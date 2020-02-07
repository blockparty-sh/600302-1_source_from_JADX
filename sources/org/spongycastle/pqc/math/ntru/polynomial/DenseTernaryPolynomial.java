package org.spongycastle.pqc.math.ntru.polynomial;

import java.security.SecureRandom;
import org.spongycastle.pqc.math.ntru.util.Util;
import org.spongycastle.util.Arrays;

public class DenseTernaryPolynomial extends IntegerPolynomial implements TernaryPolynomial {
    DenseTernaryPolynomial(int i) {
        super(i);
        checkTernarity();
    }

    public DenseTernaryPolynomial(IntegerPolynomial integerPolynomial) {
        this(integerPolynomial.coeffs);
    }

    public DenseTernaryPolynomial(int[] iArr) {
        super(iArr);
        checkTernarity();
    }

    private void checkTernarity() {
        for (int i = 0; i != this.coeffs.length; i++) {
            int i2 = this.coeffs[i];
            if (i2 < -1 || i2 > 1) {
                StringBuilder sb = new StringBuilder();
                sb.append("Illegal value: ");
                sb.append(i2);
                sb.append(", must be one of {-1, 0, 1}");
                throw new IllegalStateException(sb.toString());
            }
        }
    }

    public static DenseTernaryPolynomial generateRandom(int i, int i2, int i3, SecureRandom secureRandom) {
        return new DenseTernaryPolynomial(Util.generateRandomTernary(i, i2, i3, secureRandom));
    }

    public static DenseTernaryPolynomial generateRandom(int i, SecureRandom secureRandom) {
        DenseTernaryPolynomial denseTernaryPolynomial = new DenseTernaryPolynomial(i);
        for (int i2 = 0; i2 < i; i2++) {
            denseTernaryPolynomial.coeffs[i2] = secureRandom.nextInt(3) - 1;
        }
        return denseTernaryPolynomial;
    }

    public IntegerPolynomial mult(IntegerPolynomial integerPolynomial, int i) {
        if (i != 2048) {
            return super.mult(integerPolynomial, i);
        }
        IntegerPolynomial integerPolynomial2 = (IntegerPolynomial) integerPolynomial.clone();
        integerPolynomial2.modPositive(2048);
        return new LongPolynomial5(integerPolynomial2).mult(this).toIntegerPolynomial();
    }

    public int[] getOnes() {
        int length = this.coeffs.length;
        int[] iArr = new int[length];
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            if (this.coeffs[i2] == 1) {
                int i3 = i + 1;
                iArr[i] = i2;
                i = i3;
            }
        }
        return Arrays.copyOf(iArr, i);
    }

    public int[] getNegOnes() {
        int length = this.coeffs.length;
        int[] iArr = new int[length];
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            if (this.coeffs[i2] == -1) {
                int i3 = i + 1;
                iArr[i] = i2;
                i = i3;
            }
        }
        return Arrays.copyOf(iArr, i);
    }

    public int size() {
        return this.coeffs.length;
    }
}
