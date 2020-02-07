package com.bitcoin.mwallet.app.flows.onboarding.migrateprofile;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, mo37405d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.MigrateProfilePresenter$beginMigration$1", mo38000f = "MigrateProfilePresenter.kt", mo38001i = {1, 2, 2, 3, 3}, mo38002l = {51, 55, 66, 69}, mo38003m = "invokeSuspend", mo38004n = {"wallets", "wallets", "contacts", "wallets", "contacts"}, mo38005s = {"L$0", "L$0", "L$1", "L$0", "L$1"})
/* compiled from: MigrateProfilePresenter.kt */
final class MigrateProfilePresenter$beginMigration$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    Object L$1;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f253p$;
    final /* synthetic */ MigrateProfilePresenter this$0;

    MigrateProfilePresenter$beginMigration$1(MigrateProfilePresenter migrateProfilePresenter, Continuation continuation) {
        this.this$0 = migrateProfilePresenter;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        MigrateProfilePresenter$beginMigration$1 migrateProfilePresenter$beginMigration$1 = new MigrateProfilePresenter$beginMigration$1(this.this$0, continuation);
        migrateProfilePresenter$beginMigration$1.f253p$ = (CoroutineScope) obj;
        return migrateProfilePresenter$beginMigration$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((MigrateProfilePresenter$beginMigration$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:37:0x00b9 A[Catch:{ Exception -> 0x0045 }] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00d6 A[Catch:{ Exception -> 0x0045 }] */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r12) {
        /*
            r11 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r11.label
            r2 = 4
            r3 = 3
            r4 = 2
            r5 = 0
            r6 = 1
            if (r1 == 0) goto L_0x0048
            if (r1 == r6) goto L_0x0041
            if (r1 == r4) goto L_0x0039
            if (r1 == r3) goto L_0x002a
            if (r1 != r2) goto L_0x0022
            java.lang.Object r0 = r11.L$1
            java.util.List r0 = (java.util.List) r0
            java.lang.Object r0 = r11.L$0
            java.util.List r0 = (java.util.List) r0
            kotlin.ResultKt.throwOnFailure(r12)     // Catch:{ Exception -> 0x0045 }
            goto L_0x00e9
        L_0x0022:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r0)
            throw r12
        L_0x002a:
            java.lang.Object r1 = r11.L$1
            java.util.List r1 = (java.util.List) r1
            java.lang.Object r3 = r11.L$0
            java.util.List r3 = (java.util.List) r3
            kotlin.ResultKt.throwOnFailure(r12)     // Catch:{ Exception -> 0x0045 }
            r12 = r1
            r1 = r3
            goto L_0x00cc
        L_0x0039:
            java.lang.Object r1 = r11.L$0
            java.util.List r1 = (java.util.List) r1
            kotlin.ResultKt.throwOnFailure(r12)     // Catch:{ Exception -> 0x0045 }
            goto L_0x0080
        L_0x0041:
            kotlin.ResultKt.throwOnFailure(r12)     // Catch:{ Exception -> 0x0045 }
            goto L_0x0063
        L_0x0045:
            r12 = move-exception
            goto L_0x0119
        L_0x0048:
            kotlin.ResultKt.throwOnFailure(r12)
            kotlinx.coroutines.CoroutineScope r12 = r11.f253p$
            kotlinx.coroutines.CoroutineDispatcher r12 = kotlinx.coroutines.Dispatchers.getIO()     // Catch:{ Exception -> 0x0045 }
            kotlin.coroutines.CoroutineContext r12 = (kotlin.coroutines.CoroutineContext) r12     // Catch:{ Exception -> 0x0045 }
            com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.MigrateProfilePresenter$beginMigration$1$wallets$1 r1 = new com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.MigrateProfilePresenter$beginMigration$1$wallets$1     // Catch:{ Exception -> 0x0045 }
            r1.<init>(r11, r5)     // Catch:{ Exception -> 0x0045 }
            kotlin.jvm.functions.Function2 r1 = (kotlin.jvm.functions.Function2) r1     // Catch:{ Exception -> 0x0045 }
            r11.label = r6     // Catch:{ Exception -> 0x0045 }
            java.lang.Object r12 = kotlinx.coroutines.BuildersKt.withContext(r12, r1, r11)     // Catch:{ Exception -> 0x0045 }
            if (r12 != r0) goto L_0x0063
            return r0
        L_0x0063:
            java.util.List r12 = (java.util.List) r12     // Catch:{ Exception -> 0x0045 }
            kotlinx.coroutines.CoroutineDispatcher r1 = kotlinx.coroutines.Dispatchers.getIO()     // Catch:{ Exception -> 0x0045 }
            kotlin.coroutines.CoroutineContext r1 = (kotlin.coroutines.CoroutineContext) r1     // Catch:{ Exception -> 0x0045 }
            com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.MigrateProfilePresenter$beginMigration$1$contacts$1 r7 = new com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.MigrateProfilePresenter$beginMigration$1$contacts$1     // Catch:{ Exception -> 0x0045 }
            r7.<init>(r11, r5)     // Catch:{ Exception -> 0x0045 }
            kotlin.jvm.functions.Function2 r7 = (kotlin.jvm.functions.Function2) r7     // Catch:{ Exception -> 0x0045 }
            r11.L$0 = r12     // Catch:{ Exception -> 0x0045 }
            r11.label = r4     // Catch:{ Exception -> 0x0045 }
            java.lang.Object r1 = kotlinx.coroutines.BuildersKt.withContext(r1, r7, r11)     // Catch:{ Exception -> 0x0045 }
            if (r1 != r0) goto L_0x007d
            return r0
        L_0x007d:
            r10 = r1
            r1 = r12
            r12 = r10
        L_0x0080:
            java.util.List r12 = (java.util.List) r12     // Catch:{ Exception -> 0x0045 }
            boolean r7 = r1.isEmpty()     // Catch:{ Exception -> 0x0045 }
            if (r7 == 0) goto L_0x009a
            boolean r7 = r12.isEmpty()     // Catch:{ Exception -> 0x0045 }
            if (r7 == 0) goto L_0x009a
            com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.MigrateProfilePresenter r12 = r11.this$0     // Catch:{ Exception -> 0x0045 }
            com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.MigrateProfileRouter r12 = com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.MigrateProfilePresenter.access$getRouter$p(r12)     // Catch:{ Exception -> 0x0045 }
            r12.toCreateUser()     // Catch:{ Exception -> 0x0045 }
            kotlin.Unit r12 = kotlin.Unit.INSTANCE     // Catch:{ Exception -> 0x0045 }
            return r12
        L_0x009a:
            com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.MigrateProfilePresenter r7 = r11.this$0     // Catch:{ Exception -> 0x0045 }
            androidx.lifecycle.MutableLiveData r7 = r7._migrationStatus     // Catch:{ Exception -> 0x0045 }
            com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.MigrateProfilePresenter r8 = r11.this$0     // Catch:{ Exception -> 0x0045 }
            com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.entity.MigrationStatus r8 = r8.migrationState     // Catch:{ Exception -> 0x0045 }
            com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.entity.MigrationStatus$Migration r9 = com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.entity.MigrationStatus.Migration.MIGRATING     // Catch:{ Exception -> 0x0045 }
            com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.entity.MigrationStatus r4 = com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.entity.MigrationStatus.copy$default(r8, r9, r5, r4, r5)     // Catch:{ Exception -> 0x0045 }
            r7.postValue(r4)     // Catch:{ Exception -> 0x0045 }
            r4 = r1
            java.util.Collection r4 = (java.util.Collection) r4     // Catch:{ Exception -> 0x0045 }
            boolean r4 = r4.isEmpty()     // Catch:{ Exception -> 0x0045 }
            r4 = r4 ^ r6
            if (r4 == 0) goto L_0x00cc
            com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.MigrateProfilePresenter r4 = r11.this$0     // Catch:{ Exception -> 0x0045 }
            com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.MigrateProfileInteractor r4 = r4.getInteractor()     // Catch:{ Exception -> 0x0045 }
            r11.L$0 = r1     // Catch:{ Exception -> 0x0045 }
            r11.L$1 = r12     // Catch:{ Exception -> 0x0045 }
            r11.label = r3     // Catch:{ Exception -> 0x0045 }
            java.lang.Object r3 = r4.migrateWallets(r1, r11)     // Catch:{ Exception -> 0x0045 }
            if (r3 != r0) goto L_0x00cc
            return r0
        L_0x00cc:
            r3 = r12
            java.util.Collection r3 = (java.util.Collection) r3     // Catch:{ Exception -> 0x0045 }
            boolean r3 = r3.isEmpty()     // Catch:{ Exception -> 0x0045 }
            r3 = r3 ^ r6
            if (r3 == 0) goto L_0x00e9
            com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.MigrateProfilePresenter r3 = r11.this$0     // Catch:{ Exception -> 0x0045 }
            com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.MigrateProfileInteractor r3 = r3.getInteractor()     // Catch:{ Exception -> 0x0045 }
            r11.L$0 = r1     // Catch:{ Exception -> 0x0045 }
            r11.L$1 = r12     // Catch:{ Exception -> 0x0045 }
            r11.label = r2     // Catch:{ Exception -> 0x0045 }
            java.lang.Object r12 = r3.migrateContacts(r12, r11)     // Catch:{ Exception -> 0x0045 }
            if (r12 != r0) goto L_0x00e9
            return r0
        L_0x00e9:
            com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.entity.MigrationStatus r12 = new com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.entity.MigrationStatus     // Catch:{ Exception -> 0x0045 }
            com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.entity.MigrationStatus$Migration r0 = com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.entity.MigrationStatus.Migration.DONE     // Catch:{ Exception -> 0x0045 }
            com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.MigrateProfilePresenter r1 = r11.this$0     // Catch:{ Exception -> 0x0045 }
            android.content.Context r1 = r1.context     // Catch:{ Exception -> 0x0045 }
            r2 = 2131951858(0x7f1300f2, float:1.9540142E38)
            java.lang.String r1 = r1.getString(r2)     // Catch:{ Exception -> 0x0045 }
            java.lang.String r2 = "context.getString(R.string.migration_successful)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r2)     // Catch:{ Exception -> 0x0045 }
            r12.<init>(r0, r1)     // Catch:{ Exception -> 0x0045 }
            com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.MigrateProfilePresenter r0 = r11.this$0     // Catch:{ Exception -> 0x0045 }
            androidx.lifecycle.MutableLiveData r0 = r0._migrationStatus     // Catch:{ Exception -> 0x0045 }
            r0.postValue(r12)     // Catch:{ Exception -> 0x0045 }
            com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.MigrateProfilePresenter r12 = r11.this$0     // Catch:{ Exception -> 0x0045 }
            androidx.lifecycle.MutableLiveData r12 = r12.getMigrationComplete()     // Catch:{ Exception -> 0x0045 }
            java.lang.Boolean r0 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r6)     // Catch:{ Exception -> 0x0045 }
            r12.postValue(r0)     // Catch:{ Exception -> 0x0045 }
            goto L_0x0154
        L_0x0119:
            r0 = r12
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            r1 = 0
            java.lang.Object[] r1 = new java.lang.Object[r1]
            java.lang.String r2 = "Error migrating wallets"
            timber.log.Timber.m431e(r0, r2, r1)
            com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.entity.MigrationStatus r0 = new com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.entity.MigrationStatus
            com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.entity.MigrationStatus$Migration r1 = com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.entity.MigrationStatus.Migration.DONE
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Migration Error: "
            r2.append(r3)
            r2.append(r12)
            java.lang.String r12 = "!\n"
            r2.append(r12)
            java.lang.String r12 = " Please Contact Support for instructions for manual Migration \n"
            r2.append(r12)
            java.lang.String r12 = "DO NOT DELETE YOUR WALLET OR CLEAR YOUR CACHE"
            r2.append(r12)
            java.lang.String r12 = r2.toString()
            r0.<init>(r1, r12)
            com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.MigrateProfilePresenter r12 = r11.this$0
            androidx.lifecycle.MutableLiveData r12 = r12._migrationStatus
            r12.postValue(r0)
        L_0x0154:
            kotlin.Unit r12 = kotlin.Unit.INSTANCE
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.MigrateProfilePresenter$beginMigration$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
