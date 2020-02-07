package com.bitcoin.mwallet.app.flows.settings.settings.listeners;

import android.content.SharedPreferences;
import androidx.preference.Preference;
import com.bitcoin.mwallet.app.flows.settings.settings.SettingsRouter;
import com.bitcoin.mwallet.app.flows.settings.settings.listeners.PreferenceListener.DefaultImpls;
import com.bitcoin.mwallet.core.Constants;
import com.bitcoin.mwallet.core.repositories.ExchangeRateRepository;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0018\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u0018\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\u0010\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0010\u001a\u00020\u0011H\u0016R\u0018\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bX\u000e¢\u0006\u0004\n\u0002\u0010\nR\u0018\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bX\u000e¢\u0006\u0004\n\u0002\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0019"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/settings/settings/listeners/PreferenceListenerCurrency;", "Lcom/bitcoin/mwallet/app/flows/settings/settings/listeners/PreferenceListener;", "exchangeRateRepository", "Lcom/bitcoin/mwallet/core/repositories/ExchangeRateRepository;", "router", "Lcom/bitcoin/mwallet/app/flows/settings/settings/SettingsRouter;", "(Lcom/bitcoin/mwallet/core/repositories/ExchangeRateRepository;Lcom/bitcoin/mwallet/app/flows/settings/settings/SettingsRouter;)V", "currencies", "", "", "[Ljava/lang/String;", "currenciesDisplayName", "getRouter", "()Lcom/bitcoin/mwallet/app/flows/settings/settings/SettingsRouter;", "init", "", "preference", "Landroidx/preference/Preference;", "sharedPreferences", "Landroid/content/SharedPreferences;", "onPreferenceChange", "", "newValue", "", "onPreferenceClick", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: PreferenceListenerCurrency.kt */
public final class PreferenceListenerCurrency implements PreferenceListener {
    private String[] currencies;
    private String[] currenciesDisplayName;
    private final ExchangeRateRepository exchangeRateRepository;
    @NotNull
    private final SettingsRouter router;

    public PreferenceListenerCurrency(@NotNull ExchangeRateRepository exchangeRateRepository2, @NotNull SettingsRouter settingsRouter) {
        Intrinsics.checkParameterIsNotNull(exchangeRateRepository2, "exchangeRateRepository");
        Intrinsics.checkParameterIsNotNull(settingsRouter, "router");
        this.exchangeRateRepository = exchangeRateRepository2;
        this.router = settingsRouter;
    }

    public void accept(@NotNull Preference preference, @NotNull SharedPreferences sharedPreferences) {
        Intrinsics.checkParameterIsNotNull(preference, "preference");
        Intrinsics.checkParameterIsNotNull(sharedPreferences, "sharedPreferences");
        DefaultImpls.accept(this, preference, sharedPreferences);
    }

    @NotNull
    public final SettingsRouter getRouter() {
        return this.router;
    }

    public void init(@NotNull Preference preference, @NotNull SharedPreferences sharedPreferences) {
        Intrinsics.checkParameterIsNotNull(preference, "preference");
        Intrinsics.checkParameterIsNotNull(sharedPreferences, "sharedPreferences");
        String string = sharedPreferences.getString(preference.getKey(), null);
        if (string == null) {
            String currencyCode = Constants.INSTANCE.getDefaultCurrency().getCurrencyCode();
            Intrinsics.checkExpressionValueIsNotNull(currencyCode, "Constants.defaultCurrency.currencyCode");
            onPreferenceChange(preference, currencyCode);
            return;
        }
        preference.setSummary((CharSequence) string);
    }

    public boolean onPreferenceChange(@NotNull Preference preference, @NotNull Object obj) {
        Intrinsics.checkParameterIsNotNull(preference, "preference");
        Intrinsics.checkParameterIsNotNull(obj, "newValue");
        if (!(obj instanceof String)) {
            obj = null;
        }
        String str = (String) obj;
        if (str != null) {
            preference.setSummary((CharSequence) str);
        }
        return true;
    }

    public boolean onPreferenceClick(@NotNull Preference preference) {
        Intrinsics.checkParameterIsNotNull(preference, "preference");
        this.router.toCurrencySelect();
        return true;
    }
}
