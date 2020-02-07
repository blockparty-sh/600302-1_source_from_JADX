package org.spongycastle.crypto.engines;

import androidx.recyclerview.widget.ItemTouchHelper.Callback;
import com.google.common.base.Ascii;
import com.google.common.primitives.SignedBytes;
import com.yakivmospan.scytale.Options;
import java.lang.reflect.Array;
import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.OutputLengthException;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.signers.PSSSigner;
import org.spongycastle.crypto.tls.CipherSuite;

public class AESLightEngine implements BlockCipher {
    private static final int BLOCK_SIZE = 16;

    /* renamed from: S */
    private static final byte[] f1105S = {99, 124, 119, 123, -14, 107, 111, -59, 48, 1, 103, 43, -2, -41, -85, 118, -54, -126, -55, 125, -6, 89, 71, -16, -83, -44, -94, -81, -100, -92, 114, -64, -73, -3, -109, 38, 54, 63, -9, -52, 52, -91, -27, -15, 113, -40, Framer.STDOUT_FRAME_PREFIX, Ascii.NAK, 4, -57, 35, -61, Ascii.CAN, -106, 5, -102, 7, Ascii.DC2, Byte.MIN_VALUE, -30, -21, 39, -78, 117, 9, -125, 44, Ascii.SUB, Ascii.ESC, 110, 90, -96, 82, 59, -42, -77, 41, -29, 47, -124, 83, -47, 0, -19, 32, -4, -79, 91, 106, -53, -66, 57, 74, 76, 88, -49, -48, -17, -86, -5, 67, 77, 51, -123, 69, -7, 2, Byte.MAX_VALUE, 80, 60, -97, -88, 81, -93, SignedBytes.MAX_POWER_OF_TWO, -113, -110, -99, 56, -11, PSSSigner.TRAILER_IMPLICIT, -74, -38, Framer.ENTER_FRAME_PREFIX, 16, -1, -13, -46, -51, Ascii.f521FF, 19, -20, Framer.STDIN_REQUEST_FRAME_PREFIX, -105, 68, Ascii.ETB, -60, -89, 126, 61, 100, 93, Ascii.f520EM, 115, 96, -127, 79, -36, 34, 42, -112, -120, 70, -18, -72, Ascii.DC4, -34, 94, Ascii.f532VT, -37, -32, Framer.STDERR_FRAME_PREFIX, 58, 10, 73, 6, 36, 92, -62, -45, -84, 98, -111, -107, -28, 121, -25, -56, 55, 109, -115, -43, 78, -87, 108, 86, -12, -22, 101, 122, -82, 8, -70, Framer.EXIT_FRAME_PREFIX, 37, 46, Ascii.f522FS, -90, -76, -58, -24, -35, 116, Ascii.f531US, 75, -67, -117, -118, 112, 62, -75, 102, 72, 3, -10, Ascii.f529SO, 97, 53, 87, -71, -122, -63, Ascii.f523GS, -98, -31, -8, -104, 17, 105, -39, -114, -108, -101, Ascii.f527RS, -121, -23, -50, 85, 40, -33, -116, -95, -119, Ascii.f519CR, -65, -26, 66, 104, 65, -103, Framer.STDIN_FRAME_PREFIX, Ascii.f528SI, -80, 84, -69, Ascii.SYN};

    /* renamed from: Si */
    private static final byte[] f1106Si = {82, 9, 106, -43, 48, 54, -91, 56, -65, SignedBytes.MAX_POWER_OF_TWO, -93, -98, -127, -13, -41, -5, 124, -29, 57, -126, -101, 47, -1, -121, 52, -114, 67, 68, -60, -34, -23, -53, 84, 123, -108, Framer.STDERR_FRAME_PREFIX, -90, -62, 35, 61, -18, 76, -107, Ascii.f532VT, 66, -6, -61, 78, 8, 46, -95, 102, 40, -39, 36, -78, 118, 91, -94, 73, 109, -117, -47, 37, 114, -8, -10, 100, -122, 104, -104, Ascii.SYN, -44, -92, 92, -52, 93, 101, -74, -110, 108, 112, 72, 80, -3, -19, -71, -38, 94, Ascii.NAK, 70, 87, -89, -115, -99, -124, -112, -40, -85, 0, -116, PSSSigner.TRAILER_IMPLICIT, -45, 10, -9, -28, 88, 5, -72, -77, 69, 6, -48, 44, Ascii.f527RS, -113, -54, 63, Ascii.f528SI, 2, -63, -81, -67, 3, 1, 19, -118, 107, 58, -111, 17, 65, 79, 103, -36, -22, -105, -14, -49, -50, -16, -76, -26, 115, -106, -84, 116, 34, -25, -83, 53, -123, -30, -7, 55, -24, Ascii.f522FS, 117, -33, 110, 71, -15, Ascii.SUB, 113, Ascii.f523GS, 41, -59, -119, 111, -73, 98, Ascii.f529SO, -86, Ascii.CAN, -66, Ascii.ESC, -4, 86, 62, 75, -58, -46, 121, 32, -102, -37, -64, -2, Framer.EXIT_FRAME_PREFIX, -51, 90, -12, Ascii.f531US, -35, -88, 51, -120, 7, -57, Framer.STDOUT_FRAME_PREFIX, -79, Ascii.DC2, 16, 89, 39, Byte.MIN_VALUE, -20, Framer.STDIN_REQUEST_FRAME_PREFIX, 96, 81, Byte.MAX_VALUE, -87, Ascii.f520EM, -75, 74, Ascii.f519CR, Framer.STDIN_FRAME_PREFIX, -27, 122, -97, -109, -55, -100, -17, -96, -32, 59, 77, -82, 42, -11, -80, -56, -21, -69, 60, -125, 83, -103, 97, Ascii.ETB, 43, 4, 126, -70, 119, -42, 38, -31, 105, Ascii.DC4, 99, 85, Framer.ENTER_FRAME_PREFIX, Ascii.f521FF, 125};

    /* renamed from: m1 */
    private static final int f1107m1 = -2139062144;

    /* renamed from: m2 */
    private static final int f1108m2 = 2139062143;

    /* renamed from: m3 */
    private static final int f1109m3 = 27;
    private static final int[] rcon = {1, 2, 4, 8, 16, 32, 64, 128, 27, 54, 108, 216, 171, 77, 154, 47, 94, 188, 99, 198, 151, 53, 106, 212, 179, 125, Callback.DEFAULT_SWIPE_ANIMATION_DURATION, 239, CipherSuite.TLS_DH_anon_WITH_CAMELLIA_256_CBC_SHA256, 145};

    /* renamed from: C0 */
    private int f1110C0;

    /* renamed from: C1 */
    private int f1111C1;

    /* renamed from: C2 */
    private int f1112C2;

    /* renamed from: C3 */
    private int f1113C3;
    private int ROUNDS;
    private int[][] WorkingKey = null;
    private boolean forEncryption;

    private static int FFmulX(int i) {
        return (((i & f1107m1) >>> 7) * 27) ^ ((f1108m2 & i) << 1);
    }

    private static int shift(int i, int i2) {
        return (i << (-i2)) | (i >>> i2);
    }

    public String getAlgorithmName() {
        return Options.ALGORITHM_AES;
    }

    public int getBlockSize() {
        return 16;
    }

    public void reset() {
    }

    private static int mcol(int i) {
        int FFmulX = FFmulX(i);
        return shift(i, 24) ^ ((FFmulX ^ shift(i ^ FFmulX, 8)) ^ shift(i, 16));
    }

    private static int inv_mcol(int i) {
        int FFmulX = FFmulX(i);
        int FFmulX2 = FFmulX(FFmulX);
        int FFmulX3 = FFmulX(FFmulX2);
        int i2 = i ^ FFmulX3;
        return shift(i2, 24) ^ ((shift(FFmulX ^ i2, 8) ^ (FFmulX3 ^ (FFmulX ^ FFmulX2))) ^ shift(FFmulX2 ^ i2, 16));
    }

    private static int subWord(int i) {
        byte[] bArr = f1105S;
        return (bArr[(i >> 24) & 255] << Ascii.CAN) | (bArr[i & 255] & 255) | ((bArr[(i >> 8) & 255] & 255) << 8) | ((bArr[(i >> 16) & 255] & 255) << 16);
    }

    private int[][] generateWorkingKey(byte[] bArr, boolean z) {
        int length = bArr.length / 4;
        if ((length == 4 || length == 6 || length == 8) && length * 4 == bArr.length) {
            this.ROUNDS = length + 6;
            int[][] iArr = (int[][]) Array.newInstance(int.class, new int[]{this.ROUNDS + 1, 4});
            int i = 0;
            int i2 = 0;
            while (i < bArr.length) {
                iArr[i2 >> 2][i2 & 3] = (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16) | (bArr[i + 3] << Ascii.CAN);
                i += 4;
                i2++;
            }
            int i3 = (this.ROUNDS + 1) << 2;
            for (int i4 = length; i4 < i3; i4++) {
                int i5 = i4 - 1;
                int i6 = iArr[i5 >> 2][i5 & 3];
                int i7 = i4 % length;
                if (i7 == 0) {
                    i6 = subWord(shift(i6, 8)) ^ rcon[(i4 / length) - 1];
                } else if (length > 6 && i7 == 4) {
                    i6 = subWord(i6);
                }
                int i8 = i4 - length;
                iArr[i4 >> 2][i4 & 3] = i6 ^ iArr[i8 >> 2][i8 & 3];
            }
            if (!z) {
                for (int i9 = 1; i9 < this.ROUNDS; i9++) {
                    for (int i10 = 0; i10 < 4; i10++) {
                        iArr[i9][i10] = inv_mcol(iArr[i9][i10]);
                    }
                }
            }
            return iArr;
        }
        throw new IllegalArgumentException("Key length not 128/192/256 bits.");
    }

    public void init(boolean z, CipherParameters cipherParameters) {
        if (cipherParameters instanceof KeyParameter) {
            this.WorkingKey = generateWorkingKey(((KeyParameter) cipherParameters).getKey(), z);
            this.forEncryption = z;
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("invalid parameter passed to AES init - ");
        sb.append(cipherParameters.getClass().getName());
        throw new IllegalArgumentException(sb.toString());
    }

    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        if (this.WorkingKey == null) {
            throw new IllegalStateException("AES engine not initialised");
        } else if (i + 16 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        } else if (i2 + 16 <= bArr2.length) {
            if (this.forEncryption) {
                unpackBlock(bArr, i);
                encryptBlock(this.WorkingKey);
                packBlock(bArr2, i2);
            } else {
                unpackBlock(bArr, i);
                decryptBlock(this.WorkingKey);
                packBlock(bArr2, i2);
            }
            return 16;
        } else {
            throw new OutputLengthException("output buffer too short");
        }
    }

    private void unpackBlock(byte[] bArr, int i) {
        int i2 = i + 1;
        this.f1110C0 = bArr[i] & 255;
        int i3 = i2 + 1;
        this.f1110C0 |= (bArr[i2] & 255) << 8;
        int i4 = i3 + 1;
        this.f1110C0 |= (bArr[i3] & 255) << 16;
        int i5 = i4 + 1;
        this.f1110C0 |= bArr[i4] << Ascii.CAN;
        int i6 = i5 + 1;
        this.f1111C1 = bArr[i5] & 255;
        int i7 = i6 + 1;
        this.f1111C1 = ((bArr[i6] & 255) << 8) | this.f1111C1;
        int i8 = i7 + 1;
        this.f1111C1 |= (bArr[i7] & 255) << 16;
        int i9 = i8 + 1;
        this.f1111C1 |= bArr[i8] << Ascii.CAN;
        int i10 = i9 + 1;
        this.f1112C2 = bArr[i9] & 255;
        int i11 = i10 + 1;
        this.f1112C2 = ((bArr[i10] & 255) << 8) | this.f1112C2;
        int i12 = i11 + 1;
        this.f1112C2 |= (bArr[i11] & 255) << 16;
        int i13 = i12 + 1;
        this.f1112C2 |= bArr[i12] << Ascii.CAN;
        int i14 = i13 + 1;
        this.f1113C3 = bArr[i13] & 255;
        int i15 = i14 + 1;
        this.f1113C3 = ((bArr[i14] & 255) << 8) | this.f1113C3;
        int i16 = i15 + 1;
        this.f1113C3 |= (bArr[i15] & 255) << 16;
        this.f1113C3 = (bArr[i16] << Ascii.CAN) | this.f1113C3;
    }

    private void packBlock(byte[] bArr, int i) {
        int i2 = i + 1;
        int i3 = this.f1110C0;
        bArr[i] = (byte) i3;
        int i4 = i2 + 1;
        bArr[i2] = (byte) (i3 >> 8);
        int i5 = i4 + 1;
        bArr[i4] = (byte) (i3 >> 16);
        int i6 = i5 + 1;
        bArr[i5] = (byte) (i3 >> 24);
        int i7 = i6 + 1;
        int i8 = this.f1111C1;
        bArr[i6] = (byte) i8;
        int i9 = i7 + 1;
        bArr[i7] = (byte) (i8 >> 8);
        int i10 = i9 + 1;
        bArr[i9] = (byte) (i8 >> 16);
        int i11 = i10 + 1;
        bArr[i10] = (byte) (i8 >> 24);
        int i12 = i11 + 1;
        int i13 = this.f1112C2;
        bArr[i11] = (byte) i13;
        int i14 = i12 + 1;
        bArr[i12] = (byte) (i13 >> 8);
        int i15 = i14 + 1;
        bArr[i14] = (byte) (i13 >> 16);
        int i16 = i15 + 1;
        bArr[i15] = (byte) (i13 >> 24);
        int i17 = i16 + 1;
        int i18 = this.f1113C3;
        bArr[i16] = (byte) i18;
        int i19 = i17 + 1;
        bArr[i17] = (byte) (i18 >> 8);
        int i20 = i19 + 1;
        bArr[i19] = (byte) (i18 >> 16);
        bArr[i20] = (byte) (i18 >> 24);
    }

    private void encryptBlock(int[][] iArr) {
        int i = this.f1113C3 ^ iArr[0][3];
        int i2 = this.f1112C2 ^ iArr[0][2];
        int i3 = this.f1111C1 ^ iArr[0][1];
        int i4 = this.f1110C0 ^ iArr[0][0];
        int i5 = 1;
        while (i5 < this.ROUNDS - 1) {
            byte[] bArr = f1105S;
            int mcol = mcol((bArr[(i >> 24) & 255] << Ascii.CAN) ^ (((bArr[i4 & 255] & 255) ^ ((bArr[(i3 >> 8) & 255] & 255) << 8)) ^ ((bArr[(i2 >> 16) & 255] & 255) << 16))) ^ iArr[i5][0];
            byte[] bArr2 = f1105S;
            int mcol2 = mcol((bArr2[(i4 >> 24) & 255] << Ascii.CAN) ^ (((bArr2[i3 & 255] & 255) ^ ((bArr2[(i2 >> 8) & 255] & 255) << 8)) ^ ((bArr2[(i >> 16) & 255] & 255) << 16))) ^ iArr[i5][1];
            byte[] bArr3 = f1105S;
            int mcol3 = mcol((bArr3[(i3 >> 24) & 255] << Ascii.CAN) ^ (((bArr3[i2 & 255] & 255) ^ ((bArr3[(i >> 8) & 255] & 255) << 8)) ^ ((bArr3[(i4 >> 16) & 255] & 255) << 16))) ^ iArr[i5][2];
            byte[] bArr4 = f1105S;
            int mcol4 = mcol(((((bArr4[(i4 >> 8) & 255] & 255) << 8) ^ (bArr4[i & 255] & 255)) ^ ((bArr4[(i3 >> 16) & 255] & 255) << 16)) ^ (bArr4[(i2 >> 24) & 255] << Ascii.CAN));
            int i6 = i5 + 1;
            int i7 = iArr[i5][3] ^ mcol4;
            byte[] bArr5 = f1105S;
            i4 = mcol((bArr5[(i7 >> 24) & 255] << Ascii.CAN) ^ (((bArr5[mcol & 255] & 255) ^ ((bArr5[(mcol2 >> 8) & 255] & 255) << 8)) ^ ((bArr5[(mcol3 >> 16) & 255] & 255) << 16))) ^ iArr[i6][0];
            byte[] bArr6 = f1105S;
            int mcol5 = mcol((bArr6[(mcol >> 24) & 255] << Ascii.CAN) ^ (((bArr6[mcol2 & 255] & 255) ^ ((bArr6[(mcol3 >> 8) & 255] & 255) << 8)) ^ ((bArr6[(i7 >> 16) & 255] & 255) << 16))) ^ iArr[i6][1];
            byte[] bArr7 = f1105S;
            int mcol6 = mcol((bArr7[(mcol2 >> 24) & 255] << Ascii.CAN) ^ (((bArr7[mcol3 & 255] & 255) ^ ((bArr7[(i7 >> 8) & 255] & 255) << 8)) ^ ((bArr7[(mcol >> 16) & 255] & 255) << 16))) ^ iArr[i6][2];
            byte[] bArr8 = f1105S;
            int i8 = i6 + 1;
            int mcol7 = mcol((((bArr8[i7 & 255] & 255) ^ ((bArr8[(mcol >> 8) & 255] & 255) << 8)) ^ ((bArr8[(mcol2 >> 16) & 255] & 255) << 16)) ^ (bArr8[(mcol3 >> 24) & 255] << Ascii.CAN)) ^ iArr[i6][3];
            i3 = mcol5;
            i2 = mcol6;
            i = mcol7;
            i5 = i8;
        }
        byte[] bArr9 = f1105S;
        int mcol8 = mcol((bArr9[(i >> 24) & 255] << Ascii.CAN) ^ (((bArr9[i4 & 255] & 255) ^ ((bArr9[(i3 >> 8) & 255] & 255) << 8)) ^ ((bArr9[(i2 >> 16) & 255] & 255) << 16))) ^ iArr[i5][0];
        byte[] bArr10 = f1105S;
        int mcol9 = mcol((bArr10[(i4 >> 24) & 255] << Ascii.CAN) ^ (((bArr10[i3 & 255] & 255) ^ ((bArr10[(i2 >> 8) & 255] & 255) << 8)) ^ ((bArr10[(i >> 16) & 255] & 255) << 16))) ^ iArr[i5][1];
        byte[] bArr11 = f1105S;
        int mcol10 = mcol((bArr11[(i3 >> 24) & 255] << Ascii.CAN) ^ (((bArr11[i2 & 255] & 255) ^ ((bArr11[(i >> 8) & 255] & 255) << 8)) ^ ((bArr11[(i4 >> 16) & 255] & 255) << 16))) ^ iArr[i5][2];
        byte[] bArr12 = f1105S;
        int mcol11 = mcol(((((bArr12[(i4 >> 8) & 255] & 255) << 8) ^ (bArr12[i & 255] & 255)) ^ ((bArr12[(i3 >> 16) & 255] & 255) << 16)) ^ (bArr12[(i2 >> 24) & 255] << Ascii.CAN));
        int i9 = i5 + 1;
        int i10 = iArr[i5][3] ^ mcol11;
        byte[] bArr13 = f1105S;
        this.f1110C0 = iArr[i9][0] ^ ((((bArr13[mcol8 & 255] & 255) ^ ((bArr13[(mcol9 >> 8) & 255] & 255) << 8)) ^ ((bArr13[(mcol10 >> 16) & 255] & 255) << 16)) ^ (bArr13[(i10 >> 24) & 255] << Ascii.CAN));
        this.f1111C1 = ((((bArr13[mcol9 & 255] & 255) ^ ((bArr13[(mcol10 >> 8) & 255] & 255) << 8)) ^ ((bArr13[(i10 >> 16) & 255] & 255) << 16)) ^ (bArr13[(mcol8 >> 24) & 255] << Ascii.CAN)) ^ iArr[i9][1];
        this.f1112C2 = ((((bArr13[mcol10 & 255] & 255) ^ ((bArr13[(i10 >> 8) & 255] & 255) << 8)) ^ ((bArr13[(mcol8 >> 16) & 255] & 255) << 16)) ^ (bArr13[(mcol9 >> 24) & 255] << Ascii.CAN)) ^ iArr[i9][2];
        this.f1113C3 = iArr[i9][3] ^ ((((bArr13[i10 & 255] & 255) ^ ((bArr13[(mcol8 >> 8) & 255] & 255) << 8)) ^ ((bArr13[(mcol9 >> 16) & 255] & 255) << 16)) ^ (bArr13[(mcol10 >> 24) & 255] << Ascii.CAN));
    }

    private void decryptBlock(int[][] iArr) {
        int i = this.f1110C0;
        int i2 = this.ROUNDS;
        int i3 = i ^ iArr[i2][0];
        int i4 = this.f1111C1 ^ iArr[i2][1];
        int i5 = this.f1112C2 ^ iArr[i2][2];
        int i6 = i2 - 1;
        int i7 = iArr[i2][3] ^ this.f1113C3;
        while (i6 > 1) {
            byte[] bArr = f1106Si;
            int inv_mcol = inv_mcol((bArr[(i4 >> 24) & 255] << Ascii.CAN) ^ (((bArr[i3 & 255] & 255) ^ ((bArr[(i7 >> 8) & 255] & 255) << 8)) ^ ((bArr[(i5 >> 16) & 255] & 255) << 16))) ^ iArr[i6][0];
            byte[] bArr2 = f1106Si;
            int inv_mcol2 = inv_mcol((bArr2[(i5 >> 24) & 255] << Ascii.CAN) ^ (((bArr2[i4 & 255] & 255) ^ ((bArr2[(i3 >> 8) & 255] & 255) << 8)) ^ ((bArr2[(i7 >> 16) & 255] & 255) << 16))) ^ iArr[i6][1];
            byte[] bArr3 = f1106Si;
            int inv_mcol3 = inv_mcol((bArr3[(i7 >> 24) & 255] << Ascii.CAN) ^ (((bArr3[i5 & 255] & 255) ^ ((bArr3[(i4 >> 8) & 255] & 255) << 8)) ^ ((bArr3[(i3 >> 16) & 255] & 255) << 16))) ^ iArr[i6][2];
            byte[] bArr4 = f1106Si;
            byte b = ((bArr4[i7 & 255] & 255) ^ ((bArr4[(i5 >> 8) & 255] & 255) << 8)) ^ ((bArr4[(i4 >> 16) & 255] & 255) << 16);
            int i8 = i6 - 1;
            int inv_mcol4 = inv_mcol((bArr4[(i3 >> 24) & 255] << Ascii.CAN) ^ b) ^ iArr[i6][3];
            byte[] bArr5 = f1106Si;
            int inv_mcol5 = inv_mcol((bArr5[(inv_mcol2 >> 24) & 255] << Ascii.CAN) ^ (((bArr5[inv_mcol & 255] & 255) ^ ((bArr5[(inv_mcol4 >> 8) & 255] & 255) << 8)) ^ ((bArr5[(inv_mcol3 >> 16) & 255] & 255) << 16))) ^ iArr[i8][0];
            byte[] bArr6 = f1106Si;
            int inv_mcol6 = inv_mcol((bArr6[(inv_mcol3 >> 24) & 255] << Ascii.CAN) ^ (((bArr6[inv_mcol2 & 255] & 255) ^ ((bArr6[(inv_mcol >> 8) & 255] & 255) << 8)) ^ ((bArr6[(inv_mcol4 >> 16) & 255] & 255) << 16))) ^ iArr[i8][1];
            byte[] bArr7 = f1106Si;
            int inv_mcol7 = inv_mcol((bArr7[(inv_mcol4 >> 24) & 255] << Ascii.CAN) ^ (((bArr7[inv_mcol3 & 255] & 255) ^ ((bArr7[(inv_mcol2 >> 8) & 255] & 255) << 8)) ^ ((bArr7[(inv_mcol >> 16) & 255] & 255) << 16))) ^ iArr[i8][2];
            byte[] bArr8 = f1106Si;
            int inv_mcol8 = inv_mcol((((bArr8[inv_mcol4 & 255] & 255) ^ ((bArr8[(inv_mcol3 >> 8) & 255] & 255) << 8)) ^ ((bArr8[(inv_mcol2 >> 16) & 255] & 255) << 16)) ^ (bArr8[(inv_mcol >> 24) & 255] << Ascii.CAN));
            int i9 = i8 - 1;
            i7 = iArr[i8][3] ^ inv_mcol8;
            i3 = inv_mcol5;
            i4 = inv_mcol6;
            i5 = inv_mcol7;
            i6 = i9;
        }
        byte[] bArr9 = f1106Si;
        int inv_mcol9 = inv_mcol((bArr9[(i4 >> 24) & 255] << Ascii.CAN) ^ (((bArr9[i3 & 255] & 255) ^ ((bArr9[(i7 >> 8) & 255] & 255) << 8)) ^ ((bArr9[(i5 >> 16) & 255] & 255) << 16))) ^ iArr[i6][0];
        byte[] bArr10 = f1106Si;
        int inv_mcol10 = inv_mcol((bArr10[(i5 >> 24) & 255] << Ascii.CAN) ^ (((bArr10[i4 & 255] & 255) ^ ((bArr10[(i3 >> 8) & 255] & 255) << 8)) ^ ((bArr10[(i7 >> 16) & 255] & 255) << 16))) ^ iArr[i6][1];
        byte[] bArr11 = f1106Si;
        int inv_mcol11 = inv_mcol((bArr11[(i7 >> 24) & 255] << Ascii.CAN) ^ (((bArr11[i5 & 255] & 255) ^ ((bArr11[(i4 >> 8) & 255] & 255) << 8)) ^ ((bArr11[(i3 >> 16) & 255] & 255) << 16))) ^ iArr[i6][2];
        byte[] bArr12 = f1106Si;
        int inv_mcol12 = inv_mcol((bArr12[(i3 >> 24) & 255] << Ascii.CAN) ^ (((bArr12[i7 & 255] & 255) ^ ((bArr12[(i5 >> 8) & 255] & 255) << 8)) ^ ((bArr12[(i4 >> 16) & 255] & 255) << 16))) ^ iArr[i6][3];
        byte[] bArr13 = f1106Si;
        this.f1110C0 = ((((bArr13[inv_mcol9 & 255] & 255) ^ ((bArr13[(inv_mcol12 >> 8) & 255] & 255) << 8)) ^ ((bArr13[(inv_mcol11 >> 16) & 255] & 255) << 16)) ^ (bArr13[(inv_mcol10 >> 24) & 255] << Ascii.CAN)) ^ iArr[0][0];
        this.f1111C1 = ((((bArr13[inv_mcol10 & 255] & 255) ^ ((bArr13[(inv_mcol9 >> 8) & 255] & 255) << 8)) ^ ((bArr13[(inv_mcol12 >> 16) & 255] & 255) << 16)) ^ (bArr13[(inv_mcol11 >> 24) & 255] << Ascii.CAN)) ^ iArr[0][1];
        this.f1112C2 = ((((bArr13[inv_mcol11 & 255] & 255) ^ ((bArr13[(inv_mcol10 >> 8) & 255] & 255) << 8)) ^ ((bArr13[(inv_mcol9 >> 16) & 255] & 255) << 16)) ^ (bArr13[(inv_mcol12 >> 24) & 255] << Ascii.CAN)) ^ iArr[0][2];
        this.f1113C3 = iArr[0][3] ^ ((((bArr13[inv_mcol12 & 255] & 255) ^ ((bArr13[(inv_mcol11 >> 8) & 255] & 255) << 8)) ^ ((bArr13[(inv_mcol10 >> 16) & 255] & 255) << 16)) ^ (bArr13[(inv_mcol9 >> 24) & 255] << Ascii.CAN));
    }
}
