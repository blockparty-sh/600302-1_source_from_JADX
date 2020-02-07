package com.bitcoin.mwallet.app.flows.receive.receive;

import com.bitcoin.bitcoink.address.Address;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
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
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.app.flows.receive.receive.ReceivePresenter$setAddress$1", mo38000f = "ReceivePresenter.kt", mo38001i = {0}, mo38002l = {149}, mo38003m = "invokeSuspend", mo38004n = {"walletId"}, mo38005s = {"L$0"})
/* compiled from: ReceivePresenter.kt */
final class ReceivePresenter$setAddress$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f265p$;
    final /* synthetic */ ReceivePresenter this$0;

    ReceivePresenter$setAddress$1(ReceivePresenter receivePresenter, Continuation continuation) {
        this.this$0 = receivePresenter;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        ReceivePresenter$setAddress$1 receivePresenter$setAddress$1 = new ReceivePresenter$setAddress$1(this.this$0, continuation);
        receivePresenter$setAddress$1.f265p$ = (CoroutineScope) obj;
        return receivePresenter$setAddress$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((ReceivePresenter$setAddress$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f265p$;
            WalletId walletId = (WalletId) this.this$0.getSelectedWalletId().getValue();
            if (walletId != null) {
                ReceivePresenter receivePresenter = this.this$0;
                this.L$0 = walletId;
                this.label = 1;
                obj = receivePresenter.getCurrentReceiveAddress(walletId, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
            return Unit.INSTANCE;
        } else if (i == 1) {
            WalletId walletId2 = (WalletId) this.L$0;
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        this.this$0.setReceiveAddress((Address) obj);
        return Unit.INSTANCE;
    }
}
