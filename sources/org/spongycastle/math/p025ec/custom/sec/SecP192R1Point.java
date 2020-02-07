package org.spongycastle.math.p025ec.custom.sec;

import org.spongycastle.math.p025ec.ECCurve;
import org.spongycastle.math.p025ec.ECFieldElement;
import org.spongycastle.math.p025ec.ECPoint;
import org.spongycastle.math.p025ec.ECPoint.AbstractFp;
import org.spongycastle.math.raw.Nat;
import org.spongycastle.math.raw.Nat192;

/* renamed from: org.spongycastle.math.ec.custom.sec.SecP192R1Point */
public class SecP192R1Point extends AbstractFp {
    public SecP192R1Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        this(eCCurve, eCFieldElement, eCFieldElement2, false);
    }

    public SecP192R1Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, boolean z) {
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

    SecP192R1Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr, boolean z) {
        super(eCCurve, eCFieldElement, eCFieldElement2, eCFieldElementArr);
        this.withCompression = z;
    }

    /* access modifiers changed from: protected */
    public ECPoint detach() {
        return new SecP192R1Point(null, getAffineXCoord(), getAffineYCoord());
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
        SecP192R1FieldElement secP192R1FieldElement = (SecP192R1FieldElement) this.f1361x;
        SecP192R1FieldElement secP192R1FieldElement2 = (SecP192R1FieldElement) this.f1362y;
        SecP192R1FieldElement secP192R1FieldElement3 = (SecP192R1FieldElement) eCPoint.getXCoord();
        SecP192R1FieldElement secP192R1FieldElement4 = (SecP192R1FieldElement) eCPoint.getYCoord();
        SecP192R1FieldElement secP192R1FieldElement5 = (SecP192R1FieldElement) this.f1363zs[0];
        SecP192R1FieldElement secP192R1FieldElement6 = (SecP192R1FieldElement) eCPoint.getZCoord(0);
        int[] createExt = Nat192.createExt();
        int[] create = Nat192.create();
        int[] create2 = Nat192.create();
        int[] create3 = Nat192.create();
        boolean isOne = secP192R1FieldElement5.isOne();
        if (isOne) {
            iArr2 = secP192R1FieldElement3.f1382x;
            iArr = secP192R1FieldElement4.f1382x;
        } else {
            SecP192R1Field.square(secP192R1FieldElement5.f1382x, create2);
            SecP192R1Field.multiply(create2, secP192R1FieldElement3.f1382x, create);
            SecP192R1Field.multiply(create2, secP192R1FieldElement5.f1382x, create2);
            SecP192R1Field.multiply(create2, secP192R1FieldElement4.f1382x, create2);
            iArr2 = create;
            iArr = create2;
        }
        boolean isOne2 = secP192R1FieldElement6.isOne();
        if (isOne2) {
            iArr4 = secP192R1FieldElement.f1382x;
            iArr3 = secP192R1FieldElement2.f1382x;
        } else {
            SecP192R1Field.square(secP192R1FieldElement6.f1382x, create3);
            SecP192R1Field.multiply(create3, secP192R1FieldElement.f1382x, createExt);
            SecP192R1Field.multiply(create3, secP192R1FieldElement6.f1382x, create3);
            SecP192R1Field.multiply(create3, secP192R1FieldElement2.f1382x, create3);
            iArr4 = createExt;
            iArr3 = create3;
        }
        int[] create4 = Nat192.create();
        SecP192R1Field.subtract(iArr4, iArr2, create4);
        SecP192R1Field.subtract(iArr3, iArr, create);
        if (!Nat192.isZero(create4)) {
            SecP192R1Field.square(create4, create2);
            int[] create5 = Nat192.create();
            SecP192R1Field.multiply(create2, create4, create5);
            SecP192R1Field.multiply(create2, iArr4, create2);
            SecP192R1Field.negate(create5, create5);
            Nat192.mul(iArr3, create5, createExt);
            SecP192R1Field.reduce32(Nat192.addBothTo(create2, create2, create5), create5);
            SecP192R1FieldElement secP192R1FieldElement7 = new SecP192R1FieldElement(create3);
            SecP192R1Field.square(create, secP192R1FieldElement7.f1382x);
            SecP192R1Field.subtract(secP192R1FieldElement7.f1382x, create5, secP192R1FieldElement7.f1382x);
            SecP192R1FieldElement secP192R1FieldElement8 = new SecP192R1FieldElement(create5);
            SecP192R1Field.subtract(create2, secP192R1FieldElement7.f1382x, secP192R1FieldElement8.f1382x);
            SecP192R1Field.multiplyAddToExt(secP192R1FieldElement8.f1382x, create, createExt);
            SecP192R1Field.reduce(createExt, secP192R1FieldElement8.f1382x);
            SecP192R1FieldElement secP192R1FieldElement9 = new SecP192R1FieldElement(create4);
            if (!isOne) {
                SecP192R1Field.multiply(secP192R1FieldElement9.f1382x, secP192R1FieldElement5.f1382x, secP192R1FieldElement9.f1382x);
            }
            if (!isOne2) {
                SecP192R1Field.multiply(secP192R1FieldElement9.f1382x, secP192R1FieldElement6.f1382x, secP192R1FieldElement9.f1382x);
            }
            SecP192R1Point secP192R1Point = new SecP192R1Point(curve, secP192R1FieldElement7, secP192R1FieldElement8, new ECFieldElement[]{secP192R1FieldElement9}, this.withCompression);
            return secP192R1Point;
        } else if (Nat192.isZero(create)) {
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
        SecP192R1FieldElement secP192R1FieldElement = (SecP192R1FieldElement) this.f1362y;
        if (secP192R1FieldElement.isZero()) {
            return curve.getInfinity();
        }
        SecP192R1FieldElement secP192R1FieldElement2 = (SecP192R1FieldElement) this.f1361x;
        SecP192R1FieldElement secP192R1FieldElement3 = (SecP192R1FieldElement) this.f1363zs[0];
        int[] create = Nat192.create();
        int[] create2 = Nat192.create();
        int[] create3 = Nat192.create();
        SecP192R1Field.square(secP192R1FieldElement.f1382x, create3);
        int[] create4 = Nat192.create();
        SecP192R1Field.square(create3, create4);
        boolean isOne = secP192R1FieldElement3.isOne();
        int[] iArr = secP192R1FieldElement3.f1382x;
        if (!isOne) {
            SecP192R1Field.square(secP192R1FieldElement3.f1382x, create2);
            iArr = create2;
        }
        SecP192R1Field.subtract(secP192R1FieldElement2.f1382x, iArr, create);
        SecP192R1Field.add(secP192R1FieldElement2.f1382x, iArr, create2);
        SecP192R1Field.multiply(create2, create, create2);
        SecP192R1Field.reduce32(Nat192.addBothTo(create2, create2, create2), create2);
        SecP192R1Field.multiply(create3, secP192R1FieldElement2.f1382x, create3);
        SecP192R1Field.reduce32(Nat.shiftUpBits(6, create3, 2, 0), create3);
        SecP192R1Field.reduce32(Nat.shiftUpBits(6, create4, 3, 0, create), create);
        SecP192R1FieldElement secP192R1FieldElement4 = new SecP192R1FieldElement(create4);
        SecP192R1Field.square(create2, secP192R1FieldElement4.f1382x);
        SecP192R1Field.subtract(secP192R1FieldElement4.f1382x, create3, secP192R1FieldElement4.f1382x);
        SecP192R1Field.subtract(secP192R1FieldElement4.f1382x, create3, secP192R1FieldElement4.f1382x);
        SecP192R1FieldElement secP192R1FieldElement5 = new SecP192R1FieldElement(create3);
        SecP192R1Field.subtract(create3, secP192R1FieldElement4.f1382x, secP192R1FieldElement5.f1382x);
        SecP192R1Field.multiply(secP192R1FieldElement5.f1382x, create2, secP192R1FieldElement5.f1382x);
        SecP192R1Field.subtract(secP192R1FieldElement5.f1382x, create, secP192R1FieldElement5.f1382x);
        SecP192R1FieldElement secP192R1FieldElement6 = new SecP192R1FieldElement(create2);
        SecP192R1Field.twice(secP192R1FieldElement.f1382x, secP192R1FieldElement6.f1382x);
        if (!isOne) {
            SecP192R1Field.multiply(secP192R1FieldElement6.f1382x, secP192R1FieldElement3.f1382x, secP192R1FieldElement6.f1382x);
        }
        SecP192R1FieldElement secP192R1FieldElement7 = secP192R1FieldElement4;
        SecP192R1FieldElement secP192R1FieldElement8 = secP192R1FieldElement5;
        SecP192R1Point secP192R1Point = new SecP192R1Point(curve, secP192R1FieldElement7, secP192R1FieldElement8, new ECFieldElement[]{secP192R1FieldElement6}, this.withCompression);
        return secP192R1Point;
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
        SecP192R1Point secP192R1Point = new SecP192R1Point(this.curve, this.f1361x, this.f1362y.negate(), this.f1363zs, this.withCompression);
        return secP192R1Point;
    }
}
