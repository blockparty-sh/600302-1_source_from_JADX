package kotlin.reflect.jvm.internal.impl.descriptors;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

/* renamed from: kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterUtilsKt$computeConstructorTypeParameters$parametersFromContainingFunctions$1 */
/* compiled from: typeParameterUtils.kt */
final class C2950x246a49e2 extends Lambda implements Function1<DeclarationDescriptor, Boolean> {
    public static final C2950x246a49e2 INSTANCE = new C2950x246a49e2();

    C2950x246a49e2() {
        super(1);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        return Boolean.valueOf(invoke((DeclarationDescriptor) obj));
    }

    public final boolean invoke(@NotNull DeclarationDescriptor declarationDescriptor) {
        Intrinsics.checkParameterIsNotNull(declarationDescriptor, "it");
        return declarationDescriptor instanceof CallableDescriptor;
    }
}
