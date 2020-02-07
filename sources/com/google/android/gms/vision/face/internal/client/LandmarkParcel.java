package com.google.android.gms.vision.face.internal.client;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.apps.common.proguard.UsedByNative;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;

@UsedByNative("wrapper.cc")
@Class(creator = "LandmarkParcelCreator")
public final class LandmarkParcel extends AbstractSafeParcelable {
    public static final Creator<LandmarkParcel> CREATOR = new zzm();
    @Field(mo19055id = 4)
    public final int type;
    @VersionField(mo19061id = 1)
    private final int versionCode;
    @Field(mo19055id = 2)

    /* renamed from: x */
    public final float f497x;
    @Field(mo19055id = 3)

    /* renamed from: y */
    public final float f498y;

    @UsedByNative("wrapper.cc")
    @Constructor
    public LandmarkParcel(@Param(mo19058id = 1) int i, @Param(mo19058id = 2) float f, @Param(mo19058id = 3) float f2, @Param(mo19058id = 4) int i2) {
        this.versionCode = i;
        this.f497x = f;
        this.f498y = f2;
        this.type = i2;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.versionCode);
        SafeParcelWriter.writeFloat(parcel, 2, this.f497x);
        SafeParcelWriter.writeFloat(parcel, 3, this.f498y);
        SafeParcelWriter.writeInt(parcel, 4, this.type);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
