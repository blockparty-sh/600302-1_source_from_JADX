package com.guardanis.applock.utils;

import com.facebook.stetho.common.Utf8Charset;
import com.google.common.base.Ascii;
import java.security.MessageDigest;

public class CryptoUtils {
    public static String encryptSha1(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance("SHA-1");
            instance.update(str.getBytes(Utf8Charset.NAME), 0, str.length());
            return convertToHex(instance.digest());
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private static String convertToHex(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            byte b2 = (b >>> 4) & Ascii.f528SI;
            int i = 0;
            while (true) {
                sb.append((char) ((b2 < 0 || b2 > 9) ? (b2 - 10) + 97 : b2 + 48));
                b2 = b & Ascii.f528SI;
                int i2 = i + 1;
                if (i >= 1) {
                    break;
                }
                i = i2;
            }
        }
        return sb.toString();
    }
}
