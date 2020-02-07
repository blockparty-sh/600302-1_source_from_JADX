package p015io.grpc;

import androidx.core.p003os.EnvironmentCompat;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;
import p015io.grpc.NameResolver.Args;
import p015io.grpc.NameResolver.Factory;
import p015io.grpc.ServiceProviders.PriorityAccessor;

@ExperimentalApi("https://github.com/grpc/grpc-java/issues/4159")
@ThreadSafe
/* renamed from: io.grpc.NameResolverRegistry */
public final class NameResolverRegistry {
    private static NameResolverRegistry instance;
    private static final Logger logger = Logger.getLogger(NameResolverRegistry.class.getName());
    @GuardedBy("this")
    private final LinkedHashSet<NameResolverProvider> allProviders = new LinkedHashSet<>();
    @GuardedBy("this")
    private List<NameResolverProvider> effectiveProviders = Collections.emptyList();
    private final Factory factory = new NameResolverFactory();

    /* renamed from: io.grpc.NameResolverRegistry$NameResolverFactory */
    private final class NameResolverFactory extends Factory {
        private NameResolverFactory() {
        }

        @Nullable
        public NameResolver newNameResolver(URI uri, Args args) {
            for (NameResolverProvider newNameResolver : NameResolverRegistry.this.providers()) {
                NameResolver newNameResolver2 = newNameResolver.newNameResolver(uri, args);
                if (newNameResolver2 != null) {
                    return newNameResolver2;
                }
            }
            return null;
        }

        public String getDefaultScheme() {
            List providers = NameResolverRegistry.this.providers();
            if (providers.isEmpty()) {
                return EnvironmentCompat.MEDIA_UNKNOWN;
            }
            return ((NameResolverProvider) providers.get(0)).getDefaultScheme();
        }
    }

    /* renamed from: io.grpc.NameResolverRegistry$NameResolverPriorityAccessor */
    private static final class NameResolverPriorityAccessor implements PriorityAccessor<NameResolverProvider> {
        private NameResolverPriorityAccessor() {
        }

        public boolean isAvailable(NameResolverProvider nameResolverProvider) {
            return nameResolverProvider.isAvailable();
        }

        public int getPriority(NameResolverProvider nameResolverProvider) {
            return nameResolverProvider.priority();
        }
    }

    public synchronized void register(NameResolverProvider nameResolverProvider) {
        addProvider(nameResolverProvider);
        refreshProviders();
    }

    private synchronized void addProvider(NameResolverProvider nameResolverProvider) {
        Preconditions.checkArgument(nameResolverProvider.isAvailable(), "isAvailable() returned false");
        this.allProviders.add(nameResolverProvider);
    }

    public synchronized void deregister(NameResolverProvider nameResolverProvider) {
        this.allProviders.remove(nameResolverProvider);
        refreshProviders();
    }

    private synchronized void refreshProviders() {
        ArrayList arrayList = new ArrayList(this.allProviders);
        Collections.sort(arrayList, Collections.reverseOrder(new Comparator<NameResolverProvider>() {
            public int compare(NameResolverProvider nameResolverProvider, NameResolverProvider nameResolverProvider2) {
                return nameResolverProvider.priority() - nameResolverProvider2.priority();
            }
        }));
        this.effectiveProviders = Collections.unmodifiableList(arrayList);
    }

    public static synchronized NameResolverRegistry getDefaultRegistry() {
        NameResolverRegistry nameResolverRegistry;
        synchronized (NameResolverRegistry.class) {
            if (instance == null) {
                List<NameResolverProvider> loadAll = ServiceProviders.loadAll(NameResolverProvider.class, getHardCodedClasses(), NameResolverProvider.class.getClassLoader(), new NameResolverPriorityAccessor());
                if (loadAll.isEmpty()) {
                    logger.warning("No NameResolverProviders found via ServiceLoader, including for DNS. This is probably due to a broken build. If using ProGuard, check your configuration");
                }
                instance = new NameResolverRegistry();
                for (NameResolverProvider nameResolverProvider : loadAll) {
                    Logger logger2 = logger;
                    StringBuilder sb = new StringBuilder();
                    sb.append("Service loader found ");
                    sb.append(nameResolverProvider);
                    logger2.fine(sb.toString());
                    if (nameResolverProvider.isAvailable()) {
                        instance.addProvider(nameResolverProvider);
                    }
                }
                instance.refreshProviders();
            }
            nameResolverRegistry = instance;
        }
        return nameResolverRegistry;
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public synchronized List<NameResolverProvider> providers() {
        return this.effectiveProviders;
    }

    public Factory asFactory() {
        return this.factory;
    }

    @VisibleForTesting
    static List<Class<?>> getHardCodedClasses() {
        ArrayList arrayList = new ArrayList();
        try {
            arrayList.add(Class.forName("io.grpc.internal.DnsNameResolverProvider"));
        } catch (ClassNotFoundException e) {
            logger.log(Level.FINE, "Unable to find DNS NameResolver", e);
        }
        return Collections.unmodifiableList(arrayList);
    }
}
