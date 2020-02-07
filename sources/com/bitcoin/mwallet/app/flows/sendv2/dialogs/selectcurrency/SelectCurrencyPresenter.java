package com.bitcoin.mwallet.app.flows.sendv2.dialogs.selectcurrency;

import android.content.Context;
import com.bitcoin.mwallet.app.viper.ScreenPresenter;
import com.bitcoin.mwallet.core.interactors.GetCurrentExchangeRateInteractor;
import com.bitcoin.mwallet.core.repositories.ExchangeRateRepository;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs;
import java.util.Currency;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B-\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\u0002¢\u0006\u0002\u0010\fJ\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014J\u000e\u0010\u0016\u001a\u00020\u00172\u0006\u0010\r\u001a\u00020\u000eR\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\r\u001a\u0004\u0018\u00010\u000eX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012¨\u0006\u0018"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/selectcurrency/SelectCurrencyPresenter;", "Lcom/bitcoin/mwallet/app/viper/ScreenPresenter;", "Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/selectcurrency/SelectCurrencyRouter;", "context", "Landroid/content/Context;", "viewModelScope", "Lkotlinx/coroutines/CoroutineScope;", "exchangeRateInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetCurrentExchangeRateInteractor;", "exchangeRateRepository", "Lcom/bitcoin/mwallet/core/repositories/ExchangeRateRepository;", "router", "(Landroid/content/Context;Lkotlinx/coroutines/CoroutineScope;Lcom/bitcoin/mwallet/core/interactors/GetCurrentExchangeRateInteractor;Lcom/bitcoin/mwallet/core/repositories/ExchangeRateRepository;Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/selectcurrency/SelectCurrencyRouter;)V", "listener", "Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/selectcurrency/OnCurrencySelectedListener;", "getListener", "()Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/selectcurrency/OnCurrencySelectedListener;", "setListener", "(Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/selectcurrency/OnCurrencySelectedListener;)V", "getCurrencies", "", "Ljava/util/Currency;", "setOnCurrencySelectedListener", "", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: SelectCurrencyPresenter.kt */
public final class SelectCurrencyPresenter extends ScreenPresenter<SelectCurrencyRouter> {
    /* access modifiers changed from: private */
    public final GetCurrentExchangeRateInteractor exchangeRateInteractor;
    private final ExchangeRateRepository exchangeRateRepository;
    @Nullable
    private OnCurrencySelectedListener listener;

    public SelectCurrencyPresenter(@NotNull Context context, @NotNull CoroutineScope coroutineScope, @NotNull GetCurrentExchangeRateInteractor getCurrentExchangeRateInteractor, @NotNull ExchangeRateRepository exchangeRateRepository2, @NotNull SelectCurrencyRouter selectCurrencyRouter) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(coroutineScope, "viewModelScope");
        Intrinsics.checkParameterIsNotNull(getCurrentExchangeRateInteractor, "exchangeRateInteractor");
        Intrinsics.checkParameterIsNotNull(exchangeRateRepository2, "exchangeRateRepository");
        Intrinsics.checkParameterIsNotNull(selectCurrencyRouter, "router");
        super(context, selectCurrencyRouter);
        this.exchangeRateInteractor = getCurrentExchangeRateInteractor;
        this.exchangeRateRepository = exchangeRateRepository2;
    }

    @Nullable
    public final OnCurrencySelectedListener getListener() {
        return this.listener;
    }

    public final void setListener(@Nullable OnCurrencySelectedListener onCurrencySelectedListener) {
        this.listener = onCurrencySelectedListener;
    }

    public final void setOnCurrencySelectedListener(@NotNull OnCurrencySelectedListener onCurrencySelectedListener) {
        Intrinsics.checkParameterIsNotNull(onCurrencySelectedListener, CastExtraArgs.LISTENER);
        this.listener = onCurrencySelectedListener;
    }

    @NotNull
    public final List<Currency> getCurrencies() {
        return (List) BuildersKt__BuildersKt.runBlocking$default(null, new SelectCurrencyPresenter$getCurrencies$1(this, null), 1, null);
    }
}
