package com.google.android.gms.internal.vision;

import java.io.IOException;

abstract class zzio<T, B> {
    zzio() {
    }

    /* access modifiers changed from: 0000 */
    public abstract void zza(B b, int i, long j);

    /* access modifiers changed from: 0000 */
    public abstract void zza(B b, int i, zzeo zzeo);

    /* access modifiers changed from: 0000 */
    public abstract void zza(B b, int i, T t);

    /* access modifiers changed from: 0000 */
    public abstract void zza(T t, zzjj zzjj) throws IOException;

    /* access modifiers changed from: 0000 */
    public abstract boolean zza(zzhv zzhv);

    /* access modifiers changed from: 0000 */
    public abstract void zzb(B b, int i, long j);

    /* access modifiers changed from: 0000 */
    public abstract void zzc(B b, int i, int i2);

    /* access modifiers changed from: 0000 */
    public abstract void zzc(T t, zzjj zzjj) throws IOException;

    /* access modifiers changed from: 0000 */
    public abstract void zze(Object obj);

    /* access modifiers changed from: 0000 */
    public abstract void zze(Object obj, T t);

    /* access modifiers changed from: 0000 */
    public abstract void zzf(Object obj, B b);

    /* access modifiers changed from: 0000 */
    public abstract T zzg(T t, T t2);

    /* access modifiers changed from: 0000 */
    public abstract B zzhd();

    /* access modifiers changed from: 0000 */
    public abstract T zzm(B b);

    /* access modifiers changed from: 0000 */
    public abstract int zzp(T t);

    /* access modifiers changed from: 0000 */
    public abstract T zzt(Object obj);

    /* access modifiers changed from: 0000 */
    public abstract B zzu(Object obj);

    /* access modifiers changed from: 0000 */
    public abstract int zzv(T t);

    /* access modifiers changed from: 0000 */
    public final boolean zza(B b, zzhv zzhv) throws IOException {
        int tag = zzhv.getTag();
        int i = tag >>> 3;
        int i2 = tag & 7;
        if (i2 == 0) {
            zza(b, i, zzhv.zzcq());
            return true;
        } else if (i2 == 1) {
            zzb(b, i, zzhv.zzcs());
            return true;
        } else if (i2 == 2) {
            zza(b, i, zzhv.zzcw());
            return true;
        } else if (i2 == 3) {
            Object zzhd = zzhd();
            int i3 = 4 | (i << 3);
            while (zzhv.zzcn() != Integer.MAX_VALUE) {
                if (!zza((B) zzhd, zzhv)) {
                    break;
                }
            }
            if (i3 == zzhv.getTag()) {
                zza(b, i, (T) zzm(zzhd));
                return true;
            }
            throw zzgf.zzfl();
        } else if (i2 == 4) {
            return false;
        } else {
            if (i2 == 5) {
                zzc(b, i, zzhv.zzct());
                return true;
            }
            throw zzgf.zzfm();
        }
    }
}
