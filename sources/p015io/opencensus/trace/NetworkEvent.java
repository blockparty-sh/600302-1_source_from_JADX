package p015io.opencensus.trace;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import p015io.opencensus.common.Timestamp;
import p015io.opencensus.internal.C2865Utils;

@Immutable
@Deprecated
/* renamed from: io.opencensus.trace.NetworkEvent */
public abstract class NetworkEvent extends BaseMessageEvent {

    @Deprecated
    /* renamed from: io.opencensus.trace.NetworkEvent$Builder */
    public static abstract class Builder {
        public abstract NetworkEvent build();

        public abstract Builder setCompressedMessageSize(long j);

        public abstract Builder setKernelTimestamp(@Nullable Timestamp timestamp);

        /* access modifiers changed from: 0000 */
        public abstract Builder setMessageId(long j);

        /* access modifiers changed from: 0000 */
        public abstract Builder setType(C2901Type type);

        public abstract Builder setUncompressedMessageSize(long j);

        @Deprecated
        public Builder setMessageSize(long j) {
            return setUncompressedMessageSize(j);
        }

        Builder() {
        }
    }

    /* renamed from: io.opencensus.trace.NetworkEvent$Type */
    public enum C2901Type {
        SENT,
        RECV
    }

    public abstract long getCompressedMessageSize();

    @Nullable
    public abstract Timestamp getKernelTimestamp();

    public abstract long getMessageId();

    public abstract C2901Type getType();

    public abstract long getUncompressedMessageSize();

    public static Builder builder(C2901Type type, long j) {
        return new Builder().setType((C2901Type) C2865Utils.checkNotNull(type, "type")).setMessageId(j).setUncompressedMessageSize(0).setCompressedMessageSize(0);
    }

    @Deprecated
    public long getMessageSize() {
        return getUncompressedMessageSize();
    }

    NetworkEvent() {
    }
}
