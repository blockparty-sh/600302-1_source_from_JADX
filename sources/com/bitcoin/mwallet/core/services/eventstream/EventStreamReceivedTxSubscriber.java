package com.bitcoin.mwallet.core.services.eventstream;

import com.bitcoin.bitcoink.address.Address;
import com.bitcoin.mwallet.core.models.p009tx.history.HistoricTransaction;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0002\u0010\bJ\u000e\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0006R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/services/eventstream/EventStreamReceivedTxSubscriber;", "", "receiveAddress", "Lcom/bitcoin/bitcoink/address/Address;", "txConsumer", "Lkotlin/Function1;", "Lcom/bitcoin/mwallet/core/models/tx/history/HistoricTransaction;", "", "(Lcom/bitcoin/bitcoink/address/Address;Lkotlin/jvm/functions/Function1;)V", "legacyReceiveAddress", "", "onTransaction", "transaction", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: EventStreamReceivedTxSubscriber.kt */
public final class EventStreamReceivedTxSubscriber {
    private final String legacyReceiveAddress;
    private final Function1<HistoricTransaction, Unit> txConsumer;

    public EventStreamReceivedTxSubscriber(@NotNull Address address, @NotNull Function1<? super HistoricTransaction, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(address, "receiveAddress");
        Intrinsics.checkParameterIsNotNull(function1, "txConsumer");
        this.txConsumer = function1;
        this.legacyReceiveAddress = address.toBase58();
    }

    public final void onTransaction(@NotNull HistoricTransaction historicTransaction) {
        Intrinsics.checkParameterIsNotNull(historicTransaction, "transaction");
        if (Intrinsics.areEqual((Object) historicTransaction.getToAddress(), (Object) this.legacyReceiveAddress)) {
            this.txConsumer.invoke(historicTransaction);
        }
    }
}
