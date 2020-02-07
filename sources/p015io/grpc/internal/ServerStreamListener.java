package p015io.grpc.internal;

import p015io.grpc.Status;

/* renamed from: io.grpc.internal.ServerStreamListener */
public interface ServerStreamListener extends StreamListener {
    void closed(Status status);

    void halfClosed();
}
