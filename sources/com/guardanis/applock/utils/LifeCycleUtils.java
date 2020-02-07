package com.guardanis.applock.utils;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;
import java.lang.ref.WeakReference;

public class LifeCycleUtils {

    public static class AppLockActivityLifeCycleCallbacks implements ActivityLifecycleCallbacks {
        protected WeakReference<Delegate> delegate;
        protected WeakReference<Activity> openedActivity;

        public interface Delegate {
            void onActivityPaused();

            void onActivityResumed();
        }

        public void onActivityCreated(Activity activity, Bundle bundle) {
        }

        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        public void onActivityStarted(Activity activity) {
        }

        public void onActivityStopped(Activity activity) {
        }

        public AppLockActivityLifeCycleCallbacks(Activity activity, Delegate delegate2) {
            this.openedActivity = new WeakReference<>(activity);
            this.delegate = new WeakReference<>(delegate2);
        }

        public void onActivityResumed(Activity activity) {
            Activity activity2 = (Activity) this.openedActivity.get();
            Delegate delegate2 = (Delegate) this.delegate.get();
            if (activity2 != null && delegate2 != null && activity2 == activity) {
                delegate2.onActivityResumed();
            }
        }

        public void onActivityPaused(Activity activity) {
            Activity activity2 = (Activity) this.openedActivity.get();
            Delegate delegate2 = (Delegate) this.delegate.get();
            if (activity2 != null && delegate2 != null && activity2 == activity) {
                delegate2.onActivityPaused();
            }
        }

        public void onActivityDestroyed(Activity activity) {
            Activity activity2 = (Activity) this.openedActivity.get();
            if (activity2 != null && activity2 == activity) {
                activity2.getApplication().unregisterActivityLifecycleCallbacks(this);
            }
        }
    }

    public static AppLockActivityLifeCycleCallbacks attach(Activity activity, Delegate delegate) {
        AppLockActivityLifeCycleCallbacks appLockActivityLifeCycleCallbacks = new AppLockActivityLifeCycleCallbacks(activity, delegate);
        activity.getApplication().registerActivityLifecycleCallbacks(appLockActivityLifeCycleCallbacks);
        return appLockActivityLifeCycleCallbacks;
    }
}
