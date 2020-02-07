package com.bitcoin.mwallet.app.android;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import androidx.lifecycle.LiveData;
import com.bitcoin.mwallet.core.preferences.SharedPreference;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import timber.log.Timber;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0003\b&\u0018\u0000*\u0004\b\u0000\u0010\u00012\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00010\u00022\u00020\u0003B\u001f\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0017\u0010\r\u001a\u0004\u0018\u00018\u00002\u0006\u0010\b\u001a\u00020\tH&¢\u0006\u0002\u0010\u000eJ\b\u0010\u000f\u001a\u00020\u0010H\u0014J\b\u0010\u0011\u001a\u00020\u0010H\u0014J\u0018\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\tH\u0016R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0013"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/android/SharedPreferenceLiveData;", "T", "Landroidx/lifecycle/LiveData;", "Landroid/content/SharedPreferences$OnSharedPreferenceChangeListener;", "sharedPreferences", "Landroid/content/SharedPreferences;", "preference", "Lcom/bitcoin/mwallet/core/preferences/SharedPreference;", "key", "", "(Landroid/content/SharedPreferences;Lcom/bitcoin/mwallet/core/preferences/SharedPreference;Ljava/lang/String;)V", "getSharedPreferences", "()Landroid/content/SharedPreferences;", "getValueFromPreferences", "(Ljava/lang/String;)Ljava/lang/Object;", "onActive", "", "onInactive", "onSharedPreferenceChanged", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: SharedPreferenceLiveData.kt */
public abstract class SharedPreferenceLiveData<T> extends LiveData<T> implements OnSharedPreferenceChangeListener {
    private final String key;
    @NotNull
    private final SharedPreferences sharedPreferences;

    @Nullable
    public abstract T getValueFromPreferences(@NotNull String str);

    /* access modifiers changed from: protected */
    @NotNull
    public final SharedPreferences getSharedPreferences() {
        return this.sharedPreferences;
    }

    public /* synthetic */ SharedPreferenceLiveData(SharedPreferences sharedPreferences2, SharedPreference sharedPreference, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 4) != 0) {
            str = sharedPreference.getKey();
        }
        this(sharedPreferences2, sharedPreference, str);
    }

    public SharedPreferenceLiveData(@NotNull SharedPreferences sharedPreferences2, @NotNull SharedPreference sharedPreference, @NotNull String str) {
        Intrinsics.checkParameterIsNotNull(sharedPreferences2, "sharedPreferences");
        Intrinsics.checkParameterIsNotNull(sharedPreference, "preference");
        Intrinsics.checkParameterIsNotNull(str, "key");
        this.sharedPreferences = sharedPreferences2;
        this.key = str;
    }

    public void onSharedPreferenceChanged(@NotNull SharedPreferences sharedPreferences2, @NotNull String str) {
        Intrinsics.checkParameterIsNotNull(sharedPreferences2, "sharedPreferences");
        Intrinsics.checkParameterIsNotNull(str, "key");
        if (Intrinsics.areEqual((Object) this.key, (Object) str)) {
            setValue(getValueFromPreferences(str));
        }
    }

    /* access modifiers changed from: protected */
    public void onActive() {
        StringBuilder sb = new StringBuilder();
        String str = "onActive() for ";
        sb.append(str);
        sb.append(this.key);
        Timber.m426d(sb.toString(), new Object[0]);
        super.onActive();
        setValue(getValueFromPreferences(this.key));
        StringBuilder sb2 = new StringBuilder();
        sb2.append(str);
        sb2.append(this.key);
        sb2.append(" value: ");
        sb2.append(getValue());
        Timber.m426d(sb2.toString(), new Object[0]);
        this.sharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }

    /* access modifiers changed from: protected */
    public void onInactive() {
        StringBuilder sb = new StringBuilder();
        sb.append("onInactive() for ");
        sb.append(this.key);
        Timber.m426d(sb.toString(), new Object[0]);
        this.sharedPreferences.unregisterOnSharedPreferenceChangeListener(this);
        super.onInactive();
    }
}
