package androidx.navigation;

import android.content.Context;
import androidx.activity.OnBackPressedDispatcher;
import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelStore;

public final class NavHostController extends NavController {
    public NavHostController(@NonNull Context context) {
        super(context);
    }

    public void setLifecycleOwner(@NonNull LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
    }

    public void setOnBackPressedDispatcher(@NonNull OnBackPressedDispatcher onBackPressedDispatcher) {
        super.setOnBackPressedDispatcher(onBackPressedDispatcher);
    }

    public void enableOnBackPressed(boolean z) {
        super.enableOnBackPressed(z);
    }

    public void setViewModelStore(@NonNull ViewModelStore viewModelStore) {
        super.setViewModelStore(viewModelStore);
    }
}
