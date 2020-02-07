package com.bitcoin.mwallet.app.components.walletmanagement.createwallet.presenter;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.app.viper.PresenterBase;
import com.bitcoin.mwallet.core.interactors.CreateWalletInteractor;
import com.bitcoin.mwallet.core.interactors.CreateWalletInteractor.CreateWalletResult;
import com.bitcoin.mwallet.core.models.Coin;
import com.bitcoin.mwallet.core.repositories.UserRepository;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.GlobalScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0019\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u0010\u001a\u00020\rH@ø\u0001\u0000¢\u0006\u0002\u0010\u001eJ\u0010\u0010\u001f\u001a\u00020\u001b2\u0006\u0010\u0010\u001a\u00020\rH\u0002J\b\u0010\u0012\u001a\u0004\u0018\u00010\rJ\u000e\u0010 \u001a\u00020\u001d2\u0006\u0010\u0010\u001a\u00020\rJ\u0006\u0010!\u001a\u00020\u001dJ\u0010\u0010\"\u001a\u00020\u001d2\b\u0010#\u001a\u0004\u0018\u00010\u001bJ\u0010\u0010$\u001a\u00020\u001d2\u0006\u0010\u0010\u001a\u00020\rH\u0002J\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0010\u001a\u00020\rH\u0002R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\fX\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\r0\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\"\u0010\u0014\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u0011X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0013\"\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u000e¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006%"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/components/walletmanagement/createwallet/presenter/CreateWalletPresenter;", "Lcom/bitcoin/mwallet/app/viper/PresenterBase;", "context", "Landroid/content/Context;", "viewModelScope", "Lkotlinx/coroutines/CoroutineScope;", "userRepository", "Lcom/bitcoin/mwallet/core/repositories/UserRepository;", "createWalletInteractor", "Lcom/bitcoin/mwallet/core/interactors/CreateWalletInteractor;", "(Landroid/content/Context;Lkotlinx/coroutines/CoroutineScope;Lcom/bitcoin/mwallet/core/repositories/UserRepository;Lcom/bitcoin/mwallet/core/interactors/CreateWalletInteractor;)V", "_coin", "Landroidx/lifecycle/MutableLiveData;", "Lcom/bitcoin/mwallet/core/models/Coin;", "_processResult", "Lcom/bitcoin/mwallet/core/interactors/CreateWalletInteractor$CreateWalletResult;", "coin", "Landroidx/lifecycle/LiveData;", "getCoin", "()Landroidx/lifecycle/LiveData;", "processResult", "getProcessResult", "setProcessResult", "(Landroidx/lifecycle/LiveData;)V", "getUserRepository", "()Lcom/bitcoin/mwallet/core/repositories/UserRepository;", "walletName", "", "createWallet", "", "(Lcom/bitcoin/mwallet/core/models/Coin;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "defaultWalletName", "onCoinSelected", "onCreateWallet", "onSetWalletName", "name", "setDefaultWalletNameIfUntouched", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: CreateWalletPresenter.kt */
public final class CreateWalletPresenter extends PresenterBase {
    private final MutableLiveData<Coin> _coin = new MutableLiveData<>(Coin.BCH);
    /* access modifiers changed from: private */
    public final MutableLiveData<CreateWalletResult> _processResult = new MutableLiveData<>(null);
    @NotNull
    private final LiveData<Coin> coin = this._coin;
    private final Context context;
    private final CreateWalletInteractor createWalletInteractor;
    @NotNull
    private LiveData<CreateWalletResult> processResult = this._processResult;
    @NotNull
    private final UserRepository userRepository;
    private final CoroutineScope viewModelScope;
    private String walletName = "";

    @Metadata(mo37403bv = {1, 0, 3}, mo37406k = 3, mo37407mv = {1, 1, 15})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[Coin.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1 = new int[Coin.values().length];

        static {
            $EnumSwitchMapping$0[Coin.BCH.ordinal()] = 1;
            $EnumSwitchMapping$0[Coin.BTC.ordinal()] = 2;
            $EnumSwitchMapping$1[Coin.BCH.ordinal()] = 1;
            $EnumSwitchMapping$1[Coin.BTC.ordinal()] = 2;
        }
    }

    @NotNull
    public final UserRepository getUserRepository() {
        return this.userRepository;
    }

    public CreateWalletPresenter(@NotNull Context context2, @NotNull CoroutineScope coroutineScope, @NotNull UserRepository userRepository2, @NotNull CreateWalletInteractor createWalletInteractor2) {
        Intrinsics.checkParameterIsNotNull(context2, "context");
        Intrinsics.checkParameterIsNotNull(coroutineScope, "viewModelScope");
        Intrinsics.checkParameterIsNotNull(userRepository2, "userRepository");
        Intrinsics.checkParameterIsNotNull(createWalletInteractor2, "createWalletInteractor");
        this.context = context2;
        this.viewModelScope = coroutineScope;
        this.userRepository = userRepository2;
        this.createWalletInteractor = createWalletInteractor2;
    }

    @NotNull
    public final LiveData<Coin> getCoin() {
        return this.coin;
    }

    @NotNull
    public final LiveData<CreateWalletResult> getProcessResult() {
        return this.processResult;
    }

    public final void setProcessResult(@NotNull LiveData<CreateWalletResult> liveData) {
        Intrinsics.checkParameterIsNotNull(liveData, "<set-?>");
        this.processResult = liveData;
    }

    @Nullable
    /* renamed from: getCoin reason: collision with other method in class */
    public final Coin m471getCoin() {
        return (Coin) this._coin.getValue();
    }

    public final void onCoinSelected(@NotNull Coin coin2) {
        Intrinsics.checkParameterIsNotNull(coin2, "coin");
        this._coin.setValue(coin2);
        setDefaultWalletNameIfUntouched(coin2);
    }

    public final void onSetWalletName(@Nullable String str) {
        if (str != null) {
            this.walletName = str;
        }
    }

    public final void onCreateWallet() {
        Coin coin2 = (Coin) this.coin.getValue();
        if (coin2 != null) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new CreateWalletPresenter$onCreateWallet$$inlined$let$lambda$1(coin2, null, this), 3, null);
        }
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x003a  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ java.lang.Object createWallet(@org.jetbrains.annotations.NotNull com.bitcoin.mwallet.core.models.Coin r7, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
        /*
            r6 = this;
            boolean r0 = r8 instanceof com.bitcoin.mwallet.app.components.walletmanagement.createwallet.presenter.CreateWalletPresenter$createWallet$1
            if (r0 == 0) goto L_0x0014
            r0 = r8
            com.bitcoin.mwallet.app.components.walletmanagement.createwallet.presenter.CreateWalletPresenter$createWallet$1 r0 = (com.bitcoin.mwallet.app.components.walletmanagement.createwallet.presenter.CreateWalletPresenter$createWallet$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L_0x0019
        L_0x0014:
            com.bitcoin.mwallet.app.components.walletmanagement.createwallet.presenter.CreateWalletPresenter$createWallet$1 r0 = new com.bitcoin.mwallet.app.components.walletmanagement.createwallet.presenter.CreateWalletPresenter$createWallet$1
            r0.<init>(r6, r8)
        L_0x0019:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x003a
            if (r2 != r3) goto L_0x0032
            java.lang.Object r7 = r0.L$1
            com.bitcoin.mwallet.core.models.Coin r7 = (com.bitcoin.mwallet.core.models.Coin) r7
            java.lang.Object r7 = r0.L$0
            com.bitcoin.mwallet.app.components.walletmanagement.createwallet.presenter.CreateWalletPresenter r7 = (com.bitcoin.mwallet.app.components.walletmanagement.createwallet.presenter.CreateWalletPresenter) r7
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x0051
        L_0x0032:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L_0x003a:
            kotlin.ResultKt.throwOnFailure(r8)
            com.bitcoin.mwallet.core.interactors.CreateWalletInteractor r8 = r6.createWalletInteractor
            java.lang.String r2 = r6.walletName(r7)
            r0.L$0 = r6
            r0.L$1 = r7
            r0.label = r3
            java.lang.Object r8 = r8.createWallet(r7, r2, r0)
            if (r8 != r1) goto L_0x0050
            return r1
        L_0x0050:
            r7 = r6
        L_0x0051:
            com.bitcoin.mwallet.core.interactors.CreateWalletInteractor$CreateWalletResult r8 = (com.bitcoin.mwallet.core.interactors.CreateWalletInteractor.CreateWalletResult) r8
            kotlinx.coroutines.CoroutineScope r0 = r7.viewModelScope
            r1 = 0
            r2 = 0
            com.bitcoin.mwallet.app.components.walletmanagement.createwallet.presenter.CreateWalletPresenter$createWallet$2 r3 = new com.bitcoin.mwallet.app.components.walletmanagement.createwallet.presenter.CreateWalletPresenter$createWallet$2
            r4 = 0
            r3.<init>(r7, r8, r4)
            kotlin.jvm.functions.Function2 r3 = (kotlin.jvm.functions.Function2) r3
            r4 = 3
            r5 = 0
            kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r0, r1, r2, r3, r4, r5)
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.app.components.walletmanagement.createwallet.presenter.CreateWalletPresenter.createWallet(com.bitcoin.mwallet.core.models.Coin, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final void setDefaultWalletNameIfUntouched(Coin coin2) {
        String str;
        int i = WhenMappings.$EnumSwitchMapping$0[coin2.ordinal()];
        if (i == 1) {
            str = defaultWalletName(Coin.BTC);
        } else if (i == 2) {
            str = defaultWalletName(Coin.BCH);
        } else {
            throw new NoWhenBranchMatchedException();
        }
        if (Intrinsics.areEqual((Object) str, (Object) this.walletName)) {
            this.walletName = defaultWalletName(coin2);
        }
    }

    private final String walletName(Coin coin2) {
        String str = this.walletName;
        return StringsKt.isBlank(str) ? defaultWalletName(coin2) : str;
    }

    private final String defaultWalletName(Coin coin2) {
        int i = WhenMappings.$EnumSwitchMapping$1[coin2.ordinal()];
        if (i == 1) {
            String string = this.context.getString(C1018R.string.createwalletcoins_default_wallet_name_bch);
            Intrinsics.checkExpressionValueIsNotNull(string, "context.getString(R.stri…_default_wallet_name_bch)");
            return string;
        } else if (i == 2) {
            String string2 = this.context.getString(C1018R.string.createwalletcoins_default_wallet_name_btc);
            Intrinsics.checkExpressionValueIsNotNull(string2, "context.getString(R.stri…_default_wallet_name_btc)");
            return string2;
        } else {
            throw new NoWhenBranchMatchedException();
        }
    }
}
