package androidx.navigation;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.Navigator.Extras;
import androidx.navigation.Navigator.Name;

@Name("NoOp")
class NoOpNavigator extends Navigator<NavDestination> {
    @Nullable
    public NavDestination navigate(@NonNull NavDestination navDestination, @Nullable Bundle bundle, @Nullable NavOptions navOptions, @Nullable Extras extras) {
        return navDestination;
    }

    public boolean popBackStack() {
        return true;
    }

    NoOpNavigator() {
    }

    @NonNull
    public NavDestination createDestination() {
        return new NavDestination((Navigator<? extends NavDestination>) this);
    }
}
