package com.bitcoin.mwallet.app.components.walletselector;

import android.app.Application;
import androidx.lifecycle.ViewModelKt;
import com.bitcoin.mwallet.app.viper.ComponentBuilderBase;
import com.bitcoin.mwallet.core.interactors.GetBalanceByCoinInteractor;
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

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u000b\u001a\u00020\fX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001b\u0010\u000f\u001a\u00020\u00108FX\u0002¢\u0006\f\n\u0004\b\u0013\u0010\n\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0014"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/components/walletselector/WalletSelectorBuilder;", "Lcom/bitcoin/mwallet/app/viper/ComponentBuilderBase;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "getBalanceByCoinInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetBalanceByCoinInteractor;", "getGetBalanceByCoinInteractor", "()Lcom/bitcoin/mwallet/core/interactors/GetBalanceByCoinInteractor;", "getBalanceByCoinInteractor$delegate", "Lkotlin/Lazy;", "presenter", "Lcom/bitcoin/mwallet/app/components/walletselector/WalletSelectorPresenter;", "getPresenter", "()Lcom/bitcoin/mwallet/app/components/walletselector/WalletSelectorPresenter;", "slpRepository", "Lcom/bitcoin/mwallet/core/repositories/SlpRepository;", "getSlpRepository", "()Lcom/bitcoin/mwallet/core/repositories/SlpRepository;", "slpRepository$delegate", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: WalletSelectorBuilder.kt */
public final class WalletSelectorBuilder extends ComponentBuilderBase {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(WalletSelectorBuilder.class), "getBalanceByCoinInteractor", "getGetBalanceByCoinInteractor()Lcom/bitcoin/mwallet/core/interactors/GetBalanceByCoinInteractor;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(WalletSelectorBuilder.class), "slpRepository", "getSlpRepository()Lcom/bitcoin/mwallet/core/repositories/SlpRepository;"))};
    private final Lazy getBalanceByCoinInteractor$delegate;
    @NotNull
    private final WalletSelectorPresenter presenter;
    @NotNull
    private final Lazy slpRepository$delegate;

    private final GetBalanceByCoinInteractor getGetBalanceByCoinInteractor() {
        Lazy lazy = this.getBalanceByCoinInteractor$delegate;
        KProperty kProperty = $$delegatedProperties[0];
        return (GetBalanceByCoinInteractor) lazy.getValue();
    }

    @NotNull
    public final SlpRepository getSlpRepository() {
        Lazy lazy = this.slpRepository$delegate;
        KProperty kProperty = $$delegatedProperties[1];
        return (SlpRepository) lazy.getValue();
    }

    public WalletSelectorBuilder(@NotNull Application application) {
        Intrinsics.checkParameterIsNotNull(application, "application");
        super(application);
        Qualifier qualifier = null;
        Function0 function0 = null;
        this.getBalanceByCoinInteractor$delegate = LazyKt.lazy(new WalletSelectorBuilder$$special$$inlined$inject$1(getKoin().getRootScope(), qualifier, function0));
        this.slpRepository$delegate = LazyKt.lazy(new WalletSelectorBuilder$$special$$inlined$inject$2(getKoin().getRootScope(), qualifier, function0));
        this.presenter = new WalletSelectorPresenter(application, ViewModelKt.getViewModelScope(this), getGetBalanceByCoinInteractor(), getSlpRepository());
    }

    @NotNull
    public WalletSelectorPresenter getPresenter() {
        return this.presenter;
    }
}
