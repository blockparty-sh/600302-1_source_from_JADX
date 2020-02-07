package com.bitcoin.mwallet.core.models.exchangerate;

import com.bitcoin.bitcoink.p008tx.Satoshis;
import com.bitcoin.bitcoink.p008tx.Satoshis.Companion;
import java.math.BigDecimal;
import java.math.RoundingMode;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a\u0018\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u0004\u0018\u00010\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u001a\u0014\u0010\u0005\u001a\u00020\u0006*\u0004\u0018\u00010\u00022\u0006\u0010\u0007\u001a\u00020\u0001¨\u0006\b"}, mo37405d2 = {"fiatToSatoshis", "Lcom/bitcoin/bitcoink/tx/Satoshis;", "Lcom/bitcoin/mwallet/core/models/exchangerate/FiatExchangeRate;", "fiatValue", "Ljava/math/BigDecimal;", "toFiatString", "", "satoshis", "app_replaceRelease"}, mo37406k = 2, mo37407mv = {1, 1, 15})
/* compiled from: FiatExchangeRate.kt */
public final class FiatExchangeRateKt {
    @NotNull
    public static final String toFiatString(@Nullable FiatExchangeRate fiatExchangeRate, @NotNull Satoshis satoshis) {
        Intrinsics.checkParameterIsNotNull(satoshis, "satoshis");
        return fiatExchangeRate == null ? "-,--" : FiatExchangeRate.toFiatString$default(fiatExchangeRate, satoshis, false, 2, null);
    }

    @Nullable
    public static final Satoshis fiatToSatoshis(@Nullable FiatExchangeRate fiatExchangeRate, @Nullable BigDecimal bigDecimal) {
        if (fiatExchangeRate == null || bigDecimal == null) {
            return null;
        }
        Companion companion = Satoshis.Companion;
        BigDecimal divide = bigDecimal.divide(fiatExchangeRate.getRate(), 8, RoundingMode.HALF_UP);
        Intrinsics.checkExpressionValueIsNotNull(divide, "fiatValue.divide(this.ra… 8, RoundingMode.HALF_UP)");
        return companion.fromCoins(divide);
    }
}
