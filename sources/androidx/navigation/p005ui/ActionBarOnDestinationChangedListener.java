package androidx.navigation.p005ui;

import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.annotation.RestrictTo.Scope;
import androidx.annotation.StringRes;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

@RestrictTo({Scope.LIBRARY})
/* renamed from: androidx.navigation.ui.ActionBarOnDestinationChangedListener */
class ActionBarOnDestinationChangedListener extends AbstractAppBarOnDestinationChangedListener {
    private final AppCompatActivity mActivity;

    ActionBarOnDestinationChangedListener(@NonNull AppCompatActivity appCompatActivity, @NonNull AppBarConfiguration appBarConfiguration) {
        super(appCompatActivity.getDrawerToggleDelegate().getActionBarThemedContext(), appBarConfiguration);
        this.mActivity = appCompatActivity;
    }

    /* access modifiers changed from: protected */
    public void setTitle(CharSequence charSequence) {
        this.mActivity.getSupportActionBar().setTitle(charSequence);
    }

    /* access modifiers changed from: protected */
    public void setNavigationIcon(Drawable drawable, @StringRes int i) {
        ActionBar supportActionBar = this.mActivity.getSupportActionBar();
        if (drawable == null) {
            supportActionBar.setDisplayHomeAsUpEnabled(false);
            return;
        }
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        this.mActivity.getDrawerToggleDelegate().setActionBarUpIndicator(drawable, i);
    }
}
