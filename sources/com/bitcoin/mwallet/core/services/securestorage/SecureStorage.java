package com.bitcoin.mwallet.core.services.securestorage;

import android.app.Application;
import com.yakivmospan.scytale.Crypto;
import com.yakivmospan.scytale.Options;
import com.yakivmospan.scytale.Store;
import javax.crypto.SecretKey;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\rJ\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\f\u001a\u00020\nJ\u0016\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\rJ\u000e\u0010\u0012\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\nJ\u000e\u0010\u0013\u001a\u00020\u00142\u0006\u0010\f\u001a\u00020\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0015"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/services/securestorage/SecureStorage;", "", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "symmetricCrypto", "Lcom/yakivmospan/scytale/Crypto;", "getSymmetricCrypto", "()Lcom/yakivmospan/scytale/Crypto;", "decrypt", "", "encryptedData", "key", "Ljavax/crypto/SecretKey;", "deleteKey", "", "encrypt", "data", "getKey", "hasKey", "", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: SecureStorage.kt */
public final class SecureStorage {
    private final Application application;
    @NotNull
    private final Crypto symmetricCrypto = new Crypto(Options.TRANSFORMATION_SYMMETRIC);

    public SecureStorage(@NotNull Application application2) {
        Intrinsics.checkParameterIsNotNull(application2, "application");
        this.application = application2;
    }

    @NotNull
    public final Crypto getSymmetricCrypto() {
        return this.symmetricCrypto;
    }

    @NotNull
    public final String encrypt(@NotNull String str, @NotNull SecretKey secretKey) {
        Intrinsics.checkParameterIsNotNull(str, "data");
        Intrinsics.checkParameterIsNotNull(secretKey, "key");
        String encrypt = this.symmetricCrypto.encrypt(str, secretKey);
        Intrinsics.checkExpressionValueIsNotNull(encrypt, "symmetricCrypto.encrypt(data, key)");
        return encrypt;
    }

    @NotNull
    public final String decrypt(@NotNull String str, @NotNull SecretKey secretKey) {
        Intrinsics.checkParameterIsNotNull(str, "encryptedData");
        Intrinsics.checkParameterIsNotNull(secretKey, "key");
        String decrypt = this.symmetricCrypto.decrypt(str, secretKey);
        Intrinsics.checkExpressionValueIsNotNull(decrypt, "symmetricCrypto.decrypt(encryptedData, key)");
        return decrypt;
    }

    public final boolean hasKey(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "key");
        return new Store(this.application.getApplicationContext()).hasKey(str);
    }

    public final void deleteKey(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "key");
        new Store(this.application.getApplicationContext()).deleteKey(str);
    }

    @NotNull
    public final SecretKey getKey(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "key");
        Store store = new Store(this.application.getApplicationContext());
        if (!store.hasKey(str)) {
            SecretKey generateSymmetricKey = store.generateSymmetricKey(str, null);
            Intrinsics.checkExpressionValueIsNotNull(generateSymmetricKey, "store.generateSymmetricKey(key, null)");
            return generateSymmetricKey;
        }
        SecretKey symmetricKey = store.getSymmetricKey(str, null);
        Intrinsics.checkExpressionValueIsNotNull(symmetricKey, "store.getSymmetricKey(key, null)");
        return symmetricKey;
    }
}
