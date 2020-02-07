package p015io.grpc;

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;
import p015io.grpc.Codec.Gzip;
import p015io.grpc.Codec.Identity;

@ExperimentalApi("https://github.com/grpc/grpc-java/issues/1704")
@ThreadSafe
/* renamed from: io.grpc.DecompressorRegistry */
public final class DecompressorRegistry {
    static final Joiner ACCEPT_ENCODING_JOINER = Joiner.m95on(',');
    private static final DecompressorRegistry DEFAULT_INSTANCE = emptyInstance().with(new Gzip(), true).with(Identity.NONE, false);
    private final byte[] advertisedDecompressors;
    private final Map<String, DecompressorInfo> decompressors;

    /* renamed from: io.grpc.DecompressorRegistry$DecompressorInfo */
    private static final class DecompressorInfo {
        final boolean advertised;
        final Decompressor decompressor;

        DecompressorInfo(Decompressor decompressor2, boolean z) {
            this.decompressor = (Decompressor) Preconditions.checkNotNull(decompressor2, "decompressor");
            this.advertised = z;
        }
    }

    public static DecompressorRegistry emptyInstance() {
        return new DecompressorRegistry();
    }

    public static DecompressorRegistry getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public DecompressorRegistry with(Decompressor decompressor, boolean z) {
        return new DecompressorRegistry(decompressor, z, this);
    }

    private DecompressorRegistry(Decompressor decompressor, boolean z, DecompressorRegistry decompressorRegistry) {
        String messageEncoding = decompressor.getMessageEncoding();
        Preconditions.checkArgument(!messageEncoding.contains(","), "Comma is currently not allowed in message encoding");
        int size = decompressorRegistry.decompressors.size();
        if (!decompressorRegistry.decompressors.containsKey(decompressor.getMessageEncoding())) {
            size++;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(size);
        for (DecompressorInfo decompressorInfo : decompressorRegistry.decompressors.values()) {
            String messageEncoding2 = decompressorInfo.decompressor.getMessageEncoding();
            if (!messageEncoding2.equals(messageEncoding)) {
                linkedHashMap.put(messageEncoding2, new DecompressorInfo(decompressorInfo.decompressor, decompressorInfo.advertised));
            }
        }
        linkedHashMap.put(messageEncoding, new DecompressorInfo(decompressor, z));
        this.decompressors = Collections.unmodifiableMap(linkedHashMap);
        this.advertisedDecompressors = ACCEPT_ENCODING_JOINER.join((Iterable<?>) getAdvertisedMessageEncodings()).getBytes(Charset.forName("US-ASCII"));
    }

    private DecompressorRegistry() {
        this.decompressors = new LinkedHashMap(0);
        this.advertisedDecompressors = new byte[0];
    }

    public Set<String> getKnownMessageEncodings() {
        return this.decompressors.keySet();
    }

    /* access modifiers changed from: 0000 */
    public byte[] getRawAdvertisedMessageEncodings() {
        return this.advertisedDecompressors;
    }

    @ExperimentalApi("https://github.com/grpc/grpc-java/issues/1704")
    public Set<String> getAdvertisedMessageEncodings() {
        HashSet hashSet = new HashSet(this.decompressors.size());
        for (Entry entry : this.decompressors.entrySet()) {
            if (((DecompressorInfo) entry.getValue()).advertised) {
                hashSet.add(entry.getKey());
            }
        }
        return Collections.unmodifiableSet(hashSet);
    }

    @Nullable
    public Decompressor lookupDecompressor(String str) {
        DecompressorInfo decompressorInfo = (DecompressorInfo) this.decompressors.get(str);
        if (decompressorInfo != null) {
            return decompressorInfo.decompressor;
        }
        return null;
    }
}
