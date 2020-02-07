package com.bitcoin.mwallet.app.flows.walletdetails.overview.historyassets.transactionhistory;

import androidx.lifecycle.LiveData;
import com.bitcoin.mwallet.core.models.p009tx.history.HistoricTransaction;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001H\n¢\u0006\u0002\b\u0004"}, mo37405d2 = {"<anonymous>", "Landroidx/lifecycle/LiveData;", "", "Lcom/bitcoin/mwallet/core/models/tx/history/HistoricTransaction;", "invoke"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: TransactionHistoryPresenter.kt */
final class TransactionHistoryPresenter$transactions$2 extends Lambda implements Function0<LiveData<List<? extends HistoricTransaction>>> {
    final /* synthetic */ TransactionHistoryPresenter this$0;

    TransactionHistoryPresenter$transactions$2(TransactionHistoryPresenter transactionHistoryPresenter) {
        this.this$0 = transactionHistoryPresenter;
        super(0);
    }

    @NotNull
    public final LiveData<List<HistoricTransaction>> invoke() {
        return this.this$0.getCompleteTransactionHistoryInteractor.getCompleteTransactionHistory(this.this$0.getWalletId());
    }
}
