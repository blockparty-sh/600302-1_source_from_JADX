package kotlin.reflect.jvm.internal.impl.types.checker;

import java.util.List;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.FlexibleTypesKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeKt;
import kotlin.reflect.jvm.internal.impl.types.TypeCapabilitiesKt;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TypeCheckingProcedure {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final TypeCheckingProcedureCallbacks constraints;

    /* renamed from: kotlin.reflect.jvm.internal.impl.types.checker.TypeCheckingProcedure$1 */
    static /* synthetic */ class C31421 {
        static final /* synthetic */ int[] $SwitchMap$org$jetbrains$kotlin$types$Variance = new int[Variance.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|8) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        static {
            /*
                kotlin.reflect.jvm.internal.impl.types.Variance[] r0 = kotlin.reflect.jvm.internal.impl.types.Variance.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$jetbrains$kotlin$types$Variance = r0
                int[] r0 = $SwitchMap$org$jetbrains$kotlin$types$Variance     // Catch:{ NoSuchFieldError -> 0x0014 }
                kotlin.reflect.jvm.internal.impl.types.Variance r1 = kotlin.reflect.jvm.internal.impl.types.Variance.INVARIANT     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = $SwitchMap$org$jetbrains$kotlin$types$Variance     // Catch:{ NoSuchFieldError -> 0x001f }
                kotlin.reflect.jvm.internal.impl.types.Variance r1 = kotlin.reflect.jvm.internal.impl.types.Variance.IN_VARIANCE     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = $SwitchMap$org$jetbrains$kotlin$types$Variance     // Catch:{ NoSuchFieldError -> 0x002a }
                kotlin.reflect.jvm.internal.impl.types.Variance r1 = kotlin.reflect.jvm.internal.impl.types.Variance.OUT_VARIANCE     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.types.checker.TypeCheckingProcedure.C31421.<clinit>():void");
        }
    }

    public enum EnrichedProjectionKind {
        IN,
        OUT,
        INV,
        STAR;

        @NotNull
        public static EnrichedProjectionKind fromVariance(@NotNull Variance variance) {
            int i = C31421.$SwitchMap$org$jetbrains$kotlin$types$Variance[variance.ordinal()];
            if (i == 1) {
                return INV;
            }
            if (i == 2) {
                return IN;
            }
            if (i == 3) {
                return OUT;
            }
            throw new IllegalStateException("Unknown variance");
        }
    }

    @Nullable
    public static KotlinType findCorrespondingSupertype(@NotNull KotlinType kotlinType, @NotNull KotlinType kotlinType2) {
        return findCorrespondingSupertype(kotlinType, kotlinType2, new TypeCheckerProcedureCallbacksImpl());
    }

    @Nullable
    public static KotlinType findCorrespondingSupertype(@NotNull KotlinType kotlinType, @NotNull KotlinType kotlinType2, @NotNull TypeCheckingProcedureCallbacks typeCheckingProcedureCallbacks) {
        return UtilsKt.findCorrespondingSupertype(kotlinType, kotlinType2, typeCheckingProcedureCallbacks);
    }

    @NotNull
    private static KotlinType getOutType(@NotNull TypeParameterDescriptor typeParameterDescriptor, @NotNull TypeProjection typeProjection) {
        return typeProjection.getProjectionKind() == Variance.IN_VARIANCE || typeParameterDescriptor.getVariance() == Variance.IN_VARIANCE ? DescriptorUtilsKt.getBuiltIns(typeParameterDescriptor).getNullableAnyType() : typeProjection.getType();
    }

    @NotNull
    private static KotlinType getInType(@NotNull TypeParameterDescriptor typeParameterDescriptor, @NotNull TypeProjection typeProjection) {
        return typeProjection.getProjectionKind() == Variance.OUT_VARIANCE || typeParameterDescriptor.getVariance() == Variance.OUT_VARIANCE ? DescriptorUtilsKt.getBuiltIns(typeParameterDescriptor).getNothingType() : typeProjection.getType();
    }

    public TypeCheckingProcedure(TypeCheckingProcedureCallbacks typeCheckingProcedureCallbacks) {
        this.constraints = typeCheckingProcedureCallbacks;
    }

    public boolean equalTypes(@NotNull KotlinType kotlinType, @NotNull KotlinType kotlinType2) {
        boolean z = true;
        if (kotlinType == kotlinType2) {
            return true;
        }
        if (FlexibleTypesKt.isFlexible(kotlinType)) {
            if (!FlexibleTypesKt.isFlexible(kotlinType2)) {
                return heterogeneousEquivalence(kotlinType2, kotlinType);
            }
            if (KotlinTypeKt.isError(kotlinType) || KotlinTypeKt.isError(kotlinType2) || !isSubtypeOf(kotlinType, kotlinType2) || !isSubtypeOf(kotlinType2, kotlinType)) {
                z = false;
            }
            return z;
        } else if (FlexibleTypesKt.isFlexible(kotlinType2)) {
            return heterogeneousEquivalence(kotlinType, kotlinType2);
        } else {
            if (kotlinType.isMarkedNullable() != kotlinType2.isMarkedNullable()) {
                return false;
            }
            if (kotlinType.isMarkedNullable()) {
                return this.constraints.assertEqualTypes(TypeUtils.makeNotNullable(kotlinType), TypeUtils.makeNotNullable(kotlinType2), this);
            }
            TypeConstructor constructor = kotlinType.getConstructor();
            TypeConstructor constructor2 = kotlinType2.getConstructor();
            if (!this.constraints.assertEqualTypeConstructors(constructor, constructor2)) {
                return false;
            }
            List arguments = kotlinType.getArguments();
            List arguments2 = kotlinType2.getArguments();
            if (arguments.size() != arguments2.size()) {
                return false;
            }
            for (int i = 0; i < arguments.size(); i++) {
                TypeProjection typeProjection = (TypeProjection) arguments.get(i);
                TypeProjection typeProjection2 = (TypeProjection) arguments2.get(i);
                if (!typeProjection.isStarProjection() || !typeProjection2.isStarProjection()) {
                    TypeParameterDescriptor typeParameterDescriptor = (TypeParameterDescriptor) constructor.getParameters().get(i);
                    TypeParameterDescriptor typeParameterDescriptor2 = (TypeParameterDescriptor) constructor2.getParameters().get(i);
                    if (!capture(typeProjection, typeProjection2, typeParameterDescriptor) && (getEffectiveProjectionKind(typeParameterDescriptor, typeProjection) != getEffectiveProjectionKind(typeParameterDescriptor2, typeProjection2) || !this.constraints.assertEqualTypes(typeProjection.getType(), typeProjection2.getType(), this))) {
                        return false;
                    }
                }
            }
            return true;
        }
    }

    /* access modifiers changed from: protected */
    public boolean heterogeneousEquivalence(KotlinType kotlinType, KotlinType kotlinType2) {
        return isSubtypeOf(FlexibleTypesKt.asFlexibleType(kotlinType2).getLowerBound(), kotlinType) && isSubtypeOf(kotlinType, FlexibleTypesKt.asFlexibleType(kotlinType2).getUpperBound());
    }

    public static EnrichedProjectionKind getEffectiveProjectionKind(@NotNull TypeParameterDescriptor typeParameterDescriptor, @NotNull TypeProjection typeProjection) {
        Variance variance = typeParameterDescriptor.getVariance();
        Variance projectionKind = typeProjection.getProjectionKind();
        if (projectionKind == Variance.INVARIANT) {
            Variance variance2 = projectionKind;
            projectionKind = variance;
            variance = variance2;
        }
        if (variance == Variance.IN_VARIANCE && projectionKind == Variance.OUT_VARIANCE) {
            return EnrichedProjectionKind.STAR;
        }
        if (variance == Variance.OUT_VARIANCE && projectionKind == Variance.IN_VARIANCE) {
            return EnrichedProjectionKind.STAR;
        }
        return EnrichedProjectionKind.fromVariance(projectionKind);
    }

    public boolean isSubtypeOf(@NotNull KotlinType kotlinType, @NotNull KotlinType kotlinType2) {
        if (TypeCapabilitiesKt.sameTypeConstructors(kotlinType, kotlinType2)) {
            return !kotlinType.isMarkedNullable() || kotlinType2.isMarkedNullable();
        }
        KotlinType subtypeRepresentative = TypeCapabilitiesKt.getSubtypeRepresentative(kotlinType);
        KotlinType supertypeRepresentative = TypeCapabilitiesKt.getSupertypeRepresentative(kotlinType2);
        if (subtypeRepresentative == kotlinType && supertypeRepresentative == kotlinType2) {
            return isSubtypeOfForRepresentatives(kotlinType, kotlinType2);
        }
        return isSubtypeOf(subtypeRepresentative, supertypeRepresentative);
    }

    private boolean isSubtypeOfForRepresentatives(KotlinType kotlinType, KotlinType kotlinType2) {
        if (KotlinTypeKt.isError(kotlinType) || KotlinTypeKt.isError(kotlinType2)) {
            return true;
        }
        if (!kotlinType2.isMarkedNullable() && kotlinType.isMarkedNullable()) {
            return false;
        }
        if (KotlinBuiltIns.isNothingOrNullableNothing(kotlinType)) {
            return true;
        }
        KotlinType findCorrespondingSupertype = findCorrespondingSupertype(kotlinType, kotlinType2, this.constraints);
        if (findCorrespondingSupertype == null) {
            return this.constraints.noCorrespondingSupertype(kotlinType, kotlinType2);
        }
        if (kotlinType2.isMarkedNullable() || !findCorrespondingSupertype.isMarkedNullable()) {
            return checkSubtypeForTheSameConstructor(findCorrespondingSupertype, kotlinType2);
        }
        return false;
    }

    private boolean checkSubtypeForTheSameConstructor(@NotNull KotlinType kotlinType, @NotNull KotlinType kotlinType2) {
        TypeConstructor constructor = kotlinType.getConstructor();
        List arguments = kotlinType.getArguments();
        List arguments2 = kotlinType2.getArguments();
        if (arguments.size() != arguments2.size()) {
            return false;
        }
        List parameters = constructor.getParameters();
        int i = 0;
        while (true) {
            boolean z = true;
            if (i >= parameters.size()) {
                return true;
            }
            TypeParameterDescriptor typeParameterDescriptor = (TypeParameterDescriptor) parameters.get(i);
            TypeProjection typeProjection = (TypeProjection) arguments2.get(i);
            TypeProjection typeProjection2 = (TypeProjection) arguments.get(i);
            if (!typeProjection.isStarProjection() && !capture(typeProjection2, typeProjection, typeParameterDescriptor)) {
                if (!KotlinTypeKt.isError(typeProjection2.getType()) && !KotlinTypeKt.isError(typeProjection.getType())) {
                    z = false;
                }
                if (z || typeParameterDescriptor.getVariance() != Variance.INVARIANT || typeProjection2.getProjectionKind() != Variance.INVARIANT || typeProjection.getProjectionKind() != Variance.INVARIANT) {
                    KotlinType outType = getOutType(typeParameterDescriptor, typeProjection);
                    if (!this.constraints.assertSubtype(getOutType(typeParameterDescriptor, typeProjection2), outType, this)) {
                        return false;
                    }
                    KotlinType inType = getInType(typeParameterDescriptor, typeProjection);
                    KotlinType inType2 = getInType(typeParameterDescriptor, typeProjection2);
                    if (typeProjection.getProjectionKind() != Variance.OUT_VARIANCE && !this.constraints.assertSubtype(inType, inType2, this)) {
                        return false;
                    }
                } else if (!this.constraints.assertEqualTypes(typeProjection2.getType(), typeProjection.getType(), this)) {
                    return false;
                }
            }
            i++;
        }
    }

    private boolean capture(@NotNull TypeProjection typeProjection, @NotNull TypeProjection typeProjection2, @NotNull TypeParameterDescriptor typeParameterDescriptor) {
        if (typeParameterDescriptor.getVariance() == Variance.INVARIANT && typeProjection.getProjectionKind() != Variance.INVARIANT && typeProjection2.getProjectionKind() == Variance.INVARIANT) {
            return this.constraints.capture(typeProjection2.getType(), typeProjection);
        }
        return false;
    }
}
