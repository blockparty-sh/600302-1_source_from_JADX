package com.bitcoin.mwallet.app.flows.sendv2.review;

import android.view.View;
import android.view.View.OnClickListener;
import kotlin.Metadata;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, mo37405d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: ReviewView.kt */
final class ReviewView$bindInitialArgs$1 implements OnClickListener {
    final /* synthetic */ ReviewView this$0;

    ReviewView$bindInitialArgs$1(ReviewView reviewView) {
        this.this$0 = reviewView;
    }

    public final void onClick(View view) {
        ((ReviewPresenter) this.this$0.getPresenter()).onSend();
    }
}
