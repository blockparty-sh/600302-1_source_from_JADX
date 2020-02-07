package p015io.grpc.stub;

import p015io.grpc.ExperimentalApi;

@ExperimentalApi("https://github.com/grpc/grpc-java/issues/1788")
/* renamed from: io.grpc.stub.ServerCallStreamObserver */
public abstract class ServerCallStreamObserver<V> extends CallStreamObserver<V> {
    public abstract boolean isCancelled();

    public abstract void setCompression(String str);

    public abstract void setOnCancelHandler(Runnable runnable);
}
