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

public final class UserServiceGrpc {
    private static final int METHODID_CREATE_USER = 0;
    public static final String SERVICE_NAME = "com.bitcoin.mwallet.UserService";
    private static volatile MethodDescriptor<CreateUserRequest, CreateUserResponse> getCreateUserMethod;
    private static volatile ServiceDescriptor serviceDescriptor;

    private static final class MethodHandlers<Req, Resp> implements UnaryMethod<Req, Resp>, ServerStreamingMethod<Req, Resp>, ClientStreamingMethod<Req, Resp>, BidiStreamingMethod<Req, Resp> {
        private final int methodId;
        private final UserServiceImplBase serviceImpl;

        MethodHandlers(UserServiceImplBase userServiceImplBase, int i) {
            this.serviceImpl = userServiceImplBase;
            this.methodId = i;
        }

        public void invoke(Req req, StreamObserver<Resp> streamObserver) {
            if (this.methodId == 0) {
                this.serviceImpl.createUser((CreateUserRequest) req, streamObserver);
                return;
            }
            throw new AssertionError();
        }

        public StreamObserver<Req> invoke(StreamObserver<Resp> streamObserver) {
            int i = this.methodId;
            throw new AssertionError();
        }
    }

    public static final class UserServiceBlockingStub extends AbstractStub<UserServiceBlockingStub> {
        private UserServiceBlockingStub(Channel channel) {
            super(channel);
        }

        private UserServiceBlockingStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        /* access modifiers changed from: protected */
        public UserServiceBlockingStub build(Channel channel, CallOptions callOptions) {
            return new UserServiceBlockingStub(channel, callOptions);
        }

        public CreateUserResponse createUser(CreateUserRequest createUserRequest) {
            return (CreateUserResponse) ClientCalls.blockingUnaryCall(getChannel(), UserServiceGrpc.getCreateUserMethod(), getCallOptions(), createUserRequest);
        }
    }

    public static final class UserServiceFutureStub extends AbstractStub<UserServiceFutureStub> {
        private UserServiceFutureStub(Channel channel) {
            super(channel);
        }

        private UserServiceFutureStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        /* access modifiers changed from: protected */
        public UserServiceFutureStub build(Channel channel, CallOptions callOptions) {
            return new UserServiceFutureStub(channel, callOptions);
        }

        public ListenableFuture<CreateUserResponse> createUser(CreateUserRequest createUserRequest) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(UserServiceGrpc.getCreateUserMethod(), getCallOptions()), createUserRequest);
        }
    }

    public static abstract class UserServiceImplBase implements BindableService {
        public void createUser(CreateUserRequest createUserRequest, StreamObserver<CreateUserResponse> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(UserServiceGrpc.getCreateUserMethod(), streamObserver);
        }

        public final ServerServiceDefinition bindService() {
            return ServerServiceDefinition.builder(UserServiceGrpc.getServiceDescriptor()).addMethod(UserServiceGrpc.getCreateUserMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 0))).build();
        }
    }

    public static final class UserServiceStub extends AbstractStub<UserServiceStub> {
        private UserServiceStub(Channel channel) {
            super(channel);
        }

        private UserServiceStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        /* access modifiers changed from: protected */
        public UserServiceStub build(Channel channel, CallOptions callOptions) {
            return new UserServiceStub(channel, callOptions);
        }

        public void createUser(CreateUserRequest createUserRequest, StreamObserver<CreateUserResponse> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(UserServiceGrpc.getCreateUserMethod(), getCallOptions()), createUserRequest, streamObserver);
        }
    }

    private UserServiceGrpc() {
    }

    @RpcMethod(fullMethodName = "com.bitcoin.mwallet.UserService/createUser", methodType = MethodType.UNARY, requestType = CreateUserRequest.class, responseType = CreateUserResponse.class)
    public static MethodDescriptor<CreateUserRequest, CreateUserResponse> getCreateUserMethod() {
        MethodDescriptor<CreateUserRequest, CreateUserResponse> methodDescriptor = getCreateUserMethod;
        if (methodDescriptor == null) {
            synchronized (UserServiceGrpc.class) {
                methodDescriptor = getCreateUserMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "createUser")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoLiteUtils.marshaller(CreateUserRequest.getDefaultInstance())).setResponseMarshaller(ProtoLiteUtils.marshaller(CreateUserResponse.getDefaultInstance())).build();
                    getCreateUserMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static UserServiceStub newStub(Channel channel) {
        return new UserServiceStub(channel);
    }

    public static UserServiceBlockingStub newBlockingStub(Channel channel) {
        return new UserServiceBlockingStub(channel);
    }

    public static UserServiceFutureStub newFutureStub(Channel channel) {
        return new UserServiceFutureStub(channel);
    }

    public static ServiceDescriptor getServiceDescriptor() {
        ServiceDescriptor serviceDescriptor2 = serviceDescriptor;
        if (serviceDescriptor2 == null) {
            synchronized (UserServiceGrpc.class) {
                serviceDescriptor2 = serviceDescriptor;
                if (serviceDescriptor2 == null) {
                    serviceDescriptor2 = ServiceDescriptor.newBuilder(SERVICE_NAME).addMethod(getCreateUserMethod()).build();
                    serviceDescriptor = serviceDescriptor2;
                }
            }
        }
        return serviceDescriptor2;
    }
}
