package com.bitcoin.mwallet.app.flows.walletdetails.walletsettings;

import android.app.Activity;
import com.bitcoin.mwallet.app.viper.RouterBase;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\n\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tJ\u0016\u0010\u000b\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\r¨\u0006\u000e"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/walletdetails/walletsettings/WalletSettingsRouter;", "Lcom/bitcoin/mwallet/app/viper/RouterBase;", "()V", "endActivity", "", "activity", "Landroid/app/Activity;", "toAdvancedDetails", "walletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "toDeleteWallet", "toRecoverWallet", "walletTicker", "", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: WalletSettingsRouter.kt */
public final class WalletSettingsRouter extends RouterBase {
    public final void toAdvancedDetails(@NotNull WalletId walletId) {
        Intrinsics.checkParameterIsNotNull(walletId, "walletId");
        getNavController().navigate(WalletSettingsViewDirections.Companion.actionWalletSettingsToWalletAdvancedDetails(walletId));
    }

    public final void toRecoverWallet(@NotNull WalletId walletId, @NotNull String str) {
        Intrinsics.checkParameterIsNotNull(walletId, "walletId");
        Intrinsics.checkParameterIsNotNull(str, "walletTicker");
        getNavController().navigate(WalletSettingsViewDirections.Companion.actionWalletSettingsToWalletRecoveryPhrase(walletId, str));
    }

    public final void toDeleteWallet(@NotNull WalletId walletId) {
        Intrinsics.checkParameterIsNotNull(walletId, "walletId");
        getNavController().navigate(WalletSettingsViewDirections.Companion.actionWalletSettingsToWalletDeleteWallet(walletId));
    }

    public final void endActivity(@NotNull Activity activity) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        activity.finish();
    }
}
