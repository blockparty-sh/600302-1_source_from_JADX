package org.spongycastle.crypto.modes.gcm;

import java.lang.reflect.Array;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.Pack;

public class Tables64kGCMMultiplier implements GCMMultiplier {

    /* renamed from: H */
    private byte[] f1259H;

    /* renamed from: M */
    private int[][][] f1260M;

    public void init(byte[] bArr) {
        if (this.f1260M == null) {
            this.f1260M = (int[][][]) Array.newInstance(int.class, new int[]{16, 256, 4});
        } else if (Arrays.areEqual(this.f1259H, bArr)) {
            return;
        }
        this.f1259H = Arrays.clone(bArr);
        int i = 0;
        GCMUtil.asInts(bArr, this.f1260M[0][128]);
        for (int i2 = 64; i2 >= 1; i2 >>= 1) {
            int[][][] iArr = this.f1260M;
            GCMUtil.multiplyP(iArr[0][i2 + i2], iArr[0][i2]);
        }
        while (true) {
            for (int i3 = 2; i3 < 256; i3 += i3) {
                for (int i4 = 1; i4 < i3; i4++) {
                    int[][][] iArr2 = this.f1260M;
                    GCMUtil.xor(iArr2[i][i3], iArr2[i][i4], iArr2[i][i3 + i4]);
                }
            }
            i++;
            if (i != 16) {
                for (int i5 = 128; i5 > 0; i5 >>= 1) {
                    int[][][] iArr3 = this.f1260M;
                    GCMUtil.multiplyP8(iArr3[i - 1][i5], iArr3[i][i5]);
                }
            } else {
                return;
            }
        }
    }

    public void multiplyH(byte[] bArr) {
        int[] iArr = new int[4];
        for (int i = 15; i >= 0; i--) {
            int[] iArr2 = this.f1260M[i][bArr[i] & 255];
            iArr[0] = iArr[0] ^ iArr2[0];
            iArr[1] = iArr[1] ^ iArr2[1];
            iArr[2] = iArr[2] ^ iArr2[2];
            iArr[3] = iArr2[3] ^ iArr[3];
        }
        Pack.intToBigEndian(iArr, bArr, 0);
    }
}
