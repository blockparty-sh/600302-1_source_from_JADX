package com.bitcoin.mwallet.app.components.globalbalance.interactor;

import androidx.arch.core.util.Function;
import com.bitcoin.mwallet.core.extensions.BigDecimalKt;
import com.bitcoin.mwallet.core.models.CoinBalance;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Currency;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0018\u0010\u0002\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0003H\n¢\u0006\u0002\b\u0007"}, mo37405d2 = {"<anonymous>", "", "balances", "Lkotlin/Pair;", "Ljava/util/Currency;", "", "Lcom/bitcoin/mwallet/core/models/CoinBalance;", "apply"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: GlobalBalanceInteractor.kt */
final class GlobalBalanceInteractor$globalBalance$1<I, O> implements Function<X, Y> {
    public static final GlobalBalanceInteractor$globalBalance$1 INSTANCE = new GlobalBalanceInteractor$globalBalance$1();

    GlobalBalanceInteractor$globalBalance$1() {
    }

    @NotNull
    public final String apply(@NotNull Pair<Currency, ? extends List<CoinBalance>> pair) {
        Intrinsics.checkParameterIsNotNull(pair, "balances");
        Iterable<CoinBalance> iterable = (Iterable) pair.getSecond();
        Collection arrayList = new ArrayList();
        for (CoinBalance fiat : iterable) {
            BigDecimal fiat2 = fiat.getFiat();
            if (fiat2 != null) {
                arrayList.add(fiat2);
            }
        }
        Iterable<BigDecimal> iterable2 = (List) arrayList;
        BigDecimal bigDecimal = BigDecimal.ZERO;
        for (BigDecimal add : iterable2) {
            bigDecimal = bigDecimal.add(add);
        }
        Intrinsics.checkExpressionValueIsNotNull(bigDecimal, "balances.second\n        …al.ZERO, BigDecimal::add)");
        return BigDecimalKt.toFiatString$default(bigDecimal, (Currency) pair.getFirst(), false, 2, null);
    }
}
