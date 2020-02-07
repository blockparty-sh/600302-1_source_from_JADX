package org.koin.ext;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0010\u000e\n\u0002\b\u0003\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0003\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0004\u001a\u00020\u0002*\u00020\u0002¨\u0006\u0005"}, mo37405d2 = {"isFloat", "", "", "isInt", "quoted", "koin-core"}, mo37406k = 2, mo37407mv = {1, 1, 15})
/* compiled from: StringExt.kt */
public final class StringExtKt {
    public static final boolean isFloat(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "$this$isFloat");
        return StringsKt.toFloatOrNull(str) != null;
    }

    public static final boolean isInt(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "$this$isInt");
        return StringsKt.toIntOrNull(str) != null;
    }

    @NotNull
    public static final String quoted(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "$this$quoted");
        return StringsKt.replace$default(str, "\"", "", false, 4, (Object) null);
    }
}
