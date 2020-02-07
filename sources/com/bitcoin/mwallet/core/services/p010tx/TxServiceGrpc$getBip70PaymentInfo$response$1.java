package com.bitcoin.mwallet.core.services.p010tx;

import com.bitcoin.mwallet.Bip70PaymentRequest;
import com.bitcoin.mwallet.Bip70PaymentResponse;
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

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001*\u00020\u0003H@ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005"}, mo37405d2 = {"<anonymous>", "Lcom/bitcoin/mwallet/Bip70PaymentResponse;", "kotlin.jvm.PlatformType", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.core.services.tx.TxServiceGrpc$getBip70PaymentInfo$response$1", mo38000f = "TxServiceGrpc.kt", mo38001i = {}, mo38002l = {}, mo38003m = "invokeSuspend", mo38004n = {}, mo38005s = {})
/* renamed from: com.bitcoin.mwallet.core.services.tx.TxServiceGrpc$getBip70PaymentInfo$response$1 */
/* compiled from: TxServiceGrpc.kt */
final class TxServiceGrpc$getBip70PaymentInfo$response$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Bip70PaymentResponse>, Object> {
    final /* synthetic */ String $url;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f444p$;
    final /* synthetic */ TxServiceGrpc this$0;

    TxServiceGrpc$getBip70PaymentInfo$response$1(TxServiceGrpc txServiceGrpc, String str, Continuation continuation) {
        this.this$0 = txServiceGrpc;
        this.$url = str;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        TxServiceGrpc$getBip70PaymentInfo$response$1 txServiceGrpc$getBip70PaymentInfo$response$1 = new TxServiceGrpc$getBip70PaymentInfo$response$1(this.this$0, this.$url, continuation);
        txServiceGrpc$getBip70PaymentInfo$response$1.f444p$ = (CoroutineScope) obj;
        return txServiceGrpc$getBip70PaymentInfo$response$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((TxServiceGrpc$getBip70PaymentInfo$response$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f444p$;
            GeneratedMessageLite build = Bip70PaymentRequest.newBuilder().setBip70Url(this.$url).build();
            Intrinsics.checkExpressionValueIsNotNull(build, "Bip70PaymentRequest.newB…                 .build()");
            return this.this$0.blockingStub.bip70Payment((Bip70PaymentRequest) build);
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
