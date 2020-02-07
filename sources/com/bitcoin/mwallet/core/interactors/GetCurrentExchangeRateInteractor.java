package com.bitcoin.mwallet.core.interactors;

import androidx.lifecycle.LiveData;
import com.bitcoin.mwallet.core.models.Coin;
import com.bitcoin.mwallet.core.models.exchangerate.FiatExchangeRate;
import com.bitcoin.mwallet.core.repositories.ExchangeRateRepository;
import java.util.Currency;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010$\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001c\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\rH@ø\u0001\u0000¢\u0006\u0002\u0010\u000eJ#\u0010\u000f\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH@ø\u0001\u0000¢\u0006\u0002\u0010\u0010J \u0010\u000f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00070\u00110\u00062\u0006\u0010\n\u001a\u00020\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u0012"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/interactors/GetCurrentExchangeRateInteractor;", "", "rates", "Lcom/bitcoin/mwallet/core/repositories/ExchangeRateRepository;", "(Lcom/bitcoin/mwallet/core/repositories/ExchangeRateRepository;)V", "currentExchangeRate", "Landroidx/lifecycle/LiveData;", "Lcom/bitcoin/mwallet/core/models/exchangerate/FiatExchangeRate;", "coin", "Lcom/bitcoin/mwallet/core/models/Coin;", "fiatCurrency", "Ljava/util/Currency;", "getAvailableCurrencies", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCurrentExchangeRate", "(Lcom/bitcoin/mwallet/core/models/Coin;Ljava/util/Currency;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: GetCurrentExchangeRateInteractor.kt */
public class GetCurrentExchangeRateInteractor {
    private final ExchangeRateRepository rates;

    public GetCurrentExchangeRateInteractor(@NotNull ExchangeRateRepository exchangeRateRepository) {
        Intrinsics.checkParameterIsNotNull(exchangeRateRepository, "rates");
        this.rates = exchangeRateRepository;
    }

    @Nullable
    public final Object getCurrentExchangeRate(@NotNull Coin coin, @NotNull Currency currency, @NotNull Continuation<? super FiatExchangeRate> continuation) {
        return this.rates.getCurrentExchangeRate(coin, currency, continuation);
    }

    @Nullable
    public final Object getAvailableCurrencies(@NotNull Continuation<? super List<Currency>> continuation) {
        return this.rates.getAvailableCurrencies(continuation);
    }

    @NotNull
    public final LiveData<Map<Coin, FiatExchangeRate>> getCurrentExchangeRate(@NotNull Currency currency) {
        Intrinsics.checkParameterIsNotNull(currency, "fiatCurrency");
        return this.rates.getCurrentExchangeRate(currency);
    }

    @NotNull
    public final LiveData<FiatExchangeRate> currentExchangeRate(@NotNull Coin coin, @NotNull Currency currency) {
        Intrinsics.checkParameterIsNotNull(coin, "coin");
        Intrinsics.checkParameterIsNotNull(currency, "fiatCurrency");
        return this.rates.currentExchangeRate(coin, currency);
    }
}
