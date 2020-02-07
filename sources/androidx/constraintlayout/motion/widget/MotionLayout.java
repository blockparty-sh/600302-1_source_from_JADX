package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Interpolator;
import android.widget.TextView;
import androidx.constraintlayout.motion.utils.StopLogic;
import androidx.constraintlayout.motion.widget.MotionScene.Transition;
import androidx.constraintlayout.solver.widgets.Barrier;
import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintAnchor.C0202Type;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour;
import androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer;
import androidx.constraintlayout.solver.widgets.Flow;
import androidx.constraintlayout.solver.widgets.Guideline;
import androidx.constraintlayout.solver.widgets.Helper;
import androidx.constraintlayout.solver.widgets.HelperWidget;
import androidx.constraintlayout.solver.widgets.VirtualLayout;
import androidx.constraintlayout.widget.C0215R;
import androidx.constraintlayout.widget.ConstraintHelper;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.constraintlayout.widget.Constraints;
import androidx.core.internal.view.SupportMenu;
import androidx.core.view.NestedScrollingParent3;
import androidx.core.view.ViewCompat;
import androidx.exifinterface.media.ExifInterface;
import com.microsoft.appcenter.Constants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class MotionLayout extends ConstraintLayout implements NestedScrollingParent3 {
    private static final boolean DEBUG = false;
    public static final int DEBUG_SHOW_NONE = 0;
    public static final int DEBUG_SHOW_PATH = 2;
    public static final int DEBUG_SHOW_PROGRESS = 1;
    public static boolean IS_IN_EDIT_MODE = false;
    static final int MAX_KEY_FRAMES = 50;
    private static final boolean PREVENT_SCROLL_CALLS = false;
    static final String TAG = "MotionLayout";
    public static final int TOUCH_UP_COMPLETE = 0;
    public static final int TOUCH_UP_COMPLETE_TO_END = 2;
    public static final int TOUCH_UP_COMPLETE_TO_START = 1;
    public static final int TOUCH_UP_DECELERATE = 4;
    public static final int TOUCH_UP_DECELERATE_AND_COMPLETE = 5;
    public static final int TOUCH_UP_STOP = 3;
    public static final int VELOCITY_LAYOUT = 1;
    public static final int VELOCITY_POST_LAYOUT = 0;
    public static final int VELOCITY_STATIC_LAYOUT = 3;
    public static final int VELOCITY_STATIC_POST_LAYOUT = 2;
    boolean firstDown = true;
    private float lastPos;
    private float lastY;
    private long mAnimationStartTime = 0;
    private int mBeginState = -1;
    private RectF mBoundsCheck = new RectF();
    int mCurrentState = -1;
    int mDebugPath = 0;
    private DecelerateInterpolator mDecelerateLogic = new DecelerateInterpolator();
    private DesignTool mDesignTool;
    DevModeDraw mDevModeDraw;
    /* access modifiers changed from: private */
    public int mEndState = -1;
    int mEndWrapHeight;
    int mEndWrapWidth;
    HashMap<View, MotionController> mFrameArrayList = new HashMap<>();
    private int mFrames = 0;
    int mHeightMeasureMode;
    boolean mInTransition = false;
    boolean mIndirectTransition = false;
    Interpolator mInterpolator;
    private boolean mKeepAnimating = false;
    private KeyCache mKeyCache = new KeyCache();
    private long mLastDrawTime = -1;
    private float mLastFps = 0.0f;
    /* access modifiers changed from: private */
    public int mLastHeightMeasureSpec = 0;
    int mLastLayoutHeight;
    int mLastLayoutWidth;
    float mLastVelocity = 0.0f;
    /* access modifiers changed from: private */
    public int mLastWidthMeasureSpec = 0;
    private TransitionListener mListener;
    private float mListenerPosition = 0.0f;
    private int mListenerState = 0;
    Model mModel = new Model();
    private boolean mNeedsFireTransitionCompleted = false;
    int mOldHeight;
    int mOldWidth;
    private ArrayList<MotionHelper> mOnHideHelpers = null;
    private ArrayList<MotionHelper> mOnShowHelpers = null;
    float mPostInterpolationPosition;
    private View mRegionView = null;
    MotionScene mScene;
    View mScrollTarget;
    float mScrollTargetDT;
    float mScrollTargetDX;
    float mScrollTargetDY;
    long mScrollTargetTime;
    int mStartWrapHeight;
    int mStartWrapWidth;
    private StopLogic mStopLogic = new StopLogic();
    private boolean mTemporalInterpolator = false;
    ArrayList<Integer> mTransitionCompleted = new ArrayList<>();
    private float mTransitionDuration = 1.0f;
    float mTransitionGoalPosition = 0.0f;
    private boolean mTransitionInstantly;
    float mTransitionLastPosition = 0.0f;
    private long mTransitionLastTime;
    float mTransitionPosition = 0.0f;
    int mWidthMeasureMode;

    class DecelerateInterpolator extends MotionInterpolator {
        float currentP = 0.0f;
        float initalV = 0.0f;
        float maxA;

        DecelerateInterpolator() {
        }

        public void config(float f, float f2, float f3) {
            this.initalV = f;
            this.currentP = f2;
            this.maxA = f3;
        }

        public float getInterpolation(float f) {
            float f2;
            float f3;
            float f4 = this.initalV;
            if (f4 > 0.0f) {
                float f5 = this.maxA;
                if (f4 / f5 < f) {
                    f = f4 / f5;
                }
                MotionLayout motionLayout = MotionLayout.this;
                float f6 = this.initalV;
                float f7 = this.maxA;
                motionLayout.mLastVelocity = f6 - (f7 * f);
                f2 = (f6 * f) - (((f7 * f) * f) / 2.0f);
                f3 = this.currentP;
            } else {
                float f8 = -f4;
                float f9 = this.maxA;
                if (f8 / f9 < f) {
                    f = (-f4) / f9;
                }
                MotionLayout motionLayout2 = MotionLayout.this;
                float f10 = this.initalV;
                float f11 = this.maxA;
                motionLayout2.mLastVelocity = (f11 * f) + f10;
                f2 = (f10 * f) + (((f11 * f) * f) / 2.0f);
                f3 = this.currentP;
            }
            return f2 + f3;
        }

        public float getVelocity() {
            return MotionLayout.this.mLastVelocity;
        }
    }

    private class DevModeDraw {
        private static final int DEBUG_PATH_TICKS_PER_MS = 16;
        final int DIAMOND_SIZE = 10;
        final int GRAPH_COLOR = -13391360;
        final int KEYFRAME_COLOR = -2067046;
        final int RED_COLOR = -21965;
        final int SHADOW_COLOR = 1996488704;
        Rect mBounds = new Rect();
        DashPathEffect mDashPathEffect;
        Paint mFillPaint;
        int mKeyFrameCount;
        float[] mKeyFramePoints;
        Paint mPaint = new Paint();
        Paint mPaintGraph;
        Paint mPaintKeyframes;
        Path mPath;
        int[] mPathMode;
        float[] mPoints;
        boolean mPresentationMode = false;
        private float[] mRectangle;
        int mShadowTranslate = 1;
        Paint mTextPaint;

        public DevModeDraw() {
            this.mPaint.setAntiAlias(true);
            this.mPaint.setColor(-21965);
            this.mPaint.setStrokeWidth(2.0f);
            this.mPaint.setStyle(Style.STROKE);
            this.mPaintKeyframes = new Paint();
            this.mPaintKeyframes.setAntiAlias(true);
            this.mPaintKeyframes.setColor(-2067046);
            this.mPaintKeyframes.setStrokeWidth(2.0f);
            this.mPaintKeyframes.setStyle(Style.STROKE);
            this.mPaintGraph = new Paint();
            this.mPaintGraph.setAntiAlias(true);
            this.mPaintGraph.setColor(-13391360);
            this.mPaintGraph.setStrokeWidth(2.0f);
            this.mPaintGraph.setStyle(Style.STROKE);
            this.mTextPaint = new Paint();
            this.mTextPaint.setAntiAlias(true);
            this.mTextPaint.setColor(-13391360);
            this.mTextPaint.setTextSize(MotionLayout.this.getContext().getResources().getDisplayMetrics().density * 12.0f);
            this.mRectangle = new float[8];
            this.mFillPaint = new Paint();
            this.mFillPaint.setAntiAlias(true);
            this.mDashPathEffect = new DashPathEffect(new float[]{4.0f, 8.0f}, 0.0f);
            this.mPaintGraph.setPathEffect(this.mDashPathEffect);
            this.mKeyFramePoints = new float[100];
            this.mPathMode = new int[50];
            if (this.mPresentationMode) {
                this.mPaint.setStrokeWidth(8.0f);
                this.mFillPaint.setStrokeWidth(8.0f);
                this.mPaintKeyframes.setStrokeWidth(8.0f);
                this.mShadowTranslate = 4;
            }
        }

        public void draw(Canvas canvas, HashMap<View, MotionController> hashMap, int i, int i2) {
            if (hashMap != null && hashMap.size() != 0) {
                canvas.save();
                if (!MotionLayout.this.isInEditMode() && (i2 & 1) == 2) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(MotionLayout.this.getContext().getResources().getResourceName(MotionLayout.this.mEndState));
                    sb.append(Constants.COMMON_SCHEMA_PREFIX_SEPARATOR);
                    sb.append(MotionLayout.this.getProgress());
                    String sb2 = sb.toString();
                    canvas.drawText(sb2, 10.0f, (float) (MotionLayout.this.getHeight() - 30), this.mTextPaint);
                    canvas.drawText(sb2, 11.0f, (float) (MotionLayout.this.getHeight() - 29), this.mPaint);
                }
                for (MotionController motionController : hashMap.values()) {
                    int drawPath = motionController.getDrawPath();
                    if (i2 > 0 && drawPath == 0) {
                        drawPath = 1;
                    }
                    if (drawPath != 0) {
                        this.mKeyFrameCount = motionController.buildKeyFrames(this.mKeyFramePoints, this.mPathMode);
                        if (drawPath >= 1) {
                            int i3 = i / 16;
                            float[] fArr = this.mPoints;
                            if (fArr == null || fArr.length != i3 * 2) {
                                this.mPoints = new float[(i3 * 2)];
                                this.mPath = new Path();
                            }
                            int i4 = this.mShadowTranslate;
                            canvas.translate((float) i4, (float) i4);
                            this.mPaint.setColor(1996488704);
                            this.mFillPaint.setColor(1996488704);
                            this.mPaintKeyframes.setColor(1996488704);
                            this.mPaintGraph.setColor(1996488704);
                            motionController.buildPath(this.mPoints, i3);
                            drawAll(canvas, drawPath, this.mKeyFrameCount, motionController);
                            this.mPaint.setColor(-21965);
                            this.mPaintKeyframes.setColor(-2067046);
                            this.mFillPaint.setColor(-2067046);
                            this.mPaintGraph.setColor(-13391360);
                            int i5 = this.mShadowTranslate;
                            canvas.translate((float) (-i5), (float) (-i5));
                            drawAll(canvas, drawPath, this.mKeyFrameCount, motionController);
                            if (drawPath == 5) {
                                drawRectangle(canvas, motionController);
                            }
                        }
                    }
                }
                canvas.restore();
            }
        }

        public void drawAll(Canvas canvas, int i, int i2, MotionController motionController) {
            if (i == 4) {
                drawPathAsConfigured(canvas);
            }
            if (i == 2) {
                drawPathRelative(canvas);
            }
            if (i == 3) {
                drawPathCartesian(canvas);
            }
            drawBasicPath(canvas);
            drawTicks(canvas, i, i2, motionController);
        }

        private void drawBasicPath(Canvas canvas) {
            canvas.drawLines(this.mPoints, this.mPaint);
        }

        private void drawTicks(Canvas canvas, int i, int i2, MotionController motionController) {
            int i3;
            int i4;
            float f;
            float f2;
            int i5;
            Canvas canvas2 = canvas;
            int i6 = i;
            MotionController motionController2 = motionController;
            if (motionController2.mView != null) {
                i4 = motionController2.mView.getWidth();
                i3 = motionController2.mView.getHeight();
            } else {
                i4 = 0;
                i3 = 0;
            }
            for (int i7 = 1; i7 < i2 - 1; i7++) {
                if (i6 != 4 || this.mPathMode[i7 - 1] != 0) {
                    float[] fArr = this.mKeyFramePoints;
                    int i8 = i7 * 2;
                    float f3 = fArr[i8];
                    float f4 = fArr[i8 + 1];
                    this.mPath.reset();
                    this.mPath.moveTo(f3, f4 + 10.0f);
                    this.mPath.lineTo(f3 + 10.0f, f4);
                    this.mPath.lineTo(f3, f4 - 10.0f);
                    this.mPath.lineTo(f3 - 10.0f, f4);
                    this.mPath.close();
                    int i9 = i7 - 1;
                    motionController2.getKeyFrame(i9);
                    if (i6 == 4) {
                        int[] iArr = this.mPathMode;
                        if (iArr[i9] == 1) {
                            drawPathRelativeTicks(canvas2, f3 - 0.0f, f4 - 0.0f);
                        } else if (iArr[i9] == 2) {
                            drawPathCartesianTicks(canvas2, f3 - 0.0f, f4 - 0.0f);
                        } else if (iArr[i9] == 3) {
                            i5 = 3;
                            f2 = f4;
                            f = f3;
                            drawPathScreenTicks(canvas, f3 - 0.0f, f4 - 0.0f, i4, i3);
                            canvas2.drawPath(this.mPath, this.mFillPaint);
                        }
                        f2 = f4;
                        f = f3;
                        i5 = 3;
                        canvas2.drawPath(this.mPath, this.mFillPaint);
                    } else {
                        f2 = f4;
                        f = f3;
                        i5 = 3;
                    }
                    if (i6 == 2) {
                        drawPathRelativeTicks(canvas2, f - 0.0f, f2 - 0.0f);
                    }
                    if (i6 == i5) {
                        drawPathCartesianTicks(canvas2, f - 0.0f, f2 - 0.0f);
                    }
                    if (i6 == 6) {
                        drawPathScreenTicks(canvas, f - 0.0f, f2 - 0.0f, i4, i3);
                    }
                    canvas2.drawPath(this.mPath, this.mFillPaint);
                }
            }
            float[] fArr2 = this.mPoints;
            if (fArr2.length > 1) {
                canvas2.drawCircle(fArr2[0], fArr2[1], 8.0f, this.mPaintKeyframes);
                float[] fArr3 = this.mPoints;
                canvas2.drawCircle(fArr3[fArr3.length - 2], fArr3[fArr3.length - 1], 8.0f, this.mPaintKeyframes);
            }
        }

        private void drawTranslation(Canvas canvas, float f, float f2, float f3, float f4) {
            canvas.drawRect(f, f2, f3, f4, this.mPaintGraph);
            canvas.drawLine(f, f2, f3, f4, this.mPaintGraph);
        }

        private void drawPathRelative(Canvas canvas) {
            float[] fArr = this.mPoints;
            canvas.drawLine(fArr[0], fArr[1], fArr[fArr.length - 2], fArr[fArr.length - 1], this.mPaintGraph);
        }

        private void drawPathAsConfigured(Canvas canvas) {
            boolean z = false;
            boolean z2 = false;
            for (int i = 0; i < this.mKeyFrameCount; i++) {
                if (this.mPathMode[i] == 1) {
                    z = true;
                }
                if (this.mPathMode[i] == 2) {
                    z2 = true;
                }
            }
            if (z) {
                drawPathRelative(canvas);
            }
            if (z2) {
                drawPathCartesian(canvas);
            }
        }

        private void drawPathRelativeTicks(Canvas canvas, float f, float f2) {
            float[] fArr = this.mPoints;
            float f3 = fArr[0];
            float f4 = fArr[1];
            float f5 = fArr[fArr.length - 2];
            float f6 = fArr[fArr.length - 1];
            float hypot = (float) Math.hypot((double) (f3 - f5), (double) (f4 - f6));
            float f7 = f5 - f3;
            float f8 = f6 - f4;
            float f9 = (((f - f3) * f7) + ((f2 - f4) * f8)) / (hypot * hypot);
            float f10 = f3 + (f7 * f9);
            float f11 = f4 + (f9 * f8);
            Path path = new Path();
            path.moveTo(f, f2);
            path.lineTo(f10, f11);
            float hypot2 = (float) Math.hypot((double) (f10 - f), (double) (f11 - f2));
            StringBuilder sb = new StringBuilder();
            sb.append("");
            sb.append(((float) ((int) ((hypot2 * 100.0f) / hypot))) / 100.0f);
            String sb2 = sb.toString();
            getTextBounds(sb2, this.mTextPaint);
            canvas.drawTextOnPath(sb2, path, (hypot2 / 2.0f) - ((float) (this.mBounds.width() / 2)), -20.0f, this.mTextPaint);
            canvas.drawLine(f, f2, f10, f11, this.mPaintGraph);
        }

        /* access modifiers changed from: 0000 */
        public void getTextBounds(String str, Paint paint) {
            paint.getTextBounds(str, 0, str.length(), this.mBounds);
        }

        private void drawPathCartesian(Canvas canvas) {
            float[] fArr = this.mPoints;
            float f = fArr[0];
            float f2 = fArr[1];
            float f3 = fArr[fArr.length - 2];
            float f4 = fArr[fArr.length - 1];
            canvas.drawLine(Math.min(f, f3), Math.max(f2, f4), Math.max(f, f3), Math.max(f2, f4), this.mPaintGraph);
            canvas.drawLine(Math.min(f, f3), Math.min(f2, f4), Math.min(f, f3), Math.max(f2, f4), this.mPaintGraph);
        }

        private void drawPathCartesianTicks(Canvas canvas, float f, float f2) {
            Canvas canvas2 = canvas;
            float[] fArr = this.mPoints;
            float f3 = fArr[0];
            float f4 = fArr[1];
            float f5 = fArr[fArr.length - 2];
            float f6 = fArr[fArr.length - 1];
            float min = Math.min(f3, f5);
            float max = Math.max(f4, f6);
            float min2 = f - Math.min(f3, f5);
            float max2 = Math.max(f4, f6) - f2;
            StringBuilder sb = new StringBuilder();
            String str = "";
            sb.append(str);
            sb.append(((float) ((int) (((double) ((min2 * 100.0f) / Math.abs(f5 - f3))) + 0.5d))) / 100.0f);
            String sb2 = sb.toString();
            getTextBounds(sb2, this.mTextPaint);
            canvas2.drawText(sb2, ((min2 / 2.0f) - ((float) (this.mBounds.width() / 2))) + min, f2 - 20.0f, this.mTextPaint);
            canvas.drawLine(f, f2, Math.min(f3, f5), f2, this.mPaintGraph);
            StringBuilder sb3 = new StringBuilder();
            sb3.append(str);
            sb3.append(((float) ((int) (((double) ((max2 * 100.0f) / Math.abs(f6 - f4))) + 0.5d))) / 100.0f);
            String sb4 = sb3.toString();
            getTextBounds(sb4, this.mTextPaint);
            canvas2.drawText(sb4, f + 5.0f, max - ((max2 / 2.0f) - ((float) (this.mBounds.height() / 2))), this.mTextPaint);
            canvas.drawLine(f, f2, f, Math.max(f4, f6), this.mPaintGraph);
        }

        private void drawPathScreenTicks(Canvas canvas, float f, float f2, int i, int i2) {
            Canvas canvas2 = canvas;
            StringBuilder sb = new StringBuilder();
            String str = "";
            sb.append(str);
            sb.append(((float) ((int) (((double) (((f - ((float) (i / 2))) * 100.0f) / ((float) (MotionLayout.this.getWidth() - i)))) + 0.5d))) / 100.0f);
            String sb2 = sb.toString();
            getTextBounds(sb2, this.mTextPaint);
            canvas2.drawText(sb2, ((f / 2.0f) - ((float) (this.mBounds.width() / 2))) + 0.0f, f2 - 20.0f, this.mTextPaint);
            canvas.drawLine(f, f2, Math.min(0.0f, 1.0f), f2, this.mPaintGraph);
            StringBuilder sb3 = new StringBuilder();
            sb3.append(str);
            sb3.append(((float) ((int) (((double) (((f2 - ((float) (i2 / 2))) * 100.0f) / ((float) (MotionLayout.this.getHeight() - i2)))) + 0.5d))) / 100.0f);
            String sb4 = sb3.toString();
            getTextBounds(sb4, this.mTextPaint);
            canvas2.drawText(sb4, f + 5.0f, 0.0f - ((f2 / 2.0f) - ((float) (this.mBounds.height() / 2))), this.mTextPaint);
            canvas.drawLine(f, f2, f, Math.max(0.0f, 1.0f), this.mPaintGraph);
        }

        private void drawRectangle(Canvas canvas, MotionController motionController) {
            this.mPath.reset();
            for (int i = 0; i <= 50; i++) {
                motionController.buildRect(((float) i) / ((float) 50), this.mRectangle, 0);
                Path path = this.mPath;
                float[] fArr = this.mRectangle;
                path.moveTo(fArr[0], fArr[1]);
                Path path2 = this.mPath;
                float[] fArr2 = this.mRectangle;
                path2.lineTo(fArr2[2], fArr2[3]);
                Path path3 = this.mPath;
                float[] fArr3 = this.mRectangle;
                path3.lineTo(fArr3[4], fArr3[5]);
                Path path4 = this.mPath;
                float[] fArr4 = this.mRectangle;
                path4.lineTo(fArr4[6], fArr4[7]);
                this.mPath.close();
            }
            this.mPaint.setColor(1140850688);
            canvas.translate(2.0f, 2.0f);
            canvas.drawPath(this.mPath, this.mPaint);
            canvas.translate(-2.0f, -2.0f);
            this.mPaint.setColor(SupportMenu.CATEGORY_MASK);
            canvas.drawPath(this.mPath, this.mPaint);
        }
    }

    class Model {
        ConstraintSet mEnd = null;
        int mEndId;
        ConstraintWidgetContainer mLayoutEnd = new ConstraintWidgetContainer();
        ConstraintWidgetContainer mLayoutStart = new ConstraintWidgetContainer();
        ConstraintSet mStart = null;
        int mStartId;

        Model() {
        }

        /* access modifiers changed from: 0000 */
        public void copy(ConstraintWidgetContainer constraintWidgetContainer, ConstraintWidgetContainer constraintWidgetContainer2) {
            ConstraintWidget constraintWidget;
            ArrayList children = constraintWidgetContainer.getChildren();
            HashMap hashMap = new HashMap();
            hashMap.put(constraintWidgetContainer, constraintWidgetContainer2);
            constraintWidgetContainer2.getChildren().clear();
            constraintWidgetContainer2.copy(constraintWidgetContainer, hashMap);
            Iterator it = children.iterator();
            while (it.hasNext()) {
                ConstraintWidget constraintWidget2 = (ConstraintWidget) it.next();
                if (constraintWidget2 instanceof Barrier) {
                    constraintWidget = new Barrier();
                } else if (constraintWidget2 instanceof Guideline) {
                    constraintWidget = new Guideline();
                } else if (constraintWidget2 instanceof Flow) {
                    constraintWidget = new Flow();
                } else if (constraintWidget2 instanceof Helper) {
                    constraintWidget = new HelperWidget();
                } else {
                    constraintWidget = new ConstraintWidget();
                }
                constraintWidgetContainer2.add(constraintWidget);
                hashMap.put(constraintWidget2, constraintWidget);
            }
            Iterator it2 = children.iterator();
            while (it2.hasNext()) {
                ConstraintWidget constraintWidget3 = (ConstraintWidget) it2.next();
                ((ConstraintWidget) hashMap.get(constraintWidget3)).copy(constraintWidget3, hashMap);
            }
        }

        /* access modifiers changed from: 0000 */
        public void initFrom(ConstraintWidgetContainer constraintWidgetContainer, ConstraintSet constraintSet, ConstraintSet constraintSet2) {
            this.mStart = constraintSet;
            this.mEnd = constraintSet2;
            this.mLayoutStart.setMeasurer(MotionLayout.this.mLayoutWidget.getMeasurer());
            this.mLayoutEnd.setMeasurer(MotionLayout.this.mLayoutWidget.getMeasurer());
            this.mLayoutStart.removeAllChildren();
            this.mLayoutEnd.removeAllChildren();
            copy(MotionLayout.this.mLayoutWidget, this.mLayoutStart);
            copy(MotionLayout.this.mLayoutWidget, this.mLayoutEnd);
            if (constraintSet != null) {
                setupConstraintWidget(this.mLayoutStart, constraintSet);
            }
            setupConstraintWidget(this.mLayoutEnd, constraintSet2);
            this.mLayoutStart.updateHierarchy();
            this.mLayoutEnd.updateHierarchy();
            LayoutParams layoutParams = MotionLayout.this.getLayoutParams();
            if (layoutParams != null) {
                if (layoutParams.width == -2) {
                    this.mLayoutStart.setHorizontalDimensionBehaviour(DimensionBehaviour.WRAP_CONTENT);
                    this.mLayoutEnd.setHorizontalDimensionBehaviour(DimensionBehaviour.WRAP_CONTENT);
                }
                if (layoutParams.height == -2) {
                    this.mLayoutStart.setVerticalDimensionBehaviour(DimensionBehaviour.WRAP_CONTENT);
                    this.mLayoutEnd.setVerticalDimensionBehaviour(DimensionBehaviour.WRAP_CONTENT);
                }
            }
        }

        private void setupConstraintWidget(ConstraintWidgetContainer constraintWidgetContainer, ConstraintSet constraintSet) {
            SparseArray sparseArray = new SparseArray();
            Constraints.LayoutParams layoutParams = new Constraints.LayoutParams(-2, -2);
            sparseArray.clear();
            sparseArray.put(0, constraintWidgetContainer);
            sparseArray.put(MotionLayout.this.getId(), constraintWidgetContainer);
            Iterator it = constraintWidgetContainer.getChildren().iterator();
            while (it.hasNext()) {
                ConstraintWidget constraintWidget = (ConstraintWidget) it.next();
                sparseArray.put(((View) constraintWidget.getCompanionWidget()).getId(), constraintWidget);
            }
            Iterator it2 = constraintWidgetContainer.getChildren().iterator();
            while (it2.hasNext()) {
                ConstraintWidget constraintWidget2 = (ConstraintWidget) it2.next();
                View view = (View) constraintWidget2.getCompanionWidget();
                constraintSet.applyToLayoutParams(view.getId(), layoutParams);
                constraintWidget2.setWidth(constraintSet.getWidth(view.getId()));
                constraintWidget2.setHeight(constraintSet.getHeight(view.getId()));
                if (view instanceof ConstraintHelper) {
                    constraintSet.applyToHelper((ConstraintHelper) view, constraintWidget2, layoutParams, sparseArray);
                    if (view instanceof androidx.constraintlayout.widget.Barrier) {
                        ((androidx.constraintlayout.widget.Barrier) view).validateParams();
                    }
                }
                if (VERSION.SDK_INT >= 17) {
                    layoutParams.resolveLayoutDirection(MotionLayout.this.getLayoutDirection());
                } else {
                    layoutParams.resolveLayoutDirection(0);
                }
                MotionLayout.this.applyConstraintsFromLayoutParams(false, view, constraintWidget2, layoutParams, sparseArray);
                if (constraintSet.getVisibilityMode(view.getId()) == 1) {
                    constraintWidget2.setVisibility(view.getVisibility());
                } else {
                    constraintWidget2.setVisibility(constraintSet.getVisibility(view.getId()));
                }
            }
            Iterator it3 = constraintWidgetContainer.getChildren().iterator();
            while (it3.hasNext()) {
                ConstraintWidget constraintWidget3 = (ConstraintWidget) it3.next();
                if (constraintWidget3 instanceof Helper) {
                    Helper helper = (Helper) constraintWidget3;
                    helper.removeAllIds();
                    ((ConstraintHelper) constraintWidget3.getCompanionWidget()).updatePreLayout(constraintWidgetContainer, helper, sparseArray);
                    if (helper instanceof VirtualLayout) {
                        ((VirtualLayout) helper).captureWidgets();
                    }
                }
            }
        }

        /* access modifiers changed from: 0000 */
        public ConstraintWidget getWidget(ConstraintWidgetContainer constraintWidgetContainer, View view) {
            if (constraintWidgetContainer.getCompanionWidget() == view) {
                return constraintWidgetContainer;
            }
            ArrayList children = constraintWidgetContainer.getChildren();
            int size = children.size();
            for (int i = 0; i < size; i++) {
                ConstraintWidget constraintWidget = (ConstraintWidget) children.get(i);
                if (constraintWidget.getCompanionWidget() == view) {
                    return constraintWidget;
                }
            }
            return null;
        }

        private void debugLayoutParam(String str, ConstraintLayout.LayoutParams layoutParams) {
            StringBuilder sb = new StringBuilder();
            sb.append(" ");
            sb.append(layoutParams.startToStart != -1 ? "SS" : "__");
            String sb2 = sb.toString();
            StringBuilder sb3 = new StringBuilder();
            sb3.append(sb2);
            String str2 = "|__";
            sb3.append(layoutParams.startToEnd != -1 ? "|SE" : str2);
            String sb4 = sb3.toString();
            StringBuilder sb5 = new StringBuilder();
            sb5.append(sb4);
            sb5.append(layoutParams.endToStart != -1 ? "|ES" : str2);
            String sb6 = sb5.toString();
            StringBuilder sb7 = new StringBuilder();
            sb7.append(sb6);
            sb7.append(layoutParams.endToEnd != -1 ? "|EE" : str2);
            String sb8 = sb7.toString();
            StringBuilder sb9 = new StringBuilder();
            sb9.append(sb8);
            sb9.append(layoutParams.leftToLeft != -1 ? "|LL" : str2);
            String sb10 = sb9.toString();
            StringBuilder sb11 = new StringBuilder();
            sb11.append(sb10);
            sb11.append(layoutParams.leftToRight != -1 ? "|LR" : str2);
            String sb12 = sb11.toString();
            StringBuilder sb13 = new StringBuilder();
            sb13.append(sb12);
            sb13.append(layoutParams.rightToLeft != -1 ? "|RL" : str2);
            String sb14 = sb13.toString();
            StringBuilder sb15 = new StringBuilder();
            sb15.append(sb14);
            sb15.append(layoutParams.rightToRight != -1 ? "|RR" : str2);
            String sb16 = sb15.toString();
            StringBuilder sb17 = new StringBuilder();
            sb17.append(sb16);
            sb17.append(layoutParams.topToTop != -1 ? "|TT" : str2);
            String sb18 = sb17.toString();
            StringBuilder sb19 = new StringBuilder();
            sb19.append(sb18);
            sb19.append(layoutParams.topToBottom != -1 ? "|TB" : str2);
            String sb20 = sb19.toString();
            StringBuilder sb21 = new StringBuilder();
            sb21.append(sb20);
            sb21.append(layoutParams.bottomToTop != -1 ? "|BT" : str2);
            String sb22 = sb21.toString();
            StringBuilder sb23 = new StringBuilder();
            sb23.append(sb22);
            if (layoutParams.bottomToBottom != -1) {
                str2 = "|BB";
            }
            sb23.append(str2);
            String sb24 = sb23.toString();
            StringBuilder sb25 = new StringBuilder();
            sb25.append(str);
            sb25.append(sb24);
            Log.v(MotionLayout.TAG, sb25.toString());
        }

        private void debugWidget(String str, ConstraintWidget constraintWidget) {
            String str2;
            String str3;
            String str4;
            StringBuilder sb = new StringBuilder();
            sb.append(" ");
            ConstraintAnchor constraintAnchor = constraintWidget.mTop.mTarget;
            String str5 = "B";
            String str6 = ExifInterface.GPS_DIRECTION_TRUE;
            String str7 = "__";
            if (constraintAnchor != null) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(str6);
                sb2.append(constraintWidget.mTop.mTarget.mType == C0202Type.TOP ? str6 : str5);
                str2 = sb2.toString();
            } else {
                str2 = str7;
            }
            sb.append(str2);
            String sb3 = sb.toString();
            StringBuilder sb4 = new StringBuilder();
            sb4.append(sb3);
            if (constraintWidget.mBottom.mTarget != null) {
                StringBuilder sb5 = new StringBuilder();
                sb5.append(str5);
                if (constraintWidget.mBottom.mTarget.mType == C0202Type.TOP) {
                    str5 = str6;
                }
                sb5.append(str5);
                str3 = sb5.toString();
            } else {
                str3 = str7;
            }
            sb4.append(str3);
            String sb6 = sb4.toString();
            StringBuilder sb7 = new StringBuilder();
            sb7.append(sb6);
            String str8 = "R";
            String str9 = "L";
            if (constraintWidget.mLeft.mTarget != null) {
                StringBuilder sb8 = new StringBuilder();
                sb8.append(str9);
                sb8.append(constraintWidget.mLeft.mTarget.mType == C0202Type.LEFT ? str9 : str8);
                str4 = sb8.toString();
            } else {
                str4 = str7;
            }
            sb7.append(str4);
            String sb9 = sb7.toString();
            StringBuilder sb10 = new StringBuilder();
            sb10.append(sb9);
            if (constraintWidget.mRight.mTarget != null) {
                StringBuilder sb11 = new StringBuilder();
                sb11.append(str8);
                if (constraintWidget.mRight.mTarget.mType == C0202Type.LEFT) {
                    str8 = str9;
                }
                sb11.append(str8);
                str7 = sb11.toString();
            }
            sb10.append(str7);
            String sb12 = sb10.toString();
            StringBuilder sb13 = new StringBuilder();
            sb13.append(str);
            sb13.append(sb12);
            sb13.append(" ---  ");
            sb13.append(constraintWidget);
            Log.v(MotionLayout.TAG, sb13.toString());
        }

        private void debugLayout(String str, ConstraintWidgetContainer constraintWidgetContainer) {
            View view = (View) constraintWidgetContainer.getCompanionWidget();
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            String str2 = " ";
            sb.append(str2);
            sb.append(Debug.getName(view));
            String sb2 = sb.toString();
            StringBuilder sb3 = new StringBuilder();
            sb3.append(sb2);
            sb3.append("  ========= ");
            sb3.append(constraintWidgetContainer);
            String sb4 = sb3.toString();
            String str3 = MotionLayout.TAG;
            Log.v(str3, sb4);
            int size = constraintWidgetContainer.getChildren().size();
            for (int i = 0; i < size; i++) {
                StringBuilder sb5 = new StringBuilder();
                sb5.append(sb2);
                sb5.append("[");
                sb5.append(i);
                sb5.append("] ");
                String sb6 = sb5.toString();
                ConstraintWidget constraintWidget = (ConstraintWidget) constraintWidgetContainer.getChildren().get(i);
                StringBuilder sb7 = new StringBuilder();
                sb7.append("");
                String str4 = "_";
                sb7.append(constraintWidget.mTop.mTarget != null ? ExifInterface.GPS_DIRECTION_TRUE : str4);
                String sb8 = sb7.toString();
                StringBuilder sb9 = new StringBuilder();
                sb9.append(sb8);
                sb9.append(constraintWidget.mBottom.mTarget != null ? "B" : str4);
                String sb10 = sb9.toString();
                StringBuilder sb11 = new StringBuilder();
                sb11.append(sb10);
                sb11.append(constraintWidget.mLeft.mTarget != null ? "L" : str4);
                String sb12 = sb11.toString();
                StringBuilder sb13 = new StringBuilder();
                sb13.append(sb12);
                if (constraintWidget.mRight.mTarget != null) {
                    str4 = "R";
                }
                sb13.append(str4);
                String sb14 = sb13.toString();
                View view2 = (View) constraintWidget.getCompanionWidget();
                String name = Debug.getName(view2);
                if (view2 instanceof TextView) {
                    StringBuilder sb15 = new StringBuilder();
                    sb15.append(name);
                    sb15.append("(");
                    sb15.append(((TextView) view2).getText());
                    sb15.append(")");
                    name = sb15.toString();
                }
                StringBuilder sb16 = new StringBuilder();
                sb16.append(sb6);
                sb16.append("  ");
                sb16.append(name);
                sb16.append(str2);
                sb16.append(constraintWidget);
                sb16.append(str2);
                sb16.append(sb14);
                Log.v(str3, sb16.toString());
            }
            StringBuilder sb17 = new StringBuilder();
            sb17.append(sb2);
            sb17.append(" done. ");
            Log.v(str3, sb17.toString());
        }

        public void reEvaluateState() {
            measure(MotionLayout.this.mLastWidthMeasureSpec, MotionLayout.this.mLastHeightMeasureSpec);
            MotionLayout.this.setupMotionViews();
        }

        public void measure(int i, int i2) {
            int mode = MeasureSpec.getMode(i);
            int mode2 = MeasureSpec.getMode(i2);
            MotionLayout motionLayout = MotionLayout.this;
            motionLayout.mWidthMeasureMode = mode;
            motionLayout.mHeightMeasureMode = mode2;
            int optimizationLevel = motionLayout.getOptimizationLevel();
            if (MotionLayout.this.mCurrentState == MotionLayout.this.getStartState()) {
                MotionLayout.this.resolveSystem(this.mLayoutEnd, optimizationLevel, i, i2);
                if (this.mStart != null) {
                    MotionLayout.this.resolveSystem(this.mLayoutStart, optimizationLevel, i, i2);
                }
            } else {
                if (this.mStart != null) {
                    MotionLayout.this.resolveSystem(this.mLayoutStart, optimizationLevel, i, i2);
                }
                MotionLayout.this.resolveSystem(this.mLayoutEnd, optimizationLevel, i, i2);
            }
            MotionLayout.this.mStartWrapWidth = this.mLayoutStart.getWidth();
            MotionLayout.this.mStartWrapHeight = this.mLayoutStart.getHeight();
            MotionLayout.this.mEndWrapWidth = this.mLayoutEnd.getWidth();
            MotionLayout.this.mEndWrapHeight = this.mLayoutEnd.getHeight();
            int i3 = MotionLayout.this.mStartWrapWidth;
            int i4 = MotionLayout.this.mStartWrapHeight;
            if (MotionLayout.this.mWidthMeasureMode == Integer.MIN_VALUE) {
                i3 = (int) (((float) MotionLayout.this.mStartWrapWidth) + (MotionLayout.this.mPostInterpolationPosition * ((float) (MotionLayout.this.mEndWrapWidth - MotionLayout.this.mStartWrapWidth))));
            }
            int i5 = i3;
            if (MotionLayout.this.mHeightMeasureMode == Integer.MIN_VALUE) {
                i4 = (int) (((float) MotionLayout.this.mStartWrapHeight) + (MotionLayout.this.mPostInterpolationPosition * ((float) (MotionLayout.this.mEndWrapHeight - MotionLayout.this.mStartWrapHeight))));
            }
            MotionLayout.this.resolveMeasuredDimension(i, i2, i5, i4, this.mLayoutStart.isWidthMeasuredTooSmall() || this.mLayoutEnd.isWidthMeasuredTooSmall(), this.mLayoutStart.isHeightMeasuredTooSmall() || this.mLayoutEnd.isHeightMeasuredTooSmall());
        }

        public void build() {
            int childCount = MotionLayout.this.getChildCount();
            MotionLayout.this.mFrameArrayList.clear();
            for (int i = 0; i < childCount; i++) {
                View childAt = MotionLayout.this.getChildAt(i);
                MotionLayout.this.mFrameArrayList.put(childAt, new MotionController(childAt));
            }
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt2 = MotionLayout.this.getChildAt(i2);
                MotionController motionController = (MotionController) MotionLayout.this.mFrameArrayList.get(childAt2);
                if (motionController != null) {
                    ConstraintSet constraintSet = this.mStart;
                    String str = ")";
                    String str2 = " (";
                    String str3 = "no widget for  ";
                    String str4 = MotionLayout.TAG;
                    if (constraintSet != null) {
                        ConstraintWidget widget = getWidget(this.mLayoutStart, childAt2);
                        if (widget != null) {
                            motionController.setStartState(widget, this.mStart);
                        } else {
                            StringBuilder sb = new StringBuilder();
                            sb.append(Debug.getLocation());
                            sb.append(str3);
                            sb.append(Debug.getName(childAt2));
                            sb.append(str2);
                            sb.append(childAt2.getClass().getName());
                            sb.append(str);
                            Log.e(str4, sb.toString());
                        }
                    }
                    if (this.mEnd != null) {
                        ConstraintWidget widget2 = getWidget(this.mLayoutEnd, childAt2);
                        if (widget2 != null) {
                            motionController.setEndState(widget2, this.mEnd);
                        } else {
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append(Debug.getLocation());
                            sb2.append(str3);
                            sb2.append(Debug.getName(childAt2));
                            sb2.append(str2);
                            sb2.append(childAt2.getClass().getName());
                            sb2.append(str);
                            Log.e(str4, sb2.toString());
                        }
                    }
                }
            }
        }

        public void setMeasuredId(int i, int i2) {
            this.mStartId = i;
            this.mEndId = i2;
        }

        public boolean isNotConfiguredWith(int i, int i2) {
            return (i == this.mStartId && i2 == this.mEndId) ? false : true;
        }
    }

    protected interface MotionTracker {
        void addMovement(MotionEvent motionEvent);

        void clear();

        void computeCurrentVelocity(int i);

        void computeCurrentVelocity(int i, float f);

        float getXVelocity();

        float getXVelocity(int i);

        float getYVelocity();

        float getYVelocity(int i);

        void recycle();
    }

    private static class MyTracker implements MotionTracker {

        /* renamed from: me */
        private static MyTracker f29me = new MyTracker();
        VelocityTracker tracker;

        private MyTracker() {
        }

        public static MyTracker obtain() {
            f29me.tracker = VelocityTracker.obtain();
            return f29me;
        }

        public void recycle() {
            this.tracker.recycle();
            this.tracker = null;
        }

        public void clear() {
            this.tracker.clear();
        }

        public void addMovement(MotionEvent motionEvent) {
            this.tracker.addMovement(motionEvent);
        }

        public void computeCurrentVelocity(int i) {
            this.tracker.computeCurrentVelocity(i);
        }

        public void computeCurrentVelocity(int i, float f) {
            this.tracker.computeCurrentVelocity(i, f);
        }

        public float getXVelocity() {
            return this.tracker.getXVelocity();
        }

        public float getYVelocity() {
            return this.tracker.getYVelocity();
        }

        public float getXVelocity(int i) {
            return this.tracker.getXVelocity(i);
        }

        public float getYVelocity(int i) {
            return getYVelocity(i);
        }
    }

    public interface TransitionListener {
        void onTransitionChange(MotionLayout motionLayout, int i, int i2, float f);

        void onTransitionCompleted(MotionLayout motionLayout, int i);

        void onTransitionStarted(MotionLayout motionLayout, int i, int i2);

        void onTransitionTrigger(MotionLayout motionLayout, int i, boolean z, float f);
    }

    private static boolean willJump(float f, float f2, float f3) {
        boolean z = true;
        if (f > 0.0f) {
            float f4 = f / f3;
            if (f2 + ((f * f4) - (((f3 * f4) * f4) / 2.0f)) <= 1.0f) {
                z = false;
            }
            return z;
        }
        float f5 = (-f) / f3;
        if (f2 + (f * f5) + (((f3 * f5) * f5) / 2.0f) >= 0.0f) {
            z = false;
        }
        return z;
    }

    public boolean onNestedFling(View view, float f, float f2, boolean z) {
        return false;
    }

    public boolean onNestedPreFling(View view, float f, float f2) {
        return false;
    }

    public void onNestedScroll(View view, int i, int i2, int i3, int i4, int i5) {
    }

    public void onNestedScroll(View view, int i, int i2, int i3, int i4, int i5, int[] iArr) {
    }

    public void onNestedScrollAccepted(View view, View view2, int i, int i2) {
    }

    public MotionLayout(Context context) {
        super(context);
        init(null);
    }

    public MotionLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(attributeSet);
    }

    public MotionLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(attributeSet);
    }

    /* access modifiers changed from: protected */
    public long getNanoTime() {
        return System.nanoTime();
    }

    /* access modifiers changed from: protected */
    public MotionTracker obtainVelocityTracker() {
        return MyTracker.obtain();
    }

    public void setTransition(int i, int i2) {
        MotionScene motionScene = this.mScene;
        if (motionScene != null) {
            this.mBeginState = i;
            this.mEndState = i2;
            motionScene.setTransition(i, i2);
            this.mModel.initFrom(this.mLayoutWidget, this.mScene.getConstraintSet(i), this.mScene.getConstraintSet(i2));
            rebuildScene();
            this.mTransitionLastPosition = 0.0f;
            transitionToStart();
        }
    }

    /* access modifiers changed from: protected */
    public void setTransition(Transition transition) {
        this.mScene.setTransition(transition);
        if (this.mCurrentState == this.mScene.getEndId()) {
            this.mTransitionLastPosition = 1.0f;
            this.mTransitionPosition = 1.0f;
            this.mTransitionGoalPosition = 1.0f;
        } else {
            this.mTransitionLastPosition = 0.0f;
            this.mTransitionPosition = 0.0f;
            this.mTransitionGoalPosition = 0.0f;
        }
        this.mTransitionLastTime = getNanoTime();
        int startId = this.mScene.getStartId();
        int endId = this.mScene.getEndId();
        if (startId != this.mBeginState || endId != this.mEndState) {
            this.mBeginState = startId;
            this.mEndState = endId;
            this.mScene.setTransition(this.mBeginState, this.mEndState);
            this.mModel.initFrom(this.mLayoutWidget, this.mScene.getConstraintSet(this.mBeginState), this.mScene.getConstraintSet(this.mEndState));
            this.mModel.setMeasuredId(this.mBeginState, this.mEndState);
            this.mModel.reEvaluateState();
            rebuildScene();
            TransitionListener transitionListener = this.mListener;
            if (transitionListener != null) {
                transitionListener.onTransitionStarted(this, this.mBeginState, this.mEndState);
            }
        }
    }

    public void loadLayoutDescription(int i) {
        if (i != 0) {
            try {
                this.mScene = new MotionScene(getContext(), this, i);
                if (VERSION.SDK_INT < 19 || isAttachedToWindow()) {
                    this.mScene.readFallback(this);
                    this.mModel.initFrom(this.mLayoutWidget, this.mScene.getConstraintSet(this.mBeginState), this.mScene.getConstraintSet(this.mEndState));
                    rebuildScene();
                }
            } catch (Exception e) {
                throw new IllegalArgumentException("unable to parse MotionScene file", e);
            }
        } else {
            this.mScene = null;
        }
    }

    public void setState(int i, int i2, int i3) {
        this.mCurrentState = i;
        this.mBeginState = -1;
        this.mEndState = -1;
        if (this.mConstraintLayoutSpec != null) {
            this.mConstraintLayoutSpec.updateConstraints(i, (float) i2, (float) i3);
            return;
        }
        MotionScene motionScene = this.mScene;
        if (motionScene != null) {
            motionScene.getConstraintSet(i).applyTo(this);
        }
    }

    public void setInterpolatedProgress(float f) {
        MotionScene motionScene = this.mScene;
        if (motionScene != null) {
            Interpolator interpolator = motionScene.getInterpolator();
            if (interpolator != null) {
                setProgress(interpolator.getInterpolation(f));
                return;
            }
        }
        setProgress(f);
    }

    public void setProgress(float f) {
        if (f <= 0.0f) {
            this.mCurrentState = this.mBeginState;
        } else if (f >= 1.0f) {
            this.mCurrentState = this.mEndState;
        } else {
            this.mCurrentState = -1;
        }
        if (this.mScene != null) {
            this.mTransitionInstantly = true;
            this.mTransitionGoalPosition = f;
            this.mTransitionPosition = f;
            this.mTransitionLastTime = getNanoTime();
            this.mAnimationStartTime = -1;
            this.mInterpolator = null;
            this.mInTransition = true;
            invalidate();
        }
    }

    /* access modifiers changed from: private */
    public void setupMotionViews() {
        int childCount = getChildCount();
        this.mModel.build();
        boolean z = true;
        this.mInTransition = true;
        int width = getWidth();
        int height = getHeight();
        int i = 0;
        for (int i2 = 0; i2 < childCount; i2++) {
            MotionController motionController = (MotionController) this.mFrameArrayList.get(getChildAt(i2));
            if (motionController != null) {
                this.mScene.getKeyFrames(motionController);
                motionController.setup(width, height, this.mTransitionDuration, getNanoTime());
            }
        }
        float staggered = this.mScene.getStaggered();
        if (staggered != 0.0f) {
            boolean z2 = ((double) staggered) < 0.0d;
            float abs = Math.abs(staggered);
            float f = -3.4028235E38f;
            float f2 = Float.MAX_VALUE;
            int i3 = 0;
            float f3 = Float.MAX_VALUE;
            float f4 = -3.4028235E38f;
            while (true) {
                if (i3 >= childCount) {
                    z = false;
                    break;
                }
                MotionController motionController2 = (MotionController) this.mFrameArrayList.get(getChildAt(i3));
                if (!Float.isNaN(motionController2.mMotionStagger)) {
                    break;
                }
                float finalX = motionController2.getFinalX();
                float finalY = motionController2.getFinalY();
                float f5 = z2 ? finalY - finalX : finalY + finalX;
                f3 = Math.min(f3, f5);
                f4 = Math.max(f4, f5);
                i3++;
            }
            if (z) {
                for (int i4 = 0; i4 < childCount; i4++) {
                    MotionController motionController3 = (MotionController) this.mFrameArrayList.get(getChildAt(i4));
                    if (!Float.isNaN(motionController3.mMotionStagger)) {
                        f2 = Math.min(f2, motionController3.mMotionStagger);
                        f = Math.max(f, motionController3.mMotionStagger);
                    }
                }
                while (i < childCount) {
                    MotionController motionController4 = (MotionController) this.mFrameArrayList.get(getChildAt(i));
                    if (!Float.isNaN(motionController4.mMotionStagger)) {
                        motionController4.mStaggerScale = 1.0f / (1.0f - abs);
                        if (z2) {
                            motionController4.mStaggerOffset = abs - (((f - motionController4.mMotionStagger) / (f - f2)) * abs);
                        } else {
                            motionController4.mStaggerOffset = abs - (((motionController4.mMotionStagger - f2) * abs) / (f - f2));
                        }
                    }
                    i++;
                }
                return;
            }
            while (i < childCount) {
                MotionController motionController5 = (MotionController) this.mFrameArrayList.get(getChildAt(i));
                float finalX2 = motionController5.getFinalX();
                float finalY2 = motionController5.getFinalY();
                float f6 = z2 ? finalY2 - finalX2 : finalY2 + finalX2;
                motionController5.mStaggerScale = 1.0f / (1.0f - abs);
                motionController5.mStaggerOffset = abs - (((f6 - f3) * abs) / (f4 - f3));
                i++;
            }
        }
    }

    public void touchAnimateTo(int i, float f, float f2) {
        if (this.mScene != null && this.mTransitionLastPosition != f) {
            this.mTemporalInterpolator = true;
            this.mAnimationStartTime = getNanoTime();
            this.mTransitionDuration = ((float) this.mScene.getDuration()) / 1000.0f;
            this.mTransitionGoalPosition = f;
            this.mInTransition = true;
            float f3 = 1.0f;
            if (i == 0 || i == 1 || i == 2) {
                if (i == 1) {
                    f = 0.0f;
                } else if (i == 2) {
                    f = 1.0f;
                }
                this.mStopLogic.config(this.mTransitionLastPosition, f, f2, this.mTransitionDuration, this.mScene.getMaxAcceleration(), this.mScene.getMaxVelocity());
                int i2 = this.mCurrentState;
                if (f != 0.0f) {
                    f3 = 0.0f;
                }
                setProgress(f3);
                this.mCurrentState = i2;
                this.mInterpolator = this.mStopLogic;
            } else if (i != 3) {
                if (i == 4) {
                    this.mDecelerateLogic.config(f2, this.mTransitionLastPosition, this.mScene.getMaxAcceleration());
                    this.mInterpolator = this.mDecelerateLogic;
                } else if (i == 5) {
                    if (willJump(f2, this.mTransitionLastPosition, this.mScene.getMaxAcceleration())) {
                        this.mDecelerateLogic.config(f2, this.mTransitionLastPosition, this.mScene.getMaxAcceleration());
                        this.mInterpolator = this.mDecelerateLogic;
                    } else {
                        this.mStopLogic.config(this.mTransitionLastPosition, f, f2, this.mTransitionDuration, this.mScene.getMaxAcceleration(), this.mScene.getMaxVelocity());
                        this.mLastVelocity = 0.0f;
                        int i3 = this.mCurrentState;
                        if (f != 0.0f) {
                            f3 = 0.0f;
                        }
                        setProgress(f3);
                        this.mCurrentState = i3;
                        this.mInterpolator = this.mStopLogic;
                    }
                }
            }
            this.mTransitionInstantly = false;
            this.mAnimationStartTime = getNanoTime();
            invalidate();
        }
    }

    /* access modifiers changed from: 0000 */
    public void animateTo(float f) {
        if (this.mScene != null) {
            float f2 = this.mTransitionLastPosition;
            float f3 = this.mTransitionPosition;
            if (f2 != f3 && this.mTransitionInstantly) {
                this.mTransitionLastPosition = f3;
            }
            float f4 = this.mTransitionLastPosition;
            if (f4 != f) {
                this.mTemporalInterpolator = false;
                this.mTransitionGoalPosition = f;
                this.mTransitionDuration = ((float) this.mScene.getDuration()) / 1000.0f;
                setProgress(this.mTransitionGoalPosition);
                this.mInterpolator = this.mScene.getInterpolator();
                this.mTransitionInstantly = false;
                this.mAnimationStartTime = getNanoTime();
                this.mInTransition = true;
                this.mTransitionPosition = f4;
                this.mTransitionLastPosition = f4;
                invalidate();
            }
        }
    }

    private void computeCurrentPositions() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            MotionController motionController = (MotionController) this.mFrameArrayList.get(childAt);
            if (motionController != null) {
                motionController.setStartCurrentState(childAt);
            }
        }
    }

    public void transitionToStart() {
        animateTo(0.0f);
    }

    public void transitionToEnd() {
        animateTo(1.0f);
    }

    public void transitionToState(int i) {
        transitionToState(i, -1, -1);
    }

    public void transitionToState(int i, int i2, int i3) {
        MotionScene motionScene = this.mScene;
        if (!(motionScene == null || motionScene.mStateSet == null)) {
            int convertToConstraintSet = this.mScene.mStateSet.convertToConstraintSet(this.mCurrentState, i, (float) i2, (float) i3);
            if (convertToConstraintSet != -1) {
                i = convertToConstraintSet;
            }
        }
        int i4 = this.mCurrentState;
        if (i4 != i) {
            if (this.mBeginState == i) {
                animateTo(0.0f);
            } else if (this.mEndState == i) {
                animateTo(1.0f);
            } else {
                this.mEndState = i;
                if (i4 != -1) {
                    setTransition(i4, i);
                    animateTo(1.0f);
                    this.mTransitionLastPosition = 0.0f;
                    transitionToEnd();
                    return;
                }
                this.mTemporalInterpolator = false;
                this.mTransitionGoalPosition = 1.0f;
                this.mTransitionPosition = 0.0f;
                this.mTransitionLastPosition = 0.0f;
                this.mTransitionLastTime = getNanoTime();
                this.mAnimationStartTime = getNanoTime();
                this.mTransitionInstantly = false;
                this.mInterpolator = null;
                this.mTransitionDuration = ((float) this.mScene.getDuration()) / 1000.0f;
                this.mScene.getStartId();
                int childCount = getChildCount();
                this.mFrameArrayList.clear();
                for (int i5 = 0; i5 < childCount; i5++) {
                    View childAt = getChildAt(i5);
                    this.mFrameArrayList.put(childAt, new MotionController(childAt));
                }
                this.mInTransition = true;
                this.mModel.initFrom(this.mLayoutWidget, null, this.mScene.getConstraintSet(i));
                rebuildScene();
                this.mModel.build();
                computeCurrentPositions();
                int width = getWidth();
                int height = getHeight();
                for (int i6 = 0; i6 < childCount; i6++) {
                    MotionController motionController = (MotionController) this.mFrameArrayList.get(getChildAt(i6));
                    this.mScene.getKeyFrames(motionController);
                    motionController.setup(width, height, this.mTransitionDuration, getNanoTime());
                }
                float staggered = this.mScene.getStaggered();
                if (staggered != 0.0f) {
                    float f = Float.MAX_VALUE;
                    float f2 = -3.4028235E38f;
                    for (int i7 = 0; i7 < childCount; i7++) {
                        MotionController motionController2 = (MotionController) this.mFrameArrayList.get(getChildAt(i7));
                        float finalY = motionController2.getFinalY() + motionController2.getFinalX();
                        f = Math.min(f, finalY);
                        f2 = Math.max(f2, finalY);
                    }
                    for (int i8 = 0; i8 < childCount; i8++) {
                        MotionController motionController3 = (MotionController) this.mFrameArrayList.get(getChildAt(i8));
                        float finalX = motionController3.getFinalX();
                        float finalY2 = motionController3.getFinalY();
                        motionController3.mStaggerScale = 1.0f / (1.0f - staggered);
                        motionController3.mStaggerOffset = staggered - ((((finalX + finalY2) - f) * staggered) / (f2 - f));
                    }
                }
                this.mTransitionPosition = 0.0f;
                this.mTransitionLastPosition = 0.0f;
                this.mInTransition = true;
                invalidate();
            }
        }
    }

    public float getVelocity() {
        Interpolator interpolator = this.mInterpolator;
        if (interpolator == null) {
            return this.mLastVelocity;
        }
        if (interpolator instanceof MotionInterpolator) {
            return ((MotionInterpolator) interpolator).getVelocity();
        }
        return 0.0f;
    }

    public void getViewVelocity(View view, float f, float f2, float[] fArr, int i) {
        float[] fArr2 = fArr;
        int i2 = i;
        float f3 = this.mLastVelocity;
        float f4 = this.mTransitionLastPosition;
        if (this.mInterpolator != null) {
            float signum = Math.signum(this.mTransitionGoalPosition - f4);
            float interpolation = this.mInterpolator.getInterpolation(this.mTransitionLastPosition + 1.0E-5f);
            float interpolation2 = this.mInterpolator.getInterpolation(this.mTransitionLastPosition);
            f3 = (signum * ((interpolation - interpolation2) / 1.0E-5f)) / this.mTransitionDuration;
            f4 = interpolation2;
        }
        Interpolator interpolator = this.mInterpolator;
        if (interpolator instanceof MotionInterpolator) {
            f3 = ((MotionInterpolator) interpolator).getVelocity();
        }
        float f5 = f3;
        View view2 = view;
        MotionController motionController = (MotionController) this.mFrameArrayList.get(view);
        if ((i2 & 1) == 0) {
            motionController.getPostLayoutDvDp(f4, view.getWidth(), view.getHeight(), f, f2, fArr);
        } else {
            float f6 = f;
            float f7 = f2;
            motionController.getDpDt(f4, f, f2, fArr);
        }
        if (i2 < 2) {
            fArr2[0] = fArr2[0] * f5;
            fArr2[1] = fArr2[1] * f5;
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        if (this.mScene == null) {
            super.onMeasure(i, i2);
            return;
        }
        boolean z = (this.mLastWidthMeasureSpec == i && this.mLastHeightMeasureSpec == i2) ? false : true;
        if (this.mNeedsFireTransitionCompleted) {
            this.mNeedsFireTransitionCompleted = false;
            onNewStateAttachHandlers();
            processTransitionCompleted();
            z = true;
        }
        if (this.mDirtyHierarchy) {
            z = true;
        }
        this.mLastWidthMeasureSpec = i;
        this.mLastHeightMeasureSpec = i2;
        int startId = this.mScene.getStartId();
        int endId = this.mScene.getEndId();
        if (z || this.mModel.isNotConfiguredWith(startId, endId)) {
            super.onMeasure(i, i2);
            this.mModel.initFrom(this.mLayoutWidget, this.mScene.getConstraintSet(startId), this.mScene.getConstraintSet(endId));
            this.mModel.reEvaluateState();
            this.mModel.setMeasuredId(startId, endId);
        } else {
            int width = this.mLayoutWidget.getWidth() + getPaddingLeft() + getPaddingRight();
            int height = this.mLayoutWidget.getHeight() + getPaddingTop() + getPaddingBottom();
            if (this.mWidthMeasureMode == Integer.MIN_VALUE) {
                int i3 = this.mStartWrapWidth;
                width = (int) (((float) i3) + (this.mPostInterpolationPosition * ((float) (this.mEndWrapWidth - i3))));
                requestLayout();
            }
            if (this.mHeightMeasureMode == Integer.MIN_VALUE) {
                int i4 = this.mStartWrapHeight;
                height = (int) (((float) i4) + (this.mPostInterpolationPosition * ((float) (this.mEndWrapHeight - i4))));
                requestLayout();
            }
            setMeasuredDimension(width, height);
        }
        evaluateLayout();
    }

    public boolean onStartNestedScroll(View view, View view2, int i, int i2) {
        this.mScrollTarget = view2;
        return true;
    }

    public void onStopNestedScroll(View view, int i) {
        MotionScene motionScene = this.mScene;
        if (motionScene != null) {
            float f = this.mScrollTargetDX;
            float f2 = this.mScrollTargetDT;
            motionScene.processScrollUp(f / f2, this.mScrollTargetDY / f2);
        }
    }

    public void onNestedPreScroll(View view, int i, int i2, int[] iArr, int i3) {
        MotionScene motionScene = this.mScene;
        if (motionScene != null) {
            Transition transition = motionScene.mCurrentTransition;
            if (transition != null && transition.isEnabled()) {
                TouchResponse touchResponse = transition.getTouchResponse();
                if (touchResponse != null) {
                    int touchRegionId = touchResponse.getTouchRegionId();
                    if (!(touchRegionId == -1 || this.mScrollTarget.getId() == touchRegionId)) {
                        return;
                    }
                }
            }
            MotionScene motionScene2 = this.mScene;
            if (motionScene2 == null || !motionScene2.getMoveWhenScrollAtTop() || this.mTransitionPosition == 1.0f || !view.canScrollVertically(-1)) {
                float f = this.mTransitionPosition;
                long nanoTime = getNanoTime();
                float f2 = (float) i;
                this.mScrollTargetDX = f2;
                float f3 = (float) i2;
                this.mScrollTargetDY = f3;
                this.mScrollTargetDT = (float) (((double) (nanoTime - this.mScrollTargetTime)) * 1.0E-9d);
                this.mScrollTargetTime = nanoTime;
                this.mScene.processScrollMove(f2, f3);
                if (f != this.mTransitionPosition) {
                    iArr[0] = i;
                    iArr[1] = i2;
                }
                evaluate(false);
            }
        }
    }

    private /* synthetic */ void lambda$onNestedPreScroll$0() {
        this.mScrollTarget.setNestedScrollingEnabled(true);
    }

    private void debugPos() {
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            StringBuilder sb = new StringBuilder();
            String str = " ";
            sb.append(str);
            sb.append(Debug.getLocation());
            sb.append(str);
            sb.append(Debug.getName(this));
            sb.append(str);
            sb.append(Debug.getName(getContext(), this.mCurrentState));
            sb.append(str);
            sb.append(Debug.getName(childAt));
            sb.append(childAt.getLeft());
            sb.append(str);
            sb.append(childAt.getTop());
            Log.v(TAG, sb.toString());
        }
    }

    /* access modifiers changed from: protected */
    public void dispatchDraw(Canvas canvas) {
        String str;
        evaluate(false);
        super.dispatchDraw(canvas);
        if (this.mScene != null) {
            if ((this.mDebugPath & 1) == 1 && !isInEditMode()) {
                this.mFrames++;
                long nanoTime = getNanoTime();
                long j = this.mLastDrawTime;
                if (j != -1) {
                    long j2 = nanoTime - j;
                    if (j2 > 200000000) {
                        this.mLastFps = ((float) ((int) ((((float) this.mFrames) / (((float) j2) * 1.0E-9f)) * 100.0f))) / 100.0f;
                        this.mFrames = 0;
                        this.mLastDrawTime = nanoTime;
                    }
                } else {
                    this.mLastDrawTime = nanoTime;
                }
                Paint paint = new Paint();
                paint.setTextSize(42.0f);
                float progress = ((float) ((int) (getProgress() * 1000.0f))) / 10.0f;
                StringBuilder sb = new StringBuilder();
                sb.append(this.mLastFps);
                sb.append(" fps ");
                sb.append(Debug.getState(this, this.mBeginState));
                sb.append(" -> ");
                String sb2 = sb.toString();
                StringBuilder sb3 = new StringBuilder();
                sb3.append(sb2);
                sb3.append(Debug.getState(this, this.mEndState));
                sb3.append(" (progress: ");
                sb3.append(progress);
                sb3.append(" ) state=");
                int i = this.mCurrentState;
                if (i == -1) {
                    str = "undefined";
                } else {
                    str = Debug.getState(this, i);
                }
                sb3.append(str);
                String sb4 = sb3.toString();
                paint.setColor(ViewCompat.MEASURED_STATE_MASK);
                canvas.drawText(sb4, 11.0f, (float) (getHeight() - 29), paint);
                paint.setColor(-7864184);
                canvas.drawText(sb4, 10.0f, (float) (getHeight() - 30), paint);
            }
            if (this.mDebugPath > 1) {
                if (this.mDevModeDraw == null) {
                    this.mDevModeDraw = new DevModeDraw();
                }
                this.mDevModeDraw.draw(canvas, this.mFrameArrayList, this.mScene.getDuration(), this.mDebugPath);
            }
        }
    }

    private void evaluateLayout() {
        boolean z;
        float signum = Math.signum(this.mTransitionGoalPosition - this.mTransitionLastPosition);
        long nanoTime = getNanoTime();
        float f = this.mTransitionLastPosition + (!(this.mInterpolator instanceof StopLogic) ? ((((float) (nanoTime - this.mTransitionLastTime)) * signum) * 1.0E-9f) / this.mTransitionDuration : 0.0f);
        if (this.mTransitionInstantly) {
            f = this.mTransitionGoalPosition;
        }
        int i = (signum > 0.0f ? 1 : (signum == 0.0f ? 0 : -1));
        if ((i <= 0 || f < this.mTransitionGoalPosition) && (signum > 0.0f || f > this.mTransitionGoalPosition)) {
            z = false;
        } else {
            f = this.mTransitionGoalPosition;
            z = true;
        }
        Interpolator interpolator = this.mInterpolator;
        if (interpolator != null && !z) {
            if (this.mTemporalInterpolator) {
                f = interpolator.getInterpolation(((float) (nanoTime - this.mAnimationStartTime)) * 1.0E-9f);
            } else {
                f = interpolator.getInterpolation(f);
            }
        }
        if ((i > 0 && f >= this.mTransitionGoalPosition) || (signum <= 0.0f && f <= this.mTransitionGoalPosition)) {
            f = this.mTransitionGoalPosition;
        }
        this.mPostInterpolationPosition = f;
        int childCount = getChildCount();
        long nanoTime2 = getNanoTime();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            MotionController motionController = (MotionController) this.mFrameArrayList.get(childAt);
            if (motionController != null) {
                motionController.interpolate(childAt, f, nanoTime2, this.mKeyCache);
            }
        }
        if (this.mWidthMeasureMode == Integer.MIN_VALUE || this.mHeightMeasureMode == Integer.MIN_VALUE) {
            requestLayout();
        }
    }

    /* access modifiers changed from: 0000 */
    public void evaluate(boolean z) {
        boolean z2;
        float f = this.mTransitionLastPosition;
        if (f > 0.0f && f < 1.0f) {
            this.mCurrentState = -1;
        }
        boolean z3 = false;
        if (this.mKeepAnimating || (this.mInTransition && (z || this.mTransitionGoalPosition != this.mTransitionLastPosition))) {
            float signum = Math.signum(this.mTransitionGoalPosition - this.mTransitionLastPosition);
            long nanoTime = getNanoTime();
            float f2 = this.mTransitionLastPosition + (!(this.mInterpolator instanceof StopLogic) ? ((((float) (nanoTime - this.mTransitionLastTime)) * signum) * 1.0E-9f) / this.mTransitionDuration : 0.0f);
            if (this.mTransitionInstantly) {
                f2 = this.mTransitionGoalPosition;
            }
            int i = (signum > 0.0f ? 1 : (signum == 0.0f ? 0 : -1));
            if ((i <= 0 || f2 < this.mTransitionGoalPosition) && (signum > 0.0f || f2 > this.mTransitionGoalPosition)) {
                z2 = false;
            } else {
                f2 = this.mTransitionGoalPosition;
                this.mInTransition = false;
                z2 = true;
            }
            this.mTransitionLastPosition = f2;
            this.mTransitionLastTime = nanoTime;
            if (this.mListener != null) {
                fireTransitionChange();
            }
            Interpolator interpolator = this.mInterpolator;
            if (interpolator != null && !z2) {
                if (this.mTemporalInterpolator) {
                    f2 = interpolator.getInterpolation(((float) (nanoTime - this.mAnimationStartTime)) * 1.0E-9f);
                    this.mTransitionLastPosition = f2;
                    this.mTransitionLastTime = nanoTime;
                    Interpolator interpolator2 = this.mInterpolator;
                    if ((interpolator2 instanceof MotionInterpolator) && Math.abs(((MotionInterpolator) interpolator2).getVelocity()) <= 1.0E-4f) {
                        this.mInTransition = false;
                    }
                } else {
                    f2 = interpolator.getInterpolation(f2);
                }
            }
            if ((i > 0 && f2 >= this.mTransitionGoalPosition) || (signum <= 0.0f && f2 <= this.mTransitionGoalPosition)) {
                f2 = this.mTransitionGoalPosition;
                this.mInTransition = false;
            }
            if (f2 >= 1.0f || f2 <= 0.0f) {
                this.mInTransition = false;
            }
            int childCount = getChildCount();
            this.mKeepAnimating = false;
            long nanoTime2 = getNanoTime();
            this.mPostInterpolationPosition = f2;
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = getChildAt(i2);
                MotionController motionController = (MotionController) this.mFrameArrayList.get(childAt);
                if (motionController != null) {
                    this.mKeepAnimating |= motionController.interpolate(childAt, f2, nanoTime2, this.mKeyCache);
                }
            }
            if (this.mWidthMeasureMode == Integer.MIN_VALUE || this.mHeightMeasureMode == Integer.MIN_VALUE) {
                requestLayout();
            }
            if (this.mKeepAnimating) {
                invalidate();
            }
            if (this.mInTransition) {
                invalidate();
            }
            if (f2 <= 0.0f) {
                int i3 = this.mBeginState;
                if (i3 != -1) {
                    if (this.mCurrentState != i3) {
                        z3 = true;
                    }
                    int i4 = this.mBeginState;
                    this.mCurrentState = i4;
                    this.mScene.getConstraintSet(i4).applyCustomAttributes(this);
                }
            }
            if (((double) f2) >= 1.0d) {
                if (this.mCurrentState != this.mEndState) {
                    z3 = true;
                }
                int i5 = this.mEndState;
                this.mCurrentState = i5;
                this.mScene.getConstraintSet(i5).applyCustomAttributes(this);
            }
        }
        float f3 = this.mTransitionLastPosition;
        if (f3 >= 1.0f) {
            if (this.mCurrentState != this.mEndState) {
                z3 = true;
            }
            this.mCurrentState = this.mEndState;
        } else if (f3 <= 0.0f) {
            if (this.mCurrentState != this.mBeginState) {
                z3 = true;
            }
            this.mCurrentState = this.mBeginState;
        }
        this.mNeedsFireTransitionCompleted |= z3;
        if (VERSION.SDK_INT >= 18 && z3 && !isInLayout()) {
            requestLayout();
        }
        if (z3) {
            fireTransitionCompleted();
        }
        this.mTransitionPosition = this.mTransitionLastPosition;
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (this.mScene == null) {
            super.onLayout(z, i, i2, i3, i4);
            return;
        }
        int i5 = i3 - i;
        int i6 = i4 - i2;
        if (!(this.mLastLayoutWidth == i5 && this.mLastLayoutHeight == i6)) {
            rebuildScene();
            evaluate(true);
        }
        this.mLastLayoutWidth = i5;
        this.mLastLayoutHeight = i6;
        this.mOldWidth = i5;
        this.mOldHeight = i6;
    }

    /* access modifiers changed from: protected */
    public void parseLayoutDescription(int i) {
        this.mConstraintLayoutSpec = null;
    }

    private void init(AttributeSet attributeSet) {
        IS_IN_EDIT_MODE = isInEditMode();
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, C0215R.styleable.MotionLayout);
            int indexCount = obtainStyledAttributes.getIndexCount();
            boolean z = true;
            for (int i = 0; i < indexCount; i++) {
                int index = obtainStyledAttributes.getIndex(i);
                if (index == C0215R.styleable.MotionLayout_layoutDescription) {
                    this.mScene = new MotionScene(getContext(), this, obtainStyledAttributes.getResourceId(index, -1));
                } else if (index == C0215R.styleable.MotionLayout_currentState) {
                    this.mCurrentState = obtainStyledAttributes.getResourceId(index, -1);
                } else if (index == C0215R.styleable.MotionLayout_motionProgress) {
                    this.mTransitionGoalPosition = obtainStyledAttributes.getFloat(index, 0.0f);
                    this.mInTransition = true;
                } else if (index == C0215R.styleable.MotionLayout_applyMotionScene) {
                    z = obtainStyledAttributes.getBoolean(index, z);
                } else if (index == C0215R.styleable.MotionLayout_showPaths) {
                    if (this.mDebugPath == 0) {
                        this.mDebugPath = obtainStyledAttributes.getBoolean(index, false) ? 2 : 0;
                    }
                } else if (index == C0215R.styleable.MotionLayout_motionDebug) {
                    this.mDebugPath = obtainStyledAttributes.getInt(index, 0);
                }
            }
            obtainStyledAttributes.recycle();
            if (this.mScene == null) {
                Log.e(TAG, "WARNING NO app:layoutDescription tag");
            }
            if (!z) {
                this.mScene = null;
            }
        }
        if (this.mDebugPath != 0) {
            checkStructure();
        }
        if (this.mCurrentState == -1) {
            MotionScene motionScene = this.mScene;
            if (motionScene != null) {
                this.mCurrentState = motionScene.getStartId();
                this.mBeginState = this.mScene.getStartId();
                this.mEndState = this.mScene.getEndId();
            }
        }
    }

    public void setScene(MotionScene motionScene) {
        this.mScene = motionScene;
        rebuildScene();
    }

    private void checkStructure() {
        MotionScene motionScene = this.mScene;
        String str = TAG;
        if (motionScene == null) {
            Log.e(str, "CHECK: motion scene not set! set \"app:layoutDescription=\"@xml/file\"");
            return;
        }
        this.mBeginState = motionScene.getStartId();
        StringBuilder sb = new StringBuilder();
        sb.append("CHECK: start is ");
        sb.append(Debug.getName(getContext(), this.mBeginState));
        Log.v(str, sb.toString());
        this.mEndState = this.mScene.getEndId();
        StringBuilder sb2 = new StringBuilder();
        sb2.append("CHECK: end is ");
        sb2.append(Debug.getName(getContext(), this.mEndState));
        Log.v(str, sb2.toString());
        int i = this.mBeginState;
        checkStructure(i, this.mScene.getConstraintSet(i));
        SparseIntArray sparseIntArray = new SparseIntArray();
        SparseIntArray sparseIntArray2 = new SparseIntArray();
        Iterator it = this.mScene.getDefinedTransitions().iterator();
        while (it.hasNext()) {
            Transition transition = (Transition) it.next();
            if (transition == this.mScene.mCurrentTransition) {
                Log.v(str, "CHECK: CURRENT");
            }
            checkStructure(transition);
            int startConstraintSetId = transition.getStartConstraintSetId();
            int endConstraintSetId = transition.getEndConstraintSetId();
            String name = Debug.getName(getContext(), startConstraintSetId);
            String name2 = Debug.getName(getContext(), endConstraintSetId);
            String str2 = "->";
            if (sparseIntArray.get(startConstraintSetId) == endConstraintSetId) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append("CHECK: two transitions with the same start and end ");
                sb3.append(name);
                sb3.append(str2);
                sb3.append(name2);
                Log.e(str, sb3.toString());
            }
            if (sparseIntArray2.get(endConstraintSetId) == startConstraintSetId) {
                StringBuilder sb4 = new StringBuilder();
                sb4.append("CHECK: you can't have reverse transitions");
                sb4.append(name);
                sb4.append(str2);
                sb4.append(name2);
                Log.e(str, sb4.toString());
            }
            sparseIntArray.put(startConstraintSetId, endConstraintSetId);
            sparseIntArray2.put(endConstraintSetId, startConstraintSetId);
            if (this.mScene.getConstraintSet(startConstraintSetId) == null) {
                StringBuilder sb5 = new StringBuilder();
                sb5.append(" no such constraintSetStart ");
                sb5.append(name);
                Log.e(str, sb5.toString());
            }
            if (this.mScene.getConstraintSet(endConstraintSetId) == null) {
                StringBuilder sb6 = new StringBuilder();
                sb6.append(" no such constraintSetEnd ");
                sb6.append(name);
                Log.e(str, sb6.toString());
            }
        }
    }

    private void checkStructure(int i, ConstraintSet constraintSet) {
        String str;
        String str2;
        String name = Debug.getName(getContext(), i);
        int childCount = getChildCount();
        int i2 = 0;
        while (true) {
            str = "CHECK: ";
            str2 = TAG;
            if (i2 >= childCount) {
                break;
            }
            View childAt = getChildAt(i2);
            int id = childAt.getId();
            if (id == -1) {
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                sb.append(name);
                sb.append(" ALL VIEWS SHOULD HAVE ID's ");
                sb.append(childAt.getClass().getName());
                sb.append(" does not!");
                Log.w(str2, sb.toString());
            }
            if (constraintSet.getConstraint(id) == null) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(str);
                sb2.append(name);
                sb2.append(" NO CONSTRAINTS for ");
                sb2.append(Debug.getName(childAt));
                Log.w(str2, sb2.toString());
            }
            i2++;
        }
        int[] knownIds = constraintSet.getKnownIds();
        for (int i3 = 0; i3 < knownIds.length; i3++) {
            int i4 = knownIds[i3];
            String name2 = Debug.getName(getContext(), i4);
            if (findViewById(knownIds[i3]) == null) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append(str);
                sb3.append(name);
                sb3.append(" NO View matches id ");
                sb3.append(name2);
                Log.w(str2, sb3.toString());
            }
            String str3 = ") no LAYOUT_HEIGHT";
            String str4 = "(";
            if (constraintSet.getHeight(i4) == -1) {
                StringBuilder sb4 = new StringBuilder();
                sb4.append(str);
                sb4.append(name);
                sb4.append(str4);
                sb4.append(name2);
                sb4.append(str3);
                Log.w(str2, sb4.toString());
            }
            if (constraintSet.getWidth(i4) == -1) {
                StringBuilder sb5 = new StringBuilder();
                sb5.append(str);
                sb5.append(name);
                sb5.append(str4);
                sb5.append(name2);
                sb5.append(str3);
                Log.w(str2, sb5.toString());
            }
        }
    }

    private void checkStructure(Transition transition) {
        StringBuilder sb = new StringBuilder();
        sb.append("CHECK: transition = ");
        sb.append(transition.debugString(getContext()));
        String sb2 = sb.toString();
        String str = TAG;
        Log.v(str, sb2);
        StringBuilder sb3 = new StringBuilder();
        sb3.append("CHECK: transition.setDuration = ");
        sb3.append(transition.getDuration());
        Log.v(str, sb3.toString());
        if (transition.getStartConstraintSetId() == transition.getEndConstraintSetId()) {
            Log.e(str, "CHECK: start and end constraint set should not be the same!");
        }
    }

    public void setDebugMode(int i) {
        this.mDebugPath = i;
        invalidate();
    }

    public void getDebugMode(boolean z) {
        this.mDebugPath = z ? 2 : 1;
        invalidate();
    }

    private boolean handlesTouchEvent(float f, float f2, View view, MotionEvent motionEvent) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                if (handlesTouchEvent(((float) view.getLeft()) + f, ((float) view.getTop()) + f2, viewGroup.getChildAt(i), motionEvent)) {
                    return true;
                }
            }
        }
        this.mBoundsCheck.set(((float) view.getLeft()) + f, ((float) view.getTop()) + f2, f + ((float) view.getRight()), f2 + ((float) view.getBottom()));
        if (motionEvent.getAction() == 0) {
            return this.mBoundsCheck.contains(motionEvent.getX(), motionEvent.getY()) && view.onTouchEvent(motionEvent);
        }
        if (view.onTouchEvent(motionEvent)) {
            return true;
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        MotionScene motionScene = this.mScene;
        if (motionScene == null) {
            return false;
        }
        Transition transition = motionScene.mCurrentTransition;
        if (transition != null && transition.isEnabled()) {
            TouchResponse touchResponse = transition.getTouchResponse();
            if (touchResponse != null) {
                int touchRegionId = touchResponse.getTouchRegionId();
                if (touchRegionId != -1) {
                    View view = this.mRegionView;
                    if (view == null || view.getId() != touchRegionId) {
                        this.mRegionView = findViewById(touchRegionId);
                    }
                    View view2 = this.mRegionView;
                    if (view2 != null) {
                        this.mBoundsCheck.set((float) view2.getLeft(), (float) this.mRegionView.getTop(), (float) this.mRegionView.getRight(), (float) this.mRegionView.getBottom());
                        if (this.mBoundsCheck.contains(motionEvent.getX(), motionEvent.getY()) && !handlesTouchEvent(0.0f, 0.0f, this.mRegionView, motionEvent)) {
                            return onTouchEvent(motionEvent);
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        MotionScene motionScene = this.mScene;
        if (motionScene == null || !motionScene.supportTouch()) {
            return super.onTouchEvent(motionEvent);
        }
        this.mScene.processTouchEvent(motionEvent, getCurrentState(), this);
        return true;
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        MotionScene motionScene = this.mScene;
        if (motionScene != null) {
            int i = this.mCurrentState;
            if (i != -1) {
                ConstraintSet constraintSet = motionScene.getConstraintSet(i);
                this.mScene.readFallback(this);
                if (constraintSet != null) {
                    constraintSet.applyTo(this);
                }
            }
        }
        onNewStateAttachHandlers();
    }

    private void onNewStateAttachHandlers() {
        MotionScene motionScene = this.mScene;
        if (motionScene != null && !motionScene.autoTransition(this, this.mCurrentState)) {
            int i = this.mCurrentState;
            if (i != -1) {
                this.mScene.addOnClickListeners(this, i);
            }
            if (this.mScene.supportTouch()) {
                this.mScene.setupTouch();
            }
        }
    }

    public int getCurrentState() {
        return this.mCurrentState;
    }

    public float getProgress() {
        return this.mTransitionLastPosition;
    }

    /* access modifiers changed from: 0000 */
    public void getAnchorDpDt(int i, float f, float f2, float f3, float[] fArr) {
        String str;
        HashMap<View, MotionController> hashMap = this.mFrameArrayList;
        View viewById = getViewById(i);
        MotionController motionController = (MotionController) hashMap.get(viewById);
        if (motionController != null) {
            motionController.getDpDt(f, f2, f3, fArr);
            float y = viewById.getY();
            float f4 = f - this.lastPos;
            float f5 = this.lastY;
            int i2 = (f4 > 0.0f ? 1 : (f4 == 0.0f ? 0 : -1));
            this.lastPos = f;
            this.lastY = y;
            return;
        }
        if (viewById == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("");
            sb.append(i);
            str = sb.toString();
        } else {
            str = viewById.getContext().getResources().getResourceName(i);
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("WARNING could not find view id ");
        sb2.append(str);
        Log.w(TAG, sb2.toString());
    }

    public long getTransitionTimeMs() {
        MotionScene motionScene = this.mScene;
        if (motionScene != null) {
            this.mTransitionDuration = ((float) motionScene.getDuration()) / 1000.0f;
        }
        return ((long) this.mTransitionDuration) * 1000;
    }

    public void setTransitionListener(TransitionListener transitionListener) {
        this.mListener = transitionListener;
    }

    public void fireTrigger(int i, boolean z, float f) {
        TransitionListener transitionListener = this.mListener;
        if (transitionListener != null) {
            transitionListener.onTransitionTrigger(this, i, z, f);
        }
    }

    private void fireTransitionChange() {
        TransitionListener transitionListener = this.mListener;
        if (transitionListener != null && this.mListenerPosition != this.mTransitionPosition) {
            if (this.mListenerState != -1) {
                transitionListener.onTransitionStarted(this, this.mBeginState, this.mEndState);
            }
            this.mListenerState = -1;
            float f = this.mTransitionPosition;
            this.mListenerPosition = f;
            this.mListener.onTransitionChange(this, this.mBeginState, this.mEndState, f);
        }
    }

    /* access modifiers changed from: protected */
    public void fireTransitionCompleted() {
        if (this.mListener != null) {
            int i = -1;
            if (this.mListenerState == -1) {
                this.mListenerState = this.mCurrentState;
                if (!this.mTransitionCompleted.isEmpty()) {
                    ArrayList<Integer> arrayList = this.mTransitionCompleted;
                    i = ((Integer) arrayList.get(arrayList.size() - 1)).intValue();
                }
                int i2 = this.mCurrentState;
                if (i != i2) {
                    this.mTransitionCompleted.add(Integer.valueOf(i2));
                }
            }
        }
    }

    private void processTransitionCompleted() {
        if (this.mListener != null) {
            Iterator it = this.mTransitionCompleted.iterator();
            while (it.hasNext()) {
                this.mListener.onTransitionCompleted(this, ((Integer) it.next()).intValue());
            }
            this.mTransitionCompleted.clear();
        }
    }

    public DesignTool getDesignTool() {
        if (this.mDesignTool == null) {
            this.mDesignTool = new DesignTool(this);
        }
        return this.mDesignTool;
    }

    public void onViewAdded(View view) {
        super.onViewAdded(view);
        if (view instanceof MotionHelper) {
            MotionHelper motionHelper = (MotionHelper) view;
            if (motionHelper.isUsedOnShow()) {
                if (this.mOnShowHelpers == null) {
                    this.mOnShowHelpers = new ArrayList<>();
                }
                this.mOnShowHelpers.add(motionHelper);
            }
            if (motionHelper.isUseOnHide()) {
                if (this.mOnHideHelpers == null) {
                    this.mOnHideHelpers = new ArrayList<>();
                }
                this.mOnHideHelpers.add(motionHelper);
            }
        }
    }

    public void onViewRemoved(View view) {
        super.onViewRemoved(view);
        ArrayList<MotionHelper> arrayList = this.mOnShowHelpers;
        if (arrayList != null) {
            arrayList.remove(view);
        }
        ArrayList<MotionHelper> arrayList2 = this.mOnHideHelpers;
        if (arrayList2 != null) {
            arrayList2.remove(view);
        }
    }

    public void setOnShow(float f) {
        ArrayList<MotionHelper> arrayList = this.mOnShowHelpers;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                ((MotionHelper) this.mOnShowHelpers.get(i)).setProgress(f);
            }
        }
    }

    public void setOnHide(float f) {
        ArrayList<MotionHelper> arrayList = this.mOnHideHelpers;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                ((MotionHelper) this.mOnHideHelpers.get(i)).setProgress(f);
            }
        }
    }

    public int[] getConstraintSetIds() {
        MotionScene motionScene = this.mScene;
        if (motionScene == null) {
            return null;
        }
        return motionScene.getConstraintSetIds();
    }

    public ConstraintSet getConstraintSet(int i) {
        MotionScene motionScene = this.mScene;
        if (motionScene == null) {
            return null;
        }
        return motionScene.getConstraintSet(i);
    }

    @Deprecated
    public void rebuildMotion() {
        Log.e(TAG, "This method is deprecated. Please call rebuildScene() instead.");
        rebuildScene();
    }

    public void rebuildScene() {
        this.mModel.reEvaluateState();
        invalidate();
    }

    public void updateState(int i, ConstraintSet constraintSet) {
        MotionScene motionScene = this.mScene;
        if (motionScene != null) {
            motionScene.setConstraintSet(i, constraintSet);
        }
        updateState();
    }

    public void updateState() {
        this.mModel.initFrom(this.mLayoutWidget, this.mScene.getConstraintSet(this.mBeginState), this.mScene.getConstraintSet(this.mEndState));
        rebuildScene();
    }

    public ArrayList<Transition> getDefinedTransitions() {
        MotionScene motionScene = this.mScene;
        if (motionScene == null) {
            return null;
        }
        return motionScene.getDefinedTransitions();
    }

    public int getStartState() {
        return this.mBeginState;
    }

    public int getEndState() {
        return this.mEndState;
    }

    public float getTargetPosition() {
        return this.mTransitionGoalPosition;
    }

    public void setTransitionDuration(int i) {
        MotionScene motionScene = this.mScene;
        if (motionScene == null) {
            Log.e(TAG, "MotionScene not defined");
        } else {
            motionScene.setDuration(i);
        }
    }

    public Transition getTransition(int i) {
        return this.mScene.getTransitionById(i);
    }

    /* access modifiers changed from: 0000 */
    public int lookUpConstraintId(String str) {
        MotionScene motionScene = this.mScene;
        if (motionScene == null) {
            return 0;
        }
        return motionScene.lookUpConstraintId(str);
    }

    /* access modifiers changed from: 0000 */
    public String getConstraintSetNames(int i) {
        MotionScene motionScene = this.mScene;
        if (motionScene == null) {
            return null;
        }
        return motionScene.lookUpConstraintName(i);
    }

    /* access modifiers changed from: 0000 */
    public void disableAutoTransition(boolean z) {
        MotionScene motionScene = this.mScene;
        if (motionScene != null) {
            motionScene.disableAutoTransition(z);
        }
    }
}
