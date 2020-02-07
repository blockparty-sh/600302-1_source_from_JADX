package kotlinx.coroutines;

import com.leanplum.internal.Constants.Params;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0010\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0005"}, mo37405d2 = {"Lkotlinx/coroutines/IncompleteStateBox;", "", "state", "Lkotlinx/coroutines/Incomplete;", "(Lkotlinx/coroutines/Incomplete;)V", "kotlinx-coroutines-core"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: JobSupport.kt */
final class IncompleteStateBox {
    @NotNull
    @JvmField
    public final Incomplete state;

    public IncompleteStateBox(@NotNull Incomplete incomplete) {
        Intrinsics.checkParameterIsNotNull(incomplete, Params.STATE);
        this.state = incomplete;
    }
}
