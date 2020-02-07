package com.bitcoin.mwallet.core.interactors;

import com.bitcoin.mwallet.core.models.p009tx.utxo.WalletUtxos;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import com.bitcoin.mwallet.core.repositories.UtxoRepository;
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

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, mo37405d2 = {"<anonymous>", "Lcom/bitcoin/mwallet/core/models/tx/utxo/WalletUtxos;", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.core.interactors.FeeValidatorInteractor$isInsufficientTokenFee$walletUtxos$1", mo38000f = "FeeValidatorInteractor.kt", mo38001i = {}, mo38002l = {22}, mo38003m = "invokeSuspend", mo38004n = {}, mo38005s = {})
/* compiled from: FeeValidatorInteractor.kt */
final class FeeValidatorInteractor$isInsufficientTokenFee$walletUtxos$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super WalletUtxos>, Object> {
    final /* synthetic */ WalletId $walletId;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f350p$;
    final /* synthetic */ FeeValidatorInteractor this$0;

    FeeValidatorInteractor$isInsufficientTokenFee$walletUtxos$1(FeeValidatorInteractor feeValidatorInteractor, WalletId walletId, Continuation continuation) {
        this.this$0 = feeValidatorInteractor;
        this.$walletId = walletId;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        FeeValidatorInteractor$isInsufficientTokenFee$walletUtxos$1 feeValidatorInteractor$isInsufficientTokenFee$walletUtxos$1 = new FeeValidatorInteractor$isInsufficientTokenFee$walletUtxos$1(this.this$0, this.$walletId, continuation);
        feeValidatorInteractor$isInsufficientTokenFee$walletUtxos$1.f350p$ = (CoroutineScope) obj;
        return feeValidatorInteractor$isInsufficientTokenFee$walletUtxos$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((FeeValidatorInteractor$isInsufficientTokenFee$walletUtxos$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f350p$;
            UtxoRepository utxoRepository = this.this$0.getUtxoRepository();
            WalletId walletId = this.$walletId;
            this.label = 1;
            obj = utxoRepository.getWalletUtxos(walletId, this);
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
