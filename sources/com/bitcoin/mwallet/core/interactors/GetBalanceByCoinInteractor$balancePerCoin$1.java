package com.bitcoin.mwallet.core.interactors;

import com.bitcoin.bitcoink.p008tx.Satoshis;
import com.bitcoin.mwallet.core.models.Coin;
import com.bitcoin.mwallet.core.models.CoinBalance;
import com.bitcoin.mwallet.core.models.exchangerate.FiatExchangeRate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Currency;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000(\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0016\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u0003\u0018\u00010\u00012\u0014\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b\u0018\u00010\u00062\u0014\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\n\u0018\u00010\u0006H\n¢\u0006\u0002\b\u000b"}, mo37405d2 = {"<anonymous>", "Lkotlin/Pair;", "Ljava/util/Currency;", "", "Lcom/bitcoin/mwallet/core/models/CoinBalance;", "satoshis", "", "Lcom/bitcoin/mwallet/core/models/Coin;", "Lcom/bitcoin/bitcoink/tx/Satoshis;", "exchangeRates", "Lcom/bitcoin/mwallet/core/models/exchangerate/FiatExchangeRate;", "invoke"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: GetBalanceByCoinInteractor.kt */
final class GetBalanceByCoinInteractor$balancePerCoin$1 extends Lambda implements Function2<Map<Coin, ? extends Satoshis>, Map<Coin, ? extends FiatExchangeRate>, Pair<? extends Currency, ? extends List<? extends CoinBalance>>> {
    final /* synthetic */ Currency $currency;

    GetBalanceByCoinInteractor$balancePerCoin$1(Currency currency) {
        this.$currency = currency;
        super(2);
    }

    @Nullable
    public final Pair<Currency, List<CoinBalance>> invoke(@Nullable Map<Coin, Satoshis> map, @Nullable Map<Coin, FiatExchangeRate> map2) {
        if (map == null || map2 == null) {
            return null;
        }
        Iterable<Entry> entrySet = map.entrySet();
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(entrySet, 10));
        for (Entry entry : entrySet) {
            Coin coin = (Coin) entry.getKey();
            Satoshis satoshis = (Satoshis) entry.getValue();
            FiatExchangeRate fiatExchangeRate = (FiatExchangeRate) map2.get(coin);
            arrayList.add(new CoinBalance(coin, satoshis, fiatExchangeRate != null ? fiatExchangeRate.toFiat(satoshis) : null));
        }
        return new Pair<>(this.$currency, (List) arrayList);
    }
}
