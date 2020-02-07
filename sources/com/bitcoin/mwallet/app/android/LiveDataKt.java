package com.bitcoin.mwallet.app.android;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u001aZ\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0003\"\u0004\b\u0001\u0010\u0004\"\u0004\b\u0002\u0010\u00022\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00030\u00012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00040\u00012\u001e\u0010\u0007\u001a\u001a\u0012\u0006\u0012\u0004\u0018\u0001H\u0003\u0012\u0006\u0012\u0004\u0018\u0001H\u0004\u0012\u0006\u0012\u0004\u0018\u0001H\u00020\b\u001av\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0003\"\u0004\b\u0001\u0010\u0004\"\u0004\b\u0002\u0010\t\"\u0004\b\u0003\u0010\u00022\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00030\u00012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00040\u00012\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\t0\u00012&\u0010\u0007\u001a\"\u0012\u0006\u0012\u0004\u0018\u0001H\u0003\u0012\u0006\u0012\u0004\u0018\u0001H\u0004\u0012\u0006\u0012\u0004\u0018\u0001H\t\u0012\u0006\u0012\u0004\u0018\u0001H\u00020\u000b\u001aZ\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0003\"\u0004\b\u0001\u0010\u0004\"\u0004\b\u0002\u0010\u00022\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00030\u00012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00040\u00012\u001e\u0010\u0007\u001a\u001a\u0012\u0006\u0012\u0004\u0018\u0001H\u0003\u0012\u0006\u0012\u0004\u0018\u0001H\u0004\u0012\u0006\u0012\u0004\u0018\u0001H\u00020\b\u001av\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0003\"\u0004\b\u0001\u0010\u0004\"\u0004\b\u0002\u0010\t\"\u0004\b\u0003\u0010\u00022\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00030\u00012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00040\u00012\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\t0\u00012&\u0010\u0007\u001a\"\u0012\u0006\u0012\u0004\u0018\u0001H\u0003\u0012\u0006\u0012\u0004\u0018\u0001H\u0004\u0012\u0006\u0012\u0004\u0018\u0001H\t\u0012\u0006\u0012\u0004\u0018\u0001H\u00020\u000b\u001a\u0001\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0003\"\u0004\b\u0001\u0010\u0004\"\u0004\b\u0002\u0010\t\"\u0004\b\u0003\u0010\r\"\u0004\b\u0004\u0010\u00022\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00030\u00012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00040\u00012\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\t0\u00012\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\r0\u00012.\u0010\u0007\u001a*\u0012\u0006\u0012\u0004\u0018\u0001H\u0003\u0012\u0006\u0012\u0004\u0018\u0001H\u0004\u0012\u0006\u0012\u0004\u0018\u0001H\t\u0012\u0006\u0012\u0004\u0018\u0001H\r\u0012\u0006\u0012\u0004\u0018\u0001H\u00020\u000f¨\u0006\u0010"}, mo37405d2 = {"combineLatest", "Landroidx/lifecycle/LiveData;", "S", "T1", "T2", "source1", "source2", "func", "Lkotlin/Function2;", "T3", "source3", "Lkotlin/Function3;", "combineLatestIgnoreNull", "T4", "source4", "Lkotlin/Function4;", "app_replaceRelease"}, mo37406k = 2, mo37407mv = {1, 1, 15})
/* compiled from: LiveData.kt */
public final class LiveDataKt {
    @NotNull
    public static final <T1, T2, S> LiveData<S> combineLatest(@NotNull LiveData<T1> liveData, @NotNull LiveData<T2> liveData2, @NotNull Function2<? super T1, ? super T2, ? extends S> function2) {
        Intrinsics.checkParameterIsNotNull(liveData, "source1");
        Intrinsics.checkParameterIsNotNull(liveData2, "source2");
        Intrinsics.checkParameterIsNotNull(function2, "func");
        MediatorLiveData mediatorLiveData = new MediatorLiveData();
        mediatorLiveData.addSource(liveData, new LiveDataKt$combineLatest$1(mediatorLiveData, function2, liveData, liveData2));
        mediatorLiveData.addSource(liveData2, new LiveDataKt$combineLatest$2(mediatorLiveData, function2, liveData, liveData2));
        return mediatorLiveData;
    }

    @NotNull
    public static final <T1, T2, T3, S> LiveData<S> combineLatest(@NotNull LiveData<T1> liveData, @NotNull LiveData<T2> liveData2, @NotNull LiveData<T3> liveData3, @NotNull Function3<? super T1, ? super T2, ? super T3, ? extends S> function3) {
        Intrinsics.checkParameterIsNotNull(liveData, "source1");
        Intrinsics.checkParameterIsNotNull(liveData2, "source2");
        Intrinsics.checkParameterIsNotNull(liveData3, "source3");
        Intrinsics.checkParameterIsNotNull(function3, "func");
        MediatorLiveData mediatorLiveData = new MediatorLiveData();
        MediatorLiveData mediatorLiveData2 = mediatorLiveData;
        Function3<? super T1, ? super T2, ? super T3, ? extends S> function32 = function3;
        LiveData<T1> liveData4 = liveData;
        LiveData<T2> liveData5 = liveData2;
        LiveData<T3> liveData6 = liveData3;
        LiveDataKt$combineLatest$3 liveDataKt$combineLatest$3 = new LiveDataKt$combineLatest$3(mediatorLiveData2, function32, liveData4, liveData5, liveData6);
        mediatorLiveData.addSource(liveData, liveDataKt$combineLatest$3);
        LiveDataKt$combineLatest$4 liveDataKt$combineLatest$4 = new LiveDataKt$combineLatest$4(mediatorLiveData2, function32, liveData4, liveData5, liveData6);
        mediatorLiveData.addSource(liveData2, liveDataKt$combineLatest$4);
        LiveDataKt$combineLatest$5 liveDataKt$combineLatest$5 = new LiveDataKt$combineLatest$5(mediatorLiveData2, function32, liveData4, liveData5, liveData6);
        mediatorLiveData.addSource(liveData3, liveDataKt$combineLatest$5);
        return mediatorLiveData;
    }

    @NotNull
    public static final <T1, T2, S> LiveData<S> combineLatestIgnoreNull(@NotNull LiveData<T1> liveData, @NotNull LiveData<T2> liveData2, @NotNull Function2<? super T1, ? super T2, ? extends S> function2) {
        Intrinsics.checkParameterIsNotNull(liveData, "source1");
        Intrinsics.checkParameterIsNotNull(liveData2, "source2");
        Intrinsics.checkParameterIsNotNull(function2, "func");
        MediatorLiveData mediatorLiveData = new MediatorLiveData();
        mediatorLiveData.addSource(liveData, new LiveDataKt$combineLatestIgnoreNull$1(function2, liveData, liveData2, mediatorLiveData));
        mediatorLiveData.addSource(liveData2, new LiveDataKt$combineLatestIgnoreNull$2(function2, liveData, liveData2, mediatorLiveData));
        return mediatorLiveData;
    }

    @NotNull
    public static final <T1, T2, T3, S> LiveData<S> combineLatestIgnoreNull(@NotNull LiveData<T1> liveData, @NotNull LiveData<T2> liveData2, @NotNull LiveData<T3> liveData3, @NotNull Function3<? super T1, ? super T2, ? super T3, ? extends S> function3) {
        Intrinsics.checkParameterIsNotNull(liveData, "source1");
        Intrinsics.checkParameterIsNotNull(liveData2, "source2");
        Intrinsics.checkParameterIsNotNull(liveData3, "source3");
        Intrinsics.checkParameterIsNotNull(function3, "func");
        MediatorLiveData mediatorLiveData = new MediatorLiveData();
        Function3<? super T1, ? super T2, ? super T3, ? extends S> function32 = function3;
        LiveData<T1> liveData4 = liveData;
        LiveData<T2> liveData5 = liveData2;
        LiveData<T3> liveData6 = liveData3;
        MediatorLiveData mediatorLiveData2 = mediatorLiveData;
        LiveDataKt$combineLatestIgnoreNull$3 liveDataKt$combineLatestIgnoreNull$3 = new LiveDataKt$combineLatestIgnoreNull$3(function32, liveData4, liveData5, liveData6, mediatorLiveData2);
        mediatorLiveData.addSource(liveData, liveDataKt$combineLatestIgnoreNull$3);
        LiveDataKt$combineLatestIgnoreNull$4 liveDataKt$combineLatestIgnoreNull$4 = new LiveDataKt$combineLatestIgnoreNull$4(function32, liveData4, liveData5, liveData6, mediatorLiveData2);
        mediatorLiveData.addSource(liveData2, liveDataKt$combineLatestIgnoreNull$4);
        LiveDataKt$combineLatestIgnoreNull$5 liveDataKt$combineLatestIgnoreNull$5 = new LiveDataKt$combineLatestIgnoreNull$5(function32, liveData4, liveData5, liveData6, mediatorLiveData2);
        mediatorLiveData.addSource(liveData3, liveDataKt$combineLatestIgnoreNull$5);
        return mediatorLiveData;
    }

    @NotNull
    public static final <T1, T2, T3, T4, S> LiveData<S> combineLatestIgnoreNull(@NotNull LiveData<T1> liveData, @NotNull LiveData<T2> liveData2, @NotNull LiveData<T3> liveData3, @NotNull LiveData<T4> liveData4, @NotNull Function4<? super T1, ? super T2, ? super T3, ? super T4, ? extends S> function4) {
        Intrinsics.checkParameterIsNotNull(liveData, "source1");
        Intrinsics.checkParameterIsNotNull(liveData2, "source2");
        Intrinsics.checkParameterIsNotNull(liveData3, "source3");
        Intrinsics.checkParameterIsNotNull(liveData4, "source4");
        Intrinsics.checkParameterIsNotNull(function4, "func");
        MediatorLiveData mediatorLiveData = new MediatorLiveData();
        Function4<? super T1, ? super T2, ? super T3, ? super T4, ? extends S> function42 = function4;
        LiveData<T1> liveData5 = liveData;
        LiveData<T2> liveData6 = liveData2;
        LiveData<T3> liveData7 = liveData3;
        LiveData<T4> liveData8 = liveData4;
        MediatorLiveData mediatorLiveData2 = mediatorLiveData;
        LiveDataKt$combineLatestIgnoreNull$6 liveDataKt$combineLatestIgnoreNull$6 = new LiveDataKt$combineLatestIgnoreNull$6(function42, liveData5, liveData6, liveData7, liveData8, mediatorLiveData2);
        mediatorLiveData.addSource(liveData, liveDataKt$combineLatestIgnoreNull$6);
        LiveDataKt$combineLatestIgnoreNull$7 liveDataKt$combineLatestIgnoreNull$7 = new LiveDataKt$combineLatestIgnoreNull$7(function42, liveData5, liveData6, liveData7, liveData8, mediatorLiveData2);
        mediatorLiveData.addSource(liveData2, liveDataKt$combineLatestIgnoreNull$7);
        LiveDataKt$combineLatestIgnoreNull$8 liveDataKt$combineLatestIgnoreNull$8 = new LiveDataKt$combineLatestIgnoreNull$8(function42, liveData5, liveData6, liveData7, liveData8, mediatorLiveData2);
        mediatorLiveData.addSource(liveData3, liveDataKt$combineLatestIgnoreNull$8);
        LiveDataKt$combineLatestIgnoreNull$9 liveDataKt$combineLatestIgnoreNull$9 = new LiveDataKt$combineLatestIgnoreNull$9(function42, liveData5, liveData6, liveData7, liveData8, mediatorLiveData2);
        mediatorLiveData.addSource(liveData4, liveDataKt$combineLatestIgnoreNull$9);
        return mediatorLiveData;
    }
}
