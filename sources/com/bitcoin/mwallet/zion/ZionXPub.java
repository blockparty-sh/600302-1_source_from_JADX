package com.bitcoin.mwallet.zion;

import com.bitcoin.bitcoink.DerivationPath;
import com.bitcoin.bitcoink.ExtendedPublicKey;
import com.bitcoin.mwallet.core.models.Coin;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, mo37405d2 = {"Lcom/bitcoin/mwallet/zion/ZionXPub;", "", "zionId", "Lcom/bitcoin/mwallet/zion/ZionId;", "coin", "Lcom/bitcoin/mwallet/core/models/Coin;", "derivationPath", "Lcom/bitcoin/bitcoink/DerivationPath;", "xPub", "Lcom/bitcoin/bitcoink/ExtendedPublicKey;", "(Lcom/bitcoin/mwallet/zion/ZionId;Lcom/bitcoin/mwallet/core/models/Coin;Lcom/bitcoin/bitcoink/DerivationPath;Lcom/bitcoin/bitcoink/ExtendedPublicKey;)V", "getCoin", "()Lcom/bitcoin/mwallet/core/models/Coin;", "getDerivationPath", "()Lcom/bitcoin/bitcoink/DerivationPath;", "getXPub", "()Lcom/bitcoin/bitcoink/ExtendedPublicKey;", "getZionId", "()Lcom/bitcoin/mwallet/zion/ZionId;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: ZionXPub.kt */
public final class ZionXPub {
    @NotNull
    private final Coin coin;
    @NotNull
    private final DerivationPath derivationPath;
    @NotNull
    private final ExtendedPublicKey xPub;
    @NotNull
    private final ZionId zionId;

    public ZionXPub(@NotNull ZionId zionId2, @NotNull Coin coin2, @NotNull DerivationPath derivationPath2, @NotNull ExtendedPublicKey extendedPublicKey) {
        Intrinsics.checkParameterIsNotNull(zionId2, "zionId");
        Intrinsics.checkParameterIsNotNull(coin2, "coin");
        Intrinsics.checkParameterIsNotNull(derivationPath2, "derivationPath");
        Intrinsics.checkParameterIsNotNull(extendedPublicKey, "xPub");
        this.zionId = zionId2;
        this.coin = coin2;
        this.derivationPath = derivationPath2;
        this.xPub = extendedPublicKey;
    }

    @NotNull
    public final ZionId getZionId() {
        return this.zionId;
    }

    @NotNull
    public final Coin getCoin() {
        return this.coin;
    }

    @NotNull
    public final DerivationPath getDerivationPath() {
        return this.derivationPath;
    }

    @NotNull
    public final ExtendedPublicKey getXPub() {
        return this.xPub;
    }
}
