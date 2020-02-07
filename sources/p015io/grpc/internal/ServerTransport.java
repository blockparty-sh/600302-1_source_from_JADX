package p015io.grpc.internal;

import java.util.concurrent.ScheduledExecutorService;
import p015io.grpc.InternalChannelz.SocketStats;
import p015io.grpc.InternalInstrumented;
import p015io.grpc.Status;

/* renamed from: io.grpc.internal.ServerTransport */
public interface ServerTransport extends InternalInstrumented<SocketStats> {
    ScheduledExecutorService getScheduledExecutorService();

    void shutdown();

    void shutdownNow(Status status);
}
