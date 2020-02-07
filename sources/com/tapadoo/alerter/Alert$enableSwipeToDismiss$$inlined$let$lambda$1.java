package com.tapadoo.alerter;

import android.view.View;
import com.tapadoo.alerter.SwipeDismissTouchListener.DismissCallbacks;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000'\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0018\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0003H\u0016¨\u0006\n¸\u0006\u0000"}, mo37405d2 = {"com/tapadoo/alerter/Alert$enableSwipeToDismiss$1$1", "Lcom/tapadoo/alerter/SwipeDismissTouchListener$DismissCallbacks;", "canDismiss", "", "onDismiss", "", "view", "Landroid/view/View;", "onTouch", "touch", "alerter_release"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: Alert.kt */
public final class Alert$enableSwipeToDismiss$$inlined$let$lambda$1 implements DismissCallbacks {
    final /* synthetic */ Alert this$0;

    public boolean canDismiss() {
        return true;
    }

    public void onTouch(@NotNull View view, boolean z) {
        Intrinsics.checkParameterIsNotNull(view, "view");
    }

    Alert$enableSwipeToDismiss$$inlined$let$lambda$1(Alert alert) {
        this.this$0 = alert;
    }

    public void onDismiss(@NotNull View view) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        this.this$0.removeFromParent$alerter_release();
    }
}
