package org.spongycastle.pqc.crypto.ntru;

import org.spongycastle.crypto.AsymmetricCipherKeyPair;
import org.spongycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.spongycastle.crypto.KeyGenerationParameters;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.pqc.math.ntru.polynomial.DenseTernaryPolynomial;
import org.spongycastle.pqc.math.ntru.polynomial.IntegerPolynomial;
import org.spongycastle.pqc.math.ntru.polynomial.Polynomial;
import org.spongycastle.pqc.math.ntru.polynomial.ProductFormPolynomial;
import org.spongycastle.pqc.math.ntru.util.Util;

public class NTRUEncryptionKeyPairGenerator implements AsymmetricCipherKeyPairGenerator {
    private NTRUEncryptionKeyGenerationParameters params;

    public void init(KeyGenerationParameters keyGenerationParameters) {
        this.params = (NTRUEncryptionKeyGenerationParameters) keyGenerationParameters;
    }

    public AsymmetricCipherKeyPair generateKeyPair() {
        IntegerPolynomial integerPolynomial;
        Polynomial polynomial;
        IntegerPolynomial invertFq;
        IntegerPolynomial integerPolynomial2;
        DenseTernaryPolynomial generateRandom;
        Polynomial generateRandom2;
        int i = this.params.f1492N;
        int i2 = this.params.f1498q;
        int i3 = this.params.f1495df;
        int i4 = this.params.df1;
        int i5 = this.params.df2;
        int i6 = this.params.df3;
        int i7 = this.params.f1496dg;
        boolean z = this.params.fastFp;
        boolean z2 = this.params.sparse;
        IntegerPolynomial integerPolynomial3 = null;
        while (true) {
            if (z) {
                if (this.params.polyType == 0) {
                    polynomial = Util.generateRandomTernary(i, i3, i3, z2, this.params.getRandom());
                } else {
                    polynomial = ProductFormPolynomial.generateRandom(i, i4, i5, i6, i6, this.params.getRandom());
                }
                integerPolynomial = polynomial.toIntegerPolynomial();
                integerPolynomial.mult(3);
                int[] iArr = integerPolynomial.coeffs;
                iArr[0] = iArr[0] + 1;
            } else {
                if (this.params.polyType == 0) {
                    generateRandom2 = Util.generateRandomTernary(i, i3, i3 - 1, z2, this.params.getRandom());
                } else {
                    generateRandom2 = ProductFormPolynomial.generateRandom(i, i4, i5, i6, i6 - 1, this.params.getRandom());
                }
                integerPolynomial = polynomial.toIntegerPolynomial();
                integerPolynomial3 = integerPolynomial.invertF3();
                if (integerPolynomial3 == null) {
                    continue;
                }
            }
            invertFq = integerPolynomial.invertFq(i2);
            if (invertFq != null) {
                break;
            }
        }
        if (z) {
            integerPolynomial2 = new IntegerPolynomial(i);
            integerPolynomial2.coeffs[0] = 1;
        } else {
            integerPolynomial2 = integerPolynomial3;
        }
        do {
            generateRandom = DenseTernaryPolynomial.generateRandom(i, i7, i7 - 1, this.params.getRandom());
        } while (generateRandom.invertFq(i2) == null);
        IntegerPolynomial mult = generateRandom.mult(invertFq, i2);
        mult.mult3(i2);
        mult.ensurePositive(i2);
        generateRandom.clear();
        invertFq.clear();
        return new AsymmetricCipherKeyPair((AsymmetricKeyParameter) new NTRUEncryptionPublicKeyParameters(mult, this.params.getEncryptionParameters()), (AsymmetricKeyParameter) new NTRUEncryptionPrivateKeyParameters(mult, polynomial, integerPolynomial2, this.params.getEncryptionParameters()));
    }
}
