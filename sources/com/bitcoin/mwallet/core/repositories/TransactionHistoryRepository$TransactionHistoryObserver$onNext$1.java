package com.bitcoin.mwallet.core.repositories;

import com.bitcoin.mwallet.Token;
import com.bitcoin.mwallet.TxHistory;
import com.bitcoin.mwallet.core.dao.SlpDao;
import com.bitcoin.mwallet.core.dao.TransactionHistoryDao;
import com.bitcoin.mwallet.core.models.p009tx.history.HistoricTransaction;
import com.bitcoin.mwallet.core.models.slp.Slp;
import com.bitcoin.mwallet.core.models.slp.SlpId;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import com.bitcoin.mwallet.core.repositories.TransactionHistoryRepository.TransactionHistoryObserver;
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
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.core.repositories.TransactionHistoryRepository$TransactionHistoryObserver$onNext$1", mo38000f = "TransactionHistoryRepository.kt", mo38001i = {1}, mo38002l = {87, 96}, mo38003m = "invokeSuspend", mo38004n = {"slp"}, mo38005s = {"L$0"})
/* compiled from: TransactionHistoryRepository.kt */
final class TransactionHistoryRepository$TransactionHistoryObserver$onNext$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ TxHistory $value;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f390p$;
    final /* synthetic */ TransactionHistoryObserver this$0;

    TransactionHistoryRepository$TransactionHistoryObserver$onNext$1(TransactionHistoryObserver transactionHistoryObserver, TxHistory txHistory, Continuation continuation) {
        this.this$0 = transactionHistoryObserver;
        this.$value = txHistory;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        TransactionHistoryRepository$TransactionHistoryObserver$onNext$1 transactionHistoryRepository$TransactionHistoryObserver$onNext$1 = new TransactionHistoryRepository$TransactionHistoryObserver$onNext$1(this.this$0, this.$value, continuation);
        transactionHistoryRepository$TransactionHistoryObserver$onNext$1.f390p$ = (CoroutineScope) obj;
        return transactionHistoryRepository$TransactionHistoryObserver$onNext$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((TransactionHistoryRepository$TransactionHistoryObserver$onNext$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f390p$;
            TransactionHistoryDao access$getDao$p = this.this$0.this$0.dao;
            HistoricTransaction[] historicTransactionArr = {ProtoConverterKt.toDomain(this.$value, this.this$0.coin)};
            this.label = 1;
            if (access$getDao$p.upsert(historicTransactionArr, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else if (i == 2) {
            Slp slp = (Slp) this.L$0;
            ResultKt.throwOnFailure(obj);
            return Unit.INSTANCE;
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        Token token = this.$value.getToken();
        String str = "value.token";
        Intrinsics.checkExpressionValueIsNotNull(token, str);
        String tokenId = token.getTokenId();
        String str2 = "value.token.tokenId";
        Intrinsics.checkExpressionValueIsNotNull(tokenId, str2);
        if (tokenId.length() > 0) {
            Token token2 = this.$value.getToken();
            Intrinsics.checkExpressionValueIsNotNull(token2, str);
            String tokenId2 = token2.getTokenId();
            Intrinsics.checkExpressionValueIsNotNull(tokenId2, str2);
            SlpId slpId = new SlpId(tokenId2);
            Token token3 = this.$value.getToken();
            Intrinsics.checkExpressionValueIsNotNull(token3, str);
            String tokenTicker = token3.getTokenTicker();
            Intrinsics.checkExpressionValueIsNotNull(tokenTicker, "value.token.tokenTicker");
            Token token4 = this.$value.getToken();
            Intrinsics.checkExpressionValueIsNotNull(token4, str);
            String tokenName = token4.getTokenName();
            Intrinsics.checkExpressionValueIsNotNull(tokenName, "value.token.tokenName");
            String walletId = this.$value.getWalletId();
            Intrinsics.checkExpressionValueIsNotNull(walletId, "value.walletId");
            WalletId walletId2 = new WalletId(walletId);
            Token token5 = this.$value.getToken();
            Intrinsics.checkExpressionValueIsNotNull(token5, str);
            Slp slp2 = new Slp(slpId, tokenTicker, tokenName, walletId2, token5.getTokenDecimals());
            SlpDao access$getSlpDao$p = this.this$0.this$0.slpDao;
            Slp[] slpArr = {slp2};
            this.L$0 = slp2;
            this.label = 2;
            if (access$getSlpDao$p.upsert(slpArr, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        return Unit.INSTANCE;
    }
}
