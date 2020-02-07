package com.bitcoin.mwallet.app.components.walletassetsrecycler;

import com.bitcoin.mwallet.core.models.CoinBalance;
import com.bitcoin.mwallet.core.models.wallet.WalletInfoBalance;
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

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000 \n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u001c\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0002\u0018\u00010\u00012\u001a\u0010\u0006\u001a\u0016\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0004\u0018\u00010\u00022\u000e\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004H\n¢\u0006\u0002\b\t"}, mo37405d2 = {"<anonymous>", "", "Lkotlin/Pair;", "Lcom/bitcoin/mwallet/core/models/CoinBalance;", "", "Lcom/bitcoin/mwallet/core/models/wallet/WalletInfoBalance;", "balancesByCoin", "Ljava/util/Currency;", "balancesByWallet", "invoke"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: WalletAssetsRecyclerPresenter.kt */
final class WalletAssetsRecyclerPresenter$getLatestWalletInfoBalances$1 extends Lambda implements Function2<Pair<? extends Currency, ? extends List<? extends CoinBalance>>, List<? extends WalletInfoBalance>, List<Pair<? extends CoinBalance, ? extends List<? extends WalletInfoBalance>>>> {
    public static final WalletAssetsRecyclerPresenter$getLatestWalletInfoBalances$1 INSTANCE = new WalletAssetsRecyclerPresenter$getLatestWalletInfoBalances$1();

    WalletAssetsRecyclerPresenter$getLatestWalletInfoBalances$1() {
        super(2);
    }

    @Nullable
    public final List<Pair<CoinBalance, List<WalletInfoBalance>>> invoke(@Nullable Pair<Currency, ? extends List<CoinBalance>> pair, @Nullable List<WalletInfoBalance> list) {
        if (pair == null || list == null) {
            return null;
        }
        List<Pair<CoinBalance, List<WalletInfoBalance>>> arrayList = new ArrayList<>();
        for (CoinBalance coinBalance : (Iterable) pair.getSecond()) {
            Iterable iterable = list;
            Collection arrayList2 = new ArrayList();
            for (Object next : iterable) {
                if (Intrinsics.areEqual((Object) ((WalletInfoBalance) next).getCoin().getTicker(), (Object) coinBalance.getCoin().getTicker())) {
                    arrayList2.add(next);
                }
            }
            arrayList.add(new Pair(coinBalance, CollectionsKt.sortedWith((List) arrayList2, new C1084xe0c055e2())));
        }
        if (arrayList.size() > 1) {
            CollectionsKt.sortWith(arrayList, new C1083xddcf0483());
        }
        return arrayList;
    }
}
