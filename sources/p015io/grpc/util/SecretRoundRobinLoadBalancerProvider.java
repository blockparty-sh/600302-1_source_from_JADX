package p015io.grpc.util;

import java.util.Map;
import p015io.grpc.LoadBalancer;
import p015io.grpc.LoadBalancer.Helper;
import p015io.grpc.LoadBalancerProvider;
import p015io.grpc.NameResolver.ConfigOrError;

/* renamed from: io.grpc.util.SecretRoundRobinLoadBalancerProvider */
final class SecretRoundRobinLoadBalancerProvider {

    /* renamed from: io.grpc.util.SecretRoundRobinLoadBalancerProvider$Provider */
    public static final class Provider extends LoadBalancerProvider {
        private static final String NO_CONFIG = "no service config";

        public String getPolicyName() {
            return "round_robin";
        }

        public int getPriority() {
            return 5;
        }

        public boolean isAvailable() {
            return true;
        }

        public LoadBalancer newLoadBalancer(Helper helper) {
            return new RoundRobinLoadBalancer(helper);
        }

        public ConfigOrError parseLoadBalancingPolicyConfig(Map<String, ?> map) {
            return ConfigOrError.fromConfig(NO_CONFIG);
        }
    }

    private SecretRoundRobinLoadBalancerProvider() {
    }
}
