package kotlin.reflect.jvm.internal.impl.load.java;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import org.jetbrains.annotations.NotNull;

/* renamed from: kotlin.reflect.jvm.internal.impl.load.java.BuiltinSpecialProperties$getBuiltinSpecialPropertyGetterName$descriptor$1 */
/* compiled from: specialBuiltinMembers.kt */
final class C2981xbe16cae4 extends Lambda implements Function1<CallableMemberDescriptor, Boolean> {
    public static final C2981xbe16cae4 INSTANCE = new C2981xbe16cae4();

    C2981xbe16cae4() {
        super(1);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        return Boolean.valueOf(invoke((CallableMemberDescriptor) obj));
    }

    public final boolean invoke(@NotNull CallableMemberDescriptor callableMemberDescriptor) {
        Intrinsics.checkParameterIsNotNull(callableMemberDescriptor, "it");
        return BuiltinSpecialProperties.INSTANCE.hasBuiltinSpecialPropertyFqName(callableMemberDescriptor);
    }
}
