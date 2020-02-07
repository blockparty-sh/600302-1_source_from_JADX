package com.google.android.material.textfield;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.ClassLoaderCreator;
import android.os.Parcelable.Creator;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewStructure;
import android.view.accessibility.AccessibilityEvent;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DimenRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.annotation.StyleRes;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatDrawableManager;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.DrawableUtils;
import androidx.appcompat.widget.TintTypedArray;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.MarginLayoutParamsCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.widget.TextViewCompat;
import androidx.customview.view.AbsSavedState;
import com.google.android.material.C1435R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.internal.CheckableImageButton;
import com.google.android.material.internal.CollapsingTextHelper;
import com.google.android.material.internal.DescendantOffsetUtils;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Iterator;
import java.util.LinkedHashSet;

public class TextInputLayout extends LinearLayout {
    public static final int BOX_BACKGROUND_FILLED = 1;
    public static final int BOX_BACKGROUND_NONE = 0;
    public static final int BOX_BACKGROUND_OUTLINE = 2;
    private static final int DEF_STYLE_RES = C1435R.C1440style.Widget_Design_TextInputLayout;
    public static final int END_ICON_CLEAR_TEXT = 2;
    public static final int END_ICON_CUSTOM = -1;
    public static final int END_ICON_DROPDOWN_MENU = 3;
    public static final int END_ICON_NONE = 0;
    public static final int END_ICON_PASSWORD_TOGGLE = 1;
    private static final int INVALID_MAX_LENGTH = -1;
    private static final int LABEL_SCALE_ANIMATION_DURATION = 167;
    private static final String LOG_TAG = "TextInputLayout";
    private ValueAnimator animator;
    private MaterialShapeDrawable boxBackground;
    @ColorInt
    private int boxBackgroundColor;
    private int boxBackgroundMode;
    private final int boxCollapsedPaddingTopPx;
    private final int boxLabelCutoutPaddingPx;
    @ColorInt
    private int boxStrokeColor;
    private final int boxStrokeWidthDefaultPx;
    private final int boxStrokeWidthFocusedPx;
    private int boxStrokeWidthPx;
    private MaterialShapeDrawable boxUnderline;
    final CollapsingTextHelper collapsingTextHelper;
    private final ShapeAppearanceModel cornerAdjustedShapeAppearanceModel;
    boolean counterEnabled;
    private int counterMaxLength;
    private int counterOverflowTextAppearance;
    @Nullable
    private ColorStateList counterOverflowTextColor;
    private boolean counterOverflowed;
    private int counterTextAppearance;
    @Nullable
    private ColorStateList counterTextColor;
    private TextView counterView;
    @ColorInt
    private int defaultFilledBackgroundColor;
    private ColorStateList defaultHintTextColor;
    @ColorInt
    private final int defaultStrokeColor;
    @ColorInt
    private final int disabledColor;
    @ColorInt
    private final int disabledFilledBackgroundColor;
    EditText editText;
    private final LinkedHashSet<OnEditTextAttachedListener> editTextAttachedListeners;
    private final LinkedHashSet<OnEndIconChangedListener> endIconChangedListeners;
    private final SparseArray<EndIconDelegate> endIconDelegates;
    private Drawable endIconDummyDrawable;
    private int endIconMode;
    private ColorStateList endIconTintList;
    private Mode endIconTintMode;
    private final CheckableImageButton endIconView;
    @ColorInt
    private int focusedStrokeColor;
    private ColorStateList focusedTextColor;
    private boolean hasEndIconTintList;
    private boolean hasEndIconTintMode;
    private boolean hasStartIconTintList;
    private boolean hasStartIconTintMode;
    private CharSequence hint;
    private boolean hintAnimationEnabled;
    private boolean hintEnabled;
    private boolean hintExpanded;
    @ColorInt
    private final int hoveredFilledBackgroundColor;
    @ColorInt
    private final int hoveredStrokeColor;
    private boolean inDrawableStateChanged;
    private final IndicatorViewController indicatorViewController;
    private final FrameLayout inputFrame;
    private boolean isProvidingHint;
    private Drawable originalEditTextEndDrawable;
    private CharSequence originalHint;
    /* access modifiers changed from: private */
    public boolean restoringSavedState;
    private final ShapeAppearanceModel shapeAppearanceModel;
    private Drawable startIconDummyDrawable;
    private ColorStateList startIconTintList;
    private Mode startIconTintMode;
    private final CheckableImageButton startIconView;
    private final Rect tmpBoundsRect;
    private final Rect tmpRect;
    private final RectF tmpRectF;
    private Typeface typeface;

    public static class AccessibilityDelegate extends AccessibilityDelegateCompat {
        private final TextInputLayout layout;

        public AccessibilityDelegate(TextInputLayout textInputLayout) {
            this.layout = textInputLayout;
        }

        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
            EditText editText = this.layout.getEditText();
            Editable text = editText != null ? editText.getText() : null;
            CharSequence hint = this.layout.getHint();
            CharSequence error = this.layout.getError();
            CharSequence counterOverflowDescription = this.layout.getCounterOverflowDescription();
            boolean z = !TextUtils.isEmpty(text);
            boolean z2 = !TextUtils.isEmpty(hint);
            boolean z3 = !TextUtils.isEmpty(error);
            boolean z4 = false;
            boolean z5 = z3 || !TextUtils.isEmpty(counterOverflowDescription);
            if (z) {
                accessibilityNodeInfoCompat.setText(text);
            } else if (z2) {
                accessibilityNodeInfoCompat.setText(hint);
            }
            if (z2) {
                accessibilityNodeInfoCompat.setHintText(hint);
                if (!z && z2) {
                    z4 = true;
                }
                accessibilityNodeInfoCompat.setShowingHintText(z4);
            }
            if (z5) {
                if (!z3) {
                    error = counterOverflowDescription;
                }
                accessibilityNodeInfoCompat.setError(error);
                accessibilityNodeInfoCompat.setContentInvalid(true);
            }
        }

        public void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            super.onPopulateAccessibilityEvent(view, accessibilityEvent);
            EditText editText = this.layout.getEditText();
            CharSequence text = editText != null ? editText.getText() : null;
            if (TextUtils.isEmpty(text)) {
                text = this.layout.getHint();
            }
            if (!TextUtils.isEmpty(text)) {
                accessibilityEvent.getText().add(text);
            }
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface BoxBackgroundMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface EndIconMode {
    }

    public interface OnEditTextAttachedListener {
        void onEditTextAttached(EditText editText);
    }

    public interface OnEndIconChangedListener {
        void onEndIconChanged(int i);
    }

    static class SavedState extends AbsSavedState {
        public static final Creator<SavedState> CREATOR = new ClassLoaderCreator<SavedState>() {
            public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel, null);
            }

            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        CharSequence error;
        boolean isEndIconChecked;

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.error = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            boolean z = true;
            if (parcel.readInt() != 1) {
                z = false;
            }
            this.isEndIconChecked = z;
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            TextUtils.writeToParcel(this.error, parcel, i);
            parcel.writeInt(this.isEndIconChecked ? 1 : 0);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("TextInputLayout.SavedState{");
            sb.append(Integer.toHexString(System.identityHashCode(this)));
            sb.append(" error=");
            sb.append(this.error);
            sb.append("}");
            return sb.toString();
        }
    }

    public TextInputLayout(Context context) {
        this(context, null);
    }

    public TextInputLayout(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, C1435R.attr.textInputStyle);
    }

    public TextInputLayout(Context context, @Nullable AttributeSet attributeSet, int i) {
        AttributeSet attributeSet2 = attributeSet;
        int i2 = i;
        super(ThemeEnforcement.createThemedContext(context, attributeSet2, i2, DEF_STYLE_RES), attributeSet2, i2);
        this.indicatorViewController = new IndicatorViewController(this);
        this.tmpRect = new Rect();
        this.tmpBoundsRect = new Rect();
        this.tmpRectF = new RectF();
        this.editTextAttachedListeners = new LinkedHashSet<>();
        this.endIconMode = 0;
        this.endIconDelegates = new SparseArray<>();
        this.endIconChangedListeners = new LinkedHashSet<>();
        this.collapsingTextHelper = new CollapsingTextHelper(this);
        Context context2 = getContext();
        setOrientation(1);
        setWillNotDraw(false);
        setAddStatesFromChildren(true);
        this.inputFrame = new FrameLayout(context2);
        this.inputFrame.setAddStatesFromChildren(true);
        addView(this.inputFrame);
        this.collapsingTextHelper.setTextSizeInterpolator(AnimationUtils.LINEAR_INTERPOLATOR);
        this.collapsingTextHelper.setPositionInterpolator(AnimationUtils.LINEAR_INTERPOLATOR);
        this.collapsingTextHelper.setCollapsedTextGravity(8388659);
        TintTypedArray obtainTintedStyledAttributes = ThemeEnforcement.obtainTintedStyledAttributes(context2, attributeSet, C1435R.styleable.TextInputLayout, i, DEF_STYLE_RES, C1435R.styleable.TextInputLayout_counterTextAppearance, C1435R.styleable.TextInputLayout_counterOverflowTextAppearance, C1435R.styleable.TextInputLayout_errorTextAppearance, C1435R.styleable.TextInputLayout_helperTextTextAppearance, C1435R.styleable.TextInputLayout_hintTextAppearance);
        this.hintEnabled = obtainTintedStyledAttributes.getBoolean(C1435R.styleable.TextInputLayout_hintEnabled, true);
        setHint(obtainTintedStyledAttributes.getText(C1435R.styleable.TextInputLayout_android_hint));
        this.hintAnimationEnabled = obtainTintedStyledAttributes.getBoolean(C1435R.styleable.TextInputLayout_hintAnimationEnabled, true);
        this.shapeAppearanceModel = new ShapeAppearanceModel(context2, attributeSet2, i2, DEF_STYLE_RES);
        this.cornerAdjustedShapeAppearanceModel = new ShapeAppearanceModel(this.shapeAppearanceModel);
        this.boxLabelCutoutPaddingPx = context2.getResources().getDimensionPixelOffset(C1435R.dimen.mtrl_textinput_box_label_cutout_padding);
        this.boxCollapsedPaddingTopPx = obtainTintedStyledAttributes.getDimensionPixelOffset(C1435R.styleable.TextInputLayout_boxCollapsedPaddingTop, 0);
        this.boxStrokeWidthDefaultPx = context2.getResources().getDimensionPixelSize(C1435R.dimen.mtrl_textinput_box_stroke_width_default);
        this.boxStrokeWidthFocusedPx = context2.getResources().getDimensionPixelSize(C1435R.dimen.mtrl_textinput_box_stroke_width_focused);
        this.boxStrokeWidthPx = this.boxStrokeWidthDefaultPx;
        float dimension = obtainTintedStyledAttributes.getDimension(C1435R.styleable.TextInputLayout_boxCornerRadiusTopStart, -1.0f);
        float dimension2 = obtainTintedStyledAttributes.getDimension(C1435R.styleable.TextInputLayout_boxCornerRadiusTopEnd, -1.0f);
        float dimension3 = obtainTintedStyledAttributes.getDimension(C1435R.styleable.TextInputLayout_boxCornerRadiusBottomEnd, -1.0f);
        float dimension4 = obtainTintedStyledAttributes.getDimension(C1435R.styleable.TextInputLayout_boxCornerRadiusBottomStart, -1.0f);
        if (dimension >= 0.0f) {
            this.shapeAppearanceModel.getTopLeftCorner().setCornerSize(dimension);
        }
        if (dimension2 >= 0.0f) {
            this.shapeAppearanceModel.getTopRightCorner().setCornerSize(dimension2);
        }
        if (dimension3 >= 0.0f) {
            this.shapeAppearanceModel.getBottomRightCorner().setCornerSize(dimension3);
        }
        if (dimension4 >= 0.0f) {
            this.shapeAppearanceModel.getBottomLeftCorner().setCornerSize(dimension4);
        }
        adjustCornerSizeForStrokeWidth();
        ColorStateList colorStateList = MaterialResources.getColorStateList(context2, obtainTintedStyledAttributes, C1435R.styleable.TextInputLayout_boxBackgroundColor);
        if (colorStateList != null) {
            this.defaultFilledBackgroundColor = colorStateList.getDefaultColor();
            this.boxBackgroundColor = this.defaultFilledBackgroundColor;
            if (colorStateList.isStateful()) {
                this.disabledFilledBackgroundColor = colorStateList.getColorForState(new int[]{-16842910}, -1);
                this.hoveredFilledBackgroundColor = colorStateList.getColorForState(new int[]{16843623}, -1);
            } else {
                ColorStateList colorStateList2 = AppCompatResources.getColorStateList(context2, C1435R.C1436color.mtrl_filled_background_color);
                this.disabledFilledBackgroundColor = colorStateList2.getColorForState(new int[]{-16842910}, -1);
                this.hoveredFilledBackgroundColor = colorStateList2.getColorForState(new int[]{16843623}, -1);
            }
        } else {
            this.boxBackgroundColor = 0;
            this.defaultFilledBackgroundColor = 0;
            this.disabledFilledBackgroundColor = 0;
            this.hoveredFilledBackgroundColor = 0;
        }
        if (obtainTintedStyledAttributes.hasValue(C1435R.styleable.TextInputLayout_android_textColorHint)) {
            ColorStateList colorStateList3 = obtainTintedStyledAttributes.getColorStateList(C1435R.styleable.TextInputLayout_android_textColorHint);
            this.focusedTextColor = colorStateList3;
            this.defaultHintTextColor = colorStateList3;
        }
        ColorStateList colorStateList4 = MaterialResources.getColorStateList(context2, obtainTintedStyledAttributes, C1435R.styleable.TextInputLayout_boxStrokeColor);
        if (colorStateList4 == null || !colorStateList4.isStateful()) {
            this.focusedStrokeColor = obtainTintedStyledAttributes.getColor(C1435R.styleable.TextInputLayout_boxStrokeColor, 0);
            this.defaultStrokeColor = ContextCompat.getColor(context2, C1435R.C1436color.mtrl_textinput_default_box_stroke_color);
            this.disabledColor = ContextCompat.getColor(context2, C1435R.C1436color.mtrl_textinput_disabled_color);
            this.hoveredStrokeColor = ContextCompat.getColor(context2, C1435R.C1436color.mtrl_textinput_hovered_box_stroke_color);
        } else {
            this.defaultStrokeColor = colorStateList4.getDefaultColor();
            this.disabledColor = colorStateList4.getColorForState(new int[]{-16842910}, -1);
            this.hoveredStrokeColor = colorStateList4.getColorForState(new int[]{16843623}, -1);
            this.focusedStrokeColor = colorStateList4.getColorForState(new int[]{16842908}, -1);
        }
        if (obtainTintedStyledAttributes.getResourceId(C1435R.styleable.TextInputLayout_hintTextAppearance, -1) != -1) {
            setHintTextAppearance(obtainTintedStyledAttributes.getResourceId(C1435R.styleable.TextInputLayout_hintTextAppearance, 0));
        }
        int resourceId = obtainTintedStyledAttributes.getResourceId(C1435R.styleable.TextInputLayout_errorTextAppearance, 0);
        boolean z = obtainTintedStyledAttributes.getBoolean(C1435R.styleable.TextInputLayout_errorEnabled, false);
        int resourceId2 = obtainTintedStyledAttributes.getResourceId(C1435R.styleable.TextInputLayout_helperTextTextAppearance, 0);
        boolean z2 = obtainTintedStyledAttributes.getBoolean(C1435R.styleable.TextInputLayout_helperTextEnabled, false);
        CharSequence text = obtainTintedStyledAttributes.getText(C1435R.styleable.TextInputLayout_helperText);
        boolean z3 = obtainTintedStyledAttributes.getBoolean(C1435R.styleable.TextInputLayout_counterEnabled, false);
        setCounterMaxLength(obtainTintedStyledAttributes.getInt(C1435R.styleable.TextInputLayout_counterMaxLength, -1));
        this.counterTextAppearance = obtainTintedStyledAttributes.getResourceId(C1435R.styleable.TextInputLayout_counterTextAppearance, 0);
        this.counterOverflowTextAppearance = obtainTintedStyledAttributes.getResourceId(C1435R.styleable.TextInputLayout_counterOverflowTextAppearance, 0);
        this.startIconView = (CheckableImageButton) LayoutInflater.from(getContext()).inflate(C1435R.layout.design_text_input_start_icon, this.inputFrame, false);
        this.inputFrame.addView(this.startIconView);
        this.startIconView.setVisibility(8);
        setStartIconOnClickListener(null);
        if (obtainTintedStyledAttributes.hasValue(C1435R.styleable.TextInputLayout_startIconDrawable)) {
            setStartIconDrawable(obtainTintedStyledAttributes.getDrawable(C1435R.styleable.TextInputLayout_startIconDrawable));
            if (obtainTintedStyledAttributes.hasValue(C1435R.styleable.TextInputLayout_startIconContentDescription)) {
                setStartIconContentDescription(obtainTintedStyledAttributes.getText(C1435R.styleable.TextInputLayout_startIconContentDescription));
            }
        }
        if (obtainTintedStyledAttributes.hasValue(C1435R.styleable.TextInputLayout_startIconTint)) {
            setStartIconTintList(MaterialResources.getColorStateList(context2, obtainTintedStyledAttributes, C1435R.styleable.TextInputLayout_startIconTint));
        }
        if (obtainTintedStyledAttributes.hasValue(C1435R.styleable.TextInputLayout_startIconTintMode)) {
            setStartIconTintMode(ViewUtils.parseTintMode(obtainTintedStyledAttributes.getInt(C1435R.styleable.TextInputLayout_startIconTintMode, -1), null));
        }
        setHelperTextEnabled(z2);
        setHelperText(text);
        setHelperTextTextAppearance(resourceId2);
        setErrorEnabled(z);
        setErrorTextAppearance(resourceId);
        setCounterTextAppearance(this.counterTextAppearance);
        setCounterOverflowTextAppearance(this.counterOverflowTextAppearance);
        if (obtainTintedStyledAttributes.hasValue(C1435R.styleable.TextInputLayout_errorTextColor)) {
            setErrorTextColor(obtainTintedStyledAttributes.getColorStateList(C1435R.styleable.TextInputLayout_errorTextColor));
        }
        if (obtainTintedStyledAttributes.hasValue(C1435R.styleable.TextInputLayout_helperTextTextColor)) {
            setHelperTextColor(obtainTintedStyledAttributes.getColorStateList(C1435R.styleable.TextInputLayout_helperTextTextColor));
        }
        if (obtainTintedStyledAttributes.hasValue(C1435R.styleable.TextInputLayout_hintTextColor)) {
            setHintTextColor(obtainTintedStyledAttributes.getColorStateList(C1435R.styleable.TextInputLayout_hintTextColor));
        }
        if (obtainTintedStyledAttributes.hasValue(C1435R.styleable.TextInputLayout_counterTextColor)) {
            setCounterTextColor(obtainTintedStyledAttributes.getColorStateList(C1435R.styleable.TextInputLayout_counterTextColor));
        }
        if (obtainTintedStyledAttributes.hasValue(C1435R.styleable.TextInputLayout_counterOverflowTextColor)) {
            setCounterOverflowTextColor(obtainTintedStyledAttributes.getColorStateList(C1435R.styleable.TextInputLayout_counterOverflowTextColor));
        }
        setCounterEnabled(z3);
        setBoxBackgroundMode(obtainTintedStyledAttributes.getInt(C1435R.styleable.TextInputLayout_boxBackgroundMode, 0));
        this.endIconView = (CheckableImageButton) LayoutInflater.from(getContext()).inflate(C1435R.layout.design_text_input_end_icon, this.inputFrame, false);
        this.inputFrame.addView(this.endIconView);
        this.endIconView.setVisibility(8);
        this.endIconDelegates.append(-1, new CustomEndIconDelegate(this));
        this.endIconDelegates.append(0, new NoEndIconDelegate(this));
        this.endIconDelegates.append(1, new PasswordToggleEndIconDelegate(this));
        this.endIconDelegates.append(2, new ClearTextEndIconDelegate(this));
        this.endIconDelegates.append(3, new DropdownMenuEndIconDelegate(this));
        if (obtainTintedStyledAttributes.hasValue(C1435R.styleable.TextInputLayout_endIconMode)) {
            setEndIconMode(obtainTintedStyledAttributes.getInt(C1435R.styleable.TextInputLayout_endIconMode, 0));
            if (obtainTintedStyledAttributes.hasValue(C1435R.styleable.TextInputLayout_endIconDrawable)) {
                setEndIconDrawable(obtainTintedStyledAttributes.getDrawable(C1435R.styleable.TextInputLayout_endIconDrawable));
            }
            if (obtainTintedStyledAttributes.hasValue(C1435R.styleable.TextInputLayout_endIconContentDescription)) {
                setEndIconContentDescription(obtainTintedStyledAttributes.getText(C1435R.styleable.TextInputLayout_endIconContentDescription));
            }
        } else if (obtainTintedStyledAttributes.hasValue(C1435R.styleable.TextInputLayout_passwordToggleEnabled)) {
            setEndIconMode(1);
            setEndIconDrawable(obtainTintedStyledAttributes.getDrawable(C1435R.styleable.TextInputLayout_passwordToggleDrawable));
            setEndIconContentDescription(obtainTintedStyledAttributes.getText(C1435R.styleable.TextInputLayout_passwordToggleContentDescription));
            if (obtainTintedStyledAttributes.hasValue(C1435R.styleable.TextInputLayout_passwordToggleTint)) {
                setEndIconTintList(MaterialResources.getColorStateList(context2, obtainTintedStyledAttributes, C1435R.styleable.TextInputLayout_passwordToggleTint));
            }
            if (obtainTintedStyledAttributes.hasValue(C1435R.styleable.TextInputLayout_passwordToggleTintMode)) {
                setEndIconTintMode(ViewUtils.parseTintMode(obtainTintedStyledAttributes.getInt(C1435R.styleable.TextInputLayout_passwordToggleTintMode, -1), null));
            }
        }
        if (!obtainTintedStyledAttributes.hasValue(C1435R.styleable.TextInputLayout_passwordToggleEnabled)) {
            if (obtainTintedStyledAttributes.hasValue(C1435R.styleable.TextInputLayout_endIconTint)) {
                setEndIconTintList(MaterialResources.getColorStateList(context2, obtainTintedStyledAttributes, C1435R.styleable.TextInputLayout_endIconTint));
            }
            if (obtainTintedStyledAttributes.hasValue(C1435R.styleable.TextInputLayout_endIconTintMode)) {
                setEndIconTintMode(ViewUtils.parseTintMode(obtainTintedStyledAttributes.getInt(C1435R.styleable.TextInputLayout_endIconTintMode, -1), null));
            }
        }
        obtainTintedStyledAttributes.recycle();
        ViewCompat.setImportantForAccessibility(this, 2);
    }

    public void addView(View view, int i, LayoutParams layoutParams) {
        if (view instanceof EditText) {
            FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(layoutParams);
            layoutParams2.gravity = (layoutParams2.gravity & -113) | 16;
            this.inputFrame.addView(view, layoutParams2);
            this.inputFrame.setLayoutParams(layoutParams);
            updateInputLayoutMargins();
            setEditText((EditText) view);
            return;
        }
        super.addView(view, i, layoutParams);
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    public MaterialShapeDrawable getBoxBackground() {
        int i = this.boxBackgroundMode;
        if (i == 1 || i == 2) {
            return this.boxBackground;
        }
        throw new IllegalStateException();
    }

    public void setBoxBackgroundMode(int i) {
        if (i != this.boxBackgroundMode) {
            this.boxBackgroundMode = i;
            if (this.editText != null) {
                onApplyBoxBackgroundMode();
            }
        }
    }

    public int getBoxBackgroundMode() {
        return this.boxBackgroundMode;
    }

    private void onApplyBoxBackgroundMode() {
        assignBoxBackgroundByMode();
        setEditTextBoxBackground();
        updateTextInputBoxState();
        if (this.boxBackgroundMode != 0) {
            updateInputLayoutMargins();
        }
    }

    private void assignBoxBackgroundByMode() {
        int i = this.boxBackgroundMode;
        if (i == 0) {
            this.boxBackground = null;
            this.boxUnderline = null;
        } else if (i == 1) {
            this.boxBackground = new MaterialShapeDrawable(this.shapeAppearanceModel);
            this.boxUnderline = new MaterialShapeDrawable();
        } else if (i == 2) {
            if (!this.hintEnabled || (this.boxBackground instanceof CutoutDrawable)) {
                this.boxBackground = new MaterialShapeDrawable(this.shapeAppearanceModel);
            } else {
                this.boxBackground = new CutoutDrawable(this.shapeAppearanceModel);
            }
            this.boxUnderline = null;
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(this.boxBackgroundMode);
            sb.append(" is illegal; only @BoxBackgroundMode constants are supported.");
            throw new IllegalArgumentException(sb.toString());
        }
    }

    private void setEditTextBoxBackground() {
        if (shouldUseEditTextBackgroundForBoxBackground()) {
            ViewCompat.setBackground(this.editText, this.boxBackground);
        }
    }

    private boolean shouldUseEditTextBackgroundForBoxBackground() {
        EditText editText2 = this.editText;
        return (editText2 == null || this.boxBackground == null || editText2.getBackground() != null || this.boxBackgroundMode == 0) ? false : true;
    }

    public void setBoxStrokeColor(@ColorInt int i) {
        if (this.focusedStrokeColor != i) {
            this.focusedStrokeColor = i;
            updateTextInputBoxState();
        }
    }

    public int getBoxStrokeColor() {
        return this.focusedStrokeColor;
    }

    public void setBoxBackgroundColorResource(@ColorRes int i) {
        setBoxBackgroundColor(ContextCompat.getColor(getContext(), i));
    }

    public void setBoxBackgroundColor(@ColorInt int i) {
        if (this.boxBackgroundColor != i) {
            this.boxBackgroundColor = i;
            this.defaultFilledBackgroundColor = i;
            applyBoxAttributes();
        }
    }

    public int getBoxBackgroundColor() {
        return this.boxBackgroundColor;
    }

    public void setBoxCornerRadiiResources(@DimenRes int i, @DimenRes int i2, @DimenRes int i3, @DimenRes int i4) {
        setBoxCornerRadii(getContext().getResources().getDimension(i), getContext().getResources().getDimension(i2), getContext().getResources().getDimension(i4), getContext().getResources().getDimension(i3));
    }

    public void setBoxCornerRadii(float f, float f2, float f3, float f4) {
        if (this.shapeAppearanceModel.getTopLeftCorner().getCornerSize() != f || this.shapeAppearanceModel.getTopRightCorner().getCornerSize() != f2 || this.shapeAppearanceModel.getBottomRightCorner().getCornerSize() != f4 || this.shapeAppearanceModel.getBottomLeftCorner().getCornerSize() != f3) {
            this.shapeAppearanceModel.getTopLeftCorner().setCornerSize(f);
            this.shapeAppearanceModel.getTopRightCorner().setCornerSize(f2);
            this.shapeAppearanceModel.getBottomRightCorner().setCornerSize(f4);
            this.shapeAppearanceModel.getBottomLeftCorner().setCornerSize(f3);
            applyBoxAttributes();
        }
    }

    public float getBoxCornerRadiusTopStart() {
        return this.shapeAppearanceModel.getTopLeftCorner().getCornerSize();
    }

    public float getBoxCornerRadiusTopEnd() {
        return this.shapeAppearanceModel.getTopRightCorner().getCornerSize();
    }

    public float getBoxCornerRadiusBottomEnd() {
        return this.shapeAppearanceModel.getBottomLeftCorner().getCornerSize();
    }

    public float getBoxCornerRadiusBottomStart() {
        return this.shapeAppearanceModel.getBottomRightCorner().getCornerSize();
    }

    private void adjustCornerSizeForStrokeWidth() {
        float f = this.boxBackgroundMode == 2 ? ((float) this.boxStrokeWidthPx) / 2.0f : 0.0f;
        if (f > 0.0f) {
            this.cornerAdjustedShapeAppearanceModel.getTopLeftCorner().setCornerSize(this.shapeAppearanceModel.getTopLeftCorner().getCornerSize() + f);
            this.cornerAdjustedShapeAppearanceModel.getTopRightCorner().setCornerSize(this.shapeAppearanceModel.getTopRightCorner().getCornerSize() + f);
            this.cornerAdjustedShapeAppearanceModel.getBottomRightCorner().setCornerSize(this.shapeAppearanceModel.getBottomRightCorner().getCornerSize() + f);
            this.cornerAdjustedShapeAppearanceModel.getBottomLeftCorner().setCornerSize(this.shapeAppearanceModel.getBottomLeftCorner().getCornerSize() + f);
            ensureCornerAdjustedShapeAppearanceModel();
        }
    }

    private void ensureCornerAdjustedShapeAppearanceModel() {
        if (this.boxBackgroundMode != 0) {
            getBoxBackground().setShapeAppearanceModel(this.cornerAdjustedShapeAppearanceModel);
        }
    }

    public void setTypeface(@Nullable Typeface typeface2) {
        if (typeface2 != this.typeface) {
            this.typeface = typeface2;
            this.collapsingTextHelper.setTypefaces(typeface2);
            this.indicatorViewController.setTypefaces(typeface2);
            TextView textView = this.counterView;
            if (textView != null) {
                textView.setTypeface(typeface2);
            }
        }
    }

    @Nullable
    public Typeface getTypeface() {
        return this.typeface;
    }

    public void dispatchProvideAutofillStructure(ViewStructure viewStructure, int i) {
        if (this.originalHint != null) {
            EditText editText2 = this.editText;
            if (editText2 != null) {
                boolean z = this.isProvidingHint;
                this.isProvidingHint = false;
                CharSequence hint2 = editText2.getHint();
                this.editText.setHint(this.originalHint);
                try {
                    super.dispatchProvideAutofillStructure(viewStructure, i);
                    return;
                } finally {
                    this.editText.setHint(hint2);
                    this.isProvidingHint = z;
                }
            }
        }
        super.dispatchProvideAutofillStructure(viewStructure, i);
    }

    private void setEditText(EditText editText2) {
        if (this.editText == null) {
            if (this.endIconMode != 3 && !(editText2 instanceof TextInputEditText)) {
                Log.i(LOG_TAG, "EditText added is not a TextInputEditText. Please switch to using that class instead.");
            }
            this.editText = editText2;
            onApplyBoxBackgroundMode();
            setTextInputAccessibilityDelegate(new AccessibilityDelegate(this));
            this.collapsingTextHelper.setTypefaces(this.editText.getTypeface());
            this.collapsingTextHelper.setExpandedTextSize(this.editText.getTextSize());
            int gravity = this.editText.getGravity();
            this.collapsingTextHelper.setCollapsedTextGravity((gravity & -113) | 48);
            this.collapsingTextHelper.setExpandedTextGravity(gravity);
            this.editText.addTextChangedListener(new TextWatcher() {
                public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                }

                public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                }

                public void afterTextChanged(Editable editable) {
                    TextInputLayout textInputLayout = TextInputLayout.this;
                    textInputLayout.updateLabelState(!textInputLayout.restoringSavedState);
                    if (TextInputLayout.this.counterEnabled) {
                        TextInputLayout.this.updateCounter(editable.length());
                    }
                }
            });
            if (this.defaultHintTextColor == null) {
                this.defaultHintTextColor = this.editText.getHintTextColors();
            }
            if (this.hintEnabled) {
                if (TextUtils.isEmpty(this.hint)) {
                    this.originalHint = this.editText.getHint();
                    setHint(this.originalHint);
                    this.editText.setHint(null);
                }
                this.isProvidingHint = true;
            }
            if (this.counterView != null) {
                updateCounter(this.editText.getText().length());
            }
            updateEditTextBackground();
            this.indicatorViewController.adjustIndicatorPadding();
            updateIconViewOnEditTextAttached(this.startIconView);
            updateIconViewOnEditTextAttached(this.endIconView);
            dispatchOnEditTextAttached();
            updateLabelState(false, true);
            return;
        }
        throw new IllegalArgumentException("We already have an EditText, can only have one");
    }

    private void updateInputLayoutMargins() {
        if (this.boxBackgroundMode != 1) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.inputFrame.getLayoutParams();
            int calculateLabelMarginTop = calculateLabelMarginTop();
            if (calculateLabelMarginTop != layoutParams.topMargin) {
                layoutParams.topMargin = calculateLabelMarginTop;
                this.inputFrame.requestLayout();
            }
        }
    }

    public int getBaseline() {
        EditText editText2 = this.editText;
        if (editText2 != null) {
            return editText2.getBaseline() + getPaddingTop() + calculateLabelMarginTop();
        }
        return super.getBaseline();
    }

    /* access modifiers changed from: 0000 */
    public void updateLabelState(boolean z) {
        updateLabelState(z, false);
    }

    private void updateLabelState(boolean z, boolean z2) {
        boolean isEnabled = isEnabled();
        EditText editText2 = this.editText;
        boolean z3 = true;
        boolean z4 = editText2 != null && !TextUtils.isEmpty(editText2.getText());
        EditText editText3 = this.editText;
        if (editText3 == null || !editText3.hasFocus()) {
            z3 = false;
        }
        boolean errorShouldBeShown = this.indicatorViewController.errorShouldBeShown();
        ColorStateList colorStateList = this.defaultHintTextColor;
        if (colorStateList != null) {
            this.collapsingTextHelper.setCollapsedTextColor(colorStateList);
            this.collapsingTextHelper.setExpandedTextColor(this.defaultHintTextColor);
        }
        if (!isEnabled) {
            this.collapsingTextHelper.setCollapsedTextColor(ColorStateList.valueOf(this.disabledColor));
            this.collapsingTextHelper.setExpandedTextColor(ColorStateList.valueOf(this.disabledColor));
        } else if (errorShouldBeShown) {
            this.collapsingTextHelper.setCollapsedTextColor(this.indicatorViewController.getErrorViewTextColors());
        } else {
            if (this.counterOverflowed) {
                TextView textView = this.counterView;
                if (textView != null) {
                    this.collapsingTextHelper.setCollapsedTextColor(textView.getTextColors());
                }
            }
            if (z3) {
                ColorStateList colorStateList2 = this.focusedTextColor;
                if (colorStateList2 != null) {
                    this.collapsingTextHelper.setCollapsedTextColor(colorStateList2);
                }
            }
        }
        if (z4 || (isEnabled() && (z3 || errorShouldBeShown))) {
            if (z2 || this.hintExpanded) {
                collapseHint(z);
            }
        } else if (z2 || !this.hintExpanded) {
            expandHint(z);
        }
    }

    @Nullable
    public EditText getEditText() {
        return this.editText;
    }

    public void setHint(@Nullable CharSequence charSequence) {
        if (this.hintEnabled) {
            setHintInternal(charSequence);
            sendAccessibilityEvent(2048);
        }
    }

    private void setHintInternal(CharSequence charSequence) {
        if (!TextUtils.equals(charSequence, this.hint)) {
            this.hint = charSequence;
            this.collapsingTextHelper.setText(charSequence);
            if (!this.hintExpanded) {
                openCutout();
            }
        }
    }

    @Nullable
    public CharSequence getHint() {
        if (this.hintEnabled) {
            return this.hint;
        }
        return null;
    }

    public void setHintEnabled(boolean z) {
        if (z != this.hintEnabled) {
            this.hintEnabled = z;
            if (!this.hintEnabled) {
                this.isProvidingHint = false;
                if (!TextUtils.isEmpty(this.hint) && TextUtils.isEmpty(this.editText.getHint())) {
                    this.editText.setHint(this.hint);
                }
                setHintInternal(null);
            } else {
                CharSequence hint2 = this.editText.getHint();
                if (!TextUtils.isEmpty(hint2)) {
                    if (TextUtils.isEmpty(this.hint)) {
                        setHint(hint2);
                    }
                    this.editText.setHint(null);
                }
                this.isProvidingHint = true;
            }
            if (this.editText != null) {
                updateInputLayoutMargins();
            }
        }
    }

    public boolean isHintEnabled() {
        return this.hintEnabled;
    }

    /* access modifiers changed from: 0000 */
    public boolean isProvidingHint() {
        return this.isProvidingHint;
    }

    public void setHintTextAppearance(@StyleRes int i) {
        this.collapsingTextHelper.setCollapsedTextAppearance(i);
        this.focusedTextColor = this.collapsingTextHelper.getCollapsedTextColor();
        if (this.editText != null) {
            updateLabelState(false);
            updateInputLayoutMargins();
        }
    }

    public void setHintTextColor(@Nullable ColorStateList colorStateList) {
        if (this.focusedTextColor != colorStateList) {
            if (this.defaultHintTextColor == null) {
                this.collapsingTextHelper.setCollapsedTextColor(colorStateList);
            }
            this.focusedTextColor = colorStateList;
            if (this.editText != null) {
                updateLabelState(false);
            }
        }
    }

    @Nullable
    public ColorStateList getHintTextColor() {
        return this.focusedTextColor;
    }

    public void setDefaultHintTextColor(@Nullable ColorStateList colorStateList) {
        this.defaultHintTextColor = colorStateList;
        this.focusedTextColor = colorStateList;
        if (this.editText != null) {
            updateLabelState(false);
        }
    }

    @Nullable
    public ColorStateList getDefaultHintTextColor() {
        return this.defaultHintTextColor;
    }

    public void setErrorEnabled(boolean z) {
        this.indicatorViewController.setErrorEnabled(z);
    }

    public void setErrorTextAppearance(@StyleRes int i) {
        this.indicatorViewController.setErrorTextAppearance(i);
    }

    public void setErrorTextColor(@Nullable ColorStateList colorStateList) {
        this.indicatorViewController.setErrorViewTextColor(colorStateList);
    }

    @ColorInt
    public int getErrorCurrentTextColors() {
        return this.indicatorViewController.getErrorViewCurrentTextColor();
    }

    public void setHelperTextTextAppearance(@StyleRes int i) {
        this.indicatorViewController.setHelperTextAppearance(i);
    }

    public void setHelperTextColor(@Nullable ColorStateList colorStateList) {
        this.indicatorViewController.setHelperTextViewTextColor(colorStateList);
    }

    public boolean isErrorEnabled() {
        return this.indicatorViewController.isErrorEnabled();
    }

    public void setHelperTextEnabled(boolean z) {
        this.indicatorViewController.setHelperTextEnabled(z);
    }

    public void setHelperText(@Nullable CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            if (!isHelperTextEnabled()) {
                setHelperTextEnabled(true);
            }
            this.indicatorViewController.showHelper(charSequence);
        } else if (isHelperTextEnabled()) {
            setHelperTextEnabled(false);
        }
    }

    public boolean isHelperTextEnabled() {
        return this.indicatorViewController.isHelperTextEnabled();
    }

    @ColorInt
    public int getHelperTextCurrentTextColor() {
        return this.indicatorViewController.getHelperTextViewCurrentTextColor();
    }

    public void setError(@Nullable CharSequence charSequence) {
        if (!this.indicatorViewController.isErrorEnabled()) {
            if (!TextUtils.isEmpty(charSequence)) {
                setErrorEnabled(true);
            } else {
                return;
            }
        }
        if (!TextUtils.isEmpty(charSequence)) {
            this.indicatorViewController.showError(charSequence);
        } else {
            this.indicatorViewController.hideError();
        }
    }

    public void setCounterEnabled(boolean z) {
        if (this.counterEnabled != z) {
            if (z) {
                this.counterView = new AppCompatTextView(getContext());
                this.counterView.setId(C1435R.C1438id.textinput_counter);
                Typeface typeface2 = this.typeface;
                if (typeface2 != null) {
                    this.counterView.setTypeface(typeface2);
                }
                this.counterView.setMaxLines(1);
                this.indicatorViewController.addIndicator(this.counterView, 2);
                updateCounterTextAppearanceAndColor();
                updateCounter();
            } else {
                this.indicatorViewController.removeIndicator(this.counterView, 2);
                this.counterView = null;
            }
            this.counterEnabled = z;
        }
    }

    public void setCounterTextAppearance(int i) {
        if (this.counterTextAppearance != i) {
            this.counterTextAppearance = i;
            updateCounterTextAppearanceAndColor();
        }
    }

    public void setCounterTextColor(@Nullable ColorStateList colorStateList) {
        if (this.counterTextColor != colorStateList) {
            this.counterTextColor = colorStateList;
            updateCounterTextAppearanceAndColor();
        }
    }

    @Nullable
    public ColorStateList getCounterTextColor() {
        return this.counterTextColor;
    }

    public void setCounterOverflowTextAppearance(int i) {
        if (this.counterOverflowTextAppearance != i) {
            this.counterOverflowTextAppearance = i;
            updateCounterTextAppearanceAndColor();
        }
    }

    public void setCounterOverflowTextColor(@Nullable ColorStateList colorStateList) {
        if (this.counterOverflowTextColor != colorStateList) {
            this.counterOverflowTextColor = colorStateList;
            updateCounterTextAppearanceAndColor();
        }
    }

    @Nullable
    public ColorStateList getCounterOverflowTextColor() {
        return this.counterTextColor;
    }

    public boolean isCounterEnabled() {
        return this.counterEnabled;
    }

    public void setCounterMaxLength(int i) {
        if (this.counterMaxLength != i) {
            if (i > 0) {
                this.counterMaxLength = i;
            } else {
                this.counterMaxLength = -1;
            }
            if (this.counterEnabled) {
                updateCounter();
            }
        }
    }

    private void updateCounter() {
        if (this.counterView != null) {
            EditText editText2 = this.editText;
            updateCounter(editText2 == null ? 0 : editText2.getText().length());
        }
    }

    /* access modifiers changed from: 0000 */
    public void updateCounter(int i) {
        boolean z = this.counterOverflowed;
        if (this.counterMaxLength == -1) {
            this.counterView.setText(String.valueOf(i));
            this.counterView.setContentDescription(null);
            this.counterOverflowed = false;
        } else {
            if (ViewCompat.getAccessibilityLiveRegion(this.counterView) == 1) {
                ViewCompat.setAccessibilityLiveRegion(this.counterView, 0);
            }
            this.counterOverflowed = i > this.counterMaxLength;
            updateCounterContentDescription(getContext(), this.counterView, i, this.counterMaxLength, this.counterOverflowed);
            if (z != this.counterOverflowed) {
                updateCounterTextAppearanceAndColor();
                if (this.counterOverflowed) {
                    ViewCompat.setAccessibilityLiveRegion(this.counterView, 1);
                }
            }
            this.counterView.setText(getContext().getString(C1435R.string.character_counter_pattern, new Object[]{Integer.valueOf(i), Integer.valueOf(this.counterMaxLength)}));
        }
        if (this.editText != null && z != this.counterOverflowed) {
            updateLabelState(false);
            updateTextInputBoxState();
            updateEditTextBackground();
        }
    }

    private static void updateCounterContentDescription(Context context, TextView textView, int i, int i2, boolean z) {
        textView.setContentDescription(context.getString(z ? C1435R.string.character_counter_overflowed_content_description : C1435R.string.character_counter_content_description, new Object[]{Integer.valueOf(i), Integer.valueOf(i2)}));
    }

    public void setEnabled(boolean z) {
        recursiveSetEnabled(this, z);
        super.setEnabled(z);
    }

    private static void recursiveSetEnabled(ViewGroup viewGroup, boolean z) {
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            childAt.setEnabled(z);
            if (childAt instanceof ViewGroup) {
                recursiveSetEnabled((ViewGroup) childAt, z);
            }
        }
    }

    public int getCounterMaxLength() {
        return this.counterMaxLength;
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public CharSequence getCounterOverflowDescription() {
        if (this.counterEnabled && this.counterOverflowed) {
            TextView textView = this.counterView;
            if (textView != null) {
                return textView.getContentDescription();
            }
        }
        return null;
    }

    private void updateCounterTextAppearanceAndColor() {
        TextView textView = this.counterView;
        if (textView != null) {
            setTextAppearanceCompatWithErrorFallback(textView, this.counterOverflowed ? this.counterOverflowTextAppearance : this.counterTextAppearance);
            if (!this.counterOverflowed) {
                ColorStateList colorStateList = this.counterTextColor;
                if (colorStateList != null) {
                    this.counterView.setTextColor(colorStateList);
                }
            }
            if (this.counterOverflowed) {
                ColorStateList colorStateList2 = this.counterOverflowTextColor;
                if (colorStateList2 != null) {
                    this.counterView.setTextColor(colorStateList2);
                }
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void setTextAppearanceCompatWithErrorFallback(TextView textView, @StyleRes int i) {
        boolean z = true;
        try {
            TextViewCompat.setTextAppearance(textView, i);
            if (VERSION.SDK_INT < 23 || textView.getTextColors().getDefaultColor() != -65281) {
                z = false;
            }
        } catch (Exception unused) {
        }
        if (z) {
            TextViewCompat.setTextAppearance(textView, C1435R.C1440style.TextAppearance_AppCompat_Caption);
            textView.setTextColor(ContextCompat.getColor(getContext(), C1435R.C1436color.design_error));
        }
    }

    private int calculateLabelMarginTop() {
        float collapsedTextHeight;
        if (!this.hintEnabled) {
            return 0;
        }
        int i = this.boxBackgroundMode;
        if (i == 0 || i == 1) {
            collapsedTextHeight = this.collapsingTextHelper.getCollapsedTextHeight();
        } else if (i != 2) {
            return 0;
        } else {
            collapsedTextHeight = this.collapsingTextHelper.getCollapsedTextHeight() / 2.0f;
        }
        return (int) collapsedTextHeight;
    }

    private Rect calculateCollapsedTextBounds(Rect rect) {
        if (this.editText != null) {
            Rect rect2 = this.tmpBoundsRect;
            rect2.bottom = rect.bottom;
            int i = this.boxBackgroundMode;
            if (i == 1) {
                rect2.left = rect.left + this.editText.getCompoundPaddingLeft();
                rect2.top = rect.top + this.boxCollapsedPaddingTopPx;
                rect2.right = rect.right - this.editText.getCompoundPaddingRight();
                return rect2;
            } else if (i != 2) {
                rect2.left = rect.left + this.editText.getCompoundPaddingLeft();
                rect2.top = getPaddingTop();
                rect2.right = rect.right - this.editText.getCompoundPaddingRight();
                return rect2;
            } else {
                rect2.left = rect.left + this.editText.getPaddingLeft();
                rect2.top = rect.top - calculateLabelMarginTop();
                rect2.right = rect.right - this.editText.getPaddingRight();
                return rect2;
            }
        } else {
            throw new IllegalStateException();
        }
    }

    private Rect calculateExpandedTextBounds(Rect rect) {
        if (this.editText != null) {
            Rect rect2 = this.tmpBoundsRect;
            float expandedTextHeight = this.collapsingTextHelper.getExpandedTextHeight();
            rect2.left = rect.left + this.editText.getCompoundPaddingLeft();
            rect2.top = calculateExpandedLabelTop(rect, expandedTextHeight);
            rect2.right = rect.right - this.editText.getCompoundPaddingRight();
            rect2.bottom = calculateExpandedLabelBottom(rect, rect2, expandedTextHeight);
            return rect2;
        }
        throw new IllegalStateException();
    }

    private int calculateExpandedLabelTop(Rect rect, float f) {
        if (this.boxBackgroundMode == 1) {
            return (int) (((float) rect.centerY()) - (f / 2.0f));
        }
        return rect.top + this.editText.getCompoundPaddingTop();
    }

    private int calculateExpandedLabelBottom(Rect rect, Rect rect2, float f) {
        if (this.boxBackgroundMode == 1) {
            return (int) (((float) rect2.top) + f);
        }
        return rect.bottom - this.editText.getCompoundPaddingBottom();
    }

    private int calculateBoxBackgroundColor() {
        return this.boxBackgroundMode == 1 ? MaterialColors.layer(MaterialColors.getColor((View) this, C1435R.attr.colorSurface, 0), this.boxBackgroundColor) : this.boxBackgroundColor;
    }

    private void applyBoxAttributes() {
        if (this.boxBackground != null) {
            if (canDrawOutlineStroke()) {
                this.boxBackground.setStroke((float) this.boxStrokeWidthPx, this.boxStrokeColor);
            }
            this.boxBackgroundColor = calculateBoxBackgroundColor();
            this.boxBackground.setFillColor(ColorStateList.valueOf(this.boxBackgroundColor));
            if (this.endIconMode == 3) {
                this.editText.getBackground().invalidateSelf();
            }
            applyBoxUnderlineAttributes();
            invalidate();
        }
    }

    private void applyBoxUnderlineAttributes() {
        if (this.boxUnderline != null) {
            if (canDrawStroke()) {
                this.boxUnderline.setFillColor(ColorStateList.valueOf(this.boxStrokeColor));
            }
            invalidate();
        }
    }

    private boolean canDrawOutlineStroke() {
        return this.boxBackgroundMode == 2 && canDrawStroke();
    }

    private boolean canDrawStroke() {
        return this.boxStrokeWidthPx > -1 && this.boxStrokeColor != 0;
    }

    /* access modifiers changed from: 0000 */
    public void updateEditTextBackground() {
        EditText editText2 = this.editText;
        if (editText2 != null && this.boxBackgroundMode == 0) {
            Drawable background = editText2.getBackground();
            if (background != null) {
                if (DrawableUtils.canSafelyMutateDrawable(background)) {
                    background = background.mutate();
                }
                if (this.indicatorViewController.errorShouldBeShown()) {
                    background.setColorFilter(AppCompatDrawableManager.getPorterDuffColorFilter(this.indicatorViewController.getErrorViewCurrentTextColor(), Mode.SRC_IN));
                } else {
                    if (this.counterOverflowed) {
                        TextView textView = this.counterView;
                        if (textView != null) {
                            background.setColorFilter(AppCompatDrawableManager.getPorterDuffColorFilter(textView.getCurrentTextColor(), Mode.SRC_IN));
                        }
                    }
                    DrawableCompat.clearColorFilter(background);
                    this.editText.refreshDrawableState();
                }
            }
        }
    }

    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        if (this.indicatorViewController.errorShouldBeShown()) {
            savedState.error = getError();
        }
        savedState.isEndIconChecked = hasEndIcon() && this.endIconView.isChecked();
        return savedState;
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        setError(savedState.error);
        if (savedState.isEndIconChecked) {
            this.endIconView.performClick();
            this.endIconView.jumpDrawablesToCurrentState();
        }
        requestLayout();
    }

    /* access modifiers changed from: protected */
    public void dispatchRestoreInstanceState(SparseArray<Parcelable> sparseArray) {
        this.restoringSavedState = true;
        super.dispatchRestoreInstanceState(sparseArray);
        this.restoringSavedState = false;
    }

    @Nullable
    public CharSequence getError() {
        if (this.indicatorViewController.isErrorEnabled()) {
            return this.indicatorViewController.getErrorText();
        }
        return null;
    }

    @Nullable
    public CharSequence getHelperText() {
        if (this.indicatorViewController.isHelperTextEnabled()) {
            return this.indicatorViewController.getHelperText();
        }
        return null;
    }

    public boolean isHintAnimationEnabled() {
        return this.hintAnimationEnabled;
    }

    public void setHintAnimationEnabled(boolean z) {
        this.hintAnimationEnabled = z;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        setEditTextHeightAndDummyDrawables();
    }

    private void setEditTextHeightAndDummyDrawables() {
        if (this.editText != null) {
            int max = Math.max(this.endIconView.getMeasuredHeight(), this.startIconView.getMeasuredHeight());
            if (this.editText.getMeasuredHeight() < max) {
                this.editText.setMinimumHeight(max);
                this.editText.post(new Runnable() {
                    public void run() {
                        TextInputLayout.this.editText.requestLayout();
                    }
                });
            }
            updateIconDummyDrawables();
        }
    }

    public void setStartIconDrawable(@DrawableRes int i) {
        setStartIconDrawable(i != 0 ? AppCompatResources.getDrawable(getContext(), i) : null);
    }

    public void setStartIconDrawable(@Nullable Drawable drawable) {
        this.startIconView.setImageDrawable(drawable);
        if (drawable != null) {
            setStartIconVisible(true);
            applyStartIconTint();
            return;
        }
        setStartIconVisible(false);
        setStartIconOnClickListener(null);
        setStartIconContentDescription((CharSequence) null);
    }

    @Nullable
    public Drawable getStartIconDrawable() {
        return this.startIconView.getDrawable();
    }

    public void setStartIconOnClickListener(OnClickListener onClickListener) {
        setIconOnClickListener(this.startIconView, onClickListener);
    }

    public void setStartIconVisible(boolean z) {
        if (isStartIconVisible() != z) {
            this.startIconView.setVisibility(z ? 0 : 8);
            updateIconDummyDrawables();
        }
    }

    public boolean isStartIconVisible() {
        return this.startIconView.getVisibility() == 0;
    }

    public void setStartIconContentDescription(@StringRes int i) {
        setStartIconContentDescription(i != 0 ? getResources().getText(i) : null);
    }

    public void setStartIconContentDescription(@Nullable CharSequence charSequence) {
        if (getStartIconContentDescription() != charSequence) {
            this.startIconView.setContentDescription(charSequence);
        }
    }

    @Nullable
    public CharSequence getStartIconContentDescription() {
        return this.startIconView.getContentDescription();
    }

    public void setStartIconTintList(@Nullable ColorStateList colorStateList) {
        if (this.startIconTintList != colorStateList) {
            this.startIconTintList = colorStateList;
            this.hasStartIconTintList = true;
            applyStartIconTint();
        }
    }

    public void setStartIconTintMode(@Nullable Mode mode) {
        if (this.startIconTintMode != mode) {
            this.startIconTintMode = mode;
            this.hasStartIconTintMode = true;
            applyStartIconTint();
        }
    }

    public void setEndIconMode(int i) {
        int i2 = this.endIconMode;
        this.endIconMode = i;
        setEndIconVisible(i != 0);
        if (getEndIconDelegate().isBoxBackgroundModeSupported(this.boxBackgroundMode)) {
            getEndIconDelegate().initialize();
            applyEndIconTint();
            dispatchOnEndIconChanged(i2);
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("The current box background mode ");
        sb.append(this.boxBackgroundMode);
        sb.append(" is not supported by the end icon mode ");
        sb.append(i);
        throw new IllegalStateException(sb.toString());
    }

    public int getEndIconMode() {
        return this.endIconMode;
    }

    public void setEndIconOnClickListener(@Nullable OnClickListener onClickListener) {
        setIconOnClickListener(this.endIconView, onClickListener);
    }

    public void setEndIconVisible(boolean z) {
        if (isEndIconVisible() != z) {
            this.endIconView.setVisibility(z ? 0 : 4);
            updateIconDummyDrawables();
        }
    }

    public boolean isEndIconVisible() {
        return this.endIconView.getVisibility() == 0;
    }

    public void setEndIconActivated(boolean z) {
        this.endIconView.setActivated(z);
    }

    public void setEndIconDrawable(@DrawableRes int i) {
        setEndIconDrawable(i != 0 ? AppCompatResources.getDrawable(getContext(), i) : null);
    }

    public void setEndIconDrawable(@Nullable Drawable drawable) {
        this.endIconView.setImageDrawable(drawable);
    }

    @Nullable
    public Drawable getEndIconDrawable() {
        return this.endIconView.getDrawable();
    }

    public void setEndIconContentDescription(@StringRes int i) {
        setEndIconContentDescription(i != 0 ? getResources().getText(i) : null);
    }

    public void setEndIconContentDescription(@Nullable CharSequence charSequence) {
        if (getEndIconContentDescription() != charSequence) {
            this.endIconView.setContentDescription(charSequence);
        }
    }

    @Nullable
    public CharSequence getEndIconContentDescription() {
        return this.endIconView.getContentDescription();
    }

    public void setEndIconTintList(@Nullable ColorStateList colorStateList) {
        if (this.endIconTintList != colorStateList) {
            this.endIconTintList = colorStateList;
            this.hasEndIconTintList = true;
            applyEndIconTint();
        }
    }

    public void setEndIconTintMode(@Nullable Mode mode) {
        if (this.endIconTintMode != mode) {
            this.endIconTintMode = mode;
            this.hasEndIconTintMode = true;
            applyEndIconTint();
        }
    }

    public void addOnEndIconChangedListener(OnEndIconChangedListener onEndIconChangedListener) {
        this.endIconChangedListeners.add(onEndIconChangedListener);
    }

    public void removeOnEndIconChangedListener(OnEndIconChangedListener onEndIconChangedListener) {
        this.endIconChangedListeners.remove(onEndIconChangedListener);
    }

    public void clearOnEndIconChangedListeners() {
        this.endIconChangedListeners.clear();
    }

    public void addOnEditTextAttachedListener(OnEditTextAttachedListener onEditTextAttachedListener) {
        this.editTextAttachedListeners.add(onEditTextAttachedListener);
        EditText editText2 = this.editText;
        if (editText2 != null) {
            onEditTextAttachedListener.onEditTextAttached(editText2);
        }
    }

    public void removeOnEditTextAttachedListener(OnEditTextAttachedListener onEditTextAttachedListener) {
        this.editTextAttachedListeners.remove(onEditTextAttachedListener);
    }

    public void clearOnEditTextAttachedListeners() {
        this.editTextAttachedListeners.clear();
    }

    @Deprecated
    public void setPasswordVisibilityToggleDrawable(@DrawableRes int i) {
        setPasswordVisibilityToggleDrawable(i != 0 ? AppCompatResources.getDrawable(getContext(), i) : null);
    }

    @Deprecated
    public void setPasswordVisibilityToggleDrawable(@Nullable Drawable drawable) {
        this.endIconView.setImageDrawable(drawable);
    }

    @Deprecated
    public void setPasswordVisibilityToggleContentDescription(@StringRes int i) {
        setPasswordVisibilityToggleContentDescription(i != 0 ? getResources().getText(i) : null);
    }

    @Deprecated
    public void setPasswordVisibilityToggleContentDescription(@Nullable CharSequence charSequence) {
        this.endIconView.setContentDescription(charSequence);
    }

    @Deprecated
    @Nullable
    public Drawable getPasswordVisibilityToggleDrawable() {
        return this.endIconView.getDrawable();
    }

    @Deprecated
    @Nullable
    public CharSequence getPasswordVisibilityToggleContentDescription() {
        return this.endIconView.getContentDescription();
    }

    @Deprecated
    public boolean isPasswordVisibilityToggleEnabled() {
        return this.endIconMode == 1;
    }

    @Deprecated
    public void setPasswordVisibilityToggleEnabled(boolean z) {
        if (z && this.endIconMode != 1) {
            setEndIconMode(1);
        } else if (!z) {
            setEndIconMode(0);
        }
    }

    @Deprecated
    public void setPasswordVisibilityToggleTintList(@Nullable ColorStateList colorStateList) {
        this.endIconTintList = colorStateList;
        this.hasEndIconTintList = true;
        applyEndIconTint();
    }

    @Deprecated
    public void setPasswordVisibilityToggleTintMode(@Nullable Mode mode) {
        this.endIconTintMode = mode;
        this.hasEndIconTintMode = true;
        applyEndIconTint();
    }

    @Deprecated
    public void passwordVisibilityToggleRequested(boolean z) {
        if (this.endIconMode == 1) {
            this.endIconView.performClick();
            if (z) {
                this.endIconView.jumpDrawablesToCurrentState();
            }
        }
    }

    public void setTextInputAccessibilityDelegate(AccessibilityDelegate accessibilityDelegate) {
        EditText editText2 = this.editText;
        if (editText2 != null) {
            ViewCompat.setAccessibilityDelegate(editText2, accessibilityDelegate);
        }
    }

    /* access modifiers changed from: 0000 */
    public CheckableImageButton getEndIconView() {
        return this.endIconView;
    }

    private EndIconDelegate getEndIconDelegate() {
        EndIconDelegate endIconDelegate = (EndIconDelegate) this.endIconDelegates.get(this.endIconMode);
        return endIconDelegate != null ? endIconDelegate : (EndIconDelegate) this.endIconDelegates.get(0);
    }

    private void dispatchOnEditTextAttached() {
        Iterator it = this.editTextAttachedListeners.iterator();
        while (it.hasNext()) {
            ((OnEditTextAttachedListener) it.next()).onEditTextAttached(this.editText);
        }
    }

    private boolean hasStartIcon() {
        return getStartIconDrawable() != null;
    }

    private void applyStartIconTint() {
        applyIconTint(this.startIconView, this.hasStartIconTintList, this.startIconTintList, this.hasStartIconTintMode, this.startIconTintMode);
    }

    private boolean hasEndIcon() {
        return this.endIconMode != 0;
    }

    private void dispatchOnEndIconChanged(int i) {
        Iterator it = this.endIconChangedListeners.iterator();
        while (it.hasNext()) {
            ((OnEndIconChangedListener) it.next()).onEndIconChanged(i);
        }
    }

    private void tintEndIconOnError(boolean z) {
        if (!z || getEndIconDrawable() == null) {
            applyEndIconTint();
            return;
        }
        Drawable mutate = DrawableCompat.wrap(getEndIconDrawable()).mutate();
        DrawableCompat.setTint(mutate, this.indicatorViewController.getErrorViewCurrentTextColor());
        this.endIconView.setImageDrawable(mutate);
    }

    private void applyEndIconTint() {
        applyIconTint(this.endIconView, this.hasEndIconTintList, this.endIconTintList, this.hasEndIconTintMode, this.endIconTintMode);
    }

    private void updateIconDummyDrawables() {
        if (this.editText != null) {
            if (hasStartIcon() && isStartIconVisible()) {
                this.startIconDummyDrawable = new ColorDrawable();
                this.startIconDummyDrawable.setBounds(0, 0, (this.startIconView.getMeasuredWidth() - this.editText.getPaddingLeft()) + MarginLayoutParamsCompat.getMarginEnd((MarginLayoutParams) this.startIconView.getLayoutParams()), 1);
                Drawable[] compoundDrawablesRelative = TextViewCompat.getCompoundDrawablesRelative(this.editText);
                TextViewCompat.setCompoundDrawablesRelative(this.editText, this.startIconDummyDrawable, compoundDrawablesRelative[1], compoundDrawablesRelative[2], compoundDrawablesRelative[3]);
            } else if (this.startIconDummyDrawable != null) {
                Drawable[] compoundDrawablesRelative2 = TextViewCompat.getCompoundDrawablesRelative(this.editText);
                TextViewCompat.setCompoundDrawablesRelative(this.editText, null, compoundDrawablesRelative2[1], compoundDrawablesRelative2[2], compoundDrawablesRelative2[3]);
                this.startIconDummyDrawable = null;
            }
            if (hasEndIcon() && isEndIconVisible()) {
                if (this.endIconDummyDrawable == null) {
                    this.endIconDummyDrawable = new ColorDrawable();
                    this.endIconDummyDrawable.setBounds(0, 0, (this.endIconView.getMeasuredWidth() - this.editText.getPaddingRight()) + MarginLayoutParamsCompat.getMarginStart((MarginLayoutParams) this.endIconView.getLayoutParams()), 1);
                }
                Drawable[] compoundDrawablesRelative3 = TextViewCompat.getCompoundDrawablesRelative(this.editText);
                Drawable drawable = compoundDrawablesRelative3[2];
                Drawable drawable2 = this.endIconDummyDrawable;
                if (drawable != drawable2) {
                    this.originalEditTextEndDrawable = compoundDrawablesRelative3[2];
                    TextViewCompat.setCompoundDrawablesRelative(this.editText, compoundDrawablesRelative3[0], compoundDrawablesRelative3[1], drawable2, compoundDrawablesRelative3[3]);
                }
            } else if (this.endIconDummyDrawable != null) {
                Drawable[] compoundDrawablesRelative4 = TextViewCompat.getCompoundDrawablesRelative(this.editText);
                if (compoundDrawablesRelative4[2] == this.endIconDummyDrawable) {
                    TextViewCompat.setCompoundDrawablesRelative(this.editText, compoundDrawablesRelative4[0], compoundDrawablesRelative4[1], this.originalEditTextEndDrawable, compoundDrawablesRelative4[3]);
                }
                this.endIconDummyDrawable = null;
            }
        }
    }

    private void applyIconTint(CheckableImageButton checkableImageButton, boolean z, ColorStateList colorStateList, boolean z2, Mode mode) {
        Drawable drawable = checkableImageButton.getDrawable();
        if (drawable != null && (z || z2)) {
            drawable = DrawableCompat.wrap(drawable).mutate();
            if (z) {
                DrawableCompat.setTintList(drawable, colorStateList);
            }
            if (z2) {
                DrawableCompat.setTintMode(drawable, mode);
            }
        }
        if (checkableImageButton.getDrawable() != drawable) {
            checkableImageButton.setImageDrawable(drawable);
        }
    }

    private void setIconOnClickListener(@NonNull View view, OnClickListener onClickListener) {
        view.setOnClickListener(onClickListener);
        boolean z = true;
        view.setFocusable(onClickListener != null);
        if (onClickListener == null) {
            z = false;
        }
        view.setClickable(z);
    }

    private void updateIconViewOnEditTextAttached(@NonNull View view) {
        ViewCompat.setPaddingRelative(view, 0, getIconViewPaddingTop(view), 0, getIconViewPaddingBottom(view));
        view.bringToFront();
    }

    private int getIconViewPaddingTop(@NonNull View view) {
        if (this.boxBackgroundMode == 1) {
            return view.getPaddingTop();
        }
        EditText editText2 = this.editText;
        return editText2 != null ? editText2.getPaddingTop() : 0;
    }

    private int getIconViewPaddingBottom(@NonNull View view) {
        if (this.boxBackgroundMode == 1) {
            return view.getPaddingBottom();
        }
        EditText editText2 = this.editText;
        return editText2 != null ? editText2.getPaddingBottom() : 0;
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        EditText editText2 = this.editText;
        if (editText2 != null) {
            Rect rect = this.tmpRect;
            DescendantOffsetUtils.getDescendantRect(this, editText2, rect);
            updateBoxUnderlineBounds(rect);
            if (this.hintEnabled) {
                this.collapsingTextHelper.setCollapsedBounds(calculateCollapsedTextBounds(rect));
                this.collapsingTextHelper.setExpandedBounds(calculateExpandedTextBounds(rect));
                this.collapsingTextHelper.recalculate();
                if (cutoutEnabled() && !this.hintExpanded) {
                    openCutout();
                }
            }
        }
    }

    private void updateBoxUnderlineBounds(Rect rect) {
        if (this.boxUnderline != null) {
            this.boxUnderline.setBounds(rect.left, rect.bottom - this.boxStrokeWidthFocusedPx, rect.right, rect.bottom);
        }
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        drawHint(canvas);
        drawBoxUnderline(canvas);
    }

    private void drawHint(Canvas canvas) {
        if (this.hintEnabled) {
            this.collapsingTextHelper.draw(canvas);
        }
    }

    private void drawBoxUnderline(Canvas canvas) {
        MaterialShapeDrawable materialShapeDrawable = this.boxUnderline;
        if (materialShapeDrawable != null) {
            Rect bounds = materialShapeDrawable.getBounds();
            bounds.top = bounds.bottom - this.boxStrokeWidthPx;
            this.boxUnderline.draw(canvas);
        }
    }

    private void collapseHint(boolean z) {
        ValueAnimator valueAnimator = this.animator;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.animator.cancel();
        }
        if (!z || !this.hintAnimationEnabled) {
            this.collapsingTextHelper.setExpansionFraction(1.0f);
        } else {
            animateToExpansionFraction(1.0f);
        }
        this.hintExpanded = false;
        if (cutoutEnabled()) {
            openCutout();
        }
    }

    private boolean cutoutEnabled() {
        return this.hintEnabled && !TextUtils.isEmpty(this.hint) && (this.boxBackground instanceof CutoutDrawable);
    }

    private void openCutout() {
        if (cutoutEnabled()) {
            RectF rectF = this.tmpRectF;
            this.collapsingTextHelper.getCollapsedTextActualBounds(rectF);
            applyCutoutPadding(rectF);
            rectF.offset((float) (-getPaddingLeft()), 0.0f);
            ((CutoutDrawable) this.boxBackground).setCutout(rectF);
        }
    }

    private void closeCutout() {
        if (cutoutEnabled()) {
            ((CutoutDrawable) this.boxBackground).removeCutout();
        }
    }

    private void applyCutoutPadding(RectF rectF) {
        rectF.left -= (float) this.boxLabelCutoutPaddingPx;
        rectF.top -= (float) this.boxLabelCutoutPaddingPx;
        rectF.right += (float) this.boxLabelCutoutPaddingPx;
        rectF.bottom += (float) this.boxLabelCutoutPaddingPx;
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public boolean cutoutIsOpen() {
        return cutoutEnabled() && ((CutoutDrawable) this.boxBackground).hasCutout();
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        if (!this.inDrawableStateChanged) {
            boolean z = true;
            this.inDrawableStateChanged = true;
            super.drawableStateChanged();
            int[] drawableState = getDrawableState();
            CollapsingTextHelper collapsingTextHelper2 = this.collapsingTextHelper;
            boolean state = collapsingTextHelper2 != null ? collapsingTextHelper2.setState(drawableState) | false : false;
            if (!ViewCompat.isLaidOut(this) || !isEnabled()) {
                z = false;
            }
            updateLabelState(z);
            updateEditTextBackground();
            updateTextInputBoxState();
            if (state) {
                invalidate();
            }
            this.inDrawableStateChanged = false;
        }
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0026  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x003a  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x003f  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0089 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00a4  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void updateTextInputBoxState() {
        /*
            r5 = this;
            com.google.android.material.shape.MaterialShapeDrawable r0 = r5.boxBackground
            if (r0 == 0) goto L_0x00bd
            int r0 = r5.boxBackgroundMode
            if (r0 != 0) goto L_0x000a
            goto L_0x00bd
        L_0x000a:
            boolean r0 = r5.isFocused()
            r1 = 0
            r2 = 1
            if (r0 != 0) goto L_0x001f
            android.widget.EditText r0 = r5.editText
            if (r0 == 0) goto L_0x001d
            boolean r0 = r0.hasFocus()
            if (r0 == 0) goto L_0x001d
            goto L_0x001f
        L_0x001d:
            r0 = 0
            goto L_0x0020
        L_0x001f:
            r0 = 1
        L_0x0020:
            boolean r3 = r5.isHovered()
            if (r3 != 0) goto L_0x0033
            android.widget.EditText r3 = r5.editText
            if (r3 == 0) goto L_0x0031
            boolean r3 = r3.isHovered()
            if (r3 == 0) goto L_0x0031
            goto L_0x0033
        L_0x0031:
            r3 = 0
            goto L_0x0034
        L_0x0033:
            r3 = 1
        L_0x0034:
            boolean r4 = r5.isEnabled()
            if (r4 != 0) goto L_0x003f
            int r4 = r5.disabledColor
            r5.boxStrokeColor = r4
            goto L_0x0071
        L_0x003f:
            com.google.android.material.textfield.IndicatorViewController r4 = r5.indicatorViewController
            boolean r4 = r4.errorShouldBeShown()
            if (r4 == 0) goto L_0x0050
            com.google.android.material.textfield.IndicatorViewController r4 = r5.indicatorViewController
            int r4 = r4.getErrorViewCurrentTextColor()
            r5.boxStrokeColor = r4
            goto L_0x0071
        L_0x0050:
            boolean r4 = r5.counterOverflowed
            if (r4 == 0) goto L_0x005f
            android.widget.TextView r4 = r5.counterView
            if (r4 == 0) goto L_0x005f
            int r4 = r4.getCurrentTextColor()
            r5.boxStrokeColor = r4
            goto L_0x0071
        L_0x005f:
            if (r0 == 0) goto L_0x0066
            int r4 = r5.focusedStrokeColor
            r5.boxStrokeColor = r4
            goto L_0x0071
        L_0x0066:
            if (r3 == 0) goto L_0x006d
            int r4 = r5.hoveredStrokeColor
            r5.boxStrokeColor = r4
            goto L_0x0071
        L_0x006d:
            int r4 = r5.defaultStrokeColor
            r5.boxStrokeColor = r4
        L_0x0071:
            com.google.android.material.textfield.IndicatorViewController r4 = r5.indicatorViewController
            boolean r4 = r4.errorShouldBeShown()
            if (r4 == 0) goto L_0x0084
            com.google.android.material.textfield.EndIconDelegate r4 = r5.getEndIconDelegate()
            boolean r4 = r4.shouldTintIconOnError()
            if (r4 == 0) goto L_0x0084
            r1 = 1
        L_0x0084:
            r5.tintEndIconOnError(r1)
            if (r3 != 0) goto L_0x008b
            if (r0 == 0) goto L_0x0099
        L_0x008b:
            boolean r0 = r5.isEnabled()
            if (r0 == 0) goto L_0x0099
            int r0 = r5.boxStrokeWidthFocusedPx
            r5.boxStrokeWidthPx = r0
            r5.adjustCornerSizeForStrokeWidth()
            goto L_0x00a0
        L_0x0099:
            int r0 = r5.boxStrokeWidthDefaultPx
            r5.boxStrokeWidthPx = r0
            r5.adjustCornerSizeForStrokeWidth()
        L_0x00a0:
            int r0 = r5.boxBackgroundMode
            if (r0 != r2) goto L_0x00ba
            boolean r0 = r5.isEnabled()
            if (r0 != 0) goto L_0x00af
            int r0 = r5.disabledFilledBackgroundColor
            r5.boxBackgroundColor = r0
            goto L_0x00ba
        L_0x00af:
            if (r3 == 0) goto L_0x00b6
            int r0 = r5.hoveredFilledBackgroundColor
            r5.boxBackgroundColor = r0
            goto L_0x00ba
        L_0x00b6:
            int r0 = r5.defaultFilledBackgroundColor
            r5.boxBackgroundColor = r0
        L_0x00ba:
            r5.applyBoxAttributes()
        L_0x00bd:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.textfield.TextInputLayout.updateTextInputBoxState():void");
    }

    private void expandHint(boolean z) {
        ValueAnimator valueAnimator = this.animator;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.animator.cancel();
        }
        if (!z || !this.hintAnimationEnabled) {
            this.collapsingTextHelper.setExpansionFraction(0.0f);
        } else {
            animateToExpansionFraction(0.0f);
        }
        if (cutoutEnabled() && ((CutoutDrawable) this.boxBackground).hasCutout()) {
            closeCutout();
        }
        this.hintExpanded = true;
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public void animateToExpansionFraction(float f) {
        if (this.collapsingTextHelper.getExpansionFraction() != f) {
            if (this.animator == null) {
                this.animator = new ValueAnimator();
                this.animator.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
                this.animator.setDuration(167);
                this.animator.addUpdateListener(new AnimatorUpdateListener() {
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        TextInputLayout.this.collapsingTextHelper.setExpansionFraction(((Float) valueAnimator.getAnimatedValue()).floatValue());
                    }
                });
            }
            this.animator.setFloatValues(new float[]{this.collapsingTextHelper.getExpansionFraction(), f});
            this.animator.start();
        }
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public final boolean isHintExpanded() {
        return this.hintExpanded;
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public final boolean isHelperTextDisplayed() {
        return this.indicatorViewController.helperTextIsDisplayed();
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public final int getHintCurrentCollapsedTextColor() {
        return this.collapsingTextHelper.getCurrentCollapsedTextColor();
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public final float getHintCollapsedTextHeight() {
        return this.collapsingTextHelper.getCollapsedTextHeight();
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public final int getErrorTextCurrentColor() {
        return this.indicatorViewController.getErrorViewCurrentTextColor();
    }
}
