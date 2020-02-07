package com.bitcoin.mwallet.app.flows.walletdetails.overview;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H@ø\u0001\u0000"}, mo37405d2 = {"displayZionMnemonic", "", "walletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "continuation", "Lkotlin/coroutines/Continuation;", ""}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.app.flows.walletdetails.overview.OverviewPresenter", mo38000f = "OverviewPresenter.kt", mo38001i = {0, 0, 1, 1, 1}, mo38002l = {95, 97}, mo38003m = "displayZionMnemonic", mo38004n = {"this", "walletId", "this", "walletId", "credential"}, mo38005s = {"L$0", "L$1", "L$0", "L$1", "L$2"})
/* compiled from: OverviewPresenter.kt */
final class OverviewPresenter$displayZionMnemonic$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ OverviewPresenter this$0;

    OverviewPresenter$displayZionMnemonic$1(OverviewPresenter overviewPresenter, Continuation continuation) {
        this.this$0 = overviewPresenter;
        super(continuation);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.displayZionMnemonic(null, this);
    }
}
