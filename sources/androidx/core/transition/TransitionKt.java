package androidx.core.transition;

import android.transition.Transition;
import android.transition.Transition.TransitionListener;
import androidx.annotation.RequiresApi;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u000b\u001aÆ\u0001\u0010\u0000\u001a\u00020\u0001*\u00020\u00022#\b\u0006\u0010\u0003\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u00042#\b\u0006\u0010\t\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u00042#\b\u0006\u0010\n\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u00042#\b\u0006\u0010\u000b\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u00042#\b\u0006\u0010\f\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0004H\b\u001a2\u0010\r\u001a\u00020\u0001*\u00020\u00022#\b\u0004\u0010\u000e\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0004H\b\u001a2\u0010\u000f\u001a\u00020\u0001*\u00020\u00022#\b\u0004\u0010\u000e\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0004H\b\u001a2\u0010\u0010\u001a\u00020\u0001*\u00020\u00022#\b\u0004\u0010\u000e\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0004H\b\u001a2\u0010\u0011\u001a\u00020\u0001*\u00020\u00022#\b\u0004\u0010\u000e\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0004H\b\u001a2\u0010\u0012\u001a\u00020\u0001*\u00020\u00022#\b\u0004\u0010\u000e\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0004H\b¨\u0006\u0013"}, mo37405d2 = {"addListener", "Landroid/transition/Transition$TransitionListener;", "Landroid/transition/Transition;", "onEnd", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "transition", "", "onStart", "onCancel", "onResume", "onPause", "doOnCancel", "action", "doOnEnd", "doOnPause", "doOnResume", "doOnStart", "core-ktx_release"}, mo37406k = 2, mo37407mv = {1, 1, 15})
/* compiled from: Transition.kt */
public final class TransitionKt {
    @RequiresApi(19)
    @NotNull
    public static /* synthetic */ TransitionListener addListener$default(Transition transition, Function1 function1, Function1 function12, Function1 function13, Function1 function14, Function1 function15, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = TransitionKt$addListener$1.INSTANCE;
        }
        if ((i & 2) != 0) {
            function12 = TransitionKt$addListener$2.INSTANCE;
        }
        Function1 function16 = function12;
        if ((i & 4) != 0) {
            function13 = TransitionKt$addListener$3.INSTANCE;
        }
        Function1 function17 = function13;
        if ((i & 8) != 0) {
            function14 = TransitionKt$addListener$4.INSTANCE;
        }
        if ((i & 16) != 0) {
            function15 = TransitionKt$addListener$5.INSTANCE;
        }
        Intrinsics.checkParameterIsNotNull(transition, "$this$addListener");
        Intrinsics.checkParameterIsNotNull(function1, "onEnd");
        Intrinsics.checkParameterIsNotNull(function16, "onStart");
        Intrinsics.checkParameterIsNotNull(function17, "onCancel");
        Intrinsics.checkParameterIsNotNull(function14, "onResume");
        Intrinsics.checkParameterIsNotNull(function15, "onPause");
        TransitionKt$addListener$listener$1 transitionKt$addListener$listener$1 = new TransitionKt$addListener$listener$1(function1, function14, function15, function17, function16);
        TransitionListener transitionListener = transitionKt$addListener$listener$1;
        transition.addListener(transitionListener);
        return transitionListener;
    }

    @RequiresApi(19)
    @NotNull
    public static final TransitionListener addListener(@NotNull Transition transition, @NotNull Function1<? super Transition, Unit> function1, @NotNull Function1<? super Transition, Unit> function12, @NotNull Function1<? super Transition, Unit> function13, @NotNull Function1<? super Transition, Unit> function14, @NotNull Function1<? super Transition, Unit> function15) {
        Intrinsics.checkParameterIsNotNull(transition, "$this$addListener");
        Intrinsics.checkParameterIsNotNull(function1, "onEnd");
        Intrinsics.checkParameterIsNotNull(function12, "onStart");
        Intrinsics.checkParameterIsNotNull(function13, "onCancel");
        Intrinsics.checkParameterIsNotNull(function14, "onResume");
        Intrinsics.checkParameterIsNotNull(function15, "onPause");
        TransitionKt$addListener$listener$1 transitionKt$addListener$listener$1 = new TransitionKt$addListener$listener$1(function1, function14, function15, function13, function12);
        TransitionListener transitionListener = transitionKt$addListener$listener$1;
        transition.addListener(transitionListener);
        return transitionListener;
    }

    @RequiresApi(19)
    @NotNull
    public static final TransitionListener doOnEnd(@NotNull Transition transition, @NotNull Function1<? super Transition, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(transition, "$this$doOnEnd");
        Intrinsics.checkParameterIsNotNull(function1, "action");
        TransitionListener transitionKt$doOnEnd$$inlined$addListener$1 = new TransitionKt$doOnEnd$$inlined$addListener$1(function1);
        transition.addListener(transitionKt$doOnEnd$$inlined$addListener$1);
        return transitionKt$doOnEnd$$inlined$addListener$1;
    }

    @RequiresApi(19)
    @NotNull
    public static final TransitionListener doOnStart(@NotNull Transition transition, @NotNull Function1<? super Transition, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(transition, "$this$doOnStart");
        Intrinsics.checkParameterIsNotNull(function1, "action");
        TransitionListener transitionKt$doOnStart$$inlined$addListener$1 = new TransitionKt$doOnStart$$inlined$addListener$1(function1);
        transition.addListener(transitionKt$doOnStart$$inlined$addListener$1);
        return transitionKt$doOnStart$$inlined$addListener$1;
    }

    @RequiresApi(19)
    @NotNull
    public static final TransitionListener doOnCancel(@NotNull Transition transition, @NotNull Function1<? super Transition, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(transition, "$this$doOnCancel");
        Intrinsics.checkParameterIsNotNull(function1, "action");
        TransitionListener transitionKt$doOnCancel$$inlined$addListener$1 = new TransitionKt$doOnCancel$$inlined$addListener$1(function1);
        transition.addListener(transitionKt$doOnCancel$$inlined$addListener$1);
        return transitionKt$doOnCancel$$inlined$addListener$1;
    }

    @RequiresApi(19)
    @NotNull
    public static final TransitionListener doOnResume(@NotNull Transition transition, @NotNull Function1<? super Transition, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(transition, "$this$doOnResume");
        Intrinsics.checkParameterIsNotNull(function1, "action");
        TransitionListener transitionKt$doOnResume$$inlined$addListener$1 = new TransitionKt$doOnResume$$inlined$addListener$1(function1);
        transition.addListener(transitionKt$doOnResume$$inlined$addListener$1);
        return transitionKt$doOnResume$$inlined$addListener$1;
    }

    @RequiresApi(19)
    @NotNull
    public static final TransitionListener doOnPause(@NotNull Transition transition, @NotNull Function1<? super Transition, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(transition, "$this$doOnPause");
        Intrinsics.checkParameterIsNotNull(function1, "action");
        TransitionListener transitionKt$doOnPause$$inlined$addListener$1 = new TransitionKt$doOnPause$$inlined$addListener$1(function1);
        transition.addListener(transitionKt$doOnPause$$inlined$addListener$1);
        return transitionKt$doOnPause$$inlined$addListener$1;
    }
}
