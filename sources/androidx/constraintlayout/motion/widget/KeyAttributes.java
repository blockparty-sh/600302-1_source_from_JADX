package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import androidx.constraintlayout.widget.C0215R;
import androidx.core.app.NotificationCompat;
import java.util.HashMap;
import java.util.HashSet;

public class KeyAttributes extends C0188Key {
    public static final int KEY_TYPE = 1;
    static final String NAME = "KeyAttribute";
    private static final String TAG = "KeyAttribute";
    /* access modifiers changed from: private */
    public float mAlpha;
    /* access modifiers changed from: private */
    public int mCurveFit;
    /* access modifiers changed from: private */
    public float mElevation;
    /* access modifiers changed from: private */
    public float mProgress;
    /* access modifiers changed from: private */
    public float mRotation;
    /* access modifiers changed from: private */
    public float mRotationX;
    /* access modifiers changed from: private */
    public float mRotationY;
    /* access modifiers changed from: private */
    public float mScaleX;
    /* access modifiers changed from: private */
    public float mScaleY;
    /* access modifiers changed from: private */
    public String mTransitionEasing;
    /* access modifiers changed from: private */
    public float mTransitionPathRotate;
    /* access modifiers changed from: private */
    public float mTranslationX;
    /* access modifiers changed from: private */
    public float mTranslationY;
    /* access modifiers changed from: private */
    public float mTranslationZ;
    /* access modifiers changed from: private */
    public boolean mVisibility;

    private static class Loader {
        private static final int ANDROID_ALPHA = 1;
        private static final int ANDROID_ELEVATION = 2;
        private static final int ANDROID_ROTATION = 4;
        private static final int ANDROID_ROTATION_X = 5;
        private static final int ANDROID_ROTATION_Y = 6;
        private static final int ANDROID_SCALE_X = 7;
        private static final int ANDROID_SCALE_Y = 15;
        private static final int ANDROID_TRANSLATION_X = 16;
        private static final int ANDROID_TRANSLATION_Y = 17;
        private static final int ANDROID_TRANSLATION_Z = 18;
        private static final int ANDROID_VISIBILITY = 14;
        private static final int CURVE_FIT = 13;
        private static final int FRAME_POSITION = 12;
        private static final int PROGRESS = 19;
        private static final int TARGET_ID = 10;
        private static final int TRANSITION_EASING = 9;
        private static final int TRANSITION_PATH_ROTATE = 8;
        private static SparseIntArray mAttrMap = new SparseIntArray();

        private Loader() {
        }

        static {
            mAttrMap.append(C0215R.styleable.KeyAttribute_android_alpha, 1);
            mAttrMap.append(C0215R.styleable.KeyAttribute_android_elevation, 2);
            mAttrMap.append(C0215R.styleable.KeyAttribute_android_rotation, 4);
            mAttrMap.append(C0215R.styleable.KeyAttribute_android_rotationX, 5);
            mAttrMap.append(C0215R.styleable.KeyAttribute_android_rotationY, 6);
            mAttrMap.append(C0215R.styleable.KeyAttribute_android_scaleX, 7);
            mAttrMap.append(C0215R.styleable.KeyAttribute_transitionPathRotate, 8);
            mAttrMap.append(C0215R.styleable.KeyAttribute_transitionEasing, 9);
            mAttrMap.append(C0215R.styleable.KeyAttribute_motionTarget, 10);
            mAttrMap.append(C0215R.styleable.KeyAttribute_framePosition, 12);
            mAttrMap.append(C0215R.styleable.KeyAttribute_curveFit, 13);
            mAttrMap.append(C0215R.styleable.KeyAttribute_android_visibility, 14);
            mAttrMap.append(C0215R.styleable.KeyAttribute_android_scaleY, 15);
            mAttrMap.append(C0215R.styleable.KeyAttribute_android_translationX, 16);
            mAttrMap.append(C0215R.styleable.KeyAttribute_android_translationY, 17);
            mAttrMap.append(C0215R.styleable.KeyAttribute_android_translationZ, 18);
            mAttrMap.append(C0215R.styleable.KeyAttribute_motionProgress, 19);
        }

        public static void read(KeyAttributes keyAttributes, TypedArray typedArray) {
            int indexCount = typedArray.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArray.getIndex(i);
                switch (mAttrMap.get(index)) {
                    case 1:
                        keyAttributes.mAlpha = typedArray.getFloat(index, keyAttributes.mAlpha);
                        break;
                    case 2:
                        keyAttributes.mElevation = typedArray.getDimension(index, keyAttributes.mElevation);
                        break;
                    case 4:
                        keyAttributes.mRotation = typedArray.getFloat(index, keyAttributes.mRotation);
                        break;
                    case 5:
                        keyAttributes.mRotationX = typedArray.getFloat(index, keyAttributes.mRotationX);
                        break;
                    case 6:
                        keyAttributes.mRotationY = typedArray.getFloat(index, keyAttributes.mRotationY);
                        break;
                    case 7:
                        keyAttributes.mScaleX = typedArray.getFloat(index, keyAttributes.mScaleX);
                        break;
                    case 8:
                        keyAttributes.mTransitionPathRotate = typedArray.getFloat(index, keyAttributes.mTransitionPathRotate);
                        break;
                    case 9:
                        keyAttributes.mTransitionEasing = typedArray.getString(index);
                        break;
                    case 10:
                        if (!MotionLayout.IS_IN_EDIT_MODE) {
                            if (typedArray.peekValue(index).type != 3) {
                                keyAttributes.mTargetId = typedArray.getResourceId(index, keyAttributes.mTargetId);
                                break;
                            } else {
                                keyAttributes.mTargetString = typedArray.getString(index);
                                break;
                            }
                        } else {
                            keyAttributes.mTargetId = typedArray.getResourceId(index, keyAttributes.mTargetId);
                            if (keyAttributes.mTargetId != -1) {
                                break;
                            } else {
                                keyAttributes.mTargetString = typedArray.getString(index);
                                break;
                            }
                        }
                    case 12:
                        keyAttributes.mFramePosition = typedArray.getInt(index, keyAttributes.mFramePosition);
                        break;
                    case 13:
                        keyAttributes.mCurveFit = typedArray.getInteger(index, keyAttributes.mCurveFit);
                        break;
                    case 14:
                        keyAttributes.mVisibility = typedArray.getBoolean(index, keyAttributes.mVisibility);
                        break;
                    case 15:
                        keyAttributes.mScaleY = typedArray.getFloat(index, keyAttributes.mScaleY);
                        break;
                    case 16:
                        keyAttributes.mTranslationX = typedArray.getDimension(index, keyAttributes.mTranslationX);
                        break;
                    case 17:
                        keyAttributes.mTranslationY = typedArray.getDimension(index, keyAttributes.mTranslationY);
                        break;
                    case 18:
                        if (VERSION.SDK_INT < 21) {
                            break;
                        } else {
                            keyAttributes.mTranslationZ = typedArray.getDimension(index, keyAttributes.mTranslationZ);
                            break;
                        }
                    case 19:
                        keyAttributes.mProgress = typedArray.getFloat(index, keyAttributes.mProgress);
                        break;
                    default:
                        StringBuilder sb = new StringBuilder();
                        sb.append("unused attribute 0x");
                        sb.append(Integer.toHexString(index));
                        sb.append("   ");
                        sb.append(mAttrMap.get(index));
                        Log.e("KeyAttribute", sb.toString());
                        break;
                }
            }
        }
    }

    public KeyAttributes() {
        this.mCurveFit = -1;
        this.mVisibility = false;
        this.mAlpha = Float.NaN;
        this.mElevation = Float.NaN;
        this.mRotation = Float.NaN;
        this.mRotationX = Float.NaN;
        this.mRotationY = Float.NaN;
        this.mTransitionPathRotate = Float.NaN;
        this.mScaleX = Float.NaN;
        this.mScaleY = Float.NaN;
        this.mTranslationX = Float.NaN;
        this.mTranslationY = Float.NaN;
        this.mTranslationZ = Float.NaN;
        this.mProgress = Float.NaN;
        this.mType = 1;
        this.mCustomConstraints = new HashMap();
    }

    public void load(Context context, AttributeSet attributeSet) {
        Loader.read(this, context.obtainStyledAttributes(attributeSet, C0215R.styleable.KeyAttribute));
    }

    /* access modifiers changed from: 0000 */
    public int getCurveFit() {
        return this.mCurveFit;
    }

    public void getAttributeNames(HashSet<String> hashSet) {
        if (!Float.isNaN(this.mAlpha)) {
            hashSet.add("alpha");
        }
        if (!Float.isNaN(this.mElevation)) {
            hashSet.add("elevation");
        }
        if (!Float.isNaN(this.mRotation)) {
            hashSet.add("rotation");
        }
        if (!Float.isNaN(this.mRotationX)) {
            hashSet.add("rotationX");
        }
        if (!Float.isNaN(this.mRotationY)) {
            hashSet.add("rotationY");
        }
        if (!Float.isNaN(this.mTranslationX)) {
            hashSet.add("translationX");
        }
        if (!Float.isNaN(this.mTranslationY)) {
            hashSet.add("translationY");
        }
        if (!Float.isNaN(this.mTranslationZ)) {
            hashSet.add("translationZ");
        }
        if (!Float.isNaN(this.mTransitionPathRotate)) {
            hashSet.add("transitionPathRotate");
        }
        if (!Float.isNaN(this.mScaleX)) {
            hashSet.add("scaleX");
        }
        if (!Float.isNaN(this.mScaleX)) {
            hashSet.add("scaleY");
        }
        if (!Float.isNaN(this.mProgress)) {
            hashSet.add(NotificationCompat.CATEGORY_PROGRESS);
        }
        if (this.mCustomConstraints.size() > 0) {
            for (String str : this.mCustomConstraints.keySet()) {
                StringBuilder sb = new StringBuilder();
                sb.append("CUSTOM,");
                sb.append(str);
                hashSet.add(sb.toString());
            }
        }
    }

    public void setInterpolation(HashMap<String, Integer> hashMap) {
        if (this.mCurveFit != -1) {
            if (!Float.isNaN(this.mAlpha)) {
                hashMap.put("alpha", Integer.valueOf(this.mCurveFit));
            }
            if (!Float.isNaN(this.mElevation)) {
                hashMap.put("elevation", Integer.valueOf(this.mCurveFit));
            }
            if (!Float.isNaN(this.mRotation)) {
                hashMap.put("rotation", Integer.valueOf(this.mCurveFit));
            }
            if (!Float.isNaN(this.mRotationX)) {
                hashMap.put("rotationX", Integer.valueOf(this.mCurveFit));
            }
            if (!Float.isNaN(this.mRotationY)) {
                hashMap.put("rotationY", Integer.valueOf(this.mCurveFit));
            }
            if (!Float.isNaN(this.mTranslationX)) {
                hashMap.put("translationX", Integer.valueOf(this.mCurveFit));
            }
            if (!Float.isNaN(this.mTranslationY)) {
                hashMap.put("translationY", Integer.valueOf(this.mCurveFit));
            }
            if (!Float.isNaN(this.mTranslationZ)) {
                hashMap.put("translationZ", Integer.valueOf(this.mCurveFit));
            }
            if (!Float.isNaN(this.mTransitionPathRotate)) {
                hashMap.put("transitionPathRotate", Integer.valueOf(this.mCurveFit));
            }
            if (!Float.isNaN(this.mScaleX)) {
                hashMap.put("scaleX", Integer.valueOf(this.mCurveFit));
            }
            if (!Float.isNaN(this.mScaleY)) {
                hashMap.put("scaleY", Integer.valueOf(this.mCurveFit));
            }
            if (!Float.isNaN(this.mProgress)) {
                hashMap.put(NotificationCompat.CATEGORY_PROGRESS, Integer.valueOf(this.mCurveFit));
            }
            if (this.mCustomConstraints.size() > 0) {
                for (String str : this.mCustomConstraints.keySet()) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("CUSTOM,");
                    sb.append(str);
                    hashMap.put(sb.toString(), Integer.valueOf(this.mCurveFit));
                }
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0073, code lost:
        if (r1.equals("scaleY") != false) goto L_0x00c1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void addValues(java.util.HashMap<java.lang.String, androidx.constraintlayout.motion.widget.SplineSet> r7) {
        /*
            r6 = this;
            java.util.Set r0 = r7.keySet()
            java.util.Iterator r0 = r0.iterator()
        L_0x0008:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x01ad
            java.lang.Object r1 = r0.next()
            java.lang.String r1 = (java.lang.String) r1
            java.lang.Object r2 = r7.get(r1)
            androidx.constraintlayout.motion.widget.SplineSet r2 = (androidx.constraintlayout.motion.widget.SplineSet) r2
            java.lang.String r3 = "CUSTOM"
            boolean r3 = r1.startsWith(r3)
            r4 = 7
            if (r3 == 0) goto L_0x0039
            java.lang.String r1 = r1.substring(r4)
            java.util.HashMap r3 = r6.mCustomConstraints
            java.lang.Object r1 = r3.get(r1)
            androidx.constraintlayout.widget.ConstraintAttribute r1 = (androidx.constraintlayout.widget.ConstraintAttribute) r1
            if (r1 == 0) goto L_0x0008
            androidx.constraintlayout.motion.widget.SplineSet$CustomSet r2 = (androidx.constraintlayout.motion.widget.SplineSet.CustomSet) r2
            int r3 = r6.mFramePosition
            r2.setPoint(r3, r1)
            goto L_0x0008
        L_0x0039:
            r3 = -1
            int r5 = r1.hashCode()
            switch(r5) {
                case -1249320806: goto L_0x00b6;
                case -1249320805: goto L_0x00ac;
                case -1225497657: goto L_0x00a1;
                case -1225497656: goto L_0x0096;
                case -1225497655: goto L_0x008b;
                case -1001078227: goto L_0x0080;
                case -908189618: goto L_0x0076;
                case -908189617: goto L_0x006d;
                case -40300674: goto L_0x0063;
                case -4379043: goto L_0x0059;
                case 37232917: goto L_0x004e;
                case 92909918: goto L_0x0043;
                default: goto L_0x0041;
            }
        L_0x0041:
            goto L_0x00c0
        L_0x0043:
            java.lang.String r4 = "alpha"
            boolean r4 = r1.equals(r4)
            if (r4 == 0) goto L_0x00c0
            r4 = 0
            goto L_0x00c1
        L_0x004e:
            java.lang.String r4 = "transitionPathRotate"
            boolean r4 = r1.equals(r4)
            if (r4 == 0) goto L_0x00c0
            r4 = 5
            goto L_0x00c1
        L_0x0059:
            java.lang.String r4 = "elevation"
            boolean r4 = r1.equals(r4)
            if (r4 == 0) goto L_0x00c0
            r4 = 1
            goto L_0x00c1
        L_0x0063:
            java.lang.String r4 = "rotation"
            boolean r4 = r1.equals(r4)
            if (r4 == 0) goto L_0x00c0
            r4 = 2
            goto L_0x00c1
        L_0x006d:
            java.lang.String r5 = "scaleY"
            boolean r5 = r1.equals(r5)
            if (r5 == 0) goto L_0x00c0
            goto L_0x00c1
        L_0x0076:
            java.lang.String r4 = "scaleX"
            boolean r4 = r1.equals(r4)
            if (r4 == 0) goto L_0x00c0
            r4 = 6
            goto L_0x00c1
        L_0x0080:
            java.lang.String r4 = "progress"
            boolean r4 = r1.equals(r4)
            if (r4 == 0) goto L_0x00c0
            r4 = 11
            goto L_0x00c1
        L_0x008b:
            java.lang.String r4 = "translationZ"
            boolean r4 = r1.equals(r4)
            if (r4 == 0) goto L_0x00c0
            r4 = 10
            goto L_0x00c1
        L_0x0096:
            java.lang.String r4 = "translationY"
            boolean r4 = r1.equals(r4)
            if (r4 == 0) goto L_0x00c0
            r4 = 9
            goto L_0x00c1
        L_0x00a1:
            java.lang.String r4 = "translationX"
            boolean r4 = r1.equals(r4)
            if (r4 == 0) goto L_0x00c0
            r4 = 8
            goto L_0x00c1
        L_0x00ac:
            java.lang.String r4 = "rotationY"
            boolean r4 = r1.equals(r4)
            if (r4 == 0) goto L_0x00c0
            r4 = 4
            goto L_0x00c1
        L_0x00b6:
            java.lang.String r4 = "rotationX"
            boolean r4 = r1.equals(r4)
            if (r4 == 0) goto L_0x00c0
            r4 = 3
            goto L_0x00c1
        L_0x00c0:
            r4 = -1
        L_0x00c1:
            switch(r4) {
                case 0: goto L_0x019c;
                case 1: goto L_0x018b;
                case 2: goto L_0x017a;
                case 3: goto L_0x0169;
                case 4: goto L_0x0158;
                case 5: goto L_0x0147;
                case 6: goto L_0x0136;
                case 7: goto L_0x0125;
                case 8: goto L_0x0114;
                case 9: goto L_0x0103;
                case 10: goto L_0x00f2;
                case 11: goto L_0x00e1;
                default: goto L_0x00c4;
            }
        L_0x00c4:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "UNKNOWN addValues \""
            r2.append(r3)
            r2.append(r1)
            java.lang.String r1 = "\""
            r2.append(r1)
            java.lang.String r1 = r2.toString()
            java.lang.String r2 = "KeyAttributes"
            android.util.Log.v(r2, r1)
            goto L_0x0008
        L_0x00e1:
            float r1 = r6.mProgress
            boolean r1 = java.lang.Float.isNaN(r1)
            if (r1 != 0) goto L_0x0008
            int r1 = r6.mFramePosition
            float r3 = r6.mProgress
            r2.setPoint(r1, r3)
            goto L_0x0008
        L_0x00f2:
            float r1 = r6.mTranslationZ
            boolean r1 = java.lang.Float.isNaN(r1)
            if (r1 != 0) goto L_0x0008
            int r1 = r6.mFramePosition
            float r3 = r6.mTranslationZ
            r2.setPoint(r1, r3)
            goto L_0x0008
        L_0x0103:
            float r1 = r6.mTranslationY
            boolean r1 = java.lang.Float.isNaN(r1)
            if (r1 != 0) goto L_0x0008
            int r1 = r6.mFramePosition
            float r3 = r6.mTranslationY
            r2.setPoint(r1, r3)
            goto L_0x0008
        L_0x0114:
            float r1 = r6.mTranslationX
            boolean r1 = java.lang.Float.isNaN(r1)
            if (r1 != 0) goto L_0x0008
            int r1 = r6.mFramePosition
            float r3 = r6.mTranslationX
            r2.setPoint(r1, r3)
            goto L_0x0008
        L_0x0125:
            float r1 = r6.mScaleY
            boolean r1 = java.lang.Float.isNaN(r1)
            if (r1 != 0) goto L_0x0008
            int r1 = r6.mFramePosition
            float r3 = r6.mScaleY
            r2.setPoint(r1, r3)
            goto L_0x0008
        L_0x0136:
            float r1 = r6.mScaleX
            boolean r1 = java.lang.Float.isNaN(r1)
            if (r1 != 0) goto L_0x0008
            int r1 = r6.mFramePosition
            float r3 = r6.mScaleX
            r2.setPoint(r1, r3)
            goto L_0x0008
        L_0x0147:
            float r1 = r6.mTransitionPathRotate
            boolean r1 = java.lang.Float.isNaN(r1)
            if (r1 != 0) goto L_0x0008
            int r1 = r6.mFramePosition
            float r3 = r6.mTransitionPathRotate
            r2.setPoint(r1, r3)
            goto L_0x0008
        L_0x0158:
            float r1 = r6.mRotationY
            boolean r1 = java.lang.Float.isNaN(r1)
            if (r1 != 0) goto L_0x0008
            int r1 = r6.mFramePosition
            float r3 = r6.mRotationY
            r2.setPoint(r1, r3)
            goto L_0x0008
        L_0x0169:
            float r1 = r6.mRotationX
            boolean r1 = java.lang.Float.isNaN(r1)
            if (r1 != 0) goto L_0x0008
            int r1 = r6.mFramePosition
            float r3 = r6.mRotationX
            r2.setPoint(r1, r3)
            goto L_0x0008
        L_0x017a:
            float r1 = r6.mRotation
            boolean r1 = java.lang.Float.isNaN(r1)
            if (r1 != 0) goto L_0x0008
            int r1 = r6.mFramePosition
            float r3 = r6.mRotation
            r2.setPoint(r1, r3)
            goto L_0x0008
        L_0x018b:
            float r1 = r6.mElevation
            boolean r1 = java.lang.Float.isNaN(r1)
            if (r1 != 0) goto L_0x0008
            int r1 = r6.mFramePosition
            float r3 = r6.mElevation
            r2.setPoint(r1, r3)
            goto L_0x0008
        L_0x019c:
            float r1 = r6.mAlpha
            boolean r1 = java.lang.Float.isNaN(r1)
            if (r1 != 0) goto L_0x0008
            int r1 = r6.mFramePosition
            float r3 = r6.mAlpha
            r2.setPoint(r1, r3)
            goto L_0x0008
        L_0x01ad:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.KeyAttributes.addValues(java.util.HashMap):void");
    }

    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setValue(java.lang.String r2, java.lang.Object r3) {
        /*
            r1 = this;
            int r0 = r2.hashCode()
            switch(r0) {
                case -1913008125: goto L_0x00a1;
                case -1812823328: goto L_0x0096;
                case -1249320806: goto L_0x008c;
                case -1249320805: goto L_0x0082;
                case -1225497657: goto L_0x0077;
                case -1225497656: goto L_0x006c;
                case -908189618: goto L_0x0062;
                case -908189617: goto L_0x0057;
                case -40300674: goto L_0x004d;
                case -4379043: goto L_0x0043;
                case 37232917: goto L_0x0037;
                case 92909918: goto L_0x002c;
                case 579057826: goto L_0x0021;
                case 1317633238: goto L_0x0015;
                case 1941332754: goto L_0x0009;
                default: goto L_0x0007;
            }
        L_0x0007:
            goto L_0x00ab
        L_0x0009:
            java.lang.String r0 = "visibility"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x00ab
            r2 = 10
            goto L_0x00ac
        L_0x0015:
            java.lang.String r0 = "mTranslationZ"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x00ab
            r2 = 14
            goto L_0x00ac
        L_0x0021:
            java.lang.String r0 = "curveFit"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x00ab
            r2 = 1
            goto L_0x00ac
        L_0x002c:
            java.lang.String r0 = "alpha"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x00ab
            r2 = 0
            goto L_0x00ac
        L_0x0037:
            java.lang.String r0 = "transitionPathRotate"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x00ab
            r2 = 11
            goto L_0x00ac
        L_0x0043:
            java.lang.String r0 = "elevation"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x00ab
            r2 = 2
            goto L_0x00ac
        L_0x004d:
            java.lang.String r0 = "rotation"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x00ab
            r2 = 4
            goto L_0x00ac
        L_0x0057:
            java.lang.String r0 = "scaleY"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x00ab
            r2 = 8
            goto L_0x00ac
        L_0x0062:
            java.lang.String r0 = "scaleX"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x00ab
            r2 = 7
            goto L_0x00ac
        L_0x006c:
            java.lang.String r0 = "translationY"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x00ab
            r2 = 13
            goto L_0x00ac
        L_0x0077:
            java.lang.String r0 = "translationX"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x00ab
            r2 = 12
            goto L_0x00ac
        L_0x0082:
            java.lang.String r0 = "rotationY"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x00ab
            r2 = 6
            goto L_0x00ac
        L_0x008c:
            java.lang.String r0 = "rotationX"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x00ab
            r2 = 5
            goto L_0x00ac
        L_0x0096:
            java.lang.String r0 = "transitionEasing"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x00ab
            r2 = 9
            goto L_0x00ac
        L_0x00a1:
            java.lang.String r0 = "motionProgress"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x00ab
            r2 = 3
            goto L_0x00ac
        L_0x00ab:
            r2 = -1
        L_0x00ac:
            switch(r2) {
                case 0: goto L_0x0113;
                case 1: goto L_0x010c;
                case 2: goto L_0x0105;
                case 3: goto L_0x00fe;
                case 4: goto L_0x00f7;
                case 5: goto L_0x00f0;
                case 6: goto L_0x00e9;
                case 7: goto L_0x00e2;
                case 8: goto L_0x00db;
                case 9: goto L_0x00d4;
                case 10: goto L_0x00cd;
                case 11: goto L_0x00c6;
                case 12: goto L_0x00bf;
                case 13: goto L_0x00b8;
                case 14: goto L_0x00b1;
                default: goto L_0x00af;
            }
        L_0x00af:
            goto L_0x0119
        L_0x00b1:
            float r2 = r1.toFloat(r3)
            r1.mTranslationZ = r2
            goto L_0x0119
        L_0x00b8:
            float r2 = r1.toFloat(r3)
            r1.mTranslationY = r2
            goto L_0x0119
        L_0x00bf:
            float r2 = r1.toFloat(r3)
            r1.mTranslationX = r2
            goto L_0x0119
        L_0x00c6:
            float r2 = r1.toFloat(r3)
            r1.mTransitionPathRotate = r2
            goto L_0x0119
        L_0x00cd:
            boolean r2 = r1.toBoolean(r3)
            r1.mVisibility = r2
            goto L_0x0119
        L_0x00d4:
            java.lang.String r2 = r3.toString()
            r1.mTransitionEasing = r2
            goto L_0x0119
        L_0x00db:
            float r2 = r1.toFloat(r3)
            r1.mScaleY = r2
            goto L_0x0119
        L_0x00e2:
            float r2 = r1.toFloat(r3)
            r1.mScaleX = r2
            goto L_0x0119
        L_0x00e9:
            float r2 = r1.toFloat(r3)
            r1.mRotationY = r2
            goto L_0x0119
        L_0x00f0:
            float r2 = r1.toFloat(r3)
            r1.mRotationX = r2
            goto L_0x0119
        L_0x00f7:
            float r2 = r1.toFloat(r3)
            r1.mRotation = r2
            goto L_0x0119
        L_0x00fe:
            float r2 = r1.toFloat(r3)
            r1.mProgress = r2
            goto L_0x0119
        L_0x0105:
            float r2 = r1.toFloat(r3)
            r1.mElevation = r2
            goto L_0x0119
        L_0x010c:
            int r2 = r1.toInt(r3)
            r1.mCurveFit = r2
            goto L_0x0119
        L_0x0113:
            float r2 = r1.toFloat(r3)
            r1.mAlpha = r2
        L_0x0119:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.KeyAttributes.setValue(java.lang.String, java.lang.Object):void");
    }
}
