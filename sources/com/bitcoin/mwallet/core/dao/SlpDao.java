package com.bitcoin.mwallet.core.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import com.bitcoin.mwallet.core.models.slp.Slp;
import com.bitcoin.mwallet.core.models.slp.SlpId;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Dao
@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bg\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J!\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH§@ø\u0001\u0000¢\u0006\u0002\u0010\tJ\u001b\u0010\n\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0007\u001a\u00020\bH§@ø\u0001\u0000¢\u0006\u0002\u0010\u000bJ\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00020\rH§@ø\u0001\u0000¢\u0006\u0002\u0010\u000eJ\u001c\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\r0\u00102\u0006\u0010\u0005\u001a\u00020\u0006H'J\u001f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00020\r2\u0006\u0010\u0005\u001a\u00020\u0006H§@ø\u0001\u0000¢\u0006\u0002\u0010\u0012J\u001f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00060\r2\u0006\u0010\u0007\u001a\u00020\bH§@ø\u0001\u0000¢\u0006\u0002\u0010\u000bJ!\u0010\u0014\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH§@ø\u0001\u0000¢\u0006\u0002\u0010\t\u0002\u0004\n\u0002\b\u0019¨\u0006\u0015"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/dao/SlpDao;", "Lcom/bitcoin/mwallet/core/dao/DaoBase;", "Lcom/bitcoin/mwallet/core/models/slp/Slp;", "delete", "", "walletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "tokenId", "Lcom/bitcoin/mwallet/core/models/slp/SlpId;", "(Lcom/bitcoin/mwallet/core/models/wallet/WalletId;Lcom/bitcoin/mwallet/core/models/slp/SlpId;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTokenInfo", "(Lcom/bitcoin/mwallet/core/models/slp/SlpId;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "selectAllDistinctToken", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "selectAllFromWalletId", "Landroidx/lifecycle/LiveData;", "selectAllFromWalletIdNonLiveData", "(Lcom/bitcoin/mwallet/core/models/wallet/WalletId;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "selectAllWalletIdForToken", "selectFromWalletIdAndTokenId", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: SlpDao.kt */
public interface SlpDao extends DaoBase<Slp> {
    @Nullable
    @Query("DELETE FROM slp WHERE wallet_id = :walletId AND token_id = :tokenId")
    Object delete(@NotNull WalletId walletId, @NotNull SlpId slpId, @NotNull Continuation<? super Unit> continuation);

    @Nullable
    @Query("SELECT * from slp where token_id = :tokenId LIMIT 1")
    Object getTokenInfo(@NotNull SlpId slpId, @NotNull Continuation<? super Slp> continuation);

    @Nullable
    @Query("SELECT * From slp GROUP BY token_id")
    Object selectAllDistinctToken(@NotNull Continuation<? super List<Slp>> continuation);

    @NotNull
    @Query("SELECT * FROM slp WHERE wallet_id = :walletId")
    LiveData<List<Slp>> selectAllFromWalletId(@NotNull WalletId walletId);

    @Nullable
    @Query("SELECT * FROM slp WHERE wallet_id = :walletId")
    Object selectAllFromWalletIdNonLiveData(@NotNull WalletId walletId, @NotNull Continuation<? super List<Slp>> continuation);

    @Nullable
    @Query("SELECT wallet_id FROM slp WHERE token_id = :tokenId")
    Object selectAllWalletIdForToken(@NotNull SlpId slpId, @NotNull Continuation<? super List<WalletId>> continuation);

    @Nullable
    @Query("SELECT * FROM slp WHERE wallet_id = :walletId AND token_id = :tokenId")
    Object selectFromWalletIdAndTokenId(@NotNull WalletId walletId, @NotNull SlpId slpId, @NotNull Continuation<? super Slp> continuation);
}
