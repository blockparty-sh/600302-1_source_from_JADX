package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.UserDataKey;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeAliasDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibility;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationsImpl;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Annotation;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.C3063Type;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Function;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.MemberKind;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeAlias;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.VersionRequirement.VersionKind;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.ProtoTypeTableUtilKt;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.VersionRequirement;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.VersionRequirement.Version;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.protobuf.MessageLite;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoContainer.Package;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedAnnotationsWithPossibleTargets;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedClassDescriptor;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedSimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedTypeAliasDescriptor;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.NonEmptyDeserializedAnnotationsWithPossibleTargets;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import org.jetbrains.annotations.NotNull;

/* compiled from: MemberDeserializer.kt */
public final class MemberDeserializer {
    private final AnnotationDeserializer annotationDeserializer = new AnnotationDeserializer(this.f719c.getComponents().getModuleDescriptor(), this.f719c.getComponents().getNotFoundClasses());
    /* access modifiers changed from: private */

    /* renamed from: c */
    public final DeserializationContext f719c;

    private final int loadOldFlags(int i) {
        return (i & 63) + ((i >> 8) << 6);
    }

    public MemberDeserializer(@NotNull DeserializationContext deserializationContext) {
        Intrinsics.checkParameterIsNotNull(deserializationContext, "c");
        this.f719c = deserializationContext;
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x01a5  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x01c1  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x01df  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0214  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x025c  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0278  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x0296  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x0302  */
    @org.jetbrains.annotations.NotNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor loadProperty(@org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Property r36) {
        /*
            r35 = this;
            r6 = r35
            r5 = r36
            java.lang.String r0 = "proto"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r5, r0)
            boolean r0 = r36.hasFlags()
            if (r0 == 0) goto L_0x0014
            int r0 = r36.getFlags()
            goto L_0x001c
        L_0x0014:
            int r0 = r36.getOldFlags()
            int r0 = r6.loadOldFlags(r0)
        L_0x001c:
            r4 = r0
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedPropertyDescriptor r3 = new kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedPropertyDescriptor
            r7 = r3
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext r0 = r6.f719c
            kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor r8 = r0.getContainingDeclaration()
            r9 = 0
            r2 = r5
            kotlin.reflect.jvm.internal.impl.protobuf.MessageLite r2 = (kotlin.reflect.jvm.internal.impl.protobuf.MessageLite) r2
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotatedCallableKind r0 = kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotatedCallableKind.PROPERTY
            kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations r10 = r6.getAnnotations(r2, r4, r0)
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoEnumFlags r0 = kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoEnumFlags.INSTANCE
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags$FlagField<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Modality> r1 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.MODALITY
            java.lang.Object r1 = r1.get(r4)
            kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Modality r1 = (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Modality) r1
            kotlin.reflect.jvm.internal.impl.descriptors.Modality r11 = r0.modality(r1)
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoEnumFlags r0 = kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoEnumFlags.INSTANCE
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags$FlagField<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Visibility> r1 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.VISIBILITY
            java.lang.Object r1 = r1.get(r4)
            kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Visibility r1 = (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Visibility) r1
            kotlin.reflect.jvm.internal.impl.descriptors.Visibility r0 = r0.visibility(r1)
            r12 = r0
            java.lang.String r1 = "ProtoEnumFlags.visibilit…gs.VISIBILITY.get(flags))"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags$BooleanFlagField r0 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.IS_VAR
            java.lang.Boolean r0 = r0.get(r4)
            java.lang.String r1 = "Flags.IS_VAR.get(flags)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
            boolean r13 = r0.booleanValue()
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext r0 = r6.f719c
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver r0 = r0.getNameResolver()
            int r1 = r36.getName()
            kotlin.reflect.jvm.internal.impl.name.Name r14 = kotlin.reflect.jvm.internal.impl.serialization.deserialization.NameResolverUtilKt.getName(r0, r1)
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoEnumFlags r0 = kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoEnumFlags.INSTANCE
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags$FlagField<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$MemberKind> r1 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.MEMBER_KIND
            java.lang.Object r1 = r1.get(r4)
            kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$MemberKind r1 = (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.MemberKind) r1
            kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor$Kind r15 = r0.memberKind(r1)
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags$BooleanFlagField r0 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.IS_LATEINIT
            java.lang.Boolean r0 = r0.get(r4)
            java.lang.String r1 = "Flags.IS_LATEINIT.get(flags)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
            boolean r16 = r0.booleanValue()
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags$BooleanFlagField r0 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.IS_CONST
            java.lang.Boolean r0 = r0.get(r4)
            java.lang.String r1 = "Flags.IS_CONST.get(flags)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
            boolean r17 = r0.booleanValue()
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags$BooleanFlagField r0 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.IS_EXTERNAL_PROPERTY
            java.lang.Boolean r0 = r0.get(r4)
            java.lang.String r1 = "Flags.IS_EXTERNAL_PROPERTY.get(flags)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
            boolean r18 = r0.booleanValue()
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags$BooleanFlagField r0 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.IS_DELEGATED
            java.lang.Boolean r0 = r0.get(r4)
            java.lang.String r1 = "Flags.IS_DELEGATED.get(flags)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
            boolean r19 = r0.booleanValue()
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags$BooleanFlagField r0 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.IS_EXPECT_PROPERTY
            java.lang.Boolean r0 = r0.get(r4)
            java.lang.String r1 = "Flags.IS_EXPECT_PROPERTY.get(flags)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
            boolean r20 = r0.booleanValue()
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext r0 = r6.f719c
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver r22 = r0.getNameResolver()
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext r0 = r6.f719c
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable r23 = r0.getTypeTable()
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext r0 = r6.f719c
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.VersionRequirementTable r24 = r0.getVersionRequirementTable()
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext r0 = r6.f719c
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedContainerSource r25 = r0.getContainerSource()
            r21 = r36
            r7.<init>(r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25)
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext r0 = r6.f719c
            r27 = r3
            kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor r27 = (kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor) r27
            java.util.List r1 = r36.getTypeParameterList()
            java.lang.String r7 = "proto.typeParameterList"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r7)
            r29 = 0
            r30 = 0
            r31 = 0
            r32 = 0
            r33 = 60
            r34 = 0
            r26 = r0
            r28 = r1
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext r16 = kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext.childContext$default(r26, r27, r28, r29, r30, r31, r32, r33, r34)
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags$BooleanFlagField r0 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.HAS_GETTER
            java.lang.Boolean r7 = r0.get(r4)
            java.lang.String r0 = "hasGetter"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r7, r0)
            boolean r0 = r7.booleanValue()
            if (r0 == 0) goto L_0x0134
            boolean r0 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.ProtoTypeTableUtilKt.hasReceiver(r36)
            if (r0 == 0) goto L_0x0134
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotatedCallableKind r8 = kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotatedCallableKind.PROPERTY_GETTER
            r9 = 0
            r10 = 4
            r11 = 0
            r0 = r35
            r1 = r2
            r15 = r2
            r2 = r8
            r14 = r3
            r3 = r9
            r13 = r4
            r4 = r10
            r12 = r5
            r5 = r11
            kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations r0 = getReceiverParameterAnnotations$default(r0, r1, r2, r3, r4, r5)
            goto L_0x013e
        L_0x0134:
            r15 = r2
            r14 = r3
            r13 = r4
            r12 = r5
            kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations$Companion r0 = kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations.Companion
            kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations r0 = r0.getEMPTY()
        L_0x013e:
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.TypeDeserializer r1 = r16.getTypeDeserializer()
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext r2 = r6.f719c
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable r2 = r2.getTypeTable()
            kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Type r2 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.ProtoTypeTableUtilKt.returnType(r12, r2)
            r3 = 2
            r4 = 0
            kotlin.reflect.jvm.internal.impl.types.KotlinType r1 = kotlin.reflect.jvm.internal.impl.serialization.deserialization.TypeDeserializer.type$default(r1, r2, r4, r3, r4)
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.TypeDeserializer r2 = r16.getTypeDeserializer()
            java.util.List r2 = r2.getOwnTypeParameters()
            kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor r3 = r35.getDispatchReceiverParameter()
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext r5 = r6.f719c
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable r5 = r5.getTypeTable()
            kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Type r5 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.ProtoTypeTableUtilKt.receiverType(r12, r5)
            if (r5 == 0) goto L_0x0173
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.TypeDeserializer r8 = r16.getTypeDeserializer()
            kotlin.reflect.jvm.internal.impl.types.KotlinType r0 = r8.type(r5, r0)
            goto L_0x0174
        L_0x0173:
            r0 = r4
        L_0x0174:
            r14.setType(r1, r2, r3, r0)
            boolean r0 = r7.booleanValue()
            java.lang.String r1 = "DescriptorFactory.create…er(property, annotations)"
            r2 = 0
            r3 = 1
            if (r0 == 0) goto L_0x0226
            int r0 = r36.getGetterFlags()
            boolean r5 = r36.hasGetterFlags()
            if (r5 == 0) goto L_0x019e
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags$BooleanFlagField r5 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.IS_NOT_DEFAULT
            java.lang.Boolean r5 = r5.get(r0)
            java.lang.String r7 = "Flags.IS_NOT_DEFAULT.get(getterFlags)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r5, r7)
            boolean r5 = r5.booleanValue()
            if (r5 == 0) goto L_0x019e
            r5 = 1
            goto L_0x019f
        L_0x019e:
            r5 = 0
        L_0x019f:
            boolean r7 = r36.hasGetterFlags()
            if (r7 == 0) goto L_0x01b9
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags$BooleanFlagField r7 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.IS_EXTERNAL_ACCESSOR
            java.lang.Boolean r7 = r7.get(r0)
            java.lang.String r8 = "Flags.IS_EXTERNAL_ACCESSOR.get(getterFlags)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r7, r8)
            boolean r7 = r7.booleanValue()
            if (r7 == 0) goto L_0x01b9
            r23 = 1
            goto L_0x01bb
        L_0x01b9:
            r23 = 0
        L_0x01bb:
            boolean r7 = r36.hasGetterFlags()
            if (r7 == 0) goto L_0x01d5
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags$BooleanFlagField r7 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.IS_INLINE_ACCESSOR
            java.lang.Boolean r7 = r7.get(r0)
            java.lang.String r8 = "Flags.IS_INLINE_ACCESSOR.get(getterFlags)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r7, r8)
            boolean r7 = r7.booleanValue()
            if (r7 == 0) goto L_0x01d5
            r24 = 1
            goto L_0x01d7
        L_0x01d5:
            r24 = 0
        L_0x01d7:
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotatedCallableKind r7 = kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotatedCallableKind.PROPERTY_GETTER
            kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations r7 = r6.getAnnotations(r15, r0, r7)
            if (r5 == 0) goto L_0x0214
            kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertyGetterDescriptorImpl r8 = new kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertyGetterDescriptorImpl
            r18 = r14
            kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor r18 = (kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor) r18
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoEnumFlags r9 = kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoEnumFlags.INSTANCE
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags$FlagField<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Modality> r10 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.MODALITY
            java.lang.Object r10 = r10.get(r0)
            kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Modality r10 = (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Modality) r10
            kotlin.reflect.jvm.internal.impl.descriptors.Modality r20 = r9.modality(r10)
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoEnumFlags r9 = kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoEnumFlags.INSTANCE
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags$FlagField<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Visibility> r10 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.VISIBILITY
            java.lang.Object r0 = r10.get(r0)
            kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Visibility r0 = (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Visibility) r0
            kotlin.reflect.jvm.internal.impl.descriptors.Visibility r21 = r9.visibility(r0)
            r22 = r5 ^ 1
            kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor$Kind r25 = r14.getKind()
            r26 = 0
            kotlin.reflect.jvm.internal.impl.descriptors.SourceElement r27 = kotlin.reflect.jvm.internal.impl.descriptors.SourceElement.NO_SOURCE
            r17 = r8
            r19 = r7
            r17.<init>(r18, r19, r20, r21, r22, r23, r24, r25, r26, r27)
            r0 = r8
            goto L_0x021e
        L_0x0214:
            r0 = r14
            kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor r0 = (kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor) r0
            kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertyGetterDescriptorImpl r0 = kotlin.reflect.jvm.internal.impl.resolve.DescriptorFactory.createDefaultGetter(r0, r7)
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
        L_0x021e:
            kotlin.reflect.jvm.internal.impl.types.KotlinType r5 = r14.getReturnType()
            r0.initialize(r5)
            goto L_0x0227
        L_0x0226:
            r0 = r4
        L_0x0227:
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags$BooleanFlagField r5 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.HAS_SETTER
            java.lang.Boolean r5 = r5.get(r13)
            java.lang.String r7 = "Flags.HAS_SETTER.get(flags)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r5, r7)
            boolean r5 = r5.booleanValue()
            if (r5 == 0) goto L_0x030f
            int r4 = r36.getSetterFlags()
            boolean r5 = r36.hasSetterFlags()
            if (r5 == 0) goto L_0x0255
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags$BooleanFlagField r5 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.IS_NOT_DEFAULT
            java.lang.Boolean r5 = r5.get(r4)
            java.lang.String r7 = "Flags.IS_NOT_DEFAULT.get(setterFlags)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r5, r7)
            boolean r5 = r5.booleanValue()
            if (r5 == 0) goto L_0x0255
            r5 = 1
            goto L_0x0256
        L_0x0255:
            r5 = 0
        L_0x0256:
            boolean r7 = r36.hasSetterFlags()
            if (r7 == 0) goto L_0x0270
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags$BooleanFlagField r7 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.IS_EXTERNAL_ACCESSOR
            java.lang.Boolean r7 = r7.get(r4)
            java.lang.String r8 = "Flags.IS_EXTERNAL_ACCESSOR.get(setterFlags)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r7, r8)
            boolean r7 = r7.booleanValue()
            if (r7 == 0) goto L_0x0270
            r23 = 1
            goto L_0x0272
        L_0x0270:
            r23 = 0
        L_0x0272:
            boolean r7 = r36.hasGetterFlags()
            if (r7 == 0) goto L_0x028c
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags$BooleanFlagField r7 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.IS_INLINE_ACCESSOR
            java.lang.Boolean r7 = r7.get(r4)
            java.lang.String r8 = "Flags.IS_INLINE_ACCESSOR.get(setterFlags)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r7, r8)
            boolean r7 = r7.booleanValue()
            if (r7 == 0) goto L_0x028c
            r24 = 1
            goto L_0x028e
        L_0x028c:
            r24 = 0
        L_0x028e:
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotatedCallableKind r2 = kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotatedCallableKind.PROPERTY_SETTER
            kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations r2 = r6.getAnnotations(r15, r4, r2)
            if (r5 == 0) goto L_0x0302
            kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertySetterDescriptorImpl r1 = new kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertySetterDescriptorImpl
            r18 = r14
            kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor r18 = (kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor) r18
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoEnumFlags r7 = kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoEnumFlags.INSTANCE
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags$FlagField<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Modality> r8 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.MODALITY
            java.lang.Object r8 = r8.get(r4)
            kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Modality r8 = (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Modality) r8
            kotlin.reflect.jvm.internal.impl.descriptors.Modality r20 = r7.modality(r8)
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoEnumFlags r7 = kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoEnumFlags.INSTANCE
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags$FlagField<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Visibility> r8 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.VISIBILITY
            java.lang.Object r4 = r8.get(r4)
            kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Visibility r4 = (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Visibility) r4
            kotlin.reflect.jvm.internal.impl.descriptors.Visibility r21 = r7.visibility(r4)
            r22 = r5 ^ 1
            kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor$Kind r25 = r14.getKind()
            r26 = 0
            kotlin.reflect.jvm.internal.impl.descriptors.SourceElement r27 = kotlin.reflect.jvm.internal.impl.descriptors.SourceElement.NO_SOURCE
            r17 = r1
            r19 = r2
            r17.<init>(r18, r19, r20, r21, r22, r23, r24, r25, r26, r27)
            r8 = r1
            kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor r8 = (kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor) r8
            java.util.List r9 = kotlin.collections.CollectionsKt.emptyList()
            r10 = 0
            r11 = 0
            r2 = 0
            r3 = 0
            r4 = 60
            r5 = 0
            r7 = r16
            r12 = r2
            r2 = r13
            r13 = r3
            r3 = r14
            r14 = r4
            r4 = r15
            r15 = r5
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext r5 = kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext.childContext$default(r7, r8, r9, r10, r11, r12, r13, r14, r15)
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.MemberDeserializer r5 = r5.getMemberDeserializer()
            kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$ValueParameter r7 = r36.getSetterValueParameter()
            java.util.List r7 = kotlin.collections.CollectionsKt.listOf(r7)
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotatedCallableKind r8 = kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotatedCallableKind.PROPERTY_SETTER
            java.util.List r4 = r5.valueParameters(r7, r4, r8)
            java.lang.Object r4 = kotlin.collections.CollectionsKt.single(r4)
            kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor r4 = (kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor) r4
            r1.initialize(r4)
            r4 = r1
            r5 = r2
            goto L_0x0311
        L_0x0302:
            r5 = r13
            r3 = r14
            r4 = r3
            kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor r4 = (kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor) r4
            kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertySetterDescriptorImpl r4 = kotlin.reflect.jvm.internal.impl.resolve.DescriptorFactory.createDefaultSetter(r4, r2)
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r4, r1)
            goto L_0x0311
        L_0x030f:
            r5 = r13
            r3 = r14
        L_0x0311:
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags$BooleanFlagField r1 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.HAS_CONSTANT
            java.lang.Boolean r1 = r1.get(r5)
            java.lang.String r2 = "Flags.HAS_CONSTANT.get(flags)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r2)
            boolean r1 = r1.booleanValue()
            if (r1 == 0) goto L_0x0338
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext r1 = r6.f719c
            kotlin.reflect.jvm.internal.impl.storage.StorageManager r1 = r1.getStorageManager()
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.MemberDeserializer$loadProperty$2 r2 = new kotlin.reflect.jvm.internal.impl.serialization.deserialization.MemberDeserializer$loadProperty$2
            r5 = r36
            r2.<init>(r6, r5, r3)
            kotlin.jvm.functions.Function0 r2 = (kotlin.jvm.functions.Function0) r2
            kotlin.reflect.jvm.internal.impl.storage.NullableLazyValue r1 = r1.createNullableLazyValue(r2)
            r3.setCompileTimeInitializer(r1)
        L_0x0338:
            kotlin.reflect.jvm.internal.impl.descriptors.PropertySetterDescriptor r4 = (kotlin.reflect.jvm.internal.impl.descriptors.PropertySetterDescriptor) r4
            r1 = r3
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberDescriptor r1 = (kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberDescriptor) r1
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.TypeDeserializer r2 = r16.getTypeDeserializer()
            boolean r1 = r6.checkExperimentalCoroutine(r1, r2)
            r3.initialize(r0, r4, r1)
            kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor r3 = (kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor) r3
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.serialization.deserialization.MemberDeserializer.loadProperty(kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Property):kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor");
    }

    private final boolean checkExperimentalCoroutine(@NotNull DeserializedMemberDescriptor deserializedMemberDescriptor, TypeDeserializer typeDeserializer) {
        for (TypeParameterDescriptor upperBounds : typeDeserializer.getOwnTypeParameters()) {
            upperBounds.getUpperBounds();
        }
        return typeDeserializer.getExperimentalSuspendFunctionTypeEncountered() && versionAndReleaseCoroutinesMismatch(deserializedMemberDescriptor);
    }

    private final boolean versionAndReleaseCoroutinesMismatch(@NotNull DeserializedMemberDescriptor deserializedMemberDescriptor) {
        boolean z;
        boolean z2;
        if (!this.f719c.getComponents().getConfiguration().getReleaseCoroutines()) {
            return false;
        }
        Iterable versionRequirements = deserializedMemberDescriptor.getVersionRequirements();
        if (!(versionRequirements instanceof Collection) || !((Collection) versionRequirements).isEmpty()) {
            Iterator it = versionRequirements.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                VersionRequirement versionRequirement = (VersionRequirement) it.next();
                Version version = versionRequirement.getVersion();
                Version version2 = new Version(1, 3, 0, 4, null);
                if (!Intrinsics.areEqual((Object) version, (Object) version2) || versionRequirement.getKind() != VersionKind.LANGUAGE_VERSION) {
                    z2 = false;
                    continue;
                } else {
                    z2 = true;
                    continue;
                }
                if (z2) {
                    z = false;
                    break;
                }
            }
        }
        z = true;
        if (z) {
            return true;
        }
        return false;
    }

    @NotNull
    public final SimpleFunctionDescriptor loadFunction(@NotNull Function function) {
        Annotations annotations;
        Function function2 = function;
        Intrinsics.checkParameterIsNotNull(function2, "proto");
        int flags = function.hasFlags() ? function.getFlags() : loadOldFlags(function.getOldFlags());
        MessageLite messageLite = function2;
        Annotations annotations2 = getAnnotations(messageLite, flags, AnnotatedCallableKind.FUNCTION);
        if (ProtoTypeTableUtilKt.hasReceiver(function)) {
            annotations = getReceiverParameterAnnotations$default(this, messageLite, AnnotatedCallableKind.FUNCTION, null, 4, null);
        } else {
            annotations = Annotations.Companion.getEMPTY();
        }
        MessageLite messageLite2 = messageLite;
        int i = flags;
        Function function3 = function2;
        DeserializedSimpleFunctionDescriptor deserializedSimpleFunctionDescriptor = new DeserializedSimpleFunctionDescriptor(this.f719c.getContainingDeclaration(), null, annotations2, NameResolverUtilKt.getName(this.f719c.getNameResolver(), function.getName()), ProtoEnumFlags.INSTANCE.memberKind((MemberKind) Flags.MEMBER_KIND.get(flags)), function, this.f719c.getNameResolver(), this.f719c.getTypeTable(), this.f719c.getVersionRequirementTable(), this.f719c.getContainerSource(), null, 1024, null);
        DeserializationContext deserializationContext = this.f719c;
        DeclarationDescriptor declarationDescriptor = deserializedSimpleFunctionDescriptor;
        List typeParameterList = function.getTypeParameterList();
        Intrinsics.checkExpressionValueIsNotNull(typeParameterList, "proto.typeParameterList");
        DeserializationContext childContext$default = DeserializationContext.childContext$default(deserializationContext, declarationDescriptor, typeParameterList, null, null, null, null, 60, null);
        C3063Type receiverType = ProtoTypeTableUtilKt.receiverType(function3, this.f719c.getTypeTable());
        KotlinType type = receiverType != null ? childContext$default.getTypeDeserializer().type(receiverType, annotations) : null;
        ReceiverParameterDescriptor dispatchReceiverParameter = getDispatchReceiverParameter();
        List ownTypeParameters = childContext$default.getTypeDeserializer().getOwnTypeParameters();
        MemberDeserializer memberDeserializer = childContext$default.getMemberDeserializer();
        List valueParameterList = function.getValueParameterList();
        Intrinsics.checkExpressionValueIsNotNull(valueParameterList, "proto.valueParameterList");
        List valueParameters = memberDeserializer.valueParameters(valueParameterList, messageLite2, AnnotatedCallableKind.FUNCTION);
        KotlinType type$default = TypeDeserializer.type$default(childContext$default.getTypeDeserializer(), ProtoTypeTableUtilKt.returnType(function3, this.f719c.getTypeTable()), null, 2, null);
        Modality modality = ProtoEnumFlags.INSTANCE.modality((ProtoBuf.Modality) Flags.MODALITY.get(i));
        Visibility visibility = ProtoEnumFlags.INSTANCE.visibility((ProtoBuf.Visibility) Flags.VISIBILITY.get(i));
        Intrinsics.checkExpressionValueIsNotNull(visibility, "ProtoEnumFlags.visibilit…gs.VISIBILITY.get(flags))");
        Map emptyMap = MapsKt.emptyMap();
        Boolean bool = Flags.IS_SUSPEND.get(i);
        String str = "Flags.IS_SUSPEND.get(flags)";
        Intrinsics.checkExpressionValueIsNotNull(bool, str);
        deserializedSimpleFunctionDescriptor.initialize(type, dispatchReceiverParameter, ownTypeParameters, valueParameters, type$default, modality, visibility, emptyMap, (bool.booleanValue() && versionAndReleaseCoroutinesMismatch(deserializedSimpleFunctionDescriptor)) || checkExperimentalCoroutine(deserializedSimpleFunctionDescriptor, childContext$default.getTypeDeserializer()));
        Boolean bool2 = Flags.IS_OPERATOR.get(i);
        Intrinsics.checkExpressionValueIsNotNull(bool2, "Flags.IS_OPERATOR.get(flags)");
        deserializedSimpleFunctionDescriptor.setOperator(bool2.booleanValue());
        Boolean bool3 = Flags.IS_INFIX.get(i);
        Intrinsics.checkExpressionValueIsNotNull(bool3, "Flags.IS_INFIX.get(flags)");
        deserializedSimpleFunctionDescriptor.setInfix(bool3.booleanValue());
        Boolean bool4 = Flags.IS_EXTERNAL_FUNCTION.get(i);
        Intrinsics.checkExpressionValueIsNotNull(bool4, "Flags.IS_EXTERNAL_FUNCTION.get(flags)");
        deserializedSimpleFunctionDescriptor.setExternal(bool4.booleanValue());
        Boolean bool5 = Flags.IS_INLINE.get(i);
        Intrinsics.checkExpressionValueIsNotNull(bool5, "Flags.IS_INLINE.get(flags)");
        deserializedSimpleFunctionDescriptor.setInline(bool5.booleanValue());
        Boolean bool6 = Flags.IS_TAILREC.get(i);
        Intrinsics.checkExpressionValueIsNotNull(bool6, "Flags.IS_TAILREC.get(flags)");
        deserializedSimpleFunctionDescriptor.setTailrec(bool6.booleanValue());
        Boolean bool7 = Flags.IS_SUSPEND.get(i);
        Intrinsics.checkExpressionValueIsNotNull(bool7, str);
        deserializedSimpleFunctionDescriptor.setSuspend(bool7.booleanValue());
        Boolean bool8 = Flags.IS_EXPECT_FUNCTION.get(i);
        Intrinsics.checkExpressionValueIsNotNull(bool8, "Flags.IS_EXPECT_FUNCTION.get(flags)");
        deserializedSimpleFunctionDescriptor.setExpect(bool8.booleanValue());
        Pair deserializeContractFromFunction = this.f719c.getComponents().getContractDeserializer().deserializeContractFromFunction(function3, deserializedSimpleFunctionDescriptor, this.f719c.getTypeTable(), this.f719c.getTypeDeserializer());
        if (deserializeContractFromFunction != null) {
            deserializedSimpleFunctionDescriptor.putInUserDataMap((UserDataKey) deserializeContractFromFunction.getFirst(), deserializeContractFromFunction.getSecond());
        }
        return deserializedSimpleFunctionDescriptor;
    }

    @NotNull
    public final TypeAliasDescriptor loadTypeAlias(@NotNull TypeAlias typeAlias) {
        TypeAlias typeAlias2 = typeAlias;
        Intrinsics.checkParameterIsNotNull(typeAlias2, "proto");
        List annotationList = typeAlias.getAnnotationList();
        Intrinsics.checkExpressionValueIsNotNull(annotationList, "proto.annotationList");
        Iterable<Annotation> iterable = annotationList;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (Annotation annotation : iterable) {
            AnnotationDeserializer annotationDeserializer2 = this.annotationDeserializer;
            Intrinsics.checkExpressionValueIsNotNull(annotation, "it");
            arrayList.add(annotationDeserializer2.deserializeAnnotation(annotation, this.f719c.getNameResolver()));
        }
        AnnotationsImpl annotationsImpl = new AnnotationsImpl((List) arrayList);
        Visibility visibility = ProtoEnumFlags.INSTANCE.visibility((ProtoBuf.Visibility) Flags.VISIBILITY.get(typeAlias.getFlags()));
        StorageManager storageManager = this.f719c.getStorageManager();
        DeclarationDescriptor containingDeclaration = this.f719c.getContainingDeclaration();
        Annotations annotations = annotationsImpl;
        Name name = NameResolverUtilKt.getName(this.f719c.getNameResolver(), typeAlias.getName());
        Intrinsics.checkExpressionValueIsNotNull(visibility, "visibility");
        DeserializedTypeAliasDescriptor deserializedTypeAliasDescriptor = new DeserializedTypeAliasDescriptor(storageManager, containingDeclaration, annotations, name, visibility, typeAlias, this.f719c.getNameResolver(), this.f719c.getTypeTable(), this.f719c.getVersionRequirementTable(), this.f719c.getContainerSource());
        DeserializationContext deserializationContext = this.f719c;
        DeclarationDescriptor declarationDescriptor = deserializedTypeAliasDescriptor;
        List typeParameterList = typeAlias.getTypeParameterList();
        Intrinsics.checkExpressionValueIsNotNull(typeParameterList, "proto.typeParameterList");
        DeserializationContext childContext$default = DeserializationContext.childContext$default(deserializationContext, declarationDescriptor, typeParameterList, null, null, null, null, 60, null);
        deserializedTypeAliasDescriptor.initialize(childContext$default.getTypeDeserializer().getOwnTypeParameters(), TypeDeserializer.simpleType$default(childContext$default.getTypeDeserializer(), ProtoTypeTableUtilKt.underlyingType(typeAlias2, this.f719c.getTypeTable()), null, 2, null), TypeDeserializer.simpleType$default(childContext$default.getTypeDeserializer(), ProtoTypeTableUtilKt.expandedType(typeAlias2, this.f719c.getTypeTable()), null, 2, null), checkExperimentalCoroutine(deserializedTypeAliasDescriptor, childContext$default.getTypeDeserializer()));
        return deserializedTypeAliasDescriptor;
    }

    private final ReceiverParameterDescriptor getDispatchReceiverParameter() {
        DeclarationDescriptor containingDeclaration = this.f719c.getContainingDeclaration();
        if (!(containingDeclaration instanceof ClassDescriptor)) {
            containingDeclaration = null;
        }
        ClassDescriptor classDescriptor = (ClassDescriptor) containingDeclaration;
        if (classDescriptor != null) {
            return classDescriptor.getThisAsReceiverParameter();
        }
        return null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x00fb, code lost:
        if (versionAndReleaseCoroutinesMismatch(r15) != false) goto L_0x00ff;
     */
    @org.jetbrains.annotations.NotNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor loadConstructor(@org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Constructor r30, boolean r31) {
        /*
            r29 = this;
            r0 = r29
            r15 = r30
            java.lang.String r1 = "proto"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r15, r1)
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext r1 = r0.f719c
            kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor r1 = r1.getContainingDeclaration()
            if (r1 == 0) goto L_0x0106
            r16 = r1
            kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor r16 = (kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor) r16
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedClassConstructorDescriptor r14 = new kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedClassConstructorDescriptor
            r3 = 0
            r13 = r15
            kotlin.reflect.jvm.internal.impl.protobuf.MessageLite r13 = (kotlin.reflect.jvm.internal.impl.protobuf.MessageLite) r13
            int r1 = r30.getFlags()
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotatedCallableKind r2 = kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotatedCallableKind.FUNCTION
            kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations r4 = r0.getAnnotations(r13, r1, r2)
            kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor$Kind r6 = kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor.Kind.DECLARATION
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext r1 = r0.f719c
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver r8 = r1.getNameResolver()
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext r1 = r0.f719c
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable r9 = r1.getTypeTable()
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext r1 = r0.f719c
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.VersionRequirementTable r10 = r1.getVersionRequirementTable()
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext r1 = r0.f719c
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedContainerSource r11 = r1.getContainerSource()
            r12 = 0
            r17 = 1024(0x400, float:1.435E-42)
            r18 = 0
            r1 = r14
            r2 = r16
            r5 = r31
            r7 = r30
            r19 = r13
            r13 = r17
            r15 = r14
            r14 = r18
            r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14)
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext r1 = r0.f719c
            r21 = r15
            kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor r21 = (kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor) r21
            java.util.List r22 = kotlin.collections.CollectionsKt.emptyList()
            r23 = 0
            r24 = 0
            r25 = 0
            r26 = 0
            r27 = 60
            r28 = 0
            r20 = r1
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext r1 = kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext.childContext$default(r20, r21, r22, r23, r24, r25, r26, r27, r28)
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.MemberDeserializer r2 = r1.getMemberDeserializer()
            java.util.List r3 = r30.getValueParameterList()
            java.lang.String r4 = "proto.valueParameterList"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r3, r4)
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotatedCallableKind r4 = kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotatedCallableKind.FUNCTION
            r5 = r19
            java.util.List r2 = r2.valueParameters(r3, r5, r4)
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoEnumFlags r3 = kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoEnumFlags.INSTANCE
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags$FlagField<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Visibility> r4 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.VISIBILITY
            int r5 = r30.getFlags()
            java.lang.Object r4 = r4.get(r5)
            kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Visibility r4 = (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Visibility) r4
            kotlin.reflect.jvm.internal.impl.descriptors.Visibility r3 = r3.visibility(r4)
            r15.initialize(r2, r3)
            kotlin.reflect.jvm.internal.impl.types.SimpleType r2 = r16.getDefaultType()
            kotlin.reflect.jvm.internal.impl.types.KotlinType r2 = (kotlin.reflect.jvm.internal.impl.types.KotlinType) r2
            r15.setReturnType(r2)
            java.util.List r2 = r15.getTypeParameters()
            java.lang.String r3 = "descriptor.typeParameters"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r2, r3)
            java.lang.Iterable r2 = (java.lang.Iterable) r2
            java.util.Iterator r2 = r2.iterator()
        L_0x00b3:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x00c8
            java.lang.Object r3 = r2.next()
            kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor r3 = (kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor) r3
            java.lang.String r4 = "it"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r3, r4)
            r3.getUpperBounds()
            goto L_0x00b3
        L_0x00c8:
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.TypeDeserializer r1 = r1.getTypeDeserializer()
            boolean r1 = r1.getExperimentalSuspendFunctionTypeEncountered()
            r2 = 1
            if (r1 != 0) goto L_0x00ff
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext r1 = r0.f719c
            kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor r1 = r1.getContainingDeclaration()
            boolean r3 = r1 instanceof kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedClassDescriptor
            if (r3 != 0) goto L_0x00de
            r1 = 0
        L_0x00de:
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedClassDescriptor r1 = (kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedClassDescriptor) r1
            if (r1 == 0) goto L_0x00fe
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext r1 = r1.getC()
            if (r1 == 0) goto L_0x00fe
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.TypeDeserializer r1 = r1.getTypeDeserializer()
            if (r1 == 0) goto L_0x00fe
            boolean r1 = r1.getExperimentalSuspendFunctionTypeEncountered()
            if (r1 != r2) goto L_0x00fe
            r14 = r15
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberDescriptor r14 = (kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberDescriptor) r14
            boolean r1 = r0.versionAndReleaseCoroutinesMismatch(r14)
            if (r1 == 0) goto L_0x00fe
            goto L_0x00ff
        L_0x00fe:
            r2 = 0
        L_0x00ff:
            r15.setExperimentalCoroutineInReleaseEnvironment$deserialization(r2)
            r14 = r15
            kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor r14 = (kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor) r14
            return r14
        L_0x0106:
            kotlin.TypeCastException r1 = new kotlin.TypeCastException
            java.lang.String r2 = "null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor"
            r1.<init>(r2)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.serialization.deserialization.MemberDeserializer.loadConstructor(kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Constructor, boolean):kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor");
    }

    private final Annotations getAnnotations(MessageLite messageLite, int i, AnnotatedCallableKind annotatedCallableKind) {
        if (!Flags.HAS_ANNOTATIONS.get(i).booleanValue()) {
            return Annotations.Companion.getEMPTY();
        }
        return new NonEmptyDeserializedAnnotationsWithPossibleTargets(this.f719c.getStorageManager(), new MemberDeserializer$getAnnotations$1(this, messageLite, annotatedCallableKind));
    }

    static /* bridge */ /* synthetic */ Annotations getReceiverParameterAnnotations$default(MemberDeserializer memberDeserializer, MessageLite messageLite, AnnotatedCallableKind annotatedCallableKind, AnnotatedCallableKind annotatedCallableKind2, int i, Object obj) {
        if ((i & 4) != 0) {
            annotatedCallableKind2 = annotatedCallableKind;
        }
        return memberDeserializer.getReceiverParameterAnnotations(messageLite, annotatedCallableKind, annotatedCallableKind2);
    }

    private final Annotations getReceiverParameterAnnotations(MessageLite messageLite, AnnotatedCallableKind annotatedCallableKind, AnnotatedCallableKind annotatedCallableKind2) {
        return new DeserializedAnnotationsWithPossibleTargets(this.f719c.getStorageManager(), new MemberDeserializer$getReceiverParameterAnnotations$1(this, messageLite, annotatedCallableKind2));
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x00eb  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x00f8  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final java.util.List<kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor> valueParameters(java.util.List<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.ValueParameter> r27, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite r28, kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotatedCallableKind r29) {
        /*
            r26 = this;
            r8 = r26
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext r0 = r8.f719c
            kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor r0 = r0.getContainingDeclaration()
            if (r0 == 0) goto L_0x0120
            r21 = r0
            kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor r21 = (kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor) r21
            kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor r0 = r21.getContainingDeclaration()
            java.lang.String r1 = "callableDescriptor.containingDeclaration"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoContainer r22 = r8.asProtoContainer(r0)
            r0 = r27
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            java.util.ArrayList r1 = new java.util.ArrayList
            r2 = 10
            int r2 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r0, r2)
            r1.<init>(r2)
            r15 = r1
            java.util.Collection r15 = (java.util.Collection) r15
            java.util.Iterator r23 = r0.iterator()
            r24 = 0
            r12 = 0
        L_0x0034:
            boolean r0 = r23.hasNext()
            if (r0 == 0) goto L_0x0115
            java.lang.Object r0 = r23.next()
            int r25 = r12 + 1
            r9 = r0
            kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$ValueParameter r9 = (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.ValueParameter) r9
            boolean r0 = r9.hasFlags()
            if (r0 == 0) goto L_0x004f
            int r0 = r9.getFlags()
            r10 = r0
            goto L_0x0050
        L_0x004f:
            r10 = 0
        L_0x0050:
            if (r22 == 0) goto L_0x0086
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags$BooleanFlagField r0 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.HAS_ANNOTATIONS
            java.lang.Boolean r0 = r0.get(r10)
            java.lang.String r1 = "Flags.HAS_ANNOTATIONS.get(flags)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
            boolean r0 = r0.booleanValue()
            if (r0 == 0) goto L_0x0086
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.NonEmptyDeserializedAnnotations r11 = new kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.NonEmptyDeserializedAnnotations
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext r0 = r8.f719c
            kotlin.reflect.jvm.internal.impl.storage.StorageManager r13 = r0.getStorageManager()
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.MemberDeserializer$valueParameters$$inlined$mapIndexed$lambda$1 r14 = new kotlin.reflect.jvm.internal.impl.serialization.deserialization.MemberDeserializer$valueParameters$$inlined$mapIndexed$lambda$1
            r0 = r14
            r1 = r12
            r2 = r9
            r3 = r26
            r4 = r22
            r5 = r28
            r6 = r29
            r7 = r21
            r0.<init>(r1, r2, r3, r4, r5, r6, r7)
            kotlin.jvm.functions.Function0 r14 = (kotlin.jvm.functions.Function0) r14
            r11.<init>(r13, r14)
            kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations r11 = (kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations) r11
            r13 = r11
            goto L_0x008d
        L_0x0086:
            kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations$Companion r0 = kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations.Companion
            kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations r0 = r0.getEMPTY()
            r13 = r0
        L_0x008d:
            r11 = 0
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext r0 = r8.f719c
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver r0 = r0.getNameResolver()
            int r1 = r9.getName()
            kotlin.reflect.jvm.internal.impl.name.Name r14 = kotlin.reflect.jvm.internal.impl.serialization.deserialization.NameResolverUtilKt.getName(r0, r1)
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext r0 = r8.f719c
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.TypeDeserializer r0 = r0.getTypeDeserializer()
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext r1 = r8.f719c
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable r1 = r1.getTypeTable()
            kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Type r1 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.ProtoTypeTableUtilKt.type(r9, r1)
            r2 = 2
            r3 = 0
            kotlin.reflect.jvm.internal.impl.types.KotlinType r0 = kotlin.reflect.jvm.internal.impl.serialization.deserialization.TypeDeserializer.type$default(r0, r1, r3, r2, r3)
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags$BooleanFlagField r1 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.DECLARES_DEFAULT_VALUE
            java.lang.Boolean r1 = r1.get(r10)
            java.lang.String r4 = "Flags.DECLARES_DEFAULT_VALUE.get(flags)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r4)
            boolean r16 = r1.booleanValue()
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags$BooleanFlagField r1 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.IS_CROSSINLINE
            java.lang.Boolean r1 = r1.get(r10)
            java.lang.String r4 = "Flags.IS_CROSSINLINE.get(flags)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r4)
            boolean r17 = r1.booleanValue()
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags$BooleanFlagField r1 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.IS_NOINLINE
            java.lang.Boolean r1 = r1.get(r10)
            java.lang.String r4 = "Flags.IS_NOINLINE.get(flags)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r4)
            boolean r18 = r1.booleanValue()
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext r1 = r8.f719c
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable r1 = r1.getTypeTable()
            kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Type r1 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.ProtoTypeTableUtilKt.varargElementType(r9, r1)
            if (r1 == 0) goto L_0x00f8
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext r4 = r8.f719c
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.TypeDeserializer r4 = r4.getTypeDeserializer()
            kotlin.reflect.jvm.internal.impl.types.KotlinType r1 = kotlin.reflect.jvm.internal.impl.serialization.deserialization.TypeDeserializer.type$default(r4, r1, r3, r2, r3)
            r19 = r1
            goto L_0x00fa
        L_0x00f8:
            r19 = r3
        L_0x00fa:
            kotlin.reflect.jvm.internal.impl.descriptors.SourceElement r1 = kotlin.reflect.jvm.internal.impl.descriptors.SourceElement.NO_SOURCE
            java.lang.String r2 = "SourceElement.NO_SOURCE"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r2)
            kotlin.reflect.jvm.internal.impl.descriptors.impl.ValueParameterDescriptorImpl r2 = new kotlin.reflect.jvm.internal.impl.descriptors.impl.ValueParameterDescriptorImpl
            r9 = r2
            r10 = r21
            r3 = r15
            r15 = r0
            r20 = r1
            r9.<init>(r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20)
            r3.add(r2)
            r15 = r3
            r12 = r25
            goto L_0x0034
        L_0x0115:
            r3 = r15
            r15 = r3
            java.util.List r15 = (java.util.List) r15
            java.lang.Iterable r15 = (java.lang.Iterable) r15
            java.util.List r0 = kotlin.collections.CollectionsKt.toList(r15)
            return r0
        L_0x0120:
            kotlin.TypeCastException r0 = new kotlin.TypeCastException
            java.lang.String r1 = "null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.CallableDescriptor"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.serialization.deserialization.MemberDeserializer.valueParameters(java.util.List, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite, kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotatedCallableKind):java.util.List");
    }

    /* access modifiers changed from: private */
    public final ProtoContainer asProtoContainer(@NotNull DeclarationDescriptor declarationDescriptor) {
        if (declarationDescriptor instanceof PackageFragmentDescriptor) {
            return new Package(((PackageFragmentDescriptor) declarationDescriptor).getFqName(), this.f719c.getNameResolver(), this.f719c.getTypeTable(), this.f719c.getContainerSource());
        }
        if (declarationDescriptor instanceof DeserializedClassDescriptor) {
            return ((DeserializedClassDescriptor) declarationDescriptor).getThisAsProtoContainer$deserialization();
        }
        return null;
    }
}
