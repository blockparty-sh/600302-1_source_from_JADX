package com.google.common.hash;

import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.Immutable;
import java.io.Serializable;
import java.nio.ByteBuffer;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@Immutable
final class SipHashFunction extends AbstractHashFunction implements Serializable {
    static final HashFunction SIP_HASH_24;
    private static final long serialVersionUID = 0;

    /* renamed from: c */
    private final int f571c;

    /* renamed from: d */
    private final int f572d;

    /* renamed from: k0 */
    private final long f573k0;

    /* renamed from: k1 */
    private final long f574k1;

    private static final class SipHasher extends AbstractStreamingHasher {
        private static final int CHUNK_SIZE = 8;

        /* renamed from: b */
        private long f575b = 0;

        /* renamed from: c */
        private final int f576c;

        /* renamed from: d */
        private final int f577d;
        private long finalM = 0;

        /* renamed from: v0 */
        private long f578v0 = 8317987319222330741L;

        /* renamed from: v1 */
        private long f579v1 = 7237128888997146477L;

        /* renamed from: v2 */
        private long f580v2 = 7816392313619706465L;

        /* renamed from: v3 */
        private long f581v3 = 8387220255154660723L;

        SipHasher(int i, int i2, long j, long j2) {
            super(8);
            this.f576c = i;
            this.f577d = i2;
            this.f578v0 ^= j;
            this.f579v1 ^= j2;
            this.f580v2 ^= j;
            this.f581v3 ^= j2;
        }

        /* access modifiers changed from: protected */
        public void process(ByteBuffer byteBuffer) {
            this.f575b += 8;
            processM(byteBuffer.getLong());
        }

        /* access modifiers changed from: protected */
        public void processRemaining(ByteBuffer byteBuffer) {
            this.f575b += (long) byteBuffer.remaining();
            int i = 0;
            while (byteBuffer.hasRemaining()) {
                this.finalM ^= (((long) byteBuffer.get()) & 255) << i;
                i += 8;
            }
        }

        public HashCode makeHash() {
            this.finalM ^= this.f575b << 56;
            processM(this.finalM);
            this.f580v2 ^= 255;
            sipRound(this.f577d);
            return HashCode.fromLong(((this.f578v0 ^ this.f579v1) ^ this.f580v2) ^ this.f581v3);
        }

        private void processM(long j) {
            this.f581v3 ^= j;
            sipRound(this.f576c);
            this.f578v0 = j ^ this.f578v0;
        }

        private void sipRound(int i) {
            for (int i2 = 0; i2 < i; i2++) {
                long j = this.f578v0;
                long j2 = this.f579v1;
                this.f578v0 = j + j2;
                this.f580v2 += this.f581v3;
                this.f579v1 = Long.rotateLeft(j2, 13);
                this.f581v3 = Long.rotateLeft(this.f581v3, 16);
                long j3 = this.f579v1;
                long j4 = this.f578v0;
                this.f579v1 = j3 ^ j4;
                this.f581v3 ^= this.f580v2;
                this.f578v0 = Long.rotateLeft(j4, 32);
                long j5 = this.f580v2;
                long j6 = this.f579v1;
                this.f580v2 = j5 + j6;
                this.f578v0 += this.f581v3;
                this.f579v1 = Long.rotateLeft(j6, 17);
                this.f581v3 = Long.rotateLeft(this.f581v3, 21);
                long j7 = this.f579v1;
                long j8 = this.f580v2;
                this.f579v1 = j7 ^ j8;
                this.f581v3 ^= this.f578v0;
                this.f580v2 = Long.rotateLeft(j8, 32);
            }
        }
    }

    public int bits() {
        return 64;
    }

    static {
        SipHashFunction sipHashFunction = new SipHashFunction(2, 4, 506097522914230528L, 1084818905618843912L);
        SIP_HASH_24 = sipHashFunction;
    }

    SipHashFunction(int i, int i2, long j, long j2) {
        boolean z = true;
        Preconditions.checkArgument(i > 0, "The number of SipRound iterations (c=%s) during Compression must be positive.", i);
        if (i2 <= 0) {
            z = false;
        }
        Preconditions.checkArgument(z, "The number of SipRound iterations (d=%s) during Finalization must be positive.", i2);
        this.f571c = i;
        this.f572d = i2;
        this.f573k0 = j;
        this.f574k1 = j2;
    }

    public Hasher newHasher() {
        SipHasher sipHasher = new SipHasher(this.f571c, this.f572d, this.f573k0, this.f574k1);
        return sipHasher;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Hashing.sipHash");
        sb.append(this.f571c);
        sb.append("");
        sb.append(this.f572d);
        sb.append("(");
        sb.append(this.f573k0);
        sb.append(", ");
        sb.append(this.f574k1);
        sb.append(")");
        return sb.toString();
    }

    public boolean equals(@NullableDecl Object obj) {
        if (!(obj instanceof SipHashFunction)) {
            return false;
        }
        SipHashFunction sipHashFunction = (SipHashFunction) obj;
        if (this.f571c == sipHashFunction.f571c && this.f572d == sipHashFunction.f572d && this.f573k0 == sipHashFunction.f573k0 && this.f574k1 == sipHashFunction.f574k1) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (int) ((((long) ((getClass().hashCode() ^ this.f571c) ^ this.f572d)) ^ this.f573k0) ^ this.f574k1);
    }
}
