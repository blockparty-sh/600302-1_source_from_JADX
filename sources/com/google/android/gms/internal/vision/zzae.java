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

@Class(creator = "LineBoxParcelCreator")
@Reserved({1})
public final class zzae extends AbstractSafeParcelable {
    public static final Creator<zzae> CREATOR = new zzaf();
    @Field(mo19055id = 7)
    private final float zzdo;
    @Field(mo19055id = 8)
    public final String zzex;
    @Field(mo19055id = 2)
    public final zzan[] zzfc;
    @Field(mo19055id = 3)
    public final zzy zzfd;
    @Field(mo19055id = 4)
    private final zzy zzfe;
    @Field(mo19055id = 5)
    private final zzy zzff;
    @Field(mo19055id = 6)
    public final String zzfg;
    @Field(mo19055id = 9)
    private final int zzfh;
    @Field(mo19055id = 10)
    public final boolean zzfi;
    @Field(mo19055id = 11)
    public final int zzfj;
    @Field(mo19055id = 12)
    public final int zzfk;

    @Constructor
    public zzae(@Param(mo19058id = 2) zzan[] zzanArr, @Param(mo19058id = 3) zzy zzy, @Param(mo19058id = 4) zzy zzy2, @Param(mo19058id = 5) zzy zzy3, @Param(mo19058id = 6) String str, @Param(mo19058id = 7) float f, @Param(mo19058id = 8) String str2, @Param(mo19058id = 9) int i, @Param(mo19058id = 10) boolean z, @Param(mo19058id = 11) int i2, @Param(mo19058id = 12) int i3) {
        this.zzfc = zzanArr;
        this.zzfd = zzy;
        this.zzfe = zzy2;
        this.zzff = zzy3;
        this.zzfg = str;
        this.zzdo = f;
        this.zzex = str2;
        this.zzfh = i;
        this.zzfi = z;
        this.zzfj = i2;
        this.zzfk = i3;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedArray(parcel, 2, this.zzfc, i, false);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zzfd, i, false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzfe, i, false);
        SafeParcelWriter.writeParcelable(parcel, 5, this.zzff, i, false);
        SafeParcelWriter.writeString(parcel, 6, this.zzfg, false);
        SafeParcelWriter.writeFloat(parcel, 7, this.zzdo);
        SafeParcelWriter.writeString(parcel, 8, this.zzex, false);
        SafeParcelWriter.writeInt(parcel, 9, this.zzfh);
        SafeParcelWriter.writeBoolean(parcel, 10, this.zzfi);
        SafeParcelWriter.writeInt(parcel, 11, this.zzfj);
        SafeParcelWriter.writeInt(parcel, 12, this.zzfk);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
