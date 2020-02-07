package com.bitcoin.mwallet.core.interactors;

import androidx.arch.core.util.Function;
import com.bitcoin.bitcoink.p008tx.Satoshis;
import com.bitcoin.mwallet.core.models.Coin;
import com.bitcoin.mwallet.core.models.wallet.WalletInfoSatoshis;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\n¢\u0006\u0002\b\u0007"}, mo37405d2 = {"<anonymous>", "", "Lcom/bitcoin/mwallet/core/models/Coin;", "Lcom/bitcoin/bitcoink/tx/Satoshis;", "walletSatoshis", "", "Lcom/bitcoin/mwallet/core/models/wallet/WalletInfoSatoshis;", "apply"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: GetBalanceByCoinInteractor.kt */
final class GetBalanceByCoinInteractor$satoshisPerCoin$1<I, O> implements Function<X, Y> {
    public static final GetBalanceByCoinInteractor$satoshisPerCoin$1 INSTANCE = new GetBalanceByCoinInteractor$satoshisPerCoin$1();

    GetBalanceByCoinInteractor$satoshisPerCoin$1() {
    }

    @NotNull
    public final Map<Coin, Satoshis> apply(@NotNull List<WalletInfoSatoshis> list) {
        Intrinsics.checkParameterIsNotNull(list, "walletSatoshis");
        Iterable iterable = list;
        Map linkedHashMap = new LinkedHashMap();
        for (Object next : iterable) {
            Coin coin = ((WalletInfoSatoshis) next).getCoin();
            Object obj = linkedHashMap.get(coin);
            if (obj == null) {
                obj = new ArrayList();
                linkedHashMap.put(coin, obj);
            }
            ((List) obj).add(next);
        }
        Map<Coin, Satoshis> linkedHashMap2 = new LinkedHashMap<>(MapsKt.mapCapacity(linkedHashMap.size()));
        for (Entry entry : linkedHashMap.entrySet()) {
            Object key = entry.getKey();
            long j = 0;
            for (WalletInfoSatoshis satoshis : (Iterable) entry.getValue()) {
                j += satoshis.getSatoshis().getSatoshis();
            }
            linkedHashMap2.put(key, new Satoshis(j));
        }
        return linkedHashMap2;
    }
}
