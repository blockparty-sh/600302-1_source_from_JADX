package com.bitcoin.mwallet.core.services.address;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000(\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH@ø\u0001\u0000"}, mo37405d2 = {"getNewMaxAddressPath", "", "walletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "copayerId", "Lcom/bitcoin/mwallet/core/models/CopayerId;", "signingKey", "Lcom/bitcoin/mwallet/core/utils/signature/SigningKey;", "currentMaxPath", "Lcom/bitcoin/mwallet/core/services/address/AddressPath;", "continuation", "Lkotlin/coroutines/Continuation;", "Lcom/bitcoin/mwallet/core/services/address/NewMaxAddressPathResponse;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.core.services.address.AddressServiceGrpc", mo38000f = "AddressServiceGrpc.kt", mo38001i = {0, 0, 0, 0, 0}, mo38002l = {37}, mo38003m = "getNewMaxAddressPath", mo38004n = {"this", "walletId", "copayerId", "signingKey", "currentMaxPath"}, mo38005s = {"L$0", "L$1", "L$2", "L$3", "L$4"})
/* compiled from: AddressServiceGrpc.kt */
final class AddressServiceGrpc$getNewMaxAddressPath$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ AddressServiceGrpc this$0;

    AddressServiceGrpc$getNewMaxAddressPath$1(AddressServiceGrpc addressServiceGrpc, Continuation continuation) {
        this.this$0 = addressServiceGrpc;
        super(continuation);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.getNewMaxAddressPath(null, null, null, null, this);
    }
}
