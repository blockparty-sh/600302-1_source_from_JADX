package com.lambdaworks.crypto;

import com.google.common.base.Ascii;
import com.lambdaworks.jni.LibraryLoaders;
import java.security.GeneralSecurityException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class SCrypt {
    private static final boolean native_library_loaded = LibraryLoaders.loader().load("scrypt", true);

    /* renamed from: R */
    public static int m278R(int i, int i2) {
        return (i >>> (32 - i2)) | (i << i2);
    }

    public static native byte[] scryptN(byte[] bArr, byte[] bArr2, int i, int i2, int i3, int i4);

    public static byte[] scrypt(byte[] bArr, byte[] bArr2, int i, int i2, int i3, int i4) throws GeneralSecurityException {
        return native_library_loaded ? scryptN(bArr, bArr2, i, i2, i3, i4) : scryptJ(bArr, bArr2, i, i2, i3, i4);
    }

    public static byte[] scryptJ(byte[] bArr, byte[] bArr2, int i, int i2, int i3, int i4) throws GeneralSecurityException {
        int i5 = i;
        int i6 = i2;
        int i7 = i3;
        int i8 = i4;
        if (i5 < 2 || ((i5 - 1) & i5) != 0) {
            throw new IllegalArgumentException("N must be a power of 2 greater than 1");
        } else if (i5 > 16777215 / i6) {
            throw new IllegalArgumentException("Parameter N is too large");
        } else if (i6 <= 16777215 / i7) {
            String str = "HmacSHA256";
            Mac instance = Mac.getInstance(str);
            instance.init(new SecretKeySpec(bArr, str));
            byte[] bArr3 = new byte[i8];
            int i9 = i6 * 128;
            byte[] bArr4 = new byte[(i9 * i7)];
            byte[] bArr5 = new byte[(i6 * 256)];
            byte[] bArr6 = new byte[(i9 * i5)];
            PBKDF.pbkdf2(instance, bArr2, 1, bArr4, i7 * 128 * i6);
            int i10 = 0;
            while (i10 < i7) {
                int i11 = i10;
                smix(bArr4, i10 * 128 * i6, i2, i, bArr6, bArr5);
                i10 = i11 + 1;
            }
            PBKDF.pbkdf2(instance, bArr4, 1, bArr3, i8);
            return bArr3;
        } else {
            throw new IllegalArgumentException("Parameter r is too large");
        }
    }

    public static void smix(byte[] bArr, int i, int i2, int i3, byte[] bArr2, byte[] bArr3) {
        int i4 = i2 * 128;
        System.arraycopy(bArr, i, bArr3, 0, i4);
        for (int i5 = 0; i5 < i3; i5++) {
            System.arraycopy(bArr3, 0, bArr2, i5 * i4, i4);
            blockmix_salsa8(bArr3, 0, i4, i2);
        }
        for (int i6 = 0; i6 < i3; i6++) {
            blockxor(bArr2, (integerify(bArr3, 0, i2) & (i3 - 1)) * i4, bArr3, 0, i4);
            blockmix_salsa8(bArr3, 0, i4, i2);
        }
        System.arraycopy(bArr3, 0, bArr, i, i4);
    }

    public static void blockmix_salsa8(byte[] bArr, int i, int i2, int i3) {
        byte[] bArr2 = new byte[64];
        int i4 = i3 * 2;
        System.arraycopy(bArr, ((i4 - 1) * 64) + i, bArr2, 0, 64);
        for (int i5 = 0; i5 < i4; i5++) {
            int i6 = i5 * 64;
            blockxor(bArr, i6, bArr2, 0, 64);
            salsa20_8(bArr2);
            System.arraycopy(bArr2, 0, bArr, i6 + i2, 64);
        }
        for (int i7 = 0; i7 < i3; i7++) {
            System.arraycopy(bArr, (i7 * 2 * 64) + i2, bArr, (i7 * 64) + i, 64);
        }
        for (int i8 = 0; i8 < i3; i8++) {
            System.arraycopy(bArr, (((i8 * 2) + 1) * 64) + i2, bArr, ((i8 + i3) * 64) + i, 64);
        }
    }

    public static void salsa20_8(byte[] bArr) {
        int[] iArr = new int[16];
        int[] iArr2 = new int[16];
        for (int i = 0; i < 16; i++) {
            int i2 = i * 4;
            iArr[i] = (bArr[i2 + 0] & 255) << 0;
            iArr[i] = ((bArr[i2 + 1] & 255) << 8) | iArr[i];
            iArr[i] = iArr[i] | ((bArr[i2 + 2] & 255) << 16);
            iArr[i] = ((bArr[i2 + 3] & 255) << Ascii.CAN) | iArr[i];
        }
        System.arraycopy(iArr, 0, iArr2, 0, 16);
        for (int i3 = 8; i3 > 0; i3 -= 2) {
            iArr2[4] = iArr2[4] ^ m278R(iArr2[0] + iArr2[12], 7);
            iArr2[8] = iArr2[8] ^ m278R(iArr2[4] + iArr2[0], 9);
            iArr2[12] = iArr2[12] ^ m278R(iArr2[8] + iArr2[4], 13);
            iArr2[0] = iArr2[0] ^ m278R(iArr2[12] + iArr2[8], 18);
            iArr2[9] = iArr2[9] ^ m278R(iArr2[5] + iArr2[1], 7);
            iArr2[13] = iArr2[13] ^ m278R(iArr2[9] + iArr2[5], 9);
            iArr2[1] = iArr2[1] ^ m278R(iArr2[13] + iArr2[9], 13);
            iArr2[5] = iArr2[5] ^ m278R(iArr2[1] + iArr2[13], 18);
            iArr2[14] = m278R(iArr2[10] + iArr2[6], 7) ^ iArr2[14];
            iArr2[2] = m278R(iArr2[14] + iArr2[10], 9) ^ iArr2[2];
            iArr2[6] = m278R(iArr2[2] + iArr2[14], 13) ^ iArr2[6];
            iArr2[10] = iArr2[10] ^ m278R(iArr2[6] + iArr2[2], 18);
            iArr2[3] = iArr2[3] ^ m278R(iArr2[15] + iArr2[11], 7);
            iArr2[7] = iArr2[7] ^ m278R(iArr2[3] + iArr2[15], 9);
            iArr2[11] = iArr2[11] ^ m278R(iArr2[7] + iArr2[3], 13);
            iArr2[15] = iArr2[15] ^ m278R(iArr2[11] + iArr2[7], 18);
            iArr2[1] = iArr2[1] ^ m278R(iArr2[0] + iArr2[3], 7);
            iArr2[2] = iArr2[2] ^ m278R(iArr2[1] + iArr2[0], 9);
            iArr2[3] = iArr2[3] ^ m278R(iArr2[2] + iArr2[1], 13);
            iArr2[0] = m278R(iArr2[3] + iArr2[2], 18) ^ iArr2[0];
            iArr2[6] = iArr2[6] ^ m278R(iArr2[5] + iArr2[4], 7);
            iArr2[7] = iArr2[7] ^ m278R(iArr2[6] + iArr2[5], 9);
            iArr2[4] = iArr2[4] ^ m278R(iArr2[7] + iArr2[6], 13);
            iArr2[5] = iArr2[5] ^ m278R(iArr2[4] + iArr2[7], 18);
            iArr2[11] = iArr2[11] ^ m278R(iArr2[10] + iArr2[9], 7);
            iArr2[8] = iArr2[8] ^ m278R(iArr2[11] + iArr2[10], 9);
            iArr2[9] = iArr2[9] ^ m278R(iArr2[8] + iArr2[11], 13);
            iArr2[10] = iArr2[10] ^ m278R(iArr2[9] + iArr2[8], 18);
            iArr2[12] = iArr2[12] ^ m278R(iArr2[15] + iArr2[14], 7);
            iArr2[13] = iArr2[13] ^ m278R(iArr2[12] + iArr2[15], 9);
            iArr2[14] = m278R(iArr2[13] + iArr2[12], 13) ^ iArr2[14];
            iArr2[15] = m278R(iArr2[14] + iArr2[13], 18) ^ iArr2[15];
        }
        for (int i4 = 0; i4 < 16; i4++) {
            iArr[i4] = iArr2[i4] + iArr[i4];
        }
        int i5 = 0;
        for (int i6 = 16; i5 < i6; i6 = 16) {
            int i7 = i5 * 4;
            bArr[i7 + 0] = (byte) ((iArr[i5] >> 0) & 255);
            bArr[i7 + 1] = (byte) ((iArr[i5] >> 8) & 255);
            bArr[i7 + 2] = (byte) ((iArr[i5] >> 16) & 255);
            bArr[i7 + 3] = (byte) ((iArr[i5] >> 24) & 255);
            i5++;
        }
    }

    public static void blockxor(byte[] bArr, int i, byte[] bArr2, int i2, int i3) {
        for (int i4 = 0; i4 < i3; i4++) {
            int i5 = i2 + i4;
            bArr2[i5] = (byte) (bArr2[i5] ^ bArr[i + i4]);
        }
    }

    public static int integerify(byte[] bArr, int i, int i2) {
        int i3 = i + (((i2 * 2) - 1) * 64);
        return ((bArr[i3 + 3] & 255) << Ascii.CAN) | ((bArr[i3 + 0] & 255) << 0) | ((bArr[i3 + 1] & 255) << 8) | ((bArr[i3 + 2] & 255) << 16);
    }
}
