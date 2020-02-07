package com.bitcoin.mwallet.app.flows.walletdetails.advanceddetails;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.widget.Toast;
import androidx.lifecycle.LiveData;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.app.viper.ScreenPresenter;
import com.bitcoin.mwallet.core.interactors.GetWalletInteractor;
import com.bitcoin.mwallet.core.models.CopayerId;
import com.bitcoin.mwallet.core.models.p009tx.utxo.WalletUtxos;
import com.bitcoin.mwallet.core.models.wallet.C1261Wallet;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import com.bitcoin.mwallet.core.repositories.UtxoRepository;
import com.bitcoin.mwallet.core.services.address.AddressService;
import com.bitcoin.mwallet.core.utils.signature.SigningKey;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B5\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u0002¢\u0006\u0002\u0010\u000eJ\u000e\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cJ\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001eJ\f\u0010 \u001a\b\u0012\u0004\u0012\u00020!0\u001eJ)\u0010\"\u001a\u00020\u001a2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&H@ø\u0001\u0000¢\u0006\u0002\u0010'J\u0006\u0010(\u001a\u00020\u001aR\u000e\u0010\u000b\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0013\u001a\u00020\u0014X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018\u0002\u0004\n\u0002\b\u0019¨\u0006)"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/walletdetails/advanceddetails/AdvancedDetailsPresenter;", "Lcom/bitcoin/mwallet/app/viper/ScreenPresenter;", "Lcom/bitcoin/mwallet/app/flows/walletdetails/advanceddetails/AdvancedDetailsRouter;", "context", "Landroid/content/Context;", "viewModelScope", "Lkotlinx/coroutines/CoroutineScope;", "getWalletInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetWalletInteractor;", "utxoRepository", "Lcom/bitcoin/mwallet/core/repositories/UtxoRepository;", "addressService", "Lcom/bitcoin/mwallet/core/services/address/AddressService;", "router", "(Landroid/content/Context;Lkotlinx/coroutines/CoroutineScope;Lcom/bitcoin/mwallet/core/interactors/GetWalletInteractor;Lcom/bitcoin/mwallet/core/repositories/UtxoRepository;Lcom/bitcoin/mwallet/core/services/address/AddressService;Lcom/bitcoin/mwallet/app/flows/walletdetails/advanceddetails/AdvancedDetailsRouter;)V", "clipboard", "Landroid/content/ClipboardManager;", "copyToast", "Landroid/widget/Toast;", "walletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "getWalletId", "()Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "setWalletId", "(Lcom/bitcoin/mwallet/core/models/wallet/WalletId;)V", "addToClipboard", "", "content", "", "getUTXOs", "Landroidx/lifecycle/LiveData;", "Lcom/bitcoin/mwallet/core/models/tx/utxo/WalletUtxos;", "getWallet", "Lcom/bitcoin/mwallet/core/models/wallet/Wallet;", "scanForFunds", "copayerId", "Lcom/bitcoin/mwallet/core/models/CopayerId;", "signingKey", "Lcom/bitcoin/mwallet/core/utils/signature/SigningKey;", "(Lcom/bitcoin/mwallet/core/models/wallet/WalletId;Lcom/bitcoin/mwallet/core/models/CopayerId;Lcom/bitcoin/mwallet/core/utils/signature/SigningKey;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toScanForTransactions", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: AdvancedDetailsPresenter.kt */
public final class AdvancedDetailsPresenter extends ScreenPresenter<AdvancedDetailsRouter> {
    private final AddressService addressService;
    private final ClipboardManager clipboard;
    private final Toast copyToast;
    private final GetWalletInteractor getWalletInteractor;
    private final UtxoRepository utxoRepository;
    @NotNull
    public WalletId walletId;

    public AdvancedDetailsPresenter(@NotNull Context context, @NotNull CoroutineScope coroutineScope, @NotNull GetWalletInteractor getWalletInteractor2, @NotNull UtxoRepository utxoRepository2, @NotNull AddressService addressService2, @NotNull AdvancedDetailsRouter advancedDetailsRouter) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(coroutineScope, "viewModelScope");
        Intrinsics.checkParameterIsNotNull(getWalletInteractor2, "getWalletInteractor");
        Intrinsics.checkParameterIsNotNull(utxoRepository2, "utxoRepository");
        Intrinsics.checkParameterIsNotNull(addressService2, "addressService");
        Intrinsics.checkParameterIsNotNull(advancedDetailsRouter, "router");
        super(context, advancedDetailsRouter);
        this.getWalletInteractor = getWalletInteractor2;
        this.utxoRepository = utxoRepository2;
        this.addressService = addressService2;
        Toast makeText = Toast.makeText(context, context.getString(C1018R.string.general_copied), 0);
        Intrinsics.checkExpressionValueIsNotNull(makeText, "Toast.makeText(context, …ied), Toast.LENGTH_SHORT)");
        this.copyToast = makeText;
        Object systemService = context.getSystemService("clipboard");
        if (systemService != null) {
            this.clipboard = (ClipboardManager) systemService;
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.content.ClipboardManager");
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

    public final void addToClipboard(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, Param.CONTENT);
        this.clipboard.setPrimaryClip(ClipData.newPlainText("settings", str));
        this.copyToast.show();
    }

    @NotNull
    public final LiveData<C1261Wallet> getWallet() {
        GetWalletInteractor getWalletInteractor2 = this.getWalletInteractor;
        WalletId walletId2 = this.walletId;
        if (walletId2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("walletId");
        }
        return getWalletInteractor2.wallet(walletId2);
    }

    @NotNull
    public final LiveData<WalletUtxos> getUTXOs() {
        UtxoRepository utxoRepository2 = this.utxoRepository;
        WalletId walletId2 = this.walletId;
        if (walletId2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("walletId");
        }
        return utxoRepository2.walletUtxos(walletId2);
    }

    @Nullable
    public final Object scanForFunds(@NotNull WalletId walletId2, @NotNull CopayerId copayerId, @NotNull SigningKey signingKey, @NotNull Continuation<? super Unit> continuation) {
        return this.addressService.scanForFunds(walletId2, copayerId, signingKey, continuation);
    }

    public final void toScanForTransactions() {
        AdvancedDetailsRouter advancedDetailsRouter = (AdvancedDetailsRouter) getRouter();
        WalletId walletId2 = this.walletId;
        if (walletId2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("walletId");
        }
        advancedDetailsRouter.toScanForTransactions(walletId2);
    }
}
