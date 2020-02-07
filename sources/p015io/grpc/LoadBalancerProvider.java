package p015io.grpc;

import com.google.common.base.MoreObjects;
import java.util.Map;
import p015io.grpc.LoadBalancer.Factory;
import p015io.grpc.NameResolver.ConfigOrError;

@ExperimentalApi("https://github.com/grpc/grpc-java/issues/1771")
/* renamed from: io.grpc.LoadBalancerProvider */
public abstract class LoadBalancerProvider extends Factory {
    private static final ConfigOrError UNKNOWN_CONFIG = ConfigOrError.fromConfig(new UnknownConfig());

    /* renamed from: io.grpc.LoadBalancerProvider$UnknownConfig */
    private static final class UnknownConfig {
        public String toString() {
            return "service config is unused";
        }

        UnknownConfig() {
        }
    }

    public final boolean equals(Object obj) {
        return this == obj;
    }

    public abstract String getPolicyName();

    public abstract int getPriority();

    public abstract boolean isAvailable();

    public ConfigOrError parseLoadBalancingPolicyConfig(Map<String, ?> map) {
        return UNKNOWN_CONFIG;
    }

    public final String toString() {
        return MoreObjects.toStringHelper((Object) this).add("policy", (Object) getPolicyName()).add("priority", getPriority()).add("available", isAvailable()).toString();
    }

    public final int hashCode() {
        return super.hashCode();
    }
}
