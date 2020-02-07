package com.bitcoin.mwallet.app.flows.sendv2.selectaddress;

import android.app.Application;
import androidx.lifecycle.ViewModelKt;
import com.bitcoin.mwallet.app.viper.ScreenBuilderBase;
import com.bitcoin.mwallet.core.interactors.CreateTxInteractor;
import com.bitcoin.mwallet.core.interactors.FeeValidatorInteractor;
import com.bitcoin.mwallet.core.interactors.GetAddressInteractor;
import com.bitcoin.mwallet.core.interactors.GetCurrentExchangeRateInteractor;
import com.bitcoin.mwallet.core.interactors.GetDefaultCurrencyInteractor;
import com.bitcoin.mwallet.core.interactors.GetNetworkFeeInteractor;
import com.bitcoin.mwallet.core.interactors.GetWalletInteractor;
import com.bitcoin.mwallet.core.repositories.UtxoRepository;
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

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u001b\u0010\u000b\u001a\u00020\f8BX\u0002¢\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\r\u0010\u000eR\u001b\u0010\u0010\u001a\u00020\u00118BX\u0002¢\u0006\f\n\u0004\b\u0014\u0010\n\u001a\u0004\b\u0012\u0010\u0013R\u001b\u0010\u0015\u001a\u00020\u00168BX\u0002¢\u0006\f\n\u0004\b\u0019\u0010\n\u001a\u0004\b\u0017\u0010\u0018R\u001b\u0010\u001a\u001a\u00020\u001b8BX\u0002¢\u0006\f\n\u0004\b\u001e\u0010\n\u001a\u0004\b\u001c\u0010\u001dR\u001b\u0010\u001f\u001a\u00020 8FX\u0002¢\u0006\f\n\u0004\b#\u0010\n\u001a\u0004\b!\u0010\"R\u001b\u0010$\u001a\u00020%8BX\u0002¢\u0006\f\n\u0004\b(\u0010\n\u001a\u0004\b&\u0010'R\u001b\u0010)\u001a\u00020*8FX\u0002¢\u0006\f\n\u0004\b-\u0010\n\u001a\u0004\b+\u0010,R\u0014\u0010.\u001a\u00020/X\u0004¢\u0006\b\n\u0000\u001a\u0004\b0\u00101R\u0014\u00102\u001a\u000203X\u0004¢\u0006\b\n\u0000\u001a\u0004\b4\u00105R\u001b\u00106\u001a\u0002078BX\u0002¢\u0006\f\n\u0004\b:\u0010\n\u001a\u0004\b8\u00109R\u001b\u0010;\u001a\u00020<8FX\u0002¢\u0006\f\n\u0004\b?\u0010\n\u001a\u0004\b=\u0010>¨\u0006@"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/sendv2/selectaddress/SelectAddressBuilder;", "Lcom/bitcoin/mwallet/app/viper/ScreenBuilderBase;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "analyticsService", "Lcom/bitcoin/mwallet/core/services/AnalyticsService;", "getAnalyticsService", "()Lcom/bitcoin/mwallet/core/services/AnalyticsService;", "analyticsService$delegate", "Lkotlin/Lazy;", "createTxInteractor", "Lcom/bitcoin/mwallet/core/interactors/CreateTxInteractor;", "getCreateTxInteractor", "()Lcom/bitcoin/mwallet/core/interactors/CreateTxInteractor;", "createTxInteractor$delegate", "currentExchangeRateInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetCurrentExchangeRateInteractor;", "getCurrentExchangeRateInteractor", "()Lcom/bitcoin/mwallet/core/interactors/GetCurrentExchangeRateInteractor;", "currentExchangeRateInteractor$delegate", "defaultCurrencyInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetDefaultCurrencyInteractor;", "getDefaultCurrencyInteractor", "()Lcom/bitcoin/mwallet/core/interactors/GetDefaultCurrencyInteractor;", "defaultCurrencyInteractor$delegate", "feeValidatorInteractor", "Lcom/bitcoin/mwallet/core/interactors/FeeValidatorInteractor;", "getFeeValidatorInteractor", "()Lcom/bitcoin/mwallet/core/interactors/FeeValidatorInteractor;", "feeValidatorInteractor$delegate", "getAddressInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetAddressInteractor;", "getGetAddressInteractor", "()Lcom/bitcoin/mwallet/core/interactors/GetAddressInteractor;", "getAddressInteractor$delegate", "getNetworkFeeInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetNetworkFeeInteractor;", "getGetNetworkFeeInteractor", "()Lcom/bitcoin/mwallet/core/interactors/GetNetworkFeeInteractor;", "getNetworkFeeInteractor$delegate", "getWalletInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetWalletInteractor;", "getGetWalletInteractor", "()Lcom/bitcoin/mwallet/core/interactors/GetWalletInteractor;", "getWalletInteractor$delegate", "presenter", "Lcom/bitcoin/mwallet/app/flows/sendv2/selectaddress/SelectAddressPresenter;", "getPresenter", "()Lcom/bitcoin/mwallet/app/flows/sendv2/selectaddress/SelectAddressPresenter;", "router", "Lcom/bitcoin/mwallet/app/flows/sendv2/selectaddress/SelectAddressRouter;", "getRouter", "()Lcom/bitcoin/mwallet/app/flows/sendv2/selectaddress/SelectAddressRouter;", "utxoRepository", "Lcom/bitcoin/mwallet/core/repositories/UtxoRepository;", "getUtxoRepository", "()Lcom/bitcoin/mwallet/core/repositories/UtxoRepository;", "utxoRepository$delegate", "zionRepository", "Lcom/bitcoin/mwallet/zion/ZionRepository;", "getZionRepository", "()Lcom/bitcoin/mwallet/zion/ZionRepository;", "zionRepository$delegate", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: SelectAddressBuilder.kt */
public final class SelectAddressBuilder extends ScreenBuilderBase {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(SelectAddressBuilder.class), "getAddressInteractor", "getGetAddressInteractor()Lcom/bitcoin/mwallet/core/interactors/GetAddressInteractor;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(SelectAddressBuilder.class), "getWalletInteractor", "getGetWalletInteractor()Lcom/bitcoin/mwallet/core/interactors/GetWalletInteractor;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(SelectAddressBuilder.class), "zionRepository", "getZionRepository()Lcom/bitcoin/mwallet/zion/ZionRepository;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(SelectAddressBuilder.class), "createTxInteractor", "getCreateTxInteractor()Lcom/bitcoin/mwallet/core/interactors/CreateTxInteractor;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(SelectAddressBuilder.class), "feeValidatorInteractor", "getFeeValidatorInteractor()Lcom/bitcoin/mwallet/core/interactors/FeeValidatorInteractor;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(SelectAddressBuilder.class), "currentExchangeRateInteractor", "getCurrentExchangeRateInteractor()Lcom/bitcoin/mwallet/core/interactors/GetCurrentExchangeRateInteractor;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(SelectAddressBuilder.class), "defaultCurrencyInteractor", "getDefaultCurrencyInteractor()Lcom/bitcoin/mwallet/core/interactors/GetDefaultCurrencyInteractor;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(SelectAddressBuilder.class), "getNetworkFeeInteractor", "getGetNetworkFeeInteractor()Lcom/bitcoin/mwallet/core/interactors/GetNetworkFeeInteractor;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(SelectAddressBuilder.class), "utxoRepository", "getUtxoRepository()Lcom/bitcoin/mwallet/core/repositories/UtxoRepository;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(SelectAddressBuilder.class), "analyticsService", "getAnalyticsService()Lcom/bitcoin/mwallet/core/services/AnalyticsService;"))};
    private final Lazy analyticsService$delegate;
    private final Lazy createTxInteractor$delegate;
    private final Lazy currentExchangeRateInteractor$delegate;
    private final Lazy defaultCurrencyInteractor$delegate;
    private final Lazy feeValidatorInteractor$delegate;
    @NotNull
    private final Lazy getAddressInteractor$delegate;
    private final Lazy getNetworkFeeInteractor$delegate;
    @NotNull
    private final Lazy getWalletInteractor$delegate;
    @NotNull
    private final SelectAddressPresenter presenter;
    @NotNull
    private final SelectAddressRouter router = new SelectAddressRouter();
    private final Lazy utxoRepository$delegate;
    @NotNull
    private final Lazy zionRepository$delegate;

    private final AnalyticsService getAnalyticsService() {
        Lazy lazy = this.analyticsService$delegate;
        KProperty kProperty = $$delegatedProperties[9];
        return (AnalyticsService) lazy.getValue();
    }

    private final CreateTxInteractor getCreateTxInteractor() {
        Lazy lazy = this.createTxInteractor$delegate;
        KProperty kProperty = $$delegatedProperties[3];
        return (CreateTxInteractor) lazy.getValue();
    }

    private final GetCurrentExchangeRateInteractor getCurrentExchangeRateInteractor() {
        Lazy lazy = this.currentExchangeRateInteractor$delegate;
        KProperty kProperty = $$delegatedProperties[5];
        return (GetCurrentExchangeRateInteractor) lazy.getValue();
    }

    private final GetDefaultCurrencyInteractor getDefaultCurrencyInteractor() {
        Lazy lazy = this.defaultCurrencyInteractor$delegate;
        KProperty kProperty = $$delegatedProperties[6];
        return (GetDefaultCurrencyInteractor) lazy.getValue();
    }

    private final FeeValidatorInteractor getFeeValidatorInteractor() {
        Lazy lazy = this.feeValidatorInteractor$delegate;
        KProperty kProperty = $$delegatedProperties[4];
        return (FeeValidatorInteractor) lazy.getValue();
    }

    private final GetNetworkFeeInteractor getGetNetworkFeeInteractor() {
        Lazy lazy = this.getNetworkFeeInteractor$delegate;
        KProperty kProperty = $$delegatedProperties[7];
        return (GetNetworkFeeInteractor) lazy.getValue();
    }

    private final UtxoRepository getUtxoRepository() {
        Lazy lazy = this.utxoRepository$delegate;
        KProperty kProperty = $$delegatedProperties[8];
        return (UtxoRepository) lazy.getValue();
    }

    @NotNull
    public final GetAddressInteractor getGetAddressInteractor() {
        Lazy lazy = this.getAddressInteractor$delegate;
        KProperty kProperty = $$delegatedProperties[0];
        return (GetAddressInteractor) lazy.getValue();
    }

    @NotNull
    public final GetWalletInteractor getGetWalletInteractor() {
        Lazy lazy = this.getWalletInteractor$delegate;
        KProperty kProperty = $$delegatedProperties[1];
        return (GetWalletInteractor) lazy.getValue();
    }

    @NotNull
    public final ZionRepository getZionRepository() {
        Lazy lazy = this.zionRepository$delegate;
        KProperty kProperty = $$delegatedProperties[2];
        return (ZionRepository) lazy.getValue();
    }

    public SelectAddressBuilder(@NotNull Application application) {
        Application application2 = application;
        Intrinsics.checkParameterIsNotNull(application2, "application");
        super(application);
        Qualifier qualifier = null;
        Function0 function0 = null;
        this.getAddressInteractor$delegate = LazyKt.lazy(new SelectAddressBuilder$$special$$inlined$inject$1(getKoin().getRootScope(), qualifier, function0));
        this.getWalletInteractor$delegate = LazyKt.lazy(new SelectAddressBuilder$$special$$inlined$inject$2(getKoin().getRootScope(), qualifier, function0));
        this.zionRepository$delegate = LazyKt.lazy(new SelectAddressBuilder$$special$$inlined$inject$3(getKoin().getRootScope(), qualifier, function0));
        this.createTxInteractor$delegate = LazyKt.lazy(new SelectAddressBuilder$$special$$inlined$inject$4(getKoin().getRootScope(), qualifier, function0));
        this.feeValidatorInteractor$delegate = LazyKt.lazy(new SelectAddressBuilder$$special$$inlined$inject$5(getKoin().getRootScope(), qualifier, function0));
        this.currentExchangeRateInteractor$delegate = LazyKt.lazy(new SelectAddressBuilder$$special$$inlined$inject$6(getKoin().getRootScope(), qualifier, function0));
        this.defaultCurrencyInteractor$delegate = LazyKt.lazy(new SelectAddressBuilder$$special$$inlined$inject$7(getKoin().getRootScope(), qualifier, function0));
        this.getNetworkFeeInteractor$delegate = LazyKt.lazy(new SelectAddressBuilder$$special$$inlined$inject$8(getKoin().getRootScope(), qualifier, function0));
        this.utxoRepository$delegate = LazyKt.lazy(new SelectAddressBuilder$$special$$inlined$inject$9(getKoin().getRootScope(), qualifier, function0));
        this.analyticsService$delegate = LazyKt.lazy(new SelectAddressBuilder$$special$$inlined$inject$10(getKoin().getRootScope(), qualifier, function0));
        SelectAddressPresenter selectAddressPresenter = new SelectAddressPresenter(application2, ViewModelKt.getViewModelScope(this), getGetAddressInteractor(), getGetWalletInteractor(), getZionRepository(), getCreateTxInteractor(), getFeeValidatorInteractor(), getCurrentExchangeRateInteractor(), getDefaultCurrencyInteractor(), getGetNetworkFeeInteractor(), getUtxoRepository(), getAnalyticsService(), getRouter());
        this.presenter = selectAddressPresenter;
    }

    /* access modifiers changed from: protected */
    @NotNull
    public SelectAddressRouter getRouter() {
        return this.router;
    }

    @NotNull
    public SelectAddressPresenter getPresenter() {
        return this.presenter;
    }
}
