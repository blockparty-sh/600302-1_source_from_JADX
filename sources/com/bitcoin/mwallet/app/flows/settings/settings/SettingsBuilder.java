package com.bitcoin.mwallet.app.flows.settings.settings;

import android.app.Activity;
import android.app.Application;
import android.content.SharedPreferences;
import androidx.lifecycle.ViewModelKt;
import androidx.preference.Preference;
import com.bitcoin.mwallet.ApplicationClass;
import com.bitcoin.mwallet.app.flows.settings.settings.listeners.PreferenceListener;
import com.bitcoin.mwallet.app.flows.settings.settings.listeners.PreferenceListenerAppLockSwitch;
import com.bitcoin.mwallet.app.flows.settings.settings.listeners.PreferenceListenerContacts;
import com.bitcoin.mwallet.app.flows.settings.settings.listeners.PreferenceListenerCurrency;
import com.bitcoin.mwallet.app.flows.settings.settings.listeners.PreferenceListenerNetworkFee;
import com.bitcoin.mwallet.app.flows.settings.settings.listeners.PreferenceListenerNotificationSwitch;
import com.bitcoin.mwallet.app.flows.settings.settings.listeners.PreferenceListenerShareApp;
import com.bitcoin.mwallet.app.viper.ScreenBuilderBase;
import com.bitcoin.mwallet.core.preferences.ApplicationPreferences;
import com.bitcoin.mwallet.core.preferences.SharedPreference;
import com.bitcoin.mwallet.core.repositories.ExchangeRateRepository;
import com.bitcoin.mwallet.core.repositories.NetworkFeeRepository;
import com.bitcoin.mwallet.core.repositories.WalletRepository;
import com.bitcoin.mwallet.core.services.notification.NotificationService;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;
import org.koin.core.qualifier.Qualifier;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u000200R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u001b\u0010\u000b\u001a\u00020\f8BX\u0002¢\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\r\u0010\u000eR\u001b\u0010\u0010\u001a\u00020\u00118BX\u0002¢\u0006\f\n\u0004\b\u0014\u0010\n\u001a\u0004\b\u0012\u0010\u0013R\u001b\u0010\u0015\u001a\u00020\u00168BX\u0002¢\u0006\f\n\u0004\b\u0019\u0010\n\u001a\u0004\b\u0017\u0010\u0018R\u001a\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u001d0\u001bX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001e\u001a\u00020\u001fX\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0014\u0010\"\u001a\u00020#X\u0004¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u001b\u0010&\u001a\u00020'8BX\u0002¢\u0006\f\n\u0004\b*\u0010\n\u001a\u0004\b(\u0010)¨\u00061"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/settings/settings/SettingsBuilder;", "Lcom/bitcoin/mwallet/app/viper/ScreenBuilderBase;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "applicationPreferences", "Lcom/bitcoin/mwallet/core/preferences/ApplicationPreferences;", "getApplicationPreferences", "()Lcom/bitcoin/mwallet/core/preferences/ApplicationPreferences;", "applicationPreferences$delegate", "Lkotlin/Lazy;", "exchangeRateRepository", "Lcom/bitcoin/mwallet/core/repositories/ExchangeRateRepository;", "getExchangeRateRepository", "()Lcom/bitcoin/mwallet/core/repositories/ExchangeRateRepository;", "exchangeRateRepository$delegate", "networkFeeRepository", "Lcom/bitcoin/mwallet/core/repositories/NetworkFeeRepository;", "getNetworkFeeRepository", "()Lcom/bitcoin/mwallet/core/repositories/NetworkFeeRepository;", "networkFeeRepository$delegate", "notificationService", "Lcom/bitcoin/mwallet/core/services/notification/NotificationService;", "getNotificationService", "()Lcom/bitcoin/mwallet/core/services/notification/NotificationService;", "notificationService$delegate", "preferenceListeners", "", "", "Lcom/bitcoin/mwallet/app/flows/settings/settings/listeners/PreferenceListener;", "presenter", "Lcom/bitcoin/mwallet/app/flows/settings/settings/SettingsPresenter;", "getPresenter", "()Lcom/bitcoin/mwallet/app/flows/settings/settings/SettingsPresenter;", "router", "Lcom/bitcoin/mwallet/app/flows/settings/settings/SettingsRouter;", "getRouter", "()Lcom/bitcoin/mwallet/app/flows/settings/settings/SettingsRouter;", "walletRepository", "Lcom/bitcoin/mwallet/core/repositories/WalletRepository;", "getWalletRepository", "()Lcom/bitcoin/mwallet/core/repositories/WalletRepository;", "walletRepository$delegate", "register", "", "preference", "Landroidx/preference/Preference;", "sharedPreferences", "Landroid/content/SharedPreferences;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: SettingsBuilder.kt */
public final class SettingsBuilder extends ScreenBuilderBase {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(SettingsBuilder.class), "exchangeRateRepository", "getExchangeRateRepository()Lcom/bitcoin/mwallet/core/repositories/ExchangeRateRepository;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(SettingsBuilder.class), "networkFeeRepository", "getNetworkFeeRepository()Lcom/bitcoin/mwallet/core/repositories/NetworkFeeRepository;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(SettingsBuilder.class), "walletRepository", "getWalletRepository()Lcom/bitcoin/mwallet/core/repositories/WalletRepository;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(SettingsBuilder.class), "notificationService", "getNotificationService()Lcom/bitcoin/mwallet/core/services/notification/NotificationService;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(SettingsBuilder.class), "applicationPreferences", "getApplicationPreferences()Lcom/bitcoin/mwallet/core/preferences/ApplicationPreferences;"))};
    private final Lazy applicationPreferences$delegate;
    private final Lazy exchangeRateRepository$delegate;
    private final Lazy networkFeeRepository$delegate;
    private final Lazy notificationService$delegate;
    private final Map<String, PreferenceListener> preferenceListeners;
    @NotNull
    private final SettingsPresenter presenter;
    @NotNull
    private final SettingsRouter router = new SettingsRouter();
    private final Lazy walletRepository$delegate;

    private final ApplicationPreferences getApplicationPreferences() {
        Lazy lazy = this.applicationPreferences$delegate;
        KProperty kProperty = $$delegatedProperties[4];
        return (ApplicationPreferences) lazy.getValue();
    }

    private final ExchangeRateRepository getExchangeRateRepository() {
        Lazy lazy = this.exchangeRateRepository$delegate;
        KProperty kProperty = $$delegatedProperties[0];
        return (ExchangeRateRepository) lazy.getValue();
    }

    private final NetworkFeeRepository getNetworkFeeRepository() {
        Lazy lazy = this.networkFeeRepository$delegate;
        KProperty kProperty = $$delegatedProperties[1];
        return (NetworkFeeRepository) lazy.getValue();
    }

    private final NotificationService getNotificationService() {
        Lazy lazy = this.notificationService$delegate;
        KProperty kProperty = $$delegatedProperties[3];
        return (NotificationService) lazy.getValue();
    }

    private final WalletRepository getWalletRepository() {
        Lazy lazy = this.walletRepository$delegate;
        KProperty kProperty = $$delegatedProperties[2];
        return (WalletRepository) lazy.getValue();
    }

    public SettingsBuilder(@NotNull Application application) {
        Intrinsics.checkParameterIsNotNull(application, "application");
        super(application);
        Qualifier qualifier = null;
        Function0 function0 = null;
        this.exchangeRateRepository$delegate = LazyKt.lazy(new SettingsBuilder$$special$$inlined$inject$1(getKoin().getRootScope(), qualifier, function0));
        this.networkFeeRepository$delegate = LazyKt.lazy(new SettingsBuilder$$special$$inlined$inject$2(getKoin().getRootScope(), qualifier, function0));
        this.walletRepository$delegate = LazyKt.lazy(new SettingsBuilder$$special$$inlined$inject$3(getKoin().getRootScope(), qualifier, function0));
        this.notificationService$delegate = LazyKt.lazy(new SettingsBuilder$$special$$inlined$inject$4(getKoin().getRootScope(), qualifier, function0));
        this.applicationPreferences$delegate = LazyKt.lazy(new SettingsBuilder$$special$$inlined$inject$5(getKoin().getRootScope(), qualifier, function0));
        this.presenter = new SettingsPresenter(application, ViewModelKt.getViewModelScope(this), getApplicationPreferences(), getRouter());
        Pair[] pairArr = new Pair[6];
        pairArr[0] = new Pair(SharedPreference.FIAT_CURRENCY.getKey(), new PreferenceListenerCurrency(getExchangeRateRepository(), getRouter()));
        pairArr[1] = new Pair(SharedPreference.NETWORK_FEE.getKey(), new PreferenceListenerNetworkFee(getNetworkFeeRepository(), getRouter(), application));
        pairArr[2] = new Pair(SharedPreference.APP_LOCK.getKey(), new PreferenceListenerAppLockSwitch(application, getPresenter()));
        String key = SharedPreference.SHARE_APP.getKey();
        Activity activityContext = ((ApplicationClass) application).getActivityContext();
        if (activityContext != null) {
            pairArr[3] = new Pair(key, new PreferenceListenerShareApp(activityContext));
            pairArr[4] = new Pair(SharedPreference.NOTIFICATION.getKey(), new PreferenceListenerNotificationSwitch(getWalletRepository(), getNotificationService()));
            pairArr[5] = new Pair(SharedPreference.CONTACTS.getKey(), new PreferenceListenerContacts(getRouter()));
            this.preferenceListeners = MapsKt.toMutableMap(MapsKt.mapOf(pairArr));
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.content.Context");
    }

    /* access modifiers changed from: protected */
    @NotNull
    public SettingsRouter getRouter() {
        return this.router;
    }

    @NotNull
    public SettingsPresenter getPresenter() {
        return this.presenter;
    }

    public final void register(@NotNull Preference preference, @NotNull SharedPreferences sharedPreferences) {
        Intrinsics.checkParameterIsNotNull(preference, "preference");
        Intrinsics.checkParameterIsNotNull(sharedPreferences, "sharedPreferences");
        PreferenceListener preferenceListener = (PreferenceListener) this.preferenceListeners.get(preference.getKey());
        if (preferenceListener != null) {
            preferenceListener.accept(preference, sharedPreferences);
        }
    }
}
