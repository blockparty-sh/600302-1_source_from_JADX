package org.spongycastle.crypto.agreement.srp;

import java.math.BigInteger;
import org.spongycastle.crypto.Digest;

public class SRP6VerifierGenerator {

    /* renamed from: N */
    protected BigInteger f960N;
    protected Digest digest;

    /* renamed from: g */
    protected BigInteger f961g;

    public void init(BigInteger bigInteger, BigInteger bigInteger2, Digest digest2) {
        this.f960N = bigInteger;
        this.f961g = bigInteger2;
        this.digest = digest2;
    }

    public BigInteger generateVerifier(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        return this.f961g.modPow(SRP6Util.calculateX(this.digest, this.f960N, bArr, bArr2, bArr3), this.f960N);
    }
}
