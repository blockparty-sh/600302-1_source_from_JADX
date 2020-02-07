package com.bitcoin.mwallet.core.services.verifiedtoken;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0011\u0010\u0002\u001a\u00020\u0003H¦@ø\u0001\u0000¢\u0006\u0002\u0010\u0004\u0002\u0004\n\u0002\b\u0019¨\u0006\u0005"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/services/verifiedtoken/VerifiedTokenService;", "", "getVerifiedTokens", "Lcom/bitcoin/mwallet/core/services/verifiedtoken/VerifiedTokensResponse;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: VerifiedTokenService.kt */
public interface VerifiedTokenService {
    @Nullable
    Object getVerifiedTokens(@NotNull Continuation<? super VerifiedTokensResponse> continuation);
}
