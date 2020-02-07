package com.bitcoin.mwallet.zion;

import com.bitcoin.mwallet.zion.ZionRepositoryZKMA.Companion;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00062\u000e\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00020\bH@ø\u0001\u0000"}, mo37405d2 = {"tryZionCall", "", "T", "errorMessage", "", "func", "Lkotlin/Function0;", "continuation", "Lkotlin/coroutines/Continuation;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.zion.ZionRepositoryZKMA$Companion", mo38000f = "ZionRepositoryZKMA.kt", mo38001i = {0, 0, 0}, mo38002l = {338}, mo38003m = "tryZionCall", mo38004n = {"this", "errorMessage", "func"}, mo38005s = {"L$0", "L$1", "L$2"})
/* compiled from: ZionRepositoryZKMA.kt */
final class ZionRepositoryZKMA$Companion$tryZionCall$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ Companion this$0;

    ZionRepositoryZKMA$Companion$tryZionCall$1(Companion companion, Continuation continuation) {
        this.this$0 = companion;
        super(continuation);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.tryZionCall(null, null, this);
    }
}
