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

public final class HealthCheckServiceGrpc {
    private static final int METHODID_CHECK_HEALTH = 0;
    public static final String SERVICE_NAME = "com.bitcoin.mwallet.HealthCheckService";
    private static volatile MethodDescriptor<CheckHealthRequest, CheckHealthResponse> getCheckHealthMethod;
    private static volatile ServiceDescriptor serviceDescriptor;

    public static final class HealthCheckServiceBlockingStub extends AbstractStub<HealthCheckServiceBlockingStub> {
        private HealthCheckServiceBlockingStub(Channel channel) {
            super(channel);
        }

        private HealthCheckServiceBlockingStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        /* access modifiers changed from: protected */
        public HealthCheckServiceBlockingStub build(Channel channel, CallOptions callOptions) {
            return new HealthCheckServiceBlockingStub(channel, callOptions);
        }

        public CheckHealthResponse checkHealth(CheckHealthRequest checkHealthRequest) {
            return (CheckHealthResponse) ClientCalls.blockingUnaryCall(getChannel(), HealthCheckServiceGrpc.getCheckHealthMethod(), getCallOptions(), checkHealthRequest);
        }
    }

    public static final class HealthCheckServiceFutureStub extends AbstractStub<HealthCheckServiceFutureStub> {
        private HealthCheckServiceFutureStub(Channel channel) {
            super(channel);
        }

        private HealthCheckServiceFutureStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        /* access modifiers changed from: protected */
        public HealthCheckServiceFutureStub build(Channel channel, CallOptions callOptions) {
            return new HealthCheckServiceFutureStub(channel, callOptions);
        }

        public ListenableFuture<CheckHealthResponse> checkHealth(CheckHealthRequest checkHealthRequest) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(HealthCheckServiceGrpc.getCheckHealthMethod(), getCallOptions()), checkHealthRequest);
        }
    }

    public static abstract class HealthCheckServiceImplBase implements BindableService {
        public void checkHealth(CheckHealthRequest checkHealthRequest, StreamObserver<CheckHealthResponse> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(HealthCheckServiceGrpc.getCheckHealthMethod(), streamObserver);
        }

        public final ServerServiceDefinition bindService() {
            return ServerServiceDefinition.builder(HealthCheckServiceGrpc.getServiceDescriptor()).addMethod(HealthCheckServiceGrpc.getCheckHealthMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 0))).build();
        }
    }

    public static final class HealthCheckServiceStub extends AbstractStub<HealthCheckServiceStub> {
        private HealthCheckServiceStub(Channel channel) {
            super(channel);
        }

        private HealthCheckServiceStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        /* access modifiers changed from: protected */
        public HealthCheckServiceStub build(Channel channel, CallOptions callOptions) {
            return new HealthCheckServiceStub(channel, callOptions);
        }

        public void checkHealth(CheckHealthRequest checkHealthRequest, StreamObserver<CheckHealthResponse> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(HealthCheckServiceGrpc.getCheckHealthMethod(), getCallOptions()), checkHealthRequest, streamObserver);
        }
    }

    private static final class MethodHandlers<Req, Resp> implements UnaryMethod<Req, Resp>, ServerStreamingMethod<Req, Resp>, ClientStreamingMethod<Req, Resp>, BidiStreamingMethod<Req, Resp> {
        private final int methodId;
        private final HealthCheckServiceImplBase serviceImpl;

        MethodHandlers(HealthCheckServiceImplBase healthCheckServiceImplBase, int i) {
            this.serviceImpl = healthCheckServiceImplBase;
            this.methodId = i;
        }

        public void invoke(Req req, StreamObserver<Resp> streamObserver) {
            if (this.methodId == 0) {
                this.serviceImpl.checkHealth((CheckHealthRequest) req, streamObserver);
                return;
            }
            throw new AssertionError();
        }

        public StreamObserver<Req> invoke(StreamObserver<Resp> streamObserver) {
            int i = this.methodId;
            throw new AssertionError();
        }
    }

    private HealthCheckServiceGrpc() {
    }

    @RpcMethod(fullMethodName = "com.bitcoin.mwallet.HealthCheckService/checkHealth", methodType = MethodType.UNARY, requestType = CheckHealthRequest.class, responseType = CheckHealthResponse.class)
    public static MethodDescriptor<CheckHealthRequest, CheckHealthResponse> getCheckHealthMethod() {
        MethodDescriptor<CheckHealthRequest, CheckHealthResponse> methodDescriptor = getCheckHealthMethod;
        if (methodDescriptor == null) {
            synchronized (HealthCheckServiceGrpc.class) {
                methodDescriptor = getCheckHealthMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "checkHealth")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoLiteUtils.marshaller(CheckHealthRequest.getDefaultInstance())).setResponseMarshaller(ProtoLiteUtils.marshaller(CheckHealthResponse.getDefaultInstance())).build();
                    getCheckHealthMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static HealthCheckServiceStub newStub(Channel channel) {
        return new HealthCheckServiceStub(channel);
    }

    public static HealthCheckServiceBlockingStub newBlockingStub(Channel channel) {
        return new HealthCheckServiceBlockingStub(channel);
    }

    public static HealthCheckServiceFutureStub newFutureStub(Channel channel) {
        return new HealthCheckServiceFutureStub(channel);
    }

    public static ServiceDescriptor getServiceDescriptor() {
        ServiceDescriptor serviceDescriptor2 = serviceDescriptor;
        if (serviceDescriptor2 == null) {
            synchronized (HealthCheckServiceGrpc.class) {
                serviceDescriptor2 = serviceDescriptor;
                if (serviceDescriptor2 == null) {
                    serviceDescriptor2 = ServiceDescriptor.newBuilder(SERVICE_NAME).addMethod(getCheckHealthMethod()).build();
                    serviceDescriptor = serviceDescriptor2;
                }
            }
        }
        return serviceDescriptor2;
    }
}
