package kotlin.reflect.jvm.internal.impl.descriptors;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* renamed from: kotlin.reflect.jvm.internal.impl.descriptors.FindClassInModuleKt$findNonGenericClassAcrossDependencies$typeParametersCount$1 */
/* compiled from: findClassInModule.kt */
final class C2946x24bfe126 extends FunctionReference implements Function1<ClassId, ClassId> {
    public static final C2946x24bfe126 INSTANCE = new C2946x24bfe126();

    C2946x24bfe126() {
        super(1);
    }

    public final String getName() {
        return "getOuterClassId";
    }

    public final KDeclarationContainer getOwner() {
        return Reflection.getOrCreateKotlinClass(ClassId.class);
    }

    public final String getSignature() {
        return "getOuterClassId()Lorg/jetbrains/kotlin/name/ClassId;";
    }

    @Nullable
    public final ClassId invoke(@NotNull ClassId classId) {
        Intrinsics.checkParameterIsNotNull(classId, "p1");
        return classId.getOuterClassId();
    }
}
