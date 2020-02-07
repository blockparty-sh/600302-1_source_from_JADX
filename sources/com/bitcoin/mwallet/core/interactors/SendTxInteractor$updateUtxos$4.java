package com.bitcoin.mwallet.core.interactors;

import com.bitcoin.mwallet.core.repositories.UtxoRepository;
import java.util.List;
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
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.core.interactors.SendTxInteractor$updateUtxos$4", mo38000f = "SendTxInteractor.kt", mo38001i = {}, mo38002l = {430, 431}, mo38003m = "invokeSuspend", mo38004n = {}, mo38005s = {})
/* compiled from: SendTxInteractor.kt */
final class SendTxInteractor$updateUtxos$4 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ List $spentUtxos;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f361p$;
    final /* synthetic */ SendTxInteractor this$0;

    SendTxInteractor$updateUtxos$4(SendTxInteractor sendTxInteractor, List list, Continuation continuation) {
        this.this$0 = sendTxInteractor;
        this.$spentUtxos = list;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        SendTxInteractor$updateUtxos$4 sendTxInteractor$updateUtxos$4 = new SendTxInteractor$updateUtxos$4(this.this$0, this.$spentUtxos, continuation);
        sendTxInteractor$updateUtxos$4.f361p$ = (CoroutineScope) obj;
        return sendTxInteractor$updateUtxos$4;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((SendTxInteractor$updateUtxos$4) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f361p$;
            UtxoRepository access$getUtxoRepository$p = this.this$0.utxoRepository;
            this.label = 1;
            if (access$getUtxoRepository$p.clearSpentUtxos(this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else if (i == 2) {
            ResultKt.throwOnFailure(obj);
            return Unit.INSTANCE;
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        UtxoRepository access$getUtxoRepository$p2 = this.this$0.utxoRepository;
        List list = this.$spentUtxos;
        this.label = 2;
        if (access$getUtxoRepository$p2.setSent(list, this) == coroutine_suspended) {
            return coroutine_suspended;
        }
        return Unit.INSTANCE;
    }
}
