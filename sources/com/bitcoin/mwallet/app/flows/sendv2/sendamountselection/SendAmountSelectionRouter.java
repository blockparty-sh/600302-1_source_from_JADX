package com.bitcoin.mwallet.app.flows.sendv2.sendamountselection;

import androidx.fragment.app.FragmentManager;
import com.bitcoin.mwallet.app.components.amountselection.AmountSelectionRouterBase;
import com.bitcoin.mwallet.app.components.walletselector.WalletSelectorDialogView;
import com.bitcoin.mwallet.app.components.walletselector.WalletSelectorListener;
import com.bitcoin.mwallet.core.entity.WalletType;
import com.bitcoin.mwallet.core.models.slp.SlpId;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007J(\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u0010¨\u0006\u0011"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/sendv2/sendamountselection/SendAmountSelectionRouter;", "Lcom/bitcoin/mwallet/app/components/amountselection/AmountSelectionRouterBase;", "()V", "backToQrCode", "", "toReview", "sendWhatModel", "Lcom/bitcoin/mwallet/app/flows/sendv2/sendamountselection/SendWhatModel;", "toWalletSelect", "listener", "Lcom/bitcoin/mwallet/app/components/walletselector/WalletSelectorListener;", "walletType", "Lcom/bitcoin/mwallet/core/entity/WalletType;", "tokenId", "Lcom/bitcoin/mwallet/core/models/slp/SlpId;", "fm", "Landroidx/fragment/app/FragmentManager;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: SendAmountSelectionRouter.kt */
public final class SendAmountSelectionRouter extends AmountSelectionRouterBase {
    public final void toReview(@NotNull SendWhatModel sendWhatModel) {
        Intrinsics.checkParameterIsNotNull(sendWhatModel, "sendWhatModel");
        getNavController().navigate(SendAmountSelectionViewDirections.Companion.actionSendAmountSelectionToSendReview(sendWhatModel));
    }

    public final void backToQrCode() {
        getNavController().popBackStack();
    }

    public final void toWalletSelect(@NotNull WalletSelectorListener walletSelectorListener, @NotNull WalletType walletType, @Nullable SlpId slpId, @NotNull FragmentManager fragmentManager) {
        Intrinsics.checkParameterIsNotNull(walletSelectorListener, CastExtraArgs.LISTENER);
        Intrinsics.checkParameterIsNotNull(walletType, "walletType");
        Intrinsics.checkParameterIsNotNull(fragmentManager, "fm");
        WalletSelectorDialogView walletSelectorDialogView = new WalletSelectorDialogView(walletSelectorListener, walletType, slpId, false, false, 24, null);
        walletSelectorDialogView.show(fragmentManager, "local_wallet_select");
    }
}
