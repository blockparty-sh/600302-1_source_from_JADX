package com.bitcoin.mwallet.app.flows.walletdetails.txdetails;

import android.app.Application;
import androidx.lifecycle.ViewModelKt;
import com.bitcoin.mwallet.app.viper.ScreenBuilderBase;
import com.bitcoin.mwallet.core.interactors.GetCurrentExchangeRateInteractor;
import com.bitcoin.mwallet.core.interactors.GetDefaultCurrencyInteractor;
import com.bitcoin.mwallet.core.interactors.GetWalletInteractor;
import com.bitcoin.mwallet.core.interactors.VerifiedTokenInteractor;
import com.bitcoin.mwallet.core.repositories.NotesRepository;
import com.bitcoin.mwallet.core.repositories.SlpRepository;
import com.bitcoin.mwallet.core.repositories.TransactionHistoryRepository;
import com.bitcoin.mwallet.core.repositories.VerifiedTokenRepository;
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

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001b\u0010\u0005\u001a\u00020\u00068FX\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u001b\u0010\u000b\u001a\u00020\f8FX\u0002¢\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\r\u0010\u000eR\u001b\u0010\u0010\u001a\u00020\u00118FX\u0002¢\u0006\f\n\u0004\b\u0014\u0010\n\u001a\u0004\b\u0012\u0010\u0013R\u001b\u0010\u0015\u001a\u00020\u00168FX\u0002¢\u0006\f\n\u0004\b\u0019\u0010\n\u001a\u0004\b\u0017\u0010\u0018R\u0014\u0010\u001a\u001a\u00020\u001bX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0014\u0010\u001e\u001a\u00020\u001fX\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u001b\u0010\"\u001a\u00020#8FX\u0002¢\u0006\f\n\u0004\b&\u0010\n\u001a\u0004\b$\u0010%R\u001b\u0010'\u001a\u00020(8FX\u0002¢\u0006\f\n\u0004\b+\u0010\n\u001a\u0004\b)\u0010*R\u001b\u0010,\u001a\u00020-8FX\u0002¢\u0006\f\n\u0004\b0\u0010\n\u001a\u0004\b.\u0010/R\u001b\u00101\u001a\u0002028FX\u0002¢\u0006\f\n\u0004\b5\u0010\n\u001a\u0004\b3\u00104R\u001b\u00106\u001a\u0002078FX\u0002¢\u0006\f\n\u0004\b:\u0010\n\u001a\u0004\b8\u00109¨\u0006;"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/walletdetails/txdetails/TxDetailsBuilder;", "Lcom/bitcoin/mwallet/app/viper/ScreenBuilderBase;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "analyticsService", "Lcom/bitcoin/mwallet/core/services/AnalyticsService;", "getAnalyticsService", "()Lcom/bitcoin/mwallet/core/services/AnalyticsService;", "analyticsService$delegate", "Lkotlin/Lazy;", "defaultCurrencyInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetDefaultCurrencyInteractor;", "getDefaultCurrencyInteractor", "()Lcom/bitcoin/mwallet/core/interactors/GetDefaultCurrencyInteractor;", "defaultCurrencyInteractor$delegate", "exchangeRateInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetCurrentExchangeRateInteractor;", "getExchangeRateInteractor", "()Lcom/bitcoin/mwallet/core/interactors/GetCurrentExchangeRateInteractor;", "exchangeRateInteractor$delegate", "noteRepository", "Lcom/bitcoin/mwallet/core/repositories/NotesRepository;", "getNoteRepository", "()Lcom/bitcoin/mwallet/core/repositories/NotesRepository;", "noteRepository$delegate", "presenter", "Lcom/bitcoin/mwallet/app/flows/walletdetails/txdetails/TxDetailsPresenter;", "getPresenter", "()Lcom/bitcoin/mwallet/app/flows/walletdetails/txdetails/TxDetailsPresenter;", "router", "Lcom/bitcoin/mwallet/app/flows/walletdetails/txdetails/TxDetailsRouter;", "getRouter", "()Lcom/bitcoin/mwallet/app/flows/walletdetails/txdetails/TxDetailsRouter;", "slpRepository", "Lcom/bitcoin/mwallet/core/repositories/SlpRepository;", "getSlpRepository", "()Lcom/bitcoin/mwallet/core/repositories/SlpRepository;", "slpRepository$delegate", "txRepo", "Lcom/bitcoin/mwallet/core/repositories/TransactionHistoryRepository;", "getTxRepo", "()Lcom/bitcoin/mwallet/core/repositories/TransactionHistoryRepository;", "txRepo$delegate", "verifiedTokenInteractor", "Lcom/bitcoin/mwallet/core/interactors/VerifiedTokenInteractor;", "getVerifiedTokenInteractor", "()Lcom/bitcoin/mwallet/core/interactors/VerifiedTokenInteractor;", "verifiedTokenInteractor$delegate", "verifiedTokenRepository", "Lcom/bitcoin/mwallet/core/repositories/VerifiedTokenRepository;", "getVerifiedTokenRepository", "()Lcom/bitcoin/mwallet/core/repositories/VerifiedTokenRepository;", "verifiedTokenRepository$delegate", "walletInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetWalletInteractor;", "getWalletInteractor", "()Lcom/bitcoin/mwallet/core/interactors/GetWalletInteractor;", "walletInteractor$delegate", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: TxDetailsBuilder.kt */
public final class TxDetailsBuilder extends ScreenBuilderBase {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(TxDetailsBuilder.class), "txRepo", "getTxRepo()Lcom/bitcoin/mwallet/core/repositories/TransactionHistoryRepository;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(TxDetailsBuilder.class), "walletInteractor", "getWalletInteractor()Lcom/bitcoin/mwallet/core/interactors/GetWalletInteractor;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(TxDetailsBuilder.class), "defaultCurrencyInteractor", "getDefaultCurrencyInteractor()Lcom/bitcoin/mwallet/core/interactors/GetDefaultCurrencyInteractor;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(TxDetailsBuilder.class), "exchangeRateInteractor", "getExchangeRateInteractor()Lcom/bitcoin/mwallet/core/interactors/GetCurrentExchangeRateInteractor;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(TxDetailsBuilder.class), "noteRepository", "getNoteRepository()Lcom/bitcoin/mwallet/core/repositories/NotesRepository;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(TxDetailsBuilder.class), "slpRepository", "getSlpRepository()Lcom/bitcoin/mwallet/core/repositories/SlpRepository;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(TxDetailsBuilder.class), "verifiedTokenRepository", "getVerifiedTokenRepository()Lcom/bitcoin/mwallet/core/repositories/VerifiedTokenRepository;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(TxDetailsBuilder.class), "analyticsService", "getAnalyticsService()Lcom/bitcoin/mwallet/core/services/AnalyticsService;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(TxDetailsBuilder.class), "verifiedTokenInteractor", "getVerifiedTokenInteractor()Lcom/bitcoin/mwallet/core/interactors/VerifiedTokenInteractor;"))};
    @NotNull
    private final Lazy analyticsService$delegate;
    @NotNull
    private final Lazy defaultCurrencyInteractor$delegate;
    @NotNull
    private final Lazy exchangeRateInteractor$delegate;
    @NotNull
    private final Lazy noteRepository$delegate;
    @NotNull
    private final TxDetailsPresenter presenter;
    @NotNull
    private final TxDetailsRouter router = new TxDetailsRouter();
    @NotNull
    private final Lazy slpRepository$delegate;
    @NotNull
    private final Lazy txRepo$delegate;
    @NotNull
    private final Lazy verifiedTokenInteractor$delegate;
    @NotNull
    private final Lazy verifiedTokenRepository$delegate;
    @NotNull
    private final Lazy walletInteractor$delegate;

    @NotNull
    public final AnalyticsService getAnalyticsService() {
        Lazy lazy = this.analyticsService$delegate;
        KProperty kProperty = $$delegatedProperties[7];
        return (AnalyticsService) lazy.getValue();
    }

    @NotNull
    public final GetDefaultCurrencyInteractor getDefaultCurrencyInteractor() {
        Lazy lazy = this.defaultCurrencyInteractor$delegate;
        KProperty kProperty = $$delegatedProperties[2];
        return (GetDefaultCurrencyInteractor) lazy.getValue();
    }

    @NotNull
    public final GetCurrentExchangeRateInteractor getExchangeRateInteractor() {
        Lazy lazy = this.exchangeRateInteractor$delegate;
        KProperty kProperty = $$delegatedProperties[3];
        return (GetCurrentExchangeRateInteractor) lazy.getValue();
    }

    @NotNull
    public final NotesRepository getNoteRepository() {
        Lazy lazy = this.noteRepository$delegate;
        KProperty kProperty = $$delegatedProperties[4];
        return (NotesRepository) lazy.getValue();
    }

    @NotNull
    public final SlpRepository getSlpRepository() {
        Lazy lazy = this.slpRepository$delegate;
        KProperty kProperty = $$delegatedProperties[5];
        return (SlpRepository) lazy.getValue();
    }

    @NotNull
    public final TransactionHistoryRepository getTxRepo() {
        Lazy lazy = this.txRepo$delegate;
        KProperty kProperty = $$delegatedProperties[0];
        return (TransactionHistoryRepository) lazy.getValue();
    }

    @NotNull
    public final VerifiedTokenInteractor getVerifiedTokenInteractor() {
        Lazy lazy = this.verifiedTokenInteractor$delegate;
        KProperty kProperty = $$delegatedProperties[8];
        return (VerifiedTokenInteractor) lazy.getValue();
    }

    @NotNull
    public final VerifiedTokenRepository getVerifiedTokenRepository() {
        Lazy lazy = this.verifiedTokenRepository$delegate;
        KProperty kProperty = $$delegatedProperties[6];
        return (VerifiedTokenRepository) lazy.getValue();
    }

    @NotNull
    public final GetWalletInteractor getWalletInteractor() {
        Lazy lazy = this.walletInteractor$delegate;
        KProperty kProperty = $$delegatedProperties[1];
        return (GetWalletInteractor) lazy.getValue();
    }

    public TxDetailsBuilder(@NotNull Application application) {
        Intrinsics.checkParameterIsNotNull(application, "application");
        super(application);
        Qualifier qualifier = null;
        Function0 function0 = null;
        this.txRepo$delegate = LazyKt.lazy(new TxDetailsBuilder$$special$$inlined$inject$1(getKoin().getRootScope(), qualifier, function0));
        this.walletInteractor$delegate = LazyKt.lazy(new TxDetailsBuilder$$special$$inlined$inject$2(getKoin().getRootScope(), qualifier, function0));
        this.defaultCurrencyInteractor$delegate = LazyKt.lazy(new TxDetailsBuilder$$special$$inlined$inject$3(getKoin().getRootScope(), qualifier, function0));
        this.exchangeRateInteractor$delegate = LazyKt.lazy(new TxDetailsBuilder$$special$$inlined$inject$4(getKoin().getRootScope(), qualifier, function0));
        this.noteRepository$delegate = LazyKt.lazy(new TxDetailsBuilder$$special$$inlined$inject$5(getKoin().getRootScope(), qualifier, function0));
        this.slpRepository$delegate = LazyKt.lazy(new TxDetailsBuilder$$special$$inlined$inject$6(getKoin().getRootScope(), qualifier, function0));
        this.verifiedTokenRepository$delegate = LazyKt.lazy(new TxDetailsBuilder$$special$$inlined$inject$7(getKoin().getRootScope(), qualifier, function0));
        this.analyticsService$delegate = LazyKt.lazy(new TxDetailsBuilder$$special$$inlined$inject$8(getKoin().getRootScope(), qualifier, function0));
        this.verifiedTokenInteractor$delegate = LazyKt.lazy(new TxDetailsBuilder$$special$$inlined$inject$9(getKoin().getRootScope(), qualifier, function0));
        TxDetailsPresenter txDetailsPresenter = new TxDetailsPresenter(application, ViewModelKt.getViewModelScope(this), getTxRepo(), getWalletInteractor(), getDefaultCurrencyInteractor(), getExchangeRateInteractor(), getNoteRepository(), getSlpRepository(), getVerifiedTokenRepository(), getAnalyticsService(), getVerifiedTokenInteractor(), getRouter());
        this.presenter = txDetailsPresenter;
    }

    /* access modifiers changed from: protected */
    @NotNull
    public TxDetailsRouter getRouter() {
        return this.router;
    }

    @NotNull
    public TxDetailsPresenter getPresenter() {
        return this.presenter;
    }
}
