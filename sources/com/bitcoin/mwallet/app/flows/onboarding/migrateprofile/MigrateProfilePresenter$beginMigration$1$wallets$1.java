package com.bitcoin.mwallet.app.flows.onboarding.migrateprofile;

import com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.entity.OldWallet;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H@ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005"}, mo37405d2 = {"<anonymous>", "", "Lcom/bitcoin/mwallet/app/flows/onboarding/migrateprofile/entity/OldWallet;", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.MigrateProfilePresenter$beginMigration$1$wallets$1", mo38000f = "MigrateProfilePresenter.kt", mo38001i = {}, mo38002l = {52}, mo38003m = "invokeSuspend", mo38004n = {}, mo38005s = {})
/* compiled from: MigrateProfilePresenter.kt */
final class MigrateProfilePresenter$beginMigration$1$wallets$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends OldWallet>>, Object> {
    int label;

    /* renamed from: p$ */
    private CoroutineScope f255p$;
    final /* synthetic */ MigrateProfilePresenter$beginMigration$1 this$0;

    MigrateProfilePresenter$beginMigration$1$wallets$1(MigrateProfilePresenter$beginMigration$1 migrateProfilePresenter$beginMigration$1, Continuation continuation) {
        this.this$0 = migrateProfilePresenter$beginMigration$1;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        MigrateProfilePresenter$beginMigration$1$wallets$1 migrateProfilePresenter$beginMigration$1$wallets$1 = new MigrateProfilePresenter$beginMigration$1$wallets$1(this.this$0, continuation);
        migrateProfilePresenter$beginMigration$1$wallets$1.f255p$ = (CoroutineScope) obj;
        return migrateProfilePresenter$beginMigration$1$wallets$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((MigrateProfilePresenter$beginMigration$1$wallets$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f255p$;
            MigrateProfileInteractor interactor = this.this$0.this$0.getInteractor();
            this.label = 1;
            obj = interactor.readOldWallets(this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return obj;
    }
}
