package p015io.grpc.internal;

import com.google.common.base.Preconditions;
import javax.annotation.Nullable;
import p015io.grpc.NameResolver.ConfigOrError;

/* renamed from: io.grpc.internal.ServiceConfigState */
final class ServiceConfigState {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    @Nullable
    private ConfigOrError currentServiceConfigOrError;
    @Nullable
    private final ConfigOrError defaultServiceConfig;
    private final boolean lookUpServiceConfig;
    private boolean updated;

    ServiceConfigState(@Nullable ManagedChannelServiceConfig managedChannelServiceConfig, boolean z) {
        if (managedChannelServiceConfig == null) {
            this.defaultServiceConfig = null;
        } else {
            this.defaultServiceConfig = ConfigOrError.fromConfig(managedChannelServiceConfig);
        }
        this.lookUpServiceConfig = z;
        if (!z) {
            this.currentServiceConfigOrError = this.defaultServiceConfig;
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean shouldWaitOnServiceConfig() {
        return !this.updated && expectUpdates();
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public ConfigOrError getCurrent() {
        Preconditions.checkState(!shouldWaitOnServiceConfig(), "still waiting on service config");
        return this.currentServiceConfigOrError;
    }

    /* access modifiers changed from: 0000 */
    public void update(@Nullable ConfigOrError configOrError) {
        Preconditions.checkState(expectUpdates(), "unexpected service config update");
        boolean z = !this.updated;
        this.updated = true;
        if (z) {
            if (configOrError == null) {
                this.currentServiceConfigOrError = this.defaultServiceConfig;
            } else if (configOrError.getError() != null) {
                ConfigOrError configOrError2 = this.defaultServiceConfig;
                if (configOrError2 != null) {
                    this.currentServiceConfigOrError = configOrError2;
                } else {
                    this.currentServiceConfigOrError = configOrError;
                }
            } else {
                this.currentServiceConfigOrError = configOrError;
            }
        } else if (configOrError == null) {
            ConfigOrError configOrError3 = this.defaultServiceConfig;
            if (configOrError3 != null) {
                this.currentServiceConfigOrError = configOrError3;
            } else {
                this.currentServiceConfigOrError = null;
            }
        } else if (configOrError.getError() != null) {
            ConfigOrError configOrError4 = this.currentServiceConfigOrError;
            if (configOrError4 != null && configOrError4.getError() != null) {
                this.currentServiceConfigOrError = configOrError;
            }
        } else {
            this.currentServiceConfigOrError = configOrError;
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean expectUpdates() {
        return this.lookUpServiceConfig;
    }
}
