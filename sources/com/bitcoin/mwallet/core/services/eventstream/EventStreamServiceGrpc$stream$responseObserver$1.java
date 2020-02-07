package com.bitcoin.mwallet.core.services.eventstream;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import com.bitcoin.mwallet.StreamEvent;
import com.bitcoin.mwallet.StreamEvent.EventCase;
import com.bitcoin.mwallet.TxHistory;
import com.bitcoin.mwallet.WalletUtxos;
import com.bitcoin.mwallet.app.components.connectivitydialog.ConnectivityDialogView;
import com.bitcoin.mwallet.core.services.eventstream.EventStreamServiceGrpc.EventStreamSubscriber;
import com.bitcoin.mwallet.core.services.eventstream.EventStreamServiceGrpc.WhenMappings;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import p015io.grpc.Status;
import p015io.grpc.Status.Code;
import p015io.grpc.StatusRuntimeException;
import p015io.grpc.stub.StreamObserver;
import timber.log.Timber;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0012\u0010\b\u001a\u00020\u00042\b\u0010\t\u001a\u0004\u0018\u00010\u0002H\u0016¨\u0006\n"}, mo37405d2 = {"com/bitcoin/mwallet/core/services/eventstream/EventStreamServiceGrpc$stream$responseObserver$1", "Lio/grpc/stub/StreamObserver;", "Lcom/bitcoin/mwallet/StreamEvent;", "onCompleted", "", "onError", "t", "", "onNext", "event", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: EventStreamServiceGrpc.kt */
public final class EventStreamServiceGrpc$stream$responseObserver$1 implements StreamObserver<StreamEvent> {
    final /* synthetic */ EventStreamSubscriber $subscription;
    final /* synthetic */ Set $walletIds;
    final /* synthetic */ EventStreamServiceGrpc this$0;

    EventStreamServiceGrpc$stream$responseObserver$1(EventStreamServiceGrpc eventStreamServiceGrpc, EventStreamSubscriber eventStreamSubscriber, Set set) {
        this.this$0 = eventStreamServiceGrpc;
        this.$subscription = eventStreamSubscriber;
        this.$walletIds = set;
    }

    public void onNext(@Nullable StreamEvent streamEvent) {
        StringBuilder sb = new StringBuilder();
        sb.append("streamEvent=");
        sb.append(streamEvent);
        Timber.m426d(sb.toString(), new Object[0]);
        EventCase eventCase = streamEvent != null ? streamEvent.getEventCase() : null;
        if (eventCase != null) {
            int i = WhenMappings.$EnumSwitchMapping$0[eventCase.ordinal()];
            if (i == 1) {
                TxHistory txHistory = streamEvent.getTxHistory();
                if (txHistory != null) {
                    this.$subscription.onTxHistory(txHistory);
                }
            } else if (i == 2) {
                WalletUtxos utxos = streamEvent.getUtxos();
                if (utxos != null) {
                    this.$subscription.onWalletUtxos(utxos);
                }
            }
        }
    }

    public void onError(@NotNull Throwable th) {
        Intrinsics.checkParameterIsNotNull(th, "t");
        String str = "Stream  Error: ";
        if (th instanceof StatusRuntimeException) {
            Status status = ((StatusRuntimeException) th).getStatus();
            Intrinsics.checkExpressionValueIsNotNull(status, "t.status");
            if (status.getCode() == Code.CANCELLED) {
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                sb.append(th);
                Timber.m429e(sb.toString(), new Object[0]);
                return;
            }
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(str);
        sb2.append(th);
        Timber.m429e(sb2.toString(), new Object[0]);
        DialogFragment access$getConnectivityDialogFragment = this.this$0.getConnectivityDialogFragment();
        FragmentManager access$getSupportFragment = this.this$0.getSupportFragment();
        if (access$getConnectivityDialogFragment == null) {
            new ConnectivityDialogView().show(access$getSupportFragment, "connectivity_dialog");
        }
        Thread.sleep(5000);
        this.this$0.stream(this.$walletIds, this.$subscription);
    }

    public void onCompleted() {
        Timber.m438w("onCompleted", new Object[0]);
    }
}
