package com.bitcoin.mwallet.core.preferences;

import android.app.Application;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import androidx.annotation.RecentlyNullable;
import androidx.preference.PreferenceManager;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\u0010\"\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0001¢\u0006\u0002\u0010\u0005J\u0019\u0010\u0006\u001a\u00020\u00072\u000e\u0010\b\u001a\n \n*\u0004\u0018\u00010\t0\tH\u0003J\u0011\u0010\u000b\u001a\n \n*\u0004\u0018\u00010\f0\fH\u0001J5\u0010\r\u001a.\u0012\f\u0012\n \n*\u0004\u0018\u00010\t0\t\u0012\u0002\b\u0003 \n*\u0016\u0012\f\u0012\n \n*\u0004\u0018\u00010\t0\t\u0012\u0002\b\u0003\u0018\u00010\u000f0\u000eH\u0001J!\u0010\u0010\u001a\u00020\u00072\u000e\u0010\b\u001a\n \n*\u0004\u0018\u00010\t0\t2\u0006\u0010\u0011\u001a\u00020\u0007H\u0001J!\u0010\u0012\u001a\u00020\u00132\u000e\u0010\b\u001a\n \n*\u0004\u0018\u00010\t0\t2\u0006\u0010\u0011\u001a\u00020\u0013H\u0001J!\u0010\u0014\u001a\u00020\u00152\u000e\u0010\b\u001a\n \n*\u0004\u0018\u00010\t0\t2\u0006\u0010\u0011\u001a\u00020\u0015H\u0001J!\u0010\u0016\u001a\u00020\u00172\u000e\u0010\b\u001a\n \n*\u0004\u0018\u00010\t0\t2\u0006\u0010\u0011\u001a\u00020\u0017H\u0001J3\u0010\u0018\u001a\n \n*\u0004\u0018\u00010\t0\t2\u000e\u0010\b\u001a\n \n*\u0004\u0018\u00010\t0\t2\u0010\b\u0001\u0010\u0011\u001a\n \n*\u0004\u0018\u00010\t0\tH\u0001Jk\u0010\u0019\u001a&\u0012\f\u0012\n \n*\u0004\u0018\u00010\t0\t \n*\u0012\u0012\f\u0012\n \n*\u0004\u0018\u00010\t0\t\u0018\u00010\u001b0\u001a2\u000e\u0010\b\u001a\n \n*\u0004\u0018\u00010\t0\t2,\b\u0001\u0010\u0011\u001a&\u0012\f\u0012\n \n*\u0004\u0018\u00010\t0\t \n*\u0012\u0012\f\u0012\n \n*\u0004\u0018\u00010\t0\t\u0018\u00010\u001b0\u001aH\u0001J\u0019\u0010\u001c\u001a\u00020\u001d2\u000e\u0010\b\u001a\n \n*\u0004\u0018\u00010\u001e0\u001eH\u0001J\u0019\u0010\u001f\u001a\u00020\u001d2\u000e\u0010\b\u001a\n \n*\u0004\u0018\u00010\u001e0\u001eH\u0001R\u000e\u0010\u0004\u001a\u00020\u0001X\u0004¢\u0006\u0002\n\u0000¨\u0006 "}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/preferences/ApplicationPreferences;", "Landroid/content/SharedPreferences;", "application", "Landroid/app/Application;", "sharedPreferences", "(Landroid/app/Application;Landroid/content/SharedPreferences;)V", "contains", "", "p0", "", "kotlin.jvm.PlatformType", "edit", "Landroid/content/SharedPreferences$Editor;", "getAll", "", "", "getBoolean", "p1", "getFloat", "", "getInt", "", "getLong", "", "getString", "getStringSet", "", "", "registerOnSharedPreferenceChangeListener", "", "Landroid/content/SharedPreferences$OnSharedPreferenceChangeListener;", "unregisterOnSharedPreferenceChangeListener", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: ApplicationPreferences.kt */
public class ApplicationPreferences implements SharedPreferences {
    private final SharedPreferences sharedPreferences;

    public boolean contains(String str) {
        return this.sharedPreferences.contains(str);
    }

    public Editor edit() {
        return this.sharedPreferences.edit();
    }

    public Map<String, ?> getAll() {
        return this.sharedPreferences.getAll();
    }

    public boolean getBoolean(String str, boolean z) {
        return this.sharedPreferences.getBoolean(str, z);
    }

    public float getFloat(String str, float f) {
        return this.sharedPreferences.getFloat(str, f);
    }

    public int getInt(String str, int i) {
        return this.sharedPreferences.getInt(str, i);
    }

    public long getLong(String str, long j) {
        return this.sharedPreferences.getLong(str, j);
    }

    @RecentlyNullable
    public String getString(String str, @RecentlyNullable String str2) {
        return this.sharedPreferences.getString(str, str2);
    }

    @RecentlyNullable
    public Set<String> getStringSet(String str, @RecentlyNullable Set<String> set) {
        return this.sharedPreferences.getStringSet(str, set);
    }

    public void registerOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        this.sharedPreferences.registerOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
    }

    public void unregisterOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        this.sharedPreferences.unregisterOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
    }

    public ApplicationPreferences(@NotNull Application application, @NotNull SharedPreferences sharedPreferences2) {
        Intrinsics.checkParameterIsNotNull(application, "application");
        Intrinsics.checkParameterIsNotNull(sharedPreferences2, "sharedPreferences");
        this.sharedPreferences = sharedPreferences2;
    }

    public /* synthetic */ ApplicationPreferences(Application application, SharedPreferences sharedPreferences2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 2) != 0) {
            sharedPreferences2 = PreferenceManager.getDefaultSharedPreferences(application);
            Intrinsics.checkExpressionValueIsNotNull(sharedPreferences2, "PreferenceManager.getDef…dPreferences(application)");
        }
        this(application, sharedPreferences2);
    }
}
