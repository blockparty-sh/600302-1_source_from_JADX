package kotlinx.coroutines.scheduling;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, mo37405d2 = {"<anonymous>", "", "it", "Lkotlinx/coroutines/scheduling/Task;", "invoke"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: WorkQueue.kt */
public final class WorkQueue$pollExternal$1 extends Lambda implements Function1<Task, Boolean> {
    public static final WorkQueue$pollExternal$1 INSTANCE = new WorkQueue$pollExternal$1();

    public WorkQueue$pollExternal$1() {
        super(1);
    }

    public final boolean invoke(@NotNull Task task) {
        Intrinsics.checkParameterIsNotNull(task, "it");
        return true;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        return Boolean.valueOf(invoke((Task) obj));
    }
}
