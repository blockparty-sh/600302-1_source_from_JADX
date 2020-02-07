package com.bitcoin.mwallet.core.utils.signature;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\t"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/utils/signature/SigningKey;", "", "privateKey", "", "publicKey", "(Ljava/lang/String;Ljava/lang/String;)V", "getPrivateKey", "()Ljava/lang/String;", "getPublicKey", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: SigningKey.kt */
public final class SigningKey {
    @NotNull
    private final String privateKey;
    @NotNull
    private final String publicKey;

    public SigningKey(@NotNull String str, @NotNull String str2) {
        Intrinsics.checkParameterIsNotNull(str, "privateKey");
        Intrinsics.checkParameterIsNotNull(str2, "publicKey");
        this.privateKey = str;
        this.publicKey = str2;
    }

    @NotNull
    public final String getPrivateKey() {
        return this.privateKey;
    }

    @NotNull
    public final String getPublicKey() {
        return this.publicKey;
    }
}
