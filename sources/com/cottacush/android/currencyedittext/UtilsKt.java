package com.cottacush.android.currencyedittext;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\u001a\u001e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0001¨\u0006\u0005"}, mo37405d2 = {"parseMoneyValue", "", "value", "groupingSeparator", "currencySymbol", "com.cottacush.CurrencyEditText"}, mo37406k = 2, mo37407mv = {1, 1, 15})
/* compiled from: Utils.kt */
public final class UtilsKt {
    @NotNull
    public static final String parseMoneyValue(@NotNull String str, @NotNull String str2, @NotNull String str3) {
        Intrinsics.checkParameterIsNotNull(str, "value");
        Intrinsics.checkParameterIsNotNull(str2, "groupingSeparator");
        Intrinsics.checkParameterIsNotNull(str3, "currencySymbol");
        return StringsKt.replace$default(StringsKt.replace$default(str, str2, "", false, 4, (Object) null), str3, "", false, 4, (Object) null);
    }
}
