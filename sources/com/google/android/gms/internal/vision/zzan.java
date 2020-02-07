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

@Class(creator = "WordBoxParcelCreator")
@Reserved({1})
public final class zzan extends AbstractSafeParcelable {
    public static final Creator<zzan> CREATOR = new zzao();
    @Field(mo19055id = 6)
    private final float zzdo;
    @Field(mo19055id = 7)
    public final String zzex;
    @Field(mo19055id = 3)
    public final zzy zzfd;
    @Field(mo19055id = 4)
    private final zzy zzfe;
    @Field(mo19055id = 5)
    public final String zzfg;
    @Field(mo19055id = 2)
    private final zzai[] zzfm;
    @Field(mo19055id = 8)
    private final boolean zzfn;

    @Constructor
    public zzan(@Param(mo19058id = 2) zzai[] zzaiArr, @Param(mo19058id = 3) zzy zzy, @Param(mo19058id = 4) zzy zzy2, @Param(mo19058id = 5) String str, @Param(mo19058id = 6) float f, @Param(mo19058id = 7) String str2, @Param(mo19058id = 8) boolean z) {
        this.zzfm = zzaiArr;
        this.zzfd = zzy;
        this.zzfe = zzy2;
        this.zzfg = str;
        this.zzdo = f;
        this.zzex = str2;
        this.zzfn = z;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedArray(parcel, 2, this.zzfm, i, false);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zzfd, i, false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzfe, i, false);
        SafeParcelWriter.writeString(parcel, 5, this.zzfg, false);
        SafeParcelWriter.writeFloat(parcel, 6, this.zzdo);
        SafeParcelWriter.writeString(parcel, 7, this.zzex, false);
        SafeParcelWriter.writeBoolean(parcel, 8, this.zzfn);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
