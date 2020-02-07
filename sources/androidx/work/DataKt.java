package androidx.work;

import androidx.work.Data.Builder;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\b\u0002\u001a>\u0010\u0000\u001a\u00020\u00012.\u0010\u0002\u001a\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00040\u0003\"\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0004H\b¢\u0006\u0002\u0010\u0007¨\u0006\b"}, mo37405d2 = {"workDataOf", "Landroidx/work/Data;", "pairs", "", "Lkotlin/Pair;", "", "", "([Lkotlin/Pair;)Landroidx/work/Data;", "work-runtime-ktx_release"}, mo37406k = 2, mo37407mv = {1, 1, 15})
/* compiled from: Data.kt */
public final class DataKt {
    @NotNull
    public static final Data workDataOf(@NotNull Pair<String, ? extends Object>... pairArr) {
        Intrinsics.checkParameterIsNotNull(pairArr, "pairs");
        Builder builder = new Builder();
        for (Pair<String, ? extends Object> pair : pairArr) {
            builder.put((String) pair.getFirst(), pair.getSecond());
        }
        Data build = builder.build();
        Intrinsics.checkExpressionValueIsNotNull(build, "dataBuilder.build()");
        return build;
    }
}
