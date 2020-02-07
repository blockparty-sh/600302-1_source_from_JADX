package com.bitcoin.mwallet.core.interactors;

import com.bitcoin.mwallet.app.android.ListenableLiveData;
import com.bitcoin.mwallet.app.android.LiveDataChangeListener;
import com.bitcoin.mwallet.app.android.SharedPreferenceStringLiveData;
import com.bitcoin.mwallet.core.Constants;
import com.bitcoin.mwallet.core.extensions.StringKt;
import com.bitcoin.mwallet.core.preferences.ApplicationPreferences;
import com.bitcoin.mwallet.core.preferences.SharedPreference;
import java.util.Currency;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001a\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\tJ\u0006\u0010\n\u001a\u00020\u0007R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/interactors/GetDefaultCurrencyInteractor;", "", "sharedPreferences", "Lcom/bitcoin/mwallet/core/preferences/ApplicationPreferences;", "(Lcom/bitcoin/mwallet/core/preferences/ApplicationPreferences;)V", "defaultFiatCurrency", "Lcom/bitcoin/mwallet/app/android/ListenableLiveData;", "Ljava/util/Currency;", "changeListener", "Lcom/bitcoin/mwallet/app/android/LiveDataChangeListener;", "getDefaultFiatCurrency", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: GetDefaultCurrencyInteractor.kt */
public class GetDefaultCurrencyInteractor {
    private final ApplicationPreferences sharedPreferences;

    public GetDefaultCurrencyInteractor(@NotNull ApplicationPreferences applicationPreferences) {
        Intrinsics.checkParameterIsNotNull(applicationPreferences, "sharedPreferences");
        this.sharedPreferences = applicationPreferences;
    }

    @NotNull
    public final ListenableLiveData<Currency> defaultFiatCurrency(@NotNull LiveDataChangeListener<Currency> liveDataChangeListener) {
        Intrinsics.checkParameterIsNotNull(liveDataChangeListener, "changeListener");
        ListenableLiveData<Currency> listenableLiveData = new ListenableLiveData<>(liveDataChangeListener);
        listenableLiveData.addSource(new SharedPreferenceStringLiveData(this.sharedPreferences, SharedPreference.FIAT_CURRENCY), new GetDefaultCurrencyInteractor$defaultFiatCurrency$1(listenableLiveData));
        return listenableLiveData;
    }

    @NotNull
    public final Currency getDefaultFiatCurrency() {
        Currency tryParseCurrency = StringKt.tryParseCurrency(this.sharedPreferences.getString(SharedPreference.FIAT_CURRENCY.getKey(), null));
        return tryParseCurrency != null ? tryParseCurrency : Constants.INSTANCE.getDefaultCurrency();
    }
}
