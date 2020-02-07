package com.squareup.moshi.internal;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonClass;
import com.squareup.moshi.JsonQualifier;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Constructor;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import javax.annotation.Nullable;

public final class Util {
    public static final Type[] EMPTY_TYPE_ARRAY = new Type[0];
    public static final Set<Annotation> NO_ANNOTATIONS = Collections.emptySet();

    public static final class GenericArrayTypeImpl implements GenericArrayType {
        private final Type componentType;

        public GenericArrayTypeImpl(Type type) {
            this.componentType = Util.canonicalize(type);
        }

        public Type getGenericComponentType() {
            return this.componentType;
        }

        public boolean equals(Object obj) {
            return (obj instanceof GenericArrayType) && Types.equals(this, (GenericArrayType) obj);
        }

        public int hashCode() {
            return this.componentType.hashCode();
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(Util.typeToString(this.componentType));
            sb.append("[]");
            return sb.toString();
        }
    }

    public static final class ParameterizedTypeImpl implements ParameterizedType {
        @Nullable
        private final Type ownerType;
        private final Type rawType;
        public final Type[] typeArguments;

        public ParameterizedTypeImpl(@Nullable Type type, Type type2, Type... typeArr) {
            Type type3;
            if (type2 instanceof Class) {
                Class enclosingClass = ((Class) type2).getEnclosingClass();
                String str = "unexpected owner type for ";
                if (type != null) {
                    if (enclosingClass == null || Types.getRawType(type) != enclosingClass) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(str);
                        sb.append(type2);
                        sb.append(": ");
                        sb.append(type);
                        throw new IllegalArgumentException(sb.toString());
                    }
                } else if (enclosingClass != null) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(str);
                    sb2.append(type2);
                    sb2.append(": null");
                    throw new IllegalArgumentException(sb2.toString());
                }
            }
            if (type == null) {
                type3 = null;
            } else {
                type3 = Util.canonicalize(type);
            }
            this.ownerType = type3;
            this.rawType = Util.canonicalize(type2);
            this.typeArguments = (Type[]) typeArr.clone();
            int i = 0;
            while (true) {
                Type[] typeArr2 = this.typeArguments;
                if (i >= typeArr2.length) {
                    return;
                }
                if (typeArr2[i] != null) {
                    Util.checkNotPrimitive(typeArr2[i]);
                    Type[] typeArr3 = this.typeArguments;
                    typeArr3[i] = Util.canonicalize(typeArr3[i]);
                    i++;
                } else {
                    throw new NullPointerException();
                }
            }
        }

        public Type[] getActualTypeArguments() {
            return (Type[]) this.typeArguments.clone();
        }

        public Type getRawType() {
            return this.rawType;
        }

        @Nullable
        public Type getOwnerType() {
            return this.ownerType;
        }

        public boolean equals(Object obj) {
            return (obj instanceof ParameterizedType) && Types.equals(this, (ParameterizedType) obj);
        }

        public int hashCode() {
            return (Arrays.hashCode(this.typeArguments) ^ this.rawType.hashCode()) ^ Util.hashCodeOrZero(this.ownerType);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder((this.typeArguments.length + 1) * 30);
            sb.append(Util.typeToString(this.rawType));
            if (this.typeArguments.length == 0) {
                return sb.toString();
            }
            sb.append("<");
            sb.append(Util.typeToString(this.typeArguments[0]));
            for (int i = 1; i < this.typeArguments.length; i++) {
                sb.append(", ");
                sb.append(Util.typeToString(this.typeArguments[i]));
            }
            sb.append(">");
            return sb.toString();
        }
    }

    public static final class WildcardTypeImpl implements WildcardType {
        @Nullable
        private final Type lowerBound;
        private final Type upperBound;

        public WildcardTypeImpl(Type[] typeArr, Type[] typeArr2) {
            if (typeArr2.length > 1) {
                throw new IllegalArgumentException();
            } else if (typeArr.length != 1) {
                throw new IllegalArgumentException();
            } else if (typeArr2.length == 1) {
                if (typeArr2[0] != null) {
                    Util.checkNotPrimitive(typeArr2[0]);
                    if (typeArr[0] == Object.class) {
                        this.lowerBound = Util.canonicalize(typeArr2[0]);
                        this.upperBound = Object.class;
                        return;
                    }
                    throw new IllegalArgumentException();
                }
                throw new NullPointerException();
            } else if (typeArr[0] != null) {
                Util.checkNotPrimitive(typeArr[0]);
                this.lowerBound = null;
                this.upperBound = Util.canonicalize(typeArr[0]);
            } else {
                throw new NullPointerException();
            }
        }

        public Type[] getUpperBounds() {
            return new Type[]{this.upperBound};
        }

        public Type[] getLowerBounds() {
            Type type = this.lowerBound;
            if (type == null) {
                return Util.EMPTY_TYPE_ARRAY;
            }
            return new Type[]{type};
        }

        public boolean equals(Object obj) {
            return (obj instanceof WildcardType) && Types.equals(this, (WildcardType) obj);
        }

        public int hashCode() {
            Type type = this.lowerBound;
            return (type != null ? type.hashCode() + 31 : 1) ^ (this.upperBound.hashCode() + 31);
        }

        public String toString() {
            if (this.lowerBound != null) {
                StringBuilder sb = new StringBuilder();
                sb.append("? super ");
                sb.append(Util.typeToString(this.lowerBound));
                return sb.toString();
            } else if (this.upperBound == Object.class) {
                return "?";
            } else {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("? extends ");
                sb2.append(Util.typeToString(this.upperBound));
                return sb2.toString();
            }
        }
    }

    private Util() {
    }

    public static boolean typesMatch(Type type, Type type2) {
        return Types.equals(type, type2);
    }

    public static Set<? extends Annotation> jsonAnnotations(AnnotatedElement annotatedElement) {
        return jsonAnnotations(annotatedElement.getAnnotations());
    }

    public static Set<? extends Annotation> jsonAnnotations(Annotation[] annotationArr) {
        LinkedHashSet linkedHashSet = null;
        for (Annotation annotation : annotationArr) {
            if (annotation.annotationType().isAnnotationPresent(JsonQualifier.class)) {
                if (linkedHashSet == null) {
                    linkedHashSet = new LinkedHashSet();
                }
                linkedHashSet.add(annotation);
            }
        }
        return linkedHashSet != null ? Collections.unmodifiableSet(linkedHashSet) : NO_ANNOTATIONS;
    }

    public static boolean isAnnotationPresent(Set<? extends Annotation> set, Class<? extends Annotation> cls) {
        if (set.isEmpty()) {
            return false;
        }
        for (Annotation annotationType : set) {
            if (annotationType.annotationType() == cls) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasNullable(Annotation[] annotationArr) {
        for (Annotation annotationType : annotationArr) {
            if (annotationType.annotationType().getSimpleName().equals("Nullable")) {
                return true;
            }
        }
        return false;
    }

    public static boolean isPlatformType(Class<?> cls) {
        String name = cls.getName();
        return name.startsWith("android.") || name.startsWith("androidx.") || name.startsWith("java.") || name.startsWith("javax.") || name.startsWith("kotlin.") || name.startsWith("scala.");
    }

    public static RuntimeException rethrowCause(InvocationTargetException invocationTargetException) {
        Throwable targetException = invocationTargetException.getTargetException();
        if (targetException instanceof RuntimeException) {
            throw ((RuntimeException) targetException);
        } else if (targetException instanceof Error) {
            throw ((Error) targetException);
        } else {
            throw new RuntimeException(targetException);
        }
    }

    /* JADX WARNING: type inference failed for: r0v11, types: [com.squareup.moshi.internal.Util$GenericArrayTypeImpl] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.reflect.Type canonicalize(java.lang.reflect.Type r3) {
        /*
            boolean r0 = r3 instanceof java.lang.Class
            if (r0 == 0) goto L_0x001b
            java.lang.Class r3 = (java.lang.Class) r3
            boolean r0 = r3.isArray()
            if (r0 == 0) goto L_0x001a
            com.squareup.moshi.internal.Util$GenericArrayTypeImpl r0 = new com.squareup.moshi.internal.Util$GenericArrayTypeImpl
            java.lang.Class r3 = r3.getComponentType()
            java.lang.reflect.Type r3 = canonicalize(r3)
            r0.<init>(r3)
            r3 = r0
        L_0x001a:
            return r3
        L_0x001b:
            boolean r0 = r3 instanceof java.lang.reflect.ParameterizedType
            if (r0 == 0) goto L_0x0038
            boolean r0 = r3 instanceof com.squareup.moshi.internal.Util.ParameterizedTypeImpl
            if (r0 == 0) goto L_0x0024
            return r3
        L_0x0024:
            java.lang.reflect.ParameterizedType r3 = (java.lang.reflect.ParameterizedType) r3
            com.squareup.moshi.internal.Util$ParameterizedTypeImpl r0 = new com.squareup.moshi.internal.Util$ParameterizedTypeImpl
            java.lang.reflect.Type r1 = r3.getOwnerType()
            java.lang.reflect.Type r2 = r3.getRawType()
            java.lang.reflect.Type[] r3 = r3.getActualTypeArguments()
            r0.<init>(r1, r2, r3)
            return r0
        L_0x0038:
            boolean r0 = r3 instanceof java.lang.reflect.GenericArrayType
            if (r0 == 0) goto L_0x004d
            boolean r0 = r3 instanceof com.squareup.moshi.internal.Util.GenericArrayTypeImpl
            if (r0 == 0) goto L_0x0041
            return r3
        L_0x0041:
            java.lang.reflect.GenericArrayType r3 = (java.lang.reflect.GenericArrayType) r3
            com.squareup.moshi.internal.Util$GenericArrayTypeImpl r0 = new com.squareup.moshi.internal.Util$GenericArrayTypeImpl
            java.lang.reflect.Type r3 = r3.getGenericComponentType()
            r0.<init>(r3)
            return r0
        L_0x004d:
            boolean r0 = r3 instanceof java.lang.reflect.WildcardType
            if (r0 == 0) goto L_0x0066
            boolean r0 = r3 instanceof com.squareup.moshi.internal.Util.WildcardTypeImpl
            if (r0 == 0) goto L_0x0056
            return r3
        L_0x0056:
            java.lang.reflect.WildcardType r3 = (java.lang.reflect.WildcardType) r3
            com.squareup.moshi.internal.Util$WildcardTypeImpl r0 = new com.squareup.moshi.internal.Util$WildcardTypeImpl
            java.lang.reflect.Type[] r1 = r3.getUpperBounds()
            java.lang.reflect.Type[] r3 = r3.getLowerBounds()
            r0.<init>(r1, r3)
            return r0
        L_0x0066:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.moshi.internal.Util.canonicalize(java.lang.reflect.Type):java.lang.reflect.Type");
    }

    /* JADX WARNING: type inference failed for: r0v21, types: [java.lang.reflect.GenericArrayType] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.reflect.Type resolve(java.lang.reflect.Type r8, java.lang.Class<?> r9, java.lang.reflect.Type r10) {
        /*
        L_0x0000:
            boolean r0 = r10 instanceof java.lang.reflect.TypeVariable
            if (r0 == 0) goto L_0x000f
            java.lang.reflect.TypeVariable r10 = (java.lang.reflect.TypeVariable) r10
            java.lang.reflect.Type r0 = resolveTypeVariable(r8, r9, r10)
            if (r0 != r10) goto L_0x000d
            return r0
        L_0x000d:
            r10 = r0
            goto L_0x0000
        L_0x000f:
            boolean r0 = r10 instanceof java.lang.Class
            if (r0 == 0) goto L_0x002c
            r0 = r10
            java.lang.Class r0 = (java.lang.Class) r0
            boolean r1 = r0.isArray()
            if (r1 == 0) goto L_0x002c
            java.lang.Class r10 = r0.getComponentType()
            java.lang.reflect.Type r8 = resolve(r8, r9, r10)
            if (r10 != r8) goto L_0x0027
            goto L_0x002b
        L_0x0027:
            java.lang.reflect.GenericArrayType r0 = com.squareup.moshi.Types.arrayOf(r8)
        L_0x002b:
            return r0
        L_0x002c:
            boolean r0 = r10 instanceof java.lang.reflect.GenericArrayType
            if (r0 == 0) goto L_0x0042
            java.lang.reflect.GenericArrayType r10 = (java.lang.reflect.GenericArrayType) r10
            java.lang.reflect.Type r0 = r10.getGenericComponentType()
            java.lang.reflect.Type r8 = resolve(r8, r9, r0)
            if (r0 != r8) goto L_0x003d
            goto L_0x0041
        L_0x003d:
            java.lang.reflect.GenericArrayType r10 = com.squareup.moshi.Types.arrayOf(r8)
        L_0x0041:
            return r10
        L_0x0042:
            boolean r0 = r10 instanceof java.lang.reflect.ParameterizedType
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L_0x0085
            java.lang.reflect.ParameterizedType r10 = (java.lang.reflect.ParameterizedType) r10
            java.lang.reflect.Type r0 = r10.getOwnerType()
            java.lang.reflect.Type r3 = resolve(r8, r9, r0)
            if (r3 == r0) goto L_0x0056
            r0 = 1
            goto L_0x0057
        L_0x0056:
            r0 = 0
        L_0x0057:
            java.lang.reflect.Type[] r4 = r10.getActualTypeArguments()
            int r5 = r4.length
        L_0x005c:
            if (r2 >= r5) goto L_0x0077
            r6 = r4[r2]
            java.lang.reflect.Type r6 = resolve(r8, r9, r6)
            r7 = r4[r2]
            if (r6 == r7) goto L_0x0074
            if (r0 != 0) goto L_0x0072
            java.lang.Object r0 = r4.clone()
            r4 = r0
            java.lang.reflect.Type[] r4 = (java.lang.reflect.Type[]) r4
            r0 = 1
        L_0x0072:
            r4[r2] = r6
        L_0x0074:
            int r2 = r2 + 1
            goto L_0x005c
        L_0x0077:
            if (r0 == 0) goto L_0x0083
            com.squareup.moshi.internal.Util$ParameterizedTypeImpl r8 = new com.squareup.moshi.internal.Util$ParameterizedTypeImpl
            java.lang.reflect.Type r9 = r10.getRawType()
            r8.<init>(r3, r9, r4)
            goto L_0x0084
        L_0x0083:
            r8 = r10
        L_0x0084:
            return r8
        L_0x0085:
            boolean r0 = r10 instanceof java.lang.reflect.WildcardType
            if (r0 == 0) goto L_0x00b7
            java.lang.reflect.WildcardType r10 = (java.lang.reflect.WildcardType) r10
            java.lang.reflect.Type[] r0 = r10.getLowerBounds()
            java.lang.reflect.Type[] r3 = r10.getUpperBounds()
            int r4 = r0.length
            if (r4 != r1) goto L_0x00a5
            r1 = r0[r2]
            java.lang.reflect.Type r8 = resolve(r8, r9, r1)
            r9 = r0[r2]
            if (r8 == r9) goto L_0x00b7
            java.lang.reflect.WildcardType r8 = com.squareup.moshi.Types.supertypeOf(r8)
            return r8
        L_0x00a5:
            int r0 = r3.length
            if (r0 != r1) goto L_0x00b7
            r0 = r3[r2]
            java.lang.reflect.Type r8 = resolve(r8, r9, r0)     // Catch:{ Throwable -> 0x00b8 }
            r9 = r3[r2]
            if (r8 == r9) goto L_0x00b7
            java.lang.reflect.WildcardType r8 = com.squareup.moshi.Types.subtypeOf(r8)
            return r8
        L_0x00b7:
            return r10
        L_0x00b8:
            r8 = move-exception
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.moshi.internal.Util.resolve(java.lang.reflect.Type, java.lang.Class, java.lang.reflect.Type):java.lang.reflect.Type");
    }

    static Type resolveTypeVariable(Type type, Class<?> cls, TypeVariable<?> typeVariable) {
        Class declaringClassOf = declaringClassOf(typeVariable);
        if (declaringClassOf == null) {
            return typeVariable;
        }
        Type genericSupertype = getGenericSupertype(type, cls, declaringClassOf);
        if (!(genericSupertype instanceof ParameterizedType)) {
            return typeVariable;
        }
        return ((ParameterizedType) genericSupertype).getActualTypeArguments()[indexOf(declaringClassOf.getTypeParameters(), typeVariable)];
    }

    public static Type getGenericSupertype(Type type, Class<?> cls, Class<?> cls2) {
        if (cls2 == cls) {
            return type;
        }
        if (cls2.isInterface()) {
            Class<?>[] interfaces = cls.getInterfaces();
            int length = interfaces.length;
            for (int i = 0; i < length; i++) {
                if (interfaces[i] == cls2) {
                    return cls.getGenericInterfaces()[i];
                }
                if (cls2.isAssignableFrom(interfaces[i])) {
                    return getGenericSupertype(cls.getGenericInterfaces()[i], interfaces[i], cls2);
                }
            }
        }
        if (!cls.isInterface()) {
            while (cls != Object.class) {
                Class<?> superclass = cls.getSuperclass();
                if (superclass == cls2) {
                    return cls.getGenericSuperclass();
                }
                if (cls2.isAssignableFrom(superclass)) {
                    return getGenericSupertype(cls.getGenericSuperclass(), superclass, cls2);
                }
                cls = superclass;
            }
        }
        return cls2;
    }

    static int hashCodeOrZero(@Nullable Object obj) {
        if (obj != null) {
            return obj.hashCode();
        }
        return 0;
    }

    static String typeToString(Type type) {
        return type instanceof Class ? ((Class) type).getName() : type.toString();
    }

    static int indexOf(Object[] objArr, Object obj) {
        for (int i = 0; i < objArr.length; i++) {
            if (obj.equals(objArr[i])) {
                return i;
            }
        }
        throw new NoSuchElementException();
    }

    @Nullable
    static Class<?> declaringClassOf(TypeVariable<?> typeVariable) {
        GenericDeclaration genericDeclaration = typeVariable.getGenericDeclaration();
        if (genericDeclaration instanceof Class) {
            return (Class) genericDeclaration;
        }
        return null;
    }

    static void checkNotPrimitive(Type type) {
        if ((type instanceof Class) && ((Class) type).isPrimitive()) {
            StringBuilder sb = new StringBuilder();
            sb.append("Unexpected primitive ");
            sb.append(type);
            sb.append(". Use the boxed type.");
            throw new IllegalArgumentException(sb.toString());
        }
    }

    public static String typeAnnotatedWithAnnotations(Type type, Set<? extends Annotation> set) {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append(type);
        if (set.isEmpty()) {
            str = " (with no annotations)";
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(" annotated ");
            sb2.append(set);
            str = sb2.toString();
        }
        sb.append(str);
        return sb.toString();
    }

    @Nullable
    public static JsonAdapter<?> generatedAdapter(Moshi moshi, Type type, Class<?> cls) {
        JsonClass jsonClass = (JsonClass) cls.getAnnotation(JsonClass.class);
        if (jsonClass == null || !jsonClass.generateAdapter()) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(cls.getName().replace("$", "_"));
        sb.append("JsonAdapter");
        try {
            Class cls2 = Class.forName(sb.toString(), true, cls.getClassLoader());
            if (type instanceof ParameterizedType) {
                Constructor declaredConstructor = cls2.getDeclaredConstructor(new Class[]{Moshi.class, Type[].class});
                declaredConstructor.setAccessible(true);
                return ((JsonAdapter) declaredConstructor.newInstance(new Object[]{moshi, ((ParameterizedType) type).getActualTypeArguments()})).nullSafe();
            }
            Constructor declaredConstructor2 = cls2.getDeclaredConstructor(new Class[]{Moshi.class});
            declaredConstructor2.setAccessible(true);
            return ((JsonAdapter) declaredConstructor2.newInstance(new Object[]{moshi})).nullSafe();
        } catch (ClassNotFoundException e) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Failed to find the generated JsonAdapter class for ");
            sb2.append(cls);
            throw new RuntimeException(sb2.toString(), e);
        } catch (NoSuchMethodException e2) {
            StringBuilder sb3 = new StringBuilder();
            sb3.append("Failed to find the generated JsonAdapter constructor for ");
            sb3.append(cls);
            throw new RuntimeException(sb3.toString(), e2);
        } catch (IllegalAccessException e3) {
            StringBuilder sb4 = new StringBuilder();
            sb4.append("Failed to access the generated JsonAdapter for ");
            sb4.append(cls);
            throw new RuntimeException(sb4.toString(), e3);
        } catch (InstantiationException e4) {
            StringBuilder sb5 = new StringBuilder();
            sb5.append("Failed to instantiate the generated JsonAdapter for ");
            sb5.append(cls);
            throw new RuntimeException(sb5.toString(), e4);
        } catch (InvocationTargetException e5) {
            throw rethrowCause(e5);
        }
    }
}
