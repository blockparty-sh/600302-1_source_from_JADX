package com.bitcoin.mwallet.app.flows.walletdetails.scanfortransactions;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.bitcoin.mwallet.app.viper.ScreenPresenter;
import com.bitcoin.mwallet.core.interactors.GetWalletInteractor;
import com.bitcoin.mwallet.core.models.CopayerId;
import com.bitcoin.mwallet.core.models.wallet.C1261Wallet;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import com.bitcoin.mwallet.core.services.address.AddressService;
import com.bitcoin.mwallet.core.utils.signature.SigningKey;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B5\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eJ\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00100\u001fJ\f\u0010 \u001a\b\u0012\u0004\u0012\u00020!0\u001fJ)\u0010\"\u001a\u00020#2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'H@ø\u0001\u0000¢\u0006\u0002\u0010(R\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\u00020\u0010XD¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u000e\u0010\f\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014XD¢\u0006\u0002\n\u0000R\u0016\u0010\u0015\u001a\n \u0017*\u0004\u0018\u00010\u00160\u0016X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0018\u001a\u00020\u0019X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001d\u0002\u0004\n\u0002\b\u0019¨\u0006)"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/walletdetails/scanfortransactions/ScanForTransactionsPresenter;", "Lcom/bitcoin/mwallet/app/viper/ScreenPresenter;", "Lcom/bitcoin/mwallet/app/flows/walletdetails/scanfortransactions/ScanForTransactionsRouter;", "context", "Landroid/content/Context;", "viewModelScope", "Lkotlinx/coroutines/CoroutineScope;", "interactor", "Lcom/bitcoin/mwallet/app/flows/walletdetails/scanfortransactions/ScanForTransactionsInteractor;", "router", "addressService", "Lcom/bitcoin/mwallet/core/services/address/AddressService;", "getWalletInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetWalletInteractor;", "(Landroid/content/Context;Lkotlinx/coroutines/CoroutineScope;Lcom/bitcoin/mwallet/app/flows/walletdetails/scanfortransactions/ScanForTransactionsInteractor;Lcom/bitcoin/mwallet/app/flows/walletdetails/scanfortransactions/ScanForTransactionsRouter;Lcom/bitcoin/mwallet/core/services/address/AddressService;Lcom/bitcoin/mwallet/core/interactors/GetWalletInteractor;)V", "enableAfter", "", "getEnableAfter", "()J", "key", "", "sharedPreferences", "Landroid/content/SharedPreferences;", "kotlin.jvm.PlatformType", "walletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "getWalletId", "()Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "setWalletId", "(Lcom/bitcoin/mwallet/core/models/wallet/WalletId;)V", "checkProgress", "Landroidx/lifecycle/LiveData;", "getWallet", "Lcom/bitcoin/mwallet/core/models/wallet/Wallet;", "scanForFunds", "", "copayerId", "Lcom/bitcoin/mwallet/core/models/CopayerId;", "signingKey", "Lcom/bitcoin/mwallet/core/utils/signature/SigningKey;", "(Lcom/bitcoin/mwallet/core/models/wallet/WalletId;Lcom/bitcoin/mwallet/core/models/CopayerId;Lcom/bitcoin/mwallet/core/utils/signature/SigningKey;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: ScanForTransactionsPresenter.kt */
public final class ScanForTransactionsPresenter extends ScreenPresenter<ScanForTransactionsRouter> {
    private final AddressService addressService;
    private final long enableAfter;
    private final GetWalletInteractor getWalletInteractor;
    private final ScanForTransactionsInteractor interactor;
    private final String key = "preference_scanning_start";
    private final SharedPreferences sharedPreferences;
    @NotNull
    public WalletId walletId;

    public ScanForTransactionsPresenter(@NotNull Context context, @NotNull CoroutineScope coroutineScope, @NotNull ScanForTransactionsInteractor scanForTransactionsInteractor, @NotNull ScanForTransactionsRouter scanForTransactionsRouter, @NotNull AddressService addressService2, @NotNull GetWalletInteractor getWalletInteractor2) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(coroutineScope, "viewModelScope");
        Intrinsics.checkParameterIsNotNull(scanForTransactionsInteractor, "interactor");
        Intrinsics.checkParameterIsNotNull(scanForTransactionsRouter, "router");
        Intrinsics.checkParameterIsNotNull(addressService2, "addressService");
        Intrinsics.checkParameterIsNotNull(getWalletInteractor2, "getWalletInteractor");
        super(context, scanForTransactionsRouter);
        this.interactor = scanForTransactionsInteractor;
        this.addressService = addressService2;
        this.getWalletInteractor = getWalletInteractor2;
        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        this.enableAfter = 60000;
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

    public final long getEnableAfter() {
        return this.enableAfter;
    }

    @Nullable
    public final Object scanForFunds(@NotNull WalletId walletId2, @NotNull CopayerId copayerId, @NotNull SigningKey signingKey, @NotNull Continuation<? super Unit> continuation) {
        Editor edit = this.sharedPreferences.edit();
        StringBuilder sb = new StringBuilder();
        sb.append(this.key);
        sb.append('_');
        sb.append(walletId2);
        edit.putLong(sb.toString(), System.currentTimeMillis()).apply();
        return this.addressService.scanForFunds(walletId2, copayerId, signingKey, continuation);
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
    public final LiveData<Long> checkProgress() {
        SharedPreferences sharedPreferences2 = this.sharedPreferences;
        StringBuilder sb = new StringBuilder();
        sb.append(this.key);
        sb.append('_');
        WalletId walletId2 = this.walletId;
        String str = "walletId";
        if (walletId2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(str);
        }
        sb.append(walletId2);
        long j = sharedPreferences2.getLong(sb.toString(), 0);
        if (j > 0) {
            return new MutableLiveData<>(Long.valueOf(this.enableAfter - (System.currentTimeMillis() - j)));
        }
        Editor edit = this.sharedPreferences.edit();
        StringBuilder sb2 = new StringBuilder();
        sb2.append(this.key);
        sb2.append('_');
        WalletId walletId3 = this.walletId;
        if (walletId3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(str);
        }
        sb2.append(walletId3);
        edit.putLong(sb2.toString(), 0).apply();
        return new MutableLiveData<>(Long.valueOf(0));
    }
}
