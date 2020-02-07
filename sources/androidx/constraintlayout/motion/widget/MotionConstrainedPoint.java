package androidx.constraintlayout.motion.widget;

import android.os.Build.VERSION;
import android.util.Log;
import android.view.View;
import androidx.constraintlayout.motion.utils.Easing;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.widget.ConstraintAttribute;
import androidx.constraintlayout.widget.ConstraintAttribute.AttributeType;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.constraintlayout.widget.ConstraintSet.Constraint;
import androidx.core.app.NotificationCompat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;

class MotionConstrainedPoint implements Comparable<MotionConstrainedPoint> {
    static final int CARTESIAN = 2;
    public static final boolean DEBUG = false;
    static final int PERPENDICULAR = 1;
    public static final String TAG = "MotionPaths";
    static String[] names = {"position", "x", "y", "width", "height", "pathRotate"};
    private float alpha = 1.0f;
    private boolean applyElevation = false;
    LinkedHashMap<String, ConstraintAttribute> attributes = new LinkedHashMap<>();
    private float elevation = 0.0f;
    private float height;
    private int mDrawPath = 0;
    private Easing mKeyFrameEasing;
    int mMode = 0;
    private float mPathRotate = Float.NaN;
    private float mPivotX = Float.NaN;
    private float mPivotY = Float.NaN;
    private float mProgress = Float.NaN;
    double[] mTempDelta = new double[18];
    double[] mTempValue = new double[18];
    int mVisibilityMode = 0;
    private float position;
    private float rotation = 0.0f;
    private float rotationX = 0.0f;
    public float rotationY = 0.0f;
    private float scaleX = 1.0f;
    private float scaleY = 1.0f;
    private float translationX = 0.0f;
    private float translationY = 0.0f;
    private float translationZ = 0.0f;
    int visibility;
    private float width;

    /* renamed from: x */
    private float f27x;

    /* renamed from: y */
    private float f28y;

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
    public void different(MotionConstrainedPoint motionConstrainedPoint, HashSet<String> hashSet) {
        String str = "alpha";
        if (diff(this.alpha, motionConstrainedPoint.alpha)) {
            hashSet.add(str);
        }
        if (diff(this.elevation, motionConstrainedPoint.elevation)) {
            hashSet.add("elevation");
        }
        int i = this.visibility;
        int i2 = motionConstrainedPoint.visibility;
        if (i != i2 && this.mVisibilityMode == 0 && (i == 0 || i2 == 0)) {
            hashSet.add(str);
        }
        if (diff(this.rotation, motionConstrainedPoint.rotation)) {
            hashSet.add("rotation");
        }
        if (!Float.isNaN(this.mPathRotate) || !Float.isNaN(motionConstrainedPoint.mPathRotate)) {
            hashSet.add("transitionPathRotate");
        }
        if (!Float.isNaN(this.mProgress) || !Float.isNaN(motionConstrainedPoint.mProgress)) {
            hashSet.add(NotificationCompat.CATEGORY_PROGRESS);
        }
        if (diff(this.rotationX, motionConstrainedPoint.rotationX)) {
            hashSet.add("rotationX");
        }
        if (diff(this.rotationY, motionConstrainedPoint.rotationY)) {
            hashSet.add("rotationY");
        }
        if (diff(this.scaleX, motionConstrainedPoint.scaleX)) {
            hashSet.add("scaleX");
        }
        if (diff(this.scaleY, motionConstrainedPoint.scaleY)) {
            hashSet.add("scaleY");
        }
        if (diff(this.translationX, motionConstrainedPoint.translationX)) {
            hashSet.add("translationX");
        }
        if (diff(this.translationY, motionConstrainedPoint.translationY)) {
            hashSet.add("translationY");
        }
        if (diff(this.translationZ, motionConstrainedPoint.translationZ)) {
            hashSet.add("translationZ");
        }
    }

    /* access modifiers changed from: 0000 */
    public void different(MotionConstrainedPoint motionConstrainedPoint, boolean[] zArr, String[] strArr) {
        zArr[0] = zArr[0] | diff(this.position, motionConstrainedPoint.position);
        zArr[1] = zArr[1] | diff(this.f27x, motionConstrainedPoint.f27x);
        zArr[2] = zArr[2] | diff(this.f28y, motionConstrainedPoint.f28y);
        zArr[3] = zArr[3] | diff(this.width, motionConstrainedPoint.width);
        zArr[4] = diff(this.height, motionConstrainedPoint.height) | zArr[4];
    }

    /* access modifiers changed from: 0000 */
    public void fillStandard(double[] dArr, int[] iArr) {
        float[] fArr = {this.position, this.f27x, this.f28y, this.width, this.height, this.alpha, this.elevation, this.rotation, this.rotationX, this.rotationY, this.scaleX, this.scaleY, this.mPivotX, this.mPivotY, this.translationX, this.translationY, this.translationZ, this.mPathRotate};
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
        this.f27x = f;
        this.f28y = f2;
        this.width = f3;
        this.height = f4;
    }

    public int compareTo(MotionConstrainedPoint motionConstrainedPoint) {
        return Float.compare(this.position, motionConstrainedPoint.position);
    }

    public void applyParameters(View view) {
        this.visibility = view.getVisibility();
        this.alpha = view.getVisibility() != 0 ? 0.0f : view.getAlpha();
        this.applyElevation = false;
        if (VERSION.SDK_INT >= 21) {
            this.elevation = view.getElevation();
        }
        this.rotation = view.getRotation();
        this.rotationX = view.getRotationX();
        this.rotationY = view.getRotationY();
        this.scaleX = view.getScaleX();
        this.scaleY = view.getScaleY();
        this.mPivotX = view.getPivotX();
        this.mPivotY = view.getPivotY();
        this.translationX = view.getTranslationX();
        this.translationY = view.getTranslationY();
        if (VERSION.SDK_INT >= 21) {
            this.translationZ = view.getTranslationZ();
        }
    }

    public void applyParameters(Constraint constraint) {
        this.mVisibilityMode = constraint.propertySet.mVisibilityMode;
        this.visibility = constraint.propertySet.visibility;
        this.alpha = (constraint.propertySet.visibility == 0 || this.mVisibilityMode != 0) ? constraint.propertySet.alpha : 0.0f;
        this.applyElevation = constraint.transform.applyElevation;
        this.elevation = constraint.transform.elevation;
        this.rotation = constraint.transform.rotation;
        this.rotationX = constraint.transform.rotationX;
        this.rotationY = constraint.transform.rotationY;
        this.scaleX = constraint.transform.scaleX;
        this.scaleY = constraint.transform.scaleY;
        this.mPivotX = constraint.transform.transformPivotX;
        this.mPivotY = constraint.transform.transformPivotY;
        this.translationX = constraint.transform.translationX;
        this.translationY = constraint.transform.translationY;
        this.translationZ = constraint.transform.translationZ;
        this.mKeyFrameEasing = Easing.getInterpolator(constraint.motion.mTransitionEasing);
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

    public void addValues(HashMap<String, SplineSet> hashMap, int i) {
        for (String str : hashMap.keySet()) {
            SplineSet splineSet = (SplineSet) hashMap.get(str);
            char c = 65535;
            switch (str.hashCode()) {
                case -1249320806:
                    if (str.equals("rotationX")) {
                        c = 3;
                        break;
                    }
                    break;
                case -1249320805:
                    if (str.equals("rotationY")) {
                        c = 4;
                        break;
                    }
                    break;
                case -1225497657:
                    if (str.equals("translationX")) {
                        c = 9;
                        break;
                    }
                    break;
                case -1225497656:
                    if (str.equals("translationY")) {
                        c = 10;
                        break;
                    }
                    break;
                case -1225497655:
                    if (str.equals("translationZ")) {
                        c = 11;
                        break;
                    }
                    break;
                case -1001078227:
                    if (str.equals(NotificationCompat.CATEGORY_PROGRESS)) {
                        c = 6;
                        break;
                    }
                    break;
                case -908189618:
                    if (str.equals("scaleX")) {
                        c = 7;
                        break;
                    }
                    break;
                case -908189617:
                    if (str.equals("scaleY")) {
                        c = 8;
                        break;
                    }
                    break;
                case -40300674:
                    if (str.equals("rotation")) {
                        c = 2;
                        break;
                    }
                    break;
                case -4379043:
                    if (str.equals("elevation")) {
                        c = 1;
                        break;
                    }
                    break;
                case 37232917:
                    if (str.equals("transitionPathRotate")) {
                        c = 5;
                        break;
                    }
                    break;
                case 92909918:
                    if (str.equals("alpha")) {
                        c = 0;
                        break;
                    }
                    break;
            }
            float f = 1.0f;
            float f2 = 0.0f;
            switch (c) {
                case 0:
                    if (!Float.isNaN(this.alpha)) {
                        f = this.alpha;
                    }
                    splineSet.setPoint(i, f);
                    break;
                case 1:
                    if (!Float.isNaN(this.elevation)) {
                        f2 = this.elevation;
                    }
                    splineSet.setPoint(i, f2);
                    break;
                case 2:
                    if (!Float.isNaN(this.rotation)) {
                        f2 = this.rotation;
                    }
                    splineSet.setPoint(i, f2);
                    break;
                case 3:
                    if (!Float.isNaN(this.rotationX)) {
                        f2 = this.rotationX;
                    }
                    splineSet.setPoint(i, f2);
                    break;
                case 4:
                    if (!Float.isNaN(this.rotationY)) {
                        f2 = this.rotationY;
                    }
                    splineSet.setPoint(i, f2);
                    break;
                case 5:
                    if (!Float.isNaN(this.mPathRotate)) {
                        f2 = this.mPathRotate;
                    }
                    splineSet.setPoint(i, f2);
                    break;
                case 6:
                    if (!Float.isNaN(this.mProgress)) {
                        f2 = this.mProgress;
                    }
                    splineSet.setPoint(i, f2);
                    break;
                case 7:
                    if (!Float.isNaN(this.scaleX)) {
                        f = this.scaleX;
                    }
                    splineSet.setPoint(i, f);
                    break;
                case 8:
                    if (!Float.isNaN(this.scaleY)) {
                        f = this.scaleY;
                    }
                    splineSet.setPoint(i, f);
                    break;
                case 9:
                    if (!Float.isNaN(this.translationX)) {
                        f2 = this.translationX;
                    }
                    splineSet.setPoint(i, f2);
                    break;
                case 10:
                    if (!Float.isNaN(this.translationY)) {
                        f2 = this.translationY;
                    }
                    splineSet.setPoint(i, f2);
                    break;
                case 11:
                    if (!Float.isNaN(this.translationZ)) {
                        f2 = this.translationZ;
                    }
                    splineSet.setPoint(i, f2);
                    break;
                default:
                    String str2 = "MotionPaths";
                    if (!str.startsWith("CUSTOM")) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("UNKNOWN spline ");
                        sb.append(str);
                        Log.e(str2, sb.toString());
                        break;
                    } else {
                        String str3 = str.split(",")[1];
                        if (!this.attributes.containsKey(str3)) {
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append("UNKNOWN customName ");
                            sb2.append(str3);
                            Log.e(str2, sb2.toString());
                            break;
                        } else {
                            ConstraintAttribute constraintAttribute = (ConstraintAttribute) this.attributes.get(str3);
                            if (!(splineSet instanceof CustomSet)) {
                                StringBuilder sb3 = new StringBuilder();
                                sb3.append(str);
                                sb3.append(" splineSet not a CustomSet frame = ");
                                sb3.append(i);
                                sb3.append(", value");
                                sb3.append(constraintAttribute.getValueToInterpolate());
                                sb3.append(splineSet);
                                Log.e(str2, sb3.toString());
                                break;
                            } else {
                                ((CustomSet) splineSet).setPoint(i, constraintAttribute);
                                break;
                            }
                        }
                    }
            }
        }
    }

    public void setState(View view) {
        setBounds(view.getX(), view.getY(), (float) view.getWidth(), (float) view.getHeight());
        applyParameters(view);
    }

    public void setState(ConstraintWidget constraintWidget, ConstraintSet constraintSet, int i) {
        setBounds((float) constraintWidget.getX(), (float) constraintWidget.getY(), (float) constraintWidget.getWidth(), (float) constraintWidget.getHeight());
        applyParameters(constraintSet.getParameters(i));
    }
}
