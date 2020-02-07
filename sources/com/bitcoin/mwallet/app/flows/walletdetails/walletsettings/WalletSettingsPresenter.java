package com.bitcoin.mwallet.app.flows.walletdetails.walletsettings;

import android.app.Activity;
import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.bitcoin.mwallet.app.android.LiveDataKt;
import com.bitcoin.mwallet.app.viper.ScreenPresenter;
import com.bitcoin.mwallet.core.interactors.GetDefaultCurrencyInteractor;
import com.bitcoin.mwallet.core.interactors.GetWalletInteractor;
import com.bitcoin.mwallet.core.interactors.ModifyWalletInteractor;
import com.bitcoin.mwallet.core.models.Coin;
import com.bitcoin.mwallet.core.models.wallet.C1261Wallet;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import com.bitcoin.mwallet.core.repositories.WalletRepository;
import com.bitcoin.mwallet.zion.ZionRepository;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Currency;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001BE\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0002¢\u0006\u0002\u0010\u0012J\u0019\u0010$\u001a\u00020%2\u0006\u0010\u001e\u001a\u00020\u001fH@ø\u0001\u0000¢\u0006\u0002\u0010&J\u0006\u0010'\u001a\u00020(J\f\u0010)\u001a\b\u0012\u0004\u0012\u00020+0*J\u0010\u0010)\u001a\u0004\u0018\u00010+2\u0006\u0010\u001e\u001a\u00020\u001fJ\f\u0010,\u001a\b\u0012\u0004\u0012\u00020-0*J\b\u0010.\u001a\u0004\u0018\u00010/J\u000e\u00100\u001a\u00020%2\u0006\u00101\u001a\u00020\u0015J\u000e\u00102\u001a\u00020%2\u0006\u00103\u001a\u00020-J\u000e\u00104\u001a\u00020%2\u0006\u00105\u001a\u00020\u0019J\u000e\u00106\u001a\u00020%2\u0006\u00107\u001a\u00020\u0019J\u0006\u00108\u001a\u00020%J\u0012\u00109\u001a\u00020%2\n\b\u0002\u0010:\u001a\u0004\u0018\u00010;J\u0006\u0010<\u001a\u00020%J\u000e\u0010=\u001a\u00020%2\u0006\u0010>\u001a\u00020/R\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u000e\u0010\u000f\u001a\u00020\u0010X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0018\u001a\u00020\u0019¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u001a\u0010\u001e\u001a\u00020\u001fX.¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006?"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/walletdetails/walletsettings/WalletSettingsPresenter;", "Lcom/bitcoin/mwallet/app/viper/ScreenPresenter;", "Lcom/bitcoin/mwallet/app/flows/walletdetails/walletsettings/WalletSettingsRouter;", "context", "Landroid/content/Context;", "viewModelScope", "Lkotlinx/coroutines/CoroutineScope;", "modifyWalletInteractor", "Lcom/bitcoin/mwallet/core/interactors/ModifyWalletInteractor;", "walletInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetWalletInteractor;", "zionRepository", "Lcom/bitcoin/mwallet/zion/ZionRepository;", "walletRepository", "Lcom/bitcoin/mwallet/core/repositories/WalletRepository;", "defaultCurrencyInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetDefaultCurrencyInteractor;", "router", "(Landroid/content/Context;Lkotlinx/coroutines/CoroutineScope;Lcom/bitcoin/mwallet/core/interactors/ModifyWalletInteractor;Lcom/bitcoin/mwallet/core/interactors/GetWalletInteractor;Lcom/bitcoin/mwallet/zion/ZionRepository;Lcom/bitcoin/mwallet/core/repositories/WalletRepository;Lcom/bitcoin/mwallet/core/interactors/GetDefaultCurrencyInteractor;Lcom/bitcoin/mwallet/app/flows/walletdetails/walletsettings/WalletSettingsRouter;)V", "colorData", "Landroidx/lifecycle/MutableLiveData;", "", "getColorData", "()Landroidx/lifecycle/MutableLiveData;", "hasZion", "", "getHasZion", "()Z", "getViewModelScope", "()Lkotlinx/coroutines/CoroutineScope;", "walletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "getWalletId", "()Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "setWalletId", "(Lcom/bitcoin/mwallet/core/models/wallet/WalletId;)V", "displayZionMnemonic", "", "(Lcom/bitcoin/mwallet/core/models/wallet/WalletId;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getDefaultCurrency", "Ljava/util/Currency;", "getWallet", "Landroidx/lifecycle/LiveData;", "Lcom/bitcoin/mwallet/core/models/wallet/Wallet;", "getWalletCount", "", "getWalletSpendingThreshold", "Ljava/math/BigDecimal;", "modifyWalletName", "newName", "setColor", "color", "setHideBalances", "hideBalances", "setRequiresSpendingAuth", "requiresAuth", "toAdvancedDetails", "toDeleteWallet", "activity", "Landroid/app/Activity;", "toRecoverWallet", "updateWalletSpendingThreshold", "newValue", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: WalletSettingsPresenter.kt */
public final class WalletSettingsPresenter extends ScreenPresenter<WalletSettingsRouter> {
    @NotNull
    private final MutableLiveData<String> colorData = new MutableLiveData<>();
    private final GetDefaultCurrencyInteractor defaultCurrencyInteractor;
    private final boolean hasZion = ((Boolean) BuildersKt__BuildersKt.runBlocking$default(null, new WalletSettingsPresenter$hasZion$1(this, null), 1, null)).booleanValue();
    /* access modifiers changed from: private */
    public final ModifyWalletInteractor modifyWalletInteractor;
    @NotNull
    private final CoroutineScope viewModelScope;
    @NotNull
    public WalletId walletId;
    /* access modifiers changed from: private */
    public final GetWalletInteractor walletInteractor;
    private final WalletRepository walletRepository;
    /* access modifiers changed from: private */
    public final ZionRepository zionRepository;

    @NotNull
    public final CoroutineScope getViewModelScope() {
        return this.viewModelScope;
    }

    public WalletSettingsPresenter(@NotNull Context context, @NotNull CoroutineScope coroutineScope, @NotNull ModifyWalletInteractor modifyWalletInteractor2, @NotNull GetWalletInteractor getWalletInteractor, @NotNull ZionRepository zionRepository2, @NotNull WalletRepository walletRepository2, @NotNull GetDefaultCurrencyInteractor getDefaultCurrencyInteractor, @NotNull WalletSettingsRouter walletSettingsRouter) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(coroutineScope, "viewModelScope");
        Intrinsics.checkParameterIsNotNull(modifyWalletInteractor2, "modifyWalletInteractor");
        Intrinsics.checkParameterIsNotNull(getWalletInteractor, "walletInteractor");
        Intrinsics.checkParameterIsNotNull(zionRepository2, "zionRepository");
        Intrinsics.checkParameterIsNotNull(walletRepository2, "walletRepository");
        Intrinsics.checkParameterIsNotNull(getDefaultCurrencyInteractor, "defaultCurrencyInteractor");
        Intrinsics.checkParameterIsNotNull(walletSettingsRouter, "router");
        super(context, walletSettingsRouter);
        this.viewModelScope = coroutineScope;
        this.modifyWalletInteractor = modifyWalletInteractor2;
        this.walletInteractor = getWalletInteractor;
        this.zionRepository = zionRepository2;
        this.walletRepository = walletRepository2;
        this.defaultCurrencyInteractor = getDefaultCurrencyInteractor;
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

    public final boolean getHasZion() {
        return this.hasZion;
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
            boolean r0 = r8 instanceof com.bitcoin.mwallet.app.flows.walletdetails.walletsettings.WalletSettingsPresenter$displayZionMnemonic$1
            if (r0 == 0) goto L_0x0014
            r0 = r8
            com.bitcoin.mwallet.app.flows.walletdetails.walletsettings.WalletSettingsPresenter$displayZionMnemonic$1 r0 = (com.bitcoin.mwallet.app.flows.walletdetails.walletsettings.WalletSettingsPresenter$displayZionMnemonic$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L_0x0019
        L_0x0014:
            com.bitcoin.mwallet.app.flows.walletdetails.walletsettings.WalletSettingsPresenter$displayZionMnemonic$1 r0 = new com.bitcoin.mwallet.app.flows.walletdetails.walletsettings.WalletSettingsPresenter$displayZionMnemonic$1
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
            com.bitcoin.mwallet.app.flows.walletdetails.walletsettings.WalletSettingsPresenter r7 = (com.bitcoin.mwallet.app.flows.walletdetails.walletsettings.WalletSettingsPresenter) r7
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
            com.bitcoin.mwallet.app.flows.walletdetails.walletsettings.WalletSettingsPresenter r2 = (com.bitcoin.mwallet.app.flows.walletdetails.walletsettings.WalletSettingsPresenter) r2
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x0060
        L_0x004d:
            kotlin.ResultKt.throwOnFailure(r8)
            com.bitcoin.mwallet.core.repositories.WalletRepository r8 = r6.walletRepository
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
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.app.flows.walletdetails.walletsettings.WalletSettingsPresenter.displayZionMnemonic(com.bitcoin.mwallet.core.models.wallet.WalletId, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @NotNull
    public final MutableLiveData<String> getColorData() {
        return this.colorData;
    }

    @Nullable
    public final C1261Wallet getWallet(@NotNull WalletId walletId2) {
        Intrinsics.checkParameterIsNotNull(walletId2, "walletId");
        return (C1261Wallet) BuildersKt__BuildersKt.runBlocking$default(null, new WalletSettingsPresenter$getWallet$1(this, walletId2, null), 1, null);
    }

    public final void updateWalletSpendingThreshold(@NotNull BigDecimal bigDecimal) {
        Intrinsics.checkParameterIsNotNull(bigDecimal, "newValue");
        BuildersKt__Builders_commonKt.launch$default(this.viewModelScope, null, null, new WalletSettingsPresenter$updateWalletSpendingThreshold$1(this, bigDecimal, null), 3, null);
    }

    @Nullable
    public final BigDecimal getWalletSpendingThreshold() {
        ModifyWalletInteractor modifyWalletInteractor2 = this.modifyWalletInteractor;
        WalletId walletId2 = this.walletId;
        if (walletId2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("walletId");
        }
        return modifyWalletInteractor2.getSpendingThreshold(walletId2);
    }

    @NotNull
    public final Currency getDefaultCurrency() {
        return this.defaultCurrencyInteractor.getDefaultFiatCurrency();
    }

    public final void modifyWalletName(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "newName");
        BuildersKt__Builders_commonKt.launch$default(this.viewModelScope, null, null, new WalletSettingsPresenter$modifyWalletName$1(this, str, null), 3, null);
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

    public final void setColor(int i) {
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Object[] objArr = {Integer.valueOf(i & 16777215)};
        String format = String.format("#%06x", Arrays.copyOf(objArr, objArr.length));
        Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
        ModifyWalletInteractor modifyWalletInteractor2 = this.modifyWalletInteractor;
        WalletId walletId2 = this.walletId;
        if (walletId2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("walletId");
        }
        String str = "null cannot be cast to non-null type java.lang.String";
        if (format != null) {
            String upperCase = format.toUpperCase();
            String str2 = "(this as java.lang.String).toUpperCase()";
            Intrinsics.checkExpressionValueIsNotNull(upperCase, str2);
            modifyWalletInteractor2.changeWalletColor(walletId2, upperCase);
            MutableLiveData<String> mutableLiveData = this.colorData;
            if (format != null) {
                String upperCase2 = format.toUpperCase();
                Intrinsics.checkExpressionValueIsNotNull(upperCase2, str2);
                mutableLiveData.setValue(upperCase2);
                return;
            }
            throw new TypeCastException(str);
        }
        throw new TypeCastException(str);
    }

    public final void setHideBalances(boolean z) {
        ModifyWalletInteractor modifyWalletInteractor2 = this.modifyWalletInteractor;
        WalletId walletId2 = this.walletId;
        if (walletId2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("walletId");
        }
        modifyWalletInteractor2.setHideBalances(walletId2, z);
    }

    @NotNull
    public final LiveData<Integer> getWalletCount() {
        return LiveDataKt.combineLatestIgnoreNull(this.walletInteractor.getWallets(), getWallet(), WalletSettingsPresenter$getWalletCount$1.INSTANCE);
    }

    public final void setRequiresSpendingAuth(boolean z) {
        ModifyWalletInteractor modifyWalletInteractor2 = this.modifyWalletInteractor;
        WalletId walletId2 = this.walletId;
        if (walletId2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("walletId");
        }
        modifyWalletInteractor2.setRequiresSpendingAuth(walletId2, z);
    }

    public final void toAdvancedDetails() {
        WalletSettingsRouter walletSettingsRouter = (WalletSettingsRouter) getRouter();
        WalletId walletId2 = this.walletId;
        if (walletId2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("walletId");
        }
        walletSettingsRouter.toAdvancedDetails(walletId2);
    }

    public static /* synthetic */ void toDeleteWallet$default(WalletSettingsPresenter walletSettingsPresenter, Activity activity, int i, Object obj) {
        if ((i & 1) != 0) {
            activity = null;
        }
        walletSettingsPresenter.toDeleteWallet(activity);
    }

    public final void toDeleteWallet(@Nullable Activity activity) {
        if (!this.hasZion) {
            WalletSettingsRouter walletSettingsRouter = (WalletSettingsRouter) getRouter();
            WalletId walletId2 = this.walletId;
            if (walletId2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("walletId");
            }
            walletSettingsRouter.toDeleteWallet(walletId2);
        } else if (((Boolean) BuildersKt__BuildersKt.runBlocking$default(null, new WalletSettingsPresenter$toDeleteWallet$result$1(this, null), 1, null)).booleanValue() && activity != null) {
            ((WalletSettingsRouter) getRouter()).endActivity(activity);
        }
    }

    public final void toRecoverWallet() {
        String str = null;
        C1261Wallet wallet = (C1261Wallet) BuildersKt__BuildersKt.runBlocking$default(null, new WalletSettingsPresenter$toRecoverWallet$wallet$1(this, null), 1, null);
        if (wallet != null) {
            wallet.preference().setHasBackedUp(true);
        }
        if (this.hasZion) {
            BuildersKt__BuildersKt.runBlocking$default(null, new WalletSettingsPresenter$toRecoverWallet$1(this, null), 1, null);
            return;
        }
        WalletSettingsRouter walletSettingsRouter = (WalletSettingsRouter) getRouter();
        WalletId walletId2 = this.walletId;
        if (walletId2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("walletId");
        }
        if (wallet != null) {
            Coin coin = wallet.getCoin();
            if (coin != null) {
                str = coin.getTicker();
            }
        }
        walletSettingsRouter.toRecoverWallet(walletId2, str == null ? "" : wallet.getCoin().getTicker());
    }
}
