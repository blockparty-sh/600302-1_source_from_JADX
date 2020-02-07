package com.bitcoin.mwallet.app.flows.walletdetails.txdetails;

import com.bitcoin.mwallet.core.models.p009tx.history.HistoricTransaction;
import com.bitcoin.mwallet.core.models.wallet.C1261Wallet;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\u0010\u0000\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00012\b\u0010\u0004\u001a\u0004\u0018\u00010\u00022\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0006H\n¢\u0006\u0002\b\u0007"}, mo37405d2 = {"<anonymous>", "Lkotlin/Pair;", "Lcom/bitcoin/mwallet/core/models/tx/history/HistoricTransaction;", "Lcom/bitcoin/mwallet/core/models/wallet/Wallet;", "tx", "wallets", "", "invoke"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: TxDetailsPresenter.kt */
final class TxDetailsPresenter$getTransactionDetails$1 extends Lambda implements Function2<HistoricTransaction, List<? extends C1261Wallet>, Pair<? extends HistoricTransaction, ? extends C1261Wallet>> {
    final /* synthetic */ TxDetailsPresenter this$0;

    TxDetailsPresenter$getTransactionDetails$1(TxDetailsPresenter txDetailsPresenter) {
        this.this$0 = txDetailsPresenter;
        super(2);
    }

    @Nullable
    public final Pair<HistoricTransaction, C1261Wallet> invoke(@Nullable HistoricTransaction historicTransaction, @Nullable List<C1261Wallet> list) {
        Object obj = null;
        if (historicTransaction == null || list == null) {
            return null;
        }
        TxDetailsPresenter txDetailsPresenter = this.this$0;
        Iterator it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            if (Intrinsics.areEqual((Object) historicTransaction.getWalletId(), (Object) ((C1261Wallet) next).getId())) {
                obj = next;
                break;
            }
        }
        if (obj == null) {
            Intrinsics.throwNpe();
        }
        txDetailsPresenter.setWallet((C1261Wallet) obj);
        return new Pair<>(historicTransaction, this.this$0.getWallet());
    }
}
