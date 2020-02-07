package com.bitcoin.mwallet.app.components.contactlist.presenter;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.bitcoin.mwallet.app.components.contacts.interactor.ContactsInteractor;
import com.bitcoin.mwallet.app.viper.PresenterBase;
import com.bitcoin.mwallet.core.entity.WalletType;
import com.bitcoin.mwallet.core.models.contact.Contact;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u000e\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u0019R \u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR&\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00120\u0011X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R \u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\r\"\u0004\b\u001b\u0010\u000fR\u000e\u0010\u0006\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R \u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\r\"\u0004\b\u001e\u0010\u000fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\""}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/components/contactlist/presenter/ContactListPresenter;", "Lcom/bitcoin/mwallet/app/viper/PresenterBase;", "context", "Landroid/content/Context;", "viewModelScope", "Lkotlinx/coroutines/CoroutineScope;", "interactor", "Lcom/bitcoin/mwallet/app/components/contacts/interactor/ContactsInteractor;", "(Landroid/content/Context;Lkotlinx/coroutines/CoroutineScope;Lcom/bitcoin/mwallet/app/components/contacts/interactor/ContactsInteractor;)V", "address", "Landroidx/lifecycle/MutableLiveData;", "", "getAddress", "()Landroidx/lifecycle/MutableLiveData;", "setAddress", "(Landroidx/lifecycle/MutableLiveData;)V", "contacts", "Landroidx/lifecycle/LiveData;", "", "Lcom/bitcoin/mwallet/core/models/contact/Contact;", "getContacts", "()Landroidx/lifecycle/LiveData;", "setContacts", "(Landroidx/lifecycle/LiveData;)V", "currentWalletType", "Lcom/bitcoin/mwallet/core/entity/WalletType;", "getCurrentWalletType", "setCurrentWalletType", "name", "getName", "setName", "setWalletType", "", "walletType", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: ContactListPresenter.kt */
public final class ContactListPresenter extends PresenterBase {
    @NotNull
    private MutableLiveData<String> address = new MutableLiveData<>();
    @NotNull
    private LiveData<List<Contact>> contacts = this.interactor.getContacts();
    private final Context context;
    @NotNull
    private MutableLiveData<WalletType> currentWalletType = new MutableLiveData<>(WalletType.BCH);
    private ContactsInteractor interactor;
    @NotNull
    private MutableLiveData<String> name = new MutableLiveData<>();
    private final CoroutineScope viewModelScope;

    public ContactListPresenter(@NotNull Context context2, @NotNull CoroutineScope coroutineScope, @NotNull ContactsInteractor contactsInteractor) {
        Intrinsics.checkParameterIsNotNull(context2, "context");
        Intrinsics.checkParameterIsNotNull(coroutineScope, "viewModelScope");
        Intrinsics.checkParameterIsNotNull(contactsInteractor, "interactor");
        this.context = context2;
        this.viewModelScope = coroutineScope;
        this.interactor = contactsInteractor;
    }

    @NotNull
    public final LiveData<List<Contact>> getContacts() {
        return this.contacts;
    }

    public final void setContacts(@NotNull LiveData<List<Contact>> liveData) {
        Intrinsics.checkParameterIsNotNull(liveData, "<set-?>");
        this.contacts = liveData;
    }

    @NotNull
    public final MutableLiveData<WalletType> getCurrentWalletType() {
        return this.currentWalletType;
    }

    public final void setCurrentWalletType(@NotNull MutableLiveData<WalletType> mutableLiveData) {
        Intrinsics.checkParameterIsNotNull(mutableLiveData, "<set-?>");
        this.currentWalletType = mutableLiveData;
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

    public final void setWalletType(@NotNull WalletType walletType) {
        Intrinsics.checkParameterIsNotNull(walletType, "walletType");
        this.currentWalletType.postValue(walletType);
    }
}
