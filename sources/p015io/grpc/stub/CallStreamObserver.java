package p015io.grpc.stub;

import p015io.grpc.ExperimentalApi;

@ExperimentalApi("https://github.com/grpc/grpc-java/issues/1788")
/* renamed from: io.grpc.stub.CallStreamObserver */
public abstract class CallStreamObserver<V> implements StreamObserver<V> {
    public abstract void disableAutoInboundFlowControl();

    public abstract boolean isReady();

    public abstract void request(int i);

    public abstract void setMessageCompression(boolean z);

    public abstract void setOnReadyHandler(Runnable runnable);
}
