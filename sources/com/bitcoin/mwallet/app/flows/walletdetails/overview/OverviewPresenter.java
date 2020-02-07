package com.bitcoin.mwallet.app.flows.walletdetails.overview;

import android.app.Activity;
import android.content.Context;
import androidx.lifecycle.LiveData;
import com.bitcoin.mwallet.app.android.LiveDataKt;
import com.bitcoin.mwallet.app.viper.ScreenPresenter;
import com.bitcoin.mwallet.core.interactors.GetCurrentExchangeRateInteractor;
import com.bitcoin.mwallet.core.interactors.GetDefaultCurrencyInteractor;
import com.bitcoin.mwallet.core.interactors.GetWalletInteractor;
import com.bitcoin.mwallet.core.models.slp.Slp;
import com.bitcoin.mwallet.core.models.wallet.C1261Wallet;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import com.bitcoin.mwallet.core.models.wallet.WalletInfoBalance;
import com.bitcoin.mwallet.core.models.wallet.WalletInfoSatoshis;
import com.bitcoin.mwallet.core.repositories.SlpRepository;
import com.bitcoin.mwallet.core.repositories.WalletRepository;
import com.bitcoin.mwallet.core.services.AnalyticsService;
import com.bitcoin.mwallet.zion.ZionRepository;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001BU\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\u0006\u0010\u0013\u001a\u00020\u0014\u0012\u0006\u0010\u0015\u001a\u00020\u0002¢\u0006\u0002\u0010\u0016J\u0019\u0010!\u001a\u00020\"2\u0006\u0010\u001b\u001a\u00020\u001cH@ø\u0001\u0000¢\u0006\u0002\u0010#J\f\u0010$\u001a\b\u0012\u0004\u0012\u00020&0%J\f\u0010'\u001a\b\u0012\u0004\u0012\u00020(0%J\u0011\u0010)\u001a\u00020*H@ø\u0001\u0000¢\u0006\u0002\u0010+J\f\u0010,\u001a\b\u0012\u0004\u0012\u00020-0%J\u0014\u0010.\u001a\b\u0012\u0004\u0012\u0002000/2\u0006\u0010\u001b\u001a\u00020\u001cJ\u000e\u00101\u001a\u00020\"2\u0006\u0010\u001b\u001a\u00020\u001cJ\u0006\u00102\u001a\u00020\"J\u000e\u00103\u001a\u00020\"2\u0006\u00104\u001a\u000205J\u000e\u00106\u001a\u00020\"2\u0006\u00104\u001a\u000205J\u000e\u00107\u001a\u00020\"2\u0006\u00108\u001a\u000209J\u0006\u0010:\u001a\u00020\"R\u000e\u0010\u0013\u001a\u00020\u0014X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u001a\u0010\u001b\u001a\u00020\u001cX.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u000e\u0010\u000b\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006;"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/walletdetails/overview/OverviewPresenter;", "Lcom/bitcoin/mwallet/app/viper/ScreenPresenter;", "Lcom/bitcoin/mwallet/app/flows/walletdetails/overview/OverviewRouter;", "context", "Landroid/content/Context;", "viewModelScope", "Lkotlinx/coroutines/CoroutineScope;", "zionRepository", "Lcom/bitcoin/mwallet/zion/ZionRepository;", "defaultCurrencyInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetDefaultCurrencyInteractor;", "walletInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetWalletInteractor;", "walletRepo", "Lcom/bitcoin/mwallet/core/repositories/WalletRepository;", "exchangeRateInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetCurrentExchangeRateInteractor;", "slpRepository", "Lcom/bitcoin/mwallet/core/repositories/SlpRepository;", "analyticsService", "Lcom/bitcoin/mwallet/core/services/AnalyticsService;", "router", "(Landroid/content/Context;Lkotlinx/coroutines/CoroutineScope;Lcom/bitcoin/mwallet/zion/ZionRepository;Lcom/bitcoin/mwallet/core/interactors/GetDefaultCurrencyInteractor;Lcom/bitcoin/mwallet/core/interactors/GetWalletInteractor;Lcom/bitcoin/mwallet/core/repositories/WalletRepository;Lcom/bitcoin/mwallet/core/interactors/GetCurrentExchangeRateInteractor;Lcom/bitcoin/mwallet/core/repositories/SlpRepository;Lcom/bitcoin/mwallet/core/services/AnalyticsService;Lcom/bitcoin/mwallet/app/flows/walletdetails/overview/OverviewRouter;)V", "getContext", "()Landroid/content/Context;", "getViewModelScope", "()Lkotlinx/coroutines/CoroutineScope;", "walletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "getWalletId", "()Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "setWalletId", "(Lcom/bitcoin/mwallet/core/models/wallet/WalletId;)V", "displayZionMnemonic", "", "(Lcom/bitcoin/mwallet/core/models/wallet/WalletId;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getBalanceByWallet", "Landroidx/lifecycle/LiveData;", "Lcom/bitcoin/mwallet/core/models/wallet/WalletInfoBalance;", "getBaseBalanceAndWalletTokens", "Lcom/bitcoin/mwallet/core/models/wallet/WalletInfoSatoshis;", "getCoin", "Lcom/bitcoin/mwallet/core/models/Coin;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getWallet", "Lcom/bitcoin/mwallet/core/models/wallet/Wallet;", "getWalletTokens", "", "Lcom/bitcoin/mwallet/core/models/slp/Slp;", "initWalletId", "toBackup", "toReceive", "activity", "Landroid/app/Activity;", "toSend", "toTxDetails", "txid", "", "toWalletSettings", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: OverviewPresenter.kt */
public final class OverviewPresenter extends ScreenPresenter<OverviewRouter> {
    private final AnalyticsService analyticsService;
    @NotNull
    private final Context context;
    /* access modifiers changed from: private */
    public final GetDefaultCurrencyInteractor defaultCurrencyInteractor;
    private final GetCurrentExchangeRateInteractor exchangeRateInteractor;
    /* access modifiers changed from: private */
    public final SlpRepository slpRepository;
    @NotNull
    private final CoroutineScope viewModelScope;
    @NotNull
    public WalletId walletId;
    /* access modifiers changed from: private */
    public final GetWalletInteractor walletInteractor;
    private final WalletRepository walletRepo;
    /* access modifiers changed from: private */
    public final ZionRepository zionRepository;

    @NotNull
    public final Context getContext() {
        return this.context;
    }

    @NotNull
    public final CoroutineScope getViewModelScope() {
        return this.viewModelScope;
    }

    public OverviewPresenter(@NotNull Context context2, @NotNull CoroutineScope coroutineScope, @NotNull ZionRepository zionRepository2, @NotNull GetDefaultCurrencyInteractor getDefaultCurrencyInteractor, @NotNull GetWalletInteractor getWalletInteractor, @NotNull WalletRepository walletRepository, @NotNull GetCurrentExchangeRateInteractor getCurrentExchangeRateInteractor, @NotNull SlpRepository slpRepository2, @NotNull AnalyticsService analyticsService2, @NotNull OverviewRouter overviewRouter) {
        Intrinsics.checkParameterIsNotNull(context2, "context");
        Intrinsics.checkParameterIsNotNull(coroutineScope, "viewModelScope");
        Intrinsics.checkParameterIsNotNull(zionRepository2, "zionRepository");
        Intrinsics.checkParameterIsNotNull(getDefaultCurrencyInteractor, "defaultCurrencyInteractor");
        Intrinsics.checkParameterIsNotNull(getWalletInteractor, "walletInteractor");
        Intrinsics.checkParameterIsNotNull(walletRepository, "walletRepo");
        Intrinsics.checkParameterIsNotNull(getCurrentExchangeRateInteractor, "exchangeRateInteractor");
        Intrinsics.checkParameterIsNotNull(slpRepository2, "slpRepository");
        Intrinsics.checkParameterIsNotNull(analyticsService2, "analyticsService");
        Intrinsics.checkParameterIsNotNull(overviewRouter, "router");
        super(context2, overviewRouter);
        this.context = context2;
        this.viewModelScope = coroutineScope;
        this.zionRepository = zionRepository2;
        this.defaultCurrencyInteractor = getDefaultCurrencyInteractor;
        this.walletInteractor = getWalletInteractor;
        this.walletRepo = walletRepository;
        this.exchangeRateInteractor = getCurrentExchangeRateInteractor;
        this.slpRepository = slpRepository2;
        this.analyticsService = analyticsService2;
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

    public final void initWalletId(@NotNull WalletId walletId2) {
        Intrinsics.checkParameterIsNotNull(walletId2, "walletId");
        this.walletId = walletId2;
    }

    @NotNull
    public final LiveData<WalletInfoBalance> getBalanceByWallet() {
        WalletRepository walletRepository = this.walletRepo;
        WalletId walletId2 = this.walletId;
        if (walletId2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("walletId");
        }
        return LiveDataKt.combineLatestIgnoreNull(walletRepository.walletInfoSatoshis(walletId2), this.exchangeRateInteractor.getCurrentExchangeRate(this.defaultCurrencyInteractor.getDefaultFiatCurrency()), new OverviewPresenter$getBalanceByWallet$1(this));
    }

    @NotNull
    public final List<Slp> getWalletTokens(@NotNull WalletId walletId2) {
        Intrinsics.checkParameterIsNotNull(walletId2, "walletId");
        return (List) BuildersKt__BuildersKt.runBlocking$default(null, new OverviewPresenter$getWalletTokens$1(this, walletId2, null), 1, null);
    }

    @NotNull
    public final LiveData<WalletInfoSatoshis> getBaseBalanceAndWalletTokens() {
        WalletRepository walletRepository = this.walletRepo;
        WalletId walletId2 = this.walletId;
        if (walletId2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("walletId");
        }
        return walletRepository.walletInfoSatoshis(walletId2);
    }

    @NotNull
    public final LiveData<C1261Wallet> getWallet() {
        GetWalletInteractor getWalletInteractor = this.walletInteractor;
        WalletId walletId2 = this.walletId;
        if (walletId2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("walletId");
        }
        return getWalletInteractor.wallet(walletId2);
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0054  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object getCoin(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.bitcoin.mwallet.core.models.Coin> r6) {
        /*
            r5 = this;
            boolean r0 = r6 instanceof com.bitcoin.mwallet.app.flows.walletdetails.overview.OverviewPresenter$getCoin$1
            if (r0 == 0) goto L_0x0014
            r0 = r6
            com.bitcoin.mwallet.app.flows.walletdetails.overview.OverviewPresenter$getCoin$1 r0 = (com.bitcoin.mwallet.app.flows.walletdetails.overview.OverviewPresenter$getCoin$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L_0x0019
        L_0x0014:
            com.bitcoin.mwallet.app.flows.walletdetails.overview.OverviewPresenter$getCoin$1 r0 = new com.bitcoin.mwallet.app.flows.walletdetails.overview.OverviewPresenter$getCoin$1
            r0.<init>(r5, r6)
        L_0x0019:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0036
            if (r2 != r3) goto L_0x002e
            java.lang.Object r0 = r0.L$0
            com.bitcoin.mwallet.app.flows.walletdetails.overview.OverviewPresenter r0 = (com.bitcoin.mwallet.app.flows.walletdetails.overview.OverviewPresenter) r0
            kotlin.ResultKt.throwOnFailure(r6)
            goto L_0x004f
        L_0x002e:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)
            throw r6
        L_0x0036:
            kotlin.ResultKt.throwOnFailure(r6)
            com.bitcoin.mwallet.core.interactors.GetWalletInteractor r6 = r5.walletInteractor
            com.bitcoin.mwallet.core.models.wallet.WalletId r2 = r5.walletId
            if (r2 != 0) goto L_0x0044
            java.lang.String r4 = "walletId"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r4)
        L_0x0044:
            r0.L$0 = r5
            r0.label = r3
            java.lang.Object r6 = r6.getWalletCoin(r2, r0)
            if (r6 != r1) goto L_0x004f
            return r1
        L_0x004f:
            com.bitcoin.mwallet.core.models.Coin r6 = (com.bitcoin.mwallet.core.models.Coin) r6
            if (r6 == 0) goto L_0x0054
            goto L_0x0056
        L_0x0054:
            com.bitcoin.mwallet.core.models.Coin r6 = com.bitcoin.mwallet.core.models.Coin.BCH
        L_0x0056:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.app.flows.walletdetails.overview.OverviewPresenter.getCoin(kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final void toSend(@NotNull Activity activity) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        this.analyticsService.track("wallet_details_tap_on_send", MapsKt.emptyMap());
        OverviewRouter overviewRouter = (OverviewRouter) getRouter();
        WalletId walletId2 = this.walletId;
        if (walletId2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("walletId");
        }
        overviewRouter.toSend(activity, walletId2);
    }

    public final void toReceive(@NotNull Activity activity) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        OverviewRouter overviewRouter = (OverviewRouter) getRouter();
        WalletId walletId2 = this.walletId;
        if (walletId2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("walletId");
        }
        overviewRouter.toReceive(activity, walletId2);
    }

    public final void toTxDetails(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "txid");
        OverviewRouter overviewRouter = (OverviewRouter) getRouter();
        WalletId walletId2 = this.walletId;
        if (walletId2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("walletId");
        }
        overviewRouter.toTxDetails(str, walletId2);
    }

    public final void toWalletSettings() {
        OverviewRouter overviewRouter = (OverviewRouter) getRouter();
        WalletId walletId2 = this.walletId;
        if (walletId2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("walletId");
        }
        overviewRouter.toWalletSettings(walletId2);
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x004d  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0066  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ java.lang.Object displayZionMnemonic(@org.jetbrains.annotations.NotNull com.bitcoin.mwallet.core.models.wallet.WalletId r7, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
        /*
            r6 = this;
            boolean r0 = r8 instanceof com.bitcoin.mwallet.app.flows.walletdetails.overview.OverviewPresenter$displayZionMnemonic$1
            if (r0 == 0) goto L_0x0014
            r0 = r8
            com.bitcoin.mwallet.app.flows.walletdetails.overview.OverviewPresenter$displayZionMnemonic$1 r0 = (com.bitcoin.mwallet.app.flows.walletdetails.overview.OverviewPresenter$displayZionMnemonic$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L_0x0019
        L_0x0014:
            com.bitcoin.mwallet.app.flows.walletdetails.overview.OverviewPresenter$displayZionMnemonic$1 r0 = new com.bitcoin.mwallet.app.flows.walletdetails.overview.OverviewPresenter$displayZionMnemonic$1
            r0.<init>(r6, r8)
        L_0x0019:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x004d
            if (r2 == r4) goto L_0x0041
            if (r2 != r3) goto L_0x0039
            java.lang.Object r7 = r0.L$2
            com.bitcoin.mwallet.core.models.credential.Credential r7 = (com.bitcoin.mwallet.core.models.credential.Credential) r7
            java.lang.Object r7 = r0.L$1
            com.bitcoin.mwallet.core.models.wallet.WalletId r7 = (com.bitcoin.mwallet.core.models.wallet.WalletId) r7
            java.lang.Object r7 = r0.L$0
            com.bitcoin.mwallet.app.flows.walletdetails.overview.OverviewPresenter r7 = (com.bitcoin.mwallet.app.flows.walletdetails.overview.OverviewPresenter) r7
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x007e
        L_0x0039:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L_0x0041:
            java.lang.Object r7 = r0.L$1
            com.bitcoin.mwallet.core.models.wallet.WalletId r7 = (com.bitcoin.mwallet.core.models.wallet.WalletId) r7
            java.lang.Object r2 = r0.L$0
            com.bitcoin.mwallet.app.flows.walletdetails.overview.OverviewPresenter r2 = (com.bitcoin.mwallet.app.flows.walletdetails.overview.OverviewPresenter) r2
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x0060
        L_0x004d:
            kotlin.ResultKt.throwOnFailure(r8)
            com.bitcoin.mwallet.core.repositories.WalletRepository r8 = r6.walletRepo
            r0.L$0 = r6
            r0.L$1 = r7
            r0.label = r4
            java.lang.Object r8 = r8.getCredential(r7, r0)
            if (r8 != r1) goto L_0x005f
            return r1
        L_0x005f:
            r2 = r6
        L_0x0060:
            com.bitcoin.mwallet.core.models.credential.Credential r8 = (com.bitcoin.mwallet.core.models.credential.Credential) r8
            boolean r4 = r8 instanceof com.bitcoin.mwallet.core.models.credential.CredentialZion
            if (r4 == 0) goto L_0x007e
            com.bitcoin.mwallet.zion.ZionRepository r4 = r2.zionRepository
            r5 = r8
            com.bitcoin.mwallet.core.models.credential.CredentialZion r5 = (com.bitcoin.mwallet.core.models.credential.CredentialZion) r5
            com.bitcoin.mwallet.zion.ZionId r5 = r5.getZionId()
            r0.L$0 = r2
            r0.L$1 = r7
            r0.L$2 = r8
            r0.label = r3
            java.lang.Object r7 = r4.showWalletSeed(r5, r0)
            if (r7 != r1) goto L_0x007e
            return r1
        L_0x007e:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.app.flows.walletdetails.overview.OverviewPresenter.displayZionMnemonic(com.bitcoin.mwallet.core.models.wallet.WalletId, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x005c  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x005f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void toBackup() {
        /*
            r9 = this;
            com.bitcoin.mwallet.app.flows.walletdetails.overview.OverviewPresenter$toBackup$wallet$1 r0 = new com.bitcoin.mwallet.app.flows.walletdetails.overview.OverviewPresenter$toBackup$wallet$1
            r1 = 0
            r0.<init>(r9, r1)
            kotlin.jvm.functions.Function2 r0 = (kotlin.jvm.functions.Function2) r0
            r2 = 1
            java.lang.Object r0 = kotlinx.coroutines.BuildersKt__BuildersKt.runBlocking$default(r1, r0, r2, r1)
            com.bitcoin.mwallet.core.models.wallet.Wallet r0 = (com.bitcoin.mwallet.core.models.wallet.C1261Wallet) r0
            if (r0 == 0) goto L_0x0018
            com.bitcoin.mwallet.core.preferences.WalletPreference r3 = r0.preference()
            r3.setHasBackedUp(r2)
        L_0x0018:
            com.bitcoin.mwallet.app.flows.walletdetails.overview.OverviewPresenter$toBackup$1 r3 = new com.bitcoin.mwallet.app.flows.walletdetails.overview.OverviewPresenter$toBackup$1
            r3.<init>(r9, r1)
            kotlin.jvm.functions.Function2 r3 = (kotlin.jvm.functions.Function2) r3
            java.lang.Object r2 = kotlinx.coroutines.BuildersKt__BuildersKt.runBlocking$default(r1, r3, r2, r1)
            java.lang.Boolean r2 = (java.lang.Boolean) r2
            boolean r2 = r2.booleanValue()
            if (r2 == 0) goto L_0x003d
            kotlinx.coroutines.CoroutineScope r3 = r9.viewModelScope
            r4 = 0
            r5 = 0
            com.bitcoin.mwallet.app.flows.walletdetails.overview.OverviewPresenter$toBackup$2 r0 = new com.bitcoin.mwallet.app.flows.walletdetails.overview.OverviewPresenter$toBackup$2
            r0.<init>(r9, r1)
            r6 = r0
            kotlin.jvm.functions.Function2 r6 = (kotlin.jvm.functions.Function2) r6
            r7 = 3
            r8 = 0
            kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r3, r4, r5, r6, r7, r8)
            goto L_0x0074
        L_0x003d:
            com.bitcoin.mwallet.app.viper.RouterBase r2 = r9.getRouter()
            com.bitcoin.mwallet.app.flows.walletdetails.overview.OverviewRouter r2 = (com.bitcoin.mwallet.app.flows.walletdetails.overview.OverviewRouter) r2
            com.bitcoin.mwallet.core.models.wallet.WalletId r3 = r9.walletId
            if (r3 != 0) goto L_0x004c
            java.lang.String r4 = "walletId"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r4)
        L_0x004c:
            if (r0 == 0) goto L_0x0059
            com.bitcoin.mwallet.core.models.Coin r4 = r0.getCoin()
            if (r4 == 0) goto L_0x0059
            java.lang.String r4 = r4.getTicker()
            goto L_0x005a
        L_0x0059:
            r4 = r1
        L_0x005a:
            if (r4 != 0) goto L_0x005f
            java.lang.String r0 = ""
            goto L_0x0071
        L_0x005f:
            if (r0 == 0) goto L_0x006b
            com.bitcoin.mwallet.core.models.Coin r0 = r0.getCoin()
            if (r0 == 0) goto L_0x006b
            java.lang.String r1 = r0.getTicker()
        L_0x006b:
            r0 = r1
            if (r0 != 0) goto L_0x0071
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x0071:
            r2.toBackup(r3, r0)
        L_0x0074:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.app.flows.walletdetails.overview.OverviewPresenter.toBackup():void");
    }
}
