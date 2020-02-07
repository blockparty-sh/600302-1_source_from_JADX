package com.bitcoin.securepreferences;

import android.content.Context;
import android.os.Build.VERSION;
import android.security.KeyPairGeneratorSpec;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyGenParameterSpec.Builder;
import android.security.keystore.StrongBoxUnavailableException;
import android.util.Base64;
import android.util.Log;
import androidx.annotation.RequiresApi;
import com.leanplum.internal.Constants.Methods;
import com.yakivmospan.scytale.Options;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.util.Calendar;
import javax.crypto.Cipher;
import javax.security.auth.x500.X500Principal;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000D\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a \u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00012\u0006\u0010\u0011\u001a\u00020\u0012H\u0002\u001a\u0018\u0010\u0013\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u00012\u0006\u0010\u0011\u001a\u00020\u0012H\u0003\u001a \u0010\u0014\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00012\u0006\u0010\u0011\u001a\u00020\u0012H\u0002\u001a \u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00012\u0006\u0010\u0018\u001a\u00020\u00012\u0006\u0010\u0019\u001a\u00020\u0001H\u0002\u001a\u0018\u0010\u001a\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u0018\u001a\u00020\u0001H\u0000\u001a\u000e\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u0018\u001a\u00020\u0001\u001a \u0010\u001f\u001a\u00020 2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010!\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u0001H\u0002\u001a \u0010\"\u001a\u00020\u001c2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010!\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u0001H\u0000\u001a\u0010\u0010#\u001a\u00020\u00012\u0006\u0010\u0018\u001a\u00020\u0001H\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\t\u001a\u00020\nXT¢\u0006\u0002\n\u0000\"\u000e\u0010\u000b\u001a\u00020\nXT¢\u0006\u0002\n\u0000¨\u0006$"}, mo37405d2 = {"JSON_CIPHERTEXT", "", "JSON_VERSION", "KEY_ALIAS_SUFFIX", "KEY_PAIR_GENERATOR_ALGORITHM_RSA", "PROVIDER_ANDROID_KEY_STORE", "TAG", "VERSION_1_CIPHER", "VERSION_1_CIPHER_MODE", "VERSION_1_KEY_SIZE", "", "VERSION_1_VERSION", "createRsaKeyPair", "Ljava/security/KeyPair;", "context", "Landroid/content/Context;", "keyAlias", "rsaParams", "Lcom/bitcoin/securepreferences/RsaEncryptionParams;", "createRsaKeyPairWithKeyGenParameterSpec", "createRsaKeyPairWithKeyPairGeneratorSpec", "decryptUsingRsa", "", "ciphertextBase64", "namespace", "transformation", "decryptWithVersionUsingRsa", "json", "Lorg/json/JSONObject;", "deleteRsaEncryptionKeyFromKeyStoreIfExists", "", "encryptUsingRsa", "Lcom/bitcoin/securepreferences/RsaEncryptionResult;", "plaintextBytes", "encryptWithVersionUsingRsa", "keyAliasFromNamespace", "securepreferences_release"}, mo37406k = 2, mo37407mv = {1, 1, 13})
/* compiled from: rsa.kt */
public final class RsaKt {
    private static final String JSON_CIPHERTEXT = "ct";
    private static final String JSON_VERSION = "v";
    private static final String KEY_ALIAS_SUFFIX = ".rsa";
    private static final String KEY_PAIR_GENERATOR_ALGORITHM_RSA = "RSA";
    private static final String PROVIDER_ANDROID_KEY_STORE = "AndroidKeyStore";
    private static final String TAG = "rsa";
    private static final String VERSION_1_CIPHER = "RSA";
    private static final String VERSION_1_CIPHER_MODE = "NONE";
    private static final int VERSION_1_KEY_SIZE = 2048;
    private static final int VERSION_1_VERSION = 1;

    private static final KeyPair createRsaKeyPair(Context context, String str, RsaEncryptionParams rsaEncryptionParams) {
        if (VERSION.SDK_INT >= 23) {
            return createRsaKeyPairWithKeyGenParameterSpec(str, rsaEncryptionParams);
        }
        return createRsaKeyPairWithKeyPairGeneratorSpec(context, str, rsaEncryptionParams);
    }

    @RequiresApi(23)
    private static final KeyPair createRsaKeyPairWithKeyGenParameterSpec(String str, RsaEncryptionParams rsaEncryptionParams) {
        KeyPairGenerator instance = KeyPairGenerator.getInstance(Options.ALGORITHM_RSA, "AndroidKeyStore");
        Intrinsics.checkExpressionValueIsNotNull(instance, "KeyPairGenerator.getInst…R_ANDROID_KEY_STORE\n    )");
        Builder builder = new Builder(str, 2);
        builder.setEncryptionPaddings(new String[]{rsaEncryptionParams.getPadding()});
        if (VERSION.SDK_INT >= 28) {
            try {
                builder.setIsStrongBoxBacked(true);
            } catch (StrongBoxUnavailableException unused) {
            }
        }
        KeyGenParameterSpec build = builder.build();
        Intrinsics.checkExpressionValueIsNotNull(build, "keySpecBuilder.build()");
        instance.initialize(build);
        KeyPair generateKeyPair = instance.generateKeyPair();
        Intrinsics.checkExpressionValueIsNotNull(generateKeyPair, "keyPairGenerator.generateKeyPair()");
        return generateKeyPair;
    }

    private static final KeyPair createRsaKeyPairWithKeyPairGeneratorSpec(Context context, String str, RsaEncryptionParams rsaEncryptionParams) {
        String str2 = "Exception when generating RSA key pair.";
        String str3 = TAG;
        Calendar instance = Calendar.getInstance();
        Calendar instance2 = Calendar.getInstance();
        instance2.add(1, 100);
        int keySize = rsaEncryptionParams.getKeySize();
        KeyPairGeneratorSpec.Builder alias = new KeyPairGeneratorSpec.Builder(context).setAlias(str);
        StringBuilder sb = new StringBuilder();
        sb.append("CN=");
        sb.append(str);
        KeyPairGeneratorSpec.Builder serialNumber = alias.setSubject(new X500Principal(sb.toString())).setSerialNumber(BigInteger.valueOf(1337));
        Intrinsics.checkExpressionValueIsNotNull(instance, Methods.START);
        KeyPairGeneratorSpec.Builder startDate = serialNumber.setStartDate(instance.getTime());
        Intrinsics.checkExpressionValueIsNotNull(instance2, "end");
        KeyPairGeneratorSpec.Builder keySize2 = startDate.setEndDate(instance2.getTime()).setKeySize(keySize);
        String str4 = Options.ALGORITHM_RSA;
        KeyPairGeneratorSpec build = keySize2.setKeyType(str4).build();
        Intrinsics.checkExpressionValueIsNotNull(build, "KeyPairGeneratorSpec.Bui…THM_RSA)\n        .build()");
        KeyPair keyPair = null;
        try {
            KeyPairGenerator instance3 = KeyPairGenerator.getInstance(str4, "AndroidKeyStore");
            Intrinsics.checkExpressionValueIsNotNull(instance3, "KeyPairGenerator.getInst…OVIDER_ANDROID_KEY_STORE)");
            instance3.initialize(build);
            KeyPair generateKeyPair = instance3.generateKeyPair();
            Intrinsics.checkExpressionValueIsNotNull(generateKeyPair, "keyPair");
            return generateKeyPair;
        } catch (NoSuchAlgorithmException e) {
            Throwable th = e;
            Log.e(str3, str2, th);
            throw th;
        } catch (GeneralSecurityException e2) {
            Throwable th2 = e2;
            Log.e(str3, str2, th2);
            throw th2;
        }
    }

    @NotNull
    public static final byte[] decryptWithVersionUsingRsa(@NotNull JSONObject jSONObject, @NotNull String str) {
        Intrinsics.checkParameterIsNotNull(jSONObject, "json");
        Intrinsics.checkParameterIsNotNull(str, "namespace");
        int optInt = jSONObject.optInt(JSON_VERSION);
        String optString = jSONObject.optString(JSON_CIPHERTEXT);
        if (optString != null) {
            return decryptUsingRsa(optString, str, RsaEncryptionParams.Companion.forVersion(optInt).getTransformation());
        }
        throw new Exception("JSON format for RSA decryption was not recognised.");
    }

    private static final byte[] decryptUsingRsa(String str, String str2, String str3) {
        String keyAliasFromNamespace = keyAliasFromNamespace(str2);
        KeyStore instance = KeyStore.getInstance("AndroidKeyStore");
        Intrinsics.checkExpressionValueIsNotNull(instance, "KeyStore.getInstance(PROVIDER_ANDROID_KEY_STORE)");
        instance.load(null, null);
        Key key = instance.getKey(keyAliasFromNamespace, null);
        if (key != null) {
            byte[] decode = Base64.decode(str, 2);
            Intrinsics.checkExpressionValueIsNotNull(decode, "Base64.decode(ciphertextBase64, Base64.NO_WRAP)");
            Cipher instance2 = Cipher.getInstance(str3);
            Intrinsics.checkExpressionValueIsNotNull(instance2, "Cipher.getInstance(transformation)");
            instance2.init(2, key);
            byte[] doFinal = instance2.doFinal(decode);
            Intrinsics.checkExpressionValueIsNotNull(doFinal, "cipher.doFinal(ciphertextBytes)");
            return doFinal;
        }
        throw new Exception("RSA key missing.");
    }

    public static final void deleteRsaEncryptionKeyFromKeyStoreIfExists(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "namespace");
        if (VERSION.SDK_INT >= 23) {
            String keyAliasFromNamespace = keyAliasFromNamespace(str);
            KeyStore instance = KeyStore.getInstance("AndroidKeyStore");
            Intrinsics.checkExpressionValueIsNotNull(instance, "KeyStore.getInstance(PROVIDER_ANDROID_KEY_STORE)");
            instance.load(null, null);
            try {
                instance.deleteEntry(keyAliasFromNamespace);
            } catch (KeyStoreException unused) {
            }
        }
    }

    private static final RsaEncryptionResult encryptUsingRsa(Context context, byte[] bArr, String str) {
        PublicKey publicKey;
        String keyAliasFromNamespace = keyAliasFromNamespace(str);
        RsaEncryptionParams currentVersion = RsaEncryptionParams.Companion.getCurrentVersion();
        KeyStore instance = KeyStore.getInstance("AndroidKeyStore");
        Intrinsics.checkExpressionValueIsNotNull(instance, "KeyStore.getInstance(PROVIDER_ANDROID_KEY_STORE)");
        instance.load(null, null);
        PublicKey publicKey2 = null;
        if (!instance.containsAlias(keyAliasFromNamespace)) {
            publicKey = createRsaKeyPair(context, keyAliasFromNamespace, currentVersion).getPublic();
        } else {
            Certificate certificate = instance.getCertificate(keyAliasFromNamespace);
            Intrinsics.checkExpressionValueIsNotNull(certificate, "keyStore.getCertificate(keyAlias)");
            publicKey = certificate.getPublicKey();
        }
        if (publicKey != null) {
            Cipher instance2 = Cipher.getInstance(currentVersion.getTransformation());
            Intrinsics.checkExpressionValueIsNotNull(instance2, "Cipher.getInstance(transformation)");
            instance2.init(1, publicKey);
            byte[] doFinal = instance2.doFinal(bArr);
            Intrinsics.checkExpressionValueIsNotNull(doFinal, "cipher.doFinal(plaintextBytes)");
            return new RsaEncryptionResult(doFinal, currentVersion.getVersion());
        }
        throw new Exception("Failed to get or create public key for RSA encryption");
    }

    @NotNull
    public static final JSONObject encryptWithVersionUsingRsa(@NotNull Context context, @NotNull byte[] bArr, @NotNull String str) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(bArr, "plaintextBytes");
        Intrinsics.checkParameterIsNotNull(str, "namespace");
        RsaEncryptionResult encryptUsingRsa = encryptUsingRsa(context, bArr, str);
        String encodeToString = Base64.encodeToString(encryptUsingRsa.getCiphertext(), 2);
        Intrinsics.checkExpressionValueIsNotNull(encodeToString, "Base64.encodeToString(en…phertext, Base64.NO_WRAP)");
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(JSON_CIPHERTEXT, encodeToString);
        jSONObject.put(JSON_VERSION, encryptUsingRsa.getVersion());
        return jSONObject;
    }

    private static final String keyAliasFromNamespace(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(KEY_ALIAS_SUFFIX);
        return sb.toString();
    }
}
