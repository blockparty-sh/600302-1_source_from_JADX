package org.spongycastle.math.p025ec.custom.sec;

import org.spongycastle.math.p025ec.ECCurve;
import org.spongycastle.math.p025ec.ECFieldElement;
import org.spongycastle.math.p025ec.ECPoint;
import org.spongycastle.math.p025ec.ECPoint.AbstractFp;
import org.spongycastle.math.raw.Nat;
import org.spongycastle.math.raw.Nat224;

/* renamed from: org.spongycastle.math.ec.custom.sec.SecP224R1Point */
public class SecP224R1Point extends AbstractFp {
    public SecP224R1Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        this(eCCurve, eCFieldElement, eCFieldElement2, false);
    }

    public SecP224R1Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, boolean z) {
        super(eCCurve, eCFieldElement, eCFieldElement2);
        boolean z2 = true;
        boolean z3 = eCFieldElement == null;
        if (eCFieldElement2 != null) {
            z2 = false;
        }
        if (z3 == z2) {
            this.withCompression = z;
            return;
        }
        throw new IllegalArgumentException("Exactly one of the field elements is null");
    }

    SecP224R1Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr, boolean z) {
        super(eCCurve, eCFieldElement, eCFieldElement2, eCFieldElementArr);
        this.withCompression = z;
    }

    /* access modifiers changed from: protected */
    public ECPoint detach() {
        return new SecP224R1Point(null, getAffineXCoord(), getAffineYCoord());
    }

    public ECPoint add(ECPoint eCPoint) {
        int[] iArr;
        int[] iArr2;
        int[] iArr3;
        int[] iArr4;
        if (isInfinity()) {
            return eCPoint;
        }
        if (eCPoint.isInfinity()) {
            return this;
        }
        if (this == eCPoint) {
            return twice();
        }
        ECCurve curve = getCurve();
        SecP224R1FieldElement secP224R1FieldElement = (SecP224R1FieldElement) this.f1361x;
        SecP224R1FieldElement secP224R1FieldElement2 = (SecP224R1FieldElement) this.f1362y;
        SecP224R1FieldElement secP224R1FieldElement3 = (SecP224R1FieldElement) eCPoint.getXCoord();
        SecP224R1FieldElement secP224R1FieldElement4 = (SecP224R1FieldElement) eCPoint.getYCoord();
        SecP224R1FieldElement secP224R1FieldElement5 = (SecP224R1FieldElement) this.f1363zs[0];
        SecP224R1FieldElement secP224R1FieldElement6 = (SecP224R1FieldElement) eCPoint.getZCoord(0);
        int[] createExt = Nat224.createExt();
        int[] create = Nat224.create();
        int[] create2 = Nat224.create();
        int[] create3 = Nat224.create();
        boolean isOne = secP224R1FieldElement5.isOne();
        if (isOne) {
            iArr2 = secP224R1FieldElement3.f1393x;
            iArr = secP224R1FieldElement4.f1393x;
        } else {
            SecP224R1Field.square(secP224R1FieldElement5.f1393x, create2);
            SecP224R1Field.multiply(create2, secP224R1FieldElement3.f1393x, create);
            SecP224R1Field.multiply(create2, secP224R1FieldElement5.f1393x, create2);
            SecP224R1Field.multiply(create2, secP224R1FieldElement4.f1393x, create2);
            iArr2 = create;
            iArr = create2;
        }
        boolean isOne2 = secP224R1FieldElement6.isOne();
        if (isOne2) {
            iArr4 = secP224R1FieldElement.f1393x;
            iArr3 = secP224R1FieldElement2.f1393x;
        } else {
            SecP224R1Field.square(secP224R1FieldElement6.f1393x, create3);
            SecP224R1Field.multiply(create3, secP224R1FieldElement.f1393x, createExt);
            SecP224R1Field.multiply(create3, secP224R1FieldElement6.f1393x, create3);
            SecP224R1Field.multiply(create3, secP224R1FieldElement2.f1393x, create3);
            iArr4 = createExt;
            iArr3 = create3;
        }
        int[] create4 = Nat224.create();
        SecP224R1Field.subtract(iArr4, iArr2, create4);
        SecP224R1Field.subtract(iArr3, iArr, create);
        if (!Nat224.isZero(create4)) {
            SecP224R1Field.square(create4, create2);
            int[] create5 = Nat224.create();
            SecP224R1Field.multiply(create2, create4, create5);
            SecP224R1Field.multiply(create2, iArr4, create2);
            SecP224R1Field.negate(create5, create5);
            Nat224.mul(iArr3, create5, createExt);
            SecP224R1Field.reduce32(Nat224.addBothTo(create2, create2, create5), create5);
            SecP224R1FieldElement secP224R1FieldElement7 = new SecP224R1FieldElement(create3);
            SecP224R1Field.square(create, secP224R1FieldElement7.f1393x);
            SecP224R1Field.subtract(secP224R1FieldElement7.f1393x, create5, secP224R1FieldElement7.f1393x);
            SecP224R1FieldElement secP224R1FieldElement8 = new SecP224R1FieldElement(create5);
            SecP224R1Field.subtract(create2, secP224R1FieldElement7.f1393x, secP224R1FieldElement8.f1393x);
            SecP224R1Field.multiplyAddToExt(secP224R1FieldElement8.f1393x, create, createExt);
            SecP224R1Field.reduce(createExt, secP224R1FieldElement8.f1393x);
            SecP224R1FieldElement secP224R1FieldElement9 = new SecP224R1FieldElement(create4);
            if (!isOne) {
                SecP224R1Field.multiply(secP224R1FieldElement9.f1393x, secP224R1FieldElement5.f1393x, secP224R1FieldElement9.f1393x);
            }
            if (!isOne2) {
                SecP224R1Field.multiply(secP224R1FieldElement9.f1393x, secP224R1FieldElement6.f1393x, secP224R1FieldElement9.f1393x);
            }
            SecP224R1Point secP224R1Point = new SecP224R1Point(curve, secP224R1FieldElement7, secP224R1FieldElement8, new ECFieldElement[]{secP224R1FieldElement9}, this.withCompression);
            return secP224R1Point;
        } else if (Nat224.isZero(create)) {
            return twice();
        } else {
            return curve.getInfinity();
        }
    }

    public ECPoint twice() {
        if (isInfinity()) {
            return this;
        }
        ECCurve curve = getCurve();
        SecP224R1FieldElement secP224R1FieldElement = (SecP224R1FieldElement) this.f1362y;
        if (secP224R1FieldElement.isZero()) {
            return curve.getInfinity();
        }
        SecP224R1FieldElement secP224R1FieldElement2 = (SecP224R1FieldElement) this.f1361x;
        SecP224R1FieldElement secP224R1FieldElement3 = (SecP224R1FieldElement) this.f1363zs[0];
        int[] create = Nat224.create();
        int[] create2 = Nat224.create();
        int[] create3 = Nat224.create();
        SecP224R1Field.square(secP224R1FieldElement.f1393x, create3);
        int[] create4 = Nat224.create();
        SecP224R1Field.square(create3, create4);
        boolean isOne = secP224R1FieldElement3.isOne();
        int[] iArr = secP224R1FieldElement3.f1393x;
        if (!isOne) {
            SecP224R1Field.square(secP224R1FieldElement3.f1393x, create2);
            iArr = create2;
        }
        SecP224R1Field.subtract(secP224R1FieldElement2.f1393x, iArr, create);
        SecP224R1Field.add(secP224R1FieldElement2.f1393x, iArr, create2);
        SecP224R1Field.multiply(create2, create, create2);
        SecP224R1Field.reduce32(Nat224.addBothTo(create2, create2, create2), create2);
        SecP224R1Field.multiply(create3, secP224R1FieldElement2.f1393x, create3);
        SecP224R1Field.reduce32(Nat.shiftUpBits(7, create3, 2, 0), create3);
        SecP224R1Field.reduce32(Nat.shiftUpBits(7, create4, 3, 0, create), create);
        SecP224R1FieldElement secP224R1FieldElement4 = new SecP224R1FieldElement(create4);
        SecP224R1Field.square(create2, secP224R1FieldElement4.f1393x);
        SecP224R1Field.subtract(secP224R1FieldElement4.f1393x, create3, secP224R1FieldElement4.f1393x);
        SecP224R1Field.subtract(secP224R1FieldElement4.f1393x, create3, secP224R1FieldElement4.f1393x);
        SecP224R1FieldElement secP224R1FieldElement5 = new SecP224R1FieldElement(create3);
        SecP224R1Field.subtract(create3, secP224R1FieldElement4.f1393x, secP224R1FieldElement5.f1393x);
        SecP224R1Field.multiply(secP224R1FieldElement5.f1393x, create2, secP224R1FieldElement5.f1393x);
        SecP224R1Field.subtract(secP224R1FieldElement5.f1393x, create, secP224R1FieldElement5.f1393x);
        SecP224R1FieldElement secP224R1FieldElement6 = new SecP224R1FieldElement(create2);
        SecP224R1Field.twice(secP224R1FieldElement.f1393x, secP224R1FieldElement6.f1393x);
        if (!isOne) {
            SecP224R1Field.multiply(secP224R1FieldElement6.f1393x, secP224R1FieldElement3.f1393x, secP224R1FieldElement6.f1393x);
        }
        SecP224R1FieldElement secP224R1FieldElement7 = secP224R1FieldElement4;
        SecP224R1FieldElement secP224R1FieldElement8 = secP224R1FieldElement5;
        SecP224R1Point secP224R1Point = new SecP224R1Point(curve, secP224R1FieldElement7, secP224R1FieldElement8, new ECFieldElement[]{secP224R1FieldElement6}, this.withCompression);
        return secP224R1Point;
    }

    public ECPoint twicePlus(ECPoint eCPoint) {
        if (this == eCPoint) {
            return threeTimes();
        }
        if (isInfinity()) {
            return eCPoint;
        }
        if (eCPoint.isInfinity()) {
            return twice();
        }
        if (this.f1362y.isZero()) {
            return eCPoint;
        }
        return twice().add(eCPoint);
    }

    public ECPoint threeTimes() {
        return (isInfinity() || this.f1362y.isZero()) ? this : twice().add(this);
    }

    public ECPoint negate() {
        if (isInfinity()) {
            return this;
        }
        SecP224R1Point secP224R1Point = new SecP224R1Point(this.curve, this.f1361x, this.f1362y.negate(), this.f1363zs, this.withCompression);
        return secP224R1Point;
    }
}
