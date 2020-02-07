package p015io.opencensus.metrics.data;

import javax.annotation.concurrent.Immutable;

/* renamed from: io.opencensus.metrics.data.AttachmentValue */
public abstract class AttachmentValue {

    @Immutable
    /* renamed from: io.opencensus.metrics.data.AttachmentValue$AttachmentValueString */
    public static abstract class AttachmentValueString extends AttachmentValue {
        AttachmentValueString() {
        }

        public static AttachmentValueString create(String str) {
            return new AutoValue_AttachmentValue_AttachmentValueString(str);
        }
    }

    public abstract String getValue();
}
