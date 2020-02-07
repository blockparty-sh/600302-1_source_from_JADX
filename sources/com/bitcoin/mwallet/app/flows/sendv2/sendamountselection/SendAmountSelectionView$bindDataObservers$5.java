package com.bitcoin.mwallet.app.flows.sendv2.sendamountselection;

import androidx.lifecycle.Observer;
import com.bitcoin.mwallet.app.components.missingwalletdialog.MissingWalletDialogView;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, mo37405d2 = {"<anonymous>", "", "walletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "onChanged"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: SendAmountSelectionView.kt */
final class SendAmountSelectionView$bindDataObservers$5<T> implements Observer<WalletId> {
    final /* synthetic */ SendAmountSelectionView this$0;

    SendAmountSelectionView$bindDataObservers$5(SendAmountSelectionView sendAmountSelectionView) {
        this.this$0 = sendAmountSelectionView;
    }

    public final void onChanged(@Nullable WalletId walletId) {
        if (walletId == null) {
            new MissingWalletDialogView(this.this$0.getWalletType()).show(this.this$0.getChildFragmentManager(), "missing_wallet");
        }
    }
}
