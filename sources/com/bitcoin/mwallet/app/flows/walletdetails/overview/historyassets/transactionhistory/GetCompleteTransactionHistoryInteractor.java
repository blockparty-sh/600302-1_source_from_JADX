package com.bitcoin.mwallet.app.flows.walletdetails.overview.historyassets.transactionhistory;

import androidx.lifecycle.LiveData;
import com.bitcoin.mwallet.core.models.p009tx.history.HistoricTransaction;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import com.bitcoin.mwallet.core.repositories.TransactionHistoryRepository;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001a\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u00062\u0006\u0010\t\u001a\u00020\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/walletdetails/overview/historyassets/transactionhistory/GetCompleteTransactionHistoryInteractor;", "", "txHistoryRepository", "Lcom/bitcoin/mwallet/core/repositories/TransactionHistoryRepository;", "(Lcom/bitcoin/mwallet/core/repositories/TransactionHistoryRepository;)V", "getCompleteTransactionHistory", "Landroidx/lifecycle/LiveData;", "", "Lcom/bitcoin/mwallet/core/models/tx/history/HistoricTransaction;", "walletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: GetCompleteTransactionHistoryInteractor.kt */
public final class GetCompleteTransactionHistoryInteractor {
    private final TransactionHistoryRepository txHistoryRepository;

    public GetCompleteTransactionHistoryInteractor(@NotNull TransactionHistoryRepository transactionHistoryRepository) {
        Intrinsics.checkParameterIsNotNull(transactionHistoryRepository, "txHistoryRepository");
        this.txHistoryRepository = transactionHistoryRepository;
    }

    @NotNull
    public final LiveData<List<HistoricTransaction>> getCompleteTransactionHistory(@NotNull WalletId walletId) {
        Intrinsics.checkParameterIsNotNull(walletId, "walletId");
        return this.txHistoryRepository.getTransactions(walletId);
    }
}
