package com.bitcoin.mwallet.core.services.p010tx;

import com.bitcoin.mwallet.Bip70BroadcastTxRequest;
import com.bitcoin.mwallet.Bip70BroadcastTxResponse;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import com.google.protobuf.ByteString;
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

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001*\u00020\u0003H@ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005"}, mo37405d2 = {"<anonymous>", "Lcom/bitcoin/mwallet/Bip70BroadcastTxResponse;", "kotlin.jvm.PlatformType", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.core.services.tx.TxServiceGrpc$broadcastBip70Tx$response$1", mo38000f = "TxServiceGrpc.kt", mo38001i = {}, mo38002l = {}, mo38003m = "invokeSuspend", mo38004n = {}, mo38005s = {})
/* renamed from: com.bitcoin.mwallet.core.services.tx.TxServiceGrpc$broadcastBip70Tx$response$1 */
/* compiled from: TxServiceGrpc.kt */
final class TxServiceGrpc$broadcastBip70Tx$response$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Bip70BroadcastTxResponse>, Object> {
    final /* synthetic */ String $depositAddress;
    final /* synthetic */ byte[] $txRaw;
    final /* synthetic */ String $url;
    final /* synthetic */ WalletId $walletId;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f442p$;
    final /* synthetic */ TxServiceGrpc this$0;

    TxServiceGrpc$broadcastBip70Tx$response$1(TxServiceGrpc txServiceGrpc, String str, byte[] bArr, String str2, WalletId walletId, Continuation continuation) {
        this.this$0 = txServiceGrpc;
        this.$url = str;
        this.$txRaw = bArr;
        this.$depositAddress = str2;
        this.$walletId = walletId;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        TxServiceGrpc$broadcastBip70Tx$response$1 txServiceGrpc$broadcastBip70Tx$response$1 = new TxServiceGrpc$broadcastBip70Tx$response$1(this.this$0, this.$url, this.$txRaw, this.$depositAddress, this.$walletId, continuation);
        txServiceGrpc$broadcastBip70Tx$response$1.f442p$ = (CoroutineScope) obj;
        return txServiceGrpc$broadcastBip70Tx$response$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((TxServiceGrpc$broadcastBip70Tx$response$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f442p$;
            return this.this$0.blockingStub.bip70BroadcastTx((Bip70BroadcastTxRequest) Bip70BroadcastTxRequest.newBuilder().setBip70Url(this.$url).setTxRaw(ByteString.copyFrom(this.$txRaw)).setDepositAddress(this.$depositAddress).setWalletId(this.$walletId.toString()).build());
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
