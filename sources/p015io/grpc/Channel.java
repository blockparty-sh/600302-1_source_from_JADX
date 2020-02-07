package p015io.grpc;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
/* renamed from: io.grpc.Channel */
public abstract class Channel {
    public abstract String authority();

    public abstract <RequestT, ResponseT> ClientCall<RequestT, ResponseT> newCall(MethodDescriptor<RequestT, ResponseT> methodDescriptor, CallOptions callOptions);
}
