package com.ncorti.slidetoact;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import com.ncorti.slidetoact.SlideToActView.OnSlideResetListener;
import com.ncorti.slidetoact.SlideToActView.OnSlideToActAnimationEventListener;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J\u0012\u0010\u0006\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J\u0012\u0010\u0007\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J\u0012\u0010\b\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016¨\u0006\t"}, mo37405d2 = {"com/ncorti/slidetoact/SlideToActView$startAnimationReset$6", "Landroid/animation/Animator$AnimatorListener;", "onAnimationCancel", "", "p0", "Landroid/animation/Animator;", "onAnimationEnd", "onAnimationRepeat", "onAnimationStart", "slidetoact_release"}, mo37406k = 1, mo37407mv = {1, 1, 13})
/* compiled from: SlideToActView.kt */
public final class SlideToActView$startAnimationReset$6 implements AnimatorListener {
    final /* synthetic */ SlideToActView this$0;

    public void onAnimationCancel(@Nullable Animator animator) {
    }

    public void onAnimationRepeat(@Nullable Animator animator) {
    }

    SlideToActView$startAnimationReset$6(SlideToActView slideToActView) {
        this.this$0 = slideToActView;
    }

    public void onAnimationStart(@Nullable Animator animator) {
        OnSlideToActAnimationEventListener onSlideToActAnimationEventListener = this.this$0.getOnSlideToActAnimationEventListener();
        if (onSlideToActAnimationEventListener != null) {
            onSlideToActAnimationEventListener.onSlideResetAnimationStarted(this.this$0);
        }
    }

    public void onAnimationEnd(@Nullable Animator animator) {
        this.this$0.setEnabled(true);
        this.this$0.stopTickAnimation();
        OnSlideToActAnimationEventListener onSlideToActAnimationEventListener = this.this$0.getOnSlideToActAnimationEventListener();
        if (onSlideToActAnimationEventListener != null) {
            onSlideToActAnimationEventListener.onSlideResetAnimationEnded(this.this$0);
        }
        OnSlideResetListener onSlideResetListener = this.this$0.getOnSlideResetListener();
        if (onSlideResetListener != null) {
            onSlideResetListener.onSlideReset(this.this$0);
        }
    }
}
