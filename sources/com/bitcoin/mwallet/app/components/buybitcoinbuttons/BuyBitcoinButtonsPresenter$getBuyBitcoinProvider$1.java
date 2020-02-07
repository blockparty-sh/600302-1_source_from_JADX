package com.bitcoin.mwallet.app.components.buybitcoinbuttons;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u000e\u0010\u0002\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003H@ø\u0001\u0000"}, mo37405d2 = {"getBuyBitcoinProvider", "", "continuation", "Lkotlin/coroutines/Continuation;", "Lcom/bitcoin/mwallet/app/components/buybitcoinbuttons/view/BuyBitcoinProvider;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.app.components.buybitcoinbuttons.BuyBitcoinButtonsPresenter", mo38000f = "BuyBitcoinButtonsPresenter.kt", mo38001i = {0, 0}, mo38002l = {57}, mo38003m = "getBuyBitcoinProvider", mo38004n = {"this", "existingBuyProvider"}, mo38005s = {"L$0", "L$1"})
/* compiled from: BuyBitcoinButtonsPresenter.kt */
final class BuyBitcoinButtonsPresenter$getBuyBitcoinProvider$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ BuyBitcoinButtonsPresenter this$0;

    BuyBitcoinButtonsPresenter$getBuyBitcoinProvider$1(BuyBitcoinButtonsPresenter buyBitcoinButtonsPresenter, Continuation continuation) {
        this.this$0 = buyBitcoinButtonsPresenter;
        super(continuation);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.getBuyBitcoinProvider(this);
    }
}
