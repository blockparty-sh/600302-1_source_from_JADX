package kotlin.reflect.jvm.internal.impl.types.typesApproximation;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.renderer.ClassifierNamePolicy.FULLY_QUALIFIED;
import kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptions;
import org.jetbrains.annotations.NotNull;

/* renamed from: kotlin.reflect.jvm.internal.impl.types.typesApproximation.CapturedTypeApproximationKt$toTypeProjection$1$descriptorRenderer$1 */
/* compiled from: CapturedTypeApproximation.kt */
final class C3146x8e300b6 extends Lambda implements Function1<DescriptorRendererOptions, Unit> {
    public static final C3146x8e300b6 INSTANCE = new C3146x8e300b6();

    C3146x8e300b6() {
        super(1);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((DescriptorRendererOptions) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(@NotNull DescriptorRendererOptions descriptorRendererOptions) {
        Intrinsics.checkParameterIsNotNull(descriptorRendererOptions, "$receiver");
        descriptorRendererOptions.setClassifierNamePolicy(FULLY_QUALIFIED.INSTANCE);
    }
}
