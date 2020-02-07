package com.bitcoin.mwallet;

import com.google.common.util.concurrent.ListenableFuture;
import p015io.grpc.BindableService;
import p015io.grpc.CallOptions;
import p015io.grpc.Channel;
import p015io.grpc.MethodDescriptor;
import p015io.grpc.MethodDescriptor.MethodType;
import p015io.grpc.ServerServiceDefinition;
import p015io.grpc.ServiceDescriptor;
import p015io.grpc.protobuf.lite.ProtoLiteUtils;
import p015io.grpc.stub.AbstractStub;
import p015io.grpc.stub.ClientCalls;
import p015io.grpc.stub.ServerCalls;
import p015io.grpc.stub.ServerCalls.BidiStreamingMethod;
import p015io.grpc.stub.ServerCalls.ClientStreamingMethod;
import p015io.grpc.stub.ServerCalls.ServerStreamingMethod;
import p015io.grpc.stub.ServerCalls.UnaryMethod;
import p015io.grpc.stub.StreamObserver;
import p015io.grpc.stub.annotations.RpcMethod;

public final class MetaTestServiceGrpc {
    private static final int METHODID_SHOW_MY_METADATA = 0;
    public static final String SERVICE_NAME = "com.bitcoin.mwallet.MetaTestService";
    private static volatile MethodDescriptor<ShowMe, YesShow> getShowMyMetadataMethod;
    private static volatile ServiceDescriptor serviceDescriptor;

    public static final class MetaTestServiceBlockingStub extends AbstractStub<MetaTestServiceBlockingStub> {
        private MetaTestServiceBlockingStub(Channel channel) {
            super(channel);
        }

        private MetaTestServiceBlockingStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        /* access modifiers changed from: protected */
        public MetaTestServiceBlockingStub build(Channel channel, CallOptions callOptions) {
            return new MetaTestServiceBlockingStub(channel, callOptions);
        }

        public YesShow showMyMetadata(ShowMe showMe) {
            return (YesShow) ClientCalls.blockingUnaryCall(getChannel(), MetaTestServiceGrpc.getShowMyMetadataMethod(), getCallOptions(), showMe);
        }
    }

    public static final class MetaTestServiceFutureStub extends AbstractStub<MetaTestServiceFutureStub> {
        private MetaTestServiceFutureStub(Channel channel) {
            super(channel);
        }

        private MetaTestServiceFutureStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        /* access modifiers changed from: protected */
        public MetaTestServiceFutureStub build(Channel channel, CallOptions callOptions) {
            return new MetaTestServiceFutureStub(channel, callOptions);
        }

        public ListenableFuture<YesShow> showMyMetadata(ShowMe showMe) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(MetaTestServiceGrpc.getShowMyMetadataMethod(), getCallOptions()), showMe);
        }
    }

    public static abstract class MetaTestServiceImplBase implements BindableService {
        public void showMyMetadata(ShowMe showMe, StreamObserver<YesShow> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(MetaTestServiceGrpc.getShowMyMetadataMethod(), streamObserver);
        }

        public final ServerServiceDefinition bindService() {
            return ServerServiceDefinition.builder(MetaTestServiceGrpc.getServiceDescriptor()).addMethod(MetaTestServiceGrpc.getShowMyMetadataMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 0))).build();
        }
    }

    public static final class MetaTestServiceStub extends AbstractStub<MetaTestServiceStub> {
        private MetaTestServiceStub(Channel channel) {
            super(channel);
        }

        private MetaTestServiceStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        /* access modifiers changed from: protected */
        public MetaTestServiceStub build(Channel channel, CallOptions callOptions) {
            return new MetaTestServiceStub(channel, callOptions);
        }

        public void showMyMetadata(ShowMe showMe, StreamObserver<YesShow> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(MetaTestServiceGrpc.getShowMyMetadataMethod(), getCallOptions()), showMe, streamObserver);
        }
    }

    private static final class MethodHandlers<Req, Resp> implements UnaryMethod<Req, Resp>, ServerStreamingMethod<Req, Resp>, ClientStreamingMethod<Req, Resp>, BidiStreamingMethod<Req, Resp> {
        private final int methodId;
        private final MetaTestServiceImplBase serviceImpl;

        MethodHandlers(MetaTestServiceImplBase metaTestServiceImplBase, int i) {
            this.serviceImpl = metaTestServiceImplBase;
            this.methodId = i;
        }

        public void invoke(Req req, StreamObserver<Resp> streamObserver) {
            if (this.methodId == 0) {
                this.serviceImpl.showMyMetadata((ShowMe) req, streamObserver);
                return;
            }
            throw new AssertionError();
        }

        public StreamObserver<Req> invoke(StreamObserver<Resp> streamObserver) {
            int i = this.methodId;
            throw new AssertionError();
        }
    }

    private MetaTestServiceGrpc() {
    }

    @RpcMethod(fullMethodName = "com.bitcoin.mwallet.MetaTestService/showMyMetadata", methodType = MethodType.UNARY, requestType = ShowMe.class, responseType = YesShow.class)
    public static MethodDescriptor<ShowMe, YesShow> getShowMyMetadataMethod() {
        MethodDescriptor<ShowMe, YesShow> methodDescriptor = getShowMyMetadataMethod;
        if (methodDescriptor == null) {
            synchronized (MetaTestServiceGrpc.class) {
                methodDescriptor = getShowMyMetadataMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "showMyMetadata")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoLiteUtils.marshaller(ShowMe.getDefaultInstance())).setResponseMarshaller(ProtoLiteUtils.marshaller(YesShow.getDefaultInstance())).build();
                    getShowMyMetadataMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MetaTestServiceStub newStub(Channel channel) {
        return new MetaTestServiceStub(channel);
    }

    public static MetaTestServiceBlockingStub newBlockingStub(Channel channel) {
        return new MetaTestServiceBlockingStub(channel);
    }

    public static MetaTestServiceFutureStub newFutureStub(Channel channel) {
        return new MetaTestServiceFutureStub(channel);
    }

    public static ServiceDescriptor getServiceDescriptor() {
        ServiceDescriptor serviceDescriptor2 = serviceDescriptor;
        if (serviceDescriptor2 == null) {
            synchronized (MetaTestServiceGrpc.class) {
                serviceDescriptor2 = serviceDescriptor;
                if (serviceDescriptor2 == null) {
                    serviceDescriptor2 = ServiceDescriptor.newBuilder(SERVICE_NAME).addMethod(getShowMyMetadataMethod()).build();
                    serviceDescriptor = serviceDescriptor2;
                }
            }
        }
        return serviceDescriptor2;
    }
}
