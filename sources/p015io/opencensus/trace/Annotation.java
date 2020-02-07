package p015io.opencensus.trace;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.concurrent.Immutable;
import p015io.opencensus.internal.C2865Utils;

@Immutable
/* renamed from: io.opencensus.trace.Annotation */
public abstract class Annotation {
    private static final Map<String, AttributeValue> EMPTY_ATTRIBUTES = Collections.unmodifiableMap(Collections.emptyMap());

    public abstract Map<String, AttributeValue> getAttributes();

    public abstract String getDescription();

    public static Annotation fromDescription(String str) {
        return new AutoValue_Annotation(str, EMPTY_ATTRIBUTES);
    }

    public static Annotation fromDescriptionAndAttributes(String str, Map<String, AttributeValue> map) {
        return new AutoValue_Annotation(str, Collections.unmodifiableMap(new HashMap((Map) C2865Utils.checkNotNull(map, "attributes"))));
    }

    Annotation() {
    }
}
