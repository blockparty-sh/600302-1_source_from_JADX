package com.google.android.material.card;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Checkable;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.Dimension;
import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.cardview.widget.CardView;
import com.google.android.material.C1435R;
import com.google.android.material.internal.ThemeEnforcement;

public class MaterialCardView extends CardView implements Checkable {
    private static final int[] CHECKABLE_STATE_SET = {16842911};
    private static final int[] CHECKED_STATE_SET = {16842912};
    private static final int DEF_STYLE_RES = C1435R.C1440style.Widget_MaterialComponents_CardView;
    private static final int[] DRAGGED_STATE_SET = {C1435R.attr.state_dragged};
    private static final String LOG_TAG = "MaterialCardView";
    private final MaterialCardViewHelper cardViewHelper;
    private boolean checked;
    private final FrameLayout contentLayout;
    private boolean dragged;
    private final boolean isParentCardViewDoneInitializing;
    private OnCheckedChangeListener onCheckedChangeListener;

    public interface OnCheckedChangeListener {
        void onCheckedChanged(MaterialCardView materialCardView, boolean z);
    }

    public MaterialCardView(Context context) {
        this(context, null);
    }

    public MaterialCardView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, C1435R.attr.materialCardViewStyle);
    }

    public MaterialCardView(Context context, AttributeSet attributeSet, int i) {
        super(ThemeEnforcement.createThemedContext(context, attributeSet, i, DEF_STYLE_RES), attributeSet, i);
        this.checked = false;
        this.dragged = false;
        this.isParentCardViewDoneInitializing = true;
        Context context2 = getContext();
        TypedArray obtainStyledAttributes = ThemeEnforcement.obtainStyledAttributes(context2, attributeSet, C1435R.styleable.MaterialCardView, i, DEF_STYLE_RES, new int[0]);
        this.contentLayout = new FrameLayout(context2);
        super.addView(this.contentLayout, -1, new LayoutParams(-1, -1));
        this.cardViewHelper = new MaterialCardViewHelper(this, attributeSet, i, DEF_STYLE_RES);
        this.cardViewHelper.setCardBackgroundColor(super.getCardBackgroundColor());
        this.cardViewHelper.setUserContentPadding(super.getContentPaddingLeft(), super.getContentPaddingTop(), super.getContentPaddingRight(), super.getContentPaddingBottom());
        super.setContentPadding(0, 0, 0, 0);
        this.cardViewHelper.loadFromAttributes(obtainStyledAttributes);
        updateContentLayout();
        obtainStyledAttributes.recycle();
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(MaterialCardView.class.getName());
        accessibilityNodeInfo.setCheckable(isCheckable());
        accessibilityNodeInfo.setLongClickable(isCheckable());
        accessibilityNodeInfo.setClickable(isClickable());
    }

    private void updateContentLayout() {
        if (VERSION.SDK_INT >= 21) {
            this.cardViewHelper.createOutlineProvider(this.contentLayout);
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.cardViewHelper.onMeasure(getMeasuredWidth(), getMeasuredHeight());
    }

    public void setStrokeColor(@ColorInt int i) {
        this.cardViewHelper.setStrokeColor(ColorStateList.valueOf(i));
    }

    public void setStrokeColor(ColorStateList colorStateList) {
        this.cardViewHelper.setStrokeColor(colorStateList);
    }

    @ColorInt
    @Deprecated
    public int getStrokeColor() {
        return this.cardViewHelper.getStrokeColor();
    }

    @Nullable
    public ColorStateList getStrokeColorStateList() {
        return this.cardViewHelper.getStrokeColorStateList();
    }

    public void setStrokeWidth(@Dimension int i) {
        this.cardViewHelper.setStrokeWidth(i);
        updateContentLayout();
    }

    @Dimension
    public int getStrokeWidth() {
        return this.cardViewHelper.getStrokeWidth();
    }

    public void setRadius(float f) {
        super.setRadius(f);
        this.cardViewHelper.setCornerRadius(f);
        updateContentLayout();
    }

    public float getRadius() {
        return this.cardViewHelper.getCornerRadius();
    }

    /* access modifiers changed from: 0000 */
    public float getCardViewRadius() {
        return super.getRadius();
    }

    public void setContentPadding(int i, int i2, int i3, int i4) {
        this.cardViewHelper.setUserContentPadding(i, i2, i3, i4);
    }

    public int getContentPaddingLeft() {
        return this.cardViewHelper.getUserContentPadding().left;
    }

    public int getContentPaddingTop() {
        return this.cardViewHelper.getUserContentPadding().top;
    }

    public int getContentPaddingRight() {
        return this.cardViewHelper.getUserContentPadding().right;
    }

    public int getContentPaddingBottom() {
        return this.cardViewHelper.getUserContentPadding().bottom;
    }

    public void setCardBackgroundColor(@ColorInt int i) {
        this.cardViewHelper.setCardBackgroundColor(ColorStateList.valueOf(i));
    }

    public void setCardBackgroundColor(@Nullable ColorStateList colorStateList) {
        this.cardViewHelper.setCardBackgroundColor(colorStateList);
    }

    public ColorStateList getCardBackgroundColor() {
        return this.cardViewHelper.getCardBackgroundColor();
    }

    public void setLayoutParams(ViewGroup.LayoutParams layoutParams) {
        super.setLayoutParams(layoutParams);
        LayoutParams layoutParams2 = (LayoutParams) this.contentLayout.getLayoutParams();
        if (layoutParams instanceof LayoutParams) {
            layoutParams2.gravity = ((LayoutParams) layoutParams).gravity;
            this.contentLayout.requestLayout();
        }
    }

    public void setClickable(boolean z) {
        super.setClickable(z);
        this.cardViewHelper.updateClickable();
    }

    public void setCardElevation(float f) {
        super.setCardElevation(f);
        this.cardViewHelper.updateElevation();
    }

    public void setMaxCardElevation(float f) {
        super.setMaxCardElevation(f);
        this.cardViewHelper.updateInsets();
    }

    public void setUseCompatPadding(boolean z) {
        super.setUseCompatPadding(z);
        this.cardViewHelper.updateInsets();
        this.cardViewHelper.updateContentPadding();
    }

    public void setPreventCornerOverlap(boolean z) {
        super.setPreventCornerOverlap(z);
        this.cardViewHelper.updateInsets();
        this.cardViewHelper.updateContentPadding();
    }

    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        this.contentLayout.addView(view, i, layoutParams);
    }

    public void removeAllViews() {
        this.contentLayout.removeAllViews();
    }

    public void removeView(View view) {
        this.contentLayout.removeView(view);
    }

    public void removeViewInLayout(View view) {
        this.contentLayout.removeViewInLayout(view);
    }

    public void removeViewsInLayout(int i, int i2) {
        this.contentLayout.removeViewsInLayout(i, i2);
    }

    public void removeViewAt(int i) {
        this.contentLayout.removeViewAt(i);
    }

    public void removeViews(int i, int i2) {
        this.contentLayout.removeViews(i, i2);
    }

    public void setBackground(Drawable drawable) {
        setBackgroundDrawable(drawable);
    }

    public void setBackgroundDrawable(Drawable drawable) {
        if (this.isParentCardViewDoneInitializing) {
            if (!this.cardViewHelper.isBackgroundOverwritten()) {
                Log.i(LOG_TAG, "Setting a custom background is not supported.");
                this.cardViewHelper.setBackgroundOverwritten(true);
            }
            super.setBackgroundDrawable(drawable);
        }
    }

    /* access modifiers changed from: 0000 */
    public void setBackgroundInternal(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
    }

    /* access modifiers changed from: 0000 */
    public void setContentLayoutPadding(int i, int i2, int i3, int i4) {
        this.contentLayout.setPadding(i, i2, i3, i4);
    }

    public boolean isChecked() {
        return this.checked;
    }

    public void setChecked(boolean z) {
        if (this.checked != z) {
            toggle();
        }
    }

    public void setDragged(boolean z) {
        if (this.dragged != z) {
            this.dragged = z;
            refreshDrawableState();
            forceRippleRedrawIfNeeded();
            invalidate();
        }
    }

    public boolean isDragged() {
        return this.dragged;
    }

    public boolean isCheckable() {
        MaterialCardViewHelper materialCardViewHelper = this.cardViewHelper;
        return materialCardViewHelper != null && materialCardViewHelper.isCheckable();
    }

    public void setCheckable(boolean z) {
        this.cardViewHelper.setCheckable(z);
    }

    public void toggle() {
        if (isCheckable() && isEnabled()) {
            this.checked = !this.checked;
            refreshDrawableState();
            forceRippleRedrawIfNeeded();
            OnCheckedChangeListener onCheckedChangeListener2 = this.onCheckedChangeListener;
            if (onCheckedChangeListener2 != null) {
                onCheckedChangeListener2.onCheckedChanged(this, this.checked);
            }
        }
    }

    /* access modifiers changed from: protected */
    public int[] onCreateDrawableState(int i) {
        int[] onCreateDrawableState = super.onCreateDrawableState(i + 3);
        if (isCheckable()) {
            mergeDrawableStates(onCreateDrawableState, CHECKABLE_STATE_SET);
        }
        if (isChecked()) {
            mergeDrawableStates(onCreateDrawableState, CHECKED_STATE_SET);
        }
        if (isDragged()) {
            mergeDrawableStates(onCreateDrawableState, DRAGGED_STATE_SET);
        }
        return onCreateDrawableState;
    }

    public void setOnCheckedChangeListener(@Nullable OnCheckedChangeListener onCheckedChangeListener2) {
        this.onCheckedChangeListener = onCheckedChangeListener2;
    }

    public void setRippleColor(@Nullable ColorStateList colorStateList) {
        this.cardViewHelper.setRippleColor(colorStateList);
    }

    public void setRippleColorResource(@ColorRes int i) {
        this.cardViewHelper.setRippleColor(AppCompatResources.getColorStateList(getContext(), i));
    }

    public ColorStateList getRippleColor() {
        return this.cardViewHelper.getRippleColor();
    }

    @Nullable
    public Drawable getCheckedIcon() {
        return this.cardViewHelper.getCheckedIcon();
    }

    public void setCheckedIconResource(@DrawableRes int i) {
        this.cardViewHelper.setCheckedIcon(AppCompatResources.getDrawable(getContext(), i));
    }

    public void setCheckedIcon(@Nullable Drawable drawable) {
        this.cardViewHelper.setCheckedIcon(drawable);
    }

    @Nullable
    public ColorStateList getCheckedIconTint() {
        return this.cardViewHelper.getCheckedIconTint();
    }

    public void setCheckedIconTint(@Nullable ColorStateList colorStateList) {
        this.cardViewHelper.setCheckedIconTint(colorStateList);
    }

    private void forceRippleRedrawIfNeeded() {
        if (VERSION.SDK_INT > 26) {
            this.cardViewHelper.forceRippleRedraw();
        }
    }
}
