package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import java.util.List;

@Class(creator = "AppMetadataCreator")
@Reserved({1, 20})
public final class zzn extends AbstractSafeParcelable {
    public static final Creator<zzn> CREATOR = new zzm();
    @Field(mo19055id = 2)
    public final String packageName;
    @Field(mo19055id = 3)
    public final String zzcg;
    @Field(mo19055id = 12)
    public final String zzci;
    @Field(mo19055id = 4)
    public final String zzcm;
    @Field(defaultValueUnchecked = "Integer.MIN_VALUE", mo19055id = 11)
    public final long zzcn;
    @Field(mo19055id = 5)
    public final String zzco;
    @Field(mo19055id = 7)
    public final long zzcp;
    @Field(defaultValue = "true", mo19055id = 9)
    public final boolean zzcq;
    @Field(mo19055id = 13)
    public final long zzcr;
    @Field(defaultValue = "true", mo19055id = 16)
    public final boolean zzcs;
    @Field(defaultValue = "true", mo19055id = 17)
    public final boolean zzct;
    @Field(mo19055id = 19)
    public final String zzcu;
    @Field(mo19055id = 21)
    public final Boolean zzcv;
    @Field(mo19055id = 23)
    public final List<String> zzcw;
    @Field(mo19055id = 8)
    public final String zzdp;
    @Field(mo19055id = 10)
    public final boolean zzdq;
    @Field(mo19055id = 14)
    public final long zzdr;
    @Field(mo19055id = 15)
    public final int zzds;
    @Field(mo19055id = 18)
    public final boolean zzdt;
    @Field(mo19055id = 6)
    public final long zzr;
    @Field(mo19055id = 22)
    public final long zzs;

    zzn(String str, String str2, String str3, long j, String str4, long j2, long j3, String str5, boolean z, boolean z2, String str6, long j4, long j5, int i, boolean z3, boolean z4, boolean z5, String str7, Boolean bool, long j6, List<String> list) {
        Preconditions.checkNotEmpty(str);
        this.packageName = str;
        this.zzcg = TextUtils.isEmpty(str2) ? null : str2;
        this.zzcm = str3;
        this.zzcn = j;
        this.zzco = str4;
        this.zzr = j2;
        this.zzcp = j3;
        this.zzdp = str5;
        this.zzcq = z;
        this.zzdq = z2;
        this.zzci = str6;
        this.zzcr = j4;
        this.zzdr = j5;
        this.zzds = i;
        this.zzcs = z3;
        this.zzct = z4;
        this.zzdt = z5;
        this.zzcu = str7;
        this.zzcv = bool;
        this.zzs = j6;
        this.zzcw = list;
    }

    @Constructor
    zzn(@Param(mo19058id = 2) String str, @Param(mo19058id = 3) String str2, @Param(mo19058id = 4) String str3, @Param(mo19058id = 5) String str4, @Param(mo19058id = 6) long j, @Param(mo19058id = 7) long j2, @Param(mo19058id = 8) String str5, @Param(mo19058id = 9) boolean z, @Param(mo19058id = 10) boolean z2, @Param(mo19058id = 11) long j3, @Param(mo19058id = 12) String str6, @Param(mo19058id = 13) long j4, @Param(mo19058id = 14) long j5, @Param(mo19058id = 15) int i, @Param(mo19058id = 16) boolean z3, @Param(mo19058id = 17) boolean z4, @Param(mo19058id = 18) boolean z5, @Param(mo19058id = 19) String str7, @Param(mo19058id = 21) Boolean bool, @Param(mo19058id = 22) long j6, @Param(mo19058id = 23) List<String> list) {
        this.packageName = str;
        this.zzcg = str2;
        this.zzcm = str3;
        this.zzcn = j3;
        this.zzco = str4;
        this.zzr = j;
        this.zzcp = j2;
        this.zzdp = str5;
        this.zzcq = z;
        this.zzdq = z2;
        this.zzci = str6;
        this.zzcr = j4;
        this.zzdr = j5;
        this.zzds = i;
        this.zzcs = z3;
        this.zzct = z4;
        this.zzdt = z5;
        this.zzcu = str7;
        this.zzcv = bool;
        this.zzs = j6;
        this.zzcw = list;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.packageName, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzcg, false);
        SafeParcelWriter.writeString(parcel, 4, this.zzcm, false);
        SafeParcelWriter.writeString(parcel, 5, this.zzco, false);
        SafeParcelWriter.writeLong(parcel, 6, this.zzr);
        SafeParcelWriter.writeLong(parcel, 7, this.zzcp);
        SafeParcelWriter.writeString(parcel, 8, this.zzdp, false);
        SafeParcelWriter.writeBoolean(parcel, 9, this.zzcq);
        SafeParcelWriter.writeBoolean(parcel, 10, this.zzdq);
        SafeParcelWriter.writeLong(parcel, 11, this.zzcn);
        SafeParcelWriter.writeString(parcel, 12, this.zzci, false);
        SafeParcelWriter.writeLong(parcel, 13, this.zzcr);
        SafeParcelWriter.writeLong(parcel, 14, this.zzdr);
        SafeParcelWriter.writeInt(parcel, 15, this.zzds);
        SafeParcelWriter.writeBoolean(parcel, 16, this.zzcs);
        SafeParcelWriter.writeBoolean(parcel, 17, this.zzct);
        SafeParcelWriter.writeBoolean(parcel, 18, this.zzdt);
        SafeParcelWriter.writeString(parcel, 19, this.zzcu, false);
        SafeParcelWriter.writeBooleanObject(parcel, 21, this.zzcv, false);
        SafeParcelWriter.writeLong(parcel, 22, this.zzs);
        SafeParcelWriter.writeStringList(parcel, 23, this.zzcw, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
