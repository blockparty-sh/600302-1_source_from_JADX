package p015io.opencensus.trace.samplers;

import java.util.List;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import p015io.opencensus.internal.C2865Utils;
import p015io.opencensus.trace.Sampler;
import p015io.opencensus.trace.Span;
import p015io.opencensus.trace.SpanContext;
import p015io.opencensus.trace.SpanId;
import p015io.opencensus.trace.TraceId;

@Immutable
/* renamed from: io.opencensus.trace.samplers.ProbabilitySampler */
abstract class ProbabilitySampler extends Sampler {
    /* access modifiers changed from: 0000 */
    public abstract long getIdUpperBound();

    /* access modifiers changed from: 0000 */
    public abstract double getProbability();

    ProbabilitySampler() {
    }

    static ProbabilitySampler create(double d) {
        int i = (d > 0.0d ? 1 : (d == 0.0d ? 0 : -1));
        C2865Utils.checkArgument(i >= 0 && d <= 1.0d, "probability must be in range [0.0, 1.0]");
        long j = i == 0 ? Long.MIN_VALUE : d == 1.0d ? Long.MAX_VALUE : (long) (9.223372036854776E18d * d);
        return new AutoValue_ProbabilitySampler(d, j);
    }

    public final boolean shouldSample(@Nullable SpanContext spanContext, @Nullable Boolean bool, TraceId traceId, SpanId spanId, String str, @Nullable List<Span> list) {
        boolean z = true;
        if (spanContext != null && spanContext.getTraceOptions().isSampled()) {
            return true;
        }
        if (list != null) {
            for (Span context : list) {
                if (context.getContext().getTraceOptions().isSampled()) {
                    return true;
                }
            }
        }
        if (Math.abs(traceId.getLowerLong()) >= getIdUpperBound()) {
            z = false;
        }
        return z;
    }

    public final String getDescription() {
        return String.format("ProbabilitySampler{%.6f}", new Object[]{Double.valueOf(getProbability())});
    }
}
