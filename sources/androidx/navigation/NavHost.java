package androidx.navigation;

import androidx.annotation.NonNull;

public interface NavHost {
    @NonNull
    NavController getNavController();
}
