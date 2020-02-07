package com.bitcoin.mwallet.app.flows.walletdetails.overview.historyassets.transactionhistory;

import android.view.View;
import android.widget.TextView;
import androidx.lifecycle.Observer;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.core.models.Coin;
import java.text.MessageFormat;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, mo37405d2 = {"<anonymous>", "", "it", "", "kotlin.jvm.PlatformType", "onChanged"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: TransactionHistoryView.kt */
final class TransactionHistoryView$onActivityCreated$2<T> implements Observer<String> {
    final /* synthetic */ TransactionHistoryView this$0;

    TransactionHistoryView$onActivityCreated$2(TransactionHistoryView transactionHistoryView) {
        this.this$0 = transactionHistoryView;
    }

    public final void onChanged(String str) {
        String str2;
        if (Intrinsics.areEqual((Object) str, (Object) Coin.BTC.getTicker())) {
            str2 = this.this$0.getResources().getString(C1018R.string.general_bitcoin_core);
            Intrinsics.checkExpressionValueIsNotNull(str2, "resources.getString(R.string.general_bitcoin_core)");
        } else {
            str2 = this.this$0.getResources().getString(C1018R.string.general_bitcoin_cash);
            Intrinsics.checkExpressionValueIsNotNull(str2, "resources.getString(R.string.general_bitcoin_cash)");
        }
        View view = this.this$0.getView();
        if (view != null) {
            TextView textView = (TextView) view.findViewById(C1018R.C1021id.firstOptionTextView);
            if (textView != null) {
                textView.setText(MessageFormat.format(this.this$0.getResources().getString(C1018R.string.tx_history_empty_option_first), new Object[]{str2}));
            }
        }
    }
}
