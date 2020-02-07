package com.bitcoin.mwallet.app.flows.settings.settings.listeners;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import androidx.preference.Preference;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.app.flows.settings.settings.listeners.PreferenceListener.DefaultImpls;
import com.microsoft.appcenter.crashes.ingestion.models.ErrorAttachmentLog;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import timber.log.Timber;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0010\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\nH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0012"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/settings/settings/listeners/PreferenceListenerShareApp;", "Lcom/bitcoin/mwallet/app/flows/settings/settings/listeners/PreferenceListener;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getContext", "()Landroid/content/Context;", "init", "", "preference", "Landroidx/preference/Preference;", "sharedPreferences", "Landroid/content/SharedPreferences;", "onPreferenceChange", "", "newValue", "", "onPreferenceClick", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: PreferenceListenerShareApp.kt */
public final class PreferenceListenerShareApp implements PreferenceListener {
    @NotNull
    private final Context context;

    public PreferenceListenerShareApp(@NotNull Context context2) {
        Intrinsics.checkParameterIsNotNull(context2, "context");
        this.context = context2;
    }

    public void accept(@NotNull Preference preference, @NotNull SharedPreferences sharedPreferences) {
        Intrinsics.checkParameterIsNotNull(preference, "preference");
        Intrinsics.checkParameterIsNotNull(sharedPreferences, "sharedPreferences");
        DefaultImpls.accept(this, preference, sharedPreferences);
    }

    @NotNull
    public final Context getContext() {
        return this.context;
    }

    public void init(@NotNull Preference preference, @NotNull SharedPreferences sharedPreferences) {
        Intrinsics.checkParameterIsNotNull(preference, "preference");
        Intrinsics.checkParameterIsNotNull(sharedPreferences, "sharedPreferences");
        Timber.m426d("APP Info Initialized", new Object[0]);
    }

    public boolean onPreferenceChange(@NotNull Preference preference, @NotNull Object obj) {
        Intrinsics.checkParameterIsNotNull(preference, "preference");
        Intrinsics.checkParameterIsNotNull(obj, "newValue");
        Timber.m426d("On PreferenceChange", new Object[0]);
        return true;
    }

    public boolean onPreferenceClick(@NotNull Preference preference) {
        Intrinsics.checkParameterIsNotNull(preference, "preference");
        Intent intent = new Intent();
        intent.setAction("android.intent.action.SEND");
        intent.putExtra("android.intent.extra.TEXT", this.context.getString(C1018R.string.app_share_text));
        intent.setType(ErrorAttachmentLog.CONTENT_TYPE_TEXT_PLAIN);
        this.context.startActivity(intent);
        return true;
    }
}
