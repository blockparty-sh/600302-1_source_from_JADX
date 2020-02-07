package com.bitcoin.mwallet.core.interactors;

import com.bitcoin.mwallet.core.models.Coin;
import com.bitcoin.mwallet.core.models.exchangerate.FiatExchangeRate;
import com.bitcoin.mwallet.core.models.wallet.WalletInfoBalance;
import com.bitcoin.mwallet.core.models.wallet.WalletInfoSatoshis;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Currency;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000 \n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00012\u000e\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00012\u0014\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b\u0018\u00010\u0006H\n¢\u0006\u0002\b\t"}, mo37405d2 = {"<anonymous>", "", "Lcom/bitcoin/mwallet/core/models/wallet/WalletInfoBalance;", "walletSatoshis", "Lcom/bitcoin/mwallet/core/models/wallet/WalletInfoSatoshis;", "exchangeRates", "", "Lcom/bitcoin/mwallet/core/models/Coin;", "Lcom/bitcoin/mwallet/core/models/exchangerate/FiatExchangeRate;", "invoke"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: GetBalanceByCoinInteractor.kt */
final class GetBalanceByCoinInteractor$walletBalancesOfCoin$1 extends Lambda implements Function2<List<? extends WalletInfoSatoshis>, Map<Coin, ? extends FiatExchangeRate>, List<? extends WalletInfoBalance>> {
    final /* synthetic */ Currency $currency;

    GetBalanceByCoinInteractor$walletBalancesOfCoin$1(Currency currency) {
        this.$currency = currency;
        super(2);
    }

    @Nullable
    public final List<WalletInfoBalance> invoke(@Nullable List<WalletInfoSatoshis> list, @Nullable Map<Coin, FiatExchangeRate> map) {
        if (list == null || map == null) {
            return null;
        }
        Iterable<WalletInfoSatoshis> iterable = list;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (WalletInfoSatoshis walletInfoBalance : iterable) {
            arrayList.add(walletInfoBalance.toWalletInfoBalance(map, this.$currency));
        }
        return (List) arrayList;
    }
}
