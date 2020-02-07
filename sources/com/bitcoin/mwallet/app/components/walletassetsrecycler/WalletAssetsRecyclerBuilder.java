package com.bitcoin.mwallet.app.components.walletassetsrecycler;

import android.app.Application;
import androidx.lifecycle.ViewModelKt;
import com.bitcoin.mwallet.app.viper.ComponentBuilderBase;
import com.bitcoin.mwallet.core.interactors.GetBalanceByCoinInteractor;
import com.bitcoin.mwallet.core.interactors.GetBalanceByWalletInteractor;
import com.bitcoin.mwallet.core.interactors.GetDefaultCurrencyInteractor;
import com.bitcoin.mwallet.core.interactors.GetWalletInteractor;
import com.bitcoin.mwallet.core.repositories.SlpRepository;
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

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u001b\u0010\u000b\u001a\u00020\f8BX\u0002¢\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\r\u0010\u000eR\u001b\u0010\u0010\u001a\u00020\u00118BX\u0002¢\u0006\f\n\u0004\b\u0014\u0010\n\u001a\u0004\b\u0012\u0010\u0013R\u001b\u0010\u0015\u001a\u00020\u00168BX\u0002¢\u0006\f\n\u0004\b\u0019\u0010\n\u001a\u0004\b\u0017\u0010\u0018R\u001b\u0010\u001a\u001a\u00020\u001b8BX\u0002¢\u0006\f\n\u0004\b\u001e\u0010\n\u001a\u0004\b\u001c\u0010\u001dR\u0014\u0010\u001f\u001a\u00020 X\u0004¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u001b\u0010#\u001a\u00020$8BX\u0002¢\u0006\f\n\u0004\b'\u0010\n\u001a\u0004\b%\u0010&¨\u0006("}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/components/walletassetsrecycler/WalletAssetsRecyclerBuilder;", "Lcom/bitcoin/mwallet/app/viper/ComponentBuilderBase;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "analyticsService", "Lcom/bitcoin/mwallet/core/services/AnalyticsService;", "getAnalyticsService", "()Lcom/bitcoin/mwallet/core/services/AnalyticsService;", "analyticsService$delegate", "Lkotlin/Lazy;", "balanceCoinInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetBalanceByCoinInteractor;", "getBalanceCoinInteractor", "()Lcom/bitcoin/mwallet/core/interactors/GetBalanceByCoinInteractor;", "balanceCoinInteractor$delegate", "balanceWalletInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetBalanceByWalletInteractor;", "getBalanceWalletInteractor", "()Lcom/bitcoin/mwallet/core/interactors/GetBalanceByWalletInteractor;", "balanceWalletInteractor$delegate", "currencyInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetDefaultCurrencyInteractor;", "getCurrencyInteractor", "()Lcom/bitcoin/mwallet/core/interactors/GetDefaultCurrencyInteractor;", "currencyInteractor$delegate", "getWalletInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetWalletInteractor;", "getGetWalletInteractor", "()Lcom/bitcoin/mwallet/core/interactors/GetWalletInteractor;", "getWalletInteractor$delegate", "presenter", "Lcom/bitcoin/mwallet/app/components/walletassetsrecycler/WalletAssetsRecyclerPresenter;", "getPresenter", "()Lcom/bitcoin/mwallet/app/components/walletassetsrecycler/WalletAssetsRecyclerPresenter;", "slpRepository", "Lcom/bitcoin/mwallet/core/repositories/SlpRepository;", "getSlpRepository", "()Lcom/bitcoin/mwallet/core/repositories/SlpRepository;", "slpRepository$delegate", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: WalletAssetsRecyclerBuilder.kt */
public final class WalletAssetsRecyclerBuilder extends ComponentBuilderBase {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(WalletAssetsRecyclerBuilder.class), "balanceWalletInteractor", "getBalanceWalletInteractor()Lcom/bitcoin/mwallet/core/interactors/GetBalanceByWalletInteractor;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(WalletAssetsRecyclerBuilder.class), "balanceCoinInteractor", "getBalanceCoinInteractor()Lcom/bitcoin/mwallet/core/interactors/GetBalanceByCoinInteractor;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(WalletAssetsRecyclerBuilder.class), "currencyInteractor", "getCurrencyInteractor()Lcom/bitcoin/mwallet/core/interactors/GetDefaultCurrencyInteractor;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(WalletAssetsRecyclerBuilder.class), "slpRepository", "getSlpRepository()Lcom/bitcoin/mwallet/core/repositories/SlpRepository;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(WalletAssetsRecyclerBuilder.class), "getWalletInteractor", "getGetWalletInteractor()Lcom/bitcoin/mwallet/core/interactors/GetWalletInteractor;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(WalletAssetsRecyclerBuilder.class), "analyticsService", "getAnalyticsService()Lcom/bitcoin/mwallet/core/services/AnalyticsService;"))};
    private final Lazy analyticsService$delegate;
    private final Lazy balanceCoinInteractor$delegate;
    private final Lazy balanceWalletInteractor$delegate;
    private final Lazy currencyInteractor$delegate;
    private final Lazy getWalletInteractor$delegate;
    @NotNull
    private final WalletAssetsRecyclerPresenter presenter;
    private final Lazy slpRepository$delegate;

    private final AnalyticsService getAnalyticsService() {
        Lazy lazy = this.analyticsService$delegate;
        KProperty kProperty = $$delegatedProperties[5];
        return (AnalyticsService) lazy.getValue();
    }

    private final GetBalanceByCoinInteractor getBalanceCoinInteractor() {
        Lazy lazy = this.balanceCoinInteractor$delegate;
        KProperty kProperty = $$delegatedProperties[1];
        return (GetBalanceByCoinInteractor) lazy.getValue();
    }

    private final GetBalanceByWalletInteractor getBalanceWalletInteractor() {
        Lazy lazy = this.balanceWalletInteractor$delegate;
        KProperty kProperty = $$delegatedProperties[0];
        return (GetBalanceByWalletInteractor) lazy.getValue();
    }

    private final GetDefaultCurrencyInteractor getCurrencyInteractor() {
        Lazy lazy = this.currencyInteractor$delegate;
        KProperty kProperty = $$delegatedProperties[2];
        return (GetDefaultCurrencyInteractor) lazy.getValue();
    }

    private final GetWalletInteractor getGetWalletInteractor() {
        Lazy lazy = this.getWalletInteractor$delegate;
        KProperty kProperty = $$delegatedProperties[4];
        return (GetWalletInteractor) lazy.getValue();
    }

    private final SlpRepository getSlpRepository() {
        Lazy lazy = this.slpRepository$delegate;
        KProperty kProperty = $$delegatedProperties[3];
        return (SlpRepository) lazy.getValue();
    }

    public WalletAssetsRecyclerBuilder(@NotNull Application application) {
        Intrinsics.checkParameterIsNotNull(application, "application");
        super(application);
        Qualifier qualifier = null;
        Function0 function0 = null;
        this.balanceWalletInteractor$delegate = LazyKt.lazy(new WalletAssetsRecyclerBuilder$$special$$inlined$inject$1(getKoin().getRootScope(), qualifier, function0));
        this.balanceCoinInteractor$delegate = LazyKt.lazy(new WalletAssetsRecyclerBuilder$$special$$inlined$inject$2(getKoin().getRootScope(), qualifier, function0));
        this.currencyInteractor$delegate = LazyKt.lazy(new WalletAssetsRecyclerBuilder$$special$$inlined$inject$3(getKoin().getRootScope(), qualifier, function0));
        this.slpRepository$delegate = LazyKt.lazy(new WalletAssetsRecyclerBuilder$$special$$inlined$inject$4(getKoin().getRootScope(), qualifier, function0));
        this.getWalletInteractor$delegate = LazyKt.lazy(new WalletAssetsRecyclerBuilder$$special$$inlined$inject$5(getKoin().getRootScope(), qualifier, function0));
        this.analyticsService$delegate = LazyKt.lazy(new WalletAssetsRecyclerBuilder$$special$$inlined$inject$6(getKoin().getRootScope(), qualifier, function0));
        WalletAssetsRecyclerPresenter walletAssetsRecyclerPresenter = new WalletAssetsRecyclerPresenter(application, ViewModelKt.getViewModelScope(this), getBalanceCoinInteractor(), getBalanceWalletInteractor(), getCurrencyInteractor(), getSlpRepository(), getGetWalletInteractor(), getAnalyticsService());
        this.presenter = walletAssetsRecyclerPresenter;
    }

    @NotNull
    public WalletAssetsRecyclerPresenter getPresenter() {
        return this.presenter;
    }
}
