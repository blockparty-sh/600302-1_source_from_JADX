package com.tapadoo.alerter;

import kotlin.Metadata;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, mo37405d2 = {"com/tapadoo/alerter/Alert$removeFromParent$1", "Ljava/lang/Runnable;", "run", "", "alerter_release"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: Alert.kt */
public final class Alert$removeFromParent$1 implements Runnable {
    final /* synthetic */ Alert this$0;

    Alert$removeFromParent$1(Alert alert) {
        this.this$0 = alert;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        android.util.Log.e(getClass().getSimpleName(), "Cannot remove from parent layout");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        return;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x002d */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
            r2 = this;
            com.tapadoo.alerter.Alert r0 = r2.this$0     // Catch:{ Exception -> 0x003b }
            android.view.ViewParent r0 = r0.getParent()     // Catch:{ Exception -> 0x003b }
            if (r0 == 0) goto L_0x004d
            com.tapadoo.alerter.Alert r0 = r2.this$0     // Catch:{ Exception -> 0x002d }
            android.view.ViewParent r0 = r0.getParent()     // Catch:{ Exception -> 0x002d }
            if (r0 == 0) goto L_0x0025
            android.view.ViewGroup r0 = (android.view.ViewGroup) r0     // Catch:{ Exception -> 0x002d }
            com.tapadoo.alerter.Alert r1 = r2.this$0     // Catch:{ Exception -> 0x002d }
            android.view.View r1 = (android.view.View) r1     // Catch:{ Exception -> 0x002d }
            r0.removeView(r1)     // Catch:{ Exception -> 0x002d }
            com.tapadoo.alerter.Alert r0 = r2.this$0     // Catch:{ Exception -> 0x002d }
            com.tapadoo.alerter.OnHideAlertListener r0 = r0.getOnHideListener$alerter_release()     // Catch:{ Exception -> 0x002d }
            if (r0 == 0) goto L_0x004d
            r0.onHide()     // Catch:{ Exception -> 0x002d }
            goto L_0x004d
        L_0x0025:
            kotlin.TypeCastException r0 = new kotlin.TypeCastException     // Catch:{ Exception -> 0x002d }
            java.lang.String r1 = "null cannot be cast to non-null type android.view.ViewGroup"
            r0.<init>(r1)     // Catch:{ Exception -> 0x002d }
            throw r0     // Catch:{ Exception -> 0x002d }
        L_0x002d:
            java.lang.Class r0 = r2.getClass()     // Catch:{ Exception -> 0x003b }
            java.lang.String r0 = r0.getSimpleName()     // Catch:{ Exception -> 0x003b }
            java.lang.String r1 = "Cannot remove from parent layout"
            android.util.Log.e(r0, r1)     // Catch:{ Exception -> 0x003b }
            goto L_0x004d
        L_0x003b:
            r0 = move-exception
            java.lang.Class r1 = r2.getClass()
            java.lang.String r1 = r1.getSimpleName()
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            java.lang.String r0 = android.util.Log.getStackTraceString(r0)
            android.util.Log.e(r1, r0)
        L_0x004d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tapadoo.alerter.Alert$removeFromParent$1.run():void");
    }
}
