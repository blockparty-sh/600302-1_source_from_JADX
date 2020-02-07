package p015io.opencensus.metrics.data;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import javax.annotation.concurrent.Immutable;
import p015io.opencensus.common.Timestamp;
import p015io.opencensus.internal.C2865Utils;

@Immutable
/* renamed from: io.opencensus.metrics.data.Exemplar */
public abstract class Exemplar {
    public abstract Map<String, AttachmentValue> getAttachments();

    public abstract Timestamp getTimestamp();

    public abstract double getValue();

    Exemplar() {
    }

    public static Exemplar create(double d, Timestamp timestamp, Map<String, AttachmentValue> map) {
        C2865Utils.checkNotNull(map, "attachments");
        Map unmodifiableMap = Collections.unmodifiableMap(new HashMap(map));
        for (Entry entry : unmodifiableMap.entrySet()) {
            C2865Utils.checkNotNull(entry.getKey(), "key of attachments");
            C2865Utils.checkNotNull(entry.getValue(), "value of attachments");
        }
        return new AutoValue_Exemplar(d, timestamp, unmodifiableMap);
    }
}
