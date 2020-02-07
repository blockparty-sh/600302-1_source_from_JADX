package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.motion.utils.Easing;
import androidx.constraintlayout.widget.C0215R;
import java.util.HashMap;

public class KeyPosition extends KeyPositionBase {
    static final int KEY_TYPE = 2;
    static final String NAME = "KeyPosition";
    private static final String PERCENT_X = "percentX";
    private static final String PERCENT_Y = "percentY";
    private static final String TAG = "KeyPosition";
    public static final int TYPE_CARTESIAN = 0;
    public static final int TYPE_PATH = 1;
    public static final int TYPE_SCREEN = 2;
    float mAltPercentX;
    float mAltPercentY;
    private float mCalculatedPositionX;
    private float mCalculatedPositionY;
    int mDrawPath;
    int mPathMotionArc;
    float mPercentHeight;
    float mPercentWidth;
    float mPercentX;
    float mPercentY;
    int mPositionType;
    String mTransitionEasing;

    private static class Loader {
        private static final int CURVE_FIT = 4;
        private static final int DRAW_PATH = 5;
        private static final int FRAME_POSITION = 2;
        private static final int PATH_MOTION_ARC = 10;
        private static final int PERCENT_HEIGHT = 12;
        private static final int PERCENT_WIDTH = 11;
        private static final int PERCENT_X = 6;
        private static final int PERCENT_Y = 7;
        private static final int SIZE_PERCENT = 8;
        private static final int TARGET_ID = 1;
        private static final int TRANSITION_EASING = 3;
        private static final int TYPE = 9;
        private static SparseIntArray mAttrMap = new SparseIntArray();

        private Loader() {
        }

        static {
            mAttrMap.append(C0215R.styleable.KeyPosition_motionTarget, 1);
            mAttrMap.append(C0215R.styleable.KeyPosition_framePosition, 2);
            mAttrMap.append(C0215R.styleable.KeyPosition_transitionEasing, 3);
            mAttrMap.append(C0215R.styleable.KeyPosition_curveFit, 4);
            mAttrMap.append(C0215R.styleable.KeyPosition_drawPath, 5);
            mAttrMap.append(C0215R.styleable.KeyPosition_percentX, 6);
            mAttrMap.append(C0215R.styleable.KeyPosition_percentY, 7);
            mAttrMap.append(C0215R.styleable.KeyPosition_keyPositionType, 9);
            mAttrMap.append(C0215R.styleable.KeyPosition_sizePercent, 8);
            mAttrMap.append(C0215R.styleable.KeyPosition_percentWidth, 11);
            mAttrMap.append(C0215R.styleable.KeyPosition_percentHeight, 12);
            mAttrMap.append(C0215R.styleable.KeyPosition_pathMotionArc, 10);
        }

        /* access modifiers changed from: private */
        public static void read(KeyPosition keyPosition, TypedArray typedArray) {
            int indexCount = typedArray.getIndexCount();
            int i = 0;
            while (true) {
                String str = "KeyPosition";
                if (i < indexCount) {
                    int index = typedArray.getIndex(i);
                    switch (mAttrMap.get(index)) {
                        case 1:
                            if (!MotionLayout.IS_IN_EDIT_MODE) {
                                if (typedArray.peekValue(index).type != 3) {
                                    keyPosition.mTargetId = typedArray.getResourceId(index, keyPosition.mTargetId);
                                    break;
                                } else {
                                    keyPosition.mTargetString = typedArray.getString(index);
                                    break;
                                }
                            } else {
                                keyPosition.mTargetId = typedArray.getResourceId(index, keyPosition.mTargetId);
                                if (keyPosition.mTargetId != -1) {
                                    break;
                                } else {
                                    keyPosition.mTargetString = typedArray.getString(index);
                                    break;
                                }
                            }
                        case 2:
                            keyPosition.mFramePosition = typedArray.getInt(index, keyPosition.mFramePosition);
                            break;
                        case 3:
                            if (typedArray.peekValue(index).type != 3) {
                                keyPosition.mTransitionEasing = Easing.NAMED_EASING[typedArray.getInteger(index, 0)];
                                break;
                            } else {
                                keyPosition.mTransitionEasing = typedArray.getString(index);
                                break;
                            }
                        case 4:
                            keyPosition.mCurveFit = typedArray.getInteger(index, keyPosition.mCurveFit);
                            break;
                        case 5:
                            keyPosition.mDrawPath = typedArray.getInt(index, keyPosition.mDrawPath);
                            break;
                        case 6:
                            keyPosition.mPercentX = typedArray.getFloat(index, keyPosition.mPercentX);
                            break;
                        case 7:
                            keyPosition.mPercentY = typedArray.getFloat(index, keyPosition.mPercentY);
                            break;
                        case 8:
                            float f = typedArray.getFloat(index, keyPosition.mPercentHeight);
                            keyPosition.mPercentWidth = f;
                            keyPosition.mPercentHeight = f;
                            break;
                        case 9:
                            keyPosition.mPositionType = typedArray.getInt(index, keyPosition.mPositionType);
                            break;
                        case 10:
                            keyPosition.mPathMotionArc = typedArray.getInt(index, keyPosition.mPathMotionArc);
                            break;
                        case 11:
                            keyPosition.mPercentWidth = typedArray.getFloat(index, keyPosition.mPercentWidth);
                            break;
                        case 12:
                            keyPosition.mPercentHeight = typedArray.getFloat(index, keyPosition.mPercentHeight);
                            break;
                        default:
                            StringBuilder sb = new StringBuilder();
                            sb.append("unused attribute 0x");
                            sb.append(Integer.toHexString(index));
                            sb.append("   ");
                            sb.append(mAttrMap.get(index));
                            Log.e(str, sb.toString());
                            break;
                    }
                    i++;
                } else if (keyPosition.mFramePosition == -1) {
                    Log.e(str, "no frame position");
                    return;
                } else {
                    return;
                }
            }
        }
    }

    public void addValues(HashMap<String, SplineSet> hashMap) {
    }

    public KeyPosition() {
        this.mTransitionEasing = null;
        this.mPathMotionArc = UNSET;
        this.mDrawPath = 0;
        this.mPercentWidth = Float.NaN;
        this.mPercentHeight = Float.NaN;
        this.mPercentX = Float.NaN;
        this.mPercentY = Float.NaN;
        this.mAltPercentX = Float.NaN;
        this.mAltPercentY = Float.NaN;
        this.mPositionType = 0;
        this.mCalculatedPositionX = Float.NaN;
        this.mCalculatedPositionY = Float.NaN;
        this.mType = 2;
    }

    public void load(Context context, AttributeSet attributeSet) {
        Loader.read(this, context.obtainStyledAttributes(attributeSet, C0215R.styleable.KeyPosition));
    }

    /* access modifiers changed from: 0000 */
    public void calcPosition(int i, int i2, float f, float f2, float f3, float f4) {
        int i3 = this.mPositionType;
        if (i3 == 1) {
            calcPathPosition(f, f2, f3, f4);
        } else if (i3 != 2) {
            calcCartesianPosition(f, f2, f3, f4);
        } else {
            calcScreenPosition(i, i2);
        }
    }

    private void calcScreenPosition(int i, int i2) {
        float f = (float) (i - 0);
        float f2 = this.mPercentX;
        float f3 = (float) 0;
        this.mCalculatedPositionX = (f * f2) + f3;
        this.mCalculatedPositionY = (((float) (i2 - 0)) * f2) + f3;
    }

    private void calcPathPosition(float f, float f2, float f3, float f4) {
        float f5 = f3 - f;
        float f6 = f4 - f2;
        float f7 = -f6;
        float f8 = this.mPercentX;
        float f9 = f + (f5 * f8);
        float f10 = this.mPercentY;
        this.mCalculatedPositionX = f9 + (f7 * f10);
        this.mCalculatedPositionY = f2 + (f6 * f8) + (f5 * f10);
    }

    private void calcCartesianPosition(float f, float f2, float f3, float f4) {
        float f5 = f3 - f;
        float f6 = f4 - f2;
        float f7 = 0.0f;
        float f8 = Float.isNaN(this.mPercentX) ? 0.0f : this.mPercentX;
        float f9 = Float.isNaN(this.mAltPercentY) ? 0.0f : this.mAltPercentY;
        float f10 = Float.isNaN(this.mPercentY) ? 0.0f : this.mPercentY;
        if (!Float.isNaN(this.mAltPercentX)) {
            f7 = this.mAltPercentX;
        }
        this.mCalculatedPositionX = (float) ((int) (f + (f8 * f5) + (f7 * f6)));
        this.mCalculatedPositionY = (float) ((int) (f2 + (f5 * f9) + (f6 * f10)));
    }

    /* access modifiers changed from: 0000 */
    public float getPositionX() {
        return this.mCalculatedPositionX;
    }

    /* access modifiers changed from: 0000 */
    public float getPositionY() {
        return this.mCalculatedPositionY;
    }

    public void positionAttributes(View view, RectF rectF, RectF rectF2, float f, float f2, String[] strArr, float[] fArr) {
        int i = this.mPositionType;
        if (i == 1) {
            positionPathAttributes(rectF, rectF2, f, f2, strArr, fArr);
        } else if (i != 2) {
            positionCartAttributes(rectF, rectF2, f, f2, strArr, fArr);
        } else {
            positionScreenAttributes(view, rectF, rectF2, f, f2, strArr, fArr);
        }
    }

    /* access modifiers changed from: 0000 */
    public void positionPathAttributes(RectF rectF, RectF rectF2, float f, float f2, String[] strArr, float[] fArr) {
        float centerX = rectF.centerX();
        float centerY = rectF.centerY();
        float centerX2 = rectF2.centerX() - centerX;
        float centerY2 = rectF2.centerY() - centerY;
        float hypot = (float) Math.hypot((double) centerX2, (double) centerY2);
        if (((double) hypot) < 1.0E-4d) {
            System.out.println("distance ~ 0");
            fArr[0] = 0.0f;
            fArr[1] = 0.0f;
            return;
        }
        float f3 = centerX2 / hypot;
        float f4 = centerY2 / hypot;
        float f5 = f2 - centerY;
        float f6 = f - centerX;
        float f7 = ((f3 * f5) - (f6 * f4)) / hypot;
        float f8 = ((f3 * f6) + (f4 * f5)) / hypot;
        String str = strArr[0];
        String str2 = PERCENT_X;
        if (str == null) {
            strArr[0] = str2;
            strArr[1] = PERCENT_Y;
            fArr[0] = f8;
            fArr[1] = f7;
        } else if (str2.equals(strArr[0])) {
            fArr[0] = f8;
            fArr[1] = f7;
        }
    }

    /* access modifiers changed from: 0000 */
    public void positionScreenAttributes(View view, RectF rectF, RectF rectF2, float f, float f2, String[] strArr, float[] fArr) {
        rectF.centerX();
        rectF.centerY();
        rectF2.centerX();
        rectF2.centerY();
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        int width = viewGroup.getWidth();
        int height = viewGroup.getHeight();
        String str = strArr[0];
        String str2 = PERCENT_X;
        if (str == null) {
            strArr[0] = str2;
            fArr[0] = f / ((float) width);
            strArr[1] = PERCENT_Y;
            fArr[1] = f2 / ((float) height);
        } else if (str2.equals(strArr[0])) {
            fArr[0] = f / ((float) width);
            fArr[1] = f2 / ((float) height);
        } else {
            fArr[1] = f / ((float) width);
            fArr[0] = f2 / ((float) height);
        }
    }

    /* access modifiers changed from: 0000 */
    public void positionCartAttributes(RectF rectF, RectF rectF2, float f, float f2, String[] strArr, float[] fArr) {
        float centerX = rectF.centerX();
        float centerY = rectF.centerY();
        float centerX2 = rectF2.centerX() - centerX;
        float centerY2 = rectF2.centerY() - centerY;
        String str = strArr[0];
        String str2 = PERCENT_X;
        if (str == null) {
            strArr[0] = str2;
            fArr[0] = (f - centerX) / centerX2;
            strArr[1] = PERCENT_Y;
            fArr[1] = (f2 - centerY) / centerY2;
        } else if (str2.equals(strArr[0])) {
            fArr[0] = (f - centerX) / centerX2;
            fArr[1] = (f2 - centerY) / centerY2;
        } else {
            fArr[1] = (f - centerX) / centerX2;
            fArr[0] = (f2 - centerY) / centerY2;
        }
    }

    public boolean intersects(int i, int i2, RectF rectF, RectF rectF2, float f, float f2) {
        calcPosition(i, i2, rectF.centerX(), rectF.centerY(), rectF2.centerX(), rectF2.centerY());
        return Math.abs(f - this.mCalculatedPositionX) < 20.0f && Math.abs(f2 - this.mCalculatedPositionY) < 20.0f;
    }

    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setValue(java.lang.String r2, java.lang.Object r3) {
        /*
            r1 = this;
            int r0 = r2.hashCode()
            switch(r0) {
                case -1812823328: goto L_0x0044;
                case -1127236479: goto L_0x003a;
                case -1017587252: goto L_0x0030;
                case -827014263: goto L_0x0026;
                case -200259324: goto L_0x001c;
                case 428090547: goto L_0x0012;
                case 428090548: goto L_0x0008;
                default: goto L_0x0007;
            }
        L_0x0007:
            goto L_0x004e
        L_0x0008:
            java.lang.String r0 = "percentY"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x004e
            r2 = 6
            goto L_0x004f
        L_0x0012:
            java.lang.String r0 = "percentX"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x004e
            r2 = 5
            goto L_0x004f
        L_0x001c:
            java.lang.String r0 = "sizePercent"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x004e
            r2 = 4
            goto L_0x004f
        L_0x0026:
            java.lang.String r0 = "drawPath"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x004e
            r2 = 1
            goto L_0x004f
        L_0x0030:
            java.lang.String r0 = "percentHeight"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x004e
            r2 = 3
            goto L_0x004f
        L_0x003a:
            java.lang.String r0 = "percentWidth"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x004e
            r2 = 2
            goto L_0x004f
        L_0x0044:
            java.lang.String r0 = "transitionEasing"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x004e
            r2 = 0
            goto L_0x004f
        L_0x004e:
            r2 = -1
        L_0x004f:
            switch(r2) {
                case 0: goto L_0x007f;
                case 1: goto L_0x0078;
                case 2: goto L_0x0071;
                case 3: goto L_0x006a;
                case 4: goto L_0x0061;
                case 5: goto L_0x005a;
                case 6: goto L_0x0053;
                default: goto L_0x0052;
            }
        L_0x0052:
            goto L_0x0085
        L_0x0053:
            float r2 = r1.toFloat(r3)
            r1.mPercentY = r2
            goto L_0x0085
        L_0x005a:
            float r2 = r1.toFloat(r3)
            r1.mPercentX = r2
            goto L_0x0085
        L_0x0061:
            float r2 = r1.toFloat(r3)
            r1.mPercentWidth = r2
            r1.mPercentHeight = r2
            goto L_0x0085
        L_0x006a:
            float r2 = r1.toFloat(r3)
            r1.mPercentHeight = r2
            goto L_0x0085
        L_0x0071:
            float r2 = r1.toFloat(r3)
            r1.mPercentWidth = r2
            goto L_0x0085
        L_0x0078:
            int r2 = r1.toInt(r3)
            r1.mDrawPath = r2
            goto L_0x0085
        L_0x007f:
            java.lang.String r2 = r3.toString()
            r1.mTransitionEasing = r2
        L_0x0085:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.KeyPosition.setValue(java.lang.String, java.lang.Object):void");
    }
}
