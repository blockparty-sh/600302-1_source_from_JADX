package com.bitcoin.mwallet.core.services.featuredbusiness;

import com.bitcoin.mwallet.Featuredbusiness.FeaturedBusiness;
import com.bitcoin.mwallet.Featuredbusiness.FeaturedBusinessResponse;
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

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H@ø\u0001\u0000¢\u0006\u0004\b\u0002\u0010\u0003"}, mo37405d2 = {"<anonymous>", "Lcom/bitcoin/mwallet/core/services/featuredbusiness/GetFeaturedBusinessResponse;", "invoke", "(Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.core.services.featuredbusiness.FeaturedBusinessServiceGrpc$getFeaturedBusinesses$2", mo38000f = "FeaturedBusinessServiceGrpc.kt", mo38001i = {}, mo38002l = {25}, mo38003m = "invokeSuspend", mo38004n = {}, mo38005s = {})
/* compiled from: FeaturedBusinessServiceGrpc.kt */
final class FeaturedBusinessServiceGrpc$getFeaturedBusinesses$2 extends SuspendLambda implements Function1<Continuation<? super GetFeaturedBusinessResponse>, Object> {
    int label;
    final /* synthetic */ FeaturedBusinessServiceGrpc this$0;

    FeaturedBusinessServiceGrpc$getFeaturedBusinesses$2(FeaturedBusinessServiceGrpc featuredBusinessServiceGrpc, Continuation continuation) {
        this.this$0 = featuredBusinessServiceGrpc;
        super(1, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        return new FeaturedBusinessServiceGrpc$getFeaturedBusinesses$2(this.this$0, continuation);
    }

    public final Object invoke(Object obj) {
        return ((FeaturedBusinessServiceGrpc$getFeaturedBusinesses$2) create((Continuation) obj)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineContext access$getGrpcDispatcher$p = this.this$0.getGrpcDispatcher();
            Function2 featuredBusinessServiceGrpc$getFeaturedBusinesses$2$response$1 = new FeaturedBusinessServiceGrpc$getFeaturedBusinesses$2$response$1(this, null);
            this.label = 1;
            obj = BuildersKt.withContext(access$getGrpcDispatcher$p, featuredBusinessServiceGrpc$getFeaturedBusinesses$2$response$1, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        FeaturedBusinessResponse featuredBusinessResponse = (FeaturedBusinessResponse) obj;
        Intrinsics.checkExpressionValueIsNotNull(featuredBusinessResponse, "response");
        List listList = featuredBusinessResponse.getListList();
        Intrinsics.checkExpressionValueIsNotNull(listList, "response.listList");
        Iterable<FeaturedBusiness> iterable = listList;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (FeaturedBusiness featuredBusiness : iterable) {
            Intrinsics.checkExpressionValueIsNotNull(featuredBusiness, "business");
            int index = featuredBusiness.getIndex();
            String id = featuredBusiness.getId();
            Intrinsics.checkExpressionValueIsNotNull(id, "business.id");
            String title = featuredBusiness.getTitle();
            Intrinsics.checkExpressionValueIsNotNull(title, "business.title");
            String shortDescription = featuredBusiness.getShortDescription();
            Intrinsics.checkExpressionValueIsNotNull(shortDescription, "business.shortDescription");
            String url = featuredBusiness.getUrl();
            Intrinsics.checkExpressionValueIsNotNull(url, "business.url");
            String imageUrl = featuredBusiness.getImageUrl();
            Intrinsics.checkExpressionValueIsNotNull(imageUrl, "business.imageUrl");
            String logoImageUrl = featuredBusiness.getLogoImageUrl();
            Intrinsics.checkExpressionValueIsNotNull(logoImageUrl, "business.logoImageUrl");
            com.bitcoin.mwallet.core.models.discover.FeaturedBusiness featuredBusiness2 = new com.bitcoin.mwallet.core.models.discover.FeaturedBusiness(index, id, title, shortDescription, url, imageUrl, logoImageUrl);
            arrayList.add(featuredBusiness2);
        }
        return new GetFeaturedBusinessResponse((List) arrayList);
    }
}
