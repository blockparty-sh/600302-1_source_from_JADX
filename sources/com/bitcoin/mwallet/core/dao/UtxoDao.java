package com.bitcoin.mwallet.core.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;
import com.bitcoin.bitcoink.p008tx.TxId;
import com.bitcoin.mwallet.core.models.p009tx.utxo.Utxo;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Dao
@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J!\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH§@ø\u0001\u0000¢\u0006\u0002\u0010\tJ\u0019\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\fH§@ø\u0001\u0000¢\u0006\u0002\u0010\rJ\u001f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00020\u000f2\u0006\u0010\u000b\u001a\u00020\fH§@ø\u0001\u0000¢\u0006\u0002\u0010\rJ\u0019\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\fH§@ø\u0001\u0000¢\u0006\u0002\u0010\rJ-\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\f2\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00020\u0013\"\u00020\u0002H@ø\u0001\u0000¢\u0006\u0002\u0010\u0014J\u001c\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u000f0\u00162\u0006\u0010\u000b\u001a\u00020\fH'\u0002\u0004\n\u0002\b\u0019¨\u0006\u0017"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/dao/UtxoDao;", "Lcom/bitcoin/mwallet/core/dao/DaoBase;", "Lcom/bitcoin/mwallet/core/models/tx/utxo/Utxo;", "delete", "", "txId", "Lcom/bitcoin/bitcoink/tx/TxId;", "outputIndex", "", "(Lcom/bitcoin/bitcoink/tx/TxId;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteAll", "walletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "(Lcom/bitcoin/mwallet/core/models/wallet/WalletId;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getUtxos", "", "refresh", "replace", "values", "", "(Lcom/bitcoin/mwallet/core/models/wallet/WalletId;[Lcom/bitcoin/mwallet/core/models/tx/utxo/Utxo;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "utxos", "Landroidx/lifecycle/LiveData;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: UtxoDao.kt */
public interface UtxoDao extends DaoBase<Utxo> {

    @Metadata(mo37403bv = {1, 0, 3}, mo37406k = 3, mo37407mv = {1, 1, 15})
    /* compiled from: UtxoDao.kt */
    public static final class DefaultImpls {
        /* JADX WARNING: Removed duplicated region for block: B:14:0x0053  */
        /* JADX WARNING: Removed duplicated region for block: B:19:0x007a A[RETURN] */
        /* JADX WARNING: Removed duplicated region for block: B:20:0x007b A[PHI: r8 
          PHI: (r8v2 java.lang.Object) = (r8v6 java.lang.Object), (r8v1 java.lang.Object) binds: [B:18:0x0078, B:10:0x0029] A[DONT_GENERATE, DONT_INLINE], RETURN] */
        /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
        @androidx.room.Transaction
        @org.jetbrains.annotations.Nullable
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public static java.lang.Object replace(com.bitcoin.mwallet.core.dao.UtxoDao r5, @org.jetbrains.annotations.NotNull com.bitcoin.mwallet.core.models.wallet.WalletId r6, @org.jetbrains.annotations.NotNull com.bitcoin.mwallet.core.models.p009tx.utxo.Utxo[] r7, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
            /*
                boolean r0 = r8 instanceof com.bitcoin.mwallet.core.dao.UtxoDao$replace$1
                if (r0 == 0) goto L_0x0014
                r0 = r8
                com.bitcoin.mwallet.core.dao.UtxoDao$replace$1 r0 = (com.bitcoin.mwallet.core.dao.UtxoDao$replace$1) r0
                int r1 = r0.label
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r1 = r1 & r2
                if (r1 == 0) goto L_0x0014
                int r8 = r0.label
                int r8 = r8 - r2
                r0.label = r8
                goto L_0x0019
            L_0x0014:
                com.bitcoin.mwallet.core.dao.UtxoDao$replace$1 r0 = new com.bitcoin.mwallet.core.dao.UtxoDao$replace$1
                r0.<init>(r5, r8)
            L_0x0019:
                java.lang.Object r8 = r0.result
                java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r2 = r0.label
                r3 = 2
                r4 = 1
                if (r2 == 0) goto L_0x0053
                if (r2 == r4) goto L_0x0041
                if (r2 != r3) goto L_0x0039
                java.lang.Object r5 = r0.L$2
                com.bitcoin.mwallet.core.models.tx.utxo.Utxo[] r5 = (com.bitcoin.mwallet.core.models.p009tx.utxo.Utxo[]) r5
                java.lang.Object r5 = r0.L$1
                com.bitcoin.mwallet.core.models.wallet.WalletId r5 = (com.bitcoin.mwallet.core.models.wallet.WalletId) r5
                java.lang.Object r5 = r0.L$0
                com.bitcoin.mwallet.core.dao.UtxoDao r5 = (com.bitcoin.mwallet.core.dao.UtxoDao) r5
                kotlin.ResultKt.throwOnFailure(r8)
                goto L_0x007b
            L_0x0039:
                java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
                r5.<init>(r6)
                throw r5
            L_0x0041:
                java.lang.Object r5 = r0.L$2
                r7 = r5
                com.bitcoin.mwallet.core.models.tx.utxo.Utxo[] r7 = (com.bitcoin.mwallet.core.models.p009tx.utxo.Utxo[]) r7
                java.lang.Object r5 = r0.L$1
                r6 = r5
                com.bitcoin.mwallet.core.models.wallet.WalletId r6 = (com.bitcoin.mwallet.core.models.wallet.WalletId) r6
                java.lang.Object r5 = r0.L$0
                com.bitcoin.mwallet.core.dao.UtxoDao r5 = (com.bitcoin.mwallet.core.dao.UtxoDao) r5
                kotlin.ResultKt.throwOnFailure(r8)
                goto L_0x0065
            L_0x0053:
                kotlin.ResultKt.throwOnFailure(r8)
                r0.L$0 = r5
                r0.L$1 = r6
                r0.L$2 = r7
                r0.label = r4
                java.lang.Object r8 = r5.deleteAll(r6, r0)
                if (r8 != r1) goto L_0x0065
                return r1
            L_0x0065:
                int r8 = r7.length
                java.lang.Object[] r8 = java.util.Arrays.copyOf(r7, r8)
                com.bitcoin.mwallet.core.models.tx.utxo.Utxo[] r8 = (com.bitcoin.mwallet.core.models.p009tx.utxo.Utxo[]) r8
                r0.L$0 = r5
                r0.L$1 = r6
                r0.L$2 = r7
                r0.label = r3
                java.lang.Object r8 = r5.upsert(r8, r0)
                if (r8 != r1) goto L_0x007b
                return r1
            L_0x007b:
                return r8
            */
            throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.core.dao.UtxoDao.DefaultImpls.replace(com.bitcoin.mwallet.core.dao.UtxoDao, com.bitcoin.mwallet.core.models.wallet.WalletId, com.bitcoin.mwallet.core.models.tx.utxo.Utxo[], kotlin.coroutines.Continuation):java.lang.Object");
        }
    }

    @Nullable
    @Query("DELETE FROM utxo WHERE tx_id = :txId AND output_index = :outputIndex")
    Object delete(@NotNull TxId txId, int i, @NotNull Continuation<? super Unit> continuation);

    @Nullable
    @Query("DELETE FROM utxo where wallet_id = :walletId")
    Object deleteAll(@NotNull WalletId walletId, @NotNull Continuation<? super Unit> continuation);

    @Nullable
    @Query("SELECT * FROM utxo WHERE wallet_id = :walletId")
    Object getUtxos(@NotNull WalletId walletId, @NotNull Continuation<? super List<Utxo>> continuation);

    @Nullable
    @Query("DELETE FROM utxo where wallet_id = :walletId")
    Object refresh(@NotNull WalletId walletId, @NotNull Continuation<? super Unit> continuation);

    @Transaction
    @Nullable
    Object replace(@NotNull WalletId walletId, @NotNull Utxo[] utxoArr, @NotNull Continuation<? super Unit> continuation);

    @NotNull
    @Query("SELECT * FROM utxo WHERE wallet_id = :walletId")
    LiveData<List<Utxo>> utxos(@NotNull WalletId walletId);
}
