package com.bitcoin.mwallet.app.flows.sendv2.sendamountselection;

import androidx.lifecycle.Observer;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import java.util.Currency;
import kotlin.Metadata;
import kotlin.Pair;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012.\u0010\u0002\u001a*\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0005 \u0006*\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0007"}, mo37405d2 = {"<anonymous>", "", "pair", "Lkotlin/Pair;", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "Ljava/util/Currency;", "kotlin.jvm.PlatformType", "onChanged"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: SendAmountSelectionView.kt */
final class SendAmountSelectionView$bindDataObservers$6<T> implements Observer<Pair<? extends WalletId, ? extends Currency>> {
    final /* synthetic */ SendAmountSelectionView this$0;

    SendAmountSelectionView$bindDataObservers$6(SendAmountSelectionView sendAmountSelectionView) {
        this.this$0 = sendAmountSelectionView;
    }

    public final void onChanged(Pair<WalletId, Currency> pair) {
        ((SendAmountSelectionPresenter) this.this$0.getPresenter()).updateWalletBalance((WalletId) pair.getFirst(), (Currency) pair.getSecond());
    }
}
