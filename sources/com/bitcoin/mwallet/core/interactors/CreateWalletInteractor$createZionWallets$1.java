package com.bitcoin.mwallet.core.interactors;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000 \n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH@ø\u0001\u0000"}, mo37405d2 = {"createZionWallets", "", "xPub", "", "Lcom/bitcoin/mwallet/zion/ZionXPub;", "recoveredWallets", "", "continuation", "Lkotlin/coroutines/Continuation;", ""}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.core.interactors.CreateWalletInteractor", mo38000f = "CreateWalletInteractor.kt", mo38001i = {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1}, mo38002l = {177, 179}, mo38003m = "createZionWallets", mo38004n = {"this", "xPub", "recoveredWallets", "$this$forEach$iv", "element$iv", "it", "wallet", "saveType", "this", "xPub", "recoveredWallets"}, mo38005s = {"L$0", "L$1", "Z$0", "L$2", "L$4", "L$5", "L$6", "L$7", "L$0", "L$1", "Z$0"})
/* compiled from: CreateWalletInteractor.kt */
final class CreateWalletInteractor$createZionWallets$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    boolean Z$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ CreateWalletInteractor this$0;

    CreateWalletInteractor$createZionWallets$1(CreateWalletInteractor createWalletInteractor, Continuation continuation) {
        this.this$0 = createWalletInteractor;
        super(continuation);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.createZionWallets(null, false, this);
    }
}
