package org.spongycastle.pqc.crypto.ntru;

import org.spongycastle.crypto.Digest;
import org.spongycastle.util.Arrays;

public class IndexGenerator {

    /* renamed from: N */
    private int f1490N;
    private BitString buf;

    /* renamed from: c */
    private int f1491c;
    private int counter = 0;
    private int hLen;
    private Digest hashAlg;
    private boolean initialized;
    private int minCallsR;
    private int remLen = 0;
    private byte[] seed;
    private int totLen = 0;

    public static class BitString {
        byte[] bytes = new byte[4];
        int lastByteBits;
        int numBytes;

        /* access modifiers changed from: 0000 */
        public void appendBits(byte[] bArr) {
            for (int i = 0; i != bArr.length; i++) {
                appendBits(bArr[i]);
            }
        }

        public void appendBits(byte b) {
            int i = this.numBytes;
            byte[] bArr = this.bytes;
            if (i == bArr.length) {
                this.bytes = IndexGenerator.copyOf(bArr, bArr.length * 2);
            }
            int i2 = this.numBytes;
            if (i2 == 0) {
                this.numBytes = 1;
                this.bytes[0] = b;
                this.lastByteBits = 8;
                return;
            }
            int i3 = this.lastByteBits;
            if (i3 == 8) {
                byte[] bArr2 = this.bytes;
                this.numBytes = i2 + 1;
                bArr2[i2] = b;
                return;
            }
            int i4 = 8 - i3;
            byte[] bArr3 = this.bytes;
            int i5 = i2 - 1;
            byte b2 = b & 255;
            bArr3[i5] = (byte) ((b2 << i3) | bArr3[i5]);
            this.numBytes = i2 + 1;
            bArr3[i2] = (byte) (b2 >> i4);
        }

        public BitString getTrailing(int i) {
            int i2;
            BitString bitString = new BitString();
            bitString.numBytes = (i + 7) / 8;
            bitString.bytes = new byte[bitString.numBytes];
            int i3 = 0;
            while (true) {
                i2 = bitString.numBytes;
                if (i3 >= i2) {
                    break;
                }
                bitString.bytes[i3] = this.bytes[i3];
                i3++;
            }
            bitString.lastByteBits = i % 8;
            int i4 = bitString.lastByteBits;
            if (i4 == 0) {
                bitString.lastByteBits = 8;
            } else {
                int i5 = 32 - i4;
                byte[] bArr = bitString.bytes;
                bArr[i2 - 1] = (byte) ((bArr[i2 - 1] << i5) >>> i5);
            }
            return bitString;
        }

        public int getLeadingAsInt(int i) {
            int i2 = (((this.numBytes - 1) * 8) + this.lastByteBits) - i;
            int i3 = i2 / 8;
            int i4 = i2 % 8;
            int i5 = (this.bytes[i3] & 255) >>> i4;
            int i6 = 8 - i4;
            while (true) {
                i3++;
                if (i3 >= this.numBytes) {
                    return i5;
                }
                i5 |= (this.bytes[i3] & 255) << i6;
                i6 += 8;
            }
        }

        public byte[] getBytes() {
            return Arrays.clone(this.bytes);
        }
    }

    IndexGenerator(byte[] bArr, NTRUEncryptionParameters nTRUEncryptionParameters) {
        this.seed = bArr;
        this.f1490N = nTRUEncryptionParameters.f1499N;
        this.f1491c = nTRUEncryptionParameters.f1500c;
        this.minCallsR = nTRUEncryptionParameters.minCallsR;
        this.hashAlg = nTRUEncryptionParameters.hashAlg;
        this.hLen = this.hashAlg.getDigestSize();
        this.initialized = false;
    }

    /* access modifiers changed from: 0000 */
    public int nextIndex() {
        int leadingAsInt;
        int i;
        int i2;
        int i3;
        int i4;
        if (!this.initialized) {
            this.buf = new BitString();
            byte[] bArr = new byte[this.hashAlg.getDigestSize()];
            while (true) {
                int i5 = this.counter;
                i4 = this.minCallsR;
                if (i5 >= i4) {
                    break;
                }
                appendHash(this.buf, bArr);
                this.counter++;
            }
            this.totLen = i4 * 8 * this.hLen;
            this.remLen = this.totLen;
            this.initialized = true;
        }
        do {
            this.totLen += this.f1491c;
            BitString trailing = this.buf.getTrailing(this.remLen);
            int i6 = this.remLen;
            int i7 = this.f1491c;
            if (i6 < i7) {
                int i8 = i7 - i6;
                int i9 = this.counter;
                int i10 = this.hLen;
                int i11 = i9 + (((i8 + i10) - 1) / i10);
                byte[] bArr2 = new byte[this.hashAlg.getDigestSize()];
                while (this.counter < i11) {
                    appendHash(trailing, bArr2);
                    this.counter++;
                    int i12 = this.hLen;
                    if (i8 > i12 * 8) {
                        i8 -= i12 * 8;
                    }
                }
                this.remLen = (this.hLen * 8) - i8;
                this.buf = new BitString();
                this.buf.appendBits(bArr2);
            } else {
                this.remLen = i6 - i7;
            }
            leadingAsInt = trailing.getLeadingAsInt(this.f1491c);
            int i13 = this.f1491c;
            i = 1 << i13;
            i2 = 1 << i13;
            i3 = this.f1490N;
        } while (leadingAsInt >= i - (i2 % i3));
        return leadingAsInt % i3;
    }

    private void appendHash(BitString bitString, byte[] bArr) {
        Digest digest = this.hashAlg;
        byte[] bArr2 = this.seed;
        digest.update(bArr2, 0, bArr2.length);
        putInt(this.hashAlg, this.counter);
        this.hashAlg.doFinal(bArr, 0);
        bitString.appendBits(bArr);
    }

    private void putInt(Digest digest, int i) {
        digest.update((byte) (i >> 24));
        digest.update((byte) (i >> 16));
        digest.update((byte) (i >> 8));
        digest.update((byte) i);
    }

    /* access modifiers changed from: private */
    public static byte[] copyOf(byte[] bArr, int i) {
        byte[] bArr2 = new byte[i];
        if (i >= bArr.length) {
            i = bArr.length;
        }
        System.arraycopy(bArr, 0, bArr2, 0, i);
        return bArr2;
    }
}
