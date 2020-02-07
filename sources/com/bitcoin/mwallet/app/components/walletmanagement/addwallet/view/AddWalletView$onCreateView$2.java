package com.bitcoin.mwallet.app.components.walletmanagement.addwallet.view;

import android.view.View;
import android.view.View.OnClickListener;
import androidx.fragment.app.FragmentManager;
import com.bitcoin.mwallet.app.components.walletmanagement.importwallet.view.ImportWalletDialogView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, mo37405d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: AddWalletView.kt */
final class AddWalletView$onCreateView$2 implements OnClickListener {
    final /* synthetic */ AddWalletView this$0;

    AddWalletView$onCreateView$2(AddWalletView addWalletView) {
        this.this$0 = addWalletView;
    }

    public final void onClick(View view) {
        ImportWalletDialogView importWalletDialogView = new ImportWalletDialogView(this.this$0.getCoin());
        FragmentManager fragmentManager = this.this$0.getFragmentManager();
        if (fragmentManager == null) {
            Intrinsics.throwNpe();
        }
        importWalletDialogView.show(fragmentManager, "import_existing_wallet");
        this.this$0.dismiss();
    }
}
