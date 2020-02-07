package p015io.grpc;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;
import p015io.grpc.ServiceProviders.PriorityAccessor;

@ExperimentalApi("https://github.com/grpc/grpc-java/issues/1771")
@ThreadSafe
/* renamed from: io.grpc.LoadBalancerRegistry */
public final class LoadBalancerRegistry {
    private static final Iterable<Class<?>> HARDCODED_CLASSES = getHardCodedClasses();
    private static LoadBalancerRegistry instance;
    private static final Logger logger = Logger.getLogger(LoadBalancerRegistry.class.getName());
    private final LinkedHashSet<LoadBalancerProvider> allProviders = new LinkedHashSet<>();
    private final LinkedHashMap<String, LoadBalancerProvider> effectiveProviders = new LinkedHashMap<>();

    /* renamed from: io.grpc.LoadBalancerRegistry$LoadBalancerPriorityAccessor */
    private static final class LoadBalancerPriorityAccessor implements PriorityAccessor<LoadBalancerProvider> {
        LoadBalancerPriorityAccessor() {
        }

        public boolean isAvailable(LoadBalancerProvider loadBalancerProvider) {
            return loadBalancerProvider.isAvailable();
        }

        public int getPriority(LoadBalancerProvider loadBalancerProvider) {
            return loadBalancerProvider.getPriority();
        }
    }

    public synchronized void register(LoadBalancerProvider loadBalancerProvider) {
        addProvider(loadBalancerProvider);
        refreshProviderMap();
    }

    private synchronized void addProvider(LoadBalancerProvider loadBalancerProvider) {
        Preconditions.checkArgument(loadBalancerProvider.isAvailable(), "isAvailable() returned false");
        this.allProviders.add(loadBalancerProvider);
    }

    public synchronized void deregister(LoadBalancerProvider loadBalancerProvider) {
        this.allProviders.remove(loadBalancerProvider);
        refreshProviderMap();
    }

    private synchronized void refreshProviderMap() {
        this.effectiveProviders.clear();
        Iterator it = this.allProviders.iterator();
        while (it.hasNext()) {
            LoadBalancerProvider loadBalancerProvider = (LoadBalancerProvider) it.next();
            String policyName = loadBalancerProvider.getPolicyName();
            LoadBalancerProvider loadBalancerProvider2 = (LoadBalancerProvider) this.effectiveProviders.get(policyName);
            if (loadBalancerProvider2 == null || loadBalancerProvider2.getPriority() < loadBalancerProvider.getPriority()) {
                this.effectiveProviders.put(policyName, loadBalancerProvider);
            }
        }
    }

    public static synchronized LoadBalancerRegistry getDefaultRegistry() {
        LoadBalancerRegistry loadBalancerRegistry;
        synchronized (LoadBalancerRegistry.class) {
            if (instance == null) {
                List<LoadBalancerProvider> loadAll = ServiceProviders.loadAll(LoadBalancerProvider.class, HARDCODED_CLASSES, LoadBalancerProvider.class.getClassLoader(), new LoadBalancerPriorityAccessor());
                instance = new LoadBalancerRegistry();
                for (LoadBalancerProvider loadBalancerProvider : loadAll) {
                    Logger logger2 = logger;
                    StringBuilder sb = new StringBuilder();
                    sb.append("Service loader found ");
                    sb.append(loadBalancerProvider);
                    logger2.fine(sb.toString());
                    if (loadBalancerProvider.isAvailable()) {
                        instance.addProvider(loadBalancerProvider);
                    }
                }
                instance.refreshProviderMap();
            }
            loadBalancerRegistry = instance;
        }
        return loadBalancerRegistry;
    }

    @Nullable
    public synchronized LoadBalancerProvider getProvider(String str) {
        return (LoadBalancerProvider) this.effectiveProviders.get(Preconditions.checkNotNull(str, "policy"));
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public synchronized Map<String, LoadBalancerProvider> providers() {
        return new LinkedHashMap(this.effectiveProviders);
    }

    @VisibleForTesting
    static List<Class<?>> getHardCodedClasses() {
        ArrayList arrayList = new ArrayList();
        try {
            arrayList.add(Class.forName("io.grpc.internal.PickFirstLoadBalancerProvider"));
        } catch (ClassNotFoundException e) {
            logger.log(Level.WARNING, "Unable to find pick-first LoadBalancer", e);
        }
        try {
            arrayList.add(Class.forName("io.grpc.util.SecretRoundRobinLoadBalancerProvider$Provider"));
        } catch (ClassNotFoundException e2) {
            logger.log(Level.FINE, "Unable to find round-robin LoadBalancer", e2);
        }
        return Collections.unmodifiableList(arrayList);
    }
}
