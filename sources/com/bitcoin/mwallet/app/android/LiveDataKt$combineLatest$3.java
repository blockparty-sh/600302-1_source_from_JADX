package com.bitcoin.mwallet.app.android;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import kotlin.Metadata;
import kotlin.jvm.functions.Function3;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0004\"\u0004\b\u0003\u0010\u00052\u000e\u0010\u0006\u001a\n \u0007*\u0004\u0018\u0001H\u0002H\u0002H\n¢\u0006\u0004\b\b\u0010\t"}, mo37405d2 = {"<anonymous>", "", "T1", "T2", "T3", "S", "it", "kotlin.jvm.PlatformType", "onChanged", "(Ljava/lang/Object;)V"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: LiveData.kt */
final class LiveDataKt$combineLatest$3<T> implements Observer<S> {
    final /* synthetic */ Function3 $func;
    final /* synthetic */ MediatorLiveData $result;
    final /* synthetic */ LiveData $source1;
    final /* synthetic */ LiveData $source2;
    final /* synthetic */ LiveData $source3;

    LiveDataKt$combineLatest$3(MediatorLiveData mediatorLiveData, Function3 function3, LiveData liveData, LiveData liveData2, LiveData liveData3) {
        this.$result = mediatorLiveData;
        this.$func = function3;
        this.$source1 = liveData;
        this.$source2 = liveData2;
        this.$source3 = liveData3;
    }

    public final void onChanged(T1 t1) {
        this.$result.setValue(this.$func.invoke(this.$source1.getValue(), this.$source2.getValue(), this.$source3.getValue()));
    }
}
