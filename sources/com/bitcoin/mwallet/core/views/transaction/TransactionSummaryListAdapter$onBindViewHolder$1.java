package com.bitcoin.mwallet.core.views.transaction;

import android.view.View;
import android.view.View.OnClickListener;
import kotlin.Metadata;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, mo37405d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: TransactionSummaryListAdapter.kt */
final class TransactionSummaryListAdapter$onBindViewHolder$1 implements OnClickListener {
    final /* synthetic */ TransactionSummaryView $item;
    final /* synthetic */ TransactionSummaryListAdapter this$0;

    TransactionSummaryListAdapter$onBindViewHolder$1(TransactionSummaryListAdapter transactionSummaryListAdapter, TransactionSummaryView transactionSummaryView) {
        this.this$0 = transactionSummaryListAdapter;
        this.$item = transactionSummaryView;
    }

    public final void onClick(View view) {
        this.this$0.presenter.onTransactionSummaryClicked(this.$item.getTxId());
    }
}
