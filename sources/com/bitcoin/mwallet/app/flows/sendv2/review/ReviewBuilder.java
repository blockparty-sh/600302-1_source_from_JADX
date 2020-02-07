package com.bitcoin.mwallet.app.flows.sendv2.review;

import android.app.Application;
import androidx.lifecycle.ViewModelKt;
import com.bitcoin.mwallet.app.viper.ScreenBuilderBase;
import com.bitcoin.mwallet.core.interactors.CreateTxInteractor;
import com.bitcoin.mwallet.core.interactors.GetBalanceByWalletInteractor;
import com.bitcoin.mwallet.core.interactors.GetCurrentExchangeRateInteractor;
import com.bitcoin.mwallet.core.interactors.GetNetworkFeeInteractor;
import com.bitcoin.mwallet.core.interactors.GetWalletInteractor;
import com.bitcoin.mwallet.core.repositories.SlpRepository;
import com.bitcoin.mwallet.core.repositories.UtxoRepository;
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

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u001b\u0010\u000b\u001a\u00020\f8BX\u0002¢\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\r\u0010\u000eR\u001b\u0010\u0010\u001a\u00020\u00118BX\u0002¢\u0006\f\n\u0004\b\u0014\u0010\n\u001a\u0004\b\u0012\u0010\u0013R\u001b\u0010\u0015\u001a\u00020\u00168BX\u0002¢\u0006\f\n\u0004\b\u0019\u0010\n\u001a\u0004\b\u0017\u0010\u0018R\u0014\u0010\u001a\u001a\u00020\u001bX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0014\u0010\u001e\u001a\u00020\u001fX\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u001b\u0010\"\u001a\u00020#8BX\u0002¢\u0006\f\n\u0004\b&\u0010\n\u001a\u0004\b$\u0010%R\u001b\u0010'\u001a\u00020(8BX\u0002¢\u0006\f\n\u0004\b+\u0010\n\u001a\u0004\b)\u0010*R\u001b\u0010,\u001a\u00020-8BX\u0002¢\u0006\f\n\u0004\b0\u0010\n\u001a\u0004\b.\u0010/R\u001b\u00101\u001a\u0002028BX\u0002¢\u0006\f\n\u0004\b5\u0010\n\u001a\u0004\b3\u00104¨\u00066"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/sendv2/review/ReviewBuilder;", "Lcom/bitcoin/mwallet/app/viper/ScreenBuilderBase;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "createTxInteractor", "Lcom/bitcoin/mwallet/core/interactors/CreateTxInteractor;", "getCreateTxInteractor", "()Lcom/bitcoin/mwallet/core/interactors/CreateTxInteractor;", "createTxInteractor$delegate", "Lkotlin/Lazy;", "getExchangeRateInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetCurrentExchangeRateInteractor;", "getGetExchangeRateInteractor", "()Lcom/bitcoin/mwallet/core/interactors/GetCurrentExchangeRateInteractor;", "getExchangeRateInteractor$delegate", "getNetworkFeeInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetNetworkFeeInteractor;", "getGetNetworkFeeInteractor", "()Lcom/bitcoin/mwallet/core/interactors/GetNetworkFeeInteractor;", "getNetworkFeeInteractor$delegate", "getWalletInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetWalletInteractor;", "getGetWalletInteractor", "()Lcom/bitcoin/mwallet/core/interactors/GetWalletInteractor;", "getWalletInteractor$delegate", "presenter", "Lcom/bitcoin/mwallet/app/flows/sendv2/review/ReviewPresenter;", "getPresenter", "()Lcom/bitcoin/mwallet/app/flows/sendv2/review/ReviewPresenter;", "router", "Lcom/bitcoin/mwallet/app/flows/sendv2/review/ReviewRouter;", "getRouter", "()Lcom/bitcoin/mwallet/app/flows/sendv2/review/ReviewRouter;", "slpRepository", "Lcom/bitcoin/mwallet/core/repositories/SlpRepository;", "getSlpRepository", "()Lcom/bitcoin/mwallet/core/repositories/SlpRepository;", "slpRepository$delegate", "utxoService", "Lcom/bitcoin/mwallet/core/repositories/UtxoRepository;", "getUtxoService", "()Lcom/bitcoin/mwallet/core/repositories/UtxoRepository;", "utxoService$delegate", "walletBalanceInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetBalanceByWalletInteractor;", "getWalletBalanceInteractor", "()Lcom/bitcoin/mwallet/core/interactors/GetBalanceByWalletInteractor;", "walletBalanceInteractor$delegate", "zionRepository", "Lcom/bitcoin/mwallet/zion/ZionRepository;", "getZionRepository", "()Lcom/bitcoin/mwallet/zion/ZionRepository;", "zionRepository$delegate", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: ReviewBuilder.kt */
public final class ReviewBuilder extends ScreenBuilderBase {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(ReviewBuilder.class), "utxoService", "getUtxoService()Lcom/bitcoin/mwallet/core/repositories/UtxoRepository;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(ReviewBuilder.class), "getWalletInteractor", "getGetWalletInteractor()Lcom/bitcoin/mwallet/core/interactors/GetWalletInteractor;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(ReviewBuilder.class), "getExchangeRateInteractor", "getGetExchangeRateInteractor()Lcom/bitcoin/mwallet/core/interactors/GetCurrentExchangeRateInteractor;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(ReviewBuilder.class), "getNetworkFeeInteractor", "getGetNetworkFeeInteractor()Lcom/bitcoin/mwallet/core/interactors/GetNetworkFeeInteractor;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(ReviewBuilder.class), "walletBalanceInteractor", "getWalletBalanceInteractor()Lcom/bitcoin/mwallet/core/interactors/GetBalanceByWalletInteractor;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(ReviewBuilder.class), "slpRepository", "getSlpRepository()Lcom/bitcoin/mwallet/core/repositories/SlpRepository;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(ReviewBuilder.class), "zionRepository", "getZionRepository()Lcom/bitcoin/mwallet/zion/ZionRepository;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(ReviewBuilder.class), "createTxInteractor", "getCreateTxInteractor()Lcom/bitcoin/mwallet/core/interactors/CreateTxInteractor;"))};
    private final Lazy createTxInteractor$delegate;
    private final Lazy getExchangeRateInteractor$delegate;
    private final Lazy getNetworkFeeInteractor$delegate;
    private final Lazy getWalletInteractor$delegate;
    @NotNull
    private final ReviewPresenter presenter;
    @NotNull
    private final ReviewRouter router = new ReviewRouter();
    private final Lazy slpRepository$delegate;
    private final Lazy utxoService$delegate;
    private final Lazy walletBalanceInteractor$delegate;
    private final Lazy zionRepository$delegate;

    private final CreateTxInteractor getCreateTxInteractor() {
        Lazy lazy = this.createTxInteractor$delegate;
        KProperty kProperty = $$delegatedProperties[7];
        return (CreateTxInteractor) lazy.getValue();
    }

    private final GetCurrentExchangeRateInteractor getGetExchangeRateInteractor() {
        Lazy lazy = this.getExchangeRateInteractor$delegate;
        KProperty kProperty = $$delegatedProperties[2];
        return (GetCurrentExchangeRateInteractor) lazy.getValue();
    }

    private final GetNetworkFeeInteractor getGetNetworkFeeInteractor() {
        Lazy lazy = this.getNetworkFeeInteractor$delegate;
        KProperty kProperty = $$delegatedProperties[3];
        return (GetNetworkFeeInteractor) lazy.getValue();
    }

    private final GetWalletInteractor getGetWalletInteractor() {
        Lazy lazy = this.getWalletInteractor$delegate;
        KProperty kProperty = $$delegatedProperties[1];
        return (GetWalletInteractor) lazy.getValue();
    }

    private final SlpRepository getSlpRepository() {
        Lazy lazy = this.slpRepository$delegate;
        KProperty kProperty = $$delegatedProperties[5];
        return (SlpRepository) lazy.getValue();
    }

    private final UtxoRepository getUtxoService() {
        Lazy lazy = this.utxoService$delegate;
        KProperty kProperty = $$delegatedProperties[0];
        return (UtxoRepository) lazy.getValue();
    }

    private final GetBalanceByWalletInteractor getWalletBalanceInteractor() {
        Lazy lazy = this.walletBalanceInteractor$delegate;
        KProperty kProperty = $$delegatedProperties[4];
        return (GetBalanceByWalletInteractor) lazy.getValue();
    }

    private final ZionRepository getZionRepository() {
        Lazy lazy = this.zionRepository$delegate;
        KProperty kProperty = $$delegatedProperties[6];
        return (ZionRepository) lazy.getValue();
    }

    public ReviewBuilder(@NotNull Application application) {
        Intrinsics.checkParameterIsNotNull(application, "application");
        super(application);
        Qualifier qualifier = null;
        Function0 function0 = null;
        this.utxoService$delegate = LazyKt.lazy(new ReviewBuilder$$special$$inlined$inject$1(getKoin().getRootScope(), qualifier, function0));
        this.getWalletInteractor$delegate = LazyKt.lazy(new ReviewBuilder$$special$$inlined$inject$2(getKoin().getRootScope(), qualifier, function0));
        this.getExchangeRateInteractor$delegate = LazyKt.lazy(new ReviewBuilder$$special$$inlined$inject$3(getKoin().getRootScope(), qualifier, function0));
        this.getNetworkFeeInteractor$delegate = LazyKt.lazy(new ReviewBuilder$$special$$inlined$inject$4(getKoin().getRootScope(), qualifier, function0));
        this.walletBalanceInteractor$delegate = LazyKt.lazy(new ReviewBuilder$$special$$inlined$inject$5(getKoin().getRootScope(), qualifier, function0));
        this.slpRepository$delegate = LazyKt.lazy(new ReviewBuilder$$special$$inlined$inject$6(getKoin().getRootScope(), qualifier, function0));
        this.zionRepository$delegate = LazyKt.lazy(new ReviewBuilder$$special$$inlined$inject$7(getKoin().getRootScope(), qualifier, function0));
        this.createTxInteractor$delegate = LazyKt.lazy(new ReviewBuilder$$special$$inlined$inject$8(getKoin().getRootScope(), qualifier, function0));
        ReviewPresenter reviewPresenter = new ReviewPresenter(application, ViewModelKt.getViewModelScope(this), getCreateTxInteractor(), getGetWalletInteractor(), getGetExchangeRateInteractor(), getGetNetworkFeeInteractor(), getWalletBalanceInteractor(), getUtxoService(), getSlpRepository(), getZionRepository(), getRouter());
        this.presenter = reviewPresenter;
    }

    /* access modifiers changed from: protected */
    @NotNull
    public ReviewRouter getRouter() {
        return this.router;
    }

    @NotNull
    public ReviewPresenter getPresenter() {
        return this.presenter;
    }
}
