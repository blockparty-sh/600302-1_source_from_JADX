package p015io.grpc;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.google.common.p011io.BaseEncoding;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Set;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
/* renamed from: io.grpc.Metadata */
public final class Metadata {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final AsciiMarshaller<String> ASCII_STRING_MARSHALLER = new AsciiMarshaller<String>() {
        public String parseAsciiString(String str) {
            return str;
        }

        public String toAsciiString(String str) {
            return str;
        }
    };
    static final BaseEncoding BASE64_ENCODING_OMIT_PADDING = BaseEncoding.base64().omitPadding();
    public static final BinaryMarshaller<byte[]> BINARY_BYTE_MARSHALLER = new BinaryMarshaller<byte[]>() {
        public byte[] parseBytes(byte[] bArr) {
            return bArr;
        }

        public byte[] toBytes(byte[] bArr) {
            return bArr;
        }
    };
    public static final String BINARY_HEADER_SUFFIX = "-bin";
    private byte[][] namesAndValues;
    /* access modifiers changed from: private */
    public int size;

    /* renamed from: io.grpc.Metadata$AsciiKey */
    private static class AsciiKey<T> extends C2689Key<T> {
        private final AsciiMarshaller<T> marshaller;

        private AsciiKey(String str, boolean z, AsciiMarshaller<T> asciiMarshaller) {
            super(str, z);
            String str2 = Metadata.BINARY_HEADER_SUFFIX;
            Preconditions.checkArgument(!str.endsWith(str2), "ASCII header is named %s.  Only binary headers may end with %s", (Object) str, (Object) str2);
            this.marshaller = (AsciiMarshaller) Preconditions.checkNotNull(asciiMarshaller, "marshaller");
        }

        /* access modifiers changed from: 0000 */
        public byte[] toBytes(T t) {
            return this.marshaller.toAsciiString(t).getBytes(Charsets.US_ASCII);
        }

        /* access modifiers changed from: 0000 */
        public T parseBytes(byte[] bArr) {
            return this.marshaller.parseAsciiString(new String(bArr, Charsets.US_ASCII));
        }
    }

    /* renamed from: io.grpc.Metadata$AsciiMarshaller */
    public interface AsciiMarshaller<T> {
        T parseAsciiString(String str);

        String toAsciiString(T t);
    }

    /* renamed from: io.grpc.Metadata$BinaryKey */
    private static class BinaryKey<T> extends C2689Key<T> {
        private final BinaryMarshaller<T> marshaller;

        private BinaryKey(String str, BinaryMarshaller<T> binaryMarshaller) {
            boolean z = false;
            super(str, false);
            String str2 = Metadata.BINARY_HEADER_SUFFIX;
            Preconditions.checkArgument(str.endsWith(str2), "Binary header is named %s. It must end with %s", (Object) str, (Object) str2);
            if (str.length() > 4) {
                z = true;
            }
            Preconditions.checkArgument(z, "empty key name");
            this.marshaller = (BinaryMarshaller) Preconditions.checkNotNull(binaryMarshaller, "marshaller is null");
        }

        /* access modifiers changed from: 0000 */
        public byte[] toBytes(T t) {
            return this.marshaller.toBytes(t);
        }

        /* access modifiers changed from: 0000 */
        public T parseBytes(byte[] bArr) {
            return this.marshaller.parseBytes(bArr);
        }
    }

    /* renamed from: io.grpc.Metadata$BinaryMarshaller */
    public interface BinaryMarshaller<T> {
        T parseBytes(byte[] bArr);

        byte[] toBytes(T t);
    }

    /* renamed from: io.grpc.Metadata$IterableAt */
    private final class IterableAt<T> implements Iterable<T> {
        /* access modifiers changed from: private */
        public final C2689Key<T> key;
        /* access modifiers changed from: private */
        public int startIdx;

        private IterableAt(C2689Key<T> key2, int i) {
            this.key = key2;
            this.startIdx = i;
        }

        public Iterator<T> iterator() {
            return new Iterator<T>() {
                private boolean hasNext = true;
                private int idx = IterableAt.this.startIdx;

                public boolean hasNext() {
                    if (this.hasNext) {
                        return true;
                    }
                    while (this.idx < Metadata.this.size) {
                        if (Metadata.this.bytesEqual(IterableAt.this.key.asciiName(), Metadata.this.name(this.idx))) {
                            this.hasNext = true;
                            return this.hasNext;
                        }
                        this.idx++;
                    }
                    return false;
                }

                public T next() {
                    if (hasNext()) {
                        this.hasNext = false;
                        C2689Key access$200 = IterableAt.this.key;
                        Metadata metadata = Metadata.this;
                        int i = this.idx;
                        this.idx = i + 1;
                        return access$200.parseBytes(metadata.value(i));
                    }
                    throw new NoSuchElementException();
                }

                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }

    @Immutable
    /* renamed from: io.grpc.Metadata$Key */
    public static abstract class C2689Key<T> {
        private static final BitSet VALID_T_CHARS = generateValidTChars();
        private final String name;
        private final byte[] nameBytes;
        private final String originalName;

        /* access modifiers changed from: 0000 */
        public abstract T parseBytes(byte[] bArr);

        /* access modifiers changed from: 0000 */
        public abstract byte[] toBytes(T t);

        /* renamed from: of */
        public static <T> C2689Key<T> m296of(String str, BinaryMarshaller<T> binaryMarshaller) {
            return new BinaryKey(str, binaryMarshaller);
        }

        /* renamed from: of */
        public static <T> C2689Key<T> m295of(String str, AsciiMarshaller<T> asciiMarshaller) {
            return m297of(str, false, asciiMarshaller);
        }

        /* renamed from: of */
        static <T> C2689Key<T> m297of(String str, boolean z, AsciiMarshaller<T> asciiMarshaller) {
            return new AsciiKey(str, z, asciiMarshaller);
        }

        /* renamed from: of */
        static <T> C2689Key<T> m298of(String str, boolean z, TrustedAsciiMarshaller<T> trustedAsciiMarshaller) {
            return new TrustedAsciiKey(str, z, trustedAsciiMarshaller);
        }

        private static BitSet generateValidTChars() {
            BitSet bitSet = new BitSet(127);
            bitSet.set(45);
            bitSet.set(95);
            bitSet.set(46);
            for (char c = '0'; c <= '9'; c = (char) (c + 1)) {
                bitSet.set(c);
            }
            for (char c2 = 'a'; c2 <= 'z'; c2 = (char) (c2 + 1)) {
                bitSet.set(c2);
            }
            return bitSet;
        }

        private static String validateName(String str, boolean z) {
            Preconditions.checkNotNull(str, "name");
            Preconditions.checkArgument(!str.isEmpty(), "token must have at least 1 tchar");
            for (int i = 0; i < str.length(); i++) {
                char charAt = str.charAt(i);
                if (!z || charAt != ':' || i != 0) {
                    Preconditions.checkArgument(VALID_T_CHARS.get(charAt), "Invalid character '%s' in key name '%s'", charAt, (Object) str);
                }
            }
            return str;
        }

        private C2689Key(String str, boolean z) {
            this.originalName = (String) Preconditions.checkNotNull(str, "name");
            this.name = validateName(this.originalName.toLowerCase(Locale.ROOT), z);
            this.nameBytes = this.name.getBytes(Charsets.US_ASCII);
        }

        public final String originalName() {
            return this.originalName;
        }

        public final String name() {
            return this.name;
        }

        /* access modifiers changed from: 0000 */
        @VisibleForTesting
        public byte[] asciiName() {
            return this.nameBytes;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            return this.name.equals(((C2689Key) obj).name);
        }

        public final int hashCode() {
            return this.name.hashCode();
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Key{name='");
            sb.append(this.name);
            sb.append("'}");
            return sb.toString();
        }
    }

    /* renamed from: io.grpc.Metadata$TrustedAsciiKey */
    private static final class TrustedAsciiKey<T> extends C2689Key<T> {
        private final TrustedAsciiMarshaller<T> marshaller;

        private TrustedAsciiKey(String str, boolean z, TrustedAsciiMarshaller<T> trustedAsciiMarshaller) {
            super(str, z);
            String str2 = Metadata.BINARY_HEADER_SUFFIX;
            Preconditions.checkArgument(!str.endsWith(str2), "ASCII header is named %s.  Only binary headers may end with %s", (Object) str, (Object) str2);
            this.marshaller = (TrustedAsciiMarshaller) Preconditions.checkNotNull(trustedAsciiMarshaller, "marshaller");
        }

        /* access modifiers changed from: 0000 */
        public byte[] toBytes(T t) {
            return this.marshaller.toAsciiString(t);
        }

        /* access modifiers changed from: 0000 */
        public T parseBytes(byte[] bArr) {
            return this.marshaller.parseAsciiString(bArr);
        }
    }

    @Immutable
    /* renamed from: io.grpc.Metadata$TrustedAsciiMarshaller */
    interface TrustedAsciiMarshaller<T> {
        T parseAsciiString(byte[] bArr);

        byte[] toAsciiString(T t);
    }

    Metadata(byte[]... bArr) {
        this(bArr.length / 2, bArr);
    }

    Metadata(int i, byte[]... bArr) {
        this.size = i;
        this.namesAndValues = bArr;
    }

    /* access modifiers changed from: private */
    public byte[] name(int i) {
        return this.namesAndValues[i * 2];
    }

    private void name(int i, byte[] bArr) {
        this.namesAndValues[i * 2] = bArr;
    }

    /* access modifiers changed from: private */
    public byte[] value(int i) {
        return this.namesAndValues[(i * 2) + 1];
    }

    private void value(int i, byte[] bArr) {
        this.namesAndValues[(i * 2) + 1] = bArr;
    }

    private int cap() {
        byte[][] bArr = this.namesAndValues;
        if (bArr != null) {
            return bArr.length;
        }
        return 0;
    }

    private int len() {
        return this.size * 2;
    }

    private boolean isEmpty() {
        return this.size == 0;
    }

    public Metadata() {
    }

    /* access modifiers changed from: 0000 */
    public int headerCount() {
        return this.size;
    }

    public boolean containsKey(C2689Key<?> key) {
        for (int i = 0; i < this.size; i++) {
            if (bytesEqual(key.asciiName(), name(i))) {
                return true;
            }
        }
        return false;
    }

    @Nullable
    public <T> T get(C2689Key<T> key) {
        for (int i = this.size - 1; i >= 0; i--) {
            if (bytesEqual(key.asciiName(), name(i))) {
                return key.parseBytes(value(i));
            }
        }
        return null;
    }

    @Nullable
    public <T> Iterable<T> getAll(C2689Key<T> key) {
        for (int i = 0; i < this.size; i++) {
            if (bytesEqual(key.asciiName(), name(i))) {
                return new IterableAt(key, i);
            }
        }
        return null;
    }

    public Set<String> keys() {
        if (isEmpty()) {
            return Collections.emptySet();
        }
        HashSet hashSet = new HashSet(this.size);
        for (int i = 0; i < this.size; i++) {
            hashSet.add(new String(name(i), 0));
        }
        return Collections.unmodifiableSet(hashSet);
    }

    public <T> void put(C2689Key<T> key, T t) {
        Preconditions.checkNotNull(key, "key");
        Preconditions.checkNotNull(t, "value");
        maybeExpand();
        name(this.size, key.asciiName());
        value(this.size, key.toBytes(t));
        this.size++;
    }

    private void maybeExpand() {
        if (len() == 0 || len() == cap()) {
            expand(Math.max(len() * 2, 8));
        }
    }

    private void expand(int i) {
        byte[][] bArr = new byte[i][];
        if (!isEmpty()) {
            System.arraycopy(this.namesAndValues, 0, bArr, 0, len());
        }
        this.namesAndValues = bArr;
    }

    public <T> boolean remove(C2689Key<T> key, T t) {
        Preconditions.checkNotNull(key, "key");
        Preconditions.checkNotNull(t, "value");
        for (int i = 0; i < this.size; i++) {
            if (bytesEqual(key.asciiName(), name(i)) && t.equals(key.parseBytes(value(i)))) {
                int i2 = i * 2;
                int i3 = (i + 1) * 2;
                int len = len() - i3;
                byte[][] bArr = this.namesAndValues;
                System.arraycopy(bArr, i3, bArr, i2, len);
                this.size--;
                name(this.size, null);
                value(this.size, null);
                return true;
            }
        }
        return false;
    }

    public <T> Iterable<T> removeAll(C2689Key<T> key) {
        if (isEmpty()) {
            return null;
        }
        ArrayList arrayList = null;
        int i = 0;
        for (int i2 = 0; i2 < this.size; i2++) {
            if (bytesEqual(key.asciiName(), name(i2))) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(key.parseBytes(value(i2)));
            } else {
                name(i, name(i2));
                value(i, value(i2));
                i++;
            }
        }
        Arrays.fill(this.namesAndValues, i * 2, len(), null);
        this.size = i;
        return arrayList;
    }

    @ExperimentalApi("https://github.com/grpc/grpc-java/issues/4691")
    public <T> void discardAll(C2689Key<T> key) {
        if (!isEmpty()) {
            int i = 0;
            for (int i2 = 0; i2 < this.size; i2++) {
                if (!bytesEqual(key.asciiName(), name(i2))) {
                    name(i, name(i2));
                    value(i, value(i2));
                    i++;
                }
            }
            Arrays.fill(this.namesAndValues, i * 2, len(), null);
            this.size = i;
        }
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public byte[][] serialize() {
        if (len() == cap()) {
            return this.namesAndValues;
        }
        byte[][] bArr = new byte[len()][];
        System.arraycopy(this.namesAndValues, 0, bArr, 0, len());
        return bArr;
    }

    public void merge(Metadata metadata) {
        if (!metadata.isEmpty()) {
            int cap = cap() - len();
            if (isEmpty() || cap < metadata.len()) {
                expand(len() + metadata.len());
            }
            System.arraycopy(metadata.namesAndValues, 0, this.namesAndValues, len(), metadata.len());
            this.size += metadata.size;
        }
    }

    public void merge(Metadata metadata, Set<C2689Key<?>> set) {
        Preconditions.checkNotNull(metadata, "other");
        HashMap hashMap = new HashMap(set.size());
        for (C2689Key key : set) {
            hashMap.put(ByteBuffer.wrap(key.asciiName()), key);
        }
        for (int i = 0; i < metadata.size; i++) {
            if (hashMap.containsKey(ByteBuffer.wrap(metadata.name(i)))) {
                maybeExpand();
                name(this.size, metadata.name(i));
                value(this.size, metadata.value(i));
                this.size++;
            }
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Metadata(");
        for (int i = 0; i < this.size; i++) {
            if (i != 0) {
                sb.append(',');
            }
            String str = new String(name(i), Charsets.US_ASCII);
            sb.append(str);
            sb.append('=');
            if (str.endsWith(BINARY_HEADER_SUFFIX)) {
                sb.append(BASE64_ENCODING_OMIT_PADDING.encode(value(i)));
            } else {
                sb.append(new String(value(i), Charsets.US_ASCII));
            }
        }
        sb.append(')');
        return sb.toString();
    }

    /* access modifiers changed from: private */
    public boolean bytesEqual(byte[] bArr, byte[] bArr2) {
        return Arrays.equals(bArr, bArr2);
    }
}
