package p015io.opencensus.stats;

import javax.annotation.concurrent.Immutable;
import p015io.opencensus.stats.Aggregation.Mean;

@Immutable
@Deprecated
/* renamed from: io.opencensus.stats.AutoValue_Aggregation_Mean */
final class AutoValue_Aggregation_Mean extends Mean {
    public int hashCode() {
        return 1;
    }

    public String toString() {
        return "Mean{}";
    }

    AutoValue_Aggregation_Mean() {
    }

    public boolean equals(Object obj) {
        return obj == this || (obj instanceof Mean);
    }
}
