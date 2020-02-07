package com.bitcoin.securepreferences;

import android.os.Build.VERSION;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyGenParameterSpec.Builder;
import android.security.keystore.StrongBoxUnavailableException;
import android.util.Base64;
import androidx.annotation.RequiresApi;
import com.yakivmospan.scytale.Options;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000D\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0018\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0001H\u0003\u001a\u0010\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002\u001a\u0018\u0010\u0014\u001a\u00020\u00012\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0001H\u0000\u001a\u0018\u0010\u0018\u001a\u00020\u00012\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0015\u001a\u00020\u0016H\u0000\u001a\u000e\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u0017\u001a\u00020\u0001\u001a\u0018\u0010\u001d\u001a\u00020\u00162\u0006\u0010\u001e\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0001H\u0001\u001a\u0010\u0010\u001f\u001a\u00020 2\u0006\u0010\u001e\u001a\u00020\u0001H\u0000\u001a\u0018\u0010!\u001a\u00020\"2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u0001H\u0003\u001a\u0010\u0010#\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0001H\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\t\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\n\u001a\u00020\u000bXT¢\u0006\u0002\n\u0000\"\u000e\u0010\f\u001a\u00020\u000bXT¢\u0006\u0002\n\u0000\"\u000e\u0010\r\u001a\u00020\u000bXT¢\u0006\u0002\n\u0000¨\u0006$"}, mo37405d2 = {"JSON_CIPHERTEXT", "", "JSON_ENCRYPTED", "JSON_IV", "JSON_KEY", "JSON_VALUE", "JSON_VERSION", "KEY_ALIAS_SUFFIX", "PROVIDER_ANDROID_KEY_STORE", "TAG", "VERSION_KEY_STORE_128", "", "VERSION_KEY_STORE_256", "VERSION_WITHOUT_KEY_STORE", "createAesKeyInKeystore", "Ljavax/crypto/SecretKey;", "params", "Lcom/bitcoin/securepreferences/AesEncryptionParams;", "keyAlias", "createAesKeyWithoutKeystore", "decryptUsingAesWithKeyStore", "json", "Lorg/json/JSONObject;", "namespace", "decryptUsingAesWithoutKeyStore", "key", "", "deleteAesEncryptionKeyFromKeyStoreIfExists", "", "encryptUsingAesWithKeystore", "plaintext", "encryptUsingAesWithoutKeystore", "Lcom/bitcoin/securepreferences/AesEncryptionResult;", "getOrCreateAesKeystoreKey", "Ljava/security/Key;", "keyAliasFromNamespace", "securepreferences_release"}, mo37406k = 2, mo37407mv = {1, 1, 13})
/* compiled from: aes.kt */
public final class AesKt {
    private static final String JSON_CIPHERTEXT = "ct";
    private static final String JSON_ENCRYPTED = "encrypted";
    private static final String JSON_IV = "iv";
    private static final String JSON_KEY = "key";
    private static final String JSON_VALUE = "value";
    private static final String JSON_VERSION = "v";
    private static final String KEY_ALIAS_SUFFIX = ".aes";
    private static final String PROVIDER_ANDROID_KEY_STORE = "AndroidKeyStore";
    private static final String TAG = "aes";
    private static final int VERSION_KEY_STORE_128 = 2;
    private static final int VERSION_KEY_STORE_256 = 3;
    private static final int VERSION_WITHOUT_KEY_STORE = 1;

    private static final SecretKey createAesKeyWithoutKeystore(AesEncryptionParams aesEncryptionParams) {
        KeyGenerator instance = KeyGenerator.getInstance(aesEncryptionParams.getKeyGeneratorAlgorithm());
        Intrinsics.checkExpressionValueIsNotNull(instance, "KeyGenerator.getInstance…ms.keyGeneratorAlgorithm)");
        instance.init(aesEncryptionParams.getKeySize(), new SecureRandom());
        SecretKey generateKey = instance.generateKey();
        Intrinsics.checkExpressionValueIsNotNull(generateKey, "keyGenerator.generateKey()");
        return generateKey;
    }

    @RequiresApi(23)
    private static final SecretKey createAesKeyInKeystore(AesEncryptionParams aesEncryptionParams, String str) {
        String str2 = "AndroidKeyStore";
        String str3 = Options.ALGORITHM_AES;
        KeyGenerator instance = KeyGenerator.getInstance(str3, str2);
        Intrinsics.checkExpressionValueIsNotNull(instance, "KeyGenerator.getInstance…R_ANDROID_KEY_STORE\n    )");
        Builder builder = new Builder(str, 3);
        builder.setBlockModes(new String[]{aesEncryptionParams.getCipherMode()}).setEncryptionPaddings(new String[]{aesEncryptionParams.getPadding()});
        KeyGenParameterSpec build = builder.build();
        String str4 = "keySpecBuilder.build()";
        Intrinsics.checkExpressionValueIsNotNull(build, str4);
        instance.init(build);
        String str5 = "keyGeneratorWithoutStrongBox.generateKey()";
        if (VERSION.SDK_INT >= 28) {
            builder.setIsStrongBoxBacked(true);
            KeyGenParameterSpec build2 = builder.build();
            Intrinsics.checkExpressionValueIsNotNull(build2, str4);
            KeyGenerator instance2 = KeyGenerator.getInstance(str3, str2);
            Intrinsics.checkExpressionValueIsNotNull(instance2, "KeyGenerator.getInstance…DROID_KEY_STORE\n        )");
            instance2.init(build2);
            try {
                SecretKey generateKey = instance2.generateKey();
                Intrinsics.checkExpressionValueIsNotNull(generateKey, "keyGeneratorWithStrongBox.generateKey()");
                return generateKey;
            } catch (StrongBoxUnavailableException unused) {
                SecretKey generateKey2 = instance.generateKey();
                Intrinsics.checkExpressionValueIsNotNull(generateKey2, str5);
                return generateKey2;
            }
        } else {
            SecretKey generateKey3 = instance.generateKey();
            Intrinsics.checkExpressionValueIsNotNull(generateKey3, str5);
            return generateKey3;
        }
    }

    @NotNull
    public static final String decryptUsingAesWithoutKeyStore(@NotNull byte[] bArr, @NotNull JSONObject jSONObject) {
        Intrinsics.checkParameterIsNotNull(bArr, JSON_KEY);
        Intrinsics.checkParameterIsNotNull(jSONObject, "json");
        String optString = jSONObject.optString(JSON_IV);
        int optInt = jSONObject.optInt(JSON_VERSION);
        String optString2 = jSONObject.optString(JSON_CIPHERTEXT);
        if (optString == null || optString2 == null) {
            throw new Exception("Encrypted value is missing parameters.");
        } else if (optInt == 1) {
            AesEncryptionParams forVersion = AesEncryptionParams.Companion.forVersion(optInt);
            byte[] decode = Base64.decode(optString, 2);
            Intrinsics.checkExpressionValueIsNotNull(decode, "Base64.decode(ivBase64, Base64.NO_WRAP)");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(decode);
            byte[] decode2 = Base64.decode(optString2, 2);
            Intrinsics.checkExpressionValueIsNotNull(decode2, "Base64.decode(ciphertextBase64, Base64.NO_WRAP)");
            SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, forVersion.getKeySpecAlgorithm());
            Cipher instance = Cipher.getInstance(forVersion.getTransformation());
            Intrinsics.checkExpressionValueIsNotNull(instance, "Cipher.getInstance(params.transformation)");
            instance.init(2, secretKeySpec, ivParameterSpec);
            byte[] doFinal = instance.doFinal(decode2);
            Intrinsics.checkExpressionValueIsNotNull(doFinal, "cipher.doFinal(ciphertextBytes)");
            return new String(doFinal, Charsets.UTF_8);
        } else {
            throw new Exception("Encrypted value requires KeyStore to decrypt.");
        }
    }

    @NotNull
    public static final String decryptUsingAesWithKeyStore(@NotNull JSONObject jSONObject, @NotNull String str) {
        Intrinsics.checkParameterIsNotNull(jSONObject, "json");
        Intrinsics.checkParameterIsNotNull(str, "namespace");
        JSONObject optJSONObject = jSONObject.optJSONObject(JSON_ENCRYPTED);
        int optInt = jSONObject.optInt(JSON_VERSION);
        if (optJSONObject != null) {
            AesEncryptionParams forVersion = AesEncryptionParams.Companion.forVersion(optInt);
            String optString = optJSONObject.optString(JSON_IV);
            String optString2 = optJSONObject.optString(JSON_CIPHERTEXT);
            if (optString == null || optString2 == null) {
                throw new Exception("Encrypted value is missing components.");
            }
            byte[] decode = Base64.decode(optString, 2);
            Intrinsics.checkExpressionValueIsNotNull(decode, "Base64.decode(ivBase64, Base64.NO_WRAP)");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(decode);
            byte[] decode2 = Base64.decode(optString2, 2);
            Intrinsics.checkExpressionValueIsNotNull(decode2, "Base64.decode(ciphertextBase64, Base64.NO_WRAP)");
            String keyAliasFromNamespace = keyAliasFromNamespace(str);
            KeyStore instance = KeyStore.getInstance("AndroidKeyStore");
            Intrinsics.checkExpressionValueIsNotNull(instance, "KeyStore.getInstance(PROVIDER_ANDROID_KEY_STORE)");
            instance.load(null, null);
            Key key = instance.getKey(keyAliasFromNamespace, null);
            if (key != null) {
                Cipher instance2 = Cipher.getInstance(forVersion.getTransformation());
                Intrinsics.checkExpressionValueIsNotNull(instance2, "Cipher.getInstance(params.transformation)");
                instance2.init(2, key, ivParameterSpec);
                byte[] doFinal = instance2.doFinal(decode2);
                Intrinsics.checkExpressionValueIsNotNull(doFinal, "cipher.doFinal(ciphertextBytes)");
                return new String(doFinal, Charsets.UTF_8);
            }
            throw new Exception("Key missing when decrypting.");
        }
        throw new Exception("Encrypted value is missing parameters.");
    }

    public static final void deleteAesEncryptionKeyFromKeyStoreIfExists(@NotNull String str) {
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

    @NotNull
    public static final AesEncryptionResult encryptUsingAesWithoutKeystore(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "plaintext");
        AesEncryptionParams forVersionWithoutKeyStore = AesEncryptionParams.Companion.forVersionWithoutKeyStore();
        SecretKey createAesKeyWithoutKeystore = createAesKeyWithoutKeystore(forVersionWithoutKeyStore);
        Cipher instance = Cipher.getInstance(forVersionWithoutKeyStore.getTransformation());
        Intrinsics.checkExpressionValueIsNotNull(instance, "Cipher.getInstance(params.transformation)");
        instance.init(1, createAesKeyWithoutKeystore);
        byte[] iv = instance.getIV();
        Intrinsics.checkExpressionValueIsNotNull(iv, "cipher.iv");
        byte[] bytes = str.getBytes(Charsets.UTF_8);
        Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
        byte[] doFinal = instance.doFinal(bytes);
        Intrinsics.checkExpressionValueIsNotNull(doFinal, "cipher.doFinal(plaintext.toByteArray())");
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(JSON_IV, Base64.encodeToString(iv, 2));
        jSONObject.put(JSON_VERSION, forVersionWithoutKeyStore.getVersion());
        jSONObject.put(JSON_CIPHERTEXT, Base64.encodeToString(doFinal, 2));
        HexKt.toHexString(iv);
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put(JSON_KEY, Base64.encodeToString(createAesKeyWithoutKeystore.getEncoded(), 2));
        jSONObject2.put("value", jSONObject);
        byte[] encoded = createAesKeyWithoutKeystore.getEncoded();
        Intrinsics.checkExpressionValueIsNotNull(encoded, "secretKey.encoded");
        return new AesEncryptionResult(encoded, jSONObject);
    }

    @RequiresApi(23)
    @NotNull
    public static final JSONObject encryptUsingAesWithKeystore(@NotNull String str, @NotNull String str2) {
        Intrinsics.checkParameterIsNotNull(str, "plaintext");
        Intrinsics.checkParameterIsNotNull(str2, "namespace");
        AesEncryptionParams currentVersion = AesEncryptionParams.Companion.getCurrentVersion();
        Key orCreateAesKeystoreKey = getOrCreateAesKeystoreKey(currentVersion, str2);
        Cipher instance = Cipher.getInstance(currentVersion.getTransformation());
        Intrinsics.checkExpressionValueIsNotNull(instance, "Cipher.getInstance(params.transformation)");
        instance.init(1, orCreateAesKeystoreKey);
        byte[] iv = instance.getIV();
        Intrinsics.checkExpressionValueIsNotNull(iv, "cipher.iv");
        byte[] bytes = str.getBytes(Charsets.UTF_8);
        Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
        byte[] doFinal = instance.doFinal(bytes);
        Intrinsics.checkExpressionValueIsNotNull(doFinal, "cipher.doFinal(plaintext.toByteArray())");
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(JSON_IV, Base64.encodeToString(iv, 2));
        jSONObject.put(JSON_CIPHERTEXT, Base64.encodeToString(doFinal, 2));
        HexKt.toHexString(iv);
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put(JSON_VERSION, currentVersion.getVersion());
        jSONObject2.put(JSON_ENCRYPTED, jSONObject);
        return jSONObject2;
    }

    @RequiresApi(23)
    private static final Key getOrCreateAesKeystoreKey(AesEncryptionParams aesEncryptionParams, String str) {
        String keyAliasFromNamespace = keyAliasFromNamespace(str);
        KeyStore instance = KeyStore.getInstance("AndroidKeyStore");
        Intrinsics.checkExpressionValueIsNotNull(instance, "KeyStore.getInstance(PROVIDER_ANDROID_KEY_STORE)");
        instance.load(null, null);
        Key key = instance.getKey(keyAliasFromNamespace, null);
        return key == null ? createAesKeyInKeystore(aesEncryptionParams, keyAliasFromNamespace) : key;
    }

    private static final String keyAliasFromNamespace(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(KEY_ALIAS_SUFFIX);
        return sb.toString();
    }
}
