package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.C0215R;
import androidx.core.widget.NestedScrollView;
import androidx.core.widget.NestedScrollView.OnScrollChangeListener;
import org.xmlpull.v1.XmlPullParser;

class TouchResponse {
    private static final boolean DEBUG = false;
    private static final String TAG = "TouchResponse";
    private static final float[][] TOUCH_DIRECTION = {new float[]{0.0f, -1.0f}, new float[]{0.0f, 1.0f}, new float[]{-1.0f, 0.0f}, new float[]{1.0f, 0.0f}};
    private static final float[][] TOUCH_SIDES = {new float[]{0.5f, 0.0f}, new float[]{0.0f, 0.5f}, new float[]{1.0f, 0.5f}, new float[]{0.5f, 1.0f}, new float[]{0.5f, 0.5f}};
    private float[] mAnchorDpDt = new float[2];
    private float mDragScale = 1.0f;
    private boolean mDragStarted = false;
    private float mLastTouchX;
    private float mLastTouchY;
    private float mMaxAcceleration = 1.2f;
    private float mMaxVelocity = 4.0f;
    private final MotionLayout mMotionLayout;
    private boolean mMoveWhenScrollAtTop = true;
    private int mOnTouchUp = 0;
    private int mTouchAnchorId = -1;
    private int mTouchAnchorSide = 0;
    private float mTouchAnchorX = 0.5f;
    private float mTouchAnchorY = 0.5f;
    private float mTouchDirectionX = 0.0f;
    private float mTouchDirectionY = 1.0f;
    private int mTouchRegionId = -1;
    private int mTouchSide = 0;

    TouchResponse(Context context, MotionLayout motionLayout, XmlPullParser xmlPullParser) {
        this.mMotionLayout = motionLayout;
        fillFromAttributeList(context, Xml.asAttributeSet(xmlPullParser));
    }

    private void fillFromAttributeList(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0215R.styleable.OnSwipe);
        fill(obtainStyledAttributes);
        obtainStyledAttributes.recycle();
    }

    private void fill(TypedArray typedArray) {
        int indexCount = typedArray.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = typedArray.getIndex(i);
            if (index == C0215R.styleable.OnSwipe_touchAnchorId) {
                this.mTouchAnchorId = typedArray.getResourceId(index, this.mTouchAnchorId);
            } else if (index == C0215R.styleable.OnSwipe_touchAnchorSide) {
                this.mTouchAnchorSide = typedArray.getInt(index, this.mTouchAnchorSide);
                float[][] fArr = TOUCH_SIDES;
                int i2 = this.mTouchAnchorSide;
                this.mTouchAnchorX = fArr[i2][0];
                this.mTouchAnchorY = fArr[i2][1];
            } else if (index == C0215R.styleable.OnSwipe_dragDirection) {
                this.mTouchSide = typedArray.getInt(index, this.mTouchSide);
                float[][] fArr2 = TOUCH_DIRECTION;
                int i3 = this.mTouchSide;
                this.mTouchDirectionX = fArr2[i3][0];
                this.mTouchDirectionY = fArr2[i3][1];
            } else if (index == C0215R.styleable.OnSwipe_maxVelocity) {
                this.mMaxVelocity = typedArray.getFloat(index, this.mMaxVelocity);
            } else if (index == C0215R.styleable.OnSwipe_maxAcceleration) {
                this.mMaxAcceleration = typedArray.getFloat(index, this.mMaxAcceleration);
            } else if (index == C0215R.styleable.OnSwipe_moveWhenScrollAtTop) {
                this.mMoveWhenScrollAtTop = typedArray.getBoolean(index, this.mMoveWhenScrollAtTop);
            } else if (index == C0215R.styleable.OnSwipe_dragScale) {
                this.mDragScale = typedArray.getFloat(index, this.mDragScale);
            } else if (index == C0215R.styleable.OnSwipe_touchRegionId) {
                this.mTouchRegionId = typedArray.getResourceId(index, this.mTouchRegionId);
            } else if (index == C0215R.styleable.OnSwipe_onTouchUp) {
                this.mOnTouchUp = typedArray.getInt(index, this.mOnTouchUp);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void setUpTouchEvent(float f, float f2) {
        this.mLastTouchX = f;
        this.mLastTouchY = f2;
        this.mDragStarted = false;
    }

    /* access modifiers changed from: 0000 */
    public void processTouchEvent(MotionEvent motionEvent, MotionTracker motionTracker, int i, MotionScene motionScene) {
        float f;
        float f2;
        MotionTracker motionTracker2 = motionTracker;
        motionTracker2.addMovement(motionEvent);
        int action = motionEvent.getAction();
        if (action != 0) {
            float f3 = 1.0f;
            if (action == 1) {
                this.mDragStarted = false;
                motionTracker2.computeCurrentVelocity(1000);
                float xVelocity = motionTracker.getXVelocity();
                float yVelocity = motionTracker.getYVelocity();
                float progress = this.mMotionLayout.getProgress();
                int i2 = this.mTouchAnchorId;
                if (i2 != -1) {
                    this.mMotionLayout.getAnchorDpDt(i2, progress, this.mTouchAnchorX, this.mTouchAnchorY, this.mAnchorDpDt);
                } else {
                    float min = (float) Math.min(this.mMotionLayout.getWidth(), this.mMotionLayout.getHeight());
                    float[] fArr = this.mAnchorDpDt;
                    fArr[1] = this.mTouchDirectionY * min;
                    fArr[0] = min * this.mTouchDirectionX;
                }
                float f4 = this.mTouchDirectionX;
                float[] fArr2 = this.mAnchorDpDt;
                float f5 = fArr2[0];
                float f6 = this.mTouchDirectionY;
                float f7 = fArr2[1];
                if (f4 != 0.0f) {
                    f = xVelocity / fArr2[0];
                } else {
                    f = yVelocity / fArr2[1];
                }
                if (!Float.isNaN(f)) {
                    progress += f / 3.0f;
                }
                if (progress != 0.0f && progress != 1.0f) {
                    int i3 = this.mOnTouchUp;
                    if (i3 != 3) {
                        MotionLayout motionLayout = this.mMotionLayout;
                        if (((double) progress) < 0.5d) {
                            f3 = 0.0f;
                        }
                        motionLayout.touchAnimateTo(i3, f3, f);
                    }
                }
            } else if (action == 2) {
                float rawY = motionEvent.getRawY() - this.mLastTouchY;
                float rawX = motionEvent.getRawX() - this.mLastTouchX;
                if (Math.abs((this.mTouchDirectionX * rawX) + (this.mTouchDirectionY * rawY)) > 10.0f || this.mDragStarted) {
                    float progress2 = this.mMotionLayout.getProgress();
                    if (!this.mDragStarted) {
                        this.mDragStarted = true;
                        this.mMotionLayout.setProgress(progress2);
                    }
                    int i4 = this.mTouchAnchorId;
                    if (i4 != -1) {
                        this.mMotionLayout.getAnchorDpDt(i4, progress2, this.mTouchAnchorX, this.mTouchAnchorY, this.mAnchorDpDt);
                    } else {
                        float min2 = (float) Math.min(this.mMotionLayout.getWidth(), this.mMotionLayout.getHeight());
                        float[] fArr3 = this.mAnchorDpDt;
                        fArr3[1] = this.mTouchDirectionY * min2;
                        fArr3[0] = min2 * this.mTouchDirectionX;
                    }
                    float f8 = this.mTouchDirectionX;
                    float[] fArr4 = this.mAnchorDpDt;
                    if (((double) Math.abs(((f8 * fArr4[0]) + (this.mTouchDirectionY * fArr4[1])) * this.mDragScale)) < 0.01d) {
                        float[] fArr5 = this.mAnchorDpDt;
                        fArr5[0] = 0.01f;
                        fArr5[1] = 0.01f;
                    }
                    if (this.mTouchDirectionX != 0.0f) {
                        f2 = rawX / this.mAnchorDpDt[0];
                    } else {
                        f2 = rawY / this.mAnchorDpDt[1];
                    }
                    float max = Math.max(Math.min(progress2 + f2, 1.0f), 0.0f);
                    if (max != this.mMotionLayout.getProgress()) {
                        this.mMotionLayout.setProgress(max);
                        motionTracker2.computeCurrentVelocity(1000);
                        this.mMotionLayout.mLastVelocity = this.mTouchDirectionX != 0.0f ? motionTracker.getXVelocity() / this.mAnchorDpDt[0] : motionTracker.getYVelocity() / this.mAnchorDpDt[1];
                    } else {
                        this.mMotionLayout.mLastVelocity = 0.0f;
                    }
                    this.mLastTouchX = motionEvent.getRawX();
                    this.mLastTouchY = motionEvent.getRawY();
                }
            }
        } else {
            this.mLastTouchX = motionEvent.getRawX();
            this.mLastTouchY = motionEvent.getRawY();
            this.mDragStarted = false;
        }
    }

    /* access modifiers changed from: 0000 */
    public void setDown(float f, float f2) {
        this.mLastTouchX = f;
        this.mLastTouchY = f2;
    }

    /* access modifiers changed from: 0000 */
    public float getProgressDirection(float f, float f2) {
        this.mMotionLayout.getAnchorDpDt(this.mTouchAnchorId, this.mMotionLayout.getProgress(), this.mTouchAnchorX, this.mTouchAnchorY, this.mAnchorDpDt);
        if (this.mTouchDirectionX != 0.0f) {
            float[] fArr = this.mAnchorDpDt;
            if (fArr[0] == 0.0f) {
                fArr[0] = 1.0E-7f;
            }
            return (f * this.mTouchDirectionX) / this.mAnchorDpDt[0];
        }
        float[] fArr2 = this.mAnchorDpDt;
        if (fArr2[1] == 0.0f) {
            fArr2[1] = 1.0E-7f;
        }
        return (f2 * this.mTouchDirectionY) / this.mAnchorDpDt[1];
    }

    /* access modifiers changed from: 0000 */
    public void scrollUp(float f, float f2) {
        float f3;
        boolean z = false;
        this.mDragStarted = false;
        float progress = this.mMotionLayout.getProgress();
        this.mMotionLayout.getAnchorDpDt(this.mTouchAnchorId, progress, this.mTouchAnchorX, this.mTouchAnchorY, this.mAnchorDpDt);
        float f4 = this.mTouchDirectionX;
        float[] fArr = this.mAnchorDpDt;
        float f5 = fArr[0];
        float f6 = this.mTouchDirectionY;
        float f7 = fArr[1];
        if (f4 != 0.0f) {
            f3 = (f * f4) / fArr[0];
        } else {
            f3 = (f2 * f6) / fArr[1];
        }
        if (!Float.isNaN(f3)) {
            progress += f3 / 3.0f;
        }
        if (progress != 0.0f) {
            float f8 = 1.0f;
            boolean z2 = progress != 1.0f;
            if (this.mOnTouchUp != 3) {
                z = true;
            }
            if (z && z2) {
                MotionLayout motionLayout = this.mMotionLayout;
                int i = this.mOnTouchUp;
                if (((double) progress) < 0.5d) {
                    f8 = 0.0f;
                }
                motionLayout.touchAnimateTo(i, f8, f3);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void scrollMove(float f, float f2) {
        float f3;
        float f4 = this.mTouchDirectionX;
        float f5 = this.mTouchDirectionY;
        float progress = this.mMotionLayout.getProgress();
        if (!this.mDragStarted) {
            this.mDragStarted = true;
            this.mMotionLayout.setProgress(progress);
        }
        this.mMotionLayout.getAnchorDpDt(this.mTouchAnchorId, progress, this.mTouchAnchorX, this.mTouchAnchorY, this.mAnchorDpDt);
        float f6 = this.mTouchDirectionX;
        float[] fArr = this.mAnchorDpDt;
        if (((double) Math.abs((f6 * fArr[0]) + (this.mTouchDirectionY * fArr[1]))) < 0.01d) {
            float[] fArr2 = this.mAnchorDpDt;
            fArr2[0] = 0.01f;
            fArr2[1] = 0.01f;
        }
        float f7 = this.mTouchDirectionX;
        if (f7 != 0.0f) {
            f3 = (f * f7) / this.mAnchorDpDt[0];
        } else {
            f3 = (f2 * this.mTouchDirectionY) / this.mAnchorDpDt[1];
        }
        float max = Math.max(Math.min(progress + f3, 1.0f), 0.0f);
        if (max != this.mMotionLayout.getProgress()) {
            this.mMotionLayout.setProgress(max);
        }
    }

    /* access modifiers changed from: 0000 */
    public void setupTouch() {
        View findViewById = this.mMotionLayout.findViewById(this.mTouchAnchorId);
        if (findViewById == null) {
            Log.w(TAG, " cannot find view to handle touch");
        }
        if (findViewById instanceof NestedScrollView) {
            NestedScrollView nestedScrollView = (NestedScrollView) findViewById;
            nestedScrollView.setOnTouchListener(new OnTouchListener() {
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    return false;
                }
            });
            nestedScrollView.setOnScrollChangeListener(new OnScrollChangeListener() {
                public void onScrollChange(NestedScrollView nestedScrollView, int i, int i2, int i3, int i4) {
                }
            });
        }
    }

    public void setAnchorId(int i) {
        this.mTouchAnchorId = i;
    }

    public int getAnchorId() {
        return this.mTouchAnchorId;
    }

    public void setTouchAnchorLocation(float f, float f2) {
        this.mTouchAnchorX = f;
        this.mTouchAnchorY = f2;
    }

    public void setMaxVelocity(float f) {
        this.mMaxVelocity = f;
    }

    public void setMaxAcceleration(float f) {
        this.mMaxAcceleration = f;
    }

    /* access modifiers changed from: 0000 */
    public float getMaxAcceleration() {
        return this.mMaxAcceleration;
    }

    public float getMaxVelocity() {
        return this.mMaxVelocity;
    }

    /* access modifiers changed from: 0000 */
    public boolean getMoveWhenScrollAtTop() {
        return this.mMoveWhenScrollAtTop;
    }

    /* access modifiers changed from: 0000 */
    public RectF getTouchRegion(ViewGroup viewGroup, RectF rectF) {
        int i = this.mTouchRegionId;
        if (i == -1) {
            return null;
        }
        View findViewById = viewGroup.findViewById(i);
        if (findViewById == null) {
            return null;
        }
        rectF.set((float) findViewById.getLeft(), (float) findViewById.getTop(), (float) findViewById.getRight(), (float) findViewById.getBottom());
        return rectF;
    }

    /* access modifiers changed from: 0000 */
    public int getTouchRegionId() {
        return this.mTouchRegionId;
    }

    /* access modifiers changed from: 0000 */
    public float dot(float f, float f2) {
        return (f * this.mTouchDirectionX) + (f2 * this.mTouchDirectionY);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.mTouchDirectionX);
        sb.append(" , ");
        sb.append(this.mTouchDirectionY);
        return sb.toString();
    }
}
