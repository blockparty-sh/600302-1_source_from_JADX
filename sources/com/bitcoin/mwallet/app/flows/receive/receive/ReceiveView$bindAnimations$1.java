package com.bitcoin.mwallet.app.flows.receive.receive;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.LinearLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.bitcoin.mwallet.core.utils.PixelsKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, mo37405d2 = {"<anonymous>", "", "run"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: ReceiveView.kt */
final class ReceiveView$bindAnimations$1 implements Runnable {
    final /* synthetic */ LinearLayout $bottomFrame;
    final /* synthetic */ ObjectAnimator $fadeIn;
    final /* synthetic */ ObjectAnimator $fadeOut;
    final /* synthetic */ ConstraintLayout $qrContainer;
    final /* synthetic */ ReceiveView this$0;

    ReceiveView$bindAnimations$1(ReceiveView receiveView, ConstraintLayout constraintLayout, LinearLayout linearLayout, ObjectAnimator objectAnimator, ObjectAnimator objectAnimator2) {
        this.this$0 = receiveView;
        this.$qrContainer = constraintLayout;
        this.$bottomFrame = linearLayout;
        this.$fadeOut = objectAnimator;
        this.$fadeIn = objectAnimator2;
    }

    public final void run() {
        Resources resources = this.this$0.getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources, "this.resources");
        int i = resources.getDisplayMetrics().heightPixels / 2;
        ConstraintLayout constraintLayout = this.$qrContainer;
        float height = constraintLayout != null ? (float) constraintLayout.getHeight() : 0.0f;
        LinearLayout linearLayout = this.$bottomFrame;
        float height2 = linearLayout != null ? (float) linearLayout.getHeight() : 0.0f;
        int[] iArr = new int[2];
        ConstraintLayout constraintLayout2 = this.$qrContainer;
        if (constraintLayout2 != null) {
            constraintLayout2.getLocationOnScreen(iArr);
        }
        int i2 = iArr[1];
        ReceiveView receiveView = this.this$0;
        LinearLayout linearLayout2 = this.$bottomFrame;
        Context requireContext = receiveView.requireContext();
        String str = "requireContext()";
        Intrinsics.checkExpressionValueIsNotNull(requireContext, str);
        float[] fArr = {height2 - PixelsKt.pxFromDp(requireContext, 24.0f), 0.0f};
        String str2 = "translationY";
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(linearLayout2, str2, fArr);
        Intrinsics.checkExpressionValueIsNotNull(ofFloat, "ObjectAnimator.ofFloat(b…quireContext(), 24f), 0f)");
        receiveView.setBottomSheetSlideUp(ofFloat);
        this.this$0.getBottomSheetSlideUp().setInterpolator(new AccelerateDecelerateInterpolator());
        this.this$0.getBottomSheetSlideUp().setDuration(200);
        ReceiveView receiveView2 = this.this$0;
        LinearLayout linearLayout3 = this.$bottomFrame;
        Context requireContext2 = receiveView2.requireContext();
        Intrinsics.checkExpressionValueIsNotNull(requireContext2, str);
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(linearLayout3, str2, new float[]{0.0f, height2 - PixelsKt.pxFromDp(requireContext2, 24.0f)});
        Intrinsics.checkExpressionValueIsNotNull(ofFloat2, "ObjectAnimator.ofFloat(b…p(requireContext(), 24f))");
        receiveView2.setBottomSheetSlideDown(ofFloat2);
        this.this$0.getBottomSheetSlideDown().setInterpolator(new AccelerateDecelerateInterpolator());
        this.this$0.getBottomSheetSlideDown().setDuration(200);
        ReceiveView receiveView3 = this.this$0;
        float f = (((float) i) - (height / ((float) 2))) - ((float) i2);
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(this.$qrContainer, str2, new float[]{0.0f, f});
        Intrinsics.checkExpressionValueIsNotNull(ofFloat3, "ObjectAnimator.ofFloat(q…rContainerYPos.toFloat())");
        receiveView3.setCenterQrCard(ofFloat3);
        this.this$0.getCenterQrCard().setInterpolator(new AccelerateDecelerateInterpolator());
        this.this$0.getCenterQrCard().setDuration(200);
        ReceiveView receiveView4 = this.this$0;
        ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(this.$qrContainer, str2, new float[]{f, 0.0f});
        Intrinsics.checkExpressionValueIsNotNull(ofFloat4, "ObjectAnimator.ofFloat(q…tainerYPos.toFloat(), 0f)");
        receiveView4.setReturnQrCard(ofFloat4);
        this.this$0.getReturnQrCard().setInterpolator(new AccelerateDecelerateInterpolator());
        this.this$0.getReturnQrCard().setDuration(200);
        this.this$0.setFlipPhoneAnimations(new AnimatorSet());
        this.this$0.getFlipPhoneAnimations().playTogether(new Animator[]{this.this$0.getRotate(), this.$fadeOut, this.this$0.getBottomSheetSlideDown(), this.this$0.getCenterQrCard()});
        this.this$0.setReturnPhoneAnimations(new AnimatorSet());
        this.this$0.getReturnPhoneAnimations().playTogether(new Animator[]{this.this$0.getReverseRotate(), this.$fadeIn, this.this$0.getBottomSheetSlideUp(), this.this$0.getReturnQrCard()});
    }
}
