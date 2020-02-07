package kotlin.reflect.jvm.internal;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference0Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.reflect.KProperty;
import kotlin.text.Typography;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 2}, mo37404d1 = {"\u00000\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\u001a6\u0010\u0000\u001a\u00020\u00012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00010\bH\u0002\u001a$\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\t2\n\u0010\u000f\u001a\u0006\u0012\u0002\b\u00030\u0003H\u0002\u001a\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u0001*\u0004\u0018\u00010\u00012\n\u0010\u0011\u001a\u0006\u0012\u0002\b\u00030\u0003H\u0002¨\u0006\u0012²\u0006\r\u0010\u0013\u001a\u00020\rX\u0002¢\u0006\u0000²\u0006\r\u0010\u0014\u001a\u00020\tX\u0002¢\u0006\u0000"}, mo37405d2 = {"createAnnotationInstance", "", "annotationClass", "Ljava/lang/Class;", "methods", "", "Ljava/lang/reflect/Method;", "values", "", "", "throwIllegalArgumentType", "", "index", "", "name", "expectedJvmType", "transformKotlinToJvm", "expectedType", "kotlin-reflect-api", "hashCode", "toString"}, mo37406k = 2, mo37407mv = {1, 1, 11})
/* compiled from: AnnotationConstructorCaller.kt */
public final class AnnotationConstructorCallerKt {
    static final /* synthetic */ KProperty[] $$delegatedProperties;

    static {
        String str = "kotlin-reflect-api";
        $$delegatedProperties = new KProperty[]{Reflection.property0(new PropertyReference0Impl(Reflection.getOrCreateKotlinPackage(AnnotationConstructorCallerKt.class, str), "hashCode", "<v#0>")), Reflection.property0(new PropertyReference0Impl(Reflection.getOrCreateKotlinPackage(AnnotationConstructorCallerKt.class, str), "toString", "<v#1>"))};
    }

    /* access modifiers changed from: private */
    public static final Object transformKotlinToJvm(@Nullable Object obj, Class<?> cls) {
        if (obj instanceof Class) {
            return null;
        }
        if (obj instanceof KClass) {
            obj = JvmClassMappingKt.getJavaClass((KClass) obj);
        } else if (obj instanceof Object[]) {
            Object obj2 = (Object[]) obj;
            if (obj2 instanceof Class[]) {
                return null;
            }
            if (!(obj2 instanceof KClass[])) {
                obj = obj2;
            } else if (obj != null) {
                KClass[] kClassArr = (KClass[]) obj;
                Collection arrayList = new ArrayList(kClassArr.length);
                for (KClass javaClass : kClassArr) {
                    arrayList.add(JvmClassMappingKt.getJavaClass(javaClass));
                }
                obj = ((List) arrayList).toArray(new Class[0]);
                if (obj == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
                }
            } else {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<kotlin.reflect.KClass<*>>");
            }
        }
        if (!cls.isInstance(obj)) {
            obj = null;
        }
        return obj;
    }

    /* access modifiers changed from: private */
    public static final Void throwIllegalArgumentType(int i, String str, Class<?> cls) {
        KClass kClass;
        String str2;
        if (Intrinsics.areEqual((Object) cls, (Object) Class.class)) {
            kClass = Reflection.getOrCreateKotlinClass(KClass.class);
        } else if (!cls.isArray() || !Intrinsics.areEqual((Object) cls.getComponentType(), (Object) Class.class)) {
            kClass = JvmClassMappingKt.getKotlinClass(cls);
        } else {
            kClass = Reflection.getOrCreateKotlinClass(KClass[].class);
        }
        if (Intrinsics.areEqual((Object) kClass.getQualifiedName(), (Object) Reflection.getOrCreateKotlinClass(Object[].class).getQualifiedName())) {
            StringBuilder sb = new StringBuilder();
            sb.append(kClass.getQualifiedName());
            sb.append(Typography.less);
            Class componentType = JvmClassMappingKt.getJavaClass(kClass).getComponentType();
            Intrinsics.checkExpressionValueIsNotNull(componentType, "kotlinClass.java.componentType");
            sb.append(JvmClassMappingKt.getKotlinClass(componentType).getQualifiedName());
            sb.append(Typography.greater);
            str2 = sb.toString();
        } else {
            str2 = kClass.getQualifiedName();
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Argument #");
        sb2.append(i);
        sb2.append(' ');
        sb2.append(str);
        sb2.append(" is not of the required type ");
        sb2.append(str2);
        throw new IllegalArgumentException(sb2.toString());
    }

    /* access modifiers changed from: private */
    public static final Object createAnnotationInstance(Class<?> cls, List<Method> list, Map<String, ? extends Object> map) {
        AnnotationConstructorCallerKt$createAnnotationInstance$1 annotationConstructorCallerKt$createAnnotationInstance$1 = new AnnotationConstructorCallerKt$createAnnotationInstance$1(cls, list, map);
        Lazy lazy = LazyKt.lazy(new C1412xa743ec88(map));
        KProperty kProperty = $$delegatedProperties[0];
        Lazy lazy2 = LazyKt.lazy(new C1413x4fc4299(cls, map));
        KProperty kProperty2 = $$delegatedProperties[1];
        ClassLoader classLoader = cls.getClassLoader();
        Class[] clsArr = {cls};
        AnnotationConstructorCallerKt$createAnnotationInstance$2 annotationConstructorCallerKt$createAnnotationInstance$2 = new AnnotationConstructorCallerKt$createAnnotationInstance$2(cls, lazy2, kProperty2, lazy, kProperty, annotationConstructorCallerKt$createAnnotationInstance$1, map);
        Object newProxyInstance = Proxy.newProxyInstance(classLoader, clsArr, annotationConstructorCallerKt$createAnnotationInstance$2);
        Intrinsics.checkExpressionValueIsNotNull(newProxyInstance, "Proxy.newProxyInstance(a…        }\n        }\n    }");
        return newProxyInstance;
    }
}
