package com.bitcoin.mwallet.app.flows.receive.receive;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.Toast;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import com.bitcoin.bitcoink.address.Address;
import com.bitcoin.mwallet.ApplicationClass;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.ReceiveActivity;
import com.bitcoin.mwallet.app.components.walletselector.WalletSelectorListener;
import com.bitcoin.mwallet.app.flows.receive.receive.entity.ReceiveAmount;
import com.bitcoin.mwallet.app.viper.ScreenPresenter;
import com.bitcoin.mwallet.core.entity.WalletType;
import com.bitcoin.mwallet.core.interactors.GetAddressInteractor;
import com.bitcoin.mwallet.core.interactors.GetWalletInteractor;
import com.bitcoin.mwallet.core.models.BitcoinUriContent;
import com.bitcoin.mwallet.core.models.Coin;
import com.bitcoin.mwallet.core.models.wallet.C1261Wallet;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import com.google.zxing.BarcodeFormat;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import java.math.BigDecimal;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.bitcoinj.uri.BitcoinURI;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import timber.log.Timber;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000®\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 l2\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u0001lB-\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\u0002¢\u0006\u0002\u0010\rJ\u000e\u0010I\u001a\u00020*2\u0006\u0010J\u001a\u00020\"J\u0006\u0010K\u001a\u00020LJ\u0012\u0010M\u001a\u0004\u0018\u00010;2\u0006\u0010N\u001a\u00020&H\u0002J\u0006\u0010O\u001a\u00020LJ\u0019\u0010P\u001a\u00020 2\u0006\u0010Q\u001a\u000201H@ø\u0001\u0000¢\u0006\u0002\u0010RJ\u0019\u0010S\u001a\u00020 2\u0006\u0010Q\u001a\u000201H@ø\u0001\u0000¢\u0006\u0002\u0010RJ\u0010\u0010T\u001a\u0004\u0018\u00010\u00182\u0006\u0010Q\u001a\u000201J\u0010\u0010U\u001a\u0004\u0018\u00010V2\u0006\u0010Q\u001a\u000201J\u0006\u0010W\u001a\u00020LJ\u0010\u0010X\u001a\u00020L2\b\u0010Y\u001a\u0004\u0018\u000101J\u0018\u0010Z\u001a\u00020L2\u0006\u0010Q\u001a\u0002012\u0006\u0010J\u001a\u00020\"H\u0016J\u0006\u0010[\u001a\u00020LJ\u000e\u0010\\\u001a\u00020L2\u0006\u0010Q\u001a\u000201J\u000e\u0010]\u001a\u00020L2\u0006\u0010^\u001a\u00020 J\u000e\u0010_\u001a\u00020L2\u0006\u0010`\u001a\u00020*J\u000e\u0010a\u001a\u00020L2\u0006\u0010b\u001a\u00020\"J\u0018\u0010c\u001a\u00020&2\u0006\u0010d\u001a\u00020&2\b\u0010e\u001a\u0004\u0018\u00010fJ\u0006\u0010g\u001a\u00020LJ\u000e\u0010h\u001a\u00020L2\u0006\u0010i\u001a\u00020jJ\u0010\u0010k\u001a\u00020L2\u0006\u0010N\u001a\u00020&H\u0002R \u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001d\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\u00170\u0016¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u000e\u0010\u001b\u001a\u00020\u001cX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001f\u001a\u0004\u0018\u00010 X\u000e¢\u0006\u0002\n\u0000R \u0010!\u001a\b\u0012\u0004\u0012\u00020\"0\u000fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u0012\"\u0004\b$\u0010\u0014R \u0010%\u001a\b\u0012\u0004\u0012\u00020&0\u000fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010\u0012\"\u0004\b(\u0010\u0014R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020*X\u000e¢\u0006\u0002\n\u0000R\u001a\u0010+\u001a\u00020*X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R\u001c\u00100\u001a\u0004\u0018\u000101X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u00103\"\u0004\b4\u00105R\u001c\u00106\u001a\u0004\u0018\u000101X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u00103\"\u0004\b8\u00105R \u00109\u001a\b\u0012\u0004\u0012\u00020;0:X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b<\u0010=\"\u0004\b>\u0010?R\u000e\u0010@\u001a\u00020AX\u0004¢\u0006\u0002\n\u0000R\"\u0010B\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001010\u000fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bC\u0010\u0012\"\u0004\bD\u0010\u0014R(\u0010E\u001a\u0010\u0012\f\u0012\n F*\u0004\u0018\u00010*0*0\u000fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bG\u0010\u0012\"\u0004\bH\u0010\u0014R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006m"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/receive/receive/ReceivePresenter;", "Lcom/bitcoin/mwallet/app/viper/ScreenPresenter;", "Lcom/bitcoin/mwallet/app/flows/receive/receive/ReceiveRouter;", "Lcom/bitcoin/mwallet/app/components/walletselector/WalletSelectorListener;", "context", "Landroid/content/Context;", "viewModelScope", "Lkotlinx/coroutines/CoroutineScope;", "getAddressInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetAddressInteractor;", "getWalletInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetWalletInteractor;", "router", "(Landroid/content/Context;Lkotlinx/coroutines/CoroutineScope;Lcom/bitcoin/mwallet/core/interactors/GetAddressInteractor;Lcom/bitcoin/mwallet/core/interactors/GetWalletInteractor;Lcom/bitcoin/mwallet/app/flows/receive/receive/ReceiveRouter;)V", "amount", "Landroidx/lifecycle/MutableLiveData;", "Lcom/bitcoin/mwallet/app/flows/receive/receive/entity/ReceiveAmount;", "getAmount", "()Landroidx/lifecycle/MutableLiveData;", "setAmount", "(Landroidx/lifecycle/MutableLiveData;)V", "availableWallets", "Landroidx/lifecycle/LiveData;", "", "Lcom/bitcoin/mwallet/core/models/wallet/Wallet;", "getAvailableWallets", "()Landroidx/lifecycle/LiveData;", "clipboard", "Landroid/content/ClipboardManager;", "copyToast", "Landroid/widget/Toast;", "currentReceiveAddress", "Lcom/bitcoin/bitcoink/address/Address;", "currentWalletType", "Lcom/bitcoin/mwallet/core/entity/WalletType;", "getCurrentWalletType", "setCurrentWalletType", "displayedAddress", "", "getDisplayedAddress", "setDisplayedAddress", "initialized", "", "initializedPreferredId", "getInitializedPreferredId", "()Z", "setInitializedPreferredId", "(Z)V", "prevCashId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "getPrevCashId", "()Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "setPrevCashId", "(Lcom/bitcoin/mwallet/core/models/wallet/WalletId;)V", "prevCoreId", "getPrevCoreId", "setPrevCoreId", "qrCode", "Landroidx/lifecycle/MediatorLiveData;", "Landroid/graphics/Bitmap;", "getQrCode", "()Landroidx/lifecycle/MediatorLiveData;", "setQrCode", "(Landroidx/lifecycle/MediatorLiveData;)V", "qrCodeSize", "", "selectedWalletId", "getSelectedWalletId", "setSelectedWalletId", "useBitcoinCashLegacy", "kotlin.jvm.PlatformType", "getUseBitcoinCashLegacy", "setUseBitcoinCashLegacy", "checkWalletsExist", "walletType", "copyReceiveAddress", "", "createQrCode", "rawString", "generateAndSetNewReceiveAddress", "getCurrentReceiveAddress", "walletId", "(Lcom/bitcoin/mwallet/core/models/wallet/WalletId;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getNextReceiveAddress", "getWallet", "getWalletCoin", "Lcom/bitcoin/mwallet/core/models/Coin;", "initialize", "onActivityCreated", "walletPk", "onWalletSelected", "setAddress", "setPreferredId", "setReceiveAddress", "address", "setUseLegacy", "useLegacy", "setWalletType", "type", "toRequestAmountAddress", "prefix", "coins", "Ljava/math/BigDecimal;", "toSelectAmount", "toSelectWallet", "fm", "Landroidx/fragment/app/FragmentManager;", "updateQrCode", "Companion", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: ReceivePresenter.kt */
public final class ReceivePresenter extends ScreenPresenter<ReceiveRouter> implements WalletSelectorListener {
    public static final Companion Companion = new Companion(null);
    private static final BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
    @NotNull
    public MutableLiveData<ReceiveAmount> amount;
    @NotNull
    private final LiveData<List<C1261Wallet>> availableWallets;
    private final ClipboardManager clipboard;
    /* access modifiers changed from: private */
    public final Context context;
    private final Toast copyToast;
    private Address currentReceiveAddress;
    @NotNull
    private MutableLiveData<WalletType> currentWalletType;
    @NotNull
    private MutableLiveData<String> displayedAddress;
    private final GetAddressInteractor getAddressInteractor;
    /* access modifiers changed from: private */
    public final GetWalletInteractor getWalletInteractor;
    private boolean initialized;
    private boolean initializedPreferredId;
    @Nullable
    private WalletId prevCashId;
    @Nullable
    private WalletId prevCoreId;
    @NotNull
    private MediatorLiveData<Bitmap> qrCode;
    private final int qrCodeSize = ((int) this.context.getResources().getDimension(C1018R.dimen.qr_code_size));
    @NotNull
    private MutableLiveData<WalletId> selectedWalletId;
    @NotNull
    private MutableLiveData<Boolean> useBitcoinCashLegacy;
    private final CoroutineScope viewModelScope;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0005"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/receive/receive/ReceivePresenter$Companion;", "", "()V", "barcodeEncoder", "Lcom/journeyapps/barcodescanner/BarcodeEncoder;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: ReceivePresenter.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(mo37403bv = {1, 0, 3}, mo37406k = 3, mo37407mv = {1, 1, 15})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[Coin.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1 = new int[WalletType.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$2 = new int[WalletType.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$3 = new int[WalletType.values().length];

        static {
            $EnumSwitchMapping$0[Coin.BCH.ordinal()] = 1;
            $EnumSwitchMapping$0[Coin.BTC.ordinal()] = 2;
            $EnumSwitchMapping$1[WalletType.BCH.ordinal()] = 1;
            $EnumSwitchMapping$1[WalletType.BTC.ordinal()] = 2;
            $EnumSwitchMapping$1[WalletType.SLP.ordinal()] = 3;
            $EnumSwitchMapping$2[WalletType.BTC.ordinal()] = 1;
            $EnumSwitchMapping$2[WalletType.BCH.ordinal()] = 2;
            $EnumSwitchMapping$2[WalletType.SLP.ordinal()] = 3;
            $EnumSwitchMapping$3[WalletType.BTC.ordinal()] = 1;
            $EnumSwitchMapping$3[WalletType.BCH.ordinal()] = 2;
            $EnumSwitchMapping$3[WalletType.SLP.ordinal()] = 3;
        }
    }

    public ReceivePresenter(@NotNull Context context2, @NotNull CoroutineScope coroutineScope, @NotNull GetAddressInteractor getAddressInteractor2, @NotNull GetWalletInteractor getWalletInteractor2, @NotNull ReceiveRouter receiveRouter) {
        Intrinsics.checkParameterIsNotNull(context2, "context");
        Intrinsics.checkParameterIsNotNull(coroutineScope, "viewModelScope");
        Intrinsics.checkParameterIsNotNull(getAddressInteractor2, "getAddressInteractor");
        Intrinsics.checkParameterIsNotNull(getWalletInteractor2, "getWalletInteractor");
        Intrinsics.checkParameterIsNotNull(receiveRouter, "router");
        super(context2, receiveRouter);
        this.context = context2;
        this.viewModelScope = coroutineScope;
        this.getAddressInteractor = getAddressInteractor2;
        this.getWalletInteractor = getWalletInteractor2;
        Object systemService = this.context.getSystemService("clipboard");
        if (systemService != null) {
            this.clipboard = (ClipboardManager) systemService;
            Context context3 = this.context;
            Toast makeText = Toast.makeText(context3, context3.getString(C1018R.string.receive_address_copied), 0);
            Intrinsics.checkExpressionValueIsNotNull(makeText, "Toast.makeText(context, …ied), Toast.LENGTH_SHORT)");
            this.copyToast = makeText;
            this.currentWalletType = new MutableLiveData<>();
            this.selectedWalletId = new MutableLiveData<>();
            this.useBitcoinCashLegacy = new MutableLiveData<>(Boolean.valueOf(false));
            this.displayedAddress = new MutableLiveData<>();
            this.qrCode = new MediatorLiveData<>();
            this.availableWallets = this.getWalletInteractor.getWallets();
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.content.ClipboardManager");
    }

    @NotNull
    public final MutableLiveData<WalletType> getCurrentWalletType() {
        return this.currentWalletType;
    }

    public final void setCurrentWalletType(@NotNull MutableLiveData<WalletType> mutableLiveData) {
        Intrinsics.checkParameterIsNotNull(mutableLiveData, "<set-?>");
        this.currentWalletType = mutableLiveData;
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
    public final MutableLiveData<Boolean> getUseBitcoinCashLegacy() {
        return this.useBitcoinCashLegacy;
    }

    public final void setUseBitcoinCashLegacy(@NotNull MutableLiveData<Boolean> mutableLiveData) {
        Intrinsics.checkParameterIsNotNull(mutableLiveData, "<set-?>");
        this.useBitcoinCashLegacy = mutableLiveData;
    }

    @NotNull
    public final MutableLiveData<String> getDisplayedAddress() {
        return this.displayedAddress;
    }

    public final void setDisplayedAddress(@NotNull MutableLiveData<String> mutableLiveData) {
        Intrinsics.checkParameterIsNotNull(mutableLiveData, "<set-?>");
        this.displayedAddress = mutableLiveData;
    }

    @NotNull
    public final MediatorLiveData<Bitmap> getQrCode() {
        return this.qrCode;
    }

    public final void setQrCode(@NotNull MediatorLiveData<Bitmap> mediatorLiveData) {
        Intrinsics.checkParameterIsNotNull(mediatorLiveData, "<set-?>");
        this.qrCode = mediatorLiveData;
    }

    @NotNull
    public final MutableLiveData<ReceiveAmount> getAmount() {
        MutableLiveData<ReceiveAmount> mutableLiveData = this.amount;
        if (mutableLiveData == null) {
            Intrinsics.throwUninitializedPropertyAccessException(BitcoinURI.FIELD_AMOUNT);
        }
        return mutableLiveData;
    }

    public final void setAmount(@NotNull MutableLiveData<ReceiveAmount> mutableLiveData) {
        Intrinsics.checkParameterIsNotNull(mutableLiveData, "<set-?>");
        this.amount = mutableLiveData;
    }

    @NotNull
    public final LiveData<List<C1261Wallet>> getAvailableWallets() {
        return this.availableWallets;
    }

    @Nullable
    public final WalletId getPrevCashId() {
        return this.prevCashId;
    }

    public final void setPrevCashId(@Nullable WalletId walletId) {
        this.prevCashId = walletId;
    }

    @Nullable
    public final WalletId getPrevCoreId() {
        return this.prevCoreId;
    }

    public final void setPrevCoreId(@Nullable WalletId walletId) {
        this.prevCoreId = walletId;
    }

    public final boolean getInitializedPreferredId() {
        return this.initializedPreferredId;
    }

    public final void setInitializedPreferredId(boolean z) {
        this.initializedPreferredId = z;
    }

    @Nullable
    public final Coin getWalletCoin(@NotNull WalletId walletId) {
        Intrinsics.checkParameterIsNotNull(walletId, "walletId");
        return (Coin) BuildersKt__BuildersKt.runBlocking$default(null, new ReceivePresenter$getWalletCoin$1(this, walletId, null), 1, null);
    }

    public final void setPreferredId(@NotNull WalletId walletId) {
        Intrinsics.checkParameterIsNotNull(walletId, "walletId");
        BuildersKt__Builders_commonKt.launch$default(this.viewModelScope, null, null, new ReceivePresenter$setPreferredId$1(this, walletId, null), 3, null);
        this.initializedPreferredId = true;
    }

    public final void initialize() {
        if (!this.initialized) {
            WalletId walletId = (WalletId) BuildersKt__BuildersKt.runBlocking$default(null, new ReceivePresenter$initialize$initWalletId$1(this, null), 1, null);
            if (walletId != null) {
                Context context2 = this.context;
                if (context2 != null) {
                    Activity activityContext = ((ApplicationClass) context2).getActivityContext();
                    if (activityContext != null) {
                        this.amount = ((ReceiveActivity) activityContext).getGlobalReceiveModel().getAmount();
                        this.selectedWalletId.setValue(walletId);
                        Coin walletCoin = getWalletCoin(walletId);
                        if (walletCoin != null) {
                            int i = WhenMappings.$EnumSwitchMapping$0[walletCoin.ordinal()];
                            if (i == 1) {
                                setWalletType(WalletType.BCH);
                            } else if (i == 2) {
                                setWalletType(WalletType.BTC);
                            }
                        }
                        setAddress();
                        this.initialized = true;
                        return;
                    }
                    throw new TypeCastException("null cannot be cast to non-null type com.bitcoin.mwallet.ReceiveActivity");
                }
                throw new TypeCastException("null cannot be cast to non-null type com.bitcoin.mwallet.ApplicationClass");
            }
        }
    }

    @Nullable
    public final C1261Wallet getWallet(@NotNull WalletId walletId) {
        Intrinsics.checkParameterIsNotNull(walletId, "walletId");
        return (C1261Wallet) BuildersKt__BuildersKt.runBlocking$default(null, new ReceivePresenter$getWallet$1(this, walletId, null), 1, null);
    }

    public final void setUseLegacy(boolean z) {
        this.useBitcoinCashLegacy.setValue(Boolean.valueOf(z));
        setAddress();
    }

    public final void setWalletType(@NotNull WalletType walletType) {
        Intrinsics.checkParameterIsNotNull(walletType, "type");
        if (((WalletType) this.currentWalletType.getValue()) != walletType) {
            BuildersKt__Builders_commonKt.launch$default(this.viewModelScope, null, null, new ReceivePresenter$setWalletType$1(this, walletType, null), 3, null);
            setAddress();
        }
    }

    private final void updateQrCode(String str) {
        this.qrCode.postValue(createQrCode(str));
    }

    public final void setAddress() {
        BuildersKt__Builders_commonKt.launch$default(this.viewModelScope, null, null, new ReceivePresenter$setAddress$1(this, null), 3, null);
    }

    public final void generateAndSetNewReceiveAddress() {
        BuildersKt__Builders_commonKt.launch$default(this.viewModelScope, null, null, new ReceivePresenter$generateAndSetNewReceiveAddress$1(this, null), 3, null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x00ae  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x00b7  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void setReceiveAddress(@org.jetbrains.annotations.NotNull com.bitcoin.bitcoink.address.Address r6) {
        /*
            r5 = this;
            java.lang.String r0 = "address"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r6, r0)
            r5.currentReceiveAddress = r6
            androidx.lifecycle.MutableLiveData<com.bitcoin.mwallet.core.entity.WalletType> r0 = r5.currentWalletType
            java.lang.Object r0 = r0.getValue()
            com.bitcoin.mwallet.core.entity.WalletType r0 = (com.bitcoin.mwallet.core.entity.WalletType) r0
            java.lang.String r1 = ""
            if (r0 != 0) goto L_0x0014
            goto L_0x0025
        L_0x0014:
            int[] r2 = com.bitcoin.mwallet.app.flows.receive.receive.ReceivePresenter.WhenMappings.$EnumSwitchMapping$2
            int r0 = r0.ordinal()
            r0 = r2[r0]
            r2 = 1
            if (r0 == r2) goto L_0x008c
            r3 = 2
            if (r0 == r3) goto L_0x0041
            r2 = 3
            if (r0 == r2) goto L_0x0028
        L_0x0025:
            r6 = r1
            goto L_0x00a8
        L_0x0028:
            com.bitcoin.bitcoink.address.AddressSLP r0 = r6.toSlp()
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "simpleledger:"
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            java.lang.String r1 = kotlin.text.StringsKt.removePrefix(r0, r1)
            com.bitcoin.bitcoink.address.AddressSLP r6 = r6.toSlp()
            java.lang.String r6 = r6.toString()
            goto L_0x00a5
        L_0x0041:
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r0 = r5.useBitcoinCashLegacy
            java.lang.Object r0 = r0.getValue()
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r2)
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            java.lang.String r1 = "bitcoincash:"
            if (r0 == 0) goto L_0x006d
            com.bitcoin.bitcoink.address.AddressLegacy r6 = r6.toLegacy()
            java.lang.String r6 = java.lang.String.valueOf(r6)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r1)
            r0.append(r6)
            java.lang.String r1 = r0.toString()
            goto L_0x00a8
        L_0x006d:
            com.bitcoin.bitcoink.address.AddressCash r6 = r6.toCash()
            java.lang.String r6 = r6.toString()
            r0 = r1
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            java.lang.String r6 = kotlin.text.StringsKt.removePrefix(r6, r0)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r1)
            r0.append(r6)
            java.lang.String r1 = r0.toString()
            goto L_0x00a8
        L_0x008c:
            com.bitcoin.bitcoink.address.AddressLegacy r6 = r6.toLegacy()
            java.lang.String r1 = java.lang.String.valueOf(r6)
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r0 = "bitcoin:"
            r6.append(r0)
            r6.append(r1)
            java.lang.String r6 = r6.toString()
        L_0x00a5:
            r4 = r1
            r1 = r6
            r6 = r4
        L_0x00a8:
            androidx.lifecycle.MutableLiveData<com.bitcoin.mwallet.app.flows.receive.receive.entity.ReceiveAmount> r0 = r5.amount
            java.lang.String r2 = "amount"
            if (r0 != 0) goto L_0x00b1
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
        L_0x00b1:
            java.lang.Object r0 = r0.getValue()
            if (r0 == 0) goto L_0x00d0
            androidx.lifecycle.MutableLiveData<com.bitcoin.mwallet.app.flows.receive.receive.entity.ReceiveAmount> r0 = r5.amount
            if (r0 != 0) goto L_0x00be
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
        L_0x00be:
            java.lang.Object r0 = r0.getValue()
            com.bitcoin.mwallet.app.flows.receive.receive.entity.ReceiveAmount r0 = (com.bitcoin.mwallet.app.flows.receive.receive.entity.ReceiveAmount) r0
            if (r0 == 0) goto L_0x00cb
            java.math.BigDecimal r0 = r0.getCrypto()
            goto L_0x00cc
        L_0x00cb:
            r0 = 0
        L_0x00cc:
            java.lang.String r1 = r5.toRequestAmountAddress(r1, r0)
        L_0x00d0:
            androidx.lifecycle.MutableLiveData<java.lang.String> r0 = r5.displayedAddress
            r0.postValue(r6)
            r5.updateQrCode(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.app.flows.receive.receive.ReceivePresenter.setReceiveAddress(com.bitcoin.bitcoink.address.Address):void");
    }

    public final void copyReceiveAddress() {
        String str;
        WalletType walletType = (WalletType) this.currentWalletType.getValue();
        if (walletType != null) {
            int i = WhenMappings.$EnumSwitchMapping$3[walletType.ordinal()];
            if (i == 1) {
                StringBuilder sb = new StringBuilder();
                sb.append(BitcoinUriContent.PREFIX_BITCOIN);
                sb.append((String) this.displayedAddress.getValue());
                str = sb.toString();
            } else if (i == 2) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(BitcoinUriContent.PREFIX_BITCOIN_CASH);
                sb2.append((String) this.displayedAddress.getValue());
                str = sb2.toString();
            } else if (i == 3) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append("simpleledger:");
                sb3.append((String) this.displayedAddress.getValue());
                str = sb3.toString();
            }
            String string = this.context.getString(C1018R.string.receive_address_clip_label);
            Intrinsics.checkExpressionValueIsNotNull(string, "context.getString(R.stri…ceive_address_clip_label)");
            this.clipboard.setPrimaryClip(ClipData.newPlainText(string, str));
            this.copyToast.show();
        }
        str = (String) this.displayedAddress.getValue();
        String string2 = this.context.getString(C1018R.string.receive_address_clip_label);
        Intrinsics.checkExpressionValueIsNotNull(string2, "context.getString(R.stri…ceive_address_clip_label)");
        this.clipboard.setPrimaryClip(ClipData.newPlainText(string2, str));
        this.copyToast.show();
    }

    public final void onActivityCreated(@Nullable WalletId walletId) {
        BuildersKt__Builders_commonKt.launch$default(this.viewModelScope, null, null, new ReceivePresenter$onActivityCreated$1(this, walletId, null), 3, null);
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0066, code lost:
        if (r7 != null) goto L_0x0078;
     */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0049  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0062  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0075 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ java.lang.Object getNextReceiveAddress(@org.jetbrains.annotations.NotNull com.bitcoin.mwallet.core.models.wallet.WalletId r6, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.bitcoin.bitcoink.address.Address> r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof com.bitcoin.mwallet.app.flows.receive.receive.ReceivePresenter$getNextReceiveAddress$1
            if (r0 == 0) goto L_0x0014
            r0 = r7
            com.bitcoin.mwallet.app.flows.receive.receive.ReceivePresenter$getNextReceiveAddress$1 r0 = (com.bitcoin.mwallet.app.flows.receive.receive.ReceivePresenter$getNextReceiveAddress$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L_0x0019
        L_0x0014:
            com.bitcoin.mwallet.app.flows.receive.receive.ReceivePresenter$getNextReceiveAddress$1 r0 = new com.bitcoin.mwallet.app.flows.receive.receive.ReceivePresenter$getNextReceiveAddress$1
            r0.<init>(r5, r7)
        L_0x0019:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x0049
            if (r2 == r4) goto L_0x003d
            if (r2 != r3) goto L_0x0035
            java.lang.Object r6 = r0.L$1
            com.bitcoin.mwallet.core.models.wallet.WalletId r6 = (com.bitcoin.mwallet.core.models.wallet.WalletId) r6
            java.lang.Object r6 = r0.L$0
            com.bitcoin.mwallet.app.flows.receive.receive.ReceivePresenter r6 = (com.bitcoin.mwallet.app.flows.receive.receive.ReceivePresenter) r6
            kotlin.ResultKt.throwOnFailure(r7)
            goto L_0x0076
        L_0x0035:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x003d:
            java.lang.Object r6 = r0.L$1
            com.bitcoin.mwallet.core.models.wallet.WalletId r6 = (com.bitcoin.mwallet.core.models.wallet.WalletId) r6
            java.lang.Object r2 = r0.L$0
            com.bitcoin.mwallet.app.flows.receive.receive.ReceivePresenter r2 = (com.bitcoin.mwallet.app.flows.receive.receive.ReceivePresenter) r2
            kotlin.ResultKt.throwOnFailure(r7)
            goto L_0x005e
        L_0x0049:
            kotlin.ResultKt.throwOnFailure(r7)
            com.bitcoin.mwallet.core.interactors.GetAddressInteractor r7 = r5.getAddressInteractor
            com.bitcoin.mwallet.core.models.address.AddressPurpose r2 = com.bitcoin.mwallet.core.models.address.AddressPurpose.RECEIVE
            r0.L$0 = r5
            r0.L$1 = r6
            r0.label = r4
            java.lang.Object r7 = r7.getNextAvailableAddress(r6, r2, r0)
            if (r7 != r1) goto L_0x005d
            return r1
        L_0x005d:
            r2 = r5
        L_0x005e:
            com.bitcoin.mwallet.core.models.address.AddressAndPath r7 = (com.bitcoin.mwallet.core.models.address.AddressAndPath) r7
            if (r7 == 0) goto L_0x0069
            com.bitcoin.bitcoink.address.Address r7 = r7.getAddress()
            if (r7 == 0) goto L_0x0069
            goto L_0x0078
        L_0x0069:
            r0.L$0 = r2
            r0.L$1 = r6
            r0.label = r3
            java.lang.Object r7 = r2.getCurrentReceiveAddress(r6, r0)
            if (r7 != r1) goto L_0x0076
            return r1
        L_0x0076:
            com.bitcoin.bitcoink.address.Address r7 = (com.bitcoin.bitcoink.address.Address) r7
        L_0x0078:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.app.flows.receive.receive.ReceivePresenter.getNextReceiveAddress(com.bitcoin.mwallet.core.models.wallet.WalletId, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x003a  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ java.lang.Object getCurrentReceiveAddress(@org.jetbrains.annotations.NotNull com.bitcoin.mwallet.core.models.wallet.WalletId r5, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.bitcoin.bitcoink.address.Address> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof com.bitcoin.mwallet.app.flows.receive.receive.ReceivePresenter$getCurrentReceiveAddress$1
            if (r0 == 0) goto L_0x0014
            r0 = r6
            com.bitcoin.mwallet.app.flows.receive.receive.ReceivePresenter$getCurrentReceiveAddress$1 r0 = (com.bitcoin.mwallet.app.flows.receive.receive.ReceivePresenter$getCurrentReceiveAddress$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L_0x0019
        L_0x0014:
            com.bitcoin.mwallet.app.flows.receive.receive.ReceivePresenter$getCurrentReceiveAddress$1 r0 = new com.bitcoin.mwallet.app.flows.receive.receive.ReceivePresenter$getCurrentReceiveAddress$1
            r0.<init>(r4, r6)
        L_0x0019:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x003a
            if (r2 != r3) goto L_0x0032
            java.lang.Object r5 = r0.L$1
            com.bitcoin.mwallet.core.models.wallet.WalletId r5 = (com.bitcoin.mwallet.core.models.wallet.WalletId) r5
            java.lang.Object r5 = r0.L$0
            com.bitcoin.mwallet.app.flows.receive.receive.ReceivePresenter r5 = (com.bitcoin.mwallet.app.flows.receive.receive.ReceivePresenter) r5
            kotlin.ResultKt.throwOnFailure(r6)
            goto L_0x004e
        L_0x0032:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x003a:
            kotlin.ResultKt.throwOnFailure(r6)
            com.bitcoin.mwallet.core.interactors.GetAddressInteractor r6 = r4.getAddressInteractor
            com.bitcoin.mwallet.core.models.address.AddressPurpose r2 = com.bitcoin.mwallet.core.models.address.AddressPurpose.RECEIVE
            r0.L$0 = r4
            r0.L$1 = r5
            r0.label = r3
            java.lang.Object r6 = r6.getCurrentAddress(r5, r2, r0)
            if (r6 != r1) goto L_0x004e
            return r1
        L_0x004e:
            com.bitcoin.mwallet.core.models.address.AddressAndPath r6 = (com.bitcoin.mwallet.core.models.address.AddressAndPath) r6
            com.bitcoin.bitcoink.address.Address r5 = r6.getAddress()
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.app.flows.receive.receive.ReceivePresenter.getCurrentReceiveAddress(com.bitcoin.mwallet.core.models.wallet.WalletId, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final Bitmap createQrCode(String str) {
        try {
            return barcodeEncoder.encodeBitmap(str, BarcodeFormat.QR_CODE, this.qrCodeSize, this.qrCodeSize);
        } catch (Throwable th) {
            Timber.m431e(th, "Failed to encode bitmap", new Object[0]);
            return null;
        }
    }

    @NotNull
    public final String toRequestAmountAddress(@NotNull String str, @Nullable BigDecimal bigDecimal) {
        Intrinsics.checkParameterIsNotNull(str, "prefix");
        if (bigDecimal == null) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append("?amount=");
        sb.append(bigDecimal);
        return sb.toString();
    }

    public final void toSelectAmount() {
        if (((WalletType) this.currentWalletType.getValue()) == WalletType.BCH) {
            ((ReceiveRouter) getRouter()).toSelectAmount(Coin.BCH);
        } else {
            ((ReceiveRouter) getRouter()).toSelectAmount(Coin.BTC);
        }
    }

    public final void toSelectWallet(@NotNull FragmentManager fragmentManager) {
        Intrinsics.checkParameterIsNotNull(fragmentManager, "fm");
        ReceiveRouter receiveRouter = (ReceiveRouter) getRouter();
        WalletSelectorListener walletSelectorListener = this;
        WalletType walletType = (WalletType) this.currentWalletType.getValue();
        if (walletType == null) {
            walletType = WalletType.BCH;
        }
        receiveRouter.toWalletSelect(walletSelectorListener, walletType, fragmentManager);
    }

    public void onWalletSelected(@NotNull WalletId walletId, @NotNull WalletType walletType) {
        Intrinsics.checkParameterIsNotNull(walletId, "walletId");
        Intrinsics.checkParameterIsNotNull(walletType, "walletType");
        this.selectedWalletId.setValue(walletId);
        setWalletType(walletType);
    }

    public final boolean checkWalletsExist(@NotNull WalletType walletType) {
        Intrinsics.checkParameterIsNotNull(walletType, "walletType");
        return ((Boolean) BuildersKt__BuildersKt.runBlocking$default(null, new ReceivePresenter$checkWalletsExist$1(this, walletType, null), 1, null)).booleanValue();
    }
}
