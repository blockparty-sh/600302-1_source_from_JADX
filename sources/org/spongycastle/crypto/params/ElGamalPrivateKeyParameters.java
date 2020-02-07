package org.spongycastle.crypto.params;

import java.math.BigInteger;

public class ElGamalPrivateKeyParameters extends ElGamalKeyParameters {

    /* renamed from: x */
    private BigInteger f1299x;

    public ElGamalPrivateKeyParameters(BigInteger bigInteger, ElGamalParameters elGamalParameters) {
        super(true, elGamalParameters);
        this.f1299x = bigInteger;
    }

    public BigInteger getX() {
        return this.f1299x;
    }

    public boolean equals(Object obj) {
        if ((obj instanceof ElGamalPrivateKeyParameters) && ((ElGamalPrivateKeyParameters) obj).getX().equals(this.f1299x)) {
            return super.equals(obj);
        }
        return false;
    }

    public int hashCode() {
        return getX().hashCode();
    }
}
