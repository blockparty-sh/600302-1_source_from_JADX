package com.bitcoin.mwallet.app.flows.sendv2.review;

import androidx.lifecycle.Observer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, mo37405d2 = {"<anonymous>", "", "needsBiometric", "", "kotlin.jvm.PlatformType", "onChanged", "(Ljava/lang/Boolean;)V"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: ReviewView.kt */
final class ReviewView$bindObservers$6<T> implements Observer<Boolean> {
    final /* synthetic */ ReviewView this$0;

    ReviewView$bindObservers$6(ReviewView reviewView) {
        this.this$0 = reviewView;
    }

    public final void onChanged(Boolean bool) {
        Intrinsics.checkExpressionValueIsNotNull(bool, "needsBiometric");
        if (bool.booleanValue()) {
            this.this$0.showBiometricPrompt();
        }
    }
}