package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import org.jetbrains.annotations.NotNull;

/* renamed from: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement$enhanceSignature$valueParameterEnhancements$1$enhancementResult$1 */
/* compiled from: signatureEnhancement.kt */
final class C3027xdf7d8d68 extends Lambda implements Function1<CallableMemberDescriptor, KotlinType> {

    /* renamed from: $p */
    final /* synthetic */ ValueParameterDescriptor f711$p;

    C3027xdf7d8d68(ValueParameterDescriptor valueParameterDescriptor) {
        this.f711$p = valueParameterDescriptor;
        super(1);
    }

    @NotNull
    public final KotlinType invoke(@NotNull CallableMemberDescriptor callableMemberDescriptor) {
        Intrinsics.checkParameterIsNotNull(callableMemberDescriptor, "it");
        Object obj = callableMemberDescriptor.getValueParameters().get(this.f711$p.getIndex());
        Intrinsics.checkExpressionValueIsNotNull(obj, "it.valueParameters[p.index]");
        KotlinType type = ((ValueParameterDescriptor) obj).getType();
        Intrinsics.checkExpressionValueIsNotNull(type, "it.valueParameters[p.index].type");
        return type;
    }
}
