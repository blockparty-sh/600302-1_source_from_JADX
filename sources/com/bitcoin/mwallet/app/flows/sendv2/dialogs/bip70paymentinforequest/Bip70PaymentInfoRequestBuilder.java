package com.bitcoin.mwallet.app.flows.sendv2.dialogs.bip70paymentinforequest;

import android.app.Application;
import androidx.lifecycle.ViewModelKt;
import com.bitcoin.mwallet.app.viper.ScreenBuilderBase;
import com.bitcoin.mwallet.core.interactors.Bip70PaymentInteractor;
import com.bitcoin.mwallet.core.interactors.CreateTxInteractor;
import com.bitcoin.mwallet.core.interactors.FeeValidatorInteractor;
import com.bitcoin.mwallet.core.interactors.GetBalanceByWalletInteractor;
import com.bitcoin.mwallet.core.interactors.GetCurrentExchangeRateInteractor;
import com.bitcoin.mwallet.core.interactors.GetDefaultCurrencyInteractor;
import com.bitcoin.mwallet.core.interactors.GetNetworkFeeInteractor;
import com.bitcoin.mwallet.core.interactors.GetWalletInteractor;
import com.bitcoin.mwallet.core.repositories.UtxoRepository;
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

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u001b\u0010\u000b\u001a\u00020\f8BX\u0002¢\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\r\u0010\u000eR\u001b\u0010\u0010\u001a\u00020\u00118BX\u0002¢\u0006\f\n\u0004\b\u0014\u0010\n\u001a\u0004\b\u0012\u0010\u0013R\u001b\u0010\u0015\u001a\u00020\u00168BX\u0002¢\u0006\f\n\u0004\b\u0019\u0010\n\u001a\u0004\b\u0017\u0010\u0018R\u001b\u0010\u001a\u001a\u00020\u001b8BX\u0002¢\u0006\f\n\u0004\b\u001e\u0010\n\u001a\u0004\b\u001c\u0010\u001dR\u001b\u0010\u001f\u001a\u00020 8BX\u0002¢\u0006\f\n\u0004\b#\u0010\n\u001a\u0004\b!\u0010\"R\u001b\u0010$\u001a\u00020%8BX\u0002¢\u0006\f\n\u0004\b(\u0010\n\u001a\u0004\b&\u0010'R\u001b\u0010)\u001a\u00020*8BX\u0002¢\u0006\f\n\u0004\b-\u0010\n\u001a\u0004\b+\u0010,R\u0014\u0010.\u001a\u00020/X\u0004¢\u0006\b\n\u0000\u001a\u0004\b0\u00101R\u0014\u00102\u001a\u000203X\u0004¢\u0006\b\n\u0000\u001a\u0004\b4\u00105R\u001b\u00106\u001a\u0002078BX\u0002¢\u0006\f\n\u0004\b:\u0010\n\u001a\u0004\b8\u00109R\u001b\u0010;\u001a\u00020<8BX\u0002¢\u0006\f\n\u0004\b?\u0010\n\u001a\u0004\b=\u0010>¨\u0006@"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/bip70paymentinforequest/Bip70PaymentInfoRequestBuilder;", "Lcom/bitcoin/mwallet/app/viper/ScreenBuilderBase;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "analyticsService", "Lcom/bitcoin/mwallet/core/services/AnalyticsService;", "getAnalyticsService", "()Lcom/bitcoin/mwallet/core/services/AnalyticsService;", "analyticsService$delegate", "Lkotlin/Lazy;", "bip70PaymentInteractor", "Lcom/bitcoin/mwallet/core/interactors/Bip70PaymentInteractor;", "getBip70PaymentInteractor", "()Lcom/bitcoin/mwallet/core/interactors/Bip70PaymentInteractor;", "bip70PaymentInteractor$delegate", "createTxInteractor", "Lcom/bitcoin/mwallet/core/interactors/CreateTxInteractor;", "getCreateTxInteractor", "()Lcom/bitcoin/mwallet/core/interactors/CreateTxInteractor;", "createTxInteractor$delegate", "defaultCurrencyInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetDefaultCurrencyInteractor;", "getDefaultCurrencyInteractor", "()Lcom/bitcoin/mwallet/core/interactors/GetDefaultCurrencyInteractor;", "defaultCurrencyInteractor$delegate", "exchangeRateInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetCurrentExchangeRateInteractor;", "getExchangeRateInteractor", "()Lcom/bitcoin/mwallet/core/interactors/GetCurrentExchangeRateInteractor;", "exchangeRateInteractor$delegate", "feeValidatorInteractor", "Lcom/bitcoin/mwallet/core/interactors/FeeValidatorInteractor;", "getFeeValidatorInteractor", "()Lcom/bitcoin/mwallet/core/interactors/FeeValidatorInteractor;", "feeValidatorInteractor$delegate", "getBalanceByWalletInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetBalanceByWalletInteractor;", "getGetBalanceByWalletInteractor", "()Lcom/bitcoin/mwallet/core/interactors/GetBalanceByWalletInteractor;", "getBalanceByWalletInteractor$delegate", "getNetworkFeeInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetNetworkFeeInteractor;", "getGetNetworkFeeInteractor", "()Lcom/bitcoin/mwallet/core/interactors/GetNetworkFeeInteractor;", "getNetworkFeeInteractor$delegate", "presenter", "Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/bip70paymentinforequest/Bip70PaymentInfoRequestPresenter;", "getPresenter", "()Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/bip70paymentinforequest/Bip70PaymentInfoRequestPresenter;", "router", "Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/bip70paymentinforequest/Bip70PaymentInfoRequestRouter;", "getRouter", "()Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/bip70paymentinforequest/Bip70PaymentInfoRequestRouter;", "utxoService", "Lcom/bitcoin/mwallet/core/repositories/UtxoRepository;", "getUtxoService", "()Lcom/bitcoin/mwallet/core/repositories/UtxoRepository;", "utxoService$delegate", "walletInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetWalletInteractor;", "getWalletInteractor", "()Lcom/bitcoin/mwallet/core/interactors/GetWalletInteractor;", "walletInteractor$delegate", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: Bip70PaymentInfoRequestBuilder.kt */
public final class Bip70PaymentInfoRequestBuilder extends ScreenBuilderBase {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(Bip70PaymentInfoRequestBuilder.class), "walletInteractor", "getWalletInteractor()Lcom/bitcoin/mwallet/core/interactors/GetWalletInteractor;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(Bip70PaymentInfoRequestBuilder.class), "getBalanceByWalletInteractor", "getGetBalanceByWalletInteractor()Lcom/bitcoin/mwallet/core/interactors/GetBalanceByWalletInteractor;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(Bip70PaymentInfoRequestBuilder.class), "bip70PaymentInteractor", "getBip70PaymentInteractor()Lcom/bitcoin/mwallet/core/interactors/Bip70PaymentInteractor;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(Bip70PaymentInfoRequestBuilder.class), "exchangeRateInteractor", "getExchangeRateInteractor()Lcom/bitcoin/mwallet/core/interactors/GetCurrentExchangeRateInteractor;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(Bip70PaymentInfoRequestBuilder.class), "defaultCurrencyInteractor", "getDefaultCurrencyInteractor()Lcom/bitcoin/mwallet/core/interactors/GetDefaultCurrencyInteractor;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(Bip70PaymentInfoRequestBuilder.class), "utxoService", "getUtxoService()Lcom/bitcoin/mwallet/core/repositories/UtxoRepository;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(Bip70PaymentInfoRequestBuilder.class), "getNetworkFeeInteractor", "getGetNetworkFeeInteractor()Lcom/bitcoin/mwallet/core/interactors/GetNetworkFeeInteractor;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(Bip70PaymentInfoRequestBuilder.class), "createTxInteractor", "getCreateTxInteractor()Lcom/bitcoin/mwallet/core/interactors/CreateTxInteractor;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(Bip70PaymentInfoRequestBuilder.class), "feeValidatorInteractor", "getFeeValidatorInteractor()Lcom/bitcoin/mwallet/core/interactors/FeeValidatorInteractor;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(Bip70PaymentInfoRequestBuilder.class), "analyticsService", "getAnalyticsService()Lcom/bitcoin/mwallet/core/services/AnalyticsService;"))};
    private final Lazy analyticsService$delegate;
    private final Lazy bip70PaymentInteractor$delegate;
    private final Lazy createTxInteractor$delegate;
    private final Lazy defaultCurrencyInteractor$delegate;
    private final Lazy exchangeRateInteractor$delegate;
    private final Lazy feeValidatorInteractor$delegate;
    private final Lazy getBalanceByWalletInteractor$delegate;
    private final Lazy getNetworkFeeInteractor$delegate;
    @NotNull
    private final Bip70PaymentInfoRequestPresenter presenter;
    @NotNull
    private final Bip70PaymentInfoRequestRouter router = new Bip70PaymentInfoRequestRouter();
    private final Lazy utxoService$delegate;
    private final Lazy walletInteractor$delegate;

    private final AnalyticsService getAnalyticsService() {
        Lazy lazy = this.analyticsService$delegate;
        KProperty kProperty = $$delegatedProperties[9];
        return (AnalyticsService) lazy.getValue();
    }

    private final Bip70PaymentInteractor getBip70PaymentInteractor() {
        Lazy lazy = this.bip70PaymentInteractor$delegate;
        KProperty kProperty = $$delegatedProperties[2];
        return (Bip70PaymentInteractor) lazy.getValue();
    }

    private final CreateTxInteractor getCreateTxInteractor() {
        Lazy lazy = this.createTxInteractor$delegate;
        KProperty kProperty = $$delegatedProperties[7];
        return (CreateTxInteractor) lazy.getValue();
    }

    private final GetDefaultCurrencyInteractor getDefaultCurrencyInteractor() {
        Lazy lazy = this.defaultCurrencyInteractor$delegate;
        KProperty kProperty = $$delegatedProperties[4];
        return (GetDefaultCurrencyInteractor) lazy.getValue();
    }

    private final GetCurrentExchangeRateInteractor getExchangeRateInteractor() {
        Lazy lazy = this.exchangeRateInteractor$delegate;
        KProperty kProperty = $$delegatedProperties[3];
        return (GetCurrentExchangeRateInteractor) lazy.getValue();
    }

    private final FeeValidatorInteractor getFeeValidatorInteractor() {
        Lazy lazy = this.feeValidatorInteractor$delegate;
        KProperty kProperty = $$delegatedProperties[8];
        return (FeeValidatorInteractor) lazy.getValue();
    }

    private final GetBalanceByWalletInteractor getGetBalanceByWalletInteractor() {
        Lazy lazy = this.getBalanceByWalletInteractor$delegate;
        KProperty kProperty = $$delegatedProperties[1];
        return (GetBalanceByWalletInteractor) lazy.getValue();
    }

    private final GetNetworkFeeInteractor getGetNetworkFeeInteractor() {
        Lazy lazy = this.getNetworkFeeInteractor$delegate;
        KProperty kProperty = $$delegatedProperties[6];
        return (GetNetworkFeeInteractor) lazy.getValue();
    }

    private final UtxoRepository getUtxoService() {
        Lazy lazy = this.utxoService$delegate;
        KProperty kProperty = $$delegatedProperties[5];
        return (UtxoRepository) lazy.getValue();
    }

    private final GetWalletInteractor getWalletInteractor() {
        Lazy lazy = this.walletInteractor$delegate;
        KProperty kProperty = $$delegatedProperties[0];
        return (GetWalletInteractor) lazy.getValue();
    }

    public Bip70PaymentInfoRequestBuilder(@NotNull Application application) {
        Application application2 = application;
        Intrinsics.checkParameterIsNotNull(application2, "application");
        super(application);
        Qualifier qualifier = null;
        Function0 function0 = null;
        this.walletInteractor$delegate = LazyKt.lazy(new Bip70PaymentInfoRequestBuilder$$special$$inlined$inject$1(getKoin().getRootScope(), qualifier, function0));
        this.getBalanceByWalletInteractor$delegate = LazyKt.lazy(new Bip70PaymentInfoRequestBuilder$$special$$inlined$inject$2(getKoin().getRootScope(), qualifier, function0));
        this.bip70PaymentInteractor$delegate = LazyKt.lazy(new Bip70PaymentInfoRequestBuilder$$special$$inlined$inject$3(getKoin().getRootScope(), qualifier, function0));
        this.exchangeRateInteractor$delegate = LazyKt.lazy(new Bip70PaymentInfoRequestBuilder$$special$$inlined$inject$4(getKoin().getRootScope(), qualifier, function0));
        this.defaultCurrencyInteractor$delegate = LazyKt.lazy(new Bip70PaymentInfoRequestBuilder$$special$$inlined$inject$5(getKoin().getRootScope(), qualifier, function0));
        this.utxoService$delegate = LazyKt.lazy(new Bip70PaymentInfoRequestBuilder$$special$$inlined$inject$6(getKoin().getRootScope(), qualifier, function0));
        this.getNetworkFeeInteractor$delegate = LazyKt.lazy(new Bip70PaymentInfoRequestBuilder$$special$$inlined$inject$7(getKoin().getRootScope(), qualifier, function0));
        this.createTxInteractor$delegate = LazyKt.lazy(new Bip70PaymentInfoRequestBuilder$$special$$inlined$inject$8(getKoin().getRootScope(), qualifier, function0));
        this.feeValidatorInteractor$delegate = LazyKt.lazy(new Bip70PaymentInfoRequestBuilder$$special$$inlined$inject$9(getKoin().getRootScope(), qualifier, function0));
        this.analyticsService$delegate = LazyKt.lazy(new Bip70PaymentInfoRequestBuilder$$special$$inlined$inject$10(getKoin().getRootScope(), qualifier, function0));
        Bip70PaymentInfoRequestPresenter bip70PaymentInfoRequestPresenter = new Bip70PaymentInfoRequestPresenter(application2, ViewModelKt.getViewModelScope(this), getCreateTxInteractor(), getGetNetworkFeeInteractor(), getUtxoService(), getWalletInteractor(), getGetBalanceByWalletInteractor(), getBip70PaymentInteractor(), getExchangeRateInteractor(), getDefaultCurrencyInteractor(), getFeeValidatorInteractor(), getAnalyticsService(), getRouter());
        this.presenter = bip70PaymentInfoRequestPresenter;
    }

    /* access modifiers changed from: protected */
    @NotNull
    public Bip70PaymentInfoRequestRouter getRouter() {
        return this.router;
    }

    @NotNull
    public Bip70PaymentInfoRequestPresenter getPresenter() {
        return this.presenter;
    }
}
