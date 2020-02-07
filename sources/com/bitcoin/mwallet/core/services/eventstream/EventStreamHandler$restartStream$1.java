package com.bitcoin.mwallet.core.services.eventstream;

import com.bitcoin.mwallet.core.models.wallet.C1261Wallet;
import com.bitcoin.mwallet.core.repositories.WalletRepository;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, mo37405d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.core.services.eventstream.EventStreamHandler$restartStream$1", mo38000f = "EventStreamHandler.kt", mo38001i = {}, mo38002l = {62}, mo38003m = "invokeSuspend", mo38004n = {}, mo38005s = {})
/* compiled from: EventStreamHandler.kt */
final class EventStreamHandler$restartStream$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;

    /* renamed from: p$ */
    private CoroutineScope f437p$;
    final /* synthetic */ EventStreamHandler this$0;

    EventStreamHandler$restartStream$1(EventStreamHandler eventStreamHandler, Continuation continuation) {
        this.this$0 = eventStreamHandler;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        EventStreamHandler$restartStream$1 eventStreamHandler$restartStream$1 = new EventStreamHandler$restartStream$1(this.this$0, continuation);
        eventStreamHandler$restartStream$1.f437p$ = (CoroutineScope) obj;
        return eventStreamHandler$restartStream$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((EventStreamHandler$restartStream$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f437p$;
            WalletRepository access$getWalletRepository$p = this.this$0.walletRepository;
            this.label = 1;
            obj = access$getWalletRepository$p.getWallets(this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        for (C1261Wallet wallet : (Iterable) obj) {
            this.this$0.getWallets().put(wallet.getId(), wallet);
        }
        if (!this.this$0.getWallets().keySet().isEmpty()) {
            this.this$0.eventStreamService.stream(this.this$0.getWallets().keySet(), this.this$0);
        }
        return Unit.INSTANCE;
    }
}
