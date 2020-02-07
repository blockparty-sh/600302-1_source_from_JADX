package com.bitcoin.mwallet.core.repositories;

import com.bitcoin.mwallet.core.dao.CredentialDao;
import com.bitcoin.mwallet.core.dao.WalletDao;
import com.bitcoin.mwallet.core.models.credential.Credential;
import com.bitcoin.mwallet.core.models.wallet.C1261Wallet;
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
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.core.repositories.WalletRepository$saveNewWallet$2", mo38000f = "WalletRepository.kt", mo38001i = {}, mo38002l = {126, 127}, mo38003m = "invokeSuspend", mo38004n = {}, mo38005s = {})
/* compiled from: WalletRepository.kt */
final class WalletRepository$saveNewWallet$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Credential $credential;
    final /* synthetic */ C1261Wallet $wallet;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f420p$;
    final /* synthetic */ WalletRepository this$0;

    WalletRepository$saveNewWallet$2(WalletRepository walletRepository, C1261Wallet wallet, Credential credential, Continuation continuation) {
        this.this$0 = walletRepository;
        this.$wallet = wallet;
        this.$credential = credential;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        WalletRepository$saveNewWallet$2 walletRepository$saveNewWallet$2 = new WalletRepository$saveNewWallet$2(this.this$0, this.$wallet, this.$credential, continuation);
        walletRepository$saveNewWallet$2.f420p$ = (CoroutineScope) obj;
        return walletRepository$saveNewWallet$2;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((WalletRepository$saveNewWallet$2) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f420p$;
            WalletDao access$getWalletDao$p = this.this$0.walletDao;
            C1261Wallet[] walletArr = {this.$wallet};
            this.label = 1;
            if (access$getWalletDao$p.upsert(walletArr, this) == coroutine_suspended) {
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
        CredentialDao access$getCredentialDao$p = this.this$0.credentialDao;
        Credential credential = this.$credential;
        this.label = 2;
        if (access$getCredentialDao$p.upsert(credential, (Continuation<? super Unit>) this) == coroutine_suspended) {
            return coroutine_suspended;
        }
        return Unit.INSTANCE;
    }
}
