package p015io.grpc.perfmark;

import java.io.Closeable;

/* renamed from: io.grpc.perfmark.PerfMarkTask */
public abstract class PerfMarkTask implements Closeable {
    public abstract void close();

    PerfMarkTask() {
    }
}
