package com.bitcoin.mwallet.app.flows.onboarding.migrateprofile;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H@ø\u0001\u0000"}, mo37405d2 = {"migrateWallets", "", "oldWallets", "", "Lcom/bitcoin/mwallet/app/flows/onboarding/migrateprofile/entity/OldWallet;", "continuation", "Lkotlin/coroutines/Continuation;", ""}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.MigrateProfileInteractor", mo38000f = "MigrateProfileInteractor.kt", mo38001i = {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5}, mo38002l = {115, 130, 131, 133, 138, 139}, mo38003m = "migrateWallets", mo38004n = {"this", "oldWallets", "validWallets", "copayerIdSharedEncyptionKey", "$this$onEach$iv", "$this$apply$iv", "element$iv", "it", "this", "oldWallets", "validWallets", "copayerIdSharedEncyptionKey", "newWallets", "this", "oldWallets", "validWallets", "copayerIdSharedEncyptionKey", "newWallets", "$this$map$iv", "$this$mapTo$iv$iv", "destination$iv$iv", "item$iv$iv", "wallet", "this", "oldWallets", "validWallets", "copayerIdSharedEncyptionKey", "newWallets", "migratedWallets", "this", "oldWallets", "validWallets", "copayerIdSharedEncyptionKey", "newWallets", "this", "oldWallets", "validWallets", "copayerIdSharedEncyptionKey", "newWallets"}, mo38005s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$6", "L$8", "L$9", "L$0", "L$1", "L$2", "L$3", "L$4", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$9", "L$10", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$0", "L$1", "L$2", "L$3", "L$4", "L$0", "L$1", "L$2", "L$3", "L$4"})
/* compiled from: MigrateProfileInteractor.kt */
final class MigrateProfileInteractor$migrateWallets$1 extends ContinuationImpl {
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
    final /* synthetic */ MigrateProfileInteractor this$0;

    MigrateProfileInteractor$migrateWallets$1(MigrateProfileInteractor migrateProfileInteractor, Continuation continuation) {
        this.this$0 = migrateProfileInteractor;
        super(continuation);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.migrateWallets(null, this);
    }
}
