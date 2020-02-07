package com.bitcoin.mwallet.core.services;

import com.bitcoin.mwallet.core.repositories.SlpRepository;
import com.bitcoin.mwallet.core.repositories.TransactionHistoryRepository;
import com.bitcoin.mwallet.core.repositories.UtxoRepository;
import com.bitcoin.mwallet.core.repositories.VerifiedTokenRepository;
import com.bitcoin.mwallet.core.repositories.WalletRepository;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\u0011\u0010\u000f\u001a\u00020\u0010H@ø\u0001\u0000¢\u0006\u0002\u0010\u0011J\u0011\u0010\u0012\u001a\u00020\u0010H@ø\u0001\u0000¢\u0006\u0002\u0010\u0011R\u000e\u0010\r\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u0013"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/services/WalletRefresherTemp;", "", "walletRepository", "Lcom/bitcoin/mwallet/core/repositories/WalletRepository;", "utxoRepository", "Lcom/bitcoin/mwallet/core/repositories/UtxoRepository;", "slpRepository", "Lcom/bitcoin/mwallet/core/repositories/SlpRepository;", "txHistoryRepository", "Lcom/bitcoin/mwallet/core/repositories/TransactionHistoryRepository;", "verifiedTokenRepository", "Lcom/bitcoin/mwallet/core/repositories/VerifiedTokenRepository;", "(Lcom/bitcoin/mwallet/core/repositories/WalletRepository;Lcom/bitcoin/mwallet/core/repositories/UtxoRepository;Lcom/bitcoin/mwallet/core/repositories/SlpRepository;Lcom/bitcoin/mwallet/core/repositories/TransactionHistoryRepository;Lcom/bitcoin/mwallet/core/repositories/VerifiedTokenRepository;)V", "scheduler", "Ljava/util/concurrent/ScheduledExecutorService;", "doRefreshWallets", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "refreshWalletsAsync", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: WalletRefresherTemp.kt */
public final class WalletRefresherTemp {
    private final ScheduledExecutorService scheduler;
    private final SlpRepository slpRepository;
    private final TransactionHistoryRepository txHistoryRepository;
    private final UtxoRepository utxoRepository;
    private final VerifiedTokenRepository verifiedTokenRepository;
    private final WalletRepository walletRepository;

    public WalletRefresherTemp(@NotNull WalletRepository walletRepository2, @NotNull UtxoRepository utxoRepository2, @NotNull SlpRepository slpRepository2, @NotNull TransactionHistoryRepository transactionHistoryRepository, @NotNull VerifiedTokenRepository verifiedTokenRepository2) {
        Intrinsics.checkParameterIsNotNull(walletRepository2, "walletRepository");
        Intrinsics.checkParameterIsNotNull(utxoRepository2, "utxoRepository");
        Intrinsics.checkParameterIsNotNull(slpRepository2, "slpRepository");
        Intrinsics.checkParameterIsNotNull(transactionHistoryRepository, "txHistoryRepository");
        Intrinsics.checkParameterIsNotNull(verifiedTokenRepository2, "verifiedTokenRepository");
        this.walletRepository = walletRepository2;
        this.utxoRepository = utxoRepository2;
        this.slpRepository = slpRepository2;
        this.txHistoryRepository = transactionHistoryRepository;
        this.verifiedTokenRepository = verifiedTokenRepository2;
        ScheduledExecutorService newSingleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor();
        Intrinsics.checkExpressionValueIsNotNull(newSingleThreadScheduledExecutor, "Executors.newSingleThreadScheduledExecutor()");
        this.scheduler = newSingleThreadScheduledExecutor;
        this.scheduler.scheduleWithFixedDelay(new Runnable(this) {
            final /* synthetic */ WalletRefresherTemp this$0;

            @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, mo37405d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
            @DebugMetadata(mo37999c = "com.bitcoin.mwallet.core.services.WalletRefresherTemp$1$1", mo38000f = "WalletRefresherTemp.kt", mo38001i = {}, mo38002l = {33}, mo38003m = "invokeSuspend", mo38004n = {}, mo38005s = {})
            /* renamed from: com.bitcoin.mwallet.core.services.WalletRefresherTemp$1$1 */
            /* compiled from: WalletRefresherTemp.kt */
            static final class C12771 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                int label;

                /* renamed from: p$ */
                private CoroutineScope f429p$;
                final /* synthetic */ C12761 this$0;

                {
                    this.this$0 = r1;
                }

                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    Intrinsics.checkParameterIsNotNull(continuation, "completion");
                    C12771 r0 = new C12771(this.this$0, continuation);
                    r0.f429p$ = (CoroutineScope) obj;
                    return r0;
                }

                public final Object invoke(Object obj, Object obj2) {
                    return ((C12771) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
                }

                @Nullable
                public final Object invokeSuspend(@NotNull Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        CoroutineScope coroutineScope = this.f429p$;
                        WalletRefresherTemp walletRefresherTemp = this.this$0.this$0;
                        this.label = 1;
                        if (walletRefresherTemp.doRefreshWallets(this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else if (i == 1) {
                        ResultKt.throwOnFailure(obj);
                    } else {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    return Unit.INSTANCE;
                }
            }

            {
                this.this$0 = r1;
            }

            public final void run() {
                BuildersKt__BuildersKt.runBlocking$default(null, new C12771(this, null), 1, null);
            }
        }, 0, 30, TimeUnit.SECONDS);
    }

    @Nullable
    public final Object refreshWalletsAsync(@NotNull Continuation<? super Unit> continuation) {
        this.scheduler.schedule(new WalletRefresherTemp$refreshWalletsAsync$2(this), 0, TimeUnit.MILLISECONDS);
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0067, code lost:
        r19 = r8;
        r8 = r2;
        r20 = r9;
        r9 = r3;
        r2 = r4;
        r4 = r19;
        r5 = r20;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x00a2, code lost:
        r9 = r7;
        r10 = r8;
        r8 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x00d7, code lost:
        r1 = (java.lang.Iterable) r1;
        r7 = r1;
        r8 = r4;
        r6 = r1.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x00e4, code lost:
        if (r6.hasNext() == false) goto L_0x01a0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00e6, code lost:
        r1 = r6.next();
        r4 = (com.bitcoin.mwallet.core.models.wallet.C1261Wallet) r1;
        r5 = r8.slpRepository;
        r2.L$0 = r8;
        r2.L$1 = r7;
        r2.L$2 = r6;
        r2.L$3 = r1;
        r2.L$4 = r4;
        r2.label = 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0100, code lost:
        if (r5.refreshTokens(r4, r2) != r3) goto L_0x0103;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0102, code lost:
        return r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0103, code lost:
        r5 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0104, code lost:
        r1 = r8.utxoRepository;
        r2.L$0 = r8;
        r2.L$1 = r7;
        r2.L$2 = r6;
        r2.L$3 = r5;
        r2.L$4 = r4;
        r2.label = 3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0117, code lost:
        if (r1.refreshUtxos(r4, r2) != r3) goto L_0x00a2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0119, code lost:
        return r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x011a, code lost:
        r6 = r4.preference().getLastTxHistoryUpdate();
        r11 = r10.txHistoryRepository;
        r12 = r4.getId();
        r13 = r4.getCopayers().getWalletCopayerId();
        r14 = r4.getSigningKey();
        r15 = r4.getCoin();
        r2.L$0 = r10;
        r2.L$1 = r9;
        r2.L$2 = r8;
        r2.L$3 = r5;
        r2.L$4 = r4;
        r2.J$0 = r6;
        r2.label = 4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x014f, code lost:
        if (r11.refreshTransactionHistory(r12, r13, r14, r15, r6, r2) != r3) goto L_0x0152;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0151, code lost:
        return r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0152, code lost:
        r19 = r6;
        r6 = r4;
        r7 = r5;
        r4 = r19;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0158, code lost:
        r1 = r10.txHistoryRepository;
        r2.L$0 = r10;
        r2.L$1 = r9;
        r2.L$2 = r8;
        r2.L$3 = r7;
        r2.L$4 = r6;
        r2.J$0 = r4;
        r2.label = 5;
        r1 = r1.getLastConfirmedTransaction(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x016d, code lost:
        if (r1 != r3) goto L_0x0067;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x016f, code lost:
        return r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0170, code lost:
        r1 = (com.bitcoin.mwallet.core.models.p009tx.history.HistoricTransaction) r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0172, code lost:
        if (r1 == null) goto L_0x017f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0174, code lost:
        r6.preference().setLastTxHistoryUpdate(r1.getTimestamp());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x017f, code lost:
        r11 = r10.verifiedTokenRepository;
        r8.L$0 = r10;
        r8.L$1 = r5;
        r8.L$2 = r4;
        r8.L$3 = r7;
        r8.L$4 = r6;
        r8.J$0 = r2;
        r8.L$5 = r1;
        r8.label = 6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0196, code lost:
        if (r11.refreshVerifiedTokens(r8) != r9) goto L_0x0199;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0198, code lost:
        return r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0199, code lost:
        r6 = r4;
        r7 = r5;
        r2 = r8;
        r3 = r9;
        r8 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x01a2, code lost:
        return kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x0030  */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0050  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0074  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x008d  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x00a7  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x00bd  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x00c5  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0028  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ java.lang.Object doRefreshWallets(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r22) {
        /*
            r21 = this;
            r0 = r21
            r1 = r22
            boolean r2 = r1 instanceof com.bitcoin.mwallet.core.services.WalletRefresherTemp$doRefreshWallets$1
            if (r2 == 0) goto L_0x0018
            r2 = r1
            com.bitcoin.mwallet.core.services.WalletRefresherTemp$doRefreshWallets$1 r2 = (com.bitcoin.mwallet.core.services.WalletRefresherTemp$doRefreshWallets$1) r2
            int r3 = r2.label
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r3 & r4
            if (r3 == 0) goto L_0x0018
            int r1 = r2.label
            int r1 = r1 - r4
            r2.label = r1
            goto L_0x001d
        L_0x0018:
            com.bitcoin.mwallet.core.services.WalletRefresherTemp$doRefreshWallets$1 r2 = new com.bitcoin.mwallet.core.services.WalletRefresherTemp$doRefreshWallets$1
            r2.<init>(r0, r1)
        L_0x001d:
            java.lang.Object r1 = r2.result
            java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r4 = r2.label
            switch(r4) {
                case 0: goto L_0x00c5;
                case 1: goto L_0x00bd;
                case 2: goto L_0x00a7;
                case 3: goto L_0x008d;
                case 4: goto L_0x0074;
                case 5: goto L_0x0050;
                case 6: goto L_0x0030;
                default: goto L_0x0028;
            }
        L_0x0028:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r1
        L_0x0030:
            java.lang.Object r4 = r2.L$5
            com.bitcoin.mwallet.core.models.tx.history.HistoricTransaction r4 = (com.bitcoin.mwallet.core.models.p009tx.history.HistoricTransaction) r4
            long r4 = r2.J$0
            java.lang.Object r4 = r2.L$4
            com.bitcoin.mwallet.core.models.wallet.Wallet r4 = (com.bitcoin.mwallet.core.models.wallet.C1261Wallet) r4
            java.lang.Object r4 = r2.L$3
            java.lang.Object r4 = r2.L$2
            java.util.Iterator r4 = (java.util.Iterator) r4
            java.lang.Object r5 = r2.L$1
            java.lang.Iterable r5 = (java.lang.Iterable) r5
            java.lang.Object r6 = r2.L$0
            com.bitcoin.mwallet.core.services.WalletRefresherTemp r6 = (com.bitcoin.mwallet.core.services.WalletRefresherTemp) r6
            kotlin.ResultKt.throwOnFailure(r1)
            r7 = r5
            r8 = r6
            r6 = r4
            goto L_0x00e0
        L_0x0050:
            long r4 = r2.J$0
            java.lang.Object r6 = r2.L$4
            com.bitcoin.mwallet.core.models.wallet.Wallet r6 = (com.bitcoin.mwallet.core.models.wallet.C1261Wallet) r6
            java.lang.Object r7 = r2.L$3
            java.lang.Object r8 = r2.L$2
            java.util.Iterator r8 = (java.util.Iterator) r8
            java.lang.Object r9 = r2.L$1
            java.lang.Iterable r9 = (java.lang.Iterable) r9
            java.lang.Object r10 = r2.L$0
            com.bitcoin.mwallet.core.services.WalletRefresherTemp r10 = (com.bitcoin.mwallet.core.services.WalletRefresherTemp) r10
            kotlin.ResultKt.throwOnFailure(r1)
        L_0x0067:
            r19 = r8
            r8 = r2
            r20 = r9
            r9 = r3
            r2 = r4
            r4 = r19
            r5 = r20
            goto L_0x0170
        L_0x0074:
            long r4 = r2.J$0
            java.lang.Object r6 = r2.L$4
            com.bitcoin.mwallet.core.models.wallet.Wallet r6 = (com.bitcoin.mwallet.core.models.wallet.C1261Wallet) r6
            java.lang.Object r7 = r2.L$3
            java.lang.Object r8 = r2.L$2
            java.util.Iterator r8 = (java.util.Iterator) r8
            java.lang.Object r9 = r2.L$1
            java.lang.Iterable r9 = (java.lang.Iterable) r9
            java.lang.Object r10 = r2.L$0
            com.bitcoin.mwallet.core.services.WalletRefresherTemp r10 = (com.bitcoin.mwallet.core.services.WalletRefresherTemp) r10
            kotlin.ResultKt.throwOnFailure(r1)
            goto L_0x0158
        L_0x008d:
            java.lang.Object r4 = r2.L$4
            com.bitcoin.mwallet.core.models.wallet.Wallet r4 = (com.bitcoin.mwallet.core.models.wallet.C1261Wallet) r4
            java.lang.Object r5 = r2.L$3
            java.lang.Object r6 = r2.L$2
            java.util.Iterator r6 = (java.util.Iterator) r6
            java.lang.Object r7 = r2.L$1
            java.lang.Iterable r7 = (java.lang.Iterable) r7
            java.lang.Object r8 = r2.L$0
            com.bitcoin.mwallet.core.services.WalletRefresherTemp r8 = (com.bitcoin.mwallet.core.services.WalletRefresherTemp) r8
            kotlin.ResultKt.throwOnFailure(r1)
        L_0x00a2:
            r9 = r7
            r10 = r8
            r8 = r6
            goto L_0x011a
        L_0x00a7:
            java.lang.Object r4 = r2.L$4
            com.bitcoin.mwallet.core.models.wallet.Wallet r4 = (com.bitcoin.mwallet.core.models.wallet.C1261Wallet) r4
            java.lang.Object r5 = r2.L$3
            java.lang.Object r6 = r2.L$2
            java.util.Iterator r6 = (java.util.Iterator) r6
            java.lang.Object r7 = r2.L$1
            java.lang.Iterable r7 = (java.lang.Iterable) r7
            java.lang.Object r8 = r2.L$0
            com.bitcoin.mwallet.core.services.WalletRefresherTemp r8 = (com.bitcoin.mwallet.core.services.WalletRefresherTemp) r8
            kotlin.ResultKt.throwOnFailure(r1)
            goto L_0x0104
        L_0x00bd:
            java.lang.Object r4 = r2.L$0
            com.bitcoin.mwallet.core.services.WalletRefresherTemp r4 = (com.bitcoin.mwallet.core.services.WalletRefresherTemp) r4
            kotlin.ResultKt.throwOnFailure(r1)
            goto L_0x00d7
        L_0x00c5:
            kotlin.ResultKt.throwOnFailure(r1)
            com.bitcoin.mwallet.core.repositories.WalletRepository r1 = r0.walletRepository
            r2.L$0 = r0
            r4 = 1
            r2.label = r4
            java.lang.Object r1 = r1.getWallets(r2)
            if (r1 != r3) goto L_0x00d6
            return r3
        L_0x00d6:
            r4 = r0
        L_0x00d7:
            java.lang.Iterable r1 = (java.lang.Iterable) r1
            java.util.Iterator r5 = r1.iterator()
            r7 = r1
            r8 = r4
            r6 = r5
        L_0x00e0:
            boolean r1 = r6.hasNext()
            if (r1 == 0) goto L_0x01a0
            java.lang.Object r1 = r6.next()
            r4 = r1
            com.bitcoin.mwallet.core.models.wallet.Wallet r4 = (com.bitcoin.mwallet.core.models.wallet.C1261Wallet) r4
            com.bitcoin.mwallet.core.repositories.SlpRepository r5 = r8.slpRepository
            r2.L$0 = r8
            r2.L$1 = r7
            r2.L$2 = r6
            r2.L$3 = r1
            r2.L$4 = r4
            r9 = 2
            r2.label = r9
            java.lang.Object r5 = r5.refreshTokens(r4, r2)
            if (r5 != r3) goto L_0x0103
            return r3
        L_0x0103:
            r5 = r1
        L_0x0104:
            com.bitcoin.mwallet.core.repositories.UtxoRepository r1 = r8.utxoRepository
            r2.L$0 = r8
            r2.L$1 = r7
            r2.L$2 = r6
            r2.L$3 = r5
            r2.L$4 = r4
            r9 = 3
            r2.label = r9
            java.lang.Object r1 = r1.refreshUtxos(r4, r2)
            if (r1 != r3) goto L_0x00a2
            return r3
        L_0x011a:
            com.bitcoin.mwallet.core.preferences.WalletPreference r1 = r4.preference()
            long r6 = r1.getLastTxHistoryUpdate()
            com.bitcoin.mwallet.core.repositories.TransactionHistoryRepository r11 = r10.txHistoryRepository
            com.bitcoin.mwallet.core.models.wallet.WalletId r12 = r4.getId()
            com.bitcoin.mwallet.core.models.Copayers r1 = r4.getCopayers()
            com.bitcoin.mwallet.core.models.CopayerId r13 = r1.getWalletCopayerId()
            com.bitcoin.mwallet.core.utils.signature.SigningKey r14 = r4.getSigningKey()
            com.bitcoin.mwallet.core.models.Coin r15 = r4.getCoin()
            r2.L$0 = r10
            r2.L$1 = r9
            r2.L$2 = r8
            r2.L$3 = r5
            r2.L$4 = r4
            r2.J$0 = r6
            r1 = 4
            r2.label = r1
            r16 = r6
            r18 = r2
            java.lang.Object r1 = r11.refreshTransactionHistory(r12, r13, r14, r15, r16, r18)
            if (r1 != r3) goto L_0x0152
            return r3
        L_0x0152:
            r19 = r6
            r6 = r4
            r7 = r5
            r4 = r19
        L_0x0158:
            com.bitcoin.mwallet.core.repositories.TransactionHistoryRepository r1 = r10.txHistoryRepository
            r2.L$0 = r10
            r2.L$1 = r9
            r2.L$2 = r8
            r2.L$3 = r7
            r2.L$4 = r6
            r2.J$0 = r4
            r11 = 5
            r2.label = r11
            java.lang.Object r1 = r1.getLastConfirmedTransaction(r2)
            if (r1 != r3) goto L_0x0067
            return r3
        L_0x0170:
            com.bitcoin.mwallet.core.models.tx.history.HistoricTransaction r1 = (com.bitcoin.mwallet.core.models.p009tx.history.HistoricTransaction) r1
            if (r1 == 0) goto L_0x017f
            com.bitcoin.mwallet.core.preferences.WalletPreference r11 = r6.preference()
            long r12 = r1.getTimestamp()
            r11.setLastTxHistoryUpdate(r12)
        L_0x017f:
            com.bitcoin.mwallet.core.repositories.VerifiedTokenRepository r11 = r10.verifiedTokenRepository
            r8.L$0 = r10
            r8.L$1 = r5
            r8.L$2 = r4
            r8.L$3 = r7
            r8.L$4 = r6
            r8.J$0 = r2
            r8.L$5 = r1
            r1 = 6
            r8.label = r1
            java.lang.Object r1 = r11.refreshVerifiedTokens(r8)
            if (r1 != r9) goto L_0x0199
            return r9
        L_0x0199:
            r6 = r4
            r7 = r5
            r2 = r8
            r3 = r9
            r8 = r10
            goto L_0x00e0
        L_0x01a0:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.core.services.WalletRefresherTemp.doRefreshWallets(kotlin.coroutines.Continuation):java.lang.Object");
    }
}
