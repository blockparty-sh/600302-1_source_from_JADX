package com.bitcoin.mwallet.app.flows.sendv2.selectaddress;

import androidx.fragment.app.FragmentManager;
import com.bitcoin.bitcoink.p008tx.TxId;
import com.bitcoin.mwallet.app.components.walletselector.WalletSelectorDialogView;
import com.bitcoin.mwallet.app.components.walletselector.WalletSelectorListener;
import com.bitcoin.mwallet.app.flows.sendv2.dialogs.bip70paymentinforequest.Bip70PaymentInfoRequestView;
import com.bitcoin.mwallet.app.flows.sendv2.dialogs.bip70paymentinforequest.OnPaymentSuccessListener;
import com.bitcoin.mwallet.app.flows.sendv2.dialogs.selectcontact.SelectContactListener;
import com.bitcoin.mwallet.app.flows.sendv2.dialogs.selectcontact.SelectContactView;
import com.bitcoin.mwallet.app.flows.sendv2.dialogs.selectsendingasset.OnSendingAssetSelectedListener;
import com.bitcoin.mwallet.app.flows.sendv2.dialogs.selectsendingasset.SelectSendingAssetView;
import com.bitcoin.mwallet.app.flows.sendv2.sendamountselection.SendWhatModel;
import com.bitcoin.mwallet.app.viper.RouterBase;
import com.bitcoin.mwallet.core.entity.WalletType;
import com.bitcoin.mwallet.core.models.BitcoinUriContent;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J(\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fJ\u0018\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u00062\b\u0010\u000b\u001a\u0004\u0018\u00010\fJ\u0016\u0010\u000f\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u00102\u0006\u0010\u0007\u001a\u00020\bJ(\u0010\u0011\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u00122\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\u000b\u001a\u0004\u0018\u00010\fJ\u0016\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017J(\u0010\u0018\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u00192\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u001c\u001a\u00020\u001d¨\u0006\u001e"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/sendv2/selectaddress/SelectAddressRouter;", "Lcom/bitcoin/mwallet/app/viper/RouterBase;", "()V", "toBip70Payment", "", "bitcoinUriContent", "Lcom/bitcoin/mwallet/core/models/BitcoinUriContent;", "fm", "Landroidx/fragment/app/FragmentManager;", "listener", "Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/bip70paymentinforequest/OnPaymentSuccessListener;", "preferredWalletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "toSelectAmount", "uriContent", "toSelectContact", "Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/selectcontact/SelectContactListener;", "toSelectSendingAsset", "Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/selectsendingasset/OnSendingAssetSelectedListener;", "toSendSuccess", "txId", "Lcom/bitcoin/bitcoink/tx/TxId;", "sendWhatModel", "Lcom/bitcoin/mwallet/app/flows/sendv2/sendamountselection/SendWhatModel;", "toWalletSelect", "Lcom/bitcoin/mwallet/app/components/walletselector/WalletSelectorListener;", "walletType", "Lcom/bitcoin/mwallet/core/entity/WalletType;", "restrictive", "", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: SelectAddressRouter.kt */
public final class SelectAddressRouter extends RouterBase {
    public final void toSelectAmount(@NotNull BitcoinUriContent bitcoinUriContent, @Nullable WalletId walletId) {
        Intrinsics.checkParameterIsNotNull(bitcoinUriContent, "uriContent");
        getNavController().navigate(SelectAddressViewDirections.Companion.actionSendAddressToSendAmountSelection(bitcoinUriContent, walletId));
    }

    public final void toSelectSendingAsset(@NotNull OnSendingAssetSelectedListener onSendingAssetSelectedListener, @NotNull BitcoinUriContent bitcoinUriContent, @NotNull FragmentManager fragmentManager, @Nullable WalletId walletId) {
        Intrinsics.checkParameterIsNotNull(onSendingAssetSelectedListener, CastExtraArgs.LISTENER);
        Intrinsics.checkParameterIsNotNull(bitcoinUriContent, "bitcoinUriContent");
        Intrinsics.checkParameterIsNotNull(fragmentManager, "fm");
        new SelectSendingAssetView(onSendingAssetSelectedListener, bitcoinUriContent, walletId).show(fragmentManager, "sending_asset_select");
    }

    public final void toSelectContact(@NotNull SelectContactListener selectContactListener, @NotNull FragmentManager fragmentManager) {
        Intrinsics.checkParameterIsNotNull(selectContactListener, CastExtraArgs.LISTENER);
        Intrinsics.checkParameterIsNotNull(fragmentManager, "fm");
        new SelectContactView(selectContactListener).show(fragmentManager, "currency_select");
    }

    public final void toWalletSelect(@NotNull WalletSelectorListener walletSelectorListener, @NotNull FragmentManager fragmentManager, @Nullable WalletType walletType, boolean z) {
        Intrinsics.checkParameterIsNotNull(walletSelectorListener, CastExtraArgs.LISTENER);
        Intrinsics.checkParameterIsNotNull(fragmentManager, "fm");
        WalletSelectorDialogView walletSelectorDialogView = new WalletSelectorDialogView(walletSelectorListener, walletType, null, z, true, 4, null);
        walletSelectorDialogView.show(fragmentManager, "local_wallet_select");
    }

    public final void toBip70Payment(@NotNull BitcoinUriContent bitcoinUriContent, @NotNull FragmentManager fragmentManager, @NotNull OnPaymentSuccessListener onPaymentSuccessListener, @Nullable WalletId walletId) {
        Intrinsics.checkParameterIsNotNull(bitcoinUriContent, "bitcoinUriContent");
        Intrinsics.checkParameterIsNotNull(fragmentManager, "fm");
        Intrinsics.checkParameterIsNotNull(onPaymentSuccessListener, CastExtraArgs.LISTENER);
        new Bip70PaymentInfoRequestView(bitcoinUriContent, onPaymentSuccessListener, walletId).show(fragmentManager, "bip70_payment");
    }

    public final void toSendSuccess(@NotNull TxId txId, @NotNull SendWhatModel sendWhatModel) {
        Intrinsics.checkParameterIsNotNull(txId, "txId");
        Intrinsics.checkParameterIsNotNull(sendWhatModel, "sendWhatModel");
        getNavController().navigate(SelectAddressViewDirections.Companion.actionSelectAddressToSendSuccess(txId, sendWhatModel));
    }
}
