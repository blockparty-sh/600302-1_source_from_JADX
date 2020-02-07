package com.bitcoin.mwallet.core.interactors;

import com.bitcoin.mwallet.app.android.LiveDataChangeListener;
import com.bitcoin.mwallet.app.android.SingleSourceMediatorLiveData;
import com.bitcoin.mwallet.core.models.Coin;
import java.util.Currency;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002H\u0016¨\u0006\u0006"}, mo37405d2 = {"com/bitcoin/mwallet/core/interactors/GetBalanceByCoinInteractor$walletBalancesOfCoin$defaultFiatCurrency$1", "Lcom/bitcoin/mwallet/app/android/LiveDataChangeListener;", "Ljava/util/Currency;", "onValueSet", "", "value", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* renamed from: com.bitcoin.mwallet.core.interactors.GetBalanceByCoinInteractor$walletBalancesOfCoin$defaultFiatCurrency$1 */
/* compiled from: GetBalanceByCoinInteractor.kt */
public final class C1258xa35da926 implements LiveDataChangeListener<Currency> {
    final /* synthetic */ SingleSourceMediatorLiveData $balance;
    final /* synthetic */ Coin $coin;
    final /* synthetic */ GetBalanceByCoinInteractor this$0;

    C1258xa35da926(GetBalanceByCoinInteractor getBalanceByCoinInteractor, SingleSourceMediatorLiveData singleSourceMediatorLiveData, Coin coin) {
        this.this$0 = getBalanceByCoinInteractor;
        this.$balance = singleSourceMediatorLiveData;
        this.$coin = coin;
    }

    public void onValueSet(@Nullable Currency currency) {
        if (currency != null) {
            this.$balance.replaceSource(this.this$0.walletBalancesOfCoin(this.$coin, currency));
        }
    }
}
