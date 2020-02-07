package com.bitcoin.mwallet.core.services.p010tx;

import com.bitcoin.mwallet.GetTxHistoryRequest;
import com.bitcoin.mwallet.TxServiceGrpc.TxServiceStub;
import com.bitcoin.mwallet.core.models.CopayerId;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import com.bitcoin.mwallet.core.utils.SignedStub;
import com.bitcoin.mwallet.core.utils.SignedStub.Companion;
import com.bitcoin.mwallet.core.utils.signature.SigningKey;
import com.google.protobuf.GeneratedMessageLite;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import p015io.grpc.stub.AbstractStub;
import p015io.grpc.stub.StreamObserver;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H@ø\u0001\u0000¢\u0006\u0004\b\u0002\u0010\u0003"}, mo37405d2 = {"<anonymous>", "", "invoke", "(Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.core.services.tx.TxServiceGrpc$getTransactionHistory$2", mo38000f = "TxServiceGrpc.kt", mo38001i = {}, mo38002l = {136}, mo38003m = "invokeSuspend", mo38004n = {}, mo38005s = {})
/* renamed from: com.bitcoin.mwallet.core.services.tx.TxServiceGrpc$getTransactionHistory$2 */
/* compiled from: TxServiceGrpc.kt */
final class TxServiceGrpc$getTransactionHistory$2 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
    final /* synthetic */ CopayerId $copayerId;
    final /* synthetic */ StreamObserver $observer;
    final /* synthetic */ SigningKey $signingKey;
    final /* synthetic */ long $startTime;
    final /* synthetic */ WalletId $walletId;
    int label;
    final /* synthetic */ TxServiceGrpc this$0;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, mo37405d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
    @DebugMetadata(mo37999c = "com.bitcoin.mwallet.core.services.tx.TxServiceGrpc$getTransactionHistory$2$1", mo38000f = "TxServiceGrpc.kt", mo38001i = {}, mo38002l = {}, mo38003m = "invokeSuspend", mo38004n = {}, mo38005s = {})
    /* renamed from: com.bitcoin.mwallet.core.services.tx.TxServiceGrpc$getTransactionHistory$2$1 */
    /* compiled from: TxServiceGrpc.kt */
    static final class C12791 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        /* renamed from: p$ */
        private CoroutineScope f445p$;
        final /* synthetic */ TxServiceGrpc$getTransactionHistory$2 this$0;

        {
            this.this$0 = r1;
        }

        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            Intrinsics.checkParameterIsNotNull(continuation, "completion");
            C12791 r0 = new C12791(this.this$0, continuation);
            r0.f445p$ = (CoroutineScope) obj;
            return r0;
        }

        public final Object invoke(Object obj, Object obj2) {
            return ((C12791) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.f445p$;
                GeneratedMessageLite build = GetTxHistoryRequest.newBuilder().setWalletId(this.this$0.$walletId.getId()).setCopayerId(this.this$0.$copayerId.getId().getHex()).setEpochMillisStart(this.this$0.$startTime).build();
                Intrinsics.checkExpressionValueIsNotNull(build, "GetTxHistoryRequest.newB…                 .build()");
                GetTxHistoryRequest getTxHistoryRequest = (GetTxHistoryRequest) build;
                Companion companion = SignedStub.Companion;
                AbstractStub access$getStub$p = this.this$0.this$0.stub;
                byte[] byteArray = getTxHistoryRequest.toByteArray();
                Intrinsics.checkExpressionValueIsNotNull(byteArray, "request.toByteArray()");
                ((TxServiceStub) companion.signed(access$getStub$p, byteArray, this.this$0.$walletId, this.this$0.$copayerId, this.this$0.$signingKey)).getTxHistory(getTxHistoryRequest, this.this$0.$observer);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    TxServiceGrpc$getTransactionHistory$2(TxServiceGrpc txServiceGrpc, WalletId walletId, CopayerId copayerId, long j, SigningKey signingKey, StreamObserver streamObserver, Continuation continuation) {
        this.this$0 = txServiceGrpc;
        this.$walletId = walletId;
        this.$copayerId = copayerId;
        this.$startTime = j;
        this.$signingKey = signingKey;
        this.$observer = streamObserver;
        super(1, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        TxServiceGrpc$getTransactionHistory$2 txServiceGrpc$getTransactionHistory$2 = new TxServiceGrpc$getTransactionHistory$2(this.this$0, this.$walletId, this.$copayerId, this.$startTime, this.$signingKey, this.$observer, continuation);
        return txServiceGrpc$getTransactionHistory$2;
    }

    public final Object invoke(Object obj) {
        return ((TxServiceGrpc$getTransactionHistory$2) create((Continuation) obj)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineContext access$getGrpcDispatcher$p = this.this$0.getGrpcDispatcher();
            Function2 r1 = new C12791(this, null);
            this.label = 1;
            if (BuildersKt.withContext(access$getGrpcDispatcher$p, r1, this) == coroutine_suspended) {
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
