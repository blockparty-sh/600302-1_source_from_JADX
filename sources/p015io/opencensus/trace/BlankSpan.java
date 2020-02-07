package p015io.opencensus.trace;

import androidx.core.app.NotificationCompat;
import java.util.Map;
import javax.annotation.concurrent.Immutable;
import p015io.opencensus.internal.C2865Utils;

@Immutable
/* renamed from: io.opencensus.trace.BlankSpan */
public final class BlankSpan extends Span {
    public static final BlankSpan INSTANCE = new BlankSpan();

    @Deprecated
    public void addNetworkEvent(NetworkEvent networkEvent) {
    }

    public String toString() {
        return "BlankSpan";
    }

    private BlankSpan() {
        super(SpanContext.INVALID, null);
    }

    public void putAttribute(String str, AttributeValue attributeValue) {
        C2865Utils.checkNotNull(str, "key");
        C2865Utils.checkNotNull(attributeValue, "value");
    }

    public void putAttributes(Map<String, AttributeValue> map) {
        C2865Utils.checkNotNull(map, "attributes");
    }

    public void addAnnotation(String str, Map<String, AttributeValue> map) {
        C2865Utils.checkNotNull(str, "description");
        C2865Utils.checkNotNull(map, "attributes");
    }

    public void addAnnotation(Annotation annotation) {
        C2865Utils.checkNotNull(annotation, "annotation");
    }

    public void addMessageEvent(MessageEvent messageEvent) {
        C2865Utils.checkNotNull(messageEvent, "messageEvent");
    }

    public void addLink(Link link) {
        C2865Utils.checkNotNull(link, "link");
    }

    public void setStatus(Status status) {
        C2865Utils.checkNotNull(status, NotificationCompat.CATEGORY_STATUS);
    }

    public void end(EndSpanOptions endSpanOptions) {
        C2865Utils.checkNotNull(endSpanOptions, "options");
    }
}
