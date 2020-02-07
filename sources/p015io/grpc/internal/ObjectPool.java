package p015io.grpc.internal;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
/* renamed from: io.grpc.internal.ObjectPool */
public interface ObjectPool<T> {
    T getObject();

    T returnObject(Object obj);
}
