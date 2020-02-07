package com.bitcoin.mwallet.app.flows.sendv2.sendamountselection;

import com.bitcoin.bitcoink.p008tx.Satoshis;
import com.bitcoin.mwallet.app.components.amountselection.AmountSelectionPresenterBase.EntryType;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, mo37405d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.app.flows.sendv2.sendamountselection.SendAmountSelectionPresenter$setInitialSatoshiAmount$1", mo38000f = "SendAmountSelectionPresenter.kt", mo38001i = {}, mo38002l = {}, mo38003m = "invokeSuspend", mo38004n = {}, mo38005s = {})
/* compiled from: SendAmountSelectionPresenter.kt */
final class SendAmountSelectionPresenter$setInitialSatoshiAmount$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Satoshis $amount;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f301p$;
    final /* synthetic */ SendAmountSelectionPresenter this$0;

    SendAmountSelectionPresenter$setInitialSatoshiAmount$1(SendAmountSelectionPresenter sendAmountSelectionPresenter, Satoshis satoshis, Continuation continuation) {
        this.this$0 = sendAmountSelectionPresenter;
        this.$amount = satoshis;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        SendAmountSelectionPresenter$setInitialSatoshiAmount$1 sendAmountSelectionPresenter$setInitialSatoshiAmount$1 = new SendAmountSelectionPresenter$setInitialSatoshiAmount$1(this.this$0, this.$amount, continuation);
        sendAmountSelectionPresenter$setInitialSatoshiAmount$1.f301p$ = (CoroutineScope) obj;
        return sendAmountSelectionPresenter$setInitialSatoshiAmount$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((SendAmountSelectionPresenter$setInitialSatoshiAmount$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f301p$;
            if (this.$amount != null) {
                this.this$0.getPrimaryEntryType().postValue(EntryType.COIN);
                this.this$0.getPrimaryAmount().postValue(this.this$0.convertBigDecimalToString(this.$amount.getCoins()));
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
