package com.bitcoin.mwallet.app.flows.walletdetails.overview.historyassets.transactionhistory;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.bitcoin.bitcoink.p008tx.TxId;
import com.bitcoin.mwallet.app.android.LiveDataKt;
import com.bitcoin.mwallet.app.viper.PresenterBase;
import com.bitcoin.mwallet.core.interactors.GetCurrentExchangeRateInteractor;
import com.bitcoin.mwallet.core.interactors.GetDefaultCurrencyInteractor;
import com.bitcoin.mwallet.core.interactors.ModifyWalletInteractor;
import com.bitcoin.mwallet.core.models.Coin;
import com.bitcoin.mwallet.core.models.p009tx.history.HistoricTransaction;
import com.bitcoin.mwallet.core.models.wallet.C1261Wallet;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import com.bitcoin.mwallet.core.repositories.SlpRepository;
import com.bitcoin.mwallet.core.repositories.WalletRepository;
import com.bitcoin.mwallet.core.views.transaction.TransactionClickedListener;
import com.bitcoin.mwallet.core.views.transaction.TransactionSummaryListAdapter.TransactionSummaryClickedListener;
import com.bitcoin.mwallet.core.views.transaction.TransactionSummaryView;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003BE\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013¢\u0006\u0002\u0010\u0014J\u000e\u0010:\u001a\u00020;2\u0006\u00104\u001a\u000205J\u0010\u0010<\u001a\u00020;2\u0006\u0010=\u001a\u00020\u0017H\u0016J\u0010\u0010>\u001a\u00020;2\u0006\u0010>\u001a\u00020\u001eH\u0016J\u0012\u0010?\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020@0(0'R(\u0010\u0015\u001a\u0010\u0012\f\u0012\n \u0018*\u0004\u0018\u00010\u00170\u00170\u0016X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001e0\u0016¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001aR\u001c\u0010 \u001a\u0004\u0018\u00010!X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u000e\u0010\u0012\u001a\u00020\u0013X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0004¢\u0006\u0002\n\u0000R'\u0010&\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020)0(0'8BX\u0002¢\u0006\f\n\u0004\b,\u0010-\u001a\u0004\b*\u0010+R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u001c\u0010.\u001a\u0004\u0018\u00010/X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u00101\"\u0004\b2\u00103R\u001a\u00104\u001a\u000205X.¢\u0006\u000e\n\u0000\u001a\u0004\b6\u00107\"\u0004\b8\u00109R\u000e\u0010\u0010\u001a\u00020\u0011X\u0004¢\u0006\u0002\n\u0000¨\u0006A"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/walletdetails/overview/historyassets/transactionhistory/TransactionHistoryPresenter;", "Lcom/bitcoin/mwallet/app/viper/PresenterBase;", "Lcom/bitcoin/mwallet/core/views/transaction/TransactionSummaryListAdapter$TransactionSummaryClickedListener;", "Lcom/bitcoin/mwallet/app/flows/walletdetails/overview/historyassets/transactionhistory/SLPTxHistoryEnabledListener;", "context", "Landroid/content/Context;", "viewModelScope", "Lkotlinx/coroutines/CoroutineScope;", "getCurrentExchangeRateInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetCurrentExchangeRateInteractor;", "getDefaultCurrencyInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetDefaultCurrencyInteractor;", "getCompleteTransactionHistoryInteractor", "Lcom/bitcoin/mwallet/app/flows/walletdetails/overview/historyassets/transactionhistory/GetCompleteTransactionHistoryInteractor;", "slpRepository", "Lcom/bitcoin/mwallet/core/repositories/SlpRepository;", "walletRepository", "Lcom/bitcoin/mwallet/core/repositories/WalletRepository;", "modifyWalletInteractor", "Lcom/bitcoin/mwallet/core/interactors/ModifyWalletInteractor;", "(Landroid/content/Context;Lkotlinx/coroutines/CoroutineScope;Lcom/bitcoin/mwallet/core/interactors/GetCurrentExchangeRateInteractor;Lcom/bitcoin/mwallet/core/interactors/GetDefaultCurrencyInteractor;Lcom/bitcoin/mwallet/app/flows/walletdetails/overview/historyassets/transactionhistory/GetCompleteTransactionHistoryInteractor;Lcom/bitcoin/mwallet/core/repositories/SlpRepository;Lcom/bitcoin/mwallet/core/repositories/WalletRepository;Lcom/bitcoin/mwallet/core/interactors/ModifyWalletInteractor;)V", "asset", "Landroidx/lifecycle/MutableLiveData;", "", "kotlin.jvm.PlatformType", "getAsset", "()Landroidx/lifecycle/MutableLiveData;", "setAsset", "(Landroidx/lifecycle/MutableLiveData;)V", "hideSlpTransaction", "", "getHideSlpTransaction", "listener", "Lcom/bitcoin/mwallet/core/views/transaction/TransactionClickedListener;", "getListener", "()Lcom/bitcoin/mwallet/core/views/transaction/TransactionClickedListener;", "setListener", "(Lcom/bitcoin/mwallet/core/views/transaction/TransactionClickedListener;)V", "transactions", "Landroidx/lifecycle/LiveData;", "", "Lcom/bitcoin/mwallet/core/models/tx/history/HistoricTransaction;", "getTransactions", "()Landroidx/lifecycle/LiveData;", "transactions$delegate", "Lkotlin/Lazy;", "wallet", "Lcom/bitcoin/mwallet/core/models/wallet/Wallet;", "getWallet", "()Lcom/bitcoin/mwallet/core/models/wallet/Wallet;", "setWallet", "(Lcom/bitcoin/mwallet/core/models/wallet/Wallet;)V", "walletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "getWalletId", "()Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "setWalletId", "(Lcom/bitcoin/mwallet/core/models/wallet/WalletId;)V", "initWallet", "", "onTransactionSummaryClicked", "txid", "showBCHOnly", "transactionSummaries", "Lcom/bitcoin/mwallet/core/views/transaction/TransactionSummaryView;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: TransactionHistoryPresenter.kt */
public final class TransactionHistoryPresenter extends PresenterBase implements TransactionSummaryClickedListener, SLPTxHistoryEnabledListener {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(TransactionHistoryPresenter.class), "transactions", "getTransactions()Landroidx/lifecycle/LiveData;"))};
    @NotNull
    private MutableLiveData<String> asset = new MutableLiveData<>("");
    /* access modifiers changed from: private */
    public final Context context;
    /* access modifiers changed from: private */
    public final GetCompleteTransactionHistoryInteractor getCompleteTransactionHistoryInteractor;
    private final GetCurrentExchangeRateInteractor getCurrentExchangeRateInteractor;
    private final GetDefaultCurrencyInteractor getDefaultCurrencyInteractor;
    @NotNull
    private final MutableLiveData<Boolean> hideSlpTransaction = new MutableLiveData<>(Boolean.valueOf(false));
    @Nullable
    private TransactionClickedListener listener;
    /* access modifiers changed from: private */
    public final ModifyWalletInteractor modifyWalletInteractor;
    private final SlpRepository slpRepository;
    private final Lazy transactions$delegate = LazyKt.lazy(new TransactionHistoryPresenter$transactions$2(this));
    private final CoroutineScope viewModelScope;
    @Nullable
    private C1261Wallet wallet;
    @NotNull
    public WalletId walletId;
    /* access modifiers changed from: private */
    public final WalletRepository walletRepository;

    private final LiveData<List<HistoricTransaction>> getTransactions() {
        Lazy lazy = this.transactions$delegate;
        KProperty kProperty = $$delegatedProperties[0];
        return (LiveData) lazy.getValue();
    }

    public TransactionHistoryPresenter(@NotNull Context context2, @NotNull CoroutineScope coroutineScope, @NotNull GetCurrentExchangeRateInteractor getCurrentExchangeRateInteractor2, @NotNull GetDefaultCurrencyInteractor getDefaultCurrencyInteractor2, @NotNull GetCompleteTransactionHistoryInteractor getCompleteTransactionHistoryInteractor2, @NotNull SlpRepository slpRepository2, @NotNull WalletRepository walletRepository2, @NotNull ModifyWalletInteractor modifyWalletInteractor2) {
        Intrinsics.checkParameterIsNotNull(context2, "context");
        Intrinsics.checkParameterIsNotNull(coroutineScope, "viewModelScope");
        Intrinsics.checkParameterIsNotNull(getCurrentExchangeRateInteractor2, "getCurrentExchangeRateInteractor");
        Intrinsics.checkParameterIsNotNull(getDefaultCurrencyInteractor2, "getDefaultCurrencyInteractor");
        Intrinsics.checkParameterIsNotNull(getCompleteTransactionHistoryInteractor2, "getCompleteTransactionHistoryInteractor");
        Intrinsics.checkParameterIsNotNull(slpRepository2, "slpRepository");
        Intrinsics.checkParameterIsNotNull(walletRepository2, "walletRepository");
        Intrinsics.checkParameterIsNotNull(modifyWalletInteractor2, "modifyWalletInteractor");
        this.context = context2;
        this.viewModelScope = coroutineScope;
        this.getCurrentExchangeRateInteractor = getCurrentExchangeRateInteractor2;
        this.getDefaultCurrencyInteractor = getDefaultCurrencyInteractor2;
        this.getCompleteTransactionHistoryInteractor = getCompleteTransactionHistoryInteractor2;
        this.slpRepository = slpRepository2;
        this.walletRepository = walletRepository2;
        this.modifyWalletInteractor = modifyWalletInteractor2;
    }

    @NotNull
    public final WalletId getWalletId() {
        WalletId walletId2 = this.walletId;
        if (walletId2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("walletId");
        }
        return walletId2;
    }

    public final void setWalletId(@NotNull WalletId walletId2) {
        Intrinsics.checkParameterIsNotNull(walletId2, "<set-?>");
        this.walletId = walletId2;
    }

    @Nullable
    public final C1261Wallet getWallet() {
        return this.wallet;
    }

    public final void setWallet(@Nullable C1261Wallet wallet2) {
        this.wallet = wallet2;
    }

    @NotNull
    public final MutableLiveData<String> getAsset() {
        return this.asset;
    }

    public final void setAsset(@NotNull MutableLiveData<String> mutableLiveData) {
        Intrinsics.checkParameterIsNotNull(mutableLiveData, "<set-?>");
        this.asset = mutableLiveData;
    }

    @NotNull
    public final MutableLiveData<Boolean> getHideSlpTransaction() {
        return this.hideSlpTransaction;
    }

    public final void initWallet(@NotNull WalletId walletId2) {
        Coin coin;
        Intrinsics.checkParameterIsNotNull(walletId2, "walletId");
        this.walletId = walletId2;
        this.wallet = (C1261Wallet) BuildersKt__BuildersKt.runBlocking$default(null, new TransactionHistoryPresenter$initWallet$1(this, walletId2, null), 1, null);
        MutableLiveData<Boolean> mutableLiveData = this.hideSlpTransaction;
        C1261Wallet wallet2 = this.wallet;
        if (wallet2 == null) {
            Intrinsics.throwNpe();
        }
        mutableLiveData.setValue(Boolean.valueOf(true ^ wallet2.preference().getShowSlpTransaction()));
        MutableLiveData<String> mutableLiveData2 = this.asset;
        C1261Wallet wallet3 = this.wallet;
        if (wallet3 != null) {
            if (wallet3 == null) {
                Intrinsics.throwNpe();
            }
            coin = wallet3.getCoin();
        } else {
            coin = Coin.BCH;
        }
        mutableLiveData2.setValue(coin.getTicker());
    }

    @NotNull
    public final LiveData<List<TransactionSummaryView>> transactionSummaries() {
        LiveData liveData = this.hideSlpTransaction;
        LiveData transactions = getTransactions();
        LiveData currentExchangeRate = this.getCurrentExchangeRateInteractor.getCurrentExchangeRate(this.getDefaultCurrencyInteractor.getDefaultFiatCurrency());
        SlpRepository slpRepository2 = this.slpRepository;
        WalletId walletId2 = this.walletId;
        if (walletId2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("walletId");
        }
        return LiveDataKt.combineLatestIgnoreNull(liveData, transactions, currentExchangeRate, slpRepository2.getAllRawTokens(walletId2), new TransactionHistoryPresenter$transactionSummaries$1(this));
    }

    @Nullable
    public final TransactionClickedListener getListener() {
        return this.listener;
    }

    public final void setListener(@Nullable TransactionClickedListener transactionClickedListener) {
        this.listener = transactionClickedListener;
    }

    public void onTransactionSummaryClicked(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "txid");
        TransactionClickedListener transactionClickedListener = this.listener;
        if (transactionClickedListener != null) {
            transactionClickedListener.onTransactionClicked(new TxId(str));
        }
    }

    public void showBCHOnly(boolean z) {
        this.hideSlpTransaction.setValue(Boolean.valueOf(z));
        BuildersKt__Builders_commonKt.launch$default(this.viewModelScope, null, null, new TransactionHistoryPresenter$showBCHOnly$1(this, z, null), 3, null);
    }
}
