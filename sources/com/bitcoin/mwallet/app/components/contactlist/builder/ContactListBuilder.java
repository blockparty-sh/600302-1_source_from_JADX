package com.bitcoin.mwallet.app.components.contactlist.builder;

import android.app.Application;
import androidx.lifecycle.ViewModelKt;
import com.bitcoin.mwallet.app.components.contactlist.presenter.ContactListPresenter;
import com.bitcoin.mwallet.app.components.contacts.interactor.ContactsInteractor;
import com.bitcoin.mwallet.app.viper.ComponentBuilderBase;
import com.bitcoin.mwallet.core.repositories.ContactRepository;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\u000b\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\u00020\u000eX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/components/contactlist/builder/ContactListBuilder;", "Lcom/bitcoin/mwallet/app/viper/ComponentBuilderBase;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "contactService", "Lcom/bitcoin/mwallet/core/repositories/ContactRepository;", "getContactService", "()Lcom/bitcoin/mwallet/core/repositories/ContactRepository;", "contactService$delegate", "Lkotlin/Lazy;", "interactor", "Lcom/bitcoin/mwallet/app/components/contacts/interactor/ContactsInteractor;", "presenter", "Lcom/bitcoin/mwallet/app/components/contactlist/presenter/ContactListPresenter;", "getPresenter", "()Lcom/bitcoin/mwallet/app/components/contactlist/presenter/ContactListPresenter;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: ContactListBuilder.kt */
public final class ContactListBuilder extends ComponentBuilderBase {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(ContactListBuilder.class), "contactService", "getContactService()Lcom/bitcoin/mwallet/core/repositories/ContactRepository;"))};
    private final Lazy contactService$delegate = LazyKt.lazy(new ContactListBuilder$$special$$inlined$inject$1(getKoin().getRootScope(), null, null));
    private final ContactsInteractor interactor = new ContactsInteractor(getContactService());
    @NotNull
    private final ContactListPresenter presenter;

    private final ContactRepository getContactService() {
        Lazy lazy = this.contactService$delegate;
        KProperty kProperty = $$delegatedProperties[0];
        return (ContactRepository) lazy.getValue();
    }

    public ContactListBuilder(@NotNull Application application) {
        Intrinsics.checkParameterIsNotNull(application, "application");
        super(application);
        this.presenter = new ContactListPresenter(application, ViewModelKt.getViewModelScope(this), this.interactor);
    }

    @NotNull
    public ContactListPresenter getPresenter() {
        return this.presenter;
    }
}
