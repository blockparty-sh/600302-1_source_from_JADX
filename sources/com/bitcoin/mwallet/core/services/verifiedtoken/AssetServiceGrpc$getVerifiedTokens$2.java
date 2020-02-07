package com.bitcoin.mwallet.core.services.verifiedtoken;

import com.bitcoin.mwallet.Verifiedtoken.VerifiedToken;
import com.bitcoin.mwallet.Verifiedtoken.VerifiedTokenResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
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

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H@ø\u0001\u0000¢\u0006\u0004\b\u0002\u0010\u0003"}, mo37405d2 = {"<anonymous>", "Lcom/bitcoin/mwallet/core/services/verifiedtoken/VerifiedTokensResponse;", "invoke", "(Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.core.services.verifiedtoken.AssetServiceGrpc$getVerifiedTokens$2", mo38000f = "AssetServiceGrpc.kt", mo38001i = {}, mo38002l = {24}, mo38003m = "invokeSuspend", mo38004n = {}, mo38005s = {})
/* compiled from: AssetServiceGrpc.kt */
final class AssetServiceGrpc$getVerifiedTokens$2 extends SuspendLambda implements Function1<Continuation<? super VerifiedTokensResponse>, Object> {
    int label;
    final /* synthetic */ AssetServiceGrpc this$0;

    AssetServiceGrpc$getVerifiedTokens$2(AssetServiceGrpc assetServiceGrpc, Continuation continuation) {
        this.this$0 = assetServiceGrpc;
        super(1, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        return new AssetServiceGrpc$getVerifiedTokens$2(this.this$0, continuation);
    }

    public final Object invoke(Object obj) {
        return ((AssetServiceGrpc$getVerifiedTokens$2) create((Continuation) obj)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineContext access$getGrpcDispatcher$p = this.this$0.getGrpcDispatcher();
            Function2 assetServiceGrpc$getVerifiedTokens$2$response$1 = new AssetServiceGrpc$getVerifiedTokens$2$response$1(this, null);
            this.label = 1;
            obj = BuildersKt.withContext(access$getGrpcDispatcher$p, assetServiceGrpc$getVerifiedTokens$2$response$1, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        VerifiedTokenResponse verifiedTokenResponse = (VerifiedTokenResponse) obj;
        Intrinsics.checkExpressionValueIsNotNull(verifiedTokenResponse, "response");
        List listList = verifiedTokenResponse.getListList();
        Intrinsics.checkExpressionValueIsNotNull(listList, "response.listList");
        Iterable<VerifiedToken> iterable = listList;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (VerifiedToken verifiedToken : iterable) {
            Intrinsics.checkExpressionValueIsNotNull(verifiedToken, "token");
            String id = verifiedToken.getId();
            Intrinsics.checkExpressionValueIsNotNull(id, "token.id");
            String name = verifiedToken.getName();
            Intrinsics.checkExpressionValueIsNotNull(name, "token.name");
            String symbol = verifiedToken.getSymbol();
            Intrinsics.checkExpressionValueIsNotNull(symbol, "token.symbol");
            int decimals = verifiedToken.getDecimals();
            String iconImageUrl = verifiedToken.getIconImageUrl();
            Intrinsics.checkExpressionValueIsNotNull(iconImageUrl, "token.iconImageUrl");
            com.bitcoin.mwallet.core.models.verifiedtoken.VerifiedToken verifiedToken2 = new com.bitcoin.mwallet.core.models.verifiedtoken.VerifiedToken(id, name, symbol, decimals, iconImageUrl);
            arrayList.add(verifiedToken2);
        }
        return new VerifiedTokensResponse((List) arrayList);
    }
}
