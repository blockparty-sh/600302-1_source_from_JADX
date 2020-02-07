package com.bitcoin.mwallet.core.repositories;

import com.bitcoin.mwallet.core.dao.CredentialDao;
import com.bitcoin.mwallet.core.dao.WalletDao;
import com.bitcoin.mwallet.core.models.credential.Credential;
import com.bitcoin.mwallet.core.models.credential.CredentialId;
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

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, mo37405d2 = {"<anonymous>", "Lcom/bitcoin/mwallet/core/models/credential/Credential;", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.core.repositories.WalletRepository$getCredential$2", mo38000f = "WalletRepository.kt", mo38001i = {1, 1}, mo38002l = {344, 345}, mo38003m = "invokeSuspend", mo38004n = {"credentialId", "it"}, mo38005s = {"L$0", "L$1"})
/* compiled from: WalletRepository.kt */
final class WalletRepository$getCredential$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Credential>, Object> {
    final /* synthetic */ WalletId $walletId;
    Object L$0;
    Object L$1;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f406p$;
    final /* synthetic */ WalletRepository this$0;

    WalletRepository$getCredential$2(WalletRepository walletRepository, WalletId walletId, Continuation continuation) {
        this.this$0 = walletRepository;
        this.$walletId = walletId;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        WalletRepository$getCredential$2 walletRepository$getCredential$2 = new WalletRepository$getCredential$2(this.this$0, this.$walletId, continuation);
        walletRepository$getCredential$2.f406p$ = (CoroutineScope) obj;
        return walletRepository$getCredential$2;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((WalletRepository$getCredential$2) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Credential credential;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f406p$;
            WalletDao access$getWalletDao$p = this.this$0.walletDao;
            WalletId walletId = this.$walletId;
            this.label = 1;
            obj = access$getWalletDao$p.selectCredentialId(walletId, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else if (i == 2) {
            CredentialId credentialId = (CredentialId) this.L$1;
            CredentialId credentialId2 = (CredentialId) this.L$0;
            ResultKt.throwOnFailure(obj);
            credential = (Credential) obj;
            return credential;
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        CredentialId credentialId3 = (CredentialId) obj;
        if (credentialId3 != null) {
            CredentialDao access$getCredentialDao$p = this.this$0.credentialDao;
            this.L$0 = credentialId3;
            this.L$1 = credentialId3;
            this.label = 2;
            obj = access$getCredentialDao$p.select(credentialId3, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
            credential = (Credential) obj;
            return credential;
        }
        credential = null;
        return credential;
    }
}
