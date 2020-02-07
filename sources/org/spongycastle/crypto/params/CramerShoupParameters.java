package org.spongycastle.crypto.params;

import java.math.BigInteger;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.Digest;

public class CramerShoupParameters implements CipherParameters {

    /* renamed from: H */
    private Digest f1263H;

    /* renamed from: g1 */
    private BigInteger f1264g1;

    /* renamed from: g2 */
    private BigInteger f1265g2;

    /* renamed from: p */
    private BigInteger f1266p;

    public CramerShoupParameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, Digest digest) {
        this.f1266p = bigInteger;
        this.f1264g1 = bigInteger2;
        this.f1265g2 = bigInteger3;
        this.f1263H = digest;
    }

    public boolean equals(Object obj) {
        boolean z = false;
        if (!(obj instanceof DSAParameters)) {
            return false;
        }
        CramerShoupParameters cramerShoupParameters = (CramerShoupParameters) obj;
        if (cramerShoupParameters.getP().equals(this.f1266p) && cramerShoupParameters.getG1().equals(this.f1264g1) && cramerShoupParameters.getG2().equals(this.f1265g2)) {
            z = true;
        }
        return z;
    }

    public int hashCode() {
        return (getP().hashCode() ^ getG1().hashCode()) ^ getG2().hashCode();
    }

    public BigInteger getG1() {
        return this.f1264g1;
    }

    public BigInteger getG2() {
        return this.f1265g2;
    }

    public BigInteger getP() {
        return this.f1266p;
    }

    public Digest getH() {
        this.f1263H.reset();
        return this.f1263H;
    }
}
