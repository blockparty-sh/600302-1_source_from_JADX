package com.bitcoin.mwallet.core.interactors;

import com.bitcoin.mwallet.core.models.Coin;
import com.bitcoin.mwallet.core.models.networkfee.NetworkFeeLevel;
import com.bitcoin.mwallet.core.models.networkfee.NetworkFees;
import kotlin.Metadata;
import kotlin.Pair;
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

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001*\u00020\u0004H@ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006"}, mo37405d2 = {"<anonymous>", "Lkotlin/Pair;", "Lcom/bitcoin/mwallet/core/models/networkfee/NetworkFees;", "Lcom/bitcoin/mwallet/core/models/networkfee/NetworkFeeLevel;", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.core.interactors.FeeValidatorInteractor$isInsufficientCoinFee$networkFee$1", mo38000f = "FeeValidatorInteractor.kt", mo38001i = {}, mo38002l = {51}, mo38003m = "invokeSuspend", mo38004n = {}, mo38005s = {})
/* compiled from: FeeValidatorInteractor.kt */
final class FeeValidatorInteractor$isInsufficientCoinFee$networkFee$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Pair<? extends NetworkFees, ? extends NetworkFeeLevel>>, Object> {
    final /* synthetic */ Coin $coin;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f346p$;
    final /* synthetic */ FeeValidatorInteractor this$0;

    FeeValidatorInteractor$isInsufficientCoinFee$networkFee$1(FeeValidatorInteractor feeValidatorInteractor, Coin coin, Continuation continuation) {
        this.this$0 = feeValidatorInteractor;
        this.$coin = coin;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        FeeValidatorInteractor$isInsufficientCoinFee$networkFee$1 feeValidatorInteractor$isInsufficientCoinFee$networkFee$1 = new FeeValidatorInteractor$isInsufficientCoinFee$networkFee$1(this.this$0, this.$coin, continuation);
        feeValidatorInteractor$isInsufficientCoinFee$networkFee$1.f346p$ = (CoroutineScope) obj;
        return feeValidatorInteractor$isInsufficientCoinFee$networkFee$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((FeeValidatorInteractor$isInsufficientCoinFee$networkFee$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f346p$;
            GetNetworkFeeInteractor getNetworkFeeInteractor = this.this$0.getGetNetworkFeeInteractor();
            Coin coin = this.$coin;
            this.label = 1;
            obj = getNetworkFeeInteractor.getNetworkFeeAndPreference(coin, this);
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
