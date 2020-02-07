package org.spongycastle.crypto.digests;

import androidx.recyclerview.widget.ItemTouchHelper.Callback;
import com.yakivmospan.scytale.Options;
import org.bitcoinj.net.discovery.TorDiscovery;
import org.bitcoinj.script.ScriptOpCodes;
import org.spongycastle.crypto.ExtendedDigest;
import org.spongycastle.crypto.tls.CipherSuite;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.Memoable;

public final class WhirlpoolDigest implements ExtendedDigest, Memoable {
    private static final int BITCOUNT_ARRAY_SIZE = 32;
    private static final int BYTE_LENGTH = 64;

    /* renamed from: C0 */
    private static final long[] f1072C0 = new long[256];

    /* renamed from: C1 */
    private static final long[] f1073C1 = new long[256];

    /* renamed from: C2 */
    private static final long[] f1074C2 = new long[256];

    /* renamed from: C3 */
    private static final long[] f1075C3 = new long[256];

    /* renamed from: C4 */
    private static final long[] f1076C4 = new long[256];

    /* renamed from: C5 */
    private static final long[] f1077C5 = new long[256];

    /* renamed from: C6 */
    private static final long[] f1078C6 = new long[256];

    /* renamed from: C7 */
    private static final long[] f1079C7 = new long[256];
    private static final int DIGEST_LENGTH_BYTES = 64;
    private static final short[] EIGHT = new short[32];
    private static final int REDUCTION_POLYNOMIAL = 285;
    private static final int ROUNDS = 10;
    private static final int[] SBOX = {24, 35, 198, 232, 135, 184, 1, 79, 54, 166, 210, Options.RSA_ECB_PKCS1PADDING_ENCRYPTION_BLOCK_SIZE_FOR_JELLY_BEAN, 121, 111, 145, 82, 96, 188, 155, 142, 163, 12, 123, 53, 29, 224, 215, CipherSuite.TLS_DH_RSA_WITH_CAMELLIA_256_CBC_SHA256, 46, 75, 254, 87, 21, 119, 55, 229, 159, TorDiscovery.RESOLVE_ERROR, 74, 218, 88, 201, 41, 10, 177, 160, 107, 133, CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_128_CBC_SHA256, 93, 16, 244, 203, 62, 5, 103, 228, 39, 65, 139, 167, 125, 149, 216, 251, 238, 124, 102, 221, 23, 71, 158, 202, 45, CipherSuite.TLS_DH_anon_WITH_CAMELLIA_128_CBC_SHA256, 7, 173, 90, ScriptOpCodes.OP_INVERT, 51, 99, 2, 170, 113, 200, 25, 73, 217, 242, 227, 91, 136, 154, 38, 50, 176, 233, 15, C239114.DENSITY_TV, 128, CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_128_CBC_SHA256, 205, 52, 72, 255, 122, 144, 95, 32, 104, 26, 174, 180, 84, 147, 34, 100, 241, 115, 18, 64, 8, CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_256_CBC_SHA256, 236, 219, 161, 141, 61, 151, 0, 207, 43, 118, ScriptOpCodes.OP_SIZE, 214, 27, 181, 175, 106, 80, 69, 243, 48, 239, 63, 85, 162, 234, 101, CipherSuite.TLS_RSA_WITH_CAMELLIA_128_CBC_SHA256, 47, 192, 222, 28, 253, 77, 146, 117, 6, 138, 178, 230, 14, 31, 98, 212, 168, 150, 249, CipherSuite.TLS_DH_anon_WITH_CAMELLIA_256_CBC_SHA256, 37, 89, 132, 114, 57, 76, 94, 120, 56, 140, 209, 165, 226, 97, 179, 33, 156, 30, 67, 199, 252, 4, 81, 153, 109, 13, Callback.DEFAULT_SWIPE_ANIMATION_DURATION, 223, 126, 36, 59, 171, 206, 17, 143, 78, 183, 235, 60, ScriptOpCodes.OP_RIGHT, 148, 247, 185, 19, 44, 211, 231, 110, CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA256, 3, 86, 68, 127, 169, 42, CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_128_CBC_SHA256, CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_256_CBC_SHA256, 83, 220, 11, 157, 108, 49, 116, 246, 70, 172, 137, 20, 225, 22, 58, 105, 9, 112, 182, 208, 237, 204, 66, 152, 164, 40, 92, 248, 134};

    /* renamed from: _K */
    private long[] f1080_K = new long[8];

    /* renamed from: _L */
    private long[] f1081_L = new long[8];
    private short[] _bitCount = new short[32];
    private long[] _block = new long[8];
    private byte[] _buffer = new byte[64];
    private int _bufferPos = 0;
    private long[] _hash = new long[8];
    private final long[] _rc = new long[11];
    private long[] _state = new long[8];

    private int maskWithReductionPolynomial(int i) {
        return ((long) i) >= 256 ? i ^ REDUCTION_POLYNOMIAL : i;
    }

    private long packIntoLong(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        return (((((((((long) i2) << 48) ^ (((long) i) << 56)) ^ (((long) i3) << 40)) ^ (((long) i4) << 32)) ^ (((long) i5) << 24)) ^ (((long) i6) << 16)) ^ (((long) i7) << 8)) ^ ((long) i8);
    }

    public String getAlgorithmName() {
        return "Whirlpool";
    }

    public int getByteLength() {
        return 64;
    }

    public int getDigestSize() {
        return 64;
    }

    static {
        EIGHT[31] = 8;
    }

    public WhirlpoolDigest() {
        for (int i = 0; i < 256; i++) {
            int i2 = SBOX[i];
            int maskWithReductionPolynomial = maskWithReductionPolynomial(i2 << 1);
            int maskWithReductionPolynomial2 = maskWithReductionPolynomial(maskWithReductionPolynomial << 1);
            int i3 = maskWithReductionPolynomial2 ^ i2;
            int maskWithReductionPolynomial3 = maskWithReductionPolynomial(maskWithReductionPolynomial2 << 1);
            int i4 = maskWithReductionPolynomial3 ^ i2;
            int i5 = i2;
            f1072C0[i] = packIntoLong(i2, i5, maskWithReductionPolynomial2, i2, maskWithReductionPolynomial3, i3, maskWithReductionPolynomial, i4);
            int i6 = i2;
            f1073C1[i] = packIntoLong(i4, i5, i6, maskWithReductionPolynomial2, i2, maskWithReductionPolynomial3, i3, maskWithReductionPolynomial);
            int i7 = i2;
            f1074C2[i] = packIntoLong(maskWithReductionPolynomial, i4, i6, i7, maskWithReductionPolynomial2, i2, maskWithReductionPolynomial3, i3);
            int i8 = i2;
            f1075C3[i] = packIntoLong(i3, maskWithReductionPolynomial, i4, i7, i8, maskWithReductionPolynomial2, i2, maskWithReductionPolynomial3);
            int i9 = i2;
            f1076C4[i] = packIntoLong(maskWithReductionPolynomial3, i3, maskWithReductionPolynomial, i4, i8, i9, maskWithReductionPolynomial2, i2);
            int i10 = i2;
            f1077C5[i] = packIntoLong(i2, maskWithReductionPolynomial3, i3, maskWithReductionPolynomial, i4, i9, i10, maskWithReductionPolynomial2);
            int i11 = i2;
            f1078C6[i] = packIntoLong(maskWithReductionPolynomial2, i2, maskWithReductionPolynomial3, i3, maskWithReductionPolynomial, i4, i10, i11);
            f1079C7[i] = packIntoLong(i2, maskWithReductionPolynomial2, i2, maskWithReductionPolynomial3, i3, maskWithReductionPolynomial, i4, i11);
        }
        this._rc[0] = 0;
        for (int i12 = 1; i12 <= 10; i12++) {
            int i13 = (i12 - 1) * 8;
            this._rc[i12] = (((((((f1072C0[i13] & -72057594037927936L) ^ (f1073C1[i13 + 1] & 71776119061217280L)) ^ (f1074C2[i13 + 2] & 280375465082880L)) ^ (f1075C3[i13 + 3] & 1095216660480L)) ^ (f1076C4[i13 + 4] & 4278190080L)) ^ (f1077C5[i13 + 5] & 16711680)) ^ (f1078C6[i13 + 6] & 65280)) ^ (f1079C7[i13 + 7] & 255);
        }
    }

    public WhirlpoolDigest(WhirlpoolDigest whirlpoolDigest) {
        reset(whirlpoolDigest);
    }

    public int doFinal(byte[] bArr, int i) {
        finish();
        for (int i2 = 0; i2 < 8; i2++) {
            convertLongToByteArray(this._hash[i2], bArr, (i2 * 8) + i);
        }
        reset();
        return getDigestSize();
    }

    public void reset() {
        this._bufferPos = 0;
        Arrays.fill(this._bitCount, 0);
        Arrays.fill(this._buffer, 0);
        Arrays.fill(this._hash, 0);
        Arrays.fill(this.f1080_K, 0);
        Arrays.fill(this.f1081_L, 0);
        Arrays.fill(this._block, 0);
        Arrays.fill(this._state, 0);
    }

    private void processFilledBuffer(byte[] bArr, int i) {
        for (int i2 = 0; i2 < this._state.length; i2++) {
            this._block[i2] = bytesToLongFromBuffer(this._buffer, i2 * 8);
        }
        processBlock();
        this._bufferPos = 0;
        Arrays.fill(this._buffer, 0);
    }

    private long bytesToLongFromBuffer(byte[] bArr, int i) {
        return (((long) bArr[i + 7]) & 255) | ((((long) bArr[i + 0]) & 255) << 56) | ((((long) bArr[i + 1]) & 255) << 48) | ((((long) bArr[i + 2]) & 255) << 40) | ((((long) bArr[i + 3]) & 255) << 32) | ((((long) bArr[i + 4]) & 255) << 24) | ((((long) bArr[i + 5]) & 255) << 16) | ((((long) bArr[i + 6]) & 255) << 8);
    }

    private void convertLongToByteArray(long j, byte[] bArr, int i) {
        for (int i2 = 0; i2 < 8; i2++) {
            bArr[i + i2] = (byte) ((int) ((j >> (56 - (i2 * 8))) & 255));
        }
    }

    /* access modifiers changed from: protected */
    public void processBlock() {
        for (int i = 0; i < 8; i++) {
            long[] jArr = this._state;
            long j = this._block[i];
            long[] jArr2 = this.f1080_K;
            long j2 = this._hash[i];
            jArr2[i] = j2;
            jArr[i] = j ^ j2;
        }
        int i2 = 1;
        while (i2 <= 10) {
            int i3 = 0;
            while (i3 < 8) {
                long[] jArr3 = this.f1081_L;
                jArr3[i3] = 0;
                long j3 = jArr3[i3];
                long[] jArr4 = f1072C0;
                long[] jArr5 = this.f1080_K;
                int i4 = i2;
                jArr3[i3] = jArr4[((int) (jArr5[(i3 + 0) & 7] >>> 56)) & 255] ^ j3;
                jArr3[i3] = jArr3[i3] ^ f1073C1[((int) (jArr5[(i3 - 1) & 7] >>> 48)) & 255];
                jArr3[i3] = jArr3[i3] ^ f1074C2[((int) (jArr5[(i3 - 2) & 7] >>> 40)) & 255];
                jArr3[i3] = jArr3[i3] ^ f1075C3[((int) (jArr5[(i3 - 3) & 7] >>> 32)) & 255];
                jArr3[i3] = jArr3[i3] ^ f1076C4[((int) (jArr5[(i3 - 4) & 7] >>> 24)) & 255];
                jArr3[i3] = jArr3[i3] ^ f1077C5[((int) (jArr5[(i3 - 5) & 7] >>> 16)) & 255];
                jArr3[i3] = jArr3[i3] ^ f1078C6[((int) (jArr5[(i3 - 6) & 7] >>> 8)) & 255];
                jArr3[i3] = jArr3[i3] ^ f1079C7[((int) jArr5[(i3 - 7) & 7]) & 255];
                i3++;
                i2 = i4;
            }
            int i5 = i2;
            long[] jArr6 = this.f1081_L;
            long[] jArr7 = this.f1080_K;
            System.arraycopy(jArr6, 0, jArr7, 0, jArr7.length);
            long[] jArr8 = this.f1080_K;
            jArr8[0] = jArr8[0] ^ this._rc[i5];
            for (int i6 = 0; i6 < 8; i6++) {
                long[] jArr9 = this.f1081_L;
                jArr9[i6] = this.f1080_K[i6];
                long j4 = jArr9[i6];
                long[] jArr10 = f1072C0;
                long[] jArr11 = this._state;
                jArr9[i6] = j4 ^ jArr10[((int) (jArr11[(i6 + 0) & 7] >>> 56)) & 255];
                jArr9[i6] = jArr9[i6] ^ f1073C1[((int) (jArr11[(i6 - 1) & 7] >>> 48)) & 255];
                jArr9[i6] = jArr9[i6] ^ f1074C2[((int) (jArr11[(i6 - 2) & 7] >>> 40)) & 255];
                jArr9[i6] = jArr9[i6] ^ f1075C3[((int) (jArr11[(i6 - 3) & 7] >>> 32)) & 255];
                jArr9[i6] = jArr9[i6] ^ f1076C4[((int) (jArr11[(i6 - 4) & 7] >>> 24)) & 255];
                jArr9[i6] = jArr9[i6] ^ f1077C5[((int) (jArr11[(i6 - 5) & 7] >>> 16)) & 255];
                jArr9[i6] = jArr9[i6] ^ f1078C6[((int) (jArr11[(i6 - 6) & 7] >>> 8)) & 255];
                jArr9[i6] = jArr9[i6] ^ f1079C7[((int) jArr11[(i6 - 7) & 7]) & 255];
            }
            long[] jArr12 = this.f1081_L;
            long[] jArr13 = this._state;
            System.arraycopy(jArr12, 0, jArr13, 0, jArr13.length);
            i2 = i5 + 1;
        }
        for (int i7 = 0; i7 < 8; i7++) {
            long[] jArr14 = this._hash;
            jArr14[i7] = jArr14[i7] ^ (this._state[i7] ^ this._block[i7]);
        }
    }

    public void update(byte b) {
        byte[] bArr = this._buffer;
        int i = this._bufferPos;
        bArr[i] = b;
        this._bufferPos = i + 1;
        if (this._bufferPos == bArr.length) {
            processFilledBuffer(bArr, 0);
        }
        increment();
    }

    private void increment() {
        int i = 0;
        for (int length = this._bitCount.length - 1; length >= 0; length--) {
            short[] sArr = this._bitCount;
            int i2 = (sArr[length] & 255) + EIGHT[length] + i;
            i = i2 >>> 8;
            sArr[length] = (short) (i2 & 255);
        }
    }

    public void update(byte[] bArr, int i, int i2) {
        while (i2 > 0) {
            update(bArr[i]);
            i++;
            i2--;
        }
    }

    private void finish() {
        byte[] copyBitLength = copyBitLength();
        byte[] bArr = this._buffer;
        int i = this._bufferPos;
        this._bufferPos = i + 1;
        bArr[i] = (byte) (bArr[i] | 128);
        if (this._bufferPos == bArr.length) {
            processFilledBuffer(bArr, 0);
        }
        if (this._bufferPos > 32) {
            while (this._bufferPos != 0) {
                update(0);
            }
        }
        while (this._bufferPos <= 32) {
            update(0);
        }
        System.arraycopy(copyBitLength, 0, this._buffer, 32, copyBitLength.length);
        processFilledBuffer(this._buffer, 0);
    }

    private byte[] copyBitLength() {
        byte[] bArr = new byte[32];
        for (int i = 0; i < bArr.length; i++) {
            bArr[i] = (byte) (this._bitCount[i] & 255);
        }
        return bArr;
    }

    public Memoable copy() {
        return new WhirlpoolDigest(this);
    }

    public void reset(Memoable memoable) {
        WhirlpoolDigest whirlpoolDigest = (WhirlpoolDigest) memoable;
        long[] jArr = whirlpoolDigest._rc;
        long[] jArr2 = this._rc;
        System.arraycopy(jArr, 0, jArr2, 0, jArr2.length);
        byte[] bArr = whirlpoolDigest._buffer;
        byte[] bArr2 = this._buffer;
        System.arraycopy(bArr, 0, bArr2, 0, bArr2.length);
        this._bufferPos = whirlpoolDigest._bufferPos;
        short[] sArr = whirlpoolDigest._bitCount;
        short[] sArr2 = this._bitCount;
        System.arraycopy(sArr, 0, sArr2, 0, sArr2.length);
        long[] jArr3 = whirlpoolDigest._hash;
        long[] jArr4 = this._hash;
        System.arraycopy(jArr3, 0, jArr4, 0, jArr4.length);
        long[] jArr5 = whirlpoolDigest.f1080_K;
        long[] jArr6 = this.f1080_K;
        System.arraycopy(jArr5, 0, jArr6, 0, jArr6.length);
        long[] jArr7 = whirlpoolDigest.f1081_L;
        long[] jArr8 = this.f1081_L;
        System.arraycopy(jArr7, 0, jArr8, 0, jArr8.length);
        long[] jArr9 = whirlpoolDigest._block;
        long[] jArr10 = this._block;
        System.arraycopy(jArr9, 0, jArr10, 0, jArr10.length);
        long[] jArr11 = whirlpoolDigest._state;
        long[] jArr12 = this._state;
        System.arraycopy(jArr11, 0, jArr12, 0, jArr12.length);
    }
}
