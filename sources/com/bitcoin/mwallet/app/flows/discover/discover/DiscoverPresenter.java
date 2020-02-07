package com.bitcoin.mwallet.app.flows.discover.discover;

import android.content.Context;
import androidx.lifecycle.MutableLiveData;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.app.viper.ScreenPresenter;
import com.bitcoin.mwallet.core.interactors.DiscoverContentInteractor;
import com.bitcoin.mwallet.core.models.discover.FeaturedBusiness;
import com.bitcoin.mwallet.core.models.discover.Link;
import com.bitcoin.mwallet.core.services.AnalyticsService;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B-\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\u0002¢\u0006\u0002\u0010\fJ\u0006\u0010\u001a\u001a\u00020\u001bR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u001d\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u00130\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u001d\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\u00130\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0016¨\u0006\u001c"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/discover/discover/DiscoverPresenter;", "Lcom/bitcoin/mwallet/app/viper/ScreenPresenter;", "Lcom/bitcoin/mwallet/app/flows/discover/discover/DiscoverRouter;", "context", "Landroid/content/Context;", "discoverContentInteractor", "Lcom/bitcoin/mwallet/core/interactors/DiscoverContentInteractor;", "analyticsService", "Lcom/bitcoin/mwallet/core/services/AnalyticsService;", "viewModelScope", "Lkotlinx/coroutines/CoroutineScope;", "router", "(Landroid/content/Context;Lcom/bitcoin/mwallet/core/interactors/DiscoverContentInteractor;Lcom/bitcoin/mwallet/core/services/AnalyticsService;Lkotlinx/coroutines/CoroutineScope;Lcom/bitcoin/mwallet/app/flows/discover/discover/DiscoverRouter;)V", "getAnalyticsService", "()Lcom/bitcoin/mwallet/core/services/AnalyticsService;", "getDiscoverContentInteractor", "()Lcom/bitcoin/mwallet/core/interactors/DiscoverContentInteractor;", "externalLinks", "Landroidx/lifecycle/MutableLiveData;", "", "Lcom/bitcoin/mwallet/core/models/discover/Link;", "getExternalLinks", "()Landroidx/lifecycle/MutableLiveData;", "featuredBusinesses", "Lcom/bitcoin/mwallet/core/models/discover/FeaturedBusiness;", "getFeaturedBusinesses", "marcoCoinoClicked", "", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: DiscoverPresenter.kt */
public final class DiscoverPresenter extends ScreenPresenter<DiscoverRouter> {
    @NotNull
    private final AnalyticsService analyticsService;
    @NotNull
    private final DiscoverContentInteractor discoverContentInteractor;
    @NotNull
    private final MutableLiveData<List<Link>> externalLinks = new MutableLiveData<>();
    @NotNull
    private final MutableLiveData<List<FeaturedBusiness>> featuredBusinesses = new MutableLiveData<>();

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, mo37405d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
    @DebugMetadata(mo37999c = "com.bitcoin.mwallet.app.flows.discover.discover.DiscoverPresenter$1", mo38000f = "DiscoverPresenter.kt", mo38001i = {}, mo38002l = {37, 38}, mo38003m = "invokeSuspend", mo38004n = {}, mo38005s = {})
    /* renamed from: com.bitcoin.mwallet.app.flows.discover.discover.DiscoverPresenter$1 */
    /* compiled from: DiscoverPresenter.kt */
    static final class C10931 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        int label;

        /* renamed from: p$ */
        private CoroutineScope f246p$;
        final /* synthetic */ DiscoverPresenter this$0;

        {
            this.this$0 = r1;
        }

        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            Intrinsics.checkParameterIsNotNull(continuation, "completion");
            C10931 r0 = new C10931(this.this$0, continuation);
            r0.f246p$ = (CoroutineScope) obj;
            return r0;
        }

        public final Object invoke(Object obj, Object obj2) {
            return ((C10931) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            MutableLiveData mutableLiveData;
            MutableLiveData mutableLiveData2;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.f246p$;
                mutableLiveData2 = this.this$0.getExternalLinks();
                DiscoverContentInteractor discoverContentInteractor = this.this$0.getDiscoverContentInteractor();
                this.L$0 = mutableLiveData2;
                this.label = 1;
                obj = discoverContentInteractor.getLinks(this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (i == 1) {
                mutableLiveData2 = (MutableLiveData) this.L$0;
                ResultKt.throwOnFailure(obj);
            } else if (i == 2) {
                mutableLiveData = (MutableLiveData) this.L$0;
                ResultKt.throwOnFailure(obj);
                mutableLiveData.setValue(obj);
                return Unit.INSTANCE;
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            mutableLiveData2.setValue(obj);
            MutableLiveData featuredBusinesses = this.this$0.getFeaturedBusinesses();
            DiscoverContentInteractor discoverContentInteractor2 = this.this$0.getDiscoverContentInteractor();
            this.L$0 = featuredBusinesses;
            this.label = 2;
            Object featuredBusinesses2 = discoverContentInteractor2.getFeaturedBusinesses(this);
            if (featuredBusinesses2 == coroutine_suspended) {
                return coroutine_suspended;
            }
            mutableLiveData = featuredBusinesses;
            obj = featuredBusinesses2;
            mutableLiveData.setValue(obj);
            return Unit.INSTANCE;
        }
    }

    @NotNull
    public final DiscoverContentInteractor getDiscoverContentInteractor() {
        return this.discoverContentInteractor;
    }

    @NotNull
    public final AnalyticsService getAnalyticsService() {
        return this.analyticsService;
    }

    public DiscoverPresenter(@NotNull Context context, @NotNull DiscoverContentInteractor discoverContentInteractor2, @NotNull AnalyticsService analyticsService2, @NotNull CoroutineScope coroutineScope, @NotNull DiscoverRouter discoverRouter) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(discoverContentInteractor2, "discoverContentInteractor");
        Intrinsics.checkParameterIsNotNull(analyticsService2, "analyticsService");
        Intrinsics.checkParameterIsNotNull(coroutineScope, "viewModelScope");
        Intrinsics.checkParameterIsNotNull(discoverRouter, "router");
        super(context, discoverRouter);
        this.discoverContentInteractor = discoverContentInteractor2;
        this.analyticsService = analyticsService2;
        String str = "tab_open";
        this.analyticsService.track(str, MapsKt.mapOf(TuplesKt.m309to("tab_clicked_id", Integer.valueOf(C1018R.C1021id.nav_discover)), TuplesKt.m309to("tab_clicked", "Discover")));
        BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new C10931(this, null), 3, null);
    }

    @NotNull
    public final MutableLiveData<List<Link>> getExternalLinks() {
        return this.externalLinks;
    }

    @NotNull
    public final MutableLiveData<List<FeaturedBusiness>> getFeaturedBusinesses() {
        return this.featuredBusinesses;
    }

    public final void marcoCoinoClicked() {
        ((DiscoverRouter) getRouter()).toMacroCoino();
    }
}
