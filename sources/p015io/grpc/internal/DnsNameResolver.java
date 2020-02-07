package p015io.grpc.internal;

import com.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.base.Throwables;
import com.google.common.base.Verify;
import com.google.common.base.VerifyException;
import com.microsoft.appcenter.Constants;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.URI;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;
import p015io.grpc.Attributes;
import p015io.grpc.Attributes.Builder;
import p015io.grpc.EquivalentAddressGroup;
import p015io.grpc.NameResolver;
import p015io.grpc.NameResolver.Args;
import p015io.grpc.NameResolver.ConfigOrError;
import p015io.grpc.NameResolver.Listener2;
import p015io.grpc.NameResolver.ResolutionResult;
import p015io.grpc.ProxiedSocketAddress;
import p015io.grpc.ProxyDetector;
import p015io.grpc.Status;
import p015io.grpc.SynchronizationContext;
import p015io.grpc.internal.SharedResourceHolder.Resource;

/* renamed from: io.grpc.internal.DnsNameResolver */
final class DnsNameResolver extends NameResolver {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    @VisibleForTesting
    static final long DEFAULT_NETWORK_CACHE_TTL_SECONDS = 30;
    private static final String GRPCLB_NAME_PREFIX = "_grpclb._tcp.";
    private static final String JNDI_LOCALHOST_PROPERTY;
    private static final String JNDI_PROPERTY = System.getProperty("io.grpc.internal.DnsNameResolverProvider.enable_jndi", "true");
    private static final String JNDI_SRV_PROPERTY;
    private static final String JNDI_TXT_PROPERTY;
    @VisibleForTesting
    static final String NETWORKADDRESS_CACHE_TTL_PROPERTY = "networkaddress.cache.ttl";
    private static final String SERVICE_CONFIG_CHOICE_CLIENT_HOSTNAME_KEY = "clientHostname";
    private static final String SERVICE_CONFIG_CHOICE_CLIENT_LANGUAGE_KEY = "clientLanguage";
    private static final Set<String> SERVICE_CONFIG_CHOICE_KEYS = Collections.unmodifiableSet(new HashSet(Arrays.asList(new String[]{SERVICE_CONFIG_CHOICE_CLIENT_LANGUAGE_KEY, SERVICE_CONFIG_CHOICE_PERCENTAGE_KEY, SERVICE_CONFIG_CHOICE_CLIENT_HOSTNAME_KEY, SERVICE_CONFIG_CHOICE_SERVICE_CONFIG_KEY})));
    private static final String SERVICE_CONFIG_CHOICE_PERCENTAGE_KEY = "percentage";
    private static final String SERVICE_CONFIG_CHOICE_SERVICE_CONFIG_KEY = "serviceConfig";
    private static final String SERVICE_CONFIG_NAME_PREFIX = "_grpc_config.";
    static final String SERVICE_CONFIG_PREFIX = "grpc_config=";
    @VisibleForTesting
    static boolean enableJndi = Boolean.parseBoolean(JNDI_PROPERTY);
    @VisibleForTesting
    static boolean enableJndiLocalhost = Boolean.parseBoolean(JNDI_LOCALHOST_PROPERTY);
    @VisibleForTesting
    static boolean enableSrv = Boolean.parseBoolean(JNDI_SRV_PROPERTY);
    @VisibleForTesting
    static boolean enableTxt = Boolean.parseBoolean(JNDI_TXT_PROPERTY);
    private static String localHostname;
    /* access modifiers changed from: private */
    public static final Logger logger = Logger.getLogger(DnsNameResolver.class.getName());
    private static final ResourceResolverFactory resourceResolverFactory = getResourceResolverFactory(DnsNameResolver.class.getClassLoader());
    /* access modifiers changed from: private */
    public volatile AddressResolver addressResolver = JdkAddressResolver.INSTANCE;
    private final String authority;
    /* access modifiers changed from: private */
    public final long cacheTtlNanos;
    /* access modifiers changed from: private */
    public ResolutionResults cachedResolutionResults;
    private Executor executor;
    private final Resource<Executor> executorResource;
    /* access modifiers changed from: private */
    public final String host;
    private Listener2 listener;
    /* access modifiers changed from: private */
    public final int port;
    @VisibleForTesting
    final ProxyDetector proxyDetector;
    /* access modifiers changed from: private */
    public final Random random = new Random();
    /* access modifiers changed from: private */
    public boolean resolving;
    private final AtomicReference<ResourceResolver> resourceResolver = new AtomicReference<>();
    private boolean shutdown;
    /* access modifiers changed from: private */
    public final Stopwatch stopwatch;
    /* access modifiers changed from: private */
    public final SynchronizationContext syncContext;

    /* renamed from: io.grpc.internal.DnsNameResolver$AddressResolver */
    interface AddressResolver {
        List<InetAddress> resolveAddress(String str) throws Exception;
    }

    /* renamed from: io.grpc.internal.DnsNameResolver$JdkAddressResolver */
    private enum JdkAddressResolver implements AddressResolver {
        INSTANCE;

        public List<InetAddress> resolveAddress(String str) throws UnknownHostException {
            return Collections.unmodifiableList(Arrays.asList(InetAddress.getAllByName(str)));
        }
    }

    @VisibleForTesting
    /* renamed from: io.grpc.internal.DnsNameResolver$ResolutionResults */
    static final class ResolutionResults {
        final List<? extends InetAddress> addresses;
        final List<EquivalentAddressGroup> balancerAddresses;
        final List<String> txtRecords;

        ResolutionResults(List<? extends InetAddress> list, List<String> list2, List<EquivalentAddressGroup> list3) {
            this.addresses = Collections.unmodifiableList((List) Preconditions.checkNotNull(list, "addresses"));
            this.txtRecords = Collections.unmodifiableList((List) Preconditions.checkNotNull(list2, "txtRecords"));
            this.balancerAddresses = Collections.unmodifiableList((List) Preconditions.checkNotNull(list3, "balancerAddresses"));
        }

        public String toString() {
            return MoreObjects.toStringHelper((Object) this).add("addresses", (Object) this.addresses).add("txtRecords", (Object) this.txtRecords).add("balancerAddresses", (Object) this.balancerAddresses).toString();
        }
    }

    /* renamed from: io.grpc.internal.DnsNameResolver$Resolve */
    private final class Resolve implements Runnable {
        private final Listener2 savedListener;

        Resolve(Listener2 listener2) {
            this.savedListener = (Listener2) Preconditions.checkNotNull(listener2, "savedListener");
        }

        public void run() {
            if (DnsNameResolver.logger.isLoggable(Level.FINER)) {
                Logger access$000 = DnsNameResolver.logger;
                StringBuilder sb = new StringBuilder();
                sb.append("Attempting DNS resolution of ");
                sb.append(DnsNameResolver.this.host);
                access$000.finer(sb.toString());
            }
            try {
                resolveInternal();
            } finally {
                DnsNameResolver.this.syncContext.execute(new Runnable() {
                    public void run() {
                        DnsNameResolver.this.resolving = false;
                    }
                });
            }
        }

        /* access modifiers changed from: 0000 */
        @VisibleForTesting
        public void resolveInternal() {
            String str = "Unable to resolve host ";
            try {
                ProxiedSocketAddress proxyFor = DnsNameResolver.this.proxyDetector.proxyFor(InetSocketAddress.createUnresolved(DnsNameResolver.this.host, DnsNameResolver.this.port));
                if (proxyFor != null) {
                    if (DnsNameResolver.logger.isLoggable(Level.FINER)) {
                        Logger access$000 = DnsNameResolver.logger;
                        StringBuilder sb = new StringBuilder();
                        sb.append("Using proxy address ");
                        sb.append(proxyFor);
                        access$000.finer(sb.toString());
                    }
                    this.savedListener.onResult(ResolutionResult.newBuilder().setAddresses(Collections.singletonList(new EquivalentAddressGroup((SocketAddress) proxyFor))).setAttributes(Attributes.EMPTY).build());
                    return;
                }
                ResourceResolver resourceResolver = null;
                try {
                    if (DnsNameResolver.shouldUseJndi(DnsNameResolver.enableJndi, DnsNameResolver.enableJndiLocalhost, DnsNameResolver.this.host)) {
                        resourceResolver = DnsNameResolver.this.getResourceResolver();
                    }
                    final ResolutionResults resolveAll = DnsNameResolver.resolveAll(DnsNameResolver.this.addressResolver, resourceResolver, DnsNameResolver.enableSrv, DnsNameResolver.enableTxt, DnsNameResolver.this.host);
                    DnsNameResolver.this.syncContext.execute(new Runnable() {
                        public void run() {
                            DnsNameResolver.this.cachedResolutionResults = resolveAll;
                            if (DnsNameResolver.this.cacheTtlNanos > 0) {
                                DnsNameResolver.this.stopwatch.reset().start();
                            }
                        }
                    });
                    if (DnsNameResolver.logger.isLoggable(Level.FINER)) {
                        Logger access$0002 = DnsNameResolver.logger;
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("Found DNS results ");
                        sb2.append(resolveAll);
                        sb2.append(" for ");
                        sb2.append(DnsNameResolver.this.host);
                        access$0002.finer(sb2.toString());
                    }
                    ArrayList arrayList = new ArrayList();
                    for (InetAddress inetSocketAddress : resolveAll.addresses) {
                        arrayList.add(new EquivalentAddressGroup((SocketAddress) new InetSocketAddress(inetSocketAddress, DnsNameResolver.this.port)));
                    }
                    arrayList.addAll(resolveAll.balancerAddresses);
                    if (arrayList.isEmpty()) {
                        Listener2 listener2 = this.savedListener;
                        Status status = Status.UNAVAILABLE;
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append("No DNS backend or balancer addresses found for ");
                        sb3.append(DnsNameResolver.this.host);
                        listener2.onError(status.withDescription(sb3.toString()));
                        return;
                    }
                    Builder newBuilder = Attributes.newBuilder();
                    if (!resolveAll.txtRecords.isEmpty()) {
                        ConfigOrError parseServiceConfig = DnsNameResolver.parseServiceConfig(resolveAll.txtRecords, DnsNameResolver.this.random, DnsNameResolver.getLocalHostname());
                        if (parseServiceConfig != null) {
                            if (parseServiceConfig.getError() != null) {
                                this.savedListener.onError(parseServiceConfig.getError());
                                return;
                            } else {
                                newBuilder.set(GrpcAttributes.NAME_RESOLVER_SERVICE_CONFIG, (Map) parseServiceConfig.getConfig());
                            }
                        }
                    } else {
                        DnsNameResolver.logger.log(Level.FINE, "No TXT records found for {0}", new Object[]{DnsNameResolver.this.host});
                    }
                    this.savedListener.onResult(ResolutionResult.newBuilder().setAddresses(arrayList).setAttributes(newBuilder.build()).build());
                } catch (Exception e) {
                    Listener2 listener22 = this.savedListener;
                    Status status2 = Status.UNAVAILABLE;
                    StringBuilder sb4 = new StringBuilder();
                    sb4.append(str);
                    sb4.append(DnsNameResolver.this.host);
                    listener22.onError(status2.withDescription(sb4.toString()).withCause(e));
                }
            } catch (IOException e2) {
                Listener2 listener23 = this.savedListener;
                Status status3 = Status.UNAVAILABLE;
                StringBuilder sb5 = new StringBuilder();
                sb5.append(str);
                sb5.append(DnsNameResolver.this.host);
                listener23.onError(status3.withDescription(sb5.toString()).withCause(e2));
            }
        }
    }

    /* renamed from: io.grpc.internal.DnsNameResolver$ResourceResolver */
    interface ResourceResolver {
        List<EquivalentAddressGroup> resolveSrv(AddressResolver addressResolver, String str) throws Exception;

        List<String> resolveTxt(String str) throws Exception;
    }

    /* renamed from: io.grpc.internal.DnsNameResolver$ResourceResolverFactory */
    interface ResourceResolverFactory {
        @Nullable
        ResourceResolver newResourceResolver();

        @Nullable
        Throwable unavailabilityCause();
    }

    static {
        String str = "false";
        JNDI_LOCALHOST_PROPERTY = System.getProperty("io.grpc.internal.DnsNameResolverProvider.enable_jndi_localhost", str);
        JNDI_SRV_PROPERTY = System.getProperty("io.grpc.internal.DnsNameResolverProvider.enable_grpclb", str);
        JNDI_TXT_PROPERTY = System.getProperty("io.grpc.internal.DnsNameResolverProvider.enable_service_config", str);
    }

    DnsNameResolver(@Nullable String str, String str2, Args args, Resource<Executor> resource, Stopwatch stopwatch2, boolean z) {
        Preconditions.checkNotNull(args, "args");
        this.executorResource = resource;
        StringBuilder sb = new StringBuilder();
        sb.append("//");
        sb.append((String) Preconditions.checkNotNull(str2, "name"));
        URI create = URI.create(sb.toString());
        Preconditions.checkArgument(create.getHost() != null, "Invalid DNS name: %s", (Object) str2);
        this.authority = (String) Preconditions.checkNotNull(create.getAuthority(), "nameUri (%s) doesn't have an authority", (Object) create);
        this.host = create.getHost();
        if (create.getPort() == -1) {
            this.port = args.getDefaultPort();
        } else {
            this.port = create.getPort();
        }
        this.proxyDetector = (ProxyDetector) Preconditions.checkNotNull(args.getProxyDetector(), "proxyDetector");
        this.cacheTtlNanos = getNetworkAddressCacheTtlNanos(z);
        this.stopwatch = (Stopwatch) Preconditions.checkNotNull(stopwatch2, "stopwatch");
        this.syncContext = (SynchronizationContext) Preconditions.checkNotNull(args.getSynchronizationContext(), "syncContext");
    }

    public String getServiceAuthority() {
        return this.authority;
    }

    public void start(Listener2 listener2) {
        Preconditions.checkState(this.listener == null, "already started");
        this.executor = (Executor) SharedResourceHolder.get(this.executorResource);
        this.listener = (Listener2) Preconditions.checkNotNull(listener2, CastExtraArgs.LISTENER);
        resolve();
    }

    public void refresh() {
        Preconditions.checkState(this.listener != null, "not started");
        resolve();
    }

    @Nullable
    static ConfigOrError parseServiceConfig(List<String> list, Random random2, String str) {
        try {
            Map map = null;
            for (Map maybeChooseServiceConfig : parseTxtResults(list)) {
                try {
                    map = maybeChooseServiceConfig(maybeChooseServiceConfig, random2, str);
                    if (map != null) {
                        break;
                    }
                } catch (RuntimeException e) {
                    return ConfigOrError.fromError(Status.UNKNOWN.withDescription("failed to pick service config choice").withCause(e));
                }
            }
            if (map == null) {
                return null;
            }
            return ConfigOrError.fromConfig(map);
        } catch (IOException | RuntimeException e2) {
            return ConfigOrError.fromError(Status.UNKNOWN.withDescription("failed to parse TXT records").withCause(e2));
        }
    }

    private void resolve() {
        if (!this.resolving && !this.shutdown && cacheRefreshRequired()) {
            this.resolving = true;
            this.executor.execute(new Resolve(this.listener));
        }
    }

    private boolean cacheRefreshRequired() {
        if (this.cachedResolutionResults != null) {
            long j = this.cacheTtlNanos;
            if (j != 0 && (j <= 0 || this.stopwatch.elapsed(TimeUnit.NANOSECONDS) <= this.cacheTtlNanos)) {
                return false;
            }
        }
        return true;
    }

    public void shutdown() {
        if (!this.shutdown) {
            this.shutdown = true;
            Executor executor2 = this.executor;
            if (executor2 != null) {
                this.executor = (Executor) SharedResourceHolder.release(this.executorResource, executor2);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public final int getPort() {
        return this.port;
    }

    @VisibleForTesting
    static ResolutionResults resolveAll(AddressResolver addressResolver2, @Nullable ResourceResolver resourceResolver2, boolean z, boolean z2, String str) {
        Throwable e;
        List emptyList = Collections.emptyList();
        List emptyList2 = Collections.emptyList();
        List emptyList3 = Collections.emptyList();
        Throwable th = null;
        try {
            emptyList = addressResolver2.resolveAddress(str);
            e = null;
        } catch (Exception e2) {
            e = e2;
        }
        if (resourceResolver2 != null) {
            if (z) {
                try {
                    StringBuilder sb = new StringBuilder();
                    sb.append(GRPCLB_NAME_PREFIX);
                    sb.append(str);
                    emptyList2 = resourceResolver2.resolveSrv(addressResolver2, sb.toString());
                } catch (Exception e3) {
                    e = e3;
                }
            }
            e = null;
            if (z2) {
                boolean z3 = false;
                boolean z4 = !z || e != null;
                if (e != null && z4) {
                    z3 = true;
                }
                if (!z3) {
                    try {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append(SERVICE_CONFIG_NAME_PREFIX);
                        sb2.append(str);
                        emptyList3 = resourceResolver2.resolveTxt(sb2.toString());
                    } catch (Exception e4) {
                        th = e4;
                    }
                }
            }
        } else {
            e = null;
        }
        String str2 = "ServiceConfig resolution failure";
        String str3 = "Balancer resolution failure";
        String str4 = "Address resolution failure";
        if (e != null) {
            if (e == null) {
                try {
                    if (!emptyList2.isEmpty()) {
                    }
                } catch (Throwable th2) {
                    if (e != null) {
                        logger.log(Level.FINE, str4, e);
                    }
                    if (e != null) {
                        logger.log(Level.FINE, str3, e);
                    }
                    if (th != null) {
                        logger.log(Level.FINE, str2, th);
                    }
                    throw th2;
                }
            }
            Throwables.throwIfUnchecked(e);
            throw new RuntimeException(e);
        }
        if (e != null) {
            logger.log(Level.FINE, str4, e);
        }
        if (e != null) {
            logger.log(Level.FINE, str3, e);
        }
        if (th != null) {
            logger.log(Level.FINE, str2, th);
        }
        return new ResolutionResults(emptyList, emptyList3, emptyList2);
    }

    @VisibleForTesting
    static List<Map<String, ?>> parseTxtResults(List<String> list) throws IOException {
        ArrayList arrayList = new ArrayList();
        for (String str : list) {
            if (!str.startsWith(SERVICE_CONFIG_PREFIX)) {
                logger.log(Level.FINE, "Ignoring non service config {0}", new Object[]{str});
            } else {
                Object parse = JsonParser.parse(str.substring(12));
                if (parse instanceof List) {
                    arrayList.addAll(ServiceConfigUtil.checkObjectList((List) parse));
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append("wrong type ");
                    sb.append(parse);
                    throw new ClassCastException(sb.toString());
                }
            }
        }
        return arrayList;
    }

    @Nullable
    private static final Double getPercentageFromChoice(Map<String, ?> map) {
        String str = SERVICE_CONFIG_CHOICE_PERCENTAGE_KEY;
        if (!map.containsKey(str)) {
            return null;
        }
        return ServiceConfigUtil.getDouble(map, str);
    }

    @Nullable
    private static final List<String> getClientLanguagesFromChoice(Map<String, ?> map) {
        String str = SERVICE_CONFIG_CHOICE_CLIENT_LANGUAGE_KEY;
        if (!map.containsKey(str)) {
            return null;
        }
        return ServiceConfigUtil.checkStringList(ServiceConfigUtil.getList(map, str));
    }

    @Nullable
    private static final List<String> getHostnamesFromChoice(Map<String, ?> map) {
        String str = SERVICE_CONFIG_CHOICE_CLIENT_HOSTNAME_KEY;
        if (!map.containsKey(str)) {
            return null;
        }
        return ServiceConfigUtil.checkStringList(ServiceConfigUtil.getList(map, str));
    }

    private static long getNetworkAddressCacheTtlNanos(boolean z) {
        if (z) {
            return 0;
        }
        String str = NETWORKADDRESS_CACHE_TTL_PROPERTY;
        String property = System.getProperty(str);
        long j = DEFAULT_NETWORK_CACHE_TTL_SECONDS;
        if (property != null) {
            try {
                j = Long.parseLong(property);
            } catch (NumberFormatException unused) {
                logger.log(Level.WARNING, "Property({0}) valid is not valid number format({1}), fall back to default({2})", new Object[]{str, property, Long.valueOf(DEFAULT_NETWORK_CACHE_TTL_SECONDS)});
            }
        }
        if (j > 0) {
            j = TimeUnit.SECONDS.toNanos(j);
        }
        return j;
    }

    @Nullable
    @VisibleForTesting
    static Map<String, ?> maybeChooseServiceConfig(Map<String, ?> map, Random random2, String str) {
        boolean z;
        boolean z2;
        for (Entry entry : map.entrySet()) {
            Verify.verify(SERVICE_CONFIG_CHOICE_KEYS.contains(entry.getKey()), "Bad key: %s", (Object) entry);
        }
        List clientLanguagesFromChoice = getClientLanguagesFromChoice(map);
        if (clientLanguagesFromChoice != null && !clientLanguagesFromChoice.isEmpty()) {
            Iterator it = clientLanguagesFromChoice.iterator();
            while (true) {
                if (!it.hasNext()) {
                    z2 = false;
                    break;
                }
                if ("java".equalsIgnoreCase((String) it.next())) {
                    z2 = true;
                    break;
                }
            }
            if (!z2) {
                return null;
            }
        }
        Double percentageFromChoice = getPercentageFromChoice(map);
        if (percentageFromChoice != null) {
            int intValue = percentageFromChoice.intValue();
            Verify.verify(intValue >= 0 && intValue <= 100, "Bad percentage: %s", (Object) percentageFromChoice);
            if (random2.nextInt(100) >= intValue) {
                return null;
            }
        }
        List hostnamesFromChoice = getHostnamesFromChoice(map);
        if (hostnamesFromChoice != null && !hostnamesFromChoice.isEmpty()) {
            Iterator it2 = hostnamesFromChoice.iterator();
            while (true) {
                if (it2.hasNext()) {
                    if (((String) it2.next()).equals(str)) {
                        z = true;
                        break;
                    }
                } else {
                    z = false;
                    break;
                }
            }
            if (!z) {
                return null;
            }
        }
        String str2 = SERVICE_CONFIG_CHOICE_SERVICE_CONFIG_KEY;
        Map<String, ?> object = ServiceConfigUtil.getObject(map, str2);
        if (object != null) {
            return object;
        }
        throw new VerifyException(String.format("key '%s' missing in '%s'", new Object[]{map, str2}));
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public void setAddressResolver(AddressResolver addressResolver2) {
        this.addressResolver = addressResolver2;
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public void setResourceResolver(ResourceResolver resourceResolver2) {
        this.resourceResolver.set(resourceResolver2);
    }

    /* access modifiers changed from: private */
    @Nullable
    public ResourceResolver getResourceResolver() {
        ResourceResolver resourceResolver2 = (ResourceResolver) this.resourceResolver.get();
        if (resourceResolver2 != null) {
            return resourceResolver2;
        }
        ResourceResolverFactory resourceResolverFactory2 = resourceResolverFactory;
        return resourceResolverFactory2 != null ? resourceResolverFactory2.newResourceResolver() : resourceResolver2;
    }

    @Nullable
    @VisibleForTesting
    static ResourceResolverFactory getResourceResolverFactory(ClassLoader classLoader) {
        try {
            try {
                try {
                    ResourceResolverFactory resourceResolverFactory2 = (ResourceResolverFactory) Class.forName("io.grpc.internal.JndiResourceResolverFactory", true, classLoader).asSubclass(ResourceResolverFactory.class).getConstructor(new Class[0]).newInstance(new Object[0]);
                    if (resourceResolverFactory2.unavailabilityCause() == null) {
                        return resourceResolverFactory2;
                    }
                    logger.log(Level.FINE, "JndiResourceResolverFactory not available, skipping.", resourceResolverFactory2.unavailabilityCause());
                    return null;
                } catch (Exception e) {
                    logger.log(Level.FINE, "Can't construct JndiResourceResolverFactory, skipping.", e);
                    return null;
                }
            } catch (Exception e2) {
                logger.log(Level.FINE, "Can't find JndiResourceResolverFactory ctor, skipping.", e2);
                return null;
            }
        } catch (ClassNotFoundException e3) {
            logger.log(Level.FINE, "Unable to find JndiResourceResolverFactory, skipping.", e3);
            return null;
        }
    }

    /* access modifiers changed from: private */
    public static String getLocalHostname() {
        if (localHostname == null) {
            try {
                localHostname = InetAddress.getLocalHost().getHostName();
            } catch (UnknownHostException e) {
                throw new RuntimeException(e);
            }
        }
        return localHostname;
    }

    @VisibleForTesting
    static boolean shouldUseJndi(boolean z, boolean z2, String str) {
        if (!z) {
            return false;
        }
        if ("localhost".equalsIgnoreCase(str)) {
            return z2;
        }
        if (str.contains(Constants.COMMON_SCHEMA_PREFIX_SEPARATOR)) {
            return false;
        }
        boolean z3 = true;
        boolean z4 = true;
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt != '.') {
                z4 &= charAt >= '0' && charAt <= '9';
            }
        }
        if (z4) {
            z3 = false;
        }
        return z3;
    }
}
