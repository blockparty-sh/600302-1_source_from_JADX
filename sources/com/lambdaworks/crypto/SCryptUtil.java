package com.lambdaworks.crypto;

import com.facebook.stetho.common.Utf8Charset;
import com.lambdaworks.codec.Base64;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import kotlin.text.Typography;

public class SCryptUtil {
    private static int log2(int i) {
        int i2;
        if ((-65536 & i) != 0) {
            i >>>= 16;
            i2 = 16;
        } else {
            i2 = 0;
        }
        if (i >= 256) {
            i >>>= 8;
            i2 += 8;
        }
        if (i >= 16) {
            i >>>= 4;
            i2 += 4;
        }
        if (i >= 4) {
            i >>>= 2;
            i2 += 2;
        }
        return i2 + (i >>> 1);
    }

    public static String scrypt(String str, int i, int i2, int i3) {
        try {
            byte[] bArr = new byte[16];
            SecureRandom.getInstance("SHA1PRNG").nextBytes(bArr);
            byte[] scrypt = SCrypt.scrypt(str.getBytes(Utf8Charset.NAME), bArr, i, i2, i3, 32);
            String l = Long.toString((long) ((log2(i) << 16) | (i2 << 8) | i3), 16);
            StringBuilder sb = new StringBuilder((bArr.length + scrypt.length) * 2);
            sb.append("$s0$");
            sb.append(l);
            sb.append(Typography.dollar);
            sb.append(Base64.encode(bArr));
            sb.append(Typography.dollar);
            sb.append(Base64.encode(scrypt));
            return sb.toString();
        } catch (UnsupportedEncodingException unused) {
            throw new IllegalStateException("JVM doesn't support UTF-8?");
        } catch (GeneralSecurityException unused2) {
            throw new IllegalStateException("JVM doesn't support SHA1PRNG or HMAC_SHA256?");
        }
    }

    public static boolean check(String str, String str2) {
        try {
            String[] split = str2.split("\\$");
            if (split.length == 5) {
                boolean z = true;
                if (split[1].equals("s0")) {
                    long parseLong = Long.parseLong(split[2], 16);
                    byte[] decode = Base64.decode(split[3].toCharArray());
                    byte[] decode2 = Base64.decode(split[4].toCharArray());
                    int i = (int) parseLong;
                    byte[] scrypt = SCrypt.scrypt(str.getBytes(Utf8Charset.NAME), decode, (int) Math.pow(2.0d, (double) ((parseLong >> 16) & 65535)), (i >> 8) & 255, i & 255, 32);
                    if (decode2.length != scrypt.length) {
                        return false;
                    }
                    byte b = 0;
                    for (int i2 = 0; i2 < decode2.length; i2++) {
                        b |= decode2[i2] ^ scrypt[i2];
                    }
                    if (b != 0) {
                        z = false;
                    }
                    return z;
                }
            }
            throw new IllegalArgumentException("Invalid hashed value");
        } catch (UnsupportedEncodingException unused) {
            throw new IllegalStateException("JVM doesn't support UTF-8?");
        } catch (GeneralSecurityException unused2) {
            throw new IllegalStateException("JVM doesn't support SHA1PRNG or HMAC_SHA256?");
        }
    }
}
