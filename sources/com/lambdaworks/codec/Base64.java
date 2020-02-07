package com.lambdaworks.codec;

import com.google.common.base.Ascii;
import java.util.Arrays;

public class Base64 {
    private static final int[] decode = new int[128];
    private static final char[] encode = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();
    private static final char pad = '=';

    static {
        Arrays.fill(decode, -1);
        int i = 0;
        while (true) {
            char[] cArr = encode;
            if (i < cArr.length) {
                decode[cArr[i]] = i;
                i++;
            } else {
                decode[61] = 0;
                return;
            }
        }
    }

    public static byte[] decode(char[] cArr) {
        return decode(cArr, decode, pad);
    }

    public static char[] encode(byte[] bArr) {
        return encode(bArr, encode, pad);
    }

    public static char[] encode(byte[] bArr, boolean z) {
        return encode(bArr, encode, z ? pad : 0);
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x0091 A[LOOP:1: B:26:0x008f->B:27:0x0091, LOOP_END] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] decode(char[] r12, int[] r13, char r14) {
        /*
            int r0 = r12.length
            r1 = 0
            if (r0 != 0) goto L_0x0007
            byte[] r12 = new byte[r1]
            return r12
        L_0x0007:
            int r2 = r0 + -1
            char r2 = r12[r2]
            r3 = 2
            r4 = 1
            if (r2 != r14) goto L_0x0019
            int r2 = r0 + -2
            char r2 = r12[r2]
            if (r2 != r14) goto L_0x0017
            r14 = 2
            goto L_0x001a
        L_0x0017:
            r14 = 1
            goto L_0x001a
        L_0x0019:
            r14 = 0
        L_0x001a:
            int r2 = r0 * 6
            r5 = 3
            int r2 = r2 >> r5
            int r2 = r2 - r14
            int r14 = r2 / 3
            int r14 = r14 * 3
            byte[] r6 = new byte[r2]
            r7 = 0
            r8 = 0
        L_0x0027:
            if (r7 >= r14) goto L_0x0060
            int r9 = r8 + 1
            char r8 = r12[r8]
            r8 = r13[r8]
            int r8 = r8 << 18
            int r10 = r9 + 1
            char r9 = r12[r9]
            r9 = r13[r9]
            int r9 = r9 << 12
            r8 = r8 | r9
            int r9 = r10 + 1
            char r10 = r12[r10]
            r10 = r13[r10]
            int r10 = r10 << 6
            r8 = r8 | r10
            int r10 = r9 + 1
            char r9 = r12[r9]
            r9 = r13[r9]
            r8 = r8 | r9
            int r9 = r7 + 1
            int r11 = r8 >> 16
            byte r11 = (byte) r11
            r6[r7] = r11
            int r7 = r9 + 1
            int r11 = r8 >> 8
            byte r11 = (byte) r11
            r6[r9] = r11
            int r9 = r7 + 1
            byte r8 = (byte) r8
            r6[r7] = r8
            r7 = r9
            r8 = r10
            goto L_0x0027
        L_0x0060:
            if (r7 >= r2) goto L_0x009c
            int r0 = r0 - r8
            if (r0 == r4) goto L_0x0086
            if (r0 == r3) goto L_0x007d
            if (r0 == r5) goto L_0x0074
            r14 = 4
            if (r0 == r14) goto L_0x006d
            goto L_0x008d
        L_0x006d:
            int r14 = r8 + 3
            char r14 = r12[r14]
            r14 = r13[r14]
            r1 = r1 | r14
        L_0x0074:
            int r14 = r8 + 2
            char r14 = r12[r14]
            r14 = r13[r14]
            int r14 = r14 << 6
            r1 = r1 | r14
        L_0x007d:
            int r14 = r8 + 1
            char r14 = r12[r14]
            r14 = r13[r14]
            int r14 = r14 << 12
            r1 = r1 | r14
        L_0x0086:
            char r12 = r12[r8]
            r12 = r13[r12]
            int r12 = r12 << 18
            r1 = r1 | r12
        L_0x008d:
            r12 = 16
        L_0x008f:
            if (r7 >= r2) goto L_0x009c
            int r13 = r7 + 1
            int r14 = r1 >> r12
            byte r14 = (byte) r14
            r6[r7] = r14
            int r12 = r12 + -8
            r7 = r13
            goto L_0x008f
        L_0x009c:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lambdaworks.codec.Base64.decode(char[], int[], char):byte[]");
    }

    public static char[] encode(byte[] bArr, char[] cArr, char c) {
        int i;
        int length = bArr.length;
        int i2 = 0;
        if (length == 0) {
            return new char[0];
        }
        int i3 = (length / 3) * 3;
        int i4 = (((length - 1) / 3) + 1) << 2;
        int i5 = length - i3;
        if (c == 0 && i5 > 0) {
            i4 -= 3 - i5;
        }
        char[] cArr2 = new char[i4];
        int i6 = 0;
        while (i2 < i3) {
            int i7 = i2 + 1;
            int i8 = i7 + 1;
            byte b = ((bArr[i2] & 255) << 16) | ((bArr[i7] & 255) << 8);
            int i9 = i8 + 1;
            byte b2 = b | (bArr[i8] & 255);
            int i10 = i6 + 1;
            cArr2[i6] = cArr[(b2 >>> Ascii.DC2) & 63];
            int i11 = i10 + 1;
            cArr2[i10] = cArr[(b2 >>> Ascii.f521FF) & 63];
            int i12 = i11 + 1;
            cArr2[i11] = cArr[(b2 >>> 6) & 63];
            i6 = i12 + 1;
            cArr2[i12] = cArr[b2 & 63];
            i2 = i9;
        }
        if (i5 > 0) {
            int i13 = (bArr[i2] & 255) << 10;
            if (i5 == 2) {
                i13 |= (bArr[i2 + 1] & 255) << 2;
            }
            int i14 = i6 + 1;
            cArr2[i6] = cArr[(i13 >>> 12) & 63];
            int i15 = i14 + 1;
            cArr2[i14] = cArr[(i13 >>> 6) & 63];
            if (i5 == 2) {
                i = i15 + 1;
                cArr2[i15] = cArr[i13 & 63];
            } else {
                i = i15;
            }
            if (c != 0) {
                if (i5 == 1) {
                    int i16 = i + 1;
                    cArr2[i] = c;
                    i = i16;
                }
                cArr2[i] = c;
            }
        }
        return cArr2;
    }
}
