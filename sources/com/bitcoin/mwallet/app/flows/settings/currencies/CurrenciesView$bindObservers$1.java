package com.bitcoin.mwallet.app.flows.settings.currencies;

import androidx.lifecycle.Observer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Currency;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import org.bitcoinj.utils.MonetaryFormat;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u000122\u0010\u0002\u001a.\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004 \u0007*\u0016\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\b"}, mo37405d2 = {"<anonymous>", "", "currencies", "", "Lkotlin/Pair;", "Ljava/util/Currency;", "", "kotlin.jvm.PlatformType", "onChanged"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: CurrenciesView.kt */
final class CurrenciesView$bindObservers$1<T> implements Observer<List<? extends Pair<? extends Currency, ? extends Boolean>>> {
    final /* synthetic */ CurrencyPreferenceAdapter $adapter;

    CurrenciesView$bindObservers$1(CurrencyPreferenceAdapter currencyPreferenceAdapter) {
        this.$adapter = currencyPreferenceAdapter;
    }

    public final void onChanged(List<Pair<Currency, Boolean>> list) {
        Intrinsics.checkExpressionValueIsNotNull(list, "currencies");
        Iterable iterable = list;
        Collection arrayList = new ArrayList();
        for (Object next : iterable) {
            Pair pair = (Pair) next;
            boolean z = true;
            if (!(!Intrinsics.areEqual((Object) ((Currency) pair.getFirst()).getCurrencyCode(), (Object) MonetaryFormat.CODE_BTC)) || !(!Intrinsics.areEqual((Object) ((Currency) pair.getFirst()).getCurrencyCode(), (Object) "BTC"))) {
                z = false;
            }
            if (z) {
                arrayList.add(next);
            }
        }
        this.$adapter.submitList((List) arrayList);
    }
}
