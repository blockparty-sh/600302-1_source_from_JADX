package kotlinx.coroutines.flow;

import com.leanplum.internal.Constants.Methods;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.FlowPreview;
import kotlinx.coroutines.channels.BroadcastChannel;
import kotlinx.coroutines.channels.ReceiveChannel;
import kotlinx.coroutines.flow.internal.ChannelFlowKt;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u001e\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u0007\u001a0\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bH\u0007\u001a&\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00020\n\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u000b"}, mo37405d2 = {"asFlow", "Lkotlinx/coroutines/flow/Flow;", "T", "Lkotlinx/coroutines/channels/BroadcastChannel;", "broadcastIn", "scope", "Lkotlinx/coroutines/CoroutineScope;", "start", "Lkotlinx/coroutines/CoroutineStart;", "produceIn", "Lkotlinx/coroutines/channels/ReceiveChannel;", "kotlinx-coroutines-core"}, mo37406k = 5, mo37407mv = {1, 1, 15}, mo37410xs = "kotlinx/coroutines/flow/FlowKt")
/* compiled from: Channels.kt */
final /* synthetic */ class FlowKt__ChannelsKt {
    @NotNull
    @FlowPreview
    public static final <T> Flow<T> asFlow(@NotNull BroadcastChannel<T> broadcastChannel) {
        Intrinsics.checkParameterIsNotNull(broadcastChannel, "$this$asFlow");
        return FlowKt.flow(new FlowKt__ChannelsKt$asFlow$1(broadcastChannel, null));
    }

    @NotNull
    @FlowPreview
    public static /* synthetic */ BroadcastChannel broadcastIn$default(Flow flow, CoroutineScope coroutineScope, CoroutineStart coroutineStart, int i, Object obj) {
        if ((i & 2) != 0) {
            coroutineStart = CoroutineStart.LAZY;
        }
        return FlowKt.broadcastIn(flow, coroutineScope, coroutineStart);
    }

    @NotNull
    @FlowPreview
    public static final <T> BroadcastChannel<T> broadcastIn(@NotNull Flow<? extends T> flow, @NotNull CoroutineScope coroutineScope, @NotNull CoroutineStart coroutineStart) {
        Intrinsics.checkParameterIsNotNull(flow, "$this$broadcastIn");
        Intrinsics.checkParameterIsNotNull(coroutineScope, "scope");
        Intrinsics.checkParameterIsNotNull(coroutineStart, Methods.START);
        return ChannelFlowKt.asChannelFlow(flow).broadcastImpl(coroutineScope, coroutineStart);
    }

    @NotNull
    @FlowPreview
    public static final <T> ReceiveChannel<T> produceIn(@NotNull Flow<? extends T> flow, @NotNull CoroutineScope coroutineScope) {
        Intrinsics.checkParameterIsNotNull(flow, "$this$produceIn");
        Intrinsics.checkParameterIsNotNull(coroutineScope, "scope");
        return ChannelFlowKt.asChannelFlow(flow).produceImpl(coroutineScope);
    }
}
