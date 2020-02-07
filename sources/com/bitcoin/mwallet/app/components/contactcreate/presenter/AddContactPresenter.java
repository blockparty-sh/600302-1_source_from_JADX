package com.bitcoin.mwallet.app.components.contactcreate.presenter;

import android.content.Context;
import androidx.lifecycle.MutableLiveData;
import com.bitcoin.mwallet.app.components.contacts.interactor.ContactsInteractor;
import com.bitcoin.mwallet.app.components.contacts.interactor.ContactsInteractor.ContactResult;
import com.bitcoin.mwallet.app.viper.PresenterBase;
import com.bitcoin.mwallet.core.entity.WalletType;
import com.bitcoin.mwallet.core.models.BitcoinUriContent;
import com.bitcoin.mwallet.core.models.Coin;
import com.bitcoin.mwallet.core.services.AnalyticsService;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.GlobalScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\b\u0010*\u001a\u00020+H\u0002J\u0006\u0010,\u001a\u00020+J\u000e\u0010\"\u001a\u00020+2\u0006\u0010-\u001a\u00020\rJ\u0018\u0010.\u001a\u00020+2\u0006\u0010-\u001a\u00020\r2\b\u0010/\u001a\u0004\u0018\u00010'R \u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R \u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u000f\"\u0004\b\u0015\u0010\u0011R\u001a\u0010\u0016\u001a\u00020\u0017X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R \u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001d0\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u000f\"\u0004\b\u001f\u0010\u0011R\u000e\u0010\u0006\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R \u0010 \u001a\b\u0012\u0004\u0012\u00020\r0\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u000f\"\u0004\b\"\u0010\u0011R \u0010#\u001a\b\u0012\u0004\u0012\u00020\u00130\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\u000f\"\u0004\b%\u0010\u0011R \u0010&\u001a\b\u0012\u0004\u0012\u00020'0\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u000f\"\u0004\b)\u0010\u0011R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u00060"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/components/contactcreate/presenter/AddContactPresenter;", "Lcom/bitcoin/mwallet/app/viper/PresenterBase;", "context", "Landroid/content/Context;", "viewModelScope", "Lkotlinx/coroutines/CoroutineScope;", "interactor", "Lcom/bitcoin/mwallet/app/components/contacts/interactor/ContactsInteractor;", "analyticsService", "Lcom/bitcoin/mwallet/core/services/AnalyticsService;", "(Landroid/content/Context;Lkotlinx/coroutines/CoroutineScope;Lcom/bitcoin/mwallet/app/components/contacts/interactor/ContactsInteractor;Lcom/bitcoin/mwallet/core/services/AnalyticsService;)V", "address", "Landroidx/lifecycle/MutableLiveData;", "", "getAddress", "()Landroidx/lifecycle/MutableLiveData;", "setAddress", "(Landroidx/lifecycle/MutableLiveData;)V", "availableSave", "", "getAvailableSave", "setAvailableSave", "bitcoinUriContent", "Lcom/bitcoin/mwallet/core/models/BitcoinUriContent;", "getBitcoinUriContent", "()Lcom/bitcoin/mwallet/core/models/BitcoinUriContent;", "setBitcoinUriContent", "(Lcom/bitcoin/mwallet/core/models/BitcoinUriContent;)V", "daoResult", "Lcom/bitcoin/mwallet/app/components/contacts/interactor/ContactsInteractor$ContactResult;", "getDaoResult", "setDaoResult", "name", "getName", "setName", "pleaseSelectAsset", "getPleaseSelectAsset", "setPleaseSelectAsset", "selectedWalletType", "Lcom/bitcoin/mwallet/core/entity/WalletType;", "getSelectedWalletType", "setSelectedWalletType", "checkAvailableSave", "", "onCreateContact", "input", "setRawInput", "walletType", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: AddContactPresenter.kt */
public final class AddContactPresenter extends PresenterBase {
    @NotNull
    private MutableLiveData<String> address = new MutableLiveData<>();
    /* access modifiers changed from: private */
    public final AnalyticsService analyticsService;
    @NotNull
    private MutableLiveData<Boolean> availableSave;
    @NotNull
    public BitcoinUriContent bitcoinUriContent;
    private final Context context;
    @NotNull
    private MutableLiveData<ContactResult> daoResult = new MutableLiveData<>();
    /* access modifiers changed from: private */
    public ContactsInteractor interactor;
    @NotNull
    private MutableLiveData<String> name = new MutableLiveData<>();
    @NotNull
    private MutableLiveData<Boolean> pleaseSelectAsset;
    @NotNull
    private MutableLiveData<WalletType> selectedWalletType = new MutableLiveData<>(WalletType.BCH);
    private final CoroutineScope viewModelScope;

    public AddContactPresenter(@NotNull Context context2, @NotNull CoroutineScope coroutineScope, @NotNull ContactsInteractor contactsInteractor, @NotNull AnalyticsService analyticsService2) {
        Intrinsics.checkParameterIsNotNull(context2, "context");
        Intrinsics.checkParameterIsNotNull(coroutineScope, "viewModelScope");
        Intrinsics.checkParameterIsNotNull(contactsInteractor, "interactor");
        Intrinsics.checkParameterIsNotNull(analyticsService2, "analyticsService");
        this.context = context2;
        this.viewModelScope = coroutineScope;
        this.interactor = contactsInteractor;
        this.analyticsService = analyticsService2;
        Boolean valueOf = Boolean.valueOf(false);
        this.availableSave = new MutableLiveData<>(valueOf);
        this.pleaseSelectAsset = new MutableLiveData<>(valueOf);
    }

    @NotNull
    public final MutableLiveData<WalletType> getSelectedWalletType() {
        return this.selectedWalletType;
    }

    public final void setSelectedWalletType(@NotNull MutableLiveData<WalletType> mutableLiveData) {
        Intrinsics.checkParameterIsNotNull(mutableLiveData, "<set-?>");
        this.selectedWalletType = mutableLiveData;
    }

    @NotNull
    public final BitcoinUriContent getBitcoinUriContent() {
        BitcoinUriContent bitcoinUriContent2 = this.bitcoinUriContent;
        if (bitcoinUriContent2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bitcoinUriContent");
        }
        return bitcoinUriContent2;
    }

    public final void setBitcoinUriContent(@NotNull BitcoinUriContent bitcoinUriContent2) {
        Intrinsics.checkParameterIsNotNull(bitcoinUriContent2, "<set-?>");
        this.bitcoinUriContent = bitcoinUriContent2;
    }

    @NotNull
    public final MutableLiveData<String> getName() {
        return this.name;
    }

    public final void setName(@NotNull MutableLiveData<String> mutableLiveData) {
        Intrinsics.checkParameterIsNotNull(mutableLiveData, "<set-?>");
        this.name = mutableLiveData;
    }

    @NotNull
    public final MutableLiveData<String> getAddress() {
        return this.address;
    }

    public final void setAddress(@NotNull MutableLiveData<String> mutableLiveData) {
        Intrinsics.checkParameterIsNotNull(mutableLiveData, "<set-?>");
        this.address = mutableLiveData;
    }

    @NotNull
    public final MutableLiveData<ContactResult> getDaoResult() {
        return this.daoResult;
    }

    public final void setDaoResult(@NotNull MutableLiveData<ContactResult> mutableLiveData) {
        Intrinsics.checkParameterIsNotNull(mutableLiveData, "<set-?>");
        this.daoResult = mutableLiveData;
    }

    @NotNull
    public final MutableLiveData<Boolean> getAvailableSave() {
        return this.availableSave;
    }

    public final void setAvailableSave(@NotNull MutableLiveData<Boolean> mutableLiveData) {
        Intrinsics.checkParameterIsNotNull(mutableLiveData, "<set-?>");
        this.availableSave = mutableLiveData;
    }

    @NotNull
    public final MutableLiveData<Boolean> getPleaseSelectAsset() {
        return this.pleaseSelectAsset;
    }

    public final void setPleaseSelectAsset(@NotNull MutableLiveData<Boolean> mutableLiveData) {
        Intrinsics.checkParameterIsNotNull(mutableLiveData, "<set-?>");
        this.pleaseSelectAsset = mutableLiveData;
    }

    public final void onCreateContact() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new AddContactPresenter$onCreateContact$1(this, null), 3, null);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0036, code lost:
        if ((r1 == null || kotlin.text.StringsKt.isBlank(r1)) == false) goto L_0x003a;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void checkAvailableSave() {
        /*
            r4 = this;
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r0 = r4.availableSave
            androidx.lifecycle.MutableLiveData<com.bitcoin.mwallet.core.entity.WalletType> r1 = r4.selectedWalletType
            java.lang.Object r1 = r1.getValue()
            r2 = 1
            r3 = 0
            if (r1 == 0) goto L_0x0039
            androidx.lifecycle.MutableLiveData<java.lang.String> r1 = r4.address
            java.lang.Object r1 = r1.getValue()
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            if (r1 == 0) goto L_0x001f
            boolean r1 = kotlin.text.StringsKt.isBlank(r1)
            if (r1 == 0) goto L_0x001d
            goto L_0x001f
        L_0x001d:
            r1 = 0
            goto L_0x0020
        L_0x001f:
            r1 = 1
        L_0x0020:
            if (r1 != 0) goto L_0x0039
            androidx.lifecycle.MutableLiveData<java.lang.String> r1 = r4.name
            java.lang.Object r1 = r1.getValue()
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            if (r1 == 0) goto L_0x0035
            boolean r1 = kotlin.text.StringsKt.isBlank(r1)
            if (r1 == 0) goto L_0x0033
            goto L_0x0035
        L_0x0033:
            r1 = 0
            goto L_0x0036
        L_0x0035:
            r1 = 1
        L_0x0036:
            if (r1 != 0) goto L_0x0039
            goto L_0x003a
        L_0x0039:
            r2 = 0
        L_0x003a:
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r2)
            r0.setValue(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.app.components.contactcreate.presenter.AddContactPresenter.checkAvailableSave():void");
    }

    public final void setRawInput(@NotNull String str, @Nullable WalletType walletType) {
        Intrinsics.checkParameterIsNotNull(str, "input");
        this.selectedWalletType.setValue(walletType);
        boolean z = true;
        if (!Intrinsics.areEqual((Object) str, (Object) "")) {
            this.address.setValue(str);
            this.bitcoinUriContent = BitcoinUriContent.CREATOR.parse(str);
            BitcoinUriContent bitcoinUriContent2 = this.bitcoinUriContent;
            String str2 = "bitcoinUriContent";
            if (bitcoinUriContent2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(str2);
            }
            if (bitcoinUriContent2.isValid()) {
                BitcoinUriContent bitcoinUriContent3 = this.bitcoinUriContent;
                if (bitcoinUriContent3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(str2);
                }
                if (bitcoinUriContent3.isSlp()) {
                    this.selectedWalletType.setValue(WalletType.SLP);
                } else {
                    BitcoinUriContent bitcoinUriContent4 = this.bitcoinUriContent;
                    if (bitcoinUriContent4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException(str2);
                    }
                    if (bitcoinUriContent4.getCoin() != null) {
                        BitcoinUriContent bitcoinUriContent5 = this.bitcoinUriContent;
                        if (bitcoinUriContent5 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException(str2);
                        }
                        if (bitcoinUriContent5.getCoin() == Coin.BTC) {
                            this.selectedWalletType.setValue(WalletType.BTC);
                        } else {
                            this.selectedWalletType.setValue(WalletType.BCH);
                        }
                    }
                }
                MutableLiveData<Boolean> mutableLiveData = this.pleaseSelectAsset;
                BitcoinUriContent bitcoinUriContent6 = this.bitcoinUriContent;
                if (bitcoinUriContent6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(str2);
                }
                if (bitcoinUriContent6.getCoin() != null) {
                    z = false;
                }
                mutableLiveData.setValue(Boolean.valueOf(z));
            }
        } else {
            this.pleaseSelectAsset.setValue(Boolean.valueOf(false));
        }
        checkAvailableSave();
    }

    public final void setName(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "input");
        this.name.setValue(str);
        checkAvailableSave();
    }
}
