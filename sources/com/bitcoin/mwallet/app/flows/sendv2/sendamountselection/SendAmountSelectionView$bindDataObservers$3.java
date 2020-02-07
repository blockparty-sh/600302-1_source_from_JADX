package com.bitcoin.mwallet.app.flows.sendv2.sendamountselection;

import android.view.View;
import android.widget.TextView;
import androidx.lifecycle.Observer;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.app.components.amountselection.AmountSelectionPresenterBase.EntryType;
import com.bitcoin.mwallet.app.flows.sendv2.sendamountselection.SendAmountSelectionView.WhenMappings;
import kotlin.Metadata;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, mo37405d2 = {"<anonymous>", "", "entryType", "Lcom/bitcoin/mwallet/app/components/amountselection/AmountSelectionPresenterBase$EntryType;", "kotlin.jvm.PlatformType", "onChanged"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: SendAmountSelectionView.kt */
final class SendAmountSelectionView$bindDataObservers$3<T> implements Observer<EntryType> {
    final /* synthetic */ SendAmountSelectionView this$0;

    SendAmountSelectionView$bindDataObservers$3(SendAmountSelectionView sendAmountSelectionView) {
        this.this$0 = sendAmountSelectionView;
    }

    public final void onChanged(EntryType entryType) {
        View view = this.this$0.getView();
        int i = 0;
        if (view != null) {
            TextView textView = (TextView) view.findViewById(C1018R.C1021id.sendingWalletPrimaryBalance);
            if (textView != null) {
                textView.setVisibility(entryType == EntryType.FIAT ? 0 : 8);
            }
        }
        View view2 = this.this$0.getView();
        if (view2 != null) {
            TextView textView2 = (TextView) view2.findViewById(C1018R.C1021id.sendingWalletSecondaryBalance);
            if (textView2 != null) {
                if (!(entryType == EntryType.COIN || entryType == EntryType.TOKEN)) {
                    i = 8;
                }
                textView2.setVisibility(i);
            }
        }
        if (entryType == null || WhenMappings.$EnumSwitchMapping$0[entryType.ordinal()] != 1) {
            ((SendAmountSelectionPresenter) this.this$0.getPresenter()).swapDisplayAmount();
        }
    }
}
