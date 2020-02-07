package com.bitcoin.mwallet.app.flows.onboarding.migrateprofile;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H@ø\u0001\u0000"}, mo37405d2 = {"migrateContacts", "", "oldContacts", "", "Lcom/bitcoin/mwallet/app/flows/onboarding/migrateprofile/entity/OldContact/OldContact;", "continuation", "Lkotlin/coroutines/Continuation;", ""}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.MigrateProfileInteractor", mo38000f = "MigrateProfileInteractor.kt", mo38001i = {0, 0, 0, 0, 0, 0}, mo38002l = {99}, mo38003m = "migrateContacts", mo38004n = {"this", "oldContacts", "$this$onEach$iv", "$this$apply$iv", "element$iv", "it"}, mo38005s = {"L$0", "L$1", "L$2", "L$4", "L$6", "L$7"})
/* compiled from: MigrateProfileInteractor.kt */
final class MigrateProfileInteractor$migrateContacts$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ MigrateProfileInteractor this$0;

    MigrateProfileInteractor$migrateContacts$1(MigrateProfileInteractor migrateProfileInteractor, Continuation continuation) {
        this.this$0 = migrateProfileInteractor;
        super(continuation);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.migrateContacts(null, this);
    }
}
