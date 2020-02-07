package org.spongycastle.crypto.params;

import java.math.BigInteger;

public class DSAPrivateKeyParameters extends DSAKeyParameters {

    /* renamed from: x */
    private BigInteger f1289x;

    public DSAPrivateKeyParameters(BigInteger bigInteger, DSAParameters dSAParameters) {
        super(true, dSAParameters);
        this.f1289x = bigInteger;
    }

    public BigInteger getX() {
        return this.f1289x;
    }
}
