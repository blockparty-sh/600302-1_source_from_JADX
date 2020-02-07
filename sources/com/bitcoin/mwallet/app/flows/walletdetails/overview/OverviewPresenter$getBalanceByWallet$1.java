package com.bitcoin.mwallet.app.flows.walletdetails.overview;

import com.bitcoin.mwallet.core.models.Coin;
import com.bitcoin.mwallet.core.models.exchangerate.FiatExchangeRate;
import com.bitcoin.mwallet.core.models.wallet.WalletInfoBalance;
import com.bitcoin.mwallet.core.models.wallet.WalletInfoSatoshis;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0014\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0005H\n¢\u0006\u0002\b\b"}, mo37405d2 = {"<anonymous>", "Lcom/bitcoin/mwallet/core/models/wallet/WalletInfoBalance;", "wallet", "Lcom/bitcoin/mwallet/core/models/wallet/WalletInfoSatoshis;", "exchangeRates", "", "Lcom/bitcoin/mwallet/core/models/Coin;", "Lcom/bitcoin/mwallet/core/models/exchangerate/FiatExchangeRate;", "invoke"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: OverviewPresenter.kt */
final class OverviewPresenter$getBalanceByWallet$1 extends Lambda implements Function2<WalletInfoSatoshis, Map<Coin, ? extends FiatExchangeRate>, WalletInfoBalance> {
    final /* synthetic */ OverviewPresenter this$0;

    OverviewPresenter$getBalanceByWallet$1(OverviewPresenter overviewPresenter) {
        this.this$0 = overviewPresenter;
        super(2);
    }

    @Nullable
    public final WalletInfoBalance invoke(@Nullable WalletInfoSatoshis walletInfoSatoshis, @Nullable Map<Coin, FiatExchangeRate> map) {
        if (walletInfoSatoshis == null || map == null) {
            return null;
        }
        return walletInfoSatoshis.toWalletInfoBalance(map, this.this$0.defaultCurrencyInteractor.getDefaultFiatCurrency());
    }
}
