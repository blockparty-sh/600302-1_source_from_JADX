package com.google.android.gms.internal.vision;

import com.google.common.base.Ascii;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;

final class zzel extends zzej {
    private final byte[] buffer;
    private int limit;
    private int pos;
    private int tag;
    private final boolean zzrs = true;
    private final int zzrt;
    private int zzru;

    public zzel(ByteBuffer byteBuffer, boolean z) {
        super(null);
        this.buffer = byteBuffer.array();
        int arrayOffset = byteBuffer.arrayOffset() + byteBuffer.position();
        this.pos = arrayOffset;
        this.zzrt = arrayOffset;
        this.limit = byteBuffer.arrayOffset() + byteBuffer.limit();
    }

    private final boolean zzcm() {
        return this.pos == this.limit;
    }

    public final int zzcn() throws IOException {
        if (zzcm()) {
            return Integer.MAX_VALUE;
        }
        this.tag = zzdd();
        int i = this.tag;
        if (i == this.zzru) {
            return Integer.MAX_VALUE;
        }
        return i >>> 3;
    }

    public final int getTag() {
        return this.tag;
    }

    public final boolean zzco() throws IOException {
        if (!zzcm()) {
            int i = this.tag;
            int i2 = this.zzru;
            if (i != i2) {
                int i3 = i & 7;
                if (i3 == 0) {
                    int i4 = this.limit;
                    int i5 = this.pos;
                    if (i4 - i5 >= 10) {
                        byte[] bArr = this.buffer;
                        int i6 = i5;
                        int i7 = 0;
                        while (true) {
                            if (i7 >= 10) {
                                break;
                            }
                            int i8 = i6 + 1;
                            if (bArr[i6] >= 0) {
                                this.pos = i8;
                                break;
                            }
                            i7++;
                            i6 = i8;
                        }
                        return true;
                    }
                    for (int i9 = 0; i9 < 10; i9++) {
                        if (readByte() >= 0) {
                            return true;
                        }
                    }
                    throw zzgf.zzfj();
                } else if (i3 == 1) {
                    zzz(8);
                    return true;
                } else if (i3 == 2) {
                    zzz(zzdd());
                    return true;
                } else if (i3 == 3) {
                    this.zzru = ((i >>> 3) << 3) | 4;
                    while (zzcn() != Integer.MAX_VALUE) {
                        if (!zzco()) {
                            break;
                        }
                    }
                    if (this.tag == this.zzru) {
                        this.zzru = i2;
                        return true;
                    }
                    throw zzgf.zzfo();
                } else if (i3 == 5) {
                    zzz(4);
                    return true;
                } else {
                    throw zzgf.zzfm();
                }
            }
        }
        return false;
    }

    public final double readDouble() throws IOException {
        zzab(1);
        return Double.longBitsToDouble(zzdh());
    }

    public final float readFloat() throws IOException {
        zzab(5);
        return Float.intBitsToFloat(zzdg());
    }

    public final long zzcp() throws IOException {
        zzab(0);
        return zzde();
    }

    public final long zzcq() throws IOException {
        zzab(0);
        return zzde();
    }

    public final int zzcr() throws IOException {
        zzab(0);
        return zzdd();
    }

    public final long zzcs() throws IOException {
        zzab(1);
        return zzdh();
    }

    public final int zzct() throws IOException {
        zzab(5);
        return zzdg();
    }

    public final boolean zzcu() throws IOException {
        zzab(0);
        if (zzdd() != 0) {
            return true;
        }
        return false;
    }

    public final String readString() throws IOException {
        return zzg(false);
    }

    public final String zzcv() throws IOException {
        return zzg(true);
    }

    private final String zzg(boolean z) throws IOException {
        zzab(2);
        int zzdd = zzdd();
        if (zzdd == 0) {
            return "";
        }
        zzaa(zzdd);
        if (z) {
            byte[] bArr = this.buffer;
            int i = this.pos;
            if (!zziw.zzg(bArr, i, i + zzdd)) {
                throw zzgf.zzfp();
            }
        }
        String str = new String(this.buffer, this.pos, zzdd, zzga.UTF_8);
        this.pos += zzdd;
        return str;
    }

    public final <T> T zza(Class<T> cls, zzfk zzfk) throws IOException {
        zzab(2);
        return zzb(zzhs.zzgl().zzf(cls), zzfk);
    }

    public final <T> T zza(zzhw<T> zzhw, zzfk zzfk) throws IOException {
        zzab(2);
        return zzb(zzhw, zzfk);
    }

    private final <T> T zzb(zzhw<T> zzhw, zzfk zzfk) throws IOException {
        int zzdd = zzdd();
        zzaa(zzdd);
        int i = this.limit;
        int i2 = this.pos + zzdd;
        this.limit = i2;
        try {
            T newInstance = zzhw.newInstance();
            zzhw.zza(newInstance, this, zzfk);
            zzhw.zze(newInstance);
            if (this.pos == i2) {
                return newInstance;
            }
            throw zzgf.zzfo();
        } finally {
            this.limit = i;
        }
    }

    public final <T> T zzb(Class<T> cls, zzfk zzfk) throws IOException {
        zzab(3);
        return zzd(zzhs.zzgl().zzf(cls), zzfk);
    }

    public final <T> T zzc(zzhw<T> zzhw, zzfk zzfk) throws IOException {
        zzab(3);
        return zzd(zzhw, zzfk);
    }

    private final <T> T zzd(zzhw<T> zzhw, zzfk zzfk) throws IOException {
        int i = this.zzru;
        this.zzru = ((this.tag >>> 3) << 3) | 4;
        try {
            T newInstance = zzhw.newInstance();
            zzhw.zza(newInstance, this, zzfk);
            zzhw.zze(newInstance);
            if (this.tag == this.zzru) {
                return newInstance;
            }
            throw zzgf.zzfo();
        } finally {
            this.zzru = i;
        }
    }

    public final zzeo zzcw() throws IOException {
        zzeo zzeo;
        zzab(2);
        int zzdd = zzdd();
        if (zzdd == 0) {
            return zzeo.zzrx;
        }
        zzaa(zzdd);
        if (this.zzrs) {
            zzeo = zzeo.zzc(this.buffer, this.pos, zzdd);
        } else {
            zzeo = zzeo.zzb(this.buffer, this.pos, zzdd);
        }
        this.pos += zzdd;
        return zzeo;
    }

    public final int zzcx() throws IOException {
        zzab(0);
        return zzdd();
    }

    public final int zzcy() throws IOException {
        zzab(0);
        return zzdd();
    }

    public final int zzcz() throws IOException {
        zzab(5);
        return zzdg();
    }

    public final long zzda() throws IOException {
        zzab(1);
        return zzdh();
    }

    public final int zzdb() throws IOException {
        zzab(0);
        return zzez.zzaq(zzdd());
    }

    public final long zzdc() throws IOException {
        zzab(0);
        return zzez.zzd(zzde());
    }

    public final void zza(List<Double> list) throws IOException {
        int i;
        int i2;
        if (list instanceof zzfh) {
            zzfh zzfh = (zzfh) list;
            int i3 = this.tag & 7;
            if (i3 == 1) {
                do {
                    zzfh.zzc(readDouble());
                    if (!zzcm()) {
                        i2 = this.pos;
                    } else {
                        return;
                    }
                } while (zzdd() == this.tag);
                this.pos = i2;
            } else if (i3 == 2) {
                int zzdd = zzdd();
                zzac(zzdd);
                int i4 = this.pos + zzdd;
                while (this.pos < i4) {
                    zzfh.zzc(Double.longBitsToDouble(zzdj()));
                }
            } else {
                throw zzgf.zzfm();
            }
        } else {
            int i5 = this.tag & 7;
            if (i5 == 1) {
                do {
                    list.add(Double.valueOf(readDouble()));
                    if (!zzcm()) {
                        i = this.pos;
                    } else {
                        return;
                    }
                } while (zzdd() == this.tag);
                this.pos = i;
            } else if (i5 == 2) {
                int zzdd2 = zzdd();
                zzac(zzdd2);
                int i6 = this.pos + zzdd2;
                while (this.pos < i6) {
                    list.add(Double.valueOf(Double.longBitsToDouble(zzdj())));
                }
            } else {
                throw zzgf.zzfm();
            }
        }
    }

    public final void zzb(List<Float> list) throws IOException {
        int i;
        int i2;
        if (list instanceof zzfv) {
            zzfv zzfv = (zzfv) list;
            int i3 = this.tag & 7;
            if (i3 == 2) {
                int zzdd = zzdd();
                zzad(zzdd);
                int i4 = this.pos + zzdd;
                while (this.pos < i4) {
                    zzfv.zzh(Float.intBitsToFloat(zzdi()));
                }
            } else if (i3 == 5) {
                do {
                    zzfv.zzh(readFloat());
                    if (!zzcm()) {
                        i2 = this.pos;
                    } else {
                        return;
                    }
                } while (zzdd() == this.tag);
                this.pos = i2;
            } else {
                throw zzgf.zzfm();
            }
        } else {
            int i5 = this.tag & 7;
            if (i5 == 2) {
                int zzdd2 = zzdd();
                zzad(zzdd2);
                int i6 = this.pos + zzdd2;
                while (this.pos < i6) {
                    list.add(Float.valueOf(Float.intBitsToFloat(zzdi())));
                }
            } else if (i5 == 5) {
                do {
                    list.add(Float.valueOf(readFloat()));
                    if (!zzcm()) {
                        i = this.pos;
                    } else {
                        return;
                    }
                } while (zzdd() == this.tag);
                this.pos = i;
            } else {
                throw zzgf.zzfm();
            }
        }
    }

    public final void zzc(List<Long> list) throws IOException {
        int i;
        int i2;
        if (list instanceof zzgt) {
            zzgt zzgt = (zzgt) list;
            int i3 = this.tag & 7;
            if (i3 == 0) {
                do {
                    zzgt.zzp(zzcp());
                    if (!zzcm()) {
                        i2 = this.pos;
                    } else {
                        return;
                    }
                } while (zzdd() == this.tag);
                this.pos = i2;
            } else if (i3 == 2) {
                int zzdd = this.pos + zzdd();
                while (this.pos < zzdd) {
                    zzgt.zzp(zzde());
                }
                zzae(zzdd);
            } else {
                throw zzgf.zzfm();
            }
        } else {
            int i4 = this.tag & 7;
            if (i4 == 0) {
                do {
                    list.add(Long.valueOf(zzcp()));
                    if (!zzcm()) {
                        i = this.pos;
                    } else {
                        return;
                    }
                } while (zzdd() == this.tag);
                this.pos = i;
            } else if (i4 == 2) {
                int zzdd2 = this.pos + zzdd();
                while (this.pos < zzdd2) {
                    list.add(Long.valueOf(zzde()));
                }
                zzae(zzdd2);
            } else {
                throw zzgf.zzfm();
            }
        }
    }

    public final void zzd(List<Long> list) throws IOException {
        int i;
        int i2;
        if (list instanceof zzgt) {
            zzgt zzgt = (zzgt) list;
            int i3 = this.tag & 7;
            if (i3 == 0) {
                do {
                    zzgt.zzp(zzcq());
                    if (!zzcm()) {
                        i2 = this.pos;
                    } else {
                        return;
                    }
                } while (zzdd() == this.tag);
                this.pos = i2;
            } else if (i3 == 2) {
                int zzdd = this.pos + zzdd();
                while (this.pos < zzdd) {
                    zzgt.zzp(zzde());
                }
                zzae(zzdd);
            } else {
                throw zzgf.zzfm();
            }
        } else {
            int i4 = this.tag & 7;
            if (i4 == 0) {
                do {
                    list.add(Long.valueOf(zzcq()));
                    if (!zzcm()) {
                        i = this.pos;
                    } else {
                        return;
                    }
                } while (zzdd() == this.tag);
                this.pos = i;
            } else if (i4 == 2) {
                int zzdd2 = this.pos + zzdd();
                while (this.pos < zzdd2) {
                    list.add(Long.valueOf(zzde()));
                }
                zzae(zzdd2);
            } else {
                throw zzgf.zzfm();
            }
        }
    }

    public final void zze(List<Integer> list) throws IOException {
        int i;
        int i2;
        if (list instanceof zzfz) {
            zzfz zzfz = (zzfz) list;
            int i3 = this.tag & 7;
            if (i3 == 0) {
                do {
                    zzfz.zzbg(zzcr());
                    if (!zzcm()) {
                        i2 = this.pos;
                    } else {
                        return;
                    }
                } while (zzdd() == this.tag);
                this.pos = i2;
            } else if (i3 == 2) {
                int zzdd = this.pos + zzdd();
                while (this.pos < zzdd) {
                    zzfz.zzbg(zzdd());
                }
                zzae(zzdd);
            } else {
                throw zzgf.zzfm();
            }
        } else {
            int i4 = this.tag & 7;
            if (i4 == 0) {
                do {
                    list.add(Integer.valueOf(zzcr()));
                    if (!zzcm()) {
                        i = this.pos;
                    } else {
                        return;
                    }
                } while (zzdd() == this.tag);
                this.pos = i;
            } else if (i4 == 2) {
                int zzdd2 = this.pos + zzdd();
                while (this.pos < zzdd2) {
                    list.add(Integer.valueOf(zzdd()));
                }
                zzae(zzdd2);
            } else {
                throw zzgf.zzfm();
            }
        }
    }

    public final void zzf(List<Long> list) throws IOException {
        int i;
        int i2;
        if (list instanceof zzgt) {
            zzgt zzgt = (zzgt) list;
            int i3 = this.tag & 7;
            if (i3 == 1) {
                do {
                    zzgt.zzp(zzcs());
                    if (!zzcm()) {
                        i2 = this.pos;
                    } else {
                        return;
                    }
                } while (zzdd() == this.tag);
                this.pos = i2;
            } else if (i3 == 2) {
                int zzdd = zzdd();
                zzac(zzdd);
                int i4 = this.pos + zzdd;
                while (this.pos < i4) {
                    zzgt.zzp(zzdj());
                }
            } else {
                throw zzgf.zzfm();
            }
        } else {
            int i5 = this.tag & 7;
            if (i5 == 1) {
                do {
                    list.add(Long.valueOf(zzcs()));
                    if (!zzcm()) {
                        i = this.pos;
                    } else {
                        return;
                    }
                } while (zzdd() == this.tag);
                this.pos = i;
            } else if (i5 == 2) {
                int zzdd2 = zzdd();
                zzac(zzdd2);
                int i6 = this.pos + zzdd2;
                while (this.pos < i6) {
                    list.add(Long.valueOf(zzdj()));
                }
            } else {
                throw zzgf.zzfm();
            }
        }
    }

    public final void zzg(List<Integer> list) throws IOException {
        int i;
        int i2;
        if (list instanceof zzfz) {
            zzfz zzfz = (zzfz) list;
            int i3 = this.tag & 7;
            if (i3 == 2) {
                int zzdd = zzdd();
                zzad(zzdd);
                int i4 = this.pos + zzdd;
                while (this.pos < i4) {
                    zzfz.zzbg(zzdi());
                }
            } else if (i3 == 5) {
                do {
                    zzfz.zzbg(zzct());
                    if (!zzcm()) {
                        i2 = this.pos;
                    } else {
                        return;
                    }
                } while (zzdd() == this.tag);
                this.pos = i2;
            } else {
                throw zzgf.zzfm();
            }
        } else {
            int i5 = this.tag & 7;
            if (i5 == 2) {
                int zzdd2 = zzdd();
                zzad(zzdd2);
                int i6 = this.pos + zzdd2;
                while (this.pos < i6) {
                    list.add(Integer.valueOf(zzdi()));
                }
            } else if (i5 == 5) {
                do {
                    list.add(Integer.valueOf(zzct()));
                    if (!zzcm()) {
                        i = this.pos;
                    } else {
                        return;
                    }
                } while (zzdd() == this.tag);
                this.pos = i;
            } else {
                throw zzgf.zzfm();
            }
        }
    }

    public final void zzh(List<Boolean> list) throws IOException {
        int i;
        int i2;
        if (list instanceof zzem) {
            zzem zzem = (zzem) list;
            int i3 = this.tag & 7;
            if (i3 == 0) {
                do {
                    zzem.addBoolean(zzcu());
                    if (!zzcm()) {
                        i2 = this.pos;
                    } else {
                        return;
                    }
                } while (zzdd() == this.tag);
                this.pos = i2;
            } else if (i3 == 2) {
                int zzdd = this.pos + zzdd();
                while (this.pos < zzdd) {
                    zzem.addBoolean(zzdd() != 0);
                }
                zzae(zzdd);
            } else {
                throw zzgf.zzfm();
            }
        } else {
            int i4 = this.tag & 7;
            if (i4 == 0) {
                do {
                    list.add(Boolean.valueOf(zzcu()));
                    if (!zzcm()) {
                        i = this.pos;
                    } else {
                        return;
                    }
                } while (zzdd() == this.tag);
                this.pos = i;
            } else if (i4 == 2) {
                int zzdd2 = this.pos + zzdd();
                while (this.pos < zzdd2) {
                    list.add(Boolean.valueOf(zzdd() != 0));
                }
                zzae(zzdd2);
            } else {
                throw zzgf.zzfm();
            }
        }
    }

    public final void readStringList(List<String> list) throws IOException {
        zza(list, false);
    }

    public final void zzi(List<String> list) throws IOException {
        zza(list, true);
    }

    private final void zza(List<String> list, boolean z) throws IOException {
        int i;
        int i2;
        if ((this.tag & 7) != 2) {
            throw zzgf.zzfm();
        } else if (!(list instanceof zzgo) || z) {
            do {
                list.add(zzg(z));
                if (!zzcm()) {
                    i = this.pos;
                } else {
                    return;
                }
            } while (zzdd() == this.tag);
            this.pos = i;
        } else {
            zzgo zzgo = (zzgo) list;
            do {
                zzgo.zzc(zzcw());
                if (!zzcm()) {
                    i2 = this.pos;
                } else {
                    return;
                }
            } while (zzdd() == this.tag);
            this.pos = i2;
        }
    }

    public final <T> void zza(List<T> list, zzhw<T> zzhw, zzfk zzfk) throws IOException {
        int i;
        int i2 = this.tag;
        if ((i2 & 7) == 2) {
            do {
                list.add(zzb(zzhw, zzfk));
                if (!zzcm()) {
                    i = this.pos;
                } else {
                    return;
                }
            } while (zzdd() == i2);
            this.pos = i;
            return;
        }
        throw zzgf.zzfm();
    }

    public final <T> void zzb(List<T> list, zzhw<T> zzhw, zzfk zzfk) throws IOException {
        int i;
        int i2 = this.tag;
        if ((i2 & 7) == 3) {
            do {
                list.add(zzd(zzhw, zzfk));
                if (!zzcm()) {
                    i = this.pos;
                } else {
                    return;
                }
            } while (zzdd() == i2);
            this.pos = i;
            return;
        }
        throw zzgf.zzfm();
    }

    public final void zzj(List<zzeo> list) throws IOException {
        int i;
        if ((this.tag & 7) == 2) {
            do {
                list.add(zzcw());
                if (!zzcm()) {
                    i = this.pos;
                } else {
                    return;
                }
            } while (zzdd() == this.tag);
            this.pos = i;
            return;
        }
        throw zzgf.zzfm();
    }

    public final void zzk(List<Integer> list) throws IOException {
        int i;
        int i2;
        if (list instanceof zzfz) {
            zzfz zzfz = (zzfz) list;
            int i3 = this.tag & 7;
            if (i3 == 0) {
                do {
                    zzfz.zzbg(zzcx());
                    if (!zzcm()) {
                        i2 = this.pos;
                    } else {
                        return;
                    }
                } while (zzdd() == this.tag);
                this.pos = i2;
            } else if (i3 == 2) {
                int zzdd = this.pos + zzdd();
                while (this.pos < zzdd) {
                    zzfz.zzbg(zzdd());
                }
            } else {
                throw zzgf.zzfm();
            }
        } else {
            int i4 = this.tag & 7;
            if (i4 == 0) {
                do {
                    list.add(Integer.valueOf(zzcx()));
                    if (!zzcm()) {
                        i = this.pos;
                    } else {
                        return;
                    }
                } while (zzdd() == this.tag);
                this.pos = i;
            } else if (i4 == 2) {
                int zzdd2 = this.pos + zzdd();
                while (this.pos < zzdd2) {
                    list.add(Integer.valueOf(zzdd()));
                }
            } else {
                throw zzgf.zzfm();
            }
        }
    }

    public final void zzl(List<Integer> list) throws IOException {
        int i;
        int i2;
        if (list instanceof zzfz) {
            zzfz zzfz = (zzfz) list;
            int i3 = this.tag & 7;
            if (i3 == 0) {
                do {
                    zzfz.zzbg(zzcy());
                    if (!zzcm()) {
                        i2 = this.pos;
                    } else {
                        return;
                    }
                } while (zzdd() == this.tag);
                this.pos = i2;
            } else if (i3 == 2) {
                int zzdd = this.pos + zzdd();
                while (this.pos < zzdd) {
                    zzfz.zzbg(zzdd());
                }
            } else {
                throw zzgf.zzfm();
            }
        } else {
            int i4 = this.tag & 7;
            if (i4 == 0) {
                do {
                    list.add(Integer.valueOf(zzcy()));
                    if (!zzcm()) {
                        i = this.pos;
                    } else {
                        return;
                    }
                } while (zzdd() == this.tag);
                this.pos = i;
            } else if (i4 == 2) {
                int zzdd2 = this.pos + zzdd();
                while (this.pos < zzdd2) {
                    list.add(Integer.valueOf(zzdd()));
                }
            } else {
                throw zzgf.zzfm();
            }
        }
    }

    public final void zzm(List<Integer> list) throws IOException {
        int i;
        int i2;
        if (list instanceof zzfz) {
            zzfz zzfz = (zzfz) list;
            int i3 = this.tag & 7;
            if (i3 == 2) {
                int zzdd = zzdd();
                zzad(zzdd);
                int i4 = this.pos + zzdd;
                while (this.pos < i4) {
                    zzfz.zzbg(zzdi());
                }
            } else if (i3 == 5) {
                do {
                    zzfz.zzbg(zzcz());
                    if (!zzcm()) {
                        i2 = this.pos;
                    } else {
                        return;
                    }
                } while (zzdd() == this.tag);
                this.pos = i2;
            } else {
                throw zzgf.zzfm();
            }
        } else {
            int i5 = this.tag & 7;
            if (i5 == 2) {
                int zzdd2 = zzdd();
                zzad(zzdd2);
                int i6 = this.pos + zzdd2;
                while (this.pos < i6) {
                    list.add(Integer.valueOf(zzdi()));
                }
            } else if (i5 == 5) {
                do {
                    list.add(Integer.valueOf(zzcz()));
                    if (!zzcm()) {
                        i = this.pos;
                    } else {
                        return;
                    }
                } while (zzdd() == this.tag);
                this.pos = i;
            } else {
                throw zzgf.zzfm();
            }
        }
    }

    public final void zzn(List<Long> list) throws IOException {
        int i;
        int i2;
        if (list instanceof zzgt) {
            zzgt zzgt = (zzgt) list;
            int i3 = this.tag & 7;
            if (i3 == 1) {
                do {
                    zzgt.zzp(zzda());
                    if (!zzcm()) {
                        i2 = this.pos;
                    } else {
                        return;
                    }
                } while (zzdd() == this.tag);
                this.pos = i2;
            } else if (i3 == 2) {
                int zzdd = zzdd();
                zzac(zzdd);
                int i4 = this.pos + zzdd;
                while (this.pos < i4) {
                    zzgt.zzp(zzdj());
                }
            } else {
                throw zzgf.zzfm();
            }
        } else {
            int i5 = this.tag & 7;
            if (i5 == 1) {
                do {
                    list.add(Long.valueOf(zzda()));
                    if (!zzcm()) {
                        i = this.pos;
                    } else {
                        return;
                    }
                } while (zzdd() == this.tag);
                this.pos = i;
            } else if (i5 == 2) {
                int zzdd2 = zzdd();
                zzac(zzdd2);
                int i6 = this.pos + zzdd2;
                while (this.pos < i6) {
                    list.add(Long.valueOf(zzdj()));
                }
            } else {
                throw zzgf.zzfm();
            }
        }
    }

    public final void zzo(List<Integer> list) throws IOException {
        int i;
        int i2;
        if (list instanceof zzfz) {
            zzfz zzfz = (zzfz) list;
            int i3 = this.tag & 7;
            if (i3 == 0) {
                do {
                    zzfz.zzbg(zzdb());
                    if (!zzcm()) {
                        i2 = this.pos;
                    } else {
                        return;
                    }
                } while (zzdd() == this.tag);
                this.pos = i2;
            } else if (i3 == 2) {
                int zzdd = this.pos + zzdd();
                while (this.pos < zzdd) {
                    zzfz.zzbg(zzez.zzaq(zzdd()));
                }
            } else {
                throw zzgf.zzfm();
            }
        } else {
            int i4 = this.tag & 7;
            if (i4 == 0) {
                do {
                    list.add(Integer.valueOf(zzdb()));
                    if (!zzcm()) {
                        i = this.pos;
                    } else {
                        return;
                    }
                } while (zzdd() == this.tag);
                this.pos = i;
            } else if (i4 == 2) {
                int zzdd2 = this.pos + zzdd();
                while (this.pos < zzdd2) {
                    list.add(Integer.valueOf(zzez.zzaq(zzdd())));
                }
            } else {
                throw zzgf.zzfm();
            }
        }
    }

    public final void zzp(List<Long> list) throws IOException {
        int i;
        int i2;
        if (list instanceof zzgt) {
            zzgt zzgt = (zzgt) list;
            int i3 = this.tag & 7;
            if (i3 == 0) {
                do {
                    zzgt.zzp(zzdc());
                    if (!zzcm()) {
                        i2 = this.pos;
                    } else {
                        return;
                    }
                } while (zzdd() == this.tag);
                this.pos = i2;
            } else if (i3 == 2) {
                int zzdd = this.pos + zzdd();
                while (this.pos < zzdd) {
                    zzgt.zzp(zzez.zzd(zzde()));
                }
            } else {
                throw zzgf.zzfm();
            }
        } else {
            int i4 = this.tag & 7;
            if (i4 == 0) {
                do {
                    list.add(Long.valueOf(zzdc()));
                    if (!zzcm()) {
                        i = this.pos;
                    } else {
                        return;
                    }
                } while (zzdd() == this.tag);
                this.pos = i;
            } else if (i4 == 2) {
                int zzdd2 = this.pos + zzdd();
                while (this.pos < zzdd2) {
                    list.add(Long.valueOf(zzez.zzd(zzde())));
                }
            } else {
                throw zzgf.zzfm();
            }
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(3:17|18|(2:20|36)(3:31|21|22)) */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x004c, code lost:
        if (zzco() != false) goto L_0x004e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0054, code lost:
        throw new com.google.android.gms.internal.vision.zzgf(r6);
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0048 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final <K, V> void zza(java.util.Map<K, V> r8, com.google.android.gms.internal.vision.zzgy<K, V> r9, com.google.android.gms.internal.vision.zzfk r10) throws java.io.IOException {
        /*
            r7 = this;
            r0 = 2
            r7.zzab(r0)
            int r1 = r7.zzdd()
            r7.zzaa(r1)
            int r2 = r7.limit
            int r3 = r7.pos
            int r3 = r3 + r1
            r7.limit = r3
            K r1 = r9.zzyw     // Catch:{ all -> 0x005b }
            V r3 = r9.zzgq     // Catch:{ all -> 0x005b }
        L_0x0016:
            int r4 = r7.zzcn()     // Catch:{ all -> 0x005b }
            r5 = 2147483647(0x7fffffff, float:NaN)
            if (r4 == r5) goto L_0x0055
            r5 = 1
            java.lang.String r6 = "Unable to parse map entry."
            if (r4 == r5) goto L_0x0040
            if (r4 == r0) goto L_0x0033
            boolean r4 = r7.zzco()     // Catch:{ zzgg -> 0x0048 }
            if (r4 == 0) goto L_0x002d
            goto L_0x0016
        L_0x002d:
            com.google.android.gms.internal.vision.zzgf r4 = new com.google.android.gms.internal.vision.zzgf     // Catch:{ zzgg -> 0x0048 }
            r4.<init>(r6)     // Catch:{ zzgg -> 0x0048 }
            throw r4     // Catch:{ zzgg -> 0x0048 }
        L_0x0033:
            com.google.android.gms.internal.vision.zzjd r4 = r9.zzyx     // Catch:{ zzgg -> 0x0048 }
            V r5 = r9.zzgq     // Catch:{ zzgg -> 0x0048 }
            java.lang.Class r5 = r5.getClass()     // Catch:{ zzgg -> 0x0048 }
            java.lang.Object r3 = r7.zza(r4, r5, r10)     // Catch:{ zzgg -> 0x0048 }
            goto L_0x0016
        L_0x0040:
            com.google.android.gms.internal.vision.zzjd r4 = r9.zzyv     // Catch:{ zzgg -> 0x0048 }
            r5 = 0
            java.lang.Object r1 = r7.zza(r4, r5, r5)     // Catch:{ zzgg -> 0x0048 }
            goto L_0x0016
        L_0x0048:
            boolean r4 = r7.zzco()     // Catch:{ all -> 0x005b }
            if (r4 == 0) goto L_0x004f
            goto L_0x0016
        L_0x004f:
            com.google.android.gms.internal.vision.zzgf r8 = new com.google.android.gms.internal.vision.zzgf     // Catch:{ all -> 0x005b }
            r8.<init>(r6)     // Catch:{ all -> 0x005b }
            throw r8     // Catch:{ all -> 0x005b }
        L_0x0055:
            r8.put(r1, r3)     // Catch:{ all -> 0x005b }
            r7.limit = r2
            return
        L_0x005b:
            r8 = move-exception
            r7.limit = r2
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzel.zza(java.util.Map, com.google.android.gms.internal.vision.zzgy, com.google.android.gms.internal.vision.zzfk):void");
    }

    private final Object zza(zzjd zzjd, Class<?> cls, zzfk zzfk) throws IOException {
        switch (zzjd) {
            case BOOL:
                return Boolean.valueOf(zzcu());
            case BYTES:
                return zzcw();
            case DOUBLE:
                return Double.valueOf(readDouble());
            case ENUM:
                return Integer.valueOf(zzcy());
            case FIXED32:
                return Integer.valueOf(zzct());
            case FIXED64:
                return Long.valueOf(zzcs());
            case FLOAT:
                return Float.valueOf(readFloat());
            case INT32:
                return Integer.valueOf(zzcr());
            case INT64:
                return Long.valueOf(zzcq());
            case MESSAGE:
                return zza(cls, zzfk);
            case SFIXED32:
                return Integer.valueOf(zzcz());
            case SFIXED64:
                return Long.valueOf(zzda());
            case SINT32:
                return Integer.valueOf(zzdb());
            case SINT64:
                return Long.valueOf(zzdc());
            case STRING:
                return zzg(true);
            case UINT32:
                return Integer.valueOf(zzcx());
            case UINT64:
                return Long.valueOf(zzcp());
            default:
                throw new RuntimeException("unsupported field type.");
        }
    }

    private final int zzdd() throws IOException {
        byte b;
        int i = this.pos;
        int i2 = this.limit;
        if (i2 != i) {
            byte[] bArr = this.buffer;
            int i3 = i + 1;
            byte b2 = bArr[i];
            if (b2 >= 0) {
                this.pos = i3;
                return b2;
            } else if (i2 - i3 < 9) {
                return (int) zzdf();
            } else {
                int i4 = i3 + 1;
                byte b3 = b2 ^ (bArr[i3] << 7);
                if (b3 < 0) {
                    b = b3 ^ Byte.MIN_VALUE;
                } else {
                    int i5 = i4 + 1;
                    byte b4 = b3 ^ (bArr[i4] << Ascii.f529SO);
                    if (b4 >= 0) {
                        b = b4 ^ 16256;
                    } else {
                        i4 = i5 + 1;
                        byte b5 = b4 ^ (bArr[i5] << Ascii.NAK);
                        if (b5 < 0) {
                            b = b5 ^ -2080896;
                        } else {
                            i5 = i4 + 1;
                            byte b6 = bArr[i4];
                            b = (b5 ^ (b6 << Ascii.f522FS)) ^ 266354560;
                            if (b6 < 0) {
                                i4 = i5 + 1;
                                if (bArr[i5] < 0) {
                                    i5 = i4 + 1;
                                    if (bArr[i4] < 0) {
                                        i4 = i5 + 1;
                                        if (bArr[i5] < 0) {
                                            i5 = i4 + 1;
                                            if (bArr[i4] < 0) {
                                                i4 = i5 + 1;
                                                if (bArr[i5] < 0) {
                                                    throw zzgf.zzfj();
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    i4 = i5;
                }
                this.pos = i4;
                return b;
            }
        } else {
            throw zzgf.zzfh();
        }
    }

    private final long zzde() throws IOException {
        long j;
        int i;
        long j2;
        long j3;
        byte b;
        int i2 = this.pos;
        int i3 = this.limit;
        if (i3 != i2) {
            byte[] bArr = this.buffer;
            int i4 = i2 + 1;
            byte b2 = bArr[i2];
            if (b2 >= 0) {
                this.pos = i4;
                return (long) b2;
            } else if (i3 - i4 < 9) {
                return zzdf();
            } else {
                int i5 = i4 + 1;
                byte b3 = b2 ^ (bArr[i4] << 7);
                if (b3 < 0) {
                    b = b3 ^ Byte.MIN_VALUE;
                } else {
                    int i6 = i5 + 1;
                    byte b4 = b3 ^ (bArr[i5] << Ascii.f529SO);
                    if (b4 >= 0) {
                        i = i6;
                        j = (long) (b4 ^ 16256);
                    } else {
                        i5 = i6 + 1;
                        byte b5 = b4 ^ (bArr[i6] << Ascii.NAK);
                        if (b5 < 0) {
                            b = b5 ^ -2080896;
                        } else {
                            long j4 = (long) b5;
                            int i7 = i5 + 1;
                            long j5 = j4 ^ (((long) bArr[i5]) << 28);
                            if (j5 >= 0) {
                                j3 = 266354560;
                            } else {
                                int i8 = i7 + 1;
                                long j6 = j5 ^ (((long) bArr[i7]) << 35);
                                if (j6 < 0) {
                                    j2 = -34093383808L;
                                } else {
                                    i7 = i8 + 1;
                                    j5 = j6 ^ (((long) bArr[i8]) << 42);
                                    if (j5 >= 0) {
                                        j3 = 4363953127296L;
                                    } else {
                                        i8 = i7 + 1;
                                        j6 = j5 ^ (((long) bArr[i7]) << 49);
                                        if (j6 < 0) {
                                            j2 = -558586000294016L;
                                        } else {
                                            int i9 = i8 + 1;
                                            long j7 = (j6 ^ (((long) bArr[i8]) << 56)) ^ 71499008037633920L;
                                            if (j7 < 0) {
                                                i = i9 + 1;
                                                if (((long) bArr[i9]) < 0) {
                                                    throw zzgf.zzfj();
                                                }
                                            } else {
                                                i = i9;
                                            }
                                            j = j7;
                                        }
                                    }
                                }
                                j = j6 ^ j2;
                            }
                            j = j5 ^ j3;
                            i = i7;
                        }
                    }
                    this.pos = i;
                    return j;
                }
                j = (long) b;
                this.pos = i;
                return j;
            }
        } else {
            throw zzgf.zzfh();
        }
    }

    private final long zzdf() throws IOException {
        long j = 0;
        for (int i = 0; i < 64; i += 7) {
            byte readByte = readByte();
            j |= ((long) (readByte & Byte.MAX_VALUE)) << i;
            if ((readByte & 128) == 0) {
                return j;
            }
        }
        throw zzgf.zzfj();
    }

    private final byte readByte() throws IOException {
        int i = this.pos;
        if (i != this.limit) {
            byte[] bArr = this.buffer;
            this.pos = i + 1;
            return bArr[i];
        }
        throw zzgf.zzfh();
    }

    private final int zzdg() throws IOException {
        zzaa(4);
        return zzdi();
    }

    private final long zzdh() throws IOException {
        zzaa(8);
        return zzdj();
    }

    private final int zzdi() {
        int i = this.pos;
        byte[] bArr = this.buffer;
        this.pos = i + 4;
        return ((bArr[i + 3] & 255) << Ascii.CAN) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
    }

    private final long zzdj() {
        int i = this.pos;
        byte[] bArr = this.buffer;
        this.pos = i + 8;
        return ((((long) bArr[i + 7]) & 255) << 56) | (((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8) | ((((long) bArr[i + 2]) & 255) << 16) | ((((long) bArr[i + 3]) & 255) << 24) | ((((long) bArr[i + 4]) & 255) << 32) | ((((long) bArr[i + 5]) & 255) << 40) | ((((long) bArr[i + 6]) & 255) << 48);
    }

    private final void zzz(int i) throws IOException {
        zzaa(i);
        this.pos += i;
    }

    private final void zzaa(int i) throws IOException {
        if (i < 0 || i > this.limit - this.pos) {
            throw zzgf.zzfh();
        }
    }

    private final void zzab(int i) throws IOException {
        if ((this.tag & 7) != i) {
            throw zzgf.zzfm();
        }
    }

    private final void zzac(int i) throws IOException {
        zzaa(i);
        if ((i & 7) != 0) {
            throw zzgf.zzfo();
        }
    }

    private final void zzad(int i) throws IOException {
        zzaa(i);
        if ((i & 3) != 0) {
            throw zzgf.zzfo();
        }
    }

    private final void zzae(int i) throws IOException {
        if (this.pos != i) {
            throw zzgf.zzfh();
        }
    }
}
