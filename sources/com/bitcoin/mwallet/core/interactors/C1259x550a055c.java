package com.bitcoin.mwallet.core.interactors;

import com.bitcoin.mwallet.app.android.LiveDataChangeListener;
import com.bitcoin.mwallet.app.android.SingleSourceMediatorLiveData;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import java.util.Currency;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002H\u0016¨\u0006\u0006"}, mo37405d2 = {"com/bitcoin/mwallet/core/interactors/GetBalanceByWalletInteractor$getBalanceByWallet$defaultFiatCurrency$1", "Lcom/bitcoin/mwallet/app/android/LiveDataChangeListener;", "Ljava/util/Currency;", "onValueSet", "", "value", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* renamed from: com.bitcoin.mwallet.core.interactors.GetBalanceByWalletInteractor$getBalanceByWallet$defaultFiatCurrency$1 */
/* compiled from: GetBalanceByWalletInteractor.kt */
public final class C1259x550a055c implements LiveDataChangeListener<Currency> {
    final /* synthetic */ SingleSourceMediatorLiveData $balance;
    final /* synthetic */ WalletId $walletId;
    final /* synthetic */ GetBalanceByWalletInteractor this$0;

    C1259x550a055c(GetBalanceByWalletInteractor getBalanceByWalletInteractor, SingleSourceMediatorLiveData singleSourceMediatorLiveData, WalletId walletId) {
        this.this$0 = getBalanceByWalletInteractor;
        this.$balance = singleSourceMediatorLiveData;
        this.$walletId = walletId;
    }

    public void onValueSet(@Nullable Currency currency) {
        if (currency != null) {
            this.$balance.replaceSource(this.this$0.getBalanceByWallet(this.$walletId, currency));
        }
    }
}
