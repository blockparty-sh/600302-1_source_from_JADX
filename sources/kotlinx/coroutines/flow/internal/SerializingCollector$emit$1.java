package kotlinx.coroutines.flow.internal;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u0002H\u00022\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H@ø\u0001\u0000"}, mo37405d2 = {"emit", "", "T", "value", "continuation", "Lkotlin/coroutines/Continuation;", ""}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "kotlinx.coroutines.flow.internal.SerializingCollector", mo38000f = "Concurrent.kt", mo38001i = {0, 0, 1, 1, 2, 2, 3, 3}, mo38002l = {45, 47, 51, 52}, mo38003m = "emit", mo38004n = {"this", "value", "this", "value", "this", "value", "this", "value"}, mo38005s = {"L$0", "L$1", "L$0", "L$1", "L$0", "L$1", "L$0", "L$1"})
/* compiled from: Concurrent.kt */
final class SerializingCollector$emit$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ SerializingCollector this$0;

    SerializingCollector$emit$1(SerializingCollector serializingCollector, Continuation continuation) {
        this.this$0 = serializingCollector;
        super(continuation);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.emit(null, this);
    }
}
