package com.bitcoin.mwallet.core.models.p009tx.utxo;

import com.bitcoin.bitcoink.p008tx.Satoshis;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007R\u001b\u0010\b\u001a\u00020\t8FX\u0002¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0012"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/models/tx/utxo/WalletUtxos;", "", "walletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "utxos", "", "Lcom/bitcoin/mwallet/core/models/tx/utxo/Utxo;", "(Lcom/bitcoin/mwallet/core/models/wallet/WalletId;Ljava/util/List;)V", "balance", "Lcom/bitcoin/bitcoink/tx/Satoshis;", "getBalance", "()Lcom/bitcoin/bitcoink/tx/Satoshis;", "balance$delegate", "Lkotlin/Lazy;", "getUtxos", "()Ljava/util/List;", "getWalletId", "()Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* renamed from: com.bitcoin.mwallet.core.models.tx.utxo.WalletUtxos */
/* compiled from: WalletUtxos.kt */
public final class WalletUtxos {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(WalletUtxos.class), "balance", "getBalance()Lcom/bitcoin/bitcoink/tx/Satoshis;"))};
    @NotNull
    private final Lazy balance$delegate = LazyKt.lazy(new WalletUtxos$balance$2(this));
    @NotNull
    private final List<Utxo> utxos;
    @NotNull
    private final WalletId walletId;

    @NotNull
    public final Satoshis getBalance() {
        Lazy lazy = this.balance$delegate;
        KProperty kProperty = $$delegatedProperties[0];
        return (Satoshis) lazy.getValue();
    }

    public WalletUtxos(@NotNull WalletId walletId2, @NotNull List<Utxo> list) {
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
    public final List<Utxo> getUtxos() {
        return this.utxos;
    }
}
