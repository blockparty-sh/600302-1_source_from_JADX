package com.bitcoin.mwallet.app.android;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import kotlin.Metadata;
import kotlin.jvm.functions.Function4;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\t\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0004\"\u0004\b\u0003\u0010\u0005\"\u0004\b\u0004\u0010\u00062\u000e\u0010\u0007\u001a\n \b*\u0004\u0018\u0001H\u0003H\u0003H\n¢\u0006\u0004\b\t\u0010\n"}, mo37405d2 = {"<anonymous>", "", "T1", "T2", "T3", "T4", "S", "it", "kotlin.jvm.PlatformType", "onChanged", "(Ljava/lang/Object;)V"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: LiveData.kt */
final class LiveDataKt$combineLatestIgnoreNull$7<T> implements Observer<S> {
    final /* synthetic */ Function4 $func;
    final /* synthetic */ MediatorLiveData $result;
    final /* synthetic */ LiveData $source1;
    final /* synthetic */ LiveData $source2;
    final /* synthetic */ LiveData $source3;
    final /* synthetic */ LiveData $source4;

    LiveDataKt$combineLatestIgnoreNull$7(Function4 function4, LiveData liveData, LiveData liveData2, LiveData liveData3, LiveData liveData4, MediatorLiveData mediatorLiveData) {
        this.$func = function4;
        this.$source1 = liveData;
        this.$source2 = liveData2;
        this.$source3 = liveData3;
        this.$source4 = liveData4;
        this.$result = mediatorLiveData;
    }

    public final void onChanged(T2 t2) {
        Object invoke = this.$func.invoke(this.$source1.getValue(), this.$source2.getValue(), this.$source3.getValue(), this.$source4.getValue());
        if (invoke != null) {
            this.$result.setValue(invoke);
        }
    }
}
