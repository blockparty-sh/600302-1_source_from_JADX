package org.spongycastle.crypto.engines;

import com.google.common.base.Ascii;
import com.google.common.primitives.SignedBytes;
import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.OutputLengthException;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.RC2Parameters;
import org.spongycastle.crypto.signers.PSSSigner;

public class RC2Engine implements BlockCipher {
    private static final int BLOCK_SIZE = 8;
    private static byte[] piTable = {-39, Framer.EXIT_FRAME_PREFIX, -7, -60, Ascii.f520EM, -35, -75, -19, 40, -23, -3, 121, 74, -96, -40, -99, -58, 126, 55, -125, 43, 118, 83, -114, 98, 76, 100, -120, 68, -117, -5, -94, Ascii.ETB, -102, 89, -11, -121, -77, 79, 19, 97, 69, 109, -115, 9, -127, 125, Framer.STDERR_FRAME_PREFIX, -67, -113, SignedBytes.MAX_POWER_OF_TWO, -21, -122, -73, 123, Ascii.f532VT, -16, -107, Framer.ENTER_FRAME_PREFIX, 34, 92, 107, 78, -126, 84, -42, 101, -109, -50, 96, -78, Ascii.f522FS, 115, 86, -64, Ascii.DC4, -89, -116, -15, -36, Ascii.DC2, 117, -54, Ascii.f531US, 59, -66, -28, -47, 66, 61, -44, 48, -93, 60, -74, 38, 111, -65, Ascii.f529SO, -38, 70, 105, 7, 87, 39, -14, Ascii.f523GS, -101, PSSSigner.TRAILER_IMPLICIT, -108, 67, 3, -8, 17, -57, -10, -112, -17, 62, -25, 6, -61, -43, 47, -56, 102, Ascii.f527RS, -41, 8, -24, -22, -34, Byte.MIN_VALUE, 82, -18, -9, -124, -86, 114, -84, 53, 77, 106, 42, -106, Ascii.SUB, -46, 113, 90, Ascii.NAK, 73, 116, 75, -97, -48, 94, 4, Ascii.CAN, -92, -20, -62, -32, 65, 110, Ascii.f528SI, 81, -53, -52, 36, -111, -81, 80, -95, -12, 112, 57, -103, 124, 58, -123, 35, -72, -76, 122, -4, 2, 54, 91, 37, 85, -105, Framer.STDOUT_FRAME_PREFIX, Framer.STDIN_FRAME_PREFIX, 93, -6, -104, -29, -118, -110, -82, 5, -33, 41, 16, 103, 108, -70, -55, -45, 0, -26, -49, -31, -98, -88, 44, 99, Ascii.SYN, 1, 63, 88, -30, -119, -87, Ascii.f519CR, 56, 52, Ascii.ESC, -85, 51, -1, -80, -69, 72, Ascii.f521FF, Framer.STDIN_REQUEST_FRAME_PREFIX, -71, -79, -51, 46, -59, -13, -37, 71, -27, -91, -100, 119, 10, -90, 32, 104, -2, Byte.MAX_VALUE, -63, -83};
    private boolean encrypting;
    private int[] workingKey;

    private int rotateWordLeft(int i, int i2) {
        int i3 = i & 65535;
        return (i3 >> (16 - i2)) | (i3 << i2);
    }

    public String getAlgorithmName() {
        return "RC2";
    }

    public int getBlockSize() {
        return 8;
    }

    public void reset() {
    }

    private int[] generateWorkingKey(byte[] bArr, int i) {
        int[] iArr = new int[128];
        for (int i2 = 0; i2 != bArr.length; i2++) {
            iArr[i2] = bArr[i2] & 255;
        }
        int length = bArr.length;
        if (length < 128) {
            int i3 = iArr[length - 1];
            int i4 = length;
            int i5 = 0;
            while (true) {
                int i6 = i5 + 1;
                i3 = piTable[(i3 + iArr[i5]) & 255] & 255;
                int i7 = i4 + 1;
                iArr[i4] = i3;
                if (i7 >= 128) {
                    break;
                }
                i4 = i7;
                i5 = i6;
            }
        }
        int i8 = (i + 7) >> 3;
        int i9 = 128 - i8;
        int i10 = piTable[(255 >> ((-i) & 7)) & iArr[i9]] & 255;
        iArr[i9] = i10;
        for (int i11 = i9 - 1; i11 >= 0; i11--) {
            i10 = piTable[i10 ^ iArr[i11 + i8]] & 255;
            iArr[i11] = i10;
        }
        int[] iArr2 = new int[64];
        for (int i12 = 0; i12 != iArr2.length; i12++) {
            int i13 = i12 * 2;
            iArr2[i12] = iArr[i13] + (iArr[i13 + 1] << 8);
        }
        return iArr2;
    }

    public void init(boolean z, CipherParameters cipherParameters) {
        this.encrypting = z;
        if (cipherParameters instanceof RC2Parameters) {
            RC2Parameters rC2Parameters = (RC2Parameters) cipherParameters;
            this.workingKey = generateWorkingKey(rC2Parameters.getKey(), rC2Parameters.getEffectiveKeyBits());
        } else if (cipherParameters instanceof KeyParameter) {
            byte[] key = ((KeyParameter) cipherParameters).getKey();
            this.workingKey = generateWorkingKey(key, key.length * 8);
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("invalid parameter passed to RC2 init - ");
            sb.append(cipherParameters.getClass().getName());
            throw new IllegalArgumentException(sb.toString());
        }
    }

    public final int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        if (this.workingKey == null) {
            throw new IllegalStateException("RC2 engine not initialised");
        } else if (i + 8 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        } else if (i2 + 8 <= bArr2.length) {
            if (this.encrypting) {
                encryptBlock(bArr, i, bArr2, i2);
            } else {
                decryptBlock(bArr, i, bArr2, i2);
            }
            return 8;
        } else {
            throw new OutputLengthException("output buffer too short");
        }
    }

    private void encryptBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        int i3 = ((bArr[i + 7] & 255) << 8) + (bArr[i + 6] & 255);
        int i4 = ((bArr[i + 5] & 255) << 8) + (bArr[i + 4] & 255);
        int i5 = ((bArr[i + 3] & 255) << 8) + (bArr[i + 2] & 255);
        int i6 = ((bArr[i + 1] & 255) << 8) + (bArr[i + 0] & 255);
        for (int i7 = 0; i7 <= 16; i7 += 4) {
            i6 = rotateWordLeft(i6 + ((~i3) & i5) + (i4 & i3) + this.workingKey[i7], 1);
            i5 = rotateWordLeft(i5 + ((~i6) & i4) + (i3 & i6) + this.workingKey[i7 + 1], 2);
            i4 = rotateWordLeft(i4 + ((~i5) & i3) + (i6 & i5) + this.workingKey[i7 + 2], 3);
            i3 = rotateWordLeft(i3 + ((~i4) & i6) + (i5 & i4) + this.workingKey[i7 + 3], 5);
        }
        int[] iArr = this.workingKey;
        int i8 = i6 + iArr[i3 & 63];
        int i9 = i5 + iArr[i8 & 63];
        int i10 = i4 + iArr[i9 & 63];
        int i11 = i3 + iArr[i10 & 63];
        for (int i12 = 20; i12 <= 40; i12 += 4) {
            i8 = rotateWordLeft(i8 + ((~i11) & i9) + (i10 & i11) + this.workingKey[i12], 1);
            i9 = rotateWordLeft(i9 + ((~i8) & i10) + (i11 & i8) + this.workingKey[i12 + 1], 2);
            i10 = rotateWordLeft(i10 + ((~i9) & i11) + (i8 & i9) + this.workingKey[i12 + 2], 3);
            i11 = rotateWordLeft(i11 + ((~i10) & i8) + (i9 & i10) + this.workingKey[i12 + 3], 5);
        }
        int[] iArr2 = this.workingKey;
        int i13 = i8 + iArr2[i11 & 63];
        int i14 = i9 + iArr2[i13 & 63];
        int i15 = i10 + iArr2[i14 & 63];
        int i16 = i11 + iArr2[i15 & 63];
        for (int i17 = 44; i17 < 64; i17 += 4) {
            i13 = rotateWordLeft(i13 + ((~i16) & i14) + (i15 & i16) + this.workingKey[i17], 1);
            i14 = rotateWordLeft(i14 + ((~i13) & i15) + (i16 & i13) + this.workingKey[i17 + 1], 2);
            i15 = rotateWordLeft(i15 + ((~i14) & i16) + (i13 & i14) + this.workingKey[i17 + 2], 3);
            i16 = rotateWordLeft(i16 + ((~i15) & i13) + (i14 & i15) + this.workingKey[i17 + 3], 5);
        }
        bArr2[i2 + 0] = (byte) i13;
        bArr2[i2 + 1] = (byte) (i13 >> 8);
        bArr2[i2 + 2] = (byte) i14;
        bArr2[i2 + 3] = (byte) (i14 >> 8);
        bArr2[i2 + 4] = (byte) i15;
        bArr2[i2 + 5] = (byte) (i15 >> 8);
        bArr2[i2 + 6] = (byte) i16;
        bArr2[i2 + 7] = (byte) (i16 >> 8);
    }

    private void decryptBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        int i3 = ((bArr[i + 7] & 255) << 8) + (bArr[i + 6] & 255);
        int i4 = ((bArr[i + 5] & 255) << 8) + (bArr[i + 4] & 255);
        int i5 = ((bArr[i + 3] & 255) << 8) + (bArr[i + 2] & 255);
        int i6 = ((bArr[i + 1] & 255) << 8) + (bArr[i + 0] & 255);
        for (int i7 = 60; i7 >= 44; i7 -= 4) {
            i3 = rotateWordLeft(i3, 11) - ((((~i4) & i6) + (i5 & i4)) + this.workingKey[i7 + 3]);
            i4 = rotateWordLeft(i4, 13) - ((((~i5) & i3) + (i6 & i5)) + this.workingKey[i7 + 2]);
            i5 = rotateWordLeft(i5, 14) - ((((~i6) & i4) + (i3 & i6)) + this.workingKey[i7 + 1]);
            i6 = rotateWordLeft(i6, 15) - ((((~i3) & i5) + (i4 & i3)) + this.workingKey[i7]);
        }
        int[] iArr = this.workingKey;
        int i8 = i3 - iArr[i4 & 63];
        int i9 = i4 - iArr[i5 & 63];
        int i10 = i5 - iArr[i6 & 63];
        int i11 = i6 - iArr[i8 & 63];
        for (int i12 = 40; i12 >= 20; i12 -= 4) {
            i8 = rotateWordLeft(i8, 11) - ((((~i9) & i11) + (i10 & i9)) + this.workingKey[i12 + 3]);
            i9 = rotateWordLeft(i9, 13) - ((((~i10) & i8) + (i11 & i10)) + this.workingKey[i12 + 2]);
            i10 = rotateWordLeft(i10, 14) - ((((~i11) & i9) + (i8 & i11)) + this.workingKey[i12 + 1]);
            i11 = rotateWordLeft(i11, 15) - ((((~i8) & i10) + (i9 & i8)) + this.workingKey[i12]);
        }
        int[] iArr2 = this.workingKey;
        int i13 = i8 - iArr2[i9 & 63];
        int i14 = i9 - iArr2[i10 & 63];
        int i15 = i10 - iArr2[i11 & 63];
        int i16 = i11 - iArr2[i13 & 63];
        for (int i17 = 16; i17 >= 0; i17 -= 4) {
            i13 = rotateWordLeft(i13, 11) - ((((~i14) & i16) + (i15 & i14)) + this.workingKey[i17 + 3]);
            i14 = rotateWordLeft(i14, 13) - ((((~i15) & i13) + (i16 & i15)) + this.workingKey[i17 + 2]);
            i15 = rotateWordLeft(i15, 14) - ((((~i16) & i14) + (i13 & i16)) + this.workingKey[i17 + 1]);
            i16 = rotateWordLeft(i16, 15) - ((((~i13) & i15) + (i14 & i13)) + this.workingKey[i17]);
        }
        bArr2[i2 + 0] = (byte) i16;
        bArr2[i2 + 1] = (byte) (i16 >> 8);
        bArr2[i2 + 2] = (byte) i15;
        bArr2[i2 + 3] = (byte) (i15 >> 8);
        bArr2[i2 + 4] = (byte) i14;
        bArr2[i2 + 5] = (byte) (i14 >> 8);
        bArr2[i2 + 6] = (byte) i13;
        bArr2[i2 + 7] = (byte) (i13 >> 8);
    }
}
