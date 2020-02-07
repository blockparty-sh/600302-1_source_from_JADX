package com.yakivmospan.scytale;

import android.annotation.TargetApi;
import android.content.Context;
import android.security.KeyPairGeneratorSpec;
import android.security.keystore.KeyGenParameterSpec;
import androidx.annotation.NonNull;
import com.yakivmospan.scytale.KeyProps.Builder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStore.PasswordProtection;
import java.security.KeyStore.PrivateKeyEntry;
import java.security.KeyStore.SecretKeyEntry;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableEntryException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Calendar;
import java.util.Date;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.security.auth.x500.X500Principal;

public class Store extends ErrorHandler {
    private static final String DEFAULT_KEYSTORE_NAME = "keystore";
    private static final char[] DEFAULT_KEYSTORE_PASSWORD = BuildConfig.APPLICATION_ID.toCharArray();
    private static final String PROVIDER_ANDROID_KEY_STORE = "AndroidKeyStore";
    private static final String PROVIDER_BC = "BC";
    private KeyStore mAndroidKeyStore;
    private final Context mContext;
    private KeyStore mDefaultKeyStore;
    private final File mKeystoreFile;
    private String mKeystoreName = DEFAULT_KEYSTORE_NAME;
    private char[] mKeystorePassword = DEFAULT_KEYSTORE_PASSWORD;

    public /* bridge */ /* synthetic */ void setErrorListener(ErrorListener errorListener) {
        super.setErrorListener(errorListener);
    }

    public Store(@NonNull Context context) {
        this.mContext = context;
        this.mKeystoreFile = new File(this.mContext.getFilesDir(), this.mKeystoreName);
    }

    public Store(@NonNull Context context, @NonNull String str, char[] cArr) {
        this.mContext = context;
        this.mKeystoreName = str;
        this.mKeystorePassword = cArr;
        this.mKeystoreFile = new File(this.mContext.getFilesDir(), this.mKeystoreName);
    }

    public KeyPair generateAsymmetricKey(@NonNull String str, char[] cArr) {
        Calendar instance = Calendar.getInstance();
        Calendar instance2 = Calendar.getInstance();
        instance2.add(1, 20);
        Builder serialNumber = new Builder().setAlias(str).setPassword(cArr).setKeySize(1024).setKeyType(Options.ALGORITHM_RSA).setSerialNumber(BigInteger.ONE);
        StringBuilder sb = new StringBuilder();
        sb.append("CN=");
        sb.append(str);
        sb.append(" CA Certificate");
        return generateAsymmetricKey(serialNumber.setSubject(new X500Principal(sb.toString())).setStartDate(instance.getTime()).setEndDate(instance2.getTime()).setBlockModes(Options.BLOCK_MODE_ECB).setEncryptionPaddings(Options.PADDING_PKCS_1).setSignatureAlgorithm(Options.ALGORITHM_SHA256_WITH_RSA_ENCRYPTION).build());
    }

    public KeyPair generateAsymmetricKey(@NonNull KeyProps keyProps) {
        if (C2652Utils.lowerThenJellyBean()) {
            return generateDefaultAsymmetricKey(keyProps);
        }
        if (C2652Utils.lowerThenMarshmallow()) {
            return generateAndroidJellyAsymmetricKey(keyProps);
        }
        return generateAndroidMAsymmetricKey(keyProps);
    }

    public SecretKey generateSymmetricKey(@NonNull String str, char[] cArr) {
        return generateSymmetricKey(new Builder().setAlias(str).setPassword(cArr).setKeySize(256).setKeyType(Options.ALGORITHM_AES).setBlockModes(Options.BLOCK_MODE_CBC).setEncryptionPaddings(Options.PADDING_PKCS_7).build());
    }

    public SecretKey generateSymmetricKey(@NonNull KeyProps keyProps) {
        if (C2652Utils.lowerThenMarshmallow()) {
            return generateDefaultSymmetricKey(keyProps);
        }
        return generateAndroidSymmetricKey(keyProps);
    }

    public KeyPair getAsymmetricKey(@NonNull String str, char[] cArr) {
        if (C2652Utils.lowerThenJellyBean()) {
            return getAsymmetricKeyFromDefaultKeyStore(str, cArr);
        }
        return getAsymmetricKeyFromAndroidKeyStore(str);
    }

    public SecretKey getSymmetricKey(@NonNull String str, char[] cArr) {
        if (C2652Utils.lowerThenMarshmallow()) {
            return getSymmetricKeyFromDefaultKeyStore(str, cArr);
        }
        return getSymmetricKeyFromAndroidtKeyStore(str);
    }

    public boolean hasKey(@NonNull String str) {
        boolean z = false;
        try {
            if (C2652Utils.lowerThenJellyBean()) {
                return isKeyEntry(str, createDefaultKeyStore());
            }
            if (!C2652Utils.lowerThenMarshmallow()) {
                return isKeyEntry(str, createAndroidKeystore());
            }
            z = isKeyEntry(str, createAndroidKeystore());
            if (!z) {
                return isKeyEntry(str, createDefaultKeyStore());
            }
            return z;
        } catch (IOException | KeyStoreException | NoSuchAlgorithmException | CertificateException e) {
            onException(e);
        }
    }

    public void deleteKey(@NonNull String str) {
        try {
            if (C2652Utils.lowerThenJellyBean()) {
                deleteEntryFromDefaultKeystore(str, createDefaultKeyStore());
            } else if (C2652Utils.lowerThenMarshmallow()) {
                KeyStore createAndroidKeystore = createAndroidKeystore();
                if (isKeyEntry(str, createAndroidKeystore)) {
                    deleteEntryFromAndroidKeystore(str, createAndroidKeystore);
                    return;
                }
                KeyStore createDefaultKeyStore = createDefaultKeyStore();
                if (isKeyEntry(str, createDefaultKeyStore)) {
                    deleteEntryFromDefaultKeystore(str, createDefaultKeyStore);
                }
            } else {
                deleteEntryFromAndroidKeystore(str, createAndroidKeystore());
            }
        } catch (IOException | KeyStoreException | NoSuchAlgorithmException | CertificateException e) {
            onException(e);
        }
    }

    private boolean isKeyEntry(@NonNull String str, KeyStore keyStore) throws KeyStoreException {
        return keyStore != null && keyStore.isKeyEntry(str);
    }

    private void deleteEntryFromDefaultKeystore(@NonNull String str, KeyStore keyStore) throws KeyStoreException, IOException, CertificateException, NoSuchAlgorithmException {
        if (keyStore != null) {
            keyStore.deleteEntry(str);
            keyStore.store(new FileOutputStream(this.mKeystoreFile), this.mKeystorePassword);
        }
    }

    private void deleteEntryFromAndroidKeystore(@NonNull String str, KeyStore keyStore) throws KeyStoreException {
        if (keyStore != null) {
            keyStore.deleteEntry(str);
        }
    }

    private KeyPair generateDefaultAsymmetricKey(KeyProps keyProps) {
        try {
            KeyPair createAsymmetricKey = createAsymmetricKey(keyProps);
            PrivateKey privateKey = createAsymmetricKey.getPrivate();
            X509Certificate keyToCertificateReflection = keyToCertificateReflection(createAsymmetricKey, keyProps);
            KeyStore createDefaultKeyStore = createDefaultKeyStore();
            createDefaultKeyStore.setKeyEntry(keyProps.mAlias, privateKey, keyProps.mPassword, new Certificate[]{keyToCertificateReflection});
            createDefaultKeyStore.store(new FileOutputStream(this.mKeystoreFile), this.mKeystorePassword);
            return createAsymmetricKey;
        } catch (IOException | UnsupportedOperationException | KeyStoreException | NoSuchAlgorithmException | CertificateException e) {
            onException(e);
            return null;
        } catch (NoSuchMethodException e2) {
            onException(e2);
            return null;
        } catch (InvocationTargetException e3) {
            onException(e3);
            return null;
        } catch (InstantiationException e4) {
            onException(e4);
            return null;
        } catch (IllegalAccessException e5) {
            onException(e5);
            return null;
        }
    }

    @TargetApi(18)
    private KeyPair generateAndroidJellyAsymmetricKey(KeyProps keyProps) {
        try {
            return generateAndroidAsymmetricKey(keyProps, keyPropsToKeyPairGeneratorSpec(keyProps));
        } catch (InvalidAlgorithmParameterException | NoSuchAlgorithmException | NoSuchProviderException e) {
            onException(e);
            return null;
        }
    }

    @TargetApi(23)
    private KeyPair generateAndroidMAsymmetricKey(KeyProps keyProps) {
        try {
            return generateAndroidAsymmetricKey(keyProps, keyPropsToKeyGenParameterASpec(keyProps));
        } catch (InvalidAlgorithmParameterException | NoSuchAlgorithmException | NoSuchProviderException e) {
            onException(e);
            return null;
        }
    }

    private KeyPair generateAndroidAsymmetricKey(KeyProps keyProps, AlgorithmParameterSpec algorithmParameterSpec) throws NoSuchProviderException, NoSuchAlgorithmException, InvalidAlgorithmParameterException {
        KeyPairGenerator instance = KeyPairGenerator.getInstance(keyProps.mKeyType, "AndroidKeyStore");
        instance.initialize(algorithmParameterSpec);
        return instance.generateKeyPair();
    }

    private KeyPair createAsymmetricKey(KeyProps keyProps) throws NoSuchAlgorithmException {
        KeyPairGenerator instance = KeyPairGenerator.getInstance(keyProps.mKeyType);
        instance.initialize(keyProps.mKeySize);
        return instance.generateKeyPair();
    }

    private SecretKey generateDefaultSymmetricKey(KeyProps keyProps) {
        try {
            SecretKey createSymmetricKey = createSymmetricKey(keyProps);
            SecretKeyEntry secretKeyEntry = new SecretKeyEntry(createSymmetricKey);
            KeyStore createDefaultKeyStore = createDefaultKeyStore();
            createDefaultKeyStore.setEntry(keyProps.mAlias, secretKeyEntry, new PasswordProtection(keyProps.mPassword));
            createDefaultKeyStore.store(new FileOutputStream(this.mKeystoreFile), this.mKeystorePassword);
            return createSymmetricKey;
        } catch (IOException | KeyStoreException | NoSuchAlgorithmException | CertificateException e) {
            onException(e);
            return null;
        }
    }

    @TargetApi(23)
    private SecretKey generateAndroidSymmetricKey(KeyProps keyProps) {
        try {
            KeyGenerator instance = KeyGenerator.getInstance(keyProps.mKeyType, "AndroidKeyStore");
            instance.init(keyPropsToKeyGenParameterSSpec(keyProps));
            return instance.generateKey();
        } catch (InvalidAlgorithmParameterException | NoSuchAlgorithmException | NoSuchProviderException e) {
            onException(e);
            return null;
        }
    }

    private X509Certificate keyToCertificateReflection(KeyPair keyPair, KeyProps keyProps) throws UnsupportedOperationException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class cls;
        try {
            cls = Class.forName("com.android.org.bouncycastle.x509.X509V3CertificateGenerator");
        } catch (ClassNotFoundException unused) {
            try {
                cls = Class.forName("org.bouncycastle.x509.X509V3CertificateGenerator");
            } catch (ClassNotFoundException unused2) {
                StringBuilder sb = new StringBuilder();
                sb.append("You need to include  http://www.bouncycastle.org/ library to generate KeyPair on ");
                sb.append(C2652Utils.VERSION);
                sb.append(" API version. You can do this via gradle using command 'compile 'org.bouncycastle:bcprov-jdk15on:1.54'");
                throw new UnsupportedOperationException(sb.toString());
            }
        }
        return keyToCertificateReflection(cls, keyPair, keyProps);
    }

    private X509Certificate keyToCertificateReflection(Class cls, KeyPair keyPair, KeyProps keyProps) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Object newInstance = cls.newInstance();
        newInstance.getClass().getMethod("setPublicKey", new Class[]{PublicKey.class}).invoke(newInstance, new Object[]{keyPair.getPublic()});
        newInstance.getClass().getMethod("setSerialNumber", new Class[]{BigInteger.class}).invoke(newInstance, new Object[]{keyProps.mSerialNumber});
        newInstance.getClass().getMethod("setSubjectDN", new Class[]{X500Principal.class}).invoke(newInstance, new Object[]{keyProps.mSubject});
        newInstance.getClass().getMethod("setIssuerDN", new Class[]{X500Principal.class}).invoke(newInstance, new Object[]{keyProps.mSubject});
        newInstance.getClass().getMethod("setNotBefore", new Class[]{Date.class}).invoke(newInstance, new Object[]{keyProps.mStartDate});
        newInstance.getClass().getMethod("setNotAfter", new Class[]{Date.class}).invoke(newInstance, new Object[]{keyProps.mEndDate});
        newInstance.getClass().getMethod("setSignatureAlgorithm", new Class[]{String.class}).invoke(newInstance, new Object[]{keyProps.mSignatureAlgorithm});
        return (X509Certificate) newInstance.getClass().getMethod("generate", new Class[]{PrivateKey.class, String.class}).invoke(newInstance, new Object[]{keyPair.getPrivate(), PROVIDER_BC});
    }

    @TargetApi(19)
    private KeyPairGeneratorSpec keyPropsToKeyPairGeneratorSpec(KeyProps keyProps) throws NoSuchAlgorithmException {
        KeyPairGeneratorSpec.Builder endDate = new KeyPairGeneratorSpec.Builder(this.mContext).setAlias(keyProps.mAlias).setSerialNumber(keyProps.mSerialNumber).setSubject(keyProps.mSubject).setStartDate(keyProps.mStartDate).setEndDate(keyProps.mEndDate);
        if (C2652Utils.biggerThenJellyBean()) {
            endDate.setKeySize(keyProps.mKeySize);
        }
        return endDate.build();
    }

    @TargetApi(23)
    private KeyGenParameterSpec keyPropsToKeyGenParameterASpec(KeyProps keyProps) throws NoSuchAlgorithmException {
        return new KeyGenParameterSpec.Builder(keyProps.mAlias, 3).setKeySize(keyProps.mKeySize).setCertificateSerialNumber(keyProps.mSerialNumber).setCertificateSubject(keyProps.mSubject).setCertificateNotBefore(keyProps.mStartDate).setCertificateNotAfter(keyProps.mEndDate).setBlockModes(new String[]{keyProps.mBlockModes}).setEncryptionPaddings(new String[]{keyProps.mEncryptionPaddings}).build();
    }

    @TargetApi(23)
    private KeyGenParameterSpec keyPropsToKeyGenParameterSSpec(KeyProps keyProps) throws NoSuchAlgorithmException {
        return new KeyGenParameterSpec.Builder(keyProps.mAlias, 3).setKeySize(keyProps.mKeySize).setBlockModes(new String[]{keyProps.mBlockModes}).setEncryptionPaddings(new String[]{keyProps.mEncryptionPaddings}).build();
    }

    private SecretKey createSymmetricKey(KeyProps keyProps) throws NoSuchAlgorithmException {
        KeyGenerator instance = KeyGenerator.getInstance(keyProps.mKeyType);
        instance.init(keyProps.mKeySize);
        return instance.generateKey();
    }

    private KeyPair getAsymmetricKeyFromDefaultKeyStore(@NonNull String str, char[] cArr) {
        try {
            PrivateKeyEntry privateKeyEntry = (PrivateKeyEntry) createDefaultKeyStore().getEntry(str, new PasswordProtection(cArr));
            if (privateKeyEntry != null) {
                return new KeyPair(privateKeyEntry.getCertificate().getPublicKey(), privateKeyEntry.getPrivateKey());
            }
            return null;
        } catch (IOException | KeyStoreException | NoSuchAlgorithmException | UnrecoverableEntryException | CertificateException e) {
            onException(e);
            return null;
        }
    }

    private KeyPair getAsymmetricKeyFromAndroidKeyStore(@NonNull String str) {
        try {
            KeyStore createAndroidKeystore = createAndroidKeystore();
            PrivateKey privateKey = (PrivateKey) createAndroidKeystore.getKey(str, null);
            if (privateKey != null) {
                return new KeyPair(createAndroidKeystore.getCertificate(str).getPublicKey(), privateKey);
            }
            return null;
        } catch (IOException | KeyStoreException | NoSuchAlgorithmException | UnrecoverableEntryException | CertificateException e) {
            onException(e);
            return null;
        }
    }

    private SecretKey getSymmetricKeyFromDefaultKeyStore(@NonNull String str, char[] cArr) {
        try {
            return (SecretKey) createDefaultKeyStore().getKey(str, cArr);
        } catch (IOException | KeyStoreException | NoSuchAlgorithmException | UnrecoverableEntryException | CertificateException e) {
            onException(e);
            return null;
        }
    }

    private SecretKey getSymmetricKeyFromAndroidtKeyStore(@NonNull String str) {
        try {
            return (SecretKey) createAndroidKeystore().getKey(str, null);
        } catch (IOException | KeyStoreException | NoSuchAlgorithmException | UnrecoverableEntryException | CertificateException e) {
            onException(e);
            return null;
        }
    }

    private KeyStore createDefaultKeyStore() throws KeyStoreException, CertificateException, NoSuchAlgorithmException, IOException {
        if (this.mDefaultKeyStore == null) {
            this.mDefaultKeyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            if (!this.mKeystoreFile.exists()) {
                this.mDefaultKeyStore.load(null);
            } else {
                this.mDefaultKeyStore.load(new FileInputStream(this.mKeystoreFile), this.mKeystorePassword);
            }
        }
        return this.mDefaultKeyStore;
    }

    private KeyStore createAndroidKeystore() throws KeyStoreException, CertificateException, NoSuchAlgorithmException, IOException {
        if (this.mAndroidKeyStore == null) {
            this.mAndroidKeyStore = KeyStore.getInstance("AndroidKeyStore");
        }
        this.mAndroidKeyStore.load(null);
        return this.mAndroidKeyStore;
    }
}
