package org.spongycastle.crypto.params;

import java.math.BigInteger;

public class CramerShoupPrivateKeyParameters extends CramerShoupKeyParameters {

    /* renamed from: pk */
    private CramerShoupPublicKeyParameters f1267pk;

    /* renamed from: x1 */
    private BigInteger f1268x1;

    /* renamed from: x2 */
    private BigInteger f1269x2;

    /* renamed from: y1 */
    private BigInteger f1270y1;

    /* renamed from: y2 */
    private BigInteger f1271y2;

    /* renamed from: z */
    private BigInteger f1272z;

    public CramerShoupPrivateKeyParameters(CramerShoupParameters cramerShoupParameters, BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4, BigInteger bigInteger5) {
        super(true, cramerShoupParameters);
        this.f1268x1 = bigInteger;
        this.f1269x2 = bigInteger2;
        this.f1270y1 = bigInteger3;
        this.f1271y2 = bigInteger4;
        this.f1272z = bigInteger5;
    }

    public BigInteger getX1() {
        return this.f1268x1;
    }

    public BigInteger getX2() {
        return this.f1269x2;
    }

    public BigInteger getY1() {
        return this.f1270y1;
    }

    public BigInteger getY2() {
        return this.f1271y2;
    }

    public BigInteger getZ() {
        return this.f1272z;
    }

    public void setPk(CramerShoupPublicKeyParameters cramerShoupPublicKeyParameters) {
        this.f1267pk = cramerShoupPublicKeyParameters;
    }

    public CramerShoupPublicKeyParameters getPk() {
        return this.f1267pk;
    }

    public int hashCode() {
        return ((((this.f1268x1.hashCode() ^ this.f1269x2.hashCode()) ^ this.f1270y1.hashCode()) ^ this.f1271y2.hashCode()) ^ this.f1272z.hashCode()) ^ super.hashCode();
    }

    public boolean equals(Object obj) {
        boolean z = false;
        if (!(obj instanceof CramerShoupPrivateKeyParameters)) {
            return false;
        }
        CramerShoupPrivateKeyParameters cramerShoupPrivateKeyParameters = (CramerShoupPrivateKeyParameters) obj;
        if (cramerShoupPrivateKeyParameters.getX1().equals(this.f1268x1) && cramerShoupPrivateKeyParameters.getX2().equals(this.f1269x2) && cramerShoupPrivateKeyParameters.getY1().equals(this.f1270y1) && cramerShoupPrivateKeyParameters.getY2().equals(this.f1271y2) && cramerShoupPrivateKeyParameters.getZ().equals(this.f1272z) && super.equals(obj)) {
            z = true;
        }
        return z;
    }
}
