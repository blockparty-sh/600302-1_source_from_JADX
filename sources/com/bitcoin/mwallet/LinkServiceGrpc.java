package com.bitcoin.mwallet;

import com.bitcoin.mwallet.LinkOuterClass.LinkRequest;
import com.bitcoin.mwallet.LinkOuterClass.LinkResponse;
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

public final class LinkServiceGrpc {
    private static final int METHODID_GET_LINKS = 0;
    public static final String SERVICE_NAME = "com.bitcoin.mwallet.LinkService";
    private static volatile MethodDescriptor<LinkRequest, LinkResponse> getGetLinksMethod;
    private static volatile ServiceDescriptor serviceDescriptor;

    public static final class LinkServiceBlockingStub extends AbstractStub<LinkServiceBlockingStub> {
        private LinkServiceBlockingStub(Channel channel) {
            super(channel);
        }

        private LinkServiceBlockingStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        /* access modifiers changed from: protected */
        public LinkServiceBlockingStub build(Channel channel, CallOptions callOptions) {
            return new LinkServiceBlockingStub(channel, callOptions);
        }

        public LinkResponse getLinks(LinkRequest linkRequest) {
            return (LinkResponse) ClientCalls.blockingUnaryCall(getChannel(), LinkServiceGrpc.getGetLinksMethod(), getCallOptions(), linkRequest);
        }
    }

    public static final class LinkServiceFutureStub extends AbstractStub<LinkServiceFutureStub> {
        private LinkServiceFutureStub(Channel channel) {
            super(channel);
        }

        private LinkServiceFutureStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        /* access modifiers changed from: protected */
        public LinkServiceFutureStub build(Channel channel, CallOptions callOptions) {
            return new LinkServiceFutureStub(channel, callOptions);
        }

        public ListenableFuture<LinkResponse> getLinks(LinkRequest linkRequest) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(LinkServiceGrpc.getGetLinksMethod(), getCallOptions()), linkRequest);
        }
    }

    public static abstract class LinkServiceImplBase implements BindableService {
        public void getLinks(LinkRequest linkRequest, StreamObserver<LinkResponse> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(LinkServiceGrpc.getGetLinksMethod(), streamObserver);
        }

        public final ServerServiceDefinition bindService() {
            return ServerServiceDefinition.builder(LinkServiceGrpc.getServiceDescriptor()).addMethod(LinkServiceGrpc.getGetLinksMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 0))).build();
        }
    }

    public static final class LinkServiceStub extends AbstractStub<LinkServiceStub> {
        private LinkServiceStub(Channel channel) {
            super(channel);
        }

        private LinkServiceStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        /* access modifiers changed from: protected */
        public LinkServiceStub build(Channel channel, CallOptions callOptions) {
            return new LinkServiceStub(channel, callOptions);
        }

        public void getLinks(LinkRequest linkRequest, StreamObserver<LinkResponse> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(LinkServiceGrpc.getGetLinksMethod(), getCallOptions()), linkRequest, streamObserver);
        }
    }

    private static final class MethodHandlers<Req, Resp> implements UnaryMethod<Req, Resp>, ServerStreamingMethod<Req, Resp>, ClientStreamingMethod<Req, Resp>, BidiStreamingMethod<Req, Resp> {
        private final int methodId;
        private final LinkServiceImplBase serviceImpl;

        MethodHandlers(LinkServiceImplBase linkServiceImplBase, int i) {
            this.serviceImpl = linkServiceImplBase;
            this.methodId = i;
        }

        public void invoke(Req req, StreamObserver<Resp> streamObserver) {
            if (this.methodId == 0) {
                this.serviceImpl.getLinks((LinkRequest) req, streamObserver);
                return;
            }
            throw new AssertionError();
        }

        public StreamObserver<Req> invoke(StreamObserver<Resp> streamObserver) {
            int i = this.methodId;
            throw new AssertionError();
        }
    }

    private LinkServiceGrpc() {
    }

    @RpcMethod(fullMethodName = "com.bitcoin.mwallet.LinkService/getLinks", methodType = MethodType.UNARY, requestType = LinkRequest.class, responseType = LinkResponse.class)
    public static MethodDescriptor<LinkRequest, LinkResponse> getGetLinksMethod() {
        MethodDescriptor<LinkRequest, LinkResponse> methodDescriptor = getGetLinksMethod;
        if (methodDescriptor == null) {
            synchronized (LinkServiceGrpc.class) {
                methodDescriptor = getGetLinksMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "getLinks")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoLiteUtils.marshaller(LinkRequest.getDefaultInstance())).setResponseMarshaller(ProtoLiteUtils.marshaller(LinkResponse.getDefaultInstance())).build();
                    getGetLinksMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static LinkServiceStub newStub(Channel channel) {
        return new LinkServiceStub(channel);
    }

    public static LinkServiceBlockingStub newBlockingStub(Channel channel) {
        return new LinkServiceBlockingStub(channel);
    }

    public static LinkServiceFutureStub newFutureStub(Channel channel) {
        return new LinkServiceFutureStub(channel);
    }

    public static ServiceDescriptor getServiceDescriptor() {
        ServiceDescriptor serviceDescriptor2 = serviceDescriptor;
        if (serviceDescriptor2 == null) {
            synchronized (LinkServiceGrpc.class) {
                serviceDescriptor2 = serviceDescriptor;
                if (serviceDescriptor2 == null) {
                    serviceDescriptor2 = ServiceDescriptor.newBuilder(SERVICE_NAME).addMethod(getGetLinksMethod()).build();
                    serviceDescriptor = serviceDescriptor2;
                }
            }
        }
        return serviceDescriptor2;
    }
}
