package androidx.room;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H@ø\u0001\u0000"}, mo37405d2 = {"createTransactionContext", "", "Landroidx/room/RoomDatabase;", "continuation", "Lkotlin/coroutines/Continuation;", "Lkotlin/coroutines/CoroutineContext;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "androidx.room.RoomDatabaseKt", mo38000f = "RoomDatabase.kt", mo38001i = {0, 0}, mo38002l = {92}, mo38003m = "createTransactionContext", mo38004n = {"$this$createTransactionContext", "controlJob"}, mo38005s = {"L$0", "L$1"})
/* compiled from: RoomDatabase.kt */
final class RoomDatabaseKt$createTransactionContext$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;

    RoomDatabaseKt$createTransactionContext$1(Continuation continuation) {
        super(continuation);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return RoomDatabaseKt.createTransactionContext(null, this);
    }
}
