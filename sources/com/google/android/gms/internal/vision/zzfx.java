package com.google.android.gms.internal.vision;

import com.google.android.gms.internal.vision.zzfy.zzg;

final class zzfx implements zzhe {
    private static final zzfx zzwi = new zzfx();

    private zzfx() {
    }

    public static zzfx zzex() {
        return zzwi;
    }

    public final boolean zzb(Class<?> cls) {
        return zzfy.class.isAssignableFrom(cls);
    }

    public final zzhd zzc(Class<?> cls) {
        if (!zzfy.class.isAssignableFrom(cls)) {
            String str = "Unsupported message type: ";
            String valueOf = String.valueOf(cls.getName());
            throw new IllegalArgumentException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        }
        try {
            return (zzhd) zzfy.zzd(cls.asSubclass(zzfy.class)).zza(zzg.zzwz, (Object) null, (Object) null);
        } catch (Exception e) {
            String str2 = "Unable to get message info for ";
            String valueOf2 = String.valueOf(cls.getName());
            throw new RuntimeException(valueOf2.length() != 0 ? str2.concat(valueOf2) : new String(str2), e);
        }
    }
}
