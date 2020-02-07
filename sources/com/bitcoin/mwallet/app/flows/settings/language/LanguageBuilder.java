package com.bitcoin.mwallet.app.flows.settings.language;

import android.app.Application;
import androidx.lifecycle.ViewModelKt;
import com.bitcoin.mwallet.app.viper.ScreenBuilderBase;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\fX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u000f"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/settings/language/LanguageBuilder;", "Lcom/bitcoin/mwallet/app/viper/ScreenBuilderBase;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "interactor", "Lcom/bitcoin/mwallet/app/flows/settings/language/LanguageInteractor;", "presenter", "Lcom/bitcoin/mwallet/app/flows/settings/language/LanguagePresenter;", "getPresenter", "()Lcom/bitcoin/mwallet/app/flows/settings/language/LanguagePresenter;", "router", "Lcom/bitcoin/mwallet/app/flows/settings/language/LanguageRouter;", "getRouter", "()Lcom/bitcoin/mwallet/app/flows/settings/language/LanguageRouter;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: LanguageBuilder.kt */
public final class LanguageBuilder extends ScreenBuilderBase {
    private final LanguageInteractor interactor = new LanguageInteractor();
    @NotNull
    private final LanguagePresenter presenter;
    @NotNull
    private final LanguageRouter router = new LanguageRouter();

    public LanguageBuilder(@NotNull Application application) {
        Intrinsics.checkParameterIsNotNull(application, "application");
        super(application);
        this.presenter = new LanguagePresenter(application, ViewModelKt.getViewModelScope(this), this.interactor, getRouter());
    }

    /* access modifiers changed from: protected */
    @NotNull
    public LanguageRouter getRouter() {
        return this.router;
    }

    @NotNull
    public LanguagePresenter getPresenter() {
        return this.presenter;
    }
}
