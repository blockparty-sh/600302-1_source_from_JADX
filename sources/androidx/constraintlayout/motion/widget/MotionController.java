package androidx.constraintlayout.motion.widget;

import android.graphics.RectF;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import androidx.constraintlayout.motion.utils.CurveFit;
import androidx.constraintlayout.motion.utils.Easing;
import androidx.constraintlayout.motion.utils.VelocityMatrix;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.widget.ConstraintAttribute;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.constraintlayout.widget.ConstraintSet.Constraint;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class MotionController {
    private static final boolean DEBUG = false;
    public static final int DRAW_PATH_AS_CONFIGURED = 4;
    public static final int DRAW_PATH_BASIC = 1;
    public static final int DRAW_PATH_CARTESIAN = 3;
    public static final int DRAW_PATH_NONE = 0;
    public static final int DRAW_PATH_RECTANGLE = 5;
    public static final int DRAW_PATH_RELATIVE = 2;
    public static final int DRAW_PATH_SCREEN = 6;
    private static final boolean FAVOR_FIXED_SIZE_VIEWS = false;
    public static final int HORIZONTAL_PATH_X = 2;
    public static final int HORIZONTAL_PATH_Y = 3;
    public static final int PATH_PERCENT = 0;
    public static final int PATH_PERPENDICULAR = 1;
    private static final String TAG = "MotionController";
    public static final int VERTICAL_PATH_X = 4;
    public static final int VERTICAL_PATH_Y = 5;
    private int MAX_DIMENSION = 4;
    String[] attributeTable;
    private CurveFit mArcSpline;
    private int[] mAttributeInterpCount;
    private String[] mAttributeNames;
    private HashMap<String, SplineSet> mAttributesMap;
    String mConstraintTag;
    private int mCurveFitType = -1;
    private HashMap<String, KeyCycleOscillator> mCycleMap;
    private MotionPaths mEndMotionPath = new MotionPaths();
    private MotionConstrainedPoint mEndPoint = new MotionConstrainedPoint();
    int mId;
    private double[] mInterpolateData;
    private int[] mInterpolateVariables;
    private double[] mInterpolateVelocity;
    private ArrayList<C0188Key> mKeyList = new ArrayList<>();
    private KeyTrigger[] mKeyTriggers;
    private ArrayList<MotionPaths> mMotionPaths = new ArrayList<>();
    float mMotionStagger = Float.NaN;
    private CurveFit[] mSpline;
    float mStaggerOffset = 0.0f;
    float mStaggerScale = 1.0f;
    private MotionPaths mStartMotionPath = new MotionPaths();
    private MotionConstrainedPoint mStartPoint = new MotionConstrainedPoint();
    private HashMap<String, TimeCycleSplineSet> mTimeCycleAttributesMap;
    private float[] mValuesBuff = new float[this.MAX_DIMENSION];
    private float[] mVelocity = new float[1];
    View mView;

    /* access modifiers changed from: 0000 */
    public MotionPaths getKeyFrame(int i) {
        return (MotionPaths) this.mMotionPaths.get(i);
    }

    MotionController(View view) {
        setView(view);
    }

    /* access modifiers changed from: 0000 */
    public float getStartX() {
        return this.mStartMotionPath.f30x;
    }

    /* access modifiers changed from: 0000 */
    public float getStartY() {
        return this.mStartMotionPath.f31y;
    }

    /* access modifiers changed from: 0000 */
    public float getFinalX() {
        return this.mEndMotionPath.f30x;
    }

    /* access modifiers changed from: 0000 */
    public float getFinalY() {
        return this.mEndMotionPath.f31y;
    }

    /* access modifiers changed from: 0000 */
    public void buildPath(float[] fArr, int i) {
        float[] fArr2 = fArr;
        int i2 = i;
        float f = 1.0f;
        float f2 = 1.0f / ((float) (i2 - 1));
        HashMap<String, SplineSet> hashMap = this.mAttributesMap;
        String str = "translationX";
        KeyCycleOscillator keyCycleOscillator = null;
        SplineSet splineSet = hashMap == null ? null : (SplineSet) hashMap.get(str);
        HashMap<String, SplineSet> hashMap2 = this.mAttributesMap;
        String str2 = "translationY";
        SplineSet splineSet2 = hashMap2 == null ? null : (SplineSet) hashMap2.get(str2);
        HashMap<String, KeyCycleOscillator> hashMap3 = this.mCycleMap;
        KeyCycleOscillator keyCycleOscillator2 = hashMap3 == null ? null : (KeyCycleOscillator) hashMap3.get(str);
        HashMap<String, KeyCycleOscillator> hashMap4 = this.mCycleMap;
        if (hashMap4 != null) {
            keyCycleOscillator = (KeyCycleOscillator) hashMap4.get(str2);
        }
        int i3 = 0;
        while (i3 < i2) {
            float f3 = ((float) i3) * f2;
            float f4 = 0.0f;
            if (this.mStaggerScale != f) {
                if (f3 < this.mStaggerOffset) {
                    f3 = 0.0f;
                }
                float f5 = this.mStaggerOffset;
                if (f3 > f5 && ((double) f3) < 1.0d) {
                    f3 = (f3 - f5) * this.mStaggerScale;
                }
            }
            double d = (double) f3;
            Easing easing = this.mStartMotionPath.mKeyFrameEasing;
            float f6 = Float.NaN;
            Iterator it = this.mMotionPaths.iterator();
            while (it.hasNext()) {
                MotionPaths motionPaths = (MotionPaths) it.next();
                if (motionPaths.mKeyFrameEasing != null) {
                    if (motionPaths.time < f3) {
                        easing = motionPaths.mKeyFrameEasing;
                        f4 = motionPaths.time;
                    } else if (Float.isNaN(f6)) {
                        f6 = motionPaths.time;
                    }
                }
                int i4 = i;
            }
            if (easing != null) {
                if (Float.isNaN(f6)) {
                    f6 = 1.0f;
                }
                float f7 = f6 - f4;
                d = (double) ((((float) easing.get((double) ((f3 - f4) / f7))) * f7) + f4);
            }
            this.mSpline[0].getPos(d, this.mInterpolateData);
            CurveFit curveFit = this.mArcSpline;
            if (curveFit != null) {
                double[] dArr = this.mInterpolateData;
                if (dArr.length > 0) {
                    curveFit.getPos(d, dArr);
                }
            }
            int i5 = i3 * 2;
            this.mStartMotionPath.getCenter(this.mInterpolateVariables, this.mInterpolateData, fArr2, i5);
            if (keyCycleOscillator2 != null) {
                fArr2[i5] = fArr2[i5] + keyCycleOscillator2.get(f3);
            } else if (splineSet != null) {
                fArr2[i5] = fArr2[i5] + splineSet.get(f3);
            }
            if (keyCycleOscillator != null) {
                int i6 = i5 + 1;
                fArr2[i6] = fArr2[i6] + keyCycleOscillator.get(f3);
            } else if (splineSet2 != null) {
                int i7 = i5 + 1;
                fArr2[i7] = fArr2[i7] + splineSet2.get(f3);
            }
            i3++;
            i2 = i;
            f = 1.0f;
        }
    }

    private float getPreCycleDistance() {
        float[] fArr = new float[2];
        float f = 1.0f / ((float) 99);
        double d = 0.0d;
        double d2 = 0.0d;
        float f2 = 0.0f;
        for (int i = 0; i < 100; i++) {
            float f3 = ((float) i) * f;
            double d3 = (double) f3;
            Easing easing = this.mStartMotionPath.mKeyFrameEasing;
            float f4 = Float.NaN;
            Iterator it = this.mMotionPaths.iterator();
            float f5 = 0.0f;
            while (it.hasNext()) {
                MotionPaths motionPaths = (MotionPaths) it.next();
                if (motionPaths.mKeyFrameEasing != null) {
                    if (motionPaths.time < f3) {
                        Easing easing2 = motionPaths.mKeyFrameEasing;
                        f5 = motionPaths.time;
                        easing = easing2;
                    } else if (Float.isNaN(f4)) {
                        f4 = motionPaths.time;
                    }
                }
            }
            if (easing != null) {
                if (Float.isNaN(f4)) {
                    f4 = 1.0f;
                }
                float f6 = f4 - f5;
                d3 = (double) ((((float) easing.get((double) ((f3 - f5) / f6))) * f6) + f5);
            }
            this.mSpline[0].getPos(d3, this.mInterpolateData);
            this.mStartMotionPath.getCenter(this.mInterpolateVariables, this.mInterpolateData, fArr, 0);
            if (i > 0) {
                f2 = (float) (((double) f2) + Math.hypot(d2 - ((double) fArr[1]), d - ((double) fArr[0])));
            }
            d = (double) fArr[0];
            d2 = (double) fArr[1];
        }
        return f2;
    }

    /* access modifiers changed from: 0000 */
    public KeyPositionBase getPositionKeyframe(int i, int i2, float f, float f2) {
        RectF rectF = new RectF();
        rectF.left = this.mStartMotionPath.f30x;
        rectF.top = this.mStartMotionPath.f31y;
        rectF.right = rectF.left + this.mStartMotionPath.width;
        rectF.bottom = rectF.top + this.mStartMotionPath.height;
        RectF rectF2 = new RectF();
        rectF2.left = this.mEndMotionPath.f30x;
        rectF2.top = this.mEndMotionPath.f31y;
        rectF2.right = rectF2.left + this.mEndMotionPath.width;
        rectF2.bottom = rectF2.top + this.mEndMotionPath.height;
        Iterator it = this.mKeyList.iterator();
        while (it.hasNext()) {
            C0188Key key = (C0188Key) it.next();
            if (key instanceof KeyPositionBase) {
                KeyPositionBase keyPositionBase = (KeyPositionBase) key;
                if (keyPositionBase.intersects(i, i2, rectF, rectF2, f, f2)) {
                    return keyPositionBase;
                }
            }
        }
        return null;
    }

    /* access modifiers changed from: 0000 */
    public int buildKeyFrames(float[] fArr, int[] iArr) {
        if (fArr == null) {
            return 0;
        }
        double[] timePoints = this.mSpline[0].getTimePoints();
        if (iArr != null) {
            Iterator it = this.mMotionPaths.iterator();
            int i = 0;
            while (it.hasNext()) {
                int i2 = i + 1;
                iArr[i] = ((MotionPaths) it.next()).mMode;
                i = i2;
            }
        }
        int i3 = 0;
        for (double pos : timePoints) {
            this.mSpline[0].getPos(pos, this.mInterpolateData);
            this.mStartMotionPath.getCenter(this.mInterpolateVariables, this.mInterpolateData, fArr, i3);
            i3 += 2;
        }
        return i3 / 2;
    }

    /* access modifiers changed from: 0000 */
    public int getAttributeValues(String str, float[] fArr, int i) {
        SplineSet splineSet = (SplineSet) this.mAttributesMap.get(str);
        if (splineSet == null) {
            return -1;
        }
        for (int i2 = 0; i2 < fArr.length; i2++) {
            fArr[i2] = splineSet.get((float) (i2 / (fArr.length - 1)));
        }
        return fArr.length;
    }

    /* access modifiers changed from: 0000 */
    public void buildRect(float f, float[] fArr, int i) {
        this.mSpline[0].getPos((double) getAdjustedPosition(f, null), this.mInterpolateData);
        this.mStartMotionPath.getRect(this.mInterpolateVariables, this.mInterpolateData, fArr, i);
    }

    /* access modifiers changed from: 0000 */
    public void buildRectangles(float[] fArr, int i) {
        float f = 1.0f / ((float) (i - 1));
        for (int i2 = 0; i2 < i; i2++) {
            this.mSpline[0].getPos((double) getAdjustedPosition(((float) i2) * f, null), this.mInterpolateData);
            this.mStartMotionPath.getRect(this.mInterpolateVariables, this.mInterpolateData, fArr, i2 * 8);
        }
    }

    /* access modifiers changed from: 0000 */
    public float getKeyFrameParameter(int i, float f, float f2) {
        float f3 = this.mEndMotionPath.f30x - this.mStartMotionPath.f30x;
        float f4 = this.mEndMotionPath.f31y - this.mStartMotionPath.f31y;
        float f5 = this.mStartMotionPath.f30x + (this.mStartMotionPath.width / 2.0f);
        float f6 = this.mStartMotionPath.f31y + (this.mStartMotionPath.height / 2.0f);
        float hypot = (float) Math.hypot((double) f3, (double) f4);
        if (((double) hypot) < 1.0E-7d) {
            return Float.NaN;
        }
        float f7 = f - f5;
        float f8 = f2 - f6;
        if (((float) Math.hypot((double) f7, (double) f8)) == 0.0f) {
            return 0.0f;
        }
        float f9 = (f7 * f3) + (f8 * f4);
        if (i == 0) {
            return f9 / hypot;
        }
        if (i == 1) {
            return (float) Math.sqrt((double) ((hypot * hypot) - (f9 * f9)));
        }
        if (i == 2) {
            return f7 / f3;
        }
        if (i == 3) {
            return f8 / f3;
        }
        if (i == 4) {
            return f7 / f4;
        }
        if (i != 5) {
            return 0.0f;
        }
        return f8 / f4;
    }

    private void insertKey(MotionPaths motionPaths) {
        int binarySearch = Collections.binarySearch(this.mMotionPaths, motionPaths);
        if (binarySearch == 0) {
            StringBuilder sb = new StringBuilder();
            sb.append(" KeyPath positon \"");
            sb.append(motionPaths.position);
            sb.append("\" outside of range");
            Log.e(TAG, sb.toString());
        }
        this.mMotionPaths.add((-binarySearch) - 1, motionPaths);
    }

    /* access modifiers changed from: 0000 */
    public void addKeys(ArrayList<C0188Key> arrayList) {
        this.mKeyList.addAll(arrayList);
    }

    /* access modifiers changed from: 0000 */
    public void addKey(C0188Key key) {
        this.mKeyList.add(key);
    }

    public void setup(int i, int i2, float f, long j) {
        ArrayList arrayList;
        TimeCycleSplineSet timeCycleSplineSet;
        SplineSet splineSet;
        new HashSet();
        HashSet hashSet = new HashSet();
        HashSet hashSet2 = new HashSet();
        HashSet hashSet3 = new HashSet();
        HashMap hashMap = new HashMap();
        this.mStartPoint.different(this.mEndPoint, hashSet2);
        ArrayList<C0188Key> arrayList2 = this.mKeyList;
        if (arrayList2 != null) {
            Iterator it = arrayList2.iterator();
            arrayList = null;
            while (it.hasNext()) {
                C0188Key key = (C0188Key) it.next();
                if (key instanceof KeyPosition) {
                    KeyPosition keyPosition = (KeyPosition) key;
                    MotionPaths motionPaths = new MotionPaths(i, i2, keyPosition, this.mStartMotionPath, this.mEndMotionPath);
                    insertKey(motionPaths);
                    if (keyPosition.mCurveFit != C0188Key.UNSET) {
                        this.mCurveFitType = keyPosition.mCurveFit;
                    }
                } else if (key instanceof KeyCycle) {
                    key.getAttributeNames(hashSet3);
                } else if (key instanceof KeyTimeCycle) {
                    key.getAttributeNames(hashSet);
                } else if (key instanceof KeyTrigger) {
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add((KeyTrigger) key);
                } else {
                    key.setInterpolation(hashMap);
                    key.getAttributeNames(hashSet2);
                }
            }
        } else {
            arrayList = null;
        }
        if (arrayList != null) {
            this.mKeyTriggers = (KeyTrigger[]) arrayList.toArray(new KeyTrigger[0]);
        }
        String str = ",";
        String str2 = "CUSTOM,";
        if (!hashSet2.isEmpty()) {
            this.mAttributesMap = new HashMap<>();
            Iterator it2 = hashSet2.iterator();
            while (it2.hasNext()) {
                String str3 = (String) it2.next();
                if (str3.startsWith(str2)) {
                    SparseArray sparseArray = new SparseArray();
                    String str4 = str3.split(str)[1];
                    Iterator it3 = this.mKeyList.iterator();
                    while (it3.hasNext()) {
                        C0188Key key2 = (C0188Key) it3.next();
                        if (key2.mCustomConstraints != null) {
                            ConstraintAttribute constraintAttribute = (ConstraintAttribute) key2.mCustomConstraints.get(str4);
                            if (constraintAttribute != null) {
                                sparseArray.append(key2.mFramePosition, constraintAttribute);
                            }
                        }
                    }
                    splineSet = SplineSet.makeCustomSpline(str3, sparseArray);
                } else {
                    splineSet = SplineSet.makeSpline(str3);
                }
                if (splineSet != null) {
                    splineSet.setType(str3);
                    this.mAttributesMap.put(str3, splineSet);
                }
            }
            ArrayList<C0188Key> arrayList3 = this.mKeyList;
            if (arrayList3 != null) {
                Iterator it4 = arrayList3.iterator();
                while (it4.hasNext()) {
                    C0188Key key3 = (C0188Key) it4.next();
                    if (key3 instanceof KeyAttributes) {
                        key3.addValues(this.mAttributesMap);
                    }
                }
            }
            this.mStartPoint.addValues(this.mAttributesMap, 0);
            this.mEndPoint.addValues(this.mAttributesMap, 100);
            for (String str5 : this.mAttributesMap.keySet()) {
                ((SplineSet) this.mAttributesMap.get(str5)).setup(hashMap.containsKey(str5) ? ((Integer) hashMap.get(str5)).intValue() : 0);
            }
        }
        if (!hashSet.isEmpty()) {
            if (this.mTimeCycleAttributesMap == null) {
                this.mTimeCycleAttributesMap = new HashMap<>();
            }
            Iterator it5 = hashSet.iterator();
            while (it5.hasNext()) {
                String str6 = (String) it5.next();
                if (!this.mTimeCycleAttributesMap.containsKey(str6)) {
                    if (str6.startsWith(str2)) {
                        SparseArray sparseArray2 = new SparseArray();
                        String str7 = str6.split(str)[1];
                        Iterator it6 = this.mKeyList.iterator();
                        while (it6.hasNext()) {
                            C0188Key key4 = (C0188Key) it6.next();
                            if (key4.mCustomConstraints != null) {
                                ConstraintAttribute constraintAttribute2 = (ConstraintAttribute) key4.mCustomConstraints.get(str7);
                                if (constraintAttribute2 != null) {
                                    sparseArray2.append(key4.mFramePosition, constraintAttribute2);
                                }
                            }
                        }
                        timeCycleSplineSet = TimeCycleSplineSet.makeCustomSpline(str6, sparseArray2);
                        long j2 = j;
                    } else {
                        timeCycleSplineSet = TimeCycleSplineSet.makeSpline(str6, j);
                    }
                    if (timeCycleSplineSet != null) {
                        timeCycleSplineSet.setType(str6);
                        this.mTimeCycleAttributesMap.put(str6, timeCycleSplineSet);
                    }
                }
            }
            ArrayList<C0188Key> arrayList4 = this.mKeyList;
            if (arrayList4 != null) {
                Iterator it7 = arrayList4.iterator();
                while (it7.hasNext()) {
                    C0188Key key5 = (C0188Key) it7.next();
                    if (key5 instanceof KeyTimeCycle) {
                        ((KeyTimeCycle) key5).addTimeValues(this.mTimeCycleAttributesMap);
                    }
                }
            }
            for (String str8 : this.mTimeCycleAttributesMap.keySet()) {
                ((TimeCycleSplineSet) this.mTimeCycleAttributesMap.get(str8)).setup(hashMap.containsKey(str8) ? ((Integer) hashMap.get(str8)).intValue() : 0);
            }
        }
        MotionPaths[] motionPathsArr = new MotionPaths[(this.mMotionPaths.size() + 2)];
        motionPathsArr[0] = this.mStartMotionPath;
        motionPathsArr[motionPathsArr.length - 1] = this.mEndMotionPath;
        if (this.mMotionPaths.size() > 0 && this.mCurveFitType == -1) {
            this.mCurveFitType = 0;
        }
        Iterator it8 = this.mMotionPaths.iterator();
        int i3 = 1;
        while (it8.hasNext()) {
            int i4 = i3 + 1;
            motionPathsArr[i3] = (MotionPaths) it8.next();
            i3 = i4;
        }
        HashSet hashSet4 = new HashSet();
        for (String str9 : this.mEndMotionPath.attributes.keySet()) {
            if (this.mStartMotionPath.attributes.containsKey(str9)) {
                StringBuilder sb = new StringBuilder();
                sb.append(str2);
                sb.append(str9);
                if (!hashSet2.contains(sb.toString())) {
                    hashSet4.add(str9);
                }
            }
        }
        this.mAttributeNames = (String[]) hashSet4.toArray(new String[0]);
        this.mAttributeInterpCount = new int[this.mAttributeNames.length];
        int i5 = 0;
        while (true) {
            String[] strArr = this.mAttributeNames;
            if (i5 >= strArr.length) {
                break;
            }
            String str10 = strArr[i5];
            this.mAttributeInterpCount[i5] = 1;
            int i6 = 0;
            while (true) {
                if (i6 >= motionPathsArr.length) {
                    break;
                } else if (motionPathsArr[i5].attributes.containsKey(str10)) {
                    this.mAttributeInterpCount[i5] = ((ConstraintAttribute) motionPathsArr[i5].attributes.get(str10)).noOfInterpValues();
                    break;
                } else {
                    i6++;
                }
            }
            i5++;
        }
        boolean z = motionPathsArr[0].mPathMotionArc != C0188Key.UNSET;
        boolean[] zArr = new boolean[(18 + this.mAttributeNames.length)];
        for (int i7 = 1; i7 < motionPathsArr.length; i7++) {
            motionPathsArr[i7].different(motionPathsArr[i7 - 1], zArr, this.mAttributeNames, z);
        }
        int i8 = 0;
        for (int i9 = 1; i9 < zArr.length; i9++) {
            if (zArr[i9]) {
                i8++;
            }
        }
        this.mInterpolateVariables = new int[i8];
        int[] iArr = this.mInterpolateVariables;
        this.mInterpolateData = new double[iArr.length];
        this.mInterpolateVelocity = new double[iArr.length];
        int i10 = 0;
        for (int i11 = 1; i11 < zArr.length; i11++) {
            if (zArr[i11]) {
                int i12 = i10 + 1;
                this.mInterpolateVariables[i10] = i11;
                i10 = i12;
            }
        }
        double[][] dArr = (double[][]) Array.newInstance(double.class, new int[]{motionPathsArr.length, this.mInterpolateVariables.length});
        double[] dArr2 = new double[motionPathsArr.length];
        for (int i13 = 0; i13 < motionPathsArr.length; i13++) {
            motionPathsArr[i13].fillStandard(dArr[i13], this.mInterpolateVariables);
            dArr2[i13] = (double) motionPathsArr[i13].time;
        }
        int i14 = 0;
        while (true) {
            int[] iArr2 = this.mInterpolateVariables;
            if (i14 >= iArr2.length) {
                break;
            }
            if (iArr2[i14] < MotionPaths.names.length) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(MotionPaths.names[this.mInterpolateVariables[i14]]);
                sb2.append(" [");
                String sb3 = sb2.toString();
                for (int i15 = 0; i15 < motionPathsArr.length; i15++) {
                    StringBuilder sb4 = new StringBuilder();
                    sb4.append(sb3);
                    sb4.append(dArr[i15][i14]);
                    sb3 = sb4.toString();
                }
            }
            i14++;
        }
        this.mSpline = new CurveFit[(this.mAttributeNames.length + 1)];
        int i16 = 0;
        while (true) {
            String[] strArr2 = this.mAttributeNames;
            if (i16 >= strArr2.length) {
                break;
            }
            double[][] dArr3 = null;
            String str11 = strArr2[i16];
            double[] dArr4 = null;
            double[][] dArr5 = dArr3;
            int i17 = 0;
            for (int i18 = 0; i18 < motionPathsArr.length; i18++) {
                if (motionPathsArr[i18].hasCustomData(str11)) {
                    if (dArr5 == null) {
                        dArr4 = new double[motionPathsArr.length];
                        dArr5 = (double[][]) Array.newInstance(double.class, new int[]{motionPathsArr.length, motionPathsArr[i18].getCustomDataCount(str11)});
                    }
                    dArr4[i17] = (double) motionPathsArr[i18].time;
                    motionPathsArr[i18].getCustomData(str11, dArr5[i17], 0);
                    i17++;
                }
            }
            i16++;
            this.mSpline[i16] = CurveFit.get(this.mCurveFitType, Arrays.copyOf(dArr4, i17), (double[][]) Arrays.copyOf(dArr5, i17));
        }
        this.mSpline[0] = CurveFit.get(this.mCurveFitType, dArr2, dArr);
        if (motionPathsArr[0].mPathMotionArc != C0188Key.UNSET) {
            int length = motionPathsArr.length;
            int[] iArr3 = new int[length];
            double[] dArr6 = new double[length];
            double[][] dArr7 = (double[][]) Array.newInstance(double.class, new int[]{length, 2});
            for (int i19 = 0; i19 < length; i19++) {
                iArr3[i19] = motionPathsArr[i19].mPathMotionArc;
                dArr6[i19] = (double) motionPathsArr[i19].time;
                dArr7[i19][0] = (double) motionPathsArr[i19].f30x;
                dArr7[i19][1] = (double) motionPathsArr[i19].f31y;
            }
            this.mArcSpline = CurveFit.getArc(iArr3, dArr6, dArr7);
        }
        float f2 = Float.NaN;
        this.mCycleMap = new HashMap<>();
        if (this.mKeyList != null) {
            Iterator it9 = hashSet3.iterator();
            while (it9.hasNext()) {
                String str12 = (String) it9.next();
                KeyCycleOscillator makeSpline = KeyCycleOscillator.makeSpline(str12);
                if (makeSpline != null) {
                    if (makeSpline.variesByPath() && Float.isNaN(f2)) {
                        f2 = getPreCycleDistance();
                    }
                    makeSpline.setType(str12);
                    this.mCycleMap.put(str12, makeSpline);
                }
            }
            Iterator it10 = this.mKeyList.iterator();
            while (it10.hasNext()) {
                C0188Key key6 = (C0188Key) it10.next();
                if (key6 instanceof KeyCycle) {
                    ((KeyCycle) key6).addCycleValues(this.mCycleMap);
                }
            }
            for (KeyCycleOscillator upVar : this.mCycleMap.values()) {
                upVar.setup(f2);
            }
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(" start: x: ");
        sb.append(this.mStartMotionPath.f30x);
        String str = " y: ";
        sb.append(str);
        sb.append(this.mStartMotionPath.f31y);
        sb.append(" end: x: ");
        sb.append(this.mEndMotionPath.f30x);
        sb.append(str);
        sb.append(this.mEndMotionPath.f31y);
        return sb.toString();
    }

    private void readView(MotionPaths motionPaths) {
        motionPaths.setBounds((float) ((int) this.mView.getX()), (float) ((int) this.mView.getY()), (float) this.mView.getWidth(), (float) this.mView.getHeight());
    }

    public void setView(View view) {
        this.mView = view;
        this.mId = view.getId();
        LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof ConstraintLayout.LayoutParams) {
            this.mConstraintTag = ((ConstraintLayout.LayoutParams) layoutParams).getConstraintTag();
        }
    }

    /* access modifiers changed from: 0000 */
    public void setStartCurrentState(View view) {
        MotionPaths motionPaths = this.mStartMotionPath;
        motionPaths.time = 0.0f;
        motionPaths.position = 0.0f;
        motionPaths.setBounds(view.getX(), view.getY(), (float) view.getWidth(), (float) view.getHeight());
        this.mStartPoint.setState(view);
    }

    /* access modifiers changed from: 0000 */
    public void setStartState(ConstraintWidget constraintWidget, ConstraintSet constraintSet) {
        MotionPaths motionPaths = this.mStartMotionPath;
        motionPaths.time = 0.0f;
        motionPaths.position = 0.0f;
        readView(motionPaths);
        this.mStartMotionPath.setBounds((float) constraintWidget.getX(), (float) constraintWidget.getY(), (float) constraintWidget.getWidth(), (float) constraintWidget.getHeight());
        Constraint parameters = constraintSet.getParameters(this.mId);
        this.mStartMotionPath.applyParameters(parameters);
        this.mMotionStagger = parameters.motion.mMotionStagger;
        this.mStartPoint.setState(constraintWidget, constraintSet, this.mId);
    }

    /* access modifiers changed from: 0000 */
    public void setEndState(ConstraintWidget constraintWidget, ConstraintSet constraintSet) {
        MotionPaths motionPaths = this.mEndMotionPath;
        motionPaths.time = 1.0f;
        motionPaths.position = 1.0f;
        readView(motionPaths);
        this.mEndMotionPath.setBounds((float) constraintWidget.getX(), (float) constraintWidget.getY(), (float) constraintWidget.getWidth(), (float) constraintWidget.getHeight());
        this.mEndMotionPath.applyParameters(constraintSet.getParameters(this.mId));
        this.mEndPoint.setState(constraintWidget, constraintSet, this.mId);
    }

    private float getAdjustedPosition(float f, float[] fArr) {
        float f2 = 0.0f;
        float f3 = 1.0f;
        if (fArr != null) {
            fArr[0] = 1.0f;
        } else if (((double) this.mStaggerScale) != 1.0d) {
            if (f < this.mStaggerOffset) {
                f = 0.0f;
            }
            float f4 = this.mStaggerOffset;
            if (f > f4 && ((double) f) < 1.0d) {
                f = (f - f4) * this.mStaggerScale;
            }
        }
        Easing easing = this.mStartMotionPath.mKeyFrameEasing;
        float f5 = Float.NaN;
        Iterator it = this.mMotionPaths.iterator();
        while (it.hasNext()) {
            MotionPaths motionPaths = (MotionPaths) it.next();
            if (motionPaths.mKeyFrameEasing != null) {
                if (motionPaths.time < f) {
                    Easing easing2 = motionPaths.mKeyFrameEasing;
                    easing = easing2;
                    f2 = motionPaths.time;
                } else if (Float.isNaN(f5)) {
                    f5 = motionPaths.time;
                }
            }
        }
        if (easing != null) {
            if (!Float.isNaN(f5)) {
                f3 = f5;
            }
            float f6 = f3 - f2;
            double d = (double) ((f - f2) / f6);
            f = (((float) easing.get(d)) * f6) + f2;
            if (fArr != null) {
                fArr[0] = (float) easing.getDiff(d);
            }
        }
        return f;
    }

    /* access modifiers changed from: 0000 */
    public boolean interpolate(View view, float f, long j, KeyCache keyCache) {
        boolean z;
        PathRotate pathRotate;
        double d;
        View view2 = view;
        float adjustedPosition = getAdjustedPosition(f, null);
        HashMap<String, SplineSet> hashMap = this.mAttributesMap;
        if (hashMap != null) {
            for (SplineSet property : hashMap.values()) {
                property.setProperty(view2, adjustedPosition);
            }
        }
        HashMap<String, TimeCycleSplineSet> hashMap2 = this.mTimeCycleAttributesMap;
        if (hashMap2 != null) {
            pathRotate = null;
            boolean z2 = false;
            for (TimeCycleSplineSet timeCycleSplineSet : hashMap2.values()) {
                if (timeCycleSplineSet instanceof PathRotate) {
                    pathRotate = (PathRotate) timeCycleSplineSet;
                } else {
                    z2 |= timeCycleSplineSet.setProperty(view, adjustedPosition, j, keyCache);
                }
            }
            z = z2;
        } else {
            pathRotate = null;
            z = false;
        }
        CurveFit[] curveFitArr = this.mSpline;
        if (curveFitArr != null) {
            double d2 = (double) adjustedPosition;
            curveFitArr[0].getPos(d2, this.mInterpolateData);
            this.mSpline[0].getSlope(d2, this.mInterpolateVelocity);
            CurveFit curveFit = this.mArcSpline;
            if (curveFit != null) {
                double[] dArr = this.mInterpolateData;
                if (dArr.length > 0) {
                    curveFit.getPos(d2, dArr);
                    this.mArcSpline.getSlope(d2, this.mInterpolateVelocity);
                }
            }
            this.mStartMotionPath.setView(view, this.mInterpolateVariables, this.mInterpolateData, this.mInterpolateVelocity, null);
            HashMap<String, SplineSet> hashMap3 = this.mAttributesMap;
            if (hashMap3 != null) {
                for (SplineSet splineSet : hashMap3.values()) {
                    if (splineSet instanceof PathRotate) {
                        PathRotate pathRotate2 = (PathRotate) splineSet;
                        double[] dArr2 = this.mInterpolateVelocity;
                        d = d2;
                        pathRotate2.setPathRotate(view, adjustedPosition, dArr2[0], dArr2[1]);
                    } else {
                        d = d2;
                    }
                    d2 = d;
                }
            }
            double d3 = d2;
            if (pathRotate != null) {
                double[] dArr3 = this.mInterpolateVelocity;
                z = pathRotate.setPathRotate(view, keyCache, adjustedPosition, j, dArr3[0], dArr3[1]) | z;
            }
            int i = 1;
            while (true) {
                CurveFit[] curveFitArr2 = this.mSpline;
                if (i >= curveFitArr2.length) {
                    break;
                }
                curveFitArr2[i].getPos(d3, this.mValuesBuff);
                ((ConstraintAttribute) this.mStartMotionPath.attributes.get(this.mAttributeNames[i - 1])).setInterpolatedValue(view2, this.mValuesBuff);
                i++;
            }
            if (this.mStartPoint.mVisibilityMode == 0) {
                if (adjustedPosition <= 0.0f) {
                    view2.setVisibility(this.mStartPoint.visibility);
                } else if (adjustedPosition >= 1.0f) {
                    view2.setVisibility(this.mEndPoint.visibility);
                } else if (this.mEndPoint.visibility != this.mStartPoint.visibility) {
                    view2.setVisibility(0);
                }
            }
            if (this.mKeyTriggers != null) {
                int i2 = 0;
                while (true) {
                    KeyTrigger[] keyTriggerArr = this.mKeyTriggers;
                    if (i2 >= keyTriggerArr.length) {
                        break;
                    }
                    keyTriggerArr[i2].conditionallyFire(adjustedPosition, view2);
                    i2++;
                }
            }
        } else {
            float f2 = this.mStartMotionPath.f30x + ((this.mEndMotionPath.f30x - this.mStartMotionPath.f30x) * adjustedPosition) + 0.5f;
            int i3 = (int) f2;
            float f3 = this.mStartMotionPath.f31y + ((this.mEndMotionPath.f31y - this.mStartMotionPath.f31y) * adjustedPosition) + 0.5f;
            int i4 = (int) f3;
            int i5 = (int) (f2 + this.mStartMotionPath.width + ((this.mEndMotionPath.width - this.mStartMotionPath.width) * adjustedPosition));
            int i6 = (int) (f3 + this.mStartMotionPath.height + ((this.mEndMotionPath.height - this.mStartMotionPath.height) * adjustedPosition));
            int i7 = i5 - i3;
            int i8 = i6 - i4;
            if (!(this.mEndMotionPath.width == this.mStartMotionPath.width && this.mEndMotionPath.height == this.mStartMotionPath.height)) {
                view2.measure(MeasureSpec.makeMeasureSpec(i7, 1073741824), MeasureSpec.makeMeasureSpec(i8, 1073741824));
            }
            view2.layout(i3, i4, i5, i6);
        }
        HashMap<String, KeyCycleOscillator> hashMap4 = this.mCycleMap;
        if (hashMap4 != null) {
            for (KeyCycleOscillator keyCycleOscillator : hashMap4.values()) {
                if (keyCycleOscillator instanceof PathRotateSet) {
                    PathRotateSet pathRotateSet = (PathRotateSet) keyCycleOscillator;
                    double[] dArr4 = this.mInterpolateVelocity;
                    pathRotateSet.setPathRotate(view, adjustedPosition, dArr4[0], dArr4[1]);
                } else {
                    keyCycleOscillator.setProperty(view2, adjustedPosition);
                }
            }
        }
        return z;
    }

    /* access modifiers changed from: 0000 */
    public void getDpDt(float f, float f2, float f3, float[] fArr) {
        double[] dArr;
        float adjustedPosition = getAdjustedPosition(f, this.mVelocity);
        CurveFit[] curveFitArr = this.mSpline;
        int i = 0;
        if (curveFitArr != null) {
            double d = (double) adjustedPosition;
            curveFitArr[0].getSlope(d, this.mInterpolateVelocity);
            this.mSpline[0].getPos(d, this.mInterpolateData);
            float f4 = this.mVelocity[0];
            while (true) {
                dArr = this.mInterpolateVelocity;
                if (i >= dArr.length) {
                    break;
                }
                dArr[i] = dArr[i] * ((double) f4);
                i++;
            }
            CurveFit curveFit = this.mArcSpline;
            if (curveFit != null) {
                double[] dArr2 = this.mInterpolateData;
                if (dArr2.length > 0) {
                    curveFit.getPos(d, dArr2);
                    this.mArcSpline.getSlope(d, this.mInterpolateVelocity);
                    this.mStartMotionPath.setDpDt(f2, f3, fArr, this.mInterpolateVariables, this.mInterpolateVelocity, this.mInterpolateData);
                }
                return;
            }
            this.mStartMotionPath.setDpDt(f2, f3, fArr, this.mInterpolateVariables, dArr, this.mInterpolateData);
            return;
        }
        float f5 = this.mEndMotionPath.f30x - this.mStartMotionPath.f30x;
        float f6 = this.mEndMotionPath.f31y - this.mStartMotionPath.f31y;
        float f7 = (this.mEndMotionPath.height - this.mStartMotionPath.height) + f6;
        fArr[0] = (f5 * (1.0f - f2)) + (((this.mEndMotionPath.width - this.mStartMotionPath.width) + f5) * f2);
        fArr[1] = (f6 * (1.0f - f3)) + (f7 * f3);
    }

    /* access modifiers changed from: 0000 */
    public void getPostLayoutDvDp(float f, int i, int i2, float f2, float f3, float[] fArr) {
        float adjustedPosition = getAdjustedPosition(f, this.mVelocity);
        HashMap<String, SplineSet> hashMap = this.mAttributesMap;
        String str = "translationX";
        KeyCycleOscillator keyCycleOscillator = null;
        SplineSet splineSet = hashMap == null ? null : (SplineSet) hashMap.get(str);
        HashMap<String, SplineSet> hashMap2 = this.mAttributesMap;
        String str2 = "translationY";
        SplineSet splineSet2 = hashMap2 == null ? null : (SplineSet) hashMap2.get(str2);
        HashMap<String, SplineSet> hashMap3 = this.mAttributesMap;
        String str3 = "rotation";
        SplineSet splineSet3 = hashMap3 == null ? null : (SplineSet) hashMap3.get(str3);
        HashMap<String, SplineSet> hashMap4 = this.mAttributesMap;
        String str4 = "scaleX";
        SplineSet splineSet4 = hashMap4 == null ? null : (SplineSet) hashMap4.get(str4);
        HashMap<String, SplineSet> hashMap5 = this.mAttributesMap;
        String str5 = "scaleY";
        SplineSet splineSet5 = hashMap5 == null ? null : (SplineSet) hashMap5.get(str5);
        HashMap<String, KeyCycleOscillator> hashMap6 = this.mCycleMap;
        KeyCycleOscillator keyCycleOscillator2 = hashMap6 == null ? null : (KeyCycleOscillator) hashMap6.get(str);
        HashMap<String, KeyCycleOscillator> hashMap7 = this.mCycleMap;
        KeyCycleOscillator keyCycleOscillator3 = hashMap7 == null ? null : (KeyCycleOscillator) hashMap7.get(str2);
        HashMap<String, KeyCycleOscillator> hashMap8 = this.mCycleMap;
        KeyCycleOscillator keyCycleOscillator4 = hashMap8 == null ? null : (KeyCycleOscillator) hashMap8.get(str3);
        HashMap<String, KeyCycleOscillator> hashMap9 = this.mCycleMap;
        KeyCycleOscillator keyCycleOscillator5 = hashMap9 == null ? null : (KeyCycleOscillator) hashMap9.get(str4);
        HashMap<String, KeyCycleOscillator> hashMap10 = this.mCycleMap;
        if (hashMap10 != null) {
            keyCycleOscillator = (KeyCycleOscillator) hashMap10.get(str5);
        }
        VelocityMatrix velocityMatrix = new VelocityMatrix();
        velocityMatrix.clear();
        velocityMatrix.setRotationVelocity(splineSet3, adjustedPosition);
        velocityMatrix.setTranslationVelocity(splineSet, splineSet2, adjustedPosition);
        velocityMatrix.setScaleVelocity(splineSet4, splineSet5, adjustedPosition);
        velocityMatrix.setRotationVelocity(keyCycleOscillator4, adjustedPosition);
        velocityMatrix.setTranslationVelocity(keyCycleOscillator2, keyCycleOscillator3, adjustedPosition);
        velocityMatrix.setScaleVelocity(keyCycleOscillator5, keyCycleOscillator, adjustedPosition);
        CurveFit curveFit = this.mArcSpline;
        if (curveFit != null) {
            double[] dArr = this.mInterpolateData;
            if (dArr.length > 0) {
                double d = (double) adjustedPosition;
                curveFit.getPos(d, dArr);
                this.mArcSpline.getSlope(d, this.mInterpolateVelocity);
                this.mStartMotionPath.setDpDt(f2, f3, fArr, this.mInterpolateVariables, this.mInterpolateVelocity, this.mInterpolateData);
            }
            velocityMatrix.applyTransform(f2, f3, i, i2, fArr);
            return;
        }
        int i3 = 0;
        if (this.mSpline != null) {
            double adjustedPosition2 = (double) getAdjustedPosition(adjustedPosition, this.mVelocity);
            this.mSpline[0].getSlope(adjustedPosition2, this.mInterpolateVelocity);
            this.mSpline[0].getPos(adjustedPosition2, this.mInterpolateData);
            float f4 = this.mVelocity[0];
            while (true) {
                double[] dArr2 = this.mInterpolateVelocity;
                if (i3 < dArr2.length) {
                    dArr2[i3] = dArr2[i3] * ((double) f4);
                    i3++;
                } else {
                    float f5 = f2;
                    float f6 = f3;
                    this.mStartMotionPath.setDpDt(f5, f6, fArr, this.mInterpolateVariables, dArr2, this.mInterpolateData);
                    velocityMatrix.applyTransform(f5, f6, i, i2, fArr);
                    return;
                }
            }
        } else {
            float f7 = this.mEndMotionPath.f30x - this.mStartMotionPath.f30x;
            float f8 = this.mEndMotionPath.f31y - this.mStartMotionPath.f31y;
            KeyCycleOscillator keyCycleOscillator6 = keyCycleOscillator;
            KeyCycleOscillator keyCycleOscillator7 = keyCycleOscillator5;
            float f9 = (this.mEndMotionPath.height - this.mStartMotionPath.height) + f8;
            fArr[0] = (f7 * (1.0f - f2)) + (((this.mEndMotionPath.width - this.mStartMotionPath.width) + f7) * f2);
            fArr[1] = (f8 * (1.0f - f3)) + (f9 * f3);
            velocityMatrix.clear();
            velocityMatrix.setRotationVelocity(splineSet3, adjustedPosition);
            velocityMatrix.setTranslationVelocity(splineSet, splineSet2, adjustedPosition);
            velocityMatrix.setScaleVelocity(splineSet4, splineSet5, adjustedPosition);
            velocityMatrix.setRotationVelocity(keyCycleOscillator4, adjustedPosition);
            velocityMatrix.setTranslationVelocity(keyCycleOscillator2, keyCycleOscillator3, adjustedPosition);
            velocityMatrix.setScaleVelocity(keyCycleOscillator7, keyCycleOscillator6, adjustedPosition);
            velocityMatrix.applyTransform(f2, f3, i, i2, fArr);
        }
    }

    public int getDrawPath() {
        int i = this.mStartMotionPath.mDrawPath;
        Iterator it = this.mMotionPaths.iterator();
        while (it.hasNext()) {
            i = Math.max(i, ((MotionPaths) it.next()).mDrawPath);
        }
        return Math.max(i, this.mEndMotionPath.mDrawPath);
    }

    public void setDrawPath(int i) {
        this.mStartMotionPath.mDrawPath = i;
    }

    /* access modifiers changed from: 0000 */
    public String name() {
        return this.mView.getContext().getResources().getResourceEntryName(this.mView.getId());
    }

    /* access modifiers changed from: 0000 */
    public void positionKeyframe(View view, KeyPositionBase keyPositionBase, float f, float f2, String[] strArr, float[] fArr) {
        RectF rectF = new RectF();
        rectF.left = this.mStartMotionPath.f30x;
        rectF.top = this.mStartMotionPath.f31y;
        rectF.right = rectF.left + this.mStartMotionPath.width;
        rectF.bottom = rectF.top + this.mStartMotionPath.height;
        RectF rectF2 = new RectF();
        rectF2.left = this.mEndMotionPath.f30x;
        rectF2.top = this.mEndMotionPath.f31y;
        rectF2.right = rectF2.left + this.mEndMotionPath.width;
        rectF2.bottom = rectF2.top + this.mEndMotionPath.height;
        keyPositionBase.positionAttributes(view, rectF, rectF2, f, f2, strArr, fArr);
    }

    public int getkeyFramePositions(int[] iArr, float[] fArr) {
        Iterator it = this.mKeyList.iterator();
        int i = 0;
        int i2 = 0;
        while (it.hasNext()) {
            C0188Key key = (C0188Key) it.next();
            int i3 = i + 1;
            iArr[i] = key.mFramePosition + (key.mType * 1000);
            this.mSpline[0].getPos((double) (((float) key.mFramePosition) / 100.0f), this.mInterpolateData);
            this.mStartMotionPath.getCenter(this.mInterpolateVariables, this.mInterpolateData, fArr, i2);
            i2 += 2;
            i = i3;
        }
        return i;
    }
}
