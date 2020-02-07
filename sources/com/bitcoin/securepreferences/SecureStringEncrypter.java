package com.bitcoin.securepreferences;

import android.app.KeyguardManager;
import android.content.Context;
import android.os.Build.VERSION;
import android.util.Log;
import androidx.annotation.RequiresApi;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000e\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u0005J\u0010\u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\u000e\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u0005J\u0010\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u0005H\u0002J\u0010\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u0005H\u0003J\u0010\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u0005H\u0002J\u0010\u0010\u0015\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u000fH\u0002R\u000e\u0010\u0007\u001a\u00020\u0005XD¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, mo37405d2 = {"Lcom/bitcoin/securepreferences/SecureStringEncrypter;", "", "context", "Landroid/content/Context;", "namespace", "", "(Landroid/content/Context;Ljava/lang/String;)V", "TAG", "mApplicationContext", "mDeviceIsSecure", "", "decryptString", "json", "decryptStringEncryptedUsingAesThenKeyStoreRsa", "jsonObj", "Lorg/json/JSONObject;", "encryptString", "value", "encryptStringUsingAesThenKeystoreRsa", "encryptStringUsingKeystoreAes", "encryptionPassthroughOfString", "getStringEncryptedUsingKeyStoreAes", "Companion", "securepreferences_release"}, mo37406k = 1, mo37407mv = {1, 1, 13})
/* compiled from: SecureStringEncrypter.kt */
public final class SecureStringEncrypter {
    public static final Companion Companion = new Companion(null);
    private static final String JSON_ENCRYPTED = "encrypted";
    private static final String JSON_KEY = "key";
    private static final String JSON_VALUE = "value";
    private static final String JSON_VERSION = "v";
    private static final int VERSION_AES_KEY_STORE_RSA = 2;
    private static final int VERSION_KEY_STORE_AES = 3;
    private static final int VERSION_UNENCRYPTED = 1;
    private final String TAG = "SecureStringEncrypter";
    private final Context mApplicationContext;
    private final boolean mDeviceIsSecure;
    private final String namespace;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tXT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tXT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\tXT¢\u0006\u0002\n\u0000¨\u0006\f"}, mo37405d2 = {"Lcom/bitcoin/securepreferences/SecureStringEncrypter$Companion;", "", "()V", "JSON_ENCRYPTED", "", "JSON_KEY", "JSON_VALUE", "JSON_VERSION", "VERSION_AES_KEY_STORE_RSA", "", "VERSION_KEY_STORE_AES", "VERSION_UNENCRYPTED", "securepreferences_release"}, mo37406k = 1, mo37407mv = {1, 1, 13})
    /* compiled from: SecureStringEncrypter.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public SecureStringEncrypter(@NotNull Context context, @NotNull String str) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(str, "namespace");
        this.namespace = str;
        Context applicationContext = context.getApplicationContext();
        Intrinsics.checkExpressionValueIsNotNull(applicationContext, "context.applicationContext");
        this.mApplicationContext = applicationContext;
        Object systemService = context.getSystemService("keyguard");
        if (!(systemService instanceof KeyguardManager)) {
            systemService = null;
        }
        KeyguardManager keyguardManager = (KeyguardManager) systemService;
        boolean z = keyguardManager != null ? VERSION.SDK_INT >= 23 ? keyguardManager.isDeviceSecure() : keyguardManager.isKeyguardSecure() : false;
        this.mDeviceIsSecure = z;
        String str2 = this.TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("Device is secure: ");
        sb.append(this.mDeviceIsSecure);
        Log.d(str2, sb.toString());
    }

    @NotNull
    public final String encryptString(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "value");
        if (VERSION.SDK_INT >= 23) {
            return encryptStringUsingKeystoreAes(str);
        }
        if (this.mDeviceIsSecure) {
            return encryptStringUsingAesThenKeystoreRsa(str);
        }
        return encryptStringUsingAesThenKeystoreRsa(str);
    }

    private final String encryptStringUsingAesThenKeystoreRsa(String str) {
        AesEncryptionResult encryptUsingAesWithoutKeystore = AesKt.encryptUsingAesWithoutKeystore(str);
        JSONObject encryptWithVersionUsingRsa = RsaKt.encryptWithVersionUsingRsa(this.mApplicationContext, encryptUsingAesWithoutKeystore.getKey(), this.namespace);
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(JSON_KEY, encryptWithVersionUsingRsa);
        jSONObject.put("value", encryptUsingAesWithoutKeystore.getEncrypted());
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put(JSON_VERSION, 2);
        jSONObject2.put(JSON_ENCRYPTED, jSONObject);
        String jSONObject3 = jSONObject2.toString();
        Intrinsics.checkExpressionValueIsNotNull(jSONObject3, "container.toString()");
        return jSONObject3;
    }

    @RequiresApi(23)
    private final String encryptStringUsingKeystoreAes(String str) {
        JSONObject encryptUsingAesWithKeystore = AesKt.encryptUsingAesWithKeystore(str, this.namespace);
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(JSON_VERSION, 3);
        jSONObject.put(JSON_ENCRYPTED, encryptUsingAesWithKeystore);
        String jSONObject2 = jSONObject.toString();
        Intrinsics.checkExpressionValueIsNotNull(jSONObject2, "container.toString()");
        return jSONObject2;
    }

    private final String encryptionPassthroughOfString(String str) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(JSON_VERSION, 1);
        jSONObject.put("value", str);
        String jSONObject2 = jSONObject.toString();
        Intrinsics.checkExpressionValueIsNotNull(jSONObject2, "container.toString()");
        return jSONObject2;
    }

    @NotNull
    public final String decryptString(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "json");
        JSONObject jSONObject = new JSONObject(str);
        Integer valueOf = Integer.valueOf(jSONObject.optInt(JSON_VERSION));
        String str2 = " not found.";
        if (valueOf.intValue() == 1) {
            String optString = jSONObject.optString("value");
            if (optString != null) {
                return optString;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Value for encrypted data version ");
            sb.append(valueOf);
            sb.append(str2);
            throw new Exception(sb.toString());
        }
        int intValue = valueOf.intValue();
        String str3 = "Encrypted value for encrypted data version ";
        String str4 = JSON_ENCRYPTED;
        if (intValue == 2) {
            JSONObject optJSONObject = jSONObject.optJSONObject(str4);
            if (optJSONObject != null) {
                return decryptStringEncryptedUsingAesThenKeyStoreRsa(optJSONObject);
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append(str3);
            sb2.append(valueOf);
            sb2.append(str2);
            throw new Exception(sb2.toString());
        } else if (valueOf.intValue() == 3) {
            JSONObject optJSONObject2 = jSONObject.optJSONObject(str4);
            if (optJSONObject2 != null) {
                return getStringEncryptedUsingKeyStoreAes(optJSONObject2);
            }
            StringBuilder sb3 = new StringBuilder();
            sb3.append(str3);
            sb3.append(valueOf);
            sb3.append(str2);
            throw new Exception(sb3.toString());
        } else {
            throw new Exception("Version of encrypted data not recognised.");
        }
    }

    private final String decryptStringEncryptedUsingAesThenKeyStoreRsa(JSONObject jSONObject) {
        JSONObject optJSONObject = jSONObject.optJSONObject(JSON_KEY);
        JSONObject optJSONObject2 = jSONObject.optJSONObject("value");
        if (optJSONObject != null && optJSONObject2 != null) {
            return AesKt.decryptUsingAesWithoutKeyStore(RsaKt.decryptWithVersionUsingRsa(optJSONObject, this.namespace), optJSONObject2);
        }
        throw new Exception("Format of version 1 secure preference not recognised.");
    }

    private final String getStringEncryptedUsingKeyStoreAes(JSONObject jSONObject) {
        return AesKt.decryptUsingAesWithKeyStore(jSONObject, this.namespace);
    }
}
