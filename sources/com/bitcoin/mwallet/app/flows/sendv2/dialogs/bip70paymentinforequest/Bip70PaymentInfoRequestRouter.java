package com.bitcoin.mwallet.app.flows.sendv2.dialogs.bip70paymentinforequest;

import androidx.fragment.app.FragmentManager;
import com.bitcoin.mwallet.app.components.walletselector.WalletSelectorDialogView;
import com.bitcoin.mwallet.app.components.walletselector.WalletSelectorListener;
import com.bitcoin.mwallet.app.viper.RouterBase;
import com.bitcoin.mwallet.core.entity.WalletType;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J \u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\n¨\u0006\u000b"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/bip70paymentinforequest/Bip70PaymentInfoRequestRouter;", "Lcom/bitcoin/mwallet/app/viper/RouterBase;", "()V", "toWalletSelect", "", "listener", "Lcom/bitcoin/mwallet/app/components/walletselector/WalletSelectorListener;", "fm", "Landroidx/fragment/app/FragmentManager;", "walletType", "Lcom/bitcoin/mwallet/core/entity/WalletType;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: Bip70PaymentInfoRequestRouter.kt */
public final class Bip70PaymentInfoRequestRouter extends RouterBase {
    public final void toWalletSelect(@NotNull WalletSelectorListener walletSelectorListener, @NotNull FragmentManager fragmentManager, @Nullable WalletType walletType) {
        Intrinsics.checkParameterIsNotNull(walletSelectorListener, CastExtraArgs.LISTENER);
        Intrinsics.checkParameterIsNotNull(fragmentManager, "fm");
        WalletSelectorDialogView walletSelectorDialogView = new WalletSelectorDialogView(walletSelectorListener, walletType, null, false, false, 28, null);
        walletSelectorDialogView.show(fragmentManager, "wallet_select");
    }
}
