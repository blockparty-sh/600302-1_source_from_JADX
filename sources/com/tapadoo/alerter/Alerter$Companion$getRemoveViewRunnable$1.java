package com.tapadoo.alerter;

import android.view.ViewGroup;
import android.view.ViewParent;
import kotlin.Metadata;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, mo37405d2 = {"<anonymous>", "", "run"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: Alerter.kt */
final class Alerter$Companion$getRemoveViewRunnable$1 implements Runnable {
    final /* synthetic */ Alert $childView;

    Alerter$Companion$getRemoveViewRunnable$1(Alert alert) {
        this.$childView = alert;
    }

    public final void run() {
        Alert alert = this.$childView;
        if (alert != null) {
            ViewParent parent = alert.getParent();
            if (!(parent instanceof ViewGroup)) {
                parent = null;
            }
            ViewGroup viewGroup = (ViewGroup) parent;
            if (viewGroup != null) {
                viewGroup.removeView(this.$childView);
            }
        }
    }
}
