package p015io.grpc;

import com.google.common.util.concurrent.ListenableFuture;

@Internal
/* renamed from: io.grpc.InternalInstrumented */
public interface InternalInstrumented<T> extends InternalWithLogId {
    ListenableFuture<T> getStats();
}
