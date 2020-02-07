package com.bitcoin.mwallet.app.components.walletselector;

import android.content.Context;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import com.bitcoin.mwallet.app.components.walletmanagement.createwallet.view.CreateWalletDialogView;
import com.bitcoin.mwallet.app.viper.PresenterBase;
import com.bitcoin.mwallet.core.entity.WalletType;
import com.bitcoin.mwallet.core.interactors.GetBalanceByCoinInteractor;
import com.bitcoin.mwallet.core.models.Coin;
import com.bitcoin.mwallet.core.models.slp.Slp;
import com.bitcoin.mwallet.core.models.slp.SlpId;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import com.bitcoin.mwallet.core.repositories.SlpRepository;
import com.bitcoin.mwallet.core.views.walletsummary.WalletSummaryView;
import java.math.BigDecimal;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0018\u0010$\u001a\u0004\u0018\u00010%2\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020)J\u0018\u0010*\u001a\u0004\u0018\u00010+2\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020)J\u001a\u0010 \u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\f2\u0006\u0010,\u001a\u00020\u0015J\u0010\u0010-\u001a\u00020.2\b\u0010,\u001a\u0004\u0018\u00010\u0015J\u0016\u0010/\u001a\u00020.2\u0006\u0010,\u001a\u00020\u00152\u0006\u00100\u001a\u000201J\u001a\u00102\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\f2\u0006\u00103\u001a\u000204R(\u0010\u000b\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r\u0018\u00010\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R \u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R \u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\u0014X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0017\"\u0004\b\u001d\u0010\u0019R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R&\u0010\u001e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\u001fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#¨\u00065"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/components/walletselector/WalletSelectorPresenter;", "Lcom/bitcoin/mwallet/app/viper/PresenterBase;", "context", "Landroid/content/Context;", "viewModelScope", "Lkotlinx/coroutines/CoroutineScope;", "getBalanceByCoinInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetBalanceByCoinInteractor;", "slpRepository", "Lcom/bitcoin/mwallet/core/repositories/SlpRepository;", "(Landroid/content/Context;Lkotlinx/coroutines/CoroutineScope;Lcom/bitcoin/mwallet/core/interactors/GetBalanceByCoinInteractor;Lcom/bitcoin/mwallet/core/repositories/SlpRepository;)V", "currentWalletSummaries", "Landroidx/lifecycle/LiveData;", "", "Lcom/bitcoin/mwallet/core/views/walletsummary/WalletSummaryView;", "getCurrentWalletSummaries", "()Landroidx/lifecycle/LiveData;", "setCurrentWalletSummaries", "(Landroidx/lifecycle/LiveData;)V", "selectedCoin", "Landroidx/lifecycle/MutableLiveData;", "Lcom/bitcoin/mwallet/core/entity/WalletType;", "getSelectedCoin", "()Landroidx/lifecycle/MutableLiveData;", "setSelectedCoin", "(Landroidx/lifecycle/MutableLiveData;)V", "showEmptyLayout", "", "getShowEmptyLayout", "setShowEmptyLayout", "walletSummary", "Landroidx/lifecycle/MediatorLiveData;", "getWalletSummary", "()Landroidx/lifecycle/MediatorLiveData;", "setWalletSummary", "(Landroidx/lifecycle/MediatorLiveData;)V", "getToken", "Lcom/bitcoin/mwallet/core/models/slp/Slp;", "walletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "slpId", "Lcom/bitcoin/mwallet/core/models/slp/SlpId;", "getTokenBalance", "Ljava/math/BigDecimal;", "walletType", "setSelectedWalletType", "", "toCreateWallet", "fm", "Landroidx/fragment/app/FragmentManager;", "walletSummaries", "coin", "Lcom/bitcoin/mwallet/core/models/Coin;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: WalletSelectorPresenter.kt */
public final class WalletSelectorPresenter extends PresenterBase {
    @Nullable
    private LiveData<List<WalletSummaryView>> currentWalletSummaries;
    private final GetBalanceByCoinInteractor getBalanceByCoinInteractor;
    @NotNull
    private MutableLiveData<WalletType> selectedCoin = new MutableLiveData<>();
    @NotNull
    private MutableLiveData<Boolean> showEmptyLayout = new MutableLiveData<>();
    /* access modifiers changed from: private */
    public final SlpRepository slpRepository;
    @NotNull
    private MediatorLiveData<List<WalletSummaryView>> walletSummary = new MediatorLiveData<>();

    public WalletSelectorPresenter(@NotNull Context context, @NotNull CoroutineScope coroutineScope, @NotNull GetBalanceByCoinInteractor getBalanceByCoinInteractor2, @NotNull SlpRepository slpRepository2) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(coroutineScope, "viewModelScope");
        Intrinsics.checkParameterIsNotNull(getBalanceByCoinInteractor2, "getBalanceByCoinInteractor");
        Intrinsics.checkParameterIsNotNull(slpRepository2, "slpRepository");
        this.getBalanceByCoinInteractor = getBalanceByCoinInteractor2;
        this.slpRepository = slpRepository2;
    }

    @NotNull
    public final MutableLiveData<WalletType> getSelectedCoin() {
        return this.selectedCoin;
    }

    public final void setSelectedCoin(@NotNull MutableLiveData<WalletType> mutableLiveData) {
        Intrinsics.checkParameterIsNotNull(mutableLiveData, "<set-?>");
        this.selectedCoin = mutableLiveData;
    }

    @NotNull
    public final MediatorLiveData<List<WalletSummaryView>> getWalletSummary() {
        return this.walletSummary;
    }

    public final void setWalletSummary(@NotNull MediatorLiveData<List<WalletSummaryView>> mediatorLiveData) {
        Intrinsics.checkParameterIsNotNull(mediatorLiveData, "<set-?>");
        this.walletSummary = mediatorLiveData;
    }

    @Nullable
    public final LiveData<List<WalletSummaryView>> getCurrentWalletSummaries() {
        return this.currentWalletSummaries;
    }

    public final void setCurrentWalletSummaries(@Nullable LiveData<List<WalletSummaryView>> liveData) {
        this.currentWalletSummaries = liveData;
    }

    @NotNull
    public final MutableLiveData<Boolean> getShowEmptyLayout() {
        return this.showEmptyLayout;
    }

    public final void setShowEmptyLayout(@NotNull MutableLiveData<Boolean> mutableLiveData) {
        Intrinsics.checkParameterIsNotNull(mutableLiveData, "<set-?>");
        this.showEmptyLayout = mutableLiveData;
    }

    public final void setSelectedWalletType(@Nullable WalletType walletType) {
        MutableLiveData<WalletType> mutableLiveData = this.selectedCoin;
        if (walletType == null) {
            walletType = WalletType.BCH;
        }
        mutableLiveData.setValue(walletType);
    }

    @Nullable
    public final Slp getToken(@NotNull WalletId walletId, @NotNull SlpId slpId) {
        Intrinsics.checkParameterIsNotNull(walletId, "walletId");
        Intrinsics.checkParameterIsNotNull(slpId, "slpId");
        return (Slp) BuildersKt__BuildersKt.runBlocking$default(null, new WalletSelectorPresenter$getToken$1(this, walletId, slpId, null), 1, null);
    }

    @Nullable
    public final BigDecimal getTokenBalance(@NotNull WalletId walletId, @NotNull SlpId slpId) {
        Intrinsics.checkParameterIsNotNull(walletId, "walletId");
        Intrinsics.checkParameterIsNotNull(slpId, "slpId");
        return (BigDecimal) BuildersKt__BuildersKt.runBlocking$default(null, new WalletSelectorPresenter$getTokenBalance$1(this, walletId, slpId, null), 1, null);
    }

    @NotNull
    public final LiveData<List<WalletSummaryView>> getWalletSummary(@NotNull WalletType walletType) {
        Intrinsics.checkParameterIsNotNull(walletType, "walletType");
        return walletSummaries(walletType.getCoin());
    }

    @NotNull
    public final LiveData<List<WalletSummaryView>> walletSummaries(@NotNull Coin coin) {
        Intrinsics.checkParameterIsNotNull(coin, "coin");
        LiveData<List<WalletSummaryView>> map = Transformations.map(this.getBalanceByCoinInteractor.walletBalancesOfCoin(coin), WalletSelectorPresenter$walletSummaries$1.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(map, "Transformations.map(\n   …romWalletBalance())\n    }");
        return map;
    }

    public final void toCreateWallet(@NotNull WalletType walletType, @NotNull FragmentManager fragmentManager) {
        Intrinsics.checkParameterIsNotNull(walletType, "walletType");
        Intrinsics.checkParameterIsNotNull(fragmentManager, "fm");
        new CreateWalletDialogView(walletType.getCoin()).show(fragmentManager, "create_new_wallet");
    }
}
