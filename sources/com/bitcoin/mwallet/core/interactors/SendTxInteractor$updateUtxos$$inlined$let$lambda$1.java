package com.bitcoin.mwallet.core.interactors;

import com.bitcoin.bitcoink.p008tx.TxId;
import com.bitcoin.mwallet.core.models.p009tx.slputxo.SlpUtxoSelection;
import com.bitcoin.mwallet.core.models.p009tx.utxo.Utxo;
import com.bitcoin.mwallet.core.models.p009tx.utxo.UtxoSelection;
import com.bitcoin.mwallet.core.repositories.UtxoRepository;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref.IntRef;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, mo37405d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "com/bitcoin/mwallet/core/interactors/SendTxInteractor$updateUtxos$2$1"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: SendTxInteractor.kt */
final class SendTxInteractor$updateUtxos$$inlined$let$lambda$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Utxo $changeUtxo;
    final /* synthetic */ IntRef $slpCoinChangeOutputIndex$inlined;
    final /* synthetic */ SlpUtxoSelection $slpUtxoSelection$inlined;
    final /* synthetic */ TxId $txId$inlined;
    final /* synthetic */ UtxoSelection $utxoSelection$inlined;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f357p$;
    final /* synthetic */ SendTxInteractor this$0;

    SendTxInteractor$updateUtxos$$inlined$let$lambda$1(Utxo utxo, Continuation continuation, SendTxInteractor sendTxInteractor, TxId txId, IntRef intRef, SlpUtxoSelection slpUtxoSelection, UtxoSelection utxoSelection) {
        this.$changeUtxo = utxo;
        this.this$0 = sendTxInteractor;
        this.$txId$inlined = txId;
        this.$slpCoinChangeOutputIndex$inlined = intRef;
        this.$slpUtxoSelection$inlined = slpUtxoSelection;
        this.$utxoSelection$inlined = utxoSelection;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        SendTxInteractor$updateUtxos$$inlined$let$lambda$1 sendTxInteractor$updateUtxos$$inlined$let$lambda$1 = new SendTxInteractor$updateUtxos$$inlined$let$lambda$1(this.$changeUtxo, continuation, this.this$0, this.$txId$inlined, this.$slpCoinChangeOutputIndex$inlined, this.$slpUtxoSelection$inlined, this.$utxoSelection$inlined);
        sendTxInteractor$updateUtxos$$inlined$let$lambda$1.f357p$ = (CoroutineScope) obj;
        return sendTxInteractor$updateUtxos$$inlined$let$lambda$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((SendTxInteractor$updateUtxos$$inlined$let$lambda$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f357p$;
            UtxoRepository access$getUtxoRepository$p = this.this$0.utxoRepository;
            Collection listOf = CollectionsKt.listOf(this.$changeUtxo);
            this.label = 1;
            if (access$getUtxoRepository$p.upsert(listOf, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
