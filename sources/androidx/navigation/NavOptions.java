package androidx.navigation;

import androidx.annotation.AnimRes;
import androidx.annotation.AnimatorRes;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;

public final class NavOptions {
    @AnimRes
    @AnimatorRes
    private int mEnterAnim;
    @AnimRes
    @AnimatorRes
    private int mExitAnim;
    @AnimRes
    @AnimatorRes
    private int mPopEnterAnim;
    @AnimRes
    @AnimatorRes
    private int mPopExitAnim;
    @IdRes
    private int mPopUpTo;
    private boolean mPopUpToInclusive;
    private boolean mSingleTop;

    public static final class Builder {
        @AnimRes
        @AnimatorRes
        int mEnterAnim = -1;
        @AnimRes
        @AnimatorRes
        int mExitAnim = -1;
        @AnimRes
        @AnimatorRes
        int mPopEnterAnim = -1;
        @AnimRes
        @AnimatorRes
        int mPopExitAnim = -1;
        @IdRes
        int mPopUpTo = -1;
        boolean mPopUpToInclusive;
        boolean mSingleTop;

        @NonNull
        public Builder setLaunchSingleTop(boolean z) {
            this.mSingleTop = z;
            return this;
        }

        @NonNull
        public Builder setPopUpTo(@IdRes int i, boolean z) {
            this.mPopUpTo = i;
            this.mPopUpToInclusive = z;
            return this;
        }

        @NonNull
        public Builder setEnterAnim(@AnimRes @AnimatorRes int i) {
            this.mEnterAnim = i;
            return this;
        }

        @NonNull
        public Builder setExitAnim(@AnimRes @AnimatorRes int i) {
            this.mExitAnim = i;
            return this;
        }

        @NonNull
        public Builder setPopEnterAnim(@AnimRes @AnimatorRes int i) {
            this.mPopEnterAnim = i;
            return this;
        }

        @NonNull
        public Builder setPopExitAnim(@AnimRes @AnimatorRes int i) {
            this.mPopExitAnim = i;
            return this;
        }

        @NonNull
        public NavOptions build() {
            NavOptions navOptions = new NavOptions(this.mSingleTop, this.mPopUpTo, this.mPopUpToInclusive, this.mEnterAnim, this.mExitAnim, this.mPopEnterAnim, this.mPopExitAnim);
            return navOptions;
        }
    }

    NavOptions(boolean z, @IdRes int i, boolean z2, @AnimRes @AnimatorRes int i2, @AnimRes @AnimatorRes int i3, @AnimRes @AnimatorRes int i4, @AnimRes @AnimatorRes int i5) {
        this.mSingleTop = z;
        this.mPopUpTo = i;
        this.mPopUpToInclusive = z2;
        this.mEnterAnim = i2;
        this.mExitAnim = i3;
        this.mPopEnterAnim = i4;
        this.mPopExitAnim = i5;
    }

    public boolean shouldLaunchSingleTop() {
        return this.mSingleTop;
    }

    @IdRes
    public int getPopUpTo() {
        return this.mPopUpTo;
    }

    public boolean isPopUpToInclusive() {
        return this.mPopUpToInclusive;
    }

    @AnimRes
    @AnimatorRes
    public int getEnterAnim() {
        return this.mEnterAnim;
    }

    @AnimRes
    @AnimatorRes
    public int getExitAnim() {
        return this.mExitAnim;
    }

    @AnimRes
    @AnimatorRes
    public int getPopEnterAnim() {
        return this.mPopEnterAnim;
    }

    @AnimRes
    @AnimatorRes
    public int getPopExitAnim() {
        return this.mPopExitAnim;
    }
}
