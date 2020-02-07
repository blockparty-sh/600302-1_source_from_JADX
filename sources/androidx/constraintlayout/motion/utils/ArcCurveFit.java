package androidx.constraintlayout.motion.utils;

import java.util.Arrays;

class ArcCurveFit extends CurveFit {
    public static final int ARC_START_FLIP = 3;
    public static final int ARC_START_HORIZONTAL = 2;
    public static final int ARC_START_LINEAR = 0;
    public static final int ARC_START_VERTICAL = 1;
    private static final int START_HORIZONTAL = 2;
    private static final int START_LINEAR = 3;
    private static final int START_VERTICAL = 1;
    Arc[] mArcs;
    private final double[] mTime;

    private static class Arc {
        private static final double EPSILON = 0.001d;
        private static final String TAG = "Arc";
        private static double[] ourPercent = new double[91];
        boolean linear = false;
        double mArcDistance;
        double mArcVelocity;
        double mEllipseA;
        double mEllipseB;
        double mEllipseCenterX;
        double mEllipseCenterY;
        double[] mLut;
        double mOneOverDeltaTime;
        double mTime1;
        double mTime2;
        double mTmpCosAngle;
        double mTmpSinAngle;
        boolean mVertical;
        double mX1;
        double mX2;
        double mY1;
        double mY2;

        Arc(int i, double d, double d2, double d3, double d4, double d5, double d6) {
            int i2 = i;
            double d7 = d3;
            double d8 = d4;
            double d9 = d5;
            double d10 = d6;
            boolean z = false;
            if (i2 == 1) {
                z = true;
            }
            this.mVertical = z;
            this.mTime1 = d;
            this.mTime2 = d2;
            this.mOneOverDeltaTime = 1.0d / (this.mTime2 - this.mTime1);
            if (3 == i2) {
                this.linear = true;
            }
            double d11 = d9 - d7;
            double d12 = d4;
            double d13 = d10 - d12;
            if (this.linear || Math.abs(d11) < EPSILON || Math.abs(d13) < EPSILON) {
                this.linear = true;
                this.mX1 = d7;
                this.mX2 = d9;
                this.mY1 = d12;
                this.mY2 = d10;
                this.mArcDistance = Math.hypot(d13, d11);
                this.mArcVelocity = this.mArcDistance * this.mOneOverDeltaTime;
                double d14 = this.mTime2;
                double d15 = this.mTime1;
                this.mEllipseCenterX = d11 / (d14 - d15);
                this.mEllipseCenterY = d13 / (d14 - d15);
                return;
            }
            this.mLut = new double[101];
            this.mEllipseA = d11 * ((double) (this.mVertical ? -1 : 1));
            this.mEllipseB = d13 * ((double) (this.mVertical ? 1 : -1));
            this.mEllipseCenterX = this.mVertical ? d9 : d7;
            this.mEllipseCenterY = this.mVertical ? d12 : d10;
            buildTable(d3, d4, d5, d6);
            this.mArcVelocity = this.mArcDistance * this.mOneOverDeltaTime;
        }

        /* access modifiers changed from: 0000 */
        public void setPoint(double d) {
            double lookup = lookup((this.mVertical ? this.mTime2 - d : d - this.mTime1) * this.mOneOverDeltaTime) * 1.5707963267948966d;
            this.mTmpSinAngle = Math.sin(lookup);
            this.mTmpCosAngle = Math.cos(lookup);
        }

        /* access modifiers changed from: 0000 */
        public double getX() {
            return this.mEllipseCenterX + (this.mEllipseA * this.mTmpSinAngle);
        }

        /* access modifiers changed from: 0000 */
        public double getY() {
            return this.mEllipseCenterY + (this.mEllipseB * this.mTmpCosAngle);
        }

        /* access modifiers changed from: 0000 */
        public double getDX() {
            double d = this.mEllipseA * this.mTmpCosAngle;
            double hypot = this.mArcVelocity / Math.hypot(d, (-this.mEllipseB) * this.mTmpSinAngle);
            if (this.mVertical) {
                d = -d;
            }
            return d * hypot;
        }

        /* access modifiers changed from: 0000 */
        public double getDY() {
            double d = (-this.mEllipseB) * this.mTmpSinAngle;
            double hypot = this.mArcVelocity / Math.hypot(this.mEllipseA * this.mTmpCosAngle, d);
            return this.mVertical ? (-d) * hypot : d * hypot;
        }

        public double getLinearX(double d) {
            double d2 = (d - this.mTime1) * this.mOneOverDeltaTime;
            double d3 = this.mX1;
            return d3 + (d2 * (this.mX2 - d3));
        }

        public double getLinearY(double d) {
            double d2 = (d - this.mTime1) * this.mOneOverDeltaTime;
            double d3 = this.mY1;
            return d3 + (d2 * (this.mY2 - d3));
        }

        public double getLinearDX(double d) {
            return this.mEllipseCenterX;
        }

        public double getLinearDY(double d) {
            return this.mEllipseCenterY;
        }

        /* access modifiers changed from: 0000 */
        public double lookup(double d) {
            if (d <= 0.0d) {
                return 0.0d;
            }
            if (d >= 1.0d) {
                return 1.0d;
            }
            double[] dArr = this.mLut;
            double length = d * ((double) (dArr.length - 1));
            int i = (int) length;
            return dArr[i] + ((length - ((double) i)) * (dArr[i + 1] - dArr[i]));
        }

        private void buildTable(double d, double d2, double d3, double d4) {
            double d5;
            double d6 = d3 - d;
            double d7 = d2 - d4;
            int i = 0;
            double d8 = 0.0d;
            double d9 = 0.0d;
            double d10 = 0.0d;
            while (true) {
                double[] dArr = ourPercent;
                if (i >= dArr.length) {
                    break;
                }
                double d11 = d8;
                double radians = Math.toRadians((((double) i) * 90.0d) / ((double) (dArr.length - 1)));
                double sin = Math.sin(radians) * d6;
                double cos = Math.cos(radians) * d7;
                if (i > 0) {
                    d5 = d11 + Math.hypot(sin - d9, cos - d10);
                    ourPercent[i] = d5;
                } else {
                    d5 = d11;
                }
                i++;
                d10 = cos;
                double d12 = sin;
                d8 = d5;
                d9 = d12;
            }
            double d13 = d8;
            this.mArcDistance = d13;
            int i2 = 0;
            while (true) {
                double[] dArr2 = ourPercent;
                if (i2 >= dArr2.length) {
                    break;
                }
                dArr2[i2] = dArr2[i2] / d13;
                i2++;
            }
            int i3 = 0;
            while (true) {
                double[] dArr3 = this.mLut;
                if (i3 < dArr3.length) {
                    double length = ((double) i3) / ((double) (dArr3.length - 1));
                    int binarySearch = Arrays.binarySearch(ourPercent, length);
                    if (binarySearch >= 0) {
                        this.mLut[i3] = (double) (binarySearch / (ourPercent.length - 1));
                    } else if (binarySearch == -1) {
                        this.mLut[i3] = 0.0d;
                    } else {
                        int i4 = -binarySearch;
                        int i5 = i4 - 2;
                        int i6 = i4 - 1;
                        double d14 = (double) i5;
                        double[] dArr4 = ourPercent;
                        this.mLut[i3] = (d14 + ((length - dArr4[i5]) / (dArr4[i6] - dArr4[i5]))) / ((double) (dArr4.length - 1));
                    }
                    i3++;
                } else {
                    return;
                }
            }
        }
    }

    public void getPos(double d, double[] dArr) {
        if (d < this.mArcs[0].mTime1) {
            d = this.mArcs[0].mTime1;
        }
        Arc[] arcArr = this.mArcs;
        if (d > arcArr[arcArr.length - 1].mTime2) {
            Arc[] arcArr2 = this.mArcs;
            d = arcArr2[arcArr2.length - 1].mTime2;
        }
        int i = 0;
        while (true) {
            Arc[] arcArr3 = this.mArcs;
            if (i >= arcArr3.length) {
                return;
            }
            if (d > arcArr3[i].mTime2) {
                i++;
            } else if (this.mArcs[i].linear) {
                dArr[0] = this.mArcs[i].getLinearX(d);
                dArr[1] = this.mArcs[i].getLinearY(d);
                return;
            } else {
                this.mArcs[i].setPoint(d);
                dArr[0] = this.mArcs[i].getX();
                dArr[1] = this.mArcs[i].getY();
                return;
            }
        }
    }

    public void getPos(double d, float[] fArr) {
        if (d < this.mArcs[0].mTime1) {
            d = this.mArcs[0].mTime1;
        } else {
            Arc[] arcArr = this.mArcs;
            if (d > arcArr[arcArr.length - 1].mTime2) {
                Arc[] arcArr2 = this.mArcs;
                d = arcArr2[arcArr2.length - 1].mTime2;
            }
        }
        int i = 0;
        while (true) {
            Arc[] arcArr3 = this.mArcs;
            if (i >= arcArr3.length) {
                return;
            }
            if (d > arcArr3[i].mTime2) {
                i++;
            } else if (this.mArcs[i].linear) {
                fArr[0] = (float) this.mArcs[i].getLinearX(d);
                fArr[1] = (float) this.mArcs[i].getLinearY(d);
                return;
            } else {
                this.mArcs[i].setPoint(d);
                fArr[0] = (float) this.mArcs[i].getX();
                fArr[1] = (float) this.mArcs[i].getY();
                return;
            }
        }
    }

    public void getSlope(double d, double[] dArr) {
        if (d < this.mArcs[0].mTime1) {
            d = this.mArcs[0].mTime1;
        } else {
            Arc[] arcArr = this.mArcs;
            if (d > arcArr[arcArr.length - 1].mTime2) {
                Arc[] arcArr2 = this.mArcs;
                d = arcArr2[arcArr2.length - 1].mTime2;
            }
        }
        int i = 0;
        while (true) {
            Arc[] arcArr3 = this.mArcs;
            if (i >= arcArr3.length) {
                return;
            }
            if (d > arcArr3[i].mTime2) {
                i++;
            } else if (this.mArcs[i].linear) {
                dArr[0] = this.mArcs[i].getLinearDX(d);
                dArr[1] = this.mArcs[i].getLinearDY(d);
                return;
            } else {
                this.mArcs[i].setPoint(d);
                dArr[0] = this.mArcs[i].getDX();
                dArr[1] = this.mArcs[i].getDY();
                return;
            }
        }
    }

    public double getPos(double d, int i) {
        int i2 = 0;
        if (d < this.mArcs[0].mTime1) {
            d = this.mArcs[0].mTime1;
        } else {
            Arc[] arcArr = this.mArcs;
            if (d > arcArr[arcArr.length - 1].mTime2) {
                Arc[] arcArr2 = this.mArcs;
                d = arcArr2[arcArr2.length - 1].mTime2;
            }
        }
        while (true) {
            Arc[] arcArr3 = this.mArcs;
            if (i2 >= arcArr3.length) {
                return Double.NaN;
            }
            if (d > arcArr3[i2].mTime2) {
                i2++;
            } else if (!this.mArcs[i2].linear) {
                this.mArcs[i2].setPoint(d);
                if (i == 0) {
                    return this.mArcs[i2].getX();
                }
                return this.mArcs[i2].getY();
            } else if (i == 0) {
                return this.mArcs[i2].getLinearX(d);
            } else {
                return this.mArcs[i2].getLinearY(d);
            }
        }
    }

    public double getSlope(double d, int i) {
        int i2 = 0;
        if (d < this.mArcs[0].mTime1) {
            d = this.mArcs[0].mTime1;
        }
        Arc[] arcArr = this.mArcs;
        if (d > arcArr[arcArr.length - 1].mTime2) {
            Arc[] arcArr2 = this.mArcs;
            d = arcArr2[arcArr2.length - 1].mTime2;
        }
        while (true) {
            Arc[] arcArr3 = this.mArcs;
            if (i2 >= arcArr3.length) {
                return Double.NaN;
            }
            if (d > arcArr3[i2].mTime2) {
                i2++;
            } else if (!this.mArcs[i2].linear) {
                this.mArcs[i2].setPoint(d);
                if (i == 0) {
                    return this.mArcs[i2].getDX();
                }
                return this.mArcs[i2].getDY();
            } else if (i == 0) {
                return this.mArcs[i2].getLinearDX(d);
            } else {
                return this.mArcs[i2].getLinearDY(d);
            }
        }
    }

    public double[] getTimePoints() {
        return this.mTime;
    }

    public ArcCurveFit(int[] iArr, double[] dArr, double[][] dArr2) {
        double[] dArr3 = dArr;
        this.mTime = dArr3;
        this.mArcs = new Arc[(dArr3.length - 1)];
        int i = 0;
        int i2 = 1;
        int i3 = 1;
        while (i < this.mArcs.length) {
            int i4 = iArr[i];
            int i5 = 2;
            if (i4 == 0) {
                i5 = 3;
            } else if (i4 == 1) {
                i2 = 1;
                i5 = 1;
            } else if (i4 == 2) {
                i2 = 2;
            } else if (i4 != 3) {
                i5 = i3;
            } else {
                if (i2 != 1) {
                    i5 = 1;
                }
                i2 = i5;
            }
            Arc[] arcArr = this.mArcs;
            int i6 = i + 1;
            Arc arc = new Arc(i5, dArr3[i], dArr3[i6], dArr2[i][0], dArr2[i][1], dArr2[i6][0], dArr2[i6][1]);
            arcArr[i] = arc;
            i = i6;
            i3 = i5;
        }
    }
}
