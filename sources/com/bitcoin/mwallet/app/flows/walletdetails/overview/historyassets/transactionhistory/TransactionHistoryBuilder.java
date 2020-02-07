package com.bitcoin.mwallet.app.flows.walletdetails.overview.historyassets.transactionhistory;

import android.app.Application;
import androidx.lifecycle.ViewModelKt;
import com.bitcoin.mwallet.app.viper.ComponentBuilderBase;
import com.bitcoin.mwallet.core.interactors.GetCurrentExchangeRateInteractor;
import com.bitcoin.mwallet.core.interactors.GetDefaultCurrencyInteractor;
import com.bitcoin.mwallet.core.interactors.ModifyWalletInteractor;
import com.bitcoin.mwallet.core.repositories.SlpRepository;
import com.bitcoin.mwallet.core.repositories.TransactionHistoryRepository;
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

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0007\u001a\u00020\b8BX\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u001b\u0010\r\u001a\u00020\u000e8BX\u0002¢\u0006\f\n\u0004\b\u0011\u0010\f\u001a\u0004\b\u000f\u0010\u0010R\u001b\u0010\u0012\u001a\u00020\u00138BX\u0002¢\u0006\f\n\u0004\b\u0016\u0010\f\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0017\u001a\u00020\u0018X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u001b\u0010\u001b\u001a\u00020\u001c8BX\u0002¢\u0006\f\n\u0004\b\u001f\u0010\f\u001a\u0004\b\u001d\u0010\u001eR\u001b\u0010 \u001a\u00020!8BX\u0002¢\u0006\f\n\u0004\b$\u0010\f\u001a\u0004\b\"\u0010#R\u001b\u0010%\u001a\u00020&8BX\u0002¢\u0006\f\n\u0004\b)\u0010\f\u001a\u0004\b'\u0010(¨\u0006*"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/walletdetails/overview/historyassets/transactionhistory/TransactionHistoryBuilder;", "Lcom/bitcoin/mwallet/app/viper/ComponentBuilderBase;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "getCompleteTransactionHistoryInteractor", "Lcom/bitcoin/mwallet/app/flows/walletdetails/overview/historyassets/transactionhistory/GetCompleteTransactionHistoryInteractor;", "getCurrentExchangeRateInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetCurrentExchangeRateInteractor;", "getGetCurrentExchangeRateInteractor", "()Lcom/bitcoin/mwallet/core/interactors/GetCurrentExchangeRateInteractor;", "getCurrentExchangeRateInteractor$delegate", "Lkotlin/Lazy;", "getDefaultCurrencyInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetDefaultCurrencyInteractor;", "getGetDefaultCurrencyInteractor", "()Lcom/bitcoin/mwallet/core/interactors/GetDefaultCurrencyInteractor;", "getDefaultCurrencyInteractor$delegate", "modifyWalletInteractor", "Lcom/bitcoin/mwallet/core/interactors/ModifyWalletInteractor;", "getModifyWalletInteractor", "()Lcom/bitcoin/mwallet/core/interactors/ModifyWalletInteractor;", "modifyWalletInteractor$delegate", "presenter", "Lcom/bitcoin/mwallet/app/flows/walletdetails/overview/historyassets/transactionhistory/TransactionHistoryPresenter;", "getPresenter", "()Lcom/bitcoin/mwallet/app/flows/walletdetails/overview/historyassets/transactionhistory/TransactionHistoryPresenter;", "slpRepository", "Lcom/bitcoin/mwallet/core/repositories/SlpRepository;", "getSlpRepository", "()Lcom/bitcoin/mwallet/core/repositories/SlpRepository;", "slpRepository$delegate", "transactionHistoryRepository", "Lcom/bitcoin/mwallet/core/repositories/TransactionHistoryRepository;", "getTransactionHistoryRepository", "()Lcom/bitcoin/mwallet/core/repositories/TransactionHistoryRepository;", "transactionHistoryRepository$delegate", "walletRepository", "Lcom/bitcoin/mwallet/core/repositories/WalletRepository;", "getWalletRepository", "()Lcom/bitcoin/mwallet/core/repositories/WalletRepository;", "walletRepository$delegate", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: TransactionHistoryBuilder.kt */
public final class TransactionHistoryBuilder extends ComponentBuilderBase {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(TransactionHistoryBuilder.class), "transactionHistoryRepository", "getTransactionHistoryRepository()Lcom/bitcoin/mwallet/core/repositories/TransactionHistoryRepository;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(TransactionHistoryBuilder.class), "getCurrentExchangeRateInteractor", "getGetCurrentExchangeRateInteractor()Lcom/bitcoin/mwallet/core/interactors/GetCurrentExchangeRateInteractor;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(TransactionHistoryBuilder.class), "getDefaultCurrencyInteractor", "getGetDefaultCurrencyInteractor()Lcom/bitcoin/mwallet/core/interactors/GetDefaultCurrencyInteractor;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(TransactionHistoryBuilder.class), "slpRepository", "getSlpRepository()Lcom/bitcoin/mwallet/core/repositories/SlpRepository;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(TransactionHistoryBuilder.class), "modifyWalletInteractor", "getModifyWalletInteractor()Lcom/bitcoin/mwallet/core/interactors/ModifyWalletInteractor;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(TransactionHistoryBuilder.class), "walletRepository", "getWalletRepository()Lcom/bitcoin/mwallet/core/repositories/WalletRepository;"))};
    private final GetCompleteTransactionHistoryInteractor getCompleteTransactionHistoryInteractor = new GetCompleteTransactionHistoryInteractor(getTransactionHistoryRepository());
    private final Lazy getCurrentExchangeRateInteractor$delegate;
    private final Lazy getDefaultCurrencyInteractor$delegate;
    private final Lazy modifyWalletInteractor$delegate;
    @NotNull
    private final TransactionHistoryPresenter presenter;
    private final Lazy slpRepository$delegate;
    private final Lazy transactionHistoryRepository$delegate;
    private final Lazy walletRepository$delegate;

    private final GetCurrentExchangeRateInteractor getGetCurrentExchangeRateInteractor() {
        Lazy lazy = this.getCurrentExchangeRateInteractor$delegate;
        KProperty kProperty = $$delegatedProperties[1];
        return (GetCurrentExchangeRateInteractor) lazy.getValue();
    }

    private final GetDefaultCurrencyInteractor getGetDefaultCurrencyInteractor() {
        Lazy lazy = this.getDefaultCurrencyInteractor$delegate;
        KProperty kProperty = $$delegatedProperties[2];
        return (GetDefaultCurrencyInteractor) lazy.getValue();
    }

    private final ModifyWalletInteractor getModifyWalletInteractor() {
        Lazy lazy = this.modifyWalletInteractor$delegate;
        KProperty kProperty = $$delegatedProperties[4];
        return (ModifyWalletInteractor) lazy.getValue();
    }

    private final SlpRepository getSlpRepository() {
        Lazy lazy = this.slpRepository$delegate;
        KProperty kProperty = $$delegatedProperties[3];
        return (SlpRepository) lazy.getValue();
    }

    private final TransactionHistoryRepository getTransactionHistoryRepository() {
        Lazy lazy = this.transactionHistoryRepository$delegate;
        KProperty kProperty = $$delegatedProperties[0];
        return (TransactionHistoryRepository) lazy.getValue();
    }

    private final WalletRepository getWalletRepository() {
        Lazy lazy = this.walletRepository$delegate;
        KProperty kProperty = $$delegatedProperties[5];
        return (WalletRepository) lazy.getValue();
    }

    public TransactionHistoryBuilder(@NotNull Application application) {
        Intrinsics.checkParameterIsNotNull(application, "application");
        super(application);
        Qualifier qualifier = null;
        Function0 function0 = null;
        this.transactionHistoryRepository$delegate = LazyKt.lazy(new TransactionHistoryBuilder$$special$$inlined$inject$1(getKoin().getRootScope(), qualifier, function0));
        this.getCurrentExchangeRateInteractor$delegate = LazyKt.lazy(new TransactionHistoryBuilder$$special$$inlined$inject$2(getKoin().getRootScope(), qualifier, function0));
        this.getDefaultCurrencyInteractor$delegate = LazyKt.lazy(new TransactionHistoryBuilder$$special$$inlined$inject$3(getKoin().getRootScope(), qualifier, function0));
        this.slpRepository$delegate = LazyKt.lazy(new TransactionHistoryBuilder$$special$$inlined$inject$4(getKoin().getRootScope(), qualifier, function0));
        this.modifyWalletInteractor$delegate = LazyKt.lazy(new TransactionHistoryBuilder$$special$$inlined$inject$5(getKoin().getRootScope(), qualifier, function0));
        this.walletRepository$delegate = LazyKt.lazy(new TransactionHistoryBuilder$$special$$inlined$inject$6(getKoin().getRootScope(), qualifier, function0));
        TransactionHistoryPresenter transactionHistoryPresenter = new TransactionHistoryPresenter(application, ViewModelKt.getViewModelScope(this), getGetCurrentExchangeRateInteractor(), getGetDefaultCurrencyInteractor(), this.getCompleteTransactionHistoryInteractor, getSlpRepository(), getWalletRepository(), getModifyWalletInteractor());
        this.presenter = transactionHistoryPresenter;
    }

    @NotNull
    public TransactionHistoryPresenter getPresenter() {
        return this.presenter;
    }
}
