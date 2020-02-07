package com.bitcoin.mwallet.app.flows.walletdetails.deletewallet;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.bitcoin.mwallet.app.viper.ScreenPresenter;
import com.bitcoin.mwallet.core.interactors.GetWalletInteractor;
import com.bitcoin.mwallet.core.interactors.ModifyWalletInteractor;
import com.bitcoin.mwallet.core.models.wallet.C1261Wallet;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongycastle.i18n.TextBundle;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B-\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\u0002¢\u0006\u0002\u0010\fJ\u0011\u0010%\u001a\u00020&H@ø\u0001\u0000¢\u0006\u0002\u0010'J\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00170(J\u000e\u0010)\u001a\u00020&2\u0006\u0010\u001f\u001a\u00020 J\u000e\u0010*\u001a\u00020&2\u0006\u0010+\u001a\u00020,R \u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u001c\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR \u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0011\"\u0004\b\u001e\u0010\u0013R\u001a\u0010\u001f\u001a\u00020 X.¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006-"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/walletdetails/deletewallet/DeletewalletPresenter;", "Lcom/bitcoin/mwallet/app/viper/ScreenPresenter;", "Lcom/bitcoin/mwallet/app/flows/walletdetails/deletewallet/DeleteWalletRouter;", "context", "Landroid/content/Context;", "viewModelScope", "Lkotlinx/coroutines/CoroutineScope;", "walletInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetWalletInteractor;", "modifyWalletInteractor", "Lcom/bitcoin/mwallet/core/interactors/ModifyWalletInteractor;", "router", "(Landroid/content/Context;Lkotlinx/coroutines/CoroutineScope;Lcom/bitcoin/mwallet/core/interactors/GetWalletInteractor;Lcom/bitcoin/mwallet/core/interactors/ModifyWalletInteractor;Lcom/bitcoin/mwallet/app/flows/walletdetails/deletewallet/DeleteWalletRouter;)V", "buttonIsEnabled", "Landroidx/lifecycle/MutableLiveData;", "", "getButtonIsEnabled", "()Landroidx/lifecycle/MutableLiveData;", "setButtonIsEnabled", "(Landroidx/lifecycle/MutableLiveData;)V", "getViewModelScope", "()Lkotlinx/coroutines/CoroutineScope;", "wallet", "Lcom/bitcoin/mwallet/core/models/wallet/Wallet;", "getWallet", "()Lcom/bitcoin/mwallet/core/models/wallet/Wallet;", "setWallet", "(Lcom/bitcoin/mwallet/core/models/wallet/Wallet;)V", "walletDeleted", "getWalletDeleted", "setWalletDeleted", "walletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "getWalletId", "()Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "setWalletId", "(Lcom/bitcoin/mwallet/core/models/wallet/WalletId;)V", "deleteWallet", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Landroidx/lifecycle/LiveData;", "initializeWalletId", "setWalletNameText", "text", "", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: DeletewalletPresenter.kt */
public final class DeletewalletPresenter extends ScreenPresenter<DeleteWalletRouter> {
    @NotNull
    private MutableLiveData<Boolean> buttonIsEnabled;
    private final ModifyWalletInteractor modifyWalletInteractor;
    @NotNull
    private final CoroutineScope viewModelScope;
    @Nullable
    private C1261Wallet wallet;
    @NotNull
    private MutableLiveData<Boolean> walletDeleted;
    @NotNull
    public WalletId walletId;
    /* access modifiers changed from: private */
    public final GetWalletInteractor walletInteractor;

    @NotNull
    public final CoroutineScope getViewModelScope() {
        return this.viewModelScope;
    }

    public DeletewalletPresenter(@NotNull Context context, @NotNull CoroutineScope coroutineScope, @NotNull GetWalletInteractor getWalletInteractor, @NotNull ModifyWalletInteractor modifyWalletInteractor2, @NotNull DeleteWalletRouter deleteWalletRouter) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(coroutineScope, "viewModelScope");
        Intrinsics.checkParameterIsNotNull(getWalletInteractor, "walletInteractor");
        Intrinsics.checkParameterIsNotNull(modifyWalletInteractor2, "modifyWalletInteractor");
        Intrinsics.checkParameterIsNotNull(deleteWalletRouter, "router");
        super(context, deleteWalletRouter);
        this.viewModelScope = coroutineScope;
        this.walletInteractor = getWalletInteractor;
        this.modifyWalletInteractor = modifyWalletInteractor2;
        Boolean valueOf = Boolean.valueOf(false);
        this.buttonIsEnabled = new MutableLiveData<>(valueOf);
        this.walletDeleted = new MutableLiveData<>(valueOf);
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
    /* renamed from: getWallet reason: collision with other method in class */
    public final C1261Wallet m473getWallet() {
        return this.wallet;
    }

    public final void setWallet(@Nullable C1261Wallet wallet2) {
        this.wallet = wallet2;
    }

    @NotNull
    public final MutableLiveData<Boolean> getButtonIsEnabled() {
        return this.buttonIsEnabled;
    }

    public final void setButtonIsEnabled(@NotNull MutableLiveData<Boolean> mutableLiveData) {
        Intrinsics.checkParameterIsNotNull(mutableLiveData, "<set-?>");
        this.buttonIsEnabled = mutableLiveData;
    }

    @NotNull
    public final MutableLiveData<Boolean> getWalletDeleted() {
        return this.walletDeleted;
    }

    public final void setWalletDeleted(@NotNull MutableLiveData<Boolean> mutableLiveData) {
        Intrinsics.checkParameterIsNotNull(mutableLiveData, "<set-?>");
        this.walletDeleted = mutableLiveData;
    }

    public final void initializeWalletId(@NotNull WalletId walletId2) {
        Intrinsics.checkParameterIsNotNull(walletId2, "walletId");
        this.walletId = walletId2;
        BuildersKt__Builders_commonKt.launch$default(this.viewModelScope, null, null, new DeletewalletPresenter$initializeWalletId$1(this, walletId2, null), 3, null);
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

    public final void setWalletNameText(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, TextBundle.TEXT_ENTRY);
        BuildersKt__Builders_commonKt.launch$default(this.viewModelScope, null, null, new DeletewalletPresenter$setWalletNameText$1(this, str, null), 3, null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object deleteWallet(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r6) {
        /*
            r5 = this;
            boolean r0 = r6 instanceof com.bitcoin.mwallet.app.flows.walletdetails.deletewallet.DeletewalletPresenter$deleteWallet$1
            if (r0 == 0) goto L_0x0014
            r0 = r6
            com.bitcoin.mwallet.app.flows.walletdetails.deletewallet.DeletewalletPresenter$deleteWallet$1 r0 = (com.bitcoin.mwallet.app.flows.walletdetails.deletewallet.DeletewalletPresenter$deleteWallet$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L_0x0019
        L_0x0014:
            com.bitcoin.mwallet.app.flows.walletdetails.deletewallet.DeletewalletPresenter$deleteWallet$1 r0 = new com.bitcoin.mwallet.app.flows.walletdetails.deletewallet.DeletewalletPresenter$deleteWallet$1
            r0.<init>(r5, r6)
        L_0x0019:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0036
            if (r2 != r3) goto L_0x002e
            java.lang.Object r0 = r0.L$0
            com.bitcoin.mwallet.app.flows.walletdetails.deletewallet.DeletewalletPresenter r0 = (com.bitcoin.mwallet.app.flows.walletdetails.deletewallet.DeletewalletPresenter) r0
            kotlin.ResultKt.throwOnFailure(r6)
            goto L_0x0050
        L_0x002e:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)
            throw r6
        L_0x0036:
            kotlin.ResultKt.throwOnFailure(r6)
            com.bitcoin.mwallet.core.interactors.ModifyWalletInteractor r6 = r5.modifyWalletInteractor
            com.bitcoin.mwallet.core.models.wallet.WalletId r2 = r5.walletId
            if (r2 != 0) goto L_0x0044
            java.lang.String r4 = "walletId"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r4)
        L_0x0044:
            r0.L$0 = r5
            r0.label = r3
            java.lang.Object r6 = r6.deleteWallet(r2, r0)
            if (r6 != r1) goto L_0x004f
            return r1
        L_0x004f:
            r0 = r5
        L_0x0050:
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r6 = r0.walletDeleted
            java.lang.Boolean r0 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r3)
            r6.setValue(r0)
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.app.flows.walletdetails.deletewallet.DeletewalletPresenter.deleteWallet(kotlin.coroutines.Continuation):java.lang.Object");
    }
}
