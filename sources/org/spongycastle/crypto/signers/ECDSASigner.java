package org.spongycastle.crypto.signers;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DSA;
import org.spongycastle.crypto.params.ECDomainParameters;
import org.spongycastle.crypto.params.ECKeyParameters;
import org.spongycastle.crypto.params.ECPrivateKeyParameters;
import org.spongycastle.crypto.params.ECPublicKeyParameters;
import org.spongycastle.crypto.params.ParametersWithRandom;
import org.spongycastle.math.p025ec.ECAlgorithms;
import org.spongycastle.math.p025ec.ECConstants;
import org.spongycastle.math.p025ec.ECMultiplier;
import org.spongycastle.math.p025ec.ECPoint;
import org.spongycastle.math.p025ec.FixedPointCombMultiplier;

public class ECDSASigner implements ECConstants, DSA {
    private final DSAKCalculator kCalculator;
    private ECKeyParameters key;
    private SecureRandom random;

    public ECDSASigner() {
        this.kCalculator = new RandomDSAKCalculator();
    }

    public ECDSASigner(DSAKCalculator dSAKCalculator) {
        this.kCalculator = dSAKCalculator;
    }

    public void init(boolean z, CipherParameters cipherParameters) {
        SecureRandom secureRandom;
        if (!z) {
            this.key = (ECPublicKeyParameters) cipherParameters;
        } else if (cipherParameters instanceof ParametersWithRandom) {
            ParametersWithRandom parametersWithRandom = (ParametersWithRandom) cipherParameters;
            this.key = (ECPrivateKeyParameters) parametersWithRandom.getParameters();
            secureRandom = parametersWithRandom.getRandom();
            this.random = initSecureRandom(!z && !this.kCalculator.isDeterministic(), secureRandom);
        } else {
            this.key = (ECPrivateKeyParameters) cipherParameters;
        }
        secureRandom = null;
        this.random = initSecureRandom(!z && !this.kCalculator.isDeterministic(), secureRandom);
    }

    public BigInteger[] generateSignature(byte[] bArr) {
        ECDomainParameters parameters = this.key.getParameters();
        BigInteger n = parameters.getN();
        BigInteger calculateE = calculateE(n, bArr);
        BigInteger d = ((ECPrivateKeyParameters) this.key).getD();
        if (this.kCalculator.isDeterministic()) {
            this.kCalculator.init(n, d, bArr);
        } else {
            this.kCalculator.init(n, this.random);
        }
        ECMultiplier createBasePointMultiplier = createBasePointMultiplier();
        while (true) {
            BigInteger nextK = this.kCalculator.nextK();
            BigInteger mod = createBasePointMultiplier.multiply(parameters.getG(), nextK).normalize().getAffineXCoord().toBigInteger().mod(n);
            if (!mod.equals(ZERO)) {
                BigInteger mod2 = nextK.modInverse(n).multiply(calculateE.add(d.multiply(mod))).mod(n);
                if (!mod2.equals(ZERO)) {
                    return new BigInteger[]{mod, mod2};
                }
            }
        }
    }

    public boolean verifySignature(byte[] bArr, BigInteger bigInteger, BigInteger bigInteger2) {
        ECDomainParameters parameters = this.key.getParameters();
        BigInteger n = parameters.getN();
        BigInteger calculateE = calculateE(n, bArr);
        if (bigInteger.compareTo(ONE) < 0 || bigInteger.compareTo(n) >= 0 || bigInteger2.compareTo(ONE) < 0 || bigInteger2.compareTo(n) >= 0) {
            return false;
        }
        BigInteger modInverse = bigInteger2.modInverse(n);
        ECPoint normalize = ECAlgorithms.sumOfTwoMultiplies(parameters.getG(), calculateE.multiply(modInverse).mod(n), ((ECPublicKeyParameters) this.key).getQ(), bigInteger.multiply(modInverse).mod(n)).normalize();
        if (normalize.isInfinity()) {
            return false;
        }
        return normalize.getAffineXCoord().toBigInteger().mod(n).equals(bigInteger);
    }

    /* access modifiers changed from: protected */
    public BigInteger calculateE(BigInteger bigInteger, byte[] bArr) {
        int bitLength = bigInteger.bitLength();
        int length = bArr.length * 8;
        BigInteger bigInteger2 = new BigInteger(1, bArr);
        return bitLength < length ? bigInteger2.shiftRight(length - bitLength) : bigInteger2;
    }

    /* access modifiers changed from: protected */
    public ECMultiplier createBasePointMultiplier() {
        return new FixedPointCombMultiplier();
    }

    /* access modifiers changed from: protected */
    public SecureRandom initSecureRandom(boolean z, SecureRandom secureRandom) {
        if (!z) {
            return null;
        }
        return secureRandom != null ? secureRandom : new SecureRandom();
    }
}
