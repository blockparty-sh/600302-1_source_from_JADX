package com.bitcoin.mwallet.app.flows.sendv2.sendamountselection;

import android.content.Context;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import com.bitcoin.bitcoink.p008tx.Satoshis;
import com.bitcoin.bitcoink.p008tx.Satoshis.Companion;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.app.android.LiveDataKt;
import com.bitcoin.mwallet.app.components.amountselection.AmountSelectionPresenterBase;
import com.bitcoin.mwallet.app.components.amountselection.AmountSelectionPresenterBase.EntryType;
import com.bitcoin.mwallet.app.components.walletselector.WalletSelectorListener;
import com.bitcoin.mwallet.core.entity.WalletType;
import com.bitcoin.mwallet.core.interactors.FeeValidatorInteractor;
import com.bitcoin.mwallet.core.interactors.GetBalanceByWalletInteractor;
import com.bitcoin.mwallet.core.interactors.GetCurrentExchangeRateInteractor;
import com.bitcoin.mwallet.core.interactors.GetDefaultCurrencyInteractor;
import com.bitcoin.mwallet.core.interactors.GetWalletInteractor;
import com.bitcoin.mwallet.core.models.BitcoinUriContent;
import com.bitcoin.mwallet.core.models.Coin;
import com.bitcoin.mwallet.core.models.slp.Slp;
import com.bitcoin.mwallet.core.models.slp.SlpId;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import com.bitcoin.mwallet.core.models.wallet.WalletInfoBalance;
import com.bitcoin.mwallet.core.repositories.SlpRepository;
import java.math.BigDecimal;
import java.util.Currency;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000Î\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u00012\u00020\u0002BM\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\u0006\u0010\u0013\u001a\u00020\u0014¢\u0006\u0002\u0010\u0015J\u001c\u0010M\u001a\u0018\u0012\u0014\u0012\u0012\u0012\u0006\u0012\u0004\u0018\u000102\u0012\u0006\u0012\u0004\u0018\u00010O0N00J\f\u0010P\u001a\b\u0012\u0004\u0012\u00020$00J\u001a\u0010Q\u001a\u00020R2\b\u0010S\u001a\u0004\u0018\u00010T2\b\u0010U\u001a\u0004\u0018\u00010VJ\b\u0010W\u001a\u00020$H\u0002J\b\u0010X\u001a\u00020$H\u0002J\b\u0010Y\u001a\u00020$H\u0002J\b\u0010Z\u001a\u00020$H\u0002J\u0006\u0010[\u001a\u00020RJ\u0006\u0010\\\u001a\u00020RJ\u0016\u0010]\u001a\u00020R2\u0006\u0010^\u001a\u00020_2\u0006\u0010`\u001a\u00020aJ\u0018\u0010b\u001a\u00020R2\u0006\u0010c\u001a\u0002022\u0006\u0010`\u001a\u00020aH\u0016J\u0010\u0010d\u001a\u00020R2\b\u0010e\u001a\u0004\u0018\u00010fJ\u001a\u0010g\u001a\u00020R2\b\u0010h\u001a\u0004\u0018\u0001022\b\u0010i\u001a\u0004\u0018\u00010OJ\u0006\u0010j\u001a\u00020RR\u0014\u0010\u0016\u001a\u00020\u0017XD¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0019\u0010\u001a\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001c0\u001b¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u000e\u0010\u0011\u001a\u00020\u0012X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R(\u0010#\u001a\u0010\u0012\f\u0012\n %*\u0004\u0018\u00010$0$0\u001bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\u001e\"\u0004\b'\u0010(R\u001c\u0010)\u001a\u0004\u0018\u00010*X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R\u0016\u0010/\u001a\n\u0012\u0004\u0012\u00020*\u0018\u000100X\u000e¢\u0006\u0002\n\u0000R\"\u00101\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001020\u001bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u0010\u001e\"\u0004\b4\u0010(R\u0011\u0010\u0013\u001a\u00020\u0014¢\u0006\b\n\u0000\u001a\u0004\b5\u00106R\u001a\u00107\u001a\u000208X.¢\u0006\u000e\n\u0000\u001a\u0004\b9\u0010:\"\u0004\b;\u0010<R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\"\u0010=\u001a\n %*\u0004\u0018\u00010>0>X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b?\u0010@\"\u0004\bA\u0010BR\u001c\u0010C\u001a\u0004\u0018\u00010DX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bE\u0010F\"\u0004\bG\u0010HR\u0017\u0010I\u001a\b\u0012\u0004\u0012\u00020*0J¢\u0006\b\n\u0000\u001a\u0004\bK\u0010L¨\u0006k"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/sendv2/sendamountselection/SendAmountSelectionPresenter;", "Lcom/bitcoin/mwallet/app/components/amountselection/AmountSelectionPresenterBase;", "Lcom/bitcoin/mwallet/app/components/walletselector/WalletSelectorListener;", "context", "Landroid/content/Context;", "viewModelScope", "Lkotlinx/coroutines/CoroutineScope;", "getDefaultCurrencyInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetDefaultCurrencyInteractor;", "slpRepository", "Lcom/bitcoin/mwallet/core/repositories/SlpRepository;", "getCurrentExchangeRateInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetCurrentExchangeRateInteractor;", "getWalletInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetWalletInteractor;", "getBalanceByWalletInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetBalanceByWalletInteractor;", "feeValidatorInteractor", "Lcom/bitcoin/mwallet/core/interactors/FeeValidatorInteractor;", "sendAmountRouter", "Lcom/bitcoin/mwallet/app/flows/sendv2/sendamountselection/SendAmountSelectionRouter;", "(Landroid/content/Context;Lkotlinx/coroutines/CoroutineScope;Lcom/bitcoin/mwallet/core/interactors/GetDefaultCurrencyInteractor;Lcom/bitcoin/mwallet/core/repositories/SlpRepository;Lcom/bitcoin/mwallet/core/interactors/GetCurrentExchangeRateInteractor;Lcom/bitcoin/mwallet/core/interactors/GetWalletInteractor;Lcom/bitcoin/mwallet/core/interactors/GetBalanceByWalletInteractor;Lcom/bitcoin/mwallet/core/interactors/FeeValidatorInteractor;Lcom/bitcoin/mwallet/app/flows/sendv2/sendamountselection/SendAmountSelectionRouter;)V", "DUST", "", "getDUST", "()J", "amountError", "Landroidx/lifecycle/MutableLiveData;", "", "getAmountError", "()Landroidx/lifecycle/MutableLiveData;", "getContext", "()Landroid/content/Context;", "getGetWalletInteractor", "()Lcom/bitcoin/mwallet/core/interactors/GetWalletInteractor;", "hasEnoughBalance", "", "kotlin.jvm.PlatformType", "getHasEnoughBalance", "setHasEnoughBalance", "(Landroidx/lifecycle/MutableLiveData;)V", "latestWalletBalance", "Lcom/bitcoin/mwallet/core/models/wallet/WalletInfoBalance;", "getLatestWalletBalance", "()Lcom/bitcoin/mwallet/core/models/wallet/WalletInfoBalance;", "setLatestWalletBalance", "(Lcom/bitcoin/mwallet/core/models/wallet/WalletInfoBalance;)V", "oldWalletBalance", "Landroidx/lifecycle/LiveData;", "selectedWalletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "getSelectedWalletId", "setSelectedWalletId", "getSendAmountRouter", "()Lcom/bitcoin/mwallet/app/flows/sendv2/sendamountselection/SendAmountSelectionRouter;", "sendToUri", "Lcom/bitcoin/mwallet/core/models/BitcoinUriContent;", "getSendToUri", "()Lcom/bitcoin/mwallet/core/models/BitcoinUriContent;", "setSendToUri", "(Lcom/bitcoin/mwallet/core/models/BitcoinUriContent;)V", "targetWalletTokenBalance", "Ljava/math/BigDecimal;", "getTargetWalletTokenBalance", "()Ljava/math/BigDecimal;", "setTargetWalletTokenBalance", "(Ljava/math/BigDecimal;)V", "tokenInfo", "Lcom/bitcoin/mwallet/core/models/slp/Slp;", "getTokenInfo", "()Lcom/bitcoin/mwallet/core/models/slp/Slp;", "setTokenInfo", "(Lcom/bitcoin/mwallet/core/models/slp/Slp;)V", "walletBalance", "Landroidx/lifecycle/MediatorLiveData;", "getWalletBalance", "()Landroidx/lifecycle/MediatorLiveData;", "getBalanceDependencies", "Lkotlin/Pair;", "Ljava/util/Currency;", "getSendEnabled", "init", "", "coin", "Lcom/bitcoin/mwallet/core/models/Coin;", "tokenId", "Lcom/bitcoin/mwallet/core/models/slp/SlpId;", "isBelowDustLimit", "isInsufficientBalance", "isInsufficientFee", "isSendAll", "onContinueTapped", "onSendAllTapped", "onWalletSelectTapped", "fm", "Landroidx/fragment/app/FragmentManager;", "walletType", "Lcom/bitcoin/mwallet/core/entity/WalletType;", "onWalletSelected", "walletId", "setInitialSatoshiAmount", "amount", "Lcom/bitcoin/bitcoink/tx/Satoshis;", "updateWalletBalance", "walletid", "currency", "validateAmount", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: SendAmountSelectionPresenter.kt */
public final class SendAmountSelectionPresenter extends AmountSelectionPresenterBase implements WalletSelectorListener {
    private final long DUST = 546;
    @NotNull
    private final MutableLiveData<String> amountError = new MutableLiveData<>("");
    @NotNull
    private final Context context;
    private final FeeValidatorInteractor feeValidatorInteractor;
    private final GetBalanceByWalletInteractor getBalanceByWalletInteractor;
    @NotNull
    private final GetWalletInteractor getWalletInteractor;
    @NotNull
    private MutableLiveData<Boolean> hasEnoughBalance = new MutableLiveData<>(Boolean.valueOf(true));
    @Nullable
    private WalletInfoBalance latestWalletBalance;
    private LiveData<WalletInfoBalance> oldWalletBalance;
    @NotNull
    private MutableLiveData<WalletId> selectedWalletId = new MutableLiveData<>(null);
    @NotNull
    private final SendAmountSelectionRouter sendAmountRouter;
    @NotNull
    public BitcoinUriContent sendToUri;
    /* access modifiers changed from: private */
    public final SlpRepository slpRepository;
    private BigDecimal targetWalletTokenBalance = BigDecimal.ZERO;
    @Nullable
    private Slp tokenInfo;
    @NotNull
    private final MediatorLiveData<WalletInfoBalance> walletBalance = new MediatorLiveData<>();

    @Metadata(mo37403bv = {1, 0, 3}, mo37406k = 3, mo37407mv = {1, 1, 15})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[EntryType.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1 = new int[EntryType.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$2 = new int[EntryType.values().length];

        static {
            $EnumSwitchMapping$0[EntryType.TOKEN.ordinal()] = 1;
            $EnumSwitchMapping$1[EntryType.FIAT.ordinal()] = 1;
            $EnumSwitchMapping$1[EntryType.COIN.ordinal()] = 2;
            $EnumSwitchMapping$1[EntryType.TOKEN.ordinal()] = 3;
            $EnumSwitchMapping$2[EntryType.FIAT.ordinal()] = 1;
            $EnumSwitchMapping$2[EntryType.COIN.ordinal()] = 2;
            $EnumSwitchMapping$2[EntryType.TOKEN.ordinal()] = 3;
        }
    }

    @NotNull
    public final Context getContext() {
        return this.context;
    }

    @NotNull
    public final GetWalletInteractor getGetWalletInteractor() {
        return this.getWalletInteractor;
    }

    @NotNull
    public final SendAmountSelectionRouter getSendAmountRouter() {
        return this.sendAmountRouter;
    }

    public SendAmountSelectionPresenter(@NotNull Context context2, @NotNull CoroutineScope coroutineScope, @NotNull GetDefaultCurrencyInteractor getDefaultCurrencyInteractor, @NotNull SlpRepository slpRepository2, @NotNull GetCurrentExchangeRateInteractor getCurrentExchangeRateInteractor, @NotNull GetWalletInteractor getWalletInteractor2, @NotNull GetBalanceByWalletInteractor getBalanceByWalletInteractor2, @NotNull FeeValidatorInteractor feeValidatorInteractor2, @NotNull SendAmountSelectionRouter sendAmountSelectionRouter) {
        Context context3 = context2;
        SlpRepository slpRepository3 = slpRepository2;
        GetWalletInteractor getWalletInteractor3 = getWalletInteractor2;
        GetBalanceByWalletInteractor getBalanceByWalletInteractor3 = getBalanceByWalletInteractor2;
        FeeValidatorInteractor feeValidatorInteractor3 = feeValidatorInteractor2;
        SendAmountSelectionRouter sendAmountSelectionRouter2 = sendAmountSelectionRouter;
        Intrinsics.checkParameterIsNotNull(context2, "context");
        CoroutineScope coroutineScope2 = coroutineScope;
        Intrinsics.checkParameterIsNotNull(coroutineScope, "viewModelScope");
        GetDefaultCurrencyInteractor getDefaultCurrencyInteractor2 = getDefaultCurrencyInteractor;
        Intrinsics.checkParameterIsNotNull(getDefaultCurrencyInteractor2, "getDefaultCurrencyInteractor");
        Intrinsics.checkParameterIsNotNull(slpRepository3, "slpRepository");
        GetCurrentExchangeRateInteractor getCurrentExchangeRateInteractor2 = getCurrentExchangeRateInteractor;
        Intrinsics.checkParameterIsNotNull(getCurrentExchangeRateInteractor2, "getCurrentExchangeRateInteractor");
        Intrinsics.checkParameterIsNotNull(getWalletInteractor3, "getWalletInteractor");
        Intrinsics.checkParameterIsNotNull(getBalanceByWalletInteractor3, "getBalanceByWalletInteractor");
        Intrinsics.checkParameterIsNotNull(feeValidatorInteractor3, "feeValidatorInteractor");
        Intrinsics.checkParameterIsNotNull(sendAmountSelectionRouter2, "sendAmountRouter");
        super(context2, coroutineScope2, getDefaultCurrencyInteractor2, getCurrentExchangeRateInteractor2, sendAmountSelectionRouter2);
        this.context = context3;
        this.slpRepository = slpRepository3;
        this.getWalletInteractor = getWalletInteractor3;
        this.getBalanceByWalletInteractor = getBalanceByWalletInteractor3;
        this.feeValidatorInteractor = feeValidatorInteractor3;
        this.sendAmountRouter = sendAmountSelectionRouter2;
    }

    public final long getDUST() {
        return this.DUST;
    }

    public final BigDecimal getTargetWalletTokenBalance() {
        return this.targetWalletTokenBalance;
    }

    public final void setTargetWalletTokenBalance(BigDecimal bigDecimal) {
        this.targetWalletTokenBalance = bigDecimal;
    }

    @Nullable
    public final WalletInfoBalance getLatestWalletBalance() {
        return this.latestWalletBalance;
    }

    public final void setLatestWalletBalance(@Nullable WalletInfoBalance walletInfoBalance) {
        this.latestWalletBalance = walletInfoBalance;
    }

    @NotNull
    public final MutableLiveData<Boolean> getHasEnoughBalance() {
        return this.hasEnoughBalance;
    }

    public final void setHasEnoughBalance(@NotNull MutableLiveData<Boolean> mutableLiveData) {
        Intrinsics.checkParameterIsNotNull(mutableLiveData, "<set-?>");
        this.hasEnoughBalance = mutableLiveData;
    }

    @NotNull
    public final MutableLiveData<String> getAmountError() {
        return this.amountError;
    }

    @Nullable
    public final Slp getTokenInfo() {
        return this.tokenInfo;
    }

    public final void setTokenInfo(@Nullable Slp slp) {
        this.tokenInfo = slp;
    }

    @NotNull
    public final BitcoinUriContent getSendToUri() {
        BitcoinUriContent bitcoinUriContent = this.sendToUri;
        if (bitcoinUriContent == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sendToUri");
        }
        return bitcoinUriContent;
    }

    public final void setSendToUri(@NotNull BitcoinUriContent bitcoinUriContent) {
        Intrinsics.checkParameterIsNotNull(bitcoinUriContent, "<set-?>");
        this.sendToUri = bitcoinUriContent;
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
    public final MediatorLiveData<WalletInfoBalance> getWalletBalance() {
        return this.walletBalance;
    }

    public final void updateWalletBalance(@Nullable WalletId walletId, @Nullable Currency currency) {
        if (((EntryType) getPrimaryEntryType().getValue()) == EntryType.TOKEN && this.tokenInfo != null) {
            this.targetWalletTokenBalance = (BigDecimal) BuildersKt__BuildersKt.runBlocking$default(null, new SendAmountSelectionPresenter$updateWalletBalance$1(this, null), 1, null);
        }
        LiveData<WalletInfoBalance> liveData = this.oldWalletBalance;
        if (liveData != null) {
            MediatorLiveData<WalletInfoBalance> mediatorLiveData = this.walletBalance;
            if (liveData == null) {
                Intrinsics.throwNpe();
            }
            mediatorLiveData.removeSource(liveData);
        }
        if (walletId != null && currency != null) {
            this.oldWalletBalance = this.getBalanceByWalletInteractor.getBalanceByWallet(walletId, currency);
            MediatorLiveData<WalletInfoBalance> mediatorLiveData2 = this.walletBalance;
            LiveData<WalletInfoBalance> liveData2 = this.oldWalletBalance;
            if (liveData2 == null) {
                Intrinsics.throwNpe();
            }
            mediatorLiveData2.addSource(liveData2, new SendAmountSelectionPresenter$updateWalletBalance$2(this));
        }
    }

    @NotNull
    public final LiveData<Boolean> getSendEnabled() {
        return LiveDataKt.combineLatestIgnoreNull(this.hasEnoughBalance, this.amountError, SendAmountSelectionPresenter$getSendEnabled$1.INSTANCE);
    }

    private final boolean isBelowDustLimit() {
        if (getCryptoAmount() != null) {
            Companion companion = Satoshis.Companion;
            BigDecimal cryptoAmount = getCryptoAmount();
            if (cryptoAmount == null) {
                Intrinsics.throwNpe();
            }
            if (companion.fromCoins(cryptoAmount).compareTo(this.DUST) <= 0) {
                BigDecimal cryptoAmount2 = getCryptoAmount();
                if (cryptoAmount2 == null) {
                    Intrinsics.throwNpe();
                }
                if (cryptoAmount2.compareTo(BigDecimal.ZERO) > 0) {
                    return true;
                }
            }
        }
        return false;
    }

    private final boolean isInsufficientFee() {
        EntryType entryType = (EntryType) getPrimaryEntryType().getValue();
        String str = "selectedWalletId.value!!";
        if (entryType != null && WhenMappings.$EnumSwitchMapping$0[entryType.ordinal()] == 1) {
            if (this.selectedWalletId.getValue() == null || getTokenInputAmount() == null || this.tokenInfo == null) {
                return false;
            }
            FeeValidatorInteractor feeValidatorInteractor2 = this.feeValidatorInteractor;
            Object value = this.selectedWalletId.getValue();
            if (value == null) {
                Intrinsics.throwNpe();
            }
            Intrinsics.checkExpressionValueIsNotNull(value, str);
            WalletId walletId = (WalletId) value;
            BigDecimal tokenInputAmount = getTokenInputAmount();
            Intrinsics.checkExpressionValueIsNotNull(tokenInputAmount, "tokenInputAmount");
            Slp slp = this.tokenInfo;
            if (slp == null) {
                Intrinsics.throwNpe();
            }
            return feeValidatorInteractor2.isInsufficientTokenFee(walletId, tokenInputAmount, slp, getCoin());
        } else if (this.selectedWalletId.getValue() == null || getCryptoAmount() == null || isSendAll()) {
            return false;
        } else {
            FeeValidatorInteractor feeValidatorInteractor3 = this.feeValidatorInteractor;
            Object value2 = this.selectedWalletId.getValue();
            if (value2 == null) {
                Intrinsics.throwNpe();
            }
            Intrinsics.checkExpressionValueIsNotNull(value2, str);
            WalletId walletId2 = (WalletId) value2;
            BigDecimal cryptoAmount = getCryptoAmount();
            if (cryptoAmount == null) {
                Intrinsics.throwNpe();
            }
            return FeeValidatorInteractor.isInsufficientCoinFee$default(feeValidatorInteractor3, walletId2, cryptoAmount, getCoin(), null, 0, 24, null);
        }
    }

    private final boolean isSendAll() {
        Satoshis satoshis;
        Companion companion = Satoshis.Companion;
        BigDecimal cryptoAmount = getCryptoAmount();
        if (cryptoAmount == null) {
            Intrinsics.throwNpe();
        }
        Satoshis fromCoins = companion.fromCoins(cryptoAmount);
        WalletInfoBalance walletInfoBalance = this.latestWalletBalance;
        if (walletInfoBalance != null) {
            Satoshis satoshis2 = walletInfoBalance.getSatoshis();
            if (satoshis2 != null) {
                satoshis = satoshis2.minus(fromCoins);
                if (satoshis == null && satoshis.getSatoshis() == 0) {
                    return true;
                }
            }
        }
        satoshis = null;
        return satoshis == null ? false : false;
    }

    public final void validateAmount() {
        if (isInsufficientBalance()) {
            this.hasEnoughBalance.postValue(Boolean.valueOf(false));
            this.amountError.postValue(this.context.getString(C1018R.string.send_amount_error_insufficient_balance));
        } else if (isBelowDustLimit()) {
            this.amountError.postValue(this.context.getString(C1018R.string.send_amount_error_too_low));
        } else if (!isInsufficientFee()) {
            this.amountError.postValue("");
            this.hasEnoughBalance.postValue(Boolean.valueOf(true));
        } else if (((EntryType) getPrimaryEntryType().getValue()) == EntryType.TOKEN) {
            this.amountError.postValue(this.context.getString(C1018R.string.send_amount_error_insufficient_token_fee));
        } else {
            this.amountError.postValue(this.context.getString(C1018R.string.send_amount_error_insufficient_fee));
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0043, code lost:
        if (getTokenInputAmount().compareTo(r10.targetWalletTokenBalance) > 0) goto L_0x0045;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0072, code lost:
        if (r0.compareTo(r4) > 0) goto L_0x0045;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x00f7, code lost:
        if (r0.compareTo(r4) > 0) goto L_0x0045;
     */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0060  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0075 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00b6  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00e5  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x00b9 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final boolean isInsufficientBalance() {
        /*
            r10 = this;
            androidx.lifecycle.MutableLiveData r0 = r10.getPrimaryAmount()
            java.lang.Object r0 = r0.getValue()
            if (r0 != 0) goto L_0x000d
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x000d:
            java.lang.String r1 = "primaryAmount.value!!"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
            java.lang.String r0 = (java.lang.String) r0
            java.math.BigDecimal r0 = r10.convertToBigDecimal(r0)
            androidx.lifecycle.MutableLiveData r1 = r10.getPrimaryEntryType()
            java.lang.Object r1 = r1.getValue()
            com.bitcoin.mwallet.app.components.amountselection.AmountSelectionPresenterBase$EntryType r1 = (com.bitcoin.mwallet.app.components.amountselection.AmountSelectionPresenterBase.EntryType) r1
            r2 = 1
            r3 = 0
            if (r1 != 0) goto L_0x0028
            goto L_0x00fb
        L_0x0028:
            int[] r4 = com.bitcoin.mwallet.app.flows.sendv2.sendamountselection.SendAmountSelectionPresenter.WhenMappings.$EnumSwitchMapping$1
            int r1 = r1.ordinal()
            r1 = r4[r1]
            r4 = 0
            if (r1 == r2) goto L_0x0076
            r5 = 2
            if (r1 == r5) goto L_0x004e
            r0 = 3
            if (r1 != r0) goto L_0x0048
            java.math.BigDecimal r0 = r10.getTokenInputAmount()
            java.math.BigDecimal r1 = r10.targetWalletTokenBalance
            int r0 = r0.compareTo(r1)
            if (r0 <= 0) goto L_0x00fb
        L_0x0045:
            r3 = 1
            goto L_0x00fb
        L_0x0048:
            kotlin.NoWhenBranchMatchedException r0 = new kotlin.NoWhenBranchMatchedException
            r0.<init>()
            throw r0
        L_0x004e:
            com.bitcoin.mwallet.core.models.wallet.WalletInfoBalance r1 = r10.latestWalletBalance
            if (r1 == 0) goto L_0x005d
            com.bitcoin.bitcoink.tx.Satoshis r1 = r1.getSatoshis()
            if (r1 == 0) goto L_0x005d
            java.math.BigDecimal r1 = r1.getCoins()
            goto L_0x005e
        L_0x005d:
            r1 = r4
        L_0x005e:
            if (r1 == 0) goto L_0x0075
            com.bitcoin.mwallet.core.models.wallet.WalletInfoBalance r1 = r10.latestWalletBalance
            if (r1 == 0) goto L_0x006e
            com.bitcoin.bitcoink.tx.Satoshis r1 = r1.getSatoshis()
            if (r1 == 0) goto L_0x006e
            java.math.BigDecimal r4 = r1.getCoins()
        L_0x006e:
            int r0 = r0.compareTo(r4)
            if (r0 <= 0) goto L_0x00fb
            goto L_0x0045
        L_0x0075:
            return r3
        L_0x0076:
            androidx.lifecycle.MutableLiveData r0 = r10.getSecondaryDisplayAmount()
            java.lang.Object r0 = r0.getValue()
            java.lang.String r0 = (java.lang.String) r0
            if (r0 == 0) goto L_0x00c8
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.Appendable r1 = (java.lang.Appendable) r1
            int r5 = r0.length()
            r6 = 0
        L_0x0090:
            if (r6 >= r5) goto L_0x00bc
            char r7 = r0.charAt(r6)
            boolean r8 = java.lang.Character.isDigit(r7)
            if (r8 != 0) goto L_0x00b3
            java.text.DecimalFormat r8 = new java.text.DecimalFormat
            r8.<init>()
            java.text.DecimalFormatSymbols r8 = r8.getDecimalFormatSymbols()
            java.lang.String r9 = "DecimalFormat().decimalFormatSymbols"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r8, r9)
            char r8 = r8.getDecimalSeparator()
            if (r7 != r8) goto L_0x00b1
            goto L_0x00b3
        L_0x00b1:
            r8 = 0
            goto L_0x00b4
        L_0x00b3:
            r8 = 1
        L_0x00b4:
            if (r8 == 0) goto L_0x00b9
            r1.append(r7)
        L_0x00b9:
            int r6 = r6 + 1
            goto L_0x0090
        L_0x00bc:
            java.lang.StringBuilder r1 = (java.lang.StringBuilder) r1
            java.lang.String r0 = r1.toString()
            java.lang.String r1 = "filterTo(StringBuilder(), predicate).toString()"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
            goto L_0x00c9
        L_0x00c8:
            r0 = r4
        L_0x00c9:
            if (r0 == 0) goto L_0x00d0
            java.math.BigDecimal r0 = r10.convertToBigDecimal(r0)
            goto L_0x00d1
        L_0x00d0:
            r0 = r4
        L_0x00d1:
            if (r0 == 0) goto L_0x00fb
            com.bitcoin.mwallet.core.models.wallet.WalletInfoBalance r1 = r10.latestWalletBalance
            if (r1 == 0) goto L_0x00e2
            com.bitcoin.bitcoink.tx.Satoshis r1 = r1.getSatoshis()
            if (r1 == 0) goto L_0x00e2
            java.math.BigDecimal r1 = r1.getCoins()
            goto L_0x00e3
        L_0x00e2:
            r1 = r4
        L_0x00e3:
            if (r1 == 0) goto L_0x00fb
            com.bitcoin.mwallet.core.models.wallet.WalletInfoBalance r1 = r10.latestWalletBalance
            if (r1 == 0) goto L_0x00f3
            com.bitcoin.bitcoink.tx.Satoshis r1 = r1.getSatoshis()
            if (r1 == 0) goto L_0x00f3
            java.math.BigDecimal r4 = r1.getCoins()
        L_0x00f3:
            int r0 = r0.compareTo(r4)
            if (r0 <= 0) goto L_0x00fb
            goto L_0x0045
        L_0x00fb:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.app.flows.sendv2.sendamountselection.SendAmountSelectionPresenter.isInsufficientBalance():boolean");
    }

    public final void setInitialSatoshiAmount(@Nullable Satoshis satoshis) {
        BuildersKt__Builders_commonKt.launch$default(getViewModelScope(), null, null, new SendAmountSelectionPresenter$setInitialSatoshiAmount$1(this, satoshis, null), 3, null);
    }

    public final void init(@Nullable Coin coin, @Nullable SlpId slpId) {
        if (slpId != null) {
            BuildersKt__BuildersKt.runBlocking$default(null, new SendAmountSelectionPresenter$init$1(this, slpId, null), 1, null);
        } else if (coin != null) {
            setCoin(coin);
            getPrimaryEntryType().setValue(EntryType.FIAT);
            BuildersKt__BuildersKt.runBlocking$default(null, new SendAmountSelectionPresenter$init$2(this, coin, null), 1, null);
        } else {
            BuildersKt__BuildersKt.runBlocking$default(null, new SendAmountSelectionPresenter$init$3(this, null), 1, null);
        }
    }

    @NotNull
    public final LiveData<Pair<WalletId, Currency>> getBalanceDependencies() {
        return LiveDataKt.combineLatest(this.selectedWalletId, getDisplayCurrency(), SendAmountSelectionPresenter$getBalanceDependencies$1.INSTANCE);
    }

    public final void onContinueTapped() {
        SendWhatModel sendWhatModel;
        EntryType entryType = (EntryType) getPrimaryEntryType().getValue();
        if (entryType != null) {
            int i = WhenMappings.$EnumSwitchMapping$2[entryType.ordinal()];
            String str = "sendToUri";
            String str2 = "selectedWalletId.value!!";
            String str3 = "displayCurrency.value!!.currencyCode";
            String str4 = "displayCurrency.value!!";
            if (i == 1) {
                BigDecimal fiatAmount = getFiatAmount();
                Coin coin = getCoin();
                Object value = getDisplayCurrency().getValue();
                if (value == null) {
                    Intrinsics.throwNpe();
                }
                Intrinsics.checkExpressionValueIsNotNull(value, str4);
                String currencyCode = ((Currency) value).getCurrencyCode();
                Intrinsics.checkExpressionValueIsNotNull(currencyCode, str3);
                Object value2 = this.selectedWalletId.getValue();
                if (value2 == null) {
                    Intrinsics.throwNpe();
                }
                Intrinsics.checkExpressionValueIsNotNull(value2, str2);
                WalletId walletId = (WalletId) value2;
                BitcoinUriContent bitcoinUriContent = this.sendToUri;
                if (bitcoinUriContent == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(str);
                }
                sendWhatModel = new SendWhatModel(fiatAmount, null, null, coin, currencyCode, walletId, bitcoinUriContent, null, 128, null);
            } else if (i == 2) {
                BigDecimal cryptoAmount = getCryptoAmount();
                Coin coin2 = getCoin();
                Object value3 = getDisplayCurrency().getValue();
                if (value3 == null) {
                    Intrinsics.throwNpe();
                }
                Intrinsics.checkExpressionValueIsNotNull(value3, str4);
                String currencyCode2 = ((Currency) value3).getCurrencyCode();
                Intrinsics.checkExpressionValueIsNotNull(currencyCode2, str3);
                Object value4 = this.selectedWalletId.getValue();
                if (value4 == null) {
                    Intrinsics.throwNpe();
                }
                Intrinsics.checkExpressionValueIsNotNull(value4, str2);
                WalletId walletId2 = (WalletId) value4;
                BitcoinUriContent bitcoinUriContent2 = this.sendToUri;
                if (bitcoinUriContent2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(str);
                }
                sendWhatModel = new SendWhatModel(null, cryptoAmount, null, coin2, currencyCode2, walletId2, bitcoinUriContent2, null, 128, null);
            } else if (i == 3) {
                BigDecimal tokenInputAmount = getTokenInputAmount();
                Coin coin3 = getCoin();
                Object value5 = getDisplayCurrency().getValue();
                if (value5 == null) {
                    Intrinsics.throwNpe();
                }
                Intrinsics.checkExpressionValueIsNotNull(value5, str4);
                String currencyCode3 = ((Currency) value5).getCurrencyCode();
                Intrinsics.checkExpressionValueIsNotNull(currencyCode3, str3);
                Object value6 = this.selectedWalletId.getValue();
                if (value6 == null) {
                    Intrinsics.throwNpe();
                }
                Intrinsics.checkExpressionValueIsNotNull(value6, str2);
                WalletId walletId3 = (WalletId) value6;
                BitcoinUriContent bitcoinUriContent3 = this.sendToUri;
                if (bitcoinUriContent3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(str);
                }
                sendWhatModel = new SendWhatModel(null, null, tokenInputAmount, coin3, currencyCode3, walletId3, bitcoinUriContent3, null, 128, null);
            }
            this.sendAmountRouter.toReview(sendWhatModel);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0036, code lost:
        if (r0 != null) goto L_0x003b;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onSendAllTapped() {
        /*
            r3 = this;
            androidx.lifecycle.MutableLiveData r0 = r3.getPrimaryEntryType()
            java.lang.Object r0 = r0.getValue()
            com.bitcoin.mwallet.app.components.amountselection.AmountSelectionPresenterBase$EntryType r0 = (com.bitcoin.mwallet.app.components.amountselection.AmountSelectionPresenterBase.EntryType) r0
            com.bitcoin.mwallet.app.components.amountselection.AmountSelectionPresenterBase$EntryType r1 = com.bitcoin.mwallet.app.components.amountselection.AmountSelectionPresenterBase.EntryType.TOKEN
            if (r0 != r1) goto L_0x0028
            java.math.BigDecimal r0 = r3.targetWalletTokenBalance
            r3.setTokenInputAmount(r0)
            androidx.lifecycle.MutableLiveData r0 = r3.getPrimaryAmount()
            java.math.BigDecimal r1 = r3.getTokenInputAmount()
            java.lang.String r2 = "tokenInputAmount"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r2)
            java.lang.String r1 = r3.convertBigDecimalToString(r1)
            r0.postValue(r1)
            goto L_0x0062
        L_0x0028:
            com.bitcoin.mwallet.core.models.wallet.WalletInfoBalance r0 = r3.latestWalletBalance
            if (r0 == 0) goto L_0x0039
            com.bitcoin.bitcoink.tx.Satoshis r0 = r0.getSatoshis()
            if (r0 == 0) goto L_0x0039
            java.math.BigDecimal r0 = r0.getCoins()
            if (r0 == 0) goto L_0x0039
            goto L_0x003b
        L_0x0039:
            java.math.BigDecimal r0 = java.math.BigDecimal.ZERO
        L_0x003b:
            androidx.lifecycle.MutableLiveData r1 = r3.getPrimaryEntryType()
            java.lang.Object r1 = r1.getValue()
            com.bitcoin.mwallet.app.components.amountselection.AmountSelectionPresenterBase$EntryType r1 = (com.bitcoin.mwallet.app.components.amountselection.AmountSelectionPresenterBase.EntryType) r1
            com.bitcoin.mwallet.app.components.amountselection.AmountSelectionPresenterBase$EntryType r2 = com.bitcoin.mwallet.app.components.amountselection.AmountSelectionPresenterBase.EntryType.FIAT
            if (r1 != r2) goto L_0x0052
            androidx.lifecycle.MutableLiveData r1 = r3.getPrimaryEntryType()
            com.bitcoin.mwallet.app.components.amountselection.AmountSelectionPresenterBase$EntryType r2 = com.bitcoin.mwallet.app.components.amountselection.AmountSelectionPresenterBase.EntryType.COIN
            r1.setValue(r2)
        L_0x0052:
            androidx.lifecycle.MutableLiveData r1 = r3.getPrimaryAmount()
            java.lang.String r2 = "fullAmount"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r2)
            java.lang.String r0 = r3.convertBigDecimalToString(r0)
            r1.postValue(r0)
        L_0x0062:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.app.flows.sendv2.sendamountselection.SendAmountSelectionPresenter.onSendAllTapped():void");
    }

    public final void onWalletSelectTapped(@NotNull FragmentManager fragmentManager, @NotNull WalletType walletType) {
        Intrinsics.checkParameterIsNotNull(fragmentManager, "fm");
        Intrinsics.checkParameterIsNotNull(walletType, "walletType");
        SendAmountSelectionRouter sendAmountSelectionRouter = this.sendAmountRouter;
        WalletSelectorListener walletSelectorListener = this;
        Slp slp = this.tokenInfo;
        sendAmountSelectionRouter.toWalletSelect(walletSelectorListener, walletType, slp != null ? slp.getTokenId() : null, fragmentManager);
    }

    public void onWalletSelected(@NotNull WalletId walletId, @NotNull WalletType walletType) {
        Intrinsics.checkParameterIsNotNull(walletId, "walletId");
        Intrinsics.checkParameterIsNotNull(walletType, "walletType");
        this.selectedWalletId.setValue(walletId);
    }
}
