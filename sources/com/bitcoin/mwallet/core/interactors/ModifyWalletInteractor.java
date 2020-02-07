package com.bitcoin.mwallet.core.interactors;

import android.graphics.Color;
import com.bitcoin.mwallet.core.models.wallet.C1261Wallet;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import com.bitcoin.mwallet.core.repositories.TransactionHistoryRepository;
import com.bitcoin.mwallet.core.repositories.UtxoRepository;
import com.bitcoin.mwallet.core.repositories.WalletRepository;
import com.bitcoin.mwallet.core.services.eventstream.EventStreamHandler;
import com.bitcoin.mwallet.zion.ZionRepository;
import com.leanplum.internal.Constants.Kinds;
import java.math.BigDecimal;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eJ\u0016\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014J\u0019\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0011\u001a\u00020\u0012H@ø\u0001\u0000¢\u0006\u0002\u0010\u0017J\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u0011\u001a\u00020\u0012J!\u0010\u001a\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u001b\u001a\u00020\u0014H@ø\u0001\u0000¢\u0006\u0002\u0010\u001cJ\u0016\u0010\u001d\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u001e\u001a\u00020\u0016J\u0016\u0010\u001f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010 \u001a\u00020\u0016J\u0016\u0010!\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\"\u001a\u00020\u0016J\u0016\u0010#\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010$\u001a\u00020\u0016J!\u0010%\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010&\u001a\u00020\u0019H@ø\u0001\u0000¢\u0006\u0002\u0010'R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006("}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/interactors/ModifyWalletInteractor;", "", "walletRepository", "Lcom/bitcoin/mwallet/core/repositories/WalletRepository;", "utxoRepository", "Lcom/bitcoin/mwallet/core/repositories/UtxoRepository;", "txHistoryRepository", "Lcom/bitcoin/mwallet/core/repositories/TransactionHistoryRepository;", "eventStreamHandler", "Lcom/bitcoin/mwallet/core/services/eventstream/EventStreamHandler;", "zionRepository", "Lcom/bitcoin/mwallet/zion/ZionRepository;", "walletInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetWalletInteractor;", "(Lcom/bitcoin/mwallet/core/repositories/WalletRepository;Lcom/bitcoin/mwallet/core/repositories/UtxoRepository;Lcom/bitcoin/mwallet/core/repositories/TransactionHistoryRepository;Lcom/bitcoin/mwallet/core/services/eventstream/EventStreamHandler;Lcom/bitcoin/mwallet/zion/ZionRepository;Lcom/bitcoin/mwallet/core/interactors/GetWalletInteractor;)V", "changeWalletColor", "", "walletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "color", "", "deleteWallet", "", "(Lcom/bitcoin/mwallet/core/models/wallet/WalletId;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getSpendingThreshold", "Ljava/math/BigDecimal;", "renameWallet", "newWalletName", "(Lcom/bitcoin/mwallet/core/models/wallet/WalletId;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setHideBalances", "hideBalances", "setRequiresSpendingAuth", "requiresSpendingAuth", "setShowSLPTransactions", "showSLPTransactions", "setShowUnverifiedTokens", "showUnverifiedTokens", "setSpendingThreshold", "thresholdAmount", "(Lcom/bitcoin/mwallet/core/models/wallet/WalletId;Ljava/math/BigDecimal;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: ModifyWalletInteractor.kt */
public final class ModifyWalletInteractor {
    private final EventStreamHandler eventStreamHandler;
    private final TransactionHistoryRepository txHistoryRepository;
    private final UtxoRepository utxoRepository;
    /* access modifiers changed from: private */
    public final GetWalletInteractor walletInteractor;
    private final WalletRepository walletRepository;
    private final ZionRepository zionRepository;

    public ModifyWalletInteractor(@NotNull WalletRepository walletRepository2, @NotNull UtxoRepository utxoRepository2, @NotNull TransactionHistoryRepository transactionHistoryRepository, @NotNull EventStreamHandler eventStreamHandler2, @NotNull ZionRepository zionRepository2, @NotNull GetWalletInteractor getWalletInteractor) {
        Intrinsics.checkParameterIsNotNull(walletRepository2, "walletRepository");
        Intrinsics.checkParameterIsNotNull(utxoRepository2, "utxoRepository");
        Intrinsics.checkParameterIsNotNull(transactionHistoryRepository, "txHistoryRepository");
        Intrinsics.checkParameterIsNotNull(eventStreamHandler2, "eventStreamHandler");
        Intrinsics.checkParameterIsNotNull(zionRepository2, "zionRepository");
        Intrinsics.checkParameterIsNotNull(getWalletInteractor, "walletInteractor");
        this.walletRepository = walletRepository2;
        this.utxoRepository = utxoRepository2;
        this.txHistoryRepository = transactionHistoryRepository;
        this.eventStreamHandler = eventStreamHandler2;
        this.zionRepository = zionRepository2;
        this.walletInteractor = getWalletInteractor;
    }

    @Nullable
    public final Object renameWallet(@NotNull WalletId walletId, @NotNull String str, @NotNull Continuation<? super Unit> continuation) {
        return this.walletRepository.renameWallet(walletId, str, continuation);
    }

    public final void changeWalletColor(@NotNull WalletId walletId, @NotNull String str) {
        Intrinsics.checkParameterIsNotNull(walletId, "walletId");
        Intrinsics.checkParameterIsNotNull(str, Kinds.COLOR);
        try {
            C1261Wallet wallet = (C1261Wallet) BuildersKt__BuildersKt.runBlocking$default(null, new ModifyWalletInteractor$changeWalletColor$wallet$1(this, walletId, null), 1, null);
            if (wallet != null) {
                Color.parseColor(str);
                wallet.preference().setColor(str);
            }
        } catch (IllegalArgumentException unused) {
        }
    }

    public final void setHideBalances(@NotNull WalletId walletId, boolean z) {
        Intrinsics.checkParameterIsNotNull(walletId, "walletId");
        C1261Wallet wallet = (C1261Wallet) BuildersKt__BuildersKt.runBlocking$default(null, new ModifyWalletInteractor$setHideBalances$wallet$1(this, walletId, null), 1, null);
        if (wallet != null) {
            wallet.preference().setHideBalance(z);
        }
    }

    public final void setRequiresSpendingAuth(@NotNull WalletId walletId, boolean z) {
        Intrinsics.checkParameterIsNotNull(walletId, "walletId");
        C1261Wallet wallet = (C1261Wallet) BuildersKt__BuildersKt.runBlocking$default(null, new ModifyWalletInteractor$setRequiresSpendingAuth$wallet$1(this, walletId, null), 1, null);
        if (wallet != null) {
            wallet.preference().setHasSpendingAuth(z);
        }
    }

    public final void setShowUnverifiedTokens(@NotNull WalletId walletId, boolean z) {
        Intrinsics.checkParameterIsNotNull(walletId, "walletId");
        C1261Wallet wallet = (C1261Wallet) BuildersKt__BuildersKt.runBlocking$default(null, new ModifyWalletInteractor$setShowUnverifiedTokens$wallet$1(this, walletId, null), 1, null);
        if (wallet != null) {
            wallet.preference().setShowAllTokens(z);
        }
    }

    public final void setShowSLPTransactions(@NotNull WalletId walletId, boolean z) {
        Intrinsics.checkParameterIsNotNull(walletId, "walletId");
        C1261Wallet wallet = (C1261Wallet) BuildersKt__BuildersKt.runBlocking$default(null, new ModifyWalletInteractor$setShowSLPTransactions$wallet$1(this, walletId, null), 1, null);
        if (wallet != null) {
            wallet.preference().setShowSlpTransaction(z);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x003f  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0057  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object setSpendingThreshold(@org.jetbrains.annotations.NotNull com.bitcoin.mwallet.core.models.wallet.WalletId r5, @org.jetbrains.annotations.NotNull java.math.BigDecimal r6, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r7) {
        /*
            r4 = this;
            boolean r0 = r7 instanceof com.bitcoin.mwallet.core.interactors.ModifyWalletInteractor$setSpendingThreshold$1
            if (r0 == 0) goto L_0x0014
            r0 = r7
            com.bitcoin.mwallet.core.interactors.ModifyWalletInteractor$setSpendingThreshold$1 r0 = (com.bitcoin.mwallet.core.interactors.ModifyWalletInteractor$setSpendingThreshold$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L_0x0019
        L_0x0014:
            com.bitcoin.mwallet.core.interactors.ModifyWalletInteractor$setSpendingThreshold$1 r0 = new com.bitcoin.mwallet.core.interactors.ModifyWalletInteractor$setSpendingThreshold$1
            r0.<init>(r4, r7)
        L_0x0019:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x003f
            if (r2 != r3) goto L_0x0037
            java.lang.Object r5 = r0.L$2
            r6 = r5
            java.math.BigDecimal r6 = (java.math.BigDecimal) r6
            java.lang.Object r5 = r0.L$1
            com.bitcoin.mwallet.core.models.wallet.WalletId r5 = (com.bitcoin.mwallet.core.models.wallet.WalletId) r5
            java.lang.Object r5 = r0.L$0
            com.bitcoin.mwallet.core.interactors.ModifyWalletInteractor r5 = (com.bitcoin.mwallet.core.interactors.ModifyWalletInteractor) r5
            kotlin.ResultKt.throwOnFailure(r7)
            goto L_0x0053
        L_0x0037:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x003f:
            kotlin.ResultKt.throwOnFailure(r7)
            com.bitcoin.mwallet.core.interactors.GetWalletInteractor r7 = r4.walletInteractor
            r0.L$0 = r4
            r0.L$1 = r5
            r0.L$2 = r6
            r0.label = r3
            java.lang.Object r7 = r7.getWallet(r5, r0)
            if (r7 != r1) goto L_0x0053
            return r1
        L_0x0053:
            com.bitcoin.mwallet.core.models.wallet.Wallet r7 = (com.bitcoin.mwallet.core.models.wallet.C1261Wallet) r7
            if (r7 == 0) goto L_0x005e
            com.bitcoin.mwallet.core.preferences.WalletPreference r5 = r7.preference()
            r5.setThresholdAmount(r6)
        L_0x005e:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.core.interactors.ModifyWalletInteractor.setSpendingThreshold(com.bitcoin.mwallet.core.models.wallet.WalletId, java.math.BigDecimal, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Nullable
    public final BigDecimal getSpendingThreshold(@NotNull WalletId walletId) {
        Intrinsics.checkParameterIsNotNull(walletId, "walletId");
        C1261Wallet wallet = (C1261Wallet) BuildersKt__BuildersKt.runBlocking$default(null, new ModifyWalletInteractor$getSpendingThreshold$wallet$1(this, walletId, null), 1, null);
        if (wallet != null) {
            return wallet.preference().getThresholdAmount();
        }
        return BigDecimal.ZERO;
    }

    /* JADX INFO: used method not loaded: kotlin.collections.CollectionsKt___CollectionsKt.first(java.util.List):null, types can be incorrect */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0041, code lost:
        r12 = r11;
        r11 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x007e, code lost:
        r6 = r5;
        r5 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x00e5, code lost:
        r2 = r11;
        r11 = (com.bitcoin.mwallet.core.models.credential.Credential) r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00eb, code lost:
        if ((r11 instanceof com.bitcoin.mwallet.core.models.credential.CredentialZion) == false) goto L_0x016b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x00ed, code lost:
        r12 = r5.walletRepository;
        r6 = ((com.bitcoin.mwallet.core.models.credential.CredentialZion) r11).getZionId();
        r0.L$0 = r5;
        r0.L$1 = r2;
        r0.L$2 = r11;
        r0.label = 2;
        r12 = r12.getWalletsIds(r6, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0103, code lost:
        if (r12 != r1) goto L_0x0106;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0105, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0106, code lost:
        r12 = (java.util.Set) r12;
        r6 = new java.lang.StringBuilder();
        r6.append("walletIdsWithZionID: ");
        r6.append(r12);
        timber.log.Timber.m426d(r6.toString(), new java.lang.Object[0]);
        r6 = r5.zionRepository;
        r7 = ((com.bitcoin.mwallet.core.models.credential.CredentialZion) r11).getZionId();
        r0.L$0 = r5;
        r0.L$1 = r2;
        r0.L$2 = r11;
        r0.L$3 = r12;
        r0.label = 3;
        r6 = r6.clearWalletSeed(r7, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0136, code lost:
        if (r6 != r1) goto L_0x0139;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0138, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0139, code lost:
        r9 = r6;
        r6 = r12;
        r12 = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x013c, code lost:
        r12 = (com.bitcoin.mwallet.zion.ZionError) r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x013e, code lost:
        if (r12 == null) goto L_0x014e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0140, code lost:
        timber.log.Timber.m426d(r12.toString(), new java.lang.Object[0]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x014d, code lost:
        return kotlin.coroutines.jvm.internal.Boxing.boxBoolean(false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x014e, code lost:
        r7 = r5.zionRepository;
        r8 = ((com.bitcoin.mwallet.core.models.credential.CredentialZion) r11).getZionId();
        r0.L$0 = r5;
        r0.L$1 = r2;
        r0.L$2 = r11;
        r0.L$3 = r6;
        r0.L$4 = r12;
        r0.label = 4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0168, code lost:
        if (r7.freeWalletNameByZionId(r8, r0) != r1) goto L_0x016b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x016a, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x016b, code lost:
        r12 = r5.walletRepository;
        r0.L$0 = r5;
        r0.L$1 = r2;
        r0.L$2 = r11;
        r0.label = 5;
        r12 = r12.getWalletsById(r2, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x017a, code lost:
        if (r12 != r1) goto L_0x007e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x017c, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x017d, code lost:
        r12 = (java.util.List) r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x017f, code lost:
        if (r12 == null) goto L_0x01b1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0185, code lost:
        if (r12.size() <= 1) goto L_0x01b1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x0187, code lost:
        if (r11 == null) goto L_0x01b1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x0189, code lost:
        ((com.bitcoin.mwallet.core.models.wallet.C1261Wallet) kotlin.collections.CollectionsKt.first(r12)).preference().clearPreferences();
        r2 = r6.walletRepository;
        r7 = r11.getId();
        r0.L$0 = r6;
        r0.L$1 = r5;
        r0.L$2 = r11;
        r0.L$3 = r12;
        r0.label = 6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x01ab, code lost:
        if (r2.deleteWallet(r5, r7, r0) != r1) goto L_0x01ae;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x01ad, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x01ae, code lost:
        r11 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x01af, code lost:
        r0 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x01b1, code lost:
        r2 = r6.walletRepository;
        r0.L$0 = r6;
        r0.L$1 = r5;
        r0.L$2 = r11;
        r0.L$3 = r12;
        r0.label = 7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x01c2, code lost:
        if (r2.deleteWallet(r5, r0) != r1) goto L_0x01c5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x01c4, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x01c5, code lost:
        r2 = r11;
        r11 = r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x01c7, code lost:
        r12 = r6.utxoRepository;
        r0.L$0 = r6;
        r0.L$1 = r5;
        r0.L$2 = r2;
        r0.L$3 = r11;
        r0.label = 8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x01d9, code lost:
        if (r12.deleteByWalletId(r5, r0) != r1) goto L_0x0041;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x01db, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x01dc, code lost:
        r5 = r6.txHistoryRepository;
        r0.L$0 = r6;
        r0.L$1 = r11;
        r0.L$2 = r2;
        r0.L$3 = r12;
        r0.label = 9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x01ee, code lost:
        if (r5.deleteByWalletId(r11, r0) != r1) goto L_0x01af;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x01f0, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x01f1, code lost:
        r0.eventStreamHandler.restartStream();
        r12 = new java.lang.StringBuilder();
        r12.append("Deleted walletId=");
        r12.append(r11);
        timber.log.Timber.m426d(r12.toString(), new java.lang.Object[0]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x0210, code lost:
        return kotlin.coroutines.jvm.internal.Boxing.boxBoolean(true);
     */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x002e  */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0045  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x005a  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x006f  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0082  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x009b  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x00b5  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x00c5  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x00d2  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object deleteWallet(@org.jetbrains.annotations.NotNull com.bitcoin.mwallet.core.models.wallet.WalletId r11, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Boolean> r12) {
        /*
            r10 = this;
            boolean r0 = r12 instanceof com.bitcoin.mwallet.core.interactors.ModifyWalletInteractor$deleteWallet$1
            if (r0 == 0) goto L_0x0014
            r0 = r12
            com.bitcoin.mwallet.core.interactors.ModifyWalletInteractor$deleteWallet$1 r0 = (com.bitcoin.mwallet.core.interactors.ModifyWalletInteractor$deleteWallet$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r12 = r0.label
            int r12 = r12 - r2
            r0.label = r12
            goto L_0x0019
        L_0x0014:
            com.bitcoin.mwallet.core.interactors.ModifyWalletInteractor$deleteWallet$1 r0 = new com.bitcoin.mwallet.core.interactors.ModifyWalletInteractor$deleteWallet$1
            r0.<init>(r10, r12)
        L_0x0019:
            java.lang.Object r12 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            r4 = 0
            switch(r2) {
                case 0: goto L_0x00d2;
                case 1: goto L_0x00c5;
                case 2: goto L_0x00b5;
                case 3: goto L_0x009b;
                case 4: goto L_0x0082;
                case 5: goto L_0x006f;
                case 6: goto L_0x005a;
                case 7: goto L_0x0045;
                case 8: goto L_0x002e;
                case 9: goto L_0x005a;
                default: goto L_0x0026;
            }
        L_0x0026:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L_0x002e:
            java.lang.Object r11 = r0.L$3
            java.util.List r11 = (java.util.List) r11
            java.lang.Object r2 = r0.L$2
            com.bitcoin.mwallet.core.models.credential.Credential r2 = (com.bitcoin.mwallet.core.models.credential.Credential) r2
            java.lang.Object r5 = r0.L$1
            com.bitcoin.mwallet.core.models.wallet.WalletId r5 = (com.bitcoin.mwallet.core.models.wallet.WalletId) r5
            java.lang.Object r6 = r0.L$0
            com.bitcoin.mwallet.core.interactors.ModifyWalletInteractor r6 = (com.bitcoin.mwallet.core.interactors.ModifyWalletInteractor) r6
            kotlin.ResultKt.throwOnFailure(r12)
        L_0x0041:
            r12 = r11
            r11 = r5
            goto L_0x01dc
        L_0x0045:
            java.lang.Object r11 = r0.L$3
            java.util.List r11 = (java.util.List) r11
            java.lang.Object r2 = r0.L$2
            com.bitcoin.mwallet.core.models.credential.Credential r2 = (com.bitcoin.mwallet.core.models.credential.Credential) r2
            java.lang.Object r5 = r0.L$1
            com.bitcoin.mwallet.core.models.wallet.WalletId r5 = (com.bitcoin.mwallet.core.models.wallet.WalletId) r5
            java.lang.Object r6 = r0.L$0
            com.bitcoin.mwallet.core.interactors.ModifyWalletInteractor r6 = (com.bitcoin.mwallet.core.interactors.ModifyWalletInteractor) r6
            kotlin.ResultKt.throwOnFailure(r12)
            goto L_0x01c7
        L_0x005a:
            java.lang.Object r11 = r0.L$3
            java.util.List r11 = (java.util.List) r11
            java.lang.Object r11 = r0.L$2
            com.bitcoin.mwallet.core.models.credential.Credential r11 = (com.bitcoin.mwallet.core.models.credential.Credential) r11
            java.lang.Object r11 = r0.L$1
            com.bitcoin.mwallet.core.models.wallet.WalletId r11 = (com.bitcoin.mwallet.core.models.wallet.WalletId) r11
            java.lang.Object r0 = r0.L$0
            com.bitcoin.mwallet.core.interactors.ModifyWalletInteractor r0 = (com.bitcoin.mwallet.core.interactors.ModifyWalletInteractor) r0
            kotlin.ResultKt.throwOnFailure(r12)
            goto L_0x01f1
        L_0x006f:
            java.lang.Object r11 = r0.L$2
            com.bitcoin.mwallet.core.models.credential.Credential r11 = (com.bitcoin.mwallet.core.models.credential.Credential) r11
            java.lang.Object r2 = r0.L$1
            com.bitcoin.mwallet.core.models.wallet.WalletId r2 = (com.bitcoin.mwallet.core.models.wallet.WalletId) r2
            java.lang.Object r5 = r0.L$0
            com.bitcoin.mwallet.core.interactors.ModifyWalletInteractor r5 = (com.bitcoin.mwallet.core.interactors.ModifyWalletInteractor) r5
            kotlin.ResultKt.throwOnFailure(r12)
        L_0x007e:
            r6 = r5
            r5 = r2
            goto L_0x017d
        L_0x0082:
            java.lang.Object r11 = r0.L$4
            com.bitcoin.mwallet.zion.ZionError r11 = (com.bitcoin.mwallet.zion.ZionError) r11
            java.lang.Object r11 = r0.L$3
            java.util.Set r11 = (java.util.Set) r11
            java.lang.Object r11 = r0.L$2
            com.bitcoin.mwallet.core.models.credential.Credential r11 = (com.bitcoin.mwallet.core.models.credential.Credential) r11
            java.lang.Object r2 = r0.L$1
            com.bitcoin.mwallet.core.models.wallet.WalletId r2 = (com.bitcoin.mwallet.core.models.wallet.WalletId) r2
            java.lang.Object r5 = r0.L$0
            com.bitcoin.mwallet.core.interactors.ModifyWalletInteractor r5 = (com.bitcoin.mwallet.core.interactors.ModifyWalletInteractor) r5
            kotlin.ResultKt.throwOnFailure(r12)
            goto L_0x016b
        L_0x009b:
            java.lang.Object r11 = r0.L$3
            java.util.Set r11 = (java.util.Set) r11
            java.lang.Object r2 = r0.L$2
            com.bitcoin.mwallet.core.models.credential.Credential r2 = (com.bitcoin.mwallet.core.models.credential.Credential) r2
            java.lang.Object r5 = r0.L$1
            com.bitcoin.mwallet.core.models.wallet.WalletId r5 = (com.bitcoin.mwallet.core.models.wallet.WalletId) r5
            java.lang.Object r6 = r0.L$0
            com.bitcoin.mwallet.core.interactors.ModifyWalletInteractor r6 = (com.bitcoin.mwallet.core.interactors.ModifyWalletInteractor) r6
            kotlin.ResultKt.throwOnFailure(r12)
            r9 = r6
            r6 = r11
            r11 = r2
            r2 = r5
            r5 = r9
            goto L_0x013c
        L_0x00b5:
            java.lang.Object r11 = r0.L$2
            com.bitcoin.mwallet.core.models.credential.Credential r11 = (com.bitcoin.mwallet.core.models.credential.Credential) r11
            java.lang.Object r2 = r0.L$1
            com.bitcoin.mwallet.core.models.wallet.WalletId r2 = (com.bitcoin.mwallet.core.models.wallet.WalletId) r2
            java.lang.Object r5 = r0.L$0
            com.bitcoin.mwallet.core.interactors.ModifyWalletInteractor r5 = (com.bitcoin.mwallet.core.interactors.ModifyWalletInteractor) r5
            kotlin.ResultKt.throwOnFailure(r12)
            goto L_0x0106
        L_0x00c5:
            java.lang.Object r11 = r0.L$1
            com.bitcoin.mwallet.core.models.wallet.WalletId r11 = (com.bitcoin.mwallet.core.models.wallet.WalletId) r11
            java.lang.Object r2 = r0.L$0
            com.bitcoin.mwallet.core.interactors.ModifyWalletInteractor r2 = (com.bitcoin.mwallet.core.interactors.ModifyWalletInteractor) r2
            kotlin.ResultKt.throwOnFailure(r12)
            r5 = r2
            goto L_0x00e5
        L_0x00d2:
            kotlin.ResultKt.throwOnFailure(r12)
            com.bitcoin.mwallet.core.repositories.WalletRepository r12 = r10.walletRepository
            r0.L$0 = r10
            r0.L$1 = r11
            r0.label = r3
            java.lang.Object r12 = r12.getCredential(r11, r0)
            if (r12 != r1) goto L_0x00e4
            return r1
        L_0x00e4:
            r5 = r10
        L_0x00e5:
            r2 = r11
            r11 = r12
            com.bitcoin.mwallet.core.models.credential.Credential r11 = (com.bitcoin.mwallet.core.models.credential.Credential) r11
            boolean r12 = r11 instanceof com.bitcoin.mwallet.core.models.credential.CredentialZion
            if (r12 == 0) goto L_0x016b
            com.bitcoin.mwallet.core.repositories.WalletRepository r12 = r5.walletRepository
            r6 = r11
            com.bitcoin.mwallet.core.models.credential.CredentialZion r6 = (com.bitcoin.mwallet.core.models.credential.CredentialZion) r6
            com.bitcoin.mwallet.zion.ZionId r6 = r6.getZionId()
            r0.L$0 = r5
            r0.L$1 = r2
            r0.L$2 = r11
            r7 = 2
            r0.label = r7
            java.lang.Object r12 = r12.getWalletsIds(r6, r0)
            if (r12 != r1) goto L_0x0106
            return r1
        L_0x0106:
            java.util.Set r12 = (java.util.Set) r12
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "walletIdsWithZionID: "
            r6.append(r7)
            r6.append(r12)
            java.lang.String r6 = r6.toString()
            java.lang.Object[] r7 = new java.lang.Object[r4]
            timber.log.Timber.m426d(r6, r7)
            com.bitcoin.mwallet.zion.ZionRepository r6 = r5.zionRepository
            r7 = r11
            com.bitcoin.mwallet.core.models.credential.CredentialZion r7 = (com.bitcoin.mwallet.core.models.credential.CredentialZion) r7
            com.bitcoin.mwallet.zion.ZionId r7 = r7.getZionId()
            r0.L$0 = r5
            r0.L$1 = r2
            r0.L$2 = r11
            r0.L$3 = r12
            r8 = 3
            r0.label = r8
            java.lang.Object r6 = r6.clearWalletSeed(r7, r0)
            if (r6 != r1) goto L_0x0139
            return r1
        L_0x0139:
            r9 = r6
            r6 = r12
            r12 = r9
        L_0x013c:
            com.bitcoin.mwallet.zion.ZionError r12 = (com.bitcoin.mwallet.zion.ZionError) r12
            if (r12 == 0) goto L_0x014e
            java.lang.String r11 = r12.toString()
            java.lang.Object[] r12 = new java.lang.Object[r4]
            timber.log.Timber.m426d(r11, r12)
            java.lang.Boolean r11 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r4)
            return r11
        L_0x014e:
            com.bitcoin.mwallet.zion.ZionRepository r7 = r5.zionRepository
            r8 = r11
            com.bitcoin.mwallet.core.models.credential.CredentialZion r8 = (com.bitcoin.mwallet.core.models.credential.CredentialZion) r8
            com.bitcoin.mwallet.zion.ZionId r8 = r8.getZionId()
            r0.L$0 = r5
            r0.L$1 = r2
            r0.L$2 = r11
            r0.L$3 = r6
            r0.L$4 = r12
            r12 = 4
            r0.label = r12
            java.lang.Object r12 = r7.freeWalletNameByZionId(r8, r0)
            if (r12 != r1) goto L_0x016b
            return r1
        L_0x016b:
            com.bitcoin.mwallet.core.repositories.WalletRepository r12 = r5.walletRepository
            r0.L$0 = r5
            r0.L$1 = r2
            r0.L$2 = r11
            r6 = 5
            r0.label = r6
            java.lang.Object r12 = r12.getWalletsById(r2, r0)
            if (r12 != r1) goto L_0x007e
            return r1
        L_0x017d:
            java.util.List r12 = (java.util.List) r12
            if (r12 == 0) goto L_0x01b1
            int r2 = r12.size()
            if (r2 <= r3) goto L_0x01b1
            if (r11 == 0) goto L_0x01b1
            java.lang.Object r2 = kotlin.collections.CollectionsKt.first(r12)
            com.bitcoin.mwallet.core.models.wallet.Wallet r2 = (com.bitcoin.mwallet.core.models.wallet.C1261Wallet) r2
            com.bitcoin.mwallet.core.preferences.WalletPreference r2 = r2.preference()
            r2.clearPreferences()
            com.bitcoin.mwallet.core.repositories.WalletRepository r2 = r6.walletRepository
            com.bitcoin.mwallet.core.models.credential.CredentialId r7 = r11.getId()
            r0.L$0 = r6
            r0.L$1 = r5
            r0.L$2 = r11
            r0.L$3 = r12
            r11 = 6
            r0.label = r11
            java.lang.Object r11 = r2.deleteWallet(r5, r7, r0)
            if (r11 != r1) goto L_0x01ae
            return r1
        L_0x01ae:
            r11 = r5
        L_0x01af:
            r0 = r6
            goto L_0x01f1
        L_0x01b1:
            com.bitcoin.mwallet.core.repositories.WalletRepository r2 = r6.walletRepository
            r0.L$0 = r6
            r0.L$1 = r5
            r0.L$2 = r11
            r0.L$3 = r12
            r7 = 7
            r0.label = r7
            java.lang.Object r2 = r2.deleteWallet(r5, r0)
            if (r2 != r1) goto L_0x01c5
            return r1
        L_0x01c5:
            r2 = r11
            r11 = r12
        L_0x01c7:
            com.bitcoin.mwallet.core.repositories.UtxoRepository r12 = r6.utxoRepository
            r0.L$0 = r6
            r0.L$1 = r5
            r0.L$2 = r2
            r0.L$3 = r11
            r7 = 8
            r0.label = r7
            java.lang.Object r12 = r12.deleteByWalletId(r5, r0)
            if (r12 != r1) goto L_0x0041
            return r1
        L_0x01dc:
            com.bitcoin.mwallet.core.repositories.TransactionHistoryRepository r5 = r6.txHistoryRepository
            r0.L$0 = r6
            r0.L$1 = r11
            r0.L$2 = r2
            r0.L$3 = r12
            r12 = 9
            r0.label = r12
            java.lang.Object r12 = r5.deleteByWalletId(r11, r0)
            if (r12 != r1) goto L_0x01af
            return r1
        L_0x01f1:
            com.bitcoin.mwallet.core.services.eventstream.EventStreamHandler r12 = r0.eventStreamHandler
            r12.restartStream()
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            java.lang.String r0 = "Deleted walletId="
            r12.append(r0)
            r12.append(r11)
            java.lang.String r11 = r12.toString()
            java.lang.Object[] r12 = new java.lang.Object[r4]
            timber.log.Timber.m426d(r11, r12)
            java.lang.Boolean r11 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r3)
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.core.interactors.ModifyWalletInteractor.deleteWallet(com.bitcoin.mwallet.core.models.wallet.WalletId, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
