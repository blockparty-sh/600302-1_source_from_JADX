package com.bitcoin.mwallet.app.components.walletselector;

import com.bitcoin.mwallet.core.entity.WalletType;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\b"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/components/walletselector/WalletSelectorListener;", "", "onWalletSelected", "", "walletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "walletType", "Lcom/bitcoin/mwallet/core/entity/WalletType;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: WalletSelectorListener.kt */
public interface WalletSelectorListener {
    void onWalletSelected(@NotNull WalletId walletId, @NotNull WalletType walletType);
}
