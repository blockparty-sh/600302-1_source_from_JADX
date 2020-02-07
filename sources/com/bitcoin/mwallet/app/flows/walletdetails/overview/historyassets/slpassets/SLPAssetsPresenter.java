package com.bitcoin.mwallet.app.flows.walletdetails.overview.historyassets.slpassets;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.bitcoin.mwallet.app.android.LiveDataKt;
import com.bitcoin.mwallet.app.viper.PresenterBase;
import com.bitcoin.mwallet.core.interactors.DiscoverContentInteractor;
import com.bitcoin.mwallet.core.interactors.ModifyWalletInteractor;
import com.bitcoin.mwallet.core.interactors.VerifiedTokenInteractor;
import com.bitcoin.mwallet.core.models.slp.Slp;
import com.bitcoin.mwallet.core.models.verifiedtoken.VerifiedToken;
import com.bitcoin.mwallet.core.models.wallet.C1261Wallet;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import com.bitcoin.mwallet.core.repositories.SlpRepository;
import com.bitcoin.mwallet.core.repositories.WalletRepository;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u0002B=\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010¢\u0006\u0002\u0010\u0011J1\u0010/\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u000201\u0012\u0004\u0012\u000202000\u001d2\f\u00103\u001a\b\u0012\u0004\u0012\u0002020\u001dH@ø\u0001\u0000¢\u0006\u0002\u00104J\u0012\u00105\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002020\u001d06J\u000e\u00107\u001a\u0002082\u0006\u0010)\u001a\u00020*J\u0010\u00109\u001a\u0002082\u0006\u0010:\u001a\u00020\u0014H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000R\u001f\u0010\u0012\u001a\u0010\u0012\f\u0012\n \u0015*\u0004\u0018\u00010\u00140\u00140\u0013¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0017\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001d¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u001c\u0010#\u001a\u0004\u0018\u00010$X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R\u001a\u0010)\u001a\u00020*X.¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R\u000e\u0010\r\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006;"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/walletdetails/overview/historyassets/slpassets/SLPAssetsPresenter;", "Lcom/bitcoin/mwallet/app/viper/PresenterBase;", "Lcom/bitcoin/mwallet/app/flows/walletdetails/overview/historyassets/slpassets/VerifiedTokensEnabledListener;", "context", "Landroid/content/Context;", "viewModelScope", "Lkotlinx/coroutines/CoroutineScope;", "slpRepository", "Lcom/bitcoin/mwallet/core/repositories/SlpRepository;", "discoverContentInteractor", "Lcom/bitcoin/mwallet/core/interactors/DiscoverContentInteractor;", "modifyWalletInteractor", "Lcom/bitcoin/mwallet/core/interactors/ModifyWalletInteractor;", "walletRepo", "Lcom/bitcoin/mwallet/core/repositories/WalletRepository;", "verifiedTokenInteractor", "Lcom/bitcoin/mwallet/core/interactors/VerifiedTokenInteractor;", "(Landroid/content/Context;Lkotlinx/coroutines/CoroutineScope;Lcom/bitcoin/mwallet/core/repositories/SlpRepository;Lcom/bitcoin/mwallet/core/interactors/DiscoverContentInteractor;Lcom/bitcoin/mwallet/core/interactors/ModifyWalletInteractor;Lcom/bitcoin/mwallet/core/repositories/WalletRepository;Lcom/bitcoin/mwallet/core/interactors/VerifiedTokenInteractor;)V", "showVerifed", "Landroidx/lifecycle/MutableLiveData;", "", "kotlin.jvm.PlatformType", "getShowVerifed", "()Landroidx/lifecycle/MutableLiveData;", "getSlpRepository", "()Lcom/bitcoin/mwallet/core/repositories/SlpRepository;", "getVerifiedTokenInteractor", "()Lcom/bitcoin/mwallet/core/interactors/VerifiedTokenInteractor;", "verifiedTokens", "", "Lcom/bitcoin/mwallet/core/models/verifiedtoken/VerifiedToken;", "getVerifiedTokens", "()Ljava/util/List;", "getViewModelScope", "()Lkotlinx/coroutines/CoroutineScope;", "wallet", "Lcom/bitcoin/mwallet/core/models/wallet/Wallet;", "getWallet", "()Lcom/bitcoin/mwallet/core/models/wallet/Wallet;", "setWallet", "(Lcom/bitcoin/mwallet/core/models/wallet/Wallet;)V", "walletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "getWalletId", "()Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "setWalletId", "(Lcom/bitcoin/mwallet/core/models/wallet/WalletId;)V", "getTokenAmountPairs", "Lkotlin/Pair;", "Ljava/math/BigDecimal;", "Lcom/bitcoin/mwallet/core/models/slp/Slp;", "tokens", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTokens", "Landroidx/lifecycle/LiveData;", "initWallet", "", "showVerifiedOnlyChanged", "showOnlyVerifed", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: SLPAssetsPresenter.kt */
public final class SLPAssetsPresenter extends PresenterBase implements VerifiedTokensEnabledListener {
    private final Context context;
    /* access modifiers changed from: private */
    public final DiscoverContentInteractor discoverContentInteractor;
    /* access modifiers changed from: private */
    public final ModifyWalletInteractor modifyWalletInteractor;
    @NotNull
    private final MutableLiveData<Boolean> showVerifed = new MutableLiveData<>(Boolean.valueOf(false));
    @NotNull
    private final SlpRepository slpRepository;
    @NotNull
    private final VerifiedTokenInteractor verifiedTokenInteractor;
    @NotNull
    private final List<VerifiedToken> verifiedTokens = ((List) BuildersKt__BuildersKt.runBlocking$default(null, new SLPAssetsPresenter$verifiedTokens$1(this, null), 1, null));
    @NotNull
    private final CoroutineScope viewModelScope;
    @Nullable
    private C1261Wallet wallet;
    @NotNull
    public WalletId walletId;
    /* access modifiers changed from: private */
    public final WalletRepository walletRepo;

    @NotNull
    public final CoroutineScope getViewModelScope() {
        return this.viewModelScope;
    }

    @NotNull
    public final SlpRepository getSlpRepository() {
        return this.slpRepository;
    }

    @NotNull
    public final VerifiedTokenInteractor getVerifiedTokenInteractor() {
        return this.verifiedTokenInteractor;
    }

    public SLPAssetsPresenter(@NotNull Context context2, @NotNull CoroutineScope coroutineScope, @NotNull SlpRepository slpRepository2, @NotNull DiscoverContentInteractor discoverContentInteractor2, @NotNull ModifyWalletInteractor modifyWalletInteractor2, @NotNull WalletRepository walletRepository, @NotNull VerifiedTokenInteractor verifiedTokenInteractor2) {
        Intrinsics.checkParameterIsNotNull(context2, "context");
        Intrinsics.checkParameterIsNotNull(coroutineScope, "viewModelScope");
        Intrinsics.checkParameterIsNotNull(slpRepository2, "slpRepository");
        Intrinsics.checkParameterIsNotNull(discoverContentInteractor2, "discoverContentInteractor");
        Intrinsics.checkParameterIsNotNull(modifyWalletInteractor2, "modifyWalletInteractor");
        Intrinsics.checkParameterIsNotNull(walletRepository, "walletRepo");
        Intrinsics.checkParameterIsNotNull(verifiedTokenInteractor2, "verifiedTokenInteractor");
        this.context = context2;
        this.viewModelScope = coroutineScope;
        this.slpRepository = slpRepository2;
        this.discoverContentInteractor = discoverContentInteractor2;
        this.modifyWalletInteractor = modifyWalletInteractor2;
        this.walletRepo = walletRepository;
        this.verifiedTokenInteractor = verifiedTokenInteractor2;
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

    @Nullable
    public final C1261Wallet getWallet() {
        return this.wallet;
    }

    public final void setWallet(@Nullable C1261Wallet wallet2) {
        this.wallet = wallet2;
    }

    @NotNull
    public final MutableLiveData<Boolean> getShowVerifed() {
        return this.showVerifed;
    }

    @NotNull
    public final List<VerifiedToken> getVerifiedTokens() {
        return this.verifiedTokens;
    }

    @NotNull
    public final LiveData<List<Slp>> getTokens() {
        LiveData liveData = this.showVerifed;
        SlpRepository slpRepository2 = this.slpRepository;
        WalletId walletId2 = this.walletId;
        if (walletId2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("walletId");
        }
        return LiveDataKt.combineLatestIgnoreNull(liveData, slpRepository2.getAllRawTokens(walletId2), SLPAssetsPresenter$getTokens$1.INSTANCE);
    }

    public final void initWallet(@NotNull WalletId walletId2) {
        Intrinsics.checkParameterIsNotNull(walletId2, "walletId");
        this.walletId = walletId2;
        this.wallet = (C1261Wallet) BuildersKt__BuildersKt.runBlocking$default(null, new SLPAssetsPresenter$initWallet$1(this, walletId2, null), 1, null);
        MutableLiveData<Boolean> mutableLiveData = this.showVerifed;
        C1261Wallet wallet2 = this.wallet;
        if (wallet2 == null) {
            Intrinsics.throwNpe();
        }
        mutableLiveData.setValue(Boolean.valueOf(true ^ wallet2.preference().getShowAllTokens()));
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x004e  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0067  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object getTokenAmountPairs(@org.jetbrains.annotations.NotNull java.util.List<com.bitcoin.mwallet.core.models.slp.Slp> r11, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.util.List<? extends kotlin.Pair<? extends java.math.BigDecimal, com.bitcoin.mwallet.core.models.slp.Slp>>> r12) {
        /*
            r10 = this;
            boolean r0 = r12 instanceof com.bitcoin.mwallet.app.flows.walletdetails.overview.historyassets.slpassets.SLPAssetsPresenter$getTokenAmountPairs$1
            if (r0 == 0) goto L_0x0014
            r0 = r12
            com.bitcoin.mwallet.app.flows.walletdetails.overview.historyassets.slpassets.SLPAssetsPresenter$getTokenAmountPairs$1 r0 = (com.bitcoin.mwallet.app.flows.walletdetails.overview.historyassets.slpassets.SLPAssetsPresenter$getTokenAmountPairs$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r12 = r0.label
            int r12 = r12 - r2
            r0.label = r12
            goto L_0x0019
        L_0x0014:
            com.bitcoin.mwallet.app.flows.walletdetails.overview.historyassets.slpassets.SLPAssetsPresenter$getTokenAmountPairs$1 r0 = new com.bitcoin.mwallet.app.flows.walletdetails.overview.historyassets.slpassets.SLPAssetsPresenter$getTokenAmountPairs$1
            r0.<init>(r10, r12)
        L_0x0019:
            java.lang.Object r12 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x004e
            if (r2 != r3) goto L_0x0046
            java.lang.Object r11 = r0.L$5
            java.util.List r11 = (java.util.List) r11
            java.lang.Object r2 = r0.L$4
            java.util.Iterator r2 = (java.util.Iterator) r2
            java.lang.Object r4 = r0.L$3
            com.bitcoin.mwallet.core.models.slp.Slp r4 = (com.bitcoin.mwallet.core.models.slp.Slp) r4
            java.lang.Object r5 = r0.L$2
            java.util.List r5 = (java.util.List) r5
            java.lang.Object r6 = r0.L$1
            java.util.List r6 = (java.util.List) r6
            java.lang.Object r7 = r0.L$0
            com.bitcoin.mwallet.app.flows.walletdetails.overview.historyassets.slpassets.SLPAssetsPresenter r7 = (com.bitcoin.mwallet.app.flows.walletdetails.overview.historyassets.slpassets.SLPAssetsPresenter) r7
            kotlin.ResultKt.throwOnFailure(r12)
            r9 = r1
            r1 = r0
            r0 = r6
            r6 = r9
            goto L_0x0096
        L_0x0046:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L_0x004e:
            kotlin.ResultKt.throwOnFailure(r12)
            java.util.ArrayList r12 = new java.util.ArrayList
            r12.<init>()
            java.util.List r12 = (java.util.List) r12
            java.util.Iterator r2 = r11.iterator()
            r4 = r1
            r1 = r0
            r0 = r11
            r11 = r12
            r12 = r10
        L_0x0061:
            boolean r5 = r2.hasNext()
            if (r5 == 0) goto L_0x00a2
            java.lang.Object r5 = r2.next()
            com.bitcoin.mwallet.core.models.slp.Slp r5 = (com.bitcoin.mwallet.core.models.slp.Slp) r5
            com.bitcoin.mwallet.core.repositories.SlpRepository r6 = r12.slpRepository
            com.bitcoin.mwallet.core.models.wallet.WalletId r7 = r12.walletId
            if (r7 != 0) goto L_0x0078
            java.lang.String r8 = "walletId"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r8)
        L_0x0078:
            com.bitcoin.mwallet.core.models.slp.SlpId r8 = r5.getTokenId()
            r1.L$0 = r12
            r1.L$1 = r0
            r1.L$2 = r11
            r1.L$3 = r5
            r1.L$4 = r2
            r1.L$5 = r11
            r1.label = r3
            java.lang.Object r6 = r6.getTokenAmount(r7, r8, r1)
            if (r6 != r4) goto L_0x0091
            return r4
        L_0x0091:
            r7 = r12
            r12 = r6
            r6 = r4
            r4 = r5
            r5 = r11
        L_0x0096:
            kotlin.Pair r8 = new kotlin.Pair
            r8.<init>(r12, r4)
            r11.add(r8)
            r11 = r5
            r4 = r6
            r12 = r7
            goto L_0x0061
        L_0x00a2:
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.app.flows.walletdetails.overview.historyassets.slpassets.SLPAssetsPresenter.getTokenAmountPairs(java.util.List, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public void showVerifiedOnlyChanged(boolean z) {
        this.showVerifed.postValue(Boolean.valueOf(z));
        BuildersKt__Builders_commonKt.launch$default(this.viewModelScope, null, null, new SLPAssetsPresenter$showVerifiedOnlyChanged$1(this, z, null), 3, null);
    }
}
