package p015io.opencensus.tags;

import javax.annotation.concurrent.Immutable;
import p015io.opencensus.internal.C2865Utils;
import p015io.opencensus.internal.StringUtils;

@Immutable
/* renamed from: io.opencensus.tags.TagKey */
public abstract class TagKey {
    public static final int MAX_LENGTH = 255;

    public abstract String getName();

    TagKey() {
    }

    public static TagKey create(String str) {
        C2865Utils.checkArgument(isValid(str), "Invalid TagKey name: %s", str);
        return new AutoValue_TagKey(str);
    }

    private static boolean isValid(String str) {
        return !str.isEmpty() && str.length() <= 255 && StringUtils.isPrintableString(str);
    }
}
