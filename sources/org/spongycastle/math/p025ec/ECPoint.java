package org.spongycastle.math.p025ec;

import androidx.core.view.InputDeviceCompat;
import java.math.BigInteger;
import java.util.Hashtable;

/* renamed from: org.spongycastle.math.ec.ECPoint */
public abstract class ECPoint {
    protected static ECFieldElement[] EMPTY_ZS = new ECFieldElement[0];
    protected ECCurve curve;
    protected Hashtable preCompTable;
    protected boolean withCompression;

    /* renamed from: x */
    protected ECFieldElement f1361x;

    /* renamed from: y */
    protected ECFieldElement f1362y;

    /* renamed from: zs */
    protected ECFieldElement[] f1363zs;

    /* renamed from: org.spongycastle.math.ec.ECPoint$AbstractF2m */
    public static abstract class AbstractF2m extends ECPoint {
        protected AbstractF2m(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
            super(eCCurve, eCFieldElement, eCFieldElement2);
        }

        protected AbstractF2m(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr) {
            super(eCCurve, eCFieldElement, eCFieldElement2, eCFieldElementArr);
        }

        /* access modifiers changed from: protected */
        public boolean satisfiesCurveEquation() {
            ECFieldElement eCFieldElement;
            ECFieldElement eCFieldElement2;
            ECCurve curve = getCurve();
            ECFieldElement eCFieldElement3 = this.f1361x;
            ECFieldElement a = curve.getA();
            ECFieldElement b = curve.getB();
            int coordinateSystem = curve.getCoordinateSystem();
            if (coordinateSystem == 6) {
                ECFieldElement eCFieldElement4 = this.f1363zs[0];
                boolean isOne = eCFieldElement4.isOne();
                if (eCFieldElement3.isZero()) {
                    ECFieldElement square = this.f1362y.square();
                    if (!isOne) {
                        b = b.multiply(eCFieldElement4.square());
                    }
                    return square.equals(b);
                }
                ECFieldElement eCFieldElement5 = this.f1362y;
                ECFieldElement square2 = eCFieldElement3.square();
                if (isOne) {
                    eCFieldElement2 = eCFieldElement5.square().add(eCFieldElement5).add(a);
                    eCFieldElement = square2.square().add(b);
                } else {
                    ECFieldElement square3 = eCFieldElement4.square();
                    ECFieldElement square4 = square3.square();
                    eCFieldElement2 = eCFieldElement5.add(eCFieldElement4).multiplyPlusProduct(eCFieldElement5, a, square3);
                    eCFieldElement = square2.squarePlusProduct(b, square4);
                }
                return eCFieldElement2.multiply(square2).equals(eCFieldElement);
            }
            ECFieldElement eCFieldElement6 = this.f1362y;
            ECFieldElement multiply = eCFieldElement6.add(eCFieldElement3).multiply(eCFieldElement6);
            if (coordinateSystem != 0) {
                if (coordinateSystem == 1) {
                    ECFieldElement eCFieldElement7 = this.f1363zs[0];
                    if (!eCFieldElement7.isOne()) {
                        ECFieldElement multiply2 = eCFieldElement7.multiply(eCFieldElement7.square());
                        multiply = multiply.multiply(eCFieldElement7);
                        a = a.multiply(eCFieldElement7);
                        b = b.multiply(multiply2);
                    }
                } else {
                    throw new IllegalStateException("unsupported coordinate system");
                }
            }
            return multiply.equals(eCFieldElement3.add(a).multiply(eCFieldElement3.square()).add(b));
        }
    }

    /* renamed from: org.spongycastle.math.ec.ECPoint$AbstractFp */
    public static abstract class AbstractFp extends ECPoint {
        protected AbstractFp(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
            super(eCCurve, eCFieldElement, eCFieldElement2);
        }

        protected AbstractFp(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr) {
            super(eCCurve, eCFieldElement, eCFieldElement2, eCFieldElementArr);
        }

        /* access modifiers changed from: protected */
        public boolean getCompressionYTilde() {
            return getAffineYCoord().testBitZero();
        }

        /* access modifiers changed from: protected */
        public boolean satisfiesCurveEquation() {
            ECFieldElement eCFieldElement = this.f1361x;
            ECFieldElement eCFieldElement2 = this.f1362y;
            ECFieldElement a = this.curve.getA();
            ECFieldElement b = this.curve.getB();
            ECFieldElement square = eCFieldElement2.square();
            int curveCoordinateSystem = getCurveCoordinateSystem();
            if (curveCoordinateSystem != 0) {
                if (curveCoordinateSystem == 1) {
                    ECFieldElement eCFieldElement3 = this.f1363zs[0];
                    if (!eCFieldElement3.isOne()) {
                        ECFieldElement square2 = eCFieldElement3.square();
                        ECFieldElement multiply = eCFieldElement3.multiply(square2);
                        square = square.multiply(eCFieldElement3);
                        a = a.multiply(square2);
                        b = b.multiply(multiply);
                    }
                } else if (curveCoordinateSystem == 2 || curveCoordinateSystem == 3 || curveCoordinateSystem == 4) {
                    ECFieldElement eCFieldElement4 = this.f1363zs[0];
                    if (!eCFieldElement4.isOne()) {
                        ECFieldElement square3 = eCFieldElement4.square();
                        ECFieldElement square4 = square3.square();
                        ECFieldElement multiply2 = square3.multiply(square4);
                        a = a.multiply(square4);
                        b = b.multiply(multiply2);
                    }
                } else {
                    throw new IllegalStateException("unsupported coordinate system");
                }
            }
            return square.equals(eCFieldElement.square().add(a).multiply(eCFieldElement).add(b));
        }

        public ECPoint subtract(ECPoint eCPoint) {
            if (eCPoint.isInfinity()) {
                return this;
            }
            return add(eCPoint.negate());
        }
    }

    /* renamed from: org.spongycastle.math.ec.ECPoint$F2m */
    public static class F2m extends AbstractF2m {
        public F2m(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
            this(eCCurve, eCFieldElement, eCFieldElement2, false);
        }

        public F2m(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, boolean z) {
            super(eCCurve, eCFieldElement, eCFieldElement2);
            boolean z2 = true;
            boolean z3 = eCFieldElement == null;
            if (eCFieldElement2 != null) {
                z2 = false;
            }
            if (z3 == z2) {
                if (eCFieldElement != null) {
                    org.spongycastle.math.p025ec.ECFieldElement.F2m.checkFieldElements(this.f1361x, this.f1362y);
                    if (eCCurve != null) {
                        org.spongycastle.math.p025ec.ECFieldElement.F2m.checkFieldElements(this.f1361x, this.curve.getA());
                    }
                }
                this.withCompression = z;
                return;
            }
            throw new IllegalArgumentException("Exactly one of the field elements is null");
        }

        F2m(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr, boolean z) {
            super(eCCurve, eCFieldElement, eCFieldElement2, eCFieldElementArr);
            this.withCompression = z;
        }

        /* access modifiers changed from: protected */
        public ECPoint detach() {
            return new F2m(null, getAffineXCoord(), getAffineYCoord());
        }

        public ECFieldElement getYCoord() {
            int curveCoordinateSystem = getCurveCoordinateSystem();
            if (curveCoordinateSystem != 5 && curveCoordinateSystem != 6) {
                return this.f1362y;
            }
            ECFieldElement eCFieldElement = this.f1361x;
            ECFieldElement eCFieldElement2 = this.f1362y;
            if (isInfinity() || eCFieldElement.isZero()) {
                return eCFieldElement2;
            }
            ECFieldElement multiply = eCFieldElement2.add(eCFieldElement).multiply(eCFieldElement);
            if (6 == curveCoordinateSystem) {
                ECFieldElement eCFieldElement3 = this.f1363zs[0];
                if (!eCFieldElement3.isOne()) {
                    multiply = multiply.divide(eCFieldElement3);
                }
            }
            return multiply;
        }

        public ECPoint scaleX(ECFieldElement eCFieldElement) {
            if (isInfinity()) {
                return this;
            }
            int curveCoordinateSystem = getCurveCoordinateSystem();
            if (curveCoordinateSystem == 5) {
                ECFieldElement rawXCoord = getRawXCoord();
                ECFieldElement rawYCoord = getRawYCoord();
                return getCurve().createRawPoint(rawXCoord, rawYCoord.add(rawXCoord).divide(eCFieldElement).add(rawXCoord.multiply(eCFieldElement)), getRawZCoords(), this.withCompression);
            } else if (curveCoordinateSystem != 6) {
                return super.scaleX(eCFieldElement);
            } else {
                ECFieldElement rawXCoord2 = getRawXCoord();
                ECFieldElement rawYCoord2 = getRawYCoord();
                ECFieldElement eCFieldElement2 = getRawZCoords()[0];
                ECFieldElement multiply = rawXCoord2.multiply(eCFieldElement.square());
                ECFieldElement add = rawYCoord2.add(rawXCoord2).add(multiply);
                ECFieldElement multiply2 = eCFieldElement2.multiply(eCFieldElement);
                return getCurve().createRawPoint(multiply, add, new ECFieldElement[]{multiply2}, this.withCompression);
            }
        }

        public ECPoint scaleY(ECFieldElement eCFieldElement) {
            if (isInfinity()) {
                return this;
            }
            int curveCoordinateSystem = getCurveCoordinateSystem();
            if (curveCoordinateSystem != 5 && curveCoordinateSystem != 6) {
                return super.scaleY(eCFieldElement);
            }
            ECFieldElement rawXCoord = getRawXCoord();
            return getCurve().createRawPoint(rawXCoord, getRawYCoord().add(rawXCoord).multiply(eCFieldElement).add(rawXCoord), getRawZCoords(), this.withCompression);
        }

        /* access modifiers changed from: protected */
        public boolean getCompressionYTilde() {
            ECFieldElement rawXCoord = getRawXCoord();
            boolean z = false;
            if (rawXCoord.isZero()) {
                return false;
            }
            ECFieldElement rawYCoord = getRawYCoord();
            int curveCoordinateSystem = getCurveCoordinateSystem();
            if (curveCoordinateSystem != 5 && curveCoordinateSystem != 6) {
                return rawYCoord.divide(rawXCoord).testBitZero();
            }
            if (rawYCoord.testBitZero() != rawXCoord.testBitZero()) {
                z = true;
            }
            return z;
        }

        private static void checkPoints(ECPoint eCPoint, ECPoint eCPoint2) {
            if (eCPoint.curve != eCPoint2.curve) {
                throw new IllegalArgumentException("Only points on the same curve can be added or subtracted");
            }
        }

        public ECPoint add(ECPoint eCPoint) {
            checkPoints(this, eCPoint);
            return addSimple((F2m) eCPoint);
        }

        public F2m addSimple(F2m f2m) {
            ECFieldElement eCFieldElement;
            ECFieldElement eCFieldElement2;
            ECFieldElement eCFieldElement3;
            ECFieldElement eCFieldElement4;
            ECFieldElement eCFieldElement5;
            ECFieldElement eCFieldElement6;
            ECFieldElement eCFieldElement7;
            ECFieldElement eCFieldElement8;
            F2m f2m2 = f2m;
            if (isInfinity()) {
                return f2m2;
            }
            if (f2m.isInfinity()) {
                return this;
            }
            ECCurve curve = getCurve();
            int coordinateSystem = curve.getCoordinateSystem();
            ECFieldElement eCFieldElement9 = this.f1361x;
            ECFieldElement eCFieldElement10 = f2m2.f1361x;
            if (coordinateSystem == 0) {
                ECFieldElement eCFieldElement11 = this.f1362y;
                ECFieldElement eCFieldElement12 = f2m2.f1362y;
                ECFieldElement add = eCFieldElement9.add(eCFieldElement10);
                ECFieldElement add2 = eCFieldElement11.add(eCFieldElement12);
                if (!add.isZero()) {
                    ECFieldElement divide = add2.divide(add);
                    ECFieldElement add3 = divide.square().add(divide).add(add).add(curve.getA());
                    return new F2m(curve, add3, divide.multiply(eCFieldElement9.add(add3)).add(add3).add(eCFieldElement11), this.withCompression);
                } else if (add2.isZero()) {
                    return (F2m) twice();
                } else {
                    return (F2m) curve.getInfinity();
                }
            } else if (coordinateSystem == 1) {
                ECFieldElement eCFieldElement13 = this.f1362y;
                ECFieldElement eCFieldElement14 = this.f1363zs[0];
                ECFieldElement eCFieldElement15 = f2m2.f1362y;
                ECFieldElement eCFieldElement16 = f2m2.f1363zs[0];
                boolean isOne = eCFieldElement16.isOne();
                ECFieldElement multiply = eCFieldElement14.multiply(eCFieldElement15);
                if (isOne) {
                    eCFieldElement = eCFieldElement13;
                } else {
                    eCFieldElement = eCFieldElement13.multiply(eCFieldElement16);
                }
                ECFieldElement add4 = multiply.add(eCFieldElement);
                ECFieldElement multiply2 = eCFieldElement14.multiply(eCFieldElement10);
                if (isOne) {
                    eCFieldElement2 = eCFieldElement9;
                } else {
                    eCFieldElement2 = eCFieldElement9.multiply(eCFieldElement16);
                }
                ECFieldElement add5 = multiply2.add(eCFieldElement2);
                if (!add5.isZero()) {
                    ECFieldElement square = add5.square();
                    ECFieldElement multiply3 = square.multiply(add5);
                    if (!isOne) {
                        eCFieldElement14 = eCFieldElement14.multiply(eCFieldElement16);
                    }
                    ECFieldElement add6 = add4.add(add5);
                    ECFieldElement add7 = add6.multiplyPlusProduct(add4, square, curve.getA()).multiply(eCFieldElement14).add(multiply3);
                    ECFieldElement multiply4 = add5.multiply(add7);
                    if (!isOne) {
                        square = square.multiply(eCFieldElement16);
                    }
                    F2m f2m3 = new F2m(curve, multiply4, add4.multiplyPlusProduct(eCFieldElement9, add5, eCFieldElement13).multiplyPlusProduct(square, add6, add7), new ECFieldElement[]{multiply3.multiply(eCFieldElement14)}, this.withCompression);
                    return f2m3;
                } else if (add4.isZero()) {
                    return (F2m) twice();
                } else {
                    return (F2m) curve.getInfinity();
                }
            } else if (coordinateSystem != 6) {
                throw new IllegalStateException("unsupported coordinate system");
            } else if (!eCFieldElement9.isZero()) {
                ECFieldElement eCFieldElement17 = this.f1362y;
                ECFieldElement eCFieldElement18 = this.f1363zs[0];
                ECFieldElement eCFieldElement19 = f2m2.f1362y;
                ECFieldElement eCFieldElement20 = f2m2.f1363zs[0];
                boolean isOne2 = eCFieldElement18.isOne();
                if (!isOne2) {
                    eCFieldElement4 = eCFieldElement10.multiply(eCFieldElement18);
                    eCFieldElement3 = eCFieldElement19.multiply(eCFieldElement18);
                } else {
                    eCFieldElement4 = eCFieldElement10;
                    eCFieldElement3 = eCFieldElement19;
                }
                boolean isOne3 = eCFieldElement20.isOne();
                if (!isOne3) {
                    eCFieldElement9 = eCFieldElement9.multiply(eCFieldElement20);
                    eCFieldElement5 = eCFieldElement17.multiply(eCFieldElement20);
                } else {
                    eCFieldElement5 = eCFieldElement17;
                }
                ECFieldElement add8 = eCFieldElement5.add(eCFieldElement3);
                ECFieldElement add9 = eCFieldElement9.add(eCFieldElement4);
                if (!add9.isZero()) {
                    if (eCFieldElement10.isZero()) {
                        ECPoint normalize = normalize();
                        ECFieldElement xCoord = normalize.getXCoord();
                        ECFieldElement yCoord = normalize.getYCoord();
                        ECFieldElement divide2 = yCoord.add(eCFieldElement19).divide(xCoord);
                        eCFieldElement6 = divide2.square().add(divide2).add(xCoord).add(curve.getA());
                        if (eCFieldElement6.isZero()) {
                            return new F2m(curve, eCFieldElement6, curve.getB().sqrt(), this.withCompression);
                        }
                        eCFieldElement7 = divide2.multiply(xCoord.add(eCFieldElement6)).add(eCFieldElement6).add(yCoord).divide(eCFieldElement6).add(eCFieldElement6);
                        eCFieldElement8 = curve.fromBigInteger(ECConstants.ONE);
                    } else {
                        ECFieldElement square2 = add9.square();
                        ECFieldElement multiply5 = add8.multiply(eCFieldElement9);
                        ECFieldElement multiply6 = add8.multiply(eCFieldElement4);
                        ECFieldElement multiply7 = multiply5.multiply(multiply6);
                        if (multiply7.isZero()) {
                            return new F2m(curve, multiply7, curve.getB().sqrt(), this.withCompression);
                        }
                        ECFieldElement multiply8 = add8.multiply(square2);
                        eCFieldElement8 = !isOne3 ? multiply8.multiply(eCFieldElement20) : multiply8;
                        eCFieldElement7 = multiply6.add(square2).squarePlusProduct(eCFieldElement8, eCFieldElement17.add(eCFieldElement18));
                        if (!isOne2) {
                            eCFieldElement8 = eCFieldElement8.multiply(eCFieldElement18);
                        }
                        eCFieldElement6 = multiply7;
                    }
                    F2m f2m4 = new F2m(curve, eCFieldElement6, eCFieldElement7, new ECFieldElement[]{eCFieldElement8}, this.withCompression);
                    return f2m4;
                } else if (add8.isZero()) {
                    return (F2m) twice();
                } else {
                    return (F2m) curve.getInfinity();
                }
            } else if (eCFieldElement10.isZero()) {
                return (F2m) curve.getInfinity();
            } else {
                return f2m2.addSimple(this);
            }
        }

        public ECPoint subtract(ECPoint eCPoint) {
            checkPoints(this, eCPoint);
            return subtractSimple((F2m) eCPoint);
        }

        public F2m subtractSimple(F2m f2m) {
            if (f2m.isInfinity()) {
                return this;
            }
            return addSimple((F2m) f2m.negate());
        }

        public F2m tau() {
            if (isInfinity()) {
                return this;
            }
            ECCurve curve = getCurve();
            int coordinateSystem = curve.getCoordinateSystem();
            ECFieldElement eCFieldElement = this.f1361x;
            if (coordinateSystem != 0) {
                if (coordinateSystem != 1) {
                    if (coordinateSystem != 5) {
                        if (coordinateSystem != 6) {
                            throw new IllegalStateException("unsupported coordinate system");
                        }
                    }
                }
                ECFieldElement eCFieldElement2 = this.f1362y;
                ECFieldElement eCFieldElement3 = this.f1363zs[0];
                F2m f2m = new F2m(curve, eCFieldElement.square(), eCFieldElement2.square(), new ECFieldElement[]{eCFieldElement3.square()}, this.withCompression);
                return f2m;
            }
            return new F2m(curve, eCFieldElement.square(), this.f1362y.square(), this.withCompression);
        }

        public ECPoint twice() {
            ECFieldElement eCFieldElement;
            ECFieldElement eCFieldElement2;
            ECFieldElement eCFieldElement3;
            ECFieldElement eCFieldElement4;
            ECFieldElement eCFieldElement5;
            ECFieldElement eCFieldElement6;
            ECFieldElement eCFieldElement7;
            if (isInfinity()) {
                return this;
            }
            ECCurve curve = getCurve();
            ECFieldElement eCFieldElement8 = this.f1361x;
            if (eCFieldElement8.isZero()) {
                return curve.getInfinity();
            }
            int coordinateSystem = curve.getCoordinateSystem();
            if (coordinateSystem == 0) {
                ECCurve eCCurve = curve;
                ECFieldElement add = this.f1362y.divide(eCFieldElement8).add(eCFieldElement8);
                ECFieldElement add2 = add.square().add(add).add(eCCurve.getA());
                return new F2m(eCCurve, add2, eCFieldElement8.squarePlusProduct(add2, add.addOne()), this.withCompression);
            } else if (coordinateSystem == 1) {
                ECCurve eCCurve2 = curve;
                ECFieldElement eCFieldElement9 = this.f1362y;
                ECFieldElement eCFieldElement10 = this.f1363zs[0];
                boolean isOne = eCFieldElement10.isOne();
                if (isOne) {
                    eCFieldElement = eCFieldElement8;
                } else {
                    eCFieldElement = eCFieldElement8.multiply(eCFieldElement10);
                }
                if (!isOne) {
                    eCFieldElement9 = eCFieldElement9.multiply(eCFieldElement10);
                }
                ECFieldElement square = eCFieldElement8.square();
                ECFieldElement add3 = square.add(eCFieldElement9);
                ECFieldElement square2 = eCFieldElement.square();
                ECFieldElement add4 = add3.add(eCFieldElement);
                ECFieldElement multiplyPlusProduct = add4.multiplyPlusProduct(add3, square2, eCCurve2.getA());
                F2m f2m = new F2m(eCCurve2, eCFieldElement.multiply(multiplyPlusProduct), square.square().multiplyPlusProduct(eCFieldElement, multiplyPlusProduct, add4), new ECFieldElement[]{eCFieldElement.multiply(square2)}, this.withCompression);
                return f2m;
            } else if (coordinateSystem == 6) {
                ECFieldElement eCFieldElement11 = this.f1362y;
                ECFieldElement eCFieldElement12 = this.f1363zs[0];
                boolean isOne2 = eCFieldElement12.isOne();
                if (isOne2) {
                    eCFieldElement2 = eCFieldElement11;
                } else {
                    eCFieldElement2 = eCFieldElement11.multiply(eCFieldElement12);
                }
                if (isOne2) {
                    eCFieldElement3 = eCFieldElement12;
                } else {
                    eCFieldElement3 = eCFieldElement12.square();
                }
                ECFieldElement a = curve.getA();
                if (isOne2) {
                    eCFieldElement4 = a;
                } else {
                    eCFieldElement4 = a.multiply(eCFieldElement3);
                }
                ECFieldElement add5 = eCFieldElement11.square().add(eCFieldElement2).add(eCFieldElement4);
                if (add5.isZero()) {
                    return new F2m(curve, add5, curve.getB().sqrt(), this.withCompression);
                }
                ECFieldElement square3 = add5.square();
                if (isOne2) {
                    eCFieldElement5 = add5;
                } else {
                    eCFieldElement5 = add5.multiply(eCFieldElement3);
                }
                ECFieldElement b = curve.getB();
                ECCurve eCCurve3 = curve;
                if (b.bitLength() < (curve.getFieldSize() >> 1)) {
                    ECFieldElement square4 = eCFieldElement11.add(eCFieldElement8).square();
                    if (b.isOne()) {
                        eCFieldElement7 = eCFieldElement4.add(eCFieldElement3).square();
                    } else {
                        eCFieldElement7 = eCFieldElement4.squarePlusProduct(b, eCFieldElement3.square());
                    }
                    eCFieldElement6 = square4.add(add5).add(eCFieldElement3).multiply(square4).add(eCFieldElement7).add(square3);
                    if (a.isZero()) {
                        eCFieldElement6 = eCFieldElement6.add(eCFieldElement5);
                    } else if (!a.isOne()) {
                        eCFieldElement6 = eCFieldElement6.add(a.addOne().multiply(eCFieldElement5));
                    }
                } else {
                    if (!isOne2) {
                        eCFieldElement8 = eCFieldElement8.multiply(eCFieldElement12);
                    }
                    eCFieldElement6 = eCFieldElement8.squarePlusProduct(add5, eCFieldElement2).add(square3).add(eCFieldElement5);
                }
                ECCurve eCCurve4 = eCCurve3;
                ECFieldElement eCFieldElement13 = square3;
                ECFieldElement eCFieldElement14 = eCFieldElement6;
                F2m f2m2 = new F2m(eCCurve4, eCFieldElement13, eCFieldElement14, new ECFieldElement[]{eCFieldElement5}, this.withCompression);
                return f2m2;
            } else {
                throw new IllegalStateException("unsupported coordinate system");
            }
        }

        public ECPoint twicePlus(ECPoint eCPoint) {
            if (isInfinity()) {
                return eCPoint;
            }
            if (eCPoint.isInfinity()) {
                return twice();
            }
            ECCurve curve = getCurve();
            ECFieldElement eCFieldElement = this.f1361x;
            if (eCFieldElement.isZero()) {
                return eCPoint;
            }
            if (curve.getCoordinateSystem() != 6) {
                return twice().add(eCPoint);
            }
            ECFieldElement eCFieldElement2 = eCPoint.f1361x;
            ECFieldElement eCFieldElement3 = eCPoint.f1363zs[0];
            if (eCFieldElement2.isZero() || !eCFieldElement3.isOne()) {
                return twice().add(eCPoint);
            }
            ECFieldElement eCFieldElement4 = this.f1362y;
            ECFieldElement eCFieldElement5 = this.f1363zs[0];
            ECFieldElement eCFieldElement6 = eCPoint.f1362y;
            ECFieldElement square = eCFieldElement.square();
            ECFieldElement square2 = eCFieldElement4.square();
            ECFieldElement square3 = eCFieldElement5.square();
            ECFieldElement add = curve.getA().multiply(square3).add(square2).add(eCFieldElement4.multiply(eCFieldElement5));
            ECFieldElement addOne = eCFieldElement6.addOne();
            ECFieldElement multiplyPlusProduct = curve.getA().add(addOne).multiply(square3).add(square2).multiplyPlusProduct(add, square, square3);
            ECFieldElement multiply = eCFieldElement2.multiply(square3);
            ECFieldElement square4 = multiply.add(add).square();
            if (square4.isZero()) {
                if (multiplyPlusProduct.isZero()) {
                    return eCPoint.twice();
                }
                return curve.getInfinity();
            } else if (multiplyPlusProduct.isZero()) {
                return new F2m(curve, multiplyPlusProduct, curve.getB().sqrt(), this.withCompression);
            } else {
                ECFieldElement multiply2 = multiplyPlusProduct.square().multiply(multiply);
                ECFieldElement multiply3 = multiplyPlusProduct.multiply(square4).multiply(square3);
                F2m f2m = new F2m(curve, multiply2, multiplyPlusProduct.add(square4).square().multiplyPlusProduct(add, addOne, multiply3), new ECFieldElement[]{multiply3}, this.withCompression);
                return f2m;
            }
        }

        public ECPoint negate() {
            if (isInfinity()) {
                return this;
            }
            ECFieldElement eCFieldElement = this.f1361x;
            if (eCFieldElement.isZero()) {
                return this;
            }
            int curveCoordinateSystem = getCurveCoordinateSystem();
            if (curveCoordinateSystem == 0) {
                return new F2m(this.curve, eCFieldElement, this.f1362y.add(eCFieldElement), this.withCompression);
            } else if (curveCoordinateSystem == 1) {
                ECFieldElement eCFieldElement2 = this.f1362y;
                ECFieldElement eCFieldElement3 = this.f1363zs[0];
                F2m f2m = new F2m(this.curve, eCFieldElement, eCFieldElement2.add(eCFieldElement), new ECFieldElement[]{eCFieldElement3}, this.withCompression);
                return f2m;
            } else if (curveCoordinateSystem == 5) {
                return new F2m(this.curve, eCFieldElement, this.f1362y.addOne(), this.withCompression);
            } else if (curveCoordinateSystem == 6) {
                ECFieldElement eCFieldElement4 = this.f1362y;
                ECFieldElement eCFieldElement5 = this.f1363zs[0];
                F2m f2m2 = new F2m(this.curve, eCFieldElement, eCFieldElement4.add(eCFieldElement5), new ECFieldElement[]{eCFieldElement5}, this.withCompression);
                return f2m2;
            } else {
                throw new IllegalStateException("unsupported coordinate system");
            }
        }
    }

    /* renamed from: org.spongycastle.math.ec.ECPoint$Fp */
    public static class C3641Fp extends AbstractFp {
        public C3641Fp(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
            this(eCCurve, eCFieldElement, eCFieldElement2, false);
        }

        public C3641Fp(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, boolean z) {
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

        C3641Fp(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr, boolean z) {
            super(eCCurve, eCFieldElement, eCFieldElement2, eCFieldElementArr);
            this.withCompression = z;
        }

        /* access modifiers changed from: protected */
        public ECPoint detach() {
            return new C3641Fp(null, getAffineXCoord(), getAffineYCoord());
        }

        public ECFieldElement getZCoord(int i) {
            if (i == 1 && 4 == getCurveCoordinateSystem()) {
                return getJacobianModifiedW();
            }
            return super.getZCoord(i);
        }

        public ECPoint add(ECPoint eCPoint) {
            ECFieldElement eCFieldElement;
            ECFieldElement eCFieldElement2;
            ECFieldElement eCFieldElement3;
            ECFieldElement eCFieldElement4;
            ECFieldElement eCFieldElement5;
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
            int coordinateSystem = curve.getCoordinateSystem();
            ECFieldElement eCFieldElement6 = this.f1361x;
            ECFieldElement eCFieldElement7 = this.f1362y;
            ECFieldElement eCFieldElement8 = eCPoint2.f1361x;
            ECFieldElement eCFieldElement9 = eCPoint2.f1362y;
            if (coordinateSystem == 0) {
                ECFieldElement subtract = eCFieldElement8.subtract(eCFieldElement6);
                ECFieldElement subtract2 = eCFieldElement9.subtract(eCFieldElement7);
                if (!subtract.isZero()) {
                    ECFieldElement divide = subtract2.divide(subtract);
                    ECFieldElement subtract3 = divide.square().subtract(eCFieldElement6).subtract(eCFieldElement8);
                    return new C3641Fp(curve, subtract3, divide.multiply(eCFieldElement6.subtract(subtract3)).subtract(eCFieldElement7), this.withCompression);
                } else if (subtract2.isZero()) {
                    return twice();
                } else {
                    return curve.getInfinity();
                }
            } else if (coordinateSystem == 1) {
                ECFieldElement eCFieldElement10 = this.f1363zs[0];
                ECFieldElement eCFieldElement11 = eCPoint2.f1363zs[0];
                boolean isOne = eCFieldElement10.isOne();
                boolean isOne2 = eCFieldElement11.isOne();
                if (!isOne) {
                    eCFieldElement9 = eCFieldElement9.multiply(eCFieldElement10);
                }
                if (!isOne2) {
                    eCFieldElement7 = eCFieldElement7.multiply(eCFieldElement11);
                }
                ECFieldElement subtract4 = eCFieldElement9.subtract(eCFieldElement7);
                if (!isOne) {
                    eCFieldElement8 = eCFieldElement8.multiply(eCFieldElement10);
                }
                if (!isOne2) {
                    eCFieldElement6 = eCFieldElement6.multiply(eCFieldElement11);
                }
                ECFieldElement subtract5 = eCFieldElement8.subtract(eCFieldElement6);
                if (!subtract5.isZero()) {
                    if (isOne) {
                        eCFieldElement10 = eCFieldElement11;
                    } else if (!isOne2) {
                        eCFieldElement10 = eCFieldElement10.multiply(eCFieldElement11);
                    }
                    ECFieldElement square = subtract5.square();
                    ECFieldElement multiply = square.multiply(subtract5);
                    ECFieldElement multiply2 = square.multiply(eCFieldElement6);
                    ECFieldElement subtract6 = subtract4.square().multiply(eCFieldElement10).subtract(multiply).subtract(two(multiply2));
                    C3641Fp fp = new C3641Fp(curve, subtract5.multiply(subtract6), multiply2.subtract(subtract6).multiplyMinusProduct(subtract4, eCFieldElement7, multiply), new ECFieldElement[]{multiply.multiply(eCFieldElement10)}, this.withCompression);
                    return fp;
                } else if (subtract4.isZero()) {
                    return twice();
                } else {
                    return curve.getInfinity();
                }
            } else if (coordinateSystem == 2 || coordinateSystem == 4) {
                ECFieldElement eCFieldElement12 = this.f1363zs[0];
                ECFieldElement eCFieldElement13 = eCPoint2.f1363zs[0];
                boolean isOne3 = eCFieldElement12.isOne();
                if (isOne3 || !eCFieldElement12.equals(eCFieldElement13)) {
                    if (!isOne3) {
                        ECFieldElement square2 = eCFieldElement12.square();
                        eCFieldElement8 = square2.multiply(eCFieldElement8);
                        eCFieldElement9 = square2.multiply(eCFieldElement12).multiply(eCFieldElement9);
                    }
                    boolean isOne4 = eCFieldElement13.isOne();
                    if (!isOne4) {
                        ECFieldElement square3 = eCFieldElement13.square();
                        eCFieldElement6 = square3.multiply(eCFieldElement6);
                        eCFieldElement7 = square3.multiply(eCFieldElement13).multiply(eCFieldElement7);
                    }
                    ECFieldElement subtract7 = eCFieldElement6.subtract(eCFieldElement8);
                    ECFieldElement subtract8 = eCFieldElement7.subtract(eCFieldElement9);
                    if (!subtract7.isZero()) {
                        ECFieldElement square4 = subtract7.square();
                        ECFieldElement multiply3 = square4.multiply(subtract7);
                        ECFieldElement multiply4 = square4.multiply(eCFieldElement6);
                        ECFieldElement subtract9 = subtract8.square().add(multiply3).subtract(two(multiply4));
                        ECFieldElement multiplyMinusProduct = multiply4.subtract(subtract9).multiplyMinusProduct(subtract8, multiply3, eCFieldElement7);
                        ECFieldElement multiply5 = !isOne3 ? subtract7.multiply(eCFieldElement12) : subtract7;
                        if (!isOne4) {
                            eCFieldElement5 = multiply5.multiply(eCFieldElement13);
                        } else {
                            eCFieldElement5 = multiply5;
                        }
                        if (eCFieldElement4 == subtract7) {
                            eCFieldElement2 = multiplyMinusProduct;
                            eCFieldElement3 = subtract9;
                            eCFieldElement = square4;
                        } else {
                            eCFieldElement2 = multiplyMinusProduct;
                            eCFieldElement3 = subtract9;
                            eCFieldElement = null;
                        }
                    } else if (subtract8.isZero()) {
                        return twice();
                    } else {
                        return curve.getInfinity();
                    }
                } else {
                    eCFieldElement4 = eCFieldElement6.subtract(eCFieldElement8);
                    ECFieldElement subtract10 = eCFieldElement7.subtract(eCFieldElement9);
                    if (!eCFieldElement4.isZero()) {
                        ECFieldElement square5 = eCFieldElement4.square();
                        ECFieldElement multiply6 = eCFieldElement6.multiply(square5);
                        ECFieldElement multiply7 = eCFieldElement8.multiply(square5);
                        ECFieldElement multiply8 = multiply6.subtract(multiply7).multiply(eCFieldElement7);
                        ECFieldElement subtract11 = subtract10.square().subtract(multiply6).subtract(multiply7);
                        ECFieldElement subtract12 = multiply6.subtract(subtract11).multiply(subtract10).subtract(multiply8);
                        if (isOne3) {
                            eCFieldElement = square5;
                        } else {
                            eCFieldElement4 = eCFieldElement4.multiply(eCFieldElement12);
                            eCFieldElement = null;
                        }
                        eCFieldElement2 = subtract12;
                        eCFieldElement3 = subtract11;
                    } else if (subtract10.isZero()) {
                        return twice();
                    } else {
                        return curve.getInfinity();
                    }
                }
                C3641Fp fp2 = new C3641Fp(curve, eCFieldElement3, eCFieldElement2, coordinateSystem == 4 ? new ECFieldElement[]{eCFieldElement4, calculateJacobianModifiedW(eCFieldElement4, eCFieldElement)} : new ECFieldElement[]{eCFieldElement4}, this.withCompression);
                return fp2;
            } else {
                throw new IllegalStateException("unsupported coordinate system");
            }
        }

        public ECPoint twice() {
            ECFieldElement eCFieldElement;
            ECFieldElement eCFieldElement2;
            ECFieldElement eCFieldElement3;
            ECFieldElement eCFieldElement4;
            ECFieldElement eCFieldElement5;
            if (isInfinity()) {
                return this;
            }
            ECCurve curve = getCurve();
            ECFieldElement eCFieldElement6 = this.f1362y;
            if (eCFieldElement6.isZero()) {
                return curve.getInfinity();
            }
            int coordinateSystem = curve.getCoordinateSystem();
            ECFieldElement eCFieldElement7 = this.f1361x;
            if (coordinateSystem == 0) {
                ECFieldElement divide = three(eCFieldElement7.square()).add(getCurve().getA()).divide(two(eCFieldElement6));
                ECFieldElement subtract = divide.square().subtract(two(eCFieldElement7));
                return new C3641Fp(curve, subtract, divide.multiply(eCFieldElement7.subtract(subtract)).subtract(eCFieldElement6), this.withCompression);
            } else if (coordinateSystem == 1) {
                ECFieldElement eCFieldElement8 = this.f1363zs[0];
                boolean isOne = eCFieldElement8.isOne();
                ECFieldElement a = curve.getA();
                if (!a.isZero() && !isOne) {
                    a = a.multiply(eCFieldElement8.square());
                }
                ECFieldElement add = a.add(three(eCFieldElement7.square()));
                if (isOne) {
                    eCFieldElement = eCFieldElement6;
                } else {
                    eCFieldElement = eCFieldElement6.multiply(eCFieldElement8);
                }
                ECFieldElement square = isOne ? eCFieldElement6.square() : eCFieldElement.multiply(eCFieldElement6);
                ECFieldElement four = four(eCFieldElement7.multiply(square));
                ECFieldElement subtract2 = add.square().subtract(two(four));
                ECFieldElement two = two(eCFieldElement);
                ECFieldElement multiply = subtract2.multiply(two);
                ECFieldElement two2 = two(square);
                C3641Fp fp = new C3641Fp(curve, multiply, four.subtract(subtract2).multiply(add).subtract(two(two2.square())), new ECFieldElement[]{two(isOne ? two(two2) : two.square()).multiply(eCFieldElement)}, this.withCompression);
                return fp;
            } else if (coordinateSystem == 2) {
                ECFieldElement eCFieldElement9 = this.f1363zs[0];
                boolean isOne2 = eCFieldElement9.isOne();
                ECFieldElement square2 = eCFieldElement6.square();
                ECFieldElement square3 = square2.square();
                ECFieldElement a2 = curve.getA();
                ECFieldElement negate = a2.negate();
                if (negate.toBigInteger().equals(BigInteger.valueOf(3))) {
                    if (isOne2) {
                        eCFieldElement5 = eCFieldElement9;
                    } else {
                        eCFieldElement5 = eCFieldElement9.square();
                    }
                    eCFieldElement2 = three(eCFieldElement7.add(eCFieldElement5).multiply(eCFieldElement7.subtract(eCFieldElement5)));
                    eCFieldElement3 = four(square2.multiply(eCFieldElement7));
                } else {
                    ECFieldElement three = three(eCFieldElement7.square());
                    if (isOne2) {
                        eCFieldElement2 = three.add(a2);
                    } else if (!a2.isZero()) {
                        if (isOne2) {
                            eCFieldElement4 = eCFieldElement9;
                        } else {
                            eCFieldElement4 = eCFieldElement9.square();
                        }
                        ECFieldElement square4 = eCFieldElement4.square();
                        eCFieldElement2 = negate.bitLength() < a2.bitLength() ? three.subtract(square4.multiply(negate)) : three.add(square4.multiply(a2));
                    } else {
                        eCFieldElement2 = three;
                    }
                    eCFieldElement3 = four(eCFieldElement7.multiply(square2));
                }
                ECFieldElement subtract3 = eCFieldElement2.square().subtract(two(eCFieldElement3));
                ECFieldElement subtract4 = eCFieldElement3.subtract(subtract3).multiply(eCFieldElement2).subtract(eight(square3));
                ECFieldElement two3 = two(eCFieldElement6);
                if (!isOne2) {
                    two3 = two3.multiply(eCFieldElement9);
                }
                C3641Fp fp2 = new C3641Fp(curve, subtract3, subtract4, new ECFieldElement[]{two3}, this.withCompression);
                return fp2;
            } else if (coordinateSystem == 4) {
                return twiceJacobianModified(true);
            } else {
                throw new IllegalStateException("unsupported coordinate system");
            }
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
            ECFieldElement eCFieldElement = this.f1362y;
            if (eCFieldElement.isZero()) {
                return eCPoint;
            }
            ECCurve curve = getCurve();
            int coordinateSystem = curve.getCoordinateSystem();
            if (coordinateSystem == 0) {
                ECFieldElement eCFieldElement2 = this.f1361x;
                ECFieldElement eCFieldElement3 = eCPoint.f1361x;
                ECFieldElement eCFieldElement4 = eCPoint.f1362y;
                ECFieldElement subtract = eCFieldElement3.subtract(eCFieldElement2);
                ECFieldElement subtract2 = eCFieldElement4.subtract(eCFieldElement);
                if (!subtract.isZero()) {
                    ECFieldElement square = subtract.square();
                    ECFieldElement subtract3 = square.multiply(two(eCFieldElement2).add(eCFieldElement3)).subtract(subtract2.square());
                    if (subtract3.isZero()) {
                        return curve.getInfinity();
                    }
                    ECFieldElement invert = subtract3.multiply(subtract).invert();
                    ECFieldElement multiply = subtract3.multiply(invert).multiply(subtract2);
                    ECFieldElement subtract4 = two(eCFieldElement).multiply(square).multiply(subtract).multiply(invert).subtract(multiply);
                    ECFieldElement add = subtract4.subtract(multiply).multiply(multiply.add(subtract4)).add(eCFieldElement3);
                    return new C3641Fp(curve, add, eCFieldElement2.subtract(add).multiply(subtract4).subtract(eCFieldElement), this.withCompression);
                } else if (subtract2.isZero()) {
                    return threeTimes();
                } else {
                    return this;
                }
            } else if (coordinateSystem != 4) {
                return twice().add(eCPoint);
            } else {
                return twiceJacobianModified(false).add(eCPoint);
            }
        }

        public ECPoint threeTimes() {
            if (isInfinity()) {
                return this;
            }
            ECFieldElement eCFieldElement = this.f1362y;
            if (eCFieldElement.isZero()) {
                return this;
            }
            ECCurve curve = getCurve();
            int coordinateSystem = curve.getCoordinateSystem();
            if (coordinateSystem == 0) {
                ECFieldElement eCFieldElement2 = this.f1361x;
                ECFieldElement two = two(eCFieldElement);
                ECFieldElement square = two.square();
                ECFieldElement add = three(eCFieldElement2.square()).add(getCurve().getA());
                ECFieldElement subtract = three(eCFieldElement2).multiply(square).subtract(add.square());
                if (subtract.isZero()) {
                    return getCurve().getInfinity();
                }
                ECFieldElement invert = subtract.multiply(two).invert();
                ECFieldElement multiply = subtract.multiply(invert).multiply(add);
                ECFieldElement subtract2 = square.square().multiply(invert).subtract(multiply);
                ECFieldElement add2 = subtract2.subtract(multiply).multiply(multiply.add(subtract2)).add(eCFieldElement2);
                return new C3641Fp(curve, add2, eCFieldElement2.subtract(add2).multiply(subtract2).subtract(eCFieldElement), this.withCompression);
            } else if (coordinateSystem != 4) {
                return twice().add(this);
            } else {
                return twiceJacobianModified(false).add(this);
            }
        }

        public ECPoint timesPow2(int i) {
            int i2 = i;
            if (i2 < 0) {
                throw new IllegalArgumentException("'e' cannot be negative");
            } else if (i2 == 0 || isInfinity()) {
                return this;
            } else {
                if (i2 == 1) {
                    return twice();
                }
                ECCurve curve = getCurve();
                ECFieldElement eCFieldElement = this.f1362y;
                if (eCFieldElement.isZero()) {
                    return curve.getInfinity();
                }
                int coordinateSystem = curve.getCoordinateSystem();
                ECFieldElement a = curve.getA();
                ECFieldElement eCFieldElement2 = this.f1361x;
                ECFieldElement fromBigInteger = this.f1363zs.length < 1 ? curve.fromBigInteger(ECConstants.ONE) : this.f1363zs[0];
                if (!fromBigInteger.isOne()) {
                    if (coordinateSystem == 1) {
                        ECFieldElement square = fromBigInteger.square();
                        eCFieldElement2 = eCFieldElement2.multiply(fromBigInteger);
                        eCFieldElement = eCFieldElement.multiply(square);
                        a = calculateJacobianModifiedW(fromBigInteger, square);
                    } else if (coordinateSystem == 2) {
                        a = calculateJacobianModifiedW(fromBigInteger, null);
                    } else if (coordinateSystem == 4) {
                        a = getJacobianModifiedW();
                    }
                }
                ECFieldElement eCFieldElement3 = a;
                ECFieldElement eCFieldElement4 = eCFieldElement;
                int i3 = 0;
                while (i3 < i2) {
                    if (eCFieldElement4.isZero()) {
                        return curve.getInfinity();
                    }
                    ECFieldElement three = three(eCFieldElement2.square());
                    ECFieldElement two = two(eCFieldElement4);
                    ECFieldElement multiply = two.multiply(eCFieldElement4);
                    ECFieldElement two2 = two(eCFieldElement2.multiply(multiply));
                    ECFieldElement two3 = two(multiply.square());
                    if (!eCFieldElement3.isZero()) {
                        three = three.add(eCFieldElement3);
                        eCFieldElement3 = two(two3.multiply(eCFieldElement3));
                    }
                    ECFieldElement subtract = three.square().subtract(two(two2));
                    eCFieldElement4 = three.multiply(two2.subtract(subtract)).subtract(two3);
                    fromBigInteger = fromBigInteger.isOne() ? two : two.multiply(fromBigInteger);
                    i3++;
                    eCFieldElement2 = subtract;
                }
                if (coordinateSystem == 0) {
                    ECFieldElement invert = fromBigInteger.invert();
                    ECFieldElement square2 = invert.square();
                    return new C3641Fp(curve, eCFieldElement2.multiply(square2), eCFieldElement4.multiply(square2.multiply(invert)), this.withCompression);
                } else if (coordinateSystem == 1) {
                    C3641Fp fp = new C3641Fp(curve, eCFieldElement2.multiply(fromBigInteger), eCFieldElement4, new ECFieldElement[]{fromBigInteger.multiply(fromBigInteger.square())}, this.withCompression);
                    return fp;
                } else if (coordinateSystem == 2) {
                    C3641Fp fp2 = new C3641Fp(curve, eCFieldElement2, eCFieldElement4, new ECFieldElement[]{fromBigInteger}, this.withCompression);
                    return fp2;
                } else if (coordinateSystem == 4) {
                    C3641Fp fp3 = new C3641Fp(curve, eCFieldElement2, eCFieldElement4, new ECFieldElement[]{fromBigInteger, eCFieldElement3}, this.withCompression);
                    return fp3;
                } else {
                    throw new IllegalStateException("unsupported coordinate system");
                }
            }
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
            ECCurve curve = getCurve();
            if (curve.getCoordinateSystem() == 0) {
                return new C3641Fp(curve, this.f1361x, this.f1362y.negate(), this.withCompression);
            }
            C3641Fp fp = new C3641Fp(curve, this.f1361x, this.f1362y.negate(), this.f1363zs, this.withCompression);
            return fp;
        }

        /* access modifiers changed from: protected */
        public ECFieldElement calculateJacobianModifiedW(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
            ECFieldElement eCFieldElement3;
            ECFieldElement a = getCurve().getA();
            if (a.isZero() || eCFieldElement.isOne()) {
                return a;
            }
            if (eCFieldElement2 == null) {
                eCFieldElement2 = eCFieldElement.square();
            }
            ECFieldElement square = eCFieldElement2.square();
            ECFieldElement negate = a.negate();
            if (negate.bitLength() < a.bitLength()) {
                eCFieldElement3 = square.multiply(negate).negate();
            } else {
                eCFieldElement3 = square.multiply(a);
            }
            return eCFieldElement3;
        }

        /* access modifiers changed from: protected */
        public ECFieldElement getJacobianModifiedW() {
            ECFieldElement eCFieldElement = this.f1363zs[1];
            if (eCFieldElement != null) {
                return eCFieldElement;
            }
            ECFieldElement[] eCFieldElementArr = this.f1363zs;
            ECFieldElement calculateJacobianModifiedW = calculateJacobianModifiedW(this.f1363zs[0], null);
            eCFieldElementArr[1] = calculateJacobianModifiedW;
            return calculateJacobianModifiedW;
        }

        /* access modifiers changed from: protected */
        public C3641Fp twiceJacobianModified(boolean z) {
            ECFieldElement eCFieldElement = this.f1361x;
            ECFieldElement eCFieldElement2 = this.f1362y;
            ECFieldElement eCFieldElement3 = this.f1363zs[0];
            ECFieldElement jacobianModifiedW = getJacobianModifiedW();
            ECFieldElement add = three(eCFieldElement.square()).add(jacobianModifiedW);
            ECFieldElement two = two(eCFieldElement2);
            ECFieldElement multiply = two.multiply(eCFieldElement2);
            ECFieldElement two2 = two(eCFieldElement.multiply(multiply));
            ECFieldElement subtract = add.square().subtract(two(two2));
            ECFieldElement two3 = two(multiply.square());
            ECFieldElement subtract2 = add.multiply(two2.subtract(subtract)).subtract(two3);
            ECFieldElement two4 = z ? two(two3.multiply(jacobianModifiedW)) : null;
            if (!eCFieldElement3.isOne()) {
                two = two.multiply(eCFieldElement3);
            }
            C3641Fp fp = new C3641Fp(getCurve(), subtract, subtract2, new ECFieldElement[]{two, two4}, this.withCompression);
            return fp;
        }
    }

    public abstract ECPoint add(ECPoint eCPoint);

    /* access modifiers changed from: protected */
    public abstract ECPoint detach();

    /* access modifiers changed from: protected */
    public abstract boolean getCompressionYTilde();

    public abstract ECPoint negate();

    /* access modifiers changed from: protected */
    public abstract boolean satisfiesCurveEquation();

    public abstract ECPoint subtract(ECPoint eCPoint);

    public abstract ECPoint twice();

    protected static ECFieldElement[] getInitialZCoords(ECCurve eCCurve) {
        int coordinateSystem = eCCurve == null ? 0 : eCCurve.getCoordinateSystem();
        if (coordinateSystem == 0 || coordinateSystem == 5) {
            return EMPTY_ZS;
        }
        ECFieldElement fromBigInteger = eCCurve.fromBigInteger(ECConstants.ONE);
        if (!(coordinateSystem == 1 || coordinateSystem == 2)) {
            if (coordinateSystem == 3) {
                return new ECFieldElement[]{fromBigInteger, fromBigInteger, fromBigInteger};
            } else if (coordinateSystem == 4) {
                return new ECFieldElement[]{fromBigInteger, eCCurve.getA()};
            } else if (coordinateSystem != 6) {
                throw new IllegalArgumentException("unknown coordinate system");
            }
        }
        return new ECFieldElement[]{fromBigInteger};
    }

    protected ECPoint(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        this(eCCurve, eCFieldElement, eCFieldElement2, getInitialZCoords(eCCurve));
    }

    protected ECPoint(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr) {
        this.preCompTable = null;
        this.curve = eCCurve;
        this.f1361x = eCFieldElement;
        this.f1362y = eCFieldElement2;
        this.f1363zs = eCFieldElementArr;
    }

    /* access modifiers changed from: protected */
    public boolean satisfiesCofactor() {
        BigInteger cofactor = this.curve.getCofactor();
        return cofactor == null || cofactor.equals(ECConstants.ONE) || !ECAlgorithms.referenceMultiply(this, cofactor).isInfinity();
    }

    public final ECPoint getDetachedPoint() {
        return normalize().detach();
    }

    public ECCurve getCurve() {
        return this.curve;
    }

    /* access modifiers changed from: protected */
    public int getCurveCoordinateSystem() {
        ECCurve eCCurve = this.curve;
        if (eCCurve == null) {
            return 0;
        }
        return eCCurve.getCoordinateSystem();
    }

    public ECFieldElement getX() {
        return normalize().getXCoord();
    }

    public ECFieldElement getY() {
        return normalize().getYCoord();
    }

    public ECFieldElement getAffineXCoord() {
        checkNormalized();
        return getXCoord();
    }

    public ECFieldElement getAffineYCoord() {
        checkNormalized();
        return getYCoord();
    }

    public ECFieldElement getXCoord() {
        return this.f1361x;
    }

    public ECFieldElement getYCoord() {
        return this.f1362y;
    }

    public ECFieldElement getZCoord(int i) {
        if (i >= 0) {
            ECFieldElement[] eCFieldElementArr = this.f1363zs;
            if (i < eCFieldElementArr.length) {
                return eCFieldElementArr[i];
            }
        }
        return null;
    }

    public ECFieldElement[] getZCoords() {
        ECFieldElement[] eCFieldElementArr = this.f1363zs;
        int length = eCFieldElementArr.length;
        if (length == 0) {
            return eCFieldElementArr;
        }
        ECFieldElement[] eCFieldElementArr2 = new ECFieldElement[length];
        System.arraycopy(eCFieldElementArr, 0, eCFieldElementArr2, 0, length);
        return eCFieldElementArr2;
    }

    /* access modifiers changed from: protected */
    public final ECFieldElement getRawXCoord() {
        return this.f1361x;
    }

    /* access modifiers changed from: protected */
    public final ECFieldElement getRawYCoord() {
        return this.f1362y;
    }

    /* access modifiers changed from: protected */
    public final ECFieldElement[] getRawZCoords() {
        return this.f1363zs;
    }

    /* access modifiers changed from: protected */
    public void checkNormalized() {
        if (!isNormalized()) {
            throw new IllegalStateException("point not in normal form");
        }
    }

    public boolean isNormalized() {
        int curveCoordinateSystem = getCurveCoordinateSystem();
        if (curveCoordinateSystem == 0 || curveCoordinateSystem == 5 || isInfinity() || this.f1363zs[0].isOne()) {
            return true;
        }
        return false;
    }

    public ECPoint normalize() {
        if (isInfinity()) {
            return this;
        }
        int curveCoordinateSystem = getCurveCoordinateSystem();
        if (curveCoordinateSystem == 0 || curveCoordinateSystem == 5) {
            return this;
        }
        ECFieldElement zCoord = getZCoord(0);
        if (zCoord.isOne()) {
            return this;
        }
        return normalize(zCoord.invert());
    }

    /* access modifiers changed from: 0000 */
    public ECPoint normalize(ECFieldElement eCFieldElement) {
        int curveCoordinateSystem = getCurveCoordinateSystem();
        if (curveCoordinateSystem != 1) {
            if (curveCoordinateSystem == 2 || curveCoordinateSystem == 3 || curveCoordinateSystem == 4) {
                ECFieldElement square = eCFieldElement.square();
                return createScaledPoint(square, square.multiply(eCFieldElement));
            } else if (curveCoordinateSystem != 6) {
                throw new IllegalStateException("not a projective coordinate system");
            }
        }
        return createScaledPoint(eCFieldElement, eCFieldElement);
    }

    /* access modifiers changed from: protected */
    public ECPoint createScaledPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        return getCurve().createRawPoint(getRawXCoord().multiply(eCFieldElement), getRawYCoord().multiply(eCFieldElement2), this.withCompression);
    }

    public boolean isInfinity() {
        if (!(this.f1361x == null || this.f1362y == null)) {
            ECFieldElement[] eCFieldElementArr = this.f1363zs;
            if (eCFieldElementArr.length <= 0 || !eCFieldElementArr[0].isZero()) {
                return false;
            }
        }
        return true;
    }

    public boolean isCompressed() {
        return this.withCompression;
    }

    public boolean isValid() {
        if (!isInfinity() && getCurve() != null && (!satisfiesCurveEquation() || !satisfiesCofactor())) {
            return false;
        }
        return true;
    }

    public ECPoint scaleX(ECFieldElement eCFieldElement) {
        return isInfinity() ? this : getCurve().createRawPoint(getRawXCoord().multiply(eCFieldElement), getRawYCoord(), getRawZCoords(), this.withCompression);
    }

    public ECPoint scaleY(ECFieldElement eCFieldElement) {
        return isInfinity() ? this : getCurve().createRawPoint(getRawXCoord(), getRawYCoord().multiply(eCFieldElement), getRawZCoords(), this.withCompression);
    }

    public boolean equals(ECPoint eCPoint) {
        ECPoint eCPoint2;
        ECPoint eCPoint3;
        boolean z = false;
        if (eCPoint == null) {
            return false;
        }
        ECCurve curve2 = getCurve();
        ECCurve curve3 = eCPoint.getCurve();
        boolean z2 = curve2 == null;
        boolean z3 = curve3 == null;
        boolean isInfinity = isInfinity();
        boolean isInfinity2 = eCPoint.isInfinity();
        if (isInfinity || isInfinity2) {
            if (isInfinity && isInfinity2 && (z2 || z3 || curve2.equals(curve3))) {
                z = true;
            }
            return z;
        }
        if (!z2 || !z3) {
            if (z2) {
                eCPoint = eCPoint.normalize();
            } else {
                if (z3) {
                    eCPoint3 = eCPoint;
                    eCPoint2 = normalize();
                } else if (!curve2.equals(curve3)) {
                    return false;
                } else {
                    ECPoint[] eCPointArr = {this, curve2.importPoint(eCPoint)};
                    curve2.normalizeAll(eCPointArr);
                    eCPoint2 = eCPointArr[0];
                    eCPoint3 = eCPointArr[1];
                }
                if (eCPoint2.getXCoord().equals(eCPoint3.getXCoord()) && eCPoint2.getYCoord().equals(eCPoint3.getYCoord())) {
                    z = true;
                }
                return z;
            }
        }
        eCPoint3 = eCPoint;
        eCPoint2 = this;
        z = true;
        return z;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ECPoint)) {
            return false;
        }
        return equals((ECPoint) obj);
    }

    public int hashCode() {
        int i;
        ECCurve curve2 = getCurve();
        if (curve2 == null) {
            i = 0;
        } else {
            i = ~curve2.hashCode();
        }
        if (isInfinity()) {
            return i;
        }
        ECPoint normalize = normalize();
        return (i ^ (normalize.getXCoord().hashCode() * 17)) ^ (normalize.getYCoord().hashCode() * InputDeviceCompat.SOURCE_KEYBOARD);
    }

    public String toString() {
        if (isInfinity()) {
            return "INF";
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append('(');
        stringBuffer.append(getRawXCoord());
        stringBuffer.append(',');
        stringBuffer.append(getRawYCoord());
        for (ECFieldElement append : this.f1363zs) {
            stringBuffer.append(',');
            stringBuffer.append(append);
        }
        stringBuffer.append(')');
        return stringBuffer.toString();
    }

    public byte[] getEncoded() {
        return getEncoded(this.withCompression);
    }

    public byte[] getEncoded(boolean z) {
        if (isInfinity()) {
            return new byte[1];
        }
        ECPoint normalize = normalize();
        byte[] encoded = normalize.getXCoord().getEncoded();
        if (z) {
            byte[] bArr = new byte[(encoded.length + 1)];
            bArr[0] = (byte) (normalize.getCompressionYTilde() ? 3 : 2);
            System.arraycopy(encoded, 0, bArr, 1, encoded.length);
            return bArr;
        }
        byte[] encoded2 = normalize.getYCoord().getEncoded();
        byte[] bArr2 = new byte[(encoded.length + encoded2.length + 1)];
        bArr2[0] = 4;
        System.arraycopy(encoded, 0, bArr2, 1, encoded.length);
        System.arraycopy(encoded2, 0, bArr2, encoded.length + 1, encoded2.length);
        return bArr2;
    }

    public ECPoint timesPow2(int i) {
        if (i >= 0) {
            ECPoint eCPoint = this;
            while (true) {
                i--;
                if (i < 0) {
                    return eCPoint;
                }
                eCPoint = eCPoint.twice();
            }
        } else {
            throw new IllegalArgumentException("'e' cannot be negative");
        }
    }

    public ECPoint twicePlus(ECPoint eCPoint) {
        return twice().add(eCPoint);
    }

    public ECPoint threeTimes() {
        return twicePlus(this);
    }

    public ECPoint multiply(BigInteger bigInteger) {
        return getCurve().getMultiplier().multiply(this, bigInteger);
    }
}
