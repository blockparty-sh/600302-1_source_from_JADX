package kotlin.reflect.jvm.internal.impl.types;

import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.functions.Function1;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.CompositeAnnotations;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.FilteredAnnotations;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.resolve.calls.inference.CapturedTypeConstructorKt;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.typesApproximation.CapturedTypeApproximationKt;
import kotlin.reflect.jvm.internal.impl.utils.ExceptionUtilsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TypeSubstitutor {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final TypeSubstitutor EMPTY = create(TypeSubstitution.EMPTY);
    @NotNull
    private final TypeSubstitution substitution;

    /* renamed from: kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor$2 */
    static /* synthetic */ class C31402 {

        /* renamed from: $SwitchMap$org$jetbrains$kotlin$types$TypeSubstitutor$VarianceConflictType */
        static final /* synthetic */ int[] f725x4790888d = new int[VarianceConflictType.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|8) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        static {
            /*
                kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor$VarianceConflictType[] r0 = kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor.VarianceConflictType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f725x4790888d = r0
                int[] r0 = f725x4790888d     // Catch:{ NoSuchFieldError -> 0x0014 }
                kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor$VarianceConflictType r1 = kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor.VarianceConflictType.OUT_IN_IN_POSITION     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = f725x4790888d     // Catch:{ NoSuchFieldError -> 0x001f }
                kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor$VarianceConflictType r1 = kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor.VarianceConflictType.IN_IN_OUT_POSITION     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = f725x4790888d     // Catch:{ NoSuchFieldError -> 0x002a }
                kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor$VarianceConflictType r1 = kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor.VarianceConflictType.NO_CONFLICT     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor.C31402.<clinit>():void");
        }
    }

    private static final class SubstitutionException extends Exception {
        public SubstitutionException(String str) {
            super(str);
        }
    }

    private enum VarianceConflictType {
        NO_CONFLICT,
        IN_IN_OUT_POSITION,
        OUT_IN_IN_POSITION
    }

    @NotNull
    public static TypeSubstitutor create(@NotNull TypeSubstitution typeSubstitution) {
        return new TypeSubstitutor(typeSubstitution);
    }

    @NotNull
    public static TypeSubstitutor createChainedSubstitutor(@NotNull TypeSubstitution typeSubstitution, @NotNull TypeSubstitution typeSubstitution2) {
        return create(DisjointKeysUnionTypeSubstitution.create(typeSubstitution, typeSubstitution2));
    }

    @NotNull
    public static TypeSubstitutor create(@NotNull KotlinType kotlinType) {
        return create(TypeConstructorSubstitution.create(kotlinType.getConstructor(), kotlinType.getArguments()));
    }

    protected TypeSubstitutor(@NotNull TypeSubstitution typeSubstitution) {
        this.substitution = typeSubstitution;
    }

    public boolean isEmpty() {
        return this.substitution.isEmpty();
    }

    @NotNull
    public TypeSubstitution getSubstitution() {
        return this.substitution;
    }

    @NotNull
    public KotlinType safeSubstitute(@NotNull KotlinType kotlinType, @NotNull Variance variance) {
        if (isEmpty()) {
            return kotlinType;
        }
        try {
            return unsafeSubstitute(new TypeProjectionImpl(variance, kotlinType), 0).getType();
        } catch (SubstitutionException e) {
            return ErrorUtils.createErrorType(e.getMessage());
        }
    }

    @Nullable
    public KotlinType substitute(@NotNull KotlinType kotlinType, @NotNull Variance variance) {
        TypeProjection substitute = substitute(new TypeProjectionImpl(variance, getSubstitution().prepareTopLevelType(kotlinType, variance)));
        if (substitute == null) {
            return null;
        }
        return substitute.getType();
    }

    @Nullable
    public TypeProjection substitute(@NotNull TypeProjection typeProjection) {
        TypeProjection substituteWithoutApproximation = substituteWithoutApproximation(typeProjection);
        if (this.substitution.approximateCapturedTypes() || this.substitution.approximateContravariantCapturedTypes()) {
            return CapturedTypeApproximationKt.approximateCapturedTypesIfNecessary(substituteWithoutApproximation, this.substitution.approximateContravariantCapturedTypes());
        }
        return substituteWithoutApproximation;
    }

    @Nullable
    public TypeProjection substituteWithoutApproximation(@NotNull TypeProjection typeProjection) {
        if (isEmpty()) {
            return typeProjection;
        }
        try {
            return unsafeSubstitute(typeProjection, 0);
        } catch (SubstitutionException unused) {
            return null;
        }
    }

    @NotNull
    private TypeProjection unsafeSubstitute(@NotNull TypeProjection typeProjection, int i) throws SubstitutionException {
        KotlinType kotlinType;
        assertRecursionDepth(i, typeProjection, this.substitution);
        if (typeProjection.isStarProjection()) {
            return typeProjection;
        }
        KotlinType type = typeProjection.getType();
        if (type instanceof TypeWithEnhancement) {
            TypeWithEnhancement typeWithEnhancement = (TypeWithEnhancement) type;
            UnwrappedType origin = typeWithEnhancement.getOrigin();
            KotlinType enhancement = typeWithEnhancement.getEnhancement();
            TypeProjection unsafeSubstitute = unsafeSubstitute(new TypeProjectionImpl(typeProjection.getProjectionKind(), origin), i + 1);
            return new TypeProjectionImpl(unsafeSubstitute.getProjectionKind(), TypeWithEnhancementKt.wrapEnhancement(unsafeSubstitute.getType().unwrap(), substitute(enhancement, typeProjection.getProjectionKind())));
        }
        if (!DynamicTypesKt.isDynamic(type) && !(type.unwrap() instanceof RawType)) {
            TypeProjection typeProjection2 = this.substitution.get(type);
            Variance projectionKind = typeProjection.getProjectionKind();
            if (typeProjection2 == null && FlexibleTypesKt.isFlexible(type) && !TypeCapabilitiesKt.isCustomTypeVariable(type)) {
                FlexibleType asFlexibleType = FlexibleTypesKt.asFlexibleType(type);
                int i2 = i + 1;
                TypeProjection unsafeSubstitute2 = unsafeSubstitute(new TypeProjectionImpl(projectionKind, asFlexibleType.getLowerBound()), i2);
                TypeProjection unsafeSubstitute3 = unsafeSubstitute(new TypeProjectionImpl(projectionKind, asFlexibleType.getUpperBound()), i2);
                Variance projectionKind2 = unsafeSubstitute2.getProjectionKind();
                if (unsafeSubstitute2.getType() == asFlexibleType.getLowerBound() && unsafeSubstitute3.getType() == asFlexibleType.getUpperBound()) {
                    return typeProjection;
                }
                return new TypeProjectionImpl(projectionKind2, KotlinTypeFactory.flexibleType(TypeSubstitutionKt.asSimpleType(unsafeSubstitute2.getType()), TypeSubstitutionKt.asSimpleType(unsafeSubstitute3.getType())));
            } else if (!KotlinBuiltIns.isNothing(type) && !KotlinTypeKt.isError(type)) {
                if (typeProjection2 != null) {
                    VarianceConflictType conflictType = conflictType(projectionKind, typeProjection2.getProjectionKind());
                    if (!CapturedTypeConstructorKt.isCaptured(type)) {
                        int i3 = C31402.f725x4790888d[conflictType.ordinal()];
                        if (i3 == 1) {
                            throw new SubstitutionException("Out-projection in in-position");
                        } else if (i3 == 2) {
                            return new TypeProjectionImpl(Variance.OUT_VARIANCE, type.getConstructor().getBuiltIns().getNullableAnyType());
                        }
                    }
                    CustomTypeVariable customTypeVariable = TypeCapabilitiesKt.getCustomTypeVariable(type);
                    if (typeProjection2.isStarProjection()) {
                        return typeProjection2;
                    }
                    if (customTypeVariable != null) {
                        kotlinType = customTypeVariable.substitutionResult(typeProjection2.getType());
                    } else {
                        kotlinType = TypeUtils.makeNullableIfNeeded(typeProjection2.getType(), type.isMarkedNullable());
                    }
                    if (!type.getAnnotations().isEmpty()) {
                        kotlinType = TypeUtilsKt.replaceAnnotations(kotlinType, new CompositeAnnotations(kotlinType.getAnnotations(), filterOutUnsafeVariance(this.substitution.filterAnnotations(type.getAnnotations()))));
                    }
                    if (conflictType == VarianceConflictType.NO_CONFLICT) {
                        projectionKind = combine(projectionKind, typeProjection2.getProjectionKind());
                    }
                    return new TypeProjectionImpl(projectionKind, kotlinType);
                }
                typeProjection = substituteCompoundType(typeProjection, i);
            }
        }
        return typeProjection;
    }

    @NotNull
    private static Annotations filterOutUnsafeVariance(@NotNull Annotations annotations) {
        if (!annotations.hasAnnotation(KotlinBuiltIns.FQ_NAMES.unsafeVariance)) {
            return annotations;
        }
        return new FilteredAnnotations(annotations, new Function1<FqName, Boolean>() {
            public Boolean invoke(@NotNull FqName fqName) {
                return Boolean.valueOf(!fqName.equals(KotlinBuiltIns.FQ_NAMES.unsafeVariance));
            }
        });
    }

    private TypeProjection substituteCompoundType(TypeProjection typeProjection, int i) throws SubstitutionException {
        KotlinType type = typeProjection.getType();
        Variance projectionKind = typeProjection.getProjectionKind();
        if (type.getConstructor().getDeclarationDescriptor() instanceof TypeParameterDescriptor) {
            return typeProjection;
        }
        KotlinType kotlinType = null;
        SimpleType abbreviation = SpecialTypesKt.getAbbreviation(type);
        if (abbreviation != null) {
            kotlinType = substitute(abbreviation, Variance.INVARIANT);
        }
        KotlinType replace = TypeSubstitutionKt.replace(type, substituteTypeArguments(type.getConstructor().getParameters(), type.getArguments(), i), this.substitution.filterAnnotations(type.getAnnotations()));
        if ((replace instanceof SimpleType) && (kotlinType instanceof SimpleType)) {
            replace = SpecialTypesKt.withAbbreviation((SimpleType) replace, (SimpleType) kotlinType);
        }
        return new TypeProjectionImpl(projectionKind, replace);
    }

    private List<TypeProjection> substituteTypeArguments(List<TypeParameterDescriptor> list, List<TypeProjection> list2, int i) throws SubstitutionException {
        ArrayList arrayList = new ArrayList(list.size());
        boolean z = false;
        for (int i2 = 0; i2 < list.size(); i2++) {
            TypeParameterDescriptor typeParameterDescriptor = (TypeParameterDescriptor) list.get(i2);
            TypeProjection typeProjection = (TypeProjection) list2.get(i2);
            TypeProjection unsafeSubstitute = unsafeSubstitute(typeProjection, i + 1);
            int i3 = C31402.f725x4790888d[conflictType(typeParameterDescriptor.getVariance(), unsafeSubstitute.getProjectionKind()).ordinal()];
            if (i3 == 1 || i3 == 2) {
                unsafeSubstitute = TypeUtils.makeStarProjection(typeParameterDescriptor);
            } else if (i3 == 3 && typeParameterDescriptor.getVariance() != Variance.INVARIANT && !unsafeSubstitute.isStarProjection()) {
                unsafeSubstitute = new TypeProjectionImpl(Variance.INVARIANT, unsafeSubstitute.getType());
            }
            if (unsafeSubstitute != typeProjection) {
                z = true;
            }
            arrayList.add(unsafeSubstitute);
        }
        return !z ? list2 : arrayList;
    }

    @NotNull
    public static Variance combine(@NotNull Variance variance, @NotNull TypeProjection typeProjection) {
        if (typeProjection.isStarProjection()) {
            return Variance.OUT_VARIANCE;
        }
        return combine(variance, typeProjection.getProjectionKind());
    }

    @NotNull
    public static Variance combine(@NotNull Variance variance, @NotNull Variance variance2) {
        if (variance == Variance.INVARIANT) {
            return variance2;
        }
        if (variance2 == Variance.INVARIANT) {
            return variance;
        }
        if (variance == variance2) {
            return variance2;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Variance conflict: type parameter variance '");
        sb.append(variance);
        sb.append("' and ");
        sb.append("projection kind '");
        sb.append(variance2);
        sb.append("' cannot be combined");
        throw new AssertionError(sb.toString());
    }

    private static VarianceConflictType conflictType(Variance variance, Variance variance2) {
        if (variance == Variance.IN_VARIANCE && variance2 == Variance.OUT_VARIANCE) {
            return VarianceConflictType.OUT_IN_IN_POSITION;
        }
        if (variance == Variance.OUT_VARIANCE && variance2 == Variance.IN_VARIANCE) {
            return VarianceConflictType.IN_IN_OUT_POSITION;
        }
        return VarianceConflictType.NO_CONFLICT;
    }

    private static void assertRecursionDepth(int i, TypeProjection typeProjection, TypeSubstitution typeSubstitution) {
        if (i > 100) {
            StringBuilder sb = new StringBuilder();
            sb.append("Recursion too deep. Most likely infinite loop while substituting ");
            sb.append(safeToString(typeProjection));
            sb.append("; substitution: ");
            sb.append(safeToString(typeSubstitution));
            throw new IllegalStateException(sb.toString());
        }
    }

    private static String safeToString(Object obj) {
        try {
            return obj.toString();
        } catch (Throwable th) {
            if (!ExceptionUtilsKt.isProcessCanceledException(th)) {
                StringBuilder sb = new StringBuilder();
                sb.append("[Exception while computing toString(): ");
                sb.append(th);
                sb.append("]");
                return sb.toString();
            }
            throw ((RuntimeException) th);
        }
    }
}
