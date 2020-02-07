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

public final class AddressServiceGrpc {
    private static final int METHODID_GET_NEW_MAX_ADDRESS_PATH = 0;
    private static final int METHODID_SCAN_FOR_FUNDS = 1;
    public static final String SERVICE_NAME = "com.bitcoin.mwallet.AddressService";
    private static volatile MethodDescriptor<GetNewMaxAddressPathRequest, GetNewMaxAddressPathResponse> getGetNewMaxAddressPathMethod;
    private static volatile MethodDescriptor<ScanForFundsRequest, ScanForFundsResponse> getScanForFundsMethod;
    private static volatile ServiceDescriptor serviceDescriptor;

    public static final class AddressServiceBlockingStub extends AbstractStub<AddressServiceBlockingStub> {
        private AddressServiceBlockingStub(Channel channel) {
            super(channel);
        }

        private AddressServiceBlockingStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        /* access modifiers changed from: protected */
        public AddressServiceBlockingStub build(Channel channel, CallOptions callOptions) {
            return new AddressServiceBlockingStub(channel, callOptions);
        }

        public GetNewMaxAddressPathResponse getNewMaxAddressPath(GetNewMaxAddressPathRequest getNewMaxAddressPathRequest) {
            return (GetNewMaxAddressPathResponse) ClientCalls.blockingUnaryCall(getChannel(), AddressServiceGrpc.getGetNewMaxAddressPathMethod(), getCallOptions(), getNewMaxAddressPathRequest);
        }

        public ScanForFundsResponse scanForFunds(ScanForFundsRequest scanForFundsRequest) {
            return (ScanForFundsResponse) ClientCalls.blockingUnaryCall(getChannel(), AddressServiceGrpc.getScanForFundsMethod(), getCallOptions(), scanForFundsRequest);
        }
    }

    public static final class AddressServiceFutureStub extends AbstractStub<AddressServiceFutureStub> {
        private AddressServiceFutureStub(Channel channel) {
            super(channel);
        }

        private AddressServiceFutureStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        /* access modifiers changed from: protected */
        public AddressServiceFutureStub build(Channel channel, CallOptions callOptions) {
            return new AddressServiceFutureStub(channel, callOptions);
        }

        public ListenableFuture<GetNewMaxAddressPathResponse> getNewMaxAddressPath(GetNewMaxAddressPathRequest getNewMaxAddressPathRequest) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(AddressServiceGrpc.getGetNewMaxAddressPathMethod(), getCallOptions()), getNewMaxAddressPathRequest);
        }

        public ListenableFuture<ScanForFundsResponse> scanForFunds(ScanForFundsRequest scanForFundsRequest) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(AddressServiceGrpc.getScanForFundsMethod(), getCallOptions()), scanForFundsRequest);
        }
    }

    public static abstract class AddressServiceImplBase implements BindableService {
        public void getNewMaxAddressPath(GetNewMaxAddressPathRequest getNewMaxAddressPathRequest, StreamObserver<GetNewMaxAddressPathResponse> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(AddressServiceGrpc.getGetNewMaxAddressPathMethod(), streamObserver);
        }

        public void scanForFunds(ScanForFundsRequest scanForFundsRequest, StreamObserver<ScanForFundsResponse> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(AddressServiceGrpc.getScanForFundsMethod(), streamObserver);
        }

        public final ServerServiceDefinition bindService() {
            return ServerServiceDefinition.builder(AddressServiceGrpc.getServiceDescriptor()).addMethod(AddressServiceGrpc.getGetNewMaxAddressPathMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 0))).addMethod(AddressServiceGrpc.getScanForFundsMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 1))).build();
        }
    }

    public static final class AddressServiceStub extends AbstractStub<AddressServiceStub> {
        private AddressServiceStub(Channel channel) {
            super(channel);
        }

        private AddressServiceStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        /* access modifiers changed from: protected */
        public AddressServiceStub build(Channel channel, CallOptions callOptions) {
            return new AddressServiceStub(channel, callOptions);
        }

        public void getNewMaxAddressPath(GetNewMaxAddressPathRequest getNewMaxAddressPathRequest, StreamObserver<GetNewMaxAddressPathResponse> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(AddressServiceGrpc.getGetNewMaxAddressPathMethod(), getCallOptions()), getNewMaxAddressPathRequest, streamObserver);
        }

        public void scanForFunds(ScanForFundsRequest scanForFundsRequest, StreamObserver<ScanForFundsResponse> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(AddressServiceGrpc.getScanForFundsMethod(), getCallOptions()), scanForFundsRequest, streamObserver);
        }
    }

    private static final class MethodHandlers<Req, Resp> implements UnaryMethod<Req, Resp>, ServerStreamingMethod<Req, Resp>, ClientStreamingMethod<Req, Resp>, BidiStreamingMethod<Req, Resp> {
        private final int methodId;
        private final AddressServiceImplBase serviceImpl;

        MethodHandlers(AddressServiceImplBase addressServiceImplBase, int i) {
            this.serviceImpl = addressServiceImplBase;
            this.methodId = i;
        }

        public void invoke(Req req, StreamObserver<Resp> streamObserver) {
            int i = this.methodId;
            if (i == 0) {
                this.serviceImpl.getNewMaxAddressPath((GetNewMaxAddressPathRequest) req, streamObserver);
            } else if (i == 1) {
                this.serviceImpl.scanForFunds((ScanForFundsRequest) req, streamObserver);
            } else {
                throw new AssertionError();
            }
        }

        public StreamObserver<Req> invoke(StreamObserver<Resp> streamObserver) {
            int i = this.methodId;
            throw new AssertionError();
        }
    }

    private AddressServiceGrpc() {
    }

    @RpcMethod(fullMethodName = "com.bitcoin.mwallet.AddressService/GetNewMaxAddressPath", methodType = MethodType.UNARY, requestType = GetNewMaxAddressPathRequest.class, responseType = GetNewMaxAddressPathResponse.class)
    public static MethodDescriptor<GetNewMaxAddressPathRequest, GetNewMaxAddressPathResponse> getGetNewMaxAddressPathMethod() {
        MethodDescriptor<GetNewMaxAddressPathRequest, GetNewMaxAddressPathResponse> methodDescriptor = getGetNewMaxAddressPathMethod;
        if (methodDescriptor == null) {
            synchronized (AddressServiceGrpc.class) {
                methodDescriptor = getGetNewMaxAddressPathMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetNewMaxAddressPath")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoLiteUtils.marshaller(GetNewMaxAddressPathRequest.getDefaultInstance())).setResponseMarshaller(ProtoLiteUtils.marshaller(GetNewMaxAddressPathResponse.getDefaultInstance())).build();
                    getGetNewMaxAddressPathMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    @RpcMethod(fullMethodName = "com.bitcoin.mwallet.AddressService/scanForFunds", methodType = MethodType.UNARY, requestType = ScanForFundsRequest.class, responseType = ScanForFundsResponse.class)
    public static MethodDescriptor<ScanForFundsRequest, ScanForFundsResponse> getScanForFundsMethod() {
        MethodDescriptor<ScanForFundsRequest, ScanForFundsResponse> methodDescriptor = getScanForFundsMethod;
        if (methodDescriptor == null) {
            synchronized (AddressServiceGrpc.class) {
                methodDescriptor = getScanForFundsMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "scanForFunds")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoLiteUtils.marshaller(ScanForFundsRequest.getDefaultInstance())).setResponseMarshaller(ProtoLiteUtils.marshaller(ScanForFundsResponse.getDefaultInstance())).build();
                    getScanForFundsMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static AddressServiceStub newStub(Channel channel) {
        return new AddressServiceStub(channel);
    }

    public static AddressServiceBlockingStub newBlockingStub(Channel channel) {
        return new AddressServiceBlockingStub(channel);
    }

    public static AddressServiceFutureStub newFutureStub(Channel channel) {
        return new AddressServiceFutureStub(channel);
    }

    public static ServiceDescriptor getServiceDescriptor() {
        ServiceDescriptor serviceDescriptor2 = serviceDescriptor;
        if (serviceDescriptor2 == null) {
            synchronized (AddressServiceGrpc.class) {
                serviceDescriptor2 = serviceDescriptor;
                if (serviceDescriptor2 == null) {
                    serviceDescriptor2 = ServiceDescriptor.newBuilder(SERVICE_NAME).addMethod(getGetNewMaxAddressPathMethod()).addMethod(getScanForFundsMethod()).build();
                    serviceDescriptor = serviceDescriptor2;
                }
            }
        }
        return serviceDescriptor2;
    }
}
