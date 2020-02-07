package org.spongycastle.crypto.signers;

import java.math.BigInteger;
import java.security.SecureRandom;

class RandomDSAKCalculator implements DSAKCalculator {
    private static final BigInteger ZERO = BigInteger.valueOf(0);

    /* renamed from: q */
    private BigInteger f1341q;
    private SecureRandom random;

    public boolean isDeterministic() {
        return false;
    }

    RandomDSAKCalculator() {
    }

    public void init(BigInteger bigInteger, SecureRandom secureRandom) {
        this.f1341q = bigInteger;
        this.random = secureRandom;
    }

    public void init(BigInteger bigInteger, BigInteger bigInteger2, byte[] bArr) {
        throw new IllegalStateException("Operation not supported");
    }

    public BigInteger nextK() {
        int bitLength = this.f1341q.bitLength();
        while (true) {
            BigInteger bigInteger = new BigInteger(bitLength, this.random);
            if (!bigInteger.equals(ZERO) && bigInteger.compareTo(this.f1341q) < 0) {
                return bigInteger;
            }
        }
    }
}
