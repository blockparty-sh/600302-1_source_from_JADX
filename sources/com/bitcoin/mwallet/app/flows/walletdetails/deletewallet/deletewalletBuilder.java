package com.bitcoin.mwallet.app.flows.walletdetails.deletewallet;

import android.app.Application;
import androidx.lifecycle.ViewModelKt;
import com.bitcoin.mwallet.app.viper.ScreenBuilderBase;
import com.bitcoin.mwallet.core.interactors.GetWalletInteractor;
import com.bitcoin.mwallet.core.interactors.ModifyWalletInteractor;
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

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001b\u0010\u0005\u001a\u00020\u00068FX\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u000b\u001a\u00020\fX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\u0010X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u001b\u0010\u0013\u001a\u00020\u00148FX\u0002¢\u0006\f\n\u0004\b\u0017\u0010\n\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u0018"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/walletdetails/deletewallet/deletewalletBuilder;", "Lcom/bitcoin/mwallet/app/viper/ScreenBuilderBase;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "modifyWalletInteractor", "Lcom/bitcoin/mwallet/core/interactors/ModifyWalletInteractor;", "getModifyWalletInteractor", "()Lcom/bitcoin/mwallet/core/interactors/ModifyWalletInteractor;", "modifyWalletInteractor$delegate", "Lkotlin/Lazy;", "presenter", "Lcom/bitcoin/mwallet/app/flows/walletdetails/deletewallet/DeletewalletPresenter;", "getPresenter", "()Lcom/bitcoin/mwallet/app/flows/walletdetails/deletewallet/DeletewalletPresenter;", "router", "Lcom/bitcoin/mwallet/app/flows/walletdetails/deletewallet/DeleteWalletRouter;", "getRouter", "()Lcom/bitcoin/mwallet/app/flows/walletdetails/deletewallet/DeleteWalletRouter;", "walletInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetWalletInteractor;", "getWalletInteractor", "()Lcom/bitcoin/mwallet/core/interactors/GetWalletInteractor;", "walletInteractor$delegate", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: DeleteWalletBuilder.kt */
public final class deletewalletBuilder extends ScreenBuilderBase {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(deletewalletBuilder.class), "walletInteractor", "getWalletInteractor()Lcom/bitcoin/mwallet/core/interactors/GetWalletInteractor;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(deletewalletBuilder.class), "modifyWalletInteractor", "getModifyWalletInteractor()Lcom/bitcoin/mwallet/core/interactors/ModifyWalletInteractor;"))};
    @NotNull
    private final Lazy modifyWalletInteractor$delegate;
    @NotNull
    private final DeletewalletPresenter presenter;
    @NotNull
    private final DeleteWalletRouter router = new DeleteWalletRouter();
    @NotNull
    private final Lazy walletInteractor$delegate;

    @NotNull
    public final ModifyWalletInteractor getModifyWalletInteractor() {
        Lazy lazy = this.modifyWalletInteractor$delegate;
        KProperty kProperty = $$delegatedProperties[1];
        return (ModifyWalletInteractor) lazy.getValue();
    }

    @NotNull
    public final GetWalletInteractor getWalletInteractor() {
        Lazy lazy = this.walletInteractor$delegate;
        KProperty kProperty = $$delegatedProperties[0];
        return (GetWalletInteractor) lazy.getValue();
    }

    public deletewalletBuilder(@NotNull Application application) {
        Intrinsics.checkParameterIsNotNull(application, "application");
        super(application);
        Qualifier qualifier = null;
        Function0 function0 = null;
        this.walletInteractor$delegate = LazyKt.lazy(new deletewalletBuilder$$special$$inlined$inject$1(getKoin().getRootScope(), qualifier, function0));
        this.modifyWalletInteractor$delegate = LazyKt.lazy(new deletewalletBuilder$$special$$inlined$inject$2(getKoin().getRootScope(), qualifier, function0));
        DeletewalletPresenter deletewalletPresenter = new DeletewalletPresenter(application, ViewModelKt.getViewModelScope(this), getWalletInteractor(), getModifyWalletInteractor(), getRouter());
        this.presenter = deletewalletPresenter;
    }

    /* access modifiers changed from: protected */
    @NotNull
    public DeleteWalletRouter getRouter() {
        return this.router;
    }

    @NotNull
    public DeletewalletPresenter getPresenter() {
        return this.presenter;
    }
}
