package com.bitcoin.mwallet.app.flows.onboarding.migrateprofile;

import androidx.lifecycle.Observer;
import kotlin.Metadata;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, mo37405d2 = {"<anonymous>", "", "migrationComplete", "", "onChanged"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: MigrateProfileView.kt */
final class MigrateProfileView$bindDataObservers$3<T> implements Observer<Boolean> {
    final /* synthetic */ MigrateProfileView this$0;

    MigrateProfileView$bindDataObservers$3(MigrateProfileView migrateProfileView) {
        this.this$0 = migrateProfileView;
    }

    public /* bridge */ /* synthetic */ void onChanged(Object obj) {
        onChanged(((Boolean) obj).booleanValue());
    }

    public final void onChanged(boolean z) {
        if (z) {
            ((MigrateProfilePresenter) this.this$0.getPresenter()).goToHome();
        }
    }
}
