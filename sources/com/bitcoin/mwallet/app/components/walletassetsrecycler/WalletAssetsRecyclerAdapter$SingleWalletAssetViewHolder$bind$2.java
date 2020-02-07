package com.bitcoin.mwallet.app.components.walletassetsrecycler;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import androidx.appcompat.app.AppCompatActivity;
import com.bitcoin.mwallet.WalletDetailsActivity;
import com.bitcoin.mwallet.app.components.lockedmnemonicdialog.LockedMnemonicWalletDialog;
import com.bitcoin.mwallet.app.components.lockedwalletdialog.LockedWalletDialog;
import com.bitcoin.mwallet.app.components.multisigwalletdialog.MultiSigWalletDialog;
import com.bitcoin.mwallet.app.components.walletassetsrecycler.WalletAssetsRecyclerAdapter.SingleWalletAssetViewHolder;
import com.bitcoin.mwallet.core.models.credential.CredentialType;
import com.bitcoin.mwallet.core.models.wallet.C1261Wallet;
import com.bitcoin.mwallet.core.models.wallet.WalletInfoBalance;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, mo37405d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: WalletAssetsRecyclerAdapter.kt */
final class WalletAssetsRecyclerAdapter$SingleWalletAssetViewHolder$bind$2 implements OnClickListener {
    final /* synthetic */ WalletInfoBalance $walletBalance;
    final /* synthetic */ SingleWalletAssetViewHolder this$0;

    WalletAssetsRecyclerAdapter$SingleWalletAssetViewHolder$bind$2(SingleWalletAssetViewHolder singleWalletAssetViewHolder, WalletInfoBalance walletInfoBalance) {
        this.this$0 = singleWalletAssetViewHolder;
        this.$walletBalance = walletInfoBalance;
    }

    public final void onClick(View view) {
        C1261Wallet wallet = this.this$0.getPresenter().getWallet(this.$walletBalance.getWalletId());
        if (wallet == null) {
            Intrinsics.throwNpe();
        }
        String str = "null cannot be cast to non-null type androidx.appcompat.app.AppCompatActivity";
        if (wallet.getCredentialId().getType() == CredentialType.ENCRYPTED_MNEMONIC) {
            LockedWalletDialog lockedWalletDialog = new LockedWalletDialog(this.$walletBalance.getWalletId());
            Context context = this.this$0.getView().getContext();
            if (context != null) {
                lockedWalletDialog.show(((AppCompatActivity) context).getSupportFragmentManager(), "locked_dialog");
                return;
            }
            throw new TypeCastException(str);
        } else if (this.$walletBalance.getMultiSig()) {
            MultiSigWalletDialog multiSigWalletDialog = new MultiSigWalletDialog(this.$walletBalance.getWalletId());
            Context context2 = this.this$0.getView().getContext();
            if (context2 != null) {
                multiSigWalletDialog.show(((AppCompatActivity) context2).getSupportFragmentManager(), "multisig_dialog");
                return;
            }
            throw new TypeCastException(str);
        } else {
            C1261Wallet wallet2 = this.this$0.getPresenter().getWallet(this.$walletBalance.getWalletId());
            if (wallet2 == null) {
                Intrinsics.throwNpe();
            }
            if (wallet2.getCredentialId().getType() == CredentialType.MNEMONIC_AND_PROTECTED) {
                LockedMnemonicWalletDialog lockedMnemonicWalletDialog = new LockedMnemonicWalletDialog(this.$walletBalance.getWalletId());
                Context context3 = this.this$0.getView().getContext();
                if (context3 != null) {
                    lockedMnemonicWalletDialog.show(((AppCompatActivity) context3).getSupportFragmentManager(), "locked_mnemonic_dialog");
                    return;
                }
                throw new TypeCastException(str);
            }
            this.this$0.getPresenter().setTxDetailsAnalytics();
            Intent intent = new Intent(this.this$0.getView().getContext(), WalletDetailsActivity.class);
            intent.putExtra("wallet_id", this.$walletBalance.getWalletId());
            this.this$0.getView().getContext().startActivity(intent);
        }
    }
}
