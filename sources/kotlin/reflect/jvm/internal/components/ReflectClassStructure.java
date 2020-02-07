package kotlin.reflect.jvm.internal.components;

import com.google.firebase.analytics.FirebaseAnalytics.Param;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.ArraysKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationArgumentVisitor;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationArrayArgumentVisitor;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationVisitor;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.MemberVisitor;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.MethodAnnotationVisitor;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.structure.ReflectClassUtilKt;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 2}, mo37404d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u001b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\bÂ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u00020\u00042\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u001c\u0010\t\u001a\u00020\u00042\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u00062\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u001c\u0010\f\u001a\u00020\u00042\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u00062\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u001c\u0010\r\u001a\u00020\u00042\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u00062\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u0018\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J \u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0001H\u0002J$\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00122\u0006\u0010\u000f\u001a\u00020\u00102\n\u0010\u0017\u001a\u0006\u0012\u0002\b\u00030\u0006H\u0002J\u001a\u0010\u0018\u001a\u00020\u00042\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u00062\u0006\u0010\n\u001a\u00020\u000b¨\u0006\u0019"}, mo37405d2 = {"Lkotlin/reflect/jvm/internal/components/ReflectClassStructure;", "", "()V", "loadClassAnnotations", "", "klass", "Ljava/lang/Class;", "visitor", "Lkotlin/reflect/jvm/internal/impl/load/kotlin/KotlinJvmBinaryClass$AnnotationVisitor;", "loadConstructorAnnotations", "memberVisitor", "Lkotlin/reflect/jvm/internal/impl/load/kotlin/KotlinJvmBinaryClass$MemberVisitor;", "loadFieldAnnotations", "loadMethodAnnotations", "processAnnotation", "annotation", "", "processAnnotationArgumentValue", "Lkotlin/reflect/jvm/internal/impl/load/kotlin/KotlinJvmBinaryClass$AnnotationArgumentVisitor;", "name", "Lkotlin/reflect/jvm/internal/impl/name/Name;", "value", "processAnnotationArguments", "annotationType", "visitMembers", "descriptors.runtime"}, mo37406k = 1, mo37407mv = {1, 1, 11})
/* compiled from: ReflectKotlinClass.kt */
final class ReflectClassStructure {
    public static final ReflectClassStructure INSTANCE = new ReflectClassStructure();

    private ReflectClassStructure() {
    }

    public final void loadClassAnnotations(@NotNull Class<?> cls, @NotNull AnnotationVisitor annotationVisitor) {
        Annotation[] declaredAnnotations;
        Intrinsics.checkParameterIsNotNull(cls, "klass");
        Intrinsics.checkParameterIsNotNull(annotationVisitor, "visitor");
        for (Annotation annotation : cls.getDeclaredAnnotations()) {
            Intrinsics.checkExpressionValueIsNotNull(annotation, "annotation");
            processAnnotation(annotationVisitor, annotation);
        }
        annotationVisitor.visitEnd();
    }

    public final void visitMembers(@NotNull Class<?> cls, @NotNull MemberVisitor memberVisitor) {
        Intrinsics.checkParameterIsNotNull(cls, "klass");
        Intrinsics.checkParameterIsNotNull(memberVisitor, "memberVisitor");
        loadMethodAnnotations(cls, memberVisitor);
        loadConstructorAnnotations(cls, memberVisitor);
        loadFieldAnnotations(cls, memberVisitor);
    }

    private final void loadMethodAnnotations(Class<?> cls, MemberVisitor memberVisitor) {
        Method[] methodArr;
        String str;
        Method[] declaredMethods = cls.getDeclaredMethods();
        int length = declaredMethods.length;
        int i = 0;
        while (i < length) {
            Method method = declaredMethods[i];
            Intrinsics.checkExpressionValueIsNotNull(method, Param.METHOD);
            Name identifier = Name.identifier(method.getName());
            Intrinsics.checkExpressionValueIsNotNull(identifier, "Name.identifier(method.name)");
            MethodAnnotationVisitor visitMethod = memberVisitor.visitMethod(identifier, SignatureSerializer.INSTANCE.methodDesc(method));
            if (visitMethod != null) {
                Annotation[] declaredAnnotations = method.getDeclaredAnnotations();
                int length2 = declaredAnnotations.length;
                int i2 = 0;
                while (true) {
                    str = "annotation";
                    if (i2 >= length2) {
                        break;
                    }
                    Annotation annotation = declaredAnnotations[i2];
                    AnnotationVisitor annotationVisitor = visitMethod;
                    Intrinsics.checkExpressionValueIsNotNull(annotation, str);
                    processAnnotation(annotationVisitor, annotation);
                    i2++;
                }
                Annotation[][] parameterAnnotations = method.getParameterAnnotations();
                Intrinsics.checkExpressionValueIsNotNull(parameterAnnotations, "method.parameterAnnotations");
                int length3 = parameterAnnotations.length;
                for (int i3 = 0; i3 < length3; i3++) {
                    Annotation[] annotationArr = parameterAnnotations[i3];
                    int length4 = annotationArr.length;
                    int i4 = 0;
                    while (i4 < length4) {
                        Annotation annotation2 = annotationArr[i4];
                        Class javaClass = JvmClassMappingKt.getJavaClass(JvmClassMappingKt.getAnnotationClass(annotation2));
                        ClassId classId = ReflectClassUtilKt.getClassId(javaClass);
                        Method[] methodArr2 = declaredMethods;
                        Intrinsics.checkExpressionValueIsNotNull(annotation2, str);
                        AnnotationArgumentVisitor visitParameterAnnotation = visitMethod.visitParameterAnnotation(i3, classId, new ReflectAnnotationSource(annotation2));
                        if (visitParameterAnnotation != null) {
                            INSTANCE.processAnnotationArguments(visitParameterAnnotation, annotation2, javaClass);
                        }
                        i4++;
                        declaredMethods = methodArr2;
                    }
                    Method[] methodArr3 = declaredMethods;
                }
                methodArr = declaredMethods;
                visitMethod.visitEnd();
            } else {
                methodArr = declaredMethods;
            }
            i++;
            declaredMethods = methodArr;
        }
    }

    private final void loadConstructorAnnotations(Class<?> cls, MemberVisitor memberVisitor) {
        int i;
        Constructor[] constructorArr;
        String str;
        Constructor[] declaredConstructors = cls.getDeclaredConstructors();
        int length = declaredConstructors.length;
        int i2 = 0;
        while (i2 < length) {
            Constructor constructor = declaredConstructors[i2];
            Name special = Name.special("<init>");
            Intrinsics.checkExpressionValueIsNotNull(special, "Name.special(\"<init>\")");
            SignatureSerializer signatureSerializer = SignatureSerializer.INSTANCE;
            Intrinsics.checkExpressionValueIsNotNull(constructor, "constructor");
            MethodAnnotationVisitor visitMethod = memberVisitor.visitMethod(special, signatureSerializer.constructorDesc(constructor));
            if (visitMethod != null) {
                Annotation[] declaredAnnotations = constructor.getDeclaredAnnotations();
                int length2 = declaredAnnotations.length;
                int i3 = 0;
                while (true) {
                    str = "annotation";
                    if (i3 >= length2) {
                        break;
                    }
                    Annotation annotation = declaredAnnotations[i3];
                    AnnotationVisitor annotationVisitor = visitMethod;
                    Intrinsics.checkExpressionValueIsNotNull(annotation, str);
                    processAnnotation(annotationVisitor, annotation);
                    i3++;
                }
                Annotation[][] parameterAnnotations = constructor.getParameterAnnotations();
                Intrinsics.checkExpressionValueIsNotNull(parameterAnnotations, "parameterAnnotations");
                Object[] objArr = (Object[]) parameterAnnotations;
                if (!(objArr.length == 0)) {
                    int length3 = constructor.getParameterTypes().length - objArr.length;
                    int length4 = parameterAnnotations.length;
                    for (int i4 = 0; i4 < length4; i4++) {
                        Annotation[] annotationArr = parameterAnnotations[i4];
                        int length5 = annotationArr.length;
                        int i5 = 0;
                        while (i5 < length5) {
                            Annotation annotation2 = annotationArr[i5];
                            Class javaClass = JvmClassMappingKt.getJavaClass(JvmClassMappingKt.getAnnotationClass(annotation2));
                            Constructor[] constructorArr2 = declaredConstructors;
                            int i6 = i4 + length3;
                            int i7 = length;
                            ClassId classId = ReflectClassUtilKt.getClassId(javaClass);
                            int i8 = length3;
                            Intrinsics.checkExpressionValueIsNotNull(annotation2, str);
                            AnnotationArgumentVisitor visitParameterAnnotation = visitMethod.visitParameterAnnotation(i6, classId, new ReflectAnnotationSource(annotation2));
                            if (visitParameterAnnotation != null) {
                                INSTANCE.processAnnotationArguments(visitParameterAnnotation, annotation2, javaClass);
                            }
                            i5++;
                            declaredConstructors = constructorArr2;
                            length = i7;
                            length3 = i8;
                        }
                        Constructor[] constructorArr3 = declaredConstructors;
                        int i9 = length;
                        int i10 = length3;
                    }
                }
                constructorArr = declaredConstructors;
                i = length;
                visitMethod.visitEnd();
            } else {
                constructorArr = declaredConstructors;
                i = length;
            }
            i2++;
            declaredConstructors = constructorArr;
            length = i;
        }
    }

    private final void loadFieldAnnotations(Class<?> cls, MemberVisitor memberVisitor) {
        Field[] declaredFields;
        Annotation[] declaredAnnotations;
        for (Field field : cls.getDeclaredFields()) {
            Intrinsics.checkExpressionValueIsNotNull(field, "field");
            Name identifier = Name.identifier(field.getName());
            Intrinsics.checkExpressionValueIsNotNull(identifier, "Name.identifier(field.name)");
            AnnotationVisitor visitField = memberVisitor.visitField(identifier, SignatureSerializer.INSTANCE.fieldDesc(field), null);
            if (visitField != null) {
                for (Annotation annotation : field.getDeclaredAnnotations()) {
                    Intrinsics.checkExpressionValueIsNotNull(annotation, "annotation");
                    processAnnotation(visitField, annotation);
                }
                visitField.visitEnd();
            }
        }
    }

    private final void processAnnotation(AnnotationVisitor annotationVisitor, Annotation annotation) {
        Class javaClass = JvmClassMappingKt.getJavaClass(JvmClassMappingKt.getAnnotationClass(annotation));
        AnnotationArgumentVisitor visitAnnotation = annotationVisitor.visitAnnotation(ReflectClassUtilKt.getClassId(javaClass), new ReflectAnnotationSource(annotation));
        if (visitAnnotation != null) {
            INSTANCE.processAnnotationArguments(visitAnnotation, annotation, javaClass);
        }
    }

    private final void processAnnotationArguments(AnnotationArgumentVisitor annotationArgumentVisitor, Annotation annotation, Class<?> cls) {
        Method[] declaredMethods;
        for (Method method : cls.getDeclaredMethods()) {
            try {
                Object invoke = method.invoke(annotation, new Object[0]);
                if (invoke == null) {
                    Intrinsics.throwNpe();
                }
                Intrinsics.checkExpressionValueIsNotNull(method, Param.METHOD);
                Name identifier = Name.identifier(method.getName());
                Intrinsics.checkExpressionValueIsNotNull(identifier, "Name.identifier(method.name)");
                processAnnotationArgumentValue(annotationArgumentVisitor, identifier, invoke);
            } catch (IllegalAccessException unused) {
            }
        }
        annotationArgumentVisitor.visitEnd();
    }

    private final void processAnnotationArgumentValue(AnnotationArgumentVisitor annotationArgumentVisitor, Name name, Object obj) {
        Class cls = obj.getClass();
        if (ReflectKotlinClassKt.TYPES_ELIGIBLE_FOR_SIMPLE_VISIT.contains(cls)) {
            annotationArgumentVisitor.visit(name, obj);
        } else {
            String str = "null cannot be cast to non-null type kotlin.Enum<*>";
            if (ReflectClassUtilKt.isEnumClassOrSpecializedEnumEntryClass(cls)) {
                if (!cls.isEnum()) {
                    cls = cls.getEnclosingClass();
                }
                Intrinsics.checkExpressionValueIsNotNull(cls, "(if (clazz.isEnum) clazz…lse clazz.enclosingClass)");
                ClassId classId = ReflectClassUtilKt.getClassId(cls);
                if (obj != null) {
                    Name identifier = Name.identifier(((Enum) obj).name());
                    Intrinsics.checkExpressionValueIsNotNull(identifier, "Name.identifier((value as Enum<*>).name)");
                    annotationArgumentVisitor.visitEnum(name, classId, identifier);
                } else {
                    throw new TypeCastException(str);
                }
            } else if (Annotation.class.isAssignableFrom(cls)) {
                Class[] interfaces = cls.getInterfaces();
                Intrinsics.checkExpressionValueIsNotNull(interfaces, "clazz.interfaces");
                Class cls2 = (Class) ArraysKt.single((Object[]) interfaces);
                Intrinsics.checkExpressionValueIsNotNull(cls2, "annotationClass");
                AnnotationArgumentVisitor visitAnnotation = annotationArgumentVisitor.visitAnnotation(name, ReflectClassUtilKt.getClassId(cls2));
                if (visitAnnotation == null) {
                    return;
                }
                if (obj != null) {
                    processAnnotationArguments(visitAnnotation, (Annotation) obj, cls2);
                } else {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.Annotation");
                }
            } else if (cls.isArray()) {
                AnnotationArrayArgumentVisitor visitArray = annotationArgumentVisitor.visitArray(name);
                if (visitArray != null) {
                    Class componentType = cls.getComponentType();
                    Intrinsics.checkExpressionValueIsNotNull(componentType, "componentType");
                    String str2 = "null cannot be cast to non-null type kotlin.Array<*>";
                    int i = 0;
                    if (componentType.isEnum()) {
                        ClassId classId2 = ReflectClassUtilKt.getClassId(componentType);
                        if (obj != null) {
                            Object[] objArr = (Object[]) obj;
                            int length = objArr.length;
                            while (i < length) {
                                Object obj2 = objArr[i];
                                if (obj2 != null) {
                                    Name identifier2 = Name.identifier(((Enum) obj2).name());
                                    Intrinsics.checkExpressionValueIsNotNull(identifier2, "Name.identifier((element as Enum<*>).name)");
                                    visitArray.visitEnum(classId2, identifier2);
                                    i++;
                                } else {
                                    throw new TypeCastException(str);
                                }
                            }
                        } else {
                            throw new TypeCastException(str2);
                        }
                    } else if (obj != null) {
                        Object[] objArr2 = (Object[]) obj;
                        int length2 = objArr2.length;
                        while (i < length2) {
                            visitArray.visit(objArr2[i]);
                            i++;
                        }
                    } else {
                        throw new TypeCastException(str2);
                    }
                    visitArray.visitEnd();
                }
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("Unsupported annotation argument value (");
                sb.append(cls);
                sb.append("): ");
                sb.append(obj);
                throw new UnsupportedOperationException(sb.toString());
            }
        }
    }
}
