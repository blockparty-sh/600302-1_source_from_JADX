package kotlinx.coroutines.internal;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.internal.LockFreeLinkedListNode.AbstractAtomicDesc;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000+\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J%\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\u0010\u0004\u001a\u00060\u0002j\u0002`\u00032\u0006\u0010\u0006\u001a\u00020\u0005H\u0014¢\u0006\u0004\b\u0007\u0010\bJ'\u0010\n\u001a\u00020\t2\n\u0010\u0004\u001a\u00060\u0002j\u0002`\u00032\n\u0010\u0006\u001a\u00060\u0002j\u0002`\u0003H\u0014¢\u0006\u0004\b\n\u0010\u000bJ)\u0010\f\u001a\u0004\u0018\u00010\u00052\n\u0010\u0004\u001a\u00060\u0002j\u0002`\u00032\n\u0010\u0006\u001a\u00060\u0002j\u0002`\u0003H\u0014¢\u0006\u0004\b\f\u0010\rJ'\u0010\u000f\u001a\u00020\u000e2\n\u0010\u0004\u001a\u00060\u0002j\u0002`\u00032\n\u0010\u0006\u001a\u00060\u0002j\u0002`\u0003H\u0014¢\u0006\u0004\b\u000f\u0010\u0010R\u001e\u0010\u0013\u001a\n\u0018\u00010\u0002j\u0004\u0018\u0001`\u00038T@\u0014X\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u001e\u0010\u0015\u001a\n\u0018\u00010\u0002j\u0004\u0018\u0001`\u00038T@\u0014X\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0012¨\u0006\u0016"}, mo37405d2 = {"kotlinx/coroutines/internal/LockFreeLinkedListNode$describeRemove$1", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$AbstractAtomicDesc;", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "Lkotlinx/coroutines/internal/Node;", "affected", "", "next", "failure", "(Lkotlinx/coroutines/internal/LockFreeLinkedListNode;Ljava/lang/Object;)Ljava/lang/Object;", "", "finishOnSuccess", "(Lkotlinx/coroutines/internal/LockFreeLinkedListNode;Lkotlinx/coroutines/internal/LockFreeLinkedListNode;)V", "onPrepare", "(Lkotlinx/coroutines/internal/LockFreeLinkedListNode;Lkotlinx/coroutines/internal/LockFreeLinkedListNode;)Ljava/lang/Object;", "Lkotlinx/coroutines/internal/Removed;", "updatedNext", "(Lkotlinx/coroutines/internal/LockFreeLinkedListNode;Lkotlinx/coroutines/internal/LockFreeLinkedListNode;)Lkotlinx/coroutines/internal/Removed;", "getAffectedNode", "()Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "affectedNode", "getOriginalNext", "originalNext", "kotlinx-coroutines-core"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: LockFreeLinkedList.kt */
public final class LockFreeLinkedListNode$describeRemove$1 extends AbstractAtomicDesc {
    private static final AtomicReferenceFieldUpdater _originalNext$FU = AtomicReferenceFieldUpdater.newUpdater(LockFreeLinkedListNode$describeRemove$1.class, Object.class, "_originalNext");
    private volatile Object _originalNext = null;
    final /* synthetic */ LockFreeLinkedListNode this$0;

    LockFreeLinkedListNode$describeRemove$1(LockFreeLinkedListNode lockFreeLinkedListNode) {
        this.this$0 = lockFreeLinkedListNode;
    }

    /* access modifiers changed from: protected */
    @Nullable
    public LockFreeLinkedListNode getAffectedNode() {
        return this.this$0;
    }

    /* access modifiers changed from: protected */
    @Nullable
    public LockFreeLinkedListNode getOriginalNext() {
        return (LockFreeLinkedListNode) this._originalNext;
    }

    /* access modifiers changed from: protected */
    @Nullable
    public Object failure(@NotNull LockFreeLinkedListNode lockFreeLinkedListNode, @NotNull Object obj) {
        Intrinsics.checkParameterIsNotNull(lockFreeLinkedListNode, "affected");
        Intrinsics.checkParameterIsNotNull(obj, "next");
        if (obj instanceof Removed) {
            return LockFreeLinkedListKt.getALREADY_REMOVED();
        }
        return null;
    }

    /* access modifiers changed from: protected */
    @Nullable
    public Object onPrepare(@NotNull LockFreeLinkedListNode lockFreeLinkedListNode, @NotNull LockFreeLinkedListNode lockFreeLinkedListNode2) {
        Intrinsics.checkParameterIsNotNull(lockFreeLinkedListNode, "affected");
        Intrinsics.checkParameterIsNotNull(lockFreeLinkedListNode2, "next");
        _originalNext$FU.compareAndSet(this, null, lockFreeLinkedListNode2);
        return null;
    }

    /* access modifiers changed from: protected */
    @NotNull
    public Removed updatedNext(@NotNull LockFreeLinkedListNode lockFreeLinkedListNode, @NotNull LockFreeLinkedListNode lockFreeLinkedListNode2) {
        Intrinsics.checkParameterIsNotNull(lockFreeLinkedListNode, "affected");
        Intrinsics.checkParameterIsNotNull(lockFreeLinkedListNode2, "next");
        return lockFreeLinkedListNode2.removed();
    }

    /* access modifiers changed from: protected */
    public void finishOnSuccess(@NotNull LockFreeLinkedListNode lockFreeLinkedListNode, @NotNull LockFreeLinkedListNode lockFreeLinkedListNode2) {
        Intrinsics.checkParameterIsNotNull(lockFreeLinkedListNode, "affected");
        Intrinsics.checkParameterIsNotNull(lockFreeLinkedListNode2, "next");
        this.this$0.finishRemove(lockFreeLinkedListNode2);
    }
}
