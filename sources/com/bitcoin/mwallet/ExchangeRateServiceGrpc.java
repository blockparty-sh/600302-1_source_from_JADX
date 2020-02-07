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

public final class ExchangeRateServiceGrpc {
    private static final int METHODID_GET_BTC_EXCHANGE_RATES = 0;
    public static final String SERVICE_NAME = "com.bitcoin.mwallet.ExchangeRateService";
    private static volatile MethodDescriptor<GetBtcExchangeRatesRequest, GetBtcExhchangeRatesResponse> getGetBtcExchangeRatesMethod;
    private static volatile ServiceDescriptor serviceDescriptor;

    public static final class ExchangeRateServiceBlockingStub extends AbstractStub<ExchangeRateServiceBlockingStub> {
        private ExchangeRateServiceBlockingStub(Channel channel) {
            super(channel);
        }

        private ExchangeRateServiceBlockingStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        /* access modifiers changed from: protected */
        public ExchangeRateServiceBlockingStub build(Channel channel, CallOptions callOptions) {
            return new ExchangeRateServiceBlockingStub(channel, callOptions);
        }

        public GetBtcExhchangeRatesResponse getBtcExchangeRates(GetBtcExchangeRatesRequest getBtcExchangeRatesRequest) {
            return (GetBtcExhchangeRatesResponse) ClientCalls.blockingUnaryCall(getChannel(), ExchangeRateServiceGrpc.getGetBtcExchangeRatesMethod(), getCallOptions(), getBtcExchangeRatesRequest);
        }
    }

    public static final class ExchangeRateServiceFutureStub extends AbstractStub<ExchangeRateServiceFutureStub> {
        private ExchangeRateServiceFutureStub(Channel channel) {
            super(channel);
        }

        private ExchangeRateServiceFutureStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        /* access modifiers changed from: protected */
        public ExchangeRateServiceFutureStub build(Channel channel, CallOptions callOptions) {
            return new ExchangeRateServiceFutureStub(channel, callOptions);
        }

        public ListenableFuture<GetBtcExhchangeRatesResponse> getBtcExchangeRates(GetBtcExchangeRatesRequest getBtcExchangeRatesRequest) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(ExchangeRateServiceGrpc.getGetBtcExchangeRatesMethod(), getCallOptions()), getBtcExchangeRatesRequest);
        }
    }

    public static abstract class ExchangeRateServiceImplBase implements BindableService {
        public void getBtcExchangeRates(GetBtcExchangeRatesRequest getBtcExchangeRatesRequest, StreamObserver<GetBtcExhchangeRatesResponse> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(ExchangeRateServiceGrpc.getGetBtcExchangeRatesMethod(), streamObserver);
        }

        public final ServerServiceDefinition bindService() {
            return ServerServiceDefinition.builder(ExchangeRateServiceGrpc.getServiceDescriptor()).addMethod(ExchangeRateServiceGrpc.getGetBtcExchangeRatesMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 0))).build();
        }
    }

    public static final class ExchangeRateServiceStub extends AbstractStub<ExchangeRateServiceStub> {
        private ExchangeRateServiceStub(Channel channel) {
            super(channel);
        }

        private ExchangeRateServiceStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        /* access modifiers changed from: protected */
        public ExchangeRateServiceStub build(Channel channel, CallOptions callOptions) {
            return new ExchangeRateServiceStub(channel, callOptions);
        }

        public void getBtcExchangeRates(GetBtcExchangeRatesRequest getBtcExchangeRatesRequest, StreamObserver<GetBtcExhchangeRatesResponse> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(ExchangeRateServiceGrpc.getGetBtcExchangeRatesMethod(), getCallOptions()), getBtcExchangeRatesRequest, streamObserver);
        }
    }

    private static final class MethodHandlers<Req, Resp> implements UnaryMethod<Req, Resp>, ServerStreamingMethod<Req, Resp>, ClientStreamingMethod<Req, Resp>, BidiStreamingMethod<Req, Resp> {
        private final int methodId;
        private final ExchangeRateServiceImplBase serviceImpl;

        MethodHandlers(ExchangeRateServiceImplBase exchangeRateServiceImplBase, int i) {
            this.serviceImpl = exchangeRateServiceImplBase;
            this.methodId = i;
        }

        public void invoke(Req req, StreamObserver<Resp> streamObserver) {
            if (this.methodId == 0) {
                this.serviceImpl.getBtcExchangeRates((GetBtcExchangeRatesRequest) req, streamObserver);
                return;
            }
            throw new AssertionError();
        }

        public StreamObserver<Req> invoke(StreamObserver<Resp> streamObserver) {
            int i = this.methodId;
            throw new AssertionError();
        }
    }

    private ExchangeRateServiceGrpc() {
    }

    @RpcMethod(fullMethodName = "com.bitcoin.mwallet.ExchangeRateService/getBtcExchangeRates", methodType = MethodType.UNARY, requestType = GetBtcExchangeRatesRequest.class, responseType = GetBtcExhchangeRatesResponse.class)
    public static MethodDescriptor<GetBtcExchangeRatesRequest, GetBtcExhchangeRatesResponse> getGetBtcExchangeRatesMethod() {
        MethodDescriptor<GetBtcExchangeRatesRequest, GetBtcExhchangeRatesResponse> methodDescriptor = getGetBtcExchangeRatesMethod;
        if (methodDescriptor == null) {
            synchronized (ExchangeRateServiceGrpc.class) {
                methodDescriptor = getGetBtcExchangeRatesMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "getBtcExchangeRates")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoLiteUtils.marshaller(GetBtcExchangeRatesRequest.getDefaultInstance())).setResponseMarshaller(ProtoLiteUtils.marshaller(GetBtcExhchangeRatesResponse.getDefaultInstance())).build();
                    getGetBtcExchangeRatesMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static ExchangeRateServiceStub newStub(Channel channel) {
        return new ExchangeRateServiceStub(channel);
    }

    public static ExchangeRateServiceBlockingStub newBlockingStub(Channel channel) {
        return new ExchangeRateServiceBlockingStub(channel);
    }

    public static ExchangeRateServiceFutureStub newFutureStub(Channel channel) {
        return new ExchangeRateServiceFutureStub(channel);
    }

    public static ServiceDescriptor getServiceDescriptor() {
        ServiceDescriptor serviceDescriptor2 = serviceDescriptor;
        if (serviceDescriptor2 == null) {
            synchronized (ExchangeRateServiceGrpc.class) {
                serviceDescriptor2 = serviceDescriptor;
                if (serviceDescriptor2 == null) {
                    serviceDescriptor2 = ServiceDescriptor.newBuilder(SERVICE_NAME).addMethod(getGetBtcExchangeRatesMethod()).build();
                    serviceDescriptor = serviceDescriptor2;
                }
            }
        }
        return serviceDescriptor2;
    }
}
