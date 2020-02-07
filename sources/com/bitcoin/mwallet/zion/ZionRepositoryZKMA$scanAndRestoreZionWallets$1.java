package com.bitcoin.mwallet.zion;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u001e\u0010\u0002\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00050\u00040\u0003H@ø\u0001\u0000"}, mo37405d2 = {"scanAndRestoreZionWallets", "", "continuation", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/Pair;", "Lcom/bitcoin/mwallet/zion/ZionId;", "Lcom/bitcoin/mwallet/zion/ZionNamePrefix;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.zion.ZionRepositoryZKMA", mo38000f = "ZionRepositoryZKMA.kt", mo38001i = {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4}, mo38002l = {59, 72, 74, 80, 370}, mo38003m = "scanAndRestoreZionWallets", mo38004n = {"this", "this", "usedNames", "zionNames", "zionIds", "start", "$this$forEach$iv", "element$iv", "prefix", "index", "zionWalletName", "this", "usedNames", "zionNames", "zionIds", "start", "$this$forEach$iv", "element$iv", "prefix", "index", "zionWalletName", "zionId", "this", "usedNames", "zionNames", "zionIds", "start", "$this$forEach$iv", "element$iv", "prefix", "index", "zionWalletName", "zionId", "seedExists", "this", "usedNames", "zionNames", "zionIds", "start"}, mo38005s = {"L$0", "L$0", "L$1", "L$2", "L$3", "J$0", "L$4", "L$6", "L$7", "I$2", "L$8", "L$0", "L$1", "L$2", "L$3", "J$0", "L$4", "L$6", "L$7", "I$2", "L$8", "L$9", "L$0", "L$1", "L$2", "L$3", "J$0", "L$4", "L$6", "L$7", "I$2", "L$8", "L$9", "Z$0", "L$0", "L$1", "L$2", "L$3", "J$0"})
/* compiled from: ZionRepositoryZKMA.kt */
final class ZionRepositoryZKMA$scanAndRestoreZionWallets$1 extends ContinuationImpl {
    int I$0;
    int I$1;
    int I$2;
    int I$3;
    long J$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    Object L$8;
    Object L$9;
    boolean Z$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ ZionRepositoryZKMA this$0;

    ZionRepositoryZKMA$scanAndRestoreZionWallets$1(ZionRepositoryZKMA zionRepositoryZKMA, Continuation continuation) {
        this.this$0 = zionRepositoryZKMA;
        super(continuation);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.scanAndRestoreZionWallets(this);
    }
}
