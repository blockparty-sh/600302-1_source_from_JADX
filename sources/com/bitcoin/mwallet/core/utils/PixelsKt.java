package com.bitcoin.mwallet.core.utils;

import android.content.Context;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0016\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0001¨\u0006\u0005"}, mo37405d2 = {"pxFromDp", "", "context", "Landroid/content/Context;", "dp", "app_replaceRelease"}, mo37406k = 2, mo37407mv = {1, 1, 15})
/* compiled from: pixels.kt */
public final class PixelsKt {
    public static final float pxFromDp(@NotNull Context context, float f) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        return f * context.getResources().getDisplayMetrics().density;
    }
}
