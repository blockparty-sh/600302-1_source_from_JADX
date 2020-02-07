package com.bitcoin.mwallet;

import android.app.Dialog;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;
import androidx.biometric.BiometricPrompt.AuthenticationCallback;
import androidx.biometric.BiometricPrompt.PromptInfo;
import androidx.biometric.BiometricPrompt.PromptInfo.Builder;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LiveData;
import androidx.navigation.NavController;
import com.bitcoin.mwallet.core.interactors.BiometricsInteractor;
import com.bitcoin.mwallet.core.locale.LocaleManager;
import com.bitcoin.mwallet.core.preferences.ApplicationPreferences;
import com.bitcoin.mwallet.core.preferences.SharedPreference;
import com.bitcoin.mwallet.core.qrscan.PermissionsReceiver;
import com.bitcoin.mwallet.core.services.AnalyticsService;
import com.bitcoin.mwallet.core.services.WalletRefresherTemp;
import com.bitcoin.mwallet.core.services.eventstream.EventStreamHandler;
import com.bitcoin.mwallet.core.workers.WorkManagerGlobalFactory;
import com.example.android.navigationadvancedsample.NavigationExtensionsKt;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.guardanis.applock.AppLock;
import com.guardanis.applock.activities.LockableAppCompatActivity;
import com.guardanis.applock.dialogs.UnlockDialogBuilder;
import com.microsoft.appcenter.AppCenter;
import com.microsoft.appcenter.analytics.Analytics;
import com.microsoft.appcenter.crashes.Crashes;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.koin.core.Koin;
import org.koin.core.KoinComponent;
import org.koin.core.KoinComponent.DefaultImpls;
import org.koin.core.qualifier.Qualifier;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0012\u00106\u001a\u0002072\b\u00108\u001a\u0004\u0018\u000109H\u0014J\n\u0010:\u001a\u0004\u0018\u00010;H\u0002J\u0012\u0010<\u001a\u0002072\b\u0010=\u001a\u0004\u0018\u00010>H\u0014J\b\u0010?\u001a\u000207H\u0014J-\u0010@\u001a\u0002072\u0006\u0010A\u001a\u00020B2\u000e\u0010C\u001a\n\u0012\u0006\b\u0001\u0012\u00020E0D2\u0006\u0010F\u001a\u00020GH\u0016¢\u0006\u0002\u0010HJ\b\u0010I\u001a\u00020\u0005H\u0016J\u0006\u0010J\u001a\u000207J\b\u0010K\u001a\u000207H\u0002J\u0010\u0010L\u001a\u0002072\u0006\u0010M\u001a\u00020NH\u0002J\u0006\u0010O\u001a\u000207R\u001a\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\u0007\"\u0004\b\f\u0010\tR\u001a\u0010\r\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u0007\"\u0004\b\u000f\u0010\tR\u001a\u0010\u0010\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0007\"\u0004\b\u0012\u0010\tR\u001a\u0010\u0013\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0007\"\u0004\b\u0015\u0010\tR\u001a\u0010\u0016\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0007\"\u0004\b\u0018\u0010\tR\u001b\u0010\u0019\u001a\u00020\u001a8FX\u0002¢\u0006\f\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001b\u0010\u001cR\u001b\u0010\u001f\u001a\u00020 8FX\u0002¢\u0006\f\n\u0004\b#\u0010\u001e\u001a\u0004\b!\u0010\"R\u0016\u0010$\u001a\n\u0012\u0004\u0012\u00020&\u0018\u00010%X\u000e¢\u0006\u0002\n\u0000R\u001b\u0010'\u001a\u00020(8FX\u0002¢\u0006\f\n\u0004\b+\u0010\u001e\u001a\u0004\b)\u0010*R\u001b\u0010,\u001a\u00020-8FX\u0002¢\u0006\f\n\u0004\b0\u0010\u001e\u001a\u0004\b.\u0010/R\u001b\u00101\u001a\u0002028FX\u0002¢\u0006\f\n\u0004\b5\u0010\u001e\u001a\u0004\b3\u00104¨\u0006P"}, mo37405d2 = {"Lcom/bitcoin/mwallet/MainTabbedActivity;", "Lcom/guardanis/applock/activities/LockableAppCompatActivity;", "Lorg/koin/core/KoinComponent;", "()V", "actionBarShowAddContact", "", "getActionBarShowAddContact", "()Z", "setActionBarShowAddContact", "(Z)V", "actionBarShowEditContact", "getActionBarShowEditContact", "setActionBarShowEditContact", "actionBarShowSaveContact", "getActionBarShowSaveContact", "setActionBarShowSaveContact", "actionBarShowSettings", "getActionBarShowSettings", "setActionBarShowSettings", "actionBarShowShop", "getActionBarShowShop", "setActionBarShowShop", "actionBarShowWalletSettings", "getActionBarShowWalletSettings", "setActionBarShowWalletSettings", "analyticsService", "Lcom/bitcoin/mwallet/core/services/AnalyticsService;", "getAnalyticsService", "()Lcom/bitcoin/mwallet/core/services/AnalyticsService;", "analyticsService$delegate", "Lkotlin/Lazy;", "applicationPreferences", "Lcom/bitcoin/mwallet/core/preferences/ApplicationPreferences;", "getApplicationPreferences", "()Lcom/bitcoin/mwallet/core/preferences/ApplicationPreferences;", "applicationPreferences$delegate", "currentNavController", "Landroidx/lifecycle/LiveData;", "Landroidx/navigation/NavController;", "refresher", "Lcom/bitcoin/mwallet/core/services/WalletRefresherTemp;", "getRefresher", "()Lcom/bitcoin/mwallet/core/services/WalletRefresherTemp;", "refresher$delegate", "streamHandler", "Lcom/bitcoin/mwallet/core/services/eventstream/EventStreamHandler;", "getStreamHandler", "()Lcom/bitcoin/mwallet/core/services/eventstream/EventStreamHandler;", "streamHandler$delegate", "workManagerFactory", "Lcom/bitcoin/mwallet/core/workers/WorkManagerGlobalFactory;", "getWorkManagerFactory", "()Lcom/bitcoin/mwallet/core/workers/WorkManagerGlobalFactory;", "workManagerFactory$delegate", "attachBaseContext", "", "newBase", "Landroid/content/Context;", "findPermissionsReceiver", "Lcom/bitcoin/mwallet/core/qrscan/PermissionsReceiver;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onPostResume", "onRequestPermissionsResult", "requestCode", "", "permissions", "", "", "grantResults", "", "(I[Ljava/lang/String;[I)V", "onSupportNavigateUp", "setLocale", "setupBottomNavigationBar", "showBiometricPrompt", "activity", "Landroidx/appcompat/app/AppCompatActivity;", "unlockActivity", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: MainTabbedActivity.kt */
public final class MainTabbedActivity extends LockableAppCompatActivity implements KoinComponent {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(MainTabbedActivity.class), "refresher", "getRefresher()Lcom/bitcoin/mwallet/core/services/WalletRefresherTemp;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(MainTabbedActivity.class), "streamHandler", "getStreamHandler()Lcom/bitcoin/mwallet/core/services/eventstream/EventStreamHandler;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(MainTabbedActivity.class), "workManagerFactory", "getWorkManagerFactory()Lcom/bitcoin/mwallet/core/workers/WorkManagerGlobalFactory;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(MainTabbedActivity.class), "analyticsService", "getAnalyticsService()Lcom/bitcoin/mwallet/core/services/AnalyticsService;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(MainTabbedActivity.class), "applicationPreferences", "getApplicationPreferences()Lcom/bitcoin/mwallet/core/preferences/ApplicationPreferences;"))};
    private HashMap _$_findViewCache;
    private boolean actionBarShowAddContact;
    private boolean actionBarShowEditContact;
    private boolean actionBarShowSaveContact;
    private boolean actionBarShowSettings;
    private boolean actionBarShowShop;
    private boolean actionBarShowWalletSettings;
    @NotNull
    private final Lazy analyticsService$delegate;
    @NotNull
    private final Lazy applicationPreferences$delegate;
    private LiveData<NavController> currentNavController;
    @NotNull
    private final Lazy refresher$delegate;
    @NotNull
    private final Lazy streamHandler$delegate;
    @NotNull
    private final Lazy workManagerFactory$delegate;

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View findViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    @NotNull
    public final AnalyticsService getAnalyticsService() {
        Lazy lazy = this.analyticsService$delegate;
        KProperty kProperty = $$delegatedProperties[3];
        return (AnalyticsService) lazy.getValue();
    }

    @NotNull
    public final ApplicationPreferences getApplicationPreferences() {
        Lazy lazy = this.applicationPreferences$delegate;
        KProperty kProperty = $$delegatedProperties[4];
        return (ApplicationPreferences) lazy.getValue();
    }

    @NotNull
    public final WalletRefresherTemp getRefresher() {
        Lazy lazy = this.refresher$delegate;
        KProperty kProperty = $$delegatedProperties[0];
        return (WalletRefresherTemp) lazy.getValue();
    }

    @NotNull
    public final EventStreamHandler getStreamHandler() {
        Lazy lazy = this.streamHandler$delegate;
        KProperty kProperty = $$delegatedProperties[1];
        return (EventStreamHandler) lazy.getValue();
    }

    @NotNull
    public final WorkManagerGlobalFactory getWorkManagerFactory() {
        Lazy lazy = this.workManagerFactory$delegate;
        KProperty kProperty = $$delegatedProperties[2];
        return (WorkManagerGlobalFactory) lazy.getValue();
    }

    public MainTabbedActivity() {
        Qualifier qualifier = null;
        Function0 function0 = null;
        this.refresher$delegate = LazyKt.lazy(new MainTabbedActivity$$special$$inlined$inject$1(getKoin().getRootScope(), qualifier, function0));
        this.streamHandler$delegate = LazyKt.lazy(new MainTabbedActivity$$special$$inlined$inject$2(getKoin().getRootScope(), qualifier, function0));
        this.workManagerFactory$delegate = LazyKt.lazy(new MainTabbedActivity$$special$$inlined$inject$3(getKoin().getRootScope(), qualifier, function0));
        this.analyticsService$delegate = LazyKt.lazy(new MainTabbedActivity$$special$$inlined$inject$4(getKoin().getRootScope(), qualifier, function0));
        this.applicationPreferences$delegate = LazyKt.lazy(new MainTabbedActivity$$special$$inlined$inject$5(getKoin().getRootScope(), qualifier, function0));
    }

    @NotNull
    public Koin getKoin() {
        return DefaultImpls.getKoin(this);
    }

    public final boolean getActionBarShowAddContact() {
        return this.actionBarShowAddContact;
    }

    public final void setActionBarShowAddContact(boolean z) {
        this.actionBarShowAddContact = z;
    }

    public final boolean getActionBarShowSaveContact() {
        return this.actionBarShowSaveContact;
    }

    public final void setActionBarShowSaveContact(boolean z) {
        this.actionBarShowSaveContact = z;
    }

    public final boolean getActionBarShowSettings() {
        return this.actionBarShowSettings;
    }

    public final void setActionBarShowSettings(boolean z) {
        this.actionBarShowSettings = z;
    }

    public final boolean getActionBarShowWalletSettings() {
        return this.actionBarShowWalletSettings;
    }

    public final void setActionBarShowWalletSettings(boolean z) {
        this.actionBarShowWalletSettings = z;
    }

    public final boolean getActionBarShowShop() {
        return this.actionBarShowShop;
    }

    public final void setActionBarShowShop(boolean z) {
        this.actionBarShowShop = z;
    }

    public final boolean getActionBarShowEditContact() {
        return this.actionBarShowEditContact;
    }

    public final void setActionBarShowEditContact(boolean z) {
        this.actionBarShowEditContact = z;
    }

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) C1018R.layout.activity_main);
        setupBottomNavigationBar();
        setLocale();
        getRefresher();
        getStreamHandler();
        getWorkManagerFactory();
        getAnalyticsService();
        AppCenter.start(getApplication(), BuildConfig.MS_APP_CENTER, Analytics.class, Crashes.class);
    }

    /* access modifiers changed from: protected */
    public void onPostResume() {
        super.onPostResume();
        getStreamHandler().restartStream();
    }

    public final void unlockActivity() {
        KeyguardManager keyguardManager = (KeyguardManager) getSystemService("keyguard");
        if (getApplicationPreferences().getBoolean(SharedPreference.APP_LOCK.getKey(), false)) {
            Context context = this;
            if (AppLock.isEnrolled(context)) {
                Dialog show = new UnlockDialogBuilder(this).onCanceled(new MainTabbedActivity$unlockActivity$dialog$1(this)).onUnlocked(MainTabbedActivity$unlockActivity$dialog$2.INSTANCE).show();
                Intrinsics.checkExpressionValueIsNotNull(show, "dialog");
                Window window = show.getWindow();
                if (window != null) {
                    window.setDimAmount(1.0f);
                }
            } else if (VERSION.SDK_INT > 23 && keyguardManager != null && keyguardManager.isDeviceSecure() && BiometricsInteractor.Companion.canAuthenticateWithBiometrics(context)) {
                showBiometricPrompt(this);
            }
        }
    }

    private final void showBiometricPrompt(AppCompatActivity appCompatActivity) {
        Executor executor = MainTabbedActivity$showBiometricPrompt$executor$1.INSTANCE;
        PromptInfo build = new Builder().setTitle("Authenticate to Login").setNegativeButtonText("Cancel").build();
        Intrinsics.checkExpressionValueIsNotNull(build, "BiometricPrompt.PromptIn…el\")\n            .build()");
        new BiometricPrompt((FragmentActivity) appCompatActivity, executor, (AuthenticationCallback) new MainTabbedActivity$showBiometricPrompt$biometricPrompt$1(appCompatActivity)).authenticate(build);
    }

    public final void setLocale() {
        Context context = this;
        new LocaleManager(context).setLocale(context);
    }

    /* access modifiers changed from: protected */
    public void attachBaseContext(@Nullable Context context) {
        if (context == null) {
            Intrinsics.throwNpe();
        }
        super.attachBaseContext(new LocaleManager(context).setLocale(context));
    }

    public boolean onSupportNavigateUp() {
        LiveData<NavController> liveData = this.currentNavController;
        if (liveData != null) {
            NavController navController = (NavController) liveData.getValue();
            if (navController != null) {
                return navController.navigateUp();
            }
        }
        return false;
    }

    public void onRequestPermissionsResult(int i, @NotNull String[] strArr, @NotNull int[] iArr) {
        Intrinsics.checkParameterIsNotNull(strArr, "permissions");
        Intrinsics.checkParameterIsNotNull(iArr, "grantResults");
        if (i == PermissionsReceiver.Companion.getREQ_CODE_CAMERA()) {
            int indexOf = ArraysKt.indexOf((Object[]) strArr, (Object) "android.permission.CAMERA");
            if (indexOf >= 0) {
                int i2 = iArr[indexOf];
                PermissionsReceiver findPermissionsReceiver = findPermissionsReceiver();
                if (findPermissionsReceiver != null) {
                    findPermissionsReceiver.onPermissionResult(i2);
                }
            }
        }
        super.onRequestPermissionsResult(i, strArr, iArr);
    }

    private final PermissionsReceiver findPermissionsReceiver() {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        Intrinsics.checkExpressionValueIsNotNull(supportFragmentManager, "supportFragmentManager");
        List<Fragment> fragments = supportFragmentManager.getFragments();
        Intrinsics.checkExpressionValueIsNotNull(fragments, "supportFragmentManager.fragments");
        for (Fragment fragment : fragments) {
            if (fragment instanceof PermissionsReceiver) {
                return (PermissionsReceiver) fragment;
            }
            FragmentManager childFragmentManager = fragment.getChildFragmentManager();
            Intrinsics.checkExpressionValueIsNotNull(childFragmentManager, "fragment.childFragmentManager");
            List fragments2 = childFragmentManager.getFragments();
            Intrinsics.checkExpressionValueIsNotNull(fragments2, "fragment.childFragmentManager.fragments");
            Iterator it = fragments2.iterator();
            while (true) {
                if (it.hasNext()) {
                    Fragment fragment2 = (Fragment) it.next();
                    if (fragment2 instanceof PermissionsReceiver) {
                        return (PermissionsReceiver) fragment2;
                    }
                }
            }
        }
        return null;
    }

    private final void setupBottomNavigationBar() {
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(C1018R.C1021id.bottom_navigation);
        Intrinsics.checkExpressionValueIsNotNull(bottomNavigationView, "bottomNavigationView");
        bottomNavigationView.setLabelVisibilityMode(2);
        List listOf = CollectionsKt.listOf(Integer.valueOf(C1018R.C1024navigation.nav_home), Integer.valueOf(C1018R.C1024navigation.nav_discover), Integer.valueOf(C1018R.C1024navigation.nav_settings));
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        Intrinsics.checkExpressionValueIsNotNull(supportFragmentManager, "supportFragmentManager");
        Intent intent = getIntent();
        Intrinsics.checkExpressionValueIsNotNull(intent, "intent");
        this.currentNavController = NavigationExtensionsKt.setupWithNavController(bottomNavigationView, listOf, supportFragmentManager, C1018R.C1021id.nav_host_container, intent);
    }
}
