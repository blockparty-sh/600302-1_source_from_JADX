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

public final class WalletServiceGrpc {
    private static final int METHODID_CREATE_MULTI_SIG_WALLET = 3;
    private static final int METHODID_CREATE_SINGLE_SIG_WALLET = 0;
    private static final int METHODID_JOIN_MULTI_SIG_WALLET = 4;
    private static final int METHODID_MIGRATE_EXISTING_WALLET = 2;
    private static final int METHODID_RECOVER_SINGLE_SIG_WALLET = 1;
    public static final String SERVICE_NAME = "com.bitcoin.mwallet.WalletService";
    private static volatile MethodDescriptor<CreateMultiSigWalletRequest, CreateMultiSigWalletResponse> getCreateMultiSigWalletMethod;
    private static volatile MethodDescriptor<CreateSingleSigWalletRequest, CreateSingleSigWalletResponse> getCreateSingleSigWalletMethod;
    private static volatile MethodDescriptor<JoinMultiSigWalletRequest, JoinMultiSigWalletResponse> getJoinMultiSigWalletMethod;
    private static volatile MethodDescriptor<MigrateSingleSigWalletRequest, MigrateSingleSigWalletResponse> getMigrateExistingWalletMethod;
    private static volatile MethodDescriptor<RecoverSingleSigWalletRequest, RecoverSingleSigWalletResponse> getRecoverSingleSigWalletMethod;
    private static volatile ServiceDescriptor serviceDescriptor;

    private static final class MethodHandlers<Req, Resp> implements UnaryMethod<Req, Resp>, ServerStreamingMethod<Req, Resp>, ClientStreamingMethod<Req, Resp>, BidiStreamingMethod<Req, Resp> {
        private final int methodId;
        private final WalletServiceImplBase serviceImpl;

        MethodHandlers(WalletServiceImplBase walletServiceImplBase, int i) {
            this.serviceImpl = walletServiceImplBase;
            this.methodId = i;
        }

        public void invoke(Req req, StreamObserver<Resp> streamObserver) {
            int i = this.methodId;
            if (i == 0) {
                this.serviceImpl.createSingleSigWallet((CreateSingleSigWalletRequest) req, streamObserver);
            } else if (i == 1) {
                this.serviceImpl.recoverSingleSigWallet((RecoverSingleSigWalletRequest) req, streamObserver);
            } else if (i == 2) {
                this.serviceImpl.migrateExistingWallet((MigrateSingleSigWalletRequest) req, streamObserver);
            } else if (i == 3) {
                this.serviceImpl.createMultiSigWallet((CreateMultiSigWalletRequest) req, streamObserver);
            } else if (i == 4) {
                this.serviceImpl.joinMultiSigWallet((JoinMultiSigWalletRequest) req, streamObserver);
            } else {
                throw new AssertionError();
            }
        }

        public StreamObserver<Req> invoke(StreamObserver<Resp> streamObserver) {
            int i = this.methodId;
            throw new AssertionError();
        }
    }

    public static final class WalletServiceBlockingStub extends AbstractStub<WalletServiceBlockingStub> {
        private WalletServiceBlockingStub(Channel channel) {
            super(channel);
        }

        private WalletServiceBlockingStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        /* access modifiers changed from: protected */
        public WalletServiceBlockingStub build(Channel channel, CallOptions callOptions) {
            return new WalletServiceBlockingStub(channel, callOptions);
        }

        public CreateSingleSigWalletResponse createSingleSigWallet(CreateSingleSigWalletRequest createSingleSigWalletRequest) {
            return (CreateSingleSigWalletResponse) ClientCalls.blockingUnaryCall(getChannel(), WalletServiceGrpc.getCreateSingleSigWalletMethod(), getCallOptions(), createSingleSigWalletRequest);
        }

        public RecoverSingleSigWalletResponse recoverSingleSigWallet(RecoverSingleSigWalletRequest recoverSingleSigWalletRequest) {
            return (RecoverSingleSigWalletResponse) ClientCalls.blockingUnaryCall(getChannel(), WalletServiceGrpc.getRecoverSingleSigWalletMethod(), getCallOptions(), recoverSingleSigWalletRequest);
        }

        public MigrateSingleSigWalletResponse migrateExistingWallet(MigrateSingleSigWalletRequest migrateSingleSigWalletRequest) {
            return (MigrateSingleSigWalletResponse) ClientCalls.blockingUnaryCall(getChannel(), WalletServiceGrpc.getMigrateExistingWalletMethod(), getCallOptions(), migrateSingleSigWalletRequest);
        }

        public CreateMultiSigWalletResponse createMultiSigWallet(CreateMultiSigWalletRequest createMultiSigWalletRequest) {
            return (CreateMultiSigWalletResponse) ClientCalls.blockingUnaryCall(getChannel(), WalletServiceGrpc.getCreateMultiSigWalletMethod(), getCallOptions(), createMultiSigWalletRequest);
        }

        public JoinMultiSigWalletResponse joinMultiSigWallet(JoinMultiSigWalletRequest joinMultiSigWalletRequest) {
            return (JoinMultiSigWalletResponse) ClientCalls.blockingUnaryCall(getChannel(), WalletServiceGrpc.getJoinMultiSigWalletMethod(), getCallOptions(), joinMultiSigWalletRequest);
        }
    }

    public static final class WalletServiceFutureStub extends AbstractStub<WalletServiceFutureStub> {
        private WalletServiceFutureStub(Channel channel) {
            super(channel);
        }

        private WalletServiceFutureStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        /* access modifiers changed from: protected */
        public WalletServiceFutureStub build(Channel channel, CallOptions callOptions) {
            return new WalletServiceFutureStub(channel, callOptions);
        }

        public ListenableFuture<CreateSingleSigWalletResponse> createSingleSigWallet(CreateSingleSigWalletRequest createSingleSigWalletRequest) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletServiceGrpc.getCreateSingleSigWalletMethod(), getCallOptions()), createSingleSigWalletRequest);
        }

        public ListenableFuture<RecoverSingleSigWalletResponse> recoverSingleSigWallet(RecoverSingleSigWalletRequest recoverSingleSigWalletRequest) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletServiceGrpc.getRecoverSingleSigWalletMethod(), getCallOptions()), recoverSingleSigWalletRequest);
        }

        public ListenableFuture<MigrateSingleSigWalletResponse> migrateExistingWallet(MigrateSingleSigWalletRequest migrateSingleSigWalletRequest) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletServiceGrpc.getMigrateExistingWalletMethod(), getCallOptions()), migrateSingleSigWalletRequest);
        }

        public ListenableFuture<CreateMultiSigWalletResponse> createMultiSigWallet(CreateMultiSigWalletRequest createMultiSigWalletRequest) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletServiceGrpc.getCreateMultiSigWalletMethod(), getCallOptions()), createMultiSigWalletRequest);
        }

        public ListenableFuture<JoinMultiSigWalletResponse> joinMultiSigWallet(JoinMultiSigWalletRequest joinMultiSigWalletRequest) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletServiceGrpc.getJoinMultiSigWalletMethod(), getCallOptions()), joinMultiSigWalletRequest);
        }
    }

    public static abstract class WalletServiceImplBase implements BindableService {
        public void createSingleSigWallet(CreateSingleSigWalletRequest createSingleSigWalletRequest, StreamObserver<CreateSingleSigWalletResponse> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletServiceGrpc.getCreateSingleSigWalletMethod(), streamObserver);
        }

        public void recoverSingleSigWallet(RecoverSingleSigWalletRequest recoverSingleSigWalletRequest, StreamObserver<RecoverSingleSigWalletResponse> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletServiceGrpc.getRecoverSingleSigWalletMethod(), streamObserver);
        }

        public void migrateExistingWallet(MigrateSingleSigWalletRequest migrateSingleSigWalletRequest, StreamObserver<MigrateSingleSigWalletResponse> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletServiceGrpc.getMigrateExistingWalletMethod(), streamObserver);
        }

        public void createMultiSigWallet(CreateMultiSigWalletRequest createMultiSigWalletRequest, StreamObserver<CreateMultiSigWalletResponse> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletServiceGrpc.getCreateMultiSigWalletMethod(), streamObserver);
        }

        public void joinMultiSigWallet(JoinMultiSigWalletRequest joinMultiSigWalletRequest, StreamObserver<JoinMultiSigWalletResponse> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletServiceGrpc.getJoinMultiSigWalletMethod(), streamObserver);
        }

        public final ServerServiceDefinition bindService() {
            return ServerServiceDefinition.builder(WalletServiceGrpc.getServiceDescriptor()).addMethod(WalletServiceGrpc.getCreateSingleSigWalletMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 0))).addMethod(WalletServiceGrpc.getRecoverSingleSigWalletMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 1))).addMethod(WalletServiceGrpc.getMigrateExistingWalletMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 2))).addMethod(WalletServiceGrpc.getCreateMultiSigWalletMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 3))).addMethod(WalletServiceGrpc.getJoinMultiSigWalletMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 4))).build();
        }
    }

    public static final class WalletServiceStub extends AbstractStub<WalletServiceStub> {
        private WalletServiceStub(Channel channel) {
            super(channel);
        }

        private WalletServiceStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        /* access modifiers changed from: protected */
        public WalletServiceStub build(Channel channel, CallOptions callOptions) {
            return new WalletServiceStub(channel, callOptions);
        }

        public void createSingleSigWallet(CreateSingleSigWalletRequest createSingleSigWalletRequest, StreamObserver<CreateSingleSigWalletResponse> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletServiceGrpc.getCreateSingleSigWalletMethod(), getCallOptions()), createSingleSigWalletRequest, streamObserver);
        }

        public void recoverSingleSigWallet(RecoverSingleSigWalletRequest recoverSingleSigWalletRequest, StreamObserver<RecoverSingleSigWalletResponse> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletServiceGrpc.getRecoverSingleSigWalletMethod(), getCallOptions()), recoverSingleSigWalletRequest, streamObserver);
        }

        public void migrateExistingWallet(MigrateSingleSigWalletRequest migrateSingleSigWalletRequest, StreamObserver<MigrateSingleSigWalletResponse> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletServiceGrpc.getMigrateExistingWalletMethod(), getCallOptions()), migrateSingleSigWalletRequest, streamObserver);
        }

        public void createMultiSigWallet(CreateMultiSigWalletRequest createMultiSigWalletRequest, StreamObserver<CreateMultiSigWalletResponse> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletServiceGrpc.getCreateMultiSigWalletMethod(), getCallOptions()), createMultiSigWalletRequest, streamObserver);
        }

        public void joinMultiSigWallet(JoinMultiSigWalletRequest joinMultiSigWalletRequest, StreamObserver<JoinMultiSigWalletResponse> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletServiceGrpc.getJoinMultiSigWalletMethod(), getCallOptions()), joinMultiSigWalletRequest, streamObserver);
        }
    }

    private WalletServiceGrpc() {
    }

    @RpcMethod(fullMethodName = "com.bitcoin.mwallet.WalletService/createSingleSigWallet", methodType = MethodType.UNARY, requestType = CreateSingleSigWalletRequest.class, responseType = CreateSingleSigWalletResponse.class)
    public static MethodDescriptor<CreateSingleSigWalletRequest, CreateSingleSigWalletResponse> getCreateSingleSigWalletMethod() {
        MethodDescriptor<CreateSingleSigWalletRequest, CreateSingleSigWalletResponse> methodDescriptor = getCreateSingleSigWalletMethod;
        if (methodDescriptor == null) {
            synchronized (WalletServiceGrpc.class) {
                methodDescriptor = getCreateSingleSigWalletMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "createSingleSigWallet")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoLiteUtils.marshaller(CreateSingleSigWalletRequest.getDefaultInstance())).setResponseMarshaller(ProtoLiteUtils.marshaller(CreateSingleSigWalletResponse.getDefaultInstance())).build();
                    getCreateSingleSigWalletMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    @RpcMethod(fullMethodName = "com.bitcoin.mwallet.WalletService/recoverSingleSigWallet", methodType = MethodType.UNARY, requestType = RecoverSingleSigWalletRequest.class, responseType = RecoverSingleSigWalletResponse.class)
    public static MethodDescriptor<RecoverSingleSigWalletRequest, RecoverSingleSigWalletResponse> getRecoverSingleSigWalletMethod() {
        MethodDescriptor<RecoverSingleSigWalletRequest, RecoverSingleSigWalletResponse> methodDescriptor = getRecoverSingleSigWalletMethod;
        if (methodDescriptor == null) {
            synchronized (WalletServiceGrpc.class) {
                methodDescriptor = getRecoverSingleSigWalletMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "recoverSingleSigWallet")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoLiteUtils.marshaller(RecoverSingleSigWalletRequest.getDefaultInstance())).setResponseMarshaller(ProtoLiteUtils.marshaller(RecoverSingleSigWalletResponse.getDefaultInstance())).build();
                    getRecoverSingleSigWalletMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    @RpcMethod(fullMethodName = "com.bitcoin.mwallet.WalletService/migrateExistingWallet", methodType = MethodType.UNARY, requestType = MigrateSingleSigWalletRequest.class, responseType = MigrateSingleSigWalletResponse.class)
    public static MethodDescriptor<MigrateSingleSigWalletRequest, MigrateSingleSigWalletResponse> getMigrateExistingWalletMethod() {
        MethodDescriptor<MigrateSingleSigWalletRequest, MigrateSingleSigWalletResponse> methodDescriptor = getMigrateExistingWalletMethod;
        if (methodDescriptor == null) {
            synchronized (WalletServiceGrpc.class) {
                methodDescriptor = getMigrateExistingWalletMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "migrateExistingWallet")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoLiteUtils.marshaller(MigrateSingleSigWalletRequest.getDefaultInstance())).setResponseMarshaller(ProtoLiteUtils.marshaller(MigrateSingleSigWalletResponse.getDefaultInstance())).build();
                    getMigrateExistingWalletMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    @RpcMethod(fullMethodName = "com.bitcoin.mwallet.WalletService/createMultiSigWallet", methodType = MethodType.UNARY, requestType = CreateMultiSigWalletRequest.class, responseType = CreateMultiSigWalletResponse.class)
    public static MethodDescriptor<CreateMultiSigWalletRequest, CreateMultiSigWalletResponse> getCreateMultiSigWalletMethod() {
        MethodDescriptor<CreateMultiSigWalletRequest, CreateMultiSigWalletResponse> methodDescriptor = getCreateMultiSigWalletMethod;
        if (methodDescriptor == null) {
            synchronized (WalletServiceGrpc.class) {
                methodDescriptor = getCreateMultiSigWalletMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "createMultiSigWallet")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoLiteUtils.marshaller(CreateMultiSigWalletRequest.getDefaultInstance())).setResponseMarshaller(ProtoLiteUtils.marshaller(CreateMultiSigWalletResponse.getDefaultInstance())).build();
                    getCreateMultiSigWalletMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    @RpcMethod(fullMethodName = "com.bitcoin.mwallet.WalletService/joinMultiSigWallet", methodType = MethodType.UNARY, requestType = JoinMultiSigWalletRequest.class, responseType = JoinMultiSigWalletResponse.class)
    public static MethodDescriptor<JoinMultiSigWalletRequest, JoinMultiSigWalletResponse> getJoinMultiSigWalletMethod() {
        MethodDescriptor<JoinMultiSigWalletRequest, JoinMultiSigWalletResponse> methodDescriptor = getJoinMultiSigWalletMethod;
        if (methodDescriptor == null) {
            synchronized (WalletServiceGrpc.class) {
                methodDescriptor = getJoinMultiSigWalletMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "joinMultiSigWallet")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoLiteUtils.marshaller(JoinMultiSigWalletRequest.getDefaultInstance())).setResponseMarshaller(ProtoLiteUtils.marshaller(JoinMultiSigWalletResponse.getDefaultInstance())).build();
                    getJoinMultiSigWalletMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static WalletServiceStub newStub(Channel channel) {
        return new WalletServiceStub(channel);
    }

    public static WalletServiceBlockingStub newBlockingStub(Channel channel) {
        return new WalletServiceBlockingStub(channel);
    }

    public static WalletServiceFutureStub newFutureStub(Channel channel) {
        return new WalletServiceFutureStub(channel);
    }

    public static ServiceDescriptor getServiceDescriptor() {
        ServiceDescriptor serviceDescriptor2 = serviceDescriptor;
        if (serviceDescriptor2 == null) {
            synchronized (WalletServiceGrpc.class) {
                serviceDescriptor2 = serviceDescriptor;
                if (serviceDescriptor2 == null) {
                    serviceDescriptor2 = ServiceDescriptor.newBuilder(SERVICE_NAME).addMethod(getCreateSingleSigWalletMethod()).addMethod(getRecoverSingleSigWalletMethod()).addMethod(getMigrateExistingWalletMethod()).addMethod(getCreateMultiSigWalletMethod()).addMethod(getJoinMultiSigWalletMethod()).build();
                    serviceDescriptor = serviceDescriptor2;
                }
            }
        }
        return serviceDescriptor2;
    }
}
