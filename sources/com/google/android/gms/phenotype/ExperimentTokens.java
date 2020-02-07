package com.google.android.gms.phenotype;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.Base64;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@KeepForSdk
@Class(creator = "ExperimentTokensCreator")
@Reserved({1})
public class ExperimentTokens extends AbstractSafeParcelable {
    @KeepForSdk
    public static final Creator<ExperimentTokens> CREATOR = new zzh();
    private static final zza zzaa = new zzg();
    private static final byte[][] zzn = new byte[0][];
    private static final ExperimentTokens zzo;
    private static final zza zzx = new zzd();
    private static final zza zzy = new zze();
    private static final zza zzz = new zzf();
    @Field(mo19055id = 2)
    private final String zzp;
    @Field(mo19055id = 3)
    private final byte[] zzq;
    @Field(mo19055id = 4)
    private final byte[][] zzr;
    @Field(mo19055id = 5)
    private final byte[][] zzs;
    @Field(mo19055id = 6)
    private final byte[][] zzt;
    @Field(mo19055id = 7)
    private final byte[][] zzu;
    @Field(mo19055id = 8)
    private final int[] zzv;
    @Field(mo19055id = 9)
    private final byte[][] zzw;

    private interface zza {
    }

    static {
        byte[][] bArr = zzn;
        ExperimentTokens experimentTokens = new ExperimentTokens("", null, bArr, bArr, bArr, bArr, null, null);
        zzo = experimentTokens;
    }

    @Constructor
    public ExperimentTokens(@Param(mo19058id = 2) String str, @Param(mo19058id = 3) byte[] bArr, @Param(mo19058id = 4) byte[][] bArr2, @Param(mo19058id = 5) byte[][] bArr3, @Param(mo19058id = 6) byte[][] bArr4, @Param(mo19058id = 7) byte[][] bArr5, @Param(mo19058id = 8) int[] iArr, @Param(mo19058id = 9) byte[][] bArr6) {
        this.zzp = str;
        this.zzq = bArr;
        this.zzr = bArr2;
        this.zzs = bArr3;
        this.zzt = bArr4;
        this.zzu = bArr5;
        this.zzv = iArr;
        this.zzw = bArr6;
    }

    private static List<Integer> zza(int[] iArr) {
        if (iArr == null) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(iArr.length);
        for (int valueOf : iArr) {
            arrayList.add(Integer.valueOf(valueOf));
        }
        Collections.sort(arrayList);
        return arrayList;
    }

    private static List<String> zza(byte[][] bArr) {
        if (bArr == null) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(bArr.length);
        for (byte[] encodeToString : bArr) {
            arrayList.add(Base64.encodeToString(encodeToString, 3));
        }
        Collections.sort(arrayList);
        return arrayList;
    }

    private static void zza(StringBuilder sb, String str, byte[][] bArr) {
        String str2;
        sb.append(str);
        sb.append("=");
        if (bArr == null) {
            str2 = "null";
        } else {
            sb.append("(");
            int length = bArr.length;
            int i = 0;
            boolean z = true;
            while (i < length) {
                byte[] bArr2 = bArr[i];
                if (!z) {
                    sb.append(", ");
                }
                String str3 = "'";
                sb.append(str3);
                sb.append(Base64.encodeToString(bArr2, 3));
                sb.append(str3);
                i++;
                z = false;
            }
            str2 = ")";
        }
        sb.append(str2);
    }

    public boolean equals(Object obj) {
        if (obj instanceof ExperimentTokens) {
            ExperimentTokens experimentTokens = (ExperimentTokens) obj;
            if (zzn.equals(this.zzp, experimentTokens.zzp) && Arrays.equals(this.zzq, experimentTokens.zzq) && zzn.equals(zza(this.zzr), zza(experimentTokens.zzr)) && zzn.equals(zza(this.zzs), zza(experimentTokens.zzs)) && zzn.equals(zza(this.zzt), zza(experimentTokens.zzt)) && zzn.equals(zza(this.zzu), zza(experimentTokens.zzu)) && zzn.equals(zza(this.zzv), zza(experimentTokens.zzv)) && zzn.equals(zza(this.zzw), zza(experimentTokens.zzw))) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder("ExperimentTokens");
        String str2 = "(";
        sb.append(str2);
        String str3 = this.zzp;
        String str4 = "null";
        String str5 = "'";
        if (str3 == null) {
            str = str4;
        } else {
            StringBuilder sb2 = new StringBuilder(String.valueOf(str3).length() + 2);
            sb2.append(str5);
            sb2.append(str3);
            sb2.append(str5);
            str = sb2.toString();
        }
        sb.append(str);
        String str6 = ", ";
        sb.append(str6);
        byte[] bArr = this.zzq;
        sb.append("direct");
        String str7 = "=";
        sb.append(str7);
        if (bArr == null) {
            sb.append(str4);
        } else {
            sb.append(str5);
            sb.append(Base64.encodeToString(bArr, 3));
            sb.append(str5);
        }
        sb.append(str6);
        zza(sb, "GAIA", this.zzr);
        sb.append(str6);
        zza(sb, "PSEUDO", this.zzs);
        sb.append(str6);
        zza(sb, "ALWAYS", this.zzt);
        sb.append(str6);
        zza(sb, "OTHER", this.zzu);
        sb.append(str6);
        int[] iArr = this.zzv;
        sb.append("weak");
        sb.append(str7);
        String str8 = ")";
        if (iArr == null) {
            sb.append(str4);
        } else {
            sb.append(str2);
            int length = iArr.length;
            int i = 0;
            boolean z = true;
            while (i < length) {
                int i2 = iArr[i];
                if (!z) {
                    sb.append(str6);
                }
                sb.append(i2);
                i++;
                z = false;
            }
            sb.append(str8);
        }
        sb.append(str6);
        zza(sb, "directs", this.zzw);
        sb.append(str8);
        return sb.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzp, false);
        SafeParcelWriter.writeByteArray(parcel, 3, this.zzq, false);
        SafeParcelWriter.writeByteArrayArray(parcel, 4, this.zzr, false);
        SafeParcelWriter.writeByteArrayArray(parcel, 5, this.zzs, false);
        SafeParcelWriter.writeByteArrayArray(parcel, 6, this.zzt, false);
        SafeParcelWriter.writeByteArrayArray(parcel, 7, this.zzu, false);
        SafeParcelWriter.writeIntArray(parcel, 8, this.zzv, false);
        SafeParcelWriter.writeByteArrayArray(parcel, 9, this.zzw, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
