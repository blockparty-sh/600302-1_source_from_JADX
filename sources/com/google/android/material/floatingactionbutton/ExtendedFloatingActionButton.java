package com.google.android.material.floatingactionbutton;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.AnimatorRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout.AttachedBehavior;
import androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior;
import androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams;
import androidx.core.util.Preconditions;
import androidx.core.view.ViewCompat;
import com.google.android.material.C1435R;
import com.google.android.material.animation.AnimatorSetCompat;
import com.google.android.material.animation.MotionSpec;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.internal.DescendantOffsetUtils;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.shape.ShapeAppearanceModel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExtendedFloatingActionButton extends MaterialButton implements AttachedBehavior {
    private static final int ANIM_STATE_HIDING = 1;
    private static final int ANIM_STATE_NONE = 0;
    private static final int ANIM_STATE_SHOWING = 2;
    private static final Property<View, Float> CORNER_RADIUS = new Property<View, Float>(Float.class, "cornerRadius") {
        public void set(View view, Float f) {
            ((ExtendedFloatingActionButton) view).getShapeAppearanceModel().setCornerRadius((float) f.intValue());
        }

        public Float get(View view) {
            return Float.valueOf(((ExtendedFloatingActionButton) view).getShapeAppearanceModel().getTopRightCorner().getCornerSize());
        }
    };
    private static final int DEF_STYLE_RES = C1435R.C1440style.Widget_MaterialComponents_ExtendedFloatingActionButton_Icon;
    private static final Property<View, Float> HEIGHT = new Property<View, Float>(Float.class, "height") {
        public void set(View view, Float f) {
            view.getLayoutParams().height = f.intValue();
            view.requestLayout();
        }

        public Float get(View view) {
            return Float.valueOf((float) view.getLayoutParams().height);
        }
    };
    private static final Property<View, Float> WIDTH = new Property<View, Float>(Float.class, "width") {
        public void set(View view, Float f) {
            view.getLayoutParams().width = f.intValue();
            view.requestLayout();
        }

        public Float get(View view) {
            return Float.valueOf((float) view.getLayoutParams().width);
        }
    };
    /* access modifiers changed from: private */
    public int animState;
    private final Behavior<ExtendedFloatingActionButton> behavior;
    /* access modifiers changed from: private */
    @Nullable
    public Animator currentCollapseExpandAnimator;
    /* access modifiers changed from: private */
    @Nullable
    public Animator currentShowHideAnimator;
    @Nullable
    private MotionSpec defaultExtendMotionSpec;
    @Nullable
    private MotionSpec defaultHideMotionSpec;
    @Nullable
    private MotionSpec defaultShowMotionSpec;
    @Nullable
    private MotionSpec defaultShrinkMotionSpec;
    @Nullable
    private ArrayList<AnimatorListener> extendListeners;
    @Nullable
    private MotionSpec extendMotionSpec;
    @Nullable
    private ArrayList<AnimatorListener> hideListeners;
    @Nullable
    private MotionSpec hideMotionSpec;
    private boolean isExtended;
    private boolean isUsingPillCorner;
    /* access modifiers changed from: private */
    public final Rect shadowPadding;
    @Nullable
    private ArrayList<AnimatorListener> showListeners;
    @Nullable
    private MotionSpec showMotionSpec;
    @Nullable
    private ArrayList<AnimatorListener> shrinkListeners;
    @Nullable
    private MotionSpec shrinkMotionSpec;
    private int userSetVisibility;

    protected static class ExtendedFloatingActionButtonBehavior<T extends ExtendedFloatingActionButton> extends Behavior<T> {
        private static final boolean AUTO_HIDE_DEFAULT = false;
        private static final boolean AUTO_SHRINK_DEFAULT = true;
        private boolean autoHideEnabled;
        private boolean autoShrinkEnabled;
        @Nullable
        private OnChangedListener internalAutoHideListener;
        @Nullable
        private OnChangedListener internalAutoShrinkListener;
        private Rect tmpRect;

        public ExtendedFloatingActionButtonBehavior() {
            this.autoHideEnabled = false;
            this.autoShrinkEnabled = AUTO_SHRINK_DEFAULT;
        }

        public ExtendedFloatingActionButtonBehavior(Context context, @Nullable AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C1435R.styleable.ExtendedFloatingActionButton_Behavior_Layout);
            this.autoHideEnabled = obtainStyledAttributes.getBoolean(C1435R.styleable.ExtendedFloatingActionButton_Behavior_Layout_behavior_autoHide, false);
            this.autoShrinkEnabled = obtainStyledAttributes.getBoolean(C1435R.styleable.ExtendedFloatingActionButton_Behavior_Layout_behavior_autoShrink, AUTO_SHRINK_DEFAULT);
            obtainStyledAttributes.recycle();
        }

        public void setAutoHideEnabled(boolean z) {
            this.autoHideEnabled = z;
        }

        public boolean isAutoHideEnabled() {
            return this.autoHideEnabled;
        }

        public void setAutoShrinkEnabled(boolean z) {
            this.autoShrinkEnabled = z;
        }

        public boolean isAutoShrinkEnabled() {
            return this.autoShrinkEnabled;
        }

        public void onAttachedToLayoutParams(@NonNull LayoutParams layoutParams) {
            if (layoutParams.dodgeInsetEdges == 0) {
                layoutParams.dodgeInsetEdges = 80;
            }
        }

        public boolean onDependentViewChanged(CoordinatorLayout coordinatorLayout, ExtendedFloatingActionButton extendedFloatingActionButton, View view) {
            if (view instanceof AppBarLayout) {
                updateFabVisibilityForAppBarLayout(coordinatorLayout, (AppBarLayout) view, extendedFloatingActionButton);
            } else if (isBottomSheet(view)) {
                updateFabVisibilityForBottomSheet(view, extendedFloatingActionButton);
            }
            return false;
        }

        private static boolean isBottomSheet(@NonNull View view) {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams instanceof LayoutParams) {
                return ((LayoutParams) layoutParams).getBehavior() instanceof BottomSheetBehavior;
            }
            return false;
        }

        @VisibleForTesting
        public void setInternalAutoHideListener(@Nullable OnChangedListener onChangedListener) {
            this.internalAutoHideListener = onChangedListener;
        }

        @VisibleForTesting
        public void setInternalAutoShrinkListener(@Nullable OnChangedListener onChangedListener) {
            this.internalAutoShrinkListener = onChangedListener;
        }

        private boolean shouldUpdateVisibility(View view, ExtendedFloatingActionButton extendedFloatingActionButton) {
            LayoutParams layoutParams = (LayoutParams) extendedFloatingActionButton.getLayoutParams();
            if ((this.autoHideEnabled || this.autoShrinkEnabled) && layoutParams.getAnchorId() == view.getId() && extendedFloatingActionButton.getUserSetVisibility() == 0) {
                return AUTO_SHRINK_DEFAULT;
            }
            return false;
        }

        private boolean updateFabVisibilityForAppBarLayout(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, ExtendedFloatingActionButton extendedFloatingActionButton) {
            if (!shouldUpdateVisibility(appBarLayout, extendedFloatingActionButton)) {
                return false;
            }
            if (this.tmpRect == null) {
                this.tmpRect = new Rect();
            }
            Rect rect = this.tmpRect;
            DescendantOffsetUtils.getDescendantRect(coordinatorLayout, appBarLayout, rect);
            if (rect.bottom <= appBarLayout.getMinimumHeightForVisibleOverlappingContent()) {
                shrinkOrHide(extendedFloatingActionButton);
            } else {
                extendOrShow(extendedFloatingActionButton);
            }
            return AUTO_SHRINK_DEFAULT;
        }

        private boolean updateFabVisibilityForBottomSheet(View view, ExtendedFloatingActionButton extendedFloatingActionButton) {
            if (!shouldUpdateVisibility(view, extendedFloatingActionButton)) {
                return false;
            }
            if (view.getTop() < (extendedFloatingActionButton.getHeight() / 2) + ((LayoutParams) extendedFloatingActionButton.getLayoutParams()).topMargin) {
                shrinkOrHide(extendedFloatingActionButton);
            } else {
                extendOrShow(extendedFloatingActionButton);
            }
            return AUTO_SHRINK_DEFAULT;
        }

        /* access modifiers changed from: protected */
        public void shrinkOrHide(@NonNull ExtendedFloatingActionButton extendedFloatingActionButton) {
            if (this.autoShrinkEnabled) {
                extendedFloatingActionButton.shrink(this.internalAutoShrinkListener);
            } else if (this.autoHideEnabled) {
                extendedFloatingActionButton.hide(false, AUTO_SHRINK_DEFAULT, this.internalAutoHideListener);
            }
        }

        /* access modifiers changed from: protected */
        public void extendOrShow(@NonNull ExtendedFloatingActionButton extendedFloatingActionButton) {
            if (this.autoShrinkEnabled) {
                extendedFloatingActionButton.extend(this.internalAutoShrinkListener);
            } else if (this.autoHideEnabled) {
                extendedFloatingActionButton.show(false, AUTO_SHRINK_DEFAULT, this.internalAutoHideListener);
            }
        }

        public boolean onLayoutChild(CoordinatorLayout coordinatorLayout, ExtendedFloatingActionButton extendedFloatingActionButton, int i) {
            List dependencies = coordinatorLayout.getDependencies(extendedFloatingActionButton);
            int size = dependencies.size();
            for (int i2 = 0; i2 < size; i2++) {
                View view = (View) dependencies.get(i2);
                if (!(view instanceof AppBarLayout)) {
                    if (isBottomSheet(view) && updateFabVisibilityForBottomSheet(view, extendedFloatingActionButton)) {
                        break;
                    }
                } else if (updateFabVisibilityForAppBarLayout(coordinatorLayout, (AppBarLayout) view, extendedFloatingActionButton)) {
                    break;
                }
            }
            coordinatorLayout.onLayoutChild(extendedFloatingActionButton, i);
            offsetIfNeeded(coordinatorLayout, extendedFloatingActionButton);
            return AUTO_SHRINK_DEFAULT;
        }

        public boolean getInsetDodgeRect(@NonNull CoordinatorLayout coordinatorLayout, @NonNull ExtendedFloatingActionButton extendedFloatingActionButton, @NonNull Rect rect) {
            Rect access$600 = extendedFloatingActionButton.shadowPadding;
            rect.set(extendedFloatingActionButton.getLeft() + access$600.left, extendedFloatingActionButton.getTop() + access$600.top, extendedFloatingActionButton.getRight() - access$600.right, extendedFloatingActionButton.getBottom() - access$600.bottom);
            return AUTO_SHRINK_DEFAULT;
        }

        private void offsetIfNeeded(CoordinatorLayout coordinatorLayout, ExtendedFloatingActionButton extendedFloatingActionButton) {
            Rect access$600 = extendedFloatingActionButton.shadowPadding;
            if (access$600 != null && access$600.centerX() > 0 && access$600.centerY() > 0) {
                LayoutParams layoutParams = (LayoutParams) extendedFloatingActionButton.getLayoutParams();
                int i = 0;
                int i2 = extendedFloatingActionButton.getRight() >= coordinatorLayout.getWidth() - layoutParams.rightMargin ? access$600.right : extendedFloatingActionButton.getLeft() <= layoutParams.leftMargin ? -access$600.left : 0;
                if (extendedFloatingActionButton.getBottom() >= coordinatorLayout.getHeight() - layoutParams.bottomMargin) {
                    i = access$600.bottom;
                } else if (extendedFloatingActionButton.getTop() <= layoutParams.topMargin) {
                    i = -access$600.top;
                }
                if (i != 0) {
                    ViewCompat.offsetTopAndBottom(extendedFloatingActionButton, i);
                }
                if (i2 != 0) {
                    ViewCompat.offsetLeftAndRight(extendedFloatingActionButton, i2);
                }
            }
        }
    }

    public static abstract class OnChangedListener {
        public void onExtended(ExtendedFloatingActionButton extendedFloatingActionButton) {
        }

        public void onHidden(ExtendedFloatingActionButton extendedFloatingActionButton) {
        }

        public void onShown(ExtendedFloatingActionButton extendedFloatingActionButton) {
        }

        public void onShrunken(ExtendedFloatingActionButton extendedFloatingActionButton) {
        }
    }

    public ExtendedFloatingActionButton(Context context) {
        this(context, null);
    }

    public ExtendedFloatingActionButton(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, C1435R.attr.extendedFloatingActionButtonStyle);
    }

    public ExtendedFloatingActionButton(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.shadowPadding = new Rect();
        this.animState = 0;
        this.isExtended = true;
        this.isUsingPillCorner = true;
        this.behavior = new ExtendedFloatingActionButtonBehavior(context, attributeSet);
        this.userSetVisibility = getVisibility();
        int i2 = i;
        TypedArray obtainStyledAttributes = ThemeEnforcement.obtainStyledAttributes(context, attributeSet, C1435R.styleable.ExtendedFloatingActionButton, i2, DEF_STYLE_RES, new int[0]);
        this.showMotionSpec = MotionSpec.createFromAttribute(context, obtainStyledAttributes, C1435R.styleable.ExtendedFloatingActionButton_showMotionSpec);
        this.hideMotionSpec = MotionSpec.createFromAttribute(context, obtainStyledAttributes, C1435R.styleable.ExtendedFloatingActionButton_hideMotionSpec);
        this.extendMotionSpec = MotionSpec.createFromAttribute(context, obtainStyledAttributes, C1435R.styleable.ExtendedFloatingActionButton_extendMotionSpec);
        this.shrinkMotionSpec = MotionSpec.createFromAttribute(context, obtainStyledAttributes, C1435R.styleable.ExtendedFloatingActionButton_shrinkMotionSpec);
        obtainStyledAttributes.recycle();
        ShapeAppearanceModel shapeAppearanceModel = new ShapeAppearanceModel(context, attributeSet, i2, DEF_STYLE_RES, -1);
        setShapeAppearanceModel(shapeAppearanceModel);
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.isExtended && TextUtils.isEmpty(getText()) && getIcon() != null) {
            this.isExtended = false;
            shrinkNow();
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.isUsingPillCorner) {
            getShapeAppearanceModel().setCornerRadius((float) getAdjustedRadius(getMeasuredHeight()));
        }
    }

    @NonNull
    public Behavior<ExtendedFloatingActionButton> getBehavior() {
        return this.behavior;
    }

    public void setShapeAppearanceModel(@NonNull ShapeAppearanceModel shapeAppearanceModel) {
        this.isUsingPillCorner = shapeAppearanceModel.isUsingPillCorner();
        super.setShapeAppearanceModel(shapeAppearanceModel);
    }

    public void setCornerRadius(int i) {
        this.isUsingPillCorner = i == -1;
        if (this.isUsingPillCorner) {
            i = getAdjustedRadius(getMeasuredHeight());
        } else if (i < 0) {
            i = 0;
        }
        super.setCornerRadius(i);
    }

    public void setVisibility(int i) {
        internalSetVisibility(i, true);
    }

    /* access modifiers changed from: private */
    public void internalSetVisibility(int i, boolean z) {
        super.setVisibility(i);
        if (z) {
            this.userSetVisibility = i;
        }
    }

    public final int getUserSetVisibility() {
        return this.userSetVisibility;
    }

    public final boolean isExtended() {
        return this.isExtended;
    }

    public void addOnShowAnimationListener(@NonNull AnimatorListener animatorListener) {
        if (this.showListeners == null) {
            this.showListeners = new ArrayList<>();
        }
        this.showListeners.add(animatorListener);
    }

    public void removeOnShowAnimationListener(@NonNull AnimatorListener animatorListener) {
        ArrayList<AnimatorListener> arrayList = this.showListeners;
        if (arrayList != null) {
            arrayList.remove(animatorListener);
        }
    }

    public void addOnHideAnimationListener(@NonNull AnimatorListener animatorListener) {
        if (this.hideListeners == null) {
            this.hideListeners = new ArrayList<>();
        }
        this.hideListeners.add(animatorListener);
    }

    public void removeOnHideAnimationListener(@NonNull AnimatorListener animatorListener) {
        ArrayList<AnimatorListener> arrayList = this.hideListeners;
        if (arrayList != null) {
            arrayList.remove(animatorListener);
        }
    }

    public void addOnShrinkAnimationListener(@NonNull AnimatorListener animatorListener) {
        if (this.shrinkListeners == null) {
            this.shrinkListeners = new ArrayList<>();
        }
        this.shrinkListeners.add(animatorListener);
    }

    public void removeOnShrinkAnimationListener(@NonNull AnimatorListener animatorListener) {
        ArrayList<AnimatorListener> arrayList = this.shrinkListeners;
        if (arrayList != null) {
            arrayList.remove(animatorListener);
        }
    }

    public void addOnExtendAnimationListener(@NonNull AnimatorListener animatorListener) {
        if (this.extendListeners == null) {
            this.extendListeners = new ArrayList<>();
        }
        this.extendListeners.add(animatorListener);
    }

    public void removeOnExtendAnimationListener(@NonNull AnimatorListener animatorListener) {
        ArrayList<AnimatorListener> arrayList = this.extendListeners;
        if (arrayList != null) {
            arrayList.remove(animatorListener);
        }
    }

    public void hide() {
        hide(true);
    }

    public void hide(boolean z) {
        hide(true, z, null);
    }

    public void hide(@Nullable OnChangedListener onChangedListener) {
        hide(true, true, onChangedListener);
    }

    /* access modifiers changed from: private */
    public void hide(final boolean z, boolean z2, @Nullable final OnChangedListener onChangedListener) {
        if (!isOrWillBeHidden()) {
            Animator animator = this.currentShowHideAnimator;
            if (animator != null) {
                animator.cancel();
            }
            if (!z2 || !shouldAnimateVisibilityChange()) {
                internalSetVisibility(z ? 8 : 4, z);
                if (onChangedListener != null) {
                    onChangedListener.onHidden(this);
                }
            } else {
                AnimatorSet createAnimator = createAnimator(getCurrentHideMotionSpec());
                createAnimator.addListener(new AnimatorListenerAdapter() {
                    private boolean cancelled;

                    public void onAnimationStart(Animator animator) {
                        ExtendedFloatingActionButton.this.internalSetVisibility(0, z);
                        ExtendedFloatingActionButton.this.animState = 1;
                        ExtendedFloatingActionButton.this.currentShowHideAnimator = animator;
                        this.cancelled = false;
                    }

                    public void onAnimationCancel(Animator animator) {
                        this.cancelled = true;
                    }

                    public void onAnimationEnd(Animator animator) {
                        ExtendedFloatingActionButton.this.animState = 0;
                        ExtendedFloatingActionButton.this.currentShowHideAnimator = null;
                        if (!this.cancelled) {
                            ExtendedFloatingActionButton.this.internalSetVisibility(z ? 8 : 4, z);
                            OnChangedListener onChangedListener = onChangedListener;
                            if (onChangedListener != null) {
                                onChangedListener.onHidden(ExtendedFloatingActionButton.this);
                            }
                        }
                    }
                });
                ArrayList<AnimatorListener> arrayList = this.hideListeners;
                if (arrayList != null) {
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        createAnimator.addListener((AnimatorListener) it.next());
                    }
                }
                createAnimator.start();
            }
        }
    }

    public void show() {
        show(true);
    }

    public void show(boolean z) {
        show(true, z, null);
    }

    public void show(@Nullable OnChangedListener onChangedListener) {
        show(true, true, onChangedListener);
    }

    /* access modifiers changed from: private */
    public void show(final boolean z, boolean z2, @Nullable final OnChangedListener onChangedListener) {
        if (!isOrWillBeShown()) {
            Animator animator = this.currentShowHideAnimator;
            if (animator != null) {
                animator.cancel();
            }
            if (!z2 || !shouldAnimateVisibilityChange()) {
                internalSetVisibility(0, z);
                setAlpha(1.0f);
                setScaleY(1.0f);
                setScaleX(1.0f);
                if (onChangedListener != null) {
                    onChangedListener.onShown(this);
                }
            } else {
                AnimatorSet createAnimator = createAnimator(getCurrentShowMotionSpec());
                createAnimator.addListener(new AnimatorListenerAdapter() {
                    public void onAnimationStart(Animator animator) {
                        ExtendedFloatingActionButton.this.internalSetVisibility(0, z);
                        ExtendedFloatingActionButton.this.animState = 2;
                        ExtendedFloatingActionButton.this.currentShowHideAnimator = animator;
                    }

                    public void onAnimationEnd(Animator animator) {
                        ExtendedFloatingActionButton.this.animState = 0;
                        ExtendedFloatingActionButton.this.currentShowHideAnimator = null;
                        OnChangedListener onChangedListener = onChangedListener;
                        if (onChangedListener != null) {
                            onChangedListener.onShown(ExtendedFloatingActionButton.this);
                        }
                    }
                });
                ArrayList<AnimatorListener> arrayList = this.showListeners;
                if (arrayList != null) {
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        createAnimator.addListener((AnimatorListener) it.next());
                    }
                }
                createAnimator.start();
            }
        }
    }

    public void extend() {
        extend(true);
    }

    public void extend(boolean z) {
        setExtended(true, z, null);
    }

    public void extend(@Nullable OnChangedListener onChangedListener) {
        setExtended(true, true, onChangedListener);
    }

    public void shrink() {
        shrink(true);
    }

    public void shrink(boolean z) {
        setExtended(false, z, null);
    }

    public void shrink(@Nullable OnChangedListener onChangedListener) {
        setExtended(false, true, onChangedListener);
    }

    @Nullable
    public MotionSpec getShowMotionSpec() {
        return this.showMotionSpec;
    }

    public void setShowMotionSpec(@Nullable MotionSpec motionSpec) {
        this.showMotionSpec = motionSpec;
    }

    public void setShowMotionSpecResource(@AnimatorRes int i) {
        setShowMotionSpec(MotionSpec.createFromResource(getContext(), i));
    }

    @Nullable
    public MotionSpec getHideMotionSpec() {
        return this.hideMotionSpec;
    }

    public void setHideMotionSpec(@Nullable MotionSpec motionSpec) {
        this.hideMotionSpec = motionSpec;
    }

    public void setHideMotionSpecResource(@AnimatorRes int i) {
        setHideMotionSpec(MotionSpec.createFromResource(getContext(), i));
    }

    @Nullable
    public MotionSpec getExtendMotionSpec() {
        return this.extendMotionSpec;
    }

    public void setExtendMotionSpec(@Nullable MotionSpec motionSpec) {
        this.extendMotionSpec = motionSpec;
    }

    public void setExtendMotionSpecResource(@AnimatorRes int i) {
        setExtendMotionSpec(MotionSpec.createFromResource(getContext(), i));
    }

    @Nullable
    public MotionSpec getShrinkMotionSpec() {
        return this.shrinkMotionSpec;
    }

    public void setShrinkMotionSpec(@Nullable MotionSpec motionSpec) {
        this.shrinkMotionSpec = motionSpec;
    }

    public void setShrinkMotionSpecResource(@AnimatorRes int i) {
        setShrinkMotionSpec(MotionSpec.createFromResource(getContext(), i));
    }

    private void setExtended(final boolean z, boolean z2, @Nullable final OnChangedListener onChangedListener) {
        if (z != this.isExtended && getIcon() != null && !TextUtils.isEmpty(getText())) {
            this.isExtended = z;
            Animator animator = this.currentCollapseExpandAnimator;
            if (animator != null) {
                animator.cancel();
            }
            if (z2 && shouldAnimateVisibilityChange()) {
                measure(0, 0);
                AnimatorSet createShrinkExtendAnimator = createShrinkExtendAnimator(this.isExtended ? getCurrentExtendMotionSpec() : getCurrentShrinkMotionSpec(), !this.isExtended);
                createShrinkExtendAnimator.addListener(new AnimatorListenerAdapter() {
                    private boolean cancelled;

                    public void onAnimationStart(Animator animator) {
                        ExtendedFloatingActionButton.this.setHorizontallyScrolling(true);
                        ExtendedFloatingActionButton.this.currentCollapseExpandAnimator = animator;
                        this.cancelled = false;
                    }

                    public void onAnimationCancel(Animator animator) {
                        this.cancelled = true;
                    }

                    public void onAnimationEnd(Animator animator) {
                        ExtendedFloatingActionButton.this.setHorizontallyScrolling(false);
                        ExtendedFloatingActionButton.this.currentCollapseExpandAnimator = null;
                        if (!this.cancelled) {
                            OnChangedListener onChangedListener = onChangedListener;
                            if (onChangedListener != null) {
                                if (z) {
                                    onChangedListener.onExtended(ExtendedFloatingActionButton.this);
                                } else {
                                    onChangedListener.onShrunken(ExtendedFloatingActionButton.this);
                                }
                            }
                        }
                    }
                });
                ArrayList<AnimatorListener> arrayList = z ? this.extendListeners : this.shrinkListeners;
                if (arrayList != null) {
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        createShrinkExtendAnimator.addListener((AnimatorListener) it.next());
                    }
                }
                createShrinkExtendAnimator.start();
            } else if (z) {
                extendNow();
                if (onChangedListener != null) {
                    onChangedListener.onExtended(this);
                }
            } else {
                shrinkNow();
                if (onChangedListener != null) {
                    onChangedListener.onShrunken(this);
                }
            }
        }
    }

    private AnimatorSet createAnimator(@NonNull MotionSpec motionSpec) {
        ArrayList arrayList = new ArrayList();
        String str = "opacity";
        if (motionSpec.hasPropertyValues(str)) {
            arrayList.add(motionSpec.getAnimator(str, this, View.ALPHA));
        }
        String str2 = "scale";
        if (motionSpec.hasPropertyValues(str2)) {
            arrayList.add(motionSpec.getAnimator(str2, this, View.SCALE_Y));
            arrayList.add(motionSpec.getAnimator(str2, this, View.SCALE_X));
        }
        String str3 = "width";
        if (motionSpec.hasPropertyValues(str3)) {
            arrayList.add(motionSpec.getAnimator(str3, this, WIDTH));
        }
        String str4 = "height";
        if (motionSpec.hasPropertyValues(str4)) {
            arrayList.add(motionSpec.getAnimator(str4, this, HEIGHT));
        }
        String str5 = "cornerRadius";
        if (motionSpec.hasPropertyValues(str5) && !this.isUsingPillCorner) {
            arrayList.add(motionSpec.getAnimator(str5, this, CORNER_RADIUS));
        }
        AnimatorSet animatorSet = new AnimatorSet();
        AnimatorSetCompat.playTogether(animatorSet, arrayList);
        return animatorSet;
    }

    private AnimatorSet createShrinkExtendAnimator(@NonNull MotionSpec motionSpec, boolean z) {
        int collapsedSize = getCollapsedSize();
        String str = "width";
        if (motionSpec.hasPropertyValues(str)) {
            PropertyValuesHolder[] propertyValues = motionSpec.getPropertyValues(str);
            if (z) {
                propertyValues[0].setFloatValues(new float[]{(float) getMeasuredWidth(), (float) collapsedSize});
            } else {
                propertyValues[0].setFloatValues(new float[]{(float) getWidth(), (float) getMeasuredWidth()});
            }
            motionSpec.setPropertyValues(str, propertyValues);
        }
        String str2 = "height";
        if (motionSpec.hasPropertyValues(str2)) {
            PropertyValuesHolder[] propertyValues2 = motionSpec.getPropertyValues(str2);
            if (z) {
                propertyValues2[0].setFloatValues(new float[]{(float) getMeasuredHeight(), (float) collapsedSize});
            } else {
                propertyValues2[0].setFloatValues(new float[]{(float) getHeight(), (float) getMeasuredHeight()});
            }
            motionSpec.setPropertyValues(str2, propertyValues2);
        }
        return createAnimator(motionSpec);
    }

    private boolean isOrWillBeShown() {
        boolean z = false;
        if (getVisibility() != 0) {
            if (this.animState == 2) {
                z = true;
            }
            return z;
        }
        if (this.animState != 1) {
            z = true;
        }
        return z;
    }

    private boolean isOrWillBeHidden() {
        boolean z = false;
        if (getVisibility() == 0) {
            if (this.animState == 1) {
                z = true;
            }
            return z;
        }
        if (this.animState != 2) {
            z = true;
        }
        return z;
    }

    private boolean shouldAnimateVisibilityChange() {
        return ViewCompat.isLaidOut(this) && !isInEditMode();
    }

    private void shrinkNow() {
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams != null) {
            int collapsedSize = getCollapsedSize();
            layoutParams.width = collapsedSize;
            layoutParams.height = collapsedSize;
            requestLayout();
        }
    }

    private void extendNow() {
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams != null) {
            measure(0, 0);
            layoutParams.width = getMeasuredWidth();
            layoutParams.height = getMeasuredHeight();
            requestLayout();
        }
    }

    private MotionSpec getCurrentShowMotionSpec() {
        MotionSpec motionSpec = this.showMotionSpec;
        if (motionSpec != null) {
            return motionSpec;
        }
        if (this.defaultShowMotionSpec == null) {
            this.defaultShowMotionSpec = MotionSpec.createFromResource(getContext(), C1435R.animator.mtrl_extended_fab_show_motion_spec);
        }
        return (MotionSpec) Preconditions.checkNotNull(this.defaultShowMotionSpec);
    }

    private MotionSpec getCurrentHideMotionSpec() {
        MotionSpec motionSpec = this.hideMotionSpec;
        if (motionSpec != null) {
            return motionSpec;
        }
        if (this.defaultHideMotionSpec == null) {
            this.defaultHideMotionSpec = MotionSpec.createFromResource(getContext(), C1435R.animator.mtrl_extended_fab_hide_motion_spec);
        }
        return (MotionSpec) Preconditions.checkNotNull(this.defaultHideMotionSpec);
    }

    private MotionSpec getCurrentExtendMotionSpec() {
        MotionSpec motionSpec = this.extendMotionSpec;
        if (motionSpec != null) {
            return motionSpec;
        }
        if (this.defaultExtendMotionSpec == null) {
            this.defaultExtendMotionSpec = MotionSpec.createFromResource(getContext(), C1435R.animator.mtrl_extended_fab_extend_motion_spec);
        }
        return (MotionSpec) Preconditions.checkNotNull(this.defaultExtendMotionSpec);
    }

    private MotionSpec getCurrentShrinkMotionSpec() {
        MotionSpec motionSpec = this.shrinkMotionSpec;
        if (motionSpec != null) {
            return motionSpec;
        }
        if (this.defaultShrinkMotionSpec == null) {
            this.defaultShrinkMotionSpec = MotionSpec.createFromResource(getContext(), C1435R.animator.mtrl_extended_fab_shrink_motion_spec);
        }
        return (MotionSpec) Preconditions.checkNotNull(this.defaultShrinkMotionSpec);
    }

    private int getAdjustedRadius(int i) {
        return (i - 1) / 2;
    }

    private int getCollapsedSize() {
        return (Math.min(ViewCompat.getPaddingStart(this), ViewCompat.getPaddingEnd(this)) * 2) + getIconSize();
    }
}
