package com.bitcoin.mwallet.app.flows.settings.settings;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.FragmentKt;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;
import androidx.preference.SwitchPreference;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.MainTabbedActivity;
import com.bitcoin.mwallet.app.components.appinfodialog.AboutDialogListener;
import com.bitcoin.mwallet.core.preferences.SharedPreference;
import com.bitcoin.mwallet.core.services.AnalyticsService;
import java.util.HashMap;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference0Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0006\u0010\b\u001a\u00020\tJ\u0012\u0010\n\u001a\u00020\t2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016J\u001c\u0010\r\u001a\u00020\t2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\b\u0010\u0010\u001a\u00020\tH\u0016J\u000e\u0010\u0011\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\u0013R\u000e\u0010\u0004\u001a\u00020\u0005X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X.¢\u0006\u0002\n\u0000¨\u0006\u0014²\u0006\n\u0010\u0015\u001a\u00020\u0016X\u0002"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/settings/settings/SettingsView;", "Landroidx/preference/PreferenceFragmentCompat;", "Lcom/bitcoin/mwallet/app/components/appinfodialog/AboutDialogListener;", "()V", "builder", "Lcom/bitcoin/mwallet/app/flows/settings/settings/SettingsBuilder;", "presenter", "Lcom/bitcoin/mwallet/app/flows/settings/settings/SettingsPresenter;", "exportLegacy", "", "onActivityCreated", "savedInstanceState", "Landroid/os/Bundle;", "onCreatePreferences", "rootKey", "", "showExportFunction", "updateSpendLockPreferenceStatus", "value", "", "app_replaceRelease", "analyticsService", "Lcom/bitcoin/mwallet/core/services/AnalyticsService;"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: SettingsView.kt */
public final class SettingsView extends PreferenceFragmentCompat implements AboutDialogListener {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property0(new PropertyReference0Impl(Reflection.getOrCreateKotlinClass(SettingsView.class), "analyticsService", "<v#0>"))};
    private HashMap _$_findViewCache;
    private SettingsBuilder builder;
    /* access modifiers changed from: private */
    public SettingsPresenter presenter;

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
        if (view == null) {
            View view2 = getView();
            if (view2 == null) {
                return null;
            }
            view = view2.findViewById(i);
            this._$_findViewCache.put(Integer.valueOf(i), view);
        }
        return view;
    }

    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public static final /* synthetic */ SettingsPresenter access$getPresenter$p(SettingsView settingsView) {
        SettingsPresenter settingsPresenter = settingsView.presenter;
        if (settingsPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        return settingsPresenter;
    }

    public void showExportFunction() {
        SettingsPresenter settingsPresenter = this.presenter;
        String str = "presenter";
        if (settingsPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException(str);
        }
        if (settingsPresenter.oldWalletExists()) {
            Toast.makeText(getContext(), "Legacy wallet export activated", 0).show();
            Preference findPreference = findPreference(SharedPreference.EXPORT_LEGACY.getKey());
            if (findPreference != null) {
                SettingsPresenter settingsPresenter2 = this.presenter;
                if (settingsPresenter2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(str);
                }
                findPreference.setVisible(settingsPresenter2.oldWalletExists());
                return;
            }
            return;
        }
        Toast.makeText(getContext(), "No legacy wallet profile exist", 0).show();
    }

    public void onCreatePreferences(@Nullable Bundle bundle, @Nullable String str) {
        setPreferencesFromResource(C1018R.C1027xml.settings_screen_main, str);
    }

    public final void updateSpendLockPreferenceStatus(boolean z) {
        SwitchPreference switchPreference = (SwitchPreference) findPreference(SharedPreference.APP_LOCK.getKey());
        if (switchPreference != null) {
            switchPreference.setChecked(z);
        }
    }

    public final void exportLegacy() {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/json");
        intent.putExtra("android.intent.extra.TEXT", (String) BuildersKt__BuildersKt.runBlocking$default(null, new SettingsView$exportLegacy$1(this, null), 1, null));
        startActivity(Intent.createChooser(intent, "export"));
    }

    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        Lazy lazy = LazyKt.lazy(new SettingsView$onActivityCreated$$inlined$inject$1(this, null, null));
        KProperty kProperty = $$delegatedProperties[0];
        ViewModel viewModel = ViewModelProviders.m16of((Fragment) this).get(SettingsBuilder.class);
        Intrinsics.checkExpressionValueIsNotNull(viewModel, "ViewModelProviders.of(th…tingsBuilder::class.java)");
        this.builder = (SettingsBuilder) viewModel;
        SettingsBuilder settingsBuilder = this.builder;
        String str = "builder";
        if (settingsBuilder == null) {
            Intrinsics.throwUninitializedPropertyAccessException(str);
        }
        settingsBuilder.setNavController(FragmentKt.findNavController(this));
        SettingsBuilder settingsBuilder2 = this.builder;
        if (settingsBuilder2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(str);
        }
        this.presenter = settingsBuilder2.getPresenter();
        SettingsPresenter settingsPresenter = this.presenter;
        if (settingsPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        settingsPresenter.setView(this);
        String str2 = "tab_open";
        ((AnalyticsService) lazy.getValue()).track(str2, MapsKt.mapOf(TuplesKt.m309to("tab_clicked_id", Integer.valueOf(C1018R.C1021id.nav_settings)), TuplesKt.m309to("tab_clicked", "Settings")));
        View view = getView();
        if (view != null) {
            view.setBackgroundColor(getResources().getColor(C1018R.C1019color.xxLightGray));
        }
        FragmentActivity activity = getActivity();
        if (!(activity instanceof MainTabbedActivity)) {
            activity = null;
        }
        MainTabbedActivity mainTabbedActivity = (MainTabbedActivity) activity;
        if (mainTabbedActivity != null) {
            mainTabbedActivity.setActionBarShowSettings(false);
            mainTabbedActivity.setActionBarShowShop(false);
            mainTabbedActivity.setActionBarShowAddContact(false);
        }
        FragmentActivity activity2 = getActivity();
        if (!(activity2 instanceof AppCompatActivity)) {
            activity2 = null;
        }
        AppCompatActivity appCompatActivity = (AppCompatActivity) activity2;
        if (appCompatActivity != null) {
            ActionBar supportActionBar = appCompatActivity.getSupportActionBar();
            if (supportActionBar != null) {
                supportActionBar.setDisplayOptions(8, 9);
                supportActionBar.setTitle((int) C1018R.string.settings_title);
                supportActionBar.invalidateOptionsMenu();
            }
        }
        Preference findPreference = findPreference(SharedPreference.EXPORT_LEGACY.getKey());
        if (findPreference != null) {
            findPreference.setVisible(false);
        }
        if (findPreference != null) {
            findPreference.setOnPreferenceClickListener(new SettingsView$onActivityCreated$3(this));
        }
        Preference findPreference2 = findPreference(SharedPreference.APP_INFO.getKey());
        if (findPreference2 != null) {
            findPreference2.setOnPreferenceClickListener(new SettingsView$onActivityCreated$4(this));
        }
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        Intrinsics.checkExpressionValueIsNotNull(defaultSharedPreferences, "PreferenceManager.getDef…aredPreferences(activity)");
        for (SharedPreference key : SetsKt.setOf(SharedPreference.FIAT_CURRENCY, SharedPreference.NETWORK_FEE, SharedPreference.CONTACTS, SharedPreference.NOTIFICATION, SharedPreference.LANGUAGE, SharedPreference.SHARE_APP, SharedPreference.APP_LOCK)) {
            Preference findPreference3 = findPreference(key.getKey());
            if (findPreference3 != null) {
                SettingsBuilder settingsBuilder3 = this.builder;
                if (settingsBuilder3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(str);
                }
                Intrinsics.checkExpressionValueIsNotNull(findPreference3, "this");
                settingsBuilder3.register(findPreference3, defaultSharedPreferences);
            }
        }
        setDivider(null);
    }
}
