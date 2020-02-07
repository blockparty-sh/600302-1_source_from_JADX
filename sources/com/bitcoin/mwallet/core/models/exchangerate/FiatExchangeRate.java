package com.bitcoin.mwallet.core.models.exchangerate;

import com.bitcoin.bitcoink.p008tx.Satoshis;
import com.bitcoin.mwallet.core.extensions.BigDecimalKt;
import com.bitcoin.mwallet.core.models.Coin;
import java.math.BigDecimal;
import java.util.Currency;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u000e\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u0011J\u0018\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0014\u001a\u00020\u0015R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0016"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/models/exchangerate/FiatExchangeRate;", "", "coin", "Lcom/bitcoin/mwallet/core/models/Coin;", "fiatCurrency", "Ljava/util/Currency;", "rate", "Ljava/math/BigDecimal;", "(Lcom/bitcoin/mwallet/core/models/Coin;Ljava/util/Currency;Ljava/math/BigDecimal;)V", "getCoin", "()Lcom/bitcoin/mwallet/core/models/Coin;", "getFiatCurrency", "()Ljava/util/Currency;", "getRate", "()Ljava/math/BigDecimal;", "toFiat", "satoshis", "Lcom/bitcoin/bitcoink/tx/Satoshis;", "toFiatString", "", "withoutSymbol", "", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: FiatExchangeRate.kt */
public final class FiatExchangeRate {
    @NotNull
    private final Coin coin;
    @NotNull
    private final Currency fiatCurrency;
    @NotNull
    private final BigDecimal rate;

    public FiatExchangeRate(@NotNull Coin coin2, @NotNull Currency currency, @NotNull BigDecimal bigDecimal) {
        Intrinsics.checkParameterIsNotNull(coin2, "coin");
        Intrinsics.checkParameterIsNotNull(currency, "fiatCurrency");
        Intrinsics.checkParameterIsNotNull(bigDecimal, "rate");
        this.coin = coin2;
        this.fiatCurrency = currency;
        this.rate = bigDecimal;
    }

    @NotNull
    public final Coin getCoin() {
        return this.coin;
    }

    @NotNull
    public final Currency getFiatCurrency() {
        return this.fiatCurrency;
    }

    @NotNull
    public final BigDecimal getRate() {
        return this.rate;
    }

    @NotNull
    public final BigDecimal toFiat(@NotNull Satoshis satoshis) {
        Intrinsics.checkParameterIsNotNull(satoshis, "satoshis");
        BigDecimal multiply = satoshis.getCoins().multiply(this.rate);
        Intrinsics.checkExpressionValueIsNotNull(multiply, "satoshis.coins.multiply(rate)");
        return multiply;
    }

    @NotNull
    public static /* synthetic */ String toFiatString$default(FiatExchangeRate fiatExchangeRate, Satoshis satoshis, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return fiatExchangeRate.toFiatString(satoshis, z);
    }

    @NotNull
    public final String toFiatString(@NotNull Satoshis satoshis, boolean z) {
        Intrinsics.checkParameterIsNotNull(satoshis, "satoshis");
        return BigDecimalKt.toFiatString(toFiat(satoshis), this.fiatCurrency, z);
    }
}
