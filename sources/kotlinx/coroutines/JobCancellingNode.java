package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Job;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b \u0018\u0000*\n\b\u0000\u0010\u0001 \u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\r\u0012\u0006\u0010\u0004\u001a\u00028\u0000¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, mo37405d2 = {"Lkotlinx/coroutines/JobCancellingNode;", "J", "Lkotlinx/coroutines/Job;", "Lkotlinx/coroutines/JobNode;", "job", "(Lkotlinx/coroutines/Job;)V", "kotlinx-coroutines-core"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: JobSupport.kt */
public abstract class JobCancellingNode<J extends Job> extends JobNode<J> {
    public JobCancellingNode(@NotNull J j) {
        Intrinsics.checkParameterIsNotNull(j, "job");
        super(j);
    }
}
