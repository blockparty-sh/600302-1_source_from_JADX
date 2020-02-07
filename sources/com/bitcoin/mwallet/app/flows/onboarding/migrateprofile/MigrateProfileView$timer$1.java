package com.bitcoin.mwallet.app.flows.onboarding.migrateprofile;

import android.os.CountDownTimer;
import androidx.fragment.app.FragmentActivity;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.app.components.warningdialog.WarningView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, mo37405d2 = {"com/bitcoin/mwallet/app/flows/onboarding/migrateprofile/MigrateProfileView$timer$1", "Landroid/os/CountDownTimer;", "onFinish", "", "onTick", "millisUntilFinished", "", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: MigrateProfileView.kt */
public final class MigrateProfileView$timer$1 extends CountDownTimer {
    final /* synthetic */ MigrateProfileView this$0;

    public void onTick(long j) {
    }

    MigrateProfileView$timer$1(MigrateProfileView migrateProfileView, long j, long j2) {
        this.this$0 = migrateProfileView;
        super(j, j2);
    }

    public void onFinish() {
        FragmentActivity activity = this.this$0.getActivity();
        if (activity != null) {
            WarningView warningView = new WarningView(C1018R.string.migration_warning_title, C1018R.string.migration_warning_description, null);
            Intrinsics.checkExpressionValueIsNotNull(activity, "it");
            warningView.show(activity.getSupportFragmentManager(), "migration_warning");
        }
    }
}
