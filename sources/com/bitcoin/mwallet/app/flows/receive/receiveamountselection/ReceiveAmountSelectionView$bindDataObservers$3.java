package com.bitcoin.mwallet.app.flows.receive.receiveamountselection;

import androidx.lifecycle.Observer;
import com.bitcoin.mwallet.app.components.amountselection.AmountSelectionPresenterBase.EntryType;
import com.bitcoin.mwallet.app.flows.receive.receiveamountselection.ReceiveAmountSelectionView.WhenMappings;
import kotlin.Metadata;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, mo37405d2 = {"<anonymous>", "", "entryType", "Lcom/bitcoin/mwallet/app/components/amountselection/AmountSelectionPresenterBase$EntryType;", "kotlin.jvm.PlatformType", "onChanged"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: ReceiveAmountSelectionView.kt */
final class ReceiveAmountSelectionView$bindDataObservers$3<T> implements Observer<EntryType> {
    final /* synthetic */ ReceiveAmountSelectionView this$0;

    ReceiveAmountSelectionView$bindDataObservers$3(ReceiveAmountSelectionView receiveAmountSelectionView) {
        this.this$0 = receiveAmountSelectionView;
    }

    public final void onChanged(EntryType entryType) {
        if (entryType == null || WhenMappings.$EnumSwitchMapping$0[entryType.ordinal()] != 1) {
            ((ReceiveAmountSelectionPresenter) this.this$0.getPresenter()).swapDisplayAmount();
        }
    }
}
