package com.bitcoin.mwallet.app.flows.walletdetails.deletewallet;

import android.view.View;
import android.widget.Button;
import androidx.lifecycle.Observer;
import com.bitcoin.mwallet.C1018R;
import com.microsoft.appcenter.utils.PrefStorageConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, mo37405d2 = {"<anonymous>", "", "enabled", "", "kotlin.jvm.PlatformType", "onChanged", "(Ljava/lang/Boolean;)V"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: DeleteWalletView.kt */
final class DeleteWalletView$bindObservers$2<T> implements Observer<Boolean> {
    final /* synthetic */ DeleteWalletView this$0;

    DeleteWalletView$bindObservers$2(DeleteWalletView deleteWalletView) {
        this.this$0 = deleteWalletView;
    }

    public final void onChanged(Boolean bool) {
        View view = this.this$0.getView();
        if (view != null) {
            Button button = (Button) view.findViewById(C1018R.C1021id.deleteWalletButton);
            if (button != null) {
                Intrinsics.checkExpressionValueIsNotNull(bool, PrefStorageConstants.KEY_ENABLED);
                button.setEnabled(bool.booleanValue());
            }
        }
    }
}
