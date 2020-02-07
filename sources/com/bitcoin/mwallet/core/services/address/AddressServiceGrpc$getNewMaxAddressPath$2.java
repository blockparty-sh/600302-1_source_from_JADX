package com.bitcoin.mwallet.core.services.address;

import com.bitcoin.mwallet.GetNewMaxAddressPathResponse;
import com.bitcoin.mwallet.core.models.CopayerId;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import com.bitcoin.mwallet.core.utils.signature.SigningKey;
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

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H@ø\u0001\u0000¢\u0006\u0004\b\u0002\u0010\u0003"}, mo37405d2 = {"<anonymous>", "Lcom/bitcoin/mwallet/core/services/address/NewMaxAddressPathResponse;", "invoke", "(Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.core.services.address.AddressServiceGrpc$getNewMaxAddressPath$2", mo38000f = "AddressServiceGrpc.kt", mo38001i = {}, mo38002l = {38}, mo38003m = "invokeSuspend", mo38004n = {}, mo38005s = {})
/* compiled from: AddressServiceGrpc.kt */
final class AddressServiceGrpc$getNewMaxAddressPath$2 extends SuspendLambda implements Function1<Continuation<? super NewMaxAddressPathResponse>, Object> {
    final /* synthetic */ CopayerId $copayerId;
    final /* synthetic */ AddressPath $currentMaxPath;
    final /* synthetic */ SigningKey $signingKey;
    final /* synthetic */ WalletId $walletId;
    int label;
    final /* synthetic */ AddressServiceGrpc this$0;

    AddressServiceGrpc$getNewMaxAddressPath$2(AddressServiceGrpc addressServiceGrpc, WalletId walletId, AddressPath addressPath, CopayerId copayerId, SigningKey signingKey, Continuation continuation) {
        this.this$0 = addressServiceGrpc;
        this.$walletId = walletId;
        this.$currentMaxPath = addressPath;
        this.$copayerId = copayerId;
        this.$signingKey = signingKey;
        super(1, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        AddressServiceGrpc$getNewMaxAddressPath$2 addressServiceGrpc$getNewMaxAddressPath$2 = new AddressServiceGrpc$getNewMaxAddressPath$2(this.this$0, this.$walletId, this.$currentMaxPath, this.$copayerId, this.$signingKey, continuation);
        return addressServiceGrpc$getNewMaxAddressPath$2;
    }

    public final Object invoke(Object obj) {
        return ((AddressServiceGrpc$getNewMaxAddressPath$2) create((Continuation) obj)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineContext access$getGrpcDispatcher$p = this.this$0.getGrpcDispatcher();
            Function2 addressServiceGrpc$getNewMaxAddressPath$2$response$1 = new AddressServiceGrpc$getNewMaxAddressPath$2$response$1(this, null);
            this.label = 1;
            obj = BuildersKt.withContext(access$getGrpcDispatcher$p, addressServiceGrpc$getNewMaxAddressPath$2$response$1, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        Intrinsics.checkExpressionValueIsNotNull(obj, "withContext(grpcDispatch…equest)\n                }");
        return AddressServiceGrpc.Companion.toDomain((GetNewMaxAddressPathResponse) obj);
    }
}
