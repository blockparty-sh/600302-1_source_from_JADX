package com.bitcoin.mwallet.core.dao;

import androidx.room.Dao;
import androidx.room.Query;
import com.bitcoin.bitcoink.p008tx.TxId;
import com.bitcoin.mwallet.core.models.p009tx.SpentUtxo;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Dao
@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010 \n\u0000\bg\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J!\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH§@ø\u0001\u0000¢\u0006\u0002\u0010\tJ\u0011\u0010\n\u001a\u00020\u0004H§@ø\u0001\u0000¢\u0006\u0002\u0010\u000bJ\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00020\rH§@ø\u0001\u0000¢\u0006\u0002\u0010\u000b\u0002\u0004\n\u0002\b\u0019¨\u0006\u000e"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/dao/SpentUtxoDao;", "Lcom/bitcoin/mwallet/core/dao/DaoBase;", "Lcom/bitcoin/mwallet/core/models/tx/SpentUtxo;", "delete", "", "txId", "Lcom/bitcoin/bitcoink/tx/TxId;", "outputIndex", "", "(Lcom/bitcoin/bitcoink/tx/TxId;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteAll", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getSpentUtxos", "", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: SpentUtxoDao.kt */
public interface SpentUtxoDao extends DaoBase<SpentUtxo> {
    @Nullable
    @Query("DELETE FROM spent_utxo WHERE tx_id = :txId AND output_index = :outputIndex")
    Object delete(@NotNull TxId txId, int i, @NotNull Continuation<? super Unit> continuation);

    @Nullable
    @Query("DELETE FROM spent_utxo")
    Object deleteAll(@NotNull Continuation<? super Unit> continuation);

    @Nullable
    @Query("SELECT * FROM spent_utxo")
    Object getSpentUtxos(@NotNull Continuation<? super List<SpentUtxo>> continuation);
}
