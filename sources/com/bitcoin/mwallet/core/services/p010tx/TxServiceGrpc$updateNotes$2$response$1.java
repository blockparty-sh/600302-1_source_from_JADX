package com.bitcoin.mwallet.core.services.p010tx;

import com.bitcoin.mwallet.TxServiceGrpc.TxServiceBlockingStub;
import com.bitcoin.mwallet.UpdateNotesRequest;
import com.bitcoin.mwallet.UpdateNotesResponse;
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

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001*\u00020\u0003H@ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005"}, mo37405d2 = {"<anonymous>", "Lcom/bitcoin/mwallet/UpdateNotesResponse;", "kotlin.jvm.PlatformType", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.core.services.tx.TxServiceGrpc$updateNotes$2$response$1", mo38000f = "TxServiceGrpc.kt", mo38001i = {}, mo38002l = {}, mo38003m = "invokeSuspend", mo38004n = {}, mo38005s = {})
/* renamed from: com.bitcoin.mwallet.core.services.tx.TxServiceGrpc$updateNotes$2$response$1 */
/* compiled from: TxServiceGrpc.kt */
final class TxServiceGrpc$updateNotes$2$response$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super UpdateNotesResponse>, Object> {
    int label;

    /* renamed from: p$ */
    private CoroutineScope f449p$;
    final /* synthetic */ TxServiceGrpc$updateNotes$2 this$0;

    TxServiceGrpc$updateNotes$2$response$1(TxServiceGrpc$updateNotes$2 txServiceGrpc$updateNotes$2, Continuation continuation) {
        this.this$0 = txServiceGrpc$updateNotes$2;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        TxServiceGrpc$updateNotes$2$response$1 txServiceGrpc$updateNotes$2$response$1 = new TxServiceGrpc$updateNotes$2$response$1(this.this$0, continuation);
        txServiceGrpc$updateNotes$2$response$1.f449p$ = (CoroutineScope) obj;
        return txServiceGrpc$updateNotes$2$response$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((TxServiceGrpc$updateNotes$2$response$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f449p$;
            GeneratedMessageLite build = UpdateNotesRequest.newBuilder().setWalletId(this.this$0.$walletId.getId()).setCopayerId(this.this$0.$copayerId.getId().getHex()).addTxNote(this.this$0.$txNote).build();
            Intrinsics.checkExpressionValueIsNotNull(build, "UpdateNotesRequest.newBu…                 .build()");
            UpdateNotesRequest updateNotesRequest = (UpdateNotesRequest) build;
            Companion companion = SignedStub.Companion;
            AbstractStub access$getBlockingStub$p = this.this$0.this$0.blockingStub;
            byte[] byteArray = updateNotesRequest.toByteArray();
            Intrinsics.checkExpressionValueIsNotNull(byteArray, "request.toByteArray()");
            return ((TxServiceBlockingStub) companion.signed(access$getBlockingStub$p, byteArray, this.this$0.$walletId, this.this$0.$copayerId, this.this$0.$signingKey)).updateTxNotes(updateNotesRequest);
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
