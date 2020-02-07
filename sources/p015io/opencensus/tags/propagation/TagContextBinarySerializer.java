package p015io.opencensus.tags.propagation;

import p015io.opencensus.tags.TagContext;

/* renamed from: io.opencensus.tags.propagation.TagContextBinarySerializer */
public abstract class TagContextBinarySerializer {
    public abstract TagContext fromByteArray(byte[] bArr) throws TagContextDeserializationException;

    public abstract byte[] toByteArray(TagContext tagContext) throws TagContextSerializationException;
}
