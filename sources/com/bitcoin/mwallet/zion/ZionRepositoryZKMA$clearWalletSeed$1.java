package com.bitcoin.mwallet.zion;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u000e\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005H@ø\u0001\u0000"}, mo37405d2 = {"clearWalletSeed", "", "zionId", "Lcom/bitcoin/mwallet/zion/ZionId;", "continuation", "Lkotlin/coroutines/Continuation;", "Lcom/bitcoin/mwallet/zion/ZionError;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.zion.ZionRepositoryZKMA", mo38000f = "ZionRepositoryZKMA.kt", mo38001i = {0, 0, 0}, mo38002l = {237}, mo38003m = "clearWalletSeed", mo38004n = {"this", "zionId", "it"}, mo38005s = {"L$0", "L$1", "L$2"})
/* compiled from: ZionRepositoryZKMA.kt */
final class ZionRepositoryZKMA$clearWalletSeed$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ ZionRepositoryZKMA this$0;

    ZionRepositoryZKMA$clearWalletSeed$1(ZionRepositoryZKMA zionRepositoryZKMA, Continuation continuation) {
        this.this$0 = zionRepositoryZKMA;
        super(continuation);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.clearWalletSeed(null, this);
    }
}
