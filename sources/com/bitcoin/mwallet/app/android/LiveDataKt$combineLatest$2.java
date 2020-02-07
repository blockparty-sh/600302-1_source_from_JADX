package com.bitcoin.mwallet.app.android;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u00042\u000e\u0010\u0005\u001a\n \u0006*\u0004\u0018\u0001H\u0003H\u0003H\n¢\u0006\u0004\b\u0007\u0010\b"}, mo37405d2 = {"<anonymous>", "", "T1", "T2", "S", "it", "kotlin.jvm.PlatformType", "onChanged", "(Ljava/lang/Object;)V"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: LiveData.kt */
final class LiveDataKt$combineLatest$2<T> implements Observer<S> {
    final /* synthetic */ Function2 $func;
    final /* synthetic */ MediatorLiveData $result;
    final /* synthetic */ LiveData $source1;
    final /* synthetic */ LiveData $source2;

    LiveDataKt$combineLatest$2(MediatorLiveData mediatorLiveData, Function2 function2, LiveData liveData, LiveData liveData2) {
        this.$result = mediatorLiveData;
        this.$func = function2;
        this.$source1 = liveData;
        this.$source2 = liveData2;
    }

    public final void onChanged(T2 t2) {
        this.$result.setValue(this.$func.invoke(this.$source1.getValue(), this.$source2.getValue()));
    }
}
