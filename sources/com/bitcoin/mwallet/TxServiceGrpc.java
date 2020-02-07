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

public final class TxServiceGrpc {
    private static final int METHODID_BIP70BROADCAST_TX = 7;
    private static final int METHODID_BIP70PAYMENT = 6;
    private static final int METHODID_BROADCAST_TX = 0;
    private static final int METHODID_GET_OLD_TX_NOTES = 4;
    private static final int METHODID_GET_TX_HISTORY = 3;
    private static final int METHODID_GET_UTXOS = 1;
    private static final int METHODID_SWEEP_ADDRESSES = 2;
    private static final int METHODID_UPDATE_TX_NOTES = 5;
    public static final String SERVICE_NAME = "com.bitcoin.mwallet.TxService";
    private static volatile MethodDescriptor<Bip70BroadcastTxRequest, Bip70BroadcastTxResponse> getBip70BroadcastTxMethod;
    private static volatile MethodDescriptor<Bip70PaymentRequest, Bip70PaymentResponse> getBip70PaymentMethod;
    private static volatile MethodDescriptor<BroadcastTxRequest, BroadcastTxResponse> getBroadcastTxMethod;
    private static volatile MethodDescriptor<GetOldTxNotesRequest, TxNote> getGetOldTxNotesMethod;
    private static volatile MethodDescriptor<GetTxHistoryRequest, TxHistory> getGetTxHistoryMethod;
    private static volatile MethodDescriptor<GetUtxosRequest, GetUtxosResponse> getGetUtxosMethod;
    private static volatile MethodDescriptor<SweepAddressesRequest, SweepUtxo> getSweepAddressesMethod;
    private static volatile MethodDescriptor<UpdateNotesRequest, UpdateNotesResponse> getUpdateTxNotesMethod;
    private static volatile ServiceDescriptor serviceDescriptor;

    private static final class MethodHandlers<Req, Resp> implements UnaryMethod<Req, Resp>, ServerStreamingMethod<Req, Resp>, ClientStreamingMethod<Req, Resp>, BidiStreamingMethod<Req, Resp> {
        private final int methodId;
        private final TxServiceImplBase serviceImpl;

        MethodHandlers(TxServiceImplBase txServiceImplBase, int i) {
            this.serviceImpl = txServiceImplBase;
            this.methodId = i;
        }

        public void invoke(Req req, StreamObserver<Resp> streamObserver) {
            switch (this.methodId) {
                case 0:
                    this.serviceImpl.broadcastTx((BroadcastTxRequest) req, streamObserver);
                    return;
                case 1:
                    this.serviceImpl.getUtxos((GetUtxosRequest) req, streamObserver);
                    return;
                case 2:
                    this.serviceImpl.sweepAddresses((SweepAddressesRequest) req, streamObserver);
                    return;
                case 3:
                    this.serviceImpl.getTxHistory((GetTxHistoryRequest) req, streamObserver);
                    return;
                case 4:
                    this.serviceImpl.getOldTxNotes((GetOldTxNotesRequest) req, streamObserver);
                    return;
                case 5:
                    this.serviceImpl.updateTxNotes((UpdateNotesRequest) req, streamObserver);
                    return;
                case 6:
                    this.serviceImpl.bip70Payment((Bip70PaymentRequest) req, streamObserver);
                    return;
                case 7:
                    this.serviceImpl.bip70BroadcastTx((Bip70BroadcastTxRequest) req, streamObserver);
                    return;
                default:
                    throw new AssertionError();
            }
        }

        public StreamObserver<Req> invoke(StreamObserver<Resp> streamObserver) {
            int i = this.methodId;
            throw new AssertionError();
        }
    }

    public static final class TxServiceBlockingStub extends AbstractStub<TxServiceBlockingStub> {
        private TxServiceBlockingStub(Channel channel) {
            super(channel);
        }

        private TxServiceBlockingStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        /* access modifiers changed from: protected */
        public TxServiceBlockingStub build(Channel channel, CallOptions callOptions) {
            return new TxServiceBlockingStub(channel, callOptions);
        }

        public BroadcastTxResponse broadcastTx(BroadcastTxRequest broadcastTxRequest) {
            return (BroadcastTxResponse) ClientCalls.blockingUnaryCall(getChannel(), TxServiceGrpc.getBroadcastTxMethod(), getCallOptions(), broadcastTxRequest);
        }

        public GetUtxosResponse getUtxos(GetUtxosRequest getUtxosRequest) {
            return (GetUtxosResponse) ClientCalls.blockingUnaryCall(getChannel(), TxServiceGrpc.getGetUtxosMethod(), getCallOptions(), getUtxosRequest);
        }

        public Iterator<SweepUtxo> sweepAddresses(SweepAddressesRequest sweepAddressesRequest) {
            return ClientCalls.blockingServerStreamingCall(getChannel(), TxServiceGrpc.getSweepAddressesMethod(), getCallOptions(), sweepAddressesRequest);
        }

        public Iterator<TxHistory> getTxHistory(GetTxHistoryRequest getTxHistoryRequest) {
            return ClientCalls.blockingServerStreamingCall(getChannel(), TxServiceGrpc.getGetTxHistoryMethod(), getCallOptions(), getTxHistoryRequest);
        }

        public Iterator<TxNote> getOldTxNotes(GetOldTxNotesRequest getOldTxNotesRequest) {
            return ClientCalls.blockingServerStreamingCall(getChannel(), TxServiceGrpc.getGetOldTxNotesMethod(), getCallOptions(), getOldTxNotesRequest);
        }

        public UpdateNotesResponse updateTxNotes(UpdateNotesRequest updateNotesRequest) {
            return (UpdateNotesResponse) ClientCalls.blockingUnaryCall(getChannel(), TxServiceGrpc.getUpdateTxNotesMethod(), getCallOptions(), updateNotesRequest);
        }

        public Bip70PaymentResponse bip70Payment(Bip70PaymentRequest bip70PaymentRequest) {
            return (Bip70PaymentResponse) ClientCalls.blockingUnaryCall(getChannel(), TxServiceGrpc.getBip70PaymentMethod(), getCallOptions(), bip70PaymentRequest);
        }

        public Bip70BroadcastTxResponse bip70BroadcastTx(Bip70BroadcastTxRequest bip70BroadcastTxRequest) {
            return (Bip70BroadcastTxResponse) ClientCalls.blockingUnaryCall(getChannel(), TxServiceGrpc.getBip70BroadcastTxMethod(), getCallOptions(), bip70BroadcastTxRequest);
        }
    }

    public static final class TxServiceFutureStub extends AbstractStub<TxServiceFutureStub> {
        private TxServiceFutureStub(Channel channel) {
            super(channel);
        }

        private TxServiceFutureStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        /* access modifiers changed from: protected */
        public TxServiceFutureStub build(Channel channel, CallOptions callOptions) {
            return new TxServiceFutureStub(channel, callOptions);
        }

        public ListenableFuture<BroadcastTxResponse> broadcastTx(BroadcastTxRequest broadcastTxRequest) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(TxServiceGrpc.getBroadcastTxMethod(), getCallOptions()), broadcastTxRequest);
        }

        public ListenableFuture<GetUtxosResponse> getUtxos(GetUtxosRequest getUtxosRequest) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(TxServiceGrpc.getGetUtxosMethod(), getCallOptions()), getUtxosRequest);
        }

        public ListenableFuture<UpdateNotesResponse> updateTxNotes(UpdateNotesRequest updateNotesRequest) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(TxServiceGrpc.getUpdateTxNotesMethod(), getCallOptions()), updateNotesRequest);
        }

        public ListenableFuture<Bip70PaymentResponse> bip70Payment(Bip70PaymentRequest bip70PaymentRequest) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(TxServiceGrpc.getBip70PaymentMethod(), getCallOptions()), bip70PaymentRequest);
        }

        public ListenableFuture<Bip70BroadcastTxResponse> bip70BroadcastTx(Bip70BroadcastTxRequest bip70BroadcastTxRequest) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(TxServiceGrpc.getBip70BroadcastTxMethod(), getCallOptions()), bip70BroadcastTxRequest);
        }
    }

    public static abstract class TxServiceImplBase implements BindableService {
        public void broadcastTx(BroadcastTxRequest broadcastTxRequest, StreamObserver<BroadcastTxResponse> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(TxServiceGrpc.getBroadcastTxMethod(), streamObserver);
        }

        public void getUtxos(GetUtxosRequest getUtxosRequest, StreamObserver<GetUtxosResponse> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(TxServiceGrpc.getGetUtxosMethod(), streamObserver);
        }

        public void sweepAddresses(SweepAddressesRequest sweepAddressesRequest, StreamObserver<SweepUtxo> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(TxServiceGrpc.getSweepAddressesMethod(), streamObserver);
        }

        public void getTxHistory(GetTxHistoryRequest getTxHistoryRequest, StreamObserver<TxHistory> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(TxServiceGrpc.getGetTxHistoryMethod(), streamObserver);
        }

        public void getOldTxNotes(GetOldTxNotesRequest getOldTxNotesRequest, StreamObserver<TxNote> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(TxServiceGrpc.getGetOldTxNotesMethod(), streamObserver);
        }

        public void updateTxNotes(UpdateNotesRequest updateNotesRequest, StreamObserver<UpdateNotesResponse> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(TxServiceGrpc.getUpdateTxNotesMethod(), streamObserver);
        }

        public void bip70Payment(Bip70PaymentRequest bip70PaymentRequest, StreamObserver<Bip70PaymentResponse> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(TxServiceGrpc.getBip70PaymentMethod(), streamObserver);
        }

        public void bip70BroadcastTx(Bip70BroadcastTxRequest bip70BroadcastTxRequest, StreamObserver<Bip70BroadcastTxResponse> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(TxServiceGrpc.getBip70BroadcastTxMethod(), streamObserver);
        }

        public final ServerServiceDefinition bindService() {
            return ServerServiceDefinition.builder(TxServiceGrpc.getServiceDescriptor()).addMethod(TxServiceGrpc.getBroadcastTxMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 0))).addMethod(TxServiceGrpc.getGetUtxosMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 1))).addMethod(TxServiceGrpc.getSweepAddressesMethod(), ServerCalls.asyncServerStreamingCall(new MethodHandlers(this, 2))).addMethod(TxServiceGrpc.getGetTxHistoryMethod(), ServerCalls.asyncServerStreamingCall(new MethodHandlers(this, 3))).addMethod(TxServiceGrpc.getGetOldTxNotesMethod(), ServerCalls.asyncServerStreamingCall(new MethodHandlers(this, 4))).addMethod(TxServiceGrpc.getUpdateTxNotesMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 5))).addMethod(TxServiceGrpc.getBip70PaymentMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 6))).addMethod(TxServiceGrpc.getBip70BroadcastTxMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 7))).build();
        }
    }

    public static final class TxServiceStub extends AbstractStub<TxServiceStub> {
        private TxServiceStub(Channel channel) {
            super(channel);
        }

        private TxServiceStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        /* access modifiers changed from: protected */
        public TxServiceStub build(Channel channel, CallOptions callOptions) {
            return new TxServiceStub(channel, callOptions);
        }

        public void broadcastTx(BroadcastTxRequest broadcastTxRequest, StreamObserver<BroadcastTxResponse> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(TxServiceGrpc.getBroadcastTxMethod(), getCallOptions()), broadcastTxRequest, streamObserver);
        }

        public void getUtxos(GetUtxosRequest getUtxosRequest, StreamObserver<GetUtxosResponse> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(TxServiceGrpc.getGetUtxosMethod(), getCallOptions()), getUtxosRequest, streamObserver);
        }

        public void sweepAddresses(SweepAddressesRequest sweepAddressesRequest, StreamObserver<SweepUtxo> streamObserver) {
            ClientCalls.asyncServerStreamingCall(getChannel().newCall(TxServiceGrpc.getSweepAddressesMethod(), getCallOptions()), sweepAddressesRequest, streamObserver);
        }

        public void getTxHistory(GetTxHistoryRequest getTxHistoryRequest, StreamObserver<TxHistory> streamObserver) {
            ClientCalls.asyncServerStreamingCall(getChannel().newCall(TxServiceGrpc.getGetTxHistoryMethod(), getCallOptions()), getTxHistoryRequest, streamObserver);
        }

        public void getOldTxNotes(GetOldTxNotesRequest getOldTxNotesRequest, StreamObserver<TxNote> streamObserver) {
            ClientCalls.asyncServerStreamingCall(getChannel().newCall(TxServiceGrpc.getGetOldTxNotesMethod(), getCallOptions()), getOldTxNotesRequest, streamObserver);
        }

        public void updateTxNotes(UpdateNotesRequest updateNotesRequest, StreamObserver<UpdateNotesResponse> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(TxServiceGrpc.getUpdateTxNotesMethod(), getCallOptions()), updateNotesRequest, streamObserver);
        }

        public void bip70Payment(Bip70PaymentRequest bip70PaymentRequest, StreamObserver<Bip70PaymentResponse> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(TxServiceGrpc.getBip70PaymentMethod(), getCallOptions()), bip70PaymentRequest, streamObserver);
        }

        public void bip70BroadcastTx(Bip70BroadcastTxRequest bip70BroadcastTxRequest, StreamObserver<Bip70BroadcastTxResponse> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(TxServiceGrpc.getBip70BroadcastTxMethod(), getCallOptions()), bip70BroadcastTxRequest, streamObserver);
        }
    }

    private TxServiceGrpc() {
    }

    @RpcMethod(fullMethodName = "com.bitcoin.mwallet.TxService/broadcastTx", methodType = MethodType.UNARY, requestType = BroadcastTxRequest.class, responseType = BroadcastTxResponse.class)
    public static MethodDescriptor<BroadcastTxRequest, BroadcastTxResponse> getBroadcastTxMethod() {
        MethodDescriptor<BroadcastTxRequest, BroadcastTxResponse> methodDescriptor = getBroadcastTxMethod;
        if (methodDescriptor == null) {
            synchronized (TxServiceGrpc.class) {
                methodDescriptor = getBroadcastTxMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "broadcastTx")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoLiteUtils.marshaller(BroadcastTxRequest.getDefaultInstance())).setResponseMarshaller(ProtoLiteUtils.marshaller(BroadcastTxResponse.getDefaultInstance())).build();
                    getBroadcastTxMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    @RpcMethod(fullMethodName = "com.bitcoin.mwallet.TxService/getUtxos", methodType = MethodType.UNARY, requestType = GetUtxosRequest.class, responseType = GetUtxosResponse.class)
    public static MethodDescriptor<GetUtxosRequest, GetUtxosResponse> getGetUtxosMethod() {
        MethodDescriptor<GetUtxosRequest, GetUtxosResponse> methodDescriptor = getGetUtxosMethod;
        if (methodDescriptor == null) {
            synchronized (TxServiceGrpc.class) {
                methodDescriptor = getGetUtxosMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "getUtxos")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoLiteUtils.marshaller(GetUtxosRequest.getDefaultInstance())).setResponseMarshaller(ProtoLiteUtils.marshaller(GetUtxosResponse.getDefaultInstance())).build();
                    getGetUtxosMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    @RpcMethod(fullMethodName = "com.bitcoin.mwallet.TxService/sweepAddresses", methodType = MethodType.SERVER_STREAMING, requestType = SweepAddressesRequest.class, responseType = SweepUtxo.class)
    public static MethodDescriptor<SweepAddressesRequest, SweepUtxo> getSweepAddressesMethod() {
        MethodDescriptor<SweepAddressesRequest, SweepUtxo> methodDescriptor = getSweepAddressesMethod;
        if (methodDescriptor == null) {
            synchronized (TxServiceGrpc.class) {
                methodDescriptor = getSweepAddressesMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodType.SERVER_STREAMING).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "sweepAddresses")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoLiteUtils.marshaller(SweepAddressesRequest.getDefaultInstance())).setResponseMarshaller(ProtoLiteUtils.marshaller(SweepUtxo.getDefaultInstance())).build();
                    getSweepAddressesMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    @RpcMethod(fullMethodName = "com.bitcoin.mwallet.TxService/getTxHistory", methodType = MethodType.SERVER_STREAMING, requestType = GetTxHistoryRequest.class, responseType = TxHistory.class)
    public static MethodDescriptor<GetTxHistoryRequest, TxHistory> getGetTxHistoryMethod() {
        MethodDescriptor<GetTxHistoryRequest, TxHistory> methodDescriptor = getGetTxHistoryMethod;
        if (methodDescriptor == null) {
            synchronized (TxServiceGrpc.class) {
                methodDescriptor = getGetTxHistoryMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodType.SERVER_STREAMING).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "getTxHistory")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoLiteUtils.marshaller(GetTxHistoryRequest.getDefaultInstance())).setResponseMarshaller(ProtoLiteUtils.marshaller(TxHistory.getDefaultInstance())).build();
                    getGetTxHistoryMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    @RpcMethod(fullMethodName = "com.bitcoin.mwallet.TxService/getOldTxNotes", methodType = MethodType.SERVER_STREAMING, requestType = GetOldTxNotesRequest.class, responseType = TxNote.class)
    public static MethodDescriptor<GetOldTxNotesRequest, TxNote> getGetOldTxNotesMethod() {
        MethodDescriptor<GetOldTxNotesRequest, TxNote> methodDescriptor = getGetOldTxNotesMethod;
        if (methodDescriptor == null) {
            synchronized (TxServiceGrpc.class) {
                methodDescriptor = getGetOldTxNotesMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodType.SERVER_STREAMING).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "getOldTxNotes")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoLiteUtils.marshaller(GetOldTxNotesRequest.getDefaultInstance())).setResponseMarshaller(ProtoLiteUtils.marshaller(TxNote.getDefaultInstance())).build();
                    getGetOldTxNotesMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    @RpcMethod(fullMethodName = "com.bitcoin.mwallet.TxService/updateTxNotes", methodType = MethodType.UNARY, requestType = UpdateNotesRequest.class, responseType = UpdateNotesResponse.class)
    public static MethodDescriptor<UpdateNotesRequest, UpdateNotesResponse> getUpdateTxNotesMethod() {
        MethodDescriptor<UpdateNotesRequest, UpdateNotesResponse> methodDescriptor = getUpdateTxNotesMethod;
        if (methodDescriptor == null) {
            synchronized (TxServiceGrpc.class) {
                methodDescriptor = getUpdateTxNotesMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "updateTxNotes")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoLiteUtils.marshaller(UpdateNotesRequest.getDefaultInstance())).setResponseMarshaller(ProtoLiteUtils.marshaller(UpdateNotesResponse.getDefaultInstance())).build();
                    getUpdateTxNotesMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    @RpcMethod(fullMethodName = "com.bitcoin.mwallet.TxService/bip70Payment", methodType = MethodType.UNARY, requestType = Bip70PaymentRequest.class, responseType = Bip70PaymentResponse.class)
    public static MethodDescriptor<Bip70PaymentRequest, Bip70PaymentResponse> getBip70PaymentMethod() {
        MethodDescriptor<Bip70PaymentRequest, Bip70PaymentResponse> methodDescriptor = getBip70PaymentMethod;
        if (methodDescriptor == null) {
            synchronized (TxServiceGrpc.class) {
                methodDescriptor = getBip70PaymentMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "bip70Payment")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoLiteUtils.marshaller(Bip70PaymentRequest.getDefaultInstance())).setResponseMarshaller(ProtoLiteUtils.marshaller(Bip70PaymentResponse.getDefaultInstance())).build();
                    getBip70PaymentMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    @RpcMethod(fullMethodName = "com.bitcoin.mwallet.TxService/bip70BroadcastTx", methodType = MethodType.UNARY, requestType = Bip70BroadcastTxRequest.class, responseType = Bip70BroadcastTxResponse.class)
    public static MethodDescriptor<Bip70BroadcastTxRequest, Bip70BroadcastTxResponse> getBip70BroadcastTxMethod() {
        MethodDescriptor<Bip70BroadcastTxRequest, Bip70BroadcastTxResponse> methodDescriptor = getBip70BroadcastTxMethod;
        if (methodDescriptor == null) {
            synchronized (TxServiceGrpc.class) {
                methodDescriptor = getBip70BroadcastTxMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "bip70BroadcastTx")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoLiteUtils.marshaller(Bip70BroadcastTxRequest.getDefaultInstance())).setResponseMarshaller(ProtoLiteUtils.marshaller(Bip70BroadcastTxResponse.getDefaultInstance())).build();
                    getBip70BroadcastTxMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static TxServiceStub newStub(Channel channel) {
        return new TxServiceStub(channel);
    }

    public static TxServiceBlockingStub newBlockingStub(Channel channel) {
        return new TxServiceBlockingStub(channel);
    }

    public static TxServiceFutureStub newFutureStub(Channel channel) {
        return new TxServiceFutureStub(channel);
    }

    public static ServiceDescriptor getServiceDescriptor() {
        ServiceDescriptor serviceDescriptor2 = serviceDescriptor;
        if (serviceDescriptor2 == null) {
            synchronized (TxServiceGrpc.class) {
                serviceDescriptor2 = serviceDescriptor;
                if (serviceDescriptor2 == null) {
                    serviceDescriptor2 = ServiceDescriptor.newBuilder(SERVICE_NAME).addMethod(getBroadcastTxMethod()).addMethod(getGetUtxosMethod()).addMethod(getSweepAddressesMethod()).addMethod(getGetTxHistoryMethod()).addMethod(getGetOldTxNotesMethod()).addMethod(getUpdateTxNotesMethod()).addMethod(getBip70PaymentMethod()).addMethod(getBip70BroadcastTxMethod()).build();
                    serviceDescriptor = serviceDescriptor2;
                }
            }
        }
        return serviceDescriptor2;
    }
}
