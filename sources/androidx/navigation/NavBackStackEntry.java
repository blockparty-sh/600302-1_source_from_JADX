package androidx.navigation;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import java.util.UUID;

final class NavBackStackEntry implements ViewModelStoreOwner {
    private final Bundle mArgs;
    private final NavDestination mDestination;
    @NonNull
    final UUID mId;
    private NavControllerViewModel mNavControllerViewModel;

    NavBackStackEntry(@NonNull NavDestination navDestination, @Nullable Bundle bundle, @Nullable NavControllerViewModel navControllerViewModel) {
        this(UUID.randomUUID(), navDestination, bundle, navControllerViewModel);
    }

    NavBackStackEntry(@NonNull UUID uuid, @NonNull NavDestination navDestination, @Nullable Bundle bundle, @Nullable NavControllerViewModel navControllerViewModel) {
        this.mId = uuid;
        this.mDestination = navDestination;
        this.mArgs = bundle;
        this.mNavControllerViewModel = navControllerViewModel;
    }

    @NonNull
    public NavDestination getDestination() {
        return this.mDestination;
    }

    @Nullable
    public Bundle getArguments() {
        return this.mArgs;
    }

    /* access modifiers changed from: 0000 */
    public void setNavControllerViewModel(@NonNull NavControllerViewModel navControllerViewModel) {
        this.mNavControllerViewModel = navControllerViewModel;
    }

    @NonNull
    public ViewModelStore getViewModelStore() {
        return this.mNavControllerViewModel.getViewModelStore(this.mId);
    }
}
