package com.bitcoin.mwallet.app.components.missingwalletdialog;

import android.app.Dialog;
import android.view.View;
import android.view.View.OnClickListener;
import androidx.fragment.app.FragmentManager;
import com.bitcoin.mwallet.app.components.walletmanagement.createwallet.view.CreateWalletDialogView;
import kotlin.Metadata;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, mo37405d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: MissingWalletDialogView.kt */
final class MissingWalletDialogView$onCreateView$1 implements OnClickListener {
    final /* synthetic */ MissingWalletDialogView this$0;

    MissingWalletDialogView$onCreateView$1(MissingWalletDialogView missingWalletDialogView) {
        this.this$0 = missingWalletDialogView;
    }

    public final void onClick(View view) {
        FragmentManager fragmentManager = this.this$0.getFragmentManager();
        if (fragmentManager != null) {
            new CreateWalletDialogView(this.this$0.getWalletType().getCoin()).show(fragmentManager, "create_new_wallet");
        }
        Dialog dialog = this.this$0.getDialog();
        if (dialog != null) {
            dialog.dismiss();
        }
    }
}
