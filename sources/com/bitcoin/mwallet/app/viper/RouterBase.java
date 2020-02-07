package com.bitcoin.mwallet.app.viper;

import androidx.navigation.NavController;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\t"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/viper/RouterBase;", "", "()V", "navController", "Landroidx/navigation/NavController;", "getNavController", "()Landroidx/navigation/NavController;", "setNavController", "(Landroidx/navigation/NavController;)V", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: RouterBase.kt */
public abstract class RouterBase {
    @NotNull
    public NavController navController;

    @NotNull
    public final NavController getNavController() {
        NavController navController2 = this.navController;
        if (navController2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("navController");
        }
        return navController2;
    }

    public final void setNavController(@NotNull NavController navController2) {
        Intrinsics.checkParameterIsNotNull(navController2, "<set-?>");
        this.navController = navController2;
    }
}
