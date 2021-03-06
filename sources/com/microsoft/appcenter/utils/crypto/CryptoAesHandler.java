package com.microsoft.appcenter.utils.crypto;

import android.content.Context;
import android.security.keystore.KeyGenParameterSpec.Builder;
import androidx.annotation.RequiresApi;
import com.bitcoin.mwallet.core.services.securestorage.SecureStorageKt;
import com.yakivmospan.scytale.Options;
import java.security.KeyStore.Entry;
import java.security.KeyStore.SecretKeyEntry;
import java.util.Calendar;
import javax.crypto.spec.IvParameterSpec;

@RequiresApi(23)
class CryptoAesHandler implements CryptoHandler {
    public String getAlgorithm() {
        return "AES/CBC/PKCS7Padding/256";
    }

    CryptoAesHandler() {
    }

    public void generateKey(ICryptoFactory iCryptoFactory, String str, Context context) throws Exception {
        Calendar instance = Calendar.getInstance();
        instance.add(1, 1);
        IKeyGenerator keyGenerator = iCryptoFactory.getKeyGenerator(Options.ALGORITHM_AES, SecureStorageKt.ANDROID_KEY_STORE);
        keyGenerator.init(new Builder(str, 3).setBlockModes(new String[]{Options.BLOCK_MODE_CBC}).setEncryptionPaddings(new String[]{Options.PADDING_PKCS_7}).setKeySize(256).setKeyValidityForOriginationEnd(instance.getTime()).build());
        keyGenerator.generateKey();
    }

    public byte[] encrypt(ICryptoFactory iCryptoFactory, int i, Entry entry, byte[] bArr) throws Exception {
        ICipher cipher = iCryptoFactory.getCipher(Options.AES_CBC_PKCS7PADDING, "AndroidKeyStoreBCWorkaround");
        cipher.init(1, ((SecretKeyEntry) entry).getSecretKey());
        byte[] iv = cipher.getIV();
        byte[] doFinal = cipher.doFinal(bArr);
        byte[] bArr2 = new byte[(iv.length + doFinal.length)];
        System.arraycopy(iv, 0, bArr2, 0, iv.length);
        System.arraycopy(doFinal, 0, bArr2, iv.length, doFinal.length);
        return bArr2;
    }

    public byte[] decrypt(ICryptoFactory iCryptoFactory, int i, Entry entry, byte[] bArr) throws Exception {
        ICipher cipher = iCryptoFactory.getCipher(Options.AES_CBC_PKCS7PADDING, "AndroidKeyStoreBCWorkaround");
        int blockSize = cipher.getBlockSize();
        cipher.init(2, ((SecretKeyEntry) entry).getSecretKey(), new IvParameterSpec(bArr, 0, blockSize));
        return cipher.doFinal(bArr, blockSize, bArr.length - blockSize);
    }
}
