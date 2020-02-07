package com.bitcoin.mwallet.app.components.lockedwalletdialog;

import android.app.Application;
import androidx.lifecycle.ViewModelKt;
import com.bitcoin.mwallet.app.viper.ComponentBuilderBase;
import com.bitcoin.mwallet.core.interactors.CreateWalletInteractor;
import com.bitcoin.mwallet.core.interactors.GetCredentialInteractor;
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

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001b\u0010\u0005\u001a\u00020\u00068FX\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u001b\u0010\u000b\u001a\u00020\f8FX\u0002¢\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\r\u0010\u000eR\u001b\u0010\u0010\u001a\u00020\u00118FX\u0002¢\u0006\f\n\u0004\b\u0014\u0010\n\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0015\u001a\u00020\u0016¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u001b\u0010\u0019\u001a\u00020\u001a8FX\u0002¢\u0006\f\n\u0004\b\u001d\u0010\n\u001a\u0004\b\u001b\u0010\u001cR\u0014\u0010\u001e\u001a\u00020\u001fX\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010!¨\u0006\""}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/components/lockedwalletdialog/LockedWalletDialogBuilder;", "Lcom/bitcoin/mwallet/app/viper/ComponentBuilderBase;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "createWalletInteractor", "Lcom/bitcoin/mwallet/core/interactors/CreateWalletInteractor;", "getCreateWalletInteractor", "()Lcom/bitcoin/mwallet/core/interactors/CreateWalletInteractor;", "createWalletInteractor$delegate", "Lkotlin/Lazy;", "credentialInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetCredentialInteractor;", "getCredentialInteractor", "()Lcom/bitcoin/mwallet/core/interactors/GetCredentialInteractor;", "credentialInteractor$delegate", "getWalletInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetWalletInteractor;", "getGetWalletInteractor", "()Lcom/bitcoin/mwallet/core/interactors/GetWalletInteractor;", "getWalletInteractor$delegate", "interactor", "Lcom/bitcoin/mwallet/app/components/lockedwalletdialog/LockedWalletDialogInteractor;", "getInteractor", "()Lcom/bitcoin/mwallet/app/components/lockedwalletdialog/LockedWalletDialogInteractor;", "modifyWalletInteractor", "Lcom/bitcoin/mwallet/core/interactors/ModifyWalletInteractor;", "getModifyWalletInteractor", "()Lcom/bitcoin/mwallet/core/interactors/ModifyWalletInteractor;", "modifyWalletInteractor$delegate", "presenter", "Lcom/bitcoin/mwallet/app/components/lockedwalletdialog/LockedWalletDialogPresenter;", "getPresenter", "()Lcom/bitcoin/mwallet/app/components/lockedwalletdialog/LockedWalletDialogPresenter;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: LockedWalletDialogBuilder.kt */
public final class LockedWalletDialogBuilder extends ComponentBuilderBase {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(LockedWalletDialogBuilder.class), "credentialInteractor", "getCredentialInteractor()Lcom/bitcoin/mwallet/core/interactors/GetCredentialInteractor;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(LockedWalletDialogBuilder.class), "getWalletInteractor", "getGetWalletInteractor()Lcom/bitcoin/mwallet/core/interactors/GetWalletInteractor;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(LockedWalletDialogBuilder.class), "createWalletInteractor", "getCreateWalletInteractor()Lcom/bitcoin/mwallet/core/interactors/CreateWalletInteractor;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(LockedWalletDialogBuilder.class), "modifyWalletInteractor", "getModifyWalletInteractor()Lcom/bitcoin/mwallet/core/interactors/ModifyWalletInteractor;"))};
    @NotNull
    private final Lazy createWalletInteractor$delegate;
    @NotNull
    private final Lazy credentialInteractor$delegate;
    @NotNull
    private final Lazy getWalletInteractor$delegate;
    @NotNull
    private final LockedWalletDialogInteractor interactor = new LockedWalletDialogInteractor(getCredentialInteractor(), getGetWalletInteractor(), getCreateWalletInteractor(), getModifyWalletInteractor());
    @NotNull
    private final Lazy modifyWalletInteractor$delegate;
    @NotNull
    private final LockedWalletDialogPresenter presenter;

    @NotNull
    public final CreateWalletInteractor getCreateWalletInteractor() {
        Lazy lazy = this.createWalletInteractor$delegate;
        KProperty kProperty = $$delegatedProperties[2];
        return (CreateWalletInteractor) lazy.getValue();
    }

    @NotNull
    public final GetCredentialInteractor getCredentialInteractor() {
        Lazy lazy = this.credentialInteractor$delegate;
        KProperty kProperty = $$delegatedProperties[0];
        return (GetCredentialInteractor) lazy.getValue();
    }

    @NotNull
    public final GetWalletInteractor getGetWalletInteractor() {
        Lazy lazy = this.getWalletInteractor$delegate;
        KProperty kProperty = $$delegatedProperties[1];
        return (GetWalletInteractor) lazy.getValue();
    }

    @NotNull
    public final ModifyWalletInteractor getModifyWalletInteractor() {
        Lazy lazy = this.modifyWalletInteractor$delegate;
        KProperty kProperty = $$delegatedProperties[3];
        return (ModifyWalletInteractor) lazy.getValue();
    }

    public LockedWalletDialogBuilder(@NotNull Application application) {
        Intrinsics.checkParameterIsNotNull(application, "application");
        super(application);
        Qualifier qualifier = null;
        Function0 function0 = null;
        this.credentialInteractor$delegate = LazyKt.lazy(new LockedWalletDialogBuilder$$special$$inlined$inject$1(getKoin().getRootScope(), qualifier, function0));
        this.getWalletInteractor$delegate = LazyKt.lazy(new LockedWalletDialogBuilder$$special$$inlined$inject$2(getKoin().getRootScope(), qualifier, function0));
        this.createWalletInteractor$delegate = LazyKt.lazy(new LockedWalletDialogBuilder$$special$$inlined$inject$3(getKoin().getRootScope(), qualifier, function0));
        this.modifyWalletInteractor$delegate = LazyKt.lazy(new LockedWalletDialogBuilder$$special$$inlined$inject$4(getKoin().getRootScope(), qualifier, function0));
        this.presenter = new LockedWalletDialogPresenter(application, ViewModelKt.getViewModelScope(this), this.interactor);
    }

    @NotNull
    public final LockedWalletDialogInteractor getInteractor() {
        return this.interactor;
    }

    @NotNull
    public LockedWalletDialogPresenter getPresenter() {
        return this.presenter;
    }
}
