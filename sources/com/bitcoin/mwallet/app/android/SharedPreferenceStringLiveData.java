package com.bitcoin.mwallet.app.android;

import android.content.SharedPreferences;
import com.bitcoin.mwallet.core.preferences.SharedPreference;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0012\u0010\b\u001a\u0004\u0018\u00010\u00022\u0006\u0010\t\u001a\u00020\u0002H\u0016¨\u0006\n"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/android/SharedPreferenceStringLiveData;", "Lcom/bitcoin/mwallet/app/android/SharedPreferenceLiveData;", "", "sharedPreferences", "Landroid/content/SharedPreferences;", "preference", "Lcom/bitcoin/mwallet/core/preferences/SharedPreference;", "(Landroid/content/SharedPreferences;Lcom/bitcoin/mwallet/core/preferences/SharedPreference;)V", "getValueFromPreferences", "key", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: SharedPreferenceLiveData.kt */
public final class SharedPreferenceStringLiveData extends SharedPreferenceLiveData<String> {
    public SharedPreferenceStringLiveData(@NotNull SharedPreferences sharedPreferences, @NotNull SharedPreference sharedPreference) {
        Intrinsics.checkParameterIsNotNull(sharedPreferences, "sharedPreferences");
        Intrinsics.checkParameterIsNotNull(sharedPreference, "preference");
        super(sharedPreferences, sharedPreference, null, 4, null);
    }

    @Nullable
    public String getValueFromPreferences(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "key");
        return getSharedPreferences().getString(str, null);
    }
}
