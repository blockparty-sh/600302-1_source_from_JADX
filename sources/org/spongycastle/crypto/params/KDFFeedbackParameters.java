package org.spongycastle.crypto.params;

import org.spongycastle.crypto.DerivationParameters;
import org.spongycastle.util.Arrays;

public final class KDFFeedbackParameters implements DerivationParameters {
    private static final int UNUSED_R = -1;
    private final byte[] fixedInputData;

    /* renamed from: iv */
    private final byte[] f1313iv;

    /* renamed from: ki */
    private final byte[] f1314ki;

    /* renamed from: r */
    private final int f1315r;
    private final boolean useCounter;

    private KDFFeedbackParameters(byte[] bArr, byte[] bArr2, byte[] bArr3, int i, boolean z) {
        if (bArr != null) {
            this.f1314ki = Arrays.clone(bArr);
            if (bArr3 == null) {
                this.fixedInputData = new byte[0];
            } else {
                this.fixedInputData = Arrays.clone(bArr3);
            }
            this.f1315r = i;
            if (bArr2 == null) {
                this.f1313iv = new byte[0];
            } else {
                this.f1313iv = Arrays.clone(bArr2);
            }
            this.useCounter = z;
            return;
        }
        throw new IllegalArgumentException("A KDF requires Ki (a seed) as input");
    }

    public static KDFFeedbackParameters createWithCounter(byte[] bArr, byte[] bArr2, byte[] bArr3, int i) {
        if (i == 8 || i == 16 || i == 24 || i == 32) {
            KDFFeedbackParameters kDFFeedbackParameters = new KDFFeedbackParameters(bArr, bArr2, bArr3, i, true);
            return kDFFeedbackParameters;
        }
        throw new IllegalArgumentException("Length of counter should be 8, 16, 24 or 32");
    }

    public static KDFFeedbackParameters createWithoutCounter(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        KDFFeedbackParameters kDFFeedbackParameters = new KDFFeedbackParameters(bArr, bArr2, bArr3, -1, false);
        return kDFFeedbackParameters;
    }

    public byte[] getKI() {
        return this.f1314ki;
    }

    public byte[] getIV() {
        return this.f1313iv;
    }

    public boolean useCounter() {
        return this.useCounter;
    }

    public int getR() {
        return this.f1315r;
    }

    public byte[] getFixedInputData() {
        return Arrays.clone(this.fixedInputData);
    }
}
