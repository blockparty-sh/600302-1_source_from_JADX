package org.spongycastle.crypto.modes.gcm;

import org.spongycastle.util.Arrays;

public class BasicGCMExponentiator implements GCMExponentiator {

    /* renamed from: x */
    private int[] f1256x;

    public void init(byte[] bArr) {
        this.f1256x = GCMUtil.asInts(bArr);
    }

    public void exponentiateX(long j, byte[] bArr) {
        int[] oneAsInts = GCMUtil.oneAsInts();
        if (j > 0) {
            int[] clone = Arrays.clone(this.f1256x);
            do {
                if ((1 & j) != 0) {
                    GCMUtil.multiply(oneAsInts, clone);
                }
                GCMUtil.multiply(clone, clone);
                j >>>= 1;
            } while (j > 0);
        }
        GCMUtil.asBytes(oneAsInts, bArr);
    }
}
