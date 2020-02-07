package p015io.opencensus.tags;

import javax.annotation.concurrent.Immutable;
import p015io.opencensus.tags.TagMetadata.TagTtl;

@Immutable
/* renamed from: io.opencensus.tags.Tag */
public abstract class Tag {
    private static final TagMetadata METADATA_UNLIMITED_PROPAGATION = TagMetadata.create(TagTtl.UNLIMITED_PROPAGATION);

    public abstract TagKey getKey();

    public abstract TagMetadata getTagMetadata();

    public abstract TagValue getValue();

    Tag() {
    }

    @Deprecated
    public static Tag create(TagKey tagKey, TagValue tagValue) {
        return create(tagKey, tagValue, METADATA_UNLIMITED_PROPAGATION);
    }

    public static Tag create(TagKey tagKey, TagValue tagValue, TagMetadata tagMetadata) {
        return new AutoValue_Tag(tagKey, tagValue, tagMetadata);
    }
}
