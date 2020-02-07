package kotlin.jvm.internal;

import com.htc.htcwalletsdk.Utils.TableParser.C2278Key;
import kotlin.Metadata;
import kotlin.collections.BooleanIterator;
import kotlin.collections.ByteIterator;
import kotlin.collections.CharIterator;
import kotlin.collections.DoubleIterator;
import kotlin.collections.FloatIterator;
import kotlin.collections.IntIterator;
import kotlin.collections.LongIterator;
import kotlin.collections.ShortIterator;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000F\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0019\n\u0002\u0018\u0002\n\u0002\u0010\u0013\n\u0002\u0018\u0002\n\u0002\u0010\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0015\n\u0002\u0018\u0002\n\u0002\u0010\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0017\n\u0000\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u000e\u0010\u0000\u001a\u00020\u00042\u0006\u0010\u0002\u001a\u00020\u0005\u001a\u000e\u0010\u0000\u001a\u00020\u00062\u0006\u0010\u0002\u001a\u00020\u0007\u001a\u000e\u0010\u0000\u001a\u00020\b2\u0006\u0010\u0002\u001a\u00020\t\u001a\u000e\u0010\u0000\u001a\u00020\n2\u0006\u0010\u0002\u001a\u00020\u000b\u001a\u000e\u0010\u0000\u001a\u00020\f2\u0006\u0010\u0002\u001a\u00020\r\u001a\u000e\u0010\u0000\u001a\u00020\u000e2\u0006\u0010\u0002\u001a\u00020\u000f\u001a\u000e\u0010\u0000\u001a\u00020\u00102\u0006\u0010\u0002\u001a\u00020\u0011¨\u0006\u0012"}, mo37405d2 = {"iterator", "Lkotlin/collections/BooleanIterator;", "array", "", "Lkotlin/collections/ByteIterator;", "", "Lkotlin/collections/CharIterator;", "", "Lkotlin/collections/DoubleIterator;", "", "Lkotlin/collections/FloatIterator;", "", "Lkotlin/collections/IntIterator;", "", "Lkotlin/collections/LongIterator;", "", "Lkotlin/collections/ShortIterator;", "", "kotlin-stdlib"}, mo37406k = 2, mo37407mv = {1, 1, 15})
/* compiled from: ArrayIterators.kt */
public final class ArrayIteratorsKt {
    @NotNull
    public static final ByteIterator iterator(@NotNull byte[] bArr) {
        Intrinsics.checkParameterIsNotNull(bArr, C2278Key.table_data_array);
        return new ArrayByteIterator(bArr);
    }

    @NotNull
    public static final CharIterator iterator(@NotNull char[] cArr) {
        Intrinsics.checkParameterIsNotNull(cArr, C2278Key.table_data_array);
        return new ArrayCharIterator(cArr);
    }

    @NotNull
    public static final ShortIterator iterator(@NotNull short[] sArr) {
        Intrinsics.checkParameterIsNotNull(sArr, C2278Key.table_data_array);
        return new ArrayShortIterator(sArr);
    }

    @NotNull
    public static final IntIterator iterator(@NotNull int[] iArr) {
        Intrinsics.checkParameterIsNotNull(iArr, C2278Key.table_data_array);
        return new ArrayIntIterator(iArr);
    }

    @NotNull
    public static final LongIterator iterator(@NotNull long[] jArr) {
        Intrinsics.checkParameterIsNotNull(jArr, C2278Key.table_data_array);
        return new ArrayLongIterator(jArr);
    }

    @NotNull
    public static final FloatIterator iterator(@NotNull float[] fArr) {
        Intrinsics.checkParameterIsNotNull(fArr, C2278Key.table_data_array);
        return new ArrayFloatIterator(fArr);
    }

    @NotNull
    public static final DoubleIterator iterator(@NotNull double[] dArr) {
        Intrinsics.checkParameterIsNotNull(dArr, C2278Key.table_data_array);
        return new ArrayDoubleIterator(dArr);
    }

    @NotNull
    public static final BooleanIterator iterator(@NotNull boolean[] zArr) {
        Intrinsics.checkParameterIsNotNull(zArr, C2278Key.table_data_array);
        return new ArrayBooleanIterator(zArr);
    }
}
