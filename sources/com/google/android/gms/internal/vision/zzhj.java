package com.google.android.gms.internal.vision;

import com.google.android.gms.internal.vision.zzfy.zzg;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.spongycastle.asn1.cmp.PKIFailureInfo;
import sun.misc.Unsafe;

final class zzhj<T> implements zzhw<T> {
    private static final int[] zzzb = new int[0];
    private static final Unsafe zzzc = zziu.zzhj();
    private final int[] zzzd;
    private final Object[] zzze;
    private final int zzzf;
    private final int zzzg;
    private final zzhf zzzh;
    private final boolean zzzi;
    private final boolean zzzj;
    private final boolean zzzk;
    private final boolean zzzl;
    private final int[] zzzm;
    private final int zzzn;
    private final int zzzo;
    private final zzhn zzzp;
    private final zzgp zzzq;
    private final zzio<?, ?> zzzr;
    private final zzfl<?> zzzs;
    private final zzha zzzt;

    private zzhj(int[] iArr, Object[] objArr, int i, int i2, zzhf zzhf, boolean z, boolean z2, int[] iArr2, int i3, int i4, zzhn zzhn, zzgp zzgp, zzio<?, ?> zzio, zzfl<?> zzfl, zzha zzha) {
        this.zzzd = iArr;
        this.zzze = objArr;
        this.zzzf = i;
        this.zzzg = i2;
        this.zzzj = zzhf instanceof zzfy;
        this.zzzk = z;
        this.zzzi = zzfl != null && zzfl.zze(zzhf);
        this.zzzl = false;
        this.zzzm = iArr2;
        this.zzzn = i3;
        this.zzzo = i4;
        this.zzzp = zzhn;
        this.zzzq = zzgp;
        this.zzzr = zzio;
        this.zzzs = zzfl;
        this.zzzh = zzhf;
        this.zzzt = zzha;
    }

    private static boolean zzbm(int i) {
        return (i & PKIFailureInfo.duplicateCertReq) != 0;
    }

    static <T> zzhj<T> zza(Class<T> cls, zzhd zzhd, zzhn zzhn, zzgp zzgp, zzio<?, ?> zzio, zzfl<?> zzfl, zzha zzha) {
        int i;
        int i2;
        int i3;
        int[] iArr;
        int i4;
        int i5;
        int i6;
        int i7;
        char c;
        int i8;
        int i9;
        String str;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        Class cls2;
        int i17;
        int i18;
        Field field;
        int i19;
        char charAt;
        int i20;
        char c2;
        Field field2;
        Field field3;
        int i21;
        char charAt2;
        int i22;
        char charAt3;
        int i23;
        char charAt4;
        int i24;
        int i25;
        int i26;
        int i27;
        int i28;
        int i29;
        char charAt5;
        int i30;
        char charAt6;
        int i31;
        char charAt7;
        int i32;
        char charAt8;
        char charAt9;
        char charAt10;
        char charAt11;
        char charAt12;
        char charAt13;
        char charAt14;
        zzhd zzhd2 = zzhd;
        if (zzhd2 instanceof zzhu) {
            zzhu zzhu = (zzhu) zzhd2;
            char c3 = 0;
            boolean z = zzhu.zzge() == zzg.zzxg;
            String zzgn = zzhu.zzgn();
            int length = zzgn.length();
            char charAt15 = zzgn.charAt(0);
            if (charAt15 >= 55296) {
                char c4 = charAt15 & 8191;
                int i33 = 1;
                int i34 = 13;
                while (true) {
                    i = i33 + 1;
                    charAt14 = zzgn.charAt(i33);
                    if (charAt14 < 55296) {
                        break;
                    }
                    c4 |= (charAt14 & 8191) << i34;
                    i34 += 13;
                    i33 = i;
                }
                charAt15 = (charAt14 << i34) | c4;
            } else {
                i = 1;
            }
            int i35 = i + 1;
            char charAt16 = zzgn.charAt(i);
            if (charAt16 >= 55296) {
                char c5 = charAt16 & 8191;
                int i36 = 13;
                while (true) {
                    i2 = i35 + 1;
                    charAt13 = zzgn.charAt(i35);
                    if (charAt13 < 55296) {
                        break;
                    }
                    c5 |= (charAt13 & 8191) << i36;
                    i36 += 13;
                    i35 = i2;
                }
                charAt16 = c5 | (charAt13 << i36);
            } else {
                i2 = i35;
            }
            if (charAt16 == 0) {
                iArr = zzzb;
                c = 0;
                i7 = 0;
                i6 = 0;
                i5 = 0;
                i4 = 0;
                i3 = 0;
            } else {
                int i37 = i2 + 1;
                int charAt17 = zzgn.charAt(i2);
                if (charAt17 >= 55296) {
                    int i38 = charAt17 & 8191;
                    int i39 = 13;
                    while (true) {
                        i24 = i37 + 1;
                        charAt12 = zzgn.charAt(i37);
                        if (charAt12 < 55296) {
                            break;
                        }
                        i38 |= (charAt12 & 8191) << i39;
                        i39 += 13;
                        i37 = i24;
                    }
                    charAt17 = (charAt12 << i39) | i38;
                } else {
                    i24 = i37;
                }
                int i40 = i24 + 1;
                char charAt18 = zzgn.charAt(i24);
                if (charAt18 >= 55296) {
                    char c6 = charAt18 & 8191;
                    int i41 = 13;
                    while (true) {
                        i25 = i40 + 1;
                        charAt11 = zzgn.charAt(i40);
                        if (charAt11 < 55296) {
                            break;
                        }
                        c6 |= (charAt11 & 8191) << i41;
                        i41 += 13;
                        i40 = i25;
                    }
                    charAt18 = c6 | (charAt11 << i41);
                } else {
                    i25 = i40;
                }
                int i42 = i25 + 1;
                int charAt19 = zzgn.charAt(i25);
                if (charAt19 >= 55296) {
                    int i43 = charAt19 & 8191;
                    int i44 = 13;
                    while (true) {
                        i26 = i42 + 1;
                        charAt10 = zzgn.charAt(i42);
                        if (charAt10 < 55296) {
                            break;
                        }
                        i43 |= (charAt10 & 8191) << i44;
                        i44 += 13;
                        i42 = i26;
                    }
                    charAt19 = (charAt10 << i44) | i43;
                } else {
                    i26 = i42;
                }
                int i45 = i26 + 1;
                i5 = zzgn.charAt(i26);
                if (i5 >= 55296) {
                    int i46 = i5 & 8191;
                    int i47 = 13;
                    while (true) {
                        i27 = i45 + 1;
                        charAt9 = zzgn.charAt(i45);
                        if (charAt9 < 55296) {
                            break;
                        }
                        i46 |= (charAt9 & 8191) << i47;
                        i47 += 13;
                        i45 = i27;
                    }
                    i5 = (charAt9 << i47) | i46;
                } else {
                    i27 = i45;
                }
                int i48 = i27 + 1;
                i4 = zzgn.charAt(i27);
                if (i4 >= 55296) {
                    int i49 = i4 & 8191;
                    int i50 = 13;
                    while (true) {
                        i32 = i48 + 1;
                        charAt8 = zzgn.charAt(i48);
                        if (charAt8 < 55296) {
                            break;
                        }
                        i49 |= (charAt8 & 8191) << i50;
                        i50 += 13;
                        i48 = i32;
                    }
                    i4 = (charAt8 << i50) | i49;
                    i48 = i32;
                }
                int i51 = i48 + 1;
                c = zzgn.charAt(i48);
                if (c >= 55296) {
                    char c7 = c & 8191;
                    int i52 = 13;
                    while (true) {
                        i31 = i51 + 1;
                        charAt7 = zzgn.charAt(i51);
                        if (charAt7 < 55296) {
                            break;
                        }
                        c7 |= (charAt7 & 8191) << i52;
                        i52 += 13;
                        i51 = i31;
                    }
                    c = c7 | (charAt7 << i52);
                    i51 = i31;
                }
                int i53 = i51 + 1;
                char charAt20 = zzgn.charAt(i51);
                if (charAt20 >= 55296) {
                    int i54 = 13;
                    int i55 = i53;
                    int i56 = charAt20 & 8191;
                    int i57 = i55;
                    while (true) {
                        i30 = i57 + 1;
                        charAt6 = zzgn.charAt(i57);
                        if (charAt6 < 55296) {
                            break;
                        }
                        i56 |= (charAt6 & 8191) << i54;
                        i54 += 13;
                        i57 = i30;
                    }
                    charAt20 = i56 | (charAt6 << i54);
                    i28 = i30;
                } else {
                    i28 = i53;
                }
                int i58 = i28 + 1;
                c3 = zzgn.charAt(i28);
                if (c3 >= 55296) {
                    int i59 = 13;
                    int i60 = i58;
                    int i61 = c3 & 8191;
                    int i62 = i60;
                    while (true) {
                        i29 = i62 + 1;
                        charAt5 = zzgn.charAt(i62);
                        if (charAt5 < 55296) {
                            break;
                        }
                        i61 |= (charAt5 & 8191) << i59;
                        i59 += 13;
                        i62 = i29;
                    }
                    c3 = i61 | (charAt5 << i59);
                    i58 = i29;
                }
                iArr = new int[(c3 + c + charAt20)];
                i6 = (charAt17 << 1) + charAt18;
                int i63 = i58;
                i3 = charAt17;
                i7 = charAt19;
                i2 = i63;
            }
            Unsafe unsafe = zzzc;
            Object[] zzgo = zzhu.zzgo();
            Class cls3 = zzhu.zzgg().getClass();
            int i64 = i6;
            int[] iArr2 = new int[(i4 * 3)];
            Object[] objArr = new Object[(i4 << 1)];
            int i65 = c3 + c;
            int i66 = c3;
            int i67 = i65;
            int i68 = 0;
            int i69 = 0;
            while (i2 < length) {
                int i70 = i2 + 1;
                int charAt21 = zzgn.charAt(i2);
                char c8 = 55296;
                if (charAt21 >= 55296) {
                    int i71 = 13;
                    int i72 = i70;
                    int i73 = charAt21 & 8191;
                    int i74 = i72;
                    while (true) {
                        i23 = i74 + 1;
                        charAt4 = zzgn.charAt(i74);
                        if (charAt4 < c8) {
                            break;
                        }
                        i73 |= (charAt4 & 8191) << i71;
                        i71 += 13;
                        i74 = i23;
                        c8 = 55296;
                    }
                    charAt21 = i73 | (charAt4 << i71);
                    i8 = i23;
                } else {
                    i8 = i70;
                }
                int i75 = i8 + 1;
                char charAt22 = zzgn.charAt(i8);
                int i76 = length;
                char c9 = 55296;
                if (charAt22 >= 55296) {
                    int i77 = 13;
                    int i78 = i75;
                    int i79 = charAt22 & 8191;
                    int i80 = i78;
                    while (true) {
                        i22 = i80 + 1;
                        charAt3 = zzgn.charAt(i80);
                        if (charAt3 < c9) {
                            break;
                        }
                        i79 |= (charAt3 & 8191) << i77;
                        i77 += 13;
                        i80 = i22;
                        c9 = 55296;
                    }
                    charAt22 = i79 | (charAt3 << i77);
                    i9 = i22;
                } else {
                    i9 = i75;
                }
                int i81 = c3;
                char c10 = charAt22 & 255;
                boolean z2 = z;
                if ((charAt22 & 1024) != 0) {
                    int i82 = i68 + 1;
                    iArr[i68] = i69;
                    i68 = i82;
                }
                int i83 = i68;
                if (c10 >= '3') {
                    int i84 = i9 + 1;
                    char charAt23 = zzgn.charAt(i9);
                    char c11 = 55296;
                    if (charAt23 >= 55296) {
                        char c12 = charAt23 & 8191;
                        int i85 = 13;
                        while (true) {
                            i21 = i84 + 1;
                            charAt2 = zzgn.charAt(i84);
                            if (charAt2 < c11) {
                                break;
                            }
                            c12 |= (charAt2 & 8191) << i85;
                            i85 += 13;
                            i84 = i21;
                            c11 = 55296;
                        }
                        charAt23 = c12 | (charAt2 << i85);
                        i84 = i21;
                    }
                    int i86 = c10 - '3';
                    int i87 = i84;
                    if (i86 == 9 || i86 == 17) {
                        c2 = 1;
                        int i88 = i64 + 1;
                        objArr[((i69 / 3) << 1) + 1] = zzgo[i64];
                        i64 = i88;
                    } else {
                        if (i86 == 12 && (charAt15 & 1) == 1) {
                            int i89 = i64 + 1;
                            objArr[((i69 / 3) << 1) + 1] = zzgo[i64];
                            i64 = i89;
                        }
                        c2 = 1;
                    }
                    int i90 = charAt23 << c2;
                    Object obj = zzgo[i90];
                    if (obj instanceof Field) {
                        field2 = (Field) obj;
                    } else {
                        field2 = zza(cls3, (String) obj);
                        zzgo[i90] = field2;
                    }
                    int i91 = i7;
                    int objectFieldOffset = (int) unsafe.objectFieldOffset(field2);
                    int i92 = i90 + 1;
                    Object obj2 = zzgo[i92];
                    int i93 = objectFieldOffset;
                    if (obj2 instanceof Field) {
                        field3 = (Field) obj2;
                    } else {
                        field3 = zza(cls3, (String) obj2);
                        zzgo[i92] = field3;
                    }
                    str = zzgn;
                    i15 = (int) unsafe.objectFieldOffset(field3);
                    cls2 = cls3;
                    i11 = i64;
                    i14 = i93;
                    i16 = 0;
                    i10 = i91;
                    i12 = i5;
                    i13 = charAt21;
                    i18 = i87;
                } else {
                    int i94 = i7;
                    int i95 = i64 + 1;
                    Field zza = zza(cls3, (String) zzgo[i64]);
                    i12 = i5;
                    if (c10 == 9 || c10 == 17) {
                        i10 = i94;
                        objArr[((i69 / 3) << 1) + 1] = zza.getType();
                    } else {
                        if (c10 == 27 || c10 == '1') {
                            i10 = i94;
                            i20 = i95 + 1;
                            objArr[((i69 / 3) << 1) + 1] = zzgo[i95];
                        } else if (c10 == 12 || c10 == 30 || c10 == ',') {
                            i10 = i94;
                            if ((charAt15 & 1) == 1) {
                                i20 = i95 + 1;
                                objArr[((i69 / 3) << 1) + 1] = zzgo[i95];
                            }
                        } else if (c10 == '2') {
                            int i96 = i66 + 1;
                            iArr[i66] = i69;
                            int i97 = (i69 / 3) << 1;
                            int i98 = i95 + 1;
                            objArr[i97] = zzgo[i95];
                            if ((charAt22 & 2048) != 0) {
                                i95 = i98 + 1;
                                objArr[i97 + 1] = zzgo[i98];
                                i10 = i94;
                                i66 = i96;
                            } else {
                                i66 = i96;
                                i95 = i98;
                                i10 = i94;
                            }
                        } else {
                            i10 = i94;
                        }
                        i13 = charAt21;
                        i95 = i20;
                        i14 = (int) unsafe.objectFieldOffset(zza);
                        if ((charAt15 & 1) == 1 || c10 > 17) {
                            str = zzgn;
                            cls2 = cls3;
                            i11 = i95;
                            i17 = i9;
                            i16 = 0;
                            i15 = 0;
                        } else {
                            i17 = i9 + 1;
                            char charAt24 = zzgn.charAt(i9);
                            if (charAt24 >= 55296) {
                                char c13 = charAt24 & 8191;
                                int i99 = 13;
                                while (true) {
                                    i19 = i17 + 1;
                                    charAt = zzgn.charAt(i17);
                                    if (charAt < 55296) {
                                        break;
                                    }
                                    c13 |= (charAt & 8191) << i99;
                                    i99 += 13;
                                    i17 = i19;
                                }
                                charAt24 = c13 | (charAt << i99);
                                i17 = i19;
                            }
                            int i100 = (i3 << 1) + (charAt24 / ' ');
                            Object obj3 = zzgo[i100];
                            str = zzgn;
                            if (obj3 instanceof Field) {
                                field = (Field) obj3;
                            } else {
                                field = zza(cls3, (String) obj3);
                                zzgo[i100] = field;
                            }
                            cls2 = cls3;
                            i11 = i95;
                            i15 = (int) unsafe.objectFieldOffset(field);
                            i16 = charAt24 % ' ';
                        }
                        if (c10 >= 18 && c10 <= '1') {
                            int i101 = i67 + 1;
                            iArr[i67] = i14;
                            i67 = i101;
                        }
                        i18 = i17;
                    }
                    i13 = charAt21;
                    i14 = (int) unsafe.objectFieldOffset(zza);
                    if ((charAt15 & 1) == 1) {
                    }
                    str = zzgn;
                    cls2 = cls3;
                    i11 = i95;
                    i17 = i9;
                    i16 = 0;
                    i15 = 0;
                    int i1012 = i67 + 1;
                    iArr[i67] = i14;
                    i67 = i1012;
                    i18 = i17;
                }
                int i102 = i69 + 1;
                iArr2[i69] = i13;
                int i103 = i102 + 1;
                iArr2[i102] = (c10 << 20) | ((charAt22 & 256) != 0 ? 268435456 : 0) | ((charAt22 & 512) != 0 ? PKIFailureInfo.duplicateCertReq : 0) | i14;
                i69 = i103 + 1;
                iArr2[i103] = (i16 << 20) | i15;
                cls3 = cls2;
                i5 = i12;
                c3 = i81;
                i64 = i11;
                length = i76;
                z = z2;
                i7 = i10;
                i68 = i83;
                zzgn = str;
            }
            boolean z3 = z;
            zzhj zzhj = new zzhj(iArr2, objArr, i7, i5, zzhu.zzgg(), z, false, iArr, c3, i65, zzhn, zzgp, zzio, zzfl, zzha);
            return zzhj;
        }
        ((zzij) zzhd2).zzge();
        throw new NoSuchMethodError();
    }

    private static Field zza(Class<?> cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException unused) {
            Field[] declaredFields = cls.getDeclaredFields();
            for (Field field : declaredFields) {
                if (str.equals(field.getName())) {
                    return field;
                }
            }
            String name = cls.getName();
            String arrays = Arrays.toString(declaredFields);
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 40 + String.valueOf(name).length() + String.valueOf(arrays).length());
            sb.append("Field ");
            sb.append(str);
            sb.append(" for ");
            sb.append(name);
            sb.append(" not found. Known fields are ");
            sb.append(arrays);
            throw new RuntimeException(sb.toString());
        }
    }

    public final T newInstance() {
        return this.zzzp.newInstance(this.zzzh);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x006a, code lost:
        if (com.google.android.gms.internal.vision.zzhy.zzd(com.google.android.gms.internal.vision.zziu.zzp(r10, r6), com.google.android.gms.internal.vision.zziu.zzp(r11, r6)) != false) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x007e, code lost:
        if (com.google.android.gms.internal.vision.zziu.zzl(r10, r6) == com.google.android.gms.internal.vision.zziu.zzl(r11, r6)) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0090, code lost:
        if (com.google.android.gms.internal.vision.zziu.zzk(r10, r6) == com.google.android.gms.internal.vision.zziu.zzk(r11, r6)) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x00a4, code lost:
        if (com.google.android.gms.internal.vision.zziu.zzl(r10, r6) == com.google.android.gms.internal.vision.zziu.zzl(r11, r6)) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00b6, code lost:
        if (com.google.android.gms.internal.vision.zziu.zzk(r10, r6) == com.google.android.gms.internal.vision.zziu.zzk(r11, r6)) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00c8, code lost:
        if (com.google.android.gms.internal.vision.zziu.zzk(r10, r6) == com.google.android.gms.internal.vision.zziu.zzk(r11, r6)) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00da, code lost:
        if (com.google.android.gms.internal.vision.zziu.zzk(r10, r6) == com.google.android.gms.internal.vision.zziu.zzk(r11, r6)) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00f0, code lost:
        if (com.google.android.gms.internal.vision.zzhy.zzd(com.google.android.gms.internal.vision.zziu.zzp(r10, r6), com.google.android.gms.internal.vision.zziu.zzp(r11, r6)) != false) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0106, code lost:
        if (com.google.android.gms.internal.vision.zzhy.zzd(com.google.android.gms.internal.vision.zziu.zzp(r10, r6), com.google.android.gms.internal.vision.zziu.zzp(r11, r6)) != false) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x011c, code lost:
        if (com.google.android.gms.internal.vision.zzhy.zzd(com.google.android.gms.internal.vision.zziu.zzp(r10, r6), com.google.android.gms.internal.vision.zziu.zzp(r11, r6)) != false) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x012e, code lost:
        if (com.google.android.gms.internal.vision.zziu.zzm(r10, r6) == com.google.android.gms.internal.vision.zziu.zzm(r11, r6)) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x0140, code lost:
        if (com.google.android.gms.internal.vision.zziu.zzk(r10, r6) == com.google.android.gms.internal.vision.zziu.zzk(r11, r6)) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0154, code lost:
        if (com.google.android.gms.internal.vision.zziu.zzl(r10, r6) == com.google.android.gms.internal.vision.zziu.zzl(r11, r6)) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x0165, code lost:
        if (com.google.android.gms.internal.vision.zziu.zzk(r10, r6) == com.google.android.gms.internal.vision.zziu.zzk(r11, r6)) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x0178, code lost:
        if (com.google.android.gms.internal.vision.zziu.zzl(r10, r6) == com.google.android.gms.internal.vision.zziu.zzl(r11, r6)) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x018b, code lost:
        if (com.google.android.gms.internal.vision.zziu.zzl(r10, r6) == com.google.android.gms.internal.vision.zziu.zzl(r11, r6)) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x01a4, code lost:
        if (java.lang.Float.floatToIntBits(com.google.android.gms.internal.vision.zziu.zzn(r10, r6)) == java.lang.Float.floatToIntBits(com.google.android.gms.internal.vision.zziu.zzn(r11, r6))) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x01bf, code lost:
        if (java.lang.Double.doubleToLongBits(com.google.android.gms.internal.vision.zziu.zzo(r10, r6)) == java.lang.Double.doubleToLongBits(com.google.android.gms.internal.vision.zziu.zzo(r11, r6))) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x01c1, code lost:
        r3 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0038, code lost:
        if (com.google.android.gms.internal.vision.zzhy.zzd(com.google.android.gms.internal.vision.zziu.zzp(r10, r6), com.google.android.gms.internal.vision.zziu.zzp(r11, r6)) != false) goto L_0x01c2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean equals(T r10, T r11) {
        /*
            r9 = this;
            int[] r0 = r9.zzzd
            int r0 = r0.length
            r1 = 0
            r2 = 0
        L_0x0005:
            r3 = 1
            if (r2 >= r0) goto L_0x01c9
            int r4 = r9.zzbk(r2)
            r5 = 1048575(0xfffff, float:1.469367E-39)
            r6 = r4 & r5
            long r6 = (long) r6
            r8 = 267386880(0xff00000, float:2.3665827E-29)
            r4 = r4 & r8
            int r4 = r4 >>> 20
            switch(r4) {
                case 0: goto L_0x01a7;
                case 1: goto L_0x018e;
                case 2: goto L_0x017b;
                case 3: goto L_0x0168;
                case 4: goto L_0x0157;
                case 5: goto L_0x0144;
                case 6: goto L_0x0132;
                case 7: goto L_0x0120;
                case 8: goto L_0x010a;
                case 9: goto L_0x00f4;
                case 10: goto L_0x00de;
                case 11: goto L_0x00cc;
                case 12: goto L_0x00ba;
                case 13: goto L_0x00a8;
                case 14: goto L_0x0094;
                case 15: goto L_0x0082;
                case 16: goto L_0x006e;
                case 17: goto L_0x0058;
                case 18: goto L_0x004a;
                case 19: goto L_0x004a;
                case 20: goto L_0x004a;
                case 21: goto L_0x004a;
                case 22: goto L_0x004a;
                case 23: goto L_0x004a;
                case 24: goto L_0x004a;
                case 25: goto L_0x004a;
                case 26: goto L_0x004a;
                case 27: goto L_0x004a;
                case 28: goto L_0x004a;
                case 29: goto L_0x004a;
                case 30: goto L_0x004a;
                case 31: goto L_0x004a;
                case 32: goto L_0x004a;
                case 33: goto L_0x004a;
                case 34: goto L_0x004a;
                case 35: goto L_0x004a;
                case 36: goto L_0x004a;
                case 37: goto L_0x004a;
                case 38: goto L_0x004a;
                case 39: goto L_0x004a;
                case 40: goto L_0x004a;
                case 41: goto L_0x004a;
                case 42: goto L_0x004a;
                case 43: goto L_0x004a;
                case 44: goto L_0x004a;
                case 45: goto L_0x004a;
                case 46: goto L_0x004a;
                case 47: goto L_0x004a;
                case 48: goto L_0x004a;
                case 49: goto L_0x004a;
                case 50: goto L_0x003c;
                case 51: goto L_0x001c;
                case 52: goto L_0x001c;
                case 53: goto L_0x001c;
                case 54: goto L_0x001c;
                case 55: goto L_0x001c;
                case 56: goto L_0x001c;
                case 57: goto L_0x001c;
                case 58: goto L_0x001c;
                case 59: goto L_0x001c;
                case 60: goto L_0x001c;
                case 61: goto L_0x001c;
                case 62: goto L_0x001c;
                case 63: goto L_0x001c;
                case 64: goto L_0x001c;
                case 65: goto L_0x001c;
                case 66: goto L_0x001c;
                case 67: goto L_0x001c;
                case 68: goto L_0x001c;
                default: goto L_0x001a;
            }
        L_0x001a:
            goto L_0x01c2
        L_0x001c:
            int r4 = r9.zzbl(r2)
            r4 = r4 & r5
            long r4 = (long) r4
            int r8 = com.google.android.gms.internal.vision.zziu.zzk(r10, r4)
            int r4 = com.google.android.gms.internal.vision.zziu.zzk(r11, r4)
            if (r8 != r4) goto L_0x01c1
            java.lang.Object r4 = com.google.android.gms.internal.vision.zziu.zzp(r10, r6)
            java.lang.Object r5 = com.google.android.gms.internal.vision.zziu.zzp(r11, r6)
            boolean r4 = com.google.android.gms.internal.vision.zzhy.zzd(r4, r5)
            if (r4 != 0) goto L_0x01c2
            goto L_0x01c1
        L_0x003c:
            java.lang.Object r3 = com.google.android.gms.internal.vision.zziu.zzp(r10, r6)
            java.lang.Object r4 = com.google.android.gms.internal.vision.zziu.zzp(r11, r6)
            boolean r3 = com.google.android.gms.internal.vision.zzhy.zzd(r3, r4)
            goto L_0x01c2
        L_0x004a:
            java.lang.Object r3 = com.google.android.gms.internal.vision.zziu.zzp(r10, r6)
            java.lang.Object r4 = com.google.android.gms.internal.vision.zziu.zzp(r11, r6)
            boolean r3 = com.google.android.gms.internal.vision.zzhy.zzd(r3, r4)
            goto L_0x01c2
        L_0x0058:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01c1
            java.lang.Object r4 = com.google.android.gms.internal.vision.zziu.zzp(r10, r6)
            java.lang.Object r5 = com.google.android.gms.internal.vision.zziu.zzp(r11, r6)
            boolean r4 = com.google.android.gms.internal.vision.zzhy.zzd(r4, r5)
            if (r4 != 0) goto L_0x01c2
            goto L_0x01c1
        L_0x006e:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01c1
            long r4 = com.google.android.gms.internal.vision.zziu.zzl(r10, r6)
            long r6 = com.google.android.gms.internal.vision.zziu.zzl(r11, r6)
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 == 0) goto L_0x01c2
            goto L_0x01c1
        L_0x0082:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01c1
            int r4 = com.google.android.gms.internal.vision.zziu.zzk(r10, r6)
            int r5 = com.google.android.gms.internal.vision.zziu.zzk(r11, r6)
            if (r4 == r5) goto L_0x01c2
            goto L_0x01c1
        L_0x0094:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01c1
            long r4 = com.google.android.gms.internal.vision.zziu.zzl(r10, r6)
            long r6 = com.google.android.gms.internal.vision.zziu.zzl(r11, r6)
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 == 0) goto L_0x01c2
            goto L_0x01c1
        L_0x00a8:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01c1
            int r4 = com.google.android.gms.internal.vision.zziu.zzk(r10, r6)
            int r5 = com.google.android.gms.internal.vision.zziu.zzk(r11, r6)
            if (r4 == r5) goto L_0x01c2
            goto L_0x01c1
        L_0x00ba:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01c1
            int r4 = com.google.android.gms.internal.vision.zziu.zzk(r10, r6)
            int r5 = com.google.android.gms.internal.vision.zziu.zzk(r11, r6)
            if (r4 == r5) goto L_0x01c2
            goto L_0x01c1
        L_0x00cc:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01c1
            int r4 = com.google.android.gms.internal.vision.zziu.zzk(r10, r6)
            int r5 = com.google.android.gms.internal.vision.zziu.zzk(r11, r6)
            if (r4 == r5) goto L_0x01c2
            goto L_0x01c1
        L_0x00de:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01c1
            java.lang.Object r4 = com.google.android.gms.internal.vision.zziu.zzp(r10, r6)
            java.lang.Object r5 = com.google.android.gms.internal.vision.zziu.zzp(r11, r6)
            boolean r4 = com.google.android.gms.internal.vision.zzhy.zzd(r4, r5)
            if (r4 != 0) goto L_0x01c2
            goto L_0x01c1
        L_0x00f4:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01c1
            java.lang.Object r4 = com.google.android.gms.internal.vision.zziu.zzp(r10, r6)
            java.lang.Object r5 = com.google.android.gms.internal.vision.zziu.zzp(r11, r6)
            boolean r4 = com.google.android.gms.internal.vision.zzhy.zzd(r4, r5)
            if (r4 != 0) goto L_0x01c2
            goto L_0x01c1
        L_0x010a:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01c1
            java.lang.Object r4 = com.google.android.gms.internal.vision.zziu.zzp(r10, r6)
            java.lang.Object r5 = com.google.android.gms.internal.vision.zziu.zzp(r11, r6)
            boolean r4 = com.google.android.gms.internal.vision.zzhy.zzd(r4, r5)
            if (r4 != 0) goto L_0x01c2
            goto L_0x01c1
        L_0x0120:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01c1
            boolean r4 = com.google.android.gms.internal.vision.zziu.zzm(r10, r6)
            boolean r5 = com.google.android.gms.internal.vision.zziu.zzm(r11, r6)
            if (r4 == r5) goto L_0x01c2
            goto L_0x01c1
        L_0x0132:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01c1
            int r4 = com.google.android.gms.internal.vision.zziu.zzk(r10, r6)
            int r5 = com.google.android.gms.internal.vision.zziu.zzk(r11, r6)
            if (r4 == r5) goto L_0x01c2
            goto L_0x01c1
        L_0x0144:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01c1
            long r4 = com.google.android.gms.internal.vision.zziu.zzl(r10, r6)
            long r6 = com.google.android.gms.internal.vision.zziu.zzl(r11, r6)
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 == 0) goto L_0x01c2
            goto L_0x01c1
        L_0x0157:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01c1
            int r4 = com.google.android.gms.internal.vision.zziu.zzk(r10, r6)
            int r5 = com.google.android.gms.internal.vision.zziu.zzk(r11, r6)
            if (r4 == r5) goto L_0x01c2
            goto L_0x01c1
        L_0x0168:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01c1
            long r4 = com.google.android.gms.internal.vision.zziu.zzl(r10, r6)
            long r6 = com.google.android.gms.internal.vision.zziu.zzl(r11, r6)
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 == 0) goto L_0x01c2
            goto L_0x01c1
        L_0x017b:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01c1
            long r4 = com.google.android.gms.internal.vision.zziu.zzl(r10, r6)
            long r6 = com.google.android.gms.internal.vision.zziu.zzl(r11, r6)
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 == 0) goto L_0x01c2
            goto L_0x01c1
        L_0x018e:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01c1
            float r4 = com.google.android.gms.internal.vision.zziu.zzn(r10, r6)
            int r4 = java.lang.Float.floatToIntBits(r4)
            float r5 = com.google.android.gms.internal.vision.zziu.zzn(r11, r6)
            int r5 = java.lang.Float.floatToIntBits(r5)
            if (r4 == r5) goto L_0x01c2
            goto L_0x01c1
        L_0x01a7:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01c1
            double r4 = com.google.android.gms.internal.vision.zziu.zzo(r10, r6)
            long r4 = java.lang.Double.doubleToLongBits(r4)
            double r6 = com.google.android.gms.internal.vision.zziu.zzo(r11, r6)
            long r6 = java.lang.Double.doubleToLongBits(r6)
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 == 0) goto L_0x01c2
        L_0x01c1:
            r3 = 0
        L_0x01c2:
            if (r3 != 0) goto L_0x01c5
            return r1
        L_0x01c5:
            int r2 = r2 + 3
            goto L_0x0005
        L_0x01c9:
            com.google.android.gms.internal.vision.zzio<?, ?> r0 = r9.zzzr
            java.lang.Object r0 = r0.zzt(r10)
            com.google.android.gms.internal.vision.zzio<?, ?> r2 = r9.zzzr
            java.lang.Object r2 = r2.zzt(r11)
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L_0x01dc
            return r1
        L_0x01dc:
            boolean r0 = r9.zzzi
            if (r0 == 0) goto L_0x01f1
            com.google.android.gms.internal.vision.zzfl<?> r0 = r9.zzzs
            com.google.android.gms.internal.vision.zzfp r10 = r0.zzc(r10)
            com.google.android.gms.internal.vision.zzfl<?> r0 = r9.zzzs
            com.google.android.gms.internal.vision.zzfp r11 = r0.zzc(r11)
            boolean r10 = r10.equals(r11)
            return r10
        L_0x01f1:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzhj.equals(java.lang.Object, java.lang.Object):boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:73:0x01c3, code lost:
        r2 = (r2 * 53) + r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x0227, code lost:
        r2 = r2 + r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x0228, code lost:
        r1 = r1 + 3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int hashCode(T r9) {
        /*
            r8 = this;
            int[] r0 = r8.zzzd
            int r0 = r0.length
            r1 = 0
            r2 = 0
        L_0x0005:
            if (r1 >= r0) goto L_0x022c
            int r3 = r8.zzbk(r1)
            int[] r4 = r8.zzzd
            r4 = r4[r1]
            r5 = 1048575(0xfffff, float:1.469367E-39)
            r5 = r5 & r3
            long r5 = (long) r5
            r7 = 267386880(0xff00000, float:2.3665827E-29)
            r3 = r3 & r7
            int r3 = r3 >>> 20
            r7 = 37
            switch(r3) {
                case 0: goto L_0x0219;
                case 1: goto L_0x020e;
                case 2: goto L_0x0203;
                case 3: goto L_0x01f8;
                case 4: goto L_0x01f1;
                case 5: goto L_0x01e6;
                case 6: goto L_0x01df;
                case 7: goto L_0x01d4;
                case 8: goto L_0x01c7;
                case 9: goto L_0x01b9;
                case 10: goto L_0x01ad;
                case 11: goto L_0x01a5;
                case 12: goto L_0x019d;
                case 13: goto L_0x0195;
                case 14: goto L_0x0189;
                case 15: goto L_0x0181;
                case 16: goto L_0x0175;
                case 17: goto L_0x016a;
                case 18: goto L_0x015e;
                case 19: goto L_0x015e;
                case 20: goto L_0x015e;
                case 21: goto L_0x015e;
                case 22: goto L_0x015e;
                case 23: goto L_0x015e;
                case 24: goto L_0x015e;
                case 25: goto L_0x015e;
                case 26: goto L_0x015e;
                case 27: goto L_0x015e;
                case 28: goto L_0x015e;
                case 29: goto L_0x015e;
                case 30: goto L_0x015e;
                case 31: goto L_0x015e;
                case 32: goto L_0x015e;
                case 33: goto L_0x015e;
                case 34: goto L_0x015e;
                case 35: goto L_0x015e;
                case 36: goto L_0x015e;
                case 37: goto L_0x015e;
                case 38: goto L_0x015e;
                case 39: goto L_0x015e;
                case 40: goto L_0x015e;
                case 41: goto L_0x015e;
                case 42: goto L_0x015e;
                case 43: goto L_0x015e;
                case 44: goto L_0x015e;
                case 45: goto L_0x015e;
                case 46: goto L_0x015e;
                case 47: goto L_0x015e;
                case 48: goto L_0x015e;
                case 49: goto L_0x015e;
                case 50: goto L_0x0152;
                case 51: goto L_0x013c;
                case 52: goto L_0x012a;
                case 53: goto L_0x0118;
                case 54: goto L_0x0106;
                case 55: goto L_0x00f8;
                case 56: goto L_0x00e6;
                case 57: goto L_0x00d8;
                case 58: goto L_0x00c6;
                case 59: goto L_0x00b2;
                case 60: goto L_0x00a0;
                case 61: goto L_0x008e;
                case 62: goto L_0x0080;
                case 63: goto L_0x0072;
                case 64: goto L_0x0064;
                case 65: goto L_0x0052;
                case 66: goto L_0x0044;
                case 67: goto L_0x0032;
                case 68: goto L_0x0020;
                default: goto L_0x001e;
            }
        L_0x001e:
            goto L_0x0228
        L_0x0020:
            boolean r3 = r8.zza((T) r9, r4, r1)
            if (r3 == 0) goto L_0x0228
            java.lang.Object r3 = com.google.android.gms.internal.vision.zziu.zzp(r9, r5)
            int r2 = r2 * 53
            int r3 = r3.hashCode()
            goto L_0x0227
        L_0x0032:
            boolean r3 = r8.zza((T) r9, r4, r1)
            if (r3 == 0) goto L_0x0228
            int r2 = r2 * 53
            long r3 = zzi(r9, r5)
            int r3 = com.google.android.gms.internal.vision.zzga.zzo(r3)
            goto L_0x0227
        L_0x0044:
            boolean r3 = r8.zza((T) r9, r4, r1)
            if (r3 == 0) goto L_0x0228
            int r2 = r2 * 53
            int r3 = zzh(r9, r5)
            goto L_0x0227
        L_0x0052:
            boolean r3 = r8.zza((T) r9, r4, r1)
            if (r3 == 0) goto L_0x0228
            int r2 = r2 * 53
            long r3 = zzi(r9, r5)
            int r3 = com.google.android.gms.internal.vision.zzga.zzo(r3)
            goto L_0x0227
        L_0x0064:
            boolean r3 = r8.zza((T) r9, r4, r1)
            if (r3 == 0) goto L_0x0228
            int r2 = r2 * 53
            int r3 = zzh(r9, r5)
            goto L_0x0227
        L_0x0072:
            boolean r3 = r8.zza((T) r9, r4, r1)
            if (r3 == 0) goto L_0x0228
            int r2 = r2 * 53
            int r3 = zzh(r9, r5)
            goto L_0x0227
        L_0x0080:
            boolean r3 = r8.zza((T) r9, r4, r1)
            if (r3 == 0) goto L_0x0228
            int r2 = r2 * 53
            int r3 = zzh(r9, r5)
            goto L_0x0227
        L_0x008e:
            boolean r3 = r8.zza((T) r9, r4, r1)
            if (r3 == 0) goto L_0x0228
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.android.gms.internal.vision.zziu.zzp(r9, r5)
            int r3 = r3.hashCode()
            goto L_0x0227
        L_0x00a0:
            boolean r3 = r8.zza((T) r9, r4, r1)
            if (r3 == 0) goto L_0x0228
            java.lang.Object r3 = com.google.android.gms.internal.vision.zziu.zzp(r9, r5)
            int r2 = r2 * 53
            int r3 = r3.hashCode()
            goto L_0x0227
        L_0x00b2:
            boolean r3 = r8.zza((T) r9, r4, r1)
            if (r3 == 0) goto L_0x0228
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.android.gms.internal.vision.zziu.zzp(r9, r5)
            java.lang.String r3 = (java.lang.String) r3
            int r3 = r3.hashCode()
            goto L_0x0227
        L_0x00c6:
            boolean r3 = r8.zza((T) r9, r4, r1)
            if (r3 == 0) goto L_0x0228
            int r2 = r2 * 53
            boolean r3 = zzj(r9, r5)
            int r3 = com.google.android.gms.internal.vision.zzga.zzj(r3)
            goto L_0x0227
        L_0x00d8:
            boolean r3 = r8.zza((T) r9, r4, r1)
            if (r3 == 0) goto L_0x0228
            int r2 = r2 * 53
            int r3 = zzh(r9, r5)
            goto L_0x0227
        L_0x00e6:
            boolean r3 = r8.zza((T) r9, r4, r1)
            if (r3 == 0) goto L_0x0228
            int r2 = r2 * 53
            long r3 = zzi(r9, r5)
            int r3 = com.google.android.gms.internal.vision.zzga.zzo(r3)
            goto L_0x0227
        L_0x00f8:
            boolean r3 = r8.zza((T) r9, r4, r1)
            if (r3 == 0) goto L_0x0228
            int r2 = r2 * 53
            int r3 = zzh(r9, r5)
            goto L_0x0227
        L_0x0106:
            boolean r3 = r8.zza((T) r9, r4, r1)
            if (r3 == 0) goto L_0x0228
            int r2 = r2 * 53
            long r3 = zzi(r9, r5)
            int r3 = com.google.android.gms.internal.vision.zzga.zzo(r3)
            goto L_0x0227
        L_0x0118:
            boolean r3 = r8.zza((T) r9, r4, r1)
            if (r3 == 0) goto L_0x0228
            int r2 = r2 * 53
            long r3 = zzi(r9, r5)
            int r3 = com.google.android.gms.internal.vision.zzga.zzo(r3)
            goto L_0x0227
        L_0x012a:
            boolean r3 = r8.zza((T) r9, r4, r1)
            if (r3 == 0) goto L_0x0228
            int r2 = r2 * 53
            float r3 = zzg(r9, r5)
            int r3 = java.lang.Float.floatToIntBits(r3)
            goto L_0x0227
        L_0x013c:
            boolean r3 = r8.zza((T) r9, r4, r1)
            if (r3 == 0) goto L_0x0228
            int r2 = r2 * 53
            double r3 = zzf(r9, r5)
            long r3 = java.lang.Double.doubleToLongBits(r3)
            int r3 = com.google.android.gms.internal.vision.zzga.zzo(r3)
            goto L_0x0227
        L_0x0152:
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.android.gms.internal.vision.zziu.zzp(r9, r5)
            int r3 = r3.hashCode()
            goto L_0x0227
        L_0x015e:
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.android.gms.internal.vision.zziu.zzp(r9, r5)
            int r3 = r3.hashCode()
            goto L_0x0227
        L_0x016a:
            java.lang.Object r3 = com.google.android.gms.internal.vision.zziu.zzp(r9, r5)
            if (r3 == 0) goto L_0x01c3
            int r7 = r3.hashCode()
            goto L_0x01c3
        L_0x0175:
            int r2 = r2 * 53
            long r3 = com.google.android.gms.internal.vision.zziu.zzl(r9, r5)
            int r3 = com.google.android.gms.internal.vision.zzga.zzo(r3)
            goto L_0x0227
        L_0x0181:
            int r2 = r2 * 53
            int r3 = com.google.android.gms.internal.vision.zziu.zzk(r9, r5)
            goto L_0x0227
        L_0x0189:
            int r2 = r2 * 53
            long r3 = com.google.android.gms.internal.vision.zziu.zzl(r9, r5)
            int r3 = com.google.android.gms.internal.vision.zzga.zzo(r3)
            goto L_0x0227
        L_0x0195:
            int r2 = r2 * 53
            int r3 = com.google.android.gms.internal.vision.zziu.zzk(r9, r5)
            goto L_0x0227
        L_0x019d:
            int r2 = r2 * 53
            int r3 = com.google.android.gms.internal.vision.zziu.zzk(r9, r5)
            goto L_0x0227
        L_0x01a5:
            int r2 = r2 * 53
            int r3 = com.google.android.gms.internal.vision.zziu.zzk(r9, r5)
            goto L_0x0227
        L_0x01ad:
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.android.gms.internal.vision.zziu.zzp(r9, r5)
            int r3 = r3.hashCode()
            goto L_0x0227
        L_0x01b9:
            java.lang.Object r3 = com.google.android.gms.internal.vision.zziu.zzp(r9, r5)
            if (r3 == 0) goto L_0x01c3
            int r7 = r3.hashCode()
        L_0x01c3:
            int r2 = r2 * 53
            int r2 = r2 + r7
            goto L_0x0228
        L_0x01c7:
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.android.gms.internal.vision.zziu.zzp(r9, r5)
            java.lang.String r3 = (java.lang.String) r3
            int r3 = r3.hashCode()
            goto L_0x0227
        L_0x01d4:
            int r2 = r2 * 53
            boolean r3 = com.google.android.gms.internal.vision.zziu.zzm(r9, r5)
            int r3 = com.google.android.gms.internal.vision.zzga.zzj(r3)
            goto L_0x0227
        L_0x01df:
            int r2 = r2 * 53
            int r3 = com.google.android.gms.internal.vision.zziu.zzk(r9, r5)
            goto L_0x0227
        L_0x01e6:
            int r2 = r2 * 53
            long r3 = com.google.android.gms.internal.vision.zziu.zzl(r9, r5)
            int r3 = com.google.android.gms.internal.vision.zzga.zzo(r3)
            goto L_0x0227
        L_0x01f1:
            int r2 = r2 * 53
            int r3 = com.google.android.gms.internal.vision.zziu.zzk(r9, r5)
            goto L_0x0227
        L_0x01f8:
            int r2 = r2 * 53
            long r3 = com.google.android.gms.internal.vision.zziu.zzl(r9, r5)
            int r3 = com.google.android.gms.internal.vision.zzga.zzo(r3)
            goto L_0x0227
        L_0x0203:
            int r2 = r2 * 53
            long r3 = com.google.android.gms.internal.vision.zziu.zzl(r9, r5)
            int r3 = com.google.android.gms.internal.vision.zzga.zzo(r3)
            goto L_0x0227
        L_0x020e:
            int r2 = r2 * 53
            float r3 = com.google.android.gms.internal.vision.zziu.zzn(r9, r5)
            int r3 = java.lang.Float.floatToIntBits(r3)
            goto L_0x0227
        L_0x0219:
            int r2 = r2 * 53
            double r3 = com.google.android.gms.internal.vision.zziu.zzo(r9, r5)
            long r3 = java.lang.Double.doubleToLongBits(r3)
            int r3 = com.google.android.gms.internal.vision.zzga.zzo(r3)
        L_0x0227:
            int r2 = r2 + r3
        L_0x0228:
            int r1 = r1 + 3
            goto L_0x0005
        L_0x022c:
            int r2 = r2 * 53
            com.google.android.gms.internal.vision.zzio<?, ?> r0 = r8.zzzr
            java.lang.Object r0 = r0.zzt(r9)
            int r0 = r0.hashCode()
            int r2 = r2 + r0
            boolean r0 = r8.zzzi
            if (r0 == 0) goto L_0x024a
            int r2 = r2 * 53
            com.google.android.gms.internal.vision.zzfl<?> r0 = r8.zzzs
            com.google.android.gms.internal.vision.zzfp r9 = r0.zzc(r9)
            int r9 = r9.hashCode()
            int r2 = r2 + r9
        L_0x024a:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzhj.hashCode(java.lang.Object):int");
    }

    public final void zzc(T t, T t2) {
        if (t2 != null) {
            for (int i = 0; i < this.zzzd.length; i += 3) {
                int zzbk = zzbk(i);
                long j = (long) (1048575 & zzbk);
                int i2 = this.zzzd[i];
                switch ((zzbk & 267386880) >>> 20) {
                    case 0:
                        if (!zza(t2, i)) {
                            break;
                        } else {
                            zziu.zza((Object) t, j, zziu.zzo(t2, j));
                            zzb(t, i);
                            break;
                        }
                    case 1:
                        if (!zza(t2, i)) {
                            break;
                        } else {
                            zziu.zza((Object) t, j, zziu.zzn(t2, j));
                            zzb(t, i);
                            break;
                        }
                    case 2:
                        if (!zza(t2, i)) {
                            break;
                        } else {
                            zziu.zza((Object) t, j, zziu.zzl(t2, j));
                            zzb(t, i);
                            break;
                        }
                    case 3:
                        if (!zza(t2, i)) {
                            break;
                        } else {
                            zziu.zza((Object) t, j, zziu.zzl(t2, j));
                            zzb(t, i);
                            break;
                        }
                    case 4:
                        if (!zza(t2, i)) {
                            break;
                        } else {
                            zziu.zzb((Object) t, j, zziu.zzk(t2, j));
                            zzb(t, i);
                            break;
                        }
                    case 5:
                        if (!zza(t2, i)) {
                            break;
                        } else {
                            zziu.zza((Object) t, j, zziu.zzl(t2, j));
                            zzb(t, i);
                            break;
                        }
                    case 6:
                        if (!zza(t2, i)) {
                            break;
                        } else {
                            zziu.zzb((Object) t, j, zziu.zzk(t2, j));
                            zzb(t, i);
                            break;
                        }
                    case 7:
                        if (!zza(t2, i)) {
                            break;
                        } else {
                            zziu.zza((Object) t, j, zziu.zzm(t2, j));
                            zzb(t, i);
                            break;
                        }
                    case 8:
                        if (!zza(t2, i)) {
                            break;
                        } else {
                            zziu.zza((Object) t, j, zziu.zzp(t2, j));
                            zzb(t, i);
                            break;
                        }
                    case 9:
                        zza(t, t2, i);
                        break;
                    case 10:
                        if (!zza(t2, i)) {
                            break;
                        } else {
                            zziu.zza((Object) t, j, zziu.zzp(t2, j));
                            zzb(t, i);
                            break;
                        }
                    case 11:
                        if (!zza(t2, i)) {
                            break;
                        } else {
                            zziu.zzb((Object) t, j, zziu.zzk(t2, j));
                            zzb(t, i);
                            break;
                        }
                    case 12:
                        if (!zza(t2, i)) {
                            break;
                        } else {
                            zziu.zzb((Object) t, j, zziu.zzk(t2, j));
                            zzb(t, i);
                            break;
                        }
                    case 13:
                        if (!zza(t2, i)) {
                            break;
                        } else {
                            zziu.zzb((Object) t, j, zziu.zzk(t2, j));
                            zzb(t, i);
                            break;
                        }
                    case 14:
                        if (!zza(t2, i)) {
                            break;
                        } else {
                            zziu.zza((Object) t, j, zziu.zzl(t2, j));
                            zzb(t, i);
                            break;
                        }
                    case 15:
                        if (!zza(t2, i)) {
                            break;
                        } else {
                            zziu.zzb((Object) t, j, zziu.zzk(t2, j));
                            zzb(t, i);
                            break;
                        }
                    case 16:
                        if (!zza(t2, i)) {
                            break;
                        } else {
                            zziu.zza((Object) t, j, zziu.zzl(t2, j));
                            zzb(t, i);
                            break;
                        }
                    case 17:
                        zza(t, t2, i);
                        break;
                    case 18:
                    case 19:
                    case 20:
                    case 21:
                    case 22:
                    case 23:
                    case 24:
                    case 25:
                    case 26:
                    case 27:
                    case 28:
                    case 29:
                    case 30:
                    case 31:
                    case 32:
                    case 33:
                    case 34:
                    case 35:
                    case 36:
                    case 37:
                    case 38:
                    case 39:
                    case 40:
                    case 41:
                    case 42:
                    case 43:
                    case 44:
                    case 45:
                    case 46:
                    case 47:
                    case 48:
                    case 49:
                        this.zzzq.zza(t, t2, j);
                        break;
                    case 50:
                        zzhy.zza(this.zzzt, t, t2, j);
                        break;
                    case 51:
                    case 52:
                    case 53:
                    case 54:
                    case 55:
                    case 56:
                    case 57:
                    case 58:
                    case 59:
                        if (!zza(t2, i2, i)) {
                            break;
                        } else {
                            zziu.zza((Object) t, j, zziu.zzp(t2, j));
                            zzb(t, i2, i);
                            break;
                        }
                    case 60:
                        zzb(t, t2, i);
                        break;
                    case 61:
                    case 62:
                    case 63:
                    case 64:
                    case 65:
                    case 66:
                    case 67:
                        if (!zza(t2, i2, i)) {
                            break;
                        } else {
                            zziu.zza((Object) t, j, zziu.zzp(t2, j));
                            zzb(t, i2, i);
                            break;
                        }
                    case 68:
                        zzb(t, t2, i);
                        break;
                }
            }
            if (!this.zzzk) {
                zzhy.zza(this.zzzr, t, t2);
                if (this.zzzi) {
                    zzhy.zza(this.zzzs, t, t2);
                    return;
                }
                return;
            }
            return;
        }
        throw new NullPointerException();
    }

    private final void zza(T t, T t2, int i) {
        long zzbk = (long) (zzbk(i) & 1048575);
        if (zza(t2, i)) {
            Object zzp = zziu.zzp(t, zzbk);
            Object zzp2 = zziu.zzp(t2, zzbk);
            if (zzp == null || zzp2 == null) {
                if (zzp2 != null) {
                    zziu.zza((Object) t, zzbk, zzp2);
                    zzb(t, i);
                }
                return;
            }
            zziu.zza((Object) t, zzbk, zzga.zza(zzp, zzp2));
            zzb(t, i);
        }
    }

    private final void zzb(T t, T t2, int i) {
        int zzbk = zzbk(i);
        int i2 = this.zzzd[i];
        long j = (long) (zzbk & 1048575);
        if (zza(t2, i2, i)) {
            Object zzp = zziu.zzp(t, j);
            Object zzp2 = zziu.zzp(t2, j);
            if (zzp == null || zzp2 == null) {
                if (zzp2 != null) {
                    zziu.zza((Object) t, j, zzp2);
                    zzb(t, i2, i);
                }
                return;
            }
            zziu.zza((Object) t, j, zzga.zza(zzp, zzp2));
            zzb(t, i2, i);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:398:0x0833, code lost:
        r9 = (r9 + r10) + r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:418:0x090c, code lost:
        r13 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:434:0x0954, code lost:
        r5 = r5 + r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:472:0x0a01, code lost:
        r5 = r5 + r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:481:0x0a23, code lost:
        r3 = r3 + 3;
        r9 = r13;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int zzp(T r20) {
        /*
            r19 = this;
            r0 = r19
            r1 = r20
            boolean r2 = r0.zzzk
            r3 = 267386880(0xff00000, float:2.3665827E-29)
            r4 = 0
            r7 = 1
            r8 = 1048575(0xfffff, float:1.469367E-39)
            r9 = 0
            r11 = 0
            if (r2 == 0) goto L_0x04f2
            sun.misc.Unsafe r2 = zzzc
            r12 = 0
            r13 = 0
        L_0x0016:
            int[] r14 = r0.zzzd
            int r14 = r14.length
            if (r12 >= r14) goto L_0x04ea
            int r14 = r0.zzbk(r12)
            r15 = r14 & r3
            int r15 = r15 >>> 20
            int[] r3 = r0.zzzd
            r3 = r3[r12]
            r14 = r14 & r8
            long r5 = (long) r14
            com.google.android.gms.internal.vision.zzfs r14 = com.google.android.gms.internal.vision.zzfs.DOUBLE_LIST_PACKED
            int r14 = r14.mo21583id()
            if (r15 < r14) goto L_0x0041
            com.google.android.gms.internal.vision.zzfs r14 = com.google.android.gms.internal.vision.zzfs.SINT64_LIST_PACKED
            int r14 = r14.mo21583id()
            if (r15 > r14) goto L_0x0041
            int[] r14 = r0.zzzd
            int r17 = r12 + 2
            r14 = r14[r17]
            r14 = r14 & r8
            goto L_0x0042
        L_0x0041:
            r14 = 0
        L_0x0042:
            switch(r15) {
                case 0: goto L_0x04d6;
                case 1: goto L_0x04ca;
                case 2: goto L_0x04ba;
                case 3: goto L_0x04aa;
                case 4: goto L_0x049a;
                case 5: goto L_0x048e;
                case 6: goto L_0x0482;
                case 7: goto L_0x0476;
                case 8: goto L_0x0458;
                case 9: goto L_0x0444;
                case 10: goto L_0x0433;
                case 11: goto L_0x0424;
                case 12: goto L_0x0415;
                case 13: goto L_0x040a;
                case 14: goto L_0x03ff;
                case 15: goto L_0x03f0;
                case 16: goto L_0x03e1;
                case 17: goto L_0x03cc;
                case 18: goto L_0x03c1;
                case 19: goto L_0x03b8;
                case 20: goto L_0x03af;
                case 21: goto L_0x03a6;
                case 22: goto L_0x039d;
                case 23: goto L_0x0394;
                case 24: goto L_0x038b;
                case 25: goto L_0x0382;
                case 26: goto L_0x0379;
                case 27: goto L_0x036c;
                case 28: goto L_0x0363;
                case 29: goto L_0x035a;
                case 30: goto L_0x0350;
                case 31: goto L_0x0346;
                case 32: goto L_0x033c;
                case 33: goto L_0x0332;
                case 34: goto L_0x0328;
                case 35: goto L_0x0308;
                case 36: goto L_0x02eb;
                case 37: goto L_0x02ce;
                case 38: goto L_0x02b1;
                case 39: goto L_0x0293;
                case 40: goto L_0x0275;
                case 41: goto L_0x0257;
                case 42: goto L_0x0239;
                case 43: goto L_0x021b;
                case 44: goto L_0x01fd;
                case 45: goto L_0x01df;
                case 46: goto L_0x01c1;
                case 47: goto L_0x01a3;
                case 48: goto L_0x0185;
                case 49: goto L_0x0177;
                case 50: goto L_0x0167;
                case 51: goto L_0x0159;
                case 52: goto L_0x014d;
                case 53: goto L_0x013d;
                case 54: goto L_0x012d;
                case 55: goto L_0x011d;
                case 56: goto L_0x0111;
                case 57: goto L_0x0105;
                case 58: goto L_0x00f9;
                case 59: goto L_0x00db;
                case 60: goto L_0x00c7;
                case 61: goto L_0x00b5;
                case 62: goto L_0x00a5;
                case 63: goto L_0x0095;
                case 64: goto L_0x0089;
                case 65: goto L_0x007d;
                case 66: goto L_0x006d;
                case 67: goto L_0x005d;
                case 68: goto L_0x0047;
                default: goto L_0x0045;
            }
        L_0x0045:
            goto L_0x04e4
        L_0x0047:
            boolean r14 = r0.zza((T) r1, r3, r12)
            if (r14 == 0) goto L_0x04e4
            java.lang.Object r5 = com.google.android.gms.internal.vision.zziu.zzp(r1, r5)
            com.google.android.gms.internal.vision.zzhf r5 = (com.google.android.gms.internal.vision.zzhf) r5
            com.google.android.gms.internal.vision.zzhw r6 = r0.zzbh(r12)
            int r3 = com.google.android.gms.internal.vision.zzfe.zzc(r3, r5, r6)
            goto L_0x03c9
        L_0x005d:
            boolean r14 = r0.zza((T) r1, r3, r12)
            if (r14 == 0) goto L_0x04e4
            long r5 = zzi(r1, r5)
            int r3 = com.google.android.gms.internal.vision.zzfe.zzf(r3, r5)
            goto L_0x03c9
        L_0x006d:
            boolean r14 = r0.zza((T) r1, r3, r12)
            if (r14 == 0) goto L_0x04e4
            int r5 = zzh(r1, r5)
            int r3 = com.google.android.gms.internal.vision.zzfe.zzk(r3, r5)
            goto L_0x03c9
        L_0x007d:
            boolean r5 = r0.zza((T) r1, r3, r12)
            if (r5 == 0) goto L_0x04e4
            int r3 = com.google.android.gms.internal.vision.zzfe.zzh(r3, r9)
            goto L_0x03c9
        L_0x0089:
            boolean r5 = r0.zza((T) r1, r3, r12)
            if (r5 == 0) goto L_0x04e4
            int r3 = com.google.android.gms.internal.vision.zzfe.zzm(r3, r11)
            goto L_0x03c9
        L_0x0095:
            boolean r14 = r0.zza((T) r1, r3, r12)
            if (r14 == 0) goto L_0x04e4
            int r5 = zzh(r1, r5)
            int r3 = com.google.android.gms.internal.vision.zzfe.zzn(r3, r5)
            goto L_0x03c9
        L_0x00a5:
            boolean r14 = r0.zza((T) r1, r3, r12)
            if (r14 == 0) goto L_0x04e4
            int r5 = zzh(r1, r5)
            int r3 = com.google.android.gms.internal.vision.zzfe.zzj(r3, r5)
            goto L_0x03c9
        L_0x00b5:
            boolean r14 = r0.zza((T) r1, r3, r12)
            if (r14 == 0) goto L_0x04e4
            java.lang.Object r5 = com.google.android.gms.internal.vision.zziu.zzp(r1, r5)
            com.google.android.gms.internal.vision.zzeo r5 = (com.google.android.gms.internal.vision.zzeo) r5
            int r3 = com.google.android.gms.internal.vision.zzfe.zzc(r3, r5)
            goto L_0x03c9
        L_0x00c7:
            boolean r14 = r0.zza((T) r1, r3, r12)
            if (r14 == 0) goto L_0x04e4
            java.lang.Object r5 = com.google.android.gms.internal.vision.zziu.zzp(r1, r5)
            com.google.android.gms.internal.vision.zzhw r6 = r0.zzbh(r12)
            int r3 = com.google.android.gms.internal.vision.zzhy.zzc(r3, r5, r6)
            goto L_0x03c9
        L_0x00db:
            boolean r14 = r0.zza((T) r1, r3, r12)
            if (r14 == 0) goto L_0x04e4
            java.lang.Object r5 = com.google.android.gms.internal.vision.zziu.zzp(r1, r5)
            boolean r6 = r5 instanceof com.google.android.gms.internal.vision.zzeo
            if (r6 == 0) goto L_0x00f1
            com.google.android.gms.internal.vision.zzeo r5 = (com.google.android.gms.internal.vision.zzeo) r5
            int r3 = com.google.android.gms.internal.vision.zzfe.zzc(r3, r5)
            goto L_0x03c9
        L_0x00f1:
            java.lang.String r5 = (java.lang.String) r5
            int r3 = com.google.android.gms.internal.vision.zzfe.zzb(r3, r5)
            goto L_0x03c9
        L_0x00f9:
            boolean r5 = r0.zza((T) r1, r3, r12)
            if (r5 == 0) goto L_0x04e4
            int r3 = com.google.android.gms.internal.vision.zzfe.zzc(r3, r7)
            goto L_0x03c9
        L_0x0105:
            boolean r5 = r0.zza((T) r1, r3, r12)
            if (r5 == 0) goto L_0x04e4
            int r3 = com.google.android.gms.internal.vision.zzfe.zzl(r3, r11)
            goto L_0x03c9
        L_0x0111:
            boolean r5 = r0.zza((T) r1, r3, r12)
            if (r5 == 0) goto L_0x04e4
            int r3 = com.google.android.gms.internal.vision.zzfe.zzg(r3, r9)
            goto L_0x03c9
        L_0x011d:
            boolean r14 = r0.zza((T) r1, r3, r12)
            if (r14 == 0) goto L_0x04e4
            int r5 = zzh(r1, r5)
            int r3 = com.google.android.gms.internal.vision.zzfe.zzi(r3, r5)
            goto L_0x03c9
        L_0x012d:
            boolean r14 = r0.zza((T) r1, r3, r12)
            if (r14 == 0) goto L_0x04e4
            long r5 = zzi(r1, r5)
            int r3 = com.google.android.gms.internal.vision.zzfe.zze(r3, r5)
            goto L_0x03c9
        L_0x013d:
            boolean r14 = r0.zza((T) r1, r3, r12)
            if (r14 == 0) goto L_0x04e4
            long r5 = zzi(r1, r5)
            int r3 = com.google.android.gms.internal.vision.zzfe.zzd(r3, r5)
            goto L_0x03c9
        L_0x014d:
            boolean r5 = r0.zza((T) r1, r3, r12)
            if (r5 == 0) goto L_0x04e4
            int r3 = com.google.android.gms.internal.vision.zzfe.zzb(r3, r4)
            goto L_0x03c9
        L_0x0159:
            boolean r5 = r0.zza((T) r1, r3, r12)
            if (r5 == 0) goto L_0x04e4
            r5 = 0
            int r3 = com.google.android.gms.internal.vision.zzfe.zzb(r3, r5)
            goto L_0x03c9
        L_0x0167:
            com.google.android.gms.internal.vision.zzha r14 = r0.zzzt
            java.lang.Object r5 = com.google.android.gms.internal.vision.zziu.zzp(r1, r5)
            java.lang.Object r6 = r0.zzbi(r12)
            int r3 = r14.zzb(r3, r5, r6)
            goto L_0x03c9
        L_0x0177:
            java.util.List r5 = zze(r1, r5)
            com.google.android.gms.internal.vision.zzhw r6 = r0.zzbh(r12)
            int r3 = com.google.android.gms.internal.vision.zzhy.zzd(r3, r5, r6)
            goto L_0x03c9
        L_0x0185:
            java.lang.Object r5 = r2.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.gms.internal.vision.zzhy.zzs(r5)
            if (r5 <= 0) goto L_0x04e4
            boolean r6 = r0.zzzl
            if (r6 == 0) goto L_0x0199
            long r14 = (long) r14
            r2.putInt(r1, r14, r5)
        L_0x0199:
            int r3 = com.google.android.gms.internal.vision.zzfe.zzav(r3)
            int r6 = com.google.android.gms.internal.vision.zzfe.zzax(r5)
            goto L_0x0324
        L_0x01a3:
            java.lang.Object r5 = r2.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.gms.internal.vision.zzhy.zzw(r5)
            if (r5 <= 0) goto L_0x04e4
            boolean r6 = r0.zzzl
            if (r6 == 0) goto L_0x01b7
            long r14 = (long) r14
            r2.putInt(r1, r14, r5)
        L_0x01b7:
            int r3 = com.google.android.gms.internal.vision.zzfe.zzav(r3)
            int r6 = com.google.android.gms.internal.vision.zzfe.zzax(r5)
            goto L_0x0324
        L_0x01c1:
            java.lang.Object r5 = r2.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.gms.internal.vision.zzhy.zzy(r5)
            if (r5 <= 0) goto L_0x04e4
            boolean r6 = r0.zzzl
            if (r6 == 0) goto L_0x01d5
            long r14 = (long) r14
            r2.putInt(r1, r14, r5)
        L_0x01d5:
            int r3 = com.google.android.gms.internal.vision.zzfe.zzav(r3)
            int r6 = com.google.android.gms.internal.vision.zzfe.zzax(r5)
            goto L_0x0324
        L_0x01df:
            java.lang.Object r5 = r2.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.gms.internal.vision.zzhy.zzx(r5)
            if (r5 <= 0) goto L_0x04e4
            boolean r6 = r0.zzzl
            if (r6 == 0) goto L_0x01f3
            long r14 = (long) r14
            r2.putInt(r1, r14, r5)
        L_0x01f3:
            int r3 = com.google.android.gms.internal.vision.zzfe.zzav(r3)
            int r6 = com.google.android.gms.internal.vision.zzfe.zzax(r5)
            goto L_0x0324
        L_0x01fd:
            java.lang.Object r5 = r2.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.gms.internal.vision.zzhy.zzt(r5)
            if (r5 <= 0) goto L_0x04e4
            boolean r6 = r0.zzzl
            if (r6 == 0) goto L_0x0211
            long r14 = (long) r14
            r2.putInt(r1, r14, r5)
        L_0x0211:
            int r3 = com.google.android.gms.internal.vision.zzfe.zzav(r3)
            int r6 = com.google.android.gms.internal.vision.zzfe.zzax(r5)
            goto L_0x0324
        L_0x021b:
            java.lang.Object r5 = r2.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.gms.internal.vision.zzhy.zzv(r5)
            if (r5 <= 0) goto L_0x04e4
            boolean r6 = r0.zzzl
            if (r6 == 0) goto L_0x022f
            long r14 = (long) r14
            r2.putInt(r1, r14, r5)
        L_0x022f:
            int r3 = com.google.android.gms.internal.vision.zzfe.zzav(r3)
            int r6 = com.google.android.gms.internal.vision.zzfe.zzax(r5)
            goto L_0x0324
        L_0x0239:
            java.lang.Object r5 = r2.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.gms.internal.vision.zzhy.zzz(r5)
            if (r5 <= 0) goto L_0x04e4
            boolean r6 = r0.zzzl
            if (r6 == 0) goto L_0x024d
            long r14 = (long) r14
            r2.putInt(r1, r14, r5)
        L_0x024d:
            int r3 = com.google.android.gms.internal.vision.zzfe.zzav(r3)
            int r6 = com.google.android.gms.internal.vision.zzfe.zzax(r5)
            goto L_0x0324
        L_0x0257:
            java.lang.Object r5 = r2.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.gms.internal.vision.zzhy.zzx(r5)
            if (r5 <= 0) goto L_0x04e4
            boolean r6 = r0.zzzl
            if (r6 == 0) goto L_0x026b
            long r14 = (long) r14
            r2.putInt(r1, r14, r5)
        L_0x026b:
            int r3 = com.google.android.gms.internal.vision.zzfe.zzav(r3)
            int r6 = com.google.android.gms.internal.vision.zzfe.zzax(r5)
            goto L_0x0324
        L_0x0275:
            java.lang.Object r5 = r2.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.gms.internal.vision.zzhy.zzy(r5)
            if (r5 <= 0) goto L_0x04e4
            boolean r6 = r0.zzzl
            if (r6 == 0) goto L_0x0289
            long r14 = (long) r14
            r2.putInt(r1, r14, r5)
        L_0x0289:
            int r3 = com.google.android.gms.internal.vision.zzfe.zzav(r3)
            int r6 = com.google.android.gms.internal.vision.zzfe.zzax(r5)
            goto L_0x0324
        L_0x0293:
            java.lang.Object r5 = r2.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.gms.internal.vision.zzhy.zzu(r5)
            if (r5 <= 0) goto L_0x04e4
            boolean r6 = r0.zzzl
            if (r6 == 0) goto L_0x02a7
            long r14 = (long) r14
            r2.putInt(r1, r14, r5)
        L_0x02a7:
            int r3 = com.google.android.gms.internal.vision.zzfe.zzav(r3)
            int r6 = com.google.android.gms.internal.vision.zzfe.zzax(r5)
            goto L_0x0324
        L_0x02b1:
            java.lang.Object r5 = r2.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.gms.internal.vision.zzhy.zzr(r5)
            if (r5 <= 0) goto L_0x04e4
            boolean r6 = r0.zzzl
            if (r6 == 0) goto L_0x02c5
            long r14 = (long) r14
            r2.putInt(r1, r14, r5)
        L_0x02c5:
            int r3 = com.google.android.gms.internal.vision.zzfe.zzav(r3)
            int r6 = com.google.android.gms.internal.vision.zzfe.zzax(r5)
            goto L_0x0324
        L_0x02ce:
            java.lang.Object r5 = r2.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.gms.internal.vision.zzhy.zzq(r5)
            if (r5 <= 0) goto L_0x04e4
            boolean r6 = r0.zzzl
            if (r6 == 0) goto L_0x02e2
            long r14 = (long) r14
            r2.putInt(r1, r14, r5)
        L_0x02e2:
            int r3 = com.google.android.gms.internal.vision.zzfe.zzav(r3)
            int r6 = com.google.android.gms.internal.vision.zzfe.zzax(r5)
            goto L_0x0324
        L_0x02eb:
            java.lang.Object r5 = r2.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.gms.internal.vision.zzhy.zzx(r5)
            if (r5 <= 0) goto L_0x04e4
            boolean r6 = r0.zzzl
            if (r6 == 0) goto L_0x02ff
            long r14 = (long) r14
            r2.putInt(r1, r14, r5)
        L_0x02ff:
            int r3 = com.google.android.gms.internal.vision.zzfe.zzav(r3)
            int r6 = com.google.android.gms.internal.vision.zzfe.zzax(r5)
            goto L_0x0324
        L_0x0308:
            java.lang.Object r5 = r2.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.gms.internal.vision.zzhy.zzy(r5)
            if (r5 <= 0) goto L_0x04e4
            boolean r6 = r0.zzzl
            if (r6 == 0) goto L_0x031c
            long r14 = (long) r14
            r2.putInt(r1, r14, r5)
        L_0x031c:
            int r3 = com.google.android.gms.internal.vision.zzfe.zzav(r3)
            int r6 = com.google.android.gms.internal.vision.zzfe.zzax(r5)
        L_0x0324:
            int r3 = r3 + r6
            int r3 = r3 + r5
            goto L_0x03c9
        L_0x0328:
            java.util.List r5 = zze(r1, r5)
            int r3 = com.google.android.gms.internal.vision.zzhy.zzq(r3, r5, r11)
            goto L_0x03c9
        L_0x0332:
            java.util.List r5 = zze(r1, r5)
            int r3 = com.google.android.gms.internal.vision.zzhy.zzu(r3, r5, r11)
            goto L_0x03c9
        L_0x033c:
            java.util.List r5 = zze(r1, r5)
            int r3 = com.google.android.gms.internal.vision.zzhy.zzw(r3, r5, r11)
            goto L_0x03c9
        L_0x0346:
            java.util.List r5 = zze(r1, r5)
            int r3 = com.google.android.gms.internal.vision.zzhy.zzv(r3, r5, r11)
            goto L_0x03c9
        L_0x0350:
            java.util.List r5 = zze(r1, r5)
            int r3 = com.google.android.gms.internal.vision.zzhy.zzr(r3, r5, r11)
            goto L_0x03c9
        L_0x035a:
            java.util.List r5 = zze(r1, r5)
            int r3 = com.google.android.gms.internal.vision.zzhy.zzt(r3, r5, r11)
            goto L_0x03c9
        L_0x0363:
            java.util.List r5 = zze(r1, r5)
            int r3 = com.google.android.gms.internal.vision.zzhy.zzd(r3, r5)
            goto L_0x03c9
        L_0x036c:
            java.util.List r5 = zze(r1, r5)
            com.google.android.gms.internal.vision.zzhw r6 = r0.zzbh(r12)
            int r3 = com.google.android.gms.internal.vision.zzhy.zzc(r3, r5, r6)
            goto L_0x03c9
        L_0x0379:
            java.util.List r5 = zze(r1, r5)
            int r3 = com.google.android.gms.internal.vision.zzhy.zzc(r3, r5)
            goto L_0x03c9
        L_0x0382:
            java.util.List r5 = zze(r1, r5)
            int r3 = com.google.android.gms.internal.vision.zzhy.zzx(r3, r5, r11)
            goto L_0x03c9
        L_0x038b:
            java.util.List r5 = zze(r1, r5)
            int r3 = com.google.android.gms.internal.vision.zzhy.zzv(r3, r5, r11)
            goto L_0x03c9
        L_0x0394:
            java.util.List r5 = zze(r1, r5)
            int r3 = com.google.android.gms.internal.vision.zzhy.zzw(r3, r5, r11)
            goto L_0x03c9
        L_0x039d:
            java.util.List r5 = zze(r1, r5)
            int r3 = com.google.android.gms.internal.vision.zzhy.zzs(r3, r5, r11)
            goto L_0x03c9
        L_0x03a6:
            java.util.List r5 = zze(r1, r5)
            int r3 = com.google.android.gms.internal.vision.zzhy.zzp(r3, r5, r11)
            goto L_0x03c9
        L_0x03af:
            java.util.List r5 = zze(r1, r5)
            int r3 = com.google.android.gms.internal.vision.zzhy.zzo(r3, r5, r11)
            goto L_0x03c9
        L_0x03b8:
            java.util.List r5 = zze(r1, r5)
            int r3 = com.google.android.gms.internal.vision.zzhy.zzv(r3, r5, r11)
            goto L_0x03c9
        L_0x03c1:
            java.util.List r5 = zze(r1, r5)
            int r3 = com.google.android.gms.internal.vision.zzhy.zzw(r3, r5, r11)
        L_0x03c9:
            int r13 = r13 + r3
            goto L_0x04e4
        L_0x03cc:
            boolean r14 = r0.zza((T) r1, r12)
            if (r14 == 0) goto L_0x04e4
            java.lang.Object r5 = com.google.android.gms.internal.vision.zziu.zzp(r1, r5)
            com.google.android.gms.internal.vision.zzhf r5 = (com.google.android.gms.internal.vision.zzhf) r5
            com.google.android.gms.internal.vision.zzhw r6 = r0.zzbh(r12)
            int r3 = com.google.android.gms.internal.vision.zzfe.zzc(r3, r5, r6)
            goto L_0x03c9
        L_0x03e1:
            boolean r14 = r0.zza((T) r1, r12)
            if (r14 == 0) goto L_0x04e4
            long r5 = com.google.android.gms.internal.vision.zziu.zzl(r1, r5)
            int r3 = com.google.android.gms.internal.vision.zzfe.zzf(r3, r5)
            goto L_0x03c9
        L_0x03f0:
            boolean r14 = r0.zza((T) r1, r12)
            if (r14 == 0) goto L_0x04e4
            int r5 = com.google.android.gms.internal.vision.zziu.zzk(r1, r5)
            int r3 = com.google.android.gms.internal.vision.zzfe.zzk(r3, r5)
            goto L_0x03c9
        L_0x03ff:
            boolean r5 = r0.zza((T) r1, r12)
            if (r5 == 0) goto L_0x04e4
            int r3 = com.google.android.gms.internal.vision.zzfe.zzh(r3, r9)
            goto L_0x03c9
        L_0x040a:
            boolean r5 = r0.zza((T) r1, r12)
            if (r5 == 0) goto L_0x04e4
            int r3 = com.google.android.gms.internal.vision.zzfe.zzm(r3, r11)
            goto L_0x03c9
        L_0x0415:
            boolean r14 = r0.zza((T) r1, r12)
            if (r14 == 0) goto L_0x04e4
            int r5 = com.google.android.gms.internal.vision.zziu.zzk(r1, r5)
            int r3 = com.google.android.gms.internal.vision.zzfe.zzn(r3, r5)
            goto L_0x03c9
        L_0x0424:
            boolean r14 = r0.zza((T) r1, r12)
            if (r14 == 0) goto L_0x04e4
            int r5 = com.google.android.gms.internal.vision.zziu.zzk(r1, r5)
            int r3 = com.google.android.gms.internal.vision.zzfe.zzj(r3, r5)
            goto L_0x03c9
        L_0x0433:
            boolean r14 = r0.zza((T) r1, r12)
            if (r14 == 0) goto L_0x04e4
            java.lang.Object r5 = com.google.android.gms.internal.vision.zziu.zzp(r1, r5)
            com.google.android.gms.internal.vision.zzeo r5 = (com.google.android.gms.internal.vision.zzeo) r5
            int r3 = com.google.android.gms.internal.vision.zzfe.zzc(r3, r5)
            goto L_0x03c9
        L_0x0444:
            boolean r14 = r0.zza((T) r1, r12)
            if (r14 == 0) goto L_0x04e4
            java.lang.Object r5 = com.google.android.gms.internal.vision.zziu.zzp(r1, r5)
            com.google.android.gms.internal.vision.zzhw r6 = r0.zzbh(r12)
            int r3 = com.google.android.gms.internal.vision.zzhy.zzc(r3, r5, r6)
            goto L_0x03c9
        L_0x0458:
            boolean r14 = r0.zza((T) r1, r12)
            if (r14 == 0) goto L_0x04e4
            java.lang.Object r5 = com.google.android.gms.internal.vision.zziu.zzp(r1, r5)
            boolean r6 = r5 instanceof com.google.android.gms.internal.vision.zzeo
            if (r6 == 0) goto L_0x046e
            com.google.android.gms.internal.vision.zzeo r5 = (com.google.android.gms.internal.vision.zzeo) r5
            int r3 = com.google.android.gms.internal.vision.zzfe.zzc(r3, r5)
            goto L_0x03c9
        L_0x046e:
            java.lang.String r5 = (java.lang.String) r5
            int r3 = com.google.android.gms.internal.vision.zzfe.zzb(r3, r5)
            goto L_0x03c9
        L_0x0476:
            boolean r5 = r0.zza((T) r1, r12)
            if (r5 == 0) goto L_0x04e4
            int r3 = com.google.android.gms.internal.vision.zzfe.zzc(r3, r7)
            goto L_0x03c9
        L_0x0482:
            boolean r5 = r0.zza((T) r1, r12)
            if (r5 == 0) goto L_0x04e4
            int r3 = com.google.android.gms.internal.vision.zzfe.zzl(r3, r11)
            goto L_0x03c9
        L_0x048e:
            boolean r5 = r0.zza((T) r1, r12)
            if (r5 == 0) goto L_0x04e4
            int r3 = com.google.android.gms.internal.vision.zzfe.zzg(r3, r9)
            goto L_0x03c9
        L_0x049a:
            boolean r14 = r0.zza((T) r1, r12)
            if (r14 == 0) goto L_0x04e4
            int r5 = com.google.android.gms.internal.vision.zziu.zzk(r1, r5)
            int r3 = com.google.android.gms.internal.vision.zzfe.zzi(r3, r5)
            goto L_0x03c9
        L_0x04aa:
            boolean r14 = r0.zza((T) r1, r12)
            if (r14 == 0) goto L_0x04e4
            long r5 = com.google.android.gms.internal.vision.zziu.zzl(r1, r5)
            int r3 = com.google.android.gms.internal.vision.zzfe.zze(r3, r5)
            goto L_0x03c9
        L_0x04ba:
            boolean r14 = r0.zza((T) r1, r12)
            if (r14 == 0) goto L_0x04e4
            long r5 = com.google.android.gms.internal.vision.zziu.zzl(r1, r5)
            int r3 = com.google.android.gms.internal.vision.zzfe.zzd(r3, r5)
            goto L_0x03c9
        L_0x04ca:
            boolean r5 = r0.zza((T) r1, r12)
            if (r5 == 0) goto L_0x04e4
            int r3 = com.google.android.gms.internal.vision.zzfe.zzb(r3, r4)
            goto L_0x03c9
        L_0x04d6:
            boolean r5 = r0.zza((T) r1, r12)
            if (r5 == 0) goto L_0x04e4
            r5 = 0
            int r3 = com.google.android.gms.internal.vision.zzfe.zzb(r3, r5)
            goto L_0x03c9
        L_0x04e4:
            int r12 = r12 + 3
            r3 = 267386880(0xff00000, float:2.3665827E-29)
            goto L_0x0016
        L_0x04ea:
            com.google.android.gms.internal.vision.zzio<?, ?> r2 = r0.zzzr
            int r1 = zza(r2, (T) r1)
            int r13 = r13 + r1
            return r13
        L_0x04f2:
            sun.misc.Unsafe r2 = zzzc
            r3 = -1
            r3 = 0
            r5 = 0
            r6 = -1
            r12 = 0
        L_0x04f9:
            int[] r13 = r0.zzzd
            int r13 = r13.length
            if (r3 >= r13) goto L_0x0a2a
            int r13 = r0.zzbk(r3)
            int[] r14 = r0.zzzd
            r15 = r14[r3]
            r16 = 267386880(0xff00000, float:2.3665827E-29)
            r17 = r13 & r16
            int r4 = r17 >>> 20
            r11 = 17
            if (r4 > r11) goto L_0x0525
            int r11 = r3 + 2
            r11 = r14[r11]
            r14 = r11 & r8
            int r18 = r11 >>> 20
            int r18 = r7 << r18
            if (r14 == r6) goto L_0x0522
            long r9 = (long) r14
            int r12 = r2.getInt(r1, r9)
            goto L_0x0523
        L_0x0522:
            r14 = r6
        L_0x0523:
            r6 = r14
            goto L_0x0545
        L_0x0525:
            boolean r9 = r0.zzzl
            if (r9 == 0) goto L_0x0542
            com.google.android.gms.internal.vision.zzfs r9 = com.google.android.gms.internal.vision.zzfs.DOUBLE_LIST_PACKED
            int r9 = r9.mo21583id()
            if (r4 < r9) goto L_0x0542
            com.google.android.gms.internal.vision.zzfs r9 = com.google.android.gms.internal.vision.zzfs.SINT64_LIST_PACKED
            int r9 = r9.mo21583id()
            if (r4 > r9) goto L_0x0542
            int[] r9 = r0.zzzd
            int r10 = r3 + 2
            r9 = r9[r10]
            r11 = r9 & r8
            goto L_0x0543
        L_0x0542:
            r11 = 0
        L_0x0543:
            r18 = 0
        L_0x0545:
            r9 = r13 & r8
            long r9 = (long) r9
            switch(r4) {
                case 0: goto L_0x0a14;
                case 1: goto L_0x0a04;
                case 2: goto L_0x09f2;
                case 3: goto L_0x09e2;
                case 4: goto L_0x09d2;
                case 5: goto L_0x09c3;
                case 6: goto L_0x09b7;
                case 7: goto L_0x09ad;
                case 8: goto L_0x0991;
                case 9: goto L_0x097f;
                case 10: goto L_0x0970;
                case 11: goto L_0x0963;
                case 12: goto L_0x0956;
                case 13: goto L_0x094b;
                case 14: goto L_0x0940;
                case 15: goto L_0x0933;
                case 16: goto L_0x0926;
                case 17: goto L_0x0913;
                case 18: goto L_0x08ff;
                case 19: goto L_0x08f3;
                case 20: goto L_0x08e7;
                case 21: goto L_0x08db;
                case 22: goto L_0x08cf;
                case 23: goto L_0x08c3;
                case 24: goto L_0x08b7;
                case 25: goto L_0x08ab;
                case 26: goto L_0x08a0;
                case 27: goto L_0x0891;
                case 28: goto L_0x0885;
                case 29: goto L_0x0878;
                case 30: goto L_0x086b;
                case 31: goto L_0x085e;
                case 32: goto L_0x0851;
                case 33: goto L_0x0844;
                case 34: goto L_0x0837;
                case 35: goto L_0x0817;
                case 36: goto L_0x07fa;
                case 37: goto L_0x07dd;
                case 38: goto L_0x07c0;
                case 39: goto L_0x07a2;
                case 40: goto L_0x0784;
                case 41: goto L_0x0766;
                case 42: goto L_0x0748;
                case 43: goto L_0x072a;
                case 44: goto L_0x070c;
                case 45: goto L_0x06ee;
                case 46: goto L_0x06d0;
                case 47: goto L_0x06b2;
                case 48: goto L_0x0694;
                case 49: goto L_0x0684;
                case 50: goto L_0x0674;
                case 51: goto L_0x0666;
                case 52: goto L_0x0659;
                case 53: goto L_0x0649;
                case 54: goto L_0x0639;
                case 55: goto L_0x0629;
                case 56: goto L_0x061b;
                case 57: goto L_0x060e;
                case 58: goto L_0x0602;
                case 59: goto L_0x05e4;
                case 60: goto L_0x05d0;
                case 61: goto L_0x05be;
                case 62: goto L_0x05ae;
                case 63: goto L_0x059e;
                case 64: goto L_0x0591;
                case 65: goto L_0x0583;
                case 66: goto L_0x0573;
                case 67: goto L_0x0563;
                case 68: goto L_0x054d;
                default: goto L_0x054b;
            }
        L_0x054b:
            goto L_0x090b
        L_0x054d:
            boolean r4 = r0.zza((T) r1, r15, r3)
            if (r4 == 0) goto L_0x090b
            java.lang.Object r4 = r2.getObject(r1, r9)
            com.google.android.gms.internal.vision.zzhf r4 = (com.google.android.gms.internal.vision.zzhf) r4
            com.google.android.gms.internal.vision.zzhw r9 = r0.zzbh(r3)
            int r4 = com.google.android.gms.internal.vision.zzfe.zzc(r15, r4, r9)
            goto L_0x090a
        L_0x0563:
            boolean r4 = r0.zza((T) r1, r15, r3)
            if (r4 == 0) goto L_0x090b
            long r9 = zzi(r1, r9)
            int r4 = com.google.android.gms.internal.vision.zzfe.zzf(r15, r9)
            goto L_0x090a
        L_0x0573:
            boolean r4 = r0.zza((T) r1, r15, r3)
            if (r4 == 0) goto L_0x090b
            int r4 = zzh(r1, r9)
            int r4 = com.google.android.gms.internal.vision.zzfe.zzk(r15, r4)
            goto L_0x090a
        L_0x0583:
            boolean r4 = r0.zza((T) r1, r15, r3)
            if (r4 == 0) goto L_0x090b
            r9 = 0
            int r4 = com.google.android.gms.internal.vision.zzfe.zzh(r15, r9)
            goto L_0x090a
        L_0x0591:
            boolean r4 = r0.zza((T) r1, r15, r3)
            if (r4 == 0) goto L_0x090b
            r4 = 0
            int r9 = com.google.android.gms.internal.vision.zzfe.zzm(r15, r4)
            goto L_0x0954
        L_0x059e:
            boolean r4 = r0.zza((T) r1, r15, r3)
            if (r4 == 0) goto L_0x090b
            int r4 = zzh(r1, r9)
            int r4 = com.google.android.gms.internal.vision.zzfe.zzn(r15, r4)
            goto L_0x090a
        L_0x05ae:
            boolean r4 = r0.zza((T) r1, r15, r3)
            if (r4 == 0) goto L_0x090b
            int r4 = zzh(r1, r9)
            int r4 = com.google.android.gms.internal.vision.zzfe.zzj(r15, r4)
            goto L_0x090a
        L_0x05be:
            boolean r4 = r0.zza((T) r1, r15, r3)
            if (r4 == 0) goto L_0x090b
            java.lang.Object r4 = r2.getObject(r1, r9)
            com.google.android.gms.internal.vision.zzeo r4 = (com.google.android.gms.internal.vision.zzeo) r4
            int r4 = com.google.android.gms.internal.vision.zzfe.zzc(r15, r4)
            goto L_0x090a
        L_0x05d0:
            boolean r4 = r0.zza((T) r1, r15, r3)
            if (r4 == 0) goto L_0x090b
            java.lang.Object r4 = r2.getObject(r1, r9)
            com.google.android.gms.internal.vision.zzhw r9 = r0.zzbh(r3)
            int r4 = com.google.android.gms.internal.vision.zzhy.zzc(r15, r4, r9)
            goto L_0x090a
        L_0x05e4:
            boolean r4 = r0.zza((T) r1, r15, r3)
            if (r4 == 0) goto L_0x090b
            java.lang.Object r4 = r2.getObject(r1, r9)
            boolean r9 = r4 instanceof com.google.android.gms.internal.vision.zzeo
            if (r9 == 0) goto L_0x05fa
            com.google.android.gms.internal.vision.zzeo r4 = (com.google.android.gms.internal.vision.zzeo) r4
            int r4 = com.google.android.gms.internal.vision.zzfe.zzc(r15, r4)
            goto L_0x090a
        L_0x05fa:
            java.lang.String r4 = (java.lang.String) r4
            int r4 = com.google.android.gms.internal.vision.zzfe.zzb(r15, r4)
            goto L_0x090a
        L_0x0602:
            boolean r4 = r0.zza((T) r1, r15, r3)
            if (r4 == 0) goto L_0x090b
            int r4 = com.google.android.gms.internal.vision.zzfe.zzc(r15, r7)
            goto L_0x090a
        L_0x060e:
            boolean r4 = r0.zza((T) r1, r15, r3)
            if (r4 == 0) goto L_0x090b
            r4 = 0
            int r9 = com.google.android.gms.internal.vision.zzfe.zzl(r15, r4)
            goto L_0x0954
        L_0x061b:
            boolean r4 = r0.zza((T) r1, r15, r3)
            if (r4 == 0) goto L_0x090b
            r9 = 0
            int r4 = com.google.android.gms.internal.vision.zzfe.zzg(r15, r9)
            goto L_0x090a
        L_0x0629:
            boolean r4 = r0.zza((T) r1, r15, r3)
            if (r4 == 0) goto L_0x090b
            int r4 = zzh(r1, r9)
            int r4 = com.google.android.gms.internal.vision.zzfe.zzi(r15, r4)
            goto L_0x090a
        L_0x0639:
            boolean r4 = r0.zza((T) r1, r15, r3)
            if (r4 == 0) goto L_0x090b
            long r9 = zzi(r1, r9)
            int r4 = com.google.android.gms.internal.vision.zzfe.zze(r15, r9)
            goto L_0x090a
        L_0x0649:
            boolean r4 = r0.zza((T) r1, r15, r3)
            if (r4 == 0) goto L_0x090b
            long r9 = zzi(r1, r9)
            int r4 = com.google.android.gms.internal.vision.zzfe.zzd(r15, r9)
            goto L_0x090a
        L_0x0659:
            boolean r4 = r0.zza((T) r1, r15, r3)
            if (r4 == 0) goto L_0x090b
            r4 = 0
            int r9 = com.google.android.gms.internal.vision.zzfe.zzb(r15, r4)
            goto L_0x0954
        L_0x0666:
            boolean r4 = r0.zza((T) r1, r15, r3)
            if (r4 == 0) goto L_0x090b
            r9 = 0
            int r4 = com.google.android.gms.internal.vision.zzfe.zzb(r15, r9)
            goto L_0x090a
        L_0x0674:
            com.google.android.gms.internal.vision.zzha r4 = r0.zzzt
            java.lang.Object r9 = r2.getObject(r1, r9)
            java.lang.Object r10 = r0.zzbi(r3)
            int r4 = r4.zzb(r15, r9, r10)
            goto L_0x090a
        L_0x0684:
            java.lang.Object r4 = r2.getObject(r1, r9)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.vision.zzhw r9 = r0.zzbh(r3)
            int r4 = com.google.android.gms.internal.vision.zzhy.zzd(r15, r4, r9)
            goto L_0x090a
        L_0x0694:
            java.lang.Object r4 = r2.getObject(r1, r9)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.vision.zzhy.zzs(r4)
            if (r4 <= 0) goto L_0x090b
            boolean r9 = r0.zzzl
            if (r9 == 0) goto L_0x06a8
            long r9 = (long) r11
            r2.putInt(r1, r9, r4)
        L_0x06a8:
            int r9 = com.google.android.gms.internal.vision.zzfe.zzav(r15)
            int r10 = com.google.android.gms.internal.vision.zzfe.zzax(r4)
            goto L_0x0833
        L_0x06b2:
            java.lang.Object r4 = r2.getObject(r1, r9)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.vision.zzhy.zzw(r4)
            if (r4 <= 0) goto L_0x090b
            boolean r9 = r0.zzzl
            if (r9 == 0) goto L_0x06c6
            long r9 = (long) r11
            r2.putInt(r1, r9, r4)
        L_0x06c6:
            int r9 = com.google.android.gms.internal.vision.zzfe.zzav(r15)
            int r10 = com.google.android.gms.internal.vision.zzfe.zzax(r4)
            goto L_0x0833
        L_0x06d0:
            java.lang.Object r4 = r2.getObject(r1, r9)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.vision.zzhy.zzy(r4)
            if (r4 <= 0) goto L_0x090b
            boolean r9 = r0.zzzl
            if (r9 == 0) goto L_0x06e4
            long r9 = (long) r11
            r2.putInt(r1, r9, r4)
        L_0x06e4:
            int r9 = com.google.android.gms.internal.vision.zzfe.zzav(r15)
            int r10 = com.google.android.gms.internal.vision.zzfe.zzax(r4)
            goto L_0x0833
        L_0x06ee:
            java.lang.Object r4 = r2.getObject(r1, r9)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.vision.zzhy.zzx(r4)
            if (r4 <= 0) goto L_0x090b
            boolean r9 = r0.zzzl
            if (r9 == 0) goto L_0x0702
            long r9 = (long) r11
            r2.putInt(r1, r9, r4)
        L_0x0702:
            int r9 = com.google.android.gms.internal.vision.zzfe.zzav(r15)
            int r10 = com.google.android.gms.internal.vision.zzfe.zzax(r4)
            goto L_0x0833
        L_0x070c:
            java.lang.Object r4 = r2.getObject(r1, r9)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.vision.zzhy.zzt(r4)
            if (r4 <= 0) goto L_0x090b
            boolean r9 = r0.zzzl
            if (r9 == 0) goto L_0x0720
            long r9 = (long) r11
            r2.putInt(r1, r9, r4)
        L_0x0720:
            int r9 = com.google.android.gms.internal.vision.zzfe.zzav(r15)
            int r10 = com.google.android.gms.internal.vision.zzfe.zzax(r4)
            goto L_0x0833
        L_0x072a:
            java.lang.Object r4 = r2.getObject(r1, r9)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.vision.zzhy.zzv(r4)
            if (r4 <= 0) goto L_0x090b
            boolean r9 = r0.zzzl
            if (r9 == 0) goto L_0x073e
            long r9 = (long) r11
            r2.putInt(r1, r9, r4)
        L_0x073e:
            int r9 = com.google.android.gms.internal.vision.zzfe.zzav(r15)
            int r10 = com.google.android.gms.internal.vision.zzfe.zzax(r4)
            goto L_0x0833
        L_0x0748:
            java.lang.Object r4 = r2.getObject(r1, r9)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.vision.zzhy.zzz(r4)
            if (r4 <= 0) goto L_0x090b
            boolean r9 = r0.zzzl
            if (r9 == 0) goto L_0x075c
            long r9 = (long) r11
            r2.putInt(r1, r9, r4)
        L_0x075c:
            int r9 = com.google.android.gms.internal.vision.zzfe.zzav(r15)
            int r10 = com.google.android.gms.internal.vision.zzfe.zzax(r4)
            goto L_0x0833
        L_0x0766:
            java.lang.Object r4 = r2.getObject(r1, r9)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.vision.zzhy.zzx(r4)
            if (r4 <= 0) goto L_0x090b
            boolean r9 = r0.zzzl
            if (r9 == 0) goto L_0x077a
            long r9 = (long) r11
            r2.putInt(r1, r9, r4)
        L_0x077a:
            int r9 = com.google.android.gms.internal.vision.zzfe.zzav(r15)
            int r10 = com.google.android.gms.internal.vision.zzfe.zzax(r4)
            goto L_0x0833
        L_0x0784:
            java.lang.Object r4 = r2.getObject(r1, r9)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.vision.zzhy.zzy(r4)
            if (r4 <= 0) goto L_0x090b
            boolean r9 = r0.zzzl
            if (r9 == 0) goto L_0x0798
            long r9 = (long) r11
            r2.putInt(r1, r9, r4)
        L_0x0798:
            int r9 = com.google.android.gms.internal.vision.zzfe.zzav(r15)
            int r10 = com.google.android.gms.internal.vision.zzfe.zzax(r4)
            goto L_0x0833
        L_0x07a2:
            java.lang.Object r4 = r2.getObject(r1, r9)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.vision.zzhy.zzu(r4)
            if (r4 <= 0) goto L_0x090b
            boolean r9 = r0.zzzl
            if (r9 == 0) goto L_0x07b6
            long r9 = (long) r11
            r2.putInt(r1, r9, r4)
        L_0x07b6:
            int r9 = com.google.android.gms.internal.vision.zzfe.zzav(r15)
            int r10 = com.google.android.gms.internal.vision.zzfe.zzax(r4)
            goto L_0x0833
        L_0x07c0:
            java.lang.Object r4 = r2.getObject(r1, r9)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.vision.zzhy.zzr(r4)
            if (r4 <= 0) goto L_0x090b
            boolean r9 = r0.zzzl
            if (r9 == 0) goto L_0x07d4
            long r9 = (long) r11
            r2.putInt(r1, r9, r4)
        L_0x07d4:
            int r9 = com.google.android.gms.internal.vision.zzfe.zzav(r15)
            int r10 = com.google.android.gms.internal.vision.zzfe.zzax(r4)
            goto L_0x0833
        L_0x07dd:
            java.lang.Object r4 = r2.getObject(r1, r9)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.vision.zzhy.zzq(r4)
            if (r4 <= 0) goto L_0x090b
            boolean r9 = r0.zzzl
            if (r9 == 0) goto L_0x07f1
            long r9 = (long) r11
            r2.putInt(r1, r9, r4)
        L_0x07f1:
            int r9 = com.google.android.gms.internal.vision.zzfe.zzav(r15)
            int r10 = com.google.android.gms.internal.vision.zzfe.zzax(r4)
            goto L_0x0833
        L_0x07fa:
            java.lang.Object r4 = r2.getObject(r1, r9)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.vision.zzhy.zzx(r4)
            if (r4 <= 0) goto L_0x090b
            boolean r9 = r0.zzzl
            if (r9 == 0) goto L_0x080e
            long r9 = (long) r11
            r2.putInt(r1, r9, r4)
        L_0x080e:
            int r9 = com.google.android.gms.internal.vision.zzfe.zzav(r15)
            int r10 = com.google.android.gms.internal.vision.zzfe.zzax(r4)
            goto L_0x0833
        L_0x0817:
            java.lang.Object r4 = r2.getObject(r1, r9)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.vision.zzhy.zzy(r4)
            if (r4 <= 0) goto L_0x090b
            boolean r9 = r0.zzzl
            if (r9 == 0) goto L_0x082b
            long r9 = (long) r11
            r2.putInt(r1, r9, r4)
        L_0x082b:
            int r9 = com.google.android.gms.internal.vision.zzfe.zzav(r15)
            int r10 = com.google.android.gms.internal.vision.zzfe.zzax(r4)
        L_0x0833:
            int r9 = r9 + r10
            int r9 = r9 + r4
            goto L_0x0954
        L_0x0837:
            java.lang.Object r4 = r2.getObject(r1, r9)
            java.util.List r4 = (java.util.List) r4
            r11 = 0
            int r4 = com.google.android.gms.internal.vision.zzhy.zzq(r15, r4, r11)
            goto L_0x090a
        L_0x0844:
            r11 = 0
            java.lang.Object r4 = r2.getObject(r1, r9)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.vision.zzhy.zzu(r15, r4, r11)
            goto L_0x090a
        L_0x0851:
            r11 = 0
            java.lang.Object r4 = r2.getObject(r1, r9)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.vision.zzhy.zzw(r15, r4, r11)
            goto L_0x090a
        L_0x085e:
            r11 = 0
            java.lang.Object r4 = r2.getObject(r1, r9)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.vision.zzhy.zzv(r15, r4, r11)
            goto L_0x090a
        L_0x086b:
            r11 = 0
            java.lang.Object r4 = r2.getObject(r1, r9)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.vision.zzhy.zzr(r15, r4, r11)
            goto L_0x090a
        L_0x0878:
            r11 = 0
            java.lang.Object r4 = r2.getObject(r1, r9)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.vision.zzhy.zzt(r15, r4, r11)
            goto L_0x090a
        L_0x0885:
            java.lang.Object r4 = r2.getObject(r1, r9)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.vision.zzhy.zzd(r15, r4)
            goto L_0x090a
        L_0x0891:
            java.lang.Object r4 = r2.getObject(r1, r9)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.vision.zzhw r9 = r0.zzbh(r3)
            int r4 = com.google.android.gms.internal.vision.zzhy.zzc(r15, r4, r9)
            goto L_0x090a
        L_0x08a0:
            java.lang.Object r4 = r2.getObject(r1, r9)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.vision.zzhy.zzc(r15, r4)
            goto L_0x090a
        L_0x08ab:
            java.lang.Object r4 = r2.getObject(r1, r9)
            java.util.List r4 = (java.util.List) r4
            r11 = 0
            int r4 = com.google.android.gms.internal.vision.zzhy.zzx(r15, r4, r11)
            goto L_0x090a
        L_0x08b7:
            r11 = 0
            java.lang.Object r4 = r2.getObject(r1, r9)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.vision.zzhy.zzv(r15, r4, r11)
            goto L_0x090a
        L_0x08c3:
            r11 = 0
            java.lang.Object r4 = r2.getObject(r1, r9)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.vision.zzhy.zzw(r15, r4, r11)
            goto L_0x090a
        L_0x08cf:
            r11 = 0
            java.lang.Object r4 = r2.getObject(r1, r9)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.vision.zzhy.zzs(r15, r4, r11)
            goto L_0x090a
        L_0x08db:
            r11 = 0
            java.lang.Object r4 = r2.getObject(r1, r9)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.vision.zzhy.zzp(r15, r4, r11)
            goto L_0x090a
        L_0x08e7:
            r11 = 0
            java.lang.Object r4 = r2.getObject(r1, r9)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.vision.zzhy.zzo(r15, r4, r11)
            goto L_0x090a
        L_0x08f3:
            r11 = 0
            java.lang.Object r4 = r2.getObject(r1, r9)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.vision.zzhy.zzv(r15, r4, r11)
            goto L_0x090a
        L_0x08ff:
            r11 = 0
            java.lang.Object r4 = r2.getObject(r1, r9)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.vision.zzhy.zzw(r15, r4, r11)
        L_0x090a:
            int r5 = r5 + r4
        L_0x090b:
            r4 = 0
        L_0x090c:
            r9 = 0
            r10 = 0
            r13 = 0
            goto L_0x0a23
        L_0x0913:
            r4 = r12 & r18
            if (r4 == 0) goto L_0x090b
            java.lang.Object r4 = r2.getObject(r1, r9)
            com.google.android.gms.internal.vision.zzhf r4 = (com.google.android.gms.internal.vision.zzhf) r4
            com.google.android.gms.internal.vision.zzhw r9 = r0.zzbh(r3)
            int r4 = com.google.android.gms.internal.vision.zzfe.zzc(r15, r4, r9)
            goto L_0x090a
        L_0x0926:
            r4 = r12 & r18
            if (r4 == 0) goto L_0x090b
            long r9 = r2.getLong(r1, r9)
            int r4 = com.google.android.gms.internal.vision.zzfe.zzf(r15, r9)
            goto L_0x090a
        L_0x0933:
            r4 = r12 & r18
            if (r4 == 0) goto L_0x090b
            int r4 = r2.getInt(r1, r9)
            int r4 = com.google.android.gms.internal.vision.zzfe.zzk(r15, r4)
            goto L_0x090a
        L_0x0940:
            r4 = r12 & r18
            if (r4 == 0) goto L_0x090b
            r9 = 0
            int r4 = com.google.android.gms.internal.vision.zzfe.zzh(r15, r9)
            goto L_0x090a
        L_0x094b:
            r4 = r12 & r18
            if (r4 == 0) goto L_0x090b
            r4 = 0
            int r9 = com.google.android.gms.internal.vision.zzfe.zzm(r15, r4)
        L_0x0954:
            int r5 = r5 + r9
            goto L_0x090b
        L_0x0956:
            r4 = r12 & r18
            if (r4 == 0) goto L_0x090b
            int r4 = r2.getInt(r1, r9)
            int r4 = com.google.android.gms.internal.vision.zzfe.zzn(r15, r4)
            goto L_0x090a
        L_0x0963:
            r4 = r12 & r18
            if (r4 == 0) goto L_0x090b
            int r4 = r2.getInt(r1, r9)
            int r4 = com.google.android.gms.internal.vision.zzfe.zzj(r15, r4)
            goto L_0x090a
        L_0x0970:
            r4 = r12 & r18
            if (r4 == 0) goto L_0x090b
            java.lang.Object r4 = r2.getObject(r1, r9)
            com.google.android.gms.internal.vision.zzeo r4 = (com.google.android.gms.internal.vision.zzeo) r4
            int r4 = com.google.android.gms.internal.vision.zzfe.zzc(r15, r4)
            goto L_0x090a
        L_0x097f:
            r4 = r12 & r18
            if (r4 == 0) goto L_0x090b
            java.lang.Object r4 = r2.getObject(r1, r9)
            com.google.android.gms.internal.vision.zzhw r9 = r0.zzbh(r3)
            int r4 = com.google.android.gms.internal.vision.zzhy.zzc(r15, r4, r9)
            goto L_0x090a
        L_0x0991:
            r4 = r12 & r18
            if (r4 == 0) goto L_0x090b
            java.lang.Object r4 = r2.getObject(r1, r9)
            boolean r9 = r4 instanceof com.google.android.gms.internal.vision.zzeo
            if (r9 == 0) goto L_0x09a5
            com.google.android.gms.internal.vision.zzeo r4 = (com.google.android.gms.internal.vision.zzeo) r4
            int r4 = com.google.android.gms.internal.vision.zzfe.zzc(r15, r4)
            goto L_0x090a
        L_0x09a5:
            java.lang.String r4 = (java.lang.String) r4
            int r4 = com.google.android.gms.internal.vision.zzfe.zzb(r15, r4)
            goto L_0x090a
        L_0x09ad:
            r4 = r12 & r18
            if (r4 == 0) goto L_0x090b
            int r4 = com.google.android.gms.internal.vision.zzfe.zzc(r15, r7)
            goto L_0x090a
        L_0x09b7:
            r4 = r12 & r18
            if (r4 == 0) goto L_0x090b
            r4 = 0
            int r9 = com.google.android.gms.internal.vision.zzfe.zzl(r15, r4)
            int r5 = r5 + r9
            goto L_0x090c
        L_0x09c3:
            r4 = 0
            r9 = r12 & r18
            if (r9 == 0) goto L_0x09cf
            r13 = 0
            int r9 = com.google.android.gms.internal.vision.zzfe.zzg(r15, r13)
            goto L_0x0a01
        L_0x09cf:
            r13 = 0
            goto L_0x0a02
        L_0x09d2:
            r4 = 0
            r13 = 0
            r11 = r12 & r18
            if (r11 == 0) goto L_0x0a02
            int r9 = r2.getInt(r1, r9)
            int r9 = com.google.android.gms.internal.vision.zzfe.zzi(r15, r9)
            goto L_0x0a01
        L_0x09e2:
            r4 = 0
            r13 = 0
            r11 = r12 & r18
            if (r11 == 0) goto L_0x0a02
            long r9 = r2.getLong(r1, r9)
            int r9 = com.google.android.gms.internal.vision.zzfe.zze(r15, r9)
            goto L_0x0a01
        L_0x09f2:
            r4 = 0
            r13 = 0
            r11 = r12 & r18
            if (r11 == 0) goto L_0x0a02
            long r9 = r2.getLong(r1, r9)
            int r9 = com.google.android.gms.internal.vision.zzfe.zzd(r15, r9)
        L_0x0a01:
            int r5 = r5 + r9
        L_0x0a02:
            r9 = 0
            goto L_0x0a11
        L_0x0a04:
            r4 = 0
            r13 = 0
            r9 = r12 & r18
            if (r9 == 0) goto L_0x0a02
            r9 = 0
            int r10 = com.google.android.gms.internal.vision.zzfe.zzb(r15, r9)
            int r5 = r5 + r10
        L_0x0a11:
            r10 = 0
            goto L_0x0a23
        L_0x0a14:
            r4 = 0
            r9 = 0
            r13 = 0
            r10 = r12 & r18
            if (r10 == 0) goto L_0x0a11
            r10 = 0
            int r15 = com.google.android.gms.internal.vision.zzfe.zzb(r15, r10)
            int r5 = r5 + r15
        L_0x0a23:
            int r3 = r3 + 3
            r9 = r13
            r4 = 0
            r11 = 0
            goto L_0x04f9
        L_0x0a2a:
            com.google.android.gms.internal.vision.zzio<?, ?> r2 = r0.zzzr
            int r2 = zza(r2, (T) r1)
            int r5 = r5 + r2
            boolean r2 = r0.zzzi
            if (r2 == 0) goto L_0x0a40
            com.google.android.gms.internal.vision.zzfl<?> r2 = r0.zzzs
            com.google.android.gms.internal.vision.zzfp r1 = r2.zzc(r1)
            int r1 = r1.zzeq()
            int r5 = r5 + r1
        L_0x0a40:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzhj.zzp(java.lang.Object):int");
    }

    private static <UT, UB> int zza(zzio<UT, UB> zzio, T t) {
        return zzio.zzp(zzio.zzt(t));
    }

    private static <E> List<E> zze(Object obj, long j) {
        return (List) zziu.zzp(obj, j);
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0039  */
    /* JADX WARNING: Removed duplicated region for block: B:163:0x0511  */
    /* JADX WARNING: Removed duplicated region for block: B:178:0x054f  */
    /* JADX WARNING: Removed duplicated region for block: B:331:0x0a27  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(T r14, com.google.android.gms.internal.vision.zzjj r15) throws java.io.IOException {
        /*
            r13 = this;
            int r0 = r15.zzed()
            int r1 = com.google.android.gms.internal.vision.zzfy.zzg.zzxj
            r2 = 267386880(0xff00000, float:2.3665827E-29)
            r3 = 0
            r4 = 1
            r5 = 0
            r6 = 1048575(0xfffff, float:1.469367E-39)
            if (r0 != r1) goto L_0x0527
            com.google.android.gms.internal.vision.zzio<?, ?> r0 = r13.zzzr
            zza(r0, (T) r14, r15)
            boolean r0 = r13.zzzi
            if (r0 == 0) goto L_0x0030
            com.google.android.gms.internal.vision.zzfl<?> r0 = r13.zzzs
            com.google.android.gms.internal.vision.zzfp r0 = r0.zzc(r14)
            boolean r1 = r0.isEmpty()
            if (r1 != 0) goto L_0x0030
            java.util.Iterator r0 = r0.descendingIterator()
            java.lang.Object r1 = r0.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            goto L_0x0032
        L_0x0030:
            r0 = r3
            r1 = r0
        L_0x0032:
            int[] r7 = r13.zzzd
            int r7 = r7.length
            int r7 = r7 + -3
        L_0x0037:
            if (r7 < 0) goto L_0x050f
            int r8 = r13.zzbk(r7)
            int[] r9 = r13.zzzd
            r9 = r9[r7]
        L_0x0041:
            if (r1 == 0) goto L_0x005f
            com.google.android.gms.internal.vision.zzfl<?> r10 = r13.zzzs
            int r10 = r10.zza(r1)
            if (r10 <= r9) goto L_0x005f
            com.google.android.gms.internal.vision.zzfl<?> r10 = r13.zzzs
            r10.zza(r15, r1)
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x005d
            java.lang.Object r1 = r0.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            goto L_0x0041
        L_0x005d:
            r1 = r3
            goto L_0x0041
        L_0x005f:
            r10 = r8 & r2
            int r10 = r10 >>> 20
            switch(r10) {
                case 0: goto L_0x04fc;
                case 1: goto L_0x04ec;
                case 2: goto L_0x04dc;
                case 3: goto L_0x04cc;
                case 4: goto L_0x04bc;
                case 5: goto L_0x04ac;
                case 6: goto L_0x049c;
                case 7: goto L_0x048b;
                case 8: goto L_0x047a;
                case 9: goto L_0x0465;
                case 10: goto L_0x0452;
                case 11: goto L_0x0441;
                case 12: goto L_0x0430;
                case 13: goto L_0x041f;
                case 14: goto L_0x040e;
                case 15: goto L_0x03fd;
                case 16: goto L_0x03ec;
                case 17: goto L_0x03d7;
                case 18: goto L_0x03c6;
                case 19: goto L_0x03b5;
                case 20: goto L_0x03a4;
                case 21: goto L_0x0393;
                case 22: goto L_0x0382;
                case 23: goto L_0x0371;
                case 24: goto L_0x0360;
                case 25: goto L_0x034f;
                case 26: goto L_0x033e;
                case 27: goto L_0x0329;
                case 28: goto L_0x0318;
                case 29: goto L_0x0307;
                case 30: goto L_0x02f6;
                case 31: goto L_0x02e5;
                case 32: goto L_0x02d4;
                case 33: goto L_0x02c3;
                case 34: goto L_0x02b2;
                case 35: goto L_0x02a1;
                case 36: goto L_0x0290;
                case 37: goto L_0x027f;
                case 38: goto L_0x026e;
                case 39: goto L_0x025d;
                case 40: goto L_0x024c;
                case 41: goto L_0x023b;
                case 42: goto L_0x022a;
                case 43: goto L_0x0219;
                case 44: goto L_0x0208;
                case 45: goto L_0x01f7;
                case 46: goto L_0x01e6;
                case 47: goto L_0x01d5;
                case 48: goto L_0x01c4;
                case 49: goto L_0x01af;
                case 50: goto L_0x01a4;
                case 51: goto L_0x0193;
                case 52: goto L_0x0182;
                case 53: goto L_0x0171;
                case 54: goto L_0x0160;
                case 55: goto L_0x014f;
                case 56: goto L_0x013e;
                case 57: goto L_0x012d;
                case 58: goto L_0x011c;
                case 59: goto L_0x010b;
                case 60: goto L_0x00f6;
                case 61: goto L_0x00e3;
                case 62: goto L_0x00d2;
                case 63: goto L_0x00c1;
                case 64: goto L_0x00b0;
                case 65: goto L_0x009f;
                case 66: goto L_0x008e;
                case 67: goto L_0x007d;
                case 68: goto L_0x0068;
                default: goto L_0x0066;
            }
        L_0x0066:
            goto L_0x050b
        L_0x0068:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zziu.zzp(r14, r10)
            com.google.android.gms.internal.vision.zzhw r10 = r13.zzbh(r7)
            r15.zzb(r9, r8, r10)
            goto L_0x050b
        L_0x007d:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = zzi(r14, r10)
            r15.zzb(r9, r10)
            goto L_0x050b
        L_0x008e:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = zzh(r14, r10)
            r15.zzg(r9, r8)
            goto L_0x050b
        L_0x009f:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = zzi(r14, r10)
            r15.zzj(r9, r10)
            goto L_0x050b
        L_0x00b0:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = zzh(r14, r10)
            r15.zzo(r9, r8)
            goto L_0x050b
        L_0x00c1:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = zzh(r14, r10)
            r15.zzp(r9, r8)
            goto L_0x050b
        L_0x00d2:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = zzh(r14, r10)
            r15.zzf(r9, r8)
            goto L_0x050b
        L_0x00e3:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zziu.zzp(r14, r10)
            com.google.android.gms.internal.vision.zzeo r8 = (com.google.android.gms.internal.vision.zzeo) r8
            r15.zza(r9, r8)
            goto L_0x050b
        L_0x00f6:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zziu.zzp(r14, r10)
            com.google.android.gms.internal.vision.zzhw r10 = r13.zzbh(r7)
            r15.zza(r9, r8, r10)
            goto L_0x050b
        L_0x010b:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zziu.zzp(r14, r10)
            zza(r9, r8, r15)
            goto L_0x050b
        L_0x011c:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            boolean r8 = zzj(r14, r10)
            r15.zzb(r9, r8)
            goto L_0x050b
        L_0x012d:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = zzh(r14, r10)
            r15.zzh(r9, r8)
            goto L_0x050b
        L_0x013e:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = zzi(r14, r10)
            r15.zzc(r9, r10)
            goto L_0x050b
        L_0x014f:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = zzh(r14, r10)
            r15.zze(r9, r8)
            goto L_0x050b
        L_0x0160:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = zzi(r14, r10)
            r15.zza(r9, r10)
            goto L_0x050b
        L_0x0171:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = zzi(r14, r10)
            r15.zzi(r9, r10)
            goto L_0x050b
        L_0x0182:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            float r8 = zzg(r14, r10)
            r15.zza(r9, r8)
            goto L_0x050b
        L_0x0193:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            double r10 = zzf(r14, r10)
            r15.zza(r9, r10)
            goto L_0x050b
        L_0x01a4:
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zziu.zzp(r14, r10)
            r13.zza(r15, r9, r8, r7)
            goto L_0x050b
        L_0x01af:
            int[] r9 = r13.zzzd
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zziu.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzhw r10 = r13.zzbh(r7)
            com.google.android.gms.internal.vision.zzhy.zzb(r9, r8, r15, r10)
            goto L_0x050b
        L_0x01c4:
            int[] r9 = r13.zzzd
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zziu.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzhy.zze(r9, r8, r15, r4)
            goto L_0x050b
        L_0x01d5:
            int[] r9 = r13.zzzd
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zziu.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzhy.zzj(r9, r8, r15, r4)
            goto L_0x050b
        L_0x01e6:
            int[] r9 = r13.zzzd
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zziu.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzhy.zzg(r9, r8, r15, r4)
            goto L_0x050b
        L_0x01f7:
            int[] r9 = r13.zzzd
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zziu.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzhy.zzl(r9, r8, r15, r4)
            goto L_0x050b
        L_0x0208:
            int[] r9 = r13.zzzd
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zziu.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzhy.zzm(r9, r8, r15, r4)
            goto L_0x050b
        L_0x0219:
            int[] r9 = r13.zzzd
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zziu.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzhy.zzi(r9, r8, r15, r4)
            goto L_0x050b
        L_0x022a:
            int[] r9 = r13.zzzd
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zziu.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzhy.zzn(r9, r8, r15, r4)
            goto L_0x050b
        L_0x023b:
            int[] r9 = r13.zzzd
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zziu.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzhy.zzk(r9, r8, r15, r4)
            goto L_0x050b
        L_0x024c:
            int[] r9 = r13.zzzd
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zziu.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzhy.zzf(r9, r8, r15, r4)
            goto L_0x050b
        L_0x025d:
            int[] r9 = r13.zzzd
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zziu.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzhy.zzh(r9, r8, r15, r4)
            goto L_0x050b
        L_0x026e:
            int[] r9 = r13.zzzd
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zziu.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzhy.zzd(r9, r8, r15, r4)
            goto L_0x050b
        L_0x027f:
            int[] r9 = r13.zzzd
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zziu.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzhy.zzc(r9, r8, r15, r4)
            goto L_0x050b
        L_0x0290:
            int[] r9 = r13.zzzd
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zziu.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzhy.zzb(r9, r8, r15, r4)
            goto L_0x050b
        L_0x02a1:
            int[] r9 = r13.zzzd
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zziu.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzhy.zza(r9, r8, r15, r4)
            goto L_0x050b
        L_0x02b2:
            int[] r9 = r13.zzzd
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zziu.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzhy.zze(r9, r8, r15, r5)
            goto L_0x050b
        L_0x02c3:
            int[] r9 = r13.zzzd
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zziu.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzhy.zzj(r9, r8, r15, r5)
            goto L_0x050b
        L_0x02d4:
            int[] r9 = r13.zzzd
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zziu.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzhy.zzg(r9, r8, r15, r5)
            goto L_0x050b
        L_0x02e5:
            int[] r9 = r13.zzzd
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zziu.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzhy.zzl(r9, r8, r15, r5)
            goto L_0x050b
        L_0x02f6:
            int[] r9 = r13.zzzd
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zziu.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzhy.zzm(r9, r8, r15, r5)
            goto L_0x050b
        L_0x0307:
            int[] r9 = r13.zzzd
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zziu.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzhy.zzi(r9, r8, r15, r5)
            goto L_0x050b
        L_0x0318:
            int[] r9 = r13.zzzd
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zziu.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzhy.zzb(r9, r8, r15)
            goto L_0x050b
        L_0x0329:
            int[] r9 = r13.zzzd
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zziu.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzhw r10 = r13.zzbh(r7)
            com.google.android.gms.internal.vision.zzhy.zza(r9, r8, r15, r10)
            goto L_0x050b
        L_0x033e:
            int[] r9 = r13.zzzd
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zziu.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzhy.zza(r9, r8, r15)
            goto L_0x050b
        L_0x034f:
            int[] r9 = r13.zzzd
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zziu.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzhy.zzn(r9, r8, r15, r5)
            goto L_0x050b
        L_0x0360:
            int[] r9 = r13.zzzd
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zziu.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzhy.zzk(r9, r8, r15, r5)
            goto L_0x050b
        L_0x0371:
            int[] r9 = r13.zzzd
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zziu.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzhy.zzf(r9, r8, r15, r5)
            goto L_0x050b
        L_0x0382:
            int[] r9 = r13.zzzd
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zziu.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzhy.zzh(r9, r8, r15, r5)
            goto L_0x050b
        L_0x0393:
            int[] r9 = r13.zzzd
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zziu.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzhy.zzd(r9, r8, r15, r5)
            goto L_0x050b
        L_0x03a4:
            int[] r9 = r13.zzzd
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zziu.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzhy.zzc(r9, r8, r15, r5)
            goto L_0x050b
        L_0x03b5:
            int[] r9 = r13.zzzd
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zziu.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzhy.zzb(r9, r8, r15, r5)
            goto L_0x050b
        L_0x03c6:
            int[] r9 = r13.zzzd
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zziu.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzhy.zza(r9, r8, r15, r5)
            goto L_0x050b
        L_0x03d7:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zziu.zzp(r14, r10)
            com.google.android.gms.internal.vision.zzhw r10 = r13.zzbh(r7)
            r15.zzb(r9, r8, r10)
            goto L_0x050b
        L_0x03ec:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = com.google.android.gms.internal.vision.zziu.zzl(r14, r10)
            r15.zzb(r9, r10)
            goto L_0x050b
        L_0x03fd:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.vision.zziu.zzk(r14, r10)
            r15.zzg(r9, r8)
            goto L_0x050b
        L_0x040e:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = com.google.android.gms.internal.vision.zziu.zzl(r14, r10)
            r15.zzj(r9, r10)
            goto L_0x050b
        L_0x041f:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.vision.zziu.zzk(r14, r10)
            r15.zzo(r9, r8)
            goto L_0x050b
        L_0x0430:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.vision.zziu.zzk(r14, r10)
            r15.zzp(r9, r8)
            goto L_0x050b
        L_0x0441:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.vision.zziu.zzk(r14, r10)
            r15.zzf(r9, r8)
            goto L_0x050b
        L_0x0452:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zziu.zzp(r14, r10)
            com.google.android.gms.internal.vision.zzeo r8 = (com.google.android.gms.internal.vision.zzeo) r8
            r15.zza(r9, r8)
            goto L_0x050b
        L_0x0465:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zziu.zzp(r14, r10)
            com.google.android.gms.internal.vision.zzhw r10 = r13.zzbh(r7)
            r15.zza(r9, r8, r10)
            goto L_0x050b
        L_0x047a:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zziu.zzp(r14, r10)
            zza(r9, r8, r15)
            goto L_0x050b
        L_0x048b:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            boolean r8 = com.google.android.gms.internal.vision.zziu.zzm(r14, r10)
            r15.zzb(r9, r8)
            goto L_0x050b
        L_0x049c:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.vision.zziu.zzk(r14, r10)
            r15.zzh(r9, r8)
            goto L_0x050b
        L_0x04ac:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = com.google.android.gms.internal.vision.zziu.zzl(r14, r10)
            r15.zzc(r9, r10)
            goto L_0x050b
        L_0x04bc:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.vision.zziu.zzk(r14, r10)
            r15.zze(r9, r8)
            goto L_0x050b
        L_0x04cc:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = com.google.android.gms.internal.vision.zziu.zzl(r14, r10)
            r15.zza(r9, r10)
            goto L_0x050b
        L_0x04dc:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = com.google.android.gms.internal.vision.zziu.zzl(r14, r10)
            r15.zzi(r9, r10)
            goto L_0x050b
        L_0x04ec:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            float r8 = com.google.android.gms.internal.vision.zziu.zzn(r14, r10)
            r15.zza(r9, r8)
            goto L_0x050b
        L_0x04fc:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            double r10 = com.google.android.gms.internal.vision.zziu.zzo(r14, r10)
            r15.zza(r9, r10)
        L_0x050b:
            int r7 = r7 + -3
            goto L_0x0037
        L_0x050f:
            if (r1 == 0) goto L_0x0526
            com.google.android.gms.internal.vision.zzfl<?> r14 = r13.zzzs
            r14.zza(r15, r1)
            boolean r14 = r0.hasNext()
            if (r14 == 0) goto L_0x0524
            java.lang.Object r14 = r0.next()
            java.util.Map$Entry r14 = (java.util.Map.Entry) r14
            r1 = r14
            goto L_0x050f
        L_0x0524:
            r1 = r3
            goto L_0x050f
        L_0x0526:
            return
        L_0x0527:
            boolean r0 = r13.zzzk
            if (r0 == 0) goto L_0x0a42
            boolean r0 = r13.zzzi
            if (r0 == 0) goto L_0x0546
            com.google.android.gms.internal.vision.zzfl<?> r0 = r13.zzzs
            com.google.android.gms.internal.vision.zzfp r0 = r0.zzc(r14)
            boolean r1 = r0.isEmpty()
            if (r1 != 0) goto L_0x0546
            java.util.Iterator r0 = r0.iterator()
            java.lang.Object r1 = r0.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            goto L_0x0548
        L_0x0546:
            r0 = r3
            r1 = r0
        L_0x0548:
            int[] r7 = r13.zzzd
            int r7 = r7.length
            r8 = r1
            r1 = 0
        L_0x054d:
            if (r1 >= r7) goto L_0x0a25
            int r9 = r13.zzbk(r1)
            int[] r10 = r13.zzzd
            r10 = r10[r1]
        L_0x0557:
            if (r8 == 0) goto L_0x0575
            com.google.android.gms.internal.vision.zzfl<?> r11 = r13.zzzs
            int r11 = r11.zza(r8)
            if (r11 > r10) goto L_0x0575
            com.google.android.gms.internal.vision.zzfl<?> r11 = r13.zzzs
            r11.zza(r15, r8)
            boolean r8 = r0.hasNext()
            if (r8 == 0) goto L_0x0573
            java.lang.Object r8 = r0.next()
            java.util.Map$Entry r8 = (java.util.Map.Entry) r8
            goto L_0x0557
        L_0x0573:
            r8 = r3
            goto L_0x0557
        L_0x0575:
            r11 = r9 & r2
            int r11 = r11 >>> 20
            switch(r11) {
                case 0: goto L_0x0a12;
                case 1: goto L_0x0a02;
                case 2: goto L_0x09f2;
                case 3: goto L_0x09e2;
                case 4: goto L_0x09d2;
                case 5: goto L_0x09c2;
                case 6: goto L_0x09b2;
                case 7: goto L_0x09a1;
                case 8: goto L_0x0990;
                case 9: goto L_0x097b;
                case 10: goto L_0x0968;
                case 11: goto L_0x0957;
                case 12: goto L_0x0946;
                case 13: goto L_0x0935;
                case 14: goto L_0x0924;
                case 15: goto L_0x0913;
                case 16: goto L_0x0902;
                case 17: goto L_0x08ed;
                case 18: goto L_0x08dc;
                case 19: goto L_0x08cb;
                case 20: goto L_0x08ba;
                case 21: goto L_0x08a9;
                case 22: goto L_0x0898;
                case 23: goto L_0x0887;
                case 24: goto L_0x0876;
                case 25: goto L_0x0865;
                case 26: goto L_0x0854;
                case 27: goto L_0x083f;
                case 28: goto L_0x082e;
                case 29: goto L_0x081d;
                case 30: goto L_0x080c;
                case 31: goto L_0x07fb;
                case 32: goto L_0x07ea;
                case 33: goto L_0x07d9;
                case 34: goto L_0x07c8;
                case 35: goto L_0x07b7;
                case 36: goto L_0x07a6;
                case 37: goto L_0x0795;
                case 38: goto L_0x0784;
                case 39: goto L_0x0773;
                case 40: goto L_0x0762;
                case 41: goto L_0x0751;
                case 42: goto L_0x0740;
                case 43: goto L_0x072f;
                case 44: goto L_0x071e;
                case 45: goto L_0x070d;
                case 46: goto L_0x06fc;
                case 47: goto L_0x06eb;
                case 48: goto L_0x06da;
                case 49: goto L_0x06c5;
                case 50: goto L_0x06ba;
                case 51: goto L_0x06a9;
                case 52: goto L_0x0698;
                case 53: goto L_0x0687;
                case 54: goto L_0x0676;
                case 55: goto L_0x0665;
                case 56: goto L_0x0654;
                case 57: goto L_0x0643;
                case 58: goto L_0x0632;
                case 59: goto L_0x0621;
                case 60: goto L_0x060c;
                case 61: goto L_0x05f9;
                case 62: goto L_0x05e8;
                case 63: goto L_0x05d7;
                case 64: goto L_0x05c6;
                case 65: goto L_0x05b5;
                case 66: goto L_0x05a4;
                case 67: goto L_0x0593;
                case 68: goto L_0x057e;
                default: goto L_0x057c;
            }
        L_0x057c:
            goto L_0x0a21
        L_0x057e:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zziu.zzp(r14, r11)
            com.google.android.gms.internal.vision.zzhw r11 = r13.zzbh(r1)
            r15.zzb(r10, r9, r11)
            goto L_0x0a21
        L_0x0593:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = zzi(r14, r11)
            r15.zzb(r10, r11)
            goto L_0x0a21
        L_0x05a4:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = zzh(r14, r11)
            r15.zzg(r10, r9)
            goto L_0x0a21
        L_0x05b5:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = zzi(r14, r11)
            r15.zzj(r10, r11)
            goto L_0x0a21
        L_0x05c6:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = zzh(r14, r11)
            r15.zzo(r10, r9)
            goto L_0x0a21
        L_0x05d7:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = zzh(r14, r11)
            r15.zzp(r10, r9)
            goto L_0x0a21
        L_0x05e8:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = zzh(r14, r11)
            r15.zzf(r10, r9)
            goto L_0x0a21
        L_0x05f9:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zziu.zzp(r14, r11)
            com.google.android.gms.internal.vision.zzeo r9 = (com.google.android.gms.internal.vision.zzeo) r9
            r15.zza(r10, r9)
            goto L_0x0a21
        L_0x060c:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zziu.zzp(r14, r11)
            com.google.android.gms.internal.vision.zzhw r11 = r13.zzbh(r1)
            r15.zza(r10, r9, r11)
            goto L_0x0a21
        L_0x0621:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zziu.zzp(r14, r11)
            zza(r10, r9, r15)
            goto L_0x0a21
        L_0x0632:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            boolean r9 = zzj(r14, r11)
            r15.zzb(r10, r9)
            goto L_0x0a21
        L_0x0643:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = zzh(r14, r11)
            r15.zzh(r10, r9)
            goto L_0x0a21
        L_0x0654:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = zzi(r14, r11)
            r15.zzc(r10, r11)
            goto L_0x0a21
        L_0x0665:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = zzh(r14, r11)
            r15.zze(r10, r9)
            goto L_0x0a21
        L_0x0676:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = zzi(r14, r11)
            r15.zza(r10, r11)
            goto L_0x0a21
        L_0x0687:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = zzi(r14, r11)
            r15.zzi(r10, r11)
            goto L_0x0a21
        L_0x0698:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            float r9 = zzg(r14, r11)
            r15.zza(r10, r9)
            goto L_0x0a21
        L_0x06a9:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            double r11 = zzf(r14, r11)
            r15.zza(r10, r11)
            goto L_0x0a21
        L_0x06ba:
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zziu.zzp(r14, r11)
            r13.zza(r15, r10, r9, r1)
            goto L_0x0a21
        L_0x06c5:
            int[] r10 = r13.zzzd
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zziu.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzhw r11 = r13.zzbh(r1)
            com.google.android.gms.internal.vision.zzhy.zzb(r10, r9, r15, r11)
            goto L_0x0a21
        L_0x06da:
            int[] r10 = r13.zzzd
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zziu.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzhy.zze(r10, r9, r15, r4)
            goto L_0x0a21
        L_0x06eb:
            int[] r10 = r13.zzzd
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zziu.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzhy.zzj(r10, r9, r15, r4)
            goto L_0x0a21
        L_0x06fc:
            int[] r10 = r13.zzzd
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zziu.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzhy.zzg(r10, r9, r15, r4)
            goto L_0x0a21
        L_0x070d:
            int[] r10 = r13.zzzd
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zziu.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzhy.zzl(r10, r9, r15, r4)
            goto L_0x0a21
        L_0x071e:
            int[] r10 = r13.zzzd
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zziu.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzhy.zzm(r10, r9, r15, r4)
            goto L_0x0a21
        L_0x072f:
            int[] r10 = r13.zzzd
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zziu.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzhy.zzi(r10, r9, r15, r4)
            goto L_0x0a21
        L_0x0740:
            int[] r10 = r13.zzzd
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zziu.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzhy.zzn(r10, r9, r15, r4)
            goto L_0x0a21
        L_0x0751:
            int[] r10 = r13.zzzd
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zziu.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzhy.zzk(r10, r9, r15, r4)
            goto L_0x0a21
        L_0x0762:
            int[] r10 = r13.zzzd
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zziu.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzhy.zzf(r10, r9, r15, r4)
            goto L_0x0a21
        L_0x0773:
            int[] r10 = r13.zzzd
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zziu.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzhy.zzh(r10, r9, r15, r4)
            goto L_0x0a21
        L_0x0784:
            int[] r10 = r13.zzzd
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zziu.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzhy.zzd(r10, r9, r15, r4)
            goto L_0x0a21
        L_0x0795:
            int[] r10 = r13.zzzd
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zziu.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzhy.zzc(r10, r9, r15, r4)
            goto L_0x0a21
        L_0x07a6:
            int[] r10 = r13.zzzd
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zziu.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzhy.zzb(r10, r9, r15, r4)
            goto L_0x0a21
        L_0x07b7:
            int[] r10 = r13.zzzd
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zziu.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzhy.zza(r10, r9, r15, r4)
            goto L_0x0a21
        L_0x07c8:
            int[] r10 = r13.zzzd
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zziu.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzhy.zze(r10, r9, r15, r5)
            goto L_0x0a21
        L_0x07d9:
            int[] r10 = r13.zzzd
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zziu.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzhy.zzj(r10, r9, r15, r5)
            goto L_0x0a21
        L_0x07ea:
            int[] r10 = r13.zzzd
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zziu.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzhy.zzg(r10, r9, r15, r5)
            goto L_0x0a21
        L_0x07fb:
            int[] r10 = r13.zzzd
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zziu.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzhy.zzl(r10, r9, r15, r5)
            goto L_0x0a21
        L_0x080c:
            int[] r10 = r13.zzzd
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zziu.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzhy.zzm(r10, r9, r15, r5)
            goto L_0x0a21
        L_0x081d:
            int[] r10 = r13.zzzd
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zziu.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzhy.zzi(r10, r9, r15, r5)
            goto L_0x0a21
        L_0x082e:
            int[] r10 = r13.zzzd
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zziu.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzhy.zzb(r10, r9, r15)
            goto L_0x0a21
        L_0x083f:
            int[] r10 = r13.zzzd
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zziu.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzhw r11 = r13.zzbh(r1)
            com.google.android.gms.internal.vision.zzhy.zza(r10, r9, r15, r11)
            goto L_0x0a21
        L_0x0854:
            int[] r10 = r13.zzzd
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zziu.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzhy.zza(r10, r9, r15)
            goto L_0x0a21
        L_0x0865:
            int[] r10 = r13.zzzd
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zziu.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzhy.zzn(r10, r9, r15, r5)
            goto L_0x0a21
        L_0x0876:
            int[] r10 = r13.zzzd
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zziu.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzhy.zzk(r10, r9, r15, r5)
            goto L_0x0a21
        L_0x0887:
            int[] r10 = r13.zzzd
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zziu.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzhy.zzf(r10, r9, r15, r5)
            goto L_0x0a21
        L_0x0898:
            int[] r10 = r13.zzzd
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zziu.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzhy.zzh(r10, r9, r15, r5)
            goto L_0x0a21
        L_0x08a9:
            int[] r10 = r13.zzzd
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zziu.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzhy.zzd(r10, r9, r15, r5)
            goto L_0x0a21
        L_0x08ba:
            int[] r10 = r13.zzzd
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zziu.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzhy.zzc(r10, r9, r15, r5)
            goto L_0x0a21
        L_0x08cb:
            int[] r10 = r13.zzzd
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zziu.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzhy.zzb(r10, r9, r15, r5)
            goto L_0x0a21
        L_0x08dc:
            int[] r10 = r13.zzzd
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zziu.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzhy.zza(r10, r9, r15, r5)
            goto L_0x0a21
        L_0x08ed:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zziu.zzp(r14, r11)
            com.google.android.gms.internal.vision.zzhw r11 = r13.zzbh(r1)
            r15.zzb(r10, r9, r11)
            goto L_0x0a21
        L_0x0902:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = com.google.android.gms.internal.vision.zziu.zzl(r14, r11)
            r15.zzb(r10, r11)
            goto L_0x0a21
        L_0x0913:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = com.google.android.gms.internal.vision.zziu.zzk(r14, r11)
            r15.zzg(r10, r9)
            goto L_0x0a21
        L_0x0924:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = com.google.android.gms.internal.vision.zziu.zzl(r14, r11)
            r15.zzj(r10, r11)
            goto L_0x0a21
        L_0x0935:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = com.google.android.gms.internal.vision.zziu.zzk(r14, r11)
            r15.zzo(r10, r9)
            goto L_0x0a21
        L_0x0946:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = com.google.android.gms.internal.vision.zziu.zzk(r14, r11)
            r15.zzp(r10, r9)
            goto L_0x0a21
        L_0x0957:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = com.google.android.gms.internal.vision.zziu.zzk(r14, r11)
            r15.zzf(r10, r9)
            goto L_0x0a21
        L_0x0968:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zziu.zzp(r14, r11)
            com.google.android.gms.internal.vision.zzeo r9 = (com.google.android.gms.internal.vision.zzeo) r9
            r15.zza(r10, r9)
            goto L_0x0a21
        L_0x097b:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zziu.zzp(r14, r11)
            com.google.android.gms.internal.vision.zzhw r11 = r13.zzbh(r1)
            r15.zza(r10, r9, r11)
            goto L_0x0a21
        L_0x0990:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zziu.zzp(r14, r11)
            zza(r10, r9, r15)
            goto L_0x0a21
        L_0x09a1:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            boolean r9 = com.google.android.gms.internal.vision.zziu.zzm(r14, r11)
            r15.zzb(r10, r9)
            goto L_0x0a21
        L_0x09b2:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = com.google.android.gms.internal.vision.zziu.zzk(r14, r11)
            r15.zzh(r10, r9)
            goto L_0x0a21
        L_0x09c2:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = com.google.android.gms.internal.vision.zziu.zzl(r14, r11)
            r15.zzc(r10, r11)
            goto L_0x0a21
        L_0x09d2:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = com.google.android.gms.internal.vision.zziu.zzk(r14, r11)
            r15.zze(r10, r9)
            goto L_0x0a21
        L_0x09e2:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = com.google.android.gms.internal.vision.zziu.zzl(r14, r11)
            r15.zza(r10, r11)
            goto L_0x0a21
        L_0x09f2:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = com.google.android.gms.internal.vision.zziu.zzl(r14, r11)
            r15.zzi(r10, r11)
            goto L_0x0a21
        L_0x0a02:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            float r9 = com.google.android.gms.internal.vision.zziu.zzn(r14, r11)
            r15.zza(r10, r9)
            goto L_0x0a21
        L_0x0a12:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            double r11 = com.google.android.gms.internal.vision.zziu.zzo(r14, r11)
            r15.zza(r10, r11)
        L_0x0a21:
            int r1 = r1 + 3
            goto L_0x054d
        L_0x0a25:
            if (r8 == 0) goto L_0x0a3c
            com.google.android.gms.internal.vision.zzfl<?> r1 = r13.zzzs
            r1.zza(r15, r8)
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0a3a
            java.lang.Object r1 = r0.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            r8 = r1
            goto L_0x0a25
        L_0x0a3a:
            r8 = r3
            goto L_0x0a25
        L_0x0a3c:
            com.google.android.gms.internal.vision.zzio<?, ?> r0 = r13.zzzr
            zza(r0, (T) r14, r15)
            return
        L_0x0a42:
            r13.zzb((T) r14, r15)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzhj.zza(java.lang.Object, com.google.android.gms.internal.vision.zzjj):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:172:0x04b3  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zzb(T r19, com.google.android.gms.internal.vision.zzjj r20) throws java.io.IOException {
        /*
            r18 = this;
            r0 = r18
            r1 = r19
            r2 = r20
            boolean r3 = r0.zzzi
            if (r3 == 0) goto L_0x0021
            com.google.android.gms.internal.vision.zzfl<?> r3 = r0.zzzs
            com.google.android.gms.internal.vision.zzfp r3 = r3.zzc(r1)
            boolean r5 = r3.isEmpty()
            if (r5 != 0) goto L_0x0021
            java.util.Iterator r3 = r3.iterator()
            java.lang.Object r5 = r3.next()
            java.util.Map$Entry r5 = (java.util.Map.Entry) r5
            goto L_0x0023
        L_0x0021:
            r3 = 0
            r5 = 0
        L_0x0023:
            r6 = -1
            int[] r7 = r0.zzzd
            int r7 = r7.length
            sun.misc.Unsafe r8 = zzzc
            r10 = r5
            r5 = 0
            r11 = 0
        L_0x002c:
            if (r5 >= r7) goto L_0x04ad
            int r12 = r0.zzbk(r5)
            int[] r13 = r0.zzzd
            r14 = r13[r5]
            r15 = 267386880(0xff00000, float:2.3665827E-29)
            r15 = r15 & r12
            int r15 = r15 >>> 20
            boolean r4 = r0.zzzk
            r16 = 1048575(0xfffff, float:1.469367E-39)
            if (r4 != 0) goto L_0x0062
            r4 = 17
            if (r15 > r4) goto L_0x0062
            int r4 = r5 + 2
            r4 = r13[r4]
            r13 = r4 & r16
            if (r13 == r6) goto L_0x0056
            r17 = r10
            long r9 = (long) r13
            int r11 = r8.getInt(r1, r9)
            goto L_0x0059
        L_0x0056:
            r17 = r10
            r13 = r6
        L_0x0059:
            int r4 = r4 >>> 20
            r6 = 1
            int r9 = r6 << r4
            r6 = r13
            r10 = r17
            goto L_0x0067
        L_0x0062:
            r17 = r10
            r10 = r17
            r9 = 0
        L_0x0067:
            if (r10 == 0) goto L_0x0086
            com.google.android.gms.internal.vision.zzfl<?> r4 = r0.zzzs
            int r4 = r4.zza(r10)
            if (r4 > r14) goto L_0x0086
            com.google.android.gms.internal.vision.zzfl<?> r4 = r0.zzzs
            r4.zza(r2, r10)
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x0084
            java.lang.Object r4 = r3.next()
            java.util.Map$Entry r4 = (java.util.Map.Entry) r4
            r10 = r4
            goto L_0x0067
        L_0x0084:
            r10 = 0
            goto L_0x0067
        L_0x0086:
            r4 = r12 & r16
            long r12 = (long) r4
            switch(r15) {
                case 0: goto L_0x049d;
                case 1: goto L_0x0490;
                case 2: goto L_0x0483;
                case 3: goto L_0x0476;
                case 4: goto L_0x0469;
                case 5: goto L_0x045c;
                case 6: goto L_0x044f;
                case 7: goto L_0x0442;
                case 8: goto L_0x0434;
                case 9: goto L_0x0422;
                case 10: goto L_0x0412;
                case 11: goto L_0x0404;
                case 12: goto L_0x03f6;
                case 13: goto L_0x03e8;
                case 14: goto L_0x03da;
                case 15: goto L_0x03cc;
                case 16: goto L_0x03be;
                case 17: goto L_0x03ac;
                case 18: goto L_0x039c;
                case 19: goto L_0x038c;
                case 20: goto L_0x037c;
                case 21: goto L_0x036c;
                case 22: goto L_0x035c;
                case 23: goto L_0x034c;
                case 24: goto L_0x033c;
                case 25: goto L_0x032c;
                case 26: goto L_0x031d;
                case 27: goto L_0x030a;
                case 28: goto L_0x02fb;
                case 29: goto L_0x02eb;
                case 30: goto L_0x02db;
                case 31: goto L_0x02cb;
                case 32: goto L_0x02bb;
                case 33: goto L_0x02ab;
                case 34: goto L_0x029b;
                case 35: goto L_0x028b;
                case 36: goto L_0x027b;
                case 37: goto L_0x026b;
                case 38: goto L_0x025b;
                case 39: goto L_0x024b;
                case 40: goto L_0x023b;
                case 41: goto L_0x022b;
                case 42: goto L_0x021b;
                case 43: goto L_0x020b;
                case 44: goto L_0x01fb;
                case 45: goto L_0x01eb;
                case 46: goto L_0x01db;
                case 47: goto L_0x01cb;
                case 48: goto L_0x01bb;
                case 49: goto L_0x01a8;
                case 50: goto L_0x019f;
                case 51: goto L_0x0190;
                case 52: goto L_0x0181;
                case 53: goto L_0x0172;
                case 54: goto L_0x0163;
                case 55: goto L_0x0154;
                case 56: goto L_0x0145;
                case 57: goto L_0x0136;
                case 58: goto L_0x0127;
                case 59: goto L_0x0118;
                case 60: goto L_0x0105;
                case 61: goto L_0x00f5;
                case 62: goto L_0x00e7;
                case 63: goto L_0x00d9;
                case 64: goto L_0x00cb;
                case 65: goto L_0x00bd;
                case 66: goto L_0x00af;
                case 67: goto L_0x00a1;
                case 68: goto L_0x008f;
                default: goto L_0x008c;
            }
        L_0x008c:
            r15 = 0
            goto L_0x04a9
        L_0x008f:
            boolean r4 = r0.zza((T) r1, r14, r5)
            if (r4 == 0) goto L_0x008c
            java.lang.Object r4 = r8.getObject(r1, r12)
            com.google.android.gms.internal.vision.zzhw r9 = r0.zzbh(r5)
            r2.zzb(r14, r4, r9)
            goto L_0x008c
        L_0x00a1:
            boolean r4 = r0.zza((T) r1, r14, r5)
            if (r4 == 0) goto L_0x008c
            long r12 = zzi(r1, r12)
            r2.zzb(r14, r12)
            goto L_0x008c
        L_0x00af:
            boolean r4 = r0.zza((T) r1, r14, r5)
            if (r4 == 0) goto L_0x008c
            int r4 = zzh(r1, r12)
            r2.zzg(r14, r4)
            goto L_0x008c
        L_0x00bd:
            boolean r4 = r0.zza((T) r1, r14, r5)
            if (r4 == 0) goto L_0x008c
            long r12 = zzi(r1, r12)
            r2.zzj(r14, r12)
            goto L_0x008c
        L_0x00cb:
            boolean r4 = r0.zza((T) r1, r14, r5)
            if (r4 == 0) goto L_0x008c
            int r4 = zzh(r1, r12)
            r2.zzo(r14, r4)
            goto L_0x008c
        L_0x00d9:
            boolean r4 = r0.zza((T) r1, r14, r5)
            if (r4 == 0) goto L_0x008c
            int r4 = zzh(r1, r12)
            r2.zzp(r14, r4)
            goto L_0x008c
        L_0x00e7:
            boolean r4 = r0.zza((T) r1, r14, r5)
            if (r4 == 0) goto L_0x008c
            int r4 = zzh(r1, r12)
            r2.zzf(r14, r4)
            goto L_0x008c
        L_0x00f5:
            boolean r4 = r0.zza((T) r1, r14, r5)
            if (r4 == 0) goto L_0x008c
            java.lang.Object r4 = r8.getObject(r1, r12)
            com.google.android.gms.internal.vision.zzeo r4 = (com.google.android.gms.internal.vision.zzeo) r4
            r2.zza(r14, r4)
            goto L_0x008c
        L_0x0105:
            boolean r4 = r0.zza((T) r1, r14, r5)
            if (r4 == 0) goto L_0x008c
            java.lang.Object r4 = r8.getObject(r1, r12)
            com.google.android.gms.internal.vision.zzhw r9 = r0.zzbh(r5)
            r2.zza(r14, r4, r9)
            goto L_0x008c
        L_0x0118:
            boolean r4 = r0.zza((T) r1, r14, r5)
            if (r4 == 0) goto L_0x008c
            java.lang.Object r4 = r8.getObject(r1, r12)
            zza(r14, r4, r2)
            goto L_0x008c
        L_0x0127:
            boolean r4 = r0.zza((T) r1, r14, r5)
            if (r4 == 0) goto L_0x008c
            boolean r4 = zzj(r1, r12)
            r2.zzb(r14, r4)
            goto L_0x008c
        L_0x0136:
            boolean r4 = r0.zza((T) r1, r14, r5)
            if (r4 == 0) goto L_0x008c
            int r4 = zzh(r1, r12)
            r2.zzh(r14, r4)
            goto L_0x008c
        L_0x0145:
            boolean r4 = r0.zza((T) r1, r14, r5)
            if (r4 == 0) goto L_0x008c
            long r12 = zzi(r1, r12)
            r2.zzc(r14, r12)
            goto L_0x008c
        L_0x0154:
            boolean r4 = r0.zza((T) r1, r14, r5)
            if (r4 == 0) goto L_0x008c
            int r4 = zzh(r1, r12)
            r2.zze(r14, r4)
            goto L_0x008c
        L_0x0163:
            boolean r4 = r0.zza((T) r1, r14, r5)
            if (r4 == 0) goto L_0x008c
            long r12 = zzi(r1, r12)
            r2.zza(r14, r12)
            goto L_0x008c
        L_0x0172:
            boolean r4 = r0.zza((T) r1, r14, r5)
            if (r4 == 0) goto L_0x008c
            long r12 = zzi(r1, r12)
            r2.zzi(r14, r12)
            goto L_0x008c
        L_0x0181:
            boolean r4 = r0.zza((T) r1, r14, r5)
            if (r4 == 0) goto L_0x008c
            float r4 = zzg(r1, r12)
            r2.zza(r14, r4)
            goto L_0x008c
        L_0x0190:
            boolean r4 = r0.zza((T) r1, r14, r5)
            if (r4 == 0) goto L_0x008c
            double r12 = zzf(r1, r12)
            r2.zza(r14, r12)
            goto L_0x008c
        L_0x019f:
            java.lang.Object r4 = r8.getObject(r1, r12)
            r0.zza(r2, r14, r4, r5)
            goto L_0x008c
        L_0x01a8:
            int[] r4 = r0.zzzd
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzhw r12 = r0.zzbh(r5)
            com.google.android.gms.internal.vision.zzhy.zzb(r4, r9, r2, r12)
            goto L_0x008c
        L_0x01bb:
            int[] r4 = r0.zzzd
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            r14 = 1
            com.google.android.gms.internal.vision.zzhy.zze(r4, r9, r2, r14)
            goto L_0x008c
        L_0x01cb:
            r14 = 1
            int[] r4 = r0.zzzd
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzhy.zzj(r4, r9, r2, r14)
            goto L_0x008c
        L_0x01db:
            r14 = 1
            int[] r4 = r0.zzzd
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzhy.zzg(r4, r9, r2, r14)
            goto L_0x008c
        L_0x01eb:
            r14 = 1
            int[] r4 = r0.zzzd
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzhy.zzl(r4, r9, r2, r14)
            goto L_0x008c
        L_0x01fb:
            r14 = 1
            int[] r4 = r0.zzzd
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzhy.zzm(r4, r9, r2, r14)
            goto L_0x008c
        L_0x020b:
            r14 = 1
            int[] r4 = r0.zzzd
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzhy.zzi(r4, r9, r2, r14)
            goto L_0x008c
        L_0x021b:
            r14 = 1
            int[] r4 = r0.zzzd
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzhy.zzn(r4, r9, r2, r14)
            goto L_0x008c
        L_0x022b:
            r14 = 1
            int[] r4 = r0.zzzd
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzhy.zzk(r4, r9, r2, r14)
            goto L_0x008c
        L_0x023b:
            r14 = 1
            int[] r4 = r0.zzzd
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzhy.zzf(r4, r9, r2, r14)
            goto L_0x008c
        L_0x024b:
            r14 = 1
            int[] r4 = r0.zzzd
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzhy.zzh(r4, r9, r2, r14)
            goto L_0x008c
        L_0x025b:
            r14 = 1
            int[] r4 = r0.zzzd
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzhy.zzd(r4, r9, r2, r14)
            goto L_0x008c
        L_0x026b:
            r14 = 1
            int[] r4 = r0.zzzd
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzhy.zzc(r4, r9, r2, r14)
            goto L_0x008c
        L_0x027b:
            r14 = 1
            int[] r4 = r0.zzzd
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzhy.zzb(r4, r9, r2, r14)
            goto L_0x008c
        L_0x028b:
            r14 = 1
            int[] r4 = r0.zzzd
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzhy.zza(r4, r9, r2, r14)
            goto L_0x008c
        L_0x029b:
            int[] r4 = r0.zzzd
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            r14 = 0
            com.google.android.gms.internal.vision.zzhy.zze(r4, r9, r2, r14)
            goto L_0x008c
        L_0x02ab:
            r14 = 0
            int[] r4 = r0.zzzd
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzhy.zzj(r4, r9, r2, r14)
            goto L_0x008c
        L_0x02bb:
            r14 = 0
            int[] r4 = r0.zzzd
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzhy.zzg(r4, r9, r2, r14)
            goto L_0x008c
        L_0x02cb:
            r14 = 0
            int[] r4 = r0.zzzd
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzhy.zzl(r4, r9, r2, r14)
            goto L_0x008c
        L_0x02db:
            r14 = 0
            int[] r4 = r0.zzzd
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzhy.zzm(r4, r9, r2, r14)
            goto L_0x008c
        L_0x02eb:
            r14 = 0
            int[] r4 = r0.zzzd
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzhy.zzi(r4, r9, r2, r14)
            goto L_0x008c
        L_0x02fb:
            int[] r4 = r0.zzzd
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzhy.zzb(r4, r9, r2)
            goto L_0x008c
        L_0x030a:
            int[] r4 = r0.zzzd
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzhw r12 = r0.zzbh(r5)
            com.google.android.gms.internal.vision.zzhy.zza(r4, r9, r2, r12)
            goto L_0x008c
        L_0x031d:
            int[] r4 = r0.zzzd
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzhy.zza(r4, r9, r2)
            goto L_0x008c
        L_0x032c:
            int[] r4 = r0.zzzd
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            r15 = 0
            com.google.android.gms.internal.vision.zzhy.zzn(r4, r9, r2, r15)
            goto L_0x04a9
        L_0x033c:
            r15 = 0
            int[] r4 = r0.zzzd
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzhy.zzk(r4, r9, r2, r15)
            goto L_0x04a9
        L_0x034c:
            r15 = 0
            int[] r4 = r0.zzzd
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzhy.zzf(r4, r9, r2, r15)
            goto L_0x04a9
        L_0x035c:
            r15 = 0
            int[] r4 = r0.zzzd
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzhy.zzh(r4, r9, r2, r15)
            goto L_0x04a9
        L_0x036c:
            r15 = 0
            int[] r4 = r0.zzzd
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzhy.zzd(r4, r9, r2, r15)
            goto L_0x04a9
        L_0x037c:
            r15 = 0
            int[] r4 = r0.zzzd
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzhy.zzc(r4, r9, r2, r15)
            goto L_0x04a9
        L_0x038c:
            r15 = 0
            int[] r4 = r0.zzzd
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzhy.zzb(r4, r9, r2, r15)
            goto L_0x04a9
        L_0x039c:
            r15 = 0
            int[] r4 = r0.zzzd
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzhy.zza(r4, r9, r2, r15)
            goto L_0x04a9
        L_0x03ac:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x04a9
            java.lang.Object r4 = r8.getObject(r1, r12)
            com.google.android.gms.internal.vision.zzhw r9 = r0.zzbh(r5)
            r2.zzb(r14, r4, r9)
            goto L_0x04a9
        L_0x03be:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x04a9
            long r12 = r8.getLong(r1, r12)
            r2.zzb(r14, r12)
            goto L_0x04a9
        L_0x03cc:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x04a9
            int r4 = r8.getInt(r1, r12)
            r2.zzg(r14, r4)
            goto L_0x04a9
        L_0x03da:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x04a9
            long r12 = r8.getLong(r1, r12)
            r2.zzj(r14, r12)
            goto L_0x04a9
        L_0x03e8:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x04a9
            int r4 = r8.getInt(r1, r12)
            r2.zzo(r14, r4)
            goto L_0x04a9
        L_0x03f6:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x04a9
            int r4 = r8.getInt(r1, r12)
            r2.zzp(r14, r4)
            goto L_0x04a9
        L_0x0404:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x04a9
            int r4 = r8.getInt(r1, r12)
            r2.zzf(r14, r4)
            goto L_0x04a9
        L_0x0412:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x04a9
            java.lang.Object r4 = r8.getObject(r1, r12)
            com.google.android.gms.internal.vision.zzeo r4 = (com.google.android.gms.internal.vision.zzeo) r4
            r2.zza(r14, r4)
            goto L_0x04a9
        L_0x0422:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x04a9
            java.lang.Object r4 = r8.getObject(r1, r12)
            com.google.android.gms.internal.vision.zzhw r9 = r0.zzbh(r5)
            r2.zza(r14, r4, r9)
            goto L_0x04a9
        L_0x0434:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x04a9
            java.lang.Object r4 = r8.getObject(r1, r12)
            zza(r14, r4, r2)
            goto L_0x04a9
        L_0x0442:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x04a9
            boolean r4 = com.google.android.gms.internal.vision.zziu.zzm(r1, r12)
            r2.zzb(r14, r4)
            goto L_0x04a9
        L_0x044f:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x04a9
            int r4 = r8.getInt(r1, r12)
            r2.zzh(r14, r4)
            goto L_0x04a9
        L_0x045c:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x04a9
            long r12 = r8.getLong(r1, r12)
            r2.zzc(r14, r12)
            goto L_0x04a9
        L_0x0469:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x04a9
            int r4 = r8.getInt(r1, r12)
            r2.zze(r14, r4)
            goto L_0x04a9
        L_0x0476:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x04a9
            long r12 = r8.getLong(r1, r12)
            r2.zza(r14, r12)
            goto L_0x04a9
        L_0x0483:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x04a9
            long r12 = r8.getLong(r1, r12)
            r2.zzi(r14, r12)
            goto L_0x04a9
        L_0x0490:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x04a9
            float r4 = com.google.android.gms.internal.vision.zziu.zzn(r1, r12)
            r2.zza(r14, r4)
            goto L_0x04a9
        L_0x049d:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x04a9
            double r12 = com.google.android.gms.internal.vision.zziu.zzo(r1, r12)
            r2.zza(r14, r12)
        L_0x04a9:
            int r5 = r5 + 3
            goto L_0x002c
        L_0x04ad:
            r17 = r10
            r4 = r17
        L_0x04b1:
            if (r4 == 0) goto L_0x04c7
            com.google.android.gms.internal.vision.zzfl<?> r5 = r0.zzzs
            r5.zza(r2, r4)
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x04c5
            java.lang.Object r4 = r3.next()
            java.util.Map$Entry r4 = (java.util.Map.Entry) r4
            goto L_0x04b1
        L_0x04c5:
            r4 = 0
            goto L_0x04b1
        L_0x04c7:
            com.google.android.gms.internal.vision.zzio<?, ?> r3 = r0.zzzr
            zza(r3, (T) r1, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzhj.zzb(java.lang.Object, com.google.android.gms.internal.vision.zzjj):void");
    }

    private final <K, V> void zza(zzjj zzjj, int i, Object obj, int i2) throws IOException {
        if (obj != null) {
            zzjj.zza(i, this.zzzt.zzo(zzbi(i2)), this.zzzt.zzk(obj));
        }
    }

    private static <UT, UB> void zza(zzio<UT, UB> zzio, T t, zzjj zzjj) throws IOException {
        zzio.zza(zzio.zzt(t), zzjj);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:151|152|(1:154)|155|(6:178|157|(2:160|158)|260|(1:162)|163)(1:256)) */
    /* JADX WARNING: Code restructure failed: missing block: B:152:?, code lost:
        r7.zza(r14);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:153:0x05a0, code lost:
        if (r10 == null) goto L_0x05a2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:154:0x05a2, code lost:
        r10 = r7.zzu(r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:156:0x05ab, code lost:
        if (r7.zza(r10, r14) == false) goto L_0x05ad;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:157:0x05ad, code lost:
        r14 = r12.zzzn;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:159:0x05b1, code lost:
        if (r14 < r12.zzzo) goto L_0x05b3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:160:0x05b3, code lost:
        r10 = zza((java.lang.Object) r13, r12.zzzm[r14], (UB) r10, r7);
        r14 = r14 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:161:0x05be, code lost:
        if (r10 != null) goto L_0x05c0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:162:0x05c0, code lost:
        r7.zzf(r13, r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:163:0x05c3, code lost:
        return;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:151:0x059d */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(T r13, com.google.android.gms.internal.vision.zzhv r14, com.google.android.gms.internal.vision.zzfk r15) throws java.io.IOException {
        /*
            r12 = this;
            if (r15 == 0) goto L_0x05dc
            com.google.android.gms.internal.vision.zzio<?, ?> r7 = r12.zzzr
            com.google.android.gms.internal.vision.zzfl<?> r8 = r12.zzzs
            r9 = 0
            r0 = r9
            r10 = r0
        L_0x0009:
            int r1 = r14.zzcn()     // Catch:{ all -> 0x05c4 }
            int r2 = r12.zzbn(r1)     // Catch:{ all -> 0x05c4 }
            if (r2 >= 0) goto L_0x0077
            r2 = 2147483647(0x7fffffff, float:NaN)
            if (r1 != r2) goto L_0x002f
            int r14 = r12.zzzn
        L_0x001a:
            int r15 = r12.zzzo
            if (r14 >= r15) goto L_0x0029
            int[] r15 = r12.zzzm
            r15 = r15[r14]
            java.lang.Object r10 = r12.zza(r13, r15, (UB) r10, r7)
            int r14 = r14 + 1
            goto L_0x001a
        L_0x0029:
            if (r10 == 0) goto L_0x002e
            r7.zzf(r13, r10)
        L_0x002e:
            return
        L_0x002f:
            boolean r2 = r12.zzzi     // Catch:{ all -> 0x05c4 }
            if (r2 != 0) goto L_0x0035
            r2 = r9
            goto L_0x003c
        L_0x0035:
            com.google.android.gms.internal.vision.zzhf r2 = r12.zzzh     // Catch:{ all -> 0x05c4 }
            java.lang.Object r1 = r8.zza(r15, r2, r1)     // Catch:{ all -> 0x05c4 }
            r2 = r1
        L_0x003c:
            if (r2 == 0) goto L_0x0051
            if (r0 != 0) goto L_0x0044
            com.google.android.gms.internal.vision.zzfp r0 = r8.zzd(r13)     // Catch:{ all -> 0x05c4 }
        L_0x0044:
            r11 = r0
            r0 = r8
            r1 = r14
            r3 = r15
            r4 = r11
            r5 = r10
            r6 = r7
            java.lang.Object r10 = r0.zza(r1, r2, r3, r4, r5, r6)     // Catch:{ all -> 0x05c4 }
            r0 = r11
            goto L_0x0009
        L_0x0051:
            r7.zza(r14)     // Catch:{ all -> 0x05c4 }
            if (r10 != 0) goto L_0x005a
            java.lang.Object r10 = r7.zzu(r13)     // Catch:{ all -> 0x05c4 }
        L_0x005a:
            boolean r1 = r7.zza(r10, r14)     // Catch:{ all -> 0x05c4 }
            if (r1 != 0) goto L_0x0009
            int r14 = r12.zzzn
        L_0x0062:
            int r15 = r12.zzzo
            if (r14 >= r15) goto L_0x0071
            int[] r15 = r12.zzzm
            r15 = r15[r14]
            java.lang.Object r10 = r12.zza(r13, r15, (UB) r10, r7)
            int r14 = r14 + 1
            goto L_0x0062
        L_0x0071:
            if (r10 == 0) goto L_0x0076
            r7.zzf(r13, r10)
        L_0x0076:
            return
        L_0x0077:
            int r3 = r12.zzbk(r2)     // Catch:{ all -> 0x05c4 }
            r4 = 267386880(0xff00000, float:2.3665827E-29)
            r4 = r4 & r3
            int r4 = r4 >>> 20
            r5 = 1048575(0xfffff, float:1.469367E-39)
            switch(r4) {
                case 0: goto L_0x0571;
                case 1: goto L_0x0562;
                case 2: goto L_0x0553;
                case 3: goto L_0x0544;
                case 4: goto L_0x0535;
                case 5: goto L_0x0526;
                case 6: goto L_0x0517;
                case 7: goto L_0x0508;
                case 8: goto L_0x0500;
                case 9: goto L_0x04cf;
                case 10: goto L_0x04c0;
                case 11: goto L_0x04b1;
                case 12: goto L_0x048f;
                case 13: goto L_0x0480;
                case 14: goto L_0x0471;
                case 15: goto L_0x0462;
                case 16: goto L_0x0453;
                case 17: goto L_0x0422;
                case 18: goto L_0x0414;
                case 19: goto L_0x0406;
                case 20: goto L_0x03f8;
                case 21: goto L_0x03ea;
                case 22: goto L_0x03dc;
                case 23: goto L_0x03ce;
                case 24: goto L_0x03c0;
                case 25: goto L_0x03b2;
                case 26: goto L_0x0390;
                case 27: goto L_0x037e;
                case 28: goto L_0x0370;
                case 29: goto L_0x0362;
                case 30: goto L_0x034d;
                case 31: goto L_0x033f;
                case 32: goto L_0x0331;
                case 33: goto L_0x0323;
                case 34: goto L_0x0315;
                case 35: goto L_0x0307;
                case 36: goto L_0x02f9;
                case 37: goto L_0x02eb;
                case 38: goto L_0x02dd;
                case 39: goto L_0x02cf;
                case 40: goto L_0x02c1;
                case 41: goto L_0x02b3;
                case 42: goto L_0x02a5;
                case 43: goto L_0x0297;
                case 44: goto L_0x0282;
                case 45: goto L_0x0274;
                case 46: goto L_0x0266;
                case 47: goto L_0x0258;
                case 48: goto L_0x024a;
                case 49: goto L_0x0238;
                case 50: goto L_0x01f6;
                case 51: goto L_0x01e4;
                case 52: goto L_0x01d2;
                case 53: goto L_0x01c0;
                case 54: goto L_0x01ae;
                case 55: goto L_0x019c;
                case 56: goto L_0x018a;
                case 57: goto L_0x0178;
                case 58: goto L_0x0166;
                case 59: goto L_0x015e;
                case 60: goto L_0x012d;
                case 61: goto L_0x011f;
                case 62: goto L_0x010d;
                case 63: goto L_0x00e8;
                case 64: goto L_0x00d6;
                case 65: goto L_0x00c4;
                case 66: goto L_0x00b2;
                case 67: goto L_0x00a0;
                case 68: goto L_0x008e;
                default: goto L_0x0086;
            }
        L_0x0086:
            if (r10 != 0) goto L_0x0580
            java.lang.Object r10 = r7.zzhd()     // Catch:{ zzgg -> 0x059d }
            goto L_0x0580
        L_0x008e:
            r3 = r3 & r5
            long r3 = (long) r3     // Catch:{ zzgg -> 0x059d }
            com.google.android.gms.internal.vision.zzhw r5 = r12.zzbh(r2)     // Catch:{ zzgg -> 0x059d }
            java.lang.Object r5 = r14.zzc(r5, r15)     // Catch:{ zzgg -> 0x059d }
            com.google.android.gms.internal.vision.zziu.zza(r13, r3, r5)     // Catch:{ zzgg -> 0x059d }
            r12.zzb((T) r13, r1, r2)     // Catch:{ zzgg -> 0x059d }
            goto L_0x0009
        L_0x00a0:
            r3 = r3 & r5
            long r3 = (long) r3     // Catch:{ zzgg -> 0x059d }
            long r5 = r14.zzdc()     // Catch:{ zzgg -> 0x059d }
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ zzgg -> 0x059d }
            com.google.android.gms.internal.vision.zziu.zza(r13, r3, r5)     // Catch:{ zzgg -> 0x059d }
            r12.zzb((T) r13, r1, r2)     // Catch:{ zzgg -> 0x059d }
            goto L_0x0009
        L_0x00b2:
            r3 = r3 & r5
            long r3 = (long) r3     // Catch:{ zzgg -> 0x059d }
            int r5 = r14.zzdb()     // Catch:{ zzgg -> 0x059d }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ zzgg -> 0x059d }
            com.google.android.gms.internal.vision.zziu.zza(r13, r3, r5)     // Catch:{ zzgg -> 0x059d }
            r12.zzb((T) r13, r1, r2)     // Catch:{ zzgg -> 0x059d }
            goto L_0x0009
        L_0x00c4:
            r3 = r3 & r5
            long r3 = (long) r3     // Catch:{ zzgg -> 0x059d }
            long r5 = r14.zzda()     // Catch:{ zzgg -> 0x059d }
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ zzgg -> 0x059d }
            com.google.android.gms.internal.vision.zziu.zza(r13, r3, r5)     // Catch:{ zzgg -> 0x059d }
            r12.zzb((T) r13, r1, r2)     // Catch:{ zzgg -> 0x059d }
            goto L_0x0009
        L_0x00d6:
            r3 = r3 & r5
            long r3 = (long) r3     // Catch:{ zzgg -> 0x059d }
            int r5 = r14.zzcz()     // Catch:{ zzgg -> 0x059d }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ zzgg -> 0x059d }
            com.google.android.gms.internal.vision.zziu.zza(r13, r3, r5)     // Catch:{ zzgg -> 0x059d }
            r12.zzb((T) r13, r1, r2)     // Catch:{ zzgg -> 0x059d }
            goto L_0x0009
        L_0x00e8:
            int r4 = r14.zzcy()     // Catch:{ zzgg -> 0x059d }
            com.google.android.gms.internal.vision.zzgd r6 = r12.zzbj(r2)     // Catch:{ zzgg -> 0x059d }
            if (r6 == 0) goto L_0x00ff
            boolean r6 = r6.zzh(r4)     // Catch:{ zzgg -> 0x059d }
            if (r6 == 0) goto L_0x00f9
            goto L_0x00ff
        L_0x00f9:
            java.lang.Object r10 = com.google.android.gms.internal.vision.zzhy.zza(r1, r4, r10, r7)     // Catch:{ zzgg -> 0x059d }
            goto L_0x0009
        L_0x00ff:
            r3 = r3 & r5
            long r5 = (long) r3     // Catch:{ zzgg -> 0x059d }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r4)     // Catch:{ zzgg -> 0x059d }
            com.google.android.gms.internal.vision.zziu.zza(r13, r5, r3)     // Catch:{ zzgg -> 0x059d }
            r12.zzb((T) r13, r1, r2)     // Catch:{ zzgg -> 0x059d }
            goto L_0x0009
        L_0x010d:
            r3 = r3 & r5
            long r3 = (long) r3     // Catch:{ zzgg -> 0x059d }
            int r5 = r14.zzcx()     // Catch:{ zzgg -> 0x059d }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ zzgg -> 0x059d }
            com.google.android.gms.internal.vision.zziu.zza(r13, r3, r5)     // Catch:{ zzgg -> 0x059d }
            r12.zzb((T) r13, r1, r2)     // Catch:{ zzgg -> 0x059d }
            goto L_0x0009
        L_0x011f:
            r3 = r3 & r5
            long r3 = (long) r3     // Catch:{ zzgg -> 0x059d }
            com.google.android.gms.internal.vision.zzeo r5 = r14.zzcw()     // Catch:{ zzgg -> 0x059d }
            com.google.android.gms.internal.vision.zziu.zza(r13, r3, r5)     // Catch:{ zzgg -> 0x059d }
            r12.zzb((T) r13, r1, r2)     // Catch:{ zzgg -> 0x059d }
            goto L_0x0009
        L_0x012d:
            boolean r4 = r12.zza((T) r13, r1, r2)     // Catch:{ zzgg -> 0x059d }
            if (r4 == 0) goto L_0x0149
            r3 = r3 & r5
            long r3 = (long) r3     // Catch:{ zzgg -> 0x059d }
            java.lang.Object r5 = com.google.android.gms.internal.vision.zziu.zzp(r13, r3)     // Catch:{ zzgg -> 0x059d }
            com.google.android.gms.internal.vision.zzhw r6 = r12.zzbh(r2)     // Catch:{ zzgg -> 0x059d }
            java.lang.Object r6 = r14.zza(r6, r15)     // Catch:{ zzgg -> 0x059d }
            java.lang.Object r5 = com.google.android.gms.internal.vision.zzga.zza(r5, r6)     // Catch:{ zzgg -> 0x059d }
            com.google.android.gms.internal.vision.zziu.zza(r13, r3, r5)     // Catch:{ zzgg -> 0x059d }
            goto L_0x0159
        L_0x0149:
            r3 = r3 & r5
            long r3 = (long) r3     // Catch:{ zzgg -> 0x059d }
            com.google.android.gms.internal.vision.zzhw r5 = r12.zzbh(r2)     // Catch:{ zzgg -> 0x059d }
            java.lang.Object r5 = r14.zza(r5, r15)     // Catch:{ zzgg -> 0x059d }
            com.google.android.gms.internal.vision.zziu.zza(r13, r3, r5)     // Catch:{ zzgg -> 0x059d }
            r12.zzb((T) r13, r2)     // Catch:{ zzgg -> 0x059d }
        L_0x0159:
            r12.zzb((T) r13, r1, r2)     // Catch:{ zzgg -> 0x059d }
            goto L_0x0009
        L_0x015e:
            r12.zza(r13, r3, r14)     // Catch:{ zzgg -> 0x059d }
            r12.zzb((T) r13, r1, r2)     // Catch:{ zzgg -> 0x059d }
            goto L_0x0009
        L_0x0166:
            r3 = r3 & r5
            long r3 = (long) r3     // Catch:{ zzgg -> 0x059d }
            boolean r5 = r14.zzcu()     // Catch:{ zzgg -> 0x059d }
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)     // Catch:{ zzgg -> 0x059d }
            com.google.android.gms.internal.vision.zziu.zza(r13, r3, r5)     // Catch:{ zzgg -> 0x059d }
            r12.zzb((T) r13, r1, r2)     // Catch:{ zzgg -> 0x059d }
            goto L_0x0009
        L_0x0178:
            r3 = r3 & r5
            long r3 = (long) r3     // Catch:{ zzgg -> 0x059d }
            int r5 = r14.zzct()     // Catch:{ zzgg -> 0x059d }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ zzgg -> 0x059d }
            com.google.android.gms.internal.vision.zziu.zza(r13, r3, r5)     // Catch:{ zzgg -> 0x059d }
            r12.zzb((T) r13, r1, r2)     // Catch:{ zzgg -> 0x059d }
            goto L_0x0009
        L_0x018a:
            r3 = r3 & r5
            long r3 = (long) r3     // Catch:{ zzgg -> 0x059d }
            long r5 = r14.zzcs()     // Catch:{ zzgg -> 0x059d }
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ zzgg -> 0x059d }
            com.google.android.gms.internal.vision.zziu.zza(r13, r3, r5)     // Catch:{ zzgg -> 0x059d }
            r12.zzb((T) r13, r1, r2)     // Catch:{ zzgg -> 0x059d }
            goto L_0x0009
        L_0x019c:
            r3 = r3 & r5
            long r3 = (long) r3     // Catch:{ zzgg -> 0x059d }
            int r5 = r14.zzcr()     // Catch:{ zzgg -> 0x059d }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ zzgg -> 0x059d }
            com.google.android.gms.internal.vision.zziu.zza(r13, r3, r5)     // Catch:{ zzgg -> 0x059d }
            r12.zzb((T) r13, r1, r2)     // Catch:{ zzgg -> 0x059d }
            goto L_0x0009
        L_0x01ae:
            r3 = r3 & r5
            long r3 = (long) r3     // Catch:{ zzgg -> 0x059d }
            long r5 = r14.zzcp()     // Catch:{ zzgg -> 0x059d }
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ zzgg -> 0x059d }
            com.google.android.gms.internal.vision.zziu.zza(r13, r3, r5)     // Catch:{ zzgg -> 0x059d }
            r12.zzb((T) r13, r1, r2)     // Catch:{ zzgg -> 0x059d }
            goto L_0x0009
        L_0x01c0:
            r3 = r3 & r5
            long r3 = (long) r3     // Catch:{ zzgg -> 0x059d }
            long r5 = r14.zzcq()     // Catch:{ zzgg -> 0x059d }
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ zzgg -> 0x059d }
            com.google.android.gms.internal.vision.zziu.zza(r13, r3, r5)     // Catch:{ zzgg -> 0x059d }
            r12.zzb((T) r13, r1, r2)     // Catch:{ zzgg -> 0x059d }
            goto L_0x0009
        L_0x01d2:
            r3 = r3 & r5
            long r3 = (long) r3     // Catch:{ zzgg -> 0x059d }
            float r5 = r14.readFloat()     // Catch:{ zzgg -> 0x059d }
            java.lang.Float r5 = java.lang.Float.valueOf(r5)     // Catch:{ zzgg -> 0x059d }
            com.google.android.gms.internal.vision.zziu.zza(r13, r3, r5)     // Catch:{ zzgg -> 0x059d }
            r12.zzb((T) r13, r1, r2)     // Catch:{ zzgg -> 0x059d }
            goto L_0x0009
        L_0x01e4:
            r3 = r3 & r5
            long r3 = (long) r3     // Catch:{ zzgg -> 0x059d }
            double r5 = r14.readDouble()     // Catch:{ zzgg -> 0x059d }
            java.lang.Double r5 = java.lang.Double.valueOf(r5)     // Catch:{ zzgg -> 0x059d }
            com.google.android.gms.internal.vision.zziu.zza(r13, r3, r5)     // Catch:{ zzgg -> 0x059d }
            r12.zzb((T) r13, r1, r2)     // Catch:{ zzgg -> 0x059d }
            goto L_0x0009
        L_0x01f6:
            java.lang.Object r1 = r12.zzbi(r2)     // Catch:{ zzgg -> 0x059d }
            int r2 = r12.zzbk(r2)     // Catch:{ zzgg -> 0x059d }
            r2 = r2 & r5
            long r2 = (long) r2     // Catch:{ zzgg -> 0x059d }
            java.lang.Object r4 = com.google.android.gms.internal.vision.zziu.zzp(r13, r2)     // Catch:{ zzgg -> 0x059d }
            if (r4 != 0) goto L_0x0210
            com.google.android.gms.internal.vision.zzha r4 = r12.zzzt     // Catch:{ zzgg -> 0x059d }
            java.lang.Object r4 = r4.zzn(r1)     // Catch:{ zzgg -> 0x059d }
            com.google.android.gms.internal.vision.zziu.zza(r13, r2, r4)     // Catch:{ zzgg -> 0x059d }
            goto L_0x0227
        L_0x0210:
            com.google.android.gms.internal.vision.zzha r5 = r12.zzzt     // Catch:{ zzgg -> 0x059d }
            boolean r5 = r5.zzl(r4)     // Catch:{ zzgg -> 0x059d }
            if (r5 == 0) goto L_0x0227
            com.google.android.gms.internal.vision.zzha r5 = r12.zzzt     // Catch:{ zzgg -> 0x059d }
            java.lang.Object r5 = r5.zzn(r1)     // Catch:{ zzgg -> 0x059d }
            com.google.android.gms.internal.vision.zzha r6 = r12.zzzt     // Catch:{ zzgg -> 0x059d }
            r6.zzb(r5, r4)     // Catch:{ zzgg -> 0x059d }
            com.google.android.gms.internal.vision.zziu.zza(r13, r2, r5)     // Catch:{ zzgg -> 0x059d }
            r4 = r5
        L_0x0227:
            com.google.android.gms.internal.vision.zzha r2 = r12.zzzt     // Catch:{ zzgg -> 0x059d }
            java.util.Map r2 = r2.zzj(r4)     // Catch:{ zzgg -> 0x059d }
            com.google.android.gms.internal.vision.zzha r3 = r12.zzzt     // Catch:{ zzgg -> 0x059d }
            com.google.android.gms.internal.vision.zzgy r1 = r3.zzo(r1)     // Catch:{ zzgg -> 0x059d }
            r14.zza(r2, r1, r15)     // Catch:{ zzgg -> 0x059d }
            goto L_0x0009
        L_0x0238:
            r1 = r3 & r5
            long r3 = (long) r1     // Catch:{ zzgg -> 0x059d }
            com.google.android.gms.internal.vision.zzhw r1 = r12.zzbh(r2)     // Catch:{ zzgg -> 0x059d }
            com.google.android.gms.internal.vision.zzgp r2 = r12.zzzq     // Catch:{ zzgg -> 0x059d }
            java.util.List r2 = r2.zza(r13, r3)     // Catch:{ zzgg -> 0x059d }
            r14.zzb(r2, r1, r15)     // Catch:{ zzgg -> 0x059d }
            goto L_0x0009
        L_0x024a:
            com.google.android.gms.internal.vision.zzgp r1 = r12.zzzq     // Catch:{ zzgg -> 0x059d }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzgg -> 0x059d }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzgg -> 0x059d }
            r14.zzp(r1)     // Catch:{ zzgg -> 0x059d }
            goto L_0x0009
        L_0x0258:
            com.google.android.gms.internal.vision.zzgp r1 = r12.zzzq     // Catch:{ zzgg -> 0x059d }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzgg -> 0x059d }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzgg -> 0x059d }
            r14.zzo(r1)     // Catch:{ zzgg -> 0x059d }
            goto L_0x0009
        L_0x0266:
            com.google.android.gms.internal.vision.zzgp r1 = r12.zzzq     // Catch:{ zzgg -> 0x059d }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzgg -> 0x059d }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzgg -> 0x059d }
            r14.zzn(r1)     // Catch:{ zzgg -> 0x059d }
            goto L_0x0009
        L_0x0274:
            com.google.android.gms.internal.vision.zzgp r1 = r12.zzzq     // Catch:{ zzgg -> 0x059d }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzgg -> 0x059d }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzgg -> 0x059d }
            r14.zzm(r1)     // Catch:{ zzgg -> 0x059d }
            goto L_0x0009
        L_0x0282:
            com.google.android.gms.internal.vision.zzgp r4 = r12.zzzq     // Catch:{ zzgg -> 0x059d }
            r3 = r3 & r5
            long r5 = (long) r3     // Catch:{ zzgg -> 0x059d }
            java.util.List r3 = r4.zza(r13, r5)     // Catch:{ zzgg -> 0x059d }
            r14.zzl(r3)     // Catch:{ zzgg -> 0x059d }
            com.google.android.gms.internal.vision.zzgd r2 = r12.zzbj(r2)     // Catch:{ zzgg -> 0x059d }
            java.lang.Object r10 = com.google.android.gms.internal.vision.zzhy.zza(r1, r3, r2, r10, r7)     // Catch:{ zzgg -> 0x059d }
            goto L_0x0009
        L_0x0297:
            com.google.android.gms.internal.vision.zzgp r1 = r12.zzzq     // Catch:{ zzgg -> 0x059d }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzgg -> 0x059d }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzgg -> 0x059d }
            r14.zzk(r1)     // Catch:{ zzgg -> 0x059d }
            goto L_0x0009
        L_0x02a5:
            com.google.android.gms.internal.vision.zzgp r1 = r12.zzzq     // Catch:{ zzgg -> 0x059d }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzgg -> 0x059d }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzgg -> 0x059d }
            r14.zzh(r1)     // Catch:{ zzgg -> 0x059d }
            goto L_0x0009
        L_0x02b3:
            com.google.android.gms.internal.vision.zzgp r1 = r12.zzzq     // Catch:{ zzgg -> 0x059d }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzgg -> 0x059d }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzgg -> 0x059d }
            r14.zzg(r1)     // Catch:{ zzgg -> 0x059d }
            goto L_0x0009
        L_0x02c1:
            com.google.android.gms.internal.vision.zzgp r1 = r12.zzzq     // Catch:{ zzgg -> 0x059d }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzgg -> 0x059d }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzgg -> 0x059d }
            r14.zzf(r1)     // Catch:{ zzgg -> 0x059d }
            goto L_0x0009
        L_0x02cf:
            com.google.android.gms.internal.vision.zzgp r1 = r12.zzzq     // Catch:{ zzgg -> 0x059d }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzgg -> 0x059d }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzgg -> 0x059d }
            r14.zze(r1)     // Catch:{ zzgg -> 0x059d }
            goto L_0x0009
        L_0x02dd:
            com.google.android.gms.internal.vision.zzgp r1 = r12.zzzq     // Catch:{ zzgg -> 0x059d }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzgg -> 0x059d }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzgg -> 0x059d }
            r14.zzc(r1)     // Catch:{ zzgg -> 0x059d }
            goto L_0x0009
        L_0x02eb:
            com.google.android.gms.internal.vision.zzgp r1 = r12.zzzq     // Catch:{ zzgg -> 0x059d }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzgg -> 0x059d }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzgg -> 0x059d }
            r14.zzd(r1)     // Catch:{ zzgg -> 0x059d }
            goto L_0x0009
        L_0x02f9:
            com.google.android.gms.internal.vision.zzgp r1 = r12.zzzq     // Catch:{ zzgg -> 0x059d }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzgg -> 0x059d }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzgg -> 0x059d }
            r14.zzb(r1)     // Catch:{ zzgg -> 0x059d }
            goto L_0x0009
        L_0x0307:
            com.google.android.gms.internal.vision.zzgp r1 = r12.zzzq     // Catch:{ zzgg -> 0x059d }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzgg -> 0x059d }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzgg -> 0x059d }
            r14.zza(r1)     // Catch:{ zzgg -> 0x059d }
            goto L_0x0009
        L_0x0315:
            com.google.android.gms.internal.vision.zzgp r1 = r12.zzzq     // Catch:{ zzgg -> 0x059d }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzgg -> 0x059d }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzgg -> 0x059d }
            r14.zzp(r1)     // Catch:{ zzgg -> 0x059d }
            goto L_0x0009
        L_0x0323:
            com.google.android.gms.internal.vision.zzgp r1 = r12.zzzq     // Catch:{ zzgg -> 0x059d }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzgg -> 0x059d }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzgg -> 0x059d }
            r14.zzo(r1)     // Catch:{ zzgg -> 0x059d }
            goto L_0x0009
        L_0x0331:
            com.google.android.gms.internal.vision.zzgp r1 = r12.zzzq     // Catch:{ zzgg -> 0x059d }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzgg -> 0x059d }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzgg -> 0x059d }
            r14.zzn(r1)     // Catch:{ zzgg -> 0x059d }
            goto L_0x0009
        L_0x033f:
            com.google.android.gms.internal.vision.zzgp r1 = r12.zzzq     // Catch:{ zzgg -> 0x059d }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzgg -> 0x059d }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzgg -> 0x059d }
            r14.zzm(r1)     // Catch:{ zzgg -> 0x059d }
            goto L_0x0009
        L_0x034d:
            com.google.android.gms.internal.vision.zzgp r4 = r12.zzzq     // Catch:{ zzgg -> 0x059d }
            r3 = r3 & r5
            long r5 = (long) r3     // Catch:{ zzgg -> 0x059d }
            java.util.List r3 = r4.zza(r13, r5)     // Catch:{ zzgg -> 0x059d }
            r14.zzl(r3)     // Catch:{ zzgg -> 0x059d }
            com.google.android.gms.internal.vision.zzgd r2 = r12.zzbj(r2)     // Catch:{ zzgg -> 0x059d }
            java.lang.Object r10 = com.google.android.gms.internal.vision.zzhy.zza(r1, r3, r2, r10, r7)     // Catch:{ zzgg -> 0x059d }
            goto L_0x0009
        L_0x0362:
            com.google.android.gms.internal.vision.zzgp r1 = r12.zzzq     // Catch:{ zzgg -> 0x059d }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzgg -> 0x059d }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzgg -> 0x059d }
            r14.zzk(r1)     // Catch:{ zzgg -> 0x059d }
            goto L_0x0009
        L_0x0370:
            com.google.android.gms.internal.vision.zzgp r1 = r12.zzzq     // Catch:{ zzgg -> 0x059d }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzgg -> 0x059d }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzgg -> 0x059d }
            r14.zzj(r1)     // Catch:{ zzgg -> 0x059d }
            goto L_0x0009
        L_0x037e:
            com.google.android.gms.internal.vision.zzhw r1 = r12.zzbh(r2)     // Catch:{ zzgg -> 0x059d }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzgg -> 0x059d }
            com.google.android.gms.internal.vision.zzgp r4 = r12.zzzq     // Catch:{ zzgg -> 0x059d }
            java.util.List r2 = r4.zza(r13, r2)     // Catch:{ zzgg -> 0x059d }
            r14.zza(r2, r1, r15)     // Catch:{ zzgg -> 0x059d }
            goto L_0x0009
        L_0x0390:
            boolean r1 = zzbm(r3)     // Catch:{ zzgg -> 0x059d }
            if (r1 == 0) goto L_0x03a4
            com.google.android.gms.internal.vision.zzgp r1 = r12.zzzq     // Catch:{ zzgg -> 0x059d }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzgg -> 0x059d }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzgg -> 0x059d }
            r14.zzi(r1)     // Catch:{ zzgg -> 0x059d }
            goto L_0x0009
        L_0x03a4:
            com.google.android.gms.internal.vision.zzgp r1 = r12.zzzq     // Catch:{ zzgg -> 0x059d }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzgg -> 0x059d }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzgg -> 0x059d }
            r14.readStringList(r1)     // Catch:{ zzgg -> 0x059d }
            goto L_0x0009
        L_0x03b2:
            com.google.android.gms.internal.vision.zzgp r1 = r12.zzzq     // Catch:{ zzgg -> 0x059d }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzgg -> 0x059d }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzgg -> 0x059d }
            r14.zzh(r1)     // Catch:{ zzgg -> 0x059d }
            goto L_0x0009
        L_0x03c0:
            com.google.android.gms.internal.vision.zzgp r1 = r12.zzzq     // Catch:{ zzgg -> 0x059d }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzgg -> 0x059d }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzgg -> 0x059d }
            r14.zzg(r1)     // Catch:{ zzgg -> 0x059d }
            goto L_0x0009
        L_0x03ce:
            com.google.android.gms.internal.vision.zzgp r1 = r12.zzzq     // Catch:{ zzgg -> 0x059d }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzgg -> 0x059d }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzgg -> 0x059d }
            r14.zzf(r1)     // Catch:{ zzgg -> 0x059d }
            goto L_0x0009
        L_0x03dc:
            com.google.android.gms.internal.vision.zzgp r1 = r12.zzzq     // Catch:{ zzgg -> 0x059d }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzgg -> 0x059d }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzgg -> 0x059d }
            r14.zze(r1)     // Catch:{ zzgg -> 0x059d }
            goto L_0x0009
        L_0x03ea:
            com.google.android.gms.internal.vision.zzgp r1 = r12.zzzq     // Catch:{ zzgg -> 0x059d }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzgg -> 0x059d }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzgg -> 0x059d }
            r14.zzc(r1)     // Catch:{ zzgg -> 0x059d }
            goto L_0x0009
        L_0x03f8:
            com.google.android.gms.internal.vision.zzgp r1 = r12.zzzq     // Catch:{ zzgg -> 0x059d }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzgg -> 0x059d }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzgg -> 0x059d }
            r14.zzd(r1)     // Catch:{ zzgg -> 0x059d }
            goto L_0x0009
        L_0x0406:
            com.google.android.gms.internal.vision.zzgp r1 = r12.zzzq     // Catch:{ zzgg -> 0x059d }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzgg -> 0x059d }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzgg -> 0x059d }
            r14.zzb(r1)     // Catch:{ zzgg -> 0x059d }
            goto L_0x0009
        L_0x0414:
            com.google.android.gms.internal.vision.zzgp r1 = r12.zzzq     // Catch:{ zzgg -> 0x059d }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzgg -> 0x059d }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzgg -> 0x059d }
            r14.zza(r1)     // Catch:{ zzgg -> 0x059d }
            goto L_0x0009
        L_0x0422:
            boolean r1 = r12.zza((T) r13, r2)     // Catch:{ zzgg -> 0x059d }
            if (r1 == 0) goto L_0x0440
            r1 = r3 & r5
            long r3 = (long) r1     // Catch:{ zzgg -> 0x059d }
            java.lang.Object r1 = com.google.android.gms.internal.vision.zziu.zzp(r13, r3)     // Catch:{ zzgg -> 0x059d }
            com.google.android.gms.internal.vision.zzhw r2 = r12.zzbh(r2)     // Catch:{ zzgg -> 0x059d }
            java.lang.Object r2 = r14.zzc(r2, r15)     // Catch:{ zzgg -> 0x059d }
            java.lang.Object r1 = com.google.android.gms.internal.vision.zzga.zza(r1, r2)     // Catch:{ zzgg -> 0x059d }
            com.google.android.gms.internal.vision.zziu.zza(r13, r3, r1)     // Catch:{ zzgg -> 0x059d }
            goto L_0x0009
        L_0x0440:
            r1 = r3 & r5
            long r3 = (long) r1     // Catch:{ zzgg -> 0x059d }
            com.google.android.gms.internal.vision.zzhw r1 = r12.zzbh(r2)     // Catch:{ zzgg -> 0x059d }
            java.lang.Object r1 = r14.zzc(r1, r15)     // Catch:{ zzgg -> 0x059d }
            com.google.android.gms.internal.vision.zziu.zza(r13, r3, r1)     // Catch:{ zzgg -> 0x059d }
            r12.zzb((T) r13, r2)     // Catch:{ zzgg -> 0x059d }
            goto L_0x0009
        L_0x0453:
            r1 = r3 & r5
            long r3 = (long) r1     // Catch:{ zzgg -> 0x059d }
            long r5 = r14.zzdc()     // Catch:{ zzgg -> 0x059d }
            com.google.android.gms.internal.vision.zziu.zza(r13, r3, r5)     // Catch:{ zzgg -> 0x059d }
            r12.zzb((T) r13, r2)     // Catch:{ zzgg -> 0x059d }
            goto L_0x0009
        L_0x0462:
            r1 = r3 & r5
            long r3 = (long) r1     // Catch:{ zzgg -> 0x059d }
            int r1 = r14.zzdb()     // Catch:{ zzgg -> 0x059d }
            com.google.android.gms.internal.vision.zziu.zzb(r13, r3, r1)     // Catch:{ zzgg -> 0x059d }
            r12.zzb((T) r13, r2)     // Catch:{ zzgg -> 0x059d }
            goto L_0x0009
        L_0x0471:
            r1 = r3 & r5
            long r3 = (long) r1     // Catch:{ zzgg -> 0x059d }
            long r5 = r14.zzda()     // Catch:{ zzgg -> 0x059d }
            com.google.android.gms.internal.vision.zziu.zza(r13, r3, r5)     // Catch:{ zzgg -> 0x059d }
            r12.zzb((T) r13, r2)     // Catch:{ zzgg -> 0x059d }
            goto L_0x0009
        L_0x0480:
            r1 = r3 & r5
            long r3 = (long) r1     // Catch:{ zzgg -> 0x059d }
            int r1 = r14.zzcz()     // Catch:{ zzgg -> 0x059d }
            com.google.android.gms.internal.vision.zziu.zzb(r13, r3, r1)     // Catch:{ zzgg -> 0x059d }
            r12.zzb((T) r13, r2)     // Catch:{ zzgg -> 0x059d }
            goto L_0x0009
        L_0x048f:
            int r4 = r14.zzcy()     // Catch:{ zzgg -> 0x059d }
            com.google.android.gms.internal.vision.zzgd r6 = r12.zzbj(r2)     // Catch:{ zzgg -> 0x059d }
            if (r6 == 0) goto L_0x04a6
            boolean r6 = r6.zzh(r4)     // Catch:{ zzgg -> 0x059d }
            if (r6 == 0) goto L_0x04a0
            goto L_0x04a6
        L_0x04a0:
            java.lang.Object r10 = com.google.android.gms.internal.vision.zzhy.zza(r1, r4, r10, r7)     // Catch:{ zzgg -> 0x059d }
            goto L_0x0009
        L_0x04a6:
            r1 = r3 & r5
            long r5 = (long) r1     // Catch:{ zzgg -> 0x059d }
            com.google.android.gms.internal.vision.zziu.zzb(r13, r5, r4)     // Catch:{ zzgg -> 0x059d }
            r12.zzb((T) r13, r2)     // Catch:{ zzgg -> 0x059d }
            goto L_0x0009
        L_0x04b1:
            r1 = r3 & r5
            long r3 = (long) r1     // Catch:{ zzgg -> 0x059d }
            int r1 = r14.zzcx()     // Catch:{ zzgg -> 0x059d }
            com.google.android.gms.internal.vision.zziu.zzb(r13, r3, r1)     // Catch:{ zzgg -> 0x059d }
            r12.zzb((T) r13, r2)     // Catch:{ zzgg -> 0x059d }
            goto L_0x0009
        L_0x04c0:
            r1 = r3 & r5
            long r3 = (long) r1     // Catch:{ zzgg -> 0x059d }
            com.google.android.gms.internal.vision.zzeo r1 = r14.zzcw()     // Catch:{ zzgg -> 0x059d }
            com.google.android.gms.internal.vision.zziu.zza(r13, r3, r1)     // Catch:{ zzgg -> 0x059d }
            r12.zzb((T) r13, r2)     // Catch:{ zzgg -> 0x059d }
            goto L_0x0009
        L_0x04cf:
            boolean r1 = r12.zza((T) r13, r2)     // Catch:{ zzgg -> 0x059d }
            if (r1 == 0) goto L_0x04ed
            r1 = r3 & r5
            long r3 = (long) r1     // Catch:{ zzgg -> 0x059d }
            java.lang.Object r1 = com.google.android.gms.internal.vision.zziu.zzp(r13, r3)     // Catch:{ zzgg -> 0x059d }
            com.google.android.gms.internal.vision.zzhw r2 = r12.zzbh(r2)     // Catch:{ zzgg -> 0x059d }
            java.lang.Object r2 = r14.zza(r2, r15)     // Catch:{ zzgg -> 0x059d }
            java.lang.Object r1 = com.google.android.gms.internal.vision.zzga.zza(r1, r2)     // Catch:{ zzgg -> 0x059d }
            com.google.android.gms.internal.vision.zziu.zza(r13, r3, r1)     // Catch:{ zzgg -> 0x059d }
            goto L_0x0009
        L_0x04ed:
            r1 = r3 & r5
            long r3 = (long) r1     // Catch:{ zzgg -> 0x059d }
            com.google.android.gms.internal.vision.zzhw r1 = r12.zzbh(r2)     // Catch:{ zzgg -> 0x059d }
            java.lang.Object r1 = r14.zza(r1, r15)     // Catch:{ zzgg -> 0x059d }
            com.google.android.gms.internal.vision.zziu.zza(r13, r3, r1)     // Catch:{ zzgg -> 0x059d }
            r12.zzb((T) r13, r2)     // Catch:{ zzgg -> 0x059d }
            goto L_0x0009
        L_0x0500:
            r12.zza(r13, r3, r14)     // Catch:{ zzgg -> 0x059d }
            r12.zzb((T) r13, r2)     // Catch:{ zzgg -> 0x059d }
            goto L_0x0009
        L_0x0508:
            r1 = r3 & r5
            long r3 = (long) r1     // Catch:{ zzgg -> 0x059d }
            boolean r1 = r14.zzcu()     // Catch:{ zzgg -> 0x059d }
            com.google.android.gms.internal.vision.zziu.zza(r13, r3, r1)     // Catch:{ zzgg -> 0x059d }
            r12.zzb((T) r13, r2)     // Catch:{ zzgg -> 0x059d }
            goto L_0x0009
        L_0x0517:
            r1 = r3 & r5
            long r3 = (long) r1     // Catch:{ zzgg -> 0x059d }
            int r1 = r14.zzct()     // Catch:{ zzgg -> 0x059d }
            com.google.android.gms.internal.vision.zziu.zzb(r13, r3, r1)     // Catch:{ zzgg -> 0x059d }
            r12.zzb((T) r13, r2)     // Catch:{ zzgg -> 0x059d }
            goto L_0x0009
        L_0x0526:
            r1 = r3 & r5
            long r3 = (long) r1     // Catch:{ zzgg -> 0x059d }
            long r5 = r14.zzcs()     // Catch:{ zzgg -> 0x059d }
            com.google.android.gms.internal.vision.zziu.zza(r13, r3, r5)     // Catch:{ zzgg -> 0x059d }
            r12.zzb((T) r13, r2)     // Catch:{ zzgg -> 0x059d }
            goto L_0x0009
        L_0x0535:
            r1 = r3 & r5
            long r3 = (long) r1     // Catch:{ zzgg -> 0x059d }
            int r1 = r14.zzcr()     // Catch:{ zzgg -> 0x059d }
            com.google.android.gms.internal.vision.zziu.zzb(r13, r3, r1)     // Catch:{ zzgg -> 0x059d }
            r12.zzb((T) r13, r2)     // Catch:{ zzgg -> 0x059d }
            goto L_0x0009
        L_0x0544:
            r1 = r3 & r5
            long r3 = (long) r1     // Catch:{ zzgg -> 0x059d }
            long r5 = r14.zzcp()     // Catch:{ zzgg -> 0x059d }
            com.google.android.gms.internal.vision.zziu.zza(r13, r3, r5)     // Catch:{ zzgg -> 0x059d }
            r12.zzb((T) r13, r2)     // Catch:{ zzgg -> 0x059d }
            goto L_0x0009
        L_0x0553:
            r1 = r3 & r5
            long r3 = (long) r1     // Catch:{ zzgg -> 0x059d }
            long r5 = r14.zzcq()     // Catch:{ zzgg -> 0x059d }
            com.google.android.gms.internal.vision.zziu.zza(r13, r3, r5)     // Catch:{ zzgg -> 0x059d }
            r12.zzb((T) r13, r2)     // Catch:{ zzgg -> 0x059d }
            goto L_0x0009
        L_0x0562:
            r1 = r3 & r5
            long r3 = (long) r1     // Catch:{ zzgg -> 0x059d }
            float r1 = r14.readFloat()     // Catch:{ zzgg -> 0x059d }
            com.google.android.gms.internal.vision.zziu.zza(r13, r3, r1)     // Catch:{ zzgg -> 0x059d }
            r12.zzb((T) r13, r2)     // Catch:{ zzgg -> 0x059d }
            goto L_0x0009
        L_0x0571:
            r1 = r3 & r5
            long r3 = (long) r1     // Catch:{ zzgg -> 0x059d }
            double r5 = r14.readDouble()     // Catch:{ zzgg -> 0x059d }
            com.google.android.gms.internal.vision.zziu.zza(r13, r3, r5)     // Catch:{ zzgg -> 0x059d }
            r12.zzb((T) r13, r2)     // Catch:{ zzgg -> 0x059d }
            goto L_0x0009
        L_0x0580:
            boolean r1 = r7.zza(r10, r14)     // Catch:{ zzgg -> 0x059d }
            if (r1 != 0) goto L_0x0009
            int r14 = r12.zzzn
        L_0x0588:
            int r15 = r12.zzzo
            if (r14 >= r15) goto L_0x0597
            int[] r15 = r12.zzzm
            r15 = r15[r14]
            java.lang.Object r10 = r12.zza(r13, r15, (UB) r10, r7)
            int r14 = r14 + 1
            goto L_0x0588
        L_0x0597:
            if (r10 == 0) goto L_0x059c
            r7.zzf(r13, r10)
        L_0x059c:
            return
        L_0x059d:
            r7.zza(r14)     // Catch:{ all -> 0x05c4 }
            if (r10 != 0) goto L_0x05a7
            java.lang.Object r1 = r7.zzu(r13)     // Catch:{ all -> 0x05c4 }
            r10 = r1
        L_0x05a7:
            boolean r1 = r7.zza(r10, r14)     // Catch:{ all -> 0x05c4 }
            if (r1 != 0) goto L_0x0009
            int r14 = r12.zzzn
        L_0x05af:
            int r15 = r12.zzzo
            if (r14 >= r15) goto L_0x05be
            int[] r15 = r12.zzzm
            r15 = r15[r14]
            java.lang.Object r10 = r12.zza(r13, r15, (UB) r10, r7)
            int r14 = r14 + 1
            goto L_0x05af
        L_0x05be:
            if (r10 == 0) goto L_0x05c3
            r7.zzf(r13, r10)
        L_0x05c3:
            return
        L_0x05c4:
            r14 = move-exception
            int r15 = r12.zzzn
        L_0x05c7:
            int r0 = r12.zzzo
            if (r15 >= r0) goto L_0x05d6
            int[] r0 = r12.zzzm
            r0 = r0[r15]
            java.lang.Object r10 = r12.zza(r13, r0, (UB) r10, r7)
            int r15 = r15 + 1
            goto L_0x05c7
        L_0x05d6:
            if (r10 == 0) goto L_0x05db
            r7.zzf(r13, r10)
        L_0x05db:
            throw r14
        L_0x05dc:
            java.lang.NullPointerException r13 = new java.lang.NullPointerException
            r13.<init>()
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzhj.zza(java.lang.Object, com.google.android.gms.internal.vision.zzhv, com.google.android.gms.internal.vision.zzfk):void");
    }

    private static zzip zzq(Object obj) {
        zzfy zzfy = (zzfy) obj;
        zzip zzip = zzfy.zzwj;
        if (zzip != zzip.zzhe()) {
            return zzip;
        }
        zzip zzhf = zzip.zzhf();
        zzfy.zzwj = zzhf;
        return zzhf;
    }

    /* JADX WARNING: type inference failed for: r8v2, types: [int] */
    /* JADX WARNING: type inference failed for: r8v5 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int zza(com.google.android.gms.internal.vision.zzhw r6, byte[] r7, int r8, int r9, com.google.android.gms.internal.vision.zzei r10) throws java.io.IOException {
        /*
            int r0 = r8 + 1
            byte r8 = r7[r8]
            if (r8 >= 0) goto L_0x000c
            int r0 = com.google.android.gms.internal.vision.zzeh.zza(r8, r7, r0, r10)
            int r8 = r10.zzro
        L_0x000c:
            r3 = r0
            if (r8 < 0) goto L_0x0025
            int r9 = r9 - r3
            if (r8 > r9) goto L_0x0025
            java.lang.Object r9 = r6.newInstance()
            int r8 = r8 + r3
            r0 = r6
            r1 = r9
            r2 = r7
            r4 = r8
            r5 = r10
            r0.zza(r1, r2, r3, r4, r5)
            r6.zze(r9)
            r10.zzrq = r9
            return r8
        L_0x0025:
            com.google.android.gms.internal.vision.zzgf r6 = com.google.android.gms.internal.vision.zzgf.zzfh()
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzhj.zza(com.google.android.gms.internal.vision.zzhw, byte[], int, int, com.google.android.gms.internal.vision.zzei):int");
    }

    private static int zza(zzhw zzhw, byte[] bArr, int i, int i2, int i3, zzei zzei) throws IOException {
        zzhj zzhj = (zzhj) zzhw;
        Object newInstance = zzhj.newInstance();
        int zza = zzhj.zza((T) newInstance, bArr, i, i2, i3, zzei);
        zzhj.zze(newInstance);
        zzei.zzrq = newInstance;
        return zza;
    }

    private static int zza(zzhw<?> zzhw, int i, byte[] bArr, int i2, int i3, zzge<?> zzge, zzei zzei) throws IOException {
        int zza = zza((zzhw) zzhw, bArr, i2, i3, zzei);
        zzge.add(zzei.zzrq);
        while (zza < i3) {
            int zza2 = zzeh.zza(bArr, zza, zzei);
            if (i != zzei.zzro) {
                break;
            }
            zza = zza((zzhw) zzhw, bArr, zza2, i3, zzei);
            zzge.add(zzei.zzrq);
        }
        return zza;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:29:?, code lost:
        return r2 + 4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
        return r2 + 8;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int zza(byte[] r1, int r2, int r3, com.google.android.gms.internal.vision.zzjd r4, java.lang.Class<?> r5, com.google.android.gms.internal.vision.zzei r6) throws java.io.IOException {
        /*
            int[] r0 = com.google.android.gms.internal.vision.zzhk.zzrr
            int r4 = r4.ordinal()
            r4 = r0[r4]
            switch(r4) {
                case 1: goto L_0x0099;
                case 2: goto L_0x0094;
                case 3: goto L_0x0087;
                case 4: goto L_0x007a;
                case 5: goto L_0x007a;
                case 6: goto L_0x006f;
                case 7: goto L_0x006f;
                case 8: goto L_0x0064;
                case 9: goto L_0x0057;
                case 10: goto L_0x0057;
                case 11: goto L_0x0057;
                case 12: goto L_0x004a;
                case 13: goto L_0x004a;
                case 14: goto L_0x003d;
                case 15: goto L_0x002b;
                case 16: goto L_0x0019;
                case 17: goto L_0x0013;
                default: goto L_0x000b;
            }
        L_0x000b:
            java.lang.RuntimeException r1 = new java.lang.RuntimeException
            java.lang.String r2 = "unsupported field type."
            r1.<init>(r2)
            throw r1
        L_0x0013:
            int r1 = com.google.android.gms.internal.vision.zzeh.zzd(r1, r2, r6)
            goto L_0x00ae
        L_0x0019:
            int r1 = com.google.android.gms.internal.vision.zzeh.zzb(r1, r2, r6)
            long r2 = r6.zzrp
            long r2 = com.google.android.gms.internal.vision.zzez.zzd(r2)
            java.lang.Long r2 = java.lang.Long.valueOf(r2)
            r6.zzrq = r2
            goto L_0x00ae
        L_0x002b:
            int r1 = com.google.android.gms.internal.vision.zzeh.zza(r1, r2, r6)
            int r2 = r6.zzro
            int r2 = com.google.android.gms.internal.vision.zzez.zzaq(r2)
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r6.zzrq = r2
            goto L_0x00ae
        L_0x003d:
            com.google.android.gms.internal.vision.zzhs r4 = com.google.android.gms.internal.vision.zzhs.zzgl()
            com.google.android.gms.internal.vision.zzhw r4 = r4.zzf(r5)
            int r1 = zza(r4, r1, r2, r3, r6)
            goto L_0x00ae
        L_0x004a:
            int r1 = com.google.android.gms.internal.vision.zzeh.zzb(r1, r2, r6)
            long r2 = r6.zzrp
            java.lang.Long r2 = java.lang.Long.valueOf(r2)
            r6.zzrq = r2
            goto L_0x00ae
        L_0x0057:
            int r1 = com.google.android.gms.internal.vision.zzeh.zza(r1, r2, r6)
            int r2 = r6.zzro
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r6.zzrq = r2
            goto L_0x00ae
        L_0x0064:
            float r1 = com.google.android.gms.internal.vision.zzeh.zzd(r1, r2)
            java.lang.Float r1 = java.lang.Float.valueOf(r1)
            r6.zzrq = r1
            goto L_0x0084
        L_0x006f:
            long r3 = com.google.android.gms.internal.vision.zzeh.zzb(r1, r2)
            java.lang.Long r1 = java.lang.Long.valueOf(r3)
            r6.zzrq = r1
            goto L_0x0091
        L_0x007a:
            int r1 = com.google.android.gms.internal.vision.zzeh.zza(r1, r2)
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            r6.zzrq = r1
        L_0x0084:
            int r1 = r2 + 4
            goto L_0x00ae
        L_0x0087:
            double r3 = com.google.android.gms.internal.vision.zzeh.zzc(r1, r2)
            java.lang.Double r1 = java.lang.Double.valueOf(r3)
            r6.zzrq = r1
        L_0x0091:
            int r1 = r2 + 8
            goto L_0x00ae
        L_0x0094:
            int r1 = com.google.android.gms.internal.vision.zzeh.zze(r1, r2, r6)
            goto L_0x00ae
        L_0x0099:
            int r1 = com.google.android.gms.internal.vision.zzeh.zzb(r1, r2, r6)
            long r2 = r6.zzrp
            r4 = 0
            int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r0 == 0) goto L_0x00a7
            r2 = 1
            goto L_0x00a8
        L_0x00a7:
            r2 = 0
        L_0x00a8:
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r2)
            r6.zzrq = r2
        L_0x00ae:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzhj.zza(byte[], int, int, com.google.android.gms.internal.vision.zzjd, java.lang.Class, com.google.android.gms.internal.vision.zzei):int");
    }

    private static int zza(int i, byte[] bArr, int i2, int i3, Object obj, zzei zzei) throws IOException {
        return zzeh.zza(i, bArr, i2, i3, zzq(obj), zzei);
    }

    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Regions count limit reached
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:89)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:690)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:690)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:690)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processSwitch(RegionMaker.java:869)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:128)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:49)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
        	at jadx.core.ProcessClass.process(ProcessClass.java:30)
        	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
        	at jadx.core.ProcessClass.process(ProcessClass.java:35)
        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
        */
    /* JADX WARNING: Removed duplicated region for block: B:244:0x0422 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x01eb  */
    private final int zza(T r17, byte[] r18, int r19, int r20, int r21, int r22, int r23, int r24, long r25, int r27, long r28, com.google.android.gms.internal.vision.zzei r30) throws java.io.IOException {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            r3 = r18
            r4 = r19
            r5 = r20
            r2 = r21
            r6 = r23
            r8 = r24
            r9 = r28
            r7 = r30
            sun.misc.Unsafe r11 = zzzc
            java.lang.Object r11 = r11.getObject(r1, r9)
            com.google.android.gms.internal.vision.zzge r11 = (com.google.android.gms.internal.vision.zzge) r11
            boolean r12 = r11.zzch()
            r13 = 1
            if (r12 != 0) goto L_0x0036
            int r12 = r11.size()
            if (r12 != 0) goto L_0x002c
            r12 = 10
            goto L_0x002d
        L_0x002c:
            int r12 = r12 << r13
        L_0x002d:
            com.google.android.gms.internal.vision.zzge r11 = r11.zzah(r12)
            sun.misc.Unsafe r12 = zzzc
            r12.putObject(r1, r9, r11)
        L_0x0036:
            r9 = 5
            r14 = 0
            r10 = 2
            switch(r27) {
                case 18: goto L_0x03e4;
                case 19: goto L_0x03a6;
                case 20: goto L_0x0365;
                case 21: goto L_0x0365;
                case 22: goto L_0x034b;
                case 23: goto L_0x030c;
                case 24: goto L_0x02cd;
                case 25: goto L_0x0276;
                case 26: goto L_0x01c3;
                case 27: goto L_0x01a9;
                case 28: goto L_0x0151;
                case 29: goto L_0x034b;
                case 30: goto L_0x0119;
                case 31: goto L_0x02cd;
                case 32: goto L_0x030c;
                case 33: goto L_0x00cc;
                case 34: goto L_0x007f;
                case 35: goto L_0x03e4;
                case 36: goto L_0x03a6;
                case 37: goto L_0x0365;
                case 38: goto L_0x0365;
                case 39: goto L_0x034b;
                case 40: goto L_0x030c;
                case 41: goto L_0x02cd;
                case 42: goto L_0x0276;
                case 43: goto L_0x034b;
                case 44: goto L_0x0119;
                case 45: goto L_0x02cd;
                case 46: goto L_0x030c;
                case 47: goto L_0x00cc;
                case 48: goto L_0x007f;
                case 49: goto L_0x003f;
                default: goto L_0x003d;
            }
        L_0x003d:
            goto L_0x0422
        L_0x003f:
            r1 = 3
            if (r6 != r1) goto L_0x0422
            com.google.android.gms.internal.vision.zzhw r1 = r0.zzbh(r8)
            r6 = r2 & -8
            r6 = r6 | 4
            r22 = r1
            r23 = r18
            r24 = r19
            r25 = r20
            r26 = r6
            r27 = r30
            int r4 = zza(r22, r23, r24, r25, r26, r27)
            java.lang.Object r8 = r7.zzrq
            r11.add(r8)
        L_0x005f:
            if (r4 >= r5) goto L_0x0422
            int r8 = com.google.android.gms.internal.vision.zzeh.zza(r3, r4, r7)
            int r9 = r7.zzro
            if (r2 != r9) goto L_0x0422
            r22 = r1
            r23 = r18
            r24 = r8
            r25 = r20
            r26 = r6
            r27 = r30
            int r4 = zza(r22, r23, r24, r25, r26, r27)
            java.lang.Object r8 = r7.zzrq
            r11.add(r8)
            goto L_0x005f
        L_0x007f:
            if (r6 != r10) goto L_0x00a3
            com.google.android.gms.internal.vision.zzgt r11 = (com.google.android.gms.internal.vision.zzgt) r11
            int r1 = com.google.android.gms.internal.vision.zzeh.zza(r3, r4, r7)
            int r2 = r7.zzro
            int r2 = r2 + r1
        L_0x008a:
            if (r1 >= r2) goto L_0x009a
            int r1 = com.google.android.gms.internal.vision.zzeh.zzb(r3, r1, r7)
            long r4 = r7.zzrp
            long r4 = com.google.android.gms.internal.vision.zzez.zzd(r4)
            r11.zzp(r4)
            goto L_0x008a
        L_0x009a:
            if (r1 != r2) goto L_0x009e
            goto L_0x0423
        L_0x009e:
            com.google.android.gms.internal.vision.zzgf r1 = com.google.android.gms.internal.vision.zzgf.zzfh()
            throw r1
        L_0x00a3:
            if (r6 != 0) goto L_0x0422
            com.google.android.gms.internal.vision.zzgt r11 = (com.google.android.gms.internal.vision.zzgt) r11
            int r1 = com.google.android.gms.internal.vision.zzeh.zzb(r3, r4, r7)
            long r8 = r7.zzrp
            long r8 = com.google.android.gms.internal.vision.zzez.zzd(r8)
            r11.zzp(r8)
        L_0x00b4:
            if (r1 >= r5) goto L_0x0423
            int r4 = com.google.android.gms.internal.vision.zzeh.zza(r3, r1, r7)
            int r6 = r7.zzro
            if (r2 != r6) goto L_0x0423
            int r1 = com.google.android.gms.internal.vision.zzeh.zzb(r3, r4, r7)
            long r8 = r7.zzrp
            long r8 = com.google.android.gms.internal.vision.zzez.zzd(r8)
            r11.zzp(r8)
            goto L_0x00b4
        L_0x00cc:
            if (r6 != r10) goto L_0x00f0
            com.google.android.gms.internal.vision.zzfz r11 = (com.google.android.gms.internal.vision.zzfz) r11
            int r1 = com.google.android.gms.internal.vision.zzeh.zza(r3, r4, r7)
            int r2 = r7.zzro
            int r2 = r2 + r1
        L_0x00d7:
            if (r1 >= r2) goto L_0x00e7
            int r1 = com.google.android.gms.internal.vision.zzeh.zza(r3, r1, r7)
            int r4 = r7.zzro
            int r4 = com.google.android.gms.internal.vision.zzez.zzaq(r4)
            r11.zzbg(r4)
            goto L_0x00d7
        L_0x00e7:
            if (r1 != r2) goto L_0x00eb
            goto L_0x0423
        L_0x00eb:
            com.google.android.gms.internal.vision.zzgf r1 = com.google.android.gms.internal.vision.zzgf.zzfh()
            throw r1
        L_0x00f0:
            if (r6 != 0) goto L_0x0422
            com.google.android.gms.internal.vision.zzfz r11 = (com.google.android.gms.internal.vision.zzfz) r11
            int r1 = com.google.android.gms.internal.vision.zzeh.zza(r3, r4, r7)
            int r4 = r7.zzro
            int r4 = com.google.android.gms.internal.vision.zzez.zzaq(r4)
            r11.zzbg(r4)
        L_0x0101:
            if (r1 >= r5) goto L_0x0423
            int r4 = com.google.android.gms.internal.vision.zzeh.zza(r3, r1, r7)
            int r6 = r7.zzro
            if (r2 != r6) goto L_0x0423
            int r1 = com.google.android.gms.internal.vision.zzeh.zza(r3, r4, r7)
            int r4 = r7.zzro
            int r4 = com.google.android.gms.internal.vision.zzez.zzaq(r4)
            r11.zzbg(r4)
            goto L_0x0101
        L_0x0119:
            if (r6 != r10) goto L_0x0120
            int r2 = com.google.android.gms.internal.vision.zzeh.zza(r3, r4, r11, r7)
            goto L_0x0131
        L_0x0120:
            if (r6 != 0) goto L_0x0422
            r2 = r21
            r3 = r18
            r4 = r19
            r5 = r20
            r6 = r11
            r7 = r30
            int r2 = com.google.android.gms.internal.vision.zzeh.zza(r2, r3, r4, r5, r6, r7)
        L_0x0131:
            com.google.android.gms.internal.vision.zzfy r1 = (com.google.android.gms.internal.vision.zzfy) r1
            com.google.android.gms.internal.vision.zzip r3 = r1.zzwj
            com.google.android.gms.internal.vision.zzip r4 = com.google.android.gms.internal.vision.zzip.zzhe()
            if (r3 != r4) goto L_0x013c
            r3 = 0
        L_0x013c:
            com.google.android.gms.internal.vision.zzgd r4 = r0.zzbj(r8)
            com.google.android.gms.internal.vision.zzio<?, ?> r5 = r0.zzzr
            r6 = r22
            java.lang.Object r3 = com.google.android.gms.internal.vision.zzhy.zza(r6, r11, r4, r3, r5)
            com.google.android.gms.internal.vision.zzip r3 = (com.google.android.gms.internal.vision.zzip) r3
            if (r3 == 0) goto L_0x014e
            r1.zzwj = r3
        L_0x014e:
            r1 = r2
            goto L_0x0423
        L_0x0151:
            if (r6 != r10) goto L_0x0422
            int r1 = com.google.android.gms.internal.vision.zzeh.zza(r3, r4, r7)
            int r4 = r7.zzro
            if (r4 < 0) goto L_0x01a4
            int r6 = r3.length
            int r6 = r6 - r1
            if (r4 > r6) goto L_0x019f
            if (r4 != 0) goto L_0x0167
            com.google.android.gms.internal.vision.zzeo r4 = com.google.android.gms.internal.vision.zzeo.zzrx
            r11.add(r4)
            goto L_0x016f
        L_0x0167:
            com.google.android.gms.internal.vision.zzeo r6 = com.google.android.gms.internal.vision.zzeo.zzb(r3, r1, r4)
            r11.add(r6)
        L_0x016e:
            int r1 = r1 + r4
        L_0x016f:
            if (r1 >= r5) goto L_0x0423
            int r4 = com.google.android.gms.internal.vision.zzeh.zza(r3, r1, r7)
            int r6 = r7.zzro
            if (r2 != r6) goto L_0x0423
            int r1 = com.google.android.gms.internal.vision.zzeh.zza(r3, r4, r7)
            int r4 = r7.zzro
            if (r4 < 0) goto L_0x019a
            int r6 = r3.length
            int r6 = r6 - r1
            if (r4 > r6) goto L_0x0195
            if (r4 != 0) goto L_0x018d
            com.google.android.gms.internal.vision.zzeo r4 = com.google.android.gms.internal.vision.zzeo.zzrx
            r11.add(r4)
            goto L_0x016f
        L_0x018d:
            com.google.android.gms.internal.vision.zzeo r6 = com.google.android.gms.internal.vision.zzeo.zzb(r3, r1, r4)
            r11.add(r6)
            goto L_0x016e
        L_0x0195:
            com.google.android.gms.internal.vision.zzgf r1 = com.google.android.gms.internal.vision.zzgf.zzfh()
            throw r1
        L_0x019a:
            com.google.android.gms.internal.vision.zzgf r1 = com.google.android.gms.internal.vision.zzgf.zzfi()
            throw r1
        L_0x019f:
            com.google.android.gms.internal.vision.zzgf r1 = com.google.android.gms.internal.vision.zzgf.zzfh()
            throw r1
        L_0x01a4:
            com.google.android.gms.internal.vision.zzgf r1 = com.google.android.gms.internal.vision.zzgf.zzfi()
            throw r1
        L_0x01a9:
            if (r6 != r10) goto L_0x0422
            com.google.android.gms.internal.vision.zzhw r1 = r0.zzbh(r8)
            r22 = r1
            r23 = r21
            r24 = r18
            r25 = r19
            r26 = r20
            r27 = r11
            r28 = r30
            int r1 = zza(r22, r23, r24, r25, r26, r27, r28)
            goto L_0x0423
        L_0x01c3:
            if (r6 != r10) goto L_0x0422
            r8 = 536870912(0x20000000, double:2.652494739E-315)
            long r8 = r25 & r8
            java.lang.String r1 = ""
            int r6 = (r8 > r14 ? 1 : (r8 == r14 ? 0 : -1))
            if (r6 != 0) goto L_0x0216
            int r4 = com.google.android.gms.internal.vision.zzeh.zza(r3, r4, r7)
            int r6 = r7.zzro
            if (r6 < 0) goto L_0x0211
            if (r6 != 0) goto L_0x01de
            r11.add(r1)
            goto L_0x01e9
        L_0x01de:
            java.lang.String r8 = new java.lang.String
            java.nio.charset.Charset r9 = com.google.android.gms.internal.vision.zzga.UTF_8
            r8.<init>(r3, r4, r6, r9)
            r11.add(r8)
        L_0x01e8:
            int r4 = r4 + r6
        L_0x01e9:
            if (r4 >= r5) goto L_0x0422
            int r6 = com.google.android.gms.internal.vision.zzeh.zza(r3, r4, r7)
            int r8 = r7.zzro
            if (r2 != r8) goto L_0x0422
            int r4 = com.google.android.gms.internal.vision.zzeh.zza(r3, r6, r7)
            int r6 = r7.zzro
            if (r6 < 0) goto L_0x020c
            if (r6 != 0) goto L_0x0201
            r11.add(r1)
            goto L_0x01e9
        L_0x0201:
            java.lang.String r8 = new java.lang.String
            java.nio.charset.Charset r9 = com.google.android.gms.internal.vision.zzga.UTF_8
            r8.<init>(r3, r4, r6, r9)
            r11.add(r8)
            goto L_0x01e8
        L_0x020c:
            com.google.android.gms.internal.vision.zzgf r1 = com.google.android.gms.internal.vision.zzgf.zzfi()
            throw r1
        L_0x0211:
            com.google.android.gms.internal.vision.zzgf r1 = com.google.android.gms.internal.vision.zzgf.zzfi()
            throw r1
        L_0x0216:
            int r4 = com.google.android.gms.internal.vision.zzeh.zza(r3, r4, r7)
            int r6 = r7.zzro
            if (r6 < 0) goto L_0x0271
            if (r6 != 0) goto L_0x0224
            r11.add(r1)
            goto L_0x0237
        L_0x0224:
            int r8 = r4 + r6
            boolean r9 = com.google.android.gms.internal.vision.zziw.zzg(r3, r4, r8)
            if (r9 == 0) goto L_0x026c
            java.lang.String r9 = new java.lang.String
            java.nio.charset.Charset r10 = com.google.android.gms.internal.vision.zzga.UTF_8
            r9.<init>(r3, r4, r6, r10)
            r11.add(r9)
        L_0x0236:
            r4 = r8
        L_0x0237:
            if (r4 >= r5) goto L_0x0422
            int r6 = com.google.android.gms.internal.vision.zzeh.zza(r3, r4, r7)
            int r8 = r7.zzro
            if (r2 != r8) goto L_0x0422
            int r4 = com.google.android.gms.internal.vision.zzeh.zza(r3, r6, r7)
            int r6 = r7.zzro
            if (r6 < 0) goto L_0x0267
            if (r6 != 0) goto L_0x024f
            r11.add(r1)
            goto L_0x0237
        L_0x024f:
            int r8 = r4 + r6
            boolean r9 = com.google.android.gms.internal.vision.zziw.zzg(r3, r4, r8)
            if (r9 == 0) goto L_0x0262
            java.lang.String r9 = new java.lang.String
            java.nio.charset.Charset r10 = com.google.android.gms.internal.vision.zzga.UTF_8
            r9.<init>(r3, r4, r6, r10)
            r11.add(r9)
            goto L_0x0236
        L_0x0262:
            com.google.android.gms.internal.vision.zzgf r1 = com.google.android.gms.internal.vision.zzgf.zzfp()
            throw r1
        L_0x0267:
            com.google.android.gms.internal.vision.zzgf r1 = com.google.android.gms.internal.vision.zzgf.zzfi()
            throw r1
        L_0x026c:
            com.google.android.gms.internal.vision.zzgf r1 = com.google.android.gms.internal.vision.zzgf.zzfp()
            throw r1
        L_0x0271:
            com.google.android.gms.internal.vision.zzgf r1 = com.google.android.gms.internal.vision.zzgf.zzfi()
            throw r1
        L_0x0276:
            r1 = 0
            if (r6 != r10) goto L_0x029e
            com.google.android.gms.internal.vision.zzem r11 = (com.google.android.gms.internal.vision.zzem) r11
            int r2 = com.google.android.gms.internal.vision.zzeh.zza(r3, r4, r7)
            int r4 = r7.zzro
            int r4 = r4 + r2
        L_0x0282:
            if (r2 >= r4) goto L_0x0295
            int r2 = com.google.android.gms.internal.vision.zzeh.zzb(r3, r2, r7)
            long r5 = r7.zzrp
            int r8 = (r5 > r14 ? 1 : (r5 == r14 ? 0 : -1))
            if (r8 == 0) goto L_0x0290
            r5 = 1
            goto L_0x0291
        L_0x0290:
            r5 = 0
        L_0x0291:
            r11.addBoolean(r5)
            goto L_0x0282
        L_0x0295:
            if (r2 != r4) goto L_0x0299
            goto L_0x014e
        L_0x0299:
            com.google.android.gms.internal.vision.zzgf r1 = com.google.android.gms.internal.vision.zzgf.zzfh()
            throw r1
        L_0x029e:
            if (r6 != 0) goto L_0x0422
            com.google.android.gms.internal.vision.zzem r11 = (com.google.android.gms.internal.vision.zzem) r11
            int r4 = com.google.android.gms.internal.vision.zzeh.zzb(r3, r4, r7)
            long r8 = r7.zzrp
            int r6 = (r8 > r14 ? 1 : (r8 == r14 ? 0 : -1))
            if (r6 == 0) goto L_0x02ae
            r6 = 1
            goto L_0x02af
        L_0x02ae:
            r6 = 0
        L_0x02af:
            r11.addBoolean(r6)
        L_0x02b2:
            if (r4 >= r5) goto L_0x0422
            int r6 = com.google.android.gms.internal.vision.zzeh.zza(r3, r4, r7)
            int r8 = r7.zzro
            if (r2 != r8) goto L_0x0422
            int r4 = com.google.android.gms.internal.vision.zzeh.zzb(r3, r6, r7)
            long r8 = r7.zzrp
            int r6 = (r8 > r14 ? 1 : (r8 == r14 ? 0 : -1))
            if (r6 == 0) goto L_0x02c8
            r6 = 1
            goto L_0x02c9
        L_0x02c8:
            r6 = 0
        L_0x02c9:
            r11.addBoolean(r6)
            goto L_0x02b2
        L_0x02cd:
            if (r6 != r10) goto L_0x02ed
            com.google.android.gms.internal.vision.zzfz r11 = (com.google.android.gms.internal.vision.zzfz) r11
            int r1 = com.google.android.gms.internal.vision.zzeh.zza(r3, r4, r7)
            int r2 = r7.zzro
            int r2 = r2 + r1
        L_0x02d8:
            if (r1 >= r2) goto L_0x02e4
            int r4 = com.google.android.gms.internal.vision.zzeh.zza(r3, r1)
            r11.zzbg(r4)
            int r1 = r1 + 4
            goto L_0x02d8
        L_0x02e4:
            if (r1 != r2) goto L_0x02e8
            goto L_0x0423
        L_0x02e8:
            com.google.android.gms.internal.vision.zzgf r1 = com.google.android.gms.internal.vision.zzgf.zzfh()
            throw r1
        L_0x02ed:
            if (r6 != r9) goto L_0x0422
            com.google.android.gms.internal.vision.zzfz r11 = (com.google.android.gms.internal.vision.zzfz) r11
            int r1 = com.google.android.gms.internal.vision.zzeh.zza(r18, r19)
            r11.zzbg(r1)
        L_0x02f8:
            int r1 = r4 + 4
            if (r1 >= r5) goto L_0x0423
            int r4 = com.google.android.gms.internal.vision.zzeh.zza(r3, r1, r7)
            int r6 = r7.zzro
            if (r2 != r6) goto L_0x0423
            int r1 = com.google.android.gms.internal.vision.zzeh.zza(r3, r4)
            r11.zzbg(r1)
            goto L_0x02f8
        L_0x030c:
            if (r6 != r10) goto L_0x032c
            com.google.android.gms.internal.vision.zzgt r11 = (com.google.android.gms.internal.vision.zzgt) r11
            int r1 = com.google.android.gms.internal.vision.zzeh.zza(r3, r4, r7)
            int r2 = r7.zzro
            int r2 = r2 + r1
        L_0x0317:
            if (r1 >= r2) goto L_0x0323
            long r4 = com.google.android.gms.internal.vision.zzeh.zzb(r3, r1)
            r11.zzp(r4)
            int r1 = r1 + 8
            goto L_0x0317
        L_0x0323:
            if (r1 != r2) goto L_0x0327
            goto L_0x0423
        L_0x0327:
            com.google.android.gms.internal.vision.zzgf r1 = com.google.android.gms.internal.vision.zzgf.zzfh()
            throw r1
        L_0x032c:
            if (r6 != r13) goto L_0x0422
            com.google.android.gms.internal.vision.zzgt r11 = (com.google.android.gms.internal.vision.zzgt) r11
            long r8 = com.google.android.gms.internal.vision.zzeh.zzb(r18, r19)
            r11.zzp(r8)
        L_0x0337:
            int r1 = r4 + 8
            if (r1 >= r5) goto L_0x0423
            int r4 = com.google.android.gms.internal.vision.zzeh.zza(r3, r1, r7)
            int r6 = r7.zzro
            if (r2 != r6) goto L_0x0423
            long r8 = com.google.android.gms.internal.vision.zzeh.zzb(r3, r4)
            r11.zzp(r8)
            goto L_0x0337
        L_0x034b:
            if (r6 != r10) goto L_0x0353
            int r1 = com.google.android.gms.internal.vision.zzeh.zza(r3, r4, r11, r7)
            goto L_0x0423
        L_0x0353:
            if (r6 != 0) goto L_0x0422
            r22 = r18
            r23 = r19
            r24 = r20
            r25 = r11
            r26 = r30
            int r1 = com.google.android.gms.internal.vision.zzeh.zza(r21, r22, r23, r24, r25, r26)
            goto L_0x0423
        L_0x0365:
            if (r6 != r10) goto L_0x0385
            com.google.android.gms.internal.vision.zzgt r11 = (com.google.android.gms.internal.vision.zzgt) r11
            int r1 = com.google.android.gms.internal.vision.zzeh.zza(r3, r4, r7)
            int r2 = r7.zzro
            int r2 = r2 + r1
        L_0x0370:
            if (r1 >= r2) goto L_0x037c
            int r1 = com.google.android.gms.internal.vision.zzeh.zzb(r3, r1, r7)
            long r4 = r7.zzrp
            r11.zzp(r4)
            goto L_0x0370
        L_0x037c:
            if (r1 != r2) goto L_0x0380
            goto L_0x0423
        L_0x0380:
            com.google.android.gms.internal.vision.zzgf r1 = com.google.android.gms.internal.vision.zzgf.zzfh()
            throw r1
        L_0x0385:
            if (r6 != 0) goto L_0x0422
            com.google.android.gms.internal.vision.zzgt r11 = (com.google.android.gms.internal.vision.zzgt) r11
            int r1 = com.google.android.gms.internal.vision.zzeh.zzb(r3, r4, r7)
            long r8 = r7.zzrp
            r11.zzp(r8)
        L_0x0392:
            if (r1 >= r5) goto L_0x0423
            int r4 = com.google.android.gms.internal.vision.zzeh.zza(r3, r1, r7)
            int r6 = r7.zzro
            if (r2 != r6) goto L_0x0423
            int r1 = com.google.android.gms.internal.vision.zzeh.zzb(r3, r4, r7)
            long r8 = r7.zzrp
            r11.zzp(r8)
            goto L_0x0392
        L_0x03a6:
            if (r6 != r10) goto L_0x03c5
            com.google.android.gms.internal.vision.zzfv r11 = (com.google.android.gms.internal.vision.zzfv) r11
            int r1 = com.google.android.gms.internal.vision.zzeh.zza(r3, r4, r7)
            int r2 = r7.zzro
            int r2 = r2 + r1
        L_0x03b1:
            if (r1 >= r2) goto L_0x03bd
            float r4 = com.google.android.gms.internal.vision.zzeh.zzd(r3, r1)
            r11.zzh(r4)
            int r1 = r1 + 4
            goto L_0x03b1
        L_0x03bd:
            if (r1 != r2) goto L_0x03c0
            goto L_0x0423
        L_0x03c0:
            com.google.android.gms.internal.vision.zzgf r1 = com.google.android.gms.internal.vision.zzgf.zzfh()
            throw r1
        L_0x03c5:
            if (r6 != r9) goto L_0x0422
            com.google.android.gms.internal.vision.zzfv r11 = (com.google.android.gms.internal.vision.zzfv) r11
            float r1 = com.google.android.gms.internal.vision.zzeh.zzd(r18, r19)
            r11.zzh(r1)
        L_0x03d0:
            int r1 = r4 + 4
            if (r1 >= r5) goto L_0x0423
            int r4 = com.google.android.gms.internal.vision.zzeh.zza(r3, r1, r7)
            int r6 = r7.zzro
            if (r2 != r6) goto L_0x0423
            float r1 = com.google.android.gms.internal.vision.zzeh.zzd(r3, r4)
            r11.zzh(r1)
            goto L_0x03d0
        L_0x03e4:
            if (r6 != r10) goto L_0x0403
            com.google.android.gms.internal.vision.zzfh r11 = (com.google.android.gms.internal.vision.zzfh) r11
            int r1 = com.google.android.gms.internal.vision.zzeh.zza(r3, r4, r7)
            int r2 = r7.zzro
            int r2 = r2 + r1
        L_0x03ef:
            if (r1 >= r2) goto L_0x03fb
            double r4 = com.google.android.gms.internal.vision.zzeh.zzc(r3, r1)
            r11.zzc(r4)
            int r1 = r1 + 8
            goto L_0x03ef
        L_0x03fb:
            if (r1 != r2) goto L_0x03fe
            goto L_0x0423
        L_0x03fe:
            com.google.android.gms.internal.vision.zzgf r1 = com.google.android.gms.internal.vision.zzgf.zzfh()
            throw r1
        L_0x0403:
            if (r6 != r13) goto L_0x0422
            com.google.android.gms.internal.vision.zzfh r11 = (com.google.android.gms.internal.vision.zzfh) r11
            double r8 = com.google.android.gms.internal.vision.zzeh.zzc(r18, r19)
            r11.zzc(r8)
        L_0x040e:
            int r1 = r4 + 8
            if (r1 >= r5) goto L_0x0423
            int r4 = com.google.android.gms.internal.vision.zzeh.zza(r3, r1, r7)
            int r6 = r7.zzro
            if (r2 != r6) goto L_0x0423
            double r8 = com.google.android.gms.internal.vision.zzeh.zzc(r3, r4)
            r11.zzc(r8)
            goto L_0x040e
        L_0x0422:
            r1 = r4
        L_0x0423:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzhj.zza(java.lang.Object, byte[], int, int, int, int, int, int, long, int, long, com.google.android.gms.internal.vision.zzei):int");
    }

    /* JADX WARNING: type inference failed for: r10v4, types: [int] */
    /* JADX WARNING: type inference failed for: r10v11 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final <K, V> int zza(T r8, byte[] r9, int r10, int r11, int r12, long r13, com.google.android.gms.internal.vision.zzei r15) throws java.io.IOException {
        /*
            r7 = this;
            sun.misc.Unsafe r0 = zzzc
            java.lang.Object r12 = r7.zzbi(r12)
            java.lang.Object r1 = r0.getObject(r8, r13)
            com.google.android.gms.internal.vision.zzha r2 = r7.zzzt
            boolean r2 = r2.zzl(r1)
            if (r2 == 0) goto L_0x0021
            com.google.android.gms.internal.vision.zzha r2 = r7.zzzt
            java.lang.Object r2 = r2.zzn(r12)
            com.google.android.gms.internal.vision.zzha r3 = r7.zzzt
            r3.zzb(r2, r1)
            r0.putObject(r8, r13, r2)
            r1 = r2
        L_0x0021:
            com.google.android.gms.internal.vision.zzha r8 = r7.zzzt
            com.google.android.gms.internal.vision.zzgy r8 = r8.zzo(r12)
            com.google.android.gms.internal.vision.zzha r12 = r7.zzzt
            java.util.Map r12 = r12.zzj(r1)
            int r10 = com.google.android.gms.internal.vision.zzeh.zza(r9, r10, r15)
            int r13 = r15.zzro
            if (r13 < 0) goto L_0x0097
            int r14 = r11 - r10
            if (r13 > r14) goto L_0x0097
            int r13 = r13 + r10
            K r14 = r8.zzyw
            V r0 = r8.zzgq
        L_0x003e:
            if (r10 >= r13) goto L_0x008c
            int r1 = r10 + 1
            byte r10 = r9[r10]
            if (r10 >= 0) goto L_0x004c
            int r1 = com.google.android.gms.internal.vision.zzeh.zza(r10, r9, r1, r15)
            int r10 = r15.zzro
        L_0x004c:
            r2 = r1
            int r1 = r10 >>> 3
            r3 = r10 & 7
            r4 = 1
            if (r1 == r4) goto L_0x0072
            r4 = 2
            if (r1 == r4) goto L_0x0058
            goto L_0x0087
        L_0x0058:
            com.google.android.gms.internal.vision.zzjd r1 = r8.zzyx
            int r1 = r1.zzhp()
            if (r3 != r1) goto L_0x0087
            com.google.android.gms.internal.vision.zzjd r4 = r8.zzyx
            V r10 = r8.zzgq
            java.lang.Class r5 = r10.getClass()
            r1 = r9
            r3 = r11
            r6 = r15
            int r10 = zza(r1, r2, r3, r4, r5, r6)
            java.lang.Object r0 = r15.zzrq
            goto L_0x003e
        L_0x0072:
            com.google.android.gms.internal.vision.zzjd r1 = r8.zzyv
            int r1 = r1.zzhp()
            if (r3 != r1) goto L_0x0087
            com.google.android.gms.internal.vision.zzjd r4 = r8.zzyv
            r5 = 0
            r1 = r9
            r3 = r11
            r6 = r15
            int r10 = zza(r1, r2, r3, r4, r5, r6)
            java.lang.Object r14 = r15.zzrq
            goto L_0x003e
        L_0x0087:
            int r10 = com.google.android.gms.internal.vision.zzeh.zza(r10, r9, r2, r11, r15)
            goto L_0x003e
        L_0x008c:
            if (r10 != r13) goto L_0x0092
            r12.put(r14, r0)
            return r13
        L_0x0092:
            com.google.android.gms.internal.vision.zzgf r8 = com.google.android.gms.internal.vision.zzgf.zzfo()
            throw r8
        L_0x0097:
            com.google.android.gms.internal.vision.zzgf r8 = com.google.android.gms.internal.vision.zzgf.zzfh()
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzhj.zza(java.lang.Object, byte[], int, int, int, long, com.google.android.gms.internal.vision.zzei):int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:62:0x018a, code lost:
        r2 = r4 + 4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x019b, code lost:
        r2 = r4 + 8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x019d, code lost:
        r12.putInt(r1, r13, r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:?, code lost:
        return r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:?, code lost:
        return r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final int zza(T r17, byte[] r18, int r19, int r20, int r21, int r22, int r23, int r24, int r25, long r26, int r28, com.google.android.gms.internal.vision.zzei r29) throws java.io.IOException {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            r3 = r18
            r4 = r19
            r2 = r21
            r8 = r22
            r5 = r23
            r9 = r26
            r6 = r28
            r11 = r29
            sun.misc.Unsafe r12 = zzzc
            int[] r7 = r0.zzzd
            int r13 = r6 + 2
            r7 = r7[r13]
            r13 = 1048575(0xfffff, float:1.469367E-39)
            r7 = r7 & r13
            long r13 = (long) r7
            r7 = 5
            r15 = 2
            switch(r25) {
                case 51: goto L_0x018d;
                case 52: goto L_0x017d;
                case 53: goto L_0x016d;
                case 54: goto L_0x016d;
                case 55: goto L_0x015d;
                case 56: goto L_0x014e;
                case 57: goto L_0x0140;
                case 58: goto L_0x0127;
                case 59: goto L_0x00f3;
                case 60: goto L_0x00c5;
                case 61: goto L_0x00b8;
                case 62: goto L_0x015d;
                case 63: goto L_0x008a;
                case 64: goto L_0x0140;
                case 65: goto L_0x014e;
                case 66: goto L_0x0075;
                case 67: goto L_0x0060;
                case 68: goto L_0x0028;
                default: goto L_0x0026;
            }
        L_0x0026:
            goto L_0x01a1
        L_0x0028:
            r7 = 3
            if (r5 != r7) goto L_0x01a1
            r2 = r2 & -8
            r7 = r2 | 4
            com.google.android.gms.internal.vision.zzhw r2 = r0.zzbh(r6)
            r3 = r18
            r4 = r19
            r5 = r20
            r6 = r7
            r7 = r29
            int r2 = zza(r2, r3, r4, r5, r6, r7)
            int r3 = r12.getInt(r1, r13)
            if (r3 != r8) goto L_0x004b
            java.lang.Object r15 = r12.getObject(r1, r9)
            goto L_0x004c
        L_0x004b:
            r15 = 0
        L_0x004c:
            if (r15 != 0) goto L_0x0055
            java.lang.Object r3 = r11.zzrq
            r12.putObject(r1, r9, r3)
            goto L_0x019d
        L_0x0055:
            java.lang.Object r3 = r11.zzrq
            java.lang.Object r3 = com.google.android.gms.internal.vision.zzga.zza(r15, r3)
            r12.putObject(r1, r9, r3)
            goto L_0x019d
        L_0x0060:
            if (r5 != 0) goto L_0x01a1
            int r2 = com.google.android.gms.internal.vision.zzeh.zzb(r3, r4, r11)
            long r3 = r11.zzrp
            long r3 = com.google.android.gms.internal.vision.zzez.zzd(r3)
            java.lang.Long r3 = java.lang.Long.valueOf(r3)
            r12.putObject(r1, r9, r3)
            goto L_0x019d
        L_0x0075:
            if (r5 != 0) goto L_0x01a1
            int r2 = com.google.android.gms.internal.vision.zzeh.zza(r3, r4, r11)
            int r3 = r11.zzro
            int r3 = com.google.android.gms.internal.vision.zzez.zzaq(r3)
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r12.putObject(r1, r9, r3)
            goto L_0x019d
        L_0x008a:
            if (r5 != 0) goto L_0x01a1
            int r3 = com.google.android.gms.internal.vision.zzeh.zza(r3, r4, r11)
            int r4 = r11.zzro
            com.google.android.gms.internal.vision.zzgd r5 = r0.zzbj(r6)
            if (r5 == 0) goto L_0x00ae
            boolean r5 = r5.zzh(r4)
            if (r5 == 0) goto L_0x009f
            goto L_0x00ae
        L_0x009f:
            com.google.android.gms.internal.vision.zzip r1 = zzq(r17)
            long r4 = (long) r4
            java.lang.Long r4 = java.lang.Long.valueOf(r4)
            r1.zzb(r2, r4)
            r2 = r3
            goto L_0x01a2
        L_0x00ae:
            java.lang.Integer r2 = java.lang.Integer.valueOf(r4)
            r12.putObject(r1, r9, r2)
            r2 = r3
            goto L_0x019d
        L_0x00b8:
            if (r5 != r15) goto L_0x01a1
            int r2 = com.google.android.gms.internal.vision.zzeh.zze(r3, r4, r11)
            java.lang.Object r3 = r11.zzrq
            r12.putObject(r1, r9, r3)
            goto L_0x019d
        L_0x00c5:
            if (r5 != r15) goto L_0x01a1
            com.google.android.gms.internal.vision.zzhw r2 = r0.zzbh(r6)
            r5 = r20
            int r2 = zza(r2, r3, r4, r5, r11)
            int r3 = r12.getInt(r1, r13)
            if (r3 != r8) goto L_0x00dc
            java.lang.Object r15 = r12.getObject(r1, r9)
            goto L_0x00dd
        L_0x00dc:
            r15 = 0
        L_0x00dd:
            if (r15 != 0) goto L_0x00e5
            java.lang.Object r3 = r11.zzrq
            r12.putObject(r1, r9, r3)
            goto L_0x00ee
        L_0x00e5:
            java.lang.Object r3 = r11.zzrq
            java.lang.Object r3 = com.google.android.gms.internal.vision.zzga.zza(r15, r3)
            r12.putObject(r1, r9, r3)
        L_0x00ee:
            r12.putInt(r1, r13, r8)
            goto L_0x01a2
        L_0x00f3:
            if (r5 != r15) goto L_0x01a1
            int r2 = com.google.android.gms.internal.vision.zzeh.zza(r3, r4, r11)
            int r4 = r11.zzro
            if (r4 != 0) goto L_0x0103
            java.lang.String r3 = ""
            r12.putObject(r1, r9, r3)
            goto L_0x0122
        L_0x0103:
            r5 = 536870912(0x20000000, float:1.0842022E-19)
            r5 = r24 & r5
            if (r5 == 0) goto L_0x0117
            int r5 = r2 + r4
            boolean r5 = com.google.android.gms.internal.vision.zziw.zzg(r3, r2, r5)
            if (r5 == 0) goto L_0x0112
            goto L_0x0117
        L_0x0112:
            com.google.android.gms.internal.vision.zzgf r1 = com.google.android.gms.internal.vision.zzgf.zzfp()
            throw r1
        L_0x0117:
            java.lang.String r5 = new java.lang.String
            java.nio.charset.Charset r6 = com.google.android.gms.internal.vision.zzga.UTF_8
            r5.<init>(r3, r2, r4, r6)
            r12.putObject(r1, r9, r5)
            int r2 = r2 + r4
        L_0x0122:
            r12.putInt(r1, r13, r8)
            goto L_0x01a2
        L_0x0127:
            if (r5 != 0) goto L_0x01a1
            int r2 = com.google.android.gms.internal.vision.zzeh.zzb(r3, r4, r11)
            long r3 = r11.zzrp
            r5 = 0
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 == 0) goto L_0x0137
            r15 = 1
            goto L_0x0138
        L_0x0137:
            r15 = 0
        L_0x0138:
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r15)
            r12.putObject(r1, r9, r3)
            goto L_0x019d
        L_0x0140:
            if (r5 != r7) goto L_0x01a1
            int r2 = com.google.android.gms.internal.vision.zzeh.zza(r18, r19)
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r12.putObject(r1, r9, r2)
            goto L_0x018a
        L_0x014e:
            r2 = 1
            if (r5 != r2) goto L_0x01a1
            long r2 = com.google.android.gms.internal.vision.zzeh.zzb(r18, r19)
            java.lang.Long r2 = java.lang.Long.valueOf(r2)
            r12.putObject(r1, r9, r2)
            goto L_0x019b
        L_0x015d:
            if (r5 != 0) goto L_0x01a1
            int r2 = com.google.android.gms.internal.vision.zzeh.zza(r3, r4, r11)
            int r3 = r11.zzro
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r12.putObject(r1, r9, r3)
            goto L_0x019d
        L_0x016d:
            if (r5 != 0) goto L_0x01a1
            int r2 = com.google.android.gms.internal.vision.zzeh.zzb(r3, r4, r11)
            long r3 = r11.zzrp
            java.lang.Long r3 = java.lang.Long.valueOf(r3)
            r12.putObject(r1, r9, r3)
            goto L_0x019d
        L_0x017d:
            if (r5 != r7) goto L_0x01a1
            float r2 = com.google.android.gms.internal.vision.zzeh.zzd(r18, r19)
            java.lang.Float r2 = java.lang.Float.valueOf(r2)
            r12.putObject(r1, r9, r2)
        L_0x018a:
            int r2 = r4 + 4
            goto L_0x019d
        L_0x018d:
            r2 = 1
            if (r5 != r2) goto L_0x01a1
            double r2 = com.google.android.gms.internal.vision.zzeh.zzc(r18, r19)
            java.lang.Double r2 = java.lang.Double.valueOf(r2)
            r12.putObject(r1, r9, r2)
        L_0x019b:
            int r2 = r4 + 8
        L_0x019d:
            r12.putInt(r1, r13, r8)
            goto L_0x01a2
        L_0x01a1:
            r2 = r4
        L_0x01a2:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzhj.zza(java.lang.Object, byte[], int, int, int, int, int, int, int, long, int, com.google.android.gms.internal.vision.zzei):int");
    }

    private final zzhw zzbh(int i) {
        int i2 = (i / 3) << 1;
        zzhw zzhw = (zzhw) this.zzze[i2];
        if (zzhw != null) {
            return zzhw;
        }
        zzhw zzf = zzhs.zzgl().zzf((Class) this.zzze[i2 + 1]);
        this.zzze[i2] = zzf;
        return zzf;
    }

    private final Object zzbi(int i) {
        return this.zzze[(i / 3) << 1];
    }

    private final zzgd zzbj(int i) {
        return (zzgd) this.zzze[((i / 3) << 1) + 1];
    }

    /* JADX WARNING: type inference failed for: r29v0, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r12v0 */
    /* JADX WARNING: type inference failed for: r12v1, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r0v10, types: [int, byte] */
    /* JADX WARNING: type inference failed for: r5v4, types: [int] */
    /* JADX WARNING: type inference failed for: r12v2 */
    /* JADX WARNING: type inference failed for: r3v5 */
    /* JADX WARNING: type inference failed for: r12v3 */
    /* JADX WARNING: type inference failed for: r3v6 */
    /* JADX WARNING: type inference failed for: r12v4 */
    /* JADX WARNING: type inference failed for: r7v8 */
    /* JADX WARNING: type inference failed for: r3v7 */
    /* JADX WARNING: type inference failed for: r7v10 */
    /* JADX WARNING: type inference failed for: r0v15, types: [int] */
    /* JADX WARNING: type inference failed for: r1v12, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r12v5 */
    /* JADX WARNING: type inference failed for: r3v9 */
    /* JADX WARNING: type inference failed for: r19v2 */
    /* JADX WARNING: type inference failed for: r8v3 */
    /* JADX WARNING: type inference failed for: r12v7 */
    /* JADX WARNING: type inference failed for: r25v0 */
    /* JADX WARNING: type inference failed for: r7v11 */
    /* JADX WARNING: type inference failed for: r25v1 */
    /* JADX WARNING: type inference failed for: r25v2 */
    /* JADX WARNING: type inference failed for: r25v3 */
    /* JADX WARNING: type inference failed for: r2v13, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r5v8, types: [int] */
    /* JADX WARNING: type inference failed for: r7v13 */
    /* JADX WARNING: type inference failed for: r12v9 */
    /* JADX WARNING: type inference failed for: r2v14, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r25v4 */
    /* JADX WARNING: type inference failed for: r12v10 */
    /* JADX WARNING: type inference failed for: r3v12 */
    /* JADX WARNING: type inference failed for: r2v16, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r5v11, types: [int] */
    /* JADX WARNING: type inference failed for: r25v5 */
    /* JADX WARNING: type inference failed for: r25v6 */
    /* JADX WARNING: type inference failed for: r1v22, types: [int] */
    /* JADX WARNING: type inference failed for: r2v17, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r3v15 */
    /* JADX WARNING: type inference failed for: r8v8 */
    /* JADX WARNING: type inference failed for: r7v18 */
    /* JADX WARNING: type inference failed for: r12v12 */
    /* JADX WARNING: type inference failed for: r3v16 */
    /* JADX WARNING: type inference failed for: r12v13 */
    /* JADX WARNING: type inference failed for: r8v9 */
    /* JADX WARNING: type inference failed for: r3v17 */
    /* JADX WARNING: type inference failed for: r12v14 */
    /* JADX WARNING: type inference failed for: r8v10 */
    /* JADX WARNING: type inference failed for: r12v15 */
    /* JADX WARNING: type inference failed for: r8v11 */
    /* JADX WARNING: type inference failed for: r12v16 */
    /* JADX WARNING: type inference failed for: r8v12 */
    /* JADX WARNING: type inference failed for: r8v13 */
    /* JADX WARNING: type inference failed for: r12v17, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r8v14 */
    /* JADX WARNING: type inference failed for: r12v18, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r12v19 */
    /* JADX WARNING: type inference failed for: r8v15 */
    /* JADX WARNING: type inference failed for: r3v18 */
    /* JADX WARNING: type inference failed for: r8v16 */
    /* JADX WARNING: type inference failed for: r12v20, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r8v17 */
    /* JADX WARNING: type inference failed for: r12v21, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r8v18 */
    /* JADX WARNING: type inference failed for: r8v19 */
    /* JADX WARNING: type inference failed for: r12v22, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r12v23 */
    /* JADX WARNING: type inference failed for: r8v20 */
    /* JADX WARNING: type inference failed for: r8v21 */
    /* JADX WARNING: type inference failed for: r12v24, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r8v22 */
    /* JADX WARNING: type inference failed for: r12v25, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r8v23 */
    /* JADX WARNING: type inference failed for: r12v26, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r8v24 */
    /* JADX WARNING: type inference failed for: r12v27, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r8v25 */
    /* JADX WARNING: type inference failed for: r12v28, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r8v26, types: [int] */
    /* JADX WARNING: type inference failed for: r12v29, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r8v27 */
    /* JADX WARNING: type inference failed for: r12v30, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r8v28 */
    /* JADX WARNING: type inference failed for: r12v31 */
    /* JADX WARNING: type inference failed for: r8v29 */
    /* JADX WARNING: type inference failed for: r12v32, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r8v31 */
    /* JADX WARNING: type inference failed for: r1v57, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r8v33 */
    /* JADX WARNING: type inference failed for: r12v33 */
    /* JADX WARNING: type inference failed for: r12v34 */
    /* JADX WARNING: type inference failed for: r8v34 */
    /* JADX WARNING: type inference failed for: r7v20 */
    /* JADX WARNING: type inference failed for: r5v18 */
    /* JADX WARNING: type inference failed for: r3v21, types: [int] */
    /* JADX WARNING: type inference failed for: r5v19 */
    /* JADX WARNING: type inference failed for: r12v35 */
    /* JADX WARNING: type inference failed for: r3v22 */
    /* JADX WARNING: type inference failed for: r12v36 */
    /* JADX WARNING: type inference failed for: r3v23 */
    /* JADX WARNING: type inference failed for: r12v37 */
    /* JADX WARNING: type inference failed for: r7v21 */
    /* JADX WARNING: type inference failed for: r25v7 */
    /* JADX WARNING: type inference failed for: r25v8 */
    /* JADX WARNING: type inference failed for: r12v38 */
    /* JADX WARNING: type inference failed for: r3v24 */
    /* JADX WARNING: type inference failed for: r12v39 */
    /* JADX WARNING: type inference failed for: r12v40 */
    /* JADX WARNING: type inference failed for: r8v36 */
    /* JADX WARNING: type inference failed for: r12v41 */
    /* JADX WARNING: type inference failed for: r8v37 */
    /* JADX WARNING: type inference failed for: r12v42 */
    /* JADX WARNING: type inference failed for: r8v38 */
    /* JADX WARNING: type inference failed for: r12v43 */
    /* JADX WARNING: type inference failed for: r12v44 */
    /* JADX WARNING: type inference failed for: r12v45 */
    /* JADX WARNING: type inference failed for: r12v46 */
    /* JADX WARNING: type inference failed for: r12v47 */
    /* JADX WARNING: type inference failed for: r8v39 */
    /* JADX WARNING: type inference failed for: r12v48 */
    /* JADX WARNING: type inference failed for: r12v49 */
    /* JADX WARNING: type inference failed for: r8v40 */
    /* JADX WARNING: type inference failed for: r12v50 */
    /* JADX WARNING: type inference failed for: r12v51 */
    /* JADX WARNING: type inference failed for: r12v52 */
    /* JADX WARNING: type inference failed for: r8v41 */
    /* JADX WARNING: type inference failed for: r8v42 */
    /* JADX WARNING: type inference failed for: r8v43 */
    /* JADX WARNING: type inference failed for: r12v53 */
    /* JADX WARNING: type inference failed for: r12v54 */
    /* JADX WARNING: type inference failed for: r12v55 */
    /* JADX WARNING: type inference failed for: r12v56 */
    /* JADX WARNING: type inference failed for: r12v57 */
    /* JADX WARNING: type inference failed for: r12v58 */
    /* JADX WARNING: type inference failed for: r8v44 */
    /* JADX WARNING: type inference failed for: r12v59 */
    /* JADX WARNING: Code restructure failed: missing block: B:115:0x034e, code lost:
        if (r0 == r15) goto L_0x03bb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:122:0x0395, code lost:
        if (r0 == r15) goto L_0x03bb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:160:0x0017, code lost:
        r12 = r12;
        r3 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00ff, code lost:
        r12 = r29;
        r8 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x0207, code lost:
        r6 = r6 | r22;
        r12 = r12;
        r8 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x022b, code lost:
        r13 = r4;
        r8 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x0263, code lost:
        r6 = r6 | r22;
        r3 = r8;
        r2 = r9;
        r9 = r11;
        r0 = r13;
        r1 = r18;
        r13 = r31;
        r12 = r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x029e, code lost:
        r0 = r13 + 8;
        r12 = r12;
        r8 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x02a0, code lost:
        r6 = r6 | r22;
        r12 = r12;
        r8 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x02a2, code lost:
        r13 = r31;
        r12 = r12;
        r8 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:0x02a4, code lost:
        r3 = r8;
        r2 = r9;
        r9 = r11;
        r1 = r18;
        r12 = r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:0x02a9, code lost:
        r11 = r32;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x02ad, code lost:
        r17 = r6;
        r24 = r7;
        r7 = r8;
        r19 = r9;
        r26 = r10;
        r2 = r13;
        r6 = r32;
     */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=null, for r0v10, types: [int, byte] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte[], code=null, for r29v0, types: [byte[]] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r12v2
      assigns: []
      uses: []
      mth insns count: 518
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
    	at jadx.core.ProcessClass.process(ProcessClass.java:35)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Unknown variable types count: 54 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final int zza(T r28, byte[] r29, int r30, int r31, int r32, com.google.android.gms.internal.vision.zzei r33) throws java.io.IOException {
        /*
            r27 = this;
            r15 = r27
            r14 = r28
            r12 = r29
            r13 = r31
            r11 = r32
            r9 = r33
            sun.misc.Unsafe r10 = zzzc
            r16 = 0
            r0 = r30
            r1 = -1
            r2 = 0
            r3 = 0
            r6 = 0
            r7 = -1
        L_0x0017:
            if (r0 >= r13) goto L_0x03fa
            int r3 = r0 + 1
            byte r0 = r12[r0]
            if (r0 >= 0) goto L_0x0028
            int r0 = com.google.android.gms.internal.vision.zzeh.zza(r0, r12, r3, r9)
            int r3 = r9.zzro
            r4 = r0
            r5 = r3
            goto L_0x002a
        L_0x0028:
            r5 = r0
            r4 = r3
        L_0x002a:
            int r3 = r5 >>> 3
            r0 = r5 & 7
            r8 = 3
            if (r3 <= r1) goto L_0x0037
            int r2 = r2 / r8
            int r1 = r15.zzr(r3, r2)
            goto L_0x003b
        L_0x0037:
            int r1 = r15.zzbn(r3)
        L_0x003b:
            r2 = r1
            r1 = -1
            if (r2 != r1) goto L_0x004e
            r18 = r3
            r2 = r4
            r17 = r6
            r24 = r7
            r26 = r10
            r6 = r11
            r19 = 0
            r7 = r5
            goto L_0x03bf
        L_0x004e:
            int[] r1 = r15.zzzd
            int r18 = r2 + 1
            r8 = r1[r18]
            r18 = 267386880(0xff00000, float:2.3665827E-29)
            r18 = r8 & r18
            int r11 = r18 >>> 20
            r18 = 1048575(0xfffff, float:1.469367E-39)
            r19 = r5
            r5 = r8 & r18
            long r12 = (long) r5
            r5 = 17
            r20 = r8
            if (r11 > r5) goto L_0x02bb
            int r5 = r2 + 2
            r1 = r1[r5]
            int r5 = r1 >>> 20
            r8 = 1
            int r22 = r8 << r5
            r1 = r1 & r18
            if (r1 == r7) goto L_0x0083
            r5 = -1
            if (r7 == r5) goto L_0x007c
            long r8 = (long) r7
            r10.putInt(r14, r8, r6)
        L_0x007c:
            long r6 = (long) r1
            int r6 = r10.getInt(r14, r6)
            r7 = r1
            goto L_0x0084
        L_0x0083:
            r5 = -1
        L_0x0084:
            r1 = 5
            switch(r11) {
                case 0: goto L_0x0287;
                case 1: goto L_0x026e;
                case 2: goto L_0x0248;
                case 3: goto L_0x0248;
                case 4: goto L_0x022e;
                case 5: goto L_0x020b;
                case 6: goto L_0x01ee;
                case 7: goto L_0x01cb;
                case 8: goto L_0x01a5;
                case 9: goto L_0x016e;
                case 10: goto L_0x0154;
                case 11: goto L_0x022e;
                case 12: goto L_0x0120;
                case 13: goto L_0x01ee;
                case 14: goto L_0x020b;
                case 15: goto L_0x0103;
                case 16: goto L_0x00df;
                case 17: goto L_0x0096;
                default: goto L_0x0088;
            }
        L_0x0088:
            r12 = r29
            r11 = r33
            r9 = r2
            r18 = r3
            r13 = r4
            r8 = r19
            r19 = -1
            goto L_0x02ad
        L_0x0096:
            r8 = 3
            if (r0 != r8) goto L_0x00d5
            int r0 = r3 << 3
            r8 = r0 | 4
            com.google.android.gms.internal.vision.zzhw r0 = r15.zzbh(r2)
            r1 = r29
            r9 = r2
            r2 = r4
            r18 = r3
            r3 = r31
            r4 = r8
            r8 = r19
            r19 = -1
            r5 = r33
            int r0 = zza(r0, r1, r2, r3, r4, r5)
            r1 = r6 & r22
            if (r1 != 0) goto L_0x00c0
            r11 = r33
            java.lang.Object r1 = r11.zzrq
            r10.putObject(r14, r12, r1)
            goto L_0x00cf
        L_0x00c0:
            r11 = r33
            java.lang.Object r1 = r10.getObject(r14, r12)
            java.lang.Object r2 = r11.zzrq
            java.lang.Object r1 = com.google.android.gms.internal.vision.zzga.zza(r1, r2)
            r10.putObject(r14, r12, r1)
        L_0x00cf:
            r6 = r6 | r22
            r12 = r29
            goto L_0x02a2
        L_0x00d5:
            r11 = r33
            r9 = r2
            r18 = r3
            r8 = r19
            r19 = -1
            goto L_0x00ff
        L_0x00df:
            r11 = r33
            r9 = r2
            r18 = r3
            r8 = r19
            r19 = -1
            if (r0 != 0) goto L_0x00ff
            r2 = r12
            r12 = r29
            int r13 = com.google.android.gms.internal.vision.zzeh.zzb(r12, r4, r11)
            long r0 = r11.zzrp
            long r4 = com.google.android.gms.internal.vision.zzez.zzd(r0)
            r0 = r10
            r1 = r28
            r0.putLong(r1, r2, r4)
            goto L_0x0263
        L_0x00ff:
            r12 = r29
            goto L_0x022b
        L_0x0103:
            r11 = r33
            r9 = r2
            r18 = r3
            r2 = r12
            r8 = r19
            r19 = -1
            r12 = r29
            if (r0 != 0) goto L_0x022b
            int r0 = com.google.android.gms.internal.vision.zzeh.zza(r12, r4, r11)
            int r1 = r11.zzro
            int r1 = com.google.android.gms.internal.vision.zzez.zzaq(r1)
            r10.putInt(r14, r2, r1)
            goto L_0x02a0
        L_0x0120:
            r11 = r33
            r9 = r2
            r18 = r3
            r2 = r12
            r8 = r19
            r19 = -1
            r12 = r29
            if (r0 != 0) goto L_0x022b
            int r0 = com.google.android.gms.internal.vision.zzeh.zza(r12, r4, r11)
            int r1 = r11.zzro
            com.google.android.gms.internal.vision.zzgd r4 = r15.zzbj(r9)
            if (r4 == 0) goto L_0x014f
            boolean r4 = r4.zzh(r1)
            if (r4 == 0) goto L_0x0141
            goto L_0x014f
        L_0x0141:
            com.google.android.gms.internal.vision.zzip r2 = zzq(r28)
            long r3 = (long) r1
            java.lang.Long r1 = java.lang.Long.valueOf(r3)
            r2.zzb(r8, r1)
            goto L_0x02a2
        L_0x014f:
            r10.putInt(r14, r2, r1)
            goto L_0x02a0
        L_0x0154:
            r11 = r33
            r9 = r2
            r18 = r3
            r2 = r12
            r8 = r19
            r1 = 2
            r19 = -1
            r12 = r29
            if (r0 != r1) goto L_0x022b
            int r0 = com.google.android.gms.internal.vision.zzeh.zze(r12, r4, r11)
            java.lang.Object r1 = r11.zzrq
            r10.putObject(r14, r2, r1)
            goto L_0x02a0
        L_0x016e:
            r11 = r33
            r9 = r2
            r18 = r3
            r2 = r12
            r8 = r19
            r1 = 2
            r19 = -1
            r12 = r29
            if (r0 != r1) goto L_0x01a1
            com.google.android.gms.internal.vision.zzhw r0 = r15.zzbh(r9)
            r13 = r31
            int r0 = zza(r0, r12, r4, r13, r11)
            r1 = r6 & r22
            if (r1 != 0) goto L_0x0192
            java.lang.Object r1 = r11.zzrq
            r10.putObject(r14, r2, r1)
            goto L_0x0207
        L_0x0192:
            java.lang.Object r1 = r10.getObject(r14, r2)
            java.lang.Object r4 = r11.zzrq
            java.lang.Object r1 = com.google.android.gms.internal.vision.zzga.zza(r1, r4)
            r10.putObject(r14, r2, r1)
            goto L_0x0207
        L_0x01a1:
            r13 = r31
            goto L_0x022b
        L_0x01a5:
            r11 = r33
            r9 = r2
            r18 = r3
            r2 = r12
            r8 = r19
            r1 = 2
            r19 = -1
            r12 = r29
            r13 = r31
            if (r0 != r1) goto L_0x022b
            r0 = 536870912(0x20000000, float:1.0842022E-19)
            r0 = r20 & r0
            if (r0 != 0) goto L_0x01c1
            int r0 = com.google.android.gms.internal.vision.zzeh.zzc(r12, r4, r11)
            goto L_0x01c5
        L_0x01c1:
            int r0 = com.google.android.gms.internal.vision.zzeh.zzd(r12, r4, r11)
        L_0x01c5:
            java.lang.Object r1 = r11.zzrq
            r10.putObject(r14, r2, r1)
            goto L_0x0207
        L_0x01cb:
            r11 = r33
            r9 = r2
            r18 = r3
            r2 = r12
            r8 = r19
            r19 = -1
            r12 = r29
            r13 = r31
            if (r0 != 0) goto L_0x022b
            int r0 = com.google.android.gms.internal.vision.zzeh.zzb(r12, r4, r11)
            long r4 = r11.zzrp
            r20 = 0
            int r1 = (r4 > r20 ? 1 : (r4 == r20 ? 0 : -1))
            if (r1 == 0) goto L_0x01e9
            r1 = 1
            goto L_0x01ea
        L_0x01e9:
            r1 = 0
        L_0x01ea:
            com.google.android.gms.internal.vision.zziu.zza(r14, r2, r1)
            goto L_0x0207
        L_0x01ee:
            r11 = r33
            r9 = r2
            r18 = r3
            r2 = r12
            r8 = r19
            r19 = -1
            r12 = r29
            r13 = r31
            if (r0 != r1) goto L_0x022b
            int r0 = com.google.android.gms.internal.vision.zzeh.zza(r12, r4)
            r10.putInt(r14, r2, r0)
            int r0 = r4 + 4
        L_0x0207:
            r6 = r6 | r22
            goto L_0x02a4
        L_0x020b:
            r11 = r33
            r9 = r2
            r18 = r3
            r2 = r12
            r8 = r19
            r1 = 1
            r19 = -1
            r12 = r29
            r13 = r31
            if (r0 != r1) goto L_0x022b
            long r20 = com.google.android.gms.internal.vision.zzeh.zzb(r12, r4)
            r0 = r10
            r1 = r28
            r13 = r4
            r4 = r20
            r0.putLong(r1, r2, r4)
            goto L_0x029e
        L_0x022b:
            r13 = r4
            goto L_0x02ad
        L_0x022e:
            r11 = r33
            r9 = r2
            r18 = r3
            r2 = r12
            r8 = r19
            r19 = -1
            r12 = r29
            r13 = r4
            if (r0 != 0) goto L_0x02ad
            int r0 = com.google.android.gms.internal.vision.zzeh.zza(r12, r13, r11)
            int r1 = r11.zzro
            r10.putInt(r14, r2, r1)
            goto L_0x02a0
        L_0x0248:
            r11 = r33
            r9 = r2
            r18 = r3
            r2 = r12
            r8 = r19
            r19 = -1
            r12 = r29
            r13 = r4
            if (r0 != 0) goto L_0x02ad
            int r13 = com.google.android.gms.internal.vision.zzeh.zzb(r12, r13, r11)
            long r4 = r11.zzrp
            r0 = r10
            r1 = r28
            r0.putLong(r1, r2, r4)
        L_0x0263:
            r6 = r6 | r22
            r3 = r8
            r2 = r9
            r9 = r11
            r0 = r13
            r1 = r18
            r13 = r31
            goto L_0x02a9
        L_0x026e:
            r11 = r33
            r9 = r2
            r18 = r3
            r2 = r12
            r8 = r19
            r19 = -1
            r12 = r29
            r13 = r4
            if (r0 != r1) goto L_0x02ad
            float r0 = com.google.android.gms.internal.vision.zzeh.zzd(r12, r13)
            com.google.android.gms.internal.vision.zziu.zza(r14, r2, r0)
            int r0 = r13 + 4
            goto L_0x02a0
        L_0x0287:
            r11 = r33
            r9 = r2
            r18 = r3
            r2 = r12
            r8 = r19
            r1 = 1
            r19 = -1
            r12 = r29
            r13 = r4
            if (r0 != r1) goto L_0x02ad
            double r0 = com.google.android.gms.internal.vision.zzeh.zzc(r12, r13)
            com.google.android.gms.internal.vision.zziu.zza(r14, r2, r0)
        L_0x029e:
            int r0 = r13 + 8
        L_0x02a0:
            r6 = r6 | r22
        L_0x02a2:
            r13 = r31
        L_0x02a4:
            r3 = r8
            r2 = r9
            r9 = r11
            r1 = r18
        L_0x02a9:
            r11 = r32
            goto L_0x0017
        L_0x02ad:
            r17 = r6
            r24 = r7
            r7 = r8
            r19 = r9
            r26 = r10
            r2 = r13
            r6 = r32
            goto L_0x03bf
        L_0x02bb:
            r9 = r2
            r18 = r3
            r2 = r12
            r8 = r19
            r19 = -1
            r12 = r29
            r13 = r4
            r1 = 27
            if (r11 != r1) goto L_0x0319
            r1 = 2
            if (r0 != r1) goto L_0x030c
            java.lang.Object r0 = r10.getObject(r14, r2)
            com.google.android.gms.internal.vision.zzge r0 = (com.google.android.gms.internal.vision.zzge) r0
            boolean r1 = r0.zzch()
            if (r1 != 0) goto L_0x02eb
            int r1 = r0.size()
            if (r1 != 0) goto L_0x02e2
            r1 = 10
            goto L_0x02e4
        L_0x02e2:
            int r1 = r1 << 1
        L_0x02e4:
            com.google.android.gms.internal.vision.zzge r0 = r0.zzah(r1)
            r10.putObject(r14, r2, r0)
        L_0x02eb:
            r5 = r0
            com.google.android.gms.internal.vision.zzhw r0 = r15.zzbh(r9)
            r1 = r8
            r2 = r29
            r3 = r13
            r4 = r31
            r17 = r6
            r6 = r33
            int r0 = zza(r0, r1, r2, r3, r4, r5, r6)
            r13 = r31
            r11 = r32
            r3 = r8
            r2 = r9
            r6 = r17
            r1 = r18
            r9 = r33
            goto L_0x0017
        L_0x030c:
            r17 = r6
            r24 = r7
            r25 = r8
            r19 = r9
            r26 = r10
            r15 = r13
            goto L_0x0398
        L_0x0319:
            r17 = r6
            r1 = 49
            if (r11 > r1) goto L_0x036a
            r6 = r20
            long r5 = (long) r6
            r4 = r0
            r0 = r27
            r1 = r28
            r22 = r2
            r2 = r29
            r3 = r13
            r30 = r4
            r4 = r31
            r20 = r5
            r5 = r8
            r6 = r18
            r24 = r7
            r7 = r30
            r25 = r8
            r15 = -1
            r8 = r9
            r19 = r9
            r26 = r10
            r9 = r20
            r15 = r32
            r15 = r13
            r12 = r22
            r14 = r33
            int r0 = r0.zza((T) r1, r2, r3, r4, r5, r6, r7, r8, r9, r11, r12, r14)
            if (r0 != r15) goto L_0x0352
            goto L_0x03bb
        L_0x0352:
            r15 = r27
            r14 = r28
            r12 = r29
            r13 = r31
            r11 = r32
            r9 = r33
            r6 = r17
            r1 = r18
            r2 = r19
            r7 = r24
            r3 = r25
            goto L_0x03e7
        L_0x036a:
            r30 = r0
            r22 = r2
            r24 = r7
            r25 = r8
            r19 = r9
            r26 = r10
            r15 = r13
            r6 = r20
            r0 = 50
            if (r11 != r0) goto L_0x039e
            r7 = r30
            r0 = 2
            if (r7 != r0) goto L_0x0398
            r0 = r27
            r1 = r28
            r2 = r29
            r3 = r15
            r4 = r31
            r5 = r19
            r6 = r22
            r8 = r33
            int r0 = r0.zza((T) r1, r2, r3, r4, r5, r6, r8)
            if (r0 != r15) goto L_0x0352
            goto L_0x03bb
        L_0x0398:
            r6 = r32
            r2 = r15
        L_0x039b:
            r7 = r25
            goto L_0x03bf
        L_0x039e:
            r7 = r30
            r0 = r27
            r1 = r28
            r2 = r29
            r3 = r15
            r4 = r31
            r5 = r25
            r8 = r6
            r6 = r18
            r9 = r11
            r10 = r22
            r12 = r19
            r13 = r33
            int r0 = r0.zza((T) r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r12, r13)
            if (r0 != r15) goto L_0x03eb
        L_0x03bb:
            r6 = r32
            r2 = r0
            goto L_0x039b
        L_0x03bf:
            if (r7 != r6) goto L_0x03c6
            if (r6 != 0) goto L_0x03c4
            goto L_0x03c6
        L_0x03c4:
            r3 = r7
            goto L_0x0402
        L_0x03c6:
            r0 = r7
            r1 = r29
            r3 = r31
            r4 = r28
            r5 = r33
            int r0 = zza(r0, r1, r2, r3, r4, r5)
            r15 = r27
            r14 = r28
            r12 = r29
            r13 = r31
            r9 = r33
            r11 = r6
        L_0x03de:
            r3 = r7
            r6 = r17
            r1 = r18
            r2 = r19
            r7 = r24
        L_0x03e7:
            r10 = r26
            goto L_0x0017
        L_0x03eb:
            r7 = r25
            r15 = r27
            r14 = r28
            r12 = r29
            r13 = r31
            r11 = r32
            r9 = r33
            goto L_0x03de
        L_0x03fa:
            r17 = r6
            r24 = r7
            r26 = r10
            r6 = r11
            r2 = r0
        L_0x0402:
            r1 = r17
            r0 = r24
            r4 = -1
            if (r0 == r4) goto L_0x0412
            long r4 = (long) r0
            r0 = r28
            r7 = r26
            r7.putInt(r0, r4, r1)
            goto L_0x0414
        L_0x0412:
            r0 = r28
        L_0x0414:
            r1 = 0
            r4 = r27
            int r5 = r4.zzzn
        L_0x0419:
            int r7 = r4.zzzo
            if (r5 >= r7) goto L_0x042c
            int[] r7 = r4.zzzm
            r7 = r7[r5]
            com.google.android.gms.internal.vision.zzio<?, ?> r8 = r4.zzzr
            java.lang.Object r1 = r4.zza(r0, r7, (UB) r1, r8)
            com.google.android.gms.internal.vision.zzip r1 = (com.google.android.gms.internal.vision.zzip) r1
            int r5 = r5 + 1
            goto L_0x0419
        L_0x042c:
            if (r1 == 0) goto L_0x0433
            com.google.android.gms.internal.vision.zzio<?, ?> r5 = r4.zzzr
            r5.zzf(r0, r1)
        L_0x0433:
            if (r6 != 0) goto L_0x043f
            r0 = r31
            if (r2 != r0) goto L_0x043a
            goto L_0x0445
        L_0x043a:
            com.google.android.gms.internal.vision.zzgf r0 = com.google.android.gms.internal.vision.zzgf.zzfo()
            throw r0
        L_0x043f:
            r0 = r31
            if (r2 > r0) goto L_0x0446
            if (r3 != r6) goto L_0x0446
        L_0x0445:
            return r2
        L_0x0446:
            com.google.android.gms.internal.vision.zzgf r0 = com.google.android.gms.internal.vision.zzgf.zzfo()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzhj.zza(java.lang.Object, byte[], int, int, int, com.google.android.gms.internal.vision.zzei):int");
    }

    /* JADX WARNING: type inference failed for: r29v0, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r12v0 */
    /* JADX WARNING: type inference failed for: r2v0, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r12v1, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r0v5, types: [int, byte] */
    /* JADX WARNING: type inference failed for: r17v0, types: [int] */
    /* JADX WARNING: type inference failed for: r12v2 */
    /* JADX WARNING: type inference failed for: r12v3 */
    /* JADX WARNING: type inference failed for: r0v9, types: [int] */
    /* JADX WARNING: type inference failed for: r1v5, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r2v8, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r5v3, types: [int] */
    /* JADX WARNING: type inference failed for: r2v9, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r2v10, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r5v5, types: [int] */
    /* JADX WARNING: type inference failed for: r1v11, types: [int] */
    /* JADX WARNING: type inference failed for: r2v11, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r17v1 */
    /* JADX WARNING: type inference failed for: r3v13, types: [int] */
    /* JADX WARNING: type inference failed for: r17v2 */
    /* JADX WARNING: type inference failed for: r12v6 */
    /* JADX WARNING: type inference failed for: r12v7 */
    /* JADX WARNING: type inference failed for: r12v8 */
    /* JADX WARNING: type inference failed for: r12v9 */
    /* JADX WARNING: Code restructure failed: missing block: B:111:0x024f, code lost:
        r12 = r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:112:0x024f, code lost:
        r12 = r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x010b, code lost:
        r2 = r4;
        r1 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x013d, code lost:
        r0 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x0159, code lost:
        r0 = r8 + 8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x015b, code lost:
        r1 = r7;
        r2 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x015f, code lost:
        r24 = r7;
        r15 = r8;
        r18 = r9;
        r19 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x01e2, code lost:
        if (r0 == r15) goto L_0x0230;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x020f, code lost:
        if (r0 == r15) goto L_0x0230;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:0x022e, code lost:
        if (r0 == r15) goto L_0x0230;
     */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=null, for r0v5, types: [int, byte] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte[], code=null, for r29v0, types: [byte[]] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r12v2
      assigns: []
      uses: []
      mth insns count: 271
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
    	at jadx.core.ProcessClass.process(ProcessClass.java:35)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Unknown variable types count: 16 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(T r28, byte[] r29, int r30, int r31, com.google.android.gms.internal.vision.zzei r32) throws java.io.IOException {
        /*
            r27 = this;
            r15 = r27
            r14 = r28
            r12 = r29
            r13 = r31
            r11 = r32
            boolean r0 = r15.zzzk
            if (r0 == 0) goto L_0x025b
            sun.misc.Unsafe r9 = zzzc
            r10 = -1
            r16 = 0
            r0 = r30
            r1 = -1
            r2 = 0
        L_0x0017:
            if (r0 >= r13) goto L_0x0252
            int r3 = r0 + 1
            byte r0 = r12[r0]
            if (r0 >= 0) goto L_0x0029
            int r0 = com.google.android.gms.internal.vision.zzeh.zza(r0, r12, r3, r11)
            int r3 = r11.zzro
            r8 = r0
            r17 = r3
            goto L_0x002c
        L_0x0029:
            r17 = r0
            r8 = r3
        L_0x002c:
            int r7 = r17 >>> 3
            r6 = r17 & 7
            if (r7 <= r1) goto L_0x0039
            int r2 = r2 / 3
            int r0 = r15.zzr(r7, r2)
            goto L_0x003d
        L_0x0039:
            int r0 = r15.zzbn(r7)
        L_0x003d:
            r4 = r0
            if (r4 != r10) goto L_0x004b
            r24 = r7
            r2 = r8
            r18 = r9
            r19 = 0
            r26 = -1
            goto L_0x0231
        L_0x004b:
            int[] r0 = r15.zzzd
            int r1 = r4 + 1
            r5 = r0[r1]
            r0 = 267386880(0xff00000, float:2.3665827E-29)
            r0 = r0 & r5
            int r3 = r0 >>> 20
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r1 = (long) r0
            r0 = 17
            r10 = 2
            if (r3 > r0) goto L_0x0167
            r0 = 1
            switch(r3) {
                case 0: goto L_0x014e;
                case 1: goto L_0x013f;
                case 2: goto L_0x012d;
                case 3: goto L_0x012d;
                case 4: goto L_0x011f;
                case 5: goto L_0x010f;
                case 6: goto L_0x00fe;
                case 7: goto L_0x00e8;
                case 8: goto L_0x00d1;
                case 9: goto L_0x00b0;
                case 10: goto L_0x00a3;
                case 11: goto L_0x011f;
                case 12: goto L_0x0094;
                case 13: goto L_0x00fe;
                case 14: goto L_0x010f;
                case 15: goto L_0x0081;
                case 16: goto L_0x0066;
                default: goto L_0x0064;
            }
        L_0x0064:
            goto L_0x01a4
        L_0x0066:
            if (r6 != 0) goto L_0x01a4
            int r6 = com.google.android.gms.internal.vision.zzeh.zzb(r12, r8, r11)
            r19 = r1
            long r0 = r11.zzrp
            long r21 = com.google.android.gms.internal.vision.zzez.zzd(r0)
            r0 = r9
            r2 = r19
            r1 = r28
            r10 = r4
            r4 = r21
            r0.putLong(r1, r2, r4)
            goto L_0x013d
        L_0x0081:
            r2 = r1
            r10 = r4
            if (r6 != 0) goto L_0x015f
            int r0 = com.google.android.gms.internal.vision.zzeh.zza(r12, r8, r11)
            int r1 = r11.zzro
            int r1 = com.google.android.gms.internal.vision.zzez.zzaq(r1)
            r9.putInt(r14, r2, r1)
            goto L_0x015b
        L_0x0094:
            r2 = r1
            r10 = r4
            if (r6 != 0) goto L_0x015f
            int r0 = com.google.android.gms.internal.vision.zzeh.zza(r12, r8, r11)
            int r1 = r11.zzro
            r9.putInt(r14, r2, r1)
            goto L_0x015b
        L_0x00a3:
            r2 = r1
            if (r6 != r10) goto L_0x01a4
            int r0 = com.google.android.gms.internal.vision.zzeh.zze(r12, r8, r11)
            java.lang.Object r1 = r11.zzrq
            r9.putObject(r14, r2, r1)
            goto L_0x010b
        L_0x00b0:
            r2 = r1
            if (r6 != r10) goto L_0x01a4
            com.google.android.gms.internal.vision.zzhw r0 = r15.zzbh(r4)
            int r0 = zza(r0, r12, r8, r13, r11)
            java.lang.Object r1 = r9.getObject(r14, r2)
            if (r1 != 0) goto L_0x00c7
            java.lang.Object r1 = r11.zzrq
            r9.putObject(r14, r2, r1)
            goto L_0x010b
        L_0x00c7:
            java.lang.Object r5 = r11.zzrq
            java.lang.Object r1 = com.google.android.gms.internal.vision.zzga.zza(r1, r5)
            r9.putObject(r14, r2, r1)
            goto L_0x010b
        L_0x00d1:
            r2 = r1
            if (r6 != r10) goto L_0x01a4
            r0 = 536870912(0x20000000, float:1.0842022E-19)
            r0 = r0 & r5
            if (r0 != 0) goto L_0x00de
            int r0 = com.google.android.gms.internal.vision.zzeh.zzc(r12, r8, r11)
            goto L_0x00e2
        L_0x00de:
            int r0 = com.google.android.gms.internal.vision.zzeh.zzd(r12, r8, r11)
        L_0x00e2:
            java.lang.Object r1 = r11.zzrq
            r9.putObject(r14, r2, r1)
            goto L_0x010b
        L_0x00e8:
            r2 = r1
            if (r6 != 0) goto L_0x01a4
            int r1 = com.google.android.gms.internal.vision.zzeh.zzb(r12, r8, r11)
            long r5 = r11.zzrp
            r19 = 0
            int r8 = (r5 > r19 ? 1 : (r5 == r19 ? 0 : -1))
            if (r8 == 0) goto L_0x00f8
            goto L_0x00f9
        L_0x00f8:
            r0 = 0
        L_0x00f9:
            com.google.android.gms.internal.vision.zziu.zza(r14, r2, r0)
            r0 = r1
            goto L_0x010b
        L_0x00fe:
            r2 = r1
            r0 = 5
            if (r6 != r0) goto L_0x01a4
            int r0 = com.google.android.gms.internal.vision.zzeh.zza(r12, r8)
            r9.putInt(r14, r2, r0)
            int r0 = r8 + 4
        L_0x010b:
            r2 = r4
            r1 = r7
            goto L_0x024f
        L_0x010f:
            r2 = r1
            if (r6 != r0) goto L_0x01a4
            long r5 = com.google.android.gms.internal.vision.zzeh.zzb(r12, r8)
            r0 = r9
            r1 = r28
            r10 = r4
            r4 = r5
            r0.putLong(r1, r2, r4)
            goto L_0x0159
        L_0x011f:
            r2 = r1
            r10 = r4
            if (r6 != 0) goto L_0x015f
            int r0 = com.google.android.gms.internal.vision.zzeh.zza(r12, r8, r11)
            int r1 = r11.zzro
            r9.putInt(r14, r2, r1)
            goto L_0x015b
        L_0x012d:
            r2 = r1
            r10 = r4
            if (r6 != 0) goto L_0x015f
            int r6 = com.google.android.gms.internal.vision.zzeh.zzb(r12, r8, r11)
            long r4 = r11.zzrp
            r0 = r9
            r1 = r28
            r0.putLong(r1, r2, r4)
        L_0x013d:
            r0 = r6
            goto L_0x015b
        L_0x013f:
            r2 = r1
            r10 = r4
            r0 = 5
            if (r6 != r0) goto L_0x015f
            float r0 = com.google.android.gms.internal.vision.zzeh.zzd(r12, r8)
            com.google.android.gms.internal.vision.zziu.zza(r14, r2, r0)
            int r0 = r8 + 4
            goto L_0x015b
        L_0x014e:
            r2 = r1
            r10 = r4
            if (r6 != r0) goto L_0x015f
            double r0 = com.google.android.gms.internal.vision.zzeh.zzc(r12, r8)
            com.google.android.gms.internal.vision.zziu.zza(r14, r2, r0)
        L_0x0159:
            int r0 = r8 + 8
        L_0x015b:
            r1 = r7
            r2 = r10
            goto L_0x024f
        L_0x015f:
            r24 = r7
            r15 = r8
            r18 = r9
            r19 = r10
            goto L_0x01ab
        L_0x0167:
            r0 = 27
            if (r3 != r0) goto L_0x01af
            if (r6 != r10) goto L_0x01a4
            java.lang.Object r0 = r9.getObject(r14, r1)
            com.google.android.gms.internal.vision.zzge r0 = (com.google.android.gms.internal.vision.zzge) r0
            boolean r3 = r0.zzch()
            if (r3 != 0) goto L_0x018b
            int r3 = r0.size()
            if (r3 != 0) goto L_0x0182
            r3 = 10
            goto L_0x0184
        L_0x0182:
            int r3 = r3 << 1
        L_0x0184:
            com.google.android.gms.internal.vision.zzge r0 = r0.zzah(r3)
            r9.putObject(r14, r1, r0)
        L_0x018b:
            r5 = r0
            com.google.android.gms.internal.vision.zzhw r0 = r15.zzbh(r4)
            r1 = r17
            r2 = r29
            r3 = r8
            r19 = r4
            r4 = r31
            r6 = r32
            int r0 = zza(r0, r1, r2, r3, r4, r5, r6)
            r1 = r7
            r2 = r19
            goto L_0x024f
        L_0x01a4:
            r19 = r4
            r24 = r7
            r15 = r8
            r18 = r9
        L_0x01ab:
            r26 = -1
            goto L_0x0212
        L_0x01af:
            r19 = r4
            r0 = 49
            if (r3 > r0) goto L_0x01e5
            long r4 = (long) r5
            r0 = r27
            r20 = r1
            r1 = r28
            r2 = r29
            r10 = r3
            r3 = r8
            r22 = r4
            r4 = r31
            r5 = r17
            r30 = r6
            r6 = r7
            r24 = r7
            r7 = r30
            r15 = r8
            r8 = r19
            r18 = r9
            r25 = r10
            r26 = -1
            r9 = r22
            r11 = r25
            r12 = r20
            r14 = r32
            int r0 = r0.zza((T) r1, r2, r3, r4, r5, r6, r7, r8, r9, r11, r12, r14)
            if (r0 != r15) goto L_0x023f
            goto L_0x0230
        L_0x01e5:
            r20 = r1
            r25 = r3
            r30 = r6
            r24 = r7
            r15 = r8
            r18 = r9
            r26 = -1
            r0 = 50
            r9 = r25
            if (r9 != r0) goto L_0x0214
            r7 = r30
            if (r7 != r10) goto L_0x0212
            r0 = r27
            r1 = r28
            r2 = r29
            r3 = r15
            r4 = r31
            r5 = r19
            r6 = r20
            r8 = r32
            int r0 = r0.zza((T) r1, r2, r3, r4, r5, r6, r8)
            if (r0 != r15) goto L_0x023f
            goto L_0x0230
        L_0x0212:
            r2 = r15
            goto L_0x0231
        L_0x0214:
            r7 = r30
            r0 = r27
            r1 = r28
            r2 = r29
            r3 = r15
            r4 = r31
            r8 = r5
            r5 = r17
            r6 = r24
            r10 = r20
            r12 = r19
            r13 = r32
            int r0 = r0.zza((T) r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r12, r13)
            if (r0 != r15) goto L_0x023f
        L_0x0230:
            r2 = r0
        L_0x0231:
            r0 = r17
            r1 = r29
            r3 = r31
            r4 = r28
            r5 = r32
            int r0 = zza(r0, r1, r2, r3, r4, r5)
        L_0x023f:
            r15 = r27
            r14 = r28
            r12 = r29
            r13 = r31
            r11 = r32
            r9 = r18
            r2 = r19
            r1 = r24
        L_0x024f:
            r10 = -1
            goto L_0x0017
        L_0x0252:
            r4 = r13
            if (r0 != r4) goto L_0x0256
            return
        L_0x0256:
            com.google.android.gms.internal.vision.zzgf r0 = com.google.android.gms.internal.vision.zzgf.zzfo()
            throw r0
        L_0x025b:
            r4 = r13
            r5 = 0
            r0 = r27
            r1 = r28
            r2 = r29
            r3 = r30
            r4 = r31
            r6 = r32
            r0.zza((T) r1, r2, r3, r4, r5, r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzhj.zza(java.lang.Object, byte[], int, int, com.google.android.gms.internal.vision.zzei):void");
    }

    public final void zze(T t) {
        int i;
        int i2 = this.zzzn;
        while (true) {
            i = this.zzzo;
            if (i2 >= i) {
                break;
            }
            long zzbk = (long) (zzbk(this.zzzm[i2]) & 1048575);
            Object zzp = zziu.zzp(t, zzbk);
            if (zzp != null) {
                zziu.zza((Object) t, zzbk, this.zzzt.zzm(zzp));
            }
            i2++;
        }
        int length = this.zzzm.length;
        while (i < length) {
            this.zzzq.zzb(t, (long) this.zzzm[i]);
            i++;
        }
        this.zzzr.zze(t);
        if (this.zzzi) {
            this.zzzs.zze((Object) t);
        }
    }

    private final <UT, UB> UB zza(Object obj, int i, UB ub, zzio<UT, UB> zzio) {
        int i2 = this.zzzd[i];
        Object zzp = zziu.zzp(obj, (long) (zzbk(i) & 1048575));
        if (zzp == null) {
            return ub;
        }
        zzgd zzbj = zzbj(i);
        if (zzbj == null) {
            return ub;
        }
        return zza(i, i2, this.zzzt.zzj(zzp), zzbj, ub, zzio);
    }

    private final <K, V, UT, UB> UB zza(int i, int i2, Map<K, V> map, zzgd zzgd, UB ub, zzio<UT, UB> zzio) {
        zzgy zzo = this.zzzt.zzo(zzbi(i));
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            if (!zzgd.zzh(((Integer) entry.getValue()).intValue())) {
                if (ub == null) {
                    ub = zzio.zzhd();
                }
                zzev zzaj = zzeo.zzaj(zzgx.zza(zzo, entry.getKey(), entry.getValue()));
                try {
                    zzgx.zza(zzaj.zzdp(), zzo, entry.getKey(), entry.getValue());
                    zzio.zza(ub, i2, zzaj.zzdo());
                    it.remove();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return ub;
    }

    public final boolean zzr(T t) {
        int i;
        int i2 = 0;
        int i3 = -1;
        int i4 = 0;
        while (true) {
            boolean z = true;
            if (i2 >= this.zzzn) {
                return !this.zzzi || this.zzzs.zzc(t).isInitialized();
            }
            int i5 = this.zzzm[i2];
            int i6 = this.zzzd[i5];
            int zzbk = zzbk(i5);
            if (!this.zzzk) {
                int i7 = this.zzzd[i5 + 2];
                int i8 = i7 & 1048575;
                i = 1 << (i7 >>> 20);
                if (i8 != i3) {
                    i4 = zzzc.getInt(t, (long) i8);
                    i3 = i8;
                }
            } else {
                i = 0;
            }
            if (((268435456 & zzbk) != 0) && !zza(t, i5, i4, i)) {
                return false;
            }
            int i9 = (267386880 & zzbk) >>> 20;
            if (i9 != 9 && i9 != 17) {
                if (i9 != 27) {
                    if (i9 == 60 || i9 == 68) {
                        if (zza(t, i6, i5) && !zza((Object) t, zzbk, zzbh(i5))) {
                            return false;
                        }
                    } else if (i9 != 49) {
                        if (i9 != 50) {
                            continue;
                        } else {
                            Map zzk = this.zzzt.zzk(zziu.zzp(t, (long) (zzbk & 1048575)));
                            if (!zzk.isEmpty()) {
                                if (this.zzzt.zzo(zzbi(i5)).zzyx.zzho() == zzji.MESSAGE) {
                                    zzhw zzhw = null;
                                    Iterator it = zzk.values().iterator();
                                    while (true) {
                                        if (!it.hasNext()) {
                                            break;
                                        }
                                        Object next = it.next();
                                        if (zzhw == null) {
                                            zzhw = zzhs.zzgl().zzf(next.getClass());
                                        }
                                        if (!zzhw.zzr(next)) {
                                            z = false;
                                            break;
                                        }
                                    }
                                }
                            }
                            if (!z) {
                                return false;
                            }
                        }
                    }
                }
                List list = (List) zziu.zzp(t, (long) (zzbk & 1048575));
                if (!list.isEmpty()) {
                    zzhw zzbh = zzbh(i5);
                    int i10 = 0;
                    while (true) {
                        if (i10 >= list.size()) {
                            break;
                        } else if (!zzbh.zzr(list.get(i10))) {
                            z = false;
                            break;
                        } else {
                            i10++;
                        }
                    }
                }
                if (!z) {
                    return false;
                }
            } else if (zza(t, i5, i4, i) && !zza((Object) t, zzbk, zzbh(i5))) {
                return false;
            }
            i2++;
        }
    }

    private static boolean zza(Object obj, int i, zzhw zzhw) {
        return zzhw.zzr(zziu.zzp(obj, (long) (i & 1048575)));
    }

    private static void zza(int i, Object obj, zzjj zzjj) throws IOException {
        if (obj instanceof String) {
            zzjj.zza(i, (String) obj);
        } else {
            zzjj.zza(i, (zzeo) obj);
        }
    }

    private final void zza(Object obj, int i, zzhv zzhv) throws IOException {
        if (zzbm(i)) {
            zziu.zza(obj, (long) (i & 1048575), (Object) zzhv.zzcv());
        } else if (this.zzzj) {
            zziu.zza(obj, (long) (i & 1048575), (Object) zzhv.readString());
        } else {
            zziu.zza(obj, (long) (i & 1048575), (Object) zzhv.zzcw());
        }
    }

    private final int zzbk(int i) {
        return this.zzzd[i + 1];
    }

    private final int zzbl(int i) {
        return this.zzzd[i + 2];
    }

    private static <T> double zzf(T t, long j) {
        return ((Double) zziu.zzp(t, j)).doubleValue();
    }

    private static <T> float zzg(T t, long j) {
        return ((Float) zziu.zzp(t, j)).floatValue();
    }

    private static <T> int zzh(T t, long j) {
        return ((Integer) zziu.zzp(t, j)).intValue();
    }

    private static <T> long zzi(T t, long j) {
        return ((Long) zziu.zzp(t, j)).longValue();
    }

    private static <T> boolean zzj(T t, long j) {
        return ((Boolean) zziu.zzp(t, j)).booleanValue();
    }

    private final boolean zzc(T t, T t2, int i) {
        return zza(t, i) == zza(t2, i);
    }

    private final boolean zza(T t, int i, int i2, int i3) {
        if (this.zzzk) {
            return zza(t, i);
        }
        return (i2 & i3) != 0;
    }

    private final boolean zza(T t, int i) {
        if (this.zzzk) {
            int zzbk = zzbk(i);
            long j = (long) (zzbk & 1048575);
            switch ((zzbk & 267386880) >>> 20) {
                case 0:
                    return zziu.zzo(t, j) != 0.0d;
                case 1:
                    return zziu.zzn(t, j) != 0.0f;
                case 2:
                    return zziu.zzl(t, j) != 0;
                case 3:
                    return zziu.zzl(t, j) != 0;
                case 4:
                    return zziu.zzk(t, j) != 0;
                case 5:
                    return zziu.zzl(t, j) != 0;
                case 6:
                    return zziu.zzk(t, j) != 0;
                case 7:
                    return zziu.zzm(t, j);
                case 8:
                    Object zzp = zziu.zzp(t, j);
                    if (zzp instanceof String) {
                        return !((String) zzp).isEmpty();
                    }
                    if (zzp instanceof zzeo) {
                        return !zzeo.zzrx.equals(zzp);
                    }
                    throw new IllegalArgumentException();
                case 9:
                    return zziu.zzp(t, j) != null;
                case 10:
                    return !zzeo.zzrx.equals(zziu.zzp(t, j));
                case 11:
                    return zziu.zzk(t, j) != 0;
                case 12:
                    return zziu.zzk(t, j) != 0;
                case 13:
                    return zziu.zzk(t, j) != 0;
                case 14:
                    return zziu.zzl(t, j) != 0;
                case 15:
                    return zziu.zzk(t, j) != 0;
                case 16:
                    return zziu.zzl(t, j) != 0;
                case 17:
                    return zziu.zzp(t, j) != null;
                default:
                    throw new IllegalArgumentException();
            }
        } else {
            int zzbl = zzbl(i);
            return (zziu.zzk(t, (long) (zzbl & 1048575)) & (1 << (zzbl >>> 20))) != 0;
        }
    }

    private final void zzb(T t, int i) {
        if (!this.zzzk) {
            int zzbl = zzbl(i);
            long j = (long) (zzbl & 1048575);
            zziu.zzb((Object) t, j, zziu.zzk(t, j) | (1 << (zzbl >>> 20)));
        }
    }

    private final boolean zza(T t, int i, int i2) {
        return zziu.zzk(t, (long) (zzbl(i2) & 1048575)) == i;
    }

    private final void zzb(T t, int i, int i2) {
        zziu.zzb((Object) t, (long) (zzbl(i2) & 1048575), i);
    }

    private final int zzbn(int i) {
        if (i < this.zzzf || i > this.zzzg) {
            return -1;
        }
        return zzs(i, 0);
    }

    private final int zzr(int i, int i2) {
        if (i < this.zzzf || i > this.zzzg) {
            return -1;
        }
        return zzs(i, i2);
    }

    private final int zzs(int i, int i2) {
        int length = (this.zzzd.length / 3) - 1;
        while (i2 <= length) {
            int i3 = (length + i2) >>> 1;
            int i4 = i3 * 3;
            int i5 = this.zzzd[i4];
            if (i == i5) {
                return i4;
            }
            if (i < i5) {
                length = i3 - 1;
            } else {
                i2 = i3 + 1;
            }
        }
        return -1;
    }
}
