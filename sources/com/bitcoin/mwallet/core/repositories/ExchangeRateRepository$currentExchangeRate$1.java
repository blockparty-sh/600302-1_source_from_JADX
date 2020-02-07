package com.bitcoin.mwallet.core.repositories;

import androidx.arch.core.util.Function;
import com.bitcoin.mwallet.core.models.exchangerate.ExchangeRate;
import com.bitcoin.mwallet.core.models.exchangerate.FiatExchangeRate;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, mo37405d2 = {"<anonymous>", "Lcom/bitcoin/mwallet/core/models/exchangerate/FiatExchangeRate;", "exchangeRate", "Lcom/bitcoin/mwallet/core/models/exchangerate/ExchangeRate;", "apply"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: ExchangeRateRepository.kt */
final class ExchangeRateRepository$currentExchangeRate$1<I, O> implements Function<X, Y> {
    public static final ExchangeRateRepository$currentExchangeRate$1 INSTANCE = new ExchangeRateRepository$currentExchangeRate$1();

    ExchangeRateRepository$currentExchangeRate$1() {
    }

    @Nullable
    public final FiatExchangeRate apply(@Nullable ExchangeRate exchangeRate) {
        if (exchangeRate != null) {
            return ExchangeRateRepository.Companion.toFiatExchangeRate(exchangeRate);
        }
        return null;
    }
}
