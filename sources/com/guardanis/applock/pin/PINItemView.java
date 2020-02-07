package com.guardanis.applock.pin;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import com.guardanis.applock.pin.PINItemAnimator.ItemAnimationDirection;

public class PINItemView {
    private ItemAnimationDirection animationDirection = ItemAnimationDirection.OUT;
    private Paint backgroundPaint;
    private int currentRadius;
    private int intendedRadius;
    private float[] position;
    private Paint textPaint;
    private float[] textPosition;

    public PINItemView(float[] fArr, int[] iArr, Paint paint, Paint paint2) {
        this.position = fArr;
        this.intendedRadius = iArr[1];
        this.currentRadius = iArr[0];
        setupPaints(paint, paint2);
    }

    private void setupPaints(Paint paint, Paint paint2) {
        this.textPaint = new Paint();
        this.textPaint.setTextAlign(Align.CENTER);
        this.textPaint.setAntiAlias(true);
        this.textPaint.setColor(paint.getColor());
        this.textPaint.setTextSize(paint.getTextSize());
        float[] fArr = this.position;
        this.textPosition = new float[]{fArr[0], fArr[1] - ((this.textPaint.descent() + this.textPaint.ascent()) / 2.0f)};
        this.backgroundPaint = new Paint();
        this.backgroundPaint.setTextAlign(Align.CENTER);
        this.backgroundPaint.setAntiAlias(true);
        this.backgroundPaint.setColor(paint2.getColor());
    }

    public void draw(Canvas canvas, String str) {
        float[] fArr = this.position;
        canvas.drawCircle(fArr[0], fArr[1], (float) this.currentRadius, this.backgroundPaint);
        float[] fArr2 = this.textPosition;
        canvas.drawText(str, fArr2[0], fArr2[1], this.textPaint);
    }

    public void setAnimationDirection(ItemAnimationDirection itemAnimationDirection) {
        this.animationDirection = itemAnimationDirection;
    }

    public void onAnimationUpdate(float f) {
        this.currentRadius = (int) (((float) this.intendedRadius) * f);
        this.textPaint.setAlpha((int) (f * 255.0f));
    }

    public boolean isAnimatedOut() {
        return this.animationDirection == ItemAnimationDirection.OUT;
    }
}
