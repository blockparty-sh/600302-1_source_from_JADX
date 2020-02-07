package com.bitcoin.mwallet.core.services.eventstream;

import com.bitcoin.mwallet.TxHistory;
import com.bitcoin.mwallet.core.models.p009tx.history.HistoricTransaction;
import com.bitcoin.mwallet.core.models.wallet.C1261Wallet;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import com.bitcoin.mwallet.core.repositories.TransactionHistoryRepository;
import com.bitcoin.mwallet.core.services.grpc.ProtoConverterKt;
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
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.core.services.eventstream.EventStreamHandler$onTxHistory$1", mo38000f = "EventStreamHandler.kt", mo38001i = {0, 0, 0}, mo38002l = {79}, mo38003m = "invokeSuspend", mo38004n = {"walletId", "wallet", "tx"}, mo38005s = {"L$0", "L$1", "L$2"})
/* compiled from: EventStreamHandler.kt */
final class EventStreamHandler$onTxHistory$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ TxHistory $txHistory;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f436p$;
    final /* synthetic */ EventStreamHandler this$0;

    EventStreamHandler$onTxHistory$1(EventStreamHandler eventStreamHandler, TxHistory txHistory, Continuation continuation) {
        this.this$0 = eventStreamHandler;
        this.$txHistory = txHistory;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        EventStreamHandler$onTxHistory$1 eventStreamHandler$onTxHistory$1 = new EventStreamHandler$onTxHistory$1(this.this$0, this.$txHistory, continuation);
        eventStreamHandler$onTxHistory$1.f436p$ = (CoroutineScope) obj;
        return eventStreamHandler$onTxHistory$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((EventStreamHandler$onTxHistory$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        HistoricTransaction historicTransaction;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f436p$;
            String walletId = this.$txHistory.getWalletId();
            Intrinsics.checkExpressionValueIsNotNull(walletId, "txHistory.walletId");
            WalletId walletId2 = new WalletId(walletId);
            C1261Wallet wallet = (C1261Wallet) this.this$0.getWallets().get(walletId2);
            if (wallet != null) {
                historicTransaction = ProtoConverterKt.toDomain(this.$txHistory, wallet.getCoin());
                this.this$0.showNotification(historicTransaction);
                if (historicTransaction.getConfirmations() <= 0) {
                    TransactionHistoryRepository access$getTxHistoryRepository$p = this.this$0.txHistoryRepository;
                    this.L$0 = walletId2;
                    this.L$1 = wallet;
                    this.L$2 = historicTransaction;
                    this.label = 1;
                    if (access$getTxHistoryRepository$p.upsert(historicTransaction, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
            }
            return Unit.INSTANCE;
        } else if (i == 1) {
            HistoricTransaction historicTransaction2 = (HistoricTransaction) this.L$2;
            C1261Wallet wallet2 = (C1261Wallet) this.L$1;
            WalletId walletId3 = (WalletId) this.L$0;
            ResultKt.throwOnFailure(obj);
            historicTransaction = historicTransaction2;
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        EventStreamReceivedTxSubscriber eventStreamReceivedTxSubscriber = (EventStreamReceivedTxSubscriber) this.this$0.getTxHistorySubscription().getAndSet(null);
        if (eventStreamReceivedTxSubscriber != null) {
            eventStreamReceivedTxSubscriber.onTransaction(historicTransaction);
        }
        return Unit.INSTANCE;
    }
}
