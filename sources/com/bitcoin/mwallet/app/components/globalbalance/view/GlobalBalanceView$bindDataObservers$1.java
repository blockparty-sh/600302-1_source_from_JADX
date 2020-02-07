package com.bitcoin.mwallet.app.components.globalbalance.view;

import android.view.View;
import android.widget.TextView;
import androidx.lifecycle.Observer;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.core.extensions.BigDecimalKt;
import com.bitcoin.mwallet.core.models.wallet.WalletInfoBalance;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u001a\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004 \u0005*\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0006"}, mo37405d2 = {"<anonymous>", "", "walletInfoBalance", "", "Lcom/bitcoin/mwallet/core/models/wallet/WalletInfoBalance;", "kotlin.jvm.PlatformType", "onChanged"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: GlobalBalanceView.kt */
final class GlobalBalanceView$bindDataObservers$1<T> implements Observer<List<? extends WalletInfoBalance>> {
    final /* synthetic */ GlobalBalanceView this$0;

    GlobalBalanceView$bindDataObservers$1(GlobalBalanceView globalBalanceView) {
        this.this$0 = globalBalanceView;
    }

    public final void onChanged(List<WalletInfoBalance> list) {
        if (list != null) {
            BigDecimal bigDecimal = new BigDecimal(0);
            Iterable iterable = list;
            Collection arrayList = new ArrayList();
            for (Object next : iterable) {
                if (!((WalletInfoBalance) next).getWalletPreference().getHideBalance()) {
                    arrayList.add(next);
                }
            }
            for (WalletInfoBalance walletInfoBalance : (List) arrayList) {
                if (walletInfoBalance.getFiatBalance() != null) {
                    bigDecimal = bigDecimal.add(walletInfoBalance.getFiatBalance());
                    Intrinsics.checkExpressionValueIsNotNull(bigDecimal, "summaryBalanceFiat.add(walletInfo.fiatBalance)");
                }
            }
            View view = this.this$0.getView();
            if (view != null) {
                TextView textView = (TextView) view.findViewById(C1018R.C1021id.textViewGlobalBalance);
                if (textView != null) {
                    textView.setText(BigDecimalKt.toFiatString$default(bigDecimal, GlobalBalanceView.access$getPresenter$p(this.this$0).getGetDefaultCurrencyInteractor().getDefaultFiatCurrency(), false, 2, null));
                }
            }
        }
    }
}
