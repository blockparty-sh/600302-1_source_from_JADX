package org.spongycastle.crypto.params;

import java.security.SecureRandom;

public class DSAParameterGenerationParameters {
    public static final int DIGITAL_SIGNATURE_USAGE = 1;
    public static final int KEY_ESTABLISHMENT_USAGE = 2;
    private final int certainty;

    /* renamed from: l */
    private final int f1284l;

    /* renamed from: n */
    private final int f1285n;
    private final SecureRandom random;
    private final int usageIndex;

    public DSAParameterGenerationParameters(int i, int i2, int i3, SecureRandom secureRandom) {
        this(i, i2, i3, secureRandom, -1);
    }

    public DSAParameterGenerationParameters(int i, int i2, int i3, SecureRandom secureRandom, int i4) {
        this.f1284l = i;
        this.f1285n = i2;
        this.certainty = i3;
        this.usageIndex = i4;
        this.random = secureRandom;
    }

    public int getL() {
        return this.f1284l;
    }

    public int getN() {
        return this.f1285n;
    }

    public int getCertainty() {
        return this.certainty;
    }

    public SecureRandom getRandom() {
        return this.random;
    }

    public int getUsageIndex() {
        return this.usageIndex;
    }
}
