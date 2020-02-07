package com.bitcoin.mwallet;

import com.bitcoin.mwallet.Verifiedtoken.VerifiedTokenRequest;
import com.bitcoin.mwallet.Verifiedtoken.VerifiedTokenResponse;
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

public final class AssetServiceGrpc {
    private static final int METHODID_GET_VERIFIED_TOKEN = 0;
    public static final String SERVICE_NAME = "com.bitcoin.mwallet.AssetService";
    private static volatile MethodDescriptor<VerifiedTokenRequest, VerifiedTokenResponse> getGetVerifiedTokenMethod;
    private static volatile ServiceDescriptor serviceDescriptor;

    public static final class AssetServiceBlockingStub extends AbstractStub<AssetServiceBlockingStub> {
        private AssetServiceBlockingStub(Channel channel) {
            super(channel);
        }

        private AssetServiceBlockingStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        /* access modifiers changed from: protected */
        public AssetServiceBlockingStub build(Channel channel, CallOptions callOptions) {
            return new AssetServiceBlockingStub(channel, callOptions);
        }

        public VerifiedTokenResponse getVerifiedToken(VerifiedTokenRequest verifiedTokenRequest) {
            return (VerifiedTokenResponse) ClientCalls.blockingUnaryCall(getChannel(), AssetServiceGrpc.getGetVerifiedTokenMethod(), getCallOptions(), verifiedTokenRequest);
        }
    }

    public static final class AssetServiceFutureStub extends AbstractStub<AssetServiceFutureStub> {
        private AssetServiceFutureStub(Channel channel) {
            super(channel);
        }

        private AssetServiceFutureStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        /* access modifiers changed from: protected */
        public AssetServiceFutureStub build(Channel channel, CallOptions callOptions) {
            return new AssetServiceFutureStub(channel, callOptions);
        }

        public ListenableFuture<VerifiedTokenResponse> getVerifiedToken(VerifiedTokenRequest verifiedTokenRequest) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(AssetServiceGrpc.getGetVerifiedTokenMethod(), getCallOptions()), verifiedTokenRequest);
        }
    }

    public static abstract class AssetServiceImplBase implements BindableService {
        public void getVerifiedToken(VerifiedTokenRequest verifiedTokenRequest, StreamObserver<VerifiedTokenResponse> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(AssetServiceGrpc.getGetVerifiedTokenMethod(), streamObserver);
        }

        public final ServerServiceDefinition bindService() {
            return ServerServiceDefinition.builder(AssetServiceGrpc.getServiceDescriptor()).addMethod(AssetServiceGrpc.getGetVerifiedTokenMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 0))).build();
        }
    }

    public static final class AssetServiceStub extends AbstractStub<AssetServiceStub> {
        private AssetServiceStub(Channel channel) {
            super(channel);
        }

        private AssetServiceStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        /* access modifiers changed from: protected */
        public AssetServiceStub build(Channel channel, CallOptions callOptions) {
            return new AssetServiceStub(channel, callOptions);
        }

        public void getVerifiedToken(VerifiedTokenRequest verifiedTokenRequest, StreamObserver<VerifiedTokenResponse> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(AssetServiceGrpc.getGetVerifiedTokenMethod(), getCallOptions()), verifiedTokenRequest, streamObserver);
        }
    }

    private static final class MethodHandlers<Req, Resp> implements UnaryMethod<Req, Resp>, ServerStreamingMethod<Req, Resp>, ClientStreamingMethod<Req, Resp>, BidiStreamingMethod<Req, Resp> {
        private final int methodId;
        private final AssetServiceImplBase serviceImpl;

        MethodHandlers(AssetServiceImplBase assetServiceImplBase, int i) {
            this.serviceImpl = assetServiceImplBase;
            this.methodId = i;
        }

        public void invoke(Req req, StreamObserver<Resp> streamObserver) {
            if (this.methodId == 0) {
                this.serviceImpl.getVerifiedToken((VerifiedTokenRequest) req, streamObserver);
                return;
            }
            throw new AssertionError();
        }

        public StreamObserver<Req> invoke(StreamObserver<Resp> streamObserver) {
            int i = this.methodId;
            throw new AssertionError();
        }
    }

    private AssetServiceGrpc() {
    }

    @RpcMethod(fullMethodName = "com.bitcoin.mwallet.AssetService/getVerifiedToken", methodType = MethodType.UNARY, requestType = VerifiedTokenRequest.class, responseType = VerifiedTokenResponse.class)
    public static MethodDescriptor<VerifiedTokenRequest, VerifiedTokenResponse> getGetVerifiedTokenMethod() {
        MethodDescriptor<VerifiedTokenRequest, VerifiedTokenResponse> methodDescriptor = getGetVerifiedTokenMethod;
        if (methodDescriptor == null) {
            synchronized (AssetServiceGrpc.class) {
                methodDescriptor = getGetVerifiedTokenMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "getVerifiedToken")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoLiteUtils.marshaller(VerifiedTokenRequest.getDefaultInstance())).setResponseMarshaller(ProtoLiteUtils.marshaller(VerifiedTokenResponse.getDefaultInstance())).build();
                    getGetVerifiedTokenMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static AssetServiceStub newStub(Channel channel) {
        return new AssetServiceStub(channel);
    }

    public static AssetServiceBlockingStub newBlockingStub(Channel channel) {
        return new AssetServiceBlockingStub(channel);
    }

    public static AssetServiceFutureStub newFutureStub(Channel channel) {
        return new AssetServiceFutureStub(channel);
    }

    public static ServiceDescriptor getServiceDescriptor() {
        ServiceDescriptor serviceDescriptor2 = serviceDescriptor;
        if (serviceDescriptor2 == null) {
            synchronized (AssetServiceGrpc.class) {
                serviceDescriptor2 = serviceDescriptor;
                if (serviceDescriptor2 == null) {
                    serviceDescriptor2 = ServiceDescriptor.newBuilder(SERVICE_NAME).addMethod(getGetVerifiedTokenMethod()).build();
                    serviceDescriptor = serviceDescriptor2;
                }
            }
        }
        return serviceDescriptor2;
    }
}
