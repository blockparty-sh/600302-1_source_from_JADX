package com.bitcoin.mwallet.app.flows.onboarding.createuser;

import com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.entity.OldWallet.OldWalletsJsonSource;
import com.bitcoin.mwallet.core.interactors.CreateWalletInteractor;
import com.bitcoin.mwallet.core.repositories.UserRepository;
import com.bitcoin.mwallet.core.repositories.WalletRepository;
import com.bitcoin.mwallet.core.services.notification.NotificationService;
import com.bitcoin.mwallet.zion.ZionRepository;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eJ\u0011\u0010\u000f\u001a\u00020\u0010H@ø\u0001\u0000¢\u0006\u0002\u0010\u0011J\u0011\u0010\u0012\u001a\u00020\u0013H@ø\u0001\u0000¢\u0006\u0002\u0010\u0011J\u0011\u0010\u0014\u001a\u00020\u0013H@ø\u0001\u0000¢\u0006\u0002\u0010\u0011J\u0011\u0010\u0015\u001a\u00020\u0010H@ø\u0001\u0000¢\u0006\u0002\u0010\u0011J\u0011\u0010\u0016\u001a\u00020\u0013H@ø\u0001\u0000¢\u0006\u0002\u0010\u0011J\u0011\u0010\u0017\u001a\u00020\u0013H@ø\u0001\u0000¢\u0006\u0002\u0010\u0011R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u0018"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/onboarding/createuser/CreateUserInteractor;", "", "userRepository", "Lcom/bitcoin/mwallet/core/repositories/UserRepository;", "zionRepository", "Lcom/bitcoin/mwallet/zion/ZionRepository;", "createWalletInteractor", "Lcom/bitcoin/mwallet/core/interactors/CreateWalletInteractor;", "oldWalletFile", "Lcom/bitcoin/mwallet/app/flows/onboarding/migrateprofile/entity/OldWallet$OldWalletsJsonSource;", "walletRepository", "Lcom/bitcoin/mwallet/core/repositories/WalletRepository;", "notificationService", "Lcom/bitcoin/mwallet/core/services/notification/NotificationService;", "(Lcom/bitcoin/mwallet/core/repositories/UserRepository;Lcom/bitcoin/mwallet/zion/ZionRepository;Lcom/bitcoin/mwallet/core/interactors/CreateWalletInteractor;Lcom/bitcoin/mwallet/app/flows/onboarding/migrateprofile/entity/OldWallet$OldWalletsJsonSource;Lcom/bitcoin/mwallet/core/repositories/WalletRepository;Lcom/bitcoin/mwallet/core/services/notification/NotificationService;)V", "createDefaultWallets", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "hasZion", "", "oldWalletFileExists", "registerRegionNotification", "scanAndRestoreZionWallets", "userExists", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: CreateUserInteractor.kt */
public final class CreateUserInteractor {
    private final CreateWalletInteractor createWalletInteractor;
    private final NotificationService notificationService;
    private final OldWalletsJsonSource oldWalletFile;
    private final UserRepository userRepository;
    private final WalletRepository walletRepository;
    private final ZionRepository zionRepository;

    public CreateUserInteractor(@NotNull UserRepository userRepository2, @NotNull ZionRepository zionRepository2, @NotNull CreateWalletInteractor createWalletInteractor2, @NotNull OldWalletsJsonSource oldWalletsJsonSource, @NotNull WalletRepository walletRepository2, @NotNull NotificationService notificationService2) {
        Intrinsics.checkParameterIsNotNull(userRepository2, "userRepository");
        Intrinsics.checkParameterIsNotNull(zionRepository2, "zionRepository");
        Intrinsics.checkParameterIsNotNull(createWalletInteractor2, "createWalletInteractor");
        Intrinsics.checkParameterIsNotNull(oldWalletsJsonSource, "oldWalletFile");
        Intrinsics.checkParameterIsNotNull(walletRepository2, "walletRepository");
        Intrinsics.checkParameterIsNotNull(notificationService2, "notificationService");
        this.userRepository = userRepository2;
        this.zionRepository = zionRepository2;
        this.createWalletInteractor = createWalletInteractor2;
        this.oldWalletFile = oldWalletsJsonSource;
        this.walletRepository = walletRepository2;
        this.notificationService = notificationService2;
    }

    @Nullable
    public final Object hasZion(@NotNull Continuation<? super Boolean> continuation) {
        return this.zionRepository.hasZion(continuation);
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x00a8  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00d6  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0108  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0134 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x013f  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0146  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x014d  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0173  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0178  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x019c A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002b  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object scanAndRestoreZionWallets(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Boolean> r19) {
        /*
            r18 = this;
            r0 = r18
            r1 = r19
            boolean r2 = r1 instanceof com.bitcoin.mwallet.app.flows.onboarding.createuser.CreateUserInteractor$scanAndRestoreZionWallets$1
            if (r2 == 0) goto L_0x0018
            r2 = r1
            com.bitcoin.mwallet.app.flows.onboarding.createuser.CreateUserInteractor$scanAndRestoreZionWallets$1 r2 = (com.bitcoin.mwallet.app.flows.onboarding.createuser.CreateUserInteractor$scanAndRestoreZionWallets$1) r2
            int r3 = r2.label
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r3 & r4
            if (r3 == 0) goto L_0x0018
            int r1 = r2.label
            int r1 = r1 - r4
            r2.label = r1
            goto L_0x001d
        L_0x0018:
            com.bitcoin.mwallet.app.flows.onboarding.createuser.CreateUserInteractor$scanAndRestoreZionWallets$1 r2 = new com.bitcoin.mwallet.app.flows.onboarding.createuser.CreateUserInteractor$scanAndRestoreZionWallets$1
            r2.<init>(r0, r1)
        L_0x001d:
            java.lang.Object r1 = r2.result
            java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r4 = r2.label
            r5 = 4
            r6 = 3
            r7 = 2
            r8 = 1
            if (r4 == 0) goto L_0x00a8
            if (r4 == r8) goto L_0x00a0
            if (r4 == r7) goto L_0x005f
            if (r4 == r6) goto L_0x004d
            if (r4 != r5) goto L_0x0045
            java.lang.Object r3 = r2.L$2
            java.util.Set r3 = (java.util.Set) r3
            java.lang.Object r3 = r2.L$1
            java.util.List r3 = (java.util.List) r3
            java.lang.Object r2 = r2.L$0
            com.bitcoin.mwallet.app.flows.onboarding.createuser.CreateUserInteractor r2 = (com.bitcoin.mwallet.app.flows.onboarding.createuser.CreateUserInteractor) r2
            kotlin.ResultKt.throwOnFailure(r1)
        L_0x0042:
            r0 = 1
            goto L_0x019d
        L_0x0045:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r1
        L_0x004d:
            java.lang.Object r4 = r2.L$2
            java.util.Set r4 = (java.util.Set) r4
            java.lang.Object r6 = r2.L$1
            java.util.List r6 = (java.util.List) r6
            java.lang.Object r7 = r2.L$0
            com.bitcoin.mwallet.app.flows.onboarding.createuser.CreateUserInteractor r7 = (com.bitcoin.mwallet.app.flows.onboarding.createuser.CreateUserInteractor) r7
            kotlin.ResultKt.throwOnFailure(r1)
            r1 = r7
            goto L_0x018b
        L_0x005f:
            java.lang.Object r4 = r2.L$12
            com.bitcoin.mwallet.core.models.Coin r4 = (com.bitcoin.mwallet.core.models.Coin) r4
            java.lang.Object r4 = r2.L$11
            java.lang.Object r4 = r2.L$10
            java.util.Iterator r4 = (java.util.Iterator) r4
            java.lang.Object r9 = r2.L$9
            java.lang.Iterable r9 = (java.lang.Iterable) r9
            java.lang.Object r10 = r2.L$8
            com.bitcoin.mwallet.zion.ZionNamePrefix r10 = (com.bitcoin.mwallet.zion.ZionNamePrefix) r10
            java.lang.Object r11 = r2.L$7
            com.bitcoin.mwallet.zion.ZionId r11 = (com.bitcoin.mwallet.zion.ZionId) r11
            java.lang.Object r12 = r2.L$6
            kotlin.Pair r12 = (kotlin.Pair) r12
            java.lang.Object r13 = r2.L$5
            java.lang.Object r14 = r2.L$4
            java.util.Iterator r14 = (java.util.Iterator) r14
            java.lang.Object r15 = r2.L$3
            java.lang.Iterable r15 = (java.lang.Iterable) r15
            java.lang.Object r5 = r2.L$2
            java.util.Set r5 = (java.util.Set) r5
            java.lang.Object r6 = r2.L$1
            java.util.List r6 = (java.util.List) r6
            java.lang.Object r7 = r2.L$0
            com.bitcoin.mwallet.app.flows.onboarding.createuser.CreateUserInteractor r7 = (com.bitcoin.mwallet.app.flows.onboarding.createuser.CreateUserInteractor) r7
            kotlin.ResultKt.throwOnFailure(r1)
            r0 = r1
            r1 = r7
            r7 = r14
            r14 = r9
            r9 = r15
            r15 = 2
            r16 = r3
            r3 = r2
            r2 = r5
            r5 = r16
            goto L_0x0135
        L_0x00a0:
            java.lang.Object r4 = r2.L$0
            com.bitcoin.mwallet.app.flows.onboarding.createuser.CreateUserInteractor r4 = (com.bitcoin.mwallet.app.flows.onboarding.createuser.CreateUserInteractor) r4
            kotlin.ResultKt.throwOnFailure(r1)
            goto L_0x00b9
        L_0x00a8:
            kotlin.ResultKt.throwOnFailure(r1)
            com.bitcoin.mwallet.zion.ZionRepository r1 = r0.zionRepository
            r2.L$0 = r0
            r2.label = r8
            java.lang.Object r1 = r1.scanAndRestoreZionWallets(r2)
            if (r1 != r3) goto L_0x00b8
            return r3
        L_0x00b8:
            r4 = r0
        L_0x00b9:
            java.util.List r1 = (java.util.List) r1
            java.util.HashSet r5 = new java.util.HashSet
            r5.<init>()
            java.util.Set r5 = (java.util.Set) r5
            r6 = r1
            java.lang.Iterable r6 = (java.lang.Iterable) r6
            java.util.Iterator r7 = r6.iterator()
            r16 = r6
            r6 = r1
            r1 = r4
            r4 = r5
            r5 = r16
        L_0x00d0:
            boolean r9 = r7.hasNext()
            if (r9 == 0) goto L_0x014d
            java.lang.Object r9 = r7.next()
            r10 = r9
            kotlin.Pair r10 = (kotlin.Pair) r10
            java.lang.Object r11 = r10.component1()
            com.bitcoin.mwallet.zion.ZionId r11 = (com.bitcoin.mwallet.zion.ZionId) r11
            java.lang.Object r12 = r10.component2()
            com.bitcoin.mwallet.zion.ZionNamePrefix r12 = (com.bitcoin.mwallet.zion.ZionNamePrefix) r12
            java.util.EnumSet r13 = r12.toCoins()
            java.lang.Iterable r13 = (java.lang.Iterable) r13
            java.util.Iterator r14 = r13.iterator()
            r16 = r3
            r3 = r2
            r2 = r4
            r4 = r14
            r14 = r13
            r13 = r9
            r9 = r5
            r5 = r16
            r17 = r12
            r12 = r10
            r10 = r17
        L_0x0102:
            boolean r15 = r4.hasNext()
            if (r15 == 0) goto L_0x0146
            java.lang.Object r15 = r4.next()
            r8 = r15
            com.bitcoin.mwallet.core.models.Coin r8 = (com.bitcoin.mwallet.core.models.Coin) r8
            com.bitcoin.mwallet.zion.ZionRepository r0 = r1.zionRepository
            r3.L$0 = r1
            r3.L$1 = r6
            r3.L$2 = r2
            r3.L$3 = r9
            r3.L$4 = r7
            r3.L$5 = r13
            r3.L$6 = r12
            r3.L$7 = r11
            r3.L$8 = r10
            r3.L$9 = r14
            r3.L$10 = r4
            r3.L$11 = r15
            r3.L$12 = r8
            r15 = 2
            r3.label = r15
            java.lang.Object r0 = r0.getWalletXPub(r11, r8, r3)
            if (r0 != r5) goto L_0x0135
            return r5
        L_0x0135:
            com.bitcoin.mwallet.zion.ZionResponse r0 = (com.bitcoin.mwallet.zion.ZionResponse) r0
            java.lang.Object r0 = r0.getResult()
            com.bitcoin.mwallet.zion.ZionXPub r0 = (com.bitcoin.mwallet.zion.ZionXPub) r0
            if (r0 == 0) goto L_0x0142
            r2.add(r0)
        L_0x0142:
            r8 = 1
            r0 = r18
            goto L_0x0102
        L_0x0146:
            r0 = r18
            r4 = r2
            r2 = r3
            r3 = r5
            r5 = r9
            goto L_0x00d0
        L_0x014d:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r5 = "Restored "
            r0.append(r5)
            int r5 = r4.size()
            r0.append(r5)
            java.lang.String r5 = " Zion xPubs"
            r0.append(r5)
            java.lang.String r0 = r0.toString()
            r5 = 0
            java.lang.Object[] r7 = new java.lang.Object[r5]
            timber.log.Timber.m426d(r0, r7)
            boolean r0 = r4.isEmpty()
            if (r0 == 0) goto L_0x0178
            java.lang.Boolean r0 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r5)
            return r0
        L_0x0178:
            com.bitcoin.mwallet.core.interactors.CreateWalletInteractor r0 = r1.createWalletInteractor
            r2.L$0 = r1
            r2.L$1 = r6
            r2.L$2 = r4
            r5 = 3
            r2.label = r5
            r5 = 1
            java.lang.Object r0 = r0.createZionWallets(r4, r5, r2)
            if (r0 != r3) goto L_0x018b
            return r3
        L_0x018b:
            com.bitcoin.mwallet.core.repositories.UserRepository r0 = r1.userRepository
            r2.L$0 = r1
            r2.L$1 = r6
            r2.L$2 = r4
            r1 = 4
            r2.label = r1
            java.lang.Object r0 = r0.getOrCreateUser(r2)
            if (r0 != r3) goto L_0x0042
            return r3
        L_0x019d:
            java.lang.Boolean r0 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.app.flows.onboarding.createuser.CreateUserInteractor.scanAndRestoreZionWallets(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0045  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x006b A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x006c  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object createDefaultWallets(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
        /*
            r7 = this;
            boolean r0 = r8 instanceof com.bitcoin.mwallet.app.flows.onboarding.createuser.CreateUserInteractor$createDefaultWallets$1
            if (r0 == 0) goto L_0x0014
            r0 = r8
            com.bitcoin.mwallet.app.flows.onboarding.createuser.CreateUserInteractor$createDefaultWallets$1 r0 = (com.bitcoin.mwallet.app.flows.onboarding.createuser.CreateUserInteractor$createDefaultWallets$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L_0x0019
        L_0x0014:
            com.bitcoin.mwallet.app.flows.onboarding.createuser.CreateUserInteractor$createDefaultWallets$1 r0 = new com.bitcoin.mwallet.app.flows.onboarding.createuser.CreateUserInteractor$createDefaultWallets$1
            r0.<init>(r7, r8)
        L_0x0019:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x0045
            if (r2 == r4) goto L_0x003b
            if (r2 != r3) goto L_0x0033
            long r1 = r0.J$0
            java.lang.Object r0 = r0.L$0
            com.bitcoin.mwallet.app.flows.onboarding.createuser.CreateUserInteractor r0 = (com.bitcoin.mwallet.app.flows.onboarding.createuser.CreateUserInteractor) r0
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x006d
        L_0x0033:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L_0x003b:
            long r4 = r0.J$0
            java.lang.Object r2 = r0.L$0
            com.bitcoin.mwallet.app.flows.onboarding.createuser.CreateUserInteractor r2 = (com.bitcoin.mwallet.app.flows.onboarding.createuser.CreateUserInteractor) r2
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x005d
        L_0x0045:
            kotlin.ResultKt.throwOnFailure(r8)
            long r5 = java.lang.System.currentTimeMillis()
            com.bitcoin.mwallet.core.interactors.CreateWalletInteractor r8 = r7.createWalletInteractor
            r0.L$0 = r7
            r0.J$0 = r5
            r0.label = r4
            java.lang.Object r8 = r8.createDefaultWallets(r0)
            if (r8 != r1) goto L_0x005b
            return r1
        L_0x005b:
            r2 = r7
            r4 = r5
        L_0x005d:
            com.bitcoin.mwallet.core.repositories.UserRepository r8 = r2.userRepository
            r0.L$0 = r2
            r0.J$0 = r4
            r0.label = r3
            java.lang.Object r8 = r8.getOrCreateUser(r0)
            if (r8 != r1) goto L_0x006c
            return r1
        L_0x006c:
            r1 = r4
        L_0x006d:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r0 = "Create user and default wallets in "
            r8.append(r0)
            long r3 = java.lang.System.currentTimeMillis()
            long r3 = r3 - r1
            r8.append(r3)
            java.lang.String r0 = " ms"
            r8.append(r0)
            java.lang.String r8 = r8.toString()
            r0 = 0
            java.lang.Object[] r0 = new java.lang.Object[r0]
            timber.log.Timber.m426d(r8, r0)
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.app.flows.onboarding.createuser.CreateUserInteractor.createDefaultWallets(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0046  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0070 A[LOOP:0: B:19:0x006a->B:21:0x0070, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x00a1  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object registerRegionNotification(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r11) {
        /*
            r10 = this;
            boolean r0 = r11 instanceof com.bitcoin.mwallet.app.flows.onboarding.createuser.CreateUserInteractor$registerRegionNotification$1
            if (r0 == 0) goto L_0x0014
            r0 = r11
            com.bitcoin.mwallet.app.flows.onboarding.createuser.CreateUserInteractor$registerRegionNotification$1 r0 = (com.bitcoin.mwallet.app.flows.onboarding.createuser.CreateUserInteractor$registerRegionNotification$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r11 = r0.label
            int r11 = r11 - r2
            r0.label = r11
            goto L_0x0019
        L_0x0014:
            com.bitcoin.mwallet.app.flows.onboarding.createuser.CreateUserInteractor$registerRegionNotification$1 r0 = new com.bitcoin.mwallet.app.flows.onboarding.createuser.CreateUserInteractor$registerRegionNotification$1
            r0.<init>(r10, r11)
        L_0x0019:
            java.lang.Object r11 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x0046
            if (r2 == r4) goto L_0x003e
            if (r2 != r3) goto L_0x0036
            java.lang.Object r1 = r0.L$1
            java.util.Set r1 = (java.util.Set) r1
            java.lang.Object r0 = r0.L$0
            com.bitcoin.mwallet.app.flows.onboarding.createuser.CreateUserInteractor r0 = (com.bitcoin.mwallet.app.flows.onboarding.createuser.CreateUserInteractor) r0
            kotlin.ResultKt.throwOnFailure(r11)
            goto L_0x00e0
        L_0x0036:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r0)
            throw r11
        L_0x003e:
            java.lang.Object r2 = r0.L$0
            com.bitcoin.mwallet.app.flows.onboarding.createuser.CreateUserInteractor r2 = (com.bitcoin.mwallet.app.flows.onboarding.createuser.CreateUserInteractor) r2
            kotlin.ResultKt.throwOnFailure(r11)
            goto L_0x0057
        L_0x0046:
            kotlin.ResultKt.throwOnFailure(r11)
            com.bitcoin.mwallet.core.repositories.WalletRepository r11 = r10.walletRepository
            r0.L$0 = r10
            r0.label = r4
            java.lang.Object r11 = r11.getWallets(r0)
            if (r11 != r1) goto L_0x0056
            return r1
        L_0x0056:
            r2 = r10
        L_0x0057:
            java.lang.Iterable r11 = (java.lang.Iterable) r11
            java.util.ArrayList r5 = new java.util.ArrayList
            r6 = 10
            int r6 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r11, r6)
            r5.<init>(r6)
            java.util.Collection r5 = (java.util.Collection) r5
            java.util.Iterator r11 = r11.iterator()
        L_0x006a:
            boolean r6 = r11.hasNext()
            if (r6 == 0) goto L_0x008f
            java.lang.Object r6 = r11.next()
            com.bitcoin.mwallet.core.models.wallet.Wallet r6 = (com.bitcoin.mwallet.core.models.wallet.C1261Wallet) r6
            com.bitcoin.mwallet.core.utils.SignedStub$SignedRequest r7 = new com.bitcoin.mwallet.core.utils.SignedStub$SignedRequest
            com.bitcoin.mwallet.core.models.wallet.WalletId r8 = r6.getId()
            com.bitcoin.mwallet.core.models.Copayers r9 = r6.getCopayers()
            com.bitcoin.mwallet.core.models.CopayerId r9 = r9.getWalletCopayerId()
            com.bitcoin.mwallet.core.utils.signature.SigningKey r6 = r6.getSigningKey()
            r7.<init>(r8, r9, r6)
            r5.add(r7)
            goto L_0x006a
        L_0x008f:
            java.util.List r5 = (java.util.List) r5
            java.lang.Iterable r5 = (java.lang.Iterable) r5
            java.util.Set r11 = kotlin.collections.CollectionsKt.toSet(r5)
            r5 = r11
            java.util.Collection r5 = (java.util.Collection) r5
            boolean r5 = r5.isEmpty()
            r4 = r4 ^ r5
            if (r4 == 0) goto L_0x00e0
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "Registering Region "
            r4.append(r5)
            java.util.Locale r5 = java.util.Locale.getDefault()
            r4.append(r5)
            java.lang.String r5 = " for "
            r4.append(r5)
            r4.append(r11)
            java.lang.String r4 = r4.toString()
            r5 = 0
            java.lang.Object[] r5 = new java.lang.Object[r5]
            timber.log.Timber.m426d(r4, r5)
            com.bitcoin.mwallet.core.services.notification.NotificationService r4 = r2.notificationService
            java.util.Locale r5 = java.util.Locale.getDefault()
            java.lang.String r5 = r5.toString()
            java.lang.String r6 = "Locale.getDefault().toString()"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r5, r6)
            r0.L$0 = r2
            r0.L$1 = r11
            r0.label = r3
            java.lang.Object r11 = r4.registerRegion(r11, r5, r0)
            if (r11 != r1) goto L_0x00e0
            return r1
        L_0x00e0:
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.app.flows.onboarding.createuser.CreateUserInteractor.registerRegionNotification(kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Nullable
    public final Object userExists(@NotNull Continuation<? super Boolean> continuation) {
        return this.userRepository.userExists(continuation);
    }

    @Nullable
    public final Object oldWalletFileExists(@NotNull Continuation<? super Boolean> continuation) {
        return this.oldWalletFile.fileExists(continuation);
    }
}
