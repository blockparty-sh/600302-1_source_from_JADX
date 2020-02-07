package androidx.core.animation;

import android.animation.Animator;
import android.animation.Animator.AnimatorPauseListener;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0007¸\u0006\u0000"}, mo37405d2 = {"androidx/core/animation/AnimatorKt$addPauseListener$listener$1", "Landroid/animation/Animator$AnimatorPauseListener;", "onAnimationPause", "", "animator", "Landroid/animation/Animator;", "onAnimationResume", "core-ktx_release"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: Animator.kt */
public final class AnimatorKt$doOnPause$$inlined$addPauseListener$1 implements AnimatorPauseListener {
    final /* synthetic */ Function1 $onPause;

    public void onAnimationResume(@NotNull Animator animator) {
        Intrinsics.checkParameterIsNotNull(animator, "animator");
    }

    public AnimatorKt$doOnPause$$inlined$addPauseListener$1(Function1 function1) {
        this.$onPause = function1;
    }

    public void onAnimationPause(@NotNull Animator animator) {
        Intrinsics.checkParameterIsNotNull(animator, "animator");
        this.$onPause.invoke(animator);
    }
}