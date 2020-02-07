package p015io.opencensus.trace;

import com.google.errorprone.annotations.MustBeClosed;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.Nullable;
import p015io.opencensus.common.Scope;
import p015io.opencensus.internal.C2865Utils;
import p015io.opencensus.trace.Span.Kind;

/* renamed from: io.opencensus.trace.SpanBuilder */
public abstract class SpanBuilder {

    /* renamed from: io.opencensus.trace.SpanBuilder$NoopSpanBuilder */
    static final class NoopSpanBuilder extends SpanBuilder {
        public SpanBuilder setParentLinks(List<Span> list) {
            return this;
        }

        public SpanBuilder setRecordEvents(boolean z) {
            return this;
        }

        public SpanBuilder setSampler(@Nullable Sampler sampler) {
            return this;
        }

        public SpanBuilder setSpanKind(@Nullable Kind kind) {
            return this;
        }

        static NoopSpanBuilder createWithParent(String str, @Nullable Span span) {
            return new NoopSpanBuilder(str);
        }

        static NoopSpanBuilder createWithRemoteParent(String str, @Nullable SpanContext spanContext) {
            return new NoopSpanBuilder(str);
        }

        public Span startSpan() {
            return BlankSpan.INSTANCE;
        }

        private NoopSpanBuilder(String str) {
            C2865Utils.checkNotNull(str, "name");
        }
    }

    public abstract SpanBuilder setParentLinks(List<Span> list);

    public abstract SpanBuilder setRecordEvents(boolean z);

    public abstract SpanBuilder setSampler(Sampler sampler);

    public SpanBuilder setSpanKind(@Nullable Kind kind) {
        return this;
    }

    public abstract Span startSpan();

    @MustBeClosed
    public final Scope startScopedSpan() {
        return CurrentSpanUtils.withSpan(startSpan(), true);
    }

    public final void startSpanAndRun(Runnable runnable) {
        CurrentSpanUtils.withSpan(startSpan(), true, runnable).run();
    }

    public final <V> V startSpanAndCall(Callable<V> callable) throws Exception {
        return CurrentSpanUtils.withSpan(startSpan(), true, callable).call();
    }
}
