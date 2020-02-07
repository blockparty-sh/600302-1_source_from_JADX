package kotlin.reflect.jvm.internal;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.JvmFunctionSignature.FakeJavaAnnotationConstructor;
import kotlin.reflect.jvm.internal.JvmFunctionSignature.JavaConstructor;
import kotlin.reflect.jvm.internal.JvmFunctionSignature.JavaMethod;
import kotlin.reflect.jvm.internal.JvmFunctionSignature.KotlinConstructor;
import kotlin.reflect.jvm.internal.JvmFunctionSignature.KotlinFunction;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.builtins.PrimitiveType;
import kotlin.reflect.jvm.internal.impl.builtins.jvm.JavaToKotlinClassMap;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyGetterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertySetterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.load.java.JvmAbi;
import kotlin.reflect.jvm.internal.impl.load.java.SpecialBuiltinMembers;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaClassConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaMethodDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.sources.JavaSourceElement;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaElement;
import kotlin.reflect.jvm.internal.impl.load.kotlin.MethodSignatureMappingKt;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Constructor;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Function;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMemberSignature.Method;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmProtoBufUtil;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.protobuf.MessageLite;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorFactory;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmPrimitiveType;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedCallableMemberDescriptor;
import kotlin.reflect.jvm.internal.structure.ReflectClassUtilKt;
import kotlin.reflect.jvm.internal.structure.ReflectJavaClass;
import kotlin.reflect.jvm.internal.structure.ReflectJavaConstructor;
import kotlin.reflect.jvm.internal.structure.ReflectJavaMethod;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 2}, mo37404d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\n\u001a\u00020\u00042\n\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\u0007J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\u0012H\u0002J\u000e\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016J\u000e\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u000fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0005\u001a\u0004\u0018\u00010\u0006*\u0006\u0012\u0002\b\u00030\u00078BX\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\u001a"}, mo37405d2 = {"Lkotlin/reflect/jvm/internal/RuntimeTypeMapper;", "", "()V", "JAVA_LANG_VOID", "Lkotlin/reflect/jvm/internal/impl/name/ClassId;", "primitiveType", "Lkotlin/reflect/jvm/internal/impl/builtins/PrimitiveType;", "Ljava/lang/Class;", "getPrimitiveType", "(Ljava/lang/Class;)Lorg/jetbrains/kotlin/builtins/PrimitiveType;", "mapJvmClassToKotlinClassId", "klass", "mapJvmFunctionSignature", "Lkotlin/reflect/jvm/internal/JvmFunctionSignature$KotlinFunction;", "descriptor", "Lkotlin/reflect/jvm/internal/impl/descriptors/FunctionDescriptor;", "mapName", "", "Lkotlin/reflect/jvm/internal/impl/descriptors/CallableMemberDescriptor;", "mapPropertySignature", "Lkotlin/reflect/jvm/internal/JvmPropertySignature;", "possiblyOverriddenProperty", "Lkotlin/reflect/jvm/internal/impl/descriptors/PropertyDescriptor;", "mapSignature", "Lkotlin/reflect/jvm/internal/JvmFunctionSignature;", "possiblySubstitutedFunction", "kotlin-reflect-api"}, mo37406k = 1, mo37407mv = {1, 1, 11})
/* compiled from: RuntimeTypeMapper.kt */
public final class RuntimeTypeMapper {
    public static final RuntimeTypeMapper INSTANCE = new RuntimeTypeMapper();
    private static final ClassId JAVA_LANG_VOID = ClassId.topLevel(new FqName("java.lang.Void"));

    private RuntimeTypeMapper() {
    }

    @NotNull
    public final JvmFunctionSignature mapSignature(@NotNull FunctionDescriptor functionDescriptor) {
        JvmFunctionSignature jvmFunctionSignature;
        Intrinsics.checkParameterIsNotNull(functionDescriptor, "possiblySubstitutedFunction");
        CallableMemberDescriptor unwrapFakeOverride = DescriptorUtils.unwrapFakeOverride(functionDescriptor);
        Intrinsics.checkExpressionValueIsNotNull(unwrapFakeOverride, "DescriptorUtils.unwrapFa…siblySubstitutedFunction)");
        FunctionDescriptor original = ((FunctionDescriptor) unwrapFakeOverride).getOriginal();
        String str = "function";
        if (original instanceof DeserializedCallableMemberDescriptor) {
            DeserializedCallableMemberDescriptor deserializedCallableMemberDescriptor = (DeserializedCallableMemberDescriptor) original;
            MessageLite proto = deserializedCallableMemberDescriptor.getProto();
            if (proto instanceof Function) {
                Method jvmMethodSignature = JvmProtoBufUtil.INSTANCE.getJvmMethodSignature((Function) proto, deserializedCallableMemberDescriptor.getNameResolver(), deserializedCallableMemberDescriptor.getTypeTable());
                if (jvmMethodSignature != null) {
                    return new KotlinFunction(jvmMethodSignature);
                }
            }
            if (proto instanceof Constructor) {
                Method jvmConstructorSignature = JvmProtoBufUtil.INSTANCE.getJvmConstructorSignature((Constructor) proto, deserializedCallableMemberDescriptor.getNameResolver(), deserializedCallableMemberDescriptor.getTypeTable());
                if (jvmConstructorSignature != null) {
                    return new KotlinConstructor(jvmConstructorSignature);
                }
            }
            Intrinsics.checkExpressionValueIsNotNull(original, str);
            return mapJvmFunctionSignature(original);
        }
        JavaElement javaElement = null;
        if (original instanceof JavaMethodDescriptor) {
            Intrinsics.checkExpressionValueIsNotNull(original, str);
            SourceElement source = ((JavaMethodDescriptor) original).getSource();
            if (!(source instanceof JavaSourceElement)) {
                source = null;
            }
            JavaSourceElement javaSourceElement = (JavaSourceElement) source;
            JavaElement javaElement2 = javaSourceElement != null ? javaSourceElement.getJavaElement() : null;
            if (!(javaElement2 instanceof ReflectJavaMethod)) {
                javaElement2 = null;
            }
            ReflectJavaMethod reflectJavaMethod = (ReflectJavaMethod) javaElement2;
            if (reflectJavaMethod != null) {
                java.lang.reflect.Method member = reflectJavaMethod.getMember();
                if (member != null) {
                    return new JavaMethod(member);
                }
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Incorrect resolution sequence for Java method ");
            sb.append(original);
            throw new KotlinReflectionInternalError(sb.toString());
        }
        String str2 = " (";
        if (original instanceof JavaClassConstructorDescriptor) {
            Intrinsics.checkExpressionValueIsNotNull(original, str);
            SourceElement source2 = ((JavaClassConstructorDescriptor) original).getSource();
            if (!(source2 instanceof JavaSourceElement)) {
                source2 = null;
            }
            JavaSourceElement javaSourceElement2 = (JavaSourceElement) source2;
            if (javaSourceElement2 != null) {
                javaElement = javaSourceElement2.getJavaElement();
            }
            if (javaElement instanceof ReflectJavaConstructor) {
                jvmFunctionSignature = new JavaConstructor(((ReflectJavaConstructor) javaElement).getMember());
            } else {
                if (javaElement instanceof ReflectJavaClass) {
                    ReflectJavaClass reflectJavaClass = (ReflectJavaClass) javaElement;
                    if (reflectJavaClass.isAnnotationType()) {
                        jvmFunctionSignature = new FakeJavaAnnotationConstructor(reflectJavaClass.getElement());
                    }
                }
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Incorrect resolution sequence for Java constructor ");
                sb2.append(original);
                sb2.append(str2);
                sb2.append(javaElement);
                sb2.append(')');
                throw new KotlinReflectionInternalError(sb2.toString());
            }
            return jvmFunctionSignature;
        } else if (DescriptorFactory.isEnumValueOfMethod(original) || DescriptorFactory.isEnumValuesMethod(original)) {
            Intrinsics.checkExpressionValueIsNotNull(original, str);
            return mapJvmFunctionSignature(original);
        } else {
            StringBuilder sb3 = new StringBuilder();
            sb3.append("Unknown origin of ");
            sb3.append(original);
            sb3.append(str2);
            sb3.append(original.getClass());
            sb3.append(')');
            throw new KotlinReflectionInternalError(sb3.toString());
        }
    }

    /* JADX WARNING: type inference failed for: r2v0 */
    /* JADX WARNING: type inference failed for: r2v1, types: [kotlin.reflect.jvm.internal.JvmFunctionSignature$KotlinFunction] */
    /* JADX WARNING: type inference failed for: r2v2, types: [kotlin.reflect.jvm.internal.JvmFunctionSignature$KotlinFunction] */
    /* JADX WARNING: type inference failed for: r2v4, types: [java.lang.reflect.Method] */
    /* JADX WARNING: type inference failed for: r2v5, types: [java.lang.reflect.Method] */
    /* JADX WARNING: type inference failed for: r2v7 */
    /* JADX WARNING: type inference failed for: r2v8 */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r2v0
      assigns: [?[int, float, boolean, short, byte, char, OBJECT, ARRAY], java.lang.reflect.Method, kotlin.reflect.jvm.internal.JvmFunctionSignature$KotlinFunction]
      uses: [kotlin.reflect.jvm.internal.JvmFunctionSignature$KotlinFunction, java.lang.reflect.Method]
      mth insns count: 105
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
    	at jadx.core.ProcessClass.process(ProcessClass.java:35)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Unknown variable types count: 3 */
    @org.jetbrains.annotations.NotNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final kotlin.reflect.jvm.internal.JvmPropertySignature mapPropertySignature(@org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor r8) {
        /*
            r7 = this;
            java.lang.String r0 = "possiblyOverriddenProperty"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r8, r0)
            kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor r8 = (kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor) r8
            kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor r8 = kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils.unwrapFakeOverride(r8)
            java.lang.String r0 = "DescriptorUtils.unwrapFa…ssiblyOverriddenProperty)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r8, r0)
            kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor r8 = (kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor) r8
            kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor r1 = r8.getOriginal()
            boolean r8 = r1 instanceof kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedPropertyDescriptor
            java.lang.String r0 = "property"
            r2 = 0
            if (r8 == 0) goto L_0x004e
            r8 = r1
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedPropertyDescriptor r8 = (kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedPropertyDescriptor) r8
            kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Property r3 = r8.getProto()
            r4 = r3
            kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$ExtendableMessage r4 = (kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.ExtendableMessage) r4
            kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$GeneratedExtension<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Property, kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf$JvmPropertySignature> r5 = kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.propertySignature
            java.lang.String r6 = "JvmProtoBuf.propertySignature"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r5, r6)
            java.lang.Object r4 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.ProtoBufUtilKt.getExtensionOrNull(r4, r5)
            kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf$JvmPropertySignature r4 = (kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.JvmPropertySignature) r4
            if (r4 == 0) goto L_0x00dd
            kotlin.reflect.jvm.internal.JvmPropertySignature$KotlinProperty r6 = new kotlin.reflect.jvm.internal.JvmPropertySignature$KotlinProperty
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r0)
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver r5 = r8.getNameResolver()
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable r8 = r8.getTypeTable()
            r0 = r6
            r2 = r3
            r3 = r4
            r4 = r5
            r5 = r8
            r0.<init>(r1, r2, r3, r4, r5)
            kotlin.reflect.jvm.internal.JvmPropertySignature r6 = (kotlin.reflect.jvm.internal.JvmPropertySignature) r6
            return r6
        L_0x004e:
            boolean r8 = r1 instanceof kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaPropertyDescriptor
            if (r8 == 0) goto L_0x00dd
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r0)
            r8 = r1
            kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaPropertyDescriptor r8 = (kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaPropertyDescriptor) r8
            kotlin.reflect.jvm.internal.impl.descriptors.SourceElement r8 = r8.getSource()
            boolean r0 = r8 instanceof kotlin.reflect.jvm.internal.impl.load.java.sources.JavaSourceElement
            if (r0 != 0) goto L_0x0061
            r8 = r2
        L_0x0061:
            kotlin.reflect.jvm.internal.impl.load.java.sources.JavaSourceElement r8 = (kotlin.reflect.jvm.internal.impl.load.java.sources.JavaSourceElement) r8
            if (r8 == 0) goto L_0x006a
            kotlin.reflect.jvm.internal.impl.load.java.structure.JavaElement r8 = r8.getJavaElement()
            goto L_0x006b
        L_0x006a:
            r8 = r2
        L_0x006b:
            boolean r0 = r8 instanceof kotlin.reflect.jvm.internal.structure.ReflectJavaField
            if (r0 == 0) goto L_0x007d
            kotlin.reflect.jvm.internal.JvmPropertySignature$JavaField r0 = new kotlin.reflect.jvm.internal.JvmPropertySignature$JavaField
            kotlin.reflect.jvm.internal.structure.ReflectJavaField r8 = (kotlin.reflect.jvm.internal.structure.ReflectJavaField) r8
            java.lang.reflect.Field r8 = r8.getMember()
            r0.<init>(r8)
            kotlin.reflect.jvm.internal.JvmPropertySignature r0 = (kotlin.reflect.jvm.internal.JvmPropertySignature) r0
            goto L_0x00b6
        L_0x007d:
            boolean r0 = r8 instanceof kotlin.reflect.jvm.internal.structure.ReflectJavaMethod
            if (r0 == 0) goto L_0x00b7
            kotlin.reflect.jvm.internal.JvmPropertySignature$JavaMethodProperty r0 = new kotlin.reflect.jvm.internal.JvmPropertySignature$JavaMethodProperty
            kotlin.reflect.jvm.internal.structure.ReflectJavaMethod r8 = (kotlin.reflect.jvm.internal.structure.ReflectJavaMethod) r8
            java.lang.reflect.Method r8 = r8.getMember()
            kotlin.reflect.jvm.internal.impl.descriptors.PropertySetterDescriptor r1 = r1.getSetter()
            if (r1 == 0) goto L_0x0094
            kotlin.reflect.jvm.internal.impl.descriptors.SourceElement r1 = r1.getSource()
            goto L_0x0095
        L_0x0094:
            r1 = r2
        L_0x0095:
            boolean r3 = r1 instanceof kotlin.reflect.jvm.internal.impl.load.java.sources.JavaSourceElement
            if (r3 != 0) goto L_0x009a
            r1 = r2
        L_0x009a:
            kotlin.reflect.jvm.internal.impl.load.java.sources.JavaSourceElement r1 = (kotlin.reflect.jvm.internal.impl.load.java.sources.JavaSourceElement) r1
            if (r1 == 0) goto L_0x00a3
            kotlin.reflect.jvm.internal.impl.load.java.structure.JavaElement r1 = r1.getJavaElement()
            goto L_0x00a4
        L_0x00a3:
            r1 = r2
        L_0x00a4:
            boolean r3 = r1 instanceof kotlin.reflect.jvm.internal.structure.ReflectJavaMethod
            if (r3 != 0) goto L_0x00a9
            r1 = r2
        L_0x00a9:
            kotlin.reflect.jvm.internal.structure.ReflectJavaMethod r1 = (kotlin.reflect.jvm.internal.structure.ReflectJavaMethod) r1
            if (r1 == 0) goto L_0x00b1
            java.lang.reflect.Method r2 = r1.getMember()
        L_0x00b1:
            r0.<init>(r8, r2)
            kotlin.reflect.jvm.internal.JvmPropertySignature r0 = (kotlin.reflect.jvm.internal.JvmPropertySignature) r0
        L_0x00b6:
            return r0
        L_0x00b7:
            kotlin.reflect.jvm.internal.KotlinReflectionInternalError r0 = new kotlin.reflect.jvm.internal.KotlinReflectionInternalError
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Incorrect resolution sequence for Java field "
            r2.append(r3)
            r2.append(r1)
            java.lang.String r1 = " (source = "
            r2.append(r1)
            r2.append(r8)
            r8 = 41
            r2.append(r8)
            java.lang.String r8 = r2.toString()
            r0.<init>(r8)
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            throw r0
        L_0x00dd:
            kotlin.reflect.jvm.internal.impl.descriptors.PropertyGetterDescriptor r8 = r1.getGetter()
            if (r8 != 0) goto L_0x00e6
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x00e6:
            kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor r8 = (kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor) r8
            r0 = r7
            kotlin.reflect.jvm.internal.RuntimeTypeMapper r0 = (kotlin.reflect.jvm.internal.RuntimeTypeMapper) r0
            kotlin.reflect.jvm.internal.JvmFunctionSignature$KotlinFunction r8 = r0.mapJvmFunctionSignature(r8)
            kotlin.reflect.jvm.internal.impl.descriptors.PropertySetterDescriptor r1 = r1.getSetter()
            if (r1 == 0) goto L_0x00fb
            kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor r1 = (kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor) r1
            kotlin.reflect.jvm.internal.JvmFunctionSignature$KotlinFunction r2 = r0.mapJvmFunctionSignature(r1)
        L_0x00fb:
            kotlin.reflect.jvm.internal.JvmPropertySignature$MappedKotlinProperty r0 = new kotlin.reflect.jvm.internal.JvmPropertySignature$MappedKotlinProperty
            r0.<init>(r8, r2)
            kotlin.reflect.jvm.internal.JvmPropertySignature r0 = (kotlin.reflect.jvm.internal.JvmPropertySignature) r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.RuntimeTypeMapper.mapPropertySignature(kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor):kotlin.reflect.jvm.internal.JvmPropertySignature");
    }

    private final KotlinFunction mapJvmFunctionSignature(FunctionDescriptor functionDescriptor) {
        return new KotlinFunction(new Method(mapName(functionDescriptor), MethodSignatureMappingKt.computeJvmDescriptor$default(functionDescriptor, false, false, 1, null)));
    }

    private final String mapName(CallableMemberDescriptor callableMemberDescriptor) {
        String asString;
        String jvmMethodNameIfSpecial = SpecialBuiltinMembers.getJvmMethodNameIfSpecial(callableMemberDescriptor);
        if (jvmMethodNameIfSpecial == null) {
            if (callableMemberDescriptor instanceof PropertyGetterDescriptor) {
                asString = JvmAbi.getterName(DescriptorUtilsKt.getPropertyIfAccessor(callableMemberDescriptor).getName().asString());
            } else if (callableMemberDescriptor instanceof PropertySetterDescriptor) {
                asString = JvmAbi.setterName(DescriptorUtilsKt.getPropertyIfAccessor(callableMemberDescriptor).getName().asString());
            } else {
                asString = callableMemberDescriptor.getName().asString();
            }
            jvmMethodNameIfSpecial = asString;
            Intrinsics.checkExpressionValueIsNotNull(jvmMethodNameIfSpecial, "when (descriptor) {\n    …name.asString()\n        }");
        }
        return jvmMethodNameIfSpecial;
    }

    @NotNull
    public final ClassId mapJvmClassToKotlinClassId(@NotNull Class<?> cls) {
        Intrinsics.checkParameterIsNotNull(cls, "klass");
        if (cls.isArray()) {
            Class componentType = cls.getComponentType();
            Intrinsics.checkExpressionValueIsNotNull(componentType, "klass.componentType");
            PrimitiveType primitiveType = getPrimitiveType(componentType);
            if (primitiveType != null) {
                return new ClassId(KotlinBuiltIns.BUILT_INS_PACKAGE_FQ_NAME, primitiveType.getArrayTypeName());
            }
            ClassId classId = ClassId.topLevel(KotlinBuiltIns.FQ_NAMES.array.toSafe());
            Intrinsics.checkExpressionValueIsNotNull(classId, "ClassId.topLevel(KotlinB….FQ_NAMES.array.toSafe())");
            return classId;
        } else if (Intrinsics.areEqual((Object) cls, (Object) Void.TYPE)) {
            ClassId classId2 = JAVA_LANG_VOID;
            Intrinsics.checkExpressionValueIsNotNull(classId2, "JAVA_LANG_VOID");
            return classId2;
        } else {
            PrimitiveType primitiveType2 = getPrimitiveType(cls);
            if (primitiveType2 != null) {
                return new ClassId(KotlinBuiltIns.BUILT_INS_PACKAGE_FQ_NAME, primitiveType2.getTypeName());
            }
            ClassId classId3 = ReflectClassUtilKt.getClassId(cls);
            if (!classId3.isLocal()) {
                JavaToKotlinClassMap javaToKotlinClassMap = JavaToKotlinClassMap.INSTANCE;
                FqName asSingleFqName = classId3.asSingleFqName();
                Intrinsics.checkExpressionValueIsNotNull(asSingleFqName, "classId.asSingleFqName()");
                ClassId mapJavaToKotlin = javaToKotlinClassMap.mapJavaToKotlin(asSingleFqName);
                if (mapJavaToKotlin != null) {
                    return mapJavaToKotlin;
                }
            }
            return classId3;
        }
    }

    private final PrimitiveType getPrimitiveType(@NotNull Class<?> cls) {
        if (!cls.isPrimitive()) {
            return null;
        }
        JvmPrimitiveType jvmPrimitiveType = JvmPrimitiveType.get(cls.getSimpleName());
        Intrinsics.checkExpressionValueIsNotNull(jvmPrimitiveType, "JvmPrimitiveType.get(simpleName)");
        return jvmPrimitiveType.getPrimitiveType();
    }
}
