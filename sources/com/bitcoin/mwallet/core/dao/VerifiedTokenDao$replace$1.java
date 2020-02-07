package com.bitcoin.mwallet.core.dao;

import com.bitcoin.mwallet.core.dao.VerifiedTokenDao.DefaultImpls;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0012\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003\"\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H@ø\u0001\u0000"}, mo37405d2 = {"replace", "", "values", "", "Lcom/bitcoin/mwallet/core/models/verifiedtoken/VerifiedToken;", "continuation", "Lkotlin/coroutines/Continuation;", ""}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.core.dao.VerifiedTokenDao$DefaultImpls", mo38000f = "VerifiedTokenDao.kt", mo38001i = {0, 0, 1, 1}, mo38002l = {22, 23}, mo38003m = "replace", mo38004n = {"$this", "values", "$this", "values"}, mo38005s = {"L$0", "L$1", "L$0", "L$1"})
/* compiled from: VerifiedTokenDao.kt */
final class VerifiedTokenDao$replace$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ VerifiedTokenDao this$0;

    VerifiedTokenDao$replace$1(VerifiedTokenDao verifiedTokenDao, Continuation continuation) {
        this.this$0 = verifiedTokenDao;
        super(continuation);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        VerifiedTokenDao verifiedTokenDao = this.this$0;
        return DefaultImpls.replace(null, null, this);
    }
}
