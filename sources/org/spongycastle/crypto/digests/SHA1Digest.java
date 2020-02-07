package org.spongycastle.crypto.digests;

import com.google.common.base.Ascii;
import org.spongycastle.util.Memoable;
import org.spongycastle.util.Pack;

public class SHA1Digest extends GeneralDigest implements EncodableDigest {
    private static final int DIGEST_LENGTH = 20;

    /* renamed from: Y1 */
    private static final int f1029Y1 = 1518500249;

    /* renamed from: Y2 */
    private static final int f1030Y2 = 1859775393;

    /* renamed from: Y3 */
    private static final int f1031Y3 = -1894007588;

    /* renamed from: Y4 */
    private static final int f1032Y4 = -899497514;

    /* renamed from: H1 */
    private int f1033H1;

    /* renamed from: H2 */
    private int f1034H2;

    /* renamed from: H3 */
    private int f1035H3;

    /* renamed from: H4 */
    private int f1036H4;

    /* renamed from: H5 */
    private int f1037H5;

    /* renamed from: X */
    private int[] f1038X = new int[80];
    private int xOff;

    /* renamed from: f */
    private int m390f(int i, int i2, int i3) {
        return ((~i) & i3) | (i2 & i);
    }

    /* renamed from: g */
    private int m391g(int i, int i2, int i3) {
        return (i & i3) | (i & i2) | (i2 & i3);
    }

    /* renamed from: h */
    private int m392h(int i, int i2, int i3) {
        return (i ^ i2) ^ i3;
    }

    public String getAlgorithmName() {
        return "SHA-1";
    }

    public int getDigestSize() {
        return 20;
    }

    public SHA1Digest() {
        reset();
    }

    public SHA1Digest(SHA1Digest sHA1Digest) {
        super((GeneralDigest) sHA1Digest);
        copyIn(sHA1Digest);
    }

    public SHA1Digest(byte[] bArr) {
        super(bArr);
        this.f1033H1 = Pack.bigEndianToInt(bArr, 16);
        this.f1034H2 = Pack.bigEndianToInt(bArr, 20);
        this.f1035H3 = Pack.bigEndianToInt(bArr, 24);
        this.f1036H4 = Pack.bigEndianToInt(bArr, 28);
        this.f1037H5 = Pack.bigEndianToInt(bArr, 32);
        this.xOff = Pack.bigEndianToInt(bArr, 36);
        for (int i = 0; i != this.xOff; i++) {
            this.f1038X[i] = Pack.bigEndianToInt(bArr, (i * 4) + 40);
        }
    }

    private void copyIn(SHA1Digest sHA1Digest) {
        this.f1033H1 = sHA1Digest.f1033H1;
        this.f1034H2 = sHA1Digest.f1034H2;
        this.f1035H3 = sHA1Digest.f1035H3;
        this.f1036H4 = sHA1Digest.f1036H4;
        this.f1037H5 = sHA1Digest.f1037H5;
        int[] iArr = sHA1Digest.f1038X;
        System.arraycopy(iArr, 0, this.f1038X, 0, iArr.length);
        this.xOff = sHA1Digest.xOff;
    }

    /* access modifiers changed from: protected */
    public void processWord(byte[] bArr, int i) {
        int i2 = i + 1;
        int i3 = i2 + 1;
        int i4 = (bArr[i3 + 1] & 255) | (bArr[i] << Ascii.CAN) | ((bArr[i2] & 255) << 16) | ((bArr[i3] & 255) << 8);
        int[] iArr = this.f1038X;
        int i5 = this.xOff;
        iArr[i5] = i4;
        int i6 = i5 + 1;
        this.xOff = i6;
        if (i6 == 16) {
            processBlock();
        }
    }

    /* access modifiers changed from: protected */
    public void processLength(long j) {
        if (this.xOff > 14) {
            processBlock();
        }
        int[] iArr = this.f1038X;
        iArr[14] = (int) (j >>> 32);
        iArr[15] = (int) (j & -1);
    }

    public int doFinal(byte[] bArr, int i) {
        finish();
        Pack.intToBigEndian(this.f1033H1, bArr, i);
        Pack.intToBigEndian(this.f1034H2, bArr, i + 4);
        Pack.intToBigEndian(this.f1035H3, bArr, i + 8);
        Pack.intToBigEndian(this.f1036H4, bArr, i + 12);
        Pack.intToBigEndian(this.f1037H5, bArr, i + 16);
        reset();
        return 20;
    }

    public void reset() {
        super.reset();
        this.f1033H1 = 1732584193;
        this.f1034H2 = -271733879;
        this.f1035H3 = -1732584194;
        this.f1036H4 = 271733878;
        this.f1037H5 = -1009589776;
        this.xOff = 0;
        int i = 0;
        while (true) {
            int[] iArr = this.f1038X;
            if (i != iArr.length) {
                iArr[i] = 0;
                i++;
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void processBlock() {
        for (int i = 16; i < 80; i++) {
            int[] iArr = this.f1038X;
            int i2 = ((iArr[i - 3] ^ iArr[i - 8]) ^ iArr[i - 14]) ^ iArr[i - 16];
            iArr[i] = (i2 >>> 31) | (i2 << 1);
        }
        int i3 = this.f1033H1;
        int i4 = this.f1034H2;
        int i5 = this.f1035H3;
        int i6 = this.f1036H4;
        int i7 = this.f1037H5;
        int i8 = i6;
        int i9 = 0;
        int i10 = i5;
        int i11 = i4;
        int i12 = i3;
        int i13 = 0;
        while (i13 < 4) {
            int i14 = i9 + 1;
            int f = i7 + ((i12 << 5) | (i12 >>> 27)) + m390f(i11, i10, i8) + this.f1038X[i9] + f1029Y1;
            int i15 = (i11 >>> 2) | (i11 << 30);
            int i16 = i14 + 1;
            int f2 = i8 + ((f << 5) | (f >>> 27)) + m390f(i12, i15, i10) + this.f1038X[i14] + f1029Y1;
            int i17 = (i12 >>> 2) | (i12 << 30);
            int i18 = i16 + 1;
            int f3 = i10 + ((f2 << 5) | (f2 >>> 27)) + m390f(f, i17, i15) + this.f1038X[i16] + f1029Y1;
            i7 = (f >>> 2) | (f << 30);
            int i19 = i18 + 1;
            i11 = i15 + ((f3 << 5) | (f3 >>> 27)) + m390f(f2, i7, i17) + this.f1038X[i18] + f1029Y1;
            i8 = (f2 >>> 2) | (f2 << 30);
            i12 = i17 + ((i11 << 5) | (i11 >>> 27)) + m390f(f3, i8, i7) + this.f1038X[i19] + f1029Y1;
            i10 = (f3 >>> 2) | (f3 << 30);
            i13++;
            i9 = i19 + 1;
        }
        int i20 = 0;
        while (i20 < 4) {
            int i21 = i9 + 1;
            int h = i7 + ((i12 << 5) | (i12 >>> 27)) + m392h(i11, i10, i8) + this.f1038X[i9] + f1030Y2;
            int i22 = (i11 >>> 2) | (i11 << 30);
            int i23 = i21 + 1;
            int h2 = i8 + ((h << 5) | (h >>> 27)) + m392h(i12, i22, i10) + this.f1038X[i21] + f1030Y2;
            int i24 = (i12 >>> 2) | (i12 << 30);
            int i25 = i23 + 1;
            int h3 = i10 + ((h2 << 5) | (h2 >>> 27)) + m392h(h, i24, i22) + this.f1038X[i23] + f1030Y2;
            i7 = (h >>> 2) | (h << 30);
            int i26 = i25 + 1;
            i11 = i22 + ((h3 << 5) | (h3 >>> 27)) + m392h(h2, i7, i24) + this.f1038X[i25] + f1030Y2;
            i8 = (h2 >>> 2) | (h2 << 30);
            i12 = i24 + ((i11 << 5) | (i11 >>> 27)) + m392h(h3, i8, i7) + this.f1038X[i26] + f1030Y2;
            i10 = (h3 >>> 2) | (h3 << 30);
            i20++;
            i9 = i26 + 1;
        }
        int i27 = 0;
        while (i27 < 4) {
            int i28 = i9 + 1;
            int g = i7 + ((i12 << 5) | (i12 >>> 27)) + m391g(i11, i10, i8) + this.f1038X[i9] + f1031Y3;
            int i29 = (i11 >>> 2) | (i11 << 30);
            int i30 = i28 + 1;
            int g2 = i8 + ((g << 5) | (g >>> 27)) + m391g(i12, i29, i10) + this.f1038X[i28] + f1031Y3;
            int i31 = (i12 >>> 2) | (i12 << 30);
            int i32 = i30 + 1;
            int g3 = i10 + ((g2 << 5) | (g2 >>> 27)) + m391g(g, i31, i29) + this.f1038X[i30] + f1031Y3;
            i7 = (g >>> 2) | (g << 30);
            int i33 = i32 + 1;
            i11 = i29 + ((g3 << 5) | (g3 >>> 27)) + m391g(g2, i7, i31) + this.f1038X[i32] + f1031Y3;
            i8 = (g2 >>> 2) | (g2 << 30);
            i12 = i31 + ((i11 << 5) | (i11 >>> 27)) + m391g(g3, i8, i7) + this.f1038X[i33] + f1031Y3;
            i10 = (g3 >>> 2) | (g3 << 30);
            i27++;
            i9 = i33 + 1;
        }
        int i34 = 0;
        while (i34 <= 3) {
            int i35 = i9 + 1;
            int h4 = i7 + ((i12 << 5) | (i12 >>> 27)) + m392h(i11, i10, i8) + this.f1038X[i9] + f1032Y4;
            int i36 = (i11 >>> 2) | (i11 << 30);
            int i37 = i35 + 1;
            int h5 = i8 + ((h4 << 5) | (h4 >>> 27)) + m392h(i12, i36, i10) + this.f1038X[i35] + f1032Y4;
            int i38 = (i12 >>> 2) | (i12 << 30);
            int i39 = i37 + 1;
            int h6 = i10 + ((h5 << 5) | (h5 >>> 27)) + m392h(h4, i38, i36) + this.f1038X[i37] + f1032Y4;
            i7 = (h4 >>> 2) | (h4 << 30);
            int i40 = i39 + 1;
            i11 = i36 + ((h6 << 5) | (h6 >>> 27)) + m392h(h5, i7, i38) + this.f1038X[i39] + f1032Y4;
            i8 = (h5 >>> 2) | (h5 << 30);
            i12 = i38 + ((i11 << 5) | (i11 >>> 27)) + m392h(h6, i8, i7) + this.f1038X[i40] + f1032Y4;
            i10 = (h6 >>> 2) | (h6 << 30);
            i34++;
            i9 = i40 + 1;
        }
        this.f1033H1 += i12;
        this.f1034H2 += i11;
        this.f1035H3 += i10;
        this.f1036H4 += i8;
        this.f1037H5 += i7;
        this.xOff = 0;
        for (int i41 = 0; i41 < 16; i41++) {
            this.f1038X[i41] = 0;
        }
    }

    public Memoable copy() {
        return new SHA1Digest(this);
    }

    public void reset(Memoable memoable) {
        SHA1Digest sHA1Digest = (SHA1Digest) memoable;
        super.copyIn(sHA1Digest);
        copyIn(sHA1Digest);
    }

    public byte[] getEncodedState() {
        byte[] bArr = new byte[((this.xOff * 4) + 40)];
        super.populateState(bArr);
        Pack.intToBigEndian(this.f1033H1, bArr, 16);
        Pack.intToBigEndian(this.f1034H2, bArr, 20);
        Pack.intToBigEndian(this.f1035H3, bArr, 24);
        Pack.intToBigEndian(this.f1036H4, bArr, 28);
        Pack.intToBigEndian(this.f1037H5, bArr, 32);
        Pack.intToBigEndian(this.xOff, bArr, 36);
        for (int i = 0; i != this.xOff; i++) {
            Pack.intToBigEndian(this.f1038X[i], bArr, (i * 4) + 40);
        }
        return bArr;
    }
}
