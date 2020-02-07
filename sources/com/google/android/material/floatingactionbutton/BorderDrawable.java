package com.google.android.material.floatingactionbutton;

import android.annotation.TargetApi;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;
import androidx.annotation.ColorInt;
import androidx.annotation.Dimension;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.RestrictTo.Scope;
import androidx.core.graphics.ColorUtils;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.ShapeAppearancePathProvider;

@RestrictTo({Scope.LIBRARY_GROUP})
class BorderDrawable extends Drawable {
    private static final float DRAW_STROKE_WIDTH_MULTIPLE = 1.3333f;
    @Nullable
    private ColorStateList borderTint;
    @Dimension
    float borderWidth;
    @ColorInt
    private int bottomInnerStrokeColor;
    @ColorInt
    private int bottomOuterStrokeColor;
    @ColorInt
    private int currentBorderTintColor;
    private boolean invalidateShader = true;
    private final Paint paint;
    private final ShapeAppearancePathProvider pathProvider = new ShapeAppearancePathProvider();
    private final Rect rect = new Rect();
    private final RectF rectF = new RectF();
    private ShapeAppearanceModel shapeAppearanceModel;
    private final Path shapePath = new Path();
    private final BorderState state = new BorderState();
    @ColorInt
    private int topInnerStrokeColor;
    @ColorInt
    private int topOuterStrokeColor;

    private class BorderState extends ConstantState {
        public int getChangingConfigurations() {
            return 0;
        }

        private BorderState() {
        }

        @NonNull
        public Drawable newDrawable() {
            return BorderDrawable.this;
        }
    }

    BorderDrawable(ShapeAppearanceModel shapeAppearanceModel2) {
        this.shapeAppearanceModel = shapeAppearanceModel2;
        this.paint = new Paint(1);
        this.paint.setStyle(Style.STROKE);
    }

    public void setBorderWidth(@Dimension float f) {
        if (this.borderWidth != f) {
            this.borderWidth = f;
            this.paint.setStrokeWidth(f * DRAW_STROKE_WIDTH_MULTIPLE);
            this.invalidateShader = true;
            invalidateSelf();
        }
    }

    /* access modifiers changed from: 0000 */
    public void setBorderTint(@Nullable ColorStateList colorStateList) {
        if (colorStateList != null) {
            this.currentBorderTintColor = colorStateList.getColorForState(getState(), this.currentBorderTintColor);
        }
        this.borderTint = colorStateList;
        this.invalidateShader = true;
        invalidateSelf();
    }

    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        this.paint.setColorFilter(colorFilter);
        invalidateSelf();
    }

    /* access modifiers changed from: 0000 */
    public void setGradientColors(@ColorInt int i, @ColorInt int i2, @ColorInt int i3, @ColorInt int i4) {
        this.topOuterStrokeColor = i;
        this.topInnerStrokeColor = i2;
        this.bottomOuterStrokeColor = i3;
        this.bottomInnerStrokeColor = i4;
    }

    public void draw(Canvas canvas) {
        if (this.invalidateShader) {
            this.paint.setShader(createGradientShader());
            this.invalidateShader = false;
        }
        float strokeWidth = this.paint.getStrokeWidth() / 2.0f;
        copyBounds(this.rect);
        this.rectF.set(this.rect);
        float min = Math.min(this.shapeAppearanceModel.getTopLeftCorner().getCornerSize(), this.rectF.width() / 2.0f);
        if (this.shapeAppearanceModel.isRoundRect()) {
            this.rectF.inset(strokeWidth, strokeWidth);
            canvas.drawRoundRect(this.rectF, min, min, this.paint);
        }
    }

    @TargetApi(21)
    public void getOutline(@NonNull Outline outline) {
        if (this.shapeAppearanceModel.isRoundRect()) {
            outline.setRoundRect(getBounds(), this.shapeAppearanceModel.getTopLeftCorner().getCornerSize());
            return;
        }
        copyBounds(this.rect);
        this.rectF.set(this.rect);
        this.pathProvider.calculatePath(this.shapeAppearanceModel, 1.0f, this.rectF, this.shapePath);
        if (this.shapePath.isConvex()) {
            outline.setConvexPath(this.shapePath);
        }
    }

    public boolean getPadding(Rect rect2) {
        if (this.shapeAppearanceModel.isRoundRect()) {
            int round = Math.round(this.borderWidth);
            rect2.set(round, round, round, round);
        }
        return true;
    }

    public ShapeAppearanceModel getShapeAppearanceModel() {
        return this.shapeAppearanceModel;
    }

    public void setShapeAppearanceModel(ShapeAppearanceModel shapeAppearanceModel2) {
        this.shapeAppearanceModel = shapeAppearanceModel2;
        invalidateSelf();
    }

    public void setAlpha(@IntRange(from = 0, mo644to = 255) int i) {
        this.paint.setAlpha(i);
        invalidateSelf();
    }

    public int getOpacity() {
        return this.borderWidth > 0.0f ? -3 : -2;
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect2) {
        this.invalidateShader = true;
    }

    public boolean isStateful() {
        ColorStateList colorStateList = this.borderTint;
        return (colorStateList != null && colorStateList.isStateful()) || super.isStateful();
    }

    /* access modifiers changed from: protected */
    public boolean onStateChange(int[] iArr) {
        ColorStateList colorStateList = this.borderTint;
        if (colorStateList != null) {
            int colorForState = colorStateList.getColorForState(iArr, this.currentBorderTintColor);
            if (colorForState != this.currentBorderTintColor) {
                this.invalidateShader = true;
                this.currentBorderTintColor = colorForState;
            }
        }
        if (this.invalidateShader) {
            invalidateSelf();
        }
        return this.invalidateShader;
    }

    private Shader createGradientShader() {
        Rect rect2 = this.rect;
        copyBounds(rect2);
        float height = this.borderWidth / ((float) rect2.height());
        LinearGradient linearGradient = new LinearGradient(0.0f, (float) rect2.top, 0.0f, (float) rect2.bottom, new int[]{ColorUtils.compositeColors(this.topOuterStrokeColor, this.currentBorderTintColor), ColorUtils.compositeColors(this.topInnerStrokeColor, this.currentBorderTintColor), ColorUtils.compositeColors(ColorUtils.setAlphaComponent(this.topInnerStrokeColor, 0), this.currentBorderTintColor), ColorUtils.compositeColors(ColorUtils.setAlphaComponent(this.bottomInnerStrokeColor, 0), this.currentBorderTintColor), ColorUtils.compositeColors(this.bottomInnerStrokeColor, this.currentBorderTintColor), ColorUtils.compositeColors(this.bottomOuterStrokeColor, this.currentBorderTintColor)}, new float[]{0.0f, height, 0.5f, 0.5f, 1.0f - height, 1.0f}, TileMode.CLAMP);
        return linearGradient;
    }

    @Nullable
    public ConstantState getConstantState() {
        return this.state;
    }
}
