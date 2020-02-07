package com.bitcoin.bitcoink.address;

import com.google.common.base.Ascii;
import org.bitcoinj.core.AddressFormatException;

class AddressCashUtil {
    static final String CHARSET = "qpzry9x8gf2tvdw0s3jn54khce6mua7l";
    static final byte[] CHARSET_REV = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, Ascii.f528SI, -1, 10, 17, Ascii.NAK, Ascii.DC4, Ascii.SUB, Ascii.f527RS, 7, 5, -1, -1, -1, -1, -1, -1, -1, Ascii.f523GS, -1, Ascii.CAN, Ascii.f519CR, Ascii.f520EM, 9, 8, Ascii.ETB, -1, Ascii.DC2, Ascii.SYN, Ascii.f531US, Ascii.ESC, 19, -1, 1, 0, 3, 16, Ascii.f532VT, Ascii.f522FS, Ascii.f521FF, Ascii.f529SO, 6, 4, 2, -1, -1, -1, -1, -1, -1, Ascii.f523GS, -1, Ascii.CAN, Ascii.f519CR, Ascii.f520EM, 9, 8, Ascii.ETB, -1, Ascii.DC2, Ascii.SYN, Ascii.f531US, Ascii.ESC, 19, -1, 1, 0, 3, 16, Ascii.f532VT, Ascii.f522FS, Ascii.f521FF, Ascii.f529SO, 6, 4, 2, -1, -1, -1, -1, -1};

    public static class AddressVersionAndBytes {
        private final byte[] bytes;
        private final byte version;

        public AddressVersionAndBytes(byte b, byte[] bArr) {
            this.version = b;
            this.bytes = bArr;
        }

        public byte getVersion() {
            return this.version;
        }

        public byte[] getBytes() {
            return this.bytes;
        }
    }

    private static int calculateHashSizeFromVersionByte(byte b) {
        int i = ((b & 3) * 4) + 20;
        return (b & 4) != 0 ? i * 2 : i;
    }

    AddressCashUtil() {
    }

    static byte[] concatenateByteArrays(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[(bArr.length + bArr2.length)];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }

    static long computePolyMod(byte[] bArr) {
        long j = 1;
        for (byte b : bArr) {
            byte b2 = (byte) ((int) (j >> 35));
            j = ((j & 34359738367L) << 5) ^ ((long) b);
            if ((b2 & 1) != 0) {
                j ^= 656907472481L;
            }
            if ((b2 & 2) != 0) {
                j ^= 522768456162L;
            }
            if ((b2 & 4) != 0) {
                j ^= 1044723512260L;
            }
            if ((b2 & 8) != 0) {
                j ^= 748107326120L;
            }
            if ((b2 & 16) != 0) {
                j ^= 130178868336L;
            }
        }
        return j ^ 1;
    }

    static byte[] expandPrefix(String str) {
        byte[] bArr = new byte[(str.length() + 1)];
        byte[] bytes = str.getBytes();
        for (int i = 0; i < str.length(); i++) {
            bArr[i] = (byte) (bytes[i] & Ascii.f531US);
        }
        bArr[str.length()] = 0;
        return bArr;
    }

    static boolean verifyChecksum(String str, byte[] bArr) {
        return computePolyMod(concatenateByteArrays(expandPrefix(str), bArr)) == 0;
    }

    static byte[] createChecksum(String str, byte[] bArr) {
        byte[] concatenateByteArrays = concatenateByteArrays(expandPrefix(str), bArr);
        byte[] bArr2 = new byte[(concatenateByteArrays.length + 8)];
        System.arraycopy(concatenateByteArrays, 0, bArr2, 0, concatenateByteArrays.length);
        long computePolyMod = computePolyMod(bArr2);
        byte[] bArr3 = new byte[8];
        for (int i = 0; i < 8; i++) {
            bArr3[i] = (byte) ((int) ((computePolyMod >> ((7 - i) * 5)) & 31));
        }
        return bArr3;
    }

    public static String encodeCashAddress(String str, byte[] bArr) {
        byte[] concatenateByteArrays = concatenateByteArrays(bArr, createChecksum(str, bArr));
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(':');
        StringBuilder sb2 = new StringBuilder(sb.toString());
        for (byte charAt : concatenateByteArrays) {
            sb2.append(CHARSET.charAt(charAt));
        }
        return sb2.toString();
    }

    public static byte[] decodeCashAddress(String str, String str2) {
        String str3;
        StringBuilder sb;
        int i = 0;
        boolean z = false;
        boolean z2 = false;
        int i2 = 0;
        boolean z3 = false;
        while (true) {
            String str4 = ": Unexpected character at pos ";
            str3 = "cashaddr:  ";
            if (i < str.length()) {
                char charAt = str.charAt(i);
                if (charAt >= 'a' && charAt <= 'z') {
                    z2 = true;
                } else if (charAt >= 'A' && charAt <= 'Z') {
                    z = true;
                } else if (charAt >= '0' && charAt <= '9') {
                    z3 = true;
                } else if (charAt != ':') {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(str3);
                    sb2.append(str);
                    sb2.append(str4);
                    sb2.append(i);
                    throw new AddressFormatException(sb2.toString());
                } else if (z3 || i == 0 || i2 != 0) {
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append(str3);
                    sb3.append(str);
                    sb3.append(": The separator cannot be the first character, cannot have number and there must not be 2 separators");
                } else {
                    i2 = i;
                }
                i++;
            } else if (!z || !z2) {
                if (i2 == 0) {
                    sb = new StringBuilder(str2);
                } else {
                    sb = new StringBuilder(str.substring(0, i2).toLowerCase());
                    i2++;
                }
                int length = str.length() - i2;
                byte[] bArr = new byte[length];
                int i3 = 0;
                while (i3 < length) {
                    char charAt2 = str.charAt(i3 + i2);
                    if (charAt2 <= 127) {
                        byte[] bArr2 = CHARSET_REV;
                        if (bArr2[charAt2] != -1) {
                            bArr[i3] = bArr2[charAt2];
                            i3++;
                        }
                    }
                    StringBuilder sb4 = new StringBuilder();
                    sb4.append(str3);
                    sb4.append(str);
                    sb4.append(str4);
                    sb4.append(i3);
                    throw new AddressFormatException(sb4.toString());
                }
                if (verifyChecksum(sb.toString(), bArr)) {
                    byte[] bArr3 = new byte[(bArr.length - 8)];
                    System.arraycopy(bArr, 0, bArr3, 0, bArr.length - 8);
                    return bArr3;
                }
                StringBuilder sb5 = new StringBuilder();
                sb5.append(str3);
                sb5.append(str);
                sb5.append(": Invalid Checksum ");
                throw new AddressFormatException(sb5.toString());
            } else {
                StringBuilder sb6 = new StringBuilder();
                sb6.append(str3);
                sb6.append(str);
                sb6.append(": Cannot contain both upper and lower case letters");
                throw new AddressFormatException(sb6.toString());
            }
        }
        StringBuilder sb32 = new StringBuilder();
        sb32.append(str3);
        sb32.append(str);
        sb32.append(": The separator cannot be the first character, cannot have number and there must not be 2 separators");
        throw new AddressFormatException(sb32.toString());
    }

    public static AddressVersionAndBytes decode(String str, String str2) {
        byte[] decodeCashAddress = decodeCashAddress(str2, str);
        AddressCashValidator.INSTANCE.checkNonEmptyPayload(decodeCashAddress);
        byte length = (byte) ((decodeCashAddress.length * 5) % 8);
        AddressCashValidator.INSTANCE.checkAllowedPadding(length);
        AddressCashValidator.INSTANCE.checkNonZeroPadding(decodeCashAddress[decodeCashAddress.length - 1], (byte) ((1 << length) - 1));
        byte[] bArr = new byte[((decodeCashAddress.length * 5) / 8)];
        convertBits(bArr, decodeCashAddress, 5, 8, false);
        byte b = bArr[0];
        AddressCashValidator.INSTANCE.checkFirstBitIsZero(b);
        AddressCashValidator.INSTANCE.checkDataLength(bArr, calculateHashSizeFromVersionByte(b));
        byte[] bArr2 = new byte[(bArr.length - 1)];
        System.arraycopy(bArr, 1, bArr2, 0, bArr.length - 1);
        return new AddressVersionAndBytes(b, bArr2);
    }

    public static byte[] packAddressData(byte[] bArr, byte b) {
        byte b2 = 3;
        byte b3 = (byte) (b << 3);
        int length = bArr.length;
        int i = length * 8;
        if (i == 160) {
            b2 = 0;
        } else if (i == 192) {
            b2 = 1;
        } else if (i == 224) {
            b2 = 2;
        } else if (i != 256) {
            if (i == 320) {
                b2 = 4;
            } else if (i == 384) {
                b2 = 5;
            } else if (i == 448) {
                b2 = 6;
            } else if (i == 512) {
                b2 = 7;
            } else {
                throw new AddressFormatException("Error packing cashaddr: invalid address length");
            }
        }
        byte b4 = (byte) (b3 | b2);
        byte[] bArr2 = new byte[(bArr.length + 1)];
        bArr2[0] = b4;
        System.arraycopy(bArr, 0, bArr2, 1, bArr.length);
        byte[] bArr3 = new byte[((((length + 1) * 8) + 4) / 5)];
        convertBits(bArr3, bArr2, 8, 5, true);
        return bArr3;
    }

    public static boolean convertBits(byte[] bArr, byte[] bArr2, int i, int i2, boolean z) {
        int i3 = (1 << i2) - 1;
        int i4 = (1 << ((i + i2) - 1)) - 1;
        byte b = 0;
        int i5 = 0;
        int i6 = 0;
        for (byte b2 : bArr2) {
            b = ((b << i) | (b2 & 255)) & i4;
            i5 += i;
            while (i5 >= i2) {
                i5 -= i2;
                bArr[i6] = (byte) ((b >> i5) & i3);
                i6++;
            }
        }
        if (!z && i5 != 0) {
            return false;
        }
        if (z && i5 != 0) {
            bArr[i6] = (byte) ((b << (i2 - i5)) & i3);
        }
        return true;
    }
}
