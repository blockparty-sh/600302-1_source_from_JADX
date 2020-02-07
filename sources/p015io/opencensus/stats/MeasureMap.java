package p015io.opencensus.stats;

import javax.annotation.concurrent.NotThreadSafe;
import p015io.opencensus.internal.C2865Utils;
import p015io.opencensus.metrics.data.AttachmentValue;
import p015io.opencensus.metrics.data.AttachmentValue.AttachmentValueString;
import p015io.opencensus.stats.Measure.MeasureDouble;
import p015io.opencensus.stats.Measure.MeasureLong;
import p015io.opencensus.tags.TagContext;

@NotThreadSafe
/* renamed from: io.opencensus.stats.MeasureMap */
public abstract class MeasureMap {
    public abstract MeasureMap put(MeasureDouble measureDouble, double d);

    public abstract MeasureMap put(MeasureLong measureLong, long j);

    public abstract void record();

    public abstract void record(TagContext tagContext);

    @Deprecated
    public MeasureMap putAttachment(String str, String str2) {
        return putAttachment(str, (AttachmentValue) AttachmentValueString.create(str2));
    }

    public MeasureMap putAttachment(String str, AttachmentValue attachmentValue) {
        C2865Utils.checkNotNull(str, "key");
        C2865Utils.checkNotNull(attachmentValue, "value");
        return this;
    }
}
