package com.bitcoin.mwallet.app.components.lockedwalletdialog;

import android.content.Context;
import androidx.lifecycle.MutableLiveData;
import com.bitcoin.mwallet.app.viper.PresenterBase;
import com.bitcoin.mwallet.core.interactors.CreateWalletInteractor.CreateWalletResult;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0016\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/components/lockedwalletdialog/LockedWalletDialogPresenter;", "Lcom/bitcoin/mwallet/app/viper/PresenterBase;", "context", "Landroid/content/Context;", "viewModelScope", "Lkotlinx/coroutines/CoroutineScope;", "interactor", "Lcom/bitcoin/mwallet/app/components/lockedwalletdialog/LockedWalletDialogInteractor;", "(Landroid/content/Context;Lkotlinx/coroutines/CoroutineScope;Lcom/bitcoin/mwallet/app/components/lockedwalletdialog/LockedWalletDialogInteractor;)V", "resultSuccess", "Landroidx/lifecycle/MutableLiveData;", "Lcom/bitcoin/mwallet/app/components/lockedwalletdialog/UnlockWalletResult;", "getResultSuccess", "()Landroidx/lifecycle/MutableLiveData;", "unlockWallet", "", "walletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "password", "", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: LockedWalletDialogPresenter.kt */
public final class LockedWalletDialogPresenter extends PresenterBase {
    /* access modifiers changed from: private */
    public final LockedWalletDialogInteractor interactor;
    @NotNull
    private final MutableLiveData<UnlockWalletResult> resultSuccess = new MutableLiveData<>();
    private final CoroutineScope viewModelScope;

    @Metadata(mo37403bv = {1, 0, 3}, mo37406k = 3, mo37407mv = {1, 1, 15})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[CreateWalletResult.values().length];

        static {
            $EnumSwitchMapping$0[CreateWalletResult.SUCCESS.ordinal()] = 1;
            $EnumSwitchMapping$0[CreateWalletResult.INVALID_MNEMONIC.ordinal()] = 2;
            $EnumSwitchMapping$0[CreateWalletResult.DUPLICATE_MNEMONIC.ordinal()] = 3;
        }
    }

    public LockedWalletDialogPresenter(@NotNull Context context, @NotNull CoroutineScope coroutineScope, @NotNull LockedWalletDialogInteractor lockedWalletDialogInteractor) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(coroutineScope, "viewModelScope");
        Intrinsics.checkParameterIsNotNull(lockedWalletDialogInteractor, "interactor");
        this.viewModelScope = coroutineScope;
        this.interactor = lockedWalletDialogInteractor;
    }

    @NotNull
    public final MutableLiveData<UnlockWalletResult> getResultSuccess() {
        return this.resultSuccess;
    }

    public final void unlockWallet(@NotNull WalletId walletId, @NotNull String str) {
        Intrinsics.checkParameterIsNotNull(walletId, "walletId");
        Intrinsics.checkParameterIsNotNull(str, "password");
        BuildersKt__Builders_commonKt.launch$default(this.viewModelScope, null, null, new LockedWalletDialogPresenter$unlockWallet$1(this, walletId, str, null), 3, null);
    }
}
