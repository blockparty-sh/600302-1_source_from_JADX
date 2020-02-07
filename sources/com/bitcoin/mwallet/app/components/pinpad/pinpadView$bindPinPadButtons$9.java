package com.bitcoin.mwallet.app.components.pinpad;

import android.view.View;
import android.view.View.OnClickListener;
import kotlin.Metadata;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, mo37405d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: pinpadView.kt */
final class pinpadView$bindPinPadButtons$9 implements OnClickListener {
    final /* synthetic */ pinpadView this$0;

    pinpadView$bindPinPadButtons$9(pinpadView pinpadview) {
        this.this$0 = pinpadview;
    }

    public final void onClick(View view) {
        pinpadView.access$getPresenter$p(this.this$0).digitTapped("8");
    }
}
