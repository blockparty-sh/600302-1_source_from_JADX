package androidx.activity;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, mo37405d2 = {"androidx/activity/OnBackPressedDispatcherKt$addCallback$callback$1", "Landroidx/activity/OnBackPressedCallback;", "handleOnBackPressed", "", "activity-ktx_release"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: OnBackPressedDispatcher.kt */
public final class OnBackPressedDispatcherKt$addCallback$callback$1 extends OnBackPressedCallback {
    final /* synthetic */ boolean $enabled;
    final /* synthetic */ Function1 $onBackPressed;

    OnBackPressedDispatcherKt$addCallback$callback$1(Function1 function1, boolean z, boolean z2) {
        this.$onBackPressed = function1;
        this.$enabled = z;
        super(z2);
    }

    public void handleOnBackPressed() {
        this.$onBackPressed.invoke(this);
    }
}
