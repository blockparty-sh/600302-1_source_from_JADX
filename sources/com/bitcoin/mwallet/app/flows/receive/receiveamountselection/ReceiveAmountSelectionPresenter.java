package com.bitcoin.mwallet.app.flows.receive.receiveamountselection;

import android.content.Context;
import com.bitcoin.mwallet.app.components.amountselection.AmountSelectionPresenterBase;
import com.bitcoin.mwallet.app.components.amountselection.AmountSelectionPresenterBase.EntryType;
import com.bitcoin.mwallet.app.flows.receive.ReceiveGlobalViewModel;
import com.bitcoin.mwallet.app.flows.receive.receive.entity.ReceiveAmount;
import com.bitcoin.mwallet.core.interactors.GetCurrentExchangeRateInteractor;
import com.bitcoin.mwallet.core.interactors.GetDefaultCurrencyInteractor;
import com.bitcoin.mwallet.core.models.Coin;
import java.math.BigDecimal;
import java.util.Currency;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\u0010\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010J\u000e\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u0013R\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/receive/receiveamountselection/ReceiveAmountSelectionPresenter;", "Lcom/bitcoin/mwallet/app/components/amountselection/AmountSelectionPresenterBase;", "context", "Landroid/content/Context;", "viewModelScope", "Lkotlinx/coroutines/CoroutineScope;", "getDefaultCurrencyInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetDefaultCurrencyInteractor;", "getCurrentExchangeRateInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetCurrentExchangeRateInteractor;", "receiveRouter", "Lcom/bitcoin/mwallet/app/flows/receive/receiveamountselection/ReceiveAmountSelectionRouter;", "(Landroid/content/Context;Lkotlinx/coroutines/CoroutineScope;Lcom/bitcoin/mwallet/core/interactors/GetDefaultCurrencyInteractor;Lcom/bitcoin/mwallet/core/interactors/GetCurrentExchangeRateInteractor;Lcom/bitcoin/mwallet/app/flows/receive/receiveamountselection/ReceiveAmountSelectionRouter;)V", "init", "", "coin", "Lcom/bitcoin/mwallet/core/models/Coin;", "onContinueTapped", "globalViewModel", "Lcom/bitcoin/mwallet/app/flows/receive/ReceiveGlobalViewModel;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: ReceiveAmountSelectionPresenter.kt */
public final class ReceiveAmountSelectionPresenter extends AmountSelectionPresenterBase {
    private final ReceiveAmountSelectionRouter receiveRouter;

    public ReceiveAmountSelectionPresenter(@NotNull Context context, @NotNull CoroutineScope coroutineScope, @NotNull GetDefaultCurrencyInteractor getDefaultCurrencyInteractor, @NotNull GetCurrentExchangeRateInteractor getCurrentExchangeRateInteractor, @NotNull ReceiveAmountSelectionRouter receiveAmountSelectionRouter) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(coroutineScope, "viewModelScope");
        Intrinsics.checkParameterIsNotNull(getDefaultCurrencyInteractor, "getDefaultCurrencyInteractor");
        Intrinsics.checkParameterIsNotNull(getCurrentExchangeRateInteractor, "getCurrentExchangeRateInteractor");
        Intrinsics.checkParameterIsNotNull(receiveAmountSelectionRouter, "receiveRouter");
        super(context, coroutineScope, getDefaultCurrencyInteractor, getCurrentExchangeRateInteractor, receiveAmountSelectionRouter);
        this.receiveRouter = receiveAmountSelectionRouter;
    }

    public final void onContinueTapped(@NotNull ReceiveGlobalViewModel receiveGlobalViewModel) {
        Intrinsics.checkParameterIsNotNull(receiveGlobalViewModel, "globalViewModel");
        boolean z = ((EntryType) getPrimaryEntryType().getValue()) == EntryType.COIN;
        BigDecimal cryptoAmount = getCryptoAmount();
        String ticker = getCoin().getTicker();
        BigDecimal fiatAmount = getFiatAmount();
        Object value = getDisplayCurrency().getValue();
        if (value == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(value, "displayCurrency.value!!");
        ReceiveAmount receiveAmount = new ReceiveAmount(z, ticker, cryptoAmount, fiatAmount, (Currency) value);
        receiveGlobalViewModel.setAmountValue(receiveAmount);
        this.receiveRouter.backToQrCode();
    }

    public final void init(@Nullable Coin coin) {
        if (coin != null) {
            setCoin(coin);
            getPrimaryEntryType().postValue(EntryType.FIAT);
        }
    }
}
