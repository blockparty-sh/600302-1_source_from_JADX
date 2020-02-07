package kotlin.reflect.jvm.internal.impl.resolve.calls.inference;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import org.jetbrains.annotations.NotNull;

/* compiled from: CapturedTypeConstructor.kt */
final class CapturedTypeConstructorKt$createCapturedIfNeeded$1 extends Lambda implements Function0<KotlinType> {
    final /* synthetic */ TypeProjection receiver$0;

    CapturedTypeConstructorKt$createCapturedIfNeeded$1(TypeProjection typeProjection) {
        this.receiver$0 = typeProjection;
        super(0);
    }

    @NotNull
    public final KotlinType invoke() {
        KotlinType type = this.receiver$0.getType();
        Intrinsics.checkExpressionValueIsNotNull(type, "this@createCapturedIfNeeded.type");
        return type;
    }
}
