package com.bitcoin.mwallet.app.components.lockedwalletdialog;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import com.bitcoin.mwallet.C1018R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, mo37405d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: LockedWalletDialog.kt */
final class LockedWalletDialog$onCreateView$2 implements OnClickListener {
    final /* synthetic */ View $customView;
    final /* synthetic */ LockedWalletDialogPresenter $presenter;
    final /* synthetic */ LockedWalletDialog this$0;

    LockedWalletDialog$onCreateView$2(LockedWalletDialog lockedWalletDialog, View view, LockedWalletDialogPresenter lockedWalletDialogPresenter) {
        this.this$0 = lockedWalletDialog;
        this.$customView = view;
        this.$presenter = lockedWalletDialogPresenter;
    }

    public final void onClick(View view) {
        View findViewById = this.$customView.findViewById(C1018R.C1021id.spendLockPasswordField);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "customView.findViewById<…d.spendLockPasswordField)");
        this.$presenter.unlockWallet(this.this$0.getWalletId(), ((EditText) findViewById).getText().toString());
        LockedWalletDialog lockedWalletDialog = this.this$0;
        View view2 = this.$customView;
        Intrinsics.checkExpressionValueIsNotNull(view2, "customView");
        lockedWalletDialog.showLoading(view2);
    }
}
