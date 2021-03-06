package com.bitcoin.mwallet.app.flows.sendv2.review;

import android.view.View;
import android.widget.TextView;
import androidx.lifecycle.Observer;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.core.extensions.BigDecimalKt;
import com.bitcoin.mwallet.core.models.wallet.WalletInfoBalance;
import java.math.BigDecimal;
import java.util.Currency;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, mo37405d2 = {"<anonymous>", "", "balance", "Lcom/bitcoin/mwallet/core/models/wallet/WalletInfoBalance;", "kotlin.jvm.PlatformType", "onChanged"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: ReviewView.kt */
final class ReviewView$bindObservers$3<T> implements Observer<WalletInfoBalance> {
    final /* synthetic */ ReviewView this$0;

    ReviewView$bindObservers$3(ReviewView reviewView) {
        this.this$0 = reviewView;
    }

    public final void onChanged(WalletInfoBalance walletInfoBalance) {
        View view = this.this$0.getView();
        if (view != null) {
            TextView textView = (TextView) view.findViewById(C1018R.C1021id.reviewFiatBalance);
            if (textView != null) {
                String str = null;
                if (walletInfoBalance != null) {
                    BigDecimal fiatBalance = walletInfoBalance.getFiatBalance();
                    if (fiatBalance != null) {
                        Currency currency = ((ReviewPresenter) this.this$0.getPresenter()).getCurrency();
                        Intrinsics.checkExpressionValueIsNotNull(currency, "presenter.currency");
                        str = BigDecimalKt.toFiatString$default(fiatBalance, currency, false, 2, null);
                    }
                }
                textView.setText(str);
            }
        }
    }
}
