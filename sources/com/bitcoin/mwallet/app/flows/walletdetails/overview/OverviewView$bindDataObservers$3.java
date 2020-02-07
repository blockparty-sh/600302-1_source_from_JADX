package com.bitcoin.mwallet.app.flows.walletdetails.overview;

import android.view.View;
import android.widget.TextView;
import androidx.lifecycle.Observer;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.core.extensions.BigDecimalKt;
import com.bitcoin.mwallet.core.models.Coin;
import com.bitcoin.mwallet.core.models.wallet.WalletInfoSatoshis;
import java.util.List;
import kotlin.Metadata;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, mo37405d2 = {"<anonymous>", "", "walletBalance", "Lcom/bitcoin/mwallet/core/models/wallet/WalletInfoSatoshis;", "kotlin.jvm.PlatformType", "onChanged"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: OverviewView.kt */
final class OverviewView$bindDataObservers$3<T> implements Observer<WalletInfoSatoshis> {
    final /* synthetic */ OverviewView this$0;

    OverviewView$bindDataObservers$3(OverviewView overviewView) {
        this.this$0 = overviewView;
    }

    public final void onChanged(WalletInfoSatoshis walletInfoSatoshis) {
        CharSequence charSequence;
        if (walletInfoSatoshis != null) {
            List walletTokens = ((OverviewPresenter) this.this$0.getPresenter()).getWalletTokens(walletInfoSatoshis.getWalletId());
            View view = this.this$0.getView();
            if (view != null) {
                TextView textView = (TextView) view.findViewById(C1018R.C1021id.walletSummaryTextView);
                if (textView != null) {
                    if (walletInfoSatoshis.getCoin() == Coin.BCH) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(BigDecimalKt.toCoinString(walletInfoSatoshis.getSatoshis().getCoins(), walletInfoSatoshis.getCoin().getTicker()));
                        sb.append(" + ");
                        sb.append(walletTokens.size());
                        sb.append(' ');
                        sb.append(this.this$0.getString(C1018R.string.token_amount_suffix));
                        charSequence = sb.toString();
                    } else {
                        charSequence = String.valueOf(BigDecimalKt.toCoinString(walletInfoSatoshis.getSatoshis().getCoins(), walletInfoSatoshis.getCoin().getTicker()));
                    }
                    textView.setText(charSequence);
                }
            }
        }
    }
}
