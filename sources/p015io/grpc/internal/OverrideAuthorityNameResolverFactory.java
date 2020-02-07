package p015io.grpc.internal;

import java.net.URI;
import javax.annotation.Nullable;
import p015io.grpc.NameResolver;
import p015io.grpc.NameResolver.Args;
import p015io.grpc.NameResolver.Factory;

/* renamed from: io.grpc.internal.OverrideAuthorityNameResolverFactory */
final class OverrideAuthorityNameResolverFactory extends Factory {
    /* access modifiers changed from: private */
    public final String authorityOverride;
    private final Factory delegate;

    OverrideAuthorityNameResolverFactory(Factory factory, String str) {
        this.delegate = factory;
        this.authorityOverride = str;
    }

    @Nullable
    public NameResolver newNameResolver(URI uri, Args args) {
        NameResolver newNameResolver = this.delegate.newNameResolver(uri, args);
        if (newNameResolver == null) {
            return null;
        }
        return new ForwardingNameResolver(newNameResolver) {
            public String getServiceAuthority() {
                return OverrideAuthorityNameResolverFactory.this.authorityOverride;
            }
        };
    }

    public String getDefaultScheme() {
        return this.delegate.getDefaultScheme();
    }
}
