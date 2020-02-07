package org.spongycastle.crypto.agreement.srp;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.spongycastle.crypto.CryptoException;
import org.spongycastle.crypto.Digest;

public class SRP6Client {

    /* renamed from: A */
    protected BigInteger f940A;

    /* renamed from: B */
    protected BigInteger f941B;
    protected BigInteger Key;

    /* renamed from: M1 */
    protected BigInteger f942M1;

    /* renamed from: M2 */
    protected BigInteger f943M2;

    /* renamed from: N */
    protected BigInteger f944N;

    /* renamed from: S */
    protected BigInteger f945S;

    /* renamed from: a */
    protected BigInteger f946a;
    protected Digest digest;

    /* renamed from: g */
    protected BigInteger f947g;
    protected SecureRandom random;

    /* renamed from: u */
    protected BigInteger f948u;

    /* renamed from: x */
    protected BigInteger f949x;

    public void init(BigInteger bigInteger, BigInteger bigInteger2, Digest digest2, SecureRandom secureRandom) {
        this.f944N = bigInteger;
        this.f947g = bigInteger2;
        this.digest = digest2;
        this.random = secureRandom;
    }

    public BigInteger generateClientCredentials(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        this.f949x = SRP6Util.calculateX(this.digest, this.f944N, bArr, bArr2, bArr3);
        this.f946a = selectPrivateValue();
        this.f940A = this.f947g.modPow(this.f946a, this.f944N);
        return this.f940A;
    }

    public BigInteger calculateSecret(BigInteger bigInteger) throws CryptoException {
        this.f941B = SRP6Util.validatePublicValue(this.f944N, bigInteger);
        this.f948u = SRP6Util.calculateU(this.digest, this.f944N, this.f940A, this.f941B);
        this.f945S = calculateS();
        return this.f945S;
    }

    /* access modifiers changed from: protected */
    public BigInteger selectPrivateValue() {
        return SRP6Util.generatePrivateValue(this.digest, this.f944N, this.f947g, this.random);
    }

    private BigInteger calculateS() {
        BigInteger calculateK = SRP6Util.calculateK(this.digest, this.f944N, this.f947g);
        return this.f941B.subtract(this.f947g.modPow(this.f949x, this.f944N).multiply(calculateK).mod(this.f944N)).mod(this.f944N).modPow(this.f948u.multiply(this.f949x).add(this.f946a), this.f944N);
    }

    public BigInteger calculateClientEvidenceMessage() throws CryptoException {
        BigInteger bigInteger = this.f940A;
        if (bigInteger != null) {
            BigInteger bigInteger2 = this.f941B;
            if (bigInteger2 != null) {
                BigInteger bigInteger3 = this.f945S;
                if (bigInteger3 != null) {
                    this.f942M1 = SRP6Util.calculateM1(this.digest, this.f944N, bigInteger, bigInteger2, bigInteger3);
                    return this.f942M1;
                }
            }
        }
        throw new CryptoException("Impossible to compute M1: some data are missing from the previous operations (A,B,S)");
    }

    public boolean verifyServerEvidenceMessage(BigInteger bigInteger) throws CryptoException {
        BigInteger bigInteger2 = this.f940A;
        if (bigInteger2 != null) {
            BigInteger bigInteger3 = this.f942M1;
            if (bigInteger3 != null) {
                BigInteger bigInteger4 = this.f945S;
                if (bigInteger4 != null) {
                    if (!SRP6Util.calculateM2(this.digest, this.f944N, bigInteger2, bigInteger3, bigInteger4).equals(bigInteger)) {
                        return false;
                    }
                    this.f943M2 = bigInteger;
                    return true;
                }
            }
        }
        throw new CryptoException("Impossible to compute and verify M2: some data are missing from the previous operations (A,M1,S)");
    }

    public BigInteger calculateSessionKey() throws CryptoException {
        BigInteger bigInteger = this.f945S;
        if (bigInteger == null || this.f942M1 == null || this.f943M2 == null) {
            throw new CryptoException("Impossible to compute Key: some data are missing from the previous operations (S,M1,M2)");
        }
        this.Key = SRP6Util.calculateKey(this.digest, this.f944N, bigInteger);
        return this.Key;
    }
}
