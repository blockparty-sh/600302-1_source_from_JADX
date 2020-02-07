package com.bitcoin.mwallet.app.flows.sendv2.selectaddress;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u000e\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005H@ø\u0001\u0000"}, mo37405d2 = {"getSatoshiPerByte", "", "coin", "Lcom/bitcoin/mwallet/core/models/Coin;", "continuation", "Lkotlin/coroutines/Continuation;", "Ljava/math/BigDecimal;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.app.flows.sendv2.selectaddress.SelectAddressPresenter", mo38000f = "SelectAddressPresenter.kt", mo38001i = {0, 0}, mo38002l = {180}, mo38003m = "getSatoshiPerByte", mo38004n = {"this", "coin"}, mo38005s = {"L$0", "L$1"})
/* compiled from: SelectAddressPresenter.kt */
final class SelectAddressPresenter$getSatoshiPerByte$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ SelectAddressPresenter this$0;

    SelectAddressPresenter$getSatoshiPerByte$1(SelectAddressPresenter selectAddressPresenter, Continuation continuation) {
        this.this$0 = selectAddressPresenter;
        super(continuation);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.getSatoshiPerByte(null, this);
    }
}
