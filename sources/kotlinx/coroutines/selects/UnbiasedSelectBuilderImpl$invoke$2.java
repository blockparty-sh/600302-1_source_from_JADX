package kotlinx.coroutines.selects;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0006\b\u0001\u0010\u0003 \u0000H\n¢\u0006\u0002\b\u0004"}, mo37405d2 = {"<anonymous>", "", "Q", "R", "invoke"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: SelectUnbiased.kt */
final class UnbiasedSelectBuilderImpl$invoke$2 extends Lambda implements Function0<Unit> {
    final /* synthetic */ Function2 $block;
    final /* synthetic */ SelectClause1 $this_invoke;
    final /* synthetic */ UnbiasedSelectBuilderImpl this$0;

    UnbiasedSelectBuilderImpl$invoke$2(UnbiasedSelectBuilderImpl unbiasedSelectBuilderImpl, SelectClause1 selectClause1, Function2 function2) {
        this.this$0 = unbiasedSelectBuilderImpl;
        this.$this_invoke = selectClause1;
        this.$block = function2;
        super(0);
    }

    public final void invoke() {
        this.$this_invoke.registerSelectClause1(this.this$0.getInstance(), this.$block);
    }
}
