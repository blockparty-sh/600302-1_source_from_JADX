package org.spongycastle.math.p025ec.custom.sec;

import java.math.BigInteger;
import org.spongycastle.math.p025ec.ECFieldElement;
import org.spongycastle.math.raw.Mod;
import org.spongycastle.math.raw.Nat192;
import org.spongycastle.util.Arrays;

/* renamed from: org.spongycastle.math.ec.custom.sec.SecP192R1FieldElement */
public class SecP192R1FieldElement extends ECFieldElement {

    /* renamed from: Q */
    public static final BigInteger f1381Q = SecP192R1Curve.f1377q;

    /* renamed from: x */
    protected int[] f1382x;

    public String getFieldName() {
        return "SecP192R1Field";
    }

    public SecP192R1FieldElement(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.compareTo(f1381Q) >= 0) {
            throw new IllegalArgumentException("x value invalid for SecP192R1FieldElement");
        }
        this.f1382x = SecP192R1Field.fromBigInteger(bigInteger);
    }

    public SecP192R1FieldElement() {
        this.f1382x = Nat192.create();
    }

    protected SecP192R1FieldElement(int[] iArr) {
        this.f1382x = iArr;
    }

    public boolean isZero() {
        return Nat192.isZero(this.f1382x);
    }

    public boolean isOne() {
        return Nat192.isOne(this.f1382x);
    }

    public boolean testBitZero() {
        return Nat192.getBit(this.f1382x, 0) == 1;
    }

    public BigInteger toBigInteger() {
        return Nat192.toBigInteger(this.f1382x);
    }

    public int getFieldSize() {
        return f1381Q.bitLength();
    }

    public ECFieldElement add(ECFieldElement eCFieldElement) {
        int[] create = Nat192.create();
        SecP192R1Field.add(this.f1382x, ((SecP192R1FieldElement) eCFieldElement).f1382x, create);
        return new SecP192R1FieldElement(create);
    }

    public ECFieldElement addOne() {
        int[] create = Nat192.create();
        SecP192R1Field.addOne(this.f1382x, create);
        return new SecP192R1FieldElement(create);
    }

    public ECFieldElement subtract(ECFieldElement eCFieldElement) {
        int[] create = Nat192.create();
        SecP192R1Field.subtract(this.f1382x, ((SecP192R1FieldElement) eCFieldElement).f1382x, create);
        return new SecP192R1FieldElement(create);
    }

    public ECFieldElement multiply(ECFieldElement eCFieldElement) {
        int[] create = Nat192.create();
        SecP192R1Field.multiply(this.f1382x, ((SecP192R1FieldElement) eCFieldElement).f1382x, create);
        return new SecP192R1FieldElement(create);
    }

    public ECFieldElement divide(ECFieldElement eCFieldElement) {
        int[] create = Nat192.create();
        Mod.invert(SecP192R1Field.f1379P, ((SecP192R1FieldElement) eCFieldElement).f1382x, create);
        SecP192R1Field.multiply(create, this.f1382x, create);
        return new SecP192R1FieldElement(create);
    }

    public ECFieldElement negate() {
        int[] create = Nat192.create();
        SecP192R1Field.negate(this.f1382x, create);
        return new SecP192R1FieldElement(create);
    }

    public ECFieldElement square() {
        int[] create = Nat192.create();
        SecP192R1Field.square(this.f1382x, create);
        return new SecP192R1FieldElement(create);
    }

    public ECFieldElement invert() {
        int[] create = Nat192.create();
        Mod.invert(SecP192R1Field.f1379P, this.f1382x, create);
        return new SecP192R1FieldElement(create);
    }

    public ECFieldElement sqrt() {
        int[] iArr = this.f1382x;
        if (Nat192.isZero(iArr) || Nat192.isOne(iArr)) {
            return this;
        }
        int[] create = Nat192.create();
        int[] create2 = Nat192.create();
        SecP192R1Field.square(iArr, create);
        SecP192R1Field.multiply(create, iArr, create);
        SecP192R1Field.squareN(create, 2, create2);
        SecP192R1Field.multiply(create2, create, create2);
        SecP192R1Field.squareN(create2, 4, create);
        SecP192R1Field.multiply(create, create2, create);
        SecP192R1Field.squareN(create, 8, create2);
        SecP192R1Field.multiply(create2, create, create2);
        SecP192R1Field.squareN(create2, 16, create);
        SecP192R1Field.multiply(create, create2, create);
        SecP192R1Field.squareN(create, 32, create2);
        SecP192R1Field.multiply(create2, create, create2);
        SecP192R1Field.squareN(create2, 64, create);
        SecP192R1Field.multiply(create, create2, create);
        SecP192R1Field.squareN(create, 62, create);
        SecP192R1Field.square(create, create2);
        return Nat192.m422eq(iArr, create2) ? new SecP192R1FieldElement(create) : null;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SecP192R1FieldElement)) {
            return false;
        }
        return Nat192.m422eq(this.f1382x, ((SecP192R1FieldElement) obj).f1382x);
    }

    public int hashCode() {
        return f1381Q.hashCode() ^ Arrays.hashCode(this.f1382x, 0, 6);
    }
}
