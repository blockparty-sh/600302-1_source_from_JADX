package com.bitcoin.mwallet.core.extensions;

import com.google.firebase.analytics.FirebaseAnalytics.Param;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Currency;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000(\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001\u001a\n\u0010\u0003\u001a\u00020\u0004*\u00020\u0001\u001a\n\u0010\u0005\u001a\u00020\u0004*\u00020\u0001\u001a\u0016\u0010\u0006\u001a\u00020\u0007*\u00020\u00012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007\u001a\u001e\u0010\t\u001a\u00020\u0007*\u0004\u0018\u00010\u00012\u0006\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r\u001a\n\u0010\u000e\u001a\u00020\u0007*\u00020\u0001\u001a\n\u0010\u000f\u001a\u00020\u0007*\u00020\u0001\u001a\n\u0010\u0010\u001a\u00020\u0007*\u00020\u0001¨\u0006\u0011"}, mo37405d2 = {"divideToCoins", "Ljava/math/BigDecimal;", "divisor", "getNumberOfDecimalPlaces", "", "getNumberOfWholeDigits", "toCoinString", "", "ticker", "toFiatString", "currency", "Ljava/util/Currency;", "withoutSymbol", "", "toPlainCoinString", "toPlainFiatString", "toPlainGroupedString", "app_replaceRelease"}, mo37406k = 2, mo37407mv = {1, 1, 15})
/* compiled from: BigDecimal.kt */
public final class BigDecimalKt {
    @NotNull
    public static final BigDecimal divideToCoins(@NotNull BigDecimal bigDecimal, @NotNull BigDecimal bigDecimal2) {
        Intrinsics.checkParameterIsNotNull(bigDecimal, "$this$divideToCoins");
        Intrinsics.checkParameterIsNotNull(bigDecimal2, "divisor");
        BigDecimal divide = bigDecimal.divide(bigDecimal2, 8, RoundingMode.HALF_UP);
        Intrinsics.checkExpressionValueIsNotNull(divide, "this.divide(divisor, 8, RoundingMode.HALF_UP)");
        return divide;
    }

    public static final int getNumberOfDecimalPlaces(@NotNull BigDecimal bigDecimal) {
        Intrinsics.checkParameterIsNotNull(bigDecimal, "$this$getNumberOfDecimalPlaces");
        int i = 0;
        if (bigDecimal.doubleValue() == 0.0d) {
            return 0;
        }
        String plainString = bigDecimal.stripTrailingZeros().toPlainString();
        Intrinsics.checkExpressionValueIsNotNull(plainString, "string");
        int indexOf$default = StringsKt.indexOf$default((CharSequence) plainString, ".", 0, false, 6, (Object) null);
        if (indexOf$default >= 0) {
            i = (plainString.length() - indexOf$default) - 1;
        }
        return i;
    }

    public static final int getNumberOfWholeDigits(@NotNull BigDecimal bigDecimal) {
        Intrinsics.checkParameterIsNotNull(bigDecimal, "$this$getNumberOfWholeDigits");
        String plainString = bigDecimal.stripTrailingZeros().toPlainString();
        Intrinsics.checkExpressionValueIsNotNull(plainString, "string");
        if (StringsKt.indexOf$default((CharSequence) plainString, ".", 0, false, 6, (Object) null) == -1) {
            return plainString.length();
        }
        return (plainString.length() - getNumberOfDecimalPlaces(bigDecimal)) - 1;
    }

    @NotNull
    public static /* synthetic */ String toCoinString$default(BigDecimal bigDecimal, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        return toCoinString(bigDecimal, str);
    }

    @NotNull
    public static final String toCoinString(@NotNull BigDecimal bigDecimal, @Nullable String str) {
        Intrinsics.checkParameterIsNotNull(bigDecimal, "$this$toCoinString");
        String str2 = str != null ? "%1$s %2$s" : "%1$s";
        NumberFormat numberInstance = NumberFormat.getNumberInstance();
        Intrinsics.checkExpressionValueIsNotNull(numberInstance, "NumberFormat.getNumberInstance()");
        numberInstance.setGroupingUsed(true);
        numberInstance.setMaximumFractionDigits(8);
        numberInstance.setMinimumFractionDigits(0);
        String format = numberInstance.format(bigDecimal.doubleValue());
        Intrinsics.checkExpressionValueIsNotNull(format, "numberFormatter.format(this.toDouble())");
        int length = format.length();
        if (getNumberOfDecimalPlaces(bigDecimal) > 3) {
            if (getNumberOfDecimalPlaces(bigDecimal) <= 3 || getNumberOfDecimalPlaces(bigDecimal) > 6) {
                String str3 = "null cannot be cast to non-null type java.lang.String";
                String str4 = "(this as java.lang.String).substring(startIndex)";
                if (getNumberOfDecimalPlaces(bigDecimal) == 8) {
                    StringBuilder sb = new StringBuilder();
                    int i = length - 5;
                    sb.append(StringsKt.substring(format, RangesKt.until(0, i)));
                    sb.append(' ');
                    int i2 = length - 2;
                    sb.append(StringsKt.substring(format, RangesKt.until(i, i2)));
                    sb.append(' ');
                    if (format != null) {
                        String substring = format.substring(i2);
                        Intrinsics.checkExpressionValueIsNotNull(substring, str4);
                        sb.append(substring);
                        format = sb.toString();
                    } else {
                        throw new TypeCastException(str3);
                    }
                } else {
                    StringBuilder sb2 = new StringBuilder();
                    int i3 = length - 4;
                    sb2.append(StringsKt.substring(format, RangesKt.until(0, i3)));
                    sb2.append(' ');
                    int i4 = length - 1;
                    sb2.append(StringsKt.substring(format, RangesKt.until(i3, i4)));
                    sb2.append(' ');
                    if (format != null) {
                        String substring2 = format.substring(i4);
                        Intrinsics.checkExpressionValueIsNotNull(substring2, str4);
                        sb2.append(substring2);
                        format = sb2.toString();
                    } else {
                        throw new TypeCastException(str3);
                    }
                }
            } else {
                StringBuilder sb3 = new StringBuilder();
                sb3.append(StringsKt.substring(format, RangesKt.until(0, length - (getNumberOfDecimalPlaces(bigDecimal) - 3))));
                sb3.append(' ');
                sb3.append(StringsKt.substring(format, RangesKt.until(length - (getNumberOfDecimalPlaces(bigDecimal) - 3), length)));
                format = sb3.toString();
            }
        }
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Object[] objArr = {format, str};
        String format2 = String.format(str2, Arrays.copyOf(objArr, objArr.length));
        Intrinsics.checkExpressionValueIsNotNull(format2, "java.lang.String.format(format, *args)");
        return format2;
    }

    @NotNull
    public static final String toPlainCoinString(@NotNull BigDecimal bigDecimal) {
        Intrinsics.checkParameterIsNotNull(bigDecimal, "$this$toPlainCoinString");
        NumberFormat numberInstance = NumberFormat.getNumberInstance();
        Intrinsics.checkExpressionValueIsNotNull(numberInstance, "NumberFormat.getNumberInstance()");
        numberInstance.setGroupingUsed(true);
        numberInstance.setMaximumFractionDigits(8);
        numberInstance.setMinimumFractionDigits(8);
        String format = numberInstance.format(bigDecimal.doubleValue());
        Intrinsics.checkExpressionValueIsNotNull(format, "numberFormatter.format(this.toDouble())");
        return format;
    }

    @NotNull
    public static final String toPlainFiatString(@NotNull BigDecimal bigDecimal) {
        Intrinsics.checkParameterIsNotNull(bigDecimal, "$this$toPlainFiatString");
        NumberFormat numberInstance = NumberFormat.getNumberInstance();
        Intrinsics.checkExpressionValueIsNotNull(numberInstance, "NumberFormat.getNumberInstance()");
        numberInstance.setGroupingUsed(true);
        numberInstance.setMaximumFractionDigits(8);
        numberInstance.setMinimumFractionDigits(2);
        String format = numberInstance.format(bigDecimal.doubleValue());
        Intrinsics.checkExpressionValueIsNotNull(format, "numberFormatter.format(this.toDouble())");
        return format;
    }

    @NotNull
    public static final String toPlainGroupedString(@NotNull BigDecimal bigDecimal) {
        Intrinsics.checkParameterIsNotNull(bigDecimal, "$this$toPlainGroupedString");
        NumberFormat numberInstance = NumberFormat.getNumberInstance();
        Intrinsics.checkExpressionValueIsNotNull(numberInstance, "NumberFormat.getNumberInstance()");
        numberInstance.setGroupingUsed(true);
        numberInstance.setMaximumFractionDigits(8);
        numberInstance.setMinimumFractionDigits(0);
        String format = numberInstance.format(bigDecimal.doubleValue());
        Intrinsics.checkExpressionValueIsNotNull(format, "numberFormatter.format(this.toDouble())");
        return format;
    }

    @NotNull
    public static /* synthetic */ String toFiatString$default(BigDecimal bigDecimal, Currency currency, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return toFiatString(bigDecimal, currency, z);
    }

    @NotNull
    public static final String toFiatString(@Nullable BigDecimal bigDecimal, @NotNull Currency currency, boolean z) {
        Intrinsics.checkParameterIsNotNull(currency, Param.CURRENCY);
        NumberFormat currencyInstance = NumberFormat.getCurrencyInstance(new Locale(currency.getCurrencyCode()));
        Intrinsics.checkExpressionValueIsNotNull(currencyInstance, "NumberFormat.getCurrency…e(currency.currencyCode))");
        currencyInstance.setCurrency(currency);
        if (z) {
            if (currencyInstance != null) {
                DecimalFormat decimalFormat = (DecimalFormat) currencyInstance;
                DecimalFormatSymbols decimalFormatSymbols = decimalFormat.getDecimalFormatSymbols();
                Intrinsics.checkExpressionValueIsNotNull(decimalFormatSymbols, "(currencyFormatter as De…mat).decimalFormatSymbols");
                decimalFormatSymbols.setCurrencySymbol("");
                decimalFormat.setDecimalFormatSymbols(decimalFormatSymbols);
            } else {
                throw new TypeCastException("null cannot be cast to non-null type java.text.DecimalFormat");
            }
        }
        if (bigDecimal != null) {
            String format = currencyInstance.format(bigDecimal.doubleValue());
            Intrinsics.checkExpressionValueIsNotNull(format, "currencyFormatter.format(this.toDouble())");
            return format;
        }
        String format2 = currencyInstance.format(0.0d);
        Intrinsics.checkExpressionValueIsNotNull(format2, "currencyFormatter.format(0.0)");
        return format2;
    }
}
