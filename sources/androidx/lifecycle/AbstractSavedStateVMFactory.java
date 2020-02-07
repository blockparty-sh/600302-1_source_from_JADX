package androidx.lifecycle;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.RestrictTo.Scope;
import androidx.lifecycle.Lifecycle.Event;
import androidx.savedstate.SavedStateRegistry;
import androidx.savedstate.SavedStateRegistry.AutoRecreated;
import androidx.savedstate.SavedStateRegistryOwner;

public abstract class AbstractSavedStateVMFactory extends KeyedFactory {
    static final String TAG_SAVED_STATE_HANDLE_CONTROLLER = "androidx.lifecycle.savedstate.vm.tag";
    private final Bundle mDefaultArgs;
    private final Lifecycle mLifecycle;
    private final SavedStateRegistry mSavedStateRegistry;

    static final class OnRecreation implements AutoRecreated {
        OnRecreation() {
        }

        public void onRecreated(@NonNull SavedStateRegistryOwner savedStateRegistryOwner) {
            if (savedStateRegistryOwner instanceof ViewModelStoreOwner) {
                ViewModelStore viewModelStore = ((ViewModelStoreOwner) savedStateRegistryOwner).getViewModelStore();
                SavedStateRegistry savedStateRegistry = savedStateRegistryOwner.getSavedStateRegistry();
                for (String str : viewModelStore.keys()) {
                    SavedStateHandleController savedStateHandleController = (SavedStateHandleController) viewModelStore.get(str).getTag(AbstractSavedStateVMFactory.TAG_SAVED_STATE_HANDLE_CONTROLLER);
                    if (savedStateHandleController != null && !savedStateHandleController.isAttached()) {
                        savedStateHandleController.attachToLifecycle(savedStateRegistryOwner.getSavedStateRegistry(), savedStateRegistryOwner.getLifecycle());
                    }
                }
                if (!viewModelStore.keys().isEmpty()) {
                    savedStateRegistry.runOnNextRecreation(OnRecreation.class);
                    return;
                }
                return;
            }
            throw new IllegalStateException("Internal error: OnRecreation should be registered only on componentsthat implement ViewModelStoreOwner");
        }
    }

    static final class SavedStateHandleController implements LifecycleEventObserver {
        private final SavedStateHandle mHandle;
        boolean mIsAttached = false;
        private final String mKey;

        SavedStateHandleController(String str, SavedStateHandle savedStateHandle) {
            this.mKey = str;
            this.mHandle = savedStateHandle;
        }

        /* access modifiers changed from: 0000 */
        public boolean isAttached() {
            return this.mIsAttached;
        }

        /* access modifiers changed from: 0000 */
        public void attachToLifecycle(SavedStateRegistry savedStateRegistry, Lifecycle lifecycle) {
            if (!this.mIsAttached) {
                this.mIsAttached = true;
                lifecycle.addObserver(this);
                savedStateRegistry.registerSavedStateProvider(this.mKey, this.mHandle.savedStateProvider());
                return;
            }
            throw new IllegalStateException("Already attached to lifecycleOwner");
        }

        public void onStateChanged(@NonNull LifecycleOwner lifecycleOwner, @NonNull Event event) {
            if (event == Event.ON_DESTROY) {
                this.mIsAttached = false;
                lifecycleOwner.getLifecycle().removeObserver(this);
            }
        }
    }

    /* access modifiers changed from: protected */
    @NonNull
    public abstract <T extends ViewModel> T create(@NonNull String str, @NonNull Class<T> cls, @NonNull SavedStateHandle savedStateHandle);

    public AbstractSavedStateVMFactory(@NonNull SavedStateRegistryOwner savedStateRegistryOwner, @Nullable Bundle bundle) {
        this.mSavedStateRegistry = savedStateRegistryOwner.getSavedStateRegistry();
        this.mLifecycle = savedStateRegistryOwner.getLifecycle();
        this.mDefaultArgs = bundle;
    }

    @NonNull
    @RestrictTo({Scope.LIBRARY_GROUP})
    public final <T extends ViewModel> T create(@NonNull String str, @NonNull Class<T> cls) {
        SavedStateHandle createHandle = SavedStateHandle.createHandle(this.mSavedStateRegistry.consumeRestoredStateForKey(str), this.mDefaultArgs);
        SavedStateHandleController savedStateHandleController = new SavedStateHandleController(str, createHandle);
        savedStateHandleController.attachToLifecycle(this.mSavedStateRegistry, this.mLifecycle);
        T create = create(str, cls, createHandle);
        create.setTagIfAbsent(TAG_SAVED_STATE_HANDLE_CONTROLLER, savedStateHandleController);
        this.mSavedStateRegistry.runOnNextRecreation(OnRecreation.class);
        return create;
    }

    @NonNull
    public final <T extends ViewModel> T create(@NonNull Class<T> cls) {
        String canonicalName = cls.getCanonicalName();
        if (canonicalName != null) {
            return create(canonicalName, cls);
        }
        throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
    }
}
