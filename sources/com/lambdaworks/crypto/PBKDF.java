package com.lambdaworks.crypto;

import java.security.GeneralSecurityException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class PBKDF {
    public static byte[] pbkdf2(String str, byte[] bArr, byte[] bArr2, int i, int i2) throws GeneralSecurityException {
        Mac instance = Mac.getInstance(str);
        instance.init(new SecretKeySpec(bArr, str));
        byte[] bArr3 = new byte[i2];
        pbkdf2(instance, bArr2, i, bArr3, i2);
        return bArr3;
    }

    public static void pbkdf2(Mac mac, byte[] bArr, int i, byte[] bArr2, int i2) throws GeneralSecurityException {
        int i3;
        byte[] bArr3;
        Mac mac2 = mac;
        byte[] bArr4 = bArr;
        int i4 = i2;
        int macLength = mac.getMacLength();
        double d = (double) i4;
        double d2 = (double) macLength;
        if (d <= (Math.pow(2.0d, 32.0d) - 1.0d) * d2) {
            byte[] bArr5 = new byte[macLength];
            byte[] bArr6 = new byte[macLength];
            byte[] bArr7 = new byte[(bArr4.length + 4)];
            int ceil = (int) Math.ceil(d / d2);
            int i5 = i4 - ((ceil - 1) * macLength);
            System.arraycopy(bArr4, 0, bArr7, 0, bArr4.length);
            for (int i6 = 1; i6 <= ceil; i6++) {
                bArr7[bArr4.length + 0] = (byte) ((i6 >> 24) & 255);
                bArr7[bArr4.length + 1] = (byte) ((i6 >> 16) & 255);
                bArr7[bArr4.length + 2] = (byte) ((i6 >> 8) & 255);
                bArr7[bArr4.length + 3] = (byte) ((i6 >> 0) & 255);
                mac2.update(bArr7);
                mac2.doFinal(bArr5, 0);
                System.arraycopy(bArr5, 0, bArr6, 0, macLength);
                int i7 = i;
                for (int i8 = 1; i8 < i7; i8++) {
                    mac2.update(bArr5);
                    mac2.doFinal(bArr5, 0);
                    for (int i9 = 0; i9 < macLength; i9++) {
                        bArr6[i9] = (byte) (bArr6[i9] ^ bArr5[i9]);
                    }
                }
                int i10 = (i6 - 1) * macLength;
                if (i6 == ceil) {
                    bArr3 = bArr2;
                    i3 = i5;
                } else {
                    bArr3 = bArr2;
                    i3 = macLength;
                }
                System.arraycopy(bArr6, 0, bArr3, i10, i3);
            }
            return;
        }
        throw new GeneralSecurityException("Requested key length too long");
    }
}
