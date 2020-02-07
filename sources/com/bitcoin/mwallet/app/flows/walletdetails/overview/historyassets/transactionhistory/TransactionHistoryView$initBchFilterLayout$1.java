package com.bitcoin.mwallet.app.flows.walletdetails.overview.historyassets.transactionhistory;

import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import kotlin.Metadata;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\n¢\u0006\u0002\b\u0007"}, mo37405d2 = {"<anonymous>", "", "buttonView", "Landroid/widget/CompoundButton;", "kotlin.jvm.PlatformType", "checked", "", "onCheckedChanged"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: TransactionHistoryView.kt */
final class TransactionHistoryView$initBchFilterLayout$1 implements OnCheckedChangeListener {
    final /* synthetic */ TransactionHistoryView this$0;

    TransactionHistoryView$initBchFilterLayout$1(TransactionHistoryView transactionHistoryView) {
        this.this$0 = transactionHistoryView;
    }

    public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        TransactionHistoryView.access$getPresenter$p(this.this$0).showBCHOnly(z);
    }
}
