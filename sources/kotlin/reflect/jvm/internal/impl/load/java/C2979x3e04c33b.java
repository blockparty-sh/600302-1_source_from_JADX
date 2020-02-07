package kotlin.reflect.jvm.internal.impl.load.java;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import org.jetbrains.annotations.NotNull;

/* renamed from: kotlin.reflect.jvm.internal.impl.load.java.BuiltinMethodsWithSpecialGenericSignature$getOverriddenBuiltinFunctionWithErasedValueParametersInJava$1 */
/* compiled from: specialBuiltinMembers.kt */
final class C2979x3e04c33b extends Lambda implements Function1<CallableMemberDescriptor, Boolean> {
    public static final C2979x3e04c33b INSTANCE = new C2979x3e04c33b();

    C2979x3e04c33b() {
        super(1);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        return Boolean.valueOf(invoke((CallableMemberDescriptor) obj));
    }

    public final boolean invoke(@NotNull CallableMemberDescriptor callableMemberDescriptor) {
        Intrinsics.checkParameterIsNotNull(callableMemberDescriptor, "it");
        return BuiltinMethodsWithSpecialGenericSignature.INSTANCE.getHasErasedValueParametersInJava(callableMemberDescriptor);
    }
}
