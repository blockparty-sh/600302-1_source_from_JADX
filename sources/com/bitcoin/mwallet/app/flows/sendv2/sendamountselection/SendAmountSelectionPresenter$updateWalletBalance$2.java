package com.bitcoin.mwallet.app.flows.sendv2.sendamountselection;

import androidx.lifecycle.Observer;
import com.bitcoin.mwallet.core.models.wallet.WalletInfoBalance;
import kotlin.Metadata;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, mo37405d2 = {"<anonymous>", "", "balance", "Lcom/bitcoin/mwallet/core/models/wallet/WalletInfoBalance;", "kotlin.jvm.PlatformType", "onChanged"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: SendAmountSelectionPresenter.kt */
final class SendAmountSelectionPresenter$updateWalletBalance$2<T> implements Observer<S> {
    final /* synthetic */ SendAmountSelectionPresenter this$0;

    SendAmountSelectionPresenter$updateWalletBalance$2(SendAmountSelectionPresenter sendAmountSelectionPresenter) {
        this.this$0 = sendAmountSelectionPresenter;
    }

    public final void onChanged(WalletInfoBalance walletInfoBalance) {
        this.this$0.getWalletBalance().setValue(walletInfoBalance);
        this.this$0.setLatestWalletBalance(walletInfoBalance);
    }
}
