package com.bitcoin.mwallet.core.dao;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u000e\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005H@ø\u0001\u0000"}, mo37405d2 = {"select", "", "credential", "Lcom/bitcoin/mwallet/core/models/credential/CredentialId;", "continuation", "Lkotlin/coroutines/Continuation;", "Lcom/bitcoin/mwallet/core/models/credential/Credential;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.core.dao.CredentialDao", mo38000f = "CredentialDao.kt", mo38001i = {0, 0, 1, 1, 2, 2, 3, 3}, mo38002l = {51, 52, 53, 54}, mo38003m = "select$suspendImpl", mo38004n = {"this", "credential", "this", "credential", "this", "credential", "this", "credential"}, mo38005s = {"L$0", "L$1", "L$0", "L$1", "L$0", "L$1", "L$0", "L$1"})
/* compiled from: CredentialDao.kt */
final class CredentialDao$select$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ CredentialDao this$0;

    CredentialDao$select$1(CredentialDao credentialDao, Continuation continuation) {
        this.this$0 = credentialDao;
        super(continuation);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return CredentialDao.select$suspendImpl(this.this$0, null, this);
    }
}
