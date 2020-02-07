package p015io.grpc.internal;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;
import p015io.grpc.Status;

@ThreadSafe
/* renamed from: io.grpc.internal.ManagedClientTransport */
public interface ManagedClientTransport extends ClientTransport {

    /* renamed from: io.grpc.internal.ManagedClientTransport$Listener */
    public interface Listener {
        void transportInUse(boolean z);

        void transportReady();

        void transportShutdown(Status status);

        void transportTerminated();
    }

    void shutdown(Status status);

    void shutdownNow(Status status);

    @CheckReturnValue
    @Nullable
    Runnable start(Listener listener);
}
