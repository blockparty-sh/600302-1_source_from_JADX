package org.spongycastle.crypto.p023ec;

import org.spongycastle.math.p025ec.ECPoint;

/* renamed from: org.spongycastle.crypto.ec.ECPair */
public class ECPair {

    /* renamed from: x */
    private final ECPoint f1083x;

    /* renamed from: y */
    private final ECPoint f1084y;

    public ECPair(ECPoint eCPoint, ECPoint eCPoint2) {
        this.f1083x = eCPoint;
        this.f1084y = eCPoint2;
    }

    public ECPoint getX() {
        return this.f1083x;
    }

    public ECPoint getY() {
        return this.f1084y;
    }

    public boolean equals(ECPair eCPair) {
        return eCPair.getX().equals(getX()) && eCPair.getY().equals(getY());
    }

    public boolean equals(Object obj) {
        if (obj instanceof ECPair) {
            return equals((ECPair) obj);
        }
        return false;
    }

    public int hashCode() {
        return this.f1083x.hashCode() + (this.f1084y.hashCode() * 37);
    }
}
