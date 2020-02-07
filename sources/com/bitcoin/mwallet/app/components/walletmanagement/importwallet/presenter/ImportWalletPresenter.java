package com.bitcoin.mwallet.app.components.walletmanagement.importwallet.presenter;

import android.content.Context;
import android.widget.Toast;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.app.components.walletmanagement.importwallet.WalletBackupDecryptedResult;
import com.bitcoin.mwallet.app.viper.PresenterBase;
import com.bitcoin.mwallet.core.interactors.CreateWalletInteractor;
import com.bitcoin.mwallet.core.interactors.CreateWalletInteractor.CreateWalletResult;
import com.bitcoin.mwallet.core.models.Coin;
import com.bitcoin.mwallet.core.repositories.UserRepository;
import com.bitcoin.mwallet.zion.ZionRepository;
import com.google.gson.Gson;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.GlobalScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import timber.log.Timber;
import walletutils.Walletutils;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0010\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\u0010\u0010-\u001a\u00020.2\b\u0010/\u001a\u0004\u0018\u00010\u000fJ\u0010\u00100\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u0011H\u0002J\b\u0010\u001c\u001a\u0004\u0018\u00010\u0011J\u000e\u00101\u001a\u00020.2\u0006\u00102\u001a\u00020\u000fJ\u0006\u00103\u001a\u00020.J\u000e\u00104\u001a\u00020.2\u0006\u0010\u001b\u001a\u00020\u0011J\u000e\u00105\u001a\u00020.2\u0006\u00102\u001a\u00020\u000fJ\u0006\u00106\u001a\u00020.J\u0006\u00107\u001a\u00020.J\u000e\u00108\u001a\u00020.2\u0006\u00109\u001a\u00020\u000fJ\u000e\u0010:\u001a\u00020.2\u0006\u0010%\u001a\u00020\u000fJ\u0010\u0010;\u001a\u00020.2\b\u0010<\u001a\u0004\u0018\u00010\u000fJ!\u0010=\u001a\u00020.2\u0006\u0010\u001b\u001a\u00020\u00112\u0006\u0010>\u001a\u00020?H@ø\u0001\u0000¢\u0006\u0002\u0010@J)\u0010A\u001a\u00020.2\u0006\u0010\u001b\u001a\u00020\u00112\u0006\u0010B\u001a\u00020C2\u0006\u0010D\u001a\u00020EH@ø\u0001\u0000¢\u0006\u0002\u0010FJ)\u0010G\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010C\u0012\u0006\u0012\u0004\u0018\u00010E0H2\u0006\u0010\u001b\u001a\u00020\u0011H@ø\u0001\u0000¢\u0006\u0002\u0010IJ\u0010\u0010J\u001a\u00020.2\u0006\u0010\u001b\u001a\u00020\u0011H\u0002J\u0010\u0010,\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u0011H\u0002R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u000eX\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u00130\u000eX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0015\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00160\u000eX\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0018¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0017\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00110\u0018¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001aR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u001d\u001a\u00020\u001e¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R&\u0010!\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u00130\u0018X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u001a\"\u0004\b#\u0010$R\u0017\u0010%\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0018¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u001aR\"\u0010'\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00160\u0018X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u001a\"\u0004\b)\u0010$R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b*\u0010+R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020\u000fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006K"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/components/walletmanagement/importwallet/presenter/ImportWalletPresenter;", "Lcom/bitcoin/mwallet/app/viper/PresenterBase;", "context", "Landroid/content/Context;", "viewModelScope", "Lkotlinx/coroutines/CoroutineScope;", "createWalletInteractor", "Lcom/bitcoin/mwallet/core/interactors/CreateWalletInteractor;", "userRepository", "Lcom/bitcoin/mwallet/core/repositories/UserRepository;", "zionRepository", "Lcom/bitcoin/mwallet/zion/ZionRepository;", "(Landroid/content/Context;Lkotlinx/coroutines/CoroutineScope;Lcom/bitcoin/mwallet/core/interactors/CreateWalletInteractor;Lcom/bitcoin/mwallet/core/repositories/UserRepository;Lcom/bitcoin/mwallet/zion/ZionRepository;)V", "_backupCode", "Landroidx/lifecycle/MutableLiveData;", "", "_coin", "Lcom/bitcoin/mwallet/core/models/Coin;", "_mnemonicWords", "", "_password", "_processResult", "Lcom/bitcoin/mwallet/core/interactors/CreateWalletInteractor$CreateWalletResult;", "backupCode", "Landroidx/lifecycle/LiveData;", "getBackupCode", "()Landroidx/lifecycle/LiveData;", "coin", "getCoin", "hasZion", "", "getHasZion", "()Z", "mnemonicWords", "getMnemonicWords", "setMnemonicWords", "(Landroidx/lifecycle/LiveData;)V", "password", "getPassword", "processResult", "getProcessResult", "setProcessResult", "getUserRepository", "()Lcom/bitcoin/mwallet/core/repositories/UserRepository;", "walletName", "createWallet", "", "mnemonic", "defaultWalletName", "onAddMnemonic", "word", "onClearMnemonic", "onCoinSelected", "onDeleteMnemonic", "onImportByBackupCode", "onImportByMnemonic", "onSetBackupCode", "code", "onSetPassword", "onSetWalletName", "name", "recoverSoftwareWallet", "credential", "Lcom/bitcoin/mwallet/core/models/credential/CredentialMnemonic;", "(Lcom/bitcoin/mwallet/core/models/Coin;Lcom/bitcoin/mwallet/core/models/credential/CredentialMnemonic;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "recoverZionWallet", "zionId", "Lcom/bitcoin/mwallet/zion/ZionId;", "zionWalletName", "Lcom/bitcoin/mwallet/zion/ZionWalletName;", "(Lcom/bitcoin/mwallet/core/models/Coin;Lcom/bitcoin/mwallet/zion/ZionId;Lcom/bitcoin/mwallet/zion/ZionWalletName;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "registerZionWallet", "Lkotlin/Pair;", "(Lcom/bitcoin/mwallet/core/models/Coin;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setDefaultWalletNameIfUntouched", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: ImportWalletPresenter.kt */
public final class ImportWalletPresenter extends PresenterBase {
    private final MutableLiveData<String> _backupCode;
    private final MutableLiveData<Coin> _coin = new MutableLiveData<>(Coin.BCH);
    private final MutableLiveData<List<String>> _mnemonicWords = new MutableLiveData<>(CollectionsKt.emptyList());
    private final MutableLiveData<String> _password;
    /* access modifiers changed from: private */
    public final MutableLiveData<CreateWalletResult> _processResult = new MutableLiveData<>(null);
    @NotNull
    private final LiveData<String> backupCode;
    @NotNull
    private final LiveData<Coin> coin = this._coin;
    private final Context context;
    private final CreateWalletInteractor createWalletInteractor;
    private final boolean hasZion;
    @NotNull
    private LiveData<List<String>> mnemonicWords = this._mnemonicWords;
    @NotNull
    private final LiveData<String> password;
    @NotNull
    private LiveData<CreateWalletResult> processResult = this._processResult;
    @NotNull
    private final UserRepository userRepository;
    /* access modifiers changed from: private */
    public final CoroutineScope viewModelScope;
    private String walletName;
    /* access modifiers changed from: private */
    public final ZionRepository zionRepository;

    @Metadata(mo37403bv = {1, 0, 3}, mo37406k = 3, mo37407mv = {1, 1, 15})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[Coin.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1 = new int[Coin.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$2 = new int[Coin.values().length];

        static {
            $EnumSwitchMapping$0[Coin.BCH.ordinal()] = 1;
            $EnumSwitchMapping$0[Coin.BTC.ordinal()] = 2;
            $EnumSwitchMapping$1[Coin.BCH.ordinal()] = 1;
            $EnumSwitchMapping$1[Coin.BTC.ordinal()] = 2;
            $EnumSwitchMapping$2[Coin.BCH.ordinal()] = 1;
            $EnumSwitchMapping$2[Coin.BTC.ordinal()] = 2;
        }
    }

    @NotNull
    public final UserRepository getUserRepository() {
        return this.userRepository;
    }

    public ImportWalletPresenter(@NotNull Context context2, @NotNull CoroutineScope coroutineScope, @NotNull CreateWalletInteractor createWalletInteractor2, @NotNull UserRepository userRepository2, @NotNull ZionRepository zionRepository2) {
        Intrinsics.checkParameterIsNotNull(context2, "context");
        Intrinsics.checkParameterIsNotNull(coroutineScope, "viewModelScope");
        Intrinsics.checkParameterIsNotNull(createWalletInteractor2, "createWalletInteractor");
        Intrinsics.checkParameterIsNotNull(userRepository2, "userRepository");
        Intrinsics.checkParameterIsNotNull(zionRepository2, "zionRepository");
        this.context = context2;
        this.viewModelScope = coroutineScope;
        this.createWalletInteractor = createWalletInteractor2;
        this.userRepository = userRepository2;
        this.zionRepository = zionRepository2;
        String str = "";
        this._backupCode = new MutableLiveData<>(str);
        this.backupCode = this._backupCode;
        this._password = new MutableLiveData<>(str);
        this.password = this._password;
        this.hasZion = ((Boolean) BuildersKt__BuildersKt.runBlocking$default(null, new ImportWalletPresenter$hasZion$1(this, null), 1, null)).booleanValue();
        this.walletName = str;
    }

    @NotNull
    public final LiveData<Coin> getCoin() {
        return this.coin;
    }

    @NotNull
    public final LiveData<List<String>> getMnemonicWords() {
        return this.mnemonicWords;
    }

    public final void setMnemonicWords(@NotNull LiveData<List<String>> liveData) {
        Intrinsics.checkParameterIsNotNull(liveData, "<set-?>");
        this.mnemonicWords = liveData;
    }

    @NotNull
    public final LiveData<CreateWalletResult> getProcessResult() {
        return this.processResult;
    }

    public final void setProcessResult(@NotNull LiveData<CreateWalletResult> liveData) {
        Intrinsics.checkParameterIsNotNull(liveData, "<set-?>");
        this.processResult = liveData;
    }

    @NotNull
    public final LiveData<String> getBackupCode() {
        return this.backupCode;
    }

    @NotNull
    public final LiveData<String> getPassword() {
        return this.password;
    }

    public final boolean getHasZion() {
        return this.hasZion;
    }

    @Nullable
    /* renamed from: getCoin reason: collision with other method in class */
    public final Coin m472getCoin() {
        return (Coin) this._coin.getValue();
    }

    public final void onCoinSelected(@NotNull Coin coin2) {
        Intrinsics.checkParameterIsNotNull(coin2, "coin");
        this._coin.setValue(coin2);
        setDefaultWalletNameIfUntouched(coin2);
    }

    public final void onSetBackupCode(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "code");
        this._backupCode.setValue(str);
    }

    public final void onSetPassword(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "password");
        this._password.setValue(str);
    }

    public final void onSetWalletName(@Nullable String str) {
        if (str != null) {
            this.walletName = str;
        }
    }

    public final void onAddMnemonic(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "word");
        List list = (List) this._mnemonicWords.getValue();
        List list2 = null;
        List mutableList = list != null ? CollectionsKt.toMutableList((Collection) list) : null;
        if (mutableList != null) {
            mutableList.add(str);
        }
        MutableLiveData<List<String>> mutableLiveData = this._mnemonicWords;
        if (mutableList != null) {
            list2 = CollectionsKt.toList(mutableList);
        }
        mutableLiveData.setValue(list2);
    }

    public final void onDeleteMnemonic(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "word");
        List list = (List) this._mnemonicWords.getValue();
        List list2 = null;
        List mutableList = list != null ? CollectionsKt.toMutableList((Collection) list) : null;
        if (mutableList != null) {
            mutableList.remove(str);
        }
        MutableLiveData<List<String>> mutableLiveData = this._mnemonicWords;
        if (mutableList != null) {
            list2 = CollectionsKt.toList(mutableList);
        }
        mutableLiveData.setValue(list2);
    }

    public final void onClearMnemonic() {
        this._mnemonicWords.setValue(CollectionsKt.emptyList());
    }

    public final void onImportByMnemonic() {
        this.walletName = "";
        if (this._mnemonicWords.getValue() != null) {
            Object value = this._mnemonicWords.getValue();
            if (value == null) {
                Intrinsics.throwNpe();
            }
            Intrinsics.checkExpressionValueIsNotNull(value, "_mnemonicWords.value!!");
            createWallet(CollectionsKt.joinToString$default((Iterable) value, " ", null, null, 0, null, null, 62, null));
        }
    }

    public final void onImportByBackupCode() {
        try {
            WalletBackupDecryptedResult walletBackupDecryptedResult = (WalletBackupDecryptedResult) new Gson().fromJson(Walletutils.decryptSJCLJsonString((String) this._backupCode.getValue(), (String) this._password.getValue()), WalletBackupDecryptedResult.class);
            if (walletBackupDecryptedResult.getWalletName() != null) {
                String walletName2 = walletBackupDecryptedResult.getWalletName();
                if (walletName2 == null) {
                    Intrinsics.throwNpe();
                }
                this.walletName = walletName2;
            }
            this._coin.setValue(walletBackupDecryptedResult.getCoinFound());
            createWallet(walletBackupDecryptedResult.getMnemonic());
        } catch (Exception unused) {
            Context context2 = this.context;
            Toast.makeText(context2, context2.getResources().getString(C1018R.string.addwallet_incorrect_password), 0).show();
        }
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x005d  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00a6  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00dd  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ java.lang.Object registerZionWallet(@org.jetbrains.annotations.NotNull com.bitcoin.mwallet.core.models.Coin r10, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Pair<com.bitcoin.mwallet.zion.ZionId, com.bitcoin.mwallet.zion.ZionWalletName>> r11) {
        /*
            r9 = this;
            boolean r0 = r11 instanceof com.bitcoin.mwallet.app.components.walletmanagement.importwallet.presenter.ImportWalletPresenter$registerZionWallet$1
            if (r0 == 0) goto L_0x0014
            r0 = r11
            com.bitcoin.mwallet.app.components.walletmanagement.importwallet.presenter.ImportWalletPresenter$registerZionWallet$1 r0 = (com.bitcoin.mwallet.app.components.walletmanagement.importwallet.presenter.ImportWalletPresenter$registerZionWallet$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r11 = r0.label
            int r11 = r11 - r2
            r0.label = r11
            goto L_0x0019
        L_0x0014:
            com.bitcoin.mwallet.app.components.walletmanagement.importwallet.presenter.ImportWalletPresenter$registerZionWallet$1 r0 = new com.bitcoin.mwallet.app.components.walletmanagement.importwallet.presenter.ImportWalletPresenter$registerZionWallet$1
            r0.<init>(r9, r11)
        L_0x0019:
            java.lang.Object r11 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L_0x005d
            if (r2 == r5) goto L_0x004a
            if (r2 != r4) goto L_0x0042
            java.lang.Object r10 = r0.L$3
            com.bitcoin.mwallet.zion.ZionWalletName r10 = (com.bitcoin.mwallet.zion.ZionWalletName) r10
            java.lang.Object r1 = r0.L$2
            com.bitcoin.mwallet.zion.ZionNamePrefix r1 = (com.bitcoin.mwallet.zion.ZionNamePrefix) r1
            java.lang.Object r1 = r0.L$1
            com.bitcoin.mwallet.core.models.Coin r1 = (com.bitcoin.mwallet.core.models.Coin) r1
            java.lang.Object r0 = r0.L$0
            com.bitcoin.mwallet.app.components.walletmanagement.importwallet.presenter.ImportWalletPresenter r0 = (com.bitcoin.mwallet.app.components.walletmanagement.importwallet.presenter.ImportWalletPresenter) r0
            kotlin.ResultKt.throwOnFailure(r11)
            r8 = r11
            r11 = r10
            r10 = r8
            goto L_0x00cf
        L_0x0042:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L_0x004a:
            java.lang.Object r10 = r0.L$2
            com.bitcoin.mwallet.zion.ZionNamePrefix r10 = (com.bitcoin.mwallet.zion.ZionNamePrefix) r10
            java.lang.Object r2 = r0.L$1
            com.bitcoin.mwallet.core.models.Coin r2 = (com.bitcoin.mwallet.core.models.Coin) r2
            java.lang.Object r5 = r0.L$0
            com.bitcoin.mwallet.app.components.walletmanagement.importwallet.presenter.ImportWalletPresenter r5 = (com.bitcoin.mwallet.app.components.walletmanagement.importwallet.presenter.ImportWalletPresenter) r5
            kotlin.ResultKt.throwOnFailure(r11)
            r8 = r2
            r2 = r10
            r10 = r8
            goto L_0x00a2
        L_0x005d:
            kotlin.ResultKt.throwOnFailure(r11)
            int[] r11 = com.bitcoin.mwallet.app.components.walletmanagement.importwallet.presenter.ImportWalletPresenter.WhenMappings.$EnumSwitchMapping$0
            int r2 = r10.ordinal()
            r11 = r11[r2]
            if (r11 == r5) goto L_0x0075
            if (r11 != r4) goto L_0x006f
            com.bitcoin.mwallet.zion.ZionNamePrefix r11 = com.bitcoin.mwallet.zion.ZionNamePrefix.BTC
            goto L_0x0077
        L_0x006f:
            kotlin.NoWhenBranchMatchedException r10 = new kotlin.NoWhenBranchMatchedException
            r10.<init>()
            throw r10
        L_0x0075:
            com.bitcoin.mwallet.zion.ZionNamePrefix r11 = com.bitcoin.mwallet.zion.ZionNamePrefix.BCH
        L_0x0077:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r6 = "ZionNamePrefix: "
            r2.append(r6)
            r2.append(r11)
            java.lang.String r2 = r2.toString()
            java.lang.Object[] r6 = new java.lang.Object[r3]
            timber.log.Timber.m426d(r2, r6)
            com.bitcoin.mwallet.zion.ZionRepository r2 = r9.zionRepository
            r0.L$0 = r9
            r0.L$1 = r10
            r0.L$2 = r11
            r0.label = r5
            java.lang.Object r2 = r2.getNextAvailableWalletName(r11, r0)
            if (r2 != r1) goto L_0x009e
            return r1
        L_0x009e:
            r5 = r9
            r8 = r2
            r2 = r11
            r11 = r8
        L_0x00a2:
            com.bitcoin.mwallet.zion.ZionWalletName r11 = (com.bitcoin.mwallet.zion.ZionWalletName) r11
            if (r11 == 0) goto L_0x00dd
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "ZionWalletName: "
            r6.append(r7)
            r6.append(r11)
            java.lang.String r6 = r6.toString()
            java.lang.Object[] r3 = new java.lang.Object[r3]
            timber.log.Timber.m426d(r6, r3)
            com.bitcoin.mwallet.zion.ZionRepository r3 = r5.zionRepository
            r0.L$0 = r5
            r0.L$1 = r10
            r0.L$2 = r2
            r0.L$3 = r11
            r0.label = r4
            java.lang.Object r10 = r3.registerWallet(r11, r0)
            if (r10 != r1) goto L_0x00cf
            return r1
        L_0x00cf:
            com.bitcoin.mwallet.zion.ZionResponse r10 = (com.bitcoin.mwallet.zion.ZionResponse) r10
            java.lang.Object r10 = r10.getResult()
            com.bitcoin.mwallet.zion.ZionId r10 = (com.bitcoin.mwallet.zion.ZionId) r10
            kotlin.Pair r0 = new kotlin.Pair
            r0.<init>(r10, r11)
            return r0
        L_0x00dd:
            kotlin.Pair r10 = new kotlin.Pair
            r11 = 0
            r10.<init>(r11, r11)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.app.components.walletmanagement.importwallet.presenter.ImportWalletPresenter.registerZionWallet(com.bitcoin.mwallet.core.models.Coin, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final void createWallet(@Nullable String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("selected coin: ");
        sb.append((Coin) this.coin.getValue());
        Timber.m426d(sb.toString(), new Object[0]);
        Coin coin2 = (Coin) this.coin.getValue();
        if (coin2 != null) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new ImportWalletPresenter$createWallet$$inlined$let$lambda$1(coin2, null, this, str), 3, null);
        }
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x003e  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ java.lang.Object recoverSoftwareWallet(@org.jetbrains.annotations.NotNull com.bitcoin.mwallet.core.models.Coin r7, @org.jetbrains.annotations.NotNull com.bitcoin.mwallet.core.models.credential.CredentialMnemonic r8, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r9) {
        /*
            r6 = this;
            boolean r0 = r9 instanceof com.bitcoin.mwallet.app.components.walletmanagement.importwallet.presenter.ImportWalletPresenter$recoverSoftwareWallet$1
            if (r0 == 0) goto L_0x0014
            r0 = r9
            com.bitcoin.mwallet.app.components.walletmanagement.importwallet.presenter.ImportWalletPresenter$recoverSoftwareWallet$1 r0 = (com.bitcoin.mwallet.app.components.walletmanagement.importwallet.presenter.ImportWalletPresenter$recoverSoftwareWallet$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L_0x0019
        L_0x0014:
            com.bitcoin.mwallet.app.components.walletmanagement.importwallet.presenter.ImportWalletPresenter$recoverSoftwareWallet$1 r0 = new com.bitcoin.mwallet.app.components.walletmanagement.importwallet.presenter.ImportWalletPresenter$recoverSoftwareWallet$1
            r0.<init>(r6, r9)
        L_0x0019:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x003e
            if (r2 != r3) goto L_0x0036
            java.lang.Object r7 = r0.L$2
            com.bitcoin.mwallet.core.models.credential.CredentialMnemonic r7 = (com.bitcoin.mwallet.core.models.credential.CredentialMnemonic) r7
            java.lang.Object r7 = r0.L$1
            com.bitcoin.mwallet.core.models.Coin r7 = (com.bitcoin.mwallet.core.models.Coin) r7
            java.lang.Object r7 = r0.L$0
            com.bitcoin.mwallet.app.components.walletmanagement.importwallet.presenter.ImportWalletPresenter r7 = (com.bitcoin.mwallet.app.components.walletmanagement.importwallet.presenter.ImportWalletPresenter) r7
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x0057
        L_0x0036:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L_0x003e:
            kotlin.ResultKt.throwOnFailure(r9)
            com.bitcoin.mwallet.core.interactors.CreateWalletInteractor r9 = r6.createWalletInteractor
            java.lang.String r2 = r6.walletName(r7)
            r0.L$0 = r6
            r0.L$1 = r7
            r0.L$2 = r8
            r0.label = r3
            java.lang.Object r9 = r9.recoverSoftwareWallet(r7, r2, r8, r0)
            if (r9 != r1) goto L_0x0056
            return r1
        L_0x0056:
            r7 = r6
        L_0x0057:
            com.bitcoin.mwallet.core.interactors.CreateWalletInteractor$CreateWalletResult r9 = (com.bitcoin.mwallet.core.interactors.CreateWalletInteractor.CreateWalletResult) r9
            kotlinx.coroutines.CoroutineScope r0 = r7.viewModelScope
            r1 = 0
            r2 = 0
            com.bitcoin.mwallet.app.components.walletmanagement.importwallet.presenter.ImportWalletPresenter$recoverSoftwareWallet$2 r8 = new com.bitcoin.mwallet.app.components.walletmanagement.importwallet.presenter.ImportWalletPresenter$recoverSoftwareWallet$2
            r3 = 0
            r8.<init>(r7, r9, r3)
            r3 = r8
            kotlin.jvm.functions.Function2 r3 = (kotlin.jvm.functions.Function2) r3
            r4 = 3
            r5 = 0
            kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r0, r1, r2, r3, r4, r5)
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.app.components.walletmanagement.importwallet.presenter.ImportWalletPresenter.recoverSoftwareWallet(com.bitcoin.mwallet.core.models.Coin, com.bitcoin.mwallet.core.models.credential.CredentialMnemonic, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0043  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ java.lang.Object recoverZionWallet(@org.jetbrains.annotations.NotNull com.bitcoin.mwallet.core.models.Coin r8, @org.jetbrains.annotations.NotNull com.bitcoin.mwallet.zion.ZionId r9, @org.jetbrains.annotations.NotNull com.bitcoin.mwallet.zion.ZionWalletName r10, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r11) {
        /*
            r7 = this;
            boolean r0 = r11 instanceof com.bitcoin.mwallet.app.components.walletmanagement.importwallet.presenter.ImportWalletPresenter$recoverZionWallet$1
            if (r0 == 0) goto L_0x0014
            r0 = r11
            com.bitcoin.mwallet.app.components.walletmanagement.importwallet.presenter.ImportWalletPresenter$recoverZionWallet$1 r0 = (com.bitcoin.mwallet.app.components.walletmanagement.importwallet.presenter.ImportWalletPresenter$recoverZionWallet$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r11 = r0.label
            int r11 = r11 - r2
            r0.label = r11
            goto L_0x0019
        L_0x0014:
            com.bitcoin.mwallet.app.components.walletmanagement.importwallet.presenter.ImportWalletPresenter$recoverZionWallet$1 r0 = new com.bitcoin.mwallet.app.components.walletmanagement.importwallet.presenter.ImportWalletPresenter$recoverZionWallet$1
            r0.<init>(r7, r11)
        L_0x0019:
            r6 = r0
            java.lang.Object r11 = r6.result
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r6.label
            r2 = 1
            if (r1 == 0) goto L_0x0043
            if (r1 != r2) goto L_0x003b
            java.lang.Object r8 = r6.L$3
            com.bitcoin.mwallet.zion.ZionWalletName r8 = (com.bitcoin.mwallet.zion.ZionWalletName) r8
            java.lang.Object r8 = r6.L$2
            com.bitcoin.mwallet.zion.ZionId r8 = (com.bitcoin.mwallet.zion.ZionId) r8
            java.lang.Object r8 = r6.L$1
            com.bitcoin.mwallet.core.models.Coin r8 = (com.bitcoin.mwallet.core.models.Coin) r8
            java.lang.Object r8 = r6.L$0
            com.bitcoin.mwallet.app.components.walletmanagement.importwallet.presenter.ImportWalletPresenter r8 = (com.bitcoin.mwallet.app.components.walletmanagement.importwallet.presenter.ImportWalletPresenter) r8
            kotlin.ResultKt.throwOnFailure(r11)
            goto L_0x0061
        L_0x003b:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L_0x0043:
            kotlin.ResultKt.throwOnFailure(r11)
            com.bitcoin.mwallet.core.interactors.CreateWalletInteractor r1 = r7.createWalletInteractor
            java.lang.String r3 = r7.walletName(r8)
            r6.L$0 = r7
            r6.L$1 = r8
            r6.L$2 = r9
            r6.L$3 = r10
            r6.label = r2
            r2 = r8
            r4 = r9
            r5 = r10
            java.lang.Object r11 = r1.recoverZionWallet(r2, r3, r4, r5, r6)
            if (r11 != r0) goto L_0x0060
            return r0
        L_0x0060:
            r8 = r7
        L_0x0061:
            com.bitcoin.mwallet.core.interactors.CreateWalletInteractor$CreateWalletResult r11 = (com.bitcoin.mwallet.core.interactors.CreateWalletInteractor.CreateWalletResult) r11
            kotlinx.coroutines.CoroutineScope r0 = r8.viewModelScope
            r1 = 0
            r2 = 0
            com.bitcoin.mwallet.app.components.walletmanagement.importwallet.presenter.ImportWalletPresenter$recoverZionWallet$2 r9 = new com.bitcoin.mwallet.app.components.walletmanagement.importwallet.presenter.ImportWalletPresenter$recoverZionWallet$2
            r10 = 0
            r9.<init>(r8, r11, r10)
            r3 = r9
            kotlin.jvm.functions.Function2 r3 = (kotlin.jvm.functions.Function2) r3
            r4 = 3
            r5 = 0
            kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r0, r1, r2, r3, r4, r5)
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.app.components.walletmanagement.importwallet.presenter.ImportWalletPresenter.recoverZionWallet(com.bitcoin.mwallet.core.models.Coin, com.bitcoin.mwallet.zion.ZionId, com.bitcoin.mwallet.zion.ZionWalletName, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final void setDefaultWalletNameIfUntouched(Coin coin2) {
        String str;
        int i = WhenMappings.$EnumSwitchMapping$1[coin2.ordinal()];
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
        int i = WhenMappings.$EnumSwitchMapping$2[coin2.ordinal()];
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
