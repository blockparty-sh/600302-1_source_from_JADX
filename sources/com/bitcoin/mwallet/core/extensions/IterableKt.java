package com.bitcoin.mwallet.core.extensions;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a-\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\u0005H\b¨\u0006\u0006"}, mo37405d2 = {"sumByLong", "", "T", "", "selector", "Lkotlin/Function1;", "app_replaceRelease"}, mo37406k = 2, mo37407mv = {1, 1, 15})
/* compiled from: Iterable.kt */
public final class IterableKt {
    public static final <T> long sumByLong(@NotNull Iterable<? extends T> iterable, @NotNull Function1<? super T, Long> function1) {
        Intrinsics.checkParameterIsNotNull(iterable, "$this$sumByLong");
        Intrinsics.checkParameterIsNotNull(function1, "selector");
        long j = 0;
        for (Object invoke : iterable) {
            j += ((Number) function1.invoke(invoke)).longValue();
        }
        return j;
    }
}
