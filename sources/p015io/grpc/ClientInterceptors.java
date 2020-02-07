package p015io.grpc;

import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import p015io.grpc.ClientCall.Listener;
import p015io.grpc.MethodDescriptor.Marshaller;

/* renamed from: io.grpc.ClientInterceptors */
public class ClientInterceptors {
    /* access modifiers changed from: private */
    public static final ClientCall<Object, Object> NOOP_CALL = new ClientCall<Object, Object>() {
        public void cancel(String str, Throwable th) {
        }

        public void halfClose() {
        }

        public boolean isReady() {
            return false;
        }

        public void request(int i) {
        }

        public void sendMessage(Object obj) {
        }

        public void start(Listener<Object> listener, Metadata metadata) {
        }
    };

    /* renamed from: io.grpc.ClientInterceptors$CheckedForwardingClientCall */
    public static abstract class CheckedForwardingClientCall<ReqT, RespT> extends ForwardingClientCall<ReqT, RespT> {
        private ClientCall<ReqT, RespT> delegate;

        /* access modifiers changed from: protected */
        public abstract void checkedStart(Listener<RespT> listener, Metadata metadata) throws Exception;

        protected CheckedForwardingClientCall(ClientCall<ReqT, RespT> clientCall) {
            this.delegate = clientCall;
        }

        /* access modifiers changed from: protected */
        public final ClientCall<ReqT, RespT> delegate() {
            return this.delegate;
        }

        public final void start(Listener<RespT> listener, Metadata metadata) {
            try {
                checkedStart(listener, metadata);
            } catch (Exception e) {
                this.delegate = ClientInterceptors.NOOP_CALL;
                listener.onClose(Status.fromThrowable(e), new Metadata());
            }
        }
    }

    /* renamed from: io.grpc.ClientInterceptors$InterceptorChannel */
    private static class InterceptorChannel extends Channel {
        private final Channel channel;
        private final ClientInterceptor interceptor;

        private InterceptorChannel(Channel channel2, ClientInterceptor clientInterceptor) {
            this.channel = channel2;
            this.interceptor = (ClientInterceptor) Preconditions.checkNotNull(clientInterceptor, "interceptor");
        }

        public <ReqT, RespT> ClientCall<ReqT, RespT> newCall(MethodDescriptor<ReqT, RespT> methodDescriptor, CallOptions callOptions) {
            return this.interceptor.interceptCall(methodDescriptor, callOptions, this.channel);
        }

        public String authority() {
            return this.channel.authority();
        }
    }

    private ClientInterceptors() {
    }

    public static Channel interceptForward(Channel channel, ClientInterceptor... clientInterceptorArr) {
        return interceptForward(channel, Arrays.asList(clientInterceptorArr));
    }

    public static Channel interceptForward(Channel channel, List<? extends ClientInterceptor> list) {
        ArrayList arrayList = new ArrayList(list);
        Collections.reverse(arrayList);
        return intercept(channel, (List<? extends ClientInterceptor>) arrayList);
    }

    public static Channel intercept(Channel channel, ClientInterceptor... clientInterceptorArr) {
        return intercept(channel, Arrays.asList(clientInterceptorArr));
    }

    public static Channel intercept(Channel channel, List<? extends ClientInterceptor> list) {
        Preconditions.checkNotNull(channel, "channel");
        for (ClientInterceptor interceptorChannel : list) {
            channel = new InterceptorChannel(channel, interceptorChannel);
        }
        return channel;
    }

    static <WReqT, WRespT> ClientInterceptor wrapClientInterceptor(final ClientInterceptor clientInterceptor, final Marshaller<WReqT> marshaller, final Marshaller<WRespT> marshaller2) {
        return new ClientInterceptor() {
            public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(final MethodDescriptor<ReqT, RespT> methodDescriptor, CallOptions callOptions, Channel channel) {
                final ClientCall interceptCall = clientInterceptor.interceptCall(methodDescriptor.toBuilder(Marshaller.this, marshaller2).build(), callOptions, channel);
                return new PartialForwardingClientCall<ReqT, RespT>() {
                    public void start(final Listener<RespT> listener, Metadata metadata) {
                        interceptCall.start(new PartialForwardingClientCallListener<WRespT>() {
                            public void onMessage(WRespT wrespt) {
                                listener.onMessage(methodDescriptor.getResponseMarshaller().parse(marshaller2.stream(wrespt)));
                            }

                            /* access modifiers changed from: protected */
                            public Listener<?> delegate() {
                                return listener;
                            }
                        }, metadata);
                    }

                    public void sendMessage(ReqT reqt) {
                        interceptCall.sendMessage(Marshaller.this.parse(methodDescriptor.getRequestMarshaller().stream(reqt)));
                    }

                    /* access modifiers changed from: protected */
                    public ClientCall<?, ?> delegate() {
                        return interceptCall;
                    }
                };
            }
        };
    }
}
