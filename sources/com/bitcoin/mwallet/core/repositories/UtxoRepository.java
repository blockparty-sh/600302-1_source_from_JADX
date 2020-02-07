package com.bitcoin.mwallet.core.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import com.bitcoin.bitcoink.p008tx.TxId;
import com.bitcoin.mwallet.core.dao.SlpUtxoDao;
import com.bitcoin.mwallet.core.dao.SpentUtxoDao;
import com.bitcoin.mwallet.core.dao.UtxoDao;
import com.bitcoin.mwallet.core.models.p009tx.slputxo.SlpUtxo;
import com.bitcoin.mwallet.core.models.p009tx.slputxo.WalletSlpUtxos;
import com.bitcoin.mwallet.core.models.p009tx.utxo.Utxo;
import com.bitcoin.mwallet.core.models.p009tx.utxo.WalletUtxos;
import com.bitcoin.mwallet.core.models.slp.SlpId;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import com.bitcoin.mwallet.core.services.p010tx.TxService;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0011\u0010\u000b\u001a\u00020\fH@ø\u0001\u0000¢\u0006\u0002\u0010\rJ\u0019\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u0010H@ø\u0001\u0000¢\u0006\u0002\u0010\u0011J\u0019\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u000f\u001a\u00020\u0010H@ø\u0001\u0000¢\u0006\u0002\u0010\u0011J\u0019\u0010\u0014\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\u0016H@ø\u0001\u0000¢\u0006\u0002\u0010\u0017J+\u0010\u0018\u001a\u00020\f2\u0018\u0010\u0019\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u001d0\u001b0\u001aH@ø\u0001\u0000¢\u0006\u0002\u0010\u001eJ\u001f\u0010\u001f\u001a\u00020\f2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\"0!H@ø\u0001\u0000¢\u0006\u0002\u0010#J\u001f\u0010$\u001a\u00020\f2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020%0!H@ø\u0001\u0000¢\u0006\u0002\u0010#J\u001c\u0010&\u001a\b\u0012\u0004\u0012\u00020(0'2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010)\u001a\u00020*J!\u0010+\u001a\u00020(2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010)\u001a\u00020*H@ø\u0001\u0000¢\u0006\u0002\u0010,J\u0014\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00130'2\u0006\u0010\u000f\u001a\u00020\u0010R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006."}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/repositories/UtxoRepository;", "", "txServiceGrpc", "Lcom/bitcoin/mwallet/core/services/tx/TxService;", "utxoDao", "Lcom/bitcoin/mwallet/core/dao/UtxoDao;", "slpUtxoDao", "Lcom/bitcoin/mwallet/core/dao/SlpUtxoDao;", "spentUtxoDao", "Lcom/bitcoin/mwallet/core/dao/SpentUtxoDao;", "(Lcom/bitcoin/mwallet/core/services/tx/TxService;Lcom/bitcoin/mwallet/core/dao/UtxoDao;Lcom/bitcoin/mwallet/core/dao/SlpUtxoDao;Lcom/bitcoin/mwallet/core/dao/SpentUtxoDao;)V", "clearSpentUtxos", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteByWalletId", "walletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "(Lcom/bitcoin/mwallet/core/models/wallet/WalletId;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getWalletUtxos", "Lcom/bitcoin/mwallet/core/models/tx/utxo/WalletUtxos;", "refreshUtxos", "wallet", "Lcom/bitcoin/mwallet/core/models/wallet/Wallet;", "(Lcom/bitcoin/mwallet/core/models/wallet/Wallet;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setSent", "spentUtxos", "", "Lkotlin/Pair;", "Lcom/bitcoin/bitcoink/tx/TxId;", "", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "slpUtxoUpsert", "utxos", "", "Lcom/bitcoin/mwallet/core/models/tx/slputxo/SlpUtxo;", "(Ljava/util/Collection;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "upsert", "Lcom/bitcoin/mwallet/core/models/tx/utxo/Utxo;", "walletTokenUtxos", "Landroidx/lifecycle/LiveData;", "Lcom/bitcoin/mwallet/core/models/tx/slputxo/WalletSlpUtxos;", "tokenId", "Lcom/bitcoin/mwallet/core/models/slp/SlpId;", "walletTokenUtxosNonLive", "(Lcom/bitcoin/mwallet/core/models/wallet/WalletId;Lcom/bitcoin/mwallet/core/models/slp/SlpId;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "walletUtxos", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: UtxoRepository.kt */
public final class UtxoRepository {
    /* access modifiers changed from: private */
    public final SlpUtxoDao slpUtxoDao;
    /* access modifiers changed from: private */
    public final SpentUtxoDao spentUtxoDao;
    private final TxService txServiceGrpc;
    /* access modifiers changed from: private */
    public final UtxoDao utxoDao;

    public UtxoRepository(@NotNull TxService txService, @NotNull UtxoDao utxoDao2, @NotNull SlpUtxoDao slpUtxoDao2, @NotNull SpentUtxoDao spentUtxoDao2) {
        Intrinsics.checkParameterIsNotNull(txService, "txServiceGrpc");
        Intrinsics.checkParameterIsNotNull(utxoDao2, "utxoDao");
        Intrinsics.checkParameterIsNotNull(slpUtxoDao2, "slpUtxoDao");
        Intrinsics.checkParameterIsNotNull(spentUtxoDao2, "spentUtxoDao");
        this.txServiceGrpc = txService;
        this.utxoDao = utxoDao2;
        this.slpUtxoDao = slpUtxoDao2;
        this.spentUtxoDao = spentUtxoDao2;
    }

    @NotNull
    public final LiveData<WalletUtxos> walletUtxos(@NotNull WalletId walletId) {
        Intrinsics.checkParameterIsNotNull(walletId, "walletId");
        LiveData<WalletUtxos> map = Transformations.map(this.utxoDao.utxos(walletId), new UtxoRepository$walletUtxos$1(walletId));
        Intrinsics.checkExpressionValueIsNotNull(map, "Transformations.map(live…alletId, utxos)\n        }");
        return map;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x003e  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object walletTokenUtxosNonLive(@org.jetbrains.annotations.NotNull com.bitcoin.mwallet.core.models.wallet.WalletId r5, @org.jetbrains.annotations.NotNull com.bitcoin.mwallet.core.models.slp.SlpId r6, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.bitcoin.mwallet.core.models.p009tx.slputxo.WalletSlpUtxos> r7) {
        /*
            r4 = this;
            boolean r0 = r7 instanceof com.bitcoin.mwallet.core.repositories.UtxoRepository$walletTokenUtxosNonLive$1
            if (r0 == 0) goto L_0x0014
            r0 = r7
            com.bitcoin.mwallet.core.repositories.UtxoRepository$walletTokenUtxosNonLive$1 r0 = (com.bitcoin.mwallet.core.repositories.UtxoRepository$walletTokenUtxosNonLive$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L_0x0019
        L_0x0014:
            com.bitcoin.mwallet.core.repositories.UtxoRepository$walletTokenUtxosNonLive$1 r0 = new com.bitcoin.mwallet.core.repositories.UtxoRepository$walletTokenUtxosNonLive$1
            r0.<init>(r4, r7)
        L_0x0019:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x003e
            if (r2 != r3) goto L_0x0036
            java.lang.Object r5 = r0.L$2
            com.bitcoin.mwallet.core.models.slp.SlpId r5 = (com.bitcoin.mwallet.core.models.slp.SlpId) r5
            java.lang.Object r5 = r0.L$1
            com.bitcoin.mwallet.core.models.wallet.WalletId r5 = (com.bitcoin.mwallet.core.models.wallet.WalletId) r5
            java.lang.Object r6 = r0.L$0
            com.bitcoin.mwallet.core.repositories.UtxoRepository r6 = (com.bitcoin.mwallet.core.repositories.UtxoRepository) r6
            kotlin.ResultKt.throwOnFailure(r7)
            goto L_0x0052
        L_0x0036:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x003e:
            kotlin.ResultKt.throwOnFailure(r7)
            com.bitcoin.mwallet.core.dao.SlpUtxoDao r7 = r4.slpUtxoDao
            r0.L$0 = r4
            r0.L$1 = r5
            r0.L$2 = r6
            r0.label = r3
            java.lang.Object r7 = r7.selectTokenUtxoNonLive(r5, r6, r0)
            if (r7 != r1) goto L_0x0052
            return r1
        L_0x0052:
            java.util.List r7 = (java.util.List) r7
            com.bitcoin.mwallet.core.models.tx.slputxo.WalletSlpUtxos r6 = new com.bitcoin.mwallet.core.models.tx.slputxo.WalletSlpUtxos
            r6.<init>(r5, r7)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.core.repositories.UtxoRepository.walletTokenUtxosNonLive(com.bitcoin.mwallet.core.models.wallet.WalletId, com.bitcoin.mwallet.core.models.slp.SlpId, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @NotNull
    public final LiveData<WalletSlpUtxos> walletTokenUtxos(@NotNull WalletId walletId, @NotNull SlpId slpId) {
        Intrinsics.checkParameterIsNotNull(walletId, "walletId");
        Intrinsics.checkParameterIsNotNull(slpId, "tokenId");
        LiveData<WalletSlpUtxos> map = Transformations.map(this.slpUtxoDao.selectTokenUtxo(walletId, slpId), new UtxoRepository$walletTokenUtxos$1(walletId));
        Intrinsics.checkExpressionValueIsNotNull(map, "Transformations.map(live…alletId, utxos)\n        }");
        return map;
    }

    @Nullable
    public final Object clearSpentUtxos(@NotNull Continuation<? super Unit> continuation) {
        return this.spentUtxoDao.deleteAll(continuation);
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x003a  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object getWalletUtxos(@org.jetbrains.annotations.NotNull com.bitcoin.mwallet.core.models.wallet.WalletId r5, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.bitcoin.mwallet.core.models.p009tx.utxo.WalletUtxos> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof com.bitcoin.mwallet.core.repositories.UtxoRepository$getWalletUtxos$1
            if (r0 == 0) goto L_0x0014
            r0 = r6
            com.bitcoin.mwallet.core.repositories.UtxoRepository$getWalletUtxos$1 r0 = (com.bitcoin.mwallet.core.repositories.UtxoRepository$getWalletUtxos$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L_0x0019
        L_0x0014:
            com.bitcoin.mwallet.core.repositories.UtxoRepository$getWalletUtxos$1 r0 = new com.bitcoin.mwallet.core.repositories.UtxoRepository$getWalletUtxos$1
            r0.<init>(r4, r6)
        L_0x0019:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x003a
            if (r2 != r3) goto L_0x0032
            java.lang.Object r5 = r0.L$1
            com.bitcoin.mwallet.core.models.wallet.WalletId r5 = (com.bitcoin.mwallet.core.models.wallet.WalletId) r5
            java.lang.Object r0 = r0.L$0
            com.bitcoin.mwallet.core.repositories.UtxoRepository r0 = (com.bitcoin.mwallet.core.repositories.UtxoRepository) r0
            kotlin.ResultKt.throwOnFailure(r6)
            goto L_0x004c
        L_0x0032:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x003a:
            kotlin.ResultKt.throwOnFailure(r6)
            com.bitcoin.mwallet.core.dao.UtxoDao r6 = r4.utxoDao
            r0.L$0 = r4
            r0.L$1 = r5
            r0.label = r3
            java.lang.Object r6 = r6.getUtxos(r5, r0)
            if (r6 != r1) goto L_0x004c
            return r1
        L_0x004c:
            java.util.List r6 = (java.util.List) r6
            com.bitcoin.mwallet.core.models.tx.utxo.WalletUtxos r0 = new com.bitcoin.mwallet.core.models.tx.utxo.WalletUtxos
            r0.<init>(r5, r6)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.core.repositories.UtxoRepository.getWalletUtxos(com.bitcoin.mwallet.core.models.wallet.WalletId, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:105:0x0267  */
    /* JADX WARNING: Removed duplicated region for block: B:109:0x0163 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:114:0x022d A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0095  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x00ba A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x00bb  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00c9  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00d9  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x0160  */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x01a3  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x01a8  */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x01ab  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0030  */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x022a  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object refreshUtxos(@org.jetbrains.annotations.NotNull com.bitcoin.mwallet.core.models.wallet.C1261Wallet r19, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r20) {
        /*
            r18 = this;
            r0 = r18
            r1 = r19
            r2 = r20
            boolean r3 = r2 instanceof com.bitcoin.mwallet.core.repositories.UtxoRepository$refreshUtxos$1
            if (r3 == 0) goto L_0x001a
            r3 = r2
            com.bitcoin.mwallet.core.repositories.UtxoRepository$refreshUtxos$1 r3 = (com.bitcoin.mwallet.core.repositories.UtxoRepository$refreshUtxos$1) r3
            int r4 = r3.label
            r5 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r4 & r5
            if (r4 == 0) goto L_0x001a
            int r2 = r3.label
            int r2 = r2 - r5
            r3.label = r2
            goto L_0x001f
        L_0x001a:
            com.bitcoin.mwallet.core.repositories.UtxoRepository$refreshUtxos$1 r3 = new com.bitcoin.mwallet.core.repositories.UtxoRepository$refreshUtxos$1
            r3.<init>(r0, r2)
        L_0x001f:
            java.lang.Object r2 = r3.result
            java.lang.Object r4 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r5 = r3.label
            java.lang.String r6 = "null cannot be cast to non-null type kotlin.Array<T>"
            r8 = 4
            r9 = 3
            r10 = 2
            r11 = 0
            r12 = 1
            if (r5 == 0) goto L_0x0095
            if (r5 == r12) goto L_0x0089
            if (r5 == r10) goto L_0x0074
            if (r5 == r9) goto L_0x0059
            if (r5 != r8) goto L_0x0051
            java.lang.Object r1 = r3.L$4
            java.util.List r1 = (java.util.List) r1
            java.lang.Object r1 = r3.L$3
            java.util.List r1 = (java.util.List) r1
            java.lang.Object r1 = r3.L$2
            com.bitcoin.mwallet.core.services.tx.GetUtxosResponse r1 = (com.bitcoin.mwallet.core.services.p010tx.GetUtxosResponse) r1
            java.lang.Object r1 = r3.L$1
            com.bitcoin.mwallet.core.models.wallet.Wallet r1 = (com.bitcoin.mwallet.core.models.wallet.C1261Wallet) r1
            java.lang.Object r1 = r3.L$0
            com.bitcoin.mwallet.core.repositories.UtxoRepository r1 = (com.bitcoin.mwallet.core.repositories.UtxoRepository) r1
            kotlin.ResultKt.throwOnFailure(r2)
            goto L_0x0260
        L_0x0051:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r1
        L_0x0059:
            java.lang.Object r1 = r3.L$4
            java.util.List r1 = (java.util.List) r1
            java.lang.Object r1 = r3.L$3
            java.util.List r1 = (java.util.List) r1
            java.lang.Object r5 = r3.L$2
            com.bitcoin.mwallet.core.services.tx.GetUtxosResponse r5 = (com.bitcoin.mwallet.core.services.p010tx.GetUtxosResponse) r5
            java.lang.Object r9 = r3.L$1
            com.bitcoin.mwallet.core.models.wallet.Wallet r9 = (com.bitcoin.mwallet.core.models.wallet.C1261Wallet) r9
            java.lang.Object r10 = r3.L$0
            com.bitcoin.mwallet.core.repositories.UtxoRepository r10 = (com.bitcoin.mwallet.core.repositories.UtxoRepository) r10
            kotlin.ResultKt.throwOnFailure(r2)
            r2 = r1
            r1 = r9
            goto L_0x019d
        L_0x0074:
            java.lang.Object r1 = r3.L$2
            com.bitcoin.mwallet.core.services.tx.GetUtxosResponse r1 = (com.bitcoin.mwallet.core.services.p010tx.GetUtxosResponse) r1
            java.lang.Object r5 = r3.L$1
            com.bitcoin.mwallet.core.models.wallet.Wallet r5 = (com.bitcoin.mwallet.core.models.wallet.C1261Wallet) r5
            java.lang.Object r10 = r3.L$0
            com.bitcoin.mwallet.core.repositories.UtxoRepository r10 = (com.bitcoin.mwallet.core.repositories.UtxoRepository) r10
            kotlin.ResultKt.throwOnFailure(r2)
            r17 = r5
            r5 = r1
            r1 = r17
            goto L_0x00c1
        L_0x0089:
            java.lang.Object r1 = r3.L$1
            com.bitcoin.mwallet.core.models.wallet.Wallet r1 = (com.bitcoin.mwallet.core.models.wallet.C1261Wallet) r1
            java.lang.Object r5 = r3.L$0
            com.bitcoin.mwallet.core.repositories.UtxoRepository r5 = (com.bitcoin.mwallet.core.repositories.UtxoRepository) r5
            kotlin.ResultKt.throwOnFailure(r2)
            goto L_0x00a8
        L_0x0095:
            kotlin.ResultKt.throwOnFailure(r2)
            com.bitcoin.mwallet.core.services.tx.TxService r2 = r0.txServiceGrpc
            r3.L$0 = r0
            r3.L$1 = r1
            r3.label = r12
            java.lang.Object r2 = r2.getUtxos(r1, r3)
            if (r2 != r4) goto L_0x00a7
            return r4
        L_0x00a7:
            r5 = r0
        L_0x00a8:
            com.bitcoin.mwallet.core.services.tx.GetUtxosResponse r2 = (com.bitcoin.mwallet.core.services.p010tx.GetUtxosResponse) r2
            com.bitcoin.mwallet.core.dao.SpentUtxoDao r13 = r5.spentUtxoDao
            r3.L$0 = r5
            r3.L$1 = r1
            r3.L$2 = r2
            r3.label = r10
            java.lang.Object r10 = r13.getSpentUtxos(r3)
            if (r10 != r4) goto L_0x00bb
            return r4
        L_0x00bb:
            r17 = r5
            r5 = r2
            r2 = r10
            r10 = r17
        L_0x00c1:
            java.util.List r2 = (java.util.List) r2
            com.bitcoin.mwallet.core.services.grpc.GrpcErrorResponse r13 = r5.getGrpcError()
            if (r13 == 0) goto L_0x00d9
            com.bitcoin.mwallet.core.services.grpc.GrpcErrorResponse r1 = r5.getGrpcError()
            java.lang.String r1 = r1.toString()
            java.lang.Object[] r2 = new java.lang.Object[r11]
            timber.log.Timber.m429e(r1, r2)
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        L_0x00d9:
            com.bitcoin.mwallet.core.models.tx.utxo.WalletUtxos r13 = r5.getWalletUtxos()
            if (r13 == 0) goto L_0x00e4
            java.util.List r13 = r13.getUtxos()
            goto L_0x00e5
        L_0x00e4:
            r13 = 0
        L_0x00e5:
            if (r13 == 0) goto L_0x019d
            com.bitcoin.mwallet.core.models.tx.utxo.WalletUtxos r13 = r5.getWalletUtxos()
            java.util.List r13 = r13.getUtxos()
            java.lang.Iterable r13 = (java.lang.Iterable) r13
            java.util.ArrayList r14 = new java.util.ArrayList
            r14.<init>()
            java.util.Collection r14 = (java.util.Collection) r14
            java.util.Iterator r13 = r13.iterator()
        L_0x00fc:
            boolean r15 = r13.hasNext()
            if (r15 == 0) goto L_0x0167
            java.lang.Object r15 = r13.next()
            r16 = r15
            com.bitcoin.mwallet.core.models.tx.utxo.Utxo r16 = (com.bitcoin.mwallet.core.models.p009tx.utxo.Utxo) r16
            r7 = r2
            java.lang.Iterable r7 = (java.lang.Iterable) r7
            boolean r12 = r7 instanceof java.util.Collection
            if (r12 == 0) goto L_0x011c
            r12 = r7
            java.util.Collection r12 = (java.util.Collection) r12
            boolean r12 = r12.isEmpty()
            if (r12 == 0) goto L_0x011c
        L_0x011a:
            r7 = 1
            goto L_0x0156
        L_0x011c:
            java.util.Iterator r7 = r7.iterator()
        L_0x0120:
            boolean r12 = r7.hasNext()
            if (r12 == 0) goto L_0x011a
            java.lang.Object r12 = r7.next()
            com.bitcoin.mwallet.core.models.tx.SpentUtxo r12 = (com.bitcoin.mwallet.core.models.p009tx.SpentUtxo) r12
            com.bitcoin.bitcoink.tx.TxId r8 = r12.getTxId()
            com.bitcoin.bitcoink.tx.TxId r9 = r16.getTxId()
            boolean r8 = kotlin.jvm.internal.Intrinsics.areEqual(r8, r9)
            if (r8 == 0) goto L_0x0146
            int r8 = r12.getOutputIndex()
            int r9 = r16.getOutputIndex()
            if (r8 != r9) goto L_0x0146
            r8 = 1
            goto L_0x0147
        L_0x0146:
            r8 = 0
        L_0x0147:
            java.lang.Boolean r8 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r8)
            boolean r8 = r8.booleanValue()
            if (r8 == 0) goto L_0x0153
            r7 = 0
            goto L_0x0156
        L_0x0153:
            r8 = 4
            r9 = 3
            goto L_0x0120
        L_0x0156:
            java.lang.Boolean r7 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r7)
            boolean r7 = r7.booleanValue()
            if (r7 == 0) goto L_0x0163
            r14.add(r15)
        L_0x0163:
            r8 = 4
            r9 = 3
            r12 = 1
            goto L_0x00fc
        L_0x0167:
            java.util.List r14 = (java.util.List) r14
            com.bitcoin.mwallet.core.dao.UtxoDao r7 = r10.utxoDao
            com.bitcoin.mwallet.core.models.wallet.WalletId r8 = r1.getId()
            r9 = r14
            java.util.Collection r9 = (java.util.Collection) r9
            com.bitcoin.mwallet.core.models.tx.utxo.Utxo[] r12 = new com.bitcoin.mwallet.core.models.p009tx.utxo.Utxo[r11]
            java.lang.Object[] r9 = r9.toArray(r12)
            if (r9 == 0) goto L_0x0197
            com.bitcoin.mwallet.core.models.tx.utxo.Utxo[] r9 = (com.bitcoin.mwallet.core.models.p009tx.utxo.Utxo[]) r9
            int r12 = r9.length
            java.lang.Object[] r9 = java.util.Arrays.copyOf(r9, r12)
            com.bitcoin.mwallet.core.models.tx.utxo.Utxo[] r9 = (com.bitcoin.mwallet.core.models.p009tx.utxo.Utxo[]) r9
            r3.L$0 = r10
            r3.L$1 = r1
            r3.L$2 = r5
            r3.L$3 = r2
            r3.L$4 = r14
            r12 = 3
            r3.label = r12
            java.lang.Object r7 = r7.replace(r8, r9, r3)
            if (r7 != r4) goto L_0x019d
            return r4
        L_0x0197:
            kotlin.TypeCastException r1 = new kotlin.TypeCastException
            r1.<init>(r6)
            throw r1
        L_0x019d:
            com.bitcoin.mwallet.core.models.tx.slputxo.WalletSlpUtxos r7 = r5.getWalletSlpUtxos()
            if (r7 == 0) goto L_0x01a8
            java.util.List r7 = r7.getUtxos()
            goto L_0x01a9
        L_0x01a8:
            r7 = 0
        L_0x01a9:
            if (r7 == 0) goto L_0x0267
            com.bitcoin.mwallet.core.models.tx.slputxo.WalletSlpUtxos r7 = r5.getWalletSlpUtxos()
            java.util.List r7 = r7.getUtxos()
            java.lang.Iterable r7 = (java.lang.Iterable) r7
            java.util.ArrayList r8 = new java.util.ArrayList
            r8.<init>()
            java.util.Collection r8 = (java.util.Collection) r8
            java.util.Iterator r7 = r7.iterator()
        L_0x01c0:
            boolean r9 = r7.hasNext()
            if (r9 == 0) goto L_0x022f
            java.lang.Object r9 = r7.next()
            r12 = r9
            com.bitcoin.mwallet.core.models.tx.slputxo.SlpUtxo r12 = (com.bitcoin.mwallet.core.models.p009tx.slputxo.SlpUtxo) r12
            r13 = r2
            java.lang.Iterable r13 = (java.lang.Iterable) r13
            boolean r14 = r13 instanceof java.util.Collection
            if (r14 == 0) goto L_0x01df
            r14 = r13
            java.util.Collection r14 = (java.util.Collection) r14
            boolean r14 = r14.isEmpty()
            if (r14 == 0) goto L_0x01df
        L_0x01dd:
            r11 = 1
            goto L_0x0220
        L_0x01df:
            java.util.Iterator r13 = r13.iterator()
        L_0x01e3:
            boolean r14 = r13.hasNext()
            if (r14 == 0) goto L_0x01dd
            java.lang.Object r14 = r13.next()
            com.bitcoin.mwallet.core.models.tx.SpentUtxo r14 = (com.bitcoin.mwallet.core.models.p009tx.SpentUtxo) r14
            com.bitcoin.bitcoink.tx.TxId r15 = r14.getTxId()
            com.bitcoin.mwallet.core.models.tx.utxo.Utxo r16 = r12.getUtxo()
            com.bitcoin.bitcoink.tx.TxId r11 = r16.getTxId()
            boolean r11 = kotlin.jvm.internal.Intrinsics.areEqual(r15, r11)
            if (r11 == 0) goto L_0x0211
            int r11 = r14.getOutputIndex()
            com.bitcoin.mwallet.core.models.tx.utxo.Utxo r14 = r12.getUtxo()
            int r14 = r14.getOutputIndex()
            if (r11 != r14) goto L_0x0211
            r11 = 1
            goto L_0x0212
        L_0x0211:
            r11 = 0
        L_0x0212:
            java.lang.Boolean r11 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r11)
            boolean r11 = r11.booleanValue()
            if (r11 == 0) goto L_0x021e
            r11 = 0
            goto L_0x0220
        L_0x021e:
            r11 = 0
            goto L_0x01e3
        L_0x0220:
            java.lang.Boolean r11 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r11)
            boolean r11 = r11.booleanValue()
            if (r11 == 0) goto L_0x022d
            r8.add(r9)
        L_0x022d:
            r11 = 0
            goto L_0x01c0
        L_0x022f:
            java.util.List r8 = (java.util.List) r8
            com.bitcoin.mwallet.core.dao.SlpUtxoDao r7 = r10.slpUtxoDao
            com.bitcoin.mwallet.core.models.wallet.WalletId r9 = r1.getId()
            r11 = r8
            java.util.Collection r11 = (java.util.Collection) r11
            r12 = 0
            com.bitcoin.mwallet.core.models.tx.slputxo.SlpUtxo[] r12 = new com.bitcoin.mwallet.core.models.p009tx.slputxo.SlpUtxo[r12]
            java.lang.Object[] r11 = r11.toArray(r12)
            if (r11 == 0) goto L_0x0261
            com.bitcoin.mwallet.core.models.tx.slputxo.SlpUtxo[] r11 = (com.bitcoin.mwallet.core.models.p009tx.slputxo.SlpUtxo[]) r11
            int r6 = r11.length
            java.lang.Object[] r6 = java.util.Arrays.copyOf(r11, r6)
            com.bitcoin.mwallet.core.models.tx.slputxo.SlpUtxo[] r6 = (com.bitcoin.mwallet.core.models.p009tx.slputxo.SlpUtxo[]) r6
            r3.L$0 = r10
            r3.L$1 = r1
            r3.L$2 = r5
            r3.L$3 = r2
            r3.L$4 = r8
            r1 = 4
            r3.label = r1
            java.lang.Object r2 = r7.replace(r9, r6, r3)
            if (r2 != r4) goto L_0x0260
            return r4
        L_0x0260:
            return r2
        L_0x0261:
            kotlin.TypeCastException r1 = new kotlin.TypeCastException
            r1.<init>(r6)
            throw r1
        L_0x0267:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.core.repositories.UtxoRepository.refreshUtxos(com.bitcoin.mwallet.core.models.wallet.Wallet, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Nullable
    public final Object setSent(@NotNull List<Pair<TxId, Integer>> list, @NotNull Continuation<? super Unit> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new UtxoRepository$setSent$2(this, list, null), continuation);
    }

    @Nullable
    public final Object upsert(@NotNull Collection<Utxo> collection, @NotNull Continuation<? super Unit> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new UtxoRepository$upsert$2(this, collection, null), continuation);
    }

    @Nullable
    public final Object slpUtxoUpsert(@NotNull Collection<SlpUtxo> collection, @NotNull Continuation<? super Unit> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new UtxoRepository$slpUtxoUpsert$2(this, collection, null), continuation);
    }

    @Nullable
    public final Object deleteByWalletId(@NotNull WalletId walletId, @NotNull Continuation<? super Unit> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new UtxoRepository$deleteByWalletId$2(this, walletId, null), continuation);
    }
}
