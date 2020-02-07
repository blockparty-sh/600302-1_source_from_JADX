package com.bitcoin.mwallet.core.interactors;

import androidx.lifecycle.LiveData;
import com.bitcoin.mwallet.core.models.Coin;
import com.bitcoin.mwallet.core.models.wallet.C1261Wallet;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import com.bitcoin.mwallet.core.repositories.WalletRepository;
import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0013\u0010\u000b\u001a\u0004\u0018\u00010\fH@ø\u0001\u0000¢\u0006\u0002\u0010\rJ\u001b\u0010\u000e\u001a\u0004\u0018\u00010\b2\u0006\u0010\u000f\u001a\u00020\fH@ø\u0001\u0000¢\u0006\u0002\u0010\u0010J\u001b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u000f\u001a\u00020\fH@ø\u0001\u0000¢\u0006\u0002\u0010\u0010J\u001f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\f0\u00072\u0006\u0010\u0014\u001a\u00020\u0012H@ø\u0001\u0000¢\u0006\u0002\u0010\u0015J\u0017\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H@ø\u0001\u0000¢\u0006\u0002\u0010\rJ\u0014\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\b0\u00062\u0006\u0010\u000f\u001a\u00020\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u0002\u0004\n\u0002\b\u0019¨\u0006\u0018"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/interactors/GetWalletInteractor;", "", "walletRepository", "Lcom/bitcoin/mwallet/core/repositories/WalletRepository;", "(Lcom/bitcoin/mwallet/core/repositories/WalletRepository;)V", "wallets", "Landroidx/lifecycle/LiveData;", "", "Lcom/bitcoin/mwallet/core/models/wallet/Wallet;", "getWallets", "()Landroidx/lifecycle/LiveData;", "getDefaultWalletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getWallet", "walletId", "(Lcom/bitcoin/mwallet/core/models/wallet/WalletId;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getWalletCoin", "Lcom/bitcoin/mwallet/core/models/Coin;", "getWalletIdsByCoin", "coin", "(Lcom/bitcoin/mwallet/core/models/Coin;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getWalletsNonStream", "wallet", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: GetWalletInteractor.kt */
public final class GetWalletInteractor {
    private final WalletRepository walletRepository;
    @NotNull
    private final LiveData<List<C1261Wallet>> wallets = this.walletRepository.getWallets();

    public GetWalletInteractor(@NotNull WalletRepository walletRepository2) {
        Intrinsics.checkParameterIsNotNull(walletRepository2, "walletRepository");
        this.walletRepository = walletRepository2;
    }

    @NotNull
    public final LiveData<List<C1261Wallet>> getWallets() {
        return this.wallets;
    }

    @NotNull
    public final LiveData<C1261Wallet> wallet(@NotNull WalletId walletId) {
        Intrinsics.checkParameterIsNotNull(walletId, "walletId");
        return this.walletRepository.walletById(walletId);
    }

    @Nullable
    public final Object getWalletsNonStream(@NotNull Continuation<? super List<C1261Wallet>> continuation) {
        return this.walletRepository.getWallets(continuation);
    }

    @Nullable
    public final Object getWallet(@NotNull WalletId walletId, @NotNull Continuation<? super C1261Wallet> continuation) {
        return this.walletRepository.getWalletById(walletId, continuation);
    }

    @Nullable
    public final Object getWalletCoin(@NotNull WalletId walletId, @NotNull Continuation<? super Coin> continuation) {
        return this.walletRepository.getWalletCoin(walletId, continuation);
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0058  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00a1  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00dc A[LOOP:2: B:39:0x00d6->B:41:0x00dc, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00f2  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x008c A[EDGE_INSN: B:52:0x008c->B:28:0x008c ?: BREAK  
    EDGE_INSN: B:52:0x008c->B:28:0x008c ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object getDefaultWalletId(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.bitcoin.mwallet.core.models.wallet.WalletId> r8) {
        /*
            r7 = this;
            boolean r0 = r8 instanceof com.bitcoin.mwallet.core.interactors.GetWalletInteractor$getDefaultWalletId$1
            if (r0 == 0) goto L_0x0014
            r0 = r8
            com.bitcoin.mwallet.core.interactors.GetWalletInteractor$getDefaultWalletId$1 r0 = (com.bitcoin.mwallet.core.interactors.GetWalletInteractor$getDefaultWalletId$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L_0x0019
        L_0x0014:
            com.bitcoin.mwallet.core.interactors.GetWalletInteractor$getDefaultWalletId$1 r0 = new com.bitcoin.mwallet.core.interactors.GetWalletInteractor$getDefaultWalletId$1
            r0.<init>(r7, r8)
        L_0x0019:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0036
            if (r2 != r3) goto L_0x002e
            java.lang.Object r0 = r0.L$0
            com.bitcoin.mwallet.core.interactors.GetWalletInteractor r0 = (com.bitcoin.mwallet.core.interactors.GetWalletInteractor) r0
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x0044
        L_0x002e:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L_0x0036:
            kotlin.ResultKt.throwOnFailure(r8)
            r0.L$0 = r7
            r0.label = r3
            java.lang.Object r8 = r7.getWalletsNonStream(r0)
            if (r8 != r1) goto L_0x0044
            return r1
        L_0x0044:
            java.lang.Iterable r8 = (java.lang.Iterable) r8
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.Collection r0 = (java.util.Collection) r0
            java.util.Iterator r8 = r8.iterator()
        L_0x0051:
            boolean r1 = r8.hasNext()
            r2 = 0
            if (r1 == 0) goto L_0x008c
            java.lang.Object r1 = r8.next()
            r4 = r1
            com.bitcoin.mwallet.core.models.wallet.Wallet r4 = (com.bitcoin.mwallet.core.models.wallet.C1261Wallet) r4
            boolean r5 = r4.isMultiSig()
            if (r5 != 0) goto L_0x007e
            com.bitcoin.mwallet.core.models.credential.CredentialId r5 = r4.getCredentialId()
            com.bitcoin.mwallet.core.models.credential.CredentialType r5 = r5.getType()
            com.bitcoin.mwallet.core.models.credential.CredentialType r6 = com.bitcoin.mwallet.core.models.credential.CredentialType.ENCRYPTED_MNEMONIC
            if (r5 == r6) goto L_0x007e
            com.bitcoin.mwallet.core.models.credential.CredentialId r4 = r4.getCredentialId()
            com.bitcoin.mwallet.core.models.credential.CredentialType r4 = r4.getType()
            com.bitcoin.mwallet.core.models.credential.CredentialType r5 = com.bitcoin.mwallet.core.models.credential.CredentialType.MNEMONIC_AND_PROTECTED
            if (r4 == r5) goto L_0x007e
            r2 = 1
        L_0x007e:
            java.lang.Boolean r2 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r2)
            boolean r2 = r2.booleanValue()
            if (r2 == 0) goto L_0x0051
            r0.add(r1)
            goto L_0x0051
        L_0x008c:
            java.util.List r0 = (java.util.List) r0
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            java.util.ArrayList r8 = new java.util.ArrayList
            r8.<init>()
            java.util.Collection r8 = (java.util.Collection) r8
            java.util.Iterator r1 = r0.iterator()
        L_0x009b:
            boolean r4 = r1.hasNext()
            if (r4 == 0) goto L_0x00c1
            java.lang.Object r4 = r1.next()
            r5 = r4
            com.bitcoin.mwallet.core.models.wallet.Wallet r5 = (com.bitcoin.mwallet.core.models.wallet.C1261Wallet) r5
            com.bitcoin.mwallet.core.models.Coin r5 = r5.getCoin()
            com.bitcoin.mwallet.core.models.Coin r6 = com.bitcoin.mwallet.core.models.Coin.BCH
            if (r5 != r6) goto L_0x00b2
            r5 = 1
            goto L_0x00b3
        L_0x00b2:
            r5 = 0
        L_0x00b3:
            java.lang.Boolean r5 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r5)
            boolean r5 = r5.booleanValue()
            if (r5 == 0) goto L_0x009b
            r8.add(r4)
            goto L_0x009b
        L_0x00c1:
            java.util.List r8 = (java.util.List) r8
            java.lang.Iterable r8 = (java.lang.Iterable) r8
            java.util.ArrayList r1 = new java.util.ArrayList
            r3 = 10
            int r4 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r8, r3)
            r1.<init>(r4)
            java.util.Collection r1 = (java.util.Collection) r1
            java.util.Iterator r8 = r8.iterator()
        L_0x00d6:
            boolean r4 = r8.hasNext()
            if (r4 == 0) goto L_0x00ea
            java.lang.Object r4 = r8.next()
            com.bitcoin.mwallet.core.models.wallet.Wallet r4 = (com.bitcoin.mwallet.core.models.wallet.C1261Wallet) r4
            com.bitcoin.mwallet.core.models.wallet.WalletId r4 = r4.getId()
            r1.add(r4)
            goto L_0x00d6
        L_0x00ea:
            java.util.List r1 = (java.util.List) r1
            boolean r8 = r1.isEmpty()
            if (r8 == 0) goto L_0x0118
            java.util.ArrayList r8 = new java.util.ArrayList
            int r1 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r0, r3)
            r8.<init>(r1)
            java.util.Collection r8 = (java.util.Collection) r8
            java.util.Iterator r0 = r0.iterator()
        L_0x0101:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0115
            java.lang.Object r1 = r0.next()
            com.bitcoin.mwallet.core.models.wallet.Wallet r1 = (com.bitcoin.mwallet.core.models.wallet.C1261Wallet) r1
            com.bitcoin.mwallet.core.models.wallet.WalletId r1 = r1.getId()
            r8.add(r1)
            goto L_0x0101
        L_0x0115:
            r1 = r8
            java.util.List r1 = (java.util.List) r1
        L_0x0118:
            java.lang.Object r8 = kotlin.collections.CollectionsKt.getOrNull(r1, r2)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.core.interactors.GetWalletInteractor.getDefaultWalletId(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x003a  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x005e  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00a7  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00e0 A[LOOP:2: B:39:0x00da->B:41:0x00e0, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0092 A[EDGE_INSN: B:45:0x0092->B:28:0x0092 ?: BREAK  
    EDGE_INSN: B:45:0x0092->B:28:0x0092 ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object getWalletIdsByCoin(@org.jetbrains.annotations.NotNull com.bitcoin.mwallet.core.models.Coin r8, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.util.List<com.bitcoin.mwallet.core.models.wallet.WalletId>> r9) {
        /*
            r7 = this;
            boolean r0 = r9 instanceof com.bitcoin.mwallet.core.interactors.GetWalletInteractor$getWalletIdsByCoin$1
            if (r0 == 0) goto L_0x0014
            r0 = r9
            com.bitcoin.mwallet.core.interactors.GetWalletInteractor$getWalletIdsByCoin$1 r0 = (com.bitcoin.mwallet.core.interactors.GetWalletInteractor$getWalletIdsByCoin$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L_0x0019
        L_0x0014:
            com.bitcoin.mwallet.core.interactors.GetWalletInteractor$getWalletIdsByCoin$1 r0 = new com.bitcoin.mwallet.core.interactors.GetWalletInteractor$getWalletIdsByCoin$1
            r0.<init>(r7, r9)
        L_0x0019:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x003a
            if (r2 != r3) goto L_0x0032
            java.lang.Object r8 = r0.L$1
            com.bitcoin.mwallet.core.models.Coin r8 = (com.bitcoin.mwallet.core.models.Coin) r8
            java.lang.Object r0 = r0.L$0
            com.bitcoin.mwallet.core.interactors.GetWalletInteractor r0 = (com.bitcoin.mwallet.core.interactors.GetWalletInteractor) r0
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x004a
        L_0x0032:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L_0x003a:
            kotlin.ResultKt.throwOnFailure(r9)
            r0.L$0 = r7
            r0.L$1 = r8
            r0.label = r3
            java.lang.Object r9 = r7.getWalletsNonStream(r0)
            if (r9 != r1) goto L_0x004a
            return r1
        L_0x004a:
            java.lang.Iterable r9 = (java.lang.Iterable) r9
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.Collection r0 = (java.util.Collection) r0
            java.util.Iterator r9 = r9.iterator()
        L_0x0057:
            boolean r1 = r9.hasNext()
            r2 = 0
            if (r1 == 0) goto L_0x0092
            java.lang.Object r1 = r9.next()
            r4 = r1
            com.bitcoin.mwallet.core.models.wallet.Wallet r4 = (com.bitcoin.mwallet.core.models.wallet.C1261Wallet) r4
            boolean r5 = r4.isMultiSig()
            if (r5 != 0) goto L_0x0084
            com.bitcoin.mwallet.core.models.credential.CredentialId r5 = r4.getCredentialId()
            com.bitcoin.mwallet.core.models.credential.CredentialType r5 = r5.getType()
            com.bitcoin.mwallet.core.models.credential.CredentialType r6 = com.bitcoin.mwallet.core.models.credential.CredentialType.ENCRYPTED_MNEMONIC
            if (r5 == r6) goto L_0x0084
            com.bitcoin.mwallet.core.models.credential.CredentialId r4 = r4.getCredentialId()
            com.bitcoin.mwallet.core.models.credential.CredentialType r4 = r4.getType()
            com.bitcoin.mwallet.core.models.credential.CredentialType r5 = com.bitcoin.mwallet.core.models.credential.CredentialType.MNEMONIC_AND_PROTECTED
            if (r4 == r5) goto L_0x0084
            r2 = 1
        L_0x0084:
            java.lang.Boolean r2 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r2)
            boolean r2 = r2.booleanValue()
            if (r2 == 0) goto L_0x0057
            r0.add(r1)
            goto L_0x0057
        L_0x0092:
            java.util.List r0 = (java.util.List) r0
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            java.util.ArrayList r9 = new java.util.ArrayList
            r9.<init>()
            java.util.Collection r9 = (java.util.Collection) r9
            java.util.Iterator r0 = r0.iterator()
        L_0x00a1:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x00c5
            java.lang.Object r1 = r0.next()
            r4 = r1
            com.bitcoin.mwallet.core.models.wallet.Wallet r4 = (com.bitcoin.mwallet.core.models.wallet.C1261Wallet) r4
            com.bitcoin.mwallet.core.models.Coin r4 = r4.getCoin()
            if (r4 != r8) goto L_0x00b6
            r4 = 1
            goto L_0x00b7
        L_0x00b6:
            r4 = 0
        L_0x00b7:
            java.lang.Boolean r4 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r4)
            boolean r4 = r4.booleanValue()
            if (r4 == 0) goto L_0x00a1
            r9.add(r1)
            goto L_0x00a1
        L_0x00c5:
            java.util.List r9 = (java.util.List) r9
            java.lang.Iterable r9 = (java.lang.Iterable) r9
            java.util.ArrayList r8 = new java.util.ArrayList
            r0 = 10
            int r0 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r9, r0)
            r8.<init>(r0)
            java.util.Collection r8 = (java.util.Collection) r8
            java.util.Iterator r9 = r9.iterator()
        L_0x00da:
            boolean r0 = r9.hasNext()
            if (r0 == 0) goto L_0x00ee
            java.lang.Object r0 = r9.next()
            com.bitcoin.mwallet.core.models.wallet.Wallet r0 = (com.bitcoin.mwallet.core.models.wallet.C1261Wallet) r0
            com.bitcoin.mwallet.core.models.wallet.WalletId r0 = r0.getId()
            r8.add(r0)
            goto L_0x00da
        L_0x00ee:
            java.util.List r8 = (java.util.List) r8
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.core.interactors.GetWalletInteractor.getWalletIdsByCoin(com.bitcoin.mwallet.core.models.Coin, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
