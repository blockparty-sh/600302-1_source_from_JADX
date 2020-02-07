package org.bitcoinj.params;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSet.Builder;
import com.google.common.collect.Lists;
import java.util.Collection;
import java.util.Set;
import org.bitcoinj.core.NetworkParameters;

public class Networks {
    private static Set<? extends NetworkParameters> networks = ImmutableSet.m170of(TestNet3Params.get(), MainNetParams.get());

    public static Set<? extends NetworkParameters> get() {
        return networks;
    }

    public static void register(NetworkParameters networkParameters) {
        register((Collection<? extends NetworkParameters>) Lists.newArrayList((E[]) new NetworkParameters[]{networkParameters}));
    }

    public static void register(Collection<? extends NetworkParameters> collection) {
        Builder builder = ImmutableSet.builder();
        builder.addAll((Iterable) networks);
        builder.addAll((Iterable) collection);
        networks = builder.build();
    }

    public static void unregister(NetworkParameters networkParameters) {
        if (networks.contains(networkParameters)) {
            Builder builder = ImmutableSet.builder();
            for (NetworkParameters networkParameters2 : networks) {
                if (!networkParameters2.equals(networkParameters)) {
                    builder.add((Object) networkParameters2);
                }
            }
            networks = builder.build();
        }
    }
}
