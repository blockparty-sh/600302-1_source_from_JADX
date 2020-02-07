package org.spongycastle.asn1.p022x9;

import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.math.p025ec.ECCurve;
import org.spongycastle.math.p025ec.ECPoint;

/* renamed from: org.spongycastle.asn1.x9.X9ECPoint */
public class X9ECPoint extends ASN1Object {

    /* renamed from: p */
    ECPoint f924p;

    public X9ECPoint(ECPoint eCPoint) {
        this.f924p = eCPoint.normalize();
    }

    public X9ECPoint(ECCurve eCCurve, ASN1OctetString aSN1OctetString) {
        this.f924p = eCCurve.decodePoint(aSN1OctetString.getOctets());
    }

    public ECPoint getPoint() {
        return this.f924p;
    }

    public ASN1Primitive toASN1Primitive() {
        return new DEROctetString(this.f924p.getEncoded());
    }
}
