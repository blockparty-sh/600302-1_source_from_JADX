package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;

@Class(creator = "UserAttributeParcelCreator")
public final class zzjn extends AbstractSafeParcelable {
    public static final Creator<zzjn> CREATOR = new zzjq();
    @Field(mo19055id = 2)
    public final String name;
    @Field(mo19055id = 7)
    public final String origin;
    @Field(mo19055id = 1)
    private final int versionCode;
    @Field(mo19055id = 6)
    public final String zzkr;
    @Field(mo19055id = 3)
    public final long zztr;
    @Field(mo19055id = 4)
    public final Long zzts;
    @Field(mo19055id = 5)
    private final Float zztt;
    @Field(mo19055id = 8)
    public final Double zztu;

    zzjn(zzjp zzjp) {
        this(zzjp.name, zzjp.zztr, zzjp.value, zzjp.origin);
    }

    zzjn(String str, long j, Object obj, String str2) {
        Preconditions.checkNotEmpty(str);
        this.versionCode = 2;
        this.name = str;
        this.zztr = j;
        this.origin = str2;
        if (obj == null) {
            this.zzts = null;
            this.zztt = null;
            this.zztu = null;
            this.zzkr = null;
        } else if (obj instanceof Long) {
            this.zzts = (Long) obj;
            this.zztt = null;
            this.zztu = null;
            this.zzkr = null;
        } else if (obj instanceof String) {
            this.zzts = null;
            this.zztt = null;
            this.zztu = null;
            this.zzkr = (String) obj;
        } else if (obj instanceof Double) {
            this.zzts = null;
            this.zztt = null;
            this.zztu = (Double) obj;
            this.zzkr = null;
        } else {
            throw new IllegalArgumentException("User attribute given of un-supported type");
        }
    }

    zzjn(String str, long j, String str2) {
        Preconditions.checkNotEmpty(str);
        this.versionCode = 2;
        this.name = str;
        this.zztr = 0;
        this.zzts = null;
        this.zztt = null;
        this.zztu = null;
        this.zzkr = null;
        this.origin = null;
    }

    @Constructor
    zzjn(@Param(mo19058id = 1) int i, @Param(mo19058id = 2) String str, @Param(mo19058id = 3) long j, @Param(mo19058id = 4) Long l, @Param(mo19058id = 5) Float f, @Param(mo19058id = 6) String str2, @Param(mo19058id = 7) String str3, @Param(mo19058id = 8) Double d) {
        this.versionCode = i;
        this.name = str;
        this.zztr = j;
        this.zzts = l;
        Double d2 = null;
        this.zztt = null;
        if (i == 1) {
            if (f != null) {
                d2 = Double.valueOf(f.doubleValue());
            }
            this.zztu = d2;
        } else {
            this.zztu = d;
        }
        this.zzkr = str2;
        this.origin = str3;
    }

    public final Object getValue() {
        Long l = this.zzts;
        if (l != null) {
            return l;
        }
        Double d = this.zztu;
        if (d != null) {
            return d;
        }
        String str = this.zzkr;
        if (str != null) {
            return str;
        }
        return null;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.versionCode);
        SafeParcelWriter.writeString(parcel, 2, this.name, false);
        SafeParcelWriter.writeLong(parcel, 3, this.zztr);
        SafeParcelWriter.writeLongObject(parcel, 4, this.zzts, false);
        SafeParcelWriter.writeFloatObject(parcel, 5, null, false);
        SafeParcelWriter.writeString(parcel, 6, this.zzkr, false);
        SafeParcelWriter.writeString(parcel, 7, this.origin, false);
        SafeParcelWriter.writeDoubleObject(parcel, 8, this.zztu, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
