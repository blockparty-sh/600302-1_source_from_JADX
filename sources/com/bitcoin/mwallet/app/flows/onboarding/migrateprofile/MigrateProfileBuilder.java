package com.bitcoin.mwallet.app.flows.onboarding.migrateprofile;

import android.app.Application;
import android.content.Context;
import androidx.lifecycle.ViewModelKt;
import com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.entity.OldContact.OldContactFileSystemSource;
import com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.entity.OldWallet.OldWalletsJsonSource;
import com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.entity.OldWalletsFileSystemSource;
import com.bitcoin.mwallet.app.viper.ScreenBuilderBase;
import com.bitcoin.mwallet.core.repositories.ContactRepository;
import com.bitcoin.mwallet.core.repositories.UserRepository;
import com.bitcoin.mwallet.core.repositories.WalletRepository;
import com.bitcoin.mwallet.core.services.AnalyticsService;
import com.bitcoin.mwallet.core.services.WalletRefresherTemp;
import com.bitcoin.mwallet.core.services.eventstream.EventStreamHandler;
import com.bitcoin.mwallet.core.services.p010tx.TxService;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.koin.core.qualifier.Qualifier;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u001b\u0010\u000b\u001a\u00020\f8BX\u0002¢\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\r\u0010\u000eR\u001b\u0010\u0010\u001a\u00020\u00118BX\u0002¢\u0006\f\n\u0004\b\u0014\u0010\n\u001a\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0015\u001a\u00020\u0016X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0017\u001a\u00020\u0018X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001b\u001a\u00020\u001cX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u001b\u0010\u001f\u001a\u00020 8BX\u0002¢\u0006\f\n\u0004\b#\u0010\n\u001a\u0004\b!\u0010\"R\u001b\u0010$\u001a\u00020%8BX\u0002¢\u0006\f\n\u0004\b(\u0010\n\u001a\u0004\b&\u0010'R\u001b\u0010)\u001a\u00020*8BX\u0002¢\u0006\f\n\u0004\b-\u0010\n\u001a\u0004\b+\u0010,R\u001b\u0010.\u001a\u00020/8BX\u0002¢\u0006\f\n\u0004\b2\u0010\n\u001a\u0004\b0\u00101¨\u00063"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/onboarding/migrateprofile/MigrateProfileBuilder;", "Lcom/bitcoin/mwallet/app/viper/ScreenBuilderBase;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "analyticsService", "Lcom/bitcoin/mwallet/core/services/AnalyticsService;", "getAnalyticsService", "()Lcom/bitcoin/mwallet/core/services/AnalyticsService;", "analyticsService$delegate", "Lkotlin/Lazy;", "contactService", "Lcom/bitcoin/mwallet/core/repositories/ContactRepository;", "getContactService", "()Lcom/bitcoin/mwallet/core/repositories/ContactRepository;", "contactService$delegate", "eventStreamHandler", "Lcom/bitcoin/mwallet/core/services/eventstream/EventStreamHandler;", "getEventStreamHandler", "()Lcom/bitcoin/mwallet/core/services/eventstream/EventStreamHandler;", "eventStreamHandler$delegate", "interactor", "Lcom/bitcoin/mwallet/app/flows/onboarding/migrateprofile/MigrateProfileInteractor;", "presenter", "Lcom/bitcoin/mwallet/app/flows/onboarding/migrateprofile/MigrateProfilePresenter;", "getPresenter", "()Lcom/bitcoin/mwallet/app/flows/onboarding/migrateprofile/MigrateProfilePresenter;", "router", "Lcom/bitcoin/mwallet/app/flows/onboarding/migrateprofile/MigrateProfileRouter;", "getRouter", "()Lcom/bitcoin/mwallet/app/flows/onboarding/migrateprofile/MigrateProfileRouter;", "txService", "Lcom/bitcoin/mwallet/core/services/tx/TxService;", "getTxService", "()Lcom/bitcoin/mwallet/core/services/tx/TxService;", "txService$delegate", "userService", "Lcom/bitcoin/mwallet/core/repositories/UserRepository;", "getUserService", "()Lcom/bitcoin/mwallet/core/repositories/UserRepository;", "userService$delegate", "walletRefresherTemp", "Lcom/bitcoin/mwallet/core/services/WalletRefresherTemp;", "getWalletRefresherTemp", "()Lcom/bitcoin/mwallet/core/services/WalletRefresherTemp;", "walletRefresherTemp$delegate", "walletService", "Lcom/bitcoin/mwallet/core/repositories/WalletRepository;", "getWalletService", "()Lcom/bitcoin/mwallet/core/repositories/WalletRepository;", "walletService$delegate", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: MigrateProfileBuilder.kt */
public final class MigrateProfileBuilder extends ScreenBuilderBase {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(MigrateProfileBuilder.class), "userService", "getUserService()Lcom/bitcoin/mwallet/core/repositories/UserRepository;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(MigrateProfileBuilder.class), "analyticsService", "getAnalyticsService()Lcom/bitcoin/mwallet/core/services/AnalyticsService;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(MigrateProfileBuilder.class), "walletService", "getWalletService()Lcom/bitcoin/mwallet/core/repositories/WalletRepository;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(MigrateProfileBuilder.class), "contactService", "getContactService()Lcom/bitcoin/mwallet/core/repositories/ContactRepository;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(MigrateProfileBuilder.class), "eventStreamHandler", "getEventStreamHandler()Lcom/bitcoin/mwallet/core/services/eventstream/EventStreamHandler;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(MigrateProfileBuilder.class), "txService", "getTxService()Lcom/bitcoin/mwallet/core/services/tx/TxService;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(MigrateProfileBuilder.class), "walletRefresherTemp", "getWalletRefresherTemp()Lcom/bitcoin/mwallet/core/services/WalletRefresherTemp;"))};
    private final Lazy analyticsService$delegate;
    private final Lazy contactService$delegate;
    private final Lazy eventStreamHandler$delegate;
    private final MigrateProfileInteractor interactor;
    @NotNull
    private final MigrateProfilePresenter presenter;
    @NotNull
    private final MigrateProfileRouter router = new MigrateProfileRouter();
    private final Lazy txService$delegate;
    private final Lazy userService$delegate;
    private final Lazy walletRefresherTemp$delegate;
    private final Lazy walletService$delegate;

    private final AnalyticsService getAnalyticsService() {
        Lazy lazy = this.analyticsService$delegate;
        KProperty kProperty = $$delegatedProperties[1];
        return (AnalyticsService) lazy.getValue();
    }

    private final ContactRepository getContactService() {
        Lazy lazy = this.contactService$delegate;
        KProperty kProperty = $$delegatedProperties[3];
        return (ContactRepository) lazy.getValue();
    }

    private final EventStreamHandler getEventStreamHandler() {
        Lazy lazy = this.eventStreamHandler$delegate;
        KProperty kProperty = $$delegatedProperties[4];
        return (EventStreamHandler) lazy.getValue();
    }

    private final TxService getTxService() {
        Lazy lazy = this.txService$delegate;
        KProperty kProperty = $$delegatedProperties[5];
        return (TxService) lazy.getValue();
    }

    private final UserRepository getUserService() {
        Lazy lazy = this.userService$delegate;
        KProperty kProperty = $$delegatedProperties[0];
        return (UserRepository) lazy.getValue();
    }

    private final WalletRefresherTemp getWalletRefresherTemp() {
        Lazy lazy = this.walletRefresherTemp$delegate;
        KProperty kProperty = $$delegatedProperties[6];
        return (WalletRefresherTemp) lazy.getValue();
    }

    private final WalletRepository getWalletService() {
        Lazy lazy = this.walletService$delegate;
        KProperty kProperty = $$delegatedProperties[2];
        return (WalletRepository) lazy.getValue();
    }

    public MigrateProfileBuilder(@NotNull Application application) {
        Intrinsics.checkParameterIsNotNull(application, "application");
        super(application);
        Qualifier qualifier = null;
        Function0 function0 = null;
        this.userService$delegate = LazyKt.lazy(new MigrateProfileBuilder$$special$$inlined$inject$1(getKoin().getRootScope(), qualifier, function0));
        this.analyticsService$delegate = LazyKt.lazy(new MigrateProfileBuilder$$special$$inlined$inject$2(getKoin().getRootScope(), qualifier, function0));
        this.walletService$delegate = LazyKt.lazy(new MigrateProfileBuilder$$special$$inlined$inject$3(getKoin().getRootScope(), qualifier, function0));
        this.contactService$delegate = LazyKt.lazy(new MigrateProfileBuilder$$special$$inlined$inject$4(getKoin().getRootScope(), qualifier, function0));
        this.eventStreamHandler$delegate = LazyKt.lazy(new MigrateProfileBuilder$$special$$inlined$inject$5(getKoin().getRootScope(), qualifier, function0));
        this.txService$delegate = LazyKt.lazy(new MigrateProfileBuilder$$special$$inlined$inject$6(getKoin().getRootScope(), qualifier, function0));
        this.walletRefresherTemp$delegate = LazyKt.lazy(new MigrateProfileBuilder$$special$$inlined$inject$7(getKoin().getRootScope(), qualifier, function0));
        Context applicationContext = application.getApplicationContext();
        String str = "application.applicationContext";
        Intrinsics.checkExpressionValueIsNotNull(applicationContext, str);
        OldWalletsJsonSource oldWalletsFileSystemSource = new OldWalletsFileSystemSource(applicationContext);
        Context applicationContext2 = application.getApplicationContext();
        Intrinsics.checkExpressionValueIsNotNull(applicationContext2, str);
        MigrateProfileInteractor migrateProfileInteractor = new MigrateProfileInteractor(oldWalletsFileSystemSource, new OldContactFileSystemSource(applicationContext2), getUserService(), getWalletService(), getContactService(), getAnalyticsService(), getEventStreamHandler(), getTxService(), getWalletRefresherTemp());
        this.interactor = migrateProfileInteractor;
        CoroutineScope viewModelScope = ViewModelKt.getViewModelScope(this);
        Context applicationContext3 = application.getApplicationContext();
        Intrinsics.checkExpressionValueIsNotNull(applicationContext3, str);
        this.presenter = new MigrateProfilePresenter(viewModelScope, applicationContext3, this.interactor, getRouter());
    }

    /* access modifiers changed from: protected */
    @NotNull
    public MigrateProfileRouter getRouter() {
        return this.router;
    }

    @NotNull
    public MigrateProfilePresenter getPresenter() {
        return this.presenter;
    }
}
