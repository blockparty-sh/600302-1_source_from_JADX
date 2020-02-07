package kotlin.reflect.jvm.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.KProperty;
import kotlin.reflect.KTypeProjection;
import kotlin.reflect.jvm.internal.KTypeImpl.WhenMappings;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 2}, mo37404d1 = {"\u0000\f\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\n¢\u0006\u0002\b\u0003"}, mo37405d2 = {"<anonymous>", "", "Lkotlin/reflect/KTypeProjection;", "invoke"}, mo37406k = 3, mo37407mv = {1, 1, 11})
/* compiled from: KTypeImpl.kt */
final class KTypeImpl$arguments$2 extends Lambda implements Function0<List<? extends KTypeProjection>> {
    final /* synthetic */ KTypeImpl this$0;

    KTypeImpl$arguments$2(KTypeImpl kTypeImpl) {
        this.this$0 = kTypeImpl;
        super(0);
    }

    @NotNull
    public final List<KTypeProjection> invoke() {
        KTypeProjection kTypeProjection;
        List arguments = this.this$0.getType().getArguments();
        if (arguments.isEmpty()) {
            return CollectionsKt.emptyList();
        }
        Lazy lazy = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, (Function0) new KTypeImpl$arguments$2$parameterizedTypeArguments$2(this));
        KProperty kProperty = KTypeImpl.$$delegatedProperties[3];
        Iterable<TypeProjection> iterable = arguments;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        int i = 0;
        for (TypeProjection typeProjection : iterable) {
            int i2 = i + 1;
            if (typeProjection.isStarProjection()) {
                kTypeProjection = KTypeProjection.Companion.getSTAR();
            } else {
                KotlinType type = typeProjection.getType();
                Intrinsics.checkExpressionValueIsNotNull(type, "typeProjection.type");
                KTypeImpl kTypeImpl = new KTypeImpl(type, new KTypeImpl$arguments$2$$special$$inlined$mapIndexed$lambda$1(i, this, lazy, kProperty));
                int i3 = WhenMappings.$EnumSwitchMapping$0[typeProjection.getProjectionKind().ordinal()];
                if (i3 == 1) {
                    kTypeProjection = KTypeProjection.Companion.invariant(kTypeImpl);
                } else if (i3 == 2) {
                    kTypeProjection = KTypeProjection.Companion.contravariant(kTypeImpl);
                } else if (i3 == 3) {
                    kTypeProjection = KTypeProjection.Companion.covariant(kTypeImpl);
                } else {
                    throw new NoWhenBranchMatchedException();
                }
            }
            arrayList.add(kTypeProjection);
            i = i2;
        }
        return (List) arrayList;
    }
}
