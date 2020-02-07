package com.bitcoin.mwallet.app.flows.sendv2.review;

import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Context;
import android.os.Build.VERSION;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.bitcoin.bitcoink.p008tx.Satoshis;
import com.bitcoin.bitcoink.p008tx.TxId;
import com.bitcoin.mwallet.app.android.Event;
import com.bitcoin.mwallet.app.flows.sendv2.entity.SendWalletData;
import com.bitcoin.mwallet.app.flows.sendv2.sendamountselection.SendWhatModel;
import com.bitcoin.mwallet.app.viper.ScreenPresenter;
import com.bitcoin.mwallet.core.interactors.BiometricsInteractor;
import com.bitcoin.mwallet.core.interactors.CreateTxInteractor;
import com.bitcoin.mwallet.core.interactors.GetBalanceByWalletInteractor;
import com.bitcoin.mwallet.core.interactors.GetCurrentExchangeRateInteractor;
import com.bitcoin.mwallet.core.interactors.GetNetworkFeeInteractor;
import com.bitcoin.mwallet.core.interactors.GetWalletInteractor;
import com.bitcoin.mwallet.core.models.BitcoinUriContent;
import com.bitcoin.mwallet.core.models.Coin;
import com.bitcoin.mwallet.core.models.p009tx.slputxo.SlpUtxoSelection;
import com.bitcoin.mwallet.core.models.p009tx.utxo.UtxoSelection.Error;
import com.bitcoin.mwallet.core.models.slp.Slp;
import com.bitcoin.mwallet.core.models.slp.SlpId;
import com.bitcoin.mwallet.core.models.wallet.C1261Wallet;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import com.bitcoin.mwallet.core.models.wallet.WalletInfoBalance;
import com.bitcoin.mwallet.core.preferences.WalletPreference;
import com.bitcoin.mwallet.core.repositories.SlpRepository;
import com.bitcoin.mwallet.core.repositories.UtxoRepository;
import com.bitcoin.mwallet.zion.ZionRepository;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import com.ncorti.slidetoact.SlideToActView;
import com.ncorti.slidetoact.SlideToActView.OnSlideCompleteListener;
import java.math.BigDecimal;
import java.util.Currency;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import timber.log.Timber;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000î\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B]\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\u0006\u0010\u0014\u001a\u00020\u0015\u0012\u0006\u0010\u0016\u001a\u00020\u0017\u0012\u0006\u0010\u0018\u001a\u00020\u0002¢\u0006\u0002\u0010\u0019J\u0019\u0010x\u001a\u00020y2\u0006\u0010z\u001a\u00020QH@ø\u0001\u0000¢\u0006\u0002\u0010{J\u0013\u0010|\u001a\u0004\u0018\u00010[H@ø\u0001\u0000¢\u0006\u0002\u0010}J\u000f\u0010~\u001a\u00020y2\u0007\u0010\u001a\u00030\u0001J\u0007\u0010\u0001\u001a\u00020yJ\u0011\u0010\u0001\u001a\u00020y2\b\u0010\u0001\u001a\u00030\u0001J\u0007\u0010\u0001\u001a\u00020yJ\u0007\u0010\u0001\u001a\u00020yJ\u0013\u0010\u0001\u001a\u00020y2\b\u0010\u0001\u001a\u00030\u0001H\u0016J\t\u0010\u0001\u001a\u00020yH\u0002J\t\u0010\u0001\u001a\u00020yH\u0002J\u001c\u0010\u0001\u001a\u00020y2\u0007\u0010\u0001\u001a\u00020[H@ø\u0001\u0000¢\u0006\u0003\u0010\u0001J\u001c\u0010\u0001\u001a\u00020y2\u0007\u0010\u0001\u001a\u00020[H@ø\u0001\u0000¢\u0006\u0003\u0010\u0001J\u001b\u0010\u0001\u001a\u00020y2\u0007\u0010\u0001\u001a\u00020QH@ø\u0001\u0000¢\u0006\u0002\u0010{J\u001b\u0010v\u001a\u00020y2\u0007\u0010\u0001\u001a\u00020sH@ø\u0001\u0000¢\u0006\u0003\u0010\u0001J\u0011\u0010\u0001\u001a\u00020y2\b\u0010\u0001\u001a\u00030\u0001R \u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u001a\u0010!\u001a\u00020\"X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\"\u0010)\u001a\n +*\u0004\u0018\u00010*0*X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R\u001a\u00100\u001a\u000201X.¢\u0006\u000e\n\u0000\u001a\u0004\b2\u00103\"\u0004\b4\u00105R\u001f\u00106\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u000209\u0018\u00010807¢\u0006\b\n\u0000\u001a\u0004\b:\u0010;R\u000e\u0010\f\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u001a\u0010<\u001a\u00020=X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b>\u0010?\"\u0004\b@\u0010AR\u001a\u0010B\u001a\u00020=X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bB\u0010?\"\u0004\bC\u0010AR \u0010D\u001a\b\u0012\u0004\u0012\u00020=07X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bD\u0010;\"\u0004\bE\u0010FR(\u0010G\u001a\u0010\u0012\f\u0012\n +*\u0004\u0018\u00010=0=07X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bH\u0010;\"\u0004\bI\u0010FR \u0010J\u001a\b\u0012\u0004\u0012\u00020907X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bK\u0010;\"\u0004\bL\u0010FR \u0010M\u001a\b\u0012\u0004\u0012\u00020907X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bN\u0010;\"\u0004\bO\u0010FR\u000e\u0010P\u001a\u00020QX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010R\u001a\u0004\u0018\u00010SX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0004¢\u0006\u0002\n\u0000R\u001c\u0010T\u001a\u0004\u0018\u00010UX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bV\u0010W\"\u0004\bX\u0010YR\u001c\u0010Z\u001a\u0004\u0018\u00010[X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\\\u0010]\"\u0004\b^\u0010_R\u001c\u0010`\u001a\u0004\u0018\u00010aX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bb\u0010c\"\u0004\bd\u0010eR\u000e\u0010\u0012\u001a\u00020\u0013X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\bf\u0010gR \u0010h\u001a\b\u0012\u0004\u0012\u00020i07X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bj\u0010;\"\u0004\bk\u0010FR\u000e\u0010\u0010\u001a\u00020\u0011X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010l\u001a\u00020mX.¢\u0006\u000e\n\u0000\u001a\u0004\bn\u0010o\"\u0004\bp\u0010qR\u001a\u0010r\u001a\u00020sX.¢\u0006\u000e\n\u0000\u001a\u0004\bt\u0010u\"\u0004\bv\u0010wR\u000e\u0010\u0016\u001a\u00020\u0017X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u0001"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/sendv2/review/ReviewPresenter;", "Lcom/bitcoin/mwallet/app/viper/ScreenPresenter;", "Lcom/bitcoin/mwallet/app/flows/sendv2/review/ReviewRouter;", "Lcom/ncorti/slidetoact/SlideToActView$OnSlideCompleteListener;", "context", "Landroid/content/Context;", "viewModelScope", "Lkotlinx/coroutines/CoroutineScope;", "createTxinteractor", "Lcom/bitcoin/mwallet/core/interactors/CreateTxInteractor;", "getWalletInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetWalletInteractor;", "exchangeRateInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetCurrentExchangeRateInteractor;", "getNetworkFeeInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetNetworkFeeInteractor;", "walletBalanceInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetBalanceByWalletInteractor;", "utxoRepository", "Lcom/bitcoin/mwallet/core/repositories/UtxoRepository;", "slpRepository", "Lcom/bitcoin/mwallet/core/repositories/SlpRepository;", "zionRepository", "Lcom/bitcoin/mwallet/zion/ZionRepository;", "router", "(Landroid/content/Context;Lkotlinx/coroutines/CoroutineScope;Lcom/bitcoin/mwallet/core/interactors/CreateTxInteractor;Lcom/bitcoin/mwallet/core/interactors/GetWalletInteractor;Lcom/bitcoin/mwallet/core/interactors/GetCurrentExchangeRateInteractor;Lcom/bitcoin/mwallet/core/interactors/GetNetworkFeeInteractor;Lcom/bitcoin/mwallet/core/interactors/GetBalanceByWalletInteractor;Lcom/bitcoin/mwallet/core/repositories/UtxoRepository;Lcom/bitcoin/mwallet/core/repositories/SlpRepository;Lcom/bitcoin/mwallet/zion/ZionRepository;Lcom/bitcoin/mwallet/app/flows/sendv2/review/ReviewRouter;)V", "balance", "Landroidx/lifecycle/LiveData;", "Lcom/bitcoin/mwallet/core/models/wallet/WalletInfoBalance;", "getBalance", "()Landroidx/lifecycle/LiveData;", "setBalance", "(Landroidx/lifecycle/LiveData;)V", "coin", "Lcom/bitcoin/mwallet/core/models/Coin;", "getCoin", "()Lcom/bitcoin/mwallet/core/models/Coin;", "setCoin", "(Lcom/bitcoin/mwallet/core/models/Coin;)V", "getContext", "()Landroid/content/Context;", "currency", "Ljava/util/Currency;", "kotlin.jvm.PlatformType", "getCurrency", "()Ljava/util/Currency;", "setCurrency", "(Ljava/util/Currency;)V", "destinationAddress", "Lcom/bitcoin/mwallet/core/models/BitcoinUriContent;", "getDestinationAddress", "()Lcom/bitcoin/mwallet/core/models/BitcoinUriContent;", "setDestinationAddress", "(Lcom/bitcoin/mwallet/core/models/BitcoinUriContent;)V", "error", "Landroidx/lifecycle/MutableLiveData;", "Lcom/bitcoin/mwallet/app/android/Event;", "", "getError", "()Landroidx/lifecycle/MutableLiveData;", "hasZion", "", "getHasZion", "()Z", "setHasZion", "(Z)V", "isSendingFiat", "setSendingFiat", "isSendingToken", "setSendingToken", "(Landroidx/lifecycle/MutableLiveData;)V", "needsBiometricForSend", "getNeedsBiometricForSend", "setNeedsBiometricForSend", "networkFeeText", "getNetworkFeeText", "setNetworkFeeText", "secondaryAmountValue", "getSecondaryAmountValue", "setSecondaryAmountValue", "sendAmountSatoshis", "Lcom/bitcoin/bitcoink/tx/Satoshis;", "sendJob", "Lkotlinx/coroutines/Job;", "token", "Lcom/bitcoin/mwallet/core/models/slp/Slp;", "getToken", "()Lcom/bitcoin/mwallet/core/models/slp/Slp;", "setToken", "(Lcom/bitcoin/mwallet/core/models/slp/Slp;)V", "tokenAmount", "Ljava/math/BigDecimal;", "getTokenAmount", "()Ljava/math/BigDecimal;", "setTokenAmount", "(Ljava/math/BigDecimal;)V", "tokenId", "Lcom/bitcoin/mwallet/core/models/slp/SlpId;", "getTokenId", "()Lcom/bitcoin/mwallet/core/models/slp/SlpId;", "setTokenId", "(Lcom/bitcoin/mwallet/core/models/slp/SlpId;)V", "getViewModelScope", "()Lkotlinx/coroutines/CoroutineScope;", "wallet", "Lcom/bitcoin/mwallet/core/models/wallet/Wallet;", "getWallet", "setWallet", "walletData", "Lcom/bitcoin/mwallet/app/flows/sendv2/entity/SendWalletData;", "getWalletData", "()Lcom/bitcoin/mwallet/app/flows/sendv2/entity/SendWalletData;", "setWalletData", "(Lcom/bitcoin/mwallet/app/flows/sendv2/entity/SendWalletData;)V", "walletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "getWalletId", "()Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "setWalletId", "(Lcom/bitcoin/mwallet/core/models/wallet/WalletId;)V", "calculateFeeAmount", "", "satoshis", "(Lcom/bitcoin/bitcoink/tx/Satoshis;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getSatoshiPerByte", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "initSendData", "sendData", "Lcom/bitcoin/mwallet/app/flows/sendv2/sendamountselection/SendWhatModel;", "onBackPressed", "onClosePressed", "activity", "Landroid/app/Activity;", "onSend", "onSendingWalletSelected", "onSlideComplete", "view", "Lcom/ncorti/slidetoact/SlideToActView;", "sendNativeCoin", "sendToken", "setCryptoSendAmount", "cryptoSendAmount", "(Ljava/math/BigDecimal;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setFiatSendAmount", "fiatSendAmount", "setNetworkFee", "fee", "id", "(Lcom/bitcoin/mwallet/core/models/wallet/WalletId;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toSuccess", "txid", "Lcom/bitcoin/bitcoink/tx/TxId;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: ReviewPresenter.kt */
public final class ReviewPresenter extends ScreenPresenter<ReviewRouter> implements OnSlideCompleteListener {
    @NotNull
    private LiveData<WalletInfoBalance> balance = new MutableLiveData();
    @NotNull
    private Coin coin = Coin.BCH;
    @NotNull
    private final Context context;
    /* access modifiers changed from: private */
    public final CreateTxInteractor createTxinteractor;
    private Currency currency = Currency.getInstance("USD");
    @NotNull
    public BitcoinUriContent destinationAddress;
    @NotNull
    private final MutableLiveData<Event<String>> error = new MutableLiveData<>();
    private final GetCurrentExchangeRateInteractor exchangeRateInteractor;
    private final GetNetworkFeeInteractor getNetworkFeeInteractor;
    /* access modifiers changed from: private */
    public final GetWalletInteractor getWalletInteractor;
    private boolean hasZion;
    private boolean isSendingFiat = true;
    @NotNull
    private MutableLiveData<Boolean> isSendingToken;
    @NotNull
    private MutableLiveData<Boolean> needsBiometricForSend;
    @NotNull
    private MutableLiveData<String> networkFeeText = new MutableLiveData<>();
    @NotNull
    private MutableLiveData<String> secondaryAmountValue = new MutableLiveData<>();
    /* access modifiers changed from: private */
    public Satoshis sendAmountSatoshis = new Satoshis(546);
    private Job sendJob;
    /* access modifiers changed from: private */
    public final SlpRepository slpRepository;
    @Nullable
    private Slp token;
    @Nullable
    private BigDecimal tokenAmount;
    @Nullable
    private SlpId tokenId;
    /* access modifiers changed from: private */
    public final UtxoRepository utxoRepository;
    @NotNull
    private final CoroutineScope viewModelScope;
    @NotNull
    private MutableLiveData<C1261Wallet> wallet = new MutableLiveData<>();
    private final GetBalanceByWalletInteractor walletBalanceInteractor;
    @NotNull
    public SendWalletData walletData;
    @NotNull
    public WalletId walletId;
    /* access modifiers changed from: private */
    public final ZionRepository zionRepository;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, mo37405d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
    @DebugMetadata(mo37999c = "com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter$1", mo38000f = "ReviewPresenter.kt", mo38001i = {}, mo38002l = {101}, mo38003m = "invokeSuspend", mo38004n = {}, mo38005s = {})
    /* renamed from: com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter$1 */
    /* compiled from: ReviewPresenter.kt */
    static final class C11051 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        int label;

        /* renamed from: p$ */
        private CoroutineScope f284p$;
        final /* synthetic */ ReviewPresenter this$0;

        {
            this.this$0 = r1;
        }

        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            Intrinsics.checkParameterIsNotNull(continuation, "completion");
            C11051 r0 = new C11051(this.this$0, continuation);
            r0.f284p$ = (CoroutineScope) obj;
            return r0;
        }

        public final Object invoke(Object obj, Object obj2) {
            return ((C11051) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            ReviewPresenter reviewPresenter;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.f284p$;
                ReviewPresenter reviewPresenter2 = this.this$0;
                ZionRepository access$getZionRepository$p = reviewPresenter2.zionRepository;
                this.L$0 = reviewPresenter2;
                this.label = 1;
                Object hasZion = access$getZionRepository$p.hasZion(this);
                if (hasZion == coroutine_suspended) {
                    return coroutine_suspended;
                }
                reviewPresenter = reviewPresenter2;
                obj = hasZion;
            } else if (i == 1) {
                reviewPresenter = (ReviewPresenter) this.L$0;
                ResultKt.throwOnFailure(obj);
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            reviewPresenter.setHasZion(((Boolean) obj).booleanValue());
            return Unit.INSTANCE;
        }
    }

    @Metadata(mo37403bv = {1, 0, 3}, mo37406k = 3, mo37407mv = {1, 1, 15})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[Error.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1 = new int[SlpUtxoSelection.Error.values().length];

        static {
            $EnumSwitchMapping$0[Error.INSUFFICIENT_FUNDS.ordinal()] = 1;
            $EnumSwitchMapping$1[SlpUtxoSelection.Error.INSUFFICIENT_FUNDS.ordinal()] = 1;
        }
    }

    @NotNull
    public final Context getContext() {
        return this.context;
    }

    @NotNull
    public final CoroutineScope getViewModelScope() {
        return this.viewModelScope;
    }

    public ReviewPresenter(@NotNull Context context2, @NotNull CoroutineScope coroutineScope, @NotNull CreateTxInteractor createTxInteractor, @NotNull GetWalletInteractor getWalletInteractor2, @NotNull GetCurrentExchangeRateInteractor getCurrentExchangeRateInteractor, @NotNull GetNetworkFeeInteractor getNetworkFeeInteractor2, @NotNull GetBalanceByWalletInteractor getBalanceByWalletInteractor, @NotNull UtxoRepository utxoRepository2, @NotNull SlpRepository slpRepository2, @NotNull ZionRepository zionRepository2, @NotNull ReviewRouter reviewRouter) {
        Intrinsics.checkParameterIsNotNull(context2, "context");
        Intrinsics.checkParameterIsNotNull(coroutineScope, "viewModelScope");
        Intrinsics.checkParameterIsNotNull(createTxInteractor, "createTxinteractor");
        Intrinsics.checkParameterIsNotNull(getWalletInteractor2, "getWalletInteractor");
        Intrinsics.checkParameterIsNotNull(getCurrentExchangeRateInteractor, "exchangeRateInteractor");
        Intrinsics.checkParameterIsNotNull(getNetworkFeeInteractor2, "getNetworkFeeInteractor");
        Intrinsics.checkParameterIsNotNull(getBalanceByWalletInteractor, "walletBalanceInteractor");
        Intrinsics.checkParameterIsNotNull(utxoRepository2, "utxoRepository");
        Intrinsics.checkParameterIsNotNull(slpRepository2, "slpRepository");
        Intrinsics.checkParameterIsNotNull(zionRepository2, "zionRepository");
        Intrinsics.checkParameterIsNotNull(reviewRouter, "router");
        super(context2, reviewRouter);
        this.context = context2;
        this.viewModelScope = coroutineScope;
        this.createTxinteractor = createTxInteractor;
        this.getWalletInteractor = getWalletInteractor2;
        this.exchangeRateInteractor = getCurrentExchangeRateInteractor;
        this.getNetworkFeeInteractor = getNetworkFeeInteractor2;
        this.walletBalanceInteractor = getBalanceByWalletInteractor;
        this.utxoRepository = utxoRepository2;
        this.slpRepository = slpRepository2;
        this.zionRepository = zionRepository2;
        Boolean valueOf = Boolean.valueOf(false);
        this.isSendingToken = new MutableLiveData<>(valueOf);
        this.needsBiometricForSend = new MutableLiveData<>(valueOf);
        BuildersKt__Builders_commonKt.launch$default(this.viewModelScope, null, null, new C11051(this, null), 3, null);
    }

    public void onSlideComplete(@NotNull SlideToActView slideToActView) {
        Intrinsics.checkParameterIsNotNull(slideToActView, "view");
        KeyguardManager keyguardManager = (KeyguardManager) this.context.getSystemService("keyguard");
        C1261Wallet wallet2 = (C1261Wallet) this.wallet.getValue();
        if (wallet2 != null) {
            WalletPreference preference = wallet2.preference();
            if (preference != null && preference.getHasSpendingAuth() && VERSION.SDK_INT > 23 && keyguardManager != null && keyguardManager.isDeviceSecure() && !this.hasZion) {
                if (BiometricsInteractor.Companion.canAuthenticateWithBiometrics(this.context)) {
                    this.needsBiometricForSend.setValue(Boolean.valueOf(true));
                    return;
                } else {
                    onSend();
                    return;
                }
            }
        }
        onSend();
    }

    @NotNull
    public final MutableLiveData<Event<String>> getError() {
        return this.error;
    }

    @NotNull
    public final BitcoinUriContent getDestinationAddress() {
        BitcoinUriContent bitcoinUriContent = this.destinationAddress;
        if (bitcoinUriContent == null) {
            Intrinsics.throwUninitializedPropertyAccessException("destinationAddress");
        }
        return bitcoinUriContent;
    }

    public final void setDestinationAddress(@NotNull BitcoinUriContent bitcoinUriContent) {
        Intrinsics.checkParameterIsNotNull(bitcoinUriContent, "<set-?>");
        this.destinationAddress = bitcoinUriContent;
    }

    public final boolean isSendingFiat() {
        return this.isSendingFiat;
    }

    public final void setSendingFiat(boolean z) {
        this.isSendingFiat = z;
    }

    @NotNull
    public final Coin getCoin() {
        return this.coin;
    }

    public final void setCoin(@NotNull Coin coin2) {
        Intrinsics.checkParameterIsNotNull(coin2, "<set-?>");
        this.coin = coin2;
    }

    public final Currency getCurrency() {
        return this.currency;
    }

    public final void setCurrency(Currency currency2) {
        this.currency = currency2;
    }

    @NotNull
    public final MutableLiveData<String> getSecondaryAmountValue() {
        return this.secondaryAmountValue;
    }

    public final void setSecondaryAmountValue(@NotNull MutableLiveData<String> mutableLiveData) {
        Intrinsics.checkParameterIsNotNull(mutableLiveData, "<set-?>");
        this.secondaryAmountValue = mutableLiveData;
    }

    @NotNull
    public final MutableLiveData<String> getNetworkFeeText() {
        return this.networkFeeText;
    }

    public final void setNetworkFeeText(@NotNull MutableLiveData<String> mutableLiveData) {
        Intrinsics.checkParameterIsNotNull(mutableLiveData, "<set-?>");
        this.networkFeeText = mutableLiveData;
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
    public final SendWalletData getWalletData() {
        SendWalletData sendWalletData = this.walletData;
        if (sendWalletData == null) {
            Intrinsics.throwUninitializedPropertyAccessException("walletData");
        }
        return sendWalletData;
    }

    public final void setWalletData(@NotNull SendWalletData sendWalletData) {
        Intrinsics.checkParameterIsNotNull(sendWalletData, "<set-?>");
        this.walletData = sendWalletData;
    }

    @NotNull
    public final MutableLiveData<C1261Wallet> getWallet() {
        return this.wallet;
    }

    public final void setWallet(@NotNull MutableLiveData<C1261Wallet> mutableLiveData) {
        Intrinsics.checkParameterIsNotNull(mutableLiveData, "<set-?>");
        this.wallet = mutableLiveData;
    }

    @NotNull
    public final LiveData<WalletInfoBalance> getBalance() {
        return this.balance;
    }

    public final void setBalance(@NotNull LiveData<WalletInfoBalance> liveData) {
        Intrinsics.checkParameterIsNotNull(liveData, "<set-?>");
        this.balance = liveData;
    }

    @Nullable
    public final BigDecimal getTokenAmount() {
        return this.tokenAmount;
    }

    public final void setTokenAmount(@Nullable BigDecimal bigDecimal) {
        this.tokenAmount = bigDecimal;
    }

    @Nullable
    public final SlpId getTokenId() {
        return this.tokenId;
    }

    public final void setTokenId(@Nullable SlpId slpId) {
        this.tokenId = slpId;
    }

    @Nullable
    public final Slp getToken() {
        return this.token;
    }

    public final void setToken(@Nullable Slp slp) {
        this.token = slp;
    }

    @NotNull
    public final MutableLiveData<Boolean> isSendingToken() {
        return this.isSendingToken;
    }

    public final void setSendingToken(@NotNull MutableLiveData<Boolean> mutableLiveData) {
        Intrinsics.checkParameterIsNotNull(mutableLiveData, "<set-?>");
        this.isSendingToken = mutableLiveData;
    }

    @NotNull
    public final MutableLiveData<Boolean> getNeedsBiometricForSend() {
        return this.needsBiometricForSend;
    }

    public final void setNeedsBiometricForSend(@NotNull MutableLiveData<Boolean> mutableLiveData) {
        Intrinsics.checkParameterIsNotNull(mutableLiveData, "<set-?>");
        this.needsBiometricForSend = mutableLiveData;
    }

    public final boolean getHasZion() {
        return this.hasZion;
    }

    public final void setHasZion(boolean z) {
        this.hasZion = z;
    }

    public final void toSuccess(@NotNull TxId txId) {
        Intrinsics.checkParameterIsNotNull(txId, "txid");
        ReviewRouter reviewRouter = (ReviewRouter) getRouter();
        BigDecimal coins = this.sendAmountSatoshis.getCoins();
        BigDecimal bigDecimal = this.tokenAmount;
        Coin coin2 = this.coin;
        Currency currency2 = this.currency;
        Intrinsics.checkExpressionValueIsNotNull(currency2, Param.CURRENCY);
        String currencyCode = currency2.getCurrencyCode();
        Intrinsics.checkExpressionValueIsNotNull(currencyCode, "currency.currencyCode");
        Object value = this.wallet.getValue();
        if (value == null) {
            Intrinsics.throwNpe();
        }
        WalletId id = ((C1261Wallet) value).getId();
        BitcoinUriContent bitcoinUriContent = this.destinationAddress;
        if (bitcoinUriContent == null) {
            Intrinsics.throwUninitializedPropertyAccessException("destinationAddress");
        }
        SendWhatModel sendWhatModel = new SendWhatModel(null, coins, bigDecimal, coin2, currencyCode, id, bitcoinUriContent, null, 128, null);
        reviewRouter.toSuccess(txId, sendWhatModel);
    }

    public final void onSendingWalletSelected() {
        ((ReviewRouter) getRouter()).toSendingWalletSelect();
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x004d  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0072  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0097 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0098 A[PHI: r9 
      PHI: (r9v2 java.lang.Object) = (r9v5 java.lang.Object), (r9v1 java.lang.Object) binds: [B:22:0x0095, B:10:0x0029] A[DONT_GENERATE, DONT_INLINE], RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object setFiatSendAmount(@org.jetbrains.annotations.NotNull java.math.BigDecimal r8, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r9) {
        /*
            r7 = this;
            boolean r0 = r9 instanceof com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter$setFiatSendAmount$1
            if (r0 == 0) goto L_0x0014
            r0 = r9
            com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter$setFiatSendAmount$1 r0 = (com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter$setFiatSendAmount$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L_0x0019
        L_0x0014:
            com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter$setFiatSendAmount$1 r0 = new com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter$setFiatSendAmount$1
            r0.<init>(r7, r9)
        L_0x0019:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x004d
            if (r2 == r4) goto L_0x0041
            if (r2 != r3) goto L_0x0039
            java.lang.Object r8 = r0.L$2
            com.bitcoin.mwallet.core.models.exchangerate.FiatExchangeRate r8 = (com.bitcoin.mwallet.core.models.exchangerate.FiatExchangeRate) r8
            java.lang.Object r8 = r0.L$1
            java.math.BigDecimal r8 = (java.math.BigDecimal) r8
            java.lang.Object r8 = r0.L$0
            com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter r8 = (com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter) r8
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x0098
        L_0x0039:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L_0x0041:
            java.lang.Object r8 = r0.L$1
            java.math.BigDecimal r8 = (java.math.BigDecimal) r8
            java.lang.Object r2 = r0.L$0
            com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter r2 = (com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter) r2
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x0069
        L_0x004d:
            kotlin.ResultKt.throwOnFailure(r9)
            com.bitcoin.mwallet.core.interactors.GetCurrentExchangeRateInteractor r9 = r7.exchangeRateInteractor
            com.bitcoin.mwallet.core.models.Coin r2 = r7.coin
            java.util.Currency r5 = r7.currency
            java.lang.String r6 = "currency"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r5, r6)
            r0.L$0 = r7
            r0.L$1 = r8
            r0.label = r4
            java.lang.Object r9 = r9.getCurrentExchangeRate(r2, r5, r0)
            if (r9 != r1) goto L_0x0068
            return r1
        L_0x0068:
            r2 = r7
        L_0x0069:
            com.bitcoin.mwallet.core.models.exchangerate.FiatExchangeRate r9 = (com.bitcoin.mwallet.core.models.exchangerate.FiatExchangeRate) r9
            com.bitcoin.bitcoink.tx.Satoshis r4 = com.bitcoin.mwallet.core.models.exchangerate.FiatExchangeRateKt.fiatToSatoshis(r9, r8)
            if (r4 == 0) goto L_0x0072
            goto L_0x0078
        L_0x0072:
            com.bitcoin.bitcoink.tx.Satoshis$Companion r4 = com.bitcoin.bitcoink.p008tx.Satoshis.Companion
            com.bitcoin.bitcoink.tx.Satoshis r4 = r4.getZERO()
        L_0x0078:
            r2.sendAmountSatoshis = r4
            androidx.lifecycle.MutableLiveData<java.lang.String> r4 = r2.secondaryAmountValue
            com.bitcoin.bitcoink.tx.Satoshis r5 = r2.sendAmountSatoshis
            com.bitcoin.mwallet.core.models.Coin r6 = r2.coin
            java.lang.String r5 = com.bitcoin.mwallet.core.extensions.SatoshisKt.toCoinString(r5, r6)
            r4.setValue(r5)
            com.bitcoin.bitcoink.tx.Satoshis r4 = r2.sendAmountSatoshis
            r0.L$0 = r2
            r0.L$1 = r8
            r0.L$2 = r9
            r0.label = r3
            java.lang.Object r9 = r2.calculateFeeAmount(r4, r0)
            if (r9 != r1) goto L_0x0098
            return r1
        L_0x0098:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter.setFiatSendAmount(java.math.BigDecimal, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x004d  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0078  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0092 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0093 A[PHI: r10 
      PHI: (r10v2 java.lang.Object) = (r10v5 java.lang.Object), (r10v1 java.lang.Object) binds: [B:22:0x0090, B:10:0x0029] A[DONT_GENERATE, DONT_INLINE], RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object setCryptoSendAmount(@org.jetbrains.annotations.NotNull java.math.BigDecimal r9, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r10) {
        /*
            r8 = this;
            boolean r0 = r10 instanceof com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter$setCryptoSendAmount$1
            if (r0 == 0) goto L_0x0014
            r0 = r10
            com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter$setCryptoSendAmount$1 r0 = (com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter$setCryptoSendAmount$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r10 = r0.label
            int r10 = r10 - r2
            r0.label = r10
            goto L_0x0019
        L_0x0014:
            com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter$setCryptoSendAmount$1 r0 = new com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter$setCryptoSendAmount$1
            r0.<init>(r8, r10)
        L_0x0019:
            java.lang.Object r10 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            r4 = 2
            if (r2 == 0) goto L_0x004d
            if (r2 == r3) goto L_0x0041
            if (r2 != r4) goto L_0x0039
            java.lang.Object r9 = r0.L$2
            com.bitcoin.mwallet.core.models.exchangerate.FiatExchangeRate r9 = (com.bitcoin.mwallet.core.models.exchangerate.FiatExchangeRate) r9
            java.lang.Object r9 = r0.L$1
            java.math.BigDecimal r9 = (java.math.BigDecimal) r9
            java.lang.Object r9 = r0.L$0
            com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter r9 = (com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter) r9
            kotlin.ResultKt.throwOnFailure(r10)
            goto L_0x0093
        L_0x0039:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L_0x0041:
            java.lang.Object r9 = r0.L$1
            java.math.BigDecimal r9 = (java.math.BigDecimal) r9
            java.lang.Object r2 = r0.L$0
            com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter r2 = (com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter) r2
            kotlin.ResultKt.throwOnFailure(r10)
            goto L_0x0069
        L_0x004d:
            kotlin.ResultKt.throwOnFailure(r10)
            com.bitcoin.mwallet.core.interactors.GetCurrentExchangeRateInteractor r10 = r8.exchangeRateInteractor
            com.bitcoin.mwallet.core.models.Coin r2 = r8.coin
            java.util.Currency r5 = r8.currency
            java.lang.String r6 = "currency"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r5, r6)
            r0.L$0 = r8
            r0.L$1 = r9
            r0.label = r3
            java.lang.Object r10 = r10.getCurrentExchangeRate(r2, r5, r0)
            if (r10 != r1) goto L_0x0068
            return r1
        L_0x0068:
            r2 = r8
        L_0x0069:
            com.bitcoin.mwallet.core.models.exchangerate.FiatExchangeRate r10 = (com.bitcoin.mwallet.core.models.exchangerate.FiatExchangeRate) r10
            com.bitcoin.bitcoink.tx.Satoshis$Companion r3 = com.bitcoin.bitcoink.p008tx.Satoshis.Companion
            com.bitcoin.bitcoink.tx.Satoshis r3 = r3.fromCoins(r9)
            r2.sendAmountSatoshis = r3
            androidx.lifecycle.MutableLiveData<java.lang.String> r3 = r2.secondaryAmountValue
            r5 = 0
            if (r10 == 0) goto L_0x007f
            com.bitcoin.bitcoink.tx.Satoshis r6 = r2.sendAmountSatoshis
            r7 = 0
            java.lang.String r5 = com.bitcoin.mwallet.core.models.exchangerate.FiatExchangeRate.toFiatString$default(r10, r6, r7, r4, r5)
        L_0x007f:
            r3.setValue(r5)
            com.bitcoin.bitcoink.tx.Satoshis r3 = r2.sendAmountSatoshis
            r0.L$0 = r2
            r0.L$1 = r9
            r0.L$2 = r10
            r0.label = r4
            java.lang.Object r10 = r2.calculateFeeAmount(r3, r0)
            if (r10 != r1) goto L_0x0093
            return r1
        L_0x0093:
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter.setCryptoSendAmount(java.math.BigDecimal, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Nullable
    public final Object setWalletId(@NotNull WalletId walletId2, @NotNull Continuation<? super Unit> continuation) {
        BuildersKt__BuildersKt.runBlocking$default(null, new ReviewPresenter$setWalletId$2(this, walletId2, null), 1, null);
        GetBalanceByWalletInteractor getBalanceByWalletInteractor = this.walletBalanceInteractor;
        WalletId walletId3 = this.walletId;
        if (walletId3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("walletId");
        }
        Currency currency2 = this.currency;
        Intrinsics.checkExpressionValueIsNotNull(currency2, Param.CURRENCY);
        this.balance = getBalanceByWalletInteractor.getBalanceByWallet(walletId3, currency2);
        return Unit.INSTANCE;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x003a  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x005a  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0073  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object setNetworkFee(@org.jetbrains.annotations.NotNull com.bitcoin.bitcoink.p008tx.Satoshis r7, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
        /*
            r6 = this;
            boolean r0 = r8 instanceof com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter$setNetworkFee$1
            if (r0 == 0) goto L_0x0014
            r0 = r8
            com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter$setNetworkFee$1 r0 = (com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter$setNetworkFee$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L_0x0019
        L_0x0014:
            com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter$setNetworkFee$1 r0 = new com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter$setNetworkFee$1
            r0.<init>(r6, r8)
        L_0x0019:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x003a
            if (r2 != r3) goto L_0x0032
            java.lang.Object r7 = r0.L$1
            com.bitcoin.bitcoink.tx.Satoshis r7 = (com.bitcoin.bitcoink.p008tx.Satoshis) r7
            java.lang.Object r0 = r0.L$0
            com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter r0 = (com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter) r0
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x0056
        L_0x0032:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L_0x003a:
            kotlin.ResultKt.throwOnFailure(r8)
            com.bitcoin.mwallet.core.interactors.GetCurrentExchangeRateInteractor r8 = r6.exchangeRateInteractor
            com.bitcoin.mwallet.core.models.Coin r2 = r6.coin
            java.util.Currency r4 = r6.currency
            java.lang.String r5 = "currency"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r4, r5)
            r0.L$0 = r6
            r0.L$1 = r7
            r0.label = r3
            java.lang.Object r8 = r8.getCurrentExchangeRate(r2, r4, r0)
            if (r8 != r1) goto L_0x0055
            return r1
        L_0x0055:
            r0 = r6
        L_0x0056:
            com.bitcoin.mwallet.core.models.exchangerate.FiatExchangeRate r8 = (com.bitcoin.mwallet.core.models.exchangerate.FiatExchangeRate) r8
            if (r8 == 0) goto L_0x0073
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r2 = 0
            r3 = 2
            r4 = 0
            java.lang.String r8 = com.bitcoin.mwallet.core.models.exchangerate.FiatExchangeRate.toFiatString$default(r8, r7, r2, r3, r4)
            r1.append(r8)
            r8 = 32
            r1.append(r8)
            java.lang.String r8 = r1.toString()
            goto L_0x0075
        L_0x0073:
            java.lang.String r8 = ""
        L_0x0075:
            androidx.lifecycle.MutableLiveData<java.lang.String> r1 = r0.networkFeeText
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            com.bitcoin.mwallet.core.models.Coin r0 = r0.coin
            java.lang.String r7 = com.bitcoin.mwallet.core.extensions.SatoshisKt.toCoinString(r7, r0)
            r2.append(r7)
            java.lang.String r7 = " ("
            r2.append(r7)
            r2.append(r8)
            r7 = 41
            r2.append(r7)
            java.lang.String r7 = r2.toString()
            r1.setValue(r7)
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter.setNetworkFee(com.bitcoin.bitcoink.tx.Satoshis, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x005e  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0086  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0096  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0099  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0029  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object calculateFeeAmount(@org.jetbrains.annotations.NotNull com.bitcoin.bitcoink.p008tx.Satoshis r19, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r20) {
        /*
            r18 = this;
            r0 = r18
            r1 = r20
            boolean r2 = r1 instanceof com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter$calculateFeeAmount$1
            if (r2 == 0) goto L_0x0018
            r2 = r1
            com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter$calculateFeeAmount$1 r2 = (com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter$calculateFeeAmount$1) r2
            int r3 = r2.label
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r3 & r4
            if (r3 == 0) goto L_0x0018
            int r1 = r2.label
            int r1 = r1 - r4
            r2.label = r1
            goto L_0x001d
        L_0x0018:
            com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter$calculateFeeAmount$1 r2 = new com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter$calculateFeeAmount$1
            r2.<init>(r0, r1)
        L_0x001d:
            java.lang.Object r1 = r2.result
            java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r4 = r2.label
            r5 = 2
            r6 = 1
            if (r4 == 0) goto L_0x005e
            if (r4 == r6) goto L_0x004e
            if (r4 != r5) goto L_0x0046
            java.lang.Object r3 = r2.L$4
            com.bitcoin.mwallet.core.models.tx.utxo.UtxoSelection r3 = (com.bitcoin.mwallet.core.models.p009tx.utxo.UtxoSelection) r3
            java.lang.Object r3 = r2.L$3
            com.bitcoin.mwallet.core.models.tx.utxo.UtxoSelection r3 = (com.bitcoin.mwallet.core.models.p009tx.utxo.UtxoSelection) r3
            java.lang.Object r3 = r2.L$2
            com.bitcoin.mwallet.app.flows.sendv2.entity.SendWalletData r3 = (com.bitcoin.mwallet.app.flows.sendv2.entity.SendWalletData) r3
            java.lang.Object r3 = r2.L$1
            com.bitcoin.bitcoink.tx.Satoshis r3 = (com.bitcoin.bitcoink.p008tx.Satoshis) r3
            java.lang.Object r2 = r2.L$0
            com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter r2 = (com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter) r2
            kotlin.ResultKt.throwOnFailure(r1)
            goto L_0x00b0
        L_0x0046:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r1
        L_0x004e:
            java.lang.Object r4 = r2.L$2
            com.bitcoin.mwallet.app.flows.sendv2.entity.SendWalletData r4 = (com.bitcoin.mwallet.app.flows.sendv2.entity.SendWalletData) r4
            java.lang.Object r6 = r2.L$1
            com.bitcoin.bitcoink.tx.Satoshis r6 = (com.bitcoin.bitcoink.p008tx.Satoshis) r6
            java.lang.Object r7 = r2.L$0
            com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter r7 = (com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter) r7
            kotlin.ResultKt.throwOnFailure(r1)
            goto L_0x0081
        L_0x005e:
            kotlin.ResultKt.throwOnFailure(r1)
            com.bitcoin.mwallet.app.flows.sendv2.entity.SendWalletData r4 = r0.walletData
            if (r4 != 0) goto L_0x006a
            java.lang.String r1 = "walletData"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
        L_0x006a:
            r2.L$0 = r0
            r1 = r19
            r2.L$1 = r1
            r2.L$2 = r4
            r2.label = r6
            java.lang.Object r6 = r0.getSatoshiPerByte(r2)
            if (r6 != r3) goto L_0x007b
            return r3
        L_0x007b:
            r7 = r0
            r17 = r6
            r6 = r1
            r1 = r17
        L_0x0081:
            r11 = r1
            java.math.BigDecimal r11 = (java.math.BigDecimal) r11
            if (r11 == 0) goto L_0x0096
            com.bitcoin.mwallet.core.interactors.CreateTxInteractor r8 = r7.createTxinteractor
            r12 = 0
            r13 = 0
            r15 = 24
            r16 = 0
            r9 = r4
            r10 = r6
            com.bitcoin.mwallet.core.models.tx.utxo.UtxoSelection r1 = com.bitcoin.mwallet.core.interactors.CreateTxInteractor.selectUtxos$default(r8, r9, r10, r11, r12, r13, r15, r16)
            goto L_0x0097
        L_0x0096:
            r1 = 0
        L_0x0097:
            if (r1 == 0) goto L_0x00b0
            com.bitcoin.bitcoink.tx.Satoshis r8 = r1.getFee()
            r2.L$0 = r7
            r2.L$1 = r6
            r2.L$2 = r4
            r2.L$3 = r1
            r2.L$4 = r1
            r2.label = r5
            java.lang.Object r1 = r7.setNetworkFee(r8, r2)
            if (r1 != r3) goto L_0x00b0
            return r3
        L_0x00b0:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter.calculateFeeAmount(com.bitcoin.bitcoink.tx.Satoshis, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ java.lang.Object getSatoshiPerByte(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.math.BigDecimal> r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter$getSatoshiPerByte$1
            if (r0 == 0) goto L_0x0014
            r0 = r5
            com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter$getSatoshiPerByte$1 r0 = (com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter$getSatoshiPerByte$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r5 = r0.label
            int r5 = r5 - r2
            r0.label = r5
            goto L_0x0019
        L_0x0014:
            com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter$getSatoshiPerByte$1 r0 = new com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter$getSatoshiPerByte$1
            r0.<init>(r4, r5)
        L_0x0019:
            java.lang.Object r5 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0036
            if (r2 != r3) goto L_0x002e
            java.lang.Object r0 = r0.L$0
            com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter r0 = (com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter) r0
            kotlin.ResultKt.throwOnFailure(r5)
            goto L_0x0048
        L_0x002e:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L_0x0036:
            kotlin.ResultKt.throwOnFailure(r5)
            com.bitcoin.mwallet.core.interactors.GetNetworkFeeInteractor r5 = r4.getNetworkFeeInteractor
            com.bitcoin.mwallet.core.models.Coin r2 = r4.coin
            r0.L$0 = r4
            r0.label = r3
            java.lang.Object r5 = r5.getNetworkFeeAndPreference(r2, r0)
            if (r5 != r1) goto L_0x0048
            return r1
        L_0x0048:
            kotlin.Pair r5 = (kotlin.Pair) r5
            java.lang.Object r0 = r5.getFirst()
            com.bitcoin.mwallet.core.models.networkfee.NetworkFees r0 = (com.bitcoin.mwallet.core.models.networkfee.NetworkFees) r0
            java.lang.Object r5 = r5.getSecond()
            com.bitcoin.mwallet.core.models.networkfee.NetworkFeeLevel r5 = (com.bitcoin.mwallet.core.models.networkfee.NetworkFeeLevel) r5
            java.math.BigDecimal r5 = r0.getFee(r5)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter.getSatoshiPerByte(kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final void sendNativeCoin() {
        this.sendJob = BuildersKt__Builders_commonKt.launch$default(this.viewModelScope, null, null, new ReviewPresenter$sendNativeCoin$1(this, null), 3, null);
    }

    private final void sendToken() {
        this.sendJob = BuildersKt__Builders_commonKt.launch$default(this.viewModelScope, null, null, new ReviewPresenter$sendToken$1(this, null), 3, null);
    }

    public final void onSend() {
        Job job = this.sendJob;
        if (job == null || !job.isActive()) {
            if (this.tokenAmount == null || this.tokenId == null) {
                sendNativeCoin();
            } else {
                sendToken();
            }
            return;
        }
        Timber.m426d("test double tap ignore", new Object[0]);
    }

    public final void initSendData(@NotNull SendWhatModel sendWhatModel) {
        Intrinsics.checkParameterIsNotNull(sendWhatModel, "sendData");
        this.currency = Currency.getInstance(sendWhatModel.getCurrency());
        this.isSendingFiat = sendWhatModel.getCryptoAmount() == null;
        this.coin = sendWhatModel.getCoin();
        this.tokenAmount = sendWhatModel.getTokenAmount();
        this.tokenId = sendWhatModel.getUri().getTokenId();
        BuildersKt__BuildersKt.runBlocking$default(null, new ReviewPresenter$initSendData$1(this, sendWhatModel, null), 1, null);
    }

    public final void onBackPressed() {
        ((ReviewRouter) getRouter()).onBackPressed();
    }

    public final void onClosePressed(@NotNull Activity activity) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        ((ReviewRouter) getRouter()).onClosePressed(activity);
    }
}
