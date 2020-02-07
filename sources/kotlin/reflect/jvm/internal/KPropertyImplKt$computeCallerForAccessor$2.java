package kotlin.reflect.jvm.internal;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.KPropertyImpl.Accessor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;

@Metadata(mo37403bv = {1, 0, 2}, mo37404d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000b\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, mo37405d2 = {"isInsideJvmInterfaceCompanionObject", "", "invoke"}, mo37406k = 3, mo37407mv = {1, 1, 11})
/* compiled from: KPropertyImpl.kt */
final class KPropertyImplKt$computeCallerForAccessor$2 extends Lambda implements Function0<Boolean> {
    final /* synthetic */ Accessor receiver$0;

    KPropertyImplKt$computeCallerForAccessor$2(Accessor accessor) {
        this.receiver$0 = accessor;
        super(0);
    }

    public final boolean invoke() {
        DeclarationDescriptor containingDeclaration = this.receiver$0.getProperty().getDescriptor().getContainingDeclaration();
        if (DescriptorUtils.isCompanionObject(containingDeclaration)) {
            Intrinsics.checkExpressionValueIsNotNull(containingDeclaration, "possibleCompanionObject");
            if (DescriptorUtils.isInterface(containingDeclaration.getContainingDeclaration()) || DescriptorUtils.isAnnotationClass(containingDeclaration.getContainingDeclaration())) {
                return true;
            }
        }
        return false;
    }
}
