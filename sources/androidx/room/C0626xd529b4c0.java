package androidx.room;

import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.Job;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004¨\u0006\u0005"}, mo37405d2 = {"<anonymous>", "", "it", "", "invoke", "androidx/room/RoomDatabaseKt$acquireTransactionThread$2$1"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* renamed from: androidx.room.RoomDatabaseKt$acquireTransactionThread$$inlined$suspendCancellableCoroutine$lambda$1 */
/* compiled from: RoomDatabase.kt */
final class C0626xd529b4c0 extends Lambda implements Function1<Throwable, Unit> {
    final /* synthetic */ Job $controlJob$inlined;
    final /* synthetic */ Executor $this_acquireTransactionThread$inlined;

    C0626xd529b4c0(Executor executor, Job job) {
        this.$this_acquireTransactionThread$inlined = executor;
        this.$controlJob$inlined = job;
        super(1);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Throwable) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(@Nullable Throwable th) {
        this.$controlJob$inlined.cancel();
    }
}
