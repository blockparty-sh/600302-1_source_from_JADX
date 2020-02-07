package com.lijiankun24.shadowlayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class ShadowLayout extends RelativeLayout {
    public static final int ALL = 4369;
    public static final int BOTTOM = 4096;
    public static final int LEFT = 1;
    public static final int RIGHT = 256;
    public static final int SHAPE_OVAL = 16;
    public static final int SHAPE_RECTANGLE = 1;
    public static final int TOP = 16;
    private Paint mPaint;
    private RectF mRectF;
    private int mShadowColor;
    private float mShadowDx;
    private float mShadowDy;
    private float mShadowRadius;
    private int mShadowShape;
    private int mShadowSide;

    public ShadowLayout(Context context) {
        this(context, null);
    }

    public ShadowLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ShadowLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mPaint = new Paint(1);
        this.mRectF = new RectF();
        this.mShadowColor = 0;
        this.mShadowRadius = 0.0f;
        this.mShadowDx = 0.0f;
        this.mShadowDy = 0.0f;
        this.mShadowSide = ALL;
        this.mShadowShape = 1;
        init(attributeSet);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int i3;
        float f;
        int i4;
        float f2;
        int i5;
        super.onMeasure(i, i2);
        float dip2px = this.mShadowRadius + dip2px(5.0f);
        float measuredWidth = (float) getMeasuredWidth();
        float measuredHeight = (float) getMeasuredHeight();
        getWidth();
        int i6 = 0;
        if ((this.mShadowSide & 1) == 1) {
            i3 = (int) dip2px;
            f = dip2px;
        } else {
            f = 0.0f;
            i3 = 0;
        }
        if ((this.mShadowSide & 16) == 16) {
            i4 = (int) dip2px;
            f2 = dip2px;
        } else {
            f2 = 0.0f;
            i4 = 0;
        }
        if ((this.mShadowSide & 256) == 256) {
            measuredWidth = ((float) getMeasuredWidth()) - dip2px;
            i5 = (int) dip2px;
        } else {
            i5 = 0;
        }
        if ((this.mShadowSide & 4096) == 4096) {
            measuredHeight = ((float) getMeasuredHeight()) - dip2px;
            i6 = (int) dip2px;
        }
        float f3 = this.mShadowDy;
        if (f3 != 0.0f) {
            measuredHeight -= f3;
            i6 += (int) f3;
        }
        float f4 = this.mShadowDx;
        if (f4 != 0.0f) {
            measuredWidth -= f4;
            i5 += (int) f4;
        }
        RectF rectF = this.mRectF;
        rectF.left = f;
        rectF.top = f2;
        rectF.right = measuredWidth;
        rectF.bottom = measuredHeight;
        setPadding(i3, i4, i5, i6);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setUpShadowPaint();
        int i = this.mShadowShape;
        if (i == 1) {
            canvas.drawRect(this.mRectF, this.mPaint);
        } else if (i == 16) {
            canvas.drawCircle(this.mRectF.centerX(), this.mRectF.centerY(), Math.min(this.mRectF.width(), this.mRectF.height()) / 2.0f, this.mPaint);
        }
    }

    public void setShadowColor(int i) {
        this.mShadowColor = i;
        requestLayout();
        postInvalidate();
    }

    public void setShadowRadius(float f) {
        this.mShadowRadius = f;
        requestLayout();
        postInvalidate();
    }

    private void init(AttributeSet attributeSet) {
        setLayerType(1, null);
        setWillNotDraw(false);
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, C2447R.styleable.ShadowLayout);
        if (obtainStyledAttributes != null) {
            this.mShadowColor = obtainStyledAttributes.getColor(C2447R.styleable.ShadowLayout_shadowColor, getContext().getResources().getColor(17170444));
            this.mShadowRadius = obtainStyledAttributes.getDimension(C2447R.styleable.ShadowLayout_shadowRadius, dip2px(0.0f));
            this.mShadowDx = obtainStyledAttributes.getDimension(C2447R.styleable.ShadowLayout_shadowDx, dip2px(0.0f));
            this.mShadowDy = obtainStyledAttributes.getDimension(C2447R.styleable.ShadowLayout_shadowDy, dip2px(0.0f));
            this.mShadowSide = obtainStyledAttributes.getInt(C2447R.styleable.ShadowLayout_shadowSide, ALL);
            this.mShadowShape = obtainStyledAttributes.getInt(C2447R.styleable.ShadowLayout_shadowShape, 1);
            obtainStyledAttributes.recycle();
        }
        setUpShadowPaint();
    }

    private void setUpShadowPaint() {
        this.mPaint.reset();
        this.mPaint.setAntiAlias(true);
        this.mPaint.setColor(0);
        this.mPaint.setShadowLayer(this.mShadowRadius, this.mShadowDx, this.mShadowDy, this.mShadowColor);
    }

    private float dip2px(float f) {
        return (f * getContext().getResources().getDisplayMetrics().density) + 0.5f;
    }
}
