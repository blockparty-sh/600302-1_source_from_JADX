package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.ExperimentalCoroutinesApi;
import kotlinx.coroutines.FlowPreview;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ReceiveChannel;
import kotlinx.coroutines.flow.internal.FlowCoroutineKt;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a&\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0007\u001a&\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0004H\u0007\u001a&\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0004H\u0007\u001a$\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t*\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00042\b\b\u0002\u0010\r\u001a\u00020\u0004H\u0000\u001a&\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u0004H\u0007¨\u0006\u0010"}, mo37405d2 = {"debounce", "Lkotlinx/coroutines/flow/Flow;", "T", "timeoutMillis", "", "delayEach", "timeMillis", "delayFlow", "fixedPeriodTicker", "Lkotlinx/coroutines/channels/ReceiveChannel;", "", "Lkotlinx/coroutines/CoroutineScope;", "delayMillis", "initialDelayMillis", "sample", "periodMillis", "kotlinx-coroutines-core"}, mo37406k = 5, mo37407mv = {1, 1, 15}, mo37410xs = "kotlinx/coroutines/flow/FlowKt")
/* compiled from: Delay.kt */
final /* synthetic */ class FlowKt__DelayKt {
    @NotNull
    @FlowPreview
    public static final <T> Flow<T> debounce(@NotNull Flow<? extends T> flow, long j) {
        Intrinsics.checkParameterIsNotNull(flow, "$this$debounce");
        if (j > 0) {
            return FlowCoroutineKt.scopedFlow(new FlowKt__DelayKt$debounce$2(flow, j, null));
        }
        throw new IllegalArgumentException("Debounce timeout should be positive".toString());
    }

    @NotNull
    @FlowPreview
    public static final <T> Flow<T> sample(@NotNull Flow<? extends T> flow, long j) {
        Intrinsics.checkParameterIsNotNull(flow, "$this$sample");
        if (j > 0) {
            return FlowCoroutineKt.scopedFlow(new FlowKt__DelayKt$sample$2(flow, j, null));
        }
        throw new IllegalArgumentException("Sample period should be positive".toString());
    }

    @NotNull
    public static /* synthetic */ ReceiveChannel fixedPeriodTicker$default(CoroutineScope coroutineScope, long j, long j2, int i, Object obj) {
        if ((i & 2) != 0) {
            j2 = j;
        }
        return FlowKt.fixedPeriodTicker(coroutineScope, j, j2);
    }

    @NotNull
    public static final ReceiveChannel<Unit> fixedPeriodTicker(@NotNull CoroutineScope coroutineScope, long j, long j2) {
        Intrinsics.checkParameterIsNotNull(coroutineScope, "$this$fixedPeriodTicker");
        boolean z = true;
        String str = " ms";
        if (j >= 0) {
            if (j2 < 0) {
                z = false;
            }
            if (z) {
                FlowKt__DelayKt$fixedPeriodTicker$3 flowKt__DelayKt$fixedPeriodTicker$3 = new FlowKt__DelayKt$fixedPeriodTicker$3(j2, j, null);
                return ProduceKt.produce$default(coroutineScope, null, 0, flowKt__DelayKt$fixedPeriodTicker$3, 1, null);
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Expected non-negative initial delay, but has ");
            sb.append(j2);
            sb.append(str);
            throw new IllegalArgumentException(sb.toString().toString());
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Expected non-negative delay, but has ");
        sb2.append(j);
        sb2.append(str);
        throw new IllegalArgumentException(sb2.toString().toString());
    }

    @NotNull
    @ExperimentalCoroutinesApi
    public static final <T> Flow<T> delayFlow(@NotNull Flow<? extends T> flow, long j) {
        Intrinsics.checkParameterIsNotNull(flow, "$this$delayFlow");
        return new FlowKt__DelayKt$delayFlow$$inlined$unsafeFlow$1<>(flow, j);
    }

    @NotNull
    @ExperimentalCoroutinesApi
    public static final <T> Flow<T> delayEach(@NotNull Flow<? extends T> flow, long j) {
        Intrinsics.checkParameterIsNotNull(flow, "$this$delayEach");
        return new FlowKt__DelayKt$delayEach$$inlined$unsafeFlow$1<>(flow, j);
    }
}
