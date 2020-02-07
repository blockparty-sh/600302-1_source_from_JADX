package kotlin.collections.unsigned;

import java.util.List;
import kotlin.ExperimentalUnsignedTypes;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.UByte;
import kotlin.UByteArray;
import kotlin.UInt;
import kotlin.UIntArray;
import kotlin.ULong;
import kotlin.ULongArray;
import kotlin.UShort;
import kotlin.UShortArray;
import kotlin.UnsignedKt;
import kotlin.collections.AbstractList;
import kotlin.collections.ArraysKt;
import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000F\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0016\n\u0002\u0010\u0002\n\u0002\b\t\u001a\u001c\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005\u001a\u001c\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00060\u0001*\u00020\u0007H\u0007ø\u0001\u0000¢\u0006\u0004\b\b\u0010\t\u001a\u001c\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\n0\u0001*\u00020\u000bH\u0007ø\u0001\u0000¢\u0006\u0004\b\f\u0010\r\u001a\u001c\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0001*\u00020\u000fH\u0007ø\u0001\u0000¢\u0006\u0004\b\u0010\u0010\u0011\u001a2\u0010\u0012\u001a\u00020\u0013*\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u00022\b\b\u0002\u0010\u0015\u001a\u00020\u00132\b\b\u0002\u0010\u0016\u001a\u00020\u0013H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018\u001a2\u0010\u0012\u001a\u00020\u0013*\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u00062\b\b\u0002\u0010\u0015\u001a\u00020\u00132\b\b\u0002\u0010\u0016\u001a\u00020\u0013H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u001a\u001a2\u0010\u0012\u001a\u00020\u0013*\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\n2\b\b\u0002\u0010\u0015\u001a\u00020\u00132\b\b\u0002\u0010\u0016\u001a\u00020\u0013H\u0007ø\u0001\u0000¢\u0006\u0004\b\u001b\u0010\u001c\u001a2\u0010\u0012\u001a\u00020\u0013*\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u000e2\b\b\u0002\u0010\u0015\u001a\u00020\u00132\b\b\u0002\u0010\u0016\u001a\u00020\u0013H\u0007ø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u001e\u001a\u001f\u0010\u001f\u001a\u00020\u0002*\u00020\u00032\u0006\u0010 \u001a\u00020\u0013H\bø\u0001\u0000¢\u0006\u0004\b!\u0010\"\u001a\u001f\u0010\u001f\u001a\u00020\u0006*\u00020\u00072\u0006\u0010 \u001a\u00020\u0013H\bø\u0001\u0000¢\u0006\u0004\b#\u0010$\u001a\u001f\u0010\u001f\u001a\u00020\n*\u00020\u000b2\u0006\u0010 \u001a\u00020\u0013H\bø\u0001\u0000¢\u0006\u0004\b%\u0010&\u001a\u001f\u0010\u001f\u001a\u00020\u000e*\u00020\u000f2\u0006\u0010 \u001a\u00020\u0013H\bø\u0001\u0000¢\u0006\u0004\b'\u0010(\u001a2\u0010)\u001a\u00020**\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u00022\b\b\u0002\u0010\u0015\u001a\u00020\u00132\b\b\u0002\u0010\u0016\u001a\u00020\u0013H\u0007ø\u0001\u0000¢\u0006\u0004\b+\u0010,\u001a2\u0010)\u001a\u00020**\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u00062\b\b\u0002\u0010\u0015\u001a\u00020\u00132\b\b\u0002\u0010\u0016\u001a\u00020\u0013H\u0007ø\u0001\u0000¢\u0006\u0004\b-\u0010.\u001a2\u0010)\u001a\u00020**\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\n2\b\b\u0002\u0010\u0015\u001a\u00020\u00132\b\b\u0002\u0010\u0016\u001a\u00020\u0013H\u0007ø\u0001\u0000¢\u0006\u0004\b/\u00100\u001a2\u0010)\u001a\u00020**\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u000e2\b\b\u0002\u0010\u0015\u001a\u00020\u00132\b\b\u0002\u0010\u0016\u001a\u00020\u0013H\u0007ø\u0001\u0000¢\u0006\u0004\b1\u00102\u0002\u0004\n\u0002\b\u0019¨\u00063"}, mo37405d2 = {"asList", "", "Lkotlin/UByte;", "Lkotlin/UByteArray;", "asList-GBYM_sE", "([B)Ljava/util/List;", "Lkotlin/UInt;", "Lkotlin/UIntArray;", "asList--ajY-9A", "([I)Ljava/util/List;", "Lkotlin/ULong;", "Lkotlin/ULongArray;", "asList-QwZRm1k", "([J)Ljava/util/List;", "Lkotlin/UShort;", "Lkotlin/UShortArray;", "asList-rL5Bavg", "([S)Ljava/util/List;", "binarySearch", "", "element", "fromIndex", "toIndex", "binarySearch-WpHrYlw", "([BBII)I", "binarySearch-2fe2U9s", "([IIII)I", "binarySearch-K6DWlUc", "([JJII)I", "binarySearch-EtDCXyQ", "([SSII)I", "elementAt", "index", "elementAt-PpDY95g", "([BI)B", "elementAt-qFRl0hI", "([II)I", "elementAt-r7IrZao", "([JI)J", "elementAt-nggk6HY", "([SI)S", "fill", "", "fill-WpHrYlw", "([BBII)V", "fill-2fe2U9s", "([IIII)V", "fill-K6DWlUc", "([JJII)V", "fill-EtDCXyQ", "([SSII)V", "kotlin-stdlib"}, mo37406k = 5, mo37407mv = {1, 1, 15}, mo37408pn = "kotlin.collections", mo37409xi = 1, mo37410xs = "kotlin/collections/unsigned/UArraysKt")
/* compiled from: _UArraysJvm.kt */
class UArraysKt___UArraysJvmKt {
    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    /* renamed from: elementAt-qFRl0hI reason: not valid java name */
    private static final int m816elementAtqFRl0hI(@NotNull int[] iArr, int i) {
        return UIntArray.m615getimpl(iArr, i);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    /* renamed from: elementAt-r7IrZao reason: not valid java name */
    private static final long m817elementAtr7IrZao(@NotNull long[] jArr, int i) {
        return ULongArray.m684getimpl(jArr, i);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    /* renamed from: elementAt-PpDY95g reason: not valid java name */
    private static final byte m814elementAtPpDY95g(@NotNull byte[] bArr, int i) {
        return UByteArray.m546getimpl(bArr, i);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    /* renamed from: elementAt-nggk6HY reason: not valid java name */
    private static final short m815elementAtnggk6HY(@NotNull short[] sArr, int i) {
        return UShortArray.m751getimpl(sArr, i);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @NotNull
    /* renamed from: asList--ajY-9A reason: not valid java name */
    public static final List<UInt> m802asListajY9A(@NotNull int[] iArr) {
        Intrinsics.checkParameterIsNotNull(iArr, "$this$asList");
        return new UArraysKt___UArraysJvmKt$asList$1<>(iArr);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @NotNull
    /* renamed from: asList-QwZRm1k reason: not valid java name */
    public static final List<ULong> m804asListQwZRm1k(@NotNull long[] jArr) {
        Intrinsics.checkParameterIsNotNull(jArr, "$this$asList");
        return new UArraysKt___UArraysJvmKt$asList$2<>(jArr);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @NotNull
    /* renamed from: asList-GBYM_sE reason: not valid java name */
    public static final List<UByte> m803asListGBYM_sE(@NotNull byte[] bArr) {
        Intrinsics.checkParameterIsNotNull(bArr, "$this$asList");
        return new UArraysKt___UArraysJvmKt$asList$3<>(bArr);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @NotNull
    /* renamed from: asList-rL5Bavg reason: not valid java name */
    public static final List<UShort> m805asListrL5Bavg(@NotNull short[] sArr) {
        Intrinsics.checkParameterIsNotNull(sArr, "$this$asList");
        return new UArraysKt___UArraysJvmKt$asList$4<>(sArr);
    }

    /* renamed from: binarySearch-2fe2U9s$default reason: not valid java name */
    public static /* synthetic */ int m807binarySearch2fe2U9s$default(int[] iArr, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i2 = 0;
        }
        if ((i4 & 4) != 0) {
            i3 = UIntArray.m616getSizeimpl(iArr);
        }
        return UArraysKt.m806binarySearch2fe2U9s(iArr, i, i2, i3);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    /* renamed from: binarySearch-2fe2U9s reason: not valid java name */
    public static final int m806binarySearch2fe2U9s(@NotNull int[] iArr, int i, int i2, int i3) {
        Intrinsics.checkParameterIsNotNull(iArr, "$this$binarySearch");
        AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(i2, i3, UIntArray.m616getSizeimpl(iArr));
        int i4 = i3 - 1;
        while (i2 <= i4) {
            int i5 = (i2 + i4) >>> 1;
            int uintCompare = UnsignedKt.uintCompare(iArr[i5], i);
            if (uintCompare < 0) {
                i2 = i5 + 1;
            } else if (uintCompare <= 0) {
                return i5;
            } else {
                i4 = i5 - 1;
            }
        }
        return -(i2 + 1);
    }

    /* renamed from: binarySearch-K6DWlUc$default reason: not valid java name */
    public static /* synthetic */ int m811binarySearchK6DWlUc$default(long[] jArr, long j, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = ULongArray.m685getSizeimpl(jArr);
        }
        return UArraysKt.m810binarySearchK6DWlUc(jArr, j, i, i2);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    /* renamed from: binarySearch-K6DWlUc reason: not valid java name */
    public static final int m810binarySearchK6DWlUc(@NotNull long[] jArr, long j, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(jArr, "$this$binarySearch");
        AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(i, i2, ULongArray.m685getSizeimpl(jArr));
        int i3 = i2 - 1;
        while (i <= i3) {
            int i4 = (i + i3) >>> 1;
            int ulongCompare = UnsignedKt.ulongCompare(jArr[i4], j);
            if (ulongCompare < 0) {
                i = i4 + 1;
            } else if (ulongCompare <= 0) {
                return i4;
            } else {
                i3 = i4 - 1;
            }
        }
        return -(i + 1);
    }

    /* renamed from: binarySearch-WpHrYlw$default reason: not valid java name */
    public static /* synthetic */ int m813binarySearchWpHrYlw$default(byte[] bArr, byte b, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = UByteArray.m547getSizeimpl(bArr);
        }
        return UArraysKt.m812binarySearchWpHrYlw(bArr, b, i, i2);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    /* renamed from: binarySearch-WpHrYlw reason: not valid java name */
    public static final int m812binarySearchWpHrYlw(@NotNull byte[] bArr, byte b, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(bArr, "$this$binarySearch");
        AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(i, i2, UByteArray.m547getSizeimpl(bArr));
        byte b2 = b & 255;
        int i3 = i2 - 1;
        while (i <= i3) {
            int i4 = (i + i3) >>> 1;
            int uintCompare = UnsignedKt.uintCompare(bArr[i4], b2);
            if (uintCompare < 0) {
                i = i4 + 1;
            } else if (uintCompare <= 0) {
                return i4;
            } else {
                i3 = i4 - 1;
            }
        }
        return -(i + 1);
    }

    /* renamed from: binarySearch-EtDCXyQ$default reason: not valid java name */
    public static /* synthetic */ int m809binarySearchEtDCXyQ$default(short[] sArr, short s, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = UShortArray.m752getSizeimpl(sArr);
        }
        return UArraysKt.m808binarySearchEtDCXyQ(sArr, s, i, i2);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    /* renamed from: binarySearch-EtDCXyQ reason: not valid java name */
    public static final int m808binarySearchEtDCXyQ(@NotNull short[] sArr, short s, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(sArr, "$this$binarySearch");
        AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(i, i2, UShortArray.m752getSizeimpl(sArr));
        short s2 = s & UShort.MAX_VALUE;
        int i3 = i2 - 1;
        while (i <= i3) {
            int i4 = (i + i3) >>> 1;
            int uintCompare = UnsignedKt.uintCompare(sArr[i4], s2);
            if (uintCompare < 0) {
                i = i4 + 1;
            } else if (uintCompare <= 0) {
                return i4;
            } else {
                i3 = i4 - 1;
            }
        }
        return -(i + 1);
    }

    /* renamed from: fill-2fe2U9s$default reason: not valid java name */
    public static /* synthetic */ void m819fill2fe2U9s$default(int[] iArr, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i2 = 0;
        }
        if ((i4 & 4) != 0) {
            i3 = UIntArray.m616getSizeimpl(iArr);
        }
        UArraysKt.m818fill2fe2U9s(iArr, i, i2, i3);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    /* renamed from: fill-2fe2U9s reason: not valid java name */
    public static final void m818fill2fe2U9s(@NotNull int[] iArr, int i, int i2, int i3) {
        Intrinsics.checkParameterIsNotNull(iArr, "$this$fill");
        ArraysKt.fill(iArr, i, i2, i3);
    }

    /* renamed from: fill-K6DWlUc$default reason: not valid java name */
    public static /* synthetic */ void m823fillK6DWlUc$default(long[] jArr, long j, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = ULongArray.m685getSizeimpl(jArr);
        }
        UArraysKt.m822fillK6DWlUc(jArr, j, i, i2);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    /* renamed from: fill-K6DWlUc reason: not valid java name */
    public static final void m822fillK6DWlUc(@NotNull long[] jArr, long j, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(jArr, "$this$fill");
        ArraysKt.fill(jArr, j, i, i2);
    }

    /* renamed from: fill-WpHrYlw$default reason: not valid java name */
    public static /* synthetic */ void m825fillWpHrYlw$default(byte[] bArr, byte b, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = UByteArray.m547getSizeimpl(bArr);
        }
        UArraysKt.m824fillWpHrYlw(bArr, b, i, i2);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    /* renamed from: fill-WpHrYlw reason: not valid java name */
    public static final void m824fillWpHrYlw(@NotNull byte[] bArr, byte b, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(bArr, "$this$fill");
        ArraysKt.fill(bArr, b, i, i2);
    }

    /* renamed from: fill-EtDCXyQ$default reason: not valid java name */
    public static /* synthetic */ void m821fillEtDCXyQ$default(short[] sArr, short s, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = UShortArray.m752getSizeimpl(sArr);
        }
        UArraysKt.m820fillEtDCXyQ(sArr, s, i, i2);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    /* renamed from: fill-EtDCXyQ reason: not valid java name */
    public static final void m820fillEtDCXyQ(@NotNull short[] sArr, short s, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(sArr, "$this$fill");
        ArraysKt.fill(sArr, s, i, i2);
    }
}
