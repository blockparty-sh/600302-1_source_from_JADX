package com.leanplum;

import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.res.Resources;
import android.os.Bundle;
import com.leanplum.annotations.Parser;
import com.leanplum.callbacks.PostponableAction;
import com.leanplum.internal.ActionManager;
import com.leanplum.internal.LeanplumInternal;
import com.leanplum.internal.LeanplumUIEditorWrapper;
import com.leanplum.internal.OsHandler;
import com.leanplum.internal.Util;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class LeanplumActivityHelper {
    static Activity currentActivity;
    private static Set<Class> ignoredActivityClasses;
    static boolean isActivityPaused;
    private static final Queue<Runnable> pendingActions = new LinkedList();
    private static boolean registeredCallbacks;
    private static final Runnable runPendingActionsRunnable = new Runnable() {
        public void run() {
            LeanplumActivityHelper.runPendingActions();
        }
    };
    private final Activity activity;
    private LeanplumInflater inflater;
    private LeanplumResources res;

    public LeanplumActivityHelper(Activity activity2) {
        this.activity = activity2;
        Leanplum.setApplicationContext(activity2.getApplicationContext());
        Parser.parseVariables(activity2);
    }

    public static Activity getCurrentActivity() {
        return currentActivity;
    }

    public static boolean isActivityPaused() {
        return isActivityPaused;
    }

    public static void enableLifecycleCallbacks(Application application) {
        Leanplum.setApplicationContext(application.getApplicationContext());
        application.registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            public void onActivityCreated(Activity activity, Bundle bundle) {
            }

            public void onActivityDestroyed(Activity activity) {
            }

            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            }

            public void onActivityStarted(Activity activity) {
            }

            public void onActivityStopped(Activity activity) {
                try {
                    LeanplumActivityHelper.onStop(activity);
                } catch (Throwable th) {
                    Util.handleException(th);
                }
            }

            public void onActivityResumed(final Activity activity) {
                try {
                    if (Leanplum.isInterfaceEditingEnabled()) {
                        OsHandler.getInstance().post(new Runnable() {
                            public void run() {
                                LeanplumUIEditorWrapper.getInstance().applyInterfaceEdits(activity);
                            }
                        });
                    }
                    LeanplumActivityHelper.onResume(activity);
                    if (Leanplum.isScreenTrackingEnabled()) {
                        Leanplum.advanceTo(activity.getLocalClassName());
                    }
                } catch (Throwable th) {
                    Util.handleException(th);
                }
            }

            public void onActivityPaused(Activity activity) {
                try {
                    LeanplumActivityHelper.onPause(activity);
                } catch (Throwable th) {
                    Util.handleException(th);
                }
            }
        });
        registeredCallbacks = true;
    }

    public LeanplumResources getLeanplumResources() {
        return getLeanplumResources(null);
    }

    public LeanplumResources getLeanplumResources(Resources resources) {
        LeanplumResources leanplumResources = this.res;
        if (leanplumResources != null) {
            return leanplumResources;
        }
        if (resources == null) {
            resources = this.activity.getResources();
        }
        if (resources instanceof LeanplumResources) {
            return (LeanplumResources) resources;
        }
        this.res = new LeanplumResources(resources);
        return this.res;
    }

    public void setContentView(int i) {
        if (this.inflater == null) {
            this.inflater = LeanplumInflater.from(this.activity);
        }
        this.activity.setContentView(this.inflater.inflate(i));
    }

    /* access modifiers changed from: private */
    public static void onPause(Activity activity2) {
        isActivityPaused = true;
    }

    public void onPause() {
        try {
            if (!registeredCallbacks) {
                onPause(this.activity);
            }
        } catch (Throwable th) {
            Util.handleException(th);
        }
    }

    /* access modifiers changed from: private */
    public static void onResume(Activity activity2) {
        isActivityPaused = false;
        currentActivity = activity2;
        if (LeanplumInternal.isPaused() || LeanplumInternal.hasStartedInBackground()) {
            Leanplum.resume();
            LocationManager locationManager = ActionManager.getLocationManager();
            if (locationManager != null) {
                locationManager.updateGeofencing();
                locationManager.updateUserLocation();
            }
        }
        LeanplumInternal.addStartIssuedHandler(runPendingActionsRunnable);
    }

    public void onResume() {
        try {
            if (!registeredCallbacks) {
                onResume(this.activity);
            }
        } catch (Throwable th) {
            Util.handleException(th);
        }
    }

    /* access modifiers changed from: private */
    public static void onStop(Activity activity2) {
        if (isActivityPaused) {
            Leanplum.pause();
            LocationManager locationManager = ActionManager.getLocationManager();
            if (locationManager != null) {
                locationManager.updateGeofencing();
            }
        }
        Activity activity3 = currentActivity;
        if (activity3 != null && activity3.equals(activity2)) {
            currentActivity = null;
        }
    }

    public void onStop() {
        try {
            if (!registeredCallbacks) {
                onStop(this.activity);
            }
        } catch (Throwable th) {
            Util.handleException(th);
        }
    }

    public static void queueActionUponActive(Runnable runnable) {
        try {
            if (currentActivity == null || currentActivity.isFinishing() || isActivityPaused || ((runnable instanceof PostponableAction) && isActivityClassIgnored(currentActivity))) {
                synchronized (pendingActions) {
                    pendingActions.add(runnable);
                }
                return;
            }
            runnable.run();
        } catch (Throwable th) {
            Util.handleException(th);
        }
    }

    /* access modifiers changed from: private */
    public static void runPendingActions() {
        LinkedList<Runnable> linkedList;
        if (!isActivityPaused && currentActivity != null) {
            synchronized (pendingActions) {
                linkedList = new LinkedList<>(pendingActions);
                pendingActions.clear();
            }
            for (Runnable runnable : linkedList) {
                if (!(runnable instanceof PostponableAction) || !isActivityClassIgnored(currentActivity)) {
                    runnable.run();
                } else {
                    pendingActions.add(runnable);
                }
            }
        }
    }

    private static boolean isActivityClassIgnored(Activity activity2) {
        Set<Class> set = ignoredActivityClasses;
        return set != null && set.contains(activity2.getClass());
    }

    public static void deferMessagesForActivities(Class... clsArr) {
        if (clsArr != null && clsArr.length != 0) {
            if (ignoredActivityClasses == null) {
                ignoredActivityClasses = new HashSet(clsArr.length);
            }
            Collections.addAll(ignoredActivityClasses, clsArr);
        }
    }
}
