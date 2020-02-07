package androidx.lifecycle;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle.Event;

class ReflectiveGenericLifecycleObserver implements LifecycleEventObserver {
    private final CallbackInfo mInfo = ClassesInfoCache.sInstance.getInfo(this.mWrapped.getClass());
    private final Object mWrapped;

    ReflectiveGenericLifecycleObserver(Object obj) {
        this.mWrapped = obj;
    }

    public void onStateChanged(@NonNull LifecycleOwner lifecycleOwner, @NonNull Event event) {
        this.mInfo.invokeCallbacks(lifecycleOwner, event, this.mWrapped);
    }
}
