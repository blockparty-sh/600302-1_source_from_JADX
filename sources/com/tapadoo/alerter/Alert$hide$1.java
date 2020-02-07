package com.tapadoo.alerter;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.LinearLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\b"}, mo37405d2 = {"com/tapadoo/alerter/Alert$hide$1", "Landroid/view/animation/Animation$AnimationListener;", "onAnimationEnd", "", "animation", "Landroid/view/animation/Animation;", "onAnimationRepeat", "onAnimationStart", "alerter_release"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: Alert.kt */
public final class Alert$hide$1 implements AnimationListener {
    final /* synthetic */ Alert this$0;

    public void onAnimationRepeat(@NotNull Animation animation) {
        Intrinsics.checkParameterIsNotNull(animation, "animation");
    }

    Alert$hide$1(Alert alert) {
        this.this$0 = alert;
    }

    public void onAnimationStart(@NotNull Animation animation) {
        Intrinsics.checkParameterIsNotNull(animation, "animation");
        LinearLayout linearLayout = (LinearLayout) this.this$0._$_findCachedViewById(C2645R.C2648id.llAlertBackground);
        if (linearLayout != null) {
            linearLayout.setOnClickListener(null);
        }
        LinearLayout linearLayout2 = (LinearLayout) this.this$0._$_findCachedViewById(C2645R.C2648id.llAlertBackground);
        if (linearLayout2 != null) {
            linearLayout2.setClickable(false);
        }
    }

    public void onAnimationEnd(@NotNull Animation animation) {
        Intrinsics.checkParameterIsNotNull(animation, "animation");
        this.this$0.removeFromParent$alerter_release();
    }
}
