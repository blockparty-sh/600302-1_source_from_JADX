package com.bitcoin.mwallet.core.services.p010tx;

import com.bitcoin.mwallet.BroadcastTxResponse;
import com.bitcoin.mwallet.core.models.Coin;
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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import timber.log.Timber;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H@ø\u0001\u0000¢\u0006\u0004\b\u0002\u0010\u0003"}, mo37405d2 = {"<anonymous>", "Lcom/bitcoin/mwallet/core/services/tx/BroadcastTxResponse;", "invoke", "(Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.core.services.tx.TxServiceGrpc$broadcastTx$2", mo38000f = "TxServiceGrpc.kt", mo38001i = {}, mo38002l = {93}, mo38003m = "invokeSuspend", mo38004n = {}, mo38005s = {})
/* renamed from: com.bitcoin.mwallet.core.services.tx.TxServiceGrpc$broadcastTx$2 */
/* compiled from: TxServiceGrpc.kt */
final class TxServiceGrpc$broadcastTx$2 extends SuspendLambda implements Function1<Continuation<? super BroadcastTxResponse>, Object> {
    final /* synthetic */ Coin $coin;
    final /* synthetic */ byte[] $txRaw;
    int label;
    final /* synthetic */ TxServiceGrpc this$0;

    TxServiceGrpc$broadcastTx$2(TxServiceGrpc txServiceGrpc, Coin coin, byte[] bArr, Continuation continuation) {
        this.this$0 = txServiceGrpc;
        this.$coin = coin;
        this.$txRaw = bArr;
        super(1, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        return new TxServiceGrpc$broadcastTx$2(this.this$0, this.$coin, this.$txRaw, continuation);
    }

    public final Object invoke(Object obj) {
        return ((TxServiceGrpc$broadcastTx$2) create((Continuation) obj)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineContext access$getGrpcDispatcher$p = this.this$0.getGrpcDispatcher();
            Function2 txServiceGrpc$broadcastTx$2$response$1 = new TxServiceGrpc$broadcastTx$2$response$1(this, null);
            this.label = 1;
            obj = BuildersKt.withContext(access$getGrpcDispatcher$p, txServiceGrpc$broadcastTx$2$response$1, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        Intrinsics.checkExpressionValueIsNotNull(obj, "withContext(grpcDispatch…equest)\n                }");
        BroadcastTxResponse broadcastTxResponse = (BroadcastTxResponse) obj;
        Timber.m426d(broadcastTxResponse.toString(), new Object[0]);
        return TxServiceGrpc.Companion.toDomain(broadcastTxResponse);
    }
}
