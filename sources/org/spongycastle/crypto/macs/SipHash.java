package org.spongycastle.crypto.macs;

import org.apache.commons.cli.HelpFormatter;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.Mac;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.util.Pack;

public class SipHash implements Mac {

    /* renamed from: c */
    protected final int f1220c;

    /* renamed from: d */
    protected final int f1221d;

    /* renamed from: k0 */
    protected long f1222k0;

    /* renamed from: k1 */
    protected long f1223k1;

    /* renamed from: m */
    protected long f1224m;

    /* renamed from: v0 */
    protected long f1225v0;

    /* renamed from: v1 */
    protected long f1226v1;

    /* renamed from: v2 */
    protected long f1227v2;

    /* renamed from: v3 */
    protected long f1228v3;
    protected int wordCount;
    protected int wordPos;

    protected static long rotateLeft(long j, int i) {
        return (j >>> (-i)) | (j << i);
    }

    public int getMacSize() {
        return 8;
    }

    public SipHash() {
        this.f1224m = 0;
        this.wordPos = 0;
        this.wordCount = 0;
        this.f1220c = 2;
        this.f1221d = 4;
    }

    public SipHash(int i, int i2) {
        this.f1224m = 0;
        this.wordPos = 0;
        this.wordCount = 0;
        this.f1220c = i;
        this.f1221d = i2;
    }

    public String getAlgorithmName() {
        StringBuilder sb = new StringBuilder();
        sb.append("SipHash-");
        sb.append(this.f1220c);
        sb.append(HelpFormatter.DEFAULT_OPT_PREFIX);
        sb.append(this.f1221d);
        return sb.toString();
    }

    public void init(CipherParameters cipherParameters) throws IllegalArgumentException {
        if (cipherParameters instanceof KeyParameter) {
            byte[] key = ((KeyParameter) cipherParameters).getKey();
            if (key.length == 16) {
                this.f1222k0 = Pack.littleEndianToLong(key, 0);
                this.f1223k1 = Pack.littleEndianToLong(key, 8);
                reset();
                return;
            }
            throw new IllegalArgumentException("'params' must be a 128-bit key");
        }
        throw new IllegalArgumentException("'params' must be an instance of KeyParameter");
    }

    public void update(byte b) throws IllegalStateException {
        this.f1224m >>>= 8;
        this.f1224m |= (((long) b) & 255) << 56;
        int i = this.wordPos + 1;
        this.wordPos = i;
        if (i == 8) {
            processMessageWord();
            this.wordPos = 0;
        }
    }

    public void update(byte[] bArr, int i, int i2) throws DataLengthException, IllegalStateException {
        byte[] bArr2 = bArr;
        int i3 = i2;
        int i4 = i3 & -8;
        int i5 = this.wordPos;
        int i6 = 0;
        if (i5 == 0) {
            while (i6 < i4) {
                this.f1224m = Pack.littleEndianToLong(bArr2, i + i6);
                processMessageWord();
                i6 += 8;
            }
            while (i6 < i3) {
                this.f1224m >>>= 8;
                this.f1224m |= (((long) bArr2[i + i6]) & 255) << 56;
                i6++;
            }
            this.wordPos = i3 - i4;
            return;
        }
        int i7 = i5 << 3;
        int i8 = 0;
        while (i8 < i4) {
            long littleEndianToLong = Pack.littleEndianToLong(bArr2, i + i8);
            this.f1224m = (this.f1224m >>> (-i7)) | (littleEndianToLong << i7);
            processMessageWord();
            this.f1224m = littleEndianToLong;
            i8 += 8;
        }
        while (i8 < i3) {
            this.f1224m >>>= 8;
            this.f1224m |= (((long) bArr2[i + i8]) & 255) << 56;
            int i9 = this.wordPos + 1;
            this.wordPos = i9;
            if (i9 == 8) {
                processMessageWord();
                this.wordPos = 0;
            }
            i8++;
        }
    }

    public long doFinal() throws DataLengthException, IllegalStateException {
        long j = this.f1224m;
        int i = this.wordPos;
        this.f1224m = j >>> ((7 - i) << 3);
        this.f1224m >>>= 8;
        this.f1224m |= (((long) ((this.wordCount << 3) + i)) & 255) << 56;
        processMessageWord();
        this.f1227v2 ^= 255;
        applySipRounds(this.f1221d);
        long j2 = ((this.f1225v0 ^ this.f1226v1) ^ this.f1227v2) ^ this.f1228v3;
        reset();
        return j2;
    }

    public int doFinal(byte[] bArr, int i) throws DataLengthException, IllegalStateException {
        Pack.longToLittleEndian(doFinal(), bArr, i);
        return 8;
    }

    public void reset() {
        long j = this.f1222k0;
        this.f1225v0 = 8317987319222330741L ^ j;
        long j2 = this.f1223k1;
        this.f1226v1 = 7237128888997146477L ^ j2;
        this.f1227v2 = j ^ 7816392313619706465L;
        this.f1228v3 = 8387220255154660723L ^ j2;
        this.f1224m = 0;
        this.wordPos = 0;
        this.wordCount = 0;
    }

    /* access modifiers changed from: protected */
    public void processMessageWord() {
        this.wordCount++;
        this.f1228v3 ^= this.f1224m;
        applySipRounds(this.f1220c);
        this.f1225v0 ^= this.f1224m;
    }

    /* access modifiers changed from: protected */
    public void applySipRounds(int i) {
        long j = this.f1225v0;
        long j2 = this.f1226v1;
        long j3 = this.f1227v2;
        long j4 = this.f1228v3;
        for (int i2 = 0; i2 < i; i2++) {
            long j5 = j + j2;
            long j6 = j3 + j4;
            long rotateLeft = rotateLeft(j2, 13) ^ j5;
            long rotateLeft2 = rotateLeft(j4, 16) ^ j6;
            long j7 = j6 + rotateLeft;
            j = rotateLeft(j5, 32) + rotateLeft2;
            j2 = rotateLeft(rotateLeft, 17) ^ j7;
            j4 = rotateLeft(rotateLeft2, 21) ^ j;
            j3 = rotateLeft(j7, 32);
        }
        this.f1225v0 = j;
        this.f1226v1 = j2;
        this.f1227v2 = j3;
        this.f1228v3 = j4;
    }
}
