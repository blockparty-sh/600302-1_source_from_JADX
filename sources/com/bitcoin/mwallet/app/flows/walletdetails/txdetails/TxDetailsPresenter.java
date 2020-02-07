package com.bitcoin.mwallet.app.flows.walletdetails.txdetails;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.widget.Toast;
import androidx.lifecycle.LiveData;
import com.bitcoin.bitcoink.p008tx.TxId;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.app.android.LiveDataKt;
import com.bitcoin.mwallet.app.viper.ScreenPresenter;
import com.bitcoin.mwallet.core.interactors.GetCurrentExchangeRateInteractor;
import com.bitcoin.mwallet.core.interactors.GetDefaultCurrencyInteractor;
import com.bitcoin.mwallet.core.interactors.GetWalletInteractor;
import com.bitcoin.mwallet.core.interactors.VerifiedTokenInteractor;
import com.bitcoin.mwallet.core.models.Coin;
import com.bitcoin.mwallet.core.models.exchangerate.FiatExchangeRate;
import com.bitcoin.mwallet.core.models.p009tx.history.HistoricTransaction;
import com.bitcoin.mwallet.core.models.verifiedtoken.VerifiedToken;
import com.bitcoin.mwallet.core.models.wallet.C1261Wallet;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import com.bitcoin.mwallet.core.repositories.NotesRepository;
import com.bitcoin.mwallet.core.repositories.SlpRepository;
import com.bitcoin.mwallet.core.repositories.TransactionHistoryRepository;
import com.bitcoin.mwallet.core.repositories.VerifiedTokenRepository;
import com.bitcoin.mwallet.core.services.AnalyticsService;
import java.util.Currency;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000¬\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001Be\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\u0006\u0010\u0013\u001a\u00020\u0014\u0012\u0006\u0010\u0015\u001a\u00020\u0016\u0012\u0006\u0010\u0017\u001a\u00020\u0018\u0012\u0006\u0010\u0019\u001a\u00020\u0002¢\u0006\u0002\u0010\u001aJ\u0016\u0010J\u001a\u00020K2\u0006\u0010L\u001a\u00020,2\u0006\u0010M\u001a\u00020,J\u000e\u0010N\u001a\u00020O2\u0006\u0010P\u001a\u00020QJ\u0018\u0010R\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020U\u0012\u0004\u0012\u00020=0T0SJ\f\u0010V\u001a\b\u0012\u0004\u0012\u00020X0WJ\u000e\u0010Y\u001a\u00020K2\u0006\u0010Z\u001a\u00020,R\u0011\u0010\u0015\u001a\u00020\u0016¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u000e\u0010\u001d\u001a\u00020\u001eX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020 X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010!\u001a\u00020\"¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u001a\u0010+\u001a\u00020,X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b1\u00102R\u001a\u00103\u001a\u00020,X.¢\u0006\u000e\n\u0000\u001a\u0004\b4\u0010.\"\u0004\b5\u00100R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b6\u00107R\u0011\u0010\u0017\u001a\u00020\u0018¢\u0006\b\n\u0000\u001a\u0004\b8\u00109R\u0011\u0010\u0013\u001a\u00020\u0014¢\u0006\b\n\u0000\u001a\u0004\b:\u0010;R\u001a\u0010<\u001a\u00020=X.¢\u0006\u000e\n\u0000\u001a\u0004\b>\u0010?\"\u0004\b@\u0010AR\u001a\u0010B\u001a\u00020CX.¢\u0006\u000e\n\u0000\u001a\u0004\bD\u0010E\"\u0004\bF\u0010GR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\bH\u0010I¨\u0006["}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/walletdetails/txdetails/TxDetailsPresenter;", "Lcom/bitcoin/mwallet/app/viper/ScreenPresenter;", "Lcom/bitcoin/mwallet/app/flows/walletdetails/txdetails/TxDetailsRouter;", "context", "Landroid/content/Context;", "viewModelScope", "Lkotlinx/coroutines/CoroutineScope;", "txRepo", "Lcom/bitcoin/mwallet/core/repositories/TransactionHistoryRepository;", "walletsInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetWalletInteractor;", "defaultCurrencyInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetDefaultCurrencyInteractor;", "exchangeRateInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetCurrentExchangeRateInteractor;", "notesRepository", "Lcom/bitcoin/mwallet/core/repositories/NotesRepository;", "slpRepository", "Lcom/bitcoin/mwallet/core/repositories/SlpRepository;", "verifiedTokenRepository", "Lcom/bitcoin/mwallet/core/repositories/VerifiedTokenRepository;", "analyticsService", "Lcom/bitcoin/mwallet/core/services/AnalyticsService;", "verifiedTokenInteractor", "Lcom/bitcoin/mwallet/core/interactors/VerifiedTokenInteractor;", "router", "(Landroid/content/Context;Lkotlinx/coroutines/CoroutineScope;Lcom/bitcoin/mwallet/core/repositories/TransactionHistoryRepository;Lcom/bitcoin/mwallet/core/interactors/GetWalletInteractor;Lcom/bitcoin/mwallet/core/interactors/GetDefaultCurrencyInteractor;Lcom/bitcoin/mwallet/core/interactors/GetCurrentExchangeRateInteractor;Lcom/bitcoin/mwallet/core/repositories/NotesRepository;Lcom/bitcoin/mwallet/core/repositories/SlpRepository;Lcom/bitcoin/mwallet/core/repositories/VerifiedTokenRepository;Lcom/bitcoin/mwallet/core/services/AnalyticsService;Lcom/bitcoin/mwallet/core/interactors/VerifiedTokenInteractor;Lcom/bitcoin/mwallet/app/flows/walletdetails/txdetails/TxDetailsRouter;)V", "getAnalyticsService", "()Lcom/bitcoin/mwallet/core/services/AnalyticsService;", "clipboard", "Landroid/content/ClipboardManager;", "copyToast", "Landroid/widget/Toast;", "defaultCurrency", "Ljava/util/Currency;", "getDefaultCurrency", "()Ljava/util/Currency;", "getDefaultCurrencyInteractor", "()Lcom/bitcoin/mwallet/core/interactors/GetDefaultCurrencyInteractor;", "getExchangeRateInteractor", "()Lcom/bitcoin/mwallet/core/interactors/GetCurrentExchangeRateInteractor;", "getNotesRepository", "()Lcom/bitcoin/mwallet/core/repositories/NotesRepository;", "originalNoteText", "", "getOriginalNoteText", "()Ljava/lang/String;", "setOriginalNoteText", "(Ljava/lang/String;)V", "getSlpRepository", "()Lcom/bitcoin/mwallet/core/repositories/SlpRepository;", "txId", "getTxId", "setTxId", "getTxRepo", "()Lcom/bitcoin/mwallet/core/repositories/TransactionHistoryRepository;", "getVerifiedTokenInteractor", "()Lcom/bitcoin/mwallet/core/interactors/VerifiedTokenInteractor;", "getVerifiedTokenRepository", "()Lcom/bitcoin/mwallet/core/repositories/VerifiedTokenRepository;", "wallet", "Lcom/bitcoin/mwallet/core/models/wallet/Wallet;", "getWallet", "()Lcom/bitcoin/mwallet/core/models/wallet/Wallet;", "setWallet", "(Lcom/bitcoin/mwallet/core/models/wallet/Wallet;)V", "walletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "getWalletId", "()Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "setWalletId", "(Lcom/bitcoin/mwallet/core/models/wallet/WalletId;)V", "getWalletsInteractor", "()Lcom/bitcoin/mwallet/core/interactors/GetWalletInteractor;", "copyAddress", "", "userVisibleLabel", "address", "getFiatBalance", "Lcom/bitcoin/mwallet/core/models/exchangerate/FiatExchangeRate;", "coin", "Lcom/bitcoin/mwallet/core/models/Coin;", "getTransactionDetails", "Landroidx/lifecycle/LiveData;", "Lkotlin/Pair;", "Lcom/bitcoin/mwallet/core/models/tx/history/HistoricTransaction;", "getVerifiedToken", "", "Lcom/bitcoin/mwallet/core/models/verifiedtoken/VerifiedToken;", "setNoteDetails", "note", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: TxDetailsPresenter.kt */
public final class TxDetailsPresenter extends ScreenPresenter<TxDetailsRouter> {
    @NotNull
    private final AnalyticsService analyticsService;
    private final ClipboardManager clipboard;
    private final Toast copyToast;
    @NotNull
    private final Currency defaultCurrency = this.defaultCurrencyInteractor.getDefaultFiatCurrency();
    @NotNull
    private final GetDefaultCurrencyInteractor defaultCurrencyInteractor;
    @NotNull
    private final GetCurrentExchangeRateInteractor exchangeRateInteractor;
    @NotNull
    private final NotesRepository notesRepository;
    @NotNull
    private String originalNoteText = "";
    @NotNull
    private final SlpRepository slpRepository;
    @NotNull
    public String txId;
    @NotNull
    private final TransactionHistoryRepository txRepo;
    @NotNull
    private final VerifiedTokenInteractor verifiedTokenInteractor;
    @NotNull
    private final VerifiedTokenRepository verifiedTokenRepository;
    @NotNull
    public C1261Wallet wallet;
    @NotNull
    public WalletId walletId;
    @NotNull
    private final GetWalletInteractor walletsInteractor;

    @NotNull
    public final TransactionHistoryRepository getTxRepo() {
        return this.txRepo;
    }

    @NotNull
    public final GetWalletInteractor getWalletsInteractor() {
        return this.walletsInteractor;
    }

    @NotNull
    public final GetDefaultCurrencyInteractor getDefaultCurrencyInteractor() {
        return this.defaultCurrencyInteractor;
    }

    @NotNull
    public final GetCurrentExchangeRateInteractor getExchangeRateInteractor() {
        return this.exchangeRateInteractor;
    }

    @NotNull
    public final NotesRepository getNotesRepository() {
        return this.notesRepository;
    }

    @NotNull
    public final SlpRepository getSlpRepository() {
        return this.slpRepository;
    }

    @NotNull
    public final VerifiedTokenRepository getVerifiedTokenRepository() {
        return this.verifiedTokenRepository;
    }

    @NotNull
    public final AnalyticsService getAnalyticsService() {
        return this.analyticsService;
    }

    @NotNull
    public final VerifiedTokenInteractor getVerifiedTokenInteractor() {
        return this.verifiedTokenInteractor;
    }

    public TxDetailsPresenter(@NotNull Context context, @NotNull CoroutineScope coroutineScope, @NotNull TransactionHistoryRepository transactionHistoryRepository, @NotNull GetWalletInteractor getWalletInteractor, @NotNull GetDefaultCurrencyInteractor getDefaultCurrencyInteractor, @NotNull GetCurrentExchangeRateInteractor getCurrentExchangeRateInteractor, @NotNull NotesRepository notesRepository2, @NotNull SlpRepository slpRepository2, @NotNull VerifiedTokenRepository verifiedTokenRepository2, @NotNull AnalyticsService analyticsService2, @NotNull VerifiedTokenInteractor verifiedTokenInteractor2, @NotNull TxDetailsRouter txDetailsRouter) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(coroutineScope, "viewModelScope");
        Intrinsics.checkParameterIsNotNull(transactionHistoryRepository, "txRepo");
        Intrinsics.checkParameterIsNotNull(getWalletInteractor, "walletsInteractor");
        Intrinsics.checkParameterIsNotNull(getDefaultCurrencyInteractor, "defaultCurrencyInteractor");
        Intrinsics.checkParameterIsNotNull(getCurrentExchangeRateInteractor, "exchangeRateInteractor");
        Intrinsics.checkParameterIsNotNull(notesRepository2, "notesRepository");
        Intrinsics.checkParameterIsNotNull(slpRepository2, "slpRepository");
        Intrinsics.checkParameterIsNotNull(verifiedTokenRepository2, "verifiedTokenRepository");
        Intrinsics.checkParameterIsNotNull(analyticsService2, "analyticsService");
        Intrinsics.checkParameterIsNotNull(verifiedTokenInteractor2, "verifiedTokenInteractor");
        Intrinsics.checkParameterIsNotNull(txDetailsRouter, "router");
        super(context, txDetailsRouter);
        this.txRepo = transactionHistoryRepository;
        this.walletsInteractor = getWalletInteractor;
        this.defaultCurrencyInteractor = getDefaultCurrencyInteractor;
        this.exchangeRateInteractor = getCurrentExchangeRateInteractor;
        this.notesRepository = notesRepository2;
        this.slpRepository = slpRepository2;
        this.verifiedTokenRepository = verifiedTokenRepository2;
        this.analyticsService = analyticsService2;
        this.verifiedTokenInteractor = verifiedTokenInteractor2;
        Object systemService = context.getSystemService("clipboard");
        if (systemService != null) {
            this.clipboard = (ClipboardManager) systemService;
            Toast makeText = Toast.makeText(context, context.getString(C1018R.string.receive_address_copied), 0);
            Intrinsics.checkExpressionValueIsNotNull(makeText, "Toast.makeText(context, …ied), Toast.LENGTH_SHORT)");
            this.copyToast = makeText;
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.content.ClipboardManager");
    }

    @NotNull
    public final String getTxId() {
        String str = this.txId;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("txId");
        }
        return str;
    }

    public final void setTxId(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.txId = str;
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

    @NotNull
    public final C1261Wallet getWallet() {
        C1261Wallet wallet2 = this.wallet;
        if (wallet2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("wallet");
        }
        return wallet2;
    }

    public final void setWallet(@NotNull C1261Wallet wallet2) {
        Intrinsics.checkParameterIsNotNull(wallet2, "<set-?>");
        this.wallet = wallet2;
    }

    @NotNull
    public final String getOriginalNoteText() {
        return this.originalNoteText;
    }

    public final void setOriginalNoteText(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.originalNoteText = str;
    }

    @NotNull
    public final Currency getDefaultCurrency() {
        return this.defaultCurrency;
    }

    @NotNull
    public final LiveData<Pair<HistoricTransaction, C1261Wallet>> getTransactionDetails() {
        TransactionHistoryRepository transactionHistoryRepository = this.txRepo;
        String str = this.txId;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("txId");
        }
        TxId txId2 = new TxId(str);
        WalletId walletId2 = this.walletId;
        if (walletId2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("walletId");
        }
        return LiveDataKt.combineLatestIgnoreNull(transactionHistoryRepository.getTransactionById(txId2, walletId2), this.walletsInteractor.getWallets(), new TxDetailsPresenter$getTransactionDetails$1(this));
    }

    @NotNull
    public final List<VerifiedToken> getVerifiedToken() {
        return (List) BuildersKt__BuildersKt.runBlocking$default(null, new TxDetailsPresenter$getVerifiedToken$1(this, null), 1, null);
    }

    public final void setNoteDetails(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "note");
        this.analyticsService.track("transfer_adds_memo", MapsKt.emptyMap());
        BuildersKt__BuildersKt.runBlocking$default(null, new TxDetailsPresenter$setNoteDetails$1(this, str, null), 1, null);
    }

    @NotNull
    public final FiatExchangeRate getFiatBalance(@NotNull Coin coin) {
        Intrinsics.checkParameterIsNotNull(coin, "coin");
        return (FiatExchangeRate) BuildersKt__BuildersKt.runBlocking$default(null, new TxDetailsPresenter$getFiatBalance$1(this, coin, null), 1, null);
    }

    public final void copyAddress(@NotNull String str, @NotNull String str2) {
        Intrinsics.checkParameterIsNotNull(str, "userVisibleLabel");
        Intrinsics.checkParameterIsNotNull(str2, "address");
        this.clipboard.setPrimaryClip(ClipData.newPlainText(str, str2));
        this.copyToast.show();
    }
}
