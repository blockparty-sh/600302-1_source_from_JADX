package com.bitcoin.mwallet.core.interactors;

import androidx.lifecycle.LiveData;
import com.bitcoin.mwallet.core.models.verifiedtoken.VerifiedToken;
import com.bitcoin.mwallet.core.repositories.VerifiedTokenRepository;
import com.bitcoin.mwallet.core.services.featuredbusiness.FeaturedBusinessService;
import com.bitcoin.mwallet.core.services.link.LinkService;
import com.bitcoin.mwallet.core.services.verifiedtoken.VerifiedTokenService;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH@ø\u0001\u0000¢\u0006\u0002\u0010\u000eJ\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\fH@ø\u0001\u0000¢\u0006\u0002\u0010\u000eJ\u0012\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\f0\u0012J\u0017\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00130\fH@ø\u0001\u0000¢\u0006\u0002\u0010\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u0015"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/interactors/DiscoverContentInteractor;", "", "featuredBusinessService", "Lcom/bitcoin/mwallet/core/services/featuredbusiness/FeaturedBusinessService;", "linkService", "Lcom/bitcoin/mwallet/core/services/link/LinkService;", "tokenService", "Lcom/bitcoin/mwallet/core/services/verifiedtoken/VerifiedTokenService;", "tokenRepository", "Lcom/bitcoin/mwallet/core/repositories/VerifiedTokenRepository;", "(Lcom/bitcoin/mwallet/core/services/featuredbusiness/FeaturedBusinessService;Lcom/bitcoin/mwallet/core/services/link/LinkService;Lcom/bitcoin/mwallet/core/services/verifiedtoken/VerifiedTokenService;Lcom/bitcoin/mwallet/core/repositories/VerifiedTokenRepository;)V", "getFeaturedBusinesses", "", "Lcom/bitcoin/mwallet/core/models/discover/FeaturedBusiness;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getLinks", "Lcom/bitcoin/mwallet/core/models/discover/Link;", "getVerifiedTokenStream", "Landroidx/lifecycle/LiveData;", "Lcom/bitcoin/mwallet/core/models/verifiedtoken/VerifiedToken;", "getVerifiedTokens", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: DiscoverContentInteractor.kt */
public final class DiscoverContentInteractor {
    private final FeaturedBusinessService featuredBusinessService;
    private final LinkService linkService;
    private final VerifiedTokenRepository tokenRepository;
    private final VerifiedTokenService tokenService;

    public DiscoverContentInteractor(@NotNull FeaturedBusinessService featuredBusinessService2, @NotNull LinkService linkService2, @NotNull VerifiedTokenService verifiedTokenService, @NotNull VerifiedTokenRepository verifiedTokenRepository) {
        Intrinsics.checkParameterIsNotNull(featuredBusinessService2, "featuredBusinessService");
        Intrinsics.checkParameterIsNotNull(linkService2, "linkService");
        Intrinsics.checkParameterIsNotNull(verifiedTokenService, "tokenService");
        Intrinsics.checkParameterIsNotNull(verifiedTokenRepository, "tokenRepository");
        this.featuredBusinessService = featuredBusinessService2;
        this.linkService = linkService2;
        this.tokenService = verifiedTokenService;
        this.tokenRepository = verifiedTokenRepository;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x004f  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object getFeaturedBusinesses(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.util.List<com.bitcoin.mwallet.core.models.discover.FeaturedBusiness>> r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof com.bitcoin.mwallet.core.interactors.DiscoverContentInteractor$getFeaturedBusinesses$1
            if (r0 == 0) goto L_0x0014
            r0 = r5
            com.bitcoin.mwallet.core.interactors.DiscoverContentInteractor$getFeaturedBusinesses$1 r0 = (com.bitcoin.mwallet.core.interactors.DiscoverContentInteractor$getFeaturedBusinesses$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r5 = r0.label
            int r5 = r5 - r2
            r0.label = r5
            goto L_0x0019
        L_0x0014:
            com.bitcoin.mwallet.core.interactors.DiscoverContentInteractor$getFeaturedBusinesses$1 r0 = new com.bitcoin.mwallet.core.interactors.DiscoverContentInteractor$getFeaturedBusinesses$1
            r0.<init>(r4, r5)
        L_0x0019:
            java.lang.Object r5 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0036
            if (r2 != r3) goto L_0x002e
            java.lang.Object r0 = r0.L$0
            com.bitcoin.mwallet.core.interactors.DiscoverContentInteractor r0 = (com.bitcoin.mwallet.core.interactors.DiscoverContentInteractor) r0
            kotlin.ResultKt.throwOnFailure(r5)
            goto L_0x0046
        L_0x002e:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L_0x0036:
            kotlin.ResultKt.throwOnFailure(r5)
            com.bitcoin.mwallet.core.services.featuredbusiness.FeaturedBusinessService r5 = r4.featuredBusinessService
            r0.L$0 = r4
            r0.label = r3
            java.lang.Object r5 = r5.getFeaturedBusinesses(r0)
            if (r5 != r1) goto L_0x0046
            return r1
        L_0x0046:
            com.bitcoin.mwallet.core.services.featuredbusiness.GetFeaturedBusinessResponse r5 = (com.bitcoin.mwallet.core.services.featuredbusiness.GetFeaturedBusinessResponse) r5
            java.util.List r5 = r5.getList()
            if (r5 == 0) goto L_0x004f
            goto L_0x0053
        L_0x004f:
            java.util.List r5 = kotlin.collections.CollectionsKt.emptyList()
        L_0x0053:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.core.interactors.DiscoverContentInteractor.getFeaturedBusinesses(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x004f  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object getLinks(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.util.List<com.bitcoin.mwallet.core.models.discover.Link>> r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof com.bitcoin.mwallet.core.interactors.DiscoverContentInteractor$getLinks$1
            if (r0 == 0) goto L_0x0014
            r0 = r5
            com.bitcoin.mwallet.core.interactors.DiscoverContentInteractor$getLinks$1 r0 = (com.bitcoin.mwallet.core.interactors.DiscoverContentInteractor$getLinks$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r5 = r0.label
            int r5 = r5 - r2
            r0.label = r5
            goto L_0x0019
        L_0x0014:
            com.bitcoin.mwallet.core.interactors.DiscoverContentInteractor$getLinks$1 r0 = new com.bitcoin.mwallet.core.interactors.DiscoverContentInteractor$getLinks$1
            r0.<init>(r4, r5)
        L_0x0019:
            java.lang.Object r5 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0036
            if (r2 != r3) goto L_0x002e
            java.lang.Object r0 = r0.L$0
            com.bitcoin.mwallet.core.interactors.DiscoverContentInteractor r0 = (com.bitcoin.mwallet.core.interactors.DiscoverContentInteractor) r0
            kotlin.ResultKt.throwOnFailure(r5)
            goto L_0x0046
        L_0x002e:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L_0x0036:
            kotlin.ResultKt.throwOnFailure(r5)
            com.bitcoin.mwallet.core.services.link.LinkService r5 = r4.linkService
            r0.L$0 = r4
            r0.label = r3
            java.lang.Object r5 = r5.getLinks(r0)
            if (r5 != r1) goto L_0x0046
            return r1
        L_0x0046:
            com.bitcoin.mwallet.core.services.link.GetLinksResponse r5 = (com.bitcoin.mwallet.core.services.link.GetLinksResponse) r5
            java.util.List r5 = r5.getLinks()
            if (r5 == 0) goto L_0x004f
            goto L_0x0053
        L_0x004f:
            java.util.List r5 = kotlin.collections.CollectionsKt.emptyList()
        L_0x0053:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.core.interactors.DiscoverContentInteractor.getLinks(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x004f  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object getVerifiedTokens(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.util.List<com.bitcoin.mwallet.core.models.verifiedtoken.VerifiedToken>> r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof com.bitcoin.mwallet.core.interactors.DiscoverContentInteractor$getVerifiedTokens$1
            if (r0 == 0) goto L_0x0014
            r0 = r5
            com.bitcoin.mwallet.core.interactors.DiscoverContentInteractor$getVerifiedTokens$1 r0 = (com.bitcoin.mwallet.core.interactors.DiscoverContentInteractor$getVerifiedTokens$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r5 = r0.label
            int r5 = r5 - r2
            r0.label = r5
            goto L_0x0019
        L_0x0014:
            com.bitcoin.mwallet.core.interactors.DiscoverContentInteractor$getVerifiedTokens$1 r0 = new com.bitcoin.mwallet.core.interactors.DiscoverContentInteractor$getVerifiedTokens$1
            r0.<init>(r4, r5)
        L_0x0019:
            java.lang.Object r5 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0036
            if (r2 != r3) goto L_0x002e
            java.lang.Object r0 = r0.L$0
            com.bitcoin.mwallet.core.interactors.DiscoverContentInteractor r0 = (com.bitcoin.mwallet.core.interactors.DiscoverContentInteractor) r0
            kotlin.ResultKt.throwOnFailure(r5)
            goto L_0x0046
        L_0x002e:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L_0x0036:
            kotlin.ResultKt.throwOnFailure(r5)
            com.bitcoin.mwallet.core.services.verifiedtoken.VerifiedTokenService r5 = r4.tokenService
            r0.L$0 = r4
            r0.label = r3
            java.lang.Object r5 = r5.getVerifiedTokens(r0)
            if (r5 != r1) goto L_0x0046
            return r1
        L_0x0046:
            com.bitcoin.mwallet.core.services.verifiedtoken.VerifiedTokensResponse r5 = (com.bitcoin.mwallet.core.services.verifiedtoken.VerifiedTokensResponse) r5
            java.util.List r5 = r5.getList()
            if (r5 == 0) goto L_0x004f
            goto L_0x0053
        L_0x004f:
            java.util.List r5 = kotlin.collections.CollectionsKt.emptyList()
        L_0x0053:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.core.interactors.DiscoverContentInteractor.getVerifiedTokens(kotlin.coroutines.Continuation):java.lang.Object");
    }

    @NotNull
    public final LiveData<List<VerifiedToken>> getVerifiedTokenStream() {
        return this.tokenRepository.verifiedTokens();
    }
}
