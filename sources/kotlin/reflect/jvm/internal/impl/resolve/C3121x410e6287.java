package kotlin.reflect.jvm.internal.impl.resolve;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.utils.SmartSet;

/* renamed from: kotlin.reflect.jvm.internal.impl.resolve.OverridingUtilsKt$selectMostSpecificInEachOverridableGroup$overridableGroup$1 */
/* compiled from: overridingUtils.kt */
final class C3121x410e6287 extends Lambda implements Function1<H, Unit> {
    final /* synthetic */ SmartSet $conflictedHandles;

    C3121x410e6287(SmartSet smartSet) {
        this.$conflictedHandles = smartSet;
        super(1);
    }

    public final void invoke(H h) {
        SmartSet smartSet = this.$conflictedHandles;
        Intrinsics.checkExpressionValueIsNotNull(h, "it");
        smartSet.add(h);
    }
}
