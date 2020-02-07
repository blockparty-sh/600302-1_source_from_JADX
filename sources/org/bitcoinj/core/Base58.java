package org.bitcoinj.core;

import java.math.BigInteger;
import java.util.Arrays;

public class Base58 {
    public static final char[] ALPHABET = "123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz".toCharArray();
    private static final char ENCODED_ZERO = ALPHABET[0];
    private static final int[] INDEXES = new int[128];

    static {
        int i = 0;
        Arrays.fill(INDEXES, -1);
        while (true) {
            char[] cArr = ALPHABET;
            if (i < cArr.length) {
                INDEXES[cArr[i]] = i;
                i++;
            } else {
                return;
            }
        }
    }

    public static String encode(byte[] bArr) {
        if (bArr.length == 0) {
            return "";
        }
        int i = 0;
        while (i < bArr.length && bArr[i] == 0) {
            i++;
        }
        byte[] copyOf = Arrays.copyOf(bArr, bArr.length);
        char[] cArr = new char[(copyOf.length * 2)];
        int length = cArr.length;
        int i2 = i;
        while (i2 < copyOf.length) {
            length--;
            cArr[length] = ALPHABET[divmod(copyOf, i2, 256, 58)];
            if (copyOf[i2] == 0) {
                i2++;
            }
        }
        while (length < cArr.length && cArr[length] == ENCODED_ZERO) {
            length++;
        }
        while (true) {
            i--;
            if (i < 0) {
                return new String(cArr, length, cArr.length - length);
            }
            length--;
            cArr[length] = ENCODED_ZERO;
        }
    }

    public static byte[] decode(String str) throws AddressFormatException {
        int i = 0;
        if (str.length() == 0) {
            return new byte[0];
        }
        byte[] bArr = new byte[str.length()];
        int i2 = 0;
        while (i2 < str.length()) {
            char charAt = str.charAt(i2);
            int i3 = charAt < 128 ? INDEXES[charAt] : -1;
            if (i3 >= 0) {
                bArr[i2] = (byte) i3;
                i2++;
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("Illegal character ");
                sb.append(charAt);
                sb.append(" at position ");
                sb.append(i2);
                throw new AddressFormatException(sb.toString());
            }
        }
        while (i < bArr.length && bArr[i] == 0) {
            i++;
        }
        byte[] bArr2 = new byte[str.length()];
        int length = bArr2.length;
        int i4 = i;
        while (i4 < bArr.length) {
            length--;
            bArr2[length] = divmod(bArr, i4, 58, 256);
            if (bArr[i4] == 0) {
                i4++;
            }
        }
        while (length < bArr2.length && bArr2[length] == 0) {
            length++;
        }
        return Arrays.copyOfRange(bArr2, length - i, bArr2.length);
    }

    public static BigInteger decodeToBigInteger(String str) throws AddressFormatException {
        return new BigInteger(1, decode(str));
    }

    public static byte[] decodeChecked(String str) throws AddressFormatException {
        byte[] decode = decode(str);
        if (decode.length >= 4) {
            byte[] copyOfRange = Arrays.copyOfRange(decode, 0, decode.length - 4);
            if (Arrays.equals(Arrays.copyOfRange(decode, decode.length - 4, decode.length), Arrays.copyOfRange(Sha256Hash.hashTwice(copyOfRange), 0, 4))) {
                return copyOfRange;
            }
            throw new AddressFormatException("Checksum does not validate");
        }
        throw new AddressFormatException("Input too short");
    }

    private static byte divmod(byte[] bArr, int i, int i2, int i3) {
        int i4 = 0;
        while (i < bArr.length) {
            int i5 = (i4 * i2) + (bArr[i] & 255);
            bArr[i] = (byte) (i5 / i3);
            i4 = i5 % i3;
            i++;
        }
        return (byte) i4;
    }
}
