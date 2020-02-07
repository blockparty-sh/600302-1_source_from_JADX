package p015io.opencensus.metrics.export;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.concurrent.Immutable;
import p015io.opencensus.internal.C2865Utils;
import p015io.opencensus.metrics.LabelKey;

@Immutable
/* renamed from: io.opencensus.metrics.export.MetricDescriptor */
public abstract class MetricDescriptor {

    /* renamed from: io.opencensus.metrics.export.MetricDescriptor$Type */
    public enum C2872Type {
        GAUGE_INT64,
        GAUGE_DOUBLE,
        GAUGE_DISTRIBUTION,
        CUMULATIVE_INT64,
        CUMULATIVE_DOUBLE,
        CUMULATIVE_DISTRIBUTION,
        SUMMARY
    }

    public abstract String getDescription();

    public abstract List<LabelKey> getLabelKeys();

    public abstract String getName();

    public abstract C2872Type getType();

    public abstract String getUnit();

    MetricDescriptor() {
    }

    public static MetricDescriptor create(String str, String str2, String str3, C2872Type type, List<LabelKey> list) {
        C2865Utils.checkListElementNotNull((List) C2865Utils.checkNotNull(list, "labelKeys"), "labelKey");
        AutoValue_MetricDescriptor autoValue_MetricDescriptor = new AutoValue_MetricDescriptor(str, str2, str3, type, Collections.unmodifiableList(new ArrayList(list)));
        return autoValue_MetricDescriptor;
    }
}
