package com.bitcoin.mwallet.core.services.p010tx;

import com.bitcoin.mwallet.GetUtxosRequest;
import com.bitcoin.mwallet.GetUtxosResponse;
import com.bitcoin.mwallet.TxServiceGrpc.TxServiceBlockingStub;
import com.bitcoin.mwallet.core.utils.SignedStub;
import com.bitcoin.mwallet.core.utils.SignedStub.Companion;
import com.google.protobuf.GeneratedMessageLite;
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
import p015io.grpc.stub.AbstractStub;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001*\u00020\u0003H@ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005"}, mo37405d2 = {"<anonymous>", "Lcom/bitcoin/mwallet/GetUtxosResponse;", "kotlin.jvm.PlatformType", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.core.services.tx.TxServiceGrpc$getUtxos$2$response$1", mo38000f = "TxServiceGrpc.kt", mo38001i = {}, mo38002l = {}, mo38003m = "invokeSuspend", mo38004n = {}, mo38005s = {})
/* renamed from: com.bitcoin.mwallet.core.services.tx.TxServiceGrpc$getUtxos$2$response$1 */
/* compiled from: TxServiceGrpc.kt */
final class TxServiceGrpc$getUtxos$2$response$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super GetUtxosResponse>, Object> {
    int label;

    /* renamed from: p$ */
    private CoroutineScope f446p$;
    final /* synthetic */ TxServiceGrpc$getUtxos$2 this$0;

    TxServiceGrpc$getUtxos$2$response$1(TxServiceGrpc$getUtxos$2 txServiceGrpc$getUtxos$2, Continuation continuation) {
        this.this$0 = txServiceGrpc$getUtxos$2;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        TxServiceGrpc$getUtxos$2$response$1 txServiceGrpc$getUtxos$2$response$1 = new TxServiceGrpc$getUtxos$2$response$1(this.this$0, continuation);
        txServiceGrpc$getUtxos$2$response$1.f446p$ = (CoroutineScope) obj;
        return txServiceGrpc$getUtxos$2$response$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((TxServiceGrpc$getUtxos$2$response$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f446p$;
            GeneratedMessageLite build = GetUtxosRequest.newBuilder().addWalletId(this.this$0.$wallet.getId().getId()).build();
            Intrinsics.checkExpressionValueIsNotNull(build, "GetUtxosRequest.newBuild…                 .build()");
            GetUtxosRequest getUtxosRequest = (GetUtxosRequest) build;
            Companion companion = SignedStub.Companion;
            AbstractStub access$getBlockingStub$p = this.this$0.this$0.blockingStub;
            byte[] byteArray = getUtxosRequest.toByteArray();
            Intrinsics.checkExpressionValueIsNotNull(byteArray, "request.toByteArray()");
            return ((TxServiceBlockingStub) companion.signed(access$getBlockingStub$p, byteArray, this.this$0.$wallet.getId(), this.this$0.$wallet.getCopayers().getWalletCopayerId(), this.this$0.$wallet.getSigningKey())).getUtxos(getUtxosRequest);
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
