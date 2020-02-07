package p015io.grpc;

import com.google.common.p011io.BaseEncoding;
import java.nio.charset.Charset;
import p015io.grpc.Metadata.AsciiMarshaller;
import p015io.grpc.Metadata.C2689Key;

@Internal
/* renamed from: io.grpc.InternalMetadata */
public final class InternalMetadata {
    @Internal
    public static final BaseEncoding BASE64_ENCODING_OMIT_PADDING = Metadata.BASE64_ENCODING_OMIT_PADDING;
    @Internal
    public static final Charset US_ASCII = Charset.forName("US-ASCII");

    @Internal
    /* renamed from: io.grpc.InternalMetadata$TrustedAsciiMarshaller */
    public interface TrustedAsciiMarshaller<T> extends TrustedAsciiMarshaller<T> {
    }

    @Internal
    public static <T> C2689Key<T> keyOf(String str, TrustedAsciiMarshaller<T> trustedAsciiMarshaller) {
        boolean z = false;
        if (str != null && !str.isEmpty() && str.charAt(0) == ':') {
            z = true;
        }
        return C2689Key.m298of(str, z, (TrustedAsciiMarshaller<T>) trustedAsciiMarshaller);
    }

    @Internal
    public static <T> C2689Key<T> keyOf(String str, AsciiMarshaller<T> asciiMarshaller) {
        boolean z = false;
        if (str != null && !str.isEmpty() && str.charAt(0) == ':') {
            z = true;
        }
        return C2689Key.m297of(str, z, asciiMarshaller);
    }

    @Internal
    public static Metadata newMetadata(byte[]... bArr) {
        return new Metadata(bArr);
    }

    @Internal
    public static Metadata newMetadata(int i, byte[]... bArr) {
        return new Metadata(i, bArr);
    }

    @Internal
    public static byte[][] serialize(Metadata metadata) {
        return metadata.serialize();
    }

    @Internal
    public static int headerCount(Metadata metadata) {
        return metadata.headerCount();
    }
}
