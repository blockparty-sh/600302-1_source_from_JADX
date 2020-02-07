package com.bitcoin.mwallet.core.services.eventstream;

import android.app.Activity;
import android.app.Application;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import com.bitcoin.mwallet.ApplicationClass;
import com.bitcoin.mwallet.EventStreamServiceGrpc.EventStreamServiceStub;
import com.bitcoin.mwallet.StreamEvent;
import com.bitcoin.mwallet.StreamEvent.EventCase;
import com.bitcoin.mwallet.StreamRequest;
import com.bitcoin.mwallet.StreamRequest.Builder;
import com.bitcoin.mwallet.TxHistory;
import com.bitcoin.mwallet.WalletUtxos;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import com.bitcoin.mwallet.core.services.grpc.GrpcServiceBase;
import com.google.protobuf.GeneratedMessageLite;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineDispatcher;
import org.jetbrains.annotations.NotNull;
import p015io.grpc.ClientCall;
import p015io.grpc.ManagedChannel;
import p015io.grpc.stub.ClientCalls;
import timber.log.Timber;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u001aB\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\n\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0002J\b\u0010\u0011\u001a\u00020\u0012H\u0002J\u001c\u0010\u0013\u001a\u00020\u00142\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u00162\u0006\u0010\u0018\u001a\u00020\u0019R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f\u0018\u00010\nX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/services/eventstream/EventStreamServiceGrpc;", "Lcom/bitcoin/mwallet/core/services/grpc/GrpcServiceBase;", "grpcDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "channel", "Lio/grpc/ManagedChannel;", "application", "Landroid/app/Application;", "(Lkotlinx/coroutines/CoroutineDispatcher;Lio/grpc/ManagedChannel;Landroid/app/Application;)V", "clientCall", "Lio/grpc/ClientCall;", "Lcom/bitcoin/mwallet/StreamRequest;", "Lcom/bitcoin/mwallet/StreamEvent;", "service", "Lcom/bitcoin/mwallet/EventStreamServiceGrpc$EventStreamServiceStub;", "getConnectivityDialogFragment", "Landroidx/fragment/app/DialogFragment;", "getSupportFragment", "Landroidx/fragment/app/FragmentManager;", "stream", "", "walletIds", "", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "subscription", "Lcom/bitcoin/mwallet/core/services/eventstream/EventStreamServiceGrpc$EventStreamSubscriber;", "EventStreamSubscriber", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: EventStreamServiceGrpc.kt */
public final class EventStreamServiceGrpc extends GrpcServiceBase {
    private final Application application;
    private final ManagedChannel channel;
    private ClientCall<StreamRequest, StreamEvent> clientCall;
    private final EventStreamServiceStub service;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&¨\u0006\t"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/services/eventstream/EventStreamServiceGrpc$EventStreamSubscriber;", "", "onTxHistory", "", "txHistory", "Lcom/bitcoin/mwallet/TxHistory;", "onWalletUtxos", "walletUtxos", "Lcom/bitcoin/mwallet/WalletUtxos;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: EventStreamServiceGrpc.kt */
    public interface EventStreamSubscriber {
        void onTxHistory(@NotNull TxHistory txHistory);

        void onWalletUtxos(@NotNull WalletUtxos walletUtxos);
    }

    @Metadata(mo37403bv = {1, 0, 3}, mo37406k = 3, mo37407mv = {1, 1, 15})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[EventCase.values().length];

        static {
            $EnumSwitchMapping$0[EventCase.TX_HISTORY.ordinal()] = 1;
            $EnumSwitchMapping$0[EventCase.UTXOS.ordinal()] = 2;
        }
    }

    public EventStreamServiceGrpc(@NotNull CoroutineDispatcher coroutineDispatcher, @NotNull ManagedChannel managedChannel, @NotNull Application application2) {
        Intrinsics.checkParameterIsNotNull(coroutineDispatcher, "grpcDispatcher");
        Intrinsics.checkParameterIsNotNull(managedChannel, "channel");
        Intrinsics.checkParameterIsNotNull(application2, "application");
        super(coroutineDispatcher);
        this.channel = managedChannel;
        this.application = application2;
        EventStreamServiceStub newStub = com.bitcoin.mwallet.EventStreamServiceGrpc.newStub(this.channel);
        Intrinsics.checkExpressionValueIsNotNull(newStub, "EventStreamServiceGrpc.newStub(channel)");
        this.service = newStub;
    }

    /* access modifiers changed from: private */
    public final DialogFragment getConnectivityDialogFragment() {
        return (DialogFragment) getSupportFragment().findFragmentByTag("connectivity_dialog");
    }

    /* access modifiers changed from: private */
    public final FragmentManager getSupportFragment() {
        Application application2 = this.application;
        if (application2 != null) {
            Activity activityContext = ((ApplicationClass) application2).getActivityContext();
            if (activityContext != null) {
                FragmentManager supportFragmentManager = ((AppCompatActivity) activityContext).getSupportFragmentManager();
                Intrinsics.checkExpressionValueIsNotNull(supportFragmentManager, "(currentActivity as AppC…y).supportFragmentManager");
                return supportFragmentManager;
            }
            throw new TypeCastException("null cannot be cast to non-null type androidx.appcompat.app.AppCompatActivity");
        }
        throw new TypeCastException("null cannot be cast to non-null type com.bitcoin.mwallet.ApplicationClass");
    }

    public final synchronized void stream(@NotNull Set<WalletId> set, @NotNull EventStreamSubscriber eventStreamSubscriber) {
        Intrinsics.checkParameterIsNotNull(set, "walletIds");
        Intrinsics.checkParameterIsNotNull(eventStreamSubscriber, "subscription");
        ClientCall<StreamRequest, StreamEvent> clientCall2 = this.clientCall;
        if (clientCall2 != null) {
            clientCall2.cancel("User initiated cancel", null);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("stream walletIds=");
        sb.append(set);
        Timber.m426d(sb.toString(), new Object[0]);
        Builder newBuilder = StreamRequest.newBuilder();
        Iterable<WalletId> iterable = set;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (WalletId id : iterable) {
            arrayList.add(id.getId());
        }
        GeneratedMessageLite build = newBuilder.addAllWalletId((List) arrayList).build();
        Intrinsics.checkExpressionValueIsNotNull(build, "StreamRequest.newBuilder…d })\n            .build()");
        StreamRequest streamRequest = (StreamRequest) build;
        this.clientCall = this.channel.newCall(com.bitcoin.mwallet.EventStreamServiceGrpc.getStreamMethod(), this.service.getCallOptions());
        ClientCalls.asyncServerStreamingCall(this.clientCall, streamRequest, new EventStreamServiceGrpc$stream$responseObserver$1(this, eventStreamSubscriber, set));
    }
}
