package p015io.opencensus.metrics.export;

import javax.annotation.concurrent.Immutable;
import p015io.opencensus.common.Timestamp;

@Immutable
/* renamed from: io.opencensus.metrics.export.Point */
public abstract class Point {
    public abstract Timestamp getTimestamp();

    public abstract Value getValue();

    Point() {
    }

    public static Point create(Value value, Timestamp timestamp) {
        return new AutoValue_Point(value, timestamp);
    }
}
