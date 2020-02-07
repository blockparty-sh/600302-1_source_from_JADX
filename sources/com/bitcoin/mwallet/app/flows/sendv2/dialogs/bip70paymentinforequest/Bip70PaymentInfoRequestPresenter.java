package com.bitcoin.mwallet.app.flows.sendv2.dialogs.bip70paymentinforequest;

import android.app.KeyguardManager;
import android.content.Context;
import android.os.Build.VERSION;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.bitcoin.bitcoink.p008tx.Satoshis;
import com.bitcoin.mwallet.Bip70PaymentErrorStatus;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.app.android.LiveDataKt;
import com.bitcoin.mwallet.app.components.walletselector.WalletSelectorListener;
import com.bitcoin.mwallet.app.flows.sendv2.entity.SendWalletData;
import com.bitcoin.mwallet.app.viper.ScreenPresenter;
import com.bitcoin.mwallet.core.entity.WalletType;
import com.bitcoin.mwallet.core.interactors.BiometricsInteractor;
import com.bitcoin.mwallet.core.interactors.Bip70PaymentInteractor;
import com.bitcoin.mwallet.core.interactors.CreateTxInteractor;
import com.bitcoin.mwallet.core.interactors.FeeValidatorInteractor;
import com.bitcoin.mwallet.core.interactors.GetBalanceByWalletInteractor;
import com.bitcoin.mwallet.core.interactors.GetCurrentExchangeRateInteractor;
import com.bitcoin.mwallet.core.interactors.GetDefaultCurrencyInteractor;
import com.bitcoin.mwallet.core.interactors.GetNetworkFeeInteractor;
import com.bitcoin.mwallet.core.interactors.GetWalletInteractor;
import com.bitcoin.mwallet.core.models.BitcoinUriContent;
import com.bitcoin.mwallet.core.models.Coin;
import com.bitcoin.mwallet.core.models.bip70payment.Bip70PaymentInfo;
import com.bitcoin.mwallet.core.models.bip70payment.Bip70PaymentOutput;
import com.bitcoin.mwallet.core.models.exchangerate.FiatExchangeRate;
import com.bitcoin.mwallet.core.models.wallet.C1261Wallet;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import com.bitcoin.mwallet.core.models.wallet.WalletInfoBalance;
import com.bitcoin.mwallet.core.repositories.UtxoRepository;
import com.bitcoin.mwallet.core.services.AnalyticsService;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs;
import com.ncorti.slidetoact.SlideToActView;
import com.ncorti.slidetoact.SlideToActView.OnSlideCompleteListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref.DoubleRef;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000¨\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u00032\u00020\u0004:\u0002\u0001Bm\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\u0006\u0010\u0013\u001a\u00020\u0014\u0012\u0006\u0010\u0015\u001a\u00020\u0016\u0012\u0006\u0010\u0017\u001a\u00020\u0018\u0012\u0006\u0010\u0019\u001a\u00020\u001a\u0012\u0006\u0010\u001b\u001a\u00020\u001c\u0012\u0006\u0010\u001d\u001a\u00020\u0002¢\u0006\u0002\u0010\u001eJ\u0006\u0010p\u001a\u00020qJ\b\u0010r\u001a\u0004\u0018\u00010sJ\f\u0010t\u001a\b\u0012\u0004\u0012\u00020v0uJ\u0013\u0010w\u001a\u0004\u0018\u00010sH@ø\u0001\u0000¢\u0006\u0002\u0010xJ\u0010\u0010y\u001a\u0004\u0018\u00010z2\u0006\u0010{\u001a\u00020aJ\u000e\u0010|\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010}0uJ\u001d\u0010~\u001a\u000202\u0007\u0010\u0001\u001a\u00020!2\n\u0010\u0001\u001a\u0005\u0018\u00010\u0001H\u0002J#\u0010\u0001\u001a\u000202\u0006\u00101\u001a\u0002022\u0007\u0010\u0001\u001a\u00020I2\t\u0010\u0001\u001a\u0004\u0018\u00010aJ\u0007\u0010\u0001\u001a\u00020!J\u0007\u0010\u0001\u001a\u00020J\u0013\u0010\u0001\u001a\u000202\b\u0010\u0001\u001a\u00030\u0001H\u0016J\u0019\u0010\u0001\u001a\u000202\u0006\u0010{\u001a\u00020a2\u0006\u0010n\u001a\u00020oH\u0016J\u000e\u0010l\u001a\u000202\u0006\u0010{\u001a\u00020aJ\u001d\u0010\u0001\u001a\u000202\b\u0010\u0001\u001a\u00030\u00012\b\u0010\u0001\u001a\u00030\u0001H\u0002J\u0011\u0010\u0001\u001a\u000202\b\u0010\u0001\u001a\u00030\u0001R\u0017\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020!0 ¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0017\u0010$\u001a\b\u0012\u0004\u0012\u00020!0 ¢\u0006\b\n\u0000\u001a\u0004\b%\u0010#R\u0019\u0010&\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010'0 ¢\u0006\b\n\u0000\u001a\u0004\b(\u0010#R\u0011\u0010\u001b\u001a\u00020\u001c¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u000e\u0010\u0013\u001a\u00020\u0014X\u0004¢\u0006\u0002\n\u0000R\u001c\u0010+\u001a\u0004\u0018\u00010,X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R\u001a\u00101\u001a\u000202X.¢\u0006\u000e\n\u0000\u001a\u0004\b3\u00104\"\u0004\b5\u00106R\u001c\u00107\u001a\u0004\u0018\u000108X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b9\u0010:\"\u0004\b;\u0010<R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b=\u0010>R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u001a\u0010?\u001a\u00020@X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bA\u0010B\"\u0004\bC\u0010DR\u000e\u0010\u0015\u001a\u00020\u0016X\u0004¢\u0006\u0002\n\u0000R\u0019\u0010E\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010F0 ¢\u0006\b\n\u0000\u001a\u0004\bG\u0010#R\u000e\u0010\u0019\u001a\u00020\u001aX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000R\u001a\u0010H\u001a\u00020IX.¢\u0006\u000e\n\u0000\u001a\u0004\bJ\u0010K\"\u0004\bL\u0010MR(\u0010N\u001a\u0010\u0012\f\u0012\n O*\u0004\u0018\u00010!0!0 X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bP\u0010#\"\u0004\bQ\u0010RR\u001c\u0010S\u001a\u0004\u0018\u00010TX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bU\u0010V\"\u0004\bW\u0010XR\"\u0010Y\u001a\n\u0012\u0004\u0012\u00020[\u0018\u00010ZX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\\\u0010]\"\u0004\b^\u0010_R\"\u0010`\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010a0 X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bb\u0010#\"\u0004\bc\u0010RR\u000e\u0010\r\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000R'\u0010d\u001a\u0018\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020F0ej\b\u0012\u0004\u0012\u00020F`f0 ¢\u0006\b\n\u0000\u001a\u0004\bg\u0010#R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u001c\u0010h\u001a\u0004\u0018\u00010iX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bj\u0010k\"\u0004\bl\u0010mR\u000e\u0010\u000f\u001a\u00020\u0010X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010n\u001a\u0004\u0018\u00010oX\u000e¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u0001"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/bip70paymentinforequest/Bip70PaymentInfoRequestPresenter;", "Lcom/bitcoin/mwallet/app/viper/ScreenPresenter;", "Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/bip70paymentinforequest/Bip70PaymentInfoRequestRouter;", "Lcom/bitcoin/mwallet/app/components/walletselector/WalletSelectorListener;", "Lcom/ncorti/slidetoact/SlideToActView$OnSlideCompleteListener;", "context", "Landroid/content/Context;", "viewModelScope", "Lkotlinx/coroutines/CoroutineScope;", "createTxInteractor", "Lcom/bitcoin/mwallet/core/interactors/CreateTxInteractor;", "getNetworkFeeInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetNetworkFeeInteractor;", "utxoService", "Lcom/bitcoin/mwallet/core/repositories/UtxoRepository;", "walletInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetWalletInteractor;", "getBalanceByWalletInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetBalanceByWalletInteractor;", "bip70PaymentInteractor", "Lcom/bitcoin/mwallet/core/interactors/Bip70PaymentInteractor;", "exchangeRateInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetCurrentExchangeRateInteractor;", "defaultCurrencyInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetDefaultCurrencyInteractor;", "feeValidatorInteractor", "Lcom/bitcoin/mwallet/core/interactors/FeeValidatorInteractor;", "analyticsService", "Lcom/bitcoin/mwallet/core/services/AnalyticsService;", "router", "(Landroid/content/Context;Lkotlinx/coroutines/CoroutineScope;Lcom/bitcoin/mwallet/core/interactors/CreateTxInteractor;Lcom/bitcoin/mwallet/core/interactors/GetNetworkFeeInteractor;Lcom/bitcoin/mwallet/core/repositories/UtxoRepository;Lcom/bitcoin/mwallet/core/interactors/GetWalletInteractor;Lcom/bitcoin/mwallet/core/interactors/GetBalanceByWalletInteractor;Lcom/bitcoin/mwallet/core/interactors/Bip70PaymentInteractor;Lcom/bitcoin/mwallet/core/interactors/GetCurrentExchangeRateInteractor;Lcom/bitcoin/mwallet/core/interactors/GetDefaultCurrencyInteractor;Lcom/bitcoin/mwallet/core/interactors/FeeValidatorInteractor;Lcom/bitcoin/mwallet/core/services/AnalyticsService;Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/bip70paymentinforequest/Bip70PaymentInfoRequestRouter;)V", "_broadcastSuccess", "Landroidx/lifecycle/MutableLiveData;", "", "get_broadcastSuccess", "()Landroidx/lifecycle/MutableLiveData;", "_dataLoaded", "get_dataLoaded", "_errorState", "Lcom/bitcoin/mwallet/Bip70PaymentErrorStatus;", "get_errorState", "getAnalyticsService", "()Lcom/bitcoin/mwallet/core/services/AnalyticsService;", "bip70Url", "", "getBip70Url", "()Ljava/lang/String;", "setBip70Url", "(Ljava/lang/String;)V", "bitcoinUriContent", "Lcom/bitcoin/mwallet/core/models/BitcoinUriContent;", "getBitcoinUriContent", "()Lcom/bitcoin/mwallet/core/models/BitcoinUriContent;", "setBitcoinUriContent", "(Lcom/bitcoin/mwallet/core/models/BitcoinUriContent;)V", "coin", "Lcom/bitcoin/mwallet/core/models/Coin;", "getCoin", "()Lcom/bitcoin/mwallet/core/models/Coin;", "setCoin", "(Lcom/bitcoin/mwallet/core/models/Coin;)V", "getContext", "()Landroid/content/Context;", "defaultCurrency", "Ljava/util/Currency;", "getDefaultCurrency", "()Ljava/util/Currency;", "setDefaultCurrency", "(Ljava/util/Currency;)V", "expiryTimer", "", "getExpiryTimer", "listener", "Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/bip70paymentinforequest/OnPaymentSuccessListener;", "getListener", "()Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/bip70paymentinforequest/OnPaymentSuccessListener;", "setListener", "(Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/bip70paymentinforequest/OnPaymentSuccessListener;)V", "needsBiometricForSend", "kotlin.jvm.PlatformType", "getNeedsBiometricForSend", "setNeedsBiometricForSend", "(Landroidx/lifecycle/MutableLiveData;)V", "paymentInfo", "Lcom/bitcoin/mwallet/core/models/bip70payment/Bip70PaymentInfo;", "getPaymentInfo", "()Lcom/bitcoin/mwallet/core/models/bip70payment/Bip70PaymentInfo;", "setPaymentInfo", "(Lcom/bitcoin/mwallet/core/models/bip70payment/Bip70PaymentInfo;)V", "paymentTransactionInfo", "", "Lcom/bitcoin/mwallet/core/models/bip70payment/Bip70PaymentOutput;", "getPaymentTransactionInfo", "()Ljava/util/List;", "setPaymentTransactionInfo", "(Ljava/util/List;)V", "selectedWalletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "getSelectedWalletId", "setSelectedWalletId", "validationError", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "getValidationError", "walletData", "Lcom/bitcoin/mwallet/app/flows/sendv2/entity/SendWalletData;", "getWalletData", "()Lcom/bitcoin/mwallet/app/flows/sendv2/entity/SendWalletData;", "setWalletData", "(Lcom/bitcoin/mwallet/app/flows/sendv2/entity/SendWalletData;)V", "walletType", "Lcom/bitcoin/mwallet/core/entity/WalletType;", "getInvoiceCoinAmount", "Lcom/bitcoin/bitcoink/tx/Satoshis;", "getInvoiceFiatAmount", "Ljava/math/BigDecimal;", "getPaymentState", "Landroidx/lifecycle/LiveData;", "Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/bip70paymentinforequest/Bip70PaymentInfoRequestPresenter$PaymentState;", "getSatoshiPerByte", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getWallet", "Lcom/bitcoin/mwallet/core/models/wallet/Wallet;", "walletId", "getWalletBalance", "Lcom/bitcoin/mwallet/core/models/wallet/WalletInfoBalance;", "handleSendResult", "", "isSuccess", "resultTxId", "Lcom/bitcoin/bitcoink/tx/TxId;", "init", "paymentListener", "preferredWalletId", "isInsufficientFee", "onSend", "onSlideComplete", "view", "Lcom/ncorti/slidetoact/SlideToActView;", "onWalletSelected", "startTimer", "startTime", "", "expiryTime", "toWalletSelection", "fm", "Landroidx/fragment/app/FragmentManager;", "PaymentState", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: Bip70PaymentInfoRequestPresenter.kt */
public final class Bip70PaymentInfoRequestPresenter extends ScreenPresenter<Bip70PaymentInfoRequestRouter> implements WalletSelectorListener, OnSlideCompleteListener {
    @NotNull
    private final MutableLiveData<Boolean> _broadcastSuccess;
    @NotNull
    private final MutableLiveData<Boolean> _dataLoaded;
    @NotNull
    private final MutableLiveData<Bip70PaymentErrorStatus> _errorState;
    @NotNull
    private final AnalyticsService analyticsService;
    /* access modifiers changed from: private */
    public final Bip70PaymentInteractor bip70PaymentInteractor;
    @Nullable
    private String bip70Url;
    @NotNull
    public BitcoinUriContent bitcoinUriContent;
    @Nullable
    private Coin coin;
    @NotNull
    private final Context context;
    /* access modifiers changed from: private */
    public final CreateTxInteractor createTxInteractor;
    @NotNull
    private Currency defaultCurrency;
    /* access modifiers changed from: private */
    public final GetCurrentExchangeRateInteractor exchangeRateInteractor;
    @NotNull
    private final MutableLiveData<Integer> expiryTimer;
    private final FeeValidatorInteractor feeValidatorInteractor;
    private final GetBalanceByWalletInteractor getBalanceByWalletInteractor;
    private final GetNetworkFeeInteractor getNetworkFeeInteractor;
    @NotNull
    public OnPaymentSuccessListener listener;
    @NotNull
    private MutableLiveData<Boolean> needsBiometricForSend;
    @Nullable
    private Bip70PaymentInfo paymentInfo;
    @Nullable
    private List<Bip70PaymentOutput> paymentTransactionInfo;
    @NotNull
    private MutableLiveData<WalletId> selectedWalletId = new MutableLiveData<>(null);
    /* access modifiers changed from: private */
    public final UtxoRepository utxoService;
    @NotNull
    private final MutableLiveData<ArrayList<Integer>> validationError;
    private final CoroutineScope viewModelScope;
    @Nullable
    private SendWalletData walletData;
    /* access modifiers changed from: private */
    public final GetWalletInteractor walletInteractor;
    private WalletType walletType;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\f\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000e¨\u0006\u000f"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/bip70paymentinforequest/Bip70PaymentInfoRequestPresenter$PaymentState;", "", "layout", "", "(Ljava/lang/String;II)V", "getLayout", "()I", "setLayout", "(I)V", "LOADING", "INVALID", "EXPIRED", "PAYMENT", "BROADCAST_FAILED", "NO_WALLET", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: Bip70PaymentInfoRequestPresenter.kt */
    public enum PaymentState {
        LOADING(C1018R.C1021id.loadingLayout),
        INVALID(C1018R.C1021id.errorInvalidLayout),
        EXPIRED(C1018R.C1021id.errorExpiredLayout),
        PAYMENT(C1018R.C1021id.paymentLayout),
        BROADCAST_FAILED(0),
        NO_WALLET(C1018R.C1021id.errorNoWallet);
        
        private int layout;

        private PaymentState(int i) {
            this.layout = i;
        }

        public final int getLayout() {
            return this.layout;
        }

        public final void setLayout(int i) {
            this.layout = i;
        }
    }

    @Metadata(mo37403bv = {1, 0, 3}, mo37406k = 3, mo37407mv = {1, 1, 15})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[Coin.values().length];

        static {
            $EnumSwitchMapping$0[Coin.BCH.ordinal()] = 1;
            $EnumSwitchMapping$0[Coin.BTC.ordinal()] = 2;
        }
    }

    @NotNull
    public final Context getContext() {
        return this.context;
    }

    @NotNull
    public final AnalyticsService getAnalyticsService() {
        return this.analyticsService;
    }

    public Bip70PaymentInfoRequestPresenter(@NotNull Context context2, @NotNull CoroutineScope coroutineScope, @NotNull CreateTxInteractor createTxInteractor2, @NotNull GetNetworkFeeInteractor getNetworkFeeInteractor2, @NotNull UtxoRepository utxoRepository, @NotNull GetWalletInteractor getWalletInteractor, @NotNull GetBalanceByWalletInteractor getBalanceByWalletInteractor2, @NotNull Bip70PaymentInteractor bip70PaymentInteractor2, @NotNull GetCurrentExchangeRateInteractor getCurrentExchangeRateInteractor, @NotNull GetDefaultCurrencyInteractor getDefaultCurrencyInteractor, @NotNull FeeValidatorInteractor feeValidatorInteractor2, @NotNull AnalyticsService analyticsService2, @NotNull Bip70PaymentInfoRequestRouter bip70PaymentInfoRequestRouter) {
        Intrinsics.checkParameterIsNotNull(context2, "context");
        Intrinsics.checkParameterIsNotNull(coroutineScope, "viewModelScope");
        Intrinsics.checkParameterIsNotNull(createTxInteractor2, "createTxInteractor");
        Intrinsics.checkParameterIsNotNull(getNetworkFeeInteractor2, "getNetworkFeeInteractor");
        Intrinsics.checkParameterIsNotNull(utxoRepository, "utxoService");
        Intrinsics.checkParameterIsNotNull(getWalletInteractor, "walletInteractor");
        Intrinsics.checkParameterIsNotNull(getBalanceByWalletInteractor2, "getBalanceByWalletInteractor");
        Intrinsics.checkParameterIsNotNull(bip70PaymentInteractor2, "bip70PaymentInteractor");
        Intrinsics.checkParameterIsNotNull(getCurrentExchangeRateInteractor, "exchangeRateInteractor");
        Intrinsics.checkParameterIsNotNull(getDefaultCurrencyInteractor, "defaultCurrencyInteractor");
        Intrinsics.checkParameterIsNotNull(feeValidatorInteractor2, "feeValidatorInteractor");
        Intrinsics.checkParameterIsNotNull(analyticsService2, "analyticsService");
        Intrinsics.checkParameterIsNotNull(bip70PaymentInfoRequestRouter, "router");
        super(context2, bip70PaymentInfoRequestRouter);
        this.context = context2;
        this.viewModelScope = coroutineScope;
        this.createTxInteractor = createTxInteractor2;
        this.getNetworkFeeInteractor = getNetworkFeeInteractor2;
        this.utxoService = utxoRepository;
        this.walletInteractor = getWalletInteractor;
        this.getBalanceByWalletInteractor = getBalanceByWalletInteractor2;
        this.bip70PaymentInteractor = bip70PaymentInteractor2;
        this.exchangeRateInteractor = getCurrentExchangeRateInteractor;
        this.feeValidatorInteractor = feeValidatorInteractor2;
        this.analyticsService = analyticsService2;
        Boolean valueOf = Boolean.valueOf(false);
        this._dataLoaded = new MutableLiveData<>(valueOf);
        this._errorState = new MutableLiveData<>(null);
        this._broadcastSuccess = new MutableLiveData<>(Boolean.valueOf(true));
        this.validationError = new MutableLiveData<>(new ArrayList());
        this.defaultCurrency = getDefaultCurrencyInteractor.getDefaultFiatCurrency();
        this.paymentTransactionInfo = CollectionsKt.emptyList();
        this.expiryTimer = new MutableLiveData<>(null);
        this.needsBiometricForSend = new MutableLiveData<>(valueOf);
    }

    @NotNull
    public final MutableLiveData<WalletId> getSelectedWalletId() {
        return this.selectedWalletId;
    }

    public final void setSelectedWalletId(@NotNull MutableLiveData<WalletId> mutableLiveData) {
        Intrinsics.checkParameterIsNotNull(mutableLiveData, "<set-?>");
        this.selectedWalletId = mutableLiveData;
    }

    @NotNull
    public final MutableLiveData<Boolean> get_dataLoaded() {
        return this._dataLoaded;
    }

    @NotNull
    public final MutableLiveData<Bip70PaymentErrorStatus> get_errorState() {
        return this._errorState;
    }

    @NotNull
    public final MutableLiveData<Boolean> get_broadcastSuccess() {
        return this._broadcastSuccess;
    }

    @NotNull
    public final MutableLiveData<ArrayList<Integer>> getValidationError() {
        return this.validationError;
    }

    @Nullable
    public final Coin getCoin() {
        return this.coin;
    }

    public final void setCoin(@Nullable Coin coin2) {
        this.coin = coin2;
    }

    @NotNull
    public final Currency getDefaultCurrency() {
        return this.defaultCurrency;
    }

    public final void setDefaultCurrency(@NotNull Currency currency) {
        Intrinsics.checkParameterIsNotNull(currency, "<set-?>");
        this.defaultCurrency = currency;
    }

    @Nullable
    public final Bip70PaymentInfo getPaymentInfo() {
        return this.paymentInfo;
    }

    public final void setPaymentInfo(@Nullable Bip70PaymentInfo bip70PaymentInfo) {
        this.paymentInfo = bip70PaymentInfo;
    }

    @Nullable
    public final List<Bip70PaymentOutput> getPaymentTransactionInfo() {
        return this.paymentTransactionInfo;
    }

    public final void setPaymentTransactionInfo(@Nullable List<Bip70PaymentOutput> list) {
        this.paymentTransactionInfo = list;
    }

    @Nullable
    public final String getBip70Url() {
        return this.bip70Url;
    }

    public final void setBip70Url(@Nullable String str) {
        this.bip70Url = str;
    }

    @Nullable
    public final SendWalletData getWalletData() {
        return this.walletData;
    }

    public final void setWalletData(@Nullable SendWalletData sendWalletData) {
        this.walletData = sendWalletData;
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
    public final OnPaymentSuccessListener getListener() {
        OnPaymentSuccessListener onPaymentSuccessListener = this.listener;
        if (onPaymentSuccessListener == null) {
            Intrinsics.throwUninitializedPropertyAccessException(CastExtraArgs.LISTENER);
        }
        return onPaymentSuccessListener;
    }

    public final void setListener(@NotNull OnPaymentSuccessListener onPaymentSuccessListener) {
        Intrinsics.checkParameterIsNotNull(onPaymentSuccessListener, "<set-?>");
        this.listener = onPaymentSuccessListener;
    }

    @NotNull
    public final MutableLiveData<Integer> getExpiryTimer() {
        return this.expiryTimer;
    }

    @NotNull
    public final MutableLiveData<Boolean> getNeedsBiometricForSend() {
        return this.needsBiometricForSend;
    }

    public final void setNeedsBiometricForSend(@NotNull MutableLiveData<Boolean> mutableLiveData) {
        Intrinsics.checkParameterIsNotNull(mutableLiveData, "<set-?>");
        this.needsBiometricForSend = mutableLiveData;
    }

    public void onSlideComplete(@NotNull SlideToActView slideToActView) {
        Intrinsics.checkParameterIsNotNull(slideToActView, "view");
        C1261Wallet wallet = (C1261Wallet) BuildersKt__BuildersKt.runBlocking$default(null, new Bip70PaymentInfoRequestPresenter$onSlideComplete$wallet$1(this, null), 1, null);
        if (wallet != null) {
            KeyguardManager keyguardManager = (KeyguardManager) this.context.getSystemService("keyguard");
            if (!wallet.preference().getHasSpendingAuth() || VERSION.SDK_INT <= 23 || keyguardManager == null || !keyguardManager.isDeviceSecure()) {
                onSend();
            } else if (BiometricsInteractor.Companion.canAuthenticateWithBiometrics(this.context)) {
                this.needsBiometricForSend.setValue(Boolean.valueOf(true));
            } else {
                onSend();
            }
        }
    }

    public final void onSend() {
        BuildersKt__Builders_commonKt.launch$default(this.viewModelScope, null, null, new Bip70PaymentInfoRequestPresenter$onSend$1(this, null), 3, null);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00ac  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00c2  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00cd  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00e2  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00eb  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void handleSendResult(boolean r13, com.bitcoin.bitcoink.p008tx.TxId r14) {
        /*
            r12 = this;
            r0 = 0
            if (r13 == 0) goto L_0x00f4
            java.lang.String r13 = r12.bip70Url
            if (r13 != 0) goto L_0x000a
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x000a:
            r1 = r13
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            r13 = 1
            char[] r2 = new char[r13]
            r3 = 47
            r2[r0] = r3
            r3 = 0
            r4 = 0
            r5 = 6
            r6 = 0
            java.util.List r1 = kotlin.text.StringsKt.split$default(r1, r2, r3, r4, r5, r6)
            java.lang.Object r1 = kotlin.collections.CollectionsKt.lastOrNull(r1)
            java.lang.String r1 = (java.lang.String) r1
            java.lang.String r2 = ""
            if (r1 == 0) goto L_0x0027
            goto L_0x0028
        L_0x0027:
            r1 = r2
        L_0x0028:
            com.bitcoin.mwallet.core.models.bip70payment.Bip70PaymentInfo r3 = r12.paymentInfo
            if (r3 != 0) goto L_0x002f
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x002f:
            java.lang.String r3 = r3.getMerchantProcessor()
            java.lang.CharSequence r3 = (java.lang.CharSequence) r3
            int r3 = r3.length()
            if (r3 <= 0) goto L_0x003d
            r3 = 1
            goto L_0x003e
        L_0x003d:
            r3 = 0
        L_0x003e:
            if (r3 == 0) goto L_0x0087
            com.bitcoin.mwallet.core.models.bip70payment.Bip70PaymentInfo r3 = r12.paymentInfo
            if (r3 != 0) goto L_0x0047
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x0047:
            java.lang.String r3 = r3.getMerchantName()
            java.lang.CharSequence r3 = (java.lang.CharSequence) r3
            int r3 = r3.length()
            if (r3 <= 0) goto L_0x0055
            r3 = 1
            goto L_0x0056
        L_0x0055:
            r3 = 0
        L_0x0056:
            if (r3 == 0) goto L_0x0087
            android.content.Context r3 = r12.context
            r4 = 2131951948(0x7f13014c, float:1.9540325E38)
            r5 = 3
            java.lang.Object[] r5 = new java.lang.Object[r5]
            com.bitcoin.mwallet.core.models.bip70payment.Bip70PaymentInfo r6 = r12.paymentInfo
            if (r6 != 0) goto L_0x0067
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x0067:
            java.lang.String r6 = r6.getMerchantProcessor()
            r5[r0] = r6
            r5[r13] = r1
            r0 = 2
            com.bitcoin.mwallet.core.models.bip70payment.Bip70PaymentInfo r1 = r12.paymentInfo
            if (r1 != 0) goto L_0x0077
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x0077:
            java.lang.String r1 = r1.getMerchantName()
            r5[r0] = r1
            java.lang.String r0 = r3.getString(r4, r5)
            java.lang.String r1 = "context.getString(\n     …ame\n                    )"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
            goto L_0x009b
        L_0x0087:
            android.content.Context r1 = r12.context
            r3 = 2131951949(0x7f13014d, float:1.9540327E38)
            java.lang.Object[] r4 = new java.lang.Object[r13]
            java.lang.String r5 = r12.bip70Url
            r4[r0] = r5
            java.lang.String r0 = r1.getString(r3, r4)
            java.lang.String r1 = "context.getString(R.stri…neric_template, bip70Url)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
        L_0x009b:
            r11 = r0
            com.bitcoin.mwallet.app.flows.sendv2.sendamountselection.SendWhatModel r0 = new com.bitcoin.mwallet.app.flows.sendv2.sendamountselection.SendWhatModel
            r4 = 0
            com.bitcoin.bitcoink.tx.Satoshis r1 = r12.getInvoiceCoinAmount()
            java.math.BigDecimal r5 = r1.getCoins()
            r6 = 0
            com.bitcoin.mwallet.app.flows.sendv2.entity.SendWalletData r1 = r12.walletData
            if (r1 != 0) goto L_0x00af
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x00af:
            com.bitcoin.mwallet.core.models.Coin r7 = r1.getCoin()
            java.util.Currency r1 = r12.defaultCurrency
            java.lang.String r8 = r1.getCurrencyCode()
            java.lang.String r1 = "defaultCurrency.currencyCode"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r8, r1)
            com.bitcoin.mwallet.app.flows.sendv2.entity.SendWalletData r1 = r12.walletData
            if (r1 != 0) goto L_0x00c5
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x00c5:
            com.bitcoin.mwallet.core.models.wallet.WalletId r9 = r1.getWalletId()
            com.bitcoin.mwallet.core.models.BitcoinUriContent r10 = r12.bitcoinUriContent
            if (r10 != 0) goto L_0x00d2
            java.lang.String r1 = "bitcoinUriContent"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
        L_0x00d2:
            r3 = r0
            r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11)
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r1 = r12._broadcastSuccess
            java.lang.Boolean r13 = java.lang.Boolean.valueOf(r13)
            r1.postValue(r13)
            if (r14 == 0) goto L_0x00e2
            goto L_0x00e7
        L_0x00e2:
            com.bitcoin.bitcoink.tx.TxId r14 = new com.bitcoin.bitcoink.tx.TxId
            r14.<init>(r2)
        L_0x00e7:
            com.bitcoin.mwallet.app.flows.sendv2.dialogs.bip70paymentinforequest.OnPaymentSuccessListener r13 = r12.listener
            if (r13 != 0) goto L_0x00f0
            java.lang.String r1 = "listener"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
        L_0x00f0:
            r13.onPaymentSuccessListener(r14, r0)
            goto L_0x00fd
        L_0x00f4:
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r13 = r12._broadcastSuccess
            java.lang.Boolean r14 = java.lang.Boolean.valueOf(r0)
            r13.postValue(r14)
        L_0x00fd:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.app.flows.sendv2.dialogs.bip70paymentinforequest.Bip70PaymentInfoRequestPresenter.handleSendResult(boolean, com.bitcoin.bitcoink.tx.TxId):void");
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ java.lang.Object getSatoshiPerByte(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.math.BigDecimal> r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof com.bitcoin.mwallet.app.flows.sendv2.dialogs.bip70paymentinforequest.Bip70PaymentInfoRequestPresenter$getSatoshiPerByte$1
            if (r0 == 0) goto L_0x0014
            r0 = r5
            com.bitcoin.mwallet.app.flows.sendv2.dialogs.bip70paymentinforequest.Bip70PaymentInfoRequestPresenter$getSatoshiPerByte$1 r0 = (com.bitcoin.mwallet.app.flows.sendv2.dialogs.bip70paymentinforequest.Bip70PaymentInfoRequestPresenter$getSatoshiPerByte$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r5 = r0.label
            int r5 = r5 - r2
            r0.label = r5
            goto L_0x0019
        L_0x0014:
            com.bitcoin.mwallet.app.flows.sendv2.dialogs.bip70paymentinforequest.Bip70PaymentInfoRequestPresenter$getSatoshiPerByte$1 r0 = new com.bitcoin.mwallet.app.flows.sendv2.dialogs.bip70paymentinforequest.Bip70PaymentInfoRequestPresenter$getSatoshiPerByte$1
            r0.<init>(r4, r5)
        L_0x0019:
            java.lang.Object r5 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0036
            if (r2 != r3) goto L_0x002e
            java.lang.Object r0 = r0.L$0
            com.bitcoin.mwallet.app.flows.sendv2.dialogs.bip70paymentinforequest.Bip70PaymentInfoRequestPresenter r0 = (com.bitcoin.mwallet.app.flows.sendv2.dialogs.bip70paymentinforequest.Bip70PaymentInfoRequestPresenter) r0
            kotlin.ResultKt.throwOnFailure(r5)
            goto L_0x004d
        L_0x002e:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L_0x0036:
            kotlin.ResultKt.throwOnFailure(r5)
            com.bitcoin.mwallet.core.interactors.GetNetworkFeeInteractor r5 = r4.getNetworkFeeInteractor
            com.bitcoin.mwallet.core.models.Coin r2 = r4.coin
            if (r2 != 0) goto L_0x0042
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x0042:
            r0.L$0 = r4
            r0.label = r3
            java.lang.Object r5 = r5.getNetworkFeeAndPreference(r2, r0)
            if (r5 != r1) goto L_0x004d
            return r1
        L_0x004d:
            kotlin.Pair r5 = (kotlin.Pair) r5
            java.lang.Object r0 = r5.getFirst()
            com.bitcoin.mwallet.core.models.networkfee.NetworkFees r0 = (com.bitcoin.mwallet.core.models.networkfee.NetworkFees) r0
            java.lang.Object r5 = r5.getSecond()
            com.bitcoin.mwallet.core.models.networkfee.NetworkFeeLevel r5 = (com.bitcoin.mwallet.core.models.networkfee.NetworkFeeLevel) r5
            java.math.BigDecimal r5 = r0.getFee(r5)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.app.flows.sendv2.dialogs.bip70paymentinforequest.Bip70PaymentInfoRequestPresenter.getSatoshiPerByte(kotlin.coroutines.Continuation):java.lang.Object");
    }

    @NotNull
    public final LiveData<PaymentState> getPaymentState() {
        return LiveDataKt.combineLatestIgnoreNull(this._dataLoaded, this._errorState, this._broadcastSuccess, this.selectedWalletId, Bip70PaymentInfoRequestPresenter$getPaymentState$1.INSTANCE);
    }

    public final void setWalletData(@NotNull WalletId walletId) {
        Intrinsics.checkParameterIsNotNull(walletId, "walletId");
        C1261Wallet wallet = (C1261Wallet) BuildersKt__BuildersKt.runBlocking$default(null, new Bip70PaymentInfoRequestPresenter$setWalletData$wallet$1(this, walletId, null), 1, null);
        if (wallet != null) {
            this.walletData = (SendWalletData) BuildersKt__BuildersKt.runBlocking$default(null, new Bip70PaymentInfoRequestPresenter$setWalletData$1(this, wallet, null), 1, null);
        }
    }

    @NotNull
    public final Satoshis getInvoiceCoinAmount() {
        Long l;
        List<Bip70PaymentOutput> list = this.paymentTransactionInfo;
        if (list != null) {
            long j = 0;
            for (Bip70PaymentOutput amount : list) {
                j += amount.getAmount();
            }
            l = Long.valueOf(j);
        } else {
            l = null;
        }
        if (l != null) {
            return new Satoshis(l.longValue());
        }
        return new Satoshis(0);
    }

    @Nullable
    public final BigDecimal getInvoiceFiatAmount() {
        Satoshis invoiceCoinAmount = getInvoiceCoinAmount();
        if (this.coin == null) {
            return null;
        }
        FiatExchangeRate fiatExchangeRate = (FiatExchangeRate) BuildersKt__BuildersKt.runBlocking$default(null, new Bip70PaymentInfoRequestPresenter$getInvoiceFiatAmount$rate$1(this, null), 1, null);
        if (fiatExchangeRate != null) {
            return fiatExchangeRate.toFiat(invoiceCoinAmount);
        }
        return null;
    }

    public final void init(@NotNull BitcoinUriContent bitcoinUriContent2, @NotNull OnPaymentSuccessListener onPaymentSuccessListener, @Nullable WalletId walletId) {
        WalletType walletType2;
        Intrinsics.checkParameterIsNotNull(bitcoinUriContent2, "bitcoinUriContent");
        Intrinsics.checkParameterIsNotNull(onPaymentSuccessListener, "paymentListener");
        this.listener = onPaymentSuccessListener;
        this.bitcoinUriContent = bitcoinUriContent2;
        this.bip70Url = bitcoinUriContent2.getUrl();
        Coin coin2 = bitcoinUriContent2.getCoin();
        if (coin2 == null) {
            walletType2 = null;
        } else {
            int i = WhenMappings.$EnumSwitchMapping$0[coin2.ordinal()];
            if (i == 1) {
                walletType2 = WalletType.BCH;
            } else if (i == 2) {
                walletType2 = WalletType.BTC;
            } else {
                throw new NoWhenBranchMatchedException();
            }
        }
        this.walletType = walletType2;
        if (bitcoinUriContent2.getCoin() != null) {
            List list = (List) BuildersKt__BuildersKt.runBlocking$default(null, new Bip70PaymentInfoRequestPresenter$init$defaultWalletId$1(this, bitcoinUriContent2, null), 1, null);
            if (walletId != null) {
                this.selectedWalletId.setValue(walletId);
            } else {
                this.selectedWalletId.setValue(CollectionsKt.firstOrNull(list));
            }
            this.coin = bitcoinUriContent2.getCoin();
        }
        BuildersKt__Builders_commonKt.launch$default(this.viewModelScope, null, null, new Bip70PaymentInfoRequestPresenter$init$1(this, bitcoinUriContent2, null), 3, null);
    }

    /* access modifiers changed from: private */
    public final void startTimer(long j, long j2) {
        long currentTimeMillis = System.currentTimeMillis();
        DoubleRef doubleRef = new DoubleRef();
        doubleRef.element = (double) (j2 - currentTimeMillis);
        double d = (double) (j2 - j);
        CoroutineScope coroutineScope = this.viewModelScope;
        Bip70PaymentInfoRequestPresenter$startTimer$1 bip70PaymentInfoRequestPresenter$startTimer$1 = new Bip70PaymentInfoRequestPresenter$startTimer$1(this, doubleRef, d, null);
        BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, bip70PaymentInfoRequestPresenter$startTimer$1, 3, null);
    }

    @Nullable
    public final C1261Wallet getWallet(@NotNull WalletId walletId) {
        Intrinsics.checkParameterIsNotNull(walletId, "walletId");
        return (C1261Wallet) BuildersKt__BuildersKt.runBlocking$default(null, new Bip70PaymentInfoRequestPresenter$getWallet$1(this, walletId, null), 1, null);
    }

    public void onWalletSelected(@NotNull WalletId walletId, @NotNull WalletType walletType2) {
        Intrinsics.checkParameterIsNotNull(walletId, "walletId");
        Intrinsics.checkParameterIsNotNull(walletType2, "walletType");
        this.selectedWalletId.setValue(walletId);
    }

    public final void toWalletSelection(@NotNull FragmentManager fragmentManager) {
        Intrinsics.checkParameterIsNotNull(fragmentManager, "fm");
        ((Bip70PaymentInfoRequestRouter) getRouter()).toWalletSelect(this, fragmentManager, this.walletType);
    }

    public final boolean isInsufficientFee() {
        if (this.coin == null || this.paymentTransactionInfo == null) {
            return false;
        }
        FeeValidatorInteractor feeValidatorInteractor2 = this.feeValidatorInteractor;
        Object value = this.selectedWalletId.getValue();
        if (value == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(value, "selectedWalletId.value!!");
        WalletId walletId = (WalletId) value;
        BigDecimal coins = getInvoiceCoinAmount().getCoins();
        Coin coin2 = this.coin;
        if (coin2 == null) {
            Intrinsics.throwNpe();
        }
        List<Bip70PaymentOutput> list = this.paymentTransactionInfo;
        if (list == null) {
            Intrinsics.throwNpe();
        }
        return FeeValidatorInteractor.isInsufficientCoinFee$default(feeValidatorInteractor2, walletId, coins, coin2, Integer.valueOf(list.size()), 0, 16, null);
    }

    @NotNull
    public final LiveData<WalletInfoBalance> getWalletBalance() {
        return LiveDataKt.combineLatestIgnoreNull(this.selectedWalletId, GetBalanceByWalletInteractor.walletBalances$default(this.getBalanceByWalletInteractor, false, 1, null), Bip70PaymentInfoRequestPresenter$getWalletBalance$1.INSTANCE);
    }
}
