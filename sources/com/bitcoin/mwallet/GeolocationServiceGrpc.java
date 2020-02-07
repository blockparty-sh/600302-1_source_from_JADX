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

public final class GeolocationServiceGrpc {
    private static final int METHODID_GET_GEOLOCATION_INFO = 0;
    public static final String SERVICE_NAME = "com.bitcoin.mwallet.GeolocationService";
    private static volatile MethodDescriptor<GetGeolocationInfoRequest, GetGeolocationInfoResponse> getGetGeolocationInfoMethod;
    private static volatile ServiceDescriptor serviceDescriptor;

    public static final class GeolocationServiceBlockingStub extends AbstractStub<GeolocationServiceBlockingStub> {
        private GeolocationServiceBlockingStub(Channel channel) {
            super(channel);
        }

        private GeolocationServiceBlockingStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        /* access modifiers changed from: protected */
        public GeolocationServiceBlockingStub build(Channel channel, CallOptions callOptions) {
            return new GeolocationServiceBlockingStub(channel, callOptions);
        }

        public GetGeolocationInfoResponse getGeolocationInfo(GetGeolocationInfoRequest getGeolocationInfoRequest) {
            return (GetGeolocationInfoResponse) ClientCalls.blockingUnaryCall(getChannel(), GeolocationServiceGrpc.getGetGeolocationInfoMethod(), getCallOptions(), getGeolocationInfoRequest);
        }
    }

    public static final class GeolocationServiceFutureStub extends AbstractStub<GeolocationServiceFutureStub> {
        private GeolocationServiceFutureStub(Channel channel) {
            super(channel);
        }

        private GeolocationServiceFutureStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        /* access modifiers changed from: protected */
        public GeolocationServiceFutureStub build(Channel channel, CallOptions callOptions) {
            return new GeolocationServiceFutureStub(channel, callOptions);
        }

        public ListenableFuture<GetGeolocationInfoResponse> getGeolocationInfo(GetGeolocationInfoRequest getGeolocationInfoRequest) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(GeolocationServiceGrpc.getGetGeolocationInfoMethod(), getCallOptions()), getGeolocationInfoRequest);
        }
    }

    public static abstract class GeolocationServiceImplBase implements BindableService {
        public void getGeolocationInfo(GetGeolocationInfoRequest getGeolocationInfoRequest, StreamObserver<GetGeolocationInfoResponse> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(GeolocationServiceGrpc.getGetGeolocationInfoMethod(), streamObserver);
        }

        public final ServerServiceDefinition bindService() {
            return ServerServiceDefinition.builder(GeolocationServiceGrpc.getServiceDescriptor()).addMethod(GeolocationServiceGrpc.getGetGeolocationInfoMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 0))).build();
        }
    }

    public static final class GeolocationServiceStub extends AbstractStub<GeolocationServiceStub> {
        private GeolocationServiceStub(Channel channel) {
            super(channel);
        }

        private GeolocationServiceStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        /* access modifiers changed from: protected */
        public GeolocationServiceStub build(Channel channel, CallOptions callOptions) {
            return new GeolocationServiceStub(channel, callOptions);
        }

        public void getGeolocationInfo(GetGeolocationInfoRequest getGeolocationInfoRequest, StreamObserver<GetGeolocationInfoResponse> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(GeolocationServiceGrpc.getGetGeolocationInfoMethod(), getCallOptions()), getGeolocationInfoRequest, streamObserver);
        }
    }

    private static final class MethodHandlers<Req, Resp> implements UnaryMethod<Req, Resp>, ServerStreamingMethod<Req, Resp>, ClientStreamingMethod<Req, Resp>, BidiStreamingMethod<Req, Resp> {
        private final int methodId;
        private final GeolocationServiceImplBase serviceImpl;

        MethodHandlers(GeolocationServiceImplBase geolocationServiceImplBase, int i) {
            this.serviceImpl = geolocationServiceImplBase;
            this.methodId = i;
        }

        public void invoke(Req req, StreamObserver<Resp> streamObserver) {
            if (this.methodId == 0) {
                this.serviceImpl.getGeolocationInfo((GetGeolocationInfoRequest) req, streamObserver);
                return;
            }
            throw new AssertionError();
        }

        public StreamObserver<Req> invoke(StreamObserver<Resp> streamObserver) {
            int i = this.methodId;
            throw new AssertionError();
        }
    }

    private GeolocationServiceGrpc() {
    }

    @RpcMethod(fullMethodName = "com.bitcoin.mwallet.GeolocationService/getGeolocationInfo", methodType = MethodType.UNARY, requestType = GetGeolocationInfoRequest.class, responseType = GetGeolocationInfoResponse.class)
    public static MethodDescriptor<GetGeolocationInfoRequest, GetGeolocationInfoResponse> getGetGeolocationInfoMethod() {
        MethodDescriptor<GetGeolocationInfoRequest, GetGeolocationInfoResponse> methodDescriptor = getGetGeolocationInfoMethod;
        if (methodDescriptor == null) {
            synchronized (GeolocationServiceGrpc.class) {
                methodDescriptor = getGetGeolocationInfoMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "getGeolocationInfo")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoLiteUtils.marshaller(GetGeolocationInfoRequest.getDefaultInstance())).setResponseMarshaller(ProtoLiteUtils.marshaller(GetGeolocationInfoResponse.getDefaultInstance())).build();
                    getGetGeolocationInfoMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static GeolocationServiceStub newStub(Channel channel) {
        return new GeolocationServiceStub(channel);
    }

    public static GeolocationServiceBlockingStub newBlockingStub(Channel channel) {
        return new GeolocationServiceBlockingStub(channel);
    }

    public static GeolocationServiceFutureStub newFutureStub(Channel channel) {
        return new GeolocationServiceFutureStub(channel);
    }

    public static ServiceDescriptor getServiceDescriptor() {
        ServiceDescriptor serviceDescriptor2 = serviceDescriptor;
        if (serviceDescriptor2 == null) {
            synchronized (GeolocationServiceGrpc.class) {
                serviceDescriptor2 = serviceDescriptor;
                if (serviceDescriptor2 == null) {
                    serviceDescriptor2 = ServiceDescriptor.newBuilder(SERVICE_NAME).addMethod(getGetGeolocationInfoMethod()).build();
                    serviceDescriptor = serviceDescriptor2;
                }
            }
        }
        return serviceDescriptor2;
    }
}
