package org.spongycastle.crypto.params;

import java.math.BigInteger;

public class DSAPublicKeyParameters extends DSAKeyParameters {

    /* renamed from: y */
    private BigInteger f1290y;

    public DSAPublicKeyParameters(BigInteger bigInteger, DSAParameters dSAParameters) {
        super(false, dSAParameters);
        this.f1290y = bigInteger;
    }

    public BigInteger getY() {
        return this.f1290y;
    }
}
