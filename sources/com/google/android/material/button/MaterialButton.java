package com.google.android.material.button;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import android.widget.Checkable;
import android.widget.CompoundButton;
import androidx.annotation.C0045Px;
import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DimenRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.annotation.RestrictTo.Scope;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.ViewCompat;
import androidx.core.widget.TextViewCompat;
import com.google.android.material.C1435R;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.Shapeable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Iterator;
import java.util.LinkedHashSet;

public class MaterialButton extends AppCompatButton implements Checkable, Shapeable {
    private static final int[] CHECKABLE_STATE_SET = {16842911};
    private static final int[] CHECKED_STATE_SET = {16842912};
    private static final int DEF_STYLE_RES = C1435R.C1440style.Widget_MaterialComponents_Button;
    public static final int ICON_GRAVITY_END = 3;
    public static final int ICON_GRAVITY_START = 1;
    public static final int ICON_GRAVITY_TEXT_END = 4;
    public static final int ICON_GRAVITY_TEXT_START = 2;
    private static final String LOG_TAG = "MaterialButton";
    private boolean broadcasting;
    private boolean checked;
    private Drawable icon;
    private int iconGravity;
    @C0045Px
    private int iconLeft;
    @C0045Px
    private int iconPadding;
    @C0045Px
    private int iconSize;
    private ColorStateList iconTint;
    private Mode iconTintMode;
    @Nullable
    private final MaterialButtonHelper materialButtonHelper;
    private final LinkedHashSet<OnCheckedChangeListener> onCheckedChangeListeners;
    @Nullable
    private OnPressedChangeListener onPressedChangeListenerInternal;

    @Retention(RetentionPolicy.SOURCE)
    public @interface IconGravity {
    }

    public interface OnCheckedChangeListener {
        void onCheckedChanged(MaterialButton materialButton, boolean z);
    }

    interface OnPressedChangeListener {
        void onPressedChanged(MaterialButton materialButton, boolean z);
    }

    public MaterialButton(Context context) {
        this(context, null);
    }

    public MaterialButton(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, C1435R.attr.materialButtonStyle);
    }

    public MaterialButton(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(ThemeEnforcement.createThemedContext(context, attributeSet, i, DEF_STYLE_RES), attributeSet, i);
        this.checked = false;
        this.broadcasting = false;
        this.onCheckedChangeListeners = new LinkedHashSet<>();
        Context context2 = getContext();
        TypedArray obtainStyledAttributes = ThemeEnforcement.obtainStyledAttributes(context2, attributeSet, C1435R.styleable.MaterialButton, i, DEF_STYLE_RES, new int[0]);
        this.iconPadding = obtainStyledAttributes.getDimensionPixelSize(C1435R.styleable.MaterialButton_iconPadding, 0);
        this.iconTintMode = ViewUtils.parseTintMode(obtainStyledAttributes.getInt(C1435R.styleable.MaterialButton_iconTintMode, -1), Mode.SRC_IN);
        this.iconTint = MaterialResources.getColorStateList(getContext(), obtainStyledAttributes, C1435R.styleable.MaterialButton_iconTint);
        this.icon = MaterialResources.getDrawable(getContext(), obtainStyledAttributes, C1435R.styleable.MaterialButton_icon);
        this.iconGravity = obtainStyledAttributes.getInteger(C1435R.styleable.MaterialButton_iconGravity, 1);
        this.iconSize = obtainStyledAttributes.getDimensionPixelSize(C1435R.styleable.MaterialButton_iconSize, 0);
        this.materialButtonHelper = new MaterialButtonHelper(this, new ShapeAppearanceModel(context2, attributeSet, i, DEF_STYLE_RES));
        this.materialButtonHelper.loadFromAttributes(obtainStyledAttributes);
        obtainStyledAttributes.recycle();
        setCompoundDrawablePadding(this.iconPadding);
        updateIcon();
    }

    private String getA11yClassName() {
        return (isCheckable() ? CompoundButton.class : Button.class).getName();
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(getA11yClassName());
        accessibilityNodeInfo.setCheckable(isCheckable());
        accessibilityNodeInfo.setChecked(isChecked());
        accessibilityNodeInfo.setClickable(isClickable());
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(getA11yClassName());
        accessibilityEvent.setChecked(isChecked());
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public void setSupportBackgroundTintList(@Nullable ColorStateList colorStateList) {
        if (isUsingOriginalBackground()) {
            this.materialButtonHelper.setSupportBackgroundTintList(colorStateList);
        } else {
            super.setSupportBackgroundTintList(colorStateList);
        }
    }

    @Nullable
    @RestrictTo({Scope.LIBRARY_GROUP})
    public ColorStateList getSupportBackgroundTintList() {
        if (isUsingOriginalBackground()) {
            return this.materialButtonHelper.getSupportBackgroundTintList();
        }
        return super.getSupportBackgroundTintList();
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public void setSupportBackgroundTintMode(@Nullable Mode mode) {
        if (isUsingOriginalBackground()) {
            this.materialButtonHelper.setSupportBackgroundTintMode(mode);
        } else {
            super.setSupportBackgroundTintMode(mode);
        }
    }

    @Nullable
    @RestrictTo({Scope.LIBRARY_GROUP})
    public Mode getSupportBackgroundTintMode() {
        if (isUsingOriginalBackground()) {
            return this.materialButtonHelper.getSupportBackgroundTintMode();
        }
        return super.getSupportBackgroundTintMode();
    }

    public void setBackgroundTintList(@Nullable ColorStateList colorStateList) {
        setSupportBackgroundTintList(colorStateList);
    }

    @Nullable
    public ColorStateList getBackgroundTintList() {
        return getSupportBackgroundTintList();
    }

    public void setBackgroundTintMode(@Nullable Mode mode) {
        setSupportBackgroundTintMode(mode);
    }

    @Nullable
    public Mode getBackgroundTintMode() {
        return getSupportBackgroundTintMode();
    }

    public void setBackgroundColor(@ColorInt int i) {
        if (isUsingOriginalBackground()) {
            this.materialButtonHelper.setBackgroundColor(i);
        } else {
            super.setBackgroundColor(i);
        }
    }

    public void setBackground(Drawable drawable) {
        setBackgroundDrawable(drawable);
    }

    public void setBackgroundResource(@DrawableRes int i) {
        setBackgroundDrawable(i != 0 ? AppCompatResources.getDrawable(getContext(), i) : null);
    }

    public void setBackgroundDrawable(Drawable drawable) {
        if (!isUsingOriginalBackground()) {
            super.setBackgroundDrawable(drawable);
        } else if (drawable != getBackground()) {
            Log.w(LOG_TAG, "Do not set the background; MaterialButton manages its own background drawable.");
            this.materialButtonHelper.setBackgroundOverwritten();
            super.setBackgroundDrawable(drawable);
        } else {
            getBackground().setState(drawable.getState());
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (VERSION.SDK_INT == 21) {
            MaterialButtonHelper materialButtonHelper2 = this.materialButtonHelper;
            if (materialButtonHelper2 != null) {
                materialButtonHelper2.updateMaskBounds(i4 - i2, i3 - i);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        updateIconPosition();
    }

    /* access modifiers changed from: protected */
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        super.onTextChanged(charSequence, i, i2, i3);
        updateIconPosition();
    }

    @RequiresApi(21)
    public void setElevation(float f) {
        super.setElevation(f);
        if (isUsingOriginalBackground()) {
            this.materialButtonHelper.getMaterialShapeDrawable().setElevation(f);
        }
    }

    private void updateIconPosition() {
        if (!(this.icon == null || getLayout() == null)) {
            int i = this.iconGravity;
            boolean z = false;
            if (i == 1 || i == 3) {
                this.iconLeft = 0;
                updateIcon();
            } else {
                TextPaint paint = getPaint();
                String charSequence = getText().toString();
                if (getTransformationMethod() != null) {
                    charSequence = getTransformationMethod().getTransformation(charSequence, this).toString();
                }
                int min = Math.min((int) paint.measureText(charSequence), getLayout().getEllipsizedWidth());
                int i2 = this.iconSize;
                if (i2 == 0) {
                    i2 = this.icon.getIntrinsicWidth();
                }
                int measuredWidth = (((((getMeasuredWidth() - min) - ViewCompat.getPaddingEnd(this)) - i2) - this.iconPadding) - ViewCompat.getPaddingStart(this)) / 2;
                boolean isLayoutRTL = isLayoutRTL();
                if (this.iconGravity == 4) {
                    z = true;
                }
                if (isLayoutRTL != z) {
                    measuredWidth = -measuredWidth;
                }
                if (this.iconLeft != measuredWidth) {
                    this.iconLeft = measuredWidth;
                    updateIcon();
                }
            }
        }
    }

    private boolean isLayoutRTL() {
        return ViewCompat.getLayoutDirection(this) == 1;
    }

    /* access modifiers changed from: 0000 */
    public void setInternalBackground(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
    }

    public void setIconPadding(@C0045Px int i) {
        if (this.iconPadding != i) {
            this.iconPadding = i;
            setCompoundDrawablePadding(i);
        }
    }

    @C0045Px
    public int getIconPadding() {
        return this.iconPadding;
    }

    public void setIconSize(@C0045Px int i) {
        if (i < 0) {
            throw new IllegalArgumentException("iconSize cannot be less than 0");
        } else if (this.iconSize != i) {
            this.iconSize = i;
            updateIcon();
        }
    }

    @C0045Px
    public int getIconSize() {
        return this.iconSize;
    }

    public void setIcon(@Nullable Drawable drawable) {
        if (this.icon != drawable) {
            this.icon = drawable;
            updateIcon();
        }
    }

    public void setIconResource(@DrawableRes int i) {
        setIcon(i != 0 ? AppCompatResources.getDrawable(getContext(), i) : null);
    }

    public Drawable getIcon() {
        return this.icon;
    }

    public void setIconTint(@Nullable ColorStateList colorStateList) {
        if (this.iconTint != colorStateList) {
            this.iconTint = colorStateList;
            updateIcon();
        }
    }

    public void setIconTintResource(@ColorRes int i) {
        setIconTint(AppCompatResources.getColorStateList(getContext(), i));
    }

    public ColorStateList getIconTint() {
        return this.iconTint;
    }

    public void setIconTintMode(Mode mode) {
        if (this.iconTintMode != mode) {
            this.iconTintMode = mode;
            updateIcon();
        }
    }

    public Mode getIconTintMode() {
        return this.iconTintMode;
    }

    private void updateIcon() {
        Drawable drawable = this.icon;
        if (drawable != null) {
            this.icon = DrawableCompat.wrap(drawable).mutate();
            DrawableCompat.setTintList(this.icon, this.iconTint);
            Mode mode = this.iconTintMode;
            if (mode != null) {
                DrawableCompat.setTintMode(this.icon, mode);
            }
            int i = this.iconSize;
            if (i == 0) {
                i = this.icon.getIntrinsicWidth();
            }
            int i2 = this.iconSize;
            if (i2 == 0) {
                i2 = this.icon.getIntrinsicHeight();
            }
            Drawable drawable2 = this.icon;
            int i3 = this.iconLeft;
            drawable2.setBounds(i3, 0, i + i3, i2);
        }
        int i4 = this.iconGravity;
        if (i4 == 1 || i4 == 2) {
            TextViewCompat.setCompoundDrawablesRelative(this, this.icon, null, null, null);
        } else {
            TextViewCompat.setCompoundDrawablesRelative(this, null, null, this.icon, null);
        }
    }

    public void setRippleColor(@Nullable ColorStateList colorStateList) {
        if (isUsingOriginalBackground()) {
            this.materialButtonHelper.setRippleColor(colorStateList);
        }
    }

    public void setRippleColorResource(@ColorRes int i) {
        if (isUsingOriginalBackground()) {
            setRippleColor(AppCompatResources.getColorStateList(getContext(), i));
        }
    }

    public ColorStateList getRippleColor() {
        if (isUsingOriginalBackground()) {
            return this.materialButtonHelper.getRippleColor();
        }
        return null;
    }

    public void setStrokeColor(@Nullable ColorStateList colorStateList) {
        if (isUsingOriginalBackground()) {
            this.materialButtonHelper.setStrokeColor(colorStateList);
        }
    }

    public void setStrokeColorResource(@ColorRes int i) {
        if (isUsingOriginalBackground()) {
            setStrokeColor(AppCompatResources.getColorStateList(getContext(), i));
        }
    }

    public ColorStateList getStrokeColor() {
        if (isUsingOriginalBackground()) {
            return this.materialButtonHelper.getStrokeColor();
        }
        return null;
    }

    public void setStrokeWidth(@C0045Px int i) {
        if (isUsingOriginalBackground()) {
            this.materialButtonHelper.setStrokeWidth(i);
        }
    }

    public void setStrokeWidthResource(@DimenRes int i) {
        if (isUsingOriginalBackground()) {
            setStrokeWidth(getResources().getDimensionPixelSize(i));
        }
    }

    @C0045Px
    public int getStrokeWidth() {
        if (isUsingOriginalBackground()) {
            return this.materialButtonHelper.getStrokeWidth();
        }
        return 0;
    }

    public void setCornerRadius(@C0045Px int i) {
        if (isUsingOriginalBackground()) {
            this.materialButtonHelper.setCornerRadius(i);
        }
    }

    public void setCornerRadiusResource(@DimenRes int i) {
        if (isUsingOriginalBackground()) {
            setCornerRadius(getResources().getDimensionPixelSize(i));
        }
    }

    @C0045Px
    public int getCornerRadius() {
        if (isUsingOriginalBackground()) {
            return this.materialButtonHelper.getCornerRadius();
        }
        return 0;
    }

    public int getIconGravity() {
        return this.iconGravity;
    }

    public void setIconGravity(int i) {
        if (this.iconGravity != i) {
            this.iconGravity = i;
            updateIconPosition();
        }
    }

    /* access modifiers changed from: protected */
    public int[] onCreateDrawableState(int i) {
        int[] onCreateDrawableState = super.onCreateDrawableState(i + 2);
        if (isCheckable()) {
            mergeDrawableStates(onCreateDrawableState, CHECKABLE_STATE_SET);
        }
        if (isChecked()) {
            mergeDrawableStates(onCreateDrawableState, CHECKED_STATE_SET);
        }
        return onCreateDrawableState;
    }

    public void addOnCheckedChangeListener(@NonNull OnCheckedChangeListener onCheckedChangeListener) {
        this.onCheckedChangeListeners.add(onCheckedChangeListener);
    }

    public void removeOnCheckedChangeListener(@NonNull OnCheckedChangeListener onCheckedChangeListener) {
        this.onCheckedChangeListeners.remove(onCheckedChangeListener);
    }

    public void clearOnCheckedChangeListeners() {
        this.onCheckedChangeListeners.clear();
    }

    public void setChecked(boolean z) {
        if (isCheckable() && isEnabled() && this.checked != z) {
            this.checked = z;
            refreshDrawableState();
            if (!this.broadcasting) {
                this.broadcasting = true;
                Iterator it = this.onCheckedChangeListeners.iterator();
                while (it.hasNext()) {
                    ((OnCheckedChangeListener) it.next()).onCheckedChanged(this, this.checked);
                }
                this.broadcasting = false;
            }
        }
    }

    public boolean isChecked() {
        return this.checked;
    }

    public void toggle() {
        setChecked(!this.checked);
    }

    public boolean performClick() {
        toggle();
        return super.performClick();
    }

    public boolean isCheckable() {
        MaterialButtonHelper materialButtonHelper2 = this.materialButtonHelper;
        return materialButtonHelper2 != null && materialButtonHelper2.isCheckable();
    }

    public void setCheckable(boolean z) {
        if (isUsingOriginalBackground()) {
            this.materialButtonHelper.setCheckable(z);
        }
    }

    public void setShapeAppearanceModel(@NonNull ShapeAppearanceModel shapeAppearanceModel) {
        if (isUsingOriginalBackground()) {
            this.materialButtonHelper.setShapeAppearanceModel(shapeAppearanceModel);
            return;
        }
        throw new IllegalStateException("Attempted to set ShapeAppearanceModel on a MaterialButton which has an overwritten background.");
    }

    @NonNull
    public ShapeAppearanceModel getShapeAppearanceModel() {
        if (isUsingOriginalBackground()) {
            return this.materialButtonHelper.getShapeAppearanceModel();
        }
        throw new IllegalStateException("Attempted to get ShapeAppearanceModel from a MaterialButton which has an overwritten background.");
    }

    /* access modifiers changed from: 0000 */
    public void setOnPressedChangeListenerInternal(@Nullable OnPressedChangeListener onPressedChangeListener) {
        this.onPressedChangeListenerInternal = onPressedChangeListener;
    }

    public void setPressed(boolean z) {
        OnPressedChangeListener onPressedChangeListener = this.onPressedChangeListenerInternal;
        if (onPressedChangeListener != null) {
            onPressedChangeListener.onPressedChanged(this, z);
        }
        super.setPressed(z);
    }

    private boolean isUsingOriginalBackground() {
        MaterialButtonHelper materialButtonHelper2 = this.materialButtonHelper;
        return materialButtonHelper2 != null && !materialButtonHelper2.isBackgroundOverwritten();
    }

    /* access modifiers changed from: 0000 */
    public void setShouldDrawSurfaceColorStroke(boolean z) {
        if (isUsingOriginalBackground()) {
            this.materialButtonHelper.setShouldDrawSurfaceColorStroke(z);
        }
    }
}
