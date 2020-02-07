package org.spongycastle.crypto.params;

import java.math.BigInteger;

public class DHPrivateKeyParameters extends DHKeyParameters {

    /* renamed from: x */
    private BigInteger f1282x;

    public DHPrivateKeyParameters(BigInteger bigInteger, DHParameters dHParameters) {
        super(true, dHParameters);
        this.f1282x = bigInteger;
    }

    public BigInteger getX() {
        return this.f1282x;
    }

    public int hashCode() {
        return this.f1282x.hashCode() ^ super.hashCode();
    }

    public boolean equals(Object obj) {
        boolean z = false;
        if (!(obj instanceof DHPrivateKeyParameters)) {
            return false;
        }
        if (((DHPrivateKeyParameters) obj).getX().equals(this.f1282x) && super.equals(obj)) {
            z = true;
        }
        return z;
    }
}
