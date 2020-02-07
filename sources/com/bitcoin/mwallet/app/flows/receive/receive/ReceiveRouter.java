package com.bitcoin.mwallet.app.flows.receive.receive;

import androidx.fragment.app.FragmentManager;
import com.bitcoin.mwallet.app.components.walletselector.WalletSelectorDialogView;
import com.bitcoin.mwallet.app.components.walletselector.WalletSelectorListener;
import com.bitcoin.mwallet.app.viper.RouterBase;
import com.bitcoin.mwallet.core.entity.WalletType;
import com.bitcoin.mwallet.core.models.Coin;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u001e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r¨\u0006\u000e"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/receive/receive/ReceiveRouter;", "Lcom/bitcoin/mwallet/app/viper/RouterBase;", "()V", "toSelectAmount", "", "coin", "Lcom/bitcoin/mwallet/core/models/Coin;", "toWalletSelect", "listener", "Lcom/bitcoin/mwallet/app/components/walletselector/WalletSelectorListener;", "walletType", "Lcom/bitcoin/mwallet/core/entity/WalletType;", "fm", "Landroidx/fragment/app/FragmentManager;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: ReceiveRouter.kt */
public final class ReceiveRouter extends RouterBase {
    public final void toSelectAmount(@NotNull Coin coin) {
        Intrinsics.checkParameterIsNotNull(coin, "coin");
        getNavController().navigate(ReceiveViewDirections.Companion.actionReceiveViewToReceiveAmountSelection(coin));
    }

    public final void toWalletSelect(@NotNull WalletSelectorListener walletSelectorListener, @NotNull WalletType walletType, @NotNull FragmentManager fragmentManager) {
        Intrinsics.checkParameterIsNotNull(walletSelectorListener, CastExtraArgs.LISTENER);
        Intrinsics.checkParameterIsNotNull(walletType, "walletType");
        Intrinsics.checkParameterIsNotNull(fragmentManager, "fm");
        WalletSelectorDialogView walletSelectorDialogView = new WalletSelectorDialogView(walletSelectorListener, walletType, null, false, false, 28, null);
        walletSelectorDialogView.show(fragmentManager, "wallet_select");
    }
}
