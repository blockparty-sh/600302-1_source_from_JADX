package kotlin.reflect.jvm.internal;

import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.pcollections.HashPMap;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 2}, mo37404d1 = {"\u0000&\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\b\u0010\u0005\u001a\u00020\u0006H\u0000\u001a&\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\t0\b\"\b\b\u0000\u0010\t*\u00020\u00042\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\t0\u000bH\u0000\"*\u0010\u0000\u001a\u001e\u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00020\u0002\u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00040\u00040\u0001X\u000e¢\u0006\u0002\n\u0000¨\u0006\f"}, mo37405d2 = {"K_CLASS_CACHE", "Lkotlin/reflect/jvm/internal/pcollections/HashPMap;", "", "kotlin.jvm.PlatformType", "", "clearKClassCache", "", "getOrCreateKotlinClass", "Lkotlin/reflect/jvm/internal/KClassImpl;", "T", "jClass", "Ljava/lang/Class;", "kotlin-reflect-api"}, mo37406k = 2, mo37407mv = {1, 1, 11})
/* compiled from: kClassCache.kt */
public final class KClassCacheKt {
    private static HashPMap<String, Object> K_CLASS_CACHE = HashPMap.empty();

    @NotNull
    public static final <T> KClassImpl<T> getOrCreateKotlinClass(@NotNull Class<T> cls) {
        Intrinsics.checkParameterIsNotNull(cls, "jClass");
        String name = cls.getName();
        Object obj = K_CLASS_CACHE.get(name);
        Class cls2 = null;
        if (obj instanceof WeakReference) {
            KClassImpl<T> kClassImpl = (KClassImpl) ((WeakReference) obj).get();
            if (kClassImpl != null) {
                cls2 = kClassImpl.getJClass();
            }
            if (Intrinsics.areEqual((Object) cls2, (Object) cls)) {
                return kClassImpl;
            }
        } else if (obj != null) {
            for (WeakReference weakReference : (WeakReference[]) obj) {
                KClassImpl<T> kClassImpl2 = (KClassImpl) weakReference.get();
                if (Intrinsics.areEqual(kClassImpl2 != null ? kClassImpl2.getJClass() : null, (Object) cls)) {
                    return kClassImpl2;
                }
            }
            int length = ((Object[]) obj).length;
            WeakReference[] weakReferenceArr = new WeakReference[(length + 1)];
            System.arraycopy(obj, 0, weakReferenceArr, 0, length);
            KClassImpl<T> kClassImpl3 = new KClassImpl<>(cls);
            weakReferenceArr[length] = new WeakReference(kClassImpl3);
            K_CLASS_CACHE = K_CLASS_CACHE.plus(name, weakReferenceArr);
            return kClassImpl3;
        }
        KClassImpl<T> kClassImpl4 = new KClassImpl<>(cls);
        K_CLASS_CACHE = K_CLASS_CACHE.plus(name, new WeakReference(kClassImpl4));
        return kClassImpl4;
    }

    public static final void clearKClassCache() {
        K_CLASS_CACHE = HashPMap.empty();
    }
}
