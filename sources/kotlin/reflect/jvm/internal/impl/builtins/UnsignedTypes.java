package kotlin.reflect.jvm.internal.impl.builtins;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import org.jetbrains.annotations.NotNull;

/* compiled from: UnsignedType.kt */
public final class UnsignedTypes {
    public static final UnsignedTypes INSTANCE = new UnsignedTypes();
    @NotNull
    private static final Set<Name> unsignedTypeNames;

    static {
        UnsignedType[] values = UnsignedType.values();
        Collection arrayList = new ArrayList(values.length);
        for (UnsignedType typeName : values) {
            arrayList.add(typeName.getTypeName());
        }
        unsignedTypeNames = CollectionsKt.toSet((List) arrayList);
    }

    private UnsignedTypes() {
    }

    public final boolean isUnsignedType(@NotNull KotlinType kotlinType) {
        Intrinsics.checkParameterIsNotNull(kotlinType, "type");
        if (TypeUtils.noExpectedType(kotlinType)) {
            return false;
        }
        ClassifierDescriptor declarationDescriptor = kotlinType.getConstructor().getDeclarationDescriptor();
        if (declarationDescriptor == null) {
            return false;
        }
        Intrinsics.checkExpressionValueIsNotNull(declarationDescriptor, "descriptor");
        return isUnsignedClass(declarationDescriptor);
    }

    public final boolean isUnsignedClass(@NotNull DeclarationDescriptor declarationDescriptor) {
        Intrinsics.checkParameterIsNotNull(declarationDescriptor, "descriptor");
        DeclarationDescriptor containingDeclaration = declarationDescriptor.getContainingDeclaration();
        return (containingDeclaration instanceof PackageFragmentDescriptor) && Intrinsics.areEqual((Object) ((PackageFragmentDescriptor) containingDeclaration).getFqName(), (Object) KotlinBuiltIns.BUILT_INS_PACKAGE_FQ_NAME) && unsignedTypeNames.contains(declarationDescriptor.getName());
    }
}
