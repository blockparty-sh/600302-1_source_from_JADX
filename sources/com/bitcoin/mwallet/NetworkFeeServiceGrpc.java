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

public final class NetworkFeeServiceGrpc {
    private static final int METHODID_ESTIMATE_NETWORK_FEE = 0;
    public static final String SERVICE_NAME = "com.bitcoin.mwallet.NetworkFeeService";
    private static volatile MethodDescriptor<EstimateNetworkFeeRequest, EstimateNetworkFeeResponse> getEstimateNetworkFeeMethod;
    private static volatile ServiceDescriptor serviceDescriptor;

    private static final class MethodHandlers<Req, Resp> implements UnaryMethod<Req, Resp>, ServerStreamingMethod<Req, Resp>, ClientStreamingMethod<Req, Resp>, BidiStreamingMethod<Req, Resp> {
        private final int methodId;
        private final NetworkFeeServiceImplBase serviceImpl;

        MethodHandlers(NetworkFeeServiceImplBase networkFeeServiceImplBase, int i) {
            this.serviceImpl = networkFeeServiceImplBase;
            this.methodId = i;
        }

        public void invoke(Req req, StreamObserver<Resp> streamObserver) {
            if (this.methodId == 0) {
                this.serviceImpl.estimateNetworkFee((EstimateNetworkFeeRequest) req, streamObserver);
                return;
            }
            throw new AssertionError();
        }

        public StreamObserver<Req> invoke(StreamObserver<Resp> streamObserver) {
            int i = this.methodId;
            throw new AssertionError();
        }
    }

    public static final class NetworkFeeServiceBlockingStub extends AbstractStub<NetworkFeeServiceBlockingStub> {
        private NetworkFeeServiceBlockingStub(Channel channel) {
            super(channel);
        }

        private NetworkFeeServiceBlockingStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        /* access modifiers changed from: protected */
        public NetworkFeeServiceBlockingStub build(Channel channel, CallOptions callOptions) {
            return new NetworkFeeServiceBlockingStub(channel, callOptions);
        }

        public EstimateNetworkFeeResponse estimateNetworkFee(EstimateNetworkFeeRequest estimateNetworkFeeRequest) {
            return (EstimateNetworkFeeResponse) ClientCalls.blockingUnaryCall(getChannel(), NetworkFeeServiceGrpc.getEstimateNetworkFeeMethod(), getCallOptions(), estimateNetworkFeeRequest);
        }
    }

    public static final class NetworkFeeServiceFutureStub extends AbstractStub<NetworkFeeServiceFutureStub> {
        private NetworkFeeServiceFutureStub(Channel channel) {
            super(channel);
        }

        private NetworkFeeServiceFutureStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        /* access modifiers changed from: protected */
        public NetworkFeeServiceFutureStub build(Channel channel, CallOptions callOptions) {
            return new NetworkFeeServiceFutureStub(channel, callOptions);
        }

        public ListenableFuture<EstimateNetworkFeeResponse> estimateNetworkFee(EstimateNetworkFeeRequest estimateNetworkFeeRequest) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(NetworkFeeServiceGrpc.getEstimateNetworkFeeMethod(), getCallOptions()), estimateNetworkFeeRequest);
        }
    }

    public static abstract class NetworkFeeServiceImplBase implements BindableService {
        public void estimateNetworkFee(EstimateNetworkFeeRequest estimateNetworkFeeRequest, StreamObserver<EstimateNetworkFeeResponse> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(NetworkFeeServiceGrpc.getEstimateNetworkFeeMethod(), streamObserver);
        }

        public final ServerServiceDefinition bindService() {
            return ServerServiceDefinition.builder(NetworkFeeServiceGrpc.getServiceDescriptor()).addMethod(NetworkFeeServiceGrpc.getEstimateNetworkFeeMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 0))).build();
        }
    }

    public static final class NetworkFeeServiceStub extends AbstractStub<NetworkFeeServiceStub> {
        private NetworkFeeServiceStub(Channel channel) {
            super(channel);
        }

        private NetworkFeeServiceStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        /* access modifiers changed from: protected */
        public NetworkFeeServiceStub build(Channel channel, CallOptions callOptions) {
            return new NetworkFeeServiceStub(channel, callOptions);
        }

        public void estimateNetworkFee(EstimateNetworkFeeRequest estimateNetworkFeeRequest, StreamObserver<EstimateNetworkFeeResponse> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(NetworkFeeServiceGrpc.getEstimateNetworkFeeMethod(), getCallOptions()), estimateNetworkFeeRequest, streamObserver);
        }
    }

    private NetworkFeeServiceGrpc() {
    }

    @RpcMethod(fullMethodName = "com.bitcoin.mwallet.NetworkFeeService/estimateNetworkFee", methodType = MethodType.UNARY, requestType = EstimateNetworkFeeRequest.class, responseType = EstimateNetworkFeeResponse.class)
    public static MethodDescriptor<EstimateNetworkFeeRequest, EstimateNetworkFeeResponse> getEstimateNetworkFeeMethod() {
        MethodDescriptor<EstimateNetworkFeeRequest, EstimateNetworkFeeResponse> methodDescriptor = getEstimateNetworkFeeMethod;
        if (methodDescriptor == null) {
            synchronized (NetworkFeeServiceGrpc.class) {
                methodDescriptor = getEstimateNetworkFeeMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "estimateNetworkFee")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoLiteUtils.marshaller(EstimateNetworkFeeRequest.getDefaultInstance())).setResponseMarshaller(ProtoLiteUtils.marshaller(EstimateNetworkFeeResponse.getDefaultInstance())).build();
                    getEstimateNetworkFeeMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static NetworkFeeServiceStub newStub(Channel channel) {
        return new NetworkFeeServiceStub(channel);
    }

    public static NetworkFeeServiceBlockingStub newBlockingStub(Channel channel) {
        return new NetworkFeeServiceBlockingStub(channel);
    }

    public static NetworkFeeServiceFutureStub newFutureStub(Channel channel) {
        return new NetworkFeeServiceFutureStub(channel);
    }

    public static ServiceDescriptor getServiceDescriptor() {
        ServiceDescriptor serviceDescriptor2 = serviceDescriptor;
        if (serviceDescriptor2 == null) {
            synchronized (NetworkFeeServiceGrpc.class) {
                serviceDescriptor2 = serviceDescriptor;
                if (serviceDescriptor2 == null) {
                    serviceDescriptor2 = ServiceDescriptor.newBuilder(SERVICE_NAME).addMethod(getEstimateNetworkFeeMethod()).build();
                    serviceDescriptor = serviceDescriptor2;
                }
            }
        }
        return serviceDescriptor2;
    }
}
