package com.tapadoo.alerter;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.view.ViewGroup.LayoutParams;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, mo37405d2 = {"<anonymous>", "", "valueAnimator", "Landroid/animation/ValueAnimator;", "kotlin.jvm.PlatformType", "onAnimationUpdate"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: SwipeDismissTouchListener.kt */
final class SwipeDismissTouchListener$performDismiss$2 implements AnimatorUpdateListener {
    final /* synthetic */ LayoutParams $lp;
    final /* synthetic */ SwipeDismissTouchListener this$0;

    SwipeDismissTouchListener$performDismiss$2(SwipeDismissTouchListener swipeDismissTouchListener, LayoutParams layoutParams) {
        this.this$0 = swipeDismissTouchListener;
        this.$lp = layoutParams;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        LayoutParams layoutParams = this.$lp;
        Intrinsics.checkExpressionValueIsNotNull(valueAnimator, "valueAnimator");
        Object animatedValue = valueAnimator.getAnimatedValue();
        if (animatedValue != null) {
            layoutParams.height = ((Integer) animatedValue).intValue();
            this.this$0.mView.setLayoutParams(this.$lp);
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Int");
    }
}
