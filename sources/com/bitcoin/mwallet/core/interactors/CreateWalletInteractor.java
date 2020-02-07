package com.bitcoin.mwallet.core.interactors;

import android.app.Application;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.core.models.Coin;
import com.bitcoin.mwallet.core.repositories.WalletRepository;
import com.bitcoin.mwallet.core.services.AnalyticsService;
import com.bitcoin.mwallet.core.services.WalletRefresherTemp;
import com.bitcoin.mwallet.core.services.eventstream.EventStreamHandler;
import com.bitcoin.mwallet.zion.ZionRepository;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001,B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eJ\u0011\u0010\u000f\u001a\u00020\u0010H@ø\u0001\u0000¢\u0006\u0002\u0010\u0011J#\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H@ø\u0001\u0000¢\u0006\u0002\u0010\u0018J#\u0010\u0019\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H@ø\u0001\u0000¢\u0006\u0002\u0010\u0018J'\u0010\u001a\u001a\u00020\u00102\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001c2\u0006\u0010\u001e\u001a\u00020\u001fH@ø\u0001\u0000¢\u0006\u0002\u0010 J\u0010\u0010!\u001a\u00020\u00172\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J)\u0010\"\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010#\u001a\u00020$H@ø\u0001\u0000¢\u0006\u0002\u0010%J1\u0010&\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020*H@ø\u0001\u0000¢\u0006\u0002\u0010+R\u000e\u0010\f\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006-"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/interactors/CreateWalletInteractor;", "", "context", "Landroid/app/Application;", "walletRepository", "Lcom/bitcoin/mwallet/core/repositories/WalletRepository;", "eventStreamHandler", "Lcom/bitcoin/mwallet/core/services/eventstream/EventStreamHandler;", "zionRepository", "Lcom/bitcoin/mwallet/zion/ZionRepository;", "walletRefresherTemp", "Lcom/bitcoin/mwallet/core/services/WalletRefresherTemp;", "analyticsService", "Lcom/bitcoin/mwallet/core/services/AnalyticsService;", "(Landroid/app/Application;Lcom/bitcoin/mwallet/core/repositories/WalletRepository;Lcom/bitcoin/mwallet/core/services/eventstream/EventStreamHandler;Lcom/bitcoin/mwallet/zion/ZionRepository;Lcom/bitcoin/mwallet/core/services/WalletRefresherTemp;Lcom/bitcoin/mwallet/core/services/AnalyticsService;)V", "createDefaultWallets", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createWallet", "Lcom/bitcoin/mwallet/core/interactors/CreateWalletInteractor$CreateWalletResult;", "coin", "Lcom/bitcoin/mwallet/core/models/Coin;", "walletName", "", "(Lcom/bitcoin/mwallet/core/models/Coin;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createZionWallet", "createZionWallets", "xPub", "", "Lcom/bitcoin/mwallet/zion/ZionXPub;", "recoveredWallets", "", "(Ljava/util/Set;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getDefaultWalletName", "recoverSoftwareWallet", "credentialMnemonic", "Lcom/bitcoin/mwallet/core/models/credential/CredentialMnemonic;", "(Lcom/bitcoin/mwallet/core/models/Coin;Ljava/lang/String;Lcom/bitcoin/mwallet/core/models/credential/CredentialMnemonic;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "recoverZionWallet", "zionId", "Lcom/bitcoin/mwallet/zion/ZionId;", "zionWalletName", "Lcom/bitcoin/mwallet/zion/ZionWalletName;", "(Lcom/bitcoin/mwallet/core/models/Coin;Ljava/lang/String;Lcom/bitcoin/mwallet/zion/ZionId;Lcom/bitcoin/mwallet/zion/ZionWalletName;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "CreateWalletResult", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: CreateWalletInteractor.kt */
public final class CreateWalletInteractor {
    private final AnalyticsService analyticsService;
    private final Application context;
    private final EventStreamHandler eventStreamHandler;
    private final WalletRefresherTemp walletRefresherTemp;
    private final WalletRepository walletRepository;
    private final ZionRepository zionRepository;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\b\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/interactors/CreateWalletInteractor$CreateWalletResult;", "", "(Ljava/lang/String;I)V", "FAILED", "CANCELED", "SUCCESS", "FAILED_ZION_MAX", "INVALID_MNEMONIC", "DUPLICATE_MNEMONIC", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: CreateWalletInteractor.kt */
    public enum CreateWalletResult {
        FAILED,
        CANCELED,
        SUCCESS,
        FAILED_ZION_MAX,
        INVALID_MNEMONIC,
        DUPLICATE_MNEMONIC
    }

    @Metadata(mo37403bv = {1, 0, 3}, mo37406k = 3, mo37407mv = {1, 1, 15})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[Coin.values().length];

        static {
            $EnumSwitchMapping$0[Coin.BCH.ordinal()] = 1;
            $EnumSwitchMapping$0[Coin.BTC.ordinal()] = 2;
        }
    }

    public CreateWalletInteractor(@NotNull Application application, @NotNull WalletRepository walletRepository2, @NotNull EventStreamHandler eventStreamHandler2, @NotNull ZionRepository zionRepository2, @NotNull WalletRefresherTemp walletRefresherTemp2, @NotNull AnalyticsService analyticsService2) {
        Intrinsics.checkParameterIsNotNull(application, "context");
        Intrinsics.checkParameterIsNotNull(walletRepository2, "walletRepository");
        Intrinsics.checkParameterIsNotNull(eventStreamHandler2, "eventStreamHandler");
        Intrinsics.checkParameterIsNotNull(zionRepository2, "zionRepository");
        Intrinsics.checkParameterIsNotNull(walletRefresherTemp2, "walletRefresherTemp");
        Intrinsics.checkParameterIsNotNull(analyticsService2, "analyticsService");
        this.context = application;
        this.walletRepository = walletRepository2;
        this.eventStreamHandler = eventStreamHandler2;
        this.zionRepository = zionRepository2;
        this.walletRefresherTemp = walletRefresherTemp2;
        this.analyticsService = analyticsService2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0069  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0087  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0099  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00b8  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object createWallet(@org.jetbrains.annotations.NotNull com.bitcoin.mwallet.core.models.Coin r9, @org.jetbrains.annotations.Nullable java.lang.String r10, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.bitcoin.mwallet.core.interactors.CreateWalletInteractor.CreateWalletResult> r11) {
        /*
            r8 = this;
            boolean r0 = r11 instanceof com.bitcoin.mwallet.core.interactors.CreateWalletInteractor$createWallet$1
            if (r0 == 0) goto L_0x0014
            r0 = r11
            com.bitcoin.mwallet.core.interactors.CreateWalletInteractor$createWallet$1 r0 = (com.bitcoin.mwallet.core.interactors.CreateWalletInteractor$createWallet$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r11 = r0.label
            int r11 = r11 - r2
            r0.label = r11
            goto L_0x0019
        L_0x0014:
            com.bitcoin.mwallet.core.interactors.CreateWalletInteractor$createWallet$1 r0 = new com.bitcoin.mwallet.core.interactors.CreateWalletInteractor$createWallet$1
            r0.<init>(r8, r11)
        L_0x0019:
            java.lang.Object r11 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 3
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L_0x0069
            if (r2 == r5) goto L_0x0055
            if (r2 == r4) goto L_0x0045
            if (r2 != r3) goto L_0x003d
            java.lang.Object r9 = r0.L$2
            java.lang.String r9 = (java.lang.String) r9
            java.lang.Object r9 = r0.L$1
            com.bitcoin.mwallet.core.models.Coin r9 = (com.bitcoin.mwallet.core.models.Coin) r9
            java.lang.Object r10 = r0.L$0
            com.bitcoin.mwallet.core.interactors.CreateWalletInteractor r10 = (com.bitcoin.mwallet.core.interactors.CreateWalletInteractor) r10
            kotlin.ResultKt.throwOnFailure(r11)
            goto L_0x00b2
        L_0x003d:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L_0x0045:
            java.lang.Object r9 = r0.L$2
            java.lang.String r9 = (java.lang.String) r9
            java.lang.Object r9 = r0.L$1
            com.bitcoin.mwallet.core.models.Coin r9 = (com.bitcoin.mwallet.core.models.Coin) r9
            java.lang.Object r10 = r0.L$0
            com.bitcoin.mwallet.core.interactors.CreateWalletInteractor r10 = (com.bitcoin.mwallet.core.interactors.CreateWalletInteractor) r10
            kotlin.ResultKt.throwOnFailure(r11)
            goto L_0x0096
        L_0x0055:
            java.lang.Object r9 = r0.L$2
            r10 = r9
            java.lang.String r10 = (java.lang.String) r10
            java.lang.Object r9 = r0.L$1
            com.bitcoin.mwallet.core.models.Coin r9 = (com.bitcoin.mwallet.core.models.Coin) r9
            java.lang.Object r2 = r0.L$0
            com.bitcoin.mwallet.core.interactors.CreateWalletInteractor r2 = (com.bitcoin.mwallet.core.interactors.CreateWalletInteractor) r2
            kotlin.ResultKt.throwOnFailure(r11)
            r7 = r2
            r2 = r10
            r10 = r7
            goto L_0x007f
        L_0x0069:
            kotlin.ResultKt.throwOnFailure(r11)
            com.bitcoin.mwallet.zion.ZionRepository r11 = r8.zionRepository
            r0.L$0 = r8
            r0.L$1 = r9
            r0.L$2 = r10
            r0.label = r5
            java.lang.Object r11 = r11.hasZion(r0)
            if (r11 != r1) goto L_0x007d
            return r1
        L_0x007d:
            r2 = r10
            r10 = r8
        L_0x007f:
            java.lang.Boolean r11 = (java.lang.Boolean) r11
            boolean r11 = r11.booleanValue()
            if (r11 == 0) goto L_0x0099
            r0.L$0 = r10
            r0.L$1 = r9
            r0.L$2 = r2
            r0.label = r4
            java.lang.Object r11 = r10.createZionWallet(r9, r2, r0)
            if (r11 != r1) goto L_0x0096
            return r1
        L_0x0096:
            com.bitcoin.mwallet.core.interactors.CreateWalletInteractor$CreateWalletResult r11 = (com.bitcoin.mwallet.core.interactors.CreateWalletInteractor.CreateWalletResult) r11
            goto L_0x00b4
        L_0x0099:
            com.bitcoin.mwallet.core.repositories.WalletRepository r11 = r10.walletRepository
            if (r2 == 0) goto L_0x009f
            r6 = r2
            goto L_0x00a3
        L_0x009f:
            java.lang.String r6 = r10.getDefaultWalletName(r9)
        L_0x00a3:
            r0.L$0 = r10
            r0.L$1 = r9
            r0.L$2 = r2
            r0.label = r3
            java.lang.Object r11 = r11.createAndSaveNewWallet(r9, r6, r0)
            if (r11 != r1) goto L_0x00b2
            return r1
        L_0x00b2:
            com.bitcoin.mwallet.core.interactors.CreateWalletInteractor$CreateWalletResult r11 = com.bitcoin.mwallet.core.interactors.CreateWalletInteractor.CreateWalletResult.SUCCESS
        L_0x00b4:
            com.bitcoin.mwallet.core.interactors.CreateWalletInteractor$CreateWalletResult r0 = com.bitcoin.mwallet.core.interactors.CreateWalletInteractor.CreateWalletResult.SUCCESS
            if (r11 != r0) goto L_0x0110
            com.bitcoin.mwallet.core.services.AnalyticsService r0 = r10.analyticsService
            r1 = 6
            kotlin.Pair[] r1 = new kotlin.Pair[r1]
            r2 = 0
            java.lang.String r9 = r9.getTicker()
            java.lang.String r6 = "coin"
            kotlin.Pair r9 = kotlin.TuplesKt.m309to(r6, r9)
            r1[r2] = r9
            java.lang.String r9 = "created_by"
            java.lang.String r2 = "user"
            kotlin.Pair r9 = kotlin.TuplesKt.m309to(r9, r2)
            r1[r5] = r9
            java.lang.String r9 = "from_seed"
            java.lang.String r2 = "true"
            kotlin.Pair r9 = kotlin.TuplesKt.m309to(r9, r2)
            r1[r4] = r9
            java.lang.String r9 = "type"
            java.lang.String r2 = "regular"
            kotlin.Pair r9 = kotlin.TuplesKt.m309to(r9, r2)
            r1[r3] = r9
            r9 = 4
            java.lang.Integer r2 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r5)
            java.lang.String r3 = "num_of_copayers"
            kotlin.Pair r2 = kotlin.TuplesKt.m309to(r3, r2)
            r1[r9] = r2
            r9 = 5
            java.lang.Integer r2 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r5)
            java.lang.String r3 = "num_of_signatures"
            kotlin.Pair r2 = kotlin.TuplesKt.m309to(r3, r2)
            r1[r9] = r2
            java.util.Map r9 = kotlin.collections.MapsKt.mapOf(r1)
            java.lang.String r1 = "wallet_created"
            r0.track(r1, r9)
            com.bitcoin.mwallet.core.services.eventstream.EventStreamHandler r9 = r10.eventStreamHandler
            r9.restartStream()
        L_0x0110:
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.core.interactors.CreateWalletInteractor.createWallet(com.bitcoin.mwallet.core.models.Coin, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0094  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x00d0  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0124  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0127  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0161 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0030  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object recoverSoftwareWallet(@org.jetbrains.annotations.NotNull com.bitcoin.mwallet.core.models.Coin r19, @org.jetbrains.annotations.NotNull java.lang.String r20, @org.jetbrains.annotations.NotNull com.bitcoin.mwallet.core.models.credential.CredentialMnemonic r21, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.bitcoin.mwallet.core.interactors.CreateWalletInteractor.CreateWalletResult> r22) {
        /*
            r18 = this;
            r0 = r18
            r1 = r19
            r2 = r20
            r3 = r21
            r4 = r22
            boolean r5 = r4 instanceof com.bitcoin.mwallet.core.interactors.CreateWalletInteractor$recoverSoftwareWallet$1
            if (r5 == 0) goto L_0x001e
            r5 = r4
            com.bitcoin.mwallet.core.interactors.CreateWalletInteractor$recoverSoftwareWallet$1 r5 = (com.bitcoin.mwallet.core.interactors.CreateWalletInteractor$recoverSoftwareWallet$1) r5
            int r6 = r5.label
            r7 = -2147483648(0xffffffff80000000, float:-0.0)
            r6 = r6 & r7
            if (r6 == 0) goto L_0x001e
            int r4 = r5.label
            int r4 = r4 - r7
            r5.label = r4
            goto L_0x0023
        L_0x001e:
            com.bitcoin.mwallet.core.interactors.CreateWalletInteractor$recoverSoftwareWallet$1 r5 = new com.bitcoin.mwallet.core.interactors.CreateWalletInteractor$recoverSoftwareWallet$1
            r5.<init>(r0, r4)
        L_0x0023:
            java.lang.Object r4 = r5.result
            java.lang.Object r6 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r7 = r5.label
            r9 = 3
            r10 = 2
            r11 = 1
            if (r7 == 0) goto L_0x0094
            if (r7 == r11) goto L_0x0077
            if (r7 == r10) goto L_0x0057
            if (r7 != r9) goto L_0x004f
            java.lang.Object r1 = r5.L$4
            kotlin.Pair r1 = (kotlin.Pair) r1
            java.lang.Object r1 = r5.L$3
            com.bitcoin.mwallet.core.models.credential.CredentialMnemonic r1 = (com.bitcoin.mwallet.core.models.credential.CredentialMnemonic) r1
            java.lang.Object r1 = r5.L$2
            java.lang.String r1 = (java.lang.String) r1
            java.lang.Object r1 = r5.L$1
            com.bitcoin.mwallet.core.models.Coin r1 = (com.bitcoin.mwallet.core.models.Coin) r1
            java.lang.Object r2 = r5.L$0
            com.bitcoin.mwallet.core.interactors.CreateWalletInteractor r2 = (com.bitcoin.mwallet.core.interactors.CreateWalletInteractor) r2
            kotlin.ResultKt.throwOnFailure(r4)
            goto L_0x0162
        L_0x004f:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r1
        L_0x0057:
            java.lang.Object r1 = r5.L$4
            kotlin.Pair r1 = (kotlin.Pair) r1
            java.lang.Object r2 = r5.L$3
            com.bitcoin.mwallet.core.models.credential.CredentialMnemonic r2 = (com.bitcoin.mwallet.core.models.credential.CredentialMnemonic) r2
            java.lang.Object r3 = r5.L$2
            java.lang.String r3 = (java.lang.String) r3
            java.lang.Object r7 = r5.L$1
            com.bitcoin.mwallet.core.models.Coin r7 = (com.bitcoin.mwallet.core.models.Coin) r7
            java.lang.Object r12 = r5.L$0
            com.bitcoin.mwallet.core.interactors.CreateWalletInteractor r12 = (com.bitcoin.mwallet.core.interactors.CreateWalletInteractor) r12
            kotlin.ResultKt.throwOnFailure(r4)
            r4 = r2
            r2 = r12
            r16 = r7
            r7 = r1
            r1 = r16
            goto L_0x014c
        L_0x0077:
            java.lang.Object r1 = r5.L$4
            kotlin.Pair r1 = (kotlin.Pair) r1
            java.lang.Object r2 = r5.L$3
            com.bitcoin.mwallet.core.models.credential.CredentialMnemonic r2 = (com.bitcoin.mwallet.core.models.credential.CredentialMnemonic) r2
            java.lang.Object r3 = r5.L$2
            java.lang.String r3 = (java.lang.String) r3
            java.lang.Object r7 = r5.L$1
            com.bitcoin.mwallet.core.models.Coin r7 = (com.bitcoin.mwallet.core.models.Coin) r7
            java.lang.Object r12 = r5.L$0
            com.bitcoin.mwallet.core.interactors.CreateWalletInteractor r12 = (com.bitcoin.mwallet.core.interactors.CreateWalletInteractor) r12
            kotlin.ResultKt.throwOnFailure(r4)
            r16 = r7
            r7 = r1
            r1 = r16
            goto L_0x00bd
        L_0x0094:
            kotlin.ResultKt.throwOnFailure(r4)
            com.bitcoin.mwallet.core.models.wallet.Wallet$Companion r4 = com.bitcoin.mwallet.core.models.wallet.C1261Wallet.Companion
            kotlin.Pair r4 = r4.createFromMnemonic(r1, r2, r3)
            com.bitcoin.mwallet.core.repositories.WalletRepository r7 = r0.walletRepository
            r5.L$0 = r0
            r5.L$1 = r1
            r5.L$2 = r2
            r5.L$3 = r3
            r5.L$4 = r4
            r5.label = r11
            java.lang.Object r7 = r7.getWallets(r5)
            if (r7 != r6) goto L_0x00b2
            return r6
        L_0x00b2:
            r12 = r0
            r16 = r3
            r3 = r2
            r2 = r16
            r17 = r7
            r7 = r4
            r4 = r17
        L_0x00bd:
            java.lang.Iterable r4 = (java.lang.Iterable) r4
            java.util.ArrayList r13 = new java.util.ArrayList
            r13.<init>()
            java.util.Collection r13 = (java.util.Collection) r13
            java.util.Iterator r4 = r4.iterator()
        L_0x00ca:
            boolean r14 = r4.hasNext()
            if (r14 == 0) goto L_0x011a
            java.lang.Object r14 = r4.next()
            r15 = r14
            com.bitcoin.mwallet.core.models.wallet.Wallet r15 = (com.bitcoin.mwallet.core.models.wallet.C1261Wallet) r15
            com.bitcoin.bitcoink.ExtendedPublicKey r11 = r15.getPublicKey()
            com.bitcoin.bitcoink.Network r8 = com.bitcoin.bitcoink.Network.MAIN
            java.lang.String r8 = r11.base58(r8)
            java.lang.Object r11 = r7.getFirst()
            com.bitcoin.mwallet.core.models.wallet.Wallet r11 = (com.bitcoin.mwallet.core.models.wallet.C1261Wallet) r11
            com.bitcoin.bitcoink.ExtendedPublicKey r11 = r11.getPublicKey()
            com.bitcoin.bitcoink.Network r9 = com.bitcoin.bitcoink.Network.MAIN
            java.lang.String r9 = r11.base58(r9)
            boolean r8 = kotlin.jvm.internal.Intrinsics.areEqual(r8, r9)
            if (r8 == 0) goto L_0x0109
            com.bitcoin.mwallet.core.models.Coin r8 = r15.getCoin()
            java.lang.Object r9 = r7.getFirst()
            com.bitcoin.mwallet.core.models.wallet.Wallet r9 = (com.bitcoin.mwallet.core.models.wallet.C1261Wallet) r9
            com.bitcoin.mwallet.core.models.Coin r9 = r9.getCoin()
            if (r8 != r9) goto L_0x0109
            r8 = 1
            goto L_0x010a
        L_0x0109:
            r8 = 0
        L_0x010a:
            java.lang.Boolean r8 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r8)
            boolean r8 = r8.booleanValue()
            if (r8 == 0) goto L_0x0117
            r13.add(r14)
        L_0x0117:
            r9 = 3
            r11 = 1
            goto L_0x00ca
        L_0x011a:
            java.util.List r13 = (java.util.List) r13
            java.util.Collection r13 = (java.util.Collection) r13
            int r4 = r13.size()
            if (r4 <= 0) goto L_0x0127
            com.bitcoin.mwallet.core.interactors.CreateWalletInteractor$CreateWalletResult r1 = com.bitcoin.mwallet.core.interactors.CreateWalletInteractor.CreateWalletResult.DUPLICATE_MNEMONIC
            return r1
        L_0x0127:
            com.bitcoin.mwallet.core.repositories.WalletRepository r4 = r12.walletRepository
            java.lang.Object r8 = r7.getFirst()
            com.bitcoin.mwallet.core.models.wallet.Wallet r8 = (com.bitcoin.mwallet.core.models.wallet.C1261Wallet) r8
            java.lang.Object r9 = r7.getSecond()
            com.bitcoin.mwallet.core.models.credential.Credential r9 = (com.bitcoin.mwallet.core.models.credential.Credential) r9
            com.bitcoin.mwallet.core.repositories.WalletRepository$WalletSaveType r11 = com.bitcoin.mwallet.core.repositories.WalletRepository.WalletSaveType.FROM_MNEMONIC
            r5.L$0 = r12
            r5.L$1 = r1
            r5.L$2 = r3
            r5.L$3 = r2
            r5.L$4 = r7
            r5.label = r10
            java.lang.Object r4 = r4.saveNewWallet(r8, r9, r11, r5)
            if (r4 != r6) goto L_0x014a
            return r6
        L_0x014a:
            r4 = r2
            r2 = r12
        L_0x014c:
            com.bitcoin.mwallet.core.services.WalletRefresherTemp r8 = r2.walletRefresherTemp
            r5.L$0 = r2
            r5.L$1 = r1
            r5.L$2 = r3
            r5.L$3 = r4
            r5.L$4 = r7
            r3 = 3
            r5.label = r3
            java.lang.Object r3 = r8.refreshWalletsAsync(r5)
            if (r3 != r6) goto L_0x0162
            return r6
        L_0x0162:
            com.bitcoin.mwallet.core.services.eventstream.EventStreamHandler r3 = r2.eventStreamHandler
            r3.restartStream()
            com.bitcoin.mwallet.core.services.AnalyticsService r2 = r2.analyticsService
            r3 = 6
            kotlin.Pair[] r3 = new kotlin.Pair[r3]
            java.lang.String r1 = r1.getTicker()
            java.lang.String r4 = "coin"
            kotlin.Pair r1 = kotlin.TuplesKt.m309to(r4, r1)
            r4 = 0
            r3[r4] = r1
            java.lang.String r1 = "created_by"
            java.lang.String r4 = "user"
            kotlin.Pair r1 = kotlin.TuplesKt.m309to(r1, r4)
            r4 = 1
            r3[r4] = r1
            java.lang.String r1 = "from_seed"
            java.lang.String r5 = "true"
            kotlin.Pair r1 = kotlin.TuplesKt.m309to(r1, r5)
            r3[r10] = r1
            java.lang.String r1 = "type"
            java.lang.String r5 = "regular"
            kotlin.Pair r1 = kotlin.TuplesKt.m309to(r1, r5)
            r5 = 3
            r3[r5] = r1
            r1 = 4
            java.lang.Integer r5 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r4)
            java.lang.String r6 = "num_of_copayers"
            kotlin.Pair r5 = kotlin.TuplesKt.m309to(r6, r5)
            r3[r1] = r5
            r1 = 5
            java.lang.Integer r4 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r4)
            java.lang.String r5 = "num_of_signatures"
            kotlin.Pair r4 = kotlin.TuplesKt.m309to(r5, r4)
            r3[r1] = r4
            java.util.Map r1 = kotlin.collections.MapsKt.mapOf(r3)
            java.lang.String r3 = "wallet_created"
            r2.track(r3, r1)
            com.bitcoin.mwallet.core.interactors.CreateWalletInteractor$CreateWalletResult r1 = com.bitcoin.mwallet.core.interactors.CreateWalletInteractor.CreateWalletResult.SUCCESS
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.core.interactors.CreateWalletInteractor.recoverSoftwareWallet(com.bitcoin.mwallet.core.models.Coin, java.lang.String, com.bitcoin.mwallet.core.models.credential.CredentialMnemonic, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x00a5, code lost:
        r10 = r7;
        r11 = r8;
        r7 = r1;
        r8 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x013f, code lost:
        r1 = (com.bitcoin.mwallet.zion.ZionError) r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0142, code lost:
        if (r1 == null) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0144, code lost:
        r3 = new java.lang.StringBuilder();
        r3.append("Failed to create wallet seed zionId=");
        r3.append(r6);
        r3.append(" zionError=");
        r3.append(r1);
        timber.log.Timber.m429e(r3.toString(), new java.lang.Object[0]);
        r3 = r9.zionRepository;
        r4.L$0 = r9;
        r4.L$1 = r8;
        r4.L$2 = r7;
        r4.L$3 = r6;
        r4.L$4 = r2;
        r4.L$5 = r1;
        r4.label = 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0178, code lost:
        if (r3.unregisterWallet(r6, r2, r4) != r5) goto L_0x017b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x017a, code lost:
        return r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x017d, code lost:
        if (r1 == com.bitcoin.mwallet.zion.ZionError.USER_CANCEL) goto L_0x01a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0181, code lost:
        if (r1 != com.bitcoin.mwallet.zion.ZionError.USER_TIMEOUT) goto L_0x0184;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0184, code lost:
        r3 = r9.zionRepository;
        r10 = r2.getIndex();
        r4.L$0 = r9;
        r4.L$1 = r8;
        r4.L$2 = r7;
        r4.L$3 = r6;
        r4.L$4 = r2;
        r4.L$5 = r1;
        r4.label = 4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x019d, code lost:
        if (r3.freeWalletNameByIndex(r10, r4) != r5) goto L_0x01a0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x019f, code lost:
        return r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x01a0, code lost:
        r1 = com.bitcoin.mwallet.core.interactors.CreateWalletInteractor.CreateWalletResult.FAILED;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x01a3, code lost:
        r3 = r9.zionRepository;
        r10 = r2.getIndex();
        r4.L$0 = r9;
        r4.L$1 = r8;
        r4.L$2 = r7;
        r4.L$3 = r6;
        r4.L$4 = r2;
        r4.L$5 = r1;
        r4.label = 3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x01bc, code lost:
        if (r3.freeWalletNameByIndex(r10, r4) != r5) goto L_0x01bf;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x01be, code lost:
        return r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x01bf, code lost:
        r1 = com.bitcoin.mwallet.core.interactors.CreateWalletInteractor.CreateWalletResult.CANCELED;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x01c1, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x01c2, code lost:
        r3 = r9.zionRepository;
        r4.L$0 = r9;
        r4.L$1 = r8;
        r4.L$2 = r7;
        r4.L$3 = r6;
        r4.L$4 = r2;
        r4.L$5 = r1;
        r4.label = 5;
        r3 = r3.getWalletXPub(r6, r8, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x01d7, code lost:
        if (r3 != r5) goto L_0x00a5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x01d9, code lost:
        return r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x01da, code lost:
        r1 = (com.bitcoin.mwallet.zion.ZionResponse) r3;
        r2 = (com.bitcoin.mwallet.zion.ZionXPub) r1.getResult();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x01e3, code lost:
        if (r2 == null) goto L_0x023f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x01e5, code lost:
        r3 = com.bitcoin.mwallet.core.models.wallet.C1261Wallet.Companion.createNewZion(r2, r10);
        r12 = r9.walletRepository;
        r13 = (com.bitcoin.mwallet.core.models.wallet.C1261Wallet) r3.getFirst();
        r14 = (com.bitcoin.mwallet.core.models.credential.Credential) r3.getSecond();
        r15 = com.bitcoin.mwallet.core.repositories.WalletRepository.WalletSaveType.FROM_MNEMONIC;
        r4.L$0 = r9;
        r4.L$1 = r11;
        r4.L$2 = r10;
        r4.L$3 = r6;
        r4.L$4 = r8;
        r4.L$5 = r7;
        r4.L$6 = r1;
        r4.L$7 = r2;
        r4.L$8 = r3;
        r4.label = 6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0214, code lost:
        if (r12.saveNewWallet(r13, r14, r15, r4) != r5) goto L_0x0217;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x0216, code lost:
        return r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x0217, code lost:
        r0 = r1;
        r1 = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x0219, code lost:
        r9 = r1.walletRefresherTemp;
        r4.L$0 = r1;
        r4.L$1 = r11;
        r4.L$2 = r10;
        r4.L$3 = r6;
        r4.L$4 = r8;
        r4.L$5 = r7;
        r4.L$6 = r0;
        r4.L$7 = r2;
        r4.L$8 = r3;
        r4.label = 7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0234, code lost:
        if (r9.refreshWalletsAsync(r4) != r5) goto L_0x0237;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0236, code lost:
        return r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x0237, code lost:
        r1.eventStreamHandler.restartStream();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x023e, code lost:
        return com.bitcoin.mwallet.core.interactors.CreateWalletInteractor.CreateWalletResult.SUCCESS;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x0241, code lost:
        return com.bitcoin.mwallet.core.interactors.CreateWalletInteractor.CreateWalletResult.FAILED;
     */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x0034  */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x005d  */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x008a  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x00ab  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x00c8  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x00e5  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0102  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x011f  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002c  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object recoverZionWallet(@org.jetbrains.annotations.NotNull com.bitcoin.mwallet.core.models.Coin r17, @org.jetbrains.annotations.NotNull java.lang.String r18, @org.jetbrains.annotations.NotNull com.bitcoin.mwallet.zion.ZionId r19, @org.jetbrains.annotations.NotNull com.bitcoin.mwallet.zion.ZionWalletName r20, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.bitcoin.mwallet.core.interactors.CreateWalletInteractor.CreateWalletResult> r21) {
        /*
            r16 = this;
            r0 = r16
            r1 = r19
            r2 = r20
            r3 = r21
            boolean r4 = r3 instanceof com.bitcoin.mwallet.core.interactors.CreateWalletInteractor$recoverZionWallet$1
            if (r4 == 0) goto L_0x001c
            r4 = r3
            com.bitcoin.mwallet.core.interactors.CreateWalletInteractor$recoverZionWallet$1 r4 = (com.bitcoin.mwallet.core.interactors.CreateWalletInteractor$recoverZionWallet$1) r4
            int r5 = r4.label
            r6 = -2147483648(0xffffffff80000000, float:-0.0)
            r5 = r5 & r6
            if (r5 == 0) goto L_0x001c
            int r3 = r4.label
            int r3 = r3 - r6
            r4.label = r3
            goto L_0x0021
        L_0x001c:
            com.bitcoin.mwallet.core.interactors.CreateWalletInteractor$recoverZionWallet$1 r4 = new com.bitcoin.mwallet.core.interactors.CreateWalletInteractor$recoverZionWallet$1
            r4.<init>(r0, r3)
        L_0x0021:
            java.lang.Object r3 = r4.result
            java.lang.Object r5 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r6 = r4.label
            switch(r6) {
                case 0: goto L_0x011f;
                case 1: goto L_0x0102;
                case 2: goto L_0x00e5;
                case 3: goto L_0x00c8;
                case 4: goto L_0x00ab;
                case 5: goto L_0x008a;
                case 6: goto L_0x005d;
                case 7: goto L_0x0034;
                default: goto L_0x002c;
            }
        L_0x002c:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0034:
            java.lang.Object r1 = r4.L$8
            kotlin.Pair r1 = (kotlin.Pair) r1
            java.lang.Object r1 = r4.L$7
            com.bitcoin.mwallet.zion.ZionXPub r1 = (com.bitcoin.mwallet.zion.ZionXPub) r1
            java.lang.Object r1 = r4.L$6
            com.bitcoin.mwallet.zion.ZionResponse r1 = (com.bitcoin.mwallet.zion.ZionResponse) r1
            java.lang.Object r1 = r4.L$5
            com.bitcoin.mwallet.zion.ZionError r1 = (com.bitcoin.mwallet.zion.ZionError) r1
            java.lang.Object r1 = r4.L$4
            com.bitcoin.mwallet.zion.ZionWalletName r1 = (com.bitcoin.mwallet.zion.ZionWalletName) r1
            java.lang.Object r1 = r4.L$3
            com.bitcoin.mwallet.zion.ZionId r1 = (com.bitcoin.mwallet.zion.ZionId) r1
            java.lang.Object r1 = r4.L$2
            java.lang.String r1 = (java.lang.String) r1
            java.lang.Object r1 = r4.L$1
            com.bitcoin.mwallet.core.models.Coin r1 = (com.bitcoin.mwallet.core.models.Coin) r1
            java.lang.Object r1 = r4.L$0
            com.bitcoin.mwallet.core.interactors.CreateWalletInteractor r1 = (com.bitcoin.mwallet.core.interactors.CreateWalletInteractor) r1
            kotlin.ResultKt.throwOnFailure(r3)
            goto L_0x0237
        L_0x005d:
            java.lang.Object r1 = r4.L$8
            kotlin.Pair r1 = (kotlin.Pair) r1
            java.lang.Object r2 = r4.L$7
            com.bitcoin.mwallet.zion.ZionXPub r2 = (com.bitcoin.mwallet.zion.ZionXPub) r2
            java.lang.Object r6 = r4.L$6
            com.bitcoin.mwallet.zion.ZionResponse r6 = (com.bitcoin.mwallet.zion.ZionResponse) r6
            java.lang.Object r7 = r4.L$5
            com.bitcoin.mwallet.zion.ZionError r7 = (com.bitcoin.mwallet.zion.ZionError) r7
            java.lang.Object r8 = r4.L$4
            com.bitcoin.mwallet.zion.ZionWalletName r8 = (com.bitcoin.mwallet.zion.ZionWalletName) r8
            java.lang.Object r9 = r4.L$3
            com.bitcoin.mwallet.zion.ZionId r9 = (com.bitcoin.mwallet.zion.ZionId) r9
            java.lang.Object r10 = r4.L$2
            java.lang.String r10 = (java.lang.String) r10
            java.lang.Object r11 = r4.L$1
            com.bitcoin.mwallet.core.models.Coin r11 = (com.bitcoin.mwallet.core.models.Coin) r11
            java.lang.Object r12 = r4.L$0
            com.bitcoin.mwallet.core.interactors.CreateWalletInteractor r12 = (com.bitcoin.mwallet.core.interactors.CreateWalletInteractor) r12
            kotlin.ResultKt.throwOnFailure(r3)
            r3 = r1
            r0 = r6
            r6 = r9
            r1 = r12
            goto L_0x0219
        L_0x008a:
            java.lang.Object r1 = r4.L$5
            com.bitcoin.mwallet.zion.ZionError r1 = (com.bitcoin.mwallet.zion.ZionError) r1
            java.lang.Object r2 = r4.L$4
            com.bitcoin.mwallet.zion.ZionWalletName r2 = (com.bitcoin.mwallet.zion.ZionWalletName) r2
            java.lang.Object r6 = r4.L$3
            com.bitcoin.mwallet.zion.ZionId r6 = (com.bitcoin.mwallet.zion.ZionId) r6
            java.lang.Object r7 = r4.L$2
            java.lang.String r7 = (java.lang.String) r7
            java.lang.Object r8 = r4.L$1
            com.bitcoin.mwallet.core.models.Coin r8 = (com.bitcoin.mwallet.core.models.Coin) r8
            java.lang.Object r9 = r4.L$0
            com.bitcoin.mwallet.core.interactors.CreateWalletInteractor r9 = (com.bitcoin.mwallet.core.interactors.CreateWalletInteractor) r9
            kotlin.ResultKt.throwOnFailure(r3)
        L_0x00a5:
            r10 = r7
            r11 = r8
            r7 = r1
            r8 = r2
            goto L_0x01da
        L_0x00ab:
            java.lang.Object r1 = r4.L$5
            com.bitcoin.mwallet.zion.ZionError r1 = (com.bitcoin.mwallet.zion.ZionError) r1
            java.lang.Object r1 = r4.L$4
            com.bitcoin.mwallet.zion.ZionWalletName r1 = (com.bitcoin.mwallet.zion.ZionWalletName) r1
            java.lang.Object r1 = r4.L$3
            com.bitcoin.mwallet.zion.ZionId r1 = (com.bitcoin.mwallet.zion.ZionId) r1
            java.lang.Object r1 = r4.L$2
            java.lang.String r1 = (java.lang.String) r1
            java.lang.Object r1 = r4.L$1
            com.bitcoin.mwallet.core.models.Coin r1 = (com.bitcoin.mwallet.core.models.Coin) r1
            java.lang.Object r1 = r4.L$0
            com.bitcoin.mwallet.core.interactors.CreateWalletInteractor r1 = (com.bitcoin.mwallet.core.interactors.CreateWalletInteractor) r1
            kotlin.ResultKt.throwOnFailure(r3)
            goto L_0x01a0
        L_0x00c8:
            java.lang.Object r1 = r4.L$5
            com.bitcoin.mwallet.zion.ZionError r1 = (com.bitcoin.mwallet.zion.ZionError) r1
            java.lang.Object r1 = r4.L$4
            com.bitcoin.mwallet.zion.ZionWalletName r1 = (com.bitcoin.mwallet.zion.ZionWalletName) r1
            java.lang.Object r1 = r4.L$3
            com.bitcoin.mwallet.zion.ZionId r1 = (com.bitcoin.mwallet.zion.ZionId) r1
            java.lang.Object r1 = r4.L$2
            java.lang.String r1 = (java.lang.String) r1
            java.lang.Object r1 = r4.L$1
            com.bitcoin.mwallet.core.models.Coin r1 = (com.bitcoin.mwallet.core.models.Coin) r1
            java.lang.Object r1 = r4.L$0
            com.bitcoin.mwallet.core.interactors.CreateWalletInteractor r1 = (com.bitcoin.mwallet.core.interactors.CreateWalletInteractor) r1
            kotlin.ResultKt.throwOnFailure(r3)
            goto L_0x01bf
        L_0x00e5:
            java.lang.Object r1 = r4.L$5
            com.bitcoin.mwallet.zion.ZionError r1 = (com.bitcoin.mwallet.zion.ZionError) r1
            java.lang.Object r2 = r4.L$4
            com.bitcoin.mwallet.zion.ZionWalletName r2 = (com.bitcoin.mwallet.zion.ZionWalletName) r2
            java.lang.Object r6 = r4.L$3
            com.bitcoin.mwallet.zion.ZionId r6 = (com.bitcoin.mwallet.zion.ZionId) r6
            java.lang.Object r7 = r4.L$2
            java.lang.String r7 = (java.lang.String) r7
            java.lang.Object r8 = r4.L$1
            com.bitcoin.mwallet.core.models.Coin r8 = (com.bitcoin.mwallet.core.models.Coin) r8
            java.lang.Object r9 = r4.L$0
            com.bitcoin.mwallet.core.interactors.CreateWalletInteractor r9 = (com.bitcoin.mwallet.core.interactors.CreateWalletInteractor) r9
            kotlin.ResultKt.throwOnFailure(r3)
            goto L_0x017b
        L_0x0102:
            java.lang.Object r1 = r4.L$4
            com.bitcoin.mwallet.zion.ZionWalletName r1 = (com.bitcoin.mwallet.zion.ZionWalletName) r1
            java.lang.Object r2 = r4.L$3
            com.bitcoin.mwallet.zion.ZionId r2 = (com.bitcoin.mwallet.zion.ZionId) r2
            java.lang.Object r6 = r4.L$2
            java.lang.String r6 = (java.lang.String) r6
            java.lang.Object r7 = r4.L$1
            com.bitcoin.mwallet.core.models.Coin r7 = (com.bitcoin.mwallet.core.models.Coin) r7
            java.lang.Object r8 = r4.L$0
            com.bitcoin.mwallet.core.interactors.CreateWalletInteractor r8 = (com.bitcoin.mwallet.core.interactors.CreateWalletInteractor) r8
            kotlin.ResultKt.throwOnFailure(r3)
            r9 = r8
            r8 = r7
            r7 = r6
            r6 = r2
            r2 = r1
            goto L_0x013f
        L_0x011f:
            kotlin.ResultKt.throwOnFailure(r3)
            com.bitcoin.mwallet.zion.ZionRepository r3 = r0.zionRepository
            r4.L$0 = r0
            r6 = r17
            r4.L$1 = r6
            r7 = r18
            r4.L$2 = r7
            r4.L$3 = r1
            r4.L$4 = r2
            r8 = 1
            r4.label = r8
            java.lang.Object r3 = r3.restoreWalletSeed(r1, r2, r4)
            if (r3 != r5) goto L_0x013c
            return r5
        L_0x013c:
            r9 = r0
            r8 = r6
            r6 = r1
        L_0x013f:
            r1 = r3
            com.bitcoin.mwallet.zion.ZionError r1 = (com.bitcoin.mwallet.zion.ZionError) r1
            if (r1 == 0) goto L_0x01c2
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r10 = "Failed to create wallet seed zionId="
            r3.append(r10)
            r3.append(r6)
            java.lang.String r10 = " zionError="
            r3.append(r10)
            r3.append(r1)
            java.lang.String r3 = r3.toString()
            r10 = 0
            java.lang.Object[] r10 = new java.lang.Object[r10]
            timber.log.Timber.m429e(r3, r10)
            com.bitcoin.mwallet.zion.ZionRepository r3 = r9.zionRepository
            r4.L$0 = r9
            r4.L$1 = r8
            r4.L$2 = r7
            r4.L$3 = r6
            r4.L$4 = r2
            r4.L$5 = r1
            r10 = 2
            r4.label = r10
            java.lang.Object r3 = r3.unregisterWallet(r6, r2, r4)
            if (r3 != r5) goto L_0x017b
            return r5
        L_0x017b:
            com.bitcoin.mwallet.zion.ZionError r3 = com.bitcoin.mwallet.zion.ZionError.USER_CANCEL
            if (r1 == r3) goto L_0x01a3
            com.bitcoin.mwallet.zion.ZionError r3 = com.bitcoin.mwallet.zion.ZionError.USER_TIMEOUT
            if (r1 != r3) goto L_0x0184
            goto L_0x01a3
        L_0x0184:
            com.bitcoin.mwallet.zion.ZionRepository r3 = r9.zionRepository
            int r10 = r2.getIndex()
            r4.L$0 = r9
            r4.L$1 = r8
            r4.L$2 = r7
            r4.L$3 = r6
            r4.L$4 = r2
            r4.L$5 = r1
            r1 = 4
            r4.label = r1
            java.lang.Object r1 = r3.freeWalletNameByIndex(r10, r4)
            if (r1 != r5) goto L_0x01a0
            return r5
        L_0x01a0:
            com.bitcoin.mwallet.core.interactors.CreateWalletInteractor$CreateWalletResult r1 = com.bitcoin.mwallet.core.interactors.CreateWalletInteractor.CreateWalletResult.FAILED
            goto L_0x01c1
        L_0x01a3:
            com.bitcoin.mwallet.zion.ZionRepository r3 = r9.zionRepository
            int r10 = r2.getIndex()
            r4.L$0 = r9
            r4.L$1 = r8
            r4.L$2 = r7
            r4.L$3 = r6
            r4.L$4 = r2
            r4.L$5 = r1
            r1 = 3
            r4.label = r1
            java.lang.Object r1 = r3.freeWalletNameByIndex(r10, r4)
            if (r1 != r5) goto L_0x01bf
            return r5
        L_0x01bf:
            com.bitcoin.mwallet.core.interactors.CreateWalletInteractor$CreateWalletResult r1 = com.bitcoin.mwallet.core.interactors.CreateWalletInteractor.CreateWalletResult.CANCELED
        L_0x01c1:
            return r1
        L_0x01c2:
            com.bitcoin.mwallet.zion.ZionRepository r3 = r9.zionRepository
            r4.L$0 = r9
            r4.L$1 = r8
            r4.L$2 = r7
            r4.L$3 = r6
            r4.L$4 = r2
            r4.L$5 = r1
            r10 = 5
            r4.label = r10
            java.lang.Object r3 = r3.getWalletXPub(r6, r8, r4)
            if (r3 != r5) goto L_0x00a5
            return r5
        L_0x01da:
            r1 = r3
            com.bitcoin.mwallet.zion.ZionResponse r1 = (com.bitcoin.mwallet.zion.ZionResponse) r1
            java.lang.Object r2 = r1.getResult()
            com.bitcoin.mwallet.zion.ZionXPub r2 = (com.bitcoin.mwallet.zion.ZionXPub) r2
            if (r2 == 0) goto L_0x023f
            com.bitcoin.mwallet.core.models.wallet.Wallet$Companion r3 = com.bitcoin.mwallet.core.models.wallet.C1261Wallet.Companion
            kotlin.Pair r3 = r3.createNewZion(r2, r10)
            com.bitcoin.mwallet.core.repositories.WalletRepository r12 = r9.walletRepository
            java.lang.Object r13 = r3.getFirst()
            com.bitcoin.mwallet.core.models.wallet.Wallet r13 = (com.bitcoin.mwallet.core.models.wallet.C1261Wallet) r13
            java.lang.Object r14 = r3.getSecond()
            com.bitcoin.mwallet.core.models.credential.Credential r14 = (com.bitcoin.mwallet.core.models.credential.Credential) r14
            com.bitcoin.mwallet.core.repositories.WalletRepository$WalletSaveType r15 = com.bitcoin.mwallet.core.repositories.WalletRepository.WalletSaveType.FROM_MNEMONIC
            r4.L$0 = r9
            r4.L$1 = r11
            r4.L$2 = r10
            r4.L$3 = r6
            r4.L$4 = r8
            r4.L$5 = r7
            r4.L$6 = r1
            r4.L$7 = r2
            r4.L$8 = r3
            r0 = 6
            r4.label = r0
            java.lang.Object r0 = r12.saveNewWallet(r13, r14, r15, r4)
            if (r0 != r5) goto L_0x0217
            return r5
        L_0x0217:
            r0 = r1
            r1 = r9
        L_0x0219:
            com.bitcoin.mwallet.core.services.WalletRefresherTemp r9 = r1.walletRefresherTemp
            r4.L$0 = r1
            r4.L$1 = r11
            r4.L$2 = r10
            r4.L$3 = r6
            r4.L$4 = r8
            r4.L$5 = r7
            r4.L$6 = r0
            r4.L$7 = r2
            r4.L$8 = r3
            r0 = 7
            r4.label = r0
            java.lang.Object r0 = r9.refreshWalletsAsync(r4)
            if (r0 != r5) goto L_0x0237
            return r5
        L_0x0237:
            com.bitcoin.mwallet.core.services.eventstream.EventStreamHandler r0 = r1.eventStreamHandler
            r0.restartStream()
            com.bitcoin.mwallet.core.interactors.CreateWalletInteractor$CreateWalletResult r0 = com.bitcoin.mwallet.core.interactors.CreateWalletInteractor.CreateWalletResult.SUCCESS
            return r0
        L_0x023f:
            com.bitcoin.mwallet.core.interactors.CreateWalletInteractor$CreateWalletResult r0 = com.bitcoin.mwallet.core.interactors.CreateWalletInteractor.CreateWalletResult.FAILED
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.core.interactors.CreateWalletInteractor.recoverZionWallet(com.bitcoin.mwallet.core.models.Coin, java.lang.String, com.bitcoin.mwallet.zion.ZionId, com.bitcoin.mwallet.zion.ZionWalletName, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x014f, code lost:
        r1 = (com.bitcoin.mwallet.zion.ZionWalletName) r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0152, code lost:
        if (r1 == null) goto L_0x02bb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0154, code lost:
        r2 = r7.zionRepository;
        r8.L$0 = r7;
        r8.L$1 = r6;
        r8.L$2 = r4;
        r8.L$3 = r1;
        r8.label = 2;
        r2 = r2.registerWallet(r1, r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0165, code lost:
        if (r2 != r3) goto L_0x0168;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0167, code lost:
        return r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0168, code lost:
        r2 = (com.bitcoin.mwallet.zion.ZionId) ((com.bitcoin.mwallet.zion.ZionResponse) r2).getResult();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0170, code lost:
        if (r2 != null) goto L_0x018f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0172, code lost:
        r5 = r7.zionRepository;
        r9 = r1.getIndex();
        r8.L$0 = r7;
        r8.L$1 = r6;
        r8.L$2 = r4;
        r8.L$3 = r1;
        r8.L$4 = r2;
        r8.label = 3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0189, code lost:
        if (r5.freeWalletNameByIndex(r9, r8) != r3) goto L_0x018c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x018b, code lost:
        return r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x018e, code lost:
        return com.bitcoin.mwallet.core.interactors.CreateWalletInteractor.CreateWalletResult.FAILED;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x018f, code lost:
        r9 = r7.zionRepository;
        r8.L$0 = r7;
        r8.L$1 = r6;
        r8.L$2 = r4;
        r8.L$3 = r1;
        r8.L$4 = r2;
        r8.label = 4;
        r9 = r9.createWalletSeed(r2, r1, r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x01a2, code lost:
        if (r9 != r3) goto L_0x01a5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x01a4, code lost:
        return r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x01a5, code lost:
        r10 = r7;
        r7 = r4;
        r4 = r2;
        r2 = r9;
        r9 = r6;
        r6 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x01ab, code lost:
        r1 = (com.bitcoin.mwallet.zion.ZionError) r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x01ae, code lost:
        if (r1 == null) goto L_0x0215;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x01b0, code lost:
        r2 = new java.lang.StringBuilder();
        r2.append("Failed to create wallet seed zionId=");
        r2.append(r4);
        r2.append(" zionError=");
        r2.append(r1);
        timber.log.Timber.m429e(r2.toString(), new java.lang.Object[0]);
        r2 = r10.zionRepository;
        r8.L$0 = r10;
        r8.L$1 = r9;
        r8.L$2 = r7;
        r8.L$3 = r6;
        r8.L$4 = r4;
        r8.L$5 = r1;
        r8.label = 5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x01e3, code lost:
        if (r2.unregisterWallet(r4, r6, r8) != r3) goto L_0x01e6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x01e5, code lost:
        return r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x01e6, code lost:
        r5 = r6;
        r6 = r7;
        r7 = r9;
        r9 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x01ec, code lost:
        if (r1 == com.bitcoin.mwallet.zion.ZionError.USER_TIMEOUT) goto L_0x01f6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x01f0, code lost:
        if (r1 != com.bitcoin.mwallet.zion.ZionError.USER_CANCEL) goto L_0x01f3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x01f5, code lost:
        return com.bitcoin.mwallet.core.interactors.CreateWalletInteractor.CreateWalletResult.FAILED;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x01f6, code lost:
        r2 = r9.zionRepository;
        r10 = r5.getIndex();
        r8.L$0 = r9;
        r8.L$1 = r7;
        r8.L$2 = r6;
        r8.L$3 = r5;
        r8.L$4 = r4;
        r8.L$5 = r1;
        r8.label = 6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x020f, code lost:
        if (r2.freeWalletNameByIndex(r10, r8) != r3) goto L_0x0212;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0211, code lost:
        return r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x0214, code lost:
        return com.bitcoin.mwallet.core.interactors.CreateWalletInteractor.CreateWalletResult.CANCELED;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x0215, code lost:
        timber.log.Timber.m426d("Getting WalletXPub", new java.lang.Object[0]);
        r2 = r10.zionRepository;
        r8.L$0 = r10;
        r8.L$1 = r9;
        r8.L$2 = r7;
        r8.L$3 = r6;
        r8.L$4 = r4;
        r8.L$5 = r1;
        r8.label = 7;
        r2 = r2.getWalletXPub(r4, r9, r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0231, code lost:
        if (r2 != r3) goto L_0x0234;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x0233, code lost:
        return r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x0234, code lost:
        r2 = (com.bitcoin.mwallet.zion.ZionXPub) ((com.bitcoin.mwallet.zion.ZionResponse) r2).getResult();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x023c, code lost:
        if (r2 != null) goto L_0x0260;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x023e, code lost:
        r5 = r10.zionRepository;
        r11 = r6.getIndex();
        r8.L$0 = r10;
        r8.L$1 = r9;
        r8.L$2 = r7;
        r8.L$3 = r6;
        r8.L$4 = r4;
        r8.L$5 = r1;
        r8.L$6 = r2;
        r8.label = 8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x025a, code lost:
        if (r5.freeWalletNameByIndex(r11, r8) != r3) goto L_0x025d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x025c, code lost:
        return r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x025f, code lost:
        return com.bitcoin.mwallet.core.interactors.CreateWalletInteractor.CreateWalletResult.FAILED;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x0260, code lost:
        r11 = com.bitcoin.mwallet.core.models.wallet.C1261Wallet.Companion;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x0262, code lost:
        if (r7 == null) goto L_0x0266;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x0264, code lost:
        r12 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x0266, code lost:
        r12 = r10.getDefaultWalletName(r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x026a, code lost:
        r11 = r11.createNewZion(r2, r12);
        r12 = new java.lang.StringBuilder();
        r12.append("Saving new Wallet: ");
        r12.append(r11);
        timber.log.Timber.m426d(r12.toString(), new java.lang.Object[0]);
        r5 = r10.walletRepository;
        r12 = (com.bitcoin.mwallet.core.models.wallet.C1261Wallet) r11.getFirst();
        r13 = (com.bitcoin.mwallet.core.models.credential.Credential) r11.getSecond();
        r8.L$0 = r10;
        r8.L$1 = r9;
        r8.L$2 = r7;
        r8.L$3 = r6;
        r8.L$4 = r4;
        r8.L$5 = r1;
        r8.L$6 = r2;
        r8.L$7 = r11;
        r8.label = 9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x02b5, code lost:
        if (com.bitcoin.mwallet.core.repositories.WalletRepository.saveNewWallet$default(r5, r12, r13, null, r8, 4, null) != r3) goto L_0x02b8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x02b7, code lost:
        return r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x02ba, code lost:
        return com.bitcoin.mwallet.core.interactors.CreateWalletInteractor.CreateWalletResult.SUCCESS;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x02bd, code lost:
        return com.bitcoin.mwallet.core.interactors.CreateWalletInteractor.CreateWalletResult.FAILED_ZION_MAX;
     */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x0034  */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0059  */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x007a  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0097  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x00b4  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x00d1  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x00ef  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0108  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x011c  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x012f  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002c  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ java.lang.Object createZionWallet(@org.jetbrains.annotations.NotNull com.bitcoin.mwallet.core.models.Coin r18, @org.jetbrains.annotations.Nullable java.lang.String r19, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.bitcoin.mwallet.core.interactors.CreateWalletInteractor.CreateWalletResult> r20) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            r2 = r20
            boolean r3 = r2 instanceof com.bitcoin.mwallet.core.interactors.CreateWalletInteractor$createZionWallet$1
            if (r3 == 0) goto L_0x001a
            r3 = r2
            com.bitcoin.mwallet.core.interactors.CreateWalletInteractor$createZionWallet$1 r3 = (com.bitcoin.mwallet.core.interactors.CreateWalletInteractor$createZionWallet$1) r3
            int r4 = r3.label
            r5 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r4 & r5
            if (r4 == 0) goto L_0x001a
            int r2 = r3.label
            int r2 = r2 - r5
            r3.label = r2
            goto L_0x001f
        L_0x001a:
            com.bitcoin.mwallet.core.interactors.CreateWalletInteractor$createZionWallet$1 r3 = new com.bitcoin.mwallet.core.interactors.CreateWalletInteractor$createZionWallet$1
            r3.<init>(r0, r2)
        L_0x001f:
            r8 = r3
            java.lang.Object r2 = r8.result
            java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r4 = r8.label
            r5 = 0
            switch(r4) {
                case 0: goto L_0x012f;
                case 1: goto L_0x011c;
                case 2: goto L_0x0108;
                case 3: goto L_0x00ef;
                case 4: goto L_0x00d1;
                case 5: goto L_0x00b4;
                case 6: goto L_0x0097;
                case 7: goto L_0x007a;
                case 8: goto L_0x0059;
                case 9: goto L_0x0034;
                default: goto L_0x002c;
            }
        L_0x002c:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r1
        L_0x0034:
            java.lang.Object r1 = r8.L$7
            kotlin.Pair r1 = (kotlin.Pair) r1
            java.lang.Object r1 = r8.L$6
            com.bitcoin.mwallet.zion.ZionXPub r1 = (com.bitcoin.mwallet.zion.ZionXPub) r1
            java.lang.Object r1 = r8.L$5
            com.bitcoin.mwallet.zion.ZionError r1 = (com.bitcoin.mwallet.zion.ZionError) r1
            java.lang.Object r1 = r8.L$4
            com.bitcoin.mwallet.zion.ZionId r1 = (com.bitcoin.mwallet.zion.ZionId) r1
            java.lang.Object r1 = r8.L$3
            com.bitcoin.mwallet.zion.ZionWalletName r1 = (com.bitcoin.mwallet.zion.ZionWalletName) r1
            java.lang.Object r1 = r8.L$2
            java.lang.String r1 = (java.lang.String) r1
            java.lang.Object r1 = r8.L$1
            com.bitcoin.mwallet.core.models.Coin r1 = (com.bitcoin.mwallet.core.models.Coin) r1
            java.lang.Object r1 = r8.L$0
            com.bitcoin.mwallet.core.interactors.CreateWalletInteractor r1 = (com.bitcoin.mwallet.core.interactors.CreateWalletInteractor) r1
            kotlin.ResultKt.throwOnFailure(r2)
            goto L_0x02b8
        L_0x0059:
            java.lang.Object r1 = r8.L$6
            com.bitcoin.mwallet.zion.ZionXPub r1 = (com.bitcoin.mwallet.zion.ZionXPub) r1
            java.lang.Object r1 = r8.L$5
            com.bitcoin.mwallet.zion.ZionError r1 = (com.bitcoin.mwallet.zion.ZionError) r1
            java.lang.Object r1 = r8.L$4
            com.bitcoin.mwallet.zion.ZionId r1 = (com.bitcoin.mwallet.zion.ZionId) r1
            java.lang.Object r1 = r8.L$3
            com.bitcoin.mwallet.zion.ZionWalletName r1 = (com.bitcoin.mwallet.zion.ZionWalletName) r1
            java.lang.Object r1 = r8.L$2
            java.lang.String r1 = (java.lang.String) r1
            java.lang.Object r1 = r8.L$1
            com.bitcoin.mwallet.core.models.Coin r1 = (com.bitcoin.mwallet.core.models.Coin) r1
            java.lang.Object r1 = r8.L$0
            com.bitcoin.mwallet.core.interactors.CreateWalletInteractor r1 = (com.bitcoin.mwallet.core.interactors.CreateWalletInteractor) r1
            kotlin.ResultKt.throwOnFailure(r2)
            goto L_0x025d
        L_0x007a:
            java.lang.Object r1 = r8.L$5
            com.bitcoin.mwallet.zion.ZionError r1 = (com.bitcoin.mwallet.zion.ZionError) r1
            java.lang.Object r4 = r8.L$4
            com.bitcoin.mwallet.zion.ZionId r4 = (com.bitcoin.mwallet.zion.ZionId) r4
            java.lang.Object r6 = r8.L$3
            com.bitcoin.mwallet.zion.ZionWalletName r6 = (com.bitcoin.mwallet.zion.ZionWalletName) r6
            java.lang.Object r7 = r8.L$2
            java.lang.String r7 = (java.lang.String) r7
            java.lang.Object r9 = r8.L$1
            com.bitcoin.mwallet.core.models.Coin r9 = (com.bitcoin.mwallet.core.models.Coin) r9
            java.lang.Object r10 = r8.L$0
            com.bitcoin.mwallet.core.interactors.CreateWalletInteractor r10 = (com.bitcoin.mwallet.core.interactors.CreateWalletInteractor) r10
            kotlin.ResultKt.throwOnFailure(r2)
            goto L_0x0234
        L_0x0097:
            java.lang.Object r1 = r8.L$5
            com.bitcoin.mwallet.zion.ZionError r1 = (com.bitcoin.mwallet.zion.ZionError) r1
            java.lang.Object r1 = r8.L$4
            com.bitcoin.mwallet.zion.ZionId r1 = (com.bitcoin.mwallet.zion.ZionId) r1
            java.lang.Object r1 = r8.L$3
            com.bitcoin.mwallet.zion.ZionWalletName r1 = (com.bitcoin.mwallet.zion.ZionWalletName) r1
            java.lang.Object r1 = r8.L$2
            java.lang.String r1 = (java.lang.String) r1
            java.lang.Object r1 = r8.L$1
            com.bitcoin.mwallet.core.models.Coin r1 = (com.bitcoin.mwallet.core.models.Coin) r1
            java.lang.Object r1 = r8.L$0
            com.bitcoin.mwallet.core.interactors.CreateWalletInteractor r1 = (com.bitcoin.mwallet.core.interactors.CreateWalletInteractor) r1
            kotlin.ResultKt.throwOnFailure(r2)
            goto L_0x0212
        L_0x00b4:
            java.lang.Object r1 = r8.L$5
            com.bitcoin.mwallet.zion.ZionError r1 = (com.bitcoin.mwallet.zion.ZionError) r1
            java.lang.Object r4 = r8.L$4
            com.bitcoin.mwallet.zion.ZionId r4 = (com.bitcoin.mwallet.zion.ZionId) r4
            java.lang.Object r5 = r8.L$3
            com.bitcoin.mwallet.zion.ZionWalletName r5 = (com.bitcoin.mwallet.zion.ZionWalletName) r5
            java.lang.Object r6 = r8.L$2
            java.lang.String r6 = (java.lang.String) r6
            java.lang.Object r7 = r8.L$1
            com.bitcoin.mwallet.core.models.Coin r7 = (com.bitcoin.mwallet.core.models.Coin) r7
            java.lang.Object r9 = r8.L$0
            com.bitcoin.mwallet.core.interactors.CreateWalletInteractor r9 = (com.bitcoin.mwallet.core.interactors.CreateWalletInteractor) r9
            kotlin.ResultKt.throwOnFailure(r2)
            goto L_0x01ea
        L_0x00d1:
            java.lang.Object r1 = r8.L$4
            com.bitcoin.mwallet.zion.ZionId r1 = (com.bitcoin.mwallet.zion.ZionId) r1
            java.lang.Object r4 = r8.L$3
            com.bitcoin.mwallet.zion.ZionWalletName r4 = (com.bitcoin.mwallet.zion.ZionWalletName) r4
            java.lang.Object r6 = r8.L$2
            java.lang.String r6 = (java.lang.String) r6
            java.lang.Object r7 = r8.L$1
            com.bitcoin.mwallet.core.models.Coin r7 = (com.bitcoin.mwallet.core.models.Coin) r7
            java.lang.Object r9 = r8.L$0
            com.bitcoin.mwallet.core.interactors.CreateWalletInteractor r9 = (com.bitcoin.mwallet.core.interactors.CreateWalletInteractor) r9
            kotlin.ResultKt.throwOnFailure(r2)
            r10 = r9
            r9 = r7
            r7 = r6
            r6 = r4
            r4 = r1
            goto L_0x01ab
        L_0x00ef:
            java.lang.Object r1 = r8.L$4
            com.bitcoin.mwallet.zion.ZionId r1 = (com.bitcoin.mwallet.zion.ZionId) r1
            java.lang.Object r1 = r8.L$3
            com.bitcoin.mwallet.zion.ZionWalletName r1 = (com.bitcoin.mwallet.zion.ZionWalletName) r1
            java.lang.Object r1 = r8.L$2
            java.lang.String r1 = (java.lang.String) r1
            java.lang.Object r1 = r8.L$1
            com.bitcoin.mwallet.core.models.Coin r1 = (com.bitcoin.mwallet.core.models.Coin) r1
            java.lang.Object r1 = r8.L$0
            com.bitcoin.mwallet.core.interactors.CreateWalletInteractor r1 = (com.bitcoin.mwallet.core.interactors.CreateWalletInteractor) r1
            kotlin.ResultKt.throwOnFailure(r2)
            goto L_0x018c
        L_0x0108:
            java.lang.Object r1 = r8.L$3
            com.bitcoin.mwallet.zion.ZionWalletName r1 = (com.bitcoin.mwallet.zion.ZionWalletName) r1
            java.lang.Object r4 = r8.L$2
            java.lang.String r4 = (java.lang.String) r4
            java.lang.Object r6 = r8.L$1
            com.bitcoin.mwallet.core.models.Coin r6 = (com.bitcoin.mwallet.core.models.Coin) r6
            java.lang.Object r7 = r8.L$0
            com.bitcoin.mwallet.core.interactors.CreateWalletInteractor r7 = (com.bitcoin.mwallet.core.interactors.CreateWalletInteractor) r7
            kotlin.ResultKt.throwOnFailure(r2)
            goto L_0x0168
        L_0x011c:
            java.lang.Object r1 = r8.L$2
            java.lang.String r1 = (java.lang.String) r1
            java.lang.Object r4 = r8.L$1
            com.bitcoin.mwallet.core.models.Coin r4 = (com.bitcoin.mwallet.core.models.Coin) r4
            java.lang.Object r6 = r8.L$0
            com.bitcoin.mwallet.core.interactors.CreateWalletInteractor r6 = (com.bitcoin.mwallet.core.interactors.CreateWalletInteractor) r6
            kotlin.ResultKt.throwOnFailure(r2)
            r7 = r6
            r6 = r4
            r4 = r1
            goto L_0x014f
        L_0x012f:
            kotlin.ResultKt.throwOnFailure(r2)
            com.bitcoin.mwallet.zion.ZionRepository r2 = r0.zionRepository
            com.bitcoin.mwallet.zion.ZionNamePrefix$Companion r4 = com.bitcoin.mwallet.zion.ZionNamePrefix.Companion
            com.bitcoin.mwallet.zion.ZionNamePrefix r4 = r4.from(r1)
            r8.L$0 = r0
            r8.L$1 = r1
            r6 = r19
            r8.L$2 = r6
            r7 = 1
            r8.label = r7
            java.lang.Object r2 = r2.getNextAvailableWalletName(r4, r8)
            if (r2 != r3) goto L_0x014c
            return r3
        L_0x014c:
            r7 = r0
            r4 = r6
            r6 = r1
        L_0x014f:
            r1 = r2
            com.bitcoin.mwallet.zion.ZionWalletName r1 = (com.bitcoin.mwallet.zion.ZionWalletName) r1
            if (r1 == 0) goto L_0x02bb
            com.bitcoin.mwallet.zion.ZionRepository r2 = r7.zionRepository
            r8.L$0 = r7
            r8.L$1 = r6
            r8.L$2 = r4
            r8.L$3 = r1
            r9 = 2
            r8.label = r9
            java.lang.Object r2 = r2.registerWallet(r1, r8)
            if (r2 != r3) goto L_0x0168
            return r3
        L_0x0168:
            com.bitcoin.mwallet.zion.ZionResponse r2 = (com.bitcoin.mwallet.zion.ZionResponse) r2
            java.lang.Object r2 = r2.getResult()
            com.bitcoin.mwallet.zion.ZionId r2 = (com.bitcoin.mwallet.zion.ZionId) r2
            if (r2 != 0) goto L_0x018f
            com.bitcoin.mwallet.zion.ZionRepository r5 = r7.zionRepository
            int r9 = r1.getIndex()
            r8.L$0 = r7
            r8.L$1 = r6
            r8.L$2 = r4
            r8.L$3 = r1
            r8.L$4 = r2
            r1 = 3
            r8.label = r1
            java.lang.Object r1 = r5.freeWalletNameByIndex(r9, r8)
            if (r1 != r3) goto L_0x018c
            return r3
        L_0x018c:
            com.bitcoin.mwallet.core.interactors.CreateWalletInteractor$CreateWalletResult r1 = com.bitcoin.mwallet.core.interactors.CreateWalletInteractor.CreateWalletResult.FAILED
            return r1
        L_0x018f:
            com.bitcoin.mwallet.zion.ZionRepository r9 = r7.zionRepository
            r8.L$0 = r7
            r8.L$1 = r6
            r8.L$2 = r4
            r8.L$3 = r1
            r8.L$4 = r2
            r10 = 4
            r8.label = r10
            java.lang.Object r9 = r9.createWalletSeed(r2, r1, r8)
            if (r9 != r3) goto L_0x01a5
            return r3
        L_0x01a5:
            r10 = r7
            r7 = r4
            r4 = r2
            r2 = r9
            r9 = r6
            r6 = r1
        L_0x01ab:
            r1 = r2
            com.bitcoin.mwallet.zion.ZionError r1 = (com.bitcoin.mwallet.zion.ZionError) r1
            if (r1 == 0) goto L_0x0215
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r11 = "Failed to create wallet seed zionId="
            r2.append(r11)
            r2.append(r4)
            java.lang.String r11 = " zionError="
            r2.append(r11)
            r2.append(r1)
            java.lang.String r2 = r2.toString()
            java.lang.Object[] r5 = new java.lang.Object[r5]
            timber.log.Timber.m429e(r2, r5)
            com.bitcoin.mwallet.zion.ZionRepository r2 = r10.zionRepository
            r8.L$0 = r10
            r8.L$1 = r9
            r8.L$2 = r7
            r8.L$3 = r6
            r8.L$4 = r4
            r8.L$5 = r1
            r5 = 5
            r8.label = r5
            java.lang.Object r2 = r2.unregisterWallet(r4, r6, r8)
            if (r2 != r3) goto L_0x01e6
            return r3
        L_0x01e6:
            r5 = r6
            r6 = r7
            r7 = r9
            r9 = r10
        L_0x01ea:
            com.bitcoin.mwallet.zion.ZionError r2 = com.bitcoin.mwallet.zion.ZionError.USER_TIMEOUT
            if (r1 == r2) goto L_0x01f6
            com.bitcoin.mwallet.zion.ZionError r2 = com.bitcoin.mwallet.zion.ZionError.USER_CANCEL
            if (r1 != r2) goto L_0x01f3
            goto L_0x01f6
        L_0x01f3:
            com.bitcoin.mwallet.core.interactors.CreateWalletInteractor$CreateWalletResult r1 = com.bitcoin.mwallet.core.interactors.CreateWalletInteractor.CreateWalletResult.FAILED
            return r1
        L_0x01f6:
            com.bitcoin.mwallet.zion.ZionRepository r2 = r9.zionRepository
            int r10 = r5.getIndex()
            r8.L$0 = r9
            r8.L$1 = r7
            r8.L$2 = r6
            r8.L$3 = r5
            r8.L$4 = r4
            r8.L$5 = r1
            r1 = 6
            r8.label = r1
            java.lang.Object r1 = r2.freeWalletNameByIndex(r10, r8)
            if (r1 != r3) goto L_0x0212
            return r3
        L_0x0212:
            com.bitcoin.mwallet.core.interactors.CreateWalletInteractor$CreateWalletResult r1 = com.bitcoin.mwallet.core.interactors.CreateWalletInteractor.CreateWalletResult.CANCELED
            return r1
        L_0x0215:
            java.lang.Object[] r2 = new java.lang.Object[r5]
            java.lang.String r11 = "Getting WalletXPub"
            timber.log.Timber.m426d(r11, r2)
            com.bitcoin.mwallet.zion.ZionRepository r2 = r10.zionRepository
            r8.L$0 = r10
            r8.L$1 = r9
            r8.L$2 = r7
            r8.L$3 = r6
            r8.L$4 = r4
            r8.L$5 = r1
            r11 = 7
            r8.label = r11
            java.lang.Object r2 = r2.getWalletXPub(r4, r9, r8)
            if (r2 != r3) goto L_0x0234
            return r3
        L_0x0234:
            com.bitcoin.mwallet.zion.ZionResponse r2 = (com.bitcoin.mwallet.zion.ZionResponse) r2
            java.lang.Object r2 = r2.getResult()
            com.bitcoin.mwallet.zion.ZionXPub r2 = (com.bitcoin.mwallet.zion.ZionXPub) r2
            if (r2 != 0) goto L_0x0260
            com.bitcoin.mwallet.zion.ZionRepository r5 = r10.zionRepository
            int r11 = r6.getIndex()
            r8.L$0 = r10
            r8.L$1 = r9
            r8.L$2 = r7
            r8.L$3 = r6
            r8.L$4 = r4
            r8.L$5 = r1
            r8.L$6 = r2
            r1 = 8
            r8.label = r1
            java.lang.Object r1 = r5.freeWalletNameByIndex(r11, r8)
            if (r1 != r3) goto L_0x025d
            return r3
        L_0x025d:
            com.bitcoin.mwallet.core.interactors.CreateWalletInteractor$CreateWalletResult r1 = com.bitcoin.mwallet.core.interactors.CreateWalletInteractor.CreateWalletResult.FAILED
            return r1
        L_0x0260:
            com.bitcoin.mwallet.core.models.wallet.Wallet$Companion r11 = com.bitcoin.mwallet.core.models.wallet.C1261Wallet.Companion
            if (r7 == 0) goto L_0x0266
            r12 = r7
            goto L_0x026a
        L_0x0266:
            java.lang.String r12 = r10.getDefaultWalletName(r9)
        L_0x026a:
            kotlin.Pair r11 = r11.createNewZion(r2, r12)
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            java.lang.String r13 = "Saving new Wallet: "
            r12.append(r13)
            r12.append(r11)
            java.lang.String r12 = r12.toString()
            java.lang.Object[] r5 = new java.lang.Object[r5]
            timber.log.Timber.m426d(r12, r5)
            com.bitcoin.mwallet.core.repositories.WalletRepository r5 = r10.walletRepository
            java.lang.Object r12 = r11.getFirst()
            com.bitcoin.mwallet.core.models.wallet.Wallet r12 = (com.bitcoin.mwallet.core.models.wallet.C1261Wallet) r12
            java.lang.Object r13 = r11.getSecond()
            com.bitcoin.mwallet.core.models.credential.Credential r13 = (com.bitcoin.mwallet.core.models.credential.Credential) r13
            r14 = 0
            r15 = 4
            r16 = 0
            r8.L$0 = r10
            r8.L$1 = r9
            r8.L$2 = r7
            r8.L$3 = r6
            r8.L$4 = r4
            r8.L$5 = r1
            r8.L$6 = r2
            r8.L$7 = r11
            r1 = 9
            r8.label = r1
            r4 = r5
            r5 = r12
            r6 = r13
            r7 = r14
            r9 = r15
            r10 = r16
            java.lang.Object r1 = com.bitcoin.mwallet.core.repositories.WalletRepository.saveNewWallet$default(r4, r5, r6, r7, r8, r9, r10)
            if (r1 != r3) goto L_0x02b8
            return r3
        L_0x02b8:
            com.bitcoin.mwallet.core.interactors.CreateWalletInteractor$CreateWalletResult r1 = com.bitcoin.mwallet.core.interactors.CreateWalletInteractor.CreateWalletResult.SUCCESS
            return r1
        L_0x02bb:
            com.bitcoin.mwallet.core.interactors.CreateWalletInteractor$CreateWalletResult r1 = com.bitcoin.mwallet.core.interactors.CreateWalletInteractor.CreateWalletResult.FAILED_ZION_MAX
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.core.interactors.CreateWalletInteractor.createZionWallet(com.bitcoin.mwallet.core.models.Coin, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0049  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0076 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0077  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object createDefaultWallets(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r7) {
        /*
            r6 = this;
            boolean r0 = r7 instanceof com.bitcoin.mwallet.core.interactors.CreateWalletInteractor$createDefaultWallets$1
            if (r0 == 0) goto L_0x0014
            r0 = r7
            com.bitcoin.mwallet.core.interactors.CreateWalletInteractor$createDefaultWallets$1 r0 = (com.bitcoin.mwallet.core.interactors.CreateWalletInteractor$createDefaultWallets$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L_0x0019
        L_0x0014:
            com.bitcoin.mwallet.core.interactors.CreateWalletInteractor$createDefaultWallets$1 r0 = new com.bitcoin.mwallet.core.interactors.CreateWalletInteractor$createDefaultWallets$1
            r0.<init>(r6, r7)
        L_0x0019:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x0049
            if (r2 == r4) goto L_0x003d
            if (r2 != r3) goto L_0x0035
            java.lang.Object r1 = r0.L$1
            com.bitcoin.mwallet.core.models.Coin r1 = (com.bitcoin.mwallet.core.models.Coin) r1
            java.lang.Object r0 = r0.L$0
            com.bitcoin.mwallet.core.interactors.CreateWalletInteractor r0 = (com.bitcoin.mwallet.core.interactors.CreateWalletInteractor) r0
            kotlin.ResultKt.throwOnFailure(r7)
            goto L_0x0078
        L_0x0035:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r0)
            throw r7
        L_0x003d:
            java.lang.Object r2 = r0.L$1
            com.bitcoin.mwallet.core.models.Coin r2 = (com.bitcoin.mwallet.core.models.Coin) r2
            java.lang.Object r2 = r0.L$0
            com.bitcoin.mwallet.core.interactors.CreateWalletInteractor r2 = (com.bitcoin.mwallet.core.interactors.CreateWalletInteractor) r2
            kotlin.ResultKt.throwOnFailure(r7)
            goto L_0x0062
        L_0x0049:
            kotlin.ResultKt.throwOnFailure(r7)
            com.bitcoin.mwallet.core.models.Coin r7 = com.bitcoin.mwallet.core.models.Coin.BCH
            com.bitcoin.mwallet.core.repositories.WalletRepository r2 = r6.walletRepository
            java.lang.String r5 = r6.getDefaultWalletName(r7)
            r0.L$0 = r6
            r0.L$1 = r7
            r0.label = r4
            java.lang.Object r7 = r2.createAndSaveNewWallet(r7, r5, r0)
            if (r7 != r1) goto L_0x0061
            return r1
        L_0x0061:
            r2 = r6
        L_0x0062:
            com.bitcoin.mwallet.core.models.Coin r7 = com.bitcoin.mwallet.core.models.Coin.BTC
            com.bitcoin.mwallet.core.repositories.WalletRepository r4 = r2.walletRepository
            java.lang.String r5 = r2.getDefaultWalletName(r7)
            r0.L$0 = r2
            r0.L$1 = r7
            r0.label = r3
            java.lang.Object r7 = r4.createAndSaveNewWallet(r7, r5, r0)
            if (r7 != r1) goto L_0x0077
            return r1
        L_0x0077:
            r0 = r2
        L_0x0078:
            com.bitcoin.mwallet.core.services.eventstream.EventStreamHandler r7 = r0.eventStreamHandler
            r7.restartStream()
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.core.interactors.CreateWalletInteractor.createDefaultWallets(kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final String getDefaultWalletName(Coin coin) {
        int i = WhenMappings.$EnumSwitchMapping$0[coin.ordinal()];
        if (i == 1) {
            String string = this.context.getString(C1018R.string.createwalletcoins_default_wallet_name_bch);
            Intrinsics.checkExpressionValueIsNotNull(string, "context.getString(R.stri…_default_wallet_name_bch)");
            return string;
        } else if (i == 2) {
            String string2 = this.context.getString(C1018R.string.createwalletcoins_default_wallet_name_btc);
            Intrinsics.checkExpressionValueIsNotNull(string2, "context.getString(R.stri…_default_wallet_name_btc)");
            return string2;
        } else {
            throw new NoWhenBranchMatchedException();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0073  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x008c  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00e6 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002b  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object createZionWallets(@org.jetbrains.annotations.NotNull java.util.Set<com.bitcoin.mwallet.zion.ZionXPub> r19, boolean r20, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r21) {
        /*
            r18 = this;
            r0 = r21
            boolean r1 = r0 instanceof com.bitcoin.mwallet.core.interactors.CreateWalletInteractor$createZionWallets$1
            if (r1 == 0) goto L_0x0018
            r1 = r0
            com.bitcoin.mwallet.core.interactors.CreateWalletInteractor$createZionWallets$1 r1 = (com.bitcoin.mwallet.core.interactors.CreateWalletInteractor$createZionWallets$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L_0x0018
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            r2 = r18
            goto L_0x001f
        L_0x0018:
            com.bitcoin.mwallet.core.interactors.CreateWalletInteractor$createZionWallets$1 r1 = new com.bitcoin.mwallet.core.interactors.CreateWalletInteractor$createZionWallets$1
            r2 = r18
            r1.<init>(r2, r0)
        L_0x001f:
            java.lang.Object r0 = r1.result
            java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r4 = r1.label
            r5 = 2
            r6 = 1
            if (r4 == 0) goto L_0x0073
            if (r4 == r6) goto L_0x0046
            if (r4 != r5) goto L_0x003e
            boolean r3 = r1.Z$0
            java.lang.Object r3 = r1.L$1
            java.util.Set r3 = (java.util.Set) r3
            java.lang.Object r1 = r1.L$0
            com.bitcoin.mwallet.core.interactors.CreateWalletInteractor r1 = (com.bitcoin.mwallet.core.interactors.CreateWalletInteractor) r1
            kotlin.ResultKt.throwOnFailure(r0)
            goto L_0x00e7
        L_0x003e:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0046:
            java.lang.Object r4 = r1.L$7
            com.bitcoin.mwallet.core.repositories.WalletRepository$WalletSaveType r4 = (com.bitcoin.mwallet.core.repositories.WalletRepository.WalletSaveType) r4
            java.lang.Object r4 = r1.L$6
            kotlin.Pair r4 = (kotlin.Pair) r4
            java.lang.Object r4 = r1.L$5
            com.bitcoin.mwallet.zion.ZionXPub r4 = (com.bitcoin.mwallet.zion.ZionXPub) r4
            java.lang.Object r4 = r1.L$4
            java.lang.Object r4 = r1.L$3
            java.util.Iterator r4 = (java.util.Iterator) r4
            java.lang.Object r7 = r1.L$2
            java.lang.Iterable r7 = (java.lang.Iterable) r7
            boolean r8 = r1.Z$0
            java.lang.Object r9 = r1.L$1
            java.util.Set r9 = (java.util.Set) r9
            java.lang.Object r10 = r1.L$0
            com.bitcoin.mwallet.core.interactors.CreateWalletInteractor r10 = (com.bitcoin.mwallet.core.interactors.CreateWalletInteractor) r10
            kotlin.ResultKt.throwOnFailure(r0)
            r0 = r1
            r1 = r10
            r17 = r8
            r8 = r3
            r3 = r9
            r9 = r7
            r7 = r17
            goto L_0x00d3
        L_0x0073:
            kotlin.ResultKt.throwOnFailure(r0)
            r0 = r19
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            java.util.Iterator r4 = r0.iterator()
            r7 = r20
            r9 = r0
            r0 = r1
            r1 = r2
            r8 = r3
            r3 = r19
        L_0x0086:
            boolean r10 = r4.hasNext()
            if (r10 == 0) goto L_0x00d5
            java.lang.Object r10 = r4.next()
            r11 = r10
            com.bitcoin.mwallet.zion.ZionXPub r11 = (com.bitcoin.mwallet.zion.ZionXPub) r11
            com.bitcoin.mwallet.core.models.wallet.Wallet$Companion r12 = com.bitcoin.mwallet.core.models.wallet.C1261Wallet.Companion
            com.bitcoin.mwallet.core.models.Coin r13 = r11.getCoin()
            java.lang.String r13 = r1.getDefaultWalletName(r13)
            kotlin.Pair r12 = r12.createNewZion(r11, r13)
            if (r7 == 0) goto L_0x00a6
            com.bitcoin.mwallet.core.repositories.WalletRepository$WalletSaveType r13 = com.bitcoin.mwallet.core.repositories.WalletRepository.WalletSaveType.FROM_MNEMONIC
            goto L_0x00a8
        L_0x00a6:
            com.bitcoin.mwallet.core.repositories.WalletRepository$WalletSaveType r13 = com.bitcoin.mwallet.core.repositories.WalletRepository.WalletSaveType.NEW
        L_0x00a8:
            com.bitcoin.mwallet.core.repositories.WalletRepository r14 = r1.walletRepository
            java.lang.Object r15 = r12.getFirst()
            com.bitcoin.mwallet.core.models.wallet.Wallet r15 = (com.bitcoin.mwallet.core.models.wallet.C1261Wallet) r15
            java.lang.Object r16 = r12.getSecond()
            r5 = r16
            com.bitcoin.mwallet.core.models.credential.Credential r5 = (com.bitcoin.mwallet.core.models.credential.Credential) r5
            r0.L$0 = r1
            r0.L$1 = r3
            r0.Z$0 = r7
            r0.L$2 = r9
            r0.L$3 = r4
            r0.L$4 = r10
            r0.L$5 = r11
            r0.L$6 = r12
            r0.L$7 = r13
            r0.label = r6
            java.lang.Object r5 = r14.saveNewWallet(r15, r5, r13, r0)
            if (r5 != r8) goto L_0x00d3
            return r8
        L_0x00d3:
            r5 = 2
            goto L_0x0086
        L_0x00d5:
            com.bitcoin.mwallet.core.services.WalletRefresherTemp r4 = r1.walletRefresherTemp
            r0.L$0 = r1
            r0.L$1 = r3
            r0.Z$0 = r7
            r3 = 2
            r0.label = r3
            java.lang.Object r0 = r4.refreshWalletsAsync(r0)
            if (r0 != r8) goto L_0x00e7
            return r8
        L_0x00e7:
            com.bitcoin.mwallet.core.services.eventstream.EventStreamHandler r0 = r1.eventStreamHandler
            r0.restartStream()
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.core.interactors.CreateWalletInteractor.createZionWallets(java.util.Set, boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
