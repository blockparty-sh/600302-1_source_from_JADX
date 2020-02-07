package com.bitcoin.mwallet.core.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import com.bitcoin.bitcoink.p008tx.TxId;
import com.bitcoin.mwallet.core.models.p009tx.TxAction;
import com.bitcoin.mwallet.core.models.p009tx.history.HistoricTransaction;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Dao
@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\bg\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H'J\u001e\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u0006H'J\u001c\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\f0\b2\u0006\u0010\u0005\u001a\u00020\u0006H'J\u0013\u0010\r\u001a\u0004\u0018\u00010\u0002H§@ø\u0001\u0000¢\u0006\u0002\u0010\u000eJ\u001c\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\f0\b2\u0006\u0010\u0010\u001a\u00020\u0011H'J#\u0010\u0012\u001a\u0004\u0018\u00010\u00022\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\u0014H§@ø\u0001\u0000¢\u0006\u0002\u0010\u0015J \u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0005\u001a\u00020\u0006H'\u0002\u0004\n\u0002\b\u0019¨\u0006\u001a"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/dao/TransactionHistoryDao;", "Lcom/bitcoin/mwallet/core/dao/DaoBase;", "Lcom/bitcoin/mwallet/core/models/tx/history/HistoricTransaction;", "deleteAll", "", "walletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "selectById", "Landroidx/lifecycle/LiveData;", "id", "Lcom/bitcoin/bitcoink/tx/TxId;", "selectByWalletId", "", "selectLastConfirmedTransaction", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "selectLatest", "limit", "", "selectTransactionByIdAndAction", "txAction", "Lcom/bitcoin/mwallet/core/models/tx/TxAction;", "(Lcom/bitcoin/bitcoink/tx/TxId;Lcom/bitcoin/mwallet/core/models/tx/TxAction;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateNote", "txId", "note", "", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: TransactionHistoryDao.kt */
public interface TransactionHistoryDao extends DaoBase<HistoricTransaction> {
    @Query("DELETE FROM tx_history WHERE wallet_id = :walletId")
    void deleteAll(@NotNull WalletId walletId);

    @NotNull
    @Query("SELECT * FROM tx_history WHERE tx_id = :id and wallet_id = :walletId")
    LiveData<HistoricTransaction> selectById(@NotNull TxId txId, @NotNull WalletId walletId);

    @NotNull
    @Query("SELECT * FROM tx_history WHERE wallet_id = :walletId")
    LiveData<List<HistoricTransaction>> selectByWalletId(@NotNull WalletId walletId);

    @Nullable
    @Query("SELECT * FROM tx_history where confirmations > 0 ORDER BY timestamp DESC LIMIT 1")
    Object selectLastConfirmedTransaction(@NotNull Continuation<? super HistoricTransaction> continuation);

    @NotNull
    @Query("SELECT * FROM tx_history ORDER BY timestamp DESC LIMIT :limit")
    LiveData<List<HistoricTransaction>> selectLatest(int i);

    @Nullable
    @Query("SELECT * FROM tx_history WHERE tx_id = :id AND `action` = :txAction")
    Object selectTransactionByIdAndAction(@NotNull TxId txId, @NotNull TxAction txAction, @NotNull Continuation<? super HistoricTransaction> continuation);

    @Query("UPDATE tx_history SET note = :note WHERE tx_id = :txId AND wallet_id = :walletId")
    void updateNote(@NotNull TxId txId, @NotNull String str, @NotNull WalletId walletId);
}
