package org.spongycastle.pqc.math.ntru.polynomial;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;
import org.spongycastle.util.Arrays;

public class ProductFormPolynomial implements Polynomial {

    /* renamed from: f1 */
    private SparseTernaryPolynomial f1558f1;

    /* renamed from: f2 */
    private SparseTernaryPolynomial f1559f2;

    /* renamed from: f3 */
    private SparseTernaryPolynomial f1560f3;

    public ProductFormPolynomial(SparseTernaryPolynomial sparseTernaryPolynomial, SparseTernaryPolynomial sparseTernaryPolynomial2, SparseTernaryPolynomial sparseTernaryPolynomial3) {
        this.f1558f1 = sparseTernaryPolynomial;
        this.f1559f2 = sparseTernaryPolynomial2;
        this.f1560f3 = sparseTernaryPolynomial3;
    }

    public static ProductFormPolynomial generateRandom(int i, int i2, int i3, int i4, int i5, SecureRandom secureRandom) {
        return new ProductFormPolynomial(SparseTernaryPolynomial.generateRandom(i, i2, i2, secureRandom), SparseTernaryPolynomial.generateRandom(i, i3, i3, secureRandom), SparseTernaryPolynomial.generateRandom(i, i4, i5, secureRandom));
    }

    public static ProductFormPolynomial fromBinary(byte[] bArr, int i, int i2, int i3, int i4, int i5) throws IOException {
        return fromBinary((InputStream) new ByteArrayInputStream(bArr), i, i2, i3, i4, i5);
    }

    public static ProductFormPolynomial fromBinary(InputStream inputStream, int i, int i2, int i3, int i4, int i5) throws IOException {
        return new ProductFormPolynomial(SparseTernaryPolynomial.fromBinary(inputStream, i, i2, i2), SparseTernaryPolynomial.fromBinary(inputStream, i, i3, i3), SparseTernaryPolynomial.fromBinary(inputStream, i, i4, i5));
    }

    public byte[] toBinary() {
        byte[] binary = this.f1558f1.toBinary();
        byte[] binary2 = this.f1559f2.toBinary();
        byte[] binary3 = this.f1560f3.toBinary();
        byte[] copyOf = Arrays.copyOf(binary, binary.length + binary2.length + binary3.length);
        System.arraycopy(binary2, 0, copyOf, binary.length, binary2.length);
        System.arraycopy(binary3, 0, copyOf, binary.length + binary2.length, binary3.length);
        return copyOf;
    }

    public IntegerPolynomial mult(IntegerPolynomial integerPolynomial) {
        IntegerPolynomial mult = this.f1559f2.mult(this.f1558f1.mult(integerPolynomial));
        mult.add(this.f1560f3.mult(integerPolynomial));
        return mult;
    }

    public BigIntPolynomial mult(BigIntPolynomial bigIntPolynomial) {
        BigIntPolynomial mult = this.f1559f2.mult(this.f1558f1.mult(bigIntPolynomial));
        mult.add(this.f1560f3.mult(bigIntPolynomial));
        return mult;
    }

    public IntegerPolynomial toIntegerPolynomial() {
        IntegerPolynomial mult = this.f1558f1.mult(this.f1559f2.toIntegerPolynomial());
        mult.add(this.f1560f3.toIntegerPolynomial());
        return mult;
    }

    public IntegerPolynomial mult(IntegerPolynomial integerPolynomial, int i) {
        IntegerPolynomial mult = mult(integerPolynomial);
        mult.mod(i);
        return mult;
    }

    public int hashCode() {
        SparseTernaryPolynomial sparseTernaryPolynomial = this.f1558f1;
        int i = 0;
        int hashCode = ((sparseTernaryPolynomial == null ? 0 : sparseTernaryPolynomial.hashCode()) + 31) * 31;
        SparseTernaryPolynomial sparseTernaryPolynomial2 = this.f1559f2;
        int hashCode2 = (hashCode + (sparseTernaryPolynomial2 == null ? 0 : sparseTernaryPolynomial2.hashCode())) * 31;
        SparseTernaryPolynomial sparseTernaryPolynomial3 = this.f1560f3;
        if (sparseTernaryPolynomial3 != null) {
            i = sparseTernaryPolynomial3.hashCode();
        }
        return hashCode2 + i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ProductFormPolynomial productFormPolynomial = (ProductFormPolynomial) obj;
        SparseTernaryPolynomial sparseTernaryPolynomial = this.f1558f1;
        if (sparseTernaryPolynomial == null) {
            if (productFormPolynomial.f1558f1 != null) {
                return false;
            }
        } else if (!sparseTernaryPolynomial.equals(productFormPolynomial.f1558f1)) {
            return false;
        }
        SparseTernaryPolynomial sparseTernaryPolynomial2 = this.f1559f2;
        if (sparseTernaryPolynomial2 == null) {
            if (productFormPolynomial.f1559f2 != null) {
                return false;
            }
        } else if (!sparseTernaryPolynomial2.equals(productFormPolynomial.f1559f2)) {
            return false;
        }
        SparseTernaryPolynomial sparseTernaryPolynomial3 = this.f1560f3;
        if (sparseTernaryPolynomial3 == null) {
            if (productFormPolynomial.f1560f3 != null) {
                return false;
            }
        } else if (!sparseTernaryPolynomial3.equals(productFormPolynomial.f1560f3)) {
            return false;
        }
        return true;
    }
}
