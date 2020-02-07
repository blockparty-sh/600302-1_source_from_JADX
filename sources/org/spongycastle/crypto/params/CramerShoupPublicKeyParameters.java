package org.spongycastle.crypto.params;

import java.math.BigInteger;

public class CramerShoupPublicKeyParameters extends CramerShoupKeyParameters {

    /* renamed from: c */
    private BigInteger f1273c;

    /* renamed from: d */
    private BigInteger f1274d;

    /* renamed from: h */
    private BigInteger f1275h;

    public CramerShoupPublicKeyParameters(CramerShoupParameters cramerShoupParameters, BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        super(false, cramerShoupParameters);
        this.f1273c = bigInteger;
        this.f1274d = bigInteger2;
        this.f1275h = bigInteger3;
    }

    public BigInteger getC() {
        return this.f1273c;
    }

    public BigInteger getD() {
        return this.f1274d;
    }

    public BigInteger getH() {
        return this.f1275h;
    }

    public int hashCode() {
        return ((this.f1273c.hashCode() ^ this.f1274d.hashCode()) ^ this.f1275h.hashCode()) ^ super.hashCode();
    }

    public boolean equals(Object obj) {
        boolean z = false;
        if (!(obj instanceof CramerShoupPublicKeyParameters)) {
            return false;
        }
        CramerShoupPublicKeyParameters cramerShoupPublicKeyParameters = (CramerShoupPublicKeyParameters) obj;
        if (cramerShoupPublicKeyParameters.getC().equals(this.f1273c) && cramerShoupPublicKeyParameters.getD().equals(this.f1274d) && cramerShoupPublicKeyParameters.getH().equals(this.f1275h) && super.equals(obj)) {
            z = true;
        }
        return z;
    }
}
