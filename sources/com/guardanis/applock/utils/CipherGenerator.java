package com.guardanis.applock.utils;

import android.content.Context;
import android.os.Build.VERSION;
import android.security.keystore.KeyGenParameterSpec.Builder;
import android.security.keystore.KeyPermanentlyInvalidatedException;
import androidx.annotation.RequiresApi;
import com.guardanis.applock.C2245R;
import com.yakivmospan.scytale.Options;
import java.security.KeyStore;
import java.security.UnrecoverableKeyException;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

@RequiresApi(api = 23)
public class CipherGenerator {
    private static final String KEYSTORE_NAME = "AndroidKeyStore";

    public Cipher generateAuthCipher(Context context, boolean z, int i) throws Exception {
        if (VERSION.SDK_INT >= 23) {
            String string = context.getString(C2245R.string.applock__fingerprint_alias);
            String str = "AndroidKeyStore";
            KeyStore instance = KeyStore.getInstance(str);
            instance.load(null);
            String str2 = Options.PADDING_PKCS_7;
            String str3 = Options.BLOCK_MODE_CBC;
            String str4 = Options.ALGORITHM_AES;
            if (z || !instance.containsAlias(string)) {
                KeyGenerator instance2 = KeyGenerator.getInstance(str4, str);
                instance2.init(new Builder(string, 3).setBlockModes(new String[]{str3}).setUserAuthenticationRequired(true).setEncryptionPaddings(new String[]{str2}).build());
                instance2.generateKey();
            }
            try {
                Cipher instance3 = Cipher.getInstance(String.format("%s/%s/%s", new Object[]{str4, str3, str2}));
                instance3.init(1, instance.getKey(string, null));
                return instance3;
            } catch (KeyPermanentlyInvalidatedException e) {
                e.printStackTrace();
                if (1 < i) {
                    return generateAuthCipher(context, true, i + 1);
                }
                return null;
            } catch (UnrecoverableKeyException e2) {
                e2.printStackTrace();
                if (1 < i) {
                    return generateAuthCipher(context, true, i + 1);
                }
                return null;
            }
        } else {
            throw new RuntimeException("generateAuthCipher() not supported before Api 23");
        }
    }
}
