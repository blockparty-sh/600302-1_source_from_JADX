package androidx.core.animation;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\t"}, mo37405d2 = {"androidx/core/animation/AnimatorKt$addListener$listener$1", "Landroid/animation/Animator$AnimatorListener;", "onAnimationCancel", "", "animator", "Landroid/animation/Animator;", "onAnimationEnd", "onAnimationRepeat", "onAnimationStart", "core-ktx_release"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: Animator.kt */
public final class AnimatorKt$addListener$listener$1 implements AnimatorListener {
    final /* synthetic */ Function1 $onCancel;
    final /* synthetic */ Function1 $onEnd;
    final /* synthetic */ Function1 $onRepeat;
    final /* synthetic */ Function1 $onStart;

    public AnimatorKt$addListener$listener$1(Function1 function1, Function1 function12, Function1 function13, Function1 function14) {
        this.$onRepeat = function1;
        this.$onEnd = function12;
        this.$onCancel = function13;
        this.$onStart = function14;
    }

    public void onAnimationRepeat(@NotNull Animator animator) {
        Intrinsics.checkParameterIsNotNull(animator, "animator");
        this.$onRepeat.invoke(animator);
    }

    public void onAnimationEnd(@NotNull Animator animator) {
        Intrinsics.checkParameterIsNotNull(animator, "animator");
        this.$onEnd.invoke(animator);
    }

    public void onAnimationCancel(@NotNull Animator animator) {
        Intrinsics.checkParameterIsNotNull(animator, "animator");
        this.$onCancel.invoke(animator);
    }

    public void onAnimationStart(@NotNull Animator animator) {
        Intrinsics.checkParameterIsNotNull(animator, "animator");
        this.$onStart.invoke(animator);
    }
}
