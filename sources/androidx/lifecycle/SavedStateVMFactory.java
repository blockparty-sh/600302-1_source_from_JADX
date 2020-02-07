package androidx.lifecycle;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory;
import androidx.savedstate.SavedStateRegistryOwner;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public final class SavedStateVMFactory extends AbstractSavedStateVMFactory {
    private static final Class<?>[] ANDROID_VIEWMODEL_SIGNATURE = {Application.class, SavedStateHandle.class};
    private static final Class<?>[] VIEWMODEL_SIGNATURE = {SavedStateHandle.class};
    private final Application mApplication;
    private final AndroidViewModelFactory mFactory;

    public SavedStateVMFactory(@NonNull Fragment fragment) {
        this(fragment, (Bundle) null);
    }

    public SavedStateVMFactory(@NonNull Fragment fragment, @Nullable Bundle bundle) {
        this(checkApplication(checkActivity(fragment)), fragment, bundle);
    }

    public SavedStateVMFactory(@NonNull FragmentActivity fragmentActivity) {
        this(fragmentActivity, (Bundle) null);
    }

    public SavedStateVMFactory(@NonNull FragmentActivity fragmentActivity, @Nullable Bundle bundle) {
        this(checkApplication(fragmentActivity), fragmentActivity, bundle);
    }

    public SavedStateVMFactory(@NonNull Application application, @NonNull SavedStateRegistryOwner savedStateRegistryOwner, @Nullable Bundle bundle) {
        super(savedStateRegistryOwner, bundle);
        this.mApplication = application;
        this.mFactory = AndroidViewModelFactory.getInstance(application);
    }

    /* access modifiers changed from: protected */
    @NonNull
    public <T extends ViewModel> T create(@NonNull String str, @NonNull Class<T> cls, @NonNull SavedStateHandle savedStateHandle) {
        Constructor constructor;
        boolean isAssignableFrom = AndroidViewModel.class.isAssignableFrom(cls);
        if (isAssignableFrom) {
            constructor = findMatchingConstructor(cls, ANDROID_VIEWMODEL_SIGNATURE);
        } else {
            constructor = findMatchingConstructor(cls, VIEWMODEL_SIGNATURE);
        }
        if (constructor == null) {
            return this.mFactory.create(cls);
        }
        if (isAssignableFrom) {
            try {
                return (ViewModel) constructor.newInstance(new Object[]{this.mApplication, savedStateHandle});
            } catch (IllegalAccessException e) {
                StringBuilder sb = new StringBuilder();
                sb.append("Failed to access ");
                sb.append(cls);
                throw new RuntimeException(sb.toString(), e);
            } catch (InstantiationException e2) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("A ");
                sb2.append(cls);
                sb2.append(" cannot be instantiated.");
                throw new RuntimeException(sb2.toString(), e2);
            } catch (InvocationTargetException e3) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append("An exception happened in constructor of ");
                sb3.append(cls);
                throw new RuntimeException(sb3.toString(), e3.getCause());
            }
        } else {
            return (ViewModel) constructor.newInstance(new Object[]{savedStateHandle});
        }
    }

    private static <T> Constructor<T> findMatchingConstructor(Class<T> cls, Class<?>[] clsArr) {
        Constructor<T>[] constructors;
        for (Constructor<T> constructor : cls.getConstructors()) {
            if (Arrays.equals(clsArr, constructor.getParameterTypes())) {
                return constructor;
            }
        }
        return null;
    }

    private static Application checkApplication(Activity activity) {
        Application application = activity.getApplication();
        if (application != null) {
            return application;
        }
        throw new IllegalStateException("Your activity/fragment is not yet attached to Application. You can't request ViewModelsWithStateFactory before onCreate call.");
    }

    private static Activity checkActivity(Fragment fragment) {
        FragmentActivity activity = fragment.getActivity();
        if (activity != null) {
            return activity;
        }
        throw new IllegalStateException("Can't create ViewModelsWithStateFactory for detached fragment");
    }
}
