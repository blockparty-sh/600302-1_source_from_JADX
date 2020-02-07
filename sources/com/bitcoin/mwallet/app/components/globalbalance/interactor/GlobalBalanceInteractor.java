package com.bitcoin.mwallet.app.components.globalbalance.interactor;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import com.bitcoin.mwallet.core.interactors.GetBalanceByCoinInteractor;
import com.bitcoin.mwallet.core.interactors.GetBalanceByWalletInteractor;
import com.bitcoin.mwallet.core.interactors.GetDefaultCurrencyInteractor;
import com.bitcoin.mwallet.core.models.wallet.WalletInfoBalance;
import java.util.Currency;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001d\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u00130\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0011¨\u0006\u0016"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/components/globalbalance/interactor/GlobalBalanceInteractor;", "", "getBalanceByCoinInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetBalanceByCoinInteractor;", "getDefaultCurrencyInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetDefaultCurrencyInteractor;", "balanceByWalletInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetBalanceByWalletInteractor;", "(Lcom/bitcoin/mwallet/core/interactors/GetBalanceByCoinInteractor;Lcom/bitcoin/mwallet/core/interactors/GetDefaultCurrencyInteractor;Lcom/bitcoin/mwallet/core/interactors/GetBalanceByWalletInteractor;)V", "currency", "Ljava/util/Currency;", "getCurrency", "()Ljava/util/Currency;", "globalBalance", "Landroidx/lifecycle/LiveData;", "", "getGlobalBalance", "()Landroidx/lifecycle/LiveData;", "globalBalanceFromWallets", "", "Lcom/bitcoin/mwallet/core/models/wallet/WalletInfoBalance;", "getGlobalBalanceFromWallets", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: GlobalBalanceInteractor.kt */
public final class GlobalBalanceInteractor {
    @NotNull
    private final Currency currency;
    @NotNull
    private final LiveData<String> globalBalance;
    @NotNull
    private final LiveData<List<WalletInfoBalance>> globalBalanceFromWallets;

    public GlobalBalanceInteractor(@NotNull GetBalanceByCoinInteractor getBalanceByCoinInteractor, @NotNull GetDefaultCurrencyInteractor getDefaultCurrencyInteractor, @NotNull GetBalanceByWalletInteractor getBalanceByWalletInteractor) {
        Intrinsics.checkParameterIsNotNull(getBalanceByCoinInteractor, "getBalanceByCoinInteractor");
        Intrinsics.checkParameterIsNotNull(getDefaultCurrencyInteractor, "getDefaultCurrencyInteractor");
        Intrinsics.checkParameterIsNotNull(getBalanceByWalletInteractor, "balanceByWalletInteractor");
        LiveData<String> map = Transformations.map(getBalanceByCoinInteractor.balancePerCoin(getDefaultCurrencyInteractor.getDefaultFiatCurrency()), GlobalBalanceInteractor$globalBalance$1.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(map, "Transformations.map(getB…balances.first)\n        }");
        this.globalBalance = map;
        this.currency = getDefaultCurrencyInteractor.getDefaultFiatCurrency();
        this.globalBalanceFromWallets = GetBalanceByWalletInteractor.walletBalances$default(getBalanceByWalletInteractor, false, 1, null);
    }

    @NotNull
    public final LiveData<String> getGlobalBalance() {
        return this.globalBalance;
    }

    @NotNull
    public final Currency getCurrency() {
        return this.currency;
    }

    @NotNull
    public final LiveData<List<WalletInfoBalance>> getGlobalBalanceFromWallets() {
        return this.globalBalanceFromWallets;
    }
}
