package com.bitcoin.mwallet.app.flows.receive.receiveamountselection;

import android.view.View;
import android.widget.TextView;
import androidx.lifecycle.Observer;
import com.bitcoin.mwallet.C1018R;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import com.leanplum.core.BuildConfig;
import java.util.Currency;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, mo37405d2 = {"<anonymous>", "", "currency", "Ljava/util/Currency;", "kotlin.jvm.PlatformType", "onChanged"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: ReceiveAmountSelectionView.kt */
final class ReceiveAmountSelectionView$bindDataObservers$4<T> implements Observer<Currency> {
    final /* synthetic */ ReceiveAmountSelectionView this$0;

    ReceiveAmountSelectionView$bindDataObservers$4(ReceiveAmountSelectionView receiveAmountSelectionView) {
        this.this$0 = receiveAmountSelectionView;
    }

    public final void onChanged(Currency currency) {
        View view = this.this$0.getView();
        if (view != null) {
            TextView textView = (TextView) view.findViewById(C1018R.C1021id.currencySelector);
            if (textView != null) {
                Intrinsics.checkExpressionValueIsNotNull(currency, Param.CURRENCY);
                textView.setText(currency.getCurrencyCode());
            }
        }
        ((ReceiveAmountSelectionPresenter) this.this$0.getPresenter()).getPrimaryAmount().postValue(BuildConfig.BUILD_NUMBER);
    }
}
