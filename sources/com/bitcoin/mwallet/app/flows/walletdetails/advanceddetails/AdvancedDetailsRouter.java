package com.bitcoin.mwallet.app.flows.walletdetails.advanceddetails;

import com.bitcoin.mwallet.app.viper.RouterBase;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/walletdetails/advanceddetails/AdvancedDetailsRouter;", "Lcom/bitcoin/mwallet/app/viper/RouterBase;", "()V", "toScanForTransactions", "", "walletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: AdvancedDetailsRouter.kt */
public final class AdvancedDetailsRouter extends RouterBase {
    public final void toScanForTransactions(@NotNull WalletId walletId) {
        Intrinsics.checkParameterIsNotNull(walletId, "walletId");
        getNavController().navigate(AdvancedDetailsViewDirections.Companion.actionWalletAdvancedDetailsToWalltScanForTransactions(walletId));
    }
}
