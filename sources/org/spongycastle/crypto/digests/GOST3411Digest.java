package org.spongycastle.crypto.digests;

import java.lang.reflect.Array;
import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.ExtendedDigest;
import org.spongycastle.crypto.engines.GOST28147Engine;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.ParametersWithSBox;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.Memoable;
import org.spongycastle.util.Pack;

public class GOST3411Digest implements ExtendedDigest, Memoable {

    /* renamed from: C2 */
    private static final byte[] f962C2 = {0, -1, 0, -1, 0, -1, 0, -1, -1, 0, -1, 0, -1, 0, -1, 0, 0, -1, -1, 0, -1, 0, 0, -1, -1, 0, 0, 0, -1, -1, 0, -1};
    private static final int DIGEST_LENGTH = 32;

    /* renamed from: C */
    private byte[][] f963C;

    /* renamed from: H */
    private byte[] f964H;

    /* renamed from: K */
    private byte[] f965K;

    /* renamed from: L */
    private byte[] f966L;

    /* renamed from: M */
    private byte[] f967M;

    /* renamed from: S */
    byte[] f968S;
    private byte[] Sum;

    /* renamed from: U */
    byte[] f969U;

    /* renamed from: V */
    byte[] f970V;

    /* renamed from: W */
    byte[] f971W;

    /* renamed from: a */
    byte[] f972a;
    private long byteCount;
    private BlockCipher cipher;
    private byte[] sBox;

    /* renamed from: wS */
    short[] f973wS;
    short[] w_S;
    private byte[] xBuf;
    private int xBufOff;

    public String getAlgorithmName() {
        return "GOST3411";
    }

    public int getByteLength() {
        return 32;
    }

    public int getDigestSize() {
        return 32;
    }

    public GOST3411Digest() {
        this.f964H = new byte[32];
        this.f966L = new byte[32];
        this.f967M = new byte[32];
        this.Sum = new byte[32];
        this.f963C = (byte[][]) Array.newInstance(byte.class, new int[]{4, 32});
        this.xBuf = new byte[32];
        this.cipher = new GOST28147Engine();
        this.f965K = new byte[32];
        this.f972a = new byte[8];
        this.f973wS = new short[16];
        this.w_S = new short[16];
        this.f968S = new byte[32];
        this.f969U = new byte[32];
        this.f970V = new byte[32];
        this.f971W = new byte[32];
        this.sBox = GOST28147Engine.getSBox("D-A");
        this.cipher.init(true, new ParametersWithSBox(null, this.sBox));
        reset();
    }

    public GOST3411Digest(byte[] bArr) {
        this.f964H = new byte[32];
        this.f966L = new byte[32];
        this.f967M = new byte[32];
        this.Sum = new byte[32];
        this.f963C = (byte[][]) Array.newInstance(byte.class, new int[]{4, 32});
        this.xBuf = new byte[32];
        this.cipher = new GOST28147Engine();
        this.f965K = new byte[32];
        this.f972a = new byte[8];
        this.f973wS = new short[16];
        this.w_S = new short[16];
        this.f968S = new byte[32];
        this.f969U = new byte[32];
        this.f970V = new byte[32];
        this.f971W = new byte[32];
        this.sBox = Arrays.clone(bArr);
        this.cipher.init(true, new ParametersWithSBox(null, this.sBox));
        reset();
    }

    public GOST3411Digest(GOST3411Digest gOST3411Digest) {
        this.f964H = new byte[32];
        this.f966L = new byte[32];
        this.f967M = new byte[32];
        this.Sum = new byte[32];
        this.f963C = (byte[][]) Array.newInstance(byte.class, new int[]{4, 32});
        this.xBuf = new byte[32];
        this.cipher = new GOST28147Engine();
        this.f965K = new byte[32];
        this.f972a = new byte[8];
        this.f973wS = new short[16];
        this.w_S = new short[16];
        this.f968S = new byte[32];
        this.f969U = new byte[32];
        this.f970V = new byte[32];
        this.f971W = new byte[32];
        reset(gOST3411Digest);
    }

    public void update(byte b) {
        byte[] bArr = this.xBuf;
        int i = this.xBufOff;
        this.xBufOff = i + 1;
        bArr[i] = b;
        if (this.xBufOff == bArr.length) {
            sumByteArray(bArr);
            processBlock(this.xBuf, 0);
            this.xBufOff = 0;
        }
        this.byteCount++;
    }

    public void update(byte[] bArr, int i, int i2) {
        while (this.xBufOff != 0 && i2 > 0) {
            update(bArr[i]);
            i++;
            i2--;
        }
        while (true) {
            byte[] bArr2 = this.xBuf;
            if (i2 <= bArr2.length) {
                break;
            }
            System.arraycopy(bArr, i, bArr2, 0, bArr2.length);
            sumByteArray(this.xBuf);
            processBlock(this.xBuf, 0);
            byte[] bArr3 = this.xBuf;
            i += bArr3.length;
            i2 -= bArr3.length;
            this.byteCount += (long) bArr3.length;
        }
        while (i2 > 0) {
            update(bArr[i]);
            i++;
            i2--;
        }
    }

    /* renamed from: P */
    private byte[] m350P(byte[] bArr) {
        for (int i = 0; i < 8; i++) {
            byte[] bArr2 = this.f965K;
            int i2 = i * 4;
            bArr2[i2] = bArr[i];
            bArr2[i2 + 1] = bArr[i + 8];
            bArr2[i2 + 2] = bArr[i + 16];
            bArr2[i2 + 3] = bArr[i + 24];
        }
        return this.f965K;
    }

    /* renamed from: A */
    private byte[] m348A(byte[] bArr) {
        for (int i = 0; i < 8; i++) {
            this.f972a[i] = (byte) (bArr[i] ^ bArr[i + 8]);
        }
        System.arraycopy(bArr, 8, bArr, 0, 24);
        System.arraycopy(this.f972a, 0, bArr, 24, 8);
        return bArr;
    }

    /* renamed from: E */
    private void m349E(byte[] bArr, byte[] bArr2, int i, byte[] bArr3, int i2) {
        this.cipher.init(true, new KeyParameter(bArr));
        this.cipher.processBlock(bArr3, i2, bArr2, i);
    }

    /* renamed from: fw */
    private void m351fw(byte[] bArr) {
        cpyBytesToShort(bArr, this.f973wS);
        short[] sArr = this.w_S;
        short[] sArr2 = this.f973wS;
        sArr[15] = (short) (((((sArr2[0] ^ sArr2[1]) ^ sArr2[2]) ^ sArr2[3]) ^ sArr2[12]) ^ sArr2[15]);
        System.arraycopy(sArr2, 1, sArr, 0, 15);
        cpyShortToBytes(this.w_S, bArr);
    }

    /* access modifiers changed from: protected */
    public void processBlock(byte[] bArr, int i) {
        System.arraycopy(bArr, i, this.f967M, 0, 32);
        System.arraycopy(this.f964H, 0, this.f969U, 0, 32);
        System.arraycopy(this.f967M, 0, this.f970V, 0, 32);
        for (int i2 = 0; i2 < 32; i2++) {
            this.f971W[i2] = (byte) (this.f969U[i2] ^ this.f970V[i2]);
        }
        m349E(m350P(this.f971W), this.f968S, 0, this.f964H, 0);
        for (int i3 = 1; i3 < 4; i3++) {
            byte[] A = m348A(this.f969U);
            for (int i4 = 0; i4 < 32; i4++) {
                this.f969U[i4] = (byte) (A[i4] ^ this.f963C[i3][i4]);
            }
            this.f970V = m348A(m348A(this.f970V));
            for (int i5 = 0; i5 < 32; i5++) {
                this.f971W[i5] = (byte) (this.f969U[i5] ^ this.f970V[i5]);
            }
            int i6 = i3 * 8;
            m349E(m350P(this.f971W), this.f968S, i6, this.f964H, i6);
        }
        for (int i7 = 0; i7 < 12; i7++) {
            m351fw(this.f968S);
        }
        for (int i8 = 0; i8 < 32; i8++) {
            byte[] bArr2 = this.f968S;
            bArr2[i8] = (byte) (bArr2[i8] ^ this.f967M[i8]);
        }
        m351fw(this.f968S);
        for (int i9 = 0; i9 < 32; i9++) {
            byte[] bArr3 = this.f968S;
            bArr3[i9] = (byte) (this.f964H[i9] ^ bArr3[i9]);
        }
        for (int i10 = 0; i10 < 61; i10++) {
            m351fw(this.f968S);
        }
        byte[] bArr4 = this.f968S;
        byte[] bArr5 = this.f964H;
        System.arraycopy(bArr4, 0, bArr5, 0, bArr5.length);
    }

    private void finish() {
        Pack.longToLittleEndian(this.byteCount * 8, this.f966L, 0);
        while (this.xBufOff != 0) {
            update(0);
        }
        processBlock(this.f966L, 0);
        processBlock(this.Sum, 0);
    }

    public int doFinal(byte[] bArr, int i) {
        finish();
        byte[] bArr2 = this.f964H;
        System.arraycopy(bArr2, 0, bArr, i, bArr2.length);
        reset();
        return 32;
    }

    public void reset() {
        this.byteCount = 0;
        this.xBufOff = 0;
        int i = 0;
        while (true) {
            byte[] bArr = this.f964H;
            if (i >= bArr.length) {
                break;
            }
            bArr[i] = 0;
            i++;
        }
        int i2 = 0;
        while (true) {
            byte[] bArr2 = this.f966L;
            if (i2 >= bArr2.length) {
                break;
            }
            bArr2[i2] = 0;
            i2++;
        }
        int i3 = 0;
        while (true) {
            byte[] bArr3 = this.f967M;
            if (i3 >= bArr3.length) {
                break;
            }
            bArr3[i3] = 0;
            i3++;
        }
        int i4 = 0;
        while (true) {
            byte[][] bArr4 = this.f963C;
            if (i4 >= bArr4[1].length) {
                break;
            }
            bArr4[1][i4] = 0;
            i4++;
        }
        int i5 = 0;
        while (true) {
            byte[][] bArr5 = this.f963C;
            if (i5 >= bArr5[3].length) {
                break;
            }
            bArr5[3][i5] = 0;
            i5++;
        }
        int i6 = 0;
        while (true) {
            byte[] bArr6 = this.Sum;
            if (i6 >= bArr6.length) {
                break;
            }
            bArr6[i6] = 0;
            i6++;
        }
        int i7 = 0;
        while (true) {
            byte[] bArr7 = this.xBuf;
            if (i7 < bArr7.length) {
                bArr7[i7] = 0;
                i7++;
            } else {
                byte[] bArr8 = f962C2;
                System.arraycopy(bArr8, 0, this.f963C[2], 0, bArr8.length);
                return;
            }
        }
    }

    private void sumByteArray(byte[] bArr) {
        int i = 0;
        int i2 = 0;
        while (true) {
            byte[] bArr2 = this.Sum;
            if (i != bArr2.length) {
                int i3 = (bArr2[i] & 255) + (bArr[i] & 255) + i2;
                bArr2[i] = (byte) i3;
                i2 = i3 >>> 8;
                i++;
            } else {
                return;
            }
        }
    }

    private void cpyBytesToShort(byte[] bArr, short[] sArr) {
        for (int i = 0; i < bArr.length / 2; i++) {
            int i2 = i * 2;
            sArr[i] = (short) ((bArr[i2] & 255) | ((bArr[i2 + 1] << 8) & 65280));
        }
    }

    private void cpyShortToBytes(short[] sArr, byte[] bArr) {
        for (int i = 0; i < bArr.length / 2; i++) {
            int i2 = i * 2;
            bArr[i2 + 1] = (byte) (sArr[i] >> 8);
            bArr[i2] = (byte) sArr[i];
        }
    }

    public Memoable copy() {
        return new GOST3411Digest(this);
    }

    public void reset(Memoable memoable) {
        GOST3411Digest gOST3411Digest = (GOST3411Digest) memoable;
        this.sBox = gOST3411Digest.sBox;
        this.cipher.init(true, new ParametersWithSBox(null, this.sBox));
        reset();
        byte[] bArr = gOST3411Digest.f964H;
        System.arraycopy(bArr, 0, this.f964H, 0, bArr.length);
        byte[] bArr2 = gOST3411Digest.f966L;
        System.arraycopy(bArr2, 0, this.f966L, 0, bArr2.length);
        byte[] bArr3 = gOST3411Digest.f967M;
        System.arraycopy(bArr3, 0, this.f967M, 0, bArr3.length);
        byte[] bArr4 = gOST3411Digest.Sum;
        System.arraycopy(bArr4, 0, this.Sum, 0, bArr4.length);
        byte[][] bArr5 = gOST3411Digest.f963C;
        System.arraycopy(bArr5[1], 0, this.f963C[1], 0, bArr5[1].length);
        byte[][] bArr6 = gOST3411Digest.f963C;
        System.arraycopy(bArr6[2], 0, this.f963C[2], 0, bArr6[2].length);
        byte[][] bArr7 = gOST3411Digest.f963C;
        System.arraycopy(bArr7[3], 0, this.f963C[3], 0, bArr7[3].length);
        byte[] bArr8 = gOST3411Digest.xBuf;
        System.arraycopy(bArr8, 0, this.xBuf, 0, bArr8.length);
        this.xBufOff = gOST3411Digest.xBufOff;
        this.byteCount = gOST3411Digest.byteCount;
    }
}
