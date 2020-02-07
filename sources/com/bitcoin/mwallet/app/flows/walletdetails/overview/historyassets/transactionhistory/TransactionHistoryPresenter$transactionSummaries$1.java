package com.bitcoin.mwallet.app.flows.walletdetails.overview.historyassets.transactionhistory;

import com.bitcoin.mwallet.core.models.Coin;
import com.bitcoin.mwallet.core.models.exchangerate.FiatExchangeRate;
import com.bitcoin.mwallet.core.models.p009tx.history.HistoricTransaction;
import com.bitcoin.mwallet.core.models.slp.Slp;
import com.bitcoin.mwallet.core.views.transaction.TransactionSummaryView;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000.\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00012\b\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00012\u0014\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0018\u00010\b2\u000e\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u0001H\n¢\u0006\u0004\b\r\u0010\u000e"}, mo37405d2 = {"<anonymous>", "", "Lcom/bitcoin/mwallet/core/views/transaction/TransactionSummaryView;", "<anonymous parameter 0>", "", "transactions", "Lcom/bitcoin/mwallet/core/models/tx/history/HistoricTransaction;", "rates", "", "Lcom/bitcoin/mwallet/core/models/Coin;", "Lcom/bitcoin/mwallet/core/models/exchangerate/FiatExchangeRate;", "tokens", "Lcom/bitcoin/mwallet/core/models/slp/Slp;", "invoke", "(Ljava/lang/Boolean;Ljava/util/List;Ljava/util/Map;Ljava/util/List;)Ljava/util/List;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: TransactionHistoryPresenter.kt */
final class TransactionHistoryPresenter$transactionSummaries$1 extends Lambda implements Function4<Boolean, List<? extends HistoricTransaction>, Map<Coin, ? extends FiatExchangeRate>, List<? extends Slp>, List<? extends TransactionSummaryView>> {
    final /* synthetic */ TransactionHistoryPresenter this$0;

    TransactionHistoryPresenter$transactionSummaries$1(TransactionHistoryPresenter transactionHistoryPresenter) {
        this.this$0 = transactionHistoryPresenter;
        super(4);
    }

    @Nullable
    public final List<TransactionSummaryView> invoke(@Nullable Boolean bool, @Nullable List<HistoricTransaction> list, @Nullable Map<Coin, FiatExchangeRate> map, @Nullable List<Slp> list2) {
        if (map == null || list == null || list2 == null) {
            return null;
        }
        return TransactionSummaryView.Companion.transactionSummaries(map, CollectionsKt.sortedWith(list, new C1113xc3dbe2e6()), this.this$0.context, list2);
    }
}
