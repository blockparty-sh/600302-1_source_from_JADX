package com.bitcoin.mwallet.core.repositories;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H@ø\u0001\u0000"}, mo37405d2 = {"refreshVerifiedTokens", "", "continuation", "Lkotlin/coroutines/Continuation;", ""}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.core.repositories.VerifiedTokenRepository", mo38000f = "VerifiedTokenRepository.kt", mo38001i = {0, 1, 1, 1}, mo38002l = {22, 27}, mo38003m = "refreshVerifiedTokens", mo38004n = {"this", "this", "tokensResponse", "allTokens"}, mo38005s = {"L$0", "L$0", "L$1", "L$2"})
/* compiled from: VerifiedTokenRepository.kt */
final class VerifiedTokenRepository$refreshVerifiedTokens$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ VerifiedTokenRepository this$0;

    VerifiedTokenRepository$refreshVerifiedTokens$1(VerifiedTokenRepository verifiedTokenRepository, Continuation continuation) {
        this.this$0 = verifiedTokenRepository;
        super(continuation);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.refreshVerifiedTokens(this);
    }
}
