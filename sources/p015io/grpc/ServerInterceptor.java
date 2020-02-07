package p015io.grpc;

import javax.annotation.concurrent.ThreadSafe;
import p015io.grpc.ServerCall.Listener;

@ThreadSafe
/* renamed from: io.grpc.ServerInterceptor */
public interface ServerInterceptor {
    <ReqT, RespT> Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> serverCall, Metadata metadata, ServerCallHandler<ReqT, RespT> serverCallHandler);
}
