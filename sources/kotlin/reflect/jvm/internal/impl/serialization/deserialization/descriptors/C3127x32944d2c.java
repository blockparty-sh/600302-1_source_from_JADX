package kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors;

import java.util.List;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterUtilsKt;
import org.jetbrains.annotations.NotNull;

/* renamed from: kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedClassDescriptor$DeserializedClassTypeConstructor$parameters$1 */
/* compiled from: DeserializedClassDescriptor.kt */
final class C3127x32944d2c extends Lambda implements Function0<List<? extends TypeParameterDescriptor>> {
    final /* synthetic */ DeserializedClassTypeConstructor this$0;

    C3127x32944d2c(DeserializedClassTypeConstructor deserializedClassTypeConstructor) {
        this.this$0 = deserializedClassTypeConstructor;
        super(0);
    }

    @NotNull
    public final List<TypeParameterDescriptor> invoke() {
        return TypeParameterUtilsKt.computeConstructorTypeParameters(DeserializedClassDescriptor.this);
    }
}
