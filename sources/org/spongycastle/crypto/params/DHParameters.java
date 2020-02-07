package org.spongycastle.crypto.params;

import java.math.BigInteger;
import org.spongycastle.crypto.CipherParameters;

public class DHParameters implements CipherParameters {
    private static final int DEFAULT_MINIMUM_LENGTH = 160;

    /* renamed from: g */
    private BigInteger f1276g;

    /* renamed from: j */
    private BigInteger f1277j;

    /* renamed from: l */
    private int f1278l;

    /* renamed from: m */
    private int f1279m;

    /* renamed from: p */
    private BigInteger f1280p;

    /* renamed from: q */
    private BigInteger f1281q;
    private DHValidationParameters validation;

    private static int getDefaultMParam(int i) {
        if (i == 0) {
            return 160;
        }
        if (i >= 160) {
            i = 160;
        }
        return i;
    }

    public DHParameters(BigInteger bigInteger, BigInteger bigInteger2) {
        this(bigInteger, bigInteger2, null, 0);
    }

    public DHParameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        this(bigInteger, bigInteger2, bigInteger3, 0);
    }

    public DHParameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, int i) {
        this(bigInteger, bigInteger2, bigInteger3, getDefaultMParam(i), i, null, null);
    }

    public DHParameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, int i, int i2) {
        this(bigInteger, bigInteger2, bigInteger3, i, i2, null, null);
    }

    public DHParameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4, DHValidationParameters dHValidationParameters) {
        this(bigInteger, bigInteger2, bigInteger3, 160, 0, bigInteger4, dHValidationParameters);
    }

    public DHParameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, int i, int i2, BigInteger bigInteger4, DHValidationParameters dHValidationParameters) {
        if (i2 != 0) {
            if (BigInteger.valueOf(2 ^ ((long) (i2 - 1))).compareTo(bigInteger) == 1) {
                throw new IllegalArgumentException("when l value specified, it must satisfy 2^(l-1) <= p");
            } else if (i2 < i) {
                throw new IllegalArgumentException("when l value specified, it may not be less than m value");
            }
        }
        this.f1276g = bigInteger2;
        this.f1280p = bigInteger;
        this.f1281q = bigInteger3;
        this.f1279m = i;
        this.f1278l = i2;
        this.f1277j = bigInteger4;
        this.validation = dHValidationParameters;
    }

    public BigInteger getP() {
        return this.f1280p;
    }

    public BigInteger getG() {
        return this.f1276g;
    }

    public BigInteger getQ() {
        return this.f1281q;
    }

    public BigInteger getJ() {
        return this.f1277j;
    }

    public int getM() {
        return this.f1279m;
    }

    public int getL() {
        return this.f1278l;
    }

    public DHValidationParameters getValidationParameters() {
        return this.validation;
    }

    public boolean equals(Object obj) {
        boolean z = false;
        if (!(obj instanceof DHParameters)) {
            return false;
        }
        DHParameters dHParameters = (DHParameters) obj;
        if (getQ() != null) {
            if (!getQ().equals(dHParameters.getQ())) {
                return false;
            }
        } else if (dHParameters.getQ() != null) {
            return false;
        }
        if (dHParameters.getP().equals(this.f1280p) && dHParameters.getG().equals(this.f1276g)) {
            z = true;
        }
        return z;
    }

    public int hashCode() {
        return (getP().hashCode() ^ getG().hashCode()) ^ (getQ() != null ? getQ().hashCode() : 0);
    }
}
