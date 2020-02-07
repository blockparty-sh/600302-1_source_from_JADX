package com.bitcoin.mwallet.app.flows.settings.settings.listeners;

import android.app.Activity;
import android.app.Application;
import android.content.SharedPreferences;
import androidx.preference.Preference;
import com.bitcoin.mwallet.ApplicationClass;
import com.bitcoin.mwallet.app.flows.settings.settings.SettingsPresenter;
import com.bitcoin.mwallet.app.flows.settings.settings.listeners.PreferenceListener.DefaultImpls;
import com.guardanis.applock.AppLock;
import com.guardanis.applock.dialogs.LockCreationDialogBuilder;
import com.guardanis.applock.dialogs.UnlockDialogBuilder;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0010\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\nH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/settings/settings/listeners/PreferenceListenerAppLockSwitch;", "Lcom/bitcoin/mwallet/app/flows/settings/settings/listeners/PreferenceListener;", "application", "Landroid/app/Application;", "presenter", "Lcom/bitcoin/mwallet/app/flows/settings/settings/SettingsPresenter;", "(Landroid/app/Application;Lcom/bitcoin/mwallet/app/flows/settings/settings/SettingsPresenter;)V", "init", "", "preference", "Landroidx/preference/Preference;", "sharedPreferences", "Landroid/content/SharedPreferences;", "onPreferenceChange", "", "newValue", "", "onPreferenceClick", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: PreferenceListenerAppLockSwitch.kt */
public final class PreferenceListenerAppLockSwitch implements PreferenceListener {
    private final Application application;
    /* access modifiers changed from: private */
    public final SettingsPresenter presenter;

    public void init(@NotNull Preference preference, @NotNull SharedPreferences sharedPreferences) {
        Intrinsics.checkParameterIsNotNull(preference, "preference");
        Intrinsics.checkParameterIsNotNull(sharedPreferences, "sharedPreferences");
    }

    public boolean onPreferenceClick(@NotNull Preference preference) {
        Intrinsics.checkParameterIsNotNull(preference, "preference");
        return true;
    }

    public PreferenceListenerAppLockSwitch(@NotNull Application application2, @NotNull SettingsPresenter settingsPresenter) {
        Intrinsics.checkParameterIsNotNull(application2, "application");
        Intrinsics.checkParameterIsNotNull(settingsPresenter, "presenter");
        this.application = application2;
        this.presenter = settingsPresenter;
    }

    public void accept(@NotNull Preference preference, @NotNull SharedPreferences sharedPreferences) {
        Intrinsics.checkParameterIsNotNull(preference, "preference");
        Intrinsics.checkParameterIsNotNull(sharedPreferences, "sharedPreferences");
        DefaultImpls.accept(this, preference, sharedPreferences);
    }

    public boolean onPreferenceChange(@NotNull Preference preference, @NotNull Object obj) {
        Intrinsics.checkParameterIsNotNull(preference, "preference");
        Intrinsics.checkParameterIsNotNull(obj, "newValue");
        Application application2 = this.application;
        if (application2 != null) {
            Activity activityContext = ((ApplicationClass) application2).getActivityContext();
            if (Intrinsics.areEqual(obj, (Object) Boolean.valueOf(true))) {
                new LockCreationDialogBuilder(activityContext).onCanceled(new PreferenceListenerAppLockSwitch$onPreferenceChange$1(this)).onLockCreated(PreferenceListenerAppLockSwitch$onPreferenceChange$2.INSTANCE).show();
            } else if (AppLock.isEnrolled(activityContext)) {
                new UnlockDialogBuilder(activityContext).onCanceled(new PreferenceListenerAppLockSwitch$onPreferenceChange$3(this)).onUnlocked(new PreferenceListenerAppLockSwitch$onPreferenceChange$4(activityContext)).show();
            }
            return true;
        }
        throw new TypeCastException("null cannot be cast to non-null type com.bitcoin.mwallet.ApplicationClass");
    }
}
