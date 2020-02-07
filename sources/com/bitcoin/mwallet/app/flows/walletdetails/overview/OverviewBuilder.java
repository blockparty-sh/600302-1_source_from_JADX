package com.bitcoin.mwallet.app.flows.walletdetails.overview;

import android.app.Application;
import androidx.lifecycle.ViewModelKt;
import com.bitcoin.mwallet.app.viper.ScreenBuilderBase;
import com.bitcoin.mwallet.core.interactors.GetCurrentExchangeRateInteractor;
import com.bitcoin.mwallet.core.interactors.GetDefaultCurrencyInteractor;
import com.bitcoin.mwallet.core.interactors.GetWalletInteractor;
import com.bitcoin.mwallet.core.repositories.SlpRepository;
import com.bitcoin.mwallet.core.repositories.WalletRepository;
import com.bitcoin.mwallet.core.services.AnalyticsService;
import com.bitcoin.mwallet.zion.ZionRepository;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;
import org.koin.core.qualifier.Qualifier;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001b\u0010\u0005\u001a\u00020\u00068FX\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u001b\u0010\u000b\u001a\u00020\f8BX\u0002¢\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\r\u0010\u000eR\u001b\u0010\u0010\u001a\u00020\u00118BX\u0002¢\u0006\f\n\u0004\b\u0014\u0010\n\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0015\u001a\u00020\u0016X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0019\u001a\u00020\u001aX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u001b\u0010\u001d\u001a\u00020\u001e8BX\u0002¢\u0006\f\n\u0004\b!\u0010\n\u001a\u0004\b\u001f\u0010 R\u001b\u0010\"\u001a\u00020#8BX\u0002¢\u0006\f\n\u0004\b&\u0010\n\u001a\u0004\b$\u0010%R\u001b\u0010'\u001a\u00020(8BX\u0002¢\u0006\f\n\u0004\b+\u0010\n\u001a\u0004\b)\u0010*R\u001b\u0010,\u001a\u00020-8BX\u0002¢\u0006\f\n\u0004\b0\u0010\n\u001a\u0004\b.\u0010/¨\u00061"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/walletdetails/overview/OverviewBuilder;", "Lcom/bitcoin/mwallet/app/viper/ScreenBuilderBase;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "analyticsService", "Lcom/bitcoin/mwallet/core/services/AnalyticsService;", "getAnalyticsService", "()Lcom/bitcoin/mwallet/core/services/AnalyticsService;", "analyticsService$delegate", "Lkotlin/Lazy;", "defaultCurrencyInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetDefaultCurrencyInteractor;", "getDefaultCurrencyInteractor", "()Lcom/bitcoin/mwallet/core/interactors/GetDefaultCurrencyInteractor;", "defaultCurrencyInteractor$delegate", "exchangeRateInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetCurrentExchangeRateInteractor;", "getExchangeRateInteractor", "()Lcom/bitcoin/mwallet/core/interactors/GetCurrentExchangeRateInteractor;", "exchangeRateInteractor$delegate", "presenter", "Lcom/bitcoin/mwallet/app/flows/walletdetails/overview/OverviewPresenter;", "getPresenter", "()Lcom/bitcoin/mwallet/app/flows/walletdetails/overview/OverviewPresenter;", "router", "Lcom/bitcoin/mwallet/app/flows/walletdetails/overview/OverviewRouter;", "getRouter", "()Lcom/bitcoin/mwallet/app/flows/walletdetails/overview/OverviewRouter;", "slpRepository", "Lcom/bitcoin/mwallet/core/repositories/SlpRepository;", "getSlpRepository", "()Lcom/bitcoin/mwallet/core/repositories/SlpRepository;", "slpRepository$delegate", "walletInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetWalletInteractor;", "getWalletInteractor", "()Lcom/bitcoin/mwallet/core/interactors/GetWalletInteractor;", "walletInteractor$delegate", "walletRepository", "Lcom/bitcoin/mwallet/core/repositories/WalletRepository;", "getWalletRepository", "()Lcom/bitcoin/mwallet/core/repositories/WalletRepository;", "walletRepository$delegate", "zionRepository", "Lcom/bitcoin/mwallet/zion/ZionRepository;", "getZionRepository", "()Lcom/bitcoin/mwallet/zion/ZionRepository;", "zionRepository$delegate", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: OverviewBuilder.kt */
public final class OverviewBuilder extends ScreenBuilderBase {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(OverviewBuilder.class), "zionRepository", "getZionRepository()Lcom/bitcoin/mwallet/zion/ZionRepository;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(OverviewBuilder.class), "defaultCurrencyInteractor", "getDefaultCurrencyInteractor()Lcom/bitcoin/mwallet/core/interactors/GetDefaultCurrencyInteractor;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(OverviewBuilder.class), "walletInteractor", "getWalletInteractor()Lcom/bitcoin/mwallet/core/interactors/GetWalletInteractor;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(OverviewBuilder.class), "walletRepository", "getWalletRepository()Lcom/bitcoin/mwallet/core/repositories/WalletRepository;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(OverviewBuilder.class), "exchangeRateInteractor", "getExchangeRateInteractor()Lcom/bitcoin/mwallet/core/interactors/GetCurrentExchangeRateInteractor;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(OverviewBuilder.class), "slpRepository", "getSlpRepository()Lcom/bitcoin/mwallet/core/repositories/SlpRepository;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(OverviewBuilder.class), "analyticsService", "getAnalyticsService()Lcom/bitcoin/mwallet/core/services/AnalyticsService;"))};
    @NotNull
    private final Lazy analyticsService$delegate;
    private final Lazy defaultCurrencyInteractor$delegate;
    private final Lazy exchangeRateInteractor$delegate;
    @NotNull
    private final OverviewPresenter presenter;
    @NotNull
    private final OverviewRouter router = new OverviewRouter();
    private final Lazy slpRepository$delegate;
    private final Lazy walletInteractor$delegate;
    private final Lazy walletRepository$delegate;
    private final Lazy zionRepository$delegate;

    private final GetDefaultCurrencyInteractor getDefaultCurrencyInteractor() {
        Lazy lazy = this.defaultCurrencyInteractor$delegate;
        KProperty kProperty = $$delegatedProperties[1];
        return (GetDefaultCurrencyInteractor) lazy.getValue();
    }

    private final GetCurrentExchangeRateInteractor getExchangeRateInteractor() {
        Lazy lazy = this.exchangeRateInteractor$delegate;
        KProperty kProperty = $$delegatedProperties[4];
        return (GetCurrentExchangeRateInteractor) lazy.getValue();
    }

    private final SlpRepository getSlpRepository() {
        Lazy lazy = this.slpRepository$delegate;
        KProperty kProperty = $$delegatedProperties[5];
        return (SlpRepository) lazy.getValue();
    }

    private final GetWalletInteractor getWalletInteractor() {
        Lazy lazy = this.walletInteractor$delegate;
        KProperty kProperty = $$delegatedProperties[2];
        return (GetWalletInteractor) lazy.getValue();
    }

    private final WalletRepository getWalletRepository() {
        Lazy lazy = this.walletRepository$delegate;
        KProperty kProperty = $$delegatedProperties[3];
        return (WalletRepository) lazy.getValue();
    }

    private final ZionRepository getZionRepository() {
        Lazy lazy = this.zionRepository$delegate;
        KProperty kProperty = $$delegatedProperties[0];
        return (ZionRepository) lazy.getValue();
    }

    @NotNull
    public final AnalyticsService getAnalyticsService() {
        Lazy lazy = this.analyticsService$delegate;
        KProperty kProperty = $$delegatedProperties[6];
        return (AnalyticsService) lazy.getValue();
    }

    public OverviewBuilder(@NotNull Application application) {
        Intrinsics.checkParameterIsNotNull(application, "application");
        super(application);
        Qualifier qualifier = null;
        Function0 function0 = null;
        this.zionRepository$delegate = LazyKt.lazy(new OverviewBuilder$$special$$inlined$inject$1(getKoin().getRootScope(), qualifier, function0));
        this.defaultCurrencyInteractor$delegate = LazyKt.lazy(new OverviewBuilder$$special$$inlined$inject$2(getKoin().getRootScope(), qualifier, function0));
        this.walletInteractor$delegate = LazyKt.lazy(new OverviewBuilder$$special$$inlined$inject$3(getKoin().getRootScope(), qualifier, function0));
        this.walletRepository$delegate = LazyKt.lazy(new OverviewBuilder$$special$$inlined$inject$4(getKoin().getRootScope(), qualifier, function0));
        this.exchangeRateInteractor$delegate = LazyKt.lazy(new OverviewBuilder$$special$$inlined$inject$5(getKoin().getRootScope(), qualifier, function0));
        this.slpRepository$delegate = LazyKt.lazy(new OverviewBuilder$$special$$inlined$inject$6(getKoin().getRootScope(), qualifier, function0));
        this.analyticsService$delegate = LazyKt.lazy(new OverviewBuilder$$special$$inlined$inject$7(getKoin().getRootScope(), qualifier, function0));
        OverviewPresenter overviewPresenter = new OverviewPresenter(application, ViewModelKt.getViewModelScope(this), getZionRepository(), getDefaultCurrencyInteractor(), getWalletInteractor(), getWalletRepository(), getExchangeRateInteractor(), getSlpRepository(), getAnalyticsService(), getRouter());
        this.presenter = overviewPresenter;
    }

    /* access modifiers changed from: protected */
    @NotNull
    public OverviewRouter getRouter() {
        return this.router;
    }

    @NotNull
    public OverviewPresenter getPresenter() {
        return this.presenter;
    }
}
