package com.google.android.gms.common.stats;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import java.util.List;

@Class(creator = "WakeLockEventCreator")
public final class WakeLockEvent extends StatsEvent {
    public static final Creator<WakeLockEvent> CREATOR = new zza();
    private long durationMillis;
    @VersionField(mo19061id = 1)
    private final int versionCode;
    @Field(getter = "getTimeMillis", mo19055id = 2)
    private final long zzfo;
    @Field(getter = "getEventType", mo19055id = 11)
    private int zzfp;
    @Field(getter = "getWakeLockName", mo19055id = 4)
    private final String zzfq;
    @Field(getter = "getSecondaryWakeLockName", mo19055id = 10)
    private final String zzfr;
    @Field(getter = "getCodePackage", mo19055id = 17)
    private final String zzfs;
    @Field(getter = "getWakeLockType", mo19055id = 5)
    private final int zzft;
    @Field(getter = "getCallingPackages", mo19055id = 6)
    private final List<String> zzfu;
    @Field(getter = "getEventKey", mo19055id = 12)
    private final String zzfv;
    @Field(getter = "getElapsedRealtime", mo19055id = 8)
    private final long zzfw;
    @Field(getter = "getDeviceState", mo19055id = 14)
    private int zzfx;
    @Field(getter = "getHostPackage", mo19055id = 13)
    private final String zzfy;
    @Field(getter = "getBeginPowerPercentage", mo19055id = 15)
    private final float zzfz;
    @Field(getter = "getTimeout", mo19055id = 16)
    private final long zzga;
    @Field(getter = "getAcquiredWithTimeout", mo19055id = 18)
    private final boolean zzgb;

    @Constructor
    WakeLockEvent(@Param(mo19058id = 1) int i, @Param(mo19058id = 2) long j, @Param(mo19058id = 11) int i2, @Param(mo19058id = 4) String str, @Param(mo19058id = 5) int i3, @Param(mo19058id = 6) List<String> list, @Param(mo19058id = 12) String str2, @Param(mo19058id = 8) long j2, @Param(mo19058id = 14) int i4, @Param(mo19058id = 10) String str3, @Param(mo19058id = 13) String str4, @Param(mo19058id = 15) float f, @Param(mo19058id = 16) long j3, @Param(mo19058id = 17) String str5, @Param(mo19058id = 18) boolean z) {
        this.versionCode = i;
        this.zzfo = j;
        this.zzfp = i2;
        this.zzfq = str;
        this.zzfr = str3;
        this.zzfs = str5;
        this.zzft = i3;
        this.durationMillis = -1;
        this.zzfu = list;
        this.zzfv = str2;
        this.zzfw = j2;
        this.zzfx = i4;
        this.zzfy = str4;
        this.zzfz = f;
        this.zzga = j3;
        this.zzgb = z;
    }

    public WakeLockEvent(long j, int i, String str, int i2, List<String> list, String str2, long j2, int i3, String str3, String str4, float f, long j3, String str5, boolean z) {
        this(2, j, i, str, i2, list, str2, j2, i3, str3, str4, f, j3, str5, z);
    }

    public final long getTimeMillis() {
        return this.zzfo;
    }

    public final int getEventType() {
        return this.zzfp;
    }

    public final long zzu() {
        return this.durationMillis;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.versionCode);
        SafeParcelWriter.writeLong(parcel, 2, getTimeMillis());
        SafeParcelWriter.writeString(parcel, 4, this.zzfq, false);
        SafeParcelWriter.writeInt(parcel, 5, this.zzft);
        SafeParcelWriter.writeStringList(parcel, 6, this.zzfu, false);
        SafeParcelWriter.writeLong(parcel, 8, this.zzfw);
        SafeParcelWriter.writeString(parcel, 10, this.zzfr, false);
        SafeParcelWriter.writeInt(parcel, 11, getEventType());
        SafeParcelWriter.writeString(parcel, 12, this.zzfv, false);
        SafeParcelWriter.writeString(parcel, 13, this.zzfy, false);
        SafeParcelWriter.writeInt(parcel, 14, this.zzfx);
        SafeParcelWriter.writeFloat(parcel, 15, this.zzfz);
        SafeParcelWriter.writeLong(parcel, 16, this.zzga);
        SafeParcelWriter.writeString(parcel, 17, this.zzfs, false);
        SafeParcelWriter.writeBoolean(parcel, 18, this.zzgb);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final String zzv() {
        String str;
        String str2 = this.zzfq;
        int i = this.zzft;
        List<String> list = this.zzfu;
        String str3 = "";
        if (list == null) {
            str = str3;
        } else {
            str = TextUtils.join(",", list);
        }
        int i2 = this.zzfx;
        String str4 = this.zzfr;
        if (str4 == null) {
            str4 = str3;
        }
        String str5 = this.zzfy;
        if (str5 == null) {
            str5 = str3;
        }
        float f = this.zzfz;
        String str6 = this.zzfs;
        if (str6 != null) {
            str3 = str6;
        }
        boolean z = this.zzgb;
        StringBuilder sb = new StringBuilder(String.valueOf(str2).length() + 51 + String.valueOf(str).length() + String.valueOf(str4).length() + String.valueOf(str5).length() + String.valueOf(str3).length());
        String str7 = "\t";
        sb.append(str7);
        sb.append(str2);
        sb.append(str7);
        sb.append(i);
        sb.append(str7);
        sb.append(str);
        sb.append(str7);
        sb.append(i2);
        sb.append(str7);
        sb.append(str4);
        sb.append(str7);
        sb.append(str5);
        sb.append(str7);
        sb.append(f);
        sb.append(str7);
        sb.append(str3);
        sb.append(str7);
        sb.append(z);
        return sb.toString();
    }
}
