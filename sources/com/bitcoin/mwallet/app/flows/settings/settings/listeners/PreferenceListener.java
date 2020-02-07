package com.bitcoin.mwallet.app.flows.settings.settings.listeners;

import android.content.SharedPreferences;
import androidx.preference.Preference;
import androidx.preference.Preference.OnPreferenceChangeListener;
import androidx.preference.Preference.OnPreferenceClickListener;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u00012\u00020\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0018\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH&¨\u0006\n"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/settings/settings/listeners/PreferenceListener;", "Landroidx/preference/Preference$OnPreferenceChangeListener;", "Landroidx/preference/Preference$OnPreferenceClickListener;", "accept", "", "preference", "Landroidx/preference/Preference;", "sharedPreferences", "Landroid/content/SharedPreferences;", "init", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: PreferenceListener.kt */
public interface PreferenceListener extends OnPreferenceChangeListener, OnPreferenceClickListener {

    @Metadata(mo37403bv = {1, 0, 3}, mo37406k = 3, mo37407mv = {1, 1, 15})
    /* compiled from: PreferenceListener.kt */
    public static final class DefaultImpls {
        public static void accept(PreferenceListener preferenceListener, @NotNull Preference preference, @NotNull SharedPreferences sharedPreferences) {
            Intrinsics.checkParameterIsNotNull(preference, "preference");
            Intrinsics.checkParameterIsNotNull(sharedPreferences, "sharedPreferences");
            preference.setOnPreferenceClickListener(preferenceListener);
            preference.setOnPreferenceChangeListener(preferenceListener);
            preferenceListener.init(preference, sharedPreferences);
        }
    }

    void accept(@NotNull Preference preference, @NotNull SharedPreferences sharedPreferences);

    void init(@NotNull Preference preference, @NotNull SharedPreferences sharedPreferences);
}
