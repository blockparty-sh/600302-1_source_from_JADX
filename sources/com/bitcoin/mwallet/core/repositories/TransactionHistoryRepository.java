package com.bitcoin.mwallet.core.repositories;

import androidx.lifecycle.LiveData;
import com.bitcoin.bitcoink.p008tx.TxId;
import com.bitcoin.mwallet.TxHistory;
import com.bitcoin.mwallet.core.dao.SlpDao;
import com.bitcoin.mwallet.core.dao.TransactionHistoryDao;
import com.bitcoin.mwallet.core.models.Coin;
import com.bitcoin.mwallet.core.models.CopayerId;
import com.bitcoin.mwallet.core.models.p009tx.TxAction;
import com.bitcoin.mwallet.core.models.p009tx.history.HistoricTransaction;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import com.bitcoin.mwallet.core.services.grpc.MeasuredCompletingStreamObserver;
import com.bitcoin.mwallet.core.services.p010tx.TxService;
import com.bitcoin.mwallet.core.utils.signature.SigningKey;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import p015io.grpc.stub.StreamObserver;
import timber.log.Timber;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u00002\u00020\u0001:\u0001/B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0019\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H@ø\u0001\u0000¢\u0006\u0002\u0010\u0013J\u0013\u0010\u0014\u001a\u0004\u0018\u00010\fH@ø\u0001\u0000¢\u0006\u0002\u0010\u0015J\u001c\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\f0\n2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0011\u001a\u00020\u0012J#\u0010\u0019\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u001bH@ø\u0001\u0000¢\u0006\u0002\u0010\u001cJ\u001a\u0010\u001d\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\n2\u0006\u0010\u0011\u001a\u00020\u0012J9\u0010\u001e\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&H@ø\u0001\u0000¢\u0006\u0002\u0010'J)\u0010(\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010)\u001a\u00020*2\u0006\u0010\u0011\u001a\u00020\u0012H@ø\u0001\u0000¢\u0006\u0002\u0010+J\u0019\u0010,\u001a\u00020\u00102\u0006\u0010-\u001a\u00020\fH@ø\u0001\u0000¢\u0006\u0002\u0010.R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\n¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u00060"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/repositories/TransactionHistoryRepository;", "", "dao", "Lcom/bitcoin/mwallet/core/dao/TransactionHistoryDao;", "txService", "Lcom/bitcoin/mwallet/core/services/tx/TxService;", "slpDao", "Lcom/bitcoin/mwallet/core/dao/SlpDao;", "(Lcom/bitcoin/mwallet/core/dao/TransactionHistoryDao;Lcom/bitcoin/mwallet/core/services/tx/TxService;Lcom/bitcoin/mwallet/core/dao/SlpDao;)V", "recentTransactions", "Landroidx/lifecycle/LiveData;", "", "Lcom/bitcoin/mwallet/core/models/tx/history/HistoricTransaction;", "getRecentTransactions", "()Landroidx/lifecycle/LiveData;", "deleteByWalletId", "", "walletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "(Lcom/bitcoin/mwallet/core/models/wallet/WalletId;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getLastConfirmedTransaction", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTransactionById", "txId", "Lcom/bitcoin/bitcoink/tx/TxId;", "getTransactionByIdAndAction", "txAction", "Lcom/bitcoin/mwallet/core/models/tx/TxAction;", "(Lcom/bitcoin/bitcoink/tx/TxId;Lcom/bitcoin/mwallet/core/models/tx/TxAction;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTransactions", "refreshTransactionHistory", "copayerId", "Lcom/bitcoin/mwallet/core/models/CopayerId;", "signingKey", "Lcom/bitcoin/mwallet/core/utils/signature/SigningKey;", "coin", "Lcom/bitcoin/mwallet/core/models/Coin;", "startTime", "", "(Lcom/bitcoin/mwallet/core/models/wallet/WalletId;Lcom/bitcoin/mwallet/core/models/CopayerId;Lcom/bitcoin/mwallet/core/utils/signature/SigningKey;Lcom/bitcoin/mwallet/core/models/Coin;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setTransactionNote", "note", "", "(Lcom/bitcoin/bitcoink/tx/TxId;Ljava/lang/String;Lcom/bitcoin/mwallet/core/models/wallet/WalletId;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "upsert", "tx", "(Lcom/bitcoin/mwallet/core/models/tx/history/HistoricTransaction;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "TransactionHistoryObserver", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: TransactionHistoryRepository.kt */
public final class TransactionHistoryRepository {
    /* access modifiers changed from: private */
    public final TransactionHistoryDao dao;
    @NotNull
    private final LiveData<List<HistoricTransaction>> recentTransactions = this.dao.selectLatest(5);
    /* access modifiers changed from: private */
    public final SlpDao slpDao;
    private final TxService txService;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0003\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\u0012\u0010\b\u001a\u00020\u00072\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016J\u0012\u0010\u000b\u001a\u00020\u00072\b\u0010\f\u001a\u0004\u0018\u00010\u0002H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/repositories/TransactionHistoryRepository$TransactionHistoryObserver;", "Lio/grpc/stub/StreamObserver;", "Lcom/bitcoin/mwallet/TxHistory;", "coin", "Lcom/bitcoin/mwallet/core/models/Coin;", "(Lcom/bitcoin/mwallet/core/repositories/TransactionHistoryRepository;Lcom/bitcoin/mwallet/core/models/Coin;)V", "onCompleted", "", "onError", "t", "", "onNext", "value", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: TransactionHistoryRepository.kt */
    public final class TransactionHistoryObserver implements StreamObserver<TxHistory> {
        /* access modifiers changed from: private */
        public final Coin coin;
        final /* synthetic */ TransactionHistoryRepository this$0;

        public void onCompleted() {
        }

        public TransactionHistoryObserver(@NotNull TransactionHistoryRepository transactionHistoryRepository, Coin coin2) {
            Intrinsics.checkParameterIsNotNull(coin2, "coin");
            this.this$0 = transactionHistoryRepository;
            this.coin = coin2;
        }

        public void onNext(@Nullable TxHistory txHistory) {
            if (txHistory != null) {
                BuildersKt__BuildersKt.runBlocking$default(null, new TransactionHistoryRepository$TransactionHistoryObserver$onNext$1(this, txHistory, null), 1, null);
                return;
            }
            Timber.m438w("Received null from TxHistory stream", new Object[0]);
        }

        public void onError(@Nullable Throwable th) {
            Timber.m428d(th, "TxHistory onError", new Object[0]);
        }
    }

    public TransactionHistoryRepository(@NotNull TransactionHistoryDao transactionHistoryDao, @NotNull TxService txService2, @NotNull SlpDao slpDao2) {
        Intrinsics.checkParameterIsNotNull(transactionHistoryDao, "dao");
        Intrinsics.checkParameterIsNotNull(txService2, "txService");
        Intrinsics.checkParameterIsNotNull(slpDao2, "slpDao");
        this.dao = transactionHistoryDao;
        this.txService = txService2;
        this.slpDao = slpDao2;
    }

    @NotNull
    public final LiveData<List<HistoricTransaction>> getRecentTransactions() {
        return this.recentTransactions;
    }

    @NotNull
    public final LiveData<HistoricTransaction> getTransactionById(@NotNull TxId txId, @NotNull WalletId walletId) {
        Intrinsics.checkParameterIsNotNull(txId, "txId");
        Intrinsics.checkParameterIsNotNull(walletId, "walletId");
        return this.dao.selectById(txId, walletId);
    }

    @Nullable
    public final Object setTransactionNote(@NotNull TxId txId, @NotNull String str, @NotNull WalletId walletId, @NotNull Continuation<? super Unit> continuation) {
        CoroutineContext io = Dispatchers.getIO();
        TransactionHistoryRepository$setTransactionNote$2 transactionHistoryRepository$setTransactionNote$2 = new TransactionHistoryRepository$setTransactionNote$2(this, txId, str, walletId, null);
        return BuildersKt.withContext(io, transactionHistoryRepository$setTransactionNote$2, continuation);
    }

    @Nullable
    public final Object getTransactionByIdAndAction(@NotNull TxId txId, @NotNull TxAction txAction, @NotNull Continuation<? super HistoricTransaction> continuation) {
        return this.dao.selectTransactionByIdAndAction(txId, txAction, continuation);
    }

    @NotNull
    public final LiveData<List<HistoricTransaction>> getTransactions(@NotNull WalletId walletId) {
        Intrinsics.checkParameterIsNotNull(walletId, "walletId");
        return this.dao.selectByWalletId(walletId);
    }

    @Nullable
    public final Object getLastConfirmedTransaction(@NotNull Continuation<? super HistoricTransaction> continuation) {
        return this.dao.selectLastConfirmedTransaction(continuation);
    }

    @Nullable
    public final Object refreshTransactionHistory(@NotNull WalletId walletId, @NotNull CopayerId copayerId, @NotNull SigningKey signingKey, @NotNull Coin coin, long j, @NotNull Continuation<? super Unit> continuation) {
        return this.txService.getTransactionHistory(walletId, copayerId, signingKey, new MeasuredCompletingStreamObserver(new TransactionHistoryObserver(this, coin)), j, continuation);
    }

    @Nullable
    public final Object upsert(@NotNull HistoricTransaction historicTransaction, @NotNull Continuation<? super Unit> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new TransactionHistoryRepository$upsert$2(this, historicTransaction, null), continuation);
    }

    @Nullable
    public final Object deleteByWalletId(@NotNull WalletId walletId, @NotNull Continuation<? super Unit> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new TransactionHistoryRepository$deleteByWalletId$2(this, walletId, null), continuation);
    }
}
