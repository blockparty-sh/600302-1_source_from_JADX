package com.bitcoin.mwallet.core.dao;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0007H&J\b\u0010\b\u001a\u00020\tH&J\b\u0010\n\u001a\u00020\u000bH&J\b\u0010\f\u001a\u00020\rH&J\b\u0010\u000e\u001a\u00020\u000fH&J\b\u0010\u0010\u001a\u00020\u0011H&J\b\u0010\u0012\u001a\u00020\u0013H&J\b\u0010\u0014\u001a\u00020\u0015H&J\b\u0010\u0016\u001a\u00020\u0017H&J\b\u0010\u0018\u001a\u00020\u0019H&J\b\u0010\u001a\u001a\u00020\u001bH&J\b\u0010\u001c\u001a\u00020\u001dH&J\b\u0010\u001e\u001a\u00020\u001fH&¨\u0006 "}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/dao/WalletDaos;", "", "contactDao", "Lcom/bitcoin/mwallet/core/dao/ContactDao;", "credentialDao", "Lcom/bitcoin/mwallet/core/dao/CredentialDao;", "merchantDao", "Lcom/bitcoin/mwallet/core/dao/MerchantDao;", "networkFeeDao", "Lcom/bitcoin/mwallet/core/dao/NetworkFeeDao;", "rateDao", "Lcom/bitcoin/mwallet/core/dao/ExchangeRateDao;", "slpDao", "Lcom/bitcoin/mwallet/core/dao/SlpDao;", "slpUtxoDao", "Lcom/bitcoin/mwallet/core/dao/SlpUtxoDao;", "spentUtxoDao", "Lcom/bitcoin/mwallet/core/dao/SpentUtxoDao;", "transactionHistoryDao", "Lcom/bitcoin/mwallet/core/dao/TransactionHistoryDao;", "userDao", "Lcom/bitcoin/mwallet/core/dao/UserDao;", "utxoDao", "Lcom/bitcoin/mwallet/core/dao/UtxoDao;", "verifiedTokenDao", "Lcom/bitcoin/mwallet/core/dao/VerifiedTokenDao;", "walletAddressInfoDao", "Lcom/bitcoin/mwallet/core/dao/WalletAddressInfoDao;", "walletDao", "Lcom/bitcoin/mwallet/core/dao/WalletDao;", "zionNameDao", "Lcom/bitcoin/mwallet/core/dao/ZionNameDao;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: WalletDaos.kt */
public interface WalletDaos {
    @NotNull
    ContactDao contactDao();

    @NotNull
    CredentialDao credentialDao();

    @NotNull
    MerchantDao merchantDao();

    @NotNull
    NetworkFeeDao networkFeeDao();

    @NotNull
    ExchangeRateDao rateDao();

    @NotNull
    SlpDao slpDao();

    @NotNull
    SlpUtxoDao slpUtxoDao();

    @NotNull
    SpentUtxoDao spentUtxoDao();

    @NotNull
    TransactionHistoryDao transactionHistoryDao();

    @NotNull
    UserDao userDao();

    @NotNull
    UtxoDao utxoDao();

    @NotNull
    VerifiedTokenDao verifiedTokenDao();

    @NotNull
    WalletAddressInfoDao walletAddressInfoDao();

    @NotNull
    WalletDao walletDao();

    @NotNull
    ZionNameDao zionNameDao();
}
