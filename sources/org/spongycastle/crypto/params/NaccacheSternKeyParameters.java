package org.spongycastle.crypto.params;

import java.math.BigInteger;

public class NaccacheSternKeyParameters extends AsymmetricKeyParameter {

    /* renamed from: g */
    private BigInteger f1317g;
    int lowerSigmaBound;

    /* renamed from: n */
    private BigInteger f1318n;

    public NaccacheSternKeyParameters(boolean z, BigInteger bigInteger, BigInteger bigInteger2, int i) {
        super(z);
        this.f1317g = bigInteger;
        this.f1318n = bigInteger2;
        this.lowerSigmaBound = i;
    }

    public BigInteger getG() {
        return this.f1317g;
    }

    public int getLowerSigmaBound() {
        return this.lowerSigmaBound;
    }

    public BigInteger getModulus() {
        return this.f1318n;
    }
}
