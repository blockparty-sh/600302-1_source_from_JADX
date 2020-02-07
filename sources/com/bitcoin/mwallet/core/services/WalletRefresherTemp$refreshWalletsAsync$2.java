package com.bitcoin.mwallet.core.services;

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

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, mo37405d2 = {"<anonymous>", "", "run"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: WalletRefresherTemp.kt */
final class WalletRefresherTemp$refreshWalletsAsync$2 implements Runnable {
    final /* synthetic */ WalletRefresherTemp this$0;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, mo37405d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
    @DebugMetadata(mo37999c = "com.bitcoin.mwallet.core.services.WalletRefresherTemp$refreshWalletsAsync$2$1", mo38000f = "WalletRefresherTemp.kt", mo38001i = {}, mo38002l = {25}, mo38003m = "invokeSuspend", mo38004n = {}, mo38005s = {})
    /* renamed from: com.bitcoin.mwallet.core.services.WalletRefresherTemp$refreshWalletsAsync$2$1 */
    /* compiled from: WalletRefresherTemp.kt */
    static final class C12781 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        /* renamed from: p$ */
        private CoroutineScope f430p$;
        final /* synthetic */ WalletRefresherTemp$refreshWalletsAsync$2 this$0;

        {
            this.this$0 = r1;
        }

        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            Intrinsics.checkParameterIsNotNull(continuation, "completion");
            C12781 r0 = new C12781(this.this$0, continuation);
            r0.f430p$ = (CoroutineScope) obj;
            return r0;
        }

        public final Object invoke(Object obj, Object obj2) {
            return ((C12781) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.f430p$;
                WalletRefresherTemp walletRefresherTemp = this.this$0.this$0;
                this.label = 1;
                if (walletRefresherTemp.doRefreshWallets(this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (i == 1) {
                ResultKt.throwOnFailure(obj);
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }
    }

    WalletRefresherTemp$refreshWalletsAsync$2(WalletRefresherTemp walletRefresherTemp) {
        this.this$0 = walletRefresherTemp;
    }

    public final void run() {
        BuildersKt__BuildersKt.runBlocking$default(null, new C12781(this, null), 1, null);
    }
}
