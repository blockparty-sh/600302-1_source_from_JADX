package com.bitcoin.mwallet.core.services.verifiedtoken;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H@ø\u0001\u0000"}, mo37405d2 = {"getVerifiedTokens", "", "continuation", "Lkotlin/coroutines/Continuation;", "Lcom/bitcoin/mwallet/core/services/verifiedtoken/VerifiedTokensResponse;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.core.services.verifiedtoken.AssetServiceGrpc", mo38000f = "AssetServiceGrpc.kt", mo38001i = {0}, mo38002l = {23}, mo38003m = "getVerifiedTokens", mo38004n = {"this"}, mo38005s = {"L$0"})
/* compiled from: AssetServiceGrpc.kt */
final class AssetServiceGrpc$getVerifiedTokens$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ AssetServiceGrpc this$0;

    AssetServiceGrpc$getVerifiedTokens$1(AssetServiceGrpc assetServiceGrpc, Continuation continuation) {
        this.this$0 = assetServiceGrpc;
        super(continuation);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.getVerifiedTokens(this);
    }
}
