package androidx.constraintlayout.motion.widget;

import android.view.View;
import android.view.View.MeasureSpec;
import androidx.annotation.NonNull;
import androidx.constraintlayout.motion.utils.Easing;
import androidx.constraintlayout.widget.ConstraintAttribute;
import androidx.constraintlayout.widget.ConstraintAttribute.AttributeType;
import androidx.constraintlayout.widget.ConstraintSet.Constraint;
import java.util.Arrays;
import java.util.LinkedHashMap;

class MotionPaths implements Comparable<MotionPaths> {
    static final int CARTESIAN = 2;
    public static final boolean DEBUG = false;
    static final int OFF_HEIGHT = 4;
    static final int OFF_PATH_ROTATE = 5;
    static final int OFF_POSITION = 0;
    static final int OFF_WIDTH = 3;
    static final int OFF_X = 1;
    static final int OFF_Y = 2;
    public static final boolean OLD_WAY = false;
    static final int PERPENDICULAR = 1;
    static final int SCREEN = 3;
    public static final String TAG = "MotionPaths";
    static String[] names = {"position", "x", "y", "width", "height", "pathRotate"};
    LinkedHashMap<String, ConstraintAttribute> attributes = new LinkedHashMap<>();
    float height;
    int mDrawPath = 0;
    Easing mKeyFrameEasing;
    int mMode = 0;
    int mPathMotionArc = C0188Key.UNSET;
    float mPathRotate = Float.NaN;
    float mProgress = Float.NaN;
    double[] mTempDelta = new double[18];
    double[] mTempValue = new double[18];
    float position;
    float time;
    float width;

    /* renamed from: x */
    float f30x;

    /* renamed from: y */
    float f31y;

    private static final float xRotate(float f, float f2, float f3, float f4, float f5, float f6) {
        return (((f5 - f3) * f2) - ((f6 - f4) * f)) + f3;
    }

    private static final float yRotate(float f, float f2, float f3, float f4, float f5, float f6) {
        return ((f5 - f3) * f) + ((f6 - f4) * f2) + f4;
    }

    public MotionPaths() {
    }

    /* access modifiers changed from: 0000 */
    public void initCartesian(KeyPosition keyPosition, MotionPaths motionPaths, MotionPaths motionPaths2) {
        KeyPosition keyPosition2 = keyPosition;
        MotionPaths motionPaths3 = motionPaths;
        MotionPaths motionPaths4 = motionPaths2;
        float f = ((float) keyPosition2.mFramePosition) / 100.0f;
        this.time = f;
        this.mDrawPath = keyPosition2.mDrawPath;
        float f2 = Float.isNaN(keyPosition2.mPercentWidth) ? f : keyPosition2.mPercentWidth;
        float f3 = Float.isNaN(keyPosition2.mPercentHeight) ? f : keyPosition2.mPercentHeight;
        float f4 = motionPaths4.width;
        float f5 = motionPaths3.width;
        float f6 = f4 - f5;
        float f7 = motionPaths4.height;
        float f8 = motionPaths3.height;
        float f9 = f7 - f8;
        this.position = this.time;
        float f10 = motionPaths3.f30x;
        float f11 = (f5 / 2.0f) + f10;
        float f12 = motionPaths3.f31y;
        float f13 = (motionPaths4.f30x + (f4 / 2.0f)) - f11;
        float f14 = (motionPaths4.f31y + (f7 / 2.0f)) - (f12 + (f8 / 2.0f));
        float f15 = f6 * f2;
        float f16 = f15 / 2.0f;
        this.f30x = (float) ((int) ((f10 + (f13 * f)) - f16));
        float f17 = f9 * f3;
        float f18 = f17 / 2.0f;
        this.f31y = (float) ((int) ((f12 + (f14 * f)) - f18));
        this.width = (float) ((int) (f5 + f15));
        this.height = (float) ((int) (f8 + f17));
        KeyPosition keyPosition3 = keyPosition;
        float f19 = Float.isNaN(keyPosition3.mPercentX) ? f : keyPosition3.mPercentX;
        float f20 = 0.0f;
        float f21 = Float.isNaN(keyPosition3.mAltPercentY) ? 0.0f : keyPosition3.mAltPercentY;
        if (!Float.isNaN(keyPosition3.mPercentY)) {
            f = keyPosition3.mPercentY;
        }
        if (!Float.isNaN(keyPosition3.mAltPercentX)) {
            f20 = keyPosition3.mAltPercentX;
        }
        this.mMode = 2;
        MotionPaths motionPaths5 = motionPaths;
        this.f30x = (float) ((int) (((motionPaths5.f30x + (f19 * f13)) + (f20 * f14)) - f16));
        this.f31y = (float) ((int) (((motionPaths5.f31y + (f13 * f21)) + (f14 * f)) - f18));
        this.mKeyFrameEasing = Easing.getInterpolator(keyPosition3.mTransitionEasing);
        this.mPathMotionArc = keyPosition3.mPathMotionArc;
    }

    public MotionPaths(int i, int i2, KeyPosition keyPosition, MotionPaths motionPaths, MotionPaths motionPaths2) {
        int i3 = keyPosition.mPositionType;
        if (i3 == 1) {
            initPath(keyPosition, motionPaths, motionPaths2);
        } else if (i3 != 2) {
            initCartesian(keyPosition, motionPaths, motionPaths2);
        } else {
            initScreen(i, i2, keyPosition, motionPaths, motionPaths2);
        }
    }

    /* access modifiers changed from: 0000 */
    public void initScreen(int i, int i2, KeyPosition keyPosition, MotionPaths motionPaths, MotionPaths motionPaths2) {
        KeyPosition keyPosition2 = keyPosition;
        MotionPaths motionPaths3 = motionPaths;
        MotionPaths motionPaths4 = motionPaths2;
        float f = ((float) keyPosition2.mFramePosition) / 100.0f;
        this.time = f;
        this.mDrawPath = keyPosition2.mDrawPath;
        float f2 = Float.isNaN(keyPosition2.mPercentWidth) ? f : keyPosition2.mPercentWidth;
        float f3 = Float.isNaN(keyPosition2.mPercentHeight) ? f : keyPosition2.mPercentHeight;
        float f4 = motionPaths4.width;
        float f5 = motionPaths3.width;
        float f6 = f4 - f5;
        float f7 = motionPaths4.height;
        float f8 = motionPaths3.height;
        float f9 = f7 - f8;
        this.position = this.time;
        float f10 = motionPaths3.f30x;
        float f11 = (f5 / 2.0f) + f10;
        float f12 = motionPaths3.f31y;
        float f13 = motionPaths4.f30x + (f4 / 2.0f);
        float f14 = (motionPaths4.f31y + (f7 / 2.0f)) - (f12 + (f8 / 2.0f));
        float f15 = f6 * f2;
        this.f30x = (float) ((int) ((f10 + ((f13 - f11) * f)) - (f15 / 2.0f)));
        float f16 = f9 * f3;
        this.f31y = (float) ((int) ((f12 + (f14 * f)) - (f16 / 2.0f)));
        this.width = (float) ((int) (f5 + f15));
        this.height = (float) ((int) (f8 + f16));
        this.mMode = 3;
        KeyPosition keyPosition3 = keyPosition;
        if (!Float.isNaN(keyPosition3.mPercentX)) {
            this.f30x = (float) ((int) (keyPosition3.mPercentX * ((float) ((int) (((float) i) - this.width)))));
        }
        if (!Float.isNaN(keyPosition3.mPercentY)) {
            this.f31y = (float) ((int) (keyPosition3.mPercentY * ((float) ((int) (((float) i2) - this.height)))));
        }
        this.mKeyFrameEasing = Easing.getInterpolator(keyPosition3.mTransitionEasing);
        this.mPathMotionArc = keyPosition3.mPathMotionArc;
    }

    /* access modifiers changed from: 0000 */
    public void initPath(KeyPosition keyPosition, MotionPaths motionPaths, MotionPaths motionPaths2) {
        KeyPosition keyPosition2 = keyPosition;
        MotionPaths motionPaths3 = motionPaths;
        MotionPaths motionPaths4 = motionPaths2;
        float f = ((float) keyPosition2.mFramePosition) / 100.0f;
        this.time = f;
        this.mDrawPath = keyPosition2.mDrawPath;
        float f2 = Float.isNaN(keyPosition2.mPercentWidth) ? f : keyPosition2.mPercentWidth;
        float f3 = Float.isNaN(keyPosition2.mPercentHeight) ? f : keyPosition2.mPercentHeight;
        float f4 = motionPaths4.width - motionPaths3.width;
        float f5 = motionPaths4.height - motionPaths3.height;
        this.position = this.time;
        if (!Float.isNaN(keyPosition2.mPercentX)) {
            f = keyPosition2.mPercentX;
        }
        float f6 = motionPaths3.f30x;
        float f7 = motionPaths3.width;
        float f8 = (f7 / 2.0f) + f6;
        float f9 = motionPaths3.f31y;
        float f10 = motionPaths3.height;
        float f11 = (motionPaths4.f30x + (motionPaths4.width / 2.0f)) - f8;
        float f12 = (motionPaths4.f31y + (motionPaths4.height / 2.0f)) - ((f10 / 2.0f) + f9);
        float f13 = f11 * f;
        float f14 = f4 * f2;
        float f15 = f14 / 2.0f;
        this.f30x = (float) ((int) ((f6 + f13) - f15));
        float f16 = f * f12;
        float f17 = f5 * f3;
        float f18 = f17 / 2.0f;
        this.f31y = (float) ((int) ((f9 + f16) - f18));
        this.width = (float) ((int) (f7 + f14));
        this.height = (float) ((int) (f10 + f17));
        KeyPosition keyPosition3 = keyPosition;
        float f19 = Float.isNaN(keyPosition3.mPercentY) ? 0.0f : keyPosition3.mPercentY;
        float f20 = (-f12) * f19;
        float f21 = f11 * f19;
        this.mMode = 1;
        MotionPaths motionPaths5 = motionPaths;
        this.f30x = (float) ((int) ((motionPaths5.f30x + f13) - f15));
        this.f31y = (float) ((int) ((motionPaths5.f31y + f16) - f18));
        this.f30x += f20;
        this.f31y += f21;
        this.mKeyFrameEasing = Easing.getInterpolator(keyPosition3.mTransitionEasing);
        this.mPathMotionArc = keyPosition3.mPathMotionArc;
    }

    private boolean diff(float f, float f2) {
        boolean z = true;
        if (Float.isNaN(f) || Float.isNaN(f2)) {
            if (Float.isNaN(f) == Float.isNaN(f2)) {
                z = false;
            }
            return z;
        }
        if (Math.abs(f - f2) <= 1.0E-6f) {
            z = false;
        }
        return z;
    }

    /* access modifiers changed from: 0000 */
    public void different(MotionPaths motionPaths, boolean[] zArr, String[] strArr, boolean z) {
        zArr[0] = zArr[0] | diff(this.position, motionPaths.position);
        zArr[1] = zArr[1] | diff(this.f30x, motionPaths.f30x) | z;
        zArr[2] = z | diff(this.f31y, motionPaths.f31y) | zArr[2];
        zArr[3] = zArr[3] | diff(this.width, motionPaths.width);
        zArr[4] = diff(this.height, motionPaths.height) | zArr[4];
    }

    /* access modifiers changed from: 0000 */
    public void getCenter(int[] iArr, double[] dArr, float[] fArr, int i) {
        float f = this.f30x;
        float f2 = this.f31y;
        float f3 = this.width;
        float f4 = this.height;
        for (int i2 = 0; i2 < iArr.length; i2++) {
            float f5 = (float) dArr[i2];
            int i3 = iArr[i2];
            if (i3 == 1) {
                f = f5;
            } else if (i3 == 2) {
                f2 = f5;
            } else if (i3 == 3) {
                f3 = f5;
            } else if (i3 == 4) {
                f4 = f5;
            }
        }
        fArr[i] = f + (f3 / 2.0f) + 0.0f;
        fArr[i + 1] = f2 + (f4 / 2.0f) + 0.0f;
    }

    /* access modifiers changed from: 0000 */
    public void setView(View view, int[] iArr, double[] dArr, double[] dArr2, double[] dArr3) {
        float f;
        View view2 = view;
        int[] iArr2 = iArr;
        float f2 = this.f30x;
        float f3 = this.f31y;
        float f4 = this.width;
        float f5 = this.height;
        if (iArr2.length != 0 && this.mTempValue.length <= iArr2[iArr2.length - 1]) {
            int i = iArr2[iArr2.length - 1] + 1;
            this.mTempValue = new double[i];
            this.mTempDelta = new double[i];
        }
        Arrays.fill(this.mTempValue, Double.NaN);
        int i2 = 0;
        for (int i3 = 0; i3 < iArr2.length; i3++) {
            this.mTempValue[iArr2[i3]] = dArr[i3];
            this.mTempDelta[iArr2[i3]] = dArr2[i3];
        }
        float f6 = f2;
        float f7 = f3;
        float f8 = f4;
        float f9 = f5;
        float f10 = Float.NaN;
        float f11 = 0.0f;
        float f12 = 0.0f;
        float f13 = 0.0f;
        float f14 = 0.0f;
        while (true) {
            double[] dArr4 = this.mTempValue;
            if (i2 >= dArr4.length) {
                break;
            }
            boolean isNaN = Double.isNaN(dArr4[i2]);
            double d = 0.0d;
            if (!isNaN || !(dArr3 == null || dArr3[i2] == 0.0d)) {
                if (dArr3 != null) {
                    d = dArr3[i2];
                }
                if (!Double.isNaN(this.mTempValue[i2])) {
                    d = this.mTempValue[i2] + d;
                }
                f = f10;
                float f15 = (float) d;
                float f16 = (float) this.mTempDelta[i2];
                if (i2 != 0) {
                    if (i2 == 1) {
                        f6 = f15;
                        f11 = f16;
                    } else if (i2 == 2) {
                        f7 = f15;
                        f13 = f16;
                    } else if (i2 == 3) {
                        f8 = f15;
                        f12 = f16;
                    } else if (i2 == 4) {
                        f9 = f15;
                        f14 = f16;
                    } else if (i2 == 5) {
                        f10 = f15;
                        i2++;
                    }
                }
            } else {
                f = f10;
            }
            f10 = f;
            i2++;
        }
        float f17 = f10;
        if (!Float.isNaN(f17)) {
            float f18 = Float.NaN;
            if (Float.isNaN(Float.NaN)) {
                f18 = 0.0f;
            }
            view2.setRotation((float) (((double) f18) + ((double) f17) + Math.toDegrees(Math.atan2((double) (f13 + (f14 / 2.0f)), (double) (f11 + (f12 / 2.0f))))));
        } else if (!Float.isNaN(Float.NaN)) {
            view2.setRotation(Float.NaN);
        }
        float f19 = f6 + 0.5f;
        int i4 = (int) f19;
        float f20 = f7 + 0.5f;
        int i5 = (int) f20;
        int i6 = (int) (f19 + f8);
        int i7 = (int) (f20 + f9);
        int i8 = i6 - i4;
        int i9 = i7 - i5;
        if (!(i8 == view.getWidth() && i9 == view.getHeight())) {
            view2.measure(MeasureSpec.makeMeasureSpec(i8, 1073741824), MeasureSpec.makeMeasureSpec(i9, 1073741824));
        }
        view2.layout(i4, i5, i6, i7);
    }

    /* access modifiers changed from: 0000 */
    public void getRect(int[] iArr, double[] dArr, float[] fArr, int i) {
        float f = this.f30x;
        float f2 = this.f31y;
        float f3 = this.width;
        float f4 = this.height;
        for (int i2 = 0; i2 < iArr.length; i2++) {
            float f5 = (float) dArr[i2];
            int i3 = iArr[i2];
            if (i3 != 0) {
                if (i3 == 1) {
                    f = f5;
                } else if (i3 == 2) {
                    f2 = f5;
                } else if (i3 == 3) {
                    f3 = f5;
                } else if (i3 == 4) {
                    f4 = f5;
                }
            }
        }
        float f6 = f3 + f;
        float f7 = f4 + f2;
        boolean isNaN = Float.isNaN(Float.NaN);
        boolean isNaN2 = Float.isNaN(Float.NaN);
        float f8 = f + 0.0f;
        float f9 = f2 + 0.0f;
        float f10 = f6 + 0.0f;
        float f11 = f2 + 0.0f;
        float f12 = f6 + 0.0f;
        float f13 = f7 + 0.0f;
        float f14 = f + 0.0f;
        float f15 = f7 + 0.0f;
        int i4 = i + 1;
        fArr[i] = f8;
        int i5 = i4 + 1;
        fArr[i4] = f9;
        int i6 = i5 + 1;
        fArr[i5] = f10;
        int i7 = i6 + 1;
        fArr[i6] = f11;
        int i8 = i7 + 1;
        fArr[i7] = f12;
        int i9 = i8 + 1;
        fArr[i8] = f13;
        int i10 = i9 + 1;
        fArr[i9] = f14;
        fArr[i10] = f15;
    }

    /* access modifiers changed from: 0000 */
    public void setDpDt(float f, float f2, float[] fArr, int[] iArr, double[] dArr, double[] dArr2) {
        int[] iArr2 = iArr;
        float f3 = 0.0f;
        float f4 = 0.0f;
        float f5 = 0.0f;
        float f6 = 0.0f;
        for (int i = 0; i < iArr2.length; i++) {
            float f7 = (float) dArr[i];
            double d = dArr2[i];
            int i2 = iArr2[i];
            if (i2 != 0) {
                if (i2 == 1) {
                    f3 = f7;
                } else if (i2 == 2) {
                    f5 = f7;
                } else if (i2 == 3) {
                    f4 = f7;
                } else if (i2 == 4) {
                    f6 = f7;
                }
            }
        }
        float f8 = f3 - ((0.0f * f4) / 2.0f);
        float f9 = f5 - ((0.0f * f6) / 2.0f);
        float f10 = (f6 * 1.0f) + f9;
        fArr[0] = (f8 * (1.0f - f)) + (((f4 * 1.0f) + f8) * f) + 0.0f;
        fArr[1] = (f9 * (1.0f - f2)) + (f10 * f2) + 0.0f;
    }

    /* access modifiers changed from: 0000 */
    public void fillStandard(double[] dArr, int[] iArr) {
        float[] fArr = {this.position, this.f30x, this.f31y, this.width, this.height, this.mPathRotate};
        int i = 0;
        for (int i2 = 0; i2 < iArr.length; i2++) {
            if (iArr[i2] < fArr.length) {
                int i3 = i + 1;
                dArr[i] = (double) fArr[iArr[i2]];
                i = i3;
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean hasCustomData(String str) {
        return this.attributes.containsKey(str);
    }

    /* access modifiers changed from: 0000 */
    public int getCustomDataCount(String str) {
        return ((ConstraintAttribute) this.attributes.get(str)).noOfInterpValues();
    }

    /* access modifiers changed from: 0000 */
    public int getCustomData(String str, double[] dArr, int i) {
        ConstraintAttribute constraintAttribute = (ConstraintAttribute) this.attributes.get(str);
        if (constraintAttribute.noOfInterpValues() == 1) {
            dArr[i] = (double) constraintAttribute.getValueToInterpolate();
            return 1;
        }
        int noOfInterpValues = constraintAttribute.noOfInterpValues();
        float[] fArr = new float[noOfInterpValues];
        constraintAttribute.getValuesToInterpolate(fArr);
        int i2 = 0;
        while (i2 < noOfInterpValues) {
            int i3 = i + 1;
            dArr[i] = (double) fArr[i2];
            i2++;
            i = i3;
        }
        return noOfInterpValues;
    }

    /* access modifiers changed from: 0000 */
    public void setBounds(float f, float f2, float f3, float f4) {
        this.f30x = f;
        this.f31y = f2;
        this.width = f3;
        this.height = f4;
    }

    public int compareTo(@NonNull MotionPaths motionPaths) {
        return Float.compare(this.position, motionPaths.position);
    }

    public void applyParameters(Constraint constraint) {
        this.mKeyFrameEasing = Easing.getInterpolator(constraint.motion.mTransitionEasing);
        this.mPathMotionArc = constraint.motion.mPathMotionArc;
        this.mPathRotate = constraint.motion.mPathRotate;
        this.mDrawPath = constraint.motion.mDrawPath;
        this.mProgress = constraint.propertySet.mProgress;
        for (String str : constraint.mCustomConstraints.keySet()) {
            ConstraintAttribute constraintAttribute = (ConstraintAttribute) constraint.mCustomConstraints.get(str);
            if (constraintAttribute.getType() != AttributeType.STRING_TYPE) {
                this.attributes.put(str, constraintAttribute);
            }
        }
    }
}
