package com.guardanis.applock.pin;

import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import com.guardanis.applock.C2245R;
import java.lang.ref.WeakReference;

public class PINItemAnimator extends Thread {
    private static final int ANIMATION_DURATION = 250;
    private static final int UPDATE_RATE = 25;
    private ItemAnimationDirection animationDirection;
    private boolean canceled = false;
    private WeakReference<PINInputView> inputView;
    private Interpolator itemInterpolator = new AccelerateDecelerateInterpolator();
    /* access modifiers changed from: private */
    public PINItemView itemView;
    private float minSizePercent;
    private long startTime;

    public enum ItemAnimationDirection {
        IN,
        OUT
    }

    public PINItemAnimator(PINInputView pINInputView, PINItemView pINItemView, ItemAnimationDirection itemAnimationDirection) {
        this.inputView = new WeakReference<>(pINInputView);
        this.itemView = pINItemView;
        this.animationDirection = itemAnimationDirection;
        this.minSizePercent = Float.parseFloat(pINInputView.getResources().getString(C2245R.string.applock__empty_item_min_size_percent));
    }

    public void run() {
        this.startTime = System.currentTimeMillis();
        try {
            if (this.animationDirection == ItemAnimationDirection.IN) {
                animateIn();
            } else {
                animateOut();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void animateIn() throws Exception {
        float f = this.minSizePercent;
        while (f < 1.0f && !this.canceled) {
            f = Math.min(this.minSizePercent + calculatePercentComplete(), 1.0f);
            updateView(f);
            Thread.sleep(25);
        }
    }

    private void animateOut() throws Exception {
        float calculatePercentComplete = 1.0f - calculatePercentComplete();
        while (this.minSizePercent < calculatePercentComplete && !this.canceled) {
            calculatePercentComplete = Math.max(1.0f - calculatePercentComplete(), this.minSizePercent);
            updateView(calculatePercentComplete);
            Thread.sleep(25);
        }
    }

    /* access modifiers changed from: protected */
    public float calculatePercentComplete() {
        return this.itemInterpolator.getInterpolation(((float) (System.currentTimeMillis() - this.startTime)) / 250.0f);
    }

    private void updateView(final float f) {
        final PINInputView pINInputView = (PINInputView) this.inputView.get();
        if (pINInputView != null) {
            pINInputView.post(new Runnable() {
                public void run() {
                    PINItemAnimator.this.itemView.onAnimationUpdate(f);
                    pINInputView.invalidate();
                }
            });
        }
    }

    public void cancel() {
        this.canceled = true;
    }
}
