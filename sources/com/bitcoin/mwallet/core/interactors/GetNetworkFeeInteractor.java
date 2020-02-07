package com.bitcoin.mwallet.core.interactors;

import com.bitcoin.mwallet.core.preferences.ApplicationPreferences;
import com.bitcoin.mwallet.core.repositories.NetworkFeeRepository;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J%\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b2\u0006\u0010\u000b\u001a\u00020\fH@ø\u0001\u0000¢\u0006\u0002\u0010\rR\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u000e"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/interactors/GetNetworkFeeInteractor;", "", "networkFeeRepository", "Lcom/bitcoin/mwallet/core/repositories/NetworkFeeRepository;", "applicationPreferences", "Lcom/bitcoin/mwallet/core/preferences/ApplicationPreferences;", "(Lcom/bitcoin/mwallet/core/repositories/NetworkFeeRepository;Lcom/bitcoin/mwallet/core/preferences/ApplicationPreferences;)V", "getNetworkFeeAndPreference", "Lkotlin/Pair;", "Lcom/bitcoin/mwallet/core/models/networkfee/NetworkFees;", "Lcom/bitcoin/mwallet/core/models/networkfee/NetworkFeeLevel;", "coin", "Lcom/bitcoin/mwallet/core/models/Coin;", "(Lcom/bitcoin/mwallet/core/models/Coin;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: GetNetworkFeeInteractor.kt */
public final class GetNetworkFeeInteractor {
    private final ApplicationPreferences applicationPreferences;
    private final NetworkFeeRepository networkFeeRepository;

    public GetNetworkFeeInteractor(@NotNull NetworkFeeRepository networkFeeRepository2, @NotNull ApplicationPreferences applicationPreferences2) {
        Intrinsics.checkParameterIsNotNull(networkFeeRepository2, "networkFeeRepository");
        Intrinsics.checkParameterIsNotNull(applicationPreferences2, "applicationPreferences");
        this.networkFeeRepository = networkFeeRepository2;
        this.applicationPreferences = applicationPreferences2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0041  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object getNetworkFeeAndPreference(@org.jetbrains.annotations.NotNull com.bitcoin.mwallet.core.models.Coin r7, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Pair<com.bitcoin.mwallet.core.models.networkfee.NetworkFees, ? extends com.bitcoin.mwallet.core.models.networkfee.NetworkFeeLevel>> r8) {
        /*
            r6 = this;
            boolean r0 = r8 instanceof com.bitcoin.mwallet.core.interactors.GetNetworkFeeInteractor$getNetworkFeeAndPreference$1
            if (r0 == 0) goto L_0x0014
            r0 = r8
            com.bitcoin.mwallet.core.interactors.GetNetworkFeeInteractor$getNetworkFeeAndPreference$1 r0 = (com.bitcoin.mwallet.core.interactors.GetNetworkFeeInteractor$getNetworkFeeAndPreference$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L_0x0019
        L_0x0014:
            com.bitcoin.mwallet.core.interactors.GetNetworkFeeInteractor$getNetworkFeeAndPreference$1 r0 = new com.bitcoin.mwallet.core.interactors.GetNetworkFeeInteractor$getNetworkFeeAndPreference$1
            r0.<init>(r6, r8)
        L_0x0019:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0041
            if (r2 != r3) goto L_0x0039
            java.lang.Object r7 = r0.L$2
            java.lang.String r7 = (java.lang.String) r7
            java.lang.Object r1 = r0.L$1
            com.bitcoin.mwallet.core.models.Coin r1 = (com.bitcoin.mwallet.core.models.Coin) r1
            java.lang.Object r0 = r0.L$0
            com.bitcoin.mwallet.core.interactors.GetNetworkFeeInteractor r0 = (com.bitcoin.mwallet.core.interactors.GetNetworkFeeInteractor) r0
            kotlin.ResultKt.throwOnFailure(r8)
            r5 = r8
            r8 = r7
            r7 = r5
            goto L_0x006f
        L_0x0039:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L_0x0041:
            kotlin.ResultKt.throwOnFailure(r8)
            com.bitcoin.mwallet.core.preferences.ApplicationPreferences r8 = r6.applicationPreferences
            com.bitcoin.mwallet.core.preferences.SharedPreference r2 = com.bitcoin.mwallet.core.preferences.SharedPreference.NETWORK_FEE
            java.lang.String r2 = r2.getKey()
            r4 = 0
            java.lang.String r8 = r8.getString(r2, r4)
            if (r8 == 0) goto L_0x0054
            goto L_0x005e
        L_0x0054:
            com.bitcoin.mwallet.core.Constants r8 = com.bitcoin.mwallet.core.Constants.INSTANCE
            com.bitcoin.mwallet.core.models.networkfee.NetworkFeeLevel r8 = r8.getDefaultNetworkFeeLevel()
            java.lang.String r8 = r8.name()
        L_0x005e:
            com.bitcoin.mwallet.core.repositories.NetworkFeeRepository r2 = r6.networkFeeRepository
            r0.L$0 = r6
            r0.L$1 = r7
            r0.L$2 = r8
            r0.label = r3
            java.lang.Object r7 = r2.getNetworkFee(r7, r0)
            if (r7 != r1) goto L_0x006f
            return r1
        L_0x006f:
            com.bitcoin.mwallet.core.models.networkfee.NetworkFees r7 = (com.bitcoin.mwallet.core.models.networkfee.NetworkFees) r7
            kotlin.Pair r0 = new kotlin.Pair
            com.bitcoin.mwallet.core.models.networkfee.NetworkFeeLevel r8 = com.bitcoin.mwallet.core.models.networkfee.NetworkFeeLevel.valueOf(r8)
            r0.<init>(r7, r8)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.core.interactors.GetNetworkFeeInteractor.getNetworkFeeAndPreference(com.bitcoin.mwallet.core.models.Coin, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
