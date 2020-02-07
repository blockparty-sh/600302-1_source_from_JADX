package p015io.opencensus.trace;

import javax.annotation.concurrent.Immutable;
import p015io.opencensus.internal.C2865Utils;

@Immutable
/* renamed from: io.opencensus.trace.MessageEvent */
public abstract class MessageEvent extends BaseMessageEvent {

    /* renamed from: io.opencensus.trace.MessageEvent$Builder */
    public static abstract class Builder {
        public abstract MessageEvent build();

        public abstract Builder setCompressedMessageSize(long j);

        /* access modifiers changed from: 0000 */
        public abstract Builder setMessageId(long j);

        /* access modifiers changed from: 0000 */
        public abstract Builder setType(C2900Type type);

        public abstract Builder setUncompressedMessageSize(long j);

        Builder() {
        }
    }

    /* renamed from: io.opencensus.trace.MessageEvent$Type */
    public enum C2900Type {
        SENT,
        RECEIVED
    }

    public abstract long getCompressedMessageSize();

    public abstract long getMessageId();

    public abstract C2900Type getType();

    public abstract long getUncompressedMessageSize();

    public static Builder builder(C2900Type type, long j) {
        return new Builder().setType((C2900Type) C2865Utils.checkNotNull(type, "type")).setMessageId(j).setUncompressedMessageSize(0).setCompressedMessageSize(0);
    }

    MessageEvent() {
    }
}
