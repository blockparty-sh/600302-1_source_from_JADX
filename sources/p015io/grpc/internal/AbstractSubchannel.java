package p015io.grpc.internal;

import com.google.common.annotations.VisibleForTesting;
import javax.annotation.Nullable;
import p015io.grpc.InternalChannelz.ChannelStats;
import p015io.grpc.InternalInstrumented;
import p015io.grpc.LoadBalancer.Subchannel;

/* renamed from: io.grpc.internal.AbstractSubchannel */
abstract class AbstractSubchannel extends Subchannel {
    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public abstract InternalInstrumented<ChannelStats> getInternalSubchannel();

    /* access modifiers changed from: 0000 */
    @Nullable
    public abstract ClientTransport obtainActiveTransport();

    AbstractSubchannel() {
    }
}
