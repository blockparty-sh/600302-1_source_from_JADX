package com.bitcoin.mwallet.app.android;

import androidx.appcompat.app.ActionBar;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, mo37405d2 = {"setShown", "", "Landroidx/appcompat/app/ActionBar;", "isShown", "", "app_replaceRelease"}, mo37406k = 2, mo37407mv = {1, 1, 15})
/* compiled from: ActionBarExtensions.kt */
public final class ActionBarExtensionsKt {
    public static final void setShown(@NotNull ActionBar actionBar, boolean z) {
        Intrinsics.checkParameterIsNotNull(actionBar, "$this$setShown");
        if (z) {
            actionBar.show();
        } else {
            actionBar.hide();
        }
    }
}
