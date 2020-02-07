package com.bitcoin.mwallet.core.repositories;

import androidx.arch.core.util.Function;
import com.bitcoin.mwallet.core.models.p009tx.utxo.Utxo;
import com.bitcoin.mwallet.core.models.p009tx.utxo.WalletUtxos;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\n¢\u0006\u0002\b\u0005"}, mo37405d2 = {"<anonymous>", "Lcom/bitcoin/mwallet/core/models/tx/utxo/WalletUtxos;", "utxos", "", "Lcom/bitcoin/mwallet/core/models/tx/utxo/Utxo;", "apply"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: UtxoRepository.kt */
final class UtxoRepository$walletUtxos$1<I, O> implements Function<X, Y> {
    final /* synthetic */ WalletId $walletId;

    UtxoRepository$walletUtxos$1(WalletId walletId) {
        this.$walletId = walletId;
    }

    @NotNull
    public final WalletUtxos apply(@NotNull List<Utxo> list) {
        Intrinsics.checkParameterIsNotNull(list, "utxos");
        return new WalletUtxos(this.$walletId, list);
    }
}
