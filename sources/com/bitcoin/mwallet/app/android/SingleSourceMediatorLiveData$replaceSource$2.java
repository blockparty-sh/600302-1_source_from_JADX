package com.bitcoin.mwallet.app.android;

import androidx.lifecycle.Observer;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\b\u0010\u0003\u001a\u0004\u0018\u0001H\u0002H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, mo37405d2 = {"<anonymous>", "", "T", "value", "onChanged", "(Ljava/lang/Object;)V"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: SingleSourceMediatorLiveData.kt */
final class SingleSourceMediatorLiveData$replaceSource$2<T> implements Observer<S> {
    final /* synthetic */ SingleSourceMediatorLiveData this$0;

    SingleSourceMediatorLiveData$replaceSource$2(SingleSourceMediatorLiveData singleSourceMediatorLiveData) {
        this.this$0 = singleSourceMediatorLiveData;
    }

    public final void onChanged(@Nullable T t) {
        if (t != null) {
            this.this$0.setValue(t);
        }
    }
}
