package p015io.opencensus.trace.unsafe;

import javax.annotation.Nullable;
import p015io.grpc.Context;
import p015io.grpc.Context.C2679Key;
import p015io.opencensus.internal.C2865Utils;
import p015io.opencensus.trace.BlankSpan;
import p015io.opencensus.trace.Span;

/* renamed from: io.opencensus.trace.unsafe.ContextUtils */
public final class ContextUtils {
    @Deprecated
    public static final C2679Key<Span> CONTEXT_SPAN_KEY = Context.key("opencensus-trace-span-key");

    private ContextUtils() {
    }

    public static Context withValue(Context context, @Nullable Span span) {
        return ((Context) C2865Utils.checkNotNull(context, "context")).withValue(CONTEXT_SPAN_KEY, span);
    }

    public static Span getValue(Context context) {
        Span span = (Span) CONTEXT_SPAN_KEY.get((Context) C2865Utils.checkNotNull(context, "context"));
        return span == null ? BlankSpan.INSTANCE : span;
    }
}
