package com.bitcoin.mwallet.app.flows.sendv2.sendamountselection;

import android.app.Application;
import androidx.lifecycle.ViewModelKt;
import com.bitcoin.mwallet.app.viper.ScreenBuilderBase;
import com.bitcoin.mwallet.core.interactors.FeeValidatorInteractor;
import com.bitcoin.mwallet.core.interactors.GetBalanceByWalletInteractor;
import com.bitcoin.mwallet.core.interactors.GetCurrentExchangeRateInteractor;
import com.bitcoin.mwallet.core.interactors.GetDefaultCurrencyInteractor;
import com.bitcoin.mwallet.core.interactors.GetWalletInteractor;
import com.bitcoin.mwallet.core.repositories.SlpRepository;
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

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u001b\u0010\u000b\u001a\u00020\f8BX\u0002¢\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\r\u0010\u000eR\u001b\u0010\u0010\u001a\u00020\u00118BX\u0002¢\u0006\f\n\u0004\b\u0014\u0010\n\u001a\u0004\b\u0012\u0010\u0013R\u001b\u0010\u0015\u001a\u00020\u00168BX\u0002¢\u0006\f\n\u0004\b\u0019\u0010\n\u001a\u0004\b\u0017\u0010\u0018R\u001b\u0010\u001a\u001a\u00020\u001b8BX\u0002¢\u0006\f\n\u0004\b\u001e\u0010\n\u001a\u0004\b\u001c\u0010\u001dR\u0014\u0010\u001f\u001a\u00020 X\u0004¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0014\u0010#\u001a\u00020$X\u0004¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u001b\u0010'\u001a\u00020(8BX\u0002¢\u0006\f\n\u0004\b+\u0010\n\u001a\u0004\b)\u0010*¨\u0006,"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/sendv2/sendamountselection/SendAmountSelectionBuilder;", "Lcom/bitcoin/mwallet/app/viper/ScreenBuilderBase;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "feeValidatorInteractor", "Lcom/bitcoin/mwallet/core/interactors/FeeValidatorInteractor;", "getFeeValidatorInteractor", "()Lcom/bitcoin/mwallet/core/interactors/FeeValidatorInteractor;", "feeValidatorInteractor$delegate", "Lkotlin/Lazy;", "getBalanceByWalletInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetBalanceByWalletInteractor;", "getGetBalanceByWalletInteractor", "()Lcom/bitcoin/mwallet/core/interactors/GetBalanceByWalletInteractor;", "getBalanceByWalletInteractor$delegate", "getDefaultCurrencyInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetDefaultCurrencyInteractor;", "getGetDefaultCurrencyInteractor", "()Lcom/bitcoin/mwallet/core/interactors/GetDefaultCurrencyInteractor;", "getDefaultCurrencyInteractor$delegate", "getExchangeRateInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetCurrentExchangeRateInteractor;", "getGetExchangeRateInteractor", "()Lcom/bitcoin/mwallet/core/interactors/GetCurrentExchangeRateInteractor;", "getExchangeRateInteractor$delegate", "getWalletInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetWalletInteractor;", "getGetWalletInteractor", "()Lcom/bitcoin/mwallet/core/interactors/GetWalletInteractor;", "getWalletInteractor$delegate", "presenter", "Lcom/bitcoin/mwallet/app/flows/sendv2/sendamountselection/SendAmountSelectionPresenter;", "getPresenter", "()Lcom/bitcoin/mwallet/app/flows/sendv2/sendamountselection/SendAmountSelectionPresenter;", "router", "Lcom/bitcoin/mwallet/app/flows/sendv2/sendamountselection/SendAmountSelectionRouter;", "getRouter", "()Lcom/bitcoin/mwallet/app/flows/sendv2/sendamountselection/SendAmountSelectionRouter;", "slpRepository", "Lcom/bitcoin/mwallet/core/repositories/SlpRepository;", "getSlpRepository", "()Lcom/bitcoin/mwallet/core/repositories/SlpRepository;", "slpRepository$delegate", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: SendAmountSelectionBuilder.kt */
public final class SendAmountSelectionBuilder extends ScreenBuilderBase {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(SendAmountSelectionBuilder.class), "getDefaultCurrencyInteractor", "getGetDefaultCurrencyInteractor()Lcom/bitcoin/mwallet/core/interactors/GetDefaultCurrencyInteractor;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(SendAmountSelectionBuilder.class), "getExchangeRateInteractor", "getGetExchangeRateInteractor()Lcom/bitcoin/mwallet/core/interactors/GetCurrentExchangeRateInteractor;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(SendAmountSelectionBuilder.class), "getWalletInteractor", "getGetWalletInteractor()Lcom/bitcoin/mwallet/core/interactors/GetWalletInteractor;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(SendAmountSelectionBuilder.class), "getBalanceByWalletInteractor", "getGetBalanceByWalletInteractor()Lcom/bitcoin/mwallet/core/interactors/GetBalanceByWalletInteractor;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(SendAmountSelectionBuilder.class), "slpRepository", "getSlpRepository()Lcom/bitcoin/mwallet/core/repositories/SlpRepository;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(SendAmountSelectionBuilder.class), "feeValidatorInteractor", "getFeeValidatorInteractor()Lcom/bitcoin/mwallet/core/interactors/FeeValidatorInteractor;"))};
    private final Lazy feeValidatorInteractor$delegate;
    private final Lazy getBalanceByWalletInteractor$delegate;
    private final Lazy getDefaultCurrencyInteractor$delegate;
    private final Lazy getExchangeRateInteractor$delegate;
    private final Lazy getWalletInteractor$delegate;
    @NotNull
    private final SendAmountSelectionPresenter presenter;
    @NotNull
    private final SendAmountSelectionRouter router = new SendAmountSelectionRouter();
    private final Lazy slpRepository$delegate;

    private final FeeValidatorInteractor getFeeValidatorInteractor() {
        Lazy lazy = this.feeValidatorInteractor$delegate;
        KProperty kProperty = $$delegatedProperties[5];
        return (FeeValidatorInteractor) lazy.getValue();
    }

    private final GetBalanceByWalletInteractor getGetBalanceByWalletInteractor() {
        Lazy lazy = this.getBalanceByWalletInteractor$delegate;
        KProperty kProperty = $$delegatedProperties[3];
        return (GetBalanceByWalletInteractor) lazy.getValue();
    }

    private final GetDefaultCurrencyInteractor getGetDefaultCurrencyInteractor() {
        Lazy lazy = this.getDefaultCurrencyInteractor$delegate;
        KProperty kProperty = $$delegatedProperties[0];
        return (GetDefaultCurrencyInteractor) lazy.getValue();
    }

    private final GetCurrentExchangeRateInteractor getGetExchangeRateInteractor() {
        Lazy lazy = this.getExchangeRateInteractor$delegate;
        KProperty kProperty = $$delegatedProperties[1];
        return (GetCurrentExchangeRateInteractor) lazy.getValue();
    }

    private final GetWalletInteractor getGetWalletInteractor() {
        Lazy lazy = this.getWalletInteractor$delegate;
        KProperty kProperty = $$delegatedProperties[2];
        return (GetWalletInteractor) lazy.getValue();
    }

    private final SlpRepository getSlpRepository() {
        Lazy lazy = this.slpRepository$delegate;
        KProperty kProperty = $$delegatedProperties[4];
        return (SlpRepository) lazy.getValue();
    }

    public SendAmountSelectionBuilder(@NotNull Application application) {
        Intrinsics.checkParameterIsNotNull(application, "application");
        super(application);
        Qualifier qualifier = null;
        Function0 function0 = null;
        this.getDefaultCurrencyInteractor$delegate = LazyKt.lazy(new SendAmountSelectionBuilder$$special$$inlined$inject$1(getKoin().getRootScope(), qualifier, function0));
        this.getExchangeRateInteractor$delegate = LazyKt.lazy(new SendAmountSelectionBuilder$$special$$inlined$inject$2(getKoin().getRootScope(), qualifier, function0));
        this.getWalletInteractor$delegate = LazyKt.lazy(new SendAmountSelectionBuilder$$special$$inlined$inject$3(getKoin().getRootScope(), qualifier, function0));
        this.getBalanceByWalletInteractor$delegate = LazyKt.lazy(new SendAmountSelectionBuilder$$special$$inlined$inject$4(getKoin().getRootScope(), qualifier, function0));
        this.slpRepository$delegate = LazyKt.lazy(new SendAmountSelectionBuilder$$special$$inlined$inject$5(getKoin().getRootScope(), qualifier, function0));
        this.feeValidatorInteractor$delegate = LazyKt.lazy(new SendAmountSelectionBuilder$$special$$inlined$inject$6(getKoin().getRootScope(), qualifier, function0));
        SendAmountSelectionPresenter sendAmountSelectionPresenter = new SendAmountSelectionPresenter(application, ViewModelKt.getViewModelScope(this), getGetDefaultCurrencyInteractor(), getSlpRepository(), getGetExchangeRateInteractor(), getGetWalletInteractor(), getGetBalanceByWalletInteractor(), getFeeValidatorInteractor(), getRouter());
        this.presenter = sendAmountSelectionPresenter;
    }

    /* access modifiers changed from: protected */
    @NotNull
    public SendAmountSelectionRouter getRouter() {
        return this.router;
    }

    @NotNull
    public SendAmountSelectionPresenter getPresenter() {
        return this.presenter;
    }
}
