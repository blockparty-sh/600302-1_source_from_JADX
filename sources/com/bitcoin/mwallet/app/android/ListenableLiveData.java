package com.bitcoin.mwallet.app.android;

import androidx.lifecycle.MediatorLiveData;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0016\u0018\u0000*\u0004\b\u0000\u0010\u00012\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00010\u0002B\u0013\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\u0002\u0010\u0005J\u0017\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00018\u0000H\u0016¢\u0006\u0002\u0010\tR\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/android/ListenableLiveData;", "T", "Landroidx/lifecycle/MediatorLiveData;", "listener", "Lcom/bitcoin/mwallet/app/android/LiveDataChangeListener;", "(Lcom/bitcoin/mwallet/app/android/LiveDataChangeListener;)V", "setValue", "", "value", "(Ljava/lang/Object;)V", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: ListenableLiveData.kt */
public class ListenableLiveData<T> extends MediatorLiveData<T> {
    private final LiveDataChangeListener<T> listener;

    public ListenableLiveData(@NotNull LiveDataChangeListener<T> liveDataChangeListener) {
        Intrinsics.checkParameterIsNotNull(liveDataChangeListener, CastExtraArgs.LISTENER);
        this.listener = liveDataChangeListener;
    }

    public void setValue(@Nullable T t) {
        super.setValue(t);
        this.listener.onValueSet(t);
    }
}
