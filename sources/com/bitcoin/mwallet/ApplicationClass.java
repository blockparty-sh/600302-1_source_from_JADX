package com.bitcoin.mwallet;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import com.bitcoin.mwallet.core.locale.LocaleManager;
import com.bitcoin.mwallet.core.utils.MeasureKt;
import com.leanplum.LeanplumApplication;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.koin.core.context.GlobalContext;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 %2\u00020\u00012\u00020\u0002:\u0001%B\u0005¢\u0006\u0002\u0010\u0003J\u0012\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0014J\b\u0010\u0014\u001a\u0004\u0018\u00010\u0005J\u001a\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u00052\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J\u0010\u0010\u0019\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u0005H\u0016J\u0010\u0010\u001a\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u0005H\u0016J\u0010\u0010\u001b\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u0005H\u0016J\u0018\u0010\u001c\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u00052\u0006\u0010\u001d\u001a\u00020\u0018H\u0016J\u0010\u0010\u001e\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u0005H\u0016J\u0010\u0010\u001f\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u0005H\u0016J\u0010\u0010 \u001a\u00020\u00112\u0006\u0010!\u001a\u00020\"H\u0016J\b\u0010#\u001a\u00020\u0011H\u0016J\b\u0010$\u001a\u00020\u0011H\u0002R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u000bX.¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006&"}, mo37405d2 = {"Lcom/bitcoin/mwallet/ApplicationClass;", "Lcom/leanplum/LeanplumApplication;", "Landroid/app/Application$ActivityLifecycleCallbacks;", "()V", "currentActivity", "Landroid/app/Activity;", "getCurrentActivity", "()Landroid/app/Activity;", "setCurrentActivity", "(Landroid/app/Activity;)V", "localeManager", "Lcom/bitcoin/mwallet/core/locale/LocaleManager;", "getLocaleManager", "()Lcom/bitcoin/mwallet/core/locale/LocaleManager;", "setLocaleManager", "(Lcom/bitcoin/mwallet/core/locale/LocaleManager;)V", "attachBaseContext", "", "base", "Landroid/content/Context;", "getActivityContext", "onActivityCreated", "activity", "savedInstanceState", "Landroid/os/Bundle;", "onActivityDestroyed", "onActivityPaused", "onActivityResumed", "onActivitySaveInstanceState", "outState", "onActivityStarted", "onActivityStopped", "onConfigurationChanged", "newConfig", "Landroid/content/res/Configuration;", "onCreate", "setLocale", "Companion", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: ApplicationClass.kt */
public final class ApplicationClass extends LeanplumApplication implements ActivityLifecycleCallbacks {
    public static final Companion Companion = new Companion(null);
    /* access modifiers changed from: private */
    @Nullable
    public static Context currentApplication;
    @Nullable
    private Activity currentActivity;
    @NotNull
    public LocaleManager localeManager;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\t\u001a\u0004\u0018\u00010\u0004R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\n"}, mo37405d2 = {"Lcom/bitcoin/mwallet/ApplicationClass$Companion;", "", "()V", "currentApplication", "Landroid/content/Context;", "getCurrentApplication", "()Landroid/content/Context;", "setCurrentApplication", "(Landroid/content/Context;)V", "getCurrentApplicationContext", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: ApplicationClass.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Nullable
        public final Context getCurrentApplication() {
            return ApplicationClass.currentApplication;
        }

        public final void setCurrentApplication(@Nullable Context context) {
            ApplicationClass.currentApplication = context;
        }

        @Nullable
        public final Context getCurrentApplicationContext() {
            return getCurrentApplication();
        }
    }

    public void onActivityDestroyed(@NotNull Activity activity) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
    }

    public void onActivityPaused(@NotNull Activity activity) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
    }

    public void onActivitySaveInstanceState(@NotNull Activity activity, @NotNull Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        Intrinsics.checkParameterIsNotNull(bundle, "outState");
    }

    public void onActivityStopped(@NotNull Activity activity) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
    }

    public void onActivityCreated(@NotNull Activity activity, @Nullable Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        this.currentActivity = activity;
    }

    public void onActivityStarted(@NotNull Activity activity) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        this.currentActivity = activity;
    }

    public void onActivityResumed(@NotNull Activity activity) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        this.currentActivity = activity;
    }

    @NotNull
    public final LocaleManager getLocaleManager() {
        LocaleManager localeManager2 = this.localeManager;
        if (localeManager2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("localeManager");
        }
        return localeManager2;
    }

    public final void setLocaleManager(@NotNull LocaleManager localeManager2) {
        Intrinsics.checkParameterIsNotNull(localeManager2, "<set-?>");
        this.localeManager = localeManager2;
    }

    @Nullable
    public final Activity getCurrentActivity() {
        return this.currentActivity;
    }

    public final void setCurrentActivity(@Nullable Activity activity) {
        this.currentActivity = activity;
    }

    public void onCreate() {
        super.onCreate();
        currentApplication = getApplicationContext();
        registerActivityLifecycleCallbacks(this);
        setLocale();
        if (GlobalContext.getOrNull() == null) {
            MeasureKt.logDuration("startKoin", new ApplicationClass$onCreate$1(this));
        }
    }

    /* access modifiers changed from: protected */
    public void attachBaseContext(@Nullable Context context) {
        if (context == null) {
            Intrinsics.throwNpe();
        }
        super.attachBaseContext(new LocaleManager(context).setLocale(context));
    }

    public void onConfigurationChanged(@NotNull Configuration configuration) {
        Intrinsics.checkParameterIsNotNull(configuration, "newConfig");
        super.onConfigurationChanged(configuration);
        Context context = this;
        new LocaleManager(context).setLocale(context);
    }

    private final void setLocale() {
        Context context = this;
        new LocaleManager(context).setLocale(context);
    }

    @Nullable
    public final Activity getActivityContext() {
        return this.currentActivity;
    }
}
