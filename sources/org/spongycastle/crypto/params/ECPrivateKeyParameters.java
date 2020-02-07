package org.spongycastle.crypto.params;

import java.math.BigInteger;

public class ECPrivateKeyParameters extends ECKeyParameters {

    /* renamed from: d */
    BigInteger f1294d;

    public ECPrivateKeyParameters(BigInteger bigInteger, ECDomainParameters eCDomainParameters) {
        super(true, eCDomainParameters);
        this.f1294d = bigInteger;
    }

    public BigInteger getD() {
        return this.f1294d;
    }
}
