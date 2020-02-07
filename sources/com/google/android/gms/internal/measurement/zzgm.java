package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzey.zzd;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.spongycastle.asn1.cmp.PKIFailureInfo;
import sun.misc.Unsafe;

final class zzgm<T> implements zzgx<T> {
    private static final int[] zzakh = new int[0];
    private static final Unsafe zzaki = zzhv.zzwv();
    private final int[] zzakj;
    private final Object[] zzakk;
    private final int zzakl;
    private final int zzakm;
    private final zzgi zzakn;
    private final boolean zzako;
    private final boolean zzakp;
    private final boolean zzakq;
    private final boolean zzakr;
    private final int[] zzaks;
    private final int zzakt;
    private final int zzaku;
    private final zzgq zzakv;
    private final zzfs zzakw;
    private final zzhp<?, ?> zzakx;
    private final zzen<?> zzaky;
    private final zzgb zzakz;

    private zzgm(int[] iArr, Object[] objArr, int i, int i2, zzgi zzgi, boolean z, boolean z2, int[] iArr2, int i3, int i4, zzgq zzgq, zzfs zzfs, zzhp<?, ?> zzhp, zzen<?> zzen, zzgb zzgb) {
        this.zzakj = iArr;
        this.zzakk = objArr;
        this.zzakl = i;
        this.zzakm = i2;
        this.zzakp = zzgi instanceof zzey;
        this.zzakq = z;
        this.zzako = zzen != null && zzen.zze(zzgi);
        this.zzakr = false;
        this.zzaks = iArr2;
        this.zzakt = i3;
        this.zzaku = i4;
        this.zzakv = zzgq;
        this.zzakw = zzfs;
        this.zzakx = zzhp;
        this.zzaky = zzen;
        this.zzakn = zzgi;
        this.zzakz = zzgb;
    }

    private static boolean zzcc(int i) {
        return (i & PKIFailureInfo.duplicateCertReq) != 0;
    }

    static <T> zzgm<T> zza(Class<T> cls, zzgg zzgg, zzgq zzgq, zzfs zzfs, zzhp<?, ?> zzhp, zzen<?> zzen, zzgb zzgb) {
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
        zzgg zzgg2 = zzgg;
        if (zzgg2 instanceof zzgv) {
            zzgv zzgv = (zzgv) zzgg2;
            char c3 = 0;
            boolean z = zzgv.zzvr() == zzd.zzaim;
            String zzvz = zzgv.zzvz();
            int length = zzvz.length();
            char charAt15 = zzvz.charAt(0);
            if (charAt15 >= 55296) {
                char c4 = charAt15 & 8191;
                int i33 = 1;
                int i34 = 13;
                while (true) {
                    i = i33 + 1;
                    charAt14 = zzvz.charAt(i33);
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
            char charAt16 = zzvz.charAt(i);
            if (charAt16 >= 55296) {
                char c5 = charAt16 & 8191;
                int i36 = 13;
                while (true) {
                    i2 = i35 + 1;
                    charAt13 = zzvz.charAt(i35);
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
                iArr = zzakh;
                c = 0;
                i7 = 0;
                i6 = 0;
                i5 = 0;
                i4 = 0;
                i3 = 0;
            } else {
                int i37 = i2 + 1;
                int charAt17 = zzvz.charAt(i2);
                if (charAt17 >= 55296) {
                    int i38 = charAt17 & 8191;
                    int i39 = 13;
                    while (true) {
                        i24 = i37 + 1;
                        charAt12 = zzvz.charAt(i37);
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
                char charAt18 = zzvz.charAt(i24);
                if (charAt18 >= 55296) {
                    char c6 = charAt18 & 8191;
                    int i41 = 13;
                    while (true) {
                        i25 = i40 + 1;
                        charAt11 = zzvz.charAt(i40);
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
                int charAt19 = zzvz.charAt(i25);
                if (charAt19 >= 55296) {
                    int i43 = charAt19 & 8191;
                    int i44 = 13;
                    while (true) {
                        i26 = i42 + 1;
                        charAt10 = zzvz.charAt(i42);
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
                i5 = zzvz.charAt(i26);
                if (i5 >= 55296) {
                    int i46 = i5 & 8191;
                    int i47 = 13;
                    while (true) {
                        i27 = i45 + 1;
                        charAt9 = zzvz.charAt(i45);
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
                i4 = zzvz.charAt(i27);
                if (i4 >= 55296) {
                    int i49 = i4 & 8191;
                    int i50 = 13;
                    while (true) {
                        i32 = i48 + 1;
                        charAt8 = zzvz.charAt(i48);
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
                c = zzvz.charAt(i48);
                if (c >= 55296) {
                    char c7 = c & 8191;
                    int i52 = 13;
                    while (true) {
                        i31 = i51 + 1;
                        charAt7 = zzvz.charAt(i51);
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
                char charAt20 = zzvz.charAt(i51);
                if (charAt20 >= 55296) {
                    int i54 = 13;
                    int i55 = i53;
                    int i56 = charAt20 & 8191;
                    int i57 = i55;
                    while (true) {
                        i30 = i57 + 1;
                        charAt6 = zzvz.charAt(i57);
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
                c3 = zzvz.charAt(i28);
                if (c3 >= 55296) {
                    int i59 = 13;
                    int i60 = i58;
                    int i61 = c3 & 8191;
                    int i62 = i60;
                    while (true) {
                        i29 = i62 + 1;
                        charAt5 = zzvz.charAt(i62);
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
            Unsafe unsafe = zzaki;
            Object[] zzwa = zzgv.zzwa();
            Class cls3 = zzgv.zzvt().getClass();
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
                int charAt21 = zzvz.charAt(i2);
                char c8 = 55296;
                if (charAt21 >= 55296) {
                    int i71 = 13;
                    int i72 = i70;
                    int i73 = charAt21 & 8191;
                    int i74 = i72;
                    while (true) {
                        i23 = i74 + 1;
                        charAt4 = zzvz.charAt(i74);
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
                char charAt22 = zzvz.charAt(i8);
                int i76 = length;
                char c9 = 55296;
                if (charAt22 >= 55296) {
                    int i77 = 13;
                    int i78 = i75;
                    int i79 = charAt22 & 8191;
                    int i80 = i78;
                    while (true) {
                        i22 = i80 + 1;
                        charAt3 = zzvz.charAt(i80);
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
                    char charAt23 = zzvz.charAt(i9);
                    char c11 = 55296;
                    if (charAt23 >= 55296) {
                        char c12 = charAt23 & 8191;
                        int i85 = 13;
                        while (true) {
                            i21 = i84 + 1;
                            charAt2 = zzvz.charAt(i84);
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
                        objArr[((i69 / 3) << 1) + 1] = zzwa[i64];
                        i64 = i88;
                    } else {
                        if (i86 == 12 && (charAt15 & 1) == 1) {
                            int i89 = i64 + 1;
                            objArr[((i69 / 3) << 1) + 1] = zzwa[i64];
                            i64 = i89;
                        }
                        c2 = 1;
                    }
                    int i90 = charAt23 << c2;
                    Object obj = zzwa[i90];
                    if (obj instanceof Field) {
                        field2 = (Field) obj;
                    } else {
                        field2 = zza(cls3, (String) obj);
                        zzwa[i90] = field2;
                    }
                    int i91 = i7;
                    int objectFieldOffset = (int) unsafe.objectFieldOffset(field2);
                    int i92 = i90 + 1;
                    Object obj2 = zzwa[i92];
                    int i93 = objectFieldOffset;
                    if (obj2 instanceof Field) {
                        field3 = (Field) obj2;
                    } else {
                        field3 = zza(cls3, (String) obj2);
                        zzwa[i92] = field3;
                    }
                    str = zzvz;
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
                    Field zza = zza(cls3, (String) zzwa[i64]);
                    i12 = i5;
                    if (c10 == 9 || c10 == 17) {
                        i10 = i94;
                        objArr[((i69 / 3) << 1) + 1] = zza.getType();
                    } else {
                        if (c10 == 27 || c10 == '1') {
                            i10 = i94;
                            i20 = i95 + 1;
                            objArr[((i69 / 3) << 1) + 1] = zzwa[i95];
                        } else if (c10 == 12 || c10 == 30 || c10 == ',') {
                            i10 = i94;
                            if ((charAt15 & 1) == 1) {
                                i20 = i95 + 1;
                                objArr[((i69 / 3) << 1) + 1] = zzwa[i95];
                            }
                        } else if (c10 == '2') {
                            int i96 = i66 + 1;
                            iArr[i66] = i69;
                            int i97 = (i69 / 3) << 1;
                            int i98 = i95 + 1;
                            objArr[i97] = zzwa[i95];
                            if ((charAt22 & 2048) != 0) {
                                i95 = i98 + 1;
                                objArr[i97 + 1] = zzwa[i98];
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
                            str = zzvz;
                            cls2 = cls3;
                            i11 = i95;
                            i17 = i9;
                            i16 = 0;
                            i15 = 0;
                        } else {
                            i17 = i9 + 1;
                            char charAt24 = zzvz.charAt(i9);
                            if (charAt24 >= 55296) {
                                char c13 = charAt24 & 8191;
                                int i99 = 13;
                                while (true) {
                                    i19 = i17 + 1;
                                    charAt = zzvz.charAt(i17);
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
                            Object obj3 = zzwa[i100];
                            str = zzvz;
                            if (obj3 instanceof Field) {
                                field = (Field) obj3;
                            } else {
                                field = zza(cls3, (String) obj3);
                                zzwa[i100] = field;
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
                    str = zzvz;
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
                zzvz = str;
            }
            boolean z3 = z;
            zzgm zzgm = new zzgm(iArr2, objArr, i7, i5, zzgv.zzvt(), z, false, iArr, c3, i65, zzgq, zzfs, zzhp, zzen, zzgb);
            return zzgm;
        }
        int zzvr = ((zzhm) zzgg2).zzvr();
        int i104 = zzd.zzaim;
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
        return this.zzakv.newInstance(this.zzakn);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x006a, code lost:
        if (com.google.android.gms.internal.measurement.zzgz.zzd(com.google.android.gms.internal.measurement.zzhv.zzp(r10, r6), com.google.android.gms.internal.measurement.zzhv.zzp(r11, r6)) != false) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x007e, code lost:
        if (com.google.android.gms.internal.measurement.zzhv.zzl(r10, r6) == com.google.android.gms.internal.measurement.zzhv.zzl(r11, r6)) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0090, code lost:
        if (com.google.android.gms.internal.measurement.zzhv.zzk(r10, r6) == com.google.android.gms.internal.measurement.zzhv.zzk(r11, r6)) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x00a4, code lost:
        if (com.google.android.gms.internal.measurement.zzhv.zzl(r10, r6) == com.google.android.gms.internal.measurement.zzhv.zzl(r11, r6)) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00b6, code lost:
        if (com.google.android.gms.internal.measurement.zzhv.zzk(r10, r6) == com.google.android.gms.internal.measurement.zzhv.zzk(r11, r6)) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00c8, code lost:
        if (com.google.android.gms.internal.measurement.zzhv.zzk(r10, r6) == com.google.android.gms.internal.measurement.zzhv.zzk(r11, r6)) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00da, code lost:
        if (com.google.android.gms.internal.measurement.zzhv.zzk(r10, r6) == com.google.android.gms.internal.measurement.zzhv.zzk(r11, r6)) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00f0, code lost:
        if (com.google.android.gms.internal.measurement.zzgz.zzd(com.google.android.gms.internal.measurement.zzhv.zzp(r10, r6), com.google.android.gms.internal.measurement.zzhv.zzp(r11, r6)) != false) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0106, code lost:
        if (com.google.android.gms.internal.measurement.zzgz.zzd(com.google.android.gms.internal.measurement.zzhv.zzp(r10, r6), com.google.android.gms.internal.measurement.zzhv.zzp(r11, r6)) != false) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x011c, code lost:
        if (com.google.android.gms.internal.measurement.zzgz.zzd(com.google.android.gms.internal.measurement.zzhv.zzp(r10, r6), com.google.android.gms.internal.measurement.zzhv.zzp(r11, r6)) != false) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x012e, code lost:
        if (com.google.android.gms.internal.measurement.zzhv.zzm(r10, r6) == com.google.android.gms.internal.measurement.zzhv.zzm(r11, r6)) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x0140, code lost:
        if (com.google.android.gms.internal.measurement.zzhv.zzk(r10, r6) == com.google.android.gms.internal.measurement.zzhv.zzk(r11, r6)) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0154, code lost:
        if (com.google.android.gms.internal.measurement.zzhv.zzl(r10, r6) == com.google.android.gms.internal.measurement.zzhv.zzl(r11, r6)) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x0165, code lost:
        if (com.google.android.gms.internal.measurement.zzhv.zzk(r10, r6) == com.google.android.gms.internal.measurement.zzhv.zzk(r11, r6)) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x0178, code lost:
        if (com.google.android.gms.internal.measurement.zzhv.zzl(r10, r6) == com.google.android.gms.internal.measurement.zzhv.zzl(r11, r6)) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x018b, code lost:
        if (com.google.android.gms.internal.measurement.zzhv.zzl(r10, r6) == com.google.android.gms.internal.measurement.zzhv.zzl(r11, r6)) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x01a4, code lost:
        if (java.lang.Float.floatToIntBits(com.google.android.gms.internal.measurement.zzhv.zzn(r10, r6)) == java.lang.Float.floatToIntBits(com.google.android.gms.internal.measurement.zzhv.zzn(r11, r6))) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x01bf, code lost:
        if (java.lang.Double.doubleToLongBits(com.google.android.gms.internal.measurement.zzhv.zzo(r10, r6)) == java.lang.Double.doubleToLongBits(com.google.android.gms.internal.measurement.zzhv.zzo(r11, r6))) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x01c1, code lost:
        r3 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0038, code lost:
        if (com.google.android.gms.internal.measurement.zzgz.zzd(com.google.android.gms.internal.measurement.zzhv.zzp(r10, r6), com.google.android.gms.internal.measurement.zzhv.zzp(r11, r6)) != false) goto L_0x01c2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean equals(T r10, T r11) {
        /*
            r9 = this;
            int[] r0 = r9.zzakj
            int r0 = r0.length
            r1 = 0
            r2 = 0
        L_0x0005:
            r3 = 1
            if (r2 >= r0) goto L_0x01c9
            int r4 = r9.zzca(r2)
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
            int r4 = r9.zzcb(r2)
            r4 = r4 & r5
            long r4 = (long) r4
            int r8 = com.google.android.gms.internal.measurement.zzhv.zzk(r10, r4)
            int r4 = com.google.android.gms.internal.measurement.zzhv.zzk(r11, r4)
            if (r8 != r4) goto L_0x01c1
            java.lang.Object r4 = com.google.android.gms.internal.measurement.zzhv.zzp(r10, r6)
            java.lang.Object r5 = com.google.android.gms.internal.measurement.zzhv.zzp(r11, r6)
            boolean r4 = com.google.android.gms.internal.measurement.zzgz.zzd(r4, r5)
            if (r4 != 0) goto L_0x01c2
            goto L_0x01c1
        L_0x003c:
            java.lang.Object r3 = com.google.android.gms.internal.measurement.zzhv.zzp(r10, r6)
            java.lang.Object r4 = com.google.android.gms.internal.measurement.zzhv.zzp(r11, r6)
            boolean r3 = com.google.android.gms.internal.measurement.zzgz.zzd(r3, r4)
            goto L_0x01c2
        L_0x004a:
            java.lang.Object r3 = com.google.android.gms.internal.measurement.zzhv.zzp(r10, r6)
            java.lang.Object r4 = com.google.android.gms.internal.measurement.zzhv.zzp(r11, r6)
            boolean r3 = com.google.android.gms.internal.measurement.zzgz.zzd(r3, r4)
            goto L_0x01c2
        L_0x0058:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01c1
            java.lang.Object r4 = com.google.android.gms.internal.measurement.zzhv.zzp(r10, r6)
            java.lang.Object r5 = com.google.android.gms.internal.measurement.zzhv.zzp(r11, r6)
            boolean r4 = com.google.android.gms.internal.measurement.zzgz.zzd(r4, r5)
            if (r4 != 0) goto L_0x01c2
            goto L_0x01c1
        L_0x006e:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01c1
            long r4 = com.google.android.gms.internal.measurement.zzhv.zzl(r10, r6)
            long r6 = com.google.android.gms.internal.measurement.zzhv.zzl(r11, r6)
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 == 0) goto L_0x01c2
            goto L_0x01c1
        L_0x0082:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01c1
            int r4 = com.google.android.gms.internal.measurement.zzhv.zzk(r10, r6)
            int r5 = com.google.android.gms.internal.measurement.zzhv.zzk(r11, r6)
            if (r4 == r5) goto L_0x01c2
            goto L_0x01c1
        L_0x0094:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01c1
            long r4 = com.google.android.gms.internal.measurement.zzhv.zzl(r10, r6)
            long r6 = com.google.android.gms.internal.measurement.zzhv.zzl(r11, r6)
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 == 0) goto L_0x01c2
            goto L_0x01c1
        L_0x00a8:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01c1
            int r4 = com.google.android.gms.internal.measurement.zzhv.zzk(r10, r6)
            int r5 = com.google.android.gms.internal.measurement.zzhv.zzk(r11, r6)
            if (r4 == r5) goto L_0x01c2
            goto L_0x01c1
        L_0x00ba:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01c1
            int r4 = com.google.android.gms.internal.measurement.zzhv.zzk(r10, r6)
            int r5 = com.google.android.gms.internal.measurement.zzhv.zzk(r11, r6)
            if (r4 == r5) goto L_0x01c2
            goto L_0x01c1
        L_0x00cc:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01c1
            int r4 = com.google.android.gms.internal.measurement.zzhv.zzk(r10, r6)
            int r5 = com.google.android.gms.internal.measurement.zzhv.zzk(r11, r6)
            if (r4 == r5) goto L_0x01c2
            goto L_0x01c1
        L_0x00de:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01c1
            java.lang.Object r4 = com.google.android.gms.internal.measurement.zzhv.zzp(r10, r6)
            java.lang.Object r5 = com.google.android.gms.internal.measurement.zzhv.zzp(r11, r6)
            boolean r4 = com.google.android.gms.internal.measurement.zzgz.zzd(r4, r5)
            if (r4 != 0) goto L_0x01c2
            goto L_0x01c1
        L_0x00f4:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01c1
            java.lang.Object r4 = com.google.android.gms.internal.measurement.zzhv.zzp(r10, r6)
            java.lang.Object r5 = com.google.android.gms.internal.measurement.zzhv.zzp(r11, r6)
            boolean r4 = com.google.android.gms.internal.measurement.zzgz.zzd(r4, r5)
            if (r4 != 0) goto L_0x01c2
            goto L_0x01c1
        L_0x010a:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01c1
            java.lang.Object r4 = com.google.android.gms.internal.measurement.zzhv.zzp(r10, r6)
            java.lang.Object r5 = com.google.android.gms.internal.measurement.zzhv.zzp(r11, r6)
            boolean r4 = com.google.android.gms.internal.measurement.zzgz.zzd(r4, r5)
            if (r4 != 0) goto L_0x01c2
            goto L_0x01c1
        L_0x0120:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01c1
            boolean r4 = com.google.android.gms.internal.measurement.zzhv.zzm(r10, r6)
            boolean r5 = com.google.android.gms.internal.measurement.zzhv.zzm(r11, r6)
            if (r4 == r5) goto L_0x01c2
            goto L_0x01c1
        L_0x0132:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01c1
            int r4 = com.google.android.gms.internal.measurement.zzhv.zzk(r10, r6)
            int r5 = com.google.android.gms.internal.measurement.zzhv.zzk(r11, r6)
            if (r4 == r5) goto L_0x01c2
            goto L_0x01c1
        L_0x0144:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01c1
            long r4 = com.google.android.gms.internal.measurement.zzhv.zzl(r10, r6)
            long r6 = com.google.android.gms.internal.measurement.zzhv.zzl(r11, r6)
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 == 0) goto L_0x01c2
            goto L_0x01c1
        L_0x0157:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01c1
            int r4 = com.google.android.gms.internal.measurement.zzhv.zzk(r10, r6)
            int r5 = com.google.android.gms.internal.measurement.zzhv.zzk(r11, r6)
            if (r4 == r5) goto L_0x01c2
            goto L_0x01c1
        L_0x0168:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01c1
            long r4 = com.google.android.gms.internal.measurement.zzhv.zzl(r10, r6)
            long r6 = com.google.android.gms.internal.measurement.zzhv.zzl(r11, r6)
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 == 0) goto L_0x01c2
            goto L_0x01c1
        L_0x017b:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01c1
            long r4 = com.google.android.gms.internal.measurement.zzhv.zzl(r10, r6)
            long r6 = com.google.android.gms.internal.measurement.zzhv.zzl(r11, r6)
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 == 0) goto L_0x01c2
            goto L_0x01c1
        L_0x018e:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01c1
            float r4 = com.google.android.gms.internal.measurement.zzhv.zzn(r10, r6)
            int r4 = java.lang.Float.floatToIntBits(r4)
            float r5 = com.google.android.gms.internal.measurement.zzhv.zzn(r11, r6)
            int r5 = java.lang.Float.floatToIntBits(r5)
            if (r4 == r5) goto L_0x01c2
            goto L_0x01c1
        L_0x01a7:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01c1
            double r4 = com.google.android.gms.internal.measurement.zzhv.zzo(r10, r6)
            long r4 = java.lang.Double.doubleToLongBits(r4)
            double r6 = com.google.android.gms.internal.measurement.zzhv.zzo(r11, r6)
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
            com.google.android.gms.internal.measurement.zzhp<?, ?> r0 = r9.zzakx
            java.lang.Object r0 = r0.zzx(r10)
            com.google.android.gms.internal.measurement.zzhp<?, ?> r2 = r9.zzakx
            java.lang.Object r2 = r2.zzx(r11)
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L_0x01dc
            return r1
        L_0x01dc:
            boolean r0 = r9.zzako
            if (r0 == 0) goto L_0x01f1
            com.google.android.gms.internal.measurement.zzen<?> r0 = r9.zzaky
            com.google.android.gms.internal.measurement.zzeo r10 = r0.zzh(r10)
            com.google.android.gms.internal.measurement.zzen<?> r0 = r9.zzaky
            com.google.android.gms.internal.measurement.zzeo r11 = r0.zzh(r11)
            boolean r10 = r10.equals(r11)
            return r10
        L_0x01f1:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzgm.equals(java.lang.Object, java.lang.Object):boolean");
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
            int[] r0 = r8.zzakj
            int r0 = r0.length
            r1 = 0
            r2 = 0
        L_0x0005:
            if (r1 >= r0) goto L_0x022c
            int r3 = r8.zzca(r1)
            int[] r4 = r8.zzakj
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
            java.lang.Object r3 = com.google.android.gms.internal.measurement.zzhv.zzp(r9, r5)
            int r2 = r2 * 53
            int r3 = r3.hashCode()
            goto L_0x0227
        L_0x0032:
            boolean r3 = r8.zza((T) r9, r4, r1)
            if (r3 == 0) goto L_0x0228
            int r2 = r2 * 53
            long r3 = zzi(r9, r5)
            int r3 = com.google.android.gms.internal.measurement.zzez.zzbx(r3)
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
            int r3 = com.google.android.gms.internal.measurement.zzez.zzbx(r3)
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
            java.lang.Object r3 = com.google.android.gms.internal.measurement.zzhv.zzp(r9, r5)
            int r3 = r3.hashCode()
            goto L_0x0227
        L_0x00a0:
            boolean r3 = r8.zza((T) r9, r4, r1)
            if (r3 == 0) goto L_0x0228
            java.lang.Object r3 = com.google.android.gms.internal.measurement.zzhv.zzp(r9, r5)
            int r2 = r2 * 53
            int r3 = r3.hashCode()
            goto L_0x0227
        L_0x00b2:
            boolean r3 = r8.zza((T) r9, r4, r1)
            if (r3 == 0) goto L_0x0228
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.android.gms.internal.measurement.zzhv.zzp(r9, r5)
            java.lang.String r3 = (java.lang.String) r3
            int r3 = r3.hashCode()
            goto L_0x0227
        L_0x00c6:
            boolean r3 = r8.zza((T) r9, r4, r1)
            if (r3 == 0) goto L_0x0228
            int r2 = r2 * 53
            boolean r3 = zzj(r9, r5)
            int r3 = com.google.android.gms.internal.measurement.zzez.zzs(r3)
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
            int r3 = com.google.android.gms.internal.measurement.zzez.zzbx(r3)
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
            int r3 = com.google.android.gms.internal.measurement.zzez.zzbx(r3)
            goto L_0x0227
        L_0x0118:
            boolean r3 = r8.zza((T) r9, r4, r1)
            if (r3 == 0) goto L_0x0228
            int r2 = r2 * 53
            long r3 = zzi(r9, r5)
            int r3 = com.google.android.gms.internal.measurement.zzez.zzbx(r3)
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
            int r3 = com.google.android.gms.internal.measurement.zzez.zzbx(r3)
            goto L_0x0227
        L_0x0152:
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.android.gms.internal.measurement.zzhv.zzp(r9, r5)
            int r3 = r3.hashCode()
            goto L_0x0227
        L_0x015e:
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.android.gms.internal.measurement.zzhv.zzp(r9, r5)
            int r3 = r3.hashCode()
            goto L_0x0227
        L_0x016a:
            java.lang.Object r3 = com.google.android.gms.internal.measurement.zzhv.zzp(r9, r5)
            if (r3 == 0) goto L_0x01c3
            int r7 = r3.hashCode()
            goto L_0x01c3
        L_0x0175:
            int r2 = r2 * 53
            long r3 = com.google.android.gms.internal.measurement.zzhv.zzl(r9, r5)
            int r3 = com.google.android.gms.internal.measurement.zzez.zzbx(r3)
            goto L_0x0227
        L_0x0181:
            int r2 = r2 * 53
            int r3 = com.google.android.gms.internal.measurement.zzhv.zzk(r9, r5)
            goto L_0x0227
        L_0x0189:
            int r2 = r2 * 53
            long r3 = com.google.android.gms.internal.measurement.zzhv.zzl(r9, r5)
            int r3 = com.google.android.gms.internal.measurement.zzez.zzbx(r3)
            goto L_0x0227
        L_0x0195:
            int r2 = r2 * 53
            int r3 = com.google.android.gms.internal.measurement.zzhv.zzk(r9, r5)
            goto L_0x0227
        L_0x019d:
            int r2 = r2 * 53
            int r3 = com.google.android.gms.internal.measurement.zzhv.zzk(r9, r5)
            goto L_0x0227
        L_0x01a5:
            int r2 = r2 * 53
            int r3 = com.google.android.gms.internal.measurement.zzhv.zzk(r9, r5)
            goto L_0x0227
        L_0x01ad:
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.android.gms.internal.measurement.zzhv.zzp(r9, r5)
            int r3 = r3.hashCode()
            goto L_0x0227
        L_0x01b9:
            java.lang.Object r3 = com.google.android.gms.internal.measurement.zzhv.zzp(r9, r5)
            if (r3 == 0) goto L_0x01c3
            int r7 = r3.hashCode()
        L_0x01c3:
            int r2 = r2 * 53
            int r2 = r2 + r7
            goto L_0x0228
        L_0x01c7:
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.android.gms.internal.measurement.zzhv.zzp(r9, r5)
            java.lang.String r3 = (java.lang.String) r3
            int r3 = r3.hashCode()
            goto L_0x0227
        L_0x01d4:
            int r2 = r2 * 53
            boolean r3 = com.google.android.gms.internal.measurement.zzhv.zzm(r9, r5)
            int r3 = com.google.android.gms.internal.measurement.zzez.zzs(r3)
            goto L_0x0227
        L_0x01df:
            int r2 = r2 * 53
            int r3 = com.google.android.gms.internal.measurement.zzhv.zzk(r9, r5)
            goto L_0x0227
        L_0x01e6:
            int r2 = r2 * 53
            long r3 = com.google.android.gms.internal.measurement.zzhv.zzl(r9, r5)
            int r3 = com.google.android.gms.internal.measurement.zzez.zzbx(r3)
            goto L_0x0227
        L_0x01f1:
            int r2 = r2 * 53
            int r3 = com.google.android.gms.internal.measurement.zzhv.zzk(r9, r5)
            goto L_0x0227
        L_0x01f8:
            int r2 = r2 * 53
            long r3 = com.google.android.gms.internal.measurement.zzhv.zzl(r9, r5)
            int r3 = com.google.android.gms.internal.measurement.zzez.zzbx(r3)
            goto L_0x0227
        L_0x0203:
            int r2 = r2 * 53
            long r3 = com.google.android.gms.internal.measurement.zzhv.zzl(r9, r5)
            int r3 = com.google.android.gms.internal.measurement.zzez.zzbx(r3)
            goto L_0x0227
        L_0x020e:
            int r2 = r2 * 53
            float r3 = com.google.android.gms.internal.measurement.zzhv.zzn(r9, r5)
            int r3 = java.lang.Float.floatToIntBits(r3)
            goto L_0x0227
        L_0x0219:
            int r2 = r2 * 53
            double r3 = com.google.android.gms.internal.measurement.zzhv.zzo(r9, r5)
            long r3 = java.lang.Double.doubleToLongBits(r3)
            int r3 = com.google.android.gms.internal.measurement.zzez.zzbx(r3)
        L_0x0227:
            int r2 = r2 + r3
        L_0x0228:
            int r1 = r1 + 3
            goto L_0x0005
        L_0x022c:
            int r2 = r2 * 53
            com.google.android.gms.internal.measurement.zzhp<?, ?> r0 = r8.zzakx
            java.lang.Object r0 = r0.zzx(r9)
            int r0 = r0.hashCode()
            int r2 = r2 + r0
            boolean r0 = r8.zzako
            if (r0 == 0) goto L_0x024a
            int r2 = r2 * 53
            com.google.android.gms.internal.measurement.zzen<?> r0 = r8.zzaky
            com.google.android.gms.internal.measurement.zzeo r9 = r0.zzh(r9)
            int r9 = r9.hashCode()
            int r2 = r2 + r9
        L_0x024a:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzgm.hashCode(java.lang.Object):int");
    }

    public final void zzc(T t, T t2) {
        if (t2 != null) {
            for (int i = 0; i < this.zzakj.length; i += 3) {
                int zzca = zzca(i);
                long j = (long) (1048575 & zzca);
                int i2 = this.zzakj[i];
                switch ((zzca & 267386880) >>> 20) {
                    case 0:
                        if (!zza(t2, i)) {
                            break;
                        } else {
                            zzhv.zza((Object) t, j, zzhv.zzo(t2, j));
                            zzb(t, i);
                            break;
                        }
                    case 1:
                        if (!zza(t2, i)) {
                            break;
                        } else {
                            zzhv.zza((Object) t, j, zzhv.zzn(t2, j));
                            zzb(t, i);
                            break;
                        }
                    case 2:
                        if (!zza(t2, i)) {
                            break;
                        } else {
                            zzhv.zza((Object) t, j, zzhv.zzl(t2, j));
                            zzb(t, i);
                            break;
                        }
                    case 3:
                        if (!zza(t2, i)) {
                            break;
                        } else {
                            zzhv.zza((Object) t, j, zzhv.zzl(t2, j));
                            zzb(t, i);
                            break;
                        }
                    case 4:
                        if (!zza(t2, i)) {
                            break;
                        } else {
                            zzhv.zzb((Object) t, j, zzhv.zzk(t2, j));
                            zzb(t, i);
                            break;
                        }
                    case 5:
                        if (!zza(t2, i)) {
                            break;
                        } else {
                            zzhv.zza((Object) t, j, zzhv.zzl(t2, j));
                            zzb(t, i);
                            break;
                        }
                    case 6:
                        if (!zza(t2, i)) {
                            break;
                        } else {
                            zzhv.zzb((Object) t, j, zzhv.zzk(t2, j));
                            zzb(t, i);
                            break;
                        }
                    case 7:
                        if (!zza(t2, i)) {
                            break;
                        } else {
                            zzhv.zza((Object) t, j, zzhv.zzm(t2, j));
                            zzb(t, i);
                            break;
                        }
                    case 8:
                        if (!zza(t2, i)) {
                            break;
                        } else {
                            zzhv.zza((Object) t, j, zzhv.zzp(t2, j));
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
                            zzhv.zza((Object) t, j, zzhv.zzp(t2, j));
                            zzb(t, i);
                            break;
                        }
                    case 11:
                        if (!zza(t2, i)) {
                            break;
                        } else {
                            zzhv.zzb((Object) t, j, zzhv.zzk(t2, j));
                            zzb(t, i);
                            break;
                        }
                    case 12:
                        if (!zza(t2, i)) {
                            break;
                        } else {
                            zzhv.zzb((Object) t, j, zzhv.zzk(t2, j));
                            zzb(t, i);
                            break;
                        }
                    case 13:
                        if (!zza(t2, i)) {
                            break;
                        } else {
                            zzhv.zzb((Object) t, j, zzhv.zzk(t2, j));
                            zzb(t, i);
                            break;
                        }
                    case 14:
                        if (!zza(t2, i)) {
                            break;
                        } else {
                            zzhv.zza((Object) t, j, zzhv.zzl(t2, j));
                            zzb(t, i);
                            break;
                        }
                    case 15:
                        if (!zza(t2, i)) {
                            break;
                        } else {
                            zzhv.zzb((Object) t, j, zzhv.zzk(t2, j));
                            zzb(t, i);
                            break;
                        }
                    case 16:
                        if (!zza(t2, i)) {
                            break;
                        } else {
                            zzhv.zza((Object) t, j, zzhv.zzl(t2, j));
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
                        this.zzakw.zza(t, t2, j);
                        break;
                    case 50:
                        zzgz.zza(this.zzakz, t, t2, j);
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
                            zzhv.zza((Object) t, j, zzhv.zzp(t2, j));
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
                            zzhv.zza((Object) t, j, zzhv.zzp(t2, j));
                            zzb(t, i2, i);
                            break;
                        }
                    case 68:
                        zzb(t, t2, i);
                        break;
                }
            }
            if (!this.zzakq) {
                zzgz.zza(this.zzakx, t, t2);
                if (this.zzako) {
                    zzgz.zza(this.zzaky, t, t2);
                    return;
                }
                return;
            }
            return;
        }
        throw new NullPointerException();
    }

    private final void zza(T t, T t2, int i) {
        long zzca = (long) (zzca(i) & 1048575);
        if (zza(t2, i)) {
            Object zzp = zzhv.zzp(t, zzca);
            Object zzp2 = zzhv.zzp(t2, zzca);
            if (zzp == null || zzp2 == null) {
                if (zzp2 != null) {
                    zzhv.zza((Object) t, zzca, zzp2);
                    zzb(t, i);
                }
                return;
            }
            zzhv.zza((Object) t, zzca, zzez.zza(zzp, zzp2));
            zzb(t, i);
        }
    }

    private final void zzb(T t, T t2, int i) {
        int zzca = zzca(i);
        int i2 = this.zzakj[i];
        long j = (long) (zzca & 1048575);
        if (zza(t2, i2, i)) {
            Object zzp = zzhv.zzp(t, j);
            Object zzp2 = zzhv.zzp(t2, j);
            if (zzp == null || zzp2 == null) {
                if (zzp2 != null) {
                    zzhv.zza((Object) t, j, zzp2);
                    zzb(t, i2, i);
                }
                return;
            }
            zzhv.zza((Object) t, j, zzez.zza(zzp, zzp2));
            zzb(t, i2, i);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:398:0x0834, code lost:
        r8 = (r8 + r9) + r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:415:0x0900, code lost:
        r5 = r5 + r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:421:0x0915, code lost:
        r13 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:437:0x095a, code lost:
        r5 = r5 + r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:476:0x0a11, code lost:
        r5 = r5 + r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:484:0x0a34, code lost:
        r3 = r3 + 3;
        r9 = r13;
        r7 = 1048575;
        r8 = 1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int zzt(T r20) {
        /*
            r19 = this;
            r0 = r19
            r1 = r20
            boolean r2 = r0.zzakq
            r3 = 267386880(0xff00000, float:2.3665827E-29)
            r4 = 0
            r7 = 1048575(0xfffff, float:1.469367E-39)
            r8 = 1
            r9 = 0
            r11 = 0
            if (r2 == 0) goto L_0x04f2
            sun.misc.Unsafe r2 = zzaki
            r12 = 0
            r13 = 0
        L_0x0016:
            int[] r14 = r0.zzakj
            int r14 = r14.length
            if (r12 >= r14) goto L_0x04ea
            int r14 = r0.zzca(r12)
            r15 = r14 & r3
            int r15 = r15 >>> 20
            int[] r3 = r0.zzakj
            r3 = r3[r12]
            r14 = r14 & r7
            long r5 = (long) r14
            com.google.android.gms.internal.measurement.zzet r14 = com.google.android.gms.internal.measurement.zzet.DOUBLE_LIST_PACKED
            int r14 = r14.mo20587id()
            if (r15 < r14) goto L_0x0041
            com.google.android.gms.internal.measurement.zzet r14 = com.google.android.gms.internal.measurement.zzet.SINT64_LIST_PACKED
            int r14 = r14.mo20587id()
            if (r15 > r14) goto L_0x0041
            int[] r14 = r0.zzakj
            int r17 = r12 + 2
            r14 = r14[r17]
            r14 = r14 & r7
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
            java.lang.Object r5 = com.google.android.gms.internal.measurement.zzhv.zzp(r1, r5)
            com.google.android.gms.internal.measurement.zzgi r5 = (com.google.android.gms.internal.measurement.zzgi) r5
            com.google.android.gms.internal.measurement.zzgx r6 = r0.zzbx(r12)
            int r3 = com.google.android.gms.internal.measurement.zzee.zzc(r3, r5, r6)
            goto L_0x03c9
        L_0x005d:
            boolean r14 = r0.zza((T) r1, r3, r12)
            if (r14 == 0) goto L_0x04e4
            long r5 = zzi(r1, r5)
            int r3 = com.google.android.gms.internal.measurement.zzee.zzf(r3, r5)
            goto L_0x03c9
        L_0x006d:
            boolean r14 = r0.zza((T) r1, r3, r12)
            if (r14 == 0) goto L_0x04e4
            int r5 = zzh(r1, r5)
            int r3 = com.google.android.gms.internal.measurement.zzee.zzi(r3, r5)
            goto L_0x03c9
        L_0x007d:
            boolean r5 = r0.zza((T) r1, r3, r12)
            if (r5 == 0) goto L_0x04e4
            int r3 = com.google.android.gms.internal.measurement.zzee.zzh(r3, r9)
            goto L_0x03c9
        L_0x0089:
            boolean r5 = r0.zza((T) r1, r3, r12)
            if (r5 == 0) goto L_0x04e4
            int r3 = com.google.android.gms.internal.measurement.zzee.zzk(r3, r11)
            goto L_0x03c9
        L_0x0095:
            boolean r14 = r0.zza((T) r1, r3, r12)
            if (r14 == 0) goto L_0x04e4
            int r5 = zzh(r1, r5)
            int r3 = com.google.android.gms.internal.measurement.zzee.zzl(r3, r5)
            goto L_0x03c9
        L_0x00a5:
            boolean r14 = r0.zza((T) r1, r3, r12)
            if (r14 == 0) goto L_0x04e4
            int r5 = zzh(r1, r5)
            int r3 = com.google.android.gms.internal.measurement.zzee.zzh(r3, r5)
            goto L_0x03c9
        L_0x00b5:
            boolean r14 = r0.zza((T) r1, r3, r12)
            if (r14 == 0) goto L_0x04e4
            java.lang.Object r5 = com.google.android.gms.internal.measurement.zzhv.zzp(r1, r5)
            com.google.android.gms.internal.measurement.zzdp r5 = (com.google.android.gms.internal.measurement.zzdp) r5
            int r3 = com.google.android.gms.internal.measurement.zzee.zzc(r3, r5)
            goto L_0x03c9
        L_0x00c7:
            boolean r14 = r0.zza((T) r1, r3, r12)
            if (r14 == 0) goto L_0x04e4
            java.lang.Object r5 = com.google.android.gms.internal.measurement.zzhv.zzp(r1, r5)
            com.google.android.gms.internal.measurement.zzgx r6 = r0.zzbx(r12)
            int r3 = com.google.android.gms.internal.measurement.zzgz.zzc(r3, r5, r6)
            goto L_0x03c9
        L_0x00db:
            boolean r14 = r0.zza((T) r1, r3, r12)
            if (r14 == 0) goto L_0x04e4
            java.lang.Object r5 = com.google.android.gms.internal.measurement.zzhv.zzp(r1, r5)
            boolean r6 = r5 instanceof com.google.android.gms.internal.measurement.zzdp
            if (r6 == 0) goto L_0x00f1
            com.google.android.gms.internal.measurement.zzdp r5 = (com.google.android.gms.internal.measurement.zzdp) r5
            int r3 = com.google.android.gms.internal.measurement.zzee.zzc(r3, r5)
            goto L_0x03c9
        L_0x00f1:
            java.lang.String r5 = (java.lang.String) r5
            int r3 = com.google.android.gms.internal.measurement.zzee.zzc(r3, r5)
            goto L_0x03c9
        L_0x00f9:
            boolean r5 = r0.zza((T) r1, r3, r12)
            if (r5 == 0) goto L_0x04e4
            int r3 = com.google.android.gms.internal.measurement.zzee.zzc(r3, r8)
            goto L_0x03c9
        L_0x0105:
            boolean r5 = r0.zza((T) r1, r3, r12)
            if (r5 == 0) goto L_0x04e4
            int r3 = com.google.android.gms.internal.measurement.zzee.zzj(r3, r11)
            goto L_0x03c9
        L_0x0111:
            boolean r5 = r0.zza((T) r1, r3, r12)
            if (r5 == 0) goto L_0x04e4
            int r3 = com.google.android.gms.internal.measurement.zzee.zzg(r3, r9)
            goto L_0x03c9
        L_0x011d:
            boolean r14 = r0.zza((T) r1, r3, r12)
            if (r14 == 0) goto L_0x04e4
            int r5 = zzh(r1, r5)
            int r3 = com.google.android.gms.internal.measurement.zzee.zzg(r3, r5)
            goto L_0x03c9
        L_0x012d:
            boolean r14 = r0.zza((T) r1, r3, r12)
            if (r14 == 0) goto L_0x04e4
            long r5 = zzi(r1, r5)
            int r3 = com.google.android.gms.internal.measurement.zzee.zze(r3, r5)
            goto L_0x03c9
        L_0x013d:
            boolean r14 = r0.zza((T) r1, r3, r12)
            if (r14 == 0) goto L_0x04e4
            long r5 = zzi(r1, r5)
            int r3 = com.google.android.gms.internal.measurement.zzee.zzd(r3, r5)
            goto L_0x03c9
        L_0x014d:
            boolean r5 = r0.zza((T) r1, r3, r12)
            if (r5 == 0) goto L_0x04e4
            int r3 = com.google.android.gms.internal.measurement.zzee.zzb(r3, r4)
            goto L_0x03c9
        L_0x0159:
            boolean r5 = r0.zza((T) r1, r3, r12)
            if (r5 == 0) goto L_0x04e4
            r5 = 0
            int r3 = com.google.android.gms.internal.measurement.zzee.zzb(r3, r5)
            goto L_0x03c9
        L_0x0167:
            com.google.android.gms.internal.measurement.zzgb r14 = r0.zzakz
            java.lang.Object r5 = com.google.android.gms.internal.measurement.zzhv.zzp(r1, r5)
            java.lang.Object r6 = r0.zzby(r12)
            int r3 = r14.zzb(r3, r5, r6)
            goto L_0x03c9
        L_0x0177:
            java.util.List r5 = zze(r1, r5)
            com.google.android.gms.internal.measurement.zzgx r6 = r0.zzbx(r12)
            int r3 = com.google.android.gms.internal.measurement.zzgz.zzd(r3, r5, r6)
            goto L_0x03c9
        L_0x0185:
            java.lang.Object r5 = r2.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.gms.internal.measurement.zzgz.zzw(r5)
            if (r5 <= 0) goto L_0x04e4
            boolean r6 = r0.zzakr
            if (r6 == 0) goto L_0x0199
            long r14 = (long) r14
            r2.putInt(r1, r14, r5)
        L_0x0199:
            int r3 = com.google.android.gms.internal.measurement.zzee.zzbi(r3)
            int r6 = com.google.android.gms.internal.measurement.zzee.zzbk(r5)
            goto L_0x0324
        L_0x01a3:
            java.lang.Object r5 = r2.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.gms.internal.measurement.zzgz.zzaa(r5)
            if (r5 <= 0) goto L_0x04e4
            boolean r6 = r0.zzakr
            if (r6 == 0) goto L_0x01b7
            long r14 = (long) r14
            r2.putInt(r1, r14, r5)
        L_0x01b7:
            int r3 = com.google.android.gms.internal.measurement.zzee.zzbi(r3)
            int r6 = com.google.android.gms.internal.measurement.zzee.zzbk(r5)
            goto L_0x0324
        L_0x01c1:
            java.lang.Object r5 = r2.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.gms.internal.measurement.zzgz.zzac(r5)
            if (r5 <= 0) goto L_0x04e4
            boolean r6 = r0.zzakr
            if (r6 == 0) goto L_0x01d5
            long r14 = (long) r14
            r2.putInt(r1, r14, r5)
        L_0x01d5:
            int r3 = com.google.android.gms.internal.measurement.zzee.zzbi(r3)
            int r6 = com.google.android.gms.internal.measurement.zzee.zzbk(r5)
            goto L_0x0324
        L_0x01df:
            java.lang.Object r5 = r2.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.gms.internal.measurement.zzgz.zzab(r5)
            if (r5 <= 0) goto L_0x04e4
            boolean r6 = r0.zzakr
            if (r6 == 0) goto L_0x01f3
            long r14 = (long) r14
            r2.putInt(r1, r14, r5)
        L_0x01f3:
            int r3 = com.google.android.gms.internal.measurement.zzee.zzbi(r3)
            int r6 = com.google.android.gms.internal.measurement.zzee.zzbk(r5)
            goto L_0x0324
        L_0x01fd:
            java.lang.Object r5 = r2.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.gms.internal.measurement.zzgz.zzx(r5)
            if (r5 <= 0) goto L_0x04e4
            boolean r6 = r0.zzakr
            if (r6 == 0) goto L_0x0211
            long r14 = (long) r14
            r2.putInt(r1, r14, r5)
        L_0x0211:
            int r3 = com.google.android.gms.internal.measurement.zzee.zzbi(r3)
            int r6 = com.google.android.gms.internal.measurement.zzee.zzbk(r5)
            goto L_0x0324
        L_0x021b:
            java.lang.Object r5 = r2.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.gms.internal.measurement.zzgz.zzz(r5)
            if (r5 <= 0) goto L_0x04e4
            boolean r6 = r0.zzakr
            if (r6 == 0) goto L_0x022f
            long r14 = (long) r14
            r2.putInt(r1, r14, r5)
        L_0x022f:
            int r3 = com.google.android.gms.internal.measurement.zzee.zzbi(r3)
            int r6 = com.google.android.gms.internal.measurement.zzee.zzbk(r5)
            goto L_0x0324
        L_0x0239:
            java.lang.Object r5 = r2.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.gms.internal.measurement.zzgz.zzad(r5)
            if (r5 <= 0) goto L_0x04e4
            boolean r6 = r0.zzakr
            if (r6 == 0) goto L_0x024d
            long r14 = (long) r14
            r2.putInt(r1, r14, r5)
        L_0x024d:
            int r3 = com.google.android.gms.internal.measurement.zzee.zzbi(r3)
            int r6 = com.google.android.gms.internal.measurement.zzee.zzbk(r5)
            goto L_0x0324
        L_0x0257:
            java.lang.Object r5 = r2.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.gms.internal.measurement.zzgz.zzab(r5)
            if (r5 <= 0) goto L_0x04e4
            boolean r6 = r0.zzakr
            if (r6 == 0) goto L_0x026b
            long r14 = (long) r14
            r2.putInt(r1, r14, r5)
        L_0x026b:
            int r3 = com.google.android.gms.internal.measurement.zzee.zzbi(r3)
            int r6 = com.google.android.gms.internal.measurement.zzee.zzbk(r5)
            goto L_0x0324
        L_0x0275:
            java.lang.Object r5 = r2.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.gms.internal.measurement.zzgz.zzac(r5)
            if (r5 <= 0) goto L_0x04e4
            boolean r6 = r0.zzakr
            if (r6 == 0) goto L_0x0289
            long r14 = (long) r14
            r2.putInt(r1, r14, r5)
        L_0x0289:
            int r3 = com.google.android.gms.internal.measurement.zzee.zzbi(r3)
            int r6 = com.google.android.gms.internal.measurement.zzee.zzbk(r5)
            goto L_0x0324
        L_0x0293:
            java.lang.Object r5 = r2.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.gms.internal.measurement.zzgz.zzy(r5)
            if (r5 <= 0) goto L_0x04e4
            boolean r6 = r0.zzakr
            if (r6 == 0) goto L_0x02a7
            long r14 = (long) r14
            r2.putInt(r1, r14, r5)
        L_0x02a7:
            int r3 = com.google.android.gms.internal.measurement.zzee.zzbi(r3)
            int r6 = com.google.android.gms.internal.measurement.zzee.zzbk(r5)
            goto L_0x0324
        L_0x02b1:
            java.lang.Object r5 = r2.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.gms.internal.measurement.zzgz.zzv(r5)
            if (r5 <= 0) goto L_0x04e4
            boolean r6 = r0.zzakr
            if (r6 == 0) goto L_0x02c5
            long r14 = (long) r14
            r2.putInt(r1, r14, r5)
        L_0x02c5:
            int r3 = com.google.android.gms.internal.measurement.zzee.zzbi(r3)
            int r6 = com.google.android.gms.internal.measurement.zzee.zzbk(r5)
            goto L_0x0324
        L_0x02ce:
            java.lang.Object r5 = r2.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.gms.internal.measurement.zzgz.zzu(r5)
            if (r5 <= 0) goto L_0x04e4
            boolean r6 = r0.zzakr
            if (r6 == 0) goto L_0x02e2
            long r14 = (long) r14
            r2.putInt(r1, r14, r5)
        L_0x02e2:
            int r3 = com.google.android.gms.internal.measurement.zzee.zzbi(r3)
            int r6 = com.google.android.gms.internal.measurement.zzee.zzbk(r5)
            goto L_0x0324
        L_0x02eb:
            java.lang.Object r5 = r2.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.gms.internal.measurement.zzgz.zzab(r5)
            if (r5 <= 0) goto L_0x04e4
            boolean r6 = r0.zzakr
            if (r6 == 0) goto L_0x02ff
            long r14 = (long) r14
            r2.putInt(r1, r14, r5)
        L_0x02ff:
            int r3 = com.google.android.gms.internal.measurement.zzee.zzbi(r3)
            int r6 = com.google.android.gms.internal.measurement.zzee.zzbk(r5)
            goto L_0x0324
        L_0x0308:
            java.lang.Object r5 = r2.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.gms.internal.measurement.zzgz.zzac(r5)
            if (r5 <= 0) goto L_0x04e4
            boolean r6 = r0.zzakr
            if (r6 == 0) goto L_0x031c
            long r14 = (long) r14
            r2.putInt(r1, r14, r5)
        L_0x031c:
            int r3 = com.google.android.gms.internal.measurement.zzee.zzbi(r3)
            int r6 = com.google.android.gms.internal.measurement.zzee.zzbk(r5)
        L_0x0324:
            int r3 = r3 + r6
            int r3 = r3 + r5
            goto L_0x03c9
        L_0x0328:
            java.util.List r5 = zze(r1, r5)
            int r3 = com.google.android.gms.internal.measurement.zzgz.zzq(r3, r5, r11)
            goto L_0x03c9
        L_0x0332:
            java.util.List r5 = zze(r1, r5)
            int r3 = com.google.android.gms.internal.measurement.zzgz.zzu(r3, r5, r11)
            goto L_0x03c9
        L_0x033c:
            java.util.List r5 = zze(r1, r5)
            int r3 = com.google.android.gms.internal.measurement.zzgz.zzw(r3, r5, r11)
            goto L_0x03c9
        L_0x0346:
            java.util.List r5 = zze(r1, r5)
            int r3 = com.google.android.gms.internal.measurement.zzgz.zzv(r3, r5, r11)
            goto L_0x03c9
        L_0x0350:
            java.util.List r5 = zze(r1, r5)
            int r3 = com.google.android.gms.internal.measurement.zzgz.zzr(r3, r5, r11)
            goto L_0x03c9
        L_0x035a:
            java.util.List r5 = zze(r1, r5)
            int r3 = com.google.android.gms.internal.measurement.zzgz.zzt(r3, r5, r11)
            goto L_0x03c9
        L_0x0363:
            java.util.List r5 = zze(r1, r5)
            int r3 = com.google.android.gms.internal.measurement.zzgz.zzd(r3, r5)
            goto L_0x03c9
        L_0x036c:
            java.util.List r5 = zze(r1, r5)
            com.google.android.gms.internal.measurement.zzgx r6 = r0.zzbx(r12)
            int r3 = com.google.android.gms.internal.measurement.zzgz.zzc(r3, r5, r6)
            goto L_0x03c9
        L_0x0379:
            java.util.List r5 = zze(r1, r5)
            int r3 = com.google.android.gms.internal.measurement.zzgz.zzc(r3, r5)
            goto L_0x03c9
        L_0x0382:
            java.util.List r5 = zze(r1, r5)
            int r3 = com.google.android.gms.internal.measurement.zzgz.zzx(r3, r5, r11)
            goto L_0x03c9
        L_0x038b:
            java.util.List r5 = zze(r1, r5)
            int r3 = com.google.android.gms.internal.measurement.zzgz.zzv(r3, r5, r11)
            goto L_0x03c9
        L_0x0394:
            java.util.List r5 = zze(r1, r5)
            int r3 = com.google.android.gms.internal.measurement.zzgz.zzw(r3, r5, r11)
            goto L_0x03c9
        L_0x039d:
            java.util.List r5 = zze(r1, r5)
            int r3 = com.google.android.gms.internal.measurement.zzgz.zzs(r3, r5, r11)
            goto L_0x03c9
        L_0x03a6:
            java.util.List r5 = zze(r1, r5)
            int r3 = com.google.android.gms.internal.measurement.zzgz.zzp(r3, r5, r11)
            goto L_0x03c9
        L_0x03af:
            java.util.List r5 = zze(r1, r5)
            int r3 = com.google.android.gms.internal.measurement.zzgz.zzo(r3, r5, r11)
            goto L_0x03c9
        L_0x03b8:
            java.util.List r5 = zze(r1, r5)
            int r3 = com.google.android.gms.internal.measurement.zzgz.zzv(r3, r5, r11)
            goto L_0x03c9
        L_0x03c1:
            java.util.List r5 = zze(r1, r5)
            int r3 = com.google.android.gms.internal.measurement.zzgz.zzw(r3, r5, r11)
        L_0x03c9:
            int r13 = r13 + r3
            goto L_0x04e4
        L_0x03cc:
            boolean r14 = r0.zza((T) r1, r12)
            if (r14 == 0) goto L_0x04e4
            java.lang.Object r5 = com.google.android.gms.internal.measurement.zzhv.zzp(r1, r5)
            com.google.android.gms.internal.measurement.zzgi r5 = (com.google.android.gms.internal.measurement.zzgi) r5
            com.google.android.gms.internal.measurement.zzgx r6 = r0.zzbx(r12)
            int r3 = com.google.android.gms.internal.measurement.zzee.zzc(r3, r5, r6)
            goto L_0x03c9
        L_0x03e1:
            boolean r14 = r0.zza((T) r1, r12)
            if (r14 == 0) goto L_0x04e4
            long r5 = com.google.android.gms.internal.measurement.zzhv.zzl(r1, r5)
            int r3 = com.google.android.gms.internal.measurement.zzee.zzf(r3, r5)
            goto L_0x03c9
        L_0x03f0:
            boolean r14 = r0.zza((T) r1, r12)
            if (r14 == 0) goto L_0x04e4
            int r5 = com.google.android.gms.internal.measurement.zzhv.zzk(r1, r5)
            int r3 = com.google.android.gms.internal.measurement.zzee.zzi(r3, r5)
            goto L_0x03c9
        L_0x03ff:
            boolean r5 = r0.zza((T) r1, r12)
            if (r5 == 0) goto L_0x04e4
            int r3 = com.google.android.gms.internal.measurement.zzee.zzh(r3, r9)
            goto L_0x03c9
        L_0x040a:
            boolean r5 = r0.zza((T) r1, r12)
            if (r5 == 0) goto L_0x04e4
            int r3 = com.google.android.gms.internal.measurement.zzee.zzk(r3, r11)
            goto L_0x03c9
        L_0x0415:
            boolean r14 = r0.zza((T) r1, r12)
            if (r14 == 0) goto L_0x04e4
            int r5 = com.google.android.gms.internal.measurement.zzhv.zzk(r1, r5)
            int r3 = com.google.android.gms.internal.measurement.zzee.zzl(r3, r5)
            goto L_0x03c9
        L_0x0424:
            boolean r14 = r0.zza((T) r1, r12)
            if (r14 == 0) goto L_0x04e4
            int r5 = com.google.android.gms.internal.measurement.zzhv.zzk(r1, r5)
            int r3 = com.google.android.gms.internal.measurement.zzee.zzh(r3, r5)
            goto L_0x03c9
        L_0x0433:
            boolean r14 = r0.zza((T) r1, r12)
            if (r14 == 0) goto L_0x04e4
            java.lang.Object r5 = com.google.android.gms.internal.measurement.zzhv.zzp(r1, r5)
            com.google.android.gms.internal.measurement.zzdp r5 = (com.google.android.gms.internal.measurement.zzdp) r5
            int r3 = com.google.android.gms.internal.measurement.zzee.zzc(r3, r5)
            goto L_0x03c9
        L_0x0444:
            boolean r14 = r0.zza((T) r1, r12)
            if (r14 == 0) goto L_0x04e4
            java.lang.Object r5 = com.google.android.gms.internal.measurement.zzhv.zzp(r1, r5)
            com.google.android.gms.internal.measurement.zzgx r6 = r0.zzbx(r12)
            int r3 = com.google.android.gms.internal.measurement.zzgz.zzc(r3, r5, r6)
            goto L_0x03c9
        L_0x0458:
            boolean r14 = r0.zza((T) r1, r12)
            if (r14 == 0) goto L_0x04e4
            java.lang.Object r5 = com.google.android.gms.internal.measurement.zzhv.zzp(r1, r5)
            boolean r6 = r5 instanceof com.google.android.gms.internal.measurement.zzdp
            if (r6 == 0) goto L_0x046e
            com.google.android.gms.internal.measurement.zzdp r5 = (com.google.android.gms.internal.measurement.zzdp) r5
            int r3 = com.google.android.gms.internal.measurement.zzee.zzc(r3, r5)
            goto L_0x03c9
        L_0x046e:
            java.lang.String r5 = (java.lang.String) r5
            int r3 = com.google.android.gms.internal.measurement.zzee.zzc(r3, r5)
            goto L_0x03c9
        L_0x0476:
            boolean r5 = r0.zza((T) r1, r12)
            if (r5 == 0) goto L_0x04e4
            int r3 = com.google.android.gms.internal.measurement.zzee.zzc(r3, r8)
            goto L_0x03c9
        L_0x0482:
            boolean r5 = r0.zza((T) r1, r12)
            if (r5 == 0) goto L_0x04e4
            int r3 = com.google.android.gms.internal.measurement.zzee.zzj(r3, r11)
            goto L_0x03c9
        L_0x048e:
            boolean r5 = r0.zza((T) r1, r12)
            if (r5 == 0) goto L_0x04e4
            int r3 = com.google.android.gms.internal.measurement.zzee.zzg(r3, r9)
            goto L_0x03c9
        L_0x049a:
            boolean r14 = r0.zza((T) r1, r12)
            if (r14 == 0) goto L_0x04e4
            int r5 = com.google.android.gms.internal.measurement.zzhv.zzk(r1, r5)
            int r3 = com.google.android.gms.internal.measurement.zzee.zzg(r3, r5)
            goto L_0x03c9
        L_0x04aa:
            boolean r14 = r0.zza((T) r1, r12)
            if (r14 == 0) goto L_0x04e4
            long r5 = com.google.android.gms.internal.measurement.zzhv.zzl(r1, r5)
            int r3 = com.google.android.gms.internal.measurement.zzee.zze(r3, r5)
            goto L_0x03c9
        L_0x04ba:
            boolean r14 = r0.zza((T) r1, r12)
            if (r14 == 0) goto L_0x04e4
            long r5 = com.google.android.gms.internal.measurement.zzhv.zzl(r1, r5)
            int r3 = com.google.android.gms.internal.measurement.zzee.zzd(r3, r5)
            goto L_0x03c9
        L_0x04ca:
            boolean r5 = r0.zza((T) r1, r12)
            if (r5 == 0) goto L_0x04e4
            int r3 = com.google.android.gms.internal.measurement.zzee.zzb(r3, r4)
            goto L_0x03c9
        L_0x04d6:
            boolean r5 = r0.zza((T) r1, r12)
            if (r5 == 0) goto L_0x04e4
            r5 = 0
            int r3 = com.google.android.gms.internal.measurement.zzee.zzb(r3, r5)
            goto L_0x03c9
        L_0x04e4:
            int r12 = r12 + 3
            r3 = 267386880(0xff00000, float:2.3665827E-29)
            goto L_0x0016
        L_0x04ea:
            com.google.android.gms.internal.measurement.zzhp<?, ?> r2 = r0.zzakx
            int r1 = zza(r2, (T) r1)
            int r13 = r13 + r1
            return r13
        L_0x04f2:
            sun.misc.Unsafe r2 = zzaki
            r3 = -1
            r3 = 0
            r5 = 0
            r6 = -1
            r12 = 0
        L_0x04f9:
            int[] r13 = r0.zzakj
            int r13 = r13.length
            if (r3 >= r13) goto L_0x0a3f
            int r13 = r0.zzca(r3)
            int[] r14 = r0.zzakj
            r15 = r14[r3]
            r16 = 267386880(0xff00000, float:2.3665827E-29)
            r17 = r13 & r16
            int r4 = r17 >>> 20
            r11 = 17
            if (r4 > r11) goto L_0x0525
            int r11 = r3 + 2
            r11 = r14[r11]
            r14 = r11 & r7
            int r18 = r11 >>> 20
            int r18 = r8 << r18
            if (r14 == r6) goto L_0x0522
            long r8 = (long) r14
            int r12 = r2.getInt(r1, r8)
            goto L_0x0523
        L_0x0522:
            r14 = r6
        L_0x0523:
            r6 = r14
            goto L_0x0545
        L_0x0525:
            boolean r8 = r0.zzakr
            if (r8 == 0) goto L_0x0542
            com.google.android.gms.internal.measurement.zzet r8 = com.google.android.gms.internal.measurement.zzet.DOUBLE_LIST_PACKED
            int r8 = r8.mo20587id()
            if (r4 < r8) goto L_0x0542
            com.google.android.gms.internal.measurement.zzet r8 = com.google.android.gms.internal.measurement.zzet.SINT64_LIST_PACKED
            int r8 = r8.mo20587id()
            if (r4 > r8) goto L_0x0542
            int[] r8 = r0.zzakj
            int r9 = r3 + 2
            r8 = r8[r9]
            r11 = r8 & r7
            goto L_0x0543
        L_0x0542:
            r11 = 0
        L_0x0543:
            r18 = 0
        L_0x0545:
            r8 = r13 & r7
            long r8 = (long) r8
            switch(r4) {
                case 0: goto L_0x0a24;
                case 1: goto L_0x0a13;
                case 2: goto L_0x0a01;
                case 3: goto L_0x09f0;
                case 4: goto L_0x09df;
                case 5: goto L_0x09cf;
                case 6: goto L_0x09bf;
                case 7: goto L_0x09b3;
                case 8: goto L_0x0997;
                case 9: goto L_0x0985;
                case 10: goto L_0x0976;
                case 11: goto L_0x0969;
                case 12: goto L_0x095c;
                case 13: goto L_0x0951;
                case 14: goto L_0x0946;
                case 15: goto L_0x0939;
                case 16: goto L_0x092c;
                case 17: goto L_0x0919;
                case 18: goto L_0x0905;
                case 19: goto L_0x08f5;
                case 20: goto L_0x08e9;
                case 21: goto L_0x08dd;
                case 22: goto L_0x08d1;
                case 23: goto L_0x08c5;
                case 24: goto L_0x08b9;
                case 25: goto L_0x08ad;
                case 26: goto L_0x08a2;
                case 27: goto L_0x0892;
                case 28: goto L_0x0886;
                case 29: goto L_0x0879;
                case 30: goto L_0x086c;
                case 31: goto L_0x085f;
                case 32: goto L_0x0852;
                case 33: goto L_0x0845;
                case 34: goto L_0x0838;
                case 35: goto L_0x0818;
                case 36: goto L_0x07fb;
                case 37: goto L_0x07de;
                case 38: goto L_0x07c1;
                case 39: goto L_0x07a3;
                case 40: goto L_0x0785;
                case 41: goto L_0x0767;
                case 42: goto L_0x0749;
                case 43: goto L_0x072b;
                case 44: goto L_0x070d;
                case 45: goto L_0x06ef;
                case 46: goto L_0x06d1;
                case 47: goto L_0x06b3;
                case 48: goto L_0x0695;
                case 49: goto L_0x0685;
                case 50: goto L_0x0675;
                case 51: goto L_0x0667;
                case 52: goto L_0x065a;
                case 53: goto L_0x064a;
                case 54: goto L_0x063a;
                case 55: goto L_0x062a;
                case 56: goto L_0x061c;
                case 57: goto L_0x060f;
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
            goto L_0x0911
        L_0x054d:
            boolean r4 = r0.zza((T) r1, r15, r3)
            if (r4 == 0) goto L_0x0911
            java.lang.Object r4 = r2.getObject(r1, r8)
            com.google.android.gms.internal.measurement.zzgi r4 = (com.google.android.gms.internal.measurement.zzgi) r4
            com.google.android.gms.internal.measurement.zzgx r8 = r0.zzbx(r3)
            int r4 = com.google.android.gms.internal.measurement.zzee.zzc(r15, r4, r8)
            goto L_0x0910
        L_0x0563:
            boolean r4 = r0.zza((T) r1, r15, r3)
            if (r4 == 0) goto L_0x0911
            long r8 = zzi(r1, r8)
            int r4 = com.google.android.gms.internal.measurement.zzee.zzf(r15, r8)
            goto L_0x0910
        L_0x0573:
            boolean r4 = r0.zza((T) r1, r15, r3)
            if (r4 == 0) goto L_0x0911
            int r4 = zzh(r1, r8)
            int r4 = com.google.android.gms.internal.measurement.zzee.zzi(r15, r4)
            goto L_0x0910
        L_0x0583:
            boolean r4 = r0.zza((T) r1, r15, r3)
            if (r4 == 0) goto L_0x0911
            r8 = 0
            int r4 = com.google.android.gms.internal.measurement.zzee.zzh(r15, r8)
            goto L_0x0910
        L_0x0591:
            boolean r4 = r0.zza((T) r1, r15, r3)
            if (r4 == 0) goto L_0x0911
            r4 = 0
            int r8 = com.google.android.gms.internal.measurement.zzee.zzk(r15, r4)
            goto L_0x095a
        L_0x059e:
            boolean r4 = r0.zza((T) r1, r15, r3)
            if (r4 == 0) goto L_0x0911
            int r4 = zzh(r1, r8)
            int r4 = com.google.android.gms.internal.measurement.zzee.zzl(r15, r4)
            goto L_0x0910
        L_0x05ae:
            boolean r4 = r0.zza((T) r1, r15, r3)
            if (r4 == 0) goto L_0x0911
            int r4 = zzh(r1, r8)
            int r4 = com.google.android.gms.internal.measurement.zzee.zzh(r15, r4)
            goto L_0x0910
        L_0x05be:
            boolean r4 = r0.zza((T) r1, r15, r3)
            if (r4 == 0) goto L_0x0911
            java.lang.Object r4 = r2.getObject(r1, r8)
            com.google.android.gms.internal.measurement.zzdp r4 = (com.google.android.gms.internal.measurement.zzdp) r4
            int r4 = com.google.android.gms.internal.measurement.zzee.zzc(r15, r4)
            goto L_0x0910
        L_0x05d0:
            boolean r4 = r0.zza((T) r1, r15, r3)
            if (r4 == 0) goto L_0x0911
            java.lang.Object r4 = r2.getObject(r1, r8)
            com.google.android.gms.internal.measurement.zzgx r8 = r0.zzbx(r3)
            int r4 = com.google.android.gms.internal.measurement.zzgz.zzc(r15, r4, r8)
            goto L_0x0910
        L_0x05e4:
            boolean r4 = r0.zza((T) r1, r15, r3)
            if (r4 == 0) goto L_0x0911
            java.lang.Object r4 = r2.getObject(r1, r8)
            boolean r8 = r4 instanceof com.google.android.gms.internal.measurement.zzdp
            if (r8 == 0) goto L_0x05fa
            com.google.android.gms.internal.measurement.zzdp r4 = (com.google.android.gms.internal.measurement.zzdp) r4
            int r4 = com.google.android.gms.internal.measurement.zzee.zzc(r15, r4)
            goto L_0x0910
        L_0x05fa:
            java.lang.String r4 = (java.lang.String) r4
            int r4 = com.google.android.gms.internal.measurement.zzee.zzc(r15, r4)
            goto L_0x0910
        L_0x0602:
            boolean r4 = r0.zza((T) r1, r15, r3)
            if (r4 == 0) goto L_0x0911
            r4 = 1
            int r8 = com.google.android.gms.internal.measurement.zzee.zzc(r15, r4)
            goto L_0x095a
        L_0x060f:
            boolean r4 = r0.zza((T) r1, r15, r3)
            if (r4 == 0) goto L_0x0911
            r4 = 0
            int r8 = com.google.android.gms.internal.measurement.zzee.zzj(r15, r4)
            goto L_0x095a
        L_0x061c:
            boolean r4 = r0.zza((T) r1, r15, r3)
            if (r4 == 0) goto L_0x0911
            r8 = 0
            int r4 = com.google.android.gms.internal.measurement.zzee.zzg(r15, r8)
            goto L_0x0910
        L_0x062a:
            boolean r4 = r0.zza((T) r1, r15, r3)
            if (r4 == 0) goto L_0x0911
            int r4 = zzh(r1, r8)
            int r4 = com.google.android.gms.internal.measurement.zzee.zzg(r15, r4)
            goto L_0x0910
        L_0x063a:
            boolean r4 = r0.zza((T) r1, r15, r3)
            if (r4 == 0) goto L_0x0911
            long r8 = zzi(r1, r8)
            int r4 = com.google.android.gms.internal.measurement.zzee.zze(r15, r8)
            goto L_0x0910
        L_0x064a:
            boolean r4 = r0.zza((T) r1, r15, r3)
            if (r4 == 0) goto L_0x0911
            long r8 = zzi(r1, r8)
            int r4 = com.google.android.gms.internal.measurement.zzee.zzd(r15, r8)
            goto L_0x0910
        L_0x065a:
            boolean r4 = r0.zza((T) r1, r15, r3)
            if (r4 == 0) goto L_0x0911
            r4 = 0
            int r8 = com.google.android.gms.internal.measurement.zzee.zzb(r15, r4)
            goto L_0x095a
        L_0x0667:
            boolean r4 = r0.zza((T) r1, r15, r3)
            if (r4 == 0) goto L_0x0911
            r8 = 0
            int r4 = com.google.android.gms.internal.measurement.zzee.zzb(r15, r8)
            goto L_0x0910
        L_0x0675:
            com.google.android.gms.internal.measurement.zzgb r4 = r0.zzakz
            java.lang.Object r8 = r2.getObject(r1, r8)
            java.lang.Object r9 = r0.zzby(r3)
            int r4 = r4.zzb(r15, r8, r9)
            goto L_0x0910
        L_0x0685:
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.measurement.zzgx r8 = r0.zzbx(r3)
            int r4 = com.google.android.gms.internal.measurement.zzgz.zzd(r15, r4, r8)
            goto L_0x0910
        L_0x0695:
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.measurement.zzgz.zzw(r4)
            if (r4 <= 0) goto L_0x0911
            boolean r8 = r0.zzakr
            if (r8 == 0) goto L_0x06a9
            long r8 = (long) r11
            r2.putInt(r1, r8, r4)
        L_0x06a9:
            int r8 = com.google.android.gms.internal.measurement.zzee.zzbi(r15)
            int r9 = com.google.android.gms.internal.measurement.zzee.zzbk(r4)
            goto L_0x0834
        L_0x06b3:
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.measurement.zzgz.zzaa(r4)
            if (r4 <= 0) goto L_0x0911
            boolean r8 = r0.zzakr
            if (r8 == 0) goto L_0x06c7
            long r8 = (long) r11
            r2.putInt(r1, r8, r4)
        L_0x06c7:
            int r8 = com.google.android.gms.internal.measurement.zzee.zzbi(r15)
            int r9 = com.google.android.gms.internal.measurement.zzee.zzbk(r4)
            goto L_0x0834
        L_0x06d1:
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.measurement.zzgz.zzac(r4)
            if (r4 <= 0) goto L_0x0911
            boolean r8 = r0.zzakr
            if (r8 == 0) goto L_0x06e5
            long r8 = (long) r11
            r2.putInt(r1, r8, r4)
        L_0x06e5:
            int r8 = com.google.android.gms.internal.measurement.zzee.zzbi(r15)
            int r9 = com.google.android.gms.internal.measurement.zzee.zzbk(r4)
            goto L_0x0834
        L_0x06ef:
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.measurement.zzgz.zzab(r4)
            if (r4 <= 0) goto L_0x0911
            boolean r8 = r0.zzakr
            if (r8 == 0) goto L_0x0703
            long r8 = (long) r11
            r2.putInt(r1, r8, r4)
        L_0x0703:
            int r8 = com.google.android.gms.internal.measurement.zzee.zzbi(r15)
            int r9 = com.google.android.gms.internal.measurement.zzee.zzbk(r4)
            goto L_0x0834
        L_0x070d:
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.measurement.zzgz.zzx(r4)
            if (r4 <= 0) goto L_0x0911
            boolean r8 = r0.zzakr
            if (r8 == 0) goto L_0x0721
            long r8 = (long) r11
            r2.putInt(r1, r8, r4)
        L_0x0721:
            int r8 = com.google.android.gms.internal.measurement.zzee.zzbi(r15)
            int r9 = com.google.android.gms.internal.measurement.zzee.zzbk(r4)
            goto L_0x0834
        L_0x072b:
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.measurement.zzgz.zzz(r4)
            if (r4 <= 0) goto L_0x0911
            boolean r8 = r0.zzakr
            if (r8 == 0) goto L_0x073f
            long r8 = (long) r11
            r2.putInt(r1, r8, r4)
        L_0x073f:
            int r8 = com.google.android.gms.internal.measurement.zzee.zzbi(r15)
            int r9 = com.google.android.gms.internal.measurement.zzee.zzbk(r4)
            goto L_0x0834
        L_0x0749:
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.measurement.zzgz.zzad(r4)
            if (r4 <= 0) goto L_0x0911
            boolean r8 = r0.zzakr
            if (r8 == 0) goto L_0x075d
            long r8 = (long) r11
            r2.putInt(r1, r8, r4)
        L_0x075d:
            int r8 = com.google.android.gms.internal.measurement.zzee.zzbi(r15)
            int r9 = com.google.android.gms.internal.measurement.zzee.zzbk(r4)
            goto L_0x0834
        L_0x0767:
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.measurement.zzgz.zzab(r4)
            if (r4 <= 0) goto L_0x0911
            boolean r8 = r0.zzakr
            if (r8 == 0) goto L_0x077b
            long r8 = (long) r11
            r2.putInt(r1, r8, r4)
        L_0x077b:
            int r8 = com.google.android.gms.internal.measurement.zzee.zzbi(r15)
            int r9 = com.google.android.gms.internal.measurement.zzee.zzbk(r4)
            goto L_0x0834
        L_0x0785:
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.measurement.zzgz.zzac(r4)
            if (r4 <= 0) goto L_0x0911
            boolean r8 = r0.zzakr
            if (r8 == 0) goto L_0x0799
            long r8 = (long) r11
            r2.putInt(r1, r8, r4)
        L_0x0799:
            int r8 = com.google.android.gms.internal.measurement.zzee.zzbi(r15)
            int r9 = com.google.android.gms.internal.measurement.zzee.zzbk(r4)
            goto L_0x0834
        L_0x07a3:
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.measurement.zzgz.zzy(r4)
            if (r4 <= 0) goto L_0x0911
            boolean r8 = r0.zzakr
            if (r8 == 0) goto L_0x07b7
            long r8 = (long) r11
            r2.putInt(r1, r8, r4)
        L_0x07b7:
            int r8 = com.google.android.gms.internal.measurement.zzee.zzbi(r15)
            int r9 = com.google.android.gms.internal.measurement.zzee.zzbk(r4)
            goto L_0x0834
        L_0x07c1:
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.measurement.zzgz.zzv(r4)
            if (r4 <= 0) goto L_0x0911
            boolean r8 = r0.zzakr
            if (r8 == 0) goto L_0x07d5
            long r8 = (long) r11
            r2.putInt(r1, r8, r4)
        L_0x07d5:
            int r8 = com.google.android.gms.internal.measurement.zzee.zzbi(r15)
            int r9 = com.google.android.gms.internal.measurement.zzee.zzbk(r4)
            goto L_0x0834
        L_0x07de:
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.measurement.zzgz.zzu(r4)
            if (r4 <= 0) goto L_0x0911
            boolean r8 = r0.zzakr
            if (r8 == 0) goto L_0x07f2
            long r8 = (long) r11
            r2.putInt(r1, r8, r4)
        L_0x07f2:
            int r8 = com.google.android.gms.internal.measurement.zzee.zzbi(r15)
            int r9 = com.google.android.gms.internal.measurement.zzee.zzbk(r4)
            goto L_0x0834
        L_0x07fb:
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.measurement.zzgz.zzab(r4)
            if (r4 <= 0) goto L_0x0911
            boolean r8 = r0.zzakr
            if (r8 == 0) goto L_0x080f
            long r8 = (long) r11
            r2.putInt(r1, r8, r4)
        L_0x080f:
            int r8 = com.google.android.gms.internal.measurement.zzee.zzbi(r15)
            int r9 = com.google.android.gms.internal.measurement.zzee.zzbk(r4)
            goto L_0x0834
        L_0x0818:
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.measurement.zzgz.zzac(r4)
            if (r4 <= 0) goto L_0x0911
            boolean r8 = r0.zzakr
            if (r8 == 0) goto L_0x082c
            long r8 = (long) r11
            r2.putInt(r1, r8, r4)
        L_0x082c:
            int r8 = com.google.android.gms.internal.measurement.zzee.zzbi(r15)
            int r9 = com.google.android.gms.internal.measurement.zzee.zzbk(r4)
        L_0x0834:
            int r8 = r8 + r9
            int r8 = r8 + r4
            goto L_0x095a
        L_0x0838:
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            r10 = 0
            int r4 = com.google.android.gms.internal.measurement.zzgz.zzq(r15, r4, r10)
            goto L_0x0900
        L_0x0845:
            r10 = 0
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.measurement.zzgz.zzu(r15, r4, r10)
            goto L_0x0900
        L_0x0852:
            r10 = 0
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.measurement.zzgz.zzw(r15, r4, r10)
            goto L_0x0900
        L_0x085f:
            r10 = 0
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.measurement.zzgz.zzv(r15, r4, r10)
            goto L_0x0900
        L_0x086c:
            r10 = 0
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.measurement.zzgz.zzr(r15, r4, r10)
            goto L_0x0900
        L_0x0879:
            r10 = 0
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.measurement.zzgz.zzt(r15, r4, r10)
            goto L_0x0910
        L_0x0886:
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.measurement.zzgz.zzd(r15, r4)
            goto L_0x0910
        L_0x0892:
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.measurement.zzgx r8 = r0.zzbx(r3)
            int r4 = com.google.android.gms.internal.measurement.zzgz.zzc(r15, r4, r8)
            goto L_0x0910
        L_0x08a2:
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.measurement.zzgz.zzc(r15, r4)
            goto L_0x0910
        L_0x08ad:
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            r10 = 0
            int r4 = com.google.android.gms.internal.measurement.zzgz.zzx(r15, r4, r10)
            goto L_0x0900
        L_0x08b9:
            r10 = 0
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.measurement.zzgz.zzv(r15, r4, r10)
            goto L_0x0900
        L_0x08c5:
            r10 = 0
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.measurement.zzgz.zzw(r15, r4, r10)
            goto L_0x0900
        L_0x08d1:
            r10 = 0
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.measurement.zzgz.zzs(r15, r4, r10)
            goto L_0x0900
        L_0x08dd:
            r10 = 0
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.measurement.zzgz.zzp(r15, r4, r10)
            goto L_0x0900
        L_0x08e9:
            r10 = 0
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.measurement.zzgz.zzo(r15, r4, r10)
            goto L_0x0900
        L_0x08f5:
            r10 = 0
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.measurement.zzgz.zzv(r15, r4, r10)
        L_0x0900:
            int r5 = r5 + r4
            r4 = 1
        L_0x0902:
            r7 = 0
            goto L_0x0915
        L_0x0905:
            r10 = 0
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.measurement.zzgz.zzw(r15, r4, r10)
        L_0x0910:
            int r5 = r5 + r4
        L_0x0911:
            r4 = 1
        L_0x0912:
            r7 = 0
            r10 = 0
        L_0x0915:
            r13 = 0
            goto L_0x0a34
        L_0x0919:
            r4 = r12 & r18
            if (r4 == 0) goto L_0x0911
            java.lang.Object r4 = r2.getObject(r1, r8)
            com.google.android.gms.internal.measurement.zzgi r4 = (com.google.android.gms.internal.measurement.zzgi) r4
            com.google.android.gms.internal.measurement.zzgx r8 = r0.zzbx(r3)
            int r4 = com.google.android.gms.internal.measurement.zzee.zzc(r15, r4, r8)
            goto L_0x0910
        L_0x092c:
            r4 = r12 & r18
            if (r4 == 0) goto L_0x0911
            long r8 = r2.getLong(r1, r8)
            int r4 = com.google.android.gms.internal.measurement.zzee.zzf(r15, r8)
            goto L_0x0910
        L_0x0939:
            r4 = r12 & r18
            if (r4 == 0) goto L_0x0911
            int r4 = r2.getInt(r1, r8)
            int r4 = com.google.android.gms.internal.measurement.zzee.zzi(r15, r4)
            goto L_0x0910
        L_0x0946:
            r4 = r12 & r18
            if (r4 == 0) goto L_0x0911
            r8 = 0
            int r4 = com.google.android.gms.internal.measurement.zzee.zzh(r15, r8)
            goto L_0x0910
        L_0x0951:
            r4 = r12 & r18
            if (r4 == 0) goto L_0x0911
            r4 = 0
            int r8 = com.google.android.gms.internal.measurement.zzee.zzk(r15, r4)
        L_0x095a:
            int r5 = r5 + r8
            goto L_0x0911
        L_0x095c:
            r4 = r12 & r18
            if (r4 == 0) goto L_0x0911
            int r4 = r2.getInt(r1, r8)
            int r4 = com.google.android.gms.internal.measurement.zzee.zzl(r15, r4)
            goto L_0x0910
        L_0x0969:
            r4 = r12 & r18
            if (r4 == 0) goto L_0x0911
            int r4 = r2.getInt(r1, r8)
            int r4 = com.google.android.gms.internal.measurement.zzee.zzh(r15, r4)
            goto L_0x0910
        L_0x0976:
            r4 = r12 & r18
            if (r4 == 0) goto L_0x0911
            java.lang.Object r4 = r2.getObject(r1, r8)
            com.google.android.gms.internal.measurement.zzdp r4 = (com.google.android.gms.internal.measurement.zzdp) r4
            int r4 = com.google.android.gms.internal.measurement.zzee.zzc(r15, r4)
            goto L_0x0910
        L_0x0985:
            r4 = r12 & r18
            if (r4 == 0) goto L_0x0911
            java.lang.Object r4 = r2.getObject(r1, r8)
            com.google.android.gms.internal.measurement.zzgx r8 = r0.zzbx(r3)
            int r4 = com.google.android.gms.internal.measurement.zzgz.zzc(r15, r4, r8)
            goto L_0x0910
        L_0x0997:
            r4 = r12 & r18
            if (r4 == 0) goto L_0x0911
            java.lang.Object r4 = r2.getObject(r1, r8)
            boolean r8 = r4 instanceof com.google.android.gms.internal.measurement.zzdp
            if (r8 == 0) goto L_0x09ab
            com.google.android.gms.internal.measurement.zzdp r4 = (com.google.android.gms.internal.measurement.zzdp) r4
            int r4 = com.google.android.gms.internal.measurement.zzee.zzc(r15, r4)
            goto L_0x0910
        L_0x09ab:
            java.lang.String r4 = (java.lang.String) r4
            int r4 = com.google.android.gms.internal.measurement.zzee.zzc(r15, r4)
            goto L_0x0910
        L_0x09b3:
            r4 = r12 & r18
            if (r4 == 0) goto L_0x0911
            r4 = 1
            int r8 = com.google.android.gms.internal.measurement.zzee.zzc(r15, r4)
            int r5 = r5 + r8
            goto L_0x0912
        L_0x09bf:
            r4 = 1
            r8 = r12 & r18
            if (r8 == 0) goto L_0x09cc
            r10 = 0
            int r8 = com.google.android.gms.internal.measurement.zzee.zzj(r15, r10)
            int r5 = r5 + r8
            goto L_0x0902
        L_0x09cc:
            r10 = 0
            goto L_0x0902
        L_0x09cf:
            r4 = 1
            r10 = 0
            r8 = r12 & r18
            if (r8 == 0) goto L_0x09dc
            r13 = 0
            int r8 = com.google.android.gms.internal.measurement.zzee.zzg(r15, r13)
            goto L_0x0a11
        L_0x09dc:
            r13 = 0
            goto L_0x0a21
        L_0x09df:
            r4 = 1
            r10 = 0
            r13 = 0
            r11 = r12 & r18
            if (r11 == 0) goto L_0x0a21
            int r8 = r2.getInt(r1, r8)
            int r8 = com.google.android.gms.internal.measurement.zzee.zzg(r15, r8)
            goto L_0x0a11
        L_0x09f0:
            r4 = 1
            r10 = 0
            r13 = 0
            r11 = r12 & r18
            if (r11 == 0) goto L_0x0a21
            long r8 = r2.getLong(r1, r8)
            int r8 = com.google.android.gms.internal.measurement.zzee.zze(r15, r8)
            goto L_0x0a11
        L_0x0a01:
            r4 = 1
            r10 = 0
            r13 = 0
            r11 = r12 & r18
            if (r11 == 0) goto L_0x0a21
            long r8 = r2.getLong(r1, r8)
            int r8 = com.google.android.gms.internal.measurement.zzee.zzd(r15, r8)
        L_0x0a11:
            int r5 = r5 + r8
            goto L_0x0a21
        L_0x0a13:
            r4 = 1
            r10 = 0
            r13 = 0
            r8 = r12 & r18
            if (r8 == 0) goto L_0x0a21
            r8 = 0
            int r9 = com.google.android.gms.internal.measurement.zzee.zzb(r15, r8)
            int r5 = r5 + r9
        L_0x0a21:
            r7 = 0
            goto L_0x0a34
        L_0x0a24:
            r4 = 1
            r8 = 0
            r10 = 0
            r13 = 0
            r9 = r12 & r18
            if (r9 == 0) goto L_0x0a21
            r7 = 0
            int r11 = com.google.android.gms.internal.measurement.zzee.zzb(r15, r7)
            int r5 = r5 + r11
        L_0x0a34:
            int r3 = r3 + 3
            r9 = r13
            r4 = 0
            r7 = 1048575(0xfffff, float:1.469367E-39)
            r8 = 1
            r11 = 0
            goto L_0x04f9
        L_0x0a3f:
            r10 = 0
            com.google.android.gms.internal.measurement.zzhp<?, ?> r2 = r0.zzakx
            int r2 = zza(r2, (T) r1)
            int r5 = r5 + r2
            boolean r2 = r0.zzako
            if (r2 == 0) goto L_0x0a99
            com.google.android.gms.internal.measurement.zzen<?> r2 = r0.zzaky
            com.google.android.gms.internal.measurement.zzeo r1 = r2.zzh(r1)
            r2 = 0
        L_0x0a52:
            com.google.android.gms.internal.measurement.zzhc<FieldDescriptorType, java.lang.Object> r3 = r1.zzaex
            int r3 = r3.zzwh()
            if (r10 >= r3) goto L_0x0a72
            com.google.android.gms.internal.measurement.zzhc<FieldDescriptorType, java.lang.Object> r3 = r1.zzaex
            java.util.Map$Entry r3 = r3.zzcf(r10)
            java.lang.Object r4 = r3.getKey()
            com.google.android.gms.internal.measurement.zzeq r4 = (com.google.android.gms.internal.measurement.zzeq) r4
            java.lang.Object r3 = r3.getValue()
            int r3 = com.google.android.gms.internal.measurement.zzeo.zzb(r4, r3)
            int r2 = r2 + r3
            int r10 = r10 + 1
            goto L_0x0a52
        L_0x0a72:
            com.google.android.gms.internal.measurement.zzhc<FieldDescriptorType, java.lang.Object> r1 = r1.zzaex
            java.lang.Iterable r1 = r1.zzwi()
            java.util.Iterator r1 = r1.iterator()
        L_0x0a7c:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto L_0x0a98
            java.lang.Object r3 = r1.next()
            java.util.Map$Entry r3 = (java.util.Map.Entry) r3
            java.lang.Object r4 = r3.getKey()
            com.google.android.gms.internal.measurement.zzeq r4 = (com.google.android.gms.internal.measurement.zzeq) r4
            java.lang.Object r3 = r3.getValue()
            int r3 = com.google.android.gms.internal.measurement.zzeo.zzb(r4, r3)
            int r2 = r2 + r3
            goto L_0x0a7c
        L_0x0a98:
            int r5 = r5 + r2
        L_0x0a99:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzgm.zzt(java.lang.Object):int");
    }

    private static <UT, UB> int zza(zzhp<UT, UB> zzhp, T t) {
        return zzhp.zzt(zzhp.zzx(t));
    }

    private static List<?> zze(Object obj, long j) {
        return (List) zzhv.zzp(obj, j);
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x003b  */
    /* JADX WARNING: Removed duplicated region for block: B:163:0x0513  */
    /* JADX WARNING: Removed duplicated region for block: B:178:0x0553  */
    /* JADX WARNING: Removed duplicated region for block: B:331:0x0a2b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(T r14, com.google.android.gms.internal.measurement.zzim r15) throws java.io.IOException {
        /*
            r13 = this;
            int r0 = r15.zztk()
            int r1 = com.google.android.gms.internal.measurement.zzey.zzd.zzaip
            r2 = 267386880(0xff00000, float:2.3665827E-29)
            r3 = 0
            r4 = 1
            r5 = 0
            r6 = 1048575(0xfffff, float:1.469367E-39)
            if (r0 != r1) goto L_0x0529
            com.google.android.gms.internal.measurement.zzhp<?, ?> r0 = r13.zzakx
            zza(r0, (T) r14, r15)
            boolean r0 = r13.zzako
            if (r0 == 0) goto L_0x0032
            com.google.android.gms.internal.measurement.zzen<?> r0 = r13.zzaky
            com.google.android.gms.internal.measurement.zzeo r0 = r0.zzh(r14)
            com.google.android.gms.internal.measurement.zzhc<FieldDescriptorType, java.lang.Object> r1 = r0.zzaex
            boolean r1 = r1.isEmpty()
            if (r1 != 0) goto L_0x0032
            java.util.Iterator r0 = r0.descendingIterator()
            java.lang.Object r1 = r0.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            goto L_0x0034
        L_0x0032:
            r0 = r3
            r1 = r0
        L_0x0034:
            int[] r7 = r13.zzakj
            int r7 = r7.length
            int r7 = r7 + -3
        L_0x0039:
            if (r7 < 0) goto L_0x0511
            int r8 = r13.zzca(r7)
            int[] r9 = r13.zzakj
            r9 = r9[r7]
        L_0x0043:
            if (r1 == 0) goto L_0x0061
            com.google.android.gms.internal.measurement.zzen<?> r10 = r13.zzaky
            int r10 = r10.zza(r1)
            if (r10 <= r9) goto L_0x0061
            com.google.android.gms.internal.measurement.zzen<?> r10 = r13.zzaky
            r10.zza(r15, r1)
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x005f
            java.lang.Object r1 = r0.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            goto L_0x0043
        L_0x005f:
            r1 = r3
            goto L_0x0043
        L_0x0061:
            r10 = r8 & r2
            int r10 = r10 >>> 20
            switch(r10) {
                case 0: goto L_0x04fe;
                case 1: goto L_0x04ee;
                case 2: goto L_0x04de;
                case 3: goto L_0x04ce;
                case 4: goto L_0x04be;
                case 5: goto L_0x04ae;
                case 6: goto L_0x049e;
                case 7: goto L_0x048d;
                case 8: goto L_0x047c;
                case 9: goto L_0x0467;
                case 10: goto L_0x0454;
                case 11: goto L_0x0443;
                case 12: goto L_0x0432;
                case 13: goto L_0x0421;
                case 14: goto L_0x0410;
                case 15: goto L_0x03ff;
                case 16: goto L_0x03ee;
                case 17: goto L_0x03d9;
                case 18: goto L_0x03c8;
                case 19: goto L_0x03b7;
                case 20: goto L_0x03a6;
                case 21: goto L_0x0395;
                case 22: goto L_0x0384;
                case 23: goto L_0x0373;
                case 24: goto L_0x0362;
                case 25: goto L_0x0351;
                case 26: goto L_0x0340;
                case 27: goto L_0x032b;
                case 28: goto L_0x031a;
                case 29: goto L_0x0309;
                case 30: goto L_0x02f8;
                case 31: goto L_0x02e7;
                case 32: goto L_0x02d6;
                case 33: goto L_0x02c5;
                case 34: goto L_0x02b4;
                case 35: goto L_0x02a3;
                case 36: goto L_0x0292;
                case 37: goto L_0x0281;
                case 38: goto L_0x0270;
                case 39: goto L_0x025f;
                case 40: goto L_0x024e;
                case 41: goto L_0x023d;
                case 42: goto L_0x022c;
                case 43: goto L_0x021b;
                case 44: goto L_0x020a;
                case 45: goto L_0x01f9;
                case 46: goto L_0x01e8;
                case 47: goto L_0x01d7;
                case 48: goto L_0x01c6;
                case 49: goto L_0x01b1;
                case 50: goto L_0x01a6;
                case 51: goto L_0x0195;
                case 52: goto L_0x0184;
                case 53: goto L_0x0173;
                case 54: goto L_0x0162;
                case 55: goto L_0x0151;
                case 56: goto L_0x0140;
                case 57: goto L_0x012f;
                case 58: goto L_0x011e;
                case 59: goto L_0x010d;
                case 60: goto L_0x00f8;
                case 61: goto L_0x00e5;
                case 62: goto L_0x00d4;
                case 63: goto L_0x00c3;
                case 64: goto L_0x00b2;
                case 65: goto L_0x00a1;
                case 66: goto L_0x0090;
                case 67: goto L_0x007f;
                case 68: goto L_0x006a;
                default: goto L_0x0068;
            }
        L_0x0068:
            goto L_0x050d
        L_0x006a:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzhv.zzp(r14, r10)
            com.google.android.gms.internal.measurement.zzgx r10 = r13.zzbx(r7)
            r15.zzb(r9, r8, r10)
            goto L_0x050d
        L_0x007f:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = zzi(r14, r10)
            r15.zzb(r9, r10)
            goto L_0x050d
        L_0x0090:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = zzh(r14, r10)
            r15.zze(r9, r8)
            goto L_0x050d
        L_0x00a1:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = zzi(r14, r10)
            r15.zzj(r9, r10)
            goto L_0x050d
        L_0x00b2:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = zzh(r14, r10)
            r15.zzm(r9, r8)
            goto L_0x050d
        L_0x00c3:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = zzh(r14, r10)
            r15.zzn(r9, r8)
            goto L_0x050d
        L_0x00d4:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = zzh(r14, r10)
            r15.zzd(r9, r8)
            goto L_0x050d
        L_0x00e5:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzhv.zzp(r14, r10)
            com.google.android.gms.internal.measurement.zzdp r8 = (com.google.android.gms.internal.measurement.zzdp) r8
            r15.zza(r9, r8)
            goto L_0x050d
        L_0x00f8:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzhv.zzp(r14, r10)
            com.google.android.gms.internal.measurement.zzgx r10 = r13.zzbx(r7)
            r15.zza(r9, r8, r10)
            goto L_0x050d
        L_0x010d:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzhv.zzp(r14, r10)
            zza(r9, r8, r15)
            goto L_0x050d
        L_0x011e:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            boolean r8 = zzj(r14, r10)
            r15.zzb(r9, r8)
            goto L_0x050d
        L_0x012f:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = zzh(r14, r10)
            r15.zzf(r9, r8)
            goto L_0x050d
        L_0x0140:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = zzi(r14, r10)
            r15.zzc(r9, r10)
            goto L_0x050d
        L_0x0151:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = zzh(r14, r10)
            r15.zzc(r9, r8)
            goto L_0x050d
        L_0x0162:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = zzi(r14, r10)
            r15.zza(r9, r10)
            goto L_0x050d
        L_0x0173:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = zzi(r14, r10)
            r15.zzi(r9, r10)
            goto L_0x050d
        L_0x0184:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            float r8 = zzg(r14, r10)
            r15.zza(r9, r8)
            goto L_0x050d
        L_0x0195:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            double r10 = zzf(r14, r10)
            r15.zza(r9, r10)
            goto L_0x050d
        L_0x01a6:
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzhv.zzp(r14, r10)
            r13.zza(r15, r9, r8, r7)
            goto L_0x050d
        L_0x01b1:
            int[] r9 = r13.zzakj
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzhv.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.measurement.zzgx r10 = r13.zzbx(r7)
            com.google.android.gms.internal.measurement.zzgz.zzb(r9, r8, r15, r10)
            goto L_0x050d
        L_0x01c6:
            int[] r9 = r13.zzakj
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzhv.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.measurement.zzgz.zze(r9, r8, r15, r4)
            goto L_0x050d
        L_0x01d7:
            int[] r9 = r13.zzakj
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzhv.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.measurement.zzgz.zzj(r9, r8, r15, r4)
            goto L_0x050d
        L_0x01e8:
            int[] r9 = r13.zzakj
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzhv.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.measurement.zzgz.zzg(r9, r8, r15, r4)
            goto L_0x050d
        L_0x01f9:
            int[] r9 = r13.zzakj
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzhv.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.measurement.zzgz.zzl(r9, r8, r15, r4)
            goto L_0x050d
        L_0x020a:
            int[] r9 = r13.zzakj
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzhv.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.measurement.zzgz.zzm(r9, r8, r15, r4)
            goto L_0x050d
        L_0x021b:
            int[] r9 = r13.zzakj
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzhv.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.measurement.zzgz.zzi(r9, r8, r15, r4)
            goto L_0x050d
        L_0x022c:
            int[] r9 = r13.zzakj
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzhv.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.measurement.zzgz.zzn(r9, r8, r15, r4)
            goto L_0x050d
        L_0x023d:
            int[] r9 = r13.zzakj
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzhv.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.measurement.zzgz.zzk(r9, r8, r15, r4)
            goto L_0x050d
        L_0x024e:
            int[] r9 = r13.zzakj
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzhv.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.measurement.zzgz.zzf(r9, r8, r15, r4)
            goto L_0x050d
        L_0x025f:
            int[] r9 = r13.zzakj
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzhv.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.measurement.zzgz.zzh(r9, r8, r15, r4)
            goto L_0x050d
        L_0x0270:
            int[] r9 = r13.zzakj
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzhv.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.measurement.zzgz.zzd(r9, r8, r15, r4)
            goto L_0x050d
        L_0x0281:
            int[] r9 = r13.zzakj
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzhv.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.measurement.zzgz.zzc(r9, r8, r15, r4)
            goto L_0x050d
        L_0x0292:
            int[] r9 = r13.zzakj
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzhv.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.measurement.zzgz.zzb(r9, r8, r15, r4)
            goto L_0x050d
        L_0x02a3:
            int[] r9 = r13.zzakj
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzhv.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.measurement.zzgz.zza(r9, r8, r15, r4)
            goto L_0x050d
        L_0x02b4:
            int[] r9 = r13.zzakj
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzhv.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.measurement.zzgz.zze(r9, r8, r15, r5)
            goto L_0x050d
        L_0x02c5:
            int[] r9 = r13.zzakj
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzhv.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.measurement.zzgz.zzj(r9, r8, r15, r5)
            goto L_0x050d
        L_0x02d6:
            int[] r9 = r13.zzakj
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzhv.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.measurement.zzgz.zzg(r9, r8, r15, r5)
            goto L_0x050d
        L_0x02e7:
            int[] r9 = r13.zzakj
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzhv.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.measurement.zzgz.zzl(r9, r8, r15, r5)
            goto L_0x050d
        L_0x02f8:
            int[] r9 = r13.zzakj
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzhv.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.measurement.zzgz.zzm(r9, r8, r15, r5)
            goto L_0x050d
        L_0x0309:
            int[] r9 = r13.zzakj
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzhv.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.measurement.zzgz.zzi(r9, r8, r15, r5)
            goto L_0x050d
        L_0x031a:
            int[] r9 = r13.zzakj
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzhv.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.measurement.zzgz.zzb(r9, r8, r15)
            goto L_0x050d
        L_0x032b:
            int[] r9 = r13.zzakj
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzhv.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.measurement.zzgx r10 = r13.zzbx(r7)
            com.google.android.gms.internal.measurement.zzgz.zza(r9, r8, r15, r10)
            goto L_0x050d
        L_0x0340:
            int[] r9 = r13.zzakj
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzhv.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.measurement.zzgz.zza(r9, r8, r15)
            goto L_0x050d
        L_0x0351:
            int[] r9 = r13.zzakj
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzhv.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.measurement.zzgz.zzn(r9, r8, r15, r5)
            goto L_0x050d
        L_0x0362:
            int[] r9 = r13.zzakj
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzhv.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.measurement.zzgz.zzk(r9, r8, r15, r5)
            goto L_0x050d
        L_0x0373:
            int[] r9 = r13.zzakj
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzhv.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.measurement.zzgz.zzf(r9, r8, r15, r5)
            goto L_0x050d
        L_0x0384:
            int[] r9 = r13.zzakj
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzhv.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.measurement.zzgz.zzh(r9, r8, r15, r5)
            goto L_0x050d
        L_0x0395:
            int[] r9 = r13.zzakj
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzhv.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.measurement.zzgz.zzd(r9, r8, r15, r5)
            goto L_0x050d
        L_0x03a6:
            int[] r9 = r13.zzakj
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzhv.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.measurement.zzgz.zzc(r9, r8, r15, r5)
            goto L_0x050d
        L_0x03b7:
            int[] r9 = r13.zzakj
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzhv.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.measurement.zzgz.zzb(r9, r8, r15, r5)
            goto L_0x050d
        L_0x03c8:
            int[] r9 = r13.zzakj
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzhv.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.measurement.zzgz.zza(r9, r8, r15, r5)
            goto L_0x050d
        L_0x03d9:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzhv.zzp(r14, r10)
            com.google.android.gms.internal.measurement.zzgx r10 = r13.zzbx(r7)
            r15.zzb(r9, r8, r10)
            goto L_0x050d
        L_0x03ee:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = com.google.android.gms.internal.measurement.zzhv.zzl(r14, r10)
            r15.zzb(r9, r10)
            goto L_0x050d
        L_0x03ff:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.measurement.zzhv.zzk(r14, r10)
            r15.zze(r9, r8)
            goto L_0x050d
        L_0x0410:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = com.google.android.gms.internal.measurement.zzhv.zzl(r14, r10)
            r15.zzj(r9, r10)
            goto L_0x050d
        L_0x0421:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.measurement.zzhv.zzk(r14, r10)
            r15.zzm(r9, r8)
            goto L_0x050d
        L_0x0432:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.measurement.zzhv.zzk(r14, r10)
            r15.zzn(r9, r8)
            goto L_0x050d
        L_0x0443:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.measurement.zzhv.zzk(r14, r10)
            r15.zzd(r9, r8)
            goto L_0x050d
        L_0x0454:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzhv.zzp(r14, r10)
            com.google.android.gms.internal.measurement.zzdp r8 = (com.google.android.gms.internal.measurement.zzdp) r8
            r15.zza(r9, r8)
            goto L_0x050d
        L_0x0467:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzhv.zzp(r14, r10)
            com.google.android.gms.internal.measurement.zzgx r10 = r13.zzbx(r7)
            r15.zza(r9, r8, r10)
            goto L_0x050d
        L_0x047c:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzhv.zzp(r14, r10)
            zza(r9, r8, r15)
            goto L_0x050d
        L_0x048d:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            boolean r8 = com.google.android.gms.internal.measurement.zzhv.zzm(r14, r10)
            r15.zzb(r9, r8)
            goto L_0x050d
        L_0x049e:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.measurement.zzhv.zzk(r14, r10)
            r15.zzf(r9, r8)
            goto L_0x050d
        L_0x04ae:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = com.google.android.gms.internal.measurement.zzhv.zzl(r14, r10)
            r15.zzc(r9, r10)
            goto L_0x050d
        L_0x04be:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.measurement.zzhv.zzk(r14, r10)
            r15.zzc(r9, r8)
            goto L_0x050d
        L_0x04ce:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = com.google.android.gms.internal.measurement.zzhv.zzl(r14, r10)
            r15.zza(r9, r10)
            goto L_0x050d
        L_0x04de:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = com.google.android.gms.internal.measurement.zzhv.zzl(r14, r10)
            r15.zzi(r9, r10)
            goto L_0x050d
        L_0x04ee:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            float r8 = com.google.android.gms.internal.measurement.zzhv.zzn(r14, r10)
            r15.zza(r9, r8)
            goto L_0x050d
        L_0x04fe:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            double r10 = com.google.android.gms.internal.measurement.zzhv.zzo(r14, r10)
            r15.zza(r9, r10)
        L_0x050d:
            int r7 = r7 + -3
            goto L_0x0039
        L_0x0511:
            if (r1 == 0) goto L_0x0528
            com.google.android.gms.internal.measurement.zzen<?> r14 = r13.zzaky
            r14.zza(r15, r1)
            boolean r14 = r0.hasNext()
            if (r14 == 0) goto L_0x0526
            java.lang.Object r14 = r0.next()
            java.util.Map$Entry r14 = (java.util.Map.Entry) r14
            r1 = r14
            goto L_0x0511
        L_0x0526:
            r1 = r3
            goto L_0x0511
        L_0x0528:
            return
        L_0x0529:
            boolean r0 = r13.zzakq
            if (r0 == 0) goto L_0x0a46
            boolean r0 = r13.zzako
            if (r0 == 0) goto L_0x054a
            com.google.android.gms.internal.measurement.zzen<?> r0 = r13.zzaky
            com.google.android.gms.internal.measurement.zzeo r0 = r0.zzh(r14)
            com.google.android.gms.internal.measurement.zzhc<FieldDescriptorType, java.lang.Object> r1 = r0.zzaex
            boolean r1 = r1.isEmpty()
            if (r1 != 0) goto L_0x054a
            java.util.Iterator r0 = r0.iterator()
            java.lang.Object r1 = r0.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            goto L_0x054c
        L_0x054a:
            r0 = r3
            r1 = r0
        L_0x054c:
            int[] r7 = r13.zzakj
            int r7 = r7.length
            r8 = r1
            r1 = 0
        L_0x0551:
            if (r1 >= r7) goto L_0x0a29
            int r9 = r13.zzca(r1)
            int[] r10 = r13.zzakj
            r10 = r10[r1]
        L_0x055b:
            if (r8 == 0) goto L_0x0579
            com.google.android.gms.internal.measurement.zzen<?> r11 = r13.zzaky
            int r11 = r11.zza(r8)
            if (r11 > r10) goto L_0x0579
            com.google.android.gms.internal.measurement.zzen<?> r11 = r13.zzaky
            r11.zza(r15, r8)
            boolean r8 = r0.hasNext()
            if (r8 == 0) goto L_0x0577
            java.lang.Object r8 = r0.next()
            java.util.Map$Entry r8 = (java.util.Map.Entry) r8
            goto L_0x055b
        L_0x0577:
            r8 = r3
            goto L_0x055b
        L_0x0579:
            r11 = r9 & r2
            int r11 = r11 >>> 20
            switch(r11) {
                case 0: goto L_0x0a16;
                case 1: goto L_0x0a06;
                case 2: goto L_0x09f6;
                case 3: goto L_0x09e6;
                case 4: goto L_0x09d6;
                case 5: goto L_0x09c6;
                case 6: goto L_0x09b6;
                case 7: goto L_0x09a5;
                case 8: goto L_0x0994;
                case 9: goto L_0x097f;
                case 10: goto L_0x096c;
                case 11: goto L_0x095b;
                case 12: goto L_0x094a;
                case 13: goto L_0x0939;
                case 14: goto L_0x0928;
                case 15: goto L_0x0917;
                case 16: goto L_0x0906;
                case 17: goto L_0x08f1;
                case 18: goto L_0x08e0;
                case 19: goto L_0x08cf;
                case 20: goto L_0x08be;
                case 21: goto L_0x08ad;
                case 22: goto L_0x089c;
                case 23: goto L_0x088b;
                case 24: goto L_0x087a;
                case 25: goto L_0x0869;
                case 26: goto L_0x0858;
                case 27: goto L_0x0843;
                case 28: goto L_0x0832;
                case 29: goto L_0x0821;
                case 30: goto L_0x0810;
                case 31: goto L_0x07ff;
                case 32: goto L_0x07ee;
                case 33: goto L_0x07dd;
                case 34: goto L_0x07cc;
                case 35: goto L_0x07bb;
                case 36: goto L_0x07aa;
                case 37: goto L_0x0799;
                case 38: goto L_0x0788;
                case 39: goto L_0x0777;
                case 40: goto L_0x0766;
                case 41: goto L_0x0755;
                case 42: goto L_0x0744;
                case 43: goto L_0x0733;
                case 44: goto L_0x0722;
                case 45: goto L_0x0711;
                case 46: goto L_0x0700;
                case 47: goto L_0x06ef;
                case 48: goto L_0x06de;
                case 49: goto L_0x06c9;
                case 50: goto L_0x06be;
                case 51: goto L_0x06ad;
                case 52: goto L_0x069c;
                case 53: goto L_0x068b;
                case 54: goto L_0x067a;
                case 55: goto L_0x0669;
                case 56: goto L_0x0658;
                case 57: goto L_0x0647;
                case 58: goto L_0x0636;
                case 59: goto L_0x0625;
                case 60: goto L_0x0610;
                case 61: goto L_0x05fd;
                case 62: goto L_0x05ec;
                case 63: goto L_0x05db;
                case 64: goto L_0x05ca;
                case 65: goto L_0x05b9;
                case 66: goto L_0x05a8;
                case 67: goto L_0x0597;
                case 68: goto L_0x0582;
                default: goto L_0x0580;
            }
        L_0x0580:
            goto L_0x0a25
        L_0x0582:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0a25
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.measurement.zzhv.zzp(r14, r11)
            com.google.android.gms.internal.measurement.zzgx r11 = r13.zzbx(r1)
            r15.zzb(r10, r9, r11)
            goto L_0x0a25
        L_0x0597:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0a25
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = zzi(r14, r11)
            r15.zzb(r10, r11)
            goto L_0x0a25
        L_0x05a8:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0a25
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = zzh(r14, r11)
            r15.zze(r10, r9)
            goto L_0x0a25
        L_0x05b9:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0a25
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = zzi(r14, r11)
            r15.zzj(r10, r11)
            goto L_0x0a25
        L_0x05ca:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0a25
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = zzh(r14, r11)
            r15.zzm(r10, r9)
            goto L_0x0a25
        L_0x05db:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0a25
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = zzh(r14, r11)
            r15.zzn(r10, r9)
            goto L_0x0a25
        L_0x05ec:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0a25
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = zzh(r14, r11)
            r15.zzd(r10, r9)
            goto L_0x0a25
        L_0x05fd:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0a25
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.measurement.zzhv.zzp(r14, r11)
            com.google.android.gms.internal.measurement.zzdp r9 = (com.google.android.gms.internal.measurement.zzdp) r9
            r15.zza(r10, r9)
            goto L_0x0a25
        L_0x0610:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0a25
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.measurement.zzhv.zzp(r14, r11)
            com.google.android.gms.internal.measurement.zzgx r11 = r13.zzbx(r1)
            r15.zza(r10, r9, r11)
            goto L_0x0a25
        L_0x0625:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0a25
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.measurement.zzhv.zzp(r14, r11)
            zza(r10, r9, r15)
            goto L_0x0a25
        L_0x0636:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0a25
            r9 = r9 & r6
            long r11 = (long) r9
            boolean r9 = zzj(r14, r11)
            r15.zzb(r10, r9)
            goto L_0x0a25
        L_0x0647:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0a25
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = zzh(r14, r11)
            r15.zzf(r10, r9)
            goto L_0x0a25
        L_0x0658:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0a25
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = zzi(r14, r11)
            r15.zzc(r10, r11)
            goto L_0x0a25
        L_0x0669:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0a25
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = zzh(r14, r11)
            r15.zzc(r10, r9)
            goto L_0x0a25
        L_0x067a:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0a25
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = zzi(r14, r11)
            r15.zza(r10, r11)
            goto L_0x0a25
        L_0x068b:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0a25
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = zzi(r14, r11)
            r15.zzi(r10, r11)
            goto L_0x0a25
        L_0x069c:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0a25
            r9 = r9 & r6
            long r11 = (long) r9
            float r9 = zzg(r14, r11)
            r15.zza(r10, r9)
            goto L_0x0a25
        L_0x06ad:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0a25
            r9 = r9 & r6
            long r11 = (long) r9
            double r11 = zzf(r14, r11)
            r15.zza(r10, r11)
            goto L_0x0a25
        L_0x06be:
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.measurement.zzhv.zzp(r14, r11)
            r13.zza(r15, r10, r9, r1)
            goto L_0x0a25
        L_0x06c9:
            int[] r10 = r13.zzakj
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.measurement.zzhv.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.measurement.zzgx r11 = r13.zzbx(r1)
            com.google.android.gms.internal.measurement.zzgz.zzb(r10, r9, r15, r11)
            goto L_0x0a25
        L_0x06de:
            int[] r10 = r13.zzakj
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.measurement.zzhv.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.measurement.zzgz.zze(r10, r9, r15, r4)
            goto L_0x0a25
        L_0x06ef:
            int[] r10 = r13.zzakj
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.measurement.zzhv.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.measurement.zzgz.zzj(r10, r9, r15, r4)
            goto L_0x0a25
        L_0x0700:
            int[] r10 = r13.zzakj
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.measurement.zzhv.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.measurement.zzgz.zzg(r10, r9, r15, r4)
            goto L_0x0a25
        L_0x0711:
            int[] r10 = r13.zzakj
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.measurement.zzhv.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.measurement.zzgz.zzl(r10, r9, r15, r4)
            goto L_0x0a25
        L_0x0722:
            int[] r10 = r13.zzakj
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.measurement.zzhv.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.measurement.zzgz.zzm(r10, r9, r15, r4)
            goto L_0x0a25
        L_0x0733:
            int[] r10 = r13.zzakj
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.measurement.zzhv.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.measurement.zzgz.zzi(r10, r9, r15, r4)
            goto L_0x0a25
        L_0x0744:
            int[] r10 = r13.zzakj
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.measurement.zzhv.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.measurement.zzgz.zzn(r10, r9, r15, r4)
            goto L_0x0a25
        L_0x0755:
            int[] r10 = r13.zzakj
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.measurement.zzhv.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.measurement.zzgz.zzk(r10, r9, r15, r4)
            goto L_0x0a25
        L_0x0766:
            int[] r10 = r13.zzakj
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.measurement.zzhv.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.measurement.zzgz.zzf(r10, r9, r15, r4)
            goto L_0x0a25
        L_0x0777:
            int[] r10 = r13.zzakj
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.measurement.zzhv.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.measurement.zzgz.zzh(r10, r9, r15, r4)
            goto L_0x0a25
        L_0x0788:
            int[] r10 = r13.zzakj
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.measurement.zzhv.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.measurement.zzgz.zzd(r10, r9, r15, r4)
            goto L_0x0a25
        L_0x0799:
            int[] r10 = r13.zzakj
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.measurement.zzhv.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.measurement.zzgz.zzc(r10, r9, r15, r4)
            goto L_0x0a25
        L_0x07aa:
            int[] r10 = r13.zzakj
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.measurement.zzhv.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.measurement.zzgz.zzb(r10, r9, r15, r4)
            goto L_0x0a25
        L_0x07bb:
            int[] r10 = r13.zzakj
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.measurement.zzhv.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.measurement.zzgz.zza(r10, r9, r15, r4)
            goto L_0x0a25
        L_0x07cc:
            int[] r10 = r13.zzakj
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.measurement.zzhv.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.measurement.zzgz.zze(r10, r9, r15, r5)
            goto L_0x0a25
        L_0x07dd:
            int[] r10 = r13.zzakj
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.measurement.zzhv.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.measurement.zzgz.zzj(r10, r9, r15, r5)
            goto L_0x0a25
        L_0x07ee:
            int[] r10 = r13.zzakj
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.measurement.zzhv.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.measurement.zzgz.zzg(r10, r9, r15, r5)
            goto L_0x0a25
        L_0x07ff:
            int[] r10 = r13.zzakj
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.measurement.zzhv.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.measurement.zzgz.zzl(r10, r9, r15, r5)
            goto L_0x0a25
        L_0x0810:
            int[] r10 = r13.zzakj
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.measurement.zzhv.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.measurement.zzgz.zzm(r10, r9, r15, r5)
            goto L_0x0a25
        L_0x0821:
            int[] r10 = r13.zzakj
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.measurement.zzhv.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.measurement.zzgz.zzi(r10, r9, r15, r5)
            goto L_0x0a25
        L_0x0832:
            int[] r10 = r13.zzakj
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.measurement.zzhv.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.measurement.zzgz.zzb(r10, r9, r15)
            goto L_0x0a25
        L_0x0843:
            int[] r10 = r13.zzakj
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.measurement.zzhv.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.measurement.zzgx r11 = r13.zzbx(r1)
            com.google.android.gms.internal.measurement.zzgz.zza(r10, r9, r15, r11)
            goto L_0x0a25
        L_0x0858:
            int[] r10 = r13.zzakj
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.measurement.zzhv.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.measurement.zzgz.zza(r10, r9, r15)
            goto L_0x0a25
        L_0x0869:
            int[] r10 = r13.zzakj
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.measurement.zzhv.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.measurement.zzgz.zzn(r10, r9, r15, r5)
            goto L_0x0a25
        L_0x087a:
            int[] r10 = r13.zzakj
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.measurement.zzhv.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.measurement.zzgz.zzk(r10, r9, r15, r5)
            goto L_0x0a25
        L_0x088b:
            int[] r10 = r13.zzakj
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.measurement.zzhv.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.measurement.zzgz.zzf(r10, r9, r15, r5)
            goto L_0x0a25
        L_0x089c:
            int[] r10 = r13.zzakj
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.measurement.zzhv.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.measurement.zzgz.zzh(r10, r9, r15, r5)
            goto L_0x0a25
        L_0x08ad:
            int[] r10 = r13.zzakj
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.measurement.zzhv.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.measurement.zzgz.zzd(r10, r9, r15, r5)
            goto L_0x0a25
        L_0x08be:
            int[] r10 = r13.zzakj
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.measurement.zzhv.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.measurement.zzgz.zzc(r10, r9, r15, r5)
            goto L_0x0a25
        L_0x08cf:
            int[] r10 = r13.zzakj
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.measurement.zzhv.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.measurement.zzgz.zzb(r10, r9, r15, r5)
            goto L_0x0a25
        L_0x08e0:
            int[] r10 = r13.zzakj
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.measurement.zzhv.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.measurement.zzgz.zza(r10, r9, r15, r5)
            goto L_0x0a25
        L_0x08f1:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0a25
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.measurement.zzhv.zzp(r14, r11)
            com.google.android.gms.internal.measurement.zzgx r11 = r13.zzbx(r1)
            r15.zzb(r10, r9, r11)
            goto L_0x0a25
        L_0x0906:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0a25
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = com.google.android.gms.internal.measurement.zzhv.zzl(r14, r11)
            r15.zzb(r10, r11)
            goto L_0x0a25
        L_0x0917:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0a25
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = com.google.android.gms.internal.measurement.zzhv.zzk(r14, r11)
            r15.zze(r10, r9)
            goto L_0x0a25
        L_0x0928:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0a25
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = com.google.android.gms.internal.measurement.zzhv.zzl(r14, r11)
            r15.zzj(r10, r11)
            goto L_0x0a25
        L_0x0939:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0a25
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = com.google.android.gms.internal.measurement.zzhv.zzk(r14, r11)
            r15.zzm(r10, r9)
            goto L_0x0a25
        L_0x094a:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0a25
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = com.google.android.gms.internal.measurement.zzhv.zzk(r14, r11)
            r15.zzn(r10, r9)
            goto L_0x0a25
        L_0x095b:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0a25
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = com.google.android.gms.internal.measurement.zzhv.zzk(r14, r11)
            r15.zzd(r10, r9)
            goto L_0x0a25
        L_0x096c:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0a25
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.measurement.zzhv.zzp(r14, r11)
            com.google.android.gms.internal.measurement.zzdp r9 = (com.google.android.gms.internal.measurement.zzdp) r9
            r15.zza(r10, r9)
            goto L_0x0a25
        L_0x097f:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0a25
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.measurement.zzhv.zzp(r14, r11)
            com.google.android.gms.internal.measurement.zzgx r11 = r13.zzbx(r1)
            r15.zza(r10, r9, r11)
            goto L_0x0a25
        L_0x0994:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0a25
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.measurement.zzhv.zzp(r14, r11)
            zza(r10, r9, r15)
            goto L_0x0a25
        L_0x09a5:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0a25
            r9 = r9 & r6
            long r11 = (long) r9
            boolean r9 = com.google.android.gms.internal.measurement.zzhv.zzm(r14, r11)
            r15.zzb(r10, r9)
            goto L_0x0a25
        L_0x09b6:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0a25
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = com.google.android.gms.internal.measurement.zzhv.zzk(r14, r11)
            r15.zzf(r10, r9)
            goto L_0x0a25
        L_0x09c6:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0a25
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = com.google.android.gms.internal.measurement.zzhv.zzl(r14, r11)
            r15.zzc(r10, r11)
            goto L_0x0a25
        L_0x09d6:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0a25
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = com.google.android.gms.internal.measurement.zzhv.zzk(r14, r11)
            r15.zzc(r10, r9)
            goto L_0x0a25
        L_0x09e6:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0a25
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = com.google.android.gms.internal.measurement.zzhv.zzl(r14, r11)
            r15.zza(r10, r11)
            goto L_0x0a25
        L_0x09f6:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0a25
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = com.google.android.gms.internal.measurement.zzhv.zzl(r14, r11)
            r15.zzi(r10, r11)
            goto L_0x0a25
        L_0x0a06:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0a25
            r9 = r9 & r6
            long r11 = (long) r9
            float r9 = com.google.android.gms.internal.measurement.zzhv.zzn(r14, r11)
            r15.zza(r10, r9)
            goto L_0x0a25
        L_0x0a16:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0a25
            r9 = r9 & r6
            long r11 = (long) r9
            double r11 = com.google.android.gms.internal.measurement.zzhv.zzo(r14, r11)
            r15.zza(r10, r11)
        L_0x0a25:
            int r1 = r1 + 3
            goto L_0x0551
        L_0x0a29:
            if (r8 == 0) goto L_0x0a40
            com.google.android.gms.internal.measurement.zzen<?> r1 = r13.zzaky
            r1.zza(r15, r8)
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0a3e
            java.lang.Object r1 = r0.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            r8 = r1
            goto L_0x0a29
        L_0x0a3e:
            r8 = r3
            goto L_0x0a29
        L_0x0a40:
            com.google.android.gms.internal.measurement.zzhp<?, ?> r0 = r13.zzakx
            zza(r0, (T) r14, r15)
            return
        L_0x0a46:
            r13.zzb((T) r14, r15)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzgm.zza(java.lang.Object, com.google.android.gms.internal.measurement.zzim):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:172:0x04b5  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0030  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zzb(T r19, com.google.android.gms.internal.measurement.zzim r20) throws java.io.IOException {
        /*
            r18 = this;
            r0 = r18
            r1 = r19
            r2 = r20
            boolean r3 = r0.zzako
            if (r3 == 0) goto L_0x0023
            com.google.android.gms.internal.measurement.zzen<?> r3 = r0.zzaky
            com.google.android.gms.internal.measurement.zzeo r3 = r3.zzh(r1)
            com.google.android.gms.internal.measurement.zzhc<FieldDescriptorType, java.lang.Object> r5 = r3.zzaex
            boolean r5 = r5.isEmpty()
            if (r5 != 0) goto L_0x0023
            java.util.Iterator r3 = r3.iterator()
            java.lang.Object r5 = r3.next()
            java.util.Map$Entry r5 = (java.util.Map.Entry) r5
            goto L_0x0025
        L_0x0023:
            r3 = 0
            r5 = 0
        L_0x0025:
            r6 = -1
            int[] r7 = r0.zzakj
            int r7 = r7.length
            sun.misc.Unsafe r8 = zzaki
            r10 = r5
            r5 = 0
            r11 = 0
        L_0x002e:
            if (r5 >= r7) goto L_0x04af
            int r12 = r0.zzca(r5)
            int[] r13 = r0.zzakj
            r14 = r13[r5]
            r15 = 267386880(0xff00000, float:2.3665827E-29)
            r15 = r15 & r12
            int r15 = r15 >>> 20
            boolean r4 = r0.zzakq
            r16 = 1048575(0xfffff, float:1.469367E-39)
            if (r4 != 0) goto L_0x0064
            r4 = 17
            if (r15 > r4) goto L_0x0064
            int r4 = r5 + 2
            r4 = r13[r4]
            r13 = r4 & r16
            if (r13 == r6) goto L_0x0058
            r17 = r10
            long r9 = (long) r13
            int r11 = r8.getInt(r1, r9)
            goto L_0x005b
        L_0x0058:
            r17 = r10
            r13 = r6
        L_0x005b:
            int r4 = r4 >>> 20
            r6 = 1
            int r9 = r6 << r4
            r6 = r13
            r10 = r17
            goto L_0x0069
        L_0x0064:
            r17 = r10
            r10 = r17
            r9 = 0
        L_0x0069:
            if (r10 == 0) goto L_0x0088
            com.google.android.gms.internal.measurement.zzen<?> r4 = r0.zzaky
            int r4 = r4.zza(r10)
            if (r4 > r14) goto L_0x0088
            com.google.android.gms.internal.measurement.zzen<?> r4 = r0.zzaky
            r4.zza(r2, r10)
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x0086
            java.lang.Object r4 = r3.next()
            java.util.Map$Entry r4 = (java.util.Map.Entry) r4
            r10 = r4
            goto L_0x0069
        L_0x0086:
            r10 = 0
            goto L_0x0069
        L_0x0088:
            r4 = r12 & r16
            long r12 = (long) r4
            switch(r15) {
                case 0: goto L_0x049f;
                case 1: goto L_0x0492;
                case 2: goto L_0x0485;
                case 3: goto L_0x0478;
                case 4: goto L_0x046b;
                case 5: goto L_0x045e;
                case 6: goto L_0x0451;
                case 7: goto L_0x0444;
                case 8: goto L_0x0436;
                case 9: goto L_0x0424;
                case 10: goto L_0x0414;
                case 11: goto L_0x0406;
                case 12: goto L_0x03f8;
                case 13: goto L_0x03ea;
                case 14: goto L_0x03dc;
                case 15: goto L_0x03ce;
                case 16: goto L_0x03c0;
                case 17: goto L_0x03ae;
                case 18: goto L_0x039e;
                case 19: goto L_0x038e;
                case 20: goto L_0x037e;
                case 21: goto L_0x036e;
                case 22: goto L_0x035e;
                case 23: goto L_0x034e;
                case 24: goto L_0x033e;
                case 25: goto L_0x032e;
                case 26: goto L_0x031f;
                case 27: goto L_0x030c;
                case 28: goto L_0x02fd;
                case 29: goto L_0x02ed;
                case 30: goto L_0x02dd;
                case 31: goto L_0x02cd;
                case 32: goto L_0x02bd;
                case 33: goto L_0x02ad;
                case 34: goto L_0x029d;
                case 35: goto L_0x028d;
                case 36: goto L_0x027d;
                case 37: goto L_0x026d;
                case 38: goto L_0x025d;
                case 39: goto L_0x024d;
                case 40: goto L_0x023d;
                case 41: goto L_0x022d;
                case 42: goto L_0x021d;
                case 43: goto L_0x020d;
                case 44: goto L_0x01fd;
                case 45: goto L_0x01ed;
                case 46: goto L_0x01dd;
                case 47: goto L_0x01cd;
                case 48: goto L_0x01bd;
                case 49: goto L_0x01aa;
                case 50: goto L_0x01a1;
                case 51: goto L_0x0192;
                case 52: goto L_0x0183;
                case 53: goto L_0x0174;
                case 54: goto L_0x0165;
                case 55: goto L_0x0156;
                case 56: goto L_0x0147;
                case 57: goto L_0x0138;
                case 58: goto L_0x0129;
                case 59: goto L_0x011a;
                case 60: goto L_0x0107;
                case 61: goto L_0x00f7;
                case 62: goto L_0x00e9;
                case 63: goto L_0x00db;
                case 64: goto L_0x00cd;
                case 65: goto L_0x00bf;
                case 66: goto L_0x00b1;
                case 67: goto L_0x00a3;
                case 68: goto L_0x0091;
                default: goto L_0x008e;
            }
        L_0x008e:
            r15 = 0
            goto L_0x04ab
        L_0x0091:
            boolean r4 = r0.zza((T) r1, r14, r5)
            if (r4 == 0) goto L_0x008e
            java.lang.Object r4 = r8.getObject(r1, r12)
            com.google.android.gms.internal.measurement.zzgx r9 = r0.zzbx(r5)
            r2.zzb(r14, r4, r9)
            goto L_0x008e
        L_0x00a3:
            boolean r4 = r0.zza((T) r1, r14, r5)
            if (r4 == 0) goto L_0x008e
            long r12 = zzi(r1, r12)
            r2.zzb(r14, r12)
            goto L_0x008e
        L_0x00b1:
            boolean r4 = r0.zza((T) r1, r14, r5)
            if (r4 == 0) goto L_0x008e
            int r4 = zzh(r1, r12)
            r2.zze(r14, r4)
            goto L_0x008e
        L_0x00bf:
            boolean r4 = r0.zza((T) r1, r14, r5)
            if (r4 == 0) goto L_0x008e
            long r12 = zzi(r1, r12)
            r2.zzj(r14, r12)
            goto L_0x008e
        L_0x00cd:
            boolean r4 = r0.zza((T) r1, r14, r5)
            if (r4 == 0) goto L_0x008e
            int r4 = zzh(r1, r12)
            r2.zzm(r14, r4)
            goto L_0x008e
        L_0x00db:
            boolean r4 = r0.zza((T) r1, r14, r5)
            if (r4 == 0) goto L_0x008e
            int r4 = zzh(r1, r12)
            r2.zzn(r14, r4)
            goto L_0x008e
        L_0x00e9:
            boolean r4 = r0.zza((T) r1, r14, r5)
            if (r4 == 0) goto L_0x008e
            int r4 = zzh(r1, r12)
            r2.zzd(r14, r4)
            goto L_0x008e
        L_0x00f7:
            boolean r4 = r0.zza((T) r1, r14, r5)
            if (r4 == 0) goto L_0x008e
            java.lang.Object r4 = r8.getObject(r1, r12)
            com.google.android.gms.internal.measurement.zzdp r4 = (com.google.android.gms.internal.measurement.zzdp) r4
            r2.zza(r14, r4)
            goto L_0x008e
        L_0x0107:
            boolean r4 = r0.zza((T) r1, r14, r5)
            if (r4 == 0) goto L_0x008e
            java.lang.Object r4 = r8.getObject(r1, r12)
            com.google.android.gms.internal.measurement.zzgx r9 = r0.zzbx(r5)
            r2.zza(r14, r4, r9)
            goto L_0x008e
        L_0x011a:
            boolean r4 = r0.zza((T) r1, r14, r5)
            if (r4 == 0) goto L_0x008e
            java.lang.Object r4 = r8.getObject(r1, r12)
            zza(r14, r4, r2)
            goto L_0x008e
        L_0x0129:
            boolean r4 = r0.zza((T) r1, r14, r5)
            if (r4 == 0) goto L_0x008e
            boolean r4 = zzj(r1, r12)
            r2.zzb(r14, r4)
            goto L_0x008e
        L_0x0138:
            boolean r4 = r0.zza((T) r1, r14, r5)
            if (r4 == 0) goto L_0x008e
            int r4 = zzh(r1, r12)
            r2.zzf(r14, r4)
            goto L_0x008e
        L_0x0147:
            boolean r4 = r0.zza((T) r1, r14, r5)
            if (r4 == 0) goto L_0x008e
            long r12 = zzi(r1, r12)
            r2.zzc(r14, r12)
            goto L_0x008e
        L_0x0156:
            boolean r4 = r0.zza((T) r1, r14, r5)
            if (r4 == 0) goto L_0x008e
            int r4 = zzh(r1, r12)
            r2.zzc(r14, r4)
            goto L_0x008e
        L_0x0165:
            boolean r4 = r0.zza((T) r1, r14, r5)
            if (r4 == 0) goto L_0x008e
            long r12 = zzi(r1, r12)
            r2.zza(r14, r12)
            goto L_0x008e
        L_0x0174:
            boolean r4 = r0.zza((T) r1, r14, r5)
            if (r4 == 0) goto L_0x008e
            long r12 = zzi(r1, r12)
            r2.zzi(r14, r12)
            goto L_0x008e
        L_0x0183:
            boolean r4 = r0.zza((T) r1, r14, r5)
            if (r4 == 0) goto L_0x008e
            float r4 = zzg(r1, r12)
            r2.zza(r14, r4)
            goto L_0x008e
        L_0x0192:
            boolean r4 = r0.zza((T) r1, r14, r5)
            if (r4 == 0) goto L_0x008e
            double r12 = zzf(r1, r12)
            r2.zza(r14, r12)
            goto L_0x008e
        L_0x01a1:
            java.lang.Object r4 = r8.getObject(r1, r12)
            r0.zza(r2, r14, r4, r5)
            goto L_0x008e
        L_0x01aa:
            int[] r4 = r0.zzakj
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.measurement.zzgx r12 = r0.zzbx(r5)
            com.google.android.gms.internal.measurement.zzgz.zzb(r4, r9, r2, r12)
            goto L_0x008e
        L_0x01bd:
            int[] r4 = r0.zzakj
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            r14 = 1
            com.google.android.gms.internal.measurement.zzgz.zze(r4, r9, r2, r14)
            goto L_0x008e
        L_0x01cd:
            r14 = 1
            int[] r4 = r0.zzakj
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.measurement.zzgz.zzj(r4, r9, r2, r14)
            goto L_0x008e
        L_0x01dd:
            r14 = 1
            int[] r4 = r0.zzakj
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.measurement.zzgz.zzg(r4, r9, r2, r14)
            goto L_0x008e
        L_0x01ed:
            r14 = 1
            int[] r4 = r0.zzakj
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.measurement.zzgz.zzl(r4, r9, r2, r14)
            goto L_0x008e
        L_0x01fd:
            r14 = 1
            int[] r4 = r0.zzakj
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.measurement.zzgz.zzm(r4, r9, r2, r14)
            goto L_0x008e
        L_0x020d:
            r14 = 1
            int[] r4 = r0.zzakj
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.measurement.zzgz.zzi(r4, r9, r2, r14)
            goto L_0x008e
        L_0x021d:
            r14 = 1
            int[] r4 = r0.zzakj
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.measurement.zzgz.zzn(r4, r9, r2, r14)
            goto L_0x008e
        L_0x022d:
            r14 = 1
            int[] r4 = r0.zzakj
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.measurement.zzgz.zzk(r4, r9, r2, r14)
            goto L_0x008e
        L_0x023d:
            r14 = 1
            int[] r4 = r0.zzakj
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.measurement.zzgz.zzf(r4, r9, r2, r14)
            goto L_0x008e
        L_0x024d:
            r14 = 1
            int[] r4 = r0.zzakj
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.measurement.zzgz.zzh(r4, r9, r2, r14)
            goto L_0x008e
        L_0x025d:
            r14 = 1
            int[] r4 = r0.zzakj
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.measurement.zzgz.zzd(r4, r9, r2, r14)
            goto L_0x008e
        L_0x026d:
            r14 = 1
            int[] r4 = r0.zzakj
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.measurement.zzgz.zzc(r4, r9, r2, r14)
            goto L_0x008e
        L_0x027d:
            r14 = 1
            int[] r4 = r0.zzakj
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.measurement.zzgz.zzb(r4, r9, r2, r14)
            goto L_0x008e
        L_0x028d:
            r14 = 1
            int[] r4 = r0.zzakj
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.measurement.zzgz.zza(r4, r9, r2, r14)
            goto L_0x008e
        L_0x029d:
            int[] r4 = r0.zzakj
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            r14 = 0
            com.google.android.gms.internal.measurement.zzgz.zze(r4, r9, r2, r14)
            goto L_0x008e
        L_0x02ad:
            r14 = 0
            int[] r4 = r0.zzakj
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.measurement.zzgz.zzj(r4, r9, r2, r14)
            goto L_0x008e
        L_0x02bd:
            r14 = 0
            int[] r4 = r0.zzakj
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.measurement.zzgz.zzg(r4, r9, r2, r14)
            goto L_0x008e
        L_0x02cd:
            r14 = 0
            int[] r4 = r0.zzakj
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.measurement.zzgz.zzl(r4, r9, r2, r14)
            goto L_0x008e
        L_0x02dd:
            r14 = 0
            int[] r4 = r0.zzakj
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.measurement.zzgz.zzm(r4, r9, r2, r14)
            goto L_0x008e
        L_0x02ed:
            r14 = 0
            int[] r4 = r0.zzakj
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.measurement.zzgz.zzi(r4, r9, r2, r14)
            goto L_0x008e
        L_0x02fd:
            int[] r4 = r0.zzakj
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.measurement.zzgz.zzb(r4, r9, r2)
            goto L_0x008e
        L_0x030c:
            int[] r4 = r0.zzakj
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.measurement.zzgx r12 = r0.zzbx(r5)
            com.google.android.gms.internal.measurement.zzgz.zza(r4, r9, r2, r12)
            goto L_0x008e
        L_0x031f:
            int[] r4 = r0.zzakj
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.measurement.zzgz.zza(r4, r9, r2)
            goto L_0x008e
        L_0x032e:
            int[] r4 = r0.zzakj
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            r15 = 0
            com.google.android.gms.internal.measurement.zzgz.zzn(r4, r9, r2, r15)
            goto L_0x04ab
        L_0x033e:
            r15 = 0
            int[] r4 = r0.zzakj
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.measurement.zzgz.zzk(r4, r9, r2, r15)
            goto L_0x04ab
        L_0x034e:
            r15 = 0
            int[] r4 = r0.zzakj
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.measurement.zzgz.zzf(r4, r9, r2, r15)
            goto L_0x04ab
        L_0x035e:
            r15 = 0
            int[] r4 = r0.zzakj
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.measurement.zzgz.zzh(r4, r9, r2, r15)
            goto L_0x04ab
        L_0x036e:
            r15 = 0
            int[] r4 = r0.zzakj
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.measurement.zzgz.zzd(r4, r9, r2, r15)
            goto L_0x04ab
        L_0x037e:
            r15 = 0
            int[] r4 = r0.zzakj
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.measurement.zzgz.zzc(r4, r9, r2, r15)
            goto L_0x04ab
        L_0x038e:
            r15 = 0
            int[] r4 = r0.zzakj
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.measurement.zzgz.zzb(r4, r9, r2, r15)
            goto L_0x04ab
        L_0x039e:
            r15 = 0
            int[] r4 = r0.zzakj
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.measurement.zzgz.zza(r4, r9, r2, r15)
            goto L_0x04ab
        L_0x03ae:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x04ab
            java.lang.Object r4 = r8.getObject(r1, r12)
            com.google.android.gms.internal.measurement.zzgx r9 = r0.zzbx(r5)
            r2.zzb(r14, r4, r9)
            goto L_0x04ab
        L_0x03c0:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x04ab
            long r12 = r8.getLong(r1, r12)
            r2.zzb(r14, r12)
            goto L_0x04ab
        L_0x03ce:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x04ab
            int r4 = r8.getInt(r1, r12)
            r2.zze(r14, r4)
            goto L_0x04ab
        L_0x03dc:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x04ab
            long r12 = r8.getLong(r1, r12)
            r2.zzj(r14, r12)
            goto L_0x04ab
        L_0x03ea:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x04ab
            int r4 = r8.getInt(r1, r12)
            r2.zzm(r14, r4)
            goto L_0x04ab
        L_0x03f8:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x04ab
            int r4 = r8.getInt(r1, r12)
            r2.zzn(r14, r4)
            goto L_0x04ab
        L_0x0406:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x04ab
            int r4 = r8.getInt(r1, r12)
            r2.zzd(r14, r4)
            goto L_0x04ab
        L_0x0414:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x04ab
            java.lang.Object r4 = r8.getObject(r1, r12)
            com.google.android.gms.internal.measurement.zzdp r4 = (com.google.android.gms.internal.measurement.zzdp) r4
            r2.zza(r14, r4)
            goto L_0x04ab
        L_0x0424:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x04ab
            java.lang.Object r4 = r8.getObject(r1, r12)
            com.google.android.gms.internal.measurement.zzgx r9 = r0.zzbx(r5)
            r2.zza(r14, r4, r9)
            goto L_0x04ab
        L_0x0436:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x04ab
            java.lang.Object r4 = r8.getObject(r1, r12)
            zza(r14, r4, r2)
            goto L_0x04ab
        L_0x0444:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x04ab
            boolean r4 = com.google.android.gms.internal.measurement.zzhv.zzm(r1, r12)
            r2.zzb(r14, r4)
            goto L_0x04ab
        L_0x0451:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x04ab
            int r4 = r8.getInt(r1, r12)
            r2.zzf(r14, r4)
            goto L_0x04ab
        L_0x045e:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x04ab
            long r12 = r8.getLong(r1, r12)
            r2.zzc(r14, r12)
            goto L_0x04ab
        L_0x046b:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x04ab
            int r4 = r8.getInt(r1, r12)
            r2.zzc(r14, r4)
            goto L_0x04ab
        L_0x0478:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x04ab
            long r12 = r8.getLong(r1, r12)
            r2.zza(r14, r12)
            goto L_0x04ab
        L_0x0485:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x04ab
            long r12 = r8.getLong(r1, r12)
            r2.zzi(r14, r12)
            goto L_0x04ab
        L_0x0492:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x04ab
            float r4 = com.google.android.gms.internal.measurement.zzhv.zzn(r1, r12)
            r2.zza(r14, r4)
            goto L_0x04ab
        L_0x049f:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x04ab
            double r12 = com.google.android.gms.internal.measurement.zzhv.zzo(r1, r12)
            r2.zza(r14, r12)
        L_0x04ab:
            int r5 = r5 + 3
            goto L_0x002e
        L_0x04af:
            r17 = r10
            r4 = r17
        L_0x04b3:
            if (r4 == 0) goto L_0x04c9
            com.google.android.gms.internal.measurement.zzen<?> r5 = r0.zzaky
            r5.zza(r2, r4)
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x04c7
            java.lang.Object r4 = r3.next()
            java.util.Map$Entry r4 = (java.util.Map.Entry) r4
            goto L_0x04b3
        L_0x04c7:
            r4 = 0
            goto L_0x04b3
        L_0x04c9:
            com.google.android.gms.internal.measurement.zzhp<?, ?> r3 = r0.zzakx
            zza(r3, (T) r1, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzgm.zzb(java.lang.Object, com.google.android.gms.internal.measurement.zzim):void");
    }

    private final <K, V> void zza(zzim zzim, int i, Object obj, int i2) throws IOException {
        if (obj != null) {
            zzim.zza(i, this.zzakz.zzr(zzby(i2)), this.zzakz.zzn(obj));
        }
    }

    private static <UT, UB> void zza(zzhp<UT, UB> zzhp, T t, zzim zzim) throws IOException {
        zzhp.zza(zzhp.zzx(t), zzim);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:151|152|(1:154)|155|(6:178|157|(2:160|158)|260|(1:162)|163)(1:256)) */
    /* JADX WARNING: Code restructure failed: missing block: B:152:?, code lost:
        r7.zza(r14);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:153:0x05a0, code lost:
        if (r10 == null) goto L_0x05a2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:154:0x05a2, code lost:
        r10 = r7.zzy(r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:156:0x05ab, code lost:
        if (r7.zza(r10, r14) == false) goto L_0x05ad;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:157:0x05ad, code lost:
        r14 = r12.zzakt;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:159:0x05b1, code lost:
        if (r14 < r12.zzaku) goto L_0x05b3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:160:0x05b3, code lost:
        r10 = zza((java.lang.Object) r13, r12.zzaks[r14], (UB) r10, r7);
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
    public final void zza(T r13, com.google.android.gms.internal.measurement.zzgy r14, com.google.android.gms.internal.measurement.zzel r15) throws java.io.IOException {
        /*
            r12 = this;
            if (r15 == 0) goto L_0x05dc
            com.google.android.gms.internal.measurement.zzhp<?, ?> r7 = r12.zzakx
            com.google.android.gms.internal.measurement.zzen<?> r8 = r12.zzaky
            r9 = 0
            r0 = r9
            r10 = r0
        L_0x0009:
            int r1 = r14.zzsy()     // Catch:{ all -> 0x05c4 }
            int r2 = r12.zzcd(r1)     // Catch:{ all -> 0x05c4 }
            if (r2 >= 0) goto L_0x0077
            r2 = 2147483647(0x7fffffff, float:NaN)
            if (r1 != r2) goto L_0x002f
            int r14 = r12.zzakt
        L_0x001a:
            int r15 = r12.zzaku
            if (r14 >= r15) goto L_0x0029
            int[] r15 = r12.zzaks
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
            boolean r2 = r12.zzako     // Catch:{ all -> 0x05c4 }
            if (r2 != 0) goto L_0x0035
            r2 = r9
            goto L_0x003c
        L_0x0035:
            com.google.android.gms.internal.measurement.zzgi r2 = r12.zzakn     // Catch:{ all -> 0x05c4 }
            java.lang.Object r1 = r8.zza(r15, r2, r1)     // Catch:{ all -> 0x05c4 }
            r2 = r1
        L_0x003c:
            if (r2 == 0) goto L_0x0051
            if (r0 != 0) goto L_0x0044
            com.google.android.gms.internal.measurement.zzeo r0 = r8.zzi(r13)     // Catch:{ all -> 0x05c4 }
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
            java.lang.Object r10 = r7.zzy(r13)     // Catch:{ all -> 0x05c4 }
        L_0x005a:
            boolean r1 = r7.zza(r10, r14)     // Catch:{ all -> 0x05c4 }
            if (r1 != 0) goto L_0x0009
            int r14 = r12.zzakt
        L_0x0062:
            int r15 = r12.zzaku
            if (r14 >= r15) goto L_0x0071
            int[] r15 = r12.zzaks
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
            int r3 = r12.zzca(r2)     // Catch:{ all -> 0x05c4 }
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
            java.lang.Object r10 = r7.zzwp()     // Catch:{ zzfh -> 0x059d }
            goto L_0x0580
        L_0x008e:
            r3 = r3 & r5
            long r3 = (long) r3     // Catch:{ zzfh -> 0x059d }
            com.google.android.gms.internal.measurement.zzgx r5 = r12.zzbx(r2)     // Catch:{ zzfh -> 0x059d }
            java.lang.Object r5 = r14.zzb(r5, r15)     // Catch:{ zzfh -> 0x059d }
            com.google.android.gms.internal.measurement.zzhv.zza(r13, r3, r5)     // Catch:{ zzfh -> 0x059d }
            r12.zzb((T) r13, r1, r2)     // Catch:{ zzfh -> 0x059d }
            goto L_0x0009
        L_0x00a0:
            r3 = r3 & r5
            long r3 = (long) r3     // Catch:{ zzfh -> 0x059d }
            long r5 = r14.zzsu()     // Catch:{ zzfh -> 0x059d }
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ zzfh -> 0x059d }
            com.google.android.gms.internal.measurement.zzhv.zza(r13, r3, r5)     // Catch:{ zzfh -> 0x059d }
            r12.zzb((T) r13, r1, r2)     // Catch:{ zzfh -> 0x059d }
            goto L_0x0009
        L_0x00b2:
            r3 = r3 & r5
            long r3 = (long) r3     // Catch:{ zzfh -> 0x059d }
            int r5 = r14.zzst()     // Catch:{ zzfh -> 0x059d }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ zzfh -> 0x059d }
            com.google.android.gms.internal.measurement.zzhv.zza(r13, r3, r5)     // Catch:{ zzfh -> 0x059d }
            r12.zzb((T) r13, r1, r2)     // Catch:{ zzfh -> 0x059d }
            goto L_0x0009
        L_0x00c4:
            r3 = r3 & r5
            long r3 = (long) r3     // Catch:{ zzfh -> 0x059d }
            long r5 = r14.zzss()     // Catch:{ zzfh -> 0x059d }
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ zzfh -> 0x059d }
            com.google.android.gms.internal.measurement.zzhv.zza(r13, r3, r5)     // Catch:{ zzfh -> 0x059d }
            r12.zzb((T) r13, r1, r2)     // Catch:{ zzfh -> 0x059d }
            goto L_0x0009
        L_0x00d6:
            r3 = r3 & r5
            long r3 = (long) r3     // Catch:{ zzfh -> 0x059d }
            int r5 = r14.zzsr()     // Catch:{ zzfh -> 0x059d }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ zzfh -> 0x059d }
            com.google.android.gms.internal.measurement.zzhv.zza(r13, r3, r5)     // Catch:{ zzfh -> 0x059d }
            r12.zzb((T) r13, r1, r2)     // Catch:{ zzfh -> 0x059d }
            goto L_0x0009
        L_0x00e8:
            int r4 = r14.zzsq()     // Catch:{ zzfh -> 0x059d }
            com.google.android.gms.internal.measurement.zzfe r6 = r12.zzbz(r2)     // Catch:{ zzfh -> 0x059d }
            if (r6 == 0) goto L_0x00ff
            boolean r6 = r6.zzg(r4)     // Catch:{ zzfh -> 0x059d }
            if (r6 == 0) goto L_0x00f9
            goto L_0x00ff
        L_0x00f9:
            java.lang.Object r10 = com.google.android.gms.internal.measurement.zzgz.zza(r1, r4, r10, r7)     // Catch:{ zzfh -> 0x059d }
            goto L_0x0009
        L_0x00ff:
            r3 = r3 & r5
            long r5 = (long) r3     // Catch:{ zzfh -> 0x059d }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r4)     // Catch:{ zzfh -> 0x059d }
            com.google.android.gms.internal.measurement.zzhv.zza(r13, r5, r3)     // Catch:{ zzfh -> 0x059d }
            r12.zzb((T) r13, r1, r2)     // Catch:{ zzfh -> 0x059d }
            goto L_0x0009
        L_0x010d:
            r3 = r3 & r5
            long r3 = (long) r3     // Catch:{ zzfh -> 0x059d }
            int r5 = r14.zzsp()     // Catch:{ zzfh -> 0x059d }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ zzfh -> 0x059d }
            com.google.android.gms.internal.measurement.zzhv.zza(r13, r3, r5)     // Catch:{ zzfh -> 0x059d }
            r12.zzb((T) r13, r1, r2)     // Catch:{ zzfh -> 0x059d }
            goto L_0x0009
        L_0x011f:
            r3 = r3 & r5
            long r3 = (long) r3     // Catch:{ zzfh -> 0x059d }
            com.google.android.gms.internal.measurement.zzdp r5 = r14.zzso()     // Catch:{ zzfh -> 0x059d }
            com.google.android.gms.internal.measurement.zzhv.zza(r13, r3, r5)     // Catch:{ zzfh -> 0x059d }
            r12.zzb((T) r13, r1, r2)     // Catch:{ zzfh -> 0x059d }
            goto L_0x0009
        L_0x012d:
            boolean r4 = r12.zza((T) r13, r1, r2)     // Catch:{ zzfh -> 0x059d }
            if (r4 == 0) goto L_0x0149
            r3 = r3 & r5
            long r3 = (long) r3     // Catch:{ zzfh -> 0x059d }
            java.lang.Object r5 = com.google.android.gms.internal.measurement.zzhv.zzp(r13, r3)     // Catch:{ zzfh -> 0x059d }
            com.google.android.gms.internal.measurement.zzgx r6 = r12.zzbx(r2)     // Catch:{ zzfh -> 0x059d }
            java.lang.Object r6 = r14.zza(r6, r15)     // Catch:{ zzfh -> 0x059d }
            java.lang.Object r5 = com.google.android.gms.internal.measurement.zzez.zza(r5, r6)     // Catch:{ zzfh -> 0x059d }
            com.google.android.gms.internal.measurement.zzhv.zza(r13, r3, r5)     // Catch:{ zzfh -> 0x059d }
            goto L_0x0159
        L_0x0149:
            r3 = r3 & r5
            long r3 = (long) r3     // Catch:{ zzfh -> 0x059d }
            com.google.android.gms.internal.measurement.zzgx r5 = r12.zzbx(r2)     // Catch:{ zzfh -> 0x059d }
            java.lang.Object r5 = r14.zza(r5, r15)     // Catch:{ zzfh -> 0x059d }
            com.google.android.gms.internal.measurement.zzhv.zza(r13, r3, r5)     // Catch:{ zzfh -> 0x059d }
            r12.zzb((T) r13, r2)     // Catch:{ zzfh -> 0x059d }
        L_0x0159:
            r12.zzb((T) r13, r1, r2)     // Catch:{ zzfh -> 0x059d }
            goto L_0x0009
        L_0x015e:
            r12.zza(r13, r3, r14)     // Catch:{ zzfh -> 0x059d }
            r12.zzb((T) r13, r1, r2)     // Catch:{ zzfh -> 0x059d }
            goto L_0x0009
        L_0x0166:
            r3 = r3 & r5
            long r3 = (long) r3     // Catch:{ zzfh -> 0x059d }
            boolean r5 = r14.zzsm()     // Catch:{ zzfh -> 0x059d }
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)     // Catch:{ zzfh -> 0x059d }
            com.google.android.gms.internal.measurement.zzhv.zza(r13, r3, r5)     // Catch:{ zzfh -> 0x059d }
            r12.zzb((T) r13, r1, r2)     // Catch:{ zzfh -> 0x059d }
            goto L_0x0009
        L_0x0178:
            r3 = r3 & r5
            long r3 = (long) r3     // Catch:{ zzfh -> 0x059d }
            int r5 = r14.zzsl()     // Catch:{ zzfh -> 0x059d }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ zzfh -> 0x059d }
            com.google.android.gms.internal.measurement.zzhv.zza(r13, r3, r5)     // Catch:{ zzfh -> 0x059d }
            r12.zzb((T) r13, r1, r2)     // Catch:{ zzfh -> 0x059d }
            goto L_0x0009
        L_0x018a:
            r3 = r3 & r5
            long r3 = (long) r3     // Catch:{ zzfh -> 0x059d }
            long r5 = r14.zzsk()     // Catch:{ zzfh -> 0x059d }
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ zzfh -> 0x059d }
            com.google.android.gms.internal.measurement.zzhv.zza(r13, r3, r5)     // Catch:{ zzfh -> 0x059d }
            r12.zzb((T) r13, r1, r2)     // Catch:{ zzfh -> 0x059d }
            goto L_0x0009
        L_0x019c:
            r3 = r3 & r5
            long r3 = (long) r3     // Catch:{ zzfh -> 0x059d }
            int r5 = r14.zzsj()     // Catch:{ zzfh -> 0x059d }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ zzfh -> 0x059d }
            com.google.android.gms.internal.measurement.zzhv.zza(r13, r3, r5)     // Catch:{ zzfh -> 0x059d }
            r12.zzb((T) r13, r1, r2)     // Catch:{ zzfh -> 0x059d }
            goto L_0x0009
        L_0x01ae:
            r3 = r3 & r5
            long r3 = (long) r3     // Catch:{ zzfh -> 0x059d }
            long r5 = r14.zzsh()     // Catch:{ zzfh -> 0x059d }
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ zzfh -> 0x059d }
            com.google.android.gms.internal.measurement.zzhv.zza(r13, r3, r5)     // Catch:{ zzfh -> 0x059d }
            r12.zzb((T) r13, r1, r2)     // Catch:{ zzfh -> 0x059d }
            goto L_0x0009
        L_0x01c0:
            r3 = r3 & r5
            long r3 = (long) r3     // Catch:{ zzfh -> 0x059d }
            long r5 = r14.zzsi()     // Catch:{ zzfh -> 0x059d }
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ zzfh -> 0x059d }
            com.google.android.gms.internal.measurement.zzhv.zza(r13, r3, r5)     // Catch:{ zzfh -> 0x059d }
            r12.zzb((T) r13, r1, r2)     // Catch:{ zzfh -> 0x059d }
            goto L_0x0009
        L_0x01d2:
            r3 = r3 & r5
            long r3 = (long) r3     // Catch:{ zzfh -> 0x059d }
            float r5 = r14.readFloat()     // Catch:{ zzfh -> 0x059d }
            java.lang.Float r5 = java.lang.Float.valueOf(r5)     // Catch:{ zzfh -> 0x059d }
            com.google.android.gms.internal.measurement.zzhv.zza(r13, r3, r5)     // Catch:{ zzfh -> 0x059d }
            r12.zzb((T) r13, r1, r2)     // Catch:{ zzfh -> 0x059d }
            goto L_0x0009
        L_0x01e4:
            r3 = r3 & r5
            long r3 = (long) r3     // Catch:{ zzfh -> 0x059d }
            double r5 = r14.readDouble()     // Catch:{ zzfh -> 0x059d }
            java.lang.Double r5 = java.lang.Double.valueOf(r5)     // Catch:{ zzfh -> 0x059d }
            com.google.android.gms.internal.measurement.zzhv.zza(r13, r3, r5)     // Catch:{ zzfh -> 0x059d }
            r12.zzb((T) r13, r1, r2)     // Catch:{ zzfh -> 0x059d }
            goto L_0x0009
        L_0x01f6:
            java.lang.Object r1 = r12.zzby(r2)     // Catch:{ zzfh -> 0x059d }
            int r2 = r12.zzca(r2)     // Catch:{ zzfh -> 0x059d }
            r2 = r2 & r5
            long r2 = (long) r2     // Catch:{ zzfh -> 0x059d }
            java.lang.Object r4 = com.google.android.gms.internal.measurement.zzhv.zzp(r13, r2)     // Catch:{ zzfh -> 0x059d }
            if (r4 != 0) goto L_0x0210
            com.google.android.gms.internal.measurement.zzgb r4 = r12.zzakz     // Catch:{ zzfh -> 0x059d }
            java.lang.Object r4 = r4.zzq(r1)     // Catch:{ zzfh -> 0x059d }
            com.google.android.gms.internal.measurement.zzhv.zza(r13, r2, r4)     // Catch:{ zzfh -> 0x059d }
            goto L_0x0227
        L_0x0210:
            com.google.android.gms.internal.measurement.zzgb r5 = r12.zzakz     // Catch:{ zzfh -> 0x059d }
            boolean r5 = r5.zzo(r4)     // Catch:{ zzfh -> 0x059d }
            if (r5 == 0) goto L_0x0227
            com.google.android.gms.internal.measurement.zzgb r5 = r12.zzakz     // Catch:{ zzfh -> 0x059d }
            java.lang.Object r5 = r5.zzq(r1)     // Catch:{ zzfh -> 0x059d }
            com.google.android.gms.internal.measurement.zzgb r6 = r12.zzakz     // Catch:{ zzfh -> 0x059d }
            r6.zzb(r5, r4)     // Catch:{ zzfh -> 0x059d }
            com.google.android.gms.internal.measurement.zzhv.zza(r13, r2, r5)     // Catch:{ zzfh -> 0x059d }
            r4 = r5
        L_0x0227:
            com.google.android.gms.internal.measurement.zzgb r2 = r12.zzakz     // Catch:{ zzfh -> 0x059d }
            java.util.Map r2 = r2.zzm(r4)     // Catch:{ zzfh -> 0x059d }
            com.google.android.gms.internal.measurement.zzgb r3 = r12.zzakz     // Catch:{ zzfh -> 0x059d }
            com.google.android.gms.internal.measurement.zzfz r1 = r3.zzr(r1)     // Catch:{ zzfh -> 0x059d }
            r14.zza(r2, r1, r15)     // Catch:{ zzfh -> 0x059d }
            goto L_0x0009
        L_0x0238:
            r1 = r3 & r5
            long r3 = (long) r1     // Catch:{ zzfh -> 0x059d }
            com.google.android.gms.internal.measurement.zzgx r1 = r12.zzbx(r2)     // Catch:{ zzfh -> 0x059d }
            com.google.android.gms.internal.measurement.zzfs r2 = r12.zzakw     // Catch:{ zzfh -> 0x059d }
            java.util.List r2 = r2.zza(r13, r3)     // Catch:{ zzfh -> 0x059d }
            r14.zzb(r2, r1, r15)     // Catch:{ zzfh -> 0x059d }
            goto L_0x0009
        L_0x024a:
            com.google.android.gms.internal.measurement.zzfs r1 = r12.zzakw     // Catch:{ zzfh -> 0x059d }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzfh -> 0x059d }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzfh -> 0x059d }
            r14.zzt(r1)     // Catch:{ zzfh -> 0x059d }
            goto L_0x0009
        L_0x0258:
            com.google.android.gms.internal.measurement.zzfs r1 = r12.zzakw     // Catch:{ zzfh -> 0x059d }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzfh -> 0x059d }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzfh -> 0x059d }
            r14.zzs(r1)     // Catch:{ zzfh -> 0x059d }
            goto L_0x0009
        L_0x0266:
            com.google.android.gms.internal.measurement.zzfs r1 = r12.zzakw     // Catch:{ zzfh -> 0x059d }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzfh -> 0x059d }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzfh -> 0x059d }
            r14.zzr(r1)     // Catch:{ zzfh -> 0x059d }
            goto L_0x0009
        L_0x0274:
            com.google.android.gms.internal.measurement.zzfs r1 = r12.zzakw     // Catch:{ zzfh -> 0x059d }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzfh -> 0x059d }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzfh -> 0x059d }
            r14.zzq(r1)     // Catch:{ zzfh -> 0x059d }
            goto L_0x0009
        L_0x0282:
            com.google.android.gms.internal.measurement.zzfs r4 = r12.zzakw     // Catch:{ zzfh -> 0x059d }
            r3 = r3 & r5
            long r5 = (long) r3     // Catch:{ zzfh -> 0x059d }
            java.util.List r3 = r4.zza(r13, r5)     // Catch:{ zzfh -> 0x059d }
            r14.zzp(r3)     // Catch:{ zzfh -> 0x059d }
            com.google.android.gms.internal.measurement.zzfe r2 = r12.zzbz(r2)     // Catch:{ zzfh -> 0x059d }
            java.lang.Object r10 = com.google.android.gms.internal.measurement.zzgz.zza(r1, r3, r2, r10, r7)     // Catch:{ zzfh -> 0x059d }
            goto L_0x0009
        L_0x0297:
            com.google.android.gms.internal.measurement.zzfs r1 = r12.zzakw     // Catch:{ zzfh -> 0x059d }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzfh -> 0x059d }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzfh -> 0x059d }
            r14.zzo(r1)     // Catch:{ zzfh -> 0x059d }
            goto L_0x0009
        L_0x02a5:
            com.google.android.gms.internal.measurement.zzfs r1 = r12.zzakw     // Catch:{ zzfh -> 0x059d }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzfh -> 0x059d }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzfh -> 0x059d }
            r14.zzl(r1)     // Catch:{ zzfh -> 0x059d }
            goto L_0x0009
        L_0x02b3:
            com.google.android.gms.internal.measurement.zzfs r1 = r12.zzakw     // Catch:{ zzfh -> 0x059d }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzfh -> 0x059d }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzfh -> 0x059d }
            r14.zzk(r1)     // Catch:{ zzfh -> 0x059d }
            goto L_0x0009
        L_0x02c1:
            com.google.android.gms.internal.measurement.zzfs r1 = r12.zzakw     // Catch:{ zzfh -> 0x059d }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzfh -> 0x059d }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzfh -> 0x059d }
            r14.zzj(r1)     // Catch:{ zzfh -> 0x059d }
            goto L_0x0009
        L_0x02cf:
            com.google.android.gms.internal.measurement.zzfs r1 = r12.zzakw     // Catch:{ zzfh -> 0x059d }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzfh -> 0x059d }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzfh -> 0x059d }
            r14.zzi(r1)     // Catch:{ zzfh -> 0x059d }
            goto L_0x0009
        L_0x02dd:
            com.google.android.gms.internal.measurement.zzfs r1 = r12.zzakw     // Catch:{ zzfh -> 0x059d }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzfh -> 0x059d }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzfh -> 0x059d }
            r14.zzg(r1)     // Catch:{ zzfh -> 0x059d }
            goto L_0x0009
        L_0x02eb:
            com.google.android.gms.internal.measurement.zzfs r1 = r12.zzakw     // Catch:{ zzfh -> 0x059d }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzfh -> 0x059d }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzfh -> 0x059d }
            r14.zzh(r1)     // Catch:{ zzfh -> 0x059d }
            goto L_0x0009
        L_0x02f9:
            com.google.android.gms.internal.measurement.zzfs r1 = r12.zzakw     // Catch:{ zzfh -> 0x059d }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzfh -> 0x059d }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzfh -> 0x059d }
            r14.zzf(r1)     // Catch:{ zzfh -> 0x059d }
            goto L_0x0009
        L_0x0307:
            com.google.android.gms.internal.measurement.zzfs r1 = r12.zzakw     // Catch:{ zzfh -> 0x059d }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzfh -> 0x059d }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzfh -> 0x059d }
            r14.zze(r1)     // Catch:{ zzfh -> 0x059d }
            goto L_0x0009
        L_0x0315:
            com.google.android.gms.internal.measurement.zzfs r1 = r12.zzakw     // Catch:{ zzfh -> 0x059d }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzfh -> 0x059d }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzfh -> 0x059d }
            r14.zzt(r1)     // Catch:{ zzfh -> 0x059d }
            goto L_0x0009
        L_0x0323:
            com.google.android.gms.internal.measurement.zzfs r1 = r12.zzakw     // Catch:{ zzfh -> 0x059d }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzfh -> 0x059d }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzfh -> 0x059d }
            r14.zzs(r1)     // Catch:{ zzfh -> 0x059d }
            goto L_0x0009
        L_0x0331:
            com.google.android.gms.internal.measurement.zzfs r1 = r12.zzakw     // Catch:{ zzfh -> 0x059d }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzfh -> 0x059d }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzfh -> 0x059d }
            r14.zzr(r1)     // Catch:{ zzfh -> 0x059d }
            goto L_0x0009
        L_0x033f:
            com.google.android.gms.internal.measurement.zzfs r1 = r12.zzakw     // Catch:{ zzfh -> 0x059d }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzfh -> 0x059d }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzfh -> 0x059d }
            r14.zzq(r1)     // Catch:{ zzfh -> 0x059d }
            goto L_0x0009
        L_0x034d:
            com.google.android.gms.internal.measurement.zzfs r4 = r12.zzakw     // Catch:{ zzfh -> 0x059d }
            r3 = r3 & r5
            long r5 = (long) r3     // Catch:{ zzfh -> 0x059d }
            java.util.List r3 = r4.zza(r13, r5)     // Catch:{ zzfh -> 0x059d }
            r14.zzp(r3)     // Catch:{ zzfh -> 0x059d }
            com.google.android.gms.internal.measurement.zzfe r2 = r12.zzbz(r2)     // Catch:{ zzfh -> 0x059d }
            java.lang.Object r10 = com.google.android.gms.internal.measurement.zzgz.zza(r1, r3, r2, r10, r7)     // Catch:{ zzfh -> 0x059d }
            goto L_0x0009
        L_0x0362:
            com.google.android.gms.internal.measurement.zzfs r1 = r12.zzakw     // Catch:{ zzfh -> 0x059d }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzfh -> 0x059d }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzfh -> 0x059d }
            r14.zzo(r1)     // Catch:{ zzfh -> 0x059d }
            goto L_0x0009
        L_0x0370:
            com.google.android.gms.internal.measurement.zzfs r1 = r12.zzakw     // Catch:{ zzfh -> 0x059d }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzfh -> 0x059d }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzfh -> 0x059d }
            r14.zzn(r1)     // Catch:{ zzfh -> 0x059d }
            goto L_0x0009
        L_0x037e:
            com.google.android.gms.internal.measurement.zzgx r1 = r12.zzbx(r2)     // Catch:{ zzfh -> 0x059d }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzfh -> 0x059d }
            com.google.android.gms.internal.measurement.zzfs r4 = r12.zzakw     // Catch:{ zzfh -> 0x059d }
            java.util.List r2 = r4.zza(r13, r2)     // Catch:{ zzfh -> 0x059d }
            r14.zza(r2, r1, r15)     // Catch:{ zzfh -> 0x059d }
            goto L_0x0009
        L_0x0390:
            boolean r1 = zzcc(r3)     // Catch:{ zzfh -> 0x059d }
            if (r1 == 0) goto L_0x03a4
            com.google.android.gms.internal.measurement.zzfs r1 = r12.zzakw     // Catch:{ zzfh -> 0x059d }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzfh -> 0x059d }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzfh -> 0x059d }
            r14.zzm(r1)     // Catch:{ zzfh -> 0x059d }
            goto L_0x0009
        L_0x03a4:
            com.google.android.gms.internal.measurement.zzfs r1 = r12.zzakw     // Catch:{ zzfh -> 0x059d }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzfh -> 0x059d }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzfh -> 0x059d }
            r14.readStringList(r1)     // Catch:{ zzfh -> 0x059d }
            goto L_0x0009
        L_0x03b2:
            com.google.android.gms.internal.measurement.zzfs r1 = r12.zzakw     // Catch:{ zzfh -> 0x059d }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzfh -> 0x059d }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzfh -> 0x059d }
            r14.zzl(r1)     // Catch:{ zzfh -> 0x059d }
            goto L_0x0009
        L_0x03c0:
            com.google.android.gms.internal.measurement.zzfs r1 = r12.zzakw     // Catch:{ zzfh -> 0x059d }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzfh -> 0x059d }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzfh -> 0x059d }
            r14.zzk(r1)     // Catch:{ zzfh -> 0x059d }
            goto L_0x0009
        L_0x03ce:
            com.google.android.gms.internal.measurement.zzfs r1 = r12.zzakw     // Catch:{ zzfh -> 0x059d }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzfh -> 0x059d }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzfh -> 0x059d }
            r14.zzj(r1)     // Catch:{ zzfh -> 0x059d }
            goto L_0x0009
        L_0x03dc:
            com.google.android.gms.internal.measurement.zzfs r1 = r12.zzakw     // Catch:{ zzfh -> 0x059d }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzfh -> 0x059d }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzfh -> 0x059d }
            r14.zzi(r1)     // Catch:{ zzfh -> 0x059d }
            goto L_0x0009
        L_0x03ea:
            com.google.android.gms.internal.measurement.zzfs r1 = r12.zzakw     // Catch:{ zzfh -> 0x059d }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzfh -> 0x059d }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzfh -> 0x059d }
            r14.zzg(r1)     // Catch:{ zzfh -> 0x059d }
            goto L_0x0009
        L_0x03f8:
            com.google.android.gms.internal.measurement.zzfs r1 = r12.zzakw     // Catch:{ zzfh -> 0x059d }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzfh -> 0x059d }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzfh -> 0x059d }
            r14.zzh(r1)     // Catch:{ zzfh -> 0x059d }
            goto L_0x0009
        L_0x0406:
            com.google.android.gms.internal.measurement.zzfs r1 = r12.zzakw     // Catch:{ zzfh -> 0x059d }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzfh -> 0x059d }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzfh -> 0x059d }
            r14.zzf(r1)     // Catch:{ zzfh -> 0x059d }
            goto L_0x0009
        L_0x0414:
            com.google.android.gms.internal.measurement.zzfs r1 = r12.zzakw     // Catch:{ zzfh -> 0x059d }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzfh -> 0x059d }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzfh -> 0x059d }
            r14.zze(r1)     // Catch:{ zzfh -> 0x059d }
            goto L_0x0009
        L_0x0422:
            boolean r1 = r12.zza((T) r13, r2)     // Catch:{ zzfh -> 0x059d }
            if (r1 == 0) goto L_0x0440
            r1 = r3 & r5
            long r3 = (long) r1     // Catch:{ zzfh -> 0x059d }
            java.lang.Object r1 = com.google.android.gms.internal.measurement.zzhv.zzp(r13, r3)     // Catch:{ zzfh -> 0x059d }
            com.google.android.gms.internal.measurement.zzgx r2 = r12.zzbx(r2)     // Catch:{ zzfh -> 0x059d }
            java.lang.Object r2 = r14.zzb(r2, r15)     // Catch:{ zzfh -> 0x059d }
            java.lang.Object r1 = com.google.android.gms.internal.measurement.zzez.zza(r1, r2)     // Catch:{ zzfh -> 0x059d }
            com.google.android.gms.internal.measurement.zzhv.zza(r13, r3, r1)     // Catch:{ zzfh -> 0x059d }
            goto L_0x0009
        L_0x0440:
            r1 = r3 & r5
            long r3 = (long) r1     // Catch:{ zzfh -> 0x059d }
            com.google.android.gms.internal.measurement.zzgx r1 = r12.zzbx(r2)     // Catch:{ zzfh -> 0x059d }
            java.lang.Object r1 = r14.zzb(r1, r15)     // Catch:{ zzfh -> 0x059d }
            com.google.android.gms.internal.measurement.zzhv.zza(r13, r3, r1)     // Catch:{ zzfh -> 0x059d }
            r12.zzb((T) r13, r2)     // Catch:{ zzfh -> 0x059d }
            goto L_0x0009
        L_0x0453:
            r1 = r3 & r5
            long r3 = (long) r1     // Catch:{ zzfh -> 0x059d }
            long r5 = r14.zzsu()     // Catch:{ zzfh -> 0x059d }
            com.google.android.gms.internal.measurement.zzhv.zza(r13, r3, r5)     // Catch:{ zzfh -> 0x059d }
            r12.zzb((T) r13, r2)     // Catch:{ zzfh -> 0x059d }
            goto L_0x0009
        L_0x0462:
            r1 = r3 & r5
            long r3 = (long) r1     // Catch:{ zzfh -> 0x059d }
            int r1 = r14.zzst()     // Catch:{ zzfh -> 0x059d }
            com.google.android.gms.internal.measurement.zzhv.zzb(r13, r3, r1)     // Catch:{ zzfh -> 0x059d }
            r12.zzb((T) r13, r2)     // Catch:{ zzfh -> 0x059d }
            goto L_0x0009
        L_0x0471:
            r1 = r3 & r5
            long r3 = (long) r1     // Catch:{ zzfh -> 0x059d }
            long r5 = r14.zzss()     // Catch:{ zzfh -> 0x059d }
            com.google.android.gms.internal.measurement.zzhv.zza(r13, r3, r5)     // Catch:{ zzfh -> 0x059d }
            r12.zzb((T) r13, r2)     // Catch:{ zzfh -> 0x059d }
            goto L_0x0009
        L_0x0480:
            r1 = r3 & r5
            long r3 = (long) r1     // Catch:{ zzfh -> 0x059d }
            int r1 = r14.zzsr()     // Catch:{ zzfh -> 0x059d }
            com.google.android.gms.internal.measurement.zzhv.zzb(r13, r3, r1)     // Catch:{ zzfh -> 0x059d }
            r12.zzb((T) r13, r2)     // Catch:{ zzfh -> 0x059d }
            goto L_0x0009
        L_0x048f:
            int r4 = r14.zzsq()     // Catch:{ zzfh -> 0x059d }
            com.google.android.gms.internal.measurement.zzfe r6 = r12.zzbz(r2)     // Catch:{ zzfh -> 0x059d }
            if (r6 == 0) goto L_0x04a6
            boolean r6 = r6.zzg(r4)     // Catch:{ zzfh -> 0x059d }
            if (r6 == 0) goto L_0x04a0
            goto L_0x04a6
        L_0x04a0:
            java.lang.Object r10 = com.google.android.gms.internal.measurement.zzgz.zza(r1, r4, r10, r7)     // Catch:{ zzfh -> 0x059d }
            goto L_0x0009
        L_0x04a6:
            r1 = r3 & r5
            long r5 = (long) r1     // Catch:{ zzfh -> 0x059d }
            com.google.android.gms.internal.measurement.zzhv.zzb(r13, r5, r4)     // Catch:{ zzfh -> 0x059d }
            r12.zzb((T) r13, r2)     // Catch:{ zzfh -> 0x059d }
            goto L_0x0009
        L_0x04b1:
            r1 = r3 & r5
            long r3 = (long) r1     // Catch:{ zzfh -> 0x059d }
            int r1 = r14.zzsp()     // Catch:{ zzfh -> 0x059d }
            com.google.android.gms.internal.measurement.zzhv.zzb(r13, r3, r1)     // Catch:{ zzfh -> 0x059d }
            r12.zzb((T) r13, r2)     // Catch:{ zzfh -> 0x059d }
            goto L_0x0009
        L_0x04c0:
            r1 = r3 & r5
            long r3 = (long) r1     // Catch:{ zzfh -> 0x059d }
            com.google.android.gms.internal.measurement.zzdp r1 = r14.zzso()     // Catch:{ zzfh -> 0x059d }
            com.google.android.gms.internal.measurement.zzhv.zza(r13, r3, r1)     // Catch:{ zzfh -> 0x059d }
            r12.zzb((T) r13, r2)     // Catch:{ zzfh -> 0x059d }
            goto L_0x0009
        L_0x04cf:
            boolean r1 = r12.zza((T) r13, r2)     // Catch:{ zzfh -> 0x059d }
            if (r1 == 0) goto L_0x04ed
            r1 = r3 & r5
            long r3 = (long) r1     // Catch:{ zzfh -> 0x059d }
            java.lang.Object r1 = com.google.android.gms.internal.measurement.zzhv.zzp(r13, r3)     // Catch:{ zzfh -> 0x059d }
            com.google.android.gms.internal.measurement.zzgx r2 = r12.zzbx(r2)     // Catch:{ zzfh -> 0x059d }
            java.lang.Object r2 = r14.zza(r2, r15)     // Catch:{ zzfh -> 0x059d }
            java.lang.Object r1 = com.google.android.gms.internal.measurement.zzez.zza(r1, r2)     // Catch:{ zzfh -> 0x059d }
            com.google.android.gms.internal.measurement.zzhv.zza(r13, r3, r1)     // Catch:{ zzfh -> 0x059d }
            goto L_0x0009
        L_0x04ed:
            r1 = r3 & r5
            long r3 = (long) r1     // Catch:{ zzfh -> 0x059d }
            com.google.android.gms.internal.measurement.zzgx r1 = r12.zzbx(r2)     // Catch:{ zzfh -> 0x059d }
            java.lang.Object r1 = r14.zza(r1, r15)     // Catch:{ zzfh -> 0x059d }
            com.google.android.gms.internal.measurement.zzhv.zza(r13, r3, r1)     // Catch:{ zzfh -> 0x059d }
            r12.zzb((T) r13, r2)     // Catch:{ zzfh -> 0x059d }
            goto L_0x0009
        L_0x0500:
            r12.zza(r13, r3, r14)     // Catch:{ zzfh -> 0x059d }
            r12.zzb((T) r13, r2)     // Catch:{ zzfh -> 0x059d }
            goto L_0x0009
        L_0x0508:
            r1 = r3 & r5
            long r3 = (long) r1     // Catch:{ zzfh -> 0x059d }
            boolean r1 = r14.zzsm()     // Catch:{ zzfh -> 0x059d }
            com.google.android.gms.internal.measurement.zzhv.zza(r13, r3, r1)     // Catch:{ zzfh -> 0x059d }
            r12.zzb((T) r13, r2)     // Catch:{ zzfh -> 0x059d }
            goto L_0x0009
        L_0x0517:
            r1 = r3 & r5
            long r3 = (long) r1     // Catch:{ zzfh -> 0x059d }
            int r1 = r14.zzsl()     // Catch:{ zzfh -> 0x059d }
            com.google.android.gms.internal.measurement.zzhv.zzb(r13, r3, r1)     // Catch:{ zzfh -> 0x059d }
            r12.zzb((T) r13, r2)     // Catch:{ zzfh -> 0x059d }
            goto L_0x0009
        L_0x0526:
            r1 = r3 & r5
            long r3 = (long) r1     // Catch:{ zzfh -> 0x059d }
            long r5 = r14.zzsk()     // Catch:{ zzfh -> 0x059d }
            com.google.android.gms.internal.measurement.zzhv.zza(r13, r3, r5)     // Catch:{ zzfh -> 0x059d }
            r12.zzb((T) r13, r2)     // Catch:{ zzfh -> 0x059d }
            goto L_0x0009
        L_0x0535:
            r1 = r3 & r5
            long r3 = (long) r1     // Catch:{ zzfh -> 0x059d }
            int r1 = r14.zzsj()     // Catch:{ zzfh -> 0x059d }
            com.google.android.gms.internal.measurement.zzhv.zzb(r13, r3, r1)     // Catch:{ zzfh -> 0x059d }
            r12.zzb((T) r13, r2)     // Catch:{ zzfh -> 0x059d }
            goto L_0x0009
        L_0x0544:
            r1 = r3 & r5
            long r3 = (long) r1     // Catch:{ zzfh -> 0x059d }
            long r5 = r14.zzsh()     // Catch:{ zzfh -> 0x059d }
            com.google.android.gms.internal.measurement.zzhv.zza(r13, r3, r5)     // Catch:{ zzfh -> 0x059d }
            r12.zzb((T) r13, r2)     // Catch:{ zzfh -> 0x059d }
            goto L_0x0009
        L_0x0553:
            r1 = r3 & r5
            long r3 = (long) r1     // Catch:{ zzfh -> 0x059d }
            long r5 = r14.zzsi()     // Catch:{ zzfh -> 0x059d }
            com.google.android.gms.internal.measurement.zzhv.zza(r13, r3, r5)     // Catch:{ zzfh -> 0x059d }
            r12.zzb((T) r13, r2)     // Catch:{ zzfh -> 0x059d }
            goto L_0x0009
        L_0x0562:
            r1 = r3 & r5
            long r3 = (long) r1     // Catch:{ zzfh -> 0x059d }
            float r1 = r14.readFloat()     // Catch:{ zzfh -> 0x059d }
            com.google.android.gms.internal.measurement.zzhv.zza(r13, r3, r1)     // Catch:{ zzfh -> 0x059d }
            r12.zzb((T) r13, r2)     // Catch:{ zzfh -> 0x059d }
            goto L_0x0009
        L_0x0571:
            r1 = r3 & r5
            long r3 = (long) r1     // Catch:{ zzfh -> 0x059d }
            double r5 = r14.readDouble()     // Catch:{ zzfh -> 0x059d }
            com.google.android.gms.internal.measurement.zzhv.zza(r13, r3, r5)     // Catch:{ zzfh -> 0x059d }
            r12.zzb((T) r13, r2)     // Catch:{ zzfh -> 0x059d }
            goto L_0x0009
        L_0x0580:
            boolean r1 = r7.zza(r10, r14)     // Catch:{ zzfh -> 0x059d }
            if (r1 != 0) goto L_0x0009
            int r14 = r12.zzakt
        L_0x0588:
            int r15 = r12.zzaku
            if (r14 >= r15) goto L_0x0597
            int[] r15 = r12.zzaks
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
            java.lang.Object r1 = r7.zzy(r13)     // Catch:{ all -> 0x05c4 }
            r10 = r1
        L_0x05a7:
            boolean r1 = r7.zza(r10, r14)     // Catch:{ all -> 0x05c4 }
            if (r1 != 0) goto L_0x0009
            int r14 = r12.zzakt
        L_0x05af:
            int r15 = r12.zzaku
            if (r14 >= r15) goto L_0x05be
            int[] r15 = r12.zzaks
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
            int r15 = r12.zzakt
        L_0x05c7:
            int r0 = r12.zzaku
            if (r15 >= r0) goto L_0x05d6
            int[] r0 = r12.zzaks
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
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzgm.zza(java.lang.Object, com.google.android.gms.internal.measurement.zzgy, com.google.android.gms.internal.measurement.zzel):void");
    }

    private static zzhs zzu(Object obj) {
        zzey zzey = (zzey) obj;
        zzhs zzhs = zzey.zzahz;
        if (zzhs != zzhs.zzwq()) {
            return zzhs;
        }
        zzhs zzwr = zzhs.zzwr();
        zzey.zzahz = zzwr;
        return zzwr;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:29:?, code lost:
        return r2 + 4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
        return r2 + 8;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int zza(byte[] r1, int r2, int r3, com.google.android.gms.internal.measurement.zzig r4, java.lang.Class<?> r5, com.google.android.gms.internal.measurement.zzdk r6) throws java.io.IOException {
        /*
            int[] r0 = com.google.android.gms.internal.measurement.zzgl.zzaee
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
            int r1 = com.google.android.gms.internal.measurement.zzdl.zzd(r1, r2, r6)
            goto L_0x00ae
        L_0x0019:
            int r1 = com.google.android.gms.internal.measurement.zzdl.zzb(r1, r2, r6)
            long r2 = r6.zzadb
            long r2 = com.google.android.gms.internal.measurement.zzeb.zzbm(r2)
            java.lang.Long r2 = java.lang.Long.valueOf(r2)
            r6.zzadc = r2
            goto L_0x00ae
        L_0x002b:
            int r1 = com.google.android.gms.internal.measurement.zzdl.zza(r1, r2, r6)
            int r2 = r6.zzada
            int r2 = com.google.android.gms.internal.measurement.zzeb.zzaz(r2)
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r6.zzadc = r2
            goto L_0x00ae
        L_0x003d:
            com.google.android.gms.internal.measurement.zzgt r4 = com.google.android.gms.internal.measurement.zzgt.zzvy()
            com.google.android.gms.internal.measurement.zzgx r4 = r4.zzf(r5)
            int r1 = com.google.android.gms.internal.measurement.zzdl.zza(r4, r1, r2, r3, r6)
            goto L_0x00ae
        L_0x004a:
            int r1 = com.google.android.gms.internal.measurement.zzdl.zzb(r1, r2, r6)
            long r2 = r6.zzadb
            java.lang.Long r2 = java.lang.Long.valueOf(r2)
            r6.zzadc = r2
            goto L_0x00ae
        L_0x0057:
            int r1 = com.google.android.gms.internal.measurement.zzdl.zza(r1, r2, r6)
            int r2 = r6.zzada
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r6.zzadc = r2
            goto L_0x00ae
        L_0x0064:
            float r1 = com.google.android.gms.internal.measurement.zzdl.zzd(r1, r2)
            java.lang.Float r1 = java.lang.Float.valueOf(r1)
            r6.zzadc = r1
            goto L_0x0084
        L_0x006f:
            long r3 = com.google.android.gms.internal.measurement.zzdl.zzb(r1, r2)
            java.lang.Long r1 = java.lang.Long.valueOf(r3)
            r6.zzadc = r1
            goto L_0x0091
        L_0x007a:
            int r1 = com.google.android.gms.internal.measurement.zzdl.zza(r1, r2)
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            r6.zzadc = r1
        L_0x0084:
            int r1 = r2 + 4
            goto L_0x00ae
        L_0x0087:
            double r3 = com.google.android.gms.internal.measurement.zzdl.zzc(r1, r2)
            java.lang.Double r1 = java.lang.Double.valueOf(r3)
            r6.zzadc = r1
        L_0x0091:
            int r1 = r2 + 8
            goto L_0x00ae
        L_0x0094:
            int r1 = com.google.android.gms.internal.measurement.zzdl.zze(r1, r2, r6)
            goto L_0x00ae
        L_0x0099:
            int r1 = com.google.android.gms.internal.measurement.zzdl.zzb(r1, r2, r6)
            long r2 = r6.zzadb
            r4 = 0
            int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r0 == 0) goto L_0x00a7
            r2 = 1
            goto L_0x00a8
        L_0x00a7:
            r2 = 0
        L_0x00a8:
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r2)
            r6.zzadc = r2
        L_0x00ae:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzgm.zza(byte[], int, int, com.google.android.gms.internal.measurement.zzig, java.lang.Class, com.google.android.gms.internal.measurement.zzdk):int");
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
    private final int zza(T r17, byte[] r18, int r19, int r20, int r21, int r22, int r23, int r24, long r25, int r27, long r28, com.google.android.gms.internal.measurement.zzdk r30) throws java.io.IOException {
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
            sun.misc.Unsafe r11 = zzaki
            java.lang.Object r11 = r11.getObject(r1, r9)
            com.google.android.gms.internal.measurement.zzff r11 = (com.google.android.gms.internal.measurement.zzff) r11
            boolean r12 = r11.zzrx()
            r13 = 1
            if (r12 != 0) goto L_0x0036
            int r12 = r11.size()
            if (r12 != 0) goto L_0x002c
            r12 = 10
            goto L_0x002d
        L_0x002c:
            int r12 = r12 << r13
        L_0x002d:
            com.google.android.gms.internal.measurement.zzff r11 = r11.zzap(r12)
            sun.misc.Unsafe r12 = zzaki
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
            com.google.android.gms.internal.measurement.zzgx r1 = r0.zzbx(r8)
            r6 = r2 & -8
            r6 = r6 | 4
            r22 = r1
            r23 = r18
            r24 = r19
            r25 = r20
            r26 = r6
            r27 = r30
            int r4 = com.google.android.gms.internal.measurement.zzdl.zza(r22, r23, r24, r25, r26, r27)
            java.lang.Object r8 = r7.zzadc
            r11.add(r8)
        L_0x005f:
            if (r4 >= r5) goto L_0x0422
            int r8 = com.google.android.gms.internal.measurement.zzdl.zza(r3, r4, r7)
            int r9 = r7.zzada
            if (r2 != r9) goto L_0x0422
            r22 = r1
            r23 = r18
            r24 = r8
            r25 = r20
            r26 = r6
            r27 = r30
            int r4 = com.google.android.gms.internal.measurement.zzdl.zza(r22, r23, r24, r25, r26, r27)
            java.lang.Object r8 = r7.zzadc
            r11.add(r8)
            goto L_0x005f
        L_0x007f:
            if (r6 != r10) goto L_0x00a3
            com.google.android.gms.internal.measurement.zzfw r11 = (com.google.android.gms.internal.measurement.zzfw) r11
            int r1 = com.google.android.gms.internal.measurement.zzdl.zza(r3, r4, r7)
            int r2 = r7.zzada
            int r2 = r2 + r1
        L_0x008a:
            if (r1 >= r2) goto L_0x009a
            int r1 = com.google.android.gms.internal.measurement.zzdl.zzb(r3, r1, r7)
            long r4 = r7.zzadb
            long r4 = com.google.android.gms.internal.measurement.zzeb.zzbm(r4)
            r11.zzby(r4)
            goto L_0x008a
        L_0x009a:
            if (r1 != r2) goto L_0x009e
            goto L_0x0423
        L_0x009e:
            com.google.android.gms.internal.measurement.zzfi r1 = com.google.android.gms.internal.measurement.zzfi.zzut()
            throw r1
        L_0x00a3:
            if (r6 != 0) goto L_0x0422
            com.google.android.gms.internal.measurement.zzfw r11 = (com.google.android.gms.internal.measurement.zzfw) r11
            int r1 = com.google.android.gms.internal.measurement.zzdl.zzb(r3, r4, r7)
            long r8 = r7.zzadb
            long r8 = com.google.android.gms.internal.measurement.zzeb.zzbm(r8)
            r11.zzby(r8)
        L_0x00b4:
            if (r1 >= r5) goto L_0x0423
            int r4 = com.google.android.gms.internal.measurement.zzdl.zza(r3, r1, r7)
            int r6 = r7.zzada
            if (r2 != r6) goto L_0x0423
            int r1 = com.google.android.gms.internal.measurement.zzdl.zzb(r3, r4, r7)
            long r8 = r7.zzadb
            long r8 = com.google.android.gms.internal.measurement.zzeb.zzbm(r8)
            r11.zzby(r8)
            goto L_0x00b4
        L_0x00cc:
            if (r6 != r10) goto L_0x00f0
            com.google.android.gms.internal.measurement.zzfa r11 = (com.google.android.gms.internal.measurement.zzfa) r11
            int r1 = com.google.android.gms.internal.measurement.zzdl.zza(r3, r4, r7)
            int r2 = r7.zzada
            int r2 = r2 + r1
        L_0x00d7:
            if (r1 >= r2) goto L_0x00e7
            int r1 = com.google.android.gms.internal.measurement.zzdl.zza(r3, r1, r7)
            int r4 = r7.zzada
            int r4 = com.google.android.gms.internal.measurement.zzeb.zzaz(r4)
            r11.zzbu(r4)
            goto L_0x00d7
        L_0x00e7:
            if (r1 != r2) goto L_0x00eb
            goto L_0x0423
        L_0x00eb:
            com.google.android.gms.internal.measurement.zzfi r1 = com.google.android.gms.internal.measurement.zzfi.zzut()
            throw r1
        L_0x00f0:
            if (r6 != 0) goto L_0x0422
            com.google.android.gms.internal.measurement.zzfa r11 = (com.google.android.gms.internal.measurement.zzfa) r11
            int r1 = com.google.android.gms.internal.measurement.zzdl.zza(r3, r4, r7)
            int r4 = r7.zzada
            int r4 = com.google.android.gms.internal.measurement.zzeb.zzaz(r4)
            r11.zzbu(r4)
        L_0x0101:
            if (r1 >= r5) goto L_0x0423
            int r4 = com.google.android.gms.internal.measurement.zzdl.zza(r3, r1, r7)
            int r6 = r7.zzada
            if (r2 != r6) goto L_0x0423
            int r1 = com.google.android.gms.internal.measurement.zzdl.zza(r3, r4, r7)
            int r4 = r7.zzada
            int r4 = com.google.android.gms.internal.measurement.zzeb.zzaz(r4)
            r11.zzbu(r4)
            goto L_0x0101
        L_0x0119:
            if (r6 != r10) goto L_0x0120
            int r2 = com.google.android.gms.internal.measurement.zzdl.zza(r3, r4, r11, r7)
            goto L_0x0131
        L_0x0120:
            if (r6 != 0) goto L_0x0422
            r2 = r21
            r3 = r18
            r4 = r19
            r5 = r20
            r6 = r11
            r7 = r30
            int r2 = com.google.android.gms.internal.measurement.zzdl.zza(r2, r3, r4, r5, r6, r7)
        L_0x0131:
            com.google.android.gms.internal.measurement.zzey r1 = (com.google.android.gms.internal.measurement.zzey) r1
            com.google.android.gms.internal.measurement.zzhs r3 = r1.zzahz
            com.google.android.gms.internal.measurement.zzhs r4 = com.google.android.gms.internal.measurement.zzhs.zzwq()
            if (r3 != r4) goto L_0x013c
            r3 = 0
        L_0x013c:
            com.google.android.gms.internal.measurement.zzfe r4 = r0.zzbz(r8)
            com.google.android.gms.internal.measurement.zzhp<?, ?> r5 = r0.zzakx
            r6 = r22
            java.lang.Object r3 = com.google.android.gms.internal.measurement.zzgz.zza(r6, r11, r4, r3, r5)
            com.google.android.gms.internal.measurement.zzhs r3 = (com.google.android.gms.internal.measurement.zzhs) r3
            if (r3 == 0) goto L_0x014e
            r1.zzahz = r3
        L_0x014e:
            r1 = r2
            goto L_0x0423
        L_0x0151:
            if (r6 != r10) goto L_0x0422
            int r1 = com.google.android.gms.internal.measurement.zzdl.zza(r3, r4, r7)
            int r4 = r7.zzada
            if (r4 < 0) goto L_0x01a4
            int r6 = r3.length
            int r6 = r6 - r1
            if (r4 > r6) goto L_0x019f
            if (r4 != 0) goto L_0x0167
            com.google.android.gms.internal.measurement.zzdp r4 = com.google.android.gms.internal.measurement.zzdp.zzadh
            r11.add(r4)
            goto L_0x016f
        L_0x0167:
            com.google.android.gms.internal.measurement.zzdp r6 = com.google.android.gms.internal.measurement.zzdp.zzb(r3, r1, r4)
            r11.add(r6)
        L_0x016e:
            int r1 = r1 + r4
        L_0x016f:
            if (r1 >= r5) goto L_0x0423
            int r4 = com.google.android.gms.internal.measurement.zzdl.zza(r3, r1, r7)
            int r6 = r7.zzada
            if (r2 != r6) goto L_0x0423
            int r1 = com.google.android.gms.internal.measurement.zzdl.zza(r3, r4, r7)
            int r4 = r7.zzada
            if (r4 < 0) goto L_0x019a
            int r6 = r3.length
            int r6 = r6 - r1
            if (r4 > r6) goto L_0x0195
            if (r4 != 0) goto L_0x018d
            com.google.android.gms.internal.measurement.zzdp r4 = com.google.android.gms.internal.measurement.zzdp.zzadh
            r11.add(r4)
            goto L_0x016f
        L_0x018d:
            com.google.android.gms.internal.measurement.zzdp r6 = com.google.android.gms.internal.measurement.zzdp.zzb(r3, r1, r4)
            r11.add(r6)
            goto L_0x016e
        L_0x0195:
            com.google.android.gms.internal.measurement.zzfi r1 = com.google.android.gms.internal.measurement.zzfi.zzut()
            throw r1
        L_0x019a:
            com.google.android.gms.internal.measurement.zzfi r1 = com.google.android.gms.internal.measurement.zzfi.zzuu()
            throw r1
        L_0x019f:
            com.google.android.gms.internal.measurement.zzfi r1 = com.google.android.gms.internal.measurement.zzfi.zzut()
            throw r1
        L_0x01a4:
            com.google.android.gms.internal.measurement.zzfi r1 = com.google.android.gms.internal.measurement.zzfi.zzuu()
            throw r1
        L_0x01a9:
            if (r6 != r10) goto L_0x0422
            com.google.android.gms.internal.measurement.zzgx r1 = r0.zzbx(r8)
            r22 = r1
            r23 = r21
            r24 = r18
            r25 = r19
            r26 = r20
            r27 = r11
            r28 = r30
            int r1 = com.google.android.gms.internal.measurement.zzdl.zza(r22, r23, r24, r25, r26, r27, r28)
            goto L_0x0423
        L_0x01c3:
            if (r6 != r10) goto L_0x0422
            r8 = 536870912(0x20000000, double:2.652494739E-315)
            long r8 = r25 & r8
            java.lang.String r1 = ""
            int r6 = (r8 > r14 ? 1 : (r8 == r14 ? 0 : -1))
            if (r6 != 0) goto L_0x0216
            int r4 = com.google.android.gms.internal.measurement.zzdl.zza(r3, r4, r7)
            int r6 = r7.zzada
            if (r6 < 0) goto L_0x0211
            if (r6 != 0) goto L_0x01de
            r11.add(r1)
            goto L_0x01e9
        L_0x01de:
            java.lang.String r8 = new java.lang.String
            java.nio.charset.Charset r9 = com.google.android.gms.internal.measurement.zzez.UTF_8
            r8.<init>(r3, r4, r6, r9)
            r11.add(r8)
        L_0x01e8:
            int r4 = r4 + r6
        L_0x01e9:
            if (r4 >= r5) goto L_0x0422
            int r6 = com.google.android.gms.internal.measurement.zzdl.zza(r3, r4, r7)
            int r8 = r7.zzada
            if (r2 != r8) goto L_0x0422
            int r4 = com.google.android.gms.internal.measurement.zzdl.zza(r3, r6, r7)
            int r6 = r7.zzada
            if (r6 < 0) goto L_0x020c
            if (r6 != 0) goto L_0x0201
            r11.add(r1)
            goto L_0x01e9
        L_0x0201:
            java.lang.String r8 = new java.lang.String
            java.nio.charset.Charset r9 = com.google.android.gms.internal.measurement.zzez.UTF_8
            r8.<init>(r3, r4, r6, r9)
            r11.add(r8)
            goto L_0x01e8
        L_0x020c:
            com.google.android.gms.internal.measurement.zzfi r1 = com.google.android.gms.internal.measurement.zzfi.zzuu()
            throw r1
        L_0x0211:
            com.google.android.gms.internal.measurement.zzfi r1 = com.google.android.gms.internal.measurement.zzfi.zzuu()
            throw r1
        L_0x0216:
            int r4 = com.google.android.gms.internal.measurement.zzdl.zza(r3, r4, r7)
            int r6 = r7.zzada
            if (r6 < 0) goto L_0x0271
            if (r6 != 0) goto L_0x0224
            r11.add(r1)
            goto L_0x0237
        L_0x0224:
            int r8 = r4 + r6
            boolean r9 = com.google.android.gms.internal.measurement.zzhy.zzf(r3, r4, r8)
            if (r9 == 0) goto L_0x026c
            java.lang.String r9 = new java.lang.String
            java.nio.charset.Charset r10 = com.google.android.gms.internal.measurement.zzez.UTF_8
            r9.<init>(r3, r4, r6, r10)
            r11.add(r9)
        L_0x0236:
            r4 = r8
        L_0x0237:
            if (r4 >= r5) goto L_0x0422
            int r6 = com.google.android.gms.internal.measurement.zzdl.zza(r3, r4, r7)
            int r8 = r7.zzada
            if (r2 != r8) goto L_0x0422
            int r4 = com.google.android.gms.internal.measurement.zzdl.zza(r3, r6, r7)
            int r6 = r7.zzada
            if (r6 < 0) goto L_0x0267
            if (r6 != 0) goto L_0x024f
            r11.add(r1)
            goto L_0x0237
        L_0x024f:
            int r8 = r4 + r6
            boolean r9 = com.google.android.gms.internal.measurement.zzhy.zzf(r3, r4, r8)
            if (r9 == 0) goto L_0x0262
            java.lang.String r9 = new java.lang.String
            java.nio.charset.Charset r10 = com.google.android.gms.internal.measurement.zzez.UTF_8
            r9.<init>(r3, r4, r6, r10)
            r11.add(r9)
            goto L_0x0236
        L_0x0262:
            com.google.android.gms.internal.measurement.zzfi r1 = com.google.android.gms.internal.measurement.zzfi.zzvb()
            throw r1
        L_0x0267:
            com.google.android.gms.internal.measurement.zzfi r1 = com.google.android.gms.internal.measurement.zzfi.zzuu()
            throw r1
        L_0x026c:
            com.google.android.gms.internal.measurement.zzfi r1 = com.google.android.gms.internal.measurement.zzfi.zzvb()
            throw r1
        L_0x0271:
            com.google.android.gms.internal.measurement.zzfi r1 = com.google.android.gms.internal.measurement.zzfi.zzuu()
            throw r1
        L_0x0276:
            r1 = 0
            if (r6 != r10) goto L_0x029e
            com.google.android.gms.internal.measurement.zzdn r11 = (com.google.android.gms.internal.measurement.zzdn) r11
            int r2 = com.google.android.gms.internal.measurement.zzdl.zza(r3, r4, r7)
            int r4 = r7.zzada
            int r4 = r4 + r2
        L_0x0282:
            if (r2 >= r4) goto L_0x0295
            int r2 = com.google.android.gms.internal.measurement.zzdl.zzb(r3, r2, r7)
            long r5 = r7.zzadb
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
            com.google.android.gms.internal.measurement.zzfi r1 = com.google.android.gms.internal.measurement.zzfi.zzut()
            throw r1
        L_0x029e:
            if (r6 != 0) goto L_0x0422
            com.google.android.gms.internal.measurement.zzdn r11 = (com.google.android.gms.internal.measurement.zzdn) r11
            int r4 = com.google.android.gms.internal.measurement.zzdl.zzb(r3, r4, r7)
            long r8 = r7.zzadb
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
            int r6 = com.google.android.gms.internal.measurement.zzdl.zza(r3, r4, r7)
            int r8 = r7.zzada
            if (r2 != r8) goto L_0x0422
            int r4 = com.google.android.gms.internal.measurement.zzdl.zzb(r3, r6, r7)
            long r8 = r7.zzadb
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
            com.google.android.gms.internal.measurement.zzfa r11 = (com.google.android.gms.internal.measurement.zzfa) r11
            int r1 = com.google.android.gms.internal.measurement.zzdl.zza(r3, r4, r7)
            int r2 = r7.zzada
            int r2 = r2 + r1
        L_0x02d8:
            if (r1 >= r2) goto L_0x02e4
            int r4 = com.google.android.gms.internal.measurement.zzdl.zza(r3, r1)
            r11.zzbu(r4)
            int r1 = r1 + 4
            goto L_0x02d8
        L_0x02e4:
            if (r1 != r2) goto L_0x02e8
            goto L_0x0423
        L_0x02e8:
            com.google.android.gms.internal.measurement.zzfi r1 = com.google.android.gms.internal.measurement.zzfi.zzut()
            throw r1
        L_0x02ed:
            if (r6 != r9) goto L_0x0422
            com.google.android.gms.internal.measurement.zzfa r11 = (com.google.android.gms.internal.measurement.zzfa) r11
            int r1 = com.google.android.gms.internal.measurement.zzdl.zza(r18, r19)
            r11.zzbu(r1)
        L_0x02f8:
            int r1 = r4 + 4
            if (r1 >= r5) goto L_0x0423
            int r4 = com.google.android.gms.internal.measurement.zzdl.zza(r3, r1, r7)
            int r6 = r7.zzada
            if (r2 != r6) goto L_0x0423
            int r1 = com.google.android.gms.internal.measurement.zzdl.zza(r3, r4)
            r11.zzbu(r1)
            goto L_0x02f8
        L_0x030c:
            if (r6 != r10) goto L_0x032c
            com.google.android.gms.internal.measurement.zzfw r11 = (com.google.android.gms.internal.measurement.zzfw) r11
            int r1 = com.google.android.gms.internal.measurement.zzdl.zza(r3, r4, r7)
            int r2 = r7.zzada
            int r2 = r2 + r1
        L_0x0317:
            if (r1 >= r2) goto L_0x0323
            long r4 = com.google.android.gms.internal.measurement.zzdl.zzb(r3, r1)
            r11.zzby(r4)
            int r1 = r1 + 8
            goto L_0x0317
        L_0x0323:
            if (r1 != r2) goto L_0x0327
            goto L_0x0423
        L_0x0327:
            com.google.android.gms.internal.measurement.zzfi r1 = com.google.android.gms.internal.measurement.zzfi.zzut()
            throw r1
        L_0x032c:
            if (r6 != r13) goto L_0x0422
            com.google.android.gms.internal.measurement.zzfw r11 = (com.google.android.gms.internal.measurement.zzfw) r11
            long r8 = com.google.android.gms.internal.measurement.zzdl.zzb(r18, r19)
            r11.zzby(r8)
        L_0x0337:
            int r1 = r4 + 8
            if (r1 >= r5) goto L_0x0423
            int r4 = com.google.android.gms.internal.measurement.zzdl.zza(r3, r1, r7)
            int r6 = r7.zzada
            if (r2 != r6) goto L_0x0423
            long r8 = com.google.android.gms.internal.measurement.zzdl.zzb(r3, r4)
            r11.zzby(r8)
            goto L_0x0337
        L_0x034b:
            if (r6 != r10) goto L_0x0353
            int r1 = com.google.android.gms.internal.measurement.zzdl.zza(r3, r4, r11, r7)
            goto L_0x0423
        L_0x0353:
            if (r6 != 0) goto L_0x0422
            r22 = r18
            r23 = r19
            r24 = r20
            r25 = r11
            r26 = r30
            int r1 = com.google.android.gms.internal.measurement.zzdl.zza(r21, r22, r23, r24, r25, r26)
            goto L_0x0423
        L_0x0365:
            if (r6 != r10) goto L_0x0385
            com.google.android.gms.internal.measurement.zzfw r11 = (com.google.android.gms.internal.measurement.zzfw) r11
            int r1 = com.google.android.gms.internal.measurement.zzdl.zza(r3, r4, r7)
            int r2 = r7.zzada
            int r2 = r2 + r1
        L_0x0370:
            if (r1 >= r2) goto L_0x037c
            int r1 = com.google.android.gms.internal.measurement.zzdl.zzb(r3, r1, r7)
            long r4 = r7.zzadb
            r11.zzby(r4)
            goto L_0x0370
        L_0x037c:
            if (r1 != r2) goto L_0x0380
            goto L_0x0423
        L_0x0380:
            com.google.android.gms.internal.measurement.zzfi r1 = com.google.android.gms.internal.measurement.zzfi.zzut()
            throw r1
        L_0x0385:
            if (r6 != 0) goto L_0x0422
            com.google.android.gms.internal.measurement.zzfw r11 = (com.google.android.gms.internal.measurement.zzfw) r11
            int r1 = com.google.android.gms.internal.measurement.zzdl.zzb(r3, r4, r7)
            long r8 = r7.zzadb
            r11.zzby(r8)
        L_0x0392:
            if (r1 >= r5) goto L_0x0423
            int r4 = com.google.android.gms.internal.measurement.zzdl.zza(r3, r1, r7)
            int r6 = r7.zzada
            if (r2 != r6) goto L_0x0423
            int r1 = com.google.android.gms.internal.measurement.zzdl.zzb(r3, r4, r7)
            long r8 = r7.zzadb
            r11.zzby(r8)
            goto L_0x0392
        L_0x03a6:
            if (r6 != r10) goto L_0x03c5
            com.google.android.gms.internal.measurement.zzeu r11 = (com.google.android.gms.internal.measurement.zzeu) r11
            int r1 = com.google.android.gms.internal.measurement.zzdl.zza(r3, r4, r7)
            int r2 = r7.zzada
            int r2 = r2 + r1
        L_0x03b1:
            if (r1 >= r2) goto L_0x03bd
            float r4 = com.google.android.gms.internal.measurement.zzdl.zzd(r3, r1)
            r11.zzc(r4)
            int r1 = r1 + 4
            goto L_0x03b1
        L_0x03bd:
            if (r1 != r2) goto L_0x03c0
            goto L_0x0423
        L_0x03c0:
            com.google.android.gms.internal.measurement.zzfi r1 = com.google.android.gms.internal.measurement.zzfi.zzut()
            throw r1
        L_0x03c5:
            if (r6 != r9) goto L_0x0422
            com.google.android.gms.internal.measurement.zzeu r11 = (com.google.android.gms.internal.measurement.zzeu) r11
            float r1 = com.google.android.gms.internal.measurement.zzdl.zzd(r18, r19)
            r11.zzc(r1)
        L_0x03d0:
            int r1 = r4 + 4
            if (r1 >= r5) goto L_0x0423
            int r4 = com.google.android.gms.internal.measurement.zzdl.zza(r3, r1, r7)
            int r6 = r7.zzada
            if (r2 != r6) goto L_0x0423
            float r1 = com.google.android.gms.internal.measurement.zzdl.zzd(r3, r4)
            r11.zzc(r1)
            goto L_0x03d0
        L_0x03e4:
            if (r6 != r10) goto L_0x0403
            com.google.android.gms.internal.measurement.zzeh r11 = (com.google.android.gms.internal.measurement.zzeh) r11
            int r1 = com.google.android.gms.internal.measurement.zzdl.zza(r3, r4, r7)
            int r2 = r7.zzada
            int r2 = r2 + r1
        L_0x03ef:
            if (r1 >= r2) goto L_0x03fb
            double r4 = com.google.android.gms.internal.measurement.zzdl.zzc(r3, r1)
            r11.zzf(r4)
            int r1 = r1 + 8
            goto L_0x03ef
        L_0x03fb:
            if (r1 != r2) goto L_0x03fe
            goto L_0x0423
        L_0x03fe:
            com.google.android.gms.internal.measurement.zzfi r1 = com.google.android.gms.internal.measurement.zzfi.zzut()
            throw r1
        L_0x0403:
            if (r6 != r13) goto L_0x0422
            com.google.android.gms.internal.measurement.zzeh r11 = (com.google.android.gms.internal.measurement.zzeh) r11
            double r8 = com.google.android.gms.internal.measurement.zzdl.zzc(r18, r19)
            r11.zzf(r8)
        L_0x040e:
            int r1 = r4 + 8
            if (r1 >= r5) goto L_0x0423
            int r4 = com.google.android.gms.internal.measurement.zzdl.zza(r3, r1, r7)
            int r6 = r7.zzada
            if (r2 != r6) goto L_0x0423
            double r8 = com.google.android.gms.internal.measurement.zzdl.zzc(r3, r4)
            r11.zzf(r8)
            goto L_0x040e
        L_0x0422:
            r1 = r4
        L_0x0423:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzgm.zza(java.lang.Object, byte[], int, int, int, int, int, int, long, int, long, com.google.android.gms.internal.measurement.zzdk):int");
    }

    /* JADX WARNING: type inference failed for: r10v4, types: [int] */
    /* JADX WARNING: type inference failed for: r10v11 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final <K, V> int zza(T r8, byte[] r9, int r10, int r11, int r12, long r13, com.google.android.gms.internal.measurement.zzdk r15) throws java.io.IOException {
        /*
            r7 = this;
            sun.misc.Unsafe r0 = zzaki
            java.lang.Object r12 = r7.zzby(r12)
            java.lang.Object r1 = r0.getObject(r8, r13)
            com.google.android.gms.internal.measurement.zzgb r2 = r7.zzakz
            boolean r2 = r2.zzo(r1)
            if (r2 == 0) goto L_0x0021
            com.google.android.gms.internal.measurement.zzgb r2 = r7.zzakz
            java.lang.Object r2 = r2.zzq(r12)
            com.google.android.gms.internal.measurement.zzgb r3 = r7.zzakz
            r3.zzb(r2, r1)
            r0.putObject(r8, r13, r2)
            r1 = r2
        L_0x0021:
            com.google.android.gms.internal.measurement.zzgb r8 = r7.zzakz
            com.google.android.gms.internal.measurement.zzfz r8 = r8.zzr(r12)
            com.google.android.gms.internal.measurement.zzgb r12 = r7.zzakz
            java.util.Map r12 = r12.zzm(r1)
            int r10 = com.google.android.gms.internal.measurement.zzdl.zza(r9, r10, r15)
            int r13 = r15.zzada
            if (r13 < 0) goto L_0x0097
            int r14 = r11 - r10
            if (r13 > r14) goto L_0x0097
            int r13 = r13 + r10
            K r14 = r8.zzakc
            V r0 = r8.zzaba
        L_0x003e:
            if (r10 >= r13) goto L_0x008c
            int r1 = r10 + 1
            byte r10 = r9[r10]
            if (r10 >= 0) goto L_0x004c
            int r1 = com.google.android.gms.internal.measurement.zzdl.zza(r10, r9, r1, r15)
            int r10 = r15.zzada
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
            com.google.android.gms.internal.measurement.zzig r1 = r8.zzakd
            int r1 = r1.zzxa()
            if (r3 != r1) goto L_0x0087
            com.google.android.gms.internal.measurement.zzig r4 = r8.zzakd
            V r10 = r8.zzaba
            java.lang.Class r5 = r10.getClass()
            r1 = r9
            r3 = r11
            r6 = r15
            int r10 = zza(r1, r2, r3, r4, r5, r6)
            java.lang.Object r0 = r15.zzadc
            goto L_0x003e
        L_0x0072:
            com.google.android.gms.internal.measurement.zzig r1 = r8.zzakb
            int r1 = r1.zzxa()
            if (r3 != r1) goto L_0x0087
            com.google.android.gms.internal.measurement.zzig r4 = r8.zzakb
            r5 = 0
            r1 = r9
            r3 = r11
            r6 = r15
            int r10 = zza(r1, r2, r3, r4, r5, r6)
            java.lang.Object r14 = r15.zzadc
            goto L_0x003e
        L_0x0087:
            int r10 = com.google.android.gms.internal.measurement.zzdl.zza(r10, r9, r2, r11, r15)
            goto L_0x003e
        L_0x008c:
            if (r10 != r13) goto L_0x0092
            r12.put(r14, r0)
            return r13
        L_0x0092:
            com.google.android.gms.internal.measurement.zzfi r8 = com.google.android.gms.internal.measurement.zzfi.zzva()
            throw r8
        L_0x0097:
            com.google.android.gms.internal.measurement.zzfi r8 = com.google.android.gms.internal.measurement.zzfi.zzut()
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzgm.zza(java.lang.Object, byte[], int, int, int, long, com.google.android.gms.internal.measurement.zzdk):int");
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
    private final int zza(T r17, byte[] r18, int r19, int r20, int r21, int r22, int r23, int r24, int r25, long r26, int r28, com.google.android.gms.internal.measurement.zzdk r29) throws java.io.IOException {
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
            sun.misc.Unsafe r12 = zzaki
            int[] r7 = r0.zzakj
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
            com.google.android.gms.internal.measurement.zzgx r2 = r0.zzbx(r6)
            r3 = r18
            r4 = r19
            r5 = r20
            r6 = r7
            r7 = r29
            int r2 = com.google.android.gms.internal.measurement.zzdl.zza(r2, r3, r4, r5, r6, r7)
            int r3 = r12.getInt(r1, r13)
            if (r3 != r8) goto L_0x004b
            java.lang.Object r15 = r12.getObject(r1, r9)
            goto L_0x004c
        L_0x004b:
            r15 = 0
        L_0x004c:
            if (r15 != 0) goto L_0x0055
            java.lang.Object r3 = r11.zzadc
            r12.putObject(r1, r9, r3)
            goto L_0x019d
        L_0x0055:
            java.lang.Object r3 = r11.zzadc
            java.lang.Object r3 = com.google.android.gms.internal.measurement.zzez.zza(r15, r3)
            r12.putObject(r1, r9, r3)
            goto L_0x019d
        L_0x0060:
            if (r5 != 0) goto L_0x01a1
            int r2 = com.google.android.gms.internal.measurement.zzdl.zzb(r3, r4, r11)
            long r3 = r11.zzadb
            long r3 = com.google.android.gms.internal.measurement.zzeb.zzbm(r3)
            java.lang.Long r3 = java.lang.Long.valueOf(r3)
            r12.putObject(r1, r9, r3)
            goto L_0x019d
        L_0x0075:
            if (r5 != 0) goto L_0x01a1
            int r2 = com.google.android.gms.internal.measurement.zzdl.zza(r3, r4, r11)
            int r3 = r11.zzada
            int r3 = com.google.android.gms.internal.measurement.zzeb.zzaz(r3)
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r12.putObject(r1, r9, r3)
            goto L_0x019d
        L_0x008a:
            if (r5 != 0) goto L_0x01a1
            int r3 = com.google.android.gms.internal.measurement.zzdl.zza(r3, r4, r11)
            int r4 = r11.zzada
            com.google.android.gms.internal.measurement.zzfe r5 = r0.zzbz(r6)
            if (r5 == 0) goto L_0x00ae
            boolean r5 = r5.zzg(r4)
            if (r5 == 0) goto L_0x009f
            goto L_0x00ae
        L_0x009f:
            com.google.android.gms.internal.measurement.zzhs r1 = zzu(r17)
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
            int r2 = com.google.android.gms.internal.measurement.zzdl.zze(r3, r4, r11)
            java.lang.Object r3 = r11.zzadc
            r12.putObject(r1, r9, r3)
            goto L_0x019d
        L_0x00c5:
            if (r5 != r15) goto L_0x01a1
            com.google.android.gms.internal.measurement.zzgx r2 = r0.zzbx(r6)
            r5 = r20
            int r2 = com.google.android.gms.internal.measurement.zzdl.zza(r2, r3, r4, r5, r11)
            int r3 = r12.getInt(r1, r13)
            if (r3 != r8) goto L_0x00dc
            java.lang.Object r15 = r12.getObject(r1, r9)
            goto L_0x00dd
        L_0x00dc:
            r15 = 0
        L_0x00dd:
            if (r15 != 0) goto L_0x00e5
            java.lang.Object r3 = r11.zzadc
            r12.putObject(r1, r9, r3)
            goto L_0x00ee
        L_0x00e5:
            java.lang.Object r3 = r11.zzadc
            java.lang.Object r3 = com.google.android.gms.internal.measurement.zzez.zza(r15, r3)
            r12.putObject(r1, r9, r3)
        L_0x00ee:
            r12.putInt(r1, r13, r8)
            goto L_0x01a2
        L_0x00f3:
            if (r5 != r15) goto L_0x01a1
            int r2 = com.google.android.gms.internal.measurement.zzdl.zza(r3, r4, r11)
            int r4 = r11.zzada
            if (r4 != 0) goto L_0x0103
            java.lang.String r3 = ""
            r12.putObject(r1, r9, r3)
            goto L_0x0122
        L_0x0103:
            r5 = 536870912(0x20000000, float:1.0842022E-19)
            r5 = r24 & r5
            if (r5 == 0) goto L_0x0117
            int r5 = r2 + r4
            boolean r5 = com.google.android.gms.internal.measurement.zzhy.zzf(r3, r2, r5)
            if (r5 == 0) goto L_0x0112
            goto L_0x0117
        L_0x0112:
            com.google.android.gms.internal.measurement.zzfi r1 = com.google.android.gms.internal.measurement.zzfi.zzvb()
            throw r1
        L_0x0117:
            java.lang.String r5 = new java.lang.String
            java.nio.charset.Charset r6 = com.google.android.gms.internal.measurement.zzez.UTF_8
            r5.<init>(r3, r2, r4, r6)
            r12.putObject(r1, r9, r5)
            int r2 = r2 + r4
        L_0x0122:
            r12.putInt(r1, r13, r8)
            goto L_0x01a2
        L_0x0127:
            if (r5 != 0) goto L_0x01a1
            int r2 = com.google.android.gms.internal.measurement.zzdl.zzb(r3, r4, r11)
            long r3 = r11.zzadb
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
            int r2 = com.google.android.gms.internal.measurement.zzdl.zza(r18, r19)
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r12.putObject(r1, r9, r2)
            goto L_0x018a
        L_0x014e:
            r2 = 1
            if (r5 != r2) goto L_0x01a1
            long r2 = com.google.android.gms.internal.measurement.zzdl.zzb(r18, r19)
            java.lang.Long r2 = java.lang.Long.valueOf(r2)
            r12.putObject(r1, r9, r2)
            goto L_0x019b
        L_0x015d:
            if (r5 != 0) goto L_0x01a1
            int r2 = com.google.android.gms.internal.measurement.zzdl.zza(r3, r4, r11)
            int r3 = r11.zzada
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r12.putObject(r1, r9, r3)
            goto L_0x019d
        L_0x016d:
            if (r5 != 0) goto L_0x01a1
            int r2 = com.google.android.gms.internal.measurement.zzdl.zzb(r3, r4, r11)
            long r3 = r11.zzadb
            java.lang.Long r3 = java.lang.Long.valueOf(r3)
            r12.putObject(r1, r9, r3)
            goto L_0x019d
        L_0x017d:
            if (r5 != r7) goto L_0x01a1
            float r2 = com.google.android.gms.internal.measurement.zzdl.zzd(r18, r19)
            java.lang.Float r2 = java.lang.Float.valueOf(r2)
            r12.putObject(r1, r9, r2)
        L_0x018a:
            int r2 = r4 + 4
            goto L_0x019d
        L_0x018d:
            r2 = 1
            if (r5 != r2) goto L_0x01a1
            double r2 = com.google.android.gms.internal.measurement.zzdl.zzc(r18, r19)
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
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzgm.zza(java.lang.Object, byte[], int, int, int, int, int, int, int, long, int, com.google.android.gms.internal.measurement.zzdk):int");
    }

    private final zzgx zzbx(int i) {
        int i2 = (i / 3) << 1;
        zzgx zzgx = (zzgx) this.zzakk[i2];
        if (zzgx != null) {
            return zzgx;
        }
        zzgx zzf = zzgt.zzvy().zzf((Class) this.zzakk[i2 + 1]);
        this.zzakk[i2] = zzf;
        return zzf;
    }

    private final Object zzby(int i) {
        return this.zzakk[(i / 3) << 1];
    }

    private final zzfe zzbz(int i) {
        return (zzfe) this.zzakk[((i / 3) << 1) + 1];
    }

    /* JADX WARNING: type inference failed for: r31v0, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r12v0 */
    /* JADX WARNING: type inference failed for: r12v1, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r0v13, types: [int, byte] */
    /* JADX WARNING: type inference failed for: r5v1, types: [int] */
    /* JADX WARNING: type inference failed for: r12v2 */
    /* JADX WARNING: type inference failed for: r3v5 */
    /* JADX WARNING: type inference failed for: r12v3 */
    /* JADX WARNING: type inference failed for: r3v6 */
    /* JADX WARNING: type inference failed for: r7v4 */
    /* JADX WARNING: type inference failed for: r0v18, types: [int] */
    /* JADX WARNING: type inference failed for: r1v12, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r12v4 */
    /* JADX WARNING: type inference failed for: r3v8 */
    /* JADX WARNING: type inference failed for: r0v27, types: [int] */
    /* JADX WARNING: type inference failed for: r1v16, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r12v5 */
    /* JADX WARNING: type inference failed for: r3v10 */
    /* JADX WARNING: type inference failed for: r3v11 */
    /* JADX WARNING: type inference failed for: r12v6 */
    /* JADX WARNING: type inference failed for: r3v12 */
    /* JADX WARNING: type inference failed for: r19v3 */
    /* JADX WARNING: type inference failed for: r8v6 */
    /* JADX WARNING: type inference failed for: r12v8 */
    /* JADX WARNING: type inference failed for: r25v0 */
    /* JADX WARNING: type inference failed for: r7v8 */
    /* JADX WARNING: type inference failed for: r25v1 */
    /* JADX WARNING: type inference failed for: r25v2 */
    /* JADX WARNING: type inference failed for: r25v3 */
    /* JADX WARNING: type inference failed for: r2v14, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r5v8, types: [int] */
    /* JADX WARNING: type inference failed for: r7v10 */
    /* JADX WARNING: type inference failed for: r12v10 */
    /* JADX WARNING: type inference failed for: r3v14 */
    /* JADX WARNING: type inference failed for: r2v15, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r25v4 */
    /* JADX WARNING: type inference failed for: r12v11 */
    /* JADX WARNING: type inference failed for: r3v16 */
    /* JADX WARNING: type inference failed for: r2v17, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r5v11, types: [int] */
    /* JADX WARNING: type inference failed for: r25v5 */
    /* JADX WARNING: type inference failed for: r25v6 */
    /* JADX WARNING: type inference failed for: r1v29, types: [int] */
    /* JADX WARNING: type inference failed for: r2v18, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r3v19 */
    /* JADX WARNING: type inference failed for: r8v11 */
    /* JADX WARNING: type inference failed for: r7v18 */
    /* JADX WARNING: type inference failed for: r12v13 */
    /* JADX WARNING: type inference failed for: r3v20 */
    /* JADX WARNING: type inference failed for: r12v14 */
    /* JADX WARNING: type inference failed for: r8v12 */
    /* JADX WARNING: type inference failed for: r3v21 */
    /* JADX WARNING: type inference failed for: r12v15 */
    /* JADX WARNING: type inference failed for: r8v13 */
    /* JADX WARNING: type inference failed for: r12v16 */
    /* JADX WARNING: type inference failed for: r8v14 */
    /* JADX WARNING: type inference failed for: r8v15 */
    /* JADX WARNING: type inference failed for: r12v17, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r8v16 */
    /* JADX WARNING: type inference failed for: r12v18, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r8v17 */
    /* JADX WARNING: type inference failed for: r12v19, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r3v22 */
    /* JADX WARNING: type inference failed for: r8v18 */
    /* JADX WARNING: type inference failed for: r12v20, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r8v19 */
    /* JADX WARNING: type inference failed for: r8v20 */
    /* JADX WARNING: type inference failed for: r12v21, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r12v22 */
    /* JADX WARNING: type inference failed for: r8v21 */
    /* JADX WARNING: type inference failed for: r3v23 */
    /* JADX WARNING: type inference failed for: r12v23 */
    /* JADX WARNING: type inference failed for: r8v22 */
    /* JADX WARNING: type inference failed for: r8v23 */
    /* JADX WARNING: type inference failed for: r12v24, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r8v24 */
    /* JADX WARNING: type inference failed for: r12v25, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r8v25 */
    /* JADX WARNING: type inference failed for: r12v26, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r8v26 */
    /* JADX WARNING: type inference failed for: r12v27, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r12v28 */
    /* JADX WARNING: type inference failed for: r8v27 */
    /* JADX WARNING: type inference failed for: r8v28 */
    /* JADX WARNING: type inference failed for: r12v29, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r8v29, types: [int] */
    /* JADX WARNING: type inference failed for: r12v30, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r8v30 */
    /* JADX WARNING: type inference failed for: r12v31, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r8v31 */
    /* JADX WARNING: type inference failed for: r12v32 */
    /* JADX WARNING: type inference failed for: r12v33, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r8v33 */
    /* JADX WARNING: type inference failed for: r12v34 */
    /* JADX WARNING: type inference failed for: r1v61, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r8v35 */
    /* JADX WARNING: type inference failed for: r12v35 */
    /* JADX WARNING: type inference failed for: r3v26 */
    /* JADX WARNING: type inference failed for: r8v36 */
    /* JADX WARNING: type inference failed for: r12v36 */
    /* JADX WARNING: type inference failed for: r8v37 */
    /* JADX WARNING: type inference failed for: r7v32 */
    /* JADX WARNING: type inference failed for: r5v30 */
    /* JADX WARNING: type inference failed for: r3v27, types: [int] */
    /* JADX WARNING: type inference failed for: r5v31 */
    /* JADX WARNING: type inference failed for: r12v37 */
    /* JADX WARNING: type inference failed for: r3v28 */
    /* JADX WARNING: type inference failed for: r12v38 */
    /* JADX WARNING: type inference failed for: r3v29 */
    /* JADX WARNING: type inference failed for: r12v39 */
    /* JADX WARNING: type inference failed for: r3v30 */
    /* JADX WARNING: type inference failed for: r25v7 */
    /* JADX WARNING: type inference failed for: r25v8 */
    /* JADX WARNING: type inference failed for: r12v40 */
    /* JADX WARNING: type inference failed for: r3v31 */
    /* JADX WARNING: type inference failed for: r12v41 */
    /* JADX WARNING: type inference failed for: r12v42 */
    /* JADX WARNING: type inference failed for: r8v39 */
    /* JADX WARNING: type inference failed for: r12v43 */
    /* JADX WARNING: type inference failed for: r8v40 */
    /* JADX WARNING: type inference failed for: r12v44 */
    /* JADX WARNING: type inference failed for: r12v45 */
    /* JADX WARNING: type inference failed for: r12v46 */
    /* JADX WARNING: type inference failed for: r12v47 */
    /* JADX WARNING: type inference failed for: r8v41 */
    /* JADX WARNING: type inference failed for: r12v48 */
    /* JADX WARNING: type inference failed for: r12v49 */
    /* JADX WARNING: type inference failed for: r12v50 */
    /* JADX WARNING: type inference failed for: r8v42 */
    /* JADX WARNING: type inference failed for: r12v51 */
    /* JADX WARNING: type inference failed for: r12v52 */
    /* JADX WARNING: type inference failed for: r12v53 */
    /* JADX WARNING: type inference failed for: r8v43 */
    /* JADX WARNING: type inference failed for: r8v44 */
    /* JADX WARNING: type inference failed for: r8v45 */
    /* JADX WARNING: type inference failed for: r12v54 */
    /* JADX WARNING: type inference failed for: r12v55 */
    /* JADX WARNING: type inference failed for: r12v56 */
    /* JADX WARNING: type inference failed for: r8v46 */
    /* JADX WARNING: type inference failed for: r12v57 */
    /* JADX WARNING: type inference failed for: r12v58 */
    /* JADX WARNING: type inference failed for: r12v59 */
    /* JADX WARNING: type inference failed for: r12v60 */
    /* JADX WARNING: type inference failed for: r8v47 */
    /* JADX WARNING: type inference failed for: r8v48 */
    /* JADX WARNING: type inference failed for: r12v61 */
    /* JADX WARNING: type inference failed for: r8v49 */
    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Code restructure failed: missing block: B:116:0x0372, code lost:
        if (r0 == r15) goto L_0x03e1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:124:0x03bb, code lost:
        if (r0 == r15) goto L_0x03e1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:172:0x0017, code lost:
        r12 = r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:173:0x0017, code lost:
        r12 = r12;
        r3 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0094, code lost:
        r7 = r4;
        r8 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x017a, code lost:
        r6 = r6 | r22;
        r12 = r12;
        r8 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x0219, code lost:
        r6 = r6 | r22;
        r12 = r12;
        r8 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x021b, code lost:
        r3 = r8;
        r2 = r9;
        r1 = r11;
        r9 = r13;
        r11 = r34;
        r13 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x0245, code lost:
        r32 = r7;
        r8 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x02bc, code lost:
        r0 = r7 + 8;
        r12 = r12;
        r8 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:0x02be, code lost:
        r6 = r6 | r22;
        r7 = r32;
        r12 = r12;
        r8 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:0x02c2, code lost:
        r3 = r8;
        r2 = r9;
        r1 = r11;
        r9 = r13;
        r12 = r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x02c6, code lost:
        r13 = r33;
        r11 = r34;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x02cc, code lost:
        r17 = r32;
        r19 = r6;
        r2 = r7;
        r7 = r8;
        r18 = r9;
        r26 = r10;
        r24 = r11;
        r6 = r34;
     */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=null, for r0v13, types: [int, byte] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte[], code=null, for r31v0, types: [byte[]] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r12v2
      assigns: []
      uses: []
      mth insns count: 602
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
    /* JADX WARNING: Removed duplicated region for block: B:131:0x03e7 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:135:0x03fc  */
    /* JADX WARNING: Removed duplicated region for block: B:143:0x043e  */
    /* JADX WARNING: Unknown variable types count: 61 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int zza(T r30, byte[] r31, int r32, int r33, int r34, com.google.android.gms.internal.measurement.zzdk r35) throws java.io.IOException {
        /*
            r29 = this;
            r15 = r29
            r14 = r30
            r12 = r31
            r13 = r33
            r11 = r34
            r9 = r35
            sun.misc.Unsafe r10 = zzaki
            r16 = 0
            r0 = r32
            r1 = -1
            r2 = 0
            r3 = 0
            r6 = 0
            r7 = -1
        L_0x0017:
            if (r0 >= r13) goto L_0x0480
            int r3 = r0 + 1
            byte r0 = r12[r0]
            if (r0 >= 0) goto L_0x0028
            int r0 = com.google.android.gms.internal.measurement.zzdl.zza(r0, r12, r3, r9)
            int r3 = r9.zzada
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
            int r1 = r15.zzp(r3, r2)
            goto L_0x003b
        L_0x0037:
            int r1 = r15.zzcd(r3)
        L_0x003b:
            r2 = r1
            r1 = -1
            if (r2 != r1) goto L_0x004e
            r24 = r3
            r2 = r4
            r19 = r6
            r17 = r7
            r26 = r10
            r6 = r11
            r18 = 0
            r7 = r5
            goto L_0x03e5
        L_0x004e:
            int[] r1 = r15.zzakj
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
            if (r11 > r5) goto L_0x02dc
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
                case 0: goto L_0x02a4;
                case 1: goto L_0x028a;
                case 2: goto L_0x0264;
                case 3: goto L_0x0264;
                case 4: goto L_0x0249;
                case 5: goto L_0x0224;
                case 6: goto L_0x0201;
                case 7: goto L_0x01d9;
                case 8: goto L_0x01b4;
                case 9: goto L_0x017e;
                case 10: goto L_0x0163;
                case 11: goto L_0x0249;
                case 12: goto L_0x0131;
                case 13: goto L_0x0201;
                case 14: goto L_0x0224;
                case 15: goto L_0x0116;
                case 16: goto L_0x00e9;
                case 17: goto L_0x0097;
                default: goto L_0x0088;
            }
        L_0x0088:
            r12 = r31
            r13 = r35
            r9 = r2
            r11 = r3
            r32 = r7
            r8 = r19
            r18 = -1
        L_0x0094:
            r7 = r4
            goto L_0x02cc
        L_0x0097:
            r8 = 3
            if (r0 != r8) goto L_0x00dd
            int r0 = r3 << 3
            r8 = r0 | 4
            com.google.android.gms.internal.measurement.zzgx r0 = r15.zzbx(r2)
            r1 = r31
            r9 = r2
            r2 = r4
            r11 = r3
            r3 = r33
            r4 = r8
            r8 = r19
            r18 = -1
            r5 = r35
            int r0 = com.google.android.gms.internal.measurement.zzdl.zza(r0, r1, r2, r3, r4, r5)
            r1 = r6 & r22
            if (r1 != 0) goto L_0x00c0
            r5 = r35
            java.lang.Object r1 = r5.zzadc
            r10.putObject(r14, r12, r1)
            goto L_0x00cf
        L_0x00c0:
            r5 = r35
            java.lang.Object r1 = r10.getObject(r14, r12)
            java.lang.Object r2 = r5.zzadc
            java.lang.Object r1 = com.google.android.gms.internal.measurement.zzez.zza(r1, r2)
            r10.putObject(r14, r12, r1)
        L_0x00cf:
            r6 = r6 | r22
            r12 = r31
            r13 = r33
            r3 = r8
            r2 = r9
            r1 = r11
            r11 = r34
            r9 = r5
            goto L_0x0017
        L_0x00dd:
            r9 = r2
            r11 = r3
            r8 = r19
            r18 = -1
            r12 = r31
            r13 = r35
            goto L_0x0245
        L_0x00e9:
            r5 = r35
            r9 = r2
            r11 = r3
            r8 = r19
            r18 = -1
            if (r0 != 0) goto L_0x0111
            r2 = r12
            r12 = r31
            int r13 = com.google.android.gms.internal.measurement.zzdl.zzb(r12, r4, r5)
            long r0 = r5.zzadb
            long r19 = com.google.android.gms.internal.measurement.zzeb.zzbm(r0)
            r0 = r10
            r1 = r30
            r32 = r13
            r13 = r5
            r4 = r19
            r0.putLong(r1, r2, r4)
            r6 = r6 | r22
            r0 = r32
            goto L_0x02c2
        L_0x0111:
            r12 = r31
            r13 = r5
            goto L_0x0245
        L_0x0116:
            r9 = r2
            r11 = r3
            r2 = r12
            r8 = r19
            r18 = -1
            r12 = r31
            r13 = r35
            if (r0 != 0) goto L_0x0245
            int r0 = com.google.android.gms.internal.measurement.zzdl.zza(r12, r4, r13)
            int r1 = r13.zzada
            int r1 = com.google.android.gms.internal.measurement.zzeb.zzaz(r1)
            r10.putInt(r14, r2, r1)
            goto L_0x017a
        L_0x0131:
            r9 = r2
            r11 = r3
            r2 = r12
            r8 = r19
            r18 = -1
            r12 = r31
            r13 = r35
            if (r0 != 0) goto L_0x0245
            int r0 = com.google.android.gms.internal.measurement.zzdl.zza(r12, r4, r13)
            int r1 = r13.zzada
            com.google.android.gms.internal.measurement.zzfe r4 = r15.zzbz(r9)
            if (r4 == 0) goto L_0x015f
            boolean r4 = r4.zzg(r1)
            if (r4 == 0) goto L_0x0151
            goto L_0x015f
        L_0x0151:
            com.google.android.gms.internal.measurement.zzhs r2 = zzu(r30)
            long r3 = (long) r1
            java.lang.Long r1 = java.lang.Long.valueOf(r3)
            r2.zzb(r8, r1)
            goto L_0x02c2
        L_0x015f:
            r10.putInt(r14, r2, r1)
            goto L_0x017a
        L_0x0163:
            r9 = r2
            r11 = r3
            r2 = r12
            r8 = r19
            r1 = 2
            r18 = -1
            r12 = r31
            r13 = r35
            if (r0 != r1) goto L_0x0245
            int r0 = com.google.android.gms.internal.measurement.zzdl.zze(r12, r4, r13)
            java.lang.Object r1 = r13.zzadc
            r10.putObject(r14, r2, r1)
        L_0x017a:
            r6 = r6 | r22
            goto L_0x02c2
        L_0x017e:
            r9 = r2
            r11 = r3
            r2 = r12
            r8 = r19
            r1 = 2
            r18 = -1
            r12 = r31
            r13 = r35
            if (r0 != r1) goto L_0x01b0
            com.google.android.gms.internal.measurement.zzgx r0 = r15.zzbx(r9)
            r5 = r33
            int r0 = com.google.android.gms.internal.measurement.zzdl.zza(r0, r12, r4, r5, r13)
            r1 = r6 & r22
            if (r1 != 0) goto L_0x01a1
            java.lang.Object r1 = r13.zzadc
            r10.putObject(r14, r2, r1)
            goto L_0x0219
        L_0x01a1:
            java.lang.Object r1 = r10.getObject(r14, r2)
            java.lang.Object r4 = r13.zzadc
            java.lang.Object r1 = com.google.android.gms.internal.measurement.zzez.zza(r1, r4)
            r10.putObject(r14, r2, r1)
            goto L_0x0219
        L_0x01b0:
            r5 = r33
            goto L_0x0245
        L_0x01b4:
            r5 = r33
            r9 = r2
            r11 = r3
            r2 = r12
            r8 = r19
            r1 = 2
            r18 = -1
            r12 = r31
            r13 = r35
            if (r0 != r1) goto L_0x0245
            r0 = 536870912(0x20000000, float:1.0842022E-19)
            r0 = r20 & r0
            if (r0 != 0) goto L_0x01cf
            int r0 = com.google.android.gms.internal.measurement.zzdl.zzc(r12, r4, r13)
            goto L_0x01d3
        L_0x01cf:
            int r0 = com.google.android.gms.internal.measurement.zzdl.zzd(r12, r4, r13)
        L_0x01d3:
            java.lang.Object r1 = r13.zzadc
            r10.putObject(r14, r2, r1)
            goto L_0x0219
        L_0x01d9:
            r5 = r33
            r9 = r2
            r11 = r3
            r2 = r12
            r8 = r19
            r18 = -1
            r12 = r31
            r13 = r35
            if (r0 != 0) goto L_0x0245
            int r0 = com.google.android.gms.internal.measurement.zzdl.zzb(r12, r4, r13)
            r32 = r0
            long r0 = r13.zzadb
            r19 = 0
            int r4 = (r0 > r19 ? 1 : (r0 == r19 ? 0 : -1))
            if (r4 == 0) goto L_0x01f8
            r0 = 1
            goto L_0x01f9
        L_0x01f8:
            r0 = 0
        L_0x01f9:
            com.google.android.gms.internal.measurement.zzhv.zza(r14, r2, r0)
            r6 = r6 | r22
            r0 = r32
            goto L_0x021b
        L_0x0201:
            r5 = r33
            r9 = r2
            r11 = r3
            r2 = r12
            r8 = r19
            r18 = -1
            r12 = r31
            r13 = r35
            if (r0 != r1) goto L_0x0245
            int r0 = com.google.android.gms.internal.measurement.zzdl.zza(r12, r4)
            r10.putInt(r14, r2, r0)
            int r0 = r4 + 4
        L_0x0219:
            r6 = r6 | r22
        L_0x021b:
            r3 = r8
            r2 = r9
            r1 = r11
            r9 = r13
            r11 = r34
            r13 = r5
            goto L_0x0017
        L_0x0224:
            r5 = r33
            r9 = r2
            r11 = r3
            r2 = r12
            r8 = r19
            r1 = 1
            r18 = -1
            r12 = r31
            r13 = r35
            if (r0 != r1) goto L_0x0245
            long r19 = com.google.android.gms.internal.measurement.zzdl.zzb(r12, r4)
            r0 = r10
            r1 = r30
            r32 = r7
            r7 = r4
            r4 = r19
            r0.putLong(r1, r2, r4)
            goto L_0x02bc
        L_0x0245:
            r32 = r7
            goto L_0x0094
        L_0x0249:
            r9 = r2
            r11 = r3
            r32 = r7
            r2 = r12
            r8 = r19
            r18 = -1
            r12 = r31
            r13 = r35
            r7 = r4
            if (r0 != 0) goto L_0x02cc
            int r0 = com.google.android.gms.internal.measurement.zzdl.zza(r12, r7, r13)
            int r1 = r13.zzada
            r10.putInt(r14, r2, r1)
            goto L_0x02be
        L_0x0264:
            r9 = r2
            r11 = r3
            r32 = r7
            r2 = r12
            r8 = r19
            r18 = -1
            r12 = r31
            r13 = r35
            r7 = r4
            if (r0 != 0) goto L_0x02cc
            int r7 = com.google.android.gms.internal.measurement.zzdl.zzb(r12, r7, r13)
            long r4 = r13.zzadb
            r0 = r10
            r1 = r30
            r0.putLong(r1, r2, r4)
            r6 = r6 | r22
            r0 = r7
            r3 = r8
            r2 = r9
            r1 = r11
            r9 = r13
            r7 = r32
            goto L_0x02c6
        L_0x028a:
            r9 = r2
            r11 = r3
            r32 = r7
            r2 = r12
            r8 = r19
            r18 = -1
            r12 = r31
            r13 = r35
            r7 = r4
            if (r0 != r1) goto L_0x02cc
            float r0 = com.google.android.gms.internal.measurement.zzdl.zzd(r12, r7)
            com.google.android.gms.internal.measurement.zzhv.zza(r14, r2, r0)
            int r0 = r7 + 4
            goto L_0x02be
        L_0x02a4:
            r9 = r2
            r11 = r3
            r32 = r7
            r2 = r12
            r8 = r19
            r1 = 1
            r18 = -1
            r12 = r31
            r13 = r35
            r7 = r4
            if (r0 != r1) goto L_0x02cc
            double r0 = com.google.android.gms.internal.measurement.zzdl.zzc(r12, r7)
            com.google.android.gms.internal.measurement.zzhv.zza(r14, r2, r0)
        L_0x02bc:
            int r0 = r7 + 8
        L_0x02be:
            r6 = r6 | r22
            r7 = r32
        L_0x02c2:
            r3 = r8
            r2 = r9
            r1 = r11
            r9 = r13
        L_0x02c6:
            r13 = r33
            r11 = r34
            goto L_0x0017
        L_0x02cc:
            r17 = r32
            r19 = r6
            r2 = r7
            r7 = r8
            r18 = r9
            r26 = r10
            r24 = r11
            r6 = r34
            goto L_0x03e5
        L_0x02dc:
            r5 = r3
            r17 = r7
            r8 = r19
            r18 = -1
            r7 = r4
            r27 = r12
            r12 = r31
            r13 = r9
            r9 = r2
            r2 = r27
            r1 = 27
            if (r11 != r1) goto L_0x0341
            r1 = 2
            if (r0 != r1) goto L_0x0334
            java.lang.Object r0 = r10.getObject(r14, r2)
            com.google.android.gms.internal.measurement.zzff r0 = (com.google.android.gms.internal.measurement.zzff) r0
            boolean r1 = r0.zzrx()
            if (r1 != 0) goto L_0x0311
            int r1 = r0.size()
            if (r1 != 0) goto L_0x0308
            r1 = 10
            goto L_0x030a
        L_0x0308:
            int r1 = r1 << 1
        L_0x030a:
            com.google.android.gms.internal.measurement.zzff r0 = r0.zzap(r1)
            r10.putObject(r14, r2, r0)
        L_0x0311:
            r11 = r0
            com.google.android.gms.internal.measurement.zzgx r0 = r15.zzbx(r9)
            r1 = r8
            r2 = r31
            r3 = r7
            r4 = r33
            r7 = r5
            r5 = r11
            r19 = r6
            r6 = r35
            int r0 = com.google.android.gms.internal.measurement.zzdl.zza(r0, r1, r2, r3, r4, r5, r6)
            r11 = r34
            r1 = r7
            r3 = r8
            r2 = r9
            r9 = r13
            r7 = r17
            r6 = r19
            r13 = r33
            goto L_0x0017
        L_0x0334:
            r19 = r6
            r24 = r5
            r15 = r7
            r25 = r8
            r18 = r9
            r26 = r10
            goto L_0x03be
        L_0x0341:
            r19 = r6
            r6 = r5
            r1 = 49
            if (r11 > r1) goto L_0x0390
            r5 = r20
            long r4 = (long) r5
            r1 = r0
            r0 = r29
            r32 = r1
            r1 = r30
            r22 = r2
            r2 = r31
            r3 = r7
            r20 = r4
            r4 = r33
            r5 = r8
            r24 = r6
            r15 = r7
            r7 = r32
            r25 = r8
            r8 = r9
            r18 = r9
            r26 = r10
            r9 = r20
            r12 = r22
            r14 = r35
            int r0 = r0.zza((T) r1, r2, r3, r4, r5, r6, r7, r8, r9, r11, r12, r14)
            if (r0 != r15) goto L_0x0376
            goto L_0x03e1
        L_0x0376:
            r15 = r29
            r14 = r30
            r12 = r31
            r13 = r33
            r11 = r34
            r9 = r35
            r7 = r17
            r2 = r18
            r6 = r19
            r1 = r24
            r3 = r25
        L_0x038c:
            r10 = r26
            goto L_0x0017
        L_0x0390:
            r32 = r0
            r22 = r2
            r24 = r6
            r15 = r7
            r25 = r8
            r18 = r9
            r26 = r10
            r5 = r20
            r0 = 50
            if (r11 != r0) goto L_0x03c4
            r7 = r32
            r0 = 2
            if (r7 != r0) goto L_0x03be
            r0 = r29
            r1 = r30
            r2 = r31
            r3 = r15
            r4 = r33
            r5 = r18
            r6 = r22
            r8 = r35
            int r0 = r0.zza((T) r1, r2, r3, r4, r5, r6, r8)
            if (r0 != r15) goto L_0x0376
            goto L_0x03e1
        L_0x03be:
            r6 = r34
            r2 = r15
        L_0x03c1:
            r7 = r25
            goto L_0x03e5
        L_0x03c4:
            r7 = r32
            r0 = r29
            r1 = r30
            r2 = r31
            r3 = r15
            r4 = r33
            r8 = r5
            r5 = r25
            r6 = r24
            r9 = r11
            r10 = r22
            r12 = r18
            r13 = r35
            int r0 = r0.zza((T) r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r12, r13)
            if (r0 != r15) goto L_0x0466
        L_0x03e1:
            r6 = r34
            r2 = r0
            goto L_0x03c1
        L_0x03e5:
            if (r7 != r6) goto L_0x03f6
            if (r6 != 0) goto L_0x03ea
            goto L_0x03f6
        L_0x03ea:
            r4 = -1
            r8 = r29
            r11 = r30
            r3 = r7
            r0 = r17
            r1 = r19
            goto L_0x048f
        L_0x03f6:
            r8 = r29
            boolean r0 = r8.zzako
            if (r0 == 0) goto L_0x043e
            r9 = r35
            com.google.android.gms.internal.measurement.zzel r0 = r9.zzadd
            com.google.android.gms.internal.measurement.zzel r1 = com.google.android.gms.internal.measurement.zzel.zztp()
            if (r0 == r1) goto L_0x043b
            com.google.android.gms.internal.measurement.zzgi r0 = r8.zzakn
            com.google.android.gms.internal.measurement.zzel r1 = r9.zzadd
            r10 = r24
            com.google.android.gms.internal.measurement.zzey$zze r0 = r1.zza(r0, r10)
            if (r0 != 0) goto L_0x042b
            com.google.android.gms.internal.measurement.zzhs r4 = zzu(r30)
            r0 = r7
            r1 = r31
            r3 = r33
            r5 = r35
            int r0 = com.google.android.gms.internal.measurement.zzdl.zza(r0, r1, r2, r3, r4, r5)
            r14 = r30
            r12 = r31
            r13 = r33
            r11 = r6
            r3 = r7
            r15 = r8
            goto L_0x0477
        L_0x042b:
            r11 = r30
            r0 = r11
            com.google.android.gms.internal.measurement.zzey$zzb r0 = (com.google.android.gms.internal.measurement.zzey.zzb) r0
            r0.zzuq()
            com.google.android.gms.internal.measurement.zzeo<java.lang.Object> r0 = r0.zzaic
            java.lang.NoSuchMethodError r0 = new java.lang.NoSuchMethodError
            r0.<init>()
            throw r0
        L_0x043b:
            r11 = r30
            goto L_0x0442
        L_0x043e:
            r11 = r30
            r9 = r35
        L_0x0442:
            r10 = r24
            com.google.android.gms.internal.measurement.zzhs r4 = zzu(r30)
            r0 = r7
            r1 = r31
            r3 = r33
            r5 = r35
            int r0 = com.google.android.gms.internal.measurement.zzdl.zza(r0, r1, r2, r3, r4, r5)
            r12 = r31
            r13 = r33
            r3 = r7
            r15 = r8
            r1 = r10
            r14 = r11
            r7 = r17
            r2 = r18
            r10 = r26
            r11 = r6
            r6 = r19
            goto L_0x0017
        L_0x0466:
            r10 = r24
            r7 = r25
            r15 = r29
            r14 = r30
            r12 = r31
            r13 = r33
            r11 = r34
            r9 = r35
            r3 = r7
        L_0x0477:
            r1 = r10
            r7 = r17
            r2 = r18
            r6 = r19
            goto L_0x038c
        L_0x0480:
            r19 = r6
            r17 = r7
            r26 = r10
            r6 = r11
            r11 = r14
            r8 = r15
            r2 = r0
            r0 = r17
            r1 = r19
            r4 = -1
        L_0x048f:
            if (r0 == r4) goto L_0x0497
            long r4 = (long) r0
            r0 = r26
            r0.putInt(r11, r4, r1)
        L_0x0497:
            r0 = 0
            int r1 = r8.zzakt
        L_0x049a:
            int r4 = r8.zzaku
            if (r1 >= r4) goto L_0x04ad
            int[] r4 = r8.zzaks
            r4 = r4[r1]
            com.google.android.gms.internal.measurement.zzhp<?, ?> r5 = r8.zzakx
            java.lang.Object r0 = r8.zza(r11, r4, (UB) r0, r5)
            com.google.android.gms.internal.measurement.zzhs r0 = (com.google.android.gms.internal.measurement.zzhs) r0
            int r1 = r1 + 1
            goto L_0x049a
        L_0x04ad:
            if (r0 == 0) goto L_0x04b4
            com.google.android.gms.internal.measurement.zzhp<?, ?> r1 = r8.zzakx
            r1.zzf(r11, r0)
        L_0x04b4:
            if (r6 != 0) goto L_0x04c0
            r0 = r33
            if (r2 != r0) goto L_0x04bb
            goto L_0x04c6
        L_0x04bb:
            com.google.android.gms.internal.measurement.zzfi r0 = com.google.android.gms.internal.measurement.zzfi.zzva()
            throw r0
        L_0x04c0:
            r0 = r33
            if (r2 > r0) goto L_0x04c7
            if (r3 != r6) goto L_0x04c7
        L_0x04c6:
            return r2
        L_0x04c7:
            com.google.android.gms.internal.measurement.zzfi r0 = com.google.android.gms.internal.measurement.zzfi.zzva()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzgm.zza(java.lang.Object, byte[], int, int, int, com.google.android.gms.internal.measurement.zzdk):int");
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
    /* JADX WARNING: Code restructure failed: missing block: B:111:0x0251, code lost:
        r12 = r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:112:0x0251, code lost:
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
    public final void zza(T r28, byte[] r29, int r30, int r31, com.google.android.gms.internal.measurement.zzdk r32) throws java.io.IOException {
        /*
            r27 = this;
            r15 = r27
            r14 = r28
            r12 = r29
            r13 = r31
            r11 = r32
            boolean r0 = r15.zzakq
            if (r0 == 0) goto L_0x025d
            sun.misc.Unsafe r9 = zzaki
            r10 = -1
            r16 = 0
            r0 = r30
            r1 = -1
            r2 = 0
        L_0x0017:
            if (r0 >= r13) goto L_0x0254
            int r3 = r0 + 1
            byte r0 = r12[r0]
            if (r0 >= 0) goto L_0x0029
            int r0 = com.google.android.gms.internal.measurement.zzdl.zza(r0, r12, r3, r11)
            int r3 = r11.zzada
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
            int r0 = r15.zzp(r7, r2)
            goto L_0x003d
        L_0x0039:
            int r0 = r15.zzcd(r7)
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
            int[] r0 = r15.zzakj
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
            int r6 = com.google.android.gms.internal.measurement.zzdl.zzb(r12, r8, r11)
            r19 = r1
            long r0 = r11.zzadb
            long r21 = com.google.android.gms.internal.measurement.zzeb.zzbm(r0)
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
            int r0 = com.google.android.gms.internal.measurement.zzdl.zza(r12, r8, r11)
            int r1 = r11.zzada
            int r1 = com.google.android.gms.internal.measurement.zzeb.zzaz(r1)
            r9.putInt(r14, r2, r1)
            goto L_0x015b
        L_0x0094:
            r2 = r1
            r10 = r4
            if (r6 != 0) goto L_0x015f
            int r0 = com.google.android.gms.internal.measurement.zzdl.zza(r12, r8, r11)
            int r1 = r11.zzada
            r9.putInt(r14, r2, r1)
            goto L_0x015b
        L_0x00a3:
            r2 = r1
            if (r6 != r10) goto L_0x01a4
            int r0 = com.google.android.gms.internal.measurement.zzdl.zze(r12, r8, r11)
            java.lang.Object r1 = r11.zzadc
            r9.putObject(r14, r2, r1)
            goto L_0x010b
        L_0x00b0:
            r2 = r1
            if (r6 != r10) goto L_0x01a4
            com.google.android.gms.internal.measurement.zzgx r0 = r15.zzbx(r4)
            int r0 = com.google.android.gms.internal.measurement.zzdl.zza(r0, r12, r8, r13, r11)
            java.lang.Object r1 = r9.getObject(r14, r2)
            if (r1 != 0) goto L_0x00c7
            java.lang.Object r1 = r11.zzadc
            r9.putObject(r14, r2, r1)
            goto L_0x010b
        L_0x00c7:
            java.lang.Object r5 = r11.zzadc
            java.lang.Object r1 = com.google.android.gms.internal.measurement.zzez.zza(r1, r5)
            r9.putObject(r14, r2, r1)
            goto L_0x010b
        L_0x00d1:
            r2 = r1
            if (r6 != r10) goto L_0x01a4
            r0 = 536870912(0x20000000, float:1.0842022E-19)
            r0 = r0 & r5
            if (r0 != 0) goto L_0x00de
            int r0 = com.google.android.gms.internal.measurement.zzdl.zzc(r12, r8, r11)
            goto L_0x00e2
        L_0x00de:
            int r0 = com.google.android.gms.internal.measurement.zzdl.zzd(r12, r8, r11)
        L_0x00e2:
            java.lang.Object r1 = r11.zzadc
            r9.putObject(r14, r2, r1)
            goto L_0x010b
        L_0x00e8:
            r2 = r1
            if (r6 != 0) goto L_0x01a4
            int r1 = com.google.android.gms.internal.measurement.zzdl.zzb(r12, r8, r11)
            long r5 = r11.zzadb
            r19 = 0
            int r8 = (r5 > r19 ? 1 : (r5 == r19 ? 0 : -1))
            if (r8 == 0) goto L_0x00f8
            goto L_0x00f9
        L_0x00f8:
            r0 = 0
        L_0x00f9:
            com.google.android.gms.internal.measurement.zzhv.zza(r14, r2, r0)
            r0 = r1
            goto L_0x010b
        L_0x00fe:
            r2 = r1
            r0 = 5
            if (r6 != r0) goto L_0x01a4
            int r0 = com.google.android.gms.internal.measurement.zzdl.zza(r12, r8)
            r9.putInt(r14, r2, r0)
            int r0 = r8 + 4
        L_0x010b:
            r2 = r4
            r1 = r7
            goto L_0x0251
        L_0x010f:
            r2 = r1
            if (r6 != r0) goto L_0x01a4
            long r5 = com.google.android.gms.internal.measurement.zzdl.zzb(r12, r8)
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
            int r0 = com.google.android.gms.internal.measurement.zzdl.zza(r12, r8, r11)
            int r1 = r11.zzada
            r9.putInt(r14, r2, r1)
            goto L_0x015b
        L_0x012d:
            r2 = r1
            r10 = r4
            if (r6 != 0) goto L_0x015f
            int r6 = com.google.android.gms.internal.measurement.zzdl.zzb(r12, r8, r11)
            long r4 = r11.zzadb
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
            float r0 = com.google.android.gms.internal.measurement.zzdl.zzd(r12, r8)
            com.google.android.gms.internal.measurement.zzhv.zza(r14, r2, r0)
            int r0 = r8 + 4
            goto L_0x015b
        L_0x014e:
            r2 = r1
            r10 = r4
            if (r6 != r0) goto L_0x015f
            double r0 = com.google.android.gms.internal.measurement.zzdl.zzc(r12, r8)
            com.google.android.gms.internal.measurement.zzhv.zza(r14, r2, r0)
        L_0x0159:
            int r0 = r8 + 8
        L_0x015b:
            r1 = r7
            r2 = r10
            goto L_0x0251
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
            com.google.android.gms.internal.measurement.zzff r0 = (com.google.android.gms.internal.measurement.zzff) r0
            boolean r3 = r0.zzrx()
            if (r3 != 0) goto L_0x018b
            int r3 = r0.size()
            if (r3 != 0) goto L_0x0182
            r3 = 10
            goto L_0x0184
        L_0x0182:
            int r3 = r3 << 1
        L_0x0184:
            com.google.android.gms.internal.measurement.zzff r0 = r0.zzap(r3)
            r9.putObject(r14, r1, r0)
        L_0x018b:
            r5 = r0
            com.google.android.gms.internal.measurement.zzgx r0 = r15.zzbx(r4)
            r1 = r17
            r2 = r29
            r3 = r8
            r19 = r4
            r4 = r31
            r6 = r32
            int r0 = com.google.android.gms.internal.measurement.zzdl.zza(r0, r1, r2, r3, r4, r5, r6)
            r1 = r7
            r2 = r19
            goto L_0x0251
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
            if (r0 != r15) goto L_0x0241
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
            if (r0 != r15) goto L_0x0241
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
            if (r0 != r15) goto L_0x0241
        L_0x0230:
            r2 = r0
        L_0x0231:
            com.google.android.gms.internal.measurement.zzhs r4 = zzu(r28)
            r0 = r17
            r1 = r29
            r3 = r31
            r5 = r32
            int r0 = com.google.android.gms.internal.measurement.zzdl.zza(r0, r1, r2, r3, r4, r5)
        L_0x0241:
            r15 = r27
            r14 = r28
            r12 = r29
            r13 = r31
            r11 = r32
            r9 = r18
            r2 = r19
            r1 = r24
        L_0x0251:
            r10 = -1
            goto L_0x0017
        L_0x0254:
            r4 = r13
            if (r0 != r4) goto L_0x0258
            return
        L_0x0258:
            com.google.android.gms.internal.measurement.zzfi r0 = com.google.android.gms.internal.measurement.zzfi.zzva()
            throw r0
        L_0x025d:
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
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzgm.zza(java.lang.Object, byte[], int, int, com.google.android.gms.internal.measurement.zzdk):void");
    }

    public final void zzj(T t) {
        int i;
        int i2 = this.zzakt;
        while (true) {
            i = this.zzaku;
            if (i2 >= i) {
                break;
            }
            long zzca = (long) (zzca(this.zzaks[i2]) & 1048575);
            Object zzp = zzhv.zzp(t, zzca);
            if (zzp != null) {
                zzhv.zza((Object) t, zzca, this.zzakz.zzp(zzp));
            }
            i2++;
        }
        int length = this.zzaks.length;
        while (i < length) {
            this.zzakw.zzb(t, (long) this.zzaks[i]);
            i++;
        }
        this.zzakx.zzj(t);
        if (this.zzako) {
            this.zzaky.zzj(t);
        }
    }

    private final <UT, UB> UB zza(Object obj, int i, UB ub, zzhp<UT, UB> zzhp) {
        int i2 = this.zzakj[i];
        Object zzp = zzhv.zzp(obj, (long) (zzca(i) & 1048575));
        if (zzp == null) {
            return ub;
        }
        zzfe zzbz = zzbz(i);
        if (zzbz == null) {
            return ub;
        }
        return zza(i, i2, this.zzakz.zzm(zzp), zzbz, ub, zzhp);
    }

    private final <K, V, UT, UB> UB zza(int i, int i2, Map<K, V> map, zzfe zzfe, UB ub, zzhp<UT, UB> zzhp) {
        zzfz zzr = this.zzakz.zzr(zzby(i));
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            if (!zzfe.zzg(((Integer) entry.getValue()).intValue())) {
                if (ub == null) {
                    ub = zzhp.zzwp();
                }
                zzdx zzas = zzdp.zzas(zzga.zza(zzr, entry.getKey(), entry.getValue()));
                try {
                    zzga.zza(zzas.zzsf(), zzr, entry.getKey(), entry.getValue());
                    zzhp.zza(ub, i2, zzas.zzse());
                    it.remove();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return ub;
    }

    public final boolean zzv(T t) {
        int i;
        int i2 = 0;
        int i3 = -1;
        int i4 = 0;
        while (true) {
            boolean z = true;
            if (i2 >= this.zzakt) {
                return !this.zzako || this.zzaky.zzh(t).isInitialized();
            }
            int i5 = this.zzaks[i2];
            int i6 = this.zzakj[i5];
            int zzca = zzca(i5);
            if (!this.zzakq) {
                int i7 = this.zzakj[i5 + 2];
                int i8 = i7 & 1048575;
                i = 1 << (i7 >>> 20);
                if (i8 != i3) {
                    i4 = zzaki.getInt(t, (long) i8);
                    i3 = i8;
                }
            } else {
                i = 0;
            }
            if (((268435456 & zzca) != 0) && !zza(t, i5, i4, i)) {
                return false;
            }
            int i9 = (267386880 & zzca) >>> 20;
            if (i9 != 9 && i9 != 17) {
                if (i9 != 27) {
                    if (i9 == 60 || i9 == 68) {
                        if (zza(t, i6, i5) && !zza((Object) t, zzca, zzbx(i5))) {
                            return false;
                        }
                    } else if (i9 != 49) {
                        if (i9 != 50) {
                            continue;
                        } else {
                            Map zzn = this.zzakz.zzn(zzhv.zzp(t, (long) (zzca & 1048575)));
                            if (!zzn.isEmpty()) {
                                if (this.zzakz.zzr(zzby(i5)).zzakd.zzwz() == zzij.MESSAGE) {
                                    zzgx zzgx = null;
                                    Iterator it = zzn.values().iterator();
                                    while (true) {
                                        if (!it.hasNext()) {
                                            break;
                                        }
                                        Object next = it.next();
                                        if (zzgx == null) {
                                            zzgx = zzgt.zzvy().zzf(next.getClass());
                                        }
                                        if (!zzgx.zzv(next)) {
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
                List list = (List) zzhv.zzp(t, (long) (zzca & 1048575));
                if (!list.isEmpty()) {
                    zzgx zzbx = zzbx(i5);
                    int i10 = 0;
                    while (true) {
                        if (i10 >= list.size()) {
                            break;
                        } else if (!zzbx.zzv(list.get(i10))) {
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
            } else if (zza(t, i5, i4, i) && !zza((Object) t, zzca, zzbx(i5))) {
                return false;
            }
            i2++;
        }
    }

    private static boolean zza(Object obj, int i, zzgx zzgx) {
        return zzgx.zzv(zzhv.zzp(obj, (long) (i & 1048575)));
    }

    private static void zza(int i, Object obj, zzim zzim) throws IOException {
        if (obj instanceof String) {
            zzim.zzb(i, (String) obj);
        } else {
            zzim.zza(i, (zzdp) obj);
        }
    }

    private final void zza(Object obj, int i, zzgy zzgy) throws IOException {
        if (zzcc(i)) {
            zzhv.zza(obj, (long) (i & 1048575), (Object) zzgy.zzsn());
        } else if (this.zzakp) {
            zzhv.zza(obj, (long) (i & 1048575), (Object) zzgy.readString());
        } else {
            zzhv.zza(obj, (long) (i & 1048575), (Object) zzgy.zzso());
        }
    }

    private final int zzca(int i) {
        return this.zzakj[i + 1];
    }

    private final int zzcb(int i) {
        return this.zzakj[i + 2];
    }

    private static <T> double zzf(T t, long j) {
        return ((Double) zzhv.zzp(t, j)).doubleValue();
    }

    private static <T> float zzg(T t, long j) {
        return ((Float) zzhv.zzp(t, j)).floatValue();
    }

    private static <T> int zzh(T t, long j) {
        return ((Integer) zzhv.zzp(t, j)).intValue();
    }

    private static <T> long zzi(T t, long j) {
        return ((Long) zzhv.zzp(t, j)).longValue();
    }

    private static <T> boolean zzj(T t, long j) {
        return ((Boolean) zzhv.zzp(t, j)).booleanValue();
    }

    private final boolean zzc(T t, T t2, int i) {
        return zza(t, i) == zza(t2, i);
    }

    private final boolean zza(T t, int i, int i2, int i3) {
        if (this.zzakq) {
            return zza(t, i);
        }
        return (i2 & i3) != 0;
    }

    private final boolean zza(T t, int i) {
        if (this.zzakq) {
            int zzca = zzca(i);
            long j = (long) (zzca & 1048575);
            switch ((zzca & 267386880) >>> 20) {
                case 0:
                    return zzhv.zzo(t, j) != 0.0d;
                case 1:
                    return zzhv.zzn(t, j) != 0.0f;
                case 2:
                    return zzhv.zzl(t, j) != 0;
                case 3:
                    return zzhv.zzl(t, j) != 0;
                case 4:
                    return zzhv.zzk(t, j) != 0;
                case 5:
                    return zzhv.zzl(t, j) != 0;
                case 6:
                    return zzhv.zzk(t, j) != 0;
                case 7:
                    return zzhv.zzm(t, j);
                case 8:
                    Object zzp = zzhv.zzp(t, j);
                    if (zzp instanceof String) {
                        return !((String) zzp).isEmpty();
                    }
                    if (zzp instanceof zzdp) {
                        return !zzdp.zzadh.equals(zzp);
                    }
                    throw new IllegalArgumentException();
                case 9:
                    return zzhv.zzp(t, j) != null;
                case 10:
                    return !zzdp.zzadh.equals(zzhv.zzp(t, j));
                case 11:
                    return zzhv.zzk(t, j) != 0;
                case 12:
                    return zzhv.zzk(t, j) != 0;
                case 13:
                    return zzhv.zzk(t, j) != 0;
                case 14:
                    return zzhv.zzl(t, j) != 0;
                case 15:
                    return zzhv.zzk(t, j) != 0;
                case 16:
                    return zzhv.zzl(t, j) != 0;
                case 17:
                    return zzhv.zzp(t, j) != null;
                default:
                    throw new IllegalArgumentException();
            }
        } else {
            int zzcb = zzcb(i);
            return (zzhv.zzk(t, (long) (zzcb & 1048575)) & (1 << (zzcb >>> 20))) != 0;
        }
    }

    private final void zzb(T t, int i) {
        if (!this.zzakq) {
            int zzcb = zzcb(i);
            long j = (long) (zzcb & 1048575);
            zzhv.zzb((Object) t, j, zzhv.zzk(t, j) | (1 << (zzcb >>> 20)));
        }
    }

    private final boolean zza(T t, int i, int i2) {
        return zzhv.zzk(t, (long) (zzcb(i2) & 1048575)) == i;
    }

    private final void zzb(T t, int i, int i2) {
        zzhv.zzb((Object) t, (long) (zzcb(i2) & 1048575), i);
    }

    private final int zzcd(int i) {
        if (i < this.zzakl || i > this.zzakm) {
            return -1;
        }
        return zzq(i, 0);
    }

    private final int zzp(int i, int i2) {
        if (i < this.zzakl || i > this.zzakm) {
            return -1;
        }
        return zzq(i, i2);
    }

    private final int zzq(int i, int i2) {
        int length = (this.zzakj.length / 3) - 1;
        while (i2 <= length) {
            int i3 = (length + i2) >>> 1;
            int i4 = i3 * 3;
            int i5 = this.zzakj[i4];
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
