package kotlin.reflect.jvm.internal;

import java.lang.reflect.Type;
import java.util.List;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference0Impl;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClassifier;
import kotlin.reflect.KProperty;
import kotlin.reflect.KType;
import kotlin.reflect.KTypeProjection;
import kotlin.reflect.jvm.KTypesJvm;
import kotlin.reflect.jvm.internal.ReflectProperties.LazySoftVal;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeAliasDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.structure.ReflectClassUtilKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 2}, mo37404d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\u0012\u0010\u001d\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u0013\u0010\u001e\u001a\u00020\u00152\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0002J\b\u0010!\u001a\u00020\"H\u0016J\b\u0010#\u001a\u00020$H\u0016R!\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t8VX\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\fR\u001d\u0010\u000f\u001a\u0004\u0018\u00010\u00108VX\u0002¢\u0006\f\n\u0004\b\u0013\u0010\u000e\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0014\u001a\u00020\u00158VX\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0016R\u001b\u0010\u0017\u001a\u00020\u00068@X\u0002¢\u0006\f\n\u0004\b\u001a\u0010\u000e\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001c¨\u0006%²\u0006\u0013\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00060\tX\u0002¢\u0006\u0000"}, mo37405d2 = {"Lkotlin/reflect/jvm/internal/KTypeImpl;", "Lkotlin/reflect/KType;", "type", "Lkotlin/reflect/jvm/internal/impl/types/KotlinType;", "computeJavaType", "Lkotlin/Function0;", "Ljava/lang/reflect/Type;", "(Lorg/jetbrains/kotlin/types/KotlinType;Lkotlin/jvm/functions/Function0;)V", "arguments", "", "Lkotlin/reflect/KTypeProjection;", "getArguments", "()Ljava/util/List;", "arguments$delegate", "Lkotlin/reflect/jvm/internal/ReflectProperties$LazySoftVal;", "classifier", "Lkotlin/reflect/KClassifier;", "getClassifier", "()Lkotlin/reflect/KClassifier;", "classifier$delegate", "isMarkedNullable", "", "()Z", "javaType", "getJavaType$kotlin_reflect_api", "()Ljava/lang/reflect/Type;", "javaType$delegate", "getType", "()Lorg/jetbrains/kotlin/types/KotlinType;", "convert", "equals", "other", "", "hashCode", "", "toString", "", "kotlin-reflect-api", "parameterizedTypeArguments"}, mo37406k = 1, mo37407mv = {1, 1, 11})
/* compiled from: KTypeImpl.kt */
public final class KTypeImpl implements KType {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(KTypeImpl.class), "javaType", "getJavaType$kotlin_reflect_api()Ljava/lang/reflect/Type;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(KTypeImpl.class), "classifier", "getClassifier()Lkotlin/reflect/KClassifier;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(KTypeImpl.class), "arguments", "getArguments()Ljava/util/List;")), Reflection.property0(new PropertyReference0Impl(Reflection.getOrCreateKotlinClass(KTypeImpl.class), "parameterizedTypeArguments", "<v#0>"))};
    @NotNull
    private final LazySoftVal arguments$delegate = ReflectProperties.lazySoft(new KTypeImpl$arguments$2(this));
    @Nullable
    private final LazySoftVal classifier$delegate = ReflectProperties.lazySoft(new KTypeImpl$classifier$2(this));
    @NotNull
    private final LazySoftVal javaType$delegate;
    @NotNull
    private final KotlinType type;

    @Metadata(mo37403bv = {1, 0, 2}, mo37406k = 3, mo37407mv = {1, 1, 11})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[Variance.values().length];

        static {
            $EnumSwitchMapping$0[Variance.INVARIANT.ordinal()] = 1;
            $EnumSwitchMapping$0[Variance.IN_VARIANCE.ordinal()] = 2;
            $EnumSwitchMapping$0[Variance.OUT_VARIANCE.ordinal()] = 3;
        }
    }

    @NotNull
    public List<KTypeProjection> getArguments() {
        return (List) this.arguments$delegate.getValue(this, $$delegatedProperties[2]);
    }

    @Nullable
    public KClassifier getClassifier() {
        return (KClassifier) this.classifier$delegate.getValue(this, $$delegatedProperties[1]);
    }

    @NotNull
    public final Type getJavaType$kotlin_reflect_api() {
        return (Type) this.javaType$delegate.getValue(this, $$delegatedProperties[0]);
    }

    public KTypeImpl(@NotNull KotlinType kotlinType, @NotNull Function0<? extends Type> function0) {
        Intrinsics.checkParameterIsNotNull(kotlinType, "type");
        Intrinsics.checkParameterIsNotNull(function0, "computeJavaType");
        this.type = kotlinType;
        this.javaType$delegate = ReflectProperties.lazySoft(function0);
    }

    @NotNull
    public final KotlinType getType() {
        return this.type;
    }

    /* access modifiers changed from: private */
    public final KClassifier convert(KotlinType kotlinType) {
        ClassifierDescriptor declarationDescriptor = kotlinType.getConstructor().getDeclarationDescriptor();
        if (declarationDescriptor instanceof ClassDescriptor) {
            Class javaClass = UtilKt.toJavaClass((ClassDescriptor) declarationDescriptor);
            if (javaClass == null) {
                return null;
            }
            if (javaClass.isArray()) {
                TypeProjection typeProjection = (TypeProjection) CollectionsKt.singleOrNull(kotlinType.getArguments());
                if (typeProjection != null) {
                    KotlinType type2 = typeProjection.getType();
                    if (type2 != null) {
                        Intrinsics.checkExpressionValueIsNotNull(type2, "argument");
                        KClassifier convert = convert(type2);
                        if (convert != null) {
                            return new KClassImpl(ReflectClassUtilKt.createArrayType(JvmClassMappingKt.getJavaClass(KTypesJvm.getJvmErasure(convert))));
                        }
                        StringBuilder sb = new StringBuilder();
                        sb.append("Cannot determine classifier for array element type: ");
                        sb.append(this);
                        throw new KotlinReflectionInternalError(sb.toString());
                    }
                }
                return new KClassImpl(javaClass);
            } else if (TypeUtils.isNullableType(kotlinType)) {
                return new KClassImpl(javaClass);
            } else {
                Class primitiveByWrapper = ReflectClassUtilKt.getPrimitiveByWrapper(javaClass);
                if (primitiveByWrapper != null) {
                    javaClass = primitiveByWrapper;
                }
                return new KClassImpl(javaClass);
            }
        } else if (declarationDescriptor instanceof TypeParameterDescriptor) {
            return new KTypeParameterImpl((TypeParameterDescriptor) declarationDescriptor);
        } else {
            if (!(declarationDescriptor instanceof TypeAliasDescriptor)) {
                return null;
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append("An operation is not implemented: ");
            sb2.append("Type alias classifiers are not yet supported");
            throw new NotImplementedError(sb2.toString());
        }
    }

    public boolean isMarkedNullable() {
        return this.type.isMarkedNullable();
    }

    public boolean equals(@Nullable Object obj) {
        return (obj instanceof KTypeImpl) && Intrinsics.areEqual((Object) this.type, (Object) ((KTypeImpl) obj).type);
    }

    public int hashCode() {
        return this.type.hashCode();
    }

    @NotNull
    public String toString() {
        return ReflectionObjectRenderer.INSTANCE.renderType(this.type);
    }
}
