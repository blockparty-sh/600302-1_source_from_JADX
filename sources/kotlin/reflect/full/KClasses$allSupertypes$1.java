package kotlin.reflect.full;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlin.reflect.KClassifier;
import kotlin.reflect.KType;
import kotlin.reflect.jvm.internal.KTypeImpl;
import kotlin.reflect.jvm.internal.KotlinReflectionInternalError;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.utils.DFS.Neighbors;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 2}, mo37404d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u001d\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001c\n\u0002\b\u0002\u0010\u0000\u001a$\u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00020\u0002 \u0003*\u0010\u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00020\u00020\u00040\u00012\u000e\u0010\u0005\u001a\n \u0003*\u0004\u0018\u00010\u00020\u0002H\n¢\u0006\u0002\b\u0006"}, mo37405d2 = {"<anonymous>", "", "Lkotlin/reflect/KType;", "kotlin.jvm.PlatformType", "", "current", "getNeighbors"}, mo37406k = 3, mo37407mv = {1, 1, 11})
/* compiled from: KClasses.kt */
final class KClasses$allSupertypes$1<N> implements Neighbors<KType> {
    public static final KClasses$allSupertypes$1 INSTANCE = new KClasses$allSupertypes$1();

    KClasses$allSupertypes$1() {
    }

    @NotNull
    public final Iterable<KType> getNeighbors(KType kType) {
        KClassifier classifier = kType.getClassifier();
        if (!(classifier instanceof KClass)) {
            classifier = null;
        }
        KClass kClass = (KClass) classifier;
        if (kClass != null) {
            List supertypes = kClass.getSupertypes();
            if (kType.getArguments().isEmpty()) {
                return supertypes;
            }
            String str = "null cannot be cast to non-null type kotlin.reflect.jvm.internal.KTypeImpl";
            if (kType != null) {
                TypeSubstitutor create = TypeSubstitutor.create(((KTypeImpl) kType).getType());
                Iterable<KType> iterable = supertypes;
                Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
                for (KType kType2 : iterable) {
                    if (kType2 != null) {
                        KotlinType substitute = create.substitute(((KTypeImpl) kType2).getType(), Variance.INVARIANT);
                        if (substitute != null) {
                            Intrinsics.checkExpressionValueIsNotNull(substitute, "substituted");
                            arrayList.add(new KTypeImpl(substitute, KClasses$allSupertypes$1$1$1$1.INSTANCE));
                        } else {
                            StringBuilder sb = new StringBuilder();
                            sb.append("Type substitution failed: ");
                            sb.append(kType2);
                            sb.append(" (");
                            sb.append(kType);
                            sb.append(')');
                            throw new KotlinReflectionInternalError(sb.toString());
                        }
                    } else {
                        throw new TypeCastException(str);
                    }
                }
                return (List) arrayList;
            }
            throw new TypeCastException(str);
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Supertype not a class: ");
        sb2.append(kType);
        throw new KotlinReflectionInternalError(sb2.toString());
    }
}
