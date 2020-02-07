package com.bitcoin.mwallet.core.entity;

import com.bitcoin.mwallet.core.models.Coin;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b¨\u0006\f"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/entity/WalletType;", "", "coin", "Lcom/bitcoin/mwallet/core/models/Coin;", "(Ljava/lang/String;ILcom/bitcoin/mwallet/core/models/Coin;)V", "getCoin", "()Lcom/bitcoin/mwallet/core/models/Coin;", "setCoin", "(Lcom/bitcoin/mwallet/core/models/Coin;)V", "BCH", "BTC", "SLP", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: WalletType.kt */
public enum WalletType {
    BCH(Coin.BCH),
    BTC(Coin.BTC),
    SLP(Coin.BCH);
    
    @NotNull
    private Coin coin;

    private WalletType(Coin coin2) {
        this.coin = coin2;
    }

    @NotNull
    public final Coin getCoin() {
        return this.coin;
    }

    public final void setCoin(@NotNull Coin coin2) {
        Intrinsics.checkParameterIsNotNull(coin2, "<set-?>");
        this.coin = coin2;
    }
}
