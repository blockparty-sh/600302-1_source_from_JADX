package com.bitcoin.mwallet.app.flows.home.home;

import android.content.Context;
import androidx.fragment.app.FragmentManager;
import com.bitcoin.bitcoink.p008tx.TxId;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.app.viper.PresenterFinishOnBackHandler;
import com.bitcoin.mwallet.app.viper.PresenterWithFinishOnBackHandler;
import com.bitcoin.mwallet.app.viper.ScreenPresenter;
import com.bitcoin.mwallet.core.models.Coin;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import com.bitcoin.mwallet.core.services.AnalyticsService;
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
import timber.log.Timber;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B-\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u0002\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rJ\u0006\u0010\u0014\u001a\u00020\u0015J\u0006\u0010\u0016\u001a\u00020\u0015J\u0006\u0010\u0017\u001a\u00020\u0015J\u000e\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\u001aJ\u0016\u0010\u001b\u001a\u00020\u00152\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u001c\u001a\u00020\u001dJ\u0016\u0010\u001e\u001a\u00020\u00152\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\u00020\u000fX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000¨\u0006#"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/home/home/HomePresenter;", "Lcom/bitcoin/mwallet/app/viper/ScreenPresenter;", "Lcom/bitcoin/mwallet/app/flows/home/home/HomeRouter;", "Lcom/bitcoin/mwallet/app/viper/PresenterWithFinishOnBackHandler;", "context", "Landroid/content/Context;", "viewModelScope", "Lkotlinx/coroutines/CoroutineScope;", "analyticsService", "Lcom/bitcoin/mwallet/core/services/AnalyticsService;", "router", "interactor", "Lcom/bitcoin/mwallet/app/flows/home/home/HomeInteractor;", "(Landroid/content/Context;Lkotlinx/coroutines/CoroutineScope;Lcom/bitcoin/mwallet/core/services/AnalyticsService;Lcom/bitcoin/mwallet/app/flows/home/home/HomeRouter;Lcom/bitcoin/mwallet/app/flows/home/home/HomeInteractor;)V", "backHandler", "Lcom/bitcoin/mwallet/app/viper/PresenterFinishOnBackHandler;", "getBackHandler", "()Lcom/bitcoin/mwallet/app/viper/PresenterFinishOnBackHandler;", "getContext", "()Landroid/content/Context;", "addBuyAnalytics", "", "onReceive", "onSend", "onTransactionClicked", "txId", "Lcom/bitcoin/bitcoink/tx/TxId;", "onWalletSelected", "walletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "toAddWallet", "coin", "Lcom/bitcoin/mwallet/core/models/Coin;", "fm", "Landroidx/fragment/app/FragmentManager;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: HomePresenter.kt */
public final class HomePresenter extends ScreenPresenter<HomeRouter> implements PresenterWithFinishOnBackHandler {
    private final AnalyticsService analyticsService;
    @NotNull
    private final PresenterFinishOnBackHandler backHandler = new PresenterFinishOnBackHandler();
    @NotNull
    private final Context context;
    private final CoroutineScope viewModelScope;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, mo37405d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
    @DebugMetadata(mo37999c = "com.bitcoin.mwallet.app.flows.home.home.HomePresenter$1", mo38000f = "HomePresenter.kt", mo38001i = {}, mo38002l = {40}, mo38003m = "invokeSuspend", mo38004n = {}, mo38005s = {})
    /* renamed from: com.bitcoin.mwallet.app.flows.home.home.HomePresenter$1 */
    /* compiled from: HomePresenter.kt */
    static final class C10951 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
        int label;

        /* renamed from: p$ */
        private CoroutineScope f247p$;

        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            Intrinsics.checkParameterIsNotNull(continuation, "completion");
            C10951 r0 = new C10951(homeInteractor, continuation);
            r0.f247p$ = (CoroutineScope) obj;
            return r0;
        }

        public final Object invoke(Object obj, Object obj2) {
            return ((C10951) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.f247p$;
                HomeInteractor homeInteractor = homeInteractor;
                this.label = 1;
                obj = homeInteractor.needsOnboarding(this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (i == 1) {
                ResultKt.throwOnFailure(obj);
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return obj;
        }
    }

    @NotNull
    public final Context getContext() {
        return this.context;
    }

    public HomePresenter(@NotNull Context context2, @NotNull CoroutineScope coroutineScope, @NotNull AnalyticsService analyticsService2, @NotNull HomeRouter homeRouter, @NotNull final HomeInteractor homeInteractor) {
        Intrinsics.checkParameterIsNotNull(context2, "context");
        Intrinsics.checkParameterIsNotNull(coroutineScope, "viewModelScope");
        Intrinsics.checkParameterIsNotNull(analyticsService2, "analyticsService");
        Intrinsics.checkParameterIsNotNull(homeRouter, "router");
        Intrinsics.checkParameterIsNotNull(homeInteractor, "interactor");
        super(context2, homeRouter);
        this.context = context2;
        this.viewModelScope = coroutineScope;
        this.analyticsService = analyticsService2;
        String str = "tab_open";
        this.analyticsService.track(str, MapsKt.mapOf(TuplesKt.m309to("tab_clicked_id", Integer.valueOf(C1018R.C1021id.nav_home)), TuplesKt.m309to("tab_clicked", "Home")));
        if (((Boolean) BuildersKt__BuildersKt.runBlocking$default(null, new C10951(null), 1, null)).booleanValue()) {
            homeRouter.toOnBoarding(this.context);
        }
    }

    @NotNull
    public PresenterFinishOnBackHandler getBackHandler() {
        return this.backHandler;
    }

    public final void onTransactionClicked(@NotNull TxId txId) {
        Intrinsics.checkParameterIsNotNull(txId, "txId");
        Timber.m426d("HomePresenter.onTransaction()", new Object[0]);
        ((HomeRouter) getRouter()).toTransaction(txId);
    }

    public final void onReceive() {
        Timber.m426d("HomePresenter.onReceive()", new Object[0]);
        ((HomeRouter) getRouter()).toReceive(this.context);
    }

    public final void onSend() {
        this.analyticsService.track("home_screen_tap_on_send", MapsKt.emptyMap());
        Timber.m426d("HomePresenter.onSend()", new Object[0]);
        ((HomeRouter) getRouter()).toSend(this.context);
    }

    public final void onWalletSelected(@NotNull Context context2, @NotNull WalletId walletId) {
        Intrinsics.checkParameterIsNotNull(context2, "context");
        Intrinsics.checkParameterIsNotNull(walletId, "walletId");
        ((HomeRouter) getRouter()).toWallet(context2, walletId);
    }

    public final void toAddWallet(@NotNull Coin coin, @NotNull FragmentManager fragmentManager) {
        Intrinsics.checkParameterIsNotNull(coin, "coin");
        Intrinsics.checkParameterIsNotNull(fragmentManager, "fm");
        ((HomeRouter) getRouter()).toAddWallet(coin, fragmentManager);
    }

    public final void addBuyAnalytics() {
        String str = "buy_bitcoin_click";
        this.analyticsService.track(str, MapsKt.mapOf(TuplesKt.m309to("screen", "Home"), TuplesKt.m309to("coin", "General")));
    }
}
