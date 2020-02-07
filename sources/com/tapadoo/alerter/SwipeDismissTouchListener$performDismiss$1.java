package com.tapadoo.alerter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.ViewGroup.LayoutParams;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, mo37405d2 = {"com/tapadoo/alerter/SwipeDismissTouchListener$performDismiss$1", "Landroid/animation/AnimatorListenerAdapter;", "onAnimationEnd", "", "animation", "Landroid/animation/Animator;", "alerter_release"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: SwipeDismissTouchListener.kt */
public final class SwipeDismissTouchListener$performDismiss$1 extends AnimatorListenerAdapter {
    final /* synthetic */ LayoutParams $lp;
    final /* synthetic */ int $originalHeight;
    final /* synthetic */ SwipeDismissTouchListener this$0;

    SwipeDismissTouchListener$performDismiss$1(SwipeDismissTouchListener swipeDismissTouchListener, LayoutParams layoutParams, int i) {
        this.this$0 = swipeDismissTouchListener;
        this.$lp = layoutParams;
        this.$originalHeight = i;
    }

    public void onAnimationEnd(@NotNull Animator animator) {
        Intrinsics.checkParameterIsNotNull(animator, "animation");
        this.this$0.mCallbacks.onDismiss(this.this$0.mView);
        this.this$0.mView.setAlpha(1.0f);
        this.this$0.mView.setTranslationX(0.0f);
        this.$lp.height = this.$originalHeight;
        this.this$0.mView.setLayoutParams(this.$lp);
    }
}
