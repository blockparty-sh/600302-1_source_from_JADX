package org.spongycastle.crypto.paddings;

import java.security.SecureRandom;
import org.spongycastle.crypto.InvalidCipherTextException;

public class PKCS7Padding implements BlockCipherPadding {
    public String getPaddingName() {
        return "PKCS7";
    }

    public void init(SecureRandom secureRandom) throws IllegalArgumentException {
    }

    public int addPadding(byte[] bArr, int i) {
        byte length = (byte) (bArr.length - i);
        while (i < bArr.length) {
            bArr[i] = length;
            i++;
        }
        return length;
    }

    public int padCount(byte[] bArr) throws InvalidCipherTextException {
        int i = 1;
        byte b = bArr[bArr.length - 1] & 255;
        String str = "pad block corrupted";
        if (b > bArr.length || b == 0) {
            throw new InvalidCipherTextException(str);
        }
        while (i <= b) {
            if (bArr[bArr.length - i] == b) {
                i++;
            } else {
                throw new InvalidCipherTextException(str);
            }
        }
        return b;
    }
}
