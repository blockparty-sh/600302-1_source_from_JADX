package kotlin.reflect.full;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.SinceKotlin;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClassifier;
import kotlin.reflect.KType;
import kotlin.reflect.KTypeProjection;
import kotlin.reflect.KVariance;
import kotlin.reflect.jvm.internal.KClassifierImpl;
import kotlin.reflect.jvm.internal.KTypeImpl;
import kotlin.reflect.jvm.internal.KotlinReflectionInternalError;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.StarProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeProjectionBase;
import kotlin.reflect.jvm.internal.impl.types.TypeProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 2}, mo37404d1 = {"\u00008\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u001b\n\u0000\u001a.\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002\u001a6\u0010\u0012\u001a\u00020\u0001*\u00020\u00022\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\u000e\b\u0002\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u000eH\u0007\"\u001e\u0010\u0000\u001a\u00020\u0001*\u00020\u00028FX\u0004¢\u0006\f\u0012\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0015"}, mo37405d2 = {"starProjectedType", "Lkotlin/reflect/KType;", "Lkotlin/reflect/KClassifier;", "starProjectedType$annotations", "(Lkotlin/reflect/KClassifier;)V", "getStarProjectedType", "(Lkotlin/reflect/KClassifier;)Lkotlin/reflect/KType;", "createKotlinType", "Lkotlin/reflect/jvm/internal/impl/types/SimpleType;", "typeAnnotations", "Lkotlin/reflect/jvm/internal/impl/descriptors/annotations/Annotations;", "typeConstructor", "Lkotlin/reflect/jvm/internal/impl/types/TypeConstructor;", "arguments", "", "Lkotlin/reflect/KTypeProjection;", "nullable", "", "createType", "annotations", "", "kotlin-reflect-api"}, mo37406k = 2, mo37407mv = {1, 1, 11})
@JvmName(name = "KClassifiers")
/* compiled from: KClassifiers.kt */
public final class KClassifiers {

    @Metadata(mo37403bv = {1, 0, 2}, mo37406k = 3, mo37407mv = {1, 1, 11})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[KVariance.values().length];

        static {
            $EnumSwitchMapping$0[KVariance.INVARIANT.ordinal()] = 1;
            $EnumSwitchMapping$0[KVariance.IN.ordinal()] = 2;
            $EnumSwitchMapping$0[KVariance.OUT.ordinal()] = 3;
        }
    }

    @SinceKotlin(version = "1.1")
    public static /* synthetic */ void starProjectedType$annotations(KClassifier kClassifier) {
    }

    @NotNull
    @SinceKotlin(version = "1.1")
    public static /* bridge */ /* synthetic */ KType createType$default(KClassifier kClassifier, List list, boolean z, List list2, int i, Object obj) {
        if ((i & 1) != 0) {
            list = CollectionsKt.emptyList();
        }
        if ((i & 2) != 0) {
            z = false;
        }
        if ((i & 4) != 0) {
            list2 = CollectionsKt.emptyList();
        }
        return createType(kClassifier, list, z, list2);
    }

    @NotNull
    @SinceKotlin(version = "1.1")
    public static final KType createType(@NotNull KClassifier kClassifier, @NotNull List<KTypeProjection> list, boolean z, @NotNull List<? extends Annotation> list2) {
        Annotations annotations;
        Intrinsics.checkParameterIsNotNull(kClassifier, "$receiver");
        Intrinsics.checkParameterIsNotNull(list, "arguments");
        Intrinsics.checkParameterIsNotNull(list2, "annotations");
        KClassifierImpl kClassifierImpl = (KClassifierImpl) (!(kClassifier instanceof KClassifierImpl) ? null : kClassifier);
        if (kClassifierImpl != null) {
            ClassifierDescriptor descriptor = kClassifierImpl.getDescriptor();
            if (descriptor != null) {
                TypeConstructor typeConstructor = descriptor.getTypeConstructor();
                Intrinsics.checkExpressionValueIsNotNull(typeConstructor, "typeConstructor");
                List parameters = typeConstructor.getParameters();
                if (parameters.size() == list.size()) {
                    if (list2.isEmpty()) {
                        annotations = Annotations.Companion.getEMPTY();
                    } else {
                        annotations = Annotations.Companion.getEMPTY();
                    }
                    return new KTypeImpl(createKotlinType(annotations, typeConstructor, list, z), new KClassifiers$createType$1(kClassifier));
                }
                StringBuilder sb = new StringBuilder();
                sb.append("Class declares ");
                sb.append(parameters.size());
                sb.append(" type parameters, but ");
                sb.append(list.size());
                sb.append(" were provided.");
                throw new IllegalArgumentException(sb.toString());
            }
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Cannot create type for an unsupported classifier: ");
        sb2.append(kClassifier);
        sb2.append(" (");
        sb2.append(kClassifier.getClass());
        sb2.append(')');
        throw new KotlinReflectionInternalError(sb2.toString());
    }

    private static final SimpleType createKotlinType(Annotations annotations, TypeConstructor typeConstructor, List<KTypeProjection> list, boolean z) {
        TypeProjectionBase typeProjectionBase;
        List parameters = typeConstructor.getParameters();
        Iterable<KTypeProjection> iterable = list;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        int i = 0;
        for (KTypeProjection kTypeProjection : iterable) {
            int i2 = i + 1;
            KTypeImpl kTypeImpl = (KTypeImpl) kTypeProjection.getType();
            KotlinType type = kTypeImpl != null ? kTypeImpl.getType() : null;
            KVariance variance = kTypeProjection.getVariance();
            if (variance == null) {
                Object obj = parameters.get(i);
                Intrinsics.checkExpressionValueIsNotNull(obj, "parameters[index]");
                typeProjectionBase = new StarProjectionImpl((TypeParameterDescriptor) obj);
            } else {
                int i3 = WhenMappings.$EnumSwitchMapping$0[variance.ordinal()];
                if (i3 == 1) {
                    Variance variance2 = Variance.INVARIANT;
                    if (type == null) {
                        Intrinsics.throwNpe();
                    }
                    typeProjectionBase = new TypeProjectionImpl(variance2, type);
                } else if (i3 == 2) {
                    Variance variance3 = Variance.IN_VARIANCE;
                    if (type == null) {
                        Intrinsics.throwNpe();
                    }
                    typeProjectionBase = new TypeProjectionImpl(variance3, type);
                } else if (i3 == 3) {
                    Variance variance4 = Variance.OUT_VARIANCE;
                    if (type == null) {
                        Intrinsics.throwNpe();
                    }
                    typeProjectionBase = new TypeProjectionImpl(variance4, type);
                } else {
                    throw new NoWhenBranchMatchedException();
                }
            }
            arrayList.add(typeProjectionBase);
            i = i2;
        }
        return KotlinTypeFactory.simpleType(annotations, typeConstructor, (List) arrayList, z);
    }

    @NotNull
    public static final KType getStarProjectedType(@NotNull KClassifier kClassifier) {
        Intrinsics.checkParameterIsNotNull(kClassifier, "$receiver");
        KClassifierImpl kClassifierImpl = (KClassifierImpl) (!(kClassifier instanceof KClassifierImpl) ? null : kClassifier);
        if (kClassifierImpl != null) {
            ClassifierDescriptor descriptor = kClassifierImpl.getDescriptor();
            if (descriptor != null) {
                TypeConstructor typeConstructor = descriptor.getTypeConstructor();
                Intrinsics.checkExpressionValueIsNotNull(typeConstructor, "descriptor.typeConstructor");
                List parameters = typeConstructor.getParameters();
                if (parameters.isEmpty()) {
                    return createType$default(kClassifier, null, false, null, 7, null);
                }
                Intrinsics.checkExpressionValueIsNotNull(parameters, "typeParameters");
                Iterable<TypeParameterDescriptor> iterable = parameters;
                Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
                for (TypeParameterDescriptor typeParameterDescriptor : iterable) {
                    arrayList.add(KTypeProjection.Companion.getSTAR());
                }
                return createType$default(kClassifier, (List) arrayList, false, null, 6, null);
            }
        }
        return createType$default(kClassifier, null, false, null, 7, null);
    }
}
