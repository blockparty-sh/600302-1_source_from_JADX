package com.bitcoin.mwallet.app.flows.walletdetails.advanceddetails;

import android.app.Application;
import androidx.lifecycle.ViewModelKt;
import com.bitcoin.mwallet.app.viper.ScreenBuilderBase;
import com.bitcoin.mwallet.core.interactors.GetWalletInteractor;
import com.bitcoin.mwallet.core.repositories.UtxoRepository;
import com.bitcoin.mwallet.core.services.address.AddressService;
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

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u000b\u001a\u00020\fX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\u0010X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u001b\u0010\u0013\u001a\u00020\u00148BX\u0002¢\u0006\f\n\u0004\b\u0017\u0010\n\u001a\u0004\b\u0015\u0010\u0016R\u001b\u0010\u0018\u001a\u00020\u00198BX\u0002¢\u0006\f\n\u0004\b\u001c\u0010\n\u001a\u0004\b\u001a\u0010\u001b¨\u0006\u001d"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/walletdetails/advanceddetails/AdvancedDetailsBuilder;", "Lcom/bitcoin/mwallet/app/viper/ScreenBuilderBase;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "addressService", "Lcom/bitcoin/mwallet/core/services/address/AddressService;", "getAddressService", "()Lcom/bitcoin/mwallet/core/services/address/AddressService;", "addressService$delegate", "Lkotlin/Lazy;", "presenter", "Lcom/bitcoin/mwallet/app/flows/walletdetails/advanceddetails/AdvancedDetailsPresenter;", "getPresenter", "()Lcom/bitcoin/mwallet/app/flows/walletdetails/advanceddetails/AdvancedDetailsPresenter;", "router", "Lcom/bitcoin/mwallet/app/flows/walletdetails/advanceddetails/AdvancedDetailsRouter;", "getRouter", "()Lcom/bitcoin/mwallet/app/flows/walletdetails/advanceddetails/AdvancedDetailsRouter;", "utxoRepo", "Lcom/bitcoin/mwallet/core/repositories/UtxoRepository;", "getUtxoRepo", "()Lcom/bitcoin/mwallet/core/repositories/UtxoRepository;", "utxoRepo$delegate", "walletInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetWalletInteractor;", "getWalletInteractor", "()Lcom/bitcoin/mwallet/core/interactors/GetWalletInteractor;", "walletInteractor$delegate", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: AdvancedDetailsBuilder.kt */
public final class AdvancedDetailsBuilder extends ScreenBuilderBase {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(AdvancedDetailsBuilder.class), "walletInteractor", "getWalletInteractor()Lcom/bitcoin/mwallet/core/interactors/GetWalletInteractor;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(AdvancedDetailsBuilder.class), "addressService", "getAddressService()Lcom/bitcoin/mwallet/core/services/address/AddressService;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(AdvancedDetailsBuilder.class), "utxoRepo", "getUtxoRepo()Lcom/bitcoin/mwallet/core/repositories/UtxoRepository;"))};
    private final Lazy addressService$delegate;
    @NotNull
    private final AdvancedDetailsPresenter presenter;
    @NotNull
    private final AdvancedDetailsRouter router = new AdvancedDetailsRouter();
    private final Lazy utxoRepo$delegate;
    private final Lazy walletInteractor$delegate;

    private final AddressService getAddressService() {
        Lazy lazy = this.addressService$delegate;
        KProperty kProperty = $$delegatedProperties[1];
        return (AddressService) lazy.getValue();
    }

    private final UtxoRepository getUtxoRepo() {
        Lazy lazy = this.utxoRepo$delegate;
        KProperty kProperty = $$delegatedProperties[2];
        return (UtxoRepository) lazy.getValue();
    }

    private final GetWalletInteractor getWalletInteractor() {
        Lazy lazy = this.walletInteractor$delegate;
        KProperty kProperty = $$delegatedProperties[0];
        return (GetWalletInteractor) lazy.getValue();
    }

    public AdvancedDetailsBuilder(@NotNull Application application) {
        Intrinsics.checkParameterIsNotNull(application, "application");
        super(application);
        Qualifier qualifier = null;
        Function0 function0 = null;
        this.walletInteractor$delegate = LazyKt.lazy(new AdvancedDetailsBuilder$$special$$inlined$inject$1(getKoin().getRootScope(), qualifier, function0));
        this.addressService$delegate = LazyKt.lazy(new AdvancedDetailsBuilder$$special$$inlined$inject$2(getKoin().getRootScope(), qualifier, function0));
        this.utxoRepo$delegate = LazyKt.lazy(new AdvancedDetailsBuilder$$special$$inlined$inject$3(getKoin().getRootScope(), qualifier, function0));
        AdvancedDetailsPresenter advancedDetailsPresenter = new AdvancedDetailsPresenter(application, ViewModelKt.getViewModelScope(this), getWalletInteractor(), getUtxoRepo(), getAddressService(), getRouter());
        this.presenter = advancedDetailsPresenter;
    }

    /* access modifiers changed from: protected */
    @NotNull
    public AdvancedDetailsRouter getRouter() {
        return this.router;
    }

    @NotNull
    public AdvancedDetailsPresenter getPresenter() {
        return this.presenter;
    }
}
