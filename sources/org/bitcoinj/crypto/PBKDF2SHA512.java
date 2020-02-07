package org.bitcoinj.crypto;

import com.facebook.stetho.common.Utf8Charset;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class PBKDF2SHA512 {
    public static byte[] derive(String str, String str2, int i, int i2) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        double d = (double) i2;
        try {
            double d2 = (double) 20;
            if (d <= (Math.pow(2.0d, 32.0d) - 1.0d) * d2) {
                int ceil = (int) Math.ceil(d / d2);
                for (int i3 = 1; i3 <= ceil; i3++) {
                    byteArrayOutputStream.write(m340F(str, str2, i, i3));
                }
                byte[] bArr = new byte[i2];
                System.arraycopy(byteArrayOutputStream.toByteArray(), 0, bArr, 0, bArr.length);
                return bArr;
            }
            throw new IllegalArgumentException("derived key too long");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /* renamed from: F */
    private static byte[] m340F(String str, String str2, int i, int i2) throws Exception {
        String str3 = Utf8Charset.NAME;
        SecretKeySpec secretKeySpec = new SecretKeySpec(str.getBytes(str3), "HmacSHA512");
        Mac instance = Mac.getInstance(secretKeySpec.getAlgorithm());
        instance.init(secretKeySpec);
        byte[] bArr = null;
        byte[] bArr2 = null;
        for (int i3 = 0; i3 < i; i3++) {
            if (i3 == 0) {
                byte[] bytes = str2.getBytes(str3);
                byte[] INT = INT(i2);
                byte[] bArr3 = new byte[(bytes.length + INT.length)];
                System.arraycopy(bytes, 0, bArr3, 0, bytes.length);
                System.arraycopy(INT, 0, bArr3, bytes.length, INT.length);
                bArr = instance.doFinal(bArr3);
                instance.reset();
                bArr2 = bArr;
            } else {
                bArr2 = instance.doFinal(bArr2);
                instance.reset();
                for (int i4 = 0; i4 < bArr.length; i4++) {
                    bArr[i4] = (byte) (bArr[i4] ^ bArr2[i4]);
                }
            }
        }
        return bArr;
    }

    private static byte[] INT(int i) {
        ByteBuffer allocate = ByteBuffer.allocate(4);
        allocate.order(ByteOrder.BIG_ENDIAN);
        allocate.putInt(i);
        return allocate.array();
    }
}
