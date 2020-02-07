package kotlinx.coroutines.flow.internal;

import com.leanplum.internal.Constants.Methods;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.channels.BroadcastChannel;
import kotlinx.coroutines.channels.BroadcastKt;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.channels.ReceiveChannel;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\b \u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\u001c\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028\u00000\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aJ\u001f\u0010\u001b\u001a\u00020\f2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00028\u00000\u001dH@ø\u0001\u0000¢\u0006\u0002\u0010\u001eJ\u001f\u0010\u001f\u001a\u00020\f2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00028\u00000\nH¤@ø\u0001\u0000¢\u0006\u0002\u0010 J\u001e\u0010!\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H$J\u0014\u0010\"\u001a\b\u0012\u0004\u0012\u00028\u00000#2\u0006\u0010\u0017\u001a\u00020\u0018J\b\u0010$\u001a\u00020\u0014H\u0016J \u0010%\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0006R\u0010\u0010\u0005\u001a\u00020\u00068\u0006X\u0004¢\u0006\u0002\n\u0000R9\u0010\b\u001a$\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\n\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b\u0012\u0006\u0012\u0004\u0018\u00010\r0\t8BX\u0004ø\u0001\u0000¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\u00020\u00068BX\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012\u0002\u0004\n\u0002\b\u0019¨\u0006&"}, mo37405d2 = {"Lkotlinx/coroutines/flow/internal/ChannelFlow;", "T", "Lkotlinx/coroutines/flow/Flow;", "context", "Lkotlin/coroutines/CoroutineContext;", "capacity", "", "(Lkotlin/coroutines/CoroutineContext;I)V", "collectToFun", "Lkotlin/Function2;", "Lkotlinx/coroutines/channels/ProducerScope;", "Lkotlin/coroutines/Continuation;", "", "", "getCollectToFun", "()Lkotlin/jvm/functions/Function2;", "produceCapacity", "getProduceCapacity", "()I", "additionalToStringProps", "", "broadcastImpl", "Lkotlinx/coroutines/channels/BroadcastChannel;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "start", "Lkotlinx/coroutines/CoroutineStart;", "collect", "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "collectTo", "(Lkotlinx/coroutines/channels/ProducerScope;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "create", "produceImpl", "Lkotlinx/coroutines/channels/ReceiveChannel;", "toString", "update", "kotlinx-coroutines-core"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: ChannelFlow.kt */
public abstract class ChannelFlow<T> implements Flow<T> {
    @JvmField
    public final int capacity;
    @NotNull
    @JvmField
    public final CoroutineContext context;

    @NotNull
    public String additionalToStringProps() {
        return "";
    }

    @Nullable
    public Object collect(@NotNull FlowCollector<? super T> flowCollector, @NotNull Continuation<? super Unit> continuation) {
        return CoroutineScopeKt.coroutineScope(new ChannelFlow$collect$2(this, flowCollector, null), continuation);
    }

    /* access modifiers changed from: protected */
    @Nullable
    public abstract Object collectTo(@NotNull ProducerScope<? super T> producerScope, @NotNull Continuation<? super Unit> continuation);

    /* access modifiers changed from: protected */
    @NotNull
    public abstract ChannelFlow<T> create(@NotNull CoroutineContext coroutineContext, int i);

    public ChannelFlow(@NotNull CoroutineContext coroutineContext, int i) {
        Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
        this.context = coroutineContext;
        this.capacity = i;
    }

    @NotNull
    public static /* synthetic */ ChannelFlow update$default(ChannelFlow channelFlow, CoroutineContext coroutineContext, int i, int i2, Object obj) {
        if (obj == null) {
            if ((i2 & 1) != 0) {
                coroutineContext = EmptyCoroutineContext.INSTANCE;
            }
            if ((i2 & 2) != 0) {
                i = -3;
            }
            return channelFlow.update(coroutineContext, i);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: update");
    }

    @NotNull
    public final ChannelFlow<T> update(@NotNull CoroutineContext coroutineContext, int i) {
        Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
        CoroutineContext plus = coroutineContext.plus(this.context);
        int i2 = this.capacity;
        if (i2 != -3) {
            if (i != -3) {
                if (i2 != -2) {
                    if (i != -2) {
                        if (i2 == -1 || i == -1) {
                            i = -1;
                        } else {
                            boolean z = true;
                            String str = "Unexpected capacity ";
                            if (i2 >= 0) {
                                if (i < 0) {
                                    z = false;
                                }
                                if (z) {
                                    i += this.capacity;
                                    if (i < 0) {
                                        i = Integer.MAX_VALUE;
                                    }
                                } else {
                                    StringBuilder sb = new StringBuilder();
                                    sb.append(str);
                                    sb.append(i);
                                    throw new IllegalStateException(sb.toString().toString());
                                }
                            } else {
                                StringBuilder sb2 = new StringBuilder();
                                sb2.append(str);
                                sb2.append(this.capacity);
                                throw new IllegalStateException(sb2.toString().toString());
                            }
                        }
                    }
                }
            }
            i = i2;
        }
        if (!Intrinsics.areEqual((Object) plus, (Object) this.context) || i != this.capacity) {
            return create(plus, i);
        }
        return this;
    }

    private final Function2<ProducerScope<? super T>, Continuation<? super Unit>, Object> getCollectToFun() {
        return new ChannelFlow$collectToFun$1<>(this, null);
    }

    private final int getProduceCapacity() {
        int i = this.capacity;
        if (i == -3) {
            return -2;
        }
        return i;
    }

    @NotNull
    public final BroadcastChannel<T> broadcastImpl(@NotNull CoroutineScope coroutineScope, @NotNull CoroutineStart coroutineStart) {
        Intrinsics.checkParameterIsNotNull(coroutineScope, "scope");
        Intrinsics.checkParameterIsNotNull(coroutineStart, Methods.START);
        return BroadcastKt.broadcast$default(coroutineScope, this.context, getProduceCapacity(), coroutineStart, null, getCollectToFun(), 8, null);
    }

    @NotNull
    public final ReceiveChannel<T> produceImpl(@NotNull CoroutineScope coroutineScope) {
        Intrinsics.checkParameterIsNotNull(coroutineScope, "scope");
        return FlowCoroutineKt.flowProduce(coroutineScope, this.context, getProduceCapacity(), getCollectToFun());
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(DebugKt.getClassSimpleName(this));
        sb.append('[');
        sb.append(additionalToStringProps());
        sb.append("context=");
        sb.append(this.context);
        sb.append(", capacity=");
        sb.append(this.capacity);
        sb.append(']');
        return sb.toString();
    }
}
