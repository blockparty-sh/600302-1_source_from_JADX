package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\"\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u0000ø\u0001\u0000¢\u0006\u0002\u0010\u0004\u0002\u0004\n\u0002\b\u0019¨\u0006\u0005"}, mo37405d2 = {"toState", "", "T", "Lkotlin/Result;", "(Ljava/lang/Object;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, mo37406k = 2, mo37407mv = {1, 1, 15})
/* compiled from: CompletedExceptionally.kt */
public final class CompletedExceptionallyKt {
    @Nullable
    public static final <T> Object toState(@NotNull Object obj) {
        if (Result.m486isSuccessimpl(obj)) {
            ResultKt.throwOnFailure(obj);
            return obj;
        }
        Throwable r4 = Result.m482exceptionOrNullimpl(obj);
        if (r4 == null) {
            Intrinsics.throwNpe();
        }
        return new CompletedExceptionally(r4, false, 2, null);
    }
}
