package kotlin.reflect.jvm.internal.impl.types.typesApproximation;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.resolve.calls.inference.CapturedTypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructorSubstitution;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* renamed from: kotlin.reflect.jvm.internal.impl.types.typesApproximation.CapturedTypeApproximationKt$substituteCapturedTypesWithProjections$typeSubstitutor$1 */
/* compiled from: CapturedTypeApproximation.kt */
public final class C3145x12aaa596 extends TypeConstructorSubstitution {
    C3145x12aaa596() {
    }

    @Nullable
    public TypeProjection get(@NotNull TypeConstructor typeConstructor) {
        Intrinsics.checkParameterIsNotNull(typeConstructor, "key");
        if (!(typeConstructor instanceof CapturedTypeConstructor)) {
            typeConstructor = null;
        }
        CapturedTypeConstructor capturedTypeConstructor = (CapturedTypeConstructor) typeConstructor;
        if (capturedTypeConstructor == null) {
            return null;
        }
        if (capturedTypeConstructor.getTypeProjection().isStarProjection()) {
            return new TypeProjectionImpl(Variance.OUT_VARIANCE, capturedTypeConstructor.getTypeProjection().getType());
        }
        return capturedTypeConstructor.getTypeProjection();
    }
}
