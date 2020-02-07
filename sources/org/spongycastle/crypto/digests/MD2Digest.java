package org.spongycastle.crypto.digests;

import com.google.common.base.Ascii;
import com.google.common.primitives.SignedBytes;
import org.spongycastle.crypto.ExtendedDigest;
import org.spongycastle.crypto.signers.PSSSigner;
import org.spongycastle.util.Memoable;

public class MD2Digest implements ExtendedDigest, Memoable {
    private static final int DIGEST_LENGTH = 16;

    /* renamed from: S */
    private static final byte[] f984S = {41, 46, 67, -55, -94, -40, 124, 1, 61, 54, 84, -95, -20, -16, 6, 19, 98, -89, 5, -13, -64, -57, 115, -116, -104, -109, 43, -39, PSSSigner.TRAILER_IMPLICIT, 76, -126, -54, Ascii.f527RS, -101, 87, 60, -3, -44, -32, Ascii.SYN, 103, 66, 111, Ascii.CAN, -118, Ascii.ETB, -27, Ascii.DC2, -66, 78, -60, -42, -38, -98, -34, 73, -96, -5, -11, -114, -69, 47, -18, 122, -87, 104, 121, -111, Ascii.NAK, -78, 7, 63, -108, -62, 16, -119, Ascii.f532VT, 34, Framer.STDIN_REQUEST_FRAME_PREFIX, Framer.ENTER_FRAME_PREFIX, Byte.MIN_VALUE, Byte.MAX_VALUE, 93, -102, 90, -112, Framer.STDERR_FRAME_PREFIX, 39, 53, 62, -52, -25, -65, -9, -105, 3, -1, Ascii.f520EM, 48, -77, 72, -91, -75, -47, -41, 94, -110, 42, -84, 86, -86, -58, 79, -72, 56, -46, -106, -92, 125, -74, 118, -4, 107, -30, -100, 116, 4, -15, 69, -99, 112, 89, 100, 113, -121, 32, -122, 91, -49, 101, -26, Framer.STDIN_FRAME_PREFIX, -88, 2, Ascii.ESC, 96, 37, -83, -82, -80, -71, -10, Ascii.f522FS, 70, 97, 105, 52, SignedBytes.MAX_POWER_OF_TWO, 126, Ascii.f528SI, 85, 71, -93, 35, -35, 81, -81, 58, -61, 92, -7, -50, -70, -59, -22, 38, 44, 83, Ascii.f519CR, 110, -123, 40, -124, 9, -45, -33, -51, -12, 65, -127, 77, 82, 106, -36, 55, -56, 108, -63, -85, -6, 36, -31, 123, 8, Ascii.f521FF, -67, -79, 74, Framer.EXIT_FRAME_PREFIX, -120, -107, -117, -29, 99, -24, 109, -23, -53, -43, -2, 59, 0, Ascii.f523GS, 57, -14, -17, -73, Ascii.f529SO, 102, 88, -48, -28, -90, 119, 114, -8, -21, 117, 75, 10, Framer.STDOUT_FRAME_PREFIX, 68, 80, -76, -113, -19, Ascii.f531US, Ascii.SUB, -37, -103, -115, 51, -97, 17, -125, Ascii.DC4};

    /* renamed from: C */
    private byte[] f985C = new byte[16];
    private int COff;

    /* renamed from: M */
    private byte[] f986M = new byte[16];

    /* renamed from: X */
    private byte[] f987X = new byte[48];
    private int mOff;
    private int xOff;

    public String getAlgorithmName() {
        return "MD2";
    }

    public int getByteLength() {
        return 16;
    }

    public int getDigestSize() {
        return 16;
    }

    public MD2Digest() {
        reset();
    }

    public MD2Digest(MD2Digest mD2Digest) {
        copyIn(mD2Digest);
    }

    private void copyIn(MD2Digest mD2Digest) {
        byte[] bArr = mD2Digest.f987X;
        System.arraycopy(bArr, 0, this.f987X, 0, bArr.length);
        this.xOff = mD2Digest.xOff;
        byte[] bArr2 = mD2Digest.f986M;
        System.arraycopy(bArr2, 0, this.f986M, 0, bArr2.length);
        this.mOff = mD2Digest.mOff;
        byte[] bArr3 = mD2Digest.f985C;
        System.arraycopy(bArr3, 0, this.f985C, 0, bArr3.length);
        this.COff = mD2Digest.COff;
    }

    public int doFinal(byte[] bArr, int i) {
        int length = this.f986M.length;
        int i2 = this.mOff;
        byte b = (byte) (length - i2);
        while (true) {
            byte[] bArr2 = this.f986M;
            if (i2 < bArr2.length) {
                bArr2[i2] = b;
                i2++;
            } else {
                processCheckSum(bArr2);
                processBlock(this.f986M);
                processBlock(this.f985C);
                System.arraycopy(this.f987X, this.xOff, bArr, i, 16);
                reset();
                return 16;
            }
        }
    }

    public void reset() {
        this.xOff = 0;
        int i = 0;
        while (true) {
            byte[] bArr = this.f987X;
            if (i == bArr.length) {
                break;
            }
            bArr[i] = 0;
            i++;
        }
        this.mOff = 0;
        int i2 = 0;
        while (true) {
            byte[] bArr2 = this.f986M;
            if (i2 == bArr2.length) {
                break;
            }
            bArr2[i2] = 0;
            i2++;
        }
        this.COff = 0;
        int i3 = 0;
        while (true) {
            byte[] bArr3 = this.f985C;
            if (i3 != bArr3.length) {
                bArr3[i3] = 0;
                i3++;
            } else {
                return;
            }
        }
    }

    public void update(byte b) {
        byte[] bArr = this.f986M;
        int i = this.mOff;
        this.mOff = i + 1;
        bArr[i] = b;
        if (this.mOff == 16) {
            processCheckSum(bArr);
            processBlock(this.f986M);
            this.mOff = 0;
        }
    }

    public void update(byte[] bArr, int i, int i2) {
        while (this.mOff != 0 && i2 > 0) {
            update(bArr[i]);
            i++;
            i2--;
        }
        while (i2 > 16) {
            System.arraycopy(bArr, i, this.f986M, 0, 16);
            processCheckSum(this.f986M);
            processBlock(this.f986M);
            i2 -= 16;
            i += 16;
        }
        while (i2 > 0) {
            update(bArr[i]);
            i++;
            i2--;
        }
    }

    /* access modifiers changed from: protected */
    public void processCheckSum(byte[] bArr) {
        byte b = this.f985C[15];
        for (int i = 0; i < 16; i++) {
            byte[] bArr2 = this.f985C;
            bArr2[i] = (byte) (f984S[(b ^ bArr[i]) & 255] ^ bArr2[i]);
            b = bArr2[i];
        }
    }

    /* access modifiers changed from: protected */
    public void processBlock(byte[] bArr) {
        for (int i = 0; i < 16; i++) {
            byte[] bArr2 = this.f987X;
            bArr2[i + 16] = bArr[i];
            bArr2[i + 32] = (byte) (bArr[i] ^ bArr2[i]);
        }
        int i2 = 0;
        for (int i3 = 0; i3 < 18; i3++) {
            int i4 = i2;
            for (int i5 = 0; i5 < 48; i5++) {
                byte[] bArr3 = this.f987X;
                byte b = (byte) (f984S[i4] ^ bArr3[i5]);
                bArr3[i5] = b;
                i4 = b & 255;
            }
            i2 = (i4 + i3) % 256;
        }
    }

    public Memoable copy() {
        return new MD2Digest(this);
    }

    public void reset(Memoable memoable) {
        copyIn((MD2Digest) memoable);
    }
}
