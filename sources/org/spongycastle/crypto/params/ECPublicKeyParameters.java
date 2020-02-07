package org.spongycastle.crypto.params;

import org.spongycastle.math.p025ec.ECPoint;

public class ECPublicKeyParameters extends ECKeyParameters {

    /* renamed from: Q */
    ECPoint f1295Q;

    public ECPublicKeyParameters(ECPoint eCPoint, ECDomainParameters eCDomainParameters) {
        super(false, eCDomainParameters);
        this.f1295Q = eCPoint.normalize();
    }

    public ECPoint getQ() {
        return this.f1295Q;
    }
}
