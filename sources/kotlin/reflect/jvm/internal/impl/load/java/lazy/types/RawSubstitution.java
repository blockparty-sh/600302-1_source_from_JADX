package kotlin.reflect.jvm.internal.impl.load.java.lazy.types;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.load.java.components.TypeUsage;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.types.ErrorUtils;
import kotlin.reflect.jvm.internal.impl.types.FlexibleTypesKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeKt;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitution;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import org.jetbrains.annotations.NotNull;

/* compiled from: RawType.kt */
public final class RawSubstitution extends TypeSubstitution {
    public static final RawSubstitution INSTANCE = new RawSubstitution();
    private static final JavaTypeAttributes lowerTypeAttr = JavaTypeResolverKt.toAttributes$default(TypeUsage.COMMON, false, null, 3, null).withFlexibility(JavaTypeFlexibility.FLEXIBLE_LOWER_BOUND);
    private static final JavaTypeAttributes upperTypeAttr = JavaTypeResolverKt.toAttributes$default(TypeUsage.COMMON, false, null, 3, null).withFlexibility(JavaTypeFlexibility.FLEXIBLE_UPPER_BOUND);

    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[JavaTypeFlexibility.values().length];

        static {
            $EnumSwitchMapping$0[JavaTypeFlexibility.FLEXIBLE_LOWER_BOUND.ordinal()] = 1;
            $EnumSwitchMapping$0[JavaTypeFlexibility.FLEXIBLE_UPPER_BOUND.ordinal()] = 2;
            $EnumSwitchMapping$0[JavaTypeFlexibility.INFLEXIBLE.ordinal()] = 3;
        }
    }

    public boolean isEmpty() {
        return false;
    }

    private RawSubstitution() {
    }

    @NotNull
    public TypeProjectionImpl get(@NotNull KotlinType kotlinType) {
        Intrinsics.checkParameterIsNotNull(kotlinType, "key");
        return new TypeProjectionImpl(eraseType(kotlinType));
    }

    private final KotlinType eraseType(KotlinType kotlinType) {
        UnwrappedType unwrappedType;
        ClassifierDescriptor declarationDescriptor = kotlinType.getConstructor().getDeclarationDescriptor();
        if (declarationDescriptor instanceof TypeParameterDescriptor) {
            return eraseType(JavaTypeResolverKt.getErasedUpperBound$default((TypeParameterDescriptor) declarationDescriptor, null, null, 3, null));
        }
        if (declarationDescriptor instanceof ClassDescriptor) {
            ClassDescriptor classDescriptor = (ClassDescriptor) declarationDescriptor;
            Pair eraseInflexibleBasedOnClassDescriptor = eraseInflexibleBasedOnClassDescriptor(FlexibleTypesKt.lowerIfFlexible(kotlinType), classDescriptor, lowerTypeAttr);
            SimpleType simpleType = (SimpleType) eraseInflexibleBasedOnClassDescriptor.component1();
            boolean booleanValue = ((Boolean) eraseInflexibleBasedOnClassDescriptor.component2()).booleanValue();
            Pair eraseInflexibleBasedOnClassDescriptor2 = eraseInflexibleBasedOnClassDescriptor(FlexibleTypesKt.upperIfFlexible(kotlinType), classDescriptor, upperTypeAttr);
            SimpleType simpleType2 = (SimpleType) eraseInflexibleBasedOnClassDescriptor2.component1();
            boolean booleanValue2 = ((Boolean) eraseInflexibleBasedOnClassDescriptor2.component2()).booleanValue();
            if (booleanValue || booleanValue2) {
                unwrappedType = new RawTypeImpl(simpleType, simpleType2);
            } else {
                unwrappedType = KotlinTypeFactory.flexibleType(simpleType, simpleType2);
            }
            return unwrappedType;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Unexpected declaration kind: ");
        sb.append(declarationDescriptor);
        throw new IllegalStateException(sb.toString().toString());
    }

    private final Pair<SimpleType, Boolean> eraseInflexibleBasedOnClassDescriptor(SimpleType simpleType, ClassDescriptor classDescriptor, JavaTypeAttributes javaTypeAttributes) {
        boolean isEmpty = simpleType.getConstructor().getParameters().isEmpty();
        Boolean valueOf = Boolean.valueOf(false);
        if (isEmpty) {
            return TuplesKt.m309to(simpleType, valueOf);
        }
        KotlinType kotlinType = simpleType;
        if (KotlinBuiltIns.isArray(kotlinType)) {
            TypeProjection typeProjection = (TypeProjection) simpleType.getArguments().get(0);
            Variance projectionKind = typeProjection.getProjectionKind();
            KotlinType type = typeProjection.getType();
            Intrinsics.checkExpressionValueIsNotNull(type, "componentTypeProjection.type");
            return TuplesKt.m309to(KotlinTypeFactory.simpleType(simpleType.getAnnotations(), simpleType.getConstructor(), CollectionsKt.listOf(new TypeProjectionImpl(projectionKind, eraseType(type))), simpleType.isMarkedNullable()), valueOf);
        } else if (KotlinTypeKt.isError(kotlinType)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Raw error type: ");
            sb.append(simpleType.getConstructor());
            return TuplesKt.m309to(ErrorUtils.createErrorType(sb.toString()), valueOf);
        } else {
            Annotations annotations = simpleType.getAnnotations();
            TypeConstructor constructor = simpleType.getConstructor();
            List parameters = simpleType.getConstructor().getParameters();
            Intrinsics.checkExpressionValueIsNotNull(parameters, "type.constructor.parameters");
            Iterable<TypeParameterDescriptor> iterable = parameters;
            Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
            for (TypeParameterDescriptor typeParameterDescriptor : iterable) {
                RawSubstitution rawSubstitution = INSTANCE;
                Intrinsics.checkExpressionValueIsNotNull(typeParameterDescriptor, "parameter");
                arrayList.add(computeProjection$default(rawSubstitution, typeParameterDescriptor, javaTypeAttributes, null, 4, null));
            }
            List list = (List) arrayList;
            boolean isMarkedNullable = simpleType.isMarkedNullable();
            MemberScope memberScope = classDescriptor.getMemberScope(INSTANCE);
            Intrinsics.checkExpressionValueIsNotNull(memberScope, "declaration.getMemberScope(RawSubstitution)");
            return TuplesKt.m309to(KotlinTypeFactory.simpleTypeWithNonTrivialMemberScope(annotations, constructor, list, isMarkedNullable, memberScope), Boolean.valueOf(true));
        }
    }

    @NotNull
    public static /* bridge */ /* synthetic */ TypeProjection computeProjection$default(RawSubstitution rawSubstitution, TypeParameterDescriptor typeParameterDescriptor, JavaTypeAttributes javaTypeAttributes, KotlinType kotlinType, int i, Object obj) {
        if ((i & 4) != 0) {
            kotlinType = JavaTypeResolverKt.getErasedUpperBound$default(typeParameterDescriptor, null, null, 3, null);
        }
        return rawSubstitution.computeProjection(typeParameterDescriptor, javaTypeAttributes, kotlinType);
    }

    @NotNull
    public final TypeProjection computeProjection(@NotNull TypeParameterDescriptor typeParameterDescriptor, @NotNull JavaTypeAttributes javaTypeAttributes, @NotNull KotlinType kotlinType) {
        TypeProjection typeProjection;
        Intrinsics.checkParameterIsNotNull(typeParameterDescriptor, "parameter");
        Intrinsics.checkParameterIsNotNull(javaTypeAttributes, "attr");
        Intrinsics.checkParameterIsNotNull(kotlinType, "erasedUpperBound");
        int i = WhenMappings.$EnumSwitchMapping$0[javaTypeAttributes.getFlexibility().ordinal()];
        if (i == 1) {
            return new TypeProjectionImpl(Variance.INVARIANT, kotlinType);
        }
        if (i != 2 && i != 3) {
            throw new NoWhenBranchMatchedException();
        } else if (!typeParameterDescriptor.getVariance().getAllowsOutPosition()) {
            return new TypeProjectionImpl(Variance.INVARIANT, DescriptorUtilsKt.getBuiltIns(typeParameterDescriptor).getNothingType());
        } else {
            List parameters = kotlinType.getConstructor().getParameters();
            Intrinsics.checkExpressionValueIsNotNull(parameters, "erasedUpperBound.constructor.parameters");
            if (!parameters.isEmpty()) {
                typeProjection = new TypeProjectionImpl(Variance.OUT_VARIANCE, kotlinType);
            } else {
                typeProjection = JavaTypeResolverKt.makeStarProjection(typeParameterDescriptor, javaTypeAttributes);
            }
            return typeProjection;
        }
    }
}
