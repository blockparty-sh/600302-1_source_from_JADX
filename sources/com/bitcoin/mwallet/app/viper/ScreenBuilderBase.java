package com.bitcoin.mwallet.app.viper;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.navigation.NavController;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.koin.core.Koin;
import org.koin.core.KoinComponent;
import org.koin.core.KoinComponent.DefaultImpls;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016R\u0012\u0010\u0006\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0012\u0010\n\u001a\u00020\u000bX¤\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\r¨\u0006\u0012"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/viper/ScreenBuilderBase;", "Landroidx/lifecycle/AndroidViewModel;", "Lorg/koin/core/KoinComponent;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "presenter", "Lcom/bitcoin/mwallet/app/viper/PresenterBase;", "getPresenter", "()Lcom/bitcoin/mwallet/app/viper/PresenterBase;", "router", "Lcom/bitcoin/mwallet/app/viper/RouterBase;", "getRouter", "()Lcom/bitcoin/mwallet/app/viper/RouterBase;", "setNavController", "", "navController", "Landroidx/navigation/NavController;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: ScreenBuilderBase.kt */
public abstract class ScreenBuilderBase extends AndroidViewModel implements KoinComponent {
    @NotNull
    public abstract PresenterBase getPresenter();

    /* access modifiers changed from: protected */
    @NotNull
    public abstract RouterBase getRouter();

    public ScreenBuilderBase(@NotNull Application application) {
        Intrinsics.checkParameterIsNotNull(application, "application");
        super(application);
    }

    @NotNull
    public Koin getKoin() {
        return DefaultImpls.getKoin(this);
    }

    public void setNavController(@NotNull NavController navController) {
        Intrinsics.checkParameterIsNotNull(navController, "navController");
        getRouter().setNavController(navController);
    }
}
