package com.tapadoo.alerter;

import android.view.View;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001H\n¢\u0006\u0002\b\u0003"}, mo37405d2 = {"<anonymous>", "Landroid/view/View;", "kotlin.jvm.PlatformType", "invoke"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: Alert.kt */
final class Alert$layoutContainer$2 extends Lambda implements Function0<View> {
    final /* synthetic */ Alert this$0;

    Alert$layoutContainer$2(Alert alert) {
        this.this$0 = alert;
        super(0);
    }

    public final View invoke() {
        return this.this$0.findViewById(C2645R.C2648id.vAlertContentContainer);
    }
}
