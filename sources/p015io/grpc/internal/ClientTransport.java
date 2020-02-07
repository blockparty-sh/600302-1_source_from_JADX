package p015io.grpc.internal;

import java.util.concurrent.Executor;
import javax.annotation.concurrent.ThreadSafe;
import p015io.grpc.CallOptions;
import p015io.grpc.InternalChannelz.SocketStats;
import p015io.grpc.InternalInstrumented;
import p015io.grpc.Metadata;
import p015io.grpc.MethodDescriptor;

@ThreadSafe
/* renamed from: io.grpc.internal.ClientTransport */
public interface ClientTransport extends InternalInstrumented<SocketStats> {

    /* renamed from: io.grpc.internal.ClientTransport$PingCallback */
    public interface PingCallback {
        void onFailure(Throwable th);

        void onSuccess(long j);
    }

    ClientStream newStream(MethodDescriptor<?, ?> methodDescriptor, Metadata metadata, CallOptions callOptions);

    void ping(PingCallback pingCallback, Executor executor);
}
