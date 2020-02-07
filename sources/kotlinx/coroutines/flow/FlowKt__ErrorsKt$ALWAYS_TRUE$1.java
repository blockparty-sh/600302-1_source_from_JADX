package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0003\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, mo37405d2 = {"<anonymous>", "", "it", "", "invoke"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: Errors.kt */
final class FlowKt__ErrorsKt$ALWAYS_TRUE$1 extends Lambda implements Function1<Throwable, Boolean> {
    public static final FlowKt__ErrorsKt$ALWAYS_TRUE$1 INSTANCE = new FlowKt__ErrorsKt$ALWAYS_TRUE$1();

    FlowKt__ErrorsKt$ALWAYS_TRUE$1() {
        super(1);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        return Boolean.valueOf(invoke((Throwable) obj));
    }

    public final boolean invoke(@NotNull Throwable th) {
        Intrinsics.checkParameterIsNotNull(th, "it");
        return true;
    }
}
