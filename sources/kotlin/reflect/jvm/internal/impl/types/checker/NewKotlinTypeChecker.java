package kotlin.reflect.jvm.internal.impl.types.checker;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.NoWhenBranchMatchedException;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassKind;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ModalityKt;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.resolve.calls.inference.CapturedTypeConstructor;
import kotlin.reflect.jvm.internal.impl.resolve.constants.IntegerValueTypeConstructor;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.types.DynamicTypesKt;
import kotlin.reflect.jvm.internal.impl.types.FlexibleType;
import kotlin.reflect.jvm.internal.impl.types.FlexibleTypesKt;
import kotlin.reflect.jvm.internal.impl.types.IntersectionTypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeKt;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.SpecialTypesKt;
import kotlin.reflect.jvm.internal.impl.types.StubType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructorSubstitution;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import kotlin.reflect.jvm.internal.impl.types.TypeWithEnhancementKt;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.checker.TypeCheckerContext.LowerCapturedTypePolicy;
import kotlin.reflect.jvm.internal.impl.types.checker.TypeCheckerContext.SeveralSupertypesWithSameConstructorPolicy;
import kotlin.reflect.jvm.internal.impl.types.checker.TypeCheckerContext.SupertypesPolicy;
import kotlin.reflect.jvm.internal.impl.types.checker.TypeCheckerContext.SupertypesPolicy.LowerIfFlexible;
import kotlin.reflect.jvm.internal.impl.types.checker.TypeCheckerContext.SupertypesPolicy.LowerIfFlexibleWithCustomSubstitutor;
import kotlin.reflect.jvm.internal.impl.types.checker.TypeCheckerContext.SupertypesPolicy.None;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import kotlin.reflect.jvm.internal.impl.utils.SmartList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: NewKotlinTypeChecker.kt */
public final class NewKotlinTypeChecker implements KotlinTypeChecker {
    public static final NewKotlinTypeChecker INSTANCE = new NewKotlinTypeChecker();

    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[LowerCapturedTypePolicy.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1 = new int[SeveralSupertypesWithSameConstructorPolicy.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$2 = new int[Variance.values().length];

        static {
            $EnumSwitchMapping$0[LowerCapturedTypePolicy.CHECK_ONLY_LOWER.ordinal()] = 1;
            $EnumSwitchMapping$0[LowerCapturedTypePolicy.CHECK_SUBTYPE_AND_LOWER.ordinal()] = 2;
            $EnumSwitchMapping$0[LowerCapturedTypePolicy.SKIP_LOWER.ordinal()] = 3;
            $EnumSwitchMapping$1[SeveralSupertypesWithSameConstructorPolicy.FORCE_NOT_SUBTYPE.ordinal()] = 1;
            $EnumSwitchMapping$1[SeveralSupertypesWithSameConstructorPolicy.TAKE_FIRST_FOR_SUBTYPING.ordinal()] = 2;
            $EnumSwitchMapping$1[SeveralSupertypesWithSameConstructorPolicy.CHECK_ANY_OF_THEM.ordinal()] = 3;
            $EnumSwitchMapping$1[SeveralSupertypesWithSameConstructorPolicy.INTERSECT_ARGUMENTS_AND_CHECK_AGAIN.ordinal()] = 4;
            $EnumSwitchMapping$2[Variance.INVARIANT.ordinal()] = 1;
            $EnumSwitchMapping$2[Variance.OUT_VARIANCE.ordinal()] = 2;
            $EnumSwitchMapping$2[Variance.IN_VARIANCE.ordinal()] = 3;
        }
    }

    private NewKotlinTypeChecker() {
    }

    public boolean isSubtypeOf(@NotNull KotlinType kotlinType, @NotNull KotlinType kotlinType2) {
        Intrinsics.checkParameterIsNotNull(kotlinType, "subtype");
        Intrinsics.checkParameterIsNotNull(kotlinType2, "supertype");
        return isSubtypeOf(new TypeCheckerContext(true, false, 2, null), kotlinType.unwrap(), kotlinType2.unwrap());
    }

    public boolean equalTypes(@NotNull KotlinType kotlinType, @NotNull KotlinType kotlinType2) {
        Intrinsics.checkParameterIsNotNull(kotlinType, "a");
        Intrinsics.checkParameterIsNotNull(kotlinType2, "b");
        return equalTypes(new TypeCheckerContext(false, false, 2, null), kotlinType.unwrap(), kotlinType2.unwrap());
    }

    public final boolean equalTypes(@NotNull TypeCheckerContext typeCheckerContext, @NotNull UnwrappedType unwrappedType, @NotNull UnwrappedType unwrappedType2) {
        Intrinsics.checkParameterIsNotNull(typeCheckerContext, "$receiver");
        Intrinsics.checkParameterIsNotNull(unwrappedType, "a");
        Intrinsics.checkParameterIsNotNull(unwrappedType2, "b");
        boolean z = true;
        if (unwrappedType == unwrappedType2) {
            return true;
        }
        KotlinType kotlinType = unwrappedType;
        if (isCommonDenotableType(kotlinType)) {
            KotlinType kotlinType2 = unwrappedType2;
            if (isCommonDenotableType(kotlinType2)) {
                if (!typeCheckerContext.areEqualTypeConstructors(unwrappedType.getConstructor(), unwrappedType2.getConstructor())) {
                    return false;
                }
                if (unwrappedType.getArguments().isEmpty()) {
                    if (!hasFlexibleNullability(kotlinType) && !hasFlexibleNullability(kotlinType2) && unwrappedType.isMarkedNullable() != unwrappedType2.isMarkedNullable()) {
                        z = false;
                    }
                    return z;
                }
            }
        }
        if (!isSubtypeOf(typeCheckerContext, unwrappedType, unwrappedType2) || !isSubtypeOf(typeCheckerContext, unwrappedType2, unwrappedType)) {
            z = false;
        }
        return z;
    }

    private final boolean hasFlexibleNullability(@NotNull KotlinType kotlinType) {
        return FlexibleTypesKt.lowerIfFlexible(kotlinType).isMarkedNullable() != FlexibleTypesKt.upperIfFlexible(kotlinType).isMarkedNullable();
    }

    private final boolean isCommonDenotableType(KotlinType kotlinType) {
        return kotlinType.getConstructor().isDenotable() && !DynamicTypesKt.isDynamic(kotlinType) && !SpecialTypesKt.isDefinitelyNotNullType(kotlinType) && Intrinsics.areEqual((Object) FlexibleTypesKt.lowerIfFlexible(kotlinType).getConstructor(), (Object) FlexibleTypesKt.upperIfFlexible(kotlinType).getConstructor());
    }

    public final boolean isSubtypeOf(@NotNull TypeCheckerContext typeCheckerContext, @NotNull UnwrappedType unwrappedType, @NotNull UnwrappedType unwrappedType2) {
        Intrinsics.checkParameterIsNotNull(typeCheckerContext, "$receiver");
        Intrinsics.checkParameterIsNotNull(unwrappedType, "subType");
        Intrinsics.checkParameterIsNotNull(unwrappedType2, "superType");
        if (unwrappedType == unwrappedType2) {
            return true;
        }
        UnwrappedType transformToNewType = transformToNewType(unwrappedType);
        UnwrappedType transformToNewType2 = transformToNewType(unwrappedType2);
        KotlinType kotlinType = transformToNewType;
        KotlinType kotlinType2 = transformToNewType2;
        Boolean checkSubtypeForSpecialCases = checkSubtypeForSpecialCases(typeCheckerContext, FlexibleTypesKt.lowerIfFlexible(kotlinType), FlexibleTypesKt.upperIfFlexible(kotlinType2));
        if (checkSubtypeForSpecialCases != null) {
            boolean booleanValue = checkSubtypeForSpecialCases.booleanValue();
            typeCheckerContext.addSubtypeConstraint(transformToNewType, transformToNewType2);
            return booleanValue;
        }
        Boolean addSubtypeConstraint = typeCheckerContext.addSubtypeConstraint(transformToNewType, transformToNewType2);
        if (addSubtypeConstraint != null) {
            return addSubtypeConstraint.booleanValue();
        }
        return isSubtypeOfForSingleClassifierType(typeCheckerContext, FlexibleTypesKt.lowerIfFlexible(kotlinType), FlexibleTypesKt.upperIfFlexible(kotlinType2));
    }

    @NotNull
    public final SimpleType transformToNewType(@NotNull SimpleType simpleType) {
        Intrinsics.checkParameterIsNotNull(simpleType, "type");
        TypeConstructor constructor = simpleType.getConstructor();
        boolean z = false;
        if (constructor instanceof CapturedTypeConstructor) {
            CapturedTypeConstructor capturedTypeConstructor = (CapturedTypeConstructor) constructor;
            TypeProjection typeProjection = capturedTypeConstructor.getTypeProjection();
            if (typeProjection.getProjectionKind() == Variance.IN_VARIANCE) {
                z = true;
            }
            UnwrappedType unwrappedType = null;
            if (!z) {
                typeProjection = null;
            }
            if (typeProjection != null) {
                KotlinType type = typeProjection.getType();
                if (type != null) {
                    unwrappedType = type.unwrap();
                }
            }
            UnwrappedType unwrappedType2 = unwrappedType;
            if (capturedTypeConstructor.getNewTypeConstructor() == null) {
                TypeProjection typeProjection2 = capturedTypeConstructor.getTypeProjection();
                Iterable<KotlinType> supertypes = capturedTypeConstructor.getSupertypes();
                Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(supertypes, 10));
                for (KotlinType unwrap : supertypes) {
                    arrayList.add(unwrap.unwrap());
                }
                capturedTypeConstructor.setNewTypeConstructor(new NewCapturedTypeConstructor(typeProjection2, (List) arrayList));
            }
            CaptureStatus captureStatus = CaptureStatus.FOR_SUBTYPING;
            NewCapturedTypeConstructor newTypeConstructor = capturedTypeConstructor.getNewTypeConstructor();
            if (newTypeConstructor == null) {
                Intrinsics.throwNpe();
            }
            NewCapturedType newCapturedType = new NewCapturedType(captureStatus, newTypeConstructor, unwrappedType2, simpleType.getAnnotations(), simpleType.isMarkedNullable());
            return newCapturedType;
        } else if (constructor instanceof IntegerValueTypeConstructor) {
            Iterable<KotlinType> supertypes2 = ((IntegerValueTypeConstructor) constructor).getSupertypes();
            Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(supertypes2, 10));
            for (KotlinType makeNullableAsSpecified : supertypes2) {
                arrayList2.add(TypeUtils.makeNullableAsSpecified(makeNullableAsSpecified, simpleType.isMarkedNullable()));
            }
            return KotlinTypeFactory.simpleTypeWithNonTrivialMemberScope(simpleType.getAnnotations(), new IntersectionTypeConstructor((List) arrayList2), CollectionsKt.emptyList(), false, simpleType.getMemberScope());
        } else {
            if ((constructor instanceof IntersectionTypeConstructor) && simpleType.isMarkedNullable()) {
                Collection supertypes3 = ((IntersectionTypeConstructor) constructor).getSupertypes();
                Intrinsics.checkExpressionValueIsNotNull(supertypes3, "constructor.supertypes");
                Iterable<KotlinType> iterable = supertypes3;
                Collection arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
                for (KotlinType kotlinType : iterable) {
                    Intrinsics.checkExpressionValueIsNotNull(kotlinType, "it");
                    arrayList3.add(TypeUtilsKt.makeNullable(kotlinType));
                }
                IntersectionTypeConstructor intersectionTypeConstructor = new IntersectionTypeConstructor((List) arrayList3);
                Annotations annotations = simpleType.getAnnotations();
                TypeConstructor typeConstructor = intersectionTypeConstructor;
                List emptyList = CollectionsKt.emptyList();
                MemberScope createScopeForKotlinType = intersectionTypeConstructor.createScopeForKotlinType();
                Intrinsics.checkExpressionValueIsNotNull(createScopeForKotlinType, "newConstructor.createScopeForKotlinType()");
                simpleType = KotlinTypeFactory.simpleTypeWithNonTrivialMemberScope(annotations, typeConstructor, emptyList, false, createScopeForKotlinType);
            }
            return simpleType;
        }
    }

    @NotNull
    public final UnwrappedType transformToNewType(@NotNull UnwrappedType unwrappedType) {
        UnwrappedType unwrappedType2;
        Intrinsics.checkParameterIsNotNull(unwrappedType, "type");
        if (unwrappedType instanceof SimpleType) {
            unwrappedType2 = transformToNewType((SimpleType) unwrappedType);
        } else if (unwrappedType instanceof FlexibleType) {
            FlexibleType flexibleType = (FlexibleType) unwrappedType;
            SimpleType transformToNewType = transformToNewType(flexibleType.getLowerBound());
            SimpleType transformToNewType2 = transformToNewType(flexibleType.getUpperBound());
            if (transformToNewType == flexibleType.getLowerBound() && transformToNewType2 == flexibleType.getUpperBound()) {
                unwrappedType2 = unwrappedType;
            } else {
                unwrappedType2 = KotlinTypeFactory.flexibleType(transformToNewType, transformToNewType2);
            }
        } else {
            throw new NoWhenBranchMatchedException();
        }
        return TypeWithEnhancementKt.inheritEnhancement(unwrappedType2, unwrappedType);
    }

    private final Boolean checkSubtypeForSpecialCases(@NotNull TypeCheckerContext typeCheckerContext, SimpleType simpleType, SimpleType simpleType2) {
        boolean isError = KotlinTypeKt.isError(simpleType);
        boolean z = true;
        Boolean valueOf = Boolean.valueOf(true);
        if (isError || KotlinTypeKt.isError(simpleType2)) {
            if (typeCheckerContext.getErrorTypeEqualsToAnything()) {
                return valueOf;
            }
            if (!simpleType.isMarkedNullable() || simpleType2.isMarkedNullable()) {
                return Boolean.valueOf(StrictEqualityTypeChecker.INSTANCE.strictEqualTypes(simpleType.makeNullableAsSpecified(false), simpleType2.makeNullableAsSpecified(false)));
            }
            return Boolean.valueOf(false);
        } else if ((simpleType instanceof StubType) || (simpleType2 instanceof StubType)) {
            return valueOf;
        } else {
            if (simpleType2 instanceof NewCapturedType) {
                NewCapturedType newCapturedType = (NewCapturedType) simpleType2;
                if (newCapturedType.getLowerType() != null) {
                    int i = WhenMappings.$EnumSwitchMapping$0[typeCheckerContext.getLowerCapturedTypePolicy(simpleType, newCapturedType).ordinal()];
                    if (i == 1) {
                        return Boolean.valueOf(isSubtypeOf(typeCheckerContext, simpleType, newCapturedType.getLowerType()));
                    }
                    if (i == 2 && isSubtypeOf(typeCheckerContext, simpleType, newCapturedType.getLowerType())) {
                        return valueOf;
                    }
                }
            }
            TypeConstructor constructor = simpleType2.getConstructor();
            if (!(constructor instanceof IntersectionTypeConstructor)) {
                constructor = null;
            }
            IntersectionTypeConstructor intersectionTypeConstructor = (IntersectionTypeConstructor) constructor;
            if (intersectionTypeConstructor == null) {
                return null;
            }
            boolean z2 = !simpleType2.isMarkedNullable();
            if (!_Assertions.ENABLED || z2) {
                Collection supertypes = intersectionTypeConstructor.getSupertypes();
                Intrinsics.checkExpressionValueIsNotNull(supertypes, "it.supertypes");
                Iterable iterable = supertypes;
                if (!(iterable instanceof Collection) || !((Collection) iterable).isEmpty()) {
                    Iterator it = iterable.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        if (!INSTANCE.isSubtypeOf(typeCheckerContext, simpleType, ((KotlinType) it.next()).unwrap())) {
                            z = false;
                            break;
                        }
                    }
                }
                return Boolean.valueOf(z);
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Intersection type should not be marked nullable!: ");
            sb.append(simpleType2);
            throw new AssertionError(sb.toString());
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:58:0x00f7 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final boolean isSubtypeOfForSingleClassifierType(@org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.types.checker.TypeCheckerContext r17, kotlin.reflect.jvm.internal.impl.types.SimpleType r18, kotlin.reflect.jvm.internal.impl.types.SimpleType r19) {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            r2 = r18
            r3 = r19
            boolean r4 = kotlin.reflect.jvm.internal.impl.types.checker.NewKotlinTypeCheckerKt.isSingleClassifierType(r18)
            r5 = 0
            r6 = 1
            if (r4 != 0) goto L_0x0022
            boolean r4 = kotlin.reflect.jvm.internal.impl.types.checker.NewKotlinTypeCheckerKt.isIntersectionType(r18)
            if (r4 != 0) goto L_0x0022
            r4 = r2
            kotlin.reflect.jvm.internal.impl.types.UnwrappedType r4 = (kotlin.reflect.jvm.internal.impl.types.UnwrappedType) r4
            boolean r4 = r1.isAllowedTypeVariable(r4)
            if (r4 == 0) goto L_0x0020
            goto L_0x0022
        L_0x0020:
            r4 = 0
            goto L_0x0023
        L_0x0022:
            r4 = 1
        L_0x0023:
            boolean r7 = kotlin._Assertions.ENABLED
            if (r7 == 0) goto L_0x0043
            if (r4 == 0) goto L_0x002a
            goto L_0x0043
        L_0x002a:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r3 = "Not singleClassifierType and not intersection subType: "
            r1.append(r3)
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            java.lang.AssertionError r2 = new java.lang.AssertionError
            r2.<init>(r1)
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            throw r2
        L_0x0043:
            boolean r4 = kotlin.reflect.jvm.internal.impl.types.checker.NewKotlinTypeCheckerKt.isSingleClassifierType(r19)
            if (r4 != 0) goto L_0x0055
            r4 = r3
            kotlin.reflect.jvm.internal.impl.types.UnwrappedType r4 = (kotlin.reflect.jvm.internal.impl.types.UnwrappedType) r4
            boolean r4 = r1.isAllowedTypeVariable(r4)
            if (r4 == 0) goto L_0x0053
            goto L_0x0055
        L_0x0053:
            r4 = 0
            goto L_0x0056
        L_0x0055:
            r4 = 1
        L_0x0056:
            boolean r7 = kotlin._Assertions.ENABLED
            if (r7 == 0) goto L_0x0076
            if (r4 == 0) goto L_0x005d
            goto L_0x0076
        L_0x005d:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Not singleClassifierType superType: "
            r1.append(r2)
            r1.append(r3)
            java.lang.String r1 = r1.toString()
            java.lang.AssertionError r2 = new java.lang.AssertionError
            r2.<init>(r1)
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            throw r2
        L_0x0076:
            kotlin.reflect.jvm.internal.impl.types.checker.NullabilityChecker r4 = kotlin.reflect.jvm.internal.impl.types.checker.NullabilityChecker.INSTANCE
            boolean r4 = r4.isPossibleSubtype(r1, r2, r3)
            if (r4 != 0) goto L_0x007f
            return r5
        L_0x007f:
            kotlin.reflect.jvm.internal.impl.types.TypeConstructor r4 = r19.getConstructor()
            kotlin.reflect.jvm.internal.impl.types.TypeConstructor r7 = r18.getConstructor()
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual(r7, r4)
            if (r7 == 0) goto L_0x0098
            java.util.List r7 = r4.getParameters()
            boolean r7 = r7.isEmpty()
            if (r7 == 0) goto L_0x0098
            return r6
        L_0x0098:
            r7 = r3
            kotlin.reflect.jvm.internal.impl.types.KotlinType r7 = (kotlin.reflect.jvm.internal.impl.types.KotlinType) r7
            boolean r7 = kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt.isAnyOrNullableAny(r7)
            if (r7 == 0) goto L_0x00a2
            return r6
        L_0x00a2:
            java.util.List r7 = r0.findCorrespondingSupertypes(r1, r2, r4)
            int r8 = r7.size()
            if (r8 == 0) goto L_0x01e2
            if (r8 == r6) goto L_0x01d3
            kotlin.reflect.jvm.internal.impl.types.checker.TypeCheckerContext$SeveralSupertypesWithSameConstructorPolicy r8 = r17.getSameConstructorPolicy()
            int[] r9 = kotlin.reflect.jvm.internal.impl.types.checker.NewKotlinTypeChecker.WhenMappings.$EnumSwitchMapping$1
            int r8 = r8.ordinal()
            r8 = r9[r8]
            if (r8 == r6) goto L_0x01d2
            r9 = 2
            if (r8 == r9) goto L_0x01c3
            r9 = 3
            if (r8 == r9) goto L_0x00c6
            r9 = 4
            if (r8 == r9) goto L_0x00c6
            goto L_0x00f8
        L_0x00c6:
            r8 = r7
            java.lang.Iterable r8 = (java.lang.Iterable) r8
            boolean r9 = r8 instanceof java.util.Collection
            if (r9 == 0) goto L_0x00d8
            r9 = r8
            java.util.Collection r9 = (java.util.Collection) r9
            boolean r9 = r9.isEmpty()
            if (r9 == 0) goto L_0x00d8
        L_0x00d6:
            r8 = 0
            goto L_0x00f5
        L_0x00d8:
            java.util.Iterator r8 = r8.iterator()
        L_0x00dc:
            boolean r9 = r8.hasNext()
            if (r9 == 0) goto L_0x00d6
            java.lang.Object r9 = r8.next()
            kotlin.reflect.jvm.internal.impl.types.SimpleType r9 = (kotlin.reflect.jvm.internal.impl.types.SimpleType) r9
            kotlin.reflect.jvm.internal.impl.types.checker.NewKotlinTypeChecker r10 = INSTANCE
            java.util.List r9 = r9.getArguments()
            boolean r9 = r10.isSubtypeForSameConstructor(r1, r9, r3)
            if (r9 == 0) goto L_0x00dc
            r8 = 1
        L_0x00f5:
            if (r8 == 0) goto L_0x00f8
            return r6
        L_0x00f8:
            kotlin.reflect.jvm.internal.impl.types.checker.TypeCheckerContext$SeveralSupertypesWithSameConstructorPolicy r8 = r17.getSameConstructorPolicy()
            kotlin.reflect.jvm.internal.impl.types.checker.TypeCheckerContext$SeveralSupertypesWithSameConstructorPolicy r9 = kotlin.reflect.jvm.internal.impl.types.checker.TypeCheckerContext.SeveralSupertypesWithSameConstructorPolicy.INTERSECT_ARGUMENTS_AND_CHECK_AGAIN
            if (r8 == r9) goto L_0x0101
            return r5
        L_0x0101:
            java.util.List r4 = r4.getParameters()
            java.lang.String r8 = "superConstructor.parameters"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r4, r8)
            java.lang.Iterable r4 = (java.lang.Iterable) r4
            java.util.ArrayList r8 = new java.util.ArrayList
            r9 = 10
            int r10 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r4, r9)
            r8.<init>(r10)
            java.util.Collection r8 = (java.util.Collection) r8
            java.util.Iterator r4 = r4.iterator()
            r10 = 0
        L_0x011e:
            boolean r11 = r4.hasNext()
            if (r11 == 0) goto L_0x01bc
            java.lang.Object r11 = r4.next()
            int r12 = r10 + 1
            kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor r11 = (kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor) r11
            r11 = r7
            java.lang.Iterable r11 = (java.lang.Iterable) r11
            java.util.ArrayList r13 = new java.util.ArrayList
            int r14 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r11, r9)
            r13.<init>(r14)
            java.util.Collection r13 = (java.util.Collection) r13
            java.util.Iterator r11 = r11.iterator()
        L_0x013e:
            boolean r14 = r11.hasNext()
            if (r14 == 0) goto L_0x01a7
            java.lang.Object r14 = r11.next()
            kotlin.reflect.jvm.internal.impl.types.SimpleType r14 = (kotlin.reflect.jvm.internal.impl.types.SimpleType) r14
            java.util.List r15 = r14.getArguments()
            java.lang.Object r15 = kotlin.collections.CollectionsKt.getOrNull(r15, r10)
            kotlin.reflect.jvm.internal.impl.types.TypeProjection r15 = (kotlin.reflect.jvm.internal.impl.types.TypeProjection) r15
            if (r15 == 0) goto L_0x017a
            kotlin.reflect.jvm.internal.impl.types.Variance r6 = r15.getProjectionKind()
            kotlin.reflect.jvm.internal.impl.types.Variance r9 = kotlin.reflect.jvm.internal.impl.types.Variance.INVARIANT
            if (r6 != r9) goto L_0x0160
            r6 = 1
            goto L_0x0161
        L_0x0160:
            r6 = 0
        L_0x0161:
            if (r6 == 0) goto L_0x0164
            goto L_0x0165
        L_0x0164:
            r15 = 0
        L_0x0165:
            if (r15 == 0) goto L_0x017a
            kotlin.reflect.jvm.internal.impl.types.KotlinType r6 = r15.getType()
            if (r6 == 0) goto L_0x017a
            kotlin.reflect.jvm.internal.impl.types.UnwrappedType r6 = r6.unwrap()
            if (r6 == 0) goto L_0x017a
            r13.add(r6)
            r6 = 1
            r9 = 10
            goto L_0x013e
        L_0x017a:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r4 = "Incorrect type: "
            r1.append(r4)
            r1.append(r14)
            java.lang.String r4 = ", subType: "
            r1.append(r4)
            r1.append(r2)
            java.lang.String r2 = ", superType: "
            r1.append(r2)
            r1.append(r3)
            java.lang.String r1 = r1.toString()
            java.lang.IllegalStateException r2 = new java.lang.IllegalStateException
            java.lang.String r1 = r1.toString()
            r2.<init>(r1)
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            throw r2
        L_0x01a7:
            java.util.List r13 = (java.util.List) r13
            kotlin.reflect.jvm.internal.impl.types.UnwrappedType r6 = kotlin.reflect.jvm.internal.impl.types.checker.IntersectionTypeKt.intersectTypes(r13)
            kotlin.reflect.jvm.internal.impl.types.KotlinType r6 = (kotlin.reflect.jvm.internal.impl.types.KotlinType) r6
            kotlin.reflect.jvm.internal.impl.types.TypeProjection r6 = kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt.asTypeProjection(r6)
            r8.add(r6)
            r10 = r12
            r6 = 1
            r9 = 10
            goto L_0x011e
        L_0x01bc:
            java.util.List r8 = (java.util.List) r8
            boolean r1 = r0.isSubtypeForSameConstructor(r1, r8, r3)
            return r1
        L_0x01c3:
            java.lang.Object r2 = kotlin.collections.CollectionsKt.first(r7)
            kotlin.reflect.jvm.internal.impl.types.SimpleType r2 = (kotlin.reflect.jvm.internal.impl.types.SimpleType) r2
            java.util.List r2 = r2.getArguments()
            boolean r1 = r0.isSubtypeForSameConstructor(r1, r2, r3)
            return r1
        L_0x01d2:
            return r5
        L_0x01d3:
            java.lang.Object r2 = kotlin.collections.CollectionsKt.first(r7)
            kotlin.reflect.jvm.internal.impl.types.SimpleType r2 = (kotlin.reflect.jvm.internal.impl.types.SimpleType) r2
            java.util.List r2 = r2.getArguments()
            boolean r1 = r0.isSubtypeForSameConstructor(r1, r2, r3)
            return r1
        L_0x01e2:
            boolean r1 = r16.hasNothingSupertype(r17, r18)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.types.checker.NewKotlinTypeChecker.isSubtypeOfForSingleClassifierType(kotlin.reflect.jvm.internal.impl.types.checker.TypeCheckerContext, kotlin.reflect.jvm.internal.impl.types.SimpleType, kotlin.reflect.jvm.internal.impl.types.SimpleType):boolean");
    }

    private final List<SimpleType> collectAndFilter(@NotNull TypeCheckerContext typeCheckerContext, SimpleType simpleType, TypeConstructor typeConstructor) {
        return selectOnlyPureKotlinSupertypes(collectAllSupertypesWithGivenTypeConstructor(typeCheckerContext, simpleType, typeConstructor));
    }

    @NotNull
    public final List<SimpleType> findCorrespondingSupertypes(@NotNull TypeCheckerContext typeCheckerContext, @NotNull SimpleType simpleType, @NotNull TypeConstructor typeConstructor) {
        SupertypesPolicy supertypesPolicy;
        Intrinsics.checkParameterIsNotNull(typeCheckerContext, "$receiver");
        Intrinsics.checkParameterIsNotNull(simpleType, "baseType");
        Intrinsics.checkParameterIsNotNull(typeConstructor, "constructor");
        if (NewKotlinTypeCheckerKt.isClassType(simpleType)) {
            return collectAndFilter(typeCheckerContext, simpleType, typeConstructor);
        }
        if (!(typeConstructor.getDeclarationDescriptor() instanceof ClassDescriptor)) {
            return collectAllSupertypesWithGivenTypeConstructor(typeCheckerContext, simpleType, typeConstructor);
        }
        SmartList smartList = new SmartList();
        typeCheckerContext.initialize();
        ArrayDeque access$getSupertypesDeque$p = typeCheckerContext.supertypesDeque;
        if (access$getSupertypesDeque$p == null) {
            Intrinsics.throwNpe();
        }
        Set access$getSupertypesSet$p = typeCheckerContext.supertypesSet;
        if (access$getSupertypesSet$p == null) {
            Intrinsics.throwNpe();
        }
        access$getSupertypesDeque$p.push(simpleType);
        while (!access$getSupertypesDeque$p.isEmpty()) {
            if (access$getSupertypesSet$p.size() <= 1000) {
                SimpleType simpleType2 = (SimpleType) access$getSupertypesDeque$p.pop();
                Intrinsics.checkExpressionValueIsNotNull(simpleType2, "current");
                if (access$getSupertypesSet$p.add(simpleType2)) {
                    if (NewKotlinTypeCheckerKt.isClassType(simpleType2)) {
                        smartList.add(simpleType2);
                        supertypesPolicy = None.INSTANCE;
                    } else {
                        supertypesPolicy = LowerIfFlexible.INSTANCE;
                    }
                    if (!(!Intrinsics.areEqual((Object) supertypesPolicy, (Object) None.INSTANCE))) {
                        supertypesPolicy = null;
                    }
                    if (supertypesPolicy != null) {
                        for (KotlinType kotlinType : simpleType2.getConstructor().getSupertypes()) {
                            Intrinsics.checkExpressionValueIsNotNull(kotlinType, "supertype");
                            access$getSupertypesDeque$p.add(supertypesPolicy.transformType(kotlinType));
                        }
                    }
                }
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("Too many supertypes for type: ");
                sb.append(simpleType);
                sb.append(". Supertypes = ");
                sb.append(CollectionsKt.joinToString$default(access$getSupertypesSet$p, null, null, null, 0, null, null, 63, null));
                throw new IllegalStateException(sb.toString().toString());
            }
        }
        typeCheckerContext.clear();
        Iterable<SimpleType> iterable = smartList;
        Collection arrayList = new ArrayList();
        for (SimpleType simpleType3 : iterable) {
            NewKotlinTypeChecker newKotlinTypeChecker = INSTANCE;
            Intrinsics.checkExpressionValueIsNotNull(simpleType3, "it");
            CollectionsKt.addAll(arrayList, (Iterable<? extends T>) newKotlinTypeChecker.collectAndFilter(typeCheckerContext, simpleType3, typeConstructor));
        }
        return (List) arrayList;
    }

    private final List<SimpleType> collectAllSupertypesWithGivenTypeConstructor(@NotNull TypeCheckerContext typeCheckerContext, SimpleType simpleType, TypeConstructor typeConstructor) {
        SupertypesPolicy supertypesPolicy;
        List<SimpleType> list;
        TypeCheckerContext typeCheckerContext2 = typeCheckerContext;
        SimpleType simpleType2 = simpleType;
        TypeConstructor typeConstructor2 = typeConstructor;
        ClassifierDescriptor declarationDescriptor = typeConstructor.getDeclarationDescriptor();
        if (!(declarationDescriptor instanceof ClassDescriptor)) {
            declarationDescriptor = null;
        }
        ClassDescriptor classDescriptor = (ClassDescriptor) declarationDescriptor;
        if (classDescriptor != null) {
            if (isCommonFinalClass(classDescriptor)) {
                if (typeCheckerContext2.areEqualTypeConstructors(simpleType.getConstructor(), typeConstructor2)) {
                    SimpleType captureFromArguments$default = NewCapturedTypeKt.captureFromArguments$default(simpleType2, CaptureStatus.FOR_SUBTYPING, null, 4, null);
                    if (captureFromArguments$default == null) {
                        captureFromArguments$default = simpleType2;
                    }
                    list = CollectionsKt.listOf(captureFromArguments$default);
                } else {
                    list = CollectionsKt.emptyList();
                }
                return list;
            }
        }
        List<SimpleType> smartList = new SmartList<>();
        typeCheckerContext.initialize();
        ArrayDeque access$getSupertypesDeque$p = typeCheckerContext.supertypesDeque;
        if (access$getSupertypesDeque$p == null) {
            Intrinsics.throwNpe();
        }
        Set access$getSupertypesSet$p = typeCheckerContext.supertypesSet;
        if (access$getSupertypesSet$p == null) {
            Intrinsics.throwNpe();
        }
        access$getSupertypesDeque$p.push(simpleType2);
        while (!access$getSupertypesDeque$p.isEmpty()) {
            if (access$getSupertypesSet$p.size() <= 1000) {
                SimpleType simpleType3 = (SimpleType) access$getSupertypesDeque$p.pop();
                Intrinsics.checkExpressionValueIsNotNull(simpleType3, "current");
                if (access$getSupertypesSet$p.add(simpleType3)) {
                    SimpleType captureFromArguments$default2 = NewCapturedTypeKt.captureFromArguments$default(simpleType3, CaptureStatus.FOR_SUBTYPING, null, 4, null);
                    if (captureFromArguments$default2 == null) {
                        captureFromArguments$default2 = simpleType3;
                    }
                    if (typeCheckerContext2.areEqualTypeConstructors(captureFromArguments$default2.getConstructor(), typeConstructor2)) {
                        smartList.add(captureFromArguments$default2);
                        supertypesPolicy = None.INSTANCE;
                    } else if (captureFromArguments$default2.getArguments().isEmpty()) {
                        supertypesPolicy = LowerIfFlexible.INSTANCE;
                    } else {
                        supertypesPolicy = new LowerIfFlexibleWithCustomSubstitutor(TypeConstructorSubstitution.Companion.create(captureFromArguments$default2).buildSubstitutor());
                    }
                    if (!(!Intrinsics.areEqual((Object) supertypesPolicy, (Object) None.INSTANCE))) {
                        supertypesPolicy = null;
                    }
                    if (supertypesPolicy != null) {
                        for (KotlinType kotlinType : simpleType3.getConstructor().getSupertypes()) {
                            Intrinsics.checkExpressionValueIsNotNull(kotlinType, "supertype");
                            access$getSupertypesDeque$p.add(supertypesPolicy.transformType(kotlinType));
                        }
                    }
                }
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("Too many supertypes for type: ");
                sb.append(simpleType2);
                sb.append(". Supertypes = ");
                sb.append(CollectionsKt.joinToString$default(access$getSupertypesSet$p, null, null, null, 0, null, null, 63, null));
                throw new IllegalStateException(sb.toString().toString());
            }
        }
        typeCheckerContext.clear();
        return smartList;
    }

    private final boolean isCommonFinalClass(@NotNull ClassDescriptor classDescriptor) {
        return (!ModalityKt.isFinalClass(classDescriptor) || classDescriptor.getKind() == ClassKind.ENUM_ENTRY || classDescriptor.getKind() == ClassKind.ANNOTATION_CLASS) ? false : true;
    }

    private final List<SimpleType> selectOnlyPureKotlinSupertypes(List<? extends SimpleType> list) {
        if (list.size() < 2) {
            return list;
        }
        Iterable iterable = list;
        Collection arrayList = new ArrayList();
        Iterator it = iterable.iterator();
        while (true) {
            boolean z = true;
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            Iterable arguments = ((SimpleType) next).getArguments();
            if (!(arguments instanceof Collection) || !((Collection) arguments).isEmpty()) {
                Iterator it2 = arguments.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        break;
                    }
                    KotlinType type = ((TypeProjection) it2.next()).getType();
                    Intrinsics.checkExpressionValueIsNotNull(type, "it.type");
                    if (!(!FlexibleTypesKt.isFlexible(type))) {
                        z = false;
                        break;
                    }
                }
            }
            if (z) {
                arrayList.add(next);
            }
        }
        List<? extends SimpleType> list2 = (List) arrayList;
        if (!list2.isEmpty()) {
            list = list2;
        }
        return list;
    }

    @Nullable
    public final Variance effectiveVariance(@NotNull Variance variance, @NotNull Variance variance2) {
        Intrinsics.checkParameterIsNotNull(variance, "declared");
        Intrinsics.checkParameterIsNotNull(variance2, "useSite");
        if (variance == Variance.INVARIANT) {
            return variance2;
        }
        if (variance2 == Variance.INVARIANT || variance == variance2) {
            return variance;
        }
        return null;
    }

    private final boolean isSubtypeForSameConstructor(@NotNull TypeCheckerContext typeCheckerContext, List<? extends TypeProjection> list, SimpleType simpleType) {
        boolean z;
        if (list == simpleType.getArguments()) {
            return true;
        }
        List parameters = simpleType.getConstructor().getParameters();
        Intrinsics.checkExpressionValueIsNotNull(parameters, "parameters");
        int size = parameters.size();
        for (int i = 0; i < size; i++) {
            TypeProjection typeProjection = (TypeProjection) simpleType.getArguments().get(i);
            if (!typeProjection.isStarProjection()) {
                UnwrappedType unwrap = typeProjection.getType().unwrap();
                TypeProjection typeProjection2 = (TypeProjection) list.get(i);
                boolean z2 = typeProjection2.getProjectionKind() == Variance.INVARIANT;
                if (!_Assertions.ENABLED || z2) {
                    UnwrappedType unwrap2 = typeProjection2.getType().unwrap();
                    Object obj = parameters.get(i);
                    Intrinsics.checkExpressionValueIsNotNull(obj, "parameters[index]");
                    Variance variance = ((TypeParameterDescriptor) obj).getVariance();
                    Intrinsics.checkExpressionValueIsNotNull(variance, "parameters[index].variance");
                    Variance projectionKind = typeProjection.getProjectionKind();
                    Intrinsics.checkExpressionValueIsNotNull(projectionKind, "superProjection.projectionKind");
                    Variance effectiveVariance = effectiveVariance(variance, projectionKind);
                    if (effectiveVariance == null) {
                        return typeCheckerContext.getErrorTypeEqualsToAnything();
                    }
                    if (typeCheckerContext.argumentsDepth <= 100) {
                        typeCheckerContext.argumentsDepth = typeCheckerContext.argumentsDepth + 1;
                        int i2 = WhenMappings.$EnumSwitchMapping$2[effectiveVariance.ordinal()];
                        if (i2 == 1) {
                            z = INSTANCE.equalTypes(typeCheckerContext, unwrap2, unwrap);
                        } else if (i2 == 2) {
                            z = INSTANCE.isSubtypeOf(typeCheckerContext, unwrap2, unwrap);
                        } else if (i2 == 3) {
                            z = INSTANCE.isSubtypeOf(typeCheckerContext, unwrap, unwrap2);
                        } else {
                            throw new NoWhenBranchMatchedException();
                        }
                        typeCheckerContext.argumentsDepth = typeCheckerContext.argumentsDepth - 1;
                        if (!z) {
                            return false;
                        }
                    } else {
                        StringBuilder sb = new StringBuilder();
                        sb.append("Arguments depth is too high. Some related argument: ");
                        sb.append(unwrap2);
                        throw new IllegalStateException(sb.toString().toString());
                    }
                } else {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("Incorrect sub argument: ");
                    sb2.append(typeProjection2);
                    throw new AssertionError(sb2.toString());
                }
            }
        }
        return true;
    }

    private final boolean hasNothingSupertype(@NotNull TypeCheckerContext typeCheckerContext, SimpleType simpleType) {
        SupertypesPolicy supertypesPolicy;
        if (KotlinBuiltIns.isNothingOrNullableNothing(simpleType)) {
            return true;
        }
        typeCheckerContext.initialize();
        ArrayDeque access$getSupertypesDeque$p = typeCheckerContext.supertypesDeque;
        if (access$getSupertypesDeque$p == null) {
            Intrinsics.throwNpe();
        }
        Set access$getSupertypesSet$p = typeCheckerContext.supertypesSet;
        if (access$getSupertypesSet$p == null) {
            Intrinsics.throwNpe();
        }
        access$getSupertypesDeque$p.push(simpleType);
        while (!access$getSupertypesDeque$p.isEmpty()) {
            if (access$getSupertypesSet$p.size() <= 1000) {
                SimpleType simpleType2 = (SimpleType) access$getSupertypesDeque$p.pop();
                Intrinsics.checkExpressionValueIsNotNull(simpleType2, "current");
                if (access$getSupertypesSet$p.add(simpleType2)) {
                    if (NewKotlinTypeCheckerKt.isClassType(simpleType2)) {
                        supertypesPolicy = None.INSTANCE;
                    } else {
                        supertypesPolicy = LowerIfFlexible.INSTANCE;
                    }
                    if (!(!Intrinsics.areEqual((Object) supertypesPolicy, (Object) None.INSTANCE))) {
                        supertypesPolicy = null;
                    }
                    if (supertypesPolicy != null) {
                        for (KotlinType kotlinType : simpleType2.getConstructor().getSupertypes()) {
                            Intrinsics.checkExpressionValueIsNotNull(kotlinType, "supertype");
                            SimpleType transformType = supertypesPolicy.transformType(kotlinType);
                            if (KotlinBuiltIns.isNothingOrNullableNothing(transformType)) {
                                typeCheckerContext.clear();
                                return true;
                            }
                            access$getSupertypesDeque$p.add(transformType);
                        }
                        continue;
                    } else {
                        continue;
                    }
                }
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("Too many supertypes for type: ");
                sb.append(simpleType);
                sb.append(". Supertypes = ");
                sb.append(CollectionsKt.joinToString$default(access$getSupertypesSet$p, null, null, null, 0, null, null, 63, null));
                throw new IllegalStateException(sb.toString().toString());
            }
        }
        typeCheckerContext.clear();
        return false;
    }
}
