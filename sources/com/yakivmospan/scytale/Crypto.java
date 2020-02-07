package com.yakivmospan.scytale;

import android.util.Base64;
import androidx.annotation.NonNull;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class Crypto extends ErrorHandler {
    private static final String IV_SEPARATOR = "]";
    private static final String UTF_8 = "UTF-8";
    private int mDecryptionBlockSize;
    private int mEncryptionBlockSize;
    private String mTransformation;

    public /* bridge */ /* synthetic */ void setErrorListener(ErrorListener errorListener) {
        super.setErrorListener(errorListener);
    }

    public Crypto(@NonNull String str) {
        this.mTransformation = str;
    }

    public Crypto(@NonNull String str, int i, int i2) {
        this.mTransformation = str;
        this.mEncryptionBlockSize = i;
        this.mDecryptionBlockSize = i2;
    }

    public String encrypt(@NonNull String str, @NonNull KeyPair keyPair) {
        return encrypt(str, keyPair.getPublic(), false);
    }

    public String encrypt(@NonNull String str, @NonNull SecretKey secretKey) {
        return encrypt(str, secretKey, true);
    }

    public String encrypt(@NonNull String str, @NonNull Key key, boolean z) {
        byte[] bArr;
        String str2 = "";
        try {
            Cipher instance = Cipher.getInstance(this.mTransformation == null ? key.getAlgorithm() : this.mTransformation);
            instance.init(1, key);
            if (z) {
                String encodeToString = Base64.encodeToString(instance.getIV(), 0);
                StringBuilder sb = new StringBuilder();
                sb.append(encodeToString);
                sb.append(IV_SEPARATOR);
                str2 = sb.toString();
            }
            byte[] bytes = str.getBytes("UTF-8");
            if (this.mEncryptionBlockSize == 0 && this.mDecryptionBlockSize == 0) {
                bArr = decode(instance, bytes);
            } else {
                bArr = decodeWithBuffer(instance, bytes, this.mEncryptionBlockSize);
            }
            String encodeToString2 = Base64.encodeToString(bArr, 0);
            StringBuilder sb2 = new StringBuilder();
            sb2.append(str2);
            sb2.append(encodeToString2);
            return sb2.toString();
        } catch (IOException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) {
            onException(e);
            return str2;
        }
    }

    public String decrypt(@NonNull String str, @NonNull KeyPair keyPair) {
        return decrypt(str, keyPair.getPrivate(), false);
    }

    public String decrypt(@NonNull String str, @NonNull SecretKey secretKey) {
        return decrypt(str, secretKey, true);
    }

    public String decrypt(@NonNull String str, @NonNull Key key, boolean z) {
        byte[] bArr;
        try {
            Cipher instance = Cipher.getInstance(this.mTransformation == null ? key.getAlgorithm() : this.mTransformation);
            if (z) {
                String[] split = str.split(IV_SEPARATOR);
                String str2 = split[0];
                str = split[1];
                instance.init(2, key, new IvParameterSpec(Base64.decode(str2, 0)));
            } else {
                instance.init(2, key);
            }
            byte[] decode = Base64.decode(str, 0);
            if (this.mEncryptionBlockSize == 0 && this.mDecryptionBlockSize == 0) {
                bArr = decode(instance, decode);
            } else {
                bArr = decodeWithBuffer(instance, decode, this.mDecryptionBlockSize);
            }
            return new String(bArr, "UTF-8");
        } catch (IOException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) {
            onException(e);
            return null;
        }
    }

    private byte[] decode(@NonNull Cipher cipher, @NonNull byte[] bArr) throws IOException, IllegalBlockSizeException, BadPaddingException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        CipherOutputStream cipherOutputStream = new CipherOutputStream(byteArrayOutputStream, cipher);
        cipherOutputStream.write(bArr);
        cipherOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }

    private byte[] decodeWithBuffer(@NonNull Cipher cipher, @NonNull byte[] bArr, int i) throws IllegalBlockSizeException, BadPaddingException {
        int i2 = 0;
        byte[] bArr2 = new byte[0];
        byte[] bArr3 = new byte[(bArr.length > i ? i : bArr.length)];
        while (i2 < bArr.length) {
            if (i2 > 0 && i2 % i == 0) {
                bArr2 = append(bArr2, cipher.doFinal(bArr3));
                bArr3 = new byte[(i2 + i > bArr.length ? bArr.length - i2 : i)];
            }
            bArr3[i2 % i] = bArr[i2];
            i2++;
        }
        return append(bArr2, cipher.doFinal(bArr3));
    }

    private byte[] append(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[(bArr.length + bArr2.length)];
        for (int i = 0; i < bArr.length; i++) {
            bArr3[i] = bArr[i];
        }
        for (int i2 = 0; i2 < bArr2.length; i2++) {
            bArr3[bArr.length + i2] = bArr2[i2];
        }
        return bArr3;
    }
}
