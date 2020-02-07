package p015io.opencensus.stats;

import p015io.opencensus.stats.Aggregation.LastValue;

/* renamed from: io.opencensus.stats.AutoValue_Aggregation_LastValue */
final class AutoValue_Aggregation_LastValue extends LastValue {
    public int hashCode() {
        return 1;
    }

    public String toString() {
        return "LastValue{}";
    }

    AutoValue_Aggregation_LastValue() {
    }

    public boolean equals(Object obj) {
        return obj == this || (obj instanceof LastValue);
    }
}
