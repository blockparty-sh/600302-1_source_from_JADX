package com.microsoft.appcenter.utils.crypto;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build.VERSION;
import android.util.Base64;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.facebook.stetho.common.Utf8Charset;
import com.microsoft.appcenter.utils.AppCenterLog;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStore.Entry;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Date;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

public class CryptoUtils {
    @VisibleForTesting
    static final ICryptoFactory DEFAULT_CRYPTO_FACTORY = new ICryptoFactory() {
        public IKeyGenerator getKeyGenerator(String str, String str2) throws Exception {
            final KeyGenerator instance = KeyGenerator.getInstance(str, str2);
            return new IKeyGenerator() {
                public void init(AlgorithmParameterSpec algorithmParameterSpec) throws Exception {
                    instance.init(algorithmParameterSpec);
                }

                public void generateKey() {
                    instance.generateKey();
                }
            };
        }

        public ICipher getCipher(String str, String str2) throws Exception {
            final Cipher instance = Cipher.getInstance(str, str2);
            return new ICipher() {
                public void init(int i, Key key) throws Exception {
                    instance.init(i, key);
                }

                public void init(int i, Key key, AlgorithmParameterSpec algorithmParameterSpec) throws Exception {
                    instance.init(i, key, algorithmParameterSpec);
                }

                public byte[] doFinal(byte[] bArr) throws Exception {
                    return instance.doFinal(bArr);
                }

                public byte[] doFinal(byte[] bArr, int i, int i2) throws Exception {
                    return instance.doFinal(bArr, i, i2);
                }

                public byte[] getIV() {
                    return instance.getIV();
                }

                public int getBlockSize() {
                    return instance.getBlockSize();
                }

                public String getAlgorithm() {
                    return instance.getAlgorithm();
                }

                public String getProvider() {
                    return instance.getProvider().getName();
                }
            };
        }
    };
    @SuppressLint({"StaticFieldLeak"})
    private static CryptoUtils sInstance;
    private final int mApiLevel;
    private final Context mContext;
    private final ICryptoFactory mCryptoFactory;
    @VisibleForTesting
    final Map<String, CryptoHandlerEntry> mCryptoHandlers;
    private final KeyStore mKeyStore;

    @VisibleForTesting
    static class CryptoHandlerEntry {
        int mAliasIndex;
        final int mAliasIndexMC;
        final CryptoHandler mCryptoHandler;

        CryptoHandlerEntry(int i, int i2, CryptoHandler cryptoHandler) {
            this.mAliasIndex = i;
            this.mAliasIndexMC = i2;
            this.mCryptoHandler = cryptoHandler;
        }
    }

    public static class DecryptedData {
        final String mDecryptedData;
        final String mNewEncryptedData;

        @VisibleForTesting
        public DecryptedData(String str, String str2) {
            this.mDecryptedData = str;
            this.mNewEncryptedData = str2;
        }

        public String getDecryptedData() {
            return this.mDecryptedData;
        }

        public String getNewEncryptedData() {
            return this.mNewEncryptedData;
        }
    }

    interface ICipher {
        byte[] doFinal(byte[] bArr) throws Exception;

        byte[] doFinal(byte[] bArr, int i, int i2) throws Exception;

        @VisibleForTesting
        String getAlgorithm();

        int getBlockSize();

        byte[] getIV();

        @VisibleForTesting
        String getProvider();

        void init(int i, Key key) throws Exception;

        void init(int i, Key key, AlgorithmParameterSpec algorithmParameterSpec) throws Exception;
    }

    interface ICryptoFactory {
        ICipher getCipher(String str, String str2) throws Exception;

        IKeyGenerator getKeyGenerator(String str, String str2) throws Exception;
    }

    interface IKeyGenerator {
        void generateKey();

        void init(AlgorithmParameterSpec algorithmParameterSpec) throws Exception;
    }

    private CryptoUtils(@NonNull Context context) {
        this(context, DEFAULT_CRYPTO_FACTORY, VERSION.SDK_INT);
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0044 A[SYNTHETIC, Splitter:B:21:0x0044] */
    @androidx.annotation.VisibleForTesting
    @android.annotation.TargetApi(23)
    /* Code decompiled incorrectly, please refer to instructions dump. */
    CryptoUtils(@androidx.annotation.NonNull android.content.Context r3, @androidx.annotation.NonNull com.microsoft.appcenter.utils.crypto.CryptoUtils.ICryptoFactory r4, int r5) {
        /*
            r2 = this;
            r2.<init>()
            java.util.LinkedHashMap r0 = new java.util.LinkedHashMap
            r0.<init>()
            r2.mCryptoHandlers = r0
            android.content.Context r3 = r3.getApplicationContext()
            r2.mContext = r3
            r2.mCryptoFactory = r4
            r2.mApiLevel = r5
            r3 = 0
            java.lang.String r4 = "AppCenter"
            r0 = 19
            if (r5 < r0) goto L_0x002c
            java.lang.String r0 = "AndroidKeyStore"
            java.security.KeyStore r0 = java.security.KeyStore.getInstance(r0)     // Catch:{ Exception -> 0x0027 }
            r0.load(r3)     // Catch:{ Exception -> 0x0026 }
            r3 = r0
            goto L_0x002c
        L_0x0026:
            r3 = r0
        L_0x0027:
            java.lang.String r0 = "Cannot use secure keystore on this device."
            com.microsoft.appcenter.utils.AppCenterLog.error(r4, r0)
        L_0x002c:
            r2.mKeyStore = r3
            if (r3 == 0) goto L_0x0042
            r0 = 23
            if (r5 < r0) goto L_0x0042
            com.microsoft.appcenter.utils.crypto.CryptoAesHandler r5 = new com.microsoft.appcenter.utils.crypto.CryptoAesHandler     // Catch:{ Exception -> 0x003d }
            r5.<init>()     // Catch:{ Exception -> 0x003d }
            r2.registerHandler(r5)     // Catch:{ Exception -> 0x003d }
            goto L_0x0042
        L_0x003d:
            java.lang.String r5 = "Cannot use modern encryption on this device."
            com.microsoft.appcenter.utils.AppCenterLog.error(r4, r5)
        L_0x0042:
            if (r3 == 0) goto L_0x0052
            com.microsoft.appcenter.utils.crypto.CryptoRsaHandler r3 = new com.microsoft.appcenter.utils.crypto.CryptoRsaHandler     // Catch:{ Exception -> 0x004d }
            r3.<init>()     // Catch:{ Exception -> 0x004d }
            r2.registerHandler(r3)     // Catch:{ Exception -> 0x004d }
            goto L_0x0052
        L_0x004d:
            java.lang.String r3 = "Cannot use old encryption on this device."
            com.microsoft.appcenter.utils.AppCenterLog.error(r4, r3)
        L_0x0052:
            com.microsoft.appcenter.utils.crypto.CryptoNoOpHandler r3 = new com.microsoft.appcenter.utils.crypto.CryptoNoOpHandler
            r3.<init>()
            java.util.Map<java.lang.String, com.microsoft.appcenter.utils.crypto.CryptoUtils$CryptoHandlerEntry> r4 = r2.mCryptoHandlers
            java.lang.String r5 = r3.getAlgorithm()
            com.microsoft.appcenter.utils.crypto.CryptoUtils$CryptoHandlerEntry r0 = new com.microsoft.appcenter.utils.crypto.CryptoUtils$CryptoHandlerEntry
            r1 = 0
            r0.<init>(r1, r1, r3)
            r4.put(r5, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.microsoft.appcenter.utils.crypto.CryptoUtils.<init>(android.content.Context, com.microsoft.appcenter.utils.crypto.CryptoUtils$ICryptoFactory, int):void");
    }

    public static CryptoUtils getInstance(@NonNull Context context) {
        if (sInstance == null) {
            sInstance = new CryptoUtils(context);
        }
        return sInstance;
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public ICryptoFactory getCryptoFactory() {
        return this.mCryptoFactory;
    }

    private void registerHandler(@NonNull CryptoHandler cryptoHandler) throws Exception {
        int i;
        int i2 = 0;
        String alias = getAlias(cryptoHandler, 0, false);
        String alias2 = getAlias(cryptoHandler, 1, false);
        String alias3 = getAlias(cryptoHandler, 0, true);
        String alias4 = getAlias(cryptoHandler, 1, true);
        Date creationDate = this.mKeyStore.getCreationDate(alias);
        Date creationDate2 = this.mKeyStore.getCreationDate(alias2);
        Date creationDate3 = this.mKeyStore.getCreationDate(alias3);
        Date creationDate4 = this.mKeyStore.getCreationDate(alias4);
        if (creationDate2 == null || !creationDate2.after(creationDate)) {
            i = 0;
        } else {
            alias = alias2;
            i = 1;
        }
        if (creationDate4 != null && creationDate4.after(creationDate3)) {
            i2 = 1;
        }
        String str = "AppCenter";
        if (this.mCryptoHandlers.isEmpty() && !this.mKeyStore.containsAlias(alias)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Creating alias: ");
            sb.append(alias);
            AppCenterLog.debug(str, sb.toString());
            cryptoHandler.generateKey(this.mCryptoFactory, alias, this.mContext);
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Using ");
        sb2.append(alias);
        AppCenterLog.debug(str, sb2.toString());
        this.mCryptoHandlers.put(cryptoHandler.getAlgorithm(), new CryptoHandlerEntry(i, i2, cryptoHandler));
    }

    @NonNull
    private String getAlias(@NonNull CryptoHandler cryptoHandler, int i, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append(z ? "mobile.center" : "appcenter");
        String str = ".";
        sb.append(str);
        sb.append(i);
        sb.append(str);
        sb.append(cryptoHandler.getAlgorithm());
        return sb.toString();
    }

    @Nullable
    private Entry getKeyStoreEntry(@NonNull CryptoHandlerEntry cryptoHandlerEntry) throws Exception {
        return getKeyStoreEntry(cryptoHandlerEntry.mCryptoHandler, cryptoHandlerEntry.mAliasIndex, false);
    }

    @Nullable
    private Entry getKeyStoreEntry(CryptoHandler cryptoHandler, int i, boolean z) throws Exception {
        if (this.mKeyStore == null) {
            return null;
        }
        return this.mKeyStore.getEntry(getAlias(cryptoHandler, i, z), null);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:10|11|(1:13)|14|15) */
    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        r4 = new java.lang.StringBuilder();
        r4.append("Alias expired: ");
        r4.append(r1.mAliasIndex);
        com.microsoft.appcenter.utils.AppCenterLog.debug(r0, r4.toString());
        r1.mAliasIndex ^= 1;
        r1 = getAlias(r2, r1.mAliasIndex, false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0070, code lost:
        if (r8.mKeyStore.containsAlias(r1) != false) goto L_0x0072;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0072, code lost:
        r3 = new java.lang.StringBuilder();
        r3.append("Deleting alias: ");
        r3.append(r1);
        com.microsoft.appcenter.utils.AppCenterLog.debug(r0, r3.toString());
        r8.mKeyStore.deleteEntry(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x008b, code lost:
        r3 = new java.lang.StringBuilder();
        r3.append("Creating alias: ");
        r3.append(r1);
        com.microsoft.appcenter.utils.AppCenterLog.debug(r0, r3.toString());
        r2.generateKey(r8.mCryptoFactory, r1, r8.mContext);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x00aa, code lost:
        return encrypt(r9);
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:10:0x0048 */
    @androidx.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String encrypt(@androidx.annotation.Nullable java.lang.String r9) {
        /*
            r8 = this;
            java.lang.String r0 = "AppCenter"
            if (r9 != 0) goto L_0x0006
            r9 = 0
            return r9
        L_0x0006:
            java.util.Map<java.lang.String, com.microsoft.appcenter.utils.crypto.CryptoUtils$CryptoHandlerEntry> r1 = r8.mCryptoHandlers     // Catch:{ Exception -> 0x00ab }
            java.util.Collection r1 = r1.values()     // Catch:{ Exception -> 0x00ab }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ Exception -> 0x00ab }
            java.lang.Object r1 = r1.next()     // Catch:{ Exception -> 0x00ab }
            com.microsoft.appcenter.utils.crypto.CryptoUtils$CryptoHandlerEntry r1 = (com.microsoft.appcenter.utils.crypto.CryptoUtils.CryptoHandlerEntry) r1     // Catch:{ Exception -> 0x00ab }
            com.microsoft.appcenter.utils.crypto.CryptoHandler r2 = r1.mCryptoHandler     // Catch:{ Exception -> 0x00ab }
            r3 = 0
            java.security.KeyStore$Entry r4 = r8.getKeyStoreEntry(r1)     // Catch:{ InvalidKeyException -> 0x0048 }
            com.microsoft.appcenter.utils.crypto.CryptoUtils$ICryptoFactory r5 = r8.mCryptoFactory     // Catch:{ InvalidKeyException -> 0x0048 }
            int r6 = r8.mApiLevel     // Catch:{ InvalidKeyException -> 0x0048 }
            java.lang.String r7 = "UTF-8"
            byte[] r7 = r9.getBytes(r7)     // Catch:{ InvalidKeyException -> 0x0048 }
            byte[] r4 = r2.encrypt(r5, r6, r4, r7)     // Catch:{ InvalidKeyException -> 0x0048 }
            java.lang.String r4 = android.util.Base64.encodeToString(r4, r3)     // Catch:{ InvalidKeyException -> 0x0048 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ InvalidKeyException -> 0x0048 }
            r5.<init>()     // Catch:{ InvalidKeyException -> 0x0048 }
            java.lang.String r6 = r2.getAlgorithm()     // Catch:{ InvalidKeyException -> 0x0048 }
            r5.append(r6)     // Catch:{ InvalidKeyException -> 0x0048 }
            java.lang.String r6 = ":"
            r5.append(r6)     // Catch:{ InvalidKeyException -> 0x0048 }
            r5.append(r4)     // Catch:{ InvalidKeyException -> 0x0048 }
            java.lang.String r9 = r5.toString()     // Catch:{ InvalidKeyException -> 0x0048 }
            return r9
        L_0x0048:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00ab }
            r4.<init>()     // Catch:{ Exception -> 0x00ab }
            java.lang.String r5 = "Alias expired: "
            r4.append(r5)     // Catch:{ Exception -> 0x00ab }
            int r5 = r1.mAliasIndex     // Catch:{ Exception -> 0x00ab }
            r4.append(r5)     // Catch:{ Exception -> 0x00ab }
            java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x00ab }
            com.microsoft.appcenter.utils.AppCenterLog.debug(r0, r4)     // Catch:{ Exception -> 0x00ab }
            int r4 = r1.mAliasIndex     // Catch:{ Exception -> 0x00ab }
            r4 = r4 ^ 1
            r1.mAliasIndex = r4     // Catch:{ Exception -> 0x00ab }
            int r1 = r1.mAliasIndex     // Catch:{ Exception -> 0x00ab }
            java.lang.String r1 = r8.getAlias(r2, r1, r3)     // Catch:{ Exception -> 0x00ab }
            java.security.KeyStore r3 = r8.mKeyStore     // Catch:{ Exception -> 0x00ab }
            boolean r3 = r3.containsAlias(r1)     // Catch:{ Exception -> 0x00ab }
            if (r3 == 0) goto L_0x008b
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00ab }
            r3.<init>()     // Catch:{ Exception -> 0x00ab }
            java.lang.String r4 = "Deleting alias: "
            r3.append(r4)     // Catch:{ Exception -> 0x00ab }
            r3.append(r1)     // Catch:{ Exception -> 0x00ab }
            java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x00ab }
            com.microsoft.appcenter.utils.AppCenterLog.debug(r0, r3)     // Catch:{ Exception -> 0x00ab }
            java.security.KeyStore r3 = r8.mKeyStore     // Catch:{ Exception -> 0x00ab }
            r3.deleteEntry(r1)     // Catch:{ Exception -> 0x00ab }
        L_0x008b:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00ab }
            r3.<init>()     // Catch:{ Exception -> 0x00ab }
            java.lang.String r4 = "Creating alias: "
            r3.append(r4)     // Catch:{ Exception -> 0x00ab }
            r3.append(r1)     // Catch:{ Exception -> 0x00ab }
            java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x00ab }
            com.microsoft.appcenter.utils.AppCenterLog.debug(r0, r3)     // Catch:{ Exception -> 0x00ab }
            com.microsoft.appcenter.utils.crypto.CryptoUtils$ICryptoFactory r3 = r8.mCryptoFactory     // Catch:{ Exception -> 0x00ab }
            android.content.Context r4 = r8.mContext     // Catch:{ Exception -> 0x00ab }
            r2.generateKey(r3, r1, r4)     // Catch:{ Exception -> 0x00ab }
            java.lang.String r9 = r8.encrypt(r9)     // Catch:{ Exception -> 0x00ab }
            return r9
        L_0x00ab:
            java.lang.String r1 = "Failed to encrypt data."
            com.microsoft.appcenter.utils.AppCenterLog.error(r0, r1)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.microsoft.appcenter.utils.crypto.CryptoUtils.encrypt(java.lang.String):java.lang.String");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0048, code lost:
        return getDecryptedData(r3, r2.mAliasIndex ^ 1, r1[1], r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0049, code lost:
        com.microsoft.appcenter.utils.AppCenterLog.error(r5, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0051, code lost:
        return new com.microsoft.appcenter.utils.crypto.CryptoUtils.DecryptedData(r10, null);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x003f */
    @androidx.annotation.NonNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.microsoft.appcenter.utils.crypto.CryptoUtils.DecryptedData decrypt(@androidx.annotation.Nullable java.lang.String r10, boolean r11) {
        /*
            r9 = this;
            r0 = 0
            if (r10 != 0) goto L_0x0009
            com.microsoft.appcenter.utils.crypto.CryptoUtils$DecryptedData r10 = new com.microsoft.appcenter.utils.crypto.CryptoUtils$DecryptedData
            r10.<init>(r0, r0)
            return r10
        L_0x0009:
            java.lang.String r1 = ":"
            java.lang.String[] r1 = r10.split(r1)
            int r2 = r1.length
            r3 = 2
            if (r2 != r3) goto L_0x001f
            java.util.Map<java.lang.String, com.microsoft.appcenter.utils.crypto.CryptoUtils$CryptoHandlerEntry> r2 = r9.mCryptoHandlers
            r3 = 0
            r3 = r1[r3]
            java.lang.Object r2 = r2.get(r3)
            com.microsoft.appcenter.utils.crypto.CryptoUtils$CryptoHandlerEntry r2 = (com.microsoft.appcenter.utils.crypto.CryptoUtils.CryptoHandlerEntry) r2
            goto L_0x0020
        L_0x001f:
            r2 = r0
        L_0x0020:
            if (r2 != 0) goto L_0x0024
            r3 = r0
            goto L_0x0026
        L_0x0024:
            com.microsoft.appcenter.utils.crypto.CryptoHandler r3 = r2.mCryptoHandler
        L_0x0026:
            java.lang.String r4 = "Failed to decrypt data."
            java.lang.String r5 = "AppCenter"
            if (r3 != 0) goto L_0x0035
            com.microsoft.appcenter.utils.AppCenterLog.error(r5, r4)
            com.microsoft.appcenter.utils.crypto.CryptoUtils$DecryptedData r11 = new com.microsoft.appcenter.utils.crypto.CryptoUtils$DecryptedData
            r11.<init>(r10, r0)
            return r11
        L_0x0035:
            r6 = 1
            int r7 = r2.mAliasIndex     // Catch:{ Exception -> 0x003f }
            r8 = r1[r6]     // Catch:{ Exception -> 0x003f }
            com.microsoft.appcenter.utils.crypto.CryptoUtils$DecryptedData r10 = r9.getDecryptedData(r3, r7, r8, r11)     // Catch:{ Exception -> 0x003f }
            return r10
        L_0x003f:
            int r2 = r2.mAliasIndex     // Catch:{ Exception -> 0x0049 }
            r2 = r2 ^ r6
            r1 = r1[r6]     // Catch:{ Exception -> 0x0049 }
            com.microsoft.appcenter.utils.crypto.CryptoUtils$DecryptedData r10 = r9.getDecryptedData(r3, r2, r1, r11)     // Catch:{ Exception -> 0x0049 }
            return r10
        L_0x0049:
            com.microsoft.appcenter.utils.AppCenterLog.error(r5, r4)
            com.microsoft.appcenter.utils.crypto.CryptoUtils$DecryptedData r11 = new com.microsoft.appcenter.utils.crypto.CryptoUtils$DecryptedData
            r11.<init>(r10, r0)
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.microsoft.appcenter.utils.crypto.CryptoUtils.decrypt(java.lang.String, boolean):com.microsoft.appcenter.utils.crypto.CryptoUtils$DecryptedData");
    }

    @NonNull
    private DecryptedData getDecryptedData(CryptoHandler cryptoHandler, int i, String str, boolean z) throws Exception {
        String str2 = new String(cryptoHandler.decrypt(this.mCryptoFactory, this.mApiLevel, getKeyStoreEntry(cryptoHandler, i, z), Base64.decode(str, 0)), Utf8Charset.NAME);
        return new DecryptedData(str2, cryptoHandler != ((CryptoHandlerEntry) this.mCryptoHandlers.values().iterator().next()).mCryptoHandler ? encrypt(str2) : null);
    }
}
