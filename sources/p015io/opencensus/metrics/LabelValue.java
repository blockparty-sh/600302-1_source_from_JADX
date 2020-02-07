package p015io.opencensus.metrics;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@Immutable
/* renamed from: io.opencensus.metrics.LabelValue */
public abstract class LabelValue {
    @Nullable
    public abstract String getValue();

    LabelValue() {
    }

    public static LabelValue create(@Nullable String str) {
        return new AutoValue_LabelValue(str);
    }
}
