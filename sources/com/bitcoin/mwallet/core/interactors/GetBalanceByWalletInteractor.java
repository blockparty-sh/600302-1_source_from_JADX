package com.bitcoin.mwallet.core.interactors;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import com.bitcoin.mwallet.app.android.LiveDataKt;
import com.bitcoin.mwallet.app.android.SingleSourceMediatorLiveData;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import com.bitcoin.mwallet.core.models.wallet.WalletInfoBalance;
import com.bitcoin.mwallet.core.repositories.WalletRepository;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import java.util.Currency;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\rJ\u001c\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fJ$\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u00110\n2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u001c\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u00110\n2\b\b\u0002\u0010\u0012\u001a\u00020\u0013R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/interactors/GetBalanceByWalletInteractor;", "", "walletRepository", "Lcom/bitcoin/mwallet/core/repositories/WalletRepository;", "getCurrentExchangeRateInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetCurrentExchangeRateInteractor;", "getDefaultCurrencyInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetDefaultCurrencyInteractor;", "(Lcom/bitcoin/mwallet/core/repositories/WalletRepository;Lcom/bitcoin/mwallet/core/interactors/GetCurrentExchangeRateInteractor;Lcom/bitcoin/mwallet/core/interactors/GetDefaultCurrencyInteractor;)V", "getBalanceByWallet", "Landroidx/lifecycle/LiveData;", "Lcom/bitcoin/mwallet/core/models/wallet/WalletInfoBalance;", "walletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "currency", "Ljava/util/Currency;", "walletBalances", "", "filterEncryptedAndMultiSig", "", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: GetBalanceByWalletInteractor.kt */
public final class GetBalanceByWalletInteractor {
    private final GetCurrentExchangeRateInteractor getCurrentExchangeRateInteractor;
    private final GetDefaultCurrencyInteractor getDefaultCurrencyInteractor;
    private final WalletRepository walletRepository;

    public GetBalanceByWalletInteractor(@NotNull WalletRepository walletRepository2, @NotNull GetCurrentExchangeRateInteractor getCurrentExchangeRateInteractor2, @NotNull GetDefaultCurrencyInteractor getDefaultCurrencyInteractor2) {
        Intrinsics.checkParameterIsNotNull(walletRepository2, "walletRepository");
        Intrinsics.checkParameterIsNotNull(getCurrentExchangeRateInteractor2, "getCurrentExchangeRateInteractor");
        Intrinsics.checkParameterIsNotNull(getDefaultCurrencyInteractor2, "getDefaultCurrencyInteractor");
        this.walletRepository = walletRepository2;
        this.getCurrentExchangeRateInteractor = getCurrentExchangeRateInteractor2;
        this.getDefaultCurrencyInteractor = getDefaultCurrencyInteractor2;
    }

    @NotNull
    public static /* synthetic */ LiveData walletBalances$default(GetBalanceByWalletInteractor getBalanceByWalletInteractor, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        return getBalanceByWalletInteractor.walletBalances(z);
    }

    @NotNull
    public final LiveData<List<WalletInfoBalance>> walletBalances(boolean z) {
        LiveData<List<WalletInfoBalance>> switchMap = Transformations.switchMap(this.getDefaultCurrencyInteractor.defaultFiatCurrency(new GetBalanceByWalletInteractor$walletBalances$1()), new GetBalanceByWalletInteractor$walletBalances$2(this, z));
        Intrinsics.checkExpressionValueIsNotNull(switchMap, "Transformations.switchMa…)\n            }\n        }");
        return switchMap;
    }

    /* access modifiers changed from: private */
    public final LiveData<List<WalletInfoBalance>> walletBalances(Currency currency, boolean z) {
        return LiveDataKt.combineLatest(this.walletRepository.getWalletInfoSatoshis(), this.getCurrentExchangeRateInteractor.getCurrentExchangeRate(currency), new GetBalanceByWalletInteractor$walletBalances$3(currency, z));
    }

    @NotNull
    public final LiveData<WalletInfoBalance> getBalanceByWallet(@NotNull WalletId walletId, @NotNull Currency currency) {
        Intrinsics.checkParameterIsNotNull(walletId, "walletId");
        Intrinsics.checkParameterIsNotNull(currency, Param.CURRENCY);
        return LiveDataKt.combineLatest(this.walletRepository.walletInfoSatoshis(walletId), this.getCurrentExchangeRateInteractor.getCurrentExchangeRate(currency), new GetBalanceByWalletInteractor$getBalanceByWallet$1(currency));
    }

    @NotNull
    public final LiveData<WalletInfoBalance> getBalanceByWallet(@NotNull WalletId walletId) {
        Intrinsics.checkParameterIsNotNull(walletId, "walletId");
        SingleSourceMediatorLiveData singleSourceMediatorLiveData = new SingleSourceMediatorLiveData();
        singleSourceMediatorLiveData.addSource(this.getDefaultCurrencyInteractor.defaultFiatCurrency(new C1259x550a055c(this, singleSourceMediatorLiveData, walletId)), GetBalanceByWalletInteractor$getBalanceByWallet$2.INSTANCE);
        return singleSourceMediatorLiveData;
    }
}
