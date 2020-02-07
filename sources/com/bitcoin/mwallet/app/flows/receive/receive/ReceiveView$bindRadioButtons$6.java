package com.bitcoin.mwallet.app.flows.receive.receive;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import com.bitcoin.mwallet.app.components.missingwalletdialog.MissingWalletDialogView;
import com.bitcoin.mwallet.core.entity.WalletType;
import kotlin.Metadata;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u000e\u0010\u0005\u001a\n \u0004*\u0004\u0018\u00010\u00060\u0006H\n¢\u0006\u0002\b\u0007"}, mo37405d2 = {"<anonymous>", "", "view", "Landroid/view/View;", "kotlin.jvm.PlatformType", "<anonymous parameter 1>", "Landroid/view/MotionEvent;", "onTouch"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: ReceiveView.kt */
final class ReceiveView$bindRadioButtons$6 implements OnTouchListener {
    final /* synthetic */ ReceiveView this$0;

    ReceiveView$bindRadioButtons$6(ReceiveView receiveView) {
        this.this$0 = receiveView;
    }

    public final boolean onTouch(View view, MotionEvent motionEvent) {
        if (!((ReceivePresenter) this.this$0.getPresenter()).checkWalletsExist(WalletType.BCH)) {
            this.this$0.getChildFragmentManager();
            new MissingWalletDialogView(WalletType.BCH).show(this.this$0.getChildFragmentManager(), "missing_bch_wallets");
            return false;
        }
        view.performClick();
        return true;
    }
}
