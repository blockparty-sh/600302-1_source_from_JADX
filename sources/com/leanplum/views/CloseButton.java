package com.leanplum.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.core.view.ViewCompat;
import com.leanplum.utils.SizeUtil;

public class CloseButton extends View {
    private Paint circlePaint = new Paint();
    private Paint circlePressedPaint = new Paint();
    private boolean isPressed = false;
    private Paint linePaint = new Paint();
    private float size;

    /* renamed from: x1 */
    private float f647x1;

    /* renamed from: x2 */
    private float f648x2;

    /* renamed from: y1 */
    private float f649y1;

    /* renamed from: y2 */
    private float f650y2;

    public CloseButton(Context context) {
        super(context);
        initLabelView();
    }

    public CloseButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initLabelView();
    }

    private void initLabelView() {
        this.circlePaint.setAntiAlias(true);
        this.circlePaint.setColor(-2236963);
        this.circlePaint.setStrokeWidth(2.0f);
        this.circlePaint.setStyle(Style.FILL_AND_STROKE);
        this.circlePressedPaint.setAntiAlias(true);
        this.circlePressedPaint.setColor(-6710887);
        this.circlePressedPaint.setStrokeWidth(2.0f);
        this.circlePressedPaint.setStyle(Style.FILL_AND_STROKE);
        this.linePaint.setAntiAlias(true);
        this.linePaint.setColor(ViewCompat.MEASURED_STATE_MASK);
        this.linePaint.setStrokeWidth(3.0f);
        this.linePaint.setStyle(Style.FILL_AND_STROKE);
        this.size = (float) SizeUtil.dp30;
        float f = this.size;
        this.f647x1 = f * 0.33333334f;
        this.f648x2 = f * 0.6666667f;
        this.f649y1 = 0.33333334f * f;
        this.f650y2 = f * 0.6666667f;
    }

    public boolean performClick() {
        return super.performClick();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            this.isPressed = true;
            invalidate();
            return true;
        } else if (motionEvent.getAction() != 1) {
            return super.onTouchEvent(motionEvent);
        } else {
            this.isPressed = false;
            invalidate();
            performClick();
            return true;
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i);
        float f = this.size;
        setMeasuredDimension((int) f, (int) f);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(((float) getWidth()) / 2.0f, ((float) getHeight()) / 2.0f, (((float) getWidth()) / 2.0f) - 1.0f, this.isPressed ? this.circlePressedPaint : this.circlePaint);
        canvas.drawLine(this.f647x1, this.f649y1, this.f648x2, this.f650y2, this.linePaint);
        canvas.drawLine(this.f648x2, this.f649y1, this.f647x1, this.f650y2, this.linePaint);
    }
}
