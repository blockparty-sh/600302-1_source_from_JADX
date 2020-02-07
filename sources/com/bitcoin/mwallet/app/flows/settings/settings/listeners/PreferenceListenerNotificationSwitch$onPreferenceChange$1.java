package com.bitcoin.mwallet.app.flows.settings.settings.listeners;

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
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.app.flows.settings.settings.listeners.PreferenceListenerNotificationSwitch$onPreferenceChange$1", mo38000f = "PreferenceListenerNotificationSwitch.kt", mo38001i = {0, 1, 1, 1, 2, 2, 2, 3, 3, 3}, mo38002l = {38, 51, 52, 59}, mo38003m = "invokeSuspend", mo38004n = {"fbToken", "fbToken", "walletIds", "walletIdTokenMap", "fbToken", "walletIds", "walletIdTokenMap", "fbToken", "walletIds", "walletIdTokenMap"}, mo38005s = {"L$0", "L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$0", "L$1", "L$2"})
/* compiled from: PreferenceListenerNotificationSwitch.kt */
final class PreferenceListenerNotificationSwitch$onPreferenceChange$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Object $newValue;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f316p$;
    final /* synthetic */ PreferenceListenerNotificationSwitch this$0;

    PreferenceListenerNotificationSwitch$onPreferenceChange$1(PreferenceListenerNotificationSwitch preferenceListenerNotificationSwitch, Object obj, Continuation continuation) {
        this.this$0 = preferenceListenerNotificationSwitch;
        this.$newValue = obj;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        PreferenceListenerNotificationSwitch$onPreferenceChange$1 preferenceListenerNotificationSwitch$onPreferenceChange$1 = new PreferenceListenerNotificationSwitch$onPreferenceChange$1(this.this$0, this.$newValue, continuation);
        preferenceListenerNotificationSwitch$onPreferenceChange$1.f316p$ = (CoroutineScope) obj;
        return preferenceListenerNotificationSwitch$onPreferenceChange$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((PreferenceListenerNotificationSwitch$onPreferenceChange$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:44:0x0155 A[Catch:{ IOException -> 0x0054 }, RETURN] */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r15) {
        /*
            r14 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r14.label
            r2 = 0
            r3 = 4
            r4 = 3
            r5 = 2
            r6 = 1
            if (r1 == 0) goto L_0x005f
            if (r1 == r6) goto L_0x0057
            if (r1 == r5) goto L_0x0042
            if (r1 == r4) goto L_0x0031
            if (r1 != r3) goto L_0x0029
            java.lang.Object r0 = r14.L$2
            java.util.Map r0 = (java.util.Map) r0
            java.lang.Object r0 = r14.L$1
            java.util.Set r0 = (java.util.Set) r0
            java.lang.Object r0 = r14.L$0
            java.lang.String r0 = (java.lang.String) r0
            kotlin.ResultKt.throwOnFailure(r15)     // Catch:{ IOException -> 0x0026 }
            goto L_0x018d
        L_0x0026:
            r15 = move-exception
            goto L_0x018a
        L_0x0029:
            java.lang.IllegalStateException r15 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r15.<init>(r0)
            throw r15
        L_0x0031:
            java.lang.Object r0 = r14.L$2
            java.util.Map r0 = (java.util.Map) r0
            java.lang.Object r0 = r14.L$1
            java.util.Set r0 = (java.util.Set) r0
            java.lang.Object r0 = r14.L$0
            java.lang.String r0 = (java.lang.String) r0
            kotlin.ResultKt.throwOnFailure(r15)     // Catch:{ IOException -> 0x0054 }
            goto L_0x0156
        L_0x0042:
            java.lang.Object r1 = r14.L$2
            java.util.Map r1 = (java.util.Map) r1
            java.lang.Object r3 = r14.L$1
            java.util.Set r3 = (java.util.Set) r3
            java.lang.Object r5 = r14.L$0
            java.lang.String r5 = (java.lang.String) r5
            kotlin.ResultKt.throwOnFailure(r15)     // Catch:{ IOException -> 0x0054 }
            r15 = r3
            goto L_0x0130
        L_0x0054:
            r15 = move-exception
            goto L_0x0171
        L_0x0057:
            java.lang.Object r1 = r14.L$0
            java.lang.String r1 = (java.lang.String) r1
            kotlin.ResultKt.throwOnFailure(r15)
            goto L_0x0089
        L_0x005f:
            kotlin.ResultKt.throwOnFailure(r15)
            kotlinx.coroutines.CoroutineScope r15 = r14.f316p$
            com.google.firebase.iid.FirebaseInstanceId r15 = com.google.firebase.iid.FirebaseInstanceId.getInstance()
            java.lang.String r1 = "432300239540"
            java.lang.String r7 = "FCM"
            java.lang.String r15 = r15.getToken(r1, r7)
            java.lang.String r1 = "FirebaseInstanceId.getIn…IREBASE_SENDER_ID, \"FCM\")"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r15, r1)
            com.bitcoin.mwallet.app.flows.settings.settings.listeners.PreferenceListenerNotificationSwitch r1 = r14.this$0
            com.bitcoin.mwallet.core.repositories.WalletRepository r1 = r1.walletRepository
            r14.L$0 = r15
            r14.label = r6
            java.lang.Object r1 = r1.getWallets(r14)
            if (r1 != r0) goto L_0x0086
            return r0
        L_0x0086:
            r13 = r1
            r1 = r15
            r15 = r13
        L_0x0089:
            java.lang.Iterable r15 = (java.lang.Iterable) r15
            java.util.Set r15 = kotlin.collections.CollectionsKt.toSet(r15)
            r6 = r15
            java.lang.Iterable r6 = (java.lang.Iterable) r6
            java.util.ArrayList r7 = new java.util.ArrayList
            r8 = 10
            int r9 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r6, r8)
            r7.<init>(r9)
            java.util.Collection r7 = (java.util.Collection) r7
            java.util.Iterator r6 = r6.iterator()
        L_0x00a3:
            boolean r9 = r6.hasNext()
            if (r9 == 0) goto L_0x00c8
            java.lang.Object r9 = r6.next()
            com.bitcoin.mwallet.core.models.wallet.Wallet r9 = (com.bitcoin.mwallet.core.models.wallet.C1261Wallet) r9
            com.bitcoin.mwallet.core.utils.SignedStub$SignedRequest r10 = new com.bitcoin.mwallet.core.utils.SignedStub$SignedRequest
            com.bitcoin.mwallet.core.models.wallet.WalletId r11 = r9.getId()
            com.bitcoin.mwallet.core.models.Copayers r12 = r9.getCopayers()
            com.bitcoin.mwallet.core.models.CopayerId r12 = r12.getWalletCopayerId()
            com.bitcoin.mwallet.core.utils.signature.SigningKey r9 = r9.getSigningKey()
            r10.<init>(r11, r12, r9)
            r7.add(r10)
            goto L_0x00a3
        L_0x00c8:
            java.util.List r7 = (java.util.List) r7
            java.lang.Iterable r7 = (java.lang.Iterable) r7
            java.util.ArrayList r6 = new java.util.ArrayList
            int r8 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r7, r8)
            r6.<init>(r8)
            java.util.Collection r6 = (java.util.Collection) r6
            java.util.Iterator r7 = r7.iterator()
        L_0x00db:
            boolean r8 = r7.hasNext()
            if (r8 == 0) goto L_0x00ef
            java.lang.Object r8 = r7.next()
            com.bitcoin.mwallet.core.utils.SignedStub$SignedRequest r8 = (com.bitcoin.mwallet.core.utils.SignedStub.SignedRequest) r8
            kotlin.Pair r8 = kotlin.TuplesKt.m309to(r8, r1)
            r6.add(r8)
            goto L_0x00db
        L_0x00ef:
            java.util.List r6 = (java.util.List) r6
            java.lang.Iterable r6 = (java.lang.Iterable) r6
            java.util.Map r6 = kotlin.collections.MapsKt.toMap(r6)
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r8 = "Fetching Firebase Token: "
            r7.append(r8)
            r7.append(r1)
            java.lang.String r7 = r7.toString()
            java.lang.Object[] r8 = new java.lang.Object[r2]
            timber.log.Timber.m426d(r7, r8)
            java.lang.Object r7 = r14.$newValue
            if (r7 == 0) goto L_0x0190
            java.lang.Boolean r7 = (java.lang.Boolean) r7
            boolean r7 = r7.booleanValue()
            if (r7 == 0) goto L_0x0175
            com.bitcoin.mwallet.app.flows.settings.settings.listeners.PreferenceListenerNotificationSwitch r3 = r14.this$0     // Catch:{ IOException -> 0x0054 }
            com.bitcoin.mwallet.core.services.notification.NotificationService r3 = r3.notificationServices     // Catch:{ IOException -> 0x0054 }
            r14.L$0 = r1     // Catch:{ IOException -> 0x0054 }
            r14.L$1 = r15     // Catch:{ IOException -> 0x0054 }
            r14.L$2 = r6     // Catch:{ IOException -> 0x0054 }
            r14.label = r5     // Catch:{ IOException -> 0x0054 }
            java.lang.Object r3 = r3.subscribe(r6, r14)     // Catch:{ IOException -> 0x0054 }
            if (r3 != r0) goto L_0x012e
            return r0
        L_0x012e:
            r5 = r1
            r1 = r6
        L_0x0130:
            com.bitcoin.mwallet.app.flows.settings.settings.listeners.PreferenceListenerNotificationSwitch r3 = r14.this$0     // Catch:{ IOException -> 0x0054 }
            com.bitcoin.mwallet.core.services.notification.NotificationService r3 = r3.notificationServices     // Catch:{ IOException -> 0x0054 }
            java.util.Set r6 = r1.keySet()     // Catch:{ IOException -> 0x0054 }
            java.util.Locale r7 = java.util.Locale.getDefault()     // Catch:{ IOException -> 0x0054 }
            java.lang.String r7 = r7.toString()     // Catch:{ IOException -> 0x0054 }
            java.lang.String r8 = "Locale.getDefault().toString()"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r7, r8)     // Catch:{ IOException -> 0x0054 }
            r14.L$0 = r5     // Catch:{ IOException -> 0x0054 }
            r14.L$1 = r15     // Catch:{ IOException -> 0x0054 }
            r14.L$2 = r1     // Catch:{ IOException -> 0x0054 }
            r14.label = r4     // Catch:{ IOException -> 0x0054 }
            java.lang.Object r15 = r3.registerRegion(r6, r7, r14)     // Catch:{ IOException -> 0x0054 }
            if (r15 != r0) goto L_0x0156
            return r0
        L_0x0156:
            java.lang.StringBuilder r15 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0054 }
            r15.<init>()     // Catch:{ IOException -> 0x0054 }
            java.lang.String r0 = "Registering region: "
            r15.append(r0)     // Catch:{ IOException -> 0x0054 }
            java.util.Locale r0 = java.util.Locale.getDefault()     // Catch:{ IOException -> 0x0054 }
            r15.append(r0)     // Catch:{ IOException -> 0x0054 }
            java.lang.String r15 = r15.toString()     // Catch:{ IOException -> 0x0054 }
            java.lang.Object[] r0 = new java.lang.Object[r2]     // Catch:{ IOException -> 0x0054 }
            timber.log.Timber.m426d(r15, r0)     // Catch:{ IOException -> 0x0054 }
            goto L_0x018d
        L_0x0171:
            r15.printStackTrace()
            goto L_0x018d
        L_0x0175:
            com.bitcoin.mwallet.app.flows.settings.settings.listeners.PreferenceListenerNotificationSwitch r2 = r14.this$0     // Catch:{ IOException -> 0x0026 }
            com.bitcoin.mwallet.core.services.notification.NotificationService r2 = r2.notificationServices     // Catch:{ IOException -> 0x0026 }
            r14.L$0 = r1     // Catch:{ IOException -> 0x0026 }
            r14.L$1 = r15     // Catch:{ IOException -> 0x0026 }
            r14.L$2 = r6     // Catch:{ IOException -> 0x0026 }
            r14.label = r3     // Catch:{ IOException -> 0x0026 }
            java.lang.Object r15 = r2.unSubscribe(r6, r14)     // Catch:{ IOException -> 0x0026 }
            if (r15 != r0) goto L_0x018d
            return r0
        L_0x018a:
            r15.printStackTrace()
        L_0x018d:
            kotlin.Unit r15 = kotlin.Unit.INSTANCE
            return r15
        L_0x0190:
            kotlin.TypeCastException r15 = new kotlin.TypeCastException
            java.lang.String r0 = "null cannot be cast to non-null type kotlin.Boolean"
            r15.<init>(r0)
            throw r15
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.app.flows.settings.settings.listeners.PreferenceListenerNotificationSwitch$onPreferenceChange$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
