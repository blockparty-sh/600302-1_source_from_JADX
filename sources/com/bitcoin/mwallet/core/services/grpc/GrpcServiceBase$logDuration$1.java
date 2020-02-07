package com.bitcoin.mwallet.core.services.grpc;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u001c\u0010\u0005\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00062\f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0007H@ø\u0001\u0000"}, mo37405d2 = {"logDuration", "", "T", "identifier", "", "func", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "continuation"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.core.services.grpc.GrpcServiceBase", mo38000f = "GrpcServiceBase.kt", mo38001i = {0, 0, 0, 0}, mo38002l = {16}, mo38003m = "logDuration", mo38004n = {"this", "identifier", "func", "start"}, mo38005s = {"L$0", "L$1", "L$2", "J$0"})
/* compiled from: GrpcServiceBase.kt */
final class GrpcServiceBase$logDuration$1 extends ContinuationImpl {
    long J$0;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ GrpcServiceBase this$0;

    GrpcServiceBase$logDuration$1(GrpcServiceBase grpcServiceBase, Continuation continuation) {
        this.this$0 = grpcServiceBase;
        super(continuation);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.logDuration(null, null, this);
    }
}
