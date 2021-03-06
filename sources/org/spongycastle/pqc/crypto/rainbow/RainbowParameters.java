package org.spongycastle.pqc.crypto.rainbow;

import org.spongycastle.crypto.CipherParameters;

public class RainbowParameters implements CipherParameters {
    private final int[] DEFAULT_VI;

    /* renamed from: vi */
    private int[] f1537vi;

    public RainbowParameters() {
        this.DEFAULT_VI = new int[]{6, 12, 17, 22, 33};
        this.f1537vi = this.DEFAULT_VI;
    }

    public RainbowParameters(int[] iArr) {
        this.DEFAULT_VI = new int[]{6, 12, 17, 22, 33};
        this.f1537vi = iArr;
        try {
            checkParams();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void checkParams() throws Exception {
        int[] iArr;
        int i;
        int[] iArr2 = this.f1537vi;
        if (iArr2 == null) {
            throw new Exception("no layers defined.");
        } else if (iArr2.length > 1) {
            int i2 = 0;
            do {
                iArr = this.f1537vi;
                if (i2 < iArr.length - 1) {
                    i = iArr[i2];
                    i2++;
                } else {
                    return;
                }
            } while (i < iArr[i2]);
            throw new Exception("v[i] has to be smaller than v[i+1]");
        } else {
            throw new Exception("Rainbow needs at least 1 layer, such that v1 < v2.");
        }
    }

    public int getNumOfLayers() {
        return this.f1537vi.length - 1;
    }

    public int getDocLength() {
        int[] iArr = this.f1537vi;
        return iArr[iArr.length - 1] - iArr[0];
    }

    public int[] getVi() {
        return this.f1537vi;
    }
}
