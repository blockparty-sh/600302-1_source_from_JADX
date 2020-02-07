package org.spongycastle.crypto.params;

import java.math.BigInteger;
import org.spongycastle.crypto.CipherParameters;

public class ElGamalParameters implements CipherParameters {

    /* renamed from: g */
    private BigInteger f1296g;

    /* renamed from: l */
    private int f1297l;

    /* renamed from: p */
    private BigInteger f1298p;

    public ElGamalParameters(BigInteger bigInteger, BigInteger bigInteger2) {
        this(bigInteger, bigInteger2, 0);
    }

    public ElGamalParameters(BigInteger bigInteger, BigInteger bigInteger2, int i) {
        this.f1296g = bigInteger2;
        this.f1298p = bigInteger;
        this.f1297l = i;
    }

    public BigInteger getP() {
        return this.f1298p;
    }

    public BigInteger getG() {
        return this.f1296g;
    }

    public int getL() {
        return this.f1297l;
    }

    public boolean equals(Object obj) {
        boolean z = false;
        if (!(obj instanceof ElGamalParameters)) {
            return false;
        }
        ElGamalParameters elGamalParameters = (ElGamalParameters) obj;
        if (elGamalParameters.getP().equals(this.f1298p) && elGamalParameters.getG().equals(this.f1296g) && elGamalParameters.getL() == this.f1297l) {
            z = true;
        }
        return z;
    }

    public int hashCode() {
        return (getP().hashCode() ^ getG().hashCode()) + this.f1297l;
    }
}
