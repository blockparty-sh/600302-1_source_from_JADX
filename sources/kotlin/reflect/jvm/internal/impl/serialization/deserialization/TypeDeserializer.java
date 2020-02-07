package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.FunctionTypesKt;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.builtins.SuspendFunctionTypesKt;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FindClassInModuleKt;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.C3063Type;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.C3063Type.Argument;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.C3063Type.Argument.Projection;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeParameter;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.ProtoTypeTableUtilKt;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedAnnotationsWithPossibleTargets;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedTypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.types.ErrorUtils;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.SpecialTypesKt;
import kotlin.reflect.jvm.internal.impl.types.StarProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.TypeBasedStarProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: TypeDeserializer.kt */
public final class TypeDeserializer {
    /* access modifiers changed from: private */

    /* renamed from: c */
    public final DeserializationContext f721c;
    private final Function1<Integer, ClassDescriptor> classDescriptors;
    private final String debugName;
    private boolean experimentalSuspendFunctionTypeEncountered;
    private final TypeDeserializer parent;
    private final Function1<Integer, ClassifierDescriptor> typeAliasDescriptors;
    private final Map<Integer, TypeParameterDescriptor> typeParameterDescriptors;

    public TypeDeserializer(@NotNull DeserializationContext deserializationContext, @Nullable TypeDeserializer typeDeserializer, @NotNull List<TypeParameter> list, @NotNull String str, boolean z) {
        Map<Integer, TypeParameterDescriptor> map;
        Intrinsics.checkParameterIsNotNull(deserializationContext, "c");
        Intrinsics.checkParameterIsNotNull(list, "typeParameterProtos");
        Intrinsics.checkParameterIsNotNull(str, "debugName");
        this.f721c = deserializationContext;
        this.parent = typeDeserializer;
        this.debugName = str;
        this.experimentalSuspendFunctionTypeEncountered = z;
        this.classDescriptors = this.f721c.getStorageManager().createMemoizedFunctionWithNullableValues(new TypeDeserializer$classDescriptors$1(this));
        this.typeAliasDescriptors = this.f721c.getStorageManager().createMemoizedFunctionWithNullableValues(new TypeDeserializer$typeAliasDescriptors$1(this));
        if (list.isEmpty()) {
            map = MapsKt.emptyMap();
        } else {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            int i = 0;
            for (TypeParameter typeParameter : list) {
                linkedHashMap.put(Integer.valueOf(typeParameter.getId()), new DeserializedTypeParameterDescriptor(this.f721c, typeParameter, i));
                i++;
            }
            map = linkedHashMap;
        }
        this.typeParameterDescriptors = map;
    }

    public /* synthetic */ TypeDeserializer(DeserializationContext deserializationContext, TypeDeserializer typeDeserializer, List list, String str, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(deserializationContext, typeDeserializer, list, str, (i & 16) != 0 ? false : z);
    }

    public final boolean getExperimentalSuspendFunctionTypeEncountered() {
        return this.experimentalSuspendFunctionTypeEncountered;
    }

    @NotNull
    public final List<TypeParameterDescriptor> getOwnTypeParameters() {
        return CollectionsKt.toList(this.typeParameterDescriptors.values());
    }

    @NotNull
    public static /* bridge */ /* synthetic */ KotlinType type$default(TypeDeserializer typeDeserializer, C3063Type type, Annotations annotations, int i, Object obj) {
        if ((i & 2) != 0) {
            annotations = Annotations.Companion.getEMPTY();
        }
        return typeDeserializer.type(type, annotations);
    }

    @NotNull
    public final KotlinType type(@NotNull C3063Type type, @NotNull Annotations annotations) {
        Intrinsics.checkParameterIsNotNull(type, "proto");
        Intrinsics.checkParameterIsNotNull(annotations, "additionalAnnotations");
        if (!type.hasFlexibleTypeCapabilitiesId()) {
            return simpleType(type, annotations);
        }
        String string = this.f721c.getNameResolver().getString(type.getFlexibleTypeCapabilitiesId());
        SimpleType simpleType = simpleType(type, annotations);
        C3063Type flexibleUpperBound = ProtoTypeTableUtilKt.flexibleUpperBound(type, this.f721c.getTypeTable());
        if (flexibleUpperBound == null) {
            Intrinsics.throwNpe();
        }
        return this.f721c.getComponents().getFlexibleTypeDeserializer().create(type, string, simpleType, simpleType(flexibleUpperBound, annotations));
    }

    @NotNull
    public static /* bridge */ /* synthetic */ SimpleType simpleType$default(TypeDeserializer typeDeserializer, C3063Type type, Annotations annotations, int i, Object obj) {
        if ((i & 2) != 0) {
            annotations = Annotations.Companion.getEMPTY();
        }
        return typeDeserializer.simpleType(type, annotations);
    }

    @NotNull
    public final SimpleType simpleType(@NotNull C3063Type type, @NotNull Annotations annotations) {
        SimpleType simpleType;
        Intrinsics.checkParameterIsNotNull(type, "proto");
        Intrinsics.checkParameterIsNotNull(annotations, "additionalAnnotations");
        SimpleType simpleType2 = type.hasClassName() ? computeLocalClassifierReplacementType(type.getClassName()) : type.hasTypeAliasName() ? computeLocalClassifierReplacementType(type.getTypeAliasName()) : null;
        if (simpleType2 != null) {
            return simpleType2;
        }
        TypeConstructor typeConstructor = typeConstructor(type);
        if (ErrorUtils.isError(typeConstructor.getDeclarationDescriptor())) {
            SimpleType createErrorTypeWithCustomConstructor = ErrorUtils.createErrorTypeWithCustomConstructor(typeConstructor.toString(), typeConstructor);
            Intrinsics.checkExpressionValueIsNotNull(createErrorTypeWithCustomConstructor, "ErrorUtils.createErrorTy….toString(), constructor)");
            return createErrorTypeWithCustomConstructor;
        }
        DeserializedAnnotationsWithPossibleTargets deserializedAnnotationsWithPossibleTargets = new DeserializedAnnotationsWithPossibleTargets(this.f721c.getStorageManager(), new TypeDeserializer$simpleType$annotations$1(this, type, annotations));
        Iterable<Argument> invoke = new TypeDeserializer$simpleType$1(this).invoke(type);
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(invoke, 10));
        int i = 0;
        for (Argument argument : invoke) {
            int i2 = i + 1;
            List parameters = typeConstructor.getParameters();
            Intrinsics.checkExpressionValueIsNotNull(parameters, "constructor.parameters");
            arrayList.add(typeArgument((TypeParameterDescriptor) CollectionsKt.getOrNull(parameters, i), argument));
            i = i2;
        }
        List list = CollectionsKt.toList((List) arrayList);
        Boolean bool = Flags.SUSPEND_TYPE.get(type.getFlags());
        Intrinsics.checkExpressionValueIsNotNull(bool, "Flags.SUSPEND_TYPE.get(proto.flags)");
        if (bool.booleanValue()) {
            simpleType = createSuspendFunctionType(deserializedAnnotationsWithPossibleTargets, typeConstructor, list, type.getNullable());
        } else {
            simpleType = KotlinTypeFactory.simpleType(deserializedAnnotationsWithPossibleTargets, typeConstructor, list, type.getNullable());
        }
        C3063Type abbreviatedType = ProtoTypeTableUtilKt.abbreviatedType(type, this.f721c.getTypeTable());
        return abbreviatedType != null ? SpecialTypesKt.withAbbreviation(simpleType, simpleType(abbreviatedType, annotations)) : simpleType;
    }

    private final TypeConstructor typeConstructor(C3063Type type) {
        Object obj;
        TypeDeserializer$typeConstructor$1 typeDeserializer$typeConstructor$1 = new TypeDeserializer$typeConstructor$1(this, type);
        if (type.hasClassName()) {
            ClassDescriptor classDescriptor = (ClassDescriptor) this.classDescriptors.invoke(Integer.valueOf(type.getClassName()));
            if (classDescriptor == null) {
                classDescriptor = typeDeserializer$typeConstructor$1.invoke(type.getClassName());
            }
            TypeConstructor typeConstructor = classDescriptor.getTypeConstructor();
            Intrinsics.checkExpressionValueIsNotNull(typeConstructor, "(classDescriptors(proto.…assName)).typeConstructor");
            return typeConstructor;
        } else if (type.hasTypeParameter()) {
            TypeConstructor typeParameterTypeConstructor = typeParameterTypeConstructor(type.getTypeParameter());
            if (typeParameterTypeConstructor != null) {
                return typeParameterTypeConstructor;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Unknown type parameter ");
            sb.append(type.getTypeParameter());
            TypeConstructor createErrorTypeConstructor = ErrorUtils.createErrorTypeConstructor(sb.toString());
            Intrinsics.checkExpressionValueIsNotNull(createErrorTypeConstructor, "ErrorUtils.createErrorTy… ${proto.typeParameter}\")");
            return createErrorTypeConstructor;
        } else if (type.hasTypeParameterName()) {
            DeclarationDescriptor containingDeclaration = this.f721c.getContainingDeclaration();
            String string = this.f721c.getNameResolver().getString(type.getTypeParameterName());
            Iterator it = getOwnTypeParameters().iterator();
            while (true) {
                if (!it.hasNext()) {
                    obj = null;
                    break;
                }
                obj = it.next();
                if (Intrinsics.areEqual((Object) ((TypeParameterDescriptor) obj).getName().asString(), (Object) string)) {
                    break;
                }
            }
            TypeParameterDescriptor typeParameterDescriptor = (TypeParameterDescriptor) obj;
            if (typeParameterDescriptor != null) {
                TypeConstructor typeConstructor2 = typeParameterDescriptor.getTypeConstructor();
                if (typeConstructor2 != null) {
                    return typeConstructor2;
                }
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Deserialized type parameter ");
            sb2.append(string);
            sb2.append(" in ");
            sb2.append(containingDeclaration);
            TypeConstructor createErrorTypeConstructor2 = ErrorUtils.createErrorTypeConstructor(sb2.toString());
            Intrinsics.checkExpressionValueIsNotNull(createErrorTypeConstructor2, "ErrorUtils.createErrorTy…ter $name in $container\")");
            return createErrorTypeConstructor2;
        } else if (type.hasTypeAliasName()) {
            ClassifierDescriptor classifierDescriptor = (ClassifierDescriptor) this.typeAliasDescriptors.invoke(Integer.valueOf(type.getTypeAliasName()));
            if (classifierDescriptor == null) {
                classifierDescriptor = typeDeserializer$typeConstructor$1.invoke(type.getTypeAliasName());
            }
            TypeConstructor typeConstructor3 = classifierDescriptor.getTypeConstructor();
            Intrinsics.checkExpressionValueIsNotNull(typeConstructor3, "(typeAliasDescriptors(pr…iasName)).typeConstructor");
            return typeConstructor3;
        } else {
            TypeConstructor createErrorTypeConstructor3 = ErrorUtils.createErrorTypeConstructor("Unknown type");
            Intrinsics.checkExpressionValueIsNotNull(createErrorTypeConstructor3, "ErrorUtils.createErrorTy…nstructor(\"Unknown type\")");
            return createErrorTypeConstructor3;
        }
    }

    private final SimpleType createSuspendFunctionType(Annotations annotations, TypeConstructor typeConstructor, List<? extends TypeProjection> list, boolean z) {
        int size = typeConstructor.getParameters().size() - list.size();
        SimpleType simpleType = null;
        if (size == 0) {
            simpleType = createSuspendFunctionTypeForBasicCase(annotations, typeConstructor, list, z);
        } else if (size == 1) {
            int size2 = list.size() - 1;
            if (size2 >= 0) {
                ClassDescriptor suspendFunction = typeConstructor.getBuiltIns().getSuspendFunction(size2);
                Intrinsics.checkExpressionValueIsNotNull(suspendFunction, "functionTypeConstructor.…getSuspendFunction(arity)");
                TypeConstructor typeConstructor2 = suspendFunction.getTypeConstructor();
                Intrinsics.checkExpressionValueIsNotNull(typeConstructor2, "functionTypeConstructor.…on(arity).typeConstructor");
                simpleType = KotlinTypeFactory.simpleType(annotations, typeConstructor2, list, z);
            }
        }
        if (simpleType != null) {
            return simpleType;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Bad suspend function in metadata with constructor: ");
        sb.append(typeConstructor);
        SimpleType createErrorTypeWithArguments = ErrorUtils.createErrorTypeWithArguments(sb.toString(), list);
        Intrinsics.checkExpressionValueIsNotNull(createErrorTypeWithArguments, "ErrorUtils.createErrorTy…      arguments\n        )");
        return createErrorTypeWithArguments;
    }

    private final SimpleType createSuspendFunctionTypeForBasicCase(Annotations annotations, TypeConstructor typeConstructor, List<? extends TypeProjection> list, boolean z) {
        KotlinType simpleType = KotlinTypeFactory.simpleType(annotations, typeConstructor, list, z);
        if (!FunctionTypesKt.isFunctionType(simpleType)) {
            return null;
        }
        return transformRuntimeFunctionTypeToSuspendFunction(simpleType);
    }

    private final SimpleType transformRuntimeFunctionTypeToSuspendFunction(KotlinType kotlinType) {
        boolean releaseCoroutines = this.f721c.getComponents().getConfiguration().getReleaseCoroutines();
        TypeProjection typeProjection = (TypeProjection) CollectionsKt.lastOrNull(FunctionTypesKt.getValueParameterTypesFromFunctionType(kotlinType));
        FqName fqName = null;
        if (typeProjection != null) {
            KotlinType type = typeProjection.getType();
            if (type != null) {
                ClassifierDescriptor declarationDescriptor = type.getConstructor().getDeclarationDescriptor();
                FqName fqNameSafe = declarationDescriptor != null ? DescriptorUtilsKt.getFqNameSafe(declarationDescriptor) : null;
                boolean z = true;
                if (type.getArguments().size() != 1 || (!SuspendFunctionTypesKt.isContinuation(fqNameSafe, true) && !SuspendFunctionTypesKt.isContinuation(fqNameSafe, false))) {
                    return (SimpleType) kotlinType;
                }
                KotlinType type2 = ((TypeProjection) CollectionsKt.single(type.getArguments())).getType();
                DeclarationDescriptor containingDeclaration = this.f721c.getContainingDeclaration();
                if (!(containingDeclaration instanceof CallableDescriptor)) {
                    containingDeclaration = null;
                }
                CallableDescriptor callableDescriptor = (CallableDescriptor) containingDeclaration;
                if (callableDescriptor != null) {
                    fqName = DescriptorUtilsKt.fqNameOrNull(callableDescriptor);
                }
                String str = "suspendReturnType";
                if (Intrinsics.areEqual((Object) fqName, (Object) SuspendFunctionTypeUtilKt.KOTLIN_SUSPEND_BUILT_IN_FUNCTION_FQ_NAME)) {
                    Intrinsics.checkExpressionValueIsNotNull(type2, str);
                    return createSimpleSuspendFunctionType(kotlinType, type2);
                }
                if (!this.experimentalSuspendFunctionTypeEncountered && (!releaseCoroutines || !SuspendFunctionTypesKt.isContinuation(fqNameSafe, !releaseCoroutines))) {
                    z = false;
                }
                this.experimentalSuspendFunctionTypeEncountered = z;
                Intrinsics.checkExpressionValueIsNotNull(type2, str);
                return createSimpleSuspendFunctionType(kotlinType, type2);
            }
        }
        return null;
    }

    private final SimpleType createSimpleSuspendFunctionType(KotlinType kotlinType, KotlinType kotlinType2) {
        KotlinBuiltIns builtIns = TypeUtilsKt.getBuiltIns(kotlinType);
        Annotations annotations = kotlinType.getAnnotations();
        KotlinType receiverTypeFromFunctionType = FunctionTypesKt.getReceiverTypeFromFunctionType(kotlinType);
        Iterable<TypeProjection> dropLast = CollectionsKt.dropLast(FunctionTypesKt.getValueParameterTypesFromFunctionType(kotlinType), 1);
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(dropLast, 10));
        for (TypeProjection type : dropLast) {
            arrayList.add(type.getType());
        }
        return FunctionTypesKt.createFunctionType(builtIns, annotations, receiverTypeFromFunctionType, (List) arrayList, null, kotlinType2, true).makeNullableAsSpecified(kotlinType.isMarkedNullable());
    }

    private final TypeConstructor typeParameterTypeConstructor(int i) {
        TypeParameterDescriptor typeParameterDescriptor = (TypeParameterDescriptor) this.typeParameterDescriptors.get(Integer.valueOf(i));
        if (typeParameterDescriptor != null) {
            TypeConstructor typeConstructor = typeParameterDescriptor.getTypeConstructor();
            if (typeConstructor != null) {
                return typeConstructor;
            }
        }
        TypeDeserializer typeDeserializer = this.parent;
        if (typeDeserializer != null) {
            return typeDeserializer.typeParameterTypeConstructor(i);
        }
        return null;
    }

    /* access modifiers changed from: private */
    public final ClassDescriptor computeClassDescriptor(int i) {
        ClassId classId = NameResolverUtilKt.getClassId(this.f721c.getNameResolver(), i);
        if (classId.isLocal()) {
            return this.f721c.getComponents().deserializeClass(classId);
        }
        return FindClassInModuleKt.findClassAcrossModuleDependencies(this.f721c.getComponents().getModuleDescriptor(), classId);
    }

    private final SimpleType computeLocalClassifierReplacementType(int i) {
        if (NameResolverUtilKt.getClassId(this.f721c.getNameResolver(), i).isLocal()) {
            return this.f721c.getComponents().getLocalClassifierTypeSettings().getReplacementTypeForLocalClassifiers();
        }
        return null;
    }

    /* access modifiers changed from: private */
    public final ClassifierDescriptor computeTypeAliasDescriptor(int i) {
        ClassId classId = NameResolverUtilKt.getClassId(this.f721c.getNameResolver(), i);
        if (classId.isLocal()) {
            return null;
        }
        return FindClassInModuleKt.findTypeAliasAcrossModuleDependencies(this.f721c.getComponents().getModuleDescriptor(), classId);
    }

    private final TypeProjection typeArgument(TypeParameterDescriptor typeParameterDescriptor, Argument argument) {
        TypeProjection typeProjection;
        if (argument.getProjection() == Projection.STAR) {
            if (typeParameterDescriptor == null) {
                SimpleType nullableAnyType = this.f721c.getComponents().getModuleDescriptor().getBuiltIns().getNullableAnyType();
                Intrinsics.checkExpressionValueIsNotNull(nullableAnyType, "c.components.moduleDescr….builtIns.nullableAnyType");
                typeProjection = new TypeBasedStarProjectionImpl(nullableAnyType);
            } else {
                typeProjection = new StarProjectionImpl(typeParameterDescriptor);
            }
            return typeProjection;
        }
        ProtoEnumFlags protoEnumFlags = ProtoEnumFlags.INSTANCE;
        Projection projection = argument.getProjection();
        Intrinsics.checkExpressionValueIsNotNull(projection, "typeArgumentProto.projection");
        Variance variance = protoEnumFlags.variance(projection);
        C3063Type type = ProtoTypeTableUtilKt.type(argument, this.f721c.getTypeTable());
        if (type != null) {
            return new TypeProjectionImpl(variance, type$default(this, type, null, 2, null));
        }
        return new TypeProjectionImpl(ErrorUtils.createErrorType("No type recorded"));
    }

    @NotNull
    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append(this.debugName);
        if (this.parent == null) {
            str = "";
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(". Child of ");
            sb2.append(this.parent.debugName);
            str = sb2.toString();
        }
        sb.append(str);
        return sb.toString();
    }
}
