package com.bitcoin.mwallet.app.flows.onboarding.migrateprofile;

import com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.entity.OldContact.C1098OldContact;
import com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.entity.OldContact.C1098OldContact.OldContactJsonSource;
import com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.entity.OldProfile;
import com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.entity.OldWallet.OldWalletsJsonSource;
import com.bitcoin.mwallet.core.models.credential.Credential;
import com.bitcoin.mwallet.core.models.wallet.C1261Wallet;
import com.bitcoin.mwallet.core.repositories.ContactRepository;
import com.bitcoin.mwallet.core.repositories.UserRepository;
import com.bitcoin.mwallet.core.repositories.WalletRepository;
import com.bitcoin.mwallet.core.services.AnalyticsService;
import com.bitcoin.mwallet.core.services.WalletRefresherTemp;
import com.bitcoin.mwallet.core.services.eventstream.EventStreamHandler;
import com.bitcoin.mwallet.core.services.p010tx.TxService;
import com.squareup.moshi.JsonAdapter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001BO\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013¢\u0006\u0002\u0010\u0014J\u001f\u0010 \u001a\u00020!2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00170#H@ø\u0001\u0000¢\u0006\u0002\u0010$J?\u0010%\u001a\u00020!2\u0018\u0010&\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020)0'0#2\u0012\u0010*\u001a\u000e\u0012\u0004\u0012\u00020,\u0012\u0004\u0012\u00020,0+H@ø\u0001\u0000¢\u0006\u0002\u0010-J\u001f\u0010.\u001a\u00020!2\f\u0010/\u001a\b\u0012\u0004\u0012\u0002000#H@ø\u0001\u0000¢\u0006\u0002\u0010$J\u001a\u00101\u001a\u000e\u0012\u0004\u0012\u00020,\u0012\u0004\u0012\u00020,0+2\u0006\u00102\u001a\u00020,J\u0017\u00103\u001a\b\u0012\u0004\u0012\u00020\u00170#H@ø\u0001\u0000¢\u0006\u0002\u00104J\u0017\u00105\u001a\b\u0012\u0004\u0012\u0002000#H@ø\u0001\u0000¢\u0006\u0002\u00104R\u000e\u0010\f\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0004¢\u0006\u0002\n\u0000R!\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u00168BX\u0002¢\u0006\f\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u0018\u0010\u0019R!\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001d0\u00168BX\u0002¢\u0006\f\n\u0004\b\u001f\u0010\u001b\u001a\u0004\b\u001e\u0010\u0019R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u00066"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/onboarding/migrateprofile/MigrateProfileInteractor;", "", "oldWalletJson", "Lcom/bitcoin/mwallet/app/flows/onboarding/migrateprofile/entity/OldWallet$OldWalletsJsonSource;", "oldContactJson", "Lcom/bitcoin/mwallet/app/flows/onboarding/migrateprofile/entity/OldContact/OldContact$OldContactJsonSource;", "userService", "Lcom/bitcoin/mwallet/core/repositories/UserRepository;", "walletService", "Lcom/bitcoin/mwallet/core/repositories/WalletRepository;", "contactService", "Lcom/bitcoin/mwallet/core/repositories/ContactRepository;", "analyticsService", "Lcom/bitcoin/mwallet/core/services/AnalyticsService;", "eventStreamHandler", "Lcom/bitcoin/mwallet/core/services/eventstream/EventStreamHandler;", "txService", "Lcom/bitcoin/mwallet/core/services/tx/TxService;", "walletRefresherTemp", "Lcom/bitcoin/mwallet/core/services/WalletRefresherTemp;", "(Lcom/bitcoin/mwallet/app/flows/onboarding/migrateprofile/entity/OldWallet$OldWalletsJsonSource;Lcom/bitcoin/mwallet/app/flows/onboarding/migrateprofile/entity/OldContact/OldContact$OldContactJsonSource;Lcom/bitcoin/mwallet/core/repositories/UserRepository;Lcom/bitcoin/mwallet/core/repositories/WalletRepository;Lcom/bitcoin/mwallet/core/repositories/ContactRepository;Lcom/bitcoin/mwallet/core/services/AnalyticsService;Lcom/bitcoin/mwallet/core/services/eventstream/EventStreamHandler;Lcom/bitcoin/mwallet/core/services/tx/TxService;Lcom/bitcoin/mwallet/core/services/WalletRefresherTemp;)V", "jsonContactAdapter", "Lcom/squareup/moshi/JsonAdapter;", "Lcom/bitcoin/mwallet/app/flows/onboarding/migrateprofile/entity/OldContact/OldContact;", "getJsonContactAdapter", "()Lcom/squareup/moshi/JsonAdapter;", "jsonContactAdapter$delegate", "Lkotlin/Lazy;", "jsonWalletAdapter", "Lcom/bitcoin/mwallet/app/flows/onboarding/migrateprofile/entity/OldProfile;", "getJsonWalletAdapter", "jsonWalletAdapter$delegate", "migrateContacts", "", "oldContacts", "", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "migrateNotes", "newWallets", "Lkotlin/Pair;", "Lcom/bitcoin/mwallet/core/models/wallet/Wallet;", "Lcom/bitcoin/mwallet/core/models/credential/Credential;", "copayerIdSharedEncyptionKey", "", "", "(Ljava/util/List;Ljava/util/Map;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "migrateWallets", "oldWallets", "Lcom/bitcoin/mwallet/app/flows/onboarding/migrateprofile/entity/OldWallet;", "parseContactJson", "contactJsonString", "readOldContact", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readOldWallets", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: MigrateProfileInteractor.kt */
public final class MigrateProfileInteractor {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(MigrateProfileInteractor.class), "jsonWalletAdapter", "getJsonWalletAdapter()Lcom/squareup/moshi/JsonAdapter;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(MigrateProfileInteractor.class), "jsonContactAdapter", "getJsonContactAdapter()Lcom/squareup/moshi/JsonAdapter;"))};
    private final AnalyticsService analyticsService;
    private final ContactRepository contactService;
    private final EventStreamHandler eventStreamHandler;
    private final Lazy jsonContactAdapter$delegate = LazyKt.lazy(MigrateProfileInteractor$jsonContactAdapter$2.INSTANCE);
    private final Lazy jsonWalletAdapter$delegate = LazyKt.lazy(MigrateProfileInteractor$jsonWalletAdapter$2.INSTANCE);
    private final OldContactJsonSource oldContactJson;
    private final OldWalletsJsonSource oldWalletJson;
    /* access modifiers changed from: private */
    public final TxService txService;
    private final UserRepository userService;
    private final WalletRefresherTemp walletRefresherTemp;
    private final WalletRepository walletService;

    private final JsonAdapter<C1098OldContact> getJsonContactAdapter() {
        Lazy lazy = this.jsonContactAdapter$delegate;
        KProperty kProperty = $$delegatedProperties[1];
        return (JsonAdapter) lazy.getValue();
    }

    private final JsonAdapter<OldProfile> getJsonWalletAdapter() {
        Lazy lazy = this.jsonWalletAdapter$delegate;
        KProperty kProperty = $$delegatedProperties[0];
        return (JsonAdapter) lazy.getValue();
    }

    public MigrateProfileInteractor(@NotNull OldWalletsJsonSource oldWalletsJsonSource, @NotNull OldContactJsonSource oldContactJsonSource, @NotNull UserRepository userRepository, @NotNull WalletRepository walletRepository, @NotNull ContactRepository contactRepository, @NotNull AnalyticsService analyticsService2, @NotNull EventStreamHandler eventStreamHandler2, @Nullable TxService txService2, @NotNull WalletRefresherTemp walletRefresherTemp2) {
        Intrinsics.checkParameterIsNotNull(oldWalletsJsonSource, "oldWalletJson");
        Intrinsics.checkParameterIsNotNull(oldContactJsonSource, "oldContactJson");
        Intrinsics.checkParameterIsNotNull(userRepository, "userService");
        Intrinsics.checkParameterIsNotNull(walletRepository, "walletService");
        Intrinsics.checkParameterIsNotNull(contactRepository, "contactService");
        Intrinsics.checkParameterIsNotNull(analyticsService2, "analyticsService");
        Intrinsics.checkParameterIsNotNull(eventStreamHandler2, "eventStreamHandler");
        Intrinsics.checkParameterIsNotNull(walletRefresherTemp2, "walletRefresherTemp");
        this.oldWalletJson = oldWalletsJsonSource;
        this.oldContactJson = oldContactJsonSource;
        this.userService = userRepository;
        this.walletService = walletRepository;
        this.contactService = contactRepository;
        this.analyticsService = analyticsService2;
        this.eventStreamHandler = eventStreamHandler2;
        this.txService = txService2;
        this.walletRefresherTemp = walletRefresherTemp2;
    }

    @NotNull
    public final Map<String, String> parseContactJson(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "contactJsonString");
        JSONObject jSONObject = new JSONObject(str);
        HashMap hashMap = new HashMap();
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            Object next = keys.next();
            if (next != null) {
                String str2 = (String) next;
                String string = jSONObject.getString(str2);
                Map map = hashMap;
                Intrinsics.checkExpressionValueIsNotNull(string, "value");
                map.put(str2, string);
            } else {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
            }
        }
        return hashMap;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x004b  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00db  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object readOldContact(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.util.List<com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.entity.OldContact.C1098OldContact>> r8) {
        /*
            r7 = this;
            boolean r0 = r8 instanceof com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.MigrateProfileInteractor$readOldContact$1
            if (r0 == 0) goto L_0x0014
            r0 = r8
            com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.MigrateProfileInteractor$readOldContact$1 r0 = (com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.MigrateProfileInteractor$readOldContact$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L_0x0019
        L_0x0014:
            com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.MigrateProfileInteractor$readOldContact$1 r0 = new com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.MigrateProfileInteractor$readOldContact$1
            r0.<init>(r7, r8)
        L_0x0019:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0036
            if (r2 != r3) goto L_0x002e
            java.lang.Object r0 = r0.L$0
            com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.MigrateProfileInteractor r0 = (com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.MigrateProfileInteractor) r0
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x0047
        L_0x002e:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L_0x0036:
            kotlin.ResultKt.throwOnFailure(r8)
            com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.entity.OldContact.OldContact$OldContactJsonSource r8 = r7.oldContactJson
            r0.L$0 = r7
            r0.label = r3
            java.lang.Object r8 = r8.read(r0)
            if (r8 != r1) goto L_0x0046
            return r1
        L_0x0046:
            r0 = r7
        L_0x0047:
            java.lang.String r8 = (java.lang.String) r8
            if (r8 == 0) goto L_0x00db
            java.util.Map r8 = r0.parseContactJson(r8)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "reading old contact: "
            r0.append(r1)
            java.util.Collection r1 = r8.values()
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r1 = 0
            java.lang.Object[] r1 = new java.lang.Object[r1]
            timber.log.Timber.m426d(r0, r1)
            java.util.Collection r8 = r8.values()     // Catch:{ RuntimeException -> 0x00d0 }
            java.lang.Iterable r8 = (java.lang.Iterable) r8     // Catch:{ RuntimeException -> 0x00d0 }
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ RuntimeException -> 0x00d0 }
            r1 = 10
            int r1 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r8, r1)     // Catch:{ RuntimeException -> 0x00d0 }
            r0.<init>(r1)     // Catch:{ RuntimeException -> 0x00d0 }
            java.util.Collection r0 = (java.util.Collection) r0     // Catch:{ RuntimeException -> 0x00d0 }
            java.util.Iterator r8 = r8.iterator()     // Catch:{ RuntimeException -> 0x00d0 }
        L_0x0081:
            boolean r1 = r8.hasNext()     // Catch:{ RuntimeException -> 0x00d0 }
            if (r1 == 0) goto L_0x00c7
            java.lang.Object r1 = r8.next()     // Catch:{ RuntimeException -> 0x00d0 }
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ RuntimeException -> 0x00d0 }
            org.json.JSONObject r2 = new org.json.JSONObject     // Catch:{ RuntimeException -> 0x00d0 }
            r2.<init>(r1)     // Catch:{ RuntimeException -> 0x00d0 }
            com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.entity.OldContact.OldContact r1 = new com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.entity.OldContact.OldContact     // Catch:{ RuntimeException -> 0x00d0 }
            java.lang.String r3 = "name"
            java.lang.String r3 = r2.getString(r3)     // Catch:{ RuntimeException -> 0x00d0 }
            java.lang.String r4 = "jsonParser.getString(\"name\")"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r3, r4)     // Catch:{ RuntimeException -> 0x00d0 }
            java.lang.String r4 = "email"
            java.lang.String r4 = r2.getString(r4)     // Catch:{ RuntimeException -> 0x00d0 }
            java.lang.String r5 = "jsonParser.getString(\"email\")"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r4, r5)     // Catch:{ RuntimeException -> 0x00d0 }
            java.lang.String r5 = "address"
            java.lang.String r5 = r2.getString(r5)     // Catch:{ RuntimeException -> 0x00d0 }
            java.lang.String r6 = "jsonParser.getString(\"address\")"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r5, r6)     // Catch:{ RuntimeException -> 0x00d0 }
            java.lang.String r6 = "coin"
            java.lang.String r2 = r2.getString(r6)     // Catch:{ RuntimeException -> 0x00d0 }
            java.lang.String r6 = "jsonParser.getString(\"coin\")"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r2, r6)     // Catch:{ RuntimeException -> 0x00d0 }
            r1.<init>(r3, r4, r5, r2)     // Catch:{ RuntimeException -> 0x00d0 }
            r0.add(r1)     // Catch:{ RuntimeException -> 0x00d0 }
            goto L_0x0081
        L_0x00c7:
            java.util.List r0 = (java.util.List) r0     // Catch:{ RuntimeException -> 0x00d0 }
            java.lang.Iterable r0 = (java.lang.Iterable) r0     // Catch:{ RuntimeException -> 0x00d0 }
            java.util.List r8 = kotlin.collections.CollectionsKt.toList(r0)     // Catch:{ RuntimeException -> 0x00d0 }
            goto L_0x00da
        L_0x00d0:
            r8 = move-exception
            java.lang.Throwable r8 = (java.lang.Throwable) r8
            timber.log.Timber.m430e(r8)
            java.util.List r8 = kotlin.collections.CollectionsKt.emptyList()
        L_0x00da:
            return r8
        L_0x00db:
            java.util.List r8 = kotlin.collections.CollectionsKt.emptyList()
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.MigrateProfileInteractor.readOldContact(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x004b A[SYNTHETIC, Splitter:B:18:0x004b] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x006e  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object readOldWallets(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.util.List<com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.entity.OldWallet>> r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.MigrateProfileInteractor$readOldWallets$1
            if (r0 == 0) goto L_0x0014
            r0 = r5
            com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.MigrateProfileInteractor$readOldWallets$1 r0 = (com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.MigrateProfileInteractor$readOldWallets$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r5 = r0.label
            int r5 = r5 - r2
            r0.label = r5
            goto L_0x0019
        L_0x0014:
            com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.MigrateProfileInteractor$readOldWallets$1 r0 = new com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.MigrateProfileInteractor$readOldWallets$1
            r0.<init>(r4, r5)
        L_0x0019:
            java.lang.Object r5 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0036
            if (r2 != r3) goto L_0x002e
            java.lang.Object r0 = r0.L$0
            com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.MigrateProfileInteractor r0 = (com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.MigrateProfileInteractor) r0
            kotlin.ResultKt.throwOnFailure(r5)
            goto L_0x0047
        L_0x002e:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L_0x0036:
            kotlin.ResultKt.throwOnFailure(r5)
            com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.entity.OldWallet$OldWalletsJsonSource r5 = r4.oldWalletJson
            r0.L$0 = r4
            r0.label = r3
            java.lang.Object r5 = r5.read(r0)
            if (r5 != r1) goto L_0x0046
            return r1
        L_0x0046:
            r0 = r4
        L_0x0047:
            java.lang.String r5 = (java.lang.String) r5
            if (r5 == 0) goto L_0x006e
            com.squareup.moshi.JsonAdapter r0 = r0.getJsonWalletAdapter()     // Catch:{ RuntimeException -> 0x0063 }
            java.lang.Object r5 = r0.fromJson(r5)     // Catch:{ RuntimeException -> 0x0063 }
            com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.entity.OldProfile r5 = (com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.entity.OldProfile) r5     // Catch:{ RuntimeException -> 0x0063 }
            if (r5 == 0) goto L_0x005e
            java.util.List r5 = r5.getCredentials()     // Catch:{ RuntimeException -> 0x0063 }
            if (r5 == 0) goto L_0x005e
            goto L_0x006d
        L_0x005e:
            java.util.List r5 = kotlin.collections.CollectionsKt.emptyList()     // Catch:{ RuntimeException -> 0x0063 }
            goto L_0x006d
        L_0x0063:
            r5 = move-exception
            java.lang.Throwable r5 = (java.lang.Throwable) r5
            timber.log.Timber.m430e(r5)
            java.util.List r5 = kotlin.collections.CollectionsKt.emptyList()
        L_0x006d:
            return r5
        L_0x006e:
            java.util.List r5 = kotlin.collections.CollectionsKt.emptyList()
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.MigrateProfileInteractor.readOldWallets(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0054  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x00aa  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object migrateContacts(@org.jetbrains.annotations.NotNull java.util.List<com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.entity.OldContact.C1098OldContact> r12, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r13) {
        /*
            r11 = this;
            boolean r0 = r13 instanceof com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.MigrateProfileInteractor$migrateContacts$1
            if (r0 == 0) goto L_0x0014
            r0 = r13
            com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.MigrateProfileInteractor$migrateContacts$1 r0 = (com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.MigrateProfileInteractor$migrateContacts$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r13 = r0.label
            int r13 = r13 - r2
            r0.label = r13
            goto L_0x0019
        L_0x0014:
            com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.MigrateProfileInteractor$migrateContacts$1 r0 = new com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.MigrateProfileInteractor$migrateContacts$1
            r0.<init>(r11, r13)
        L_0x0019:
            java.lang.Object r13 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0054
            if (r2 != r3) goto L_0x004c
            java.lang.Object r12 = r0.L$7
            com.bitcoin.mwallet.core.models.contact.Contact r12 = (com.bitcoin.mwallet.core.models.contact.Contact) r12
            java.lang.Object r12 = r0.L$6
            java.lang.Object r12 = r0.L$5
            java.util.Iterator r12 = (java.util.Iterator) r12
            java.lang.Object r2 = r0.L$4
            java.lang.Iterable r2 = (java.lang.Iterable) r2
            java.lang.Object r4 = r0.L$3
            java.lang.Iterable r4 = (java.lang.Iterable) r4
            java.lang.Object r5 = r0.L$2
            java.lang.Iterable r5 = (java.lang.Iterable) r5
            java.lang.Object r6 = r0.L$1
            java.util.List r6 = (java.util.List) r6
            java.lang.Object r7 = r0.L$0
            com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.MigrateProfileInteractor r7 = (com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.MigrateProfileInteractor) r7
            kotlin.ResultKt.throwOnFailure(r13)
            r13 = r7
            r10 = r6
            r6 = r1
            r1 = r10
            goto L_0x00a4
        L_0x004c:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r13 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r13)
            throw r12
        L_0x0054:
            kotlin.ResultKt.throwOnFailure(r13)
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>()
            java.lang.String r2 = "OldContacts list: "
            r13.append(r2)
            r13.append(r12)
            java.lang.String r13 = r13.toString()
            r2 = 0
            java.lang.Object[] r2 = new java.lang.Object[r2]
            timber.log.Timber.m426d(r13, r2)
            r13 = r12
            java.lang.Iterable r13 = (java.lang.Iterable) r13
            java.util.ArrayList r2 = new java.util.ArrayList
            r4 = 10
            int r4 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r13, r4)
            r2.<init>(r4)
            java.util.Collection r2 = (java.util.Collection) r2
            java.util.Iterator r13 = r13.iterator()
        L_0x0082:
            boolean r4 = r13.hasNext()
            if (r4 == 0) goto L_0x0096
            java.lang.Object r4 = r13.next()
            com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.entity.OldContact.OldContact r4 = (com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.entity.OldContact.C1098OldContact) r4
            com.bitcoin.mwallet.core.models.contact.Contact r4 = r4.toNewContact()
            r2.add(r4)
            goto L_0x0082
        L_0x0096:
            java.util.List r2 = (java.util.List) r2
            java.lang.Iterable r2 = (java.lang.Iterable) r2
            java.util.Iterator r13 = r2.iterator()
            r6 = r1
            r4 = r2
            r5 = r4
            r1 = r12
            r12 = r13
            r13 = r11
        L_0x00a4:
            boolean r7 = r12.hasNext()
            if (r7 == 0) goto L_0x00cc
            java.lang.Object r7 = r12.next()
            r8 = r7
            com.bitcoin.mwallet.core.models.contact.Contact r8 = (com.bitcoin.mwallet.core.models.contact.Contact) r8
            com.bitcoin.mwallet.core.repositories.ContactRepository r9 = r13.contactService
            r0.L$0 = r13
            r0.L$1 = r1
            r0.L$2 = r5
            r0.L$3 = r4
            r0.L$4 = r2
            r0.L$5 = r12
            r0.L$6 = r7
            r0.L$7 = r8
            r0.label = r3
            java.lang.Object r7 = r9.insert(r8, r0)
            if (r7 != r6) goto L_0x00a4
            return r6
        L_0x00cc:
            kotlin.Unit r12 = kotlin.Unit.INSTANCE
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.MigrateProfileInteractor.migrateContacts(java.util.List, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:49:0x01ea, code lost:
        if (r5.hasNext() == false) goto L_0x029b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x01ec, code lost:
        r11 = r5.next();
        r14 = (kotlin.Pair) r11;
        r15 = r13.walletService;
        r9 = (com.bitcoin.mwallet.core.models.wallet.C1261Wallet) r14.getFirst();
        r12 = (com.bitcoin.mwallet.core.models.credential.Credential) r14.getSecond();
        r2 = com.bitcoin.mwallet.core.repositories.WalletRepository.WalletSaveType.MIGRATED;
        r1.L$0 = r13;
        r1.L$1 = r0;
        r1.L$2 = r4;
        r1.L$3 = r6;
        r1.L$4 = r8;
        r1.L$5 = r7;
        r1.L$6 = r3;
        r1.L$7 = r5;
        r1.L$8 = r11;
        r1.L$9 = r14;
        r1.label = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0222, code lost:
        if (r15.saveNewWallet(r9, r12, r2, r1) != r10) goto L_0x0225;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x0224, code lost:
        return r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0225, code lost:
        r13.analyticsService.track("wallet_created", kotlin.collections.MapsKt.mapOf(kotlin.TuplesKt.m309to("coin", ((com.bitcoin.mwallet.core.models.wallet.C1261Wallet) r14.getFirst()).getCoin().getTicker()), kotlin.TuplesKt.m309to("created_by", kotlinx.coroutines.DebugKt.DEBUG_PROPERTY_VALUE_AUTO), kotlin.TuplesKt.m309to("from_seed", "false"), kotlin.TuplesKt.m309to("type", "regular"), kotlin.TuplesKt.m309to("num_of_copayers", kotlin.coroutines.jvm.internal.Boxing.boxInt(((com.bitcoin.mwallet.core.models.wallet.C1261Wallet) r14.getFirst()).getCopayers().getNumCopayers())), kotlin.TuplesKt.m309to("num_of_signatures", kotlin.coroutines.jvm.internal.Boxing.boxInt(1))));
        r2 = r19;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x029b, code lost:
        r2 = (java.util.List) r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:?, code lost:
        r3 = r13.walletService;
        r1.L$0 = r13;
        r1.L$1 = r0;
        r1.L$2 = r4;
        r1.L$3 = r6;
        r1.L$4 = r2;
        r1.label = 2;
        r3 = r3.getWallets(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x02b1, code lost:
        if (r3 != r10) goto L_0x02b4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x02b3, code lost:
        return r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x02b4, code lost:
        r9 = r0;
        r0 = r3;
        r7 = r4;
        r3 = r10;
        r4 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:?, code lost:
        r0 = (java.lang.Iterable) r0;
        r2 = new java.util.ArrayList(kotlin.collections.CollectionsKt.collectionSizeOrDefault(r0, 10));
        r5 = r0.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x02cc, code lost:
        r15 = r0;
        r0 = r13;
        r13 = r5;
        r5 = r15;
        r17 = r4;
        r4 = r2;
        r2 = r17;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x02d9, code lost:
        if (r13.hasNext() == false) goto L_0x0333;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x02db, code lost:
        r8 = r13.next();
        r10 = (com.bitcoin.mwallet.core.models.wallet.C1261Wallet) r8;
        r11 = r0.walletService;
        r12 = r10.getId();
        r1.L$0 = r0;
        r1.L$1 = r9;
        r1.L$2 = r7;
        r1.L$3 = r6;
        r1.L$4 = r2;
        r1.L$5 = r5;
        r1.L$6 = r15;
        r1.L$7 = r4;
        r1.L$8 = r13;
        r1.L$9 = r8;
        r1.L$10 = r10;
        r1.L$11 = r10;
        r1.L$12 = r4;
        r1.label = 3;
        r11 = r11.getCredential(r12, (kotlin.coroutines.Continuation<? super com.bitcoin.mwallet.core.models.credential.Credential>) r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x0309, code lost:
        if (r11 != r3) goto L_0x030c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x030b, code lost:
        return r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x030c, code lost:
        r14 = r4;
        r17 = r11;
        r11 = r0;
        r0 = r17;
        r18 = r3;
        r3 = r2;
        r2 = r9;
        r9 = r10;
        r10 = r5;
        r5 = r18;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x031a, code lost:
        if (r0 != null) goto L_0x031f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:?, code lost:
        kotlin.jvm.internal.Intrinsics.throwNpe();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x031f, code lost:
        r4.add(new kotlin.Pair(r9, r0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x0327, code lost:
        r9 = r2;
        r2 = r3;
        r3 = r5;
        r5 = r10;
        r0 = r11;
        r4 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x032e, code lost:
        r9 = r2;
        r2 = r3;
        r3 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:?, code lost:
        r4 = (java.util.List) r4;
        r1.L$0 = r0;
        r1.L$1 = r9;
        r1.L$2 = r7;
        r1.L$3 = r6;
        r1.L$4 = r2;
        r1.L$5 = r4;
        r1.label = 4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x0348, code lost:
        if (r0.migrateNotes(r4, r6, r1) != r3) goto L_0x034b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x034a, code lost:
        return r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x034b, code lost:
        r13 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x034d, code lost:
        r13 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x0350, code lost:
        r9 = r0;
        r7 = r4;
        r3 = r10;
     */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x0035  */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x004e  */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x006c  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x008d  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x00d4  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00f6  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x012b  */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x0373 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002d  */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x0374  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object migrateWallets(@org.jetbrains.annotations.NotNull java.util.List<com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.entity.OldWallet> r20, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r21) {
        /*
            r19 = this;
            r0 = r21
            boolean r1 = r0 instanceof com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.MigrateProfileInteractor$migrateWallets$1
            if (r1 == 0) goto L_0x0018
            r1 = r0
            com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.MigrateProfileInteractor$migrateWallets$1 r1 = (com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.MigrateProfileInteractor$migrateWallets$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L_0x0018
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            r2 = r19
            goto L_0x001f
        L_0x0018:
            com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.MigrateProfileInteractor$migrateWallets$1 r1 = new com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.MigrateProfileInteractor$migrateWallets$1
            r2 = r19
            r1.<init>(r2, r0)
        L_0x001f:
            java.lang.Object r0 = r1.result
            java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r4 = r1.label
            r9 = 10
            r12 = 0
            switch(r4) {
                case 0: goto L_0x012b;
                case 1: goto L_0x00f6;
                case 2: goto L_0x00d4;
                case 3: goto L_0x008d;
                case 4: goto L_0x006c;
                case 5: goto L_0x004e;
                case 6: goto L_0x0035;
                default: goto L_0x002d;
            }
        L_0x002d:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0035:
            java.lang.Object r3 = r1.L$4
            java.util.List r3 = (java.util.List) r3
            java.lang.Object r4 = r1.L$3
            java.util.Map r4 = (java.util.Map) r4
            java.lang.Object r4 = r1.L$2
            java.util.List r4 = (java.util.List) r4
            java.lang.Object r4 = r1.L$1
            java.util.List r4 = (java.util.List) r4
            java.lang.Object r1 = r1.L$0
            com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.MigrateProfileInteractor r1 = (com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.MigrateProfileInteractor) r1
            kotlin.ResultKt.throwOnFailure(r0)
            goto L_0x0392
        L_0x004e:
            java.lang.Object r4 = r1.L$4
            java.util.List r4 = (java.util.List) r4
            java.lang.Object r5 = r1.L$3
            java.util.Map r5 = (java.util.Map) r5
            java.lang.Object r6 = r1.L$2
            java.util.List r6 = (java.util.List) r6
            java.lang.Object r7 = r1.L$1
            java.util.List r7 = (java.util.List) r7
            java.lang.Object r9 = r1.L$0
            com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.MigrateProfileInteractor r9 = (com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.MigrateProfileInteractor) r9
            kotlin.ResultKt.throwOnFailure(r0)
            r0 = r1
            r2 = r3
            r3 = r4
            r4 = r7
            r1 = r9
            goto L_0x037c
        L_0x006c:
            java.lang.Object r4 = r1.L$5
            java.util.List r4 = (java.util.List) r4
            java.lang.Object r4 = r1.L$4
            java.util.List r4 = (java.util.List) r4
            java.lang.Object r6 = r1.L$3
            java.util.Map r6 = (java.util.Map) r6
            java.lang.Object r7 = r1.L$2
            java.util.List r7 = (java.util.List) r7
            java.lang.Object r9 = r1.L$1
            java.util.List r9 = (java.util.List) r9
            java.lang.Object r13 = r1.L$0
            com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.MigrateProfileInteractor r13 = (com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.MigrateProfileInteractor) r13
            kotlin.ResultKt.throwOnFailure(r0)     // Catch:{ Exception -> 0x008a }
            r2 = r4
            goto L_0x035c
        L_0x008a:
            r2 = r4
            goto L_0x00f3
        L_0x008d:
            java.lang.Object r4 = r1.L$12
            java.util.Collection r4 = (java.util.Collection) r4
            java.lang.Object r9 = r1.L$11
            com.bitcoin.mwallet.core.models.wallet.Wallet r9 = (com.bitcoin.mwallet.core.models.wallet.C1261Wallet) r9
            java.lang.Object r13 = r1.L$10
            com.bitcoin.mwallet.core.models.wallet.Wallet r13 = (com.bitcoin.mwallet.core.models.wallet.C1261Wallet) r13
            java.lang.Object r13 = r1.L$9
            java.lang.Object r13 = r1.L$8
            java.util.Iterator r13 = (java.util.Iterator) r13
            java.lang.Object r14 = r1.L$7
            java.util.Collection r14 = (java.util.Collection) r14
            java.lang.Object r15 = r1.L$6
            java.lang.Iterable r15 = (java.lang.Iterable) r15
            java.lang.Object r5 = r1.L$5
            java.lang.Iterable r5 = (java.lang.Iterable) r5
            java.lang.Object r6 = r1.L$4
            java.util.List r6 = (java.util.List) r6
            java.lang.Object r7 = r1.L$3
            java.util.Map r7 = (java.util.Map) r7
            java.lang.Object r10 = r1.L$2
            java.util.List r10 = (java.util.List) r10
            java.lang.Object r8 = r1.L$1
            java.util.List r8 = (java.util.List) r8
            java.lang.Object r11 = r1.L$0
            com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.MigrateProfileInteractor r11 = (com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.MigrateProfileInteractor) r11
            kotlin.ResultKt.throwOnFailure(r0)     // Catch:{ Exception -> 0x00ce }
            r2 = r8
            r8 = 3
            r17 = r5
            r5 = r3
            r3 = r6
            r6 = r7
            r7 = r10
            r10 = r17
            goto L_0x031a
        L_0x00ce:
            r2 = r6
            r6 = r7
            r9 = r8
            r7 = r10
        L_0x00d2:
            r13 = r11
            goto L_0x00f3
        L_0x00d4:
            java.lang.Object r4 = r1.L$4
            java.util.List r4 = (java.util.List) r4
            java.lang.Object r5 = r1.L$3
            r6 = r5
            java.util.Map r6 = (java.util.Map) r6
            java.lang.Object r5 = r1.L$2
            r7 = r5
            java.util.List r7 = (java.util.List) r7
            java.lang.Object r5 = r1.L$1
            java.util.List r5 = (java.util.List) r5
            java.lang.Object r8 = r1.L$0
            r13 = r8
            com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.MigrateProfileInteractor r13 = (com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.MigrateProfileInteractor) r13
            kotlin.ResultKt.throwOnFailure(r0)     // Catch:{ Exception -> 0x00f1 }
            r9 = r5
            goto L_0x02b9
        L_0x00f1:
            r2 = r4
            r9 = r5
        L_0x00f3:
            r0 = 0
            goto L_0x0355
        L_0x00f6:
            java.lang.Object r4 = r1.L$9
            kotlin.Pair r4 = (kotlin.Pair) r4
            java.lang.Object r5 = r1.L$8
            java.lang.Object r5 = r1.L$7
            java.util.Iterator r5 = (java.util.Iterator) r5
            java.lang.Object r6 = r1.L$6
            java.lang.Iterable r6 = (java.lang.Iterable) r6
            java.lang.Object r7 = r1.L$5
            java.lang.Iterable r7 = (java.lang.Iterable) r7
            java.lang.Object r8 = r1.L$4
            java.lang.Iterable r8 = (java.lang.Iterable) r8
            java.lang.Object r10 = r1.L$3
            java.util.Map r10 = (java.util.Map) r10
            java.lang.Object r11 = r1.L$2
            java.util.List r11 = (java.util.List) r11
            java.lang.Object r13 = r1.L$1
            java.util.List r13 = (java.util.List) r13
            java.lang.Object r14 = r1.L$0
            com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.MigrateProfileInteractor r14 = (com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.MigrateProfileInteractor) r14
            kotlin.ResultKt.throwOnFailure(r0)
            r0 = r13
            r13 = r14
            r14 = r4
            r4 = r11
            r17 = r10
            r10 = r3
            r3 = r6
            r6 = r17
            goto L_0x0225
        L_0x012b:
            kotlin.ResultKt.throwOnFailure(r0)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r4 = "Starting migration of "
            r0.append(r4)
            int r4 = r20.size()
            r0.append(r4)
            java.lang.String r4 = " wallets"
            r0.append(r4)
            java.lang.String r0 = r0.toString()
            java.lang.Object[] r4 = new java.lang.Object[r12]
            timber.log.Timber.m426d(r0, r4)
            r0 = r20
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            java.util.Collection r4 = (java.util.Collection) r4
            java.util.Iterator r5 = r0.iterator()
        L_0x015c:
            boolean r6 = r5.hasNext()
            if (r6 == 0) goto L_0x017b
            java.lang.Object r6 = r5.next()
            r7 = r6
            com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.entity.OldWallet r7 = (com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.entity.OldWallet) r7
            boolean r7 = r7.isValidWallet()
            java.lang.Boolean r7 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r7)
            boolean r7 = r7.booleanValue()
            if (r7 == 0) goto L_0x015c
            r4.add(r6)
            goto L_0x015c
        L_0x017b:
            java.util.List r4 = (java.util.List) r4
            java.util.ArrayList r5 = new java.util.ArrayList
            int r6 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r0, r9)
            r5.<init>(r6)
            java.util.Collection r5 = (java.util.Collection) r5
            java.util.Iterator r0 = r0.iterator()
        L_0x018c:
            boolean r6 = r0.hasNext()
            if (r6 == 0) goto L_0x01a8
            java.lang.Object r6 = r0.next()
            com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.entity.OldWallet r6 = (com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.entity.OldWallet) r6
            java.lang.String r7 = r6.getCopayerId()
            java.lang.String r6 = r6.getSharedEncryptingKey()
            kotlin.Pair r6 = kotlin.TuplesKt.m309to(r7, r6)
            r5.add(r6)
            goto L_0x018c
        L_0x01a8:
            java.util.List r5 = (java.util.List) r5
            java.lang.Iterable r5 = (java.lang.Iterable) r5
            java.util.Map r0 = kotlin.collections.MapsKt.toMap(r5)
            r5 = r4
            java.lang.Iterable r5 = (java.lang.Iterable) r5
            java.util.ArrayList r6 = new java.util.ArrayList
            int r7 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r5, r9)
            r6.<init>(r7)
            java.util.Collection r6 = (java.util.Collection) r6
            java.util.Iterator r5 = r5.iterator()
        L_0x01c2:
            boolean r7 = r5.hasNext()
            if (r7 == 0) goto L_0x01d6
            java.lang.Object r7 = r5.next()
            com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.entity.OldWallet r7 = (com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.entity.OldWallet) r7
            kotlin.Pair r7 = r7.toNewWallet()
            r6.add(r7)
            goto L_0x01c2
        L_0x01d6:
            java.util.List r6 = (java.util.List) r6
            java.lang.Iterable r6 = (java.lang.Iterable) r6
            java.util.Iterator r5 = r6.iterator()
            r13 = r2
            r10 = r3
            r3 = r6
            r7 = r3
            r8 = r7
            r6 = r0
            r0 = r20
        L_0x01e6:
            boolean r11 = r5.hasNext()
            if (r11 == 0) goto L_0x029b
            java.lang.Object r11 = r5.next()
            r14 = r11
            kotlin.Pair r14 = (kotlin.Pair) r14
            com.bitcoin.mwallet.core.repositories.WalletRepository r15 = r13.walletService
            java.lang.Object r16 = r14.getFirst()
            r9 = r16
            com.bitcoin.mwallet.core.models.wallet.Wallet r9 = (com.bitcoin.mwallet.core.models.wallet.C1261Wallet) r9
            java.lang.Object r16 = r14.getSecond()
            r12 = r16
            com.bitcoin.mwallet.core.models.credential.Credential r12 = (com.bitcoin.mwallet.core.models.credential.Credential) r12
            com.bitcoin.mwallet.core.repositories.WalletRepository$WalletSaveType r2 = com.bitcoin.mwallet.core.repositories.WalletRepository.WalletSaveType.MIGRATED
            r1.L$0 = r13
            r1.L$1 = r0
            r1.L$2 = r4
            r1.L$3 = r6
            r1.L$4 = r8
            r1.L$5 = r7
            r1.L$6 = r3
            r1.L$7 = r5
            r1.L$8 = r11
            r1.L$9 = r14
            r11 = 1
            r1.label = r11
            java.lang.Object r2 = r15.saveNewWallet(r9, r12, r2, r1)
            if (r2 != r10) goto L_0x0225
            return r10
        L_0x0225:
            com.bitcoin.mwallet.core.services.AnalyticsService r2 = r13.analyticsService
            r9 = 6
            kotlin.Pair[] r11 = new kotlin.Pair[r9]
            java.lang.Object r9 = r14.getFirst()
            com.bitcoin.mwallet.core.models.wallet.Wallet r9 = (com.bitcoin.mwallet.core.models.wallet.C1261Wallet) r9
            com.bitcoin.mwallet.core.models.Coin r9 = r9.getCoin()
            java.lang.String r9 = r9.getTicker()
            java.lang.String r12 = "coin"
            kotlin.Pair r9 = kotlin.TuplesKt.m309to(r12, r9)
            r12 = 0
            r11[r12] = r9
            java.lang.String r9 = "created_by"
            java.lang.String r12 = "auto"
            kotlin.Pair r9 = kotlin.TuplesKt.m309to(r9, r12)
            r12 = 1
            r11[r12] = r9
            java.lang.String r9 = "from_seed"
            java.lang.String r12 = "false"
            kotlin.Pair r9 = kotlin.TuplesKt.m309to(r9, r12)
            r12 = 2
            r11[r12] = r9
            java.lang.String r9 = "type"
            java.lang.String r12 = "regular"
            kotlin.Pair r9 = kotlin.TuplesKt.m309to(r9, r12)
            r12 = 3
            r11[r12] = r9
            java.lang.Object r9 = r14.getFirst()
            com.bitcoin.mwallet.core.models.wallet.Wallet r9 = (com.bitcoin.mwallet.core.models.wallet.C1261Wallet) r9
            com.bitcoin.mwallet.core.models.Copayers r9 = r9.getCopayers()
            int r9 = r9.getNumCopayers()
            java.lang.Integer r9 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r9)
            java.lang.String r12 = "num_of_copayers"
            kotlin.Pair r9 = kotlin.TuplesKt.m309to(r12, r9)
            r12 = 4
            r11[r12] = r9
            r9 = 1
            java.lang.Integer r12 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r9)
            java.lang.String r9 = "num_of_signatures"
            kotlin.Pair r9 = kotlin.TuplesKt.m309to(r9, r12)
            r12 = 5
            r11[r12] = r9
            java.util.Map r9 = kotlin.collections.MapsKt.mapOf(r11)
            java.lang.String r11 = "wallet_created"
            r2.track(r11, r9)
            r9 = 10
            r12 = 0
            r2 = r19
            goto L_0x01e6
        L_0x029b:
            r2 = r7
            java.util.List r2 = (java.util.List) r2
            com.bitcoin.mwallet.core.repositories.WalletRepository r3 = r13.walletService     // Catch:{ Exception -> 0x0350 }
            r1.L$0 = r13     // Catch:{ Exception -> 0x0350 }
            r1.L$1 = r0     // Catch:{ Exception -> 0x0350 }
            r1.L$2 = r4     // Catch:{ Exception -> 0x0350 }
            r1.L$3 = r6     // Catch:{ Exception -> 0x0350 }
            r1.L$4 = r2     // Catch:{ Exception -> 0x0350 }
            r5 = 2
            r1.label = r5     // Catch:{ Exception -> 0x0350 }
            java.lang.Object r3 = r3.getWallets(r1)     // Catch:{ Exception -> 0x0350 }
            if (r3 != r10) goto L_0x02b4
            return r10
        L_0x02b4:
            r9 = r0
            r0 = r3
            r7 = r4
            r3 = r10
            r4 = r2
        L_0x02b9:
            java.lang.Iterable r0 = (java.lang.Iterable) r0     // Catch:{ Exception -> 0x008a }
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch:{ Exception -> 0x008a }
            r5 = 10
            int r5 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r0, r5)     // Catch:{ Exception -> 0x008a }
            r2.<init>(r5)     // Catch:{ Exception -> 0x008a }
            java.util.Collection r2 = (java.util.Collection) r2     // Catch:{ Exception -> 0x008a }
            java.util.Iterator r5 = r0.iterator()     // Catch:{ Exception -> 0x008a }
            r15 = r0
            r0 = r13
            r13 = r5
            r5 = r15
            r17 = r4
            r4 = r2
            r2 = r17
        L_0x02d5:
            boolean r8 = r13.hasNext()     // Catch:{ Exception -> 0x034d }
            if (r8 == 0) goto L_0x0333
            java.lang.Object r8 = r13.next()     // Catch:{ Exception -> 0x034d }
            r10 = r8
            com.bitcoin.mwallet.core.models.wallet.Wallet r10 = (com.bitcoin.mwallet.core.models.wallet.C1261Wallet) r10     // Catch:{ Exception -> 0x034d }
            com.bitcoin.mwallet.core.repositories.WalletRepository r11 = r0.walletService     // Catch:{ Exception -> 0x034d }
            com.bitcoin.mwallet.core.models.wallet.WalletId r12 = r10.getId()     // Catch:{ Exception -> 0x034d }
            r1.L$0 = r0     // Catch:{ Exception -> 0x034d }
            r1.L$1 = r9     // Catch:{ Exception -> 0x034d }
            r1.L$2 = r7     // Catch:{ Exception -> 0x034d }
            r1.L$3 = r6     // Catch:{ Exception -> 0x034d }
            r1.L$4 = r2     // Catch:{ Exception -> 0x034d }
            r1.L$5 = r5     // Catch:{ Exception -> 0x034d }
            r1.L$6 = r15     // Catch:{ Exception -> 0x034d }
            r1.L$7 = r4     // Catch:{ Exception -> 0x034d }
            r1.L$8 = r13     // Catch:{ Exception -> 0x034d }
            r1.L$9 = r8     // Catch:{ Exception -> 0x034d }
            r1.L$10 = r10     // Catch:{ Exception -> 0x034d }
            r1.L$11 = r10     // Catch:{ Exception -> 0x034d }
            r1.L$12 = r4     // Catch:{ Exception -> 0x034d }
            r8 = 3
            r1.label = r8     // Catch:{ Exception -> 0x034d }
            java.lang.Object r11 = r11.getCredential(r12, r1)     // Catch:{ Exception -> 0x034d }
            if (r11 != r3) goto L_0x030c
            return r3
        L_0x030c:
            r14 = r4
            r17 = r11
            r11 = r0
            r0 = r17
            r18 = r3
            r3 = r2
            r2 = r9
            r9 = r10
            r10 = r5
            r5 = r18
        L_0x031a:
            if (r0 != 0) goto L_0x031f
            kotlin.jvm.internal.Intrinsics.throwNpe()     // Catch:{ Exception -> 0x032e }
        L_0x031f:
            kotlin.Pair r12 = new kotlin.Pair     // Catch:{ Exception -> 0x032e }
            r12.<init>(r9, r0)     // Catch:{ Exception -> 0x032e }
            r4.add(r12)     // Catch:{ Exception -> 0x032e }
            r9 = r2
            r2 = r3
            r3 = r5
            r5 = r10
            r0 = r11
            r4 = r14
            goto L_0x02d5
        L_0x032e:
            r9 = r2
            r2 = r3
            r3 = r5
            goto L_0x00d2
        L_0x0333:
            java.util.List r4 = (java.util.List) r4     // Catch:{ Exception -> 0x034d }
            r1.L$0 = r0     // Catch:{ Exception -> 0x034d }
            r1.L$1 = r9     // Catch:{ Exception -> 0x034d }
            r1.L$2 = r7     // Catch:{ Exception -> 0x034d }
            r1.L$3 = r6     // Catch:{ Exception -> 0x034d }
            r1.L$4 = r2     // Catch:{ Exception -> 0x034d }
            r1.L$5 = r4     // Catch:{ Exception -> 0x034d }
            r5 = 4
            r1.label = r5     // Catch:{ Exception -> 0x034d }
            java.lang.Object r4 = r0.migrateNotes(r4, r6, r1)     // Catch:{ Exception -> 0x034d }
            if (r4 != r3) goto L_0x034b
            return r3
        L_0x034b:
            r13 = r0
            goto L_0x035c
        L_0x034d:
            r13 = r0
            goto L_0x00f3
        L_0x0350:
            r9 = r0
            r7 = r4
            r3 = r10
            goto L_0x00f3
        L_0x0355:
            java.lang.Object[] r4 = new java.lang.Object[r0]
            java.lang.String r0 = "Note Migration Failed"
            timber.log.Timber.m426d(r0, r4)
        L_0x035c:
            r5 = r6
            r6 = r7
            com.bitcoin.mwallet.core.repositories.UserRepository r0 = r13.userService
            r1.L$0 = r13
            r1.L$1 = r9
            r1.L$2 = r6
            r1.L$3 = r5
            r1.L$4 = r2
            r4 = 5
            r1.label = r4
            java.lang.Object r0 = r0.getOrCreateUser(r1)
            if (r0 != r3) goto L_0x0374
            return r3
        L_0x0374:
            r0 = r1
            r4 = r9
            r1 = r13
            r17 = r3
            r3 = r2
            r2 = r17
        L_0x037c:
            com.bitcoin.mwallet.core.services.WalletRefresherTemp r7 = r1.walletRefresherTemp
            r0.L$0 = r1
            r0.L$1 = r4
            r0.L$2 = r6
            r0.L$3 = r5
            r0.L$4 = r3
            r5 = 6
            r0.label = r5
            java.lang.Object r0 = r7.refreshWalletsAsync(r0)
            if (r0 != r2) goto L_0x0392
            return r2
        L_0x0392:
            com.bitcoin.mwallet.core.services.eventstream.EventStreamHandler r0 = r1.eventStreamHandler
            r0.restartStream()
            com.bitcoin.mwallet.core.services.AnalyticsService r0 = r1.analyticsService
            r1 = 2
            kotlin.Pair[] r1 = new kotlin.Pair[r1]
            java.util.Collection r4 = (java.util.Collection) r4
            int r2 = r4.size()
            java.lang.Integer r2 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r2)
            java.lang.String r4 = "origin_wallets"
            kotlin.Pair r2 = kotlin.TuplesKt.m309to(r4, r2)
            r4 = 0
            r1[r4] = r2
            java.util.Collection r3 = (java.util.Collection) r3
            int r2 = r3.size()
            java.lang.Integer r2 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r2)
            java.lang.String r3 = "migrated_wallets"
            kotlin.Pair r2 = kotlin.TuplesKt.m309to(r3, r2)
            r3 = 1
            r1[r3] = r2
            java.util.Map r1 = kotlin.collections.MapsKt.mapOf(r1)
            java.lang.String r2 = "wallet_migration"
            r0.track(r2, r1)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.MigrateProfileInteractor.migrateWallets(java.util.List, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public final /* synthetic */ Object migrateNotes(@NotNull List<? extends Pair<C1261Wallet, ? extends Credential>> list, @NotNull Map<String, String> map, @NotNull Continuation<? super Unit> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new MigrateProfileInteractor$migrateNotes$2(this, list, map, null), continuation);
    }
}
