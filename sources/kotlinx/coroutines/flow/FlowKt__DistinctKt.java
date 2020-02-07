package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.ExperimentalCoroutinesApi;
import kotlinx.coroutines.FlowPreview;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u001a\u001e\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0001H\u0007\u001a8\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00040\u0006H\u0007¨\u0006\u0007"}, mo37405d2 = {"distinctUntilChanged", "Lkotlinx/coroutines/flow/Flow;", "T", "distinctUntilChangedBy", "K", "keySelector", "Lkotlin/Function1;", "kotlinx-coroutines-core"}, mo37406k = 5, mo37407mv = {1, 1, 15}, mo37410xs = "kotlinx/coroutines/flow/FlowKt")
/* compiled from: Distinct.kt */
final /* synthetic */ class FlowKt__DistinctKt {
    @NotNull
    @ExperimentalCoroutinesApi
    public static final <T> Flow<T> distinctUntilChanged(@NotNull Flow<? extends T> flow) {
        Intrinsics.checkParameterIsNotNull(flow, "$this$distinctUntilChanged");
        return FlowKt.distinctUntilChangedBy(flow, FlowKt__DistinctKt$distinctUntilChanged$1.INSTANCE);
    }

    @NotNull
    @FlowPreview
    public static final <T, K> Flow<T> distinctUntilChangedBy(@NotNull Flow<? extends T> flow, @NotNull Function1<? super T, ? extends K> function1) {
        Intrinsics.checkParameterIsNotNull(flow, "$this$distinctUntilChangedBy");
        Intrinsics.checkParameterIsNotNull(function1, "keySelector");
        return new FlowKt__DistinctKt$distinctUntilChangedBy$$inlined$unsafeFlow$1<>(flow, function1);
    }
}
