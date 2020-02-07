package com.bitcoin.mwallet.app.flows.settings.settings;

import androidx.preference.Preference;
import androidx.preference.Preference.OnPreferenceClickListener;
import com.guardanis.applock.AppLock;
import com.guardanis.applock.dialogs.UnlockDialogBuilder;
import kotlin.Metadata;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, mo37405d2 = {"<anonymous>", "", "it", "Landroidx/preference/Preference;", "kotlin.jvm.PlatformType", "onPreferenceClick"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: SettingsView.kt */
final class SettingsView$onActivityCreated$3 implements OnPreferenceClickListener {
    final /* synthetic */ SettingsView this$0;

    SettingsView$onActivityCreated$3(SettingsView settingsView) {
        this.this$0 = settingsView;
    }

    public final boolean onPreferenceClick(Preference preference) {
        if (AppLock.isEnrolled(this.this$0.getActivity())) {
            new UnlockDialogBuilder(this.this$0.getActivity()).onCanceled(C11081.INSTANCE).onUnlocked(new Runnable(this) {
                final /* synthetic */ SettingsView$onActivityCreated$3 this$0;

                {
                    this.this$0 = r1;
                }

                public final void run() {
                    this.this$0.this$0.exportLegacy();
                }
            }).show();
        } else {
            this.this$0.exportLegacy();
        }
        return true;
    }
}
