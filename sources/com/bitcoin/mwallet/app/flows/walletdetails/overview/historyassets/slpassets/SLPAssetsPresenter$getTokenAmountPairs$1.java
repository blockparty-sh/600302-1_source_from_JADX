package com.bitcoin.mwallet.app.flows.walletdetails.overview.historyassets.slpassets;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u001e\u0010\u0005\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00040\u00070\u00030\u0006H@ø\u0001\u0000"}, mo37405d2 = {"getTokenAmountPairs", "", "tokens", "", "Lcom/bitcoin/mwallet/core/models/slp/Slp;", "continuation", "Lkotlin/coroutines/Continuation;", "Lkotlin/Pair;", "Ljava/math/BigDecimal;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.app.flows.walletdetails.overview.historyassets.slpassets.SLPAssetsPresenter", mo38000f = "SLPAssetsPresenter.kt", mo38001i = {0, 0, 0, 0}, mo38002l = {60}, mo38003m = "getTokenAmountPairs", mo38004n = {"this", "tokens", "pairs", "token"}, mo38005s = {"L$0", "L$1", "L$2", "L$3"})
/* compiled from: SLPAssetsPresenter.kt */
final class SLPAssetsPresenter$getTokenAmountPairs$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ SLPAssetsPresenter this$0;

    SLPAssetsPresenter$getTokenAmountPairs$1(SLPAssetsPresenter sLPAssetsPresenter, Continuation continuation) {
        this.this$0 = sLPAssetsPresenter;
        super(continuation);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.getTokenAmountPairs(null, this);
    }
}
