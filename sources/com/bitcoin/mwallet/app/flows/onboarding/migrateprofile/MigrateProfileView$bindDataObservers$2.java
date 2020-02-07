package com.bitcoin.mwallet.app.flows.onboarding.migrateprofile;

import android.view.View;
import android.widget.TextView;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.Observer;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.entity.MigrationStatus;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, mo37405d2 = {"<anonymous>", "", "status", "Lcom/bitcoin/mwallet/app/flows/onboarding/migrateprofile/entity/MigrationStatus;", "onChanged"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: MigrateProfileView.kt */
final class MigrateProfileView$bindDataObservers$2<T> implements Observer<MigrationStatus> {
    final /* synthetic */ MigrateProfileView this$0;

    MigrateProfileView$bindDataObservers$2(MigrateProfileView migrateProfileView) {
        this.this$0 = migrateProfileView;
    }

    public final void onChanged(@NotNull MigrationStatus migrationStatus) {
        Intrinsics.checkParameterIsNotNull(migrationStatus, NotificationCompat.CATEGORY_STATUS);
        View view = this.this$0.getView();
        if (view != null) {
            TextView textView = (TextView) view.findViewById(C1018R.C1021id.migrationStatus);
            if (textView != null) {
                textView.setText(migrationStatus.getStatusText());
            }
        }
        this.this$0.controlAnimation(migrationStatus);
    }
}
