package kotlin.reflect.jvm.internal.impl.descriptors;

import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.sequences.Sequence;
import org.jetbrains.annotations.NotNull;

/* renamed from: kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterUtilsKt$computeConstructorTypeParameters$parametersFromContainingFunctions$2 */
/* compiled from: typeParameterUtils.kt */
final class C2951x246a49e3 extends Lambda implements Function1<DeclarationDescriptor, Sequence<? extends TypeParameterDescriptor>> {
    public static final C2951x246a49e3 INSTANCE = new C2951x246a49e3();

    C2951x246a49e3() {
        super(1);
    }

    @NotNull
    public final Sequence<TypeParameterDescriptor> invoke(@NotNull DeclarationDescriptor declarationDescriptor) {
        Intrinsics.checkParameterIsNotNull(declarationDescriptor, "it");
        List typeParameters = ((CallableDescriptor) declarationDescriptor).getTypeParameters();
        Intrinsics.checkExpressionValueIsNotNull(typeParameters, "(it as CallableDescriptor).typeParameters");
        return CollectionsKt.asSequence(typeParameters);
    }
}
