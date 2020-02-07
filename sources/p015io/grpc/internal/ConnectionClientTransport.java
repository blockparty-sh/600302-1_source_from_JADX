package p015io.grpc.internal;

import javax.annotation.concurrent.ThreadSafe;
import p015io.grpc.Attributes;

@ThreadSafe
/* renamed from: io.grpc.internal.ConnectionClientTransport */
public interface ConnectionClientTransport extends ManagedClientTransport {
    Attributes getAttributes();
}
