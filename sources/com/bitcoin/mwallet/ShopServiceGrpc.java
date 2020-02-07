package com.bitcoin.mwallet;

import com.bitcoin.mwallet.ShopOuterClass.ReportShopRequest;
import com.bitcoin.mwallet.ShopOuterClass.ReportShopResponse;
import com.bitcoin.mwallet.ShopOuterClass.ShopRequest;
import com.bitcoin.mwallet.ShopOuterClass.ShopResponse;
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

public final class ShopServiceGrpc {
    private static final int METHODID_GET_SHOPS = 0;
    private static final int METHODID_REPORT_SHOP = 1;
    public static final String SERVICE_NAME = "com.bitcoin.mwallet.ShopService";
    private static volatile MethodDescriptor<ShopRequest, ShopResponse> getGetShopsMethod;
    private static volatile MethodDescriptor<ReportShopRequest, ReportShopResponse> getReportShopMethod;
    private static volatile ServiceDescriptor serviceDescriptor;

    private static final class MethodHandlers<Req, Resp> implements UnaryMethod<Req, Resp>, ServerStreamingMethod<Req, Resp>, ClientStreamingMethod<Req, Resp>, BidiStreamingMethod<Req, Resp> {
        private final int methodId;
        private final ShopServiceImplBase serviceImpl;

        MethodHandlers(ShopServiceImplBase shopServiceImplBase, int i) {
            this.serviceImpl = shopServiceImplBase;
            this.methodId = i;
        }

        public void invoke(Req req, StreamObserver<Resp> streamObserver) {
            int i = this.methodId;
            if (i == 0) {
                this.serviceImpl.getShops((ShopRequest) req, streamObserver);
            } else if (i == 1) {
                this.serviceImpl.reportShop((ReportShopRequest) req, streamObserver);
            } else {
                throw new AssertionError();
            }
        }

        public StreamObserver<Req> invoke(StreamObserver<Resp> streamObserver) {
            int i = this.methodId;
            throw new AssertionError();
        }
    }

    public static final class ShopServiceBlockingStub extends AbstractStub<ShopServiceBlockingStub> {
        private ShopServiceBlockingStub(Channel channel) {
            super(channel);
        }

        private ShopServiceBlockingStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        /* access modifiers changed from: protected */
        public ShopServiceBlockingStub build(Channel channel, CallOptions callOptions) {
            return new ShopServiceBlockingStub(channel, callOptions);
        }

        public ShopResponse getShops(ShopRequest shopRequest) {
            return (ShopResponse) ClientCalls.blockingUnaryCall(getChannel(), ShopServiceGrpc.getGetShopsMethod(), getCallOptions(), shopRequest);
        }

        public ReportShopResponse reportShop(ReportShopRequest reportShopRequest) {
            return (ReportShopResponse) ClientCalls.blockingUnaryCall(getChannel(), ShopServiceGrpc.getReportShopMethod(), getCallOptions(), reportShopRequest);
        }
    }

    public static final class ShopServiceFutureStub extends AbstractStub<ShopServiceFutureStub> {
        private ShopServiceFutureStub(Channel channel) {
            super(channel);
        }

        private ShopServiceFutureStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        /* access modifiers changed from: protected */
        public ShopServiceFutureStub build(Channel channel, CallOptions callOptions) {
            return new ShopServiceFutureStub(channel, callOptions);
        }

        public ListenableFuture<ShopResponse> getShops(ShopRequest shopRequest) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(ShopServiceGrpc.getGetShopsMethod(), getCallOptions()), shopRequest);
        }

        public ListenableFuture<ReportShopResponse> reportShop(ReportShopRequest reportShopRequest) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(ShopServiceGrpc.getReportShopMethod(), getCallOptions()), reportShopRequest);
        }
    }

    public static abstract class ShopServiceImplBase implements BindableService {
        public void getShops(ShopRequest shopRequest, StreamObserver<ShopResponse> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(ShopServiceGrpc.getGetShopsMethod(), streamObserver);
        }

        public void reportShop(ReportShopRequest reportShopRequest, StreamObserver<ReportShopResponse> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(ShopServiceGrpc.getReportShopMethod(), streamObserver);
        }

        public final ServerServiceDefinition bindService() {
            return ServerServiceDefinition.builder(ShopServiceGrpc.getServiceDescriptor()).addMethod(ShopServiceGrpc.getGetShopsMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 0))).addMethod(ShopServiceGrpc.getReportShopMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 1))).build();
        }
    }

    public static final class ShopServiceStub extends AbstractStub<ShopServiceStub> {
        private ShopServiceStub(Channel channel) {
            super(channel);
        }

        private ShopServiceStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        /* access modifiers changed from: protected */
        public ShopServiceStub build(Channel channel, CallOptions callOptions) {
            return new ShopServiceStub(channel, callOptions);
        }

        public void getShops(ShopRequest shopRequest, StreamObserver<ShopResponse> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(ShopServiceGrpc.getGetShopsMethod(), getCallOptions()), shopRequest, streamObserver);
        }

        public void reportShop(ReportShopRequest reportShopRequest, StreamObserver<ReportShopResponse> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(ShopServiceGrpc.getReportShopMethod(), getCallOptions()), reportShopRequest, streamObserver);
        }
    }

    private ShopServiceGrpc() {
    }

    @RpcMethod(fullMethodName = "com.bitcoin.mwallet.ShopService/getShops", methodType = MethodType.UNARY, requestType = ShopRequest.class, responseType = ShopResponse.class)
    public static MethodDescriptor<ShopRequest, ShopResponse> getGetShopsMethod() {
        MethodDescriptor<ShopRequest, ShopResponse> methodDescriptor = getGetShopsMethod;
        if (methodDescriptor == null) {
            synchronized (ShopServiceGrpc.class) {
                methodDescriptor = getGetShopsMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "getShops")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoLiteUtils.marshaller(ShopRequest.getDefaultInstance())).setResponseMarshaller(ProtoLiteUtils.marshaller(ShopResponse.getDefaultInstance())).build();
                    getGetShopsMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    @RpcMethod(fullMethodName = "com.bitcoin.mwallet.ShopService/reportShop", methodType = MethodType.UNARY, requestType = ReportShopRequest.class, responseType = ReportShopResponse.class)
    public static MethodDescriptor<ReportShopRequest, ReportShopResponse> getReportShopMethod() {
        MethodDescriptor<ReportShopRequest, ReportShopResponse> methodDescriptor = getReportShopMethod;
        if (methodDescriptor == null) {
            synchronized (ShopServiceGrpc.class) {
                methodDescriptor = getReportShopMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "reportShop")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoLiteUtils.marshaller(ReportShopRequest.getDefaultInstance())).setResponseMarshaller(ProtoLiteUtils.marshaller(ReportShopResponse.getDefaultInstance())).build();
                    getReportShopMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static ShopServiceStub newStub(Channel channel) {
        return new ShopServiceStub(channel);
    }

    public static ShopServiceBlockingStub newBlockingStub(Channel channel) {
        return new ShopServiceBlockingStub(channel);
    }

    public static ShopServiceFutureStub newFutureStub(Channel channel) {
        return new ShopServiceFutureStub(channel);
    }

    public static ServiceDescriptor getServiceDescriptor() {
        ServiceDescriptor serviceDescriptor2 = serviceDescriptor;
        if (serviceDescriptor2 == null) {
            synchronized (ShopServiceGrpc.class) {
                serviceDescriptor2 = serviceDescriptor;
                if (serviceDescriptor2 == null) {
                    serviceDescriptor2 = ServiceDescriptor.newBuilder(SERVICE_NAME).addMethod(getGetShopsMethod()).addMethod(getReportShopMethod()).build();
                    serviceDescriptor = serviceDescriptor2;
                }
            }
        }
        return serviceDescriptor2;
    }
}
