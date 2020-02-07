package kotlin.reflect.jvm.internal;

import com.google.firebase.analytics.FirebaseAnalytics.Param;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 2}, mo37404d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010 \n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u001f\b \u0018\u0000 /*\f\b\u0000\u0010\u0001 \u0001*\u0004\u0018\u00010\u00022\u00020\u0003:\u001b#$%&'()*+,-./0123456789:;<=B1\u0012\u0006\u0010\u0004\u001a\u00028\u0000\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0002\b\u0003\u0018\u00010\b\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\n¢\u0006\u0002\u0010\u000bJ\u001b\u0010\u001b\u001a\u0004\u0018\u00010\u00032\n\u0010\u001c\u001a\u0006\u0012\u0002\b\u00030\nH&¢\u0006\u0002\u0010\u001dJ\u0019\u0010\u001e\u001a\u00020\u001f2\n\u0010\u001c\u001a\u0006\u0012\u0002\b\u00030\nH\u0014¢\u0006\u0002\u0010 J\u0012\u0010!\u001a\u00020\u001f2\b\u0010\"\u001a\u0004\u0018\u00010\u0003H\u0004R\u0011\u0010\f\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0007\u001a\b\u0012\u0002\b\u0003\u0018\u00010\bX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0016\u0010\u0004\u001a\u00028\u0000X\u0004¢\u0006\n\n\u0002\u0010\u0014\u001a\u0004\b\u0012\u0010\u0013R\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00060\u0016¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001a¨\u0006>"}, mo37405d2 = {"Lkotlin/reflect/jvm/internal/FunctionCaller;", "M", "Ljava/lang/reflect/Member;", "", "member", "returnType", "Ljava/lang/reflect/Type;", "instanceClass", "Ljava/lang/Class;", "valueParameterTypes", "", "(Ljava/lang/reflect/Member;Ljava/lang/reflect/Type;Ljava/lang/Class;[Ljava/lang/reflect/Type;)V", "arity", "", "getArity", "()I", "getInstanceClass$kotlin_reflect_api", "()Ljava/lang/Class;", "getMember$kotlin_reflect_api", "()Ljava/lang/reflect/Member;", "Ljava/lang/reflect/Member;", "parameterTypes", "", "getParameterTypes", "()Ljava/util/List;", "getReturnType$kotlin_reflect_api", "()Ljava/lang/reflect/Type;", "call", "args", "([Ljava/lang/Object;)Ljava/lang/Object;", "checkArguments", "", "([Ljava/lang/Object;)V", "checkObjectInstance", "obj", "BoundClassCompanionFieldGetter", "BoundClassCompanionFieldSetter", "BoundConstructor", "BoundInstanceFieldGetter", "BoundInstanceFieldSetter", "BoundInstanceMethod", "BoundJvmStaticInObject", "BoundJvmStaticInObjectFieldGetter", "BoundJvmStaticInObjectFieldSetter", "BoundStaticMethod", "ClassCompanionFieldGetter", "ClassCompanionFieldSetter", "Companion", "Constructor", "FieldGetter", "FieldSetter", "InstanceFieldGetter", "InstanceFieldSetter", "InstanceMethod", "JvmStaticInObject", "JvmStaticInObjectFieldGetter", "JvmStaticInObjectFieldSetter", "Method", "StaticFieldGetter", "StaticFieldSetter", "StaticMethod", "ThrowingCaller", "kotlin-reflect-api"}, mo37406k = 1, mo37407mv = {1, 1, 11})
/* compiled from: FunctionCaller.kt */
public abstract class FunctionCaller<M extends Member> {
    public static final Companion Companion = new Companion(null);
    @Nullable
    private final Class<?> instanceClass;
    private final M member;
    @NotNull
    private final List<Type> parameterTypes;
    @NotNull
    private final Type returnType;

    @Metadata(mo37403bv = {1, 0, 2}, mo37404d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u001b\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\u0010\b\u001a\u0006\u0012\u0002\b\u00030\tH\u0016¢\u0006\u0002\u0010\nR\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, mo37405d2 = {"Lkotlin/reflect/jvm/internal/FunctionCaller$BoundClassCompanionFieldGetter;", "Lkotlin/reflect/jvm/internal/FunctionCaller$FieldGetter;", "field", "Ljava/lang/reflect/Field;", "boundReceiver", "", "(Ljava/lang/reflect/Field;Ljava/lang/Object;)V", "call", "args", "", "([Ljava/lang/Object;)Ljava/lang/Object;", "kotlin-reflect-api"}, mo37406k = 1, mo37407mv = {1, 1, 11})
    /* compiled from: FunctionCaller.kt */
    public static final class BoundClassCompanionFieldGetter extends FieldGetter {
        private final Object boundReceiver;

        public BoundClassCompanionFieldGetter(@NotNull Field field, @Nullable Object obj) {
            Intrinsics.checkParameterIsNotNull(field, "field");
            super(field, false);
            this.boundReceiver = obj;
        }

        @Nullable
        public Object call(@NotNull Object[] objArr) {
            Intrinsics.checkParameterIsNotNull(objArr, "args");
            checkArguments(objArr);
            return ((Field) getMember$kotlin_reflect_api()).get(this.boundReceiver);
        }
    }

    @Metadata(mo37403bv = {1, 0, 2}, mo37404d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0019\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005¢\u0006\u0002\u0010\u0006J\u001b\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\u0010\t\u001a\u0006\u0012\u0002\b\u00030\nH\u0016¢\u0006\u0002\u0010\u000b¨\u0006\f"}, mo37405d2 = {"Lkotlin/reflect/jvm/internal/FunctionCaller$BoundClassCompanionFieldSetter;", "Lkotlin/reflect/jvm/internal/FunctionCaller;", "Ljava/lang/reflect/Field;", "field", "klass", "Ljava/lang/Class;", "(Ljava/lang/reflect/Field;Ljava/lang/Class;)V", "call", "", "args", "", "([Ljava/lang/Object;)Ljava/lang/Object;", "kotlin-reflect-api"}, mo37406k = 1, mo37407mv = {1, 1, 11})
    /* compiled from: FunctionCaller.kt */
    public static final class BoundClassCompanionFieldSetter extends FunctionCaller<Field> {
        public BoundClassCompanionFieldSetter(@NotNull Field field, @NotNull Class<?> cls) {
            Intrinsics.checkParameterIsNotNull(field, "field");
            Intrinsics.checkParameterIsNotNull(cls, "klass");
            Member member = field;
            Class cls2 = Void.TYPE;
            Intrinsics.checkExpressionValueIsNotNull(cls2, "Void.TYPE");
            Type type = cls2;
            Type genericType = field.getGenericType();
            Intrinsics.checkExpressionValueIsNotNull(genericType, "field.genericType");
            super(member, type, cls, new Type[]{genericType});
        }

        @Nullable
        public Object call(@NotNull Object[] objArr) {
            Intrinsics.checkParameterIsNotNull(objArr, "args");
            checkArguments(objArr);
            ((Field) getMember$kotlin_reflect_api()).set(null, ArraysKt.last(objArr));
            return Unit.INSTANCE;
        }
    }

    @Metadata(mo37403bv = {1, 0, 2}, mo37404d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0002\u0018\u00002\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\u0001B\u001b\u0012\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0002\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u001b\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\u0010\b\u001a\u0006\u0012\u0002\b\u00030\tH\u0016¢\u0006\u0002\u0010\nR\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, mo37405d2 = {"Lkotlin/reflect/jvm/internal/FunctionCaller$BoundConstructor;", "Lkotlin/reflect/jvm/internal/FunctionCaller;", "Ljava/lang/reflect/Constructor;", "constructor", "boundReceiver", "", "(Ljava/lang/reflect/Constructor;Ljava/lang/Object;)V", "call", "args", "", "([Ljava/lang/Object;)Ljava/lang/Object;", "kotlin-reflect-api"}, mo37406k = 1, mo37407mv = {1, 1, 11})
    /* compiled from: FunctionCaller.kt */
    public static final class BoundConstructor extends FunctionCaller<java.lang.reflect.Constructor<?>> {
        private final Object boundReceiver;

        public BoundConstructor(@NotNull java.lang.reflect.Constructor<?> constructor, @Nullable Object obj) {
            Intrinsics.checkParameterIsNotNull(constructor, "constructor");
            Member member = constructor;
            Class declaringClass = constructor.getDeclaringClass();
            Intrinsics.checkExpressionValueIsNotNull(declaringClass, "constructor.declaringClass");
            Type type = declaringClass;
            Type[] genericParameterTypes = constructor.getGenericParameterTypes();
            Intrinsics.checkExpressionValueIsNotNull(genericParameterTypes, "constructor.genericParameterTypes");
            super(member, type, null, genericParameterTypes);
            this.boundReceiver = obj;
        }

        @Nullable
        public Object call(@NotNull Object[] objArr) {
            Intrinsics.checkParameterIsNotNull(objArr, "args");
            checkArguments(objArr);
            java.lang.reflect.Constructor constructor = (java.lang.reflect.Constructor) getMember$kotlin_reflect_api();
            Object[] argsWithReceiver = FunctionCaller.Companion.argsWithReceiver(this.boundReceiver, objArr);
            return constructor.newInstance(Arrays.copyOf(argsWithReceiver, argsWithReceiver.length));
        }
    }

    @Metadata(mo37403bv = {1, 0, 2}, mo37404d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u001b\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\u0010\b\u001a\u0006\u0012\u0002\b\u00030\tH\u0016¢\u0006\u0002\u0010\nR\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, mo37405d2 = {"Lkotlin/reflect/jvm/internal/FunctionCaller$BoundInstanceFieldGetter;", "Lkotlin/reflect/jvm/internal/FunctionCaller$FieldGetter;", "field", "Ljava/lang/reflect/Field;", "boundReceiver", "", "(Ljava/lang/reflect/Field;Ljava/lang/Object;)V", "call", "args", "", "([Ljava/lang/Object;)Ljava/lang/Object;", "kotlin-reflect-api"}, mo37406k = 1, mo37407mv = {1, 1, 11})
    /* compiled from: FunctionCaller.kt */
    public static final class BoundInstanceFieldGetter extends FieldGetter {
        private final Object boundReceiver;

        public BoundInstanceFieldGetter(@NotNull Field field, @Nullable Object obj) {
            Intrinsics.checkParameterIsNotNull(field, "field");
            super(field, false);
            this.boundReceiver = obj;
        }

        @Nullable
        public Object call(@NotNull Object[] objArr) {
            Intrinsics.checkParameterIsNotNull(objArr, "args");
            checkArguments(objArr);
            return ((Field) getMember$kotlin_reflect_api()).get(this.boundReceiver);
        }
    }

    @Metadata(mo37403bv = {1, 0, 2}, mo37404d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\u001b\u0010\t\u001a\u0004\u0018\u00010\u00072\n\u0010\n\u001a\u0006\u0012\u0002\b\u00030\u000bH\u0016¢\u0006\u0002\u0010\fR\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, mo37405d2 = {"Lkotlin/reflect/jvm/internal/FunctionCaller$BoundInstanceFieldSetter;", "Lkotlin/reflect/jvm/internal/FunctionCaller$FieldSetter;", "field", "Ljava/lang/reflect/Field;", "notNull", "", "boundReceiver", "", "(Ljava/lang/reflect/Field;ZLjava/lang/Object;)V", "call", "args", "", "([Ljava/lang/Object;)Ljava/lang/Object;", "kotlin-reflect-api"}, mo37406k = 1, mo37407mv = {1, 1, 11})
    /* compiled from: FunctionCaller.kt */
    public static final class BoundInstanceFieldSetter extends FieldSetter {
        private final Object boundReceiver;

        public BoundInstanceFieldSetter(@NotNull Field field, boolean z, @Nullable Object obj) {
            Intrinsics.checkParameterIsNotNull(field, "field");
            super(field, z, false);
            this.boundReceiver = obj;
        }

        @Nullable
        public Object call(@NotNull Object[] objArr) {
            Intrinsics.checkParameterIsNotNull(objArr, "args");
            checkArguments(objArr);
            ((Field) getMember$kotlin_reflect_api()).set(this.boundReceiver, ArraysKt.first(objArr));
            return Unit.INSTANCE;
        }
    }

    @Metadata(mo37403bv = {1, 0, 2}, mo37404d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u001b\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\u0010\b\u001a\u0006\u0012\u0002\b\u00030\tH\u0016¢\u0006\u0002\u0010\nR\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, mo37405d2 = {"Lkotlin/reflect/jvm/internal/FunctionCaller$BoundInstanceMethod;", "Lkotlin/reflect/jvm/internal/FunctionCaller$Method;", "method", "Ljava/lang/reflect/Method;", "boundReceiver", "", "(Ljava/lang/reflect/Method;Ljava/lang/Object;)V", "call", "args", "", "([Ljava/lang/Object;)Ljava/lang/Object;", "kotlin-reflect-api"}, mo37406k = 1, mo37407mv = {1, 1, 11})
    /* compiled from: FunctionCaller.kt */
    public static final class BoundInstanceMethod extends Method {
        private final Object boundReceiver;

        public BoundInstanceMethod(@NotNull java.lang.reflect.Method method, @Nullable Object obj) {
            Intrinsics.checkParameterIsNotNull(method, Param.METHOD);
            super(method, false, null, 4, null);
            this.boundReceiver = obj;
        }

        @Nullable
        public Object call(@NotNull Object[] objArr) {
            Intrinsics.checkParameterIsNotNull(objArr, "args");
            checkArguments(objArr);
            return callMethod(this.boundReceiver, objArr);
        }
    }

    @Metadata(mo37403bv = {1, 0, 2}, mo37404d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\bH\u0016¢\u0006\u0002\u0010\t¨\u0006\n"}, mo37405d2 = {"Lkotlin/reflect/jvm/internal/FunctionCaller$BoundJvmStaticInObject;", "Lkotlin/reflect/jvm/internal/FunctionCaller$Method;", "method", "Ljava/lang/reflect/Method;", "(Ljava/lang/reflect/Method;)V", "call", "", "args", "", "([Ljava/lang/Object;)Ljava/lang/Object;", "kotlin-reflect-api"}, mo37406k = 1, mo37407mv = {1, 1, 11})
    /* compiled from: FunctionCaller.kt */
    public static final class BoundJvmStaticInObject extends Method {
        public BoundJvmStaticInObject(@NotNull java.lang.reflect.Method method) {
            Intrinsics.checkParameterIsNotNull(method, Param.METHOD);
            super(method, false, null, 4, null);
        }

        @Nullable
        public Object call(@NotNull Object[] objArr) {
            Intrinsics.checkParameterIsNotNull(objArr, "args");
            checkArguments(objArr);
            return callMethod(null, objArr);
        }
    }

    @Metadata(mo37403bv = {1, 0, 2}, mo37404d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, mo37405d2 = {"Lkotlin/reflect/jvm/internal/FunctionCaller$BoundJvmStaticInObjectFieldGetter;", "Lkotlin/reflect/jvm/internal/FunctionCaller$FieldGetter;", "field", "Ljava/lang/reflect/Field;", "(Ljava/lang/reflect/Field;)V", "kotlin-reflect-api"}, mo37406k = 1, mo37407mv = {1, 1, 11})
    /* compiled from: FunctionCaller.kt */
    public static final class BoundJvmStaticInObjectFieldGetter extends FieldGetter {
        public BoundJvmStaticInObjectFieldGetter(@NotNull Field field) {
            Intrinsics.checkParameterIsNotNull(field, "field");
            super(field, false);
        }
    }

    @Metadata(mo37403bv = {1, 0, 2}, mo37404d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u001b\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\u0010\t\u001a\u0006\u0012\u0002\b\u00030\nH\u0016¢\u0006\u0002\u0010\u000b¨\u0006\f"}, mo37405d2 = {"Lkotlin/reflect/jvm/internal/FunctionCaller$BoundJvmStaticInObjectFieldSetter;", "Lkotlin/reflect/jvm/internal/FunctionCaller$FieldSetter;", "field", "Ljava/lang/reflect/Field;", "notNull", "", "(Ljava/lang/reflect/Field;Z)V", "call", "", "args", "", "([Ljava/lang/Object;)Ljava/lang/Object;", "kotlin-reflect-api"}, mo37406k = 1, mo37407mv = {1, 1, 11})
    /* compiled from: FunctionCaller.kt */
    public static final class BoundJvmStaticInObjectFieldSetter extends FieldSetter {
        public BoundJvmStaticInObjectFieldSetter(@NotNull Field field, boolean z) {
            Intrinsics.checkParameterIsNotNull(field, "field");
            super(field, z, false);
        }

        @Nullable
        public Object call(@NotNull Object[] objArr) {
            Intrinsics.checkParameterIsNotNull(objArr, "args");
            checkArguments(objArr);
            ((Field) getMember$kotlin_reflect_api()).set(null, ArraysKt.last(objArr));
            return Unit.INSTANCE;
        }
    }

    @Metadata(mo37403bv = {1, 0, 2}, mo37404d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u001b\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\u0010\b\u001a\u0006\u0012\u0002\b\u00030\tH\u0016¢\u0006\u0002\u0010\nR\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, mo37405d2 = {"Lkotlin/reflect/jvm/internal/FunctionCaller$BoundStaticMethod;", "Lkotlin/reflect/jvm/internal/FunctionCaller$Method;", "method", "Ljava/lang/reflect/Method;", "boundReceiver", "", "(Ljava/lang/reflect/Method;Ljava/lang/Object;)V", "call", "args", "", "([Ljava/lang/Object;)Ljava/lang/Object;", "kotlin-reflect-api"}, mo37406k = 1, mo37407mv = {1, 1, 11})
    /* compiled from: FunctionCaller.kt */
    public static final class BoundStaticMethod extends Method {
        private final Object boundReceiver;

        public BoundStaticMethod(@NotNull java.lang.reflect.Method method, @Nullable Object obj) {
            Object obj2;
            Intrinsics.checkParameterIsNotNull(method, Param.METHOD);
            Companion companion = FunctionCaller.Companion;
            Type[] genericParameterTypes = method.getGenericParameterTypes();
            Intrinsics.checkExpressionValueIsNotNull(genericParameterTypes, "method.genericParameterTypes");
            if (genericParameterTypes.length <= 1) {
                obj2 = new Type[0];
            } else {
                obj2 = Arrays.copyOfRange(genericParameterTypes, 1, genericParameterTypes.length);
                Intrinsics.checkExpressionValueIsNotNull(obj2, "java.util.Arrays.copyOfR…this, fromIndex, toIndex)");
                if (obj2 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
                }
            }
            super(method, false, (Type[]) obj2);
            this.boundReceiver = obj;
        }

        @Nullable
        public Object call(@NotNull Object[] objArr) {
            Intrinsics.checkParameterIsNotNull(objArr, "args");
            checkArguments(objArr);
            return callMethod(null, FunctionCaller.Companion.argsWithReceiver(this.boundReceiver, objArr));
        }
    }

    @Metadata(mo37403bv = {1, 0, 2}, mo37404d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0019\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005¢\u0006\u0002\u0010\u0006J\u001b\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\u0010\t\u001a\u0006\u0012\u0002\b\u00030\nH\u0016¢\u0006\u0002\u0010\u000b¨\u0006\f"}, mo37405d2 = {"Lkotlin/reflect/jvm/internal/FunctionCaller$ClassCompanionFieldGetter;", "Lkotlin/reflect/jvm/internal/FunctionCaller;", "Ljava/lang/reflect/Field;", "field", "klass", "Ljava/lang/Class;", "(Ljava/lang/reflect/Field;Ljava/lang/Class;)V", "call", "", "args", "", "([Ljava/lang/Object;)Ljava/lang/Object;", "kotlin-reflect-api"}, mo37406k = 1, mo37407mv = {1, 1, 11})
    /* compiled from: FunctionCaller.kt */
    public static final class ClassCompanionFieldGetter extends FunctionCaller<Field> {
        public ClassCompanionFieldGetter(@NotNull Field field, @NotNull Class<?> cls) {
            Intrinsics.checkParameterIsNotNull(field, "field");
            Intrinsics.checkParameterIsNotNull(cls, "klass");
            Member member = field;
            Type genericType = field.getGenericType();
            Intrinsics.checkExpressionValueIsNotNull(genericType, "field.genericType");
            super(member, genericType, cls, new Type[0]);
        }

        @Nullable
        public Object call(@NotNull Object[] objArr) {
            Intrinsics.checkParameterIsNotNull(objArr, "args");
            checkArguments(objArr);
            return ((Field) getMember$kotlin_reflect_api()).get(ArraysKt.first(objArr));
        }
    }

    @Metadata(mo37403bv = {1, 0, 2}, mo37404d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0019\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005¢\u0006\u0002\u0010\u0006J\u001b\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\u0010\t\u001a\u0006\u0012\u0002\b\u00030\nH\u0016¢\u0006\u0002\u0010\u000b¨\u0006\f"}, mo37405d2 = {"Lkotlin/reflect/jvm/internal/FunctionCaller$ClassCompanionFieldSetter;", "Lkotlin/reflect/jvm/internal/FunctionCaller;", "Ljava/lang/reflect/Field;", "field", "klass", "Ljava/lang/Class;", "(Ljava/lang/reflect/Field;Ljava/lang/Class;)V", "call", "", "args", "", "([Ljava/lang/Object;)Ljava/lang/Object;", "kotlin-reflect-api"}, mo37406k = 1, mo37407mv = {1, 1, 11})
    /* compiled from: FunctionCaller.kt */
    public static final class ClassCompanionFieldSetter extends FunctionCaller<Field> {
        public ClassCompanionFieldSetter(@NotNull Field field, @NotNull Class<?> cls) {
            Intrinsics.checkParameterIsNotNull(field, "field");
            Intrinsics.checkParameterIsNotNull(cls, "klass");
            Member member = field;
            Class cls2 = Void.TYPE;
            Intrinsics.checkExpressionValueIsNotNull(cls2, "Void.TYPE");
            Type type = cls2;
            Type genericType = field.getGenericType();
            Intrinsics.checkExpressionValueIsNotNull(genericType, "field.genericType");
            super(member, type, cls, new Type[]{genericType});
        }

        @Nullable
        public Object call(@NotNull Object[] objArr) {
            Intrinsics.checkParameterIsNotNull(objArr, "args");
            checkArguments(objArr);
            ((Field) getMember$kotlin_reflect_api()).set(null, ArraysKt.last(objArr));
            return Unit.INSTANCE;
        }
    }

    @Metadata(mo37403bv = {1, 0, 2}, mo37404d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\b\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J1\u0010\u0003\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00012\u0010\u0010\u0006\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u0004¢\u0006\u0002\u0010\u0007J(\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\t0\u0004\"\u0006\b\u0001\u0010\t\u0018\u0001*\n\u0012\u0006\b\u0001\u0012\u0002H\t0\u0004H\b¢\u0006\u0002\u0010\nJ\u001b\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0004*\u0006\u0012\u0002\b\u00030\u0004¢\u0006\u0002\u0010\n¨\u0006\f"}, mo37405d2 = {"Lkotlin/reflect/jvm/internal/FunctionCaller$Companion;", "", "()V", "argsWithReceiver", "", "receiver", "args", "(Ljava/lang/Object;[Ljava/lang/Object;)[Ljava/lang/Object;", "dropFirst", "T", "([Ljava/lang/Object;)[Ljava/lang/Object;", "dropFirstArg", "kotlin-reflect-api"}, mo37406k = 1, mo37407mv = {1, 1, 11})
    /* compiled from: FunctionCaller.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final Object[] argsWithReceiver(@Nullable Object obj, @NotNull Object[] objArr) {
            Intrinsics.checkParameterIsNotNull(objArr, "args");
            Object[] objArr2 = new Object[(objArr.length + 1)];
            objArr2[0] = obj;
            System.arraycopy(objArr, 0, objArr2, 1, objArr.length);
            return objArr2;
        }

        @NotNull
        public final Object[] dropFirstArg(@NotNull Object[] objArr) {
            Intrinsics.checkParameterIsNotNull(objArr, "$receiver");
            Companion companion = this;
            if (objArr.length <= 1) {
                return new Object[0];
            }
            Object[] copyOfRange = Arrays.copyOfRange(objArr, 1, objArr.length);
            Intrinsics.checkExpressionValueIsNotNull(copyOfRange, "java.util.Arrays.copyOfR…this, fromIndex, toIndex)");
            if (copyOfRange != null) {
                return copyOfRange;
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
        }
    }

    @Metadata(mo37403bv = {1, 0, 2}, mo37404d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\u0018\u00002\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\u0001B\u0011\u0012\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0002¢\u0006\u0002\u0010\u0004J\u001b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\bH\u0016¢\u0006\u0002\u0010\t¨\u0006\n"}, mo37405d2 = {"Lkotlin/reflect/jvm/internal/FunctionCaller$Constructor;", "Lkotlin/reflect/jvm/internal/FunctionCaller;", "Ljava/lang/reflect/Constructor;", "constructor", "(Ljava/lang/reflect/Constructor;)V", "call", "", "args", "", "([Ljava/lang/Object;)Ljava/lang/Object;", "kotlin-reflect-api"}, mo37406k = 1, mo37407mv = {1, 1, 11})
    /* compiled from: FunctionCaller.kt */
    public static final class Constructor extends FunctionCaller<java.lang.reflect.Constructor<?>> {
        public Constructor(@NotNull java.lang.reflect.Constructor<?> constructor) {
            Intrinsics.checkParameterIsNotNull(constructor, "constructor");
            Member member = constructor;
            Class declaringClass = constructor.getDeclaringClass();
            Intrinsics.checkExpressionValueIsNotNull(declaringClass, "constructor.declaringClass");
            Type type = declaringClass;
            Class declaringClass2 = constructor.getDeclaringClass();
            Intrinsics.checkExpressionValueIsNotNull(declaringClass2, "klass");
            Class declaringClass3 = declaringClass2.getDeclaringClass();
            if (declaringClass3 == null || Modifier.isStatic(declaringClass2.getModifiers())) {
                declaringClass3 = null;
            }
            Type[] genericParameterTypes = constructor.getGenericParameterTypes();
            Intrinsics.checkExpressionValueIsNotNull(genericParameterTypes, "constructor.genericParameterTypes");
            super(member, type, declaringClass3, genericParameterTypes);
        }

        @Nullable
        public Object call(@NotNull Object[] objArr) {
            Intrinsics.checkParameterIsNotNull(objArr, "args");
            checkArguments(objArr);
            return ((java.lang.reflect.Constructor) getMember$kotlin_reflect_api()).newInstance(Arrays.copyOf(objArr, objArr.length));
        }
    }

    @Metadata(mo37403bv = {1, 0, 2}, mo37404d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\b&\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u001b\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\u0010\t\u001a\u0006\u0012\u0002\b\u00030\nH\u0016¢\u0006\u0002\u0010\u000b¨\u0006\f"}, mo37405d2 = {"Lkotlin/reflect/jvm/internal/FunctionCaller$FieldGetter;", "Lkotlin/reflect/jvm/internal/FunctionCaller;", "Ljava/lang/reflect/Field;", "field", "requiresInstance", "", "(Ljava/lang/reflect/Field;Z)V", "call", "", "args", "", "([Ljava/lang/Object;)Ljava/lang/Object;", "kotlin-reflect-api"}, mo37406k = 1, mo37407mv = {1, 1, 11})
    /* compiled from: FunctionCaller.kt */
    public static abstract class FieldGetter extends FunctionCaller<Field> {
        public /* synthetic */ FieldGetter(Field field, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
            if ((i & 2) != 0) {
                z = !Modifier.isStatic(field.getModifiers());
            }
            this(field, z);
        }

        public FieldGetter(@NotNull Field field, boolean z) {
            Class cls;
            Intrinsics.checkParameterIsNotNull(field, "field");
            Member member = field;
            Type genericType = field.getGenericType();
            Intrinsics.checkExpressionValueIsNotNull(genericType, "field.genericType");
            if (z) {
                cls = field.getDeclaringClass();
            } else {
                cls = null;
            }
            super(member, genericType, cls, new Type[0]);
        }

        @Nullable
        public Object call(@NotNull Object[] objArr) {
            Intrinsics.checkParameterIsNotNull(objArr, "args");
            checkArguments(objArr);
            return ((Field) getMember$kotlin_reflect_api()).get(getInstanceClass$kotlin_reflect_api() != null ? ArraysKt.first(objArr) : null);
        }
    }

    @Metadata(mo37403bv = {1, 0, 2}, mo37404d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\b&\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J\u001b\u0010\b\u001a\u0004\u0018\u00010\t2\n\u0010\n\u001a\u0006\u0012\u0002\b\u00030\u000bH\u0016¢\u0006\u0002\u0010\fJ\u0019\u0010\r\u001a\u00020\u000e2\n\u0010\n\u001a\u0006\u0012\u0002\b\u00030\u000bH\u0014¢\u0006\u0002\u0010\u000fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, mo37405d2 = {"Lkotlin/reflect/jvm/internal/FunctionCaller$FieldSetter;", "Lkotlin/reflect/jvm/internal/FunctionCaller;", "Ljava/lang/reflect/Field;", "field", "notNull", "", "requiresInstance", "(Ljava/lang/reflect/Field;ZZ)V", "call", "", "args", "", "([Ljava/lang/Object;)Ljava/lang/Object;", "checkArguments", "", "([Ljava/lang/Object;)V", "kotlin-reflect-api"}, mo37406k = 1, mo37407mv = {1, 1, 11})
    /* compiled from: FunctionCaller.kt */
    public static abstract class FieldSetter extends FunctionCaller<Field> {
        private final boolean notNull;

        public /* synthetic */ FieldSetter(Field field, boolean z, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            if ((i & 4) != 0) {
                z2 = !Modifier.isStatic(field.getModifiers());
            }
            this(field, z, z2);
        }

        public FieldSetter(@NotNull Field field, boolean z, boolean z2) {
            Intrinsics.checkParameterIsNotNull(field, "field");
            Member member = field;
            Class cls = Void.TYPE;
            Intrinsics.checkExpressionValueIsNotNull(cls, "Void.TYPE");
            Type type = cls;
            Class declaringClass = z2 ? field.getDeclaringClass() : null;
            Type genericType = field.getGenericType();
            Intrinsics.checkExpressionValueIsNotNull(genericType, "field.genericType");
            super(member, type, declaringClass, new Type[]{genericType});
            this.notNull = z;
        }

        /* access modifiers changed from: protected */
        public void checkArguments(@NotNull Object[] objArr) {
            Intrinsics.checkParameterIsNotNull(objArr, "args");
            FunctionCaller.super.checkArguments(objArr);
            if (this.notNull && ArraysKt.last(objArr) == null) {
                throw new IllegalArgumentException("null is not allowed as a value for this property.");
            }
        }

        @Nullable
        public Object call(@NotNull Object[] objArr) {
            Intrinsics.checkParameterIsNotNull(objArr, "args");
            checkArguments(objArr);
            ((Field) getMember$kotlin_reflect_api()).set(getInstanceClass$kotlin_reflect_api() != null ? ArraysKt.first(objArr) : null, ArraysKt.last(objArr));
            return Unit.INSTANCE;
        }
    }

    @Metadata(mo37403bv = {1, 0, 2}, mo37404d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, mo37405d2 = {"Lkotlin/reflect/jvm/internal/FunctionCaller$InstanceFieldGetter;", "Lkotlin/reflect/jvm/internal/FunctionCaller$FieldGetter;", "field", "Ljava/lang/reflect/Field;", "(Ljava/lang/reflect/Field;)V", "kotlin-reflect-api"}, mo37406k = 1, mo37407mv = {1, 1, 11})
    /* compiled from: FunctionCaller.kt */
    public static final class InstanceFieldGetter extends FieldGetter {
        public InstanceFieldGetter(@NotNull Field field) {
            Intrinsics.checkParameterIsNotNull(field, "field");
            super(field, false, 2, null);
        }
    }

    @Metadata(mo37403bv = {1, 0, 2}, mo37404d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, mo37405d2 = {"Lkotlin/reflect/jvm/internal/FunctionCaller$InstanceFieldSetter;", "Lkotlin/reflect/jvm/internal/FunctionCaller$FieldSetter;", "field", "Ljava/lang/reflect/Field;", "notNull", "", "(Ljava/lang/reflect/Field;Z)V", "kotlin-reflect-api"}, mo37406k = 1, mo37407mv = {1, 1, 11})
    /* compiled from: FunctionCaller.kt */
    public static final class InstanceFieldSetter extends FieldSetter {
        public InstanceFieldSetter(@NotNull Field field, boolean z) {
            Intrinsics.checkParameterIsNotNull(field, "field");
            super(field, z, false, 4, null);
        }
    }

    @Metadata(mo37403bv = {1, 0, 2}, mo37404d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\bH\u0016¢\u0006\u0002\u0010\t¨\u0006\n"}, mo37405d2 = {"Lkotlin/reflect/jvm/internal/FunctionCaller$InstanceMethod;", "Lkotlin/reflect/jvm/internal/FunctionCaller$Method;", "method", "Ljava/lang/reflect/Method;", "(Ljava/lang/reflect/Method;)V", "call", "", "args", "", "([Ljava/lang/Object;)Ljava/lang/Object;", "kotlin-reflect-api"}, mo37406k = 1, mo37407mv = {1, 1, 11})
    /* compiled from: FunctionCaller.kt */
    public static final class InstanceMethod extends Method {
        public InstanceMethod(@NotNull java.lang.reflect.Method method) {
            Intrinsics.checkParameterIsNotNull(method, Param.METHOD);
            super(method, false, null, 6, null);
        }

        @Nullable
        public Object call(@NotNull Object[] objArr) {
            Intrinsics.checkParameterIsNotNull(objArr, "args");
            checkArguments(objArr);
            return callMethod(objArr[0], FunctionCaller.Companion.dropFirstArg(objArr));
        }
    }

    @Metadata(mo37403bv = {1, 0, 2}, mo37404d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\bH\u0016¢\u0006\u0002\u0010\t¨\u0006\n"}, mo37405d2 = {"Lkotlin/reflect/jvm/internal/FunctionCaller$JvmStaticInObject;", "Lkotlin/reflect/jvm/internal/FunctionCaller$Method;", "method", "Ljava/lang/reflect/Method;", "(Ljava/lang/reflect/Method;)V", "call", "", "args", "", "([Ljava/lang/Object;)Ljava/lang/Object;", "kotlin-reflect-api"}, mo37406k = 1, mo37407mv = {1, 1, 11})
    /* compiled from: FunctionCaller.kt */
    public static final class JvmStaticInObject extends Method {
        public JvmStaticInObject(@NotNull java.lang.reflect.Method method) {
            Intrinsics.checkParameterIsNotNull(method, Param.METHOD);
            super(method, true, null, 4, null);
        }

        @Nullable
        public Object call(@NotNull Object[] objArr) {
            Intrinsics.checkParameterIsNotNull(objArr, "args");
            checkArguments(objArr);
            checkObjectInstance(ArraysKt.firstOrNull(objArr));
            return callMethod(null, FunctionCaller.Companion.dropFirstArg(objArr));
        }
    }

    @Metadata(mo37403bv = {1, 0, 2}, mo37404d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0019\u0010\u0005\u001a\u00020\u00062\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\bH\u0014¢\u0006\u0002\u0010\t¨\u0006\n"}, mo37405d2 = {"Lkotlin/reflect/jvm/internal/FunctionCaller$JvmStaticInObjectFieldGetter;", "Lkotlin/reflect/jvm/internal/FunctionCaller$FieldGetter;", "field", "Ljava/lang/reflect/Field;", "(Ljava/lang/reflect/Field;)V", "checkArguments", "", "args", "", "([Ljava/lang/Object;)V", "kotlin-reflect-api"}, mo37406k = 1, mo37407mv = {1, 1, 11})
    /* compiled from: FunctionCaller.kt */
    public static final class JvmStaticInObjectFieldGetter extends FieldGetter {
        public JvmStaticInObjectFieldGetter(@NotNull Field field) {
            Intrinsics.checkParameterIsNotNull(field, "field");
            super(field, true);
        }

        /* access modifiers changed from: protected */
        public void checkArguments(@NotNull Object[] objArr) {
            Intrinsics.checkParameterIsNotNull(objArr, "args");
            super.checkArguments(objArr);
            checkObjectInstance(ArraysKt.firstOrNull(objArr));
        }
    }

    @Metadata(mo37403bv = {1, 0, 2}, mo37404d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0019\u0010\u0007\u001a\u00020\b2\n\u0010\t\u001a\u0006\u0012\u0002\b\u00030\nH\u0014¢\u0006\u0002\u0010\u000b¨\u0006\f"}, mo37405d2 = {"Lkotlin/reflect/jvm/internal/FunctionCaller$JvmStaticInObjectFieldSetter;", "Lkotlin/reflect/jvm/internal/FunctionCaller$FieldSetter;", "field", "Ljava/lang/reflect/Field;", "notNull", "", "(Ljava/lang/reflect/Field;Z)V", "checkArguments", "", "args", "", "([Ljava/lang/Object;)V", "kotlin-reflect-api"}, mo37406k = 1, mo37407mv = {1, 1, 11})
    /* compiled from: FunctionCaller.kt */
    public static final class JvmStaticInObjectFieldSetter extends FieldSetter {
        public JvmStaticInObjectFieldSetter(@NotNull Field field, boolean z) {
            Intrinsics.checkParameterIsNotNull(field, "field");
            super(field, z, true);
        }

        /* access modifiers changed from: protected */
        public void checkArguments(@NotNull Object[] objArr) {
            Intrinsics.checkParameterIsNotNull(objArr, "args");
            super.checkArguments(objArr);
            checkObjectInstance(ArraysKt.firstOrNull(objArr));
        }
    }

    @Metadata(mo37403bv = {1, 0, 2}, mo37404d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0004\b&\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B'\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0002\u0010\tJ%\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\r\u001a\u0004\u0018\u00010\f2\n\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\u0007H\u0004¢\u0006\u0002\u0010\u000fR\u000e\u0010\n\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, mo37405d2 = {"Lkotlin/reflect/jvm/internal/FunctionCaller$Method;", "Lkotlin/reflect/jvm/internal/FunctionCaller;", "Ljava/lang/reflect/Method;", "method", "requiresInstance", "", "parameterTypes", "", "Ljava/lang/reflect/Type;", "(Ljava/lang/reflect/Method;Z[Ljava/lang/reflect/Type;)V", "isVoidMethod", "callMethod", "", "instance", "args", "(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;", "kotlin-reflect-api"}, mo37406k = 1, mo37407mv = {1, 1, 11})
    /* compiled from: FunctionCaller.kt */
    public static abstract class Method extends FunctionCaller<java.lang.reflect.Method> {
        private final boolean isVoidMethod;

        public /* synthetic */ Method(java.lang.reflect.Method method, boolean z, Type[] typeArr, int i, DefaultConstructorMarker defaultConstructorMarker) {
            if ((i & 2) != 0) {
                z = !Modifier.isStatic(method.getModifiers());
            }
            if ((i & 4) != 0) {
                typeArr = method.getGenericParameterTypes();
                Intrinsics.checkExpressionValueIsNotNull(typeArr, "method.genericParameterTypes");
            }
            this(method, z, typeArr);
        }

        public Method(@NotNull java.lang.reflect.Method method, boolean z, @NotNull Type[] typeArr) {
            Intrinsics.checkParameterIsNotNull(method, Param.METHOD);
            Intrinsics.checkParameterIsNotNull(typeArr, "parameterTypes");
            Member member = method;
            Type genericReturnType = method.getGenericReturnType();
            Intrinsics.checkExpressionValueIsNotNull(genericReturnType, "method.genericReturnType");
            super(member, genericReturnType, z ? method.getDeclaringClass() : null, typeArr);
            this.isVoidMethod = Intrinsics.areEqual((Object) getReturnType$kotlin_reflect_api(), (Object) Void.TYPE);
        }

        /* access modifiers changed from: protected */
        @Nullable
        public final Object callMethod(@Nullable Object obj, @NotNull Object[] objArr) {
            Intrinsics.checkParameterIsNotNull(objArr, "args");
            return this.isVoidMethod ? Unit.INSTANCE : ((java.lang.reflect.Method) getMember$kotlin_reflect_api()).invoke(obj, Arrays.copyOf(objArr, objArr.length));
        }
    }

    @Metadata(mo37403bv = {1, 0, 2}, mo37404d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, mo37405d2 = {"Lkotlin/reflect/jvm/internal/FunctionCaller$StaticFieldGetter;", "Lkotlin/reflect/jvm/internal/FunctionCaller$FieldGetter;", "field", "Ljava/lang/reflect/Field;", "(Ljava/lang/reflect/Field;)V", "kotlin-reflect-api"}, mo37406k = 1, mo37407mv = {1, 1, 11})
    /* compiled from: FunctionCaller.kt */
    public static final class StaticFieldGetter extends FieldGetter {
        public StaticFieldGetter(@NotNull Field field) {
            Intrinsics.checkParameterIsNotNull(field, "field");
            super(field, false, 2, null);
        }
    }

    @Metadata(mo37403bv = {1, 0, 2}, mo37404d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, mo37405d2 = {"Lkotlin/reflect/jvm/internal/FunctionCaller$StaticFieldSetter;", "Lkotlin/reflect/jvm/internal/FunctionCaller$FieldSetter;", "field", "Ljava/lang/reflect/Field;", "notNull", "", "(Ljava/lang/reflect/Field;Z)V", "kotlin-reflect-api"}, mo37406k = 1, mo37407mv = {1, 1, 11})
    /* compiled from: FunctionCaller.kt */
    public static final class StaticFieldSetter extends FieldSetter {
        public StaticFieldSetter(@NotNull Field field, boolean z) {
            Intrinsics.checkParameterIsNotNull(field, "field");
            super(field, z, false, 4, null);
        }
    }

    @Metadata(mo37403bv = {1, 0, 2}, mo37404d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\bH\u0016¢\u0006\u0002\u0010\t¨\u0006\n"}, mo37405d2 = {"Lkotlin/reflect/jvm/internal/FunctionCaller$StaticMethod;", "Lkotlin/reflect/jvm/internal/FunctionCaller$Method;", "method", "Ljava/lang/reflect/Method;", "(Ljava/lang/reflect/Method;)V", "call", "", "args", "", "([Ljava/lang/Object;)Ljava/lang/Object;", "kotlin-reflect-api"}, mo37406k = 1, mo37407mv = {1, 1, 11})
    /* compiled from: FunctionCaller.kt */
    public static final class StaticMethod extends Method {
        public StaticMethod(@NotNull java.lang.reflect.Method method) {
            Intrinsics.checkParameterIsNotNull(method, Param.METHOD);
            super(method, false, null, 6, null);
        }

        @Nullable
        public Object call(@NotNull Object[] objArr) {
            Intrinsics.checkParameterIsNotNull(objArr, "args");
            checkArguments(objArr);
            return callMethod(null, objArr);
        }
    }

    @Metadata(mo37403bv = {1, 0, 2}, mo37404d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u001b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u0007H\u0016¢\u0006\u0002\u0010\b¨\u0006\t"}, mo37405d2 = {"Lkotlin/reflect/jvm/internal/FunctionCaller$ThrowingCaller;", "Lkotlin/reflect/jvm/internal/FunctionCaller;", "", "()V", "call", "", "args", "", "([Ljava/lang/Object;)Ljava/lang/Object;", "kotlin-reflect-api"}, mo37406k = 1, mo37407mv = {1, 1, 11})
    /* compiled from: FunctionCaller.kt */
    public static final class ThrowingCaller extends FunctionCaller {
        public static final ThrowingCaller INSTANCE = new ThrowingCaller();

        private ThrowingCaller() {
            Class cls = Void.TYPE;
            Intrinsics.checkExpressionValueIsNotNull(cls, "Void.TYPE");
            super(null, cls, null, new Type[0]);
        }

        @Nullable
        public Object call(@NotNull Object[] objArr) {
            Intrinsics.checkParameterIsNotNull(objArr, "args");
            throw new UnsupportedOperationException("call/callBy are not supported for this declaration.");
        }
    }

    @Nullable
    public abstract Object call(@NotNull Object[] objArr);

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0035, code lost:
        if (r2 != null) goto L_0x003c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public FunctionCaller(M r2, @org.jetbrains.annotations.NotNull java.lang.reflect.Type r3, @org.jetbrains.annotations.Nullable java.lang.Class<?> r4, @org.jetbrains.annotations.NotNull java.lang.reflect.Type[] r5) {
        /*
            r1 = this;
            java.lang.String r0 = "returnType"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r3, r0)
            java.lang.String r0 = "valueParameterTypes"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r5, r0)
            r1.<init>()
            r1.member = r2
            r1.returnType = r3
            r1.instanceClass = r4
            java.lang.Class<?> r2 = r1.instanceClass
            if (r2 == 0) goto L_0x0038
            kotlin.jvm.internal.SpreadBuilder r3 = new kotlin.jvm.internal.SpreadBuilder
            r4 = 2
            r3.<init>(r4)
            java.lang.reflect.Type r2 = (java.lang.reflect.Type) r2
            r3.add(r2)
            r3.addSpread(r5)
            int r2 = r3.size()
            java.lang.reflect.Type[] r2 = new java.lang.reflect.Type[r2]
            java.lang.Object[] r2 = r3.toArray(r2)
            java.lang.reflect.Type[] r2 = (java.lang.reflect.Type[]) r2
            java.util.List r2 = kotlin.collections.CollectionsKt.listOf(r2)
            if (r2 == 0) goto L_0x0038
            goto L_0x003c
        L_0x0038:
            java.util.List r2 = kotlin.collections.ArraysKt.toList(r5)
        L_0x003c:
            r1.parameterTypes = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.FunctionCaller.<init>(java.lang.reflect.Member, java.lang.reflect.Type, java.lang.Class, java.lang.reflect.Type[]):void");
    }

    public final M getMember$kotlin_reflect_api() {
        return this.member;
    }

    @NotNull
    public final Type getReturnType$kotlin_reflect_api() {
        return this.returnType;
    }

    @Nullable
    public final Class<?> getInstanceClass$kotlin_reflect_api() {
        return this.instanceClass;
    }

    @NotNull
    public final List<Type> getParameterTypes() {
        return this.parameterTypes;
    }

    public final int getArity() {
        return this.parameterTypes.size();
    }

    /* access modifiers changed from: protected */
    public void checkArguments(@NotNull Object[] objArr) {
        Intrinsics.checkParameterIsNotNull(objArr, "args");
        if (getArity() != objArr.length) {
            StringBuilder sb = new StringBuilder();
            sb.append("Callable expects ");
            sb.append(getArity());
            sb.append(" arguments, but ");
            sb.append(objArr.length);
            sb.append(" were provided.");
            throw new IllegalArgumentException(sb.toString());
        }
    }

    /* access modifiers changed from: protected */
    public final void checkObjectInstance(@Nullable Object obj) {
        if (obj != null) {
            M m = this.member;
            if (m == null) {
                Intrinsics.throwNpe();
            }
            if (m.getDeclaringClass().isInstance(obj)) {
                return;
            }
        }
        throw new IllegalArgumentException("An object member requires the object instance passed as the first argument.");
    }
}
