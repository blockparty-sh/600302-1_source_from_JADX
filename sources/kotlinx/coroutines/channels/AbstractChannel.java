package kotlinx.coroutines.channels;

import java.util.concurrent.CancellationException;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.Result.Companion;
import kotlin.ResultKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationKt;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancelHandler;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.channels.ChannelIterator.DefaultImpls;
import kotlinx.coroutines.internal.LockFreeLinkedListHead;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.internal.LockFreeLinkedListNode.AddLastDesc;
import kotlinx.coroutines.internal.LockFreeLinkedListNode.RemoveFirstDesc;
import kotlinx.coroutines.internal.StackTraceRecoveryKt;
import kotlinx.coroutines.intrinsics.UndispatchedKt;
import kotlinx.coroutines.selects.SelectClause1;
import kotlinx.coroutines.selects.SelectInstance;
import kotlinx.coroutines.selects.SelectKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b \u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003:\b@ABCDEFGB\u0005¢\u0006\u0002\u0010\u0004J\u0012\u0010\u0013\u001a\u00020\u00062\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0007J\u0016\u0010\u0013\u001a\u00020\u00162\u000e\u0010\u0014\u001a\n\u0018\u00010\u0017j\u0004\u0018\u0001`\u0018J\u0017\u0010\u0019\u001a\u00020\u00062\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0010¢\u0006\u0002\b\u001aJ\b\u0010\u001b\u001a\u00020\u0016H\u0014J\u000e\u0010\u001c\u001a\b\u0012\u0004\u0012\u00028\u00000\u001dH\u0004J\u0016\u0010\u001e\u001a\u00020\u00062\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00028\u00000 H\u0002J\u000f\u0010!\u001a\b\u0012\u0004\u0012\u00028\u00000\"H\u0002J\b\u0010#\u001a\u00020\u0016H\u0014J\b\u0010$\u001a\u00020\u0016H\u0014J\r\u0010%\u001a\u0004\u0018\u00018\u0000¢\u0006\u0002\u0010&J\n\u0010'\u001a\u0004\u0018\u00010(H\u0014J\u0016\u0010)\u001a\u0004\u0018\u00010(2\n\u0010*\u001a\u0006\u0012\u0002\b\u00030+H\u0014J\u0011\u0010\u001f\u001a\u00028\u0000H@ø\u0001\u0000¢\u0006\u0002\u0010,J\u0013\u0010-\u001a\u0004\u0018\u00018\u0000H@ø\u0001\u0000¢\u0006\u0002\u0010,J\u0019\u0010.\u001a\u0004\u0018\u00018\u00002\b\u0010/\u001a\u0004\u0018\u00010(H\u0002¢\u0006\u0002\u00100J\u0013\u00101\u001a\u0004\u0018\u00018\u0000H@ø\u0001\u0000¢\u0006\u0002\u0010,J\u0017\u00102\u001a\u00028\u00002\b\u0010/\u001a\u0004\u0018\u00010(H\u0002¢\u0006\u0002\u00100J\u0011\u00103\u001a\u00028\u0000H@ø\u0001\u0000¢\u0006\u0002\u0010,JH\u00104\u001a\u00020\u0016\"\u0004\b\u0001\u001052\f\u0010*\u001a\b\u0012\u0004\u0012\u0002H50+2\"\u00106\u001a\u001e\b\u0001\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u0002H508\u0012\u0006\u0012\u0004\u0018\u00010(07H\u0002ø\u0001\u0000¢\u0006\u0002\u00109JJ\u0010:\u001a\u00020\u0016\"\u0004\b\u0001\u001052\f\u0010*\u001a\b\u0012\u0004\u0012\u0002H50+2$\u00106\u001a \b\u0001\u0012\u0006\u0012\u0004\u0018\u00018\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u0002H508\u0012\u0006\u0012\u0004\u0018\u00010(07H\u0002ø\u0001\u0000¢\u0006\u0002\u00109J \u0010;\u001a\u00020\u00162\n\u0010<\u001a\u0006\u0012\u0002\b\u00030=2\n\u0010\u001f\u001a\u0006\u0012\u0002\b\u00030 H\u0002J\u0010\u0010>\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010?H\u0014R\u0014\u0010\u0005\u001a\u00020\u00068DX\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0012\u0010\t\u001a\u00020\u0006X¤\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\bR\u0012\u0010\n\u001a\u00020\u0006X¤\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\bR\u0011\u0010\u000b\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\bR\u0011\u0010\f\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\f\u0010\bR\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000\u000e8F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0019\u0010\u0011\u001a\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u000e8F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0010\u0002\u0004\n\u0002\b\u0019¨\u0006H"}, mo37405d2 = {"Lkotlinx/coroutines/channels/AbstractChannel;", "E", "Lkotlinx/coroutines/channels/AbstractSendChannel;", "Lkotlinx/coroutines/channels/Channel;", "()V", "hasReceiveOrClosed", "", "getHasReceiveOrClosed", "()Z", "isBufferAlwaysEmpty", "isBufferEmpty", "isClosedForReceive", "isEmpty", "onReceive", "Lkotlinx/coroutines/selects/SelectClause1;", "getOnReceive", "()Lkotlinx/coroutines/selects/SelectClause1;", "onReceiveOrNull", "getOnReceiveOrNull", "cancel", "cause", "", "", "Ljava/util/concurrent/CancellationException;", "Lkotlinx/coroutines/CancellationException;", "cancelInternal", "cancelInternal$kotlinx_coroutines_core", "cleanupSendQueueOnCancel", "describeTryPoll", "Lkotlinx/coroutines/channels/AbstractChannel$TryPollDesc;", "enqueueReceive", "receive", "Lkotlinx/coroutines/channels/Receive;", "iterator", "Lkotlinx/coroutines/channels/ChannelIterator;", "onReceiveDequeued", "onReceiveEnqueued", "poll", "()Ljava/lang/Object;", "pollInternal", "", "pollSelectInternal", "select", "Lkotlinx/coroutines/selects/SelectInstance;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "receiveOrNull", "receiveOrNullResult", "result", "(Ljava/lang/Object;)Ljava/lang/Object;", "receiveOrNullSuspend", "receiveResult", "receiveSuspend", "registerSelectReceive", "R", "block", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "(Lkotlinx/coroutines/selects/SelectInstance;Lkotlin/jvm/functions/Function2;)V", "registerSelectReceiveOrNull", "removeReceiveOnCancel", "cont", "Lkotlinx/coroutines/CancellableContinuation;", "takeFirstReceiveOrPeekClosed", "Lkotlinx/coroutines/channels/ReceiveOrClosed;", "IdempotentTokenValue", "Itr", "ReceiveElement", "ReceiveHasNext", "ReceiveSelect", "RemoveReceiveOnCancel", "TryEnqueueReceiveDesc", "TryPollDesc", "kotlinx-coroutines-core"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: AbstractChannel.kt */
public abstract class AbstractChannel<E> extends AbstractSendChannel<E> implements Channel<E> {

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0005\b\u0002\u0018\u0000*\u0006\b\u0001\u0010\u0001 \u00012\u00020\u0002B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00028\u0001¢\u0006\u0002\u0010\u0005R\u0010\u0010\u0003\u001a\u00020\u00028\u0006X\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0004\u001a\u00028\u00018\u0006X\u0004¢\u0006\u0004\n\u0002\u0010\u0006\u0002\u0004\n\u0002\b\u0019¨\u0006\u0007"}, mo37405d2 = {"Lkotlinx/coroutines/channels/AbstractChannel$IdempotentTokenValue;", "E", "", "token", "value", "(Ljava/lang/Object;Ljava/lang/Object;)V", "Ljava/lang/Object;", "kotlinx-coroutines-core"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: AbstractChannel.kt */
    private static final class IdempotentTokenValue<E> {
        @NotNull
        @JvmField
        public final Object token;
        @JvmField
        public final E value;

        public IdempotentTokenValue(@NotNull Object obj, E e) {
            Intrinsics.checkParameterIsNotNull(obj, "token");
            this.token = obj;
            this.value = e;
        }
    }

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0002\u0018\u0000*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0013\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00010\u0004¢\u0006\u0002\u0010\u0005J\u0011\u0010\u000e\u001a\u00020\u000fHBø\u0001\u0000¢\u0006\u0002\u0010\u0010J\u0012\u0010\u0011\u001a\u00020\u000f2\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0002J\u0011\u0010\u0012\u001a\u00020\u000fH@ø\u0001\u0000¢\u0006\u0002\u0010\u0010J\u000e\u0010\u0013\u001a\u00028\u0001H\u0002¢\u0006\u0002\u0010\u000bR\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001c\u0010\b\u001a\u0004\u0018\u00010\tX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r\u0002\u0004\n\u0002\b\u0019¨\u0006\u0014"}, mo37405d2 = {"Lkotlinx/coroutines/channels/AbstractChannel$Itr;", "E", "Lkotlinx/coroutines/channels/ChannelIterator;", "channel", "Lkotlinx/coroutines/channels/AbstractChannel;", "(Lkotlinx/coroutines/channels/AbstractChannel;)V", "getChannel", "()Lkotlinx/coroutines/channels/AbstractChannel;", "result", "", "getResult", "()Ljava/lang/Object;", "setResult", "(Ljava/lang/Object;)V", "hasNext", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "hasNextResult", "hasNextSuspend", "next", "kotlinx-coroutines-core"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: AbstractChannel.kt */
    private static final class Itr<E> implements ChannelIterator<E> {
        @NotNull
        private final AbstractChannel<E> channel;
        @Nullable
        private Object result = AbstractChannelKt.POLL_FAILED;

        public Itr(@NotNull AbstractChannel<E> abstractChannel) {
            Intrinsics.checkParameterIsNotNull(abstractChannel, "channel");
            this.channel = abstractChannel;
        }

        @NotNull
        public final AbstractChannel<E> getChannel() {
            return this.channel;
        }

        @Nullable
        @Deprecated(level = DeprecationLevel.HIDDEN, message = "Since 1.3.0, binary compatibility with versions <= 1.2.x")
        @JvmName(name = "next")
        public /* synthetic */ Object next(@NotNull Continuation<? super E> continuation) {
            return DefaultImpls.next(this, continuation);
        }

        @Nullable
        public final Object getResult() {
            return this.result;
        }

        public final void setResult(@Nullable Object obj) {
            this.result = obj;
        }

        @Nullable
        public Object hasNext(@NotNull Continuation<? super Boolean> continuation) {
            if (this.result != AbstractChannelKt.POLL_FAILED) {
                return Boxing.boxBoolean(hasNextResult(this.result));
            }
            this.result = this.channel.pollInternal();
            if (this.result != AbstractChannelKt.POLL_FAILED) {
                return Boxing.boxBoolean(hasNextResult(this.result));
            }
            return hasNextSuspend(continuation);
        }

        private final boolean hasNextResult(Object obj) {
            if (!(obj instanceof Closed)) {
                return true;
            }
            Closed closed = (Closed) obj;
            if (closed.closeCause == null) {
                return false;
            }
            throw StackTraceRecoveryKt.recoverStackTrace(closed.getReceiveException());
        }

        public E next() {
            E e = this.result;
            if (e instanceof Closed) {
                throw StackTraceRecoveryKt.recoverStackTrace(((Closed) e).getReceiveException());
            } else if (e != AbstractChannelKt.POLL_FAILED) {
                this.result = AbstractChannelKt.POLL_FAILED;
                return e;
            } else {
                throw new IllegalStateException("'hasNext' should be called prior to 'next' invocation");
            }
        }

        /* access modifiers changed from: 0000 */
        @Nullable
        public final /* synthetic */ Object hasNextSuspend(@NotNull Continuation<? super Boolean> continuation) {
            CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 0);
            CancellableContinuation cancellableContinuation = cancellableContinuationImpl;
            ReceiveHasNext receiveHasNext = new ReceiveHasNext(this, cancellableContinuation);
            while (true) {
                Receive receive = receiveHasNext;
                if (!getChannel().enqueueReceive(receive)) {
                    Object pollInternal = getChannel().pollInternal();
                    setResult(pollInternal);
                    if (!(pollInternal instanceof Closed)) {
                        if (pollInternal != AbstractChannelKt.POLL_FAILED) {
                            Continuation continuation2 = cancellableContinuation;
                            Boolean boxBoolean = Boxing.boxBoolean(true);
                            Companion companion = Result.Companion;
                            continuation2.resumeWith(Result.m479constructorimpl(boxBoolean));
                            break;
                        }
                    } else {
                        Closed closed = (Closed) pollInternal;
                        if (closed.closeCause == null) {
                            Continuation continuation3 = cancellableContinuation;
                            Boolean boxBoolean2 = Boxing.boxBoolean(false);
                            Companion companion2 = Result.Companion;
                            continuation3.resumeWith(Result.m479constructorimpl(boxBoolean2));
                        } else {
                            Continuation continuation4 = cancellableContinuation;
                            Throwable receiveException = closed.getReceiveException();
                            Companion companion3 = Result.Companion;
                            continuation4.resumeWith(Result.m479constructorimpl(ResultKt.createFailure(receiveException)));
                        }
                    }
                } else {
                    getChannel().removeReceiveOnCancel(cancellableContinuation, receive);
                    break;
                }
            }
            Object result2 = cancellableContinuationImpl.getResult();
            if (result2 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                DebugProbesKt.probeCoroutineSuspended(continuation);
            }
            return result2;
        }
    }

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0002\u0018\u0000*\u0006\b\u0001\u0010\u0001 \u00002\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u001d\u0012\u000e\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u00018\u00010\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0014\u0010\f\u001a\u00020\t2\n\u0010\r\u001a\u0006\u0012\u0002\b\u00030\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u0010H\u0016J!\u0010\u0011\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0012\u001a\u00028\u00012\b\u0010\u0013\u001a\u0004\u0018\u00010\u000bH\u0016¢\u0006\u0002\u0010\u0014R\u0018\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u00018\u00010\u00048\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u00020\u00068\u0006X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u0015"}, mo37405d2 = {"Lkotlinx/coroutines/channels/AbstractChannel$ReceiveElement;", "E", "Lkotlinx/coroutines/channels/Receive;", "cont", "Lkotlinx/coroutines/CancellableContinuation;", "nullOnClose", "", "(Lkotlinx/coroutines/CancellableContinuation;Z)V", "completeResumeReceive", "", "token", "", "resumeReceiveClosed", "closed", "Lkotlinx/coroutines/channels/Closed;", "toString", "", "tryResumeReceive", "value", "idempotent", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: AbstractChannel.kt */
    private static final class ReceiveElement<E> extends Receive<E> {
        @NotNull
        @JvmField
        public final CancellableContinuation<E> cont;
        @JvmField
        public final boolean nullOnClose;

        public ReceiveElement(@NotNull CancellableContinuation<? super E> cancellableContinuation, boolean z) {
            Intrinsics.checkParameterIsNotNull(cancellableContinuation, "cont");
            this.cont = cancellableContinuation;
            this.nullOnClose = z;
        }

        @Nullable
        public Object tryResumeReceive(E e, @Nullable Object obj) {
            return this.cont.tryResume(e, obj);
        }

        public void completeResumeReceive(@NotNull Object obj) {
            Intrinsics.checkParameterIsNotNull(obj, "token");
            this.cont.completeResume(obj);
        }

        public void resumeReceiveClosed(@NotNull Closed<?> closed) {
            Intrinsics.checkParameterIsNotNull(closed, "closed");
            if (closed.closeCause != null || !this.nullOnClose) {
                Continuation continuation = this.cont;
                Throwable receiveException = closed.getReceiveException();
                Companion companion = Result.Companion;
                continuation.resumeWith(Result.m479constructorimpl(ResultKt.createFailure(receiveException)));
                return;
            }
            Continuation continuation2 = this.cont;
            Companion companion2 = Result.Companion;
            continuation2.resumeWith(Result.m479constructorimpl(null));
        }

        @NotNull
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("ReceiveElement[");
            sb.append(this.cont);
            sb.append(",nullOnClose=");
            sb.append(this.nullOnClose);
            sb.append(']');
            return sb.toString();
        }
    }

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0002\u0018\u0000*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B!\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00010\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\u0010\bJ\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0014\u0010\r\u001a\u00020\n2\n\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u0011H\u0016J!\u0010\u0012\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0013\u001a\u00028\u00012\b\u0010\u0014\u001a\u0004\u0018\u00010\fH\u0016¢\u0006\u0002\u0010\u0015R\u0016\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068\u0006X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00010\u00048\u0006X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u0016"}, mo37405d2 = {"Lkotlinx/coroutines/channels/AbstractChannel$ReceiveHasNext;", "E", "Lkotlinx/coroutines/channels/Receive;", "iterator", "Lkotlinx/coroutines/channels/AbstractChannel$Itr;", "cont", "Lkotlinx/coroutines/CancellableContinuation;", "", "(Lkotlinx/coroutines/channels/AbstractChannel$Itr;Lkotlinx/coroutines/CancellableContinuation;)V", "completeResumeReceive", "", "token", "", "resumeReceiveClosed", "closed", "Lkotlinx/coroutines/channels/Closed;", "toString", "", "tryResumeReceive", "value", "idempotent", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: AbstractChannel.kt */
    private static final class ReceiveHasNext<E> extends Receive<E> {
        @NotNull
        @JvmField
        public final CancellableContinuation<Boolean> cont;
        @NotNull
        @JvmField
        public final Itr<E> iterator;

        public ReceiveHasNext(@NotNull Itr<E> itr, @NotNull CancellableContinuation<? super Boolean> cancellableContinuation) {
            Intrinsics.checkParameterIsNotNull(itr, "iterator");
            Intrinsics.checkParameterIsNotNull(cancellableContinuation, "cont");
            this.iterator = itr;
            this.cont = cancellableContinuation;
        }

        @Nullable
        public Object tryResumeReceive(E e, @Nullable Object obj) {
            Object tryResume = this.cont.tryResume(Boolean.valueOf(true), obj);
            if (tryResume != null) {
                if (obj != null) {
                    return new IdempotentTokenValue(tryResume, e);
                }
                this.iterator.setResult(e);
            }
            return tryResume;
        }

        public void completeResumeReceive(@NotNull Object obj) {
            Intrinsics.checkParameterIsNotNull(obj, "token");
            if (obj instanceof IdempotentTokenValue) {
                IdempotentTokenValue idempotentTokenValue = (IdempotentTokenValue) obj;
                this.iterator.setResult(idempotentTokenValue.value);
                this.cont.completeResume(idempotentTokenValue.token);
                return;
            }
            this.cont.completeResume(obj);
        }

        public void resumeReceiveClosed(@NotNull Closed<?> closed) {
            Object obj;
            Intrinsics.checkParameterIsNotNull(closed, "closed");
            if (closed.closeCause == null) {
                obj = CancellableContinuation.DefaultImpls.tryResume$default(this.cont, Boolean.valueOf(false), null, 2, null);
            } else {
                obj = this.cont.tryResumeWithException(StackTraceRecoveryKt.recoverStackTrace(closed.getReceiveException(), this.cont));
            }
            if (obj != null) {
                this.iterator.setResult(closed);
                this.cont.completeResume(obj);
            }
        }

        @NotNull
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("ReceiveHasNext[");
            sb.append(this.cont);
            sb.append(']');
            return sb.toString();
        }
    }

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0004\u0018\u0000*\u0004\b\u0001\u0010\u0001*\u0006\b\u0002\u0010\u0002 \u00002\b\u0012\u0004\u0012\u0002H\u00020\u00032\u00020\u0004BD\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00010\u0006\u0012$\u0010\u0007\u001a \b\u0001\u0012\u0006\u0012\u0004\u0018\u00018\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\t\u0012\u0006\u0012\u0004\u0018\u00010\n0\b\u0012\u0006\u0010\u000b\u001a\u00020\fø\u0001\u0000¢\u0006\u0002\u0010\rJ\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\nH\u0016J\b\u0010\u0012\u001a\u00020\u0010H\u0016J\u0006\u0010\u0013\u001a\u00020\u0010J\u0014\u0010\u0014\u001a\u00020\u00102\n\u0010\u0015\u001a\u0006\u0012\u0002\b\u00030\u0016H\u0016J\b\u0010\u0017\u001a\u00020\u0018H\u0016J!\u0010\u0019\u001a\u0004\u0018\u00010\n2\u0006\u0010\u001a\u001a\u00028\u00022\b\u0010\u001b\u001a\u0004\u0018\u00010\nH\u0016¢\u0006\u0002\u0010\u001cR3\u0010\u0007\u001a \b\u0001\u0012\u0006\u0012\u0004\u0018\u00018\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\t\u0012\u0006\u0012\u0004\u0018\u00010\n0\b8\u0006X\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u000eR\u0010\u0010\u000b\u001a\u00020\f8\u0006X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00010\u00068\u0006X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u001d"}, mo37405d2 = {"Lkotlinx/coroutines/channels/AbstractChannel$ReceiveSelect;", "R", "E", "Lkotlinx/coroutines/channels/Receive;", "Lkotlinx/coroutines/DisposableHandle;", "select", "Lkotlinx/coroutines/selects/SelectInstance;", "block", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "", "nullOnClose", "", "(Lkotlinx/coroutines/channels/AbstractChannel;Lkotlinx/coroutines/selects/SelectInstance;Lkotlin/jvm/functions/Function2;Z)V", "Lkotlin/jvm/functions/Function2;", "completeResumeReceive", "", "token", "dispose", "removeOnSelectCompletion", "resumeReceiveClosed", "closed", "Lkotlinx/coroutines/channels/Closed;", "toString", "", "tryResumeReceive", "value", "idempotent", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: AbstractChannel.kt */
    private final class ReceiveSelect<R, E> extends Receive<E> implements DisposableHandle {
        @NotNull
        @JvmField
        public final Function2<E, Continuation<? super R>, Object> block;
        @JvmField
        public final boolean nullOnClose;
        @NotNull
        @JvmField
        public final SelectInstance<R> select;
        final /* synthetic */ AbstractChannel this$0;

        public ReceiveSelect(@NotNull AbstractChannel abstractChannel, @NotNull SelectInstance<? super R> selectInstance, Function2<? super E, ? super Continuation<? super R>, ? extends Object> function2, boolean z) {
            Intrinsics.checkParameterIsNotNull(selectInstance, "select");
            Intrinsics.checkParameterIsNotNull(function2, "block");
            this.this$0 = abstractChannel;
            this.select = selectInstance;
            this.block = function2;
            this.nullOnClose = z;
        }

        @Nullable
        public Object tryResumeReceive(E e, @Nullable Object obj) {
            if (this.select.trySelect(obj)) {
                return e != null ? e : AbstractChannelKt.NULL_VALUE;
            }
            return null;
        }

        public void completeResumeReceive(@NotNull Object obj) {
            Intrinsics.checkParameterIsNotNull(obj, "token");
            Function2<E, Continuation<? super R>, Object> function2 = this.block;
            if (obj == AbstractChannelKt.NULL_VALUE) {
                obj = null;
            }
            ContinuationKt.startCoroutine(function2, obj, this.select.getCompletion());
        }

        public void resumeReceiveClosed(@NotNull Closed<?> closed) {
            Intrinsics.checkParameterIsNotNull(closed, "closed");
            if (!this.select.trySelect(null)) {
                return;
            }
            if (closed.closeCause != null || !this.nullOnClose) {
                this.select.resumeSelectCancellableWithException(closed.getReceiveException());
            } else {
                ContinuationKt.startCoroutine(this.block, null, this.select.getCompletion());
            }
        }

        public final void removeOnSelectCompletion() {
            this.select.disposeOnSelect(this);
        }

        public void dispose() {
            if (remove()) {
                this.this$0.onReceiveDequeued();
            }
        }

        @NotNull
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("ReceiveSelect[");
            sb.append(this.select);
            sb.append(",nullOnClose=");
            sb.append(this.nullOnClose);
            sb.append(']');
            return sb.toString();
        }
    }

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0004\u0018\u00002\u00020\u0001B\u0011\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\u0002\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0002J\b\u0010\t\u001a\u00020\nH\u0016R\u0012\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u000b"}, mo37405d2 = {"Lkotlinx/coroutines/channels/AbstractChannel$RemoveReceiveOnCancel;", "Lkotlinx/coroutines/CancelHandler;", "receive", "Lkotlinx/coroutines/channels/Receive;", "(Lkotlinx/coroutines/channels/AbstractChannel;Lkotlinx/coroutines/channels/Receive;)V", "invoke", "", "cause", "", "toString", "", "kotlinx-coroutines-core"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: AbstractChannel.kt */
    private final class RemoveReceiveOnCancel extends CancelHandler {
        private final Receive<?> receive;
        final /* synthetic */ AbstractChannel this$0;

        public RemoveReceiveOnCancel(@NotNull AbstractChannel abstractChannel, Receive<?> receive2) {
            Intrinsics.checkParameterIsNotNull(receive2, "receive");
            this.this$0 = abstractChannel;
            this.receive = receive2;
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Throwable) obj);
            return Unit.INSTANCE;
        }

        public void invoke(@Nullable Throwable th) {
            if (this.receive.remove()) {
                this.this$0.onReceiveDequeued();
            }
        }

        @NotNull
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("RemoveReceiveOnCancel[");
            sb.append(this.receive);
            sb.append(']');
            return sb.toString();
        }
    }

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0004\u0018\u0000*\u0004\b\u0001\u0010\u0001*\u0004\b\u0002\u0010\u00022>\u0012\u001a\u0012\u0018\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00010\u0004R\b\u0012\u0004\u0012\u00028\u00000\u00050\u0003j\u001e\u0012\u001a\u0012\u0018\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00010\u0004R\b\u0012\u0004\u0012\u00028\u00000\u0005`\u0006BD\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00020\b\u0012$\u0010\t\u001a \b\u0001\u0012\u0006\u0012\u0004\u0018\u00018\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00020\u000b\u0012\u0006\u0012\u0004\u0018\u00010\f0\n\u0012\u0006\u0010\r\u001a\u00020\u000eø\u0001\u0000¢\u0006\u0002\u0010\u000fJ\u001a\u0010\u0010\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\fH\u0014J\u0018\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0012H\u0014J\u001a\u0010\u0016\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0012H\u0014\u0002\u0004\n\u0002\b\u0019¨\u0006\u0017"}, mo37405d2 = {"Lkotlinx/coroutines/channels/AbstractChannel$TryEnqueueReceiveDesc;", "E", "R", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$AddLastDesc;", "Lkotlinx/coroutines/channels/AbstractChannel$ReceiveSelect;", "Lkotlinx/coroutines/channels/AbstractChannel;", "Lkotlinx/coroutines/internal/AddLastDesc;", "select", "Lkotlinx/coroutines/selects/SelectInstance;", "block", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "", "nullOnClose", "", "(Lkotlinx/coroutines/channels/AbstractChannel;Lkotlinx/coroutines/selects/SelectInstance;Lkotlin/jvm/functions/Function2;Z)V", "failure", "affected", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "next", "finishOnSuccess", "", "onPrepare", "kotlinx-coroutines-core"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: AbstractChannel.kt */
    private final class TryEnqueueReceiveDesc<E, R> extends AddLastDesc<ReceiveSelect<R, ? super E>> {
        final /* synthetic */ AbstractChannel this$0;

        public TryEnqueueReceiveDesc(@NotNull AbstractChannel abstractChannel, @NotNull SelectInstance<? super R> selectInstance, Function2<? super E, ? super Continuation<? super R>, ? extends Object> function2, boolean z) {
            Intrinsics.checkParameterIsNotNull(selectInstance, "select");
            Intrinsics.checkParameterIsNotNull(function2, "block");
            this.this$0 = abstractChannel;
            super(abstractChannel.getQueue(), new ReceiveSelect(abstractChannel, selectInstance, function2, z));
        }

        /* access modifiers changed from: protected */
        @Nullable
        public Object failure(@NotNull LockFreeLinkedListNode lockFreeLinkedListNode, @NotNull Object obj) {
            Intrinsics.checkParameterIsNotNull(lockFreeLinkedListNode, "affected");
            Intrinsics.checkParameterIsNotNull(obj, "next");
            if (lockFreeLinkedListNode instanceof Send) {
                return AbstractChannelKt.ENQUEUE_FAILED;
            }
            return null;
        }

        /* access modifiers changed from: protected */
        @Nullable
        public Object onPrepare(@NotNull LockFreeLinkedListNode lockFreeLinkedListNode, @NotNull LockFreeLinkedListNode lockFreeLinkedListNode2) {
            Intrinsics.checkParameterIsNotNull(lockFreeLinkedListNode, "affected");
            Intrinsics.checkParameterIsNotNull(lockFreeLinkedListNode2, "next");
            if (!this.this$0.isBufferEmpty()) {
                return AbstractChannelKt.ENQUEUE_FAILED;
            }
            return super.onPrepare(lockFreeLinkedListNode, lockFreeLinkedListNode2);
        }

        /* access modifiers changed from: protected */
        public void finishOnSuccess(@NotNull LockFreeLinkedListNode lockFreeLinkedListNode, @NotNull LockFreeLinkedListNode lockFreeLinkedListNode2) {
            Intrinsics.checkParameterIsNotNull(lockFreeLinkedListNode, "affected");
            Intrinsics.checkParameterIsNotNull(lockFreeLinkedListNode2, "next");
            super.finishOnSuccess(lockFreeLinkedListNode, lockFreeLinkedListNode2);
            this.this$0.onReceiveEnqueued();
            ((ReceiveSelect) this.node).removeOnSelectCompletion();
        }
    }

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0004\u0018\u0000*\u0004\b\u0001\u0010\u00012\u0012\u0012\u0004\u0012\u00020\u00030\u0002j\b\u0012\u0004\u0012\u00020\u0003`\u0004B\r\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u001a\u0010\f\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000bH\u0014J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0003H\u0014R\u0016\u0010\b\u001a\u0004\u0018\u00018\u00018\u0006@\u0006X\u000e¢\u0006\u0004\n\u0002\u0010\tR\u0014\u0010\n\u001a\u0004\u0018\u00010\u000b8\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000¨\u0006\u0013"}, mo37405d2 = {"Lkotlinx/coroutines/channels/AbstractChannel$TryPollDesc;", "E", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$RemoveFirstDesc;", "Lkotlinx/coroutines/channels/Send;", "Lkotlinx/coroutines/internal/RemoveFirstDesc;", "queue", "Lkotlinx/coroutines/internal/LockFreeLinkedListHead;", "(Lkotlinx/coroutines/internal/LockFreeLinkedListHead;)V", "pollResult", "Ljava/lang/Object;", "resumeToken", "", "failure", "affected", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "next", "validatePrepared", "", "node", "kotlinx-coroutines-core"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: AbstractChannel.kt */
    protected static final class TryPollDesc<E> extends RemoveFirstDesc<Send> {
        @Nullable
        @JvmField
        public E pollResult;
        @Nullable
        @JvmField
        public Object resumeToken;

        public TryPollDesc(@NotNull LockFreeLinkedListHead lockFreeLinkedListHead) {
            Intrinsics.checkParameterIsNotNull(lockFreeLinkedListHead, "queue");
            super(lockFreeLinkedListHead);
        }

        /* access modifiers changed from: protected */
        @Nullable
        public Object failure(@NotNull LockFreeLinkedListNode lockFreeLinkedListNode, @NotNull Object obj) {
            Intrinsics.checkParameterIsNotNull(lockFreeLinkedListNode, "affected");
            Intrinsics.checkParameterIsNotNull(obj, "next");
            if (lockFreeLinkedListNode instanceof Closed) {
                return lockFreeLinkedListNode;
            }
            if (!(lockFreeLinkedListNode instanceof Send)) {
                return AbstractChannelKt.POLL_FAILED;
            }
            return null;
        }

        /* access modifiers changed from: protected */
        public boolean validatePrepared(@NotNull Send send) {
            Intrinsics.checkParameterIsNotNull(send, "node");
            Object tryResumeSend = send.tryResumeSend(this);
            if (tryResumeSend == null) {
                return false;
            }
            this.resumeToken = tryResumeSend;
            this.pollResult = send.getPollResult();
            return true;
        }
    }

    /* access modifiers changed from: protected */
    public abstract boolean isBufferAlwaysEmpty();

    /* access modifiers changed from: protected */
    public abstract boolean isBufferEmpty();

    /* access modifiers changed from: protected */
    public void onReceiveDequeued() {
    }

    /* access modifiers changed from: protected */
    public void onReceiveEnqueued() {
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Since 1.2.0, binary compatibility with versions <= 1.1.x")
    public /* synthetic */ void cancel() {
        ReceiveChannel.DefaultImpls.cancel(this);
    }

    /* access modifiers changed from: protected */
    @Nullable
    public Object pollInternal() {
        Send takeFirstSendOrPeekClosed;
        Object tryResumeSend;
        do {
            takeFirstSendOrPeekClosed = takeFirstSendOrPeekClosed();
            if (takeFirstSendOrPeekClosed == null) {
                return AbstractChannelKt.POLL_FAILED;
            }
            tryResumeSend = takeFirstSendOrPeekClosed.tryResumeSend(null);
        } while (tryResumeSend == null);
        takeFirstSendOrPeekClosed.completeResumeSend(tryResumeSend);
        return takeFirstSendOrPeekClosed.getPollResult();
    }

    /* access modifiers changed from: protected */
    @Nullable
    public Object pollSelectInternal(@NotNull SelectInstance<?> selectInstance) {
        Intrinsics.checkParameterIsNotNull(selectInstance, "select");
        TryPollDesc describeTryPoll = describeTryPoll();
        Object performAtomicTrySelect = selectInstance.performAtomicTrySelect(describeTryPoll);
        if (performAtomicTrySelect != null) {
            return performAtomicTrySelect;
        }
        Send send = (Send) describeTryPoll.getResult();
        Object obj = describeTryPoll.resumeToken;
        if (obj == null) {
            Intrinsics.throwNpe();
        }
        send.completeResumeSend(obj);
        return describeTryPoll.pollResult;
    }

    /* access modifiers changed from: protected */
    public final boolean getHasReceiveOrClosed() {
        return getQueue().getNextNode() instanceof ReceiveOrClosed;
    }

    public final boolean isClosedForReceive() {
        return getClosedForReceive() != null && isBufferEmpty();
    }

    public final boolean isEmpty() {
        return !(getQueue().getNextNode() instanceof Send) && isBufferEmpty();
    }

    @Nullable
    public final Object receive(@NotNull Continuation<? super E> continuation) {
        Object pollInternal = pollInternal();
        if (pollInternal != AbstractChannelKt.POLL_FAILED) {
            return receiveResult(pollInternal);
        }
        return receiveSuspend(continuation);
    }

    private final E receiveResult(Object obj) {
        if (!(obj instanceof Closed)) {
            return obj;
        }
        throw StackTraceRecoveryKt.recoverStackTrace(((Closed) obj).getReceiveException());
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x005c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean enqueueReceive(kotlinx.coroutines.channels.Receive<? super E> r8) {
        /*
            r7 = this;
            boolean r0 = r7.isBufferAlwaysEmpty()
            java.lang.String r1 = "null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */"
            r2 = 1
            r3 = 0
            if (r0 == 0) goto L_0x0030
            kotlinx.coroutines.internal.LockFreeLinkedListHead r0 = r7.getQueue()
        L_0x000e:
            java.lang.Object r4 = r0.getPrev()
            if (r4 == 0) goto L_0x002a
            kotlinx.coroutines.internal.LockFreeLinkedListNode r4 = (kotlinx.coroutines.internal.LockFreeLinkedListNode) r4
            boolean r5 = r4 instanceof kotlinx.coroutines.channels.Send
            if (r5 != 0) goto L_0x001c
            r5 = 1
            goto L_0x001d
        L_0x001c:
            r5 = 0
        L_0x001d:
            if (r5 != 0) goto L_0x0020
            goto L_0x005a
        L_0x0020:
            r5 = r8
            kotlinx.coroutines.internal.LockFreeLinkedListNode r5 = (kotlinx.coroutines.internal.LockFreeLinkedListNode) r5
            boolean r4 = r4.addNext(r5, r0)
            if (r4 == 0) goto L_0x000e
            goto L_0x0059
        L_0x002a:
            kotlin.TypeCastException r8 = new kotlin.TypeCastException
            r8.<init>(r1)
            throw r8
        L_0x0030:
            kotlinx.coroutines.internal.LockFreeLinkedListHead r0 = r7.getQueue()
            kotlinx.coroutines.channels.AbstractChannel$enqueueReceive$$inlined$addLastIfPrevAndIf$1 r4 = new kotlinx.coroutines.channels.AbstractChannel$enqueueReceive$$inlined$addLastIfPrevAndIf$1
            kotlinx.coroutines.internal.LockFreeLinkedListNode r8 = (kotlinx.coroutines.internal.LockFreeLinkedListNode) r8
            r4.<init>(r8, r8, r7)
            kotlinx.coroutines.internal.LockFreeLinkedListNode$CondAddOp r4 = (kotlinx.coroutines.internal.LockFreeLinkedListNode.CondAddOp) r4
        L_0x003d:
            java.lang.Object r5 = r0.getPrev()
            if (r5 == 0) goto L_0x0060
            kotlinx.coroutines.internal.LockFreeLinkedListNode r5 = (kotlinx.coroutines.internal.LockFreeLinkedListNode) r5
            boolean r6 = r5 instanceof kotlinx.coroutines.channels.Send
            if (r6 != 0) goto L_0x004b
            r6 = 1
            goto L_0x004c
        L_0x004b:
            r6 = 0
        L_0x004c:
            if (r6 != 0) goto L_0x004f
            goto L_0x005a
        L_0x004f:
            int r5 = r5.tryCondAddNext(r8, r0, r4)
            if (r5 == r2) goto L_0x0059
            r6 = 2
            if (r5 == r6) goto L_0x005a
            goto L_0x003d
        L_0x0059:
            r3 = 1
        L_0x005a:
            if (r3 == 0) goto L_0x005f
            r7.onReceiveEnqueued()
        L_0x005f:
            return r3
        L_0x0060:
            kotlin.TypeCastException r8 = new kotlin.TypeCastException
            r8.<init>(r1)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.AbstractChannel.enqueueReceive(kotlinx.coroutines.channels.Receive):boolean");
    }

    @Nullable
    public final Object receiveOrNull(@NotNull Continuation<? super E> continuation) {
        Object pollInternal = pollInternal();
        if (pollInternal != AbstractChannelKt.POLL_FAILED) {
            return receiveOrNullResult(pollInternal);
        }
        return receiveOrNullSuspend(continuation);
    }

    private final E receiveOrNullResult(Object obj) {
        if (!(obj instanceof Closed)) {
            return obj;
        }
        Closed closed = (Closed) obj;
        if (closed.closeCause == null) {
            return null;
        }
        throw StackTraceRecoveryKt.recoverStackTrace(closed.closeCause);
    }

    @Nullable
    public final E poll() {
        Object pollInternal = pollInternal();
        if (pollInternal == AbstractChannelKt.POLL_FAILED) {
            return null;
        }
        return receiveOrNullResult(pollInternal);
    }

    public final void cancel(@Nullable CancellationException cancellationException) {
        if (cancellationException == null) {
            StringBuilder sb = new StringBuilder();
            sb.append(DebugKt.getClassSimpleName(this));
            sb.append(" was cancelled");
            cancellationException = new CancellationException(sb.toString());
        }
        cancel(cancellationException);
    }

    /* renamed from: cancelInternal$kotlinx_coroutines_core */
    public boolean cancel(@Nullable Throwable th) {
        boolean close = close(th);
        cleanupSendQueueOnCancel();
        return close;
    }

    /* access modifiers changed from: protected */
    public void cleanupSendQueueOnCancel() {
        Closed closedForSend = getClosedForSend();
        String str = "Cannot happen";
        if (closedForSend != null) {
            while (true) {
                Send takeFirstSendOrPeekClosed = takeFirstSendOrPeekClosed();
                if (takeFirstSendOrPeekClosed == null) {
                    throw new IllegalStateException(str.toString());
                } else if (takeFirstSendOrPeekClosed instanceof Closed) {
                    if (!(takeFirstSendOrPeekClosed == closedForSend)) {
                        throw new IllegalStateException("Check failed.".toString());
                    }
                    return;
                } else {
                    takeFirstSendOrPeekClosed.resumeSendClosed(closedForSend);
                }
            }
        } else {
            throw new IllegalStateException(str.toString());
        }
    }

    @NotNull
    public final ChannelIterator<E> iterator() {
        return new Itr<>(this);
    }

    /* access modifiers changed from: protected */
    @NotNull
    public final TryPollDesc<E> describeTryPoll() {
        return new TryPollDesc<>(getQueue());
    }

    @NotNull
    public final SelectClause1<E> getOnReceive() {
        return new AbstractChannel$onReceive$1<>(this);
    }

    /* access modifiers changed from: private */
    public final <R> void registerSelectReceive(SelectInstance<? super R> selectInstance, Function2<? super E, ? super Continuation<? super R>, ? extends Object> function2) {
        while (!selectInstance.isSelected()) {
            if (!isEmpty()) {
                Object pollSelectInternal = pollSelectInternal(selectInstance);
                if (pollSelectInternal != SelectKt.getALREADY_SELECTED()) {
                    if (pollSelectInternal != AbstractChannelKt.POLL_FAILED) {
                        if (!(pollSelectInternal instanceof Closed)) {
                            UndispatchedKt.startCoroutineUnintercepted(function2, pollSelectInternal, selectInstance.getCompletion());
                            return;
                        }
                        throw StackTraceRecoveryKt.recoverStackTrace(((Closed) pollSelectInternal).getReceiveException());
                    }
                } else {
                    return;
                }
            } else if (function2 != null) {
                Object performAtomicIfNotSelected = selectInstance.performAtomicIfNotSelected(new TryEnqueueReceiveDesc(this, selectInstance, function2, false));
                if (performAtomicIfNotSelected != null && performAtomicIfNotSelected != SelectKt.getALREADY_SELECTED()) {
                    if (performAtomicIfNotSelected != AbstractChannelKt.ENQUEUE_FAILED) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("performAtomicIfNotSelected(TryEnqueueReceiveDesc) returned ");
                        sb.append(performAtomicIfNotSelected);
                        throw new IllegalStateException(sb.toString().toString());
                    }
                } else {
                    return;
                }
            } else {
                throw new TypeCastException("null cannot be cast to non-null type suspend (E?) -> R");
            }
        }
    }

    @NotNull
    public final SelectClause1<E> getOnReceiveOrNull() {
        return new AbstractChannel$onReceiveOrNull$1<>(this);
    }

    /* access modifiers changed from: private */
    public final <R> void registerSelectReceiveOrNull(SelectInstance<? super R> selectInstance, Function2<? super E, ? super Continuation<? super R>, ? extends Object> function2) {
        while (!selectInstance.isSelected()) {
            if (isEmpty()) {
                Object performAtomicIfNotSelected = selectInstance.performAtomicIfNotSelected(new TryEnqueueReceiveDesc(this, selectInstance, function2, true));
                if (performAtomicIfNotSelected != null && performAtomicIfNotSelected != SelectKt.getALREADY_SELECTED()) {
                    if (performAtomicIfNotSelected != AbstractChannelKt.ENQUEUE_FAILED) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("performAtomicIfNotSelected(TryEnqueueReceiveDesc) returned ");
                        sb.append(performAtomicIfNotSelected);
                        throw new IllegalStateException(sb.toString().toString());
                    }
                } else {
                    return;
                }
            } else {
                Object pollSelectInternal = pollSelectInternal(selectInstance);
                if (pollSelectInternal != SelectKt.getALREADY_SELECTED()) {
                    if (pollSelectInternal != AbstractChannelKt.POLL_FAILED) {
                        if (pollSelectInternal instanceof Closed) {
                            Closed closed = (Closed) pollSelectInternal;
                            if (closed.closeCause == null) {
                                if (selectInstance.trySelect(null)) {
                                    UndispatchedKt.startCoroutineUnintercepted(function2, null, selectInstance.getCompletion());
                                }
                                return;
                            }
                            throw StackTraceRecoveryKt.recoverStackTrace(closed.closeCause);
                        }
                        UndispatchedKt.startCoroutineUnintercepted(function2, pollSelectInternal, selectInstance.getCompletion());
                        return;
                    }
                } else {
                    return;
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    @Nullable
    public ReceiveOrClosed<E> takeFirstReceiveOrPeekClosed() {
        ReceiveOrClosed<E> takeFirstReceiveOrPeekClosed = super.takeFirstReceiveOrPeekClosed();
        if (takeFirstReceiveOrPeekClosed != null && !(takeFirstReceiveOrPeekClosed instanceof Closed)) {
            onReceiveDequeued();
        }
        return takeFirstReceiveOrPeekClosed;
    }

    /* access modifiers changed from: private */
    public final void removeReceiveOnCancel(CancellableContinuation<?> cancellableContinuation, Receive<?> receive) {
        cancellableContinuation.invokeOnCancellation(new RemoveReceiveOnCancel(this, receive));
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public final /* synthetic */ Object receiveSuspend(@NotNull Continuation<? super E> continuation) {
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 0);
        CancellableContinuation cancellableContinuation = cancellableContinuationImpl;
        ReceiveElement receiveElement = new ReceiveElement(cancellableContinuation, false);
        while (true) {
            Receive receive = receiveElement;
            if (!enqueueReceive(receive)) {
                Object pollInternal = pollInternal();
                if (!(pollInternal instanceof Closed)) {
                    if (pollInternal != AbstractChannelKt.POLL_FAILED) {
                        Continuation continuation2 = cancellableContinuation;
                        Companion companion = Result.Companion;
                        continuation2.resumeWith(Result.m479constructorimpl(pollInternal));
                        break;
                    }
                } else {
                    Continuation continuation3 = cancellableContinuation;
                    Throwable receiveException = ((Closed) pollInternal).getReceiveException();
                    Companion companion2 = Result.Companion;
                    continuation3.resumeWith(Result.m479constructorimpl(ResultKt.createFailure(receiveException)));
                    break;
                }
            } else {
                removeReceiveOnCancel(cancellableContinuation, receive);
                break;
            }
        }
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public final /* synthetic */ Object receiveOrNullSuspend(@NotNull Continuation<? super E> continuation) {
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 0);
        CancellableContinuation cancellableContinuation = cancellableContinuationImpl;
        ReceiveElement receiveElement = new ReceiveElement(cancellableContinuation, true);
        while (true) {
            Receive receive = receiveElement;
            if (!enqueueReceive(receive)) {
                Object pollInternal = pollInternal();
                if (!(pollInternal instanceof Closed)) {
                    if (pollInternal != AbstractChannelKt.POLL_FAILED) {
                        Continuation continuation2 = cancellableContinuation;
                        Companion companion = Result.Companion;
                        continuation2.resumeWith(Result.m479constructorimpl(pollInternal));
                        break;
                    }
                } else {
                    Closed closed = (Closed) pollInternal;
                    if (closed.closeCause == null) {
                        Continuation continuation3 = cancellableContinuation;
                        Companion companion2 = Result.Companion;
                        continuation3.resumeWith(Result.m479constructorimpl(null));
                    } else {
                        Continuation continuation4 = cancellableContinuation;
                        Throwable th = closed.closeCause;
                        Companion companion3 = Result.Companion;
                        continuation4.resumeWith(Result.m479constructorimpl(ResultKt.createFailure(th)));
                    }
                }
            } else {
                removeReceiveOnCancel(cancellableContinuation, receive);
                break;
            }
        }
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }
}
