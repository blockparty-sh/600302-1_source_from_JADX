package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.Result.Companion;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.CancellableContinuation;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0000\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\n¢\u0006\u0002\b\u0005"}, mo37405d2 = {"<anonymous>", "", "T", "it", "", "invoke"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: Produce.kt */
final class ProduceKt$awaitClose$4$1 extends Lambda implements Function1<Throwable, Unit> {
    final /* synthetic */ CancellableContinuation $cont;

    ProduceKt$awaitClose$4$1(CancellableContinuation cancellableContinuation) {
        this.$cont = cancellableContinuation;
        super(1);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Throwable) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(@Nullable Throwable th) {
        Continuation continuation = this.$cont;
        Unit unit = Unit.INSTANCE;
        Companion companion = Result.Companion;
        continuation.resumeWith(Result.m479constructorimpl(unit));
    }
}
