package com.bitcoin.mwallet;

import com.bitcoin.mwallet.Featuredbusiness.FeaturedBusinessRequest;
import com.bitcoin.mwallet.Featuredbusiness.FeaturedBusinessResponse;
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

public final class FeaturedBusinessServiceGrpc {
    private static final int METHODID_GET_FEATURED_BUSINESSS = 0;
    public static final String SERVICE_NAME = "com.bitcoin.mwallet.FeaturedBusinessService";
    private static volatile MethodDescriptor<FeaturedBusinessRequest, FeaturedBusinessResponse> getGetFeaturedBusinesssMethod;
    private static volatile ServiceDescriptor serviceDescriptor;

    public static final class FeaturedBusinessServiceBlockingStub extends AbstractStub<FeaturedBusinessServiceBlockingStub> {
        private FeaturedBusinessServiceBlockingStub(Channel channel) {
            super(channel);
        }

        private FeaturedBusinessServiceBlockingStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        /* access modifiers changed from: protected */
        public FeaturedBusinessServiceBlockingStub build(Channel channel, CallOptions callOptions) {
            return new FeaturedBusinessServiceBlockingStub(channel, callOptions);
        }

        public FeaturedBusinessResponse getFeaturedBusinesss(FeaturedBusinessRequest featuredBusinessRequest) {
            return (FeaturedBusinessResponse) ClientCalls.blockingUnaryCall(getChannel(), FeaturedBusinessServiceGrpc.getGetFeaturedBusinesssMethod(), getCallOptions(), featuredBusinessRequest);
        }
    }

    public static final class FeaturedBusinessServiceFutureStub extends AbstractStub<FeaturedBusinessServiceFutureStub> {
        private FeaturedBusinessServiceFutureStub(Channel channel) {
            super(channel);
        }

        private FeaturedBusinessServiceFutureStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        /* access modifiers changed from: protected */
        public FeaturedBusinessServiceFutureStub build(Channel channel, CallOptions callOptions) {
            return new FeaturedBusinessServiceFutureStub(channel, callOptions);
        }

        public ListenableFuture<FeaturedBusinessResponse> getFeaturedBusinesss(FeaturedBusinessRequest featuredBusinessRequest) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(FeaturedBusinessServiceGrpc.getGetFeaturedBusinesssMethod(), getCallOptions()), featuredBusinessRequest);
        }
    }

    public static abstract class FeaturedBusinessServiceImplBase implements BindableService {
        public void getFeaturedBusinesss(FeaturedBusinessRequest featuredBusinessRequest, StreamObserver<FeaturedBusinessResponse> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(FeaturedBusinessServiceGrpc.getGetFeaturedBusinesssMethod(), streamObserver);
        }

        public final ServerServiceDefinition bindService() {
            return ServerServiceDefinition.builder(FeaturedBusinessServiceGrpc.getServiceDescriptor()).addMethod(FeaturedBusinessServiceGrpc.getGetFeaturedBusinesssMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 0))).build();
        }
    }

    public static final class FeaturedBusinessServiceStub extends AbstractStub<FeaturedBusinessServiceStub> {
        private FeaturedBusinessServiceStub(Channel channel) {
            super(channel);
        }

        private FeaturedBusinessServiceStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        /* access modifiers changed from: protected */
        public FeaturedBusinessServiceStub build(Channel channel, CallOptions callOptions) {
            return new FeaturedBusinessServiceStub(channel, callOptions);
        }

        public void getFeaturedBusinesss(FeaturedBusinessRequest featuredBusinessRequest, StreamObserver<FeaturedBusinessResponse> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(FeaturedBusinessServiceGrpc.getGetFeaturedBusinesssMethod(), getCallOptions()), featuredBusinessRequest, streamObserver);
        }
    }

    private static final class MethodHandlers<Req, Resp> implements UnaryMethod<Req, Resp>, ServerStreamingMethod<Req, Resp>, ClientStreamingMethod<Req, Resp>, BidiStreamingMethod<Req, Resp> {
        private final int methodId;
        private final FeaturedBusinessServiceImplBase serviceImpl;

        MethodHandlers(FeaturedBusinessServiceImplBase featuredBusinessServiceImplBase, int i) {
            this.serviceImpl = featuredBusinessServiceImplBase;
            this.methodId = i;
        }

        public void invoke(Req req, StreamObserver<Resp> streamObserver) {
            if (this.methodId == 0) {
                this.serviceImpl.getFeaturedBusinesss((FeaturedBusinessRequest) req, streamObserver);
                return;
            }
            throw new AssertionError();
        }

        public StreamObserver<Req> invoke(StreamObserver<Resp> streamObserver) {
            int i = this.methodId;
            throw new AssertionError();
        }
    }

    private FeaturedBusinessServiceGrpc() {
    }

    @RpcMethod(fullMethodName = "com.bitcoin.mwallet.FeaturedBusinessService/getFeaturedBusinesss", methodType = MethodType.UNARY, requestType = FeaturedBusinessRequest.class, responseType = FeaturedBusinessResponse.class)
    public static MethodDescriptor<FeaturedBusinessRequest, FeaturedBusinessResponse> getGetFeaturedBusinesssMethod() {
        MethodDescriptor<FeaturedBusinessRequest, FeaturedBusinessResponse> methodDescriptor = getGetFeaturedBusinesssMethod;
        if (methodDescriptor == null) {
            synchronized (FeaturedBusinessServiceGrpc.class) {
                methodDescriptor = getGetFeaturedBusinesssMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "getFeaturedBusinesss")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoLiteUtils.marshaller(FeaturedBusinessRequest.getDefaultInstance())).setResponseMarshaller(ProtoLiteUtils.marshaller(FeaturedBusinessResponse.getDefaultInstance())).build();
                    getGetFeaturedBusinesssMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static FeaturedBusinessServiceStub newStub(Channel channel) {
        return new FeaturedBusinessServiceStub(channel);
    }

    public static FeaturedBusinessServiceBlockingStub newBlockingStub(Channel channel) {
        return new FeaturedBusinessServiceBlockingStub(channel);
    }

    public static FeaturedBusinessServiceFutureStub newFutureStub(Channel channel) {
        return new FeaturedBusinessServiceFutureStub(channel);
    }

    public static ServiceDescriptor getServiceDescriptor() {
        ServiceDescriptor serviceDescriptor2 = serviceDescriptor;
        if (serviceDescriptor2 == null) {
            synchronized (FeaturedBusinessServiceGrpc.class) {
                serviceDescriptor2 = serviceDescriptor;
                if (serviceDescriptor2 == null) {
                    serviceDescriptor2 = ServiceDescriptor.newBuilder(SERVICE_NAME).addMethod(getGetFeaturedBusinesssMethod()).build();
                    serviceDescriptor = serviceDescriptor2;
                }
            }
        }
        return serviceDescriptor2;
    }
}
