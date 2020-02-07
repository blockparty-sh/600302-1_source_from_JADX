package com.bitcoin.mwallet.app.flows.walletdetails.overview.historyassets.slpassets;

import com.bitcoin.mwallet.core.models.wallet.C1261Wallet;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import com.bitcoin.mwallet.core.repositories.WalletRepository;
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

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, mo37405d2 = {"<anonymous>", "Lcom/bitcoin/mwallet/core/models/wallet/Wallet;", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.app.flows.walletdetails.overview.historyassets.slpassets.SLPAssetsPresenter$initWallet$1", mo38000f = "SLPAssetsPresenter.kt", mo38001i = {}, mo38002l = {50}, mo38003m = "invokeSuspend", mo38004n = {}, mo38005s = {})
/* compiled from: SLPAssetsPresenter.kt */
final class SLPAssetsPresenter$initWallet$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super C1261Wallet>, Object> {
    final /* synthetic */ WalletId $walletId;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f325p$;
    final /* synthetic */ SLPAssetsPresenter this$0;

    SLPAssetsPresenter$initWallet$1(SLPAssetsPresenter sLPAssetsPresenter, WalletId walletId, Continuation continuation) {
        this.this$0 = sLPAssetsPresenter;
        this.$walletId = walletId;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        SLPAssetsPresenter$initWallet$1 sLPAssetsPresenter$initWallet$1 = new SLPAssetsPresenter$initWallet$1(this.this$0, this.$walletId, continuation);
        sLPAssetsPresenter$initWallet$1.f325p$ = (CoroutineScope) obj;
        return sLPAssetsPresenter$initWallet$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((SLPAssetsPresenter$initWallet$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f325p$;
            WalletRepository access$getWalletRepo$p = this.this$0.walletRepo;
            WalletId walletId = this.$walletId;
            this.label = 1;
            obj = access$getWalletRepo$p.getWalletById(walletId, this);
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
