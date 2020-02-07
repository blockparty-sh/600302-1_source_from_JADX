package org.spongycastle.crypto.params;

import java.math.BigInteger;

public class ElGamalPublicKeyParameters extends ElGamalKeyParameters {

    /* renamed from: y */
    private BigInteger f1300y;

    public ElGamalPublicKeyParameters(BigInteger bigInteger, ElGamalParameters elGamalParameters) {
        super(false, elGamalParameters);
        this.f1300y = bigInteger;
    }

    public BigInteger getY() {
        return this.f1300y;
    }

    public int hashCode() {
        return this.f1300y.hashCode() ^ super.hashCode();
    }

    public boolean equals(Object obj) {
        boolean z = false;
        if (!(obj instanceof ElGamalPublicKeyParameters)) {
            return false;
        }
        if (((ElGamalPublicKeyParameters) obj).getY().equals(this.f1300y) && super.equals(obj)) {
            z = true;
        }
        return z;
    }
}
