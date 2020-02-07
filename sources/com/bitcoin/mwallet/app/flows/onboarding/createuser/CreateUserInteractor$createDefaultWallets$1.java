package com.bitcoin.mwallet.app.flows.onboarding.createuser;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H@ø\u0001\u0000"}, mo37405d2 = {"createDefaultWallets", "", "continuation", "Lkotlin/coroutines/Continuation;", ""}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.app.flows.onboarding.createuser.CreateUserInteractor", mo38000f = "CreateUserInteractor.kt", mo38001i = {0, 0, 1, 1}, mo38002l = {51, 52}, mo38003m = "createDefaultWallets", mo38004n = {"this", "start", "this", "start"}, mo38005s = {"L$0", "J$0", "L$0", "J$0"})
/* compiled from: CreateUserInteractor.kt */
final class CreateUserInteractor$createDefaultWallets$1 extends ContinuationImpl {
    long J$0;
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ CreateUserInteractor this$0;

    CreateUserInteractor$createDefaultWallets$1(CreateUserInteractor createUserInteractor, Continuation continuation) {
        this.this$0 = createUserInteractor;
        super(continuation);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.createDefaultWallets(this);
    }
}
