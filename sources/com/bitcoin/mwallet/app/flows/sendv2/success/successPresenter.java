package com.bitcoin.mwallet.app.flows.sendv2.success;

import android.app.Activity;
import android.content.Context;
import com.bitcoin.bitcoink.p008tx.Satoshis;
import com.bitcoin.bitcoink.p008tx.TxId;
import com.bitcoin.mwallet.app.flows.sendv2.sendamountselection.SendWhatModel;
import com.bitcoin.mwallet.app.viper.PresenterFinishOnBackHandler;
import com.bitcoin.mwallet.app.viper.PresenterWithFinishOnBackHandler;
import com.bitcoin.mwallet.app.viper.ScreenPresenter;
import com.bitcoin.mwallet.core.interactors.GetCurrentExchangeRateInteractor;
import com.bitcoin.mwallet.core.models.exchangerate.FiatExchangeRate;
import com.bitcoin.mwallet.core.models.exchangerate.FiatExchangeRateKt;
import com.bitcoin.mwallet.core.models.slp.Slp;
import com.bitcoin.mwallet.core.models.slp.SlpId;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import com.bitcoin.mwallet.core.repositories.NotesRepository;
import com.bitcoin.mwallet.core.repositories.SlpRepository;
import com.bitcoin.mwallet.core.repositories.WalletRepository;
import com.bitcoin.mwallet.core.services.AnalyticsService;
import java.math.BigDecimal;
import java.util.Currency;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.GlobalScope;
import org.bitcoinj.uri.BitcoinURI;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003BE\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0002¢\u0006\u0002\u0010\u0013J\u000e\u0010;\u001a\u00020<2\u0006\u0010=\u001a\u00020>J\u000e\u0010?\u001a\u00020<2\u0006\u0010@\u001a\u00020AJ\u000e\u0010B\u001a\u00020<2\u0006\u0010C\u001a\u00020\u0019R\u000e\u0010\u0010\u001a\u00020\u0011X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\u00020\u0015X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0018\u001a\u00020\u0019X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u001a\u0010 \u001a\u00020\u0019X.¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u001b\"\u0004\b\"\u0010\u001dR\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u001a\u0010%\u001a\u00020&X.¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b+\u0010,R\u001a\u0010-\u001a\u00020.X.¢\u0006\u000e\n\u0000\u001a\u0004\b/\u00100\"\u0004\b1\u00102R\u001a\u00103\u001a\u000204X.¢\u0006\u000e\n\u0000\u001a\u0004\b5\u00106\"\u0004\b7\u00108R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b9\u0010:¨\u0006D"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/sendv2/success/successPresenter;", "Lcom/bitcoin/mwallet/app/viper/ScreenPresenter;", "Lcom/bitcoin/mwallet/app/flows/sendv2/success/successRouter;", "Lcom/bitcoin/mwallet/app/viper/PresenterWithFinishOnBackHandler;", "context", "Landroid/content/Context;", "viewModelScope", "Lkotlinx/coroutines/CoroutineScope;", "getCurrentExchangeRateInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetCurrentExchangeRateInteractor;", "slpRepository", "Lcom/bitcoin/mwallet/core/repositories/SlpRepository;", "walletRepository", "Lcom/bitcoin/mwallet/core/repositories/WalletRepository;", "notesRepository", "Lcom/bitcoin/mwallet/core/repositories/NotesRepository;", "analyticsService", "Lcom/bitcoin/mwallet/core/services/AnalyticsService;", "router", "(Landroid/content/Context;Lkotlinx/coroutines/CoroutineScope;Lcom/bitcoin/mwallet/core/interactors/GetCurrentExchangeRateInteractor;Lcom/bitcoin/mwallet/core/repositories/SlpRepository;Lcom/bitcoin/mwallet/core/repositories/WalletRepository;Lcom/bitcoin/mwallet/core/repositories/NotesRepository;Lcom/bitcoin/mwallet/core/services/AnalyticsService;Lcom/bitcoin/mwallet/app/flows/sendv2/success/successRouter;)V", "backHandler", "Lcom/bitcoin/mwallet/app/viper/PresenterFinishOnBackHandler;", "getBackHandler", "()Lcom/bitcoin/mwallet/app/viper/PresenterFinishOnBackHandler;", "displayAmount", "", "getDisplayAmount", "()Ljava/lang/String;", "setDisplayAmount", "(Ljava/lang/String;)V", "getGetCurrentExchangeRateInteractor", "()Lcom/bitcoin/mwallet/core/interactors/GetCurrentExchangeRateInteractor;", "memo", "getMemo", "setMemo", "getNotesRepository", "()Lcom/bitcoin/mwallet/core/repositories/NotesRepository;", "sendWhatData", "Lcom/bitcoin/mwallet/app/flows/sendv2/sendamountselection/SendWhatModel;", "getSendWhatData", "()Lcom/bitcoin/mwallet/app/flows/sendv2/sendamountselection/SendWhatModel;", "setSendWhatData", "(Lcom/bitcoin/mwallet/app/flows/sendv2/sendamountselection/SendWhatModel;)V", "getSlpRepository", "()Lcom/bitcoin/mwallet/core/repositories/SlpRepository;", "txId", "Lcom/bitcoin/bitcoink/tx/TxId;", "getTxId", "()Lcom/bitcoin/bitcoink/tx/TxId;", "setTxId", "(Lcom/bitcoin/bitcoink/tx/TxId;)V", "walletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "getWalletId", "()Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "setWalletId", "(Lcom/bitcoin/mwallet/core/models/wallet/WalletId;)V", "getWalletRepository", "()Lcom/bitcoin/mwallet/core/repositories/WalletRepository;", "endFlow", "", "activity", "Landroid/app/Activity;", "setInitialArgs", "args", "Lcom/bitcoin/mwallet/app/flows/sendv2/success/successViewArgs;", "setNoteDetails", "note", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: successPresenter.kt */
public final class successPresenter extends ScreenPresenter<successRouter> implements PresenterWithFinishOnBackHandler {
    private final AnalyticsService analyticsService;
    @NotNull
    private final PresenterFinishOnBackHandler backHandler = new PresenterFinishOnBackHandler();
    @NotNull
    private String displayAmount = "";
    @NotNull
    private final GetCurrentExchangeRateInteractor getCurrentExchangeRateInteractor;
    @NotNull
    public String memo;
    @NotNull
    private final NotesRepository notesRepository;
    @NotNull
    public SendWhatModel sendWhatData;
    @NotNull
    private final SlpRepository slpRepository;
    @NotNull
    public TxId txId;
    @NotNull
    public WalletId walletId;
    @NotNull
    private final WalletRepository walletRepository;

    @NotNull
    public final GetCurrentExchangeRateInteractor getGetCurrentExchangeRateInteractor() {
        return this.getCurrentExchangeRateInteractor;
    }

    @NotNull
    public final SlpRepository getSlpRepository() {
        return this.slpRepository;
    }

    @NotNull
    public final WalletRepository getWalletRepository() {
        return this.walletRepository;
    }

    @NotNull
    public final NotesRepository getNotesRepository() {
        return this.notesRepository;
    }

    public successPresenter(@NotNull Context context, @NotNull CoroutineScope coroutineScope, @NotNull GetCurrentExchangeRateInteractor getCurrentExchangeRateInteractor2, @NotNull SlpRepository slpRepository2, @NotNull WalletRepository walletRepository2, @NotNull NotesRepository notesRepository2, @NotNull AnalyticsService analyticsService2, @NotNull successRouter successrouter) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(coroutineScope, "viewModelScope");
        Intrinsics.checkParameterIsNotNull(getCurrentExchangeRateInteractor2, "getCurrentExchangeRateInteractor");
        Intrinsics.checkParameterIsNotNull(slpRepository2, "slpRepository");
        Intrinsics.checkParameterIsNotNull(walletRepository2, "walletRepository");
        Intrinsics.checkParameterIsNotNull(notesRepository2, "notesRepository");
        Intrinsics.checkParameterIsNotNull(analyticsService2, "analyticsService");
        Intrinsics.checkParameterIsNotNull(successrouter, "router");
        super(context, successrouter);
        this.getCurrentExchangeRateInteractor = getCurrentExchangeRateInteractor2;
        this.slpRepository = slpRepository2;
        this.walletRepository = walletRepository2;
        this.notesRepository = notesRepository2;
        this.analyticsService = analyticsService2;
    }

    @NotNull
    public final TxId getTxId() {
        TxId txId2 = this.txId;
        if (txId2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("txId");
        }
        return txId2;
    }

    public final void setTxId(@NotNull TxId txId2) {
        Intrinsics.checkParameterIsNotNull(txId2, "<set-?>");
        this.txId = txId2;
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
    public final SendWhatModel getSendWhatData() {
        SendWhatModel sendWhatModel = this.sendWhatData;
        if (sendWhatModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sendWhatData");
        }
        return sendWhatModel;
    }

    public final void setSendWhatData(@NotNull SendWhatModel sendWhatModel) {
        Intrinsics.checkParameterIsNotNull(sendWhatModel, "<set-?>");
        this.sendWhatData = sendWhatModel;
    }

    @NotNull
    public final String getMemo() {
        String str = this.memo;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("memo");
        }
        return str;
    }

    public final void setMemo(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.memo = str;
    }

    @NotNull
    public PresenterFinishOnBackHandler getBackHandler() {
        return this.backHandler;
    }

    @NotNull
    public final String getDisplayAmount() {
        return this.displayAmount;
    }

    public final void setDisplayAmount(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.displayAmount = str;
    }

    public final void endFlow(@NotNull Activity activity) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        ((successRouter) getRouter()).endFlow(activity);
    }

    public final void setInitialArgs(@NotNull successViewArgs successviewargs) {
        String str;
        Intrinsics.checkParameterIsNotNull(successviewargs, "args");
        this.txId = successviewargs.getTxid();
        this.walletId = successviewargs.getSendWhatData().getWallet();
        this.sendWhatData = successviewargs.getSendWhatData();
        this.memo = successviewargs.getSendWhatData().getMemo();
        SendWhatModel sendWhatModel = this.sendWhatData;
        String str2 = "sendWhatData";
        if (sendWhatModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException(str2);
        }
        SlpId tokenId = sendWhatModel.getUri().getTokenId();
        SendWhatModel sendWhatModel2 = this.sendWhatData;
        if (sendWhatModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(str2);
        }
        BigDecimal cryptoAmount = sendWhatModel2.getCryptoAmount();
        SendWhatModel sendWhatModel3 = this.sendWhatData;
        if (sendWhatModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(str2);
        }
        String str3 = null;
        if (sendWhatModel3.getTokenAmount() != null && tokenId != null) {
            Slp slp = (Slp) BuildersKt__BuildersKt.runBlocking$default(null, new successPresenter$setInitialArgs$tokenInfo$1(this, tokenId, null), 1, null);
            StringBuilder sb = new StringBuilder();
            SendWhatModel sendWhatModel4 = this.sendWhatData;
            if (sendWhatModel4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(str2);
            }
            sb.append(sendWhatModel4.getTokenAmount());
            sb.append(' ');
            if (slp != null) {
                str3 = slp.getTicker();
            }
            sb.append(str3);
            this.displayAmount = sb.toString();
        } else if (cryptoAmount != null) {
            SendWhatModel sendWhatModel5 = this.sendWhatData;
            if (sendWhatModel5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(str2);
            }
            this.displayAmount = FiatExchangeRateKt.toFiatString((FiatExchangeRate) BuildersKt__BuildersKt.runBlocking$default(null, new successPresenter$setInitialArgs$exchangeRate$1(this, Currency.getInstance(sendWhatModel5.getCurrency()), null), 1, null), Satoshis.Companion.fromCoins(cryptoAmount));
        }
        if (tokenId != null) {
            SendWhatModel sendWhatModel6 = this.sendWhatData;
            if (sendWhatModel6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(str2);
            }
            cryptoAmount = sendWhatModel6.getTokenAmount();
            str = "SLP";
        } else {
            SendWhatModel sendWhatModel7 = this.sendWhatData;
            if (sendWhatModel7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(str2);
            }
            str = sendWhatModel7.getCoin().getTicker();
        }
        if (cryptoAmount == null) {
            cryptoAmount = BigDecimal.ZERO;
        }
        String str4 = this.memo;
        String str5 = "memo";
        if (str4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(str5);
        }
        if (str4.length() > 0) {
            String str6 = this.memo;
            if (str6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(str5);
            }
            setNoteDetails(str6);
        }
        this.analyticsService.track("transfer_success", MapsKt.mapOf(TuplesKt.m309to("coin", str), TuplesKt.m309to("type", "outgoing"), TuplesKt.m309to(BitcoinURI.FIELD_AMOUNT, cryptoAmount)));
    }

    public final void setNoteDetails(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "note");
        this.analyticsService.track("transfer_adds_memo", MapsKt.emptyMap());
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new successPresenter$setNoteDetails$1(this, str, null), 3, null);
    }
}
