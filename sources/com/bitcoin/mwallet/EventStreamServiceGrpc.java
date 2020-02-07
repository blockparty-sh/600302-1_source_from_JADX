package com.bitcoin.mwallet;

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

public final class EventStreamServiceGrpc {
    private static final int METHODID_STREAM = 0;
    public static final String SERVICE_NAME = "com.bitcoin.mwallet.EventStreamService";
    private static volatile MethodDescriptor<StreamRequest, StreamEvent> getStreamMethod;
    private static volatile ServiceDescriptor serviceDescriptor;

    public static final class EventStreamServiceBlockingStub extends AbstractStub<EventStreamServiceBlockingStub> {
        private EventStreamServiceBlockingStub(Channel channel) {
            super(channel);
        }

        private EventStreamServiceBlockingStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        /* access modifiers changed from: protected */
        public EventStreamServiceBlockingStub build(Channel channel, CallOptions callOptions) {
            return new EventStreamServiceBlockingStub(channel, callOptions);
        }

        public Iterator<StreamEvent> stream(StreamRequest streamRequest) {
            return ClientCalls.blockingServerStreamingCall(getChannel(), EventStreamServiceGrpc.getStreamMethod(), getCallOptions(), streamRequest);
        }
    }

    public static final class EventStreamServiceFutureStub extends AbstractStub<EventStreamServiceFutureStub> {
        private EventStreamServiceFutureStub(Channel channel) {
            super(channel);
        }

        private EventStreamServiceFutureStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        /* access modifiers changed from: protected */
        public EventStreamServiceFutureStub build(Channel channel, CallOptions callOptions) {
            return new EventStreamServiceFutureStub(channel, callOptions);
        }
    }

    public static abstract class EventStreamServiceImplBase implements BindableService {
        public void stream(StreamRequest streamRequest, StreamObserver<StreamEvent> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(EventStreamServiceGrpc.getStreamMethod(), streamObserver);
        }

        public final ServerServiceDefinition bindService() {
            return ServerServiceDefinition.builder(EventStreamServiceGrpc.getServiceDescriptor()).addMethod(EventStreamServiceGrpc.getStreamMethod(), ServerCalls.asyncServerStreamingCall(new MethodHandlers(this, 0))).build();
        }
    }

    public static final class EventStreamServiceStub extends AbstractStub<EventStreamServiceStub> {
        private EventStreamServiceStub(Channel channel) {
            super(channel);
        }

        private EventStreamServiceStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        /* access modifiers changed from: protected */
        public EventStreamServiceStub build(Channel channel, CallOptions callOptions) {
            return new EventStreamServiceStub(channel, callOptions);
        }

        public void stream(StreamRequest streamRequest, StreamObserver<StreamEvent> streamObserver) {
            ClientCalls.asyncServerStreamingCall(getChannel().newCall(EventStreamServiceGrpc.getStreamMethod(), getCallOptions()), streamRequest, streamObserver);
        }
    }

    private static final class MethodHandlers<Req, Resp> implements UnaryMethod<Req, Resp>, ServerStreamingMethod<Req, Resp>, ClientStreamingMethod<Req, Resp>, BidiStreamingMethod<Req, Resp> {
        private final int methodId;
        private final EventStreamServiceImplBase serviceImpl;

        MethodHandlers(EventStreamServiceImplBase eventStreamServiceImplBase, int i) {
            this.serviceImpl = eventStreamServiceImplBase;
            this.methodId = i;
        }

        public void invoke(Req req, StreamObserver<Resp> streamObserver) {
            if (this.methodId == 0) {
                this.serviceImpl.stream((StreamRequest) req, streamObserver);
                return;
            }
            throw new AssertionError();
        }

        public StreamObserver<Req> invoke(StreamObserver<Resp> streamObserver) {
            int i = this.methodId;
            throw new AssertionError();
        }
    }

    private EventStreamServiceGrpc() {
    }

    @RpcMethod(fullMethodName = "com.bitcoin.mwallet.EventStreamService/stream", methodType = MethodType.SERVER_STREAMING, requestType = StreamRequest.class, responseType = StreamEvent.class)
    public static MethodDescriptor<StreamRequest, StreamEvent> getStreamMethod() {
        MethodDescriptor<StreamRequest, StreamEvent> methodDescriptor = getStreamMethod;
        if (methodDescriptor == null) {
            synchronized (EventStreamServiceGrpc.class) {
                methodDescriptor = getStreamMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodType.SERVER_STREAMING).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "stream")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoLiteUtils.marshaller(StreamRequest.getDefaultInstance())).setResponseMarshaller(ProtoLiteUtils.marshaller(StreamEvent.getDefaultInstance())).build();
                    getStreamMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static EventStreamServiceStub newStub(Channel channel) {
        return new EventStreamServiceStub(channel);
    }

    public static EventStreamServiceBlockingStub newBlockingStub(Channel channel) {
        return new EventStreamServiceBlockingStub(channel);
    }

    public static EventStreamServiceFutureStub newFutureStub(Channel channel) {
        return new EventStreamServiceFutureStub(channel);
    }

    public static ServiceDescriptor getServiceDescriptor() {
        ServiceDescriptor serviceDescriptor2 = serviceDescriptor;
        if (serviceDescriptor2 == null) {
            synchronized (EventStreamServiceGrpc.class) {
                serviceDescriptor2 = serviceDescriptor;
                if (serviceDescriptor2 == null) {
                    serviceDescriptor2 = ServiceDescriptor.newBuilder(SERVICE_NAME).addMethod(getStreamMethod()).build();
                    serviceDescriptor = serviceDescriptor2;
                }
            }
        }
        return serviceDescriptor2;
    }
}
