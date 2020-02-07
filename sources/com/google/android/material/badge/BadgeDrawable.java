package com.google.android.material.badge;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint.Align;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.AttrRes;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.PluralsRes;
import androidx.annotation.StringRes;
import androidx.annotation.StyleRes;
import androidx.annotation.StyleableRes;
import androidx.core.view.ViewCompat;
import com.google.android.material.C1435R;
import com.google.android.material.internal.TextDrawableHelper;
import com.google.android.material.internal.TextDrawableHelper.TextDrawableDelegate;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.resources.TextAppearance;
import com.google.android.material.shape.MaterialShapeDrawable;

public class BadgeDrawable extends Drawable implements TextDrawableDelegate {
    private static final int BADGE_NUMBER_NONE = -1;
    static final String DEFAULT_EXCEED_MAX_BADGE_NUMBER_SUFFIX = "+";
    private static final int DEFAULT_MAX_BADGE_CHARACTER_COUNT = 4;
    private static final int MAX_CIRCULAR_BADGE_NUMBER_COUNT = 99;
    private final Rect badgeBounds = new Rect();
    private float badgeCenterX;
    private float badgeCenterY;
    private final float badgeRadius;
    private final float badgeWidePadding;
    private final float badgeWithTextRadius;
    private final Context context;
    private int maxBadgeNumber;
    private final SavedState savedState;
    private final MaterialShapeDrawable shapeDrawable = new MaterialShapeDrawable();
    private final TextDrawableHelper textDrawableHelper;
    private final Rect tmpRect = new Rect();

    public static final class SavedState implements Parcelable {
        public static final Creator<SavedState> CREATOR = new Creator<SavedState>() {
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        /* access modifiers changed from: private */
        public int alpha = 255;
        /* access modifiers changed from: private */
        @ColorInt
        public int backgroundColor;
        /* access modifiers changed from: private */
        @ColorInt
        public int badgeTextColor;
        /* access modifiers changed from: private */
        public CharSequence contentDescriptionNumberless;
        /* access modifiers changed from: private */
        @PluralsRes
        public int contentDescriptionQuantityStrings;
        /* access modifiers changed from: private */
        public int maxCharacterCount;
        /* access modifiers changed from: private */
        public int number = -1;

        public int describeContents() {
            return 0;
        }

        public SavedState(Context context) {
            this.badgeTextColor = new TextAppearance(context, C1435R.C1440style.TextAppearance_MaterialComponents_Badge).textColor.getDefaultColor();
            this.contentDescriptionNumberless = context.getString(C1435R.string.mtrl_badge_numberless_content_description);
            this.contentDescriptionQuantityStrings = C1435R.plurals.mtrl_badge_content_description;
        }

        protected SavedState(Parcel parcel) {
            this.backgroundColor = parcel.readInt();
            this.badgeTextColor = parcel.readInt();
            this.alpha = parcel.readInt();
            this.number = parcel.readInt();
            this.maxCharacterCount = parcel.readInt();
            this.contentDescriptionNumberless = parcel.readString();
            this.contentDescriptionQuantityStrings = parcel.readInt();
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.backgroundColor);
            parcel.writeInt(this.badgeTextColor);
            parcel.writeInt(this.alpha);
            parcel.writeInt(this.number);
            parcel.writeInt(this.maxCharacterCount);
            parcel.writeString(this.contentDescriptionNumberless.toString());
            parcel.writeInt(this.contentDescriptionQuantityStrings);
        }
    }

    public int getOpacity() {
        return -3;
    }

    public boolean isStateful() {
        return false;
    }

    public void setColorFilter(ColorFilter colorFilter) {
    }

    public SavedState getSavedState() {
        return this.savedState;
    }

    public static BadgeDrawable createFromSavedState(Context context2, @NonNull SavedState savedState2) {
        BadgeDrawable badgeDrawable = new BadgeDrawable(context2);
        badgeDrawable.restoreFromSavedState(savedState2);
        return badgeDrawable;
    }

    public static BadgeDrawable create(Context context2) {
        return createFromAttributes(context2, null, C1435R.attr.badgeStyle, C1435R.C1440style.Widget_MaterialComponents_Badge);
    }

    public static BadgeDrawable createFromAttributes(@NonNull Context context2, AttributeSet attributeSet, @AttrRes int i, @StyleRes int i2) {
        BadgeDrawable badgeDrawable = new BadgeDrawable(context2);
        badgeDrawable.loadDefaultStateFromAttributes(context2, attributeSet, i, i2);
        return badgeDrawable;
    }

    public void setVisible(boolean z) {
        setVisible(z, false);
    }

    private void restoreFromSavedState(SavedState savedState2) {
        setMaxCharacterCount(savedState2.maxCharacterCount);
        if (savedState2.number != -1) {
            setNumber(savedState2.number);
        }
        setBackgroundColor(savedState2.backgroundColor);
        setBadgeTextColor(savedState2.badgeTextColor);
    }

    private void loadDefaultStateFromAttributes(Context context2, AttributeSet attributeSet, @AttrRes int i, @StyleRes int i2) {
        TypedArray obtainStyledAttributes = ThemeEnforcement.obtainStyledAttributes(context2, attributeSet, C1435R.styleable.Badge, i, i2, new int[0]);
        setMaxCharacterCount(obtainStyledAttributes.getInt(C1435R.styleable.Badge_maxCharacterCount, 4));
        if (obtainStyledAttributes.hasValue(C1435R.styleable.Badge_number)) {
            setNumber(obtainStyledAttributes.getInt(C1435R.styleable.Badge_number, 0));
        }
        setBackgroundColor(readColorFromAttributes(context2, obtainStyledAttributes, C1435R.styleable.Badge_backgroundColor));
        if (obtainStyledAttributes.hasValue(C1435R.styleable.Badge_badgeTextColor)) {
            setBadgeTextColor(readColorFromAttributes(context2, obtainStyledAttributes, C1435R.styleable.Badge_badgeTextColor));
        }
        obtainStyledAttributes.recycle();
    }

    private static int readColorFromAttributes(Context context2, TypedArray typedArray, @StyleableRes int i) {
        return MaterialResources.getColorStateList(context2, typedArray, i).getDefaultColor();
    }

    private BadgeDrawable(Context context2) {
        this.context = context2;
        ThemeEnforcement.checkMaterialTheme(context2);
        Resources resources = context2.getResources();
        this.badgeRadius = (float) resources.getDimensionPixelSize(C1435R.dimen.mtrl_badge_radius);
        this.badgeWidePadding = (float) resources.getDimensionPixelSize(C1435R.dimen.mtrl_badge_long_text_horizontal_padding);
        this.badgeWithTextRadius = (float) resources.getDimensionPixelSize(C1435R.dimen.mtrl_badge_with_text_radius);
        this.textDrawableHelper = new TextDrawableHelper(this);
        this.textDrawableHelper.getTextPaint().setTextAlign(Align.CENTER);
        this.savedState = new SavedState(context2);
        setTextAppearanceResource(C1435R.C1440style.TextAppearance_MaterialComponents_Badge);
    }

    public void updateBadgeCoordinates(@NonNull View view, @Nullable ViewGroup viewGroup) {
        calculateBadgeCenterCoordinates(view, viewGroup);
        updateBounds();
        invalidateSelf();
    }

    @ColorInt
    public int getBackgroundColor() {
        return this.shapeDrawable.getFillColor().getDefaultColor();
    }

    public void setBackgroundColor(@ColorInt int i) {
        this.savedState.backgroundColor = i;
        ColorStateList valueOf = ColorStateList.valueOf(i);
        if (this.shapeDrawable.getFillColor() != valueOf) {
            this.shapeDrawable.setFillColor(valueOf);
            invalidateSelf();
        }
    }

    @ColorInt
    public int getBadgeTextColor() {
        return this.textDrawableHelper.getTextPaint().getColor();
    }

    public void setBadgeTextColor(@ColorInt int i) {
        this.savedState.badgeTextColor = i;
        if (this.textDrawableHelper.getTextPaint().getColor() != i) {
            this.textDrawableHelper.getTextPaint().setColor(i);
            invalidateSelf();
        }
    }

    public boolean hasNumber() {
        return this.savedState.number != -1;
    }

    public int getNumber() {
        if (!hasNumber()) {
            return 0;
        }
        return this.savedState.number;
    }

    public void setNumber(int i) {
        int max = Math.max(0, i);
        if (this.savedState.number != max) {
            this.savedState.number = max;
            this.textDrawableHelper.setTextWidthDirty(true);
            updateBounds();
            invalidateSelf();
        }
    }

    public void clearBadgeNumber() {
        this.savedState.number = -1;
        invalidateSelf();
    }

    public int getMaxCharacterCount() {
        return this.savedState.maxCharacterCount;
    }

    public void setMaxCharacterCount(int i) {
        if (this.savedState.maxCharacterCount != i) {
            this.savedState.maxCharacterCount = i;
            updateMaxBadgeNumber();
            this.textDrawableHelper.setTextWidthDirty(true);
            updateBounds();
            invalidateSelf();
        }
    }

    public int getAlpha() {
        return this.savedState.alpha;
    }

    public void setAlpha(int i) {
        this.savedState.alpha = i;
        this.textDrawableHelper.getTextPaint().setAlpha(i);
        invalidateSelf();
    }

    public int getIntrinsicHeight() {
        return this.badgeBounds.height();
    }

    public int getIntrinsicWidth() {
        return this.badgeBounds.width();
    }

    public void draw(Canvas canvas) {
        if (!getBounds().isEmpty() && getAlpha() != 0 && isVisible()) {
            this.shapeDrawable.draw(canvas);
            if (hasNumber()) {
                drawText(canvas);
            }
        }
    }

    public void onTextSizeChange() {
        invalidateSelf();
    }

    public boolean onStateChange(int[] iArr) {
        return super.onStateChange(iArr);
    }

    public void setContentDescriptionNumberless(CharSequence charSequence) {
        this.savedState.contentDescriptionNumberless = charSequence;
    }

    public void setContentDescriptionQuantityStringsResource(@StringRes int i) {
        this.savedState.contentDescriptionQuantityStrings = i;
    }

    @Nullable
    public CharSequence getContentDescription(Context context2) {
        if (!isVisible()) {
            return null;
        }
        if (!hasNumber()) {
            return this.savedState.contentDescriptionNumberless;
        }
        if (this.savedState.contentDescriptionQuantityStrings <= 0) {
            return null;
        }
        return context2.getResources().getQuantityString(this.savedState.contentDescriptionQuantityStrings, getNumber(), new Object[]{Integer.valueOf(getNumber())});
    }

    private void setTextAppearanceResource(@StyleRes int i) {
        setTextAppearance(new TextAppearance(this.context, i));
    }

    private void setTextAppearance(@Nullable TextAppearance textAppearance) {
        if (this.textDrawableHelper.getTextAppearance() != textAppearance) {
            this.textDrawableHelper.setTextAppearance(textAppearance, this.context);
            updateBounds();
        }
    }

    private void updateBounds() {
        float f;
        this.tmpRect.set(this.badgeBounds);
        if (getNumber() <= 99) {
            f = !hasNumber() ? this.badgeRadius : this.badgeWithTextRadius;
            BadgeUtils.updateBadgeBounds(this.badgeBounds, this.badgeCenterX, this.badgeCenterY, f, f);
        } else {
            f = this.badgeWithTextRadius;
            BadgeUtils.updateBadgeBounds(this.badgeBounds, this.badgeCenterX, this.badgeCenterY, (this.textDrawableHelper.getTextWidth(getBadgeText()) / 2.0f) + this.badgeWidePadding, f);
        }
        this.shapeDrawable.setCornerRadius(f);
        if (!this.tmpRect.equals(this.badgeBounds)) {
            this.shapeDrawable.setBounds(this.badgeBounds);
        }
    }

    private void drawText(Canvas canvas) {
        Rect rect = new Rect();
        String badgeText = getBadgeText();
        this.textDrawableHelper.getTextPaint().getTextBounds(badgeText, 0, badgeText.length(), rect);
        canvas.drawText(badgeText, this.badgeCenterX, this.badgeCenterY + ((float) (rect.height() / 2)), this.textDrawableHelper.getTextPaint());
    }

    private String getBadgeText() {
        if (getNumber() <= this.maxBadgeNumber) {
            return Integer.toString(getNumber());
        }
        return this.context.getString(C1435R.string.mtrl_exceed_max_badge_number_suffix, new Object[]{Integer.valueOf(this.maxBadgeNumber), "+"});
    }

    private void updateMaxBadgeNumber() {
        this.maxBadgeNumber = ((int) Math.pow(10.0d, ((double) getMaxCharacterCount()) - 1.0d)) - 1;
    }

    private void calculateBadgeCenterCoordinates(@NonNull View view, @Nullable ViewGroup viewGroup) {
        Resources resources = this.context.getResources();
        Rect rect = new Rect();
        view.getDrawingRect(rect);
        rect.top += resources.getDimensionPixelSize(C1435R.dimen.mtrl_badge_vertical_offset);
        if (viewGroup != null || BadgeUtils.USE_COMPAT_PARENT) {
            if (viewGroup == null) {
                viewGroup = (ViewGroup) view.getParent();
            }
            viewGroup.offsetDescendantRectToMyCoords(view, rect);
        }
        this.badgeCenterX = (float) (ViewCompat.getLayoutDirection(view) == 0 ? rect.right : rect.left);
        this.badgeCenterY = (float) rect.top;
    }
}
