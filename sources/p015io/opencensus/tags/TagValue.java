package p015io.opencensus.tags;

import javax.annotation.concurrent.Immutable;
import p015io.opencensus.internal.C2865Utils;
import p015io.opencensus.internal.StringUtils;

@Immutable
/* renamed from: io.opencensus.tags.TagValue */
public abstract class TagValue {
    public static final int MAX_LENGTH = 255;

    public abstract String asString();

    TagValue() {
    }

    public static TagValue create(String str) {
        C2865Utils.checkArgument(isValid(str), "Invalid TagValue: %s", str);
        return new AutoValue_TagValue(str);
    }

    private static boolean isValid(String str) {
        return str.length() <= 255 && StringUtils.isPrintableString(str);
    }
}
