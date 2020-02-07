package com.bitcoin.mwallet.core.services.address;

import com.bitcoin.mwallet.AddressServiceGrpc.AddressServiceBlockingStub;
import com.bitcoin.mwallet.ScanForFundsRequest;
import com.bitcoin.mwallet.ScanForFundsResponse;
import com.bitcoin.mwallet.core.models.CopayerId;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import com.bitcoin.mwallet.core.utils.SignedStub;
import com.bitcoin.mwallet.core.utils.SignedStub.Companion;
import com.bitcoin.mwallet.core.utils.signature.SigningKey;
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

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001*\u00020\u0003H@ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005"}, mo37405d2 = {"<anonymous>", "Lcom/bitcoin/mwallet/ScanForFundsResponse;", "kotlin.jvm.PlatformType", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.core.services.address.AddressServiceGrpc$scanForFunds$2", mo38000f = "AddressServiceGrpc.kt", mo38001i = {}, mo38002l = {}, mo38003m = "invokeSuspend", mo38004n = {}, mo38005s = {})
/* compiled from: AddressServiceGrpc.kt */
final class AddressServiceGrpc$scanForFunds$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ScanForFundsResponse>, Object> {
    final /* synthetic */ CopayerId $copayerId;
    final /* synthetic */ SigningKey $signingKey;
    final /* synthetic */ WalletId $walletId;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f434p$;
    final /* synthetic */ AddressServiceGrpc this$0;

    AddressServiceGrpc$scanForFunds$2(AddressServiceGrpc addressServiceGrpc, WalletId walletId, CopayerId copayerId, SigningKey signingKey, Continuation continuation) {
        this.this$0 = addressServiceGrpc;
        this.$walletId = walletId;
        this.$copayerId = copayerId;
        this.$signingKey = signingKey;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        AddressServiceGrpc$scanForFunds$2 addressServiceGrpc$scanForFunds$2 = new AddressServiceGrpc$scanForFunds$2(this.this$0, this.$walletId, this.$copayerId, this.$signingKey, continuation);
        addressServiceGrpc$scanForFunds$2.f434p$ = (CoroutineScope) obj;
        return addressServiceGrpc$scanForFunds$2;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((AddressServiceGrpc$scanForFunds$2) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f434p$;
            ScanForFundsRequest scanForFundsRequest = (ScanForFundsRequest) ScanForFundsRequest.newBuilder().setWalletId(this.$walletId.getId()).build();
            Companion companion = SignedStub.Companion;
            AbstractStub access$getBlockingStub$p = this.this$0.blockingStub;
            byte[] byteArray = scanForFundsRequest.toByteArray();
            Intrinsics.checkExpressionValueIsNotNull(byteArray, "scanningRequest.toByteArray()");
            return ((AddressServiceBlockingStub) companion.signed(access$getBlockingStub$p, byteArray, this.$walletId, this.$copayerId, this.$signingKey)).scanForFunds(scanForFundsRequest);
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
