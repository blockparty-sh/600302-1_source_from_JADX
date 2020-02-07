package com.bitcoin.mwallet.zion;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000 \n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0012\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u0007H@ø\u0001\u0000"}, mo37405d2 = {"getWalletXPub", "", "zionId", "Lcom/bitcoin/mwallet/zion/ZionId;", "coin", "Lcom/bitcoin/mwallet/core/models/Coin;", "continuation", "Lkotlin/coroutines/Continuation;", "Lcom/bitcoin/mwallet/zion/ZionResponse;", "Lcom/bitcoin/mwallet/zion/ZionXPub;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.zion.ZionRepositoryZKMA", mo38000f = "ZionRepositoryZKMA.kt", mo38001i = {0, 0, 0, 0, 0, 0, 0, 0}, mo38002l = {189}, mo38003m = "getWalletXPub", mo38004n = {"this", "zionId", "coin", "walletPath", "purpose", "coinType", "account", "it"}, mo38005s = {"L$0", "L$1", "L$2", "L$3", "I$0", "I$1", "I$2", "L$4"})
/* compiled from: ZionRepositoryZKMA.kt */
final class ZionRepositoryZKMA$getWalletXPub$1 extends ContinuationImpl {
    int I$0;
    int I$1;
    int I$2;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ ZionRepositoryZKMA this$0;

    ZionRepositoryZKMA$getWalletXPub$1(ZionRepositoryZKMA zionRepositoryZKMA, Continuation continuation) {
        this.this$0 = zionRepositoryZKMA;
        super(continuation);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.getWalletXPub(null, null, this);
    }
}
