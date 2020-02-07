package org.spongycastle.crypto.params;

import java.math.BigInteger;

public class GOST3410PublicKeyParameters extends GOST3410KeyParameters {

    /* renamed from: y */
    private BigInteger f1305y;

    public GOST3410PublicKeyParameters(BigInteger bigInteger, GOST3410Parameters gOST3410Parameters) {
        super(false, gOST3410Parameters);
        this.f1305y = bigInteger;
    }

    public BigInteger getY() {
        return this.f1305y;
    }
}
