package kotlin.reflect.jvm;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KDeclarationContainer;
import kotlin.reflect.KFunction;
import kotlin.reflect.KMutableProperty;
import kotlin.reflect.KProperty;
import kotlin.reflect.KProperty1;
import kotlin.reflect.KType;
import kotlin.reflect.full.KClasses;
import kotlin.reflect.jvm.internal.KPropertyImpl;
import kotlin.reflect.jvm.internal.KTypeImpl;
import kotlin.reflect.jvm.internal.UtilKt;
import kotlin.reflect.jvm.internal.impl.load.kotlin.header.KotlinClassHeader.Kind;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 2}, mo37404d1 = {"\u0000J\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u000e\u0010%\u001a\u0004\u0018\u00010&*\u00020'H\u0002\"2\u0010\u0000\u001a\n\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00038FX\u0004¢\u0006\f\u0012\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007\"\u001b\u0010\b\u001a\u0004\u0018\u00010\t*\u0006\u0012\u0002\b\u00030\n8F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f\"\u001b\u0010\r\u001a\u0004\u0018\u00010\u000e*\u0006\u0012\u0002\b\u00030\n8F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010\"\u001b\u0010\u0011\u001a\u0004\u0018\u00010\u000e*\u0006\u0012\u0002\b\u00030\u00038F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013\"\u001b\u0010\u0014\u001a\u0004\u0018\u00010\u000e*\u0006\u0012\u0002\b\u00030\u00158F¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017\"\u0015\u0010\u0018\u001a\u00020\u0019*\u00020\u001a8F¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001c\"-\u0010\u001d\u001a\n\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\u0003\"\b\b\u0000\u0010\u0002*\u00020\u001e*\b\u0012\u0004\u0012\u0002H\u00020\u00018F¢\u0006\u0006\u001a\u0004\b\u001f\u0010 \"\u001b\u0010\u001d\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0003*\u00020\u000e8F¢\u0006\u0006\u001a\u0004\b\u001f\u0010!\"\u001b\u0010\"\u001a\b\u0012\u0002\b\u0003\u0018\u00010\n*\u00020\t8F¢\u0006\u0006\u001a\u0004\b#\u0010$¨\u0006("}, mo37405d2 = {"javaConstructor", "Ljava/lang/reflect/Constructor;", "T", "Lkotlin/reflect/KFunction;", "javaConstructor$annotations", "(Lkotlin/reflect/KFunction;)V", "getJavaConstructor", "(Lkotlin/reflect/KFunction;)Ljava/lang/reflect/Constructor;", "javaField", "Ljava/lang/reflect/Field;", "Lkotlin/reflect/KProperty;", "getJavaField", "(Lkotlin/reflect/KProperty;)Ljava/lang/reflect/Field;", "javaGetter", "Ljava/lang/reflect/Method;", "getJavaGetter", "(Lkotlin/reflect/KProperty;)Ljava/lang/reflect/Method;", "javaMethod", "getJavaMethod", "(Lkotlin/reflect/KFunction;)Ljava/lang/reflect/Method;", "javaSetter", "Lkotlin/reflect/KMutableProperty;", "getJavaSetter", "(Lkotlin/reflect/KMutableProperty;)Ljava/lang/reflect/Method;", "javaType", "Ljava/lang/reflect/Type;", "Lkotlin/reflect/KType;", "getJavaType", "(Lkotlin/reflect/KType;)Ljava/lang/reflect/Type;", "kotlinFunction", "", "getKotlinFunction", "(Ljava/lang/reflect/Constructor;)Lkotlin/reflect/KFunction;", "(Ljava/lang/reflect/Method;)Lkotlin/reflect/KFunction;", "kotlinProperty", "getKotlinProperty", "(Ljava/lang/reflect/Field;)Lkotlin/reflect/KProperty;", "getKPackage", "Lkotlin/reflect/KDeclarationContainer;", "Ljava/lang/reflect/Member;", "kotlin-reflect-api"}, mo37406k = 2, mo37407mv = {1, 1, 11})
@JvmName(name = "ReflectJvmMapping")
/* compiled from: ReflectJvmMapping.kt */
public final class ReflectJvmMapping {

    @Metadata(mo37403bv = {1, 0, 2}, mo37406k = 3, mo37407mv = {1, 1, 11})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[Kind.values().length];

        static {
            $EnumSwitchMapping$0[Kind.FILE_FACADE.ordinal()] = 1;
            $EnumSwitchMapping$0[Kind.MULTIFILE_CLASS.ordinal()] = 2;
            $EnumSwitchMapping$0[Kind.MULTIFILE_CLASS_PART.ordinal()] = 3;
        }
    }

    public static /* synthetic */ void javaConstructor$annotations(KFunction kFunction) {
    }

    @Nullable
    public static final Field getJavaField(@NotNull KProperty<?> kProperty) {
        Intrinsics.checkParameterIsNotNull(kProperty, "$receiver");
        KPropertyImpl asKPropertyImpl = UtilKt.asKPropertyImpl(kProperty);
        if (asKPropertyImpl != null) {
            return asKPropertyImpl.getJavaField();
        }
        return null;
    }

    @Nullable
    public static final Method getJavaGetter(@NotNull KProperty<?> kProperty) {
        Intrinsics.checkParameterIsNotNull(kProperty, "$receiver");
        return getJavaMethod(kProperty.getGetter());
    }

    @Nullable
    public static final Method getJavaSetter(@NotNull KMutableProperty<?> kMutableProperty) {
        Intrinsics.checkParameterIsNotNull(kMutableProperty, "$receiver");
        return getJavaMethod(kMutableProperty.getSetter());
    }

    /* JADX WARNING: Removed duplicated region for block: B:8:0x001c  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.reflect.Method getJavaMethod(@org.jetbrains.annotations.NotNull kotlin.reflect.KFunction<?> r2) {
        /*
            java.lang.String r0 = "$receiver"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r2, r0)
            kotlin.reflect.jvm.internal.KCallableImpl r2 = kotlin.reflect.jvm.internal.UtilKt.asKCallableImpl(r2)
            r0 = 0
            if (r2 == 0) goto L_0x0017
            kotlin.reflect.jvm.internal.FunctionCaller r2 = r2.getCaller()
            if (r2 == 0) goto L_0x0017
            java.lang.reflect.Member r2 = r2.getMember$kotlin_reflect_api()
            goto L_0x0018
        L_0x0017:
            r2 = r0
        L_0x0018:
            boolean r1 = r2 instanceof java.lang.reflect.Method
            if (r1 != 0) goto L_0x001d
            r2 = r0
        L_0x001d:
            java.lang.reflect.Method r2 = (java.lang.reflect.Method) r2
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.ReflectJvmMapping.getJavaMethod(kotlin.reflect.KFunction):java.lang.reflect.Method");
    }

    /* JADX WARNING: Removed duplicated region for block: B:8:0x001c  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <T> java.lang.reflect.Constructor<T> getJavaConstructor(@org.jetbrains.annotations.NotNull kotlin.reflect.KFunction<? extends T> r2) {
        /*
            java.lang.String r0 = "$receiver"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r2, r0)
            kotlin.reflect.jvm.internal.KCallableImpl r2 = kotlin.reflect.jvm.internal.UtilKt.asKCallableImpl(r2)
            r0 = 0
            if (r2 == 0) goto L_0x0017
            kotlin.reflect.jvm.internal.FunctionCaller r2 = r2.getCaller()
            if (r2 == 0) goto L_0x0017
            java.lang.reflect.Member r2 = r2.getMember$kotlin_reflect_api()
            goto L_0x0018
        L_0x0017:
            r2 = r0
        L_0x0018:
            boolean r1 = r2 instanceof java.lang.reflect.Constructor
            if (r1 != 0) goto L_0x001d
            r2 = r0
        L_0x001d:
            java.lang.reflect.Constructor r2 = (java.lang.reflect.Constructor) r2
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.ReflectJvmMapping.getJavaConstructor(kotlin.reflect.KFunction):java.lang.reflect.Constructor");
    }

    @NotNull
    public static final Type getJavaType(@NotNull KType kType) {
        Intrinsics.checkParameterIsNotNull(kType, "$receiver");
        return ((KTypeImpl) kType).getJavaType$kotlin_reflect_api();
    }

    @Nullable
    public static final KProperty<?> getKotlinProperty(@NotNull Field field) {
        Intrinsics.checkParameterIsNotNull(field, "$receiver");
        Object obj = null;
        if (field.isSynthetic()) {
            return null;
        }
        KDeclarationContainer kPackage = getKPackage(field);
        if (kPackage != null) {
            Iterable members = kPackage.getMembers();
            Collection arrayList = new ArrayList();
            for (Object next : members) {
                if (next instanceof KProperty) {
                    arrayList.add(next);
                }
            }
            Iterator it = ((List) arrayList).iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Object next2 = it.next();
                if (Intrinsics.areEqual((Object) getJavaField((KProperty) next2), (Object) field)) {
                    obj = next2;
                    break;
                }
            }
            return (KProperty) obj;
        }
        Class declaringClass = field.getDeclaringClass();
        Intrinsics.checkExpressionValueIsNotNull(declaringClass, "declaringClass");
        Iterator it2 = KClasses.getMemberProperties(JvmClassMappingKt.getKotlinClass(declaringClass)).iterator();
        while (true) {
            if (!it2.hasNext()) {
                break;
            }
            Object next3 = it2.next();
            if (Intrinsics.areEqual((Object) getJavaField((KProperty1) next3), (Object) field)) {
                obj = next3;
                break;
            }
        }
        return (KProperty) obj;
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:7:0x0021  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final kotlin.reflect.KDeclarationContainer getKPackage(@org.jetbrains.annotations.NotNull java.lang.reflect.Member r5) {
        /*
            kotlin.reflect.jvm.internal.components.ReflectKotlinClass$Factory r0 = kotlin.reflect.jvm.internal.components.ReflectKotlinClass.Factory
            java.lang.Class r1 = r5.getDeclaringClass()
            java.lang.String r2 = "declaringClass"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r2)
            kotlin.reflect.jvm.internal.components.ReflectKotlinClass r0 = r0.create(r1)
            r1 = 0
            if (r0 == 0) goto L_0x001d
            kotlin.reflect.jvm.internal.impl.load.kotlin.header.KotlinClassHeader r0 = r0.getClassHeader()
            if (r0 == 0) goto L_0x001d
            kotlin.reflect.jvm.internal.impl.load.kotlin.header.KotlinClassHeader$Kind r0 = r0.getKind()
            goto L_0x001e
        L_0x001d:
            r0 = r1
        L_0x001e:
            if (r0 != 0) goto L_0x0021
            goto L_0x0042
        L_0x0021:
            int[] r3 = kotlin.reflect.jvm.ReflectJvmMapping.WhenMappings.$EnumSwitchMapping$0
            int r0 = r0.ordinal()
            r0 = r3[r0]
            r3 = 1
            r4 = 2
            if (r0 == r3) goto L_0x0033
            if (r0 == r4) goto L_0x0033
            r3 = 3
            if (r0 == r3) goto L_0x0033
            goto L_0x0042
        L_0x0033:
            kotlin.reflect.jvm.internal.KPackageImpl r0 = new kotlin.reflect.jvm.internal.KPackageImpl
            java.lang.Class r5 = r5.getDeclaringClass()
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r5, r2)
            r0.<init>(r5, r1, r4, r1)
            r1 = r0
            kotlin.reflect.KDeclarationContainer r1 = (kotlin.reflect.KDeclarationContainer) r1
        L_0x0042:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.ReflectJvmMapping.getKPackage(java.lang.reflect.Member):kotlin.reflect.KDeclarationContainer");
    }

    /* JADX WARNING: Removed duplicated region for block: B:59:0x00d1 A[EDGE_INSN: B:59:0x00d1->B:41:0x00d1 ?: BREAK  , SYNTHETIC] */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final kotlin.reflect.KFunction<?> getKotlinFunction(@org.jetbrains.annotations.NotNull java.lang.reflect.Method r8) {
        /*
            java.lang.String r0 = "$receiver"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r8, r0)
            boolean r0 = r8.isSynthetic()
            r1 = 0
            if (r0 == 0) goto L_0x000d
            return r1
        L_0x000d:
            int r0 = r8.getModifiers()
            boolean r0 = java.lang.reflect.Modifier.isStatic(r0)
            java.lang.String r2 = "declaringClass"
            if (r0 == 0) goto L_0x00d6
            r0 = r8
            java.lang.reflect.Member r0 = (java.lang.reflect.Member) r0
            kotlin.reflect.KDeclarationContainer r0 = getKPackage(r0)
            if (r0 == 0) goto L_0x0068
            java.util.Collection r0 = r0.getMembers()
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            java.util.Collection r2 = (java.util.Collection) r2
            java.util.Iterator r0 = r0.iterator()
        L_0x0033:
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto L_0x0045
            java.lang.Object r3 = r0.next()
            boolean r4 = r3 instanceof kotlin.reflect.KFunction
            if (r4 == 0) goto L_0x0033
            r2.add(r3)
            goto L_0x0033
        L_0x0045:
            java.util.List r2 = (java.util.List) r2
            java.lang.Iterable r2 = (java.lang.Iterable) r2
            java.util.Iterator r0 = r2.iterator()
        L_0x004d:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x0065
            java.lang.Object r2 = r0.next()
            r3 = r2
            kotlin.reflect.KFunction r3 = (kotlin.reflect.KFunction) r3
            java.lang.reflect.Method r3 = getJavaMethod(r3)
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r3, r8)
            if (r3 == 0) goto L_0x004d
            r1 = r2
        L_0x0065:
            kotlin.reflect.KFunction r1 = (kotlin.reflect.KFunction) r1
            return r1
        L_0x0068:
            java.lang.Class r0 = r8.getDeclaringClass()
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r2)
            kotlin.reflect.KClass r0 = kotlin.jvm.JvmClassMappingKt.getKotlinClass(r0)
            kotlin.reflect.KClass r0 = kotlin.reflect.full.KClasses.getCompanionObject(r0)
            if (r0 == 0) goto L_0x00d6
            java.util.Collection r0 = kotlin.reflect.full.KClasses.getFunctions(r0)
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            java.util.Iterator r0 = r0.iterator()
        L_0x0083:
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto L_0x00d0
            java.lang.Object r3 = r0.next()
            r4 = r3
            kotlin.reflect.KFunction r4 = (kotlin.reflect.KFunction) r4
            java.lang.reflect.Method r4 = getJavaMethod(r4)
            if (r4 == 0) goto L_0x00cc
            java.lang.String r5 = r4.getName()
            java.lang.String r6 = r8.getName()
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual(r5, r6)
            if (r5 == 0) goto L_0x00cc
            java.lang.Class[] r5 = r4.getParameterTypes()
            if (r5 != 0) goto L_0x00ad
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x00ad:
            java.lang.Class[] r6 = r8.getParameterTypes()
            java.lang.String r7 = "this.parameterTypes"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r6, r7)
            boolean r5 = java.util.Arrays.equals(r5, r6)
            if (r5 == 0) goto L_0x00cc
            java.lang.Class r4 = r4.getReturnType()
            java.lang.Class r5 = r8.getReturnType()
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual(r4, r5)
            if (r4 == 0) goto L_0x00cc
            r4 = 1
            goto L_0x00cd
        L_0x00cc:
            r4 = 0
        L_0x00cd:
            if (r4 == 0) goto L_0x0083
            goto L_0x00d1
        L_0x00d0:
            r3 = r1
        L_0x00d1:
            kotlin.reflect.KFunction r3 = (kotlin.reflect.KFunction) r3
            if (r3 == 0) goto L_0x00d6
            return r3
        L_0x00d6:
            java.lang.Class r0 = r8.getDeclaringClass()
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r2)
            kotlin.reflect.KClass r0 = kotlin.jvm.JvmClassMappingKt.getKotlinClass(r0)
            java.util.Collection r0 = kotlin.reflect.full.KClasses.getFunctions(r0)
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            java.util.Iterator r0 = r0.iterator()
        L_0x00eb:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x0103
            java.lang.Object r2 = r0.next()
            r3 = r2
            kotlin.reflect.KFunction r3 = (kotlin.reflect.KFunction) r3
            java.lang.reflect.Method r3 = getJavaMethod(r3)
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r3, r8)
            if (r3 == 0) goto L_0x00eb
            r1 = r2
        L_0x0103:
            kotlin.reflect.KFunction r1 = (kotlin.reflect.KFunction) r1
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.ReflectJvmMapping.getKotlinFunction(java.lang.reflect.Method):kotlin.reflect.KFunction");
    }

    @Nullable
    public static final <T> KFunction<T> getKotlinFunction(@NotNull Constructor<T> constructor) {
        Intrinsics.checkParameterIsNotNull(constructor, "$receiver");
        Object obj = null;
        if (constructor.isSynthetic()) {
            return null;
        }
        Class declaringClass = constructor.getDeclaringClass();
        Intrinsics.checkExpressionValueIsNotNull(declaringClass, "declaringClass");
        Iterator it = JvmClassMappingKt.getKotlinClass(declaringClass).getConstructors().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            if (Intrinsics.areEqual((Object) getJavaConstructor((KFunction) next), (Object) constructor)) {
                obj = next;
                break;
            }
        }
        return (KFunction) obj;
    }
}
