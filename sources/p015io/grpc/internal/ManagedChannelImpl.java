package p015io.grpc.internal;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.base.Supplier;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import java.lang.Thread.UncaughtExceptionHandler;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.annotation.CheckForNull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;
import p015io.grpc.Attributes;
import p015io.grpc.CallOptions;
import p015io.grpc.Channel;
import p015io.grpc.ChannelLogger;
import p015io.grpc.ChannelLogger.ChannelLogLevel;
import p015io.grpc.ClientCall;
import p015io.grpc.ClientInterceptor;
import p015io.grpc.ClientInterceptors;
import p015io.grpc.ClientStreamTracer;
import p015io.grpc.CompressorRegistry;
import p015io.grpc.ConnectivityState;
import p015io.grpc.ConnectivityStateInfo;
import p015io.grpc.Context;
import p015io.grpc.DecompressorRegistry;
import p015io.grpc.EquivalentAddressGroup;
import p015io.grpc.InternalChannelz;
import p015io.grpc.InternalChannelz.ChannelStats;
import p015io.grpc.InternalChannelz.ChannelTrace.Event.Builder;
import p015io.grpc.InternalChannelz.ChannelTrace.Event.Severity;
import p015io.grpc.InternalInstrumented;
import p015io.grpc.InternalLogId;
import p015io.grpc.LoadBalancer;
import p015io.grpc.LoadBalancer.Helper;
import p015io.grpc.LoadBalancer.PickResult;
import p015io.grpc.LoadBalancer.PickSubchannelArgs;
import p015io.grpc.LoadBalancer.ResolvedAddresses;
import p015io.grpc.LoadBalancer.Subchannel;
import p015io.grpc.LoadBalancer.SubchannelPicker;
import p015io.grpc.ManagedChannel;
import p015io.grpc.Metadata;
import p015io.grpc.MethodDescriptor;
import p015io.grpc.NameResolver;
import p015io.grpc.NameResolver.Args;
import p015io.grpc.NameResolver.ConfigOrError;
import p015io.grpc.NameResolver.Listener2;
import p015io.grpc.NameResolver.ResolutionResult;
import p015io.grpc.NameResolver.ServiceConfigParser;
import p015io.grpc.ProxyDetector;
import p015io.grpc.Status;
import p015io.grpc.SynchronizationContext;
import p015io.grpc.SynchronizationContext.ScheduledHandle;
import p015io.grpc.internal.BackoffPolicy.Provider;
import p015io.grpc.internal.CallTracer.Factory;
import p015io.grpc.internal.ManagedClientTransport.Listener;

@ThreadSafe
/* renamed from: io.grpc.internal.ManagedChannelImpl */
final class ManagedChannelImpl extends ManagedChannel implements InternalInstrumented<ChannelStats> {
    static final long IDLE_TIMEOUT_MILLIS_DISABLE = -1;
    @VisibleForTesting
    static final Status SHUTDOWN_NOW_STATUS = Status.UNAVAILABLE.withDescription("Channel shutdownNow invoked");
    @VisibleForTesting
    static final Status SHUTDOWN_STATUS = Status.UNAVAILABLE.withDescription("Channel shutdown invoked");
    @VisibleForTesting
    static final long SUBCHANNEL_SHUTDOWN_DELAY_SECONDS = 5;
    @VisibleForTesting
    static final Status SUBCHANNEL_SHUTDOWN_STATUS = Status.UNAVAILABLE.withDescription("Subchannel shutdown invoked");
    @VisibleForTesting
    static final Pattern URI_PATTERN = Pattern.compile("[a-zA-Z][a-zA-Z0-9+.-]*:/.*");
    static final Logger logger = Logger.getLogger(ManagedChannelImpl.class.getName());
    /* access modifiers changed from: private */
    public final Provider backoffPolicyProvider;
    /* access modifiers changed from: private */
    public final ExecutorHolder balancerRpcExecutorHolder;
    /* access modifiers changed from: private */
    public final ObjectPool<? extends Executor> balancerRpcExecutorPool;
    /* access modifiers changed from: private */
    public final Factory callTracerFactory;
    /* access modifiers changed from: private */
    public final long channelBufferLimit;
    /* access modifiers changed from: private */
    public final ChannelBufferMeter channelBufferUsed;
    /* access modifiers changed from: private */
    public final CallTracer channelCallTracer;
    /* access modifiers changed from: private */
    public final ChannelLogger channelLogger;
    /* access modifiers changed from: private */
    public final ConnectivityStateManager channelStateManager = new ConnectivityStateManager();
    /* access modifiers changed from: private */
    public final ChannelTracer channelTracer;
    /* access modifiers changed from: private */
    public final InternalChannelz channelz;
    /* access modifiers changed from: private */
    public final CompressorRegistry compressorRegistry;
    /* access modifiers changed from: private */
    public final DecompressorRegistry decompressorRegistry;
    /* access modifiers changed from: private */
    @Nullable
    public final Map<String, ?> defaultServiceConfig;
    /* access modifiers changed from: private */
    public final DelayedClientTransport delayedTransport;
    private final Listener delayedTransportListener;
    /* access modifiers changed from: private */
    public final Executor executor;
    private final ObjectPool<? extends Executor> executorPool;
    /* access modifiers changed from: private */
    public boolean fullStreamDecompression;
    /* access modifiers changed from: private */
    @CheckForNull
    public Boolean haveBackends;
    private final long idleTimeoutMillis;
    private final Rescheduler idleTimer;
    @VisibleForTesting
    final InUseStateAggregator<Object> inUseStateAggregator;
    private final Channel interceptorChannel;
    /* access modifiers changed from: private */
    @Nullable
    public Map<String, ?> lastServiceConfig;
    /* access modifiers changed from: private */
    @Nullable
    public LbHelperImpl lbHelper;
    private final AutoConfiguredLoadBalancerFactory loadBalancerFactory;
    private final InternalLogId logId;
    /* access modifiers changed from: private */
    public final boolean lookUpServiceConfig;
    /* access modifiers changed from: private */
    public final int maxTraceEvents;
    private NameResolver nameResolver;
    private final Args nameResolverArgs;
    /* access modifiers changed from: private */
    @Nullable
    public BackoffPolicy nameResolverBackoffPolicy;
    /* access modifiers changed from: private */
    public final NameResolver.Factory nameResolverFactory;
    /* access modifiers changed from: private */
    public boolean nameResolverStarted;
    /* access modifiers changed from: private */
    public final Set<OobChannel> oobChannels;
    private boolean panicMode;
    /* access modifiers changed from: private */
    public final long perRpcBufferLimit;
    /* access modifiers changed from: private */
    public final boolean retryEnabled;
    /* access modifiers changed from: private */
    public final ScheduledExecutorForBalancer scheduledExecutorForBalancer;
    /* access modifiers changed from: private */
    @Nullable
    public ScheduledHandle scheduledNameResolverRefresh;
    private final ServiceConfigInterceptor serviceConfigInterceptor;
    /* access modifiers changed from: private */
    public final AtomicBoolean shutdown;
    /* access modifiers changed from: private */
    public boolean shutdownNowed;
    /* access modifiers changed from: private */
    public final Supplier<Stopwatch> stopwatchSupplier;
    /* access modifiers changed from: private */
    @Nullable
    public volatile SubchannelPicker subchannelPicker;
    /* access modifiers changed from: private */
    public final Set<InternalSubchannel> subchannels = new HashSet(16, 0.75f);
    @VisibleForTesting
    final SynchronizationContext syncContext = new SynchronizationContext(new UncaughtExceptionHandler() {
        public void uncaughtException(Thread thread, Throwable th) {
            Logger logger = ManagedChannelImpl.logger;
            Level level = Level.SEVERE;
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            sb.append(ManagedChannelImpl.this.getLogId());
            sb.append("] Uncaught exception in the SynchronizationContext. Panic!");
            logger.log(level, sb.toString(), th);
            ManagedChannelImpl.this.panic(th);
        }
    });
    /* access modifiers changed from: private */
    public final String target;
    /* access modifiers changed from: private */
    public volatile boolean terminated;
    private final CountDownLatch terminatedLatch;
    /* access modifiers changed from: private */
    public volatile boolean terminating;
    /* access modifiers changed from: private */
    @Nullable
    public Throttle throttle;
    /* access modifiers changed from: private */
    public final TimeProvider timeProvider;
    /* access modifiers changed from: private */
    public final ClientTransportFactory transportFactory;
    /* access modifiers changed from: private */
    public final ClientTransportProvider transportProvider;
    /* access modifiers changed from: private */
    public final UncommittedRetriableStreamsRegistry uncommittedRetriableStreamsRegistry;
    /* access modifiers changed from: private */
    @Nullable
    public final String userAgent;
    private boolean waitingForServiceConfig;

    /* renamed from: io.grpc.internal.ManagedChannelImpl$ChannelTransportProvider */
    private final class ChannelTransportProvider implements ClientTransportProvider {
        private ChannelTransportProvider() {
        }

        public ClientTransport get(PickSubchannelArgs pickSubchannelArgs) {
            SubchannelPicker access$1300 = ManagedChannelImpl.this.subchannelPicker;
            if (ManagedChannelImpl.this.shutdown.get()) {
                return ManagedChannelImpl.this.delayedTransport;
            }
            if (access$1300 == null) {
                ManagedChannelImpl.this.syncContext.execute(new Runnable() {
                    public void run() {
                        ManagedChannelImpl.this.exitIdleMode();
                    }
                });
                return ManagedChannelImpl.this.delayedTransport;
            }
            ClientTransport transportFromPickResult = GrpcUtil.getTransportFromPickResult(access$1300.pickSubchannel(pickSubchannelArgs), pickSubchannelArgs.getCallOptions().isWaitForReady());
            if (transportFromPickResult != null) {
                return transportFromPickResult;
            }
            return ManagedChannelImpl.this.delayedTransport;
        }

        public <ReqT> ClientStream newRetriableStream(MethodDescriptor<ReqT, ?> methodDescriptor, CallOptions callOptions, Metadata metadata, Context context) {
            Preconditions.checkState(ManagedChannelImpl.this.retryEnabled, "retry should be enabled");
            AnonymousClass1RetryStream r2 = new RetriableStream<ReqT>(this, methodDescriptor, metadata, callOptions, context) {
                final /* synthetic */ ChannelTransportProvider this$1;
                final /* synthetic */ CallOptions val$callOptions;
                final /* synthetic */ Context val$context;
                final /* synthetic */ Metadata val$headers;
                final /* synthetic */ MethodDescriptor val$method;

                {
                    ChannelTransportProvider channelTransportProvider = r16;
                    CallOptions callOptions = r19;
                    this.this$1 = channelTransportProvider;
                    this.val$method = r17;
                    this.val$headers = r18;
                    this.val$callOptions = callOptions;
                    this.val$context = r20;
                    ChannelBufferMeter access$1700 = ManagedChannelImpl.this.channelBufferUsed;
                    long access$1800 = ManagedChannelImpl.this.perRpcBufferLimit;
                    long access$1900 = ManagedChannelImpl.this.channelBufferLimit;
                    Executor access$2000 = ManagedChannelImpl.this.getCallExecutor(callOptions);
                    ScheduledExecutorService scheduledExecutorService = ManagedChannelImpl.this.transportFactory.getScheduledExecutorService();
                    Provider provider = (Provider) callOptions.getOption(ServiceConfigInterceptor.RETRY_POLICY_KEY);
                    Provider provider2 = (Provider) callOptions.getOption(ServiceConfigInterceptor.HEDGING_POLICY_KEY);
                    Throttle access$2200 = ManagedChannelImpl.this.throttle;
                }

                /* access modifiers changed from: 0000 */
                public Status prestart() {
                    return ManagedChannelImpl.this.uncommittedRetriableStreamsRegistry.add(this);
                }

                /* access modifiers changed from: 0000 */
                public void postCommit() {
                    ManagedChannelImpl.this.uncommittedRetriableStreamsRegistry.remove(this);
                }

                /* access modifiers changed from: 0000 */
                public ClientStream newSubstream(ClientStreamTracer.Factory factory, Metadata metadata) {
                    CallOptions withStreamTracerFactory = this.val$callOptions.withStreamTracerFactory(factory);
                    ClientTransport clientTransport = this.this$1.get(new PickSubchannelArgsImpl(this.val$method, metadata, withStreamTracerFactory));
                    Context attach = this.val$context.attach();
                    try {
                        ClientStream newStream = clientTransport.newStream(this.val$method, metadata, withStreamTracerFactory);
                        return newStream;
                    } finally {
                        this.val$context.detach(attach);
                    }
                }
            };
            return r2;
        }
    }

    @VisibleForTesting
    /* renamed from: io.grpc.internal.ManagedChannelImpl$DelayedNameResolverRefresh */
    class DelayedNameResolverRefresh implements Runnable {
        DelayedNameResolverRefresh() {
        }

        public void run() {
            ManagedChannelImpl.this.scheduledNameResolverRefresh = null;
            ManagedChannelImpl.this.refreshNameResolution();
        }
    }

    /* renamed from: io.grpc.internal.ManagedChannelImpl$DelayedTransportListener */
    private final class DelayedTransportListener implements Listener {
        public void transportReady() {
        }

        private DelayedTransportListener() {
        }

        public void transportShutdown(Status status) {
            Preconditions.checkState(ManagedChannelImpl.this.shutdown.get(), "Channel must have been shut down");
        }

        public void transportInUse(boolean z) {
            ManagedChannelImpl.this.inUseStateAggregator.updateObjectInUse(ManagedChannelImpl.this.delayedTransport, z);
        }

        public void transportTerminated() {
            Preconditions.checkState(ManagedChannelImpl.this.shutdown.get(), "Channel must have been shut down");
            ManagedChannelImpl.this.terminating = true;
            ManagedChannelImpl.this.shutdownNameResolverAndLoadBalancer(false);
            ManagedChannelImpl.this.maybeShutdownNowSubchannels();
            ManagedChannelImpl.this.maybeTerminateChannel();
        }
    }

    /* renamed from: io.grpc.internal.ManagedChannelImpl$ExecutorHolder */
    private static final class ExecutorHolder {
        private Executor executor;
        private final ObjectPool<? extends Executor> pool;

        ExecutorHolder(ObjectPool<? extends Executor> objectPool) {
            this.pool = (ObjectPool) Preconditions.checkNotNull(objectPool, "executorPool");
        }

        /* access modifiers changed from: 0000 */
        public synchronized Executor getExecutor() {
            if (this.executor == null) {
                this.executor = (Executor) Preconditions.checkNotNull(this.pool.getObject(), "%s.getObject()", (Object) this.executor);
            }
            return this.executor;
        }

        /* access modifiers changed from: 0000 */
        public synchronized void release() {
            if (this.executor != null) {
                this.executor = (Executor) this.pool.returnObject(this.executor);
            }
        }
    }

    /* renamed from: io.grpc.internal.ManagedChannelImpl$IdleModeStateAggregator */
    private final class IdleModeStateAggregator extends InUseStateAggregator<Object> {
        private IdleModeStateAggregator() {
        }

        /* access modifiers changed from: protected */
        public void handleInUse() {
            ManagedChannelImpl.this.exitIdleMode();
        }

        /* access modifiers changed from: protected */
        public void handleNotInUse() {
            if (!ManagedChannelImpl.this.shutdown.get()) {
                ManagedChannelImpl.this.rescheduleIdleTimer();
            }
        }
    }

    /* renamed from: io.grpc.internal.ManagedChannelImpl$IdleModeTimer */
    private class IdleModeTimer implements Runnable {
        private IdleModeTimer() {
        }

        public void run() {
            ManagedChannelImpl.this.enterIdleMode();
        }
    }

    /* renamed from: io.grpc.internal.ManagedChannelImpl$LbHelperImpl */
    private class LbHelperImpl extends Helper {

        /* renamed from: lb */
        LoadBalancer f681lb;

        private LbHelperImpl() {
        }

        /* access modifiers changed from: private */
        public void handleInternalSubchannelState(ConnectivityStateInfo connectivityStateInfo) {
            if (connectivityStateInfo.getState() == ConnectivityState.TRANSIENT_FAILURE || connectivityStateInfo.getState() == ConnectivityState.IDLE) {
                ManagedChannelImpl.this.refreshAndResetNameResolution();
            }
        }

        public AbstractSubchannel createSubchannel(List<EquivalentAddressGroup> list, Attributes attributes) {
            List<EquivalentAddressGroup> list2 = list;
            Attributes attributes2 = attributes;
            ManagedChannelImpl.this.logWarningIfNotInSyncContext("createSubchannel()");
            Preconditions.checkNotNull(list2, "addressGroups");
            Preconditions.checkNotNull(attributes2, "attrs");
            Preconditions.checkState(!ManagedChannelImpl.this.terminated, "Channel is terminated");
            final SubchannelImpl subchannelImpl = new SubchannelImpl(attributes2);
            long currentTimeNanos = ManagedChannelImpl.this.timeProvider.currentTimeNanos();
            InternalLogId allocate = InternalLogId.allocate("Subchannel", (String) null);
            int access$4300 = ManagedChannelImpl.this.maxTraceEvents;
            StringBuilder sb = new StringBuilder();
            sb.append("Subchannel for ");
            sb.append(list2);
            ChannelTracer channelTracer = new ChannelTracer(allocate, access$4300, currentTimeNanos, sb.toString());
            InternalSubchannel internalSubchannel = r1;
            long j = currentTimeNanos;
            SubchannelImpl subchannelImpl2 = subchannelImpl;
            InternalSubchannel internalSubchannel2 = new InternalSubchannel(list, ManagedChannelImpl.this.authority(), ManagedChannelImpl.this.userAgent, ManagedChannelImpl.this.backoffPolicyProvider, ManagedChannelImpl.this.transportFactory, ManagedChannelImpl.this.transportFactory.getScheduledExecutorService(), ManagedChannelImpl.this.stopwatchSupplier, ManagedChannelImpl.this.syncContext, new Callback() {
                /* access modifiers changed from: 0000 */
                public void onTerminated(InternalSubchannel internalSubchannel) {
                    ManagedChannelImpl.this.subchannels.remove(internalSubchannel);
                    ManagedChannelImpl.this.channelz.removeSubchannel(internalSubchannel);
                    ManagedChannelImpl.this.maybeTerminateChannel();
                }

                /* access modifiers changed from: 0000 */
                public void onStateChange(InternalSubchannel internalSubchannel, ConnectivityStateInfo connectivityStateInfo) {
                    LbHelperImpl.this.handleInternalSubchannelState(connectivityStateInfo);
                    LbHelperImpl lbHelperImpl = LbHelperImpl.this;
                    if (lbHelperImpl == ManagedChannelImpl.this.lbHelper) {
                        LbHelperImpl.this.f681lb.handleSubchannelState(subchannelImpl, connectivityStateInfo);
                    }
                }

                /* access modifiers changed from: 0000 */
                public void onInUse(InternalSubchannel internalSubchannel) {
                    ManagedChannelImpl.this.inUseStateAggregator.updateObjectInUse(internalSubchannel, true);
                }

                /* access modifiers changed from: 0000 */
                public void onNotInUse(InternalSubchannel internalSubchannel) {
                    ManagedChannelImpl.this.inUseStateAggregator.updateObjectInUse(internalSubchannel, false);
                }
            }, ManagedChannelImpl.this.channelz, ManagedChannelImpl.this.callTracerFactory.create(), channelTracer, allocate, ManagedChannelImpl.this.timeProvider);
            final InternalSubchannel internalSubchannel3 = internalSubchannel;
            ManagedChannelImpl.this.channelTracer.reportEvent(new Builder().setDescription("Child Subchannel created").setSeverity(Severity.CT_INFO).setTimestampNanos(j).setSubchannelRef(internalSubchannel3).build());
            ManagedChannelImpl.this.channelz.addSubchannel(internalSubchannel3);
            SubchannelImpl subchannelImpl3 = subchannelImpl2;
            subchannelImpl3.subchannel = internalSubchannel3;
            ManagedChannelImpl.this.syncContext.execute(new Runnable() {
                public void run() {
                    if (ManagedChannelImpl.this.terminating) {
                        internalSubchannel3.shutdown(ManagedChannelImpl.SHUTDOWN_STATUS);
                    }
                    if (!ManagedChannelImpl.this.terminated) {
                        ManagedChannelImpl.this.subchannels.add(internalSubchannel3);
                    }
                }
            });
            return subchannelImpl3;
        }

        public void updateBalancingState(final ConnectivityState connectivityState, final SubchannelPicker subchannelPicker) {
            Preconditions.checkNotNull(connectivityState, "newState");
            Preconditions.checkNotNull(subchannelPicker, "newPicker");
            ManagedChannelImpl.this.logWarningIfNotInSyncContext("updateBalancingState()");
            ManagedChannelImpl.this.syncContext.execute(new Runnable() {
                public void run() {
                    LbHelperImpl lbHelperImpl = LbHelperImpl.this;
                    if (lbHelperImpl == ManagedChannelImpl.this.lbHelper) {
                        ManagedChannelImpl.this.updateSubchannelPicker(subchannelPicker);
                        if (connectivityState != ConnectivityState.SHUTDOWN) {
                            ManagedChannelImpl.this.channelLogger.log(ChannelLogLevel.INFO, "Entering {0} state", connectivityState);
                            ManagedChannelImpl.this.channelStateManager.gotoState(connectivityState);
                        }
                    }
                }
            });
        }

        public void refreshNameResolution() {
            ManagedChannelImpl.this.logWarningIfNotInSyncContext("refreshNameResolution()");
            ManagedChannelImpl.this.syncContext.execute(new Runnable() {
                public void run() {
                    ManagedChannelImpl.this.refreshAndResetNameResolution();
                }
            });
        }

        public void updateSubchannelAddresses(Subchannel subchannel, List<EquivalentAddressGroup> list) {
            Preconditions.checkArgument(subchannel instanceof SubchannelImpl, "subchannel must have been returned from createSubchannel");
            ManagedChannelImpl.this.logWarningIfNotInSyncContext("updateSubchannelAddresses()");
            ((SubchannelImpl) subchannel).subchannel.updateAddresses(list);
        }

        public ManagedChannel createOobChannel(EquivalentAddressGroup equivalentAddressGroup, String str) {
            EquivalentAddressGroup equivalentAddressGroup2 = equivalentAddressGroup;
            Preconditions.checkState(!ManagedChannelImpl.this.terminated, "Channel is terminated");
            long currentTimeNanos = ManagedChannelImpl.this.timeProvider.currentTimeNanos();
            InternalLogId allocate = InternalLogId.allocate("OobChannel", (String) null);
            InternalLogId allocate2 = InternalLogId.allocate("Subchannel-OOB", (String) null);
            int access$4300 = ManagedChannelImpl.this.maxTraceEvents;
            StringBuilder sb = new StringBuilder();
            sb.append("OobChannel for ");
            sb.append(equivalentAddressGroup2);
            long j = currentTimeNanos;
            ChannelTracer channelTracer = new ChannelTracer(allocate, access$4300, j, sb.toString());
            OobChannel oobChannel = new OobChannel(str, ManagedChannelImpl.this.balancerRpcExecutorPool, ManagedChannelImpl.this.transportFactory.getScheduledExecutorService(), ManagedChannelImpl.this.syncContext, ManagedChannelImpl.this.callTracerFactory.create(), channelTracer, ManagedChannelImpl.this.channelz, ManagedChannelImpl.this.timeProvider);
            ManagedChannelImpl.this.channelTracer.reportEvent(new Builder().setDescription("Child OobChannel created").setSeverity(Severity.CT_INFO).setTimestampNanos(currentTimeNanos).setChannelRef(oobChannel).build());
            int access$43002 = ManagedChannelImpl.this.maxTraceEvents;
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Subchannel for ");
            sb2.append(equivalentAddressGroup2);
            final OobChannel oobChannel2 = oobChannel;
            ChannelTracer channelTracer2 = new ChannelTracer(allocate2, access$43002, j, sb2.toString());
            final OobChannel oobChannel3 = oobChannel2;
            InternalSubchannel internalSubchannel = new InternalSubchannel(Collections.singletonList(equivalentAddressGroup), str, ManagedChannelImpl.this.userAgent, ManagedChannelImpl.this.backoffPolicyProvider, ManagedChannelImpl.this.transportFactory, ManagedChannelImpl.this.transportFactory.getScheduledExecutorService(), ManagedChannelImpl.this.stopwatchSupplier, ManagedChannelImpl.this.syncContext, new Callback() {
                /* access modifiers changed from: 0000 */
                public void onTerminated(InternalSubchannel internalSubchannel) {
                    ManagedChannelImpl.this.oobChannels.remove(oobChannel2);
                    ManagedChannelImpl.this.channelz.removeSubchannel(internalSubchannel);
                    oobChannel2.handleSubchannelTerminated();
                    ManagedChannelImpl.this.maybeTerminateChannel();
                }

                /* access modifiers changed from: 0000 */
                public void onStateChange(InternalSubchannel internalSubchannel, ConnectivityStateInfo connectivityStateInfo) {
                    LbHelperImpl.this.handleInternalSubchannelState(connectivityStateInfo);
                    oobChannel2.handleSubchannelStateChange(connectivityStateInfo);
                }
            }, ManagedChannelImpl.this.channelz, ManagedChannelImpl.this.callTracerFactory.create(), channelTracer2, allocate2, ManagedChannelImpl.this.timeProvider);
            channelTracer.reportEvent(new Builder().setDescription("Child Subchannel created").setSeverity(Severity.CT_INFO).setTimestampNanos(currentTimeNanos).setSubchannelRef(internalSubchannel).build());
            ManagedChannelImpl.this.channelz.addSubchannel(oobChannel3);
            ManagedChannelImpl.this.channelz.addSubchannel(internalSubchannel);
            oobChannel3.setSubchannel(internalSubchannel);
            ManagedChannelImpl.this.syncContext.execute(new Runnable() {
                public void run() {
                    if (ManagedChannelImpl.this.terminating) {
                        oobChannel3.shutdown();
                    }
                    if (!ManagedChannelImpl.this.terminated) {
                        ManagedChannelImpl.this.oobChannels.add(oobChannel3);
                    }
                }
            });
            return oobChannel3;
        }

        public void updateOobChannelAddresses(ManagedChannel managedChannel, EquivalentAddressGroup equivalentAddressGroup) {
            Preconditions.checkArgument(managedChannel instanceof OobChannel, "channel must have been returned from createOobChannel");
            ((OobChannel) managedChannel).updateAddresses(equivalentAddressGroup);
        }

        public String getAuthority() {
            return ManagedChannelImpl.this.authority();
        }

        @Deprecated
        public NameResolver.Factory getNameResolverFactory() {
            return ManagedChannelImpl.this.nameResolverFactory;
        }

        public SynchronizationContext getSynchronizationContext() {
            return ManagedChannelImpl.this.syncContext;
        }

        public ScheduledExecutorService getScheduledExecutorService() {
            return ManagedChannelImpl.this.scheduledExecutorForBalancer;
        }

        public ChannelLogger getChannelLogger() {
            return ManagedChannelImpl.this.channelLogger;
        }
    }

    /* renamed from: io.grpc.internal.ManagedChannelImpl$NameResolverListener */
    private final class NameResolverListener extends Listener2 {
        final LbHelperImpl helper;
        final NameResolver resolver;

        NameResolverListener(LbHelperImpl lbHelperImpl, NameResolver nameResolver) {
            this.helper = (LbHelperImpl) Preconditions.checkNotNull(lbHelperImpl, "helperImpl");
            this.resolver = (NameResolver) Preconditions.checkNotNull(nameResolver, "resolver");
        }

        public void onResult(final ResolutionResult resolutionResult) {
            ManagedChannelImpl.this.syncContext.execute(new Runnable() {
                public void run() {
                    Map map;
                    List addresses = resolutionResult.getAddresses();
                    Attributes attributes = resolutionResult.getAttributes();
                    ManagedChannelImpl.this.channelLogger.log(ChannelLogLevel.DEBUG, "Resolved address: {0}, config={1}", addresses, attributes);
                    if (ManagedChannelImpl.this.haveBackends == null || !ManagedChannelImpl.this.haveBackends.booleanValue()) {
                        ManagedChannelImpl.this.channelLogger.log(ChannelLogLevel.INFO, "Address resolved: {0}", addresses);
                        ManagedChannelImpl.this.haveBackends = Boolean.valueOf(true);
                    }
                    ManagedChannelImpl.this.nameResolverBackoffPolicy = null;
                    Map map2 = (Map) attributes.get(GrpcAttributes.NAME_RESOLVER_SERVICE_CONFIG);
                    if (!ManagedChannelImpl.this.lookUpServiceConfig) {
                        if (map2 != null) {
                            ManagedChannelImpl.this.channelLogger.log(ChannelLogLevel.INFO, "Service config from name resolver discarded by channel settings");
                        }
                        map = ManagedChannelImpl.this.defaultServiceConfig;
                    } else {
                        if (map2 != null) {
                            map = map2;
                        } else {
                            map = ManagedChannelImpl.this.defaultServiceConfig;
                            if (ManagedChannelImpl.this.defaultServiceConfig != null) {
                                ManagedChannelImpl.this.channelLogger.log(ChannelLogLevel.INFO, "Received no service config, using default service config");
                            }
                        }
                        if (map != ManagedChannelImpl.this.lastServiceConfig) {
                            ChannelLogger access$2800 = ManagedChannelImpl.this.channelLogger;
                            ChannelLogLevel channelLogLevel = ChannelLogLevel.INFO;
                            Object[] objArr = new Object[1];
                            objArr[0] = map == null ? " to null" : "";
                            access$2800.log(channelLogLevel, "Service config changed{0}", objArr);
                            ManagedChannelImpl.this.lastServiceConfig = map;
                        }
                        try {
                            ManagedChannelImpl.this.handleServiceConfigUpdate();
                        } catch (RuntimeException e) {
                            Logger logger = ManagedChannelImpl.logger;
                            Level level = Level.WARNING;
                            StringBuilder sb = new StringBuilder();
                            sb.append("[");
                            sb.append(ManagedChannelImpl.this.getLogId());
                            sb.append("] Unexpected exception from parsing service config");
                            logger.log(level, sb.toString(), e);
                        }
                    }
                    if (NameResolverListener.this.helper != ManagedChannelImpl.this.lbHelper) {
                        return;
                    }
                    if (!addresses.isEmpty() || NameResolverListener.this.helper.f681lb.canHandleEmptyAddressListFromNameResolution()) {
                        if (map != map2) {
                            attributes = attributes.toBuilder().set(GrpcAttributes.NAME_RESOLVER_SERVICE_CONFIG, map).build();
                        }
                        NameResolverListener.this.helper.f681lb.handleResolvedAddresses(ResolvedAddresses.newBuilder().setAddresses(addresses).setAttributes(attributes).build());
                        return;
                    }
                    NameResolverListener nameResolverListener = NameResolverListener.this;
                    Status status = Status.UNAVAILABLE;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("Name resolver ");
                    sb2.append(NameResolverListener.this.resolver);
                    sb2.append(" returned an empty list");
                    nameResolverListener.handleErrorInSyncContext(status.withDescription(sb2.toString()));
                }
            });
        }

        public void onError(final Status status) {
            Preconditions.checkArgument(!status.isOk(), "the error status must not be OK");
            ManagedChannelImpl.this.syncContext.execute(new Runnable() {
                public void run() {
                    NameResolverListener.this.handleErrorInSyncContext(status);
                }
            });
        }

        /* access modifiers changed from: private */
        public void handleErrorInSyncContext(Status status) {
            ManagedChannelImpl.logger.log(Level.WARNING, "[{0}] Failed to resolve name. status={1}", new Object[]{ManagedChannelImpl.this.getLogId(), status});
            if (ManagedChannelImpl.this.haveBackends == null || ManagedChannelImpl.this.haveBackends.booleanValue()) {
                ManagedChannelImpl.this.channelLogger.log(ChannelLogLevel.WARNING, "Failed to resolve name: {0}", status);
                ManagedChannelImpl.this.haveBackends = Boolean.valueOf(false);
            }
            if (this.helper == ManagedChannelImpl.this.lbHelper) {
                this.helper.f681lb.handleNameResolutionError(status);
                if (ManagedChannelImpl.this.scheduledNameResolverRefresh == null || !ManagedChannelImpl.this.scheduledNameResolverRefresh.isPending()) {
                    if (ManagedChannelImpl.this.nameResolverBackoffPolicy == null) {
                        ManagedChannelImpl managedChannelImpl = ManagedChannelImpl.this;
                        managedChannelImpl.nameResolverBackoffPolicy = managedChannelImpl.backoffPolicyProvider.get();
                    }
                    long nextBackoffNanos = ManagedChannelImpl.this.nameResolverBackoffPolicy.nextBackoffNanos();
                    ManagedChannelImpl.this.channelLogger.log(ChannelLogLevel.DEBUG, "Scheduling DNS resolution backoff for {0} ns", Long.valueOf(nextBackoffNanos));
                    ManagedChannelImpl managedChannelImpl2 = ManagedChannelImpl.this;
                    managedChannelImpl2.scheduledNameResolverRefresh = managedChannelImpl2.syncContext.schedule(new DelayedNameResolverRefresh(), nextBackoffNanos, TimeUnit.NANOSECONDS, ManagedChannelImpl.this.transportFactory.getScheduledExecutorService());
                }
            }
        }
    }

    /* renamed from: io.grpc.internal.ManagedChannelImpl$RealChannel */
    private class RealChannel extends Channel {
        private final String authority;

        private RealChannel(String str) {
            this.authority = (String) Preconditions.checkNotNull(str, "authority");
        }

        public <ReqT, RespT> ClientCall<ReqT, RespT> newCall(MethodDescriptor<ReqT, RespT> methodDescriptor, CallOptions callOptions) {
            ClientCallImpl clientCallImpl = new ClientCallImpl(methodDescriptor, ManagedChannelImpl.this.getCallExecutor(callOptions), callOptions, ManagedChannelImpl.this.transportProvider, ManagedChannelImpl.this.terminated ? null : ManagedChannelImpl.this.transportFactory.getScheduledExecutorService(), ManagedChannelImpl.this.channelCallTracer, ManagedChannelImpl.this.retryEnabled);
            return clientCallImpl.setFullStreamDecompression(ManagedChannelImpl.this.fullStreamDecompression).setDecompressorRegistry(ManagedChannelImpl.this.decompressorRegistry).setCompressorRegistry(ManagedChannelImpl.this.compressorRegistry);
        }

        public String authority() {
            return this.authority;
        }
    }

    @VisibleForTesting
    /* renamed from: io.grpc.internal.ManagedChannelImpl$ScParser */
    static final class ScParser extends ServiceConfigParser {
        private final AutoConfiguredLoadBalancerFactory autoLoadBalancerFactory;
        private final int maxHedgedAttemptsLimit;
        private final int maxRetryAttemptsLimit;
        private final boolean retryEnabled;

        ScParser(boolean z, int i, int i2, AutoConfiguredLoadBalancerFactory autoConfiguredLoadBalancerFactory) {
            this.retryEnabled = z;
            this.maxRetryAttemptsLimit = i;
            this.maxHedgedAttemptsLimit = i2;
            this.autoLoadBalancerFactory = (AutoConfiguredLoadBalancerFactory) Preconditions.checkNotNull(autoConfiguredLoadBalancerFactory, "autoLoadBalancerFactory");
        }

        public ConfigOrError parseServiceConfig(Map<String, ?> map) {
            Object obj;
            try {
                ConfigOrError selectLoadBalancerPolicy = this.autoLoadBalancerFactory.selectLoadBalancerPolicy(map);
                if (selectLoadBalancerPolicy == null) {
                    obj = null;
                } else if (selectLoadBalancerPolicy.getError() != null) {
                    return ConfigOrError.fromError(selectLoadBalancerPolicy.getError());
                } else {
                    obj = selectLoadBalancerPolicy.getConfig();
                }
                return ConfigOrError.fromConfig(ManagedChannelServiceConfig.fromServiceConfig(map, this.retryEnabled, this.maxRetryAttemptsLimit, this.maxHedgedAttemptsLimit, obj));
            } catch (RuntimeException e) {
                return ConfigOrError.fromError(Status.UNKNOWN.withDescription("failed to parse service config").withCause(e));
            }
        }
    }

    /* renamed from: io.grpc.internal.ManagedChannelImpl$ScheduledExecutorForBalancer */
    private static final class ScheduledExecutorForBalancer implements ScheduledExecutorService {
        final ScheduledExecutorService delegate;

        private ScheduledExecutorForBalancer(ScheduledExecutorService scheduledExecutorService) {
            this.delegate = (ScheduledExecutorService) Preconditions.checkNotNull(scheduledExecutorService, "delegate");
        }

        public <V> ScheduledFuture<V> schedule(Callable<V> callable, long j, TimeUnit timeUnit) {
            return this.delegate.schedule(callable, j, timeUnit);
        }

        public ScheduledFuture<?> schedule(Runnable runnable, long j, TimeUnit timeUnit) {
            return this.delegate.schedule(runnable, j, timeUnit);
        }

        public ScheduledFuture<?> scheduleAtFixedRate(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
            return this.delegate.scheduleAtFixedRate(runnable, j, j2, timeUnit);
        }

        public ScheduledFuture<?> scheduleWithFixedDelay(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
            return this.delegate.scheduleWithFixedDelay(runnable, j, j2, timeUnit);
        }

        public boolean awaitTermination(long j, TimeUnit timeUnit) throws InterruptedException {
            return this.delegate.awaitTermination(j, timeUnit);
        }

        public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> collection) throws InterruptedException {
            return this.delegate.invokeAll(collection);
        }

        public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> collection, long j, TimeUnit timeUnit) throws InterruptedException {
            return this.delegate.invokeAll(collection, j, timeUnit);
        }

        public <T> T invokeAny(Collection<? extends Callable<T>> collection) throws InterruptedException, ExecutionException {
            return this.delegate.invokeAny(collection);
        }

        public <T> T invokeAny(Collection<? extends Callable<T>> collection, long j, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
            return this.delegate.invokeAny(collection, j, timeUnit);
        }

        public boolean isShutdown() {
            return this.delegate.isShutdown();
        }

        public boolean isTerminated() {
            return this.delegate.isTerminated();
        }

        public void shutdown() {
            throw new UnsupportedOperationException("Restricted: shutdown() is not allowed");
        }

        public List<Runnable> shutdownNow() {
            throw new UnsupportedOperationException("Restricted: shutdownNow() is not allowed");
        }

        public <T> Future<T> submit(Callable<T> callable) {
            return this.delegate.submit(callable);
        }

        public Future<?> submit(Runnable runnable) {
            return this.delegate.submit(runnable);
        }

        public <T> Future<T> submit(Runnable runnable, T t) {
            return this.delegate.submit(runnable, t);
        }

        public void execute(Runnable runnable) {
            this.delegate.execute(runnable);
        }
    }

    /* renamed from: io.grpc.internal.ManagedChannelImpl$SubchannelImpl */
    private final class SubchannelImpl extends AbstractSubchannel {
        final Attributes attrs;
        @GuardedBy("shutdownLock")
        ScheduledFuture<?> delayedShutdownTask;
        final Object shutdownLock = new Object();
        @GuardedBy("shutdownLock")
        boolean shutdownRequested;
        InternalSubchannel subchannel;

        SubchannelImpl(Attributes attributes) {
            this.attrs = (Attributes) Preconditions.checkNotNull(attributes, "attrs");
        }

        /* access modifiers changed from: 0000 */
        public ClientTransport obtainActiveTransport() {
            return this.subchannel.obtainActiveTransport();
        }

        /* access modifiers changed from: 0000 */
        public InternalInstrumented<ChannelStats> getInternalSubchannel() {
            return this.subchannel;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:11:0x0025, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void shutdown() {
            /*
                r6 = this;
                io.grpc.internal.ManagedChannelImpl r0 = p015io.grpc.internal.ManagedChannelImpl.this
                java.lang.String r1 = "Subchannel.shutdown()"
                r0.logWarningIfNotInSyncContext(r1)
                java.lang.Object r0 = r6.shutdownLock
                monitor-enter(r0)
                boolean r1 = r6.shutdownRequested     // Catch:{ all -> 0x005a }
                if (r1 == 0) goto L_0x0026
                io.grpc.internal.ManagedChannelImpl r1 = p015io.grpc.internal.ManagedChannelImpl.this     // Catch:{ all -> 0x005a }
                boolean r1 = r1.terminating     // Catch:{ all -> 0x005a }
                if (r1 == 0) goto L_0x0024
                java.util.concurrent.ScheduledFuture<?> r1 = r6.delayedShutdownTask     // Catch:{ all -> 0x005a }
                if (r1 == 0) goto L_0x0024
                java.util.concurrent.ScheduledFuture<?> r1 = r6.delayedShutdownTask     // Catch:{ all -> 0x005a }
                r2 = 0
                r1.cancel(r2)     // Catch:{ all -> 0x005a }
                r1 = 0
                r6.delayedShutdownTask = r1     // Catch:{ all -> 0x005a }
                goto L_0x0029
            L_0x0024:
                monitor-exit(r0)     // Catch:{ all -> 0x005a }
                return
            L_0x0026:
                r1 = 1
                r6.shutdownRequested = r1     // Catch:{ all -> 0x005a }
            L_0x0029:
                io.grpc.internal.ManagedChannelImpl r1 = p015io.grpc.internal.ManagedChannelImpl.this     // Catch:{ all -> 0x005a }
                boolean r1 = r1.terminating     // Catch:{ all -> 0x005a }
                if (r1 != 0) goto L_0x0051
                io.grpc.internal.ManagedChannelImpl r1 = p015io.grpc.internal.ManagedChannelImpl.this     // Catch:{ all -> 0x005a }
                io.grpc.internal.ClientTransportFactory r1 = r1.transportFactory     // Catch:{ all -> 0x005a }
                java.util.concurrent.ScheduledExecutorService r1 = r1.getScheduledExecutorService()     // Catch:{ all -> 0x005a }
                io.grpc.internal.LogExceptionRunnable r2 = new io.grpc.internal.LogExceptionRunnable     // Catch:{ all -> 0x005a }
                io.grpc.internal.ManagedChannelImpl$SubchannelImpl$1ShutdownSubchannel r3 = new io.grpc.internal.ManagedChannelImpl$SubchannelImpl$1ShutdownSubchannel     // Catch:{ all -> 0x005a }
                r3.<init>()     // Catch:{ all -> 0x005a }
                r2.<init>(r3)     // Catch:{ all -> 0x005a }
                r3 = 5
                java.util.concurrent.TimeUnit r5 = java.util.concurrent.TimeUnit.SECONDS     // Catch:{ all -> 0x005a }
                java.util.concurrent.ScheduledFuture r1 = r1.schedule(r2, r3, r5)     // Catch:{ all -> 0x005a }
                r6.delayedShutdownTask = r1     // Catch:{ all -> 0x005a }
                monitor-exit(r0)     // Catch:{ all -> 0x005a }
                return
            L_0x0051:
                monitor-exit(r0)     // Catch:{ all -> 0x005a }
                io.grpc.internal.InternalSubchannel r0 = r6.subchannel
                io.grpc.Status r1 = p015io.grpc.internal.ManagedChannelImpl.SHUTDOWN_STATUS
                r0.shutdown(r1)
                return
            L_0x005a:
                r1 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x005a }
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: p015io.grpc.internal.ManagedChannelImpl.SubchannelImpl.shutdown():void");
        }

        public void requestConnection() {
            this.subchannel.obtainActiveTransport();
        }

        public List<EquivalentAddressGroup> getAllAddresses() {
            ManagedChannelImpl.this.logWarningIfNotInSyncContext("Subchannel.getAllAddresses()");
            return this.subchannel.getAddressGroups();
        }

        public Attributes getAttributes() {
            return this.attrs;
        }

        public String toString() {
            return this.subchannel.getLogId().toString();
        }

        public Channel asChannel() {
            return new SubchannelChannel(this.subchannel, ManagedChannelImpl.this.balancerRpcExecutorHolder.getExecutor(), ManagedChannelImpl.this.transportFactory.getScheduledExecutorService(), ManagedChannelImpl.this.callTracerFactory.create());
        }

        public ChannelLogger getChannelLogger() {
            return this.subchannel.getChannelLogger();
        }
    }

    /* renamed from: io.grpc.internal.ManagedChannelImpl$UncommittedRetriableStreamsRegistry */
    private final class UncommittedRetriableStreamsRegistry {
        final Object lock;
        @GuardedBy("lock")
        Status shutdownStatus;
        @GuardedBy("lock")
        Collection<ClientStream> uncommittedRetriableStreams;

        private UncommittedRetriableStreamsRegistry() {
            this.lock = new Object();
            this.uncommittedRetriableStreams = new HashSet();
        }

        /* access modifiers changed from: 0000 */
        /* JADX WARNING: Code restructure failed: missing block: B:10:0x0014, code lost:
            p015io.grpc.internal.ManagedChannelImpl.access$1500(r2.this$0).shutdown(r3);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:11:0x001d, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:9:0x0012, code lost:
            if (r1 == false) goto L_0x001d;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onShutdown(p015io.grpc.Status r3) {
            /*
                r2 = this;
                java.lang.Object r0 = r2.lock
                monitor-enter(r0)
                io.grpc.Status r1 = r2.shutdownStatus     // Catch:{ all -> 0x001e }
                if (r1 == 0) goto L_0x0009
                monitor-exit(r0)     // Catch:{ all -> 0x001e }
                return
            L_0x0009:
                r2.shutdownStatus = r3     // Catch:{ all -> 0x001e }
                java.util.Collection<io.grpc.internal.ClientStream> r1 = r2.uncommittedRetriableStreams     // Catch:{ all -> 0x001e }
                boolean r1 = r1.isEmpty()     // Catch:{ all -> 0x001e }
                monitor-exit(r0)     // Catch:{ all -> 0x001e }
                if (r1 == 0) goto L_0x001d
                io.grpc.internal.ManagedChannelImpl r0 = p015io.grpc.internal.ManagedChannelImpl.this
                io.grpc.internal.DelayedClientTransport r0 = r0.delayedTransport
                r0.shutdown(r3)
            L_0x001d:
                return
            L_0x001e:
                r3 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x001e }
                throw r3
            */
            throw new UnsupportedOperationException("Method not decompiled: p015io.grpc.internal.ManagedChannelImpl.UncommittedRetriableStreamsRegistry.onShutdown(io.grpc.Status):void");
        }

        /* access modifiers changed from: 0000 */
        public void onShutdownNow(Status status) {
            ArrayList<ClientStream> arrayList;
            onShutdown(status);
            synchronized (this.lock) {
                arrayList = new ArrayList<>(this.uncommittedRetriableStreams);
            }
            for (ClientStream cancel : arrayList) {
                cancel.cancel(status);
            }
            ManagedChannelImpl.this.delayedTransport.shutdownNow(status);
        }

        /* access modifiers changed from: 0000 */
        @Nullable
        public Status add(RetriableStream<?> retriableStream) {
            synchronized (this.lock) {
                if (this.shutdownStatus != null) {
                    Status status = this.shutdownStatus;
                    return status;
                }
                this.uncommittedRetriableStreams.add(retriableStream);
                return null;
            }
        }

        /* access modifiers changed from: 0000 */
        public void remove(RetriableStream<?> retriableStream) {
            Status status;
            synchronized (this.lock) {
                this.uncommittedRetriableStreams.remove(retriableStream);
                if (this.uncommittedRetriableStreams.isEmpty()) {
                    status = this.shutdownStatus;
                    this.uncommittedRetriableStreams = new HashSet();
                } else {
                    status = null;
                }
            }
            if (status != null) {
                ManagedChannelImpl.this.delayedTransport.shutdown(status);
            }
        }
    }

    /* access modifiers changed from: private */
    public void maybeShutdownNowSubchannels() {
        if (this.shutdownNowed) {
            for (InternalSubchannel shutdownNow : this.subchannels) {
                shutdownNow.shutdownNow(SHUTDOWN_NOW_STATUS);
            }
            for (OobChannel internalSubchannel : this.oobChannels) {
                internalSubchannel.getInternalSubchannel().shutdownNow(SHUTDOWN_NOW_STATUS);
            }
        }
    }

    public ListenableFuture<ChannelStats> getStats() {
        final SettableFuture create = SettableFuture.create();
        this.syncContext.execute(new Runnable() {
            public void run() {
                ChannelStats.Builder builder = new ChannelStats.Builder();
                ManagedChannelImpl.this.channelCallTracer.updateBuilder(builder);
                ManagedChannelImpl.this.channelTracer.updateBuilder(builder);
                builder.setTarget(ManagedChannelImpl.this.target).setState(ManagedChannelImpl.this.channelStateManager.getState());
                ArrayList arrayList = new ArrayList();
                arrayList.addAll(ManagedChannelImpl.this.subchannels);
                arrayList.addAll(ManagedChannelImpl.this.oobChannels);
                builder.setSubchannels(arrayList);
                create.set(builder.build());
            }
        });
        return create;
    }

    public InternalLogId getLogId() {
        return this.logId;
    }

    /* access modifiers changed from: private */
    public void shutdownNameResolverAndLoadBalancer(boolean z) {
        this.syncContext.throwIfNotInThisSynchronizationContext();
        if (z) {
            Preconditions.checkState(this.nameResolverStarted, "nameResolver is not started");
            Preconditions.checkState(this.lbHelper != null, "lbHelper is null");
        }
        if (this.nameResolver != null) {
            cancelNameResolverBackoff();
            this.nameResolver.shutdown();
            this.nameResolverStarted = false;
            if (z) {
                this.nameResolver = getNameResolver(this.target, this.nameResolverFactory, this.nameResolverArgs);
            } else {
                this.nameResolver = null;
            }
        }
        LbHelperImpl lbHelperImpl = this.lbHelper;
        if (lbHelperImpl != null) {
            lbHelperImpl.f681lb.shutdown();
            this.lbHelper = null;
        }
        this.subchannelPicker = null;
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public void exitIdleMode() {
        this.syncContext.throwIfNotInThisSynchronizationContext();
        if (!this.shutdown.get() && !this.panicMode) {
            if (this.inUseStateAggregator.isInUse()) {
                cancelIdleTimer(false);
            } else {
                rescheduleIdleTimer();
            }
            if (this.lbHelper == null) {
                this.channelLogger.log(ChannelLogLevel.INFO, "Exiting idle mode");
                LbHelperImpl lbHelperImpl = new LbHelperImpl();
                lbHelperImpl.f681lb = this.loadBalancerFactory.newLoadBalancer(lbHelperImpl);
                this.lbHelper = lbHelperImpl;
                this.nameResolver.start((Listener2) new NameResolverListener(lbHelperImpl, this.nameResolver));
                this.nameResolverStarted = true;
            }
        }
    }

    /* access modifiers changed from: private */
    public void enterIdleMode() {
        shutdownNameResolverAndLoadBalancer(true);
        this.delayedTransport.reprocess(null);
        this.channelLogger.log(ChannelLogLevel.INFO, "Entering IDLE state");
        this.channelStateManager.gotoState(ConnectivityState.IDLE);
        if (this.inUseStateAggregator.isInUse()) {
            exitIdleMode();
        }
    }

    /* access modifiers changed from: private */
    public void cancelIdleTimer(boolean z) {
        this.idleTimer.cancel(z);
    }

    /* access modifiers changed from: private */
    public void rescheduleIdleTimer() {
        long j = this.idleTimeoutMillis;
        if (j != -1) {
            this.idleTimer.reschedule(j, TimeUnit.MILLISECONDS);
        }
    }

    private void cancelNameResolverBackoff() {
        this.syncContext.throwIfNotInThisSynchronizationContext();
        ScheduledHandle scheduledHandle = this.scheduledNameResolverRefresh;
        if (scheduledHandle != null) {
            scheduledHandle.cancel();
            this.scheduledNameResolverRefresh = null;
            this.nameResolverBackoffPolicy = null;
        }
    }

    /* access modifiers changed from: private */
    public void refreshAndResetNameResolution() {
        this.syncContext.throwIfNotInThisSynchronizationContext();
        cancelNameResolverBackoff();
        refreshNameResolution();
    }

    /* access modifiers changed from: private */
    public void refreshNameResolution() {
        this.syncContext.throwIfNotInThisSynchronizationContext();
        if (this.nameResolverStarted) {
            this.nameResolver.refresh();
        }
    }

    ManagedChannelImpl(AbstractManagedChannelImplBuilder<?> abstractManagedChannelImplBuilder, ClientTransportFactory clientTransportFactory, Provider provider, ObjectPool<? extends Executor> objectPool, Supplier<Stopwatch> supplier, List<ClientInterceptor> list, TimeProvider timeProvider2) {
        AbstractManagedChannelImplBuilder<?> abstractManagedChannelImplBuilder2 = abstractManagedChannelImplBuilder;
        ObjectPool<? extends Executor> objectPool2 = objectPool;
        final TimeProvider timeProvider3 = timeProvider2;
        boolean z = true;
        this.oobChannels = new HashSet(1, 0.75f);
        this.uncommittedRetriableStreamsRegistry = new UncommittedRetriableStreamsRegistry();
        this.shutdown = new AtomicBoolean(false);
        this.terminatedLatch = new CountDownLatch(1);
        this.waitingForServiceConfig = true;
        this.channelBufferUsed = new ChannelBufferMeter();
        this.delayedTransportListener = new DelayedTransportListener();
        this.inUseStateAggregator = new IdleModeStateAggregator();
        this.transportProvider = new ChannelTransportProvider();
        this.target = (String) Preconditions.checkNotNull(abstractManagedChannelImplBuilder2.target, "target");
        this.logId = InternalLogId.allocate("Channel", this.target);
        this.nameResolverFactory = abstractManagedChannelImplBuilder.getNameResolverFactory();
        ProxyDetector defaultProxyDetector = abstractManagedChannelImplBuilder2.proxyDetector != null ? abstractManagedChannelImplBuilder2.proxyDetector : GrpcUtil.getDefaultProxyDetector();
        this.retryEnabled = abstractManagedChannelImplBuilder2.retryEnabled && !abstractManagedChannelImplBuilder2.temporarilyDisableRetry;
        this.loadBalancerFactory = new AutoConfiguredLoadBalancerFactory(abstractManagedChannelImplBuilder2.defaultLbPolicy);
        this.nameResolverArgs = Args.newBuilder().setDefaultPort(abstractManagedChannelImplBuilder.getDefaultPort()).setProxyDetector(defaultProxyDetector).setSynchronizationContext(this.syncContext).setServiceConfigParser(new ScParser(this.retryEnabled, abstractManagedChannelImplBuilder2.maxRetryAttempts, abstractManagedChannelImplBuilder2.maxHedgedAttempts, this.loadBalancerFactory)).build();
        this.nameResolver = getNameResolver(this.target, this.nameResolverFactory, this.nameResolverArgs);
        this.timeProvider = (TimeProvider) Preconditions.checkNotNull(timeProvider3, "timeProvider");
        this.maxTraceEvents = abstractManagedChannelImplBuilder2.maxTraceEvents;
        InternalLogId internalLogId = this.logId;
        int i = abstractManagedChannelImplBuilder2.maxTraceEvents;
        long currentTimeNanos = timeProvider2.currentTimeNanos();
        StringBuilder sb = new StringBuilder();
        sb.append("Channel for '");
        sb.append(this.target);
        sb.append("'");
        ChannelTracer channelTracer2 = new ChannelTracer(internalLogId, i, currentTimeNanos, sb.toString());
        this.channelTracer = channelTracer2;
        this.channelLogger = new ChannelLoggerImpl(this.channelTracer, timeProvider3);
        this.executorPool = (ObjectPool) Preconditions.checkNotNull(abstractManagedChannelImplBuilder2.executorPool, "executorPool");
        this.balancerRpcExecutorPool = (ObjectPool) Preconditions.checkNotNull(objectPool2, "balancerRpcExecutorPool");
        this.balancerRpcExecutorHolder = new ExecutorHolder(objectPool2);
        this.executor = (Executor) Preconditions.checkNotNull(this.executorPool.getObject(), "executor");
        this.delayedTransport = new DelayedClientTransport(this.executor, this.syncContext);
        this.delayedTransport.start(this.delayedTransportListener);
        this.backoffPolicyProvider = provider;
        this.transportFactory = new CallCredentialsApplyingTransportFactory(clientTransportFactory, this.executor);
        this.scheduledExecutorForBalancer = new ScheduledExecutorForBalancer(this.transportFactory.getScheduledExecutorService());
        this.serviceConfigInterceptor = new ServiceConfigInterceptor(this.retryEnabled, abstractManagedChannelImplBuilder2.maxRetryAttempts, abstractManagedChannelImplBuilder2.maxHedgedAttempts);
        this.defaultServiceConfig = abstractManagedChannelImplBuilder2.defaultServiceConfig;
        this.lastServiceConfig = this.defaultServiceConfig;
        this.lookUpServiceConfig = abstractManagedChannelImplBuilder2.lookUpServiceConfig;
        Channel intercept = ClientInterceptors.intercept((Channel) new RealChannel(this.nameResolver.getServiceAuthority()), this.serviceConfigInterceptor);
        if (abstractManagedChannelImplBuilder2.binlog != null) {
            intercept = abstractManagedChannelImplBuilder2.binlog.wrapChannel(intercept);
        }
        this.interceptorChannel = ClientInterceptors.intercept(intercept, list);
        this.stopwatchSupplier = (Supplier) Preconditions.checkNotNull(supplier, "stopwatchSupplier");
        if (abstractManagedChannelImplBuilder2.idleTimeoutMillis == -1) {
            this.idleTimeoutMillis = abstractManagedChannelImplBuilder2.idleTimeoutMillis;
        } else {
            if (abstractManagedChannelImplBuilder2.idleTimeoutMillis < AbstractManagedChannelImplBuilder.IDLE_MODE_MIN_TIMEOUT_MILLIS) {
                z = false;
            }
            Preconditions.checkArgument(z, "invalid idleTimeoutMillis %s", abstractManagedChannelImplBuilder2.idleTimeoutMillis);
            this.idleTimeoutMillis = abstractManagedChannelImplBuilder2.idleTimeoutMillis;
        }
        this.idleTimer = new Rescheduler(new IdleModeTimer(), this.syncContext, this.transportFactory.getScheduledExecutorService(), (Stopwatch) supplier.get());
        this.fullStreamDecompression = abstractManagedChannelImplBuilder2.fullStreamDecompression;
        this.decompressorRegistry = (DecompressorRegistry) Preconditions.checkNotNull(abstractManagedChannelImplBuilder2.decompressorRegistry, "decompressorRegistry");
        this.compressorRegistry = (CompressorRegistry) Preconditions.checkNotNull(abstractManagedChannelImplBuilder2.compressorRegistry, "compressorRegistry");
        this.userAgent = abstractManagedChannelImplBuilder2.userAgent;
        this.channelBufferLimit = abstractManagedChannelImplBuilder2.retryBufferSize;
        this.perRpcBufferLimit = abstractManagedChannelImplBuilder2.perRpcBufferLimit;
        this.callTracerFactory = new Factory() {
            public CallTracer create() {
                return new CallTracer(timeProvider3);
            }
        };
        this.channelCallTracer = this.callTracerFactory.create();
        this.channelz = (InternalChannelz) Preconditions.checkNotNull(abstractManagedChannelImplBuilder2.channelz);
        this.channelz.addRootChannel(this);
        if (!this.lookUpServiceConfig) {
            if (this.defaultServiceConfig != null) {
                this.channelLogger.log(ChannelLogLevel.INFO, "Service config look-up disabled, using default service config");
            }
            handleServiceConfigUpdate();
        }
    }

    /* access modifiers changed from: private */
    public void handleServiceConfigUpdate() {
        this.waitingForServiceConfig = false;
        this.serviceConfigInterceptor.handleUpdate(this.lastServiceConfig);
        if (this.retryEnabled) {
            this.throttle = ServiceConfigUtil.getThrottlePolicy(this.lastServiceConfig);
        }
    }

    @VisibleForTesting
    static NameResolver getNameResolver(String str, NameResolver.Factory factory, Args args) {
        URI uri;
        StringBuilder sb = new StringBuilder();
        try {
            uri = new URI(str);
        } catch (URISyntaxException e) {
            sb.append(e.getMessage());
            uri = null;
        }
        if (uri != null) {
            NameResolver newNameResolver = factory.newNameResolver(uri, args);
            if (newNameResolver != null) {
                return newNameResolver;
            }
        }
        String str2 = "";
        if (!URI_PATTERN.matcher(str).matches()) {
            try {
                String defaultScheme = factory.getDefaultScheme();
                StringBuilder sb2 = new StringBuilder();
                sb2.append("/");
                sb2.append(str);
                NameResolver newNameResolver2 = factory.newNameResolver(new URI(defaultScheme, str2, sb2.toString(), null), args);
                if (newNameResolver2 != null) {
                    return newNameResolver2;
                }
            } catch (URISyntaxException e2) {
                throw new IllegalArgumentException(e2);
            }
        }
        Object[] objArr = new Object[2];
        objArr[0] = str;
        if (sb.length() > 0) {
            StringBuilder sb3 = new StringBuilder();
            sb3.append(" (");
            sb3.append(sb);
            sb3.append(")");
            str2 = sb3.toString();
        }
        objArr[1] = str2;
        throw new IllegalArgumentException(String.format("cannot find a NameResolver for %s%s", objArr));
    }

    public ManagedChannelImpl shutdown() {
        this.channelLogger.log(ChannelLogLevel.DEBUG, "shutdown() called");
        if (!this.shutdown.compareAndSet(false, true)) {
            return this;
        }
        this.syncContext.executeLater(new Runnable() {
            public void run() {
                ManagedChannelImpl.this.channelLogger.log(ChannelLogLevel.INFO, "Entering SHUTDOWN state");
                ManagedChannelImpl.this.channelStateManager.gotoState(ConnectivityState.SHUTDOWN);
            }
        });
        this.uncommittedRetriableStreamsRegistry.onShutdown(SHUTDOWN_STATUS);
        this.syncContext.execute(new Runnable() {
            public void run() {
                ManagedChannelImpl.this.cancelIdleTimer(true);
            }
        });
        return this;
    }

    public ManagedChannelImpl shutdownNow() {
        this.channelLogger.log(ChannelLogLevel.DEBUG, "shutdownNow() called");
        shutdown();
        this.uncommittedRetriableStreamsRegistry.onShutdownNow(SHUTDOWN_NOW_STATUS);
        this.syncContext.execute(new Runnable() {
            public void run() {
                if (!ManagedChannelImpl.this.shutdownNowed) {
                    ManagedChannelImpl.this.shutdownNowed = true;
                    ManagedChannelImpl.this.maybeShutdownNowSubchannels();
                }
            }
        });
        return this;
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public void panic(final Throwable th) {
        if (!this.panicMode) {
            this.panicMode = true;
            cancelIdleTimer(true);
            shutdownNameResolverAndLoadBalancer(false);
            updateSubchannelPicker(new SubchannelPicker() {
                private final PickResult panicPickResult = PickResult.withDrop(Status.INTERNAL.withDescription("Panic! This is a bug!").withCause(th));

                public PickResult pickSubchannel(PickSubchannelArgs pickSubchannelArgs) {
                    return this.panicPickResult;
                }
            });
            this.channelLogger.log(ChannelLogLevel.ERROR, "PANIC! Entering TRANSIENT_FAILURE");
            this.channelStateManager.gotoState(ConnectivityState.TRANSIENT_FAILURE);
        }
    }

    /* access modifiers changed from: private */
    public void updateSubchannelPicker(SubchannelPicker subchannelPicker2) {
        this.subchannelPicker = subchannelPicker2;
        this.delayedTransport.reprocess(subchannelPicker2);
    }

    public boolean isShutdown() {
        return this.shutdown.get();
    }

    public boolean awaitTermination(long j, TimeUnit timeUnit) throws InterruptedException {
        return this.terminatedLatch.await(j, timeUnit);
    }

    public boolean isTerminated() {
        return this.terminated;
    }

    public <ReqT, RespT> ClientCall<ReqT, RespT> newCall(MethodDescriptor<ReqT, RespT> methodDescriptor, CallOptions callOptions) {
        return this.interceptorChannel.newCall(methodDescriptor, callOptions);
    }

    public String authority() {
        return this.interceptorChannel.authority();
    }

    /* access modifiers changed from: private */
    public Executor getCallExecutor(CallOptions callOptions) {
        Executor executor2 = callOptions.getExecutor();
        return executor2 == null ? this.executor : executor2;
    }

    /* access modifiers changed from: private */
    public void maybeTerminateChannel() {
        if (!this.terminated && this.shutdown.get() && this.subchannels.isEmpty() && this.oobChannels.isEmpty()) {
            this.channelLogger.log(ChannelLogLevel.INFO, "Terminated");
            this.channelz.removeRootChannel(this);
            this.terminated = true;
            this.terminatedLatch.countDown();
            this.executorPool.returnObject(this.executor);
            this.balancerRpcExecutorHolder.release();
            this.transportFactory.close();
        }
    }

    public ConnectivityState getState(boolean z) {
        ConnectivityState state = this.channelStateManager.getState();
        if (z && state == ConnectivityState.IDLE) {
            this.syncContext.execute(new Runnable() {
                public void run() {
                    ManagedChannelImpl.this.exitIdleMode();
                    if (ManagedChannelImpl.this.subchannelPicker != null) {
                        ManagedChannelImpl.this.subchannelPicker.requestConnection();
                    }
                }
            });
        }
        return state;
    }

    public void notifyWhenStateChanged(final ConnectivityState connectivityState, final Runnable runnable) {
        this.syncContext.execute(new Runnable() {
            public void run() {
                ManagedChannelImpl.this.channelStateManager.notifyWhenStateChanged(runnable, ManagedChannelImpl.this.executor, connectivityState);
            }
        });
    }

    public void resetConnectBackoff() {
        this.syncContext.execute(new Runnable() {
            public void run() {
                if (!ManagedChannelImpl.this.shutdown.get()) {
                    if (ManagedChannelImpl.this.scheduledNameResolverRefresh != null && ManagedChannelImpl.this.scheduledNameResolverRefresh.isPending()) {
                        Preconditions.checkState(ManagedChannelImpl.this.nameResolverStarted, "name resolver must be started");
                        ManagedChannelImpl.this.refreshAndResetNameResolution();
                    }
                    for (InternalSubchannel resetConnectBackoff : ManagedChannelImpl.this.subchannels) {
                        resetConnectBackoff.resetConnectBackoff();
                    }
                    for (OobChannel resetConnectBackoff2 : ManagedChannelImpl.this.oobChannels) {
                        resetConnectBackoff2.resetConnectBackoff();
                    }
                }
            }
        });
    }

    public void enterIdle() {
        this.syncContext.execute(new Runnable() {
            public void run() {
                if (!ManagedChannelImpl.this.shutdown.get() && ManagedChannelImpl.this.lbHelper != null) {
                    ManagedChannelImpl.this.cancelIdleTimer(false);
                    ManagedChannelImpl.this.enterIdleMode();
                }
            }
        });
    }

    public String toString() {
        return MoreObjects.toStringHelper((Object) this).add("logId", this.logId.getId()).add("target", (Object) this.target).toString();
    }

    /* access modifiers changed from: private */
    public void logWarningIfNotInSyncContext(String str) {
        try {
            this.syncContext.throwIfNotInThisSynchronizationContext();
        } catch (IllegalStateException e) {
            Logger logger2 = logger;
            Level level = Level.WARNING;
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(" should be called from SynchronizationContext. This warning will become an exception in a future release. See https://github.com/grpc/grpc-java/issues/5015 for more details");
            logger2.log(level, sb.toString(), e);
        }
    }
}
