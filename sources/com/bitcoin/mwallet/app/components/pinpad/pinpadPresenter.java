package com.bitcoin.mwallet.app.components.pinpad;

import android.content.Context;
import com.bitcoin.mwallet.app.viper.PresenterBase;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010\r\u001a\u00020\u000eJ\u0006\u0010\u000f\u001a\u00020\u000eJ\u000e\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u0012R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f¨\u0006\u0013"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/components/pinpad/pinpadPresenter;", "Lcom/bitcoin/mwallet/app/viper/PresenterBase;", "context", "Landroid/content/Context;", "viewModelScope", "Lkotlinx/coroutines/CoroutineScope;", "(Landroid/content/Context;Lkotlinx/coroutines/CoroutineScope;)V", "listener", "Lcom/bitcoin/mwallet/app/components/pinpad/OnPinPadItemClickedListener;", "getListener", "()Lcom/bitcoin/mwallet/app/components/pinpad/OnPinPadItemClickedListener;", "setListener", "(Lcom/bitcoin/mwallet/app/components/pinpad/OnPinPadItemClickedListener;)V", "decimalTapped", "", "deleteTapped", "digitTapped", "digit", "", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: pinpadPresenter.kt */
public final class pinpadPresenter extends PresenterBase {
    @Nullable
    private OnPinPadItemClickedListener listener;

    public pinpadPresenter(@NotNull Context context, @NotNull CoroutineScope coroutineScope) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(coroutineScope, "viewModelScope");
    }

    @Nullable
    public final OnPinPadItemClickedListener getListener() {
        return this.listener;
    }

    public final void setListener(@Nullable OnPinPadItemClickedListener onPinPadItemClickedListener) {
        this.listener = onPinPadItemClickedListener;
    }

    public final void digitTapped(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "digit");
        OnPinPadItemClickedListener onPinPadItemClickedListener = this.listener;
        if (onPinPadItemClickedListener != null) {
            onPinPadItemClickedListener.digitTapped(str);
        }
    }

    public final void decimalTapped() {
        OnPinPadItemClickedListener onPinPadItemClickedListener = this.listener;
        if (onPinPadItemClickedListener != null) {
            onPinPadItemClickedListener.decimalTapped();
        }
    }

    public final void deleteTapped() {
        OnPinPadItemClickedListener onPinPadItemClickedListener = this.listener;
        if (onPinPadItemClickedListener != null) {
            onPinPadItemClickedListener.deleteTapped();
        }
    }
}
