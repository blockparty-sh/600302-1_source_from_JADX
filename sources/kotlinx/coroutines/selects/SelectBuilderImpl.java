package kotlinx.coroutines.selects;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.Result;
import kotlin.Result.Companion;
import kotlin.ResultKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CompletedExceptionally;
import kotlinx.coroutines.CompletedExceptionallyKt;
import kotlinx.coroutines.CoroutineExceptionHandlerKt;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.DispatchedKt;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobCancellingNode;
import kotlinx.coroutines.internal.AtomicDesc;
import kotlinx.coroutines.internal.AtomicOp;
import kotlinx.coroutines.internal.LockFreeLinkedListHead;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.internal.LockFreeLinkedListNode.CondAddOp;
import kotlinx.coroutines.internal.OpDescriptor;
import kotlinx.coroutines.internal.StackTraceRecoveryKt;
import kotlinx.coroutines.intrinsics.UndispatchedKt;
import kotlinx.coroutines.selects.SelectBuilder.DefaultImpls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@PublishedApi
@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0001\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00002\u00020\u00022\b\u0012\u0004\u0012\u00028\u00000\u00032\b\u0012\u0004\u0012\u00028\u00000\u00042\b\u0012\u0004\u0012\u00028\u00000\u00052\u00060\u0006j\u0002`\u0007:\u0003STUB\u0015\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\u000e\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0010\u001a\u00020\rH\u0002¢\u0006\u0004\b\u0010\u0010\u0011J.\u0010\u0016\u001a\u00020\r2\u000e\u0010\u0014\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00130\u00122\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\r0\u0012H\b¢\u0006\u0004\b\u0016\u0010\u0017J\u0011\u0010\u0018\u001a\u0004\u0018\u00010\u0013H\u0001¢\u0006\u0004\b\u0018\u0010\u0019J\u0017\u0010\u001c\u001a\n\u0018\u00010\u001aj\u0004\u0018\u0001`\u001bH\u0016¢\u0006\u0004\b\u001c\u0010\u001dJ\u0017\u0010 \u001a\u00020\r2\u0006\u0010\u001f\u001a\u00020\u001eH\u0001¢\u0006\u0004\b \u0010!J\u000f\u0010\"\u001a\u00020\rH\u0002¢\u0006\u0004\b\"\u0010\u0011J8\u0010&\u001a\u00020\r2\u0006\u0010$\u001a\u00020#2\u001c\u0010\u0015\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00130%H\u0016ø\u0001\u0000¢\u0006\u0004\b&\u0010'J\u0019\u0010*\u001a\u0004\u0018\u00010\u00132\u0006\u0010)\u001a\u00020(H\u0016¢\u0006\u0004\b*\u0010+J\u0019\u0010,\u001a\u0004\u0018\u00010\u00132\u0006\u0010)\u001a\u00020(H\u0016¢\u0006\u0004\b,\u0010+J\u0017\u0010.\u001a\u00020\r2\u0006\u0010-\u001a\u00020\u001eH\u0016¢\u0006\u0004\b.\u0010!J \u00101\u001a\u00020\r2\f\u00100\u001a\b\u0012\u0004\u0012\u00028\u00000/H\u0016ø\u0001\u0000¢\u0006\u0004\b1\u00102J\u0019\u00105\u001a\u0002042\b\u00103\u001a\u0004\u0018\u00010\u0013H\u0016¢\u0006\u0004\b5\u00106J5\u00108\u001a\u00020\r*\u0002072\u001c\u0010\u0015\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00130%H\u0002ø\u0001\u0000¢\u0006\u0004\b8\u00109JG\u00108\u001a\u00020\r\"\u0004\b\u0001\u0010:*\b\u0012\u0004\u0012\u00028\u00010;2\"\u0010\u0015\u001a\u001e\b\u0001\u0012\u0004\u0012\u00028\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00130<H\u0002ø\u0001\u0000¢\u0006\u0004\b8\u0010=J[\u00108\u001a\u00020\r\"\u0004\b\u0001\u0010>\"\u0004\b\u0002\u0010:*\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020?2\u0006\u0010@\u001a\u00028\u00012\"\u0010\u0015\u001a\u001e\b\u0001\u0012\u0004\u0012\u00028\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00130<H\u0002ø\u0001\u0000¢\u0006\u0004\b8\u0010AR\u001e\u0010D\u001a\n\u0018\u00010\u0006j\u0004\u0018\u0001`\u00078V@\u0016X\u0004¢\u0006\u0006\u001a\u0004\bB\u0010CR\u001c\u0010G\u001a\b\u0012\u0004\u0012\u00028\u00000\u00058V@\u0016X\u0004¢\u0006\u0006\u001a\u0004\bE\u0010FR\u0016\u0010K\u001a\u00020H8V@\u0016X\u0004¢\u0006\u0006\u001a\u0004\bI\u0010JR\u0016\u0010L\u001a\u0002048V@\u0016X\u0004¢\u0006\u0006\u001a\u0004\bL\u0010MR\u0018\u0010N\u001a\u0004\u0018\u00010\u000b8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bN\u0010OR\u0018\u0010Q\u001a\u0004\u0018\u00010\u00138B@\u0002X\u0004¢\u0006\u0006\u001a\u0004\bP\u0010\u0019R\u001c\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u00058\u0002@\u0002X\u0004¢\u0006\u0006\n\u0004\b\b\u0010R\u0002\u0004\n\u0002\b\u0019¨\u0006V"}, mo37405d2 = {"Lkotlinx/coroutines/selects/SelectBuilderImpl;", "R", "Lkotlinx/coroutines/internal/LockFreeLinkedListHead;", "Lkotlinx/coroutines/selects/SelectBuilder;", "Lkotlinx/coroutines/selects/SelectInstance;", "Lkotlin/coroutines/Continuation;", "Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "Lkotlinx/coroutines/internal/CoroutineStackFrame;", "uCont", "<init>", "(Lkotlin/coroutines/Continuation;)V", "Lkotlinx/coroutines/DisposableHandle;", "handle", "", "disposeOnSelect", "(Lkotlinx/coroutines/DisposableHandle;)V", "doAfterSelect", "()V", "Lkotlin/Function0;", "", "value", "block", "doResume", "(Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;)V", "getResult", "()Ljava/lang/Object;", "Ljava/lang/StackTraceElement;", "Lkotlinx/coroutines/internal/StackTraceElement;", "getStackTraceElement", "()Ljava/lang/StackTraceElement;", "", "e", "handleBuilderException", "(Ljava/lang/Throwable;)V", "initCancellability", "", "timeMillis", "Lkotlin/Function1;", "onTimeout", "(JLkotlin/jvm/functions/Function1;)V", "Lkotlinx/coroutines/internal/AtomicDesc;", "desc", "performAtomicIfNotSelected", "(Lkotlinx/coroutines/internal/AtomicDesc;)Ljava/lang/Object;", "performAtomicTrySelect", "exception", "resumeSelectCancellableWithException", "Lkotlin/Result;", "result", "resumeWith", "(Ljava/lang/Object;)V", "idempotent", "", "trySelect", "(Ljava/lang/Object;)Z", "Lkotlinx/coroutines/selects/SelectClause0;", "invoke", "(Lkotlinx/coroutines/selects/SelectClause0;Lkotlin/jvm/functions/Function1;)V", "Q", "Lkotlinx/coroutines/selects/SelectClause1;", "Lkotlin/Function2;", "(Lkotlinx/coroutines/selects/SelectClause1;Lkotlin/jvm/functions/Function2;)V", "P", "Lkotlinx/coroutines/selects/SelectClause2;", "param", "(Lkotlinx/coroutines/selects/SelectClause2;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)V", "getCallerFrame", "()Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "callerFrame", "getCompletion", "()Lkotlin/coroutines/Continuation;", "completion", "Lkotlin/coroutines/CoroutineContext;", "getContext", "()Lkotlin/coroutines/CoroutineContext;", "context", "isSelected", "()Z", "parentHandle", "Lkotlinx/coroutines/DisposableHandle;", "getState", "state", "Lkotlin/coroutines/Continuation;", "AtomicSelectOp", "DisposeNode", "SelectOnCancelling", "kotlinx-coroutines-core"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: Select.kt */
public final class SelectBuilderImpl<R> extends LockFreeLinkedListHead implements SelectBuilder<R>, SelectInstance<R>, Continuation<R>, CoroutineStackFrame {
    static final AtomicReferenceFieldUpdater _result$FU = AtomicReferenceFieldUpdater.newUpdater(SelectBuilderImpl.class, Object.class, "_result");
    static final AtomicReferenceFieldUpdater _state$FU = AtomicReferenceFieldUpdater.newUpdater(SelectBuilderImpl.class, Object.class, "_state");
    volatile Object _result = SelectKt.UNDECIDED;
    volatile Object _state = this;
    private volatile DisposableHandle parentHandle;
    private final Continuation<R> uCont;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\b\u0004\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u001c\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u00022\b\u0010\u000b\u001a\u0004\u0018\u00010\u0002H\u0016J\u0012\u0010\f\u001a\u00020\t2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0002H\u0002J\u0014\u0010\r\u001a\u0004\u0018\u00010\u00022\b\u0010\n\u001a\u0004\u0018\u00010\u0002H\u0016J\b\u0010\u000e\u001a\u0004\u0018\u00010\u0002R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u00020\u00068\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, mo37405d2 = {"Lkotlinx/coroutines/selects/SelectBuilderImpl$AtomicSelectOp;", "Lkotlinx/coroutines/internal/AtomicOp;", "", "desc", "Lkotlinx/coroutines/internal/AtomicDesc;", "select", "", "(Lkotlinx/coroutines/selects/SelectBuilderImpl;Lkotlinx/coroutines/internal/AtomicDesc;Z)V", "complete", "", "affected", "failure", "completeSelect", "prepare", "prepareIfNotSelected", "kotlinx-coroutines-core"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: Select.kt */
    private final class AtomicSelectOp extends AtomicOp<Object> {
        @NotNull
        @JvmField
        public final AtomicDesc desc;
        @JvmField
        public final boolean select;
        final /* synthetic */ SelectBuilderImpl this$0;

        public AtomicSelectOp(@NotNull SelectBuilderImpl selectBuilderImpl, AtomicDesc atomicDesc, boolean z) {
            Intrinsics.checkParameterIsNotNull(atomicDesc, "desc");
            this.this$0 = selectBuilderImpl;
            this.desc = atomicDesc;
            this.select = z;
        }

        @Nullable
        public Object prepare(@Nullable Object obj) {
            if (obj == null) {
                Object prepareIfNotSelected = prepareIfNotSelected();
                if (prepareIfNotSelected != null) {
                    return prepareIfNotSelected;
                }
            }
            return this.desc.prepare(this);
        }

        public void complete(@Nullable Object obj, @Nullable Object obj2) {
            completeSelect(obj2);
            this.desc.complete(this, obj2);
        }

        @Nullable
        public final Object prepareIfNotSelected() {
            SelectBuilderImpl selectBuilderImpl = this.this$0;
            while (true) {
                Object obj = selectBuilderImpl._state;
                if (obj == this) {
                    return null;
                }
                if (obj instanceof OpDescriptor) {
                    ((OpDescriptor) obj).perform(this.this$0);
                } else {
                    SelectBuilderImpl selectBuilderImpl2 = this.this$0;
                    if (obj != selectBuilderImpl2) {
                        return SelectKt.getALREADY_SELECTED();
                    }
                    if (SelectBuilderImpl._state$FU.compareAndSet(selectBuilderImpl2, this.this$0, this)) {
                        return null;
                    }
                }
            }
        }

        private final void completeSelect(Object obj) {
            SelectBuilderImpl selectBuilderImpl;
            boolean z = this.select && obj == null;
            if (z) {
                selectBuilderImpl = null;
            } else {
                selectBuilderImpl = this.this$0;
            }
            if (SelectBuilderImpl._state$FU.compareAndSet(this.this$0, this, selectBuilderImpl) && z) {
                this.this$0.doAfterSelect();
            }
        }
    }

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0010\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0005"}, mo37405d2 = {"Lkotlinx/coroutines/selects/SelectBuilderImpl$DisposeNode;", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "handle", "Lkotlinx/coroutines/DisposableHandle;", "(Lkotlinx/coroutines/DisposableHandle;)V", "kotlinx-coroutines-core"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: Select.kt */
    private static final class DisposeNode extends LockFreeLinkedListNode {
        @NotNull
        @JvmField
        public final DisposableHandle handle;

        public DisposeNode(@NotNull DisposableHandle disposableHandle) {
            Intrinsics.checkParameterIsNotNull(disposableHandle, "handle");
            this.handle = disposableHandle;
        }
    }

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0002J\b\u0010\t\u001a\u00020\nH\u0016¨\u0006\u000b"}, mo37405d2 = {"Lkotlinx/coroutines/selects/SelectBuilderImpl$SelectOnCancelling;", "Lkotlinx/coroutines/JobCancellingNode;", "Lkotlinx/coroutines/Job;", "job", "(Lkotlinx/coroutines/selects/SelectBuilderImpl;Lkotlinx/coroutines/Job;)V", "invoke", "", "cause", "", "toString", "", "kotlinx-coroutines-core"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: Select.kt */
    private final class SelectOnCancelling extends JobCancellingNode<Job> {
        final /* synthetic */ SelectBuilderImpl this$0;

        public SelectOnCancelling(@NotNull SelectBuilderImpl selectBuilderImpl, Job job) {
            Intrinsics.checkParameterIsNotNull(job, "job");
            this.this$0 = selectBuilderImpl;
            super(job);
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Throwable) obj);
            return Unit.INSTANCE;
        }

        public void invoke(@Nullable Throwable th) {
            if (this.this$0.trySelect(null)) {
                this.this$0.resumeSelectCancellableWithException(this.job.getCancellationException());
            }
        }

        @NotNull
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("SelectOnCancelling[");
            sb.append(this.this$0);
            sb.append(']');
            return sb.toString();
        }
    }

    @Nullable
    public StackTraceElement getStackTraceElement() {
        return null;
    }

    public <P, Q> void invoke(@NotNull SelectClause2<? super P, ? extends Q> selectClause2, @NotNull Function2<? super Q, ? super Continuation<? super R>, ? extends Object> function2) {
        Intrinsics.checkParameterIsNotNull(selectClause2, "$this$invoke");
        Intrinsics.checkParameterIsNotNull(function2, "block");
        DefaultImpls.invoke(this, selectClause2, function2);
    }

    public SelectBuilderImpl(@NotNull Continuation<? super R> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "uCont");
        this.uCont = continuation;
    }

    @Nullable
    public CoroutineStackFrame getCallerFrame() {
        Continuation<R> continuation = this.uCont;
        if (!(continuation instanceof CoroutineStackFrame)) {
            continuation = null;
        }
        return (CoroutineStackFrame) continuation;
    }

    @NotNull
    public CoroutineContext getContext() {
        return this.uCont.getContext();
    }

    @NotNull
    public Continuation<R> getCompletion() {
        return this;
    }

    private final void doResume(Function0<? extends Object> function0, Function0<Unit> function02) {
        if (isSelected()) {
            while (true) {
                Object obj = this._result;
                if (obj == SelectKt.UNDECIDED) {
                    if (_result$FU.compareAndSet(this, SelectKt.UNDECIDED, function0.invoke())) {
                        return;
                    }
                } else if (obj != IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                    throw new IllegalStateException("Already resumed");
                } else if (_result$FU.compareAndSet(this, IntrinsicsKt.getCOROUTINE_SUSPENDED(), SelectKt.RESUMED)) {
                    function02.invoke();
                    return;
                }
            }
        } else {
            throw new IllegalStateException("Must be selected first".toString());
        }
    }

    @Nullable
    @PublishedApi
    public final Object getResult() {
        if (!isSelected()) {
            initCancellability();
        }
        Object obj = this._result;
        if (obj == SelectKt.UNDECIDED) {
            if (_result$FU.compareAndSet(this, SelectKt.UNDECIDED, IntrinsicsKt.getCOROUTINE_SUSPENDED())) {
                return IntrinsicsKt.getCOROUTINE_SUSPENDED();
            }
            obj = this._result;
        }
        if (obj == SelectKt.RESUMED) {
            throw new IllegalStateException("Already resumed");
        } else if (!(obj instanceof CompletedExceptionally)) {
            return obj;
        } else {
            throw ((CompletedExceptionally) obj).cause;
        }
    }

    private final void initCancellability() {
        Job job = (Job) getContext().get(Job.Key);
        if (job != null) {
            DisposableHandle invokeOnCompletion$default = Job.DefaultImpls.invokeOnCompletion$default(job, true, false, new SelectOnCancelling(this, job), 2, null);
            this.parentHandle = invokeOnCompletion$default;
            if (isSelected()) {
                invokeOnCompletion$default.dispose();
            }
        }
    }

    @PublishedApi
    public final void handleBuilderException(@NotNull Throwable th) {
        Intrinsics.checkParameterIsNotNull(th, "e");
        if (trySelect(null)) {
            Companion companion = Result.Companion;
            resumeWith(Result.m479constructorimpl(ResultKt.createFailure(th)));
            return;
        }
        CoroutineExceptionHandlerKt.handleCoroutineException(getContext(), th);
    }

    public boolean isSelected() {
        return getState() != this;
    }

    public void disposeOnSelect(@NotNull DisposableHandle disposableHandle) {
        boolean z;
        Intrinsics.checkParameterIsNotNull(disposableHandle, "handle");
        DisposeNode disposeNode = new DisposeNode(disposableHandle);
        while (getState() == this) {
            LockFreeLinkedListNode lockFreeLinkedListNode = disposeNode;
            CondAddOp selectBuilderImpl$disposeOnSelect$$inlined$addLastIf$1 = new SelectBuilderImpl$disposeOnSelect$$inlined$addLastIf$1(lockFreeLinkedListNode, lockFreeLinkedListNode, this);
            while (true) {
                Object prev = getPrev();
                if (prev != null) {
                    int tryCondAddNext = ((LockFreeLinkedListNode) prev).tryCondAddNext(lockFreeLinkedListNode, this, selectBuilderImpl$disposeOnSelect$$inlined$addLastIf$1);
                    z = true;
                    if (tryCondAddNext != 1) {
                        if (tryCondAddNext == 2) {
                            z = false;
                            continue;
                            break;
                        }
                    } else {
                        break;
                    }
                } else {
                    throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */");
                }
            }
            if (z) {
                return;
            }
        }
        disposableHandle.dispose();
    }

    /* access modifiers changed from: private */
    public final void doAfterSelect() {
        DisposableHandle disposableHandle = this.parentHandle;
        if (disposableHandle != null) {
            disposableHandle.dispose();
        }
        Object next = getNext();
        if (next != null) {
            for (LockFreeLinkedListNode lockFreeLinkedListNode = (LockFreeLinkedListNode) next; !Intrinsics.areEqual((Object) lockFreeLinkedListNode, (Object) this); lockFreeLinkedListNode = lockFreeLinkedListNode.getNextNode()) {
                if (lockFreeLinkedListNode instanceof DisposeNode) {
                    ((DisposeNode) lockFreeLinkedListNode).handle.dispose();
                }
            }
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */");
    }

    public boolean trySelect(@Nullable Object obj) {
        if (!(obj instanceof OpDescriptor)) {
            do {
                Object state = getState();
                if (state != this) {
                    return obj != null && state == obj;
                }
            } while (!_state$FU.compareAndSet(this, this, obj));
            doAfterSelect();
            return true;
        }
        throw new IllegalStateException("cannot use OpDescriptor as idempotent marker".toString());
    }

    @Nullable
    public Object performAtomicTrySelect(@NotNull AtomicDesc atomicDesc) {
        Intrinsics.checkParameterIsNotNull(atomicDesc, "desc");
        return new AtomicSelectOp(this, atomicDesc, true).perform(null);
    }

    @Nullable
    public Object performAtomicIfNotSelected(@NotNull AtomicDesc atomicDesc) {
        Intrinsics.checkParameterIsNotNull(atomicDesc, "desc");
        return new AtomicSelectOp(this, atomicDesc, false).perform(null);
    }

    public void invoke(@NotNull SelectClause0 selectClause0, @NotNull Function1<? super Continuation<? super R>, ? extends Object> function1) {
        Intrinsics.checkParameterIsNotNull(selectClause0, "$this$invoke");
        Intrinsics.checkParameterIsNotNull(function1, "block");
        selectClause0.registerSelectClause0(this, function1);
    }

    public <Q> void invoke(@NotNull SelectClause1<? extends Q> selectClause1, @NotNull Function2<? super Q, ? super Continuation<? super R>, ? extends Object> function2) {
        Intrinsics.checkParameterIsNotNull(selectClause1, "$this$invoke");
        Intrinsics.checkParameterIsNotNull(function2, "block");
        selectClause1.registerSelectClause1(this, function2);
    }

    public <P, Q> void invoke(@NotNull SelectClause2<? super P, ? extends Q> selectClause2, P p, @NotNull Function2<? super Q, ? super Continuation<? super R>, ? extends Object> function2) {
        Intrinsics.checkParameterIsNotNull(selectClause2, "$this$invoke");
        Intrinsics.checkParameterIsNotNull(function2, "block");
        selectClause2.registerSelectClause2(this, p, function2);
    }

    public void onTimeout(long j, @NotNull Function1<? super Continuation<? super R>, ? extends Object> function1) {
        Intrinsics.checkParameterIsNotNull(function1, "block");
        if (j <= 0) {
            if (trySelect(null)) {
                UndispatchedKt.startCoroutineUnintercepted(function1, getCompletion());
            }
            return;
        }
        disposeOnSelect(DelayKt.getDelay(getContext()).invokeOnTimeout(j, new SelectBuilderImpl$onTimeout$$inlined$Runnable$1(this, function1)));
    }

    public void resumeWith(@NotNull Object obj) {
        if (isSelected()) {
            while (true) {
                Object obj2 = this._result;
                if (obj2 == SelectKt.UNDECIDED) {
                    if (_result$FU.compareAndSet(this, SelectKt.UNDECIDED, CompletedExceptionallyKt.toState(obj))) {
                        return;
                    }
                } else if (obj2 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                    throw new IllegalStateException("Already resumed");
                } else if (_result$FU.compareAndSet(this, IntrinsicsKt.getCOROUTINE_SUSPENDED(), SelectKt.RESUMED)) {
                    if (Result.m485isFailureimpl(obj)) {
                        Continuation<R> continuation = this.uCont;
                        Throwable r4 = Result.m482exceptionOrNullimpl(obj);
                        if (r4 == null) {
                            Intrinsics.throwNpe();
                        }
                        Companion companion = Result.Companion;
                        continuation.resumeWith(Result.m479constructorimpl(ResultKt.createFailure(StackTraceRecoveryKt.recoverStackTrace(r4, continuation))));
                        return;
                    }
                    this.uCont.resumeWith(obj);
                    return;
                }
            }
        } else {
            throw new IllegalStateException("Must be selected first".toString());
        }
    }

    public void resumeSelectCancellableWithException(@NotNull Throwable th) {
        Intrinsics.checkParameterIsNotNull(th, "exception");
        if (isSelected()) {
            while (true) {
                Object obj = this._result;
                if (obj == SelectKt.UNDECIDED) {
                    if (_result$FU.compareAndSet(this, SelectKt.UNDECIDED, new CompletedExceptionally(th, false, 2, null))) {
                        return;
                    }
                } else if (obj != IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                    throw new IllegalStateException("Already resumed");
                } else if (_result$FU.compareAndSet(this, IntrinsicsKt.getCOROUTINE_SUSPENDED(), SelectKt.RESUMED)) {
                    DispatchedKt.resumeCancellableWithException(IntrinsicsKt.intercepted(this.uCont), th);
                    return;
                }
            }
        } else {
            throw new IllegalStateException("Must be selected first".toString());
        }
    }

    /* access modifiers changed from: private */
    public final Object getState() {
        while (true) {
            Object obj = this._state;
            if (!(obj instanceof OpDescriptor)) {
                return obj;
            }
            ((OpDescriptor) obj).perform(this);
        }
    }
}
