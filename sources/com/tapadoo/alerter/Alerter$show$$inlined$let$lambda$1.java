package com.tapadoo.alerter;

import android.view.ViewGroup;
import kotlin.Metadata;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002¨\u0006\u0003"}, mo37405d2 = {"<anonymous>", "", "run", "com/tapadoo/alerter/Alerter$show$1$1"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: Alerter.kt */
final class Alerter$show$$inlined$let$lambda$1 implements Runnable {
    final /* synthetic */ Alerter this$0;

    Alerter$show$$inlined$let$lambda$1(Alerter alerter) {
        this.this$0 = alerter;
    }

    public final void run() {
        ViewGroup access$getActivityDecorView$p = this.this$0.getActivityDecorView();
        if (access$getActivityDecorView$p != null) {
            access$getActivityDecorView$p.addView(this.this$0.alert);
        }
    }
}
