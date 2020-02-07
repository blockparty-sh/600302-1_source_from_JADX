package com.bitcoin.mwallet.core.dao;

import androidx.room.Dao;
import androidx.room.Query;
import com.bitcoin.bitcoink.p008tx.TxId;
import com.bitcoin.mwallet.core.models.merchant.Merchant;
import com.bitcoin.mwallet.core.models.slp.Slp;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Dao
@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bg\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0019\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H§@ø\u0001\u0000¢\u0006\u0002\u0010\u0007\u0002\u0004\n\u0002\b\u0019¨\u0006\b"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/dao/MerchantDao;", "Lcom/bitcoin/mwallet/core/dao/DaoBase;", "Lcom/bitcoin/mwallet/core/models/slp/Slp;", "selectFromTxId", "Lcom/bitcoin/mwallet/core/models/merchant/Merchant;", "txId", "Lcom/bitcoin/bitcoink/tx/TxId;", "(Lcom/bitcoin/bitcoink/tx/TxId;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: MerchantDao.kt */
public interface MerchantDao extends DaoBase<Slp> {
    @Nullable
    @Query("SELECT * FROM merchant WHERE tx_id = :txId")
    Object selectFromTxId(@NotNull TxId txId, @NotNull Continuation<? super Merchant> continuation);
}
