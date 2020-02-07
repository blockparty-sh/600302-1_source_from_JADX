package com.bitcoin.mwallet.app.components.amountselection;

import android.app.Activity;
import android.content.Context;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.MutableLiveData;
import com.bitcoin.mwallet.app.flows.sendv2.dialogs.selectcurrency.OnCurrencySelectedListener;
import com.bitcoin.mwallet.app.viper.ScreenPresenter;
import com.bitcoin.mwallet.core.extensions.BigDecimalKt;
import com.bitcoin.mwallet.core.interactors.GetCurrentExchangeRateInteractor;
import com.bitcoin.mwallet.core.interactors.GetDefaultCurrencyInteractor;
import com.bitcoin.mwallet.core.models.Coin;
import com.leanplum.core.BuildConfig;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Currency;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0016\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u0001gB-\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\u0002¢\u0006\u0002\u0010\rJ\b\u0010L\u001a\u00020MH\u0002J\u000e\u0010N\u001a\u0002082\u0006\u0010O\u001a\u00020&J\u000e\u0010P\u001a\u00020&2\u0006\u0010O\u001a\u000208J\u0006\u0010Q\u001a\u00020RJ\u0006\u0010S\u001a\u00020RJ\u000e\u0010T\u001a\u00020R2\u0006\u0010U\u001a\u000208J\u000e\u0010V\u001a\u0002082\u0006\u0010W\u001a\u000208J\b\u0010X\u001a\u00020\u000fH\u0002J\b\u0010Y\u001a\u00020\u000fH\u0002J\b\u0010Z\u001a\u00020\u000fH\u0002J\u0006\u0010[\u001a\u00020RJ\u000e\u0010\\\u001a\u00020R2\u0006\u0010]\u001a\u00020^J\u000e\u0010_\u001a\u00020R2\u0006\u0010`\u001a\u00020aJ\u0010\u0010b\u001a\u00020R2\u0006\u0010\u001f\u001a\u00020-H\u0016J\u0006\u0010c\u001a\u00020RJ\u0006\u0010d\u001a\u00020RJ\u0006\u0010e\u001a\u00020RJ\u0006\u0010f\u001a\u00020RR\u0014\u0010\u000e\u001a\u00020\u000fXD¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001a\u00020\u000fXD¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u0014\u0010\u0014\u001a\u00020\u000fXD¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0011R\u0014\u0010\u0016\u001a\u00020\u000fXD¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0011R\u001a\u0010\u0018\u001a\u00020\u000fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0011\"\u0004\b\u001a\u0010\u001bR\u001a\u0010\u001c\u001a\u00020\u000fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0011\"\u0004\b\u001e\u0010\u001bR\u001a\u0010\u001f\u001a\u00020 X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u001c\u0010%\u001a\u0004\u0018\u00010&X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R \u0010+\u001a\b\u0012\u0004\u0012\u00020-0,X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010/\"\u0004\b0\u00101R\u001c\u00102\u001a\u0004\u0018\u00010&X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u0010(\"\u0004\b4\u0010*R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b5\u00106R \u00107\u001a\b\u0012\u0004\u0012\u0002080,X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b9\u0010/\"\u0004\b:\u00101R\u0017\u0010;\u001a\b\u0012\u0004\u0012\u00020<0,¢\u0006\b\n\u0000\u001a\u0004\b=\u0010/R \u0010>\u001a\b\u0012\u0004\u0012\u0002080,X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b?\u0010/\"\u0004\b@\u00101R\"\u0010A\u001a\n B*\u0004\u0018\u00010&0&X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bC\u0010(\"\u0004\bD\u0010*R\u001a\u0010E\u001a\u000208X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bF\u0010G\"\u0004\bH\u0010IR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\bJ\u0010K¨\u0006h"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/components/amountselection/AmountSelectionPresenterBase;", "Lcom/bitcoin/mwallet/app/viper/ScreenPresenter;", "Lcom/bitcoin/mwallet/app/components/amountselection/AmountSelectionRouterBase;", "Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/selectcurrency/OnCurrencySelectedListener;", "context", "Landroid/content/Context;", "viewModelScope", "Lkotlinx/coroutines/CoroutineScope;", "getDefaultCurrencyInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetDefaultCurrencyInteractor;", "getCurrentExchangeRateInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetCurrentExchangeRateInteractor;", "router", "(Landroid/content/Context;Lkotlinx/coroutines/CoroutineScope;Lcom/bitcoin/mwallet/core/interactors/GetDefaultCurrencyInteractor;Lcom/bitcoin/mwallet/core/interactors/GetCurrentExchangeRateInteractor;Lcom/bitcoin/mwallet/app/components/amountselection/AmountSelectionRouterBase;)V", "MAX_CRYPTO_DECIMALS", "", "getMAX_CRYPTO_DECIMALS", "()I", "MAX_CRYPTO_INTEGERS", "getMAX_CRYPTO_INTEGERS", "MAX_FIAT_DECIMALS", "getMAX_FIAT_DECIMALS", "MAX_FIAT_INTEGERS", "getMAX_FIAT_INTEGERS", "MAX_TOKEN_DECIMALS", "getMAX_TOKEN_DECIMALS", "setMAX_TOKEN_DECIMALS", "(I)V", "MAX_TOKEN_INTEGERS", "getMAX_TOKEN_INTEGERS", "setMAX_TOKEN_INTEGERS", "coin", "Lcom/bitcoin/mwallet/core/models/Coin;", "getCoin", "()Lcom/bitcoin/mwallet/core/models/Coin;", "setCoin", "(Lcom/bitcoin/mwallet/core/models/Coin;)V", "cryptoAmount", "Ljava/math/BigDecimal;", "getCryptoAmount", "()Ljava/math/BigDecimal;", "setCryptoAmount", "(Ljava/math/BigDecimal;)V", "displayCurrency", "Landroidx/lifecycle/MutableLiveData;", "Ljava/util/Currency;", "getDisplayCurrency", "()Landroidx/lifecycle/MutableLiveData;", "setDisplayCurrency", "(Landroidx/lifecycle/MutableLiveData;)V", "fiatAmount", "getFiatAmount", "setFiatAmount", "getGetCurrentExchangeRateInteractor", "()Lcom/bitcoin/mwallet/core/interactors/GetCurrentExchangeRateInteractor;", "primaryAmount", "", "getPrimaryAmount", "setPrimaryAmount", "primaryEntryType", "Lcom/bitcoin/mwallet/app/components/amountselection/AmountSelectionPresenterBase$EntryType;", "getPrimaryEntryType", "secondaryDisplayAmount", "getSecondaryDisplayAmount", "setSecondaryDisplayAmount", "tokenInputAmount", "kotlin.jvm.PlatformType", "getTokenInputAmount", "setTokenInputAmount", "tokenTicker", "getTokenTicker", "()Ljava/lang/String;", "setTokenTicker", "(Ljava/lang/String;)V", "getViewModelScope", "()Lkotlinx/coroutines/CoroutineScope;", "amountHasDecimals", "", "convertBigDecimalToString", "value", "convertToBigDecimal", "decimalTapped", "", "deleteTapped", "digitTapped", "digit", "formatPrimaryDisplayValue", "displayValue", "getMaxDecimal", "getMaxInteger", "numDecimalDigits", "onBackPressed", "onClosePressed", "activity", "Landroid/app/Activity;", "onCurrencySelectTapped", "fm", "Landroidx/fragment/app/FragmentManager;", "onCurrencySelected", "onSwapCurrencyTapped", "storeBalance", "swapDisplayAmount", "updateSecondaryAmount", "EntryType", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: AmountSelectionPresenterBase.kt */
public class AmountSelectionPresenterBase extends ScreenPresenter<AmountSelectionRouterBase> implements OnCurrencySelectedListener {
    private final int MAX_CRYPTO_DECIMALS = 8;
    private final int MAX_CRYPTO_INTEGERS = 8;
    private final int MAX_FIAT_DECIMALS = 2;
    private final int MAX_FIAT_INTEGERS = 8;
    private int MAX_TOKEN_DECIMALS;
    private int MAX_TOKEN_INTEGERS = 12;
    @NotNull
    private Coin coin = Coin.BCH;
    @Nullable
    private BigDecimal cryptoAmount = BigDecimal.ZERO;
    @NotNull
    private MutableLiveData<Currency> displayCurrency;
    @Nullable
    private BigDecimal fiatAmount = BigDecimal.ZERO;
    @NotNull
    private final GetCurrentExchangeRateInteractor getCurrentExchangeRateInteractor;
    @NotNull
    private MutableLiveData<String> primaryAmount;
    @NotNull
    private final MutableLiveData<EntryType> primaryEntryType = new MutableLiveData<>();
    @NotNull
    private MutableLiveData<String> secondaryDisplayAmount;
    private BigDecimal tokenInputAmount = BigDecimal.ZERO;
    @NotNull
    private String tokenTicker = "";
    @NotNull
    private final CoroutineScope viewModelScope;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/components/amountselection/AmountSelectionPresenterBase$EntryType;", "", "(Ljava/lang/String;I)V", "FIAT", "COIN", "TOKEN", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: AmountSelectionPresenterBase.kt */
    public enum EntryType {
        FIAT,
        COIN,
        TOKEN
    }

    @Metadata(mo37403bv = {1, 0, 3}, mo37406k = 3, mo37407mv = {1, 1, 15})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[EntryType.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1 = new int[EntryType.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$2 = new int[EntryType.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$3 = new int[EntryType.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$4 = new int[EntryType.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$5 = new int[EntryType.values().length];

        static {
            $EnumSwitchMapping$0[EntryType.FIAT.ordinal()] = 1;
            $EnumSwitchMapping$0[EntryType.COIN.ordinal()] = 2;
            $EnumSwitchMapping$0[EntryType.TOKEN.ordinal()] = 3;
            $EnumSwitchMapping$1[EntryType.FIAT.ordinal()] = 1;
            $EnumSwitchMapping$1[EntryType.COIN.ordinal()] = 2;
            $EnumSwitchMapping$1[EntryType.TOKEN.ordinal()] = 3;
            $EnumSwitchMapping$2[EntryType.FIAT.ordinal()] = 1;
            $EnumSwitchMapping$2[EntryType.COIN.ordinal()] = 2;
            $EnumSwitchMapping$2[EntryType.TOKEN.ordinal()] = 3;
            $EnumSwitchMapping$3[EntryType.FIAT.ordinal()] = 1;
            $EnumSwitchMapping$3[EntryType.COIN.ordinal()] = 2;
            $EnumSwitchMapping$3[EntryType.TOKEN.ordinal()] = 3;
            $EnumSwitchMapping$4[EntryType.FIAT.ordinal()] = 1;
            $EnumSwitchMapping$4[EntryType.COIN.ordinal()] = 2;
            $EnumSwitchMapping$4[EntryType.TOKEN.ordinal()] = 3;
            $EnumSwitchMapping$5[EntryType.FIAT.ordinal()] = 1;
            $EnumSwitchMapping$5[EntryType.COIN.ordinal()] = 2;
        }
    }

    @NotNull
    public final CoroutineScope getViewModelScope() {
        return this.viewModelScope;
    }

    @NotNull
    public final GetCurrentExchangeRateInteractor getGetCurrentExchangeRateInteractor() {
        return this.getCurrentExchangeRateInteractor;
    }

    public AmountSelectionPresenterBase(@NotNull Context context, @NotNull CoroutineScope coroutineScope, @NotNull GetDefaultCurrencyInteractor getDefaultCurrencyInteractor, @NotNull GetCurrentExchangeRateInteractor getCurrentExchangeRateInteractor2, @NotNull AmountSelectionRouterBase amountSelectionRouterBase) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(coroutineScope, "viewModelScope");
        Intrinsics.checkParameterIsNotNull(getDefaultCurrencyInteractor, "getDefaultCurrencyInteractor");
        Intrinsics.checkParameterIsNotNull(getCurrentExchangeRateInteractor2, "getCurrentExchangeRateInteractor");
        Intrinsics.checkParameterIsNotNull(amountSelectionRouterBase, "router");
        super(context, amountSelectionRouterBase);
        this.viewModelScope = coroutineScope;
        this.getCurrentExchangeRateInteractor = getCurrentExchangeRateInteractor2;
        this.displayCurrency = new MutableLiveData<>(getDefaultCurrencyInteractor.getDefaultFiatCurrency());
        String str = BuildConfig.BUILD_NUMBER;
        this.primaryAmount = new MutableLiveData<>(str);
        this.secondaryDisplayAmount = new MutableLiveData<>(str);
    }

    public final int getMAX_FIAT_DECIMALS() {
        return this.MAX_FIAT_DECIMALS;
    }

    public final int getMAX_FIAT_INTEGERS() {
        return this.MAX_FIAT_INTEGERS;
    }

    public final int getMAX_TOKEN_DECIMALS() {
        return this.MAX_TOKEN_DECIMALS;
    }

    public final void setMAX_TOKEN_DECIMALS(int i) {
        this.MAX_TOKEN_DECIMALS = i;
    }

    public final int getMAX_TOKEN_INTEGERS() {
        return this.MAX_TOKEN_INTEGERS;
    }

    public final void setMAX_TOKEN_INTEGERS(int i) {
        this.MAX_TOKEN_INTEGERS = i;
    }

    public final int getMAX_CRYPTO_DECIMALS() {
        return this.MAX_CRYPTO_DECIMALS;
    }

    public final int getMAX_CRYPTO_INTEGERS() {
        return this.MAX_CRYPTO_INTEGERS;
    }

    @NotNull
    public final MutableLiveData<EntryType> getPrimaryEntryType() {
        return this.primaryEntryType;
    }

    @Nullable
    public final BigDecimal getFiatAmount() {
        return this.fiatAmount;
    }

    public final void setFiatAmount(@Nullable BigDecimal bigDecimal) {
        this.fiatAmount = bigDecimal;
    }

    @Nullable
    public final BigDecimal getCryptoAmount() {
        return this.cryptoAmount;
    }

    public final void setCryptoAmount(@Nullable BigDecimal bigDecimal) {
        this.cryptoAmount = bigDecimal;
    }

    public final BigDecimal getTokenInputAmount() {
        return this.tokenInputAmount;
    }

    public final void setTokenInputAmount(BigDecimal bigDecimal) {
        this.tokenInputAmount = bigDecimal;
    }

    @NotNull
    public final Coin getCoin() {
        return this.coin;
    }

    public final void setCoin(@NotNull Coin coin2) {
        Intrinsics.checkParameterIsNotNull(coin2, "<set-?>");
        this.coin = coin2;
    }

    @NotNull
    public final String getTokenTicker() {
        return this.tokenTicker;
    }

    public final void setTokenTicker(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.tokenTicker = str;
    }

    @NotNull
    public final MutableLiveData<Currency> getDisplayCurrency() {
        return this.displayCurrency;
    }

    public final void setDisplayCurrency(@NotNull MutableLiveData<Currency> mutableLiveData) {
        Intrinsics.checkParameterIsNotNull(mutableLiveData, "<set-?>");
        this.displayCurrency = mutableLiveData;
    }

    @NotNull
    public final MutableLiveData<String> getPrimaryAmount() {
        return this.primaryAmount;
    }

    public final void setPrimaryAmount(@NotNull MutableLiveData<String> mutableLiveData) {
        Intrinsics.checkParameterIsNotNull(mutableLiveData, "<set-?>");
        this.primaryAmount = mutableLiveData;
    }

    @NotNull
    public final MutableLiveData<String> getSecondaryDisplayAmount() {
        return this.secondaryDisplayAmount;
    }

    public final void setSecondaryDisplayAmount(@NotNull MutableLiveData<String> mutableLiveData) {
        Intrinsics.checkParameterIsNotNull(mutableLiveData, "<set-?>");
        this.secondaryDisplayAmount = mutableLiveData;
    }

    private final int getMaxDecimal() {
        EntryType entryType = (EntryType) this.primaryEntryType.getValue();
        if (entryType == null) {
            return 0;
        }
        int i = WhenMappings.$EnumSwitchMapping$0[entryType.ordinal()];
        if (i == 1) {
            return this.MAX_FIAT_DECIMALS;
        }
        if (i == 2) {
            return this.MAX_CRYPTO_DECIMALS;
        }
        if (i == 3) {
            return this.MAX_TOKEN_DECIMALS;
        }
        throw new NoWhenBranchMatchedException();
    }

    private final int getMaxInteger() {
        EntryType entryType = (EntryType) this.primaryEntryType.getValue();
        if (entryType == null) {
            return 0;
        }
        int i = WhenMappings.$EnumSwitchMapping$1[entryType.ordinal()];
        if (i == 1) {
            return this.MAX_FIAT_INTEGERS;
        }
        if (i == 2) {
            return this.MAX_CRYPTO_INTEGERS;
        }
        if (i == 3) {
            return this.MAX_TOKEN_INTEGERS;
        }
        throw new NoWhenBranchMatchedException();
    }

    public final void digitTapped(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "digit");
        Object value = this.primaryAmount.getValue();
        if (value == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(value, "primaryAmount.value!!");
        String str2 = (String) value;
        String str3 = BuildConfig.BUILD_NUMBER;
        if (Intrinsics.areEqual((Object) str, (Object) str3) && amountHasDecimals() && numDecimalDigits() < getMaxDecimal() - 1) {
            MutableLiveData<String> mutableLiveData = this.primaryAmount;
            StringBuilder sb = new StringBuilder();
            sb.append(str2);
            sb.append('0');
            mutableLiveData.postValue(sb.toString());
        } else if (!amountHasDecimals() || numDecimalDigits() != getMaxDecimal()) {
            if (!amountHasDecimals()) {
                BigDecimal bigDecimalOrNull = StringsKt.toBigDecimalOrNull(str2);
                if (bigDecimalOrNull != null && BigDecimalKt.getNumberOfWholeDigits(bigDecimalOrNull) == getMaxInteger()) {
                    return;
                }
            }
            if (Intrinsics.areEqual((Object) str2, (Object) str3)) {
                this.primaryAmount.postValue(str);
            } else {
                MutableLiveData<String> mutableLiveData2 = this.primaryAmount;
                StringBuilder sb2 = new StringBuilder();
                sb2.append(str2);
                sb2.append(str);
                mutableLiveData2.postValue(sb2.toString());
            }
        }
    }

    public final void deleteTapped() {
        Object value = this.primaryAmount.getValue();
        if (value == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(value, "primaryAmount.value!!");
        String str = (String) value;
        boolean z = false;
        if (!(str.length() == 0)) {
            String dropLast = StringsKt.dropLast(str, 1);
            if (dropLast.length() == 0) {
                z = true;
            }
            String str2 = BuildConfig.BUILD_NUMBER;
            if (z || Intrinsics.areEqual((Object) dropLast, (Object) str2)) {
                this.primaryAmount.postValue(str2);
            } else {
                this.primaryAmount.postValue(dropLast);
            }
        }
    }

    public final void decimalTapped() {
        Object value = this.primaryAmount.getValue();
        if (value == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(value, "primaryAmount.value!!");
        String str = (String) value;
        if (!amountHasDecimals()) {
            MutableLiveData<String> mutableLiveData = this.primaryAmount;
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            DecimalFormatSymbols decimalFormatSymbols = new DecimalFormat().getDecimalFormatSymbols();
            Intrinsics.checkExpressionValueIsNotNull(decimalFormatSymbols, "DecimalFormat().decimalFormatSymbols");
            sb.append(decimalFormatSymbols.getDecimalSeparator());
            mutableLiveData.postValue(sb.toString());
        }
    }

    @NotNull
    public final String formatPrimaryDisplayValue(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "displayValue");
        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormat().getDecimalFormatSymbols();
        Intrinsics.checkExpressionValueIsNotNull(decimalFormatSymbols, "DecimalFormat().decimalFormatSymbols");
        char decimalSeparator = decimalFormatSymbols.getDecimalSeparator();
        EntryType entryType = (EntryType) this.primaryEntryType.getValue();
        if (entryType == null) {
            return "";
        }
        int i = WhenMappings.$EnumSwitchMapping$2[entryType.ordinal()];
        if (i == 1) {
            BigDecimal scale = convertToBigDecimal(str).setScale(2, 2);
            String str2 = null;
            String plainGroupedString = scale != null ? BigDecimalKt.toPlainGroupedString(scale) : null;
            if (StringsKt.last(str) == decimalSeparator) {
                StringBuilder sb = new StringBuilder();
                Currency currency = (Currency) this.displayCurrency.getValue();
                if (currency != null) {
                    str2 = currency.getSymbol();
                }
                sb.append(str2);
                sb.append(' ');
                sb.append(plainGroupedString);
                sb.append(decimalSeparator);
                return sb.toString();
            }
            String takeLast = StringsKt.takeLast(str, 2);
            StringBuilder sb2 = new StringBuilder();
            sb2.append(decimalSeparator);
            sb2.append('0');
            if (Intrinsics.areEqual((Object) takeLast, (Object) sb2.toString())) {
                StringBuilder sb3 = new StringBuilder();
                Currency currency2 = (Currency) this.displayCurrency.getValue();
                if (currency2 != null) {
                    str2 = currency2.getSymbol();
                }
                sb3.append(str2);
                sb3.append(' ');
                sb3.append(plainGroupedString);
                sb3.append(decimalSeparator);
                sb3.append('0');
                return sb3.toString();
            }
            String takeLast2 = StringsKt.takeLast(str, 3);
            StringBuilder sb4 = new StringBuilder();
            sb4.append(decimalSeparator);
            String str3 = "00";
            sb4.append(str3);
            if (Intrinsics.areEqual((Object) takeLast2, (Object) sb4.toString())) {
                StringBuilder sb5 = new StringBuilder();
                Currency currency3 = (Currency) this.displayCurrency.getValue();
                if (currency3 != null) {
                    str2 = currency3.getSymbol();
                }
                sb5.append(str2);
                sb5.append(' ');
                sb5.append(plainGroupedString);
                sb5.append(decimalSeparator);
                sb5.append(str3);
                return sb5.toString();
            }
            StringBuilder sb6 = new StringBuilder();
            Currency currency4 = (Currency) this.displayCurrency.getValue();
            if (currency4 != null) {
                str2 = currency4.getSymbol();
            }
            sb6.append(str2);
            sb6.append(' ');
            sb6.append(plainGroupedString);
            return sb6.toString();
        } else if (i == 2) {
            StringBuilder sb7 = new StringBuilder();
            sb7.append(str);
            sb7.append(' ');
            sb7.append(this.coin.getTicker());
            return sb7.toString();
        } else if (i == 3) {
            StringBuilder sb8 = new StringBuilder();
            sb8.append(str);
            sb8.append(' ');
            sb8.append(this.tokenTicker);
            return sb8.toString();
        } else {
            throw new NoWhenBranchMatchedException();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0053  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0056 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void storeBalance() {
        /*
            r11 = this;
            androidx.lifecycle.MutableLiveData<java.lang.String> r0 = r11.primaryAmount
            java.lang.Object r0 = r0.getValue()
            if (r0 != 0) goto L_0x000b
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x000b:
            java.lang.String r1 = "primaryAmount.value!!"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
            java.lang.String r0 = (java.lang.String) r0
            androidx.lifecycle.MutableLiveData<java.lang.String> r1 = r11.secondaryDisplayAmount
            java.lang.Object r1 = r1.getValue()
            java.lang.String r1 = (java.lang.String) r1
            r2 = 0
            r3 = 1
            if (r1 == 0) goto L_0x0065
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.Appendable r4 = (java.lang.Appendable) r4
            int r5 = r1.length()
            r6 = 0
            r7 = 0
        L_0x002d:
            if (r7 >= r5) goto L_0x0059
            char r8 = r1.charAt(r7)
            boolean r9 = java.lang.Character.isDigit(r8)
            if (r9 != 0) goto L_0x0050
            java.text.DecimalFormat r9 = new java.text.DecimalFormat
            r9.<init>()
            java.text.DecimalFormatSymbols r9 = r9.getDecimalFormatSymbols()
            java.lang.String r10 = "DecimalFormat().decimalFormatSymbols"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r9, r10)
            char r9 = r9.getDecimalSeparator()
            if (r8 != r9) goto L_0x004e
            goto L_0x0050
        L_0x004e:
            r9 = 0
            goto L_0x0051
        L_0x0050:
            r9 = 1
        L_0x0051:
            if (r9 == 0) goto L_0x0056
            r4.append(r8)
        L_0x0056:
            int r7 = r7 + 1
            goto L_0x002d
        L_0x0059:
            java.lang.StringBuilder r4 = (java.lang.StringBuilder) r4
            java.lang.String r1 = r4.toString()
            java.lang.String r4 = "filterTo(StringBuilder(), predicate).toString()"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r4)
            goto L_0x0066
        L_0x0065:
            r1 = r2
        L_0x0066:
            if (r1 == 0) goto L_0x006c
            java.math.BigDecimal r2 = r11.convertToBigDecimal(r1)
        L_0x006c:
            java.math.BigDecimal r0 = r11.convertToBigDecimal(r0)
            androidx.lifecycle.MutableLiveData<com.bitcoin.mwallet.app.components.amountselection.AmountSelectionPresenterBase$EntryType> r1 = r11.primaryEntryType
            java.lang.Object r1 = r1.getValue()
            com.bitcoin.mwallet.app.components.amountselection.AmountSelectionPresenterBase$EntryType r1 = (com.bitcoin.mwallet.app.components.amountselection.AmountSelectionPresenterBase.EntryType) r1
            if (r1 != 0) goto L_0x007b
            goto L_0x0098
        L_0x007b:
            int[] r4 = com.bitcoin.mwallet.app.components.amountselection.AmountSelectionPresenterBase.WhenMappings.$EnumSwitchMapping$3
            int r1 = r1.ordinal()
            r1 = r4[r1]
            if (r1 == r3) goto L_0x0094
            r3 = 2
            if (r1 == r3) goto L_0x008f
            r2 = 3
            if (r1 == r2) goto L_0x008c
            goto L_0x0098
        L_0x008c:
            r11.tokenInputAmount = r0
            goto L_0x0098
        L_0x008f:
            r11.cryptoAmount = r0
            r11.fiatAmount = r2
            goto L_0x0098
        L_0x0094:
            r11.fiatAmount = r0
            r11.cryptoAmount = r2
        L_0x0098:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.app.components.amountselection.AmountSelectionPresenterBase.storeBalance():void");
    }

    @NotNull
    public final String convertBigDecimalToString(@NotNull BigDecimal bigDecimal) {
        Intrinsics.checkParameterIsNotNull(bigDecimal, "value");
        NumberFormat instance = NumberFormat.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(instance, "nf");
        instance.setMaximumFractionDigits(8);
        String format = instance.format(bigDecimal);
        Intrinsics.checkExpressionValueIsNotNull(format, "nf.format(value)");
        return format;
    }

    @NotNull
    public final BigDecimal convertToBigDecimal(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "value");
        NumberFormat instance = NumberFormat.getInstance();
        if (instance != null) {
            DecimalFormat decimalFormat = (DecimalFormat) instance;
            decimalFormat.setParseBigDecimal(true);
            Number parse = decimalFormat.parse(str);
            if (parse != null) {
                return (BigDecimal) parse;
            }
            throw new TypeCastException("null cannot be cast to non-null type java.math.BigDecimal");
        }
        throw new TypeCastException("null cannot be cast to non-null type java.text.DecimalFormat");
    }

    public final void updateSecondaryAmount() {
        Object value = this.primaryAmount.getValue();
        if (value == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(value, "primaryAmount.value!!");
        BigDecimal convertToBigDecimal = convertToBigDecimal((String) value);
        EntryType entryType = (EntryType) this.primaryEntryType.getValue();
        if (entryType != null) {
            int i = WhenMappings.$EnumSwitchMapping$4[entryType.ordinal()];
            if (i == 1) {
                BuildersKt__BuildersKt.runBlocking$default(null, new AmountSelectionPresenterBase$updateSecondaryAmount$1(this, convertToBigDecimal, null), 1, null);
            } else if (i == 2) {
                BuildersKt__BuildersKt.runBlocking$default(null, new AmountSelectionPresenterBase$updateSecondaryAmount$2(this, convertToBigDecimal, null), 1, null);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0040  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0043 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void swapDisplayAmount() {
        /*
            r9 = this;
            androidx.lifecycle.MutableLiveData<java.lang.String> r0 = r9.secondaryDisplayAmount
            java.lang.Object r0 = r0.getValue()
            java.lang.String r0 = (java.lang.String) r0
            r1 = 0
            if (r0 == 0) goto L_0x0052
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.Appendable r2 = (java.lang.Appendable) r2
            int r3 = r0.length()
            r4 = 0
            r5 = 0
        L_0x001a:
            if (r5 >= r3) goto L_0x0046
            char r6 = r0.charAt(r5)
            boolean r7 = java.lang.Character.isDigit(r6)
            if (r7 != 0) goto L_0x003d
            java.text.DecimalFormat r7 = new java.text.DecimalFormat
            r7.<init>()
            java.text.DecimalFormatSymbols r7 = r7.getDecimalFormatSymbols()
            java.lang.String r8 = "DecimalFormat().decimalFormatSymbols"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r7, r8)
            char r7 = r7.getDecimalSeparator()
            if (r6 != r7) goto L_0x003b
            goto L_0x003d
        L_0x003b:
            r7 = 0
            goto L_0x003e
        L_0x003d:
            r7 = 1
        L_0x003e:
            if (r7 == 0) goto L_0x0043
            r2.append(r6)
        L_0x0043:
            int r5 = r5 + 1
            goto L_0x001a
        L_0x0046:
            java.lang.StringBuilder r2 = (java.lang.StringBuilder) r2
            java.lang.String r0 = r2.toString()
            java.lang.String r2 = "filterTo(StringBuilder(), predicate).toString()"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r2)
            goto L_0x0053
        L_0x0052:
            r0 = r1
        L_0x0053:
            if (r0 == 0) goto L_0x0059
            java.math.BigDecimal r1 = r9.convertToBigDecimal(r0)
        L_0x0059:
            if (r1 == 0) goto L_0x006d
            double r1 = r1.doubleValue()
            r3 = 0
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 != 0) goto L_0x006d
            androidx.lifecycle.MutableLiveData<java.lang.String> r0 = r9.primaryAmount
            java.lang.String r1 = "0"
            r0.setValue(r1)
            goto L_0x0072
        L_0x006d:
            androidx.lifecycle.MutableLiveData<java.lang.String> r1 = r9.primaryAmount
            r1.setValue(r0)
        L_0x0072:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.app.components.amountselection.AmountSelectionPresenterBase.swapDisplayAmount():void");
    }

    public final void onCurrencySelectTapped(@NotNull FragmentManager fragmentManager) {
        Intrinsics.checkParameterIsNotNull(fragmentManager, "fm");
        ((AmountSelectionRouterBase) getRouter()).toCurrencySelect(fragmentManager, this);
    }

    public final void onSwapCurrencyTapped() {
        EntryType entryType;
        MutableLiveData<EntryType> mutableLiveData = this.primaryEntryType;
        EntryType entryType2 = (EntryType) mutableLiveData.getValue();
        if (entryType2 != null) {
            int i = WhenMappings.$EnumSwitchMapping$5[entryType2.ordinal()];
            if (i == 1) {
                entryType = EntryType.COIN;
            } else if (i == 2) {
                entryType = EntryType.FIAT;
            }
            mutableLiveData.setValue(entryType);
        }
        entryType = EntryType.FIAT;
        mutableLiveData.setValue(entryType);
    }

    private final boolean amountHasDecimals() {
        Object value = this.primaryAmount.getValue();
        if (value == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(value, "primaryAmount.value!!");
        CharSequence charSequence = (String) value;
        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormat().getDecimalFormatSymbols();
        Intrinsics.checkExpressionValueIsNotNull(decimalFormatSymbols, "DecimalFormat().decimalFormatSymbols");
        return StringsKt.contains$default(charSequence, decimalFormatSymbols.getDecimalSeparator(), false, 2, (Object) null);
    }

    private final int numDecimalDigits() {
        Object value = this.primaryAmount.getValue();
        if (value == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(value, "primaryAmount.value!!");
        String str = (String) value;
        CharSequence charSequence = str;
        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormat().getDecimalFormatSymbols();
        Intrinsics.checkExpressionValueIsNotNull(decimalFormatSymbols, "DecimalFormat().decimalFormatSymbols");
        int indexOf$default = StringsKt.indexOf$default(charSequence, decimalFormatSymbols.getDecimalSeparator(), 0, false, 6, (Object) null);
        if (indexOf$default == -1) {
            return 0;
        }
        int i = indexOf$default + 1;
        if (str != null) {
            String substring = str.substring(i);
            Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.String).substring(startIndex)");
            return substring.length();
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }

    public void onCurrencySelected(@NotNull Currency currency) {
        Intrinsics.checkParameterIsNotNull(currency, "coin");
        this.primaryAmount.postValue(BuildConfig.BUILD_NUMBER);
        this.displayCurrency.postValue(currency);
    }

    public final void onBackPressed() {
        ((AmountSelectionRouterBase) getRouter()).onBackPressed();
    }

    public final void onClosePressed(@NotNull Activity activity) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        ((AmountSelectionRouterBase) getRouter()).onClosePressed(activity);
    }
}
