package com.bitcoin.mwallet.app.flows.settings.settings;

import android.content.Context;
import com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.entity.OldWallet.OldWalletsJsonSource;
import com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.entity.OldWalletsFileSystemSource;
import com.bitcoin.mwallet.app.viper.ScreenPresenter;
import com.bitcoin.mwallet.core.preferences.ApplicationPreferences;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B%\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0002¢\u0006\u0002\u0010\nJ\u0006\u0010\u001b\u001a\u00020\u001cJ\u0006\u0010\u001d\u001a\u00020\u001cJ\u0006\u0010\u001e\u001a\u00020\u001fR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\u0014X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001a¨\u0006 "}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/settings/settings/SettingsPresenter;", "Lcom/bitcoin/mwallet/app/viper/ScreenPresenter;", "Lcom/bitcoin/mwallet/app/flows/settings/settings/SettingsRouter;", "context", "Landroid/content/Context;", "viewModelScope", "Lkotlinx/coroutines/CoroutineScope;", "applicationPreferences", "Lcom/bitcoin/mwallet/core/preferences/ApplicationPreferences;", "router", "(Landroid/content/Context;Lkotlinx/coroutines/CoroutineScope;Lcom/bitcoin/mwallet/core/preferences/ApplicationPreferences;Lcom/bitcoin/mwallet/app/flows/settings/settings/SettingsRouter;)V", "getApplicationPreferences", "()Lcom/bitcoin/mwallet/core/preferences/ApplicationPreferences;", "getContext", "()Landroid/content/Context;", "oldWallet", "Lcom/bitcoin/mwallet/app/flows/onboarding/migrateprofile/entity/OldWallet$OldWalletsJsonSource;", "getOldWallet", "()Lcom/bitcoin/mwallet/app/flows/onboarding/migrateprofile/entity/OldWallet$OldWalletsJsonSource;", "view", "Lcom/bitcoin/mwallet/app/flows/settings/settings/SettingsView;", "getView", "()Lcom/bitcoin/mwallet/app/flows/settings/settings/SettingsView;", "setView", "(Lcom/bitcoin/mwallet/app/flows/settings/settings/SettingsView;)V", "getViewModelScope", "()Lkotlinx/coroutines/CoroutineScope;", "disabledAppLock", "", "enableAppLock", "oldWalletExists", "", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: SettingsPresenter.kt */
public final class SettingsPresenter extends ScreenPresenter<SettingsRouter> {
    @NotNull
    private final ApplicationPreferences applicationPreferences;
    @NotNull
    private final Context context;
    @NotNull
    private final OldWalletsJsonSource oldWallet = new OldWalletsFileSystemSource(this.context);
    @NotNull
    public SettingsView view;
    @NotNull
    private final CoroutineScope viewModelScope;

    @NotNull
    public final Context getContext() {
        return this.context;
    }

    @NotNull
    public final CoroutineScope getViewModelScope() {
        return this.viewModelScope;
    }

    @NotNull
    public final ApplicationPreferences getApplicationPreferences() {
        return this.applicationPreferences;
    }

    public SettingsPresenter(@NotNull Context context2, @NotNull CoroutineScope coroutineScope, @NotNull ApplicationPreferences applicationPreferences2, @NotNull SettingsRouter settingsRouter) {
        Intrinsics.checkParameterIsNotNull(context2, "context");
        Intrinsics.checkParameterIsNotNull(coroutineScope, "viewModelScope");
        Intrinsics.checkParameterIsNotNull(applicationPreferences2, "applicationPreferences");
        Intrinsics.checkParameterIsNotNull(settingsRouter, "router");
        super(context2, settingsRouter);
        this.context = context2;
        this.viewModelScope = coroutineScope;
        this.applicationPreferences = applicationPreferences2;
    }

    @NotNull
    public final SettingsView getView() {
        SettingsView settingsView = this.view;
        if (settingsView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
        }
        return settingsView;
    }

    public final void setView(@NotNull SettingsView settingsView) {
        Intrinsics.checkParameterIsNotNull(settingsView, "<set-?>");
        this.view = settingsView;
    }

    @NotNull
    public final OldWalletsJsonSource getOldWallet() {
        return this.oldWallet;
    }

    public final boolean oldWalletExists() {
        return ((Boolean) BuildersKt__BuildersKt.runBlocking$default(null, new SettingsPresenter$oldWalletExists$1(this, null), 1, null)).booleanValue();
    }

    public final void disabledAppLock() {
        BuildersKt__Builders_commonKt.launch$default(this.viewModelScope, null, null, new SettingsPresenter$disabledAppLock$1(this, null), 3, null);
    }

    public final void enableAppLock() {
        BuildersKt__Builders_commonKt.launch$default(this.viewModelScope, null, null, new SettingsPresenter$enableAppLock$1(this, null), 3, null);
    }
}
