package androidx.navigation.p005ui;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.RestrictTo.Scope;
import androidx.annotation.StringRes;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import java.lang.ref.WeakReference;

@RestrictTo({Scope.LIBRARY})
/* renamed from: androidx.navigation.ui.CollapsingToolbarOnDestinationChangedListener */
class CollapsingToolbarOnDestinationChangedListener extends AbstractAppBarOnDestinationChangedListener {
    private final WeakReference<CollapsingToolbarLayout> mCollapsingToolbarLayoutWeakReference;
    private final WeakReference<Toolbar> mToolbarWeakReference;

    CollapsingToolbarOnDestinationChangedListener(@NonNull CollapsingToolbarLayout collapsingToolbarLayout, @NonNull Toolbar toolbar, @NonNull AppBarConfiguration appBarConfiguration) {
        super(collapsingToolbarLayout.getContext(), appBarConfiguration);
        this.mCollapsingToolbarLayoutWeakReference = new WeakReference<>(collapsingToolbarLayout);
        this.mToolbarWeakReference = new WeakReference<>(toolbar);
    }

    public void onDestinationChanged(@NonNull NavController navController, @NonNull NavDestination navDestination, @Nullable Bundle bundle) {
        Toolbar toolbar = (Toolbar) this.mToolbarWeakReference.get();
        if (((CollapsingToolbarLayout) this.mCollapsingToolbarLayoutWeakReference.get()) == null || toolbar == null) {
            navController.removeOnDestinationChangedListener(this);
        } else {
            super.onDestinationChanged(navController, navDestination, bundle);
        }
    }

    /* access modifiers changed from: protected */
    public void setTitle(CharSequence charSequence) {
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) this.mCollapsingToolbarLayoutWeakReference.get();
        if (collapsingToolbarLayout != null) {
            collapsingToolbarLayout.setTitle(charSequence);
        }
    }

    /* access modifiers changed from: protected */
    public void setNavigationIcon(Drawable drawable, @StringRes int i) {
        Toolbar toolbar = (Toolbar) this.mToolbarWeakReference.get();
        if (toolbar != null) {
            toolbar.setNavigationIcon(drawable);
            toolbar.setNavigationContentDescription(i);
        }
    }
}
