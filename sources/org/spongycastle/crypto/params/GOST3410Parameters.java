package org.spongycastle.crypto.params;

import java.math.BigInteger;
import org.spongycastle.crypto.CipherParameters;

public class GOST3410Parameters implements CipherParameters {

    /* renamed from: a */
    private BigInteger f1301a;

    /* renamed from: p */
    private BigInteger f1302p;

    /* renamed from: q */
    private BigInteger f1303q;
    private GOST3410ValidationParameters validation;

    public GOST3410Parameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        this.f1302p = bigInteger;
        this.f1303q = bigInteger2;
        this.f1301a = bigInteger3;
    }

    public GOST3410Parameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, GOST3410ValidationParameters gOST3410ValidationParameters) {
        this.f1301a = bigInteger3;
        this.f1302p = bigInteger;
        this.f1303q = bigInteger2;
        this.validation = gOST3410ValidationParameters;
    }

    public BigInteger getP() {
        return this.f1302p;
    }

    public BigInteger getQ() {
        return this.f1303q;
    }

    public BigInteger getA() {
        return this.f1301a;
    }

    public GOST3410ValidationParameters getValidationParameters() {
        return this.validation;
    }

    public int hashCode() {
        return (this.f1302p.hashCode() ^ this.f1303q.hashCode()) ^ this.f1301a.hashCode();
    }

    public boolean equals(Object obj) {
        boolean z = false;
        if (!(obj instanceof GOST3410Parameters)) {
            return false;
        }
        GOST3410Parameters gOST3410Parameters = (GOST3410Parameters) obj;
        if (gOST3410Parameters.getP().equals(this.f1302p) && gOST3410Parameters.getQ().equals(this.f1303q) && gOST3410Parameters.getA().equals(this.f1301a)) {
            z = true;
        }
        return z;
    }
}
