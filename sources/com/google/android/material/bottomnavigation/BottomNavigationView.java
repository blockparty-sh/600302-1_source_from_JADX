package com.google.android.material.bottomnavigation;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.ClassLoaderCreator;
import android.os.Parcelable.Creator;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import androidx.annotation.DimenRes;
import androidx.annotation.Dimension;
import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.StyleRes;
import androidx.appcompat.view.SupportMenuInflater;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuBuilder.Callback;
import androidx.appcompat.widget.TintTypedArray;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.ViewCompat;
import androidx.customview.view.AbsSavedState;
import com.google.android.material.C1435R;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.ripple.RippleUtils;
import com.google.android.material.shape.MaterialShapeDrawable;

public class BottomNavigationView extends FrameLayout {
    private static final int DEF_STYLE_RES = C1435R.C1440style.Widget_Design_BottomNavigationView;
    private static final int MENU_PRESENTER_ID = 1;
    private ColorStateList itemRippleColor;
    private final MenuBuilder menu;
    private MenuInflater menuInflater;
    private final BottomNavigationMenuView menuView;
    private final BottomNavigationPresenter presenter;
    /* access modifiers changed from: private */
    public OnNavigationItemReselectedListener reselectedListener;
    /* access modifiers changed from: private */
    public OnNavigationItemSelectedListener selectedListener;

    public interface OnNavigationItemReselectedListener {
        void onNavigationItemReselected(@NonNull MenuItem menuItem);
    }

    public interface OnNavigationItemSelectedListener {
        boolean onNavigationItemSelected(@NonNull MenuItem menuItem);
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
        Bundle menuPresenterState;

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            readFromParcel(parcel, classLoader);
        }

        public void writeToParcel(@NonNull Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeBundle(this.menuPresenterState);
        }

        private void readFromParcel(Parcel parcel, ClassLoader classLoader) {
            this.menuPresenterState = parcel.readBundle(classLoader);
        }
    }

    public int getMaxItemCount() {
        return 5;
    }

    public BottomNavigationView(Context context) {
        this(context, null);
    }

    public BottomNavigationView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, C1435R.attr.bottomNavigationStyle);
    }

    public BottomNavigationView(Context context, AttributeSet attributeSet, int i) {
        super(ThemeEnforcement.createThemedContext(context, attributeSet, i, DEF_STYLE_RES), attributeSet, i);
        this.presenter = new BottomNavigationPresenter();
        Context context2 = getContext();
        this.menu = new BottomNavigationMenu(context2);
        this.menuView = new BottomNavigationMenuView(context2);
        LayoutParams layoutParams = new LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        this.menuView.setLayoutParams(layoutParams);
        this.presenter.setBottomNavigationMenuView(this.menuView);
        this.presenter.setId(1);
        this.menuView.setPresenter(this.presenter);
        this.menu.addMenuPresenter(this.presenter);
        this.presenter.initForMenu(getContext(), this.menu);
        TintTypedArray obtainTintedStyledAttributes = ThemeEnforcement.obtainTintedStyledAttributes(context2, attributeSet, C1435R.styleable.BottomNavigationView, i, C1435R.C1440style.Widget_Design_BottomNavigationView, C1435R.styleable.BottomNavigationView_itemTextAppearanceInactive, C1435R.styleable.BottomNavigationView_itemTextAppearanceActive);
        if (obtainTintedStyledAttributes.hasValue(C1435R.styleable.BottomNavigationView_itemIconTint)) {
            this.menuView.setIconTintList(obtainTintedStyledAttributes.getColorStateList(C1435R.styleable.BottomNavigationView_itemIconTint));
        } else {
            BottomNavigationMenuView bottomNavigationMenuView = this.menuView;
            bottomNavigationMenuView.setIconTintList(bottomNavigationMenuView.createDefaultColorStateList(16842808));
        }
        setItemIconSize(obtainTintedStyledAttributes.getDimensionPixelSize(C1435R.styleable.BottomNavigationView_itemIconSize, getResources().getDimensionPixelSize(C1435R.dimen.design_bottom_navigation_icon_size)));
        if (obtainTintedStyledAttributes.hasValue(C1435R.styleable.BottomNavigationView_itemTextAppearanceInactive)) {
            setItemTextAppearanceInactive(obtainTintedStyledAttributes.getResourceId(C1435R.styleable.BottomNavigationView_itemTextAppearanceInactive, 0));
        }
        if (obtainTintedStyledAttributes.hasValue(C1435R.styleable.BottomNavigationView_itemTextAppearanceActive)) {
            setItemTextAppearanceActive(obtainTintedStyledAttributes.getResourceId(C1435R.styleable.BottomNavigationView_itemTextAppearanceActive, 0));
        }
        if (obtainTintedStyledAttributes.hasValue(C1435R.styleable.BottomNavigationView_itemTextColor)) {
            setItemTextColor(obtainTintedStyledAttributes.getColorStateList(C1435R.styleable.BottomNavigationView_itemTextColor));
        }
        if (getBackground() == null || (getBackground() instanceof ColorDrawable)) {
            ViewCompat.setBackground(this, createMaterialShapeDrawableBackground(context2));
        }
        if (obtainTintedStyledAttributes.hasValue(C1435R.styleable.BottomNavigationView_elevation)) {
            ViewCompat.setElevation(this, (float) obtainTintedStyledAttributes.getDimensionPixelSize(C1435R.styleable.BottomNavigationView_elevation, 0));
        }
        DrawableCompat.setTintList(getBackground().mutate(), MaterialResources.getColorStateList(context2, obtainTintedStyledAttributes, C1435R.styleable.BottomNavigationView_backgroundTint));
        setLabelVisibilityMode(obtainTintedStyledAttributes.getInteger(C1435R.styleable.BottomNavigationView_labelVisibilityMode, -1));
        setItemHorizontalTranslationEnabled(obtainTintedStyledAttributes.getBoolean(C1435R.styleable.BottomNavigationView_itemHorizontalTranslationEnabled, true));
        int resourceId = obtainTintedStyledAttributes.getResourceId(C1435R.styleable.BottomNavigationView_itemBackground, 0);
        if (resourceId != 0) {
            this.menuView.setItemBackgroundRes(resourceId);
        } else {
            setItemRippleColor(MaterialResources.getColorStateList(context2, obtainTintedStyledAttributes, C1435R.styleable.BottomNavigationView_itemRippleColor));
        }
        if (obtainTintedStyledAttributes.hasValue(C1435R.styleable.BottomNavigationView_menu)) {
            inflateMenu(obtainTintedStyledAttributes.getResourceId(C1435R.styleable.BottomNavigationView_menu, 0));
        }
        obtainTintedStyledAttributes.recycle();
        addView(this.menuView, layoutParams);
        if (VERSION.SDK_INT < 21) {
            addCompatibilityTopDivider(context2);
        }
        this.menu.setCallback(new Callback() {
            public void onMenuModeChange(MenuBuilder menuBuilder) {
            }

            public boolean onMenuItemSelected(MenuBuilder menuBuilder, MenuItem menuItem) {
                boolean z = true;
                if (BottomNavigationView.this.reselectedListener == null || menuItem.getItemId() != BottomNavigationView.this.getSelectedItemId()) {
                    if (BottomNavigationView.this.selectedListener == null || BottomNavigationView.this.selectedListener.onNavigationItemSelected(menuItem)) {
                        z = false;
                    }
                    return z;
                }
                BottomNavigationView.this.reselectedListener.onNavigationItemReselected(menuItem);
                return true;
            }
        });
    }

    private MaterialShapeDrawable createMaterialShapeDrawableBackground(Context context) {
        MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable();
        Drawable background = getBackground();
        if (background instanceof ColorDrawable) {
            materialShapeDrawable.setFillColor(ColorStateList.valueOf(((ColorDrawable) background).getColor()));
        }
        materialShapeDrawable.initializeElevationOverlay(context);
        return materialShapeDrawable;
    }

    @RequiresApi(21)
    public void setElevation(float f) {
        super.setElevation(f);
        Drawable background = getBackground();
        if (background instanceof MaterialShapeDrawable) {
            ((MaterialShapeDrawable) background).setElevation(f);
        }
    }

    public void setOnNavigationItemSelectedListener(@Nullable OnNavigationItemSelectedListener onNavigationItemSelectedListener) {
        this.selectedListener = onNavigationItemSelectedListener;
    }

    public void setOnNavigationItemReselectedListener(@Nullable OnNavigationItemReselectedListener onNavigationItemReselectedListener) {
        this.reselectedListener = onNavigationItemReselectedListener;
    }

    @NonNull
    public Menu getMenu() {
        return this.menu;
    }

    public void inflateMenu(int i) {
        this.presenter.setUpdateSuspended(true);
        getMenuInflater().inflate(i, this.menu);
        this.presenter.setUpdateSuspended(false);
        this.presenter.updateMenuView(true);
    }

    @Nullable
    public ColorStateList getItemIconTintList() {
        return this.menuView.getIconTintList();
    }

    public void setItemIconTintList(@Nullable ColorStateList colorStateList) {
        this.menuView.setIconTintList(colorStateList);
    }

    public void setItemIconSize(@Dimension int i) {
        this.menuView.setItemIconSize(i);
    }

    public void setItemIconSizeRes(@DimenRes int i) {
        setItemIconSize(getResources().getDimensionPixelSize(i));
    }

    @Dimension
    public int getItemIconSize() {
        return this.menuView.getItemIconSize();
    }

    @Nullable
    public ColorStateList getItemTextColor() {
        return this.menuView.getItemTextColor();
    }

    public void setItemTextColor(@Nullable ColorStateList colorStateList) {
        this.menuView.setItemTextColor(colorStateList);
    }

    @Deprecated
    @DrawableRes
    public int getItemBackgroundResource() {
        return this.menuView.getItemBackgroundRes();
    }

    public void setItemBackgroundResource(@DrawableRes int i) {
        this.menuView.setItemBackgroundRes(i);
        this.itemRippleColor = null;
    }

    @Nullable
    public Drawable getItemBackground() {
        return this.menuView.getItemBackground();
    }

    public void setItemBackground(@Nullable Drawable drawable) {
        this.menuView.setItemBackground(drawable);
        this.itemRippleColor = null;
    }

    public ColorStateList getItemRippleColor() {
        return this.itemRippleColor;
    }

    public void setItemRippleColor(ColorStateList colorStateList) {
        if (this.itemRippleColor == colorStateList) {
            if (colorStateList == null && this.menuView.getItemBackground() != null) {
                this.menuView.setItemBackground(null);
            }
            return;
        }
        this.itemRippleColor = colorStateList;
        if (colorStateList == null) {
            this.menuView.setItemBackground(null);
        } else {
            ColorStateList convertToRippleDrawableColor = RippleUtils.convertToRippleDrawableColor(colorStateList);
            if (VERSION.SDK_INT >= 21) {
                this.menuView.setItemBackground(new RippleDrawable(convertToRippleDrawableColor, null, null));
            } else {
                GradientDrawable gradientDrawable = new GradientDrawable();
                gradientDrawable.setCornerRadius(1.0E-5f);
                Drawable wrap = DrawableCompat.wrap(gradientDrawable);
                DrawableCompat.setTintList(wrap, convertToRippleDrawableColor);
                this.menuView.setItemBackground(wrap);
            }
        }
    }

    @IdRes
    public int getSelectedItemId() {
        return this.menuView.getSelectedItemId();
    }

    public void setSelectedItemId(@IdRes int i) {
        MenuItem findItem = this.menu.findItem(i);
        if (findItem != null && !this.menu.performItemAction(findItem, this.presenter, 0)) {
            findItem.setChecked(true);
        }
    }

    public void setLabelVisibilityMode(int i) {
        if (this.menuView.getLabelVisibilityMode() != i) {
            this.menuView.setLabelVisibilityMode(i);
            this.presenter.updateMenuView(false);
        }
    }

    public int getLabelVisibilityMode() {
        return this.menuView.getLabelVisibilityMode();
    }

    public void setItemTextAppearanceInactive(@StyleRes int i) {
        this.menuView.setItemTextAppearanceInactive(i);
    }

    @StyleRes
    public int getItemTextAppearanceInactive() {
        return this.menuView.getItemTextAppearanceInactive();
    }

    public void setItemTextAppearanceActive(@StyleRes int i) {
        this.menuView.setItemTextAppearanceActive(i);
    }

    @StyleRes
    public int getItemTextAppearanceActive() {
        return this.menuView.getItemTextAppearanceActive();
    }

    public void setItemHorizontalTranslationEnabled(boolean z) {
        if (this.menuView.isItemHorizontalTranslationEnabled() != z) {
            this.menuView.setItemHorizontalTranslationEnabled(z);
            this.presenter.updateMenuView(false);
        }
    }

    public boolean isItemHorizontalTranslationEnabled() {
        return this.menuView.isItemHorizontalTranslationEnabled();
    }

    @Nullable
    public BadgeDrawable getBadge(int i) {
        return this.menuView.getBadge(i);
    }

    public BadgeDrawable showBadge(int i) {
        return this.menuView.showBadge(i);
    }

    public void removeBadge(int i) {
        this.menuView.removeBadge(i);
    }

    private void addCompatibilityTopDivider(Context context) {
        View view = new View(context);
        view.setBackgroundColor(ContextCompat.getColor(context, C1435R.C1436color.design_bottom_navigation_shadow_color));
        view.setLayoutParams(new LayoutParams(-1, getResources().getDimensionPixelSize(C1435R.dimen.design_bottom_navigation_shadow_height)));
        addView(view);
    }

    private MenuInflater getMenuInflater() {
        if (this.menuInflater == null) {
            this.menuInflater = new SupportMenuInflater(getContext());
        }
        return this.menuInflater;
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.menuPresenterState = new Bundle();
        this.menu.savePresenterStates(savedState.menuPresenterState);
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
        this.menu.restorePresenterStates(savedState.menuPresenterState);
    }
}
