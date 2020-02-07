package com.bitcoin.mwallet.app.components.contactcreate.builder;

import android.app.Application;
import androidx.lifecycle.ViewModelKt;
import com.bitcoin.mwallet.app.components.contactcreate.presenter.AddContactPresenter;
import com.bitcoin.mwallet.app.components.contacts.interactor.ContactsInteractor;
import com.bitcoin.mwallet.app.flows.settings.settings.SettingsRouter;
import com.bitcoin.mwallet.app.viper.ComponentBuilderBase;
import com.bitcoin.mwallet.core.repositories.ContactRepository;
import com.bitcoin.mwallet.core.services.AnalyticsService;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;
import org.koin.core.qualifier.Qualifier;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u001b\u0010\u000b\u001a\u00020\f8BX\u0002¢\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u0010\u001a\u00020\u0011X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\u00020\u0013X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0016\u001a\u00020\u0017¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019¨\u0006\u001a"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/components/contactcreate/builder/AddContactBuilder;", "Lcom/bitcoin/mwallet/app/viper/ComponentBuilderBase;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "analyticsService", "Lcom/bitcoin/mwallet/core/services/AnalyticsService;", "getAnalyticsService", "()Lcom/bitcoin/mwallet/core/services/AnalyticsService;", "analyticsService$delegate", "Lkotlin/Lazy;", "contactService", "Lcom/bitcoin/mwallet/core/repositories/ContactRepository;", "getContactService", "()Lcom/bitcoin/mwallet/core/repositories/ContactRepository;", "contactService$delegate", "interactor", "Lcom/bitcoin/mwallet/app/components/contacts/interactor/ContactsInteractor;", "presenter", "Lcom/bitcoin/mwallet/app/components/contactcreate/presenter/AddContactPresenter;", "getPresenter", "()Lcom/bitcoin/mwallet/app/components/contactcreate/presenter/AddContactPresenter;", "router", "Lcom/bitcoin/mwallet/app/flows/settings/settings/SettingsRouter;", "getRouter", "()Lcom/bitcoin/mwallet/app/flows/settings/settings/SettingsRouter;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: AddContactBuilder.kt */
public final class AddContactBuilder extends ComponentBuilderBase {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(AddContactBuilder.class), "contactService", "getContactService()Lcom/bitcoin/mwallet/core/repositories/ContactRepository;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(AddContactBuilder.class), "analyticsService", "getAnalyticsService()Lcom/bitcoin/mwallet/core/services/AnalyticsService;"))};
    private final Lazy analyticsService$delegate;
    private final Lazy contactService$delegate;
    private final ContactsInteractor interactor = new ContactsInteractor(getContactService());
    @NotNull
    private final AddContactPresenter presenter;
    @NotNull
    private final SettingsRouter router = new SettingsRouter();

    private final AnalyticsService getAnalyticsService() {
        Lazy lazy = this.analyticsService$delegate;
        KProperty kProperty = $$delegatedProperties[1];
        return (AnalyticsService) lazy.getValue();
    }

    private final ContactRepository getContactService() {
        Lazy lazy = this.contactService$delegate;
        KProperty kProperty = $$delegatedProperties[0];
        return (ContactRepository) lazy.getValue();
    }

    public AddContactBuilder(@NotNull Application application) {
        Intrinsics.checkParameterIsNotNull(application, "application");
        super(application);
        Qualifier qualifier = null;
        Function0 function0 = null;
        this.contactService$delegate = LazyKt.lazy(new AddContactBuilder$$special$$inlined$inject$1(getKoin().getRootScope(), qualifier, function0));
        this.analyticsService$delegate = LazyKt.lazy(new AddContactBuilder$$special$$inlined$inject$2(getKoin().getRootScope(), qualifier, function0));
        this.presenter = new AddContactPresenter(application, ViewModelKt.getViewModelScope(this), this.interactor, getAnalyticsService());
    }

    @NotNull
    public final SettingsRouter getRouter() {
        return this.router;
    }

    @NotNull
    public AddContactPresenter getPresenter() {
        return this.presenter;
    }
}
