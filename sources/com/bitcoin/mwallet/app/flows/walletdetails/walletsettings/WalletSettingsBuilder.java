package com.bitcoin.mwallet.app.flows.walletdetails.walletsettings;

import android.app.Application;
import androidx.lifecycle.ViewModelKt;
import com.bitcoin.mwallet.app.viper.ScreenBuilderBase;
import com.bitcoin.mwallet.core.interactors.GetDefaultCurrencyInteractor;
import com.bitcoin.mwallet.core.interactors.GetWalletInteractor;
import com.bitcoin.mwallet.core.interactors.ModifyWalletInteractor;
import com.bitcoin.mwallet.core.repositories.WalletRepository;
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

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001b\u0010\u0005\u001a\u00020\u00068FX\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u001b\u0010\u000b\u001a\u00020\f8FX\u0002¢\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u0010\u001a\u00020\u0011X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0014\u001a\u00020\u0015X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u001b\u0010\u0018\u001a\u00020\u00198FX\u0002¢\u0006\f\n\u0004\b\u001c\u0010\n\u001a\u0004\b\u001a\u0010\u001bR\u001b\u0010\u001d\u001a\u00020\u001e8FX\u0002¢\u0006\f\n\u0004\b!\u0010\n\u001a\u0004\b\u001f\u0010 R\u001b\u0010\"\u001a\u00020#8FX\u0002¢\u0006\f\n\u0004\b&\u0010\n\u001a\u0004\b$\u0010%¨\u0006'"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/walletdetails/walletsettings/WalletSettingsBuilder;", "Lcom/bitcoin/mwallet/app/viper/ScreenBuilderBase;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "defaultCurrencyInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetDefaultCurrencyInteractor;", "getDefaultCurrencyInteractor", "()Lcom/bitcoin/mwallet/core/interactors/GetDefaultCurrencyInteractor;", "defaultCurrencyInteractor$delegate", "Lkotlin/Lazy;", "modifyWalletInteractor", "Lcom/bitcoin/mwallet/core/interactors/ModifyWalletInteractor;", "getModifyWalletInteractor", "()Lcom/bitcoin/mwallet/core/interactors/ModifyWalletInteractor;", "modifyWalletInteractor$delegate", "presenter", "Lcom/bitcoin/mwallet/app/flows/walletdetails/walletsettings/WalletSettingsPresenter;", "getPresenter", "()Lcom/bitcoin/mwallet/app/flows/walletdetails/walletsettings/WalletSettingsPresenter;", "router", "Lcom/bitcoin/mwallet/app/flows/walletdetails/walletsettings/WalletSettingsRouter;", "getRouter", "()Lcom/bitcoin/mwallet/app/flows/walletdetails/walletsettings/WalletSettingsRouter;", "walletInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetWalletInteractor;", "getWalletInteractor", "()Lcom/bitcoin/mwallet/core/interactors/GetWalletInteractor;", "walletInteractor$delegate", "walletRepository", "Lcom/bitcoin/mwallet/core/repositories/WalletRepository;", "getWalletRepository", "()Lcom/bitcoin/mwallet/core/repositories/WalletRepository;", "walletRepository$delegate", "zionRepository", "Lcom/bitcoin/mwallet/zion/ZionRepository;", "getZionRepository", "()Lcom/bitcoin/mwallet/zion/ZionRepository;", "zionRepository$delegate", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: WalletSettingsBuilder.kt */
public final class WalletSettingsBuilder extends ScreenBuilderBase {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(WalletSettingsBuilder.class), "modifyWalletInteractor", "getModifyWalletInteractor()Lcom/bitcoin/mwallet/core/interactors/ModifyWalletInteractor;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(WalletSettingsBuilder.class), "walletInteractor", "getWalletInteractor()Lcom/bitcoin/mwallet/core/interactors/GetWalletInteractor;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(WalletSettingsBuilder.class), "zionRepository", "getZionRepository()Lcom/bitcoin/mwallet/zion/ZionRepository;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(WalletSettingsBuilder.class), "walletRepository", "getWalletRepository()Lcom/bitcoin/mwallet/core/repositories/WalletRepository;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(WalletSettingsBuilder.class), "defaultCurrencyInteractor", "getDefaultCurrencyInteractor()Lcom/bitcoin/mwallet/core/interactors/GetDefaultCurrencyInteractor;"))};
    @NotNull
    private final Lazy defaultCurrencyInteractor$delegate;
    @NotNull
    private final Lazy modifyWalletInteractor$delegate;
    @NotNull
    private final WalletSettingsPresenter presenter;
    @NotNull
    private final WalletSettingsRouter router = new WalletSettingsRouter();
    @NotNull
    private final Lazy walletInteractor$delegate;
    @NotNull
    private final Lazy walletRepository$delegate;
    @NotNull
    private final Lazy zionRepository$delegate;

    @NotNull
    public final GetDefaultCurrencyInteractor getDefaultCurrencyInteractor() {
        Lazy lazy = this.defaultCurrencyInteractor$delegate;
        KProperty kProperty = $$delegatedProperties[4];
        return (GetDefaultCurrencyInteractor) lazy.getValue();
    }

    @NotNull
    public final ModifyWalletInteractor getModifyWalletInteractor() {
        Lazy lazy = this.modifyWalletInteractor$delegate;
        KProperty kProperty = $$delegatedProperties[0];
        return (ModifyWalletInteractor) lazy.getValue();
    }

    @NotNull
    public final GetWalletInteractor getWalletInteractor() {
        Lazy lazy = this.walletInteractor$delegate;
        KProperty kProperty = $$delegatedProperties[1];
        return (GetWalletInteractor) lazy.getValue();
    }

    @NotNull
    public final WalletRepository getWalletRepository() {
        Lazy lazy = this.walletRepository$delegate;
        KProperty kProperty = $$delegatedProperties[3];
        return (WalletRepository) lazy.getValue();
    }

    @NotNull
    public final ZionRepository getZionRepository() {
        Lazy lazy = this.zionRepository$delegate;
        KProperty kProperty = $$delegatedProperties[2];
        return (ZionRepository) lazy.getValue();
    }

    public WalletSettingsBuilder(@NotNull Application application) {
        Intrinsics.checkParameterIsNotNull(application, "application");
        super(application);
        Qualifier qualifier = null;
        Function0 function0 = null;
        this.modifyWalletInteractor$delegate = LazyKt.lazy(new WalletSettingsBuilder$$special$$inlined$inject$1(getKoin().getRootScope(), qualifier, function0));
        this.walletInteractor$delegate = LazyKt.lazy(new WalletSettingsBuilder$$special$$inlined$inject$2(getKoin().getRootScope(), qualifier, function0));
        this.zionRepository$delegate = LazyKt.lazy(new WalletSettingsBuilder$$special$$inlined$inject$3(getKoin().getRootScope(), qualifier, function0));
        this.walletRepository$delegate = LazyKt.lazy(new WalletSettingsBuilder$$special$$inlined$inject$4(getKoin().getRootScope(), qualifier, function0));
        this.defaultCurrencyInteractor$delegate = LazyKt.lazy(new WalletSettingsBuilder$$special$$inlined$inject$5(getKoin().getRootScope(), qualifier, function0));
        WalletSettingsPresenter walletSettingsPresenter = new WalletSettingsPresenter(application, ViewModelKt.getViewModelScope(this), getModifyWalletInteractor(), getWalletInteractor(), getZionRepository(), getWalletRepository(), getDefaultCurrencyInteractor(), getRouter());
        this.presenter = walletSettingsPresenter;
    }

    /* access modifiers changed from: protected */
    @NotNull
    public WalletSettingsRouter getRouter() {
        return this.router;
    }

    @NotNull
    public WalletSettingsPresenter getPresenter() {
        return this.presenter;
    }
}
