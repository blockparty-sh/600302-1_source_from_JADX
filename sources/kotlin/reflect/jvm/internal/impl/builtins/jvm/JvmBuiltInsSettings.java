package kotlin.reflect.jvm.internal.impl.builtins.jvm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Ref.ObjectRef;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor.Kind;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassKind;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorUtilKt;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibilities;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationsImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.AdditionalClassPartsProvider;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.PlatformDependentDeclarationFilter;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.PlatformDependentDeclarationFilterKt;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.ClassDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.incremental.components.NoLookupLocation;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaClassDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaClassMemberScope;
import kotlin.reflect.jvm.internal.impl.load.kotlin.MethodSignatureMappingKt;
import kotlin.reflect.jvm.internal.impl.load.kotlin.SignatureBuildingComponents;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmPrimitiveType;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope.Empty;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedClassDescriptor;
import kotlin.reflect.jvm.internal.impl.storage.CacheWithNotNullValues;
import kotlin.reflect.jvm.internal.impl.storage.NotNullLazyValue;
import kotlin.reflect.jvm.internal.impl.storage.StorageKt;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.LazyWrappedType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.utils.DFS;
import kotlin.reflect.jvm.internal.impl.utils.SmartSet;
import org.jetbrains.annotations.NotNull;

/* compiled from: JvmBuiltInsSettings.kt */
public class JvmBuiltInsSettings implements AdditionalClassPartsProvider, PlatformDependentDeclarationFilter {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(JvmBuiltInsSettings.class), "ownerModuleDescriptor", "getOwnerModuleDescriptor()Lorg/jetbrains/kotlin/descriptors/ModuleDescriptor;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(JvmBuiltInsSettings.class), "isAdditionalBuiltInsFeatureSupported", "isAdditionalBuiltInsFeatureSupported()Z")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(JvmBuiltInsSettings.class), "cloneableType", "getCloneableType()Lorg/jetbrains/kotlin/types/SimpleType;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(JvmBuiltInsSettings.class), "notConsideredDeprecation", "getNotConsideredDeprecation()Lorg/jetbrains/kotlin/descriptors/annotations/AnnotationsImpl;"))};
    @NotNull
    private static final Set<String> BLACK_LIST_CONSTRUCTOR_SIGNATURES;
    /* access modifiers changed from: private */
    @NotNull
    public static final Set<String> BLACK_LIST_METHOD_SIGNATURES;
    public static final Companion Companion = new Companion(null);
    /* access modifiers changed from: private */
    @NotNull
    public static final Set<String> DROP_LIST_METHOD_SIGNATURES;
    @NotNull
    private static final Set<String> MUTABLE_METHOD_SIGNATURES;
    @NotNull
    private static final Set<String> WHITE_LIST_CONSTRUCTOR_SIGNATURES;
    /* access modifiers changed from: private */
    @NotNull
    public static final Set<String> WHITE_LIST_METHOD_SIGNATURES;
    private final NotNullLazyValue cloneableType$delegate;
    private final Lazy isAdditionalBuiltInsFeatureSupported$delegate;
    /* access modifiers changed from: private */
    public final JavaToKotlinClassMap j2kClassMap = JavaToKotlinClassMap.INSTANCE;
    private final CacheWithNotNullValues<FqName, ClassDescriptor> javaAnalogueClassesWithCustomSupertypeCache;
    private final KotlinType mockSerializableType;
    /* access modifiers changed from: private */
    public final ModuleDescriptor moduleDescriptor;
    private final NotNullLazyValue notConsideredDeprecation$delegate;
    private final Lazy ownerModuleDescriptor$delegate;

    /* compiled from: JvmBuiltInsSettings.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final boolean isSerializableInJava(@NotNull FqNameUnsafe fqNameUnsafe) {
            Intrinsics.checkParameterIsNotNull(fqNameUnsafe, "fqName");
            if (isArrayOrPrimitiveArray(fqNameUnsafe)) {
                return true;
            }
            ClassId mapKotlinToJava = JavaToKotlinClassMap.INSTANCE.mapKotlinToJava(fqNameUnsafe);
            if (mapKotlinToJava != null) {
                try {
                    return Serializable.class.isAssignableFrom(Class.forName(mapKotlinToJava.asSingleFqName().asString()));
                } catch (ClassNotFoundException unused) {
                }
            }
            return false;
        }

        /* access modifiers changed from: private */
        public final boolean isArrayOrPrimitiveArray(FqNameUnsafe fqNameUnsafe) {
            return Intrinsics.areEqual((Object) fqNameUnsafe, (Object) KotlinBuiltIns.FQ_NAMES.array) || KotlinBuiltIns.isPrimitiveArray(fqNameUnsafe);
        }

        @NotNull
        public final Set<String> getDROP_LIST_METHOD_SIGNATURES() {
            return JvmBuiltInsSettings.DROP_LIST_METHOD_SIGNATURES;
        }

        @NotNull
        public final Set<String> getBLACK_LIST_METHOD_SIGNATURES() {
            return JvmBuiltInsSettings.BLACK_LIST_METHOD_SIGNATURES;
        }

        @NotNull
        public final Set<String> getWHITE_LIST_METHOD_SIGNATURES() {
            return JvmBuiltInsSettings.WHITE_LIST_METHOD_SIGNATURES;
        }

        /* access modifiers changed from: private */
        public final Set<String> buildPrimitiveValueMethodsSet() {
            SignatureBuildingComponents signatureBuildingComponents = SignatureBuildingComponents.INSTANCE;
            Iterable<JvmPrimitiveType> listOf = CollectionsKt.listOf(JvmPrimitiveType.BOOLEAN, JvmPrimitiveType.CHAR);
            Collection linkedHashSet = new LinkedHashSet();
            for (JvmPrimitiveType jvmPrimitiveType : listOf) {
                String asString = jvmPrimitiveType.getWrapperFqName().shortName().asString();
                Intrinsics.checkExpressionValueIsNotNull(asString, "it.wrapperFqName.shortName().asString()");
                StringBuilder sb = new StringBuilder();
                sb.append(jvmPrimitiveType.getJavaKeywordName());
                sb.append("Value()");
                sb.append(jvmPrimitiveType.getDesc());
                CollectionsKt.addAll(linkedHashSet, (Iterable<? extends T>) signatureBuildingComponents.inJavaLang(asString, sb.toString()));
            }
            return (LinkedHashSet) linkedHashSet;
        }

        /* access modifiers changed from: private */
        public final Set<String> buildPrimitiveStringConstructorsSet() {
            SignatureBuildingComponents signatureBuildingComponents = SignatureBuildingComponents.INSTANCE;
            Iterable<JvmPrimitiveType> listOf = CollectionsKt.listOf(JvmPrimitiveType.BOOLEAN, JvmPrimitiveType.BYTE, JvmPrimitiveType.DOUBLE, JvmPrimitiveType.FLOAT, JvmPrimitiveType.BYTE, JvmPrimitiveType.INT, JvmPrimitiveType.LONG, JvmPrimitiveType.SHORT);
            Collection linkedHashSet = new LinkedHashSet();
            for (JvmPrimitiveType wrapperFqName : listOf) {
                String asString = wrapperFqName.getWrapperFqName().shortName().asString();
                Intrinsics.checkExpressionValueIsNotNull(asString, "it.wrapperFqName.shortName().asString()");
                String[] constructors = signatureBuildingComponents.constructors("Ljava/lang/String;");
                CollectionsKt.addAll(linkedHashSet, (Iterable<? extends T>) signatureBuildingComponents.inJavaLang(asString, (String[]) Arrays.copyOf(constructors, constructors.length)));
            }
            return (LinkedHashSet) linkedHashSet;
        }
    }

    /* compiled from: JvmBuiltInsSettings.kt */
    private enum JDKMemberStatus {
        BLACK_LIST,
        WHITE_LIST,
        NOT_CONSIDERED,
        DROP
    }

    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[JDKMemberStatus.values().length];

        static {
            $EnumSwitchMapping$0[JDKMemberStatus.BLACK_LIST.ordinal()] = 1;
            $EnumSwitchMapping$0[JDKMemberStatus.NOT_CONSIDERED.ordinal()] = 2;
            $EnumSwitchMapping$0[JDKMemberStatus.DROP.ordinal()] = 3;
            $EnumSwitchMapping$0[JDKMemberStatus.WHITE_LIST.ordinal()] = 4;
        }
    }

    private final SimpleType getCloneableType() {
        return (SimpleType) StorageKt.getValue(this.cloneableType$delegate, (Object) this, $$delegatedProperties[2]);
    }

    private final AnnotationsImpl getNotConsideredDeprecation() {
        return (AnnotationsImpl) StorageKt.getValue(this.notConsideredDeprecation$delegate, (Object) this, $$delegatedProperties[3]);
    }

    /* access modifiers changed from: private */
    public final ModuleDescriptor getOwnerModuleDescriptor() {
        Lazy lazy = this.ownerModuleDescriptor$delegate;
        KProperty kProperty = $$delegatedProperties[0];
        return (ModuleDescriptor) lazy.getValue();
    }

    private final boolean isAdditionalBuiltInsFeatureSupported() {
        Lazy lazy = this.isAdditionalBuiltInsFeatureSupported$delegate;
        KProperty kProperty = $$delegatedProperties[1];
        return ((Boolean) lazy.getValue()).booleanValue();
    }

    public JvmBuiltInsSettings(@NotNull ModuleDescriptor moduleDescriptor2, @NotNull StorageManager storageManager, @NotNull Function0<? extends ModuleDescriptor> function0, @NotNull Function0<Boolean> function02) {
        Intrinsics.checkParameterIsNotNull(moduleDescriptor2, "moduleDescriptor");
        Intrinsics.checkParameterIsNotNull(storageManager, "storageManager");
        Intrinsics.checkParameterIsNotNull(function0, "deferredOwnerModuleDescriptor");
        Intrinsics.checkParameterIsNotNull(function02, "isAdditionalBuiltInsFeatureSupported");
        this.moduleDescriptor = moduleDescriptor2;
        this.ownerModuleDescriptor$delegate = LazyKt.lazy(function0);
        this.isAdditionalBuiltInsFeatureSupported$delegate = LazyKt.lazy(function02);
        this.mockSerializableType = createMockJavaIoSerializableType(storageManager);
        this.cloneableType$delegate = storageManager.createLazyValue(new JvmBuiltInsSettings$cloneableType$2(this, storageManager));
        this.javaAnalogueClassesWithCustomSupertypeCache = storageManager.createCacheWithNotNullValues();
        this.notConsideredDeprecation$delegate = storageManager.createLazyValue(new JvmBuiltInsSettings$notConsideredDeprecation$2(this));
    }

    private final KotlinType createMockJavaIoSerializableType(@NotNull StorageManager storageManager) {
        ClassDescriptorImpl classDescriptorImpl = new ClassDescriptorImpl(new C2943xaea9d104(this, this.moduleDescriptor, new FqName("java.io")), Name.identifier("Serializable"), Modality.ABSTRACT, ClassKind.INTERFACE, CollectionsKt.listOf(new LazyWrappedType(storageManager, new C2944xf01ceaf8(this))), SourceElement.NO_SOURCE, false, storageManager);
        classDescriptorImpl.initialize(Empty.INSTANCE, SetsKt.emptySet(), null);
        SimpleType defaultType = classDescriptorImpl.getDefaultType();
        Intrinsics.checkExpressionValueIsNotNull(defaultType, "mockSerializableClass.defaultType");
        return defaultType;
    }

    @NotNull
    public Collection<KotlinType> getSupertypes(@NotNull ClassDescriptor classDescriptor) {
        Intrinsics.checkParameterIsNotNull(classDescriptor, "classDescriptor");
        FqNameUnsafe fqNameUnsafe = DescriptorUtilsKt.getFqNameUnsafe(classDescriptor);
        if (Companion.isArrayOrPrimitiveArray(fqNameUnsafe)) {
            SimpleType cloneableType = getCloneableType();
            Intrinsics.checkExpressionValueIsNotNull(cloneableType, "cloneableType");
            return CollectionsKt.listOf(cloneableType, this.mockSerializableType);
        } else if (Companion.isSerializableInJava(fqNameUnsafe)) {
            return CollectionsKt.listOf(this.mockSerializableType);
        } else {
            return CollectionsKt.emptyList();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:48:0x0143  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x00c4 A[SYNTHETIC] */
    @org.jetbrains.annotations.NotNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.Collection<kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor> getFunctions(@org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.name.Name r7, @org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor r8) {
        /*
            r6 = this;
            java.lang.String r0 = "name"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r7, r0)
            java.lang.String r0 = "classDescriptor"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r8, r0)
            kotlin.reflect.jvm.internal.impl.builtins.jvm.CloneableClassScope$Companion r0 = kotlin.reflect.jvm.internal.impl.builtins.jvm.CloneableClassScope.Companion
            kotlin.reflect.jvm.internal.impl.name.Name r0 = r0.getCLONE_NAME$descriptors_jvm()
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r7, r0)
            r1 = 1
            if (r0 == 0) goto L_0x009f
            boolean r0 = r8 instanceof kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedClassDescriptor
            if (r0 == 0) goto L_0x009f
            boolean r0 = kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns.isArrayOrPrimitiveArray(r8)
            if (r0 == 0) goto L_0x009f
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedClassDescriptor r8 = (kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedClassDescriptor) r8
            kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Class r0 = r8.getClassProto()
            java.util.List r0 = r0.getFunctionList()
            java.lang.String r2 = "classDescriptor.classProto.functionList"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r2)
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            boolean r2 = r0 instanceof java.util.Collection
            r3 = 0
            if (r2 == 0) goto L_0x0041
            r2 = r0
            java.util.Collection r2 = (java.util.Collection) r2
            boolean r2 = r2.isEmpty()
            if (r2 == 0) goto L_0x0041
            goto L_0x0073
        L_0x0041:
            java.util.Iterator r0 = r0.iterator()
        L_0x0045:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x0073
            java.lang.Object r2 = r0.next()
            kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Function r2 = (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Function) r2
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext r4 = r8.getC()
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver r4 = r4.getNameResolver()
            java.lang.String r5 = "functionProto"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r2, r5)
            int r2 = r2.getName()
            kotlin.reflect.jvm.internal.impl.name.Name r2 = kotlin.reflect.jvm.internal.impl.serialization.deserialization.NameResolverUtilKt.getName(r4, r2)
            kotlin.reflect.jvm.internal.impl.builtins.jvm.CloneableClassScope$Companion r4 = kotlin.reflect.jvm.internal.impl.builtins.jvm.CloneableClassScope.Companion
            kotlin.reflect.jvm.internal.impl.name.Name r4 = r4.getCLONE_NAME$descriptors_jvm()
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual(r2, r4)
            if (r2 == 0) goto L_0x0045
            r3 = 1
        L_0x0073:
            if (r3 == 0) goto L_0x007c
            java.util.List r7 = kotlin.collections.CollectionsKt.emptyList()
            java.util.Collection r7 = (java.util.Collection) r7
            return r7
        L_0x007c:
            kotlin.reflect.jvm.internal.impl.types.SimpleType r0 = r6.getCloneableType()
            kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope r0 = r0.getMemberScope()
            kotlin.reflect.jvm.internal.impl.incremental.components.NoLookupLocation r1 = kotlin.reflect.jvm.internal.impl.incremental.components.NoLookupLocation.FROM_BUILTINS
            kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation r1 = (kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation) r1
            java.util.Collection r7 = r0.getContributedFunctions(r7, r1)
            java.lang.Iterable r7 = (java.lang.Iterable) r7
            java.lang.Object r7 = kotlin.collections.CollectionsKt.single(r7)
            kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor r7 = (kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor) r7
            kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor r7 = r6.createCloneForArray(r8, r7)
            java.util.List r7 = kotlin.collections.CollectionsKt.listOf(r7)
            java.util.Collection r7 = (java.util.Collection) r7
            return r7
        L_0x009f:
            boolean r0 = r6.isAdditionalBuiltInsFeatureSupported()
            if (r0 != 0) goto L_0x00ac
            java.util.List r7 = kotlin.collections.CollectionsKt.emptyList()
            java.util.Collection r7 = (java.util.Collection) r7
            return r7
        L_0x00ac:
            kotlin.reflect.jvm.internal.impl.builtins.jvm.JvmBuiltInsSettings$getFunctions$2 r0 = new kotlin.reflect.jvm.internal.impl.builtins.jvm.JvmBuiltInsSettings$getFunctions$2
            r0.<init>(r7)
            kotlin.jvm.functions.Function1 r0 = (kotlin.jvm.functions.Function1) r0
            java.util.Collection r7 = r6.getAdditionalFunctions(r8, r0)
            java.lang.Iterable r7 = (java.lang.Iterable) r7
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.Collection r0 = (java.util.Collection) r0
            java.util.Iterator r7 = r7.iterator()
        L_0x00c4:
            boolean r2 = r7.hasNext()
            if (r2 == 0) goto L_0x0158
            java.lang.Object r2 = r7.next()
            kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor r2 = (kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor) r2
            kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor r3 = r2.getContainingDeclaration()
            if (r3 == 0) goto L_0x0150
            kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor r3 = (kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor) r3
            kotlin.reflect.jvm.internal.impl.types.TypeConstructorSubstitution r3 = kotlin.reflect.jvm.internal.impl.builtins.jvm.MappingUtilKt.createMappedTypeParametersSubstitution(r3, r8)
            kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor r3 = r3.buildSubstitutor()
            kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor r3 = r2.substitute(r3)
            if (r3 == 0) goto L_0x0148
            kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor r3 = (kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor) r3
            kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor$CopyBuilder r3 = r3.newCopyBuilder()
            r4 = r8
            kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor r4 = (kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor) r4
            r3.setOwner(r4)
            kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor r4 = r8.getThisAsReceiverParameter()
            r3.setDispatchReceiverParameter(r4)
            r3.setPreserveSourceElement()
            kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor r2 = (kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor) r2
            kotlin.reflect.jvm.internal.impl.builtins.jvm.JvmBuiltInsSettings$JDKMemberStatus r2 = r6.getJdkMethodStatus(r2)
            int[] r4 = kotlin.reflect.jvm.internal.impl.builtins.jvm.JvmBuiltInsSettings.WhenMappings.$EnumSwitchMapping$0
            int r2 = r2.ordinal()
            r2 = r4[r2]
            r4 = 0
            if (r2 == r1) goto L_0x0125
            r5 = 2
            if (r2 == r5) goto L_0x0115
            r5 = 3
            if (r2 == r5) goto L_0x0141
            r4 = 4
            goto L_0x0135
        L_0x0115:
            kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationsImpl r2 = r6.getNotConsideredDeprecation()
            kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations r2 = (kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations) r2
            kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor$CopyBuilder r2 = r3.setAdditionalAnnotations(r2)
            java.lang.String r4 = "setAdditionalAnnotations(notConsideredDeprecation)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r2, r4)
            goto L_0x0135
        L_0x0125:
            boolean r2 = kotlin.reflect.jvm.internal.impl.descriptors.ModalityKt.isFinalClass(r8)
            if (r2 == 0) goto L_0x012c
            goto L_0x0141
        L_0x012c:
            kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor$CopyBuilder r2 = r3.setHiddenForResolutionEverywhereBesideSupercalls()
            java.lang.String r4 = "setHiddenForResolutionEverywhereBesideSupercalls()"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r2, r4)
        L_0x0135:
            kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor r2 = r3.build()
            if (r2 != 0) goto L_0x013e
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x013e:
            r4 = r2
            kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor r4 = (kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor) r4
        L_0x0141:
            if (r4 == 0) goto L_0x00c4
            r0.add(r4)
            goto L_0x00c4
        L_0x0148:
            kotlin.TypeCastException r7 = new kotlin.TypeCastException
            java.lang.String r8 = "null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.SimpleFunctionDescriptor"
            r7.<init>(r8)
            throw r7
        L_0x0150:
            kotlin.TypeCastException r7 = new kotlin.TypeCastException
            java.lang.String r8 = "null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor"
            r7.<init>(r8)
            throw r7
        L_0x0158:
            java.util.List r0 = (java.util.List) r0
            java.util.Collection r0 = (java.util.Collection) r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.builtins.jvm.JvmBuiltInsSettings.getFunctions(kotlin.reflect.jvm.internal.impl.name.Name, kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor):java.util.Collection");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0020, code lost:
        if (r2 != null) goto L_0x0027;
     */
    @org.jetbrains.annotations.NotNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.Set<kotlin.reflect.jvm.internal.impl.name.Name> getFunctionsNames(@org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor r2) {
        /*
            r1 = this;
            java.lang.String r0 = "classDescriptor"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r2, r0)
            boolean r0 = r1.isAdditionalBuiltInsFeatureSupported()
            if (r0 != 0) goto L_0x0010
            java.util.Set r2 = kotlin.collections.SetsKt.emptySet()
            return r2
        L_0x0010:
            kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaClassDescriptor r2 = r1.getJavaAnalogue(r2)
            if (r2 == 0) goto L_0x0023
            kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaClassMemberScope r2 = r2.getUnsubstitutedMemberScope()
            if (r2 == 0) goto L_0x0023
            java.util.Set r2 = r2.getFunctionNames()
            if (r2 == 0) goto L_0x0023
            goto L_0x0027
        L_0x0023:
            java.util.Set r2 = kotlin.collections.SetsKt.emptySet()
        L_0x0027:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.builtins.jvm.JvmBuiltInsSettings.getFunctionsNames(kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor):java.util.Set");
    }

    private final Collection<SimpleFunctionDescriptor> getAdditionalFunctions(ClassDescriptor classDescriptor, Function1<? super MemberScope, ? extends Collection<? extends SimpleFunctionDescriptor>> function1) {
        boolean z;
        LazyJavaClassDescriptor javaAnalogue = getJavaAnalogue(classDescriptor);
        if (javaAnalogue == null) {
            return CollectionsKt.emptyList();
        }
        DeclarationDescriptor declarationDescriptor = javaAnalogue;
        Iterable<ClassDescriptor> mapPlatformClass = this.j2kClassMap.mapPlatformClass(DescriptorUtilsKt.getFqNameSafe(declarationDescriptor), FallbackBuiltIns.Companion.getInstance());
        ClassDescriptor classDescriptor2 = (ClassDescriptor) CollectionsKt.lastOrNull(mapPlatformClass);
        if (classDescriptor2 == null) {
            return CollectionsKt.emptyList();
        }
        kotlin.reflect.jvm.internal.impl.utils.SmartSet.Companion companion = SmartSet.Companion;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(mapPlatformClass, 10));
        for (ClassDescriptor fqNameSafe : mapPlatformClass) {
            arrayList.add(DescriptorUtilsKt.getFqNameSafe(fqNameSafe));
        }
        SmartSet create = companion.create((List) arrayList);
        boolean isMutable = this.j2kClassMap.isMutable(classDescriptor);
        MemberScope unsubstitutedMemberScope = ((ClassDescriptor) this.javaAnalogueClassesWithCustomSupertypeCache.computeIfAbsent(DescriptorUtilsKt.getFqNameSafe(declarationDescriptor), new C2945x1b86dd87(javaAnalogue, classDescriptor2))).getUnsubstitutedMemberScope();
        Intrinsics.checkExpressionValueIsNotNull(unsubstitutedMemberScope, "scope");
        Iterable iterable = (Iterable) function1.invoke(unsubstitutedMemberScope);
        Collection arrayList2 = new ArrayList();
        for (Object next : iterable) {
            SimpleFunctionDescriptor simpleFunctionDescriptor = (SimpleFunctionDescriptor) next;
            boolean z2 = false;
            if (simpleFunctionDescriptor.getKind() == Kind.DECLARATION && simpleFunctionDescriptor.getVisibility().isPublicAPI() && !KotlinBuiltIns.isDeprecated(simpleFunctionDescriptor)) {
                Collection overriddenDescriptors = simpleFunctionDescriptor.getOverriddenDescriptors();
                Intrinsics.checkExpressionValueIsNotNull(overriddenDescriptors, "analogueMember.overriddenDescriptors");
                Iterable iterable2 = overriddenDescriptors;
                if (!(iterable2 instanceof Collection) || !((Collection) iterable2).isEmpty()) {
                    Iterator it = iterable2.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        FunctionDescriptor functionDescriptor = (FunctionDescriptor) it.next();
                        Intrinsics.checkExpressionValueIsNotNull(functionDescriptor, "it");
                        DeclarationDescriptor containingDeclaration = functionDescriptor.getContainingDeclaration();
                        Intrinsics.checkExpressionValueIsNotNull(containingDeclaration, "it.containingDeclaration");
                        if (create.contains(DescriptorUtilsKt.getFqNameSafe(containingDeclaration))) {
                            z = true;
                            break;
                        }
                    }
                }
                z = false;
                if (!z && !isMutabilityViolation(simpleFunctionDescriptor, isMutable)) {
                    z2 = true;
                }
            }
            if (z2) {
                arrayList2.add(next);
            }
        }
        return (List) arrayList2;
    }

    private final SimpleFunctionDescriptor createCloneForArray(DeserializedClassDescriptor deserializedClassDescriptor, SimpleFunctionDescriptor simpleFunctionDescriptor) {
        CopyBuilder newCopyBuilder = simpleFunctionDescriptor.newCopyBuilder();
        newCopyBuilder.setOwner(deserializedClassDescriptor);
        newCopyBuilder.setVisibility(Visibilities.PUBLIC);
        newCopyBuilder.setReturnType(deserializedClassDescriptor.getDefaultType());
        newCopyBuilder.setDispatchReceiverParameter(deserializedClassDescriptor.getThisAsReceiverParameter());
        FunctionDescriptor build = newCopyBuilder.build();
        if (build == null) {
            Intrinsics.throwNpe();
        }
        return (SimpleFunctionDescriptor) build;
    }

    private final boolean isMutabilityViolation(@NotNull SimpleFunctionDescriptor simpleFunctionDescriptor, boolean z) {
        DeclarationDescriptor containingDeclaration = simpleFunctionDescriptor.getContainingDeclaration();
        if (containingDeclaration != null) {
            if (z ^ MUTABLE_METHOD_SIGNATURES.contains(SignatureBuildingComponents.INSTANCE.signature((ClassDescriptor) containingDeclaration, MethodSignatureMappingKt.computeJvmDescriptor$default(simpleFunctionDescriptor, false, false, 3, null)))) {
                return true;
            }
            Boolean ifAny = DFS.ifAny(CollectionsKt.listOf(simpleFunctionDescriptor), JvmBuiltInsSettings$isMutabilityViolation$1.INSTANCE, new JvmBuiltInsSettings$isMutabilityViolation$2(this));
            Intrinsics.checkExpressionValueIsNotNull(ifAny, "DFS.ifAny<CallableMember…lassDescriptor)\n        }");
            return ifAny.booleanValue();
        }
        throw new TypeCastException("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor");
    }

    private final JDKMemberStatus getJdkMethodStatus(@NotNull FunctionDescriptor functionDescriptor) {
        DeclarationDescriptor containingDeclaration = functionDescriptor.getContainingDeclaration();
        if (containingDeclaration != null) {
            ClassDescriptor classDescriptor = (ClassDescriptor) containingDeclaration;
            String computeJvmDescriptor$default = MethodSignatureMappingKt.computeJvmDescriptor$default(functionDescriptor, false, false, 3, null);
            ObjectRef objectRef = new ObjectRef();
            objectRef.element = (JDKMemberStatus) null;
            Object dfs = DFS.dfs(CollectionsKt.listOf(classDescriptor), new JvmBuiltInsSettings$getJdkMethodStatus$1(this), new JvmBuiltInsSettings$getJdkMethodStatus$2(computeJvmDescriptor$default, objectRef));
            Intrinsics.checkExpressionValueIsNotNull(dfs, "DFS.dfs<ClassDescriptor,…CONSIDERED\n            })");
            return (JDKMemberStatus) dfs;
        }
        throw new TypeCastException("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor");
    }

    /* access modifiers changed from: private */
    public final LazyJavaClassDescriptor getJavaAnalogue(@NotNull ClassDescriptor classDescriptor) {
        if (KotlinBuiltIns.isAny(classDescriptor)) {
            return null;
        }
        DeclarationDescriptor declarationDescriptor = classDescriptor;
        if (!KotlinBuiltIns.isUnderKotlinPackage(declarationDescriptor)) {
            return null;
        }
        FqNameUnsafe fqNameUnsafe = DescriptorUtilsKt.getFqNameUnsafe(declarationDescriptor);
        if (!fqNameUnsafe.isSafe()) {
            return null;
        }
        ClassId mapKotlinToJava = this.j2kClassMap.mapKotlinToJava(fqNameUnsafe);
        if (mapKotlinToJava != null) {
            FqName asSingleFqName = mapKotlinToJava.asSingleFqName();
            if (asSingleFqName != null) {
                ModuleDescriptor ownerModuleDescriptor = getOwnerModuleDescriptor();
                Intrinsics.checkExpressionValueIsNotNull(asSingleFqName, "javaAnalogueFqName");
                ClassDescriptor resolveClassByFqName = DescriptorUtilKt.resolveClassByFqName(ownerModuleDescriptor, asSingleFqName, NoLookupLocation.FROM_BUILTINS);
                if (!(resolveClassByFqName instanceof LazyJavaClassDescriptor)) {
                    resolveClassByFqName = null;
                }
                return (LazyJavaClassDescriptor) resolveClassByFqName;
            }
        }
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:34:0x00e1  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0058 A[SYNTHETIC] */
    @org.jetbrains.annotations.NotNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.Collection<kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor> getConstructors(@org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor r18) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            java.lang.String r2 = "classDescriptor"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r1, r2)
            kotlin.reflect.jvm.internal.impl.descriptors.ClassKind r2 = r18.getKind()
            kotlin.reflect.jvm.internal.impl.descriptors.ClassKind r3 = kotlin.reflect.jvm.internal.impl.descriptors.ClassKind.CLASS
            if (r2 != r3) goto L_0x016b
            boolean r2 = r17.isAdditionalBuiltInsFeatureSupported()
            if (r2 != 0) goto L_0x0019
            goto L_0x016b
        L_0x0019:
            kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaClassDescriptor r2 = r17.getJavaAnalogue(r18)
            if (r2 == 0) goto L_0x0164
            kotlin.reflect.jvm.internal.impl.builtins.jvm.JavaToKotlinClassMap r3 = r0.j2kClassMap
            r4 = r2
            kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor r4 = (kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor) r4
            kotlin.reflect.jvm.internal.impl.name.FqName r4 = kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt.getFqNameSafe(r4)
            kotlin.reflect.jvm.internal.impl.builtins.jvm.FallbackBuiltIns$Companion r5 = kotlin.reflect.jvm.internal.impl.builtins.jvm.FallbackBuiltIns.Companion
            kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns r5 = r5.getInstance()
            r6 = 0
            r7 = 4
            r8 = 0
            kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor r3 = kotlin.reflect.jvm.internal.impl.builtins.jvm.JavaToKotlinClassMap.mapJavaToKotlin$default(r3, r4, r5, r6, r7, r8)
            if (r3 == 0) goto L_0x015d
            r4 = r2
            kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor r4 = (kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor) r4
            kotlin.reflect.jvm.internal.impl.types.TypeConstructorSubstitution r5 = kotlin.reflect.jvm.internal.impl.builtins.jvm.MappingUtilKt.createMappedTypeParametersSubstitution(r3, r4)
            kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor r5 = r5.buildSubstitutor()
            kotlin.reflect.jvm.internal.impl.builtins.jvm.JvmBuiltInsSettings$getConstructors$1 r6 = new kotlin.reflect.jvm.internal.impl.builtins.jvm.JvmBuiltInsSettings$getConstructors$1
            r6.<init>(r5)
            java.util.List r2 = r2.getConstructors()
            java.lang.Iterable r2 = (java.lang.Iterable) r2
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            java.util.Collection r7 = (java.util.Collection) r7
            java.util.Iterator r2 = r2.iterator()
        L_0x0058:
            boolean r8 = r2.hasNext()
            r9 = 0
            r10 = 3
            java.lang.String r11 = "javaConstructor"
            r12 = 0
            if (r8 == 0) goto L_0x00e6
            java.lang.Object r8 = r2.next()
            r13 = r8
            kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor r13 = (kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor) r13
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r13, r11)
            kotlin.reflect.jvm.internal.impl.descriptors.Visibility r11 = r13.getVisibility()
            boolean r11 = r11.isPublicAPI()
            if (r11 == 0) goto L_0x00dd
            java.util.Collection r11 = r3.getConstructors()
            java.lang.String r15 = "defaultKotlinVersion.constructors"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r11, r15)
            java.lang.Iterable r11 = (java.lang.Iterable) r11
            boolean r15 = r11 instanceof java.util.Collection
            if (r15 == 0) goto L_0x0091
            r15 = r11
            java.util.Collection r15 = (java.util.Collection) r15
            boolean r15 = r15.isEmpty()
            if (r15 == 0) goto L_0x0091
        L_0x008f:
            r14 = 1
            goto L_0x00b2
        L_0x0091:
            java.util.Iterator r11 = r11.iterator()
        L_0x0095:
            boolean r15 = r11.hasNext()
            if (r15 == 0) goto L_0x008f
            java.lang.Object r15 = r11.next()
            kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor r15 = (kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor) r15
            java.lang.String r14 = "it"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r15, r14)
            kotlin.reflect.jvm.internal.impl.descriptors.ConstructorDescriptor r15 = (kotlin.reflect.jvm.internal.impl.descriptors.ConstructorDescriptor) r15
            r14 = r13
            kotlin.reflect.jvm.internal.impl.descriptors.ConstructorDescriptor r14 = (kotlin.reflect.jvm.internal.impl.descriptors.ConstructorDescriptor) r14
            boolean r14 = r6.invoke(r15, r14)
            if (r14 == 0) goto L_0x0095
            r14 = 0
        L_0x00b2:
            if (r14 == 0) goto L_0x00dd
            r11 = r13
            kotlin.reflect.jvm.internal.impl.descriptors.ConstructorDescriptor r11 = (kotlin.reflect.jvm.internal.impl.descriptors.ConstructorDescriptor) r11
            boolean r11 = r0.isTrivialCopyConstructorFor(r11, r1)
            if (r11 != 0) goto L_0x00dd
            r11 = r13
            kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor r11 = (kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor) r11
            boolean r11 = kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns.isDeprecated(r11)
            if (r11 != 0) goto L_0x00dd
            java.util.Set<java.lang.String> r11 = BLACK_LIST_CONSTRUCTOR_SIGNATURES
            kotlin.reflect.jvm.internal.impl.load.kotlin.SignatureBuildingComponents r14 = kotlin.reflect.jvm.internal.impl.load.kotlin.SignatureBuildingComponents.INSTANCE
            kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor r13 = (kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor) r13
            java.lang.String r9 = kotlin.reflect.jvm.internal.impl.load.kotlin.MethodSignatureMappingKt.computeJvmDescriptor$default(r13, r12, r12, r10, r9)
            java.lang.String r9 = r14.signature(r4, r9)
            boolean r9 = r11.contains(r9)
            if (r9 != 0) goto L_0x00dd
            r16 = 1
            goto L_0x00df
        L_0x00dd:
            r16 = 0
        L_0x00df:
            if (r16 == 0) goto L_0x0058
            r7.add(r8)
            goto L_0x0058
        L_0x00e6:
            java.util.List r7 = (java.util.List) r7
            java.lang.Iterable r7 = (java.lang.Iterable) r7
            java.util.ArrayList r2 = new java.util.ArrayList
            r3 = 10
            int r3 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r7, r3)
            r2.<init>(r3)
            java.util.Collection r2 = (java.util.Collection) r2
            java.util.Iterator r3 = r7.iterator()
        L_0x00fb:
            boolean r6 = r3.hasNext()
            if (r6 == 0) goto L_0x0158
            java.lang.Object r6 = r3.next()
            kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor r6 = (kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor) r6
            kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor$CopyBuilder r7 = r6.newCopyBuilder()
            r8 = r1
            kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor r8 = (kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor) r8
            r7.setOwner(r8)
            kotlin.reflect.jvm.internal.impl.types.SimpleType r8 = r18.getDefaultType()
            kotlin.reflect.jvm.internal.impl.types.KotlinType r8 = (kotlin.reflect.jvm.internal.impl.types.KotlinType) r8
            r7.setReturnType(r8)
            r7.setPreserveSourceElement()
            kotlin.reflect.jvm.internal.impl.types.TypeSubstitution r8 = r5.getSubstitution()
            r7.setSubstitution(r8)
            java.util.Set<java.lang.String> r8 = WHITE_LIST_CONSTRUCTOR_SIGNATURES
            kotlin.reflect.jvm.internal.impl.load.kotlin.SignatureBuildingComponents r13 = kotlin.reflect.jvm.internal.impl.load.kotlin.SignatureBuildingComponents.INSTANCE
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r6, r11)
            kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor r6 = (kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor) r6
            java.lang.String r6 = kotlin.reflect.jvm.internal.impl.load.kotlin.MethodSignatureMappingKt.computeJvmDescriptor$default(r6, r12, r12, r10, r9)
            java.lang.String r6 = r13.signature(r4, r6)
            boolean r6 = r8.contains(r6)
            if (r6 != 0) goto L_0x0144
            kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationsImpl r6 = r17.getNotConsideredDeprecation()
            kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations r6 = (kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations) r6
            r7.setAdditionalAnnotations(r6)
        L_0x0144:
            kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor r6 = r7.build()
            if (r6 == 0) goto L_0x0150
            kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor r6 = (kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor) r6
            r2.add(r6)
            goto L_0x00fb
        L_0x0150:
            kotlin.TypeCastException r1 = new kotlin.TypeCastException
            java.lang.String r2 = "null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassConstructorDescriptor"
            r1.<init>(r2)
            throw r1
        L_0x0158:
            java.util.List r2 = (java.util.List) r2
            java.util.Collection r2 = (java.util.Collection) r2
            return r2
        L_0x015d:
            java.util.List r1 = kotlin.collections.CollectionsKt.emptyList()
            java.util.Collection r1 = (java.util.Collection) r1
            return r1
        L_0x0164:
            java.util.List r1 = kotlin.collections.CollectionsKt.emptyList()
            java.util.Collection r1 = (java.util.Collection) r1
            return r1
        L_0x016b:
            java.util.List r1 = kotlin.collections.CollectionsKt.emptyList()
            java.util.Collection r1 = (java.util.Collection) r1
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.builtins.jvm.JvmBuiltInsSettings.getConstructors(kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor):java.util.Collection");
    }

    public boolean isFunctionAvailable(@NotNull ClassDescriptor classDescriptor, @NotNull SimpleFunctionDescriptor simpleFunctionDescriptor) {
        Intrinsics.checkParameterIsNotNull(classDescriptor, "classDescriptor");
        Intrinsics.checkParameterIsNotNull(simpleFunctionDescriptor, "functionDescriptor");
        LazyJavaClassDescriptor javaAnalogue = getJavaAnalogue(classDescriptor);
        if (javaAnalogue == null || !simpleFunctionDescriptor.getAnnotations().hasAnnotation(PlatformDependentDeclarationFilterKt.getPLATFORM_DEPENDENT_ANNOTATION_FQ_NAME())) {
            return true;
        }
        boolean z = false;
        if (!isAdditionalBuiltInsFeatureSupported()) {
            return false;
        }
        String computeJvmDescriptor$default = MethodSignatureMappingKt.computeJvmDescriptor$default(simpleFunctionDescriptor, false, false, 3, null);
        LazyJavaClassMemberScope unsubstitutedMemberScope = javaAnalogue.getUnsubstitutedMemberScope();
        Name name = simpleFunctionDescriptor.getName();
        Intrinsics.checkExpressionValueIsNotNull(name, "functionDescriptor.name");
        Iterable contributedFunctions = unsubstitutedMemberScope.getContributedFunctions(name, NoLookupLocation.FROM_BUILTINS);
        if (!(contributedFunctions instanceof Collection) || !((Collection) contributedFunctions).isEmpty()) {
            Iterator it = contributedFunctions.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (Intrinsics.areEqual((Object) MethodSignatureMappingKt.computeJvmDescriptor$default((SimpleFunctionDescriptor) it.next(), false, false, 3, null), (Object) computeJvmDescriptor$default)) {
                        z = true;
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        return z;
    }

    private final boolean isTrivialCopyConstructorFor(@NotNull ConstructorDescriptor constructorDescriptor, ClassDescriptor classDescriptor) {
        if (constructorDescriptor.getValueParameters().size() == 1) {
            List valueParameters = constructorDescriptor.getValueParameters();
            Intrinsics.checkExpressionValueIsNotNull(valueParameters, "valueParameters");
            Object single = CollectionsKt.single(valueParameters);
            Intrinsics.checkExpressionValueIsNotNull(single, "valueParameters.single()");
            ClassifierDescriptor declarationDescriptor = ((ValueParameterDescriptor) single).getType().getConstructor().getDeclarationDescriptor();
            if (Intrinsics.areEqual((Object) declarationDescriptor != null ? DescriptorUtilsKt.getFqNameUnsafe(declarationDescriptor) : null, (Object) DescriptorUtilsKt.getFqNameUnsafe(classDescriptor))) {
                return true;
            }
        }
        return false;
    }

    static {
        String str = "Collection";
        DROP_LIST_METHOD_SIGNATURES = SetsKt.plus((Set<? extends T>) SignatureBuildingComponents.INSTANCE.inJavaUtil(str, "toArray()[Ljava/lang/Object;", "toArray([Ljava/lang/Object;)[Ljava/lang/Object;"), "java/lang/annotation/Annotation.annotationType()Ljava/lang/Class;");
        SignatureBuildingComponents signatureBuildingComponents = SignatureBuildingComponents.INSTANCE;
        String str2 = "sort(Ljava/util/Comparator;)V";
        String str3 = "List";
        String str4 = "String";
        Set plus = SetsKt.plus(SetsKt.plus(Companion.buildPrimitiveValueMethodsSet(), (Iterable<? extends T>) signatureBuildingComponents.inJavaUtil(str3, str2)), (Iterable<? extends T>) signatureBuildingComponents.inJavaLang(str4, "codePointAt(I)I", "codePointBefore(I)I", "codePointCount(II)I", "compareToIgnoreCase(Ljava/lang/String;)I", "concat(Ljava/lang/String;)Ljava/lang/String;", "contains(Ljava/lang/CharSequence;)Z", "contentEquals(Ljava/lang/CharSequence;)Z", "contentEquals(Ljava/lang/StringBuffer;)Z", "endsWith(Ljava/lang/String;)Z", "equalsIgnoreCase(Ljava/lang/String;)Z", "getBytes()[B", "getBytes(II[BI)V", "getBytes(Ljava/lang/String;)[B", "getBytes(Ljava/nio/charset/Charset;)[B", "getChars(II[CI)V", "indexOf(I)I", "indexOf(II)I", "indexOf(Ljava/lang/String;)I", "indexOf(Ljava/lang/String;I)I", "intern()Ljava/lang/String;", "isEmpty()Z", "lastIndexOf(I)I", "lastIndexOf(II)I", "lastIndexOf(Ljava/lang/String;)I", "lastIndexOf(Ljava/lang/String;I)I", "matches(Ljava/lang/String;)Z", "offsetByCodePoints(II)I", "regionMatches(ILjava/lang/String;II)Z", "regionMatches(ZILjava/lang/String;II)Z", "replaceAll(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", "replace(CC)Ljava/lang/String;", "replaceFirst(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", "replace(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;", "split(Ljava/lang/String;I)[Ljava/lang/String;", "split(Ljava/lang/String;)[Ljava/lang/String;", "startsWith(Ljava/lang/String;I)Z", "startsWith(Ljava/lang/String;)Z", "substring(II)Ljava/lang/String;", "substring(I)Ljava/lang/String;", "toCharArray()[C", "toLowerCase()Ljava/lang/String;", "toLowerCase(Ljava/util/Locale;)Ljava/lang/String;", "toUpperCase()Ljava/lang/String;", "toUpperCase(Ljava/util/Locale;)Ljava/lang/String;", "trim()Ljava/lang/String;", "isBlank()Z", "lines()Ljava/util/stream/Stream;", "repeat(I)Ljava/lang/String;"));
        String str5 = "isNaN()Z";
        String str6 = "isInfinite()Z";
        String[] strArr = {str6, str5};
        String str7 = "Float";
        BLACK_LIST_METHOD_SIGNATURES = SetsKt.plus(SetsKt.plus(SetsKt.plus(plus, (Iterable<? extends T>) signatureBuildingComponents.inJavaLang("Double", str6, str5)), (Iterable<? extends T>) signatureBuildingComponents.inJavaLang(str7, strArr)), (Iterable<? extends T>) signatureBuildingComponents.inJavaLang("Enum", "getDeclaringClass()Ljava/lang/Class;", "finalize()V"));
        SignatureBuildingComponents signatureBuildingComponents2 = SignatureBuildingComponents.INSTANCE;
        String str8 = "spliterator()Ljava/util/Spliterator;";
        String str9 = "Throwable";
        Set plus2 = SetsKt.plus(SetsKt.plus(SetsKt.plus((Set<? extends T>) signatureBuildingComponents2.inJavaLang("CharSequence", "codePoints()Ljava/util/stream/IntStream;", "chars()Ljava/util/stream/IntStream;"), (Iterable<? extends T>) signatureBuildingComponents2.inJavaUtil("Iterator", "forEachRemaining(Ljava/util/function/Consumer;)V")), (Iterable<? extends T>) signatureBuildingComponents2.inJavaLang("Iterable", "forEach(Ljava/util/function/Consumer;)V", str8)), (Iterable<? extends T>) signatureBuildingComponents2.inJavaLang(str9, "setStackTrace([Ljava/lang/StackTraceElement;)V", "fillInStackTrace()Ljava/lang/Throwable;", "getLocalizedMessage()Ljava/lang/String;", "printStackTrace()V", "printStackTrace(Ljava/io/PrintStream;)V", "printStackTrace(Ljava/io/PrintWriter;)V", "getStackTrace()[Ljava/lang/StackTraceElement;", "initCause(Ljava/lang/Throwable;)Ljava/lang/Throwable;", "getSuppressed()[Ljava/lang/Throwable;", "addSuppressed(Ljava/lang/Throwable;)V"));
        String str10 = "removeIf(Ljava/util/function/Predicate;)Z";
        Set plus3 = SetsKt.plus(plus2, (Iterable<? extends T>) signatureBuildingComponents2.inJavaUtil(str, str8, "parallelStream()Ljava/util/stream/Stream;", "stream()Ljava/util/stream/Stream;", str10));
        String str11 = "replaceAll(Ljava/util/function/UnaryOperator;)V";
        String str12 = "Map";
        WHITE_LIST_METHOD_SIGNATURES = SetsKt.plus(SetsKt.plus(plus3, (Iterable<? extends T>) signatureBuildingComponents2.inJavaUtil(str3, str11)), (Iterable<? extends T>) signatureBuildingComponents2.inJavaUtil(str12, "getOrDefault(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "forEach(Ljava/util/function/BiConsumer;)V", "replaceAll(Ljava/util/function/BiFunction;)V", "merge(Ljava/lang/Object;Ljava/lang/Object;Ljava/util/function/BiFunction;)Ljava/lang/Object;", "computeIfPresent(Ljava/lang/Object;Ljava/util/function/BiFunction;)Ljava/lang/Object;", "putIfAbsent(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "replace(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Z", "replace(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "computeIfAbsent(Ljava/lang/Object;Ljava/util/function/Function;)Ljava/lang/Object;", "compute(Ljava/lang/Object;Ljava/util/function/BiFunction;)Ljava/lang/Object;"));
        SignatureBuildingComponents signatureBuildingComponents3 = SignatureBuildingComponents.INSTANCE;
        MUTABLE_METHOD_SIGNATURES = SetsKt.plus(SetsKt.plus((Set<? extends T>) signatureBuildingComponents3.inJavaUtil(str, str10), (Iterable<? extends T>) signatureBuildingComponents3.inJavaUtil(str3, str11, str2)), (Iterable<? extends T>) signatureBuildingComponents3.inJavaUtil(str12, "computeIfAbsent(Ljava/lang/Object;Ljava/util/function/Function;)Ljava/lang/Object;", "computeIfPresent(Ljava/lang/Object;Ljava/util/function/BiFunction;)Ljava/lang/Object;", "compute(Ljava/lang/Object;Ljava/util/function/BiFunction;)Ljava/lang/Object;", "merge(Ljava/lang/Object;Ljava/lang/Object;Ljava/util/function/BiFunction;)Ljava/lang/Object;", "putIfAbsent(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "remove(Ljava/lang/Object;Ljava/lang/Object;)Z", "replaceAll(Ljava/util/function/BiFunction;)V", "replace(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "replace(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Z"));
        SignatureBuildingComponents signatureBuildingComponents4 = SignatureBuildingComponents.INSTANCE;
        Set access$buildPrimitiveStringConstructorsSet = Companion.buildPrimitiveStringConstructorsSet();
        String[] constructors = signatureBuildingComponents4.constructors("D");
        Set plus4 = SetsKt.plus(access$buildPrimitiveStringConstructorsSet, (Iterable<? extends T>) signatureBuildingComponents4.inJavaLang(str7, (String[]) Arrays.copyOf(constructors, constructors.length)));
        String[] constructors2 = signatureBuildingComponents4.constructors("[C", "[CII", "[III", "[BIILjava/lang/String;", "[BIILjava/nio/charset/Charset;", "[BLjava/lang/String;", "[BLjava/nio/charset/Charset;", "[BII", "[B", "Ljava/lang/StringBuffer;", "Ljava/lang/StringBuilder;");
        BLACK_LIST_CONSTRUCTOR_SIGNATURES = SetsKt.plus(plus4, (Iterable<? extends T>) signatureBuildingComponents4.inJavaLang(str4, (String[]) Arrays.copyOf(constructors2, constructors2.length)));
        SignatureBuildingComponents signatureBuildingComponents5 = SignatureBuildingComponents.INSTANCE;
        String[] constructors3 = signatureBuildingComponents5.constructors("Ljava/lang/String;Ljava/lang/Throwable;ZZ");
        WHITE_LIST_CONSTRUCTOR_SIGNATURES = signatureBuildingComponents5.inJavaLang(str9, (String[]) Arrays.copyOf(constructors3, constructors3.length));
    }
}
