package com.bitcoin.mwallet.core.repositories;

import com.bitcoin.bitcoink.p008tx.TxId;
import com.bitcoin.mwallet.core.dao.MerchantDao;
import com.bitcoin.mwallet.core.models.merchant.Merchant;
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

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, mo37405d2 = {"<anonymous>", "Lcom/bitcoin/mwallet/core/models/merchant/Merchant;", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.core.repositories.MerchantRepository$getMerchantInfo$2", mo38000f = "MerchantRepository.kt", mo38001i = {}, mo38002l = {15}, mo38003m = "invokeSuspend", mo38004n = {}, mo38005s = {})
/* compiled from: MerchantRepository.kt */
final class MerchantRepository$getMerchantInfo$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Merchant>, Object> {
    final /* synthetic */ TxId $txId;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f385p$;
    final /* synthetic */ MerchantRepository this$0;

    MerchantRepository$getMerchantInfo$2(MerchantRepository merchantRepository, TxId txId, Continuation continuation) {
        this.this$0 = merchantRepository;
        this.$txId = txId;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        MerchantRepository$getMerchantInfo$2 merchantRepository$getMerchantInfo$2 = new MerchantRepository$getMerchantInfo$2(this.this$0, this.$txId, continuation);
        merchantRepository$getMerchantInfo$2.f385p$ = (CoroutineScope) obj;
        return merchantRepository$getMerchantInfo$2;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((MerchantRepository$getMerchantInfo$2) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f385p$;
            MerchantDao access$getMerchantDao$p = this.this$0.merchantDao;
            TxId txId = this.$txId;
            this.label = 1;
            obj = access$getMerchantDao$p.selectFromTxId(txId, this);
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
