package p015io.opencensus.trace;

import androidx.core.app.NotificationCompat;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;
import p015io.opencensus.internal.C2865Utils;
import p015io.opencensus.trace.internal.BaseMessageEventUtils;

/* renamed from: io.opencensus.trace.Span */
public abstract class Span {
    private static final Set<Options> DEFAULT_OPTIONS = Collections.unmodifiableSet(EnumSet.noneOf(Options.class));
    private static final Map<String, AttributeValue> EMPTY_ATTRIBUTES = Collections.emptyMap();
    private final SpanContext context;
    private final Set<Options> options;

    /* renamed from: io.opencensus.trace.Span$Kind */
    public enum Kind {
        SERVER,
        CLIENT
    }

    /* renamed from: io.opencensus.trace.Span$Options */
    public enum Options {
        RECORD_EVENTS
    }

    public abstract void addAnnotation(Annotation annotation);

    public abstract void addAnnotation(String str, Map<String, AttributeValue> map);

    public abstract void addLink(Link link);

    public abstract void end(EndSpanOptions endSpanOptions);

    protected Span(SpanContext spanContext, @Nullable EnumSet<Options> enumSet) {
        Set<Options> set;
        this.context = (SpanContext) C2865Utils.checkNotNull(spanContext, "context");
        if (enumSet == null) {
            set = DEFAULT_OPTIONS;
        } else {
            set = Collections.unmodifiableSet(EnumSet.copyOf(enumSet));
        }
        this.options = set;
        C2865Utils.checkArgument(!spanContext.getTraceOptions().isSampled() || this.options.contains(Options.RECORD_EVENTS), "Span is sampled, but does not have RECORD_EVENTS set.");
    }

    public void putAttribute(String str, AttributeValue attributeValue) {
        C2865Utils.checkNotNull(str, "key");
        C2865Utils.checkNotNull(attributeValue, "value");
        putAttributes(Collections.singletonMap(str, attributeValue));
    }

    public void putAttributes(Map<String, AttributeValue> map) {
        C2865Utils.checkNotNull(map, "attributes");
        addAttributes(map);
    }

    @Deprecated
    public void addAttributes(Map<String, AttributeValue> map) {
        putAttributes(map);
    }

    public final void addAnnotation(String str) {
        C2865Utils.checkNotNull(str, "description");
        addAnnotation(str, EMPTY_ATTRIBUTES);
    }

    @Deprecated
    public void addNetworkEvent(NetworkEvent networkEvent) {
        addMessageEvent(BaseMessageEventUtils.asMessageEvent(networkEvent));
    }

    public void addMessageEvent(MessageEvent messageEvent) {
        C2865Utils.checkNotNull(messageEvent, "messageEvent");
        addNetworkEvent(BaseMessageEventUtils.asNetworkEvent(messageEvent));
    }

    public void setStatus(Status status) {
        C2865Utils.checkNotNull(status, NotificationCompat.CATEGORY_STATUS);
    }

    public final void end() {
        end(EndSpanOptions.DEFAULT);
    }

    public final SpanContext getContext() {
        return this.context;
    }

    public final Set<Options> getOptions() {
        return this.options;
    }
}
