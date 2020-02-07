package com.bitcoin.mwallet.core.repositories;

import com.bitcoin.mwallet.BuildConfig;
import com.bitcoin.mwallet.core.models.CopayerId;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import com.bitcoin.mwallet.core.services.notification.NotificationService;
import com.bitcoin.mwallet.core.utils.SignedStub.SignedRequest;
import com.bitcoin.mwallet.core.utils.signature.SigningKey;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import java.io.IOException;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import timber.log.Timber;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, mo37405d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.core.repositories.WalletRepository$subscribeToNotifications$1", mo38000f = "WalletRepository.kt", mo38001i = {0}, mo38002l = {168}, mo38003m = "invokeSuspend", mo38004n = {"fbToken"}, mo38005s = {"L$0"})
/* compiled from: WalletRepository.kt */
final class WalletRepository$subscribeToNotifications$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ CopayerId $copayerId;
    final /* synthetic */ SigningKey $signingKey;
    final /* synthetic */ WalletId $walletId;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f423p$;
    final /* synthetic */ WalletRepository this$0;

    WalletRepository$subscribeToNotifications$1(WalletRepository walletRepository, WalletId walletId, CopayerId copayerId, SigningKey signingKey, Continuation continuation) {
        this.this$0 = walletRepository;
        this.$walletId = walletId;
        this.$copayerId = copayerId;
        this.$signingKey = signingKey;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        WalletRepository$subscribeToNotifications$1 walletRepository$subscribeToNotifications$1 = new WalletRepository$subscribeToNotifications$1(this.this$0, this.$walletId, this.$copayerId, this.$signingKey, continuation);
        walletRepository$subscribeToNotifications$1.f423p$ = (CoroutineScope) obj;
        return walletRepository$subscribeToNotifications$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((WalletRepository$subscribeToNotifications$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f423p$;
            Timber.m426d("Fetching Firebase Token", new Object[0]);
            String token = FirebaseInstanceId.getInstance().getToken(BuildConfig.FIREBASE_SENDER_ID, FirebaseMessaging.INSTANCE_ID_SCOPE);
            Timber.m426d(token, new Object[0]);
            NotificationService access$getNotificationService$p = this.this$0.notificationService;
            Map mapOf = MapsKt.mapOf(TuplesKt.m309to(new SignedRequest(this.$walletId, this.$copayerId, this.$signingKey), token));
            this.L$0 = token;
            this.label = 1;
            if (access$getNotificationService$p.subscribe(mapOf, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            String str = (String) this.L$0;
            try {
                ResultKt.throwOnFailure(obj);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
