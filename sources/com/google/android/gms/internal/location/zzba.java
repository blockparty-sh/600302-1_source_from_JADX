package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import android.util.Log;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;
import com.google.android.gms.location.LocationStatusCodes;

final class zzba extends zzan {
    private ResultHolder<Status> zzdf;

    public zzba(ResultHolder<Status> resultHolder) {
        this.zzdf = resultHolder;
    }

    public final void zza(int i, PendingIntent pendingIntent) {
        Log.wtf("LocationClientImpl", "Unexpected call to onRemoveGeofencesByPendingIntentResult");
    }

    public final void zza(int i, String[] strArr) {
        if (this.zzdf == null) {
            Log.wtf("LocationClientImpl", "onAddGeofenceResult called multiple times");
            return;
        }
        this.zzdf.setResult(LocationStatusCodes.zzd(LocationStatusCodes.zzc(i)));
        this.zzdf = null;
    }

    public final void zzb(int i, String[] strArr) {
        Log.wtf("LocationClientImpl", "Unexpected call to onRemoveGeofencesByRequestIdsResult");
    }
}
