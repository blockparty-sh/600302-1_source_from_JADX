package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FindClassInModuleKt;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.NotFoundClasses;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Annotation;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Annotation.Argument;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Annotation.Argument.Value;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Annotation.Argument.Value.C3041Type;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.KClassValue;
import kotlin.reflect.jvm.internal.impl.types.ErrorUtils;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import org.jetbrains.annotations.NotNull;

/* compiled from: AnnotationDeserializer.kt */
public final class AnnotationDeserializer {
    private final ModuleDescriptor module;
    private final NotFoundClasses notFoundClasses;

    public AnnotationDeserializer(@NotNull ModuleDescriptor moduleDescriptor, @NotNull NotFoundClasses notFoundClasses2) {
        Intrinsics.checkParameterIsNotNull(moduleDescriptor, "module");
        Intrinsics.checkParameterIsNotNull(notFoundClasses2, "notFoundClasses");
        this.module = moduleDescriptor;
        this.notFoundClasses = notFoundClasses2;
    }

    private final KotlinBuiltIns getBuiltIns() {
        return this.module.getBuiltIns();
    }

    @NotNull
    public final AnnotationDescriptor deserializeAnnotation(@NotNull Annotation annotation, @NotNull NameResolver nameResolver) {
        String str;
        Intrinsics.checkParameterIsNotNull(annotation, "proto");
        Intrinsics.checkParameterIsNotNull(nameResolver, "nameResolver");
        ClassDescriptor resolveClass = resolveClass(NameResolverUtilKt.getClassId(nameResolver, annotation.getId()));
        Map emptyMap = MapsKt.emptyMap();
        if (annotation.getArgumentCount() != 0) {
            DeclarationDescriptor declarationDescriptor = resolveClass;
            if (!ErrorUtils.isError(declarationDescriptor) && DescriptorUtils.isAnnotationClass(declarationDescriptor)) {
                Collection constructors = resolveClass.getConstructors();
                Intrinsics.checkExpressionValueIsNotNull(constructors, "annotationClass.constructors");
                ClassConstructorDescriptor classConstructorDescriptor = (ClassConstructorDescriptor) CollectionsKt.singleOrNull((Iterable<? extends T>) constructors);
                if (classConstructorDescriptor != null) {
                    List valueParameters = classConstructorDescriptor.getValueParameters();
                    Intrinsics.checkExpressionValueIsNotNull(valueParameters, "constructor.valueParameters");
                    Iterable iterable = valueParameters;
                    Map linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(iterable, 10)), 16));
                    Iterator it = iterable.iterator();
                    while (true) {
                        str = "it";
                        if (!it.hasNext()) {
                            break;
                        }
                        Object next = it.next();
                        ValueParameterDescriptor valueParameterDescriptor = (ValueParameterDescriptor) next;
                        Intrinsics.checkExpressionValueIsNotNull(valueParameterDescriptor, str);
                        linkedHashMap.put(valueParameterDescriptor.getName(), next);
                    }
                    List argumentList = annotation.getArgumentList();
                    Intrinsics.checkExpressionValueIsNotNull(argumentList, "proto.argumentList");
                    Iterable<Argument> iterable2 = argumentList;
                    Collection arrayList = new ArrayList();
                    for (Argument argument : iterable2) {
                        Intrinsics.checkExpressionValueIsNotNull(argument, str);
                        Pair resolveArgument = resolveArgument(argument, linkedHashMap, nameResolver);
                        if (resolveArgument != null) {
                            arrayList.add(resolveArgument);
                        }
                    }
                    emptyMap = MapsKt.toMap((Iterable<? extends Pair<? extends K, ? extends V>>) (List) arrayList);
                }
            }
        }
        return new AnnotationDescriptorImpl(resolveClass.getDefaultType(), emptyMap, SourceElement.NO_SOURCE);
    }

    private final Pair<Name, ConstantValue<?>> resolveArgument(Argument argument, Map<Name, ? extends ValueParameterDescriptor> map, NameResolver nameResolver) {
        ValueParameterDescriptor valueParameterDescriptor = (ValueParameterDescriptor) map.get(NameResolverUtilKt.getName(nameResolver, argument.getNameId()));
        if (valueParameterDescriptor == null) {
            return null;
        }
        Name name = NameResolverUtilKt.getName(nameResolver, argument.getNameId());
        KotlinType type = valueParameterDescriptor.getType();
        Intrinsics.checkExpressionValueIsNotNull(type, "parameter.type");
        Value value = argument.getValue();
        Intrinsics.checkExpressionValueIsNotNull(value, "proto.value");
        return new Pair<>(name, resolveValue(type, value, nameResolver));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:59:0x01f8, code lost:
        if (kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt.isSubtypeOf(r8.getType(r6.module), r7) == false) goto L_0x01fb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:?, code lost:
        return kotlin.reflect.jvm.internal.impl.resolve.constants.ErrorValue.Companion.create("Unexpected argument value");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:?, code lost:
        return r8;
     */
    @org.jetbrains.annotations.NotNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue<?> resolveValue(@org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.types.KotlinType r7, @org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Annotation.Argument.Value r8, @org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver r9) {
        /*
            r6 = this;
            java.lang.String r0 = "expectedType"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r7, r0)
            java.lang.String r0 = "value"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r8, r0)
            java.lang.String r0 = "nameResolver"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r9, r0)
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags$BooleanFlagField r0 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.IS_UNSIGNED
            int r1 = r8.getFlags()
            java.lang.Boolean r0 = r0.get(r1)
            kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Annotation$Argument$Value$Type r1 = r8.getType()
            if (r1 == 0) goto L_0x0207
            int[] r2 = kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotationDeserializer.WhenMappings.$EnumSwitchMapping$0
            int r1 = r1.ordinal()
            r1 = r2[r1]
            r2 = 0
            r3 = 1
            java.lang.String r4 = "isUnsigned"
            switch(r1) {
                case 1: goto L_0x01d1;
                case 2: goto L_0x01c2;
                case 3: goto L_0x01a4;
                case 4: goto L_0x0188;
                case 5: goto L_0x016c;
                case 6: goto L_0x015e;
                case 7: goto L_0x0150;
                case 8: goto L_0x013b;
                case 9: goto L_0x0129;
                case 10: goto L_0x011b;
                case 11: goto L_0x0101;
                case 12: goto L_0x00ea;
                case 13: goto L_0x0030;
                default: goto L_0x002e;
            }
        L_0x002e:
            goto L_0x0207
        L_0x0030:
            boolean r0 = kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns.isArray(r7)
            if (r0 != 0) goto L_0x003c
            boolean r0 = kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns.isPrimitiveArray(r7)
            if (r0 == 0) goto L_0x003d
        L_0x003c:
            r2 = 1
        L_0x003d:
            java.util.List r8 = r8.getArrayElementList()
            java.lang.String r0 = "arrayElements"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r8, r0)
            r0 = r8
            java.util.Collection r0 = (java.util.Collection) r0
            boolean r0 = r0.isEmpty()
            r0 = r0 ^ r3
            if (r0 == 0) goto L_0x0081
            java.lang.Object r0 = kotlin.collections.CollectionsKt.first(r8)
            java.lang.String r1 = "arrayElements.first()"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
            kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Annotation$Argument$Value r0 = (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Annotation.Argument.Value) r0
            kotlin.reflect.jvm.internal.impl.types.SimpleType r0 = r6.resolveArrayElementType(r0, r9)
            kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns r1 = r6.getBuiltIns()
            kotlin.reflect.jvm.internal.impl.types.KotlinType r0 = (kotlin.reflect.jvm.internal.impl.types.KotlinType) r0
            kotlin.reflect.jvm.internal.impl.types.SimpleType r1 = r1.getPrimitiveArrayKotlinTypeByPrimitiveKotlinType(r0)
            if (r1 == 0) goto L_0x006f
            r0 = r1
            kotlin.reflect.jvm.internal.impl.types.KotlinType r0 = (kotlin.reflect.jvm.internal.impl.types.KotlinType) r0
            goto L_0x00a0
        L_0x006f:
            kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns r1 = r6.getBuiltIns()
            kotlin.reflect.jvm.internal.impl.types.Variance r3 = kotlin.reflect.jvm.internal.impl.types.Variance.INVARIANT
            kotlin.reflect.jvm.internal.impl.types.SimpleType r0 = r1.getArrayType(r3, r0)
            java.lang.String r1 = "builtIns.getArrayType(Va…RIANT, actualElementType)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
            kotlin.reflect.jvm.internal.impl.types.KotlinType r0 = (kotlin.reflect.jvm.internal.impl.types.KotlinType) r0
            goto L_0x00a0
        L_0x0081:
            if (r2 == 0) goto L_0x0085
            r0 = r7
            goto L_0x00a0
        L_0x0085:
            kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns r0 = r6.getBuiltIns()
            kotlin.reflect.jvm.internal.impl.types.Variance r1 = kotlin.reflect.jvm.internal.impl.types.Variance.INVARIANT
            kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns r3 = r6.getBuiltIns()
            kotlin.reflect.jvm.internal.impl.types.SimpleType r3 = r3.getAnyType()
            kotlin.reflect.jvm.internal.impl.types.KotlinType r3 = (kotlin.reflect.jvm.internal.impl.types.KotlinType) r3
            kotlin.reflect.jvm.internal.impl.types.SimpleType r0 = r0.getArrayType(r1, r3)
            java.lang.String r1 = "builtIns.getArrayType(Va…ARIANT, builtIns.anyType)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
            kotlin.reflect.jvm.internal.impl.types.KotlinType r0 = (kotlin.reflect.jvm.internal.impl.types.KotlinType) r0
        L_0x00a0:
            kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns r1 = r6.getBuiltIns()
            if (r2 == 0) goto L_0x00a8
            r2 = r7
            goto L_0x00a9
        L_0x00a8:
            r2 = r0
        L_0x00a9:
            kotlin.reflect.jvm.internal.impl.types.KotlinType r1 = r1.getArrayElementType(r2)
            kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValueFactory r2 = kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValueFactory.INSTANCE
            java.lang.Iterable r8 = (java.lang.Iterable) r8
            java.util.ArrayList r3 = new java.util.ArrayList
            r4 = 10
            int r4 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r8, r4)
            r3.<init>(r4)
            java.util.Collection r3 = (java.util.Collection) r3
            java.util.Iterator r8 = r8.iterator()
        L_0x00c2:
            boolean r4 = r8.hasNext()
            if (r4 == 0) goto L_0x00e0
            java.lang.Object r4 = r8.next()
            kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Annotation$Argument$Value r4 = (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Annotation.Argument.Value) r4
            java.lang.String r5 = "expectedElementType"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r5)
            java.lang.String r5 = "it"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r4, r5)
            kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue r4 = r6.resolveValue(r1, r4, r9)
            r3.add(r4)
            goto L_0x00c2
        L_0x00e0:
            java.util.List r3 = (java.util.List) r3
            kotlin.reflect.jvm.internal.impl.resolve.constants.ArrayValue r8 = r2.createArrayValue(r3, r0)
            kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue r8 = (kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue) r8
            goto L_0x01ee
        L_0x00ea:
            kotlin.reflect.jvm.internal.impl.resolve.constants.AnnotationValue r0 = new kotlin.reflect.jvm.internal.impl.resolve.constants.AnnotationValue
            kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Annotation r8 = r8.getAnnotation()
            java.lang.String r1 = "value.annotation"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r8, r1)
            kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor r8 = r6.deserializeAnnotation(r8, r9)
            r0.<init>(r8)
            r8 = r0
            kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue r8 = (kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue) r8
            goto L_0x01ee
        L_0x0101:
            kotlin.reflect.jvm.internal.impl.resolve.constants.EnumValue r0 = new kotlin.reflect.jvm.internal.impl.resolve.constants.EnumValue
            int r1 = r8.getClassId()
            kotlin.reflect.jvm.internal.impl.name.ClassId r1 = kotlin.reflect.jvm.internal.impl.serialization.deserialization.NameResolverUtilKt.getClassId(r9, r1)
            int r8 = r8.getEnumValueId()
            kotlin.reflect.jvm.internal.impl.name.Name r8 = kotlin.reflect.jvm.internal.impl.serialization.deserialization.NameResolverUtilKt.getName(r9, r8)
            r0.<init>(r1, r8)
            r8 = r0
            kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue r8 = (kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue) r8
            goto L_0x01ee
        L_0x011b:
            int r8 = r8.getClassId()
            kotlin.reflect.jvm.internal.impl.name.ClassId r8 = kotlin.reflect.jvm.internal.impl.serialization.deserialization.NameResolverUtilKt.getClassId(r9, r8)
            kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue r8 = r6.resolveClassLiteralValue(r8)
            goto L_0x01ee
        L_0x0129:
            kotlin.reflect.jvm.internal.impl.resolve.constants.StringValue r0 = new kotlin.reflect.jvm.internal.impl.resolve.constants.StringValue
            int r8 = r8.getStringValue()
            java.lang.String r8 = r9.getString(r8)
            r0.<init>(r8)
            r8 = r0
            kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue r8 = (kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue) r8
            goto L_0x01ee
        L_0x013b:
            kotlin.reflect.jvm.internal.impl.resolve.constants.BooleanValue r9 = new kotlin.reflect.jvm.internal.impl.resolve.constants.BooleanValue
            long r0 = r8.getIntValue()
            r4 = 0
            int r8 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r8 == 0) goto L_0x0148
            r2 = 1
        L_0x0148:
            r9.<init>(r2)
            r8 = r9
            kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue r8 = (kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue) r8
            goto L_0x01ee
        L_0x0150:
            kotlin.reflect.jvm.internal.impl.resolve.constants.DoubleValue r9 = new kotlin.reflect.jvm.internal.impl.resolve.constants.DoubleValue
            double r0 = r8.getDoubleValue()
            r9.<init>(r0)
            r8 = r9
            kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue r8 = (kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue) r8
            goto L_0x01ee
        L_0x015e:
            kotlin.reflect.jvm.internal.impl.resolve.constants.FloatValue r9 = new kotlin.reflect.jvm.internal.impl.resolve.constants.FloatValue
            float r8 = r8.getFloatValue()
            r9.<init>(r8)
            r8 = r9
            kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue r8 = (kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue) r8
            goto L_0x01ee
        L_0x016c:
            long r8 = r8.getIntValue()
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r4)
            boolean r0 = r0.booleanValue()
            if (r0 == 0) goto L_0x017f
            kotlin.reflect.jvm.internal.impl.resolve.constants.ULongValue r0 = new kotlin.reflect.jvm.internal.impl.resolve.constants.ULongValue
            r0.<init>(r8)
            goto L_0x0184
        L_0x017f:
            kotlin.reflect.jvm.internal.impl.resolve.constants.LongValue r0 = new kotlin.reflect.jvm.internal.impl.resolve.constants.LongValue
            r0.<init>(r8)
        L_0x0184:
            r8 = r0
            kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue r8 = (kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue) r8
            goto L_0x01ee
        L_0x0188:
            long r8 = r8.getIntValue()
            int r9 = (int) r8
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r4)
            boolean r8 = r0.booleanValue()
            if (r8 == 0) goto L_0x019c
            kotlin.reflect.jvm.internal.impl.resolve.constants.UIntValue r8 = new kotlin.reflect.jvm.internal.impl.resolve.constants.UIntValue
            r8.<init>(r9)
            goto L_0x01a1
        L_0x019c:
            kotlin.reflect.jvm.internal.impl.resolve.constants.IntValue r8 = new kotlin.reflect.jvm.internal.impl.resolve.constants.IntValue
            r8.<init>(r9)
        L_0x01a1:
            kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue r8 = (kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue) r8
            goto L_0x01ee
        L_0x01a4:
            long r8 = r8.getIntValue()
            int r9 = (int) r8
            short r8 = (short) r9
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r4)
            boolean r9 = r0.booleanValue()
            if (r9 == 0) goto L_0x01b9
            kotlin.reflect.jvm.internal.impl.resolve.constants.UShortValue r9 = new kotlin.reflect.jvm.internal.impl.resolve.constants.UShortValue
            r9.<init>(r8)
            goto L_0x01be
        L_0x01b9:
            kotlin.reflect.jvm.internal.impl.resolve.constants.ShortValue r9 = new kotlin.reflect.jvm.internal.impl.resolve.constants.ShortValue
            r9.<init>(r8)
        L_0x01be:
            r8 = r9
            kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue r8 = (kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue) r8
            goto L_0x01ee
        L_0x01c2:
            kotlin.reflect.jvm.internal.impl.resolve.constants.CharValue r9 = new kotlin.reflect.jvm.internal.impl.resolve.constants.CharValue
            long r0 = r8.getIntValue()
            int r8 = (int) r0
            char r8 = (char) r8
            r9.<init>(r8)
            r8 = r9
            kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue r8 = (kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue) r8
            goto L_0x01ee
        L_0x01d1:
            long r8 = r8.getIntValue()
            int r9 = (int) r8
            byte r8 = (byte) r9
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r4)
            boolean r9 = r0.booleanValue()
            if (r9 == 0) goto L_0x01e6
            kotlin.reflect.jvm.internal.impl.resolve.constants.UByteValue r9 = new kotlin.reflect.jvm.internal.impl.resolve.constants.UByteValue
            r9.<init>(r8)
            goto L_0x01eb
        L_0x01e6:
            kotlin.reflect.jvm.internal.impl.resolve.constants.ByteValue r9 = new kotlin.reflect.jvm.internal.impl.resolve.constants.ByteValue
            r9.<init>(r8)
        L_0x01eb:
            r8 = r9
            kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue r8 = (kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue) r8
        L_0x01ee:
            kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor r9 = r6.module
            kotlin.reflect.jvm.internal.impl.types.KotlinType r9 = r8.getType(r9)
            boolean r7 = kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt.isSubtypeOf(r9, r7)
            if (r7 == 0) goto L_0x01fb
            goto L_0x0206
        L_0x01fb:
            kotlin.reflect.jvm.internal.impl.resolve.constants.ErrorValue$Companion r7 = kotlin.reflect.jvm.internal.impl.resolve.constants.ErrorValue.Companion
            java.lang.String r8 = "Unexpected argument value"
            kotlin.reflect.jvm.internal.impl.resolve.constants.ErrorValue r7 = r7.create(r8)
            r8 = r7
            kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue r8 = (kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue) r8
        L_0x0206:
            return r8
        L_0x0207:
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r0 = "Unsupported annotation argument type: "
            r9.append(r0)
            kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Annotation$Argument$Value$Type r8 = r8.getType()
            r9.append(r8)
            java.lang.String r8 = " (expected "
            r9.append(r8)
            r9.append(r7)
            r7 = 41
            r9.append(r7)
            java.lang.String r7 = r9.toString()
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r7 = r7.toString()
            r8.<init>(r7)
            java.lang.Throwable r8 = (java.lang.Throwable) r8
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotationDeserializer.resolveValue(kotlin.reflect.jvm.internal.impl.types.KotlinType, kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Annotation$Argument$Value, kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver):kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue");
    }

    private final ConstantValue<?> resolveClassLiteralValue(ClassId classId) {
        SimpleType defaultType = resolveClass(classId).getDefaultType();
        Intrinsics.checkExpressionValueIsNotNull(defaultType, "resolveClass(classId).defaultType");
        KotlinType replaceArgumentsWithStarProjections = TypeUtilsKt.replaceArgumentsWithStarProjections(defaultType);
        ClassId classId2 = ClassId.topLevel(KotlinBuiltIns.FQ_NAMES.kClass.toSafe());
        Intrinsics.checkExpressionValueIsNotNull(classId2, "ClassId.topLevel(KotlinB…FQ_NAMES.kClass.toSafe())");
        return new KClassValue<>(KotlinTypeFactory.simpleNotNullType(Annotations.Companion.getEMPTY(), resolveClass(classId2), CollectionsKt.listOf(new TypeProjectionImpl(replaceArgumentsWithStarProjections))));
    }

    private final SimpleType resolveArrayElementType(Value value, NameResolver nameResolver) {
        KotlinBuiltIns builtIns = getBuiltIns();
        C3041Type type = value.getType();
        if (type != null) {
            switch (type) {
                case BYTE:
                    SimpleType byteType = builtIns.getByteType();
                    Intrinsics.checkExpressionValueIsNotNull(byteType, "byteType");
                    return byteType;
                case CHAR:
                    SimpleType charType = builtIns.getCharType();
                    Intrinsics.checkExpressionValueIsNotNull(charType, "charType");
                    return charType;
                case SHORT:
                    SimpleType shortType = builtIns.getShortType();
                    Intrinsics.checkExpressionValueIsNotNull(shortType, "shortType");
                    return shortType;
                case INT:
                    SimpleType intType = builtIns.getIntType();
                    Intrinsics.checkExpressionValueIsNotNull(intType, "intType");
                    return intType;
                case LONG:
                    SimpleType longType = builtIns.getLongType();
                    Intrinsics.checkExpressionValueIsNotNull(longType, "longType");
                    return longType;
                case FLOAT:
                    SimpleType floatType = builtIns.getFloatType();
                    Intrinsics.checkExpressionValueIsNotNull(floatType, "floatType");
                    return floatType;
                case DOUBLE:
                    SimpleType doubleType = builtIns.getDoubleType();
                    Intrinsics.checkExpressionValueIsNotNull(doubleType, "doubleType");
                    return doubleType;
                case BOOLEAN:
                    SimpleType booleanType = builtIns.getBooleanType();
                    Intrinsics.checkExpressionValueIsNotNull(booleanType, "booleanType");
                    return booleanType;
                case STRING:
                    SimpleType stringType = builtIns.getStringType();
                    Intrinsics.checkExpressionValueIsNotNull(stringType, "stringType");
                    return stringType;
                case CLASS:
                    throw new IllegalStateException("Arrays of class literals are not supported yet".toString());
                case ENUM:
                    SimpleType defaultType = resolveClass(NameResolverUtilKt.getClassId(nameResolver, value.getClassId())).getDefaultType();
                    Intrinsics.checkExpressionValueIsNotNull(defaultType, "resolveClass(nameResolve…lue.classId)).defaultType");
                    return defaultType;
                case ANNOTATION:
                    Annotation annotation = value.getAnnotation();
                    Intrinsics.checkExpressionValueIsNotNull(annotation, "value.annotation");
                    SimpleType defaultType2 = resolveClass(NameResolverUtilKt.getClassId(nameResolver, annotation.getId())).getDefaultType();
                    Intrinsics.checkExpressionValueIsNotNull(defaultType2, "resolveClass(nameResolve…notation.id)).defaultType");
                    return defaultType2;
                case ARRAY:
                    throw new IllegalStateException("Array of arrays is impossible".toString());
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Unknown type: ");
        sb.append(value.getType());
        throw new IllegalStateException(sb.toString().toString());
    }

    private final ClassDescriptor resolveClass(ClassId classId) {
        return FindClassInModuleKt.findNonGenericClassAcrossDependencies(this.module, classId, this.notFoundClasses);
    }
}
