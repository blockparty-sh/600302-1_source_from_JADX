package com.bitcoin.mwallet.core.services.eventstream;

import com.bitcoin.bitcoink.p008tx.TxId;
import com.bitcoin.mwallet.core.models.p009tx.TxAction;
import com.bitcoin.mwallet.core.models.p009tx.history.HistoricTransaction;
import com.bitcoin.mwallet.core.repositories.TransactionHistoryRepository;
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

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, mo37405d2 = {"<anonymous>", "Lcom/bitcoin/mwallet/core/models/tx/history/HistoricTransaction;", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.core.services.eventstream.EventStreamHandler$showNotification$existingTxHistory$1", mo38000f = "EventStreamHandler.kt", mo38001i = {}, mo38002l = {90}, mo38003m = "invokeSuspend", mo38004n = {}, mo38005s = {})
/* compiled from: EventStreamHandler.kt */
final class EventStreamHandler$showNotification$existingTxHistory$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super HistoricTransaction>, Object> {
    final /* synthetic */ HistoricTransaction $tx;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f439p$;
    final /* synthetic */ EventStreamHandler this$0;

    EventStreamHandler$showNotification$existingTxHistory$1(EventStreamHandler eventStreamHandler, HistoricTransaction historicTransaction, Continuation continuation) {
        this.this$0 = eventStreamHandler;
        this.$tx = historicTransaction;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        EventStreamHandler$showNotification$existingTxHistory$1 eventStreamHandler$showNotification$existingTxHistory$1 = new EventStreamHandler$showNotification$existingTxHistory$1(this.this$0, this.$tx, continuation);
        eventStreamHandler$showNotification$existingTxHistory$1.f439p$ = (CoroutineScope) obj;
        return eventStreamHandler$showNotification$existingTxHistory$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((EventStreamHandler$showNotification$existingTxHistory$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f439p$;
            TransactionHistoryRepository access$getTxHistoryRepository$p = this.this$0.txHistoryRepository;
            TxId txId = this.$tx.getTxId();
            TxAction txAction = TxAction.RECEIVED;
            this.label = 1;
            obj = access$getTxHistoryRepository$p.getTransactionByIdAndAction(txId, txAction, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return obj;
    }
}
