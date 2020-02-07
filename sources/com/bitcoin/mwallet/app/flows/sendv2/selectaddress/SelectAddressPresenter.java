package com.bitcoin.mwallet.app.flows.sendv2.selectaddress;

import android.content.Context;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.MutableLiveData;
import com.bitcoin.bitcoink.p008tx.TxId;
import com.bitcoin.mwallet.app.android.Event;
import com.bitcoin.mwallet.app.components.walletselector.WalletSelectorListener;
import com.bitcoin.mwallet.app.flows.sendv2.dialogs.bip70paymentinforequest.OnPaymentSuccessListener;
import com.bitcoin.mwallet.app.flows.sendv2.dialogs.selectcontact.SelectContactListener;
import com.bitcoin.mwallet.app.flows.sendv2.dialogs.selectsendingasset.OnSendingAssetSelectedListener;
import com.bitcoin.mwallet.app.flows.sendv2.dialogs.selectsendingasset.SendableAssetModel;
import com.bitcoin.mwallet.app.flows.sendv2.sendamountselection.SendWhatModel;
import com.bitcoin.mwallet.app.viper.ScreenPresenter;
import com.bitcoin.mwallet.core.entity.WalletType;
import com.bitcoin.mwallet.core.interactors.CreateTxInteractor;
import com.bitcoin.mwallet.core.interactors.FeeValidatorInteractor;
import com.bitcoin.mwallet.core.interactors.GetAddressInteractor;
import com.bitcoin.mwallet.core.interactors.GetCurrentExchangeRateInteractor;
import com.bitcoin.mwallet.core.interactors.GetDefaultCurrencyInteractor;
import com.bitcoin.mwallet.core.interactors.GetNetworkFeeInteractor;
import com.bitcoin.mwallet.core.interactors.GetWalletInteractor;
import com.bitcoin.mwallet.core.models.BitcoinUriContent;
import com.bitcoin.mwallet.core.models.Coin;
import com.bitcoin.mwallet.core.models.address.AddressAndPath;
import com.bitcoin.mwallet.core.models.contact.Contact;
import com.bitcoin.mwallet.core.models.p009tx.utxo.UtxoSelection.Error;
import com.bitcoin.mwallet.core.models.slp.SlpId;
import com.bitcoin.mwallet.core.models.wallet.C1261Wallet;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import com.bitcoin.mwallet.core.repositories.UtxoRepository;
import com.bitcoin.mwallet.core.services.AnalyticsService;
import com.bitcoin.mwallet.zion.ZionRepository;
import com.leanplum.internal.Constants.Params;
import java.util.Currency;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.GlobalScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000ð\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u00032\u00020\u00042\u00020\u00052\u00020\u0006Bm\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\u0006\u0010\u0013\u001a\u00020\u0014\u0012\u0006\u0010\u0015\u001a\u00020\u0016\u0012\u0006\u0010\u0017\u001a\u00020\u0018\u0012\u0006\u0010\u0019\u001a\u00020\u001a\u0012\u0006\u0010\u001b\u001a\u00020\u001c\u0012\u0006\u0010\u001d\u001a\u00020\u001e\u0012\u0006\u0010\u001f\u001a\u00020\u0002¢\u0006\u0002\u0010 J\b\u0010Z\u001a\u0004\u0018\u00010[J\u001b\u0010\\\u001a\u0004\u0018\u00010]2\u0006\u0010^\u001a\u00020_H@ø\u0001\u0000¢\u0006\u0002\u0010`J\u0010\u0010a\u001a\u00020b2\u0006\u0010c\u001a\u00020dH\u0016J\u0018\u0010e\u001a\u00020b2\u0006\u0010f\u001a\u00020g2\u0006\u0010h\u001a\u00020iH\u0016J\u0010\u0010j\u001a\u00020b2\u0006\u0010k\u001a\u00020lH\u0016J\u0018\u0010m\u001a\u00020b2\u0006\u0010n\u001a\u00020@2\u0006\u0010o\u001a\u00020pH\u0016J\u000e\u0010q\u001a\u00020b2\u0006\u0010r\u001a\u000202J\u000e\u0010s\u001a\u00020b2\u0006\u0010t\u001a\u00020[J\u000e\u0010G\u001a\u00020b2\u0006\u0010u\u001a\u000202J\u0006\u0010v\u001a\u00020bJ\u0010\u0010w\u001a\u00020b2\b\u0010x\u001a\u0004\u0018\u00010SJ\u000e\u0010y\u001a\u00020b2\u0006\u0010x\u001a\u00020SR\u000e\u0010\u000b\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u001d\u001a\u00020\u001e¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u001a\u0010#\u001a\u00020$X.¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u000e\u0010\u0011\u001a\u00020\u0012X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010+\u001a\u00020,¢\u0006\b\n\u0000\u001a\u0004\b-\u0010.R\u000e\u0010\u0015\u001a\u00020\u0016X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0004¢\u0006\u0002\n\u0000R\u001f\u0010/\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u000202\u0018\u00010100¢\u0006\b\n\u0000\u001a\u0004\b3\u00104R\u000e\u0010\u0013\u001a\u00020\u0014X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000R\u0011\u00105\u001a\u000206¢\u0006\b\n\u0000\u001a\u0004\b7\u00108R\u001c\u00109\u001a\u0004\u0018\u00010:X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b;\u0010<\"\u0004\b=\u0010>R\u001c\u0010?\u001a\u0004\u0018\u00010@X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bA\u0010B\"\u0004\bC\u0010DR \u0010E\u001a\b\u0012\u0004\u0012\u00020200X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bF\u00104\"\u0004\bG\u0010HR\"\u0010I\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010600X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bJ\u00104\"\u0004\bK\u0010HR \u0010L\u001a\b\u0012\u0004\u0012\u00020M00X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bN\u00104\"\u0004\bO\u0010HR\u0011\u0010\u001b\u001a\u00020\u001c¢\u0006\b\n\u0000\u001a\u0004\bP\u0010QR\u001c\u0010R\u001a\u0004\u0018\u00010SX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bT\u0010U\"\u0004\bV\u0010WR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\bX\u0010YR\u000e\u0010\u000f\u001a\u00020\u0010X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006z"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/sendv2/selectaddress/SelectAddressPresenter;", "Lcom/bitcoin/mwallet/app/viper/ScreenPresenter;", "Lcom/bitcoin/mwallet/app/flows/sendv2/selectaddress/SelectAddressRouter;", "Lcom/bitcoin/mwallet/app/components/walletselector/WalletSelectorListener;", "Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/selectsendingasset/OnSendingAssetSelectedListener;", "Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/bip70paymentinforequest/OnPaymentSuccessListener;", "Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/selectcontact/SelectContactListener;", "context", "Landroid/content/Context;", "viewModelScope", "Lkotlinx/coroutines/CoroutineScope;", "addressInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetAddressInteractor;", "getWalletInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetWalletInteractor;", "zionRepository", "Lcom/bitcoin/mwallet/zion/ZionRepository;", "createTxInteractor", "Lcom/bitcoin/mwallet/core/interactors/CreateTxInteractor;", "feeValidatorInteractor", "Lcom/bitcoin/mwallet/core/interactors/FeeValidatorInteractor;", "currentExchangeRateInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetCurrentExchangeRateInteractor;", "defaultCurrencyInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetDefaultCurrencyInteractor;", "getNetworkFeeInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetNetworkFeeInteractor;", "utxoRepository", "Lcom/bitcoin/mwallet/core/repositories/UtxoRepository;", "analyticsService", "Lcom/bitcoin/mwallet/core/services/AnalyticsService;", "router", "(Landroid/content/Context;Lkotlinx/coroutines/CoroutineScope;Lcom/bitcoin/mwallet/core/interactors/GetAddressInteractor;Lcom/bitcoin/mwallet/core/interactors/GetWalletInteractor;Lcom/bitcoin/mwallet/zion/ZionRepository;Lcom/bitcoin/mwallet/core/interactors/CreateTxInteractor;Lcom/bitcoin/mwallet/core/interactors/FeeValidatorInteractor;Lcom/bitcoin/mwallet/core/interactors/GetCurrentExchangeRateInteractor;Lcom/bitcoin/mwallet/core/interactors/GetDefaultCurrencyInteractor;Lcom/bitcoin/mwallet/core/interactors/GetNetworkFeeInteractor;Lcom/bitcoin/mwallet/core/repositories/UtxoRepository;Lcom/bitcoin/mwallet/core/services/AnalyticsService;Lcom/bitcoin/mwallet/app/flows/sendv2/selectaddress/SelectAddressRouter;)V", "getAnalyticsService", "()Lcom/bitcoin/mwallet/core/services/AnalyticsService;", "bitcoinUriContent", "Lcom/bitcoin/mwallet/core/models/BitcoinUriContent;", "getBitcoinUriContent", "()Lcom/bitcoin/mwallet/core/models/BitcoinUriContent;", "setBitcoinUriContent", "(Lcom/bitcoin/mwallet/core/models/BitcoinUriContent;)V", "getContext", "()Landroid/content/Context;", "currency", "Ljava/util/Currency;", "getCurrency", "()Ljava/util/Currency;", "error", "Landroidx/lifecycle/MutableLiveData;", "Lcom/bitcoin/mwallet/app/android/Event;", "", "getError", "()Landroidx/lifecycle/MutableLiveData;", "hasZion", "", "getHasZion", "()Z", "preferredTokenId", "Lcom/bitcoin/mwallet/core/models/slp/SlpId;", "getPreferredTokenId", "()Lcom/bitcoin/mwallet/core/models/slp/SlpId;", "setPreferredTokenId", "(Lcom/bitcoin/mwallet/core/models/slp/SlpId;)V", "preferredWalletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "getPreferredWalletId", "()Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "setPreferredWalletId", "(Lcom/bitcoin/mwallet/core/models/wallet/WalletId;)V", "rawInput", "getRawInput", "setRawInput", "(Landroidx/lifecycle/MutableLiveData;)V", "rawInputIsValid", "getRawInputIsValid", "setRawInputIsValid", "selectedAddress", "Lcom/bitcoin/mwallet/core/models/address/AddressAndPath;", "getSelectedAddress", "setSelectedAddress", "getUtxoRepository", "()Lcom/bitcoin/mwallet/core/repositories/UtxoRepository;", "viewFragmentManager", "Landroidx/fragment/app/FragmentManager;", "getViewFragmentManager", "()Landroidx/fragment/app/FragmentManager;", "setViewFragmentManager", "(Landroidx/fragment/app/FragmentManager;)V", "getViewModelScope", "()Lkotlinx/coroutines/CoroutineScope;", "checkAutoSendThreshold", "Lcom/bitcoin/mwallet/core/models/wallet/Wallet;", "getSatoshiPerByte", "Ljava/math/BigDecimal;", "coin", "Lcom/bitcoin/mwallet/core/models/Coin;", "(Lcom/bitcoin/mwallet/core/models/Coin;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onContactSelected", "", "item", "Lcom/bitcoin/mwallet/core/models/contact/Contact;", "onPaymentSuccessListener", "txId", "Lcom/bitcoin/bitcoink/tx/TxId;", "sendWhatModel", "Lcom/bitcoin/mwallet/app/flows/sendv2/sendamountselection/SendWhatModel;", "onSendingAssetSelected", "sendableAssetModel", "Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/selectsendingasset/SendableAssetModel;", "onWalletSelected", "walletId", "walletType", "Lcom/bitcoin/mwallet/core/entity/WalletType;", "pasteTapped", "clipboardString", "sendNativeCoin", "walletCandidate", "input", "toSelectAmount", "toSelectContact", "fm", "toSelectLocalReceivingWallet", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: SelectAddressPresenter.kt */
public final class SelectAddressPresenter extends ScreenPresenter<SelectAddressRouter> implements WalletSelectorListener, OnSendingAssetSelectedListener, OnPaymentSuccessListener, SelectContactListener {
    /* access modifiers changed from: private */
    public final GetAddressInteractor addressInteractor;
    @NotNull
    private final AnalyticsService analyticsService;
    @NotNull
    public BitcoinUriContent bitcoinUriContent;
    @NotNull
    private final Context context;
    /* access modifiers changed from: private */
    public final CreateTxInteractor createTxInteractor;
    @NotNull
    private final Currency currency = this.defaultCurrencyInteractor.getDefaultFiatCurrency();
    /* access modifiers changed from: private */
    public final GetCurrentExchangeRateInteractor currentExchangeRateInteractor;
    private final GetDefaultCurrencyInteractor defaultCurrencyInteractor;
    @NotNull
    private final MutableLiveData<Event<String>> error = new MutableLiveData<>();
    private final FeeValidatorInteractor feeValidatorInteractor;
    private final GetNetworkFeeInteractor getNetworkFeeInteractor;
    /* access modifiers changed from: private */
    public final GetWalletInteractor getWalletInteractor;
    private final boolean hasZion = ((Boolean) BuildersKt__BuildersKt.runBlocking$default(null, new SelectAddressPresenter$hasZion$1(this, null), 1, null)).booleanValue();
    @Nullable
    private SlpId preferredTokenId;
    @Nullable
    private WalletId preferredWalletId;
    @NotNull
    private MutableLiveData<String> rawInput = new MutableLiveData<>();
    @NotNull
    private MutableLiveData<Boolean> rawInputIsValid = new MutableLiveData<>(null);
    @NotNull
    private MutableLiveData<AddressAndPath> selectedAddress = new MutableLiveData<>();
    @NotNull
    private final UtxoRepository utxoRepository;
    @Nullable
    private FragmentManager viewFragmentManager;
    @NotNull
    private final CoroutineScope viewModelScope;
    /* access modifiers changed from: private */
    public final ZionRepository zionRepository;

    @Metadata(mo37403bv = {1, 0, 3}, mo37406k = 3, mo37407mv = {1, 1, 15})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[Coin.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1 = new int[WalletType.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$2 = new int[Error.values().length];

        static {
            $EnumSwitchMapping$0[Coin.BCH.ordinal()] = 1;
            $EnumSwitchMapping$0[Coin.BTC.ordinal()] = 2;
            $EnumSwitchMapping$1[WalletType.BCH.ordinal()] = 1;
            $EnumSwitchMapping$1[WalletType.BTC.ordinal()] = 2;
            $EnumSwitchMapping$1[WalletType.SLP.ordinal()] = 3;
            $EnumSwitchMapping$2[Error.INSUFFICIENT_FUNDS.ordinal()] = 1;
        }
    }

    public static final /* synthetic */ SelectAddressRouter access$getRouter$p(SelectAddressPresenter selectAddressPresenter) {
        return (SelectAddressRouter) selectAddressPresenter.getRouter();
    }

    @NotNull
    public final Context getContext() {
        return this.context;
    }

    @NotNull
    public final CoroutineScope getViewModelScope() {
        return this.viewModelScope;
    }

    @NotNull
    public final UtxoRepository getUtxoRepository() {
        return this.utxoRepository;
    }

    @NotNull
    public final AnalyticsService getAnalyticsService() {
        return this.analyticsService;
    }

    public SelectAddressPresenter(@NotNull Context context2, @NotNull CoroutineScope coroutineScope, @NotNull GetAddressInteractor getAddressInteractor, @NotNull GetWalletInteractor getWalletInteractor2, @NotNull ZionRepository zionRepository2, @NotNull CreateTxInteractor createTxInteractor2, @NotNull FeeValidatorInteractor feeValidatorInteractor2, @NotNull GetCurrentExchangeRateInteractor getCurrentExchangeRateInteractor, @NotNull GetDefaultCurrencyInteractor getDefaultCurrencyInteractor, @NotNull GetNetworkFeeInteractor getNetworkFeeInteractor2, @NotNull UtxoRepository utxoRepository2, @NotNull AnalyticsService analyticsService2, @NotNull SelectAddressRouter selectAddressRouter) {
        Intrinsics.checkParameterIsNotNull(context2, "context");
        Intrinsics.checkParameterIsNotNull(coroutineScope, "viewModelScope");
        Intrinsics.checkParameterIsNotNull(getAddressInteractor, "addressInteractor");
        Intrinsics.checkParameterIsNotNull(getWalletInteractor2, "getWalletInteractor");
        Intrinsics.checkParameterIsNotNull(zionRepository2, "zionRepository");
        Intrinsics.checkParameterIsNotNull(createTxInteractor2, "createTxInteractor");
        Intrinsics.checkParameterIsNotNull(feeValidatorInteractor2, "feeValidatorInteractor");
        Intrinsics.checkParameterIsNotNull(getCurrentExchangeRateInteractor, "currentExchangeRateInteractor");
        Intrinsics.checkParameterIsNotNull(getDefaultCurrencyInteractor, "defaultCurrencyInteractor");
        Intrinsics.checkParameterIsNotNull(getNetworkFeeInteractor2, "getNetworkFeeInteractor");
        Intrinsics.checkParameterIsNotNull(utxoRepository2, "utxoRepository");
        Intrinsics.checkParameterIsNotNull(analyticsService2, "analyticsService");
        Intrinsics.checkParameterIsNotNull(selectAddressRouter, "router");
        super(context2, selectAddressRouter);
        this.context = context2;
        this.viewModelScope = coroutineScope;
        this.addressInteractor = getAddressInteractor;
        this.getWalletInteractor = getWalletInteractor2;
        this.zionRepository = zionRepository2;
        this.createTxInteractor = createTxInteractor2;
        this.feeValidatorInteractor = feeValidatorInteractor2;
        this.currentExchangeRateInteractor = getCurrentExchangeRateInteractor;
        this.defaultCurrencyInteractor = getDefaultCurrencyInteractor;
        this.getNetworkFeeInteractor = getNetworkFeeInteractor2;
        this.utxoRepository = utxoRepository2;
        this.analyticsService = analyticsService2;
    }

    public void onPaymentSuccessListener(@NotNull TxId txId, @NotNull SendWhatModel sendWhatModel) {
        Intrinsics.checkParameterIsNotNull(txId, "txId");
        Intrinsics.checkParameterIsNotNull(sendWhatModel, "sendWhatModel");
        ((SelectAddressRouter) getRouter()).toSendSuccess(txId, sendWhatModel);
    }

    @NotNull
    public final MutableLiveData<AddressAndPath> getSelectedAddress() {
        return this.selectedAddress;
    }

    public final void setSelectedAddress(@NotNull MutableLiveData<AddressAndPath> mutableLiveData) {
        Intrinsics.checkParameterIsNotNull(mutableLiveData, "<set-?>");
        this.selectedAddress = mutableLiveData;
    }

    @NotNull
    public final BitcoinUriContent getBitcoinUriContent() {
        BitcoinUriContent bitcoinUriContent2 = this.bitcoinUriContent;
        if (bitcoinUriContent2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bitcoinUriContent");
        }
        return bitcoinUriContent2;
    }

    public final void setBitcoinUriContent(@NotNull BitcoinUriContent bitcoinUriContent2) {
        Intrinsics.checkParameterIsNotNull(bitcoinUriContent2, "<set-?>");
        this.bitcoinUriContent = bitcoinUriContent2;
    }

    @NotNull
    public final MutableLiveData<String> getRawInput() {
        return this.rawInput;
    }

    public final void setRawInput(@NotNull MutableLiveData<String> mutableLiveData) {
        Intrinsics.checkParameterIsNotNull(mutableLiveData, "<set-?>");
        this.rawInput = mutableLiveData;
    }

    @NotNull
    public final MutableLiveData<Boolean> getRawInputIsValid() {
        return this.rawInputIsValid;
    }

    public final void setRawInputIsValid(@NotNull MutableLiveData<Boolean> mutableLiveData) {
        Intrinsics.checkParameterIsNotNull(mutableLiveData, "<set-?>");
        this.rawInputIsValid = mutableLiveData;
    }

    public final boolean getHasZion() {
        return this.hasZion;
    }

    @Nullable
    public final WalletId getPreferredWalletId() {
        return this.preferredWalletId;
    }

    public final void setPreferredWalletId(@Nullable WalletId walletId) {
        this.preferredWalletId = walletId;
    }

    @Nullable
    public final SlpId getPreferredTokenId() {
        return this.preferredTokenId;
    }

    public final void setPreferredTokenId(@Nullable SlpId slpId) {
        this.preferredTokenId = slpId;
    }

    @NotNull
    public final MutableLiveData<Event<String>> getError() {
        return this.error;
    }

    @NotNull
    public final Currency getCurrency() {
        return this.currency;
    }

    @Nullable
    public final FragmentManager getViewFragmentManager() {
        return this.viewFragmentManager;
    }

    public final void setViewFragmentManager(@Nullable FragmentManager fragmentManager) {
        this.viewFragmentManager = fragmentManager;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0027, code lost:
        if (r0.isSlp() != false) goto L_0x0029;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void toSelectAmount() {
        /*
            r5 = this;
            androidx.fragment.app.FragmentManager r0 = r5.viewFragmentManager
            if (r0 != 0) goto L_0x0005
            return
        L_0x0005:
            r0 = r5
            com.bitcoin.mwallet.app.flows.sendv2.selectaddress.SelectAddressPresenter r0 = (com.bitcoin.mwallet.app.flows.sendv2.selectaddress.SelectAddressPresenter) r0
            com.bitcoin.mwallet.core.models.BitcoinUriContent r0 = r0.bitcoinUriContent
            if (r0 != 0) goto L_0x000d
            return
        L_0x000d:
            com.bitcoin.mwallet.core.models.BitcoinUriContent r0 = r5.bitcoinUriContent
            java.lang.String r1 = "bitcoinUriContent"
            if (r0 != 0) goto L_0x0016
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
        L_0x0016:
            boolean r0 = r0.isBip70()
            if (r0 != 0) goto L_0x0029
            com.bitcoin.mwallet.core.models.BitcoinUriContent r0 = r5.bitcoinUriContent
            if (r0 != 0) goto L_0x0023
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
        L_0x0023:
            boolean r0 = r0.isSlp()
            if (r0 == 0) goto L_0x003f
        L_0x0029:
            boolean r0 = r5.hasZion
            if (r0 == 0) goto L_0x003f
            com.bitcoin.mwallet.app.components.unsupporteddialog.UnsupportedDialogView r0 = new com.bitcoin.mwallet.app.components.unsupporteddialog.UnsupportedDialogView
            r0.<init>()
            androidx.fragment.app.FragmentManager r1 = r5.viewFragmentManager
            if (r1 != 0) goto L_0x0039
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x0039:
            java.lang.String r2 = "Unsupported_dialog"
            r0.show(r1, r2)
            return
        L_0x003f:
            com.bitcoin.mwallet.core.models.BitcoinUriContent r0 = r5.bitcoinUriContent
            if (r0 != 0) goto L_0x0046
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
        L_0x0046:
            boolean r0 = r0.isBip70()
            if (r0 == 0) goto L_0x0075
            com.bitcoin.mwallet.core.services.AnalyticsService r0 = r5.analyticsService
            java.util.Map r2 = kotlin.collections.MapsKt.emptyMap()
            java.lang.String r3 = "payment_protocol_url_received"
            r0.track(r3, r2)
            com.bitcoin.mwallet.app.viper.RouterBase r0 = r5.getRouter()
            com.bitcoin.mwallet.app.flows.sendv2.selectaddress.SelectAddressRouter r0 = (com.bitcoin.mwallet.app.flows.sendv2.selectaddress.SelectAddressRouter) r0
            com.bitcoin.mwallet.core.models.BitcoinUriContent r2 = r5.bitcoinUriContent
            if (r2 != 0) goto L_0x0064
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
        L_0x0064:
            androidx.fragment.app.FragmentManager r1 = r5.viewFragmentManager
            if (r1 != 0) goto L_0x006b
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x006b:
            r3 = r5
            com.bitcoin.mwallet.app.flows.sendv2.dialogs.bip70paymentinforequest.OnPaymentSuccessListener r3 = (com.bitcoin.mwallet.app.flows.sendv2.dialogs.bip70paymentinforequest.OnPaymentSuccessListener) r3
            com.bitcoin.mwallet.core.models.wallet.WalletId r4 = r5.preferredWalletId
            r0.toBip70Payment(r2, r1, r3, r4)
            goto L_0x0102
        L_0x0075:
            com.bitcoin.mwallet.core.models.BitcoinUriContent r0 = r5.bitcoinUriContent
            if (r0 != 0) goto L_0x007c
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
        L_0x007c:
            com.bitcoin.mwallet.core.models.Coin r0 = r0.getCoin()
            if (r0 != 0) goto L_0x00ac
            com.bitcoin.mwallet.core.models.BitcoinUriContent r0 = r5.bitcoinUriContent
            if (r0 != 0) goto L_0x0089
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
        L_0x0089:
            boolean r0 = r0.isValid()
            if (r0 == 0) goto L_0x00ac
            com.bitcoin.mwallet.app.viper.RouterBase r0 = r5.getRouter()
            com.bitcoin.mwallet.app.flows.sendv2.selectaddress.SelectAddressRouter r0 = (com.bitcoin.mwallet.app.flows.sendv2.selectaddress.SelectAddressRouter) r0
            r2 = r5
            com.bitcoin.mwallet.app.flows.sendv2.dialogs.selectsendingasset.OnSendingAssetSelectedListener r2 = (com.bitcoin.mwallet.app.flows.sendv2.dialogs.selectsendingasset.OnSendingAssetSelectedListener) r2
            com.bitcoin.mwallet.core.models.BitcoinUriContent r3 = r5.bitcoinUriContent
            if (r3 != 0) goto L_0x009f
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
        L_0x009f:
            androidx.fragment.app.FragmentManager r1 = r5.viewFragmentManager
            if (r1 != 0) goto L_0x00a6
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x00a6:
            com.bitcoin.mwallet.core.models.wallet.WalletId r4 = r5.preferredWalletId
            r0.toSelectSendingAsset(r2, r3, r1, r4)
            goto L_0x0102
        L_0x00ac:
            com.bitcoin.mwallet.core.models.BitcoinUriContent r0 = r5.bitcoinUriContent
            if (r0 != 0) goto L_0x00b3
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
        L_0x00b3:
            boolean r0 = r0.isSlp()
            if (r0 == 0) goto L_0x00f0
            com.bitcoin.mwallet.core.models.BitcoinUriContent r0 = r5.bitcoinUriContent
            if (r0 != 0) goto L_0x00c0
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
        L_0x00c0:
            boolean r0 = r0.isValid()
            if (r0 == 0) goto L_0x00f0
            com.bitcoin.mwallet.core.models.BitcoinUriContent r0 = r5.bitcoinUriContent
            if (r0 != 0) goto L_0x00cd
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
        L_0x00cd:
            com.bitcoin.mwallet.core.models.slp.SlpId r0 = r0.getTokenId()
            if (r0 != 0) goto L_0x00f0
            com.bitcoin.mwallet.app.viper.RouterBase r0 = r5.getRouter()
            com.bitcoin.mwallet.app.flows.sendv2.selectaddress.SelectAddressRouter r0 = (com.bitcoin.mwallet.app.flows.sendv2.selectaddress.SelectAddressRouter) r0
            r2 = r5
            com.bitcoin.mwallet.app.flows.sendv2.dialogs.selectsendingasset.OnSendingAssetSelectedListener r2 = (com.bitcoin.mwallet.app.flows.sendv2.dialogs.selectsendingasset.OnSendingAssetSelectedListener) r2
            com.bitcoin.mwallet.core.models.BitcoinUriContent r3 = r5.bitcoinUriContent
            if (r3 != 0) goto L_0x00e3
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
        L_0x00e3:
            androidx.fragment.app.FragmentManager r1 = r5.viewFragmentManager
            if (r1 != 0) goto L_0x00ea
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x00ea:
            com.bitcoin.mwallet.core.models.wallet.WalletId r4 = r5.preferredWalletId
            r0.toSelectSendingAsset(r2, r3, r1, r4)
            goto L_0x0102
        L_0x00f0:
            com.bitcoin.mwallet.app.viper.RouterBase r0 = r5.getRouter()
            com.bitcoin.mwallet.app.flows.sendv2.selectaddress.SelectAddressRouter r0 = (com.bitcoin.mwallet.app.flows.sendv2.selectaddress.SelectAddressRouter) r0
            com.bitcoin.mwallet.core.models.BitcoinUriContent r2 = r5.bitcoinUriContent
            if (r2 != 0) goto L_0x00fd
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
        L_0x00fd:
            com.bitcoin.mwallet.core.models.wallet.WalletId r1 = r5.preferredWalletId
            r0.toSelectAmount(r2, r1)
        L_0x0102:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.app.flows.sendv2.selectaddress.SelectAddressPresenter.toSelectAmount():void");
    }

    public final void toSelectContact(@Nullable FragmentManager fragmentManager) {
        SelectAddressRouter selectAddressRouter = (SelectAddressRouter) getRouter();
        SelectContactListener selectContactListener = this;
        if (fragmentManager == null) {
            Intrinsics.throwNpe();
        }
        selectAddressRouter.toSelectContact(selectContactListener, fragmentManager);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0060, code lost:
        if (r4.isSlp() != false) goto L_0x0062;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void setRawInput(@org.jetbrains.annotations.NotNull java.lang.String r4) {
        /*
            r3 = this;
            java.lang.String r0 = "input"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r4, r0)
            java.lang.String r0 = ""
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r4, r0)
            if (r0 == 0) goto L_0x0015
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r4 = r3.rawInputIsValid
            r0 = 0
            r4.setValue(r0)
            goto L_0x0084
        L_0x0015:
            com.bitcoin.mwallet.core.models.BitcoinUriContent$CREATOR r0 = com.bitcoin.mwallet.core.models.BitcoinUriContent.CREATOR
            com.bitcoin.mwallet.core.models.BitcoinUriContent r4 = r0.parse(r4)
            r3.bitcoinUriContent = r4
            com.bitcoin.mwallet.core.models.slp.SlpId r4 = r3.preferredTokenId
            r0 = 0
            java.lang.String r1 = "bitcoinUriContent"
            if (r4 == 0) goto L_0x0048
            com.bitcoin.mwallet.core.models.BitcoinUriContent r4 = r3.bitcoinUriContent
            if (r4 != 0) goto L_0x002b
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
        L_0x002b:
            boolean r4 = r4.isSlp()
            if (r4 == 0) goto L_0x003e
            com.bitcoin.mwallet.core.models.BitcoinUriContent r4 = r3.bitcoinUriContent
            if (r4 != 0) goto L_0x0038
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
        L_0x0038:
            com.bitcoin.mwallet.core.models.slp.SlpId r2 = r3.preferredTokenId
            r4.setTokenId(r2)
            goto L_0x0048
        L_0x003e:
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r4 = r3.rawInputIsValid
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
            r4.setValue(r0)
            return
        L_0x0048:
            com.bitcoin.mwallet.core.models.BitcoinUriContent r4 = r3.bitcoinUriContent
            if (r4 != 0) goto L_0x004f
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
        L_0x004f:
            boolean r4 = r4.isBip70()
            if (r4 != 0) goto L_0x0062
            com.bitcoin.mwallet.core.models.BitcoinUriContent r4 = r3.bitcoinUriContent
            if (r4 != 0) goto L_0x005c
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
        L_0x005c:
            boolean r4 = r4.isSlp()
            if (r4 == 0) goto L_0x0070
        L_0x0062:
            boolean r4 = r3.hasZion
            if (r4 == 0) goto L_0x0070
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r4 = r3.rawInputIsValid
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
            r4.setValue(r0)
            goto L_0x0084
        L_0x0070:
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r4 = r3.rawInputIsValid
            com.bitcoin.mwallet.core.models.BitcoinUriContent r0 = r3.bitcoinUriContent
            if (r0 != 0) goto L_0x0079
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
        L_0x0079:
            boolean r0 = r0.isValid()
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
            r4.setValue(r0)
        L_0x0084:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.app.flows.sendv2.selectaddress.SelectAddressPresenter.setRawInput(java.lang.String):void");
    }

    public final void pasteTapped(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "clipboardString");
        this.rawInput.setValue(str);
    }

    public final void toSelectLocalReceivingWallet(@NotNull FragmentManager fragmentManager) {
        Intrinsics.checkParameterIsNotNull(fragmentManager, "fm");
        WalletType walletType = null;
        WalletType walletType2 = null;
        boolean z = true;
        if (this.preferredWalletId != null) {
            C1261Wallet wallet = (C1261Wallet) BuildersKt__BuildersKt.runBlocking$default(null, new SelectAddressPresenter$toSelectLocalReceivingWallet$wallet$1(this, null), 1, null);
            Coin coin = wallet != null ? wallet.getCoin() : null;
            if (coin != null) {
                int i = WhenMappings.$EnumSwitchMapping$0[coin.ordinal()];
                if (i == 1) {
                    walletType = WalletType.BCH;
                } else if (i == 2) {
                    walletType = WalletType.BTC;
                }
            }
            walletType2 = walletType;
        }
        if (this.preferredTokenId != null) {
            walletType2 = WalletType.SLP;
        } else {
            z = false;
        }
        ((SelectAddressRouter) getRouter()).toWalletSelect(this, fragmentManager, walletType2, z);
    }

    public void onWalletSelected(@NotNull WalletId walletId, @NotNull WalletType walletType) {
        Intrinsics.checkParameterIsNotNull(walletId, "walletId");
        Intrinsics.checkParameterIsNotNull(walletType, "walletType");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new SelectAddressPresenter$onWalletSelected$1(this, walletId, walletType, null), 3, null);
    }

    public void onSendingAssetSelected(@NotNull SendableAssetModel sendableAssetModel) {
        Intrinsics.checkParameterIsNotNull(sendableAssetModel, "sendableAssetModel");
        BitcoinUriContent bitcoinUriContent2 = this.bitcoinUriContent;
        String str = "bitcoinUriContent";
        if (bitcoinUriContent2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(str);
        }
        bitcoinUriContent2.setTokenId(sendableAssetModel.getTokenId());
        BitcoinUriContent bitcoinUriContent3 = this.bitcoinUriContent;
        if (bitcoinUriContent3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(str);
        }
        bitcoinUriContent3.setCoin(sendableAssetModel.getCoin());
        toSelectAmount();
    }

    public void onContactSelected(@NotNull Contact contact) {
        Intrinsics.checkParameterIsNotNull(contact, Params.IAP_ITEM);
        this.rawInput.setValue(contact.getAddress());
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x003a  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ java.lang.Object getSatoshiPerByte(@org.jetbrains.annotations.NotNull com.bitcoin.mwallet.core.models.Coin r5, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.math.BigDecimal> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof com.bitcoin.mwallet.app.flows.sendv2.selectaddress.SelectAddressPresenter$getSatoshiPerByte$1
            if (r0 == 0) goto L_0x0014
            r0 = r6
            com.bitcoin.mwallet.app.flows.sendv2.selectaddress.SelectAddressPresenter$getSatoshiPerByte$1 r0 = (com.bitcoin.mwallet.app.flows.sendv2.selectaddress.SelectAddressPresenter$getSatoshiPerByte$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L_0x0019
        L_0x0014:
            com.bitcoin.mwallet.app.flows.sendv2.selectaddress.SelectAddressPresenter$getSatoshiPerByte$1 r0 = new com.bitcoin.mwallet.app.flows.sendv2.selectaddress.SelectAddressPresenter$getSatoshiPerByte$1
            r0.<init>(r4, r6)
        L_0x0019:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x003a
            if (r2 != r3) goto L_0x0032
            java.lang.Object r5 = r0.L$1
            com.bitcoin.mwallet.core.models.Coin r5 = (com.bitcoin.mwallet.core.models.Coin) r5
            java.lang.Object r5 = r0.L$0
            com.bitcoin.mwallet.app.flows.sendv2.selectaddress.SelectAddressPresenter r5 = (com.bitcoin.mwallet.app.flows.sendv2.selectaddress.SelectAddressPresenter) r5
            kotlin.ResultKt.throwOnFailure(r6)
            goto L_0x004c
        L_0x0032:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x003a:
            kotlin.ResultKt.throwOnFailure(r6)
            com.bitcoin.mwallet.core.interactors.GetNetworkFeeInteractor r6 = r4.getNetworkFeeInteractor
            r0.L$0 = r4
            r0.L$1 = r5
            r0.label = r3
            java.lang.Object r6 = r6.getNetworkFeeAndPreference(r5, r0)
            if (r6 != r1) goto L_0x004c
            return r1
        L_0x004c:
            kotlin.Pair r6 = (kotlin.Pair) r6
            java.lang.Object r5 = r6.getFirst()
            com.bitcoin.mwallet.core.models.networkfee.NetworkFees r5 = (com.bitcoin.mwallet.core.models.networkfee.NetworkFees) r5
            java.lang.Object r6 = r6.getSecond()
            com.bitcoin.mwallet.core.models.networkfee.NetworkFeeLevel r6 = (com.bitcoin.mwallet.core.models.networkfee.NetworkFeeLevel) r6
            java.math.BigDecimal r5 = r5.getFee(r6)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.app.flows.sendv2.selectaddress.SelectAddressPresenter.getSatoshiPerByte(com.bitcoin.mwallet.core.models.Coin, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final void sendNativeCoin(@NotNull C1261Wallet wallet) {
        Intrinsics.checkParameterIsNotNull(wallet, "walletCandidate");
        BuildersKt__Builders_commonKt.launch$default(this.viewModelScope, null, null, new SelectAddressPresenter$sendNativeCoin$1(this, wallet, null), 3, null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0053  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0060  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00bf  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x007c A[SYNTHETIC] */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.bitcoin.mwallet.core.models.wallet.C1261Wallet checkAutoSendThreshold() {
        /*
            r15 = this;
            com.bitcoin.mwallet.core.models.BitcoinUriContent r0 = r15.bitcoinUriContent
            java.lang.String r1 = "bitcoinUriContent"
            if (r0 != 0) goto L_0x0009
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
        L_0x0009:
            com.bitcoin.bitcoink.tx.Satoshis r0 = r0.getAmount()
            r2 = 0
            if (r0 == 0) goto L_0x00f5
            com.bitcoin.mwallet.core.models.BitcoinUriContent r3 = r15.bitcoinUriContent
            if (r3 != 0) goto L_0x0017
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
        L_0x0017:
            com.bitcoin.mwallet.core.models.Coin r3 = r3.getCoin()
            if (r3 == 0) goto L_0x00f5
            com.bitcoin.mwallet.app.flows.sendv2.selectaddress.SelectAddressPresenter$checkAutoSendThreshold$fiatExchangeRate$1 r3 = new com.bitcoin.mwallet.app.flows.sendv2.selectaddress.SelectAddressPresenter$checkAutoSendThreshold$fiatExchangeRate$1
            r3.<init>(r15, r2)
            kotlin.jvm.functions.Function2 r3 = (kotlin.jvm.functions.Function2) r3
            r4 = 1
            java.lang.Object r3 = kotlinx.coroutines.BuildersKt__BuildersKt.runBlocking$default(r2, r3, r4, r2)
            com.bitcoin.mwallet.core.models.exchangerate.FiatExchangeRate r3 = (com.bitcoin.mwallet.core.models.exchangerate.FiatExchangeRate) r3
            r5 = r2
            com.bitcoin.mwallet.core.models.wallet.Wallet r5 = (com.bitcoin.mwallet.core.models.wallet.C1261Wallet) r5
            com.bitcoin.mwallet.core.models.wallet.WalletId r6 = r15.preferredWalletId
            if (r6 == 0) goto L_0x0062
            com.bitcoin.mwallet.app.flows.sendv2.selectaddress.SelectAddressPresenter$checkAutoSendThreshold$preferredWallet$1 r6 = new com.bitcoin.mwallet.app.flows.sendv2.selectaddress.SelectAddressPresenter$checkAutoSendThreshold$preferredWallet$1
            r6.<init>(r15, r2)
            kotlin.jvm.functions.Function2 r6 = (kotlin.jvm.functions.Function2) r6
            java.lang.Object r4 = kotlinx.coroutines.BuildersKt__BuildersKt.runBlocking$default(r2, r6, r4, r2)
            com.bitcoin.mwallet.core.models.wallet.Wallet r4 = (com.bitcoin.mwallet.core.models.wallet.C1261Wallet) r4
            if (r4 == 0) goto L_0x004c
            com.bitcoin.mwallet.core.preferences.WalletPreference r6 = r4.preference()
            if (r6 == 0) goto L_0x004c
            java.math.BigDecimal r6 = r6.getThresholdAmount()
            goto L_0x004d
        L_0x004c:
            r6 = r2
        L_0x004d:
            com.bitcoin.bitcoink.tx.Satoshis r3 = com.bitcoin.mwallet.core.models.exchangerate.FiatExchangeRateKt.fiatToSatoshis(r3, r6)
            if (r3 != 0) goto L_0x0056
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x0056:
            long r6 = r0.getSatoshis()
            int r3 = r3.compareTo(r6)
            if (r3 <= 0) goto L_0x00cc
            r5 = r4
            goto L_0x00cc
        L_0x0062:
            com.bitcoin.mwallet.app.flows.sendv2.selectaddress.SelectAddressPresenter$checkAutoSendThreshold$wallets$1 r5 = new com.bitcoin.mwallet.app.flows.sendv2.selectaddress.SelectAddressPresenter$checkAutoSendThreshold$wallets$1
            r5.<init>(r15, r2)
            kotlin.jvm.functions.Function2 r5 = (kotlin.jvm.functions.Function2) r5
            java.lang.Object r5 = kotlinx.coroutines.BuildersKt__BuildersKt.runBlocking$default(r2, r5, r4, r2)
            java.util.List r5 = (java.util.List) r5
            java.lang.Iterable r5 = (java.lang.Iterable) r5
            java.util.ArrayList r6 = new java.util.ArrayList
            r6.<init>()
            java.util.Collection r6 = (java.util.Collection) r6
            java.util.Iterator r5 = r5.iterator()
        L_0x007c:
            boolean r7 = r5.hasNext()
            if (r7 == 0) goto L_0x00c3
            java.lang.Object r7 = r5.next()
            r8 = r7
            com.bitcoin.mwallet.core.models.wallet.Wallet r8 = (com.bitcoin.mwallet.core.models.wallet.C1261Wallet) r8
            com.bitcoin.mwallet.core.models.Coin r9 = r8.getCoin()
            com.bitcoin.mwallet.core.models.BitcoinUriContent r10 = r15.bitcoinUriContent
            if (r10 != 0) goto L_0x0094
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
        L_0x0094:
            com.bitcoin.mwallet.core.models.Coin r10 = r10.getCoin()
            if (r10 != 0) goto L_0x009d
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x009d:
            if (r9 != r10) goto L_0x00bc
            com.bitcoin.mwallet.core.preferences.WalletPreference r8 = r8.preference()
            java.math.BigDecimal r8 = r8.getThresholdAmount()
            com.bitcoin.bitcoink.tx.Satoshis r8 = com.bitcoin.mwallet.core.models.exchangerate.FiatExchangeRateKt.fiatToSatoshis(r3, r8)
            if (r8 != 0) goto L_0x00b0
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x00b0:
            long r9 = r0.getSatoshis()
            int r8 = r8.compareTo(r9)
            if (r8 <= 0) goto L_0x00bc
            r8 = 1
            goto L_0x00bd
        L_0x00bc:
            r8 = 0
        L_0x00bd:
            if (r8 == 0) goto L_0x007c
            r6.add(r7)
            goto L_0x007c
        L_0x00c3:
            java.util.List r6 = (java.util.List) r6
            java.lang.Object r3 = kotlin.collections.CollectionsKt.firstOrNull(r6)
            r5 = r3
            com.bitcoin.mwallet.core.models.wallet.Wallet r5 = (com.bitcoin.mwallet.core.models.wallet.C1261Wallet) r5
        L_0x00cc:
            if (r5 == 0) goto L_0x00f5
            com.bitcoin.mwallet.core.interactors.FeeValidatorInteractor r6 = r15.feeValidatorInteractor
            com.bitcoin.mwallet.core.models.wallet.WalletId r7 = r5.getId()
            java.math.BigDecimal r8 = r0.getCoins()
            com.bitcoin.mwallet.core.models.BitcoinUriContent r0 = r15.bitcoinUriContent
            if (r0 != 0) goto L_0x00df
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
        L_0x00df:
            com.bitcoin.mwallet.core.models.Coin r9 = r0.getCoin()
            if (r9 != 0) goto L_0x00e8
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x00e8:
            r10 = 0
            r11 = 0
            r13 = 24
            r14 = 0
            boolean r0 = com.bitcoin.mwallet.core.interactors.FeeValidatorInteractor.isInsufficientCoinFee$default(r6, r7, r8, r9, r10, r11, r13, r14)
            if (r0 != 0) goto L_0x00f5
            return r5
        L_0x00f5:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.app.flows.sendv2.selectaddress.SelectAddressPresenter.checkAutoSendThreshold():com.bitcoin.mwallet.core.models.wallet.Wallet");
    }
}
