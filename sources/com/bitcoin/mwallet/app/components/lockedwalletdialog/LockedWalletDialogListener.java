package com.bitcoin.mwallet.app.components.lockedwalletdialog;

import com.bitcoin.mwallet.core.models.wallet.WalletId;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\t"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/components/lockedwalletdialog/LockedWalletDialogListener;", "", "onUnlock", "", "walletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "passcode", "", "onWalletRemove", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: LockedWalletDialogListener.kt */
public interface LockedWalletDialogListener {
    void onUnlock(@NotNull WalletId walletId, @NotNull String str);

    void onWalletRemove(@NotNull WalletId walletId);
}
