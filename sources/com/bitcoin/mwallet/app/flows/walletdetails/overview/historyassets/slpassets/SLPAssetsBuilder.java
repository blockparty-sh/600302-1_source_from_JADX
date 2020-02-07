package com.bitcoin.mwallet.app.flows.walletdetails.overview.historyassets.slpassets;

import android.app.Application;
import androidx.lifecycle.ViewModelKt;
import com.bitcoin.mwallet.app.viper.ComponentBuilderBase;
import com.bitcoin.mwallet.core.interactors.DiscoverContentInteractor;
import com.bitcoin.mwallet.core.interactors.ModifyWalletInteractor;
import com.bitcoin.mwallet.core.interactors.VerifiedTokenInteractor;
import com.bitcoin.mwallet.core.repositories.SlpRepository;
import com.bitcoin.mwallet.core.repositories.WalletRepository;
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

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001b\u0010\u0005\u001a\u00020\u00068FX\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u001b\u0010\u000b\u001a\u00020\f8FX\u0002¢\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u0010\u001a\u00020\u0011X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u001b\u0010\u0014\u001a\u00020\u00158FX\u0002¢\u0006\f\n\u0004\b\u0018\u0010\n\u001a\u0004\b\u0016\u0010\u0017R\u001b\u0010\u0019\u001a\u00020\u001a8FX\u0002¢\u0006\f\n\u0004\b\u001d\u0010\n\u001a\u0004\b\u001b\u0010\u001cR\u001b\u0010\u001e\u001a\u00020\u001f8FX\u0002¢\u0006\f\n\u0004\b\"\u0010\n\u001a\u0004\b \u0010!¨\u0006#"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/walletdetails/overview/historyassets/slpassets/SLPAssetsBuilder;", "Lcom/bitcoin/mwallet/app/viper/ComponentBuilderBase;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "discoverContentInteractor", "Lcom/bitcoin/mwallet/core/interactors/DiscoverContentInteractor;", "getDiscoverContentInteractor", "()Lcom/bitcoin/mwallet/core/interactors/DiscoverContentInteractor;", "discoverContentInteractor$delegate", "Lkotlin/Lazy;", "modifyWalletInteractor", "Lcom/bitcoin/mwallet/core/interactors/ModifyWalletInteractor;", "getModifyWalletInteractor", "()Lcom/bitcoin/mwallet/core/interactors/ModifyWalletInteractor;", "modifyWalletInteractor$delegate", "presenter", "Lcom/bitcoin/mwallet/app/flows/walletdetails/overview/historyassets/slpassets/SLPAssetsPresenter;", "getPresenter", "()Lcom/bitcoin/mwallet/app/flows/walletdetails/overview/historyassets/slpassets/SLPAssetsPresenter;", "slpRepository", "Lcom/bitcoin/mwallet/core/repositories/SlpRepository;", "getSlpRepository", "()Lcom/bitcoin/mwallet/core/repositories/SlpRepository;", "slpRepository$delegate", "verifiedTokenInteractor", "Lcom/bitcoin/mwallet/core/interactors/VerifiedTokenInteractor;", "getVerifiedTokenInteractor", "()Lcom/bitcoin/mwallet/core/interactors/VerifiedTokenInteractor;", "verifiedTokenInteractor$delegate", "walletRepo", "Lcom/bitcoin/mwallet/core/repositories/WalletRepository;", "getWalletRepo", "()Lcom/bitcoin/mwallet/core/repositories/WalletRepository;", "walletRepo$delegate", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: SLPAssetsBuilder.kt */
public final class SLPAssetsBuilder extends ComponentBuilderBase {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(SLPAssetsBuilder.class), "slpRepository", "getSlpRepository()Lcom/bitcoin/mwallet/core/repositories/SlpRepository;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(SLPAssetsBuilder.class), "modifyWalletInteractor", "getModifyWalletInteractor()Lcom/bitcoin/mwallet/core/interactors/ModifyWalletInteractor;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(SLPAssetsBuilder.class), "discoverContentInteractor", "getDiscoverContentInteractor()Lcom/bitcoin/mwallet/core/interactors/DiscoverContentInteractor;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(SLPAssetsBuilder.class), "walletRepo", "getWalletRepo()Lcom/bitcoin/mwallet/core/repositories/WalletRepository;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(SLPAssetsBuilder.class), "verifiedTokenInteractor", "getVerifiedTokenInteractor()Lcom/bitcoin/mwallet/core/interactors/VerifiedTokenInteractor;"))};
    @NotNull
    private final Lazy discoverContentInteractor$delegate;
    @NotNull
    private final Lazy modifyWalletInteractor$delegate;
    @NotNull
    private final SLPAssetsPresenter presenter;
    @NotNull
    private final Lazy slpRepository$delegate;
    @NotNull
    private final Lazy verifiedTokenInteractor$delegate;
    @NotNull
    private final Lazy walletRepo$delegate;

    @NotNull
    public final DiscoverContentInteractor getDiscoverContentInteractor() {
        Lazy lazy = this.discoverContentInteractor$delegate;
        KProperty kProperty = $$delegatedProperties[2];
        return (DiscoverContentInteractor) lazy.getValue();
    }

    @NotNull
    public final ModifyWalletInteractor getModifyWalletInteractor() {
        Lazy lazy = this.modifyWalletInteractor$delegate;
        KProperty kProperty = $$delegatedProperties[1];
        return (ModifyWalletInteractor) lazy.getValue();
    }

    @NotNull
    public final SlpRepository getSlpRepository() {
        Lazy lazy = this.slpRepository$delegate;
        KProperty kProperty = $$delegatedProperties[0];
        return (SlpRepository) lazy.getValue();
    }

    @NotNull
    public final VerifiedTokenInteractor getVerifiedTokenInteractor() {
        Lazy lazy = this.verifiedTokenInteractor$delegate;
        KProperty kProperty = $$delegatedProperties[4];
        return (VerifiedTokenInteractor) lazy.getValue();
    }

    @NotNull
    public final WalletRepository getWalletRepo() {
        Lazy lazy = this.walletRepo$delegate;
        KProperty kProperty = $$delegatedProperties[3];
        return (WalletRepository) lazy.getValue();
    }

    public SLPAssetsBuilder(@NotNull Application application) {
        Intrinsics.checkParameterIsNotNull(application, "application");
        super(application);
        Qualifier qualifier = null;
        Function0 function0 = null;
        this.slpRepository$delegate = LazyKt.lazy(new SLPAssetsBuilder$$special$$inlined$inject$1(getKoin().getRootScope(), qualifier, function0));
        this.modifyWalletInteractor$delegate = LazyKt.lazy(new SLPAssetsBuilder$$special$$inlined$inject$2(getKoin().getRootScope(), qualifier, function0));
        this.discoverContentInteractor$delegate = LazyKt.lazy(new SLPAssetsBuilder$$special$$inlined$inject$3(getKoin().getRootScope(), qualifier, function0));
        this.walletRepo$delegate = LazyKt.lazy(new SLPAssetsBuilder$$special$$inlined$inject$4(getKoin().getRootScope(), qualifier, function0));
        this.verifiedTokenInteractor$delegate = LazyKt.lazy(new SLPAssetsBuilder$$special$$inlined$inject$5(getKoin().getRootScope(), qualifier, function0));
        SLPAssetsPresenter sLPAssetsPresenter = new SLPAssetsPresenter(application, ViewModelKt.getViewModelScope(this), getSlpRepository(), getDiscoverContentInteractor(), getModifyWalletInteractor(), getWalletRepo(), getVerifiedTokenInteractor());
        this.presenter = sLPAssetsPresenter;
    }

    @NotNull
    public SLPAssetsPresenter getPresenter() {
        return this.presenter;
    }
}
