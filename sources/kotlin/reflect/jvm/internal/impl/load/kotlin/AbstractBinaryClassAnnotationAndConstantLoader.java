package kotlin.reflect.jvm.internal.impl.load.kotlin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.UnsignedTypes;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationUseSiteTarget;
import kotlin.reflect.jvm.internal.impl.load.java.JvmAnnotationNames;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationArgumentVisitor;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Annotation;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.C3063Type;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Class.Kind;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Constructor;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.EnumEntry;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Function;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Property;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeParameter;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.ValueParameter;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.ProtoBufUtilKt;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.ProtoTypeTableUtilKt;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.JvmMethodSignature;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.JvmPropertySignature;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.ClassMapperLite;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMemberSignature.Field;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMemberSignature.Method;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmProtoBufUtil;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.ExtendableMessage;
import kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.GeneratedExtension;
import kotlin.reflect.jvm.internal.impl.protobuf.MessageLite;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmClassName;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotatedCallableKind;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotationAndConstantLoader;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoContainer;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoContainer.Class;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoContainer.Package;
import kotlin.reflect.jvm.internal.impl.storage.MemoizedFunctionToNotNull;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: AbstractBinaryClassAnnotationAndConstantLoader.kt */
public abstract class AbstractBinaryClassAnnotationAndConstantLoader<A, C, T> implements AnnotationAndConstantLoader<A, C, T> {
    @Deprecated
    public static final Companion Companion = new Companion(null);
    @NotNull
    private static final Set<ClassId> SPECIAL_ANNOTATIONS;
    private final KotlinClassFinder kotlinClassFinder;
    private final MemoizedFunctionToNotNull<KotlinJvmBinaryClass, Storage<A, C>> storage;

    /* compiled from: AbstractBinaryClassAnnotationAndConstantLoader.kt */
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* compiled from: AbstractBinaryClassAnnotationAndConstantLoader.kt */
    private static final class Storage<A, C> {
        @NotNull
        private final Map<MemberSignature, List<A>> memberAnnotations;
        @NotNull
        private final Map<MemberSignature, C> propertyConstants;

        public Storage(@NotNull Map<MemberSignature, ? extends List<? extends A>> map, @NotNull Map<MemberSignature, ? extends C> map2) {
            Intrinsics.checkParameterIsNotNull(map, "memberAnnotations");
            Intrinsics.checkParameterIsNotNull(map2, "propertyConstants");
            this.memberAnnotations = map;
            this.propertyConstants = map2;
        }

        @NotNull
        public final Map<MemberSignature, List<A>> getMemberAnnotations() {
            return this.memberAnnotations;
        }

        @NotNull
        public final Map<MemberSignature, C> getPropertyConstants() {
            return this.propertyConstants;
        }
    }

    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[AnnotatedCallableKind.values().length];

        static {
            $EnumSwitchMapping$0[AnnotatedCallableKind.PROPERTY_GETTER.ordinal()] = 1;
            $EnumSwitchMapping$0[AnnotatedCallableKind.PROPERTY_SETTER.ordinal()] = 2;
            $EnumSwitchMapping$0[AnnotatedCallableKind.PROPERTY.ordinal()] = 3;
        }
    }

    /* access modifiers changed from: protected */
    @Nullable
    public byte[] getCachedFileContent(@NotNull KotlinJvmBinaryClass kotlinJvmBinaryClass) {
        Intrinsics.checkParameterIsNotNull(kotlinJvmBinaryClass, "kotlinClass");
        return null;
    }

    /* access modifiers changed from: protected */
    @Nullable
    public abstract AnnotationArgumentVisitor loadAnnotation(@NotNull ClassId classId, @NotNull SourceElement sourceElement, @NotNull List<A> list);

    /* access modifiers changed from: protected */
    @Nullable
    public abstract C loadConstant(@NotNull String str, @NotNull Object obj);

    /* access modifiers changed from: protected */
    @NotNull
    public abstract List<T> loadPropertyAnnotations(@NotNull List<? extends A> list, @NotNull List<? extends A> list2, @NotNull AnnotationUseSiteTarget annotationUseSiteTarget);

    /* access modifiers changed from: protected */
    @NotNull
    public abstract A loadTypeAnnotation(@NotNull Annotation annotation, @NotNull NameResolver nameResolver);

    /* access modifiers changed from: protected */
    @NotNull
    public abstract List<T> transformAnnotations(@NotNull List<? extends A> list);

    /* access modifiers changed from: protected */
    @Nullable
    public abstract C transformToUnsignedConstant(@NotNull C c);

    public AbstractBinaryClassAnnotationAndConstantLoader(@NotNull StorageManager storageManager, @NotNull KotlinClassFinder kotlinClassFinder2) {
        Intrinsics.checkParameterIsNotNull(storageManager, "storageManager");
        Intrinsics.checkParameterIsNotNull(kotlinClassFinder2, "kotlinClassFinder");
        this.kotlinClassFinder = kotlinClassFinder2;
        this.storage = storageManager.createMemoizedFunction(new AbstractBinaryClassAnnotationAndConstantLoader$storage$1(this));
    }

    /* access modifiers changed from: private */
    public final AnnotationArgumentVisitor loadAnnotationIfNotSpecial(ClassId classId, SourceElement sourceElement, List<A> list) {
        if (SPECIAL_ANNOTATIONS.contains(classId)) {
            return null;
        }
        return loadAnnotation(classId, sourceElement, list);
    }

    private final KotlinJvmBinaryClass toBinaryClass(@NotNull Class classR) {
        SourceElement source = classR.getSource();
        if (!(source instanceof KotlinJvmBinarySourceElement)) {
            source = null;
        }
        KotlinJvmBinarySourceElement kotlinJvmBinarySourceElement = (KotlinJvmBinarySourceElement) source;
        if (kotlinJvmBinarySourceElement != null) {
            return kotlinJvmBinarySourceElement.getBinaryClass();
        }
        return null;
    }

    @NotNull
    public List<A> loadClassAnnotations(@NotNull Class classR) {
        Intrinsics.checkParameterIsNotNull(classR, "container");
        KotlinJvmBinaryClass binaryClass = toBinaryClass(classR);
        if (binaryClass != null) {
            ArrayList arrayList = new ArrayList(1);
            binaryClass.loadClassAnnotations(new C3029xc0ff27f9(this, arrayList), getCachedFileContent(binaryClass));
            return arrayList;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Class for loading annotations is not found: ");
        sb.append(classR.debugFqName());
        throw new IllegalStateException(sb.toString().toString());
    }

    @NotNull
    public List<T> loadCallableAnnotations(@NotNull ProtoContainer protoContainer, @NotNull MessageLite messageLite, @NotNull AnnotatedCallableKind annotatedCallableKind) {
        AnnotationUseSiteTarget annotationUseSiteTarget;
        MessageLite messageLite2 = messageLite;
        AnnotatedCallableKind annotatedCallableKind2 = annotatedCallableKind;
        Intrinsics.checkParameterIsNotNull(protoContainer, "container");
        Intrinsics.checkParameterIsNotNull(messageLite2, "proto");
        Intrinsics.checkParameterIsNotNull(annotatedCallableKind2, "kind");
        if (annotatedCallableKind2 == AnnotatedCallableKind.PROPERTY) {
            Property property = (Property) messageLite2;
            Property property2 = property;
            MemberSignature propertySignature$default = getPropertySignature$default(this, property2, protoContainer.getNameResolver(), protoContainer.getTypeTable(), false, true, 8, null);
            MemberSignature propertySignature$default2 = getPropertySignature$default(this, property2, protoContainer.getNameResolver(), protoContainer.getTypeTable(), true, false, 16, null);
            Boolean bool = Flags.IS_CONST.get(property.getFlags());
            boolean isMovedFromInterfaceCompanion = JvmProtoBufUtil.isMovedFromInterfaceCompanion(property);
            List findClassAndLoadMemberAnnotations$default = propertySignature$default != null ? findClassAndLoadMemberAnnotations$default(this, protoContainer, propertySignature$default, true, false, bool, isMovedFromInterfaceCompanion, 8, null) : null;
            if (findClassAndLoadMemberAnnotations$default == null) {
                findClassAndLoadMemberAnnotations$default = CollectionsKt.emptyList();
            }
            List list = findClassAndLoadMemberAnnotations$default;
            List findClassAndLoadMemberAnnotations = propertySignature$default2 != null ? findClassAndLoadMemberAnnotations(protoContainer, propertySignature$default2, true, true, bool, isMovedFromInterfaceCompanion) : null;
            if (findClassAndLoadMemberAnnotations == null) {
                findClassAndLoadMemberAnnotations = CollectionsKt.emptyList();
            }
            boolean z = false;
            if (propertySignature$default2 != null) {
                String signature$descriptors_jvm = propertySignature$default2.getSignature$descriptors_jvm();
                if (signature$descriptors_jvm != null) {
                    z = StringsKt.contains$default((CharSequence) signature$descriptors_jvm, (CharSequence) "$delegate", false, 2, (Object) null);
                }
            }
            if (z) {
                annotationUseSiteTarget = AnnotationUseSiteTarget.PROPERTY_DELEGATE_FIELD;
            } else {
                annotationUseSiteTarget = AnnotationUseSiteTarget.FIELD;
            }
            return loadPropertyAnnotations(list, findClassAndLoadMemberAnnotations, annotationUseSiteTarget);
        }
        MemberSignature callableSignature = getCallableSignature(messageLite2, protoContainer.getNameResolver(), protoContainer.getTypeTable(), annotatedCallableKind2);
        if (callableSignature != null) {
            return transformAnnotations(findClassAndLoadMemberAnnotations$default(this, protoContainer, callableSignature, false, false, null, false, 60, null));
        }
        return CollectionsKt.emptyList();
    }

    @NotNull
    public List<A> loadEnumEntryAnnotations(@NotNull ProtoContainer protoContainer, @NotNull EnumEntry enumEntry) {
        Intrinsics.checkParameterIsNotNull(protoContainer, "container");
        Intrinsics.checkParameterIsNotNull(enumEntry, "proto");
        kotlin.reflect.jvm.internal.impl.load.kotlin.MemberSignature.Companion companion = MemberSignature.Companion;
        String string = protoContainer.getNameResolver().getString(enumEntry.getName());
        String asString = ((Class) protoContainer).getClassId().asString();
        Intrinsics.checkExpressionValueIsNotNull(asString, "(container as ProtoConta…Class).classId.asString()");
        return findClassAndLoadMemberAnnotations$default(this, protoContainer, companion.fromFieldNameAndDesc(string, ClassMapperLite.mapClass(asString)), false, false, null, false, 60, null);
    }

    static /* bridge */ /* synthetic */ List findClassAndLoadMemberAnnotations$default(AbstractBinaryClassAnnotationAndConstantLoader abstractBinaryClassAnnotationAndConstantLoader, ProtoContainer protoContainer, MemberSignature memberSignature, boolean z, boolean z2, Boolean bool, boolean z3, int i, Object obj) {
        if (obj == null) {
            return abstractBinaryClassAnnotationAndConstantLoader.findClassAndLoadMemberAnnotations(protoContainer, memberSignature, (i & 4) != 0 ? false : z, (i & 8) != 0 ? false : z2, (i & 16) != 0 ? null : bool, (i & 32) != 0 ? false : z3);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: findClassAndLoadMemberAnnotations");
    }

    private final List<A> findClassAndLoadMemberAnnotations(ProtoContainer protoContainer, MemberSignature memberSignature, boolean z, boolean z2, Boolean bool, boolean z3) {
        KotlinJvmBinaryClass findClassWithAnnotationsAndInitializers = findClassWithAnnotationsAndInitializers(protoContainer, getSpecialCaseContainerClass(protoContainer, z, z2, bool, z3));
        if (findClassWithAnnotationsAndInitializers == null) {
            return CollectionsKt.emptyList();
        }
        List<A> list = (List) ((Storage) this.storage.invoke(findClassWithAnnotationsAndInitializers)).getMemberAnnotations().get(memberSignature);
        if (list == null) {
            list = CollectionsKt.emptyList();
        }
        return list;
    }

    @NotNull
    public List<A> loadValueParameterAnnotations(@NotNull ProtoContainer protoContainer, @NotNull MessageLite messageLite, @NotNull AnnotatedCallableKind annotatedCallableKind, int i, @NotNull ValueParameter valueParameter) {
        Intrinsics.checkParameterIsNotNull(protoContainer, "container");
        Intrinsics.checkParameterIsNotNull(messageLite, "callableProto");
        Intrinsics.checkParameterIsNotNull(annotatedCallableKind, "kind");
        Intrinsics.checkParameterIsNotNull(valueParameter, "proto");
        MemberSignature callableSignature = getCallableSignature(messageLite, protoContainer.getNameResolver(), protoContainer.getTypeTable(), annotatedCallableKind);
        if (callableSignature == null) {
            return CollectionsKt.emptyList();
        }
        return findClassAndLoadMemberAnnotations$default(this, protoContainer, MemberSignature.Companion.fromMethodSignatureAndParameterIndex(callableSignature, i + computeJvmParameterIndexShift(protoContainer, messageLite)), false, false, null, false, 60, null);
    }

    private final int computeJvmParameterIndexShift(ProtoContainer protoContainer, MessageLite messageLite) {
        if (messageLite instanceof Function) {
            if (ProtoTypeTableUtilKt.hasReceiver((Function) messageLite)) {
                return 1;
            }
        } else if (messageLite instanceof Property) {
            if (ProtoTypeTableUtilKt.hasReceiver((Property) messageLite)) {
                return 1;
            }
        } else if (!(messageLite instanceof Constructor)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Unsupported message: ");
            sb.append(messageLite.getClass());
            throw new UnsupportedOperationException(sb.toString());
        } else if (protoContainer != null) {
            Class classR = (Class) protoContainer;
            if (classR.getKind() == Kind.ENUM_CLASS) {
                return 2;
            }
            if (classR.isInner()) {
                return 1;
            }
        } else {
            throw new TypeCastException("null cannot be cast to non-null type org.jetbrains.kotlin.serialization.deserialization.ProtoContainer.Class");
        }
        return 0;
    }

    @NotNull
    public List<A> loadExtensionReceiverParameterAnnotations(@NotNull ProtoContainer protoContainer, @NotNull MessageLite messageLite, @NotNull AnnotatedCallableKind annotatedCallableKind) {
        Intrinsics.checkParameterIsNotNull(protoContainer, "container");
        Intrinsics.checkParameterIsNotNull(messageLite, "proto");
        Intrinsics.checkParameterIsNotNull(annotatedCallableKind, "kind");
        MemberSignature callableSignature = getCallableSignature(messageLite, protoContainer.getNameResolver(), protoContainer.getTypeTable(), annotatedCallableKind);
        if (callableSignature == null) {
            return CollectionsKt.emptyList();
        }
        return findClassAndLoadMemberAnnotations$default(this, protoContainer, MemberSignature.Companion.fromMethodSignatureAndParameterIndex(callableSignature, 0), false, false, null, false, 60, null);
    }

    @NotNull
    public List<A> loadTypeAnnotations(@NotNull C3063Type type, @NotNull NameResolver nameResolver) {
        Intrinsics.checkParameterIsNotNull(type, "proto");
        Intrinsics.checkParameterIsNotNull(nameResolver, "nameResolver");
        Object extension = type.getExtension(JvmProtoBuf.typeAnnotation);
        Intrinsics.checkExpressionValueIsNotNull(extension, "proto.getExtension(JvmProtoBuf.typeAnnotation)");
        Iterable<Annotation> iterable = (Iterable) extension;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (Annotation annotation : iterable) {
            Intrinsics.checkExpressionValueIsNotNull(annotation, "it");
            arrayList.add(loadTypeAnnotation(annotation, nameResolver));
        }
        return (List) arrayList;
    }

    @NotNull
    public List<A> loadTypeParameterAnnotations(@NotNull TypeParameter typeParameter, @NotNull NameResolver nameResolver) {
        Intrinsics.checkParameterIsNotNull(typeParameter, "proto");
        Intrinsics.checkParameterIsNotNull(nameResolver, "nameResolver");
        Object extension = typeParameter.getExtension(JvmProtoBuf.typeParameterAnnotation);
        Intrinsics.checkExpressionValueIsNotNull(extension, "proto.getExtension(JvmPr….typeParameterAnnotation)");
        Iterable<Annotation> iterable = (Iterable) extension;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (Annotation annotation : iterable) {
            Intrinsics.checkExpressionValueIsNotNull(annotation, "it");
            arrayList.add(loadTypeAnnotation(annotation, nameResolver));
        }
        return (List) arrayList;
    }

    @Nullable
    public C loadPropertyConstant(@NotNull ProtoContainer protoContainer, @NotNull Property property, @NotNull KotlinType kotlinType) {
        Intrinsics.checkParameterIsNotNull(protoContainer, "container");
        Intrinsics.checkParameterIsNotNull(property, "proto");
        Intrinsics.checkParameterIsNotNull(kotlinType, "expectedType");
        MemberSignature callableSignature = getCallableSignature(property, protoContainer.getNameResolver(), protoContainer.getTypeTable(), AnnotatedCallableKind.PROPERTY);
        if (callableSignature != null) {
            KotlinJvmBinaryClass findClassWithAnnotationsAndInitializers = findClassWithAnnotationsAndInitializers(protoContainer, getSpecialCaseContainerClass(protoContainer, true, true, Flags.IS_CONST.get(property.getFlags()), JvmProtoBufUtil.isMovedFromInterfaceCompanion(property)));
            if (findClassWithAnnotationsAndInitializers != null) {
                C c = ((Storage) this.storage.invoke(findClassWithAnnotationsAndInitializers)).getPropertyConstants().get(callableSignature);
                if (c != null) {
                    if (UnsignedTypes.INSTANCE.isUnsignedType(kotlinType)) {
                        c = transformToUnsignedConstant(c);
                    }
                    return c;
                }
            }
        }
        return null;
    }

    private final KotlinJvmBinaryClass findClassWithAnnotationsAndInitializers(ProtoContainer protoContainer, KotlinJvmBinaryClass kotlinJvmBinaryClass) {
        if (kotlinJvmBinaryClass != null) {
            return kotlinJvmBinaryClass;
        }
        if (protoContainer instanceof Class) {
            return toBinaryClass((Class) protoContainer);
        }
        return null;
    }

    private final KotlinJvmBinaryClass getSpecialCaseContainerClass(ProtoContainer protoContainer, boolean z, boolean z2, Boolean bool, boolean z3) {
        if (z) {
            if (bool != null) {
                if (protoContainer instanceof Class) {
                    Class classR = (Class) protoContainer;
                    if (classR.getKind() == Kind.INTERFACE) {
                        KotlinClassFinder kotlinClassFinder2 = this.kotlinClassFinder;
                        ClassId createNestedClassId = classR.getClassId().createNestedClassId(Name.identifier("DefaultImpls"));
                        Intrinsics.checkExpressionValueIsNotNull(createNestedClassId, "container.classId.create…EFAULT_IMPLS_CLASS_NAME))");
                        return kotlinClassFinder2.findKotlinClass(createNestedClassId);
                    }
                }
                if (bool.booleanValue() && (protoContainer instanceof Package)) {
                    SourceElement source = protoContainer.getSource();
                    if (!(source instanceof JvmPackagePartSource)) {
                        source = null;
                    }
                    JvmPackagePartSource jvmPackagePartSource = (JvmPackagePartSource) source;
                    JvmClassName facadeClassName = jvmPackagePartSource != null ? jvmPackagePartSource.getFacadeClassName() : null;
                    if (facadeClassName != null) {
                        KotlinClassFinder kotlinClassFinder3 = this.kotlinClassFinder;
                        String internalName = facadeClassName.getInternalName();
                        Intrinsics.checkExpressionValueIsNotNull(internalName, "facadeClassName.internalName");
                        ClassId classId = ClassId.topLevel(new FqName(StringsKt.replace$default(internalName, '/', '.', false, 4, (Object) null)));
                        Intrinsics.checkExpressionValueIsNotNull(classId, "ClassId.topLevel(FqName(…lName.replace('/', '.')))");
                        return kotlinClassFinder3.findKotlinClass(classId);
                    }
                }
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("isConst should not be null for property (container=");
                sb.append(protoContainer);
                sb.append(')');
                throw new IllegalStateException(sb.toString().toString());
            }
        }
        if (z2 && (protoContainer instanceof Class)) {
            Class classR2 = (Class) protoContainer;
            if (classR2.getKind() == Kind.COMPANION_OBJECT) {
                Class outerClass = classR2.getOuterClass();
                if (outerClass != null && (outerClass.getKind() == Kind.CLASS || outerClass.getKind() == Kind.ENUM_CLASS || (z3 && (outerClass.getKind() == Kind.INTERFACE || outerClass.getKind() == Kind.ANNOTATION_CLASS)))) {
                    return toBinaryClass(outerClass);
                }
            }
        }
        if (!(protoContainer instanceof Package) || !(protoContainer.getSource() instanceof JvmPackagePartSource)) {
            return null;
        }
        SourceElement source2 = protoContainer.getSource();
        if (source2 != null) {
            JvmPackagePartSource jvmPackagePartSource2 = (JvmPackagePartSource) source2;
            KotlinJvmBinaryClass knownJvmBinaryClass = jvmPackagePartSource2.getKnownJvmBinaryClass();
            if (knownJvmBinaryClass == null) {
                knownJvmBinaryClass = this.kotlinClassFinder.findKotlinClass(jvmPackagePartSource2.getClassId());
            }
            return knownJvmBinaryClass;
        }
        throw new TypeCastException("null cannot be cast to non-null type org.jetbrains.kotlin.load.kotlin.JvmPackagePartSource");
    }

    /* access modifiers changed from: private */
    public final Storage<A, C> loadAnnotationsAndInitializers(KotlinJvmBinaryClass kotlinJvmBinaryClass) {
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        kotlinJvmBinaryClass.visitMembers(new C3028x2cf49cf1(this, hashMap, hashMap2), getCachedFileContent(kotlinJvmBinaryClass));
        return new Storage<>(hashMap, hashMap2);
    }

    static /* bridge */ /* synthetic */ MemberSignature getPropertySignature$default(AbstractBinaryClassAnnotationAndConstantLoader abstractBinaryClassAnnotationAndConstantLoader, Property property, NameResolver nameResolver, TypeTable typeTable, boolean z, boolean z2, int i, Object obj) {
        if (obj == null) {
            return abstractBinaryClassAnnotationAndConstantLoader.getPropertySignature(property, nameResolver, typeTable, (i & 8) != 0 ? false : z, (i & 16) != 0 ? false : z2);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getPropertySignature");
    }

    private final MemberSignature getPropertySignature(Property property, NameResolver nameResolver, TypeTable typeTable, boolean z, boolean z2) {
        ExtendableMessage extendableMessage = property;
        GeneratedExtension<Property, JvmPropertySignature> generatedExtension = JvmProtoBuf.propertySignature;
        Intrinsics.checkExpressionValueIsNotNull(generatedExtension, "propertySignature");
        JvmPropertySignature jvmPropertySignature = (JvmPropertySignature) ProtoBufUtilKt.getExtensionOrNull(extendableMessage, generatedExtension);
        if (jvmPropertySignature != null) {
            if (z) {
                Field jvmFieldSignature = JvmProtoBufUtil.INSTANCE.getJvmFieldSignature(property, nameResolver, typeTable);
                if (jvmFieldSignature != null) {
                    return MemberSignature.Companion.fromJvmMemberSignature(jvmFieldSignature);
                }
                return null;
            } else if (z2 && jvmPropertySignature.hasSyntheticMethod()) {
                kotlin.reflect.jvm.internal.impl.load.kotlin.MemberSignature.Companion companion = MemberSignature.Companion;
                JvmMethodSignature syntheticMethod = jvmPropertySignature.getSyntheticMethod();
                Intrinsics.checkExpressionValueIsNotNull(syntheticMethod, "signature.syntheticMethod");
                return companion.fromMethod(nameResolver, syntheticMethod);
            }
        }
        return null;
    }

    private final MemberSignature getCallableSignature(MessageLite messageLite, NameResolver nameResolver, TypeTable typeTable, AnnotatedCallableKind annotatedCallableKind) {
        MemberSignature memberSignature = null;
        if (messageLite instanceof Constructor) {
            kotlin.reflect.jvm.internal.impl.load.kotlin.MemberSignature.Companion companion = MemberSignature.Companion;
            Method jvmConstructorSignature = JvmProtoBufUtil.INSTANCE.getJvmConstructorSignature((Constructor) messageLite, nameResolver, typeTable);
            if (jvmConstructorSignature == null) {
                return null;
            }
            memberSignature = companion.fromJvmMemberSignature(jvmConstructorSignature);
        } else if (messageLite instanceof Function) {
            kotlin.reflect.jvm.internal.impl.load.kotlin.MemberSignature.Companion companion2 = MemberSignature.Companion;
            Method jvmMethodSignature = JvmProtoBufUtil.INSTANCE.getJvmMethodSignature((Function) messageLite, nameResolver, typeTable);
            if (jvmMethodSignature == null) {
                return null;
            }
            memberSignature = companion2.fromJvmMemberSignature(jvmMethodSignature);
        } else if (messageLite instanceof Property) {
            ExtendableMessage extendableMessage = (ExtendableMessage) messageLite;
            GeneratedExtension<Property, JvmPropertySignature> generatedExtension = JvmProtoBuf.propertySignature;
            Intrinsics.checkExpressionValueIsNotNull(generatedExtension, "propertySignature");
            JvmPropertySignature jvmPropertySignature = (JvmPropertySignature) ProtoBufUtilKt.getExtensionOrNull(extendableMessage, generatedExtension);
            if (jvmPropertySignature != null) {
                int i = WhenMappings.$EnumSwitchMapping$0[annotatedCallableKind.ordinal()];
                if (i != 1) {
                    if (i != 2) {
                        if (i == 3) {
                            memberSignature = getPropertySignature((Property) messageLite, nameResolver, typeTable, true, true);
                        }
                    } else if (jvmPropertySignature.hasSetter()) {
                        kotlin.reflect.jvm.internal.impl.load.kotlin.MemberSignature.Companion companion3 = MemberSignature.Companion;
                        JvmMethodSignature setter = jvmPropertySignature.getSetter();
                        Intrinsics.checkExpressionValueIsNotNull(setter, "signature.setter");
                        memberSignature = companion3.fromMethod(nameResolver, setter);
                    }
                } else if (jvmPropertySignature.hasGetter()) {
                    kotlin.reflect.jvm.internal.impl.load.kotlin.MemberSignature.Companion companion4 = MemberSignature.Companion;
                    JvmMethodSignature getter = jvmPropertySignature.getGetter();
                    Intrinsics.checkExpressionValueIsNotNull(getter, "signature.getter");
                    memberSignature = companion4.fromMethod(nameResolver, getter);
                }
            }
        }
        return memberSignature;
    }

    static {
        Iterable<FqName> listOf = CollectionsKt.listOf(JvmAnnotationNames.METADATA_FQ_NAME, JvmAnnotationNames.JETBRAINS_NOT_NULL_ANNOTATION, JvmAnnotationNames.JETBRAINS_NULLABLE_ANNOTATION, new FqName("java.lang.annotation.Target"), new FqName("java.lang.annotation.Retention"), new FqName("java.lang.annotation.Documented"));
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listOf, 10));
        for (FqName fqName : listOf) {
            arrayList.add(ClassId.topLevel(fqName));
        }
        SPECIAL_ANNOTATIONS = CollectionsKt.toSet((List) arrayList);
    }
}
