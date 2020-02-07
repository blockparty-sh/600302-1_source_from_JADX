package com.bitcoin.mwallet.core.dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;
import com.bitcoin.mwallet.core.models.address.AddressPurpose;
import com.bitcoin.mwallet.core.models.address.WalletAddressInfo;
import com.bitcoin.mwallet.core.models.address.WalletAddressInfo.PathY;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Dao
@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0007\b'\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0019\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H§@ø\u0001\u0000¢\u0006\u0002\u0010\bJ!\u0010\t\u001a\u00020\n2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\fH@ø\u0001\u0000¢\u0006\u0002\u0010\rJ#\u0010\u000e\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u0010H§@ø\u0001\u0000¢\u0006\u0002\u0010\u0011J!\u0010\u0012\u001a\u00020\n2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\fH@ø\u0001\u0000¢\u0006\u0002\u0010\rJ1\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u0010H§@ø\u0001\u0000¢\u0006\u0002\u0010\u0016\u0002\u0004\n\u0002\b\u0019¨\u0006\u0017"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/dao/WalletAddressInfoDao;", "Lcom/bitcoin/mwallet/core/dao/DaoBase;", "Lcom/bitcoin/mwallet/core/models/address/WalletAddressInfo;", "()V", "delete", "", "walletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "(Lcom/bitcoin/mwallet/core/models/wallet/WalletId;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getOrCreatePathY", "Lcom/bitcoin/mwallet/core/models/address/WalletAddressInfo$PathY;", "purpose", "Lcom/bitcoin/mwallet/core/models/address/AddressPurpose;", "(Lcom/bitcoin/mwallet/core/models/wallet/WalletId;Lcom/bitcoin/mwallet/core/models/address/AddressPurpose;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getPathY", "pathX", "", "(Lcom/bitcoin/mwallet/core/models/wallet/WalletId;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "incrementAndGetPathY", "updatePathY", "pathYNext", "pathYMax", "(Lcom/bitcoin/mwallet/core/models/wallet/WalletId;IIILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: WalletAddressInfoDao.kt */
public abstract class WalletAddressInfoDao implements DaoBase<WalletAddressInfo> {
    @Nullable
    @Query("DELETE FROM wallet_address_info WHERE wallet_id = :walletId")
    public abstract Object delete(@NotNull WalletId walletId, @NotNull Continuation<? super Unit> continuation);

    @Transaction
    @Nullable
    public Object getOrCreatePathY(@NotNull WalletId walletId, @NotNull AddressPurpose addressPurpose, @NotNull Continuation<? super PathY> continuation) {
        return getOrCreatePathY$suspendImpl(this, walletId, addressPurpose, continuation);
    }

    @Nullable
    @Query("SELECT path_y_next as next, path_y_max as max FROM wallet_address_info WHERE wallet_id = :walletId AND path_x = :pathX")
    public abstract Object getPathY(@NotNull WalletId walletId, int i, @NotNull Continuation<? super PathY> continuation);

    @Transaction
    @Nullable
    public Object incrementAndGetPathY(@NotNull WalletId walletId, @NotNull AddressPurpose addressPurpose, @NotNull Continuation<? super PathY> continuation) {
        return incrementAndGetPathY$suspendImpl(this, walletId, addressPurpose, continuation);
    }

    @Nullable
    @Query("UPDATE wallet_address_info SET path_y_next = :pathYNext, path_y_max = :pathYMax WHERE wallet_id = :walletId AND path_x = :pathX")
    public abstract Object updatePathY(@NotNull WalletId walletId, int i, int i2, int i3, @NotNull Continuation<? super Unit> continuation);

    /* JADX WARNING: Removed duplicated region for block: B:14:0x005b  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0075  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0099 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static /* synthetic */ java.lang.Object getOrCreatePathY$suspendImpl(com.bitcoin.mwallet.core.dao.WalletAddressInfoDao r6, com.bitcoin.mwallet.core.models.wallet.WalletId r7, com.bitcoin.mwallet.core.models.address.AddressPurpose r8, kotlin.coroutines.Continuation r9) {
        /*
            boolean r0 = r9 instanceof com.bitcoin.mwallet.core.dao.WalletAddressInfoDao$getOrCreatePathY$1
            if (r0 == 0) goto L_0x0014
            r0 = r9
            com.bitcoin.mwallet.core.dao.WalletAddressInfoDao$getOrCreatePathY$1 r0 = (com.bitcoin.mwallet.core.dao.WalletAddressInfoDao$getOrCreatePathY$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L_0x0019
        L_0x0014:
            com.bitcoin.mwallet.core.dao.WalletAddressInfoDao$getOrCreatePathY$1 r0 = new com.bitcoin.mwallet.core.dao.WalletAddressInfoDao$getOrCreatePathY$1
            r0.<init>(r6, r9)
        L_0x0019:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x005b
            if (r2 == r4) goto L_0x0049
            if (r2 != r3) goto L_0x0041
            java.lang.Object r6 = r0.L$4
            com.bitcoin.mwallet.core.models.address.WalletAddressInfo r6 = (com.bitcoin.mwallet.core.models.address.WalletAddressInfo) r6
            java.lang.Object r7 = r0.L$3
            com.bitcoin.mwallet.core.models.address.WalletAddressInfo$PathY r7 = (com.bitcoin.mwallet.core.models.address.WalletAddressInfo.PathY) r7
            java.lang.Object r7 = r0.L$2
            com.bitcoin.mwallet.core.models.address.AddressPurpose r7 = (com.bitcoin.mwallet.core.models.address.AddressPurpose) r7
            java.lang.Object r7 = r0.L$1
            com.bitcoin.mwallet.core.models.wallet.WalletId r7 = (com.bitcoin.mwallet.core.models.wallet.WalletId) r7
            java.lang.Object r7 = r0.L$0
            com.bitcoin.mwallet.core.dao.WalletAddressInfoDao r7 = (com.bitcoin.mwallet.core.dao.WalletAddressInfoDao) r7
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x0094
        L_0x0041:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x0049:
            java.lang.Object r6 = r0.L$2
            r8 = r6
            com.bitcoin.mwallet.core.models.address.AddressPurpose r8 = (com.bitcoin.mwallet.core.models.address.AddressPurpose) r8
            java.lang.Object r6 = r0.L$1
            r7 = r6
            com.bitcoin.mwallet.core.models.wallet.WalletId r7 = (com.bitcoin.mwallet.core.models.wallet.WalletId) r7
            java.lang.Object r6 = r0.L$0
            com.bitcoin.mwallet.core.dao.WalletAddressInfoDao r6 = (com.bitcoin.mwallet.core.dao.WalletAddressInfoDao) r6
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x0071
        L_0x005b:
            kotlin.ResultKt.throwOnFailure(r9)
            int r9 = r8.getPathX()
            r0.L$0 = r6
            r0.L$1 = r7
            r0.L$2 = r8
            r0.label = r4
            java.lang.Object r9 = r6.getPathY(r7, r9, r0)
            if (r9 != r1) goto L_0x0071
            return r1
        L_0x0071:
            com.bitcoin.mwallet.core.models.address.WalletAddressInfo$PathY r9 = (com.bitcoin.mwallet.core.models.address.WalletAddressInfo.PathY) r9
            if (r9 != 0) goto L_0x0099
            com.bitcoin.mwallet.core.models.address.WalletAddressInfo$Companion r2 = com.bitcoin.mwallet.core.models.address.WalletAddressInfo.Companion
            com.bitcoin.mwallet.core.models.address.WalletAddressInfo r2 = r2.m474default(r7, r8)
            com.bitcoin.mwallet.core.models.address.WalletAddressInfo[] r4 = new com.bitcoin.mwallet.core.models.address.WalletAddressInfo[r4]
            r5 = 0
            r4[r5] = r2
            r0.L$0 = r6
            r0.L$1 = r7
            r0.L$2 = r8
            r0.L$3 = r9
            r0.L$4 = r2
            r0.label = r3
            java.lang.Object r6 = r6.upsert(r4, r0)
            if (r6 != r1) goto L_0x0093
            return r1
        L_0x0093:
            r6 = r2
        L_0x0094:
            com.bitcoin.mwallet.core.models.address.WalletAddressInfo$PathY r6 = r6.getPathY()
            return r6
        L_0x0099:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.core.dao.WalletAddressInfoDao.getOrCreatePathY$suspendImpl(com.bitcoin.mwallet.core.dao.WalletAddressInfoDao, com.bitcoin.mwallet.core.models.wallet.WalletId, com.bitcoin.mwallet.core.models.address.AddressPurpose, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x007a  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0095  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00b8  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0027  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static /* synthetic */ java.lang.Object incrementAndGetPathY$suspendImpl(com.bitcoin.mwallet.core.dao.WalletAddressInfoDao r7, com.bitcoin.mwallet.core.models.wallet.WalletId r8, com.bitcoin.mwallet.core.models.address.AddressPurpose r9, kotlin.coroutines.Continuation r10) {
        /*
            boolean r0 = r10 instanceof com.bitcoin.mwallet.core.dao.WalletAddressInfoDao$incrementAndGetPathY$1
            if (r0 == 0) goto L_0x0014
            r0 = r10
            com.bitcoin.mwallet.core.dao.WalletAddressInfoDao$incrementAndGetPathY$1 r0 = (com.bitcoin.mwallet.core.dao.WalletAddressInfoDao$incrementAndGetPathY$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r10 = r0.label
            int r10 = r10 - r2
            r0.label = r10
            goto L_0x0019
        L_0x0014:
            com.bitcoin.mwallet.core.dao.WalletAddressInfoDao$incrementAndGetPathY$1 r0 = new com.bitcoin.mwallet.core.dao.WalletAddressInfoDao$incrementAndGetPathY$1
            r0.<init>(r7, r10)
        L_0x0019:
            r6 = r0
            java.lang.Object r10 = r6.result
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r6.label
            r2 = 3
            r3 = 2
            r4 = 1
            if (r1 == 0) goto L_0x007a
            if (r1 == r4) goto L_0x0068
            if (r1 == r3) goto L_0x0050
            if (r1 != r2) goto L_0x0048
            int r7 = r6.I$0
            java.lang.Object r7 = r6.L$4
            com.bitcoin.mwallet.core.models.address.WalletAddressInfo$PathY r7 = (com.bitcoin.mwallet.core.models.address.WalletAddressInfo.PathY) r7
            java.lang.Object r8 = r6.L$3
            com.bitcoin.mwallet.core.models.address.WalletAddressInfo$PathY r8 = (com.bitcoin.mwallet.core.models.address.WalletAddressInfo.PathY) r8
            java.lang.Object r8 = r6.L$2
            com.bitcoin.mwallet.core.models.address.AddressPurpose r8 = (com.bitcoin.mwallet.core.models.address.AddressPurpose) r8
            java.lang.Object r8 = r6.L$1
            com.bitcoin.mwallet.core.models.wallet.WalletId r8 = (com.bitcoin.mwallet.core.models.wallet.WalletId) r8
            java.lang.Object r8 = r6.L$0
            com.bitcoin.mwallet.core.dao.WalletAddressInfoDao r8 = (com.bitcoin.mwallet.core.dao.WalletAddressInfoDao) r8
            kotlin.ResultKt.throwOnFailure(r10)
            goto L_0x00e6
        L_0x0048:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L_0x0050:
            java.lang.Object r7 = r6.L$4
            com.bitcoin.mwallet.core.models.address.WalletAddressInfo r7 = (com.bitcoin.mwallet.core.models.address.WalletAddressInfo) r7
            java.lang.Object r8 = r6.L$3
            com.bitcoin.mwallet.core.models.address.WalletAddressInfo$PathY r8 = (com.bitcoin.mwallet.core.models.address.WalletAddressInfo.PathY) r8
            java.lang.Object r8 = r6.L$2
            com.bitcoin.mwallet.core.models.address.AddressPurpose r8 = (com.bitcoin.mwallet.core.models.address.AddressPurpose) r8
            java.lang.Object r8 = r6.L$1
            com.bitcoin.mwallet.core.models.wallet.WalletId r8 = (com.bitcoin.mwallet.core.models.wallet.WalletId) r8
            java.lang.Object r8 = r6.L$0
            com.bitcoin.mwallet.core.dao.WalletAddressInfoDao r8 = (com.bitcoin.mwallet.core.dao.WalletAddressInfoDao) r8
            kotlin.ResultKt.throwOnFailure(r10)
            goto L_0x00b3
        L_0x0068:
            java.lang.Object r7 = r6.L$2
            r9 = r7
            com.bitcoin.mwallet.core.models.address.AddressPurpose r9 = (com.bitcoin.mwallet.core.models.address.AddressPurpose) r9
            java.lang.Object r7 = r6.L$1
            r8 = r7
            com.bitcoin.mwallet.core.models.wallet.WalletId r8 = (com.bitcoin.mwallet.core.models.wallet.WalletId) r8
            java.lang.Object r7 = r6.L$0
            com.bitcoin.mwallet.core.dao.WalletAddressInfoDao r7 = (com.bitcoin.mwallet.core.dao.WalletAddressInfoDao) r7
            kotlin.ResultKt.throwOnFailure(r10)
            goto L_0x0090
        L_0x007a:
            kotlin.ResultKt.throwOnFailure(r10)
            int r10 = r9.getPathX()
            r6.L$0 = r7
            r6.L$1 = r8
            r6.L$2 = r9
            r6.label = r4
            java.lang.Object r10 = r7.getPathY(r8, r10, r6)
            if (r10 != r0) goto L_0x0090
            return r0
        L_0x0090:
            r1 = r7
            com.bitcoin.mwallet.core.models.address.WalletAddressInfo$PathY r10 = (com.bitcoin.mwallet.core.models.address.WalletAddressInfo.PathY) r10
            if (r10 != 0) goto L_0x00b8
            com.bitcoin.mwallet.core.models.address.WalletAddressInfo$Companion r7 = com.bitcoin.mwallet.core.models.address.WalletAddressInfo.Companion
            com.bitcoin.mwallet.core.models.address.WalletAddressInfo r7 = r7.m474default(r8, r9)
            com.bitcoin.mwallet.core.models.address.WalletAddressInfo[] r2 = new com.bitcoin.mwallet.core.models.address.WalletAddressInfo[r4]
            r4 = 0
            r2[r4] = r7
            r6.L$0 = r1
            r6.L$1 = r8
            r6.L$2 = r9
            r6.L$3 = r10
            r6.L$4 = r7
            r6.label = r3
            java.lang.Object r8 = r1.upsert(r2, r6)
            if (r8 != r0) goto L_0x00b3
            return r0
        L_0x00b3:
            com.bitcoin.mwallet.core.models.address.WalletAddressInfo$PathY r7 = r7.getPathY()
            return r7
        L_0x00b8:
            com.bitcoin.mwallet.core.models.address.WalletAddressInfo$PathY r7 = r10.increment()
            java.lang.Integer r3 = r7.getNext()
            if (r3 == 0) goto L_0x00e6
            java.lang.Number r3 = (java.lang.Number) r3
            int r4 = r3.intValue()
            int r3 = r9.getPathX()
            int r5 = r7.getMax()
            r6.L$0 = r1
            r6.L$1 = r8
            r6.L$2 = r9
            r6.L$3 = r10
            r6.L$4 = r7
            r6.I$0 = r4
            r6.label = r2
            r2 = r8
            java.lang.Object r8 = r1.updatePathY(r2, r3, r4, r5, r6)
            if (r8 != r0) goto L_0x00e6
            return r0
        L_0x00e6:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.core.dao.WalletAddressInfoDao.incrementAndGetPathY$suspendImpl(com.bitcoin.mwallet.core.dao.WalletAddressInfoDao, com.bitcoin.mwallet.core.models.wallet.WalletId, com.bitcoin.mwallet.core.models.address.AddressPurpose, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
