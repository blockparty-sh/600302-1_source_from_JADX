package p015io.grpc;

import androidx.core.app.NotificationCompat;
import com.google.common.base.MoreObjects;
import com.google.common.base.MoreObjects.ToStringHelper;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;
import p015io.grpc.Attributes.C2669Key;

@ExperimentalApi("https://github.com/grpc/grpc-java/issues/1771")
@NotThreadSafe
/* renamed from: io.grpc.LoadBalancer */
public abstract class LoadBalancer {
    public static final C2669Key<Map<String, ?>> ATTR_LOAD_BALANCING_CONFIG = C2669Key.create("io.grpc.LoadBalancer.loadBalancingConfig");

    @ExperimentalApi("https://github.com/grpc/grpc-java/issues/1771")
    @ThreadSafe
    /* renamed from: io.grpc.LoadBalancer$Factory */
    public static abstract class Factory {
        public abstract LoadBalancer newLoadBalancer(Helper helper);
    }

    @ExperimentalApi("https://github.com/grpc/grpc-java/issues/1771")
    @ThreadSafe
    /* renamed from: io.grpc.LoadBalancer$Helper */
    public static abstract class Helper {
        public abstract ManagedChannel createOobChannel(EquivalentAddressGroup equivalentAddressGroup, String str);

        public abstract String getAuthority();

        @Deprecated
        public abstract p015io.grpc.NameResolver.Factory getNameResolverFactory();

        public abstract void updateBalancingState(@Nonnull ConnectivityState connectivityState, @Nonnull SubchannelPicker subchannelPicker);

        public final Subchannel createSubchannel(EquivalentAddressGroup equivalentAddressGroup, Attributes attributes) {
            Preconditions.checkNotNull(equivalentAddressGroup, "addrs");
            return createSubchannel(Collections.singletonList(equivalentAddressGroup), attributes);
        }

        public Subchannel createSubchannel(List<EquivalentAddressGroup> list, Attributes attributes) {
            throw new UnsupportedOperationException();
        }

        public final void updateSubchannelAddresses(Subchannel subchannel, EquivalentAddressGroup equivalentAddressGroup) {
            Preconditions.checkNotNull(equivalentAddressGroup, "addrs");
            updateSubchannelAddresses(subchannel, Collections.singletonList(equivalentAddressGroup));
        }

        public void updateSubchannelAddresses(Subchannel subchannel, List<EquivalentAddressGroup> list) {
            throw new UnsupportedOperationException();
        }

        public void updateOobChannelAddresses(ManagedChannel managedChannel, EquivalentAddressGroup equivalentAddressGroup) {
            throw new UnsupportedOperationException();
        }

        public ManagedChannel createResolvingOobChannel(String str) {
            throw new UnsupportedOperationException("Not implemented");
        }

        public void refreshNameResolution() {
            throw new UnsupportedOperationException();
        }

        @Deprecated
        public void runSerialized(Runnable runnable) {
            getSynchronizationContext().execute(runnable);
        }

        public SynchronizationContext getSynchronizationContext() {
            throw new UnsupportedOperationException();
        }

        public ScheduledExecutorService getScheduledExecutorService() {
            throw new UnsupportedOperationException();
        }

        public ChannelLogger getChannelLogger() {
            throw new UnsupportedOperationException();
        }
    }

    @ExperimentalApi("https://github.com/grpc/grpc-java/issues/1771")
    @Immutable
    /* renamed from: io.grpc.LoadBalancer$PickResult */
    public static final class PickResult {
        private static final PickResult NO_RESULT = new PickResult(null, null, Status.f678OK, false);
        private final boolean drop;
        private final Status status;
        @Nullable
        private final p015io.grpc.ClientStreamTracer.Factory streamTracerFactory;
        @Nullable
        private final Subchannel subchannel;

        private PickResult(@Nullable Subchannel subchannel2, @Nullable p015io.grpc.ClientStreamTracer.Factory factory, Status status2, boolean z) {
            this.subchannel = subchannel2;
            this.streamTracerFactory = factory;
            this.status = (Status) Preconditions.checkNotNull(status2, NotificationCompat.CATEGORY_STATUS);
            this.drop = z;
        }

        public static PickResult withSubchannel(Subchannel subchannel2, @Nullable p015io.grpc.ClientStreamTracer.Factory factory) {
            return new PickResult((Subchannel) Preconditions.checkNotNull(subchannel2, "subchannel"), factory, Status.f678OK, false);
        }

        public static PickResult withSubchannel(Subchannel subchannel2) {
            return withSubchannel(subchannel2, null);
        }

        public static PickResult withError(Status status2) {
            Preconditions.checkArgument(!status2.isOk(), "error status shouldn't be OK");
            return new PickResult(null, null, status2, false);
        }

        public static PickResult withDrop(Status status2) {
            Preconditions.checkArgument(!status2.isOk(), "drop status shouldn't be OK");
            return new PickResult(null, null, status2, true);
        }

        public static PickResult withNoResult() {
            return NO_RESULT;
        }

        @Nullable
        public Subchannel getSubchannel() {
            return this.subchannel;
        }

        @Nullable
        public p015io.grpc.ClientStreamTracer.Factory getStreamTracerFactory() {
            return this.streamTracerFactory;
        }

        public Status getStatus() {
            return this.status;
        }

        public boolean isDrop() {
            return this.drop;
        }

        public String toString() {
            String str = "streamTracerFactory";
            ToStringHelper add = MoreObjects.toStringHelper((Object) this).add("subchannel", (Object) this.subchannel).add(str, (Object) this.streamTracerFactory);
            return add.add(NotificationCompat.CATEGORY_STATUS, (Object) this.status).add("drop", this.drop).toString();
        }

        public int hashCode() {
            return Objects.hashCode(this.subchannel, this.status, this.streamTracerFactory, Boolean.valueOf(this.drop));
        }

        public boolean equals(Object obj) {
            boolean z = false;
            if (!(obj instanceof PickResult)) {
                return false;
            }
            PickResult pickResult = (PickResult) obj;
            if (Objects.equal(this.subchannel, pickResult.subchannel) && Objects.equal(this.status, pickResult.status) && Objects.equal(this.streamTracerFactory, pickResult.streamTracerFactory) && this.drop == pickResult.drop) {
                z = true;
            }
            return z;
        }
    }

    @ExperimentalApi("https://github.com/grpc/grpc-java/issues/1771")
    /* renamed from: io.grpc.LoadBalancer$PickSubchannelArgs */
    public static abstract class PickSubchannelArgs {
        public abstract CallOptions getCallOptions();

        public abstract Metadata getHeaders();

        public abstract MethodDescriptor<?, ?> getMethodDescriptor();
    }

    @ExperimentalApi("https://github.com/grpc/grpc-java/issues/1771")
    /* renamed from: io.grpc.LoadBalancer$ResolvedAddresses */
    public static final class ResolvedAddresses {
        private final List<EquivalentAddressGroup> addresses;
        private final Attributes attributes;
        @Nullable
        private final Object loadBalancingPolicyConfig;

        @ExperimentalApi("https://github.com/grpc/grpc-java/issues/1771")
        /* renamed from: io.grpc.LoadBalancer$ResolvedAddresses$Builder */
        public static final class Builder {
            private List<EquivalentAddressGroup> addresses;
            private Attributes attributes = Attributes.EMPTY;
            @Nullable
            private Object loadBalancingPolicyConfig;

            Builder() {
            }

            public Builder setAddresses(List<EquivalentAddressGroup> list) {
                this.addresses = list;
                return this;
            }

            public Builder setAttributes(Attributes attributes2) {
                this.attributes = attributes2;
                return this;
            }

            public Builder setLoadBalancingPolicyConfig(@Nullable Object obj) {
                this.loadBalancingPolicyConfig = obj;
                return this;
            }

            public ResolvedAddresses build() {
                return new ResolvedAddresses(this.addresses, this.attributes, this.loadBalancingPolicyConfig);
            }
        }

        private ResolvedAddresses(List<EquivalentAddressGroup> list, Attributes attributes2, Object obj) {
            this.addresses = Collections.unmodifiableList(new ArrayList((Collection) Preconditions.checkNotNull(list, "addresses")));
            this.attributes = (Attributes) Preconditions.checkNotNull(attributes2, "attributes");
            this.loadBalancingPolicyConfig = obj;
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder toBuilder() {
            return newBuilder().setAddresses(this.addresses).setAttributes(this.attributes).setLoadBalancingPolicyConfig(this.loadBalancingPolicyConfig);
        }

        public List<EquivalentAddressGroup> getAddresses() {
            return this.addresses;
        }

        public Attributes getAttributes() {
            return this.attributes;
        }

        @Nullable
        public Object getLoadBalancingPolicyConfig() {
            return this.loadBalancingPolicyConfig;
        }

        public String toString() {
            return MoreObjects.toStringHelper((Object) this).add("addresses", (Object) this.addresses).add("attributes", (Object) this.attributes).add("loadBalancingPolicyConfig", this.loadBalancingPolicyConfig).toString();
        }

        public int hashCode() {
            return Objects.hashCode(this.addresses, this.attributes, this.loadBalancingPolicyConfig);
        }

        public boolean equals(Object obj) {
            boolean z = false;
            if (!(obj instanceof ResolvedAddresses)) {
                return false;
            }
            ResolvedAddresses resolvedAddresses = (ResolvedAddresses) obj;
            if (Objects.equal(this.addresses, resolvedAddresses.addresses) && Objects.equal(this.attributes, resolvedAddresses.attributes) && Objects.equal(this.loadBalancingPolicyConfig, resolvedAddresses.loadBalancingPolicyConfig)) {
                z = true;
            }
            return z;
        }
    }

    @ExperimentalApi("https://github.com/grpc/grpc-java/issues/1771")
    /* renamed from: io.grpc.LoadBalancer$Subchannel */
    public static abstract class Subchannel {
        public abstract Attributes getAttributes();

        public abstract void requestConnection();

        public abstract void shutdown();

        public final EquivalentAddressGroup getAddresses() {
            List allAddresses = getAllAddresses();
            boolean z = true;
            if (allAddresses.size() != 1) {
                z = false;
            }
            Preconditions.checkState(z, "Does not have exactly one group");
            return (EquivalentAddressGroup) allAddresses.get(0);
        }

        public List<EquivalentAddressGroup> getAllAddresses() {
            throw new UnsupportedOperationException();
        }

        @Internal
        public Channel asChannel() {
            throw new UnsupportedOperationException();
        }

        public ChannelLogger getChannelLogger() {
            throw new UnsupportedOperationException();
        }
    }

    @ExperimentalApi("https://github.com/grpc/grpc-java/issues/1771")
    @ThreadSafe
    /* renamed from: io.grpc.LoadBalancer$SubchannelPicker */
    public static abstract class SubchannelPicker {
        public abstract PickResult pickSubchannel(PickSubchannelArgs pickSubchannelArgs);

        public void requestConnection() {
        }
    }

    public boolean canHandleEmptyAddressListFromNameResolution() {
        return false;
    }

    public abstract void handleNameResolutionError(Status status);

    public abstract void handleSubchannelState(Subchannel subchannel, ConnectivityStateInfo connectivityStateInfo);

    public abstract void shutdown();

    @Deprecated
    public void handleResolvedAddressGroups(List<EquivalentAddressGroup> list, Attributes attributes) {
        handleResolvedAddresses(ResolvedAddresses.newBuilder().setAddresses(list).setAttributes(attributes).build());
    }

    public void handleResolvedAddresses(ResolvedAddresses resolvedAddresses) {
        handleResolvedAddressGroups(resolvedAddresses.getAddresses(), resolvedAddresses.getAttributes());
    }
}
