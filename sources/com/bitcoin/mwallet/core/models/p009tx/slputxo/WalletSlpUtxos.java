package com.bitcoin.mwallet.core.models.p009tx.slputxo;

import com.bitcoin.mwallet.core.models.wallet.WalletId;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/models/tx/slputxo/WalletSlpUtxos;", "", "walletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "utxos", "", "Lcom/bitcoin/mwallet/core/models/tx/slputxo/SlpUtxo;", "(Lcom/bitcoin/mwallet/core/models/wallet/WalletId;Ljava/util/List;)V", "getUtxos", "()Ljava/util/List;", "getWalletId", "()Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* renamed from: com.bitcoin.mwallet.core.models.tx.slputxo.WalletSlpUtxos */
/* compiled from: WalletSlpUtxos.kt */
public final class WalletSlpUtxos {
    @NotNull
    private final List<SlpUtxo> utxos;
    @NotNull
    private final WalletId walletId;

    public WalletSlpUtxos(@NotNull WalletId walletId2, @NotNull List<SlpUtxo> list) {
        Intrinsics.checkParameterIsNotNull(walletId2, "walletId");
        Intrinsics.checkParameterIsNotNull(list, "utxos");
        this.walletId = walletId2;
        this.utxos = list;
    }

    @NotNull
    public final WalletId getWalletId() {
        return this.walletId;
    }

    @NotNull
    public final List<SlpUtxo> getUtxos() {
        return this.utxos;
    }
}
