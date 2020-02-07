package com.bitcoin.mwallet.core.repositories;

import androidx.arch.core.util.Function;
import com.bitcoin.mwallet.core.extensions.StringKt;
import com.bitcoin.mwallet.core.models.Coin;
import com.bitcoin.mwallet.core.models.exchangerate.ExchangeRate;
import com.bitcoin.mwallet.core.models.exchangerate.FiatExchangeRate;
import java.util.Currency;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\n¢\u0006\u0002\b\u0007"}, mo37405d2 = {"<anonymous>", "", "Lcom/bitcoin/mwallet/core/models/Coin;", "Lcom/bitcoin/mwallet/core/models/exchangerate/FiatExchangeRate;", "rates", "", "Lcom/bitcoin/mwallet/core/models/exchangerate/ExchangeRate;", "apply"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: ExchangeRateRepository.kt */
final class ExchangeRateRepository$getCurrentExchangeRate$1<I, O> implements Function<X, Y> {
    public static final ExchangeRateRepository$getCurrentExchangeRate$1 INSTANCE = new ExchangeRateRepository$getCurrentExchangeRate$1();

    ExchangeRateRepository$getCurrentExchangeRate$1() {
    }

    @NotNull
    public final Map<Coin, FiatExchangeRate> apply(@NotNull List<ExchangeRate> list) {
        Intrinsics.checkParameterIsNotNull(list, "rates");
        Map<Coin, FiatExchangeRate> linkedHashMap = new LinkedHashMap<>();
        for (ExchangeRate exchangeRate : list) {
            Coin fromTicker = Coin.CREATOR.fromTicker(exchangeRate.getFromTicker());
            Currency tryParseCurrency = StringKt.tryParseCurrency(exchangeRate.getToTicker());
            if (!(fromTicker == null || tryParseCurrency == null)) {
                linkedHashMap.put(fromTicker, new FiatExchangeRate(fromTicker, tryParseCurrency, exchangeRate.getRate()));
            }
        }
        return linkedHashMap;
    }
}
