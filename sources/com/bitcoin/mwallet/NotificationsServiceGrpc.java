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

public final class NotificationsServiceGrpc {
    private static final int METHODID_REGISTER_REGION = 2;
    private static final int METHODID_SUBSCRIBE = 0;
    private static final int METHODID_UNSUBSCRIBE = 1;
    public static final String SERVICE_NAME = "com.bitcoin.mwallet.NotificationsService";
    private static volatile MethodDescriptor<RegisterRegionRequest, RegisterRegionResponse> getRegisterRegionMethod;
    private static volatile MethodDescriptor<SubscribeRequest, SubscribeResponse> getSubscribeMethod;
    private static volatile MethodDescriptor<UnsubscribeRequest, UnsubscribeResponse> getUnsubscribeMethod;
    private static volatile ServiceDescriptor serviceDescriptor;

    private static final class MethodHandlers<Req, Resp> implements UnaryMethod<Req, Resp>, ServerStreamingMethod<Req, Resp>, ClientStreamingMethod<Req, Resp>, BidiStreamingMethod<Req, Resp> {
        private final int methodId;
        private final NotificationsServiceImplBase serviceImpl;

        MethodHandlers(NotificationsServiceImplBase notificationsServiceImplBase, int i) {
            this.serviceImpl = notificationsServiceImplBase;
            this.methodId = i;
        }

        public void invoke(Req req, StreamObserver<Resp> streamObserver) {
            int i = this.methodId;
            if (i == 0) {
                this.serviceImpl.subscribe((SubscribeRequest) req, streamObserver);
            } else if (i == 1) {
                this.serviceImpl.unsubscribe((UnsubscribeRequest) req, streamObserver);
            } else if (i == 2) {
                this.serviceImpl.registerRegion((RegisterRegionRequest) req, streamObserver);
            } else {
                throw new AssertionError();
            }
        }

        public StreamObserver<Req> invoke(StreamObserver<Resp> streamObserver) {
            int i = this.methodId;
            throw new AssertionError();
        }
    }

    public static final class NotificationsServiceBlockingStub extends AbstractStub<NotificationsServiceBlockingStub> {
        private NotificationsServiceBlockingStub(Channel channel) {
            super(channel);
        }

        private NotificationsServiceBlockingStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        /* access modifiers changed from: protected */
        public NotificationsServiceBlockingStub build(Channel channel, CallOptions callOptions) {
            return new NotificationsServiceBlockingStub(channel, callOptions);
        }

        public SubscribeResponse subscribe(SubscribeRequest subscribeRequest) {
            return (SubscribeResponse) ClientCalls.blockingUnaryCall(getChannel(), NotificationsServiceGrpc.getSubscribeMethod(), getCallOptions(), subscribeRequest);
        }

        public UnsubscribeResponse unsubscribe(UnsubscribeRequest unsubscribeRequest) {
            return (UnsubscribeResponse) ClientCalls.blockingUnaryCall(getChannel(), NotificationsServiceGrpc.getUnsubscribeMethod(), getCallOptions(), unsubscribeRequest);
        }

        public RegisterRegionResponse registerRegion(RegisterRegionRequest registerRegionRequest) {
            return (RegisterRegionResponse) ClientCalls.blockingUnaryCall(getChannel(), NotificationsServiceGrpc.getRegisterRegionMethod(), getCallOptions(), registerRegionRequest);
        }
    }

    public static final class NotificationsServiceFutureStub extends AbstractStub<NotificationsServiceFutureStub> {
        private NotificationsServiceFutureStub(Channel channel) {
            super(channel);
        }

        private NotificationsServiceFutureStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        /* access modifiers changed from: protected */
        public NotificationsServiceFutureStub build(Channel channel, CallOptions callOptions) {
            return new NotificationsServiceFutureStub(channel, callOptions);
        }

        public ListenableFuture<SubscribeResponse> subscribe(SubscribeRequest subscribeRequest) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(NotificationsServiceGrpc.getSubscribeMethod(), getCallOptions()), subscribeRequest);
        }

        public ListenableFuture<UnsubscribeResponse> unsubscribe(UnsubscribeRequest unsubscribeRequest) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(NotificationsServiceGrpc.getUnsubscribeMethod(), getCallOptions()), unsubscribeRequest);
        }

        public ListenableFuture<RegisterRegionResponse> registerRegion(RegisterRegionRequest registerRegionRequest) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(NotificationsServiceGrpc.getRegisterRegionMethod(), getCallOptions()), registerRegionRequest);
        }
    }

    public static abstract class NotificationsServiceImplBase implements BindableService {
        public void subscribe(SubscribeRequest subscribeRequest, StreamObserver<SubscribeResponse> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(NotificationsServiceGrpc.getSubscribeMethod(), streamObserver);
        }

        public void unsubscribe(UnsubscribeRequest unsubscribeRequest, StreamObserver<UnsubscribeResponse> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(NotificationsServiceGrpc.getUnsubscribeMethod(), streamObserver);
        }

        public void registerRegion(RegisterRegionRequest registerRegionRequest, StreamObserver<RegisterRegionResponse> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(NotificationsServiceGrpc.getRegisterRegionMethod(), streamObserver);
        }

        public final ServerServiceDefinition bindService() {
            return ServerServiceDefinition.builder(NotificationsServiceGrpc.getServiceDescriptor()).addMethod(NotificationsServiceGrpc.getSubscribeMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 0))).addMethod(NotificationsServiceGrpc.getUnsubscribeMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 1))).addMethod(NotificationsServiceGrpc.getRegisterRegionMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 2))).build();
        }
    }

    public static final class NotificationsServiceStub extends AbstractStub<NotificationsServiceStub> {
        private NotificationsServiceStub(Channel channel) {
            super(channel);
        }

        private NotificationsServiceStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        /* access modifiers changed from: protected */
        public NotificationsServiceStub build(Channel channel, CallOptions callOptions) {
            return new NotificationsServiceStub(channel, callOptions);
        }

        public void subscribe(SubscribeRequest subscribeRequest, StreamObserver<SubscribeResponse> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(NotificationsServiceGrpc.getSubscribeMethod(), getCallOptions()), subscribeRequest, streamObserver);
        }

        public void unsubscribe(UnsubscribeRequest unsubscribeRequest, StreamObserver<UnsubscribeResponse> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(NotificationsServiceGrpc.getUnsubscribeMethod(), getCallOptions()), unsubscribeRequest, streamObserver);
        }

        public void registerRegion(RegisterRegionRequest registerRegionRequest, StreamObserver<RegisterRegionResponse> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(NotificationsServiceGrpc.getRegisterRegionMethod(), getCallOptions()), registerRegionRequest, streamObserver);
        }
    }

    private NotificationsServiceGrpc() {
    }

    @RpcMethod(fullMethodName = "com.bitcoin.mwallet.NotificationsService/subscribe", methodType = MethodType.UNARY, requestType = SubscribeRequest.class, responseType = SubscribeResponse.class)
    public static MethodDescriptor<SubscribeRequest, SubscribeResponse> getSubscribeMethod() {
        MethodDescriptor<SubscribeRequest, SubscribeResponse> methodDescriptor = getSubscribeMethod;
        if (methodDescriptor == null) {
            synchronized (NotificationsServiceGrpc.class) {
                methodDescriptor = getSubscribeMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "subscribe")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoLiteUtils.marshaller(SubscribeRequest.getDefaultInstance())).setResponseMarshaller(ProtoLiteUtils.marshaller(SubscribeResponse.getDefaultInstance())).build();
                    getSubscribeMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    @RpcMethod(fullMethodName = "com.bitcoin.mwallet.NotificationsService/unsubscribe", methodType = MethodType.UNARY, requestType = UnsubscribeRequest.class, responseType = UnsubscribeResponse.class)
    public static MethodDescriptor<UnsubscribeRequest, UnsubscribeResponse> getUnsubscribeMethod() {
        MethodDescriptor<UnsubscribeRequest, UnsubscribeResponse> methodDescriptor = getUnsubscribeMethod;
        if (methodDescriptor == null) {
            synchronized (NotificationsServiceGrpc.class) {
                methodDescriptor = getUnsubscribeMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "unsubscribe")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoLiteUtils.marshaller(UnsubscribeRequest.getDefaultInstance())).setResponseMarshaller(ProtoLiteUtils.marshaller(UnsubscribeResponse.getDefaultInstance())).build();
                    getUnsubscribeMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    @RpcMethod(fullMethodName = "com.bitcoin.mwallet.NotificationsService/registerRegion", methodType = MethodType.UNARY, requestType = RegisterRegionRequest.class, responseType = RegisterRegionResponse.class)
    public static MethodDescriptor<RegisterRegionRequest, RegisterRegionResponse> getRegisterRegionMethod() {
        MethodDescriptor<RegisterRegionRequest, RegisterRegionResponse> methodDescriptor = getRegisterRegionMethod;
        if (methodDescriptor == null) {
            synchronized (NotificationsServiceGrpc.class) {
                methodDescriptor = getRegisterRegionMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "registerRegion")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoLiteUtils.marshaller(RegisterRegionRequest.getDefaultInstance())).setResponseMarshaller(ProtoLiteUtils.marshaller(RegisterRegionResponse.getDefaultInstance())).build();
                    getRegisterRegionMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static NotificationsServiceStub newStub(Channel channel) {
        return new NotificationsServiceStub(channel);
    }

    public static NotificationsServiceBlockingStub newBlockingStub(Channel channel) {
        return new NotificationsServiceBlockingStub(channel);
    }

    public static NotificationsServiceFutureStub newFutureStub(Channel channel) {
        return new NotificationsServiceFutureStub(channel);
    }

    public static ServiceDescriptor getServiceDescriptor() {
        ServiceDescriptor serviceDescriptor2 = serviceDescriptor;
        if (serviceDescriptor2 == null) {
            synchronized (NotificationsServiceGrpc.class) {
                serviceDescriptor2 = serviceDescriptor;
                if (serviceDescriptor2 == null) {
                    serviceDescriptor2 = ServiceDescriptor.newBuilder(SERVICE_NAME).addMethod(getSubscribeMethod()).addMethod(getUnsubscribeMethod()).addMethod(getRegisterRegionMethod()).build();
                    serviceDescriptor = serviceDescriptor2;
                }
            }
        }
        return serviceDescriptor2;
    }
}
