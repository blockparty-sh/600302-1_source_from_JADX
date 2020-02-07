package com.bitcoin.mwallet.app.flows.onboarding.createuser;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\u0010\u0000\u001a\u0004\u0018\u00010\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H@ø\u0001\u0000"}, mo37405d2 = {"scanAndRestoreZionWallets", "", "continuation", "Lkotlin/coroutines/Continuation;", ""}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.app.flows.onboarding.createuser.CreateUserInteractor", mo38000f = "CreateUserInteractor.kt", mo38001i = {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 3, 3, 3}, mo38002l = {30, 34, 44, 45}, mo38003m = "scanAndRestoreZionWallets", mo38004n = {"this", "this", "zionWallets", "xPubs", "$this$forEach$iv", "element$iv", "$dstr$zionId$prefix", "zionId", "prefix", "$this$forEach$iv", "element$iv", "coin", "this", "zionWallets", "xPubs", "this", "zionWallets", "xPubs"}, mo38005s = {"L$0", "L$0", "L$1", "L$2", "L$3", "L$5", "L$6", "L$7", "L$8", "L$9", "L$11", "L$12", "L$0", "L$1", "L$2", "L$0", "L$1", "L$2"})
/* compiled from: CreateUserInteractor.kt */
final class CreateUserInteractor$scanAndRestoreZionWallets$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$10;
    Object L$11;
    Object L$12;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    Object L$8;
    Object L$9;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ CreateUserInteractor this$0;

    CreateUserInteractor$scanAndRestoreZionWallets$1(CreateUserInteractor createUserInteractor, Continuation continuation) {
        this.this$0 = createUserInteractor;
        super(continuation);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.scanAndRestoreZionWallets(this);
    }
}
