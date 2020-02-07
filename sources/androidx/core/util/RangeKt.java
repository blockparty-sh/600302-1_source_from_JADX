package androidx.core.util;

import android.util.Range;
import androidx.annotation.RequiresApi;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.ClosedRange;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000f\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a7\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001H\f\u001a6\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0006\u001a\u0002H\u0002H\n¢\u0006\u0002\u0010\u0007\u001a7\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001H\n\u001a0\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003*\u0002H\u00022\u0006\u0010\t\u001a\u0002H\u0002H\f¢\u0006\u0002\u0010\n\u001a(\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00020\f\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u0001H\u0007\u001a(\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\fH\u0007¨\u0006\u000e"}, mo37405d2 = {"and", "Landroid/util/Range;", "T", "", "other", "plus", "value", "(Landroid/util/Range;Ljava/lang/Comparable;)Landroid/util/Range;", "rangeTo", "that", "(Ljava/lang/Comparable;Ljava/lang/Comparable;)Landroid/util/Range;", "toClosedRange", "Lkotlin/ranges/ClosedRange;", "toRange", "core-ktx_release"}, mo37406k = 2, mo37407mv = {1, 1, 15})
/* compiled from: Range.kt */
public final class RangeKt {
    @RequiresApi(21)
    @NotNull
    public static final <T extends Comparable<? super T>> Range<T> rangeTo(@NotNull T t, @NotNull T t2) {
        Intrinsics.checkParameterIsNotNull(t, "$this$rangeTo");
        Intrinsics.checkParameterIsNotNull(t2, "that");
        return new Range<>(t, t2);
    }

    @RequiresApi(21)
    @NotNull
    public static final <T extends Comparable<? super T>> Range<T> plus(@NotNull Range<T> range, @NotNull T t) {
        Intrinsics.checkParameterIsNotNull(range, "$this$plus");
        Intrinsics.checkParameterIsNotNull(t, "value");
        Range<T> extend = range.extend(t);
        Intrinsics.checkExpressionValueIsNotNull(extend, "extend(value)");
        return extend;
    }

    @RequiresApi(21)
    @NotNull
    public static final <T extends Comparable<? super T>> Range<T> plus(@NotNull Range<T> range, @NotNull Range<T> range2) {
        Intrinsics.checkParameterIsNotNull(range, "$this$plus");
        Intrinsics.checkParameterIsNotNull(range2, "other");
        Range<T> extend = range.extend(range2);
        Intrinsics.checkExpressionValueIsNotNull(extend, "extend(other)");
        return extend;
    }

    @RequiresApi(21)
    @NotNull
    public static final <T extends Comparable<? super T>> Range<T> and(@NotNull Range<T> range, @NotNull Range<T> range2) {
        Intrinsics.checkParameterIsNotNull(range, "$this$and");
        Intrinsics.checkParameterIsNotNull(range2, "other");
        Range<T> intersect = range.intersect(range2);
        Intrinsics.checkExpressionValueIsNotNull(intersect, "intersect(other)");
        return intersect;
    }

    @RequiresApi(21)
    @NotNull
    public static final <T extends Comparable<? super T>> ClosedRange<T> toClosedRange(@NotNull Range<T> range) {
        Intrinsics.checkParameterIsNotNull(range, "$this$toClosedRange");
        return new RangeKt$toClosedRange$1<>(range);
    }

    @RequiresApi(21)
    @NotNull
    public static final <T extends Comparable<? super T>> Range<T> toRange(@NotNull ClosedRange<T> closedRange) {
        Intrinsics.checkParameterIsNotNull(closedRange, "$this$toRange");
        return new Range<>(closedRange.getStart(), closedRange.getEndInclusive());
    }
}
