package com.bitcoin.mwallet.app.flows.home.home;

import com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.entity.OldWallet.OldWalletsJsonSource;
import com.bitcoin.mwallet.core.repositories.UserRepository;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0011\u0010\u0007\u001a\u00020\bH@ø\u0001\u0000¢\u0006\u0002\u0010\tR\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\n"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/home/home/HomeInteractor;", "", "userService", "Lcom/bitcoin/mwallet/core/repositories/UserRepository;", "oldWalletFile", "Lcom/bitcoin/mwallet/app/flows/onboarding/migrateprofile/entity/OldWallet$OldWalletsJsonSource;", "(Lcom/bitcoin/mwallet/core/repositories/UserRepository;Lcom/bitcoin/mwallet/app/flows/onboarding/migrateprofile/entity/OldWallet$OldWalletsJsonSource;)V", "needsOnboarding", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: HomeInteractor.kt */
public final class HomeInteractor {
    private final OldWalletsJsonSource oldWalletFile;
    private final UserRepository userService;

    public HomeInteractor(@NotNull UserRepository userRepository, @NotNull OldWalletsJsonSource oldWalletsJsonSource) {
        Intrinsics.checkParameterIsNotNull(userRepository, "userService");
        Intrinsics.checkParameterIsNotNull(oldWalletsJsonSource, "oldWalletFile");
        this.userService = userRepository;
        this.oldWalletFile = oldWalletsJsonSource;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object needsOnboarding(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Boolean> r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof com.bitcoin.mwallet.app.flows.home.home.HomeInteractor$needsOnboarding$1
            if (r0 == 0) goto L_0x0014
            r0 = r5
            com.bitcoin.mwallet.app.flows.home.home.HomeInteractor$needsOnboarding$1 r0 = (com.bitcoin.mwallet.app.flows.home.home.HomeInteractor$needsOnboarding$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r5 = r0.label
            int r5 = r5 - r2
            r0.label = r5
            goto L_0x0019
        L_0x0014:
            com.bitcoin.mwallet.app.flows.home.home.HomeInteractor$needsOnboarding$1 r0 = new com.bitcoin.mwallet.app.flows.home.home.HomeInteractor$needsOnboarding$1
            r0.<init>(r4, r5)
        L_0x0019:
            java.lang.Object r5 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0036
            if (r2 != r3) goto L_0x002e
            java.lang.Object r0 = r0.L$0
            com.bitcoin.mwallet.app.flows.home.home.HomeInteractor r0 = (com.bitcoin.mwallet.app.flows.home.home.HomeInteractor) r0
            kotlin.ResultKt.throwOnFailure(r5)
            goto L_0x0046
        L_0x002e:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L_0x0036:
            kotlin.ResultKt.throwOnFailure(r5)
            com.bitcoin.mwallet.core.repositories.UserRepository r5 = r4.userService
            r0.L$0 = r4
            r0.label = r3
            java.lang.Object r5 = r5.userExists(r0)
            if (r5 != r1) goto L_0x0046
            return r1
        L_0x0046:
            java.lang.Boolean r5 = (java.lang.Boolean) r5
            boolean r5 = r5.booleanValue()
            r5 = r5 ^ r3
            java.lang.Boolean r5 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r5)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.app.flows.home.home.HomeInteractor.needsOnboarding(kotlin.coroutines.Continuation):java.lang.Object");
    }
}
