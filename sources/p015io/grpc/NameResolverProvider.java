package p015io.grpc;

import java.util.List;
import p015io.grpc.Attributes.C2669Key;
import p015io.grpc.NameResolver.Factory;

@ExperimentalApi("https://github.com/grpc/grpc-java/issues/4159")
/* renamed from: io.grpc.NameResolverProvider */
public abstract class NameResolverProvider extends Factory {
    @Deprecated
    public static final C2669Key<Integer> PARAMS_DEFAULT_PORT = Factory.PARAMS_DEFAULT_PORT;

    /* access modifiers changed from: protected */
    public abstract boolean isAvailable();

    /* access modifiers changed from: protected */
    public abstract int priority();

    @Deprecated
    public static List<NameResolverProvider> providers() {
        return NameResolverRegistry.getDefaultRegistry().providers();
    }

    @Deprecated
    public static Factory asFactory() {
        return NameResolverRegistry.getDefaultRegistry().asFactory();
    }
}
