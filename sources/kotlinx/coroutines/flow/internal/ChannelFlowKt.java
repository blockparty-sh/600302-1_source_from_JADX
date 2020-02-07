package kotlinx.coroutines.flow.internal;

import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.internal.ThreadContextKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000.\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a[\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001\"\u0004\b\u0001\u0010\u00022\u0006\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\"\u0010\u0007\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\t\u0012\u0006\u0012\u0004\u0018\u00010\u00060\b2\u0006\u0010\n\u001a\u0002H\u0002H@ø\u0001\u0000¢\u0006\u0002\u0010\u000b\u001a\u001e\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u00010\r\"\u0004\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u000eH\u0000\u001a&\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0010\"\u0004\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u0004H\u0002\u0002\u0004\n\u0002\b\u0019¨\u0006\u0012"}, mo37405d2 = {"withContextUndispatched", "T", "V", "newContext", "Lkotlin/coroutines/CoroutineContext;", "countOrElement", "", "block", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "value", "(Lkotlin/coroutines/CoroutineContext;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "asChannelFlow", "Lkotlinx/coroutines/flow/internal/ChannelFlow;", "Lkotlinx/coroutines/flow/Flow;", "withUndispatchedContextCollector", "Lkotlinx/coroutines/flow/FlowCollector;", "emitContext", "kotlinx-coroutines-core"}, mo37406k = 2, mo37407mv = {1, 1, 15})
/* compiled from: ChannelFlow.kt */
public final class ChannelFlowKt {
    @NotNull
    public static final <T> ChannelFlow<T> asChannelFlow(@NotNull Flow<? extends T> flow) {
        Intrinsics.checkParameterIsNotNull(flow, "$this$asChannelFlow");
        ChannelFlow<T> channelFlow = (ChannelFlow) (!(flow instanceof ChannelFlow) ? null : flow);
        if (channelFlow != null) {
            return channelFlow;
        }
        ChannelFlowOperatorImpl channelFlowOperatorImpl = new ChannelFlowOperatorImpl(flow, null, 0, 6, null);
        return channelFlowOperatorImpl;
    }

    /* access modifiers changed from: private */
    public static final <T> FlowCollector<T> withUndispatchedContextCollector(@NotNull FlowCollector<? super T> flowCollector, CoroutineContext coroutineContext) {
        if (flowCollector instanceof SendingCollector) {
            return flowCollector;
        }
        if (flowCollector instanceof ConcurrentFlowCollector) {
            return new ConcurrentUndispatchedContextCollector<>((ConcurrentFlowCollector) flowCollector, coroutineContext);
        }
        return new UndispatchedContextCollector<>(flowCollector, coroutineContext);
    }

    @Nullable
    static /* synthetic */ Object withContextUndispatched$default(CoroutineContext coroutineContext, Object obj, Function2 function2, Object obj2, Continuation continuation, int i, Object obj3) {
        if ((i & 2) != 0) {
            obj = ThreadContextKt.threadContextElements(coroutineContext);
        }
        return withContextUndispatched(coroutineContext, obj, function2, obj2, continuation);
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: 0000 */
    @Nullable
    public static final /* synthetic */ <T, V> Object withContextUndispatched(@NotNull CoroutineContext coroutineContext, @NotNull Object obj, @NotNull Function2<? super V, ? super Continuation<? super T>, ? extends Object> function2, V v, @NotNull Continuation<? super T> continuation) {
        Object updateThreadContext = ThreadContextKt.updateThreadContext(coroutineContext, obj);
        try {
            C3268x9e72d8c3 channelFlowKt$withContextUndispatched$$inlined$suspendCoroutineUninterceptedOrReturn$lambda$1 = new C3268x9e72d8c3(coroutineContext, continuation, coroutineContext, obj, function2, v);
            Continuation continuation2 = channelFlowKt$withContextUndispatched$$inlined$suspendCoroutineUninterceptedOrReturn$lambda$1;
            if (function2 != null) {
                Object invoke = ((Function2) TypeIntrinsics.beforeCheckcastToFunctionOfArity(function2, 2)).invoke(v, continuation2);
                ThreadContextKt.restoreThreadContext(coroutineContext, updateThreadContext);
                if (invoke == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                    DebugProbesKt.probeCoroutineSuspended(continuation);
                }
                return invoke;
            }
            throw new TypeCastException("null cannot be cast to non-null type (R, kotlin.coroutines.Continuation<T>) -> kotlin.Any?");
        } catch (Throwable th) {
            ThreadContextKt.restoreThreadContext(coroutineContext, updateThreadContext);
            throw th;
        }
    }
}
