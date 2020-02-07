package com.google.android.gms.internal.vision;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;

@Class(creator = "BoundingBoxParcelCreator")
@Reserved({1})
public final class zzy extends AbstractSafeParcelable {
    public static final Creator<zzy> CREATOR = new zzz();
    @Field(mo19055id = 5)
    public final int height;
    @Field(mo19055id = 2)
    public final int left;
    @Field(mo19055id = 3)
    public final int top;
    @Field(mo19055id = 4)
    public final int width;
    @Field(mo19055id = 6)
    public final float zzfb;

    @Constructor
    public zzy(@Param(mo19058id = 2) int i, @Param(mo19058id = 3) int i2, @Param(mo19058id = 4) int i3, @Param(mo19058id = 5) int i4, @Param(mo19058id = 6) float f) {
        this.left = i;
        this.top = i2;
        this.width = i3;
        this.height = i4;
        this.zzfb = f;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, this.left);
        SafeParcelWriter.writeInt(parcel, 3, this.top);
        SafeParcelWriter.writeInt(parcel, 4, this.width);
        SafeParcelWriter.writeInt(parcel, 5, this.height);
        SafeParcelWriter.writeFloat(parcel, 6, this.zzfb);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
