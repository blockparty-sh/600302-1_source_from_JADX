package com.bitcoin.mwallet.core.services.verifiedtoken;

import com.bitcoin.mwallet.Verifiedtoken.VerifiedTokenRequest;
import com.bitcoin.mwallet.Verifiedtoken.VerifiedTokenResponse;
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

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001*\u00020\u0003H@ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005"}, mo37405d2 = {"<anonymous>", "Lcom/bitcoin/mwallet/Verifiedtoken$VerifiedTokenResponse;", "kotlin.jvm.PlatformType", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.core.services.verifiedtoken.AssetServiceGrpc$getVerifiedTokens$2$response$1", mo38000f = "AssetServiceGrpc.kt", mo38001i = {}, mo38002l = {}, mo38003m = "invokeSuspend", mo38004n = {}, mo38005s = {})
/* compiled from: AssetServiceGrpc.kt */
final class AssetServiceGrpc$getVerifiedTokens$2$response$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super VerifiedTokenResponse>, Object> {
    int label;

    /* renamed from: p$ */
    private CoroutineScope f450p$;
    final /* synthetic */ AssetServiceGrpc$getVerifiedTokens$2 this$0;

    AssetServiceGrpc$getVerifiedTokens$2$response$1(AssetServiceGrpc$getVerifiedTokens$2 assetServiceGrpc$getVerifiedTokens$2, Continuation continuation) {
        this.this$0 = assetServiceGrpc$getVerifiedTokens$2;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        AssetServiceGrpc$getVerifiedTokens$2$response$1 assetServiceGrpc$getVerifiedTokens$2$response$1 = new AssetServiceGrpc$getVerifiedTokens$2$response$1(this.this$0, continuation);
        assetServiceGrpc$getVerifiedTokens$2$response$1.f450p$ = (CoroutineScope) obj;
        return assetServiceGrpc$getVerifiedTokens$2$response$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((AssetServiceGrpc$getVerifiedTokens$2$response$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f450p$;
            GeneratedMessageLite build = VerifiedTokenRequest.newBuilder().build();
            Intrinsics.checkExpressionValueIsNotNull(build, "Verifiedtoken.VerifiedTo…                 .build()");
            return this.this$0.this$0.blockingStub.getVerifiedToken((VerifiedTokenRequest) build);
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
