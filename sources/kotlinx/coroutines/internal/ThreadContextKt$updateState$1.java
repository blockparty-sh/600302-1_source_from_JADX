package kotlinx.coroutines.internal;

import com.leanplum.internal.Constants.Params;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext.Element;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.ThreadContextElement;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, mo37405d2 = {"<no name provided>", "Lkotlinx/coroutines/internal/ThreadState;", "state", "element", "Lkotlin/coroutines/CoroutineContext$Element;", "invoke"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: ThreadContext.kt */
final class ThreadContextKt$updateState$1 extends Lambda implements Function2<ThreadState, Element, ThreadState> {
    public static final ThreadContextKt$updateState$1 INSTANCE = new ThreadContextKt$updateState$1();

    ThreadContextKt$updateState$1() {
        super(2);
    }

    @NotNull
    public final ThreadState invoke(@NotNull ThreadState threadState, @NotNull Element element) {
        Intrinsics.checkParameterIsNotNull(threadState, Params.STATE);
        Intrinsics.checkParameterIsNotNull(element, "element");
        if (element instanceof ThreadContextElement) {
            threadState.append(((ThreadContextElement) element).updateThreadContext(threadState.getContext()));
        }
        return threadState;
    }
}
