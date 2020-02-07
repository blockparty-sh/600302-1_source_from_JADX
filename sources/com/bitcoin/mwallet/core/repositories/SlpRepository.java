package com.bitcoin.mwallet.core.repositories;

import androidx.lifecycle.LiveData;
import com.bitcoin.mwallet.core.dao.SlpDao;
import com.bitcoin.mwallet.core.dao.SlpUtxoDao;
import com.bitcoin.mwallet.core.models.slp.Slp;
import com.bitcoin.mwallet.core.models.slp.SlpId;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import com.bitcoin.mwallet.core.services.p010tx.TxService;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\u001a\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f0\u000e2\u0006\u0010\u0011\u001a\u00020\u0012J\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH@ø\u0001\u0000¢\u0006\u0002\u0010\u0014J\u001f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\u0006\u0010\u0011\u001a\u00020\u0012H@ø\u0001\u0000¢\u0006\u0002\u0010\u0016J\u001f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00120\u000f2\u0006\u0010\u0018\u001a\u00020\u0019H@ø\u0001\u0000¢\u0006\u0002\u0010\u001aJ#\u0010\u001b\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0018\u001a\u00020\u0019H@ø\u0001\u0000¢\u0006\u0002\u0010\u001cJ!\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0018\u001a\u00020\u0019H@ø\u0001\u0000¢\u0006\u0002\u0010\u001cJ\u001b\u0010\u001f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0018\u001a\u00020\u0019H@ø\u0001\u0000¢\u0006\u0002\u0010\u001aJ\u0019\u0010 \u001a\u00020\u001e2\u0006\u0010\u0018\u001a\u00020\u0019H@ø\u0001\u0000¢\u0006\u0002\u0010\u001aJ\u0019\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$H@ø\u0001\u0000¢\u0006\u0002\u0010%J\u001f\u0010&\u001a\u00020\"2\f\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00100(H@ø\u0001\u0000¢\u0006\u0002\u0010)R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006*"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/repositories/SlpRepository;", "", "txServiceGrpc", "Lcom/bitcoin/mwallet/core/services/tx/TxService;", "slpDao", "Lcom/bitcoin/mwallet/core/dao/SlpDao;", "slpUtxoDao", "Lcom/bitcoin/mwallet/core/dao/SlpUtxoDao;", "walletRepository", "Lcom/bitcoin/mwallet/core/repositories/WalletRepository;", "verifiedTokenRepository", "Lcom/bitcoin/mwallet/core/repositories/VerifiedTokenRepository;", "(Lcom/bitcoin/mwallet/core/services/tx/TxService;Lcom/bitcoin/mwallet/core/dao/SlpDao;Lcom/bitcoin/mwallet/core/dao/SlpUtxoDao;Lcom/bitcoin/mwallet/core/repositories/WalletRepository;Lcom/bitcoin/mwallet/core/repositories/VerifiedTokenRepository;)V", "getAllRawTokens", "Landroidx/lifecycle/LiveData;", "", "Lcom/bitcoin/mwallet/core/models/slp/Slp;", "walletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "getAllTokens", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllTokensFromWalletId", "(Lcom/bitcoin/mwallet/core/models/wallet/WalletId;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllWalletIdsforToken", "tokenId", "Lcom/bitcoin/mwallet/core/models/slp/SlpId;", "(Lcom/bitcoin/mwallet/core/models/slp/SlpId;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getToken", "(Lcom/bitcoin/mwallet/core/models/wallet/WalletId;Lcom/bitcoin/mwallet/core/models/slp/SlpId;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTokenAmount", "Ljava/math/BigDecimal;", "getTokenInfo", "getUserTokenAmount", "refreshTokens", "", "wallet", "Lcom/bitcoin/mwallet/core/models/wallet/Wallet;", "(Lcom/bitcoin/mwallet/core/models/wallet/Wallet;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "upsert", "slps", "", "(Ljava/util/Collection;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: SlpRepository.kt */
public final class SlpRepository {
    /* access modifiers changed from: private */
    public final SlpDao slpDao;
    private final SlpUtxoDao slpUtxoDao;
    private final TxService txServiceGrpc;
    private final VerifiedTokenRepository verifiedTokenRepository;
    private final WalletRepository walletRepository;

    public SlpRepository(@NotNull TxService txService, @NotNull SlpDao slpDao2, @NotNull SlpUtxoDao slpUtxoDao2, @NotNull WalletRepository walletRepository2, @NotNull VerifiedTokenRepository verifiedTokenRepository2) {
        Intrinsics.checkParameterIsNotNull(txService, "txServiceGrpc");
        Intrinsics.checkParameterIsNotNull(slpDao2, "slpDao");
        Intrinsics.checkParameterIsNotNull(slpUtxoDao2, "slpUtxoDao");
        Intrinsics.checkParameterIsNotNull(walletRepository2, "walletRepository");
        Intrinsics.checkParameterIsNotNull(verifiedTokenRepository2, "verifiedTokenRepository");
        this.txServiceGrpc = txService;
        this.slpDao = slpDao2;
        this.slpUtxoDao = slpUtxoDao2;
        this.walletRepository = walletRepository2;
        this.verifiedTokenRepository = verifiedTokenRepository2;
    }

    @Nullable
    public final Object getAllTokens(@NotNull Continuation<? super List<Slp>> continuation) {
        return this.slpDao.selectAllDistinctToken(continuation);
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x006e  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x009d  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00e2  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00e4  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00ef  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00f3  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002b  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object getAllTokensFromWalletId(@org.jetbrains.annotations.NotNull com.bitcoin.mwallet.core.models.wallet.WalletId r18, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.util.List<com.bitcoin.mwallet.core.models.slp.Slp>> r19) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            r2 = r19
            boolean r3 = r2 instanceof com.bitcoin.mwallet.core.repositories.SlpRepository$getAllTokensFromWalletId$1
            if (r3 == 0) goto L_0x001a
            r3 = r2
            com.bitcoin.mwallet.core.repositories.SlpRepository$getAllTokensFromWalletId$1 r3 = (com.bitcoin.mwallet.core.repositories.SlpRepository$getAllTokensFromWalletId$1) r3
            int r4 = r3.label
            r5 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r4 & r5
            if (r4 == 0) goto L_0x001a
            int r2 = r3.label
            int r2 = r2 - r5
            r3.label = r2
            goto L_0x001f
        L_0x001a:
            com.bitcoin.mwallet.core.repositories.SlpRepository$getAllTokensFromWalletId$1 r3 = new com.bitcoin.mwallet.core.repositories.SlpRepository$getAllTokensFromWalletId$1
            r3.<init>(r0, r2)
        L_0x001f:
            java.lang.Object r2 = r3.result
            java.lang.Object r4 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r5 = r3.label
            r6 = 2
            r7 = 1
            if (r5 == 0) goto L_0x006e
            if (r5 == r7) goto L_0x0062
            if (r5 != r6) goto L_0x005a
            java.lang.Object r1 = r3.L$9
            com.bitcoin.mwallet.core.models.slp.Slp r1 = (com.bitcoin.mwallet.core.models.slp.Slp) r1
            java.lang.Object r5 = r3.L$8
            com.bitcoin.mwallet.core.models.slp.Slp r5 = (com.bitcoin.mwallet.core.models.slp.Slp) r5
            java.lang.Object r5 = r3.L$7
            java.lang.Object r8 = r3.L$6
            java.util.Iterator r8 = (java.util.Iterator) r8
            java.lang.Object r9 = r3.L$5
            java.util.Collection r9 = (java.util.Collection) r9
            java.lang.Object r10 = r3.L$4
            java.lang.Iterable r10 = (java.lang.Iterable) r10
            java.lang.Object r11 = r3.L$3
            java.lang.Iterable r11 = (java.lang.Iterable) r11
            java.lang.Object r12 = r3.L$2
            java.util.List r12 = (java.util.List) r12
            java.lang.Object r13 = r3.L$1
            com.bitcoin.mwallet.core.models.wallet.WalletId r13 = (com.bitcoin.mwallet.core.models.wallet.WalletId) r13
            java.lang.Object r14 = r3.L$0
            com.bitcoin.mwallet.core.repositories.SlpRepository r14 = (com.bitcoin.mwallet.core.repositories.SlpRepository) r14
            kotlin.ResultKt.throwOnFailure(r2)
            goto L_0x00c7
        L_0x005a:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r1
        L_0x0062:
            java.lang.Object r1 = r3.L$1
            com.bitcoin.mwallet.core.models.wallet.WalletId r1 = (com.bitcoin.mwallet.core.models.wallet.WalletId) r1
            java.lang.Object r5 = r3.L$0
            com.bitcoin.mwallet.core.repositories.SlpRepository r5 = (com.bitcoin.mwallet.core.repositories.SlpRepository) r5
            kotlin.ResultKt.throwOnFailure(r2)
            goto L_0x0081
        L_0x006e:
            kotlin.ResultKt.throwOnFailure(r2)
            com.bitcoin.mwallet.core.dao.SlpDao r2 = r0.slpDao
            r3.L$0 = r0
            r3.L$1 = r1
            r3.label = r7
            java.lang.Object r2 = r2.selectAllFromWalletIdNonLiveData(r1, r3)
            if (r2 != r4) goto L_0x0080
            return r4
        L_0x0080:
            r5 = r0
        L_0x0081:
            java.util.List r2 = (java.util.List) r2
            r8 = r2
            java.lang.Iterable r8 = (java.lang.Iterable) r8
            java.util.ArrayList r9 = new java.util.ArrayList
            r9.<init>()
            java.util.Collection r9 = (java.util.Collection) r9
            java.util.Iterator r10 = r8.iterator()
            r13 = r1
            r12 = r2
            r14 = r5
            r11 = r8
            r8 = r10
            r10 = r11
        L_0x0097:
            boolean r1 = r8.hasNext()
            if (r1 == 0) goto L_0x00f3
            java.lang.Object r5 = r8.next()
            r1 = r5
            com.bitcoin.mwallet.core.models.slp.Slp r1 = (com.bitcoin.mwallet.core.models.slp.Slp) r1
            com.bitcoin.mwallet.core.dao.SlpUtxoDao r2 = r14.slpUtxoDao
            com.bitcoin.mwallet.core.models.slp.SlpId r15 = r1.getTokenId()
            r3.L$0 = r14
            r3.L$1 = r13
            r3.L$2 = r12
            r3.L$3 = r11
            r3.L$4 = r10
            r3.L$5 = r9
            r3.L$6 = r8
            r3.L$7 = r5
            r3.L$8 = r1
            r3.L$9 = r1
            r3.label = r6
            java.lang.Object r2 = r2.getWalletTokenAmount(r13, r15, r3)
            if (r2 != r4) goto L_0x00c7
            return r4
        L_0x00c7:
            java.lang.Number r2 = (java.lang.Number) r2
            long r15 = r2.longValue()
            java.math.BigDecimal r2 = java.math.BigDecimal.valueOf(r15)
            java.lang.String r15 = "BigDecimal.valueOf(this)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r2, r15)
            java.math.BigDecimal r1 = r1.toReadableAmount(r2)
            java.math.BigDecimal r2 = java.math.BigDecimal.ZERO
            int r1 = r1.compareTo(r2)
            if (r1 <= 0) goto L_0x00e4
            r1 = 1
            goto L_0x00e5
        L_0x00e4:
            r1 = 0
        L_0x00e5:
            java.lang.Boolean r1 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r1)
            boolean r1 = r1.booleanValue()
            if (r1 == 0) goto L_0x0097
            r9.add(r5)
            goto L_0x0097
        L_0x00f3:
            java.util.List r9 = (java.util.List) r9
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.core.repositories.SlpRepository.getAllTokensFromWalletId(com.bitcoin.mwallet.core.models.wallet.WalletId, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Nullable
    public final Object getAllWalletIdsforToken(@NotNull SlpId slpId, @NotNull Continuation<? super List<WalletId>> continuation) {
        return this.slpDao.selectAllWalletIdForToken(slpId, continuation);
    }

    @NotNull
    public final LiveData<List<Slp>> getAllRawTokens(@NotNull WalletId walletId) {
        Intrinsics.checkParameterIsNotNull(walletId, "walletId");
        return this.slpDao.selectAllFromWalletId(walletId);
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x003e  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object getToken(@org.jetbrains.annotations.NotNull com.bitcoin.mwallet.core.models.wallet.WalletId r5, @org.jetbrains.annotations.NotNull com.bitcoin.mwallet.core.models.slp.SlpId r6, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.bitcoin.mwallet.core.models.slp.Slp> r7) {
        /*
            r4 = this;
            boolean r0 = r7 instanceof com.bitcoin.mwallet.core.repositories.SlpRepository$getToken$1
            if (r0 == 0) goto L_0x0014
            r0 = r7
            com.bitcoin.mwallet.core.repositories.SlpRepository$getToken$1 r0 = (com.bitcoin.mwallet.core.repositories.SlpRepository$getToken$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L_0x0019
        L_0x0014:
            com.bitcoin.mwallet.core.repositories.SlpRepository$getToken$1 r0 = new com.bitcoin.mwallet.core.repositories.SlpRepository$getToken$1
            r0.<init>(r4, r7)
        L_0x0019:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x003e
            if (r2 != r3) goto L_0x0036
            java.lang.Object r5 = r0.L$2
            com.bitcoin.mwallet.core.models.slp.SlpId r5 = (com.bitcoin.mwallet.core.models.slp.SlpId) r5
            java.lang.Object r5 = r0.L$1
            com.bitcoin.mwallet.core.models.wallet.WalletId r5 = (com.bitcoin.mwallet.core.models.wallet.WalletId) r5
            java.lang.Object r5 = r0.L$0
            com.bitcoin.mwallet.core.repositories.SlpRepository r5 = (com.bitcoin.mwallet.core.repositories.SlpRepository) r5
            kotlin.ResultKt.throwOnFailure(r7)
            goto L_0x0052
        L_0x0036:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x003e:
            kotlin.ResultKt.throwOnFailure(r7)
            com.bitcoin.mwallet.core.dao.SlpDao r7 = r4.slpDao
            r0.L$0 = r4
            r0.L$1 = r5
            r0.L$2 = r6
            r0.label = r3
            java.lang.Object r7 = r7.selectFromWalletIdAndTokenId(r5, r6, r0)
            if (r7 != r1) goto L_0x0052
            return r1
        L_0x0052:
            com.bitcoin.mwallet.core.models.slp.Slp r7 = (com.bitcoin.mwallet.core.models.slp.Slp) r7
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.core.repositories.SlpRepository.getToken(com.bitcoin.mwallet.core.models.wallet.WalletId, com.bitcoin.mwallet.core.models.slp.SlpId, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0096, code lost:
        if (r7 != null) goto L_0x00a0;
     */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0059  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0080 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0089  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object getTokenAmount(@org.jetbrains.annotations.NotNull com.bitcoin.mwallet.core.models.wallet.WalletId r7, @org.jetbrains.annotations.NotNull com.bitcoin.mwallet.core.models.slp.SlpId r8, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.math.BigDecimal> r9) {
        /*
            r6 = this;
            boolean r0 = r9 instanceof com.bitcoin.mwallet.core.repositories.SlpRepository$getTokenAmount$1
            if (r0 == 0) goto L_0x0014
            r0 = r9
            com.bitcoin.mwallet.core.repositories.SlpRepository$getTokenAmount$1 r0 = (com.bitcoin.mwallet.core.repositories.SlpRepository$getTokenAmount$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L_0x0019
        L_0x0014:
            com.bitcoin.mwallet.core.repositories.SlpRepository$getTokenAmount$1 r0 = new com.bitcoin.mwallet.core.repositories.SlpRepository$getTokenAmount$1
            r0.<init>(r6, r9)
        L_0x0019:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x0059
            if (r2 == r4) goto L_0x0048
            if (r2 != r3) goto L_0x0040
            java.lang.Object r7 = r0.L$3
            com.bitcoin.mwallet.core.models.slp.Slp r7 = (com.bitcoin.mwallet.core.models.slp.Slp) r7
            java.lang.Object r8 = r0.L$2
            com.bitcoin.mwallet.core.models.slp.SlpId r8 = (com.bitcoin.mwallet.core.models.slp.SlpId) r8
            java.lang.Object r8 = r0.L$1
            com.bitcoin.mwallet.core.models.wallet.WalletId r8 = (com.bitcoin.mwallet.core.models.wallet.WalletId) r8
            java.lang.Object r8 = r0.L$0
            com.bitcoin.mwallet.core.repositories.SlpRepository r8 = (com.bitcoin.mwallet.core.repositories.SlpRepository) r8
            kotlin.ResultKt.throwOnFailure(r9)
            r5 = r9
            r9 = r7
            r7 = r5
            goto L_0x0081
        L_0x0040:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L_0x0048:
            java.lang.Object r7 = r0.L$2
            r8 = r7
            com.bitcoin.mwallet.core.models.slp.SlpId r8 = (com.bitcoin.mwallet.core.models.slp.SlpId) r8
            java.lang.Object r7 = r0.L$1
            com.bitcoin.mwallet.core.models.wallet.WalletId r7 = (com.bitcoin.mwallet.core.models.wallet.WalletId) r7
            java.lang.Object r2 = r0.L$0
            com.bitcoin.mwallet.core.repositories.SlpRepository r2 = (com.bitcoin.mwallet.core.repositories.SlpRepository) r2
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x006c
        L_0x0059:
            kotlin.ResultKt.throwOnFailure(r9)
            r0.L$0 = r6
            r0.L$1 = r7
            r0.L$2 = r8
            r0.label = r4
            java.lang.Object r9 = r6.getToken(r7, r8, r0)
            if (r9 != r1) goto L_0x006b
            return r1
        L_0x006b:
            r2 = r6
        L_0x006c:
            com.bitcoin.mwallet.core.models.slp.Slp r9 = (com.bitcoin.mwallet.core.models.slp.Slp) r9
            com.bitcoin.mwallet.core.dao.SlpUtxoDao r4 = r2.slpUtxoDao
            r0.L$0 = r2
            r0.L$1 = r7
            r0.L$2 = r8
            r0.L$3 = r9
            r0.label = r3
            java.lang.Object r7 = r4.getWalletTokenAmount(r7, r8, r0)
            if (r7 != r1) goto L_0x0081
            return r1
        L_0x0081:
            java.lang.Number r7 = (java.lang.Number) r7
            long r7 = r7.longValue()
            if (r9 == 0) goto L_0x0099
            java.math.BigDecimal r7 = java.math.BigDecimal.valueOf(r7)
            java.lang.String r8 = "BigDecimal.valueOf(this)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r7, r8)
            java.math.BigDecimal r7 = r9.toReadableAmount(r7)
            if (r7 == 0) goto L_0x0099
            goto L_0x00a0
        L_0x0099:
            java.math.BigDecimal r7 = java.math.BigDecimal.ZERO
            java.lang.String r8 = "BigDecimal.ZERO"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r7, r8)
        L_0x00a0:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.core.repositories.SlpRepository.getTokenAmount(com.bitcoin.mwallet.core.models.wallet.WalletId, com.bitcoin.mwallet.core.models.slp.SlpId, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Nullable
    public final Object getTokenInfo(@NotNull SlpId slpId, @NotNull Continuation<? super Slp> continuation) {
        return this.slpDao.getTokenInfo(slpId, continuation);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0089, code lost:
        if (r7 != null) goto L_0x0093;
     */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0050  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0073 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x007c  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object getUserTokenAmount(@org.jetbrains.annotations.NotNull com.bitcoin.mwallet.core.models.slp.SlpId r7, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.math.BigDecimal> r8) {
        /*
            r6 = this;
            boolean r0 = r8 instanceof com.bitcoin.mwallet.core.repositories.SlpRepository$getUserTokenAmount$1
            if (r0 == 0) goto L_0x0014
            r0 = r8
            com.bitcoin.mwallet.core.repositories.SlpRepository$getUserTokenAmount$1 r0 = (com.bitcoin.mwallet.core.repositories.SlpRepository$getUserTokenAmount$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L_0x0019
        L_0x0014:
            com.bitcoin.mwallet.core.repositories.SlpRepository$getUserTokenAmount$1 r0 = new com.bitcoin.mwallet.core.repositories.SlpRepository$getUserTokenAmount$1
            r0.<init>(r6, r8)
        L_0x0019:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x0050
            if (r2 == r4) goto L_0x0044
            if (r2 != r3) goto L_0x003c
            java.lang.Object r7 = r0.L$2
            com.bitcoin.mwallet.core.models.slp.Slp r7 = (com.bitcoin.mwallet.core.models.slp.Slp) r7
            java.lang.Object r1 = r0.L$1
            com.bitcoin.mwallet.core.models.slp.SlpId r1 = (com.bitcoin.mwallet.core.models.slp.SlpId) r1
            java.lang.Object r0 = r0.L$0
            com.bitcoin.mwallet.core.repositories.SlpRepository r0 = (com.bitcoin.mwallet.core.repositories.SlpRepository) r0
            kotlin.ResultKt.throwOnFailure(r8)
            r5 = r8
            r8 = r7
            r7 = r5
            goto L_0x0074
        L_0x003c:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L_0x0044:
            java.lang.Object r7 = r0.L$1
            com.bitcoin.mwallet.core.models.slp.SlpId r7 = (com.bitcoin.mwallet.core.models.slp.SlpId) r7
            java.lang.Object r2 = r0.L$0
            com.bitcoin.mwallet.core.repositories.SlpRepository r2 = (com.bitcoin.mwallet.core.repositories.SlpRepository) r2
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x0061
        L_0x0050:
            kotlin.ResultKt.throwOnFailure(r8)
            r0.L$0 = r6
            r0.L$1 = r7
            r0.label = r4
            java.lang.Object r8 = r6.getTokenInfo(r7, r0)
            if (r8 != r1) goto L_0x0060
            return r1
        L_0x0060:
            r2 = r6
        L_0x0061:
            com.bitcoin.mwallet.core.models.slp.Slp r8 = (com.bitcoin.mwallet.core.models.slp.Slp) r8
            com.bitcoin.mwallet.core.dao.SlpUtxoDao r4 = r2.slpUtxoDao
            r0.L$0 = r2
            r0.L$1 = r7
            r0.L$2 = r8
            r0.label = r3
            java.lang.Object r7 = r4.getAllAmountForToken(r7, r0)
            if (r7 != r1) goto L_0x0074
            return r1
        L_0x0074:
            java.lang.Number r7 = (java.lang.Number) r7
            long r0 = r7.longValue()
            if (r8 == 0) goto L_0x008c
            java.math.BigDecimal r7 = java.math.BigDecimal.valueOf(r0)
            java.lang.String r0 = "BigDecimal.valueOf(this)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r7, r0)
            java.math.BigDecimal r7 = r8.toReadableAmount(r7)
            if (r7 == 0) goto L_0x008c
            goto L_0x0093
        L_0x008c:
            java.math.BigDecimal r7 = java.math.BigDecimal.ZERO
            java.lang.String r8 = "BigDecimal.ZERO"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r7, r8)
        L_0x0093:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.core.repositories.SlpRepository.getUserTokenAmount(com.bitcoin.mwallet.core.models.slp.SlpId, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x004d  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0068  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x007e  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object refreshTokens(@org.jetbrains.annotations.NotNull com.bitcoin.mwallet.core.models.wallet.C1261Wallet r6, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof com.bitcoin.mwallet.core.repositories.SlpRepository$refreshTokens$1
            if (r0 == 0) goto L_0x0014
            r0 = r7
            com.bitcoin.mwallet.core.repositories.SlpRepository$refreshTokens$1 r0 = (com.bitcoin.mwallet.core.repositories.SlpRepository$refreshTokens$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L_0x0019
        L_0x0014:
            com.bitcoin.mwallet.core.repositories.SlpRepository$refreshTokens$1 r0 = new com.bitcoin.mwallet.core.repositories.SlpRepository$refreshTokens$1
            r0.<init>(r5, r7)
        L_0x0019:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x004d
            if (r2 == r4) goto L_0x0041
            if (r2 != r3) goto L_0x0039
            java.lang.Object r6 = r0.L$2
            com.bitcoin.mwallet.core.services.tx.GetUtxosResponse r6 = (com.bitcoin.mwallet.core.services.p010tx.GetUtxosResponse) r6
            java.lang.Object r6 = r0.L$1
            com.bitcoin.mwallet.core.models.wallet.Wallet r6 = (com.bitcoin.mwallet.core.models.wallet.C1261Wallet) r6
            java.lang.Object r6 = r0.L$0
            com.bitcoin.mwallet.core.repositories.SlpRepository r6 = (com.bitcoin.mwallet.core.repositories.SlpRepository) r6
            kotlin.ResultKt.throwOnFailure(r7)
            goto L_0x007d
        L_0x0039:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x0041:
            java.lang.Object r6 = r0.L$1
            com.bitcoin.mwallet.core.models.wallet.Wallet r6 = (com.bitcoin.mwallet.core.models.wallet.C1261Wallet) r6
            java.lang.Object r2 = r0.L$0
            com.bitcoin.mwallet.core.repositories.SlpRepository r2 = (com.bitcoin.mwallet.core.repositories.SlpRepository) r2
            kotlin.ResultKt.throwOnFailure(r7)
            goto L_0x0060
        L_0x004d:
            kotlin.ResultKt.throwOnFailure(r7)
            com.bitcoin.mwallet.core.services.tx.TxService r7 = r5.txServiceGrpc
            r0.L$0 = r5
            r0.L$1 = r6
            r0.label = r4
            java.lang.Object r7 = r7.getUtxos(r6, r0)
            if (r7 != r1) goto L_0x005f
            return r1
        L_0x005f:
            r2 = r5
        L_0x0060:
            com.bitcoin.mwallet.core.services.tx.GetUtxosResponse r7 = (com.bitcoin.mwallet.core.services.p010tx.GetUtxosResponse) r7
            java.util.List r4 = r7.getSlpTokenInfo()
            if (r4 == 0) goto L_0x007e
            java.util.List r4 = r7.getSlpTokenInfo()
            java.util.Collection r4 = (java.util.Collection) r4
            r0.L$0 = r2
            r0.L$1 = r6
            r0.L$2 = r7
            r0.label = r3
            java.lang.Object r7 = r2.upsert(r4, r0)
            if (r7 != r1) goto L_0x007d
            return r1
        L_0x007d:
            return r7
        L_0x007e:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.core.repositories.SlpRepository.refreshTokens(com.bitcoin.mwallet.core.models.wallet.Wallet, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Nullable
    public final Object upsert(@NotNull Collection<Slp> collection, @NotNull Continuation<? super Unit> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new SlpRepository$upsert$2(this, collection, null), continuation);
    }
}
