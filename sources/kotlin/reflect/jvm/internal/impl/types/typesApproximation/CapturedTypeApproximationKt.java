package kotlin.reflect.jvm.internal.impl.types.typesApproximation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.renderer.DescriptorRenderer;
import kotlin.reflect.jvm.internal.impl.resolve.calls.inference.CapturedTypeConstructor;
import kotlin.reflect.jvm.internal.impl.resolve.calls.inference.CapturedTypeConstructorKt;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.FlexibleTypesKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitution;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutionKt;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import kotlin.reflect.jvm.internal.impl.types.TypeWithEnhancementKt;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: CapturedTypeApproximation.kt */
public final class CapturedTypeApproximationKt {

    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[Variance.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1 = new int[Variance.values().length];

        static {
            $EnumSwitchMapping$0[Variance.INVARIANT.ordinal()] = 1;
            $EnumSwitchMapping$0[Variance.IN_VARIANCE.ordinal()] = 2;
            $EnumSwitchMapping$0[Variance.OUT_VARIANCE.ordinal()] = 3;
            $EnumSwitchMapping$1[Variance.IN_VARIANCE.ordinal()] = 1;
            $EnumSwitchMapping$1[Variance.OUT_VARIANCE.ordinal()] = 2;
        }
    }

    private static final TypeProjection toTypeProjection(@NotNull TypeArgument typeArgument) {
        boolean isConsistent = typeArgument.isConsistent();
        if (!_Assertions.ENABLED || isConsistent) {
            CapturedTypeApproximationKt$toTypeProjection$2 capturedTypeApproximationKt$toTypeProjection$2 = new CapturedTypeApproximationKt$toTypeProjection$2(typeArgument);
            if (Intrinsics.areEqual((Object) typeArgument.getInProjection(), (Object) typeArgument.getOutProjection())) {
                return new TypeProjectionImpl(typeArgument.getInProjection());
            }
            if (KotlinBuiltIns.isNothing(typeArgument.getInProjection()) && typeArgument.getTypeParameter().getVariance() != Variance.IN_VARIANCE) {
                return new TypeProjectionImpl(capturedTypeApproximationKt$toTypeProjection$2.invoke(Variance.OUT_VARIANCE), typeArgument.getOutProjection());
            }
            if (KotlinBuiltIns.isNullableAny(typeArgument.getOutProjection())) {
                return new TypeProjectionImpl(capturedTypeApproximationKt$toTypeProjection$2.invoke(Variance.IN_VARIANCE), typeArgument.getInProjection());
            }
            return new TypeProjectionImpl(capturedTypeApproximationKt$toTypeProjection$2.invoke(Variance.OUT_VARIANCE), typeArgument.getOutProjection());
        }
        DescriptorRenderer withOptions = DescriptorRenderer.Companion.withOptions(C3146x8e300b6.INSTANCE);
        StringBuilder sb = new StringBuilder();
        sb.append("Only consistent enhanced type projection can be converted to type projection, but ");
        sb.append('[');
        sb.append(withOptions.render(typeArgument.getTypeParameter()));
        sb.append(": <");
        sb.append(withOptions.renderType(typeArgument.getInProjection()));
        sb.append(", ");
        sb.append(withOptions.renderType(typeArgument.getOutProjection()));
        sb.append(">]");
        sb.append(" was found");
        throw new AssertionError(sb.toString());
    }

    private static final TypeArgument toTypeArgument(@NotNull TypeProjection typeProjection, TypeParameterDescriptor typeParameterDescriptor) {
        int i = WhenMappings.$EnumSwitchMapping$0[TypeSubstitutor.combine(typeParameterDescriptor.getVariance(), typeProjection).ordinal()];
        String str = "type";
        if (i == 1) {
            KotlinType type = typeProjection.getType();
            Intrinsics.checkExpressionValueIsNotNull(type, str);
            KotlinType type2 = typeProjection.getType();
            Intrinsics.checkExpressionValueIsNotNull(type2, str);
            return new TypeArgument(typeParameterDescriptor, type, type2);
        } else if (i == 2) {
            KotlinType type3 = typeProjection.getType();
            Intrinsics.checkExpressionValueIsNotNull(type3, str);
            SimpleType nullableAnyType = DescriptorUtilsKt.getBuiltIns(typeParameterDescriptor).getNullableAnyType();
            Intrinsics.checkExpressionValueIsNotNull(nullableAnyType, "typeParameter.builtIns.nullableAnyType");
            return new TypeArgument(typeParameterDescriptor, type3, nullableAnyType);
        } else if (i == 3) {
            SimpleType nothingType = DescriptorUtilsKt.getBuiltIns(typeParameterDescriptor).getNothingType();
            Intrinsics.checkExpressionValueIsNotNull(nothingType, "typeParameter.builtIns.nothingType");
            KotlinType kotlinType = nothingType;
            KotlinType type4 = typeProjection.getType();
            Intrinsics.checkExpressionValueIsNotNull(type4, str);
            return new TypeArgument(typeParameterDescriptor, kotlinType, type4);
        } else {
            throw new NoWhenBranchMatchedException();
        }
    }

    @Nullable
    public static final TypeProjection approximateCapturedTypesIfNecessary(@Nullable TypeProjection typeProjection, boolean z) {
        if (typeProjection == null) {
            return null;
        }
        if (typeProjection.isStarProjection()) {
            return typeProjection;
        }
        KotlinType type = typeProjection.getType();
        if (!TypeUtils.contains(type, C3144x21acc51c.INSTANCE)) {
            return typeProjection;
        }
        Variance projectionKind = typeProjection.getProjectionKind();
        String str = "type";
        if (projectionKind == Variance.OUT_VARIANCE) {
            Intrinsics.checkExpressionValueIsNotNull(type, str);
            return new TypeProjectionImpl(projectionKind, (KotlinType) approximateCapturedTypes(type).getUpper());
        } else if (!z) {
            return substituteCapturedTypesWithProjections(typeProjection);
        } else {
            Intrinsics.checkExpressionValueIsNotNull(type, str);
            return new TypeProjectionImpl(projectionKind, (KotlinType) approximateCapturedTypes(type).getLower());
        }
    }

    private static final TypeProjection substituteCapturedTypesWithProjections(TypeProjection typeProjection) {
        return TypeSubstitutor.create((TypeSubstitution) new C3145x12aaa596()).substituteWithoutApproximation(typeProjection);
    }

    @NotNull
    public static final ApproximationBounds<KotlinType> approximateCapturedTypes(@NotNull KotlinType kotlinType) {
        KotlinType kotlinType2;
        ApproximationBounds<KotlinType> approximationBounds;
        Intrinsics.checkParameterIsNotNull(kotlinType, "type");
        if (FlexibleTypesKt.isFlexible(kotlinType)) {
            ApproximationBounds approximateCapturedTypes = approximateCapturedTypes(FlexibleTypesKt.lowerIfFlexible(kotlinType));
            ApproximationBounds approximateCapturedTypes2 = approximateCapturedTypes(FlexibleTypesKt.upperIfFlexible(kotlinType));
            return new ApproximationBounds<>(TypeWithEnhancementKt.inheritEnhancement(KotlinTypeFactory.flexibleType(FlexibleTypesKt.lowerIfFlexible((KotlinType) approximateCapturedTypes.getLower()), FlexibleTypesKt.upperIfFlexible((KotlinType) approximateCapturedTypes2.getLower())), kotlinType), TypeWithEnhancementKt.inheritEnhancement(KotlinTypeFactory.flexibleType(FlexibleTypesKt.lowerIfFlexible((KotlinType) approximateCapturedTypes.getUpper()), FlexibleTypesKt.upperIfFlexible((KotlinType) approximateCapturedTypes2.getUpper())), kotlinType));
        }
        TypeConstructor constructor = kotlinType.getConstructor();
        String str = "type.builtIns.nothingType";
        if (CapturedTypeConstructorKt.isCaptured(kotlinType)) {
            if (constructor != null) {
                TypeProjection typeProjection = ((CapturedTypeConstructor) constructor).getTypeProjection();
                CapturedTypeApproximationKt$approximateCapturedTypes$1 capturedTypeApproximationKt$approximateCapturedTypes$1 = new CapturedTypeApproximationKt$approximateCapturedTypes$1(kotlinType);
                KotlinType type = typeProjection.getType();
                Intrinsics.checkExpressionValueIsNotNull(type, "typeProjection.type");
                KotlinType invoke = capturedTypeApproximationKt$approximateCapturedTypes$1.invoke(type);
                int i = WhenMappings.$EnumSwitchMapping$1[typeProjection.getProjectionKind().ordinal()];
                if (i == 1) {
                    approximationBounds = new ApproximationBounds<>(invoke, TypeUtilsKt.getBuiltIns(kotlinType).getNullableAnyType());
                } else if (i == 2) {
                    SimpleType nothingType = TypeUtilsKt.getBuiltIns(kotlinType).getNothingType();
                    Intrinsics.checkExpressionValueIsNotNull(nothingType, str);
                    approximationBounds = new ApproximationBounds<>(capturedTypeApproximationKt$approximateCapturedTypes$1.invoke((KotlinType) nothingType), invoke);
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Only nontrivial projections should have been captured, not: ");
                    sb.append(typeProjection);
                    throw new AssertionError(sb.toString());
                }
                return approximationBounds;
            }
            throw new TypeCastException("null cannot be cast to non-null type org.jetbrains.kotlin.resolve.calls.inference.CapturedTypeConstructor");
        } else if (kotlinType.getArguments().isEmpty() || kotlinType.getArguments().size() != constructor.getParameters().size()) {
            return new ApproximationBounds<>(kotlinType, kotlinType);
        } else {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            Iterable arguments = kotlinType.getArguments();
            List parameters = constructor.getParameters();
            Intrinsics.checkExpressionValueIsNotNull(parameters, "typeConstructor.parameters");
            for (Pair pair : CollectionsKt.zip(arguments, (Iterable<? extends R>) parameters)) {
                TypeProjection typeProjection2 = (TypeProjection) pair.component1();
                TypeParameterDescriptor typeParameterDescriptor = (TypeParameterDescriptor) pair.component2();
                Intrinsics.checkExpressionValueIsNotNull(typeParameterDescriptor, "typeParameter");
                TypeArgument typeArgument = toTypeArgument(typeProjection2, typeParameterDescriptor);
                if (typeProjection2.isStarProjection()) {
                    arrayList.add(typeArgument);
                    arrayList2.add(typeArgument);
                } else {
                    ApproximationBounds approximateProjection = approximateProjection(typeArgument);
                    TypeArgument typeArgument2 = (TypeArgument) approximateProjection.component1();
                    TypeArgument typeArgument3 = (TypeArgument) approximateProjection.component2();
                    arrayList.add(typeArgument2);
                    arrayList2.add(typeArgument3);
                }
            }
            Iterable iterable = arrayList;
            boolean z = false;
            if (!(iterable instanceof Collection) || !((Collection) iterable).isEmpty()) {
                Iterator it = iterable.iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (!((TypeArgument) it.next()).isConsistent()) {
                            z = true;
                            break;
                        }
                    } else {
                        break;
                    }
                }
            }
            if (z) {
                SimpleType nothingType2 = TypeUtilsKt.getBuiltIns(kotlinType).getNothingType();
                Intrinsics.checkExpressionValueIsNotNull(nothingType2, str);
                kotlinType2 = nothingType2;
            } else {
                kotlinType2 = replaceTypeArguments(kotlinType, arrayList);
            }
            return new ApproximationBounds<>(kotlinType2, replaceTypeArguments(kotlinType, arrayList2));
        }
    }

    private static final KotlinType replaceTypeArguments(@NotNull KotlinType kotlinType, List<TypeArgument> list) {
        boolean z = kotlinType.getArguments().size() == list.size();
        if (!_Assertions.ENABLED || z) {
            Iterable<TypeArgument> iterable = list;
            Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
            for (TypeArgument typeProjection : iterable) {
                arrayList.add(toTypeProjection(typeProjection));
            }
            return TypeSubstitutionKt.replace$default(kotlinType, (List) arrayList, (Annotations) null, 2, (Object) null);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Incorrect type arguments ");
        sb.append(list);
        throw new AssertionError(sb.toString());
    }

    private static final ApproximationBounds<TypeArgument> approximateProjection(TypeArgument typeArgument) {
        ApproximationBounds approximateCapturedTypes = approximateCapturedTypes(typeArgument.getInProjection());
        KotlinType kotlinType = (KotlinType) approximateCapturedTypes.component1();
        KotlinType kotlinType2 = (KotlinType) approximateCapturedTypes.component2();
        ApproximationBounds approximateCapturedTypes2 = approximateCapturedTypes(typeArgument.getOutProjection());
        return new ApproximationBounds<>(new TypeArgument(typeArgument.getTypeParameter(), kotlinType2, (KotlinType) approximateCapturedTypes2.component1()), new TypeArgument(typeArgument.getTypeParameter(), kotlinType, (KotlinType) approximateCapturedTypes2.component2()));
    }
}
