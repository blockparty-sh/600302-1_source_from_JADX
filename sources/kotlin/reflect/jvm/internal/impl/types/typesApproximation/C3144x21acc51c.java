package kotlin.reflect.jvm.internal.impl.types.typesApproximation;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.resolve.calls.inference.CapturedTypeConstructorKt;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;

/* renamed from: kotlin.reflect.jvm.internal.impl.types.typesApproximation.CapturedTypeApproximationKt$approximateCapturedTypesIfNecessary$1 */
/* compiled from: CapturedTypeApproximation.kt */
final class C3144x21acc51c extends Lambda implements Function1<UnwrappedType, Boolean> {
    public static final C3144x21acc51c INSTANCE = new C3144x21acc51c();

    C3144x21acc51c() {
        super(1);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        return Boolean.valueOf(invoke((UnwrappedType) obj));
    }

    public final boolean invoke(UnwrappedType unwrappedType) {
        Intrinsics.checkExpressionValueIsNotNull(unwrappedType, "it");
        return CapturedTypeConstructorKt.isCaptured(unwrappedType);
    }
}
