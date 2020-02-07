package com.google.android.material.textfield;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build.VERSION;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.widget.AutoCompleteTextView;
import android.widget.AutoCompleteTextView.OnDismissListener;
import android.widget.EditText;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.view.ViewCompat;
import com.google.android.material.C1435R;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.textfield.TextInputLayout.OnEditTextAttachedListener;

class DropdownMenuEndIconDelegate extends EndIconDelegate {
    private static final boolean IS_LOLLIPOP = (VERSION.SDK_INT >= 21);
    private final OnEditTextAttachedListener dropdownMenuOnEditTextAttachedListener = new OnEditTextAttachedListener() {
        public void onEditTextAttached(EditText editText) {
            AutoCompleteTextView access$000 = DropdownMenuEndIconDelegate.this.castAutoCompleteTextViewOrThrow(editText);
            DropdownMenuEndIconDelegate.this.setPopupBackground(access$000);
            DropdownMenuEndIconDelegate.this.addRippleEffect(access$000);
            DropdownMenuEndIconDelegate.this.setUpDropdownShowHideBehavior(access$000);
            access$000.setThreshold(0);
            editText.removeTextChangedListener(DropdownMenuEndIconDelegate.this.exposedDropdownEndIconTextWatcher);
            editText.addTextChangedListener(DropdownMenuEndIconDelegate.this.exposedDropdownEndIconTextWatcher);
            DropdownMenuEndIconDelegate.this.textInputLayout.setEndIconVisible(true);
        }
    };
    /* access modifiers changed from: private */
    public long dropdownPopupActivatedAt = Long.MAX_VALUE;
    /* access modifiers changed from: private */
    public boolean dropdownPopupDirty = false;
    /* access modifiers changed from: private */
    public final TextWatcher exposedDropdownEndIconTextWatcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void afterTextChanged(Editable editable) {
            DropdownMenuEndIconDelegate dropdownMenuEndIconDelegate = DropdownMenuEndIconDelegate.this;
            final AutoCompleteTextView access$000 = dropdownMenuEndIconDelegate.castAutoCompleteTextViewOrThrow(dropdownMenuEndIconDelegate.textInputLayout.getEditText());
            access$000.post(new Runnable() {
                public void run() {
                    boolean isPopupShowing = access$000.isPopupShowing();
                    DropdownMenuEndIconDelegate.this.endIconView.setChecked(isPopupShowing);
                    DropdownMenuEndIconDelegate.this.dropdownPopupDirty = isPopupShowing;
                }
            });
        }
    };
    private StateListDrawable filledPopupBackground;
    private MaterialShapeDrawable outlinedPopupBackground;

    /* access modifiers changed from: 0000 */
    public boolean isBoxBackgroundModeSupported(int i) {
        return i != 0;
    }

    /* access modifiers changed from: 0000 */
    public boolean shouldTintIconOnError() {
        return true;
    }

    DropdownMenuEndIconDelegate(TextInputLayout textInputLayout) {
        super(textInputLayout);
    }

    /* access modifiers changed from: 0000 */
    public void initialize() {
        float dimensionPixelOffset = (float) this.context.getResources().getDimensionPixelOffset(C1435R.dimen.mtrl_shape_corner_size_small_component);
        float dimensionPixelOffset2 = (float) this.context.getResources().getDimensionPixelOffset(C1435R.dimen.mtrl_exposed_dropdown_menu_popup_elevation);
        int dimensionPixelOffset3 = this.context.getResources().getDimensionPixelOffset(C1435R.dimen.mtrl_exposed_dropdown_menu_popup_vertical_padding);
        MaterialShapeDrawable popUpMaterialShapeDrawable = getPopUpMaterialShapeDrawable(dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset2, dimensionPixelOffset3);
        MaterialShapeDrawable popUpMaterialShapeDrawable2 = getPopUpMaterialShapeDrawable(0.0f, dimensionPixelOffset, dimensionPixelOffset2, dimensionPixelOffset3);
        this.outlinedPopupBackground = popUpMaterialShapeDrawable;
        this.filledPopupBackground = new StateListDrawable();
        this.filledPopupBackground.addState(new int[]{16842922}, popUpMaterialShapeDrawable);
        this.filledPopupBackground.addState(new int[0], popUpMaterialShapeDrawable2);
        this.textInputLayout.setEndIconDrawable(AppCompatResources.getDrawable(this.context, IS_LOLLIPOP ? C1435R.C1437drawable.mtrl_dropdown_arrow : C1435R.C1437drawable.mtrl_ic_arrow_drop_down));
        this.textInputLayout.setEndIconContentDescription(this.textInputLayout.getResources().getText(C1435R.string.exposed_dropdown_menu_content_description));
        this.textInputLayout.setEndIconOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                DropdownMenuEndIconDelegate.this.showHideDropdown((AutoCompleteTextView) DropdownMenuEndIconDelegate.this.textInputLayout.getEditText());
            }
        });
        this.textInputLayout.addOnEditTextAttachedListener(this.dropdownMenuOnEditTextAttachedListener);
    }

    /* access modifiers changed from: private */
    public void showHideDropdown(AutoCompleteTextView autoCompleteTextView) {
        if (autoCompleteTextView != null) {
            if (isDropdownPopupActive()) {
                this.dropdownPopupDirty = false;
            }
            if (!this.dropdownPopupDirty) {
                this.endIconView.toggle();
                if (this.endIconView.isChecked()) {
                    autoCompleteTextView.requestFocus();
                    autoCompleteTextView.showDropDown();
                } else {
                    autoCompleteTextView.dismissDropDown();
                }
            } else {
                this.dropdownPopupDirty = false;
            }
        }
    }

    /* access modifiers changed from: private */
    public void setPopupBackground(AutoCompleteTextView autoCompleteTextView) {
        if (IS_LOLLIPOP) {
            int boxBackgroundMode = this.textInputLayout.getBoxBackgroundMode();
            if (boxBackgroundMode == 2) {
                autoCompleteTextView.setDropDownBackgroundDrawable(this.outlinedPopupBackground);
            } else if (boxBackgroundMode == 1) {
                autoCompleteTextView.setDropDownBackgroundDrawable(this.filledPopupBackground);
            }
        }
    }

    /* access modifiers changed from: private */
    public void addRippleEffect(AutoCompleteTextView autoCompleteTextView) {
        if (autoCompleteTextView.getKeyListener() == null) {
            int boxBackgroundMode = this.textInputLayout.getBoxBackgroundMode();
            MaterialShapeDrawable boxBackground = this.textInputLayout.getBoxBackground();
            int color = MaterialColors.getColor(autoCompleteTextView, C1435R.attr.colorControlHighlight);
            int[][] iArr = {new int[]{16842919}, new int[0]};
            if (boxBackgroundMode == 2) {
                addRippleEffectOnOutlinedLayout(autoCompleteTextView, color, iArr, boxBackground);
            } else if (boxBackgroundMode == 1) {
                addRippleEffectOnFilledLayout(autoCompleteTextView, color, iArr, boxBackground);
            }
        }
    }

    private void addRippleEffectOnOutlinedLayout(AutoCompleteTextView autoCompleteTextView, int i, int[][] iArr, MaterialShapeDrawable materialShapeDrawable) {
        LayerDrawable layerDrawable;
        int color = MaterialColors.getColor(autoCompleteTextView, C1435R.attr.colorSurface);
        MaterialShapeDrawable materialShapeDrawable2 = new MaterialShapeDrawable(materialShapeDrawable.getShapeAppearanceModel());
        int layer = MaterialColors.layer(i, color, 0.1f);
        materialShapeDrawable2.setFillColor(new ColorStateList(iArr, new int[]{layer, 0}));
        if (IS_LOLLIPOP) {
            materialShapeDrawable2.setTint(color);
            ColorStateList colorStateList = new ColorStateList(iArr, new int[]{layer, color});
            MaterialShapeDrawable materialShapeDrawable3 = new MaterialShapeDrawable(materialShapeDrawable.getShapeAppearanceModel());
            materialShapeDrawable3.setTint(-1);
            layerDrawable = new LayerDrawable(new Drawable[]{new RippleDrawable(colorStateList, materialShapeDrawable2, materialShapeDrawable3), materialShapeDrawable});
        } else {
            layerDrawable = new LayerDrawable(new Drawable[]{materialShapeDrawable2, materialShapeDrawable});
        }
        ViewCompat.setBackground(autoCompleteTextView, layerDrawable);
    }

    private void addRippleEffectOnFilledLayout(AutoCompleteTextView autoCompleteTextView, int i, int[][] iArr, MaterialShapeDrawable materialShapeDrawable) {
        int boxBackgroundColor = this.textInputLayout.getBoxBackgroundColor();
        int[] iArr2 = {MaterialColors.layer(i, boxBackgroundColor, 0.1f), boxBackgroundColor};
        if (IS_LOLLIPOP) {
            ViewCompat.setBackground(autoCompleteTextView, new RippleDrawable(new ColorStateList(iArr, iArr2), materialShapeDrawable, materialShapeDrawable));
            return;
        }
        MaterialShapeDrawable materialShapeDrawable2 = new MaterialShapeDrawable(materialShapeDrawable.getShapeAppearanceModel());
        materialShapeDrawable2.setFillColor(new ColorStateList(iArr, iArr2));
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{materialShapeDrawable, materialShapeDrawable2});
        int paddingStart = ViewCompat.getPaddingStart(autoCompleteTextView);
        int paddingTop = autoCompleteTextView.getPaddingTop();
        int paddingEnd = ViewCompat.getPaddingEnd(autoCompleteTextView);
        int paddingBottom = autoCompleteTextView.getPaddingBottom();
        ViewCompat.setBackground(autoCompleteTextView, layerDrawable);
        ViewCompat.setPaddingRelative(autoCompleteTextView, paddingStart, paddingTop, paddingEnd, paddingBottom);
    }

    /* access modifiers changed from: private */
    public void setUpDropdownShowHideBehavior(final AutoCompleteTextView autoCompleteTextView) {
        autoCompleteTextView.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 1) {
                    if (DropdownMenuEndIconDelegate.this.isDropdownPopupActive()) {
                        DropdownMenuEndIconDelegate.this.dropdownPopupDirty = false;
                    }
                    DropdownMenuEndIconDelegate.this.showHideDropdown(autoCompleteTextView);
                    view.performClick();
                }
                return false;
            }
        });
        autoCompleteTextView.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                DropdownMenuEndIconDelegate.this.textInputLayout.setEndIconActivated(z);
                if (!z) {
                    DropdownMenuEndIconDelegate.this.endIconView.setChecked(false);
                    DropdownMenuEndIconDelegate.this.dropdownPopupDirty = false;
                }
            }
        });
        if (IS_LOLLIPOP) {
            autoCompleteTextView.setOnDismissListener(new OnDismissListener() {
                public void onDismiss() {
                    DropdownMenuEndIconDelegate.this.dropdownPopupDirty = true;
                    DropdownMenuEndIconDelegate.this.dropdownPopupActivatedAt = System.currentTimeMillis();
                    DropdownMenuEndIconDelegate.this.endIconView.setChecked(false);
                }
            });
        }
    }

    private MaterialShapeDrawable getPopUpMaterialShapeDrawable(float f, float f2, float f3, int i) {
        ShapeAppearanceModel shapeAppearanceModel = new ShapeAppearanceModel();
        shapeAppearanceModel.setCornerRadii(f, f, f2, f2);
        MaterialShapeDrawable createWithElevationOverlay = MaterialShapeDrawable.createWithElevationOverlay(this.context, f3);
        createWithElevationOverlay.setShapeAppearanceModel(shapeAppearanceModel);
        createWithElevationOverlay.setPadding(0, i, 0, i);
        return createWithElevationOverlay;
    }

    /* access modifiers changed from: private */
    public boolean isDropdownPopupActive() {
        long currentTimeMillis = System.currentTimeMillis() - this.dropdownPopupActivatedAt;
        return currentTimeMillis < 0 || currentTimeMillis > 300;
    }

    /* access modifiers changed from: private */
    public AutoCompleteTextView castAutoCompleteTextViewOrThrow(EditText editText) {
        if (editText instanceof AutoCompleteTextView) {
            return (AutoCompleteTextView) editText;
        }
        throw new RuntimeException("EditText needs to be an AutoCompleteTextView if an Exposed Dropdown Menu is being used.");
    }
}
