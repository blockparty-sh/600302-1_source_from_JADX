package com.bitcoin.mwallet.core.interactors;

import androidx.lifecycle.Observer;
import java.util.Currency;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, mo37405d2 = {"<anonymous>", "", "it", "Ljava/util/Currency;", "onChanged"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: GetBalanceByCoinInteractor.kt */
final class GetBalanceByCoinInteractor$walletBalancesOfCoin$2<T> implements Observer<S> {
    public static final GetBalanceByCoinInteractor$walletBalancesOfCoin$2 INSTANCE = new GetBalanceByCoinInteractor$walletBalancesOfCoin$2();

    GetBalanceByCoinInteractor$walletBalancesOfCoin$2() {
    }

    public final void onChanged(@Nullable Currency currency) {
    }
}
