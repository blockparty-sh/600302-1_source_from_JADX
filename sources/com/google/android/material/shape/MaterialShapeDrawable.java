package com.google.android.material.shape;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.Region.Op;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import androidx.annotation.AttrRes;
import androidx.annotation.ColorInt;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.RestrictTo.Scope;
import androidx.annotation.StyleRes;
import androidx.core.graphics.drawable.TintAwareDrawable;
import androidx.core.util.ObjectsCompat;
import com.google.android.material.C1435R;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.elevation.ElevationOverlayProvider;
import com.google.android.material.shadow.ShadowRenderer;
import com.google.android.material.shape.ShapeAppearanceModel.OnChangedListener;
import com.google.android.material.shape.ShapeAppearancePathProvider.PathListener;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class MaterialShapeDrawable extends Drawable implements TintAwareDrawable, OnChangedListener {
    public static final int SHADOW_COMPAT_MODE_ALWAYS = 2;
    public static final int SHADOW_COMPAT_MODE_DEFAULT = 0;
    public static final int SHADOW_COMPAT_MODE_NEVER = 1;
    private static final float SHADOW_OFFSET_MULTIPLIER = 0.25f;
    private static final float SHADOW_RADIUS_MULTIPLIER = 0.75f;
    private static final Paint clearPaint = new Paint(1);
    /* access modifiers changed from: private */
    public final ShadowCompatOperation[] cornerShadowOperation;
    private MaterialShapeDrawableState drawableState;
    /* access modifiers changed from: private */
    public final ShadowCompatOperation[] edgeShadowOperation;
    private final Paint fillPaint;
    private final RectF insetRectF;
    private final Matrix matrix;
    @Nullable
    private Rect padding;
    private final Path path;
    /* access modifiers changed from: private */
    public boolean pathDirty;
    private final Path pathInsetByStroke;
    private final ShapeAppearancePathProvider pathProvider;
    private final PathListener pathShadowListener;
    private final RectF rectF;
    private final Region scratchRegion;
    private final ShadowRenderer shadowRenderer;
    private final Paint strokePaint;
    private ShapeAppearanceModel strokeShapeAppearance;
    @Nullable
    private PorterDuffColorFilter strokeTintFilter;
    @Nullable
    private PorterDuffColorFilter tintFilter;
    private final Region transparentRegion;

    @Retention(RetentionPolicy.SOURCE)
    public @interface CompatibilityShadowMode {
    }

    static final class MaterialShapeDrawableState extends ConstantState {
        public int alpha = 255;
        @Nullable
        public ColorFilter colorFilter;
        public float elevation = 0.0f;
        @Nullable
        public ElevationOverlayProvider elevationOverlayProvider;
        @Nullable
        public ColorStateList fillColor = null;
        public float interpolation = 1.0f;
        @Nullable
        public Rect padding = null;
        public Style paintStyle = Style.FILL_AND_STROKE;
        public float scale = 1.0f;
        public int shadowCompatMode = 0;
        public int shadowCompatOffset = 0;
        public int shadowCompatRadius = 0;
        public int shadowCompatRotation = 0;
        @NonNull
        public ShapeAppearanceModel shapeAppearanceModel;
        @Nullable
        public ColorStateList strokeColor = null;
        @Nullable
        public ColorStateList strokeTintList = null;
        public float strokeWidth;
        @Nullable
        public ColorStateList tintList = null;
        @Nullable
        public Mode tintMode = Mode.SRC_IN;
        public float translationZ = 0.0f;
        public boolean useTintColorForShadow = false;

        public int getChangingConfigurations() {
            return 0;
        }

        public MaterialShapeDrawableState(ShapeAppearanceModel shapeAppearanceModel2, ElevationOverlayProvider elevationOverlayProvider2) {
            this.shapeAppearanceModel = shapeAppearanceModel2;
            this.elevationOverlayProvider = elevationOverlayProvider2;
        }

        public MaterialShapeDrawableState(MaterialShapeDrawableState materialShapeDrawableState) {
            this.shapeAppearanceModel = materialShapeDrawableState.shapeAppearanceModel;
            this.elevationOverlayProvider = materialShapeDrawableState.elevationOverlayProvider;
            this.strokeWidth = materialShapeDrawableState.strokeWidth;
            this.colorFilter = materialShapeDrawableState.colorFilter;
            this.fillColor = materialShapeDrawableState.fillColor;
            this.strokeColor = materialShapeDrawableState.strokeColor;
            this.tintMode = materialShapeDrawableState.tintMode;
            this.tintList = materialShapeDrawableState.tintList;
            this.alpha = materialShapeDrawableState.alpha;
            this.scale = materialShapeDrawableState.scale;
            this.shadowCompatOffset = materialShapeDrawableState.shadowCompatOffset;
            this.shadowCompatMode = materialShapeDrawableState.shadowCompatMode;
            this.useTintColorForShadow = materialShapeDrawableState.useTintColorForShadow;
            this.interpolation = materialShapeDrawableState.interpolation;
            this.elevation = materialShapeDrawableState.elevation;
            this.translationZ = materialShapeDrawableState.translationZ;
            this.shadowCompatRadius = materialShapeDrawableState.shadowCompatRadius;
            this.shadowCompatRotation = materialShapeDrawableState.shadowCompatRotation;
            this.strokeTintList = materialShapeDrawableState.strokeTintList;
            this.paintStyle = materialShapeDrawableState.paintStyle;
            Rect rect = materialShapeDrawableState.padding;
            if (rect != null) {
                this.padding = new Rect(rect);
            }
        }

        public Drawable newDrawable() {
            MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable(this);
            materialShapeDrawable.pathDirty = true;
            return materialShapeDrawable;
        }
    }

    private static int modulateAlpha(int i, int i2) {
        return (i * (i2 + (i2 >>> 7))) >>> 8;
    }

    public int getOpacity() {
        return -3;
    }

    public static MaterialShapeDrawable createWithElevationOverlay(Context context) {
        return createWithElevationOverlay(context, 0.0f);
    }

    public static MaterialShapeDrawable createWithElevationOverlay(Context context, float f) {
        int color = MaterialColors.getColor(context, C1435R.attr.colorSurface, MaterialShapeDrawable.class.getSimpleName());
        MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable();
        materialShapeDrawable.initializeElevationOverlay(context);
        materialShapeDrawable.setFillColor(ColorStateList.valueOf(color));
        materialShapeDrawable.setElevation(f);
        return materialShapeDrawable;
    }

    public MaterialShapeDrawable() {
        this(new ShapeAppearanceModel());
    }

    public MaterialShapeDrawable(Context context, AttributeSet attributeSet, @AttrRes int i, @StyleRes int i2) {
        this(new ShapeAppearanceModel(context, attributeSet, i, i2));
    }

    public MaterialShapeDrawable(ShapeAppearanceModel shapeAppearanceModel) {
        this(new MaterialShapeDrawableState(shapeAppearanceModel, null));
    }

    private MaterialShapeDrawable(MaterialShapeDrawableState materialShapeDrawableState) {
        this.cornerShadowOperation = new ShadowCompatOperation[4];
        this.edgeShadowOperation = new ShadowCompatOperation[4];
        this.matrix = new Matrix();
        this.path = new Path();
        this.pathInsetByStroke = new Path();
        this.rectF = new RectF();
        this.insetRectF = new RectF();
        this.transparentRegion = new Region();
        this.scratchRegion = new Region();
        this.fillPaint = new Paint(1);
        this.strokePaint = new Paint(1);
        this.shadowRenderer = new ShadowRenderer();
        this.pathProvider = new ShapeAppearancePathProvider();
        this.drawableState = materialShapeDrawableState;
        this.strokePaint.setStyle(Style.STROKE);
        this.fillPaint.setStyle(Style.FILL);
        clearPaint.setColor(-1);
        clearPaint.setXfermode(new PorterDuffXfermode(Mode.DST_OUT));
        updateTintFilter();
        updateColorsForState(getState());
        this.pathShadowListener = new PathListener() {
            public void onCornerPathCreated(ShapePath shapePath, Matrix matrix, int i) {
                MaterialShapeDrawable.this.cornerShadowOperation[i] = shapePath.createShadowCompatOperation(matrix);
            }

            public void onEdgePathCreated(ShapePath shapePath, Matrix matrix, int i) {
                MaterialShapeDrawable.this.edgeShadowOperation[i] = shapePath.createShadowCompatOperation(matrix);
            }
        };
        materialShapeDrawableState.shapeAppearanceModel.addOnChangedListener(this);
    }

    @Nullable
    public ConstantState getConstantState() {
        return this.drawableState;
    }

    @NonNull
    public Drawable mutate() {
        this.drawableState = new MaterialShapeDrawableState(this.drawableState);
        return this;
    }

    public void setShapeAppearanceModel(@NonNull ShapeAppearanceModel shapeAppearanceModel) {
        this.drawableState.shapeAppearanceModel.removeOnChangedListener(this);
        this.drawableState.shapeAppearanceModel = shapeAppearanceModel;
        shapeAppearanceModel.addOnChangedListener(this);
        invalidateSelf();
    }

    @NonNull
    public ShapeAppearanceModel getShapeAppearanceModel() {
        return this.drawableState.shapeAppearanceModel;
    }

    @Deprecated
    public void setShapedViewModel(ShapePathModel shapePathModel) {
        setShapeAppearanceModel(shapePathModel);
    }

    @Deprecated
    public ShapeAppearanceModel getShapedViewModel() {
        return getShapeAppearanceModel();
    }

    public void setFillColor(@Nullable ColorStateList colorStateList) {
        if (this.drawableState.fillColor != colorStateList) {
            this.drawableState.fillColor = colorStateList;
            onStateChange(getState());
        }
    }

    @Nullable
    public ColorStateList getFillColor() {
        return this.drawableState.fillColor;
    }

    public void setStrokeColor(@Nullable ColorStateList colorStateList) {
        if (this.drawableState.strokeColor != colorStateList) {
            this.drawableState.strokeColor = colorStateList;
            onStateChange(getState());
        }
    }

    @Nullable
    public ColorStateList getStrokeColor() {
        return this.drawableState.strokeColor;
    }

    public void setTintMode(@Nullable Mode mode) {
        if (this.drawableState.tintMode != mode) {
            this.drawableState.tintMode = mode;
            updateTintFilter();
            invalidateSelfIgnoreShape();
        }
    }

    public void setTintList(@Nullable ColorStateList colorStateList) {
        this.drawableState.tintList = colorStateList;
        updateTintFilter();
        invalidateSelfIgnoreShape();
    }

    public ColorStateList getTintList() {
        return this.drawableState.tintList;
    }

    public ColorStateList getStrokeTintList() {
        return this.drawableState.strokeTintList;
    }

    @ColorInt
    @Deprecated
    public int getStrokeTint() {
        return this.drawableState.strokeTintList.getColorForState(getState(), 0);
    }

    public void setTint(@ColorInt int i) {
        setTintList(ColorStateList.valueOf(i));
    }

    public void setStrokeTint(ColorStateList colorStateList) {
        this.drawableState.strokeTintList = colorStateList;
        updateTintFilter();
        invalidateSelfIgnoreShape();
    }

    public void setStrokeTint(@ColorInt int i) {
        setStrokeTint(ColorStateList.valueOf(i));
    }

    public void setStroke(float f, @ColorInt int i) {
        setStrokeWidth(f);
        setStrokeColor(ColorStateList.valueOf(i));
    }

    public void setStroke(float f, @Nullable ColorStateList colorStateList) {
        setStrokeWidth(f);
        setStrokeColor(colorStateList);
    }

    public float getStrokeWidth() {
        return this.drawableState.strokeWidth;
    }

    public void setStrokeWidth(float f) {
        this.drawableState.strokeWidth = f;
        invalidateSelf();
    }

    public void setAlpha(@IntRange(from = 0, mo644to = 255) int i) {
        if (this.drawableState.alpha != i) {
            this.drawableState.alpha = i;
            invalidateSelfIgnoreShape();
        }
    }

    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        this.drawableState.colorFilter = colorFilter;
        invalidateSelfIgnoreShape();
    }

    public Region getTransparentRegion() {
        this.transparentRegion.set(getBounds());
        calculatePath(getBoundsAsRectF(), this.path);
        this.scratchRegion.setPath(this.path, this.transparentRegion);
        this.transparentRegion.op(this.scratchRegion, Op.DIFFERENCE);
        return this.transparentRegion;
    }

    /* access modifiers changed from: protected */
    public RectF getBoundsAsRectF() {
        Rect bounds = getBounds();
        this.rectF.set((float) bounds.left, (float) bounds.top, (float) bounds.right, (float) bounds.bottom);
        return this.rectF;
    }

    public void setCornerRadius(float f) {
        this.drawableState.shapeAppearanceModel.setCornerRadius(f);
        invalidateSelf();
    }

    public boolean isPointInTransparentRegion(int i, int i2) {
        return getTransparentRegion().contains(i, i2);
    }

    public int getShadowCompatibilityMode() {
        return this.drawableState.shadowCompatMode;
    }

    public boolean getPadding(Rect rect) {
        Rect rect2 = this.padding;
        if (rect2 == null) {
            return super.getPadding(rect);
        }
        rect.set(rect2);
        return true;
    }

    public void setPadding(int i, int i2, int i3, int i4) {
        if (this.drawableState.padding == null) {
            this.drawableState.padding = new Rect();
        }
        this.drawableState.padding.set(i, i2, i3, i4);
        this.padding = this.drawableState.padding;
        invalidateSelf();
    }

    public void setShadowCompatibilityMode(int i) {
        if (this.drawableState.shadowCompatMode != i) {
            this.drawableState.shadowCompatMode = i;
            invalidateSelfIgnoreShape();
        }
    }

    @Deprecated
    public boolean isShadowEnabled() {
        return this.drawableState.shadowCompatMode == 0 || this.drawableState.shadowCompatMode == 2;
    }

    @Deprecated
    public void setShadowEnabled(boolean z) {
        setShadowCompatibilityMode(z ^ true ? 1 : 0);
    }

    public boolean isElevationOverlayInitialized() {
        return this.drawableState.elevationOverlayProvider != null;
    }

    public void initializeElevationOverlay(Context context) {
        this.drawableState.elevationOverlayProvider = new ElevationOverlayProvider(context);
        updateZ();
    }

    @ColorInt
    private int layerElevationOverlayIfNeeded(@ColorInt int i) {
        return this.drawableState.elevationOverlayProvider != null ? this.drawableState.elevationOverlayProvider.layerOverlayIfNeeded(i, getZ()) : i;
    }

    public float getInterpolation() {
        return this.drawableState.interpolation;
    }

    public void setInterpolation(float f) {
        if (this.drawableState.interpolation != f) {
            this.drawableState.interpolation = f;
            this.pathDirty = true;
            invalidateSelf();
        }
    }

    public float getElevation() {
        return this.drawableState.elevation;
    }

    public void setElevation(float f) {
        if (this.drawableState.elevation != f) {
            this.drawableState.elevation = f;
            updateZ();
        }
    }

    public float getTranslationZ() {
        return this.drawableState.translationZ;
    }

    public void setTranslationZ(float f) {
        if (this.drawableState.translationZ != f) {
            this.drawableState.translationZ = f;
            updateZ();
        }
    }

    public float getZ() {
        return getElevation() + getTranslationZ();
    }

    public void setZ(float f) {
        setTranslationZ(f - getElevation());
    }

    private void updateZ() {
        float z = getZ();
        this.drawableState.shadowCompatRadius = (int) Math.ceil((double) (SHADOW_RADIUS_MULTIPLIER * z));
        this.drawableState.shadowCompatOffset = (int) Math.ceil((double) (z * SHADOW_OFFSET_MULTIPLIER));
        updateTintFilter();
        invalidateSelfIgnoreShape();
    }

    @Deprecated
    public int getShadowElevation() {
        return (int) getElevation();
    }

    @Deprecated
    public void setShadowElevation(int i) {
        setElevation((float) i);
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public int getShadowVerticalOffset() {
        return this.drawableState.shadowCompatOffset;
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public void setShadowVerticalOffset(int i) {
        if (this.drawableState.shadowCompatOffset != i) {
            this.drawableState.shadowCompatOffset = i;
            invalidateSelfIgnoreShape();
        }
    }

    public int getShadowCompatRotation() {
        return this.drawableState.shadowCompatRotation;
    }

    public void setShadowCompatRotation(int i) {
        if (this.drawableState.shadowCompatRotation != i) {
            this.drawableState.shadowCompatRotation = i;
            invalidateSelfIgnoreShape();
        }
    }

    public int getShadowRadius() {
        return this.drawableState.shadowCompatRadius;
    }

    @Deprecated
    public void setShadowRadius(int i) {
        this.drawableState.shadowCompatRadius = i;
    }

    private boolean requiresCompatShadow() {
        return VERSION.SDK_INT < 21 || (!this.drawableState.shapeAppearanceModel.isRoundRect() && !this.path.isConvex());
    }

    public float getScale() {
        return this.drawableState.scale;
    }

    public void setScale(float f) {
        if (this.drawableState.scale != f) {
            this.drawableState.scale = f;
            invalidateSelf();
        }
    }

    public void onShapeAppearanceModelChanged() {
        invalidateSelf();
    }

    public void invalidateSelf() {
        this.pathDirty = true;
        super.invalidateSelf();
    }

    private void invalidateSelfIgnoreShape() {
        super.invalidateSelf();
    }

    public void setUseTintColorForShadow(boolean z) {
        if (this.drawableState.useTintColorForShadow != z) {
            this.drawableState.useTintColorForShadow = z;
            invalidateSelf();
        }
    }

    public void setShadowColor(int i) {
        this.shadowRenderer.setShadowColor(i);
        this.drawableState.useTintColorForShadow = false;
        invalidateSelfIgnoreShape();
    }

    public Style getPaintStyle() {
        return this.drawableState.paintStyle;
    }

    public void setPaintStyle(Style style) {
        this.drawableState.paintStyle = style;
        invalidateSelfIgnoreShape();
    }

    private boolean hasCompatShadow() {
        if (this.drawableState.shadowCompatMode == 1 || this.drawableState.shadowCompatRadius <= 0 || (this.drawableState.shadowCompatMode != 2 && !requiresCompatShadow())) {
            return false;
        }
        return true;
    }

    private boolean hasFill() {
        return this.drawableState.paintStyle == Style.FILL_AND_STROKE || this.drawableState.paintStyle == Style.FILL;
    }

    private boolean hasStroke() {
        return (this.drawableState.paintStyle == Style.FILL_AND_STROKE || this.drawableState.paintStyle == Style.STROKE) && this.strokePaint.getStrokeWidth() > 0.0f;
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        this.pathDirty = true;
        super.onBoundsChange(rect);
    }

    public void draw(Canvas canvas) {
        this.fillPaint.setColorFilter(this.tintFilter);
        int alpha = this.fillPaint.getAlpha();
        this.fillPaint.setAlpha(modulateAlpha(alpha, this.drawableState.alpha));
        this.strokePaint.setColorFilter(this.strokeTintFilter);
        this.strokePaint.setStrokeWidth(this.drawableState.strokeWidth);
        int alpha2 = this.strokePaint.getAlpha();
        this.strokePaint.setAlpha(modulateAlpha(alpha2, this.drawableState.alpha));
        if (this.pathDirty) {
            calculateStrokePath();
            calculatePath(getBoundsAsRectF(), this.path);
            this.pathDirty = false;
        }
        if (hasCompatShadow()) {
            canvas.save();
            prepareCanvasForShadow(canvas);
            Bitmap createBitmap = Bitmap.createBitmap(getBounds().width() + (this.drawableState.shadowCompatRadius * 2), getBounds().height() + (this.drawableState.shadowCompatRadius * 2), Config.ARGB_8888);
            Canvas canvas2 = new Canvas(createBitmap);
            float f = (float) (getBounds().left - this.drawableState.shadowCompatRadius);
            float f2 = (float) (getBounds().top - this.drawableState.shadowCompatRadius);
            canvas2.translate(-f, -f2);
            drawCompatShadow(canvas2);
            canvas.drawBitmap(createBitmap, f, f2, null);
            createBitmap.recycle();
            canvas.restore();
        }
        if (hasFill()) {
            drawFillShape(canvas);
        }
        if (hasStroke()) {
            drawStrokeShape(canvas);
        }
        this.fillPaint.setAlpha(alpha);
        this.strokePaint.setAlpha(alpha2);
    }

    /* access modifiers changed from: protected */
    @RestrictTo({Scope.LIBRARY_GROUP})
    public void drawShape(Canvas canvas, Paint paint, Path path2, RectF rectF2) {
        drawShape(canvas, paint, path2, this.drawableState.shapeAppearanceModel, rectF2);
    }

    private void drawShape(Canvas canvas, Paint paint, Path path2, ShapeAppearanceModel shapeAppearanceModel, RectF rectF2) {
        if (shapeAppearanceModel.isRoundRect()) {
            float cornerSize = shapeAppearanceModel.getTopRightCorner().getCornerSize();
            canvas.drawRoundRect(rectF2, cornerSize, cornerSize, paint);
            return;
        }
        canvas.drawPath(path2, paint);
    }

    private void drawFillShape(Canvas canvas) {
        drawShape(canvas, this.fillPaint, this.path, this.drawableState.shapeAppearanceModel, getBoundsAsRectF());
    }

    private void drawStrokeShape(Canvas canvas) {
        drawShape(canvas, this.strokePaint, this.pathInsetByStroke, this.strokeShapeAppearance, getBoundsInsetByStroke());
    }

    private void prepareCanvasForShadow(Canvas canvas) {
        int shadowOffsetX = getShadowOffsetX();
        int shadowOffsetY = getShadowOffsetY();
        if (VERSION.SDK_INT < 21) {
            Rect clipBounds = canvas.getClipBounds();
            clipBounds.inset(-this.drawableState.shadowCompatRadius, -this.drawableState.shadowCompatRadius);
            clipBounds.offset(shadowOffsetX, shadowOffsetY);
            canvas.clipRect(clipBounds, Op.REPLACE);
        }
        canvas.translate((float) shadowOffsetX, (float) shadowOffsetY);
    }

    private void drawCompatShadow(Canvas canvas) {
        if (this.drawableState.shadowCompatOffset != 0) {
            canvas.drawPath(this.path, this.shadowRenderer.getShadowPaint());
        }
        for (int i = 0; i < 4; i++) {
            this.cornerShadowOperation[i].draw(this.shadowRenderer, this.drawableState.shadowCompatRadius, canvas);
            this.edgeShadowOperation[i].draw(this.shadowRenderer, this.drawableState.shadowCompatRadius, canvas);
        }
        int shadowOffsetX = getShadowOffsetX();
        int shadowOffsetY = getShadowOffsetY();
        canvas.translate((float) (-shadowOffsetX), (float) (-shadowOffsetY));
        canvas.drawPath(this.path, clearPaint);
        canvas.translate((float) shadowOffsetX, (float) shadowOffsetY);
    }

    public int getShadowOffsetX() {
        return (int) (((double) this.drawableState.shadowCompatOffset) * Math.sin(Math.toRadians((double) this.drawableState.shadowCompatRotation)));
    }

    public int getShadowOffsetY() {
        return (int) (((double) this.drawableState.shadowCompatOffset) * Math.cos(Math.toRadians((double) this.drawableState.shadowCompatRotation)));
    }

    @Deprecated
    public void getPathForSize(int i, int i2, Path path2) {
        calculatePathForSize(new RectF(0.0f, 0.0f, (float) i, (float) i2), path2);
    }

    @Deprecated
    public void getPathForSize(Rect rect, Path path2) {
        calculatePathForSize(new RectF(rect), path2);
    }

    private void calculatePathForSize(RectF rectF2, Path path2) {
        this.pathProvider.calculatePath(this.drawableState.shapeAppearanceModel, this.drawableState.interpolation, rectF2, this.pathShadowListener, path2);
    }

    private void calculateStrokePath() {
        this.strokeShapeAppearance = new ShapeAppearanceModel(getShapeAppearanceModel());
        this.strokeShapeAppearance.setCornerRadii(adjustCornerSizeForStrokeSize(this.strokeShapeAppearance.getTopLeftCorner().cornerSize), adjustCornerSizeForStrokeSize(this.strokeShapeAppearance.getTopRightCorner().cornerSize), adjustCornerSizeForStrokeSize(this.strokeShapeAppearance.getBottomRightCorner().cornerSize), adjustCornerSizeForStrokeSize(this.strokeShapeAppearance.getBottomLeftCorner().cornerSize));
        this.pathProvider.calculatePath(this.strokeShapeAppearance, this.drawableState.interpolation, getBoundsInsetByStroke(), this.pathInsetByStroke);
    }

    private float adjustCornerSizeForStrokeSize(float f) {
        return Math.max(f - getStrokeInsetLength(), 0.0f);
    }

    @TargetApi(21)
    public void getOutline(Outline outline) {
        if (this.drawableState.shadowCompatMode != 2) {
            if (this.drawableState.shapeAppearanceModel.isRoundRect()) {
                outline.setRoundRect(getBounds(), this.drawableState.shapeAppearanceModel.getTopLeftCorner().getCornerSize());
                return;
            }
            calculatePath(getBoundsAsRectF(), this.path);
            if (this.path.isConvex()) {
                outline.setConvexPath(this.path);
            }
        }
    }

    private void calculatePath(RectF rectF2, Path path2) {
        calculatePathForSize(rectF2, path2);
        if (this.drawableState.scale != 1.0f) {
            this.matrix.reset();
            this.matrix.setScale(this.drawableState.scale, this.drawableState.scale, rectF2.width() / 2.0f, rectF2.height() / 2.0f);
            path2.transform(this.matrix);
        }
    }

    private boolean updateTintFilter() {
        PorterDuffColorFilter porterDuffColorFilter = this.tintFilter;
        PorterDuffColorFilter porterDuffColorFilter2 = this.strokeTintFilter;
        this.tintFilter = calculateTintFilter(this.drawableState.tintList, this.drawableState.tintMode, this.fillPaint, true);
        this.strokeTintFilter = calculateTintFilter(this.drawableState.strokeTintList, this.drawableState.tintMode, this.strokePaint, false);
        if (this.drawableState.useTintColorForShadow) {
            this.shadowRenderer.setShadowColor(this.drawableState.tintList.getColorForState(getState(), 0));
        }
        if (!ObjectsCompat.equals(porterDuffColorFilter, this.tintFilter) || !ObjectsCompat.equals(porterDuffColorFilter2, this.strokeTintFilter)) {
            return true;
        }
        return false;
    }

    @Nullable
    private PorterDuffColorFilter calculateTintFilter(ColorStateList colorStateList, Mode mode, Paint paint, boolean z) {
        if (colorStateList == null || mode == null) {
            return calculatePaintColorTintFilter(paint, z);
        }
        return calculateTintColorTintFilter(colorStateList, mode, z);
    }

    @Nullable
    private PorterDuffColorFilter calculatePaintColorTintFilter(Paint paint, boolean z) {
        if (z) {
            int color = paint.getColor();
            int layerElevationOverlayIfNeeded = layerElevationOverlayIfNeeded(color);
            if (layerElevationOverlayIfNeeded != color) {
                return new PorterDuffColorFilter(layerElevationOverlayIfNeeded, Mode.SRC_IN);
            }
        }
        return null;
    }

    private PorterDuffColorFilter calculateTintColorTintFilter(ColorStateList colorStateList, Mode mode, boolean z) {
        int colorForState = colorStateList.getColorForState(getState(), 0);
        if (z) {
            colorForState = layerElevationOverlayIfNeeded(colorForState);
        }
        return new PorterDuffColorFilter(colorForState, mode);
    }

    public boolean isStateful() {
        return super.isStateful() || (this.drawableState.tintList != null && this.drawableState.tintList.isStateful()) || ((this.drawableState.strokeTintList != null && this.drawableState.strokeTintList.isStateful()) || ((this.drawableState.strokeColor != null && this.drawableState.strokeColor.isStateful()) || (this.drawableState.fillColor != null && this.drawableState.fillColor.isStateful())));
    }

    /* access modifiers changed from: protected */
    public boolean onStateChange(int[] iArr) {
        boolean z = updateColorsForState(iArr) || updateTintFilter();
        if (z) {
            invalidateSelf();
        }
        return z;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean updateColorsForState(int[] r5) {
        /*
            r4 = this;
            com.google.android.material.shape.MaterialShapeDrawable$MaterialShapeDrawableState r0 = r4.drawableState
            android.content.res.ColorStateList r0 = r0.fillColor
            r1 = 1
            if (r0 == 0) goto L_0x001e
            android.graphics.Paint r0 = r4.fillPaint
            int r0 = r0.getColor()
            com.google.android.material.shape.MaterialShapeDrawable$MaterialShapeDrawableState r2 = r4.drawableState
            android.content.res.ColorStateList r2 = r2.fillColor
            int r2 = r2.getColorForState(r5, r0)
            if (r0 == r2) goto L_0x001e
            android.graphics.Paint r0 = r4.fillPaint
            r0.setColor(r2)
            r0 = 1
            goto L_0x001f
        L_0x001e:
            r0 = 0
        L_0x001f:
            com.google.android.material.shape.MaterialShapeDrawable$MaterialShapeDrawableState r2 = r4.drawableState
            android.content.res.ColorStateList r2 = r2.strokeColor
            if (r2 == 0) goto L_0x003b
            android.graphics.Paint r2 = r4.strokePaint
            int r2 = r2.getColor()
            com.google.android.material.shape.MaterialShapeDrawable$MaterialShapeDrawableState r3 = r4.drawableState
            android.content.res.ColorStateList r3 = r3.strokeColor
            int r5 = r3.getColorForState(r5, r2)
            if (r2 == r5) goto L_0x003b
            android.graphics.Paint r0 = r4.strokePaint
            r0.setColor(r5)
            r0 = 1
        L_0x003b:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.shape.MaterialShapeDrawable.updateColorsForState(int[]):boolean");
    }

    private float getStrokeInsetLength() {
        if (hasStroke()) {
            return this.strokePaint.getStrokeWidth() / 2.0f;
        }
        return 0.0f;
    }

    private RectF getBoundsInsetByStroke() {
        RectF boundsAsRectF = getBoundsAsRectF();
        float strokeInsetLength = getStrokeInsetLength();
        this.insetRectF.set(boundsAsRectF.left + strokeInsetLength, boundsAsRectF.top + strokeInsetLength, boundsAsRectF.right - strokeInsetLength, boundsAsRectF.bottom - strokeInsetLength);
        return this.insetRectF;
    }
}
