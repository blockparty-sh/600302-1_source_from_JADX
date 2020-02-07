package com.bitcoin.mwallet.app.components.globalbalance.builder;

import android.app.Application;
import androidx.lifecycle.ViewModelKt;
import com.bitcoin.mwallet.app.components.globalbalance.interactor.GlobalBalanceInteractor;
import com.bitcoin.mwallet.app.components.globalbalance.presenter.GlobalBalancePresenter;
import com.bitcoin.mwallet.app.viper.ComponentBuilderBase;
import com.bitcoin.mwallet.core.interactors.GetBalanceByCoinInteractor;
import com.bitcoin.mwallet.core.interactors.GetBalanceByWalletInteractor;
import com.bitcoin.mwallet.core.interactors.GetDefaultCurrencyInteractor;
import com.bitcoin.mwallet.core.services.AnalyticsService;
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

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u001b\u0010\u000b\u001a\u00020\f8BX\u0002¢\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\r\u0010\u000eR\u001b\u0010\u0010\u001a\u00020\u00118BX\u0002¢\u0006\f\n\u0004\b\u0014\u0010\n\u001a\u0004\b\u0012\u0010\u0013R\u001b\u0010\u0015\u001a\u00020\u00168BX\u0002¢\u0006\f\n\u0004\b\u0019\u0010\n\u001a\u0004\b\u0017\u0010\u0018R\u000e\u0010\u001a\u001a\u00020\u001bX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001c\u001a\u00020\u001dX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001f¨\u0006 "}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/components/globalbalance/builder/GlobalBalanceBuilder;", "Lcom/bitcoin/mwallet/app/viper/ComponentBuilderBase;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "analyticsService", "Lcom/bitcoin/mwallet/core/services/AnalyticsService;", "getAnalyticsService", "()Lcom/bitcoin/mwallet/core/services/AnalyticsService;", "analyticsService$delegate", "Lkotlin/Lazy;", "balanceByWalletInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetBalanceByWalletInteractor;", "getBalanceByWalletInteractor", "()Lcom/bitcoin/mwallet/core/interactors/GetBalanceByWalletInteractor;", "balanceByWalletInteractor$delegate", "getBalanceByCoinInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetBalanceByCoinInteractor;", "getGetBalanceByCoinInteractor", "()Lcom/bitcoin/mwallet/core/interactors/GetBalanceByCoinInteractor;", "getBalanceByCoinInteractor$delegate", "getDefaultCurrencyInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetDefaultCurrencyInteractor;", "getGetDefaultCurrencyInteractor", "()Lcom/bitcoin/mwallet/core/interactors/GetDefaultCurrencyInteractor;", "getDefaultCurrencyInteractor$delegate", "interactor", "Lcom/bitcoin/mwallet/app/components/globalbalance/interactor/GlobalBalanceInteractor;", "presenter", "Lcom/bitcoin/mwallet/app/components/globalbalance/presenter/GlobalBalancePresenter;", "getPresenter", "()Lcom/bitcoin/mwallet/app/components/globalbalance/presenter/GlobalBalancePresenter;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: GlobalBalanceBuilder.kt */
public final class GlobalBalanceBuilder extends ComponentBuilderBase {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(GlobalBalanceBuilder.class), "getBalanceByCoinInteractor", "getGetBalanceByCoinInteractor()Lcom/bitcoin/mwallet/core/interactors/GetBalanceByCoinInteractor;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(GlobalBalanceBuilder.class), "getDefaultCurrencyInteractor", "getGetDefaultCurrencyInteractor()Lcom/bitcoin/mwallet/core/interactors/GetDefaultCurrencyInteractor;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(GlobalBalanceBuilder.class), "balanceByWalletInteractor", "getBalanceByWalletInteractor()Lcom/bitcoin/mwallet/core/interactors/GetBalanceByWalletInteractor;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(GlobalBalanceBuilder.class), "analyticsService", "getAnalyticsService()Lcom/bitcoin/mwallet/core/services/AnalyticsService;"))};
    private final Lazy analyticsService$delegate;
    private final Lazy balanceByWalletInteractor$delegate;
    private final Lazy getBalanceByCoinInteractor$delegate;
    private final Lazy getDefaultCurrencyInteractor$delegate;
    private final GlobalBalanceInteractor interactor = new GlobalBalanceInteractor(getGetBalanceByCoinInteractor(), getGetDefaultCurrencyInteractor(), getBalanceByWalletInteractor());
    @NotNull
    private final GlobalBalancePresenter presenter;

    private final AnalyticsService getAnalyticsService() {
        Lazy lazy = this.analyticsService$delegate;
        KProperty kProperty = $$delegatedProperties[3];
        return (AnalyticsService) lazy.getValue();
    }

    private final GetBalanceByWalletInteractor getBalanceByWalletInteractor() {
        Lazy lazy = this.balanceByWalletInteractor$delegate;
        KProperty kProperty = $$delegatedProperties[2];
        return (GetBalanceByWalletInteractor) lazy.getValue();
    }

    private final GetBalanceByCoinInteractor getGetBalanceByCoinInteractor() {
        Lazy lazy = this.getBalanceByCoinInteractor$delegate;
        KProperty kProperty = $$delegatedProperties[0];
        return (GetBalanceByCoinInteractor) lazy.getValue();
    }

    private final GetDefaultCurrencyInteractor getGetDefaultCurrencyInteractor() {
        Lazy lazy = this.getDefaultCurrencyInteractor$delegate;
        KProperty kProperty = $$delegatedProperties[1];
        return (GetDefaultCurrencyInteractor) lazy.getValue();
    }

    public GlobalBalanceBuilder(@NotNull Application application) {
        Intrinsics.checkParameterIsNotNull(application, "application");
        super(application);
        Qualifier qualifier = null;
        Function0 function0 = null;
        this.getBalanceByCoinInteractor$delegate = LazyKt.lazy(new GlobalBalanceBuilder$$special$$inlined$inject$1(getKoin().getRootScope(), qualifier, function0));
        this.getDefaultCurrencyInteractor$delegate = LazyKt.lazy(new GlobalBalanceBuilder$$special$$inlined$inject$2(getKoin().getRootScope(), qualifier, function0));
        this.balanceByWalletInteractor$delegate = LazyKt.lazy(new GlobalBalanceBuilder$$special$$inlined$inject$3(getKoin().getRootScope(), qualifier, function0));
        this.analyticsService$delegate = LazyKt.lazy(new GlobalBalanceBuilder$$special$$inlined$inject$4(getKoin().getRootScope(), qualifier, function0));
        GlobalBalancePresenter globalBalancePresenter = new GlobalBalancePresenter(application, ViewModelKt.getViewModelScope(this), this.interactor, getGetDefaultCurrencyInteractor(), getAnalyticsService());
        this.presenter = globalBalancePresenter;
    }

    @NotNull
    public GlobalBalancePresenter getPresenter() {
        return this.presenter;
    }
}
