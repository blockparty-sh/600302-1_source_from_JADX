package com.tapadoo.alerter;

import android.animation.ValueAnimator;
import android.content.Context;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewConfiguration;
import android.view.ViewGroup.LayoutParams;
import androidx.annotation.RequiresApi;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001:\u0001\u001cB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0018\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\u00032\u0006\u0010\u0018\u001a\u00020\u0019H\u0017J\b\u0010\u001a\u001a\u00020\u001bH\u0003R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\rX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\rX\u000e¢\u0006\u0002\n\u0000¨\u0006\u001d"}, mo37405d2 = {"Lcom/tapadoo/alerter/SwipeDismissTouchListener;", "Landroid/view/View$OnTouchListener;", "mView", "Landroid/view/View;", "mCallbacks", "Lcom/tapadoo/alerter/SwipeDismissTouchListener$DismissCallbacks;", "(Landroid/view/View;Lcom/tapadoo/alerter/SwipeDismissTouchListener$DismissCallbacks;)V", "mAnimationTime", "", "mDownX", "", "mDownY", "mMinFlingVelocity", "", "mSlop", "mSwiping", "", "mSwipingSlop", "mTranslationX", "mVelocityTracker", "Landroid/view/VelocityTracker;", "mViewWidth", "onTouch", "view", "motionEvent", "Landroid/view/MotionEvent;", "performDismiss", "", "DismissCallbacks", "alerter_release"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: SwipeDismissTouchListener.kt */
public final class SwipeDismissTouchListener implements OnTouchListener {
    private final long mAnimationTime;
    /* access modifiers changed from: private */
    public final DismissCallbacks mCallbacks;
    private float mDownX;
    private float mDownY;
    private final int mMinFlingVelocity;
    private final int mSlop;
    private boolean mSwiping;
    private int mSwipingSlop;
    private float mTranslationX;
    private VelocityTracker mVelocityTracker;
    /* access modifiers changed from: private */
    public final View mView;
    private int mViewWidth = 1;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b`\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0018\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0003H&¨\u0006\n"}, mo37405d2 = {"Lcom/tapadoo/alerter/SwipeDismissTouchListener$DismissCallbacks;", "", "canDismiss", "", "onDismiss", "", "view", "Landroid/view/View;", "onTouch", "touch", "alerter_release"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: SwipeDismissTouchListener.kt */
    public interface DismissCallbacks {
        boolean canDismiss();

        void onDismiss(@NotNull View view);

        void onTouch(@NotNull View view, boolean z);
    }

    public SwipeDismissTouchListener(@NotNull View view, @NotNull DismissCallbacks dismissCallbacks) {
        Intrinsics.checkParameterIsNotNull(view, "mView");
        Intrinsics.checkParameterIsNotNull(dismissCallbacks, "mCallbacks");
        this.mView = view;
        this.mCallbacks = dismissCallbacks;
        ViewConfiguration viewConfiguration = ViewConfiguration.get(this.mView.getContext());
        Intrinsics.checkExpressionValueIsNotNull(viewConfiguration, "vc");
        this.mSlop = viewConfiguration.getScaledTouchSlop();
        this.mMinFlingVelocity = viewConfiguration.getScaledMinimumFlingVelocity() * 16;
        Context context = this.mView.getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "mView.context");
        this.mAnimationTime = (long) context.getResources().getInteger(17694720);
    }

    @RequiresApi(api = 12)
    public boolean onTouch(@NotNull View view, @NotNull MotionEvent motionEvent) {
        boolean z;
        Intrinsics.checkParameterIsNotNull(view, "view");
        Intrinsics.checkParameterIsNotNull(motionEvent, "motionEvent");
        motionEvent.offsetLocation(this.mTranslationX, 0.0f);
        if (this.mViewWidth < 2) {
            this.mViewWidth = this.mView.getWidth();
        }
        int actionMasked = motionEvent.getActionMasked();
        boolean z2 = true;
        if (actionMasked != 0) {
            if (actionMasked == 1) {
                VelocityTracker velocityTracker = this.mVelocityTracker;
                if (velocityTracker != null) {
                    float rawX = motionEvent.getRawX() - this.mDownX;
                    velocityTracker.addMovement(motionEvent);
                    velocityTracker.computeCurrentVelocity(1000);
                    float xVelocity = velocityTracker.getXVelocity();
                    float abs = Math.abs(xVelocity);
                    float abs2 = Math.abs(velocityTracker.getYVelocity());
                    if (Math.abs(rawX) > ((float) (this.mViewWidth / 2)) && this.mSwiping) {
                        z = rawX > ((float) 0);
                    } else if (((float) this.mMinFlingVelocity) > abs || abs2 >= abs || !this.mSwiping) {
                        z = false;
                        z2 = false;
                    } else {
                        float f = (float) 0;
                        boolean z3 = ((xVelocity > f ? 1 : (xVelocity == f ? 0 : -1)) < 0) == ((rawX > f ? 1 : (rawX == f ? 0 : -1)) < 0);
                        if (velocityTracker.getXVelocity() <= f) {
                            z2 = false;
                        }
                        z = z2;
                        z2 = z3;
                    }
                    if (z2) {
                        this.mView.animate().translationX((float) (z ? this.mViewWidth : -this.mViewWidth)).alpha(0.0f).setDuration(this.mAnimationTime).setListener(new SwipeDismissTouchListener$onTouch$$inlined$run$lambda$1(this, motionEvent, view));
                    } else if (this.mSwiping) {
                        this.mView.animate().translationX(0.0f).alpha(1.0f).setDuration(this.mAnimationTime).setListener(null);
                        this.mCallbacks.onTouch(view, false);
                    }
                    velocityTracker.recycle();
                    this.mVelocityTracker = null;
                    this.mTranslationX = 0.0f;
                    this.mDownX = 0.0f;
                    this.mDownY = 0.0f;
                    this.mSwiping = false;
                }
            } else if (actionMasked == 2) {
                VelocityTracker velocityTracker2 = this.mVelocityTracker;
                if (velocityTracker2 != null) {
                    velocityTracker2.addMovement(motionEvent);
                    float rawX2 = motionEvent.getRawX() - this.mDownX;
                    float rawY = motionEvent.getRawY() - this.mDownY;
                    if (Math.abs(rawX2) > ((float) this.mSlop) && Math.abs(rawY) < Math.abs(rawX2) / ((float) 2)) {
                        this.mSwiping = true;
                        this.mSwipingSlop = rawX2 > ((float) 0) ? this.mSlop : -this.mSlop;
                        this.mView.getParent().requestDisallowInterceptTouchEvent(true);
                        MotionEvent obtain = MotionEvent.obtain(motionEvent);
                        Intrinsics.checkExpressionValueIsNotNull(obtain, "cancelEvent");
                        obtain.setAction((motionEvent.getActionIndex() << 8) | 3);
                        this.mView.onTouchEvent(obtain);
                        obtain.recycle();
                    }
                    if (this.mSwiping) {
                        this.mTranslationX = rawX2;
                        this.mView.setTranslationX(rawX2 - ((float) this.mSwipingSlop));
                        this.mView.setAlpha(Math.max(0.0f, Math.min(1.0f, 1.0f - ((Math.abs(rawX2) * 2.0f) / ((float) this.mViewWidth)))));
                        return true;
                    }
                }
            } else if (actionMasked != 3) {
                view.performClick();
                return false;
            } else {
                VelocityTracker velocityTracker3 = this.mVelocityTracker;
                if (velocityTracker3 != null) {
                    this.mView.animate().translationX(0.0f).alpha(1.0f).setDuration(this.mAnimationTime).setListener(null);
                    velocityTracker3.recycle();
                    this.mVelocityTracker = null;
                    this.mTranslationX = 0.0f;
                    this.mDownX = 0.0f;
                    this.mDownY = 0.0f;
                    this.mSwiping = false;
                }
            }
            return false;
        }
        this.mDownX = motionEvent.getRawX();
        this.mDownY = motionEvent.getRawY();
        if (this.mCallbacks.canDismiss()) {
            this.mVelocityTracker = VelocityTracker.obtain();
            VelocityTracker velocityTracker4 = this.mVelocityTracker;
            if (velocityTracker4 == null) {
                Intrinsics.throwNpe();
            }
            velocityTracker4.addMovement(motionEvent);
        }
        this.mCallbacks.onTouch(view, true);
        return false;
    }

    /* access modifiers changed from: private */
    @RequiresApi(api = 11)
    public final void performDismiss() {
        LayoutParams layoutParams = this.mView.getLayoutParams();
        int height = this.mView.getHeight();
        ValueAnimator duration = ValueAnimator.ofInt(new int[]{height, 1}).setDuration(this.mAnimationTime);
        duration.addListener(new SwipeDismissTouchListener$performDismiss$1(this, layoutParams, height));
        duration.addUpdateListener(new SwipeDismissTouchListener$performDismiss$2(this, layoutParams));
        duration.start();
    }
}
