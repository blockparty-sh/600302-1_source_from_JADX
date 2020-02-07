package com.bitcoin.mwallet.core.utils;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import timber.log.Timber;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0012\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a'\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0005¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, mo37405d2 = {"logDuration", "T", "identifier", "", "func", "Lkotlin/Function0;", "(Ljava/lang/String;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "app_replaceRelease"}, mo37406k = 2, mo37407mv = {1, 1, 15})
/* compiled from: Measure.kt */
public final class MeasureKt {
    public static final <T> T logDuration(@NotNull String str, @NotNull Function0<? extends T> function0) {
        Intrinsics.checkParameterIsNotNull(str, "identifier");
        Intrinsics.checkParameterIsNotNull(function0, "func");
        long currentTimeMillis = System.currentTimeMillis();
        T invoke = function0.invoke();
        StringBuilder sb = new StringBuilder();
        sb.append("Duration: ");
        sb.append(str);
        sb.append("  ");
        sb.append(System.currentTimeMillis() - currentTimeMillis);
        sb.append(" ms");
        Timber.m426d(sb.toString(), new Object[0]);
        return invoke;
    }
}
