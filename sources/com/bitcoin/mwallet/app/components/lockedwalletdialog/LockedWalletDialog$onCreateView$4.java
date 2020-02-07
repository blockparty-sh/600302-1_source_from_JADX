package com.bitcoin.mwallet.app.components.lockedwalletdialog;

import android.app.Dialog;
import android.view.View;
import android.widget.Toast;
import androidx.lifecycle.Observer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, mo37405d2 = {"<anonymous>", "", "result", "Lcom/bitcoin/mwallet/app/components/lockedwalletdialog/UnlockWalletResult;", "kotlin.jvm.PlatformType", "onChanged"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: LockedWalletDialog.kt */
final class LockedWalletDialog$onCreateView$4<T> implements Observer<UnlockWalletResult> {
    final /* synthetic */ View $customView;
    final /* synthetic */ LockedWalletDialog this$0;

    LockedWalletDialog$onCreateView$4(LockedWalletDialog lockedWalletDialog, View view) {
        this.this$0 = lockedWalletDialog;
        this.$customView = view;
    }

    public final void onChanged(UnlockWalletResult unlockWalletResult) {
        Toast.makeText(this.this$0.getContext(), unlockWalletResult.toString(), 0).show();
        if (unlockWalletResult == UnlockWalletResult.SUCCESS) {
            Dialog dialog = this.this$0.getDialog();
            if (dialog != null) {
                dialog.dismiss();
                return;
            }
            return;
        }
        LockedWalletDialog lockedWalletDialog = this.this$0;
        View view = this.$customView;
        Intrinsics.checkExpressionValueIsNotNull(view, "customView");
        lockedWalletDialog.hideLoading(view);
    }
}
