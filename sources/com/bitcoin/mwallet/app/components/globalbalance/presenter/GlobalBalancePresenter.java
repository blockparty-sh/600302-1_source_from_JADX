package com.bitcoin.mwallet.app.components.globalbalance.presenter;

import android.content.Context;
import androidx.lifecycle.LiveData;
import com.bitcoin.mwallet.app.components.globalbalance.interactor.GlobalBalanceInteractor;
import com.bitcoin.mwallet.app.viper.PresenterBase;
import com.bitcoin.mwallet.core.interactors.GetDefaultCurrencyInteractor;
import com.bitcoin.mwallet.core.models.wallet.WalletInfoBalance;
import com.bitcoin.mwallet.core.services.AnalyticsService;
import java.util.List;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\u0006\u0010\u001a\u001a\u00020\u001bR\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u001d\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u00150\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0013R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/components/globalbalance/presenter/GlobalBalancePresenter;", "Lcom/bitcoin/mwallet/app/viper/PresenterBase;", "context", "Landroid/content/Context;", "viewModelScope", "Lkotlinx/coroutines/CoroutineScope;", "interactor", "Lcom/bitcoin/mwallet/app/components/globalbalance/interactor/GlobalBalanceInteractor;", "getDefaultCurrencyInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetDefaultCurrencyInteractor;", "analyticsService", "Lcom/bitcoin/mwallet/core/services/AnalyticsService;", "(Landroid/content/Context;Lkotlinx/coroutines/CoroutineScope;Lcom/bitcoin/mwallet/app/components/globalbalance/interactor/GlobalBalanceInteractor;Lcom/bitcoin/mwallet/core/interactors/GetDefaultCurrencyInteractor;Lcom/bitcoin/mwallet/core/services/AnalyticsService;)V", "getAnalyticsService", "()Lcom/bitcoin/mwallet/core/services/AnalyticsService;", "balance", "Landroidx/lifecycle/LiveData;", "", "getBalance", "()Landroidx/lifecycle/LiveData;", "balanceFromWallets", "", "Lcom/bitcoin/mwallet/core/models/wallet/WalletInfoBalance;", "getBalanceFromWallets", "getGetDefaultCurrencyInteractor", "()Lcom/bitcoin/mwallet/core/interactors/GetDefaultCurrencyInteractor;", "addBuyAnalytics", "", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: GlobalBalancePresenter.kt */
public final class GlobalBalancePresenter extends PresenterBase {
    @NotNull
    private final AnalyticsService analyticsService;
    @NotNull
    private final LiveData<String> balance = this.interactor.getGlobalBalance();
    @NotNull
    private final LiveData<List<WalletInfoBalance>> balanceFromWallets = this.interactor.getGlobalBalanceFromWallets();
    @NotNull
    private final GetDefaultCurrencyInteractor getDefaultCurrencyInteractor;
    private final GlobalBalanceInteractor interactor;

    @NotNull
    public final GetDefaultCurrencyInteractor getGetDefaultCurrencyInteractor() {
        return this.getDefaultCurrencyInteractor;
    }

    @NotNull
    public final AnalyticsService getAnalyticsService() {
        return this.analyticsService;
    }

    public GlobalBalancePresenter(@NotNull Context context, @NotNull CoroutineScope coroutineScope, @NotNull GlobalBalanceInteractor globalBalanceInteractor, @NotNull GetDefaultCurrencyInteractor getDefaultCurrencyInteractor2, @NotNull AnalyticsService analyticsService2) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(coroutineScope, "viewModelScope");
        Intrinsics.checkParameterIsNotNull(globalBalanceInteractor, "interactor");
        Intrinsics.checkParameterIsNotNull(getDefaultCurrencyInteractor2, "getDefaultCurrencyInteractor");
        Intrinsics.checkParameterIsNotNull(analyticsService2, "analyticsService");
        this.interactor = globalBalanceInteractor;
        this.getDefaultCurrencyInteractor = getDefaultCurrencyInteractor2;
        this.analyticsService = analyticsService2;
    }

    @NotNull
    public final LiveData<String> getBalance() {
        return this.balance;
    }

    @NotNull
    public final LiveData<List<WalletInfoBalance>> getBalanceFromWallets() {
        return this.balanceFromWallets;
    }

    public final void addBuyAnalytics() {
        String str = "buy_bitcoin_click";
        this.analyticsService.track(str, MapsKt.mapOf(TuplesKt.m309to("screen", "Home"), TuplesKt.m309to("coin", "General")));
    }
}
