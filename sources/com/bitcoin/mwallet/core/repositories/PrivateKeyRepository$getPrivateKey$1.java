package com.bitcoin.mwallet.core.repositories;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000$\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u000e\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\nH@ø\u0001\u0000"}, mo37405d2 = {"getPrivateKey", "", "walletNetwork", "Lcom/bitcoin/bitcoink/Network;", "walletCredentialId", "Lcom/bitcoin/mwallet/core/models/credential/CredentialId;", "walletPath", "Lcom/bitcoin/bitcoink/DerivationPath;", "addressPath", "continuation", "Lkotlin/coroutines/Continuation;", "Lcom/bitcoin/bitcoink/PrivateKey;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.core.repositories.PrivateKeyRepository", mo38000f = "PrivateKeyRepository.kt", mo38001i = {0, 0, 0, 0, 0}, mo38002l = {33}, mo38003m = "getPrivateKey", mo38004n = {"this", "walletNetwork", "walletCredentialId", "walletPath", "addressPath"}, mo38005s = {"L$0", "L$1", "L$2", "L$3", "L$4"})
/* compiled from: PrivateKeyRepository.kt */
final class PrivateKeyRepository$getPrivateKey$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ PrivateKeyRepository this$0;

    PrivateKeyRepository$getPrivateKey$1(PrivateKeyRepository privateKeyRepository, Continuation continuation) {
        this.this$0 = privateKeyRepository;
        super(continuation);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.getPrivateKey(null, null, null, null, this);
    }
}
