package com.bitcoin.mwallet.app.viper;

import android.content.Context;
import com.bitcoin.mwallet.app.viper.RouterBase;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0016\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003B\u0015\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00028\u0000¢\u0006\u0002\u0010\u0007R\u0016\u0010\u0006\u001a\u00028\u0000X\u0004¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\b\u0010\t¨\u0006\u000b"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/viper/ScreenPresenter;", "ROUTER", "Lcom/bitcoin/mwallet/app/viper/RouterBase;", "Lcom/bitcoin/mwallet/app/viper/PresenterBase;", "context", "Landroid/content/Context;", "router", "(Landroid/content/Context;Lcom/bitcoin/mwallet/app/viper/RouterBase;)V", "getRouter", "()Lcom/bitcoin/mwallet/app/viper/RouterBase;", "Lcom/bitcoin/mwallet/app/viper/RouterBase;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: ScreenPresenter.kt */
public class ScreenPresenter<ROUTER extends RouterBase> extends PresenterBase {
    @NotNull
    private final ROUTER router;

    /* access modifiers changed from: protected */
    @NotNull
    public final ROUTER getRouter() {
        return this.router;
    }

    public ScreenPresenter(@NotNull Context context, @NotNull ROUTER router2) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(router2, "router");
        this.router = router2;
    }
}
