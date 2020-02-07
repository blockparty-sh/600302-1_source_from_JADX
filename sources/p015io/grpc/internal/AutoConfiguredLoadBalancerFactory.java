package p015io.grpc.internal;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import javax.annotation.Nullable;
import p015io.grpc.Attributes;
import p015io.grpc.ChannelLogger.ChannelLogLevel;
import p015io.grpc.ConnectivityState;
import p015io.grpc.ConnectivityStateInfo;
import p015io.grpc.EquivalentAddressGroup;
import p015io.grpc.LoadBalancer;
import p015io.grpc.LoadBalancer.Factory;
import p015io.grpc.LoadBalancer.Helper;
import p015io.grpc.LoadBalancer.PickResult;
import p015io.grpc.LoadBalancer.PickSubchannelArgs;
import p015io.grpc.LoadBalancer.ResolvedAddresses;
import p015io.grpc.LoadBalancer.Subchannel;
import p015io.grpc.LoadBalancer.SubchannelPicker;
import p015io.grpc.LoadBalancerProvider;
import p015io.grpc.LoadBalancerRegistry;
import p015io.grpc.NameResolver.ConfigOrError;
import p015io.grpc.Status;
import p015io.grpc.internal.ServiceConfigUtil.LbConfig;

/* renamed from: io.grpc.internal.AutoConfiguredLoadBalancerFactory */
public final class AutoConfiguredLoadBalancerFactory extends Factory {
    private static final String GRPCLB_POLICY_NAME = "grpclb";
    /* access modifiers changed from: private */
    public static final Logger logger = Logger.getLogger(AutoConfiguredLoadBalancerFactory.class.getName());
    /* access modifiers changed from: private */
    public final String defaultPolicy;
    /* access modifiers changed from: private */
    public final LoadBalancerRegistry registry;

    @VisibleForTesting
    /* renamed from: io.grpc.internal.AutoConfiguredLoadBalancerFactory$AutoConfiguredLoadBalancer */
    public final class AutoConfiguredLoadBalancer extends LoadBalancer {
        private LoadBalancer delegate;
        private LoadBalancerProvider delegateProvider;
        private final Helper helper;
        private boolean roundRobinDueToGrpclbDepMissing;

        public boolean canHandleEmptyAddressListFromNameResolution() {
            return true;
        }

        AutoConfiguredLoadBalancer(Helper helper2) {
            this.helper = helper2;
            this.delegateProvider = AutoConfiguredLoadBalancerFactory.this.registry.getProvider(AutoConfiguredLoadBalancerFactory.this.defaultPolicy);
            LoadBalancerProvider loadBalancerProvider = this.delegateProvider;
            if (loadBalancerProvider != null) {
                this.delegate = loadBalancerProvider.newLoadBalancer(helper2);
                return;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Could not find policy '");
            sb.append(AutoConfiguredLoadBalancerFactory.this.defaultPolicy);
            sb.append("'. Make sure its implementation is either registered to LoadBalancerRegistry or included in META-INF/services/io.grpc.LoadBalancerProvider from your jar files.");
            throw new IllegalStateException(sb.toString());
        }

        public void handleResolvedAddresses(ResolvedAddresses resolvedAddresses) {
            List addresses = resolvedAddresses.getAddresses();
            Attributes attributes = resolvedAddresses.getAttributes();
            if (attributes.get(ATTR_LOAD_BALANCING_CONFIG) == null) {
                try {
                    PolicySelection decideLoadBalancerProvider = decideLoadBalancerProvider(addresses, (Map) attributes.get(GrpcAttributes.NAME_RESOLVER_SERVICE_CONFIG));
                    if (this.delegateProvider == null || !decideLoadBalancerProvider.provider.getPolicyName().equals(this.delegateProvider.getPolicyName())) {
                        this.helper.updateBalancingState(ConnectivityState.CONNECTING, new EmptyPicker());
                        this.delegate.shutdown();
                        this.delegateProvider = decideLoadBalancerProvider.provider;
                        LoadBalancer loadBalancer = this.delegate;
                        this.delegate = this.delegateProvider.newLoadBalancer(this.helper);
                        this.helper.getChannelLogger().log(ChannelLogLevel.INFO, "Load balancer changed from {0} to {1}", loadBalancer.getClass().getSimpleName(), this.delegate.getClass().getSimpleName());
                    }
                    if (decideLoadBalancerProvider.config != null) {
                        this.helper.getChannelLogger().log(ChannelLogLevel.DEBUG, "Load-balancing config: {0}", decideLoadBalancerProvider.config);
                        attributes = attributes.toBuilder().set(ATTR_LOAD_BALANCING_CONFIG, decideLoadBalancerProvider.config).build();
                    }
                    LoadBalancer delegate2 = getDelegate();
                    if (!decideLoadBalancerProvider.serverList.isEmpty() || delegate2.canHandleEmptyAddressListFromNameResolution()) {
                        delegate2.handleResolvedAddresses(ResolvedAddresses.newBuilder().setAddresses(decideLoadBalancerProvider.serverList).setAttributes(attributes).build());
                    } else {
                        Status status = Status.UNAVAILABLE;
                        StringBuilder sb = new StringBuilder();
                        sb.append("Name resolver returned no usable address. addrs=");
                        sb.append(addresses);
                        sb.append(", attrs=");
                        sb.append(attributes);
                        delegate2.handleNameResolutionError(status.withDescription(sb.toString()));
                    }
                } catch (PolicyException e) {
                    this.helper.updateBalancingState(ConnectivityState.TRANSIENT_FAILURE, new FailingPicker(Status.INTERNAL.withDescription(e.getMessage())));
                    this.delegate.shutdown();
                    this.delegateProvider = null;
                    this.delegate = new NoopLoadBalancer();
                }
            } else {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Unexpected ATTR_LOAD_BALANCING_CONFIG from upstream: ");
                sb2.append(attributes.get(ATTR_LOAD_BALANCING_CONFIG));
                throw new IllegalArgumentException(sb2.toString());
            }
        }

        public void handleNameResolutionError(Status status) {
            getDelegate().handleNameResolutionError(status);
        }

        public void handleSubchannelState(Subchannel subchannel, ConnectivityStateInfo connectivityStateInfo) {
            getDelegate().handleSubchannelState(subchannel, connectivityStateInfo);
        }

        public void shutdown() {
            this.delegate.shutdown();
            this.delegate = null;
        }

        @VisibleForTesting
        public LoadBalancer getDelegate() {
            return this.delegate;
        }

        /* access modifiers changed from: 0000 */
        @VisibleForTesting
        public void setDelegate(LoadBalancer loadBalancer) {
            this.delegate = loadBalancer;
        }

        /* access modifiers changed from: 0000 */
        @VisibleForTesting
        public LoadBalancerProvider getDelegateProvider() {
            return this.delegateProvider;
        }

        /* access modifiers changed from: 0000 */
        @VisibleForTesting
        public PolicySelection decideLoadBalancerProvider(List<EquivalentAddressGroup> list, @Nullable Map<String, ?> map) throws PolicyException {
            List<EquivalentAddressGroup> arrayList = new ArrayList<>();
            boolean z = false;
            for (EquivalentAddressGroup equivalentAddressGroup : list) {
                if (equivalentAddressGroup.getAttributes().get(GrpcAttributes.ATTR_LB_ADDR_AUTHORITY) != null) {
                    z = true;
                } else {
                    arrayList.add(equivalentAddressGroup);
                }
            }
            List<LbConfig> unwrapLoadBalancingConfigList = map != null ? ServiceConfigUtil.unwrapLoadBalancingConfigList(ServiceConfigUtil.getLoadBalancingConfigsFromServiceConfig(map)) : null;
            String str = AutoConfiguredLoadBalancerFactory.GRPCLB_POLICY_NAME;
            if (unwrapLoadBalancingConfigList != null && !unwrapLoadBalancingConfigList.isEmpty()) {
                LinkedHashSet linkedHashSet = new LinkedHashSet();
                for (LbConfig lbConfig : unwrapLoadBalancingConfigList) {
                    String policyName = lbConfig.getPolicyName();
                    LoadBalancerProvider provider = AutoConfiguredLoadBalancerFactory.this.registry.getProvider(policyName);
                    if (provider == null) {
                        linkedHashSet.add(policyName);
                    } else {
                        if (!linkedHashSet.isEmpty()) {
                            this.helper.getChannelLogger().log(ChannelLogLevel.DEBUG, "{0} specified by Service Config are not available", linkedHashSet);
                        }
                        if (!policyName.equals(str)) {
                            list = arrayList;
                        }
                        return new PolicySelection(provider, list, lbConfig.getRawConfigValue());
                    }
                }
                if (!z) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("None of ");
                    sb.append(linkedHashSet);
                    sb.append(" specified by Service Config are available.");
                    throw new PolicyException(sb.toString());
                }
            }
            if (z) {
                LoadBalancerProvider provider2 = AutoConfiguredLoadBalancerFactory.this.registry.getProvider(str);
                if (provider2 != null) {
                    return new PolicySelection(provider2, list, null);
                }
                if (!arrayList.isEmpty()) {
                    if (!this.roundRobinDueToGrpclbDepMissing) {
                        this.roundRobinDueToGrpclbDepMissing = true;
                        String str2 = "Found balancer addresses but grpclb runtime is missing. Will use round_robin. Please include grpc-grpclb in your runtime depedencies.";
                        this.helper.getChannelLogger().log(ChannelLogLevel.ERROR, str2);
                        AutoConfiguredLoadBalancerFactory.logger.warning(str2);
                    }
                    return new PolicySelection(AutoConfiguredLoadBalancerFactory.this.getProviderOrThrow("round_robin", "received balancer addresses but grpclb runtime is missing"), arrayList, null);
                }
                throw new PolicyException("Received ONLY balancer addresses but grpclb runtime is missing");
            }
            this.roundRobinDueToGrpclbDepMissing = false;
            AutoConfiguredLoadBalancerFactory autoConfiguredLoadBalancerFactory = AutoConfiguredLoadBalancerFactory.this;
            return new PolicySelection(autoConfiguredLoadBalancerFactory.getProviderOrThrow(autoConfiguredLoadBalancerFactory.defaultPolicy, "using default policy"), list, null);
        }
    }

    /* renamed from: io.grpc.internal.AutoConfiguredLoadBalancerFactory$EmptyPicker */
    private static final class EmptyPicker extends SubchannelPicker {
        private EmptyPicker() {
        }

        public PickResult pickSubchannel(PickSubchannelArgs pickSubchannelArgs) {
            return PickResult.withNoResult();
        }
    }

    /* renamed from: io.grpc.internal.AutoConfiguredLoadBalancerFactory$FailingPicker */
    private static final class FailingPicker extends SubchannelPicker {
        private final Status failure;

        FailingPicker(Status status) {
            this.failure = status;
        }

        public PickResult pickSubchannel(PickSubchannelArgs pickSubchannelArgs) {
            return PickResult.withError(this.failure);
        }
    }

    /* renamed from: io.grpc.internal.AutoConfiguredLoadBalancerFactory$NoopLoadBalancer */
    private static final class NoopLoadBalancer extends LoadBalancer {
        public void handleNameResolutionError(Status status) {
        }

        @Deprecated
        public void handleResolvedAddressGroups(List<EquivalentAddressGroup> list, Attributes attributes) {
        }

        public void handleResolvedAddresses(ResolvedAddresses resolvedAddresses) {
        }

        public void handleSubchannelState(Subchannel subchannel, ConnectivityStateInfo connectivityStateInfo) {
        }

        public void shutdown() {
        }

        private NoopLoadBalancer() {
        }
    }

    @VisibleForTesting
    /* renamed from: io.grpc.internal.AutoConfiguredLoadBalancerFactory$PolicyException */
    static final class PolicyException extends Exception {
        private static final long serialVersionUID = 1;

        private PolicyException(String str) {
            super(str);
        }
    }

    @VisibleForTesting
    /* renamed from: io.grpc.internal.AutoConfiguredLoadBalancerFactory$PolicySelection */
    static final class PolicySelection {
        @Nullable
        final Map<String, ?> config;
        final LoadBalancerProvider provider;
        @Nullable
        final List<EquivalentAddressGroup> serverList;

        PolicySelection(LoadBalancerProvider loadBalancerProvider, List<EquivalentAddressGroup> list, @Nullable Map<String, ?> map) {
            this.provider = (LoadBalancerProvider) Preconditions.checkNotNull(loadBalancerProvider, "provider");
            this.serverList = Collections.unmodifiableList((List) Preconditions.checkNotNull(list, "serverList"));
            this.config = map;
        }

        PolicySelection(LoadBalancerProvider loadBalancerProvider, @Nullable Map<String, ?> map) {
            this.provider = (LoadBalancerProvider) Preconditions.checkNotNull(loadBalancerProvider, "provider");
            this.serverList = null;
            this.config = map;
        }
    }

    public AutoConfiguredLoadBalancerFactory(String str) {
        this(LoadBalancerRegistry.getDefaultRegistry(), str);
    }

    @VisibleForTesting
    AutoConfiguredLoadBalancerFactory(LoadBalancerRegistry loadBalancerRegistry, String str) {
        this.registry = (LoadBalancerRegistry) Preconditions.checkNotNull(loadBalancerRegistry, "registry");
        this.defaultPolicy = (String) Preconditions.checkNotNull(str, "defaultPolicy");
    }

    public LoadBalancer newLoadBalancer(Helper helper) {
        return new AutoConfiguredLoadBalancer(helper);
    }

    /* access modifiers changed from: private */
    public LoadBalancerProvider getProviderOrThrow(String str, String str2) throws PolicyException {
        LoadBalancerProvider provider = this.registry.getProvider(str);
        if (provider != null) {
            return provider;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Trying to load '");
        sb.append(str);
        sb.append("' because ");
        sb.append(str2);
        sb.append(", but it's unavailable");
        throw new PolicyException(sb.toString());
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public ConfigOrError selectLoadBalancerPolicy(Map<String, ?> map) {
        List<LbConfig> list;
        if (map != null) {
            try {
                list = ServiceConfigUtil.unwrapLoadBalancingConfigList(ServiceConfigUtil.getLoadBalancingConfigsFromServiceConfig(map));
            } catch (RuntimeException e) {
                return ConfigOrError.fromError(Status.UNKNOWN.withDescription("can't parse load balancer configuration").withCause(e));
            }
        } else {
            list = null;
        }
        if (list == null || list.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (LbConfig lbConfig : list) {
            String policyName = lbConfig.getPolicyName();
            LoadBalancerProvider provider = this.registry.getProvider(policyName);
            if (provider != null) {
                return ConfigOrError.fromConfig(new PolicySelection(provider, null, lbConfig.getRawConfigValue()));
            }
            arrayList.add(policyName);
        }
        Status status = Status.UNKNOWN;
        StringBuilder sb = new StringBuilder();
        sb.append("None of ");
        sb.append(arrayList);
        sb.append(" specified by Service Config are available.");
        return ConfigOrError.fromError(status.withDescription(sb.toString()));
    }
}
