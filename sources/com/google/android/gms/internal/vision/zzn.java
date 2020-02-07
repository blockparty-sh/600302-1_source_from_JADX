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
import com.google.android.gms.vision.Frame;

@Class(creator = "FrameMetadataParcelCreator")
@Reserved({1})
public final class zzn extends AbstractSafeParcelable {
    public static final Creator<zzn> CREATOR = new zzo();
    @Field(mo19055id = 3)
    public int height;
    @Field(mo19055id = 4)

    /* renamed from: id */
    public int f493id;
    @Field(mo19055id = 6)
    public int rotation;
    @Field(mo19055id = 2)
    public int width;
    @Field(mo19055id = 5)
    public long zzat;

    public zzn() {
    }

    @Constructor
    public zzn(@Param(mo19058id = 2) int i, @Param(mo19058id = 3) int i2, @Param(mo19058id = 4) int i3, @Param(mo19058id = 5) long j, @Param(mo19058id = 6) int i4) {
        this.width = i;
        this.height = i2;
        this.f493id = i3;
        this.zzat = j;
        this.rotation = i4;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, this.width);
        SafeParcelWriter.writeInt(parcel, 3, this.height);
        SafeParcelWriter.writeInt(parcel, 4, this.f493id);
        SafeParcelWriter.writeLong(parcel, 5, this.zzat);
        SafeParcelWriter.writeInt(parcel, 6, this.rotation);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public static zzn zzc(Frame frame) {
        zzn zzn = new zzn();
        zzn.width = frame.getMetadata().getWidth();
        zzn.height = frame.getMetadata().getHeight();
        zzn.rotation = frame.getMetadata().getRotation();
        zzn.f493id = frame.getMetadata().getId();
        zzn.zzat = frame.getMetadata().getTimestampMillis();
        return zzn;
    }
}
