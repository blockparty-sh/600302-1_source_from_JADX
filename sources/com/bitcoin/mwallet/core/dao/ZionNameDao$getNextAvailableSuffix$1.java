package com.bitcoin.mwallet.core.dao;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u000e\u0010\u0002\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003H@ø\u0001\u0000"}, mo37405d2 = {"getNextAvailableSuffix", "", "continuation", "Lkotlin/coroutines/Continuation;", ""}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.core.dao.ZionNameDao", mo38000f = "ZionNameDao.kt", mo38001i = {0, 1, 1, 1, 1}, mo38002l = {29, 41}, mo38003m = "getNextAvailableSuffix$suspendImpl", mo38004n = {"this", "this", "takenIds", "freeId", "zionName"}, mo38005s = {"L$0", "L$0", "L$1", "L$2", "L$3"})
/* compiled from: ZionNameDao.kt */
final class ZionNameDao$getNextAvailableSuffix$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ ZionNameDao this$0;

    ZionNameDao$getNextAvailableSuffix$1(ZionNameDao zionNameDao, Continuation continuation) {
        this.this$0 = zionNameDao;
        super(continuation);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return ZionNameDao.getNextAvailableSuffix$suspendImpl(this.this$0, this);
    }
}
