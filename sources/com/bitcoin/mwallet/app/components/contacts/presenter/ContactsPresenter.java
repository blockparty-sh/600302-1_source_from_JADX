package com.bitcoin.mwallet.app.components.contacts.presenter;

import android.content.Context;
import androidx.lifecycle.MutableLiveData;
import com.bitcoin.mwallet.app.components.contacts.interactor.ContactsInteractor;
import com.bitcoin.mwallet.app.components.contacts.interactor.ContactsInteractor.ContactResult;
import com.bitcoin.mwallet.app.viper.PresenterBase;
import com.bitcoin.mwallet.core.entity.WalletType;
import com.bitcoin.mwallet.core.models.contact.Contact;
import com.bitcoin.mwallet.core.services.AnalyticsService;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.GlobalScope;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u000e\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019J\u000e\u0010\u001a\u001a\u00020\u00172\u0006\u0010\u001b\u001a\u00020\u0013R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R \u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0006\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R \u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u000f\"\u0004\b\u0015\u0010\u0011R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/components/contacts/presenter/ContactsPresenter;", "Lcom/bitcoin/mwallet/app/viper/PresenterBase;", "context", "Landroid/content/Context;", "viewModelScope", "Lkotlinx/coroutines/CoroutineScope;", "interactor", "Lcom/bitcoin/mwallet/app/components/contacts/interactor/ContactsInteractor;", "analyticsService", "Lcom/bitcoin/mwallet/core/services/AnalyticsService;", "(Landroid/content/Context;Lkotlinx/coroutines/CoroutineScope;Lcom/bitcoin/mwallet/app/components/contacts/interactor/ContactsInteractor;Lcom/bitcoin/mwallet/core/services/AnalyticsService;)V", "daoResult", "Landroidx/lifecycle/MutableLiveData;", "Lcom/bitcoin/mwallet/app/components/contacts/interactor/ContactsInteractor$ContactResult;", "getDaoResult", "()Landroidx/lifecycle/MutableLiveData;", "setDaoResult", "(Landroidx/lifecycle/MutableLiveData;)V", "selectedWalletType", "Lcom/bitcoin/mwallet/core/entity/WalletType;", "getSelectedWalletType", "setSelectedWalletType", "onDeleteContact", "", "contact", "Lcom/bitcoin/mwallet/core/models/contact/Contact;", "selectWalletType", "walletType", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: ContactsPresenter.kt */
public final class ContactsPresenter extends PresenterBase {
    private final AnalyticsService analyticsService;
    private final Context context;
    @NotNull
    private MutableLiveData<ContactResult> daoResult = new MutableLiveData<>();
    /* access modifiers changed from: private */
    public ContactsInteractor interactor;
    @NotNull
    private MutableLiveData<WalletType> selectedWalletType = new MutableLiveData<>(WalletType.BCH);
    private final CoroutineScope viewModelScope;

    public ContactsPresenter(@NotNull Context context2, @NotNull CoroutineScope coroutineScope, @NotNull ContactsInteractor contactsInteractor, @NotNull AnalyticsService analyticsService2) {
        Intrinsics.checkParameterIsNotNull(context2, "context");
        Intrinsics.checkParameterIsNotNull(coroutineScope, "viewModelScope");
        Intrinsics.checkParameterIsNotNull(contactsInteractor, "interactor");
        Intrinsics.checkParameterIsNotNull(analyticsService2, "analyticsService");
        this.context = context2;
        this.viewModelScope = coroutineScope;
        this.interactor = contactsInteractor;
        this.analyticsService = analyticsService2;
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
    public final MutableLiveData<ContactResult> getDaoResult() {
        return this.daoResult;
    }

    public final void setDaoResult(@NotNull MutableLiveData<ContactResult> mutableLiveData) {
        Intrinsics.checkParameterIsNotNull(mutableLiveData, "<set-?>");
        this.daoResult = mutableLiveData;
    }

    public final void onDeleteContact(@NotNull Contact contact) {
        Intrinsics.checkParameterIsNotNull(contact, "contact");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new ContactsPresenter$onDeleteContact$1(this, contact, null), 3, null);
    }

    public final void selectWalletType(@NotNull WalletType walletType) {
        Intrinsics.checkParameterIsNotNull(walletType, "walletType");
        this.selectedWalletType.setValue(walletType);
    }
}
