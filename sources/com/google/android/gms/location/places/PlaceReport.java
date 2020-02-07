package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import androidx.core.p003os.EnvironmentCompat;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Objects.ToStringHelper;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.firebase.analytics.FirebaseAnalytics;

@Class(creator = "PlaceReportCreator")
public class PlaceReport extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Creator<PlaceReport> CREATOR = new zza();
    @Field(getter = "getTag", mo19055id = 3)
    private final String tag;
    @VersionField(mo19061id = 1)
    private final int versionCode;
    @Field(getter = "getPlaceId", mo19055id = 2)
    private final String zza;
    @Field(getter = "getSource", mo19055id = 4)
    private final String zzb;

    @Constructor
    PlaceReport(@Param(mo19058id = 1) int i, @Param(mo19058id = 2) String str, @Param(mo19058id = 3) String str2, @Param(mo19058id = 4) String str3) {
        this.versionCode = i;
        this.zza = str;
        this.tag = str2;
        this.zzb = str3;
    }

    @VisibleForTesting
    public static PlaceReport create(String str, String str2) {
        char c;
        Preconditions.checkNotNull(str);
        Preconditions.checkNotEmpty(str2);
        String str3 = EnvironmentCompat.MEDIA_UNKNOWN;
        Preconditions.checkNotEmpty(str3);
        boolean z = false;
        switch (str3.hashCode()) {
            case -284840886:
                c = 0;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0 || c == 1 || c == 2 || c == 3 || c == 4 || c == 5) {
            z = true;
        }
        Preconditions.checkArgument(z, "Invalid source");
        return new PlaceReport(1, str, str2, str3);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof PlaceReport)) {
            return false;
        }
        PlaceReport placeReport = (PlaceReport) obj;
        return Objects.equal(this.zza, placeReport.zza) && Objects.equal(this.tag, placeReport.tag) && Objects.equal(this.zzb, placeReport.zzb);
    }

    public String getPlaceId() {
        return this.zza;
    }

    public String getTag() {
        return this.tag;
    }

    public int hashCode() {
        return Objects.hashCode(this.zza, this.tag, this.zzb);
    }

    public String toString() {
        ToStringHelper stringHelper = Objects.toStringHelper(this);
        stringHelper.add("placeId", this.zza);
        stringHelper.add("tag", this.tag);
        if (!EnvironmentCompat.MEDIA_UNKNOWN.equals(this.zzb)) {
            stringHelper.add(FirebaseAnalytics.Param.SOURCE, this.zzb);
        }
        return stringHelper.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.versionCode);
        SafeParcelWriter.writeString(parcel, 2, getPlaceId(), false);
        SafeParcelWriter.writeString(parcel, 3, getTag(), false);
        SafeParcelWriter.writeString(parcel, 4, this.zzb, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
