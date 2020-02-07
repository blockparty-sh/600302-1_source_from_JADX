package p015io.grpc.stub;

import javax.annotation.Nullable;
import p015io.grpc.ExperimentalApi;

@ExperimentalApi("https://github.com/grpc/grpc-java/issues/1788")
/* renamed from: io.grpc.stub.ClientCallStreamObserver */
public abstract class ClientCallStreamObserver<V> extends CallStreamObserver<V> {
    public abstract void cancel(@Nullable String str, @Nullable Throwable th);
}
