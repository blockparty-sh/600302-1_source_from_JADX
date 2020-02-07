package kotlin.reflect.jvm.internal.impl.resolve;

import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeChecker.TypeConstructorEquality;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* renamed from: kotlin.reflect.jvm.internal.impl.resolve.DescriptorEquivalenceForOverrides$areCallableDescriptorsEquivalent$overridingUtil$1 */
/* compiled from: DescriptorEquivalenceForOverrides.kt */
final class C3108x10bda471 implements TypeConstructorEquality {

    /* renamed from: $a */
    final /* synthetic */ CallableDescriptor f715$a;

    /* renamed from: $b */
    final /* synthetic */ CallableDescriptor f716$b;

    C3108x10bda471(CallableDescriptor callableDescriptor, CallableDescriptor callableDescriptor2) {
        this.f715$a = callableDescriptor;
        this.f716$b = callableDescriptor2;
    }

    /* renamed from: invoke */
    public final boolean equals(@NotNull TypeConstructor typeConstructor, @NotNull TypeConstructor typeConstructor2) {
        Intrinsics.checkParameterIsNotNull(typeConstructor, "c1");
        Intrinsics.checkParameterIsNotNull(typeConstructor2, "c2");
        if (Intrinsics.areEqual((Object) typeConstructor, (Object) typeConstructor2)) {
            return true;
        }
        ClassifierDescriptor declarationDescriptor = typeConstructor.getDeclarationDescriptor();
        ClassifierDescriptor declarationDescriptor2 = typeConstructor2.getDeclarationDescriptor();
        if (!(declarationDescriptor instanceof TypeParameterDescriptor) || !(declarationDescriptor2 instanceof TypeParameterDescriptor)) {
            return false;
        }
        return DescriptorEquivalenceForOverrides.INSTANCE.areTypeParametersEquivalent((TypeParameterDescriptor) declarationDescriptor, (TypeParameterDescriptor) declarationDescriptor2, new Function2<DeclarationDescriptor, DeclarationDescriptor, Boolean>(this) {
            final /* synthetic */ C3108x10bda471 this$0;

            {
                this.this$0 = r1;
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(invoke((DeclarationDescriptor) obj, (DeclarationDescriptor) obj2));
            }

            public final boolean invoke(@Nullable DeclarationDescriptor declarationDescriptor, @Nullable DeclarationDescriptor declarationDescriptor2) {
                return Intrinsics.areEqual((Object) declarationDescriptor, (Object) this.this$0.f715$a) && Intrinsics.areEqual((Object) declarationDescriptor2, (Object) this.this$0.f716$b);
            }
        });
    }
}
