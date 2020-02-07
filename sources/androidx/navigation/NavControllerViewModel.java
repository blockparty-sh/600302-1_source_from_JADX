package androidx.navigation;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProvider.Factory;
import androidx.lifecycle.ViewModelStore;
import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;

class NavControllerViewModel extends ViewModel {
    private static final Factory FACTORY = new Factory() {
        @NonNull
        public <T extends ViewModel> T create(@NonNull Class<T> cls) {
            return new NavControllerViewModel();
        }
    };
    private final HashMap<UUID, ViewModelStore> mViewModelStores = new HashMap<>();

    NavControllerViewModel() {
    }

    @NonNull
    static NavControllerViewModel getInstance(ViewModelStore viewModelStore) {
        return (NavControllerViewModel) new ViewModelProvider(viewModelStore, FACTORY).get(NavControllerViewModel.class);
    }

    /* access modifiers changed from: 0000 */
    public void clear(@NonNull UUID uuid) {
        ViewModelStore viewModelStore = (ViewModelStore) this.mViewModelStores.remove(uuid);
        if (viewModelStore != null) {
            viewModelStore.clear();
        }
    }

    /* access modifiers changed from: protected */
    public void onCleared() {
        for (UUID clear : this.mViewModelStores.keySet()) {
            clear(clear);
        }
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    public ViewModelStore getViewModelStore(@NonNull UUID uuid) {
        ViewModelStore viewModelStore = (ViewModelStore) this.mViewModelStores.get(uuid);
        if (viewModelStore != null) {
            return viewModelStore;
        }
        ViewModelStore viewModelStore2 = new ViewModelStore();
        this.mViewModelStores.put(uuid, viewModelStore2);
        return viewModelStore2;
    }

    @NonNull
    public String toString() {
        StringBuilder sb = new StringBuilder("NavControllerViewModel{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append("} ViewModelStores (");
        Iterator it = this.mViewModelStores.keySet().iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(')');
        return sb.toString();
    }
}
