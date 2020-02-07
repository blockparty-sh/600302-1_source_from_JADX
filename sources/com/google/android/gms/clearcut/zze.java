package com.google.android.gms.clearcut;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.clearcut.ClearcutLogger.zzb;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.internal.clearcut.zzha;
import com.google.android.gms.internal.clearcut.zzr;
import com.google.android.gms.phenotype.ExperimentTokens;
import java.util.Arrays;

@Class(creator = "LogEventParcelableCreator")
@Reserved({1})
public final class zze extends AbstractSafeParcelable {
    public static final Creator<zze> CREATOR = new zzf();
    public final zzha zzaa;
    @Field(mo19055id = 2)
    public zzr zzag;
    @Field(mo19055id = 3)
    public byte[] zzah;
    @Field(mo19055id = 4)
    private int[] zzai;
    @Field(mo19055id = 5)
    private String[] zzaj;
    @Field(mo19055id = 6)
    private int[] zzak;
    @Field(mo19055id = 7)
    private byte[][] zzal;
    @Field(mo19055id = 9)
    private ExperimentTokens[] zzam;
    public final zzb zzan;
    public final zzb zzt;
    @Field(defaultValue = "true", mo19055id = 8)
    private boolean zzz;

    public zze(zzr zzr, zzha zzha, zzb zzb, zzb zzb2, int[] iArr, String[] strArr, int[] iArr2, byte[][] bArr, ExperimentTokens[] experimentTokensArr, boolean z) {
        this.zzag = zzr;
        this.zzaa = zzha;
        this.zzt = zzb;
        this.zzan = null;
        this.zzai = iArr;
        this.zzaj = null;
        this.zzak = iArr2;
        this.zzal = null;
        this.zzam = null;
        this.zzz = z;
    }

    @Constructor
    zze(@Param(mo19058id = 2) zzr zzr, @Param(mo19058id = 3) byte[] bArr, @Param(mo19058id = 4) int[] iArr, @Param(mo19058id = 5) String[] strArr, @Param(mo19058id = 6) int[] iArr2, @Param(mo19058id = 7) byte[][] bArr2, @Param(mo19058id = 8) boolean z, @Param(mo19058id = 9) ExperimentTokens[] experimentTokensArr) {
        this.zzag = zzr;
        this.zzah = bArr;
        this.zzai = iArr;
        this.zzaj = strArr;
        this.zzaa = null;
        this.zzt = null;
        this.zzan = null;
        this.zzak = iArr2;
        this.zzal = bArr2;
        this.zzam = experimentTokensArr;
        this.zzz = z;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zze) {
            zze zze = (zze) obj;
            return Objects.equal(this.zzag, zze.zzag) && Arrays.equals(this.zzah, zze.zzah) && Arrays.equals(this.zzai, zze.zzai) && Arrays.equals(this.zzaj, zze.zzaj) && Objects.equal(this.zzaa, zze.zzaa) && Objects.equal(this.zzt, zze.zzt) && Objects.equal(this.zzan, zze.zzan) && Arrays.equals(this.zzak, zze.zzak) && Arrays.deepEquals(this.zzal, zze.zzal) && Arrays.equals(this.zzam, zze.zzam) && this.zzz == zze.zzz;
        }
    }

    public final int hashCode() {
        return Objects.hashCode(this.zzag, this.zzah, this.zzai, this.zzaj, this.zzaa, this.zzt, this.zzan, this.zzak, this.zzal, this.zzam, Boolean.valueOf(this.zzz));
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("LogEventParcelable[");
        sb.append(this.zzag);
        sb.append(", LogEventBytes: ");
        byte[] bArr = this.zzah;
        sb.append(bArr == null ? null : new String(bArr));
        sb.append(", TestCodes: ");
        sb.append(Arrays.toString(this.zzai));
        sb.append(", MendelPackages: ");
        sb.append(Arrays.toString(this.zzaj));
        sb.append(", LogEvent: ");
        sb.append(this.zzaa);
        sb.append(", ExtensionProducer: ");
        sb.append(this.zzt);
        sb.append(", VeProducer: ");
        sb.append(this.zzan);
        sb.append(", ExperimentIDs: ");
        sb.append(Arrays.toString(this.zzak));
        sb.append(", ExperimentTokens: ");
        sb.append(Arrays.toString(this.zzal));
        sb.append(", ExperimentTokensParcelables: ");
        sb.append(Arrays.toString(this.zzam));
        sb.append(", AddPhenotypeExperimentTokens: ");
        sb.append(this.zzz);
        sb.append("]");
        return sb.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzag, i, false);
        SafeParcelWriter.writeByteArray(parcel, 3, this.zzah, false);
        SafeParcelWriter.writeIntArray(parcel, 4, this.zzai, false);
        SafeParcelWriter.writeStringArray(parcel, 5, this.zzaj, false);
        SafeParcelWriter.writeIntArray(parcel, 6, this.zzak, false);
        SafeParcelWriter.writeByteArrayArray(parcel, 7, this.zzal, false);
        SafeParcelWriter.writeBoolean(parcel, 8, this.zzz);
        SafeParcelWriter.writeTypedArray(parcel, 9, this.zzam, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
