package org.spongycastle.math.p025ec.custom.sec;

import org.spongycastle.math.p025ec.ECCurve;
import org.spongycastle.math.p025ec.ECFieldElement;
import org.spongycastle.math.p025ec.ECPoint;
import org.spongycastle.math.p025ec.ECPoint.AbstractFp;
import org.spongycastle.math.raw.Nat;

/* renamed from: org.spongycastle.math.ec.custom.sec.SecP521R1Point */
public class SecP521R1Point extends AbstractFp {
    public SecP521R1Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        this(eCCurve, eCFieldElement, eCFieldElement2, false);
    }

    public SecP521R1Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, boolean z) {
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

    SecP521R1Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr, boolean z) {
        super(eCCurve, eCFieldElement, eCFieldElement2, eCFieldElementArr);
        this.withCompression = z;
    }

    /* access modifiers changed from: protected */
    public ECPoint detach() {
        return new SecP521R1Point(null, getAffineXCoord(), getAffineYCoord());
    }

    public ECPoint add(ECPoint eCPoint) {
        int[] iArr;
        int[] iArr2;
        int[] iArr3;
        int[] iArr4;
        ECPoint eCPoint2 = eCPoint;
        if (isInfinity()) {
            return eCPoint2;
        }
        if (eCPoint.isInfinity()) {
            return this;
        }
        if (this == eCPoint2) {
            return twice();
        }
        ECCurve curve = getCurve();
        SecP521R1FieldElement secP521R1FieldElement = (SecP521R1FieldElement) this.f1361x;
        SecP521R1FieldElement secP521R1FieldElement2 = (SecP521R1FieldElement) this.f1362y;
        SecP521R1FieldElement secP521R1FieldElement3 = (SecP521R1FieldElement) eCPoint.getXCoord();
        SecP521R1FieldElement secP521R1FieldElement4 = (SecP521R1FieldElement) eCPoint.getYCoord();
        SecP521R1FieldElement secP521R1FieldElement5 = (SecP521R1FieldElement) this.f1363zs[0];
        SecP521R1FieldElement secP521R1FieldElement6 = (SecP521R1FieldElement) eCPoint2.getZCoord(0);
        int[] create = Nat.create(17);
        int[] create2 = Nat.create(17);
        int[] create3 = Nat.create(17);
        int[] create4 = Nat.create(17);
        boolean isOne = secP521R1FieldElement5.isOne();
        if (isOne) {
            iArr2 = secP521R1FieldElement3.f1413x;
            iArr = secP521R1FieldElement4.f1413x;
        } else {
            SecP521R1Field.square(secP521R1FieldElement5.f1413x, create3);
            SecP521R1Field.multiply(create3, secP521R1FieldElement3.f1413x, create2);
            SecP521R1Field.multiply(create3, secP521R1FieldElement5.f1413x, create3);
            SecP521R1Field.multiply(create3, secP521R1FieldElement4.f1413x, create3);
            iArr2 = create2;
            iArr = create3;
        }
        boolean isOne2 = secP521R1FieldElement6.isOne();
        if (isOne2) {
            iArr4 = secP521R1FieldElement.f1413x;
            iArr3 = secP521R1FieldElement2.f1413x;
        } else {
            SecP521R1Field.square(secP521R1FieldElement6.f1413x, create4);
            SecP521R1Field.multiply(create4, secP521R1FieldElement.f1413x, create);
            SecP521R1Field.multiply(create4, secP521R1FieldElement6.f1413x, create4);
            SecP521R1Field.multiply(create4, secP521R1FieldElement2.f1413x, create4);
            iArr4 = create;
            iArr3 = create4;
        }
        int[] create5 = Nat.create(17);
        SecP521R1Field.subtract(iArr4, iArr2, create5);
        SecP521R1Field.subtract(iArr3, iArr, create2);
        if (!Nat.isZero(17, create5)) {
            SecP521R1Field.square(create5, create3);
            int[] create6 = Nat.create(17);
            SecP521R1Field.multiply(create3, create5, create6);
            SecP521R1Field.multiply(create3, iArr4, create3);
            SecP521R1Field.multiply(iArr3, create6, create);
            SecP521R1FieldElement secP521R1FieldElement7 = new SecP521R1FieldElement(create4);
            SecP521R1Field.square(create2, secP521R1FieldElement7.f1413x);
            SecP521R1Field.add(secP521R1FieldElement7.f1413x, create6, secP521R1FieldElement7.f1413x);
            SecP521R1Field.subtract(secP521R1FieldElement7.f1413x, create3, secP521R1FieldElement7.f1413x);
            SecP521R1Field.subtract(secP521R1FieldElement7.f1413x, create3, secP521R1FieldElement7.f1413x);
            SecP521R1FieldElement secP521R1FieldElement8 = new SecP521R1FieldElement(create6);
            SecP521R1Field.subtract(create3, secP521R1FieldElement7.f1413x, secP521R1FieldElement8.f1413x);
            SecP521R1Field.multiply(secP521R1FieldElement8.f1413x, create2, create2);
            SecP521R1Field.subtract(create2, create, secP521R1FieldElement8.f1413x);
            SecP521R1FieldElement secP521R1FieldElement9 = new SecP521R1FieldElement(create5);
            if (!isOne) {
                SecP521R1Field.multiply(secP521R1FieldElement9.f1413x, secP521R1FieldElement5.f1413x, secP521R1FieldElement9.f1413x);
            }
            if (!isOne2) {
                SecP521R1Field.multiply(secP521R1FieldElement9.f1413x, secP521R1FieldElement6.f1413x, secP521R1FieldElement9.f1413x);
            }
            SecP521R1Point secP521R1Point = new SecP521R1Point(curve, secP521R1FieldElement7, secP521R1FieldElement8, new ECFieldElement[]{secP521R1FieldElement9}, this.withCompression);
            return secP521R1Point;
        } else if (Nat.isZero(17, create2)) {
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
        SecP521R1FieldElement secP521R1FieldElement = (SecP521R1FieldElement) this.f1362y;
        if (secP521R1FieldElement.isZero()) {
            return curve.getInfinity();
        }
        SecP521R1FieldElement secP521R1FieldElement2 = (SecP521R1FieldElement) this.f1361x;
        SecP521R1FieldElement secP521R1FieldElement3 = (SecP521R1FieldElement) this.f1363zs[0];
        int[] create = Nat.create(17);
        int[] create2 = Nat.create(17);
        int[] create3 = Nat.create(17);
        SecP521R1Field.square(secP521R1FieldElement.f1413x, create3);
        int[] create4 = Nat.create(17);
        SecP521R1Field.square(create3, create4);
        boolean isOne = secP521R1FieldElement3.isOne();
        int[] iArr = secP521R1FieldElement3.f1413x;
        if (!isOne) {
            SecP521R1Field.square(secP521R1FieldElement3.f1413x, create2);
            iArr = create2;
        }
        SecP521R1Field.subtract(secP521R1FieldElement2.f1413x, iArr, create);
        SecP521R1Field.add(secP521R1FieldElement2.f1413x, iArr, create2);
        SecP521R1Field.multiply(create2, create, create2);
        Nat.addBothTo(17, create2, create2, create2);
        SecP521R1Field.reduce23(create2);
        SecP521R1Field.multiply(create3, secP521R1FieldElement2.f1413x, create3);
        Nat.shiftUpBits(17, create3, 2, 0);
        SecP521R1Field.reduce23(create3);
        Nat.shiftUpBits(17, create4, 3, 0, create);
        SecP521R1Field.reduce23(create);
        SecP521R1FieldElement secP521R1FieldElement4 = new SecP521R1FieldElement(create4);
        SecP521R1Field.square(create2, secP521R1FieldElement4.f1413x);
        SecP521R1Field.subtract(secP521R1FieldElement4.f1413x, create3, secP521R1FieldElement4.f1413x);
        SecP521R1Field.subtract(secP521R1FieldElement4.f1413x, create3, secP521R1FieldElement4.f1413x);
        SecP521R1FieldElement secP521R1FieldElement5 = new SecP521R1FieldElement(create3);
        SecP521R1Field.subtract(create3, secP521R1FieldElement4.f1413x, secP521R1FieldElement5.f1413x);
        SecP521R1Field.multiply(secP521R1FieldElement5.f1413x, create2, secP521R1FieldElement5.f1413x);
        SecP521R1Field.subtract(secP521R1FieldElement5.f1413x, create, secP521R1FieldElement5.f1413x);
        SecP521R1FieldElement secP521R1FieldElement6 = new SecP521R1FieldElement(create2);
        SecP521R1Field.twice(secP521R1FieldElement.f1413x, secP521R1FieldElement6.f1413x);
        if (!isOne) {
            SecP521R1Field.multiply(secP521R1FieldElement6.f1413x, secP521R1FieldElement3.f1413x, secP521R1FieldElement6.f1413x);
        }
        SecP521R1Point secP521R1Point = new SecP521R1Point(curve, secP521R1FieldElement4, secP521R1FieldElement5, new ECFieldElement[]{secP521R1FieldElement6}, this.withCompression);
        return secP521R1Point;
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

    /* access modifiers changed from: protected */
    public ECFieldElement two(ECFieldElement eCFieldElement) {
        return eCFieldElement.add(eCFieldElement);
    }

    /* access modifiers changed from: protected */
    public ECFieldElement three(ECFieldElement eCFieldElement) {
        return two(eCFieldElement).add(eCFieldElement);
    }

    /* access modifiers changed from: protected */
    public ECFieldElement four(ECFieldElement eCFieldElement) {
        return two(two(eCFieldElement));
    }

    /* access modifiers changed from: protected */
    public ECFieldElement eight(ECFieldElement eCFieldElement) {
        return four(two(eCFieldElement));
    }

    /* access modifiers changed from: protected */
    public ECFieldElement doubleProductFromSquares(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3, ECFieldElement eCFieldElement4) {
        return eCFieldElement.add(eCFieldElement2).square().subtract(eCFieldElement3).subtract(eCFieldElement4);
    }

    public ECPoint negate() {
        if (isInfinity()) {
            return this;
        }
        SecP521R1Point secP521R1Point = new SecP521R1Point(this.curve, this.f1361x, this.f1362y.negate(), this.f1363zs, this.withCompression);
        return secP521R1Point;
    }
}