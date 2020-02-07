package org.spongycastle.crypto.params;

import java.math.BigInteger;

public class DHPublicKeyParameters extends DHKeyParameters {

    /* renamed from: y */
    private BigInteger f1283y;

    public DHPublicKeyParameters(BigInteger bigInteger, DHParameters dHParameters) {
        super(false, dHParameters);
        this.f1283y = bigInteger;
    }

    public BigInteger getY() {
        return this.f1283y;
    }

    public int hashCode() {
        return this.f1283y.hashCode() ^ super.hashCode();
    }

    public boolean equals(Object obj) {
        boolean z = false;
        if (!(obj instanceof DHPublicKeyParameters)) {
            return false;
        }
        if (((DHPublicKeyParameters) obj).getY().equals(this.f1283y) && super.equals(obj)) {
            z = true;
        }
        return z;
    }
}
