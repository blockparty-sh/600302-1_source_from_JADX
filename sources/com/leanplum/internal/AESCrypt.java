package com.leanplum.internal;

import android.content.SharedPreferences;
import android.util.Pair;
import com.facebook.stetho.common.Utf8Charset;
import com.leanplum.internal.Constants.Crypt;
import com.yakivmospan.scytale.Options;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class AESCrypt {
    private static final String APP_ID_KEY_PREFIX;
    private static final String APP_ID_KEY_SUFFIX;
    private final String appId;
    private final String token;

    /* renamed from: com.leanplum.internal.AESCrypt$1 */
    static /* synthetic */ class C23551 {
        static final /* synthetic */ int[] $SwitchMap$com$leanplum$internal$AESCrypt$EncryptionType = new int[EncryptionType.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        static {
            /*
                com.leanplum.internal.AESCrypt$EncryptionType[] r0 = com.leanplum.internal.AESCrypt.EncryptionType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$leanplum$internal$AESCrypt$EncryptionType = r0
                int[] r0 = $SwitchMap$com$leanplum$internal$AESCrypt$EncryptionType     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.leanplum.internal.AESCrypt$EncryptionType r1 = com.leanplum.internal.AESCrypt.EncryptionType.LEGACY_TOKEN     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = $SwitchMap$com$leanplum$internal$AESCrypt$EncryptionType     // Catch:{ NoSuchFieldError -> 0x001f }
                com.leanplum.internal.AESCrypt$EncryptionType r1 = com.leanplum.internal.AESCrypt.EncryptionType.APP_ID_KEY     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.leanplum.internal.AESCrypt.C23551.<clinit>():void");
        }
    }

    private enum EncryptionType {
        LEGACY_TOKEN(0),
        APP_ID_KEY(1);
        

        /* renamed from: id */
        public final int f644id;
        public final String prefix;
        public final String prefixWithBracket;

        private EncryptionType(int i) {
            this.f644id = i;
            this.prefix = String.format("%02d", new Object[]{Integer.valueOf(i)});
            StringBuilder sb = new StringBuilder();
            sb.append(this.prefix);
            sb.append("[");
            this.prefixWithBracket = sb.toString();
        }

        private static EncryptionType forId(int i) {
            if (i == 1) {
                return APP_ID_KEY;
            }
            return null;
        }

        public static Pair<EncryptionType, String> parseCipherText(String str) {
            if (str != null && !str.isEmpty()) {
                if (str.startsWith("[")) {
                    return Pair.create(LEGACY_TOKEN, str);
                }
                if (str.startsWith(APP_ID_KEY.prefixWithBracket)) {
                    EncryptionType encryptionType = APP_ID_KEY;
                    return Pair.create(encryptionType, str.substring(encryptionType.prefixWithBracket.length() - 1));
                }
            }
            return null;
        }
    }

    static {
        StringBuilder sb = new StringBuilder();
        String str = "L";
        sb.append(str);
        sb.append("q");
        sb.append(3);
        sb.append("f");
        sb.append("z");
        APP_ID_KEY_PREFIX = sb.toString();
        StringBuilder sb2 = new StringBuilder();
        sb2.append("b");
        sb2.append(str);
        sb2.append("t");
        sb2.append("i");
        sb2.append(2);
        APP_ID_KEY_SUFFIX = sb2.toString();
    }

    public AESCrypt(String str, String str2) {
        this.appId = str;
        this.token = str2;
    }

    private String appIdKeyPassword() {
        StringBuilder sb = new StringBuilder();
        sb.append(APP_ID_KEY_PREFIX);
        sb.append(this.appId);
        sb.append(APP_ID_KEY_SUFFIX);
        return sb.toString();
    }

    public String encrypt(String str) {
        if (str == null) {
            return null;
        }
        String str2 = this.appId;
        if (str2 == null || str2.isEmpty()) {
            Log.m280e("Encrypt called with null appId.");
            return null;
        }
        String encryptInternal = encryptInternal(appIdKeyPassword(), str);
        if (encryptInternal == null) {
            Log.m284w("Failed to encrypt.");
            return null;
        } else if (encryptInternal.isEmpty() || encryptInternal.equals(str) || !encryptInternal.startsWith("[")) {
            StringBuilder sb = new StringBuilder();
            sb.append("Invalid ciphertext: ");
            sb.append(encryptInternal);
            Log.m284w(sb.toString());
            return null;
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(EncryptionType.APP_ID_KEY.prefix);
            sb2.append(encryptInternal);
            return sb2.toString();
        }
    }

    public String decodePreference(SharedPreferences sharedPreferences, String str, String str2) {
        String string = sharedPreferences.getString(str, null);
        if (string == null) {
            return str2;
        }
        String decrypt = decrypt(string);
        return decrypt == null ? str2 : decrypt;
    }

    public String decrypt(String str) {
        Pair parseCipherText = EncryptionType.parseCipherText(str);
        String str2 = null;
        if (parseCipherText == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("Got null encryptionSpec for encrypted: ");
            sb.append(str);
            Log.m283v(sb.toString());
        } else {
            int i = C23551.$SwitchMap$com$leanplum$internal$AESCrypt$EncryptionType[((EncryptionType) parseCipherText.first).ordinal()];
            if (i == 1) {
                String str3 = this.token;
                if (str3 == null || str3.isEmpty()) {
                    Log.m280e("Decrypt called with null token.");
                } else {
                    String decryptInternal = decryptInternal(this.token, (String) parseCipherText.second);
                    if (decryptInternal == null || parseCiphertextInternal(decryptInternal) == null) {
                        str2 = decryptInternal;
                    } else {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("Discarding legacy value that appears to be an encrypted value: ");
                        sb2.append(decryptInternal);
                        Log.m280e(sb2.toString());
                        return null;
                    }
                }
            } else if (i == 2) {
                String str4 = this.appId;
                if (str4 == null || str4.isEmpty()) {
                    Log.m280e("Decrypt called with null appId.");
                } else {
                    str2 = decryptInternal(appIdKeyPassword(), (String) parseCipherText.second);
                }
            }
        }
        if (str2 == null) {
            StringBuilder sb3 = new StringBuilder();
            sb3.append("Unable to decrypt ");
            sb3.append(str);
            Log.m284w(sb3.toString());
        }
        return str2;
    }

    public static String encryptInternal(String str, String str2) {
        try {
            return Arrays.toString(performCryptOperation(1, str, str2.getBytes(Utf8Charset.NAME)));
        } catch (UnsupportedEncodingException e) {
            StringBuilder sb = new StringBuilder();
            sb.append("Unable to encrypt ");
            sb.append(str2);
            Log.m284w(sb.toString(), e);
            return null;
        }
    }

    private static byte[] parseCiphertextInternal(String str) {
        if (str == null) {
            return null;
        }
        String trim = str.trim();
        if (trim.length() < 2) {
            return null;
        }
        try {
            String[] split = trim.substring(1, trim.length() - 1).trim().split("\\s*,\\s*");
            byte[] bArr = new byte[split.length];
            for (int i = 0; i < split.length; i++) {
                bArr[i] = Byte.parseByte(split[i]);
            }
            return bArr;
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    private static String decryptInternal(String str, String str2) {
        try {
            byte[] parseCiphertextInternal = parseCiphertextInternal(str2);
            if (parseCiphertextInternal == null) {
                StringBuilder sb = new StringBuilder();
                sb.append("Invalid ciphertext: ");
                sb.append(str2);
                Log.m284w(sb.toString());
                return null;
            }
            byte[] performCryptOperation = performCryptOperation(2, str, parseCiphertextInternal);
            if (performCryptOperation != null) {
                return new String(performCryptOperation, Utf8Charset.NAME);
            }
            return null;
        } catch (UnsupportedEncodingException e) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Could not encode UTF8 string.\n");
            sb2.append(Log.getStackTraceString(e));
            Log.m284w(sb2.toString());
        }
    }

    private static byte[] performCryptOperation(int i, String str, byte[] bArr) {
        String str2 = Utf8Charset.NAME;
        try {
            byte[] bytes = Crypt.SALT.getBytes(str2);
            byte[] bytes2 = Crypt.f645IV.getBytes(str2);
            SecretKeySpec secretKeySpec = new SecretKeySpec(SecretKeyFactory.getInstance("PBEWithMD5And128BitAES-CBC-OpenSSL").generateSecret(new PBEKeySpec(str.toCharArray(), bytes, 1000, 256)).getEncoded(), Options.ALGORITHM_AES);
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
            instance.init(i, secretKeySpec, new IvParameterSpec(bytes2));
            return instance.doFinal(bArr);
        } catch (UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException unused) {
            return null;
        }
    }
}
