package com.bitcoin.mwallet.app.flows.sendv2.dialogs.selectcontact;

import android.app.Application;
import androidx.lifecycle.ViewModelKt;
import com.bitcoin.mwallet.app.viper.ScreenBuilderBase;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0014\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\nX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\r"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/selectcontact/SelectContactBuilder;", "Lcom/bitcoin/mwallet/app/viper/ScreenBuilderBase;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "presenter", "Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/selectcontact/SelectContactPresenter;", "getPresenter", "()Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/selectcontact/SelectContactPresenter;", "router", "Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/selectcontact/SelectContactRouter;", "getRouter", "()Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/selectcontact/SelectContactRouter;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: SelectContactBuilder.kt */
public final class SelectContactBuilder extends ScreenBuilderBase {
    @NotNull
    private final SelectContactPresenter presenter;
    @NotNull
    private final SelectContactRouter router = new SelectContactRouter();

    public SelectContactBuilder(@NotNull Application application) {
        Intrinsics.checkParameterIsNotNull(application, "application");
        super(application);
        this.presenter = new SelectContactPresenter(application, ViewModelKt.getViewModelScope(this), getRouter());
    }

    /* access modifiers changed from: protected */
    @NotNull
    public SelectContactRouter getRouter() {
        return this.router;
    }

    @NotNull
    public SelectContactPresenter getPresenter() {
        return this.presenter;
    }
}
