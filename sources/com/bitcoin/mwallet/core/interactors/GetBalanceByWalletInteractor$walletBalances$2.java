package com.bitcoin.mwallet.core.interactors;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.bitcoin.mwallet.core.models.wallet.WalletInfoBalance;
import java.util.Currency;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import timber.log.Timber;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u00012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\n¢\u0006\u0002\b\u0006"}, mo37405d2 = {"<anonymous>", "Landroidx/lifecycle/LiveData;", "", "Lcom/bitcoin/mwallet/core/models/wallet/WalletInfoBalance;", "currency", "Ljava/util/Currency;", "apply"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: GetBalanceByWalletInteractor.kt */
final class GetBalanceByWalletInteractor$walletBalances$2<I, O> implements Function<X, LiveData<Y>> {
    final /* synthetic */ boolean $filterEncryptedAndMultiSig;
    final /* synthetic */ GetBalanceByWalletInteractor this$0;

    GetBalanceByWalletInteractor$walletBalances$2(GetBalanceByWalletInteractor getBalanceByWalletInteractor, boolean z) {
        this.this$0 = getBalanceByWalletInteractor;
        this.$filterEncryptedAndMultiSig = z;
    }

    @NotNull
    public final LiveData<List<WalletInfoBalance>> apply(@Nullable Currency currency) {
        StringBuilder sb = new StringBuilder();
        sb.append("Currency for walletBalances: ");
        sb.append(currency != null ? currency.getCurrencyCode() : null);
        Timber.m426d(sb.toString(), new Object[0]);
        if (currency != null) {
            return this.this$0.walletBalances(currency, this.$filterEncryptedAndMultiSig);
        }
        return new MutableLiveData<>(CollectionsKt.emptyList());
    }
}
