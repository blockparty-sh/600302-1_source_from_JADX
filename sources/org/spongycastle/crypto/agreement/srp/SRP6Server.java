package org.spongycastle.crypto.agreement.srp;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.spongycastle.crypto.CryptoException;
import org.spongycastle.crypto.Digest;

public class SRP6Server {

    /* renamed from: A */
    protected BigInteger f950A;

    /* renamed from: B */
    protected BigInteger f951B;
    protected BigInteger Key;

    /* renamed from: M1 */
    protected BigInteger f952M1;

    /* renamed from: M2 */
    protected BigInteger f953M2;

    /* renamed from: N */
    protected BigInteger f954N;

    /* renamed from: S */
    protected BigInteger f955S;

    /* renamed from: b */
    protected BigInteger f956b;
    protected Digest digest;

    /* renamed from: g */
    protected BigInteger f957g;
    protected SecureRandom random;

    /* renamed from: u */
    protected BigInteger f958u;

    /* renamed from: v */
    protected BigInteger f959v;

    public void init(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, Digest digest2, SecureRandom secureRandom) {
        this.f954N = bigInteger;
        this.f957g = bigInteger2;
        this.f959v = bigInteger3;
        this.random = secureRandom;
        this.digest = digest2;
    }

    public BigInteger generateServerCredentials() {
        BigInteger calculateK = SRP6Util.calculateK(this.digest, this.f954N, this.f957g);
        this.f956b = selectPrivateValue();
        this.f951B = calculateK.multiply(this.f959v).mod(this.f954N).add(this.f957g.modPow(this.f956b, this.f954N)).mod(this.f954N);
        return this.f951B;
    }

    public BigInteger calculateSecret(BigInteger bigInteger) throws CryptoException {
        this.f950A = SRP6Util.validatePublicValue(this.f954N, bigInteger);
        this.f958u = SRP6Util.calculateU(this.digest, this.f954N, this.f950A, this.f951B);
        this.f955S = calculateS();
        return this.f955S;
    }

    /* access modifiers changed from: protected */
    public BigInteger selectPrivateValue() {
        return SRP6Util.generatePrivateValue(this.digest, this.f954N, this.f957g, this.random);
    }

    private BigInteger calculateS() {
        return this.f959v.modPow(this.f958u, this.f954N).multiply(this.f950A).mod(this.f954N).modPow(this.f956b, this.f954N);
    }

    public boolean verifyClientEvidenceMessage(BigInteger bigInteger) throws CryptoException {
        BigInteger bigInteger2 = this.f950A;
        if (bigInteger2 != null) {
            BigInteger bigInteger3 = this.f951B;
            if (bigInteger3 != null) {
                BigInteger bigInteger4 = this.f955S;
                if (bigInteger4 != null) {
                    if (!SRP6Util.calculateM1(this.digest, this.f954N, bigInteger2, bigInteger3, bigInteger4).equals(bigInteger)) {
                        return false;
                    }
                    this.f952M1 = bigInteger;
                    return true;
                }
            }
        }
        throw new CryptoException("Impossible to compute and verify M1: some data are missing from the previous operations (A,B,S)");
    }

    public BigInteger calculateServerEvidenceMessage() throws CryptoException {
        BigInteger bigInteger = this.f950A;
        if (bigInteger != null) {
            BigInteger bigInteger2 = this.f952M1;
            if (bigInteger2 != null) {
                BigInteger bigInteger3 = this.f955S;
                if (bigInteger3 != null) {
                    this.f953M2 = SRP6Util.calculateM2(this.digest, this.f954N, bigInteger, bigInteger2, bigInteger3);
                    return this.f953M2;
                }
            }
        }
        throw new CryptoException("Impossible to compute M2: some data are missing from the previous operations (A,M1,S)");
    }

    public BigInteger calculateSessionKey() throws CryptoException {
        BigInteger bigInteger = this.f955S;
        if (bigInteger == null || this.f952M1 == null || this.f953M2 == null) {
            throw new CryptoException("Impossible to compute Key: some data are missing from the previous operations (S,M1,M2)");
        }
        this.Key = SRP6Util.calculateKey(this.digest, this.f954N, bigInteger);
        return this.Key;
    }
}
