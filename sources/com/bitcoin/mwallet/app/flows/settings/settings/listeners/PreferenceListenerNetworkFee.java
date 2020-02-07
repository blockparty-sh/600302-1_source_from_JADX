package com.bitcoin.mwallet.app.flows.settings.settings.listeners;

import android.app.Application;
import android.content.SharedPreferences;
import androidx.preference.Preference;
import com.bitcoin.mwallet.app.flows.settings.settings.SettingsRouter;
import com.bitcoin.mwallet.app.flows.settings.settings.listeners.PreferenceListener.DefaultImpls;
import com.bitcoin.mwallet.core.Constants;
import com.bitcoin.mwallet.core.models.networkfee.NetworkFeeLevel;
import com.bitcoin.mwallet.core.preferences.SharedPreference;
import com.bitcoin.mwallet.core.repositories.NetworkFeeRepository;
import java.util.EnumMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0010\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\fH\u0002J\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0018\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u0012\u0010\u0019\u001a\u00020\u00162\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\nX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/settings/settings/listeners/PreferenceListenerNetworkFee;", "Lcom/bitcoin/mwallet/app/flows/settings/settings/listeners/PreferenceListener;", "networkFeeRepository", "Lcom/bitcoin/mwallet/core/repositories/NetworkFeeRepository;", "router", "Lcom/bitcoin/mwallet/app/flows/settings/settings/SettingsRouter;", "application", "Landroid/app/Application;", "(Lcom/bitcoin/mwallet/core/repositories/NetworkFeeRepository;Lcom/bitcoin/mwallet/app/flows/settings/settings/SettingsRouter;Landroid/app/Application;)V", "feeLevelTitle", "", "Lcom/bitcoin/mwallet/core/models/networkfee/NetworkFeeLevel;", "", "getFeeLevelTitle", "selectedPreference", "init", "", "preference", "Landroidx/preference/Preference;", "sharedPreferences", "Landroid/content/SharedPreferences;", "onPreferenceChange", "", "newValue", "", "onPreferenceClick", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: PreferenceListenerNetworkFee.kt */
public final class PreferenceListenerNetworkFee implements PreferenceListener {
    private final Application application;
    private final Map<NetworkFeeLevel, String> feeLevelTitle = new EnumMap(NetworkFeeLevel.class);
    private final NetworkFeeRepository networkFeeRepository;
    private final SettingsRouter router;

    public PreferenceListenerNetworkFee(@NotNull NetworkFeeRepository networkFeeRepository2, @NotNull SettingsRouter settingsRouter, @NotNull Application application2) {
        Intrinsics.checkParameterIsNotNull(networkFeeRepository2, "networkFeeRepository");
        Intrinsics.checkParameterIsNotNull(settingsRouter, "router");
        Intrinsics.checkParameterIsNotNull(application2, "application");
        this.networkFeeRepository = networkFeeRepository2;
        this.router = settingsRouter;
        this.application = application2;
    }

    public void accept(@NotNull Preference preference, @NotNull SharedPreferences sharedPreferences) {
        Intrinsics.checkParameterIsNotNull(preference, "preference");
        Intrinsics.checkParameterIsNotNull(sharedPreferences, "sharedPreferences");
        DefaultImpls.accept(this, preference, sharedPreferences);
    }

    public void init(@NotNull Preference preference, @NotNull SharedPreferences sharedPreferences) {
        Intrinsics.checkParameterIsNotNull(preference, "preference");
        Intrinsics.checkParameterIsNotNull(sharedPreferences, "sharedPreferences");
        String string = sharedPreferences.getString(SharedPreference.NETWORK_FEE.getKey(), null);
        if (string == null) {
            string = Constants.INSTANCE.getDefaultNetworkFeeLevel().name();
        }
        preference.setSummary((CharSequence) getFeeLevelTitle(string));
    }

    public boolean onPreferenceChange(@NotNull Preference preference, @NotNull Object obj) {
        Intrinsics.checkParameterIsNotNull(preference, "preference");
        Intrinsics.checkParameterIsNotNull(obj, "newValue");
        if (!(obj instanceof String)) {
            obj = null;
        }
        String str = (String) obj;
        if (str != null) {
            preference.setSummary((CharSequence) getFeeLevelTitle(str));
        }
        return true;
    }

    public boolean onPreferenceClick(@Nullable Preference preference) {
        this.router.toNetworkFee();
        return true;
    }

    private final String getFeeLevelTitle(String str) {
        NetworkFeeLevel valueOf = NetworkFeeLevel.valueOf(str);
        Map<NetworkFeeLevel, String> map = this.feeLevelTitle;
        Object obj = map.get(valueOf);
        if (obj == null) {
            obj = this.application.getString(valueOf.getPresentableNameResId());
            Intrinsics.checkExpressionValueIsNotNull(obj, "application.getString(se…vel.presentableNameResId)");
            map.put(valueOf, obj);
        }
        return (String) obj;
    }
}
