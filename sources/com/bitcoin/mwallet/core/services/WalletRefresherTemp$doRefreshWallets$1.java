package com.bitcoin.mwallet.core.services;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H@ø\u0001\u0000"}, mo37405d2 = {"doRefreshWallets", "", "continuation", "Lkotlin/coroutines/Continuation;", ""}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.core.services.WalletRefresherTemp", mo38000f = "WalletRefresherTemp.kt", mo38001i = {0, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5}, mo38002l = {39, 41, 42, 49, 51, 55}, mo38003m = "doRefreshWallets", mo38004n = {"this", "this", "$this$forEach$iv", "element$iv", "it", "this", "$this$forEach$iv", "element$iv", "it", "this", "$this$forEach$iv", "element$iv", "it", "startTime", "this", "$this$forEach$iv", "element$iv", "it", "startTime", "this", "$this$forEach$iv", "element$iv", "it", "startTime", "lastConfirmedTxHistory"}, mo38005s = {"L$0", "L$0", "L$1", "L$3", "L$4", "L$0", "L$1", "L$3", "L$4", "L$0", "L$1", "L$3", "L$4", "J$0", "L$0", "L$1", "L$3", "L$4", "J$0", "L$0", "L$1", "L$3", "L$4", "J$0", "L$5"})
/* compiled from: WalletRefresherTemp.kt */
final class WalletRefresherTemp$doRefreshWallets$1 extends ContinuationImpl {
    long J$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ WalletRefresherTemp this$0;

    WalletRefresherTemp$doRefreshWallets$1(WalletRefresherTemp walletRefresherTemp, Continuation continuation) {
        this.this$0 = walletRefresherTemp;
        super(continuation);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.doRefreshWallets(this);
    }
}
