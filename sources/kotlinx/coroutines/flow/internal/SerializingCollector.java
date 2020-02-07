package kotlinx.coroutines.flow.internal;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.channels.ArrayChannel;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.flow.FlowCollector;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00028\u00000\u0002B\u0015\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u001b\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00028\u0000H@ø\u0001\u0000¢\u0006\u0004\b\t\u0010\nJ\u0013\u0010\u000b\u001a\u00020\bH@ø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\fR\u001e\u0010\u0011\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\r8B@\u0002X\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u001c\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u00038\u0002@\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010\u0012\u0002\u0004\n\u0002\b\u0019¨\u0006\u0013"}, mo37405d2 = {"Lkotlinx/coroutines/flow/internal/SerializingCollector;", "T", "Lkotlinx/coroutines/flow/internal/ConcurrentFlowCollector;", "Lkotlinx/coroutines/flow/FlowCollector;", "downstream", "<init>", "(Lkotlinx/coroutines/flow/FlowCollector;)V", "value", "", "emit", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "helpEmit", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lkotlinx/coroutines/channels/ArrayChannel;", "", "getChannel", "()Lkotlinx/coroutines/channels/ArrayChannel;", "channel", "Lkotlinx/coroutines/flow/FlowCollector;", "kotlinx-coroutines-core"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: Concurrent.kt */
final class SerializingCollector<T> implements ConcurrentFlowCollector<T> {
    private static final AtomicReferenceFieldUpdater _channel$FU = AtomicReferenceFieldUpdater.newUpdater(SerializingCollector.class, Object.class, "_channel");
    private static final AtomicIntegerFieldUpdater inProgressLock$FU = AtomicIntegerFieldUpdater.newUpdater(SerializingCollector.class, "inProgressLock");
    private volatile Object _channel = null;
    private final FlowCollector<T> downstream;
    private volatile int inProgressLock = 0;

    public SerializingCollector(@NotNull FlowCollector<? super T> flowCollector) {
        Intrinsics.checkParameterIsNotNull(flowCollector, "downstream");
        this.downstream = flowCollector;
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0061  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x008c  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x009a  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00b9 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00ba A[PHI: r10 
      PHI: (r10v2 java.lang.Object) = (r10v3 java.lang.Object), (r10v1 java.lang.Object) binds: [B:41:0x00b7, B:12:0x0030] A[DONT_GENERATE, DONT_INLINE], RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0028  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object emit(T r9, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r10) {
        /*
            r8 = this;
            boolean r0 = r10 instanceof kotlinx.coroutines.flow.internal.SerializingCollector$emit$1
            if (r0 == 0) goto L_0x0014
            r0 = r10
            kotlinx.coroutines.flow.internal.SerializingCollector$emit$1 r0 = (kotlinx.coroutines.flow.internal.SerializingCollector$emit$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r10 = r0.label
            int r10 = r10 - r2
            r0.label = r10
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.flow.internal.SerializingCollector$emit$1 r0 = new kotlinx.coroutines.flow.internal.SerializingCollector$emit$1
            r0.<init>(r8, r10)
        L_0x0019:
            java.lang.Object r10 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            r4 = 4
            r5 = 3
            r6 = 2
            r7 = 1
            if (r2 == 0) goto L_0x0061
            if (r2 == r7) goto L_0x0057
            if (r2 == r6) goto L_0x004d
            if (r2 == r5) goto L_0x0043
            if (r2 != r4) goto L_0x003b
            java.lang.Object r9 = r0.L$1
            java.lang.Object r9 = r0.L$0
            kotlinx.coroutines.flow.internal.SerializingCollector r9 = (kotlinx.coroutines.flow.internal.SerializingCollector) r9
            kotlin.ResultKt.throwOnFailure(r10)
            goto L_0x00ba
        L_0x003b:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L_0x0043:
            java.lang.Object r9 = r0.L$1
            java.lang.Object r2 = r0.L$0
            kotlinx.coroutines.flow.internal.SerializingCollector r2 = (kotlinx.coroutines.flow.internal.SerializingCollector) r2
            kotlin.ResultKt.throwOnFailure(r10)
            goto L_0x00ad
        L_0x004d:
            java.lang.Object r9 = r0.L$1
            java.lang.Object r9 = r0.L$0
            kotlinx.coroutines.flow.internal.SerializingCollector r9 = (kotlinx.coroutines.flow.internal.SerializingCollector) r9
            kotlin.ResultKt.throwOnFailure(r10)
            goto L_0x0099
        L_0x0057:
            java.lang.Object r9 = r0.L$1
            java.lang.Object r2 = r0.L$0
            kotlinx.coroutines.flow.internal.SerializingCollector r2 = (kotlinx.coroutines.flow.internal.SerializingCollector) r2
            kotlin.ResultKt.throwOnFailure(r10)
            goto L_0x0084
        L_0x0061:
            kotlin.ResultKt.throwOnFailure(r10)
            java.util.concurrent.atomic.AtomicIntegerFieldUpdater r10 = inProgressLock$FU
            boolean r10 = r10.compareAndSet(r8, r3, r7)
            if (r10 != 0) goto L_0x009d
            kotlinx.coroutines.channels.ArrayChannel r10 = r8.getChannel()
            if (r9 == 0) goto L_0x0074
            r2 = r9
            goto L_0x0076
        L_0x0074:
            kotlinx.coroutines.internal.Symbol r2 = kotlinx.coroutines.flow.internal.NullSurrogateKt.NULL
        L_0x0076:
            r0.L$0 = r8
            r0.L$1 = r9
            r0.label = r7
            java.lang.Object r10 = r10.send(r2, r0)
            if (r10 != r1) goto L_0x0083
            return r1
        L_0x0083:
            r2 = r8
        L_0x0084:
            java.util.concurrent.atomic.AtomicIntegerFieldUpdater r10 = inProgressLock$FU
            boolean r10 = r10.compareAndSet(r2, r3, r7)
            if (r10 == 0) goto L_0x009a
            r0.L$0 = r2
            r0.L$1 = r9
            r0.label = r6
            java.lang.Object r10 = r2.helpEmit(r0)
            if (r10 != r1) goto L_0x0099
            return r1
        L_0x0099:
            return r10
        L_0x009a:
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        L_0x009d:
            kotlinx.coroutines.flow.FlowCollector<T> r10 = r8.downstream
            r0.L$0 = r8
            r0.L$1 = r9
            r0.label = r5
            java.lang.Object r10 = r10.emit(r9, r0)
            if (r10 != r1) goto L_0x00ac
            return r1
        L_0x00ac:
            r2 = r8
        L_0x00ad:
            r0.L$0 = r2
            r0.L$1 = r9
            r0.label = r4
            java.lang.Object r10 = r2.helpEmit(r0)
            if (r10 != r1) goto L_0x00ba
            return r1
        L_0x00ba:
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.internal.SerializingCollector.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0039  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0043  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ java.lang.Object helpEmit(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r7) {
        /*
            r6 = this;
            boolean r0 = r7 instanceof kotlinx.coroutines.flow.internal.SerializingCollector$helpEmit$1
            if (r0 == 0) goto L_0x0014
            r0 = r7
            kotlinx.coroutines.flow.internal.SerializingCollector$helpEmit$1 r0 = (kotlinx.coroutines.flow.internal.SerializingCollector$helpEmit$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.flow.internal.SerializingCollector$helpEmit$1 r0 = new kotlinx.coroutines.flow.internal.SerializingCollector$helpEmit$1
            r0.<init>(r6, r7)
        L_0x0019:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0039
            if (r2 != r3) goto L_0x0031
            java.lang.Object r2 = r0.L$1
            java.lang.Object r2 = r0.L$0
            kotlinx.coroutines.flow.internal.SerializingCollector r2 = (kotlinx.coroutines.flow.internal.SerializingCollector) r2
            kotlin.ResultKt.throwOnFailure(r7)
            r7 = r2
            goto L_0x003d
        L_0x0031:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r0)
            throw r7
        L_0x0039:
            kotlin.ResultKt.throwOnFailure(r7)
            r7 = r6
        L_0x003d:
            java.lang.Object r2 = r7._channel
            kotlinx.coroutines.channels.ArrayChannel r2 = (kotlinx.coroutines.channels.ArrayChannel) r2
            if (r2 == 0) goto L_0x005f
            java.lang.Object r2 = r2.poll()
            if (r2 == 0) goto L_0x005f
            kotlinx.coroutines.flow.FlowCollector<T> r4 = r7.downstream
            kotlinx.coroutines.internal.Symbol r5 = kotlinx.coroutines.flow.internal.NullSurrogateKt.NULL
            if (r2 != r5) goto L_0x0051
            r5 = 0
            goto L_0x0052
        L_0x0051:
            r5 = r2
        L_0x0052:
            r0.L$0 = r7
            r0.L$1 = r2
            r0.label = r3
            java.lang.Object r2 = r4.emit(r5, r0)
            if (r2 != r1) goto L_0x003d
            return r1
        L_0x005f:
            r2 = 0
            r7.inProgressLock = r2
            java.lang.Object r4 = r7._channel
            kotlinx.coroutines.channels.ArrayChannel r4 = (kotlinx.coroutines.channels.ArrayChannel) r4
            if (r4 == 0) goto L_0x0076
            boolean r4 = r4.isEmpty()
            if (r4 != 0) goto L_0x0076
            java.util.concurrent.atomic.AtomicIntegerFieldUpdater r4 = inProgressLock$FU
            boolean r2 = r4.compareAndSet(r7, r2, r3)
            if (r2 != 0) goto L_0x003d
        L_0x0076:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.internal.SerializingCollector.helpEmit(kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final ArrayChannel<Object> getChannel() {
        Object obj;
        ArrayChannel<Object> arrayChannel;
        do {
            obj = this._channel;
            ArrayChannel<Object> arrayChannel2 = (ArrayChannel) obj;
            if (arrayChannel2 != null) {
                return arrayChannel2;
            }
            arrayChannel = new ArrayChannel<>(Channel.Factory.getCHANNEL_DEFAULT_CAPACITY$kotlinx_coroutines_core());
        } while (!_channel$FU.compareAndSet(this, obj, arrayChannel));
        return arrayChannel;
    }
}
