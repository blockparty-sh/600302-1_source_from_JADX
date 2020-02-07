package org.spongycastle.crypto.generators;

import java.math.BigInteger;
import org.spongycastle.crypto.AsymmetricCipherKeyPair;
import org.spongycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.spongycastle.crypto.KeyGenerationParameters;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.crypto.params.RSAKeyGenerationParameters;
import org.spongycastle.crypto.params.RSAKeyParameters;
import org.spongycastle.crypto.params.RSAPrivateCrtKeyParameters;
import org.spongycastle.math.p025ec.WNafUtil;

public class RSAKeyPairGenerator implements AsymmetricCipherKeyPairGenerator {
    private static final BigInteger ONE = BigInteger.valueOf(1);
    private RSAKeyGenerationParameters param;

    public void init(KeyGenerationParameters keyGenerationParameters) {
        this.param = (RSAKeyGenerationParameters) keyGenerationParameters;
    }

    public AsymmetricCipherKeyPair generateKeyPair() {
        BigInteger chooseRandomPrime;
        BigInteger multiply;
        BigInteger bigInteger;
        BigInteger bigInteger2;
        int strength = this.param.getStrength();
        int i = strength >>> 1;
        int i2 = strength - i;
        int i3 = strength / 3;
        int i4 = strength >>> 2;
        BigInteger publicExponent = this.param.getPublicExponent();
        BigInteger chooseRandomPrime2 = chooseRandomPrime(i2, publicExponent);
        while (true) {
            chooseRandomPrime = chooseRandomPrime(i, publicExponent);
            if (chooseRandomPrime.subtract(chooseRandomPrime2).abs().bitLength() >= i3) {
                multiply = chooseRandomPrime2.multiply(chooseRandomPrime);
                if (multiply.bitLength() == strength) {
                    if (WNafUtil.getNafWeight(multiply) >= i4) {
                        break;
                    }
                    chooseRandomPrime2 = chooseRandomPrime(i2, publicExponent);
                } else {
                    chooseRandomPrime2 = chooseRandomPrime2.max(chooseRandomPrime);
                }
            }
        }
        if (chooseRandomPrime2.compareTo(chooseRandomPrime) < 0) {
            bigInteger = chooseRandomPrime2;
            bigInteger2 = chooseRandomPrime;
        } else {
            bigInteger2 = chooseRandomPrime2;
            bigInteger = chooseRandomPrime;
        }
        BigInteger subtract = bigInteger2.subtract(ONE);
        BigInteger subtract2 = bigInteger.subtract(ONE);
        BigInteger modInverse = publicExponent.modInverse(subtract.multiply(subtract2));
        BigInteger remainder = modInverse.remainder(subtract);
        BigInteger remainder2 = modInverse.remainder(subtract2);
        BigInteger modInverse2 = bigInteger.modInverse(bigInteger2);
        RSAKeyParameters rSAKeyParameters = new RSAKeyParameters(false, multiply, publicExponent);
        RSAPrivateCrtKeyParameters rSAPrivateCrtKeyParameters = new RSAPrivateCrtKeyParameters(multiply, publicExponent, modInverse, bigInteger2, bigInteger, remainder, remainder2, modInverse2);
        return new AsymmetricCipherKeyPair((AsymmetricKeyParameter) rSAKeyParameters, (AsymmetricKeyParameter) rSAPrivateCrtKeyParameters);
    }

    /* access modifiers changed from: protected */
    public BigInteger chooseRandomPrime(int i, BigInteger bigInteger) {
        while (true) {
            BigInteger bigInteger2 = new BigInteger(i, 1, this.param.getRandom());
            if (!bigInteger2.mod(bigInteger).equals(ONE) && bigInteger2.isProbablePrime(this.param.getCertainty()) && bigInteger.gcd(bigInteger2.subtract(ONE)).equals(ONE)) {
                return bigInteger2;
            }
        }
    }
}
