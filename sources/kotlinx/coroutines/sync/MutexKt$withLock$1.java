package kotlinx.coroutines.sync;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00020\bHHø\u0001\u0000"}, mo37405d2 = {"withLock", "", "T", "Lkotlinx/coroutines/sync/Mutex;", "owner", "action", "Lkotlin/Function0;", "continuation", "Lkotlin/coroutines/Continuation;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "kotlinx.coroutines.sync.MutexKt", mo38000f = "Mutex.kt", mo38001i = {0, 0, 0}, mo38002l = {109}, mo38003m = "withLock", mo38004n = {"$this$withLock", "owner", "action"}, mo38005s = {"L$0", "L$1", "L$2"})
/* compiled from: Mutex.kt */
public final class MutexKt$withLock$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;

    public MutexKt$withLock$1(Continuation continuation) {
        super(continuation);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return MutexKt.withLock(null, null, null, this);
    }
}
