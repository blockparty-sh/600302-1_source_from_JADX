package com.bitcoin.mwallet.core.services.grpc;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineDispatcher;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J=\u0010\u0007\u001a\u0002H\b\"\u0004\b\u0000\u0010\b2\u0006\u0010\t\u001a\u00020\n2\u001c\u0010\u000b\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\b0\r\u0012\u0006\u0012\u0004\u0018\u00010\u00010\fH@ø\u0001\u0000¢\u0006\u0002\u0010\u000eR\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0002\u0004\n\u0002\b\u0019¨\u0006\u000f"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/services/grpc/GrpcServiceBase;", "", "grpcDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "(Lkotlinx/coroutines/CoroutineDispatcher;)V", "getGrpcDispatcher", "()Lkotlinx/coroutines/CoroutineDispatcher;", "logDuration", "T", "identifier", "", "func", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: GrpcServiceBase.kt */
public abstract class GrpcServiceBase {
    @NotNull
    private final CoroutineDispatcher grpcDispatcher;

    public GrpcServiceBase(@NotNull CoroutineDispatcher coroutineDispatcher) {
        Intrinsics.checkParameterIsNotNull(coroutineDispatcher, "grpcDispatcher");
        this.grpcDispatcher = coroutineDispatcher;
    }

    /* access modifiers changed from: protected */
    @NotNull
    public final CoroutineDispatcher getGrpcDispatcher() {
        return this.grpcDispatcher;
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x004d  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002b  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final <T> java.lang.Object logDuration(@org.jetbrains.annotations.NotNull java.lang.String r11, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super kotlin.coroutines.Continuation<? super T>, ? extends java.lang.Object> r12, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super T> r13) {
        /*
            r10 = this;
            boolean r0 = r13 instanceof com.bitcoin.mwallet.core.services.grpc.GrpcServiceBase$logDuration$1
            if (r0 == 0) goto L_0x0014
            r0 = r13
            com.bitcoin.mwallet.core.services.grpc.GrpcServiceBase$logDuration$1 r0 = (com.bitcoin.mwallet.core.services.grpc.GrpcServiceBase$logDuration$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r13 = r0.label
            int r13 = r13 - r2
            r0.label = r13
            goto L_0x0019
        L_0x0014:
            com.bitcoin.mwallet.core.services.grpc.GrpcServiceBase$logDuration$1 r0 = new com.bitcoin.mwallet.core.services.grpc.GrpcServiceBase$logDuration$1
            r0.<init>(r10, r13)
        L_0x0019:
            java.lang.Object r13 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            java.lang.String r4 = " ms"
            r5 = 32
            java.lang.String r6 = "gRPC call "
            r7 = 1
            if (r2 == 0) goto L_0x004d
            if (r2 != r7) goto L_0x0045
            long r11 = r0.J$0
            java.lang.Object r1 = r0.L$2
            kotlin.jvm.functions.Function1 r1 = (kotlin.jvm.functions.Function1) r1
            java.lang.Object r1 = r0.L$1
            java.lang.String r1 = (java.lang.String) r1
            java.lang.Object r0 = r0.L$0
            com.bitcoin.mwallet.core.services.grpc.GrpcServiceBase r0 = (com.bitcoin.mwallet.core.services.grpc.GrpcServiceBase) r0
            kotlin.ResultKt.throwOnFailure(r13)     // Catch:{ all -> 0x0041 }
            r8 = r11
            r11 = r1
            goto L_0x0065
        L_0x0041:
            r13 = move-exception
            r8 = r11
            r11 = r1
            goto L_0x0089
        L_0x0045:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L_0x004d:
            kotlin.ResultKt.throwOnFailure(r13)
            long r8 = java.lang.System.currentTimeMillis()
            r0.L$0 = r10     // Catch:{ all -> 0x0088 }
            r0.L$1 = r11     // Catch:{ all -> 0x0088 }
            r0.L$2 = r12     // Catch:{ all -> 0x0088 }
            r0.J$0 = r8     // Catch:{ all -> 0x0088 }
            r0.label = r7     // Catch:{ all -> 0x0088 }
            java.lang.Object r13 = r12.invoke(r0)     // Catch:{ all -> 0x0088 }
            if (r13 != r1) goto L_0x0065
            return r1
        L_0x0065:
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            r12.append(r6)
            r12.append(r11)
            r12.append(r5)
            long r0 = java.lang.System.currentTimeMillis()
            long r0 = r0 - r8
            r12.append(r0)
            r12.append(r4)
            java.lang.String r11 = r12.toString()
            java.lang.Object[] r12 = new java.lang.Object[r3]
            timber.log.Timber.m426d(r11, r12)
            return r13
        L_0x0088:
            r13 = move-exception
        L_0x0089:
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            r12.append(r6)
            r12.append(r11)
            r12.append(r5)
            long r0 = java.lang.System.currentTimeMillis()
            long r0 = r0 - r8
            r12.append(r0)
            r12.append(r4)
            java.lang.String r11 = r12.toString()
            java.lang.Object[] r12 = new java.lang.Object[r3]
            timber.log.Timber.m426d(r11, r12)
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.core.services.grpc.GrpcServiceBase.logDuration(java.lang.String, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
