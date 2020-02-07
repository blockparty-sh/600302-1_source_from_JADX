package com.bitcoin.mwallet.core.repositories;

import androidx.lifecycle.LiveData;
import com.bitcoin.mwallet.core.dao.VerifiedTokenDao;
import com.bitcoin.mwallet.core.models.verifiedtoken.VerifiedToken;
import com.bitcoin.mwallet.core.services.verifiedtoken.VerifiedTokenService;
import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0011\u0010\u000b\u001a\u00020\fH@ø\u0001\u0000¢\u0006\u0002\u0010\rJ\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH@ø\u0001\u0000¢\u0006\u0002\u0010\rJ\u0012\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f0\u0012R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u0002\u0004\n\u0002\b\u0019¨\u0006\u0013"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/repositories/VerifiedTokenRepository;", "", "verifiedTokenGrpc", "Lcom/bitcoin/mwallet/core/services/verifiedtoken/VerifiedTokenService;", "verifiedTokenDao", "Lcom/bitcoin/mwallet/core/dao/VerifiedTokenDao;", "(Lcom/bitcoin/mwallet/core/services/verifiedtoken/VerifiedTokenService;Lcom/bitcoin/mwallet/core/dao/VerifiedTokenDao;)V", "getVerifiedTokenDao", "()Lcom/bitcoin/mwallet/core/dao/VerifiedTokenDao;", "getVerifiedTokenGrpc", "()Lcom/bitcoin/mwallet/core/services/verifiedtoken/VerifiedTokenService;", "refreshVerifiedTokens", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "verfiedTokensNonStream", "", "Lcom/bitcoin/mwallet/core/models/verifiedtoken/VerifiedToken;", "verifiedTokens", "Landroidx/lifecycle/LiveData;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: VerifiedTokenRepository.kt */
public final class VerifiedTokenRepository {
    @NotNull
    private final VerifiedTokenDao verifiedTokenDao;
    @NotNull
    private final VerifiedTokenService verifiedTokenGrpc;

    public VerifiedTokenRepository(@NotNull VerifiedTokenService verifiedTokenService, @NotNull VerifiedTokenDao verifiedTokenDao2) {
        Intrinsics.checkParameterIsNotNull(verifiedTokenService, "verifiedTokenGrpc");
        Intrinsics.checkParameterIsNotNull(verifiedTokenDao2, "verifiedTokenDao");
        this.verifiedTokenGrpc = verifiedTokenService;
        this.verifiedTokenDao = verifiedTokenDao2;
    }

    @NotNull
    public final VerifiedTokenService getVerifiedTokenGrpc() {
        return this.verifiedTokenGrpc;
    }

    @NotNull
    public final VerifiedTokenDao getVerifiedTokenDao() {
        return this.verifiedTokenDao;
    }

    @NotNull
    public final LiveData<List<VerifiedToken>> verifiedTokens() {
        return this.verifiedTokenDao.getVerifiedTokens();
    }

    @Nullable
    public final Object verfiedTokensNonStream(@NotNull Continuation<? super List<VerifiedToken>> continuation) {
        return this.verifiedTokenDao.getVerifiedTokensNonStream(continuation);
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0049  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0062  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0065  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object refreshVerifiedTokens(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
        /*
            r7 = this;
            boolean r0 = r8 instanceof com.bitcoin.mwallet.core.repositories.VerifiedTokenRepository$refreshVerifiedTokens$1
            if (r0 == 0) goto L_0x0014
            r0 = r8
            com.bitcoin.mwallet.core.repositories.VerifiedTokenRepository$refreshVerifiedTokens$1 r0 = (com.bitcoin.mwallet.core.repositories.VerifiedTokenRepository$refreshVerifiedTokens$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L_0x0019
        L_0x0014:
            com.bitcoin.mwallet.core.repositories.VerifiedTokenRepository$refreshVerifiedTokens$1 r0 = new com.bitcoin.mwallet.core.repositories.VerifiedTokenRepository$refreshVerifiedTokens$1
            r0.<init>(r7, r8)
        L_0x0019:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x0049
            if (r2 == r4) goto L_0x0041
            if (r2 != r3) goto L_0x0039
            java.lang.Object r1 = r0.L$2
            com.bitcoin.mwallet.core.models.verifiedtoken.VerifiedToken[] r1 = (com.bitcoin.mwallet.core.models.verifiedtoken.VerifiedToken[]) r1
            java.lang.Object r1 = r0.L$1
            com.bitcoin.mwallet.core.services.verifiedtoken.VerifiedTokensResponse r1 = (com.bitcoin.mwallet.core.services.verifiedtoken.VerifiedTokensResponse) r1
            java.lang.Object r0 = r0.L$0
            com.bitcoin.mwallet.core.repositories.VerifiedTokenRepository r0 = (com.bitcoin.mwallet.core.repositories.VerifiedTokenRepository) r0
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x0097
        L_0x0039:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L_0x0041:
            java.lang.Object r2 = r0.L$0
            com.bitcoin.mwallet.core.repositories.VerifiedTokenRepository r2 = (com.bitcoin.mwallet.core.repositories.VerifiedTokenRepository) r2
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x005a
        L_0x0049:
            kotlin.ResultKt.throwOnFailure(r8)
            com.bitcoin.mwallet.core.services.verifiedtoken.VerifiedTokenService r8 = r7.verifiedTokenGrpc
            r0.L$0 = r7
            r0.label = r4
            java.lang.Object r8 = r8.getVerifiedTokens(r0)
            if (r8 != r1) goto L_0x0059
            return r1
        L_0x0059:
            r2 = r7
        L_0x005a:
            com.bitcoin.mwallet.core.services.verifiedtoken.VerifiedTokensResponse r8 = (com.bitcoin.mwallet.core.services.verifiedtoken.VerifiedTokensResponse) r8
            com.bitcoin.mwallet.core.services.grpc.GrpcErrorResponse r4 = r8.getGrpcError()
            if (r4 == 0) goto L_0x0065
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        L_0x0065:
            java.util.List r4 = r8.getList()
            if (r4 == 0) goto L_0x006c
            goto L_0x0070
        L_0x006c:
            java.util.List r4 = kotlin.collections.CollectionsKt.emptyList()
        L_0x0070:
            java.util.Collection r4 = (java.util.Collection) r4
            if (r4 == 0) goto L_0x00a0
            r5 = 0
            com.bitcoin.mwallet.core.models.verifiedtoken.VerifiedToken[] r5 = new com.bitcoin.mwallet.core.models.verifiedtoken.VerifiedToken[r5]
            java.lang.Object[] r4 = r4.toArray(r5)
            if (r4 == 0) goto L_0x0098
            com.bitcoin.mwallet.core.models.verifiedtoken.VerifiedToken[] r4 = (com.bitcoin.mwallet.core.models.verifiedtoken.VerifiedToken[]) r4
            com.bitcoin.mwallet.core.dao.VerifiedTokenDao r5 = r2.verifiedTokenDao
            int r6 = r4.length
            java.lang.Object[] r6 = java.util.Arrays.copyOf(r4, r6)
            com.bitcoin.mwallet.core.models.verifiedtoken.VerifiedToken[] r6 = (com.bitcoin.mwallet.core.models.verifiedtoken.VerifiedToken[]) r6
            r0.L$0 = r2
            r0.L$1 = r8
            r0.L$2 = r4
            r0.label = r3
            java.lang.Object r8 = r5.replace(r6, r0)
            if (r8 != r1) goto L_0x0097
            return r1
        L_0x0097:
            return r8
        L_0x0098:
            kotlin.TypeCastException r8 = new kotlin.TypeCastException
            java.lang.String r0 = "null cannot be cast to non-null type kotlin.Array<T>"
            r8.<init>(r0)
            throw r8
        L_0x00a0:
            kotlin.TypeCastException r8 = new kotlin.TypeCastException
            java.lang.String r0 = "null cannot be cast to non-null type java.util.Collection<T>"
            r8.<init>(r0)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.core.repositories.VerifiedTokenRepository.refreshVerifiedTokens(kotlin.coroutines.Continuation):java.lang.Object");
    }
}
