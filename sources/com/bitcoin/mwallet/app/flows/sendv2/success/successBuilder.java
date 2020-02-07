package com.bitcoin.mwallet.app.flows.sendv2.success;

import android.app.Application;
import androidx.lifecycle.ViewModelKt;
import com.bitcoin.mwallet.app.viper.ScreenBuilderBase;
import com.bitcoin.mwallet.core.interactors.GetCurrentExchangeRateInteractor;
import com.bitcoin.mwallet.core.repositories.NotesRepository;
import com.bitcoin.mwallet.core.repositories.SlpRepository;
import com.bitcoin.mwallet.core.repositories.WalletRepository;
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

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u001b\u0010\u000b\u001a\u00020\f8BX\u0002¢\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\r\u0010\u000eR\u001b\u0010\u0010\u001a\u00020\u00118BX\u0002¢\u0006\f\n\u0004\b\u0014\u0010\n\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0015\u001a\u00020\u0016X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0019\u001a\u00020\u001aX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u001b\u0010\u001d\u001a\u00020\u001e8BX\u0002¢\u0006\f\n\u0004\b!\u0010\n\u001a\u0004\b\u001f\u0010 R\u001b\u0010\"\u001a\u00020#8BX\u0002¢\u0006\f\n\u0004\b&\u0010\n\u001a\u0004\b$\u0010%¨\u0006'"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/sendv2/success/successBuilder;", "Lcom/bitcoin/mwallet/app/viper/ScreenBuilderBase;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "analyticsService", "Lcom/bitcoin/mwallet/core/services/AnalyticsService;", "getAnalyticsService", "()Lcom/bitcoin/mwallet/core/services/AnalyticsService;", "analyticsService$delegate", "Lkotlin/Lazy;", "getCurrentExchangeRateInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetCurrentExchangeRateInteractor;", "getGetCurrentExchangeRateInteractor", "()Lcom/bitcoin/mwallet/core/interactors/GetCurrentExchangeRateInteractor;", "getCurrentExchangeRateInteractor$delegate", "noteRepository", "Lcom/bitcoin/mwallet/core/repositories/NotesRepository;", "getNoteRepository", "()Lcom/bitcoin/mwallet/core/repositories/NotesRepository;", "noteRepository$delegate", "presenter", "Lcom/bitcoin/mwallet/app/flows/sendv2/success/successPresenter;", "getPresenter", "()Lcom/bitcoin/mwallet/app/flows/sendv2/success/successPresenter;", "router", "Lcom/bitcoin/mwallet/app/flows/sendv2/success/successRouter;", "getRouter", "()Lcom/bitcoin/mwallet/app/flows/sendv2/success/successRouter;", "slpRepository", "Lcom/bitcoin/mwallet/core/repositories/SlpRepository;", "getSlpRepository", "()Lcom/bitcoin/mwallet/core/repositories/SlpRepository;", "slpRepository$delegate", "walletRepository", "Lcom/bitcoin/mwallet/core/repositories/WalletRepository;", "getWalletRepository", "()Lcom/bitcoin/mwallet/core/repositories/WalletRepository;", "walletRepository$delegate", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: successBuilder.kt */
public final class successBuilder extends ScreenBuilderBase {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(successBuilder.class), "getCurrentExchangeRateInteractor", "getGetCurrentExchangeRateInteractor()Lcom/bitcoin/mwallet/core/interactors/GetCurrentExchangeRateInteractor;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(successBuilder.class), "noteRepository", "getNoteRepository()Lcom/bitcoin/mwallet/core/repositories/NotesRepository;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(successBuilder.class), "slpRepository", "getSlpRepository()Lcom/bitcoin/mwallet/core/repositories/SlpRepository;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(successBuilder.class), "walletRepository", "getWalletRepository()Lcom/bitcoin/mwallet/core/repositories/WalletRepository;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(successBuilder.class), "analyticsService", "getAnalyticsService()Lcom/bitcoin/mwallet/core/services/AnalyticsService;"))};
    private final Lazy analyticsService$delegate;
    private final Lazy getCurrentExchangeRateInteractor$delegate;
    private final Lazy noteRepository$delegate;
    @NotNull
    private final successPresenter presenter;
    @NotNull
    private final successRouter router = new successRouter();
    private final Lazy slpRepository$delegate;
    private final Lazy walletRepository$delegate;

    private final AnalyticsService getAnalyticsService() {
        Lazy lazy = this.analyticsService$delegate;
        KProperty kProperty = $$delegatedProperties[4];
        return (AnalyticsService) lazy.getValue();
    }

    private final GetCurrentExchangeRateInteractor getGetCurrentExchangeRateInteractor() {
        Lazy lazy = this.getCurrentExchangeRateInteractor$delegate;
        KProperty kProperty = $$delegatedProperties[0];
        return (GetCurrentExchangeRateInteractor) lazy.getValue();
    }

    private final NotesRepository getNoteRepository() {
        Lazy lazy = this.noteRepository$delegate;
        KProperty kProperty = $$delegatedProperties[1];
        return (NotesRepository) lazy.getValue();
    }

    private final SlpRepository getSlpRepository() {
        Lazy lazy = this.slpRepository$delegate;
        KProperty kProperty = $$delegatedProperties[2];
        return (SlpRepository) lazy.getValue();
    }

    private final WalletRepository getWalletRepository() {
        Lazy lazy = this.walletRepository$delegate;
        KProperty kProperty = $$delegatedProperties[3];
        return (WalletRepository) lazy.getValue();
    }

    public successBuilder(@NotNull Application application) {
        Intrinsics.checkParameterIsNotNull(application, "application");
        super(application);
        Qualifier qualifier = null;
        Function0 function0 = null;
        this.getCurrentExchangeRateInteractor$delegate = LazyKt.lazy(new successBuilder$$special$$inlined$inject$1(getKoin().getRootScope(), qualifier, function0));
        this.noteRepository$delegate = LazyKt.lazy(new successBuilder$$special$$inlined$inject$2(getKoin().getRootScope(), qualifier, function0));
        this.slpRepository$delegate = LazyKt.lazy(new successBuilder$$special$$inlined$inject$3(getKoin().getRootScope(), qualifier, function0));
        this.walletRepository$delegate = LazyKt.lazy(new successBuilder$$special$$inlined$inject$4(getKoin().getRootScope(), qualifier, function0));
        this.analyticsService$delegate = LazyKt.lazy(new successBuilder$$special$$inlined$inject$5(getKoin().getRootScope(), qualifier, function0));
        successPresenter successpresenter = new successPresenter(application, ViewModelKt.getViewModelScope(this), getGetCurrentExchangeRateInteractor(), getSlpRepository(), getWalletRepository(), getNoteRepository(), getAnalyticsService(), getRouter());
        this.presenter = successpresenter;
    }

    /* access modifiers changed from: protected */
    @NotNull
    public successRouter getRouter() {
        return this.router;
    }

    @NotNull
    public successPresenter getPresenter() {
        return this.presenter;
    }
}
