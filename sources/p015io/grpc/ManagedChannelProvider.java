package p015io.grpc;

import com.google.common.annotations.VisibleForTesting;
import java.util.ArrayList;
import java.util.Iterator;
import p015io.grpc.ServiceProviders.PriorityAccessor;

@Internal
/* renamed from: io.grpc.ManagedChannelProvider */
public abstract class ManagedChannelProvider {
    @VisibleForTesting
    static final Iterable<Class<?>> HARDCODED_CLASSES = new HardcodedClasses();
    private static final ManagedChannelProvider provider = ((ManagedChannelProvider) ServiceProviders.load(ManagedChannelProvider.class, HARDCODED_CLASSES, ManagedChannelProvider.class.getClassLoader(), new PriorityAccessor<ManagedChannelProvider>() {
        public boolean isAvailable(ManagedChannelProvider managedChannelProvider) {
            return managedChannelProvider.isAvailable();
        }

        public int getPriority(ManagedChannelProvider managedChannelProvider) {
            return managedChannelProvider.priority();
        }
    }));

    /* renamed from: io.grpc.ManagedChannelProvider$HardcodedClasses */
    private static final class HardcodedClasses implements Iterable<Class<?>> {
        private HardcodedClasses() {
        }

        public Iterator<Class<?>> iterator() {
            ArrayList arrayList = new ArrayList();
            try {
                arrayList.add(Class.forName("io.grpc.okhttp.OkHttpChannelProvider"));
            } catch (ClassNotFoundException unused) {
            }
            try {
                arrayList.add(Class.forName("io.grpc.netty.NettyChannelProvider"));
            } catch (ClassNotFoundException unused2) {
            }
            return arrayList.iterator();
        }
    }

    /* renamed from: io.grpc.ManagedChannelProvider$ProviderNotFoundException */
    public static final class ProviderNotFoundException extends RuntimeException {
        private static final long serialVersionUID = 1;

        public ProviderNotFoundException(String str) {
            super(str);
        }
    }

    /* access modifiers changed from: protected */
    public abstract ManagedChannelBuilder<?> builderForAddress(String str, int i);

    /* access modifiers changed from: protected */
    public abstract ManagedChannelBuilder<?> builderForTarget(String str);

    /* access modifiers changed from: protected */
    public abstract boolean isAvailable();

    /* access modifiers changed from: protected */
    public abstract int priority();

    public static ManagedChannelProvider provider() {
        ManagedChannelProvider managedChannelProvider = provider;
        if (managedChannelProvider != null) {
            return managedChannelProvider;
        }
        throw new ProviderNotFoundException("No functional channel service provider found. Try adding a dependency on the grpc-okhttp, grpc-netty, or grpc-netty-shaded artifact");
    }
}
