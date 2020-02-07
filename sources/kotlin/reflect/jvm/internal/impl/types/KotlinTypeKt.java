package kotlin.reflect.jvm.internal.impl.types;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: KotlinType.kt */
public final class KotlinTypeKt {
    public static final boolean isError(@NotNull KotlinType kotlinType) {
        Intrinsics.checkParameterIsNotNull(kotlinType, "$receiver");
        UnwrappedType unwrap = kotlinType.unwrap();
        return (unwrap instanceof ErrorType) || ((unwrap instanceof FlexibleType) && (((FlexibleType) unwrap).getDelegate() instanceof ErrorType));
    }
}
