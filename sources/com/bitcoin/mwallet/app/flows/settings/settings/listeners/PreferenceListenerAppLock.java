package com.bitcoin.mwallet.app.flows.settings.settings.listeners;

import android.app.Activity;
import android.content.SharedPreferences;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import com.bitcoin.mwallet.app.flows.settings.settings.listeners.PreferenceListener.DefaultImpls;
import com.bitcoin.mwallet.core.preferences.SharedPreference;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0010\u0010\u0015\u001a\u00020\u00122\u0006\u0010\r\u001a\u00020\u000eH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0018\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bX\u000e¢\u0006\u0004\n\u0002\u0010\n¨\u0006\u0016"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/settings/settings/listeners/PreferenceListenerAppLock;", "Lcom/bitcoin/mwallet/app/flows/settings/settings/listeners/PreferenceListener;", "activity", "Landroid/app/Activity;", "(Landroid/app/Activity;)V", "getActivity", "()Landroid/app/Activity;", "locktypes", "", "", "[Ljava/lang/String;", "init", "", "preference", "Landroidx/preference/Preference;", "sharedPreferences", "Landroid/content/SharedPreferences;", "onPreferenceChange", "", "newValue", "", "onPreferenceClick", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: PreferenceListenerAppLock.kt */
public final class PreferenceListenerAppLock implements PreferenceListener {
    @NotNull
    private final Activity activity;
    private String[] locktypes;

    public PreferenceListenerAppLock(@NotNull Activity activity2) {
        Intrinsics.checkParameterIsNotNull(activity2, "activity");
        this.activity = activity2;
    }

    public void accept(@NotNull Preference preference, @NotNull SharedPreferences sharedPreferences) {
        Intrinsics.checkParameterIsNotNull(preference, "preference");
        Intrinsics.checkParameterIsNotNull(sharedPreferences, "sharedPreferences");
        DefaultImpls.accept(this, preference, sharedPreferences);
    }

    @NotNull
    public final Activity getActivity() {
        return this.activity;
    }

    public void init(@NotNull Preference preference, @NotNull SharedPreferences sharedPreferences) {
        Intrinsics.checkParameterIsNotNull(preference, "preference");
        Intrinsics.checkParameterIsNotNull(sharedPreferences, "sharedPreferences");
        String str = "Disabled";
        Collection listOf = CollectionsKt.listOf("Touch ID/Finger Print", "PIN", str);
        if (listOf != null) {
            Object[] array = listOf.toArray(new String[0]);
            if (array != null) {
                this.locktypes = (String[]) array;
                preference.setEnabled(false);
                String string = sharedPreferences.getString(SharedPreference.APP_LOCK.getKey(), null);
                if (string == null) {
                    onPreferenceChange(preference, str);
                } else {
                    preference.setSummary((CharSequence) string);
                }
            } else {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
            }
        } else {
            throw new TypeCastException("null cannot be cast to non-null type java.util.Collection<T>");
        }
    }

    public boolean onPreferenceChange(@NotNull Preference preference, @NotNull Object obj) {
        Intrinsics.checkParameterIsNotNull(preference, "preference");
        Intrinsics.checkParameterIsNotNull(obj, "newValue");
        if (!(obj instanceof String)) {
            obj = null;
        }
        String str = (String) obj;
        if (str != null) {
            preference.setSummary((CharSequence) str);
        }
        return true;
    }

    public boolean onPreferenceClick(@NotNull Preference preference) {
        Intrinsics.checkParameterIsNotNull(preference, "preference");
        if (!(preference instanceof ListPreference)) {
            preference = null;
        }
        ListPreference listPreference = (ListPreference) preference;
        if (listPreference != null) {
            listPreference.setEntries((CharSequence[]) this.locktypes);
            listPreference.setEntryValues((CharSequence[]) this.locktypes);
        }
        return true;
    }
}
