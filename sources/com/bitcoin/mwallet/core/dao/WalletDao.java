package com.bitcoin.mwallet.core.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import com.bitcoin.bitcoink.ExtendedPublicKey;
import com.bitcoin.mwallet.core.models.Coin;
import com.bitcoin.mwallet.core.models.credential.CredentialId;
import com.bitcoin.mwallet.core.models.wallet.C1261Wallet;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import com.bitcoin.mwallet.core.models.wallet.WalletInfoSatoshis;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Dao
@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bg\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0014\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u00050\u0004H'J\u0019\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH§@ø\u0001\u0000¢\u0006\u0002\u0010\nJ!\u0010\u000b\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\rH§@ø\u0001\u0000¢\u0006\u0002\u0010\u000eJ\u001b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\b\u001a\u00020\tH§@ø\u0001\u0000¢\u0006\u0002\u0010\nJ\u0019\u0010\u0011\u001a\u00020\u00122\u0006\u0010\b\u001a\u00020\tH§@ø\u0001\u0000¢\u0006\u0002\u0010\nJ\u0010\u0010\u0013\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\tH'J\u0010\u0010\u0014\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\tH'J\u0017\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\t0\u0005H§@ø\u0001\u0000¢\u0006\u0002\u0010\u0017J\u001f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\t0\u00052\u0006\u0010\u0018\u001a\u00020\u0010H§@ø\u0001\u0000¢\u0006\u0002\u0010\u0019J\u0017\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\t0\u0005H§@ø\u0001\u0000¢\u0006\u0002\u0010\u0017J\u001f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\t0\u00052\u0006\u0010\u0018\u001a\u00020\u0010H§@ø\u0001\u0000¢\u0006\u0002\u0010\u0019J\u0017\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00020\u0005H§@ø\u0001\u0000¢\u0006\u0002\u0010\u0017J\u0016\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00020\u00052\u0006\u0010\b\u001a\u00020\tH'J!\u0010\u001d\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u001e\u001a\u00020\rH§@ø\u0001\u0000¢\u0006\u0002\u0010\u000eJ)\u0010\u001d\u001a\u00020\u00072\u0006\u0010\u001f\u001a\u00020\t2\u0006\u0010 \u001a\u00020\r2\u0006\u0010!\u001a\u00020\rH§@ø\u0001\u0000¢\u0006\u0002\u0010\"J\u001b\u0010#\u001a\u0004\u0018\u00010$2\u0006\u0010\b\u001a\u00020\tH§@ø\u0001\u0000¢\u0006\u0002\u0010\nJ\u001f\u0010%\u001a\b\u0012\u0004\u0012\u00020\t0\u00052\u0006\u0010\u0018\u001a\u00020\u0010H§@ø\u0001\u0000¢\u0006\u0002\u0010\u0019J\u0014\u0010&\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020'0\u00050\u0004H'J\u001c\u0010&\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020'0\u00050\u00042\u0006\u0010\u0018\u001a\u00020\u0010H'J\u0016\u0010&\u001a\b\u0012\u0004\u0012\u00020'0\u00042\u0006\u0010\b\u001a\u00020\tH'J\u001f\u0010(\u001a\b\u0012\u0004\u0012\u00020\t0\u00052\u0006\u0010\u0018\u001a\u00020\u0010H§@ø\u0001\u0000¢\u0006\u0002\u0010\u0019J\u0016\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00020\u00042\u0006\u0010\b\u001a\u00020\tH'J\u0016\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00020\u00042\u0006\u0010\u0015\u001a\u00020\tH'\u0002\u0004\n\u0002\b\u0019¨\u0006+"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/dao/WalletDao;", "Lcom/bitcoin/mwallet/core/dao/DaoBase;", "Lcom/bitcoin/mwallet/core/models/wallet/Wallet;", "allWallets", "Landroidx/lifecycle/LiveData;", "", "deleteById", "", "walletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "(Lcom/bitcoin/mwallet/core/models/wallet/WalletId;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteByIdAndCredentialId", "credentialId", "", "(Lcom/bitcoin/mwallet/core/models/wallet/WalletId;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCoin", "Lcom/bitcoin/mwallet/core/models/Coin;", "getPublicKey", "Lcom/bitcoin/bitcoink/ExtendedPublicKey;", "getWalletById", "getWalletByPk", "walletPk", "getWalletIds", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "coin", "(Lcom/bitcoin/mwallet/core/models/Coin;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getWalletPks", "getWallets", "getWalletsById", "renameWallet", "walletName", "id", "oldWalletName", "newWalletName", "(Lcom/bitcoin/mwallet/core/models/wallet/WalletId;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "selectCredentialId", "Lcom/bitcoin/mwallet/core/models/credential/CredentialId;", "selectWalletIdsByCoin", "selectWalletInfoSatoshis", "Lcom/bitcoin/mwallet/core/models/wallet/WalletInfoSatoshis;", "selectWalletPkByCoin", "walletById", "walletByPk", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: WalletDao.kt */
public interface WalletDao extends DaoBase<C1261Wallet> {
    @NotNull
    @Query("SELECT * FROM wallet")
    LiveData<List<C1261Wallet>> allWallets();

    @Nullable
    @Query("DELETE FROM wallet WHERE id = :walletId")
    Object deleteById(@NotNull WalletId walletId, @NotNull Continuation<? super Unit> continuation);

    @Nullable
    @Query("DELETE FROM wallet WHERE id =:walletId AND credential_id =:credentialId")
    Object deleteByIdAndCredentialId(@NotNull WalletId walletId, @NotNull String str, @NotNull Continuation<? super Unit> continuation);

    @Nullable
    @Query("SELECT coin FROM wallet WHERE id = :walletId")
    Object getCoin(@NotNull WalletId walletId, @NotNull Continuation<? super Coin> continuation);

    @Nullable
    @Query("SELECT public_key FROM wallet WHERE id = :walletId")
    Object getPublicKey(@NotNull WalletId walletId, @NotNull Continuation<? super ExtendedPublicKey> continuation);

    @NotNull
    @Query("SELECT * FROM wallet WHERE id = :walletId")
    C1261Wallet getWalletById(@NotNull WalletId walletId);

    @NotNull
    @Query("SELECT * FROM wallet WHERE pri_key = :walletPk")
    C1261Wallet getWalletByPk(@NotNull WalletId walletId);

    @Nullable
    @Query("SELECT id FROM wallet where coin = :coin")
    Object getWalletIds(@NotNull Coin coin, @NotNull Continuation<? super List<WalletId>> continuation);

    @Nullable
    @Query("SELECT id FROM wallet")
    Object getWalletIds(@NotNull Continuation<? super List<WalletId>> continuation);

    @Nullable
    @Query("SELECT pri_key from wallet where coin =:coin")
    Object getWalletPks(@NotNull Coin coin, @NotNull Continuation<? super List<WalletId>> continuation);

    @Nullable
    @Query("SELECT pri_key FROM wallet")
    Object getWalletPks(@NotNull Continuation<? super List<WalletId>> continuation);

    @Nullable
    @Query("SELECT * FROM wallet")
    Object getWallets(@NotNull Continuation<? super List<C1261Wallet>> continuation);

    @NotNull
    @Query("SELECT * FROM wallet WHERE id = :walletId")
    List<C1261Wallet> getWalletsById(@NotNull WalletId walletId);

    @Nullable
    @Query("UPDATE wallet SET name = :newWalletName WHERE id = :id AND name =:oldWalletName")
    Object renameWallet(@NotNull WalletId walletId, @NotNull String str, @NotNull String str2, @NotNull Continuation<? super Unit> continuation);

    @Nullable
    @Query("UPDATE wallet SET name = :walletName WHERE id = :walletId")
    Object renameWallet(@NotNull WalletId walletId, @NotNull String str, @NotNull Continuation<? super Unit> continuation);

    @Nullable
    @Query("SELECT credential_id as id, credential_type as type FROM wallet WHERE id = :walletId")
    Object selectCredentialId(@NotNull WalletId walletId, @NotNull Continuation<? super CredentialId> continuation);

    @Nullable
    @Query("SELECT id FROM wallet WHERE coin = :coin")
    Object selectWalletIdsByCoin(@NotNull Coin coin, @NotNull Continuation<? super List<WalletId>> continuation);

    @NotNull
    @Query("SELECT wallet.credential_type as credentialType, wallet.id as walletId, wallet.name as name, wallet.coin as coin, wallet.copayers_num_copayers as numCopayers, SUM(utxo.satoshis) as satoshis FROM wallet LEFT JOIN utxo ON wallet.id = utxo.wallet_id GROUP BY wallet.pri_key")
    LiveData<List<WalletInfoSatoshis>> selectWalletInfoSatoshis();

    @NotNull
    @Query("SELECT wallet.credential_type as credentialType, wallet.id as walletId, wallet.name as name, wallet.coin as coin, wallet.copayers_num_copayers as numCopayers, SUM(utxo.satoshis) as satoshis FROM wallet LEFT JOIN utxo ON wallet.id = utxo.wallet_id WHERE wallet.coin = :coin GROUP BY wallet.pri_key")
    LiveData<List<WalletInfoSatoshis>> selectWalletInfoSatoshis(@NotNull Coin coin);

    @NotNull
    @Query("SELECT wallet.credential_type as credentialType, wallet.id as walletId, wallet.name as name, wallet.coin as coin, wallet.copayers_num_copayers as numCopayers, SUM(utxo.satoshis) as satoshis FROM wallet LEFT JOIN utxo ON wallet.id = utxo.wallet_id WHERE wallet.id = :walletId GROUP by wallet.pri_key")
    LiveData<WalletInfoSatoshis> selectWalletInfoSatoshis(@NotNull WalletId walletId);

    @Nullable
    @Query("SELECT pri_key from wallet WHERE coin =:coin")
    Object selectWalletPkByCoin(@NotNull Coin coin, @NotNull Continuation<? super List<WalletId>> continuation);

    @NotNull
    @Query("SELECT * FROM wallet WHERE id = :walletId")
    LiveData<C1261Wallet> walletById(@NotNull WalletId walletId);

    @NotNull
    @Query("SELECT * FROM wallet where pri_key = :walletPk")
    LiveData<C1261Wallet> walletByPk(@NotNull WalletId walletId);
}
