package kotlinx.coroutines.flow.internal;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\b \u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\b\u0012\u0004\u0012\u0002H\u00020\u0003B#\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u001f\u0010\u000b\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00010\u000eH@ø\u0001\u0000¢\u0006\u0002\u0010\u000fJ\u001f\u0010\u0010\u001a\u00020\f2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00028\u00010\u0012H@ø\u0001\u0000¢\u0006\u0002\u0010\u0013J'\u0010\u0014\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00010\u000e2\u0006\u0010\u0015\u001a\u00020\u0007H@ø\u0001\u0000¢\u0006\u0002\u0010\u0016J\u001f\u0010\u0017\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00010\u000eH¤@ø\u0001\u0000¢\u0006\u0002\u0010\u000fJ\b\u0010\u0018\u001a\u00020\u0019H\u0016R\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u00058\u0006X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u001a"}, mo37405d2 = {"Lkotlinx/coroutines/flow/internal/ChannelFlowOperator;", "S", "T", "Lkotlinx/coroutines/flow/internal/ChannelFlow;", "flow", "Lkotlinx/coroutines/flow/Flow;", "context", "Lkotlin/coroutines/CoroutineContext;", "capacity", "", "(Lkotlinx/coroutines/flow/Flow;Lkotlin/coroutines/CoroutineContext;I)V", "collect", "", "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "collectTo", "scope", "Lkotlinx/coroutines/channels/ProducerScope;", "(Lkotlinx/coroutines/channels/ProducerScope;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "collectWithContextUndispatched", "newContext", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/CoroutineContext;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "flowCollect", "toString", "", "kotlinx-coroutines-core"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: ChannelFlow.kt */
public abstract class ChannelFlowOperator<S, T> extends ChannelFlow<T> {
    @NotNull
    @JvmField
    public final Flow<S> flow;

    @Nullable
    public Object collect(@NotNull FlowCollector<? super T> flowCollector, @NotNull Continuation<? super Unit> continuation) {
        return collect$suspendImpl(this, flowCollector, continuation);
    }

    /* access modifiers changed from: protected */
    @Nullable
    public Object collectTo(@NotNull ProducerScope<? super T> producerScope, @NotNull Continuation<? super Unit> continuation) {
        return flowCollect(new SendingCollector(producerScope), continuation);
    }

    /* access modifiers changed from: protected */
    @Nullable
    public abstract Object flowCollect(@NotNull FlowCollector<? super T> flowCollector, @NotNull Continuation<? super Unit> continuation);

    public ChannelFlowOperator(@NotNull Flow<? extends S> flow2, @NotNull CoroutineContext coroutineContext, int i) {
        Intrinsics.checkParameterIsNotNull(flow2, "flow");
        Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
        super(coroutineContext, i);
        this.flow = flow2;
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public final /* synthetic */ Object collectWithContextUndispatched(@NotNull FlowCollector<? super T> flowCollector, @NotNull CoroutineContext coroutineContext, @NotNull Continuation<? super Unit> continuation) {
        return ChannelFlowKt.withContextUndispatched$default(coroutineContext, null, new ChannelFlowOperator$collectWithContextUndispatched$2(this, null), ChannelFlowKt.withUndispatchedContextCollector(flowCollector, continuation.getContext()), continuation, 2, null);
    }

    static /* synthetic */ Object collect$suspendImpl(ChannelFlowOperator channelFlowOperator, FlowCollector flowCollector, Continuation continuation) {
        if (channelFlowOperator.capacity == -3) {
            CoroutineContext context = continuation.getContext();
            CoroutineContext plus = context.plus(channelFlowOperator.context);
            if (Intrinsics.areEqual((Object) plus, (Object) context)) {
                return channelFlowOperator.flowCollect(flowCollector, continuation);
            }
            if (Intrinsics.areEqual((Object) (ContinuationInterceptor) plus.get(ContinuationInterceptor.Key), (Object) (ContinuationInterceptor) context.get(ContinuationInterceptor.Key))) {
                return channelFlowOperator.collectWithContextUndispatched(flowCollector, plus, continuation);
            }
        }
        return super.collect(flowCollector, continuation);
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.flow);
        sb.append(" -> ");
        sb.append(super.toString());
        return sb.toString();
    }
}
