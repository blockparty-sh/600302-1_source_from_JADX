package p015io.grpc.internal;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.base.Supplier;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import com.google.errorprone.annotations.ForOverride;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;
import p015io.grpc.Attributes;
import p015io.grpc.CallOptions;
import p015io.grpc.ChannelLogger;
import p015io.grpc.ChannelLogger.ChannelLogLevel;
import p015io.grpc.ConnectivityState;
import p015io.grpc.ConnectivityStateInfo;
import p015io.grpc.EquivalentAddressGroup;
import p015io.grpc.HttpConnectProxiedSocketAddress;
import p015io.grpc.InternalChannelz;
import p015io.grpc.InternalChannelz.ChannelStats;
import p015io.grpc.InternalChannelz.ChannelStats.Builder;
import p015io.grpc.InternalInstrumented;
import p015io.grpc.InternalLogId;
import p015io.grpc.Metadata;
import p015io.grpc.MethodDescriptor;
import p015io.grpc.Status;
import p015io.grpc.SynchronizationContext;
import p015io.grpc.internal.BackoffPolicy.Provider;
import p015io.grpc.internal.ClientStreamListener.RpcProgress;
import p015io.grpc.internal.ClientTransportFactory.ClientTransportOptions;
import p015io.grpc.internal.ManagedClientTransport.Listener;

@ThreadSafe
/* renamed from: io.grpc.internal.InternalSubchannel */
final class InternalSubchannel implements InternalInstrumented<ChannelStats> {
    /* access modifiers changed from: private */
    public static final Logger log = Logger.getLogger(InternalSubchannel.class.getName());
    /* access modifiers changed from: private */
    @Nullable
    public volatile ManagedClientTransport activeTransport;
    /* access modifiers changed from: private */
    @GuardedBy("lock")
    public Index addressIndex;
    private final String authority;
    private final Provider backoffPolicyProvider;
    /* access modifiers changed from: private */
    public final Callback callback;
    private final CallTracer callsTracer;
    /* access modifiers changed from: private */
    public final ChannelLoggerImpl channelLogger;
    private final ChannelTracer channelTracer;
    /* access modifiers changed from: private */
    public final InternalChannelz channelz;
    @GuardedBy("lock")
    private final Stopwatch connectingTimer;
    /* access modifiers changed from: private */
    public final InUseStateAggregator<ConnectionClientTransport> inUseStateAggregator = new InUseStateAggregator<ConnectionClientTransport>() {
        /* access modifiers changed from: protected */
        public void handleInUse() {
            InternalSubchannel.this.callback.onInUse(InternalSubchannel.this);
        }

        /* access modifiers changed from: protected */
        public void handleNotInUse() {
            InternalSubchannel.this.callback.onNotInUse(InternalSubchannel.this);
        }
    };
    /* access modifiers changed from: private */
    public final Object lock = new Object();
    private final InternalLogId logId;
    /* access modifiers changed from: private */
    @GuardedBy("lock")
    @Nullable
    public ConnectionClientTransport pendingTransport;
    /* access modifiers changed from: private */
    @GuardedBy("lock")
    public boolean reconnectCanceled;
    /* access modifiers changed from: private */
    @GuardedBy("lock")
    public BackoffPolicy reconnectPolicy;
    /* access modifiers changed from: private */
    @GuardedBy("lock")
    @Nullable
    public ScheduledFuture<?> reconnectTask;
    private final ScheduledExecutorService scheduledExecutor;
    /* access modifiers changed from: private */
    @GuardedBy("lock")
    public Status shutdownReason;
    /* access modifiers changed from: private */
    @GuardedBy("lock")
    public ConnectivityStateInfo state = ConnectivityStateInfo.forNonError(ConnectivityState.IDLE);
    /* access modifiers changed from: private */
    public final SynchronizationContext syncContext;
    private final ClientTransportFactory transportFactory;
    /* access modifiers changed from: private */
    @GuardedBy("lock")
    public final Collection<ConnectionClientTransport> transports = new ArrayList();
    private final String userAgent;

    @VisibleForTesting
    /* renamed from: io.grpc.internal.InternalSubchannel$CallTracingTransport */
    static final class CallTracingTransport extends ForwardingConnectionClientTransport {
        /* access modifiers changed from: private */
        public final CallTracer callTracer;
        private final ConnectionClientTransport delegate;

        private CallTracingTransport(ConnectionClientTransport connectionClientTransport, CallTracer callTracer2) {
            this.delegate = connectionClientTransport;
            this.callTracer = callTracer2;
        }

        /* access modifiers changed from: protected */
        public ConnectionClientTransport delegate() {
            return this.delegate;
        }

        public ClientStream newStream(MethodDescriptor<?, ?> methodDescriptor, Metadata metadata, CallOptions callOptions) {
            final ClientStream newStream = super.newStream(methodDescriptor, metadata, callOptions);
            return new ForwardingClientStream() {
                /* access modifiers changed from: protected */
                public ClientStream delegate() {
                    return newStream;
                }

                public void start(final ClientStreamListener clientStreamListener) {
                    CallTracingTransport.this.callTracer.reportCallStarted();
                    super.start(new ForwardingClientStreamListener() {
                        /* access modifiers changed from: protected */
                        public ClientStreamListener delegate() {
                            return clientStreamListener;
                        }

                        public void closed(Status status, Metadata metadata) {
                            CallTracingTransport.this.callTracer.reportCallEnded(status.isOk());
                            super.closed(status, metadata);
                        }

                        public void closed(Status status, RpcProgress rpcProgress, Metadata metadata) {
                            CallTracingTransport.this.callTracer.reportCallEnded(status.isOk());
                            super.closed(status, rpcProgress, metadata);
                        }
                    });
                }
            };
        }
    }

    /* renamed from: io.grpc.internal.InternalSubchannel$Callback */
    static abstract class Callback {
        /* access modifiers changed from: 0000 */
        @ForOverride
        public void onInUse(InternalSubchannel internalSubchannel) {
        }

        /* access modifiers changed from: 0000 */
        @ForOverride
        public void onNotInUse(InternalSubchannel internalSubchannel) {
        }

        /* access modifiers changed from: 0000 */
        @ForOverride
        public void onStateChange(InternalSubchannel internalSubchannel, ConnectivityStateInfo connectivityStateInfo) {
        }

        /* access modifiers changed from: 0000 */
        @ForOverride
        public void onTerminated(InternalSubchannel internalSubchannel) {
        }

        Callback() {
        }
    }

    @VisibleForTesting
    /* renamed from: io.grpc.internal.InternalSubchannel$Index */
    static final class Index {
        private List<EquivalentAddressGroup> addressGroups;
        private int addressIndex;
        private int groupIndex;

        public Index(List<EquivalentAddressGroup> list) {
            this.addressGroups = list;
        }

        public boolean isValid() {
            return this.groupIndex < this.addressGroups.size();
        }

        public boolean isAtBeginning() {
            return this.groupIndex == 0 && this.addressIndex == 0;
        }

        public void increment() {
            EquivalentAddressGroup equivalentAddressGroup = (EquivalentAddressGroup) this.addressGroups.get(this.groupIndex);
            this.addressIndex++;
            if (this.addressIndex >= equivalentAddressGroup.getAddresses().size()) {
                this.groupIndex++;
                this.addressIndex = 0;
            }
        }

        public void reset() {
            this.groupIndex = 0;
            this.addressIndex = 0;
        }

        public SocketAddress getCurrentAddress() {
            return (SocketAddress) ((EquivalentAddressGroup) this.addressGroups.get(this.groupIndex)).getAddresses().get(this.addressIndex);
        }

        public Attributes getCurrentEagAttributes() {
            return ((EquivalentAddressGroup) this.addressGroups.get(this.groupIndex)).getAttributes();
        }

        public List<EquivalentAddressGroup> getGroups() {
            return this.addressGroups;
        }

        public void updateGroups(List<EquivalentAddressGroup> list) {
            this.addressGroups = list;
            reset();
        }

        public boolean seekTo(SocketAddress socketAddress) {
            int i = 0;
            while (i < this.addressGroups.size()) {
                int indexOf = ((EquivalentAddressGroup) this.addressGroups.get(i)).getAddresses().indexOf(socketAddress);
                if (indexOf == -1) {
                    i++;
                } else {
                    this.groupIndex = i;
                    this.addressIndex = indexOf;
                    return true;
                }
            }
            return false;
        }
    }

    /* renamed from: io.grpc.internal.InternalSubchannel$TransportListener */
    private class TransportListener implements Listener {
        final SocketAddress address;
        final ConnectionClientTransport transport;

        TransportListener(ConnectionClientTransport connectionClientTransport, SocketAddress socketAddress) {
            this.transport = connectionClientTransport;
            this.address = socketAddress;
        }

        public void transportReady() {
            Status access$1100;
            InternalSubchannel.this.channelLogger.log(ChannelLogLevel.INFO, "READY");
            try {
                synchronized (InternalSubchannel.this.lock) {
                    access$1100 = InternalSubchannel.this.shutdownReason;
                    InternalSubchannel.this.reconnectPolicy = null;
                    if (access$1100 != null) {
                        Preconditions.checkState(InternalSubchannel.this.activeTransport == null, "Unexpected non-null activeTransport");
                    } else if (InternalSubchannel.this.pendingTransport == this.transport) {
                        InternalSubchannel.this.gotoNonErrorState(ConnectivityState.READY);
                        InternalSubchannel.this.activeTransport = this.transport;
                        InternalSubchannel.this.pendingTransport = null;
                    }
                }
                InternalSubchannel.this.syncContext.drain();
                if (access$1100 != null) {
                    this.transport.shutdown(access$1100);
                }
            } catch (Throwable th) {
                InternalSubchannel.this.syncContext.drain();
                throw th;
            }
        }

        public void transportInUse(boolean z) {
            InternalSubchannel.this.handleTransportInUseState(this.transport, z);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:24:0x00ba, code lost:
            p015io.grpc.internal.InternalSubchannel.access$900(r6.this$0).drain();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:25:0x00c3, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void transportShutdown(p015io.grpc.Status r7) {
            /*
                r6 = this;
                io.grpc.internal.InternalSubchannel r0 = p015io.grpc.internal.InternalSubchannel.this
                io.grpc.internal.ChannelLoggerImpl r0 = r0.channelLogger
                io.grpc.ChannelLogger$ChannelLogLevel r1 = p015io.grpc.ChannelLogger.ChannelLogLevel.INFO
                r2 = 2
                java.lang.Object[] r2 = new java.lang.Object[r2]
                io.grpc.internal.ConnectionClientTransport r3 = r6.transport
                io.grpc.InternalLogId r3 = r3.getLogId()
                r4 = 0
                r2[r4] = r3
                io.grpc.internal.InternalSubchannel r3 = p015io.grpc.internal.InternalSubchannel.this
                java.lang.String r3 = r3.printShortStatus(r7)
                r5 = 1
                r2[r5] = r3
                java.lang.String r3 = "{0} SHUTDOWN with {1}"
                r0.log(r1, r3, r2)
                io.grpc.internal.InternalSubchannel r0 = p015io.grpc.internal.InternalSubchannel.this     // Catch:{ all -> 0x00c7 }
                java.lang.Object r0 = r0.lock     // Catch:{ all -> 0x00c7 }
                monitor-enter(r0)     // Catch:{ all -> 0x00c7 }
                io.grpc.internal.InternalSubchannel r1 = p015io.grpc.internal.InternalSubchannel.this     // Catch:{ all -> 0x00c4 }
                io.grpc.ConnectivityStateInfo r1 = r1.state     // Catch:{ all -> 0x00c4 }
                io.grpc.ConnectivityState r1 = r1.getState()     // Catch:{ all -> 0x00c4 }
                io.grpc.ConnectivityState r2 = p015io.grpc.ConnectivityState.SHUTDOWN     // Catch:{ all -> 0x00c4 }
                if (r1 != r2) goto L_0x0042
                monitor-exit(r0)     // Catch:{ all -> 0x00c4 }
                io.grpc.internal.InternalSubchannel r7 = p015io.grpc.internal.InternalSubchannel.this
                io.grpc.SynchronizationContext r7 = r7.syncContext
                r7.drain()
                return
            L_0x0042:
                io.grpc.internal.InternalSubchannel r1 = p015io.grpc.internal.InternalSubchannel.this     // Catch:{ all -> 0x00c4 }
                io.grpc.internal.ManagedClientTransport r1 = r1.activeTransport     // Catch:{ all -> 0x00c4 }
                io.grpc.internal.ConnectionClientTransport r2 = r6.transport     // Catch:{ all -> 0x00c4 }
                r3 = 0
                if (r1 != r2) goto L_0x0063
                io.grpc.internal.InternalSubchannel r7 = p015io.grpc.internal.InternalSubchannel.this     // Catch:{ all -> 0x00c4 }
                io.grpc.ConnectivityState r1 = p015io.grpc.ConnectivityState.IDLE     // Catch:{ all -> 0x00c4 }
                r7.gotoNonErrorState(r1)     // Catch:{ all -> 0x00c4 }
                io.grpc.internal.InternalSubchannel r7 = p015io.grpc.internal.InternalSubchannel.this     // Catch:{ all -> 0x00c4 }
                r7.activeTransport = r3     // Catch:{ all -> 0x00c4 }
                io.grpc.internal.InternalSubchannel r7 = p015io.grpc.internal.InternalSubchannel.this     // Catch:{ all -> 0x00c4 }
                io.grpc.internal.InternalSubchannel$Index r7 = r7.addressIndex     // Catch:{ all -> 0x00c4 }
                r7.reset()     // Catch:{ all -> 0x00c4 }
                goto L_0x00b9
            L_0x0063:
                io.grpc.internal.InternalSubchannel r1 = p015io.grpc.internal.InternalSubchannel.this     // Catch:{ all -> 0x00c4 }
                io.grpc.internal.ConnectionClientTransport r1 = r1.pendingTransport     // Catch:{ all -> 0x00c4 }
                io.grpc.internal.ConnectionClientTransport r2 = r6.transport     // Catch:{ all -> 0x00c4 }
                if (r1 != r2) goto L_0x00b9
                io.grpc.internal.InternalSubchannel r1 = p015io.grpc.internal.InternalSubchannel.this     // Catch:{ all -> 0x00c4 }
                io.grpc.ConnectivityStateInfo r1 = r1.state     // Catch:{ all -> 0x00c4 }
                io.grpc.ConnectivityState r1 = r1.getState()     // Catch:{ all -> 0x00c4 }
                io.grpc.ConnectivityState r2 = p015io.grpc.ConnectivityState.CONNECTING     // Catch:{ all -> 0x00c4 }
                if (r1 != r2) goto L_0x007c
                r4 = 1
            L_0x007c:
                java.lang.String r1 = "Expected state is CONNECTING, actual state is %s"
                io.grpc.internal.InternalSubchannel r2 = p015io.grpc.internal.InternalSubchannel.this     // Catch:{ all -> 0x00c4 }
                io.grpc.ConnectivityStateInfo r2 = r2.state     // Catch:{ all -> 0x00c4 }
                io.grpc.ConnectivityState r2 = r2.getState()     // Catch:{ all -> 0x00c4 }
                com.google.common.base.Preconditions.checkState(r4, r1, r2)     // Catch:{ all -> 0x00c4 }
                io.grpc.internal.InternalSubchannel r1 = p015io.grpc.internal.InternalSubchannel.this     // Catch:{ all -> 0x00c4 }
                io.grpc.internal.InternalSubchannel$Index r1 = r1.addressIndex     // Catch:{ all -> 0x00c4 }
                r1.increment()     // Catch:{ all -> 0x00c4 }
                io.grpc.internal.InternalSubchannel r1 = p015io.grpc.internal.InternalSubchannel.this     // Catch:{ all -> 0x00c4 }
                io.grpc.internal.InternalSubchannel$Index r1 = r1.addressIndex     // Catch:{ all -> 0x00c4 }
                boolean r1 = r1.isValid()     // Catch:{ all -> 0x00c4 }
                if (r1 != 0) goto L_0x00b4
                io.grpc.internal.InternalSubchannel r1 = p015io.grpc.internal.InternalSubchannel.this     // Catch:{ all -> 0x00c4 }
                r1.pendingTransport = r3     // Catch:{ all -> 0x00c4 }
                io.grpc.internal.InternalSubchannel r1 = p015io.grpc.internal.InternalSubchannel.this     // Catch:{ all -> 0x00c4 }
                io.grpc.internal.InternalSubchannel$Index r1 = r1.addressIndex     // Catch:{ all -> 0x00c4 }
                r1.reset()     // Catch:{ all -> 0x00c4 }
                io.grpc.internal.InternalSubchannel r1 = p015io.grpc.internal.InternalSubchannel.this     // Catch:{ all -> 0x00c4 }
                r1.scheduleBackoff(r7)     // Catch:{ all -> 0x00c4 }
                goto L_0x00b9
            L_0x00b4:
                io.grpc.internal.InternalSubchannel r7 = p015io.grpc.internal.InternalSubchannel.this     // Catch:{ all -> 0x00c4 }
                r7.startNewTransport()     // Catch:{ all -> 0x00c4 }
            L_0x00b9:
                monitor-exit(r0)     // Catch:{ all -> 0x00c4 }
                io.grpc.internal.InternalSubchannel r7 = p015io.grpc.internal.InternalSubchannel.this
                io.grpc.SynchronizationContext r7 = r7.syncContext
                r7.drain()
                return
            L_0x00c4:
                r7 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x00c4 }
                throw r7     // Catch:{ all -> 0x00c7 }
            L_0x00c7:
                r7 = move-exception
                io.grpc.internal.InternalSubchannel r0 = p015io.grpc.internal.InternalSubchannel.this
                io.grpc.SynchronizationContext r0 = r0.syncContext
                r0.drain()
                throw r7
            */
            throw new UnsupportedOperationException("Method not decompiled: p015io.grpc.internal.InternalSubchannel.TransportListener.transportShutdown(io.grpc.Status):void");
        }

        public void transportTerminated() {
            boolean z = true;
            InternalSubchannel.this.channelLogger.log(ChannelLogLevel.INFO, "{0} Terminated", this.transport.getLogId());
            InternalSubchannel.this.channelz.removeClientSocket(this.transport);
            InternalSubchannel.this.handleTransportInUseState(this.transport, false);
            try {
                synchronized (InternalSubchannel.this.lock) {
                    InternalSubchannel.this.transports.remove(this.transport);
                    if (InternalSubchannel.this.state.getState() == ConnectivityState.SHUTDOWN && InternalSubchannel.this.transports.isEmpty()) {
                        InternalSubchannel.this.handleTermination();
                    }
                }
                InternalSubchannel.this.syncContext.drain();
                if (InternalSubchannel.this.activeTransport == this.transport) {
                    z = false;
                }
                Preconditions.checkState(z, "activeTransport still points to this transport. Seems transportShutdown() was not called.");
            } catch (Throwable th) {
                InternalSubchannel.this.syncContext.drain();
                throw th;
            }
        }
    }

    @VisibleForTesting
    /* renamed from: io.grpc.internal.InternalSubchannel$TransportLogger */
    static final class TransportLogger extends ChannelLogger {
        InternalLogId logId;

        TransportLogger() {
        }

        public void log(ChannelLogLevel channelLogLevel, String str) {
            ChannelLoggerImpl.logOnly(this.logId, channelLogLevel, str);
        }

        public void log(ChannelLogLevel channelLogLevel, String str, Object... objArr) {
            ChannelLoggerImpl.logOnly(this.logId, channelLogLevel, str, objArr);
        }
    }

    InternalSubchannel(List<EquivalentAddressGroup> list, String str, String str2, Provider provider, ClientTransportFactory clientTransportFactory, ScheduledExecutorService scheduledExecutorService, Supplier<Stopwatch> supplier, SynchronizationContext synchronizationContext, Callback callback2, InternalChannelz internalChannelz, CallTracer callTracer, ChannelTracer channelTracer2, InternalLogId internalLogId, TimeProvider timeProvider) {
        Preconditions.checkNotNull(list, "addressGroups");
        Preconditions.checkArgument(!list.isEmpty(), "addressGroups is empty");
        checkListHasNoNulls(list, "addressGroups contains null entry");
        this.addressIndex = new Index(Collections.unmodifiableList(new ArrayList(list)));
        this.authority = str;
        this.userAgent = str2;
        this.backoffPolicyProvider = provider;
        this.transportFactory = clientTransportFactory;
        this.scheduledExecutor = scheduledExecutorService;
        this.connectingTimer = (Stopwatch) supplier.get();
        this.syncContext = synchronizationContext;
        this.callback = callback2;
        this.channelz = internalChannelz;
        this.callsTracer = callTracer;
        this.channelTracer = (ChannelTracer) Preconditions.checkNotNull(channelTracer2, "channelTracer");
        this.logId = InternalLogId.allocate("Subchannel", str);
        this.channelLogger = new ChannelLoggerImpl(channelTracer2, timeProvider);
    }

    /* access modifiers changed from: 0000 */
    public ChannelLogger getChannelLogger() {
        return this.channelLogger;
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x002f, code lost:
        r4.syncContext.drain();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0035, code lost:
        return null;
     */
    @javax.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public p015io.grpc.internal.ClientTransport obtainActiveTransport() {
        /*
            r4 = this;
            io.grpc.internal.ManagedClientTransport r0 = r4.activeTransport
            if (r0 == 0) goto L_0x0005
            return r0
        L_0x0005:
            java.lang.Object r0 = r4.lock     // Catch:{ all -> 0x0039 }
            monitor-enter(r0)     // Catch:{ all -> 0x0039 }
            io.grpc.internal.ManagedClientTransport r1 = r4.activeTransport     // Catch:{ all -> 0x0036 }
            if (r1 == 0) goto L_0x0013
            monitor-exit(r0)     // Catch:{ all -> 0x0036 }
            io.grpc.SynchronizationContext r0 = r4.syncContext
            r0.drain()
            return r1
        L_0x0013:
            io.grpc.ConnectivityStateInfo r1 = r4.state     // Catch:{ all -> 0x0036 }
            io.grpc.ConnectivityState r1 = r1.getState()     // Catch:{ all -> 0x0036 }
            io.grpc.ConnectivityState r2 = p015io.grpc.ConnectivityState.IDLE     // Catch:{ all -> 0x0036 }
            if (r1 != r2) goto L_0x002e
            io.grpc.internal.ChannelLoggerImpl r1 = r4.channelLogger     // Catch:{ all -> 0x0036 }
            io.grpc.ChannelLogger$ChannelLogLevel r2 = p015io.grpc.ChannelLogger.ChannelLogLevel.INFO     // Catch:{ all -> 0x0036 }
            java.lang.String r3 = "CONNECTING as requested"
            r1.log(r2, r3)     // Catch:{ all -> 0x0036 }
            io.grpc.ConnectivityState r1 = p015io.grpc.ConnectivityState.CONNECTING     // Catch:{ all -> 0x0036 }
            r4.gotoNonErrorState(r1)     // Catch:{ all -> 0x0036 }
            r4.startNewTransport()     // Catch:{ all -> 0x0036 }
        L_0x002e:
            monitor-exit(r0)     // Catch:{ all -> 0x0036 }
            io.grpc.SynchronizationContext r0 = r4.syncContext
            r0.drain()
            r0 = 0
            return r0
        L_0x0036:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0036 }
            throw r1     // Catch:{ all -> 0x0039 }
        L_0x0039:
            r0 = move-exception
            io.grpc.SynchronizationContext r1 = r4.syncContext
            r1.drain()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p015io.grpc.internal.InternalSubchannel.obtainActiveTransport():io.grpc.internal.ClientTransport");
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public ClientTransport getTransport() {
        return this.activeTransport;
    }

    /* access modifiers changed from: 0000 */
    public String getAuthority() {
        return this.authority;
    }

    /* access modifiers changed from: private */
    @GuardedBy("lock")
    public void startNewTransport() {
        SocketAddress socketAddress;
        HttpConnectProxiedSocketAddress httpConnectProxiedSocketAddress;
        Preconditions.checkState(this.reconnectTask == null, "Should have no reconnectTask scheduled");
        if (this.addressIndex.isAtBeginning()) {
            this.connectingTimer.reset().start();
        }
        SocketAddress currentAddress = this.addressIndex.getCurrentAddress();
        if (currentAddress instanceof HttpConnectProxiedSocketAddress) {
            httpConnectProxiedSocketAddress = (HttpConnectProxiedSocketAddress) currentAddress;
            socketAddress = httpConnectProxiedSocketAddress.getTargetAddress();
        } else {
            socketAddress = currentAddress;
            httpConnectProxiedSocketAddress = null;
        }
        ClientTransportOptions httpConnectProxiedSocketAddress2 = new ClientTransportOptions().setAuthority(this.authority).setEagAttributes(this.addressIndex.getCurrentEagAttributes()).setUserAgent(this.userAgent).setHttpConnectProxiedSocketAddress(httpConnectProxiedSocketAddress);
        TransportLogger transportLogger = new TransportLogger();
        transportLogger.logId = getLogId();
        CallTracingTransport callTracingTransport = new CallTracingTransport(this.transportFactory.newClientTransport(socketAddress, httpConnectProxiedSocketAddress2, transportLogger), this.callsTracer);
        transportLogger.logId = callTracingTransport.getLogId();
        this.channelz.addClientSocket(callTracingTransport);
        this.pendingTransport = callTracingTransport;
        this.transports.add(callTracingTransport);
        Runnable start = callTracingTransport.start(new TransportListener(callTracingTransport, socketAddress));
        if (start != null) {
            this.syncContext.executeLater(start);
        }
        this.channelLogger.log(ChannelLogLevel.INFO, "Started transport {0}", transportLogger.logId);
    }

    /* access modifiers changed from: private */
    @GuardedBy("lock")
    public void scheduleBackoff(Status status) {
        gotoState(ConnectivityStateInfo.forTransientFailure(status));
        if (this.reconnectPolicy == null) {
            this.reconnectPolicy = this.backoffPolicyProvider.get();
        }
        long nextBackoffNanos = this.reconnectPolicy.nextBackoffNanos() - this.connectingTimer.elapsed(TimeUnit.NANOSECONDS);
        boolean z = true;
        this.channelLogger.log(ChannelLogLevel.INFO, "TRANSIENT_FAILURE ({0}). Will reconnect after {1} ns", printShortStatus(status), Long.valueOf(nextBackoffNanos));
        if (this.reconnectTask != null) {
            z = false;
        }
        Preconditions.checkState(z, "previous reconnectTask is not done");
        this.reconnectCanceled = false;
        this.reconnectTask = this.scheduledExecutor.schedule(new LogExceptionRunnable(new Runnable() {
            public void run() {
                try {
                    synchronized (InternalSubchannel.this.lock) {
                        InternalSubchannel.this.reconnectTask = null;
                        if (InternalSubchannel.this.reconnectCanceled) {
                            InternalSubchannel.this.syncContext.drain();
                            return;
                        }
                        InternalSubchannel.this.channelLogger.log(ChannelLogLevel.INFO, "CONNECTING after backoff");
                        InternalSubchannel.this.gotoNonErrorState(ConnectivityState.CONNECTING);
                        InternalSubchannel.this.startNewTransport();
                    }
                } catch (Throwable th) {
                    try {
                        InternalSubchannel.log.log(Level.WARNING, "Exception handling end of backoff", th);
                    } catch (Throwable th2) {
                        InternalSubchannel.this.syncContext.drain();
                        throw th2;
                    }
                }
                InternalSubchannel.this.syncContext.drain();
            }
        }), nextBackoffNanos, TimeUnit.NANOSECONDS);
    }

    /* access modifiers changed from: 0000 */
    public void resetConnectBackoff() {
        try {
            synchronized (this.lock) {
                if (this.state.getState() != ConnectivityState.TRANSIENT_FAILURE) {
                    this.syncContext.drain();
                    return;
                }
                cancelReconnectTask();
                this.channelLogger.log(ChannelLogLevel.INFO, "CONNECTING; backoff interrupted");
                gotoNonErrorState(ConnectivityState.CONNECTING);
                startNewTransport();
                this.syncContext.drain();
            }
        } catch (Throwable th) {
            this.syncContext.drain();
            throw th;
        }
    }

    /* access modifiers changed from: private */
    @GuardedBy("lock")
    public void gotoNonErrorState(ConnectivityState connectivityState) {
        gotoState(ConnectivityStateInfo.forNonError(connectivityState));
    }

    @GuardedBy("lock")
    private void gotoState(final ConnectivityStateInfo connectivityStateInfo) {
        if (this.state.getState() != connectivityStateInfo.getState()) {
            boolean z = this.state.getState() != ConnectivityState.SHUTDOWN;
            StringBuilder sb = new StringBuilder();
            sb.append("Cannot transition out of SHUTDOWN to ");
            sb.append(connectivityStateInfo);
            Preconditions.checkState(z, sb.toString());
            this.state = connectivityStateInfo;
            this.syncContext.executeLater(new Runnable() {
                public void run() {
                    InternalSubchannel.this.callback.onStateChange(InternalSubchannel.this, connectivityStateInfo);
                }
            });
        }
    }

    public void updateAddresses(List<EquivalentAddressGroup> list) {
        ManagedClientTransport managedClientTransport;
        Preconditions.checkNotNull(list, "newAddressGroups");
        checkListHasNoNulls(list, "newAddressGroups contains null entry");
        Preconditions.checkArgument(!list.isEmpty(), "newAddressGroups is empty");
        List unmodifiableList = Collections.unmodifiableList(new ArrayList(list));
        try {
            synchronized (this.lock) {
                SocketAddress currentAddress = this.addressIndex.getCurrentAddress();
                this.addressIndex.updateGroups(unmodifiableList);
                if ((this.state.getState() != ConnectivityState.READY && this.state.getState() != ConnectivityState.CONNECTING) || this.addressIndex.seekTo(currentAddress)) {
                    managedClientTransport = null;
                } else if (this.state.getState() == ConnectivityState.READY) {
                    managedClientTransport = this.activeTransport;
                    this.activeTransport = null;
                    this.addressIndex.reset();
                    gotoNonErrorState(ConnectivityState.IDLE);
                } else {
                    managedClientTransport = this.pendingTransport;
                    this.pendingTransport = null;
                    this.addressIndex.reset();
                    startNewTransport();
                }
            }
            this.syncContext.drain();
            if (managedClientTransport != null) {
                managedClientTransport.shutdown(Status.UNAVAILABLE.withDescription("InternalSubchannel closed transport due to address change"));
            }
        } catch (Throwable th) {
            this.syncContext.drain();
            throw th;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0038, code lost:
        r4.syncContext.drain();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x003d, code lost:
        if (r1 == null) goto L_0x0042;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x003f, code lost:
        r1.shutdown(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0042, code lost:
        if (r2 == null) goto L_0x0047;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0044, code lost:
        r2.shutdown(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0047, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void shutdown(p015io.grpc.Status r5) {
        /*
            r4 = this;
            java.lang.Object r0 = r4.lock     // Catch:{ all -> 0x004b }
            monitor-enter(r0)     // Catch:{ all -> 0x004b }
            io.grpc.ConnectivityStateInfo r1 = r4.state     // Catch:{ all -> 0x0048 }
            io.grpc.ConnectivityState r1 = r1.getState()     // Catch:{ all -> 0x0048 }
            io.grpc.ConnectivityState r2 = p015io.grpc.ConnectivityState.SHUTDOWN     // Catch:{ all -> 0x0048 }
            if (r1 != r2) goto L_0x0014
            monitor-exit(r0)     // Catch:{ all -> 0x0048 }
            io.grpc.SynchronizationContext r5 = r4.syncContext
            r5.drain()
            return
        L_0x0014:
            r4.shutdownReason = r5     // Catch:{ all -> 0x0048 }
            io.grpc.ConnectivityState r1 = p015io.grpc.ConnectivityState.SHUTDOWN     // Catch:{ all -> 0x0048 }
            r4.gotoNonErrorState(r1)     // Catch:{ all -> 0x0048 }
            io.grpc.internal.ManagedClientTransport r1 = r4.activeTransport     // Catch:{ all -> 0x0048 }
            io.grpc.internal.ConnectionClientTransport r2 = r4.pendingTransport     // Catch:{ all -> 0x0048 }
            r3 = 0
            r4.activeTransport = r3     // Catch:{ all -> 0x0048 }
            r4.pendingTransport = r3     // Catch:{ all -> 0x0048 }
            io.grpc.internal.InternalSubchannel$Index r3 = r4.addressIndex     // Catch:{ all -> 0x0048 }
            r3.reset()     // Catch:{ all -> 0x0048 }
            java.util.Collection<io.grpc.internal.ConnectionClientTransport> r3 = r4.transports     // Catch:{ all -> 0x0048 }
            boolean r3 = r3.isEmpty()     // Catch:{ all -> 0x0048 }
            if (r3 == 0) goto L_0x0034
            r4.handleTermination()     // Catch:{ all -> 0x0048 }
        L_0x0034:
            r4.cancelReconnectTask()     // Catch:{ all -> 0x0048 }
            monitor-exit(r0)     // Catch:{ all -> 0x0048 }
            io.grpc.SynchronizationContext r0 = r4.syncContext
            r0.drain()
            if (r1 == 0) goto L_0x0042
            r1.shutdown(r5)
        L_0x0042:
            if (r2 == 0) goto L_0x0047
            r2.shutdown(r5)
        L_0x0047:
            return
        L_0x0048:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0048 }
            throw r5     // Catch:{ all -> 0x004b }
        L_0x004b:
            r5 = move-exception
            io.grpc.SynchronizationContext r0 = r4.syncContext
            r0.drain()
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: p015io.grpc.internal.InternalSubchannel.shutdown(io.grpc.Status):void");
    }

    public String toString() {
        List groups;
        synchronized (this.lock) {
            groups = this.addressIndex.getGroups();
        }
        return MoreObjects.toStringHelper((Object) this).add("logId", this.logId.getId()).add("addressGroups", (Object) groups).toString();
    }

    /* access modifiers changed from: private */
    @GuardedBy("lock")
    public void handleTermination() {
        this.channelLogger.log(ChannelLogLevel.INFO, "Terminated");
        this.syncContext.executeLater(new Runnable() {
            public void run() {
                InternalSubchannel.this.callback.onTerminated(InternalSubchannel.this);
            }
        });
    }

    /* access modifiers changed from: private */
    public void handleTransportInUseState(final ConnectionClientTransport connectionClientTransport, final boolean z) {
        this.syncContext.execute(new Runnable() {
            public void run() {
                InternalSubchannel.this.inUseStateAggregator.updateObjectInUse(connectionClientTransport, z);
            }
        });
    }

    /* access modifiers changed from: 0000 */
    public void shutdownNow(Status status) {
        ArrayList<ManagedClientTransport> arrayList;
        shutdown(status);
        try {
            synchronized (this.lock) {
                arrayList = new ArrayList<>(this.transports);
            }
            this.syncContext.drain();
            for (ManagedClientTransport shutdownNow : arrayList) {
                shutdownNow.shutdownNow(status);
            }
        } catch (Throwable th) {
            this.syncContext.drain();
            throw th;
        }
    }

    /* access modifiers changed from: 0000 */
    public List<EquivalentAddressGroup> getAddressGroups() {
        List<EquivalentAddressGroup> groups;
        try {
            synchronized (this.lock) {
                groups = this.addressIndex.getGroups();
            }
            this.syncContext.drain();
            return groups;
        } catch (Throwable th) {
            this.syncContext.drain();
            throw th;
        }
    }

    @GuardedBy("lock")
    private void cancelReconnectTask() {
        ScheduledFuture<?> scheduledFuture = this.reconnectTask;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
            this.reconnectCanceled = true;
            this.reconnectTask = null;
            this.reconnectPolicy = null;
        }
    }

    public InternalLogId getLogId() {
        return this.logId;
    }

    public ListenableFuture<ChannelStats> getStats() {
        List groups;
        ArrayList arrayList;
        SettableFuture create = SettableFuture.create();
        Builder builder = new Builder();
        synchronized (this.lock) {
            groups = this.addressIndex.getGroups();
            arrayList = new ArrayList(this.transports);
        }
        builder.setTarget(groups.toString()).setState(getState());
        builder.setSockets(arrayList);
        this.callsTracer.updateBuilder(builder);
        this.channelTracer.updateBuilder(builder);
        create.set(builder.build());
        return create;
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public ConnectivityState getState() {
        ConnectivityState state2;
        try {
            synchronized (this.lock) {
                state2 = this.state.getState();
            }
            this.syncContext.drain();
            return state2;
        } catch (Throwable th) {
            this.syncContext.drain();
            throw th;
        }
    }

    private static void checkListHasNoNulls(List<?> list, String str) {
        for (Object checkNotNull : list) {
            Preconditions.checkNotNull(checkNotNull, str);
        }
    }

    /* access modifiers changed from: private */
    public String printShortStatus(Status status) {
        StringBuilder sb = new StringBuilder();
        sb.append(status.getCode());
        if (status.getDescription() != null) {
            sb.append("(");
            sb.append(status.getDescription());
            sb.append(")");
        }
        return sb.toString();
    }
}
