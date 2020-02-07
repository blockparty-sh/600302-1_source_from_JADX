package com.bitcoin.mwallet.core.extensions;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import androidx.exifinterface.media.ExifInterface;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000V\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0010\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\u001a\f\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002\u001a\f\u0010\u0003\u001a\u0004\u0018\u00010\u0004*\u00020\u0002\u001a\r\u0010\u0005\u001a\u00020\u0006*\u00020\u0002H\b\u001a\f\u0010\u0007\u001a\u0004\u0018\u00010\b*\u00020\u0002\u001a&\u0010\t\u001a\u0004\u0018\u0001H\n\"\u0010\b\u0000\u0010\n\u0018\u0001*\b\u0012\u0004\u0012\u0002H\n0\u000b*\u00020\u0002H\b¢\u0006\u0002\u0010\f\u001a(\u0010\r\u001a\u0004\u0018\u0001H\n\"\u0004\b\u0000\u0010\n*\u00020\u00022\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\n0\u000fH\b¢\u0006\u0002\u0010\u0010\u001a)\u0010\u0011\u001a\u0004\u0018\u0001H\n\"\b\b\u0000\u0010\n*\u00020\u0012*\u00020\u00022\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\n0\u0014¢\u0006\u0002\u0010\u0015\u001a\u0014\u0010\u0016\u001a\u00020\u0017*\u00020\u00022\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001\u001a\u0014\u0010\u0019\u001a\u00020\u0017*\u00020\u00022\b\u0010\u0018\u001a\u0004\u0018\u00010\u0004\u001a\u0015\u0010\u001a\u001a\u00020\u0017*\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u0006H\b\u001a\u0014\u0010\u001b\u001a\u00020\u0017*\u00020\u00022\b\u0010\u0018\u001a\u0004\u0018\u00010\b\u001a,\u0010\u001c\u001a\u00020\u0017\"\u000e\b\u0000\u0010\n*\b\u0012\u0004\u0012\u0002H\n0\u000b*\u00020\u00022\b\u0010\u0018\u001a\u0004\u0018\u0001H\nH\b¢\u0006\u0002\u0010\u001d\u001a6\u0010\u001e\u001a\u00020\u0017\"\u0004\b\u0000\u0010\n*\u00020\u00022\b\u0010\u0018\u001a\u0004\u0018\u0001H\n2\u0012\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u00020\u00170 H\b¢\u0006\u0002\u0010!\u001a+\u0010\"\u001a\u00020\u0017\"\b\b\u0000\u0010\n*\u00020\u0012*\u00020\u00022\b\u0010\u0018\u001a\u0004\u0018\u0001H\n2\u0006\u0010#\u001a\u00020$¢\u0006\u0002\u0010%¨\u0006&"}, mo37405d2 = {"readBigDecimal", "Ljava/math/BigDecimal;", "Landroid/os/Parcel;", "readBigInteger", "Ljava/math/BigInteger;", "readBoolean", "", "readDate", "Ljava/util/Date;", "readEnum", "T", "", "(Landroid/os/Parcel;)Ljava/lang/Enum;", "readNullable", "reader", "Lkotlin/Function0;", "(Landroid/os/Parcel;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "readTypedObjectCompat", "Landroid/os/Parcelable;", "c", "Landroid/os/Parcelable$Creator;", "(Landroid/os/Parcel;Landroid/os/Parcelable$Creator;)Landroid/os/Parcelable;", "writeBigDecimal", "", "value", "writeBigInteger", "writeBoolean", "writeDate", "writeEnum", "(Landroid/os/Parcel;Ljava/lang/Enum;)V", "writeNullable", "writer", "Lkotlin/Function1;", "(Landroid/os/Parcel;Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)V", "writeTypedObjectCompat", "parcelableFlags", "", "(Landroid/os/Parcel;Landroid/os/Parcelable;I)V", "app_replaceRelease"}, mo37406k = 2, mo37407mv = {1, 1, 15})
/* compiled from: Parcel.kt */
public final class ParcelKt {
    public static final boolean readBoolean(@NotNull Parcel parcel) {
        Intrinsics.checkParameterIsNotNull(parcel, "$this$readBoolean");
        return parcel.readInt() != 0;
    }

    public static final void writeBoolean(@NotNull Parcel parcel, boolean z) {
        Intrinsics.checkParameterIsNotNull(parcel, "$this$writeBoolean");
        parcel.writeInt(z ? 1 : 0);
    }

    private static final <T extends Enum<T>> T readEnum(@NotNull Parcel parcel) {
        String readString = parcel.readString();
        if (readString == null) {
            return null;
        }
        Intrinsics.reifiedOperationMarker(5, ExifInterface.GPS_DIRECTION_TRUE);
        return Enum.valueOf(null, readString);
    }

    public static final <T extends Enum<T>> void writeEnum(@NotNull Parcel parcel, @Nullable T t) {
        Intrinsics.checkParameterIsNotNull(parcel, "$this$writeEnum");
        parcel.writeString(t != null ? t.name() : null);
    }

    @Nullable
    public static final <T> T readNullable(@NotNull Parcel parcel, @NotNull Function0<? extends T> function0) {
        Intrinsics.checkParameterIsNotNull(parcel, "$this$readNullable");
        Intrinsics.checkParameterIsNotNull(function0, "reader");
        if (parcel.readInt() != 0) {
            return function0.invoke();
        }
        return null;
    }

    public static final <T> void writeNullable(@NotNull Parcel parcel, @Nullable T t, @NotNull Function1<? super T, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(parcel, "$this$writeNullable");
        Intrinsics.checkParameterIsNotNull(function1, "writer");
        if (t != null) {
            parcel.writeInt(1);
            function1.invoke(t);
            return;
        }
        parcel.writeInt(0);
    }

    @Nullable
    public static final Date readDate(@NotNull Parcel parcel) {
        Intrinsics.checkParameterIsNotNull(parcel, "$this$readDate");
        if (parcel.readInt() != 0) {
            return new Date(parcel.readLong());
        }
        return null;
    }

    public static final void writeDate(@NotNull Parcel parcel, @Nullable Date date) {
        Intrinsics.checkParameterIsNotNull(parcel, "$this$writeDate");
        if (date != null) {
            parcel.writeInt(1);
            parcel.writeLong(date.getTime());
            return;
        }
        parcel.writeInt(0);
    }

    @Nullable
    public static final BigInteger readBigInteger(@NotNull Parcel parcel) {
        Intrinsics.checkParameterIsNotNull(parcel, "$this$readBigInteger");
        if (parcel.readInt() != 0) {
            return new BigInteger(parcel.createByteArray());
        }
        return null;
    }

    public static final void writeBigInteger(@NotNull Parcel parcel, @Nullable BigInteger bigInteger) {
        Intrinsics.checkParameterIsNotNull(parcel, "$this$writeBigInteger");
        if (bigInteger != null) {
            parcel.writeInt(1);
            parcel.writeByteArray(bigInteger.toByteArray());
            return;
        }
        parcel.writeInt(0);
    }

    @Nullable
    public static final BigDecimal readBigDecimal(@NotNull Parcel parcel) {
        Intrinsics.checkParameterIsNotNull(parcel, "$this$readBigDecimal");
        if (parcel.readInt() != 0) {
            return new BigDecimal(new BigInteger(parcel.createByteArray()), parcel.readInt());
        }
        return null;
    }

    public static final void writeBigDecimal(@NotNull Parcel parcel, @Nullable BigDecimal bigDecimal) {
        Intrinsics.checkParameterIsNotNull(parcel, "$this$writeBigDecimal");
        if (bigDecimal != null) {
            parcel.writeInt(1);
            parcel.writeByteArray(bigDecimal.unscaledValue().toByteArray());
            parcel.writeInt(bigDecimal.scale());
            return;
        }
        parcel.writeInt(0);
    }

    @Nullable
    public static final <T extends Parcelable> T readTypedObjectCompat(@NotNull Parcel parcel, @NotNull Creator<T> creator) {
        Intrinsics.checkParameterIsNotNull(parcel, "$this$readTypedObjectCompat");
        Intrinsics.checkParameterIsNotNull(creator, "c");
        if (parcel.readInt() != 0) {
            return (Parcelable) creator.createFromParcel(parcel);
        }
        return null;
    }

    public static final <T extends Parcelable> void writeTypedObjectCompat(@NotNull Parcel parcel, @Nullable T t, int i) {
        Intrinsics.checkParameterIsNotNull(parcel, "$this$writeTypedObjectCompat");
        if (t != null) {
            parcel.writeInt(1);
            t.writeToParcel(parcel, i);
            return;
        }
        parcel.writeInt(0);
    }
}
