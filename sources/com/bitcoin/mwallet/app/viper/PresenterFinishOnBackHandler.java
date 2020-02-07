package com.bitcoin.mwallet.app.viper;

import androidx.activity.OnBackPressedCallback;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\n\u001a\u00020\u000bH\u0016R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\f"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/viper/PresenterFinishOnBackHandler;", "Landroidx/activity/OnBackPressedCallback;", "()V", "_shouldFinish", "Landroidx/lifecycle/MutableLiveData;", "", "shouldFinish", "Landroidx/lifecycle/LiveData;", "getShouldFinish", "()Landroidx/lifecycle/LiveData;", "handleOnBackPressed", "", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: PresenterFinishOnBackHandler.kt */
public final class PresenterFinishOnBackHandler extends OnBackPressedCallback {
    private final MutableLiveData<Boolean> _shouldFinish = new MutableLiveData<>(Boolean.valueOf(false));
    @NotNull
    private final LiveData<Boolean> shouldFinish = this._shouldFinish;

    public PresenterFinishOnBackHandler() {
        super(true);
    }

    public void handleOnBackPressed() {
        this._shouldFinish.setValue(Boolean.valueOf(true));
    }

    @NotNull
    public final LiveData<Boolean> getShouldFinish() {
        return this.shouldFinish;
    }
}
