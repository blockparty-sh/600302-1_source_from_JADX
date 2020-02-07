package p015io.grpc.internal;

import androidx.core.app.NotificationCompat;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;
import com.google.common.util.concurrent.MoreExecutors;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import p015io.grpc.Attributes;
import p015io.grpc.Codec.Identity;
import p015io.grpc.Compressor;
import p015io.grpc.CompressorRegistry;
import p015io.grpc.Context;
import p015io.grpc.Context.CancellableContext;
import p015io.grpc.Context.CancellationListener;
import p015io.grpc.DecompressorRegistry;
import p015io.grpc.InternalDecompressorRegistry;
import p015io.grpc.Metadata;
import p015io.grpc.MethodDescriptor;
import p015io.grpc.ServerCall;
import p015io.grpc.ServerCall.Listener;
import p015io.grpc.Status;
import p015io.grpc.internal.StreamListener.MessageProducer;
import p015io.grpc.perfmark.PerfMark;
import p015io.grpc.perfmark.PerfTag;

/* renamed from: io.grpc.internal.ServerCallImpl */
final class ServerCallImpl<ReqT, RespT> extends ServerCall<ReqT, RespT> {
    @VisibleForTesting
    static final String MISSING_RESPONSE = "Completed without a response";
    @VisibleForTesting
    static final String TOO_MANY_RESPONSES = "Too many responses";
    private static final Logger log = Logger.getLogger(ServerCallImpl.class.getName());
    /* access modifiers changed from: private */
    public volatile boolean cancelled;
    private boolean closeCalled;
    private Compressor compressor;
    private final CompressorRegistry compressorRegistry;
    private final CancellableContext context;
    private final DecompressorRegistry decompressorRegistry;
    private final byte[] messageAcceptEncoding;
    private boolean messageSent;
    /* access modifiers changed from: private */
    public final MethodDescriptor<ReqT, RespT> method;
    private boolean sendHeadersCalled;
    private CallTracer serverCallTracer;
    private final ServerStream stream;
    /* access modifiers changed from: private */
    public final PerfTag tag;

    @VisibleForTesting
    /* renamed from: io.grpc.internal.ServerCallImpl$ServerStreamListenerImpl */
    static final class ServerStreamListenerImpl<ReqT> implements ServerStreamListener {
        /* access modifiers changed from: private */
        public final ServerCallImpl<ReqT, ?> call;
        private final CancellableContext context;
        private final Listener<ReqT> listener;

        public ServerStreamListenerImpl(ServerCallImpl<ReqT, ?> serverCallImpl, Listener<ReqT> listener2, CancellableContext cancellableContext) {
            this.call = (ServerCallImpl) Preconditions.checkNotNull(serverCallImpl, NotificationCompat.CATEGORY_CALL);
            this.listener = (Listener) Preconditions.checkNotNull(listener2, "listener must not be null");
            this.context = (CancellableContext) Preconditions.checkNotNull(cancellableContext, "context");
            this.context.addListener(new CancellationListener() {
                public void cancelled(Context context) {
                    ServerStreamListenerImpl.this.call.cancelled = true;
                }
            }, MoreExecutors.directExecutor());
        }

        public void messagesAvailable(MessageProducer messageProducer) {
            InputStream next;
            if (this.call.cancelled) {
                GrpcUtil.closeQuietly(messageProducer);
                return;
            }
            String str = "ServerCall.messagesAvailable";
            PerfMark.taskStart(this.call.tag, str);
            while (true) {
                try {
                    next = messageProducer.next();
                    if (next != null) {
                        this.listener.onMessage(this.call.method.parseRequest(next));
                        next.close();
                    } else {
                        PerfMark.taskEnd(this.call.tag, str);
                        return;
                    }
                } catch (Throwable th) {
                    try {
                        GrpcUtil.closeQuietly(messageProducer);
                        Throwables.throwIfUnchecked(th);
                        throw new RuntimeException(th);
                    } catch (Throwable th2) {
                        PerfMark.taskEnd(this.call.tag, str);
                        throw th2;
                    }
                }
            }
        }

        public void halfClosed() {
            if (!this.call.cancelled) {
                String str = "ServerCall.halfClosed";
                PerfMark.taskStart(this.call.tag, str);
                try {
                    this.listener.onHalfClose();
                } finally {
                    PerfMark.taskEnd(this.call.tag, str);
                }
            }
        }

        public void closed(Status status) {
            String str = "ServerCall.closed";
            PerfMark.taskStart(this.call.tag, str);
            try {
                if (status.isOk()) {
                    this.listener.onComplete();
                } else {
                    this.call.cancelled = true;
                    this.listener.onCancel();
                }
                this.context.cancel(null);
                PerfMark.taskEnd(this.call.tag, str);
            } catch (Throwable th) {
                PerfMark.taskEnd(this.call.tag, str);
                throw th;
            }
        }

        public void onReady() {
            if (!this.call.cancelled) {
                String str = "ServerCall.closed";
                PerfMark.taskStart(this.call.tag, str);
                try {
                    this.listener.onReady();
                } finally {
                    PerfMark.taskEnd(this.call.tag, str);
                }
            }
        }
    }

    ServerCallImpl(ServerStream serverStream, MethodDescriptor<ReqT, RespT> methodDescriptor, Metadata metadata, CancellableContext cancellableContext, DecompressorRegistry decompressorRegistry2, CompressorRegistry compressorRegistry2, CallTracer callTracer) {
        this.stream = serverStream;
        this.method = methodDescriptor;
        this.tag = PerfMark.createTag(methodDescriptor.getFullMethodName());
        this.context = cancellableContext;
        this.messageAcceptEncoding = (byte[]) metadata.get(GrpcUtil.MESSAGE_ACCEPT_ENCODING_KEY);
        this.decompressorRegistry = decompressorRegistry2;
        this.compressorRegistry = compressorRegistry2;
        this.serverCallTracer = callTracer;
        this.serverCallTracer.reportCallStarted();
    }

    public void request(int i) {
        this.stream.request(i);
    }

    public void sendHeaders(Metadata metadata) {
        String str = "ServerCall.sendHeaders";
        PerfMark.taskStart(this.tag, str);
        try {
            sendHeadersInternal(metadata);
        } finally {
            PerfMark.taskEnd(this.tag, str);
        }
    }

    private void sendHeadersInternal(Metadata metadata) {
        Preconditions.checkState(!this.sendHeadersCalled, "sendHeaders has already been called");
        Preconditions.checkState(!this.closeCalled, "call is closed");
        metadata.discardAll(GrpcUtil.MESSAGE_ENCODING_KEY);
        if (this.compressor == null) {
            this.compressor = Identity.NONE;
        } else if (this.messageAcceptEncoding == null) {
            this.compressor = Identity.NONE;
        } else if (!GrpcUtil.iterableContains(GrpcUtil.ACCEPT_ENCODING_SPLITTER.split(new String(this.messageAcceptEncoding, GrpcUtil.US_ASCII)), this.compressor.getMessageEncoding())) {
            this.compressor = Identity.NONE;
        }
        metadata.put(GrpcUtil.MESSAGE_ENCODING_KEY, this.compressor.getMessageEncoding());
        this.stream.setCompressor(this.compressor);
        metadata.discardAll(GrpcUtil.MESSAGE_ACCEPT_ENCODING_KEY);
        byte[] rawAdvertisedMessageEncodings = InternalDecompressorRegistry.getRawAdvertisedMessageEncodings(this.decompressorRegistry);
        if (rawAdvertisedMessageEncodings.length != 0) {
            metadata.put(GrpcUtil.MESSAGE_ACCEPT_ENCODING_KEY, rawAdvertisedMessageEncodings);
        }
        this.sendHeadersCalled = true;
        this.stream.writeHeaders(metadata);
    }

    public void sendMessage(RespT respt) {
        String str = "ServerCall.sendMessage";
        PerfMark.taskStart(this.tag, str);
        try {
            sendMessageInternal(respt);
        } finally {
            PerfMark.taskEnd(this.tag, str);
        }
    }

    private void sendMessageInternal(RespT respt) {
        Preconditions.checkState(this.sendHeadersCalled, "sendHeaders has not been called");
        Preconditions.checkState(!this.closeCalled, "call is closed");
        if (!this.method.getType().serverSendsOneMessage() || !this.messageSent) {
            this.messageSent = true;
            try {
                this.stream.writeMessage(this.method.streamResponse(respt));
                this.stream.flush();
            } catch (RuntimeException e) {
                close(Status.fromThrowable(e), new Metadata());
            } catch (Error e2) {
                close(Status.CANCELLED.withDescription("Server sendMessage() failed with Error"), new Metadata());
                throw e2;
            }
            return;
        }
        internalClose(Status.INTERNAL.withDescription(TOO_MANY_RESPONSES));
    }

    public void setMessageCompression(boolean z) {
        this.stream.setMessageCompression(z);
    }

    public void setCompression(String str) {
        boolean z = true;
        Preconditions.checkState(!this.sendHeadersCalled, "sendHeaders has been called");
        this.compressor = this.compressorRegistry.lookupCompressor(str);
        if (this.compressor == null) {
            z = false;
        }
        Preconditions.checkArgument(z, "Unable to find compressor by name %s", (Object) str);
    }

    public boolean isReady() {
        return this.stream.isReady();
    }

    public void close(Status status, Metadata metadata) {
        String str = "ServerCall.close";
        PerfMark.taskStart(this.tag, str);
        try {
            closeInternal(status, metadata);
        } finally {
            PerfMark.taskEnd(this.tag, str);
        }
    }

    private void closeInternal(Status status, Metadata metadata) {
        Preconditions.checkState(!this.closeCalled, "call already closed");
        try {
            this.closeCalled = true;
            if (!status.isOk() || !this.method.getType().serverSendsOneMessage() || this.messageSent) {
                this.stream.close(status, metadata);
                this.serverCallTracer.reportCallEnded(status.isOk());
                return;
            }
            internalClose(Status.INTERNAL.withDescription(MISSING_RESPONSE));
        } finally {
            this.serverCallTracer.reportCallEnded(status.isOk());
        }
    }

    public boolean isCancelled() {
        return this.cancelled;
    }

    /* access modifiers changed from: 0000 */
    public ServerStreamListener newServerStreamListener(Listener<ReqT> listener) {
        return new ServerStreamListenerImpl(this, listener, this.context);
    }

    public Attributes getAttributes() {
        return this.stream.getAttributes();
    }

    public String getAuthority() {
        return this.stream.getAuthority();
    }

    public MethodDescriptor<ReqT, RespT> getMethodDescriptor() {
        return this.method;
    }

    private void internalClose(Status status) {
        log.log(Level.WARNING, "Cancelling the stream with status {0}", new Object[]{status});
        this.stream.cancel(status);
        this.serverCallTracer.reportCallEnded(status.isOk());
    }
}
