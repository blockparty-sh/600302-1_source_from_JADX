package com.bitcoin.mwallet.app.flows.receive.receive;

import android.view.View;
import android.view.View.OnClickListener;
import androidx.fragment.app.FragmentManager;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, mo37405d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: ReceiveView.kt */
final class ReceiveView$bindActions$9 implements OnClickListener {
    final /* synthetic */ ReceiveView this$0;

    ReceiveView$bindActions$9(ReceiveView receiveView) {
        this.this$0 = receiveView;
    }

    public final void onClick(View view) {
        ReceivePresenter receivePresenter = (ReceivePresenter) this.this$0.getPresenter();
        FragmentManager childFragmentManager = this.this$0.getChildFragmentManager();
        Intrinsics.checkExpressionValueIsNotNull(childFragmentManager, "childFragmentManager");
        receivePresenter.toSelectWallet(childFragmentManager);
    }
}
