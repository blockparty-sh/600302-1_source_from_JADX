package com.bitcoin.mwallet.core.services.link;

import com.bitcoin.mwallet.LinkOuterClass.Link;
import com.bitcoin.mwallet.LinkOuterClass.LinkResponse;
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

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H@ø\u0001\u0000¢\u0006\u0004\b\u0002\u0010\u0003"}, mo37405d2 = {"<anonymous>", "Lcom/bitcoin/mwallet/core/services/link/GetLinksResponse;", "invoke", "(Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.core.services.link.LinkServiceGrpc$getLinks$2", mo38000f = "LinkServiceGrpc.kt", mo38001i = {}, mo38002l = {25}, mo38003m = "invokeSuspend", mo38004n = {}, mo38005s = {})
/* compiled from: LinkServiceGrpc.kt */
final class LinkServiceGrpc$getLinks$2 extends SuspendLambda implements Function1<Continuation<? super GetLinksResponse>, Object> {
    int label;
    final /* synthetic */ LinkServiceGrpc this$0;

    LinkServiceGrpc$getLinks$2(LinkServiceGrpc linkServiceGrpc, Continuation continuation) {
        this.this$0 = linkServiceGrpc;
        super(1, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        return new LinkServiceGrpc$getLinks$2(this.this$0, continuation);
    }

    public final Object invoke(Object obj) {
        return ((LinkServiceGrpc$getLinks$2) create((Continuation) obj)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineContext access$getGrpcDispatcher$p = this.this$0.getGrpcDispatcher();
            Function2 linkServiceGrpc$getLinks$2$response$1 = new LinkServiceGrpc$getLinks$2$response$1(this, null);
            this.label = 1;
            obj = BuildersKt.withContext(access$getGrpcDispatcher$p, linkServiceGrpc$getLinks$2$response$1, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        LinkResponse linkResponse = (LinkResponse) obj;
        Intrinsics.checkExpressionValueIsNotNull(linkResponse, "response");
        List itemsList = linkResponse.getItemsList();
        Intrinsics.checkExpressionValueIsNotNull(itemsList, "response.itemsList");
        Iterable<Link> iterable = itemsList;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (Link link : iterable) {
            Intrinsics.checkExpressionValueIsNotNull(link, "link");
            int index = link.getIndex();
            String id = link.getId();
            Intrinsics.checkExpressionValueIsNotNull(id, "link.id");
            String title = link.getTitle();
            Intrinsics.checkExpressionValueIsNotNull(title, "link.title");
            String shortDescription = link.getShortDescription();
            Intrinsics.checkExpressionValueIsNotNull(shortDescription, "link.shortDescription");
            String url = link.getUrl();
            Intrinsics.checkExpressionValueIsNotNull(url, "link.url");
            String logoImageUrl = link.getLogoImageUrl();
            Intrinsics.checkExpressionValueIsNotNull(logoImageUrl, "link.logoImageUrl");
            com.bitcoin.mwallet.core.models.discover.Link link2 = new com.bitcoin.mwallet.core.models.discover.Link(index, id, title, shortDescription, url, logoImageUrl);
            arrayList.add(link2);
        }
        return new GetLinksResponse((List) arrayList);
    }
}
