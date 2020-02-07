package p015io.grpc.internal;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import p015io.grpc.NameResolver;
import p015io.grpc.NameResolver.Listener;
import p015io.grpc.NameResolver.Listener2;

/* renamed from: io.grpc.internal.ForwardingNameResolver */
abstract class ForwardingNameResolver extends NameResolver {
    private final NameResolver delegate;

    ForwardingNameResolver(NameResolver nameResolver) {
        Preconditions.checkNotNull(nameResolver, "delegate can not be null");
        this.delegate = nameResolver;
    }

    public String getServiceAuthority() {
        return this.delegate.getServiceAuthority();
    }

    @Deprecated
    public void start(Listener listener) {
        this.delegate.start(listener);
    }

    public void start(Listener2 listener2) {
        this.delegate.start(listener2);
    }

    public void shutdown() {
        this.delegate.shutdown();
    }

    public void refresh() {
        this.delegate.refresh();
    }

    public String toString() {
        return MoreObjects.toStringHelper((Object) this).add("delegate", (Object) this.delegate).toString();
    }
}
