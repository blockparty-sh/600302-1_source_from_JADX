package org.spongycastle.math.p025ec.custom.sec;

import java.math.BigInteger;
import org.spongycastle.math.p025ec.ECFieldElement;
import org.spongycastle.math.raw.Mod;
import org.spongycastle.math.raw.Nat256;
import org.spongycastle.util.Arrays;

/* renamed from: org.spongycastle.math.ec.custom.sec.SecP256R1FieldElement */
public class SecP256R1FieldElement extends ECFieldElement {

    /* renamed from: Q */
    public static final BigInteger f1403Q = SecP256R1Curve.f1399q;

    /* renamed from: x */
    protected int[] f1404x;

    public String getFieldName() {
        return "SecP256R1Field";
    }

    public SecP256R1FieldElement(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.compareTo(f1403Q) >= 0) {
            throw new IllegalArgumentException("x value invalid for SecP256R1FieldElement");
        }
        this.f1404x = SecP256R1Field.fromBigInteger(bigInteger);
    }

    public SecP256R1FieldElement() {
        this.f1404x = Nat256.create();
    }

    protected SecP256R1FieldElement(int[] iArr) {
        this.f1404x = iArr;
    }

    public boolean isZero() {
        return Nat256.isZero(this.f1404x);
    }

    public boolean isOne() {
        return Nat256.isOne(this.f1404x);
    }

    public boolean testBitZero() {
        return Nat256.getBit(this.f1404x, 0) == 1;
    }

    public BigInteger toBigInteger() {
        return Nat256.toBigInteger(this.f1404x);
    }

    public int getFieldSize() {
        return f1403Q.bitLength();
    }

    public ECFieldElement add(ECFieldElement eCFieldElement) {
        int[] create = Nat256.create();
        SecP256R1Field.add(this.f1404x, ((SecP256R1FieldElement) eCFieldElement).f1404x, create);
        return new SecP256R1FieldElement(create);
    }

    public ECFieldElement addOne() {
        int[] create = Nat256.create();
        SecP256R1Field.addOne(this.f1404x, create);
        return new SecP256R1FieldElement(create);
    }

    public ECFieldElement subtract(ECFieldElement eCFieldElement) {
        int[] create = Nat256.create();
        SecP256R1Field.subtract(this.f1404x, ((SecP256R1FieldElement) eCFieldElement).f1404x, create);
        return new SecP256R1FieldElement(create);
    }

    public ECFieldElement multiply(ECFieldElement eCFieldElement) {
        int[] create = Nat256.create();
        SecP256R1Field.multiply(this.f1404x, ((SecP256R1FieldElement) eCFieldElement).f1404x, create);
        return new SecP256R1FieldElement(create);
    }

    public ECFieldElement divide(ECFieldElement eCFieldElement) {
        int[] create = Nat256.create();
        Mod.invert(SecP256R1Field.f1401P, ((SecP256R1FieldElement) eCFieldElement).f1404x, create);
        SecP256R1Field.multiply(create, this.f1404x, create);
        return new SecP256R1FieldElement(create);
    }

    public ECFieldElement negate() {
        int[] create = Nat256.create();
        SecP256R1Field.negate(this.f1404x, create);
        return new SecP256R1FieldElement(create);
    }

    public ECFieldElement square() {
        int[] create = Nat256.create();
        SecP256R1Field.square(this.f1404x, create);
        return new SecP256R1FieldElement(create);
    }

    public ECFieldElement invert() {
        int[] create = Nat256.create();
        Mod.invert(SecP256R1Field.f1401P, this.f1404x, create);
        return new SecP256R1FieldElement(create);
    }

    public ECFieldElement sqrt() {
        int[] iArr = this.f1404x;
        if (Nat256.isZero(iArr) || Nat256.isOne(iArr)) {
            return this;
        }
        int[] create = Nat256.create();
        int[] create2 = Nat256.create();
        SecP256R1Field.square(iArr, create);
        SecP256R1Field.multiply(create, iArr, create);
        SecP256R1Field.squareN(create, 2, create2);
        SecP256R1Field.multiply(create2, create, create2);
        SecP256R1Field.squareN(create2, 4, create);
        SecP256R1Field.multiply(create, create2, create);
        SecP256R1Field.squareN(create, 8, create2);
        SecP256R1Field.multiply(create2, create, create2);
        SecP256R1Field.squareN(create2, 16, create);
        SecP256R1Field.multiply(create, create2, create);
        SecP256R1Field.squareN(create, 32, create);
        SecP256R1Field.multiply(create, iArr, create);
        SecP256R1Field.squareN(create, 96, create);
        SecP256R1Field.multiply(create, iArr, create);
        SecP256R1Field.squareN(create, 94, create);
        SecP256R1Field.square(create, create2);
        return Nat256.m424eq(iArr, create2) ? new SecP256R1FieldElement(create) : null;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SecP256R1FieldElement)) {
            return false;
        }
        return Nat256.m424eq(this.f1404x, ((SecP256R1FieldElement) obj).f1404x);
    }

    public int hashCode() {
        return f1403Q.hashCode() ^ Arrays.hashCode(this.f1404x, 0, 8);
    }
}
