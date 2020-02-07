package org.spongycastle.pqc.crypto.gmss.util;

import org.spongycastle.crypto.Digest;

public class WinternitzOTSVerify {
    private Digest messDigestOTS;

    /* renamed from: w */
    private int f1449w;

    public int getLog(int i) {
        int i2 = 1;
        int i3 = 2;
        while (i3 < i) {
            i3 <<= 1;
            i2++;
        }
        return i2;
    }

    public WinternitzOTSVerify(Digest digest, int i) {
        this.f1449w = i;
        this.messDigestOTS = digest;
    }

    public int getSignatureLength() {
        int digestSize = this.messDigestOTS.getDigestSize();
        int i = digestSize << 3;
        int i2 = this.f1449w;
        int i3 = (i + (i2 - 1)) / i2;
        int log = getLog((i3 << i2) + 1);
        int i4 = this.f1449w;
        return digestSize * (i3 + (((log + i4) - 1) / i4));
    }

    public byte[] Verify(byte[] bArr, byte[] bArr2) {
        int i;
        byte[] bArr3 = bArr;
        byte[] bArr4 = bArr2;
        int digestSize = this.messDigestOTS.getDigestSize();
        byte[] bArr5 = new byte[digestSize];
        this.messDigestOTS.update(bArr3, 0, bArr3.length);
        byte[] bArr6 = new byte[this.messDigestOTS.getDigestSize()];
        this.messDigestOTS.doFinal(bArr6, 0);
        int i2 = digestSize << 3;
        int i3 = this.f1449w;
        int i4 = ((i3 - 1) + i2) / i3;
        int log = getLog((i4 << i3) + 1);
        int i5 = this.f1449w;
        int i6 = ((((log + i5) - 1) / i5) + i4) * digestSize;
        if (i6 != bArr4.length) {
            return null;
        }
        byte[] bArr7 = new byte[i6];
        int i7 = 8;
        if (8 % i5 == 0) {
            int i8 = 8 / i5;
            int i9 = (1 << i5) - 1;
            byte[] bArr8 = new byte[digestSize];
            int i10 = 0;
            int i11 = 0;
            int i12 = 0;
            while (i10 < bArr6.length) {
                byte[] bArr9 = bArr8;
                int i13 = i12;
                int i14 = i11;
                int i15 = 0;
                while (i15 < i8) {
                    int i16 = bArr6[i10] & i9;
                    i14 += i16;
                    int i17 = i8;
                    int i18 = i13 * digestSize;
                    System.arraycopy(bArr4, i18, bArr9, 0, digestSize);
                    while (i16 < i9) {
                        int i19 = i14;
                        this.messDigestOTS.update(bArr9, 0, bArr9.length);
                        bArr9 = new byte[this.messDigestOTS.getDigestSize()];
                        this.messDigestOTS.doFinal(bArr9, 0);
                        i16++;
                        byte[] bArr10 = bArr2;
                        i14 = i19;
                    }
                    int i20 = i14;
                    System.arraycopy(bArr9, 0, bArr7, i18, digestSize);
                    bArr6[i10] = (byte) (bArr6[i10] >>> this.f1449w);
                    i13++;
                    i15++;
                    i8 = i17;
                    bArr4 = bArr2;
                }
                int i21 = i8;
                i10++;
                bArr4 = bArr2;
                i11 = i14;
                i12 = i13;
                bArr8 = bArr9;
            }
            int i22 = (i4 << this.f1449w) - i11;
            int i23 = 0;
            while (i23 < log) {
                int i24 = i12 * digestSize;
                System.arraycopy(bArr2, i24, bArr8, 0, digestSize);
                for (int i25 = i22 & i9; i25 < i9; i25++) {
                    this.messDigestOTS.update(bArr8, 0, bArr8.length);
                    bArr8 = new byte[this.messDigestOTS.getDigestSize()];
                    this.messDigestOTS.doFinal(bArr8, 0);
                }
                System.arraycopy(bArr8, 0, bArr7, i24, digestSize);
                int i26 = this.f1449w;
                i22 >>>= i26;
                i12++;
                i23 += i26;
            }
        } else {
            byte[] bArr11 = bArr4;
            if (i5 < 8) {
                int i27 = digestSize / i5;
                int i28 = (1 << i5) - 1;
                byte[] bArr12 = new byte[digestSize];
                int i29 = 0;
                int i30 = 0;
                int i31 = 0;
                int i32 = 0;
                while (i29 < i27) {
                    int i33 = i30;
                    long j = 0;
                    for (int i34 = 0; i34 < this.f1449w; i34++) {
                        j ^= (long) ((bArr6[i33] & 255) << (i34 << 3));
                        i33++;
                    }
                    byte[] bArr13 = bArr12;
                    int i35 = 0;
                    while (i35 < i7) {
                        int i36 = i29;
                        int i37 = (int) (j & ((long) i28));
                        i31 += i37;
                        int i38 = i32 * digestSize;
                        System.arraycopy(bArr11, i38, bArr13, 0, digestSize);
                        while (i37 < i28) {
                            this.messDigestOTS.update(bArr13, 0, bArr13.length);
                            bArr13 = new byte[this.messDigestOTS.getDigestSize()];
                            this.messDigestOTS.doFinal(bArr13, 0);
                            i37++;
                        }
                        System.arraycopy(bArr13, 0, bArr7, i38, digestSize);
                        j >>>= this.f1449w;
                        i32++;
                        i35++;
                        i29 = i36;
                        i7 = 8;
                    }
                    i29++;
                    bArr12 = bArr13;
                    i30 = i33;
                    i7 = 8;
                }
                int i39 = digestSize % this.f1449w;
                long j2 = 0;
                for (int i40 = 0; i40 < i39; i40++) {
                    j2 ^= (long) ((bArr6[i30] & 255) << (i40 << 3));
                    i30++;
                }
                int i41 = i39 << 3;
                byte[] bArr14 = bArr12;
                int i42 = 0;
                while (i42 < i41) {
                    int i43 = (int) (j2 & ((long) i28));
                    i31 += i43;
                    int i44 = i32 * digestSize;
                    System.arraycopy(bArr11, i44, bArr14, 0, digestSize);
                    while (i43 < i28) {
                        this.messDigestOTS.update(bArr14, 0, bArr14.length);
                        bArr14 = new byte[this.messDigestOTS.getDigestSize()];
                        this.messDigestOTS.doFinal(bArr14, 0);
                        i43++;
                    }
                    System.arraycopy(bArr14, 0, bArr7, i44, digestSize);
                    int i45 = this.f1449w;
                    j2 >>>= i45;
                    i32++;
                    i42 += i45;
                }
                int i46 = (i4 << this.f1449w) - i31;
                int i47 = 0;
                while (i47 < log) {
                    int i48 = i32 * digestSize;
                    System.arraycopy(bArr11, i48, bArr14, 0, digestSize);
                    for (int i49 = i46 & i28; i49 < i28; i49++) {
                        this.messDigestOTS.update(bArr14, 0, bArr14.length);
                        bArr14 = new byte[this.messDigestOTS.getDigestSize()];
                        this.messDigestOTS.doFinal(bArr14, 0);
                    }
                    System.arraycopy(bArr14, 0, bArr7, i48, digestSize);
                    int i50 = this.f1449w;
                    i46 >>>= i50;
                    i32++;
                    i47 += i50;
                }
            } else if (i5 < 57) {
                int i51 = i2 - i5;
                int i52 = (1 << i5) - 1;
                byte[] bArr15 = new byte[digestSize];
                int i53 = 0;
                int i54 = 0;
                int i55 = 0;
                while (i53 <= i51) {
                    int i56 = i53 >>> 3;
                    int i57 = i53 % 8;
                    int i58 = i53 + this.f1449w;
                    int i59 = 0;
                    long j3 = 0;
                    while (i56 < ((i58 + 7) >>> 3)) {
                        j3 ^= (long) ((bArr6[i56] & 255) << (i59 << 3));
                        i59++;
                        i56++;
                        log = log;
                        i51 = i51;
                    }
                    int i60 = i51;
                    int i61 = log;
                    int i62 = i4;
                    long j4 = (long) i52;
                    long j5 = (j3 >>> i57) & j4;
                    int i63 = i52;
                    i54 = (int) (((long) i54) + j5);
                    int i64 = i55 * digestSize;
                    int i65 = i58;
                    System.arraycopy(bArr11, i64, bArr15, 0, digestSize);
                    while (j5 < j4) {
                        long j6 = j4;
                        this.messDigestOTS.update(bArr15, 0, bArr15.length);
                        bArr15 = new byte[this.messDigestOTS.getDigestSize()];
                        this.messDigestOTS.doFinal(bArr15, 0);
                        j5++;
                        j4 = j6;
                    }
                    System.arraycopy(bArr15, 0, bArr7, i64, digestSize);
                    i55++;
                    i4 = i62;
                    i52 = i63;
                    i53 = i65;
                    log = i61;
                    i51 = i60;
                }
                int i66 = log;
                int i67 = i4;
                int i68 = i52;
                int i69 = i53 >>> 3;
                if (i69 < digestSize) {
                    int i70 = i53 % 8;
                    int i71 = 0;
                    long j7 = 0;
                    while (i69 < digestSize) {
                        j7 ^= (long) ((bArr6[i69] & 255) << (i71 << 3));
                        i71++;
                        i69++;
                    }
                    i = i68;
                    long j8 = (long) i;
                    long j9 = (j7 >>> i70) & j8;
                    i54 = (int) (((long) i54) + j9);
                    int i72 = i55 * digestSize;
                    System.arraycopy(bArr11, i72, bArr15, 0, digestSize);
                    while (j9 < j8) {
                        long j10 = j8;
                        this.messDigestOTS.update(bArr15, 0, bArr15.length);
                        bArr15 = new byte[this.messDigestOTS.getDigestSize()];
                        this.messDigestOTS.doFinal(bArr15, 0);
                        j9++;
                        j8 = j10;
                    }
                    System.arraycopy(bArr15, 0, bArr7, i72, digestSize);
                    i55++;
                } else {
                    i = i68;
                }
                int i73 = (i67 << this.f1449w) - i54;
                int i74 = i66;
                int i75 = 0;
                while (i75 < i74) {
                    int i76 = i55 * digestSize;
                    System.arraycopy(bArr11, i76, bArr15, 0, digestSize);
                    byte[] bArr16 = bArr7;
                    for (long j11 = (long) (i73 & i); j11 < ((long) i); j11++) {
                        this.messDigestOTS.update(bArr15, 0, bArr15.length);
                        bArr15 = new byte[this.messDigestOTS.getDigestSize()];
                        this.messDigestOTS.doFinal(bArr15, 0);
                    }
                    byte[] bArr17 = bArr16;
                    System.arraycopy(bArr15, 0, bArr17, i76, digestSize);
                    int i77 = this.f1449w;
                    i73 >>>= i77;
                    i55++;
                    i75 += i77;
                    bArr7 = bArr17;
                }
            }
        }
        byte[] bArr18 = bArr7;
        byte[] bArr19 = new byte[digestSize];
        this.messDigestOTS.update(bArr18, 0, bArr18.length);
        byte[] bArr20 = new byte[this.messDigestOTS.getDigestSize()];
        this.messDigestOTS.doFinal(bArr20, 0);
        return bArr20;
    }
}
