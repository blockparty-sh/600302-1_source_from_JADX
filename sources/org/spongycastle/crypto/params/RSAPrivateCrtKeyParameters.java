package org.spongycastle.crypto.params;

import java.math.BigInteger;

public class RSAPrivateCrtKeyParameters extends RSAKeyParameters {

    /* renamed from: dP */
    private BigInteger f1320dP;

    /* renamed from: dQ */
    private BigInteger f1321dQ;

    /* renamed from: e */
    private BigInteger f1322e;

    /* renamed from: p */
    private BigInteger f1323p;

    /* renamed from: q */
    private BigInteger f1324q;
    private BigInteger qInv;

    public RSAPrivateCrtKeyParameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4, BigInteger bigInteger5, BigInteger bigInteger6, BigInteger bigInteger7, BigInteger bigInteger8) {
        super(true, bigInteger, bigInteger3);
        this.f1322e = bigInteger2;
        this.f1323p = bigInteger4;
        this.f1324q = bigInteger5;
        this.f1320dP = bigInteger6;
        this.f1321dQ = bigInteger7;
        this.qInv = bigInteger8;
    }

    public BigInteger getPublicExponent() {
        return this.f1322e;
    }

    public BigInteger getP() {
        return this.f1323p;
    }

    public BigInteger getQ() {
        return this.f1324q;
    }

    public BigInteger getDP() {
        return this.f1320dP;
    }

    public BigInteger getDQ() {
        return this.f1321dQ;
    }

    public BigInteger getQInv() {
        return this.qInv;
    }
}
