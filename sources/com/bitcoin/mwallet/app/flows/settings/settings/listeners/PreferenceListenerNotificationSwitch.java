package com.bitcoin.mwallet.app.flows.settings.settings.listeners;

import android.content.SharedPreferences;
import androidx.preference.Preference;
import androidx.preference.PreferenceManager;
import androidx.preference.SwitchPreference;
import com.bitcoin.mwallet.app.flows.settings.settings.listeners.PreferenceListener.DefaultImpls;
import com.bitcoin.mwallet.core.preferences.SharedPreference;
import com.bitcoin.mwallet.core.repositories.WalletRepository;
import com.bitcoin.mwallet.core.services.notification.NotificationService;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.GlobalScope;
import org.jetbrains.annotations.NotNull;
import timber.log.Timber;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0010\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\nH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/settings/settings/listeners/PreferenceListenerNotificationSwitch;", "Lcom/bitcoin/mwallet/app/flows/settings/settings/listeners/PreferenceListener;", "walletRepository", "Lcom/bitcoin/mwallet/core/repositories/WalletRepository;", "notificationServices", "Lcom/bitcoin/mwallet/core/services/notification/NotificationService;", "(Lcom/bitcoin/mwallet/core/repositories/WalletRepository;Lcom/bitcoin/mwallet/core/services/notification/NotificationService;)V", "init", "", "preference", "Landroidx/preference/Preference;", "sharedPreferences", "Landroid/content/SharedPreferences;", "onPreferenceChange", "", "newValue", "", "onPreferenceClick", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: PreferenceListenerNotificationSwitch.kt */
public final class PreferenceListenerNotificationSwitch implements PreferenceListener {
    /* access modifiers changed from: private */
    public final NotificationService notificationServices;
    /* access modifiers changed from: private */
    public final WalletRepository walletRepository;

    public boolean onPreferenceClick(@NotNull Preference preference) {
        Intrinsics.checkParameterIsNotNull(preference, "preference");
        return true;
    }

    public PreferenceListenerNotificationSwitch(@NotNull WalletRepository walletRepository2, @NotNull NotificationService notificationService) {
        Intrinsics.checkParameterIsNotNull(walletRepository2, "walletRepository");
        Intrinsics.checkParameterIsNotNull(notificationService, "notificationServices");
        this.walletRepository = walletRepository2;
        this.notificationServices = notificationService;
    }

    public void accept(@NotNull Preference preference, @NotNull SharedPreferences sharedPreferences) {
        Intrinsics.checkParameterIsNotNull(preference, "preference");
        Intrinsics.checkParameterIsNotNull(sharedPreferences, "sharedPreferences");
        DefaultImpls.accept(this, preference, sharedPreferences);
    }

    public void init(@NotNull Preference preference, @NotNull SharedPreferences sharedPreferences) {
        Intrinsics.checkParameterIsNotNull(preference, "preference");
        Intrinsics.checkParameterIsNotNull(sharedPreferences, "sharedPreferences");
        PreferenceManager preferenceManager = preference.getPreferenceManager();
        Intrinsics.checkExpressionValueIsNotNull(preferenceManager, "preference.preferenceManager");
        boolean z = preferenceManager.getSharedPreferences().getBoolean(SharedPreference.NOTIFICATION.getKey(), true);
        ((SwitchPreference) preference).setChecked(z);
        StringBuilder sb = new StringBuilder();
        sb.append("Notification Switch Preference Initialized: ");
        sb.append(z);
        Timber.m426d(sb.toString(), new Object[0]);
    }

    public boolean onPreferenceChange(@NotNull Preference preference, @NotNull Object obj) {
        Intrinsics.checkParameterIsNotNull(preference, "preference");
        Intrinsics.checkParameterIsNotNull(obj, "newValue");
        Timber.m426d("On PreferenceChange", new Object[0]);
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new PreferenceListenerNotificationSwitch$onPreferenceChange$1(this, obj, null), 3, null);
        return true;
    }
}
