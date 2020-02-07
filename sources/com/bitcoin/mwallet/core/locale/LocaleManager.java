package com.bitcoin.mwallet.core.locale;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\n\u001a\u0004\u0018\u00010\u000bJ\u0010\u0010\f\u001a\u00020\u00032\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003J\u0016\u0010\r\u001a\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000bR\u0019\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u000f"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/locale/LocaleManager;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "pref", "Landroid/content/SharedPreferences;", "kotlin.jvm.PlatformType", "getPref", "()Landroid/content/SharedPreferences;", "getCurrentLanguage", "Ljava/util/Locale;", "setLocale", "updateResources", "language", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: LocaleManager.kt */
public final class LocaleManager {
    private final SharedPreferences pref;

    @NotNull
    public final Context updateResources(@NotNull Context context, @NotNull Locale locale) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(locale, "language");
        return context;
    }

    public LocaleManager(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.pref = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public final SharedPreferences getPref() {
        return this.pref;
    }

    @NotNull
    public final Context setLocale(@Nullable Context context) {
        if (context == null) {
            Intrinsics.throwNpe();
        }
        Locale currentLanguage = getCurrentLanguage();
        if (currentLanguage == null) {
            Intrinsics.throwNpe();
        }
        return updateResources(context, currentLanguage);
    }

    @Nullable
    public final Locale getCurrentLanguage() {
        return Locale.getDefault();
    }
}
