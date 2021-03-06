package com.ncorti.slidetoact;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.graphics.drawable.Drawable;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, mo37405d2 = {"<anonymous>", "", "it", "Landroid/animation/ValueAnimator;", "kotlin.jvm.PlatformType", "onAnimationUpdate"}, mo37406k = 3, mo37407mv = {1, 1, 13})
/* compiled from: SlideToActView.kt */
final class SlideToActView$startAnimationComplete$4 implements AnimatorUpdateListener {
    final /* synthetic */ SlideToActView this$0;

    SlideToActView$startAnimationComplete$4(SlideToActView slideToActView) {
        this.this$0 = slideToActView;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        SlideToActView slideToActView = this.this$0;
        slideToActView.mTickMargin = slideToActView.mIconMargin;
        this.this$0.mFlagDrawTick = true;
        Drawable access$getMDrawableTick$p = this.this$0.mDrawableTick;
        Intrinsics.checkExpressionValueIsNotNull(valueAnimator, "it");
        Object animatedValue = valueAnimator.getAnimatedValue();
        if (animatedValue != null) {
            access$getMDrawableTick$p.setAlpha(((Integer) animatedValue).intValue());
            this.this$0.invalidateArea();
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Int");
    }
}
