package com.bitcoin.mwallet.core.interactors;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import com.bitcoin.bitcoink.p008tx.Satoshis;
import com.bitcoin.mwallet.app.android.LiveDataKt;
import com.bitcoin.mwallet.app.android.SingleSourceMediatorLiveData;
import com.bitcoin.mwallet.core.models.Coin;
import com.bitcoin.mwallet.core.models.CoinBalance;
import com.bitcoin.mwallet.core.models.wallet.WalletInfoBalance;
import com.bitcoin.mwallet.core.repositories.WalletRepository;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import java.util.Currency;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ&\u0010\u000e\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00110\u000f0\n2\u0006\u0010\u0013\u001a\u00020\u0010J\u001a\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00110\n2\u0006\u0010\u0016\u001a\u00020\fJ\"\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00110\n2\u0006\u0010\u0016\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\u0010R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R \u0010\t\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b0\nX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/interactors/GetBalanceByCoinInteractor;", "", "walletRepository", "Lcom/bitcoin/mwallet/core/repositories/WalletRepository;", "getCurrentExchangeRateInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetCurrentExchangeRateInteractor;", "getDefaultCurrencyInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetDefaultCurrencyInteractor;", "(Lcom/bitcoin/mwallet/core/repositories/WalletRepository;Lcom/bitcoin/mwallet/core/interactors/GetCurrentExchangeRateInteractor;Lcom/bitcoin/mwallet/core/interactors/GetDefaultCurrencyInteractor;)V", "satoshisPerCoin", "Landroidx/lifecycle/LiveData;", "", "Lcom/bitcoin/mwallet/core/models/Coin;", "Lcom/bitcoin/bitcoink/tx/Satoshis;", "balancePerCoin", "Lkotlin/Pair;", "Ljava/util/Currency;", "", "Lcom/bitcoin/mwallet/core/models/CoinBalance;", "currency", "walletBalancesOfCoin", "Lcom/bitcoin/mwallet/core/models/wallet/WalletInfoBalance;", "coin", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: GetBalanceByCoinInteractor.kt */
public final class GetBalanceByCoinInteractor {
    private final GetCurrentExchangeRateInteractor getCurrentExchangeRateInteractor;
    private final GetDefaultCurrencyInteractor getDefaultCurrencyInteractor;
    private final LiveData<Map<Coin, Satoshis>> satoshisPerCoin;
    private final WalletRepository walletRepository;

    public GetBalanceByCoinInteractor(@NotNull WalletRepository walletRepository2, @NotNull GetCurrentExchangeRateInteractor getCurrentExchangeRateInteractor2, @NotNull GetDefaultCurrencyInteractor getDefaultCurrencyInteractor2) {
        Intrinsics.checkParameterIsNotNull(walletRepository2, "walletRepository");
        Intrinsics.checkParameterIsNotNull(getCurrentExchangeRateInteractor2, "getCurrentExchangeRateInteractor");
        Intrinsics.checkParameterIsNotNull(getDefaultCurrencyInteractor2, "getDefaultCurrencyInteractor");
        this.walletRepository = walletRepository2;
        this.getCurrentExchangeRateInteractor = getCurrentExchangeRateInteractor2;
        this.getDefaultCurrencyInteractor = getDefaultCurrencyInteractor2;
        LiveData<Map<Coin, Satoshis>> map = Transformations.map(this.walletRepository.getWalletInfoSatoshis(), GetBalanceByCoinInteractor$satoshisPerCoin$1.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(map, "Transformations.map(wall…              }\n        }");
        this.satoshisPerCoin = map;
    }

    @NotNull
    public final LiveData<List<WalletInfoBalance>> walletBalancesOfCoin(@NotNull Coin coin, @NotNull Currency currency) {
        Intrinsics.checkParameterIsNotNull(coin, "coin");
        Intrinsics.checkParameterIsNotNull(currency, Param.CURRENCY);
        return LiveDataKt.combineLatestIgnoreNull(this.walletRepository.walletInfoSatoshis(coin), this.getCurrentExchangeRateInteractor.getCurrentExchangeRate(currency), new GetBalanceByCoinInteractor$walletBalancesOfCoin$1(currency));
    }

    @NotNull
    public final LiveData<List<WalletInfoBalance>> walletBalancesOfCoin(@NotNull Coin coin) {
        Intrinsics.checkParameterIsNotNull(coin, "coin");
        SingleSourceMediatorLiveData singleSourceMediatorLiveData = new SingleSourceMediatorLiveData();
        singleSourceMediatorLiveData.addSource(this.getDefaultCurrencyInteractor.defaultFiatCurrency(new C1258xa35da926(this, singleSourceMediatorLiveData, coin)), GetBalanceByCoinInteractor$walletBalancesOfCoin$2.INSTANCE);
        return singleSourceMediatorLiveData;
    }

    @NotNull
    public final LiveData<Pair<Currency, List<CoinBalance>>> balancePerCoin(@NotNull Currency currency) {
        Intrinsics.checkParameterIsNotNull(currency, Param.CURRENCY);
        return LiveDataKt.combineLatestIgnoreNull(this.satoshisPerCoin, this.getCurrentExchangeRateInteractor.getCurrentExchangeRate(currency), new GetBalanceByCoinInteractor$balancePerCoin$1(currency));
    }
}
