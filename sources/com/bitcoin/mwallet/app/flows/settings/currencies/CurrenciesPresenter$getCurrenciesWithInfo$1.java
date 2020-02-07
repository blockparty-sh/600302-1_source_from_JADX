package com.bitcoin.mwallet.app.flows.settings.currencies;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Currency;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u0016\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002\u0018\u00010\u00012\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\n¢\u0006\u0002\b\b"}, mo37405d2 = {"<anonymous>", "", "Lkotlin/Pair;", "Ljava/util/Currency;", "", "currencies", "selectedCurrency", "", "invoke"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: CurrenciesPresenter.kt */
final class CurrenciesPresenter$getCurrenciesWithInfo$1 extends Lambda implements Function2<List<? extends Currency>, String, List<? extends Pair<? extends Currency, ? extends Boolean>>> {
    public static final CurrenciesPresenter$getCurrenciesWithInfo$1 INSTANCE = new CurrenciesPresenter$getCurrenciesWithInfo$1();

    CurrenciesPresenter$getCurrenciesWithInfo$1() {
        super(2);
    }

    @Nullable
    public final List<Pair<Currency, Boolean>> invoke(@Nullable List<Currency> list, @Nullable String str) {
        if (list == null) {
            return null;
        }
        Iterable<Currency> iterable = list;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (Currency currency : iterable) {
            arrayList.add(new Pair(currency, Boolean.valueOf(Intrinsics.areEqual((Object) str, (Object) currency.getCurrencyCode()))));
        }
        return (List) arrayList;
    }
}
