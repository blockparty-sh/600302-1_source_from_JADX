package com.bitcoin.mwallet.core.services.notification;

import com.bitcoin.mwallet.NotificationsServiceGrpc;
import com.bitcoin.mwallet.NotificationsServiceGrpc.NotificationsServiceBlockingStub;
import com.bitcoin.mwallet.RegisterRegionRequest;
import com.bitcoin.mwallet.RegisterRegionRequest.RegionRegistration;
import com.bitcoin.mwallet.RegisterRegionResponse;
import com.bitcoin.mwallet.SubscribeRequest;
import com.bitcoin.mwallet.SubscribeResponse;
import com.bitcoin.mwallet.SubscriptionRequest;
import com.bitcoin.mwallet.UnsubscribeRequest;
import com.bitcoin.mwallet.UnsubscribeResponse;
import com.bitcoin.mwallet.core.services.grpc.GrpcServiceBase;
import com.bitcoin.mwallet.core.utils.SignedStub;
import com.bitcoin.mwallet.core.utils.SignedStub.Companion;
import com.bitcoin.mwallet.core.utils.SignedStub.SignedRequest;
import com.google.protobuf.GeneratedMessageLite;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineDispatcher;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import p015io.grpc.ManagedChannel;
import p015io.grpc.stub.AbstractStub;
import timber.log.Timber;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u0002B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J'\u0010\n\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\u000f\u001a\u00020\u0010H@ø\u0001\u0000¢\u0006\u0002\u0010\u0011J%\u0010\u0012\u001a\u00020\u000b2\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00100\u0013H@ø\u0001\u0000¢\u0006\u0002\u0010\u0014J%\u0010\u0015\u001a\u00020\u000b2\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00100\u0013H@ø\u0001\u0000¢\u0006\u0002\u0010\u0014R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u0016"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/services/notification/NotificationServiceGrpc;", "Lcom/bitcoin/mwallet/core/services/grpc/GrpcServiceBase;", "Lcom/bitcoin/mwallet/core/services/notification/NotificationService;", "grpcDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "channel", "Lio/grpc/ManagedChannel;", "(Lkotlinx/coroutines/CoroutineDispatcher;Lio/grpc/ManagedChannel;)V", "blockingStub", "Lcom/bitcoin/mwallet/NotificationsServiceGrpc$NotificationsServiceBlockingStub;", "registerRegion", "", "signedRequest", "", "Lcom/bitcoin/mwallet/core/utils/SignedStub$SignedRequest;", "languageCode", "", "(Ljava/util/Set;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "subscribe", "", "(Ljava/util/Map;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "unSubscribe", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: NotificationServiceGrpc.kt */
public final class NotificationServiceGrpc extends GrpcServiceBase implements NotificationService {
    private final NotificationsServiceBlockingStub blockingStub;

    public NotificationServiceGrpc(@NotNull CoroutineDispatcher coroutineDispatcher, @NotNull ManagedChannel managedChannel) {
        Intrinsics.checkParameterIsNotNull(coroutineDispatcher, "grpcDispatcher");
        Intrinsics.checkParameterIsNotNull(managedChannel, "channel");
        super(coroutineDispatcher);
        NotificationsServiceBlockingStub newBlockingStub = NotificationsServiceGrpc.newBlockingStub(managedChannel);
        Intrinsics.checkExpressionValueIsNotNull(newBlockingStub, "NotificationsServiceGrpc.newBlockingStub(channel)");
        this.blockingStub = newBlockingStub;
    }

    @Nullable
    public Object registerRegion(@NotNull Set<SignedRequest> set, @NotNull String str, @NotNull Continuation<? super Boolean> continuation) {
        boolean z = false;
        try {
            Iterable<SignedRequest> iterable = set;
            Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
            for (SignedRequest walletId : iterable) {
                arrayList.add((RegionRegistration) RegionRegistration.newBuilder().setSingleSigWalletId(walletId.getWalletId().getId()).build());
            }
            GeneratedMessageLite build = RegisterRegionRequest.newBuilder().addAllRegistration((List) arrayList).setUserLanguage(str).build();
            Intrinsics.checkExpressionValueIsNotNull(build, "RegisterRegionRequest.ne…\n                .build()");
            RegisterRegionResponse registerRegion = this.blockingStub.registerRegion((RegisterRegionRequest) build);
            Intrinsics.checkExpressionValueIsNotNull(registerRegion, "blockingStub.registerRegion(request)");
            StringBuilder sb = new StringBuilder();
            sb.append("Registartion Response: ");
            sb.append(registerRegion);
            Timber.m426d(sb.toString(), new Object[0]);
            z = true;
        } catch (RuntimeException unused) {
        }
        return Boxing.boxBoolean(z);
    }

    @Nullable
    public Object unSubscribe(@NotNull Map<SignedRequest, String> map, @NotNull Continuation<? super Boolean> continuation) {
        boolean z = false;
        try {
            Collection arrayList = new ArrayList(map.size());
            for (Entry entry : map.entrySet()) {
                arrayList.add((SubscriptionRequest) SubscriptionRequest.newBuilder().setSingleSigWalletId(((SignedRequest) entry.getKey()).getWalletId().getId()).setToken((String) entry.getValue()).build());
            }
            GeneratedMessageLite build = UnsubscribeRequest.newBuilder().addAllRequest((List) arrayList).build();
            Intrinsics.checkExpressionValueIsNotNull(build, "UnsubscribeRequest.newBu…\n                .build()");
            UnsubscribeResponse unsubscribe = this.blockingStub.unsubscribe((UnsubscribeRequest) build);
            Intrinsics.checkExpressionValueIsNotNull(unsubscribe, "blockingStub.unsubscribe(request)");
            StringBuilder sb = new StringBuilder();
            sb.append("UnRegistration Response: ");
            sb.append(unsubscribe);
            Timber.m426d(sb.toString(), new Object[0]);
            z = true;
        } catch (RuntimeException unused) {
        }
        return Boxing.boxBoolean(z);
    }

    @Nullable
    public Object subscribe(@NotNull Map<SignedRequest, String> map, @NotNull Continuation<? super Boolean> continuation) {
        boolean z = false;
        try {
            Collection arrayList = new ArrayList(map.size());
            for (Entry entry : map.entrySet()) {
                arrayList.add((SubscriptionRequest) SubscriptionRequest.newBuilder().setSingleSigWalletId(((SignedRequest) entry.getKey()).getWalletId().getId()).setToken((String) entry.getValue()).build());
            }
            GeneratedMessageLite build = SubscribeRequest.newBuilder().addAllRequest((List) arrayList).build();
            Intrinsics.checkExpressionValueIsNotNull(build, "SubscribeRequest.newBuil…quest(walletList).build()");
            SubscribeRequest subscribeRequest = (SubscribeRequest) build;
            Companion companion = SignedStub.Companion;
            AbstractStub abstractStub = this.blockingStub;
            byte[] byteArray = subscribeRequest.toByteArray();
            Intrinsics.checkExpressionValueIsNotNull(byteArray, "request.toByteArray()");
            SubscribeResponse subscribe = ((NotificationsServiceBlockingStub) companion.signed(abstractStub, byteArray, map.keySet())).subscribe(subscribeRequest);
            Intrinsics.checkExpressionValueIsNotNull(subscribe, "SignedStub.signed(blocki….keys).subscribe(request)");
            StringBuilder sb = new StringBuilder();
            sb.append("Subscribe Response: ");
            sb.append(subscribe);
            Timber.m426d(sb.toString(), new Object[0]);
            z = true;
        } catch (RuntimeException e) {
            Timber.m430e(e);
        }
        return Boxing.boxBoolean(z);
    }
}
