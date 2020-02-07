package com.bitcoin.mwallet.app.flows.walletdetails.scanfortransactions;

import android.app.Application;
import androidx.lifecycle.ViewModelKt;
import com.bitcoin.mwallet.app.viper.ScreenBuilderBase;
import com.bitcoin.mwallet.core.interactors.GetWalletInteractor;
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

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\u000b\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\u00020\u000eX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0011\u001a\u00020\u0012X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u001b\u0010\u0015\u001a\u00020\u00168BX\u0002¢\u0006\f\n\u0004\b\u0019\u0010\n\u001a\u0004\b\u0017\u0010\u0018¨\u0006\u001a"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/walletdetails/scanfortransactions/ScanForTransactionsBuilder;", "Lcom/bitcoin/mwallet/app/viper/ScreenBuilderBase;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "addressService", "Lcom/bitcoin/mwallet/core/services/address/AddressService;", "getAddressService", "()Lcom/bitcoin/mwallet/core/services/address/AddressService;", "addressService$delegate", "Lkotlin/Lazy;", "interactor", "Lcom/bitcoin/mwallet/app/flows/walletdetails/scanfortransactions/ScanForTransactionsInteractor;", "presenter", "Lcom/bitcoin/mwallet/app/flows/walletdetails/scanfortransactions/ScanForTransactionsPresenter;", "getPresenter", "()Lcom/bitcoin/mwallet/app/flows/walletdetails/scanfortransactions/ScanForTransactionsPresenter;", "router", "Lcom/bitcoin/mwallet/app/flows/walletdetails/scanfortransactions/ScanForTransactionsRouter;", "getRouter", "()Lcom/bitcoin/mwallet/app/flows/walletdetails/scanfortransactions/ScanForTransactionsRouter;", "walletInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetWalletInteractor;", "getWalletInteractor", "()Lcom/bitcoin/mwallet/core/interactors/GetWalletInteractor;", "walletInteractor$delegate", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: ScanForTransactionsBuilder.kt */
public final class ScanForTransactionsBuilder extends ScreenBuilderBase {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(ScanForTransactionsBuilder.class), "addressService", "getAddressService()Lcom/bitcoin/mwallet/core/services/address/AddressService;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(ScanForTransactionsBuilder.class), "walletInteractor", "getWalletInteractor()Lcom/bitcoin/mwallet/core/interactors/GetWalletInteractor;"))};
    private final Lazy addressService$delegate;
    private final ScanForTransactionsInteractor interactor = new ScanForTransactionsInteractor();
    @NotNull
    private final ScanForTransactionsPresenter presenter;
    @NotNull
    private final ScanForTransactionsRouter router = new ScanForTransactionsRouter();
    private final Lazy walletInteractor$delegate;

    private final AddressService getAddressService() {
        Lazy lazy = this.addressService$delegate;
        KProperty kProperty = $$delegatedProperties[0];
        return (AddressService) lazy.getValue();
    }

    private final GetWalletInteractor getWalletInteractor() {
        Lazy lazy = this.walletInteractor$delegate;
        KProperty kProperty = $$delegatedProperties[1];
        return (GetWalletInteractor) lazy.getValue();
    }

    public ScanForTransactionsBuilder(@NotNull Application application) {
        Intrinsics.checkParameterIsNotNull(application, "application");
        super(application);
        Qualifier qualifier = null;
        Function0 function0 = null;
        this.addressService$delegate = LazyKt.lazy(new ScanForTransactionsBuilder$$special$$inlined$inject$1(getKoin().getRootScope(), qualifier, function0));
        this.walletInteractor$delegate = LazyKt.lazy(new ScanForTransactionsBuilder$$special$$inlined$inject$2(getKoin().getRootScope(), qualifier, function0));
        ScanForTransactionsPresenter scanForTransactionsPresenter = new ScanForTransactionsPresenter(application, ViewModelKt.getViewModelScope(this), this.interactor, getRouter(), getAddressService(), getWalletInteractor());
        this.presenter = scanForTransactionsPresenter;
    }

    /* access modifiers changed from: protected */
    @NotNull
    public ScanForTransactionsRouter getRouter() {
        return this.router;
    }

    @NotNull
    public ScanForTransactionsPresenter getPresenter() {
        return this.presenter;
    }
}
