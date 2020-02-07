package com.bitcoin.mwallet.app.viper;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import kotlin.Metadata;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000$\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\b\b\u0001\u0010\u0004*\u00020\u0005\"\u0010\b\u0002\u0010\u0006*\n\u0012\u0006\b\u0001\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\nH\n¢\u0006\u0002\b\u000b"}, mo37405d2 = {"<anonymous>", "", "P", "Lcom/bitcoin/mwallet/app/viper/PresenterWithCloseOnBackHandler;", "BUILDER", "Lcom/bitcoin/mwallet/app/viper/ScreenBuilderBase;", "PRESENTER", "Lcom/bitcoin/mwallet/app/viper/ScreenPresenter;", "Lcom/bitcoin/mwallet/app/viper/RouterBase;", "shouldFinish", "", "onChanged"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: ScreenView.kt */
final class ScreenView$setUpCloseOnBackHandler$1<T> implements Observer<Boolean> {
    final /* synthetic */ ScreenView this$0;

    ScreenView$setUpCloseOnBackHandler$1(ScreenView screenView) {
        this.this$0 = screenView;
    }

    public /* bridge */ /* synthetic */ void onChanged(Object obj) {
        onChanged(((Boolean) obj).booleanValue());
    }

    public final void onChanged(boolean z) {
        if (z) {
            FragmentActivity activity = this.this$0.getActivity();
            if (activity != null) {
                activity.finishAffinity();
            }
        }
    }
}
