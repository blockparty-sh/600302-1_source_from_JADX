package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0005\u0010\u0000\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0004\b\u0001\u0010\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, mo37405d2 = {"<anonymous>", "", "T", "R", "invoke", "()[Ljava/lang/Object;", "kotlinx/coroutines/flow/FlowKt__ZipKt$combineLatest$5"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: Zip.kt */
public final class FlowKt__ZipKt$combineLatest$$inlined$combineLatest$5 extends Lambda implements Function0<Object[]> {
    final /* synthetic */ Flow[] $others;

    public FlowKt__ZipKt$combineLatest$$inlined$combineLatest$5(Flow[] flowArr) {
        this.$others = flowArr;
        super(0);
    }

    @NotNull
    public final Object[] invoke() {
        return new Object[(this.$others.length + 1)];
    }
}
