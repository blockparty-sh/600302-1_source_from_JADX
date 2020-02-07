package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.C3063Type;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import org.jetbrains.annotations.NotNull;

/* compiled from: FlexibleTypeDeserializer.kt */
public interface FlexibleTypeDeserializer {

    /* compiled from: FlexibleTypeDeserializer.kt */
    public static final class ThrowException implements FlexibleTypeDeserializer {
        public static final ThrowException INSTANCE = new ThrowException();

        private ThrowException() {
        }

        @NotNull
        public KotlinType create(@NotNull C3063Type type, @NotNull String str, @NotNull SimpleType simpleType, @NotNull SimpleType simpleType2) {
            Intrinsics.checkParameterIsNotNull(type, "proto");
            Intrinsics.checkParameterIsNotNull(str, "flexibleId");
            Intrinsics.checkParameterIsNotNull(simpleType, "lowerBound");
            Intrinsics.checkParameterIsNotNull(simpleType2, "upperBound");
            throw new IllegalArgumentException("This method should not be used.");
        }
    }

    @NotNull
    KotlinType create(@NotNull C3063Type type, @NotNull String str, @NotNull SimpleType simpleType, @NotNull SimpleType simpleType2);
}
