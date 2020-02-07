package com.bitcoin.mwallet.app.flows.walletdetails.overview;

import android.view.View;
import android.widget.TextView;
import androidx.lifecycle.Observer;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.core.extensions.BigDecimalKt;
import com.bitcoin.mwallet.core.models.wallet.WalletInfoBalance;
import kotlin.Metadata;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, mo37405d2 = {"<anonymous>", "", "balanceInfo", "Lcom/bitcoin/mwallet/core/models/wallet/WalletInfoBalance;", "kotlin.jvm.PlatformType", "onChanged"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: OverviewView.kt */
final class OverviewView$bindDataObservers$2<T> implements Observer<WalletInfoBalance> {
    final /* synthetic */ OverviewView this$0;

    OverviewView$bindDataObservers$2(OverviewView overviewView) {
        this.this$0 = overviewView;
    }

    public final void onChanged(WalletInfoBalance walletInfoBalance) {
        View view = this.this$0.getView();
        if (view != null) {
            TextView textView = (TextView) view.findViewById(C1018R.C1021id.walletValueTextView);
            if (textView != null) {
                textView.setText(BigDecimalKt.toFiatString$default(walletInfoBalance.getFiatBalance(), walletInfoBalance.getFiatCurrency(), false, 2, null));
            }
        }
        View view2 = this.this$0.getView();
        if (view2 != null) {
            TextView textView2 = (TextView) view2.findViewById(C1018R.C1021id.walletNameHeader);
            if (textView2 != null) {
                textView2.setText(walletInfoBalance.getName());
            }
        }
    }
}
