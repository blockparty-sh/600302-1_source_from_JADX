package com.bitcoin.mwallet;

import com.google.common.util.concurrent.ListenableFuture;
import java.util.Iterator;
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

public final class MultiSigServiceGrpc {
    private static final int METHODID_CREATE_AND_SIGN_PROPOSAL = 0;
    private static final int METHODID_FETCH_CREATED_PROPOSAL = 3;
    private static final int METHODID_REJECT_PROPOSAL = 2;
    private static final int METHODID_SIGN_PROPOSAL = 1;
    public static final String SERVICE_NAME = "com.bitcoin.mwallet.MultiSigService";
    private static volatile MethodDescriptor<CreateMultiSigProposalRequest, CreateMultiSigProposalResponse> getCreateAndSignProposalMethod;
    private static volatile MethodDescriptor<FetchCreatedProposalRequest, FetchCreatedProposalResponse> getFetchCreatedProposalMethod;
    private static volatile MethodDescriptor<RejectProposalRequest, RejectProposalResponse> getRejectProposalMethod;
    private static volatile MethodDescriptor<SignMultiSigProposalRequest, SignMultiSigProposalResponse> getSignProposalMethod;
    private static volatile ServiceDescriptor serviceDescriptor;

    private static final class MethodHandlers<Req, Resp> implements UnaryMethod<Req, Resp>, ServerStreamingMethod<Req, Resp>, ClientStreamingMethod<Req, Resp>, BidiStreamingMethod<Req, Resp> {
        private final int methodId;
        private final MultiSigServiceImplBase serviceImpl;

        MethodHandlers(MultiSigServiceImplBase multiSigServiceImplBase, int i) {
            this.serviceImpl = multiSigServiceImplBase;
            this.methodId = i;
        }

        public void invoke(Req req, StreamObserver<Resp> streamObserver) {
            int i = this.methodId;
            if (i == 0) {
                this.serviceImpl.createAndSignProposal((CreateMultiSigProposalRequest) req, streamObserver);
            } else if (i == 1) {
                this.serviceImpl.signProposal((SignMultiSigProposalRequest) req, streamObserver);
            } else if (i == 2) {
                this.serviceImpl.rejectProposal((RejectProposalRequest) req, streamObserver);
            } else if (i == 3) {
                this.serviceImpl.fetchCreatedProposal((FetchCreatedProposalRequest) req, streamObserver);
            } else {
                throw new AssertionError();
            }
        }

        public StreamObserver<Req> invoke(StreamObserver<Resp> streamObserver) {
            int i = this.methodId;
            throw new AssertionError();
        }
    }

    public static final class MultiSigServiceBlockingStub extends AbstractStub<MultiSigServiceBlockingStub> {
        private MultiSigServiceBlockingStub(Channel channel) {
            super(channel);
        }

        private MultiSigServiceBlockingStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        /* access modifiers changed from: protected */
        public MultiSigServiceBlockingStub build(Channel channel, CallOptions callOptions) {
            return new MultiSigServiceBlockingStub(channel, callOptions);
        }

        public CreateMultiSigProposalResponse createAndSignProposal(CreateMultiSigProposalRequest createMultiSigProposalRequest) {
            return (CreateMultiSigProposalResponse) ClientCalls.blockingUnaryCall(getChannel(), MultiSigServiceGrpc.getCreateAndSignProposalMethod(), getCallOptions(), createMultiSigProposalRequest);
        }

        public SignMultiSigProposalResponse signProposal(SignMultiSigProposalRequest signMultiSigProposalRequest) {
            return (SignMultiSigProposalResponse) ClientCalls.blockingUnaryCall(getChannel(), MultiSigServiceGrpc.getSignProposalMethod(), getCallOptions(), signMultiSigProposalRequest);
        }

        public RejectProposalResponse rejectProposal(RejectProposalRequest rejectProposalRequest) {
            return (RejectProposalResponse) ClientCalls.blockingUnaryCall(getChannel(), MultiSigServiceGrpc.getRejectProposalMethod(), getCallOptions(), rejectProposalRequest);
        }

        public Iterator<FetchCreatedProposalResponse> fetchCreatedProposal(FetchCreatedProposalRequest fetchCreatedProposalRequest) {
            return ClientCalls.blockingServerStreamingCall(getChannel(), MultiSigServiceGrpc.getFetchCreatedProposalMethod(), getCallOptions(), fetchCreatedProposalRequest);
        }
    }

    public static final class MultiSigServiceFutureStub extends AbstractStub<MultiSigServiceFutureStub> {
        private MultiSigServiceFutureStub(Channel channel) {
            super(channel);
        }

        private MultiSigServiceFutureStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        /* access modifiers changed from: protected */
        public MultiSigServiceFutureStub build(Channel channel, CallOptions callOptions) {
            return new MultiSigServiceFutureStub(channel, callOptions);
        }

        public ListenableFuture<CreateMultiSigProposalResponse> createAndSignProposal(CreateMultiSigProposalRequest createMultiSigProposalRequest) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(MultiSigServiceGrpc.getCreateAndSignProposalMethod(), getCallOptions()), createMultiSigProposalRequest);
        }

        public ListenableFuture<SignMultiSigProposalResponse> signProposal(SignMultiSigProposalRequest signMultiSigProposalRequest) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(MultiSigServiceGrpc.getSignProposalMethod(), getCallOptions()), signMultiSigProposalRequest);
        }

        public ListenableFuture<RejectProposalResponse> rejectProposal(RejectProposalRequest rejectProposalRequest) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(MultiSigServiceGrpc.getRejectProposalMethod(), getCallOptions()), rejectProposalRequest);
        }
    }

    public static abstract class MultiSigServiceImplBase implements BindableService {
        public void createAndSignProposal(CreateMultiSigProposalRequest createMultiSigProposalRequest, StreamObserver<CreateMultiSigProposalResponse> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(MultiSigServiceGrpc.getCreateAndSignProposalMethod(), streamObserver);
        }

        public void signProposal(SignMultiSigProposalRequest signMultiSigProposalRequest, StreamObserver<SignMultiSigProposalResponse> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(MultiSigServiceGrpc.getSignProposalMethod(), streamObserver);
        }

        public void rejectProposal(RejectProposalRequest rejectProposalRequest, StreamObserver<RejectProposalResponse> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(MultiSigServiceGrpc.getRejectProposalMethod(), streamObserver);
        }

        public void fetchCreatedProposal(FetchCreatedProposalRequest fetchCreatedProposalRequest, StreamObserver<FetchCreatedProposalResponse> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(MultiSigServiceGrpc.getFetchCreatedProposalMethod(), streamObserver);
        }

        public final ServerServiceDefinition bindService() {
            return ServerServiceDefinition.builder(MultiSigServiceGrpc.getServiceDescriptor()).addMethod(MultiSigServiceGrpc.getCreateAndSignProposalMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 0))).addMethod(MultiSigServiceGrpc.getSignProposalMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 1))).addMethod(MultiSigServiceGrpc.getRejectProposalMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 2))).addMethod(MultiSigServiceGrpc.getFetchCreatedProposalMethod(), ServerCalls.asyncServerStreamingCall(new MethodHandlers(this, 3))).build();
        }
    }

    public static final class MultiSigServiceStub extends AbstractStub<MultiSigServiceStub> {
        private MultiSigServiceStub(Channel channel) {
            super(channel);
        }

        private MultiSigServiceStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        /* access modifiers changed from: protected */
        public MultiSigServiceStub build(Channel channel, CallOptions callOptions) {
            return new MultiSigServiceStub(channel, callOptions);
        }

        public void createAndSignProposal(CreateMultiSigProposalRequest createMultiSigProposalRequest, StreamObserver<CreateMultiSigProposalResponse> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(MultiSigServiceGrpc.getCreateAndSignProposalMethod(), getCallOptions()), createMultiSigProposalRequest, streamObserver);
        }

        public void signProposal(SignMultiSigProposalRequest signMultiSigProposalRequest, StreamObserver<SignMultiSigProposalResponse> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(MultiSigServiceGrpc.getSignProposalMethod(), getCallOptions()), signMultiSigProposalRequest, streamObserver);
        }

        public void rejectProposal(RejectProposalRequest rejectProposalRequest, StreamObserver<RejectProposalResponse> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(MultiSigServiceGrpc.getRejectProposalMethod(), getCallOptions()), rejectProposalRequest, streamObserver);
        }

        public void fetchCreatedProposal(FetchCreatedProposalRequest fetchCreatedProposalRequest, StreamObserver<FetchCreatedProposalResponse> streamObserver) {
            ClientCalls.asyncServerStreamingCall(getChannel().newCall(MultiSigServiceGrpc.getFetchCreatedProposalMethod(), getCallOptions()), fetchCreatedProposalRequest, streamObserver);
        }
    }

    private MultiSigServiceGrpc() {
    }

    @RpcMethod(fullMethodName = "com.bitcoin.mwallet.MultiSigService/createAndSignProposal", methodType = MethodType.UNARY, requestType = CreateMultiSigProposalRequest.class, responseType = CreateMultiSigProposalResponse.class)
    public static MethodDescriptor<CreateMultiSigProposalRequest, CreateMultiSigProposalResponse> getCreateAndSignProposalMethod() {
        MethodDescriptor<CreateMultiSigProposalRequest, CreateMultiSigProposalResponse> methodDescriptor = getCreateAndSignProposalMethod;
        if (methodDescriptor == null) {
            synchronized (MultiSigServiceGrpc.class) {
                methodDescriptor = getCreateAndSignProposalMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "createAndSignProposal")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoLiteUtils.marshaller(CreateMultiSigProposalRequest.getDefaultInstance())).setResponseMarshaller(ProtoLiteUtils.marshaller(CreateMultiSigProposalResponse.getDefaultInstance())).build();
                    getCreateAndSignProposalMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    @RpcMethod(fullMethodName = "com.bitcoin.mwallet.MultiSigService/signProposal", methodType = MethodType.UNARY, requestType = SignMultiSigProposalRequest.class, responseType = SignMultiSigProposalResponse.class)
    public static MethodDescriptor<SignMultiSigProposalRequest, SignMultiSigProposalResponse> getSignProposalMethod() {
        MethodDescriptor<SignMultiSigProposalRequest, SignMultiSigProposalResponse> methodDescriptor = getSignProposalMethod;
        if (methodDescriptor == null) {
            synchronized (MultiSigServiceGrpc.class) {
                methodDescriptor = getSignProposalMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "signProposal")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoLiteUtils.marshaller(SignMultiSigProposalRequest.getDefaultInstance())).setResponseMarshaller(ProtoLiteUtils.marshaller(SignMultiSigProposalResponse.getDefaultInstance())).build();
                    getSignProposalMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    @RpcMethod(fullMethodName = "com.bitcoin.mwallet.MultiSigService/rejectProposal", methodType = MethodType.UNARY, requestType = RejectProposalRequest.class, responseType = RejectProposalResponse.class)
    public static MethodDescriptor<RejectProposalRequest, RejectProposalResponse> getRejectProposalMethod() {
        MethodDescriptor<RejectProposalRequest, RejectProposalResponse> methodDescriptor = getRejectProposalMethod;
        if (methodDescriptor == null) {
            synchronized (MultiSigServiceGrpc.class) {
                methodDescriptor = getRejectProposalMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "rejectProposal")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoLiteUtils.marshaller(RejectProposalRequest.getDefaultInstance())).setResponseMarshaller(ProtoLiteUtils.marshaller(RejectProposalResponse.getDefaultInstance())).build();
                    getRejectProposalMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    @RpcMethod(fullMethodName = "com.bitcoin.mwallet.MultiSigService/fetchCreatedProposal", methodType = MethodType.SERVER_STREAMING, requestType = FetchCreatedProposalRequest.class, responseType = FetchCreatedProposalResponse.class)
    public static MethodDescriptor<FetchCreatedProposalRequest, FetchCreatedProposalResponse> getFetchCreatedProposalMethod() {
        MethodDescriptor<FetchCreatedProposalRequest, FetchCreatedProposalResponse> methodDescriptor = getFetchCreatedProposalMethod;
        if (methodDescriptor == null) {
            synchronized (MultiSigServiceGrpc.class) {
                methodDescriptor = getFetchCreatedProposalMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodType.SERVER_STREAMING).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "fetchCreatedProposal")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoLiteUtils.marshaller(FetchCreatedProposalRequest.getDefaultInstance())).setResponseMarshaller(ProtoLiteUtils.marshaller(FetchCreatedProposalResponse.getDefaultInstance())).build();
                    getFetchCreatedProposalMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MultiSigServiceStub newStub(Channel channel) {
        return new MultiSigServiceStub(channel);
    }

    public static MultiSigServiceBlockingStub newBlockingStub(Channel channel) {
        return new MultiSigServiceBlockingStub(channel);
    }

    public static MultiSigServiceFutureStub newFutureStub(Channel channel) {
        return new MultiSigServiceFutureStub(channel);
    }

    public static ServiceDescriptor getServiceDescriptor() {
        ServiceDescriptor serviceDescriptor2 = serviceDescriptor;
        if (serviceDescriptor2 == null) {
            synchronized (MultiSigServiceGrpc.class) {
                serviceDescriptor2 = serviceDescriptor;
                if (serviceDescriptor2 == null) {
                    serviceDescriptor2 = ServiceDescriptor.newBuilder(SERVICE_NAME).addMethod(getCreateAndSignProposalMethod()).addMethod(getSignProposalMethod()).addMethod(getRejectProposalMethod()).addMethod(getFetchCreatedProposalMethod()).build();
                    serviceDescriptor = serviceDescriptor2;
                }
            }
        }
        return serviceDescriptor2;
    }
}
