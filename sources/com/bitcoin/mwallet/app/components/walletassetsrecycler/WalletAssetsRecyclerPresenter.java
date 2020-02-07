package com.bitcoin.mwallet.app.components.walletassetsrecycler;

import android.content.Context;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import com.bitcoin.mwallet.app.android.LiveDataKt;
import com.bitcoin.mwallet.app.viper.PresenterBase;
import com.bitcoin.mwallet.core.interactors.GetBalanceByCoinInteractor;
import com.bitcoin.mwallet.core.interactors.GetBalanceByWalletInteractor;
import com.bitcoin.mwallet.core.interactors.GetDefaultCurrencyInteractor;
import com.bitcoin.mwallet.core.interactors.GetWalletInteractor;
import com.bitcoin.mwallet.core.models.CoinBalance;
import com.bitcoin.mwallet.core.models.wallet.C1261Wallet;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import com.bitcoin.mwallet.core.models.wallet.WalletInfoBalance;
import com.bitcoin.mwallet.core.repositories.SlpRepository;
import com.bitcoin.mwallet.core.services.AnalyticsService;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001BE\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011¢\u0006\u0002\u0010\u0012J$\u0010!\u001a \u0012\u001c\u0012\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020%\u0012\n\u0012\b\u0012\u0004\u0012\u00020'0&0$0#0\"J\u0010\u0010(\u001a\u0004\u0018\u00010)2\u0006\u0010*\u001a\u00020+J\u000e\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020\u001cJ\u0006\u0010/\u001a\u00020-R\u0011\u0010\u0010\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u001c\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 ¨\u00060"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/components/walletassetsrecycler/WalletAssetsRecyclerPresenter;", "Lcom/bitcoin/mwallet/app/viper/PresenterBase;", "context", "Landroid/content/Context;", "viewModelScope", "Lkotlinx/coroutines/CoroutineScope;", "balanceByCoinInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetBalanceByCoinInteractor;", "balanceByWalletInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetBalanceByWalletInteractor;", "currencyInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetDefaultCurrencyInteractor;", "slpRepository", "Lcom/bitcoin/mwallet/core/repositories/SlpRepository;", "getWalletInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetWalletInteractor;", "analyticsService", "Lcom/bitcoin/mwallet/core/services/AnalyticsService;", "(Landroid/content/Context;Lkotlinx/coroutines/CoroutineScope;Lcom/bitcoin/mwallet/core/interactors/GetBalanceByCoinInteractor;Lcom/bitcoin/mwallet/core/interactors/GetBalanceByWalletInteractor;Lcom/bitcoin/mwallet/core/interactors/GetDefaultCurrencyInteractor;Lcom/bitcoin/mwallet/core/repositories/SlpRepository;Lcom/bitcoin/mwallet/core/interactors/GetWalletInteractor;Lcom/bitcoin/mwallet/core/services/AnalyticsService;)V", "getAnalyticsService", "()Lcom/bitcoin/mwallet/core/services/AnalyticsService;", "getCurrencyInteractor", "()Lcom/bitcoin/mwallet/core/interactors/GetDefaultCurrencyInteractor;", "getGetWalletInteractor", "()Lcom/bitcoin/mwallet/core/interactors/GetWalletInteractor;", "getSlpRepository", "()Lcom/bitcoin/mwallet/core/repositories/SlpRepository;", "viewLifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "getViewLifecycleOwner", "()Landroidx/lifecycle/LifecycleOwner;", "setViewLifecycleOwner", "(Landroidx/lifecycle/LifecycleOwner;)V", "getLatestWalletInfoBalances", "Landroidx/lifecycle/LiveData;", "", "Lkotlin/Pair;", "Lcom/bitcoin/mwallet/core/models/CoinBalance;", "", "Lcom/bitcoin/mwallet/core/models/wallet/WalletInfoBalance;", "getWallet", "Lcom/bitcoin/mwallet/core/models/wallet/Wallet;", "walletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "setLifecycleOwner", "", "view", "setTxDetailsAnalytics", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: WalletAssetsRecyclerPresenter.kt */
public final class WalletAssetsRecyclerPresenter extends PresenterBase {
    @NotNull
    private final AnalyticsService analyticsService;
    private final GetBalanceByCoinInteractor balanceByCoinInteractor;
    private final GetBalanceByWalletInteractor balanceByWalletInteractor;
    @NotNull
    private final GetDefaultCurrencyInteractor currencyInteractor;
    @NotNull
    private final GetWalletInteractor getWalletInteractor;
    @NotNull
    private final SlpRepository slpRepository;
    @Nullable
    private LifecycleOwner viewLifecycleOwner;

    @NotNull
    public final GetDefaultCurrencyInteractor getCurrencyInteractor() {
        return this.currencyInteractor;
    }

    @NotNull
    public final SlpRepository getSlpRepository() {
        return this.slpRepository;
    }

    @NotNull
    public final GetWalletInteractor getGetWalletInteractor() {
        return this.getWalletInteractor;
    }

    @NotNull
    public final AnalyticsService getAnalyticsService() {
        return this.analyticsService;
    }

    public WalletAssetsRecyclerPresenter(@NotNull Context context, @NotNull CoroutineScope coroutineScope, @NotNull GetBalanceByCoinInteractor getBalanceByCoinInteractor, @NotNull GetBalanceByWalletInteractor getBalanceByWalletInteractor, @NotNull GetDefaultCurrencyInteractor getDefaultCurrencyInteractor, @NotNull SlpRepository slpRepository2, @NotNull GetWalletInteractor getWalletInteractor2, @NotNull AnalyticsService analyticsService2) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(coroutineScope, "viewModelScope");
        Intrinsics.checkParameterIsNotNull(getBalanceByCoinInteractor, "balanceByCoinInteractor");
        Intrinsics.checkParameterIsNotNull(getBalanceByWalletInteractor, "balanceByWalletInteractor");
        Intrinsics.checkParameterIsNotNull(getDefaultCurrencyInteractor, "currencyInteractor");
        Intrinsics.checkParameterIsNotNull(slpRepository2, "slpRepository");
        Intrinsics.checkParameterIsNotNull(getWalletInteractor2, "getWalletInteractor");
        Intrinsics.checkParameterIsNotNull(analyticsService2, "analyticsService");
        this.balanceByCoinInteractor = getBalanceByCoinInteractor;
        this.balanceByWalletInteractor = getBalanceByWalletInteractor;
        this.currencyInteractor = getDefaultCurrencyInteractor;
        this.slpRepository = slpRepository2;
        this.getWalletInteractor = getWalletInteractor2;
        this.analyticsService = analyticsService2;
    }

    @Nullable
    public final LifecycleOwner getViewLifecycleOwner() {
        return this.viewLifecycleOwner;
    }

    public final void setViewLifecycleOwner(@Nullable LifecycleOwner lifecycleOwner) {
        this.viewLifecycleOwner = lifecycleOwner;
    }

    public final void setLifecycleOwner(@NotNull LifecycleOwner lifecycleOwner) {
        Intrinsics.checkParameterIsNotNull(lifecycleOwner, "view");
        this.viewLifecycleOwner = lifecycleOwner;
    }

    @Nullable
    public final C1261Wallet getWallet(@NotNull WalletId walletId) {
        Intrinsics.checkParameterIsNotNull(walletId, "walletId");
        return (C1261Wallet) BuildersKt__BuildersKt.runBlocking$default(null, new WalletAssetsRecyclerPresenter$getWallet$1(this, walletId, null), 1, null);
    }

    public final void setTxDetailsAnalytics() {
        this.analyticsService.track("wallet_details_open", MapsKt.emptyMap());
    }

    @NotNull
    public final LiveData<List<Pair<CoinBalance, List<WalletInfoBalance>>>> getLatestWalletInfoBalances() {
        return LiveDataKt.combineLatestIgnoreNull(this.balanceByCoinInteractor.balancePerCoin(this.currencyInteractor.getDefaultFiatCurrency()), this.balanceByWalletInteractor.walletBalances(false), WalletAssetsRecyclerPresenter$getLatestWalletInfoBalances$1.INSTANCE);
    }
}
