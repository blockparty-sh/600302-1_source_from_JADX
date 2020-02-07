package com.bitcoin.mwallet.app.android;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00010\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0014\u0010\u0006\u001a\u00020\u00072\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005R\u0016\u0010\u0004\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0005X\u000e¢\u0006\u0002\n\u0000¨\u0006\b"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/android/SingleSourceMediatorLiveData;", "T", "Landroidx/lifecycle/MediatorLiveData;", "()V", "source", "Landroidx/lifecycle/LiveData;", "replaceSource", "", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: SingleSourceMediatorLiveData.kt */
public final class SingleSourceMediatorLiveData<T> extends MediatorLiveData<T> {
    private LiveData<T> source;

    public final void replaceSource(@NotNull LiveData<T> liveData) {
        Intrinsics.checkParameterIsNotNull(liveData, Param.SOURCE);
        LiveData<T> liveData2 = this.source;
        if (liveData2 != null) {
            removeSource(liveData2);
        }
        this.source = liveData;
        addSource(liveData, new SingleSourceMediatorLiveData$replaceSource$2(this));
    }
}
