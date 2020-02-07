package com.bitcoin.mwallet.app.flows.walletdetails.overview;

import android.app.Activity;
import android.content.Intent;
import com.bitcoin.mwallet.ReceiveActivity;
import com.bitcoin.mwallet.SendV2Activity;
import com.bitcoin.mwallet.app.viper.RouterBase;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u0016\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u0006J\u0016\u0010\f\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u0006J\u0016\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0010"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/walletdetails/overview/OverviewRouter;", "Lcom/bitcoin/mwallet/app/viper/RouterBase;", "()V", "toBackup", "", "walletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "walletTicker", "", "toReceive", "activity", "Landroid/app/Activity;", "toSend", "toTxDetails", "txid", "toWalletSettings", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: OverviewRouter.kt */
public final class OverviewRouter extends RouterBase {
    public final void toSend(@NotNull Activity activity, @NotNull WalletId walletId) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        Intrinsics.checkParameterIsNotNull(walletId, "walletId");
        Intent intent = new Intent(activity, SendV2Activity.class);
        intent.putExtra("wallet_id", walletId);
        activity.startActivity(intent);
    }

    public final void toReceive(@NotNull Activity activity, @NotNull WalletId walletId) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        Intrinsics.checkParameterIsNotNull(walletId, "walletId");
        Intent intent = new Intent(activity, ReceiveActivity.class);
        intent.putExtra("wallet_id", walletId);
        activity.startActivity(intent);
    }

    public final void toTxDetails(@NotNull String str, @NotNull WalletId walletId) {
        Intrinsics.checkParameterIsNotNull(str, "txid");
        Intrinsics.checkParameterIsNotNull(walletId, "walletId");
        getNavController().navigate(OverviewViewDirections.Companion.actionWalletOverviewToTxDetails(str, walletId));
    }

    public final void toWalletSettings(@NotNull WalletId walletId) {
        Intrinsics.checkParameterIsNotNull(walletId, "walletId");
        getNavController().navigate(OverviewViewDirections.Companion.actionWalletOverviewToWalletSettings(walletId));
    }

    public final void toBackup(@NotNull WalletId walletId, @NotNull String str) {
        Intrinsics.checkParameterIsNotNull(walletId, "walletId");
        Intrinsics.checkParameterIsNotNull(str, "walletTicker");
        getNavController().navigate(OverviewViewDirections.Companion.actionWalletOverviewToWalletRecoveryPhrase(walletId, str));
    }
}
