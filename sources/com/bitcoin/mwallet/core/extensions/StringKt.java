package com.bitcoin.mwallet.core.extensions;

import java.math.BigDecimal;
import java.util.Currency;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002\u001a\u000e\u0010\u0003\u001a\u0004\u0018\u00010\u0004*\u0004\u0018\u00010\u0002¨\u0006\u0005"}, mo37405d2 = {"tryParseBigDecimal", "Ljava/math/BigDecimal;", "", "tryParseCurrency", "Ljava/util/Currency;", "app_replaceRelease"}, mo37406k = 2, mo37407mv = {1, 1, 15})
/* compiled from: String.kt */
public final class StringKt {
    @Nullable
    public static final BigDecimal tryParseBigDecimal(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "$this$tryParseBigDecimal");
        try {
            return new BigDecimal(str);
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    @Nullable
    public static final Currency tryParseCurrency(@Nullable String str) {
        Currency currency = null;
        if (str == null) {
            return null;
        }
        try {
            currency = Currency.getInstance(str);
        } catch (IllegalArgumentException unused) {
        }
        return currency;
    }
}
