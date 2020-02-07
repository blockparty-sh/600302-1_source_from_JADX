package com.bitcoin.mwallet;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import com.bitcoin.mwallet.core.services.AnalyticsService;
import com.bitcoin.mwallet.core.services.WalletRefresherTemp;
import com.bitcoin.mwallet.core.services.eventstream.EventStreamHandler;
import com.bitcoin.mwallet.core.workers.WorkManagerGlobalFactory;
import java.util.HashMap;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.koin.core.Koin;
import org.koin.core.KoinComponent;
import org.koin.core.KoinComponent.DefaultImpls;
import org.koin.core.qualifier.Qualifier;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0012\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0014R\u001b\u0010\u0004\u001a\u00020\u00058FX\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R\u001b\u0010\n\u001a\u00020\u000b8FX\u0002¢\u0006\f\n\u0004\b\u000e\u0010\t\u001a\u0004\b\f\u0010\rR\u001b\u0010\u000f\u001a\u00020\u00108FX\u0002¢\u0006\f\n\u0004\b\u0013\u0010\t\u001a\u0004\b\u0011\u0010\u0012R\u001b\u0010\u0014\u001a\u00020\u00158FX\u0002¢\u0006\f\n\u0004\b\u0018\u0010\t\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u001d"}, mo37405d2 = {"Lcom/bitcoin/mwallet/OnBoardingActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "Lorg/koin/core/KoinComponent;", "()V", "analyticsService", "Lcom/bitcoin/mwallet/core/services/AnalyticsService;", "getAnalyticsService", "()Lcom/bitcoin/mwallet/core/services/AnalyticsService;", "analyticsService$delegate", "Lkotlin/Lazy;", "refresher", "Lcom/bitcoin/mwallet/core/services/WalletRefresherTemp;", "getRefresher", "()Lcom/bitcoin/mwallet/core/services/WalletRefresherTemp;", "refresher$delegate", "streamHandler", "Lcom/bitcoin/mwallet/core/services/eventstream/EventStreamHandler;", "getStreamHandler", "()Lcom/bitcoin/mwallet/core/services/eventstream/EventStreamHandler;", "streamHandler$delegate", "workManagerFactory", "Lcom/bitcoin/mwallet/core/workers/WorkManagerGlobalFactory;", "getWorkManagerFactory", "()Lcom/bitcoin/mwallet/core/workers/WorkManagerGlobalFactory;", "workManagerFactory$delegate", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: OnBoardingActivity.kt */
public final class OnBoardingActivity extends AppCompatActivity implements KoinComponent {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(OnBoardingActivity.class), "refresher", "getRefresher()Lcom/bitcoin/mwallet/core/services/WalletRefresherTemp;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(OnBoardingActivity.class), "streamHandler", "getStreamHandler()Lcom/bitcoin/mwallet/core/services/eventstream/EventStreamHandler;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(OnBoardingActivity.class), "workManagerFactory", "getWorkManagerFactory()Lcom/bitcoin/mwallet/core/workers/WorkManagerGlobalFactory;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(OnBoardingActivity.class), "analyticsService", "getAnalyticsService()Lcom/bitcoin/mwallet/core/services/AnalyticsService;"))};
    private HashMap _$_findViewCache;
    @NotNull
    private final Lazy analyticsService$delegate;
    @NotNull
    private final Lazy refresher$delegate;
    @NotNull
    private final Lazy streamHandler$delegate;
    @NotNull
    private final Lazy workManagerFactory$delegate;

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View findViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    @NotNull
    public final AnalyticsService getAnalyticsService() {
        Lazy lazy = this.analyticsService$delegate;
        KProperty kProperty = $$delegatedProperties[3];
        return (AnalyticsService) lazy.getValue();
    }

    @NotNull
    public final WalletRefresherTemp getRefresher() {
        Lazy lazy = this.refresher$delegate;
        KProperty kProperty = $$delegatedProperties[0];
        return (WalletRefresherTemp) lazy.getValue();
    }

    @NotNull
    public final EventStreamHandler getStreamHandler() {
        Lazy lazy = this.streamHandler$delegate;
        KProperty kProperty = $$delegatedProperties[1];
        return (EventStreamHandler) lazy.getValue();
    }

    @NotNull
    public final WorkManagerGlobalFactory getWorkManagerFactory() {
        Lazy lazy = this.workManagerFactory$delegate;
        KProperty kProperty = $$delegatedProperties[2];
        return (WorkManagerGlobalFactory) lazy.getValue();
    }

    public OnBoardingActivity() {
        Qualifier qualifier = null;
        Function0 function0 = null;
        this.refresher$delegate = LazyKt.lazy(new OnBoardingActivity$$special$$inlined$inject$1(getKoin().getRootScope(), qualifier, function0));
        this.streamHandler$delegate = LazyKt.lazy(new OnBoardingActivity$$special$$inlined$inject$2(getKoin().getRootScope(), qualifier, function0));
        this.workManagerFactory$delegate = LazyKt.lazy(new OnBoardingActivity$$special$$inlined$inject$3(getKoin().getRootScope(), qualifier, function0));
        this.analyticsService$delegate = LazyKt.lazy(new OnBoardingActivity$$special$$inlined$inject$4(getKoin().getRootScope(), qualifier, function0));
    }

    @NotNull
    public Koin getKoin() {
        return DefaultImpls.getKoin(this);
    }

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) C1018R.layout.activity_onboarding);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.hide();
        }
        getRefresher();
        getStreamHandler();
        getWorkManagerFactory();
        getAnalyticsService();
    }
}
