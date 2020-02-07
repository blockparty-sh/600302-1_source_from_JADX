package com.google.android.material.card;

import android.annotation.TargetApi;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Outline;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewOutlineProvider;
import androidx.annotation.ColorInt;
import androidx.annotation.Dimension;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.annotation.RestrictTo.Scope;
import androidx.annotation.StyleRes;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.ViewCompat;
import com.google.android.material.C1435R;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.ripple.RippleUtils;
import com.google.android.material.shape.CornerTreatment;
import com.google.android.material.shape.CutCornerTreatment;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.RoundedCornerTreatment;
import com.google.android.material.shape.ShapeAppearanceModel;

@RestrictTo({Scope.LIBRARY_GROUP})
class MaterialCardViewHelper {
    private static final float CARD_VIEW_SHADOW_MULTIPLIER = 1.5f;
    private static final int CHECKED_ICON_LAYER_INDEX = 2;
    private static final int[] CHECKED_STATE_SET = {16842912};
    private static final double COS_45 = Math.cos(Math.toRadians(45.0d));
    private static final int DEFAULT_STROKE_VALUE = -1;
    private final MaterialShapeDrawable bgDrawable;
    private boolean checkable;
    private Drawable checkedIcon;
    private ColorStateList checkedIconTint;
    @Nullable
    private LayerDrawable clickableForegroundDrawable;
    @Nullable
    private MaterialShapeDrawable compatRippleDrawable;
    /* access modifiers changed from: private */
    public final MaterialShapeDrawable drawableInsetByStroke;
    private Drawable fgDrawable;
    private final MaterialShapeDrawable foregroundContentDrawable;
    private boolean isBackgroundOverwritten = false;
    private final MaterialCardView materialCardView;
    private ColorStateList rippleColor;
    @Nullable
    private Drawable rippleDrawable;
    private final ShapeAppearanceModel shapeAppearanceModel;
    private final ShapeAppearanceModel shapeAppearanceModelInsetByStroke;
    @Nullable
    private ColorStateList strokeColor;
    /* access modifiers changed from: private */
    @Dimension
    public int strokeWidth;
    /* access modifiers changed from: private */
    public final Rect temporaryBounds = new Rect();
    private final Rect userContentPadding = new Rect();

    public MaterialCardViewHelper(MaterialCardView materialCardView2, AttributeSet attributeSet, int i, @StyleRes int i2) {
        this.materialCardView = materialCardView2;
        this.bgDrawable = new MaterialShapeDrawable(materialCardView2.getContext(), attributeSet, i, i2);
        this.bgDrawable.initializeElevationOverlay(materialCardView2.getContext());
        this.shapeAppearanceModel = this.bgDrawable.getShapeAppearanceModel();
        this.bgDrawable.setShadowColor(-12303292);
        this.foregroundContentDrawable = new MaterialShapeDrawable(this.shapeAppearanceModel);
        TypedArray obtainStyledAttributes = materialCardView2.getContext().obtainStyledAttributes(attributeSet, C1435R.styleable.CardView, i, C1435R.C1440style.CardView);
        if (obtainStyledAttributes.hasValue(C1435R.styleable.CardView_cardCornerRadius)) {
            this.shapeAppearanceModel.setCornerRadius(obtainStyledAttributes.getDimension(C1435R.styleable.CardView_cardCornerRadius, 0.0f));
        }
        this.shapeAppearanceModelInsetByStroke = new ShapeAppearanceModel(this.shapeAppearanceModel);
        this.drawableInsetByStroke = new MaterialShapeDrawable(this.shapeAppearanceModelInsetByStroke);
    }

    /* access modifiers changed from: 0000 */
    public void loadFromAttributes(TypedArray typedArray) {
        this.strokeColor = MaterialResources.getColorStateList(this.materialCardView.getContext(), typedArray, C1435R.styleable.MaterialCardView_strokeColor);
        if (this.strokeColor == null) {
            this.strokeColor = ColorStateList.valueOf(-1);
        }
        this.strokeWidth = typedArray.getDimensionPixelSize(C1435R.styleable.MaterialCardView_strokeWidth, 0);
        this.checkable = typedArray.getBoolean(C1435R.styleable.MaterialCardView_android_checkable, false);
        this.materialCardView.setLongClickable(this.checkable);
        this.checkedIconTint = MaterialResources.getColorStateList(this.materialCardView.getContext(), typedArray, C1435R.styleable.MaterialCardView_checkedIconTint);
        setCheckedIcon(MaterialResources.getDrawable(this.materialCardView.getContext(), typedArray, C1435R.styleable.MaterialCardView_checkedIcon));
        this.rippleColor = MaterialResources.getColorStateList(this.materialCardView.getContext(), typedArray, C1435R.styleable.MaterialCardView_rippleColor);
        if (this.rippleColor == null) {
            this.rippleColor = ColorStateList.valueOf(MaterialColors.getColor(this.materialCardView, C1435R.attr.colorControlHighlight));
        }
        adjustShapeAppearanceModelInsetByStroke();
        ColorStateList colorStateList = MaterialResources.getColorStateList(this.materialCardView.getContext(), typedArray, C1435R.styleable.MaterialCardView_cardForegroundColor);
        MaterialShapeDrawable materialShapeDrawable = this.foregroundContentDrawable;
        if (colorStateList == null) {
            colorStateList = ColorStateList.valueOf(0);
        }
        materialShapeDrawable.setFillColor(colorStateList);
        updateRippleColor();
        updateElevation();
        updateStroke();
        this.materialCardView.setBackgroundInternal(insetDrawable(this.bgDrawable));
        this.fgDrawable = this.materialCardView.isClickable() ? getClickableForeground() : this.foregroundContentDrawable;
        this.materialCardView.setForeground(insetDrawable(this.fgDrawable));
    }

    /* access modifiers changed from: 0000 */
    public boolean isBackgroundOverwritten() {
        return this.isBackgroundOverwritten;
    }

    /* access modifiers changed from: 0000 */
    public void setBackgroundOverwritten(boolean z) {
        this.isBackgroundOverwritten = z;
    }

    /* access modifiers changed from: 0000 */
    public void setStrokeColor(ColorStateList colorStateList) {
        if (this.strokeColor != colorStateList) {
            this.strokeColor = colorStateList;
            updateStroke();
        }
    }

    /* access modifiers changed from: 0000 */
    @ColorInt
    public int getStrokeColor() {
        ColorStateList colorStateList = this.strokeColor;
        if (colorStateList == null) {
            return -1;
        }
        return colorStateList.getDefaultColor();
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public ColorStateList getStrokeColorStateList() {
        return this.strokeColor;
    }

    /* access modifiers changed from: 0000 */
    public void setStrokeWidth(@Dimension int i) {
        if (i != this.strokeWidth) {
            this.strokeWidth = i;
            adjustShapeAppearanceModelInsetByStroke();
            updateStroke();
        }
    }

    /* access modifiers changed from: 0000 */
    @Dimension
    public int getStrokeWidth() {
        return this.strokeWidth;
    }

    /* access modifiers changed from: 0000 */
    public void setCardBackgroundColor(ColorStateList colorStateList) {
        this.bgDrawable.setFillColor(colorStateList);
    }

    /* access modifiers changed from: 0000 */
    public ColorStateList getCardBackgroundColor() {
        return this.bgDrawable.getFillColor();
    }

    /* access modifiers changed from: 0000 */
    public void setUserContentPadding(int i, int i2, int i3, int i4) {
        this.userContentPadding.set(i, i2, i3, i4);
        updateContentPadding();
    }

    /* access modifiers changed from: 0000 */
    public Rect getUserContentPadding() {
        return this.userContentPadding;
    }

    /* access modifiers changed from: 0000 */
    public void updateClickable() {
        Drawable drawable = this.fgDrawable;
        this.fgDrawable = this.materialCardView.isClickable() ? getClickableForeground() : this.foregroundContentDrawable;
        Drawable drawable2 = this.fgDrawable;
        if (drawable != drawable2) {
            updateInsetForeground(drawable2);
        }
    }

    /* access modifiers changed from: 0000 */
    public void setCornerRadius(float f) {
        this.shapeAppearanceModel.setCornerRadius(f);
        this.shapeAppearanceModelInsetByStroke.setCornerRadius(f - ((float) this.strokeWidth));
        this.bgDrawable.invalidateSelf();
        this.fgDrawable.invalidateSelf();
        if (shouldAddCornerPaddingOutsideCardBackground() || shouldAddCornerPaddingInsideCardBackground()) {
            updateContentPadding();
        }
        if (shouldAddCornerPaddingOutsideCardBackground()) {
            updateInsets();
        }
    }

    /* access modifiers changed from: 0000 */
    public float getCornerRadius() {
        return this.shapeAppearanceModel.getTopLeftCorner().getCornerSize();
    }

    /* access modifiers changed from: 0000 */
    public void updateElevation() {
        this.bgDrawable.setElevation(this.materialCardView.getCardElevation());
    }

    /* access modifiers changed from: 0000 */
    public void updateInsets() {
        if (!isBackgroundOverwritten()) {
            this.materialCardView.setBackgroundInternal(insetDrawable(this.bgDrawable));
        }
        this.materialCardView.setForeground(insetDrawable(this.fgDrawable));
    }

    /* access modifiers changed from: 0000 */
    public void updateStroke() {
        this.foregroundContentDrawable.setStroke((float) this.strokeWidth, this.strokeColor);
    }

    /* access modifiers changed from: 0000 */
    @TargetApi(21)
    public void createOutlineProvider(@Nullable View view) {
        if (view != null) {
            this.materialCardView.setClipToOutline(false);
            if (canClipToOutline()) {
                view.setClipToOutline(true);
                view.setOutlineProvider(new ViewOutlineProvider() {
                    public void getOutline(View view, Outline outline) {
                        MaterialCardViewHelper.this.temporaryBounds.set(MaterialCardViewHelper.this.strokeWidth, MaterialCardViewHelper.this.strokeWidth, view.getWidth() - MaterialCardViewHelper.this.strokeWidth, view.getHeight() - MaterialCardViewHelper.this.strokeWidth);
                        MaterialCardViewHelper.this.drawableInsetByStroke.setBounds(MaterialCardViewHelper.this.temporaryBounds);
                        MaterialCardViewHelper.this.drawableInsetByStroke.getOutline(outline);
                    }
                });
            } else {
                view.setClipToOutline(false);
                view.setOutlineProvider(null);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void updateContentPadding() {
        int calculateActualCornerPadding = (int) ((shouldAddCornerPaddingInsideCardBackground() || shouldAddCornerPaddingOutsideCardBackground() ? calculateActualCornerPadding() : 0.0f) - getParentCardViewCalculatedCornerPadding());
        this.materialCardView.setContentLayoutPadding(this.userContentPadding.left + calculateActualCornerPadding, this.userContentPadding.top + calculateActualCornerPadding, this.userContentPadding.right + calculateActualCornerPadding, this.userContentPadding.bottom + calculateActualCornerPadding);
    }

    /* access modifiers changed from: 0000 */
    public void setCheckable(boolean z) {
        this.checkable = z;
    }

    /* access modifiers changed from: 0000 */
    public boolean isCheckable() {
        return this.checkable;
    }

    /* access modifiers changed from: 0000 */
    public void setRippleColor(@Nullable ColorStateList colorStateList) {
        this.rippleColor = colorStateList;
        updateRippleColor();
    }

    /* access modifiers changed from: 0000 */
    public void setCheckedIconTint(@Nullable ColorStateList colorStateList) {
        this.checkedIconTint = colorStateList;
        Drawable drawable = this.checkedIcon;
        if (drawable != null) {
            DrawableCompat.setTintList(drawable, colorStateList);
        }
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public ColorStateList getCheckedIconTint() {
        return this.checkedIconTint;
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public ColorStateList getRippleColor() {
        return this.rippleColor;
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public Drawable getCheckedIcon() {
        return this.checkedIcon;
    }

    /* access modifiers changed from: 0000 */
    public void setCheckedIcon(@Nullable Drawable drawable) {
        this.checkedIcon = drawable;
        if (drawable != null) {
            this.checkedIcon = DrawableCompat.wrap(drawable.mutate());
            DrawableCompat.setTintList(this.checkedIcon, this.checkedIconTint);
        }
        if (this.clickableForegroundDrawable != null) {
            this.clickableForegroundDrawable.setDrawableByLayerId(C1435R.C1438id.mtrl_card_checked_layer_id, createCheckedIconLayer());
        }
    }

    /* access modifiers changed from: 0000 */
    public void onMeasure(int i, int i2) {
        int i3;
        int i4;
        if (this.materialCardView.isCheckable() && this.clickableForegroundDrawable != null) {
            Resources resources = this.materialCardView.getResources();
            int dimensionPixelSize = resources.getDimensionPixelSize(C1435R.dimen.mtrl_card_checked_icon_margin);
            int dimensionPixelSize2 = resources.getDimensionPixelSize(C1435R.dimen.mtrl_card_checked_icon_size);
            int i5 = (i - dimensionPixelSize) - dimensionPixelSize2;
            int i6 = (i2 - dimensionPixelSize) - dimensionPixelSize2;
            if (ViewCompat.getLayoutDirection(this.materialCardView) == 1) {
                i3 = i5;
                i4 = dimensionPixelSize;
            } else {
                i4 = i5;
                i3 = dimensionPixelSize;
            }
            this.clickableForegroundDrawable.setLayerInset(2, i4, dimensionPixelSize, i3, i6);
        }
    }

    /* access modifiers changed from: 0000 */
    @RequiresApi(api = 23)
    public void forceRippleRedraw() {
        Drawable drawable = this.rippleDrawable;
        if (drawable != null) {
            Rect bounds = drawable.getBounds();
            int i = bounds.bottom;
            this.rippleDrawable.setBounds(bounds.left, bounds.top, bounds.right, i - 1);
            this.rippleDrawable.setBounds(bounds.left, bounds.top, bounds.right, i);
        }
    }

    private void adjustShapeAppearanceModelInsetByStroke() {
        this.shapeAppearanceModelInsetByStroke.getTopLeftCorner().setCornerSize(this.shapeAppearanceModel.getTopLeftCorner().getCornerSize() - ((float) this.strokeWidth));
        this.shapeAppearanceModelInsetByStroke.getTopRightCorner().setCornerSize(this.shapeAppearanceModel.getTopRightCorner().getCornerSize() - ((float) this.strokeWidth));
        this.shapeAppearanceModelInsetByStroke.getBottomRightCorner().setCornerSize(this.shapeAppearanceModel.getBottomRightCorner().getCornerSize() - ((float) this.strokeWidth));
        this.shapeAppearanceModelInsetByStroke.getBottomLeftCorner().setCornerSize(this.shapeAppearanceModel.getBottomLeftCorner().getCornerSize() - ((float) this.strokeWidth));
    }

    private void updateInsetForeground(Drawable drawable) {
        if (VERSION.SDK_INT < 23 || !(this.materialCardView.getForeground() instanceof InsetDrawable)) {
            this.materialCardView.setForeground(insetDrawable(drawable));
        } else {
            ((InsetDrawable) this.materialCardView.getForeground()).setDrawable(drawable);
        }
    }

    private Drawable insetDrawable(Drawable drawable) {
        int i;
        int i2;
        if ((VERSION.SDK_INT < 21) || this.materialCardView.getUseCompatPadding()) {
            i2 = (int) Math.ceil((double) calculateHorizontalBackgroundPadding());
            i = (int) Math.ceil((double) calculateVerticalBackgroundPadding());
        } else {
            i2 = 0;
            i = 0;
        }
        C14752 r2 = new InsetDrawable(drawable, i2, i, i2, i) {
            public boolean getPadding(Rect rect) {
                return false;
            }
        };
        return r2;
    }

    private float calculateVerticalBackgroundPadding() {
        return (this.materialCardView.getMaxCardElevation() * CARD_VIEW_SHADOW_MULTIPLIER) + (shouldAddCornerPaddingOutsideCardBackground() ? calculateActualCornerPadding() : 0.0f);
    }

    private float calculateHorizontalBackgroundPadding() {
        return this.materialCardView.getMaxCardElevation() + (shouldAddCornerPaddingOutsideCardBackground() ? calculateActualCornerPadding() : 0.0f);
    }

    private boolean canClipToOutline() {
        return VERSION.SDK_INT >= 21 && this.shapeAppearanceModel.isRoundRect();
    }

    private float getParentCardViewCalculatedCornerPadding() {
        if (!this.materialCardView.getPreventCornerOverlap() || (VERSION.SDK_INT >= 21 && !this.materialCardView.getUseCompatPadding())) {
            return 0.0f;
        }
        return (float) ((1.0d - COS_45) * ((double) this.materialCardView.getCardViewRadius()));
    }

    private boolean shouldAddCornerPaddingInsideCardBackground() {
        return this.materialCardView.getPreventCornerOverlap() && !canClipToOutline();
    }

    private boolean shouldAddCornerPaddingOutsideCardBackground() {
        return this.materialCardView.getPreventCornerOverlap() && canClipToOutline() && this.materialCardView.getUseCompatPadding();
    }

    private float calculateActualCornerPadding() {
        return Math.max(Math.max(calculateCornerPaddingForCornerTreatment(this.shapeAppearanceModel.getTopLeftCorner()), calculateCornerPaddingForCornerTreatment(this.shapeAppearanceModel.getTopRightCorner())), Math.max(calculateCornerPaddingForCornerTreatment(this.shapeAppearanceModel.getBottomRightCorner()), calculateCornerPaddingForCornerTreatment(this.shapeAppearanceModel.getBottomLeftCorner())));
    }

    private float calculateCornerPaddingForCornerTreatment(CornerTreatment cornerTreatment) {
        if (cornerTreatment instanceof RoundedCornerTreatment) {
            return (float) ((1.0d - COS_45) * ((double) cornerTreatment.getCornerSize()));
        }
        if (cornerTreatment instanceof CutCornerTreatment) {
            return cornerTreatment.getCornerSize() / 2.0f;
        }
        return 0.0f;
    }

    @NonNull
    private Drawable getClickableForeground() {
        if (this.rippleDrawable == null) {
            this.rippleDrawable = createForegroundRippleDrawable();
        }
        if (this.clickableForegroundDrawable == null) {
            this.clickableForegroundDrawable = new LayerDrawable(new Drawable[]{this.rippleDrawable, this.foregroundContentDrawable, createCheckedIconLayer()});
            this.clickableForegroundDrawable.setId(2, C1435R.C1438id.mtrl_card_checked_layer_id);
        }
        return this.clickableForegroundDrawable;
    }

    private Drawable createForegroundRippleDrawable() {
        if (RippleUtils.USE_FRAMEWORK_RIPPLE) {
            return new RippleDrawable(this.rippleColor, null, createForegroundShapeDrawable());
        }
        return createCompatRippleDrawable();
    }

    private Drawable createCompatRippleDrawable() {
        StateListDrawable stateListDrawable = new StateListDrawable();
        this.compatRippleDrawable = createForegroundShapeDrawable();
        this.compatRippleDrawable.setFillColor(this.rippleColor);
        stateListDrawable.addState(new int[]{16842919}, this.compatRippleDrawable);
        return stateListDrawable;
    }

    private void updateRippleColor() {
        if (RippleUtils.USE_FRAMEWORK_RIPPLE) {
            Drawable drawable = this.rippleDrawable;
            if (drawable != null) {
                ((RippleDrawable) drawable).setColor(this.rippleColor);
                return;
            }
        }
        MaterialShapeDrawable materialShapeDrawable = this.compatRippleDrawable;
        if (materialShapeDrawable != null) {
            materialShapeDrawable.setFillColor(this.rippleColor);
        }
    }

    @NonNull
    private Drawable createCheckedIconLayer() {
        StateListDrawable stateListDrawable = new StateListDrawable();
        Drawable drawable = this.checkedIcon;
        if (drawable != null) {
            stateListDrawable.addState(CHECKED_STATE_SET, drawable);
        }
        return stateListDrawable;
    }

    private MaterialShapeDrawable createForegroundShapeDrawable() {
        return new MaterialShapeDrawable(this.shapeAppearanceModel);
    }
}
