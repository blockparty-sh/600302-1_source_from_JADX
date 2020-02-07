package kotlin.reflect.jvm.internal.impl.descriptors.annotations;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import org.jetbrains.annotations.NotNull;

/* renamed from: kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationUtilKt$createDeprecatedAnnotation$replaceWithAnnotation$1 */
/* compiled from: annotationUtil.kt */
final class C2964xf14e0c1a extends Lambda implements Function1<ModuleDescriptor, SimpleType> {
    final /* synthetic */ KotlinBuiltIns receiver$0;

    C2964xf14e0c1a(KotlinBuiltIns kotlinBuiltIns) {
        this.receiver$0 = kotlinBuiltIns;
        super(1);
    }

    @NotNull
    public final SimpleType invoke(@NotNull ModuleDescriptor moduleDescriptor) {
        Intrinsics.checkParameterIsNotNull(moduleDescriptor, "module");
        SimpleType arrayType = moduleDescriptor.getBuiltIns().getArrayType(Variance.INVARIANT, this.receiver$0.getStringType());
        Intrinsics.checkExpressionValueIsNotNull(arrayType, "module.builtIns.getArray…ce.INVARIANT, stringType)");
        return arrayType;
    }
}
