package kotlinx.coroutines.internal;

import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0016\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a.\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u00012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0006H\b¢\u0006\u0002\u0010\u0007*\f\b\u0000\u0010\b\"\u00020\u00032\u00020\u0003¨\u0006\t"}, mo37405d2 = {"synchronized", "T", "lock", "", "Lkotlinx/coroutines/internal/SynchronizedObject;", "block", "Lkotlin/Function0;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "SynchronizedObject", "kotlinx-coroutines-core"}, mo37406k = 2, mo37407mv = {1, 1, 15})
/* compiled from: Synchronized.kt */
public final class SynchronizedKt {
    public static /* synthetic */ void SynchronizedObject$annotations() {
    }

    @PublishedApi
    /* renamed from: synchronized reason: not valid java name */
    public static final <T> T m1369synchronized(@NotNull Object obj, @NotNull Function0<? extends T> function0) {
        T invoke;
        Intrinsics.checkParameterIsNotNull(obj, "lock");
        Intrinsics.checkParameterIsNotNull(function0, "block");
        synchronized (obj) {
            try {
                invoke = function0.invoke();
            } finally {
                InlineMarker.finallyStart(1);
                InlineMarker.finallyEnd(1);
            }
        }
        InlineMarker.finallyEnd(1);
        return invoke;
    }
}
