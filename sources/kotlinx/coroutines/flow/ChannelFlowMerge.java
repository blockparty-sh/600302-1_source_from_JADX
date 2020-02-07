package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.flow.internal.ChannelFlow;
import kotlinx.coroutines.flow.internal.ChannelFlowOperator;
import kotlinx.coroutines.flow.internal.ConcurrentFlowCollector;
import kotlinx.coroutines.flow.internal.FlowCoroutineKt;
import kotlinx.coroutines.flow.internal.SendingCollector;
import kotlinx.coroutines.sync.SemaphoreKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u0003\u0012\u0004\u0012\u0002H\u00010\u0002B5\u0012\u0012\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00030\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\u0006¢\u0006\u0002\u0010\nJ\b\u0010\u000b\u001a\u00020\fH\u0016J\u001f\u0010\r\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u0010H@ø\u0001\u0000¢\u0006\u0002\u0010\u0011J\u001e\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u00132\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0006H\u0014J\u001f\u0010\u0014\u001a\u00020\u000e2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028\u00000\u0016H@ø\u0001\u0000¢\u0006\u0002\u0010\u0017J'\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00192\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028\u00000\u001aH@ø\u0001\u0000¢\u0006\u0002\u0010\u001bR\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u001c"}, mo37405d2 = {"Lkotlinx/coroutines/flow/ChannelFlowMerge;", "T", "Lkotlinx/coroutines/flow/internal/ChannelFlowOperator;", "Lkotlinx/coroutines/flow/Flow;", "flow", "concurrency", "", "context", "Lkotlin/coroutines/CoroutineContext;", "capacity", "(Lkotlinx/coroutines/flow/Flow;ILkotlin/coroutines/CoroutineContext;I)V", "additionalToStringProps", "", "collectTo", "", "scope", "Lkotlinx/coroutines/channels/ProducerScope;", "(Lkotlinx/coroutines/channels/ProducerScope;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "create", "Lkotlinx/coroutines/flow/internal/ChannelFlow;", "flowCollect", "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "mergeImpl", "Lkotlinx/coroutines/CoroutineScope;", "Lkotlinx/coroutines/flow/internal/ConcurrentFlowCollector;", "(Lkotlinx/coroutines/CoroutineScope;Lkotlinx/coroutines/flow/internal/ConcurrentFlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: Merge.kt */
final class ChannelFlowMerge<T> extends ChannelFlowOperator<Flow<? extends T>, T> {
    private final int concurrency;

    public /* synthetic */ ChannelFlowMerge(Flow flow, int i, CoroutineContext coroutineContext, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i3 & 4) != 0) {
            coroutineContext = EmptyCoroutineContext.INSTANCE;
        }
        if ((i3 & 8) != 0) {
            i2 = -3;
        }
        this(flow, i, coroutineContext, i2);
    }

    public ChannelFlowMerge(@NotNull Flow<? extends Flow<? extends T>> flow, int i, @NotNull CoroutineContext coroutineContext, int i2) {
        Intrinsics.checkParameterIsNotNull(flow, "flow");
        Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
        super(flow, coroutineContext, i2);
        this.concurrency = i;
    }

    /* access modifiers changed from: protected */
    @NotNull
    public ChannelFlow<T> create(@NotNull CoroutineContext coroutineContext, int i) {
        Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
        return new ChannelFlowMerge<>(this.flow, this.concurrency, coroutineContext, i);
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public final /* synthetic */ Object mergeImpl(@NotNull CoroutineScope coroutineScope, @NotNull ConcurrentFlowCollector<T> concurrentFlowCollector, @NotNull Continuation<? super Unit> continuation) {
        return this.flow.collect(new ChannelFlowMerge$mergeImpl$$inlined$collect$1(SemaphoreKt.Semaphore$default(this.concurrency, 0, 2, null), coroutineScope, concurrentFlowCollector), continuation);
    }

    /* access modifiers changed from: protected */
    @Nullable
    public Object flowCollect(@NotNull FlowCollector<? super T> flowCollector, @NotNull Continuation<? super Unit> continuation) {
        if (this.capacity == -3) {
            return FlowCoroutineKt.flowScope(new ChannelFlowMerge$flowCollect$2(this, flowCollector, null), continuation);
        }
        throw new IllegalStateException("Check failed.".toString());
    }

    /* access modifiers changed from: protected */
    @Nullable
    public Object collectTo(@NotNull ProducerScope<? super T> producerScope, @NotNull Continuation<? super Unit> continuation) {
        return mergeImpl(producerScope, new SendingCollector(producerScope), continuation);
    }

    @NotNull
    public String additionalToStringProps() {
        StringBuilder sb = new StringBuilder();
        sb.append("concurrency=");
        sb.append(this.concurrency);
        sb.append(", ");
        return sb.toString();
    }
}
