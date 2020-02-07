package com.bitcoin.mwallet.app.flows.walletdetails.deletewallet;

import com.bitcoin.mwallet.core.interactors.GetWalletInteractor;
import com.bitcoin.mwallet.core.models.wallet.C1261Wallet;
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
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.app.flows.walletdetails.deletewallet.DeletewalletPresenter$initializeWalletId$1", mo38000f = "DeletewalletPresenter.kt", mo38001i = {}, mo38002l = {31}, mo38003m = "invokeSuspend", mo38004n = {}, mo38005s = {})
/* compiled from: DeletewalletPresenter.kt */
final class DeletewalletPresenter$initializeWalletId$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ WalletId $walletId;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f318p$;
    final /* synthetic */ DeletewalletPresenter this$0;

    DeletewalletPresenter$initializeWalletId$1(DeletewalletPresenter deletewalletPresenter, WalletId walletId, Continuation continuation) {
        this.this$0 = deletewalletPresenter;
        this.$walletId = walletId;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        DeletewalletPresenter$initializeWalletId$1 deletewalletPresenter$initializeWalletId$1 = new DeletewalletPresenter$initializeWalletId$1(this.this$0, this.$walletId, continuation);
        deletewalletPresenter$initializeWalletId$1.f318p$ = (CoroutineScope) obj;
        return deletewalletPresenter$initializeWalletId$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((DeletewalletPresenter$initializeWalletId$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        DeletewalletPresenter deletewalletPresenter;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f318p$;
            DeletewalletPresenter deletewalletPresenter2 = this.this$0;
            GetWalletInteractor access$getWalletInteractor$p = deletewalletPresenter2.walletInteractor;
            WalletId walletId = this.$walletId;
            this.L$0 = deletewalletPresenter2;
            this.label = 1;
            Object wallet = access$getWalletInteractor$p.getWallet(walletId, this);
            if (wallet == coroutine_suspended) {
                return coroutine_suspended;
            }
            deletewalletPresenter = deletewalletPresenter2;
            obj = wallet;
        } else if (i == 1) {
            deletewalletPresenter = (DeletewalletPresenter) this.L$0;
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        deletewalletPresenter.setWallet((C1261Wallet) obj);
        return Unit.INSTANCE;
    }
}
